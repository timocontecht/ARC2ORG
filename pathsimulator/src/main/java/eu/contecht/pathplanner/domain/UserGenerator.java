package eu.contecht.pathplanner.domain;

import java.util.concurrent.TimeUnit;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;


public class UserGenerator extends SimProcess  {
	Model myModel;
	UserType type;
	
	public UserGenerator(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		myModel = owner;
	   }
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public void lifeCycle() {
		while (true)  {
			hold(new TimeSpan(type.getUserFrequency().sample(), TimeUnit.MINUTES));
			User u = new User(myModel, type.getName(), true);
			u.beginNextService();
		}
		
	}
}
