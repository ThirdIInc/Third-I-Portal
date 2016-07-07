<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="formbg">
		<tr>
<td width='100%' align="center">
<div id='manpowerStatusDiv'></div>
</td></tr>
	<tr>
		<td align="center">
		<%Object[][] distributionData = (Object[][]) request.getAttribute("distributionData");
		Object[][] axisName = (Object[][]) request.getAttribute("axisName");
		String dashletId=(String)request.getAttribute("dashletId");
		String dashletWidth=(String)request.getAttribute("dashletWidth");
		%> <%
			 
   //Now, we need to convert this data into XML. We convert using string concatenation.
   String strXML="";
   
   //Initialize <chart> element
   try{
   strXML = "<chart caption='Manpower Distribution Analysis' bgAlpha='0' canvasBgAlpha ='0' canvasBaseColor='#FFFFFF' showCanvasBase='false' showBorder='false'  showCanvasBg='false'   xAxisName='"+String.valueOf(axisName[0][0])+"' yAxisName='No of Employees' showValues='0' decimals='0' formatNumberScale='0'>";

   //Close <chart> element
  
    for (int k = 0; k < distributionData.length; k++) {
	   //Convert data to XML and append
	if(String.valueOf(distributionData[k][4]).equals("3")){
		strXML = strXML + "<set label='"+String.valueOf(distributionData[k][1])+"' value='"+String.valueOf(distributionData[k][2])+"'/>";
	}
	else{
		strXML = strXML + "<set label='"+String.valueOf(distributionData[k][1])+"' value='"+String.valueOf(distributionData[k][2])+"' link='JavaScript:myJS("+String.valueOf(distributionData[k][0])+","+String.valueOf(distributionData[k][3])+","+String.valueOf(distributionData[k][4])+");'/>";		
	}
      
   }
   }catch(Exception e){
	   
	}
   strXML = strXML + "</chart>";
     System.out.println("strXML==="+strXML);
     System.out.println("dashletWidth==manpower="+dashletWidth);
     System.out.println("dashletId=manpower=="+dashletId);
     String chartCode=""; 
     System.out.println("in createChart strXML======="+strXML);
     chartCode=createChartHTML("../pages/charts/fusionCharts/Column3D.swf",    "", strXML, "productSales", dashletWidth, 180, false);
     System.out.println("in createChart result"+chartCode);
     %> <%=chartCode%> </td>

	</tr>

</table>

<script defer="defer">
var width=<%=dashletWidth%>;
var xmlData='';

	function myJS(currentId,previousId,graph){
            //window.alert("currentId"+currentId+"previousId"+previousId+"graph"+graph);
            var url = '<%=request.getContextPath()%>/common/RecruitmentHome_getManPowerAnalysis.action?currentId=' +
			currentId + '&previousId=' + previousId + 
			'&graph=' + graph+'&dashId=' + <%=dashletId%>+'&dashWidth=' + width;
			getReloadURL(<%=dashletId%>, url);	
     } //end of myJS function     
</script>

