/*
 * Copyright (C) 2014 PRo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.pro.lib.database.version;

import de.pro.lib.database.version.api.IVersion;
import de.pro.lib.logger.api.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * In this class the preparation for all versions in the application is done.
 *
 * @author PRo
 */
public class PrepareVersioning {
    private static final String SUFFIX_SNAPSHOT = "-SNAPSHOT"; // NOI18N
    
    private static Connection connection = null;
    
    /**
     * Checks the given table if it exists.
     * 
     * @param table The table which will check if exists.
     * @return <code>true</code> if the table exists, otherwise <code>false</code>.
     */
    public static Boolean checkIfTableExists(String table) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.getMetaData().getTables("%", "%", "%", new String[] { "TABLE" }); // NOI18N
            while(resultSet.next()) {
                if (resultSet.getString("TABLE_NAME").equalsIgnoreCase(table)){ // NOI18N
                    return Boolean.TRUE;
                }
            }
        } catch (SQLException ex) {
            LoggerFactory.getDefault().error(PrepareVersioning.class,
                    "Can't check if table " + table + " exists...", ex); // NOI18N
        } finally {
            try {
                if (resultSet != null) { resultSet.close(); }
            } catch (SQLException ex) {
            }
        }
        
        return Boolean.FALSE;
    }
    
    /**
     * Check the given table if test data should created.
     * 
     * @param table The table which will controlled.
     * @return <code>true</code> if there no data in the table.
     */
    @SuppressWarnings("null")
    public static Boolean checkIfTableShouldPopulatedWithTestData(String table) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + table);
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        } catch (SQLException ex) {
            LoggerFactory.getDefault().error(PrepareVersioning.class,
                    "Can't count entries in table " + table, ex); // NOI18N
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
        
        return Boolean.TRUE;
    }
        /*
    TODO integrate in javadoc
    
                     newVersion           actualVersion
System.out.println("\"v0.0.1\".compareTo(\"v0.0.0\"): " + "v0.0.1".compareTo("v0.0.0")); = 1
System.out.println("\"v1.1.0\".compareTo(\"v1.1.0\"): " + "v1.1.0".compareTo("v1.1.0")); = 0
System.out.println("\"v1.1.0\".compareTo(\"v1.1.1\"): " + "v1.1.0".compareTo("v1.1.1")); = -1
        */
    /**
     * In this method all new versions are done. See the JavaDoc from 
     * {@link de.pro.lib.database.version.api.IVersion IVersion} for an advanced
     * description.
     * 
     * @param shouldCreateTestData If <code>true</code> then test data will create,
     * otherwise not.
     */
    public void init(Boolean shouldCreateTestData) {
//        String newVersion = PropertiesFactory.getDefault().getProperty(
//                EProperties.Application_Config,
//                IProperties.APPLICATION_CONFIG_VERSION);
//        if (newVersion.contains(SUFFIX_SNAPSHOT)) {
//            newVersion = newVersion.substring(0, newVersion.length() - SUFFIX_SNAPSHOT.length());
//        }
//        
//        final String actualVersion = PreferencesFactory.getDefault().get(
//                IPreferences.SYSTEM_APPLICATION__VERSION,
//                IPreferences.SYSTEM_APPLICATION__VERSION_DEFAULT_VALUE);
//        LoggerFactory.getDefault().info(IDatabase.class,
//                String.format("new.version=%s <-> %s=actual.version", // NOI18N
//                        newVersion, actualVersion));

//        if (newVersion.compareTo(actualVersion) > 0 && actualVersion.compareTo("v0.1.0") <= -1) { // NOI18N
//            LoggerFactory.getDefault().info(this.getClass(), "Update database to v0.1.0 ..."); // NOI18N
//            
//            this.init(new TemplateVnrForXy(connection), shouldCreateTestData);
//        }
        
        // Update actual version
//        if (newVersion.compareTo(actualVersion) != 0) {
//            PreferencesFactory.getDefault().put(
//                    IPreferences.SYSTEM_APPLICATION__VERSION,
//                    newVersion);
//        }
    }
    
    private void init(IVersion version, Boolean shouldCreateTestData) {
        version.initTable();
        version.initDefaultData();
        
        if (shouldCreateTestData) {
            version.initTestData();
        }
    }
    
    /**
     * Register the connection in this class.
     * 
     * @param con The connection to the database.
     */
    public void register(Connection con) {
        connection = con;
    }
     
}
