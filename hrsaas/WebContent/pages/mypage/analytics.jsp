
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>

<%
			Object serviceMenuObj[][] = (Object[][]) request
			.getAttribute("serviceMenulst");

	Object groupDataObj[][] = (Object[][]) request
			.getAttribute("groupData");
%>


<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">
	
 

	<div style="float: left; width: 98%"><!-- center div starts-->

	<div style="float: left; width: 100%">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/analytic24.png"  
						  /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Analytics </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">
		

		<%
				if (groupDataObj != null && groupDataObj.length > 0) {
				for (int j = 0; j < groupDataObj.length; j++) {
		%>

		 <%if(j!=0) {%>
			<tr>
			<td >&nbsp;</td>
		</tr>
		
		<%} %><!--
	

 
		 <tr>
			<td ><b><%=Utility.checkNull(String.valueOf(groupDataObj[j][0]))%></b></td>
		</tr>
background-color: #EBEBEB;
		--><tr>
			<td colspan="3" style="margin: 5px 5px 5px 5px; "> 
			 
			 </td>
		</tr>
		

 
	

 

		<%
				if (serviceMenuObj != null && serviceMenuObj.length > 0) {
				int firstchk = 0;
				int secondchk = 0;
				int diff = 3;

				int counter = 0;
				for (int i = 0; i < serviceMenuObj.length; i++) {

					if (String.valueOf(serviceMenuObj[i][5]).equals(
					String.valueOf(groupDataObj[j][0]))) {
						String MenuCode = String
						.valueOf(serviceMenuObj[i][0]);

						String MenuName = String
						.valueOf(serviceMenuObj[i][1]);

						String MenuLink = String
						.valueOf(serviceMenuObj[i][2]);

						String MenuImage = String
						.valueOf(serviceMenuObj[i][3]);

						String MenuKeword = String
						.valueOf(serviceMenuObj[i][4]);

						if (counter == firstchk) {
					secondchk = firstchk + 2;
		%>
			
		
		<tr>
			<%
			}
			%>


			<td width="1%" valign="top" nowrap="nowrap"><img align="absmiddle"
				src="../pages/mypage/images/icons/<%=MenuImage %>"  style="padding-right: 5px;"
				  /><a class="servicelink" align="absmiddle"
				href="javascript:void(0);" title="<%=MenuName%>"
				onclick="callMyAction('<%=Utility.checkNull(MenuLink)%>'); "><%=Utility.checkNull(MenuName)%></a><br>
			 </td>
			<%
						if (counter == secondchk) {

						firstchk = secondchk + 1;
			%>
		</tr>

		<tr>
			<td bgcolor="#ffffff" >&nbsp;</td>
		</tr>


		<%
		}
		%>

		<%
					counter++;
					}
				}
					}
		%>



		<%
			}
			}
		%>
	</table>

	</div>

	<!-- center div starts--> <!-- center div ends--></div>

</s:form>

<script>


function callMyAction_OLD(actionName)
{ 
 	// alert(actionName);
	document.getElementById('paraFrm').action ='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getServiceLinkMenu.action?actionName='+actionName;
	document.getElementById('paraFrm').submit();
}

function callMyAction(actionName)
	{
 // alert("In my code:"+actionName);
	try{
	document.getElementById('paraFrm').action= '<%=request.getContextPath()%>'+actionName;
	document.getElementById("paraFrm").target="_self";
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		alert("Exception:"+e);
	}
	
    }

</script>

