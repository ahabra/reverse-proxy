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

import org.apache.commons.lang.StringUtils;

public class TextTools {

  public static String dquoteAround(String value) {
    return '"' + value + '"';
  }
  
  public static boolean isQuote(char quote) {
    return quote=='\'' || quote=='"';
  }
  
 /** remove chars less than 32 and replace with space */ 
  public static String removeControlChars(String text) {
    StringBuilder sb= new StringBuilder();
    for (int i=0, n=StringUtils.length(text) ; i<n; i++) {
      char ch= text.charAt(i);
      if (ch < ' ') ch= ' ';
      sb.append(ch);
    }
    return sb.toString();
  }
  
}
