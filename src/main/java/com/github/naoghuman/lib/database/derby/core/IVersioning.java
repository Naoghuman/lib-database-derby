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
package com.github.naoghuman.lib.database.derby.core;

/**
 * Use this <code>Interface</code> for versioning your database. Use the template
 * {@link de.pro.lib.database.version.TemplateVnrForXy TemplateVnrForXy} to implement
 * your changes in the database in the actual version.<p />
 * 
 * For example:<br />
 * <ul>
 * <li>Create package de.pro.lib.database.provider.version.v0_1_0.</li>
 * <li>In this new package create a new class <code>V0_1_0ForCategory</code>
 * with the template.</li>
 * <li>Integrate the new class <code>V0_1_0ForCategory</code> in the method
 * de.pro.lib.database.provider.version.Versioning.init(java.lang.Boolean)
 * </ul>
 *
 * @author PRo
 * @see de.pro.lib.database.version.TemplateVnrForXy
 */
public interface IVersioning {
    
    /**
     * Initialize your table in this method.
     */
    public void initTable();
    
    /**
     * Initialize you default data for your previous created table in this method.
     * 
     * @see #initTable()
     */
    public void initDefaultData();
    
    /**
     * Initialize your test data for your previous created table in this method.
     * 
     * @see #initTable()
     */
    public void initTestData();
    
}
