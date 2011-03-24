package com.tek271.reverseProxy.text;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

import com.tek271.reverseProxy.model.ModelFactory;

public class TagTranslatorTest {
  // Will find all <a .*> tags
  private static final String ANCHORE_REGEX= "<[aA]\\b(\"[^\"]*\"|'[^']*'|[^'\">])*>";  // match <a .*> tags
  private static final Pattern ANCHORE_PATTERN= Pattern.compile(ANCHORE_REGEX);
  
  private static final String HREF_REGEX= "\\bhref\\b\\s*=\\s*(\\S*?)[\\s>]";  // match href value
  private static final Pattern HREF_PATTERN= Pattern.compile(HREF_REGEX, Pattern.CASE_INSENSITIVE);
  
  TagTranslator target= new TagTranslator(ModelFactory.MAPPING1, ANCHORE_PATTERN, HREF_PATTERN);

  @Test
  public void testTranslateAnchor() {
    assertEquals("", target.translateTag(""));
    assertEquals("<a></a>", target.translateTag("<a></a>"));
    assertEquals("<a/>", target.translateTag("<a/>"));
    assertEquals("<a href></a>", target.translateTag("<a href></a>"));
    assertEquals("<a href=\"\"></a>", target.translateTag("<a href=\"\"></a>"));
    assertEquals("<a href=\"\"></a>", target.translateTag("<a href=''></a>"));
    assertEquals("<a href= \"\" ></a>", target.translateTag("<a href= '' ></a>"));
  }

  @Test public void testAttributeValue() {
    assertEquals("abc", target.attributeValue("<A href='abc'>t</a>").e3);
    assertEquals("abc", target.attributeValue("<A href=\"abc\">t</a>").e3);
    assertEquals("abc", target.attributeValue("<A href= 'abc'  >t</a>").e3);
    assertEquals("abc", target.attributeValue("<a href=abc>t</a>").e3);
    assertEquals("abc", target.attributeValue("<a href=abc>t</a>").e3);
    assertEquals("abc", target.attributeValue("<a href=abc   >t</a>").e3);
    assertEquals("abc", target.attributeValue("<a href='abc>t</a>").e3);
  }
  
  
}
