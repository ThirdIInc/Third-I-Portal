<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalMonitoring" validate="true" id="paraFrm" theme="simple">
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
	    <tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        		<tr>
				         <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Monitoring</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				     </tr>
	        	</table>
	        </td>
        </tr>
        <tr>
     		 <td colspan="3">
     		 	<table width="100%" border="0" cellpadding="2" cellspacing="2">
                  <tr>
            		<td width="78%"></td>
            		<td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          		  </tr>
        		</table>
          	</td>
   		 </tr>
	    <tr>
	      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="22%" colspan="1" height="20" class="formtext"><label  class = "set" name="appraisal.code"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :</td>
				  <td width="38%" colspan="1" height="20"><s:textfield name="apprCode" size="20" maxlength="25" readonly="true" /><s:hidden name="apprId" /><s:hidden name="templateCode"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callCalendar();"></td>
	              <td width="40%" height="20" class="formtext" colspan="2"><s:hidden name="frmDate"/><s:hidden name="toDate"/><s:hidden name="lockFlag"/><s:hidden name="closureDate"/></td>
	            </tr>
	         </table></td>
	       </tr>
	     <tr>
	    	 <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	    	 	<tr>
					<td colspan="4"><b>View or Update Employee Appraisal Status</b></td>
				</tr>
	         	<tr>
	               <td width="22%" colspan="1" height="20" class="formtext"><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
				   <td width="38%" colspan="2" height="20" ><s:textfield name="empToken" size="10" readonly="true" /><s:textfield name="empName" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();"></td> 
				   <td width="40%" height="20"><s:hidden name="empCode"/><s:hidden name="appraiseeGroup"/>  </td>
	           </tr>
	    	<s:if test="%{displayFlag}">
	   			 <tr>
					<td colspan="4">
						<%
						try {
						%>
				<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
					<tr>
						<td colspan="4">PhaseWise Appraiser List</td>
					</tr>
					<tr>
						<td colspan="4" class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth" align="center"  width="8%"><label name="phase.srno" class = "set"  id="phase.srno" ondblclick="callShowDiv(this);"><%=label.get("phase.srno")%></label></td>
							<td class="formth" align="center"  width="22%"><label name="phase.name" class = "set"  id="phase.name" ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label></td>
							<td class="formth" align="center"  width="40%"><label name="appraiser.name" class = "set"  id="appraiser.name" ondblclick="callShowDiv(this);"><%=label.get("appraiser.name")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="appraiser.level"  class = "set"  id="appraiser.level" ondblclick="callShowDiv(this);"><%=label.get("appraiser.level")%></label></td>
							<td class="formth" align="center"  width="15%"><label name="phase.isCompleted"  class = "set"  id="phase.isCompleted" ondblclick="callShowDiv(this);"><%=label.get("phase.isCompleted")%></label></td>
						</tr>	
						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:if>
							<%int count=0; %>
							<%! int d=0; %>
							<%	int i = 0;	%>
							
							<s:iterator value="phaseList">
								<tr>
									<td width="8%" align="center" class="sortableTD"><%=++i%></td>
									<td width="22%" align="left" class="sortableTD"><s:property value="phaseName" /><s:hidden name="phaseCode" ></s:hidden></td>
									<td width="40%" align="left" class="sortableTD"><s:property value="appraiserName" /></td>
									<td width="15%" align="center" class="sortableTD"><s:property value="appraiserLevel" /></td>
									<td width="15%" align="center" class="sortableTD"><s:property value="phaseCompleted" /></td>
								</tr>
							</s:iterator>
							<%d=i; %>
						</table>
						<%
						} catch (Exception e) {
						}
						%>
					</td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td colspan="4">
					<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
						<tr>
							<td class="formtext" colspan="4"><b>Send Back Appraisal</b></td>
						</tr>
						<tr>
							<td width="22%" ><label name="appraisal.sendBack" class = "set"  id="appraisal.sendBack" ondblclick="callShowDiv(this);"><%=label.get("appraisal.sendBack")%></label> :</td>
							<td width="38%" ><s:textfield name="sbPhaseName" size="10" readonly="true" /><s:textfield name="sbAppraiserName" size="25" readonly="true" /><s:hidden name="sbPhaseCode" ></s:hidden>
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callSbPhase();"/>
								<s:hidden name="sbAppraiserLevel" /><s:hidden name="sbAppraiserCode" /><s:hidden name="sbPhaseOrder" /></td>
							<td width="40%" ><input type="button" value="Process" onclick="callSendBack();"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
					<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
						<tr>
							<td class="formtext" colspan="4"><b>Change Appraisee Group</b></td>
						</tr>
						<tr>
							<td width="22%" ><label name="selectAppraiser" class = "set"  id="selectAppraiser" ondblclick="callShowDiv(this);"><%=label.get("selectAppraiser")%></label> :</td>
							<td width="38%" ><s:textfield name="groupName" size="20" readonly="true" /><s:hidden name="groupId" ></s:hidden>
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callAppraiseeGroup();"/></td>
							<td width="40%" ><input type="button" value="Process" onclick="callChangeGroup();"></td>
						</tr>
						</table>
					</td>
				</tr>
			</s:if>
	    </table></td>
	    </tr>
	    <tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td class="formtext" colspan="4"><b>Add Employee To Appraisal Process </b></td>
				</tr>
				<tr>
					<td width="22%" ><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
					<td width="38%" ><s:textfield name="addEmpToken" size="10" readonly="true" /><s:textfield name="addEmpName" size="25" readonly="true" /><s:hidden name="addEmpId" ></s:hidden>
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callAddEmployee();"/></td>
					<td width="40%" ></td>
				</tr>
				<tr>
						<td width="22%" ><label name="selectAppraiser" class = "set"  id="selectAppraiser" ondblclick="callShowDiv(this);"><%=label.get("selectAppraiser")%></label> :</td>
						<td width="38%" ><s:textfield name="addAppraiserGroup" size="20" readonly="true" /><s:hidden name="addAppraiserGroupCode" ></s:hidden>
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callAddEmpAppraiser();"/></td>
						<td width="40%" ></td>
					</tr>
					<tr>
						<td width="22%" ><label name="selectQgroup" class = "set"  id="selectQgroup" ondblclick="callShowDiv(this);"><%=label.get("selectQgroup")%></label> :</td>
						<td width="38%" ><s:textfield name="templateName" size="10" readonly="true" /><s:textfield name="quesGroupName" size="25" readonly="true" /><s:hidden name="quesGroupCode" ></s:hidden>
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callAddEmpTemplate();"/></td>
						<td width="40%" ><input type="button" value="Process" onclick="callAddEmp();"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td class="formtext" colspan="4"><b>Remove Employee From Appraisal Process </b></td>
				</tr>
				<tr>
					<td width="22%" ><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> : </td>
					<td width="38%" ><s:textfield name="removeEmpToken" size="10" readonly="true" /><s:textfield name="removeEmpName" size="25" readonly="true" /><s:hidden name="removeEmpId" ></s:hidden>
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callRemoveEmployee();"/></td>
					<td width="40%" ><input type="button" value="Process" onclick="callRemoveEmp();"></td>
				</tr>
				</table>
			</td>
		</tr>
	 </table>
</s:form>
<script type="text/javascript">
	function callEmployee(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			if(document.getElementById("paraFrm_lockFlag").value=='Y'){
				
				alert("This appraisal is closed on "+document.getElementById("paraFrm_closureDate").value+". So configuration changes are not allowed in the system.");
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9Employee.action'); 
	}
	function callCalendar(){
			document.getElementById("paraFrm_empCode").value="";
			document.getElementById("paraFrm_empName").value="";
			document.getElementById("paraFrm_empToken").value="";
			
			javascript:callsF9(500,325,'AppraisalMonitoring_f9AppCal.action'); 
	}
	function callSbPhase(){
			if(document.getElementById("paraFrm_appraiseeGroup").value==""){
				alert("Appraisers not defined for selected employee");
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9PhaseCompleted.action'); 
	}
	function callSendBack(){
			if(document.getElementById("paraFrm_sbPhaseCode").value==""){
				alert("Please select "+document.getElementById("appraisal.sendBack").innerHTML.toLowerCase());
				document.getElementById("paraFrm_sbPhaseName").focus();
				return false;
			}
			var conf = confirm('Do you really want to send this appraisl back to selected Phase or Appraiser ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="AppraisalMonitoring_processSendBack.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
	function callAppraiseeGroup(){
			javascript:callsF9(500,325,'AppraisalMonitoring_f9group.action'); 
	}
	function callChangeGroup(){
			if(document.getElementById("paraFrm_groupId").value==""){
				alert("Please select "+document.getElementById("selectAppraiser").innerHTML.toLowerCase());
				document.getElementById("paraFrm_groupName").focus();
				return false;
			}
			if(document.getElementById("paraFrm_groupId").value==document.getElementById("paraFrm_appraiseeGroup").value){
				alert("Employee already present in the selected appraisee group.");
				return false;
			}
			var conf = confirm('Rating or Comment given by the current appraiser will be deleted (if exists).\nDo you want to continue with change '+document.getElementById("selectAppraiser").innerHTML.toLowerCase()+' ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="AppraisalMonitoring_processChangeGroup.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
	function callAddEmployee(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			if(document.getElementById("paraFrm_lockFlag").value=='Y'){
				alert("This appraisal is closed on "+document.getElementById("paraFrm_closureDate").value+". So configuration changes are not allowed in the system.");
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9addEmployee.action'); 
	}
	function callAddEmpAppraiser(){
			if(document.getElementById("paraFrm_addEmpId").value==""){
				alert("Please select "+document.getElementById("employee").innerHTML.toLowerCase());
				document.getElementById("paraFrm_addEmpName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9appraiserGroup.action'); 
	}
	function callAddEmpTemplate(){
			if(document.getElementById("paraFrm_addEmpId").value==""){
				alert("Please select "+document.getElementById("employee").innerHTML.toLowerCase());
				document.getElementById("paraFrm_addEmpName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9template.action'); 
	}
	function callAddEmp(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			if(document.getElementById("paraFrm_addEmpId").value==""){
				alert("Please select "+document.getElementById("employee").innerHTML.toLowerCase()+" to add in appraisal process.");
				document.getElementById("paraFrm_addEmpName").focus();
				return false;
			}
			if(document.getElementById("paraFrm_addAppraiserGroupCode").value==""){
				alert("Please select "+document.getElementById("selectAppraiser").innerHTML.toLowerCase());
				document.getElementById("paraFrm_addAppraiserGroup").focus();
				return false;
			}
			if(document.getElementById("paraFrm_quesGroupCode").value==""){
				alert("Please select "+document.getElementById("selectQgroup").innerHTML.toLowerCase());
				document.getElementById("paraFrm_quesGroupName").focus();
				return false;
			}
			var conf = confirm('Do you really want to add selected employee to appraisal process ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="AppraisalMonitoring_processAddEmp.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
	function callRemoveEmployee(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			if(document.getElementById("paraFrm_lockFlag").value=='Y'){
				alert("This appraisal is closed on "+document.getElementById("paraFrm_closureDate").value+". So configuration changes are not allowed in the system.");
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMonitoring_f9RemoveEmployee.action'); 
	}
	function callRemoveEmp(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			if(document.getElementById("paraFrm_removeEmpId").value==""){
				alert("Please select "+document.getElementById("employee").innerHTML.toLowerCase()+" to remove from appraisal process.");
				document.getElementById("paraFrm_removeEmpName").focus();
				return false;
			}
			var conf = confirm('Do you really want to remove selected employee from appraisal process ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="AppraisalMonitoring_processRemoveEmp.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
	
</script>
