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

import java.io.*;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class FileTools {
  
  public static InputStream readFromContextToInputStream(String fileName) {
    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    if (inputStream == null) {
      throw new IllegalArgumentException("Cannot find " + fileName);
    }
    return inputStream;
  }

  public static String readTextFileFromContext(String fileName) {
    InputStream stream = readFromContextToInputStream(fileName);
    return toString(stream);
  }

  static String toString(InputStream stream) {
    try {
      return IOUtils.toString(stream);
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot read stream", e);
    }
  }

  public static List<String> readLinesFromContext(String fileName) {
    InputStream is = readFromContextToInputStream(fileName);
    try {
      return IOUtils.readLines(is);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot read file: " + fileName, e);
    }
  }
  
  public static long size(File file) {
    if (file == null) {
      return 0;
    }
    return file.length();
  }

  public static String readTextFileContent(File file) {
    if (file == null ) return "";
    
    String text;
    try {
      text = FileUtils.readFileToString(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read file's text.", e);
    }
    return StringUtils.defaultString(text);
  }

}
