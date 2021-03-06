package com.hire.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="position", discriminatorType=DiscriminatorType.STRING, length=20)
public class Employee extends Person {
	@Column(unique=true)
	private String identifiant;
	private String password;
	
	public Employee() {
		
	}
	
	public Employee(String identifiant, String password) {
		this.identifiant = identifiant;
		this.password = password;
	}
	@Transient
	public String getDecriminatorValue() {
		return this.getClass().getName();
	}

	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [identifiant=" + identifiant + ", password=" + password + "]";
	}
	

}
