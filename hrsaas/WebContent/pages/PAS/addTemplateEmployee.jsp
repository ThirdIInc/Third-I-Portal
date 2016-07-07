<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/employeeTreeMapping.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SectionMapping" theme="simple" method="post" name="paraFrm" id="paraFrm">
<%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<!--<s:textfield name="calupdateFlag" value="<%=calupdateflag %>"></s:textfield>-->
		<s:hidden name="calupdateFlag"/>
		<s:hidden name="apprId"/>
		<s:hidden name="startDate"></s:hidden>
		<s:hidden name="endDate"></s:hidden>
		<s:hidden name="apprCode"></s:hidden>
		<s:hidden name="templateCode"/>
		<s:hidden name="templateName"></s:hidden>
		<s:hidden name="addFLag"></s:hidden>
		<s:hidden name="moveGroupName"/>
		<s:hidden name="generateListFlag"/>
		<s:hidden name="modifiedListFlag"/>
		<s:hidden name="empTypeFlag"/>
		<s:hidden name="addQuestionFlag"/>
		<s:hidden name="isSectionDefinedFlag"/>
		<s:hidden name="check" value="%{check}" />
		<s:hidden name="groupId"/>
		
	
<table width="100%" border="0" align="right" cellpadding="2"
					cellspacing="1" class="formbg">

					
					
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Define Template Employee
						</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
          
            </table></td>
          </tr>
      </table></td>
    </tr>
     <tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
            <td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
				</table>                    
	          </td>
	        </tr>
	        
	         <tr>
	        	<td colspan="3">
	        		<table width="100%" class="formbg" >
	        			<tr>
	        				 <td width="25%" colspan="1" height="20" class="formtext"><label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>  :</td>
			 				 <td width="25%" colspan="1" height="20" align="left"><s:property value="apprCode" /></td>
         
	        				 <td width="25%" colspan="1" height="20" class="formtext"><label name="group.name" class = "set"  id="group.name" ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label>  :</td>
	        				<td><s:hidden name="groupName"  /><s:property value="groupName" /></td>
	        			</tr>
	        			
	        			<tr>
				  			<td width="25%">
								<label  class = "set" name="division"  id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font color="red">*</font> :
							</td>
							
							<td width="25%" nowrap="nowrap" colspan="1">
								<s:textfield name="divName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9Division.action'); ">&nbsp;</td>
								<s:hidden name="divCode"/>
							<td width="25%">
								<label  class = "set" name="branch"  id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
							</td>
							
							<td width="25%" nowrap="nowrap" colspan="1">
								<s:textfield name="branchName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9Branch.action'); ">&nbsp;</td>
								<s:hidden name="branchCode"/>
																					
						</tr>
				<tr>
					<td width="25%">
								<label  class = "set" name="department"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													<s:textfield name="deptName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9Department.action'); ">&nbsp;</td>
													<s:hidden name="deptCode"/>
					   
					
						<td width="25%">
								<label  class = "set" name="designation"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													<s:textfield name="designationName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9Designation.action'); ">&nbsp;</td>
													<s:hidden name="designationId"/>
				</tr>
				<tr>
					<td width="25%">
								<label  class = "set" name="grade"  id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													<s:textfield name="gradeName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9Grade.action'); ">&nbsp;</td>
													<s:hidden name="gradeId"/>
						<td width="25%">
								<label  class = "set" name="appraiser.empType"  id="appraiser.empType" ondblclick="callShowDiv(this);"><%=label.get("appraiser.empType")%></label> :
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													<s:textfield name="empTypeName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9EmployeeType.action'); ">&nbsp;</td>
													<s:hidden name="empTypeId"/>
					   
				</tr>
				<tr>
					<td width="25%">
								<label  class = "set" name="appraiser.repotingTo"  id="appraiser.repotingTo" ondblclick="callShowDiv(this);"><%=label.get("appraiser.repotingTo")%></label> :
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													<s:textfield name="empreptName" size="20" readonly="true"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9ReportingTo.action'); ">&nbsp;</td>
													<s:hidden name="empreptToken"/>
													<s:hidden name="empreptId"/>
					   
					
						<td width="25%">
								&nbsp;
						</td>
												
												<td width="25%" nowrap="nowrap" colspan="1">
													&nbsp;
					   
					</td>
				</tr>
				<tr>
					<td colspan="7"><input type="button" value="View Employee List" class="del" onclick="return viewEmployeeList();"> <input type="button" value="Reset" class="del" onclick="resetValues();"></td>
				</tr>
				
	        		</table>
	        	</td>
	        </tr>
	        
	        
	         <tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="6"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Employee List</label></strong></td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						
						<tr class="td_bottom_border">
							<td width="5%" class="formth" nowrap="nowrap"><label name="section.srno" class = "set"  id="section.srno" ondblclick="callShowDiv(this);"><%=label.get("section.srno")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.emptoken" class = "set"  id="appraiser.emptoken" ondblclick="callShowDiv(this);"><%=label.get("appraiser.emptoken")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.empName" class = "set"  id="appraiser.empName" ondblclick="callShowDiv(this);"><%=label.get("appraiser.empName")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.branch" class = "set"  id="appraiser.branch" ondblclick="callShowDiv(this);"><%=label.get("appraiser.branch")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.department" class = "set"  id="appraiser.department" ondblclick="callShowDiv(this);"><%=label.get("appraiser.department")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.designation" class = "set"  id="appraiser.designation" ondblclick="callShowDiv(this);"><%=label.get("appraiser.designation")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.repotingTo" class = "set"  id="appraiser.repotingTo" ondblclick="callShowDiv(this);"><%=label.get("appraiser.repotingTo")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><input type="button" value="Add To Group" class="del" onclick="callAddAppraisee();"></td>
						</tr>
						<% int i = 0;%>

						<s:iterator value="pendAppraiseeList">


							<tr class="sortableTD">
								<td class="sortableTD" width="5%" align="center"><%=i+1%></td>
								<td class="sortableTD" width="5%"><s:property value="ittrPendEmpToken" /><s:hidden
									name="ittrPendEmpToken"></s:hidden><s:hidden
									name="ittrPendEmpId" id='<%="ittrPendEmpId"+i%>' /></td>
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
								<td class="sortableTD" width="5%" align="center"><input type="checkbox" id='<%="addAppraisee"+i%>' name="addAppraisee"></td>
								
							</tr>

							<%
			i++;
			%>
						</s:iterator>
			
			<input type="hidden" name="count" id="count" value="<%=i%>"/>
			<s:hidden name="paraId"></s:hidden>
				<%if(i==0){ %>			       	
				       	 <tr>
				 	          <td colspan="7" class="sortableTD" align="center"><font color="red">No Data To Display</font></td>
				 	     </tr>			       	
			       	<%} %>   
					</table>

					</td>
				
			</table>
			</td>
		</tr>
	        
	        
	         <tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
					<tr>
            <td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            
          </tr>
				</table>                    
	          </td>
	        </tr>

</table>



</s:form>
<script><!--
	function resetValues(){
	document.getElementById("paraFrm").action="SectionMapping_resetEmployee.action";
	document.getElementById("paraFrm").submit();
}
	function viewEmployeeList(){
	if(document.getElementById("paraFrm_divCode").value=="")
	{
		alert("Please select division");
		return false;
	}
	/*if(document.getElementById("paraFrm_branchCode").value=="")
	{
		alert("Please select branch");
		return false;
	}
	if(document.getElementById("paraFrm_deptCode").value=="")
	{
		alert("Please select department");
		return false;
	}*/
	document.getElementById("paraFrm").action="SectionMapping_viewEmployeeList.action";
	document.getElementById("paraFrm").submit();
}
function backFun(){
	
				document.getElementById("paraFrm_paraId").value ="";
				document.getElementById("paraFrm").action="SectionMapping_defineTemplateEmployee.action";
				document.getElementById("paraFrm").submit();
	}
	function callAddAppraisee(){
		var countCheck = document.getElementById("count").value;
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
			alert("Please select atleast one employee to add.");
			return false;
			}
			//alert("counter :: "+counter);
		var conf=confirm("Do you really want to add these employee ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					document.getElementById("paraFrm").action="SectionMapping_addMultipleAppraisee.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
--></script>