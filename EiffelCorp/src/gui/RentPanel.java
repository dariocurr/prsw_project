package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.AttributedString;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.IRenterObserver;
import common.IVehicle;
import company.ClientProxy;

/** Class for the panel in the gui used to rent a car */
public class RentPanel extends JPanel {
	
	private JComboBox<VehicleComboItem> rentComboBox;
	private ImageIcon vehicleImage;
	private JLabel vehicleRentLabel;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScrollPane;
	private JButton rentButton;
	private IRenterObserver renter;
	private ClientProxy clientProxy;
	private JLabel isAvailable;
	
	private List<IVehicle> vehiclesRentable;
	
	private GridBagConstraints constraint;
	
	/** Main constructor, it initializes the proxy and it calls the method to initialize all the gui components 
	 * @param renter the employee who logged in */
	public RentPanel(IRenterObserver renter) throws MalformedURLException, RemoteException, NotBoundException {
		super();
		this.setLayout(new GridBagLayout());
		
		this.clientProxy = new ClientProxy();
		this.renter = renter;
		
		this.initComponents();
	}
	
	
	private void initComponents() {
		try {
			this.vehiclesRentable = this.clientProxy.getAvailableVehicles();
		} catch (RemoteException e) {
			this.generateError("Impossible to fecth avalaible cars");
		}
		
		this.rentComboBox = new JComboBox<VehicleComboItem>();
		
		for(IVehicle vehicle : vehiclesRentable) {
			this.rentComboBox.addItem(new VehicleComboItem(vehicle));
		}
		
		this.rentComboBox.addItemListener(new ItemChangeListener());
		
		this.isAvailable = new JLabel("AVAILABLE");
		
		this.vehicleRentLabel = new JLabel();
		
		this.descriptionArea = new JTextArea(10, 30);
		this.descriptionArea.setOpaque(false);
		this.descriptionArea.setText("Model:\nKm:\nTrasmission:");
		this.descriptionArea.setEditable(false);
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
        this.rentButton = new JButton("RENT");
        
        this.rentButton.addActionListener(new RentActionListener());
        
        this.paintComponents();
	}
	
	private void paintComponents() {
		this.constraint = new GridBagConstraints();
		this.constraint.insets = new Insets(16, 8, 8, 8);
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
        this.add(this.rentComboBox,constraint);
		
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        
        if(!this.vehiclesRentable.isEmpty()) {
	        this.paintImage(this.vehiclesRentable.get(0).getFileName());
	        this.add(this.vehicleRentLabel,constraint);
	        try {
				this.paintDescription(this.vehiclesRentable.get(0));
			} catch (RemoteException e) {
				this.descriptionArea.setText("Impossibile to print description of the car");
			}
        }
        
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
	
	/** Listener class used when the vehicle in the combobox is changed */
	class ItemChangeListener implements ItemListener{
		
		/** Paint the image and the description of the selected vehicle.
		 * @param event the event called by the listener.*/
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          IVehicle v = ((VehicleComboItem) event.getItem()).getVehicle();
	          paintImage(v.getFileName());
	          try {
				paintDescription(v);
			} catch (RemoteException e) {
				generateError("Impossible to print image of the car");
			}
	          
	       }
	    }       
	}
	
	/** Listener class used when the employee click on the rent button */
	class RentActionListener implements ActionListener{
		
		/** Rent a vehicle and remove it from the combobox.
		 * @param event the event of the listener*/
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if(rentComboBox.getSelectedItem() != null) {
				IVehicle vehicle =((VehicleComboItem)rentComboBox.getSelectedItem()).getVehicle();
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
				LocalDateTime startDate = LocalDateTime.now();
				
				
				String endDate = startDate.plusMonths(3).format(dtf);
				try {
					if(clientProxy.rentVehicle(renter, vehicle, startDate.format(dtf), endDate, "EMP001") == null) {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "The vehicle is not available now. Do you want to be in the Wait List?","Warning",dialogButton);
						if(dialogResult == JOptionPane.YES_OPTION){
							rentComboBox.removeItem(((VehicleComboItem)rentComboBox.getSelectedItem()));
							clientProxy.attach(renter, vehicle, startDate.format(dtf), endDate, "EMP001");
						}
						
					}
					else
						rentComboBox.removeItem(((VehicleComboItem)rentComboBox.getSelectedItem()));
	
					//ReturnPanel.this.returnComboBox.addItem(((VehicleComboItem)rentComboBox.getSelectedItem()));
				} catch (RemoteException e) {
					generateError("Rent failed.");
				}
			
			}
		}
	}
	
	/** It paints the image of the car.
	 * @param file_name the name of the image's file.*/
	public void paintImage(String file_name) {
         ImageIcon vehicleImg = new ImageIcon("res" + File.separator + "car_img" + File.separator + file_name);
         Image image = vehicleImg.getImage();
         Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
         this.vehicleImage = new ImageIcon(newImg);
         this.vehicleRentLabel.setIcon(vehicleImage);
	}


	/** It paints the description of a vehicle.
	 * @param v the vehicle which description has to be painted.*/
	public void paintDescription(IVehicle v) throws RemoteException {
		Map<IVehicle, String> map = this.clientProxy.getNotAvailableVehicles();
		if(map.containsKey(v))
			this.descriptionArea.setText("	  AVAILABLE FROM " + map.get(v) + "\n\n" + "Model: " + v.getModel() + "\n" + "Year: " + v.getYear() + "\n" + "Seats: " + v.getSeats() + "\n" + "Doors: " + v.getDoors() + "\n" + "Transmission: " + v.getTrasmission() + "\n" + "Size: " + v.getSize() + "\n" + "Price per day: " + v.getPricePerDay() + "�");
		else
			this.descriptionArea.setText("	              AVAILABLE" + "\n\n" + "Model: " + v.getModel() + "\n" + "Year: " + v.getYear() + "\n" + "Seats: " + v.getSeats() + "\n" + "Doors: " + v.getDoors() + "\n" + "Transmission: " + v.getTrasmission() + "\n" + "Size: " + v.getSize() + "\n" + "Price per day: " + v.getPricePerDay() + "�");
		
	}
	
	private void generateError(String error) {
		JOptionPane.showMessageDialog(this,
			    error,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

}