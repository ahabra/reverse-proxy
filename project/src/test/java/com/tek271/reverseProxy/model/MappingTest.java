package com.tek271.reverseProxy.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MappingTest {

  @Test
  public void constructorShouldParseInput() {
    Mapping mapping = new Mapping("maps.google.com, localhost:8080, /rp/maps");
    assertEquals("maps.google.com", mapping.hiddenDomain);
    assertEquals("localhost:8080", mapping.proxyDomain);
    assertEquals("/rp/maps", mapping.proxyResource);
  }

  @SuppressWarnings("unused")
  @Test(expected=IllegalArgumentException.class)
  public void constructorShouldFailIfNoInput() {
    new Mapping("");
  }

  @SuppressWarnings("unused")
  @Test(expected=IllegalArgumentException.class)
  public void constructorShouldFailIfInvalid() {
    new Mapping("maps.google.com, localhost:8080");
  }
 
  @Test
  public void testMapProxyToHidden() {
    String proxyUrl= "http://localhost:8080/rp/maps/home";
    String actual= ModelFactory.MAPPING1.mapProxyToHidden(proxyUrl);
    String expected= "http://maps.google.com/home";
    assertEquals(expected, actual);
  }
  
  @Test
  public void testMapHiddenToProxy() {
    String hiddenUrl= "http://maps.google.com/home";
    String actual= ModelFactory.MAPPING1.mapHiddenToProxy(hiddenUrl);
    String expected= "http://localhost:8080/rp/maps/home";
    assertEquals(expected, actual);
  }
  
}
