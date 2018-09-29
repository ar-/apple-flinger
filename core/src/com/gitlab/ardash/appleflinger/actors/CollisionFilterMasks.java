/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <ar-appleflinger@abga.be>
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
 ******************************************************************************/
package com.gitlab.ardash.appleflinger.actors;

public class CollisionFilterMasks {
    public static final short NONE = 0x00;
    public static final short GROUND = 0x01;
    public static final short ABSORBER = 0x02;
    public static final short HANS_HAND = 0x04;
    public static final short GRENADE = 0x08;
    /**
     * Other than listed here.
     */
    public static final short OTHER = 0x16;
    /**
     * All bodies.
     */
    public static final short ALL = 0xff;
}
