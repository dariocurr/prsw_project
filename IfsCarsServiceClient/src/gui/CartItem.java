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

public class CartItem extends JPanel{
	private JTextField text;
	private JButton button;
	
	/*public CartItem() {
		this.text = new JTextField(13);
		text.setEditable(false);
		text.setBorder(BorderFactory.createCompoundBorder(
		        text.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		text.setText("macchina 111111111111111111");
		this.button = new JButton("X");
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.LINE_START);
		this.add(button, BorderLayout.LINE_END);
	}*/
	
	public CartItem(IVehicle vehicle) {
		this.text = new JTextField(13);
		text.setEditable(false);
		text.setBorder(BorderFactory.createCompoundBorder(
		        text.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		text.setText(vehicle.getModel()+" "+vehicle.getPrice());
		this.button = new JButton("X");
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.LINE_START);
		this.add(button, BorderLayout.LINE_END);	
	}
	
	public String getText() {
		return text.getText();
	}
	
	@Override
	public Dimension getPreferredSize() {
		if (isPreferredSizeSet()) {
			return super.getPreferredSize();
			}
		return new Dimension(150, 100);
	}
	
	public JButton getButton() {
		return this.button;
	}

}
