package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.rpc.ServiceException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import client.Basket;
import client.IfsCarsServiceClient;
import common.IVehicle;
import service.CarSeller;
import service.CarSellerServiceLocator;

public class ClientGUI {
	private JFrame frame;
	private JPanel mainPanel;
	private IfsCarsServiceClient client;
	private List<IVehicle> vehiclesList;
	
	private JComboBox<VehicleComboItem> buyComboBox;
	private JLabel vehicleLabel;
	private ImageIcon VehicleIcon;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScrollPane;
	
	private JScrollPane basketScrollPane;
	private CartPanel cartPanel;
	
	private JLabel totalPrice;
	private JButton addButton;
	private JButton buyButton;
	
	private CarSeller seller;
	
	private GridBagConstraints constraint;
	
	public ClientGUI() {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    if(!FlatDarkLaf.install()) {
		    	System.err.println( "Failed to initialize dark theme" );
		    	
		    }
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		this.frame = new JFrame();
		
		try {
			CarSeller seller = new CarSellerServiceLocator().getCarSeller();
		} catch (ServiceException e) {
			JOptionPane.showMessageDialog(this.frame,
				    "Error loading the remote service.\nPlease contact ad administrator.",
				    "No service",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			this.client = new IfsCarsServiceClient();
			this.vehiclesList = client.getVehicles();
		} catch (ServiceException | RemoteException  e) {
			JOptionPane.showMessageDialog(this.frame,
				    "Error while getting data from remote host.\nPlease contact ad administrator.",
				    "No vehicles",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		
		try {
		    frame.setIconImage(ImageIO.read(new File("res/logo.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		this.initComponents();
		this.startGUI();
		
		this.frame.add(mainPanel);
		
		this.frame.setResizable(false);
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setMinimumSize(this.frame.getSize());
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		
		
		this.descriptionArea = new JTextArea(10, 30);
		this.descriptionArea.setOpaque(false);
		this.descriptionArea.setText("");
		this.descriptionArea.setEditable(false);
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
        

        /*List<IVehicle> vehhiclesList = new ArrayList<IVehicle>();
		try {
			vehhiclesList = client.getVehicles();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this.frame,
				    "Error while loading vehicles from remote host. Please contact ad administrator.",
				    "No vehicles",
				    JOptionPane.ERROR_MESSAGE);
		}*/
        
        
		this.vehicleLabel = new JLabel();
		this.buyComboBox = new JComboBox<VehicleComboItem>();
		this.buyComboBox.setPrototypeDisplayValue(new VehicleComboItem(new Vehicle("XXXXXXXXXXXXXXXXXXX", "", 0, 0, "", "", 0, 0, "")));
		this.buyComboBox.addItemListener(event -> {
			 if (event.getStateChange() == ItemEvent.SELECTED) {
		          IVehicle v = ((VehicleComboItem) event.getItem()).getVehicle();
		          //IVehicle veh = this.vehiclesList.get(this.vehiclesList.indexOf(v));
		          paintImage(v.getFileName());
		          paintDescription(v);
		          
		       }
		});
		
		//TODO get list from service

		
		
		
		/*IVehicle testIVehicle = new Vehicle("Fiat 500", "2004", 4, 2, "manual", "little", 80, 12000, "fiat_500.png");
		IVehicle testIVehicle2 = new Vehicle("Fiat 501", "2006", 4, 2, "manual", "little", 80, 12000, "fiat_500.png");
		IVehicle testIVehicle3 = new Vehicle("Fiesta", "2007", 4, 2, "manual", "little", 80, 12000.07, "ford_fiesta.png");
		vehhiclesList.add(testIVehicle);
		vehhiclesList.add(testIVehicle2);
		vehhiclesList.add(testIVehicle3);*/
		
		for(IVehicle vehicle : this.vehiclesList) {
			this.buyComboBox.addItem(new VehicleComboItem(vehicle));
			
		}
		
		//this.buyComboBox.addItem();
		//this.buyComboBox.setPrototypeDisplayValue("text long like this or more..... ");
		//this.buyComboBox.addItemListener(new ItemChangeListener());
		
        
        this.buyButton = new JButton("BUY");
        this.totalPrice = new JLabel("Total price: 0.0");
        
        
        this.addButton = new JButton("ADD TO CART");
        this.addButton.addActionListener(ev -> {
        	if(this.buyComboBox.getItemCount() > 0) {
        		IVehicle vehicle = ((VehicleComboItem) this.buyComboBox.getSelectedItem()).getVehicle();
        		this.buyComboBox.removeItemAt(this.buyComboBox.getSelectedIndex());
        		if(this.buyComboBox.getItemCount() > 0) {
        			IVehicle nextVehicle = ((VehicleComboItem) this.buyComboBox.getSelectedItem()).getVehicle();
        			paintImage(nextVehicle.getFileName());
        		} else {
            		this.vehicleLabel.setIcon(null);

            		this.vehicleLabel.setMinimumSize(new Dimension(400,400));
            		this.vehicleLabel.setOpaque(true);
            		this.vehicleLabel.setBackground(Color.yellow);
        		}
            	this.cartPanel.additem(vehicle);  
        	} else {
        		JOptionPane.showMessageDialog(this.frame,
    				    "Unfortunately no vehicles are avalaible to be sold, please wait some returns!",
    				    "No vehicles",
    				    JOptionPane.INFORMATION_MESSAGE);
        	}
        	      	
        });
        
        this.cartPanel = new CartPanel(this.buyButton, this.totalPrice, this.buyComboBox);
        
        this.buyButton.setEnabled(false);
        this.buyButton.addActionListener(ev -> {
        	 BuyDialog dialog = new BuyDialog(frame, this.cartPanel.getBasket(), this.client);
        	 dialog.addWindowListener(new WindowAdapter()
        	    {
        	      public void windowClosed(WindowEvent e)
        	      {
        	        cartPanel.reload();
        	      }
        	    });
        	
        });
	}
	
	private void startGUI() {
		this.mainPanel = new JPanel();
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		this.constraint = new GridBagConstraints();
		
		this.constraint.insets = new Insets(16, 8, 0, 8);
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
        this.mainPanel.add(this.buyComboBox,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        
        if(vehiclesList.size() > 0) {
        	ImageIcon vehicleImage = new ImageIcon("res" + File.separator + "car_img" + File.separator + vehiclesList.get(0).getFileName());
    		Image image = vehicleImage.getImage();
            Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
            this.VehicleIcon = new ImageIcon(newImg);
        } else {
        	this.VehicleIcon = new ImageIcon();
        }
        
        this.vehicleLabel = new JLabel(VehicleIcon);
        this.mainPanel.add(this.vehicleLabel,constraint);
        
        this.constraint.insets = new Insets(10, 8, 0, 8);
        this.constraint.gridx = 1;
        this.constraint.gridy = 0;
        this.constraint.gridheight = 2;
        this.constraint.fill = GridBagConstraints.VERTICAL;
        this.mainPanel.add(this.descriptionScrollPane,constraint);
        
        this.constraint.insets = new Insets(10,8,0,8);
        this.constraint.gridx = 2;
        this.constraint.gridy = 0;
        this.constraint.gridheight = 2;
        this.constraint.fill = GridBagConstraints.VERTICAL;
        this.mainPanel.add(this.cartPanel,constraint);
        
        this.constraint.insets = new Insets(0,0,0,8);
        this.constraint.gridx = 2;
        this.constraint.gridy = 3;
        this.constraint.gridheight = 1;
        this.constraint.anchor = GridBagConstraints.LINE_END;
        this.mainPanel.add(this.totalPrice,constraint);
        
        this.constraint.insets = new Insets(20,0,0,0);
        this.constraint.gridx = 2;
        this.constraint.gridy = 4;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 10;
        this.mainPanel.add(this.buyButton,constraint);
        
        
        this.constraint.anchor = GridBagConstraints.CENTER;
        this.constraint.insets = new Insets(20,0,0,0);
        this.constraint.gridx = 0;
        this.constraint.gridy = 4;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 10;
        this.mainPanel.add(this.addButton,constraint);
        

	}
	
	public void paintImage(String file_name) {
		ImageIcon vehicleImage = new ImageIcon("res" + File.separator + "car_img" + File.separator + file_name);
        Image image = vehicleImage.getImage();
        Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
        this.VehicleIcon = new ImageIcon(newImg);
        this.vehicleLabel.setIcon(VehicleIcon);
	}
	
	public void paintDescription(IVehicle v) {
		String notes = "";
		for(String line : v.getNotes()) {
			notes += line + "\n";
		}
			
		this.descriptionArea.setText("Model: " + v.getModel() + "\n" + "Year: " + v.getYear() + "\n" + "Seats: " + v.getSeats() + "\n" + "Doors: " + v.getDoors() + "\n" + "Transmission: " + v.getTrasmission() + "\n" + "Size: " + v.getSize() + "\n" + "Price per day: " + v.getPricePerDay() + "€" + "\n" + "Notes: " + "\n"+  notes);
		
	}
	
	public static void main(String[] args) {
        new ClientGUI();
    }
	
}
