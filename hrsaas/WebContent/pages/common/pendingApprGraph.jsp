<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="formbg">
	
	
		
		<%String strXML="<chart legendAllowDrag='1' legendPosition='RIGHT' legendBgAlpha='100' legendBgColor ='FFFFFF' legendShadow='0' legendBorderColor='FFFFFF' xAxisName='Department' yAxisName='Pending Appraisal'  bgAlpha='0' shownames='1'  showvalues='0' showSum='1' decimals='0' slantLabels='1' overlapColumns='1' chartLeftMargin='0' chartRightMargin='0' chartBottomMargin ='5' chartTopMargin='10'  canvasBgColor='FFFFFF' showCanvasBg='0' >";
		String dashletId=(String)request.getAttribute("dashletId");
		String dashletWidth=(String)request.getAttribute("dashletWidth");
		try{
			strXML += (String) request.getAttribute("deptApprisalXML");
			
		%> <%
		}catch(Exception e){e.printStackTrace();}
		
		strXML = strXML + "</chart>";
    //Create the chart - Column 3D Chart with data contained in strXML
   String chartCode=""; 
   chartCode=createChartHTML("../pages/charts/fusionCharts/StackedColumn3D.swf?ChartNoDataText=No Appraisal data available",    "", strXML, "myChartId", dashletWidth, 180, false);
   System.out.println("in createChart strXML======="+strXML);
   %>

<tr>   
	<td>
		<div id='pendingGraphDiv' style="display: none;"><%=chartCode%></div>
	</td>
</tr>
	
<tr>   
	<td>
		<div id='pendingPluginDiv' style="display: none;"><a target="blank" href="http://www.macromedia.com/go/getflashplayer"><font color="red">Click here to download the plugins required.</font></a></div>
	</td>
</tr>	
</table>
<script type="text/javascript" defer='defer'> 
	detectFlashPlayerPlugin('pendingGraphDiv','pendingPluginDiv');
</script>

