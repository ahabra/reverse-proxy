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

import org.apache.commons.lang.builder.*;

public class Tuple3<E1, E2, E3> {
    private static final Tuple3<?, ?, ?> NULL= tuple3(null, null, null);
    
    public final E1 e1;
    public final E2 e2;
    public final E3 e3;

    public Tuple3(E1 e1, E2 e2, E3 e3) {
        this.e1= e1;
        this.e2= e2;
        this.e3= e3;
    }
    
    public static <E1, E2, E3> Tuple3<E1, E2, E3> tuple3(E1 e1, E2 e2, E3 e3) {
        return new Tuple3<E1, E2, E3>(e1, e2, e3);
    }
    
    @SuppressWarnings("unchecked")
    public static <E1, E2, E3> Tuple3<E1, E2, E3> nil() {
        return (Tuple3<E1, E2, E3>) NULL;
    }

    public boolean isNull() {
        return this == NULL;
    }
    
    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    @Override
    public boolean equals(Object obj) {
		if (obj == null) {	return false; }
		if (obj == this) {	return true;  }
		if (obj.getClass() != getClass()) {	return false; }
		
		@SuppressWarnings("unchecked")
		Tuple3<E1, E2, E3> rhs = (Tuple3<E1, E2, E3>) obj;
		return new EqualsBuilder()
				.append(e1, rhs.e1)
				.append(e2, rhs.e2)
				.append(e3, rhs.e3)
				.isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
        	.append(e1)
        	.append(e2)
        	.append(e3)
        	.toHashCode();
    }
    
}
