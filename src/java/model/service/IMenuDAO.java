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
public interface IMenuDAO {
    
    public abstract void save(MenuItem menuItem) throws Exception;
    
    public abstract void delete(MenuItem menuItem) throws Exception;
    
    public MenuItem findMenuItemByID(String ID) throws Exception;
    
    public abstract List<MenuItem> getAllMenuItems()throws Exception;
    
    public abstract void setDB(DBAccessor db);
    
    public abstract DBAccessor getDB();
}