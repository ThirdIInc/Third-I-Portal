
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility,java.util.*"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
			Object serviceMenuObj[][] = (Object[][]) request
			.getAttribute("serviceMenulst");
	HashMap groupMap = (HashMap) request.getAttribute("groupMap");

	String menuType = "Administration Home";
	try {
		menuType = request.getParameter("menuType");
		if (menuType.equals("AC")) {
			menuType = "Administration Configuration";
		} else if (menuType.equals("AP")) {
			menuType = "Administration Process";
		} else if (menuType.equals("AR")) {
			menuType = "Administration Reports";
		} else if (menuType.equals("AD")) {
			menuType = "Administration Dashboard";
		}
	} catch (Exception e) {
		menuType = "Administration Home";
	}
%>


<%@page import="java.util.HashMap"%>
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
						src="../pages/mypage/images/icons/myservices.png" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Modules
					>> Administration >> <%=menuType%> </strong></td>
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
				if (groupMap != null && groupMap.size() > 0) {
				Set keySet = null;
				Iterator itKeyList = null;
				keySet = groupMap.keySet();
				itKeyList = keySet.iterator();
				while (itKeyList.hasNext()) {
					String key = (String) itKeyList.next();
					System.out.println("Group Name :" + key);

					Vector vect = (Vector) groupMap.get(key);
		%>


		<tr>
			<td colspan="6" style="height: 1px;"><img
				src="../pages/mypage/images/icons/<%=key.split("#")[1]%>"
				<% System.out.print("\nImage......"+key.split("#")[1]);%> width="32"
				height="32" align="absmiddle" />&nbsp;&nbsp;&nbsp;<strong><font
				style="border-bottom: 1px; border-bottom-color: #EBEBEB; font-size: 12px;"><%=key.split("#")[0]%></font>

			<%
			System.out.print("\n Image......" + key.split("#")[0]);
			%> </strong></td>
		</tr>
		<tr>
			<td colspan="6" style="height: 1px; background-color: #EBEBEB;">
			</td>
		</tr>

		<%
				for (int m = 0; m < vect.size(); m++) {
				Object[] menuObj = (Object[]) vect.get(m);
				System.out.println("MenuName :"
						+ String.valueOf(menuObj[1]));
		%>
		<%
		if (m % 3 == 3 || m % 3 == 0) {
		%>
		<tr>
			<%
			}
			%>


			<td width="5%" valign="top" style="padding-top: 5px"></td>
			<td width="25%" valign="middle" style="padding-top: 5px"><img
				align="absmiddle" src="../pages/common/css/default/images/dot.gif"><a
				class="servicelink" href="javascript:void(0);"
				title="<%=String.valueOf(menuObj[1])%>"
				onclick="callMyAction('<%=Utility.checkNull(String.valueOf(menuObj[2]))%>'); ">
			<%=Utility.checkNull(String
												.valueOf(menuObj[1]))%></a><br>
			<span class="smalltext"><%=Utility.checkNull(String
												.valueOf(menuObj[4]))%></span></td>
			<%
			if (m % 3 == 2 || m == vect.size()) {
			%>
		</tr>
		<%
		}
		%>


		<%
		}
		%>



		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>

		<%
				// for service group condition check
				}// For group loop
			}// for group loop if check
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
 //alert("In my code:"+actionName);
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

