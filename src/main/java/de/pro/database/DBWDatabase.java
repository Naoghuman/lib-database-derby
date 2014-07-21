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
package de.pro.database;

import de.pro.database.api.IDatabase;
import de.pro.logger.api.LoggerFactory;
import java.io.File;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author PRo
 */
public final class DBWDatabase implements IDatabase {
    
    private static final String DATABASE_PATH =
            "jdbc:derby:"
            + System.getProperty("user.dir") // NOI18N
            + File.separator + "database" + File.separator; // NOI18N
    private static final String DATABASE_CREATE_TRUE = ";create=true"; // NOI18N
    
    private Connection connection = null;
    
    @Override
    public Clob getClob(String text) throws SQLException {
        final Clob clob = this.getConnection().createClob();
        clob.setString(1, text);

        return clob;
    }
    
    @Override
    public Connection getConnection() {
        return connection;
    }
    
    @Override
    public String getTextFromClob(final Clob clob) throws SQLException {
        final long length = clob.length();
        return clob.getSubString(1, Integer.parseInt(String.valueOf(length)));
    }
    
    @Override
    public void register(String databaseName, String user, String password) {
        LoggerFactory.getDefault().info(IDatabase.class,
                "Check if database folder exists and create it if necessary"); // NOI18N
        
        final String path = System.getProperty("user.dir") + File.separator + "database"; // NOI18N
        final File file = new File(path);
        file.mkdir();
        
        try {
            LoggerFactory.getDefault().info(IDatabase.class, "Init sql-driver: org.apache.derby.jdbc.EmbeddedDriver"); // NOI18N
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); // NOI18N
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            LoggerFactory.getDefault().error(this.getClass(),
                    "Can't create embbed-derby driver", ex); // NOI18N
        }
        try {
            LoggerFactory.getDefault().info(IDatabase.class,
                    "Create connection to: " + DATABASE_PATH + databaseName); // NOI18N
            
            connection = DriverManager.getConnection(DATABASE_PATH + databaseName + DATABASE_CREATE_TRUE, user, password);
        } catch (SQLException ex) {
            LoggerFactory.getDefault().error(this.getClass(),
                    "Can't initialze sql connection", ex); // NOI18N
        }
    }

    @Override
    public void shutdown() throws SQLException {
        LoggerFactory.getDefault().info(IDatabase.class, "Shutdown derby"); // NOI18N
        
        DriverManager.getConnection("jdbc:derby:;shutdown=true"); // NOI18N
        
        // Check if the connection created or open
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        
        connection = null;
    }
}
