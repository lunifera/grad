package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Auditor;
import com.lunifera.graduate.entities.Division;
import com.lunifera.graduate.entities.Grading;
import com.lunifera.graduate.entities.NumberedEntity;
import com.lunifera.graduate.entities.Student;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

@Entity
@Table(name = "DISSERTATION")
@DiscriminatorValue(value = "DISSERTATION")
@SuppressWarnings("all")
public class Dissertation extends NumberedEntity implements IEntity {
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STUDENT_ID")
  private Student student;
  
  @NotNull
  @Size(max = 500)
  @Column(name = "TOPIC")
  @Valid
  private String topic;
  
  @Column(name = "ACCEPTED_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date acceptedDate;
  
  @Column(name = "SURRENDER_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date surrenderDate;
  
  @Column(name = "OPENING_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date openingDate;
  
  @Column(name = "PUBLIC_VIEW_FROM")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date publicViewFrom;
  
  @Column(name = "PUBLIC_VIEW_UNTIL")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date publicViewUntil;
  
  @Column(name = "OBJECTION_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date objectionDate;
  
  @Column(name = "CLOSING_SESSION_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date closingSessionDate;
  
  @Column(name = "DEPOSIT_COPY_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date depositCopyDate;
  
  @Column(name = "CERTIFICATE_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date certificateDate;
  
  @Column(name = "INVITATION_DIES_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date invitationDiesDate;
  
  @Column(name = "DIES_ACADEMICUS_DATE")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date diesAcademicusDate;
  
  @Column(name = "NOTE")
  @Valid
  private String note;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUDITOR1_ID")
  private Auditor auditor1;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "date", column = @Column(name = "AUDITORGRADE1_DATE")), @AttributeOverride(name = "grade", column = @Column(name = "AUDITORGRADE1_GRADE")) })
  @Column(name = "AUDITOR_GRADE1")
  @Valid
  private Grading auditorGrade1;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "date", column = @Column(name = "AUDITORGRADE2_DATE")), @AttributeOverride(name = "grade", column = @Column(name = "AUDITORGRADE2_GRADE")) })
  @Column(name = "AUDITOR_GRADE2")
  @Valid
  private Grading auditorGrade2;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUDITOR2_ID")
  private Auditor auditor2;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SELECTED_AUDITOR1_ID")
  private Auditor selectedAuditor1;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SELECTED_AUDITOR2_ID")
  private Auditor selectedAuditor2;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEAN_ID")
  private Auditor dean;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEAN_DIVISON_ID")
  private Division deanDivison;
  
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
   * Returns the student property or <code>null</code> if not present.
   */
  public Student getStudent() {
    checkDisposed();
    return this.student;
  }
  
  /**
   * Sets the student property to this instance.
   */
  public void setStudent(final Student student) {
    checkDisposed();
    this.student = student;
  }
  
  /**
   * Returns the topic property or <code>null</code> if not present.
   */
  public String getTopic() {
    checkDisposed();
    return this.topic;
  }
  
  /**
   * Sets the topic property to this instance.
   */
  public void setTopic(final String topic) {
    checkDisposed();
    this.topic = topic;
  }
  
  /**
   * Returns the acceptedDate property or <code>null</code> if not present.
   */
  public Date getAcceptedDate() {
    checkDisposed();
    return this.acceptedDate;
  }
  
  /**
   * Sets the acceptedDate property to this instance.
   */
  public void setAcceptedDate(final Date acceptedDate) {
    checkDisposed();
    this.acceptedDate = acceptedDate;
  }
  
  /**
   * Returns the surrenderDate property or <code>null</code> if not present.
   */
  public Date getSurrenderDate() {
    checkDisposed();
    return this.surrenderDate;
  }
  
  /**
   * Sets the surrenderDate property to this instance.
   */
  public void setSurrenderDate(final Date surrenderDate) {
    checkDisposed();
    this.surrenderDate = surrenderDate;
  }
  
  /**
   * Returns the openingDate property or <code>null</code> if not present.
   */
  public Date getOpeningDate() {
    checkDisposed();
    return this.openingDate;
  }
  
  /**
   * Sets the openingDate property to this instance.
   */
  public void setOpeningDate(final Date openingDate) {
    checkDisposed();
    this.openingDate = openingDate;
  }
  
  /**
   * Returns the publicViewFrom property or <code>null</code> if not present.
   */
  public Date getPublicViewFrom() {
    checkDisposed();
    return this.publicViewFrom;
  }
  
  /**
   * Sets the publicViewFrom property to this instance.
   */
  public void setPublicViewFrom(final Date publicViewFrom) {
    checkDisposed();
    this.publicViewFrom = publicViewFrom;
  }
  
  /**
   * Returns the publicViewUntil property or <code>null</code> if not present.
   */
  public Date getPublicViewUntil() {
    checkDisposed();
    return this.publicViewUntil;
  }
  
  /**
   * Sets the publicViewUntil property to this instance.
   */
  public void setPublicViewUntil(final Date publicViewUntil) {
    checkDisposed();
    this.publicViewUntil = publicViewUntil;
  }
  
  /**
   * Returns the objectionDate property or <code>null</code> if not present.
   */
  public Date getObjectionDate() {
    checkDisposed();
    return this.objectionDate;
  }
  
  /**
   * Sets the objectionDate property to this instance.
   */
  public void setObjectionDate(final Date objectionDate) {
    checkDisposed();
    this.objectionDate = objectionDate;
  }
  
  /**
   * Returns the closingSessionDate property or <code>null</code> if not present.
   */
  public Date getClosingSessionDate() {
    checkDisposed();
    return this.closingSessionDate;
  }
  
  /**
   * Sets the closingSessionDate property to this instance.
   */
  public void setClosingSessionDate(final Date closingSessionDate) {
    checkDisposed();
    this.closingSessionDate = closingSessionDate;
  }
  
  /**
   * Returns the depositCopyDate property or <code>null</code> if not present.
   */
  public Date getDepositCopyDate() {
    checkDisposed();
    return this.depositCopyDate;
  }
  
  /**
   * Sets the depositCopyDate property to this instance.
   */
  public void setDepositCopyDate(final Date depositCopyDate) {
    checkDisposed();
    this.depositCopyDate = depositCopyDate;
  }
  
  /**
   * Returns the certificateDate property or <code>null</code> if not present.
   */
  public Date getCertificateDate() {
    checkDisposed();
    return this.certificateDate;
  }
  
  /**
   * Sets the certificateDate property to this instance.
   */
  public void setCertificateDate(final Date certificateDate) {
    checkDisposed();
    this.certificateDate = certificateDate;
  }
  
  /**
   * Returns the invitationDiesDate property or <code>null</code> if not present.
   */
  public Date getInvitationDiesDate() {
    checkDisposed();
    return this.invitationDiesDate;
  }
  
  /**
   * Sets the invitationDiesDate property to this instance.
   */
  public void setInvitationDiesDate(final Date invitationDiesDate) {
    checkDisposed();
    this.invitationDiesDate = invitationDiesDate;
  }
  
  /**
   * Returns the diesAcademicusDate property or <code>null</code> if not present.
   */
  public Date getDiesAcademicusDate() {
    checkDisposed();
    return this.diesAcademicusDate;
  }
  
  /**
   * Sets the diesAcademicusDate property to this instance.
   */
  public void setDiesAcademicusDate(final Date diesAcademicusDate) {
    checkDisposed();
    this.diesAcademicusDate = diesAcademicusDate;
  }
  
  /**
   * Returns the note property or <code>null</code> if not present.
   */
  public String getNote() {
    checkDisposed();
    return this.note;
  }
  
  /**
   * Sets the note property to this instance.
   */
  public void setNote(final String note) {
    checkDisposed();
    this.note = note;
  }
  
  /**
   * Returns the auditor1 property or <code>null</code> if not present.
   */
  public Auditor getAuditor1() {
    checkDisposed();
    return this.auditor1;
  }
  
  /**
   * Sets the auditor1 property to this instance.
   */
  public void setAuditor1(final Auditor auditor1) {
    checkDisposed();
    this.auditor1 = auditor1;
  }
  
  /**
   * Returns the auditorGrade1 property or <code>null</code> if not present.
   */
  public Grading getAuditorGrade1() {
    checkDisposed();
    return this.auditorGrade1;
  }
  
  /**
   * Sets the auditorGrade1 property to this instance.
   */
  public void setAuditorGrade1(final Grading auditorGrade1) {
    checkDisposed();
    this.auditorGrade1 = auditorGrade1;
  }
  
  /**
   * Returns the auditorGrade2 property or <code>null</code> if not present.
   */
  public Grading getAuditorGrade2() {
    checkDisposed();
    return this.auditorGrade2;
  }
  
  /**
   * Sets the auditorGrade2 property to this instance.
   */
  public void setAuditorGrade2(final Grading auditorGrade2) {
    checkDisposed();
    this.auditorGrade2 = auditorGrade2;
  }
  
  /**
   * Returns the auditor2 property or <code>null</code> if not present.
   */
  public Auditor getAuditor2() {
    checkDisposed();
    return this.auditor2;
  }
  
  /**
   * Sets the auditor2 property to this instance.
   */
  public void setAuditor2(final Auditor auditor2) {
    checkDisposed();
    this.auditor2 = auditor2;
  }
  
  /**
   * Returns the selectedAuditor1 property or <code>null</code> if not present.
   */
  public Auditor getSelectedAuditor1() {
    checkDisposed();
    return this.selectedAuditor1;
  }
  
  /**
   * Sets the selectedAuditor1 property to this instance.
   */
  public void setSelectedAuditor1(final Auditor selectedAuditor1) {
    checkDisposed();
    this.selectedAuditor1 = selectedAuditor1;
  }
  
  /**
   * Returns the selectedAuditor2 property or <code>null</code> if not present.
   */
  public Auditor getSelectedAuditor2() {
    checkDisposed();
    return this.selectedAuditor2;
  }
  
  /**
   * Sets the selectedAuditor2 property to this instance.
   */
  public void setSelectedAuditor2(final Auditor selectedAuditor2) {
    checkDisposed();
    this.selectedAuditor2 = selectedAuditor2;
  }
  
  /**
   * Returns the dean property or <code>null</code> if not present.
   */
  public Auditor getDean() {
    checkDisposed();
    return this.dean;
  }
  
  /**
   * Sets the dean property to this instance.
   */
  public void setDean(final Auditor dean) {
    checkDisposed();
    this.dean = dean;
  }
  
  /**
   * Returns the deanDivison property or <code>null</code> if not present.
   */
  public Division getDeanDivison() {
    checkDisposed();
    return this.deanDivison;
  }
  
  /**
   * Sets the deanDivison property to this instance.
   */
  public void setDeanDivison(final Division deanDivison) {
    checkDisposed();
    this.deanDivison = deanDivison;
  }
}
