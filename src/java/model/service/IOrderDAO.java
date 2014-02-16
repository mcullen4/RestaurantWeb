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
public abstract class IOrderDAO {

    public IOrderDAO() {
    }

    public abstract void save(Order order) throws Exception;

    public abstract void delete(Order order) throws Exception;

    public abstract Order findOrderByID(String ID) throws Exception;

    public abstract List<Order> getAllOrders();

    public abstract DBAccessor getDb();

    public abstract void setDb(DBAccessor db);
    
}
