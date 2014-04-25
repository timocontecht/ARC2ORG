package eu.contecht.pathplanner.domain;

import java.util.ArrayList;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class User extends Entity {
	ArrayList<Service> services = new ArrayList<Service>();
	int currentServiceIndex = -1;
	UserType type;
	
	public User(Model owner, String name, boolean showInTrace) {
	      super(owner, name, showInTrace);
	   }

	// getter and setter functions
	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public void beginNextService() {
		currentServiceIndex++;
		if (currentServiceIndex != services.size())
			services.get(currentServiceIndex).queueUser(this);
	}
	
}
