import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

public class yamDB {

	private JFrame frame;

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
		JTextField txtName = new JTextField();
		innerPan.add(lb1);
		innerPan.add(txtName);

		//search by year
		JPanel innerPan2 = new JPanel();
		innerPan2.setLayout(new BoxLayout(innerPan2, BoxLayout.X_AXIS));		
		JLabel lb2 = new JLabel("Search by Year :  ");
		JComboBox years = new JComboBox();
		ArrayList<Integer> yesrList = new ArrayList();
		for(int i = 1878; i < 2017; i++){
			yesrList.add(i);
			years.addItem(i);
		}
		//years.addItem(yesrList);
		innerPan2.add(lb2);
		innerPan2.add(years);
		
		//search button
		JPanel innerPan3 = new JPanel();
		innerPan3.setLayout(new BorderLayout());
		JButton btnSearch = new JButton("Search");
		innerPan3.add(btnSearch,BorderLayout.CENTER);
		
		
		//Table
		JTable movieTalbe = new JTable();
		DefaultTableModel dtm = new DefaultTableModel();
		String columNames[] = {"Title", "Year","Rating"};
		dtm.setColumnCount(3);
		dtm.setColumnIdentifiers(columNames);
		movieTalbe.setModel(dtm);
		movieTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		dtm.addRow(new Object[] {"The Secret Life of Pets","2016","7.5"});
		dtm.addRow(new Object[] {"The BFG","2016","8.5"});
		dtm.addRow(new Object[] {"Ghostbusters","2016","5.5"});
		dtm.addRow(new Object[] {"Central Intelligence","2016","8.0"});
			
		panel.add(lbIcon);
		panel.add(Box.createVerticalStrut(30));
		panel.add(innerPan);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan2);
		panel.add(Box.createVerticalStrut(15));
		panel.add(innerPan3);
		panel.add(Box.createVerticalStrut(15));
		panel.add(new JScrollPane(movieTalbe));
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
}