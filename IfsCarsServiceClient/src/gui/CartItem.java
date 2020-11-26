package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.IVehicle;

/** Class for the items in the shopping cart */
public class CartItem extends JPanel{
	private JTextArea text;
	private JButton button;
	
	/** Main constructor which initializes the components
	 * @param vehicle the vehicle to be added in the shopping cart */
	public CartItem(IVehicle vehicle) {
		this.text = new JTextArea(2,13);
		text.setEditable(false);
		text.setBorder(BorderFactory.createCompoundBorder(
		        text.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		text.setText(vehicle.getModel()+" "+vehicle.getPrice());
		text.setLineWrap(true);
		this.button = new JButton("X");
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.LINE_START);
		this.add(button, BorderLayout.LINE_END);
	}
	
	/** Get the description of the vehicle in the textArea
	 * @return the description in the textArea */
	public String getText() {
		return text.getText();
	}
	
	/** {@inheritDoc} */
	@Override 
	public Dimension getPreferredSize() {
		return new Dimension(150, 50);
	}
	
	/** Get the delete button
	 * @return the delete button */
	public JButton getButton() {
		return this.button;
	}

}
