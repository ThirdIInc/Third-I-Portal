<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="GoalInitialization" method="post" validate="true" theme="simple"
	name="form" id="paraFrm">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="goalPublishStatus"/>
		<s:hidden name="paraId"/>
		<s:hidden name="goalName" />
		<s:hidden name="goalfromDate"/>
		<s:hidden name="goaltoDate" />
		<s:hidden name="reportingType"/>
		<s:hidden name="deleteKey" id="deleteKey"/>
		<tr>
		<td>
		<table width="100%">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" >
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Eligible Employee List </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
					
						<!--<s:submit cssClass="add" onclick="return saveFunction();" value="    Save" />
						
										
						<s:submit cssClass="report" 
							value="    Report"
							onclick="return empReport();" />
					 --><input type="button" cssClass="back"  value="    Back" 
					 onclick="callForEditBack('<s:property value="goalId" />','<s:property value="goalPublishStatus" />');"/>
					</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="6" class="formhead"><strong class="forminnerhead">Eligible Employee List</strong></td>
				</tr>
				
					<tr>
						<td width="15%" colspan="1"><label name="division"
							id="employee" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
							color="red">*</font>:</td>
						<td width="26%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="division" readonly="true" /></td>
						<td widyh="5%">
						
						<img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_division',200,250,'GoalInitialization_f9MultiDiv.action',event,'false','','right')">
					
						
						
					<s:hidden name="divCode"></s:hidden></td>
				
			
					<td width="15%" colspan="1"><label name="branch"
						id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
						color="red"> </font>:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="branch" readonly="true" /></td>
						<td>
						
						<img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_branch',200,250,'GoalInitialization_f9MultiCenter.action',event,'false','','right')">
						
				      <s:hidden name="branchCode"/></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="dept" id="dept1"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
						color="red"> </font>:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="deptName" readonly="true" /></td>
						<td>
						<img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_deptName',200,250,'GoalInitialization_f9MultiDept.action',event,'false','','right')">
						
						 <s:hidden name="deptCode"></s:hidden></td>
					
						<td width="15%" colspan="1"><label name="designation"
						id="designation1" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="desgName" readonly="true" /></td>
						<td><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_desgName',200,250,'GoalInitialization_f9MultiRank.action',event,'false','','right')">
												
					   <s:hidden name="desgCode"></s:hidden></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="grade"
						id="grade1" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="grade" readonly="true" /></td>
						<td><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_grade',200,250,'GoalInitialization_f9MultiGrade.action',event,'false','','right')">
											
					    <s:hidden name="gradeCode"></s:hidden>
					  
					  <td width="15%" colspan="1"><label name="emp.name"
						id="emp.name1" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>
					:</td>
					<td colspan="2"><s:hidden name="empIdTxt"/>
					<s:hidden name="empToken"/><s:textfield size="34" name="empName" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/>
				    <img id="ctrlHide"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="callsF9(500,325,'GoalInitialization_f9Employee.action'); ;">
							
					</td>
					<td></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="empType"
						id="empType" ondblclick="callShowDiv(this);"><%=label.get("empType")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35" theme="simple"
						name="empType" readonly="true" /></td>
						<td><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_empType',200,250,'GoalInitialization_f9MultiEmpType.action',event,'false','','right')">
												
					    <s:hidden name="empTypeCode"></s:hidden>
					    
					<!--<td width="15%" colspan="1"><label name="dateOfJoining"
						id="dateOfJoining" ondblclick="callShowDiv(this);"><%=label.get("dateOfJoining")%></label>
					:</td>
					<td width="25%" colspan="1"></td>
				--></tr>
				
				<tr>
					<!--  <td width="25%" colspan="1">&nbsp;</td>-->
					<td colspan="4" width="10%" class="sortableTD" id="ctrlHide" align="center">
						<s:hidden name="goalId" id="goalId"/>
						
					<input type="button" theme="simple" value="Search"
						 theme="simple" class="del"
						onclick="return addFunction();" />
						<s:submit cssClass=" reset" theme="simple" value=" Reset"
						action="GoalInitialization_resetEmployee" theme="simple"
						onclick="#" />
						
						</td>
				</tr>
				
				<tr>
					<td width="100%" colspan="6">
					<table class="formbg" width="100%">
						<tr>
							<!--   srNo -->
							<td align="center" class="formth" width="10%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<!--   category  -->
							<td align="center" class="formth" width="20%"><label
								class="set" name="emp.token" id="emp.token"
								ondblclick="callShowDiv(this);"><%=label.get("emp.token")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="emp.name" id="emp.name2"
								ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="branch" id="branch2"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="dept" id="dept2"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="designation" id="designation2"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="grade" id="grade2"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							</td>
							<s:if test="deleteKey">
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<input type="button" value="Add To Goal" onclick="addMultiple()"/>&nbsp;
							<s:checkbox name="selectAll" id="selectAll" onclick="addAll();" title="Select all records to move"/>
							</td></s:if>
						</tr>
						
						
							<%
									int count = 0;
									%>
									<%
										int k = 1;
										int c = 0;
										int temp=0;
									%>
									<s:if test="tableLength">
									<%
										 
										//int cn = PageNoPending * 20 - 20;
									%>
									<!------------- Iterator Starts --------->

									<s:iterator value="empList">

									<tr <%if(count%2==0){
									%> class="tableCell1"	<%
								 //totalPagePending = 0;
								 //PageNoPending = 0;
							%>
								
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="empId" />');" >
 
									 
											<td class="sortableTD" width="5%" align="center"><%=++temp%><s:hidden
												name="srNo" /></td>
											<td class="sortableTD">
										 	 <s:hidden name="empId" id="<%="empId"+c%>" />
											<s:property value="empToken" /></td>
											<td class="sortableTD" align="left"><s:property
												value="empName" /></td>
											<td class="sortableTD" align="left"><s:property
												value="empBranch" /></td>
											<td class="sortableTD" align="left"><s:property
												value="empDepartment" /></td>
											<td class="sortableTD" align="left"><s:property
												value="empDesignation" /></td>
											<td class="sortableTD" align="left"><s:property
												value="empGrade" /></td>
											<s:if test="delKey">
										     <td align="center" class="sortableTD" id="ctrlShow"><input type="checkbox"
											    name="allChk" id="<%="chkDelete"+c%>"
												onclick="callCheck('<s:property	value="empId" />',this)" /></td>
										</s:if>
										
										<%
											k++;
											c++;
										%>
										
										</tr>
									</s:iterator>
									</s:if>
									<input type="hidden" id="count" name="count" value="<%=c%>"/>
									<!------------- Iterator Ends --------->

									 <s:if test="tableLength"></s:if>
									 <s:else>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
								 </s:else>
					</table>
					</td>
				</tr>
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
					<td width="78%">
					
						<!--<s:submit cssClass="add" onclick="return saveFunction();" value="    Save" />
						
										
						<s:submit cssClass="report" 
							value="    Report"
							onclick="return empReport();" />
					 --><input type="button" cssClass="back"  value="    Back" 
					 onclick="callForEditBack('<s:property value="goalId" />','<s:property value="goalPublishStatus" />');"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	</table>

</s:form>
<script>

function getEmployee()
{
	var loginCode =document.getElementById('paraFrm_loginCode').value;
	if(loginCode == "")
		callsF9(500,325,'User_f9UserToken.action');
	else
		alert("You can't change or select Token No for this user ! ")
}
function callAdd(){
	    	var fields = ["paraFrm_category"];
			var labels = ["category1"];
			var labels1 = ["categoryWeightage"];
			var selectFlag = ["enter","enter"];	
		  	var val=document.getElementById('category1').value;
		  	var val1=document.getElementById('paraFrm_categoryWeightage').value;
		  	
		  	if(val1==""){ 
			  alert("Please enter category weightage");
			  document.getElementById('categoryWeightage').value=''; 
			  return false;  
			}  
	
			if(val==""){
			  alert("Please enter atleast minimum one category");
			  document.getElementById('category1').value='';
			  return false;
			}
			
			if(!f9specialchars(fields)){
		          return false;
		    }
		    document.getElementById('paraFrm').target = "main";
		    document.getElementById('isCategoryReqID').checked=true;
		    return true;
	}
	
    function callForDelete2(id){
    try{
    	 document.getElementById('paraFrm_paraId').value = id;
		 document.getElementById('paraFrm').action = "GoalInitialization_removeGoalsCategories.action";
		 document.getElementById('paraFrm').submit();
		 }catch(e)
		 {
		 	alert("Exception occured in delete function : "+e);
		 } 
		 
    }
    
	function callForEdit11(kvalue,rowNo,kval2){
	
	    document.getElementById("category1").value = kvalue;
	    document.getElementById("paraFrm_categoryWeightage").value = kval2;
	    document.getElementById('paraFrm_paraId').value=rowNo;  
	}  
	
	//added by Tinshuk begins
	//function addFunction(){
	 //try{
	     //document.getElementById('paraFrm').target = "main";
		 //document.getElementById('paraFrm').action = "GoalInitialization_addEligibleEmp.action";
		 //document.getElementById('paraFrm').submit();
	//}catch(e){
	//alert(e);
	//}
	//return true;
	//}
	
	
	function addFunction(){
	if(document.getElementById("paraFrm_divCode").value=="")
	{
		alert("Please select division");
		return false;
	}
	document.getElementById("paraFrm").action="GoalInitialization_addEligibleEmp.action";
	document.getElementById("paraFrm").submit();
}
	
	/**function saveFunction(){
	try{
	 if( document.getElementById('deleteKey').value!=null || document.getElementById('deleteKey').value!="true" ){
	alert("Records already saved.");
	return false;
	}else{
	     document.getElementById('paraFrm').target = "main";
		 document.getElementById('paraFrm').action = "GoalInitialization_addDetails.action";
		 document.getElementById('paraFrm').submit();
		 }
	}catch(e){
	alert(e);
	}
		 return true;
	}**/
	
	function saveFunction()
	{
	try
	{
	 if(document.getElementById('deleteKey').value=="false")
	 {
	 alert("Please Unlock The Record");
	 return false;
	 }
	 else
	 {
	 document.getElementById('paraFrm').target="main";
	 document.getElementById('paraFrm').action="GoalInitialization_addDetails.action";
	 document.getElementById('paraFrm').submit();
	 }
	 }
	 catch(e)
	 {
	 alert(e);
	 }
	 return true;
	}
	
	function addMultiple(){
	try{
	//alert("delete multiples");
		var count =document.getElementById('count').value;
		
		var param="";
		var checked =false;
		for(var check=0;check <count; check++){
			if(document.getElementById('chkDelete'+check).checked){
				if(check == eval(count-1)){
				param += document.getElementById('empId'+check).value ;
			}else{
				param += document.getElementById('empId'+check).value+"," ;
				}
				checked = true;
			}
			}
		if(!checked){
				alert("Select minimum one employee to add");
				return false;
			}else{
				document.getElementById('paraFrm_paraId').value=param;
				
				var conf = confirm("Do you really want to add the selected record?");
  				if(conf) {
	      				document.getElementById('paraFrm').action = 'GoalInitialization_multipleEmpAdd.action';
						document.getElementById('paraFrm').submit();
				}
	}
	}catch(e){
	alert(e);
	}
}
			
	function callForEditBack(autoCode,status)
	{

	 	document.getElementById("paraFrm").action="GoalInitialization_eligibleEmployeeList.action?autoCode="+autoCode+"&status="+status;  
	    document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();	
	
	}
	
	  function empReport() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'GoalInitialization_empReport.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function addAll(){
	try{
	var count =document.getElementById('count').value;
	if(document.getElementById("selectAll").checked){
		for(var check=0;check <count; check++){
			document.getElementById('chkDelete'+check).checked=true;
			}
		}else{
		   for(var check=0;check <count; check++){
			document.getElementById('chkDelete'+check).checked=false;
		}
		}
	}catch(e){
	alert(e);
	  }
	}
		
	
	function deleteCurrentRow(obj,g){
  try{
      //alert("loc--"+loc);
      //alert("alert122222-"+document.getElementById('uploadFileNameNew').value);
      //alert("uploadFileName12-"+document.getElementById('uploadFileName'+loc).value);
  
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		
		alert(g);
		alert(document.getElementById('empId'+g).value);
		document.getElementById('empId'+g).value="";
		
		}
		catch(e){
		alert(e);
		}
		
	}
	//added by Tinshuk ends

</script>
