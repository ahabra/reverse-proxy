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

import java.util.regex.Pattern;

import com.google.common.base.Objects;

public class RegexPair {

  public final Pattern tagPattern;
  public final Pattern attributePattern;
  
  private final String tagRegex;
  private final String attributeRegex;
  
  private RegexPair(String tagRegex, String attributeRegex) {
    tagPattern= Pattern.compile(tagRegex, Pattern.CASE_INSENSITIVE);
    attributePattern= Pattern.compile(attributeRegex, Pattern.CASE_INSENSITIVE);
    
    this.tagRegex= tagRegex;
    this.attributeRegex= attributeRegex;
  }
  
  public static RegexPair pair(String tagRegex, String attributeRegex) {
    return new RegexPair(tagRegex, attributeRegex);
  }
  
  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("tagRegex", tagRegex)
        .add("attributeRegex", attributeRegex)
        .toString();
  }
}
