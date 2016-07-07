<!--Prakash Shetkar--><!--May 11, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" 	src="../pages/common/include/javascript/payrollAjax.js"></script>
<!--  <%//@include file="../common/commonValidations.jsp"%>-->
<script type="text/javascript" src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/syncscroll.js"></script>

<script type="text/javascript">
	var eCode = new Array();
</script>

<s:form action="UMC_SalaryProcess" name="SalaryProcess" id="paraFrm" theme="simple">
	
<div id="msgDiv" style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 50px; left: 250px;'></div>
<div id="confirmationDiv" style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
<div align="center" id="overlay" style="z-index: 3; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;"></div>
<s:if test="dataFlag"> 
<div id="progressBar" style="z-index: 3; position: absolute; width: 770px;">
<table width="100%">
	<tr>
		<td height="200"></td>
	</tr>
	<tr>
		<td align="center"><img src="../pages/images/ajax-loader.gif">
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Processing...</span>
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Please
		do not close the browser and do not click anywhere</span></td>
	</tr>
</table>
</div>
</s:if>
	<table width="98%" class="formbg">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Salary Process Editable View</strong></td>
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
						<td><s:submit action="UMC_SalaryProcess_callForEdit" value="Back" cssClass="token" title="Back" />
						<s:if test="dataFlag"> <input type="button" class="save" theme="simple" onclick="return checkSave('Save');" value=" Save" />
						<input type="button" class="reset" theme="simple" onclick="return resetFun();" value=" Reset" /></s:if></td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
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
						
						<s:hidden name="branchId" />
						<s:hidden name="departmentId" />
						<s:hidden name="employeeTypeId" />
						<s:hidden name="payBillId" />
						<s:hidden name="empId" />
						<s:hidden name="recoveryDebitCode" />
						<s:hidden name="year" />
						<s:hidden name="monthView"/>
						<s:hidden name="month"/>
						<s:hidden name="extraWorkFlag"/>
						<s:hidden name="leaveEncashFlag"/>
						<s:hidden name="allowanceFlag"/>
						<s:hidden name="otherIncomeFlag"></s:hidden>
						
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										<s:property value="monthView" />
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td>
										<s:property value="year"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%" >
								<s:hidden name="divisionId" /><s:hidden name="branchViewId" />
									<tr>
										<td  width="15%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font></td>
										<td  width="35%"><s:textfield name="divisionName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" /></td>
										<td  width="15%"><label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
										<td  width="35%"><s:textfield name="branchViewName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
												<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the branch" onclick="callsF9(500,325,'UMC_SalaryProcess_f9BranchView.action');"></td>
									</tr>
									<s:hidden name="departmentViewId" /><s:hidden name="employeeTypeViewId" />
									<tr>
										<td  width="15%"><label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
										<td  width="35%"><s:textfield name="departmentViewName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
												<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the department" onclick="callsF9(500,325,'UMC_SalaryProcess_f9DepartmentView.action');"></td>
										<td  width="15%"><label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
										<td  width="35%"><s:textfield name="employeeTypeViewName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
												<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the type of an employee" onclick="callsF9(500,325,'UMC_SalaryProcess_f9EmployeeTypeView.action');"> </td>
									</tr>
									<s:hidden name="payBillViewId" /><s:hidden name="empViewId" /><s:hidden name="empStatusView"/><s:hidden name="empTokenView"/>
									<tr>
										<td  width="15%"><label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :</td>
										<td  width="35%"><s:textfield name="payBillViewName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
												<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the pay bill group" onclick="callsF9(500,325,'UMC_SalaryProcess_f9PayBillView.action');"> </td>
										<td  width="15%"><label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
										<td  width="35%"><s:textfield name="empViewName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
												<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="middle" width="18" title="Select the employee" onclick="callsF9(500,325,'UMC_SalaryProcess_f9employee.action');"> </td>
									</tr>
									<tr>
										<td  width="15%"></td>
										<td  width="35%" align="right" ><input type="button"  value="Show Records" class="token" title="Show" onclick="return callEditableSalary();" /></td>
										<td  width="15%"></td>
										<td  width="35%"></td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<s:if test="dataFlag"> 
		<tr>
			<td width="100%">
				<s:hidden name="searchFlagRes" />
				<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
				<s:hidden name="hdProcess" id="hdProcess" value="%{hdProcess}" />
					<table width="100%" class="formbg">
						<tr>
							<td colspan="4">
							<%
								Object[][] rows = (Object[][]) request.getAttribute("rows");
										Object[][] c = (Object[][]) request.getAttribute("creditLength");
										Object[][] d = (Object[][]) request.getAttribute("debitLength");
										int totalPage = (Integer) request.getAttribute("totalPage");
										int pageNo = (Integer) request.getAttribute("PageNo");
										int index = (Integer) request.getAttribute("index");
							%>
							<table width="98%">
								<tr>
									<td width="50%" align="center">
									<%
										if (pageNo != 1) {
									%> <a href="#" onclick="callPage('1');"> <img src="../pages/common/img/first.gif" width="10" height="10"
												class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
										<img src="../pages/common/img/previous.gif" width="10" height="10"	class="iconImage" /> </a> <%
			 						}
								 	if (totalPage <= 5) {
								 				if (totalPage == 1) {
								 					%> <b><u><%=totalPage%></u></b> <%
								 				} else {
								 					for (int z = 1; z <= totalPage; z++) {
								 						if (pageNo == z) {
															 %> <b><u><%=z%></u></b> <%
								 						} else {
															 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				}
								 	} else {
								 				if (pageNo == totalPage - 1 || pageNo == totalPage) {
								 					for (int z = pageNo - 2; z <= totalPage; z++) {
								 						if (pageNo == z) {
								 							%> <b><u><%=z%></u></b> <%
								 						} else {
															 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				} else if (pageNo <= 3) {
								 					for (int z = 1; z <= 5; z++) {
								 						if (pageNo == z) {
															 %> <b><u><%=z%></u></b> <%
								 						} else {
								 							 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				} else if (pageNo > 3) {
								 					for (int z = pageNo - 2; z <= pageNo + 2; z++) {
								 						if (pageNo == z) {
								 							%> <b><u><%=z%></u></b> <%
								 						} else {
								 							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				}
								 			}
								 			if (!(pageNo == totalPage)) {
								 				if (totalPage == 0) {
								 				} else {
													 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
													 <a	href="#" onclick="callPage('<%=totalPage%>');"> <img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> </a> <%
								 				}
								 			}
								 		%>
									 	</td>
			
										<td align="right">
												<input type="button" class="token" onclick="callOnhold()" value="OnHold" /> 
												<input type="button" class="token" onclick="removeOnHold()" value="Remove OnHold" />
												<input type="button" class="edit" onclick="checkRecordRecal();"	value="   Recalculate" />
										</td>
								</tr>
							</table>
							<table id="thetable" width="100%">
								<tr>
									<td><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px" type="text" size="8" value="Employee Id" /></td>
									<td><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px;" type="text" size="24" value="Employee Name" /></td>
									<td><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px" type="text" size="8" value="Salary Days" /></td>
									<s:iterator value="creditHeader">
										<td><input class="tokenPay" type="text" size="4" readonly="readonly" style="text-align: center; border: none; margin: 1px" value="<s:property value="creditName" />" /></td>
									</s:iterator>
									<td><input class="tokenPay" type="text" size="6" style="text-align: center; border: none; margin: 1px" value="Total Credit" /></td>
									<s:iterator value="debitHeader">
										<td><input class="tokenPay" type="text" size="4" readonly="readonly" style="text-align: center; border: none; margin: 1px" value="<s:property value="debitName" />" /></td>
									</s:iterator>
									<td><input class="tokenPay" type="text" readonly="readonly"	style="text-align: center; border: none; margin: 1px" size="6" value="Total Debit" /></td>
									<td><input class="tokenPay" type="text" readonly="readonly"	style="text-align: center; border: none; margin: 1px" size="6"	value="Net Pay" /></td>
									<td><!--  <input class="classCheck" type="checkbox" name="selectId" id="selectId" onclick="return selectAll();" />--> </td>
								</tr>
								<%
									try {
											int colVal = rows[0].length - 4;
											int i = 0;
											for (int k = 0; k < index; k++) {
																
															System.out.println("salary days"+ rows[k][rows[0].length - 3] + "------k="+ k);
															System.out.println("attendance days"+ rows[k][rows[0].length - 1] + "------k="+ k);
											%>
											<%
												if (!(String.valueOf(rows[k][rows[0].length - 3])
																		.equals(String.valueOf(rows[k][rows[0].length - 1])))) {
											%>
													<tr>
														<td><input type="hidden" name="onholdEmp" id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" />
															<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"	id="<%=rows[k][0]%>"> 
															<input	style="background-color: #FF8383" type="text" readonly="readonly" size="8" name="tokenNo" value="<%=rows[k][1] %>"	id='<%=rows[k][0]+"empToken"%>'></td>
														<td><input style="background-color: #FF8383" type="text" size="24" readonly="readonly" name="empName" value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'></td>
														<td><input style="background-color: #FF8383" type="text" size="8" readonly="readonly" name="salDays<%=k%>" id='<%=rows[k][0]+"saldays"%>' value="<%=rows[k][rows[0].length-3] %>"></td>
														<%
															i = 3;
														%>
														<s:iterator value="creditHeader">
															<td><input style="background-color: #FF8383; text-align: right" type="text" 	size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
																id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();" onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)">
															</td>
														<%
															i++;
														%>
														</s:iterator>
														<td><input style="background-color: #FF8383; text-align: right" type="text" size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"	name="totalCredit<%=k%>" value="<%=rows[k][i] %>"></td>
														<%
															int dStart = i;
														%>
														<s:iterator value="debitHeader">
															<%
																i++;
															%>
															<td align="center" width="40"><input style="background-color: #FF8383; text-align: right" type="text" size="4" name="<%=k%>" value="<%=rows[k][i] %>"
																id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();" onkeyup="sumDebits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)">
															</td>
														</s:iterator>
														<%
															int iVal = i;
															iVal++;
														%>
														<td><input style="background-color: #FF8383; text-align: right" type="text"	size="6" readonly="readonly" name="totalDebit<%=k%>" value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>"></td>
														<%
															iVal++;
														%>
														<td><input style="background-color: #FF8383; text-align: right" type="text" size="6" readonly="readonly" name="netPay<%=k%>" value="<%=rows[k][i+2] %>" id="<%=rows[k][0]+"c"+iVal%>"></td>
														<td><input class="classCheck" style="background-color: #FF8383" type="checkbox" name="onHoldFlag" id='<%=rows[k][0]+"onHoldChk"%>' value="<%= String.valueOf(rows[k][0])%>" /></td>
													</tr>
													<script>
																creDebCount = "<%=iVal%>";
													</script>
											<%
												} else if (String.valueOf(rows[k][rows[0].length - 2]).equals("Y")) {
											%>
													<tr>
														<td><input type="hidden" name="onholdEmp" id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" /> 
															<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"	id="<%=rows[k][0]%>"> 
															<input style="background-color: #C0EDFE" type="text" readonly="readonly" size="8" name="tokenNo" value="<%=rows[k][1] %>" id='<%=rows[k][0]+"empToken"%>'></td>
														<td><input style="background-color: #C0EDFE" type="text" size="24" readonly="readonly" name="empName" value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'></td>
														<td><input style="background-color: #C0EDFE" type="text" size="8" readonly="readonly" name="salDays<%=k%>" id='<%=rows[k][0]+"saldays"%>' value="<%=rows[k][rows[0].length-3] %>"></td>
														<%
															i = 3;
														%>
														<s:iterator value="creditHeader">
															<td><input
																style="background-color: #C0EDFE; text-align: right" type="text" size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
																id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();" onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)">
															</td>
														<%
															i++;
														%>
														</s:iterator>
														<td><input style="background-color: #C0EDFE; text-align: right" type="text" size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>" name="totalCredit<%=k%>" value="<%=rows[k][i] %>"></td>
														<%
															int dStart = i;
														%>
														<s:iterator value="debitHeader">
														<%
															i++;
														%>
														<td align="center" width="40"><input style="background-color: #C0EDFE; text-align: right" type="text" size="4" name="<%=k%>" value="<%=rows[k][i] %>"
																id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();" onkeyup="sumDebits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)">
														</td>
														</s:iterator>
														<%
															int iVal = i;
															iVal++;
														%>
														<td><input style="background-color: #C0EDFE; text-align: right" type="text" size="6" readonly="readonly" name="totalDebit<%=k%>" value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>"></td>
														<%
															iVal++;
														%>
														<td><input style="background-color: #C0EDFE; text-align: right" type="text" size="6" readonly="readonly" name="netPay<%=k%>" value="<%=rows[k][i+2] %>" id="<%=rows[k][0]+"c"+iVal%>"> </td>
														<td><input class="classCheck" style="background-color: #C0EDFE" type="checkbox" name="onHoldFlag" id='<%=rows[k][0]+"onHoldChk"%>' value="<%= String.valueOf(rows[k][0])%>" /></td>
													</tr>
													<script>
															creDebCount = "<%=iVal%>";
													</script>
												<%
													} else {
												%>
													<tr>
														<td><input type="hidden" name="onholdEmp" id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" />
															<input type="hidden" name="emp_id" value="<%=rows[k][0] %>" id="<%=rows[k][0]%>"> 
															<input style="background-color: #F2F2F2" type="text" size="8" readonly="readonly" name="tokenNo" value="<%=rows[k][1] %>" id='<%=rows[k][0]+"empToken"%>'></td>
														<td><input style="background-color: #F2F2F2" type="text" size="24" readonly="readonly" name="empName" value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'></td>
														<td><input style="background-color: #F2F2F2" type="text" size="8" readonly="readonly" name="salDays<%=k%>"  id='<%=rows[k][0]+"saldays"%>' value="<%=rows[k][rows[0].length-3] %>"></td>
														<%
															i = 3;
														%>
														<s:iterator value="creditHeader">
															<td><input type="text" size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>" id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();" 
															onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)" style="text-align: right"></td>
														<%
															i++;
														%>
														</s:iterator>
														<td><input style="background-color: #F2F2F2; text-align: right" type="text" size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"
															name="totalCredit<%=k%>" value="<%=rows[k][i] %>" style="text-align: right"></td>
														<%
															int dStart = i;
														%>
														<s:iterator value="debitHeader">
														<%
															i++;
														%>
														<td><input type="text" size="4" name="<%=k%>" value="<%=rows[k][i] %>" id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
																onkeyup="sumDebits(<%=d.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)" style="text-align: right"></td>
														</s:iterator>
														<%
															int iVal = i;
															iVal++;
														%>
														<td><input style="background-color: #F2F2F2; text-align: right" type="text" size="6" readonly="readonly" name="totalDebit<%=k%>"
															value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>" style="text-align: right"></td>
														<%
															iVal++;
														%>
														<td><input style="background-color: #F2F2F2; text-align: right" type="text" size="6" readonly="readonly" id="<%=rows[k][0]+"c"+iVal%>"	name="netPay<%=k%>" value="<%=rows[k][i+2] %>"></td>
														<input type="hidden" name="onHoldChkHid" /> 
														<td><input class="classCheck" type="checkbox" style="background-color: #F2F2F2" name="onHoldFlag" id='<%=rows[k][0]+"onHoldChk"%>' value="<%= String.valueOf(rows[k][0])%>" /></td>
													</tr>
													<script>
														creDebCount = "<%=iVal%>";
													</script>
												<%
													}
												%>
												<script>
													eCode[<%=k%>] = "<%=String.valueOf(rows[k][0])%>";
												</script>
								<%
												}
										} catch (Exception e) {
			
										}
								%>
							</table>
							</td>
						</tr> 
						<tr>
							<td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
						</tr>
					</table>
					<table width="100%" class="formbg">
						<tr>
							<td style="color: red;">Note:</td>
						</tr>
						<tr>
							<td style="color: red;">Records in Blue colour represent employees 'on hold'.</td>
						</tr>
						<tr>
							<td style="color: red;">Records in Red colour indicate change in eligible salary days. Please recalculate these records.</td>
						</tr>
					</table>
			</td>
		</tr>
	</s:if>
	<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td><s:submit action="UMC_SalaryProcess_callForEdit" value="Back" cssClass="token" title="Back" />
						<s:if test="dataFlag"> <input type="button" class="save" theme="simple" onclick="return checkSave('Save');" value=" Save" />
						<input type="button" class="reset" theme="simple" onclick="return resetFun();" value=" Reset" /></s:if></td>
					</tr>
				</table>
			</td>
		</tr> 
		
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td></td>
					</tr>
				</table>
			</td>
		</tr> 
	</table>
</s:form>

<script type="text/javascript">
function resetFun(){
		document.getElementById('paraFrm').action="UMC_SalaryProcess_resetRecords.action";
		document.getElementById('paraFrm').submit();
}
function callPage(id,totalPage){
	var con;
	var process=document.getElementById('hdProcess').value
 	var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
	if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
	}
	if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
	}
	document.getElementById('hdPage').value=id;
	if(process=="Pr"){
		if(ledgerStatus=="SAL_FINAL" ||ledgerStatus=="ATTN_UNLOCK"){
			document.getElementById('paraFrm').action="UMC_SalaryProcess_getEditableSalary.action";
			document.getElementById('paraFrm').submit();
		}else{
	 		//con=confirm("Do you want to save the page and proceed to the next page");
	 		document.getElementById("confirmationDiv").style.visibility = 'visible';
		 	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
	}else{
		if(ledgerStatus=="SAL_FINAL" || ledgerStatus=="ATTN_UNLOCK"){
			document.getElementById('paraFrm').action="UMC_SalaryProcess_getEditableSalary.action";
			document.getElementById('paraFrm').submit();
		}else{
		 	//con=confirm("Do you want to save the page and proceed to the next page");
		 	document.getElementById("confirmationDiv").style.visibility = 'visible';
		 	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 	+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
	}
}
onload();
function onload(){
	if(document.getElementById("paraFrm_dataFlag").value)
				gridScroll();
	else{
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
	}			
}
function gridScroll(){
	try{
		enableBlockDiv();
		myST = superTable("thetable", { fixedCols : 3,rightCols:2,viewCols:7});
		disableBlockDiv();
	}catch(e){
		disableBlockDiv();
	}
}
function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
}
function proceedWithSave(){
	try{
			document.getElementById("confirmationDiv").style.visibility = 'hidden';
			enableBlockDiv();
			document.getElementById('paraFrm').action="UMC_SalaryProcess_saveSalaryAfterRecalculate.action";
			document.getElementById('paraFrm').submit();
		}catch(e){
			alert(e);
		}
}
function proceedWithoutSave(){
	try{
			enableBlockDiv();
			document.getElementById("confirmationDiv").style.visibility = 'hidden';
			document.getElementById('paraFrm').action="UMC_SalaryProcess_getEditableSalary.action";
			document.getElementById('paraFrm').submit();
		}catch(e){
			alert(e);
		}
}
function sumCredits(cLen,k,colLen,empId) {
		var creditAmount=0;
		var debitAmount=0;
		var j=3;
		for(var i=0;i<cLen;i++){
			var values=document.getElementById(empId+"c"+j).value;
			if(values ==''){
 		 		values =0;
 			 }
 			 j++;
			creditAmount=eval(creditAmount)+eval((values*100)/100);
		}	
		debitAmount = document.getElementById(empId+"c"+eval(colLen-1)).value
		document.getElementById(empId+"c"+eval(cLen+3)).value=creditAmount;
		document.getElementById(empId+"c"+colLen).value=eval(creditAmount*100/100)-eval(debitAmount*100/100); 
}
function sumDebits(cLen,k,colLen,empId,id,dLen) {
		var creditAmount=0;
		var debitAmount=0;
		var j=id;
		for(var i=0;i<dLen;i++){
			var values=document.getElementById(empId+"c"+j).value;
			if(values ==''){
 		 		values =0;
 			 }
 		 j++;
		debitAmount=eval(debitAmount)+eval((values*100)/100);
	}	
	creditAmount = document.getElementById(empId+"c"+eval(id-1)).value
	document.getElementById(empId+"c"+eval(colLen-1)).value=debitAmount; 
	document.getElementById(empId+"c"+colLen).value=eval(creditAmount*100/100)-eval(debitAmount*100/100); 
}
function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";   
			}catch(e){
			//alert(" disableBlockDiv -- "+e);
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
			}
}
function enableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";   
		}catch(e){
		//alert(" enableBlockDiv -- "+e);
		}
}
function selectAll(){
	      if(document.getElementById("selectId").checked){  
		       for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked = true;
				}
	      }else{
	      		for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked = false;
				}
	      }
}
function checkRecordRecal()
	{
		var cnt = 0;
		var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		try{
			if(ledgerStatus=="SAL_FINAL"){
				alert("Salary is already Locked");
				for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			}else if(ledgerStatus=="ATTN_UNLOCK"){
				alert("Attendance is unlocked");
				for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			}else{
			//alert(1);
				retrieveURLRecalNew('UMC_SalaryProcess_recalSalary.action?','paraFrm');
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
				
				for(var i = 0; i < eCode.length; i++)
				{
				try{
					if(document.getElementById(eCode[i]+"onHoldChk").checked)
						{
							if(document.getElementById("onholdEmp"+i).value=="Y"){
								document.getElementById(eCode[i]+"saldays").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"empToken").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"empName").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"onHoldChk").style.background = "#C0EDFE";
								for(var j =3;j<=creDebCount;j++){
									document.getElementById(eCode[i]+"c"+j).style.background = "#C0EDFE";
								}
							}
							else{
								document.getElementById(eCode[i]+"saldays").style.background = "";
								document.getElementById(eCode[i]+"empToken").style.background = "";
								document.getElementById(eCode[i]+"empName").style.background = "";
								document.getElementById(eCode[i]+"onHoldChk").style.background = "";
								for(var j =3;j<=creDebCount;j++){
									document.getElementById(eCode[i]+"c"+j).style.background = "";
								}
							}
						}
					}
					catch(e){
				
					}
					if(document.getElementById(eCode[i]+"onHoldChk").checked)
					{				
						cnt =cnt +1;
					}
					if(document.getElementById(eCode[i]+"onHoldChk").checked)
					{				
						document.getElementById(eCode[i]+"onHoldChk").checked =false;
					}
				}
				if(cnt==0)
				{
					alert("Please select atleast one employee");
					return false;
			}
		}
			//document.getElementById("selectId").checked=false;
			disableBlockDiv();
		}catch(e){
			
		}
}

function callEditableSalary(){
	var fields=["paraFrm_divisionName","paraFrm_branchViewName","paraFrm_departmentViewName"];
	var lables=["division","branch","department"];
	var flags=["select","select","select"];
	//if(!validateBlank(fields,lables,flags)){
		//return false;
	//}
	
	document.getElementById('paraFrm').action='UMC_SalaryProcess_getEditableSalary.action';
	document.getElementById('paraFrm').submit();
}
function removeOnHold()
	{
		var cnt = 0;
		var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		try{
			if(ledgerStatus=="SAL_FINAL"){
				alert("Salary is locked!");
				for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			}else if(ledgerStatus=="ATTN_UNLOCK"){
				alert("Attendance is unlocked!");
				for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			}else{
				var ledgerCode=document.getElementById("paraFrm_ledgerCode").value;
				//alert('ledgerCode --'+ledgerCode);
				retrieveURLRemoveOnHoldNew('UMC_SalaryProcess_removeOnHold.action?','paraFrm',ledgerCode);
				for(var i = 0; i < eCode.length; i++)
					{
						try{
							if(document.getElementById(eCode[i]+"onHoldChk").checked)
							{		
								document.getElementById(eCode[i]+"saldays").style.background = "";
								document.getElementById(eCode[i]+"empToken").style.background = "";
								document.getElementById(eCode[i]+"empName").style.background = "";		
								document.getElementById(eCode[i]+"onHoldChk").style.background = "";
								for(var j =3;j<=creDebCount;j++){
									document.getElementById(eCode[i]+"c"+j).style.background = "";
								}
								cnt =cnt +1;
								document.getElementById("onholdEmp"+i).value="N";
							}
						}catch(e){
					
						}
						if(document.getElementById(eCode[i]+"onHoldChk").checked)
						{				
							document.getElementById(eCode[i]+"onHoldChk").checked =false;
						}
				}
				if(cnt==0)
				{	
					disableBlockDiv();
					alert("Please select atleast one employee!");
					return false;
				}
			}
			//document.getElementById("selectId").checked=false;
		}catch(e)
		{
			
		}
}	

function checkSave(id){
 	 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
		}
		else{
			if(id=="Save"){
				enableBlockDiv();
				document.getElementById('paraFrm').action="UMC_SalaryProcess_saveSalaryAfterRecalculate.action";
				document.getElementById('paraFrm').submit();
			}
			if(id=="Lock"){
			 	con=confirm('Do you really want to lock the salary?');
			 	if(con){
			 	enableBlockDiv();
				document.getElementById('paraFrm').action="UMC_SalaryProcess_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}
		}
 }

function callOnhold()
	{
			
		var cnt = 0;
		 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		// alert(ledgerStatus);
		try{
		if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else{
			
			var ledgerCode=document.getElementById("paraFrm_ledgerCode").value;
			//alert(ledgerCode);
			retrieveURLOnHoldNew('UMC_SalaryProcess_onholdSave.action?','paraFrm',ledgerCode);
			for(var i = 0; i < eCode.length; i++)
			{
			
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
					try{
						document.getElementById(eCode[i]+"saldays").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"empToken").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"empName").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"onHoldChk").style.background = "#C0EDFE";
						for(var j =3;j<=creDebCount;j++){
							document.getElementById(eCode[i]+"c"+j).style.background = "#C0EDFE";
						}
					}
					catch(e){
					//alert(e);
					}
					cnt =cnt +1;
					document.getElementById("onholdEmp"+i).value="Y";
				}
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
				
			}
			if(cnt==0)
			{	disableBlockDiv();
				alert("Please select atleast one employee");
				return false;
			}
		}
		
		//document.getElementById("selectId").checked=false;
		
		}
		catch(e)
		{
			
		}
	}
</script>