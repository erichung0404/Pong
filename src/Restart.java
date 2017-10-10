import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Restart extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public JButton restart = new JButton("Restart");
	
	public Restart() {
		Container cp = getContentPane(); 
	    cp.setLayout(new BorderLayout());
	    
  		JPanel bottomPanel = new JPanel();
  		bottomPanel.add(restart);
  		cp.add(bottomPanel, BorderLayout.SOUTH);
	    
	    restart.addActionListener(new ActionListener() {

	          public void actionPerformed(ActionEvent e) {
	        	//Creating the window with all its awesome snaky features
	      		Main thisClass= new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
	      		
	      		//Close Menu once select
	      		String cmd = e.getActionCommand();
	      		if(cmd.equals("Restart"))
	            {
	                dispose();
	            }
	          };
	      });
	}
}