package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import client.Basket;
import common.IVehicle;

public class CartPanel extends JPanel {
   private static final int PREF_W = 200;
   private static final int PREF_H = PREF_W;
   
   private JPanel container;
   private Basket basket;
   private List<CartItem> basketList;
   private JButton buyButton;
   private JLabel totalPrice;

   private JComboBox<VehicleComboItem> combobox;

   public CartPanel(JButton button, JLabel totalPrice, JComboBox<VehicleComboItem> combobox) {
	   setBorder(BorderFactory.createTitledBorder("Shopping cart"));
       setLayout(new BorderLayout());
       
       container = new JPanel(new GridBagLayout());
       /*GridBagConstraints gbc = new GridBagConstraints();
       gbc.gridwidth = GridBagConstraints.REMAINDER;
       gbc.weightx = 1;
       gbc.weighty = 1;
       container.add(new JPanel(), gbc);*/
       
       JScrollPane scrollPane = new JScrollPane(container);
       //scrollPane.setViewportBorder(null);
       scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setBorder(null);
       
       add(scrollPane);

       this.basket = new Basket();
       this.buyButton = button;
       this.totalPrice = totalPrice;
       this.combobox = combobox;
   }
   
   private void loadBasket() {
	   container.removeAll();
	  
	   for (IVehicle v : basket.getVehiclesInBasket()) {
		   CartItem cItem = new CartItem(v);
		   cItem.getButton().addActionListener(ev -> {
			   container.remove(cItem);
			   basket.removeVehicleFromBasket(v);
			   combobox.addItem(new VehicleComboItem(v));
			   if(basket.getVehiclesInBasket().size() <= 0) {
				   buyButton.setEnabled(false);
			   }
			   totalPrice.setText("Total price: "+basket.getTotalPrice());
			   container.revalidate();
			   container.repaint();
		   });
		   GridBagConstraints gbc = new GridBagConstraints();
		   gbc.insets = new Insets(5, 0, 0, 0);
           gbc.gridwidth = GridBagConstraints.REMAINDER;
           gbc.weightx = 1;
           gbc.fill = GridBagConstraints.HORIZONTAL;
           container.add(cItem, gbc, 0);
		   
	   }
  
	   
	   totalPrice.setText("Total price: "+basket.getTotalPrice());
	   container.revalidate();
	   container.repaint();

   }
   
   public void additem(IVehicle v) {
	   this.basket.addVehicleToBasket(v);
	   if(basket.getVehiclesInBasket().size() > 0) {
		   buyButton.setEnabled(true);
	   }
	   this.loadBasket();
   }
   
   public void reload() {
	   this.loadBasket();
   }
   
   public int getBasketSize() {
	   return  basket.getVehiclesInBasket().size();
   }
   

   @Override
   public Dimension getPreferredSize() {
      if (isPreferredSizeSet()) {
         return super.getPreferredSize();
      }
      return new Dimension(PREF_W, PREF_H);
   }

	public Basket getBasket() {
		return this.basket;
	}
   
   
}