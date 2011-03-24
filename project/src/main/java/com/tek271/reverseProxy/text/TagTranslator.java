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

import static org.apache.commons.lang.StringUtils.defaultString;
import static org.apache.commons.lang.StringUtils.left;
import static org.apache.commons.lang.StringUtils.substring;
import static org.apache.commons.lang.StringUtils.substringAfter;
import static org.apache.commons.lang.StringUtils.substringBetween;

import java.util.List;
import java.util.regex.Pattern;

import com.google.common.annotations.VisibleForTesting;
import com.tek271.reverseProxy.model.Mapping;
import com.tek271.reverseProxy.utils.*;
import static com.tek271.reverseProxy.utils.TextTools.*;

public class TagTranslator {
  
  private final UrlMapper urlMapper;
  private final Pattern tagPattern;
  private final Pattern attributePattern;
  
  public TagTranslator(Mapping mapping, Pattern tagPattern, Pattern attributePattern) {
    this.urlMapper= new UrlMapper(mapping);
    this.tagPattern= tagPattern;
    this.attributePattern= attributePattern;
  }
  
  public String translate(String text) {
    StringBuilder sb= new StringBuilder();
    List<Tuple3<Integer, Integer, String>> matches = RegexTools.findAll(tagPattern, text, false);

    int lastEnd=0;
    for (Tuple3<Integer, Integer, String> match : matches) {
      sb.append(text.substring(lastEnd, match.e1));
      sb.append( translateTag(match.e3) );
      lastEnd= match.e2;
    }
    
    String remaining = substring(text, lastEnd);
    sb.append(defaultString(remaining));
    return sb.toString();
  }
  
  @VisibleForTesting
  String translateTag(String tag) {
    Tuple3<Integer, Integer, String> value = attributeValue(tag);
    if (value.isNull()) return tag;
    
    String newUrl = urlMapper.mapContentUrl(value.e3);
    return left(tag, value.e1) + dquoteAround(newUrl) + substring(tag, value.e2);
  }
  
  @VisibleForTesting
  Tuple3<Integer, Integer, String> attributeValue(String tag) {
    String normalized= TextTools.removeControlChars(tag);
    List<Tuple3<Integer, Integer, String>> matches = RegexTools.findAll(attributePattern, normalized, true);
    if (matches.isEmpty()) return Tuple3.nil();
    
    Tuple3<Integer, Integer, String> match = matches.get(0);
    String value = match.e3;
    char quote= value.charAt(0);
    if (isQuote(quote)) {
      value= extractBetweenQuotes(value, quote);
    }
    return Tuple3.tuple3(match.e1, match.e2, value);
  }
  
  private static String extractBetweenQuotes(String value, char quote) {
    String q = String.valueOf(quote);
    String v= substringBetween(value, q);
    if (v==null) v= substringAfter(value, q);
    return v;
  }
  
}
