package com.tek271.reverseProxy.config;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tek271.reverseProxy.model.Mappings;
import com.tek271.reverseProxy.utils.PropertiesFile;

public class PropertiesTest {
  private static final String FILE= "reverseProxy.test.properties";

  @Test
  public void testReadMappings() {
    PropertiesFile propertiesFile= new PropertiesFile(FILE, true);
    Mappings mappings = Properties.readMappings(propertiesFile);
    assertEquals(5, mappings.size());
    assertEquals("maps.google.com", mappings.get(0).hiddenDomain);
  }

}
