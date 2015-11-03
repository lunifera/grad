package com.lunifera.graduate.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.Valid;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@MappedSuperclass
@SuppressWarnings("all")
public class Base implements IEntity {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Id
  @Valid
  private String uuid = java.util.UUID.randomUUID().toString();
  
  @Version
  @Valid
  private long version;
  
  @Column(name = "CREATED_AT")
  @Temporal(value = TemporalType.TIMESTAMP)
  @Valid
  private Date createdAt;
  
  @Column(name = "CREATED_BY")
  @Valid
  private String createdBy;
  
  @Column(name = "UPDATED_AT")
  @Temporal(value = TemporalType.TIMESTAMP)
  @Valid
  private Date updatedAt;
  
  @Column(name = "UPDATET_BY")
  @Valid
  private String updatetBy;
  
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
   * Returns the uuid property or <code>null</code> if not present.
   */
  public String getUuid() {
    checkDisposed();
    return this.uuid;
  }
  
  /**
   * Sets the uuid property to this instance.
   */
  public void setUuid(final String uuid) {
    checkDisposed();
    this.uuid = uuid;
  }
  
  /**
   * Returns the version property or <code>null</code> if not present.
   */
  public long getVersion() {
    checkDisposed();
    return this.version;
  }
  
  /**
   * Sets the version property to this instance.
   */
  public void setVersion(final long version) {
    checkDisposed();
    this.version = version;
  }
  
  /**
   * Returns the createdAt property or <code>null</code> if not present.
   */
  public Date getCreatedAt() {
    checkDisposed();
    return this.createdAt;
  }
  
  /**
   * Sets the createdAt property to this instance.
   */
  public void setCreatedAt(final Date createdAt) {
    checkDisposed();
    this.createdAt = createdAt;
  }
  
  /**
   * Returns the createdBy property or <code>null</code> if not present.
   */
  public String getCreatedBy() {
    checkDisposed();
    return this.createdBy;
  }
  
  /**
   * Sets the createdBy property to this instance.
   */
  public void setCreatedBy(final String createdBy) {
    checkDisposed();
    this.createdBy = createdBy;
  }
  
  /**
   * Returns the updatedAt property or <code>null</code> if not present.
   */
  public Date getUpdatedAt() {
    checkDisposed();
    return this.updatedAt;
  }
  
  /**
   * Sets the updatedAt property to this instance.
   */
  public void setUpdatedAt(final Date updatedAt) {
    checkDisposed();
    this.updatedAt = updatedAt;
  }
  
  /**
   * Returns the updatetBy property or <code>null</code> if not present.
   */
  public String getUpdatetBy() {
    checkDisposed();
    return this.updatetBy;
  }
  
  /**
   * Sets the updatetBy property to this instance.
   */
  public void setUpdatetBy(final String updatetBy) {
    checkDisposed();
    this.updatetBy = updatetBy;
  }
  
  @PostPersist
  public void preCreate() {
    Date _date = new Date();
    this.createdAt = _date;
  }
  
  @PostUpdate
  public void preUpdate() {
    Date _date = new Date();
    this.updatedAt = _date;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Base other = (Base) obj;
    if (this.uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!this.uuid.equals(other.uuid))
      return false;
    return true;
  }
  
  @Override
  public int hashCode() {
     int prime = 31;
    int result = 1;
    result = prime * result + ((this.uuid== null) ? 0 : this.uuid.hashCode());
    return result;
  }
}
