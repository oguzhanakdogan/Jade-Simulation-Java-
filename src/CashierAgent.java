import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CashierAgent extends Agent{

	private static final int CASH_METHOD = 1;
	private static final int CREDIT_CARD_METHOD = 2;
	@Override
	protected void setup() {
		System.out.println("Hi, I am cashier. You will pay for coffe here.");
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("cashier");
		sd.setName("JADE-ticket");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
		
		addBehaviour(new TicketBehaviour(this, 1000));
		
	}

	private class TicketBehaviour extends CyclicBehaviour{
		private int process_time ;
		public TicketBehaviour(Agent a, int processTime ) {
			super(a);
		}
		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				// CFP Message received. Process it
				String title = msg.getContent();
				if(Integer.parseInt(title) == 2) {
					System.out.println("Kredi kartý için " + msg.getSender().getName()+ "'a fiþ kesiliyor " );
				}
				else {
					System.out.println("Nakit ödeme için " + msg.getSender().getName()+ "'a fiþ kesiliyor " );

				}
				try {
					if(Integer.parseInt(title) == CASH_METHOD) {
						Thread.sleep(1000 / BaseAgent.FACTOR);
					}else {
						Thread.sleep(1500 / BaseAgent.FACTOR);
					}
					
					System.out.println( msg.getSender().getName() 
							+ " adlý kullanýcýnýn fiþi basýldý");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.INFORM);
				reply.setContent("Here is your ticket. Plese give it to barista");
				
				myAgent.send(reply);
				
			}
			else {
				block();
			}
			
		}
		
	}
}