package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import client.Basket;

public class ClientGUI {
	private JFrame frame;
	private JPanel mainPanel;
	
	private JComboBox<String> buyComboBox;
	private JLabel vehicleLabel;
	private JTextArea descriptionArea;
	private JScrollPane descriptionScrollPane;
	
	private JScrollPane basketScrollPane;
	private CartPanel cartPanel;
	
	private JLabel totalPrice;
	private JButton addButton;
	private JButton buyButton;
	
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
		this.buyComboBox = new JComboBox<String>();
		//this.buyComboBox.setModel(new DefaultComboBoxModel<String>(modelVehiclesRentable.toArray(new String[0])));
		this.buyComboBox.addItem("macchina 1");
		this.buyComboBox.addItem("macchina 2");
		this.buyComboBox.setPrototypeDisplayValue("text long like this or more..... ");
		//this.buyComboBox.addItemListener(new ItemChangeListener());
		
		this.descriptionArea = new JTextArea(10, 30);
		this.descriptionArea.setOpaque(false);
		this.descriptionArea.setText("Model:\nKm:\nTrasmission:");
		this.descriptionArea.setEditable(false);
		this.descriptionScrollPane = new JScrollPane(this.descriptionArea);
		this.descriptionScrollPane.setBorder(BorderFactory.createTitledBorder("Description of the car"));
        this.descriptionScrollPane.setSize (300,600);
        
        cartPanel = new CartPanel();
        
        this.totalPrice = new JLabel("Total price: "+10000);
        this.addButton = new JButton("ADD TO CART");
        this.buyButton = new JButton("BUY");
        this.buyButton.addActionListener(ev -> {
        	BuyDialog dialog = new BuyDialog(frame);
        });
	}
	
	private void startGUI() {
		this.mainPanel = new JPanel();
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		constraint = new GridBagConstraints();
		
		this.constraint.insets = new Insets(16, 8, 0, 8);
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
        this.mainPanel.add(this.buyComboBox,constraint);
        
        this.constraint.gridx = 0;
        this.constraint.gridy = 1;
        
        //this.vehicleImage = new ImageIcon("res\\car_img\\" + this.vehiclesRentable.get(0).getFileName());
        ImageIcon vehicleImage = new ImageIcon("res\\car_img\\fiat_500.png");
		Image image = vehicleImage.getImage();
        Image newImg = image.getScaledInstance(190, 200,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleLabel = new JLabel(newVehicIcon);
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
        //this.constraint.fill = GridBagConstraints.HORIZONTAL;
        this.mainPanel.add(this.totalPrice,constraint);
        
        this.constraint.insets = new Insets(20,0,0,0);
        this.constraint.gridx = 2;
        this.constraint.gridy = 4;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 10;
        //this.constraint.gridheight = 0;
        //this.constraint.fill = GridBagConstraints.HORIZONTAL;
        this.mainPanel.add(this.buyButton,constraint);
        
        
        this.constraint.anchor = GridBagConstraints.CENTER;
        this.constraint.insets = new Insets(20,0,0,0);
        this.constraint.gridx = 0;
        this.constraint.gridy = 4;
        this.constraint.ipadx = 10;
        this.constraint.ipady = 10;
        //this.constraint.gridheight = 0;
        //this.constraint.fill = GridBagConstraints.HORIZONTAL;
        this.mainPanel.add(this.addButton,constraint);
        

	}
	
	public static void main(String[] args) {
        new ClientGUI();
    }
	

}
