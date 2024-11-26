import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CloseFrameOnClick extends JFrame
{
	public CloseFrameOnClick() 
	{
        // Set up the frame
        setTitle("Close Frame Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the button
        JButton closeButton = new JButton("Click to Close");
        
        // Add action listener to close the frame when clicked
        closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        // Add the button to the frame
        add(closeButton);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new CloseFrameOnClick();
    }
}
