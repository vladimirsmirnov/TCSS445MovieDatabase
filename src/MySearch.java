//UNUSED OLD MAIN CLASS
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import com.mysql.jdbc.PreparedStatement;
//
// UNUSED OLD MAIN CLASS
//public class MySearch {
//
//    public static void main(String[] args) {
//        new MySearch();
//    }
//
//    public MySearch() {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                }
//
//                JFrame frame = new JFrame("YAMDB");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setLayout(new BorderLayout());
//                frame.add(new TestPane());
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        });
//    }
//
//    public class TestPane extends JPanel {
//
//        private JTextField findMovie;
//        private JButton search;
//        private DefaultListModel<String> model;
//
//        public TestPane() {
//            setLayout(new BorderLayout());
//            JPanel searchPane = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridx = 0;
//            gbc.gridy = 0;
//            gbc.insets = new Insets(2, 2, 2, 2);
//            searchPane.add(new JLabel("Movie Name: "), gbc);
//            gbc.gridx++;
//            gbc.fill = GridBagConstraints.HORIZONTAL;
//            gbc.weightx = 1;
//            findMovie = new JTextField(20);
//            searchPane.add(findMovie, gbc);
//
//            gbc.gridx++;
//            gbc.fill = GridBagConstraints.NONE;
//            gbc.weightx = 0;
//            search = new JButton("Search");
//            searchPane.add(search, gbc);
//            JLabel lbl = new JLabel("YEAR");
//            lbl.setVisible(true);
//
//            searchPane.add(lbl);
//
//            String[] choices = { "START","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
//
//            final JComboBox<String> startYear = new JComboBox<String>(choices);
//            
//            String[] choices2 = { "END","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
//
//            final JComboBox<String> endYear = new JComboBox<String>(choices2);
//
//            startYear.setVisible(true);
//            searchPane.add(startYear);
//            
//            endYear.setVisible(true);
//            searchPane.add(endYear);
//
//            add(searchPane, BorderLayout.NORTH);
//
//            model = new DefaultListModel<>();
//            JList list = new JList(model);
//            add(new JScrollPane(list));
//
//            ActionHandler handler = new ActionHandler();
//
//            search.addActionListener(handler);
//            findMovie.addActionListener(handler);
//        }
//
//        public class ActionHandler implements ActionListener {
//        	
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	model.removeAllElements();
//            	try {
//
//        			//1.Get connection to database
////        			Connection myCon = DriverManager.getConnection("jdbc:mysql://LocalHost/world", "root", "Lita1386!"); // replace world with the name of your database, put your password in ""
//            		Connection myCon = DriverManager.getConnection("jdbc:mysql://yamdb.ddns.net/yamdb?useSSL=false", "devs", "P@ssw0rd"); // replace world with the name of your database, put your password in ""
//            		
//            		//2. Create a statement
//        			Statement myStmt = myCon.createStatement();
//        			//3 Execute SQl query
//        			String searchText = findMovie.getText();
//        			
//        			
//        			/*String query = "select * from test WHERE name = ?";
//        			PreparedStatement pst = (PreparedStatement) myCon.prepareStatement(query);
//        			pst.setString(1, "%" + searchText + "%");
//        			ResultSet rs = pst.executeQuery();*/
//        			String js = searchText;
//        			
//        		ResultSet rs = myStmt.executeQuery("select * from Movies WHERE Title = '" + js + "'");
//        			
//        			//ResultSet rs = myStmt.executeQuery("select * from test");
//        			
//        			
//        			//4 Process the result set
//        			
//        			 while(rs.next()) {
//        				model.addElement(rs.getString("Title") + rs.getString("Year"));
//        				
//        	         }
//        	        /*if (searchText.equals(query)) {
//        	            System.out.println("Searching names.."  + query);
//        	            }*/
//        			
//        		} 
//        		catch (SQLException g) {
//        			// TODO Auto-generated catch block
//        			g.printStackTrace();
//        		}
//            }
//        }
//    }
//}
