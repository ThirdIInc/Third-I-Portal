<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="OfficialDetail" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="editFlag" />
	<s:hidden name="editDetail" />
	<s:hidden name="empCode" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="empName" />
	<s:hidden name="profileEmpId" />
	<s:hidden name="newFlag"/>
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"> <img
						src="../pages/mypage/images/icons/profile_officialinfo.png"
						width="16" height="16" />&nbsp;&nbsp;Official Information </legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="100%">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="%{offDetail.insertFlag}">

													<td><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="saveFun()" class="iconbutton">Save</a></td>
												<td>|</td>
												</s:if>
												<s:elseif test="%{offDetail.updateFlag}">

													<td><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="saveFun()" class="iconbutton">Save</a></td>
												<td>|</td>
												</s:elseif>

												<td><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="%{offDetail.insertFlag}">
													<td><a href="#"><img
														src="../pages/mypage/images/icons/add.png"
														onclick="addFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="addFun()" class="iconbutton">Add</a></td>
													<td width="3%">|</td>
												</s:if>
												<s:if test="%{offDetail.updateFlag}">
											
													<td><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="editFun()" class="iconbutton">Edit</a></td>
													<td>|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													
													<td align="right" width="100%"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td width="3%">|</td>
												</s:if>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr>
									<td colspan="11">
									<fieldset><legend class="legend1"> Employee
									Information</legend>
									<table width="100%" border="0" align="right" cellpadding="0"
										cellspacing="0">
										<tr>
											<td colspan="3">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<s:if test="editFlag">
													<tr>
														<td>
														<table width="98%" border="0" align="center"
															cellpadding="0" cellspacing="2">
															<tr>
																<td width="22%" height="22"><label class="set"
																	name="employee.id" id="employee.id"
																	ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%" height="22" class="text1"><s:textfield
																	name="empToken" size="30" maxlength="15"
																	onkeypress="return allCharacters()" /></td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
																<!--<td width="22%" height="22"><label class="set1"
																	name="photograph" id="photograph"
																	" ondblclick="callShowDiv(this);"><%=label.get("photograph")%></label>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																 
																 <td width="17%" rowspan="5">
																<table width="5" height="100" border="0" cellpadding="0"
																	cellspacing="0" class="borderPhoto">
																	<tr>
																		<td bgcolor="#FFFFFF" align="center"
																			style="padding: 3px;"><s:if test="%{flag}">
																			<%
																			%>
																			<img src="../pages/images/employee/NoImage.jpg "
																				height="100" align="middle" />
																		</s:if><s:else>
																			<%
																			String str = (String) request.getAttribute("profilePhoto");
																			%>

																			<%
																				if (str.equals("NoImage.jpg")) {
																				%>
																			<img src="../pages/images/employee/NoImage.jpg"
																				height="100" align="middle" />
																			<%
																				} else {
																				%>
																			<img
																				src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>"
																				height="110" />
																			<%
																				}
																				%>
																		</s:else></td>
																	</tr>
																</table>
																</td>-->
																<td width="22%" height="22"><label name="gender"
																	id="gender" ondblclick="callShowDiv(this);"><%=label.get("gender")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%" height="22"><s:select
																	name="gender"
																	list="gendermap" size="1" /></td>
																<td width="2%"></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label name="title"
																	id="title" ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%" height="22" class="text1"><s:hidden
																	name="title" /> <s:textfield
																	name="titleName" readonly="true" size="30"
																	maxlength="30" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="titleAction()" />
																</td>
																<td width="3%" height="22">
																<td width="22%" height="22"><label name="dob"
																	id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%" height="22"><s:textfield
																	name="birthDate" size="30" maxlength="10" onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_birthDate', 'dob')" /></td>
																<td width="2%"><s:a
																	href="javascript:NewCal('paraFrm_birthDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" border="0" align="absmiddle" />
																</s:a></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label
																	name="first.name" id="first.name"
																	ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%" height="22"><s:textfield
																	name="firstName" size="30" maxlength="30"
																	tooltip="Select Employee Name"
																	onkeypress="return charactersOnly()" /></td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label name="grade"
																	id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="cadreCode" /> <s:textfield
																	name="cadreName" size="30" readonly="true" /></td>
																<td><input type="button" name="Submit3"
																	class="button" value="..." onclick="gradeAction()" /></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label
																	name="middle.name" id="middle.name"
																	ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td height="22"><s:textfield
																	name="middleName" size="30" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label name="trade"
																	id="trade" ondblclick="callShowDiv(this);"><%=label.get("trade")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="tradeCode" /> <s:textfield
																	name="tradeName" size="30" maxlength="30"
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="tradeAction()" />
															</tr>
															<tr>
																<td width="22%" height="22"><label name="last.name"
																	id="last.name" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%" height="22"><s:textfield
																	name="lastName" size="30" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label name="doj"
																	id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield
																	name="regularDate" size="30" maxlength="10" onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_regularDate', 'doj')" /></td>
																<td width="2%"><s:a href="javascript:NewCal('paraFrm_regularDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" class="imageIcon" border="0"
																		align="absmiddle" /></s:a></td>
															</tr>
															<tr>
																<td><label name="division" id="division"
																	ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden name="divCode" />
																<s:textfield name="divName" size="30"
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="divisionAction()" /></td>
																<td width="3%">&nbsp;</td>
																<!--<td width="22%" height="22"><label
																	name="upload.photo" id="upload.photo"
																	ondblclick="callShowDiv(this);"><%=label.get("upload.photo")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%" height="22"><s:textfield
																	name="uploadFileName" size="10" maxlength="30"
																	readonly="true" /> <input name="Browse" type="button"
																	class="token" value="Upload"
																	onclick="uploadFile('uploadFileName');" /></td>-->
																	<td width="22%" height="22"><label name="conf.date"
																	id="conf.date" ondblclick="callShowDiv(this);"><%=label.get("conf.date")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield
																	name="confirmDate" size="30" maxlength="10" onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_confirmDate', 'conf.date')" /></td>
																<td><s:a
																	href="javascript:NewCal('paraFrm_confirmDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" border="0" align="absmiddle" />
																</s:a></td>
																<td width="2%"></td>
															</tr>
															<tr>
																<td width="22%"><label name="branch" id="branch"
																	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
																</td>
																<td width="2%"><font color="red">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="centerCode" /> <s:textfield
																	name="centerName" size="30" maxlength="30"
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="branchAction()" />
																</td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label name="dol"
																	id="dol" ondblclick="callShowDiv(this);"><%=label.get("dol")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield
																	name="leaveDate" size="30" maxlength="10" onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_leaveDate', 'dol')" /></td>
																<td width="2%"><s:a
																	href="javascript:NewCal('paraFrm_leaveDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" border="0" align="absmiddle" />
																</s:a></td>
																<td width="2%"></td>
															</tr>
															<tr>
																<td width="22%"><label name="department"
																	id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden name="deptCode" />
																<s:textfield name="deptName" size="30"
																	maxlength="30" readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="deptAction()" /></td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label
																	name="groupjoin.date" id="groupjoin.date"
																	ondblclick="callShowDiv(this);"><%=label.get("groupjoin.date")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield
																	name="groupjoinDate" size="30" maxlength="10" onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_groupjoinDate', 'groupjoin.date')" /></td>
																<td width="2%"><s:a
																	href="javascript:NewCal('paraFrm_groupjoinDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" border="0" align="absmiddle" />
																</s:a> <!--<s:property value="serviceTenureValue"/> --></td>
															</tr>
															<tr>
																<td width="22%"><label name="designation"
																	id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden name="rankCode" />
																<s:textfield name="rankName" size="30"
																	readonly="true" /></td>

																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="desigAction()" /></td>
																<td width="3%">&nbsp;</td>
																<td width="22%" height="22"><label
																	name="serviceTenure" id="serviceTenure"
																	ondblclick="callShowDiv(this);"><%=label.get("serviceTenure")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:property
																	value="serviceTenureValue" /></td>
																<td width="2%"></td>
															</tr>
															<tr>
																<td width="22%"><label class="set" name="roleName"
																	id="roleName" ondblclick="callShowDiv(this);"><%=label.get("roleName")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield
																	name="roleName" size="30"
																	onkeypress="return charactersOnly()" maxlength="25" /></td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
															</tr>
															<tr>
																<td width="22%"><label name="shift" id="shift"
																	ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
																</td>
																<td width="2%"><font color="red">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="shiftCode" /> <s:textfield
																	name="shiftType" size="30" maxlength="30"
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="shiftAction()" />
																</td>
																<td width="3%">&nbsp;</td>															
															</tr>
															<tr>
																<td width="22%"><label name="employee.type"
																	id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
																</td>
																<td width="2%"><font color="red">*</font></td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden name="typeCode" />
																<s:textfield name="typeName" size="30"
																	maxlength="30" readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="empTypeAction()" />
																</td>
																<td width="3%">&nbsp;</td>
																
															</tr>
															<tr>
																<td width="22%"><label name="pay.bill"
																	id="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="payBillId" /> <s:textfield
																	name="payBillName" size="30" maxlength="30" 
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="payBillAction()" />
																</td>
																<td width="3%">&nbsp;</td>
																
															</tr>
															<tr>
																<td width="22%"><label name="reporting.to"
																	id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:hidden
																	name="reportingID" /> <s:hidden
																	name="reportingToken" /> <s:textfield
																	name="reportingTo" size="30" maxlength="30"
																	readonly="true" /></td>
																<td width="2%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="reportingAction()" />
																</td>

																<td width="3%">&nbsp;</td>
																
															</tr>
															<tr>
																<td width="22%"><label name="status" id="status"
																	ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="17%"><s:select name="status"
																	list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated','A':'Absconding'}" size="1" />
																</td>
																<td width="2%"></td>
																<td width="3%">&nbsp;</td>
																
															</tr><!--
															<s:if test="%{divD1Flag}">
																<tr>
																	<s:if test="%{ssnFlag}">
																		<td width="22%"><label name="ssn" id="ssn"
																			ondblclick="callShowDiv(this);"><%=label.get("ssn")%></label>
																		</td>
																		<td width="2%"><font color="red">*</font></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:textfield name="offDetail.ssn"
																			size="30" maxlength="20" /></td>
																		<td width="2%"></td>
																	</s:if>
																	<s:else>
																		<td width="22%"><label name="ssn" id="ssn"
																			ondblclick="callShowDiv(this);"><%=label.get("ssn")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="17%"><s:textfield name="offDetail.ssn"
																			size="30" maxlength="20" readonly="true"
																			cssStyle="background-color: #F2F2F2;" /></td>
																		<td width="2%"></td>
																	</s:else>

																	<s:if test="%{sinFlag}">
																		<td width="3%">&nbsp;</td>
																		<td width="22%" height="22"><label name="sin"
																			id="sin" ondblclick="callShowDiv(this);"><%=label.get("sin")%></label>
																		</td>
																		<td width="2%"><font color="red">*</font></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:textfield name="offDetail.sin"
																			size="30" maxlength="20" /></td>
																		<td width="2%"></td>
																	</s:if>
																	<s:else>
																		<td width="3%" height="22">&nbsp;</td>
																		<td width="22%" height="22"><label name="sin"
																			id="sin" ondblclick="callShowDiv(this);"><%=label.get("sin")%></label>
																		</td>
																		<td width="2%"></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:textfield name="offDetail.sin"
																			size="30" maxlength="20" readonly="true"
																			cssStyle="background-color: #F2F2F2;" /></td>
																		<td width="2%"></td>
																	</s:else>
																</tr>
																<tr>
																	<s:if test="%{regionFlag}">
																		<td width="22%"><label name="region" id="region"
																			ondblclick="callShowDiv(this);"><%=label.get("region")%></label>
																		</td>
																		<td width="2%"><font color="red">*</font></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.regCode" /> <s:textfield
																			name="offDetail.regName" size="30" readonly="true" /></td>

																		<td width="2%"><input type="button"
																			name="Submit3" class="button" value="..."
																			onclick="religionAction()" /></td>
																	</s:if>
																	<s:else>

																		<td width="22%"><label name="region" id="region"
																			ondblclick="callShowDiv(this);"><%=label.get("region")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.regCode" /> <s:textfield
																			name="offDetail.regName" size="30" readonly="true"
																			cssStyle="background-color: #F2F2F2;" /></td>
																		<td width="2%"></td>
																	</s:else>
																	<s:if test="%{exeFlag}">
																		<td width="3%">&nbsp;</td>
																		<td width="22%" height="22"><label name="exec"
																			id="exec" ondblclick="callShowDiv(this);"><%=label.get("exec")%></label>
																		</td>
																		<td width="2%"><font color="red">*</font></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.execCode" /> <s:textfield
																			name="offDetail.execName" size="30" readonly="true" /></td>
																		<td width="2%"><input type="button"
																			name="Submit3" class="button" value="..."
																			onclick="executiveAction()" /></td>
																	</s:if>
																	<s:else>
																		<td width="3%">&nbsp;</td>
																		<td width="22%" height="22"><label name="exec"
																			id="exec" ondblclick="callShowDiv(this);"><%=label.get("exec")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.execCode" /> <s:textfield
																			name="offDetail.execName" size="30" readonly="true"
																			cssStyle="background-color: #F2F2F2;" /></td>
																		<td width="2%"></td>
																	</s:else>
																</tr>
																<s:if test="%{deptNoFlag}">
																	<tr>
																		<td width="22%"><label name="deptNo" id="deptNo"
																			ondblclick="callShowDiv(this);"><%=label.get("deptNo")%></label>
																		</td>
																		<td width="2%"><font color="red">*</font></td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.deptNoCode" /> <s:textfield
																			name="offDetail.deptNumber" size="30" readonly="true" /></td>
																		<td width="2%"><input type="button"
																			name="Submit3" class="button" value="..."
																			onclick="deptNumAction()" /></td>
																		<td width="3%">&nbsp;</td>
																		<td width="22%" height="22"></td>
																		<td width="2%"></td>
																		<td width="2%"></td>
																		<td width="17%"></td>
																		<td width="2%"></td>
																	</tr>

																</s:if>
																<s:else>
																	<tr>
																		<td width="22%"><label name="deptNo" id="deptNo"
																			ondblclick="callShowDiv(this);"><%=label.get("deptNo")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="17%"><s:hidden
																			name="offDetail.deptNoCode" /> <s:textfield
																			name="offDetail.deptNumber" size="30" readonly="true"
																			cssStyle="background-color: #F2F2F2;" /></td>
																		<td width="2%"></td>
																		<td width="3%">&nbsp;</td>
																		<td width="22%" height="22"></td>
																		<td width="2%"></td>
																		<td width="2%"></td>
																		<td width="17%"></td>
																		<td width="2%"></td>
																	</tr>
																</s:else>
															</s:if>
														--></table>
														</td>
													</tr>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9">
														<table width="98%" border="0" align="center"
															cellpadding="0" cellspacing="1">
															<tr>
																<td width="22%" height="22"><label class="set"
																	name="employee.id" id="employee.id"
																	ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:property
																	value="empToken" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%">
																<label class="set"
																	name="gender" id="gender"
																	ondblclick="callShowDiv(this);"><%=label.get("gender")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="genderProperty" /></td>
															</tr>
															<tr>
																<td width="22%" height="22"><s:hidden
																	name="title" /> <label class="set"
																	name="title" id="title" ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:property
																	value="titleName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%">
																<label name="dob"
																	id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="birthDate" /></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label class="set"
																	name="first.name" id="first.name"
																	ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:property
																	value="firstName" /></td>
																<td width="8%" height="22">&nbsp;</td>
																<td width="22%">
																<label name="grade"
																	id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:hidden
																	name="cadreCode" /> <s:property
																	value="cadreName" /></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label class="set"
																	name="middle.name" id="middle.name"
																	ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:property
																	value="middleName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																<label name="trade"
																	id="trade" ondblclick="callShowDiv(this);"><%=label.get("trade")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td height="22" class="text1"><s:hidden
																	name="tradeCode" /> <s:property
																	value="tradeName" /></td>
															</tr>
															<tr>
																<td width="22%" height="22"><label name="last.name"
																	id="last.name" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:property
																	value="lastName" /> <a href="#"></a></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																<label name="doj"
																	id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td height="22" class="text1"><s:property
																	value="regularDate" /></td>
															</tr>
															<tr>
																<td width="22%"><label name="division"
																	id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="divCode" /> <s:property
																	value="divName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22"><label name="conf.date"
																	id="conf.date" ondblclick="callShowDiv(this);"><%=label.get("conf.date")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="confirmDate" /></td>
															</tr>
															<tr>
																<td width="22%"><label name="branch" id="branch"
																	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="centerCode" /> <s:property
																	value="centerName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22"><label name="dol"
																	id="dol" ondblclick="callShowDiv(this);"><%=label.get("dol")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="leaveDate" /></td>
															</tr>
															<tr>
																<td width="22%"><label name="department"
																	id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="2%" class="text1"><s:property
																	value="deptName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="25%" height="22"><label
																	name="groupjoin.date" id="groupjoin.date"
																	ondblclick="callShowDiv(this);"><%=label.get("groupjoin.date")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="groupjoinDate" /></td>
															</tr>
															<tr>
																<td width="22%"><label name="designation"
																	id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="rankCode" /> <s:property
																	value="rankName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22"><label name="serviceTenure"
																	id="serviceTenure" ondblclick="callShowDiv(this);"><%=label.get("serviceTenure")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td class="text1"><s:property
																	value="serviceTenureValue" /></td>
															</tr>
															<tr>
																<td width="22%">
																<label class="set" name="roleName"
																	id="roleName" ondblclick="callShowDiv(this);"><%=label.get("roleName")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:property
																	value="roleName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td class="text1"></td>
															</tr>
															<tr>
																<td width="2%">
																<label class="set" name="shift"
																	id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label></td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="shiftCode" /> <s:property
																	value="shiftType" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td class="text1"></td>
															</tr>
															
															<tr>
																<td width="22%"><label name="employee.type"
																	id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="typeCode" /> <s:property
																	value="typeName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td class="text1"></td>
															</tr>
															
															<tr>
																<td width="22%" height="22"><label class="set" name="pay.bill"
																	id="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" height="22" class="text1"><s:hidden
																	name="payBillId" /> <s:property
																	value="payBillName" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td class="text1"></td>
															</tr>
															
															<tr>
																<td width="2%"><label name="reporting.to"
																	id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:hidden
																	name="reportingID" /> <s:hidden
																	name="reportingToken" /> <s:property
																	value="reportingTo" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td class="text1"></td>
															</tr>
															
															<!--
															<s:if test="%{divD1Flag}">
																<tr>
																	<s:if test="%{ssnFlag}">
																		<td width="22%"><label name="ssn" id="ssn"
																			ondblclick="callShowDiv(this);"><%=label.get("ssn")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" nowrap="nowrap" class="text1"><s:property
																			value="offDetail.ssn" /></td>
																	</s:if>
																	<s:else>
																		<td width="22%"><label name="ssn" id="ssn"
																			ondblclick="callShowDiv(this);"><%=label.get("ssn")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" class="text1"><s:property
																			value="offDetail.ssn" /></td>
																	</s:else>

																	<s:if test="%{sinFlag}">
																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label name="sin"
																			id="sin" ondblclick="callShowDiv(this);"><%=label.get("sin")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:property
																			value="offDetail.sin" /></td>
																	</s:if>
																	<s:else>
																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label name="sin"
																			id="sin" ondblclick="callShowDiv(this);"><%=label.get("sin")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:property
																			value="offDetail.sin" /></td>
																	</s:else>
																</tr>
																<tr>
																	<s:if test="%{regionFlag}">
																		<td width="22%"><label name="region" id="region"
																			ondblclick="callShowDiv(this);"><%=label.get("region")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" class="text1"><s:hidden
																			name="offDetail.regCode" /> <s:property
																			value="offDetail.regName" /></td>
																	</s:if>
																	<s:else>
																		<td width="22%"><label name="region" id="region"
																			ondblclick="callShowDiv(this);"><%=label.get("region")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" class="text1"><s:hidden
																			name="offDetail.regCode" /> <s:property
																			value="offDetail.regName" /></td>
																	</s:else>
																	<s:if test="%{exeFlag}">
																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label name="exec"
																			id="exec" ondblclick="callShowDiv(this);"><%=label.get("exec")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:hidden
																			name="offDetail.execCode" /> <s:property
																			value="offDetail.execName" /></td>
																	</s:if>
																	<s:else>
																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label name="exec"
																			id="exec" ondblclick="callShowDiv(this);"><%=label.get("exec")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:hidden
																			name="offDetail.execCode" /> <s:property
																			value="offDetail.execName" /></td>
																	</s:else>
																</tr>
																<s:if test="%{deptNoFlag}">
																	<tr>
																		<td width="22%"><label name="deptNo" id="deptNo"
																			ondblclick="callShowDiv(this);"><%=label.get("deptNo")%></label></td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" class="text1"><s:hidden
																			name="offDetail.deptNoCode" /> <s:property
																			value="offDetail.deptNumber" /></td>
																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label
																			name="serviceTenure" id="serviceTenure"
																			ondblclick="callShowDiv(this);"><%=label.get("serviceTenure")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:property
																			value="serviceTenureValue" /></td>
																	</tr>
																</s:if>
																<s:else>
																	<tr>
																		<td width="22%"><label name="deptNo" id="deptNo"
																			ondblclick="callShowDiv(this);"><%=label.get("deptNo")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td width="25%" class="text1"><s:hidden
																			name="offDetail.deptNoCode" /> <s:property
																			value="offDetail.deptNumber" /></td>

																		<td width="8%">&nbsp;</td>
																		<td width="22%" height="22"><label
																			name="serviceTenure" id="serviceTenure"
																			ondblclick="callShowDiv(this);"><%=label.get("serviceTenure")%></label>
																		</td>
																		<td width="2%">&nbsp;</td>
																		<td width="2%">:</td>
																		<td class="text1"><s:property
																			value="serviceTenureValue" /></td>
																	</tr>
																</s:else>
															</s:if>
															--><tr>
																<td width="22%"><label class="set" name="status"
																	id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%">:</td>
																<td width="25%" class="text1"><s:property
																			value="statusProperty" /></td>
																<td width="8%">&nbsp;</td>
																<td width="22%" height="22">
																</td>
																<td width="2%">&nbsp;</td>
																<td width="2%"></td>
																<td nowrap="nowrap" class="text1"></td>
															</tr>
														</table>
														</td>
													</tr>
												</s:else>
											</table>
											</td>
										</tr>
									</table>
									</fieldset>
									</td>
								</tr>
								<tr height="10">
								</tr>
								<tr>
									<td height="1px" bgcolor="#cccccc"></td>
								</tr>
								<tr>
									<td>
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag">
											<tr>
												<s:if test="%{offDetail.insertFlag}">
													<td><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="saveFun()" class="iconbutton">Save</a></td>
												<td>|</td>
												</s:if>
												<s:elseif test="%{offDetail.updateFlag}">
													<td><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="saveFun()" class="iconbutton">Save</a></td>
												<td>|</td>
												</s:elseif>
												
												<td><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="%{offDetail.insertFlag}">
													<td><a href="#"><img
														src="../pages/mypage/images/icons/add.png"
														onclick="addFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="addFun()" class="iconbutton">Add</a></td>
													<td width="3%">|</td>
												</s:if>
												<s:if test="%{offDetail.updateFlag}">
											
													<td><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td><a href="#" onclick="editFun()" class="iconbutton">Edit</a></td>
												
													<td width="3%">|</td>
											
												</s:if>
												<s:if test="isNotGeneralUser">
													
													<td align="right" width="100%"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
													<td>|</td>
												</s:if>
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
	</div>


</s:form>

<script>
function addFun()
{
try{ 
	
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="OfficialDetail_add.action";
	document.getElementById("paraFrm").submit();
}catch(e){
alert(e);}
}

function searchFun(){
	javascript:callsF9(500,325,'OfficialDetail_f9action.action');
}		

function editFun(){
try{
   document.getElementById("paraFrm_editFlag").value=true;
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="OfficialDetail_employeeDetails.action";
	document.getElementById("paraFrm").submit();
}catch(e){
alert(e);}
}

function cancelFun(){
	 document.getElementById("paraFrm_editFlag").value=false;
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="OfficialDetail_cancel.action";
	document.getElementById("paraFrm").submit();
}

function uploadFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/employee";
    	window.open('../pages/common/uploadFileHrm.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
     	document.getElementById('paraFrm').target="main";
} 

function titleAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9titleaction.action');
}
function divisionAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9divaction.action');
}		
function branchAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9centeraction.action');
}
function deptAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9deptaction.action');
}
function desigAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9rankaction.action');
}

function shiftAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9shiftaction.action');
}
function empTypeAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9typeaction.action');
}
function payBillAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9payBillaction.action');
}
function reportingAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9reporingToaction.action');
}
function religionAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9regionAction.action');
}
function deptNumAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9deptNoAction.action');
}
function gradeAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9gradeaction.action');
}
function tradeAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9tradeaction.action');
}
function executiveAction(){
	javascript:callsF9(500,325,'OfficialDetail_f9execAction.action');
}

function saveFun() {
   
     var empToken=document.getElementById('paraFrm_empToken').value
     var pre1 = document.getElementById('paraFrm_empCode').value;
     var bd = document.getElementById('paraFrm_birthDate').value;
     var crd = document.getElementById('paraFrm_regularDate').value;//Date of Joining
     var dld = document.getElementById('paraFrm_leaveDate').value;//Date of Leaving Dockyard 
     var fName = document.getElementById('paraFrm_firstName').value;//Last Increment Date
     var mName = document.getElementById('paraFrm_middleName').value;//Last Increment Date
     var lName = document.getElementById('paraFrm_lastName').value;//Last Increment Date
     var confirmationDate = document.getElementById('paraFrm_confirmDate').value; 
     var groupjoinDate = document.getElementById('paraFrm_groupjoinDate').value;
	
	var empid=document.getElementById('employee.id').innerHTML.toLowerCase();
	var bh=document.getElementById('branch').innerHTML.toLowerCase();
	var div=document.getElementById('division').innerHTML.toLowerCase();
	var dep=document.getElementById('department').innerHTML.toLowerCase();
	var des=document.getElementById('designation').innerHTML.toLowerCase();
	var sft=document.getElementById('shift').innerHTML.toLowerCase();
	var eType=document.getElementById('employee.type').innerHTML.toLowerCase();
	var doj=document.getElementById('doj').innerHTML.toLowerCase();
	var dob=document.getElementById('dob').innerHTML.toLowerCase();
	var dol=document.getElementById('dol').innerHTML.toLowerCase();
	var first=document.getElementById('first.name').innerHTML.toLowerCase();
	
	
	if(trim(document.getElementById('paraFrm_empToken').value)==""){
	alert("Please enter "+empid);
	return false;
	}
	
	if(trim(document.getElementById('paraFrm_firstName').value)==""){
		alert ("Please enter "+first);
		return false;
	}
	if(document.getElementById('paraFrm_divCode').value==""){
		alert ("Please select "+div);
		return false;
	}
	if(document.getElementById('paraFrm_centerCode').value==""){
				alert ("Please select "+bh);
				return false;
	}
	if(document.getElementById('paraFrm_deptName').value==""){
		alert ("Please select "+dep);
		return false;
	}
	if(document.getElementById('paraFrm_rankName').value==""){
		alert ("Please select "+des);
		return false;
	}
	if(document.getElementById('paraFrm_shiftCode').value==""){
		alert ("Please select "+sft);
		return false;
	}
	if(document.getElementById('paraFrm_typeName').value==""){
		alert ("Please select "+eType);
		return false;
	}
	if(document.getElementById('paraFrm_regularDate').value==""){
		alert ("Please enter/select "+doj);
		return false;
	}
    //Added by Prajakta Date: 7-Dec-2012
	//Checks for Date of Joining greater than Birth Date
	   	if(crd!="" && bd!=="") {
	   	
	    if (!dateDifference(bd, crd, 'paraFrm_regularDate', 'dob', 'doj')){
		return false;
	    }
		}
	//Checks for Date of Leaving greater than Birth Date
	    if(dld!="" && bd!=="") {
	    if (!dateDifference(bd, dld, 'paraFrm_leaveDate', 'dob', 'dol')){
		return false;
	     }
		}
	// Checks for Date of joining greater than birth date
	if(bd!=="" && groupjoinDate!=="" ){
	 if (!dateDifference(bd, groupjoinDate, 'paraFrm_groupjoinDate', 'dob', 'groupjoin.date')){
		return false;
	     }
	}	
	//Checks Leave Date greater than Joining Date
	   	if(dld!="" && crd!=="") {
	    if (!dateDifference(crd, dld, 'paraFrm_leaveDate', 'doj', 'dol')){
		return false;
	    }
	}
// Check for confirmation date greater thab birth date		 
		if(confirmationDate!="" && bd!="")
			{
				if (!dateDifference(bd, confirmationDate, 'paraFrm_confirmDate', 'dob', 'conf.date')){
				return false;
			    }
			}
//check for confirmation date greater than date of joinig
			/**if(confirmationDate!="" && crd!="")
			{
				if (!dateDifference(confirmationDate,crd, 'paraFrm_confirmDate','conf.date', 'doj')){
				return false;
			    }
			}**/
//check for date of leaving greater than confirmation date		
			if(confirmationDate!="" && dld!="")
			{
				if (!dateDifference(confirmationDate, dld, 'paraFrm_leaveDate','conf.date','dol')){
				return false;
			    }
		}
//checks for leaving date greater than gropu joinig  date
			if(groupjoinDate!=="" && dld!="" ){
			if (!dateDifference(groupjoinDate, dld , 'paraFrm_leaveDate','groupjoin.date','dol' )){
				return false;
			}
		} 		
//checks for group joining date greater than joining date
			if(groupjoinDate!=="" && crd!="" ){
			if (!dateDifferenceEqual(groupjoinDate ,crd ,'paraFrm_regularDate','groupjoin.date','doj' )){
				return false;
			}
			}
			
			if(bd!==""){
			if(!(validateDate('paraFrm_birthDate', 'dob'))){
				return false;
			}
			}
			
			if(dld!==""){
			if(!(validateDate('paraFrm_leaveDate', 'dol'))){
				return false;
			}
			}
			
			if(crd!==""){
			if(!(validateDate('paraFrm_regularDate', 'doj'))){
				return false;
			}
			}
			if(groupjoinDate!==""){
			if(!(validateDate('paraFrm_groupjoinDate', 'groupjoin.date'))){
				return false;
			}
			}
			
			if(confirmationDate!==""){
			if(!(validateDate('paraFrm_confirmDate','conf.date'))){
				return false;
			}
			}

//End Added by Prajakta Date: 7-Dec-2012
			document.getElementById("paraFrm").action="OfficialDetail_save.action";
			document.getElementById("paraFrm").submit();
			document.getElementById('paraFrm').target="main";
		
		}
		//End added by janisha for D1 integratio validation
		
 </script>