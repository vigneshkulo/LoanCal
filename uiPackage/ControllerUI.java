/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	ControllerUI.Java
 * Package	:	uiPackage
 * Comments	:	Controller class for the User Interface Layer (Avoids exposing UI objects to other layers) which does delegation work only
 */

package uiPackage;

public class ControllerUI {
	
	TableUI tableUILocal = new TableUI();
	GraphUI graphUILocal = new GraphUI();

	// Calls Table Class to display the Chart
	public void displayChartController(Integer[] paymentno, Double[] interest, Double[] principal, Double[] balance, int loanTerm)
	{
		tableUILocal.displayChart(paymentno, interest, principal, balance, loanTerm);
	}
	
	// Calls Graph Class to display the Graph
	public void displayGraphController(Double[] balance, int loanTerm)
	{
		graphUILocal.displayGraph(balance, loanTerm);
	}
}
