<!--SHashikant DOke--><!--May 11, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<s:form action="SalaryProcess" name="SalaryProcess" validate="true" id="paraFrm" target="main" theme="simple">

	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Salary Process</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>	
							<s:if test="showFlag">
								<s:if test="%{ledgerStatus == 'ATTN_READY'}">
								<s:if test="uploadSalaryFlag!='NU'">
									<input type="button" value="Process" class="token" 
									title="Delete & Process the Salary" onclick="return processSal('processAll')" />
								</s:if>
								<input type="button" class="token" theme="simple" title="Manage Misc Salary"
									onclick="return callMiscSalaryUpload();" value=" Manage Misc Salary " />
								</s:if>
								<s:else>
								<s:if test="%{ledgerStatus == 'SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'}">
								
								</s:if>
								<s:else>
								<s:if test="uploadSalaryFlag!='NU'">
									<input type="button" value="Re-Process All" class="token" 
									title="Delete & Process the Salary" onclick="return processSal('reProcess')" />
								</s:if>
								<input type="button" class="token" theme="simple" title=" Manage Misc Salary "
									onclick="return callMiscSalaryUpload();" value=" Manage Misc Salary " />
								</s:else>
								</s:else>
								 
								<s:if test="%{ledgerStatus == 'ATTN_READY'}">
								</s:if>
								<s:else>
								<!--<s:submit action="SalaryProcess_viewSalaryReport" value="Salary Register" cssClass="token" title="Salary Register" />
								
								--><input type="button" class="token" theme="simple" title="Salary Register"
									onclick="return callSalaryRegister();" value=" Salary Register" />
								
								</s:else>
															
							
								
									
									<s:if test="%{ledgerStatus == 'SAL_FINAL'}">	
									
									<input type="button" class="token" theme="simple" title="Bank Statement"
									onclick="return callBankStatement();" value=" Bank Statement" />	
								<input type="button" class="unlock" title="Unlock Salary" theme="simple"
									onclick="return unlockSal();" value="    Unlock" />
									</s:if>	
									
							</s:if>
							<s:else>
							<s:if test="uploadSalaryFlag!='NU'">
								<input type="button" value="Process" class="token" 
								title="Process the Salary" onclick="return processSal('process')" />
							</s:if>	
							</s:else>
								<s:if test="%{ledgerStatus == 'SAL_START'}">
								
									<input type="button" class="token" theme="simple" title="Bank Statement"
									onclick="return callBankStatement();" value=" Bank Statement" />
										<input type="button"  value="Recalculate Tax" class="token" 
											title="Recalculate Tax" onclick="return checkFun('Tax');" />									
									<input type="button" class="lock" theme="simple" title="Lock Salary"
									onclick="return checkFun('Lock');" value="    Lock" />
									
									</s:if>
							<s:submit action="SalaryProcess_input" value="Return to List" cssClass="token" 
								title="Return to List" onclick="return callReturntoList()" />
								
						</td>
						
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr><s:hidden name="ledgerCode" />
						<s:hidden name="ledgerStatus" />
						<s:hidden name="joinDaysFlag" />
						<s:hidden name="recoveryFlag" />
						<s:hidden name="profHandiFLag" />
						<s:hidden name="incomeTaxFlag" />
						<s:hidden name="vpfFlag" />
						<s:hidden name="lwfFlag" />
						<s:hidden name="lwfDebitCode" />
						<s:hidden name="lwfCreditCode" />
						<s:hidden name="creditRound" />
						<s:hidden name="totalCreditRound" />
						<s:hidden name="totalDebitRound" />
						<s:hidden name="netPayRound" />
						<s:hidden name="recordsPerPage" />
						<s:hidden name="dataFlag" />
						<s:hidden name="recoveryDebitCode" />
						<s:hidden name="monthView"/>
						<s:hidden name="extraWorkFlag"/>
						<s:hidden name="leaveEncashFlag"/>
						<s:hidden name="allowanceFlag"/>
						<s:hidden name="otherIncomeFlag"></s:hidden>
						<s:hidden name="statisticsHidden"/>
						<s:hidden name="statusChange"/>
						<s:hidden name="totalEmp"/>
						<s:hidden name="zeroDays"/>
							<s:hidden name="onHoldEmp"/>							
							<s:hidden name="statusEmpCode"/>
							<s:hidden name="statusEmpName"/>
							<s:hidden name="reProcessEmpCode"/>							
							<s:hidden name="statusChangeFlag"/>
							<s:hidden name="zeroDaysFlag"/>
					<s:hidden name="companyPFFlag"/>		
					<s:hidden name="uploadSalaryFlag"/>		
							
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										<s:if test="showFlag"><s:property value="monthView"/><s:hidden name="month"/></s:if><s:else><s:select name="month" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></s:else>
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td colspan="1">
									<s:if test="showFlag"><s:property value="year"/><s:hidden name="year"/></s:if>
										<s:else><s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
										onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" /></s:else>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">
								<s:hidden name="divisionId" /><s:hidden name="divisionFlag" />
								<s:if test="divisionFlag">
									<tr>
										<td width="300" align="right">
											<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
										</td>
										<s:if test="showFlag">
										<td colspan="2">
											<s:property value="divisionName" /><s:hidden name ="divisionName"/>
										</td>
										</s:if>
										<s:else>
										<td width="20">
											<s:textfield name="divisionName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the division" 
											onclick="callsF9(500,325,'SalaryProcess_f9Division.action');">
										</td>
										</s:else>
										
									</tr>
								</s:if>
								<s:else><s:hidden name="divisionName" /></s:else>
								
								<s:hidden name="branchId" /><s:hidden name="branchFlag" />
								<s:if test="branchFlag">
									<tr>
										<td width="300" align="right">
											<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red">*</font>
										</td>
										
										<s:if test="showFlag">
										<td width="20" >
										<s:property value="branchName" /><s:hidden name ="branchName"/>
										</td>
										<td>&nbsp;
										</td>
										</s:if>
										<s:else>
										<td width="20">
											<s:textfield name="branchName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the branch" 
											onclick="callsF9(500,325,'SalaryProcess_f9Branch.action');">
										</td>
										</s:else>
										
									</tr>
								</s:if>
								<s:else><s:hidden name="branchName" /></s:else>
								
								<s:hidden name="departmentId" /><s:hidden name="departmentFlag" />
								<s:if test="departmentFlag">
									<tr>
										<td width="300" align="right">
											<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="departmentName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'SalaryProcess_f9Department.action');">
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="departmentName" /></s:else>
								
								<s:hidden name="employeeTypeId" /><s:hidden name="employeeTypeFlag" />
								<s:if test="employeeTypeFlag">
									<tr>
										<td width="300" align="right">
											<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="employeeTypeName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the type of an employee" 
											onclick="callsF9(500,325,'SalaryProcess_f9EmployeeType.action');">
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="employeeTypeName" /></s:else>
								
								<s:hidden name="payBillId" /><s:hidden name="payBillFlag" />
								<s:if test="payBillFlag">
									<tr>
										<td width="300" align="right">
											<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :<font color="red">*</font>
										</td>
										<td width="20">
											<s:textfield name="payBillName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the pay bill group" 
											onclick="callsF9(500,325,'SalaryProcess_f9PayBill.action');">
										</td>
									</tr>
								</s:if>
								<s:else><s:hidden name="payBillName" /></s:else>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<s:hidden name="showFlag" />
		
		
	
		
		
		
		<tr>
		
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="50%" valign="top"><b><label id="salary.process.statistics" name="salary.process.statistics" ondblclick="callShowDiv(this);"><%=label.get("salary.process.statistics")%></label></b>
							<table width="100%" align="center" border="0" bordercolor="red" class="formbg" >
								
							
								
								
								<tr>
									<td class="sortableTD" colspan="3"><label id="processed.record" name="processed.record" ondblclick="callShowDiv(this);"><%=label.get("processed.record")%></label>         </td>									
									<td class="sortableTD" align="right" width="15%"><s:property value="processedRecord"/> </td>
									<td class="sortableTD" align="left" width="25%">
									<a href="#" onclick="viewEmpStatistics('T');" title="View the processed record"><font color="blue"><u>View</u></font></a>
									</td>
								</tr>
								<tr>
									<td class="sortableTD" colspan="3"><label id="onhold.record" name="onhold.record" ondblclick="callShowDiv(this);"><%=label.get("onhold.record")%></label>                     </td>									
									<td class="sortableTD" align="right" width="15%"><s:property value="onHoldRecord"/> </td>
									<td class="sortableTD" align="left" width="15%">
									<a href="#" title="Manage the current onhold" onclick="callManageCurrent('O');"><font color="blue"><u>Manage</u></font></a>
									</td>
								</tr>
						<s:if test="uploadSalaryFlag!='NU'">
								<tr>
									<td class="sortableTD" colspan="3"><label id="status.change.record" name="status.change.record" ondblclick="callShowDiv(this);"><%=label.get("status.change.record")%></label></td>									
									<td class="sortableTD" align="right" width="15%"><s:hidden name="statusChangeRecord"/>
									<s:property value="statusChangeRecord"/> </td>
									<td class="sortableTD" align="left" width="15%">
									<s:if test="statusChangeFlag=='true'">
									<a href="#"  title="view the records"  onclick="viewEmpStatistics('S');"><font color="blue"><u>View
									</u></font></a>
								<s:if test="ledgerStatus=='SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'">
								</s:if>
								<s:else>
								
									<input type="button" value="Re-Process" class="token" 
										title="Re-Process the Salary" onclick="return reProcessSalary()" />
								
							</s:else>			 
									</s:if>	
									<s:else><u title="View the records with status change">View</u>
									</s:else>	
									</td>
								</tr>
						</s:if>		
								<tr>
									<td class="sortableTD" colspan="3"><label id="zero.salary.days" name="zero.salary.days" ondblclick="callShowDiv(this);"><%=label.get("zero.salary.days")%></label></td>									
									<td class="sortableTD" align="right" width="15%"><s:property value="zeroSalaryRecord"/> </td>
									<td class="sortableTD" align="left" width="15%">
									<s:if test="zeroDaysFlag=='true'">
									<a href="#" onclick="viewEmpStatistics('Z');"><font color="blue"><u title="View the records with zero salary days">View</u></font></a>
									</s:if>
									<s:else><u title="View the records with zero salary days">View</u>
									</s:else>
									</td>
								</tr>
										
								<tr>
									<td class="sortableTD" colspan="3"><label id="onhold.previous.record" name="onhold.previous.record" ondblclick="callShowDiv(this);"><%=label.get("onhold.previous.record")%></label></td>									
									<td class="sortableTD" align="right" width="15%"><s:property value="onHoldPrevRecord"/> </td>
									<td class="sortableTD" align="left" width="15%">
									<a href="#" title="Manage the previous onhold employee" onclick="viewManagePrevious();"><font color="blue"><u>
									Manage
									</u></font></a>
									</td>
								</tr>	 
								
								<tr>
									<td class="sortableTD" colspan="3"><label id="arrear.record" name="arrear.record" ondblclick="callShowDiv(this);"><%=label.get("arrear.record")%></label></td>									
									<td class="sortableTD" align="right" width="15%"><s:property value="arrearsRecord"/> </td>
									<td class="sortableTD" align="left" width="15%">
									<s:if test="arrearsFlag=='true'">
									<a href="#" title="View the arrears record" onclick="viewArrears();"><font color="blue"><u>
									View
									</u></font></a>
									</s:if>
									<s:else><u title="View the arrears record">View</u>
									</s:else>
									</td>
								</tr>	
								
								
							</table>
						</td>
						
						<td width="50%" valign="top"><b><label id="misc.salary.statistics" name="misc.salary.statistics" ondblclick="callShowDiv(this);"><%=label.get("misc.salary.statistics")%></label>  </b>
							<s:if test="miscList">
							<!--<s:submit action="SalaryProcess_viewMiscSalaryUpload" value="View Misc Upload" cssClass="token" title="View Misc Salary Upload" />
							--></s:if>
							
							
							
							<table width="100%" align="center" class="formbg">
								<tr>
									<td class="formth">
									<s:hidden name="miscCodeHidden" />
									<s:hidden name="miscTypeHidden" />
									<label id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td class="formth"><label id="misc.name" name="misc.name" ondblclick="callShowDiv(this);"><%=label.get("misc.name")%></label> </td>
									<td class="formth"><label id="misc.type" name="misc.type" ondblclick="callShowDiv(this);"><%=label.get("misc.type")%></label> </td>
									<td class="formth"><label id="misc.no.of.employee" name="misc.no.of.employee" ondblclick="callShowDiv(this);"><%=label.get("misc.no.of.employee")%></label></td>
									<td class="formth"><label id="misc.total.amount" name="misc.total.amount" ondblclick="callShowDiv(this);"><%=label.get("misc.total.amount")%></label> </td>
								</tr>
							
								<%int j=1; %>
								
							<s:if test="miscList">
								<s:iterator value="miscList">
								<tr>
									<td class="sortableTD" width="8%"><%=j++ %> </td>
									<td class="sortableTD">
									<a href="#" onclick="viewMiscUpload('<s:property value="miscCode"/>','<s:property value="miscType"/>');"><font color="blue"><u title="View the records"><s:property value="miscName"/></u></font></a>
									
									</td>
									<td class="sortableTD"><s:property value="miscType"/> </td>
									<td class="sortableTD" align="right" width="15%">
									<s:hidden name="miscCode" />
									<s:hidden name="miscType" />
									<s:property value="miscNoOfEmp"/></td>
									<td class="sortableTD" align="right" width="15%"><s:property value="miscTotalAmount"/> </td>
								</tr>
								
								</s:iterator>		 
									<!--<tr>
									<td class="sortableTD">&nbsp; </td>
									<td class="sortableTD"><b>Total</b> </td>
									<td class="sortableTD"> &nbsp;</td>
									<td class="sortableTD" align="right"><b><s:property value="miscTotalEmp"/></b></td>
									<td class="sortableTD" align="right"><b><s:property value="miscTotal"/></b></td>
								</tr>	
							--></s:if>		 
							<s:else>
									<tr>
											<td align="center" colspan="5"><font color="red">No Data To Display</font></td>
										</tr>
							</s:else>
							</table>
						</td>
					
						
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>		
			<td width="100%">
				<table width="100%" class="formbg" >
					<tr>
						<td width="100%"><b><label id="edit.employee.salary" name="edit.employee.salary" ondblclick="callShowDiv(this);"><%=label.get("edit.employee.salary")%></label> </b>
							<table width="100%" align="center" border="0" >
								<tr>
									<td width="20%" align="left" ><label id="select.employee" name="select.employee" ondblclick="callShowDiv(this);"><%=label.get("select.employee")%></label>  :<font color="red">*</font></td>
									<td width="30%" ><s:textfield name="editEmpName" size="45" readonly="true"/>
									<s:hidden name="editEmpCode" /><s:hidden name="editEmpToken" />
										<s:hidden name="editEmpSalaryDays" />
									</td>
									<td width="3%">
									<s:a href="#" >
									<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the employee" onclick="callsF9(500,325,'SalaryProcess_f9editEmployee.action');">
									</s:a></td>
									<td width="34%" align="left" >
									<s:submit action="SalaryProcess_showEmpRecord"  value="Show Record" cssClass="token" title="Show Record" onclick="return callShowRecord();"/>
									</td>
								</tr>
										 
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<s:if test="otherIncomeFlag">
			<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%"><b>Pull Other Income in Salary</b>
							<table width="100%" border ="0" class="formbg">
								<tr>
								<td width="100" align="left">
									<s:if test="extraWorkFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcess_pullExtraWorkBenefit.action')" value='Extrawork Benefits'/>
									</s:if>
									<s:if test="leaveEncashFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcess_pullLeaveEncashment.action')" value='Leave Encashment'/>
									</s:if>
									<s:if test="allowanceFlag">
									
										<input type="button" class="token" onclick="return callPullOtherIncome('SalaryProcess_pullAllowance.action')" value='Allowance'/>
									</s:if>
								</td>
								
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</s:if>
			
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<s:if test="showFlag">
								<s:if test="%{ledgerStatus == 'ATTN_READY'}">
								<s:if test="uploadSalaryFlag!='NU'">
									<input type="button" value="Process" class="token" 
									title="Delete & Process the Salary" onclick="return processSal('processAll')" />
								</s:if>
								<input type="button" class="token" theme="simple" title=" Manage Misc Salary "
									onclick="return callMiscSalaryUpload();" value=" Manage Misc Salary " />
								</s:if>
								<s:else>
								<s:if test="%{ledgerStatus == 'SAL_FINAL'||ledgerStatus == 'ATTN_UNLOCK'}">
								
								</s:if>
								<s:else>
								<s:if test="uploadSalaryFlag!='NU'">
									<input type="button" value="Re-Process All" class="token" 
									title="Delete & Process the Salary" onclick="return processSal('reProcess')" />
								</s:if>
								<input type="button" class="token" theme="simple" title="Manage Misc Salary "
									onclick="return callMiscSalaryUpload();" value=" Manage Misc Salary " />
								</s:else>
								</s:else>
								
								<s:if test="%{ledgerStatus == 'ATTN_READY'}">
								</s:if>
								<s:else>
								<!--<s:submit action="SalaryProcess_viewSalaryReport" value="Salary Register" cssClass="token" title="Salary Register" />
								
								--><input type="button" class="token" theme="simple" title="Salary Register"
									onclick="return callSalaryRegister();" value=" Salary Register" />
								
								</s:else>
								
								<!--<input type="button"  value="Recalculate Tax" class="token" 
								title="Recalculate Tax" onclick="return checkFun('Tax');" />
									-->
									
									<s:if test="%{ledgerStatus == 'SAL_FINAL'}">	
									<input type="button" class="token" theme="simple" title="Bank Statement"
									onclick="return callBankStatement();" value=" Bank Statement" />	
								<input type="button" class="unlock" title="Unlock Salary" theme="simple"
									onclick="return unlockSal();" value="    Unlock" />
									
									</s:if>	
									
							</s:if>
							<s:else>
							<s:if test="uploadSalaryFlag!='NU'">
									<input type="button" value="Process" class="token" 
									title="Process the Salary" onclick="return processSal('process')" />
							</s:if>
							</s:else>
						<s:if test="%{ledgerStatus == 'SAL_START'}">
						
									<input type="button" class="token" theme="simple" title="Bank Statement"
									onclick="return callBankStatement();" value=" Bank Statement" />
									<input type="button"  value="Recalculate Tax" class="token" 
											title="Recalculate Tax" onclick="return checkFun('Tax');" />									
									<input type="button" class="lock" theme="simple" title="Lock Salary"
									onclick="return checkFun('Lock');" value="    Lock" />
									</s:if>
							<s:submit action="SalaryProcess_input" value="Return to List" cssClass="token" 
								title="Return to List" onclick="return callReturntoList()" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

		function callBankStatement(){
		//paraFrm_linkSource
			var arrearsCode=document.getElementById('paraFrm_ledgerCode').value;			
			var linkSource11='SalaryProcess_callForEdit.action?linkSourceCode='+arrearsCode;
			var month=document.getElementById('paraFrm_month').value;
			var year=document.getElementById('paraFrm_year').value;			
			var divName=document.getElementById('paraFrm_divisionName').value;
			var monthText=document.getElementById('paraFrm_monthView').value;
			var divCode=document.getElementById('paraFrm_divisionId').value
			document.getElementById('paraFrm').action='SalaryStatementBank_viewSalaryStatementLink.action?earningType=S&earningTypeDisplay=S&hiddenMonth='+month+'&earningYear='+year+'&earningCode='+
			arrearsCode+'&divisionName11='+divName+'&earningMonth='+monthText+'&linkSource='+linkSource11+'&divisionCode='+divCode;
			document.getElementById('paraFrm').submit();
			//document.getElementById('paraFrm_linkSource').value='SalaryProcess_callForEdit.action?linkSourceCode='+ledgerCodeValue;
			
			}

		function callSalaryRegister(){
			var divName=document.getElementById('paraFrm_divisionName').value;
			var divNameId=document.getElementById('paraFrm_divisionId').value;
			var branchCode=document.getElementById('paraFrm_branchId').value;
			var arrearsCode=document.getElementById('paraFrm_ledgerCode').value;			
			var linkSource11='SalaryProcess_callForEdit.action?linkSourceCode='+arrearsCode;
			document.getElementById('paraFrm').action='SalaryRegister_input.action?divName='+divName+'&divCode='+divNameId+'&linkSource='+linkSource11+'&branchCode='+branchCode;
			document.getElementById('paraFrm').submit();
			}

		function callMiscSalaryUpload(){
			var divName=document.getElementById('paraFrm_divisionName').value;
			var divNameId=document.getElementById('paraFrm_divisionId').value;
			var arrearsCode=document.getElementById('paraFrm_ledgerCode').value;			
			var linkSource11='SalaryProcess_callForEdit.action?linkSourceCode='+arrearsCode;
			document.getElementById('paraFrm').action='MiscSalaryUpload_input.action?linkSource='+linkSource11;
			document.getElementById('paraFrm').submit();
			}

		function callManageCurrent(id){
			document.getElementById('paraFrm').action='SalaryProcess_manageCurrentOnHold.action';
			document.getElementById('paraFrm').submit();
		}

		function viewManagePrevious(){
			document.getElementById('paraFrm').action='SalaryProcess_viewManagePrevious.action';
			document.getElementById('paraFrm').submit();
		}
		
		function viewArrears(){
			callsF9(500,325,'SalaryProcess_f9arrearEmployee.action');	
			}

		function viewMiscUpload(code,type){
			document.getElementById('paraFrm_miscCodeHidden').value=code;
			document.getElementById('paraFrm_miscTypeHidden').value=type;
			callsF9(500,325,'SalaryProcess_f9miscSalary.action');	
			}
		
		function viewEmpStatistics(branchCode) {		
				document.getElementById('paraFrm_statisticsHidden').value=branchCode;
				callsF9(500,325,'SalaryProcess_f9Statistics.action');		
			}
		function viewProcessedRecord(branchCode) {		
			//document.getElementById('paraFrm_statisticsHidden').value=branchCode;
			callsF9(500,325,'SalaryProcess_f9viewProcessedRecord.action');		
		}
		
		function callShowRecord(){

		

		

		
			if(document.getElementById('paraFrm_editEmpCode').value ==''){
					alert('Please select employee');
					document.getElementById('paraFrm_editEmpName').focus();
				return false;
				}
			}

	function callOnload(){
					document.getElementById("overlay").style.visibility = "hidden";
					document.getElementById("overlay").style.display = "none";
					document.getElementById("progressBar").style.visibility = "hidden";
					document.getElementById("progressBar").style.display = "none";
	}

	function reProcessSalary() {
				if(document.getElementById('paraFrm_month').selectedIndex == 0) {
					alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
					document.getElementById('paraFrm_month').focus();
					return false;
				}
				if(document.getElementById('paraFrm_year').value == "") {
					alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
					document.getElementById('paraFrm_year').focus();
					return false;
				}
				if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
				document.getElementById('paraFrm_divisionId').value == "") {
					alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
					return false;
				}
				if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
				document.getElementById('paraFrm_branchId').value == "") {
					alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
					return false;
				}
				if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
				document.getElementById('paraFrm_departmentId').value == "") {
					alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
					return false;
				}
				if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
				document.getElementById('paraFrm_employeeTypeId').value == "") {
					alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
					return false;
				}
				if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
				document.getElementById('paraFrm_payBillId').value == "") {
					alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
					return false;
				}

				if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
					alert("Salary is locked,  Please unlock the salary first");
					return false;
				}

				
				document.getElementById('paraFrm_reProcessEmpCode').value=document.getElementById('paraFrm_statusChange').value;

				con=confirm('Do you really want to Re-Process the salary');
			 	if(con){
				document.getElementById('paraFrm').action = "SalaryProcess_recalculate.action";
				document.getElementById('paraFrm').submit();
			 	}
	}


	function processSal(processType) {
		if(document.getElementById('paraFrm_ledgerStatus').value=='SAL_FINAL'){
			alert("Salary is locked.Please unlock first");
			return false;
		}
	if(processType=='process'){
		
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && 
		document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && 
		document.getElementById('paraFrm_branchId').value == "") {
			alert("Please select the  " + document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && 
		document.getElementById('paraFrm_departmentId').value == "") {
			alert("Please select the  " + document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_employeeTypeFlag').value == 'true' && 
		document.getElementById('paraFrm_employeeTypeId').value == "") {
			alert("Please select the " + document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && 
		document.getElementById('paraFrm_payBillId').value == "") {
			alert("Please select the  " + document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}
		
				//document.getElementById("overlay").style.visibility = "";
				//document.getElementById("overlay").style.display = "";
				//document.getElementById("progressBar").style.visibility = "";
				//document.getElementById("progressBar").style.display = "";
				document.getElementById('paraFrm').action = "SalaryProcess_processSalary.action";
				document.getElementById('paraFrm').submit();
				
		}
	else if(processType=='processAll'){
		var con=confirm('Do you really want to Process the salary?');
		 	if(!con){
		 	return false;
		 	}
		 	//document.getElementById("overlay").style.visibility = "";
			///document.getElementById("overlay").style.display = "";
			///document.getElementById("progressBar").style.visibility = "";
			//document.getElementById("progressBar").style.display = "";
			document.getElementById('paraFrm').action = "SalaryProcess_processAll.action";
			document.getElementById('paraFrm').submit();
	}
	else if(processType=='reProcess'){
			var con=confirm('Do you really want to re-Process the salary?');
			 	if(!con){
			 	return false;
			 	}
			 	//document.getElementById("overlay").style.visibility = "";
				///document.getElementById("overlay").style.display = "";
				///document.getElementById("progressBar").style.visibility = "";
				//document.getElementById("progressBar").style.display = "";
				document.getElementById('paraFrm').action = "SalaryProcess_deleteAndProcessSalary.action";
				document.getElementById('paraFrm').submit();
		}
		return true;
	}
	function uploadFile(fieldName) 
{
	var path="oo/<%=session.getAttribute("session_pool")%>/pay";
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	function callReturntoList() {
		document.getElementById("paraFrm_ledgerCode").value = "";
		document.getElementById("paraFrm_month").value = "";
		document.getElementById("paraFrm_year").value = "";
		document.getElementById("paraFrm_employeeTypeName").value = "";
		document.getElementById("paraFrm_employeeTypeId").value = "";
		document.getElementById("paraFrm_payBillName").value = "";
		document.getElementById("paraFrm_payBillId").value = "";
		document.getElementById("paraFrm_departmentName").value = "";
		document.getElementById("paraFrm_departmentId").value = "";
		document.getElementById("paraFrm_branchName").value = "";
		document.getElementById("paraFrm_branchId").value = "";
		document.getElementById("paraFrm_divisionName").value = "";
		document.getElementById("paraFrm_divisionId").value = "";
		document.getElementById("paraFrm_ledgerStatus").value = "";
	}
	
	function downloadTemplate(){
		document.getElementById('paraFrm').action="SalaryProcess_downloadTemplate.action";
		document.getElementById('paraFrm').submit();
	}
	
function checkCreditUpload(){
	var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var upload = document.getElementById('paraFrm_uploadFileNameCredit').value;
    var credithead = document.getElementById('paraFrm_uploadCreditCode').value;
   
		
		if(credithead==""){
			alert("Please select "+document.getElementById('credit.Name').innerHTML);
			return false;
		}
		
		if(upload==""){
			alert("Please select the Xls File");
			return false;
		}else
		{
		//alert("with in else....!!");
		var sub=upload.substring(upload.length-4,upload.length);

		if(!(sub==".xls"||sub==".Xls")){
		alert("Please Upload Only Xls File with .xls Extension");
		 return false;
		}
		
	   
		}
	document.getElementById('paraFrm').action='SalaryProcess_uploadCredit.action';
	document.getElementById('paraFrm').submit();
}



function checkFun(action){
 	 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
		}
		else{
		if(action=='Lock'){
			
			
			if(document.getElementById("paraFrm_uploadSalaryFlag").value!='NU'){
			if(document.getElementById("paraFrm_statusChangeRecord").value=='0'){

			}else{
				alert('Some records present with status change, Please first Re-Process salary ');
				return false;
				}
			}
			


			
			 	con=confirm('Do you really want to lock the salary?');
			 	if(con){
			 	//enableBlockDiv();
				document.getElementById('paraFrm').action="SalaryProcess_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}else if(action=='Tax'){
			con=confirm('Do you really want to recalculate tax?');
			 	if(con){
				document.getElementById('paraFrm').action="SalaryProcess_recalculateTax.action";
				document.getElementById('paraFrm').submit();
				}
			}
			
		}
 }
 function unlockSal() {
		doAuthorisation('7', 'Salary', 'U');
	}
	
	function doUnlock() {
		//enableBlockDiv();
		document.getElementById('paraFrm').action="SalaryProcess_unLockSalary.action";
		document.getElementById('paraFrm').submit();
	}
function checkDebitUpload(){
var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var upload = document.getElementById('paraFrm_uploadFileNameDebit').value;
    var Debithead = document.getElementById('paraFrm_uploadDebitCode').value;
   
		
		if(Debithead==""){
			alert("Please select "+document.getElementById('debit.Name').innerHTML);
			return false;
		}
		
		if(upload==""){
			alert("Please select the Xls File");
			return false;
		}else
		{
		//alert("with in else....!!");
		var sub=upload.substring(upload.length-4,upload.length);

		if(!(sub==".xls"||sub==".Xls")){
		alert("Please Upload Only Xls File with .xls Extension");
		 return false;
		}
		
	   
		}
		document.getElementById('paraFrm').action='SalaryProcess_uploadDebit.action';
		document.getElementById('paraFrm').submit();

}

function callPullOtherIncome(actionName){
	var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			return false;
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			return false;
		}
	var con=confirm('Do you really want to pull Data in salary?');
			 	if(!con){
			 	return false;
			 	}
		document.getElementById('paraFrm').action=actionName;
		document.getElementById('paraFrm').submit();
}




</script>