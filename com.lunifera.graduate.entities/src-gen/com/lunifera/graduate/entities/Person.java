package com.lunifera.graduate.entities;

import com.lunifera.graduate.entities.Address;
import com.lunifera.graduate.entities.Country;
import com.lunifera.graduate.entities.Gender;
import com.lunifera.graduate.entities.NumberedEntity;
import com.lunifera.graduate.entities.Title;
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
@Table(name = "PERSON")
@DiscriminatorValue(value = "PERSON")
@SuppressWarnings("all")
public abstract class Person extends NumberedEntity implements IEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TITLE_ID")
  private Title title;
  
  @Column(name = "GENDER")
  @Valid
  private Gender gender;
  
  @NotNull
  @Size(min = 3, max = 25)
  @Column(name = "FIRST_NAME")
  @Valid
  private String firstName;
  
  @Column(name = "FIRST_NAME2")
  @Valid
  private String firstName2;
  
  @NotNull
  @Size(min = 3, max = 25)
  @Column(name = "LAST_NAME")
  @Valid
  private String lastName;
  
  @Column(name = "BIRTH_NAME")
  @Valid
  private String birthName;
  
  @Column(name = "BIRTHDAY")
  @Temporal(value = TemporalType.DATE)
  @Valid
  private Date birthday;
  
  @Column(name = "BIRTH_PLACE")
  @Valid
  private String birthPlace;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BIRTH_COUNTRY_ID")
  private Country birthCountry;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "NATIONALITY_ID")
  private Country nationality;
  
  @Column(name = "PHONE")
  @Valid
  private String phone;
  
  @Column(name = "CELL_PHONE")
  @Valid
  private String cellPhone;
  
  @NotNull
  @Size(min = 3, max = 45)
  @Column(name = "MAIL_ADDRESS")
  @Valid
  private String mailAddress;
  
  @NotNull
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "HOMEADDRESS_STREET")), @AttributeOverride(name = "postalcode", column = @Column(name = "HOMEADDRESS_POSTALCODE")), @AttributeOverride(name = "city", column = @Column(name = "HOMEADDRESS_CITY")) })
  @Column(name = "HOME_ADDRESS")
  @Valid
  private Address homeAddress;
  
  @NotNull
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "SEMESTERADDRESS_STREET")), @AttributeOverride(name = "postalcode", column = @Column(name = "SEMESTERADDRESS_POSTALCODE")), @AttributeOverride(name = "city", column = @Column(name = "SEMESTERADDRESS_CITY")) })
  @Column(name = "SEMESTER_ADDRESS")
  @Valid
  private Address semesterAddress;
  
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
   * Returns the title property or <code>null</code> if not present.
   */
  public Title getTitle() {
    checkDisposed();
    return this.title;
  }
  
  /**
   * Sets the title property to this instance.
   */
  public void setTitle(final Title title) {
    checkDisposed();
    this.title = title;
  }
  
  /**
   * Returns the gender property or <code>null</code> if not present.
   */
  public Gender getGender() {
    checkDisposed();
    return this.gender;
  }
  
  /**
   * Sets the gender property to this instance.
   */
  public void setGender(final Gender gender) {
    checkDisposed();
    this.gender = gender;
  }
  
  /**
   * Returns the firstName property or <code>null</code> if not present.
   */
  public String getFirstName() {
    checkDisposed();
    return this.firstName;
  }
  
  /**
   * Sets the firstName property to this instance.
   */
  public void setFirstName(final String firstName) {
    checkDisposed();
    this.firstName = firstName;
  }
  
  /**
   * Returns the firstName2 property or <code>null</code> if not present.
   */
  public String getFirstName2() {
    checkDisposed();
    return this.firstName2;
  }
  
  /**
   * Sets the firstName2 property to this instance.
   */
  public void setFirstName2(final String firstName2) {
    checkDisposed();
    this.firstName2 = firstName2;
  }
  
  /**
   * Returns the lastName property or <code>null</code> if not present.
   */
  public String getLastName() {
    checkDisposed();
    return this.lastName;
  }
  
  /**
   * Sets the lastName property to this instance.
   */
  public void setLastName(final String lastName) {
    checkDisposed();
    this.lastName = lastName;
  }
  
  /**
   * Returns the birthName property or <code>null</code> if not present.
   */
  public String getBirthName() {
    checkDisposed();
    return this.birthName;
  }
  
  /**
   * Sets the birthName property to this instance.
   */
  public void setBirthName(final String birthName) {
    checkDisposed();
    this.birthName = birthName;
  }
  
  /**
   * Returns the birthday property or <code>null</code> if not present.
   */
  public Date getBirthday() {
    checkDisposed();
    return this.birthday;
  }
  
  /**
   * Sets the birthday property to this instance.
   */
  public void setBirthday(final Date birthday) {
    checkDisposed();
    this.birthday = birthday;
  }
  
  /**
   * Returns the birthPlace property or <code>null</code> if not present.
   */
  public String getBirthPlace() {
    checkDisposed();
    return this.birthPlace;
  }
  
  /**
   * Sets the birthPlace property to this instance.
   */
  public void setBirthPlace(final String birthPlace) {
    checkDisposed();
    this.birthPlace = birthPlace;
  }
  
  /**
   * Returns the birthCountry property or <code>null</code> if not present.
   */
  public Country getBirthCountry() {
    checkDisposed();
    return this.birthCountry;
  }
  
  /**
   * Sets the birthCountry property to this instance.
   */
  public void setBirthCountry(final Country birthCountry) {
    checkDisposed();
    this.birthCountry = birthCountry;
  }
  
  /**
   * Returns the nationality property or <code>null</code> if not present.
   */
  public Country getNationality() {
    checkDisposed();
    return this.nationality;
  }
  
  /**
   * Sets the nationality property to this instance.
   */
  public void setNationality(final Country nationality) {
    checkDisposed();
    this.nationality = nationality;
  }
  
  /**
   * Returns the phone property or <code>null</code> if not present.
   */
  public String getPhone() {
    checkDisposed();
    return this.phone;
  }
  
  /**
   * Sets the phone property to this instance.
   */
  public void setPhone(final String phone) {
    checkDisposed();
    this.phone = phone;
  }
  
  /**
   * Returns the cellPhone property or <code>null</code> if not present.
   */
  public String getCellPhone() {
    checkDisposed();
    return this.cellPhone;
  }
  
  /**
   * Sets the cellPhone property to this instance.
   */
  public void setCellPhone(final String cellPhone) {
    checkDisposed();
    this.cellPhone = cellPhone;
  }
  
  /**
   * Returns the mailAddress property or <code>null</code> if not present.
   */
  public String getMailAddress() {
    checkDisposed();
    return this.mailAddress;
  }
  
  /**
   * Sets the mailAddress property to this instance.
   */
  public void setMailAddress(final String mailAddress) {
    checkDisposed();
    this.mailAddress = mailAddress;
  }
  
  /**
   * Returns the homeAddress property or <code>null</code> if not present.
   */
  public Address getHomeAddress() {
    checkDisposed();
    return this.homeAddress;
  }
  
  /**
   * Sets the homeAddress property to this instance.
   */
  public void setHomeAddress(final Address homeAddress) {
    checkDisposed();
    this.homeAddress = homeAddress;
  }
  
  /**
   * Returns the semesterAddress property or <code>null</code> if not present.
   */
  public Address getSemesterAddress() {
    checkDisposed();
    return this.semesterAddress;
  }
  
  /**
   * Sets the semesterAddress property to this instance.
   */
  public void setSemesterAddress(final Address semesterAddress) {
    checkDisposed();
    this.semesterAddress = semesterAddress;
  }
}
