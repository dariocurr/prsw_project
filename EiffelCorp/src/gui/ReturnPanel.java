package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import gui.RentPanel.ItemChangeListener;

public class ReturnPanel extends JPanel{

	private JComboBox<VehicleComboItem> returnComboBox;
	
	private ImageIcon vehicleImage;
	private JLabel vehicleReturnLabel;
	private JTextArea notesArea;
	private JScrollPane notesScrollPane;
	private JButton returnButton;
	
	private List<IVehicle> vehiclesRented;
	
	private GridBagConstraints constraint;
	
	private List<IRent> rents;
	private IRenterObserver renter;

	private ClientProxy clientProxy;
	
	public ReturnPanel(IRenterObserver renter) throws MalformedURLException, RemoteException, NotBoundException {
		super();
		this.setLayout(new GridBagLayout());
		
		this.renter = renter; 
		this.clientProxy = new ClientProxy();
		this.rents = this.clientProxy.getRenterRentals(renter);
		
		this.loadVehicles();
		this.initComponents();
		this.paintComponents();
	}
	
	private void loadVehicles() {
		this.vehiclesRented = new ArrayList<>();
		if(this.rents != null)
			for(IRent rent : this.rents)
				this.vehiclesRented.add(rent.getVehicle());
	}
	
	
	private void initComponents() {
		/*ArrayList<String> modelVehiclesRented = new ArrayList<>();
		for(IVehicle vehicle : this.vehiclesRented)
			modelVehiclesRented.add(vehicle.getModel());*/
		
		this.returnComboBox = new JComboBox<VehicleComboItem>();
		//this.rentComboBox.setModel(new DefaultComboBoxModel<String>(modelVehiclesRentable.toArray(new String[0])));
		//this.returnComboBox.setPrototypeDisplayValue("text long like this or more..... ");
		
		for(IVehicle vehicle : this.vehiclesRented) {
			this.returnComboBox.addItem(new VehicleComboItem(vehicle));
		}
		
		this.returnComboBox.addItemListener(new ItemChangeListener());
		this.notesArea = new JTextArea(10, 30);
		this.notesScrollPane = new JScrollPane(this.notesArea);
		this.notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes"));
        this.notesScrollPane.setSize(300,600);
		this.returnButton = new JButton("RETURN");
		this.returnButton.addActionListener(new ReturnActionListener());
	}

	private void paintComponents() {
		this.constraint = new GridBagConstraints();
		this.constraint.insets = new Insets(16, 8, 8, 8);
        this.constraint.gridx = 0;
        this.constraint.gridy = 0;
        this.add(this.returnComboBox,constraint);
        
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        if(!this.vehiclesRented.isEmpty()) {
	        this.vehicleImage = new ImageIcon("res\\car_img\\" + this.vehiclesRented.get(0).getFileName());
			Image image = this.vehicleImage.getImage();
	        Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
	        ImageIcon newVehicIcon = new ImageIcon(newImg);
	        this.vehicleReturnLabel = new JLabel(newVehicIcon);
	        this.add(this.vehicleReturnLabel,constraint);
        }
		
		
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
	
	class ItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          IVehicle v = ((VehicleComboItem) event.getItem()).getVehicle();
	          paintImage(v.getFileName());
	       }
	    }       
	}
	
	public void paintImage(String file_name) {
        ImageIcon vehicleImg = new ImageIcon("res\\car_img\\" + file_name);
        Image image = vehicleImg.getImage();
        Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
        this.vehicleImage = new ImageIcon(newImg);
        this.vehicleReturnLabel.setIcon(vehicleImage);
	}

	class ReturnActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			IRent rent = null;
			if(returnComboBox.getSelectedItem() != null) {
				//String modelName = returnComboBox.getSelectedItem().toString();
				//System.out.println(modelName);
				for(IRent r : rents) {
		        	  if(r.getVehicle().equals(((VehicleComboItem)returnComboBox.getSelectedItem()).getVehicle())) {
		        		  rent = r;
		        	  }
				}
				
				List<String> notes = new ArrayList<>();
				for (String line : notesArea.getText().split("\\n")) 
					notes.add(line);
				
				try {
					//System.out.println(rent.getVehicle().getModel() + "Returned");
					clientProxy.returnVehicle(rent, notes);
					//clientProxy.detach(rent);
					//clientProxy = new ClientProxy();
					//rents = clientProxy.getRenterRentals(renter);
					
					//loadVehicles();
					
					//System.out.println("Ciao" + ((VehicleComboItem)returnComboBox.getSelectedItem()).getVehicle().getModel());
					
					returnComboBox.removeItem(((VehicleComboItem)returnComboBox.getSelectedItem()));
			
					
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
}