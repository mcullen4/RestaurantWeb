

package model.db.accessor;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michele
 */
public class DB_MySql implements DBAccessor {
    
    private Connection conn;
    
    
    private static final String URL_ERR = 
            "Invalid URL - Cannot be null or empty string";
    private static final String PARAM_ERR = 
            "Insufficient Information Entered";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    @Override
    public void openConnection(String driverClassName,String url, 
            String userName, String password) throws Exception {
        if(url == null || url.length()==0){
        throw new IllegalArgumentException(URL_ERR);
        }
        try{
        Class.forName(driverClassName);
            
        conn = (Connection)DriverManager.getConnection(url, userName, password);
            System.out.println(conn.getProperties());
        }catch (ClassNotFoundException | SQLException ex){
        //What goes in here????
        }
        
        }

    @Override
    public void closeConnection() throws Exception {
        conn.close();
    }

    public List<Map> simpleMenuQuery (List columnNames, String tableName) throws SQLException{
    final List list = new ArrayList();
    //create StringBuilder and start Query
    StringBuilder sqlStatement = new StringBuilder();
    sqlStatement.append("SELECT ");
    
    /*append the column names to the select statement - expanded to include
    joins, it would require the column names to be set as follows - 
    table.columnName to ensure that no error would result from foreign key 
    columns
    */
    for(int i=0;i<columnNames.size();i++){
    sqlStatement.append(columnNames.get(i));
    sqlStatement.append(", ");
    }
    
    /*appends the table information to the sql query - if joins were created 
    and passed through as parameters, this section would need to be changed
    to a map?
    */
    sqlStatement.deleteCharAt(sqlStatement.length()-2);
    sqlStatement.append("FROM ").append(tableName);
    //sqlStatement.append(";");
    
    String sqlQuery = sqlStatement.toString();
        System.out.println(sqlQuery);
    Statement stmt = null;
    ResultSet rs = null;
    ResultSetMetaData metaData = null;
    LinkedHashMap record = null;
    
    try {
        
	stmt = conn.createStatement();
        //rs = stmt.executeQuery("Select item_id item_name item_price from menu");
	rs = stmt.executeQuery(sqlQuery);
	metaData = rs.getMetaData();
	final int fields=metaData.getColumnCount();

	while( rs.next() ) {
		record = new LinkedHashMap();
		for( int i=1; i <= fields; i++ ) {
			try {
                            record.put( metaData.getColumnName(i), rs.getObject(i) );
			} catch(NullPointerException npe) { 
			// no need to do anything... if it fails, just ignore it and continue
                        }
		} // end for
		list.add(record);
	} // end while

	} catch (SQLException sqle) {
		throw sqle;
	} catch (Exception e) {
		throw e;
	} finally {
//		try {
//			stmt.close();
//			//if(closeConnection) conn.close();
//		} catch(SQLException e) {
//				throw e;
//		} // end try
	} // end finally
    
    
    return list;
    
    }
    
    @Override
    public List<Map> findRecordsFromOneTable(List columnNames, String tableName,
            List<Map<String, Object>> whereClauseCriteria) throws Exception {
    //validates parameters    
    if (columnNames.isEmpty()||tableName==null||tableName.length()==0){
         throw new IllegalArgumentException(PARAM_ERR);
    }
    //create StringBuilder and start Query
    StringBuilder sqlStatement = new StringBuilder();
    sqlStatement.append("SELECT ");
    
    /*append the column names to the select statement - expanded to include
    joins, it would require the column names to be set as follows - 
    table.columnName to ensure that no error would result from foreign key 
    columns
    */
    for(int i=0;i<columnNames.size();i++){
    sqlStatement.append(columnNames.get(i));
    sqlStatement.append(" ");
    }
    
    /*appends the table information to the sql query - if joins were created 
    and passed through as parameters, this section would need to be changed
    to a map?
    */
    sqlStatement.append("FROM ").append(tableName).append(" ");
    
    /*appends the where clause information to the query
    currently can only handle AND for multiple where statements
    needs to be modified to handle OR */
    if(whereClauseCriteria.isEmpty()){
    sqlStatement.append(";");
    }else{
    sqlStatement.append("WHERE ");
    for(int m = 0;m<whereClauseCriteria.size();m++){
        Map map = whereClauseCriteria.get(m);
        sqlStatement.append(map.get("Column Name")).append(" ");
        sqlStatement.append(map.get("Operand")).append(" ");
        //if value is a number, no quotes are necessary
        if(map.get("Value") instanceof Number){
        sqlStatement.append(map.get("Value"));
        }
        //if value is a string, it requires quotes around it
        else{
        sqlStatement.append("\'").append(map.get("Value")).append("\'");
        
        }
        sqlStatement.append(" AND ");
    }
    //deletes the last AND from the sql StringBuilder
    sqlStatement.delete(sqlStatement.length()-5, sqlStatement.length());
    sqlStatement.append(";");    
    /*add group by and order by criteria here using if statements - parameters
    need to be added - use if statements to determine if it needs to be
    added*/
    
    }
    
    //create Statement and result set - convert sqlStatement to string
    String sqlQuery = sqlStatement.toString();
    Statement stmt = null;
    ResultSet rs = null;
    ResultSetMetaData metaData = null;
    final List list=new ArrayList();
    LinkedHashMap record = null;
    
    try {
        
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlQuery);
	metaData = rs.getMetaData();
	final int fields=metaData.getColumnCount();

	while( rs.next() ) {
		record = new LinkedHashMap();
		for( int i=1; i <= fields; i++ ) {
			try {
                            record.put( metaData.getColumnName(i), rs.getObject(i) );
			} catch(NullPointerException npe) { 
			// no need to do anything... if it fails, just ignore it and continue
                        }
		} // end for
		list.add(record);
	} // end while

	} catch (SQLException sqle) {
		throw sqle;
	} catch (Exception e) {
		throw e;
	} finally {
		try {
			stmt.close();
			//if(closeConnection) conn.close();
		} catch(SQLException e) {
				throw e;
		} // end try
	} // end finally
    
    
    return list;
    }

    @Override
    public List<Map> findRecordsFromMultipleTables() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map getRecordByID(String table, String primaryKeyField, Object keyValue, boolean closeConnection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean insertRecord(String tableName, List<Map<String, Object>> columnNamesAndValues, boolean closeConnection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateRecords(String tableName, List<Map<String, Object>> columnNamesAndValues, List<Map<String, Object>> whereClauseFieldNamesAndValues, boolean closeConnection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteRecords(String tableName, List<Map<String, Object>> whereClauseFieldNamesAndValues, boolean closeConnection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    public static void main(String[] args) throws Exception {
        DB_MySql db = new DB_MySql();
        List <Map> results = new ArrayList();
        List list = new ArrayList();
        list.add("item_id");
        list.add("item_name");
        list.add("item_price");
        String tableName = "menu";
        db.openConnection( "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/restaurant", "root", "Skt072317");
        results = db.simpleMenuQuery(list, tableName);
        for (int i = 0;i<results.size();i++){
            Map map = results.get(i);
            System.out.print("Item ID:  ");
            System.out.print(map.get("item_id")+"\n");
            System.out.print("Item Description:  ");
            System.out.print(map.get("item_name")+"\n");
            System.out.print("Item Price");
            //Decimal itemPrice = (Decimal) map.get("item_price");
            //System.out.print(itemPrice+"\n");
           
    }
    }
    
    
    
    
    
}
