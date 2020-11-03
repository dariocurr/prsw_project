package gui;

import javax.swing.*;

import common.Vehicle;

import java.awt.*;

public class VehicleRentalGUI {
	private JPanel rentPanel; //mainPanel
	private BoxLayout rentLayout; //mainBoxLayout
	
	private JPanel returnPanel; //secondPanel
	private BoxLayout returnLayout; //secondBoxLayout
	
	private JPanel selectRentPanel; //panel 3
	private BoxLayout selectRentLayout; //boxLayout 2
	
	private JPanel selectReturnPanel; //panel 6
	private BoxLayout selectReturnLayout; //boxLayout
	
	private JPanel descriptionPanel; //panel 4
	private JPanel notesPanel;
	
	private JPanel bodyRentPanel; //panel 2
	private JPanel bodyReturnPanel; //panel 7
	
	private JPanel buttonRentPanel; //panel 5
	private JPanel buttonReturnPanel; //panel 8
	
	private JComboBox<String> rentComboBox; //comboBox
	private JComboBox<String> returnComboBox; //comboBox 2
	
	private ImageIcon vehicleImage; //icon
	private JLabel vehicleRentLabel; //label
	private JLabel vehicleReturnLabel; //label 2
	
	private JTextArea descriptionArea; //textArea
	private JTextArea notesArea; //textArea2
	
	private JScrollPane descriptionScrollPane; //scroll
	private JScrollPane notesScrollPane; //scroll 2
	
	private JButton rentButton; //jb3
	private JButton returnButton; //jb4
	
	private JTabbedPane tabPane; //jtbExample
	
	private String[] vehiclesRentable;
	private String[] vehiclesRented;
	
	
	public VehicleRentalGUI() {
		JFrame frame = new JFrame("Vehicles Rental");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.startGUI();
		this.setRentTab(frame);
		
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}


	private void startGUI() {
		this.rentPanel = new JPanel();
		this.rentLayout = new BoxLayout(this.rentPanel, BoxLayout.Y_AXIS);
		
		this.returnPanel = new JPanel();
		this.returnLayout = new BoxLayout(this.returnPanel, BoxLayout.Y_AXIS);
		
		this.selectRentPanel = new JPanel();
		this.selectRentLayout = new BoxLayout(this.selectRentPanel, BoxLayout.Y_AXIS);
		
		this.selectReturnPanel = new JPanel();
		this.selectReturnLayout = new BoxLayout(this.selectReturnPanel, BoxLayout.Y_AXIS);
		
		this.descriptionPanel = new JPanel();
		this.notesPanel = new JPanel();
		
		this.bodyRentPanel = new JPanel();
		this.bodyReturnPanel = new JPanel();
		
		this.buttonReturnPanel = new JPanel();
		this.buttonRentPanel = new JPanel();
		
		this.vehiclesRentable = new String[] {"Giulietta", "Merceder 34736hh", "Ferrari dgdg223"};
		this.vehiclesRentable = new String[] {"500", "Merceder 34436hh", "Lamborghini dgdg223"};
		
		this.rentComboBox = new JComboBox<>(this.vehiclesRentable);
		this.returnComboBox = new JComboBox<>(this.vehiclesRented);
		
		this.descriptionArea = new JTextArea(10, 30);
		this.notesArea = new JTextArea(10, 30);
		
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.notesScrollPane = new JScrollPane(this.notesArea);
      
		this.rentButton = new JButton("RENT");
		this.returnButton = new JButton("RETURN");
		
		this.tabPane = new JTabbedPane();
		
		this.descriptionArea.setText("Model:\nKm:\nTrasmission:");
		this.descriptionArea.setEditable(false);
	
	}
	
	
	private void setRentTab(JFrame frame) {
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        
			
	}
	

	public static void main(String[] args) {
		
	}
	
	
}
