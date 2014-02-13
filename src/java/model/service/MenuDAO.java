/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.db.accessor.DBAccessor;

/**
 *
 * @author Michele
 */
public class MenuDAO implements IMenuDAO {
    
    DBAccessor db;
    private final String TABLE_NAME = "menu";
    
    public MenuDAO(DBAccessor db){
    this.db=db;
    }

    @Override
    public void save(MenuItem menuItem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MenuItem menuItem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuItem findMenuItemByID(String ID) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MenuItem> getAllMenuItems() throws Exception {
    db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/restaurant", "root", "admin");
        List<Map> rawData = new ArrayList<>();
        List columnNames = new ArrayList();
        columnNames.add("*");
        List<Map<String,Object>>whereClauseCriteria = new ArrayList();
        List<MenuItem> records = new ArrayList<>();
        try {
            rawData = db.findRecordsFromOneTable(columnNames,TABLE_NAME, whereClauseCriteria);
        } catch (SQLException e1) {
            //throw new DataAccessException(e1.getMessage(), e1);

        } catch (Exception e2) {
            //throw new DataAccessException(e2.getMessage(), e2);
        }

        MenuItem menuItem = null;

        // Translate List<Map> into List<Employee>
        for (Map m : rawData) {
            
            int id = Integer.valueOf(m.get("item_id").toString());
            String name = m.get("item_name").toString();
            double price = Double.valueOf(m.get("item_price").toString());
            menuItem = new MenuItem(id,name,price);
            
            records.add(menuItem);
        }

        return records;
    }

    @Override
    public void setDB(DBAccessor db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBAccessor getDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    
    

