/*
This file is part of Tek271 Reverse Proxy Server.

Tek271 Reverse Proxy Server is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Tek271 Reverse Proxy Server is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Tek271 Reverse Proxy Server.  If not, see http://www.gnu.org/licenses/
 */
package com.tek271.reverseProxy.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Tuple2<E1, E2> {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static final Tuple2 NULL= new Tuple2(null, null);
    
    public final E1 e1;
    public final E2 e2;

    public Tuple2(E1 e1, E2 e2) {
        this.e1= e1;
        this.e2= e2;
    }
    
    public static <E1, E2> Tuple2<E1, E2> tuple2(E1 e1, E2 e2) {
        return new Tuple2<E1, E2>(e1, e2);
    }
    
    @SuppressWarnings("unchecked")
    public static <E1, E2> Tuple2<E1, E2> nil() {
        return NULL;
    }

    public boolean isNull() {
        return this == NULL;
    }
    
    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    public static <E1, E2> Map<E1, E2> toMap(boolean skipNullValues, Tuple2<E1, E2>...tuples) {
        Map<E1, E2> map= new LinkedHashMap<E1, E2>();
        for (Tuple2<E1, E2> t: tuples) {
          if (!skipNullValues || t.e2!=null) {
            map.put(t.e1, t.e2);
          }
        }
        return map;
    }
    
}
