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

import java.util.regex.Pattern;

public enum RegexFlagsEnum {
  CANON_EQ(Pattern.CANON_EQ),
  CASE_INSENSITIVE(Pattern.CASE_INSENSITIVE),
  COMMENTS(Pattern.COMMENTS),
  DOTALL(Pattern.DOTALL),
  LITERAL(Pattern.LITERAL),
  MULTILINE(Pattern.MULTILINE),
  UNICODE_CASE(Pattern.UNICODE_CASE),
  UNIX_LINES(Pattern.UNIX_LINES);
  
  public final int code;
  
  private RegexFlagsEnum(int code) {
    this.code= code;
  }
  
  public static int addCodes(RegexFlagsEnum... flags) {
    int sum=0;
    for (RegexFlagsEnum flag : flags) {
      sum += flag.code;
    }
    return sum;
  }
  
}
