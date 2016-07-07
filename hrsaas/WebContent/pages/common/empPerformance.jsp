<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF" class="formbg">
	<tr>
	<td>
	<table>
	<tr>
	 <td width="17%" colspan="1">
	  Appraisal Code <font color="red">*</font>: 
	 </td>
	 <td width="30%" colspan="1">
	 <s:textfield name="apprCode" id="paraFrm_apprCode" theme="simple" size="30" maxlength="50" readonly="true" />
	  <s:if test="%{generalFlag}"></s:if>
	<s:else><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
			onclick="callAppraisalCode();">
	</s:else>
	
	 </td>
	 <td width="17%" >
	  Employee <font color="red">*</font>:
	 </td>
	 <td width="35%">
	  <!-- 
	  	<s:textfield  name="token" id="paraFrm_token" theme="simple" size="3" maxlength="15" readonly="true" />
	   --> 
	   <s:hidden  name="token" id="paraFrm_token" theme="simple"  />
	    <s:textfield  name="empName" id="paraFrm_empName"  theme="simple" size="30" maxlength="105" readonly="true" />
	   <s:if test="%{generalFlag}"></s:if>	<s:else>	  
	  <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
						onclick="getEmpCode();">
			
			</s:else>		
			
						
	  
	  <input type="button"	value=" Go " class="token" onclick="searchEmpPerformance()" />
	  
	 </td> 
	</tr>	
	
	</table></td></tr>
	<tr>
	<td><table>
	<tr id='empPerformanceDiv' >
		<td colspan="2" align="center">
		<%String strXML="";String finalScore="";
		String dashletId=(String)request.getAttribute("dashletId");
		String dashletWidth=(String)request.getAttribute("dashletWidth");
		String displayDash=(String)request.getAttribute("finalScore");
		Object[][] ratingData =null;
		String dashletWidthActual="";
		Object minMaxObj [][]= (Object[][]) request.getAttribute("minMaxRating");
		try{
		ratingData = (Object[][]) request.getAttribute("ratingData");
		 finalScore = (String) request.getAttribute("finalScore");
		if(finalScore.equals("")||finalScore.equals("null")||finalScore==null){
			finalScore ="";
			displayDash ="false";
		}
		System.out.println("dashletWidth======="+dashletWidth);
		try{
			   dashletWidthActual=String.valueOf(Double.parseDouble(dashletWidth)*.60);
			   }catch(Exception e){
				   dashletWidthActual ="420";
			   }
		 System.out.println("finalScore======="+finalScore);
		%> <%
			  //Object[][] arrData=null;
			  
			 // arrData=(Object[][])request.getAttribute("department");
  

   //Now, we need to convert this data into XML. We convert using string concatenation.
   
   
   //Initialize <chart> element
   
   strXML = "<chart adjustTM='1' upperLimit='"+String.valueOf(minMaxObj[0][0])+"' lowerLimit='"+String.valueOf(minMaxObj[0][1])+"' defaultAnimation='1' bgAlpha='0' autoScale='0'  bgAlpha='0' bgColor='FFFFFF' showBorder='0' chartTopMargin='10' toolTipBgColor='80A905' gaugeFillMix='{dark-10},FFFFFF,{dark-10}' gaugeFillRatio='3'> <colorRange>"
   			+"<color minValue='0'  maxValue='"+String.valueOf(ratingData[0][1])+"' code='FF654F'/>"
   			+"<color minValue='"+String.valueOf(ratingData[0][1])+"'  maxValue='"+String.valueOf(ratingData[ratingData.length-2][1])+"' code='F6BD0F'/>"
   			+"<color minValue='"+String.valueOf(ratingData[ratingData.length-2][1])+"'  maxValue='"+String.valueOf(ratingData[ratingData.length-1][1])+"' code='8BBA00'/> </colorRange>"
   			+"<dials>"
   	     	+"<dial value='"+finalScore+"' rearExtension='10'/>"
   	   		+"</dials> <trendpoints>";

   //Close <chart> element
  
    for (int k = 0; k < ratingData.length; k++) {
	   //Convert data to XML and append
	
      strXML = strXML + "<point value='"+String.valueOf(ratingData[k][1])+"' displayValue='"+String.valueOf(ratingData[k][2])+"' fontcolor='F6BD0F' useMarker='1' dashed='1' dashGap='2' valueInside='1' />";
   }
   
    strXML = strXML + " </trendpoints>"
    /*strXML = strXML + "<annotations>"
     +"<annotationGroup id='Grp1' showBelow='1' >"
     +"<annotation type='rectangle' x='90' y='0' toX='500' toY='220' radius='10' color='009999,333333'  showBorder='0' />"
  	+"</annotationGroup>"
  	+"</annotations>"
  	+"<styles>"
  	+"<definition>"
     +"<style name='RectShadow' type='shadow' strength='2'/>"
  	+"</definition>"
  	+"<application>"
     +"<apply toObject='Grp1' styles='RectShadow' />"
  	+"</application>"
  	+"</styles>"*/
  	+"</chart>'";
		}catch(Exception e){
			  // e.printStackTrace();
			   
		   }
   //Create the chart - Column 3D Chart with data contained in strXML
   String chartCode=""; 
   
   System.out.println("in createChart strXML123======="+strXML);
  
   chartCode=createChartHTML("../pages/charts/fusionWidgets/AngularGauge.swf?LoadDataErrorText=No appraisal data present",    "", strXML, "productSales", dashletWidthActual, 160, false);
   System.out.println("in createChart result"+chartCode);
   %> <%=chartCode%>
   
   </td>
 <%if(!finalScore.equals("")){%>
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
<% }%>
</tr>

<tr valign="bottom" id='empPerfPluginDiv' style="display: none;">   
	<td colspan="2">
		<div><a target="blank" href="http://www.macromedia.com/go/getflashplayer"><font color="red">Click here to download the plugins required.</font></a></div>
	</td>
</tr>
</table></td></tr>




</table>
 <s:hidden name="apprId" id="paraFrm_apprId" theme="simple"   />
	  <s:hidden name="frmDate" id="paraFrm_frmDate" theme="simple" />
	  <s:hidden name="toDate" id="paraFrm_toDate" theme="simple" />
	   <s:hidden name="empId" id="paraFrm_empId" />
	  <s:hidden name="desg" id="paraFrm_desg" /> 
	  <s:hidden name="branch" id="paraFrm_branch" /> 
<script type="text/javascript" defer='defer'> 


function callAppraisalCode()
{
	var path = '<%=request.getContextPath() %>';
		window.open(path+'/common/PerformanceHome_f9AppCal.action','','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');

}

function getEmpCode() {
		var path = '<%=request.getContextPath() %>';
		window.open(path+'/common/PerformanceHome_f9Employee.action?apprId='+document.getElementById('paraFrm_apprId').value,'','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');
	}

function searchEmpPerformance() {
	try{
		if(document.getElementById('paraFrm_apprCode').value == "") {
			alert('Please select appraisal code.');
		  	return false;	      
	 	} else if(document.getElementById('paraFrm_empName').value == "") {
	      	alert('Please select employee.');
		  	return false;
	 	} else {
			var dashId=<%=dashletId%>;
			var dashWidth=<%=dashletWidth%>;
			var url = '<%=request.getContextPath()%>/common/PerformanceHome_searchEmpPerformance.action?apprId=' +
			document.getElementById('paraFrm_apprId').value + '&empId=' + document.getElementById('paraFrm_empId').value + 
			'&apprCode=' + document.getElementById('paraFrm_apprCode').value + '&empName=' + document.getElementById('paraFrm_empName').value+"&dashId="+dashId+"&dashWidth="+dashWidth;	  
	  		getReloadURL(dashId, url);
			  
		}
	}catch(e){alert("Error----Dashlet-----"+e);}		
	}

	detectFlashPlayerPlugin('empPerformanceDiv','empPerfPluginDiv');
	</script>

