import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CoffeBuyerAgent extends Agent{

	private String targetCoffeName;
	 private AID baristaAgent = null;
	 private AID cashierAgent = null;
	 private Customer me;

	@Override
	protected void setup() {
		System.out.println("Hallo! Buyer-agent "+getAID().getName()+" is ready." 
 + " created at : "+ System.currentTimeMillis());
		
		// Get the title of the book to buy as a start-up argument
		Object[] args = {"latte"};
		me =(Customer) getArguments()[0];
		//me.UpdateGui(false);
		if (args != null && args.length > 0) {

			addBehaviour(new OneShotBehaviour(this) {
				
				@Override
				public void action() {
					DFAgentDescription[] result;
					
					DFAgentDescription baristaTemplate = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("coffe-selling");
					baristaTemplate.addServices(sd);
					
					DFAgentDescription cashierTemplate = new DFAgentDescription();
					ServiceDescription cashierSD = new ServiceDescription();
					cashierSD.setType("cashier");
					cashierTemplate.addServices(cashierSD);
					
					try {
						//Finds the 'cashier agent' and 'barista agent'
						result = DFService.search(myAgent, baristaTemplate); 
						System.out.println("Found the following barista agent: ");
						baristaAgent = result[0].getName();
						System.out.println(baristaAgent.getName());
						
						result = DFService.search(myAgent, cashierTemplate); 
						System.out.println("Found the following cashier agent: ");
						cashierAgent = result[0].getName();
						System.out.println(cashierAgent.getName());

						
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}
					
					myAgent.addBehaviour(new RequestPerformer());
					
				}
			});
		}
	}
	
	
	/**
	 * 
	 * @author MuSiC
	 *	This class is implemented for buyer to say the latte coffee
	 */
	
	private class RequestPerformer extends Behaviour {

		private MessageTemplate mt; // The template to receive replies
		private int step = 0;
		@Override
		public void action() {
			if(cashierAgent != null && baristaAgent != null) {
				switch (step) {
				case 0:
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					cfp.addReceiver(cashierAgent);
					//User choose payment method by random number. There is 
					// 2 percent chance to abandon to get any coffee.
					int randomNumber = (int)(Math.random() * 100);
					System.err.println("random number => " + randomNumber);
					if(randomNumber > 75 && randomNumber <= 98) {
						cfp.setContent("1");
						me.setPaymentMethod("Cash");
						me.UpdateGui();
					}else if(randomNumber <=75 ){
						cfp.setContent("2");
						me.setPaymentMethod("Credit Card");
						me.UpdateGui();

					}else {
						System.err.println("The customer refused to buy coffee.");
						me.setWaiting(false);
						me.UpdateGui();
						myAgent.doDelete();
						block();
						
					}
					if(!(randomNumber >= 80)) {
						cfp.setConversationId("ticket-trade");
						
						
						cfp.setReplyWith("INFORM" + System.currentTimeMillis());//unique val
						myAgent.send(cfp);
						// Prepare the template to get proposals
						mt = MessageTemplate.and(MessageTemplate.MatchConversationId("ticket-trade"),
								MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
						step = 1;
					}
					
					
					
					break;
				case 1:
					ACLMessage getTicket = myAgent.receive(mt);
					if (getTicket != null) {
						
						if (getTicket.getPerformative() == ACLMessage.INFORM) {
							ACLMessage giveTicket = new ACLMessage(ACLMessage.CFP);
							giveTicket.addReceiver(baristaAgent);
							//user choose a coffee type
							int coffeeSelection = (int)(Math.random() * 100);
							if(coffeeSelection <= 20 ) {
								targetCoffeName = BaseAgent.coffeTypes[0];
								me.setCoffeeType(targetCoffeName);
								me.UpdateGui();

							}else if(coffeeSelection > 20 && coffeeSelection <= 35) {
								targetCoffeName = BaseAgent.coffeTypes[1];
								me.setCoffeeType(targetCoffeName);
								me.UpdateGui();


							}else if(coffeeSelection > 35 && coffeeSelection <= 70) {
								targetCoffeName = BaseAgent.coffeTypes[2];
								me.setCoffeeType(targetCoffeName);
								me.UpdateGui();


							}else {
								targetCoffeName = BaseAgent.coffeTypes[3];
								me.setCoffeeType(targetCoffeName);
								me.UpdateGui();

							}
							
							giveTicket.setContent(targetCoffeName);
							giveTicket.setConversationId("coffe-trade");
							giveTicket.setReplyWith("INFORM" + System.currentTimeMillis());//unique val
							myAgent.send(giveTicket);
							// Prepare the template to get proposals
							mt = MessageTemplate.and(MessageTemplate.MatchConversationId("coffe-trade"),
									MessageTemplate.MatchInReplyTo(giveTicket.getReplyWith()));
							me.setGetTicket(true);
							me.setWaiting(false);
							me.setInCoffeeLine(true);
							me.UpdateGui();
							step = 2;
						}
						
						
					}else {
						block();
					}
					
					break;
					
				case 2:
				
					ACLMessage reply = myAgent.receive(mt);
					if (reply != null) {
						// Reply received
						if (reply.getPerformative() == ACLMessage.INFORM) {
							System.err.println("I got my coffee and I'm leaving" + myAgent.getAID().getName());
							me.setDone(true);
							me.setInCoffeeLine(false);
							me.UpdateGui();
							step =3;
							myAgent.doDelete();
						}
						
						
					}
					else {
						block();
					}
					

				default:
					break;
				}
				
				
				
				
			}
			
			//ödeme yapma implement edilecek
			//block();
		}
		@Override
		public boolean done() {
			return step == 3;
		}

		
	}
}