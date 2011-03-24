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
package com.tek271.reverseProxy.config;

import com.google.common.annotations.VisibleForTesting;
import com.tek271.reverseProxy.model.*;
import com.tek271.reverseProxy.utils.PropertiesFile;


public class Properties {
  private static final String FILE= "reverseProxy.properties";
  public final Mappings mappings;
  
  public Properties() {
    PropertiesFile propertiesFile= new PropertiesFile(FILE, true);
    mappings= readMappings(propertiesFile);
  }
  
  @VisibleForTesting
  static Mappings readMappings(PropertiesFile propertiesFile) {
    Mappings mappings= new Mappings();
    PropertiesFile subset = propertiesFile.subset("mapping.");
    
    for(String key: subset.keys()) {
      String value = subset.getString(key);
      mappings.add( new Mapping(value) );
    }
    
    return mappings;
  }
  

}
