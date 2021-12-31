**CENG 4513 Modeling and Simulation 2021-2022 Fall Assignment 02**

` `**Oğuzhan Akdoğan**


1. **Introduction:**

In this report, the implementation of Arena simulation in Assignment 1 with Jade is explained.



1. **Case Study with JADE:**

`	`There are 6 classes in total in this implementation. These; BaseAgent, CoffeeBuyerAgent, BaristaAgent, CashierAgent, AssignmentGUI, Customer. The BaseAgent class is the class from which other agents are created. We used the AssignmentGUI and the Customer class to create the interface for the implementation.

1. **How to Run the Implementation**

`	`This implementation was built using the Eclipse IDE. Use the “Start EJADE RMA” button to run the implementation and then deploy agent in the EJADE part of the “BaseAgent” claBaseAgent Class

1. ` `Firstly, go over the “Install new software” in the help menu on eclipse.

![Install new software](https://github.com/oguzhanakdogan/Jade-Simulation-Java-/Images/install-new-software.png?raw=true)

1. Write EJADE - <http://dit.unitn.it/~dnguyen/ejade/update>[ in the Work with bar.]()

1. Right Click on the project folder, and click on the “toggle JADE/JADEX agent nature”

1. Right click on the “BaseAgent.java” class and  Ejade → Deploy Agent(s)













1. **How to create Agent**

`	`The cyclic behavior has been used in the BaseAgent class, so we have the continuous behavior in this class.


*Figure 1: Creation of Agents*

































`	`The code block named startAgent used to create agents is shown in Figure1. In order to ensure that the agents created in this code block are included in the same container, the content of the if-else code block is shown in the image.



*Figure 2: Interarrival Time*

The CoffeeBuyerAgent class is created every 120 seconds with Exponential Distribution. \\ In Figure 2, there is a code block related to this.




*Figure 3: Start Creating Agents*

`	`We create the agents using the code block in Figure 3. In this part, we create BaristaAgent and CashierAgent in the same container by calling “startAgent()” function.



*Figure 4: Output of Created Agents*

As seen in Figure 4, it can be seen that the agents were created successfully.

1. **CashierAgent Class:**

`	`Because the cashier will be in a continuous payment process, we've set the behavior of the cashier as TicketBehavior, which inherits the Cyclic Behavior.

`	`The cashier issues receipts according to the type of payment received from the customer. According to the message received from the customer, it gets the name of the customer with the msg.getSender().getName() function. We create these features as in the code block image below.



1. **BaristaAgent Class:**

`	`Every agent offering a service must register their service in the yellow pages(DF) so that other agents on the same platform can search for it. For this you need to create ServiceDescription.


*Figure 6: DFAgentDescription and ServiceDescription*



1. **Applying Distribution in Implementation:**

`	`We determined the distribution according to each type of coffee in the first assignment. This time we will apply the preparation time of each coffee to JADE according to these distributions. We used the libraries “org.apache.commons.math3.distribution.GammaDistribution”, “org.apache.commons.math3.distribution.NormalDistribution”, “org.apache.commons.math3.distribution.WeibullDistribution” for this. We then included the coffee types in the array and assigned their distribution as in Figure 7.


*Figure 7: Distributions by Coffee Type*

1. **End of the process of the preparation Coffee**

`	`When the coffee is ready, the barista notifies the customer (CoffeeBuyerAgent) with an INFORM type message. It is shown in the image in Figure 8.


*Figure 8: Send message to costumer from barista*



1. **CoffeeBuyerAgent Class:**

`	`In the previous sections, we described the situations required for the agents we mentioned to find each other. CoffeeBuyerAgent finds BaristaAgent and CashierAgent, we apply the same procedures in this part. We also used OneShot Behavior to create behaviors for actions that only need to be done once. Because the coffee-buyer performs operations such as ordering and payment only once.

1. **Choosing Payment Method:**

`	`According to the information we have previously obtained, 75% of the payment type is credit card and 23% is cash payment. The remaining 2% is the segment that gave up trading. By choosing a random number, we understand which payment method the customer will choose based on this number. The 2% part leaves the system with the doDelete() method. (see also Figure 9)


*Şekil 9: Choosing Payment Method*



1. **Implementation of Coffee Types According to Their Preference Possibilities.**
**
`    `In the simulation we made on the arena, the purchase rates of each coffee were as follows: Latte 20%, Frappucino 15%, Hot Drip 35%, Iced Coffee 30%. We applied these ratios to the project with the if-else block. A sample code is shown in Figure 10.

*Figure 10:Customer choose a coffee type*

`	`The *"giveTicket"* command is the message content of the INFORM type that allows us to understand whether the order placed by the customer has been approved or not.

1. **After Costumer Got Coffee:**

`	`Customers who take their coffee and process it, quit of the system by sending the message that they have received their coffee. We will refer to the UpdateGui() function shown in Figure 11 in the following sections.


*Figure 11: Costumer got coffee*

1. **Graphical User Interface:**

`	`In order for our project to have a more understandable interface, we decided to add a GUI to the project. We preferred MVC Design Pattern when creating the GUI.

1. **Customer Class**

`	`To create the GUI, we first create a class called Customer, where the properties of the customers are specified. In this class, we have added features that allow us to understand the customer's ID, interarrival times, whether they are on queue in order or coffee, and we have defined constructors and their getter/setter methods. (Figure 12 shows the Customer class.)




`	`In addition, the UpdateGUI() function seen in Figure 12 allows us to add rows to the interface. The AddMeGui() function allows us to add the information of the new customer to the interface.

1. **AssignmentGUI Class:**

`	`In this class, we create the visual and content part of the interface. In the visual part, we inherit the JFrame library to create the layouts.


*Figure 6: AddNewCostumer() Function*

`	`We calculate the id(number of customer -1) of the customer, which we determined in the Customer Class, with the counter and add it to the table.(Figue 13)


*Figure 7 :UpdateRow() Function*

`	`Once we have determined the number of customers(id), we use the UpdateRow() function to retrieve other information to add to the table.

*Figure 8: ShowGui() Function*

`	`After getting all the necessary information of the customer, we arrange the table where this information will be placed, using the ShowGui() method. In Figure 14, each cell in the table has been dimensioned.

1. **Results:**

In this section of the report, we will review the outputs.

*Figure 16: Outputs of created agents*

`	`When the customer logs into the system, we get an output like "Hello, Buyer-Agent xxx is ready". This output can be considered as the customer entering the coffee shop. Then, according to the possibilities we mentioned in the previous sections, the customer decides which coffee to choose and we get a output in the form of "target coffee is xxx". Then the system finds the barista to prepare the selected coffee and returns an output like "Found the following barista-agent ". It repeats the same process for the cashier agent.(See Figure 16)


*Figure 17: JADE Agent Environment*

`	`The customer entering the system in Figure 17 is created inside the TestContainer, in the same container as the barista and cashier agent.

*Figure 18: Ticket Output*

`	`In the image above, it is checked whether the customer's receipt is cut according to the type of payment to be made.



*Figure 19: Customer got coffee*












`	`After the customer completes all the operations, he gives a printout as in Figure 19 that he has received his coffee.		

*Figure 20: Costumer gave up buying coffee*

If the customer refuses to buy coffee at the time of ordering, we see an output like Figure 20. The abandonment situation covers 2% of the customers. (Available in arena simulation.


`	`In Figure 21, there is a sample part of the GUI we created for better understanding of the outputs. Row names appear at the top. The arrival time of the customer logging into the system is displayed in seconds. The customer then enters the ticket queue and his status here becomes "true". If he receives his receipt, the "Get Ticket" status will be "true" and the receipt will be out of the queue. So the "In Ticket Line" section becomes false. The same is repeated for the coffee taking process. In addition, which type of coffee the customer buys and which payment method he chooses can be seen on the interface.



*Figure 21: GUI*


In the section indicated by the yellow line in Figure 21, there is a sample part of the GUI we created for better understanding of the outputs. Row names appear at the top. The arrival time of the customer logging into the system is displayed in seconds. The customer then enters the ticket queue and his status here becomes "true". If he receives his receipt, the "Get Ticket" status will be "true" and the receipt will be out of the queue. So the "In Ticket Line" section becomes false. The same is repeated for the coffee taking process. In addition, which type of coffee the customer buys and which payment method he chooses can be seen on the interface.


*Figure 22: Error*

In our project, we get an error seen in Figure 23. (The screenshot contains only a part of the error.) This error occurs after the customer finds the barista and cashier agent required to place the order, and then performs a random number assignment.










\4. LIBRARIES

`	`To run this project, you have to download some libraries:

- Jade library → [Jade Download | Jade Site (tilab.com)](https://jade.tilab.com/download/jade/license/jade-download/?x=47&y=15)  (Jade all contains everythings)
- [Math – Download Apache Commons Math](https://commons.apache.org/proper/commons-math/download_math.cgi)  (Consists of distribution library to create time interval between two behaviour.)



