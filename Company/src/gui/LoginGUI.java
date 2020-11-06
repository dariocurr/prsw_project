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

public class LoginGUI {
	
	private JFrame frame;
	
	private JPanel mainPanel;
	private GridBagConstraints constraint;
	
	private JLabel iconLabel;
	
	private JTextField usernameField;
	private JLabel usernameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JButton loginButton;
	private JButton signinButton;
	
	public LoginGUI() {
		
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
<<<<<<< HEAD
<<<<<<< HEAD
		frame.setSize(screenSize);
		frame.setPreferredSize(screenSize);
		frame.setResizable(true);
		frame.setMinimumSize(new Dimension(1100,600));
=======
		this.frame.setSize(screenSize);
=======
		this.frame.setSize(screenSize);*/
		
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
		this.frame.setResizable(false);
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
<<<<<<< HEAD
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setMinimumSize(this.frame.getSize());
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        
	}
	

	public void startGUI() {
		this.mainPanel = new JPanel();
<<<<<<< HEAD
		this.mainPanel.setSize(new Dimension(800, 400));
		this.mainPanel.setPreferredSize(new Dimension(800, 400));
		//this.mainPanel.setLayout(null);
		this.mainPanel.setLayout(new GridBagLayout());
		this.constraint = new GridBagConstraints();
=======
		
		/*this.mainPanel.setSize(new Dimension(800, 400));
		this.mainPanel.setPreferredSize(new Dimension(800, 400));*/
		this.mainPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
		this.mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = 2;
		
		constraint.gridx = 0;
        constraint.gridy = 0;
        ImageIcon icon = new ImageIcon("res/logo.png");
		Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(newImg);
        this.iconLabel = new JLabel(newLogoIcon);
        this.mainPanel.add(this.iconLabel, constraint);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
		
		
		
        constraint.anchor = GridBagConstraints.WEST;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        
        constraint.gridy = 1;
		this.usernameLabel = new JLabel("Username:");
<<<<<<< HEAD
		
		this.passwordField = new JPasswordField(12);
		this.usernameField = new JTextField(12);
=======
		this.mainPanel.add(this.usernameLabel, constraint);
		constraint.gridy = 2;
		constraint.ipadx = 10;
		constraint.ipady = 10;
		this.usernameField = new JTextField();
		this.mainPanel.add(this.usernameField, constraint);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
		
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
		
		constraint.insets = new Insets(100, 100, 0, 150);
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
		//this.iconLabel.setBounds(80, 120, 200, 110);
		this.mainPanel.add(this.iconLabel, this.constraint);
		
	}
	
	public void addUsernameField() {
		this.usernameLabel.setFont(this.usernameLabel.getFont().deriveFont(18.0f));
<<<<<<< HEAD
<<<<<<< HEAD
        //this.usernameLabel.setBounds(300, 109, 250, 44);
=======
        this.usernameLabel.setBounds(330, 109, 200, 30);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
        this.usernameLabel.setBounds(300, 109, 250, 44);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
        
		this.usernameField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.usernameField.setFont(this.usernameField.getFont().deriveFont(18.0f));
<<<<<<< HEAD
<<<<<<< HEAD
		//this.usernameField.setBounds(430, 109, 250, 44);
=======
		this.usernameField.setBounds(430, 109, 200, 30);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
		this.usernameField.setBounds(430, 109, 250, 44);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
		this.usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
		
		this.constraint = new GridBagConstraints();
		this.constraint.insets = new Insets(0, 0, 0, 20);
		this.constraint.gridx = 1;
		this.constraint.gridy = 0;
		this.mainPanel.add(this.usernameLabel, constraint);
		
		this.constraint = new GridBagConstraints();
		this.constraint.fill = GridBagConstraints.HORIZONTAL;
		this.constraint.gridx = 2;
		//this.constraint.gridwidth = 1;
		//this.constraint.weightx = 1.0;
		//this.constraint.weighty = 1.0;
		this.constraint.gridy = 0;
		//this.constraint.gridwidth = 5;
		this.mainPanel.add(this.usernameField, constraint);
	}


	
	public void addPasswordField() {
		this.passwordLabel.setFont(this.passwordLabel.getFont().deriveFont(18.0f));
<<<<<<< HEAD
<<<<<<< HEAD
        //this.passwordLabel.setBounds(302, 168, 250, 44);
=======
        this.passwordLabel.setBounds(330, 169, 200, 30);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
        this.passwordLabel.setBounds(302, 168, 250, 44);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
      
        this.passwordField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        //this.passwordField.setMargin(new Insets(2, 10, 2, 2));
        //this.passwordField.setHorizontalAlignment(SwingConstants.LEFT);
        
        this.passwordField.setFont(this.passwordField.getFont().deriveFont(18.0f));
        this.passwordField.setBorder(BorderFactory.createLineBorder(new Color(103, 112, 120)));

<<<<<<< HEAD
<<<<<<< HEAD
        //this.passwordField.setBounds(430, 168, 250, 44);
=======
        this.passwordField.setBounds(430, 168, 200, 30);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
        this.passwordField.setBounds(430, 168, 250, 44);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
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

        this.constraint = new GridBagConstraints();
        this.constraint.insets = new Insets(0, 0, 0, 20);
        this.constraint.gridx = 1;
        this.constraint.gridy = 1;
        this.mainPanel.add(this.passwordLabel, constraint);
        
        this.constraint = new GridBagConstraints();
        this.constraint.gridx = 2;
        this.constraint.gridy = 1;
        //this.constraint.fill = GridBagConstraints.HORIZONTAL;
        this.mainPanel.add(this.passwordField, constraint);
		
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

      
<<<<<<< HEAD
<<<<<<< HEAD
        //this.loginButton.setBounds(470, 255, 120, 44);
=======
        this.loginButton.setBounds(470, 229, 80, 40);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
=======
        this.loginButton.setBounds(470, 255, 120, 44);
>>>>>>> branch 'main' of https://github.com/dariocurr/prsw.git
        this.loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        this.constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.ipady = 15;
        constraint.ipadx = 20;
        this.constraint.insets = new Insets(60, 0, 0, 0);
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        constraint.anchor = GridBagConstraints.NORTH;
        constraint.fill = GridBagConstraints.NONE;
        this.mainPanel.add(this.loginButton, constraint);
		
	}*/
	
	public static void main(String[] args) {
        new LoginGUI();
    }
	
}
