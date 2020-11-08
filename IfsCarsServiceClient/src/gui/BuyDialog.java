package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.WindowConstants;

public class BuyDialog extends JDialog implements ActionListener{
	private JPanel panel;
	private GridBagConstraints constraint;
	private JLabel text;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public BuyDialog(JFrame parent) {
		
		super(parent, "Confirm");
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		setModalityType(ModalityType.DOCUMENT_MODAL);
		
		panel = new JPanel();
		
	    panel.setLayout(new GridBagLayout());
	    constraint = new GridBagConstraints();
	    this.text = new JLabel("provaaaaaaaaaaaaaa");
	    this.confirmButton = new JButton("CONFIRM");
	    this.cancelButton = new JButton("CANCEL");
        
	    this.constraint.insets = new Insets(8, 8, 8, 8);
        this.constraint.gridx = 0;
        this.constraint.gridy = 0;
        this.panel.add(text,constraint);
        
        
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.panel.add(cancelButton,constraint);
        
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.constraint.insets = new Insets(8, 92, 8, 8);
        
        
        this.panel.add(confirmButton,constraint);
        
        this.cancelButton.addActionListener(ev -> {
        	//do thighs 
        });
        
        this.confirmButton.addActionListener(ev -> {
        	//do thighs 
        });
        
        
        
        setMinimumSize(new Dimension(300,150));
        setLocationRelativeTo(parent);
	    add(panel);
	    pack();
	    setVisible(true); 
	}
	
	public void actionPerformed(ActionEvent ae) {
      Object source = ae.getSource();
      
      dispose();
   }

}
