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
package de.pro.lib.database.api;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLTimeoutException;

/**
 * The <code>Interface</code> for the class <code>de.pro.lib.database.PRoDatabase</code>.
 * Over the factory <code>de.pro.lib.database.api.DatabaseFactory</code> you can 
 * access the methods in this <code>Interface</code>.
 *
 * @author PRo
 * @see de.pro.lib.database.PRoDatabase
 * @see de.pro.lib.database.api.DatabaseFactory
 */
public interface IDatabase {
    
    /**
     * Provides a sql connection to the registered database.
     * 
     * @return The sql connection.
     */
    public Connection getConnection();
    
    /**
     * Convert a <code>Clob</code> for a <code>String</code>.
     * 
     * @param text The <code>String</code> which should be convert to a <code>Clob</code>.
     * @return The converted <code>Clob</code>.
     * @throws SQLException If an object that implements the Clob interface can not be constructed, this method is called on a closed connection or a database access error occurs.
     * @throws SQLFeatureNotSupportedException If the JDBC driver does not support this data type.
     */
    public Clob getClob(String text) throws SQLException, SQLFeatureNotSupportedException;
    
    /**
     * Convert a <code>String</code> from a <code>Clob</code>.
     * 
     * @param clob The <code>Clob</code> which should be convert to a <code>String</code>.
     * @return The converted <code>String</code>.
     * @throws SQLException If there is an error accessing the CLOB value; if pos is less than 1 or length is less than 0.
     * @throws SQLFeatureNotSupportedException If the JDBC driver does not support this data type.
     */
    public String getTextFromClob(final Clob clob) throws SQLException, SQLFeatureNotSupportedException;
    
    /**
     * Create a database with the specific name in the folder System.getProperty("user.dir")
     * + File.separator + "database" if it not exists.
     * 
     * @param databaseName The name for the database which should be created.
     * @param user The user for the database.
     * @param password The password for the database.
     */
    public void register(String databaseName, String user, String password);
    
    /**
     * Close the sql connection and shutdown the database which was register.
     * 
     * @throws SQLException If a database access error occurs or the url is null.
     * @throws SQLTimeoutException When the driver has determined that the timeout value specified by the setLoginTimeout method has been exceeded and has at least tried to cancel the current database connection attempt.
     */
    public void shutdown() throws SQLException, SQLTimeoutException;
}
