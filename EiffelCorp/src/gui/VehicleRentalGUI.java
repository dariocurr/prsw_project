package gui;

import javax.swing.*;
import com.formdev.flatlaf.*;

import common.IRenterObserver;

//import common.Vehicle;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationGroupID;

public class VehicleRentalGUI {
	
	private JPanel selectRentPanel;
	private JPanel selectReturnPanel;
	private JTabbedPane tabPane;
	private JFrame frame;

	public VehicleRentalGUI(IRenterObserver renter) throws MalformedURLException, RemoteException, NotBoundException {

		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    if(!FlatDarkLaf.install()) {
		    	System.err.println( "Failed to initialize dark theme" );
		    	
		    }
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		this.frame = new JFrame("Vehicles Rental");
		
		
		this.tabPane = new JTabbedPane();
		this.selectRentPanel = new RentPanel(renter);
        this.selectReturnPanel = new ReturnPanel(renter);
		
        this.tabPane.addTab("RENT A VEHICLE", this.selectRentPanel);
        this.tabPane.addTab("RETURN A VEHICLE", this.selectReturnPanel);
		this.tabPane.setSelectedIndex(0);
		this.frame.add(this.tabPane);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setUndecorated(true);
		this.frame.setPreferredSize(new Dimension(720, 500));
		this.frame.setMinimumSize(new Dimension(580, 400));
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}


	/*public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		new VehicleRentalGUI();
	}*/
}
