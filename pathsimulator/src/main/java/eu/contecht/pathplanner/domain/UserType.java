package eu.contecht.pathplanner.domain;

import java.util.ArrayList;

import desmoj.core.dist.ContDist;

public class UserType {
	String name;
	ArrayList<Service> services;
	private ContDist userFrequency;
	
	public UserType()  {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	public ContDist getUserFrequency() {
		return userFrequency;
	}

	public void setUserFrequency(ContDist userFrequency) {
		this.userFrequency = userFrequency;
	}
	
	
	
}
