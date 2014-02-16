

package model.db.accessor;

import java.util.List;
import java.util.Map;

/**
 *Interface class for accessing a database and performing basic CRUD functions
 * on a database
 * @author Michele
 */
public interface DBAccessor {
    
    /**
     * This method is used to open a connection to the database
     * @param driverClassName - fully qualified name of the driver
     * @param url the url of the server and database name
     * @param username a String representing the user name, driver dependent
     * @param password a String representing the password 
     * @throws Exception
     */
    public abstract void openConnection(String driverClassName, String url, 
            String username, String password) throws Exception;
    
    
    
    /**
     * Method used to close the connection to a database
     * @throws Exception
     */
    public abstract void closeConnection() throws Exception;
    
    
    
    /**
     *
     * @param columnNames a List object with the column names of the table
     * @param tableName a String representing the table name
     * @param whereClauseCriteria - list of maps representing the where clause
     * criteria to be used in the SQL statement
     * @return a list of maps representing the records searched for in the SQL 
     * statement
     * @throws Exception
     */
    public abstract List <Map> findRecordsFromOneTable(List columnNames,
            String tableName,List<Map<String,Object>> whereClauseCriteria) throws 
            Exception;
    
    
    
    

    /**
     * Method to retrieve a record using the primary key of the table
     * @param table - String representing table name
     * @param primaryKeyField - String representing column that contains primary
     * key
     * @param keyValue - Object that contains the value of the primary key
     * @param closeConnection - boolean that instructs method whether or not to
     * close the connection after completing method
     * @return a map representing the record requested
     * @throws Exception
     */
    public abstract Map getRecordByID(String table, String primaryKeyField, 
            Object keyValue, boolean closeConnection)throws Exception;
    
    

    /**
     * Method used to insert a record into a single table
     * @param tableName String representing the table name
     * @param columnData
     * @param columnNames
     * @param columnValues
     * @return a boolean indicating whether record was inserted
     * @throws Exception
     */
    public abstract Boolean insertRecord(String tableName, 
            Map<String,Object> columnData)throws Exception;

    /**
     * Method used to update certain columns of a record based upon criteria in
     * a where clause of the SQL statement
     * @param tableName - String representing name of table
     * @param columnNamesAndValues - a list of maps representing the column names
     * and values to be updated within those columns
     * @param whereField
     * @param whereValue
     * @param whereClauseFieldNamesAndValues a list of maps representing the
     * where field names and values to be used in selecting which records 
     * should be updated
     * @param closeConnection a boolean representing whether the method should
     * close the connection upon completion
     * @return number of records updated
     * @throws Exception
     */
    public int updateRecords(String tableName, Map<String, Object> columnData,
            String whereField, Object whereValue)
            throws Exception;

    /**
     * Method used to delete records from a single table
     * @param tableName a String representing the table name
     * @param whereClauseFieldNamesAndValues a list of maps representing the
     * where clause selection fields and values
     * @param closeConnection a boolean that indicates whether or not the 
     * connection should be closed upon completion of the method
     * @return number of records deleted
     * @throws Exception
     */
    public abstract int deleteRecords(String tableName, String whereField, Object whereValue)
	throws Exception;
}
