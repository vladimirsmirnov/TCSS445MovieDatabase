import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			//1.Get connection to database
			Connection myCon = DriverManager.getConnection("jdbc:mysql://LocalHost/world", "root", "Lita1386!");
			//2. Create a statement
			Statement myStmt = myCon.createStatement();
			//3 Execute SQl query
			ResultSet myRs = myStmt.executeQuery("select  * from test");
			//System.out.println(myRs);
			//4 Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("name"));
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final JFrame frame = new JFrame();
	        JPanel panel = new JPanel();

	        JButton button1 = new JButton();

	        frame.add(panel);
	        panel.add(button1);
	        frame.setVisible(true);

	        button1.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent arg0) {
	                JOptionPane.showMessageDialog(frame.getComponent(0), "Hello World");

	            }
	        });
	        		/* final JFrame frame = new JFrame();
	        JPanel panel = new JPanel();

	        JButton button1 = new JButton();

	        final JLabel label = new JLabel("Hello World");

	        label.setVisible(false);
	        frame.add(panel);
	        panel.add(button1);
	        panel.add(label);
	        frame.setVisible(true);
	        button1.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent arg0) {
	                //JOptionPane.showMessageDialog(frame.getComponent(0), "Hello World");
	                label.setVisible(true);
	            }
	        });*/


	    }

	}

	
