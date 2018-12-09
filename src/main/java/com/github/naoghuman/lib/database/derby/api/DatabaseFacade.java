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
package com.github.naoghuman.lib.database.derby.api;

import com.github.naoghuman.lib.database.derby.LibDatabase;

/**
 * The facade {@link com.github.naoghuman.lib.database.derby.api.DatabaseFacade} provides a 
 * singleton instance of the Interface {@link com.github.naoghuman.lib.database.derby.api.ILibDatabase}.
 *
 * @author PRo
 * @see com.github.naoghuman.lib.database.derby.api.ILibDatabase
 */
public final class DatabaseFacade {
    
    private static ILibDatabase instance = null;
    
    /**
     * Provides a singleton instance from the Interface {@link com.github.naoghuman.lib.database.derby.api.ILibDatabase}.
     * 
     * @return A singleton instance of {@link com.github.naoghuman.lib.database.derby.api.ILibDatabase}.
     * @see com.github.naoghuman.lib.database.derby.api.ILibDatabase
     */
    public static ILibDatabase getDefault() {
        if (instance == null) {
            instance = new LibDatabase();
        }
        
        return instance;
    }
    
    private DatabaseFacade() { }
    
}
