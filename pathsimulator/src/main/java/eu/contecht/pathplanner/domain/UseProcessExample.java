package eu.contecht.pathplanner.domain;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import desmoj.core.dist.ContDistUniform;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeInstant;


public class UseProcessExample extends Model  {

	ArrayList<Service> availableServices;
	ArrayList<UserType> userTypes;
	
	public UseProcessExample(Model owner, String modelName, boolean showInReport, 
            boolean showInTrace) {
		super(owner, modelName, showInReport, showInTrace);
	}
	

	public ArrayList<Service> getAvailableServices() {
		return availableServices;
	}

	public void setAvailableServices(ArrayList<Service> availableServices) {
		this.availableServices = availableServices;
	}

	public ArrayList<UserType> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(ArrayList<UserType> userTypes) {
		this.userTypes = userTypes;
	}


	@Override
	public String description() {
		return "Model for an examplary use process for a building";
	}

	@Override
	public void doInitialSchedules() {
		for (Service service : availableServices)  {
			service.activate();
		}
		
		for (UserType type : userTypes)  {
			UserGenerator gen = new UserGenerator(this, "generator for type " + type.getName(), false);
			gen.setType(type);
			gen.activate();
		}
		
	}

	@Override
	public void init() {
		
		availableServices = new ArrayList<Service>();
		userTypes = new ArrayList<UserType>();
		
		// TODO: replace this with a spring configuration
		Service service1 = new Service(this, "Service 1", true);
		service1.setName("Service 1");
		service1.setServiceTime(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		availableServices.add(service1);
		
		Service service2 = new Service(this, "Service 2", true);
		service2.setName("Service 2");
		service2.setServiceTime(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		availableServices.add(service2);
		
		Service service3 = new Service(this, "Service 3", true);
		service3.setName("Service 3");
		service3.setServiceTime(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		availableServices.add(service3);
		
		UserType type1 = new UserType();
		type1.setName("1 - 2 - 3");
		type1.setUserFrequency(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service1);
		services.add(service2);
		services.add(service3);
		type1.setServices(services);
		userTypes.add(type1);
		
		UserType type2 = new UserType();
		type2.setName("2 - 1 - 3");
		type2.setUserFrequency(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		services = new ArrayList<Service>();
		services.add(service2);
		services.add(service1);
		services.add(service3);
		type2.setServices(services);
		userTypes.add(type2);
		
		UserType type3 = new UserType();
		type3.setName("3 - 2 - 1");
		type3.setUserFrequency(new ContDistUniform(this, "ServiceTimeStream", 3.0, 7.0, true, false));
		services = new ArrayList<Service>();
		services.add(service3);
		services.add(service2);
		services.add(service1);
		type3.setServices(services);
		userTypes.add(type3);
	}
	
	public static void main(java.lang.String[] args) {

		   // create model and experiment
		UseProcessExample model = new UseProcessExample(null,
		                         "Simple User Process", true, true);
		   // null as first parameter because it is the main model and has no mastermodel

		   Experiment exp = new Experiment("ProcessExampleExperiment", 
		                         TimeUnit.SECONDS, TimeUnit.MINUTES, null);
		   // ATTENTION, since the name of the experiment is used in the names of the
		   // output files, you have to specify a string that's compatible with the
		   // filename constraints of your computer's operating system. The remaing three
		   // parameters specify the granularity of simulation time, default unit to
		   // display time and the time formatter to use (null yields a default formatter).
		   model.connectToExperiment(exp);
		   
		// set experiment parameters
		   exp.setShowProgressBar(true);  // display a progress bar (or not)
		   exp.stop(new TimeInstant(1500, TimeUnit.MINUTES));   // set end of simulation at 1500 minutes
		   exp.tracePeriod(new TimeInstant(0), new TimeInstant(100, TimeUnit.MINUTES));
		                                              // set the period of the trace
		   exp.debugPeriod(new TimeInstant(0), new TimeInstant(50, TimeUnit.MINUTES));   // and debug output
		      // ATTENTION!
		      // Don't use too long periods. Otherwise a huge HTML page will
		      // be created which crashes Netscape :-)
		   
		// ... main() continued

		   // start the experiment at simulation time 0.0
		   exp.start();

		   // --> now the simulation is running until it reaches its end criterion
		   // ...
		   // ...
		   // <-- afterwards, the main thread returns here

		   // generate the report (and other output files)
		   exp.report();

		   // stop all threads still alive and close all output files
		   exp.finish();
	}
}
