/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service;


import java.util.List;
import model.db.accessor.DBAccessor;
import model.db.accessor.DB_MySql;

/**
 *
 * @author Michele
 */
public class MenuService {
  
    private IMenuDAO menuDAO;
    
    public MenuService(){
    //Ask how to do the injection using the web.xml file
    DBAccessor db = new DB_MySql();
    menuDAO = new MenuDAO(db);
    }
    
    public List<MenuItem> getAllMenuItems() throws Exception{
        return menuDAO.getAllMenuItems();
    
    }
}
