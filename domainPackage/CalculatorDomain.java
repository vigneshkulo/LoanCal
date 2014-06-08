/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	CalculatorDomain.Java
 * Package	:	domainPackage
 * Comments	:	Calculator class in domain Layer which calculates the Amortization schedule
 */

package domainPackage;
import uiPackage.ControllerUI;

public class CalculatorDomain {

	int term		= 0;							// Loan Term global variable
	double EMI 		= 0;							// EMI global variable - Constant for each month					
	ControllerUI uiControllerLocal = new ControllerUI();
	
	// Calculate the Amortization values (Interest, Principal and Balance for each Month) and return EMI
	public double calc(double principalAmount, int loanTerm, double interestRate, int displayType)
	{
		Double[] iPM 	= new Double[loanTerm];		// Interest amount to be paid for each month
		Double[] pPM 	= new Double[loanTerm];		// Principal amount to be paid for each month
		Double[] bal 	= new Double[loanTerm];		// Balance amount at end of each month
		Integer[] paNum = new Integer[loanTerm];	// Payment Number
		
		// Monthly Interest = Annual Interest rate / 12
		double monIntRate = interestRate / (12 *100);

		term = loanTerm;

		// First Month Interest amount = Initial Principal Amount / Monthly Interest
		iPM[0] = principalAmount*monIntRate;
		// Round the value to 2 decimal digits
		iPM[0] = Math.round( iPM[0] * 100.0 ) / 100.0;

		// First Month amortization payment is calculated using given formula
		pPM[0] = (principalAmount * (monIntRate * (Math.pow((1+monIntRate),loanTerm))))/(Math.pow((1 + monIntRate),loanTerm)-1) - iPM[0];
		// Round the value to 2 decimal digits
		pPM[0] = Math.round( pPM[0] * 100.0 ) / 100.0;

		// EMI = First Month amortization payment + First month interest amount. The EMI value is constant for every month
		EMI = iPM[0] + pPM[0];
		// Principal amount for 2nd month = Initial Principal amount - First month amortization payment
		principalAmount -= pPM[0];
		// Round the value to 2 decimal digits
		principalAmount = Math.round( principalAmount * 100.0 ) / 100.0;
		
		// Balance at the end of first month is the Principal amount of Second month
		bal[0] = principalAmount;
		
		// Payment number for first month is 1
		paNum[0] = 1;

		// Repeat for other months
		for(int i = 1; i < loanTerm; i++)
		{
			paNum[i] = (i+1);
			
			// Monthly Interest amount = Principal Amount / Monthly Interest
			iPM[i] = principalAmount*monIntRate;
			iPM[i] = Math.round( iPM[i] * 100.0 ) / 100.0;
			
			// Monthly amortization payment = EMI - Interest amount for that month
			pPM[i] = EMI - iPM[i];
			pPM[i] = Math.round( pPM[i] * 100.0 ) / 100.0;
		
			principalAmount -= pPM[i];
			principalAmount = Math.round( principalAmount * 100.0 ) / 100.0;
			
			bal[i] = principalAmount;
		}
		
		// Balance at the end of Last month is Zero
		bal[(int) (loanTerm-1)] = (double) 0;
		
		if(1 == displayType)
		{
			// Display Chart if call was from Chart Button
			uiControllerLocal.displayChartController(paNum, iPM, pPM, bal, loanTerm);
		}
		else if (2 == displayType)
		{
			// Display Graph if call was from Graph Button
			uiControllerLocal.displayGraphController(bal, loanTerm);
		}
		
		// Return the EMI
		return EMI;
	}

	// Returns the Total of Payments
	public double getTotalPay() {
		
		// Total of Payments = EMI * Loan Term, and round the output to 2 decimal digits
		return (Math.round(EMI*term*100.0)/100.0);
	}
}
