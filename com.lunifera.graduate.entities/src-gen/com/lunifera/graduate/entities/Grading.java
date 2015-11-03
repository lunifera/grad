package com.lunifera.graduate.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import org.lunifera.dsl.common.datatypes.IBean;
import org.lunifera.runtime.common.annotations.Dispose;

@Embeddable
@SuppressWarnings("all")
public class Grading implements Serializable, IBean {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Basic
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date date;
  
  @Basic
  @Valid
  private double grade;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Dispose
  public boolean isDisposed() {
    return this.disposed;
  }
  
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
    disposed = true;
  }
  
  /**
   * Returns the date property or <code>null</code> if not present.
   */
  public Date getDate() {
    checkDisposed();
    return this.date;
  }
  
  /**
   * Sets the date property to this instance.
   */
  public void setDate(final Date date) {
    checkDisposed();
    this.date = date;
  }
  
  /**
   * Returns the grade property or <code>null</code> if not present.
   */
  public double getGrade() {
    checkDisposed();
    return this.grade;
  }
  
  /**
   * Sets the grade property to this instance.
   */
  public void setGrade(final double grade) {
    checkDisposed();
    this.grade = grade;
  }
}
