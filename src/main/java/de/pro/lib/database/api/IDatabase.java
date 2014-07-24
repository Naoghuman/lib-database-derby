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
     * @throws SQLException 
     */
    public Clob getClob(String text) throws SQLException;
    
    /**
     * Convert a <code>String</code> from a <code>Clob</code>.
     * 
     * @param clob The <code>Clob</code> which should be convert to a <code>String</code>.
     * @return The converted <code>String</code>.
     * @throws SQLException 
     */
    public String getTextFromClob(final Clob clob) throws SQLException;
    
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
     * Close the sql connection and shutdown the database.
     * 
     * @throws SQLException 
     */
    public void shutdown() throws SQLException;
}
