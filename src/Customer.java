public class Customer {
	private long arrivalTime;
	private int name;
	private boolean waiting;
	private AssignmentGUI gui;
	private boolean getTicket;
	private boolean done;
	private boolean inCoffeeLine;
	private String coffeeType;
	private String paymentMethod;
	public Customer(long arrivalTime, int name, boolean waiting, AssignmentGUI gui,
			boolean getTicket,boolean inCoffeeLine ,boolean done, String coffeeType, String paymentMethod) {
		// TODO Auto-generated constructor stub
		this.arrivalTime = arrivalTime;
		this.name = name;
		this.gui = gui;
		this.waiting = waiting;
		this.getTicket = getTicket;
		this.done = done;
		this.inCoffeeLine = inCoffeeLine;
		this.coffeeType = coffeeType;
		this.paymentMethod = paymentMethod;
	}
	
	public void UpdateGui() {
		//this.waiting = waiting;
		gui.UpdateRow(this);
	}
	
	public void AddMeGui() {
		gui.AddNewCustomer(this);
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public AssignmentGUI getGui() {
		return gui;
	}

	public void setGui(AssignmentGUI gui) {
		this.gui = gui;
	}

	public boolean isGetTicket() {
		return getTicket;
	}

	public void setGetTicket(boolean getTicket) {
		this.getTicket = getTicket;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isInCoffeeLine() {
		return inCoffeeLine;
	}

	public void setInCoffeeLine(boolean inCoffeeLine) {
		this.inCoffeeLine = inCoffeeLine;
	}

	public String getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(String coffeeType) {
		this.coffeeType = coffeeType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
	
	
	
	
	
}