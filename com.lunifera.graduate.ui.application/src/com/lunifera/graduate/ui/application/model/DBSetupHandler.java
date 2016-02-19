/**
 * Copyright (c) 2015 - 2016, Lunifera GmbH (Wien)
 * All rights reserved. 
 *
 * Contributors:
 *         Florian Pirchner - Initial implementation
 */
package com.lunifera.graduate.ui.application.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import com.lunifera.graduate.entities.Address;
import com.lunifera.graduate.entities.Country;
import com.lunifera.graduate.entities.Degree;
import com.lunifera.graduate.entities.Student;
import com.lunifera.graduate.entities.Title;

public class DBSetupHandler {

	private Student s1;
	private Country austria;
	private Country germany;
	private Degree drMedVet;
	private Degree drMed;
	private Title titleDr;
	private Title titleMag;
	private Title titleProf;
	private int index;

	@Execute
	public void execute(EntityManager em) {
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();

			createCountries(em);
			createDegrees(em);
			createTitles(em);

			createPersons(em);

		} finally {
			txn.commit();
		}
	}

	protected void createPersons(EntityManager em) {

		String[] firstNames = new String[] { "Florian", "Klemens", "Bernhard",
				"Lars", "Susanne", "Jörg" };
		String[] lastNames = new String[] { "Pirchner", "Edler", "Schuster",
				"Vogel", "Riegel" };
		String[] cities = new String[] { "Innsbruck", "Wien", "Maurach",
				"Neunkirchen", "Unterstinkenbrunn" };
		String[] streets = new String[] { "Wagramer Straße", "Seestadtstraße",
				"Am Stein", "Ebener Straße", "Bergisel" };
		String[] postcodes = new String[] { "6212", "2880", "1220", "2640",
				"1010" };

		for (int i = 1; i < 25; i++) {
			s1 = new Student();
			s1.setLfp(index++);
			s1.setTitle(titleDr);
			s1.setBirthCountry(austria);
			s1.setBirthday(createBirthdate((int) (Math.random() * 28 + 1),
					(int) (Math.random() * 12 + 1),
					(int) (Math.random() * 25 + 1970)));
			s1.setFirstName(firstNames[(int) (Math.random() * firstNames.length)]);
			s1.setLastName(lastNames[(int) (Math.random() * lastNames.length)]);
			s1.setBirthName(lastNames[(int) (Math.random() * lastNames.length)]);
			s1.setBirthPlace(cities[(int) (Math.random() * cities.length)]);
			s1.setCellPhone("+43 650 999" + (int) (Math.random() * 9999));
			s1.setFinalDegree(drMedVet);

			Address homeAddress = new Address();
			homeAddress.setCity(cities[(int) (Math.random() * cities.length)]);
			homeAddress
					.setStreet(streets[(int) (Math.random() * streets.length)]
							+ " " + (int) (Math.random() * 99));
			homeAddress
					.setPostalcode(postcodes[(int) (Math.random() * postcodes.length)]);
			s1.setHomeAddress(homeAddress);
			Address semesterAddress = new Address();
			semesterAddress
					.setCity(cities[(int) (Math.random() * cities.length)]);
			semesterAddress
					.setStreet(streets[(int) (Math.random() * streets.length)]
							+ " " + (int) (Math.random() * 99));
			semesterAddress
					.setPostalcode(postcodes[(int) (Math.random() * postcodes.length)]);
			s1.setSemesterAddress(semesterAddress);
			em.persist(s1);
		}
	}

	protected void createTitles(EntityManager em) {
		titleDr = new Title();
		titleDr.setLfp(index++);
		titleDr.setNumber("Dr " + index);
		titleDr.setDescription("Doktor " + index);
		em.persist(titleDr);

		titleMag = new Title();
		titleMag.setLfp(index++);
		titleMag.setNumber("Mag " + index);
		titleMag.setDescription("Magister " + index);
		em.persist(titleMag);

		titleProf = new Title();
		titleProf.setLfp(index++);
		titleProf.setNumber("Prof " + index);
		titleProf.setDescription("Professor " + index);
		em.persist(titleProf);
	}

	private void createDegrees(EntityManager em) {
		drMedVet = new Degree();
		drMedVet.setNumber("Dr Med Vet");
		drMedVet.setDescription("Doktor Med Vet");
		em.persist(drMedVet);

		drMed = new Degree();
		drMed.setNumber("Dr med");
		drMed.setDescription("Doktor Med");
		em.persist(drMed);
	}

	private Date createBirthdate(int day, int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}

	private void createCountries(EntityManager em) {
		austria = new Country();
		austria.setLfp(index++);
		austria.setNumber("AT " + index);
		austria.setIsoCode("AT");
		austria.setDescription("Austria");
		em.persist(austria);

		germany = new Country();
		germany.setLfp(index++);
		germany.setNumber("DE " + index);
		germany.setIsoCode("DE");
		germany.setDescription("Germany");
		em.persist(germany);
	}

	@CanExecute
	public boolean canExeute() {
		return true;
	}

}
