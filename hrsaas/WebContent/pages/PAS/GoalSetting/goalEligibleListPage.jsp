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
        <s:hidden name="goalId" id="goalId"/>
        <tr>
        <td>
        <table width="100%">
        <tr>
        <td>
        <table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
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
			
		<input type="button" theme="simple" value="Add New Employee"
						 theme="simple"
						onclick="return addFunction();" />
						 <input type="button" cssClass="back"  value="    Back" 
					 onclick="callForEditBack('<s:property value="goalId" />','<s:property value="goalPublishStatus" />');"/>
					 </td>
					 </tr>
					 </table>
					 </td>
					 </tr>
					 

		<table width="100%">
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
							<input type="button" value="Delete" onclick="deleteMultiple()"/>&nbsp;
							<s:checkbox name="selectAll" id="selectAll" onclick="deleteAll();" title="Select all records to remove"/>
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
	          <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
			
		<input type="button" theme="simple" value="Add New Employee"
						 theme="simple"
						onclick="return addFunction();" />
						 <input type="button" cssClass="back"  value="    Back" 
					 onclick="callForEditBack('<s:property value="goalId" />','<s:property value="goalPublishStatus" />');"/>
					 </td>
					 </tr>
					 </table>
					 </td>
					 </tr>
	</table>
	</td>
	</tr>
	</table>
</s:form>


<script>
function deleteAll(){
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
	
	
function deleteMultiple(){
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
				alert("Select minimum one employee to delete");
				return false;
			}else{
				document.getElementById('paraFrm_paraId').value=param;
				
				var conf = confirm("Do you really want to delete this record?");
  				if(conf) {
	      				document.getElementById('paraFrm').action = 'GoalInitialization_multipleEmpDeleteListPage.action';
						document.getElementById('paraFrm').submit();
				}
	}
	}catch(e){
	alert(e);
	}
}

function addFunction()
{
	document.getElementById("paraFrm").action="GoalInitialization_eligibleEmployee.action";
	document.getElementById("paraFrm").submit();
}


	function callForEditBack(autoCode,status)
	{

	 	document.getElementById("paraFrm").action="GoalInitialization_callforedit.action?autoCode="+autoCode+"&status="+status;  
	    document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();	
	
	}
</script>