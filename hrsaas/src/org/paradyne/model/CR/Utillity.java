package org.paradyne.model.CR;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sun.misc.BASE64Encoder;
public class Utillity {

	public static String post(String urlLocation, String postMessage,
	   String requestContentType, String requestMethod,
	   Map<String, String> requestHeaders) throws IOException {
	  String response = null;
	/*
	  System.out.println("-----" + urlLocation + "-----" + postMessage
	    + "----" + requestContentType + "-----" + requestMethod
	    + "-----" + requestHeaders + "-----00000000000000000");
	*/
	  if (urlLocation != null) {
	   BufferedReader reader = null;
	   OutputStreamWriter writer = null;
	   HttpURLConnection connection = null;

	   try {
	    BASE64Encoder encoder = new BASE64Encoder();
	    // Enter your basic authentication userid,pwd here
	    String password = "uid:pwd"; 
	    String authString = "Basic "
	      + encoder.encode(password.getBytes());

	    URL url = new URL(urlLocation);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setConnectTimeout(180000);
	    connection.setReadTimeout(180000);
	    connection.setDoOutput(true);
	    connection.setRequestMethod(requestMethod);
	    connection.setRequestProperty("Authorization", authString);

	    if (requestContentType != null) {
	     connection.setRequestProperty("Content-Type",
	       requestContentType);
	    }

	    if (requestHeaders != null) {
	     for (String key : requestHeaders.keySet()) {
	      connection.setRequestProperty(key, requestHeaders
	        .get(key));
	     }
	    }

	    if (postMessage != null) {
	     connection.setRequestProperty("Content-Length", ""
	       + postMessage.length());

	     try {
	      writer = new OutputStreamWriter(connection
	        .getOutputStream());
	     } catch (Exception use) {
	      use.getMessage();
	     } 

	     writer.write(postMessage);
	     writer.flush();
	    }

	    StringBuilder sb = new StringBuilder();
	    String line = null;

	    try {
	     reader = new BufferedReader(new InputStreamReader(
	       connection.getInputStream()));
	    } catch (IOException e) {
	     InputStream errorIp = connection.getErrorStream();
	     if (errorIp != null) {
	      BufferedReader errorReader = new BufferedReader(
	        new InputStreamReader(errorIp));
	      while ((line = errorReader.readLine()) != null) {
	       sb.append(line);
	      }
	      System.out.println("Got Error Response: " + sb.toString());
	     }     
	     throw e;
	    }

	    while ((line = reader.readLine()) != null) {
	     sb.append(line);
	    }
	    response = sb.toString();
	    System.out.println("response--" + response);
	   } finally {
	    try {
	     if (writer != null) {
	      writer.close();
	     }
	     if (reader != null) {
	      reader.close();
	     }
	     if (connection != null) {
	      connection.disconnect();
	     }
	    } catch (Exception e) {
	    }
	   }
	  }
	  if (response != null) {
	   // Removes all special characters.
	   response = new String(response.getBytes());
	  }
	  return response;
	 }
}
