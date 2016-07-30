import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

public class yamDB {

	private JFrame frame;
	 private JTextField jtxtFieldName;
	 private JTextField jtxtFieldYear;
     private JButton btnSearch;
     private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        Splash mySplash = new Splash(2000);
        mySplash.showSplash();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yamDB window = new yamDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public yamDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Yam DB");
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80))); 

		// Icon
		JLabel lbIcon = new JLabel();
		ImageIcon icon = createImageIcon("logo.png","");
		lbIcon.setIcon(icon);

		//search by name
		JPanel innerPan = new JPanel();
		innerPan.setLayout(new BoxLayout(innerPan, BoxLayout.X_AXIS));
		JLabel lb1 = new JLabel("Search by Name :  ");
	    jtxtFieldName = new JTextField();
		innerPan.add(lb1);
		innerPan.add(jtxtFieldName);

		//search by year
		JPanel innerPan2 = new JPanel();
		innerPan2.setLayout(new BoxLayout(innerPan2, BoxLayout.X_AXIS));		
		JLabel lb2 = new JLabel("Search by Year :  ");
		/*JComboBox years = new JComboBox();
		ArrayList<Integer> yesrList = new ArrayList();
		for(int i = 1878; i < 2017; i++){
			yesrList.add(i);
			years.addItem(i);
		}*/
		jtxtFieldYear = new JTextField();
		
		//years.addItem(yesrList);
		
		innerPan2.add(lb2);
		//innerPan2.add(years);
		innerPan2.add(jtxtFieldYear);
		//search button
		JPanel innerPan3 = new JPanel();
		innerPan3.setLayout(new BorderLayout());
		btnSearch = new JButton("Search");
		ActionHandler handler = new ActionHandler();

        btnSearch.addActionListener(handler);
        jtxtFieldName.addActionListener(handler);
        jtxtFieldYear.addActionListener(handler);
		innerPan3.add(btnSearch,BorderLayout.CENTER);
		
		
		//Table
		/*JTable movieTalbe = new JTable();
		DefaultTableModel dtm = new DefaultTableModel();
		String columNames[] = {"Title", "Year","Rating"};
		dtm.setColumnCount(3);
		dtm.setColumnIdentifiers(columNames);
		movieTalbe.setModel(dtm);
		movieTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		dtm.addRow(new Object[] {"The Secret Life of Pets","2016","7.5"});
		dtm.addRow(new Object[] {"The BFG","2016","8.5"});
		dtm.addRow(new Object[] {"Ghostbusters","2016","5.5"});
		dtm.addRow(new Object[] {"Central Intelligence","2016","8.0"});*/
		model = new DefaultListModel<>();
         JList list = new JList(model);
        


		panel.add(lbIcon);
		panel.add(Box.createVerticalStrut(30));
		panel.add(innerPan);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan2);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan3);
		panel.add(Box.createVerticalStrut(15));
		 panel.add(new JScrollPane(list));
		//panel.add(new JScrollPane(movieTalbe));
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


	}
	
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	 public class ActionHandler implements ActionListener {
     	

         @Override
         public void actionPerformed(ActionEvent e) {
         	model.removeAllElements();
         	try {

     			//1.Get connection to database
//     			Connection myCon = DriverManager.getConnection("jdbc:mysql://LocalHost/world", "root", "Lita1386!"); // replace world with the name of your database, put your password in ""
         		Connection myCon = DriverManager.getConnection("jdbc:mysql://yamdb.ddns.net/yamdb?useSSL=false", "devs", "P@ssw0rd"); // replace world with the name of your database, put your password in ""
         		
         		//2. Create a statement
     			Statement myStmt = myCon.createStatement();
     			//3 Execute SQl query
     			String name = jtxtFieldName.getText();
     			String date = jtxtFieldYear.getText();
     			String rank = null;
     			String votes = null;
     			/*String query = "select * from test WHERE name = ?";
     			PreparedStatement pst = (PreparedStatement) myCon.prepareStatement(query);
     			pst.setString(1, "%" + searchText + "%");
     			ResultSet rs = pst.executeQuery();*/
     		String searchStatement = "select * from Ratings WHERE Title = '" + name + "'";
         		//also search date if we were sent one
         	if(date.length() == 4) {
         		searchStatement += " AND Year = '" + date + "'";
         	}
     		ResultSet rs = myStmt.executeQuery(searchStatement);
     			
     			//ResultSet rs = myStmt.executeQuery("select * from test");
     			
     			
     			//4 Process the result set
     			
     			 while(rs.next()) {
     				name = rs.getString("Title");
     				date = rs.getString("Year").substring(0, 4);
     				//rank = rs.getString("Rank");
     				//votes = rs.getString("Votes");
     				
     				model.addElement("Movie: " + name + "    Release Date: " + date);// + "    Rating: " + name + "     Votes: " + date);
     				
     	         }
     	        /*if (searchText.equals(query)) {
     	            System.out.println("Searching names.."  + query);
     	            }*/
     			
     		} 
     		catch (SQLException g) {
     			// TODO Auto-generated catch block
     			g.printStackTrace();
     		}
         }
     }
	
}
