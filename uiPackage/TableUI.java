/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	TableUI.Java
 * Package	:	uiPackage
 * Comments	:	TableUI class displays the amortization schedule as a Chart
 */

package uiPackage;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableUI{
	
	public void displayChart(Integer[] paymentNo, Double[] interest, Double[] principal, Double[] balance, int loanTerm)
	{
		// Frame variable for GUI
		JFrame frame	=	new JFrame("Loan Amortization Chart");
		
		// Table variable to List data
		JTable table;
		
		// Table Model
		DefaultTableModel model;
		
		// Column Names
		String col[] = {"Payment No","Interest","Principal","Balance"};

		model = new DefaultTableModel(col, 0); 
		table = new JTable(model); 
		
		// Enable Scrolling
	    JScrollPane pane = new JScrollPane(table);
	    
	    // Fill table with received values
	    for(int i=0; i < loanTerm ;i++)
	    {
	    	// Add data to Table by inserting each row
	    	model.addRow(new Object[]{paymentNo[i],interest[i],principal[i],balance[i]});
	    }
	    
	    // Display the Table in GUI
	    frame.add(pane);
	    frame.setVisible(true);
	    frame.setSize(500,500);
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
