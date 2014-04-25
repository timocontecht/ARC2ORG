package eu.contecht.pathplanner.domain;

import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeSpan;

public class Service extends SimProcess {
	
	private String name; 
	private ContDist serviceTime;
	private Queue<User> userQueue;
	
	public Service(Model owner, String name, boolean showInTrace) {
	      super(owner, name, showInTrace);
	      userQueue = new Queue<User>(owner, name, true, true);
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContDist getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(ContDist serviceTime) {
		this.serviceTime = serviceTime;
	}

	
	public Queue<User> getUserQueue() {
		return userQueue;
	}

	@Override
	public void lifeCycle() {
		while (true)   {
			if (userQueue.isEmpty() )  {
				passivate();
			}
			else {
				hold(new TimeSpan(serviceTime.sample(), TimeUnit.MINUTES));
				User u = userQueue.first();
				u.beginNextService();
			}
		}
	}

	public void queueUser(User u) {
		userQueue.insert(u);
	}

}
