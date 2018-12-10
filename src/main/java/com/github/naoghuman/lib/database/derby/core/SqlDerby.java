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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @since   0.2.0
 * @version 0.2.0
 * @author  Naoghuman
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SqlDerby {
    
    /**
     * 
     * @return 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    Type type() default Type.NO_TYPE;
    
    /**
     * 
     * @return 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    int mayor() default 0;
    
    /**
     * 
     * @return 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    int minor() default 0;
    
    /**
     * 
     * @return 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    int patch() default 0;
    
    /**
     * 
     * @since   0.2.0
     * @version 0.2.0
     * @author  Naoghuman
     */
    public enum Type {
        
        /**
         * 
         * @since   0.2.0
         * @version 0.2.0
         * @author  Naoghuman
         */
        INITIALIZATION,
        
        /**
         * 
         * @since   0.2.0
         * @version 0.2.0
         * @author  Naoghuman
         */
        DEFAULT,
        
        /**
         * 
         * @since   0.2.0
         * @version 0.2.0
         * @author  Naoghuman
         */
        MIGRATION,
        
        /**
         * 
         * @since   0.2.0
         * @version 0.2.0
         * @author  Naoghuman
         */
        NO_TYPE,
        
        /**
         * 
         * @since   0.2.0
         * @version 0.2.0
         * @author  Naoghuman
         */
        TEST;
        
    }
    
}
