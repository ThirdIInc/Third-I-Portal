<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="SalaryDetail" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="checkTabFlag" />
	<s:hidden name="editFlag" />
	<s:hidden name="viewFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="empName" />
	<s:hidden name="uploadFileName" />
	<s:hidden name="empID" id="paraFrm_empID" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />

	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="11">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"> <img
						src="../pages/mypage/images/icons/profile_accoutinfo.png"
						width="16" height="16" /> &nbsp;&nbsp;Account Information </legend>
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
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="3%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
													</s:if>
												<s:if test="isNotGeneralUser">
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td>|</td>
												</s:if>
											</tr>
										</s:if>
										<s:else>
											<tr>
												<s:if test="updateFlg">
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:if>
												<td width="18%"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" /></td>
												<td width="18%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td>|</td>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<!-- -------if------------------------------------------- -->
								<s:if test="editFlag">
									<tr>
										<td>
										<fieldset><legend class="legend1">
										Statutory Details </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="ESIC.number"
													id="ESIC.number" ondblclick="callShowDiv(this);"><%=label.get("ESIC.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="esciNumber" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="20%"><label name="PAN.number" id="PAN.number"
													ondblclick="callShowDiv(this);"><%=label.get("PAN.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><label><s:property
													value="noPAN" /></label></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="23%"><label name="gratuity.id"
													id="gratuity.idr" ondblclick="callShowDiv(this);"><%=label.get("gratuity.id")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="gratuityId" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="emp.overtime"
													id="emp.overtime" ondblclick="callShowDiv(this);"><%=label.get("emp.overtime")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:checkbox
													theme="simple" name="sOTflag"
													onclick="return checkAppl('sOTflag');" disabled="true" /></td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td>
										<fieldset><legend class="legend1"> PF
										Information </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<s:hidden name="oEPFflag" />
												<s:hidden name="oGPFflag" />
												<s:hidden name="oVPFflag" />
												<s:hidden name="oPFTflag" />
												<s:hidden name="oOTflag" />
												<s:if test="oEPFflag">
													<td width="23%"><label name="emp.epf" id="emp.epf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.epf")%></label></td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%"><s:checkbox theme="simple"
														name="sEPFflag" onclick="return checkAppl('sEPFflag');"
														disabled="true" /></td>
												</s:if>
												<s:else>
													<s:hidden name='sEPFflag' value='false' disabled="true" />
												</s:else>
												<s:if test="oGPFflag">
													<td width="2%"></td>
													<td width="4%">&nbsp;</td>
													<td width="23%"><label name="emp.gpf" id="emp.gpf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.gpf")%>
													</label></td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%" valign="top" class="text1"><s:checkbox
														theme="simple" name="sGPFflag"
														onclick="return checkAppl('sGPFflag');" disabled="true" /></td>
													<td width="2%"></td>
												</s:if>
												<s:else>
													<s:hidden name='sGPFflag' value='false' disabled="true" />
												</s:else>
											</tr>
											<tr>
												<s:if test="oVPFflag">
													<td width="23%"><label name="emp.vpf" id="emp.vpf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.vpf")%></label></td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%" class="text1"><s:checkbox
														theme="simple" name="sVPFflag"
														onclick="return checkAppl('sVPFflag');" disabled="true" /></td>
												</s:if>
												<s:else>
													<s:hidden name='sVPFflag' value='false' disabled="true" />
												</s:else>
												<s:if test="oPFTflag">
													<td width="2%"></td>
													<td width="4%">&nbsp;</td>
													<td width="23%"><label name="emp.pft" id="emp.pft"
														ondblclick="callShowDiv(this);"><%=label.get("emp.pft")%></label>
													</td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%" valign="top" class="text1"><s:checkbox
														theme="simple" name="sPFTflag"
														onclick="return checkAppl('sPFTflag');" disabled="true" /></td>
													<td width="2%"></td>
												</s:if>
												<s:else>
													<s:hidden name='sPFTflag' value='false' disabled="true" />
												</s:else>
											</tr>
											<tr>
												<td width="23%"><label name="PF.number" id="PF.number"
													ondblclick="callShowDiv(this);"><%=label.get("PF.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property value="noGPF" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="pfJoin.date"
													id="pfJoin.date" ondblclick="callShowDiv(this);"><%=label.get("pfJoin.date")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="pfJoinDate" /></td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="override.pf.cal.parameter"
													id="override.pf.cal.parameter"
													ondblclick="callShowDiv(this);"><%=label.get("override.pf.cal.parameter")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													theme="simple" name="overridePfCal" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%"></td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="cal.emp.pf"
													id="cal.emp.pf" ondblclick="callShowDiv(this);"><%=label.get("cal.emp.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtEmpPfFlag" theme="simple"
													onclick="return callEmpPf('actualAmtEmpPfFlag');"
													disabled="true" />&nbsp;Actual Amount <s:hidden
													name="actualAmtEmpPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox theme="simple"
													name="qualAmtEmpPfFlag"
													onclick="return callEmpPf('qualAmtEmpPfFlag');"
													disabled="true" />&nbsp;Qualifying Amount <s:hidden
													name="qualAmtPf" disabled="true" /></td>
												<td width="2">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="cal.comp.pf"
													id="cal.comp.pf" ondblclick="callShowDiv(this);"><%=label.get("cal.comp.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtCompPfFlag"
													onclick="return callCompPf('actualAmtCompPfFlag');"
													disabled="true" />&nbsp;Actual Amount <s:hidden
													name="actualAmtCompPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox name="qualAmtCompPfFlag"
													onclick="return callCompPf('qualAmtCompPfFlag');"
													disabled="true" />&nbsp;Qualifying Amount <s:hidden
													name="qualAmtPf" disabled="true" /></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label class="set"
													name="cal.pension.pf" id="cal.pension.pf"
													ondblclick="callShowDiv(this);"><%=label.get("cal.pension.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtPensionPfFlag"
													onclick="return callPensionPf('actualAmtPensionPfFlag');"
													disabled="true" />&nbsp;Actual Amount <s:hidden
													name="actualAmtPensionPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox name="qualAmtPensionPfFlag"
													disabled="true" />&nbsp;Qualifying Amount <s:hidden
													name="qualAmtPf" disabled="true" /></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td>
										<fieldset><legend class="legend1"> Banking
										Info</legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="salary.acNo"
													id="salary.acNo" ondblclick="callShowDiv(this);"><%=label.get("salary.acNo")%></label></td>
												<td width="2%" class="star">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="regularACCNO" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="salary.bank"
													id="salary.bank" ondblclick="callShowDiv(this);"><%=label.get("salary.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="micrReguCode" /> <s:property value="regularMICR" /><s:hidden
													name="branchName" /> <s:hidden name="ifscCode" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="23%"><label name="reimburse.acNo"
													id="reimburse.acNo" ondblclick="callShowDiv(this);"><%=label.get("reimburse.acNo")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="reimbursementACCNO" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="reimbursement.bank"
													id="reimbursement.bank" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="reimbrBankCode" /> <s:property value="reimbrBank" /></td>
												 <td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="pension.account.no"
													id="pension.account.no" ondblclick="callShowDiv(this);"><%=label.get("pension.account.no")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="pensionACCNO" /></td>
													<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="pension.bank"
													id="pension.bank" ondblclick="callShowDiv(this);"><%=label.get("pension.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="pensionBankCode" /> <s:property value="pensionBank" /></td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="pensionable" id="pensionable"
													ondblclick="callShowDiv(this);"><%=label.get("pensionable")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="pensionable" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="salPay.mode" id="salPay.mode"
													ondblclick="callShowDiv(this);"><%=label.get("salPay.mode")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="salDetail.payMode" /></td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label name="customer.refno"
													id="customer.refno" ondblclick="callShowDiv(this);"><%=label.get("customer.refno")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="customerRefNo" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>

									<tr>
										<td>
										<fieldset><legend class="legend1"> Cost
										Center Info </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="accounting.category"
													id="accounting.category" ondblclick="callShowDiv(this);"><%=label.get("accounting.category")%></label></td>
												<td width="2%" class="star">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="accountingCategory" /> <s:hidden
													name="accountingCategoryCode" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%"></td>
												<td width="2%"></td>

											</tr>
											<tr>
												<td width="23%"><label name="cost.center"
													id="cost.center" ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:property
													value="costCenter" /> <s:hidden name="costCenterCode" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="sub.cost.center"
													id="sub.cost.center" ondblclick="callShowDiv(this);"><%=label.get("sub.cost.center")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:property
													value="subCostCenter" /> <s:hidden
													name="subCostCenterCode" /></td>
												<td width="2%"></td>
											</tr>

										</table>
										</fieldset>
										</td>
									</tr>
								</s:if>
								<!-- ---------------------------else------------------------------ -->
								<s:else>
									<tr>
										<td>
										<fieldset><legend class="legend1">
										Statutory Details </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="ESIC.numbe id="
													ESIC.number" ondblclick="callShowDiv(this);"><%=label.get("ESIC.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="esciNumber"
													size="20" maxlength="15" onkeypress="return alphaNumeric1();" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="PAN.number"
													id="PAN.number" ondblclick="callShowDiv(this);"><%=label.get("PAN.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield
													name="noPAN" size="20" maxlength="15" onkeypress="return alphaNumeric1();" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="23%"><label name="gratuity.id"
													id="gratuity.idr" ondblclick="callShowDiv(this);"><%=label.get("gratuity.id")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="gratuityId"
													size="20" maxlength="15"  onkeypress="return alphaNumeric1();"  /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="emp.overtime"
													id="emp.overtime" ondblclick="callShowDiv(this);"><%=label.get("emp.overtime")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top"><s:checkbox
													theme="simple" name="sOTflag"
													onclick="return checkAppl('sOTflag');" /></td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td>
										<fieldset><legend class="legend1"> PF
										Information </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<s:hidden name="oEPFflag" />
												<s:hidden name="oGPFflag" />
												<s:hidden name="oVPFflag" />
												<s:hidden name="oPFTflag" />
												<s:hidden name="oOTflag" />

												<s:if test="oEPFflag">
													<td width="23%"><label name="emp.epf" id="emp.epf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.epf")%></label></td>
													<td width="2%" class="star">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%"><s:checkbox theme="simple"
														name="sEPFflag" onclick="return checkAppl('sEPFflag');" /></td>
												</s:if>
												<s:else>
													<s:hidden name='sEPFflag' value='false' />
												</s:else>
												<s:if test="oGPFflag">
													<td width="2%"></td>
													<td width="4%">&nbsp;</td>
													<td width="23%"><label name="emp.gpf" id="emp.gpf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.gpf")%>
													</label></td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%" valign="top" class="text1"><s:checkbox
														theme="simple" name="sGPFflag"
														onclick="return checkAppl('sGPFflag');" /></td>
													<td width="2%"></td>
												</s:if>
												<s:else>
													<s:hidden name='sGPFflag' value='false' />
												</s:else>
											</tr>

											<tr>
												<s:if test="oVPFflag">
													<td width="23%"><label name="emp.vpf" id="emp.vpf"
														ondblclick="callShowDiv(this);"><%=label.get("emp.vpf")%></label></td>
													<td width="2%">&nbsp;</td>
													<td width="2">:</td>
													<td width="18%"><s:checkbox theme="simple"
														name="sVPFflag" onclick="return checkAppl('sVPFflag');" /></td>
												</s:if>
												<s:else>
													<s:hidden name='sVPFflag' value='false' />
												</s:else>

												<s:if test="oPFTflag">
													<td width="2%"></td>
													<td width="4%">&nbsp;</td>
													<td width="23%"><label name="emp.pft" id="emp.pft"
														ondblclick="callShowDiv(this);"><%=label.get("emp.pft")%></label>
													</td>
													<td width="2%">&nbsp;</td>
													<td width="2%">:</td>
													<td width="18%" valign="top" class="text1"><s:checkbox
														theme="simple" name="sPFTflag"
														onclick="return checkAppl('sPFTflag');" /></td>
													<td width="2%"></td>
												</s:if>
												<s:else>
													<s:hidden name='sPFTflag' value='false' />
												</s:else>
											</tr>

											<tr>
												<td width="23%"><label name="PF.number" id="PF.number"
													ondblclick="callShowDiv(this);"><%=label.get("PF.number")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:textfield name="noGPF"
													size="20" maxlength="17"  onkeypress="return alphaNumeric1();" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="pfJoin.date"
													id="pfJoin.date" ondblclick="callShowDiv(this);"><%=label.get("pfJoin.date")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:textfield
													name="pfJoinDate" size="20" maxlength="10" onkeypress="return numbersWithHiphen();"
													onblur="return validateDate('paraFrm_pfJoinDate','pfJoin.date');"/></td>
												<td width="2%"><s:a
													href="javascript:NewCal('paraFrm_pfJoinDate','DDMMYYYY');">
													<img src="../pages/images/recruitment/Date.gif" width="16" height="16"
														class="imageIcon" border="0" align="absmiddle" />
												</s:a></td>
											</tr>

											<tr>
												<td width="23%"><label name="override.pf.cal.parameter"
													id="override.pf.cal.parameter"
													ondblclick="callShowDiv(this);"><%=label.get("override.pf.cal.parameter")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:checkbox theme="simple"
													name="overridePfCal" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label class="set" name="cal.emp.pf"
													id="cal.emp.pf" ondblclick="callShowDiv(this);"><%=label.get("cal.emp.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtEmpPfFlag" theme="simple"
													onclick="return callEmpPf('actualAmtEmpPfFlag');" />&nbsp;Actual
												Amount <s:hidden name="actualAmtEmpPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox theme="simple"
													name="qualAmtEmpPfFlag"
													onclick="return callEmpPf('qualAmtEmpPfFlag');" />&nbsp;Qualifying
												Amount <s:hidden name="qualAmtPf" disabled="true" /></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label class="set" name="cal.comp.pf"
													id="cal.comp.pf" ondblclick="callShowDiv(this);"><%=label.get("cal.comp.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtCompPfFlag"
													onclick="return callCompPf('actualAmtCompPfFlag');" />&nbsp;Actual
												Amount <s:hidden name="actualAmtCompPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox name="qualAmtCompPfFlag"
													onclick="return callCompPf('qualAmtCompPfFlag');" />&nbsp;Qualifying
												Amount <s:hidden name="qualAmtPf" disabled="true" /></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>

											<tr>
												<td width="23%"><label class="set"
													name="cal.pension.pf" id="cal.pension.pf"
													ondblclick="callShowDiv(this);"><%=label.get("cal.pension.pf")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:checkbox
													name="actualAmtPensionPfFlag"
													onclick="return callPensionPf('actualAmtPensionPfFlag');" />&nbsp;Actual
												Amount <s:hidden name="actualAmtPensionPf" disabled="true" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%" class="text1"><s:checkbox name="qualAmtPensionPfFlag"
													onclick="return callPensionPf('qualAmtPensionPfFlag');" />&nbsp;Qualifying
												Amount <s:hidden name="qualAmtPf" /></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top" class="text1">&nbsp;</td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>

									<tr>
										<td>
										<fieldset><legend class="legend1"> Banking
										Info</legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="salary.acNo"
													id="salary.acNo" ondblclick="callShowDiv(this);"><%=label.get("salary.acNo")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="regularACCNO"
													size="20" maxlength="20"
													onkeypress="return alphaNumeric1();" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="salary.bank"
													id="salary.bank" ondblclick="callShowDiv(this);"><%=label.get("salary.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:<s:hidden name="branchName" /> <s:hidden
													name="ifscCode" /></td>
												<td width="18%" valign="top" class="text1"><s:textfield
													name="micrReguCode" size="3" readonly="true" /><s:textfield
													size="8" name="regularMICR" readonly="true" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..." align="absmiddle"
													onclick="javascript:callsF9(500,325,'SalaryDetail_f9micraction.action');" /></td>
											</tr>
											<tr>
												<td width="23%"><label name="reimburse.acNo"
													id="reimburse.acNo" ondblclick="callShowDiv(this);"><%=label.get("reimburse.acNo")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="reimbursementACCNO"
													size="20" maxlength="20" onkeypress="return alphaNumeric1();"  /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="reimbursement.bank"
													id="reimbursement.bank" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:textfield
													name="reimbrBankCode" size="3" readonly="true" /><s:textfield
													size="8" name="reimbrBank" readonly="true" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..." align="absmiddle"
													onclick="javascript:callsF9(500,325,'SalaryDetail_f9Reimb.action');"></td>
											</tr>

											<tr>
												<td width="23%"><label name="pension.account.no"
													id="pension.account.no" ondblclick="callShowDiv(this);"><%=label.get("pension.account.no")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text1"><s:textfield
													name="pensionACCNO" size="20" maxlength="20" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="pension.bank"
													id="pension.bank" ondblclick="callShowDiv(this);"><%=label.get("pension.bank")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:textfield
													name="pensionBankCode" size="3" readonly="true" /><s:textfield
													size="8" name="pensionBank" readonly="true" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..." align="absmiddle"
													onclick="javascript:callsF9(500,325,'SalaryDetail_f9PensionBank.action');"></td>
											</tr>

											<tr>
												<td width="23%"><label name="pensionable"
													id="pensionable" ondblclick="callShowDiv(this);"><%=label.get("pensionable")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:select name="pensionable"
													list="#{'':'----------Select---------','Y':'Yes','N':'No'}" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="salPay.mode"
													id="salPay.mode" ondblclick="callShowDiv(this);"><%=label.get("salPay.mode")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" valign="top" class="text1"><s:select
													name="salDetail.payMode" list="assetmap" headerKey=""
													headerValue="--select--" size="1" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="23%"><label name="customer.refno"
													id="customer.refno" ondblclick="callShowDiv(this);"><%=label.get("customer.refno")%></label></td>

												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="customerRefNo"
													value="%{customerRefNo}" maxLength="20" size="20" onkeypress="return alphaNumeric1();"  /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%" valign="top">&nbsp;</td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>

									<tr>
										<td>
										<fieldset><legend class="legend1"> Cost
										Center Info </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="23%"><label name="accounting.category"
													id="accounting.category" ondblclick="callShowDiv(this);"><%=label.get("accounting.category")%></label></td>
												<td width="2%" class="star">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield name="accountingCategory"
													size="20" readonly="true" /> <s:hidden
													name="accountingCategoryCode" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..."
													onclick="javascript:callsF9(500,325,'SalaryDetail_f9AccountingCategory.action');">
												</td>
												<td width="4%">&nbsp;</td>
												<td width="23%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="18%">&nbsp;</td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="23%"><label name="cost.center"
													id="cost.center" ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%" class="text2"><s:textfield
													name="costCenter" size="20" readonly="true" /> <s:hidden
													name="costCenterCode" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..."
													onclick="javascript:callCostCenter();"></td>
												<td width="4%">&nbsp;</td>
												<td width="23%"><label name="sub.cost.center"
													id="sub.cost.center" ondblclick="callShowDiv(this);"><%=label.get("sub.cost.center")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="18%"><s:textfield
													name="subCostCenter" size="20" readonly="true" /> <s:hidden
													name="subCostCenterCode" /></td>
												<td width="2%"><input name="salaryBank" type="button"
													class="button" value="..."
													onclick="javascript:callSubCostCenter()"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
								</s:else>

								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td height="1px" bgcolor="#cccccc"></td>
								</tr>

								<tr>
									<td height="0">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
						<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="3%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td>|</td>
												</s:if>
											</tr>
										</s:if>
										<s:else>
											<tr>
												<s:if test="updateFlg">
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:if>
												<td width="18%"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" /></td>
												<td width="18%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td>|</td>
											</tr>
										</s:else>
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
			</td>
		</tr>
	</table>
</s:form>
<script>

function editFun(){
  document.getElementById('paraFrm').action='SalaryDetail_edit.action';
   document.getElementById('paraFrm').submit();
}

function saveFun(){
  	try{
  		if((document.getElementById('paraFrm_pfJoinDate').value)!=""){
  		if(!(validateDate('paraFrm_pfJoinDate','pfJoin.date'))){
  		return false;
  		}
  		}
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="SalaryDetail_save.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
  }        
  
  
 function searchFun(){
  	try{
  	
  	if(document.getElementById("paraFrm_viewFlag").value) {
  			
  			  javascript:callsF9(500,325,'SalaryDetail_f9empaction.action');  
  			   
  			   }
			
	}catch(e){alert(e);}
  }

function cancelFun(){
  	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="SalaryDetail_cancelFunc.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
  }        


function checkAppl(type){
		if(type!="sPFTflag"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_sPFTflag').checked =false;
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_sVPFflag').checked =false;
				document.getElementById('paraFrm_sGPFflag').checked =false;               
				document.getElementById('paraFrm_sEPFflag').checked =false;
			}
		}
}

function callEmpPf(type){
		if(type!="qualAmtEmpPfFlag"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_qualAmtEmpPfFlag').checked =false;
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_actualAmtEmpPfFlag').checked =false;
			}
		}
}

function callCompPf(type){
		if(type!="qualAmtCompPfFlag"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_qualAmtCompPfFlag').checked =false;
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_actualAmtCompPfFlag').checked =false;
			}
		}
}
function callPensionPf(type){
		if(type!="qualAmtPensionPfFlag"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_qualAmtPensionPfFlag').checked =false;
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_actualAmtPensionPfFlag').checked =false;
			}
		}
}

				function callSubCostCenter(){				
				if(document.getElementById('paraFrm_costCenterCode').value==''){
				alert('Please select '+document.getElementById('cost.center').innerHTML.toLowerCase());
				return false;
				}				
				callsF9(500,325,'SalaryDetail_f9SubCostCenter.action');
				}

		function callCostCenter(){				
				//document.getElementById('paraFrm_subCostCenter').value='';
				//document.getElementById('paraFrm_subCostCenterCode').value='';				
				callsF9(500,325,'SalaryDetail_f9CostCenter.action');
				}

	  
	  onloadCall();
			function onloadCall(){
			document.getElementById('first').style.display='block';
			document.getElementById('second').style.display='block';
			document.getElementById('third').style.display='block';
			document.getElementById('menuid1').className='li';
			document.getElementById('menuid2').className='li';
			document.getElementById('menuid3').className='li';
			var flag=document.getElementById('paraFrm_checkTabFlag').value;
			
			if(flag=='menuid3'){
			document.getElementById('third').style.display='block';
			document.getElementById('menuid3').className='on';
			}
			else{
			document.getElementById('first').style.display='block';
			document.getElementById('menuid1').className='on';
			}
			}
		
				function showCurrent(menuId, id){
				document.getElementById('first').style.display='none';
				document.getElementById('second').style.display='none';
				document.getElementById('third').style.display='none';
				document.getElementById('menuid1').className='li';
				document.getElementById('menuid2').className='li';
				document.getElementById('menuid3').className='li';
				document.getElementById(menuId).className='on';
				document.getElementById(id).style.display='block';
				document.getElementById('paraFrm_checkTabFlag').value=menuId;
				}
	  
	  function calldel(name) {
	   var emp=document.getElementById('employee').innerHTML.toLowerCase();	
  			if(document.getElementById("paraFrm_empName").value=="") {
  			alert("Please select "+emp);
  			} else {
  			var conf=confirm("Are you sure to delete this record ? ");
  			if(conf) {
  			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
  			
  			}
  	        }
  			}
	  
	  function formValidate() {
	   var emp=document.getElementById('employee').innerHTML.toLowerCase();	
  			if(document.getElementById("paraFrm_empName").value=="") {
  			
  			   alert("Please select "+emp);
  			   return false;
  			   
  			   }
  			
  			}

	function setNotice(){
		 	if(document.getElementById('paraFrm_actualAmtEmpPf').checked==true)
		 		document.getElementById('paraFrm_qualAmtEmpPfFlag').checked =false;
		 	
		 }

 function alphaNumeric1(e){
	document.onkeypress = alphaNumeric1;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91) || (key > 47 && key < 58) || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}		 
	 
	 </script>