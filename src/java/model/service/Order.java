/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service;

import java.util.List;

/**
 *
 * @author Michele
 */
public class Order {
   private int orderNumber;
   private List<MenuItem> orderedItems;
   private double taxAmount;
   private double suggestedTipAmount;
   private double actualTipAmount;
   private Boolean delivery;
}
