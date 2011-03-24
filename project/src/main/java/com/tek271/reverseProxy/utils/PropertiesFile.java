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

import java.util.*;

import com.google.common.collect.*;

import static org.apache.commons.lang.StringUtils.*;


public class PropertiesFile {
  private static final String COMMENT = "#";
  private static final String EQ = "=";
  
  private Map<String, String> props;
  private boolean allowInLineComments= false;

  private PropertiesFile() {}
  
  public PropertiesFile(String fileName, boolean allowInLineComments) {
    List<String> lines = FileTools.readLinesFromContext(fileName);
    this.allowInLineComments= allowInLineComments;
    props= parse(lines);
  }
  
  public PropertiesFile(String fileName) {
    this(fileName, false);
  }
  
  public static Map<String, String> read(String fileName) {
    PropertiesFile pf= new PropertiesFile(fileName);
    return pf.props;
  }
  
  private Map<String, String> parse(List<String> lines) {
    Map<String, String> map= new LinkedHashMap<String, String>();
    
    for(String line: lines) {
      Tuple2<String, String> tuple = parseLine(line);
      if (tuple!=null) {
        map.put(tuple.e1, tuple.e2);
      }
    }
    return map;
  }
  
  private Tuple2<String, String> parseLine(String line) {
    line= trim(line);
    if (isEmpty(line)) return null;
    if (line.startsWith(COMMENT)) return null;
    if (allowInLineComments) line= substringBefore(line, COMMENT);
    
    if (! line.contains(EQ)) return null;
    
    String key= trim(substringBefore(line, EQ));
    if (isEmpty(key)) return null;
    
    String value= trim(substringAfter(line, EQ));
    return Tuple2.tuple2(key, value);
  }
  
  public Map<String, String> getProperties() {
    return props;
  }
  
  public String getString(String key) {
    return props.get(key);
  }
  
  public Integer getInteger(String key) {
    String v= props.get(key);
    if (isEmpty(v)) return null;
    return Integer.parseInt(v);
  }
  
  public Long getLong(String key) {
    String v= props.get(key);
    if (isEmpty(v)) return null;
    return Long.parseLong(v);
  }
  
  private static final Set<String> TRUES= ImmutableSet.of("true", "t", "yes", "y", "1");
  
  public Boolean getBoolean(String key) {
    String v= props.get(key);
    if (isEmpty(v)) return null;
    
    v= lowerCase(v);
    return TRUES.contains(v);
  }
  
  /** get a subset of element with the given key prefix */ 
  public PropertiesFile subset(String keyPrefix) {
    PropertiesFile subset= new PropertiesFile();
    subset.props= Maps.newLinkedHashMap();
    subset.allowInLineComments= allowInLineComments;
    for (Map.Entry<String, String> e: props.entrySet()) {
      String k= e.getKey();
      if (startsWith(k, keyPrefix)) subset.props.put(k, e.getValue());
    }
    return subset;
  }
  
  public Set<String> keys() {
    return props.keySet();
  }
  
}
