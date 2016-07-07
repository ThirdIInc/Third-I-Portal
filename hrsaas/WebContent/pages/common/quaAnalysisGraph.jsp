<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="formbg">
	<tr>
		<td align="center">
		<%Object[][] ratingData = (Object[][]) request.getAttribute("analysisData");
		String dashletId=(String)request.getAttribute("dashletId");
		String dashletWidth=(String)request.getAttribute("dashletWidth");
		   System.out.println("dashletWidth==qua="+dashletWidth);
		     System.out.println("dashletId=qua=="+dashletId);
		%> <%
			 
   //Now, we need to convert this data into XML. We convert using string concatenation.
   String strXML="";
   
   //Initialize <chart> element
   try{
   strXML = "<chart palette='4' showBorder='false' decimals='0' enableSmartLabels='1' enableRotation='0' bgColor='#FFFFFF' bgAlpha='0'  showBorder='1' startingAngle='150'>";

   //Close <chart> element
  
    for (int k = 0; k < ratingData.length; k++) {
	   //Convert data to XML and append
	if(k==0 ||k==1){
		strXML = strXML + "<set label='"+String.valueOf(ratingData[k][0])+"' value='"+String.valueOf(ratingData[k][1])+"' isSliced='1'/>";
	}
	else{
		strXML = strXML + "<set label='"+String.valueOf(ratingData[k][0])+"' value='"+String.valueOf(ratingData[k][1])+"'/>";		
	}
      
   }
   }catch(Exception e){
	   
	}
   strXML = strXML + "</chart>";
   String chartCode=""; 
   System.out.println("in createChart strXML======="+strXML);
   chartCode=createChartHTML("../pages/charts/fusionCharts/Pie3D.swf",    "", strXML, "productSales", dashletWidth, 180, false);
   System.out.println("in createChart result"+chartCode);
   %> <%=chartCode%> </td>

	</tr>

</table>
