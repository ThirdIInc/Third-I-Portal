
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStrNew" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
<s:hidden name="defineSelectSource" />
		<s:hidden name="empGroupName"/> 
		<s:hidden name="empGroupCode"/> 
		<s:hidden name="empDivCode"/> 
		<s:hidden name="empModuleAbbr"/> 
		<s:hidden name="empModuleName"/> 

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					  Structure </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2">&nbsp;</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2"><input type="button" name="addBtn"
						value="Add Employee" onclick="addEmployee();" class="save" /> <s:submit
						name="" value="Back" cssClass="save"
						action="ReportingStrNew_backToGroupScreen"></s:submit></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="6"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Configured
					Employee List</label></strong></td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						bordercolor="red" class="sortable">

						<tr class="td_bottom_border">

							<td width="5%" class="formth" nowrap="nowrap"><label
								name="srNo" class="set" id="srNo1"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>

							<td width="20%" class="formth" nowrap="nowrap"><label
								name="emp.token" class="set" id="emp.token1"
								ondblclick="callShowDiv(this);"><%=label.get("emp.token")%></label></td>

							<td width="20%" class="formth" nowrap="nowrap"><label
								name="emp.name" class="set" id="emp.name3"
								ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>

							<td width="20%" class="formth" nowrap="nowrap"><label
								name="branch" class="set" id="branch3"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>


							<td width="20%" class="formth" nowrap="nowrap"><label
								name="department" class="set" id="department3"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>

							<td width="10%" class="formth" nowrap="nowrap"><label
								name="designation" class="set" id="designation3"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>

							<td width="10%" class="formth" nowrap="nowrap"><label
								name="reporting.to" class="set" id="reporting.to3"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label></td>

							<td width="10%" class="formth" nowrap="nowrap">
							
							<img title="Move to Other Group" onclick="moveToGroup();"
							
										src="../pages/mypage/images/icons/delete.png"
										style="cursor: pointer;" />
							
							<!--<input
								type="button" value="   Move to Other Group" class="del"
								onclick="moveToGroup();">
								
								--></td>

							<td width="5%" class="formth" nowrap="nowrap">
							
							<img title="Remove Employee" onclick="return delEmployee();"
										src="../pages/mypage/images/icons/delete.png"
										style="cursor: pointer;" />
										
							<!--<input
								type="button" value="Delete" class="del"
								onclick="return delEmployee();">
								
								
								--></td>

						</tr>
						<% int i = 0;%>

						<s:iterator value="confEmployeeList">


							<tr class="sortableTD">
								<td class="sortableTD" align="center"><%=i+1%></td>

								<td class="sortableTD"><s:property value="ittrConfEmpToken" /><s:hidden
									name="ittrConfEmpToken"></s:hidden><s:hidden
									name="ittrConfEmpId" id="<%="ittrConfEmpId"+i%>"></s:hidden></td>

								<td class="sortableTD"><s:property value="ittrConfEmpName" /><s:hidden
									name="ittrConfEmpName" /></td>

								<td class="sortableTD"><s:property
									value="ittrConfEmpBranch" /><s:hidden name="ittrConfEmpBranch" /></td>

								<td class="sortableTD" align="center"><s:property
									value="ittrConfEmpDepartment" /><s:hidden
									name="ittrConfEmpDepartment" /></td>

								<td class="sortableTD" align="center"><s:property
									value="ittrConfEmpDesgination" /><s:hidden
									name="ittrConfEmpDesgination" /></td>

								<td class="sortableTD" align="center"><s:property
									value="ittrConfEmpReptTo" /><s:hidden name="ittrConfEmpReptTo" /></td>

								<td class="sortableTD" align="center"><input
									type="checkbox" id="<%="moveEmployee"+i%>" name="moveEmployee"></td>

								<td class="sortableTD" align="center"><input
									type="checkbox" id="<%="deleteEmployee"+i%>"
									name="deleteEmployee"></td>

							</tr>

							<%
			i++;
			%>
						</s:iterator>

	<input type="hidden" name="countEmp" id="countEmp" value="<%=i%>"/>
			<s:hidden name="paraIdEmp"/>
				<%if(i==0){ %>			       	
				       	 <tr>
				 	          <td colspan="9" class="sortableTD" align="center"><font color="red">No Data To Display</font></td>
				 	     </tr>			       	
			       	<%} %>   


					</table>

					</td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2"><s:submit name=""
						value="Add Employee" cssClass="save"
						action="ReportingStrNew_addTeamEmployee"></s:submit> <s:submit
						name="" value="Back" cssClass="save"
						action="ReportingStrNew_backToGroupScreen"></s:submit></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>

</s:form>

<script>


function moveToGroup(){
try{
var countCheck = document.getElementById("countEmp").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("moveEmployee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrConfEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one employee to move.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to move employee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraIdEmp").value = counter;
					win=window.open('','win','top=240,left=130,width=400,height=200,scrollbars=no,status=no,resizable=no');
					document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="ReportingStrNew_openMoveToGroupForm.action";
					document.getElementById("paraFrm").submit();	
					document.getElementById("paraFrm").target="main";
  			}else{
  				return false;
  			}
}catch(e){alert("e "+e);}
		
		
}

function addEmployee()
{
 
	var divisionCode =document.getElementById('paraFrm_empDivCode').value;
	var groupCode =document.getElementById('paraFrm_empGroupCode').value;
	var groupName =document.getElementById('paraFrm_empGroupName').value;
	var moduleName =document.getElementById('paraFrm_empModuleName').value;
	var moduleAbbr =document.getElementById('paraFrm_empModuleAbbr').value;
	
	document.getElementById("paraFrm").action='ReportingStrNew_addTeamEmployee.action?divisionCodeEmpStr='+divisionCode+'&groupCodeEmpStr='+groupCode+'&groupNameEmpStr='+groupName+'&moduleNameEmpStr='+moduleName+'&moduleAbbrStr='+moduleAbbr;
		
		//alert(document.getElementById("paraFrm").action);
		
				 document.getElementById("paraFrm").submit();

}


function callAdd(){
		var countCheck = document.getElementById("countEmp").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("addAppraisee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrPendEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one Apprisee to add.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to add these Appraisee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					document.getElementById("paraFrm").action="ReportingStrNew_addMultipleEmployee.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
	
	
	function delEmployee(){
		var countCheck = document.getElementById("countEmp").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("deleteEmployee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrConfEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one employee to delete.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to delete employee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraIdEmp").value = counter;
					//win=window.open('','win','top=240,left=130,width=400,height=200,scrollbars=no,status=no,resizable=no');
					//document.getElementById("paraFrm").target="win";
					document.getElementById("paraFrm").action="ReportingStrNew_deleteEmployee.action";
					document.getElementById("paraFrm").submit();	
					//document.getElementById("paraFrm").target="main";
  			}else{
  				return false;
  			}
		
		return true;
}

</script>