package gui;

import javax.swing.*;
import com.formdev.flatlaf.*;

//import common.Vehicle;

import java.awt.*;
import java.rmi.activation.ActivationGroupID;

public class VehicleRentalGUI {
	
	private JPanel selectRentPanel;
	
	private JPanel selectReturnPanel;
	
	private JComboBox<String> rentComboBox;
	private JComboBox<String> returnComboBox;
	
	private ImageIcon vehicleImage;
	private JLabel vehicleRentLabel;
	private JLabel vehicleReturnLabel;
	
	private JTextArea descriptionArea;
	private JTextArea notesArea;
	
	private JScrollPane descriptionScrollPane;
	private JScrollPane notesScrollPane;
	
	private JButton rentButton;
	private JButton returnButton;
	
	private JTabbedPane tabPane;
	
	private String[] vehiclesRentable;
	private String[] vehiclesRented;
	
	private JFrame frame;
	
	private GridBagConstraints constraint;
	
	
	public VehicleRentalGUI() {
		
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    if(!FlatDarkLaf.install()) {
		    	System.err.println( "Failed to initialize dark theme" );
		    	
		    }
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		this.frame = new JFrame("Vehicles Rental");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.startGUI();
		this.setRentTab();
		this.setReturnTab();
		
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(720, 500));
		frame.setMinimumSize(new Dimension(580, 400));
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}


	private void startGUI() {
		
		this.selectRentPanel = new JPanel();
		this.selectRentPanel.setLayout(new GridBagLayout());
        
        this.selectReturnPanel = new JPanel();
        this.selectReturnPanel.setLayout(new GridBagLayout());
		
		this.vehiclesRentable = new String[] {"Giulietta", "Merceder 34736hh", "Ferrari dgdg223"};
		this.vehiclesRented = new String[] {"500", "Merceder 34436hh", "Lamborghini dgdg223"};
		
		this.rentComboBox = new JComboBox<>(this.vehiclesRentable);
		this.rentComboBox.setPrototypeDisplayValue("text long like this or more..... ");
		this.returnComboBox = new JComboBox<>(this.vehiclesRented);
		this.returnComboBox.setPrototypeDisplayValue("text long like this or more.... ");
		
		this.descriptionArea = new JTextArea(10, 30);
		this.descriptionArea.setOpaque(false);
		this.descriptionArea.setText("Model:\nKm:\nTrasmission:");
		this.descriptionArea.setEditable(false);
		
		this.notesArea = new JTextArea(10, 30);
		
		
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
       
		
		this.notesScrollPane = new JScrollPane(this.notesArea);
		this.notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes"));
        this.notesScrollPane.setSize(300,600);
      
		this.rentButton = new JButton("RENT");
		this.returnButton = new JButton("RETURN");
		
		this.tabPane = new JTabbedPane();
	
	}
	
	private void setRentTab() {
		constraint = new GridBagConstraints();
		constraint.insets = new Insets(16, 8, 8, 8);
        constraint.gridx = 0;
        constraint.gridy = 0;
        this.selectRentPanel.add(this.rentComboBox,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        //this.vehicleImage = new ImageIcon("C:\\Users\\emanu\\Documents\\Coding\\Java\\Programmation Repartie\\prsw\\CarRental\\res\\car_img\\Fiat 500.png");
        this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        this.selectRentPanel.add(this.vehicleRentLabel,constraint);
        
        constraint.insets = new Insets(10, 8, 8, 8);
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridheight = 2;
        constraint.fill = GridBagConstraints.VERTICAL;
        this.selectRentPanel.add(this.descriptionScrollPane,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.ipady = 15;
        constraint.ipadx = 20;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        constraint.anchor = GridBagConstraints.NORTH;
        constraint.fill = GridBagConstraints.NONE;
        this.selectRentPanel.add(this.rentButton,constraint);
        
        
        
		this.tabPane.addTab("RENT A VEHICLE", this.selectRentPanel);
		this.tabPane.setSelectedIndex(0);
		
		this.frame.add(this.tabPane);
		
	}
	
	
	private void setReturnTab() {
		constraint = new GridBagConstraints();
        constraint.insets = new Insets(16, 8, 8, 8);
        constraint.gridx = 0;
        constraint.gridy = 0;
        this.selectReturnPanel.add(this.returnComboBox,constraint);
        
        constraint.gridx = 0;
		constraint.gridy = 1;
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleReturnLabel = new JLabel(newVehicIcon);
        this.selectReturnPanel.add(this.vehicleReturnLabel, constraint);
		
		
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridheight = 2;
        constraint.insets = new Insets(10, 8, 8, 8);
        constraint.fill = GridBagConstraints.VERTICAL;
		this.selectReturnPanel.add(this.notesScrollPane, constraint);
		
		
		constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.ipady = 15;
        constraint.ipadx = 20;
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        constraint.anchor = GridBagConstraints.NORTH;
        constraint.fill = GridBagConstraints.NONE;
        this.selectReturnPanel.add(this.returnButton,constraint);
		
		
		this.tabPane.addTab("RETURN A VEHICLE", this.selectReturnPanel);
	}
	

	public static void main(String[] args) {
		VehicleRentalGUI gui = new VehicleRentalGUI();
	}
	
	
}
