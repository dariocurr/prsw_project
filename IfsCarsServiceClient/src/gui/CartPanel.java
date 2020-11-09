package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

   public CartPanel(JButton button, JLabel totalPrice) {
	   
       JScrollPane scrollPane = new JScrollPane();
       //scrollPane.setViewportBorder(null);
       scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       scrollPane.setBorder(null);
       container = new JPanel(new GridLayout(0, 1));

       this.basket = new Basket();
       this.buyButton = button;
       this.totalPrice = totalPrice;
       
       /*for (CartItem item : basketList) {
      	 container.add(item);
       }*/
       scrollPane.setViewportView(container);

       setBorder(BorderFactory.createTitledBorder("Shopping cart"));
       setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
       add(scrollPane);
   }
   
   private void loadBasket() {
	   container.removeAll();
	   for (IVehicle v : basket.getVehiclesInBasket()) {
		   CartItem cItem = new CartItem(v);
		   cItem.getButton().addActionListener(ev -> {
			   container.remove(cItem);
			   basket.removeVehicleFromBasket(v);
			   if(basket.getVehiclesInBasket().size() <= 0) {
				   buyButton.setEnabled(false);
			   }
			   totalPrice.setText("Total price: "+basket.getTotalPrice());
			   container.revalidate();
			   container.repaint();
		   });
		   container.add(cItem);
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