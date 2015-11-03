package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Degree;
import com.lunifera.graduate.entities.Person;
import com.lunifera.graduate.entities.SubjectOfStudy;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "STUDENT")
@DiscriminatorValue(value = "STUDENT")
@SuppressWarnings("all")
public class Student extends Person implements IEntity {
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SUBJECT_OF_STUDY_ID")
  private SubjectOfStudy subjectOfStudy;
  
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FINAL_DEGREE_ID")
  private Degree finalDegree;
  
  @Column(name = "COMPLETED")
  @Valid
  private boolean completed;
  
  @Column(name = "COMPLETION_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date completionDate;
  
  @Column(name = "COMPLETION_CITY")
  @Valid
  private String completionCity;
  
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
   * Returns the subjectOfStudy property or <code>null</code> if not present.
   */
  public SubjectOfStudy getSubjectOfStudy() {
    checkDisposed();
    return this.subjectOfStudy;
  }
  
  /**
   * Sets the subjectOfStudy property to this instance.
   */
  public void setSubjectOfStudy(final SubjectOfStudy subjectOfStudy) {
    checkDisposed();
    this.subjectOfStudy = subjectOfStudy;
  }
  
  /**
   * Returns the finalDegree property or <code>null</code> if not present.
   */
  public Degree getFinalDegree() {
    checkDisposed();
    return this.finalDegree;
  }
  
  /**
   * Sets the finalDegree property to this instance.
   */
  public void setFinalDegree(final Degree finalDegree) {
    checkDisposed();
    this.finalDegree = finalDegree;
  }
  
  /**
   * Returns the completed property or <code>null</code> if not present.
   */
  public boolean getCompleted() {
    checkDisposed();
    return this.completed;
  }
  
  /**
   * Sets the completed property to this instance.
   */
  public void setCompleted(final boolean completed) {
    checkDisposed();
    this.completed = completed;
  }
  
  /**
   * Returns the completionDate property or <code>null</code> if not present.
   */
  public Date getCompletionDate() {
    checkDisposed();
    return this.completionDate;
  }
  
  /**
   * Sets the completionDate property to this instance.
   */
  public void setCompletionDate(final Date completionDate) {
    checkDisposed();
    this.completionDate = completionDate;
  }
  
  /**
   * Returns the completionCity property or <code>null</code> if not present.
   */
  public String getCompletionCity() {
    checkDisposed();
    return this.completionCity;
  }
  
  /**
   * Sets the completionCity property to this instance.
   */
  public void setCompletionCity(final String completionCity) {
    checkDisposed();
    this.completionCity = completionCity;
  }
}
