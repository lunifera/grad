package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Base;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.Valid;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "NUMBERED_ENTITY")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "NUMBERED_ENTITY")
@SuppressWarnings("all")
public abstract class NumberedEntity extends Base implements IEntity {
  @Column(name = "NUMBER")
  @Valid
  private String number;
  
  @Column(name = "DESCRIPTION")
  @Valid
  private String description;
  
  @Column(name = "LFP")
  @Valid
  private long lfp;
  
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
   * Returns the number property or <code>null</code> if not present.
   */
  public String getNumber() {
    checkDisposed();
    return this.number;
  }
  
  /**
   * Sets the number property to this instance.
   */
  public void setNumber(final String number) {
    checkDisposed();
    this.number = number;
  }
  
  /**
   * Returns the description property or <code>null</code> if not present.
   */
  public String getDescription() {
    checkDisposed();
    return this.description;
  }
  
  /**
   * Sets the description property to this instance.
   */
  public void setDescription(final String description) {
    checkDisposed();
    this.description = description;
  }
  
  /**
   * Returns the lfp property or <code>null</code> if not present.
   */
  public long getLfp() {
    checkDisposed();
    return this.lfp;
  }
  
  /**
   * Sets the lfp property to this instance.
   */
  public void setLfp(final long lfp) {
    checkDisposed();
    this.lfp = lfp;
  }
}
