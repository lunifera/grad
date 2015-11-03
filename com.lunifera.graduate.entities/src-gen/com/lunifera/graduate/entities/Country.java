package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.NumberedEntity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "COUNTRY")
@DiscriminatorValue(value = "COUNTRY")
@SuppressWarnings("all")
public class Country extends NumberedEntity implements IEntity {
  @Column(name = "ISO_CODE")
  @Valid
  private String isoCode;
  
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
   * Returns the isoCode property or <code>null</code> if not present.
   */
  public String getIsoCode() {
    checkDisposed();
    return this.isoCode;
  }
  
  /**
   * Sets the isoCode property to this instance.
   */
  public void setIsoCode(final String isoCode) {
    checkDisposed();
    this.isoCode = isoCode;
  }
}
