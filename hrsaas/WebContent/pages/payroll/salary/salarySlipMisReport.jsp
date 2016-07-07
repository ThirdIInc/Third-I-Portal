<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SalarySlipMisReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="recordFlag"  />
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
					<td width="93%" class="txt"><strong class="text_head">Employee Salary Slip
					</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								 <s:if test="%{salMisbean.generalFlag}">
								  	<input type="button" class="token" value=" Submit"
										   onclick="return callReport('SalarySlipMisReport_genreport.action');" />
								 </s:if>
								 <s:else>
								 	<input type="button" class="token" value=" Submit"
										  onclick="return callReport('SalarySlipMisReport_report.action');" />
								</s:else>
									<input type="button" class="token"  onclick="resetScreen();" value="Reset"/>			
							</td>
							<td>
								<div align="right"><font color="red">*</font> Indicates	Required</div>
							</td>
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
								<s:hidden name="recrdsPerPage" value="100"/>
						
								<tr>
									<td colspan="1" width="20%"><label class="set" id="month"
										name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>
									<font color="red">*</font>:</td>
									<td colspan="1" width="30%"><s:select name="salMonth"
										cssStyle="width:154"
										list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>

									<td colspan="1" width="20%"><label class="set" id="year"
										name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>
									<font color="red">*</font>:</td>
									<td colspan="1" width="30%">
									<s:textfield name="salYear" maxlength="4" size="25"	onkeypress="return numbersOnly();" /></td>
								</tr>
								
								 
								
								<s:hidden name="salDivisionId" /> 
								
								
								
															
								<s:if test="%{salMisbean.generalFlag}">
								 <s:hidden name="salEmpTypeId" /> 																
								<s:hidden name="salBranchId" /> 								 
								<s:hidden name="salDeptId" /> 	
								<s:hidden name="salDivisionName" /> 
								</s:if>
								<s:else>
								
								<tr>


									<td colspan="1" width="20%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
										color="red">*</font>:</td>
									<td colspan="1" width="30%">
									
									<s:textfield name="salDivisionName" theme="simple" readonly="true"
										maxlength="50" size="25" /> 
									
										<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9Division.action');">

									</td>


									<td colspan="1" width="20%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="salBranchId" />
									<s:textfield name="salBranchName" theme="simple" readonly="true"
										maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9Branch.action');">

									</td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td><s:hidden name="salDeptId" /> <s:textfield
										name="salDeptName" theme="simple" readonly="true"
										maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9Dept.action');">
									</td>


									<td colspan="1" width="20%"><label class="set"
										id="employee.type" name="employee.type"
										ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="salEmpTypeId" />
									<s:textfield name="salEmpTypeName" theme="simple"
										readonly="true" maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9Emptype.action');">

									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set" id="designation"
											name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</label>
									:</td>
									<td><s:hidden name="empRankId" />
									<s:textfield name="empRank" readonly="true" size="25" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9designation.action');">
									</td>
									<td colspan="1" width="20%"><label class="set"
										id="pay.bill" name="pay.bill"
										ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="paybillId" />
									<s:textfield name="paybillName" theme="simple"
										readonly="true" maxlength="50" size="25" /> 
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9paybill.action');">
									</td>
								</tr>
								<tr>
									<td>
										<label class="set"
										id="reporting.to" name="reporting.to"
										ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%>
									</td>
									<td><s:hidden name="reportingToId" />
										<s:hidden name="reportingToToken" />
										<s:textfield name="reportingToName" theme="simple"
										readonly="true" maxlength="50" size="25" /> 
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9reportingTo.action');">	
									</td>
								</tr>
								</s:else>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td colspan="3" width="80%"><s:hidden name="EmpCode" /> <s:textfield
										theme="simple" readonly="true" name="EmpToken" size="25" /> <s:textfield
										theme="simple" readonly="true" size="69" name="EmpName" /> 
										<s:if test="salMisbean.generalFlag">
										</s:if>
										<s:else> 
												<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'SalarySlipMisReport_f9employee.action');">
										</s:else> 
									</td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="slip.for" name="slip.for"
										ondblclick="callShowDiv(this);"><%=label.get("slip.for")%></label>:</td>
									<td colspan="1" width="30%"><s:select name="salarySlipFor"
										cssStyle="width:220"
										list="#{'S':'Salary','A':'Arrears (If paid separtely)'}" /></td>
									</td>
								</tr>

								

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
					<table width="100%" class="formbg">
					<s:if test="salMisbean.generalFlag"></s:if>
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
									System.out.println("------------->"+totalRecords);
								if (totalRecords != 0 ) {
									
									if (total > pagecount) {
								loopcount = total / pagecount;
								extrecords = total % pagecount;
									}
									if (loopcount > 0) {
								for ( i = 0; i < loopcount; i++) {
									System.out.println("--------loopcount----->"+loopcount);
									// set of urls
									%>
								<tr><td nowrap="nowrap">	
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Salary Slips  for <%=(i*pagecount)+1%> to <%=(i*pagecount)+pagecount%> </a> 
									</td></tr>
									<%
								}
								if (extrecords > 0) {
									// add final url
									System.out.println("--------extrecords----->"+extrecords);
									%>
								<tr><td nowrap="nowrap">	<a href="#" onclick="callFun('<%=i%>');">Click here to Download Salary Slips  for <%=(i*pagecount)+1%> to <%=total%> </a> 
								</td></tr>
								<% 
								}
									} else {
										System.out.println("--------total----->"+total);
										//single url
										%>
								<tr><td nowrap="nowrap">
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Salary Slips  for <%=(i*pagecount)+1%> to <%=total%> </a> 
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
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								 <s:if test="%{salMisbean.generalFlag}">
								  	<input type="button" class="token" value=" Submit"
										   onclick="return callReport('SalarySlipMisReport_genreport.action');" />
								 </s:if>
								 <s:else>
								 	<input type="button" class="token" value=" Submit"
										  onclick="return callReport('SalarySlipMisReport_report.action');" />
								</s:else>
									<input type="button" class="token"  onclick="resetScreen();" value="Reset"/>			
							</td>
						</tr>
					</table>
					</td>
				</tr>
	</table>
</s:form>



<script type="text/javascript">
	myonload();
	function myonload()
	{
		var curyear=document.getElementById('paraFrm_salYear').value;
		if(document.getElementById('paraFrm_salYear').value=="")
		{
			setCurrentYear("paraFrm_salYear");
		}
	
	}

	 function callCheck(){
	 	var checkDefault = document.getElementById('arrearFlag').checked;
	 	if(checkDefault){
	 			document.getElementById('arrearFlag').value="Y";
	 	
	 	}
	 }
	 function callFun(rowstart)
	 {
	 	  document.getElementById("paraFrm_recordFlag").value=true;
	   	  document.getElementById("paraFrm").action='SalarySlipMisReport_reportforLink.action?rangeValue='+eval(rowstart);
	      document.getElementById('paraFrm').target="main";
		  document.getElementById("paraFrm").submit();	
		 
	 }
	function resetScreen()
	{
				//UPDATED BY REEBA
				document.getElementById('paraFrm').target="_self";
				document.getElementById('paraFrm').action="SalarySlipMisReport_reset.action";	
				document.getElementById('paraFrm').submit();	
				//document.getElementById('paraFrm').target="_self";	
	}
	function callReport(myaction){
		var empName = document.getElementById('paraFrm_EmpCode').value;
		var year = document.getElementById('paraFrm_salYear').value;
		var month = document.getElementById('paraFrm_salMonth').value;
		var division=document.getElementById('paraFrm_salDivisionId').value;
		/*
		if(empName=="")
		{
		alert("Please Select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}*/
		if(year=='')
		{
		alert("Please Enter "+document.getElementById('year').innerHTML.toLowerCase());
		return false;
		}
		if(month==0)
		{
		alert("Please Select "+document.getElementById('month').innerHTML.toLowerCase());
		return false;
		}
		if(division=="")
		{
		alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
		}

				if(empName=="")
				{
				document.getElementById('paraFrm').target="main";
				}
				else{
					document.getElementById('paraFrm').target="_blank";
				}
				document.getElementById('paraFrm').action=myaction;	
				document.getElementById('paraFrm').submit();	
				
}

</script>
