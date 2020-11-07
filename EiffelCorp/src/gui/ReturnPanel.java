package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import common.IRent;
import common.IRenterObserver;
import common.IVehicle;
import company.ClientProxy;

public class ReturnPanel extends JPanel{

	private JComboBox<String> returnComboBox;
	
	private ImageIcon vehicleImage;
	private JLabel vehicleReturnLabel;
	private JTextArea notesArea;
	private JScrollPane notesScrollPane;
	private JButton returnButton;
	
	private List<IVehicle> vehiclesRented;
	
	private GridBagConstraints constraint;
	
	private ClientProxy clientProxy;
	private List<IRent> rents;
	private IRenterObserver renter;
	
	public ReturnPanel() throws MalformedURLException, RemoteException, NotBoundException {
		super();
		this.setLayout(new GridBagLayout());
		
		//INSERIRE RENTER this.renter = 
		this.clientProxy = new ClientProxy();
		this.rents = this.clientProxy.getRenterRentals(renter);
		
		
		this.loadVehicles();
		this.initComponents();
		this.paintComponents();
	}
	
	private void loadVehicles() {
		for(IRent rent : this.rents)
			this.vehiclesRented.add(rent.getVehicle());
	}
	
	private void initComponents() {
		ArrayList<String> modelVehiclesRented = new ArrayList<>();
		for(IVehicle vehicle : this.vehiclesRented)
			modelVehiclesRented.add(vehicle.getModel());
		
		this.returnComboBox = new JComboBox<String>();
		this.returnComboBox.setModel(new DefaultComboBoxModel<String>(modelVehiclesRented.toArray(new String[0])));
		this.returnComboBox.setPrototypeDisplayValue("text long like this or more.... ");
		this.notesArea = new JTextArea(10, 30);
		this.notesScrollPane = new JScrollPane(this.notesArea);
		this.notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes"));
        this.notesScrollPane.setSize(300,600);
		this.returnButton = new JButton("RETURN");
	}

	private void paintComponents() {
		this.constraint = new GridBagConstraints();
		this.constraint.insets = new Insets(16, 8, 8, 8);
        this.constraint.gridx = 0;
        this.constraint.gridy = 0;
        this.add(this.returnComboBox,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage(); // transform it
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleReturnLabel = new JLabel(newVehicIcon);
        this.add(this.vehicleReturnLabel, constraint);
		
		
        this.constraint.gridx = 1;
        this.constraint.gridy = 0;
        this.constraint.gridheight = 2;
        this.constraint.insets = new Insets(10, 8, 8, 8);
        this.constraint.fill = GridBagConstraints.VERTICAL;
		this.add(this.notesScrollPane, constraint);
		
		
		this.constraint.gridx = 0;
		this.constraint.gridy = 2;
        this.constraint.ipady = 15;
        this.constraint.ipadx = 20;
        this.constraint.gridwidth = GridBagConstraints.REMAINDER;
        this.constraint.anchor = GridBagConstraints.NORTH;
        this.constraint.fill = GridBagConstraints.NONE;
        this.add(this.returnButton,constraint);
	}


}