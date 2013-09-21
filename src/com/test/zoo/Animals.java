package com.test.zoo;



import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Animals implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	private int age;
	private String species;
	private String cage;
	private String keeper;
	
	public Animals(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getCage() {
		return cage;
	}
	public void setCage(String cage) {
		this.cage = cage;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	@Override
	public String toString(){
		return name;
	}

}
