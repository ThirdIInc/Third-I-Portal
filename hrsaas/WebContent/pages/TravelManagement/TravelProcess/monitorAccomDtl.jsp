<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/TravelManagement/TravelProcess/tmsAjax.js"></script>
<s:form action="TravelMonitor" id="paraFrm" name="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Accommodations
					Details</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<%!boolean flag = false;%>
					<s:hidden name="accStatus" />
					<td width="78%"><s:if test="!readOnlyFlag">
						<s:if
							test="(userType == 'SUB' || userType == 'SCH') && accStatus=='Y '">
							<input type="button" value=" Save" onclick="saveAccomDetails();"
								name="save" class="save">
						</s:if>
					</s:if> <s:if test="userType == 'APL' && accStatus=='SO'">
						<input type="button" value=" Submit"
							onclick="acceptAccomDetails();" name="accept" class="save">
					</s:if><s:if test="(userType == 'APR' && accStatus=='CO')">
						<input type="button" value="Approve" onclick="approveAccom();"
							name="Approve" class="token">
						<input type="button" value="Reject"
							onclick="rejectAccomDetails();" name="reject" class="token">
					</s:if></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="empId" id="hiddEmpId" />
		<s:hidden name="applicationId" id="hiddApplicationId" />
		<s:hidden name="empApplId" id="hiddEmpApplId" />
		<s:hidden name="iniEmpId" id="hiddIniEmpId" />
		<s:hidden name="userType" id="hiddUserType" />
		<s:hidden name="applicationDate" id="hiddApplicationDate" />
		<s:hidden name="contactNo" id="contactNo" />
		<s:hidden name="policyId" id="policyId" />
		<s:hidden name="policyName" id="policyName" />
		<s:hidden name="gradeName" id="gradeName" />
		<s:hidden name="gradeId" id="gradeId" />
		<s:hidden name="applicant" id="applicant" />
		<s:hidden name="tourStartDate" id="tourStartDate" />
		<s:hidden name="tourEndDate" id="tourEndDate" />
		<s:hidden name="tmsTrvlId" />
		<s:hidden name="tmsTrvlIndiId" />
		<s:hidden name="tmsChkTypeFlg" />
		<s:hidden name="deskFlag" />
		<s:hidden name="savedFlag" id="savedFlag" />
		<s:hidden name="currentPage" id="currentPage" />
		<input type="hidden" name="violateFlag" id="violateFlag" value="" />
		<input type="hidden" name="dtlId" id="dtlId" value="" />
		<s:hidden name="rowId" />
		<tr>
			<td>
			<table width="100%" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Employee Information</strong></td>
				</tr>
				<tr>
					<td width="15%"><label name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td><s:property value="applicant" /></td>
				</tr>
				<tr>
					<s:if test="empId != 0">
						<td width="15%"><label name="travel.policy"
							id="travel.policy" ondblclick="callShowDiv(this);"><%=label.get("travel.policy")%></label>
						:</td>
						<td width="25%" ><s:property
							value="policyName" /></td>
					</s:if>
					<td width="15%"><label name="application.date"
						id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>
					:</td>
					<td width="45%"><s:property value="applicationDate" /></td>
				</tr>

				<tr>
					<s:if test="empId != 0">
						<td width="15%"><label name="grade" id="grade"
							ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
						<td><s:property value="gradeName" /></td>
					</s:if>
					<td width="15%"><label name="contact.number"
						id="contact.number" ondblclick="callShowDiv(this);"><%=label.get("contact.number")%></label>
					:</td>
					<td width="45%"><s:property value="contactNo" /></td>
				</tr>
				<tr>
					<td width="15%"><label name="tourStartDate" id="tourStartDate"
						ondblclick="callShowDiv(this);"><%=label.get("tourStartDate")%></label>
					:</td>
					<td><s:property value="tourStartDate" /></td>
					<td width="15%"><label name="tourEndDate" id="tourEndDate"
						ondblclick="callShowDiv(this);"><%=label.get("tourEndDate")%></label>
					:</td>
					<td width="45%"><s:property value="tourEndDate" /></td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td width="25%" ><s:if test="empId != 0">
						<input type="button" name="ViewPolicy" value="View Policy"
							class="token"
							onclick="viewPolicy('<s:property value="gradeId" />')"; > &nbsp;&nbsp;&nbsp;&nbsp;</s:if><input
						type="button" name="ViewReq" value="View Application"
						class="token"
						onclick="viewDetails('<s:property value="applicationId" />','<s:property value="empApplId" />','S')"; >
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<%!int i = 1, j = 1, k = 1, m, n, p, totCount = 1, count = 1;%>
				<s:hidden name="monitorId" id="monitorId" />
				<s:hidden name="userType" />
				<s:hidden name="readOnlyFlag" />
				<s:iterator value="main">
					<input type="hidden" name="readOnlyFlag<%=i%>" value='<s:property value="readOnlyFlag" />' >
					<s:if test="!readOnlyFlag">
							<%
							flag = true;
							%>
						</s:if>
						<s:else>
							<%
							flag = false;
							%>
						</s:else>
					<tr>
						<td colspan="8">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2" class="formbg">
							<tr>
								<td colspan="2" width="20%"><strong>City : <s:property
									value="fromCity" /></strong> <input type="hidden" name="fromCity_<%=i%>"
									value='<s:property value="fromCity" />' id="fromCity_<%=i%>">
								</td>
								<%
								if (flag) {
								%>
								<td colspan="6" align="right" width="80%"><input
									type="button" name="addOption" value="Add Option"
									onclick="addNewOption('<%=i%>');" class="token"> <input
									type="button" name="removeOpt" value="Remove Option"
									class="token" onclick="removeOption('<%=i%>');"></td>
								<%
								}
								%>
							</tr>
							<tr>
								<td colspan="8">
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="2">
									<s:iterator value="options">
										<input type="hidden" name="lodgCancelFlag<%=i%>_<%=j%>" 
										id='<%="cancelFlag"+i+"_"+j %>' value='<s:property value="lodgCancelFlag" />'>
										<tr>
											<td colspan="1" width="2%"><input type="radio"
												name="optRadio_<%=i%>" id="optRadio_<%=i%>_<%=j%>"
												onclick="checkOnClick('radio');" value='<s:property value="lodgCancelFlag" />'></td>
											<td colspan="10" align="right" width="98%">
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2" class="formbg">
												<tr>
													<td colspan="8" width="100%">
													<table width="100%" border="0" align="center"
														cellpadding="2" cellspacing="2">
														<tr>
															<td width="8%" class="formth" ><label name="city"
																id="city_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
															<font color="red"> * </font></td>
															<td width="12%" class="formth" ><label
																name="fromDate" id="fromDate_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label>
															<font color="red"> * </font> (DD-MM-YYYY)</td>
															<td width="8%" class="formth" ><label name="check.in"
																id="check.in_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("check.in")%></label>
															<font color="red"> * </font> (HH24:MM)</td>
															<td width="12%" class="formth" ><label name="toDate"
																id="toDate_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label>
															<font color="red"> * </font> (DD-MM-YYYY)</td>
															<td width="10%" class="formth" ><label
																name="check.out" id="check.out_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("check.out")%></label>
															<font color="red">*</font> (HH24:MM)</td>
															<td width="14%" class="formth"><label
																name="hotel.type" id="hotel.type_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("hotel.type")%></label><font
																color="red"> * </font></td>
															<td width="14%" class="formth"><label
																name="room.type" id="room.type_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("room.type")%></label><font
																color="red"> * </font></td>
															<td width="12%" class="formth"><label name="address"
																id="address_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("address")%></label></td>
															<%
															if (flag) {
															%>
															<td width="7%" class="formth"><input type="button"
																name="Add" value="Add" class="add"
																onclick="addRowToOption('<%=i%>_<%=j%>');"></td>
															<%
															}
															%>
														</tr>
														<s:iterator value="path">
															<tr id="tr_<%=i %>_<%=j %>_<%=k %>">
																<s:hidden name='<%="monitorId_"+i+"_"+j %>'
																	value='%{monitorId}' />
																<s:hidden name='<%="lodgeCode_"+i+"_"+j %>'
																	value='%{lodgeCode}'
																	id='<%="lodgeCode_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="lodgeDtlId_"+i+"_"+j %>'
																	value='%{lodgeDtlId}'
																	id='<%="lodgeDtlId_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="selFlag_"+i+"_"+j %>'
																	value='%{selFlag}' id='<%="selFlag_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="cityCode_"+i+"_"+j %>'
																	value='%{cityCode}'
																	id='<%="paraFrm_cityCode_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="violateFlag_"+i+"_"+j %>'
																	value='%{violateFlag}'
																	id='<%="violateFlag_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="rejFlag_"+i+"_"+j %>'
																	value='%{rejFlag}' id='<%="rejFlag_"+i+"_"+j+"_"+k %>' />
																<td width="8%"  class="sortabletd">
																<s:if test="readOnlyFlag">
																	<s:property value="source" />&nbsp;
																	<s:hidden
																	name='<%="source_"+i+"_"+j%>'
																	id='<%="source_"+i+"_"+j+"_"+k %>'
																	value="%{source}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="13" maxlength="50"
																	name='<%="source_"+i+"_"+j%>'
																	id='<%="source_"+i+"_"+j+"_"+k %>'
																	readonly="%{readOnlyFlag}" value="%{source}" />
																	</s:else>
																	</td>
																<td width="15%"  class="sortabletd" nowrap="nowrap">
																<s:if test="readOnlyFlag">
																	<s:property value="accomFromDate" />&nbsp;
																	<s:hidden
																	name='<%="accomFromDate_"+i+"_"+j%>'
																	value="%{accomFromDate}"
																	id='<%="accomFromDate_"+i+"_"+j+"_"+k %>' />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="10" maxlength="10"
																	onkeypress="return numbersWithHiphen();"
																	readonly="%{readOnlyFlag}"
																	name='<%="accomFromDate_"+i+"_"+j%>'
																	value="%{accomFromDate}"
																	id='<%="accomFromDate_"+i+"_"+j+"_"+k %>' /> </s:else><a
																	href="javascript:NewCal('<%="accomFromDate_"+i+"_"+j+"_"+k%>','DDMMYYYY');">
																
																<%
																																if (flag) {
																																%> <img class="iconImage"
																	src="../pages/images/recruitment/Date.gif" width="16"
																	height="16" class="imageIcon" border="0"
																	align="absmiddle" />
																<%
																}
																%> </a></td>
																<td width="6%"  class="sortabletd">
																<s:if test="readOnlyFlag">
																	<s:property value="accomFromTime" />&nbsp;
																	<s:hidden
																	name='<%="accomFromTime_"+i+"_"+j%>'
																	id='<%="accomFromTime_"+i+"_"+j+"_"+k %>'
																	value="%{accomFromTime}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="7" maxlength="5"
																	onkeypress="return  numbersWithColon();"
																	readonly="%{readOnlyFlag}"
																	name='<%="accomFromTime_"+i+"_"+j%>'
																	id='<%="accomFromTime_"+i+"_"+j+"_"+k %>'
																	value="%{accomFromTime}" /></s:else></td>
																<td width="15%"  class="sortabletd" nowrap="nowrap">
																<s:if test="readOnlyFlag">
																	<s:property value="accomToDate" />&nbsp;
																	<s:hidden
																	name='<%="accomToDate_"+i+"_"+j%>'
																	id='<%="accomToDate_"+i+"_"+j+"_"+k%>'
																	value="%{accomToDate}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="10" maxlength="10"
																	onkeypress="return numbersWithHiphen();"
																	readonly="%{readOnlyFlag}"
																	name='<%="accomToDate_"+i+"_"+j%>'
																	id='<%="accomToDate_"+i+"_"+j+"_"+k%>'
																	value="%{accomToDate}" /> </s:else><a
																	href="javascript:NewCal('<%="accomToDate_"+i+"_"+j+"_"+k%>','DDMMYYYY');">
																<%
																if (flag) {
																%><img class="iconImage"
																	src="../pages/images/recruitment/Date.gif" width="16"
																	height="16" class="imageIcon" border="0"
																	align="absmiddle" />
																<%
																}
																%> </a></td>
																<td width="6%"  class="sortabletd">
																<s:if test="readOnlyFlag">
																	<s:property value="accomToTime" />&nbsp;
																	<s:hidden
																	name='<%="accomToTime_"+i+"_"+j%>'
																	id='<%="accomToTime_"+i+"_"+j+"_"+k %>'
																	value="%{accomToTime}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="7" maxlength="5"
																	onkeypress="return numbersWithColon();"
																	name='<%="accomToTime_"+i+"_"+j%>'
																	id='<%="accomToTime_"+i+"_"+j+"_"+k %>'
																	readonly="%{readOnlyFlag}" value="%{accomToTime}" />
																	</s:else>
																	</td>
																<td width="14%"  class="sortabletd" nowrap="nowrap"><s:hidden
																	name='<%="hotelTypeCode_"+i+"_"+j%>'
																	value="%{hotelTypeCode}"
																	id='<%="paraFrm_hotelTypeCode_"+i+"_"+j+"_"+k%>' />
																	<s:if test="readOnlyFlag">
																		<s:property value="HotelTypeName" />&nbsp;
																		<s:hidden
																			name='<%="hotelTypeName_"+i+"_"+j%>' 
																			id='<%="paraFrm_hotelTypeName_"+i+"_"+j+"_"+k %>'
																			value="%{hotelTypeName}" />
																	</s:if>
																<s:else>
																	<s:textfield
																	theme="simple" size="20" maxlength="5"
																	onkeypress="return numbersWithColon();"
																	name='<%="hotelTypeName_"+i+"_"+j%>' readonly="true"
																	id='<%="paraFrm_hotelTypeName_"+i+"_"+j+"_"+k %>'
																	value="%{hotelTypeName}" /> </s:else><%
 if (flag) {
 %><img
																	src="../pages/images/recruitment/search2.gif"
																	width="16" height="15" class="iconImage"
																	onclick="selectHotelType('<%=i+"_"+j+"_"+k %>');" /> <%
 }
 %>
																	</td>
																<td width="14%"  class="sortabletd" nowrap="nowrap"><s:hidden
																	name='<%="roomTypeCode_"+i+"_"+j%>'
																	value="%{roomTypeCode}"
																	id='<%="paraFrm_roomTypeCode_"+i+"_"+j+"_"+k%>' />
																	<s:if test="readOnlyFlag">
																	<s:property value="roomTypeName" />&nbsp;
																	<s:hidden
																	name='<%="roomTypeName_"+i+"_"+j%>' 
																	id='<%="paraFrm_roomTypeName_"+i+"_"+j+"_"+k %>'
																	value="%{roomTypeName}" />
																</s:if>
																<s:else>
																	<s:textfield
																	theme="simple" size="20" maxlength="5"
																	onkeypress="return  numbersWithColon();"
																	name='<%="roomTypeName_"+i+"_"+j%>' readonly="true"
																	id='<%="paraFrm_roomTypeName_"+i+"_"+j+"_"+k %>'
																	value="%{roomTypeName}" /> </s:else><%
 if (flag) {
 %> <img src="../pages/images/recruitment/search2.gif"
																	width="16" height="15" class="iconImage"
																	onclick="javascript:selectRoomType('<%=i+"_"+j+"_"+k%>');" /></td>
																	<%
																	}
																	%>
																<td width="12%"  class="sortabletd" nowrap="nowrap"><s:textarea
																	theme="simple" rows="1" cols="10"
																	onkeyup="textCounter(this,100);"
																	name='<%="accomAddress_"+i+"_"+j%>'
																	id='<%="accomAddress_"+i+"_"+j+"_"+k %>'
																	readonly="%{readOnlyFlag}" value="%{accomAddress}" />
																	<input type="hidden" name="remCount" id='remCount_<%=i%>_<%=j%>_<%=k%>' >
																	<%
																	if (flag) {
																	%><img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
							 										onclick="callWindow('accomAddress_<%=i%>_<%=j%>_<%=k%>','address_1_1','','remCount_<%=i%>_<%=j%>_<%=k%>',500);">
							 										<%
							 										} else {
							 										%>
							 										<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
							 										onclick="callWindow('accomAddress_<%=i%>_<%=j%>_<%=k%>','address_1_1','readonly','remCount_<%=i%>_<%=j%>_<%=k%>',500);">
							 										<%
							 										}
							 										%> 
																	
																	</td>
																<s:hidden theme="simple"
																	name='<%="accomBookingAmt_"+i+"_"+j%>'
																	id='<%="accomBookingAmt_"+i+"_"+j+"_"+k %>'
																	value="%{accomBookingAmt}" />
																<%
																if (flag) {
																%>
																<td width="7%" class="sortabletd"><input
																	type="button" name="remove" value="Remove"
																	class="token"
																	onclick="removeRowOption('<%=i%>_<%=j%>_<%=k%>')"></td>
																<%
																}
																%>
															</tr>
																<script>
																if(document.getElementById('selFlag_<%=i%>_<%=j%>_<%=k%>').value == 'Y' && document.getElementById('paraFrm_accStatus').value=='CE')
																{
														 			document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#FFFFCC';
														 		}
														 		if(document.getElementById('paraFrm_accStatus').value == 'SO' && document.getElementById('rejFlag_<%=i%>_<%=j%>_<%=k%>').value == 'Y')
																{
														 			document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#92CCFF';
														 		}
														 		if(document.getElementById('paraFrm_accStatus').value == 'CO' && document.getElementById('violateFlag_<%=i%>_<%=j%>_<%=k%>').value == 'Y')
																{
															 		document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#F08080';
															 	}
																</script>
																<s:if test="lodgCancelFlag == 'PC' || lodgCancelFlag == 'CC'">
																<script>
																	document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#FFC488';
																</script>
															</s:if>
															<%
																++k;
																++totCount;
															%>
														</s:iterator>
														<script>
											 				if(document.getElementById('selFlag_<%=i%>_<%=j%>_1').value == 'Y')
											 					document.getElementById('optRadio_<%=i%>_<%=j%>').checked=true;
											 				if(document.getElementById('optRadio_<%=i%>_<%=j%>').value == 'CC')
											 					document.getElementById('optRadio_<%=i%>_<%=j%>').disabled=true;
														</script>
														<input type="hidden" value="<%=k %>" name="path"
															id="path_<%=i%>_<%=j%>" />
														<%
															p = k;
															k = 1;
														%>
														<%
														++j;
														%>
													</table>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</s:iterator>
									<input type="hidden" value="<%=j%>" name="radioHidden"
										id="radioHidden_<%=i%>" />
									<%
											if (j > n) {
											n = j;
										}
										j = 1;
									%>
									<%
									++i;
									%>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:iterator>

				<tr id="aplRow" style="display: none">
					<td colspan="4"><s:checkbox name="noneCheck" id="noneCheck"
						theme="simple" onclick="checkOnClick('none');" /> None of these
					(require more options)</td>
				</tr>
				<tr id="aplRow1" style="display: none">
					<td colspan="4"><s:checkbox name="dontNeed" id="dontNeed"
						theme="simple" onclick="checkOnClick('dontNeed');" /> Not Required
					(Self Managed)</td>
				</tr>
				<s:if test="accStatus=='Y '">
				</s:if>
				<s:elseif test="accStatus=='CN'">
					<script>
						document.getElementById('dontNeed').checked=true;
					</script>
				</s:elseif>
				<script>
						if(document.getElementById('hiddUserType').value != 'APL' || document.getElementById('paraFrm_accStatus').value != 'SO')
						{
							document.getElementById('noneCheck').disabled=true;
							document.getElementById('dontNeed').disabled=true;
							var count = <%=i-1%>;
							if(document.getElementById('paraFrm_accStatus').value != 'Y ')
							for(i=1;i<=count;i++)
							{
								var opCount = eval(document.getElementById('radioHidden_'+i).value)-1;
								for(j=1;j<=opCount;j++)
									document.getElementById('optRadio_'+i+'_'+j).disabled=true;
							}
						}
				</script>
				<%
					m = i;
					i = 1;
					count = totCount;
					totCount = 1;
				%>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td id="commRow" style="display: none">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="20%"><label name="aplComments" id="aplComments"
								ondblclick="callShowDiv(this);"><%=label.get("aplComments")%></label>
							:</td>
							<td width="30%"><s:textarea name="aplComments" rows="3"
								cols="30" onkeyup="textCounter(this,500);" theme="simple"
								wrap="true" />
								<img src="../pages/images/zoomin.gif" style="vertical-align: bottom;" class="iconImage" height="10" align="absmiddle" width="10"
							 				onclick="zoomText('paraFrm_aplComments','aplComments','remCount_1_1_1');">
								</td>
							<td width="20%"><label name="aprComments" id="aprComments"
								ondblclick="callShowDiv(this);"><%=label.get("aprComments")%></label>
							:</td>
							<td width="30%"><s:textarea name="aprComments" rows="3"
								onkeyup="textCounter(this,500);" theme="simple" wrap="true"
								cols="30" theme="simple" wrap="true" />
								<img src="../pages/images/zoomin.gif" style="vertical-align: bottom;" class="iconImage" height="10" align="absmiddle" width="10"
							 				onclick="zoomText('paraFrm_aprComments','aprComments','remCount_1_1_1');">
								</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="20%"><label name="schComments" id="schComments"
								ondblclick="callShowDiv(this);"><%=label.get("schComments")%></label>
							:</td>
							<td width="30%"><s:textarea name="schComments" rows="3"
								cols="30" onkeyup="textCounter(this,500);" theme="simple"
								wrap="true" />
								<img src="../pages/images/zoomin.gif" style="vertical-align: bottom;" class="iconImage" height="10" align="absmiddle" width="10"
							 				onclick="zoomText('paraFrm_schComments','schComments','remCount_1_1_1');">
								</td>
							<td width="50%">
								<a href="#" onclick="showCommentsTrail();" style="color: blue;"><u>Show Comments</u></a>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
			</table>
			<s:if test="accStatus!='Y ' && accStatus!='SO'">
							<script>
						document.getElementById('paraFrm_aplComments').readOnly=true;
						document.getElementById('paraFrm_schComments').readOnly=true;
					</script>
						</s:if>
						<s:elseif test="userType!='APL'">
							<script>
						document.getElementById('paraFrm_aplComments').readOnly=true;
					</script>
						</s:elseif>
						<s:if test="readOnlyFlag">
							<script>
						document.getElementById('paraFrm_schComments').readOnly=true;
					</script>
						</s:if>
						<s:if test="userType!='APR' || accStatus!='CO'">
							<script>
						document.getElementById('paraFrm_aprComments').readOnly=true;
					</script>
						</s:if>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:if test="!readOnlyFlag">
						<s:if
							test="(userType == 'SUB' || userType == 'SCH') && accStatus=='Y '">
							<input type="button" value=" Save" onclick="saveAccomDetails();"
								name="save" class="save">
						</s:if>
					</s:if> <s:if test="userType == 'APL' && accStatus=='SO'">
						<input type="button" value=" Submit"
							onclick="acceptAccomDetails();" name="accept" class="save">
					</s:if><s:if test="(userType == 'APR' && accStatus=='CO')">
						<input type="button" value="Approve" onclick="approveAccom();"
							name="Approve" class="token">
						<input type="button" value="Reject"
							onclick="rejectAccomDetails();" name="reject" class="token">
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr id="noteRow" style="display: none">
			<td>
			<table width="100%">
				<tr>
					<td width="8%"><b>Note : -</b></td>
					<td width="92%"><input type="text" disabled="disabled" style="background-color: #F08080 ;height:15px;width: 15px" >&nbsp;&nbsp;&nbsp; Indicates records pending for approval due to policy violation.</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" disabled="disabled" style="background-color: #92CCFF;height:15px;width: 15px" >&nbsp;&nbsp;&nbsp; Indicates previously rejected records.</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" disabled="disabled" style="background-color: #FFFFCC;height:15px;width: 15px" >&nbsp;&nbsp;&nbsp; Indicates confirmed records.</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" disabled="disabled" style="background-color: #FFC488 ;height:15px;width: 15px" >&nbsp;&nbsp;&nbsp; Indicates records either pending for cancellation or cancelled.</td>
				</tr>
			</table>
			</td>
		</tr>
		<script>
				if(document.getElementById('savedFlag').value=='true')
				{
					document.getElementById('commRow').style.display='';
					document.getElementById('noteRow').style.display='';
					document.getElementById('aplRow').style.display='';
					document.getElementById('aplRow1').style.display='';
					if(document.getElementById('paraFrm_accStatus').value=='Y ')
						document.getElementById('noneCheck').checked=true;
				}
		</script>
	</table>
	<%
	flag = false;
	%>
</s:form>
<script>
	function addRowToOption(pathName)
	{
		try {
		var maxMainCount = <%=m-1%>;
		var maxOptCount = <%=n-1%>;
		var empId = document.getElementById('hiddEmpId').value;
		var empApplId = document.getElementById('hiddEmpApplId').value;
		var applicationId = document.getElementById('hiddApplicationId').value;
		var userType = document.getElementById('hiddUserType').value; 
		var iniEmpId = document.getElementById('hiddIniEmpId').value;
		var applDate = document.getElementById('hiddApplicationDate').value;
		pathName=pathName+"_"+eval(eval(document.getElementById('path_'+pathName).value)-1);
		document.getElementById('paraFrm').action = 'TravelMonitor_addRowToOptionForAccom.action?pathName='
			+pathName+'&maxMainCount='+maxMainCount+'&maxOptCount='+maxOptCount;
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function addNewOption(pathName)
	{
		try {
			var maxMainCount = <%=m-1%>;
			var maxOptCount = <%=n-1%>;
			var empId = document.getElementById('hiddEmpId').value;
			var empApplId = document.getElementById('hiddEmpApplId').value;
			var applicationId = document.getElementById('hiddApplicationId').value;
			var userType = document.getElementById('hiddUserType').value; 
			var iniEmpId = document.getElementById('hiddIniEmpId').value;
			var applDate = document.getElementById('hiddApplicationDate').value;
			document.getElementById('paraFrm').action = 'TravelMonitor_addOptionForAccom.action?pathName='
				+pathName+'&maxMainCount='+maxMainCount+'&maxOptCount='+maxOptCount;
			document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function removeOption(pathName)
	{
		try {
			var count = eval(document.getElementById('radioHidden_'+pathName).value)-1;
			if(count == 1)
			{
				alert('Option cannot be removed! Accomodation for '+document.getElementById('fromCity_'+pathName).value+' should have atleast one option');
				return false;
			}
			var flag=false;
			for(i=1;i<=count;i++)
			{
				if(document.getElementById("optRadio_"+pathName+"_"+i).checked)
				{
					pathName=pathName+"_"+i;
					flag=true;
					break;
				}
			}
			if(!flag)
			{
				alert("Please select an option to remove");
				return false;
			}
			var maxMainCount = <%=m-1%>;
			var maxOptCount = <%=n-1%>;
			var empId = document.getElementById('hiddEmpId').value;
			var empApplId = document.getElementById('hiddEmpApplId').value;
			var applicationId = document.getElementById('hiddApplicationId').value;
			var userType = document.getElementById('hiddUserType').value; 
			var iniEmpId = document.getElementById('hiddIniEmpId').value;
			var applDate = document.getElementById('hiddApplicationDate').value;
			if(!confirm('<%=label.get("confirm.remove")%>'))
				return false;
			document.getElementById('paraFrm').action = 'TravelMonitor_removeOptionForAccom.action?pathName='
				+pathName+'&maxMainCount='+maxMainCount+'&maxOptCount='+maxOptCount;
			document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function removeRowOption(pathName)
	{
	 try {
		var maxMainCount = <%=m-1%>;
		var maxOptCount = <%=n-1%>;
		var iVal = pathName.split('_');
		var count = eval(document.getElementById('radioHidden_'+iVal[0]).value)-1;
		if(count == 1)
		{
			var selFlagCount = eval(document.getElementById('path_'+iVal[0]+'_'+iVal[1]).value)-1;
			if(selFlagCount == 1)
			{
				alert('Option cannot be removed! Accomodation for '+document.getElementById('fromCity_'+iVal[0]).value+' should have atleast one option');
				return false;
			}
		}
		var empId = document.getElementById('hiddEmpId').value;
		var empApplId = document.getElementById('hiddEmpApplId').value;
		var applicationId = document.getElementById('hiddApplicationId').value;
		var userType = document.getElementById('hiddUserType').value; 
		var iniEmpId = document.getElementById('hiddIniEmpId').value;
		var applDate = document.getElementById('hiddApplicationDate').value;
		if(!confirm('<%=label.get("confirm.remove")%>'))
				return false;
		document.getElementById('paraFrm').action = 'TravelMonitor_removeRowOptionForAccom.action?pathName='
			+pathName+'&maxMainCount='+maxMainCount+'&maxOptCount='+maxOptCount;
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function saveAccomDetails()
	{
		try{
		var empApplId = document.getElementById('hiddEmpApplId').value;
		var journeyCount = <%=m-1%>;
		if(document.getElementById('hiddUserType').value=='SCH' || document.getElementById('hiddUserType').value=='SUB')
		for(i=1;i<=journeyCount;i++)
		{
			try
			{
				var opCount = eval(document.getElementById('radioHidden_'+i).value)-1;
			}
			catch(e)
			{
				break;
			}
			if(document.getElementById('cancelFlag'+i+'_'+1).value != 'CC')
			for(j=1;j<=opCount;j++)
			{
				var selFlagCount = eval(document.getElementById('path_'+i+'_'+j).value)-1;
				for(k=1;k<=selFlagCount;k++)
				{
					var source=trim(document.getElementById('source_'+i+'_'+j+'_'+k).value);
					var fromDate=document.getElementById('accomFromDate_'+i+'_'+j+'_'+k).value;
					var fromTime=document.getElementById('accomFromTime_'+i+'_'+j+'_'+k).value;
					var toDate=document.getElementById('accomToDate_'+i+'_'+j+'_'+k).value;
					var toTime=document.getElementById('accomToTime_'+i+'_'+j+'_'+k).value;
					var hotelCode=trim(document.getElementById('paraFrm_hotelTypeCode_'+i+'_'+j+'_'+k).value);
					var roomCode=trim(document.getElementById('paraFrm_roomTypeCode_'+i+'_'+j+'_'+k).value);
					if(source == '')
					{
						alert("Please enter "+document.getElementById('city_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('source_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					var fieldName = ['tourStartDate','tourEndDate','accomFromDate_'+i+'_'+j+'_'+k];
					var labelName = ['tour start date','tour end date',document.getElementById('fromDate_'+i+'_'+j).innerHTML.toLowerCase()];
					if(fromDate == '')
					{
						alert("Please enter "+document.getElementById('fromDate_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('accomFromDate_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateDate('accomFromDate_'+i+'_'+j+'_'+k,'fromDate_'+i+'_'+j)
								|| !dateBetweenTwoDates(fieldName,labelName,i+'_'+j+'_'+k))
					{
						return false;
					}
					if(fromTime == '')
					{
						alert("Please enter "+document.getElementById('check.in_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('accomFromTime_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateTime('accomFromTime_'+i+'_'+j+'_'+k,'check.in_'+i+'_'+j))
					{
						return false;
					}
					
					fieldName = ['tourStartDate','tourEndDate','accomToDate_'+i+'_'+j+'_'+k];
					labelName = ['tour start date','tour end date',document.getElementById('toDate_'+i+'_'+j).innerHTML.toLowerCase()];
					if(toDate == '')
					{
						alert("Please enter "+document.getElementById('toDate_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('accomToDate_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateDate('accomToDate_'+i+'_'+j+'_'+k,'toDate_'+i+'_'+j)
								|| !dateBetweenTwoDates(fieldName,labelName,i+'_'+j+'_'+k))
					{
						return false;
					}
					if(toTime == '')
					{
						alert("Please enter "+document.getElementById('check.out_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('accomToTime_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateTime('accomToTime_'+i+'_'+j+'_'+k,'check.out_'+i+'_'+j))
					{
						return false;
					}
					
					
					if(!dateDifferenceEqual(document.getElementById('accomFromDate_'+i+'_'+j+'_'+k).value,
							document.getElementById('accomToDate_'+i+'_'+j+'_'+k).value,i,j,k))
					{
							return false;
					}
					else
					{
						if(document.getElementById('accomFromDate_'+i+'_'+j+'_'+k).value == document.getElementById('accomToDate_'+i+'_'+j+'_'+k).value)
							{
								prvTime = (document.getElementById('accomFromTime_'+i+'_'+j+'_'+k).value).split(':');
								thisTime = (document.getElementById('accomToTime_'+i+'_'+j+'_'+k).value).split(':');
								if(eval(prvTime[0]) > eval(thisTime[0]) || (eval(prvTime[0]) == eval(thisTime[0]) && eval(prvTime[1]) >= eval(thisTime[1])))
								{
									alert(document.getElementById('check.out_'+i+'_'+j).innerHTML.toLowerCase()+' for accommodation in '
									+document.getElementById('source_'+i+'_'+j+'_'+k).value
									+' should be greater than '+document.getElementById('check.in_'+i+'_'+j).innerHTML.toLowerCase());
									document.getElementById('accomToTime_'+i+'_'+j+'_'+k).focus();
									return false;
								}
							}
					}
					
					
					if(k > 1)
					{
						if(!dateDifferenceEqual(document.getElementById('accomToDate_'+i+'_'+j+'_'+(k-1)).value,
							document.getElementById('accomFromDate_'+i+'_'+j+'_'+(k)).value,i,j,k))
						{
							return false;
						}
						else
						{
							if(document.getElementById('accomToDate_'+i+'_'+j+'_'+(k-1)).value==document.getElementById('accomFromDate_'+i+'_'+j+'_'+(k)).value)
							{
								prvTime = (document.getElementById('accomToTime_'+i+'_'+j+'_'+(k-1)).value).split(':');
								thisTime = (document.getElementById('accomFromTime_'+i+'_'+j+'_'+k).value).split(':');
								if(eval(prvTime[0]) > eval(thisTime[0]) || (eval(prvTime[0]) == eval(thisTime[0]) && eval(prvTime[1]) >= eval(thisTime[1])))
								{
									alert(document.getElementById('check.in_'+i+'_'+j).innerHTML.toLowerCase()+' for accommodation in '
									+document.getElementById('source_'+i+'_'+j+'_'+k).value
									+' should be greater than '+document.getElementById('check.out_'+i+'_'+j).innerHTML.toLowerCase()
									+' for accommodation in '+document.getElementById('source_'+i+'_'+j+'_'+(k-1)).value);
									document.getElementById('accomFromTime_'+i+'_'+j+'_'+k).focus();
									return false;
								}
							}
						}
					}
					if(hotelCode == '')
					{
						alert('Please select '+document.getElementById('hotel.type_1_1').innerHTML.toLowerCase());
						return false
					}
					if(roomCode == '')
					{
						alert('Please select '+document.getElementById('room.type_1_1').innerHTML.toLowerCase());
						return false
					}
					document.getElementById('lodgeCode_'+i+'_'+j+'_'+k).value = document.getElementById('lodgeCode_'+i+'_1_1').value;
				}
			}
		}
		if(!confirm("Are you sure want to save? click ok to confirm and \n\ncancel to recheck the information."))
		{
			return false;
		}
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='TravelMonitor_saveAccomDetails.action?empApplId'+empApplId+'&totCount='+<%=count-1%>+'&lodgeCount='+<%=m-1%>;
		window.close();
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function acceptAccomDetails()
	{
		try {
			var empApplId = document.getElementById('hiddEmpApplId').value;
			var empId = document.getElementById('hiddEmpId').value;
			var journeyCount = <%=m-1%>;
			var dtlId = "";
			var classId="";
			if(!(document.getElementById('noneCheck').checked || document.getElementById('dontNeed').checked))
			{
				for(i=1;i<=journeyCount;i++)
				{
					var opCount = eval(document.getElementById('radioHidden_'+i).value)-1;
					if(document.getElementById('cancelFlag'+i+'_'+1).value != 'CC')
					for(j=1;j<=opCount;j++)
					{
						if(document.getElementById('optRadio_'+i+'_'+j).checked)
						{
							var selFlagCount = eval(document.getElementById('path_'+i+'_'+j).value)-1;
							for(k=1;k<=selFlagCount;k++)
							{
								document.getElementById('selFlag_'+i+'_'+j+'_'+k).value = 'Y';
								dtlId+=document.getElementById('lodgeDtlId_'+i+'_'+j+'_'+k).value+",";
								classId+=document.getElementById('paraFrm_roomTypeCode_'+i+'_'+j+'_'+k).value+",";
							}
							break;
						}
						if(j==opCount)
						{
							alert("Please select an option for accomodation in "+document.getElementById('fromCity_'+i).value);
							return false;
						}
					}
				}
				if(!confirm("Are you sure want to accept?"))
				{
					return false;
				}
				dtlId = dtlId.substring(0,dtlId.length-1);
				document.getElementById('dtlId').value=dtlId;
				classId = classId.substring(0,classId.length-1);
				travelMonitor_isPolicyViolate('TravelMonitor_isAccomPolicyViolate.action?classId='+classId+'&empId='+empId+'&abcd='+Math.random());
			}
			else if(document.getElementById('dontNeed').checked)
			{
				if(!confirm("Are you sure that you dont require accommodations arrangements?"))
				{
					return false;
				}
				cancelAccomDetails();
			}
			else if(document.getElementById('noneCheck').checked)
			{
				document.getElementById('paraFrm').target='main';
				document.getElementById('paraFrm').action='TravelMonitor_declineAccomDetails.action?dtlId=';
				window.close();
				document.getElementById('paraFrm').submit();
			}
		}catch(e)
		{
			alert(e);
		}
	}
	function afterAjax()
	{
		if(document.getElementById('violateFlag').value == 'N')
		{
			document.getElementById('violateFlag').value='';
			return false;	
		}
		window.close();
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='TravelMonitor_acceptAccomDetails.action?dtlId='+document.getElementById('dtlId').value+'&policyViolate='+document.getElementById('violateFlag').value;
		document.getElementById('paraFrm').submit();
	}
	function rejectAccomDetails()
	{
		try{
		if(document.getElementById('paraFrm_aprComments').value == '')
		{
			alert('please enter the comments for rejecting');
			document.getElementById('paraFrm_aprComments').focus();
			return false;
		}
		if(!confirm("Are you sure want to reject?"))
		{
			return false;
		}
		}catch(e)
		{
			alert(e);
		}
		var dtlId='';
		var journeyCount = <%=m-1%>;
		for(i=1;i<=journeyCount;i++)
		{
			var opCount = eval(document.getElementById('radioHidden_'+i).value)-1;
			for(j=1;j<=opCount;j++)
			{
				if(document.getElementById('optRadio_'+i+'_'+j).checked)
				{
					var selFlagCount = eval(document.getElementById('path_'+i+'_'+j).value)-1;
					for(k=1;k<=selFlagCount;k++)
					{
						dtlId+=document.getElementById('lodgeDtlId_'+i+'_'+j+'_'+k).value+",";
					}
					break;
				}
			}
		}
		if(dtlId.length > 1)
			dtlId = dtlId.substring(0,dtlId.length-1);
		window.close();
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='TravelMonitor_declineAccomDetails.action?dtlId='+dtlId;
		document.getElementById('paraFrm').submit();
	}
	
function dateBetweenTwoDates(fieldName, labelName, id)
{	
	try {
	var fromDate     = document.getElementById(fieldName[0]).value;
	var toDate       = document.getElementById(fieldName[1]).value;
	var enteredDate  = document.getElementById(fieldName[2]).value;
	
	var strDate   = fromDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
	var endDate   = toDate.split("-"); 
	var endtime   = new Date(endDate[2],endDate[1]-1,endDate[0]); 
	
	var validDate   = enteredDate.split("-");
	var validTime   = new Date(validDate[2],validDate[1]-1,validDate[0]); 
	
	if((validTime < starttime) || (validTime > endtime)) 
	{ 
		alert(""+labelName[2]+" should be between "+labelName[0]+" and "+labelName[1]+' for '+document.getElementById('source_'+id).value
					+'\n\nTour Start Date : '+document.getElementById('tourStartDate').value+' and Tour End Date : '+document.getElementById('tourEndDate').value);
		document.getElementById(fieldName[2]).focus();
		return false;
	}
	return true;
	}catch(e)
	{
		alert(e);
		return false;
	}
}

function dateDifferenceEqual(fromDate, toDate,i,j,k){
	try {
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(document.getElementById('toDate_'+i+'_'+j).innerHTML.toLowerCase()+' for accomodation in '
				+document.getElementById('source_'+i+'_'+j+'_'+k).value
				+' should be greater than or equal to\n\n '+document.getElementById('fromDate_'+i+'_'+j).innerHTML.toLowerCase());
		document.getElementById('accomToDate_'+i+'_'+j+'_'+k).focus();
		return false;
	}
	}catch(e)
	{
		alert(e);
	}
	return true;
}

function selectRoomType(id)
{
	if(document.getElementById('paraFrm_hotelTypeCode_'+id).value=='')
	{
		alert('Please select '+document.getElementById('hotel.type_1_1').innerHTML.toLowerCase()+' first');
		return false;
	}
	document.getElementById('paraFrm_rowId').value=id;
	document.getElementById('paraFrm_deskFlag').value=document.getElementById('paraFrm_hotelTypeCode_'+id).value;
	callsF9(500,325,'TravelMonitor_f9RoomType.action');
	document.getElementById('paraFrm').target='';
}

function selectHotelType(id)
{
	document.getElementById('paraFrm_rowId').value=id;
	callsF9(500,325,'TravelMonitor_f9HotelType.action');
	document.getElementById('paraFrm').target='';
}

	
function viewPolicy(gradeId)
{
	var applDate = document.getElementById('hiddApplicationDate').value;
	window.open('TravelApplication_getTravelPolicy.action?gradeId='+gradeId+'&appDate='+applDate,'polWinT','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
}


function viewDetails(travelAppId,travelIndiAppId,typeFlag){  //AppId,AppCode,self/guest
 		typeFlag='S';
		if(document.getElementById('hiddEmpId').value == 0)
			typeFlag='G';
		window.open('TravelAppvr_callView.action?tmsTrvlId='+travelAppId+'&tmsTrvlIndiId='+travelIndiAppId+'&chkFlg='+typeFlag+'&deskFlag=true','viewDtlT','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
}

function cancelAccomDetails()
{
	document.getElementById('paraFrm').target='main';
	window.close();
	document.getElementById('paraFrm').action = "TravelMonitor_cancel.action?type=A";
	document.getElementById('paraFrm').submit();
}

function approveAccom()
{
	if(!confirm("Are you sure want to approve?"))
	{
		return false;
	}
	document.getElementById('paraFrm').target='main';
	window.close();
	document.getElementById('paraFrm').action = "TravelMonitor_approve.action?type=A";
	document.getElementById('paraFrm').submit();
}

function checkOnClick(type)
{
	if(document.getElementById('hiddUserType').value != 'APL')
		return false;
	var journeyCount = <%=m-1%>;
	if(type != 'radio')
	{
		for(i=1;i<=journeyCount;i++)
		{
			var opCount = eval(document.getElementById('radioHidden_'+i).value)-1;
			for(j=1;j<=opCount;j++)
				document.getElementById('optRadio_'+i+'_'+j).checked=false;
		}	
	}
	if(type == 'none')
	{
		if(document.getElementById('noneCheck').checked)
			document.getElementById('dontNeed').checked=false;
		//else
			//document.getElementById('dontNeed').checked=true;
	
	}
	else if(type == 'dontNeed')
	{
		if(document.getElementById('dontNeed').checked)
			document.getElementById('noneCheck').checked=false;
		//else
			//document.getElementById('noneCheck').checked=true;
	}
	else
	{
		document.getElementById('dontNeed').checked=false;
		document.getElementById('noneCheck').checked=false;
	}
}

function zoomText(txtAreaId,labelId,remTxtId)
{
	if(document.getElementById(txtAreaId).readOnly)
	{
		callWindow(txtAreaId,labelId,'readonly',remTxtId,500);
	}
	else
	{
		callWindow(txtAreaId,labelId,'',remTxtId,500);
	}
}

function showCommentsTrail()
{
		var applCode = document.getElementById('hiddEmpApplId').value;
		var applId = document.getElementById('hiddApplicationId').value;	
	  	var wind = window.open('','win','width=600,height=300,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100','location=no');	 
		document.getElementById('paraFrm').target = "win";
		document.getElementById('paraFrm').action = "TravelAppvr_showCmtsTrail.action?applicationId="+applId+"&applicationCode="+applCode;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
}
</script>