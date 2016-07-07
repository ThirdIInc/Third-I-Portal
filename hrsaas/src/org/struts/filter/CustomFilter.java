package org.struts.filter;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CustomFilter extends StrutsPrepareAndExecuteFilter {
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
String url = ((HttpServletRequest)req).getRequestURI();
//System.out.println("url inside filter=="+url);
if (url.indexOf("CrystalReportViewerHandler") < 0) {
super.doFilter(req, res, chain);
}/*else if (url.indexOf("CrystalReportViewerHandler") < 0) {
super.doFilter(req, res, chain);
}*/
else{
chain.doFilter(req, res);
}
}
}
