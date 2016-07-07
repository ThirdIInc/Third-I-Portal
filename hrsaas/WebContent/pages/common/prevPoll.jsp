<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ include file="../charts/FusionCharts.jsp"%>
<script LANGUAGE="Javascript" SRC="../charts/jsClass/FusionCharts.js"></script>
<s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple">
<table width="100%" height="10" align="center" class="formbg">
<% Object[][] optionObj= (Object[][]) request
		 		.getAttribute("result");
           String chartString= String.valueOf(request.getAttribute("chartString"));
%>
		<tr>
			<td align="left" width="30%"><s:if test="home.prevFlag"></s:if><s:else>
				<a href="javascript:getPrevious('HomePage_prevPoll.action','Pr');">Previous</a>
			</s:else> <s:if test="home.nextFlag"></s:if> <s:else>
				<s:if test="home.prevFlag"></s:if>
				<s:else>|</s:else>
			</s:else> <s:if test="home.nextFlag"></s:if><s:else>
				<a href="javascript:getPrevious('HomePage_nextPoll.action','N');">Next</a>
			</s:else></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="20%"><a
				href="javascript:callPoll('HomePage_showPollStatistics.action');">View
			Poll Statistics</a></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td class="whitetable" width="100%" colspan="2"><s:hidden
				name="prevPollCode" id="prevPollCode" value="%{prevPollCode}" /><s:property
				value="prevQuesName" /></td>
		</tr>	
		<tr valign="top">
			<td valign="top" width="100%">
			<%String chartCode =createChartHTML(	"../pages/charts/fusionCharts/Pie2D.swf","",	
						chartString,"productSales", "510",300, false);
		%>
			<div style="height:100%"><%=chartCode%></div>
			</td>
		</tr>
		<tr>
			<td><b> Total Votes : </b><s:property id="totalVotes"
				value="totalVotes" /></td>
		</tr>
		<s:hidden name="clickType" value="%{clickType}" />
	</table>
</s:form>
<script type="text/javascript">
<!--

//-->
function getPrevious(name,type){
	
	document.getElementById("paraFrm_clickType").value=type;
	
	window.open('','window','top=260,left=150,width=500,height=400,scrollbars=yes,status=yes,resizable=yes');

	document.getElementById("paraFrm").target="window";
	document.getElementById("paraFrm").action=name;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}

function callPoll(name) {
	document.getElementById("paraFrm_clickType").value='S';
	window.open('','window','top=260,left=150,width=500,height=400,scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="window";
	document.getElementById("paraFrm").action=name;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}
</script>



