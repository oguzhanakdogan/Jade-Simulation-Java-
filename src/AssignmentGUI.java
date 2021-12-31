import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import jade.util.leap.ArrayList;

public class AssignmentGUI extends JFrame {
	short GUI_WIDTH = 1800;
	short GUI_HEIGHT = 600;
	private JTextField titleField, priceField;
	Object[][] customers = new Object[100][8];
	private int customerCounter = 0;
	JFrame frame;
	JTable table;
	public AssignmentGUI() {
		    frame = new JFrame("My First Swing Example");
	        // Setting the width and height of frame
	        frame.setSize(700, 500);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        frame.add(panel);
	        /* calling user defined method for adding components
	         * to the panel.
	         */
	     //   placeComponents(panel);

	        String[] columnNames = {"CustomerID",
                    "Arrival Time",
                    "In Ticket Line",
                    "Get Ticket",
                    "In Coffee Line",
                    "Get Coffe",
                    "Coffee Type",
                    "Payment Method"
                    };
//	        
//	       
	        
	        ArrayList deneme = new ArrayList();
	         table = new JTable(customers, columnNames);
	        Object[][] data1 = { {"osman", "akdoðan",
	       	     "qweqweq", new Integer(50), new Boolean(false)}};
	        
	        JScrollPane sp = new JScrollPane(table);
	        frame.add(sp);
	      //  data[5] = data1[0];
	        // Setting the frame visibility to true
	       // frame.add(table);
	        frame.setVisible(true);
	}
	
	public void AddNewCustomer(Customer c) {
		Object[][] data = {{c.getName(), c.getArrivalTime(), c.isWaiting(),
			c.isGetTicket(),c.isInCoffeeLine() ,c.isDone(), c.getCoffeeType(), c.getPaymentMethod()}};
		customers[customerCounter] =data[0];
		customerCounter++;
		table.updateUI();
		
	}
	
	public void UpdateRow(Customer c) {
		Object[][] data = {{c.getName(), c.getArrivalTime(), c.isWaiting(),
			c.isGetTicket(), c.isInCoffeeLine(),c.isDone(), c.getCoffeeType(), c.getPaymentMethod()}};
		customers[c.getName()] =data[0];
		table.updateUI();
		
	}
	
	
	/* private static void placeComponents(JPanel panel) {

	        
	        panel.setLayout(null);

	        JLabel userLabel = new JLabel("User");
	        userLabel.setBounds(10,20,80,25);
	        panel.add(userLabel);

	        JTextField userText = new JTextField(20);
	        userText.setBounds(100,20,165,25);
	        panel.add(userText);

	        JLabel passwordLabel = new JLabel("Password");
	        passwordLabel.setBounds(10,50,80,25);
	        panel.add(passwordLabel);

	        JPasswordField passwordText = new JPasswordField(20);
	        passwordText.setBounds(100,50,165,25);
	        panel.add(passwordText); 


	        JButton loginButton = new JButton("login");
	        loginButton.setBounds(10, 80, 80, 25);
	        panel.add(loginButton); 
	    } */

	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	}	
}