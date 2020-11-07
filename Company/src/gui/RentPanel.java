package gui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.IVehicle;
import company.ClientProxy;

public class RentPanel extends JPanel {
	
	private JComboBox<String> rentComboBox;
	private ImageIcon vehicleImage;
	private JLabel vehicleRentLabel;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScrollPane;
	private JButton rentButton;
	private ClientProxy clientProxy;
	
	private List<IVehicle> vehiclesRentable;
	
	private GridBagConstraints constraint;
	
	public RentPanel() throws MalformedURLException, RemoteException, NotBoundException {
		super();
		this.setLayout(new GridBagLayout());
		
		this.clientProxy = new ClientProxy();
		
		this.vehiclesRentable = this.clientProxy.getAvailableVehicles();
		
		this.initComponents();
		this.paintComponents();
	}
	
	
	private void initComponents() {
		ArrayList<String> modelVehiclesRentable = new ArrayList<>();
		for(IVehicle vehicle : this.vehiclesRentable)
			modelVehiclesRentable.add(vehicle.getModel());
		
		this.rentComboBox = new JComboBox<String>();
		this.rentComboBox.setModel(new DefaultComboBoxModel<String>(modelVehiclesRentable.toArray(new String[0])));
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
