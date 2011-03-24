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
package com.tek271.reverseProxy.model;

import static org.apache.commons.lang.StringUtils.*;

import java.util.ArrayList;


public class Mappings extends ArrayList<Mapping> {
  
  private static final long serialVersionUID = 1L;


  public static Mappings create(Mapping...mappings) {
    Mappings r= new Mappings();
    for (Mapping mapping: mappings) {
      r.add(mapping);
    }
    return r;
  }
  
  private static final String SEP = "://";

  
  public Mapping findByProxyUrl(String proxyUrl) {
    String noProtocol= substringAfter(proxyUrl, SEP);
    for (Mapping mapping: this) {
      if (startsWithIgnoreCase(noProtocol, mapping.proxy)) return mapping;
    }
    return null;
  }
  
  public Mapping findByHiddenUrl(String hiddenUrl) {
    String noProtocol= substringAfter(hiddenUrl, SEP);
    for (Mapping mapping: this) {
      if (startsWithIgnoreCase(noProtocol, mapping.hiddenDomain)) return mapping;
    }
    return null;
  }
  
  
}
