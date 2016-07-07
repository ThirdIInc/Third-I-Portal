<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbConfiguration_" id="paraFrm" theme="simple">



<s:hidden name="myPage" id="myPage" />
	<s:hidden name="IdHead"></s:hidden>
	<s:hidden name="reimbManagerApproval"></s:hidden>
	<s:hidden name="reimbHead"></s:hidden>
	<s:hidden name="reimbAdminApproval"></s:hidden>
	<s:hidden name="reimbAdmin"></s:hidden>
	<s:hidden name="reimbAccountant"></s:hidden>
	
	<table width="100%" border="0" class="formbg" align="right">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Configuration </strong></td>

				</tr>
		</table>
		
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>

						<td align="left" colspan="2"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" />
							
								<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'ReimbConfiguration_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'ReimbConfiguration_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'ReimbConfiguration_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'ReimbConfiguration_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbConfiguration_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
			
					
					
							
							</tr>
			</table>
			</td>
		</tr>
							
							
							
							

							
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0">
				<tr>
					<td colspan="2" class="txt"><strong class="text_head">Reimbursement Configuration</strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete()" /></td>
				</tr>

				</td>
				</tr>
				
				<table width="100%" border="0" cellpadding="1" cellspacing="1">

					<tr>
						<td class="formth" align="center" width="10%"><b>Sr No</td>
						<td class="formth" width="30%" align="center"><label class="set"
							name="reimb.Head" id="reimb.Head"
							ondblclick="callShowDiv(this);"><%=label.get("reimb.Head")%></label></td>
						<td class="formth" align="center" width="15%"><label class="set"
							name="reimb.Manager.Approval" id="reimb.Manager.Approval"
							ondblclick="callShowDiv(this);"><%=label.get("reimb.Manager.Approval")%></label></td>
					   <td class="formth" align="center" width="15%"><label class="set"
							name="reimb.Admin.Approval" id="reimb.Admin.Approval"
							ondblclick="callShowDiv(this);"><%=label.get("reimb.Admin.Approval")%></label></td>
					   <td class="formth" align="center" width="25%"><label class="set"
							name="reimb.Admin" id="reimb.Admin"
							ondblclick="callShowDiv(this);"><%=label.get("reimb.Admin")%></label></td>
					   <td class="formth" align="center" width="25%"><label class="set"
							name="reimb.Accountant" id="reimb.Accountant"
							ondblclick="callShowDiv(this);"><%=label.get("reimb.Accountant")%></label></td>
					   <td align="right" id="ctrlShow" class="formth" nowrap="nowrap">
						<br />
						<input type="checkbox" id="selAll" style="cursor: hand;"
							title="Select all records Name to remove"
							onclick="selectAllRecords(this);" /></td>
					</tr>	
					
					<% 
					int i = 0;
					%>
					<%!int total = 0;%>

					<s:iterator value="ReimbConfigurationItt">
						
						<tr ondblclick="javascript:callForEdit('<s:property  value="ittReimbCode"/>');">
							
							<td width="10%" align="center" class="sortableTD"><s:property
								value="ittSrN0" /></td>
							<td class="sortableTD" width="40%"><s:property
								value="ittReimbHead" /></td>
							<td class="sortableTD" width="5%">&nbsp;<s:property
								value="ittReimbManagerApproval" /></td>    
							<td width="5%" align="center" class="sortableTD"><s:property
								value="ittReimbAdminApproval" /></td>
							<td width="30%" align="center" class="sortableTD"><s:property
								value="ittReimbAdmin" /></td>
							<td width="30%" align="center" class="sortableTD"><s:property
								value="ittReimbAccountant" /></td>	
								
							<td align="center" nowrap="nowrap" class="sortableTD"
								id="ctrlShow"><input type="checkbox" class="checkbox"
								id="confChk<%=i%>" name="confChk"
								onclick="selectRecord('<%=i%>');" />
								 <input type="hidden" name="hidCode" id="hidCode<%=i%>"/ >
								
								
								<s:hidden name="ittReimbCode"></s:hidden  > 
								
								</td>
						</tr>
						
<%
						i++;
						%>
						<%
						total = i;
						%>
					</s:iterator>
				<tr>

						<td align="left" colspan="4"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" />
							</td>
						
					</tr>
				</table>

			</table>
			</td>
		</tr>
	</table>
	
		
</s:form>

<script>
  	
  	function addnewFun(){
  	    document.getElementById('paraFrm').target = "_self";
   	    document.getElementById('paraFrm').action="ReimbConfiguration_addNew.action";
	    document.getElementById('paraFrm').submit();
	   
  	}
  	
  		function searchFun(){
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='ReimbConfiguration_searchConfiguration.action';
			document.getElementById("paraFrm").submit();
  	}
  	
  	function chkDelete() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReimbConfiguration_deleteCheck.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
  	
  	
  		
	
	function selectAllRecords(object) {
			var count='<%=total%>';
		if(object.checked ) {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = true;
				 document.getElementById('hidCode' + i).value="Y";
			}
		} else {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = false;
				 document.getElementById('hidCode' + i).value="";
			}
		}
	}
	

	
	function selectRecord(i){
	
if(document.getElementById('confChk' + i).checked == true ) {
	document.getElementById('hidCode' + i).value="Y";
	}
	else{
	
   document.getElementById('confChk' + i).checked =false;
	document.getElementById('hidCode' + i).value=" ";
	}		 


}

	function callForEdit(id){

	
	document.getElementById('paraFrm_IdHead').value=id;
  	    document.getElementById('paraFrm').target = "_self";
   	   document.getElementById('paraFrm').action="ReimbConfiguration_dblClickItt.action";
	   document.getElementById('paraFrm').submit();
	   return false;
  	}
  	</script>