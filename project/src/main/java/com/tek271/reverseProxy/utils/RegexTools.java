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

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class RegexTools {

  /** Find all matches in a string */
  public static List<Tuple3<Integer, Integer, String>> findAll(Pattern pattern, String text, boolean matchGroups) {
    if (StringUtils.isEmpty(text)) return Collections.emptyList();
    
    Matcher matcher = pattern.matcher(text);
    List<Tuple3<Integer, Integer, String>> r= Lists.newArrayList();
    while (matcher.find()) {
      int groupCount = matcher.groupCount();
      if (groupCount==0 || !matchGroups) {
        r.add(getMatch(matcher, 0));
      } else {
        for (int i=1; i<=groupCount; i++) {
          r.add(getMatch(matcher, i));
        }
      }
    }
    return r;
  }
  
  private static Tuple3<Integer, Integer, String> getMatch(Matcher matcher, int groupIndex) {
    return Tuple3.tuple3(matcher.start(groupIndex), matcher.end(groupIndex), matcher.group(groupIndex));
  }
  
  public static List<Tuple3<Integer, Integer, String>> findAll(String pattern, String text, boolean matchGroups, RegexFlagsEnum... flags) {
    int codes= RegexFlagsEnum.addCodes(flags);
    Pattern pat= Pattern.compile(pattern, codes);
    return findAll(pat, text, matchGroups);
  }
  
}
