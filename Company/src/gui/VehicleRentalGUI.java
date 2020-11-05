package gui;

import javax.swing.*;
import com.formdev.flatlaf.*;

//import common.Vehicle;

import java.awt.*;

public class VehicleRentalGUI {
	private JPanel rentPanel;
	private GridBagLayout rentLayout;
	private GridBagConstraints c;
	
	private JPanel returnPanel; 
	private GridBagLayout returnLayout;
	
	private JPanel selectRentPanel;
	//private BoxLayout selectRentLayout;
	private GridBagLayout selectRentLayout;
	
	private JPanel selectReturnPanel;
	//private BoxLayout selectReturnLayout;
	private GridBagLayout selectReturnLayout;
	
	private JPanel descriptionPanel;
	private JPanel notesPanel;
	
	private JPanel bodyRentPanel; 
	private GridBagLayout bodyRentLayout;
	
	private JPanel bodyReturnPanel;
	private GridBagLayout bodyReturnLayout;
	
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
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize(screenSize.getWidth()*0.55, screenSize.getHeight()*0.55);
		frame.setSize(screenSize);
		frame.setMinimumSize(new Dimension(680,335));
		frame.setResizable(true);
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(700, 350));
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		//frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}


	private void startGUI() {
		this.rentPanel = new JPanel();
		this.rentLayout = new GridBagLayout();
		this.c = new GridBagConstraints();
		this.rentPanel.setLayout(this.rentLayout);
		
		this.returnPanel = new JPanel();
		this.returnLayout = new GridBagLayout();
		this.returnPanel.setLayout(this.returnLayout);
		
		this.selectRentPanel = new JPanel();
		this.selectRentLayout = new GridBagLayout();
		this.selectRentPanel.setLayout(this.selectRentLayout);
		
		this.selectReturnPanel = new JPanel();
		this.selectReturnLayout = new GridBagLayout();
		this.selectReturnPanel.setLayout(this.selectReturnLayout);
		
		this.descriptionPanel = new JPanel();
		this.notesPanel = new JPanel();
		
		this.bodyRentPanel = new JPanel();
		this.bodyRentLayout = new GridBagLayout();
		this.bodyRentPanel.setLayout(bodyRentLayout);
		
		this.bodyReturnPanel = new JPanel();
		this.bodyReturnLayout = new GridBagLayout();
		this.bodyReturnPanel.setLayout(bodyReturnLayout);
		
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
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage();
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleRentLabel = new JLabel(newVehicIcon);
        
        
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.weighty = 1;
		this.selectRentPanel.add(this.rentComboBox,c);
		this.selectRentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		
		this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.anchor = GridBagConstraints.SOUTH;
		this.selectRentPanel.add(this.vehicleRentLabel, c);
		
		this.descriptionPanel.add(this.descriptionScrollPane);
		
		this.c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.bodyRentPanel.add(this.selectRentPanel,c);
		
		this.c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.c.gridx = 1;
		this.c.gridy = 0;
		this.selectRentPanel.add(Box.createRigidArea(new Dimension(100,0)));
		
		this.bodyRentPanel.add(this.descriptionPanel,c);
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.rentPanel.add(this.bodyRentPanel, c);
		
		this.buttonRentPanel.add(this.rentButton);
		this.buttonRentPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		this.c.gridx = 0;
		this.c.gridy = 1;
		this.c.anchor = GridBagConstraints.CENTER;
		this.rentPanel.add(this.buttonRentPanel, c);
		
		this.tabPane.addTab("RENT A VEHICLE", this.rentPanel);
		this.tabPane.setSelectedIndex(0);
		
		this.frame.add(this.tabPane);
	}
	
	private void setReturnTab() {
		this.vehicleImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/alfa-romeo-2020-giulia.png");
		Image image = this.vehicleImage.getImage();
        Image newImg = image.getScaledInstance(160, 100,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newVehicIcon = new ImageIcon(newImg);
        this.vehicleReturnLabel = new JLabel(newVehicIcon);
       
        
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.weighty = 1;
		this.selectReturnPanel.add(this.returnComboBox,c);
		this.selectReturnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		
		this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.anchor = GridBagConstraints.SOUTH;
		this.selectReturnPanel.add(this.vehicleReturnLabel, c);
		
		this.notesPanel.add(this.notesScrollPane);
		
		this.c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.bodyReturnPanel.add(this.selectReturnPanel,c);
		
		this.c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.c.gridx = 1;
		this.c.gridy = 0;
		this.selectReturnPanel.add(Box.createRigidArea(new Dimension(100, 30)));
		
		this.bodyReturnPanel.add(this.notesPanel,c);
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.returnPanel.add(this.bodyReturnPanel, c);
		
		this.buttonReturnPanel.add(this.returnButton);
		this.buttonReturnPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		this.c.gridx = 0;
		this.c.gridy = 1;
		this.c.anchor = GridBagConstraints.CENTER;
		this.returnPanel.add(this.buttonReturnPanel, c);
		
		this.tabPane.addTab("RETURN A VEHICLE", this.returnPanel);
	}
	

	public static void main(String[] args) {
		VehicleRentalGUI gui = new VehicleRentalGUI();
	}
	
	
}
