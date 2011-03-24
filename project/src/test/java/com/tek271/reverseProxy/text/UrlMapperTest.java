package com.tek271.reverseProxy.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tek271.reverseProxy.model.ModelFactory;

public class UrlMapperTest {
  private UrlMapper target= new UrlMapper(ModelFactory.MAPPING1);
  
  
  @Test
    public void testMapContentUrlUrl() {
      assertEquals("http://a.b", target.mapContentUrl("http://a.b"));
      String prefix = ModelFactory.MAPPING1.proxyResource;  // /rp/maps
      assertEquals(prefix + "/home", target.mapContentUrl("/home"));
    }
  

}
