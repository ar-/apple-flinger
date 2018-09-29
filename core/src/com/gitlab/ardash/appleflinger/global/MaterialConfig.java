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
package com.gitlab.ardash.appleflinger.global;

import com.badlogic.gdx.graphics.Texture;
import com.gitlab.ardash.appleflinger.global.Assets.SpriteGroupAsset;
import com.gitlab.ardash.appleflinger.global.Assets.TextureAsset;

public enum MaterialConfig {
    INVISIBLE (TextureAsset.INVISIBLE, 300, 1.0f, 1.0f, 1, 1, 0.1f),
//    GLASS (Assets.glass, 300, 1.0f, 1.0f, 1, 1, 0.1f),
//    WOOD (Assets.manager.get(Assets.wood, Texture.class), 400, 0.1f, 0.5f, 1, 1, 0.1f),
    STONE (Assets.TextureAsset.STONE, 500, 0.1f, 0.5f, 	1, 0.9f, 0.0f),
//  METAL (Assets.metal, 600, 0.1f, 0.5f, 1, 1, 0.0f);
    WOOD_TNT (Assets.SpriteGroupAsset.WOOD_TNT, 50/16, 0.1f, 0.5f, 1, 0.4f, 0.0f),
    WOOD_RECT (Assets.SpriteGroupAsset.WOOD_RECT, 50, 0.1f, 0.5f, 1, 0.4f, 0.0f),
    
    WOOD_BL_11 (Assets.SpriteGroupAsset.WOOD_BL_11, 50/16, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    WOOD_BL_21 (Assets.SpriteGroupAsset.WOOD_BL_21, 50/8, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    WOOD_BL_22 (Assets.SpriteGroupAsset.WOOD_BL_22, 12.5f, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    WOOD_BL_41 (Assets.SpriteGroupAsset.WOOD_BL_41, 12.5f, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    WOOD_BL_42 (Assets.SpriteGroupAsset.WOOD_BL_42, 25, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    WOOD_BL_81 (Assets.SpriteGroupAsset.WOOD_BL_81, 25, 0.1f, 0.4f, 1, 0.5f, 0.0f),
    
    PROJECTILE (Assets.TextureAsset.STONE, 500, 0.1f, 0.99f, 	1, 0.9f, 0.1f),
    TARGET_DORK (Assets.SpriteGroupAsset.DORK, 10, 0.1f, 500f, 10, 0.9f, 0.0f), // high damping so they don't roll easily
    TARGET_PENG (Assets.SpriteGroupAsset.PENG, 10, 0.1f, 500f, 10, 0.9f, 0.0f); // high damping so they don't roll easily
    

    public Texture texture;
    public SpriteGroupAsset spriteGroup;
    public final float hp;
    public final float linearDamping;
    public final float angularDamping;
    public final float density;
    public final float friction;
    public final float restitution;


    MaterialConfig(TextureAsset texture, float hp, float linearDamping, float angularDamping, float density, float friction, float restitution) {
    	if (texture != TextureAsset.INVISIBLE)
        this.texture = Assets.getTexture(texture);
        this.hp = hp;
        this.linearDamping = linearDamping;
        this.angularDamping = angularDamping;
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
    }
    
    MaterialConfig(SpriteGroupAsset spa, float hp, float linearDamping, float angularDamping, float density, float friction, float restitution) {
        this.spriteGroup = spa;
        this.hp = hp;
        this.linearDamping = linearDamping;
        this.angularDamping = angularDamping;
        this.density = density;
        this.friction = friction;
        this.restitution = restitution;
    }
    
//    private double mass() { return linearDamping; }
//    private double radius() { return angularDamping; }
//
//    // universal gravitational constant  (m3 kg-1 s-2)
//    public static final double G = 6.67300E-11;
//
//    double surfaceGravity() {
//        return G * linearDamping / (angularDamping * angularDamping);
//    }
//    double surfaceWeight(double otherMass) {
//        return otherMass * surfaceGravity();
//    }
//    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.err.println("Usage: java Planet <earth_weight>");
//            System.exit(-1);
//        }
//        double earthWeight = Double.parseDouble(args[0]);
//        double mass = earthWeight/EARTH.surfaceGravity();
//        for (MaterialConfig p : MaterialConfig.values())
//           System.out.printf("Your weight on %s is %f%n",
//                             p, p.surfaceWeight(mass));
//    }
}
