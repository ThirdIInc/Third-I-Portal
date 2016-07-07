<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="VisaDetails" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="paraId" />
	<s:hidden name="empId" />
	<s:hidden name="editFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="insertFlg" value="insertFlag" />


	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>
			<div style="float: left; width: 100%">
			<fieldset><legend class="legend"><img
				src="../pages/mypage/images/icons/visa_form.jpg" width="16"
				height="16" />&nbsp;&nbsp;&nbsp;Visa Information</legend>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="2">
				<tr>
					<td height="0">
					<table width="100%" border="0" align="right" cellpadding="2"
						cellspacing="3">

						<s:if test="editFlag">
							<tr>
								<s:if test="insertFlg">
									<td width="100%" align="right"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="5%"><a href="#" onclick="callSave()"
										class="iconbutton">Save</a></td>
									<td>|</td>
								</s:if>
								<s:elseif test="updateFlg">
									<td width="100%" align="right"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="5%"><a href="#" onclick="callSave()"
										class="iconbutton">Save</a></td>
									<td>|</td>
								</s:elseif>
								<td width="5%"><a href="#"><img
									src="../pages/mypage/images/icons/cancel.png"
									onclick="cancel()" width="10" height="10" border="0" /></a></td>
								<td width="13%"><a href="#" onclick="cancel()"
									class="iconbutton">Cancel</a></td>
									<td>|</td>
							</tr>
						</s:if>

						<s:else>

							<tr>
							<s:if test="updateFlg">
							<td width="89%" align="right"><a href="#"><img
									src="../pages/mypage/images/icons/edit.png"
									onclick="callForEdit()" width="10" height="10" border="0" /></a></td>
								<td><a href="#" onclick="callForEdit()" class="iconbutton">Edit</a></td>
								<td>|</td>
							</s:if>
							<s:if test="isNotGeneralUser">
								
								<td><a href="#"><img
									src="../pages/mypage/images/icons/search.png"
									onclick="searchEmpVisa()" width="10" height="10" border="0" /></a></td>
								<td><a href="#" class="iconbutton"
									onclick="searchEmpVisa()"> Search</a></td>
									<td>|</td>
                            </s:if>
							</tr>
						</s:else>

					</table>
					</td>
				</tr>
				<s:if test="editFlag">
					<tr>
						<td>
						<fieldset><legend class="legend1">Passport
						Details</legend>
						<div id="second">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="11">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="2">
									<tr>
										<td width="20%"><label name="passport.no"
											id="passport.no" ondblclick="callShowDiv(this);"><%=label.get("passport.no")%></label></td>
										<td><font color="red">*</font>&nbsp;:</td>
										<td width="25%" nowrap="nowrap"><s:textfield
											name="passport" size="28" maxlength="50" /></td>
										<td width="5%"></td>
										<td width="20%"><label name="passport.name"
											id="passport.name" ondblclick="callShowDiv(this);"><%=label.get("passport.name")%></label></td>
										<td>&nbsp;:</td>
										<td width="35%" nowrap="nowrap"><s:textfield
											name="passportName" size="28" maxlength="50" /></td>
									</tr>
									<tr>
										<td width="20%"><label name="passport.fathername"
											id="passport.fathername" ondblclick="callShowDiv(this);"><%=label.get("passport.fathername")%></label></td>
										<td>&nbsp;:</td>
										<td width="25%" nowrap="nowrap"><s:textfield
											name="fatherName" size="28" maxlength="50"
											onkeypress=" return charactersOnly();" /></td>
										<td width="5%"></td>
										<td width="20%"><label name="passport.motherName"
											id="passport.motherName" ondblclick="callShowDiv(this);"><%=label.get("passport.motherName")%></label></td>
										<td>&nbsp;:</td>
										<td width="35%" nowrap="nowrap"><s:textfield
											name="motherName" size="28" maxlength="50"
											onkeypress=" return charactersOnly ();" /></td>
									</tr>
									<tr>
										<td width="20%"><label name="passport.issuefrom"
											id="passport.issuefrom" ondblclick="callShowDiv(this);"><%=label.get("passport.issuefrom")%></label></td>
										<td>&nbsp;:</td>
										<td width="25%" nowrap="nowrap"><s:textfield
											name="passportIssueFrom" size="28" maxlength="50" /></td>
										<td width="5%"></td>
										<td width="20%"><label name="passport.uidno"
											id="passport.uidno" ondblclick="callShowDiv(this);"><%=label.get("passport.uidno")%></label></td>
										<td>&nbsp;:</td>
										<td width="35%" nowrap="nowrap"><s:textfield
											name="passportUidNum" size="28" maxlength="20" /></td>

									</tr>
									<tr>
										<td width="20%"><label name="passport.dateOfIssue"
											id="passport.dateOfIssue" ondblclick="callShowDiv(this);"><%=label.get("passport.dateOfIssue")%></label></td>
										<td><font color="red">*</font>&nbsp;:</td>
										<td width="25%" nowrap="nowrap"><s:textfield
											name="passportDateOfIssue" size="28" maxlength="10"
											onkeypress="numbersWithHiphen();" /><s:a
											href="javascript:NewCal('paraFrm_passportDateOfIssue','DDMMYYYY');">&nbsp;
											<img class="iconImage" class="iconImage"
												src="../pages/images/recruitment/Date.gif" class="iconImage"
												height="16" align="absmiddle" width="16">
										</s:a></td>
										<td width="5%"></td>

										<td width="20%"><label name="passport.expiry"
											id="passport.expiry" ondblclick="callShowDiv(this);"><%=label.get("passport.expiry")%></label></td>
										<td><font color="red">*</font>&nbsp;:</td>
										<td width="25%"><s:textfield name="passportExpDate"
											size="28" maxlength="10" onkeypress="numbersWithHiphen();" />
										<s:a
											href="javascript:NewCal('paraFrm_passportExpDate','DDMMYYYY');">
											<img class="iconImage" class="iconImage"
												src="../pages/images/recruitment/Date.gif" class="iconImage"
												height="16" align="absmiddle" width="16">
										</s:a></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</div>
						</fieldset>
						</td>
					</tr>
				</s:if>
				<s:else>

					<tr>
						<td>
						<fieldset><legend class="legend1">Passport
						Details</legend>
						<table width="100%" border="0" cellpadding="2" cellspacing="1">
							<tr>
								<td colspan="11">
								<table width="99%" border="0" align="center" cellpadding="2"
									cellspacing="1">
									<tr>
										<td width="18%"><label name="passport.no"
											id="passport.no" ondblclick="callShowDiv(this);"><%=label.get("passport.no")%></label>
										</td>
										<td></td>
										<td width="2%">:</td>
										<td width="25%" nowrap="nowrap" class="text1"><s:property
											value="passport" id="paraFrm_passportValue"/></td>
											<td></td>
                                        <td width="7%" colspan="1"></td>
										<td width="20%"><label name="passport.name"
											id="passport.name" ondblclick="callShowDiv(this);"><%=label.get("passport.name")%></label>
										<font color="red">&nbsp;</font></td>
										<td width="4%"></td>
										<td width="2%">:</td>
										<td width="35%" nowrap="nowrap" class="text1"><s:property
											value="passportName" /></td>
										<td></td>
									</tr>

									<tr>
										<td width="18%"><label name="passport.fathername"
											id="passport.fathername" ondblclick="callShowDiv(this);"><%=label.get("passport.fathername")%></label>
										<font color="red">&nbsp;</font></td>
										<td></td>
										<td width="2%">:</td>
										<td width="25%" nowrap="nowrap" class="text1"><s:property
											value="fatherName" /></td>
											<td></td>
											<td width="7%" colspan="1"></td>
										<td width="20%"><label name="passport.motherName"
											id="passport.motherName" ondblclick="callShowDiv(this);"><%=label.get("passport.motherName")%></label>
										<font color="red">&nbsp;</font></td>
										<td width="4%"></td>
										<td width="2%">:</td>
										<td width="35%" nowrap="nowrap" class="text1"><s:property
											value="motherName" /></td>
											<td></td>
									</tr>

									<tr>
										<td width="18%"><label name="passport.issuefrom"
											id="passport.issuefrom" ondblclick="callShowDiv(this);"><%=label.get("passport.issuefrom")%></label>
										<font color="red">&nbsp;</font></td>
										<td></td>
										<td width="2%">:</td>
										<td width="25%" nowrap="nowrap" class="text1"><s:property
											value="passportIssueFrom" /></td>
											<td></td>
											<td width="7%" colspan="1"></td>
										<td width="20%"><label name="passport.uidno"
											id="passport.uidno" ondblclick="callShowDiv(this);"><%=label.get("passport.uidno")%></label>
										<font color="red">&nbsp;</font></td>
										<td width="4%"></td>
										<td width="2%">:</td>
										<td width="35%" nowrap="nowrap" class="text1"><s:property
											value="passportUidNum" /></td>
											<td></td>

									</tr>

									<tr>
										<td width="18%"><label name="passport.dateOfIssue"
											id="passport.dateOfIssue" ondblclick="callShowDiv(this);"><%=label.get("passport.dateOfIssue")%></label>
										</td>
										<td></td>
										<td width="2%">:</td>
										<td width="25%" nowrap="nowrap" class="text1"><s:property
											value="passportDateOfIssue" /><s:a
											href="javascript:NewCal('paraFrm_passportDateOfIssue','DDMMYYYY');">
										</s:a></td>
										<td></td>
										<td width="7%" colspan="1"></td>
										<td width="20%"><label name="passport.expiry"
											id="passport.expiry" ondblclick="callShowDiv(this);"><%=label.get("passport.expiry")%></label></td>
											<td width="4%"></td>
										<td width="2%">:</td>
										<td width="25%" class="text1"><s:property
											value="passportExpDate" /> <s:a
											href="javascript:NewCal('paraFrm_passportExpDate','DDMMYYYY');">
										</s:a></td>
										<td></td>
									</tr>

								</table>
								</td>
							</tr>
						</table>
						</fieldset>
						</td>
					</tr>
				</s:else>
				<tr>
					<td>
					<fieldset><legend class="legend1">Visa Details</legend>
					<table width="100%" border="0" align="right">
						<tr>
							<td align="right"><s:submit cssClass="add" theme="simple"
								value="Add New Visa Entry" onclick="return callForAdd();"
								id="ctrlHide" /></td>
						</tr>
					</table>
					
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="11">

							<div>

							<table width="100%" border="0" align="center" cellpadding="1"
								cellspacing="1">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td width="15%" colspan="1"></td>
									<td></td>
									<td></td>
									<td></td>

									<td align="center"></td>
									<td></td>
								</tr>
								<s:if test="addFlag">
									<tr>
										<td width="20%" colspan="1"><label class="set"
											name="visa_number" id="visa_number"
											ondblclick="callShowDiv(this);"><%=label.get("visa_number")%>
										</label></td>

										<td><font color="red">*</font></td>
										<td>:</td>
										<td width="21%" colspan="1"><s:textfield size="25"
											maxlength="15" name="visaNumber" /></td>
										<td></td>
										<td width="15%" colspan="1"></td>
										<td width="20%" colspan="1"><label class="set"
											name="country_name" id="country_name"
											ondblclick="callShowDiv(this);"><%=label.get("country_name")%>
										</label></td>
										<td><font color="red">*</font></td>
										<td>:</td>
										<td width="35%" colspan="1"><s:textfield size="25"
											maxlength="20" name="country"
											onkeypress="return charactersWithSpecialCharacters();" /></td>
										<td></td>

									</tr>
									<tr>
										<td width="20%" colspan="1"><label class="set"
											name="visa_type" id="visa_type"
											ondblclick="callShowDiv(this);"><%=label.get("visa_type")%>
										</label></td>
										<td><font color="red">*</font></td>
										<td>:</td>
										<td width="21%" colspan="1"><s:textfield size="25"
											maxlength="10" name="visaType"
											onkeypress="return isCharactersKey(event)" /></td>
										<td></td>
										<td width="15%" colspan="1"></td>
										<td width="20%" colspan="1"><label class="set"
											name="visa_entry" id="visa_entry"
											ondblclick="callShowDiv(this);"><%=label.get("visa_entry")%>
										</label></td>
										<td><font color="red">*</font></td>
										<td>:</td>
										<td width="35%" colspan="1"><s:select name="visaEntry"
											value="%{visaEntry}"
											list="#{'-1':'-----------Select-------------','Single':'Single','Multiple':'Multiple'}" />
										</td>
										<td></td>
									</tr>
									<tr>
										<td width="25%" colspan="1"><label class="set"
											name="issue_place" id="issue_place"
											ondblclick="callShowDiv(this);"><%=label.get("issue_place")%></label></td>
										<td></td>
										<td>:</td>
										<td width="21%" colspan="1"><s:textfield size="25"
											maxlength="20" name="issuePlace"
											onkeypress="return isCharactersKey(event)" /></td>
										<td></td>
										<td width="15%" colspan="1"></td>
										<td width="35%" colspan="1"><label class="set"
											name="issue_authority" id="issue_authority"
											ondblclick="callShowDiv(this);"><%=label.get("issue_authority")%></label>
										</td>
										<td></td>
										<td>:</td>
										<td width="35%" colspan="1"><s:textfield size="25"
											maxlength="25" name="issueAuthority"
											onkeypress="return isCharactersKey(event)" /></td>
										<td></td>
									</tr>
									<tr>
										<td><label class="set" name="issue_date" id="issue_date"
											ondblclick="callShowDiv(this);"><%=label.get("issue_date")%>
										</label></td>
										<td><font color="red">*</font></td>
										<td>:</td>
										<td><s:textfield size="25" maxlength="10"
											onkeypress="numbersWithHiphen();" name="issueDate" /></td>
										<td><s:a
											href="javascript:NewCal('paraFrm_issueDate','DDMMYYYY');">
											<img class="iconImage" class="iconImage"
												src="../pages/images/recruitment/Date.gif" class="iconImage"
												height="16" align="right" width="16">
										</s:a></td>
										<td width="15%" colspan="1"></td>
										<td><label class="set" name="valid_upto" id="valid_upto"
											ondblclick="callShowDiv(this);"><%=label.get("valid_upto")%></label>
										</td>
										<td><font color="red">*</font></td>
										<td>:</td>
										<td><s:textfield size="25" maxlength="10"
											onkeypress="numbersWithHiphen();" name="validUpto" /></td>
										<td><s:a
											href="javascript:NewCal('paraFrm_validUpto','DDMMYYYY');">
											<img class="iconImage" class="iconImage"
												src="../pages/images/recruitment/Date.gif" class="iconImage"
												height="16" align="absmiddle" width="16">
										</s:a></td>
									</tr>
									<tr>
										<td><label id="address_details" class="set"
											ondblclick="callShowDiv(this);" name="address_details">Address
										</label></td>
										<td></td>
										<td>:</td>
										<td><s:textarea id="paraFrm_address" rows="3" cols="22"
											theme="simple" onkeyup="callLength('remainingChars');"
											name="address">
										</s:textarea></td>
										<td></td>
										<td width="15%" colspan="1"></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td><img src="../pages/images/zoomin.gif" height="12"
											align="absmiddle"width="12" theme="simple"
											onclick="javascript:callWindow('paraFrm_address','address_details','','200','200');">
										Remaining Chars:&nbsp;<s:textfield size="2" maxlength="3"
											name="remainingChars" readonly="true" /></td>
										<td></td>
										<td width="15%" colspan="1"></td>
										<td></td>
										<td></td>
										<td></td>
										<td align="right"><input type="button"
											value="   Save   " onclick="return callForSaveVisa();" />&nbsp;<input type="button" value="   Back   "
											onclick="return cancel()" /></td>
										<td></td>
									</tr>
								</s:if>


							</table>
							</div>
							</td>
						</tr>


						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="formtext">
								<table width="100%" border="0" align="center" cellpadding="1"
									cellspacing="1">
									<tr>
										<td width="5%" bgcolor="#EEF4FB"><label class="set"
											id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td colspan="1" width="10" nowrap="nowrap" bgcolor="#EEF4FB"><label
											name="visa_number" id="visa_number"
											ondblclick="callShowDiv(this);"><%=label.get("visa_number")%></label></td>
										<td colspan="1" width="10" bgcolor="#EEF4FB"><label
											name="country_name" id="country_name"
											ondblclick="callShowDiv(this);"><%=label.get("country_name")%></label></td>
										<td colspan="1" width="10" bgcolor="#EEF4FB"><label
											name="visa_type" id="visa_type"
											ondblclick="callShowDiv(this);"><%=label.get("visa_type")%></label></td>
										<td " colspan="1" width="5" bgcolor="#EEF4FB"><label
											name="visa_entry" id="visa_entry"
											ondblclick="callShowDiv(this);"><%=label.get("visa_entry")%></label></td>
										<td colspan="1" width="10" bgcolor="#EEF4FB"><label
											name="issue_place" id="issue_place"
											ondblclick="callShowDiv(this);"><%=label.get("issue_place")%></label></td>
										<td colspan="1" width="10" nowrap="nowrap" bgcolor="#EEF4FB"><label
											name="issue_authority" id="issue_authority"
											ondblclick="callShowDiv(this);"><%=label.get("issue_authority")%></label></td>
										<td colspan="1" width="10" nowrap="nowrap" bgcolor="#EEF4FB"><label
											name="issue_date" id="issue_date"
											ondblclick="callShowDiv(this);"><%=label.get("issue_date")%></label></td>
										<td colspan="1" width="10" nowrap="nowrap" bgcolor="#EEF4FB"><label
											name="valid_upto" id="valid_upto"
											ondblclick="callShowDiv(this);"><%=label.get("valid_upto")%></label></td>
										<td colspan="1" width="15" nowrap="nowrap" bgcolor="#EEF4FB"><label
											name="address_details" id="address_details"
											ondblclick="callShowDiv(this);"><%=label.get("address_details")%></label></td>

										<td nowrap="nowrap" colspan="1" width="5%" bgcolor="#EEF4FB">Edit|Delete</td>
									</tr>
									<%!int loopCount = 0;%>
									<%
									int n = 0;
									%>
									<s:iterator value="visaDetailsList" status="stat">
										<tr>
											<td width="5%" class="sortableTD" align="center"><s:hidden
												name="srNo" /><%=++n%><s:hidden name="visaCode" /></td>

											<td width="10%" align="left" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property
												value="visaNumberItt" /> <s:hidden name="visaNumberItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap" title="<s:property value="countryItt"/>">&nbsp;
												<s:property value="abbrCountry" />
											    <s:hidden name="countryItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property value="visaTypeItt" />
											<s:hidden name="visaTypeItt" /></td>

											<td width="5%" align="center" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property value="visaEntryItt" />
											<s:hidden name="visaEntryItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap"
												title="<s:property
												value="issuePlaceItt" />">&nbsp;
											<s:property value="abbrIssuePlace" /> <s:hidden
												name="issuePlaceItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property
												value="issueAuthorityItt" /> <s:hidden
												name="issueAuthorityItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property value="issueDateItt" />
											<s:hidden name="issueDateItt" /></td>

											<td width="10%" align="center" class="sortableTD"
												nowrap="nowrap">&nbsp; <s:property value="validUptoItt" />
											<s:hidden name="validUptoItt" /></td>

											<td width="15%" align="center" class="sortableTD"
												title="<s:property value="addressItt" />">&nbsp; <s:property
												value="abbrAddress" /> <s:hidden name="addressItt" /></td>
											<td width="5%" align="center" class="sortableTD"
												id='ctrlHide'><img
												src="../pages/mypage/images/icons/edit.png" width="10"
												height="10" title="Edit Record"
												onclick="callForEditData('<%=n%>','<s:property value="visaCode"/>')" />&nbsp|

											<img src="../pages/mypage/images/icons/delete.png" width="10"
												height="10"
												onclick="callDeleteRecord('<%=n%>','<s:property value="visaCode"/>')"
												title="Delete Record" /></td>
										</tr>
									</s:iterator>
									<%
										loopCount = n;
										//out.println("loopCount    " +loopCount);
									%>
								</table>
								</td>
							</tr>

							<s:if test="noData">
								<tr>
									<td colspan="7" align="center"><font color="red">No
									data to display</font></td>
								</tr>
							</s:if>
						</table>

						<tr>
					<td height="0">
					<table width="100%" border="0" align="right" cellpadding="2"
						cellspacing="3">

						<s:if test="editFlag">
							<tr>
								<s:if test="insertFlg">
									<td width="100%" align="right"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="5%"><a href="#" onclick="callSave()"
										class="iconbutton">Save</a></td>
									<td>|</td>
								</s:if>
								<s:elseif test="updateFlg">
									<td width="100%" align="right"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="5%"><a href="#" onclick="callSave()"
										class="iconbutton">Save</a></td>
									<td>|</td>
								</s:elseif>
								<td width="5%"><a href="#"><img
									src="../pages/mypage/images/icons/cancel.png"
									onclick="cancel()" width="10" height="10" border="0" /></a></td>
								<td width="13%"><a href="#" onclick="cancel()"
									class="iconbutton">Cancel</a></td>
									<td>|</td>
							</tr>
						</s:if>

						<s:else>

							<tr>
							<s:if test="updateFlg">
							<td width="89%" align="right"><a href="#"><img
									src="../pages/mypage/images/icons/edit.png"
									onclick="callForEdit()" width="10" height="10" border="0" /></a></td>
								<td><a href="#" onclick="callForEdit()" class="iconbutton">Edit</a></td>
								<td>|</td>
							</s:if>
							<s:if test="isNotGeneralUser">
								
								<td><a href="#"><img
									src="../pages/mypage/images/icons/search.png"
									onclick="searchEmpVisa()" width="10" height="10" border="0" /></a></td>
								<td><a href="#" class="iconbutton"
									onclick="searchEmpVisa()"> Search</a></td>
									<td>|</td>
                            </s:if>
							</tr>
						</s:else>

					</table>
					</td>
				</tr>




					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</div>
			</td>
		</tr>
	</table>
</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script><!--


function callForEdit()
{
document.getElementById("paraFrm").action="VisaDetails_editPassport.action";
document.getElementById("paraFrm").submit();
}
function cancel(){
	document.getElementById("paraFrm").action="VisaDetails_cancelFunc.action";
	document.getElementById("paraFrm").submit();
}

function callForAdd(){
     //var passportNo = document.getElementById('paraFrm_passport').value;
     //var passportDateOfIssue = document.getElementById('paraFrm_passportDateOfIssue').value;
	 //var passportExpDate = document.getElementById('paraFrm_passportExpDate').value;
	 /**if(passportNo=="")
      {
      alert("Please Fill Your Passport Details First");
      return false;
      }**/
	 document.getElementById('paraFrm').action="VisaDetails_addItem.action";
	 document.getElementById('paraFrm').submit();                    
}





function callSave(){
		var passportNo = document.getElementById('paraFrm_passport').value;
		var passportName = document.getElementById('paraFrm_passportName').value;
		var fatherName = document.getElementById('paraFrm_fatherName').value;
		var motherName = document.getElementById('paraFrm_motherName').value;
		var passportIssueFrom = document.getElementById('paraFrm_passportIssueFrom').value;
		var passportDateOfIssue = document.getElementById('paraFrm_passportDateOfIssue').value;
		var passportExpDate = document.getElementById('paraFrm_passportExpDate').value;
		var passportUidNum = document.getElementById('paraFrm_passportUidNum').value;
		
		
		var passportNoLabel=document.getElementById('passport.no').innerHTML;
		var passportNameLabel=document.getElementById('passport.name').innerHTML;
		var fatherNameLabel=document.getElementById('passport.fathername').innerHTML;
		var motherNameLabel=document.getElementById('passport.motherName').innerHTML;
		var passportIssueFromLabel=document.getElementById('passport.issuefrom').innerHTML;
		var passportDateOfIssueLabel=document.getElementById('passport.dateOfIssue').innerHTML;
		var passportExpDateLabel=document.getElementById('passport.expiry').innerHTML;
		var passportUidNumLabel=document.getElementById('passport.uidno').innerHTML;
		

		///var total ='<%=loopCount%>'; 	
	
		
		 if(passportNo==""){
	   	 	alert("Please Enter " +passportNoLabel);
	    	return false;
	    }
	    	
	     if(passportDateOfIssue==""){
	   	 	alert("Please Enter/Select "+passportDateOfIssueLabel);
	    	return false;
	    }
	    
	     if(passportExpDate==""){
	   	 	alert("Please Enter/Select "+passportExpDateLabel);
	    	return false;
	    }
		if(!(passportNo=="")){
  				var count =0;
				var iChars =" ";
		  		for (var i = 0; i < passportNo.length; i++){				  			 
			  		if (iChars.indexOf(passportNo.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  					}
  				}
  				if(count==passportNo.length){
  					alert ("Blank Spaces Not Allowed in "+passportNoLabel);
  					return false;
  				}
  	  }		
	  if(!(passportName=="")){
  				var count =0;
				var iChars =" ";
		  		for (var i = 0; i < passportName.length; i++){				  			 
			  		if (iChars.indexOf(passportName.charAt(i))!= -1){
			  	 		count=count+1; 				  	
  					}
  			    }
  				if(count==passportName.length){
  					alert ("Blank Spaces Not Allowed "+passportNameLabel);
  					return false;
  				}  		
  	 }		
	 if(!(fatherName=="")){
  				var count =0;
				var iChars =" ";
		  		for (var i = 0; i < fatherName.length; i++){				  			
			  		if (iChars.indexOf(fatherName.charAt(i))!= -1){
			  	 		count=count+1; 				  	
  					}
  				}
  				if(count==fatherName.length){
  					alert ("Blank Spaces Not Allowed in "+fatherNameLabel);
  					return false;
  				}
  		
  	}		
	if(!(motherName=="")){
  			var count =0;
			var iChars =" ";
		  	for (var i = 0; i < motherName.length; i++){				  			 
			  	if (iChars.indexOf(motherName.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  				}
  			}
  			if(count==motherName.length){
  				alert ("Blank Spaces Not Allowed in "+motherNameLabel);
  				return false;
  			}  		
  	}
	if(!(passportIssueFrom=="")){
  			var count =0;
			var iChars =" ";
		  	for (var i = 0; i < passportIssueFrom.length; i++) {				  			 
			  	if (iChars.indexOf(passportIssueFrom.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  				}
  		    }
  			if(count==passportIssueFrom.length){
  				alert ("Blank Spaces Not Allowed in "+passportIssueFromLabel);
  				return false;
  			}  		
  	}	
	if(!(passportUidNum=="")){
  			var count =0;
			var iChars =" ";
		  	for (var i = 0; i < passportUidNum.length; i++){				  			 
			  	if (iChars.indexOf(passportUidNum.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  				}
  			}
  			if(count==passportUidNum.length){
  				alert ("Blank Spaces Not Allowed in "+passportUidNumLabel);
  				return false;
  			}  		
  	}	
  	if(!validateDate('paraFrm_passportDateOfIssue',"passport.dateOfIssue")){
				document.getElementById('paraFrm_passportDateOfIssue').focus();
				return false;   	
	   	}						
		if(!validateDate('paraFrm_passportExpDate',"passport.expiry")){
				document.getElementById('paraFrm_passportExpDate').focus();
				return false;   	
	   	}										
		if(!passportDateOfIssue == "" && !passportExpDate == ""){								
				if(!dateDifferenceEqual(passportDateOfIssue,passportExpDate,'paraFrm_passportDateOfIssue','passport.dateOfIssue','passport.expiry')){
						document.getElementById('paraFrm_passportExpDate').focus();
						return false;   	
	   			}			
}

document.getElementById('paraFrm').target="main";
	 document.getElementById('paraFrm').action="VisaDetails_savePassport.action";
	 document.getElementById('paraFrm').submit();	  
  	 return true;
}
function callForEditData(id,viscode)
{
        document.getElementById('paraFrm_paraId').value=viscode;
        document.getElementById("paraFrm").action="VisaDetails_editVisa.action";		
    	document.getElementById("paraFrm").submit();			 	
}

function callDeleteRecord(id,viscode){
  	document.getElementById('paraFrm_paraId').value=viscode;
    var conf=confirm("Are you sure to delete this record?");
   	if(conf) {   		   		
   	   	document.getElementById("paraFrm").action="VisaDetails_deleteVisaDetails.action";
    	document.getElementById("paraFrm").submit();
    } 
}			
 function callForSaveVisa(){
    	var visaNumber = document.getElementById('paraFrm_visaNumber').value;
        var country = document.getElementById('paraFrm_country').value;    	        
        var visaType = document.getElementById('paraFrm_visaType').value;        
       	var visaEntry = document.getElementById('paraFrm_visaEntry').value;           
        var issueDate = trim(document.getElementById('paraFrm_issueDate').value);		
		var validUpto = trim(document.getElementById('paraFrm_validUpto').value);
              
        var visaNumberLabel=document.getElementById('visa_number').innerHTML;
        var countryLabel=document.getElementById('country_name').innerHTML;
        var visaTypeLabel=document.getElementById('visa_type').innerHTML;
        var visaEntryLabel=document.getElementById('visa_entry').innerHTML;
        var issueDateLabel=document.getElementById('issue_date').innerHTML;
        var validUptoLabel=document.getElementById('valid_upto').innerHTML
      
       if(visaNumber==""){
   		 alert("Please Enter " +visaNumberLabel);
    	return false;
       }    
       if(!(visaNumber=="")){
  				var count =0;
				var iChars =" ";
		  		for (var i = 0; i < visaNumber.length; i++){				  			 
			  		if (iChars.indexOf(visaNumber.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  					   }
  					}
  					if(count==visaNumber.length){
  					alert ("Blank Spaces Not Allowed in Visa Number");
  					return false;
  				}  		
  	   }	    
	  if(country==""){
   			 alert("Please Enter "+countryLabel);
    			return false;
      }	    
      if(!(country=="")){
  			var count =0;
			var iChars =" ";
		  	for (var i = 0; i < country.length; i++){				  			 
			  	if (iChars.indexOf(country.charAt(i))!= -1){
			  	 		count=count+1; 				  	
  				}
  			}
  			if(count==country.length){
  				alert ("Blank Spaces Not Allowed in "+countryLabel);
  				return false;
  			}  		
  	}	    
    if(visaType==""){
    	alert("Please Enter "+visaTypeLabel);
    	return false;
    }
    if(!(visaType=="")){
  		var count =0;
		var iChars =" ";
		for (var i = 0; i < visaType.length; i++){				  			 
			  if (iChars.indexOf(visaType.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  			  }
  		}
  		if(count==visaType.length){
  			alert ("Blank Spaces Not Allowed in "+visaTypeLabel);
  			return false;
  		}  		
  	}
   if(visaEntry=="-1"){
   		 alert("Please Select "+visaEntryLabel);
   		 return false;
    }
			
	if(!validateDate('paraFrm_issueDate',"issue_date")){
			document.getElementById('paraFrm_issueDate').focus();
			return false;   	
	}				
	if(!validateDate('paraFrm_validUpto',"valid_upto")){
			document.getElementById('paraFrm_validUpto').focus();
			return false;   	
	}	
	if(issueDate=="")
	{
	alert("Please Enter/Select Visa "+issueDateLabel)
	return false;
	}		
	if(validUpto=="")		
	{
	alert("Please Enter/Select Visa "+validUptoLabel);
	return false;
	}					
	if(!issueDate == "" && !validUpto == ""){				
			if(!dateDifferenceEqual(issueDate,validUpto,'paraFrm_issueDate','issue_date','valid_upto')){
					document.getElementById('paraFrm_validUpto').focus();
					return false;   	
	   		}			
	}
	var visaAddress = document.getElementById('paraFrm_address').value;
		if(visaAddress.length > 200){
			alert("Maximum length of Address is 200 characters");
			return false;
		}        
	document.getElementById("paraFrm").action="VisaDetails_saveVisa.action";
	document.getElementById("paraFrm").submit();             
}

function searchEmpVisa()
{
	try{
  		 javascript:callsF9(500,325,'VisaDetails_f9action.action');  
  	   }
  	catch(e)
  	   {
  	   
  	   }	  
}

function callLength(type){ 
 if(type=='remainingChars'){
	var cmt =document.getElementById('paraFrm_address').value;
	var remain = 200 - eval(cmt.length);
	document.getElementById('paraFrm_remainingChars').value = remain;
	if(eval(remain)< 0){
		document.getElementById('paraFrm_address').style.background = '#FFFF99';
	}else document.getElementById('paraFrm_address').style.background = '#FFFFFF';
 }
}  

--></script>





