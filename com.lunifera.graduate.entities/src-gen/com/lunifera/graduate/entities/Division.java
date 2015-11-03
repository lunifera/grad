package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Address;
import com.lunifera.graduate.entities.NumberedEntity;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "DIVISION")
@DiscriminatorValue(value = "DIVISION")
@SuppressWarnings("all")
public class Division extends NumberedEntity implements IEntity {
  @Column(name = "INTERNAL")
  @Valid
  private boolean internal;
  
  @NotNull
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "ADDRESS1_STREET")), @AttributeOverride(name = "postalcode", column = @Column(name = "ADDRESS1_POSTALCODE")), @AttributeOverride(name = "city", column = @Column(name = "ADDRESS1_CITY")) })
  @Column(name = "ADDRESS1")
  @Valid
  private Address address1;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "ADDRESS2_STREET")), @AttributeOverride(name = "postalcode", column = @Column(name = "ADDRESS2_POSTALCODE")), @AttributeOverride(name = "city", column = @Column(name = "ADDRESS2_CITY")) })
  @Column(name = "ADDRESS2")
  @Valid
  private Address address2;
  
  /**
   * Checks whether the object is disposed.
   * @throws RuntimeException if the object is disposed.
   */
  private void checkDisposed() {
    if (isDisposed()) {
      throw new RuntimeException("Object already disposed: " + this);
    }
  }
  
  /**
   * Calling dispose will destroy that instance. The internal state will be 
   * set to 'disposed' and methods of that object must not be used anymore. 
   * Each call will result in runtime exceptions.<br/>
   * If this object keeps composition containments, these will be disposed too. 
   * So the whole composition containment tree will be disposed on calling this method.
   */
  @Dispose
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    super.dispose();
  }
  
  /**
   * Returns the internal property or <code>null</code> if not present.
   */
  public boolean getInternal() {
    checkDisposed();
    return this.internal;
  }
  
  /**
   * Sets the internal property to this instance.
   */
  public void setInternal(final boolean internal) {
    checkDisposed();
    this.internal = internal;
  }
  
  /**
   * Returns the address1 property or <code>null</code> if not present.
   */
  public Address getAddress1() {
    checkDisposed();
    return this.address1;
  }
  
  /**
   * Sets the address1 property to this instance.
   */
  public void setAddress1(final Address address1) {
    checkDisposed();
    this.address1 = address1;
  }
  
  /**
   * Returns the address2 property or <code>null</code> if not present.
   */
  public Address getAddress2() {
    checkDisposed();
    return this.address2;
  }
  
  /**
   * Sets the address2 property to this instance.
   */
  public void setAddress2(final Address address2) {
    checkDisposed();
    this.address2 = address2;
  }
}
