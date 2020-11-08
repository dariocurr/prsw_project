package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import client.Basket;

public class CartPanel extends JPanel {
   private static final int PREF_W = 200;
   private static final int PREF_H = PREF_W;
   private Basket basket;
   private List<CartItem> basketList;

   public CartPanel() {
      JScrollPane scrollPane = new JScrollPane();
      //scrollPane.setViewportBorder(null);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBorder(null);
      JPanel container = new JPanel(new GridLayout(0, 1));

      this.loadBasket();
      for (CartItem item : basketList) {
      	container.add(item);
      }
      scrollPane.setViewportView(container);

      setBorder(BorderFactory.createTitledBorder("Shopping cart"));
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(scrollPane);
   }
   
   private void loadBasket() {
	   basketList = new ArrayList<CartItem>();
	   for(int i = 0; i< 15; i++)
		   basketList.add(new CartItem());

   }

   @Override
   public Dimension getPreferredSize() {
      if (isPreferredSizeSet()) {
         return super.getPreferredSize();
      }
      return new Dimension(PREF_W, PREF_H);
   }
}