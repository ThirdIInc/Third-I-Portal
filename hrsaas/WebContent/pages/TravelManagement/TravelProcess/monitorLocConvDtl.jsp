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
					<td width="93%" class="txt"><strong class="text_head">Local
					Conveyance Details</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<s:hidden name="viewFlag" />
					<s:hidden name="locConStatus" />
					<%!boolean flag = false;%>
					<td width="78%"><s:if test="!readOnlyFlag">
						<s:if
							test="(userType == 'SUB' || userType == 'SCH') && locConStatus=='Y '">
							<input type="button" value=" Save" onclick="saveLocConDetails();"
								name="save" class="save">
						</s:if>
					</s:if> <s:if test="userType == 'APL' && locConStatus=='SO'">
						<input type="button" value=" Submit"
							onclick="acceptLocalConDetails();" name="accept" class="save">
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
					<td width="20%"></td>
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
								<td colspan="3" width="20%"><strong> City : <s:property
									value="locConCity" /> </strong><input type="hidden"
									name="locConCity_<%=i%>"
									value='<s:property value="locConCity" />'
									id="locConCity_<%=i%>"></td>
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
									<input type="hidden" name="locCancelFlag<%=i%>_<%=j%>" 
										id='<%="cancelFlag"+i+"_"+j %>' value='<s:property value="locCancelFlag" />'>
										<tr>
											<td colspan="1" width="2%"><input type="radio"
												onclick="checkOnClick('radio');" name="optRadio_<%=i%>"
												id="optRadio_<%=i%>_<%=j%>" value='<s:property value="locCancelFlag" />'></td>
											<td colspan="7" align="right" width="98%">
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2" class="formbg">

												<tr>
													<td colspan="12" width="100%">
													<table width="100%" border="0" align="center"
														cellpadding="2" cellspacing="2">
														<tr>
															<td width="10%" class="formth"><label name="locCity"
																id="locCity_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locCity")%></label><font
																color="red"> * </font></td>
															<td width="9%" class="formth"><label name="locDate"
																id="locDate_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locDate")%></label><font
																color="red"> * </font> (DD-MM-YYYY)</td>
															<td width="9%" class="formth"><label
																name="locTrvlMode" id="locTrvlMode_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locTrvlMode")%></label><font
																color="red"> * </font></td>
															<td width="9%" class="formth"><label
																name="locTrvlModeNo" id="locTrvlModeNo_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locTrvlModeNo")%></label></td>
															<td width="9%" class="formth"><label
																name="locConPerson" id="locConPerson_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locConPerson")%></label></td>
															<td width="9%" class="formth"><label name="locConNo"
																id="locConNo_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locConNo")%></label></td>
															<td width="9%" class="formth"><label
																name="locPicPerson" id="locPicPerson_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locPicPerson")%></label></td>
															<td width="9%" class="formth"><label
																name="locFrmTime" id="locFrmTime_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locFrmTime")%></label><font
																color="red"> * </font> (HH24:MM)</td>
															<td width="9%" class="formth"><label
																name="locToTime" id="locToTime_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locToTime")%></label><font
																color="red"> * </font> (HH24:MM)</td>
															<td width="9%" class="formth"><label
																name="locPickUpPlace"
																id="locPickUpPlace_<%=i %>_<%=j %>"
																ondblclick="callShowDiv(this);"><%=label.get("locPickUpPlace")%></label></td>

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
																<s:hidden name='<%="locConvCode_"+i+"_"+j %>'
																	value='%{locConvCode}'
																	id='<%="locConvCode_"+i+"_"+j+"_"+k %>' />
																<s:hidden name='<%="locDtlId_"+i+"_"+j %>'
																	id='<%="locDtlId_"+i+"_"+j+"_"+k %>'
																	value='%{locDtlId}' />
																<s:hidden name='<%="selFlag_"+i+"_"+j %>'
																	value='%{selFlag}' id='<%="selFlag_"+i+"_"+j+"_"+k %>' />

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="source" />&nbsp;
																	<s:hidden
																	 name='<%="source_"+i+"_"+j%>'
																	id='<%="source_"+i+"_"+j+"_"+k %>' value="%{source}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="source_"+i+"_"+j%>'
																	readonly="%{readOnlyFlag}"
																	id='<%="source_"+i+"_"+j+"_"+k %>' value="%{source}"
																	size="15" maxlength="50"
																	onkeypress="return allCharacters();" /></s:else></td>
																<td class="sortabletd"  valign="top" nowrap="nowrap">
																<s:if test="readOnlyFlag">
																	<s:property value="travelDate" />&nbsp;
																	<s:hidden
																	name='<%="travelDate_"+i+"_"+j%>'
																	id='<%="travelDate_"+i+"_"+j+"_"+k%>'
																	value="%{travelDate}" />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" size="15" maxlength="10"
																	onkeypress="return numbersWithHiphen();"
																	name='<%="travelDate_"+i+"_"+j%>'
																	id='<%="travelDate_"+i+"_"+j+"_"+k%>'
																	readonly="%{readOnlyFlag}" value="%{travelDate}" /></s:else><a
																	href="javascript:NewCal('<%="travelDate_"+i+"_"+j+"_"+k%>','DDMMYYYY');">
																<%
																if (flag) {
																%><img class="iconImage"
																	src="../pages/images/recruitment/Date.gif" width="16"
																	height="16" class="imageIcon" border="0"
																	align="absmiddle" /> <%
 }
 %>
																</a></td>
																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="travelModeClass" />&nbsp;
																	<s:hidden
																	name='<%="mode_"+i+"_"+j%>' 
																	value="%{travelModeClass}" 
																	id='<%="mode_"+i+"_"+j+"_"+k%>' />
																</s:if>
																<s:else>
																<s:textfield
																	name='<%="mode_"+i+"_"+j%>' size="15" theme="simple"
																	value="%{travelModeClass}" readonly="%{readOnlyFlag}" maxlength="50"
																	id='<%="mode_"+i+"_"+j+"_"+k%>' /></s:else></td>
																</td>
																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConVehNo" />&nbsp;
																	<s:hidden name='<%="locConVehNo_"+i+"_"+j%>'
																	value="%{locConVehNo}"  />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="locConVehNo_"+i+"_"+j%>'
																	value="%{locConVehNo}" size="15" maxlength="50"
																	readonly="%{readOnlyFlag}" onkeypress="alphaNumeric();" />
																	</s:else></td>

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConConPer" />&nbsp;
																	<s:hidden name='<%="locConConPer_"+i+"_"+j%>'
																	value="%{locConConPer}"  />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="locConConPer_"+i+"_"+j%>'
																	value="%{locConConPer}" size="20" maxlength="50"
																	readonly="%{readOnlyFlag}"
																	onkeypress="allCharacters();" /></s:else></td>

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConConNo" />&nbsp;
																	<s:hidden name='<%="locConConNo_"+i+"_"+j%>'
																	value="%{locConConNo}" />
																</s:if>
																<s:else>
																<s:textfield name='<%="locConConNo_"+i+"_"+j%>'
																	value="%{locConConNo}"  size="20" maxlength="12"
																	readonly="%{readOnlyFlag}"
																	onkeypress="return numbersOnly();"/>
																	</s:else></td>

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConPicPer" />&nbsp;
																	<s:hidden name='<%="locConPicPer_"+i+"_"+j%>'
																	value="%{locConPicPer}"  />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="locConPicPer_"+i+"_"+j%>'
																	value="%{locConPicPer}" size="20" maxlength="50"
																	readonly="%{readOnlyFlag}"
																	onkeypress="return allCharacters();" />
																	</s:else>
																	</td>

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConFrmTime" />&nbsp;
																	<s:hidden name='<%="locConFrmTime_"+i+"_"+j%>'
																	value="%{locConFrmTime}" 
																	id='<%="locConFrmTime_"+i+"_"+j+"_"+k%>'  />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="locConFrmTime_"+i+"_"+j%>'
																	value="%{locConFrmTime}" readonly="%{readOnlyFlag}"
																	onkeypress="return numbersWithColon();" maxlength="5"
																	id='<%="locConFrmTime_"+i+"_"+j+"_"+k%>' size="8" />
																</s:else>
																	</td>

																<td class="sortabletd" valign="top" >
																<s:if test="readOnlyFlag">
																	<s:property value="locConToTime" />&nbsp;
																	<s:hidden name='<%="locConToTime_"+i+"_"+j%>'
																	value="%{locConToTime}"
																	id='<%="locConToTime_"+i+"_"+j+"_"+k%>' />
																</s:if>
																<s:else>
																<s:textfield
																	theme="simple" name='<%="locConToTime_"+i+"_"+j%>'
																	value="%{locConToTime}" readonly="%{readOnlyFlag}"
																	onkeypress="return numbersWithColon();" maxlength="5"
																	id='<%="locConToTime_"+i+"_"+j+"_"+k%>' size="8" />
																</s:else>
																	</td>

																<td class="sortabletd" valign="top" nowrap="nowrap">
																<s:textarea
																	theme="simple" name='<%="locConPicPlace_"+i+"_"+j%>' onkeyup="textCounter(this,500)"
																	value="%{locConPicPlace}" rows="1" cols="10" id='<%="locConPicPlace_"+i+"_"+j+"_"+k%>'
																	readonly="%{readOnlyFlag}"
																	onkeypress="return allCharacters();" />
																	<input type="hidden" name="remCount" id='remCount_<%=i%>_<%=j%>_<%=k%>' >
																	<%if(flag){ %><img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
							 										onclick="callWindow('locConPicPlace_<%=i%>_<%=j%>_<%=k%>','locPickUpPlace_1_1','','remCount_<%=i%>_<%=j%>_<%=k%>',500);">
							 										<%} 
							 										else
							 										{%>
							 										<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
							 										onclick="callWindow('locConPicPlace_<%=i%>_<%=j%>_<%=k%>','locPickUpPlace_1_1','readonly','remCount_<%=i%>_<%=j%>_<%=k%>',500);">
							 										<%} %> 
																	</td>

																<s:hidden
																	name='<%="locConToriffCost_"+i+"_"+j%>'
																	value="%{locConToriffCost}" />

																<%
																if (flag) {
																%>
																<td width="7%" class="sortabletd" ><input
																	type="button" name="remove" value="Remove"
																	class="token"
																	onclick="removeRowOption('<%=i%>_<%=j%>_<%=k%>')"></td>
																<%
																}
																%>

															</tr>

															<script>
													if(document.getElementById('selFlag_<%=i%>_<%=j%>_<%=k%>').value == 'Y' && document.getElementById('paraFrm_locConStatus').value=='CE')
											 			document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#FFFFCC';
													</script>
															<s:if test="locCancelFlag == 'PC' || locCancelFlag == 'CC'">
																<script>
																	document.getElementById('tr_<%=i%>_<%=j%>_<%=k%>').style.background='#FFC488';
																</script>
															</s:if>
															<%
																++k;
																++totCount;
															%>
														</s:iterator>
														<s:if test="readOnlyFlag">
															<script>
											 				document.getElementById('mode_<%=i%>_<%=j%>_<%=k%>').disabled=true;
														</script>
														</s:if>
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
				<s:if test="locConStatus=='Y '">
				</s:if>
				<s:elseif test="locConStatus=='CN'">
					<script>
						document.getElementById('dontNeed').checked=true;
					</script>
				</s:elseif>
				<script>
						if(document.getElementById('hiddUserType').value != 'APL' || document.getElementById('paraFrm_locConStatus').value != 'SO')
						{
							document.getElementById('noneCheck').disabled=true;
							document.getElementById('dontNeed').disabled=true;
							var count = <%=i-1%>;
							if(document.getElementById('paraFrm_locConStatus').value != 'Y ')
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
				<tr id="commRow" style="display: none">
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="20%"><label name="aplComments" id="aplComments"
								ondblclick="callShowDiv(this);"><%=label.get("aplComments")%></label>
							:</td>
							<td width="30%"><s:textarea name="aplComments" rows="3"
								onkeyup="textCounter(this,500);" cols="30" theme="simple"
								wrap="true" />
								<img src="../pages/images/zoomin.gif" style="vertical-align: bottom;" class="iconImage" height="10" align="absmiddle" width="10"
							 				onclick="zoomText('paraFrm_aplComments','aplComments','remCount_1_1_1');">
								</td>
							<td width="20%"></td>
							<td width="30%"><s:hidden name="aprComments" /></td>
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
			<s:if test="locConStatus!='Y ' && locConStatus!='SO'">
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
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:if test="!readOnlyFlag">
						<s:if
							test="(userType == 'SUB' || userType == 'SCH') && locConStatus=='Y '">
							<input type="button" value=" Save" onclick="saveLocConDetails();"
								name="save" class="save">
						</s:if>
					</s:if> <s:if test="userType == 'APL' && locConStatus=='SO'">
						<input type="button" value=" Submit"
							onclick="acceptLocalConDetails();" name="accept" class="save">
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
				if(document.getElementById('paraFrm_locConStatus').value=='Y ')
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
		document.getElementById('paraFrm').action = 'TravelMonitor_addRowToOptionForLocConDtls.action?pathName='
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
			document.getElementById('paraFrm').action = 'TravelMonitor_addOptionForLocConDtls.action?pathName='
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
				alert('Option cannot be removed! local conveyance for '+document.getElementById('locConCity_'+pathName).value+' should have atleast one option');
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
			if(!confirm('Are you sure want to remove?'))
				return false;
			document.getElementById('paraFrm').action = 'TravelMonitor_removeOptionForLocConDtls.action?pathName='
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
				alert('Option cannot be removed!  City '+document.getElementById('locConCity_'+iVal[0]).value +' should have atleast one option');
				return false;
			}
		}
		var empId = document.getElementById('hiddEmpId').value;
		var empApplId = document.getElementById('hiddEmpApplId').value;
		var applicationId = document.getElementById('hiddApplicationId').value;
		var userType = document.getElementById('hiddUserType').value; 
		var iniEmpId = document.getElementById('hiddIniEmpId').value;
		var applDate = document.getElementById('hiddApplicationDate').value;
		if(!confirm('Are you sure want to remove?'))
			return false;			
		document.getElementById('paraFrm').action = 'TravelMonitor_removeRowOptionForLocConDtls.action?pathName='
			+pathName+'&maxMainCount='+maxMainCount+'&maxOptCount='+maxOptCount;
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	
	
	function saveLocConDetails()
	{
		try{
		var empApplId = document.getElementById('hiddEmpApplId').value;
		var locConCount = <%=m-1%>;		
		
				
		for(i=1;i<=locConCount;i++)
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
					var city=trim(document.getElementById('source_'+i+'_'+j+'_'+k).value);
					var date=trim(document.getElementById('travelDate_'+i+'_'+j+'_'+k).value);
					var mode=trim(document.getElementById('mode_'+i+'_'+j+'_'+k).value);
				    var frmTime=trim(document.getElementById('locConFrmTime_'+i+'_'+j+'_'+k).value);
				    var toTime=trim(document.getElementById('locConToTime_'+i+'_'+j+'_'+k).value);
				    var fieldName = ['tourStartDate','tourEndDate','travelDate_'+i+'_'+j+'_'+k];
					var labelName = ['tour start date','tour end date',document.getElementById('locDate_'+i+'_'+j).innerHTML.toLowerCase()];	
					if(city == '')
					{
						alert("Please enter "+document.getElementById('locCity_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('source_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					if(date == '')
					{
						alert("Please enter "+document.getElementById('locDate_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('travelDate_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateDate('travelDate_'+i+'_'+j+'_'+k,'locDate_'+i+'_'+j)
								|| !dateBetweenTwoDates(fieldName,labelName,i+'_'+j+'_'+k))
					{
						return false;
					}
					if(mode == '')
					{
						alert("Please select "+document.getElementById('locTrvlMode_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('mode_'+i+'_'+j+'_'+k).focus();
						return false;
					}
				
					if(frmTime == '')
					{
						alert("Please enter "+document.getElementById('locFrmTime_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('locConFrmTime_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateTime('locConFrmTime_'+i+'_'+j+'_'+k,'locFrmTime_'+i+'_'+j))
					{
						return false;
					}
				
					if(toTime == '')
					{
						alert("Please enter "+document.getElementById('locToTime_'+i+'_'+j).innerHTML.toLowerCase());
						document.getElementById('locConToTime_'+i+'_'+j+'_'+k).focus();
						return false;
					}
					else if(!validateTime('locConToTime_'+i+'_'+j+'_'+k,'locToTime_'+i+'_'+j))
					{
						return false;
					}
					prvTime = (document.getElementById('locConFrmTime_'+i+'_'+j+'_'+k).value).split(':');
					thisTime = (document.getElementById('locConToTime_'+i+'_'+j+'_'+k).value).split(':');
					if(eval(prvTime[0]) > eval(thisTime[0]) || (eval(prvTime[0]) == eval(thisTime[0]) && eval(prvTime[1]) >= eval(thisTime[1])))
					{
						alert(document.getElementById('locToTime_'+i+'_'+j).innerHTML.toLowerCase()
						+' should be greater than '+document.getElementById('locFrmTime_'+i+'_'+j).innerHTML.toLowerCase()
						+' for city '+document.getElementById('locConCity_'+i).value);
						document.getElementById('locConToTime_'+i+'_'+j+'_'+k).focus();
						return false;
					}
				document.getElementById('locConvCode_'+i+'_'+j+'_'+k).value = document.getElementById('locConvCode_'+i+'_1_1').value;
				}
			}
		}
		if(!confirm("Are you sure want to save? Press ok to confirm and \n\ncancel to recheck the information"))
		{
			return false;
		}
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='TravelMonitor_saveLocConDetails.action?empApplId'+empApplId+'&totCount='+<%=count-1%>+'&locConCnt='+<%=m-1%>;
		window.close();
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert(e);
		}
	}
	
	function acceptLocalConDetails()
	{
		try {
			var empApplId = document.getElementById('hiddEmpApplId').value;
			var empId = document.getElementById('hiddEmpId').value;
			var localCount = <%=m-1%>;
			var dtlId = "";	
			if(!(document.getElementById('noneCheck').checked || document.getElementById('dontNeed').checked))
			{
				for(i=1;i<=localCount;i++)
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
								dtlId+=document.getElementById('locDtlId_'+i+'_'+j+'_'+k).value+",";
							}
							break;
						}
						if(j==opCount)
						{
							alert("Please select an option for city "+document.getElementById('locConCity_'+i).value);
							return false;
						}
				
										
					}
				}//end of for loop		
			}
			else if(document.getElementById('dontNeed').checked)
			{
				if(!confirm("Are you sure that you dont require local conveyance?"))
				{
					return false;
				}
				cancelLocConDetails();
				return false;
			}
			else if(document.getElementById('noneCheck').checked)
			{
				document.getElementById('paraFrm').target='main';
				document.getElementById('paraFrm').action='TravelMonitor_declineLocConDetails.action?dtlId=';
				window.close();
				document.getElementById('paraFrm').submit();
				return false;
			}	
		
		if(!confirm("Are you sure want to accept?"))
		{
			return false;
		}
		dtlId = dtlId.substring(0,dtlId.length-1);
		document.getElementById('dtlId').value=dtlId;
		document.getElementById('paraFrm').target='main';
		window.close();
		document.getElementById('paraFrm').action='TravelMonitor_acceptLocConDetails.action?dtlId='+document.getElementById('dtlId').value;
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(e);
		}
	}
	
	function rejectLocalConDetails()
	{
		if(document.getElementById('paraFrm_aprComments').value == '')
		{
			alert('Please enter the comments for rejecting');
			document.getElementById('paraFrm_aprComments').focus();
			return false;
		}
		document.getElementById('paraFrm').target='main';  
		window.close();
		document.getElementById('paraFrm').action='TravelMonitor_declineLocConDetails.action?dtlId=';
		document.getElementById('paraFrm').submit();
	}
	
	
	function dateBetweenTwoDates(fieldName, labelName, id)
{	
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
		alert(""+labelName[2]+" should be between "+labelName[0]+" and "+labelName[1]+' for City '+document.getElementById('source_'+id).value
					+'\n\nTour Start Date : '+document.getElementById('tourStartDate').value+' and Tour End Date : '+document.getElementById('tourEndDate').value);
		document.getElementById(fieldName[2]).focus();
		return false;
	}
	return true;
}

function viewPolicy(gradeId)
{
	var applDate = document.getElementById('hiddApplicationDate').value;
	window.open('TravelApplication_getTravelPolicy.action?gradeId='+gradeId+'&appDate='+applDate,'polL','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
}


function viewDetails(travelAppId,travelIndiAppId,typeFlag){  //AppId,AppCode,self/guest
 		typeFlag='S';
		if(document.getElementById('hiddEmpId').value == 0)
			typeFlag='G';
		window.open('TravelAppvr_callView.action?tmsTrvlId='+travelAppId+'&tmsTrvlIndiId='+travelIndiAppId+'&chkFlg='+typeFlag+'&deskFlag=true','localApp','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
}

function cancelLocConDetails()
{
	document.getElementById('paraFrm').target='main';
	window.close();
	document.getElementById('paraFrm').action = "TravelMonitor_cancel.action?type=L";
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