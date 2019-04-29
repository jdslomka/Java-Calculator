package CP213;
import javax.swing.JFrame;

/**
 * Main function to run calculator view contained with the main frame class
 * @author John Slomka
 *
 */
public class MainFrame {

	public static void main(final String args[]) {
		final CalcModel model = new CalcModel();
		
		final calcView view = new calcView(model);
		
		final JFrame f = new JFrame("Calculator");
		f.setContentPane(view);
		f.setSize(350,420);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	
	
	
	
	
	
}
