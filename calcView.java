package CP213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Entire calculator view that uses a calculator model and is launched in the MainFrame
 * @author John Slomka
 *
 */
@SuppressWarnings("serial")

public class calcView extends JPanel {
	
	
	/**
	 * Listener for any of the number buttons that are pressed
	 * 
	 * @author John Slomka
	 */
	private class NumberListener implements ActionListener {
		
		private String value = "";
		
		
		public NumberListener(final String value) {
			this.value = value;
		}
		
		/**
		 * Gives a value to X and Y as buttons are pressed
		 * @author John Slomka
		 */
		@Override
		public void actionPerformed(final ActionEvent evt) {
			// Checks if x already has a value
			if (calcView.this.model.getOp() == "") {
				calcView.this.model.setX(value);
			} 
			// Checks if y already has a value
			else {
				calcView.this.model.setY(value);
			}
		}
	}
	/**
	 * Listener for any operation that is pressed.
	 * @author John Slomka
	 */
	public class OperationListener implements ActionListener {
		
		private String op = "";
		
		public OperationListener(final String op) {
			this.op = op;
		}
		
		/**
		 * Checks if an operation has already been selected and selects it.
		 */
		@Override
		public void actionPerformed(final ActionEvent evt) {
			if (calcView.this.model.getOp() != "") {
				calcView.this.model.lock = true;
			} 
			calcView.this.model.setOp(this.op);
			
	}
	}
	/**
	 * Clears both displays by resetting the values in the model.
	 * @author John Slomka
	 *
	 */
	public class ClearListener implements ActionListener {
		
		@Override
		public void actionPerformed(final ActionEvent evt) {
			calcView.this.model.reset();
			calcView.this.result.setText("");
			
		}
	}
	
	/**
	 * Listens for when the equals sign is pressed and calculates the equation.
	 * @author John Slomka
	 *
	 */
	public class CalculateListener implements ActionListener {
		
		@Override
		public void actionPerformed(final ActionEvent evt) {
			if (calcView.this.model.lock == false) {
				calcView.this.model.calculate();
				}
			
			}
		}
	
	/**
	 * Listener that checks if the final result has changed
	 * @author John Slomka
	 *
	 */
	private class ResultChange implements PropertyChangeListener {
		
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			calcView.this.result.setText(calcView.f.format(calcView.this.model.getResult()));
		}
	}
	
	/**
	 * Listener that checks if the display has changed
	 * @author John Slomka
	 *
	 */
	private class DisplayChange implements PropertyChangeListener {
			
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				calcView.this.display.setText(calcView.this.model.sDisplay);
			}
		}
	
	/**
	 * All button declarations
	 */
	private final JButton zero = new JButton("0");
	private final JButton one = new JButton("1");
	private final JButton two = new JButton("2");
	private final JButton three = new JButton("3");
	private final JButton four = new JButton("4");
	private final JButton five = new JButton("5");
	private final JButton six = new JButton("6");
	private final JButton seven = new JButton("7");
	private final JButton eight = new JButton("8");
	private final JButton nine = new JButton("9");
	private final JButton divide = new JButton("/");
	private final JButton multiply = new JButton("*");
	private final JButton minus = new JButton("-");
	private final JButton add = new JButton("+");
	private final JButton clear = new JButton("C");
	private final JButton equal = new JButton("=");
	private static final DecimalFormat f = new DecimalFormat("###.###############");
	
	/**
	 * Label and accessory declarations
	 */
	Border border = LineBorder.createGrayLineBorder();
	private final JLabel display = new JLabel(" ");
	private final JLabel result = new JLabel(" ");
	private final CalcModel model;
	
	/**
	 * Registers the calculator views
	 * @param newModel
	 */
	public calcView(final CalcModel newModel) {
		this.model = newModel;
		this.layoutView();
		this.registerListeners();
		this.display.setText(calcView.this.model.sDisplay);
		this.result.setText("");
	}
	
	/**
	 * Sets up the view
	 * @author John Slomka
	 */
	private void layoutView() {
		/**
		 * Set up with two separate panels. One for the two screens and one for 
		 * the buttons. Both are then added to the main gridlayout.
		 */
		
		/**
		 * First panel containing the labels
		 */
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2,1));
		labelPanel.add(this.display);
		this.display.setHorizontalAlignment(SwingConstants.CENTER);
		this.display.setBorder(this.border);
		labelPanel.add(this.result);
		this.result.setHorizontalAlignment(SwingConstants.CENTER);
		this.result.setBorder(this.border);
		
		/**
		 * Second panel containing the buttons
		 */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,5));
		buttonPanel.add(labelPanel);
		buttonPanel.add(this.seven);
		buttonPanel.add(this.eight);
		buttonPanel.add(this.nine);
		buttonPanel.add(this.divide);
		buttonPanel.add(this.clear);
		buttonPanel.add(this.four);
		buttonPanel.add(this.five);
		buttonPanel.add(this.six);
		buttonPanel.add(this.multiply);
		buttonPanel.add(new JButton());
		buttonPanel.add(this.one);
		buttonPanel.add(this.two);
		buttonPanel.add(this.three);
		buttonPanel.add(this.minus);
		buttonPanel.add(new JButton());
		buttonPanel.add(new JButton());
		buttonPanel.add(this.zero);
		buttonPanel.add(new JButton());
		buttonPanel.add(this.add);
		buttonPanel.add(this.equal);
		
		/**
		 * Both panels are added to the main grid
		 */
		this.setLayout(new GridLayout(2,1));
		this.add(labelPanel);
		this.add(buttonPanel);
		
	}
	
	/**
	 * Contains all of the registered listeners
	 * @author John Slomka
	 *
	 */
	private void registerListeners() {
		
		this.zero.addActionListener(new NumberListener("0"));
		this.one.addActionListener(new NumberListener("1"));
		this.two.addActionListener(new NumberListener("2"));
		this.three.addActionListener(new NumberListener("3"));
		this.four.addActionListener(new NumberListener("4"));
		this.five.addActionListener(new NumberListener("5"));
		this.six.addActionListener(new NumberListener("6"));
		this.seven.addActionListener(new NumberListener("7"));
		this.eight.addActionListener(new NumberListener("8"));
		this.nine.addActionListener(new NumberListener("9"));
	
		this.equal.addActionListener(new CalculateListener());
		
		this.divide.addActionListener(new OperationListener("/"));
		this.multiply.addActionListener(new OperationListener("*"));
		this.minus.addActionListener(new OperationListener("-"));
		this.add.addActionListener(new OperationListener("+"));
		this.clear.addActionListener(new ClearListener());
		
		this.model.addPropertyChangeListener(CalcModel.RESULT_CHANGE, new ResultChange());
		this.model.addPropertyChangeListener(CalcModel.DISPLAY_CHANGE, new DisplayChange());
	}
	
	
	
	
}
