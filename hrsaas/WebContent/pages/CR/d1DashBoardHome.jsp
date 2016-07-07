<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<%
Object[][] d1Menu = (Object[][]) request.getAttribute("d1DashMenuData");
%>
<script>
function callURLFunction(URL){
	    document.getElementById('dashFrame').src=URL;
}

</script>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">
	<div style="float: left; width: 98%">
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/myservices.png" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					DashBoard </strong></td>
					<td width="3%" valign="top" class="txt"><s:select list="dashMenuDropMap" id="dashMenuId" 
						onchange="javascript:callURLFunction(this.value);"/>					
					<div align="right"></div>
					</td>
				</tr>
				<tr>
				<td valign="top" align="left" width="100%" colspan="3"><iframe id="dashFrame"
								frameborder="0"
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" 
								src="http://www.fusioncharts.com/demos/business/management-dashboard/view/" scrolling="no"
								marginwidth="0" marginheight="0" vspace="0" name="dashFrame" width="100%" height="1400px"/></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>	
	</div>
	</div>
</s:form>
<script type="text/javascript">

</script>
