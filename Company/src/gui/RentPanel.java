package gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RentPanel extends JPanel {
	
	private JComboBox<String> rentComboBox;
	private ImageIcon vehicleImage;
	private JLabel vehicleRentLabel;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScrollPane;
	private JButton rentButton;
	
	private String[] vehiclesRentable;
	
	private GridBagConstraints constraint;
	
	public RentPanel() {
		super();
		this.setLayout(new GridBagLayout());
		
		this.loadVehicles();
		this.initComponents();
		this.paintComponents();
	}
	
	private void loadVehicles() {
		this.vehiclesRentable = new String[] {"Giulietta", "Merceder 34736hh", "Ferrari dgdg223"};
	}
	
	private void initComponents() {
		this.rentComboBox = new JComboBox<>(this.vehiclesRentable);
		this.rentComboBox.setPrototypeDisplayValue("text long like this or more..... ");
		this.descriptionArea = new JTextArea(10, 30);
		this.descriptionArea.setOpaque(false);
		this.descriptionArea.setText("Model:\nKm:\nTrasmission:");
		this.descriptionArea.setEditable(false);
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
        this.rentButton = new JButton("RENT");
	}
	
	private void paintComponents() {
		this.constraint = new GridBagConstraints();
		this.constraint.insets = new Insets(16, 8, 8, 8);
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
        this.add(this.rentComboBox,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        this.add(this.vehicleRentLabel,constraint);
        
        this.constraint.insets = new Insets(10, 8, 8, 8);
        this.constraint.gridx = 1;
        this.constraint.gridy = 0;
        this.constraint.gridheight = 2;
        this.constraint.fill = GridBagConstraints.VERTICAL;
        this.add(this.descriptionScrollPane,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 2;
        this.constraint.ipady = 15;
        this.constraint.ipadx = 20;
        this.constraint.gridwidth = GridBagConstraints.REMAINDER;
        this.constraint.anchor = GridBagConstraints.NORTH;
        this.constraint.fill = GridBagConstraints.NONE;
        this.add(this.rentButton,constraint);
	}

}
