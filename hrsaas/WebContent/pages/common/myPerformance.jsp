<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>

<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr id='myPerformanceDiv' style="display: none;">
		<td colspan="2" align="center">
		<%Object[][] ratingData = (Object[][]) request.getAttribute("ratingData");
		String finalScore = (String) request.getAttribute("finalScore");
		String dashId = (String) request.getAttribute("dashId");
		String dashletWidth = (String) request.getAttribute("dashletWidth");
		Object minMaxObj [][]= (Object[][]) request.getAttribute("minMaxRating");
		String dashletWidthActual="";
		%> <%
			  //Object[][] arrData=null;
			  
			 // arrData=(Object[][])request.getAttribute("department");
  

   //Now, we need to convert this data into XML. We convert using string concatenation.
   String strXML="";
   
   //Initialize <chart> element
   try{
	   
	   strXML = "<chart adjustTM='1' upperLimit='"+String.valueOf(minMaxObj[0][0])+"' lowerLimit='"+String.valueOf(minMaxObj[0][1])+"'  defaultAnimation='1' bgAlpha='0' autoScale='0'  bgAlpha='0' bgColor='FFFFFF' showBorder='0' chartTopMargin='10' toolTipBgColor='80A905' gaugeFillMix='{dark-10},FFFFFF,{dark-10}' gaugeFillRatio='3'> <colorRange>"
   			+"<color minValue='0'  maxValue='"+String.valueOf(ratingData[0][1])+"' code='FF654F'/>"
   			+"<color minValue='"+String.valueOf(ratingData[0][1])+"'  maxValue='"+String.valueOf(ratingData[ratingData.length-2][1])+"' code='F6BD0F'/>"
   			+"<color minValue='"+String.valueOf(ratingData[ratingData.length-2][1])+"'  maxValue='"+String.valueOf(ratingData[ratingData.length-1][1])+"' code='8BBA00'/> </colorRange>"
   			+"<dials>"
   	     	+"<dial value='"+finalScore+"' rearExtension='10'/>"
   	   		+"</dials> <trendpoints>";

   //Close <chart> element
  
    for (int k = 0; k < ratingData.length; k++) {
	   //Convert data to XML and append
	
      strXML = strXML + "<point value='"+String.valueOf(ratingData[k][1])+"' displayValue='"+String.valueOf(ratingData[k][2])+"' fontcolor='000000' useMarker='1' dashed='1' dashLen='2' dashGap='2' valueInside='1' />";
   }
   
   strXML = strXML + " </trendpoints>"
  	+"<styles>"
	+"<definition>"
   +"<style name='RectShadow' type='shadow' strength='2'/>"
	+"</definition>"
	+"<application>"
   +"<apply toObject='Grp1' styles='RectShadow' />"
	+"</application>"
	+"</styles></chart>";
   }catch(Exception e){
	   e.printStackTrace();
	   
   }
   try{
   dashletWidthActual=String.valueOf(Double.parseDouble(dashletWidth)*.60);
   }catch(Exception e){
	   dashletWidthActual ="420";
   }
   //Create the chart - Column 3D Chart with data contained in strXML
   String chartCode=""; 
   System.out.println("dashletWidth======="+dashletWidth);
   System.out.println("in createChart strXML123======="+strXML);
   System.out.println("in createChart result"+chartCode);
   if(!strXML.equals(""))
   chartCode=createChartHTML("../pages/charts/fusionWidgets/AngularGauge.swf",    "", strXML, "productSales", dashletWidthActual, 190, false);
   System.out.println("in createChart result"+chartCode);
   %> <%=chartCode%></td>
<td colspan="2"><b>Rating Description :</b>
<table>
<tbody>
<%
	for(int x=0;x<ratingData.length;x++){%>
		<TR><td>
		<font class="formtext"><%=""+ratingData[x][2]+" = "+ratingData[x][3]%></font><BR>
		</td></TR>
	<%}%>
	</tbody>
	</table>
</td>
	</tr>
	
<tr valign="bottom" id='myPerfPluginDiv' style="display: none;">   
	<td colspan="2">
		<div><a target="blank" href="http://www.macromedia.com/go/getflashplayer"><font color="red">Click here to download the plugins required.</font></a></div>
	</td>
</tr>
</table>
<script type="text/javascript" defer="defer">	
	detectFlashPlayerPlugin('myPerformanceDiv','myPerfPluginDiv');
</script>


