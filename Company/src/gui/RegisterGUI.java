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
	private JLabel usernameLabel;
	
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
		/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize(screenSize.getWidth()*0.65, screenSize.getHeight()*0.65);
		this.frame.setSize(screenSize);*/
		
		//this.frame.setResizable(false);
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
		
		/*this.mainPanel.setSize(new Dimension(800, 400));
		this.mainPanel.setPreferredSize(new Dimension(800, 400));*/
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = 2;
		
		constraint.gridx = 0;
        constraint.gridy = 0;
        //this.vehicleImage = new ImageIcon("C:\\Users\\emanu\\Documents\\Coding\\Java\\Programmation Repartie\\prsw\\CarRental\\res\\car_img\\Fiat 500.png");
        ImageIcon icon = new ImageIcon("C:\\Users\\emanu\\Documents\\Coding\\Java\\Programmation Repartie\\prsw\\CarRental\\res\\car_img\\fiat_500.png");
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
		this.mainPanel.add(this.loginButton, constraint);
		
		//constraint.anchor = GridBagConstraints.LAST_LINE_START;
		constraint.gridy = 5;
		constraint.gridx = 1;
		//constraint.fill = GridBagConstraints.NONE;
		//constraint.anchor = GridBagConstraints.PAGE_END;
		this.signinButton = new JButton("SIGN IN");
		this.mainPanel.add(this.signinButton, constraint);
	}
	
	
	/*public void addLogo() {
		this.iconLabel.setFocusable(false);
		
		this.iconImage = new ImageIcon("C:/Users/domy-/OneDrive/Desktop/icon.png");
		Image image = this.iconImage.getImage(); // transform it
		Image newImg = image.getScaledInstance(110, 110,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);
		this.iconLabel.setIcon(newIcon);
		
		this.iconLabel.setBounds(80, 120, 200, 110);
		this.mainPanel.add(this.iconLabel);
		
	}
	
	public void addUsernameField() {
		this.usernameLabel.setFont(this.usernameLabel.getFont().deriveFont(18.0f));
        this.usernameLabel.setBounds(300, 109, 250, 44);
        
		this.usernameField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.usernameField.setFont(this.usernameField.getFont().deriveFont(18.0f));
		this.usernameField.setBounds(430, 109, 250, 44);
		this.usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
		
		this.mainPanel.add(this.usernameLabel);
		this.mainPanel.add(this.usernameField);
	}


	
	public void addPasswordField() {
		this.passwordLabel.setFont(this.passwordLabel.getFont().deriveFont(18.0f));
        this.passwordLabel.setBounds(302, 168, 250, 44);
      
        this.passwordField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        this.passwordField.setMargin(new Insets(2, 10, 2, 2));
        this.passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        
        this.passwordField.setFont(this.passwordField.getFont().deriveFont(18.0f));
        this.passwordField.setBorder(BorderFactory.createLineBorder(new Color(103, 112, 120)));

        this.passwordField.setBounds(430, 168, 250, 44);
        this.passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });

        this.passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER);

            }
        });

        this.mainPanel.add(this.passwordLabel);
        this.mainPanel.add(this.passwordField);
		
	}

	public void addLoginButton() {
		this.loginButton.setFont(this.loginButton.getFont().deriveFont(16.0f));
        this.loginButton.setFocusPainted(false);
        
        this.loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });

      
        this.loginButton.setBounds(470, 255, 120, 44);
        this.loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.mainPanel.add(this.loginButton);
		
	}*/
	
	public static void main(String[] args) {
        new LoginGUI();
    }
	
}
