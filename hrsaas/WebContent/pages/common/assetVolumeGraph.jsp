<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<table width="100%" align="center" cellpadding="0" cellspacing="0">

<% String dashletId =(String)request.getAttribute("dashletId");
 	String dashletWidth =(String)request.getAttribute("dashletWidth");
 	String graphXML =(String)request.getAttribute("assetGraphXML");
 	Object assetData[][]=(Object[][])request.getAttribute("assetGraphObj");
 	String dashletWidthActual="";
 	String strXML="<chart  bgAlpha='0' pieRadius='70'>";
 	String assetLength="";
 	try{
 		strXML +=graphXML;
 		assetLength =String.valueOf(assetData.length);
 	
 	}catch(Exception e){
 		System.out.println("exception in asset");
 		assetLength="0";
 		
 	}
 	try{
 	   dashletWidthActual=String.valueOf(Double.parseDouble(dashletWidth)*.70);
 	   }catch(Exception e){
 		   dashletWidthActual ="420";
 	   }
 	strXML +=" </chart>";
 	String chartCode="";%>
 	 <% System.out.println("in createChart result"+chartCode);
 	 System.out.println("in assetLength"+assetLength);%>
 	<%chartCode=createChartHTML("../pages/charts/fusionCharts/Pie3D.swf?ChartNoDataText=Asset data not available, please select Asset type and Asset Sub-type",    "", strXML, "Asset Volume", dashletWidthActual, 190, false);
 	 System.out.println("in createChart result after"+chartCode);%>
	<tr>

		
<td colspan="1" width='75%'>
<table>
<tbody>

<TR><td align="left" colspan="1">
<div id='chartDivAsset' style="display: none">
<%if(!assetLength.equals(""))%><%=chartCode %></div>
</td></TR>

<TR><td align="left" colspan="1">
<div id='pluginDivAsset' style="display: none">
<a target="blank" href="http://www.macromedia.com/go/getflashplayer"><font color="red">Click here to download the plugins required.</font></a></div>
</td></TR>
	
	</tbody>
	</table>
</td>
<td align="right" width='25%'>
		<table width="100%" >
			<tr valign="middle">
				<td align="left"><s:select name="assetCategory" 
					cssStyle="width:100" headerKey="" list="assetmap"
				 onchange="getAssetCategory();" /></td>
				<td align="left"><s:select name="assetType" 
					cssStyle="width:100" headerKey="" list="assetmap1"
					onchange="showAssetGraph();" />
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>

</table>
<script type="text/javascript" defer="defer">
var width='';
var xmlData='';
detectFlashPlayerPlugin('chartDivAsset','pluginDivAsset');
function getAssetCategory() {
	//alert(2);
		id1 = document.getElementById('assetCategory').value;
		//alert(id1);
		var dashId = <%=dashletId%>;
		var dashWidth = <%=dashletWidth%>;
		//alert('dashId=='+dashId);
		//alert('dashWidth=='+dashWidth);
	 	var url = '<%=request.getContextPath()%>/common/AdminHome_getAssetSubTypeList.action?assetCategory='+id1+ '&dashId=' + dashId+ '&dashWidth='+dashWidth; ;
	 	//alert('url=='+url);	  
	  	getReloadURL(<%=dashletId%>, url);
	}
	
	function getAssetSubtype() {
		id1 = document.getElementById('paraFrm_assetCategory').value;
		var dashId = <%=dashletId%>;
		var dashWidth = <%=dashletWidth%>;
		var url = '<%=request.getContextPath()%>/common/AdminHome_getAssetGraph?assetCategory=' + id1 + '&dashId=' + dashId+ '&dashWidth='+dashWidth; 
		getReloadURL(<%=dashletId%>, url);
	}
	function showAssetGraph(){
	try{
	//alert(1); 
		id1 = document.getElementById('assetCategory').value;
		//alert('id1='+id1); 
		id2 = document.getElementById('assetType').value;
		//alert('id2='+id2); 
		var dashId = <%=dashletId%>;
		var dashWidth = <%=dashletWidth%>;
		var url = '<%=request.getContextPath()%>/common/AdminHome_getAssetGraphXml.action?assetCategory=' + id1 + '&assetSubType=' + id2+'&dashId=' + dashId+ '&dashWidth='+dashWidth;
		//alert('url=='+url); 
		getReloadURL(<%=dashletId%>, url);
		
 	}catch(e){
 	alert(e);
 	}
 	
}

</script>




