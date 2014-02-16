/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service;

/**
 *
 * @author Michele
 */
public class TipCalculator extends ITipCalculator {
   private final static double TIP_PERCENT = .10;
   
   public double calculateSuggestedTip(double totalBill){
   double suggestedTip = totalBill * TIP_PERCENT;
   return suggestedTip;
   }
    
    
}
