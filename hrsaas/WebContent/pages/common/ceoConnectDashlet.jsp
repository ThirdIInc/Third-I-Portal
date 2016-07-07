
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple">
	<s:hidden name="ceoFlag" />
	<s:hidden name="ceoPresentFlag"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="9%" valign="top">
			<%
				Object[][] ceoPhotoObj = null;
				try {
					ceoPhotoObj = (Object[][]) request.getAttribute("ceoPhoto");

					if (ceoPhotoObj != null && ceoPhotoObj.length > 0) {

						if (String.valueOf(ceoPhotoObj[0][0]).equals("nophoto")) {
			%> <img src="../pages/mypage/images/icons/ceo.jpg" width="80"
				style="padding: 5px" height="80" /> <%
 } else {
 %> <img style="padding: 5px"
				src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=ceoPhotoObj[0][0] %>"
				width="80" height="80" /> <%
 		}

 		} else {
 %> <img src="../pages/mypage/images/icons/ceo.jpg" width="80"
				style="padding: 5px" height="80" /> <%
 	}
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 %>
			</td>
			<td width="91%" style="padding-left: 5px; text-align: justify;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td height="18">Share Knowledge, Idea, Information as <br>
					freedom to speak & information Sharing <br>
					brings consistent growth.</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="ceoFlag">
			<tr>
				<td valign="top" colspan="2"><a class="contlink"
					onclick="callCeoMsgLink();" href="javascript:void(0);"><br>
				Click here to View Messages</a></td>
			</tr>
		</s:if>
		<s:else> 
			<tr>
			 <s:if test="ceoPresentFlag">
				<td valign="top" colspan="2"><a class="contlink"
					onclick="callCeoLink();" href="javascript:void(0);"><br>
				Click here to Connect our CEO</a></td>
				</s:if>
			</tr>
			
		</s:else>




	</table>
</s:form>
<script>
	function callCeoLink()
{
	try{
	
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/HomePage_ceoConnectData.action?loginEmpCode=<%=request.getAttribute("login_EmpId")%>';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert("Error "+e);
		}	
	 
}
//function to view messages when user is CEO
function callCeoMsgLink()
{
	try{
	
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/HomePage_ceoConnectMsg.action?loginEmpCode=<%=request.getAttribute("login_EmpId")%>';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert("Error "+e);
		}	
	 
}

 </script>