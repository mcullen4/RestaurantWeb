/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service;

import java.util.List;
import model.db.accessor.DBAccessor;

/**
 *
 * @author Michele
 */
public class OrderDAO extends IOrderDAO {
    DBAccessor db;
    private final String TABLE_NAME = "menu";
    
    public OrderDAO(DBAccessor db){
    this.db=db;
    } 
    
    public void save(Order order) throws Exception{}
    
    public void delete (Order order) throws Exception{}
    
    public Order findOrderByID(String ID) throws Exception{
    return null;
    }
    
    public List<Order> getAllOrders(){
    return null;
    }

    public DBAccessor getDb() {
        return db;
    }

    public void setDb(DBAccessor db) {
        this.db = db;
    }
    
    
}
