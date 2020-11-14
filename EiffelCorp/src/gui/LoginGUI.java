package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.*;

import common.IRenterObserver;
import company.ClientProxy;

/** GUI class for employee's log in  */
public class LoginGUI {
	
	private JFrame frame;
	
	private JPanel mainPanel;
	
	private JLabel iconLabel;
	
	private JTextField usernameField;
	private JLabel usernameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JButton loginButton;
	private JButton signinButton;
	
	private ClientProxy clientProxy;
	
	/** Constructor which is used to initialize the frame and the proxy */
	public LoginGUI() throws MalformedURLException, RemoteException, NotBoundException {
		
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    if(!FlatDarkLaf.install()) {
		    	System.err.println( "Failed to initialize dark theme" );
		    	
		    }
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}

		this.clientProxy = new ClientProxy();
		this.frame = new JFrame();
		
		this.startGUI();
		
		this.frame.add(mainPanel);
		/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize(screenSize.getWidth()*0.65, screenSize.getHeight()*0.65);
		this.frame.setSize(screenSize);*/
		
		this.frame.setResizable(false);
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setMinimumSize(this.frame.getSize());
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        
	}
	

	/** Initialize the main panel with all the gui components. 
	 * @return Nothing*/
	public void startGUI() {
		this.mainPanel = new JPanel();
		
		/*this.mainPanel.setSize(new Dimension(800, 400));
		this.mainPanel.setPreferredSize(new Dimension(800, 400));*/
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = 2;
		
		constraint.gridx = 0;
        constraint.gridy = 0;
        ImageIcon icon = new ImageIcon("res" + File.separator + "logo.png");
		Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(newImg);
        this.iconLabel = new JLabel(newLogoIcon);
        this.mainPanel.add(this.iconLabel, constraint);
		
		
		
        constraint.anchor = GridBagConstraints.WEST;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        
        constraint.gridy = 1;
		this.usernameLabel = new JLabel("Username:");
		this.mainPanel.add(this.usernameLabel, constraint);
		constraint.gridy = 2;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.usernameField = new JTextField();
		this.mainPanel.add(this.usernameField, constraint);
		
		constraint.gridy = 3;
		this.passwordLabel = new JLabel("Password:");
		this.mainPanel.add(this.passwordLabel, constraint);
		constraint.gridy = 4;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.passwordField = new JPasswordField();
		this.mainPanel.add(this.passwordField, constraint);
		
		constraint.gridwidth = 1;
		constraint.insets = new Insets(24,8,8,8);
		constraint.weightx = 0.5;
		//constraint.fill = GridBagConstraints.NONE;
		
		//constraint.anchor = GridBagConstraints.LAST_LINE_END;
		constraint.gridy = 5;
		//constraint.anchor = GridBagConstraints.PAGE_END;
		this.loginButton = new JButton("LOGIN");
		this.loginButton.addActionListener(new LoginActionListener());
		this.mainPanel.add(this.loginButton, constraint);
		
		//constraint.anchor = GridBagConstraints.LAST_LINE_START;
		//constraint.gridy = 5;
		//constraint.gridx = 1;
		//constraint.fill = GridBagConstraints.NONE;
		//constraint.anchor = GridBagConstraints.PAGE_END;
		//this.signinButton = new JButton("SIGN IN");
		//this.mainPanel.add(this.signinButton, constraint);
	}
	
	/** Main method, used to build the login gui 
	 * @param args Unused 
	 * @return Nothing */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        new LoginGUI();
    }
	
	/** Listener for the clicks in the login button. */
	class LoginActionListener implements ActionListener{
		/** When someone click on the login button, it is checked if username and password are correct.
		 * @param event The event of the listener.
		 * @return Nothing */
		@Override
		public void actionPerformed(ActionEvent event) {
			Map<String, String> credentialsMap = LoginGUI.this.clientProxy.getCredentials();
			List<IRenterObserver> renters = LoginGUI.this.clientProxy.getRenters();
			IRenterObserver actualRenter = null;
			
			
			if(credentialsMap.containsKey(LoginGUI.this.usernameField.getText())) {
				if(new String(LoginGUI.this.passwordField.getPassword()).equals(credentialsMap.get(LoginGUI.this.usernameField.getText()))) {
					for(IRenterObserver renter : renters) {
						if(renter.getEmail().equals(LoginGUI.this.usernameField.getText()) && renter.getPassword().equals(new String(LoginGUI.this.passwordField.getPassword())))
							actualRenter = renter;
					}
					try {
						LoginGUI.this.frame.dispose();
						new VehicleRentalGUI(actualRenter);
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			
			JOptionPane.showMessageDialog(null, "Email or Password Wrong");
			
		}
		
	}
}
