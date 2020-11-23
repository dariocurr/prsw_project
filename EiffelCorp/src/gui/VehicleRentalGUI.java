package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.*;

import common.IRenterObserver;
import company.ClientProxy;

//import common.Vehicle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationGroupID;

/** Class for the rental gui, it joins the rent's panel and the return's panel */
public class VehicleRentalGUI {
	
	private JTabbedPane tabPane;
	private JFrame frame;
	private ClientProxy clientProxy;
	
	private RentPanel selectRentPanel;
	private ReturnPanel selectReturnPanel;

	/** Main constructor, it initializes the frame and the tapPane where we can switch between
	 * the two panels (@RentPanel and @ReturnPanel). 
	 * @param renter the renter who logged in. */
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
		
		try {
		    frame.setIconImage(ImageIO.read(new File("res/logo.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
		
		this.clientProxy = new ClientProxy();
		
		
		this.tabPane = new JTabbedPane();
		
        this.tabPane.addTab("RENT A VEHICLE", new RentPanel(renter));
        this.tabPane.addTab("RETURN A VEHICLE", new ReturnPanel(renter));
		this.tabPane.setSelectedIndex(0);
		this.tabPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		        int selectedIndex = tabbedPane.getSelectedIndex();
		        if(selectedIndex == 0) {
					try {
						tabPane.setComponentAt(selectedIndex, new RentPanel(renter));
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } else {
		        	try {
						tabPane.setComponentAt(selectedIndex, new ReturnPanel(renter));
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
				
			}
		});
		
		
		this.frame.add(this.tabPane);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setUndecorated(true);
		this.frame.setPreferredSize(new Dimension(780, 500));
		this.frame.setMinimumSize(new Dimension(580, 400));
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
}
