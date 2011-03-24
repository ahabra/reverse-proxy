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

import java.util.List;

import com.google.common.collect.*;
import com.tek271.reverseProxy.model.*;
import static com.tek271.reverseProxy.model.RegexPair.*;

public class TextTranslator {

  private static final RegexPair ANCHOR= pair("<[aA]\\b(\"[^\"]*\"|'[^']*'|[^'\">])*>",  // match <a .*> tags
                                              "\\bhref\\b\\s*=\\s*(\\S*?)[\\s>]");       // match href attribute value
  
  private static final RegexPair IMAGE= pair("<img\\b(\"[^\"]*\"|'[^']*'|[^'\">])*>",    // match <img .*> tags
                                             "\\bsrc\\b\\s*=\\s*(\\S*?)[\\s>]");         // match src attribute value
  
  private static final RegexPair LINK= pair("<link\\b(\"[^\"]*\"|'[^']*'|[^'\">])*>",    // match <link .*> tags
                                            "\\bhref\\b\\s*=\\s*(\\S*?)[\\s>]");         // match href attribute value
  
  private static final RegexPair SCRIPT= pair("<script\\b(\"[^\"]*\"|'[^']*'|[^'\">])*>",    // match <script .*> tags
                                              "\\bsrc\\b\\s*=\\s*(\\S*?)[\\s>]");            // match src attribute value
  
  private static final List<RegexPair> TAGS_PATTERNS= ImmutableList.of(ANCHOR, IMAGE, LINK, SCRIPT);
  
  private final List<TagTranslator> tagTranslators= Lists.newArrayList();
  
  public TextTranslator(Mapping mapping) {
    for (RegexPair pair: TAGS_PATTERNS) {
      tagTranslators.add( new TagTranslator(mapping, pair.tagPattern, pair.attributePattern) );
    }
  }
  
  public String translate(String text) {
    for (TagTranslator translator : tagTranslators) {
      text= translator.translate(text);
    }
    return text;
  }
  
}
