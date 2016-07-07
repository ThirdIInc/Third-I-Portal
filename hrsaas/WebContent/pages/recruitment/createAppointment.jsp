<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CreateAppointment" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="appointmentStatus" />
	<s:hidden name="hiddenOfferCode" />
	<s:hidden name="hiddenAppointmentCode" />
	<s:hidden name="hiddenRequisitionCode"/>
	<s:hidden name="buttonName" />
	<s:hidden name="myPageOffer" />
	<s:hidden name="myPageApp" />
	<s:hidden name="show" />
	<s:hidden name="recordLength" />
	<s:hidden name="searchFlag" />
	<s:hidden name="chkSerch" />
	<s:hidden name="reqCodeFlag" />
	<s:hidden name="candFlag" />
	<s:hidden name="positionFlag" />
	<s:hidden name="hiringFlag" />
	<s:hidden name="dueDaysFlag" />
	<table class="formbg" width="100%">
		<!--main table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Create
					Appointment Letter</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4"><img
				src="../pages/common/css/default/images/space.gif" width="3"
				height="1" /></td>
		</tr>
		<tr>
			<td width="100%" colspan="4" align="left" nowrap="nowrap"><s:if
				test="appointmentDueFlag">
				<s:submit cssClass="token" value="Issue Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
				<s:submit cssClass="token" value="Create New Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="createNewOffer();" />
			</s:if> <s:elseif test="appointmentIssueFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
				<s:submit cssClass="token" value="Cancel Appointment"
					action="CreateAppointment_cancelAppointment"
					onclick="return cancelAppointment('I');" />
			</s:elseif> <s:elseif test="appointmentAccFlag">
				<s:submit cssClass="token" value="Cancel Appointment"
					action="CreateAppointment_cancelAppointment"
					onclick="return cancelAppointment('OA');" />
			</s:elseif> <s:elseif test="appointmentRejFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
			</s:elseif> <s:elseif test="appointmentCancelFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
			</s:elseif></td>

		</tr>

		<tr>
			<td width="100%" class="formbg" colspan="3"><strong
				class="text_head">Appointment Details</strong></td>
		</tr>


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 1 -->
				<tr>
					<td><a
						href="CreateAppointment_showAppointmentList.action?status=D">Appointment
					Due</a> | <a
						href="CreateAppointment_showAppointmentList.action?status=P"
						" 
                        		onclick="showApprovedAppointment();">Appointment
					Approval</a> | <a
						href="CreateAppointment_showAppointmentList.action?status=I">Appointment
					Issued</a> | <a
						href="CreateAppointment_showAppointmentList.action?status=OA">Appointment
					Accepted</a> | <a
						href="CreateAppointment_showAppointmentList.action?status=S">
					Appointment Rejected</a> | <a
						href="CreateAppointment_showAppointmentList.action?status=C">
					Appointment Cancelled</a></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength"></s:hidden>
			</table>
			<!-- table 1 --></td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<!-- table option -->
				<tr>
					<td colspan="2"><s:if test="clearFlag"></s:if><s:else>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>

								<td width="40%"><strong class="text_head"><s:if
									test="searchFlag">Applied Filter</s:if><s:else>
									<label class="set" name="searchApply.filter"
										id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></label>
								</s:else></strong></td>

								<td id="showFilter" align="right" colspan="2"><input
									type="button" value="Show Filter" cssClass="token"
									onclick="return callShowFilter();"></td>

								<td id="hideFilter" align="right"><input type="button"
									value="Hide Filter" cssClass="token"
									onclick="return callHideFilter();"></td>
							</tr>
						</table>
					</s:else></td>
				</tr>

				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="3"
						id="showFilterToApp">


						<tr>
							<td width="20%" height="22" class="formtext"><label
								class="set" name="reqs.code" id="reqs.code3"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="25"
								name="searchRequisitionCode" readonly="true" maxlength="30" />
							</td>
							<td width="10%"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateAppointment_f9Reqs.action');">


							<s:hidden name="searchHidRequisitionCode" /><s:hidden name="searchHidRequiredbyDate" /></td>
							

							<td width="20%" height="22" class="formtext"><label
								class="set" name="cand.name" id="cand.name3"
								ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:</td>
							<td width="13%" height="22"><s:textfield size="25"
								name="searchCandName" readonly="true" maxlength="30" /></td>
							<td width="10%"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateAppointment_f9Candidate.action');">
							</td>
							<s:hidden name="searchCandCode" />

						</tr>

						<tr>
							<td width="20%" height="22" class="formtext"><label
								class="set" name="position" id="position3"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="25"
								name="searchPosition" readonly="true" maxlength="30" /></td>
							<td width="10%" nowrap="nowrap"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateAppointment_f9Position.action');">
							<s:hidden name="searchPositionId" /></td>
							

							<td width="20%" height="22" class="formtext"><label
								class="set" name="hiring.mgr" id="hiring.mgr3"
								ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="25"
								name="searchHiringMgr" readonly="true" maxlength="30" /></td>
							<td width="10%"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateAppointment_f9Hiring.action');">

							<s:hidden name="searchHiringMgrId" /></td>

						</tr>
						<s:if
							test="%{ appointmentApprPendingFlag || appointmentApprRejFlag || appointmentApprFlag || appointmentDueFlag }">
							<tr>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="due" id="due3"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label> :</td>
								<td width="13%" height="22"><s:textfield size="10"
									name="searchDueSinceDays" onkeypress="return numbersOnly();"
									maxlength="3" /></td>
								<td width="10%"></td>	
							</tr>
						</s:if>
						<s:if
							test="%{appointmentIssueFlag || appointmentAccFlag || appointmentRejFlag || appointmentCancelFlag}">

							<tr>

								<td width="20%" nowrap="nowrap"><label class="set" name="app.frdate"
									id="app.frdate" ondblclick="callShowDiv(this);"><%=label.get("app.frdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appFrmDate" size="25"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									readonly="false" /></td>
									<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appFrmDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
								
								<td width="22%" nowrap="nowrap"><label class="set" name="app.todate"
									id="app.todate" ondblclick="callShowDiv(this);"><%=label.get("app.todate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appToDate" size="25"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									readonly="false" />
								</td>
								<td width="15%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appToDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
							</tr>
						</s:if>



						<s:if test="%{appointmentAccFlag}">

							<tr>

								<td width="20%"><label class="set" name="app.accFrdate"
									id="app.accFrdate" ondblclick="callShowDiv(this);"><%=label.get("app.accFrdate")%></label>
								:</td>
								<td width="13%"><s:textfield name="appAccFrmDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%"><s:a
									href="javascript:NewCal('paraFrm_appAccFrmDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
								
								<td width="20%"><label class="set" name="app.accTodate"
									id="app.accTodate" ondblclick="callShowDiv(this);"><%=label.get("app.accTodate")%></label>
								:</td>
								<td width="13%"><s:textfield name="appAccToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%"><s:a
									href="javascript:NewCal('paraFrm_appAccToDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
							</tr>
						</s:if>


						<s:if test="%{appointmentRejFlag}">

							<tr>

								<td width="20%"><label class="set" name="app.RejFrdate"
									id="app.RejFrdate" ondblclick="callShowDiv(this);"><%=label.get("app.RejFrdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appRejFrmDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appRejFrmDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
								
								<td width="20%" nowrap="nowrap"><label class="set" name="app.RejTodate"
									id="app.RejTodate" ondblclick="callShowDiv(this);"><%=label.get("app.RejTodate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appRejToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appRejToDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
							</tr>
						</s:if>


						<s:if test="%{appointmentCancelFlag}">

							<tr>

								<td width="20%" nowrap="nowrap"><label class="set" name="app.canFrdate"
									id="app.canFrdate" ondblclick="callShowDiv(this);"><%=label.get("app.canFrdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appCanFrmDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appCanFrmDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
								
								<td width="20%" nowrap="nowrap"><label class="set" name="app.canTodate"
									id="app.canTodate" ondblclick="callShowDiv(this);"><%=label.get("app.canTodate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="appCanToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" />
								</td>
								<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_appCanToDate','DDMMYYYY');"><img
									src="../pages/common/css/default/images/Date.gif" width="16"
									border="0" /></s:a></td>
							</tr>
						</s:if>

						
						<tr>
							<td align="center" colspan="5"><input type="button"
								class="token" theme="simple" value="Apply Filter"
								onclick="return applyFilter();" />&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return calReset();"
								value="Reset " /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>

					<td width="100%">
					<%
						String[] dispArr = (String[]) request.getAttribute("dispArr");
						if (dispArr != null && dispArr.length > 0) {
							int k = 0;
							int count = 0;
							if (dispArr.length % 2 == 0) {
								k = dispArr.length / 2;
							} else {
								k = (dispArr.length / 2) + 1;
							}
					%>


					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="enableFilterValue">
						<%
						for (int m = 0; m < k; m++) {
						%>


						<tr>
							<%
							if (count < dispArr.length) {
							%>

							<td width="20%" height="22" class="formtext"><%=dispArr[count]%></td>
							<%
							count++;
							%>
							<%
							}
							%>
							<%
							if (count < dispArr.length) {
							%>

							<td width="20%" height="22" class="formtext"><%=dispArr[count]%></td>
							<%
							count++;
							%>
							<%
							}
							%>


						</tr>

						<%
							}
						%>
						<tr>
							<td align="center" colspan="5">&nbsp; 
							<input type="button" class="reset" theme="simple" onclick="return callClear();" value="Clear Filter" />
							&nbsp; <input type="button" class="token" theme="simple" onclick="return callShowFilter();" value="Edit Filter" />	
								
								
								
								</td>
						</tr>

					</table>
					
					
					<%
							} 
						%>
					</td>
				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 2 -->
				<tr id="approvedAppointment">
					<td width="100%" colspan="9">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!-- table 3 -->
						<tr>
							<td><strong>Appointment Approval List</strong></td>
						</tr>
						<tr>
							<td><a
								href="CreateAppointment_showAppointmentList.action?status=P">Pending
							List</a> | <a
								href="CreateAppointment_showAppointmentList.action?status=A">Approved
							List</a> | <a
								href="CreateAppointment_showAppointmentList.action?status=R">Rejected
							List</a></td>
						</tr>
					</table>
					<!-- table 3 --></td>
				</tr>

				<tr>
					<td height="27" class="formtxt"><strong> <%String appointmentStatus = (String)request.getAttribute("appointmentListStatus");
	                    	if(appointmentStatus != null){out.println(appointmentStatus);}
	                    	else{out.println("Appointment Due List");}%> </strong></td>

					<%
					int totalPage = (Integer) request.getAttribute("totalPageApp");
					int pageNoApp = (Integer) request.getAttribute("PageNoApp");
					%>
					<s:if test="noData"></s:if>
					<s:else>
						<td align="right"><b>Page:</b> <input type="hidden"
							name="totalPage" id="totalPage" value="<%=totalPage%>"> <a
							href="#"
							onclick="callPage('1','F','<s:property value="appointmentStatus"/>');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P','P','<s:property value="appointmentStatus"/>');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" theme="simple" size="3"
							value="<%= pageNoApp%>"
							onkeypress="callPageText(event,'<s:property value="appointmentStatus"/>');return numbersOnly()"
							maxlength="4" /> of <%=totalPage%> <a href="#"
							onclick="callPage('N','N','<s:property value="appointmentStatus"/>')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>','L','<s:property value="appointmentStatus"/>');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else>
				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- table 4 -->

						<s:if test="appointmentDueFlag">
							<tr>
								<td width="6%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="due" id="due1"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label></b></td> 
								
								<td width="10%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
									
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.eval"
									id="candidate.evaluation1" ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.cv" id="view.cv1"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></</td>
							</tr>
						</s:if>


						<s:if test="appointmentIssueFlag">
							<tr>
								<td width="6%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="appointment.date" id="appointment.date1"
									ondblclick="callShowDiv(this);"><%=label.get("appointment.date")%></label></b></td>
								
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
									
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="offered.ctc" id="offered.ctc1"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
							   <td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.appointment"
									id="view.appointment1" ondblclick="callShowDiv(this);"><%=label.get("view.appointment")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="appointmentAccFlag">
							<tr>
								<td width="6%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="appointment.date" id="appointment.date1"
									ondblclick="callShowDiv(this);"><%=label.get("appointment.date")%></label></b></td>
									
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="acc.date"
									id="acc.date1" ondblclick="callShowDiv(this);"><%=label.get("acc.date")%></label></b></td>
								
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="offered.ctc" id="offered.ctc1"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
										
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.appointDetails" id="view.appointDetails"
									ondblclick="callShowDiv(this);"><%=label.get("view.appointDetails")%></label></b>
								</td> 
								 <td width="8%" valign="top" class="formth" align="center">
										<b><label class="set" name="view.appointment"
									id="view.appointment1" ondblclick="callShowDiv(this);"><%=label.get("view.appointment")%></label></b>
								</td>
							</tr>
						</s:if>

						<s:if test="appointmentRejFlag">
							<tr>
								<td width="6%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="appointment.date" id="appointment.date1"
									ondblclick="callShowDiv(this);"><%=label.get("appointment.date")%></label></b></td>
								
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="rej.date"
									id="rej.date1" ondblclick="callShowDiv(this);"><%=label.get("rej.date")%></label></b></td>
									
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
										
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="offered.ctc" id="offered.ctc1"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								 
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.appointDetails" id="view.appointDetails"
									ondblclick="callShowDiv(this);"><%=label.get("view.appointDetails")%></label></b>
								</td> 
								
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.appointment"
									id="view.appointment1" ondblclick="callShowDiv(this);"><%=label.get("view.appointment")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="appointmentCancelFlag">
							<tr>
								<td width="6%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="appointment.date" id="appointment.date1"
									ondblclick="callShowDiv(this);"><%=label.get("appointment.date")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="cancel.date" id="cancel.date1"
									ondblclick="callShowDiv(this);"><%=label.get("cancel.date")%></label></b></td>
								
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
									
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="offered.ctc" id="offered.ctc1"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								 <td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.appointment"
									id="view.appointment1" ondblclick="callShowDiv(this);"><%=label.get("view.appointment")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="appointmentApprPendingFlag">
							<tr>
								<td width="6%" valign="top" class="formth" align="center">
								<b><label class="set" name="serial.no" id="serial.no1"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>	
								<td width="8%" valign="top" class="formth" align="center"><b><label
									class="set" name="due" id="due2"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label></b></td>
								
								<td width="12%" valign="top" class="formth" align="center"><b><label
									class="set" name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
									 
								<td width="10%" valign="top" class="formth" align="center"><b><label
									class="set" name="view.eval" id="candidate.evaluation1"
									ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"><b><label
									class="set" name="view.cv" id="view.cv1"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="appointmentApprFlag">
							<tr>
								<td width="6%" valign="top" class="formth" align="center">
								<b><label class="set" name="serial.no" id="serial.no1"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>	
								<td width="8%" valign="top" class="formth" align="center"><b><label
									class="set" name="apprDate" id="apprDate1"
									ondblclick="callShowDiv(this);"><%=label.get("apprDate")%></label></b></td> 
								
								<td width="12%" valign="top" class="formth" align="center"><b><label
									class="set" name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
									
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.eval"
									id="candidate.evaluation1" ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.cv" id="view.cv1"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="appointmentApprRejFlag">
							<tr>
								<td width="6%" valign="top" class="formth" align="center">
								<b><label class="set" name="serial.no" id="serial.no1"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name1"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position1"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr1"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>	
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="rej.date" id="rej.date1"
									ondblclick="callShowDiv(this);"><%=label.get("rej.date")%></label></b></td>
									
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="joiningDateInList" id="joiningDateInList"
									ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label></b></td>
									
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.eval"
									id="candidate.evaluation1" ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.cv" id="view.cv1"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="noAppointmentDataFlag">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>
						<%
											int cnt1 = pageNoApp * 20 - 20;
											int m1 = 0;
											int countRow = 0;
								%>
						<%! int j = 0 ; %>
						<% int h = 1; int g=0;%>

						<s:if test="appointmentDueFlag">
							<s:iterator value="appointmentList">
									<tr  onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" > 
									<td width="6%" class="sortableTD"><input type="radio"
										name="radAppoint" id="<%="radAppoint"+g %>"
										onclick="callChk('<%="radioAppoint"+g %>')" /> <input
										type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>'
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode" id='<%="appointmentCode"+g %>' /> &nbsp;</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="dueDaysAppointment" />
									<s:hidden name="dueDaysAppointment" />&nbsp;</td>
									 
									 <td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									 
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeAppointment"/>', 
						                    			'<s:property value="candCodeAppointment"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:hidden name="resumeAppointment" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeAppointment"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentIssueFlag">
							<s:iterator value="appointmentList">
									<tr  onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >  
									<td class="sortableTD" width="6%"><input type="radio"
										name="radAppoint" id='<%="radAppoint"+g %>'
										onclick="callChk('<%="radioAppoint"+g %>')" /> <input
										type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>'
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> &nbsp;</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="center" nowrap="nowrap"><s:property
										value="appointmentDate" /> <s:hidden name="appointmentDate" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD" align="left"><s:property
										value="AppointmentOfferedCtc" /> <s:hidden
										name="AppointmentOfferedCtc" />&nbsp;</td> 
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="appointTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewAppointmentDetails('<s:property value="reqCodeAppointment"/>', 
						                    		'<s:property value="candCodeAppointment"/>', '<s:property value="appointTemplate"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentAccFlag">
							<s:iterator value="appointmentList">
							<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >  
								 	<td width="6%" class="sortableTD"><input type="radio"
										name="radAppoint" id='<%="radAppoint"+g %>'
										onclick="callChk('<%="radioAppoint"+g %>')" /> <input
										type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>'
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> &nbsp;</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="left"><s:property
										value="appointmentDate" /> <s:hidden name="appointmentDate" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="left"><s:property
										value="appointmentAcceptedDate" /> <s:hidden
										name="appointmentAcceptedDate" />&nbsp;</td>
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><s:property value="AppointmentOfferedCtc" />
									<s:hidden name="AppointmentOfferedCtc" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" class="token"
										value="View"
										onclick="viewOfferDetails('<s:property value="appointmentCode"/>','<s:property value="reqCodeAppointment"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="appointTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewAppointmentDetails('<s:property value="reqCodeAppointment"/>', 
						                    		'<s:property value="candCodeAppointment"/>', '<s:property value="appointTemplate"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentRejFlag">
							<s:iterator value="appointmentList">
								<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >   
									<td width="6%" class="sortableTD"><input type="radio"
										name="radAppoint" id='<%="radAppoint"+g %>'
										onclick="callChk('<%="radioAppoint"+g %>')" /> <input
										type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>'
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> &nbsp;</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="left"><s:property
										value="appointmentDate" /> <s:hidden name="appointmentDate" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="center" nowrap="nowrap"><s:property
										value="appointmentAcceptedDate" /> <s:hidden
										name="appointmentAcceptedDate" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><s:property value="AppointmentOfferedCtc" />
									<s:hidden name="AppointmentOfferedCtc" />&nbsp;</td>
									 
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" class="token"
										value="View"
										onclick="viewOfferDetails('<s:property value="appointmentCode"/>','<s:property value="reqCodeAppointment"/>')" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="appointTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewAppointmentDetails('<s:property value="reqCodeAppointment"/>', 
						                    		'<s:property value="candCodeAppointment"/>', '<s:property value="appointTemplate"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j;%>
						</s:if>

						<s:if test="appointmentCancelFlag">
							<s:iterator value="appointmentList">
								<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >    
									<td width="6%" class="sortableTD"><input type="radio"
										name="radAppoint" id='<%="radAppoint"+g %>'
										onclick="callChk('<%="radioAppoint"+g %>')" /> <input
										type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>'
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> &nbsp;</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /><!--
												<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
											<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="left"><s:property
										value="appointmentDate" /> <s:hidden name="appointmentDate" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD" align="center" nowrap="nowrap"><s:property
										value="appointmentAcceptedDate" /> <s:hidden
										name="appointmentAcceptedDate" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><s:property value="AppointmentOfferedCtc" />
									<s:hidden name="AppointmentOfferedCtc" />&nbsp;</td> 
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="appointTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewAppointmentDetails('<s:property value="reqCodeAppointment"/>', 
						                    		'<s:property value="candCodeAppointment"/>', '<s:property value="appointTemplate"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentApprPendingFlag">
							<s:iterator value="appointmentList">
								<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >     
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> <!--<input type="radio" name="radAppoint" id='<%="radAppoint"+g %>' onclick="callChk('<%="radioAppoint"+g %>')"/>
												<input type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>' value="N">&nbsp;
												
												
												--></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="10%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOfferCode" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="dueDaysAppointment" />
									<s:hidden name="dueDaysAppointment" />&nbsp;</td> 
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeAppointment"/>', 
						                    			'<s:property value="candCodeAppointment"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeAppointment" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeAppointment"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentApprFlag">
							<s:iterator value="appointmentList">
								<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return  viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >      
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /> <!--<input type="radio" name="radAppoint" id='<%="radAppoint"+g %>' onclick="callChk('<%="radioAppoint"+g %>')"/>
												<input type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>' value="N">&nbsp;
												
												
												
												--></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>
												&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="10%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOfferCode" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property
										value="appointmentApprovedDate" /> <s:hidden
										name="appointmentApprovedDate" />&nbsp;</td> 
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeAppointment"/>', 
						                    			'<s:property value="candCodeAppointment"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeAppointment" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeAppointment"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j; %>
						</s:if>

						<s:if test="appointmentApprRejFlag">
							<s:iterator value="appointmentList">
								<tr  onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeAppointment"/>', 'appointment');" title="Double click for view Requisition" >       
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt1%>
									<%++m1;%> 
									<s:hidden name="appointmentCode"
										id='<%="appointmentCode"+g %>' /><!--
					        					<input type="radio" name="radAppoint" id='<%="radAppoint"+g %>' onclick="callChk('<%="radioAppoint"+g %>')"/>
												<input type="hidden" name="radioAppoint" id='<%="radioAppoint"+g %>' value="N">&nbsp;
												
												
												--></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameAppointment" /> <!--<input type="button" class="token" value='<s:property value ="reqNameAppointment"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeAppointment"/>')"/>
												--><s:hidden name="reqNameAppointment" />
												<s:hidden name="reqCodeAppointment" id='<%="reqCodeAppointment"+g %>'/>&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameAppointment" /> <s:hidden
										name="candCodeAppointment" /><s:hidden
										name="canddNameAppointment" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionAppointment" /> <s:hidden
										name="positionAppointment" /></td>
									<td width="10%" class="sortableTD" valign="middle"><s:property
										value="hireMgrAppointment" /> <s:hidden
										name="hireMgrAppointment" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOfferCode" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property
										value="appointmentApprovedDate" /> <s:hidden
										name="appointmentApprovedDate" />&nbsp;</td> 
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeAppointment"/>', 
						                    			'<s:property value="candCodeAppointment"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeAppointment" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeAppointment"/>');" />&nbsp;</td>
								</tr>
								<%h++; g++; %>
							</s:iterator>
							<%m1=j;%>
						</s:if>
						<% j=h ; %>
					</table>
					<!-- table 4 --></td>
				</tr>
			</table>
			<!-- table 2 --></td>
			<input type="hidden" name="appointCount" id="appointCount"
				value="<%=g%>" />
		</tr>

		<tr>
			<td width="50%" align="left" nowrap="nowrap"><s:if test="appointmentDueFlag">
				<s:submit cssClass="token" value="Issue Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
				<s:submit cssClass="token" value="Create New Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="createNewOffer();" />
			</s:if> <s:elseif test="appointmentIssueFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
				<s:submit cssClass="token" value="Cancel Appointment"
					action="CreateAppointment_cancelAppointment"
					onclick="return cancelAppointment('I');" />
			</s:elseif> <s:elseif test="appointmentAccFlag">
				<s:submit cssClass="token" value="Cancel Appointment"
					action="CreateAppointment_cancelAppointment"
					onclick="return cancelAppointment('OA');" />
			</s:elseif> <s:elseif test="appointmentRejFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
			</s:elseif> <s:elseif test="appointmentCancelFlag">
				<s:submit cssClass="token" value="Revise Appointment"
					action="CreateAppointment_toAppointmentDetails"
					onclick="return createAppointment();" />
			</s:elseif></td>
			<td width="50%" align="Right"><s:if test="recordLength">
				<b>Number of records :&nbsp;<s:property value="totalRecords" /></b>
			</s:if></td>
		</tr>
	</table>
</s:form>
<script>
//window.onload=   document.getElementById('pageNoField').focus();
 
showApprovedAppointment();
function showApprovedAppointment(){
		appointmentStatus = document.getElementById("paraFrm_appointmentStatus").value;
		
		if(appointmentStatus == "P" || appointmentStatus == "A" || appointmentStatus == "R"){
			document.getElementById("approvedAppointment").style.display = '';
		}else{
			document.getElementById("approvedAppointment").style.display = 'none';
		}
	}
	
	function createAppointment(){
		var appointCount = document.getElementById("appointCount").value;
		var checkFlag  = false;
		
		document.getElementById("paraFrm_buttonName").value = "Y";
		
		for(var i=0; i<appointCount; i++){
			if(document.getElementById("radAppoint"+i).checked){
				checkFlag = true;
				document.getElementById("paraFrm_hiddenAppointmentCode").value = document.getElementById("appointmentCode"+i).value;
				document.getElementById("paraFrm_hiddenRequisitionCode").value = document.getElementById("reqCodeAppointment"+i).value;
			}
		}
		
		if(appointCount == 0){
			alert("There is no record in the list");
			return false;
		}
		
		if(!checkFlag){
			alert("Please select a candidate");
			return false;
		}
		return true;
	}
	
	function cancelAppointment(status){
		if(!createAppointment())return false;
		
		document.getElementById("paraFrm_appointmentStatus").value = status;
		//alert(document.getElementById("paraFrm_appointmentStatus").value);
		
		var conf = confirm("Do you really want to cancel the appointment?");
		
		if(conf){
			return true;
		}else{
	  		return false;
	  	}
	  	return true;
	}

	function viewRequisition(reqCode, type){
		var status = "";
		var action = "CreateAppointment_input.action";
		if(type == "appointment"){
			status = document.getElementById("paraFrm_appointmentStatus").value;
			action = "CreateAppointment_showAppointmentList.action";
		}
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction='+action+'&statusKey='+status;;
	    document.getElementById("paraFrm").submit();
	}

	function viewAppointmentDetails(reqsCode, candCode, templateCode){
		//document.getElementById('paraFrm').target = "_blank";
		document.getElementById("paraFrm").action = 'AppointmentDetails_previewappointment.action?requisitionCode='+reqsCode+'&candidateCode='+candCode+'&templateCode='+templateCode; 
	  	document.getElementById("paraFrm").submit();
	  	//document.getElementById('paraFrm').target = "main";
	}

	function viewInterviewDetails(reqCode, candCode){
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}

	function createNewOffer(){
		document.getElementById("paraFrm_buttonName").value = "";
	}
	
	function viewCV(fileName){
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CreateAppointment_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}

	// Created by Guru Prasad  	
 
function callShowFilter(){
  			document.getElementById("showFilter").style.display='none';//button ShowFilter
  			document.getElementById("hideFilter").style.display='';//button HideFilter
  			document.getElementById("showFilterToApp").style.display='';//to appear all fields for enter data
  			document.getElementById('enableFilterValue').style.display='none';
 }

 function callHideFilter(){
            calReset();
  			document.getElementById("showFilter").style.display='';//button ShowFilter 			
  			document.getElementById("hideFilter").style.display='none';//button HideFilter
  			document.getElementById("showFilterToApp").style.display='none';//to appear all fields for enter data
  			//document.getElementById("appliedFilterValue").style.display='none';//after apply filter that is in view mode
 } 
 
 callFilter();
	function callFilter(){
		    var chkSearch=document.getElementById('paraFrm_chkSerch').value;
	if(chkSearch=="")
	        {  
	        	document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('showFilterToApp').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	      }
	else
	      {
	      		document.getElementById('showFilterToApp').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
	        }
	    }
function applyFilter(){
var status=document.getElementById('paraFrm_appointmentStatus').value;
if(status=="I"){
	var fromDate=document.getElementById('paraFrm_appFrmDate').value;
	var toDate=document.getElementById('paraFrm_appToDate').value;
	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_appFrmDate','app.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_appToDate','app.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_appToDate', 'app.frdate','app.todate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="OA"){
	var fromDate=document.getElementById('paraFrm_appFrmDate').value;
	var toDate=document.getElementById('paraFrm_appToDate').value;
	var accFromDate=document.getElementById('paraFrm_appAccFrmDate').value;
   	var accToDate=document.getElementById('paraFrm_appAccToDate').value;
   	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
   	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_appFrmDate','app.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_appToDate','app.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_appToDate', 'app.frdate','app.todate'))
	  return false;
	}
	if(accFromDate!="")
	{
	  if(!validateDate('paraFrm_appAccFrmDate','app.accFrdate'))
	  return false; 
	}
	if(accToDate!="")
	{
	  if(!validateDate('paraFrm_appAccToDate','app.accTodate'))
	  return false;
	}
	if(accFromDate!="" && accToDate!="")
	{
	  if(!dateDifferenceEqual(accFromDate,accToDate,'paraFrm_appAccToDate', 'app.accFrdate','app.accTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" && accFromDate == "" && accToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="S"){
	var fromDate=document.getElementById('paraFrm_appFrmDate').value;
	var toDate=document.getElementById('paraFrm_appToDate').value;
	var rejFromDate=document.getElementById('paraFrm_appRejFrmDate').value;
   	var rejToDate=document.getElementById('paraFrm_appRejToDate').value;
   	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
   	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_appFrmDate','app.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_appToDate','app.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_appToDate', 'app.frdate','app.todate'))
	  return false;
	}
	if(rejFromDate!="")
	{
	  if(!validateDate('paraFrm_appRejFrmDate','app.RejFrdate'))
	  return false; 
	}
	if(rejToDate!="")
	{
	  if(!validateDate('paraFrm_appRejToDate','app.RejTodate'))
	  return false;
	}
	if(rejFromDate!="" && rejToDate!="")
	{
	  if(!dateDifferenceEqual(rejFromDate,rejToDate,'paraFrm_appRejToDate', 'app.RejFrdate','app.RejTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" && rejFromDate == "" && rejToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="C"){
	var fromDate=document.getElementById('paraFrm_appFrmDate').value;
	var toDate=document.getElementById('paraFrm_appToDate').value;
	var canFromDate=document.getElementById('paraFrm_appCanFrmDate').value;
	var canToDate=document.getElementById('paraFrm_appCanToDate').value;
	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value; 
	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_appFrmDate','app.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_appToDate','app.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_appToDate', 'app.frdate','app.todate'))
	  return false;
	}
	if(canFromDate!="")
	{
	  if(!validateDate('paraFrm_appCanFrmDate','app.canFrdate'))
	  return false; 
	}
	if(canToDate!="")
	{
	  if(!validateDate('paraFrm_appCanToDate','app.canTodate'))
	  return false;
	}
	if(canFromDate!="" && canToDate!="")
	{
	  if(!dateDifferenceEqual(canFromDate,canToDate,'paraFrm_appCanToDate', 'app.canFrdate','app.canTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" && canFromDate == "" && canToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="D" || status=="P" || status=="A" || status=="R"){
var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
var candName=document.getElementById('paraFrm_searchCandName').value;
var position=document.getElementById('paraFrm_searchPosition').value;
var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
var days=document.getElementById('paraFrm_searchDueSinceDays').value;
if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && days == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
}
} 
document.getElementById('showFilterToApp').style.display='none';
document.getElementById('hideFilter').style.display='none';
document.getElementById('showFilter').style.display='none';
document.getElementById("paraFrm_searchFlag").value="true";
document.getElementById('paraFrm').action='CreateAppointment_getAppointmentDetailsOnSearch.action';
document.getElementById('paraFrm').submit();
}	

function callClear(){
document.getElementById("hideFilter").style.display='none';
document.getElementById("showFilterToApp").style.display='none';
document.getElementById("paraFrm_searchFlag").value='false';
document.getElementById("paraFrm").action="CreateAppointment_reset.action";
document.getElementById("paraFrm").submit();
document.getElementById("showFilter").style.display='';
}

function calReset(){
   var status=document.getElementById('paraFrm_appointmentStatus').value;
   
   if(status=="I"){	
	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_appFrmDate').value="";
	   document.getElementById('paraFrm_appToDate').value="";
   }else if(status=="OA"){
	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_appFrmDate').value="";
	   document.getElementById('paraFrm_appToDate').value="";
	   document.getElementById('paraFrm_appAccFrmDate').value="";
   	   document.getElementById('paraFrm_appAccToDate').value="";	   	
   }else if(status=="S"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_appFrmDate').value="";
	   document.getElementById('paraFrm_appToDate').value="";
	   document.getElementById('paraFrm_appRejFrmDate').value="";
   	   document.getElementById('paraFrm_appRejToDate').value="";
   }else if(status=="C"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_appFrmDate').value="";
	   document.getElementById('paraFrm_appToDate').value="";
	   document.getElementById('paraFrm_appCanFrmDate').value="";
	   document.getElementById('paraFrm_appCanToDate').value=""; 	
   }else if(status=="D" || status=="R" || status=="A" || status=="P"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_searchDueSinceDays').value="";
   }
}


//Modified Paging by Guru Prasad


function callPageText(id,status1){  
	   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPageApp').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPageApp').value=pageNo;
		   
			document.getElementById('paraFrm').action='CreateAppointment_showPageList.action?status='+status1;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	
	
 function callPage(id,pageImg,status1){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
            
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		
		if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("enableFilterValue").style.display='';
		 }
		document.getElementById('paraFrm_myPageApp').value=id;
		document.getElementById('paraFrm').action='CreateAppointment_showPageList.action?status='+status1;
		document.getElementById('paraFrm').submit(); 
	}
	
	function newRowColor(cell)
   	{
		cell.className='Cell_bg_first'; 
	}
	function oldRowColor(cell,val){ 
		cell.className='Cell_bg_second'; 
	}
	
	function viewOfferDetails(appointCode,reqCode){
		var abc = true;
		document.getElementById("paraFrm").action='AppointmentDetails_viewAppointmentDetails.action?appointmentCode='+appointCode+'&reqsCode='+reqCode+'&doubleClickEditFlag='+abc;
	    document.getElementById("paraFrm").submit();
	} 

</script>