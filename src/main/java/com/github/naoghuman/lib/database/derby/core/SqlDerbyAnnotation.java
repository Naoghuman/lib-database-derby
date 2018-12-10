/*
 * Copyright (C) 2018 Naoghuman's dream
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
 *
 * @since   0.2.0
 * @version 0.2.0
 * @author  Naoghuman
 */
public interface SqlDerbyAnnotation {
    
    /**
     * 
     * @param   sqlDerbys 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    public void register(final SqlDerby... sqlDerbys);
    
    /**
     * 
     * @param   sqlDergyTypes 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    public void execute(final SqlDerby.Type... sqlDergyTypes);
}
