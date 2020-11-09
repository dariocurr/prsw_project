package gui;

import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import common.IBasket;
import common.IVehicle;
import gui.ClientGUI.ItemChangeListener;

public class BuyDialog extends JDialog implements ActionListener{
	private JPanel panel;
	private IBasket basket;
	private GridBagConstraints constraint;
	private JLabel model;
	private JLabel price;
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
	    this.confirmButton = new JButton("CONFIRM");
	    this.cancelButton = new JButton("CANCEL");
	    this.currencyComboBox = new JComboBox<String>();
		this.currencyComboBox.addItemListener(new ItemChangeListener());
		this.currencyComboBox.addItem("EUR");
		this.currencyComboBox.addItem("USD");
		this.currencyComboBox.addItem("GBD");
	    
        this.constraint.gridx = 0;
        this.constraint.gridy = 0;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        //this.model.setText("<html><br>model1<br>model2</html>");
        this.model.setFont(new Font(this.model.getName(),Font.PLAIN,18));
        this.panel.add(model,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.price.setFont(new Font(this.price.getName(),Font.PLAIN,18));
        this.panel.add(price,constraint);
        
        this.constraint.gridx = 1;
        this.constraint.gridy = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.panel.add(currencyComboBox,constraint);
        
        this.constraint.insets = new Insets(8, 8, 8, 8);
        this.constraint.gridy = 2;
        this.constraint.gridx = 0;
        this.constraint.anchor = GridBagConstraints.LINE_START;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 5;
        this.panel.add(cancelButton,constraint);
        
        this.constraint.gridy = 2;
        this.constraint.gridx = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.panel.add(confirmButton,constraint);
        
        this.cancelButton.addActionListener(ev -> {
        	dispose();
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

	class ItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          System.out.println(event.getItem().toString());
	          
	       }
	    }
	
}
}
