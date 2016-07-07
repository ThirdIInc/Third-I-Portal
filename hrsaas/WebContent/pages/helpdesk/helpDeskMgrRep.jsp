<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskMgrReporting" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="1" cellspacing="2"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Manager</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<s:hidden name="settingCode" />
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="department" class="set" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("dept")%></label>
							:<font color="red">*</font></td>
							<td width="17%" height="22"><s:hidden name="deptCode" /> 
							<s:textfield name="deptName" theme="simple" size="25" readonly="true" /></td>
							<td width="27%" height="22" class="formtext"><img
								id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="callSearch('f9deptaction');"></td>
							<td width="18%" height="22"></td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="manager" class="set" id="manager1"
								ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
							:<font color="red">*</font></td>
							<td><s:hidden  name="managerCode" />
							<s:hidden  name="managerToken" />
							<s:textfield name="managerName" theme="simple" size="25"
								readonly="true" /></td>
							<td width="27%" height="22" class="formtext"><img
								id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="callSearch('f9manageraction');">
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="req.type" class="set" id="req.type"
								ondblclick="callShowDiv(this);"><%=label.get("req.type")%></label>
							:<font color="red">*</font></td>
							<td width="17%" height="22"><s:hidden name="reqTypeCode" />
							<s:textfield name="reqType" theme="simple" size="25"
								readonly="true" /></td>
							<td width="27%" height="22" class="formtext"><img
								id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="callSearch('f9reqtypeaction');"></td>
						</tr>
						<tr>
							<td colspan="3">Select Branches :<font color="red">*</font></td>
						</tr>
						<tr>
							<td colspan="4">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="sorttable">
								<tr>
									<td class="formth">Sr.No</td>
									<td class="formth">Branch Names</td>
									<td class="formth">Select</td>
								</tr>
								<%
										int count = 1;
										int i = 0;
								%>
								<%!	int del = 0; %>
								<s:iterator value="branchList">
									<tr>
										<td align="center" class="sortableTD"><%=++i%></td>
										<td align="left" class="sortableTD">
										<s:hidden name="branchCodeItt" id="paraFrm_branchCodeItt<%=count%>" /> 
										<s:property value="branchNameItt" id="paraFrm_branchNameItt<%=count%>"/>
										</td>
										<td align="center" class="sortableTD">
										<s:hidden value="savedBranchItt" id="paraFrm_savedBranchItt<%=count%>"/> 
										<input type="hidden" name="selectedCode" id="selectedCode<%=count%>" value='<s:property value="selectedCode"/>'/>
										<s:if test='%{savedBranchItt =="Y"}'>	
											<input type="checkbox" checked="checked" name="checkedBox"  id="checkedBox<%=count%>" 
											   onclick="callSaveBranch('<s:property value="branchCodeItt"/>','<%=count%>')" />
										</s:if><s:else>
										<input type="checkbox" id="checkedBox<%=count%>" name="checkedBox"
											   onclick="callSaveBranch('<s:property value="branchCodeItt"/>','<%=count%>')" />
										</s:else></td>
									</tr>
									<% count++;	%>
								</s:iterator>
								<%	del = count; %>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="subcode" />
		<s:hidden name="listLength" />
		<s:hidden name="hiddenEdit" />
		<s:hidden name="editHidcode" />
		<s:hidden name="hidDeptCode" />

		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

	function callSearch(action) {
	
		if(action == "f9deptaction"){
			document.getElementById("paraFrm_managerCode").value="";
			document.getElementById("paraFrm_managerToken").value="";
			document.getElementById("paraFrm_managerName").value="";
			document.getElementById("paraFrm_reqTypeCode").value="";
			document.getElementById("paraFrm_reqType").value="";
			
		}
		if(action == "f9manageraction"){
			document.getElementById("paraFrm_reqTypeCode").value="";
			document.getElementById("paraFrm_reqType").value="";
		}
		if(action == "f9reqtypeaction"){
			if(document.getElementById("paraFrm_deptName").value==""){
				alert("Please select department");
				return false;
			}
		}
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'HelpDeskMgrReporting_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function saveFun(){
		if(!validateBlank()){
			return false;
		}else{
			document.getElementById("paraFrm").target="main";
		 	document.getElementById("paraFrm").action="HelpDeskMgrReporting_save.action";
		    document.getElementById("paraFrm").submit();
		    }
	}
	
	function validateBlank(){
	
		var department = document.getElementById('paraFrm_deptName').value;
	   	var manager = document.getElementById('paraFrm_managerName').value;
	   	var request = document.getElementById('paraFrm_reqType').value;
	   	
	   
	   	if(department==""){
			alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
	     	document.getElementById("paraFrm_deptName").focus();
	     	return false;
	   	}
	   	if(manager==""){
	   		 alert("Please select "+document.getElementById('manager1').innerHTML.toLowerCase());
	     	 document.getElementById("paraFrm_managerName").focus();
	     	 return false;
	   	}
	   	if(request==""){
	   		 alert("Please select "+document.getElementById('req.type').innerHTML.toLowerCase());
	     	 document.getElementById("paraFrm_reqType").focus();
	     	 return false;
	   	}
	   	
	   	var selected=false;
		var flag = <%=del%>;
		for(var check=1;check < flag;check++) {
				if( document.getElementById('checkedBox'+check).checked ){
					selected = true;
				}
		}
		if(!selected){
				alert("Plesase select atleast one branch");
				return false;
		}
	   
	   	return true ; 
	}
	
	function editFun(){
		return true;
		//document.getElementById("paraFrm").target="main";
	 	//document.getElementById("paraFrm").action="HelpDeskMgrReporting_edit.action";
	   // document.getElementById("paraFrm").submit();
	}
	function backFun(){
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskMgrReporting_back.action";
	    document.getElementById("paraFrm").submit();
	}
	function resetFun(){
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskMgrReporting_reset.action";
	    document.getElementById("paraFrm").submit();
	}
	function callSaveBranch(id,i) {
		if(document.getElementById('checkedBox'+i).checked == true)	{
			document.getElementById('selectedCode'+i).value=id;
		} else {
			document.getElementById('selectedCode'+i).value="";
		}
	}
	function addList(){
   		
	   	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDeskMgrReporting_addItem.action';
		document.getElementById('paraFrm').submit();
   	}
   	function deleteFun(){
   		var conf=confirm("Are you sure you want to delete this record ?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	  	 	document.getElementById('paraFrm').action = "HelpDeskMgrReporting_delete.action";
			document.getElementById('paraFrm').submit();
		}	
   	}
</script>
