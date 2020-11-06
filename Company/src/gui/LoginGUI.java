package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

import com.formdev.flatlaf.*;

public class LoginGUI {
	
	private JFrame frame;
	
	private JPanel mainPanel;
	
	private JLabel iconLabel;
	private ImageIcon iconImage;
	
	private JTextField usernameField;
	private JLabel usernameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JButton loginButton;
	
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
		
		this.addLogo();
		this.addUsernameField();
		this.addPasswordField();
		this.addLoginButton();
		
		this.frame.add(mainPanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.setSize(screenSize.getWidth()*0.65, screenSize.getHeight()*0.65);
		this.frame.setSize(screenSize);
		this.frame.setResizable(false);
		this.frame.setUndecorated(true);
		this.frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	

	public void startGUI() {
		this.mainPanel = new JPanel();
		this.mainPanel.setSize(new Dimension(800, 400));
		this.mainPanel.setPreferredSize(new Dimension(800, 400));
		this.mainPanel.setLayout(null);
		
		
		this.iconLabel = new JLabel();
		this.passwordLabel = new JLabel("Username:");
		this.usernameLabel = new JLabel("Password:");
		
		this.passwordField = new JPasswordField();
		this.usernameField = new JTextField();
		
		this.loginButton = new JButton("LOGIN");
	}
	
	
	public void addLogo() {
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
		
	}
	
	public static void main(String[] args) {
        new LoginGUI();
    }
	
}
