
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelAuthorities" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:head name="hidDtlCode" />
	<!-- Flagas used For Cancel Button -->

	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Management Authorities </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
				<s:if test="msgFlag">
					<tr>
						<td width="80%"><font color="red">The data of <s:property
							value="msg" /> branch is applicable for all branches.</font></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<s:hidden name="travelAuth"></s:hidden>



						<tr>
							<td nowrap="nowrap" colspan="1" width="22%"><label
								name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							<s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif>:</td>
							<td width="77%" colspan="3"><s:textfield size="25"
								readonly="true" name="branchName" /><s:if test="imgflag">
								<img id="imgSrch0" src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelAuthorities_f9Branch.action');">
							</s:if> <s:hidden name="branchId" /></td>
							</td>

						</tr>





						<tr>
							<td colspan="4" width="100%">
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="2"
										cellspacing="2">

										<tr height="20">
											<td width="15%" colspan="2"><strong><label
												class="set" name="travel.schDet" id="travel.schDet"
												ondblclick="callShowDiv(this);"><%=label.get("travel.schDet")%></label></strong></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.schNm" id="travel.schNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.schNm")%></label>
											<s:if test="%{flag}">
												<font color="red">*</font>
											</s:if><s:elseif test="%{editFlag}">
												<font color="red">*</font>
											</s:elseif><s:elseif test="%{dblFlag}">
												<font color="red">*</font>
											</s:elseif>:</td>
											<td width="75%" colspan="1"><s:textfield name="schName"
												readonly="true" size="25" /><s:if test="imgflag">
												<img id="imgSrch1"
													src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9EmployeeSch.action');">
											</s:if> <s:hidden name="hidSchCode" /><s:hidden name="hidSchToken" /></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.altSchNm" id="travel.altSchNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.altSchNm")%></label>
											:</td>
											<td width="75%" colspan="1"><s:textfield
												name="altSchName" readonly="true" size="25" /><s:if
												test="imgflag">
												<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9EmployeeAltSch.action');">
											</s:if> <s:hidden name="altSchCode" /><s:hidden
												name="hidAltSchCode" /><s:hidden name="hidAltSchToken" />


											<s:if test="imgflag">
												<s:submit cssClass="add" theme="simple" value="Add"
													action="TravelAuthorities_addSch"
													onclick="return callAddSch();" />
											</s:if><s:else>
												<input type="button" class="add" theme="simple" value="Add " />
											</s:else></td>
											</td>
										</tr>





										<tr height="20">
											<td width="100%" colspan="4">
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2">

												<tr>
													<td align="center" class="formth" width="5%"><label
														class="set" id="travel.sn" name="travel.sn"
														ondblclick="callShowDiv(this);"><%=label.get("travel.sn")%></label></td>
													<td align="center" class="formth" width="25%"><label
														class="set" id="employee.id1" name="employee.id"
														ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
													<td align="center" class="formth" width="45%"><label
														class="set" id="travel.altSchNm1" name="travel.altSchNm"
														ondblclick="callShowDiv(this);"><%=label.get("travel.altSchNm")%></label></td>


													<td align="right" class="formth" nowrap="nowrap" width="5%"><s:if
														test="imgflag">
														<s:submit cssClass="delete" theme="simple"
															value="    Remove  " onclick=" return chkDelete1();" />
													</s:if><s:else>
														<input type="button" class="delete" theme="simple"
															value="Remove" />
													</s:else></td>


													<%
													int count1 = 0;
													%>
													<%!int d1 = 0;%>
													<%
													int ii = 0;
													%>


													<s:iterator value="schlist">

														<tr <%if(count1%2==0){
									%> class="tableCell1"
															<%}else{%> class="tableCell2" <%	}count1++; %>
															onmouseover="javascript:newRowColor(this);"
															onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
															ondblclick="javascript:callForEditSch('<s:property value="empToken"/>','<s:property value="itEmpName"/>','<%=ii+1%>');">



															<td width="10%" class="sortableTD"><%=++ii%><input
																type="hidden" name="srNo1" value="<%=ii%>" /></td>

															<td class="sortableTD"><s:property value="empToken" /><s:hidden
																name="itEmpId" /><s:hidden name="empToken" /></td>
															<td class="sortableTD"><s:property value="itEmpName" /><s:hidden
																name="itEmpName" /></td>
															<input type="hidden" name="hdeleteOp"
																id="hdeleteOp<%=ii%>" />
															<td width="10%" align="center" nowrap="nowrap"
																class="sortableTD"><input type="checkbox"
																class="checkbox" value="N" id="confChkop<%=ii%>"
																name="confChkop" onclick="callForDelete('<%=ii%>')" /></td>
														</tr>

													</s:iterator>
													<%
													d1 = ii;
													%>
												</tr>

												<s:hidden name="tableLength" />
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
							<td colspan="4">
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="2"
										cellspacing="2">

										<tr height="20">
											<td width="15%" colspan="2"><strong><label
												class="set" id="travel.aprDet" name="travel.aprDet"
												ondblclick="callShowDiv(this);"><%=label.get("travel.aprDet")%></label></strong></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.aprNm" id="travel.aprNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.aprNm")%></label>
											<s:if test="%{flag}">
												<font color="red">*</font>
											</s:if><s:elseif test="%{editFlag}">
												<font color="red">*</font>
											</s:elseif><s:elseif test="%{dblFlag}">
												<font color="red">*</font>
											</s:elseif>:</td>
											<td width="75%" colspan="1"><s:textfield
												name="approverName" readonly="true" size="25" /><s:if
												test="imgflag">
												<img id="imgSrch1"
													src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9Employee.action');">
											</s:if> <s:hidden name="hidApprover" /><s:hidden name="hidAppToken" /></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.altAprNm" id="travel.altAprNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.altAprNm")%></label>
											:</td>
											<td width="75%" colspan="1"><s:textfield
												name="altApprover" readonly="true" size="25" /><s:if
												test="imgflag">
												<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9EmployeeApp.action');">
											</s:if> <s:hidden name="hidAltApprover" /><s:hidden
												name="altAppCode" /><s:hidden name="hidAltAppToken" /> <s:if
												test="imgflag">
												<s:submit cssClass="add" theme="simple" value="Add"
													action="TravelAuthorities_addApr"
													onclick="return callAddApr();" />
											</s:if><s:else>
												<input type="button" class="add" theme="simple" value="Add" />
											</s:else></td>
											</td>
										</tr>



										<tr height="20">
											<td width="100%" colspan="4">
											<table width="100%" border="0" cellpadding="2"
												cellspacing="2">

												<tr>
													<td align="center" class="formth" width="5%"><label
														class="set" id="travel.sn1" name="travel.sn"
														ondblclick="callShowDiv(this);"><%=label.get("travel.sn")%></label></td>
													<td align="center" class="formth" width="25%"><label
														class="set" id="employee.id2" name="employee.id"
														ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
													<td align="center" class="formth" width="45%"><label
														class="set" id="travel.altAprNm1" name="travel.altAprNm"
														ondblclick="callShowDiv(this);"><%=label.get("travel.altAprNm")%></label></td>


													<td align="right" class="formth" nowrap="nowrap" width="5%"><s:if
														test="imgflag">
														<s:submit cssClass="delete" theme="simple" value="Remove"
															onclick=" return chkDelete2();" />
													</s:if><s:else>
														<input type="button" class="delete" theme="simple"
															value="Remove" />
													</s:else></td>


													<%
													int count2 = 0;
													%>
													<%!int d2 = 0;%>
													<%
													int i2 = 0;
													%>


													<s:iterator value="aprlist">

														<tr <%if(count2%2==0){
									%> class="tableCell1"
															<%}else{%> class="tableCell2" <%	}count1++; %>
															onmouseover="javascript:newRowColor(this);"
															onmouseout="javascript:oldRowColor(this,<%=count2%2 %>);"
															ondblclick="javascript:callForEditSch('<s:property value="empToken"/>','<s:property value="itEmpName"/>','<%=i2+1%>');">



															<td width="10%" class="sortableTD"><%=++i2%><input
																type="hidden" name="srNo2" value="<%=i2%>" /></td>

															<td class="sortableTD"><s:property value="aprToken" /><s:hidden
																name="itAprId" /><s:hidden name="aprToken" /></td>
															<td class="sortableTD"><s:property value="itAprName" /><s:hidden
																name="itAprName" /></td>
															<input type="hidden" name="hdeleteApr"
																id="hdeleteApr<%=i2%>" />
															<td width="10%" align="center" nowrap="nowrap"
																class="sortableTD"><input type="checkbox"
																class="checkbox" value="N" id="confChkApr<%=i2%>"
																name="confChkApr" onclick="callForDeleteApr('<%=i2%>')" /></td>
														</tr>

													</s:iterator>
													<%
													d2 = i2;
													%>
												</tr>

												<s:hidden name="tableLength2" />
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
							<td colspan="4">
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="2"
										cellspacing="2">

										<tr height="20">
											<td width="15%" colspan="2"><strong><label
												class="set" id="travel.accDet" name="travel.accDet"
												ondblclick="callShowDiv(this);"><%=label.get("travel.accDet")%></label></strong></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.accNm" id="travel.accNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.accNm")%></label>
											<s:if test="%{flag}">
												<font color="red">*</font>
											</s:if><s:elseif test="%{editFlag}">
												<font color="red">*</font>
											</s:elseif><s:elseif test="%{dblFlag}">
												<font color="red">*</font>
											</s:elseif>:</td>
											<td width="75%" colspan="1"><s:textfield name="accName"
												readonly="true" size="25" /><s:if test="imgflag">
												<img id="imgSrch1"
													src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9EmployeeAcc.action');">
											</s:if> <s:hidden name="hidAccName" /><s:hidden name="hidAccToken" /></td>

										</tr>
										<tr>
											<td width="21%" colspan="1"><label class="set"
												name="travel.altAccNm" id="travel.altAccNm"
												ondblclick="callShowDiv(this);"><%=label.get("travel.altAccNm")%></label>
											:</td>
											<td width="75%" colspan="1"><s:textfield
												name="altAccName" readonly="true" size="25" /><s:if
												test="imgflag">
												<img src="../pages/images/recruitment/search2.gif"
													class="iconImage" height="18" align="absmiddle" width="18"
													onclick="javascript:callsF9(500,325,'TravelAuthorities_f9EmployeeAltAcc.action');">
											</s:if> <s:hidden name="hidAltAccName" /><s:hidden
												name="altAccCode" /><s:hidden name="hidAltAccToken" /> <s:if
												test="imgflag">
												<s:submit cssClass="add" theme="simple" value="Add"
													action="TravelAuthorities_addAcc"
													onclick="return callAddAcc();" />
											</s:if><s:else>
												<input type="button" class="add" theme="simple" value="Add" />
											</s:else></td>
											</td>
										</tr>



										<tr height="20">
											<td width="100%" colspan="4">
											<table width="100%" border="0" cellpadding="2"
												cellspacing="2">

												<tr>
													<td align="center" class="formth" width="5%"><label
														class="set" id="travel.sn2" name="travel.sn"
														ondblclick="callShowDiv(this);"><%=label.get("travel.sn")%></label></td>
													<td align="center" class="formth" width="25%"><label
														class="set" id="employee.id3" name="employee.id"
														ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
													<td align="center" class="formth" width="45%"><label
														class="set" id="travel.altAccNm1" name="travel.altAccNm"
														ondblclick="callShowDiv(this);"><%=label.get("travel.altAccNm")%></label></td>


													<td align="right" class="formth" nowrap="nowrap" width="5%"><s:if
														test="imgflag">
														<s:submit cssClass="delete" theme="simple" value="Remove"
															onclick=" return chkDelete3();" />
													</s:if><s:else>
														<input type="button" class="delete" theme="simple"
															value="Remove" />
													</s:else></td>


													<%
													int count3 = 0;
													%>
													<%!int d3 = 0;%>
													<%
													int i3 = 0;
													%>


													<s:iterator value="acclist">

														<tr <%if(count3%2==0){
									%> class="tableCell1"
															<%}else{%> class="tableCell2" <%	}count3++; %>
															onmouseover="javascript:newRowColor(this);"
															onmouseout="javascript:oldRowColor(this,<%=count3%2 %>);"
															ondblclick="javascript:callForEditSch('<s:property value="itAccId"/>','<s:property value="itAccName"/>','<%=i2+1%>');">



															<td width="10%" class="sortableTD"><%=++i3%><input
																type="hidden" name="srNo3" value="<%=i3%>" /></td>

															<td class="sortableTD"><s:property value="accToken" /><s:hidden
																name="itAccId" /><s:hidden name="accToken" /></td>
															<td class="sortableTD"><s:property value="itAccName" /><s:hidden
																name="itAccName" /></td>
															<input type="hidden" name="hdeleteAcc"
																id="hdeleteAcc<%=i3%>" />
															<td width="10%" align="center" nowrap="nowrap"
																class="sortableTD"><input type="checkbox"
																class="checkbox" value="N" id="confChkAcc<%=i3%>"
																name="confChkAcc" onclick="callForDeleteAcc('<%=i3%>')" /></td>
														</tr>

													</s:iterator>
													<%
													d3 = i3;
													%>
												</tr>

												<s:hidden name="tableLength3" />
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
							<td colspan="4">
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">



								<tr>
									<td colspan="1" width="21%"><label class="set"
										name="travel.all" id="travel.all"
										ondblclick="callShowDiv(this);"><%=label.get("travel.all")%></label>:</td>
									<td colspan="3" width="75%"><input type="checkbox"
										name="appFlag" <s:property value="appFlag"/> value="N"
										id="appFlag" onclick="return callapp();" /> <s:hidden
										value="appFlag" /></td>
									<s:hidden name="branchFlag" />
									<s:hidden name="hidBranchFlag" />
								</tr>


								<tr>
									<td height="15" class="formtext"><label class="set"
										name="travel.desc" id="travel.desc"
										ondblclick="callShowDiv(this);"><%=label.get("travel.desc")%></label>
									:</td>

									<td height="22" colspan="3"><s:if test="%{pageFlag}">
										<s:textarea name="description" cols="32" rows="4"
											readonly="true" />
									</s:if> <s:if test="%{onLoadFlag}">
										<s:textarea name="description" cols="32" rows="4"
											readonly="true"></s:textarea>
									</s:if> <s:elseif test="%{flag}">
										<s:textarea name="description" cols="32" rows="4"></s:textarea>
									</s:elseif> <s:elseif test="%{saveFlag}">
										<s:label name="description"></s:label>
									</s:elseif> <s:elseif test="%{editFlag}">
										<s:textarea name="description" cols="32" rows="4"></s:textarea>
									</s:elseif> <s:elseif test="%{dblFlag}">
										<s:textarea name="description" cols="32" rows="4"></s:textarea>
									</s:elseif></td>
								</tr>

								<tr>
									<td height="22" class="formtext"><label class="set"
										name="travel.sts" id="travel.sts"
										ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label>
									:</td>
									<td height="22"><s:if test="%{pageFlag}">
										<s:select name="status" disabled="false"
											list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" />
									</s:if> <s:if test="%{onLoadFlag}">
										<s:select name="status" disabled="true"
											list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" />
									</s:if><s:elseif test="%{flag}">
										<s:select name="status" list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" />
									</s:elseif> <s:elseif test="%{saveFlag}">
										<s:label name="status" />
									</s:elseif><s:elseif test="%{editFlag}">
										<s:select name="status" list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" />
									</s:elseif> <s:elseif test="%{dblFlag}">
										<s:select name="status" list="#{'A':'Active','D':'Deactive'}"
											cssStyle="width:180;z-index:5;" />
									</s:elseif></td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>



						<tr>
							<td colspan="4">

							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="75%"><jsp:include
										page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

									<td align="right"><b>Page:</b> <%
 	int total1 = (Integer) request.getAttribute("abc");
 	int PageNo1 = (Integer) request.getAttribute("xyz");
 %> <%
 if (!(PageNo1 == 1)) {
 %><a href="#" onclick="callPage('1');"> <img
										src="../pages/common/img/first.gif" width="10" height="10"
										class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /></a> <%
 	}
 	if (total1 < 5) {
 		for (int i = 1; i <= total1; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}

 	if (total1 >= 5) {
 		for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}
 	if (!(PageNo1 == 1)) {
 %>...<a href="#" onclick="next()"> <img
										src="../pages/common/img/next.gif" class="iconImage" /> </a>
									&nbsp;<a href="#" onclick="callPage('<%=total1%>');"> <img
										src="../pages/common/img/last.gif" width="10" height="10"
										class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="on()" id="selectname">
										<%
										for (int i = 1; i <= total1; i++) {
										%>

										<option value="<%=i%>"><%=i%></option>
										<%
										}
										%>
									</select></td>
								</tr>
							</table>
							</td>
						</tr>



						<tr>
							<td colspan="7">
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">

								<tr>

									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1">
										<tr>
											<td colspan="8"><strong class="forminnerhead"><label
												class="set" name="travel.auth" id="travel.auth"
												ondblclick="callShowDiv(this);"><%=label.get("travel.auth")%></label></strong></td>
										</tr>
										<tr>
											<s:hidden name="myPage" id="myPage" />
											<td class="formth"><label class="set" name="travel.sn"
												id="travel.sn3" ondblclick="callShowDiv(this);"><%=label.get("travel.sn")%></label></td>
											<td class="formth" align="center" width="20%"><label
												class="set" name="branch" id="branch1"
												ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
											<td class="formth" align="center" width="20%"><label
												class="set" name="travel.schNm" id="travel.schNm1"
												ondblclick="callShowDiv(this);"><%=label.get("travel.schNm")%></label></td>
											<td class="formth" align="center" width="20%"><label
												class="set" name="travel.aprNm" id="travel.aprNm1"
												ondblclick="callShowDiv(this);"><%=label.get("travel.aprNm")%></label></td>
											<td class="formth" align="center" width="20%"><label
												class="set" name="travel.accNm" id="travel.accNm1"
												ondblclick="callShowDiv(this);"><%=label.get("travel.accNm")%></label></td>
											<td class="formth" align="center" width="10%"><label
												class="set" name="travel.sts" id="travel.sts1"
												ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label></td>
											<td class="formth" align="center" width="10%"><label
												class="set" name="travel.flag" id="travel.flag"
												ondblclick="callShowDiv(this);"><%=label.get("travel.flag")%></label></td>
											<td align="right" class="formth" nowrap="nowrap"><s:submit
												cssClass="delete" theme="simple" value="Delete"
												onclick=" return chkDelete();" /></td>


											<%
											int count = 0;
											%>
											<%!int d = 0;%>
											<%
													int cnt = PageNo1 * 20 - 20;
													int i = 0;
											%>
											<s:iterator value="typeList">

												<tr id="tr1" <%if(count%2==0){
									%>
													class="tableCell1" <%}else{%> class="tableCell2"
													<%	}count++; %> onmouseover="javascript:newRowColor(this);"
													title="Double click for edit"
													onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
													ondblclick="javascript:callForEdit('<s:property  value="TravelAuth"/>');">


													<td nowrap="nowrap" align="left" class="sortableTD"><%=++cnt%>
													<%
 ++i;
 %>
													</td>


													<td width="5%" align="left" class="sortableTD"><s:hidden
														value="%{TravelAuth}"></s:hidden> <input type="hidden"
														name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
														value="branchName" /></td>
													<td width="23%" align="left" class="sortableTD"><s:property
														value="schName" /></td>
													<td width="23%" align="left" class="sortableTD"><s:property
														value="approverName" /></td>
													<td width="23%" align="left" class="sortableTD"><s:property
														value="accName" /></td>

													<td width="23%" align="left" class="sortableTD"><s:property
														value="status" /></td>
													<td width="23%" align="center" class="sortableTD"><s:property
														value="hidBranchFlag" /></td>
													<input type="hidden" name="hdeleteCode"
														id="hdeleteCode<%=i%>" />

													<td align="center" nowrap="nowrap" class="sortableTD"><input
														type="checkbox" class="checkbox" id="confChk<%=i%>"
														name="confChk"
														onclick="callForDelete1('<s:property  value="TravelAuth"/>','<%=i%>')" /></td>
												</tr>

											</s:iterator>
											<%
											d = i;
											%>
										</tr>


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

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>


	function callapp(){
	//alert("callapp");
	if(document.getElementById('appFlag').checked == true)
		{		
		document.getElementById('paraFrm_hidBranchFlag').value='Y'; 
		}else{		
		document.getElementById('paraFrm_hidBranchFlag').value='N';
		}
	}
	//callDiable();
	
	function callDiable()
	{
		for(var i = 0; i < 2; i++) {
			document.getElementById('imgSrch'+i).style.display = 'none';
		}
		//document.getElementById('imgSrch').style.display = 'none';
		//document.getElementById('imgSrch1').style.display = 'none';
		/*var formElements = document.forms['paraFrm'].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
		{
			alert(formElements[i].name);
			document.getElementById('paraFrm_'+formElements[i].name).disable = 'true';
		}*/
	}
	

var f9Fields= ["paraFrm_branchName","paraFrm_schName","paraFrm_approverName","paraFrm_accName"];
var fieldName = ["paraFrm_branchName","paraFrm_schName","paraFrm_approverName","paraFrm_accName"];
var lableName = ["branch","travel.schNm","travel.aprNm","travel.accNm"];
var type = ['select','select','select','select'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="TravelAuthorities_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	/*if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;*/
	var brch=document.getElementById('paraFrm_branchName').value;
	var sch=document.getElementById('paraFrm_schName').value;
	var apr=document.getElementById('paraFrm_approverName').value;
	var acc=document.getElementById('paraFrm_accName').value;
	var altSch=document.getElementById('paraFrm_altSchName').value;
	var altApr=document.getElementById('paraFrm_altApprover').value;
	var altAcc=document.getElementById('paraFrm_altAccName').value;
	if(brch==""){
	alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
	return false;
	}
	if(sch==""){
	alert("Please select "+document.getElementById('travel.schNm').innerHTML.toLowerCase());
	return false;
	}
	if(altSch!=""){
	alert("Please add "+document.getElementById('travel.altSchNm').innerHTML.toLowerCase());
	return false;
	}
	if(apr==""){
	alert("Please select "+document.getElementById('travel.aprNm').innerHTML.toLowerCase());
	return false;
	}
	if(altApr!=""){
	alert("Please add "+document.getElementById('travel.altAprNm').innerHTML.toLowerCase());
	return false;
	}
	if(acc==""){
	alert("Please select "+document.getElementById('travel.accNm').innerHTML.toLowerCase());
	return false;
	}
	if(altAcc!=""){
	alert("Please add "+document.getElementById('travel.altAccNm').innerHTML.toLowerCase());
	return false;
	}
	var flg=document.getElementById('paraFrm_hidBranchFlag').value;
	if(flg=="Y"){
	var con=confirm('Do you really want to apply this record to all branches.')
	if(con){
			var save="TravelAuthorities_save.action";
		   document.getElementById('paraFrm').action=save;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
	}else{
	document.getElementById('paraFrm').action="TravelAuthorities_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	
	}
}

//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="TravelAuthorities_report.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="TravelAuthorities_edit.action";
	document.getElementById('paraFrm').submit();
}

//for report
function reportFun()
{
	document.getElementById('paraFrm').action="TravelAuthorities_report.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ?')
	if(con){
			var del="TravelAuthorities_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"TravelAuthorities_f9Action.action");
}

//For Cancel Button

function cancelFun()
{
	//alert("1pppk"+document.getElementById('paraFrm_loadFlag').value);
	//alert("2pppppp"+document.getElementById('paraFrm_addFlag').value);
	//alert("3pppppp"+document.getElementById('paraFrm_modFlag').value);
	
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in first");
		document.getElementById('paraFrm').action="TravelAuthorities_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="TravelAuthorities_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="TravelAuthorities_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="TravelAuthorities_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}


function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('<%=label.get("confirm.delete")%>');
	if(con){
		   document.getElementById('paraFrm').action="TravelAuthorities_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="TravelAuthorities_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}

function next(){
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="TravelAuthorities_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="TravelAuthorities_callPage1.action";
		document.getElementById('paraFrm').submit();
}
   
   
function newRowColor(cell){
			cell.className='onOverCell';

}
	
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}else {
	cell.className='tableCell1';
		}
}
	
	
	
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	
	   	document.getElementById("paraFrm").action="TravelAuthorities_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	//delete from list
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	  }
	  else
	    document.getElementById('hdeleteCode'+i).value="";
}
  	
function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete this record ?');
	 if(con){
	   document.getElementById('paraFrm').action="TravelAuthorities_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
		     for(var a=1;a<=flag;a++)
		     	document.getElementById('confChk'+a).checked = false;
	     return false;
	     }
	 } else {
	 	alert('Please select atleast one record to delete');
	 	 return false;
	 }
} 

function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
}
	
	


//delete Schedular
	function chkDelete1()
		{
		 if(chk1()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TravelAuthorities_deleteDtl.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkop'+a).checked=false;
	   document.getElementById('confChkop'+a).checked="";
	    document.getElementById('hdeleteOp'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk1(){
	 	var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkop'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDelete(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChkop'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdeleteOp'+id).value="";	   
   		}
   		
   //delete Approver
   function chkDelete2()
		{
		 if(chk2()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TravelAuthorities_deleteApr.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d2 %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkApr'+a).checked=false;
	   document.getElementById('confChkApr'+a).checked="";
	    document.getElementById('hdeleteApr'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk2(){
	 	var flag='<%=d2 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkApr'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDeleteApr(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChkApr'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteApr'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdeleteApr'+id).value="";	   
   		}
   		
   		
   		//delete Accountant
   		
   function chkDelete3()
		{
		 if(chk3()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TravelAuthorities_deleteAcc.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d3 %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkAcc'+a).checked=false;
	   document.getElementById('confChkAcc'+a).checked="";
	    document.getElementById('hdeleteAcc'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk3(){
	 	var flag='<%=d3 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkAcc'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDeleteAcc(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChkAcc'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteAcc'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdeleteAcc'+id).value="";	   
   		}
  /* function callForEditSch(empToken,itEmpName,sno){
   
   		document.getElementById('paraFrm_altSchCode').value=sno;
	 	document.getElementById('paraFrm_hidAltSchCode').value=empToken;
	 	document.getElementById('paraFrm_altSchName').value=itEmpName;
	   	
	   	
	   	return false;
  		//document.getElementById("paraFrm").submit();
  		}*/
  		
  		function previous(){
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="TravelAuthorities_callPage1.action";
	   document.getElementById('paraFrm').submit();
}   
	/*function callAdd(){
	 if(document.getElementById('paraFrm_hidBranchFlag').value=='Y')
			{		
		  document.getElementById('appFlag').checked = 'true';
			}else{
			document.getElementById('appFlag').checked = 'false';
			} 
	}*/"travel.schNm","travel.aprNm","travel.accNm"
	function  callAddSch(){ 
	var fields=["paraFrm_schName","paraFrm_altSchName"];
    var labels=["travel.schNm","travel.altSchNm"];
    var flag = ["select","select"];
 	if(!validateBlank(fields,labels,flag))
     return false;
   }
   
   function  callAddApr(){   
	var fields=["paraFrm_approverName","paraFrm_altApprover"];
    var labels=["travel.aprNm","travel.altAprNm"];
    var flag = ["select","select"]; 	
    	if(!validateBlank(fields,labels,flag))
     return false;

   }
   
   function  callAddAcc(){ 
  
	var fields=["paraFrm_accName","paraFrm_altAccName"];
    var labels=["travel.accNm","travel.altAccNm"];
    var flag = ["select","select"]; 
    	if(!validateBlank(fields,labels,flag))
     return false;

   }
   		
</script>
