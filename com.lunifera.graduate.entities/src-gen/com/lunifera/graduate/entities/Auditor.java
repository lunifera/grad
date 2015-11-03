package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Division;
import com.lunifera.graduate.entities.Person;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "AUDITOR")
@DiscriminatorValue(value = "AUDITOR")
@SuppressWarnings("all")
public class Auditor extends Person implements IEntity {
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DIVISON_ID")
  private Division divison;
  
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
   * Returns the divison property or <code>null</code> if not present.
   */
  public Division getDivison() {
    checkDisposed();
    return this.divison;
  }
  
  /**
   * Sets the divison property to this instance.
   */
  public void setDivison(final Division divison) {
    checkDisposed();
    this.divison = divison;
  }
}
