package org.paradyne.lib;

import java.util.*;
import java.io.*;

public  class CommandLine

{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommandLine.class);
 public static String exec(String cmd) throws Exception
 
 {
  Runtime rt = Runtime.getRuntime();
  Process proc = rt.exec(cmd.toString());
  //StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
  StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
 // errorGobbler.start();
  return outputGobbler.exec();
 }

 
 public static void main(String args[]){
	 try{
		 logger.info(""+CommandLine.exec("getMAC"));
	 }catch (Exception e) {
		// TODO: handle exception
	}
 }
 
}