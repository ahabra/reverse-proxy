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
package com.tek271.reverseProxy.servlet;

import static org.apache.commons.lang.StringUtils.*;
import org.apache.http.Header;

public class ContentType {
  private static final String DEFAULT_CHARSET= "UTF-8";
  
  public final String value;
  public final String charset;
  public final boolean isText;
  public final boolean isBinary;
  public final boolean isJavaScript;
  public final String url;
  
  
  public ContentType(Header header, String url) {
    value= trimToEmpty( header.getValue() );
    charset= extractCharset(value);
    isText= isText(value);
    isBinary= !isText;
    this.url= url;
    
    String path= extractPath(url);
    isJavaScript= isJavaScript(path);
  }
  
  private static String extractCharset(String value) {
    value= lowerCase( trimToEmpty(value));
    String cs= substringAfter(value, "charset=");
    if (isEmpty(cs)) return DEFAULT_CHARSET;
    
    cs= trimToEmpty(cs);
    cs= trim(substringBefore(cs, ";"));
    if (isEmpty(cs)) cs= DEFAULT_CHARSET;
    return cs;
  }
  
  private static boolean isText(String value) {
    if (containsIgnoreCase(value, "image")) return false;
    return true;
  }
  
  private static boolean isJavaScript(String path) {
    return endsWithIgnoreCase(path, ".js");
  }
  
  private static String extractPath(String url) {
    return substringBefore(url, "?");
  }
  
}
