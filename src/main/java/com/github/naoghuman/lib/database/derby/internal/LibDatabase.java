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
package com.github.naoghuman.lib.database.derby.internal;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.database.derby.core.ILibDatabase;
import java.io.File;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLTimeoutException;

/**
 * The implementation from the Interface {@link com.github.naoghuman.lib.database.derby.core.ILibDatabase}.<br />
 * Access to this class is over the facade {@link com.github.naoghuman.lib.database.derby.core.DatabaseFacade}.
 * 
 * @author PRo
 * @see com.github.naoghuman.lib.database.derby.core.ILibDatabase
 * @see com.github.naoghuman.lib.database.derby.core.DatabaseFacade
 */
public final class LibDatabase implements ILibDatabase {
    
    private static final String DATABASE_PATH = "jdbc:derby:" + System.getProperty("user.dir") // NOI18N
            + File.separator + "database" + File.separator; // NOI18N
    private static final String DATABASE_CREATE_TRUE = ";create=true"; // NOI18N
    
    private Connection connection = null;
    private String databaseName = null;
    
    @Override
    public Clob getClob(String text) throws SQLException, SQLFeatureNotSupportedException {
        final Clob clob = this.getConnection().createClob();
        clob.setString(1, text);
        
        return clob;
    }
    
    @Override
    public Connection getConnection() {
        return connection;
    }
    
    @Override
    public String getTextFromClob(final Clob clob) throws SQLException, SQLFeatureNotSupportedException {
        final long length = clob.length();
        return clob.getSubString(1, Integer.parseInt(String.valueOf(length)));
    }
    
    private void initConnection(String databaseName, String user, String password) throws SQLException {
            LoggerFacade.getDefault().own(this.getClass(),
                    "Create connection to: " + DATABASE_PATH + databaseName); // NOI18N
            
            connection = DriverManager.getConnection(DATABASE_PATH + databaseName + DATABASE_CREATE_TRUE, user, password);
            
            databaseName = null;
            user = null;
            password = null;
    }
    
    private void initDatabaseFolder(Boolean shouldDeleteFolders) {
        // TODO delete folder if
        LoggerFacade.getDefault().own(this.getClass(), "Check if database folder exists..."); // NOI18N
        
        final String path = System.getProperty("user.dir") + File.separator + "database"; // NOI18N
        final File file = new File(path);
        file.mkdir();
    }
    
    private void initEmbbedDerbyDriver() throws Exception {
        LoggerFacade.getDefault().own(this.getClass(),
                "Init sql-driver: org.apache.derby.jdbc.EmbeddedDriver"); // NOI18N
        
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); // NOI18N
    }
    
    @Override
    public void register(String databaseName, String user, String password) {
        this.initDatabaseFolder(Boolean.FALSE); // TODO extract from parameter
        
        try {
            this.initEmbbedDerbyDriver();
        } catch (Exception ex) {
            LoggerFacade.getDefault().error(this.getClass(),
                    "Can't register embbed-derby driver", ex); // NOI18N
        }
        
        try {
            this.initConnection(databaseName, user, password);
        } catch (SQLException ex) {
            LoggerFacade.getDefault().error(this.getClass(),
                    "Can't initialze sql connection", ex); // NOI18N
        }
    }

    @Override
    public void shutdown() throws SQLException, SQLTimeoutException {
        LoggerFacade.getDefault().own(ILibDatabase.class, "Close sql connection"); // NOI18N
        
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
        
        LoggerFacade.getDefault().own(ILibDatabase.class, "Shutdown derby"); // NOI18N
        
        DriverManager.getConnection("jdbc:derby:" + databaseName + ";shutdown=true"); // NOI18N
    }
}
