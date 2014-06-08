/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	GUI.Java
 * Package	:	uiPackage
 * Comments	:	Class which creates the GUI
 */

package uiPackage;

import javax.swing.*;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import domainPackage.ControllerDomain;

public class GUI implements ActionListener, KeyListener {

		// Frame variable for GUI
		JFrame frame	=	new JFrame("Loan Calculator");
		
		// Panel variable to add to the Frame GUI
		JPanel panel	=	new JPanel(new GridBagLayout());
		
		// Chart and Graph Button Variables
		JButton chartButton = new JButton("Display Chart");
		JButton graphButton = new JButton("Display Graph");
	
		// Various Labels
		JLabel emiLabel			= new JLabel();
		JLabel emiValLabel		= new JLabel();
		JLabel monthsLabel		= new JLabel();
		JLabel dollarLabel 		= new JLabel();	
		JLabel totalPayLabel	= new JLabel();
		JLabel intRateLabel 	= new JLabel();
		JLabel loanAmtLabel 	= new JLabel();
		JLabel equalToLabel1 	= new JLabel();
		JLabel equalToLabel2 	= new JLabel();
		JLabel loanTermLabel 	= new JLabel();
		JLabel totalPayValLabel	= new JLabel();
		JLabel percentageLabel 	= new JLabel();
		
		// Input Text Fields
		JTextField loanTermInput = new JTextField(10);
		JTextField loanAmountInput = new JTextField(10);
		JTextField interestRateInput = new JTextField(10);
		
		// Constraints to align the Objects in the Panel - Grid Bag Layout used
		GridBagConstraints c = new GridBagConstraints();

		// Domain Layer Controller Class instance 
		ControllerDomain controllerLocal = new ControllerDomain();

		/* Enable Chart and Graph Buttons when value = 7 (Bits 1,2 and 4 are mapped to each Input), else Disable.
		 * Bit 1 -> if Loan amount text field is valid, this bit is set, else set as zero
		 * Bit 2 -> if Loan Term text field is valid, this bit is set
		 * Bit 4 -> if Loan interest rate field is valid, this bit is set
		 * So, when enableButton's value is 7, it means all three text field values are valid
		 */
		int enableButton = 0;

		// Function to create and display the Calculator GUI
		public void displayGUI()
		{
				// Set Frame Visibility, Size and Exit operation
				frame.setVisible(true);
				frame.setSize(400, 250);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				// Add Panel to the Frame - TOP (North end)
				frame.add(panel, BorderLayout.NORTH);
				
				// Place Loan Amount Label in (0,0) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 0;
				c.insets = new Insets(10, 10, 10, 10);
				loanAmtLabel.setText("Loan Amount");
				panel.add(loanAmtLabel, c);		
				
				// Place Loan Amount Text field in (1,0) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 0;
				panel.add(loanAmountInput, c);
				
				// Place dollar symbol in (2,0) position in the Grid (Panel)
				c.gridx = 2;
				c.gridy = 0;
				dollarLabel.setText("dollars");
				panel.add(dollarLabel,c);
				
				// Place Loan Term Label in (0,1) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 1;
				loanTermLabel.setText("Loan Term");
				panel.add(loanTermLabel, c);
				
				// Place Loan Term Text field in (1,1) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 1;
				panel.add(loanTermInput, c);
				
				// Place Months Label in (2,1) position in the Grid (Panel)
				c.gridx = 2;
				c.gridy = 1;
				monthsLabel.setText("months");
				panel.add(monthsLabel,c);
				
				// Place Interest Rate Label in (0,2) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 2;
				intRateLabel.setText("Interest Rate");
				panel.add(intRateLabel, c);
				
				// Place Interest Rate Text field in (1,2) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 2;
				panel.add(interestRateInput, c);
				
				// Place Percentage Symbol in (1,1) position in the Grid (Panel)
				c.gridx = 2;
				c.gridy = 2;
				percentageLabel.setText("%");
				panel.add(percentageLabel,c);
				
				// Place Chart Button in (0,3) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 3;
				panel.add(chartButton, c);
				
				// Place Graph Button in (1,3) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 3;
				panel.add(graphButton, c);
				
				// Place EMI Label in (0,4) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 4;
				emiLabel.setText("EMI");
				panel.add(emiLabel,c);
				
				// Place "equal to" symbol in (1,4) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 4;
				equalToLabel1.setText("=");
				panel.add(equalToLabel1,c);
				
				// Place EMI Value in (2,4) position in the Grid (Panel)
				c.gridx = 2;
				c.gridy = 4;
				emiValLabel.setText("xxxx");
				panel.add(emiValLabel,c);

				// Place Number of Payments Label in (0,5) position in the Grid (Panel)
				c.gridx = 0;
				c.gridy = 5;
				totalPayLabel.setText("Total of Payments");
				panel.add(totalPayLabel,c);
				
				// Place "equal to" symbol in (1,5) position in the Grid (Panel)
				c.gridx = 1;
				c.gridy = 5;
				equalToLabel2.setText("=");
				panel.add(equalToLabel2,c);
				
				// Place Number of Payments value in (2,5) position in the Grid (Panel)
				c.gridx = 2;
				c.gridy = 5;
				totalPayValLabel.setText("xxxx");
				panel.add(totalPayValLabel,c);
				
				// Set EMI and Number of Payments related Labels to Invisible state
				emiLabel.setVisible(false);
				equalToLabel1.setVisible(false);
				emiValLabel.setVisible(false);
				totalPayLabel.setVisible(false);
				equalToLabel2.setVisible(false);
				totalPayValLabel.setVisible(false);
				
				// Disable two buttons initially
				graphButton.setEnabled(false);
				chartButton.setEnabled(false);
				
				// Add Key listeners to JTextField's)
				loanAmountInput.addKeyListener(this);
				loanTermInput.addKeyListener(this);
				interestRateInput.addKeyListener(this);	
				
				// Add Action listeners to JButton's)
				chartButton.addActionListener(this);
				graphButton.addActionListener(this);
		}
		
		// Set EMI and Number of Payments related Labels to Visible state
		public void setVisibleEmiAndPay(Double EMI, Double totalPay)
		{
			// Resize frame to fill in new Labels
			frame.setSize(400, 300);
			
			// Set the string values of EMI and Total Payment
			emiValLabel.setText(Double.toString(EMI));
			totalPayValLabel.setText(Double.toString(totalPay));
			
			// Set related Labels as Visible
			emiLabel.setVisible(true);
			equalToLabel1.setVisible(true);
			emiValLabel.setVisible(true);
			totalPayLabel.setVisible(true);
			equalToLabel2.setVisible(true);
			totalPayValLabel.setVisible(true);
		}
		
		// Action Listener function for Chart and Graph Buttons
		public void actionPerformed(ActionEvent e) {
			
			// Get source of the Event
			Object source = e.getSource();
			
			// Do if source is Chart Button
			if ( chartButton == source)
			{		
				double principalAmount 	= Double.valueOf(loanAmountInput.getText());
				int loanTerm 			= Integer.valueOf(loanTermInput.getText());
				double interestRate 	= Double.valueOf(interestRateInput.getText());
				
				/* Calls -> calcController which calculates the amortization schedule, displays a Chart and returns EMI value 
				 * 4th Parameter of calcController differentiates between calls from "Display Chart (1)" and "Display Graph (2)"
				 * Calls -> getTotPayController which gets the Total of Payments from Domain Layer
				 * Calls -> setVisibleEmiAndPay local function passing the above two values (EMI and Total of Payments) as input to this function
				 */
				setVisibleEmiAndPay(controllerLocal.calcController(principalAmount, loanTerm, interestRate, 1), controllerLocal.getTotPayController());
			}
			
			// Do if source is Graph Button
			if ( graphButton == source)
			{
				double principalAmount 	= Double.valueOf(loanAmountInput.getText());
				int loanTerm 			= Integer.valueOf(loanTermInput.getText());
				double interestRate 	= Double.valueOf(interestRateInput.getText());
				
				/* Calls -> calcController which calculates the amortization schedule, displays a Graph and returns EMI value 
				 * 4th Parameter of calcController differentiates between calls from "Display Chart (1)" and "Display Graph (2)"
				 * Calls -> getTotPayController which gets the Total of Payments from Domain Layer
				 * Calls -> setVisibleEmiAndPay local function passing the above two values (EMI and Total of Payments) as input to this function
				 */
				setVisibleEmiAndPay(controllerLocal.calcController(principalAmount, loanTerm, interestRate, 2), controllerLocal.getTotPayController());
			}			
		}

		// Check if input Loan Amount is in correct format. If Yes returns True, else returns False.
		public boolean checkLoanAmt(String input)
		{
			try
			{
				// Check of input variable has Positive numbers only
				if(0 > Math.signum(Double.parseDouble(input))) return false;
	        
				// Check if Loan Amount < 9999999 
				if(9999999 < Double.parseDouble(input) || 0 == Double.parseDouble(input)) 
				{
					return false;
				}
				return true;
			}
			catch (NumberFormatException e1)
			{
				return false;
			}
		}
		
		// Check if input Loan Term is in correct format. If Yes returns True, else returns False.
		public boolean checkLoanTerm(String input)
		{
			try
			{
				// Check of input variable has Positive integers only
				if(0 > Math.signum(Integer.parseInt(input))) return false;
				
				// Check if Loan Term < 120 months
				if((120 < Integer.parseInt(input)) || (3 < input.length()) || 0 == Integer.parseInt(input)) 
				{
					return false;
				}
				return true;
			}
			catch (NumberFormatException e1)
			{
				return false;
			}
		}
		
		// Check if input Interest Rate is in correct format. If Yes returns True, else returns False.
		public boolean checkInterestRate(String input)
		{
			try
			{
				// Check of input variable has Positive integers only
				if(0 > Math.signum(Double.parseDouble(input))) return false;
				
				// Check if Interest Rate < 50 percent
				if(50 < Double.parseDouble(input) || 0 == Double.parseDouble(input)) 
				{
					return false;
				}
				return true;
			}
			catch (NumberFormatException e1)
			{
				return false;
			}
		}
		
		public void keyTyped(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
		}
		
		// Key Listener for Text Fields
		public void keyReleased(KeyEvent e) {
			
			boolean status;

			// Get text field source
			Object source = e.getSource();
			
			/* Allow only Key codes in range 47 to 111 (allow alphabets, numbers, decimal point etc.,) and neglect key events generated
			 * due to backspace, delete, functions keys etc.,
			*/
			if ((e.getKeyCode() > 47 && e.getKeyCode() < 111) || (e.getKeyCode() == 192) || (e.getKeyCode() == 46) || (e.getKeyCode() == 110))
			{
				if (loanAmountInput == source)
				{
					status = checkLoanAmt(loanAmountInput.getText());
					if (false == status) 
					{
						JOptionPane.showMessageDialog(null, "Loan amount has to be in the range from 1 to 9999999.");
						loanAmountInput.setText(""); 
						enableButton &= 6;
					}
					if (true == status) enableButton|=1;
				}
				
				if (loanTermInput == source)
				{
					status = checkLoanTerm(loanTermInput.getText());
					if (false == status) 
					{
						JOptionPane.showMessageDialog(null, "Loan term must be in the range from 1 to 120.");
						loanTermInput.setText(""); 
						enableButton &= 5;
					}
					if (true == status) enableButton|=2;
				}
				
				if (interestRateInput == source)
				{
					status = checkInterestRate(interestRateInput.getText());
					if (false == status) 
					{
						JOptionPane.showMessageDialog(null, "Interest rate must be in the range from 0.1 to 50.");
						interestRateInput.setText(""); 
						enableButton &= 3;
					}
					if (true == status) enableButton|=4;
				}

			}
			else
			{
				// Check if any of the text field's is empty, if Yes disable the bit that corresponds to the text field in enable Button
				if(loanAmountInput.getText().isEmpty()) enableButton &= 6;
				if(loanTermInput.getText().isEmpty()) enableButton &= 5;
				if(interestRateInput.getText().isEmpty()) enableButton &= 3;
			}
			
			// So, when enableButton's value is 7, it means all three text field values are valid, so enable both Buttons
			if( 7 == enableButton)
			{
				graphButton.setEnabled(true);
				chartButton.setEnabled(true);
			}
			else
			{
				graphButton.setEnabled(false);
				chartButton.setEnabled(false);
			}
		}		
}
