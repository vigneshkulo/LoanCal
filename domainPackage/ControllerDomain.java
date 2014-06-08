/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	GUI.Java
 * Package	:	domainPackage
 * Comments	:	Controller class for the Domain Layer (Avoids exposing Domain objects to other layers) which does only
 * 				delegation
 */

package domainPackage;

public class ControllerDomain {

	CalculatorDomain calcDomainLocal = new CalculatorDomain();

	// Calls Calculator to compute the Amortization values and returns the EMI value to GUI class
	public double calcController(double principalAmt, int loanTerm, double interestRate, int displayType)
	{
		return calcDomainLocal.calc(principalAmt, loanTerm, interestRate, displayType);
	}
	
	// Gets the Total of Payments from Calculator class and returns to GUI class
	public double getTotPayController()
	{
		return calcDomainLocal.getTotalPay();
	}
}
