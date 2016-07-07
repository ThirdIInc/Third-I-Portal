<!-- @author: Prajakta Bhandare -->
<!-- @ Date: 11 Jan 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.paradyne.lib.Utility"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CTCDetail" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="editFlag" />
	<s:hidden name="empId" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="middle">
			<fieldset><legend class="legend"> <img
				src="../pages/mypage/images/icons/profile_ctcinfo.png" width="16"
				height="16" />&nbsp;&nbsp;CTC Information </legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td></td>
										</tr>
										<tr>
											<td height="0">
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">
												<!--if edit flag true-->
												<s:if test="editFlag">
											<tr align="right">
											
												<s:if test="updateFlg"><!--if update flag true-->
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if><!--end if update flag true-->
												
												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<!--end if edit flag true-->
										<s:else>
											<tr align="right">
												<s:if test="updateFlg"><!--if update flag true-->
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													
													<td width="1%">|</td>
													</s:if><!--end if update flag true-->
												
												<s:if test="isNotGeneralUser">
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right" ><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td>|</td>
												</s:if>
											</tr>
										</s:else><!--end of else-->
	
											</table>
											</td>
										</tr>
										<tr>
											<td height="1" bgcolor="#cccccc" class="style1"></td>
										</tr>
										<!-- Display editable mode to edit record -->
										<s:if test="editFlag"><!--if edit flag true-->
											<tr>
												<td>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
														<fieldset><legend class="legend1">CTC
														Particulars</legend>
														<table width="100%" border="0" cellpadding="2"
															cellspacing="3">
															<tr>
																<td colspan="11">
																<table width="100%" border="0" cellpadding="2"
																	cellspacing="3">
																	<tr>
																		<td width="22%"><label name="employee"
																			id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1" colspan="7"><s:property
																			value="empToken" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property
																			value="empName" /></td>

																	</tr>
																	<tr>
																		<td width="20%"><label name="branch" id="branch"
																			ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empCenter" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="22%"><label name="department"
																			id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="20%" class="text1"><s:property
																			value="empDeptName" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="designation"
																			id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empRank" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="22%"><label name="emp.grade"
																			id="emp.grade" ondblclick="callShowDiv(this);"><%=label.get("emp.grade")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="20%" class="text1"><s:property
																			value="empGradeName" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="doj" id="doj"
																			ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empJoinDate" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="22%"><label name="accNo" id="accNo"
																			ondblclick="callShowDiv(this);"><%=label.get("accNo")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="20%" class="text1"><s:property
																			value="empAccNo" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="PAN.no" id="PAN.no"
																			ondblclick="callShowDiv(this);"><%=label.get("PAN.no")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empPANNo" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="22%"></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%"></td>
																		<td width="20%" class="text1"></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label class="set"
																			id="applicableFormula" name="applicableFormula"><%=label.get("applicableFormula")%></label>
																		</td>
																		<td width="2%" class="star">*</td>
																		<td width="2%">:</td>
																		<td width="22%"><s:textfield theme="simple"
																			readonly="true" name="formula" size="25" /> <s:hidden
																			name="formulaId" /></td>
																		<td width="2%"><input type="button"
																			name="Submit3" class="button" value="..."
																			onclick="frmAction();clearFields('paraFrm_salGradeId', 'paraFrm_salGradeName');" /></td>
																		<td width="4%">&nbsp;</td>
																		<td width="20%"><label class="set" id="grsAmount"
																			name="grsAmount" ondblclick="callShowDiv(this);"><%=label.get("grsAmount")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%"><s:textfield theme="simple"
																			name="grsAmt" onkeypress="return numbersWithDot();"
																			size="25" maxlength="10" /></td>
																		<td width="2%"><s:submit cssClass="token"
																			name="calculate" value="Calculate"
																			onclick="return callCal();" /></td>
																	</tr>
																	<tr>
																		<td width="20%"><label class="set" id="salGrade"
																			name="salGrade" ondblclick="callShowDiv(this);"><%=label.get("salGrade")%></label></td>
																		<td width="2%" class="star">*</td>
																		<td width="2%">:</td><s:hidden name="salGradeId" />
																		<td width="22%"><s:textfield readonly="true"
																			size="25" name="salGradeName" /></td>
																		<td width="2%"><input type="button"
																			name="Submit3" class="button" value="..."
																			onclick="gradeAction();clearFields('paraFrm_formulaId', 'paraFrm_formula');" /></td>
																		<td width="4%">&nbsp;</td>
																		<td colspan="2"></td>
																	</tr>
																	<tr>
																		<td width="20%" colspan="1"><label class="set"
																			id="view" name="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label>
																		</td>
																		<td width="2%"></td>
																		<td width="2%">:</td>
																		<td width="22%"><s:select name="incrementPeriod"
																			headerKey="" headerValue="   --Select--  "
																			list="%{incrementHistoryMap}" cssStyle="width:129" /></td>
																		<td width="2%"><input type="button"
																			value="  View  " Class="token" onclick="callView();"
																			id="ctrlShow" /></td>
																		<td width="4%">&nbsp;</td>
																		<td colspan="5"></td>
																	</tr>
																	<%
																		int i = 0;
																		int i2 = 0;
																	%>
																	<%!int p = 0, t = 0;%>
																	<tr>
																		<td bgcolor="#EEF4FB" width="50%" colspan="5"
																			align="center"><label class="set"
																			id="salary.header" name="salary.header"
																			ondblclick="callShowDiv(this);"><%=label.get("salary.header")%></label></td>
																		<td bgcolor="#EEF4FB" width="20%" colspan="2"
																			align="center"><label class="set" id="period"
																			name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
																		<td bgcolor="#EEF4FB" width="28%" colspan="4"
																			align="center"><label class="set" id="amount"
																			name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
																	</tr>
																	<!--ietrator to display salary header-->
																	<s:iterator value="salaryHdr">
																		<tr>
																			<td class="text1" width="50%" colspan="5"><s:property
																				value="ctcNameItt" /> <s:hidden name="ctcNameIdItt" />
																			&nbsp; <s:hidden name="ctcNameItt" /> <input
																				type="hidden" name='period'
																				value="<s:property value='ctcPeriodItt' />"
																				id='<%="period"+i%>' /></td>
																			<td class="text1" width="20%" colspan="2"><s:property
																				value="ctcPeriodItt" /></td>
																			<td class="text1" width="28%" align="right"
																				colspan="4"><input type="text"
																				style="text-align: right;" size="10" maxlength="15"
																				name="amount" id="amount<%=i%>"
																				onkeyup="sumAmt('period',<%=i%>)"
																				value="<s:property value="ctcAmountItt" />"
																				onkeypress="return numbersWithDot();" />&nbsp;</td>
																		</tr>
																		<%
																			p++;
																			i++;
																		%>
																	</s:iterator><!--end of ietrator to display salary header-->
																</table>
																</td>
															</tr>
															<s:if test="noData">
																<tr>
																	<td colspan="11" align="center"><font color="red">No
																	data to display</font></td>
																</tr>
															</s:if>
															<%
																t = p;
																p = 0;
															%>
<!--															<tr>-->
<!--																<td height="1px" colspan="11"><hr style="height: 0.2;background-color: #ccc"></td>-->
<!--															</tr>-->
															<!-- TOTAL MONTHLY SALARY -->
															<tr align="right">
																<td width="85%" colspan="9" align="right"><label
																	class="set" id="totMonth" name="totMonth"
																	ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
																</td>
																<td width="15%"><s:textfield name="totalAmt" theme="simple"
																	size="10" readonly="true"
																	cssStyle="background-color: #F2F2F2;text-align:right;" />
																</td>
																<td></td>
															</tr>
															<!-- TOTAL ANNUAL SALARY -->
															<tr align="right">

																<td width="85%" colspan="9" align="right"><label
																	class="set" id="totAnnual" name="totAnnual"
																	ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>
																</td>
																<td width="15%"> <s:textfield name="annualAmt" theme="simple"
																	size="10" readonly="true"
																	cssStyle="background-color: #F2F2F2;text-align:right;" />
																</td>
																<td></td>
															</tr>
															<!-- CTC -->
															<tr align="right">

																<td width="85%" colspan="9" align="right"><label
																	class="set" id="ctcamt1" name="ctcamt"
																	ondblclick="callShowDiv(this);"><%=label.get("ctcamt")%></label>
																</td>
																<td width="15%"><s:textfield name="ctc" theme="simple"
																	readonly="true" size="10"
																	cssStyle="background-color: #F2F2F2;text-align:right;" />
																</td>
																<td></td>
															</tr>

														</table>
														</fieldset>
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</s:if><!--if edit flag true-->
										<!--end of Display editable mode to edit record -->
										<!-- Display record to view only-->
										<s:else>
											<tr>
												<td>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
														<fieldset><legend class="legend1">CTC
														Details</legend>
														<table width="100%" border="0" cellpadding="2"
															cellspacing="3">
															<tr>
																<td colspan="11">
																<table width="100%" border="0" cellpadding="2"
																	cellspacing="3">
																	<tr>
																		<td width="20%"><label name="employee"
																			id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1" colspan="7"><s:property
																			value="empToken" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property
																			value="empName" /></td>

																	</tr>
																	<tr>
																		<td width="20%"><label name="branch" id="branch"
																			ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empCenter" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="20%"><label name="department"
																			id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empDeptName" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="designation"
																			id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empRank" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="20%"><label name="emp.grade"
																			id="emp.grade" ondblclick="callShowDiv(this);"><%=label.get("emp.grade")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empGradeName" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="doj" id="doj"
																			ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empJoinDate" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="20%"><label name="accNo" id="accNo"
																			ondblclick="callShowDiv(this);"><%=label.get("accNo")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empAccNo" /></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%"><label name="PAN.no" id="PAN.no"
																			ondblclick="callShowDiv(this);"><%=label.get("PAN.no")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="22%" class="text1"><s:property
																			value="empPANNo" /></td>
																		<td width="2%"></td>
																		<td width="4%">&nbsp;</td>
																		<td width="20%"></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%"></td>
																		<td width="22%" class="text1"></td>
																		<td width="2%"></td>
																	</tr>
																	<tr>
																		<td width="20%" colspan="1"><label class="set"
																			id="view" name="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label>
																		</td>
																		<td width="2%"></td>
																		<td width="2%">:</td>
																		<td width="22%"><s:select name="incrementPeriod"
																			headerKey="" headerValue="   --Select--  "
																			list="%{incrementHistoryMap}" cssStyle="width:129" /></td>
																		<td width="2%"><input type="button"
																			value="  View  " Class="token" onclick="callView();"
																			id="ctrlShow" /></td>
																		<td width="4%">&nbsp;</td>
																		<td colspan="5"></td>
																	</tr>
																	<tr>
																		<td bgcolor="#EEF4FB" width="50%" colspan="5"
																			align="center"><label class="set"
																			id="salary.header" name="salary.header"
																			ondblclick="callShowDiv(this);"><%=label.get("salary.header")%></label></td>
																		<td bgcolor="#EEF4FB" width="20%" colspan="2"
																			align="center"><label class="set" id="period"
																			name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
																		<td bgcolor="#EEF4FB" width="28%" colspan="4"
																			align="center"><label class="set" id="amount"
																			name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>

																	</tr>
																	<!-- iterator to display salary header -->
																	<s:iterator value="salaryHdr">
																		<tr>
																			<td class="text1" width="50%" colspan="5"
																				class="text1"><s:property value="ctcNameItt" />
																			<s:hidden name="ctcNameIdItt" /> &nbsp; <s:hidden
																				name="ctcNameItt" /> <input type="hidden"
																				name='period'
																				value="<s:property value='ctcPeriodItt' />" /></td>
																			<td class="text1" width="20%" colspan="2"
																				class="text1"><s:property value="ctcPeriodItt" /></td>
																			<td class="text1" width="28%" align="right"
																				colspan="4" class="text1"><s:property
																				value="ctcAmountItt" /></td>
																		</tr>
																	</s:iterator><!--end iterator to display salary header -->
																	<s:if test="noData">
																		<tr>
																			<td colspan="11" align="center"><font
																				color="red">No data to display</font></td>
																		</tr>
																	</s:if>
<!--																	<tr>-->
<!--																		<td height="1px" colspan="11">-->
<!--																		<hr style="height: 0.2; background-color: #ccc">-->
<!--																		</td>-->
<!--																	</tr>-->
																	<tr align="right">
																		<td width="82%" colspan="10" align="right"><label
																			class="set" id="totMonth" name="totMonth"
																			ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
																		</td>
																		<td class="text1"><s:property value="totalAmt" /></td>
																	</tr>
																	<!-- TOTAL ANNUAL SALARY -->
																	<tr align="right">
																		<td width="82%" colspan="10" align="right"><label
																			class="set" id="totAnnual" name="totAnnual"
																			ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>
																		</td>
																		<td class="text1"><s:property value="annualAmt" />
																		</td>
																	</tr>
																	<!-- CTC -->
																	<tr align="right">
																		<td width="82%" colspan="10" align="right"><label
																			class="set" id="ctcamt1" name="ctcamt"
																			ondblclick="callShowDiv(this);"><%=label.get("ctcamt")%></label>
																		</td>
																		<td class="text1"><s:property value="ctc" /></td>
																	</tr>
																</table>
																</td>
															</tr>
														</table>
														</fieldset>
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</s:else>
										<!-- Display record to view only-->
										<tr height="10"></tr>
										<tr>
											<td height="1" bgcolor="#cccccc" class="style1"></td>
										</tr>

										<tr>
											<td height="0">
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">
										<s:if test="editFlag"><!-- if edit flag true -->
											<tr align="right">
												<s:if test="updateFlg"><!-- if update flag true -->
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if><!--end if update flag true -->
												
												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td>|</td>
											</tr>
										</s:if><!--end if edit flag true -->
										<s:else>
											<tr align="right">
												<s:if test="updateFlg"><!-- if edit flag true -->
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if><!--end if edit flag true -->
												<s:if test="isNotGeneralUser"><!-- if notgeneraluser flag true -->
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td  align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td>|</td>
												</s:if><!--end if notgeneraluser flag true -->
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
					</table>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</div>
</s:form>
<script>
//function to edit record
function editFun(){
try{
	document.getElementById('paraFrm').target = "_self";
  	document.getElementById("paraFrm").action="CTCDetail_edit.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

//function to search employee
function searchFun(){
try{
javascript:callsF9(500,325,'CTCDetail_f9empaction.action');
}catch(e){}
}

//function to cancel current operation
function cancelFun(){
try{
document.getElementById('paraFrm').target = "_self";
  	document.getElementById("paraFrm").action="CTCDetail_cancel.action";
	document.getElementById("paraFrm").submit();
}catch(e){}
}

//function to delete
function deleteFun(){
	var con=confirm('Do you want to delete the record(s) ?');
	if(con){//if con
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="CTCDetail_delete.action";
		document.getElementById('paraFrm').submit();
	}//end if con
}
//function to save record
function saveFun(){
try{
document.getElementById('paraFrm').target = "_self";
  	document.getElementById("paraFrm").action="CTCDetail_save.action";
	document.getElementById("paraFrm").submit();
}catch(e){}
}

//function to search formula
function frmAction(){
try{
javascript:callsF9(500,325,'CTCDetail_f9FormAction.action');
}catch(e){}
}

//function to search grade
function gradeAction(){
try{

javascript:callsF9(500,325,'CTCDetail_f9GradeAction.action');
}catch(e){}
}


//function to view salary header 
function callView(){
try{
	var incPeriod = document.getElementById('paraFrm_incrementPeriod').value; 
	if(incPeriod==""){//if period null
		alert("Please select "+document.getElementById('view').innerHTML.toLowerCase());
		return false;
	}//end if period null
	else if(incPeriod=="N/A"){
		alert("Increment period not available");
		return false;
	}//end of elseif
	else{
	 	document.getElementById("paraFrm").action="CTCDetail_viewIncrementHistory.action"; 
	  	document.getElementById("paraFrm").submit();
  	}//end of else
}catch(e){}
}


//function to round number
function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}

//function to calculate ctc
function callCal(){
try{
	var grossamount=document.getElementById('paraFrm_grsAmt').value
	var formula=document.getElementById('paraFrm_formulaId').value
	
	if(formula==""){//if formula null
		alert("please select "+document.getElementById('applicableFormula').innerHTML.toLowerCase());				   
		document.getElementById('paraFrm_formula').focus();
		return false;
	}//end if formula null
	if(grossamount=="0" || grossamount==""){//if grossAmount null
		alert("please enter "+document.getElementById('grsAmount').innerHTML.toLowerCase());
		document.getElementById('paraFrm_grsAmt').focus();
		return false;
	}//end if grossAmount null
	document.getElementById("paraFrm").action="CTCDetail_calCtc.action"; 
	 document.getElementById("paraFrm").submit();
}catch(e){}
	
}

//function to sum amount
function sumAmt(p,s){
try{
	var value=document.getElementById('amount'+s).value;
	if(value!='.' && isNaN(value)){
		alert("Only single dot is allowed");
		document.getElementById('amount'+s).value=0;
		value=0;
	}
	var totalrow = <%=t%> ;
	var a='amount';
	var count=0;
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	
	for(var row = 0;row < totalrow ;row++){
	if(document.getElementById(p+row).value=="Monthly"){//if monthly
		var values=document.getElementById(a+row).value;
		if(values =="" || values=='.'){
			values =0;
		}
		values =eval(values*100/100);
		count=eval(count)+eval(values);
		count1=eval(count1)+eval(values)*12;
	}//end if monthly
	else if(document.getElementById(p+row).value=="Half Yearly"){//if Half Yearly
			var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
			values =eval(values*100/100);
			count2=eval(count2)+(eval(values)*2);
	}//end if Half Yearly
	else if(document.getElementById(p+row).value=="Annually"){//if Annually
		var values=document.getElementById(a+row).value;
		if(values =="" || values=='.'){
			values =0;
			}
			values =eval(values*100/100);
			count3=eval(count3)+eval(values);
		}//end if Annually
		 else if(document.getElementById(p+row).value=="Quarterly"){//if Quarterly
			var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
		}//end if Quarterly
	}
	var amount = 0;
	document.getElementById('paraFrm_totalAmt').value=roundNumber(count,2);	
	document.getElementById('paraFrm_annualAmt').value=roundNumber(count1+count2+count3+count4,2);
}catch(e){}
}

//function to clear fields
function clearFields(field1, field2){
	document.getElementById(field1).value="";
	document.getElementById(field2).value="";
}	

//function to reset fields
function resetFun(){
try{
	document.getElementById('paraFrm').target = "_self";
  	document.getElementById("paraFrm").action="CTCDetail_resetFields.action";
	document.getElementById("paraFrm").submit();
}catch(e){}
}
</script>