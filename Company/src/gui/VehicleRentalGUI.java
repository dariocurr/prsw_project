package gui;

import javax.swing.*;
import com.formdev.flatlaf.*;

//import common.Vehicle;

import java.awt.*;
import java.rmi.activation.ActivationGroupID;

public class VehicleRentalGUI {
	private JPanel rentPanel;
	private BoxLayout rentLayout;
	
	private JPanel returnPanel; 
	private BoxLayout returnLayout;
	
	private JPanel selectRentPanel;
	private BoxLayout selectRentLayout;
	
	private JPanel selectReturnPanel;
	private BoxLayout selectReturnLayout;
	
	private JPanel descriptionPanel;
	private JPanel notesPanel;
	
	private JPanel bodyRentPanel; 
	private JPanel bodyReturnPanel;
	
	private JPanel buttonRentPanel;
	private JPanel buttonReturnPanel;
	
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
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}


	private void startGUI() {
		this.rentPanel = new JPanel();
		this.rentLayout = new BoxLayout(this.rentPanel, BoxLayout.Y_AXIS);
		this.rentPanel.setLayout(this.rentLayout);
		
		this.returnPanel = new JPanel();
		this.returnLayout = new BoxLayout(this.returnPanel, BoxLayout.Y_AXIS);
		this.returnPanel.setLayout(this.returnLayout);
		
		this.selectRentPanel = new JPanel();
		this.selectRentPanel.setLayout(new GridBagLayout());
        this.constraint = new GridBagConstraints();
        this.constraint.fill = GridBagConstraints.HORIZONTAL;
		/*this.selectRentPanel = new JPanel();
		this.selectRentLayout = new BoxLayout(this.selectRentPanel, BoxLayout.Y_AXIS);
		this.selectRentPanel.setLayout(this.selectRentLayout);*/
		
		this.selectReturnPanel = new JPanel();
		this.selectReturnLayout = new BoxLayout(this.selectReturnPanel, BoxLayout.Y_AXIS);
		this.selectReturnPanel.setLayout(this.selectReturnLayout);
		
		this.descriptionPanel = new JPanel();
		this.notesPanel = new JPanel();
		
		this.bodyRentPanel = new JPanel();
		this.bodyReturnPanel = new JPanel();
		
		this.buttonReturnPanel = new JPanel();
		this.buttonRentPanel = new JPanel();
		
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
		//this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
       
		
		this.notesScrollPane = new JScrollPane(this.notesArea);
		this.notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes"));
        this.notesScrollPane.setSize(300,600);
      
		this.rentButton = new JButton("RENT");
		this.returnButton = new JButton("RETURN");
		
		this.tabPane = new JTabbedPane();
	
	}
	
	private void setRentTab() {
		constraint.insets = new Insets(16, 8, 8, 8);
        constraint.gridx = 0;
        constraint.gridy = 0;
        this.selectRentPanel.add(this.rentComboBox,constraint);
        
        constraint.gridx = 0;
        constraint.gridy = 1;
        this.vehicleImage = new ImageIcon("C:\\Users\\emanu\\Documents\\Coding\\Java\\Programmation Repartie\\prsw\\CarRental\\res\\car_img\\Fiat 500.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        this.selectRentPanel.add(this.vehicleRentLabel,constraint);
        
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
	
	
	/*private void setRentTab() {
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        
		this.selectRentPanel.add(this.rentComboBox);
		this.selectRentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		this.selectRentPanel.add(this.vehicleRentLabel);
		
		this.descriptionPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		this.descriptionPanel.add(this.descriptionScrollPane);
		
		this.bodyRentPanel.add(this.selectRentPanel);
		this.bodyRentPanel.add(this.descriptionPanel);
		this.bodyRentPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		
		this.buttonRentPanel.add(this.rentButton);
		this.buttonRentPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		
		this.rentPanel.add(this.bodyRentPanel);
		this.rentPanel.add(this.buttonRentPanel);
		
		this.tabPane.addTab("RENT A VEHICLE", this.rentPanel);
		this.tabPane.setSelectedIndex(0);
		
		this.frame.add(this.tabPane);
	}*/
	
	private void setReturnTab() {
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleReturnLabel = new JLabel(newVehicIcon);
        
		this.selectReturnPanel.add(this.returnComboBox);
		this.selectReturnPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		this.selectReturnPanel.add(this.vehicleReturnLabel);
		
		this.notesPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		this.notesPanel.add(this.notesScrollPane);
		
		this.bodyReturnPanel.add(this.selectReturnPanel);
		this.bodyReturnPanel.add(this.notesPanel);
		this.bodyReturnPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		
		this.buttonReturnPanel.add(this.returnButton);
		this.buttonReturnPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		
		this.returnPanel.add(this.bodyReturnPanel);
		this.returnPanel.add(this.buttonReturnPanel);
		
		this.tabPane.addTab("RETURN A VEHICLE", this.returnPanel);
	}
	

	public static void main(String[] args) {
		VehicleRentalGUI gui = new VehicleRentalGUI();
	}
	
	
}
