/**** package paralib.report;

import netscape.javascript.*;

public class Report {

	private static common.CommonFrame frame;

	public Report(common.CommonFrame frame) {
		this.frame = frame;
	}
	public static void showReport(String path) {
		common.Trial tr = (common.Trial)frame.getDesktopPane().getTopLevelAncestor();
		JSObject win = JSObject.getWindow(tr.getApplet());
    	String[] param = {path};
    	win.call("ShowReport",param);
	}

} */

package org.paradyne.lib.report;



public class Report {

	

	public Report() {

	}
	
/*
	public void downloadAny(javax.swing.JApplet app, String path) {
		try{
			JSObject win = JSObject.getWindow(app);
	    	String[] param = {path};
//	    	javax.swing.JOptionPane.showMessageDialog(app, ""+path, "Message" , javax.swing.JOptionPane.INFORMATION_MESSAGE);
	    	win.call("DownloadReport",param);
		} catch(Exception e) {}
	}

	public void downloadReport(String path) {
		common.Trial tr =null;
		try{
			tr = (common.Trial)frame.getDesktopPane().getTopLevelAncestor();
			JSObject win = JSObject.getWindow(tr.getApplet());
	    	String[] param = {path};
	    	win.call("DownloadReport", param);
		} catch(Exception e) {}
	}



	public void showReport(String path) {
		common.Trial tr =null;
		try{
			tr = (common.Trial)frame.getDesktopPane().getTopLevelAncestor();
		} catch(Exception e) {}

		if(tr.getApplet()==null){
			try{
			Runtime r = Runtime.getRuntime();
			String[] cmd = new String[2];
			cmd[0] = "explorer";
			cmd[1] = "\""+path+"\"";
			Process p = r.exec(cmd);
			} catch(Exception ex) {
//				javax.swing.JOptionPane.showMessageDialog(null, "here"+ex, "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JSObject win = JSObject.getWindow(tr.getApplet());
	    	String[] param = {path};
	    	win.call("ShowReport", param);
    	}
	}

	public void showReport(javax.swing.JApplet applet, String path) {
		if(applet==null) {
			try{
				Runtime r = Runtime.getRuntime();
				String[] cmd = new String[2];
				cmd[0] = "explorer";
				cmd[1] = path;
//				javax.swing.JOptionPane.showMessageDialog(null, "runtime.exec", "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);
				Process p = r.exec(cmd);
//				javax.swing.JOptionPane.showMessageDialog(null, "runtime.exec", "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);

			} catch(Exception ex) {
				javax.swing.JOptionPane.showMessageDialog(null, "here"+ex, "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		} else {
//			javax.swing.JOptionPane.showMessageDialog(null, "apllet is null", "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);
			JSObject win = JSObject.getWindow(applet);
	    	String[] param = {path};
	    	win.call("ShowReport", param);
	    	javax.swing.JOptionPane.showMessageDialog(null, "apllet is null", "Alert !", javax.swing.JOptionPane.ERROR_MESSAGE);
    	}
	}*/
	public void showReport(String path) {
		
		try{	
		Runtime r = Runtime.getRuntime();
			String[] cmd = new String[2];
			cmd[0] = "explorer";
			cmd[1] = "\""+path+"\"";
			Process p = r.exec(cmd);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}