/*
 * Name		:	VIGNESH KULOTHUNGAN
 * NetID	:	vxk111430
 * File 	:	GraphUI.Java
 * Package	:	uiPackage
 * Comments	:	GraphUI class displays the amortization schedule as a Graph
 *
 *
 * 	Graph Layout
 *
 * 							width
 * 			<--------------------------------------->			
 *			 ______________________________________		
 * 		^	|										|	
 * 		|	|xAxisMargin				xAxisMargin	|	
 * 		|	|<--><----------------------------><--->|	^ 
 *		|	|	|		 Width Limit Range			|	|
 * 		|	|	|									|	|
 * 		|	|	|									|	|
 * 		|	|	|									|	| Height Limit Range 
 * 		|	|	|									|	|
 * Height	|	|									|	|
 * 		|	|	|									|	|
 * 		|	|	|									|	|
 * 		|	|	|									|	|
 * 		|	|	|______________________________		|	v
 * 		|	|							^			|	
 * 		|	|						yAxisMargin		|	
 * 		v	|___________________________v___________|	
 */

package uiPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphUI extends JPanel{

	int loanTerm;
	Double[] balance;
	int width 	= 0;
	int height	= 0;
	int xAxisMargin 	= 60;			// Margin between the frame and xAxis
	int yAxisMargin 	= 30;			// Margin between the frame and yAxis
	
	double widthLimiter 	= 1;		// width Limiter varies according to the loan term, if loan term is less width is high, else low
	double heightLimiter 	= 1;		// height Limiter varies according to the maximum balance value.

	// Initializes the required global data
	public void init(Double[] bal, int term)
	{
		loanTerm 	= term;
		balance 	= bal;
	}

	// Adds the graph to the frame
	public void paint(Graphics graphParameter)
	{
        super.paint(graphParameter);
        Graphics2D grap2D = (Graphics2D)graphParameter;
        
        width  = getWidth();		// Width of the graphics component in the frame
        height = getHeight();		// Height of the graphics component in the frame
        
		widthLimiter	= (width -  (2*xAxisMargin))/loanTerm;		
		heightLimiter 	= (height - (2*yAxisMargin))/balance[0];

		// Draw X axis Line
		grap2D.draw(new Line2D.Double(xAxisMargin, yAxisMargin - 10, xAxisMargin, height - yAxisMargin));
		
		// Draw Y axis Line
		grap2D.draw(new Line2D.Double(xAxisMargin, height - yAxisMargin, width - xAxisMargin + 20, height - yAxisMargin));

        // Draws X axis arrow
        grap2D.draw(new Line2D.Double(width - xAxisMargin + 15, height - yAxisMargin - 5, width - xAxisMargin + 20, height - yAxisMargin));
        grap2D.draw(new Line2D.Double(width - xAxisMargin + 15, height - yAxisMargin + 5, width - xAxisMargin + 20, height - yAxisMargin));
        
        // Draws Y axis arrow
		grap2D.draw(new Line2D.Double(xAxisMargin, yAxisMargin - 10, xAxisMargin - 5, yAxisMargin - 5));
		grap2D.draw(new Line2D.Double(xAxisMargin, yAxisMargin - 10, xAxisMargin + 5, yAxisMargin - 5));

		// Draw labels
        grap2D.drawString("0", xAxisMargin - 20, height - yAxisMargin );
        grap2D.drawString("Balance", 5, (height/2) );
        grap2D.drawString("Loan Term", (width/2)-yAxisMargin, height-8);
        grap2D.setPaint(Color.blue);
        
		for(int i  = 1; i <= loanTerm; i++)
		{
			// Draw line in graph, height of the line = Balance amount for that month
	        grap2D.draw(new Line2D.Double((xAxisMargin+(i*widthLimiter)), (height-yAxisMargin-(heightLimiter*balance[i-1])), xAxisMargin+i*widthLimiter, height-yAxisMargin));
            
	        // Draw a point in graph, point at the top of the line (above)
	        grap2D.fill(new Ellipse2D.Double((xAxisMargin+(i*widthLimiter)-2), (height-yAxisMargin-(heightLimiter*balance[i-1])-2), 4, 4));
		}
	}
	
	// Displays the frame which shows the graph
	public void displayGraph(Double[] balance, int loanTerm)
	{
		JFrame frame = new JFrame("Loan Amortization Schedule - Graph");
		GraphUI graphUILocal = new GraphUI();
		
		// Pass input values to initialize
		graphUILocal.init(balance, loanTerm);
		
		// Add the Graph to the Frame
		frame.add(graphUILocal);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
