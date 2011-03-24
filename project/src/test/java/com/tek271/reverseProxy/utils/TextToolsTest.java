package com.tek271.reverseProxy.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextToolsTest {

  @Test public void testRemoveControlChars() {
      assertEquals("", TextTools.removeControlChars(null));
      assertEquals("", TextTools.removeControlChars(""));
      assertEquals("  ", TextTools.removeControlChars("  "));
      assertEquals("    ", TextTools.removeControlChars(" \t\n "));
    }
  

}
