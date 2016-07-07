<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="EmployeePortal"
			id="paraFrm" theme="simple" name="employeePortalForm">
<table width="90%" border="0" align="center" cellpadding="0"
	cellspacing="0">

	<%
				Object[][] optionObj = (Object[][]) request
				.getAttribute("optionObj");
	%>
	<tr>
		<td style="padding: 5px;">
		<%
		if (optionObj != null && optionObj.length > 0) {
		%> <s:hidden name="pollCode" id="pollCode" value="%{pollCode}" />
		<s:property value="quesName" /> <%
 }
 %>
		</td>
	</tr>

	<%
			if (optionObj != null && optionObj.length > 0) {
			int length = (optionObj.length) > 3 ? 3 : optionObj.length;
			for (int k = 0; k < length; k++) {
	%>
	<tr>
		<td height="22" valign="middle">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		<td valign="middle" width="2px">
		<input type="radio"
			value="<%=String.valueOf(optionObj[k][0]) %>" name="voteValue"
			onClick="setPollValue(this);" id="paraFrm_voteValue">
			</td><td valign="middle" align="left">
			 <span  
			class="blacklink"> &nbsp;<%=String.valueOf(optionObj[k][1])%> </span></td>
			</tr></table></td>
	</tr>
	<%
		}
		}
	%>

	<tr>
		<td height="10"><input type="hidden" name="optionValue"
			id="optionValue" />
 
		</td>
	</tr>
	<tr>
		<td width="100%"> 
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
		<td>
		
		<div align="left">
		<% if (optionObj != null && optionObj.length > 0){%> 
		<img style="cursor: pointer;"
			src="../pages/portal/images/submit.gif" width="51" height="24"
			onclick="submitPoll()" /><%} %>	</div>
			
			</td>
		<td width="75%" align="right"><s:if test="home.statFlag"></s:if><s:else>
					<a class="contlink"
						href="javascript:callPoll('HomePage_showPollStatistics.action');">
					<% if (optionObj != null && optionObj.length > 0){%>Poll Statistics<%} %></a>
				</s:else><br><s:if test="home.hmPrevFlag">
					<a   class="contlink" href="javascript:callPoll('HomePage_showPreviousPoll.action');">
				<% if (optionObj != null && optionObj.length > 0){%>View All<%} %>	</a>
				</s:if><s:else>&nbsp;</s:else></td>
		
		</tr></table>	
		</td>
	</tr>
</table>

</s:form>
<script>


function callPoll(name) {
	try{
		window.open('','window','top=260,left=150,width=520,height=470,scrollbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = "window";
		document.getElementById("paraFrm").action = name + "?pollCode=" + document.getElementById('pollCode').value;
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target = "main";
		}catch(e){alert(e);}
	}


	function setPollValue(id){
		document.getElementById('optionValue').value = id.value;
	}
	
	function submitPoll(){
	try{
	 	var opt = document.getElementById('optionValue').value;
		if(opt == "") {
			alert('Please select an option');
			return false;
		}
		else{ 			
			retrievePollForEmployeePortal('EmployeePortal_pollSave.action?random='+Math.random()+'&pollCode=' + document.getElementById('pollCode').value +'&optionCode=' + opt+'&employeePortalForm');			
		}
	}catch(e){
			//alert(e);
	}			
 }

</script>
