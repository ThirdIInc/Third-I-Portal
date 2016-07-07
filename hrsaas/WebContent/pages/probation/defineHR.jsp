<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="DefineHR" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>

					<td colspan="1" width="90%" class="txt"><strong
						class="text_head">Define HR </strong></td>

					<td colspan="2" width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%">
				<tr>
					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" colspan="3">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" width="100%"><strong class="text_head">Division
					Wise </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="division.name" id="division.name"
						ondblclick="callShowDiv(this);"><%=label.get("division.name")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="20%"><s:hidden name="divCode" /> <s:textfield
						name="divisionName" theme="simple" size="35" readonly="true" /></td>
					<td colspan="1" width="35%"><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'DefineHR_f9div.action');">
					</td>

					<td colspan="1" width="25">&nbsp;</td>


				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="employee.name" name="employee.name"
						ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
					<font color="red">*</font> :<s:hidden name="empCode" /></td>

					<td width="80%" colspan="3" align="left"><s:textfield
						name="empToken" size="15" theme="simple" readonly="true" /> <s:textfield
						name="employeeName" size="50" theme="simple" readonly="true" /><img
						src="../pages/images/search2.gif" height="18" align="absmiddle"
						width="18" theme="simple"
						onclick="callSearch();"> &nbsp; &nbsp; &nbsp;
					<s:submit cssClass="add" action="DefineHR_insertDivision" theme="simple" value="   Add   "
						onclick="return callAddDivision();" /></td>

				</tr>

				<!-- List Starts -->


				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr align="center">

					<td colspan="5">
					<table width="100%" cellspacing="1" class="formbg" border="0">
						<tr>
							<td width="10%" class="formth"><label class="set" id="sr.no"
								name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="20%" class="formth"><label class="set"
								id="division.name" name="division.name"
								ondblclick="callShowDiv(this);"><%=label.get("division.name")%></label></td>
							<td width="20%" class="formth"><label class="set"
								id="employee.token" name="employee.token"
								ondblclick="callShowDiv(this);"><%=label.get("employee.token")%></label></td>
							<td width="40%" class="formth"><label class="set"
								id="employee.name" name="employee.name"
								ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></td>
							<td width="10%" class="formth">Delete</td>

						</tr>
						
						<%! int loopCount = 0;%>
							<%
							int n = 0;
							%>
						<s:if test="divisionList">
							
							<s:iterator value="divisionList" status="stat">
								<tr>
									<td width="10%" class="sortableTD" align="center"><s:hidden
										name="srNo" /><%=++n%></td>

									<td width="20%" align="center" class="sortableTD"><s:hidden
										name="divisionId" />&nbsp;<s:property value="divisionNameItt" />
									<s:hidden name="divisionNameItt" /></td>

									<td width="20%" align="center" class="sortableTD">&nbsp; <s:property
										value="employeetokenDivisionItt" /> <s:hidden
										name="employeetokenDivisionItt" /></td>


									<td width="40%" align="center" class="sortableTD">&nbsp; <s:hidden
										name="empid" /><s:property value="employeenameDivisionItt" />
									<s:hidden name="employeenameDivisionItt" /></td>

									<td width="10%" align="center" class="sortableTD" id='ctrlHide'>
									<input type="button" class="rowDelete"
										onclick="callDeleteDivision('<%=n%>')" title="Delete Record" /></td>
									
									</td>
								</tr>
							</s:iterator>
							
						</s:if>
						
						<%
							loopCount=n;
						//out.println("loopCount    " +loopCount);
					//	out.println("n        " + n);
							%>
					</table>
					</td>
				</tr>

				<!-- List Ends -->
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" width="100%"><strong class="text_head">Branch
					Wise</strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="branch.name" id="branch.name"
						ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="20%"><s:hidden name="branchCode" /> <s:textfield
						name="branchName" theme="simple" size="35" readonly="true" /></td>
					<td colspan="1" width="35"><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'DefineHR_f9branch.action');">
					</td>

					<td colspan="1" width="25">&nbsp;</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="employee.name" name="employee.name"
						ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
					<font color="red">*</font> :<s:hidden name="empBranchCode" /></td>
					<td width="80%" colspan="3" align="left"><s:textfield
						name="empBranchToken" size="15" theme="simple" readonly="true" />
					<s:textfield name="employeeBranchName" size="50" theme="simple"
						readonly="true" /><img src="../pages/images/search2.gif"
						height="18" align="absmiddle" width="18" theme="simple"
						onclick="callBranchEmployeeSearch();"> &nbsp; &nbsp; &nbsp;
					<s:submit cssClass="add" action="DefineHR_insertBranch"
						theme="simple" value="   Add   " onclick="return callAddBranch();" /></td>

				</tr>

				<!--  List Starts -->


				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr align="center">

					<td colspan="4">
					<table width="100%" cellspacing="1" class="formbg" border="0">
						<tr>
							<td width="10%" class="formth"><label class="set" id="sr.no"
								name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="20%" class="formth"><label class="set"
								id="branch.name" name="branch.name"
								ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></td>
							<td width="20%" class="formth"><label class="set"
								id="employee.token" name="employee.token"
								ondblclick="callShowDiv(this);"><%=label.get("employee.token")%></label></td>
							<td width="40%" class="formth"><label class="set"
								id="employee.name" name="employee.name"
								ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></td>
							<td width="10%" class="formth">Delete</td>

						</tr>
						
						<%! int loopCountBranch = 0;%>
							
							<%
							int d = 0;
							%>
						<s:if test="branchList">
							
							<s:iterator value="branchList" status="stat">
								<tr>
									<td width="10%" class="sortableTD" align="center"><s:hidden
										name="serialNo" /><%=++d%></td>

									<td width="20%" align="center" class="sortableTD">&nbsp; <s:hidden
										name="branchId" /><s:property value="branchNameItt" /> <s:hidden
										name="branchNameItt" /></td>

									<td width="20%" align="center" class="sortableTD">&nbsp; <s:property
										value="employeetokenBranchItt" /> <s:hidden
										name="employeetokenBranchItt" /></td>

									<td width="20%" align="center" class="sortableTD">&nbsp;<s:hidden
										name="branchEmpId" /> <s:property
										value="employeenameBranchItt" /> <s:hidden
										name="employeenameBranchItt" /></td>

									<td width="20%" align="center" class="sortableTD" id='ctrlHide'>
									<input type="button" class="rowDelete"
										onclick="callDeleteBranch('<%=d%>')" title="Delete Record" /></td>
									   
									
									
									</td>
								</tr>
							</s:iterator>
							
						</s:if>
						
						<% loopCountBranch = d;
							//out.println("d      " + d);
							//out.println("loopCountBranch      " + loopCountBranch);
							
							%>
					</table>
					</td>
				</tr>
				<!--List Ends-->

				</tabel>
				</td>
				</tr>
			</table>
		<tr>
			<td colspan="4" width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

<s:hidden name="hiddenDelete" id="hiddenDelete" />
<s:hidden name="hiddenDeleteDiv" id="hiddenDeleteDiv" />

</s:form>
<script>

function callSearch()
{

	var divCode =document.getElementById('paraFrm_divCode').value;
	
	if(divCode=="")
	{
		alert("Please Select Division.");
		return false;
	}	
	
	else{
	callsF9(500,325,'DefineHR_f9actionEmp.action')
	}
	
	return true ;
}


function callBranchEmployeeSearch()
{
var branchCode =document.getElementById('paraFrm_branchCode').value;
	
	if(branchCode=="")
	{
		alert("Please Select Branch.");
		return false;
	}	

else
{
  callsF9(500,325,'DefineHR_f9empBranch.action')
}

return true;

}





 function callAddDivision(){
       
  var divisionName = document.getElementById('paraFrm_divisionName').value;
  var empId = document.getElementById('paraFrm_empCode').value;
    	
     if(divisionName=="")
  {
      alert("Please Select Division");
          return false;
  }
  
     if(empId=="")
  {
     alert("Please Select Employee");
          return false;
  }
  
     return true;
  
   }
   
 function callAddBranch()
    {
       
    var branchName = document.getElementById('paraFrm_branchName').value;
    var empId = document.getElementById('paraFrm_empBranchCode').value;
      if(branchName=='')
    {
	   alert("Please Select Branch");
	       return false;
	}
      if(empId=="" )
   {
      alert("Please Select Employee");
         return false;
   }

return true;
   }
   
  function callDeleteDivision(deleteDivId)
  {
  ///alert(deleteDivId);
        var conf=confirm("Are you sure to delete this record?");
   if(conf) {
   		
   		document.getElementById("hiddenDeleteDiv").value=deleteDivId;
   	   	document.getElementById("paraFrm").action="DefineHR_deleteDivision.action";
    	document.getElementById("paraFrm").submit();
              } 
   }
   
   
   function callDeleteBranch(deleteId)
   {
 /// 	alert(deleteId);
  	   var conf=confirm("Are you sure to delete this record?");
   if(conf) {
   		
   		          
        document.getElementById("hiddenDelete").value=deleteId;
   	   	document.getElementById("paraFrm").action="DefineHR_deleteBranch.action";
    	document.getElementById("paraFrm").submit();
              } 
   
   }
   
   function saveFun()
{
     var total ='<%=loopCount%>'; 
     var totalnew ='<%=loopCountBranch%>'; 
		 
		  if  (eval(total)== 0 && eval(totalnew)==0)
		  {
		  alert("Please Add atleast one Division or Branch");
		  return false;
		  
		  }


    document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action = 'DefineHR_save.action';
	document.getElementById('paraFrm').submit();
   
 
 }
   
   function resetFun() 
   {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'DefineHR_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
   
</script>

