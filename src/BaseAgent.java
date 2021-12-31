import org.apache.commons.math3.distribution.ExponentialDistribution;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.CyclicBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class BaseAgent extends Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int creationTimeOfCustomers = 0;
	int counter = 0;
	private AgentController  AC = null;
	ContainerController container1 = null;
	public static final String[] coffeTypes = {"latte", "frappucino", 
			"hot drip", "iced coffee"};
	
	public static int FACTOR = 100;
	
	
	
	@Override
	protected void setup() {
		final AssignmentGUI ag = new AssignmentGUI();
		System.out.println("hello Base agent created! ");
		final String[] arr = {"mantar" };
		startAgent1("Container1", "localhost", "cashier", "CashierAgent", null);
		startAgent1("Container1", "localhost", "barista", "BaristaAgent", null);
		addBehaviour(new CyclicBehaviour(this) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				// TODO Auto-generated method stub
				ExponentialDistribution distribution = new ExponentialDistribution(120000);
				try {
					Thread.sleep((long) distribution.sample() / BaseAgent.FACTOR);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Customer c = new Customer(System.currentTimeMillis(), 
						counter, true, ag,false, false, false, "nothing", "nothing");
				
				startEmployeeAgent("Container1","localhost",String.valueOf(counter),"CoffeBuyerAgent",c);

				c.AddMeGui();
				counter++;
				System.out.println("yaratýldý");
			}
		});
	}
	
	
	
	
	private void startEmployeeAgent(String containerName, String host, String agentName, String agentClass, Customer c) {

		//Get the JADE runtime interface (singleton)
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		//Create a Profile, where the launch arguments are stored
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "TestContainer");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		//create a non-main agent container
		
		ContainerController container = null;
		if (container1 == null) {
			container = runtime.createAgentContainer(profile);
			container1 = container;
		} else {
			container = container1;
		}
		try {
		        AgentController ag = container.createNewAgent(agentName, 
		        									agentClass, 
		                                      new Object[] {c});//arguments
		        ag.start();
		} catch (StaleProxyException e) {
		    e.printStackTrace();
		}
		
	}
	
	private void startAgent1(String containerName, String host, String agentName, String agentClass, Customer c) {

		//Get the JADE runtime interface (singleton)
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		//Create a Profile, where the launch arguments are stored
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "TestContainer");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		//create a non-main agent container
		
		ContainerController container = null;
		if (container1 == null) {
			container = runtime.createAgentContainer(profile);
			container1 = container;
		} else {
			container = container1;
		}
		try {
		        AgentController ag = container.createNewAgent(agentName, 
		        									agentClass, 
		                                      new Object[] {c});//arguments
		        ag.start();
		} catch (StaleProxyException e) {
		    e.printStackTrace();
		}
		
	}
	

}