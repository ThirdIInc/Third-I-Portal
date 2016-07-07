<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="formbg">
	
	<%String strXML="<chart  bgAlpha='0' shownames='1' xAxisName='Department' yAxisName='Score'  showvalues='0' showSum='1' decimals='0' slantLabels='1' overlapColumns='1' chartLeftMargin='0' chartRightMargin='0' chartBottomMargin ='5' chartTopMargin='10'  canvasBgColor='FFFFFF' showCanvasBg='0' >";
		String dashletDeptId=(String)request.getAttribute("dashletId");
		String dashletDeptWidth=(String)request.getAttribute("dashletWidth");
		try{
			strXML += (String) request.getAttribute("deptPerformanceXML");
		%> <%
		}catch(Exception e){
			//e.printStackTrace();
			}
		
		strXML = strXML + "</chart>";
    //Create the chart - Column 3D Chart with data contained in strXML
   String chartCode=""; 
   System.out.println("in createChart strXML dept======="+strXML);
   chartCode=createChartHTML("../pages/charts/fusionCharts/Column3D.swf?ChartNoDataText=Appraisal data not available for current calendar",    "", strXML, "myChartId", dashletDeptWidth, 165, false);
   System.out.println("in createChart strXML======="+chartCode);
   %>

	


<tr>   
	<td>
		<div id='chartDeptDiv' style="display: none;"><%=chartCode%></div>
	</td>
</tr>
	
<tr>   
	<td>
		<div id='deptPluginDiv' style="display: none;"><a target="blank" href="http://www.macromedia.com/go/getflashplayer"><font color="red">Click here to download the plugins required.</font></a></div>
	</td>
</tr>	
</table>
<script type="text/javascript" defer='defer'> 
	detectFlashPlayerPlugin('chartDeptDiv','deptPluginDiv');
</script>

