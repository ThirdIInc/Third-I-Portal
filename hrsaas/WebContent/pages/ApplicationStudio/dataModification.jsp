<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="DataModification" validate="true" id="paraFrm"
	theme="simple">


	<input type="hidden" name="editFlag" id="editFlag" />

	<table width="100%" border="0">



		<tr valign="top">
			<td valign="bottom" class="txt">

			<table width="100%" border="0" align="right" cellspacing="0"
				class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Data
					Modification </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td>

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">

						<tr>
							<td colspan="1" width="20%"><label name="select.list"
								id="select.list" ondblclick="callShowDiv(this);"><%=label.get("select.list")%></label></td>
							<td width="85%" colspan="3" height="22"><s:select
								name="datalist" list="map" headerKey="" headerValue="--select--"
								size="1" onchange="getCategory();" /></td>



						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<div id="genderTypediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<td>

							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong class="text_head">
									Gender Type</strong></td>
								</tr>
								<tr>
									<td colspan="3"><s:submit cssClass="save"
										action="DataModification_save" theme="simple" value="  Save"
										onclick="return formValidate('gender')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type" id="type"
										ondblclick="callShowDiv(this);"><%=label.get("type")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="genderType" size="30" maxlength="20" />
									<s:hidden name="hiddenEdit" /></td>

								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="value" id="value"
										ondblclick="callShowDiv(this);"><%=label.get("value")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="genderTypeFld" size="30" maxlength="1"
										onkeypress="return alphaNumeric();" /></td>

								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="genderTypeactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblgenderType">

										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no1" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="type1" ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="value1" ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="is.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>




										</tr>

										<%!int y = 0;%>
										<%!int d1 = 0;%>
										<%!int k = 0;%>

										<%
												try {

												String[][] gender = (String[][]) request
												.getAttribute("genderType");
												System.out.println("In Gender----------");

												y = gender.length;
												if (gender.length > 0) {
													for (k = 0; k < gender.length; k++) {
												System.out.println("gender in jsp is________"
														+ gender[k][0]);
												System.out.println("active  in jsp is________"
														+ gender[k][1]);
												System.out.println("value  in jsp is________"
														+ gender[k][2]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>
											<td width="40%"><input type="text" style="border: none;"
												name="genderTypeRow" id="genderTypeRow<%=k %>"
												value="<%= String.valueOf(gender[k][0]) %>"
												readonly="readonly" /><s:hidden name="genderTypeRow<%=k %>" /></td>

											<td width="30%"><input type="text" style="border: none;"
												name="genderTypeValue" id="genderTypeValue<%=k %>"
												value="<%= String.valueOf(gender[k][2]) %>"
												readonly="readonly" /><s:hidden
												name="genderTypeValue<%=k %>" /></td>





											<td width="10%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>
											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (gender[k][1].equals("Y")) {
											%> <input type="checkbox" checked="checked" align="left"
												class="checkbox" value="<%=k%>" id="genderTypeChk<%=k%>"
												name="confChk" onclick="callForDelete('<%=k%>')" /> <input
												type="hidden" name="genderTypeOp" id="genderTypeOp<%=k%>"
												value="0" /> <%
 } else {
 %> <input type="checkbox" class="checkbox" value="<%=k%>" align="left"
												id="genderTypeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="genderTypeOp" id="genderTypeOp<%=k%>" /> <%
 }
 %>
											</td>
										</tr>
										<%
												d1 = k + 1;
												System.out.println("value in jsp" + d1);
													}

												}

											} catch (Exception e) {
												e.printStackTrace();

											}
										%>

									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
				<tr>
					<td>
					<div id="marriagediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>


							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">

								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead"> Maritial Status</strong></td>
								</tr>
								<tr>
									<td colspan="3"><s:submit cssClass="save"
										action="DataModification_marriageSave" theme="simple"
										value="  Save" onclick="return formValidate('marriage')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type" id="type2"
										ondblclick="callShowDiv(this);"><%=label.get("type")%></label>
									<font color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="marriage" size="30" maxlength="20" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="value2" ondblclick="callShowDiv(this);"><%=label.get("value")%></label>
									<font color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="marriageFld" size="30" maxlength="1"
										onkeypress="return alphaNumeric();" /></td>

								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="marriage.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="marriageactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick="return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblmarriage">



										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no2" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="status" ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="value2" ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="marriage.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>


										</tr>


										<%
												try {

												String[][] marriageType = (String[][]) request
												.getAttribute("marriage");

												if (marriageType.length > 0) {
													for (int k = 0; k < marriageType.length; k++) {
												System.out.println("marriage is________"
														+ marriageType[k][0]);
												System.out.println("active  is________"
														+ marriageType[k][1]);
												System.out.println("value  is________"
														+ marriageType[k][2]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="marriageRow" id="marriageRow<%=k %>"
												value="<%= String.valueOf(marriageType[k][0]) %>"
												readonly="readonly" /><s:hidden name="marriageRow<%=k %>" /></td>
											<td width="10%"><input type="text" style="border: none;"
												name="marriageValue" id="marriageValue<%=k %>"
												value="<%= String.valueOf(marriageType[k][2]) %>"
												readonly="readonly" /><s:hidden name="marriageValue<%=k %>" /></td>




											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>
											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (marriageType[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="marriageChk<%=k%>" c name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="marriageOp" id="marriageOp<%=k%>" value="0" /> <%
 } else {
 %> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="marriageChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="marriageOp" id="marriageOp<%=k%>" /> <%
 }
 %>
											</td>
										</tr>
										<%
													d1 = k + 1;
													}

												}

											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>

									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
				<tr>
					<td>
					<div id="bloodGroupdiv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead">Blood Group</strong></td>
								</tr>
								<tr>
									<td colspan="4"><s:submit cssClass="save"
										action="DataModification_bloodSave" theme="simple"
										value="  Save" onclick="return formValidate('blood')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type" id="type4"
										ondblclick="callShowDiv(this);"><%=label.get("type")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="bloodGroup" size="30" maxlength="20" /></td>
								</tr>


								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="value3" ondblclick="callShowDiv(this);"><%=label.get("value")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="bloodGroupFld" size="30" maxlength="3" />


									</td>

								</tr>


								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="blood.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="bloodGroupactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>


								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblbloodGroup">



										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no3" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="blood.group1"
												ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="value4" ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="blood.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>




										</tr>

										<%
												try {

												String[][] bloodGroupType = (String[][]) request
												.getAttribute("bloodGroup");

												if (bloodGroupType.length > 0) {

													for (int k = 0; k < bloodGroupType.length; k++) {
												System.out.println("finalobj2 is________"
														+ bloodGroupType[k][0]);
												System.out.println("active in jsp________"
														+ bloodGroupType[k][1]);
												System.out.println("value  in jsp________"
														+ bloodGroupType[k][2]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="bloodGroupRow" id="bloodGroupRow<%=k %>"
												value="<%= String.valueOf(bloodGroupType[k][0]) %>"
												readonly="readonly" /></td>

											<td width="10%"><input type="text" style="border: none;"
												name="bloodGroupValue" id="bloodGroupValue<%=k %>"
												value="<%= String.valueOf(bloodGroupType[k][2]) %>"
												readonly="readonly" /><s:hidden
												name="bloodGroupValue<%=k %>" /></td>







											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>
											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (bloodGroupType[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="bloodGroupChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="bloodGroupOp" id="bloodGroupOp<%=k%>" value="0" /> <%
 } else {
 %> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="bloodGroupChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="bloodGroupOp" id="bloodGroupOp<%=k%>" /> <%
 }
 %>
											</td>




										</tr>
										<%
													d1 = k + 1;
													}

												}
											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>


									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
				<tr>
					<td>
					<div id="payModediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead">Paymode</strong></td>
								<tr>
									<td colspan="4"><s:submit cssClass="save"
										action="DataModification_paySave" theme="simple"
										value="  Save" onclick="return formValidate('pay')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type"
										id="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("type")%></label>
									<font color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="payMode" size="30" maxlength="20" /></td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="value5" ondblclick="callShowDiv(this);"><%=label.get("value")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="payModeFld" size="30" maxlength="2"
										onkeypress="return alphaNumeric();" /></td>

								</tr>


								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="paymode.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="payModeactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>


								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblpayMode">


										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no4" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="pay.mode1" ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="value6" ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="paymode.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>




										</tr>
										<%
												try {

												String[][] payType = (String[][]) request
												.getAttribute("payMode");

												if (payType.length > 0) {
													for (int k = 0; k < payType.length; k++) {
												System.out.println("finalobj2 is________"
														+ payType[k][0]);
												System.out.println("finalobj2 is________"
														+ payType[k][1]);
												System.out.println("value is________" + payType[k][2]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="payModeRow" id="payModeRow<%=k %>"
												value="<%= String.valueOf(payType[k][0]) %>"
												readonly="readonly" /></td>

											<td width="10%"><input type="text" style="border: none;"
												name="payModeValue" id="payModeValue<%=k %>"
												value="<%= String.valueOf(payType[k][2]) %>"
												readonly="readonly" /><s:hidden name="payModeValue<%=k %>" /></td>









											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>

											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (payType[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="payModeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="payModeOp" id="payModeOp<%=k%>" value="0" /> <%
 } else {
 %> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="payModeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="payModeOp" id="payModeOp<%=k%>" /> <%
 }
 %>
											</td>



										</tr>
										<%
												}
												}
											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>


									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
				<tr>
					<td>
					<div id="addressTypediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead"> Address</strong></td>
								</tr>
								<tr>
									<td colspan="4"><s:submit cssClass="save"
										action="DataModification_addressSave" theme="simple"
										value="  Save" onclick="return formValidate('address')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type"
										id="address.type" ondblclick="callShowDiv(this);"><%=label.get("type")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="addressType" size="30" maxlength="20" />

									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="address.value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label>
									<font color="red">*</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="addressTypeFld" size="30" maxlength="1"
										onkeypress="return alphaNumeric();" /></td>

								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="address.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="addressTypeactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tbladdressType">

										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no5" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="address.type1"
												ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="address.value1"
												ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="address.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>




										</tr>
										<%
												try {

												String[][] addressType = (String[][]) request
												.getAttribute("addressType");

												if (addressType.length > 0) {
													for (int k = 0; k < addressType.length; k++) {
												System.out.println("finalobj2 is________"
														+ addressType[k][0]);
												System.out.println("value is________"
														+ addressType[k][2]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="addressTypeRow" id="addressTypeRow<%=k %>"
												value="<%= String.valueOf(addressType[k][0]) %>"
												readonly="readonly" /></td>

											<td width="10%"><input type="text" style="border: none;"
												name="addressTypeValue" id="addressTypeValue<%=k %>"
												value="<%= String.valueOf(addressType[k][2]) %>"
												readonly="readonly" /><s:hidden
												name="addressTypeValue<%=k %>" /></td>







											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>


											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (addressType[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="addressTypeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="addressTypeOp" id="addressTypeOp<%=k%>" value="0" />
											<%
											} else {
											%> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="addressTypeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="addressTypeOp" id="addressTypeOp<%=k%>" /> <%
 }
 %>
											</td>



										</tr>
										<%
												}
												}
											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>


									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
				<tr>
					<td>
					<div id="incrementTypediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead">Increment Type</strong></td>

								</tr>
								<tr>
									<td colspan="4"><s:submit cssClass="save"
										action="DataModification_incrementSave" theme="simple"
										value="  Save" onclick="return formValidate('increment')" />
									<input type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type"
										id="increment.type" ondblclick="callShowDiv(this);"><%=label.get("type")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="incrementType" size="30" maxlength="20" />

									</td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="increment.value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label><font
										color="red"> *</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="incrementTypeFld" size="30" maxlength="2"
										onkeypress="return alphaNumeric();" /></td>

								</tr>


								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="increment.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="incrementTypeactive"
										list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblincrementType">

										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no10" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="increment.type1"
												ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="increment.value1"
												ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="increment.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>



										</tr>
										<%
												try {

												String[][] incrementType = (String[][]) request
												.getAttribute("incrementType");

												if (incrementType.length > 0) {
													for (int k = 0; k < incrementType.length; k++) {
												System.out.println("finalobj2 is________"
														+ incrementType[k][0]);
												System.out.println("finalobj2 is________"
														+ incrementType[k][1]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="incrementTypeRow" id="incrementTypeRow<%=k %>"
												value="<%= String.valueOf(incrementType[k][0]) %>"
												readonly="readonly" /></td>

											<td width="10%"><input type="text" style="border: none;"
												name="incrementTypeValue" id="incrementTypeValue<%=k %>"
												value="<%= String.valueOf(incrementType[k][2]) %>"
												readonly="readonly" /><s:hidden
												name="incrementTypeValue<%=k %>" /></td>









											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>


											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (incrementType[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="incrementTypeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="incrementTypeOp" id="incrementTypeOp<%=k%>" value="0" />
											<%
											} else {
											%> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="incrementTypeChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="incrementTypeOp" id="incrementTypeOp<%=k%>" /> <%
 }
 %>
											</td>


										</tr>
										<%
												}
												}
											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>





									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<!-- ADDED BY MANISH FOR ZONE --- BEGINS -->
				<tr>
					<td>
					<div id="zonediv" style="display: none">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<s:hidden name="paraId" />
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead">Zone</strong></td>

								</tr>
								<tr>
									<td colspan="4"><s:submit cssClass="save"
										action="DataModification_zoneSave" theme="simple"
										value="  Save" onclick="return formValidate('zone')" /> <input
										type="button" class="reset" value="    Reset  "
										onclick="resetData();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label name="type"
										id="zone.type" ondblclick="callShowDiv(this);"><%=label.get("type")%></label><font
										color="red">*</font> :</td>

									<td colspan="3" width="80%" height="28"><s:textfield
										theme="simple" name="zone" size="30" maxlength="20" /></td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label name="value"
										id="zone.value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label><font
										color="red"> *</font> :</td>

									<td colspan="3" width="40%" height="28"><s:textfield
										theme="simple" name="zoneFld" size="30" maxlength="2"
										onkeypress="return alphaNumeric();" /></td>

								</tr>


								<tr>
									<td colspan="1" width="20%"><label name="is.active"
										id="zone.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									:</td>

									<td colspan="3" width="40%" height="28"><s:select
										theme="simple" name="zoneactive" list="#{'Y':'Yes','N':'No'}" /></td>

								</tr>

								<tr>
									<td width="10%" colspan="1"></td>
									<td width="10%" colspan="1"><input type="button"
										value="Add " class="add" onclick=" return addGender();" /></td>

								</tr>
							</table>
							</td>
						</tr>


						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblzone">

										<tr>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="sr.no" id="sr.no11" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
											<td class="formth" width="40%" height="22" valign="top"><label
												name="type" id="zone.type1" ondblclick="callShowDiv(this);"><%=label.get("type")%></label></td>
											<td class="formth" width="30%" height="22" valign="top"><label
												name="value" id="zone.value1"
												ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
											<td class="formth" width="10%" height="22" valign="top">Edit</td>
											<td class="formth" width="10%" height="22" valign="top"><label
												name="is.active" id="zone.active1"
												ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>



										</tr>
										<%
												try {

												String[][] zone = (String[][]) request.getAttribute("zone");

												if (zone.length > 0) {
													for (int k = 0; k < zone.length; k++) {
												System.out.println("finalobj2 is________" + zone[k][0]);
												System.out.println("finalobj2 is________" + zone[k][1]);
										%>
										<tr>
											<td width="10%"><%=k + 1%></td>




											<td width="50%"><input type="text" style="border: none;"
												name="zoneRow" id="zoneRow<%=k %>"
												value="<%= String.valueOf(zone[k][0]) %>"
												readonly="readonly" /></td>

											<td width="10%"><input type="text" style="border: none;"
												name="zoneValue" id="zoneValue<%=k %>"
												value="<%= String.valueOf(zone[k][2]) %>"
												readonly="readonly" /><s:hidden name="zoneValue<%=k %>" /></td>









											<td width="40%"><input type="button" class="rowEdit"
												onclick="javascript:callForEdit('<%=k%>');"></td>


											<td width="10%" align="center" nowrap="nowrap">
											<%
											if (zone[k][1].equals("Y")) {
											%> <input type="checkbox" class="checkbox" checked="checked"
												value="<%=k%>" id="zoneChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="zoneOp" id="zoneOp<%=k%>" value="0" /> <%
 } else {
 %> <input type="checkbox" class="checkbox" value="<%=k%>"
												id="zoneChk<%=k%>" name="confChk"
												onclick="callForDelete('<%=k%>')" /> <input type="hidden"
												name="zoneOp" id="zoneOp<%=k%>" /> <%
 }
 %>
											</td>


										</tr>
										<%
												}
												}
											} catch (Exception e) {
												//e.printStackTrace();

											}
										%>





									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<!-- ADDED BY MANISH FOR ZONE ---- ENDS -->





			</table>

			<table width="100%" height="400px" border="0" cellpadding="0"
				cellspacing="0" background="white">
				<tr>
					<td></td>
			</table>
</s:form>



<script>

getCategory();

function getCategory()
{ //alert('x');
	try{
		var divName = document.getElementById('paraFrm_datalist');
		// alert('divName'+divName);
		var comboValue = document.getElementById('paraFrm_datalist').value;
		//var xyz= document.getElementById('paraFrm_active').value;
		
		//alert('combovalue'+comboValue );
		for(i=1;i<divName.length;i++)
			document.getElementById(divName.options[i].value+'div').style.display='none';
		if(comboValue != '--Select--'){
			document.getElementById(comboValue+'div').style.display='';
		
			
			}
	}catch(e)
	{
	}
	resetData();
}

</script>

<script type="text/javascript">














function callForEdit(sno)
{	

//alert('x'+sno);
	try{
	//alert(document.getElementById('paraFrm_datalist').value);
	var comboValue = document.getElementById('paraFrm_datalist').value;
	 //alert('value'+comboValue);
	//alert('___'+document.getElementById(comboValue+'Row'+sno).value);
 	document.getElementById('paraFrm_'+comboValue).value=document.getElementById(comboValue+'Row'+sno).value;
 	//alert('haha'+document.getElementById(comboValue+'Value'+sno).value);
 	document.getElementById('paraFrm_'+comboValue+'Fld').value=document.getElementById(comboValue+'Value'+sno).value;
 	document.getElementById('paraFrm_'+comboValue+'Fld').disabled="true";
 	if(document.getElementById(comboValue+'Chk'+sno).checked==true)
 		document.getElementById('paraFrm_'+comboValue+'active').value="Y";
 	else
 		document.getElementById('paraFrm_'+comboValue+'active').value="N";
   	document.getElementById('editFlag').value=sno;
   	return false;
   	}catch(e)
   	{
   		alert(e);
   	}
}

function addGender()
{
//var comboValue = document.getElementById('paraFrm_datalist').value;
		if(document.getElementById('editFlag').value != '')
		{
		//alert('x');
		 if(!validateAdd1())
		 {
		 //alert('y');
	     return false;
	     }
		else
		{
		//alert('1');
		var edFlag=editFld();
			if(edFlag==false){
			//alert('haha');
			return false;
			}
			else{
			//alert('laaaki');
			var comboValue = document.getElementById('paraFrm_datalist').value;
			document.getElementById('paraFrm_'+comboValue+'Fld').disabled="";
			//alert(2)
			}
			}
		}
else
{
var comboValue = document.getElementById('paraFrm_datalist').value;
var pre1 = document.getElementById('paraFrm_'+comboValue).value;

//alert(pre1);
if(pre1=='')
{
	alert('Please enter '+document.getElementById('type').innerHTML.toLowerCase());
	return false;
}

	var fieldName = ['paraFrm_'+comboValue,'paraFrm_'+comboValue+'Fld'];
		
		var lableName = ["type","value"];
		var flag = ["enter","enter"];
	//	alert('x');
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }

if(!validateAdd())
	return false;

var comboValue = document.getElementById('paraFrm_datalist').value;
//alert("Combo Value "+comboValue);
if((comboValue=='bloodGroup'))
{
//alert('x');
var data=document.getElementById('paraFrm_'+comboValue+'Fld').value;
//alert('length'+data.length);
if(data.length>0)
{
try{
var iChars = "`~!@#$%^&*=[]\\\';{}|\"<>?";
		  			for (var i = 0; i < data.length; i++)
		  			 {		  	
			  		if (iChars.indexOf(data.charAt(i)) != -1)
			  	 	{
				  	alert ("Please Enter Valid  value ");
				  	return false;
				  	
  					}
  					}
  					  }catch(e)
	{
		alert(e);
	
	}
}
}   
else if(comboValue=='payMode')
{
//alert('dd');
var data1=document.getElementById('paraFrm_'+comboValue+'Fld').value;
//alert('data1'+data1)
if(data1.length>2)
{
alert("you cannot enter more than two character");
return false;
}
}
  var tbl = document.getElementById('tbl'+comboValue);
  var lastRow = tbl.rows.length;
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  // alert("iteration is"+iteration);
   var edit=document.getElementById('paraFrm_hiddenEdit').value;
  // left cell
  var cellLeft = row.insertCell(0);
  
  var textNode = document.createTextNode(iteration);
  
  cellLeft.appendChild(textNode);
  
  
   var cell = row.insertCell(1);
   
   var val =document.getElementById('paraFrm_'+comboValue).value;
      
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.style.border='none';
  ed.name = comboValue+'Row';
  //alert('name'+ed.name);
  //alert(' Iterator '+eval(iteration-1));
  ed.id =  ed.name+''+eval(iteration-1);
 
 //alert('value for id'+ ed.id);

  //alert('id'+ed.id);
  ed.value=val;
  ed.size =20;
   ed.readOnly ='true';
  var act =document.getElementById('paraFrm_'+comboValue+'active').value;
  
 // alert('act'+act);

    cell.appendChild(ed);
    

  
 document.getElementById('paraFrm_'+comboValue).value='';
 
var cellLast = row.insertCell(2);
var cellChk = row.insertCell(3);
var cellhidden = row.insertCell(4);
    var cellval = row.insertCell(2);
cellChk.align="center";

    var ed1 = document.createElement('input');
     ed1.type = 'hidden';
    ed1.name = comboValue+'Op';
     //alert('hidden name'+ed1.name)
    ed1.id =  ed1.name+''+eval(iteration-1);
    
    //alert('hidden name'+ed1.id);
    ed1.readOnly ='true';
  




  var skillChk = document.createElement('input');
  var chkBox = document.createElement('input');
  //alert('button'+skillChk);
   skillChk.type = 'button';
   chkBox.type = 'checkbox';
   chkBox.align = 'right';
   skillChk.name = 'edit';
   chkBox.name = 'confChk';
   chkBox.value=eval(iteration-1);
  
  // alert('checkbox value'+ chkBox.value);
   skillChk.className="rowEdit";
  skillChk.id = 'confChkSkill'+iteration;
  chkBox.id = document.getElementById('paraFrm_datalist').value+'Chk'+eval(iteration-1);
    skillChk.onclick= function callForEdit(){
    //alert('dipti');
    document.getElementById('paraFrm_'+comboValue+'Fld').disabled="";
	document.getElementById('paraFrm_hiddenEdit').value=iteration;
 	document.getElementById('paraFrm_'+comboValue).value=document.getElementById(ed.id).value;
 	document.getElementById('paraFrm_'+comboValue+'Fld').value=document.getElementById(fd.id).value;
 	if(document.getElementById(comboValue+'Chk'+eval(iteration-1)).checked==true)
 		document.getElementById('paraFrm_'+comboValue+'active').value="Y";
 	else
 		document.getElementById('paraFrm_'+comboValue+'active').value="N";
   	document.getElementById('editFlag').value=eval(iteration-1);
   //	alert('999999'+document.getElementById('editFlag').value);
  		//document.getElementById("paraFrm").submit();
  };
  chkBox.onclick = function callForDelete()
   {
	   var comboValue = document.getElementById('paraFrm_datalist').value;   
	   if(document.getElementById(comboValue+'Chk'+eval(iteration-1)).checked == true)
	   	 	document.getElementById(comboValue+'Op'+eval(iteration-1)).value=eval(iteration-1);
	   else
	   		document.getElementById(comboValue+'Op'+eval(iteration-1)).value="";
   };
  cellLast.appendChild(skillChk);
  cellChk.appendChild(chkBox);
  cellhidden.appendChild(ed1);

var val1 =document.getElementById('paraFrm_'+comboValue+'Fld').value;
//alert('value for new'+val1);
 var fd = document.createElement('input');
  fd.type = 'text';
  fd.style.border='none';
   fd.name = comboValue+'Value';
  fd.id =  fd.name+''+eval(iteration-1);
   fd.value=val1;
  fd.size =20;
   fd.readOnly ='true';
    cellval.appendChild(fd);
  // alert(document.getElementById('paraFrm_'+comboValue+'Fld').value);
 document.getElementById('paraFrm_'+comboValue+'Fld').value='';
   
   
  if(act=='Y')
  {
  
  document.getElementById(chkBox.id).checked=true;
   document.getElementById(ed1.id).value="0";
  }
  
  }
 
	
	
	
	
	
	
} 



   
function editFld()
{
//alert('in editfld---');
	try{
	var comboValue = document.getElementById('paraFrm_datalist').value;
	//alert('in comboValue'+comboValue);
	if((comboValue=='genderType')||(comboValue=='marriage')||(comboValue=='addressType')||(comboValue=='bloodGroup')
	||(comboValue=='payMode')||(comboValue=='incrementType')||(comboValue=='zone'))
   {
 var data=document.getElementById('paraFrm_'+comboValue+'Fld').value;
//alert('length'+data.length);
var fieldName = ['paraFrm_'+comboValue,'paraFrm_'+comboValue+'Fld'];
		//alert('check');
		var lableName = ["type","value"];
		var flag = ["enter","enter"];
		
		if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }

 }
	
	

	
	
		var editFldId = document.getElementById('paraFrm_datalist').value+"Row"+document.getElementById('editFlag').value;
		var editFldId1 =document.getElementById('paraFrm_datalist').value+"Value"+document.getElementById('editFlag').value;
		if(document.getElementById('paraFrm_'+comboValue+'active').value=="Y")
		{
			document.getElementById(comboValue+'Op'+document.getElementById('editFlag').value).value=document.getElementById('editFlag').value;
			document.getElementById(comboValue+'Chk'+document.getElementById('editFlag').value).checked=true;
		}
		else
		{
			document.getElementById(comboValue+'Op'+document.getElementById('editFlag').value).value="";
			document.getElementById(comboValue+'Chk'+document.getElementById('editFlag').value).checked=false;
		}
		//alert('editFldId'+editFldId);
		//alert('editFldId1'+editFldId1);
		document.getElementById('editFlag').value = "";
		//alert('3333333==='+document.getElementById('paraFrm_datalist').value);
		document.getElementById(editFldId).value = document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value;
		document.getElementById(editFldId1).value = document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value+'Fld').value;
		//alert(''+document.getElementById('paraFrm_datalist').value);
		document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value = "";
		document.getElementById('paraFrm_'+comboValue+'Fld').value='';
	
			document.getElementById('paraFrm_'+comboValue+'active').value='Y';
		//alert('after----'+document.getElementById('paraFrm_datalist').value);
		
		//alert(''+document.getElementById('paraFrm_'+comboValue+'active').value);
		
	}catch(e)
	{
		//alert(e);
	}
	return true;
}
function callForDelete(id)
	   {
	 	  //alert(id);
	  
	     //var i=eval(id)-1;
	   if(document.getElementById('confChk'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value=id;
	   }
	   else
	   document.getElementById('hdeleteOp'+id).value="";
   		}



   		
   	function chkDelete()
	{
		 try{
		 if(chk())
		 {
			var con=confirm('Do you really want to remove the record ?');
		 	if(con)
		 	{
			   	document.getElementById('paraFrm').action="DataModification_remove.action";
			    document.getElementById('paraFrm').submit();
	    	}
	    	else
	   		{
			  	for(var a=0;;a++)
				   	document.getElementById(document.getElementById('paraFrm_datalist').value+'Chk'+a).checked=false;
		     	return false;
			}
	 	}
	 	else 
	 	{
		 	alert('Please select atleast one record to delete.');
		 	return false;
	 	}
	 	}catch(e)
	 	{
	 		alert(e);
	 	}
	}
	
	function chk()
	{
		//alert('x');
		try{
	  	for(var a=0;;a++)
	  	{
	  	
	  	//alert(''+document.getElementById(document.getElementById('paraFrm_datalist').value+'Chk'+a).checked);
	   	if(document.getElementById(document.getElementById('paraFrm_datalist').value+'Chk'+a).checked)
	 	    return true;
	 	 }
	  	return false;
	  	}catch(e)
	  	{
	  		return false;
	  	}
	}
	function callForDelete(id)
	   {
	   try{
	   //alert('id'+id);
	 	var comboValue = document.getElementById('paraFrm_datalist').value;   
	 	//alert(''+comboValue);
	 	//alert(''+comboValue+'Chk');
	  // var i=eval(id)-1;
	 //var box=document.getElementById(comboValue+'Chk').value;
	  //alert('box'+box); 
	  
	   if(document.getElementById(comboValue+'Chk'+id).checked == true)
	   {
	   //alert('x---'+id);	  
	    document.getElementById(comboValue+'Op'+id).value=id;
	    //alert('y---------');	 
	   }
	   else
	   document.getElementById(comboValue+'Op'+id).value="";
	    //alert(i+"flag=="+document.getElementById('confChkop'+id).checked);
	   //alert("id for delete"+document.getElementById('hdeleteOp'+id).value);
   		}
	
	
	catch(e)
	{
	alert(e);
	}
	
}	
	
function validateAdd()
{
//alert("In");
//alert('p'.toUpperCase());
var comboVal= document.getElementById('paraFrm_datalist').value;
var a=document.getElementsByName(comboVal+"Value");
var b=document.getElementsByName(comboVal+"Row");
//alert('length11'+a.length);
//alert('dddd'+a[0].value);






fld1=document.getElementById('paraFrm_'+comboVal).value;
for(var i=0;i<b.length;i++)
{
	if(fld1.toUpperCase()==(b[i].value).toUpperCase())
	{
		alert('Entered '+document.getElementById('type').innerHTML.toLowerCase()+''+'   already exist.Please enter different one');
		return false;
	}
}
var fld1=document.getElementById('paraFrm_'+comboVal+'Fld').value;
for(var i=0;i<a.length;i++)
{
if(fld1.toUpperCase()==(a[i].value).toUpperCase())
{
alert(' Entered '+document.getElementById('value').innerHTML.toLowerCase()+''+'  already exist.Please enter different one');
return false;
}
}

return true;
}

function validateAdd1()
{
//alert("In");
//alert('p'.toUpperCase());
var comboVal= document.getElementById('paraFrm_datalist').value;
var a=document.getElementsByName(comboVal+"Value");
var b=document.getElementsByName(comboVal+"Row");
//alert('length11'+a.length);
//alert('dddd'+a[0].value);

var dpfld=document.getElementById('editFlag').value;
//alert('dpfld'+dpfld);





 var fld1=document.getElementById('paraFrm_'+comboVal).value;
for(var i=0;i<b.length;i++)
{
//alert('ff'+b[dpfld].value.toUpperCase());
	if((fld1.toUpperCase()==(b[i].value).toUpperCase())&& (fld1.toUpperCase()!=(b[dpfld].value).toUpperCase()))
	{
		alert('Entered '+document.getElementById('type').innerHTML.toLowerCase()+' '+'   already exist.Please enter different one');
		return false;
	}
}
 fld1=document.getElementById('paraFrm_'+comboVal+'Fld').value;
for(var i=0;i<a.length;i++)
{
//alert('ff'+a[dpfld].value.toUpperCase());
if((fld1.toUpperCase()==(a[i].value).toUpperCase()) && (fld1.toUpperCase()!=(a[dpfld].value).toUpperCase()))
{
alert('Entered  '+document.getElementById('value').innerHTML.toLowerCase()+''+'     already exist.Please enter different one');
return false;
}
}


return true;
}


function formValidate(type)
{


//alert(''+document.getElementById('paraFrm_datalist').value);
//alert('haha'+document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value);
//alert('haha value---'+document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value+'Fld').value);
var type=document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value;
var value=document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value+'Fld').value;


if((type!=''|| value!=''))
{
alert('Please click on Add button');
return false;
}
document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value="";
	return true;
}
function resetData()
{
var comboValue = document.getElementById('paraFrm_datalist').value;
document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value).value="";
var value=document.getElementById('paraFrm_'+document.getElementById('paraFrm_datalist').value+'Fld').value="";
document.getElementById('paraFrm_'+comboValue+'Fld').disabled=false;
document.getElementById('editFlag').value = '';
}
	
</script>



