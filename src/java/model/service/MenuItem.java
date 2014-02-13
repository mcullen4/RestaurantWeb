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
public class MenuItem {
    private int menuID;
    private String menuDescription;
    private double price;
    
    public MenuItem(String menuDescription){
    this.menuDescription=menuDescription;
    }
    
    public MenuItem(int menuID, String menuDescription, double price){
    this.menuID=menuID;
    this.menuDescription=menuDescription;
    this.price=price;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.menuID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuItem other = (MenuItem) obj;
        if (this.menuID != other.menuID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuItems{" + "menuID=" + menuID + ", menuDescription=" + 
                menuDescription + ", price=" + price + '}';
    }
    
    
}
