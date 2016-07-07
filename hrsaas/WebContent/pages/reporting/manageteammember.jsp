 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStrNew" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
<s:hidden name="defineSelectSource" />
		<s:hidden name="teamDivCode"/> 

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
					<td width="78%" colspan="2"><s:submit name="" value="Back"
						cssClass="back" action="ReportingStrNew_backToManageApprover"></s:submit></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<!--<tr>
					<td colspan="6" class="formhead"><strong class="forminnerhead">Eligible
					Employee List</strong></td>
				</tr>

				--><tr>
					<td width="15%" colspan="1"><label name="mdoule.name"
						id="mdoule.name3" ondblclick="callShowDiv(this);"><%=label.get("mdoule.name")%></label><font
						color="red"> </font>:</td>
					<td width="26%" colspan="1"><s:property value="teamModuleName" /><s:hidden
						name="teamModuleName"></s:hidden> <s:hidden
						name="teamModuleAbbr"/> </td>
					<td widyh="5%"></td>
					<td width="15%" colspan="1"><label name="group.name"
						id="group.name3" ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label><font
						color="red"> </font>:</td>
					<td width="25%" colspan="1"><s:property value="teamGroupName" /><s:hidden
						name="teamGroupName"></s:hidden> <s:hidden name="teamGroupCode"/> 
					</td>
					<td></td>
				</tr>

				<tr>
					<td width="15%" colspan="1"><label name="division"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
						color="red"> *</font>:</td>
					<td width="26%" colspan="1"><s:textfield size="33"
						name="division" readonly="true" /> <s:hidden name="divisionCode"></s:hidden></td>
					<td widyh="5%"><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectdivision.action');" />

					</td>


					<td width="15%" colspan="1"><label name="branch" id="branch1"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
						color="red">  </font>:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35"
						theme="simple" name="branch" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_branch',200,250,'ReportingStrNew_f9MultiCenter.action',event,'false','','right')">
					<s:hidden name="branchCode" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="dept" id="dept1"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
						color="red">  </font>:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35"
						theme="simple" name="deptName" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_deptName',200,250,'ReportingStrNew_f9MultiDept.action',event,'false','','right')">
					<s:hidden name="deptCode"></s:hidden></td>

					</td>

					<td width="15%" colspan="1"><label name="designation"
						id="designation1" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35"
						theme="simple" name="desgName" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_desgName',200,250,'ReportingStrNew_f9MultiRank.action',event,'false','','right')">
					<s:hidden name="desgCode"></s:hidden></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="grade" id="grade1"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35"
						theme="simple" name="grade" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_grade',200,250,'ReportingStrNew_f9MultiGrade.action',event,'false','','right')">
					<s:hidden name="gradeCode"></s:hidden>
					<td width="15%" colspan="1"><label name="empType" id="empType"
						ondblclick="callShowDiv(this);"><%=label.get("empType")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textarea rows="1" cols="35"
						theme="simple" name="empType" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_empType',200,250,'ReportingStrNew_f9MultiEmpType.action',event,'false','','right')">
					<s:hidden name="empTypeCode"></s:hidden></td>
					<td></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="reporting.to"
						id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
					:</td>
					<td width="25%" colspan="1"><s:hidden name="reportingtotoken" />
					<s:textfield size="33" name="reportingto" readonly="true" /></td>
					<td><img src="../pages/images/recruitment/search2.gif"
						width="16" height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ReportingStrNew_f9reporting.action');" />
					<s:hidden name="reportingtoCode"></s:hidden>
					<td width="15%" colspan="1"></td>
					<td width="25%" colspan="1"></td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td align="center">
					<td width="15%" colspan="1"></td>
					<td width="25%" colspan="1"><input type="button"
						value="View Employee" onclick="callEmployee();" /></td>
					<td width="15%" colspan="1"></td>
					<td width="25%" colspan="1"></td>
					<td></td>
					<td></td>

					</td>
				</tr>

			</table>
			</td>
		</tr>
		<s:if test="tableLength">
			<tr>
				<td width="100%" colspan="6">
				<table class="formbg" width="100%">
					<tr>
						<!--   srNo -->
						<td align="center" class="formth" width="10%"><label
							class="set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
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
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
						<td align="center" class="formth" width="20%"><label
							class="set" name="dept" id="dept2"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						</td>
						<td align="center" class="formth" width="20%"><label
							class="set" name="designation" id="designation2"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						</td>
						 <td align="center" class="formth" width="20%"><label
							class="set" name="reporting.to" id="reporting.to2"
							ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
						</td>
						<td width="10%" class="formth" nowrap="nowrap">
						
						
						<img title="Add To Group" onclick="callAddEmployee();"
										src="../pages/mypage/images/icons/delete.png"
										style="cursor: pointer;" />
						<!--				
						<input
							type="button" value="Add To Group" class="del"
							onclick="callAddEmployee();">
							
							--></td>
					</tr>


					<%
					int count = 0;
					%>
					<%
						int k = 1;
						int c = 0;
						int temp = 0;
					%>
					<s:if test="tableLength">
						<%
						//int cn = PageNoPending * 20 - 20;
						%>
						<!------------- Iterator Starts --------->

						<% int i = 0;%>

						<s:iterator value="pendEmployeeList">


							<tr class="sortableTD">
								<td class="sortableTD" width="5%" align="center"><%=i+1%></td>
								<td class="sortableTD" width="5%"><s:property value="ittrPendEmpToken" /><s:hidden
									name="ittrPendEmpToken"></s:hidden><s:hidden
									name="ittrPendEmpId" id="<%="ittrPendEmpId"+i%>" /></td>
								<td class="sortableTD" width="5%"><s:property value="ittrPendEmpName" /><s:hidden
									name="ittrPendEmpName"/></td>
								<td class="sortableTD" width="5%"><s:property value="ittrPendEmpBranch" /><s:hidden
									name="ittrPendEmpBranch"/></td>
								<td class="sortableTD" width="5%" align="center"><s:property value="ittrPendEmpDepartment" /><s:hidden
									name="ittrPendEmpDepartment" /></td>
									<td class="sortableTD" width="5%" align="center"><s:property value="ittrPendEmpDesgination" /><s:hidden
									name="ittrPendEmpDesgination" /></td>
									<td class="sortableTD" width="5%" align="center"><s:property value="ittrPendEmpReptTo" /><s:hidden
									name="ittrPendEmpReptTo" /></td>
								<td class="sortableTD" width="5%" align="center"><input type="checkbox" id="<%="addEmployee"+i%>" name="addEmployee"></td>
								
							</tr>

							<%
			i++;
			%>
						</s:iterator>
						
							<input type="hidden" name="count" id="count" value="<%=i%>"/>
			<s:hidden name="paraId" /> 
			
					</s:if>
				
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

		</s:if>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2"><s:submit name="" value="Back"
						cssClass="back" action="ReportingStrNew_backToManageApprover"></s:submit>
					</td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>
</s:form>
<script>
function callEmployee()
{

var divCode =document.getElementById('paraFrm_divisionCode').value;
var branchCode =document.getElementById('paraFrm_branchCode').value;
var deptCode =document.getElementById('paraFrm_deptCode').value;

if(divCode=="")
{
alert("Please select division.");
return false;
}
/*
if(branchCode=="")
{
alert("Please select branch.");
return false;
}
if(deptCode=="")
{
alert("Please select department.");
return false;
}
*/
document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/ReportingStrNew_viewEmployee.action';
		
		//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();
}

function callAddEmployee(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("addEmployee"+j).checked){
						//counter += j+",";
						counter += document.getElementById("ittrPendEmpId"+j).value+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one Employee to add.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to add these Employees ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					document.getElementById("paraFrm").action="ReportingStrNew_addMultipleEmployee.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}

</script>