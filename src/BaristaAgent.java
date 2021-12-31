import java.util.concurrent.CyclicBarrier;

import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.genetics.CycleCrossover;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BaristaAgent extends Agent{

	
	@Override
	protected void setup() {
		System.out.println("hello. I am barista working here!");
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("coffe-selling");
		sd.setName("JADE-coffe-trading");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		addBehaviour(new PrepareCoffeBehaviour());
		
	}
	
	
	
	private class PrepareCoffeBehaviour extends CyclicBehaviour{

		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				// CFP Message received. Process it
				String title = msg.getContent();
				System.out.println(msg.getSender().getName() + " adlý müþteri " + title +" istiyor " );
				
				
				try {
					NormalDistribution latteDist = new NormalDistribution(9465, 6400);
					WeibullDistribution hot_drip  = new WeibullDistribution(800, 32500);
					WeibullDistribution iced_coffee  = new WeibullDistribution(2000, 27600);
					GammaDistribution frappucino = new GammaDistribution(7800, 29000);
					

					if(title == BaseAgent.coffeTypes[0] ) 
					{	
						Thread.sleep((long) latteDist.sample() / BaseAgent.FACTOR);	
					}
					else if(title == BaseAgent.coffeTypes[1]) 
					{
						
						//Thread.sleep((long)frappucino.sample() / BaseAgent.FACTOR);
					}
					else if(title == BaseAgent.coffeTypes[2]) 
					{
						Thread.sleep((long)hot_drip.sample() / BaseAgent.FACTOR);
					}
					else 
					{
						Thread.sleep((long)iced_coffee.sample() / BaseAgent.FACTOR);
					}
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.INFORM);
				reply.setContent("Coffee is ready");
				myAgent.send(reply);
				System.out.println("Coffee is ready" );
			}
			else {
				block();
			}
			
		}

		
	}
}