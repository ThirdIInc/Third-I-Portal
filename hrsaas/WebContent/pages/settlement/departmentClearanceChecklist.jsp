
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="DepartmentClearanceChecklist" validate="true"
	id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Department
					Clearance Checklist</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button"
						onclick="return validate();" class="add" value=" Save" /> <s:submit
						action="DepartmentClearanceChecklist_reset" value="Reset"
						cssClass="reset" /> <input type="button" class="search"
						theme="simple" value="  Search" onclick="javascript:search();" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:<font
								color="red">*</font></td>
							<td width="80%" colspan="3"><s:textfield name="deptName"
								size="20" value="%{deptName}" theme="simple" readonly="true" />
							<s:hidden name="deptCode" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'DepartmentClearanceChecklist_f9deptaction.action');"></td>

						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="responsiblePerson" id="responsiblePerson"
								ondblclick="callShowDiv(this);"><%=label.get("responsiblePerson")%></label>:<font
								color="red">*</font></td>
							<td width="80%" colspan="3"><s:textfield name="empName"
								size="20" value="%{empName}" theme="simple" readonly="true" />
							<s:hidden name="empCode" /><s:hidden name="empToken" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'DepartmentClearanceChecklist_f9employee.action');"></td>

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="checklist" id="checklist"
								ondblclick="callShowDiv(this);"><%=label.get("checklist")%></label>:<font
								color="red">*</font></td>
							<td width="80%" colspan="3"><s:textarea name="checkList"
								rows="5" cols="68" /></td>

						</tr>
						<tr id='ctrlHide'>
							<td colspan="5">
							<table width="100%" border="0">

								<td width="535" colspan="3" align="center"><s:submit
									cssClass="add" action="DepartmentClearanceChecklist_addItem"
									value="Add CheckList" onclick="return addCheckList()" /></td>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<table class="formbg" width="100%">

								<tr>
									<td class="formth" align="center" width="10%"><label
										class="set" name="serial.no" id="serial.no1"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td align="center" class="formth" width="40%"><label
										class="set" name="checklist" id="checklist1"
										ondblclick="callShowDiv(this);"><%=label.get("checklist")%></label></td>
									<td class="formth" nowrap="nowrap">Edit | Delete</td>

								</tr>

								<%
								int ii = 0;
								%>
								<%!int listCnt = 0;%>
								<s:iterator value="list">
									<tr>


										<td width="10%" align="center" class="sortableTD"><%=++ii%><input
											type="hidden" name="srNo" value="<%=ii%>" /></td>

										<td class="sortableTD"><s:property value="checkListItt" />&nbsp;<s:hidden
											name="checkListItt" id='<%="checkListItt"+ii%>' /></td>

										<td width="25%" class="sortableTD" align="center" nowrap><input
											type="button" class="rowEdit"
											onclick="callForEdit('<%=ii %>')" /> <input type="button"
											class="rowDelete" onclick="callForDelete('<%=ii %>')" /></td>


									</tr>

								</s:iterator>
								<%
								listCnt = ii;
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="checkEdit" />
	<s:hidden name="checkListId" />

</s:form>

<script>

function addCheckList()
{

	var checklist = document.getElementById('paraFrm_checkList').value;
		var department = document.getElementById('paraFrm_deptCode').value;
   				var employee = document.getElementById('paraFrm_empCode').value;
   		 
   		 		if(department == ""){
  			alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(employee == ""){
  			alert("Please select "+document.getElementById('responsiblePerson').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(checklist == ""){
  			alert("Please enter "+document.getElementById('checklist').innerHTML.toLowerCase());
  			return false;
  		}
}
function search()
{
		callsF9(500,325,'DepartmentClearanceChecklist_f9Searchaction.action');
}

function callForEdit(id){
   		// alert("+++++++++"+id);
   		 
   		document.getElementById('paraFrm_checkEdit').value=id;
   		document.getElementById("paraFrm").action="DepartmentClearanceChecklist_editCheckList.action";
		document.getElementById("paraFrm").submit();
	   	return false;
  	
  		}
	
	
  function callForDelete(id)
	   {
	 	  // alert(id);
	 	  var conf=confirm("Do you really want to delete this application ?");
	 	  
	 	  if(conf)
	 	  {
	 	document.getElementById('paraFrm_checkEdit').value=id;
   		document.getElementById("paraFrm").action="DepartmentClearanceChecklist_deleteCheckList.action";
		document.getElementById("paraFrm").submit();
	   	}
	   	else
	   	{
	   			return false;
	   			
	   	}
	   	
	   			return true;	
   		}
   		
   		function validate()
   		   		{
   		   		
   		   		try{
   				var count ='<%= listCnt %>';
   				var department = document.getElementById('paraFrm_deptCode').value;
   				var employee = document.getElementById('paraFrm_empCode').value;
   		 
   		 		if(department == ""){
  			alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(employee == ""){
  			alert("Please select "+document.getElementById('responsiblePerson').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(count == 0){
  			alert("Please enter "+document.getElementById('checklist').innerHTML.toLowerCase());
  			return false;
  		}
   	}catch(e){alert(e);}		
   		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='DepartmentClearanceChecklist_save.action'; 
		document.getElementById('paraFrm').submit();
   				
   				}

</script>