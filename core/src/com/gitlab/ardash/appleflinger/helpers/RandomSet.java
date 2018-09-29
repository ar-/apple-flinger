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
package com.gitlab.ardash.appleflinger.helpers;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;

public class RandomSet<E> extends AbstractSet<E> {

    List<E> dta = new ArrayList<>();
    Map<E, Integer> idx = new HashMap<>();

    public RandomSet() {
    }

    public RandomSet(Collection<E> items) {
        for (E item : items) {
            idx.put(item, dta.size());
            dta.add(item);
        }
    }

    @Override
    public boolean add(E item) {
        if (idx.containsKey(item)) {
            return false;
        }
        idx.put(item, dta.size());
        dta.add(item);
        return true;
    }

    /**
     * Override element at position <code>id</code> with last element.
     * @param id
     */
    public E removeAt(int id) {
        if (id >= dta.size()) {
            return null;
        }
        E res = dta.get(id);
        idx.remove(res);
        E last = dta.remove(dta.size() - 1);
        // skip filling the hole if last is removed
        if (id < dta.size()) {
            idx.put(last, id);
            dta.set(id, last);
        }
        return res;
    }

    @Override
    public boolean remove(Object item) {
        Integer id = idx.get(item);
        if (id == null) {
            return false;
        }
        removeAt(id);
        return true;
    }

    public E get(int i) {
        return dta.get(i);
    }
    
    public E getRandom() {
        if (dta.isEmpty()) {
            return null;
        }
        int id = MathUtils.random(0, this.size()-1);
    	return get(id);
    }
    
//    public E pollRandom(Random rnd) {
//        if (dta.isEmpty()) {
//            return null;
//        }
//        int id = rnd.nextInt(dta.size());
//        return removeAt(id);
//    }

    @Override
    public int size() {
        return dta.size();
    }

    @Override
    public Iterator<E> iterator() {
        return dta.iterator();
    }
}
