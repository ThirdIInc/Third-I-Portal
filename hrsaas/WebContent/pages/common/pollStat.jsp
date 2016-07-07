<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<s:form action="HomePage" id="paraFrm"  name="HomeForm" theme="simple">
<TABLE  width="100%" class="formbg" >

	<tr>
		<td><s:if test="backFlag">
		<a align="right" href="javascript:back('HomePage_prevPoll.action','P');">Back</a>
		</s:if>
		</td>
	</tr>
	
	<tr>
		<td  width="100%" colspan="1">
			<table class="formbg" width="100%">
				<tr class="formbg">
					<td  width="100%" colspan="1">
						<center><B>POLL STATISTICS</B></center>
					</td>
				</tr>
			</table>
		</td>
	</tr>


	<%
	String dashletId=(String)request.getAttribute("dashletId");
	String dashletWidth=(String)request.getAttribute("dashletWidth");
	try{
		Object[][]pollStatList = (Object[][]) request
		.getAttribute("pollStatList");
				
		%>


<tr>
	<td>
		<table width="100%" class="formbg">
			<tr>
		<td width="100%" colspan="1">
		<B>GenderWise Statistics</B>
		</td>
	</tr>
	<tr>
	<td  width="100%" >
	 <%
	 String strGenderXML="";    
	 try{
		 strGenderXML = "<chart xAxisName='Gender' showBorder='0'  canvasBgColor='FFFFFF' bgColor='FFFFFF' canvasBgAlpha='0'  bgAlpha='0' yAxisName='No of votes(percentage)' showValues='0' decimals='0' formatNumberScale='0' chartRightMargin='30'>";
	
	   //Close <chart> element
	  
	    for (int k = 0; k < pollStatList.length; k++) {
		   //Convert data to XML and append
			strGenderXML = strGenderXML + "<set label='"+String.valueOf(pollStatList[k][0])+"' value='"+String.valueOf(pollStatList[k][2])+"'/>";
	   }
  	 }catch(Exception e){
	   
	 }
  	strGenderXML = strGenderXML + "</chart>";
  	 String chartCodeGender=""; 
     System.out.println("in createChart strXML======="+strGenderXML);
     chartCodeGender=createChartHTML("../pages/charts/fusionCharts/Bar2D.swf",    "", strGenderXML, "productSales", "500", 120, false);
     System.out.println("in createChart result"+chartCodeGender);
  	%><%=chartCodeGender%>

<%}catch(Exception e){
}	%>
</td>
</tr>
		</table>
	</td>
</tr>

<tr>
	<td>
		<table width="100%" class="formbg">
			<tr>
		<td width="100%" colspan="1">
		<B>DepartmentWise Statistics</B>
		</td>
	</tr>
	<tr>
		<td>
			<%Object[][] pollDeptStat = (Object[][]) request.getAttribute("pollDeptStat");
				Object[][] axisName = (Object[][]) request.getAttribute("axisName");	
			%> <%
			 
   //Now, we need to convert this data into XML. We convert using string concatenation.
   String strXML="";
   
   //Initialize <chart> element
   try{
   strXML = "<chart xAxisName='Department' yAxisName='No of votes(percentage)' bgAlpha='0' canvasBgAlpha ='0' canvasBaseColor='#FFFFFF' showCanvasBase='false' showBorder='false'  showCanvasBg='false' showValues='0' decimals='0' formatNumberScale='0'>";

   //Close <chart> element
  
    for (int k = 0; k < pollDeptStat.length; k++) {
	   //Convert data to XML and append
		strXML = strXML + "<set label='"+String.valueOf(pollDeptStat[k][1])+"' value='"+String.valueOf(pollDeptStat[k][2])+"'/>";
   }
   }catch(Exception e){
	   
	}
   strXML = strXML + "</chart>";
   
   //Create the chart - Column 3D Chart with data contained in strXML
   String chartCode=""; 
   System.out.println("in createChart strXML======="+strXML);
   chartCode=createChartHTML("../pages/charts/fusionCharts/Column3D.swf",    "", strXML, "productSales", "500", 250, false);
   System.out.println("in createChart result"+chartCode);
   %> <%=chartCode%>					
										
					
		</td>												

	</tr>
		</table>
	</td>
</tr>

</table>

	
	
<s:hidden name="prevPollCode"
			value="%{prevPollCode}" />
			<s:hidden name="clickType" value="S" ></s:hidden>
			<s:hidden name="pollCode"
			value="%{pollCode}" />


</s:form>
<script>
function back(name,type){
	//alert(document.getElementById("paraFrm_prevPollCode").value);
	window.open('','window','top=260,left=150,width=500,height=400,scrollbars=yes,status=yes,resizable=yes');
	document.getElementById("paraFrm_prevPollCode").value=document.getElementById("paraFrm_prevPollCode").value;
	document.getElementById("paraFrm").target="window";
	document.getElementById("paraFrm").action=name;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}
</script>




