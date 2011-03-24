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
package com.tek271.reverseProxy.text;

import static org.apache.commons.lang.StringUtils.*;

import com.tek271.reverseProxy.config.Config;
import com.tek271.reverseProxy.model.Mapping;
import com.tek271.reverseProxy.utils.Tuple2;

public class UrlMapper {

  private Mapping currentMapping;
  
  public UrlMapper(Mapping currentMapping) {
    this.currentMapping= currentMapping;
  }
  
  public String mapContentUrl(String url) {
    if (isEmpty(url)) return EMPTY;
    if (startsWith(url, "/")) {
      url = currentMapping.proxyResource + url;
    }
    url= mapFullUrlIfCan(url);
    return url;
  }
  
  private static String mapFullUrlIfCan(String url) {
    if (! isFullUrl(url)) return url;
    Tuple2<Mapping, String> mapped = mapFullUrlHiddenToProxy(url);
    if (mapped.isNull()) return url;
    return mapped.e2;
  }
  
  private static Tuple2<Mapping, String> mapFullUrlHiddenToProxy(String fullHiddenUrl) {
    Mapping mapping= Config.singleton.mappings.findByHiddenUrl(fullHiddenUrl);
    if (mapping==null) return Tuple2.nil();
    
    String newUrl= mapping.mapHiddenToProxy(fullHiddenUrl);
    return Tuple2.tuple2(mapping, newUrl);
  }
  
  
  public static Tuple2<Mapping, String> mapFullUrlProxyToHidden(String fullProxyUrl) {
    Mapping mapping= Config.singleton.mappings.findByProxyUrl(fullProxyUrl);
    if (mapping==null) return Tuple2.nil();
    
    String newUrl= mapping.mapProxyToHidden(fullProxyUrl);
    return Tuple2.tuple2(mapping, newUrl);
  }
  
  private static boolean isFullUrl(String url) {
    return startsWithIgnoreCase(url, "http://");
  }
  
  
}
