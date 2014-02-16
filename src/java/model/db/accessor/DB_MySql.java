

package model.db.accessor;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    

    
    
    
    @Override
    public void openConnection(String driverClassName,String url, 
            String userName, String password) throws Exception {
        
        if(url == null || url.length()==0){
        throw new IllegalArgumentException(URL_ERR);
        }
        Class.forName(driverClassName);
        conn = (Connection)DriverManager.getConnection(url, userName, password);
            System.out.println(conn.getProperties());
        
        }

    
    
    
    
    @Override
    public void closeConnection() throws Exception {
        conn.close();
    }

    
    
    public List<Map> simpleMenuQuery (List columnNames, String tableName)
            throws SQLException{
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
        rs = stmt.executeQuery(sqlQuery);
	metaData = rs.getMetaData();
	final int fields=metaData.getColumnCount();

	while( rs.next() ) {
		record = new LinkedHashMap();
		for( int i=1; i <= fields; i++ ) {
			try {
                            record.put( metaData.getColumnName(i), 
                                    rs.getObject(i) );
			} catch(NullPointerException npe) { 
			//Nothing has to be put in here 
                        }
		} // end for
		list.add(record);
                } // end while
        stmt.close();
	} catch (SQLException sqle) {
		throw sqle;
	} catch (Exception e) {
		throw e;
	} finally {
		try {
                    conn.close();
		} catch(SQLException e) {
                    throw e;
		} 
	} 
    
    
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
			//nothing has to go in here
                        }
		} // end for
		list.add(record);
                stmt.close();
	} // end while

	} catch (SQLException sqle) {
		throw sqle;
	} catch (Exception e) {
		throw e;
	} finally {
		try {
			
			conn.close();
		} catch(SQLException e) {
				throw e;
		} // end try
	} // end finally
    
    
    return list;
    }

    

    @Override
    public Map getRecordByID(String table, String primaryKeyField, 
        Object keyValue, boolean closeConnection) throws Exception {
        Map record = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(table).append(" WHERE ").append(primaryKeyField);
        sql.append(" = ");
        if (keyValue instanceof Number){
        sql.append(keyValue);
        }else{
        sql.append("\'").append(keyValue).append("\'");
        }
        sql.append(";");
        String finalSQL = sql.toString();
       
      
    
    try {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(finalSQL);
        ResultSetMetaData metaData=null;
	metaData = rs.getMetaData();
	final int fields=metaData.getColumnCount();
        

        record = new LinkedHashMap();
            if (rs.next()) {
                for (int i = 1; i <= fields; i++) {

                    try {

                        record.put(metaData.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {
                        //nothing has to go here
                    }
                } // end for
            }

            stmt.close();
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw e;
            }
        }

        return record;
    }

    @Override
    public Boolean insertRecord(String tableName, Map<String,Object> columnData)
            throws Exception {
        List columnNames = new ArrayList();
        List columnValues = new ArrayList();
        int recordsUpdated=0;
        
        PreparedStatement pstmt = null;
        columnNames.addAll(columnData.keySet());
        columnValues.addAll(columnData.values());
        
        
        try{
        pstmt = prepareInsertStatement(conn,tableName,columnNames);
        int index = 1;
        for(int i=0;i<columnValues.size();i++){
            final Object obj = columnValues.get(i);
            if (obj instanceof String) {
                pstmt.setString(index++, (String) obj);
            } else if (obj instanceof Integer) {
                pstmt.setInt(index++, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                pstmt.setLong(index++, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                pstmt.setDouble(index++, ((Double) obj).doubleValue());
            } else if (obj instanceof java.sql.Date) {
                pstmt.setDate(index++, (java.sql.Date) obj);
            } else if (obj instanceof Boolean) {
                pstmt.setBoolean(index++, ((Boolean) obj).booleanValue());
            } else {
                if (obj != null) {
                    pstmt.setObject(index++, obj);
                }
            }
        }
            recordsUpdated = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                
                conn.close();
            } catch (SQLException e) {
                throw e;
			} 
		} 
        
        if(recordsUpdated==1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int updateRecords(String tableName, Map<String, Object> columnData,
            String whereField, Object whereValue) throws Exception {
        List columnNames = new ArrayList();
        List columnValues = new ArrayList();
        int recordsUpdated=0;
        
        PreparedStatement pstmt = null;
        columnNames.addAll(columnData.keySet());
        columnValues.addAll(columnData.values());  
        try{
        pstmt = prepareUpdateStatement(conn,tableName,columnNames,whereField);
        int index = 1;
        for(int i=0;i<columnValues.size();i++){
            final Object obj = columnValues.get(i);
            if (obj instanceof String) {
                pstmt.setString(index++, (String) obj);
            } else if (obj instanceof Integer) {
                pstmt.setInt(index++, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                pstmt.setLong(index++, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                pstmt.setDouble(index++, ((Double) obj).doubleValue());
            } else if (obj instanceof java.sql.Date) {
                pstmt.setDate(index++, (java.sql.Date) obj);
            } else if (obj instanceof Boolean) {
                pstmt.setBoolean(index++, ((Boolean) obj).booleanValue());
            } else {
                if (obj != null) {
                    pstmt.setObject(index++, obj);
                }
            }
        }
        pstmt.setObject(index++,whereValue);
            recordsUpdated = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw e;
			} 
		} 
        return recordsUpdated;
        
    }

    @Override
    public int deleteRecords(String tableName, String whereField, 
            Object whereValue) throws Exception {
        int numberDeleted=0;
        PreparedStatement pstmt = null;
        try {
	 pstmt = prepareDeleteStatement(conn,tableName,whereField);
        

            // delete all records if whereField is null
            if (whereField != null) {
                if (whereValue instanceof String) {
                    pstmt.setString(1, (String) whereValue);
                } else if (whereValue instanceof Number) {
                    pstmt.setInt(1, ((Number) whereValue).intValue());
                } else if (whereValue instanceof java.sql.Date) {
                    pstmt.setDate(1, (java.sql.Date) whereValue);
                } else if (whereValue instanceof Boolean) {
                    pstmt.setBoolean(1, ((Boolean) whereValue).booleanValue());
                } else {
                    if (whereValue != null) {
                        pstmt.setObject(1, whereValue);
                    }
                }
            }

			numberDeleted = pstmt.executeUpdate();
                        pstmt.close();
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
			conn.close();
			} catch(SQLException e) {
				throw e;
			} // end try
		} // end finally

		return numberDeleted;
        
    }
    
    private PreparedStatement prepareInsertStatement
        (Connection connection,String tableName, List columnNames)
                throws SQLException{
        
        if (columnNames.isEmpty()){
        //if column Names is empty throw some type of exception
        }
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" (");
        for (int i = 0;i <columnNames.size();i++){
        sql.append(columnNames.get(i)).append(", ");
        }
        sql.delete(sql.length()-2,sql.length());
        sql.append(") VALUES (");
        for (int a=0;a< columnNames.size();a++){
        sql.append("?, ");
        
        }
        sql.delete(sql.length()-2, sql.length());
        sql.append(");");
        final String finalSQL = sql.toString();
        System.out.println(finalSQL);
        return connection.prepareStatement(finalSQL);
       
    }
        
        private PreparedStatement prepareUpdateStatement(Connection connection,
                String tableName, List columnNames, String whereField) 
                throws SQLException{
            
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(tableName).append(" SET ");
        for (int i = 0;i <columnNames.size();i++){
        sql.append(columnNames.get(i)).append(" = ?, ");
        }
        sql.delete(sql.length()-2,sql.length());
        sql.append(" WHERE ").append(whereField).append(" = ?;");
            
        final String finalSQL = sql.toString();
        System.out.println(finalSQL);
        return connection.prepareStatement(finalSQL);
        }
    
    private PreparedStatement prepareDeleteStatement(Connection connection,
            String tableName, String whereField) throws Exception{
        
        if(whereField==null){
            //I have this in here to make sure all the contents 
            //of a table aren't deleted on accident
            throw new Exception();
        }else{
        
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(tableName);
        sql.append(" WHERE ");
        sql.append(whereField).append(" = ?;");
        
        final String finalSQL = sql.toString();
        System.out.println(finalSQL);
        return connection.prepareStatement(finalSQL);    
        }
    
    }
   
    public static void main(String[] args) throws Exception {
        DB_MySql db = new DB_MySql();
//        LinkedHashMap record = (LinkedHashMap) db.getRecordByID("menu","item_id", 7, true);
//        System.out.println(record);
//        List <Map> results = new ArrayList();
//        List list = new ArrayList();
//        list.add("item_id");
//        list.add("item_name");
//        list.add("item_price");
//        String tableName = "menu";
//        String whereField = "item_id";
//        Map<String,Object> map = new LinkedHashMap<>();
//        map.put("item_name", "Bucket of Chicken");
//        map.put("item_price", 19.99);
        db.openConnection( "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/restaurant", "root", "admin");
//        Boolean insertRecord = db.insertRecord(tableName, map);
//        System.out.println(insertRecord);
        //Class.forName("com.mysql.jdbc.Driver");
        Map <String,Object> map = new LinkedHashMap();
        map.put("item_name", "updated Name");
        //Connection connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "admin");
        int noDelete = db.updateRecords("menu",map,"item_id", 3);
        //System.out.println(record);
        //System.out.println(conn.getProperties());
//        results = db.simpleMenuQuery(list, tableName);
//        for (int i = 0;i<results.size();i++){
//            Map map = results.get(i);
//            System.out.print("Item ID:  ");
//            System.out.print(map.get("item_id")+"\n");
//            System.out.print("Item Description:  ");
//            System.out.print(map.get("item_name")+"\n");
//            System.out.print("Item Price");
            //Decimal itemPrice = (Decimal) map.get("item_price");
            //System.out.print(itemPrice+"\n");
//        PreparedStatement stmt = db.prepareUpdateStatement(connection,tableName,list, whereField);
//        System.out.println(stmt.toString());
           
    }
    }
    
    
    

