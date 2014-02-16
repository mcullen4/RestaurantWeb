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
public class TaxCalculator extends ITaxCalculator {
    private final static double TAX_PERCENT = .055;
    
    public double calculateTax(double totalBill){
        double taxAmount = totalBill*TAX_PERCENT;
            
        return taxAmount;
    }
}
