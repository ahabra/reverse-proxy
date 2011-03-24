package com.tek271.reverseProxy.text;

import static org.junit.Assert.*;

import java.util.regex.*;

import org.junit.Test;

import com.tek271.reverseProxy.model.ModelFactory;

public class TextTranslatorTest {
  private TextTranslator target= new TextTranslator(ModelFactory.MAPPING1);

  @Test public void testRegEx() {
    String regex= "\\bhref\\b\\s*=\\s*(\\S*?)[\\s>]";
    String text= "<A href= a.c at >t</a>";
    Pattern pattern= Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    matcher.find();
    String found = matcher.group(1);
    assertEquals("a.c", found);
  }
  
  
  @Test
  public void testTranslate() {
    assertEquals("", target.translate(""));
    assertEquals("", target.translate(null));
    assertEquals("hello", target.translate("hello"));
    assertEquals("<a href=\"\">click</a>", target.translate("<a href=''>click</a>"));
    assertEquals("<a href=\"yahoo\">click</a>", target.translate("<a href='yahoo'>click</a>"));
    assertEquals("<a href=\"/rp/maps/abc\">click</a>", target.translate("<a href='/abc'>click</a>"));
    assertEquals("<a at= 'ab' href=\"/rp/maps/abc\">click</a>", target.translate("<a at= 'ab' href='/abc'>click</a>"));
    assertEquals("<a href=\"/rp/maps/abc\"  >click</a>", target.translate("<a href='/abc'  >click</a>"));
    assertEquals("hello<a href=\"/rp/maps/abc\">click</a>", target.translate("hello<a href='/abc'>click</a>"));
    assertEquals("hello<a href=\"/rp/maps/abc\">click</a>", target.translate("hello<a href=\"/abc\">click</a>"));
    assertEquals("hello<a href=\"/rp/maps/abc\">click</a>", target.translate("hello<a href=/abc>click</a>"));
  }

}
