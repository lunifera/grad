package com.lunifera.graduate.ui.application.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.lunifera.runtime.web.vaadin.common.resource.IResourceProvider;
import org.semanticsoft.vaaclipse.publicapi.resources.BundleResource;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ExplorerView {

	private static final String VIEW = "View";
	private static final String EXPLORER_VIEW = "explorerView";
	private static final String NAME_PROP = "name";
	private static final String ICON_PROP = "icon";
	private static final String OBJECT_PROP = "object";
	final private static String CATEGORY_TAG = "categoryTag:";
	final private static int CATEGORY_TAG_LENGTH = CATEGORY_TAG.length();

	@Inject
	private IEclipseContext eclipseContext;
	@Inject
	private IEventBroker eventBroker;
	@Inject
	MApplication mApplication;
	@Inject
	private MPart mPart;
	@Inject
	private VerticalLayout parentLayout;
	@Inject
	private org.eclipse.e4.core.services.events.IEventBroker e4EventBroker;
	@Inject
	private IResourceProvider resourceProvider;
	@Inject
	private EPartService partService;

	private Tree tree;

	@SuppressWarnings("serial")
	@PostConstruct
	public void setup() {
		tree = new Tree();
		tree.setSizeFull();
		tree.setContainerDataSource(createDataSource(mApplication));
		tree.setItemCaptionPropertyId(NAME_PROP);
		tree.setItemIconPropertyId(ICON_PROP);

		parentLayout.setMargin(true);
		parentLayout.setSpacing(true);
		parentLayout.addComponent(tree);

		tree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(final ItemClickEvent event) {
				if (event.getButton() == MouseButton.LEFT) {
					Item item = event.getItem();
					Object object = item.getItemProperty(OBJECT_PROP)
							.getValue();
					if (object instanceof MPartDescriptor) {
						String id = ((MPartDescriptor) object).getElementId();
						partService.showPart(id, PartState.ACTIVATE);
					}
				}
			}
		});
	}

	private HierarchicalContainer createDataSource(MApplication mApp) {
		HierarchicalContainer data = new HierarchicalContainer();
		data.addContainerProperty(NAME_PROP, String.class, "No Name");
		data.addContainerProperty(ICON_PROP, ThemeResource.class, null);
		data.addContainerProperty(OBJECT_PROP, Object.class, null);

		List<MPartDescriptor> descriptors = mApp.getDescriptors();
		Set<String> categoryTags = new HashSet<String>();

		for (MPartDescriptor descriptor : descriptors) {
			List<String> tags = descriptor.getTags();
			if (!tags.contains(EXPLORER_VIEW)) {
				continue;
			}
			String category = null;
			boolean isView = false;
			for (String tag : tags) {
				if (tag.equals(VIEW))
					isView = true;
				else if (tag.startsWith(CATEGORY_TAG)) {
					category = tag.substring(CATEGORY_TAG_LENGTH);
				}
			}
			if (isView) {
				Item descriptorItem = data.addItem(descriptor);
				data.setChildrenAllowed(descriptor, false);
				setupDescriptorItem(descriptorItem, descriptor);

				if (category != null) {
					category = category.trim();
					if (!category.isEmpty()) {
						if (!categoryTags.contains(category)) {
							categoryTags.add(category);
							Item categoryItem = data.addItem(category);
							setupCategoryItem(categoryItem, category);
						}

						data.setParent(descriptor, category);
					}
				}
			}
		}

		return data;
	}

	@SuppressWarnings("unchecked")
	private void setupCategoryItem(Item categoryItem, String category) {
		categoryItem.getItemProperty(NAME_PROP).setValue(category);
		categoryItem
				.getItemProperty(ICON_PROP)
				.setValue(
						BundleResource
								.valueOf("platform:/plugin/org.semanticsoft.vaaclipse.resources/VAADIN/themes/vaaclipse_default_theme/img/folder.png"));
		categoryItem.getItemProperty(OBJECT_PROP).setValue(category);
	}

	@SuppressWarnings("unchecked")
	private void setupDescriptorItem(Item descriptorItem,
			MPartDescriptor descriptor) {
		descriptorItem.getItemProperty(NAME_PROP).setValue(
				descriptor.getLabel());
		if (descriptor.getIconURI() != null
				&& descriptor.getIconURI().trim().length() > 0)
			descriptorItem.getItemProperty(ICON_PROP).setValue(
					BundleResource.valueOf(descriptor.getIconURI()));
		descriptorItem.getItemProperty(OBJECT_PROP).setValue(descriptor);
	}
}
