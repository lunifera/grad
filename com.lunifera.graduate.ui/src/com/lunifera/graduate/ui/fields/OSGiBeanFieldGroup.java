/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Contributor:
 * 		Florian Pirchner - copied from com.vaadin.data.fieldgroup.OSGiBeanFieldGroup and added OSGi support
 */
package com.lunifera.graduate.ui.fields;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.lunifera.graduate.ui.validator.BeanValidator;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanUtil;
import com.vaadin.ui.Field;

@SuppressWarnings("serial")
public class OSGiBeanFieldGroup<T> extends FieldGroup {

	private final Class<T> beanType;

	private Validator validator;
	private final Map<Field<?>, BeanValidator> defaultValidators;

	private ValidatorFactory validatorFactory;

	private ServiceReference<ValidatorFactory> ref;

	public OSGiBeanFieldGroup(Class<T> beanType) {
		this.beanType = beanType;
		this.defaultValidators = new HashMap<Field<?>, BeanValidator>();
	}

	@Override
	protected Class<?> getPropertyType(Object propertyId) {
		if (getItemDataSource() != null) {
			return super.getPropertyType(propertyId);
		} else {
			// Data source not set so we need to figure out the type manually
			/*
			 * toString should never really be needed as propertyId should be of
			 * form "fieldName" or "fieldName.subField[.subField2]" but the
			 * method declaration comes from parent.
			 */
			try {
				Class<?> type = BeanUtil.getPropertyType(beanType,
						propertyId.toString());
				if (type == null) {
					throw new BindException(
							"Cannot determine type of propertyId '"
									+ propertyId
									+ "'. The propertyId was not found in "
									+ beanType.getName());
				}
				return type;
			} catch (IntrospectionException e) {
				throw new BindException("Cannot determine type of propertyId '"
						+ propertyId + "'. Unable to introspect " + beanType, e);
			}
		}
	}

	@Override
	protected Object findPropertyId(java.lang.reflect.Field memberField) {
		String fieldName = memberField.getName();

		Item dataSource = getItemDataSource();
		boolean isNested = fieldName.contains("_");
		if (isNested) {
			String nestedPropertyId = fieldName.replace("_", ".");
			ensureNestedPropertyAdded(nestedPropertyId);
			if (dataSource != null
					&& dataSource.getItemProperty(nestedPropertyId) != null) {
				return nestedPropertyId;
			}
		} else {
			if (dataSource != null
					&& dataSource.getItemProperty(fieldName) != null) {
				return fieldName;
			} else {
				String minifiedFieldName = minifyFieldName(fieldName);
				try {
					return getFieldName(beanType, minifiedFieldName);
				} catch (SecurityException e) {
				} catch (NoSuchFieldException e) {
				}
			}
		}
		return null;
	}

	private static String getFieldName(Class<?> cls, String propertyId)
			throws SecurityException, NoSuchFieldException {
		for (java.lang.reflect.Field field1 : cls.getDeclaredFields()) {
			if (propertyId.equals(minifyFieldName(field1.getName()))) {
				return field1.getName();
			}
		}
		// Try super classes until we reach Object
		Class<?> superClass = cls.getSuperclass();
		if (superClass != null && superClass != Object.class) {
			return getFieldName(superClass, propertyId);
		} else {
			throw new NoSuchFieldException();
		}
	}

	/**
	 * Helper method for setting the data source directly using a bean. This
	 * method wraps the bean in a {@link BeanItem} and calls
	 * {@link #setItemDataSource(Item)}.
	 * <p>
	 * For null values, a null item is passed to
	 * {@link #setItemDataSource(Item)} to be properly clear fields.
	 * 
	 * @param bean
	 *            The bean to use as data source.
	 */
	public void setItemDataSource(T bean) {
		if (bean == null) {
			setItemDataSource((Item) null);
		} else {
			setItemDataSource(new BeanItem<T>(bean, beanType));
		}
	}

	@Override
	public void setItemDataSource(Item item) {
		if (item == null || (item instanceof BeanItem)) {
			super.setItemDataSource(item);
		} else {
			throw new RuntimeException(getClass().getSimpleName()
					+ " only supports BeanItems as item data source");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItem<T> getItemDataSource() {
		return (BeanItem<T>) super.getItemDataSource();
	}

	private void ensureNestedPropertyAdded(Object propertyId) {
		if (getItemDataSource() != null) {
			// The data source is set so the property must be found in the item.
			// If it is not we try to add it.
			try {
				getItemProperty(propertyId);
			} catch (BindException e) {
				// Not found, try to add a nested property;
				// BeanItem property ids are always strings so this is safe
				getItemDataSource().addNestedProperty((String) propertyId);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void bind(Field field, Object propertyId) {
		ensureNestedPropertyAdded(propertyId);
		super.bind(field, propertyId);
	}

	@SuppressWarnings({ "rawtypes", "hiding"})
	@Override
	public <T extends Field> T buildAndBind(String caption, Object propertyId,
			Class<T> fieldType) throws BindException {
		ensureNestedPropertyAdded(propertyId);
		return super.buildAndBind(caption, propertyId, fieldType);
	}

	@Override
	public void unbind(Field<?> field) throws BindException {
		super.unbind(field);

		BeanValidator removed = defaultValidators.remove(field);
		if (removed != null) {
			field.removeValidator(removed);
		}
	}

	@Override
	protected void configureField(Field<?> field) {
		super.configureField(field);
		// Add Bean validators if there are annotations
		if (isBeanValidationImplementationAvailable()
				&& !defaultValidators.containsKey(field)) {
			BeanValidator validator = new BeanValidator(validatorFactory,
					beanType, getPropertyId(field).toString());
			field.addValidator(validator);
			if (field.getLocale() != null) {
				validator.setLocale(field.getLocale());
			}
			defaultValidators.put(field, validator);
		}
	}

	/**
	 * Checks whether a bean validation implementation (e.g. Hibernate Validator
	 * or Apache Bean Validation) is available.
	 * 
	 * @return true if a JSR-303 bean validation implementation is available
	 */
	protected boolean isBeanValidationImplementationAvailable() {
		if (validator != null) {
			return true;
		}
		BundleContext bc = FrameworkUtil.getBundle(OSGiBeanFieldGroup.class)
				.getBundleContext();
		ref = bc.getServiceReference(ValidatorFactory.class);
		if (ref != null) {
			validatorFactory = bc.getService(ref);
		}
		return validatorFactory != null;
	}

	/**
	 * Releases all used OSGi services
	 */
	public void dispose() {
		if (ref != null) {
			BundleContext bc = FrameworkUtil
					.getBundle(OSGiBeanFieldGroup.class).getBundleContext();
			bc.ungetService(ref);
			ref = null;
		}
	}

	/**
	 * Convenience method to bind Fields from a given "field container" to a
	 * given bean with buffering disabled.
	 * <p>
	 * The returned {@link OSGiBeanFieldGroup} can be used for further
	 * configuration.
	 * 
	 * @see #bindFieldsBuffered(Object, Object)
	 * @see #bindMemberFields(Object)
	 * @since 7.2
	 * @param bean
	 *            the bean to be bound
	 * @param objectWithMemberFields
	 *            the class that contains {@link Field}s for bean properties
	 * @return the bean field group used to make binding
	 */
	public static <T> OSGiBeanFieldGroup<T> bindFieldsUnbuffered(T bean,
			Object objectWithMemberFields) {
		return createAndBindFields(bean, objectWithMemberFields, false);
	}

	/**
	 * Convenience method to bind Fields from a given "field container" to a
	 * given bean with buffering enabled.
	 * <p>
	 * The returned {@link OSGiBeanFieldGroup} can be used for further
	 * configuration.
	 * 
	 * @see #bindFieldsUnbuffered(Object, Object)
	 * @see #bindMemberFields(Object)
	 * @since 7.2
	 * @param bean
	 *            the bean to be bound
	 * @param objectWithMemberFields
	 *            the class that contains {@link Field}s for bean properties
	 * @return the bean field group used to make binding
	 */
	public static <T> OSGiBeanFieldGroup<T> bindFieldsBuffered(T bean,
			Object objectWithMemberFields) {
		return createAndBindFields(bean, objectWithMemberFields, true);
	}

	private static <T> OSGiBeanFieldGroup<T> createAndBindFields(T bean,
			Object objectWithMemberFields, boolean buffered) {
		@SuppressWarnings("unchecked")
		OSGiBeanFieldGroup<T> beanFieldGroup = new OSGiBeanFieldGroup<T>(
				(Class<T>) bean.getClass());
		beanFieldGroup.setItemDataSource(bean);
		beanFieldGroup.setBuffered(buffered);
		beanFieldGroup.bindMemberFields(objectWithMemberFields);
		return beanFieldGroup;
	}

}
