package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class RegisterGUI {
	
	private JFrame frame;
	
	private JPanel mainPanel;
	
	private JLabel iconLabel;
	
	private JTextField usernameField;
	private JLabel nameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JButton loginButton;
	private JButton signinButton;
	
	public RegisterGUI() {
		
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		    if(!FlatDarkLaf.install()) {
		    	System.err.println( "Failed to initialize dark theme" );
		    	
		    }
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		this.frame = new JFrame();
		
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
	

	public void startGUI() {
		this.mainPanel = new JPanel();
		
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = 2;
		
		constraint.gridx = 0;
        constraint.gridy = 0;
        ImageIcon icon = new ImageIcon("D:\\code\\rest_eclipse\\progetto\\prsw\\CarRental\\res\\car_img\\fiat_500.png");
		Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(newImg);
        this.iconLabel = new JLabel(newLogoIcon);
        this.mainPanel.add(this.iconLabel, constraint);
		
        constraint.anchor = GridBagConstraints.WEST;
        
		constraint.insets = new Insets(0,8,0,8);
		
        constraint.gridwidth = 1;
        constraint.gridy = 1;
		this.nameLabel = new JLabel("Name:");
		this.mainPanel.add(this.nameLabel, constraint);
		constraint.gridy = 2;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.usernameField = new JTextField(15);
		this.mainPanel.add(this.usernameField, constraint);
		
		constraint.gridy = 1;
		constraint.gridx = 1;
		this.nameLabel = new JLabel("Surname:");
		this.mainPanel.add(this.nameLabel, constraint);
		constraint.gridy = 2;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.usernameField = new JTextField(15);
		this.mainPanel.add(this.usernameField, constraint);
		
		constraint.gridy = 3;
		constraint.gridx = 0;
		this.nameLabel = new JLabel("Email:");
		this.mainPanel.add(this.nameLabel, constraint);
		constraint.gridwidth = 2;
		constraint.gridy = 4;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		this.usernameField = new JTextField(15);
		this.mainPanel.add(this.usernameField, constraint);
		
		constraint.gridy = 5;
		constraint.gridx = 0;
		this.passwordLabel = new JLabel("Password:");
		this.mainPanel.add(this.passwordLabel, constraint);
		constraint.gridwidth = 1;
		constraint.gridy = 6;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.passwordField = new JPasswordField();
		this.mainPanel.add(this.passwordField, constraint);
		
		constraint.gridy = 5;
		constraint.gridx = 1;
		this.passwordLabel = new JLabel("Repeat password:");
		this.mainPanel.add(this.passwordLabel, constraint);
		constraint.gridy = 6;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.passwordField = new JPasswordField();
		this.mainPanel.add(this.passwordField, constraint);
		
		constraint.gridwidth = 1;
		constraint.insets = new Insets(24,8,8,8);
		
		constraint.gridy = 7;
		constraint.gridx = 0;
		this.loginButton = new JButton("LOGIN");
		this.mainPanel.add(this.loginButton, constraint);
		
		constraint.gridy = 7;
		constraint.gridx = 1;
		this.signinButton = new JButton("SIGN IN");
		this.mainPanel.add(this.signinButton, constraint);
	}
	
	
	public static void main(String[] args) {
        new RegisterGUI();
    }
	
}
