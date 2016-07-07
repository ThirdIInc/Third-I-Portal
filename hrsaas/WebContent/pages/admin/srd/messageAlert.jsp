<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>






<table width="100%"  border="0" align="center"
	cellpadding="0" cellspacing="0" class="sortable" bgcolor="#FFFFFF">

	<tr>
		<td width="5%" class="whitetable" valign="top" nowrap="nowrap">S No.</td>
		<td class="whitetable" width="30%">Message Subject</td>

		<td width="30%" class="whitetable" align="center">Message Date</td>
		<td width="30%" class="whitetable" align="center">Message Type</td>
		<td width="30%" class="whitetable" align="center">Select</td>
		<!--<td width="30%" class="headercell">View the Application</td>
		-->
	</tr>
	<%
			try {
			int p = 1;
	%>
	<%
			int i = 0;
			HashMap afdata = (HashMap) request.getAttribute("data");
	%>
	<%if(afdata!=null){ %>
	<s:iterator value="msgList" status="stat">
		<%
				String audFlag = (String) afdata.get("" + i);
				System.out.println("-----------------------------adFlag"
				+ audFlag);
		%>







		<tr>
			<s:hidden name="msgId" />
			<s:hidden name="msgTo" />
			<td class="whitetable1" width="5%"><%=p%></td>
			<td class="whitetable1"><a href="<s:url></s:url>"
				onclick="callsF10(500,400,'AlertMessage_detail.action','<s:property value="msgId"/>');"><s:property
				value="msgSub" /></a></td>

			<td class="whitetable1" align="center"><s:property
				value="msgDate" /></td>
			<td class="whitetable1" align="center"><s:property
				value="msgType" /></td>

			<td class="whitetable1" width="15%" align="center"><input
				type="checkbox" class="checkbox" name="leaveChk"
				value="<%=audFlag.equals("Y")?"checked":""%>"
				onclick="callChk(<%=i%>)" /> <input type="hidden"
				name="chkSubmitTest" id="<%=i%>"
				value="<%=audFlag.equals("P")?"N":audFlag%>" /><s:hidden
				name="msgId" /></td>
			<!--<td class="bodyCell" align="center"><s:if test="%{chkFlag}"><a href="<s:url><s:param name="appCode" value="appCode"/></s:url>" onclick="callView(800,600,'/hrsaas-usermanual/tyduty/TyDutyProp_msgView.action');">Click here to View Application</a></s:if>
				<s:else> <a href="<s:url ></s:url>" onclick="callAction()">Click here to Recommend/Reject the Application</a></s:else></td>
			-->
		</tr>
		<%
		i++;
		%>
		<%
		p++;
		%>
	</s:iterator>
	<%}
		} catch (Exception e) {
		}
	%>
</table>


<table>
	

	<s:hidden name="msgCode" />
	<s:hidden name="appCode" />
	<s:hidden name="paraId" />
</table>


<script>
  
 
  function callChk(id){

 if(document.getElementById(id).value=='Y'){
  document.getElementById(id).value='N';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
 } 
}

function callPage(name,id)
{
alert(name);
alert(id);
}

function callsF10(width,height,action,id) {
	
	win=window.open('','win','top=150,left=120,width='+width+',height='+height+',scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action=action;
	document.getElementById("msgCode").value=id;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}

function callAction() {
	

	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action="/hrsaas-usermanual/tyduty/TyDutyApproval_approve.action";
	document.getElementById("paraFrm").submit();	
	
}

function callView(width,height,action,id) {
	
	win=window.open('','win','top=150,left=120,width='+width+',height='+height+',scrollbars=yes,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action=action;
	document.getElementById("paraId").value=id;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
	
}
 </script>
