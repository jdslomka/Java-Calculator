package CP213;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Calculator model that contains appropriate methods 
 * and variables in order to generate a calculator
 * @author John Slomka
 *
 */
public class CalcModel {
	public static final String RESULT_CHANGE = "Result Changed";
	public static final String DISPLAY_CHANGE = "Display Changed";
	public static String x = "";
	public static String y = "";
	public static String op = "";
	public static double result = 0.0;
	public String sDisplay = "";
	public static boolean lock = false;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	
	// ---------------------------------------------------------------
    /**
     * Attaches listeners to the model.
     * 
     * @param listener
     *            The listener to attach to the model.
     */
	public void addPropertyChangeListener(
		final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	    }
	
	
	// ---------------------------------------------------------------
    /**
     * Attaches listeners to the model for a particular property.
     * 
     * @param propertyName
     *            The name of the property to listen for.
     * @param listener
     *            The listener to attach to the model.
     */
    public void addPropertyChangeListener(final String propertyName,
	    final PropertyChangeListener listener) {
    	this.pcs.addPropertyChangeListener(propertyName, listener);
    }
    
    /**
     * Sets value of x
     * @param x
     */
    public void setX(final String x) {
    	this.x += x;
    	this.sDisplay += x;
    	this.pcs.firePropertyChange(CalcModel.DISPLAY_CHANGE, null, this.sDisplay);
    }
    
    /**
     * Sets value of y
     * @param y
     */
    public void setY(final String y) {
    	this.y += y;
    	this.sDisplay += y;
    	this.pcs.firePropertyChange(CalcModel.DISPLAY_CHANGE, null, this.sDisplay);
    }
    /**
     * Sets the operation
     * @param op
     */
    public void setOp(final String op) {
    	this.op = op;
    	this.sDisplay += " " + this.op + " ";
    	this.pcs.firePropertyChange(CalcModel.DISPLAY_CHANGE, null, this.sDisplay);
    }
    /**
     * Gets the operation
     * @return 
     */
    public String getOp() {
    	return this.op;
    }
	/**
	 * Gets value of x
	 * @return
	 */
    public int getX() {
    	return Integer.parseInt(this.x);
    }
	/**
	 * Gets value of y
	 * @return
	 */
    public int getY() {
    	return Integer.parseInt(this.y);
    }
    /**
     * Gets result value
     * @return
     */
    public double getResult() {
    	return this.result;
    }
	
    /**
     * Resets all data values to original value and notifies listeners
     */
    public void reset() {
    	this.lock = false;
    	this.x = "0";
    	this.y = "0";
    	this.op = "";
    	this.sDisplay = "";
    	this.pcs.firePropertyChange(CalcModel.DISPLAY_CHANGE, null, this.sDisplay);
    	this.result = 0;
    	this.pcs.firePropertyChange(CalcModel.RESULT_CHANGE, null, this.result);
    	
    }
	
    
    /**
     * Calculates equation and sets result equal to the result with the correct operation
     */
    public void calculate() {
    	if (x != "" && y != "") {
    	
    	double xx = Double.parseDouble(this.x);
    	double yy = Double.parseDouble(this.y);
    	
    	switch (this.op) {
    	case "/":
    		this.result = (double)xx / yy;
    		break;
    	case "*":
    		this.result = xx * yy;
    		break;
    	case "+":
    		this.result = xx + yy;
    		break;
    	case "-":
    		this.result = xx - yy;
    		break;
    		
    	default:
    		System.out.println("Operation does not match cases.");
    		break;
    	}
    	this.pcs.firePropertyChange(CalcModel.RESULT_CHANGE, null, this.result);
    	} 
    }
	
}
