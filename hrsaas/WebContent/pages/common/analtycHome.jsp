<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>
<s:form action="AnalyticHome" id="paraFrm" theme="simple">
<div  id="reportDash" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
		 <tr>
    <td width="89%" colspan="1" class="header">&nbsp;Report Viewer </td>
    <td width="11%" colspan="1"><div align="right"><a href="#" onclick="closeViewer();"><img  style="cursor: hand;" src="../pages/common/css/default/images/close.gif" width="21" height="18" /></a></div></td>
  </tr>

		<tr>
			<td colspan="2">
			<div class="curvedBox">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="borderLR">
				<tr>
					<td colspan="2" class="bg_grad" align="center">

				<iframe src="" id="urlFrame" style="height: 300px;" width="100%" frameborder='0' ></iframe>
					</td>
				</tr>
				
			</table>
			<div> <div class="r4"></div> <div class="r3"></div> <div
				class="r2"></div> <div class="r1"></div> </div></div>
			</td>
		</tr>
		</table>
</div>
	<div id="floatingBoxParentContainer" style="vertical-align: top;">
	</div>
	

<%!String dashUrls="";
String dashWidths="";
String dashHeights="";
String dashHeaders="";
String dashColumnNos="";
String dashFloats="";
String[][] dashlets=null;
%>
<% 

%>

<%dashlets=(String[][])request.getAttribute("result");
		try{
			if(dashlets!=null && dashlets.length>0){
		for(int i=0;i<dashlets.length;i++){
			
		%>
		
<%if(i==dashlets.length-1){%>
	<%
	
	dashUrls+="'"+request.getContextPath()+"/common/AnalyticHome_getLinks.action?menuCode="+dashlets[i][0]+"'";
	//out.println("dashUrls"+dashUrls);
	dashWidths+="50";
	dashHeights+="200";
	dashHeaders+="'"+dashlets[i][1]+"'";
	if(i%2==0){
	dashColumnNos+="1";
	dashFloats+="'left'";
	}
	else{
	dashColumnNos+="2";
	dashFloats+="'right'";
	}
	
	}
else{
	
	dashUrls+="'"+request.getContextPath()+"/common/AnalyticHome_getLinks.action?menuCode="+dashlets[i][0]+"',";
	//out.println("dashUrls"+dashUrls);
	dashWidths+="50,";
	dashHeights+="200,";
	dashHeaders+="'"+dashlets[i][1]+"',";
	if(i%2==0){
	dashColumnNos+="1,";
	dashFloats+="'left',";
	}
	else{
	dashColumnNos+="2,";
	dashFloats+="'right',";
	}
}

	}
			}
	}catch(Exception e){
		}
		
		%>
		
</s:form>

<%

String dashUrls_local="";
String dashWidths_local="";
String dashHeights_local="";
String dashHeaders_local="";
String dashColumnNos_local="";
String dashFloats_local="";
try{

	 dashUrls_local=dashUrls;
	 dashWidths_local=dashWidths;
	 dashHeights_local=dashHeights;
	 dashHeaders_local=dashHeaders;
	 dashColumnNos_local=dashColumnNos;
	 dashFloats_local=dashFloats;
//System.out.println(dashUrls_local);

 dashUrls="";
 dashWidths="";
 dashHeights="";
 dashHeaders="";
 dashColumnNos="";
 dashFloats="";
 
}catch(Exception e){
	System.out.println("Exception====="+e);
	
	
}
%>

<script type="text/javascript">


//alert(<%=dashUrls_local%>);

	<%if(dashlets!=null && dashlets.length>0){%>
	var numberOfDash = <%=dashlets.length%>;
	<%}%>
	document.getElementById('reportDash').style.display='none';
	var numberOfColumns = 2;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;

function initDragableBoxes()
{ 
		dashletURLs= [<%=dashUrls_local%>];
		dashletWidths= ["49","49"];
		dashletHeights= [<%=dashHeights_local%>];
		columnNos= [<%=dashColumnNos_local%>];
		dashHeaders= [<%=dashHeaders_local%>];
		dashletFloats= [<%=dashFloats_local%>];
	
		var numberOfColumns=2;
		
		
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.
		
		
		
	}
	
	function closeViewer(){
	document.getElementById('reportDash').style.display='none';
	}
</script>



