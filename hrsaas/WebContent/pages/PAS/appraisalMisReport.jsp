<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AppraisalMisReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal MIS Report </strong></td>
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
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							 	<input type="button" class="token" value=" Submit"	onclick="return getURL();" />
								<input type="button" class="token"  onclick="resetScreen();" value="Reset"/></td>
							<td><div align="right"><font color="red">*</font> Indicates	Required</div></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="5" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="appraisal.code" name="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font>:</td>
									<td colspan="1" width="30%"><s:textfield name="apprCode" theme="simple" readonly="true" size="25" /> 
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AppraisalMisReport_f9AppCal.action');"></td>
									<td colspan="1" width="20%"><s:hidden name="frmDate"/><s:hidden name="toDate"/><s:hidden name="apprPeriod"/> </td>
									<td colspan="1" width="30%"><s:hidden name="apprId"/></td>
								</tr>
								<s:if test="%{appraisalMisReport.generalFlag}">
									<s:hidden name="branchId" />
									<s:hidden name="deptId" />
									<s:hidden name="empTypeId" />
								</s:if>
								<s:else>
									<tr>
									<td colspan="1" width="20%"><label class="set" id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font color="red">*</font>:</td>
									<td colspan="1" width="30%"><s:hidden name="divisionId" /><s:textfield name="divisionName" theme="simple" readonly="true" maxlength="50" size="25" /> 
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callCheck('div');"></td>
									<td colspan="1" width="20%"><label class="set" id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="1" width="30%"><s:hidden name="branchId" />
									<s:textfield name="branchName" theme="simple" readonly="true" maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callCheck('brn');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td><s:hidden name="deptId" /> <s:textfield name="deptName" theme="simple" readonly="true" maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callCheck('dept');">
									</td>

									<td colspan="1" width="20%"><label class="set" id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
									<td colspan="1" width="30%"><s:hidden name="empTypeId" /><s:textfield name="empTypeName" theme="simple" readonly="true" maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="callCheck('empType');">
									</td>
								</tr>
								</s:else>
								
								<tr>
									<td colspan="1" width="20%"><label class="set" id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
									<td colspan="3" width="80%"><s:hidden name="empId" /> <s:textfield theme="simple" readonly="true" name="empToken" size="20" /> <s:textfield
										theme="simple" readonly="true" size="50" name="empName" />
										<s:if test="appraisalMisReport.generalFlag">
										</s:if>
										<s:else> 
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" 
											height="18" align="absmiddle" width="18" onclick="callCheck('employee');">
										</s:else> 
									</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							 	<input type="button" class="token" value="Submit" onclick="return getURL();" />
								<input type="button" class="token"  onclick="resetScreen();" value="Reset"/>
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
				<table width="100%" class="formbg">
					<s:if test="appraisalMisReport.generalFlag"></s:if>
					<s:else>
					<s:if test="recordFlag">	
						<%
						try {
							int i=0;
								int totalRecords =0;
								int recPerPage=0;
								totalRecords = (Integer) request.getAttribute("totalRecords");
								recPerPage = (Integer) request.getAttribute("recPerPage");
								int total =totalRecords;								
								int loopcount = 0, pagecount =recPerPage, extrecords = 0;
									//(String) request.getParameter("totalRecords");
									//System.out.println("------------->"+totalRecords);
								if (totalRecords != 0 ) {
									
									if (total > pagecount) {
								loopcount = total / pagecount;
								extrecords = total % pagecount;
									}
									if (loopcount > 0) {
								for ( i = 0; i < loopcount; i++) {
									//System.out.println("--------loopcount----->"+loopcount);
									// set of urls
									%>
								<tr><td nowrap="nowrap">	
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Appraisal Report  for <%=(i*pagecount)+1%> to <%=(i*pagecount)+pagecount%> </a> 
									</td></tr>
									<%
								}
								if (extrecords > 0) {
									// add final url
									//System.out.println("--------extrecords----->"+extrecords);
									%>
								<tr><td nowrap="nowrap">	<a href="#" onclick="callFun('<%=i%>');">Click here to Download Appraisal Report  for <%=(i*pagecount)+1%> to <%=total%> </a> 
								</td></tr>
								<% 
								}
									} else {
										//System.out.println("--------total----->"+total);
										//single url
										%>
								<tr><td nowrap="nowrap">
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Appraisal Report  for <%=(i*pagecount)+1%> to <%=total%> </a> 
							</td></tr>
								<% 
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
								
							}
						%>
					</s:if>	
					</s:else>	
					</table>
					</td>
			</tr>
	</table>
	<s:hidden name="appraisalMisReport.generalFlag" id="generalFlag" />
</s:form>
<script type="text/javascript">
function getURL(){
		
		//alert(document.getElementById("generalFlag").value);
		if(document.getElementById("paraFrm_apprId").value==""){
			alert("Please select "+document.getElementById('appraisal.code').innerHTML.toLowerCase());
			document.getElementById("paraFrm_apprCode").focus();
			return false;
		}
		if(document.getElementById("generalFlag").value=="false")
		{
		    //alert("-----------"+document.getElementById("paraFrm_divisionId").value);
			if(document.getElementById("paraFrm_divisionId").value==""){
			//alert("-----------");
				alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
				document.getElementById("paraFrm_divisionName").focus();
				return false;
			}
		}
		if(document.getElementById('paraFrm_empId').value==""){
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action="AppraisalMisReport_getURL.action";	
		}else{
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="AppraisalMisReport_getReport.action";
			
		}
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').submit();
}
function callFun(rowstart)
 {
   	  document.getElementById("paraFrm").action='AppraisalMisReport_getReport.action?rangeValue='+eval(rowstart);
      document.getElementById('paraFrm').target="_blank";
	  document.getElementById("paraFrm").submit();	
 }
 function callCheck(check){
 		
 		if(document.getElementById("paraFrm_apprId").value==""){
			alert("Please select "+document.getElementById('appraisal.code').innerHTML.toLowerCase());
			document.getElementById("paraFrm_apprCode").focus();
			return false;
		}
		
		if(check =='div')
			javascript:callsF9(500,325,'AppraisalMisReport_f9Division.action');
		if(check =='brn'){
			if(document.getElementById("paraFrm_divisionId").value==""){
				alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
				document.getElementById("paraFrm_divisionName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMisReport_f9Branch.action');
		}
			
		if(check =='dept'){
			if(document.getElementById("paraFrm_divisionId").value==""){
				alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
				document.getElementById("paraFrm_divisionName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMisReport_f9Dept.action');
		}
			
		if(check =='empType'){
			if(document.getElementById("paraFrm_divisionId").value==""){
				alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
				document.getElementById("paraFrm_divisionName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMisReport_f9Emptype.action');
		}
		if(check =='employee'){
			if(document.getElementById("paraFrm_divisionId").value==""){
				alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
				document.getElementById("paraFrm_divisionName").focus();
				return false;
			}
			javascript:callsF9(500,325,'AppraisalMisReport_f9Employee.action');	
		}
 }
 function resetScreen(){
 		document.getElementById('paraFrm').action="AppraisalMisReport_reset.action";
 		document.getElementById('paraFrm').target="main";	
 		document.getElementById("paraFrm").submit();
 }
</script>
