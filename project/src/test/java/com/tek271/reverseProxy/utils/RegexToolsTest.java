package com.tek271.reverseProxy.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class RegexToolsTest {

  @Test
  public void testFindAll() {
    String pattern= "A+";
    String text= "A-AA-AAA-";
    List<Tuple3<Integer, Integer, String>> found = RegexTools.findAll(pattern, text, true);
    assertEquals(3, found.size());
    List<Tuple3<Integer, Integer, String>> expected= ImmutableList.of(
          Tuple3.tuple3(0, 1, "A"),
          Tuple3.tuple3(2, 4, "AA"),
          Tuple3.tuple3(5, 8, "AAA")
        );
    assertEquals(expected, found);
  }

  @Test
  public void testFindAllWithGroups() {
    String pattern= "\\bhref\\b\\s*=\\s*(\\S*?)[\\s>]";
    String text= "<A href= a.c at >t</a>";
    List<Tuple3<Integer, Integer, String>> found = RegexTools.findAll(pattern, text, true);
    assertEquals(1, found.size());
    List<Tuple3<Integer, Integer, String>> expected= ImmutableList.of(
          Tuple3.tuple3(9, 12, "a.c")
        );
    assertEquals(expected, found);
  }
  
  
}
