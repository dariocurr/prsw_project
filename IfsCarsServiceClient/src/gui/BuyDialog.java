package gui;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import common.IBasket;
import common.IVehicle;


public class BuyDialog extends JDialog implements ActionListener{
	private JPanel panel;
	private IBasket basket;
	private GridBagConstraints constraint;
	private JLabel model;
	private JLabel price;
	private JLabel bankResult;
	private JTextField bankAccountNumber;
	private JComboBox<String> currencyComboBox;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public BuyDialog(JFrame parent, IBasket basket) {
		
		super(parent, "Confirm");
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		this.basket = basket;
		panel = new JPanel();
	    panel.setLayout(new GridBagLayout());
	    constraint = new GridBagConstraints();
	    this.model = new JLabel("model ");
	    this.price = new JLabel("price " + this.basket.getTotalPrice());
	    this.bankAccountNumber = new JTextField(); 
	    this.bankResult = new JLabel("bank processing...");
	    this.confirmButton = new JButton("CONFIRM");
	    this.cancelButton = new JButton("CANCEL");
	    this.currencyComboBox = new JComboBox<String>();
	    this.currencyComboBox.addItemListener(event -> {
	    	if (event.getStateChange() == ItemEvent.SELECTED) {
		          System.out.println(event.getItem().toString());
		          String s = "total: "+this.basket.getTotalPrice();
		          if (event.getItem().toString().equals("EUR")) {
		        	  s += "€";
		          }
		          if (event.getItem().toString().equals("USD")) {
		        	  s += "$";
		          }
		          if (event.getItem().toString().equals("GBD")) {
		        	  s += "£";
		          }
		          this.price.setText(s); 
		       }
	    });
		
		this.currencyComboBox.addItem("EUR");
		this.currencyComboBox.addItem("USD");
		this.currencyComboBox.addItem("GBD");
		this.constraint.insets = new Insets(24, 0, 0, 0);
		
		String a="";
        for(String models : basket.getVehicleModel()) {
        	a+=models+"<br>";
        }
		this.constraint.gridx = 0;
        this.constraint.gridy = 0;
        this.constraint.gridwidth = 2;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.constraint.gridwidth = 1;
        this.model.setText("<html> models:<br> "+ a+ "</html>");
        this.model.setFont(new Font(this.model.getName(),Font.PLAIN,16));
        this.panel.add(model,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.constraint.insets = new Insets(8, 0, 0, 8);
        this.price.setFont(new Font(this.price.getName(),Font.PLAIN,16));
        this.panel.add(price,constraint);
        
        this.constraint.gridx = 1;
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.panel.add(currencyComboBox,constraint);
        
        this.constraint.insets = new Insets(16, 0, 0, 8);
        this.constraint.gridwidth = 2;
        this.constraint.gridx = 0;
        this.constraint.gridy = 2;
        this.constraint.fill = GridBagConstraints.HORIZONTAL;
        this.panel.add(bankAccountNumber,constraint);
        
        this.constraint.insets = new Insets(0, 0, 8, 8);
        this.constraint.gridwidth = 1;
        this.constraint.gridx = 0;
        this.constraint.gridy = 3;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.panel.add(bankResult,constraint);
        
        this.constraint.insets = new Insets(8, 8, 24, 8);
        this.constraint.gridy = 4;
        this.constraint.gridx = 0;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 5;
        this.panel.add(cancelButton,constraint);
        
        this.constraint.gridy = 4;
        this.constraint.gridx = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.confirmButton.setEnabled(false);
        this.panel.add(confirmButton,constraint);
        
        
        
        this.cancelButton.addActionListener(ev -> {
        	dispose();
        });
        
        this.confirmButton.addActionListener(ev -> {
        	//do thighs 
        });
        
        setMinimumSize(new Dimension(400,200));
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
