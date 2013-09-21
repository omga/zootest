package com.test.zoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;

@Stateless
public class ZooMain implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Animals> animals=new ArrayList<Animals>();

	public ZooMain(){
		Animals an=new Animals();an.setName("Joy");an.setAge(1);an.setCage("house");an.setKeeper("Andrey");an.setSpecies("cat");
		animals.add(an);
		PopulateAnimalList.populateList(animals);
	}

	public List<Animals> getAnimals(){
		return animals;
	}
	public void setAnimals(List<Animals> an){
		animals=an;
	}
	public void addAnimals(List<Animals> an){
		animals.addAll(an);
	}
	
}
