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
package com.tek271.reverseProxy.servlet;

import javax.servlet.ServletResponse;

import org.apache.http.HttpEntity;

import com.tek271.reverseProxy.model.Mapping;
import com.tek271.reverseProxy.text.*;

public class ContentTranslator {

  private final Mapping mapping;
  private final String newUrl;
  
  public ContentTranslator(Mapping mapping, String newUrl) {
    this.mapping= mapping;
    this.newUrl= newUrl;
  }
  
  public void translate(HttpEntity entity, ServletResponse response) {
    if (entity==null) return;
    
    ContentType contentType= new ContentType(entity.getContentType(), newUrl);
    if (contentType.isBinary) {
      ContentUtils.copyBinary(entity, response);
      return;
    }
    String text = ContentUtils.getContentText(entity, contentType.charset);
    if (contentType.isJavaScript) {
      ContentUtils.copyText(text, response, contentType);
      return;
    }
    
    text= translateText(text);
    ContentUtils.copyText(text, response, contentType);
  }
 
  private String translateText(String text) {
    TextTranslator textTranslator= new TextTranslator(mapping);
    return textTranslator.translate(text);
  }

  
}
