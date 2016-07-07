<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelClaim" validate="" id="paraFrm" theme="simple">
	<input type="hidden" name="fieldName" id="paraFrm_fieldName">
	<s:hidden name="tmsExpType" />
	<!-- For setting params for showing booking details -->
	<s:hidden name="bDtlAppId" />
	<s:hidden name="bDtlAppCode" />
	<s:hidden name="bDtlInitrId" />
	 <s:hidden name="bDtlEmpId" />
	<s:hidden name="bDtlAppDate" />
	<s:hidden name="bDtlForFlag" />
	<!-- flags for showing the booking details in self arrangement case -->
	<s:hidden name="tmsTrvlId" />
	<s:hidden name="tmsTrvlIndiId" />
	<s:hidden name="tmsChkTypeFlg" />
	<s:hidden name="deskFlag" />
	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />
	<s:hidden name="claimApplnCode" />
	<s:hidden name="listType" />
	<s:hidden name="appDate" />
	<s:hidden name="applnId" />
	<s:hidden name="applnCode" />
	<s:hidden name="applnStatus" />
	<s:hidden name="applnFor" />
	<s:hidden name="applnInitId" />
	<s:hidden name="applnEmpId" />
	<s:hidden name="SchFlag" />
	<s:hidden name="expTabLength" />
	<s:hidden name="defaultCurrencyFlag" />
	
	 <s:hidden name="source" id="source"/> 

	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td  width="4%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" ><strong
						class="text_head">Travel Claim Application</strong></td>
					<td width="3%" valign="top" class="txt" >
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
					<td width="80%" colspan="2"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" >
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="applnStatus=='_F' || hiddenStatus=='_F'">
			<tr>
				<td colspan="3">
				<table class="formbg" width="100%">
					<tr>
						<td colspan="3"><font color="red"><b>Application has been revoked. So cannot apply for claim.</b></font>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
				<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="approverListFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date </td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
								</td>

							</tr>
							<%
							int m = 1;
							%>
				<s:iterator value="approverCommentList" status="stat">
					<tr>
					<td width="10%" class="sortableTD"><%=m%><s:hidden name="appSrNo" value="%{<%=m%>}"/> </td>
						<td width="15%" class="sortableTD"><s:property value="prevApproverID"/><s:hidden name="prevApproverID"/></td>
							<td width="25%" class="sortableTD"><s:property value="prevApproverName"/><s:hidden name="prevApproverName"/></td>
								<td width="10%" class="sortableTD" align="center"><s:property value="prevApproverDate"/><s:hidden name="prevApproverDate"/></td>
								<td width="10%" class="sortableTD">&nbsp;<s:property value="prevApproverStatus"/><s:hidden name="prevApproverStatus"/></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property value="prevApproverComment"/><s:hidden name="prevApproverComment"/></td>
					</tr>
							<%
							m++;
							%>
					</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->
		<!-- EMPLOYEE INFORMATION SECTION BEGINS  -->
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4" width="100%"><b>Employee Information</b></td>
				</tr>
				<!-- New/Sent Back applications -->
				<tr>
					<td width="20%"  height="22"><s:if
						test='%{applnFor=="G"}'>
						<label class="set" name="trainitor" id="trainitor"
							ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>
					</s:if> <s:else>
						<label class="set" name="Empname" id="Empname"
							ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label>
					</s:else> :</td>
					<td width="30%" >
					<s:hidden name="employeeName"></s:hidden>
					<s:property value="employeeName" /><s:hidden
						name="employeeToken" /><s:hidden name="employeeId" /></td>
					<td width="20%" ><label class="set"
						name="Gradename" id="Gradename" ondblclick="callShowDiv(this);"><%=label.get("Gradename")%></label>
					:</td>
					<td width="30%" ><s:hidden name="gradId" /><s:property
						value="grad" /></td>
				</tr>
				<tr>
					<td width="20%" ><label class="set"
						name="Trastdate" id="Trastdate" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
					:</td>
					<td width="30%" ><s:property value="trvlStartDate" />
					<s:hidden name="trvlStartDate" /></td>
					<td width="20%" ><label class="set"
						name="Traenddate" id="Traenddate" ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label> :</td>
					<td width="30%" ><s:property value="trvlEndDate" />
					<s:hidden name="trvlEndDate" /></td>
				</tr>
				<tr>
					<td  width="20%" height="22"><label class="set"
						name="traappdate" id="traappdate" ondblclick="callShowDiv(this);"><%=label.get("traappdate")%></label>:
					</td>
					<td width="30%" ><s:hidden name="expAppDateDraft" />
					<s:property value="expAppDateDraft" /></td>
					<td width="20%" >&nbsp;</td>
				</tr>
				<tr>
					<td width="20%"><label class="set"
						name="traReqname" id="traReqname" ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label>
					:</td>
					<td width="30%"><s:property
						value="trvlReqNameAddNew" /><s:hidden name="trvlReqNameAddNew" /></td>
					<td width="20%"><label class="set"
						name="Trapurpose" id="Trapurpose" ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
					:</td>
					<td width="30%" ><s:hidden name="travelPurposeId" /><s:hidden
						name="travelPurpose" /><s:property value="travelPurpose" /></td>
				</tr>

				<tr>
					<td width="20%"><label class="set"
						name="TravelType" id="TravelType" ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label>
					:</td>
					<td width="30%"><s:hidden name="travelTypeId" /><s:hidden
						name="travelType" /><s:property value="travelType" /></td>
					<td width="20%"><label class="set"
						name="reqAdvAmt" id="reqAdvAmt" ondblclick="callShowDiv(this);"><%=label.get("AdvAmount")%></label>
					:</td>
					<td width="30%" nowrap="nowrap">
						<s:property value="advanceAmtTaken" /><s:hidden name="advanceAmtTaken" />
						<s:property value="currencyEmployeeAdvance"/><s:hidden name="currencyEmployeeAdvance"/>
					</td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="Project" id="Project"
						ondblclick="callShowDiv(this);"><%=label.get("Project")%></label>
					:</td>
					<td width="30%"><s:hidden name="projectId" /> <s:property
						value="project" /><s:hidden name="project" /></td>

					<td width="20%"><label class="set" name="customer"
						id="customer" ondblclick="callShowDiv(this);"><%=label.get("customer")%></label>
					:</td>
					<td width="30%"><s:hidden name="customerId" /> <s:property
						value="customerName" /><s:hidden name="customerName" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="source" id="source"
						ondblclick="callShowDiv(this);"><%=label.get("src")%></label>
					:</td>
					<td width="30%"> <s:property value="sourceDestination"/>&nbsp;</td>
					<td width="20%"> <input name="viewPolicy"
						type="button" class="token" value="View Policy"
						onclick="viewpolicyFun();" /></td>
					<td width="30%"> &nbsp;
					<s:if test="showBookingDetailsFlag">
					<input name="bookingDetails"
						type="button" class="token" value="View Booking Details"
						onclick="viewbookingdetailsFun();" />
						</s:if>
						</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EMPLOYEE INFORMATION SECTION  ENDS-->
		<!-- TAVEL RATINGS SECTION  BEGINS-->
		<%
			int deskVal = 1;
			int hotelVal = 1;
			int hotelNameVal = 1;
		%>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<tr>
				<!-- 
					<td width="2%"><img id="paraFrm_travelImg" align="absbottom"
						alt="Open/Close ratings"
						src="../pages/common/img/nolines_plus.gif" class="iconImage"
						onclick="expandCollapseRatings('ratingsTable','paraFrm_travelImg');"></td>
					 -->
					<td width="20%" colspan="1"><b>Travel Desk Ratings</b></td>
					<td colspan="2">
					<div id="paraFrm_vote" style="font-family:tahoma; color:green;"></div>
					</td>
				</tr>
				<tr id="ratingsTable" style='display: inline';>
					<td colspan="4">
					<table width="100%" class="formbg" border="0">
						<%!int totalCount = 0;%>

						<s:iterator value="travelRatingParameterList">
							<tr>
								<td width="20%"><s:hidden name="deskRatingIdItt"
									id="paraFrm_deskRatingIdItt<%=deskVal%>" /> <input
									type="hidden" name="deskRatingItt"
									id="paraFrm_deskRatingItt<%=deskVal%>"
									value='<s:property value="deskRatingItt"/>' /> 
									<input type="hidden" name="deskRatingNameItt"
									 id="paraFrm_deskRatingNameItt<%=deskVal%>"
									value='<s:property value="deskRatingNameItt"/>' />
									
									<s:property	value="deskRatingNameItt" /> :</td>
								<td colspan="3">
								<img src="../pages/images/starGrey.png"
									class="iconImage"
									onmouseover="highlight(1,'paraFrm_deskRatingItt<%=deskVal%>','paraFrm_deskRatingIdItt<%=deskVal%>')"
									onclick="setStar('1','paraFrm_deskRatingIdItt<%=deskVal%>','paraFrm_deskRatingItt<%=deskVal%>')"
									onmouseout="losehighlight('paraFrm_deskRatingIdItt<%=deskVal%>','<%=deskVal%>')"
									id="paraFrm_deskRatingIdItt<%=deskVal%>1" /> <img
									src="../pages/images/starGrey.png" class="iconImage"
									onmouseover="highlight(2,'paraFrm_deskRatingItt<%=deskVal%>','paraFrm_deskRatingIdItt<%=deskVal%>')"
									onclick="setStar('2','paraFrm_deskRatingIdItt<%=deskVal%>','paraFrm_deskRatingItt<%=deskVal%>')"
									onmouseout="losehighlight('paraFrm_deskRatingIdItt<%=deskVal%>','<%=deskVal%>')"
									id="paraFrm_deskRatingIdItt<%=deskVal%>2" /> <img
									src="../pages/images/starGrey.png" class="iconImage"
									onmouseover="highlight(3,'paraFrm_deskRatingItt<%=deskVal%>','paraFrm_deskRatingIdItt<%=deskVal%>')"
									onclick="setStar('3','paraFrm_deskRatingIdItt<%=deskVal%>','paraFrm_deskRatingItt<%=deskVal%>')"
									onmouseout="losehighlight('paraFrm_deskRatingIdItt<%=deskVal%>','<%=deskVal%>')"
									id="paraFrm_deskRatingIdItt<%=deskVal%>3" /> <img
									src="../pages/images/starGrey.png" class="iconImage"
									onmouseover="highlight(4,'paraFrm_deskRatingItt<%=deskVal%>','paraFrm_deskRatingIdItt<%=deskVal%>')"
									onclick="setStar('4','paraFrm_deskRatingIdItt<%=deskVal%>','paraFrm_deskRatingItt<%=deskVal%>')"
									onmouseout="losehighlight('paraFrm_deskRatingIdItt<%=deskVal%>','<%=deskVal%>')"
									id="paraFrm_deskRatingIdItt<%=deskVal%>4" /> <img
									src="../pages/images/starGrey.png" class="iconImage"
									onmouseover="highlight(5,'paraFrm_deskRatingItt<%=deskVal%>','paraFrm_deskRatingIdItt<%=deskVal%>')"
									onclick="setStar('5','paraFrm_deskRatingIdItt<%=deskVal%>','paraFrm_deskRatingItt<%=deskVal%>')"
									onmouseout="losehighlight('paraFrm_deskRatingIdItt<%=deskVal%>','<%=deskVal%>')"
									id="paraFrm_deskRatingIdItt<%=deskVal%>5" />
									</td>
									
								<%
																	deskVal++;
																	%>

							</tr>
						</s:iterator>
						<%
						totalCount = deskVal;
						%>
					</table>
					</td>
				</tr>
				
				<s:if test="showHotelRatingFlag">
				<tr>
				<!-- 
					<td width="2%"><img id="paraFrm_hotelImg" align="absbottom"
						alt="Open/Close ratings"
						src="../pages/common/img/nolines_plus.gif" class="iconImage"
						onclick="expandCollapseRatings('ratingsHotelTable','paraFrm_hotelImg');"></td>
					 -->
					<td width="20%"><b>Hotel Ratings</b></td>
					<td colspan="2"><div id="paraFrm_Hotelvote" style="font-family:tahoma; color:green;"></div></td>
				</tr>
				<tr id="ratingsHotelTable" style='display: inline';>
					<td colspan="4">
					<table width="100%" class="formbg" border="0">
						<s:iterator value="hotelNameList">
							<tr>
								<td><s:hidden name="hotelIdItt"
									id="paraFrm_hotelIdItt<%=hotelNameVal%>" /> 
									<input type="hidden" name="hotelNameItt"
									 id="paraFrm_hotelNameItt<%=hotelNameVal%>"
									value='<s:property value="hotelNameItt"/>' />
									<strong>Hotel Name  : &nbsp;<s:property	value="hotelNameItt"
									 /></strong>
								
								<%!int totalHotelCount = 0;%>
								
								<table width="100%" border="0">
									<s:iterator value="hotelRatingParameterList">
										<tr>
											<td width="20%"><s:hidden name="hotelRatingIdItt"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>" /> <input
												type="hidden" name="hotelRatingItt"
												id="paraFrm_hotelRatingItt<%=hotelVal%>"
												value='<s:property value="hotelRatingItt"/>' /> 
												<input type="hidden" name="hotelRatingNameItt"
									 			id="paraFrm_hotelRatingNameItt<%=hotelVal%>"
												value='<s:property value="hotelRatingNameItt"/>' />
												<s:property	value="hotelRatingNameItt" /> :</td>
											<td colspan="3"><img src="../pages/images/starGrey.png"
												class="iconImage"
												onmouseover="highlightHotel(1,'paraFrm_hotelRatingItt<%=hotelVal%>','paraFrm_hotelRatingIdItt<%=hotelVal%>')"
												onclick="setHotelStar('1','paraFrm_hotelRatingIdItt<%=hotelVal%>','paraFrm_hotelRatingItt<%=hotelVal%>')"
												onmouseout="loseHotelHighlight('paraFrm_hotelRatingIdItt<%=hotelVal%>','<%=hotelVal%>')"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>1" /> <img
												src="../pages/images/starGrey.png" class="iconImage"
												onmouseover="highlightHotel(2,'paraFrm_hotelRatingItt<%=hotelVal%>','paraFrm_hotelRatingIdItt<%=hotelVal%>')"
												onclick="setHotelStar('2','paraFrm_hotelRatingIdItt<%=hotelVal%>','paraFrm_hotelRatingItt<%=hotelVal%>')"
												onmouseout="loseHotelHighlight('paraFrm_hotelRatingIdItt<%=hotelVal%>','<%=hotelVal%>')"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>2" /> <img
												src="../pages/images/starGrey.png" class="iconImage"
												onmouseover="highlightHotel(3,'paraFrm_hotelRatingItt<%=hotelVal%>','paraFrm_hotelRatingIdItt<%=hotelVal%>')"
												onclick="setHotelStar('3','paraFrm_hotelRatingIdItt<%=hotelVal%>','paraFrm_hotelRatingItt<%=hotelVal%>')"
												onmouseout="loseHotelHighlight('paraFrm_hotelRatingIdItt<%=hotelVal%>','<%=hotelVal%>')"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>3" /> <img
												src="../pages/images/starGrey.png" class="iconImage"
												onmouseover="highlightHotel(4,'paraFrm_hotelRatingItt<%=hotelVal%>','paraFrm_hotelRatingIdItt<%=hotelVal%>')"
												onclick="setHotelStar('4','paraFrm_hotelRatingIdItt<%=hotelVal%>','paraFrm_hotelRatingItt<%=hotelVal%>')"
												onmouseout="loseHotelHighlight('paraFrm_hotelRatingIdItt<%=hotelVal%>','<%=hotelVal%>')"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>4" /> <img
												src="../pages/images/starGrey.png" class="iconImage"
												onmouseover="highlightHotel(5,'paraFrm_hotelRatingItt<%=hotelVal%>','paraFrm_hotelRatingIdItt<%=hotelVal%>')"
												onclick="setHotelStar('5','paraFrm_hotelRatingIdItt<%=hotelVal%>','paraFrm_hotelRatingItt<%=hotelVal%>')"
												onmouseout="loseHotelHighlight('paraFrm_hotelRatingIdItt<%=hotelVal%>','<%=hotelVal%>')"
												id="paraFrm_hotelRatingIdItt<%=hotelVal%>5" /></td>
											<%
											hotelVal++;
											%>

										</tr>
									</s:iterator>
								</table>
								</td>
								<%
								totalHotelCount = hotelVal;
								%>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<!-- TAVEL RATINGS SECTION  ENDS-->
		<!-- TOUR REPORT SECTION BEGINS -->
		<tr>
			<td >
			<table width="100%" class="formbg" border="0">
				<tr>
					<!--  <td width="2%"><img id="paraFrm_tourImage" align="absbottom"
						alt="Open/Close ratings"
						src="../pages/common/img/nolines_plus.gif" class="iconImage"
						onclick="expandCollapseRatings('tourTable','paraFrm_tourImage');"></td>-->
					<td width="100%" colspan="3"><b>Tour Report</b></td>
				</tr>
				<tr id="tourTable" style='display:';>
					<td width="100%" colspan="4">
					<table class="formbg" border="0">
					<tr>
					</tr>
						<tr>
							<td width="20%"><b><label class="set" name="tourDesc"
								id="tourDesc" ondblclick="callShowDiv(this);"><%=label.get("tourDesc")%></label>
							<font color="red">*</font>:</b></td>
							<td colspan="2">
							<s:textarea name="tourComments" cols="80" rows="6"
							readonly="false" onkeypress="return allCharacters();"
							onkeyup="callLength('tourComments','descCnt2','2000');" /></td>
							<td valign="bottom" colspan="2" width="30%"><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_tourComments','tourDesc','','paraFrm_descCnt2','1000');">
							&nbsp;Remaining chars&nbsp;<s:textfield name="descCnt2"
							readonly="true" size="5"></s:textfield></td>
						</tr>
						<!-- <tr>
							<td><b><label class="set" name="serviceQual"
								id="serviceQual" ondblclick="callShowDiv(this);"><%=label.get("attachTour")%></label>
							:</b></td>
							<td><s:textfield name="tourReportFile"
								readonly="true" size="78" /></td>
							<td align="left" colspan="2"><input name="Upload"
								type="button" class="token" value="Browse"
								onclick="uploadFile('tourReportFile');" /></td>
						</tr>  
						<tr>
							<td width="20%"><b><label class="set" name="achieve"
								id="achieve" ondblclick="callShowDiv(this);"><%=label.get("achieve")%></label>
							:</b></td>
							<td width="30%" colspan="3"><s:textarea rows="3" cols="80"
								name="achievementComments" id="paraFrm_achievementComments" />
							</td>
						</tr>-->
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="6">
					<table class="formbg" border="0" width="100%">
						<tr>
							<td width="20%"><b><label class="set" name="action"
								id="action" ondblclick="callShowDiv(this);"><%=label.get("action")%></label>
							<font color="red">*</font></b></td>
							<s:if test="claimApp.showFlag"> 
							<td align="right"><input type="button" value="   Add   "
								class="token" align="middle" onclick="callAddTourReports();"></td>
								</s:if><s:else>
								<td  align="center">&nbsp;</td>
								</s:else>
						</tr>
						<tr>
							<td width="100%" colspan="4">
							<table id="followUpTable" width="100%" border="0" cellpadding="1"
								cellspacing="1" >
								<tr>
									<td class="formth"><b><label class="set" name="action"
										id="action1" ondblclick="callShowDiv(this);"><%=label.get("action")%></label></b>
									<font color="red">*</font>
									</td>
									<td class="formth" colspan="2"><b><label class="set"
										name="responsiblePerson" id="responsiblePerson1"
										ondblclick="callShowDiv(this);"><%=label.get("responsiblePerson")%></label></b>
									<font color="red">*</font>
									</td>
									<td class="formth"><b><label class="set" name="targetDt"
										id="targetDt1" ondblclick="callShowDiv(this);"><%=label.get("targetDt")%></label></b>
									<font color="red">*</font>
									</td>
									<td class="formth" align="center" colspan="2">&nbsp;</td>
								</tr>
								<%
								int counter = 0;
								%>
								<s:iterator value="followUpActionList">
									<tr>
										<td class="sortableTD" align="center"><input  maxlength="150"
													type="text" id="paraFrm_followUpCommentsItt<%=counter%>" name="followUpCommentsItt"
													value='<s:property value="followUpCommentsItt"/>' size="30" /></td>
										<td class="sortableTD" align="center">
										<input	type="hidden" id="paraFrm_responsibleEmpIdItt<%=counter%>"
													name="responsibleEmpIdItt"	value='<s:property value="responsibleEmpIdItt"/>' />
										<input	type="hidden" id="paraFrm_responsibleEmpTokenItt<%=counter%>"
													name="responsibleEmpTokenItt"	value='<s:property value="responsibleEmpTokenItt"/>' />
										<input	type="text" id="paraFrm_responsibleEmpItt<%=counter%>"
													name="responsibleEmpItt"
													value='<s:property value="responsibleEmpItt"/>' size="30" /></td>
										<s:if test="claimApp.showFlag">
										<td align="center" class="sortableTD"><img
										src="../pages/images/recruitment/search2.gif" class="iconImage"
										height="16" align="center" width="16"
										onclick="setRowIdValue(<%=counter%>);"
										</td>
										</s:if><s:else>
											<td class="sortableTD" align="center">&nbsp;</td>
										</s:else>
										<td class="sortableTD" align="center"><input type="text"
													id="paraFrm_targetDateItt<%=counter%>" name="targetDateItt"
													value='<s:property value="targetDateItt"/>' size="10"
													 /></td>
										<s:if test="claimApp.showFlag">
											<td align="center" class="sortableTD"><a href="javascript:NewCal('paraFrm_targetDateItt<%=counter%>','DDMMYYYY');">
												<img src="../pages/images/recruitment/Date.gif"	class="iconImage" align="center">
											</a></td>
											<td class="sortableTD" align="center"><img src="../pages/common/css/icons/delete.gif"
												onclick="deleteFollowUpAction(this);"></td>
											</s:if><s:else>
											<td class="sortableTD" align="center" colspan="2">&nbsp;</td>
											</s:else>
									</tr>
									<%counter++;%>
								</s:iterator>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- TOUR REPORT SECTION ENDS -->
		<!-- EXPENSE DETAILS SECTION  BEGINS-->
		
		<s:if test="claimApp.showFlag"> 
		<tr id="expenseDetails" style='display:';>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="3" width="100%"><b>Expense Details</b></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="expense.type"
						id="expense.type" ondblclick="callShowDiv(this);"><%=label.get("expense.type")%></label>
					<font color="red">*</font> :</td>
					<td colspan="3"><s:textfield name="expenseType"
						readonly="true" size="40" /><s:hidden name="expenseTypeId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:expenseTypeFunction(); "></td>
				</tr>
					<tr>
					<td width="20%"><label class="set" name="eligible.amount"
						id="eligible.amount" ondblclick="callShowDiv(this);"><%=label.get("eligible.amount")%></label> :
					</td>
					<s:if test="eligibleAmountFlag">
						<td width="30%"><b><label class="set" name="amountWithBill"
									id="amountWithBill" ondblclick="callShowDiv(this);"><%=label.get("amountWithBill")%></label></b> :
								<s:property value="amountWithBill" /> <s:hidden
									name="amountWithBill" value='%{amountWithBill}' /></td>
					</s:if>
					<s:else>
						<td width="30%"><s:hidden name="AtActual"></s:hidden> <s:property
									value="AtActual" /><s:hidden name="cityGrade"></s:hidden> <s:property
									value="cityGrade" /></td>
					</s:else>
					<s:if test="eligibleAmountFlag">
								<td width="20%"><b><label class="set" name="amountWithoutBill"
									id="amountWithoutBill" ondblclick="callShowDiv(this);"><%=label.get("amountWithoutBill")%></label></b> :
								<s:property value="amountWithoutBill" /> <s:hidden
									name="amountWithoutBill" value='%{amountWithoutBill}' /></td>
								<td width="30%"><b><label class="set" name="cityGrade" id="cityGrade"
									ondblclick="callShowDiv(this);"><%=label.get("cityGrade")%></label></b>:
								<s:property value="cityGrade" /> <s:hidden name="cityGrade"
									value='%{cityGrade}' /></td>
					</s:if>
				</tr>
				<s:if test="cityGradeFlag">
				<tr>
						<td width="20%"><label class="set" name="city.grade"
							id="city.grade" ondblclick="callShowDiv(this);"><%=label.get("city.grade")%></label>
						:</td>

						<td colspan="3"><s:select name="cityname"
							cssStyle="width:145" list="tmap" /></td>
					</tr>
				</s:if>
				<tr>
					<td width="20%"><label class="set" name="expense.date"
						id="expense.date" ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label> <font color="red">*</font> :</td>

					<td width="30%"><s:textfield name="expenseDate" size="10"
						maxLength="10" theme="simple" />
						<a href="javascript:NewCal('paraFrm_expenseDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a></td>
					<td width="20%"><label class="set" name="expense.amount"
						id="expense.amount" ondblclick="callShowDiv(this);"><%=label.get("expense.amount")%></label> <font color="red">*</font> :</td>

					<td width="30%" nowrap="nowrap">
					<s:if test="defaultCurrencyFlag">
								<s:if test="advanceAmtTaken==0.00" >
								
								<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyExpenseAmt" 
						  	name="currencyExpenseAmt" value='<s:property value="currencyExpenseAmt"/>' 						  	
						  	readonly="readonly"						  	
						  /> 
								</s:if>
					<s:else>
						<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyExpenseAmt" 
						  	name="currencyExpenseAmt" value='<s:property value="currencyEmployeeAdvance"/>' 						  	
						  	readonly="readonly"						  	
						  /> 
					</s:else>	  
					</s:if>
					<s:else>
						<input type="text" size="3" style="border: none;cursor: pointer;" id="paraFrm_currencyExpenseAmt" 
						  	name="currencyExpenseAmt" value='<s:property value="currencyExpenseAmt"/>' 
						  	title="Click here to change currency" 
						  	readonly="readonly"
						  	onclick="javascript:callDropdown('paraFrm_currencyExpenseAmt', 200, 250, 'TravelClaim_f9Currency.action',event,'false');" 
						  /> 
					</s:else>
						
						<s:textfield name="expenseAmt" size="10" maxLength="10" theme="simple" value='%{expenseAmt}' onkeypress="return numbersWithDot();" />
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="proof.required"
						id="proof.required" ondblclick="callShowDiv(this);"><%=label.get("proof.required")%></label>:</td>
					<td width="30%"><s:checkbox name="proofRequired" /></td>
				</tr>
					<tr>
					<td width="20%"><label class="set" name="attach.proof"
						id="attach.proof" ondblclick="callShowDiv(this);"><%=label.get("attach.proof")%></label>:</td>
					<td ><s:textfield name="uploadLocFileName"
						readonly="true" size="40" /> </td>
					<td colspan="2"><input name="Upload" type="button"
						class="token" value="Browse"
						onclick="uploadFile('uploadLocFileName');" />&nbsp;
						<input type="button" name="Add Proof" value="Upload" class="token"
						onclick="return callUpload();" /></td>
				</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
							int cnt = 0;
							int cnt1 = 0;
						%>

						<s:iterator value="proofList">
							<tr>
								<td width="3%"><%=++cnt%>. <s:hidden name="proofSrNo" /></td>
								<td width="40%"><a href="#"
									onclick="showproofname('<s:property value="proofName" />');">
								<s:hidden name="proofName" /> <s:property value="proofName" />
								</a></td>
								<td colspan="2"><a href="#"
									onclick="callForRemoveUpload(<%=cnt%>);">Remove Proof</a></td>
							</tr>
							<%
							cnt1 = cnt;
							%>
						</s:iterator>
						<%
						cnt1 = 0;
						%>
						<input type="hidden" name="hiddenProofCnt" id="hiddenProofCnt" value="<%=cnt %>"/>
					</table>
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="particulars"
						id="particulars" ondblclick="callShowDiv(this);"><%=label.get("particulars")%></label>
					:</td>
					<td colspan="3">
					<s:textarea name="particulars" cols="50" rows="2"
							readonly="false" onkeypress="return allCharacters();"
							  />
					  </td>
					
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button" value=" Add To List "
						class="token" align="middle" onclick="callAdd();"></td>
				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<!-- EXPENSE DETAILS SECTION  ENDS-->
		<!-- EXPENSE LIST SECTION  BEGINS-->
		<tr>
			<td colspan="3">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td colspan="2"><b>Expense Details List</b></td>
				<!--  	<td align="right">
					<s:if test="%{claimApp.showFlag}">
				<input type="button" value="Add Expense"
						class="token" align="middle" onclick="hideDisplayBlock('expenseDetails');">
						</s:if>
						</td>-->
				</tr>

				<tr>
					<td width="100%" colspan="10">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td class="formth" width="5%" nowrap="nowrap"><strong>Sr
							No.</strong></td>
							<td class="formth"><strong><label class="set"
								name="expense.date" id="expense.date1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></strong>
							</td>
							<td class="formth"><strong><label class="set"
								name="expense.type" id="expense.type1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.type")%></label></strong></td>
							<td class="formth"><strong><label class="set"
								name="particulars" id="particulars1"
								ondblclick="callShowDiv(this);"><%=label.get("particulars")%></label></strong></td>

							<td class="formth"><strong><label class="set"
								name="eligible.amount" id="eligible.amount"
								ondblclick="callShowDiv(this);"><%=label.get("eligible.amount")%></label>
							</strong></td>

							<td class="formth"><strong> <label class="set"
								name="expense.amount" id="expense.amount"
								ondblclick="callShowDiv(this);"><%=label.get("expense.amount")%></label>
							</strong></td>
							
							<td class="formth"><strong> <label class="set"
								name="expense.violated" id="expense.violated"
								ondblclick="callShowDiv(this);"><%=label.get("expense.violated")%></label>
							</strong></td>
							
							<td class="formth" width="10px"><strong>Proof</strong></td>
							<s:if test="submitFlag"></s:if>
							<s:else>
								<s:if test="%{claimApp.showFlag}">
									<td class="formth" colspan="1"><strong> <s:submit
										value="  Remove  " action="TravelClaim_removeExpDtls"
										cssClass="delete" theme="simple"
										onclick="return checkRemoveExpDtls();" /></strong><br>
									<input type="checkbox" name="" id="chkExp"
										onclick="checkAllExpDtls();"></td>
								</s:if>
							</s:else>
						</tr>
					
						<%!int c = 0, accCount = 0, total = 0;%>
						<%
						int y = 1;
						%>
						<s:hidden name="removeData" />
						<s:iterator value="expenseDtlList">
							<tr id="expense_<%=y %>">
								
								<td class="sortableTD" width="5%"><%=y%>
								<s:hidden name="expDtlId" />
								<s:hidden name="expItId" />
								<s:hidden name="srNo" /></td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="expenseDateIt" /> <s:hidden name="expenseDateIt" />&nbsp;
								</td>

								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="expenseTypeIt" /> <s:hidden name="expenseTypeIt" /> <s:hidden
									name="expenseTypeIdIt" />&nbsp;</td>

								<td class="sortableTD"><!-- <s:property value="particularsIt" /> -->
								<s:hidden name="particularsIt" id="<%="particularsIt"+y %>" />
								<s:property value="particularsIt" />&nbsp;</td>


								<td class="sortableTD" width="12%" nowrap="nowrap" align="right">
								<s:property value="eligibleAmtIt" /> <input type="hidden"
									name="eligibleAmtIt" id="eligibleAmtIt<%=y %>"
									value='<s:property value="eligibleAmtIt" />'> &nbsp;</td>

								<td class="sortableTD" width="10%" nowrap="nowrap" align="right">
									<s:property value="expenseAmtIt" /> 
									<input type="hidden" name="expenseAmtIt" id="expenseAmtIt<%=y %>"
									value='<s:property value="expenseAmtIt" />'> &nbsp;
									
									<s:property value="currencyExpenseAmtItr" /> 
									<input type="hidden" name="currencyExpenseAmtItr" id="currencyExpenseAmtItr<%=y %>"
									value='<s:property value="currencyExpenseAmtItr" />'>
								</td>
									
								<td class="sortableTD" width="10%" nowrap="nowrap" align="right"><s:property
									value="policyViolationTextIt" /> <input type="hidden"
									name="policyViolationTextIt" id="policyViolationTextIt<%=y %>"
									value='<s:property value="policyViolationTextIt" />'>
								&nbsp;</td>
								
									
								<td class="sortableTD" width="10px" nowrap="nowrap"><s:hidden
									name="proofIt" /> <s:hidden name="proofRequiredIt" /> <s:iterator
									value="ittUploadList">
									<a href="#"
										onclick=" showproofname('<s:property value='ittproofName' />');">
									<s:hidden name="ittproofName" /> <s:property
										value="ittproofName" /><br>
									</a>
								</s:iterator>&nbsp;</td>
								<s:hidden name="itteratorProofNameForSave" />
								<s:if test="submitFlag"></s:if>
								<s:else>
									<td align="center" class="sortableTD"><s:hidden
										name="submitFlag" /> <input type="checkbox" name="expChk"
										id="chkExp<%=y%>" value="<s:property value='expDtlId' />"
										onclick="checkAExp('chkExp<%=y%>','expFlag<%=y%>')"> <input
										type="hidden" name="expFlag" id="expFlag<%=y %>" value="N" />&nbsp;</td>
								</s:else>
							</tr>

							<%
							y++;
							%>
						</s:iterator>
						
						<%
												total = y;
												%>
						<%
						if (y == 1) {
						%>
						<tr>
							<td colspan="10" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
						<%
						} else {
						%>
						<tr>
							<td width="5%">&nbsp;</td>
							<td>&nbsp;</td>
							<td width="8%">&nbsp;</td>
							<td><strong>Total:</strong></td>
							<td width="12%"><s:hidden name="totElgAmt" /></td>

							<td align="right" nowrap="nowrap" width="10%">
								<s:textfield name="totExpAmt" size="5" readonly="true" cssStyle="text-align:right; border:none" /> 
								<s:textfield size="2" readonly="true" name="totalCurrencyExpense" cssStyle="text-align:center; border:none"/>
							</td>

							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<%
							}
							c = y;
							accCount = c;
							c = 0;
						%>

						<input type="hidden" name="hExpCount" id="hExpCount"
							value="<%=y%>">
						<s:hidden name="delExp" theme="simple" />
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EXPENSE LIST SECTION  ENDS-->
		
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="20%"><label class="set"
						name="IdComments" id="IdComments" ondblclick="callShowDiv(this);"><%=label.get("IdComments")%></label>
					:</td>
					<s:if test="submitFlag">
						<td width="30%"><s:property value="comment" /> <s:hidden
							name="comment" /></td>
					</s:if>
					<s:else>
						<td width="20%"><s:textarea name="comment" cols="70" rows="2"
							readonly="false" onkeypress="return allCharacters();"
							onkeyup="callLength('comment','descCnt1','1000');" /></td>
						<td valign="bottom" colspan="2" width="30%"><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comment','IdComments','','paraFrm_descCnt1','1000');">
						&nbsp;Remaining chars&nbsp;<s:textfield name="descCnt1"
							readonly="true" size="5"></s:textfield></td>
					</s:else>
					</td>
				</tr>
				<tr>
					<td colspan="3"><br>
					<font color="red">*</font> Please submit Boarding pass along with
					vouchers to the accounts department for air travel claim.</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="dataPath" />
	<s:hidden name="checkRemoveUpload" />
	<s:hidden name="policyViolationText" value="No" /> 
	<s:hidden name="policyViolationFlag" value="N" />
	<s:hidden name="hiddenStatus" />
	<s:hidden name="showHotelRatingFlag" />
	<s:hidden name="rowId" />
</s:form>

<script>
	loadStars();
	callMeOnload();
			function callMeOnload(){
			try{	
				
				if(document.getElementById("followUpTable").rows.length-1 < 1){
		 			callAddTourReports();
		 		}
                     var expCount='<%=accCount%>';
			                     if((eval(expCount)-1)!=0){    
			                     
			                     var tempEligbl=0.0;
			                     var totalCurrency;
			                         var tempExp=0.0;
			                         for(var x=1;x<=expCount-1;x++){          
			                                 if(document.getElementById('eligibleAmtIt'+x).value=="")
			                                 tempEligbl+=0.0;  
			                                 else
			                                 tempEligbl+=parseFloat(document.getElementById('eligibleAmtIt'+x).value);
			                       
			                                 
			                                 if(document.getElementById('expenseAmtIt'+x).value=="")
			                                 tempExp+=0.0;         
			                                 else
			                                 tempExp+=parseFloat(document.getElementById('expenseAmtIt'+x).value);
			                                 totalCurrency = document.getElementById('currencyExpenseAmtItr'+x).value; 
			                         } //end of for loop          
			         				 document.getElementById('paraFrm_totElgAmt').value=tempEligbl.toFixed(2);
			         				
			                         document.getElementById('paraFrm_totExpAmt').value=tempExp.toFixed(2);
			                         document.getElementById('paraFrm_totalCurrencyExpense').value = totalCurrency;
			                     }
                     }
                     catch(e)
                     {
                     alert(e);
                     }
       } 




function callAdd()  {
	try {	
		/*var fieldName = ["paraFrm_trvlStartDate","paraFrm_trvlEndDate","paraFrm_expenseDate","paraFrm_expenseType","paraFrm_expenseAmt"];
		var lableName = ["Trastdate","Traenddate","expense.date","expense.type","expense.amount"];
		var type = ['enter','enter','enter','enter','enter'];
		if(!validateBlank(fieldName, lableName, type))
    	return false;*/
    	
   		var filename = document.getElementById('paraFrm_uploadLocFileName').value;
   	
   		if(!filename=="") {
   			alert("Please upload file");
   			return false;
   		}
  		
  		if(!validateCurrency()) {
  			return false;
  		}
  		
  		
  		
	var doc=document.getElementById('paraFrm_proofRequired').checked;
	var fileUp=document.getElementById('paraFrm_uploadLocFileName').value;
	if(doc && fileUp==""){
	alert("Please Attach Proof");
	document.getElementById('paraFrm_uploadLocFileName').focus();
	return false;
	}
	if(!validateAddDtls()) {
    		return false;
  		}
	
  		
	} catch(e) {
		alert("Exception Occurred ================>"+e);
	}
	
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="TravelClaim_addExpenseDtl.action" ;
	document.getElementById("paraFrm").submit();
}

	
	
function draftFun(){
	try{
	var count = '<%=total%>';
	var iterator = eval(count-1);
	if(!validateTourDetails()){
		return false;
	}
	
	try{
	var doc=document.getElementById('paraFrm_proofRequired').checked;
	var fileUp=document.getElementById('paraFrm_uploadLocFileName').value;
	if(doc && fileUp==""){
	alert("Please Attach Proof");
	document.getElementById('paraFrm_uploadLocFileName').focus();
	return false;
	}
	}catch(e){
	alert(e);
	}
	
	
	if(eval(count-1)>0) {
		 
	}else{
		alert("Please add the expense details.");
		document.getElementById('paraFrm_trvlStartDate').focus();
		return false;
	}
	 
	var comment =	document.getElementById('paraFrm_comment').value;
	if(comment != "" && comment.length >1000) {
		alert("Maximum length of "+document.getElementById('IdComments').innerHTML.toLowerCase()+" is 1000 characters.");
		return false;
    } else {
		var applnStatus =	document.getElementById('paraFrm_applnStatus').value;
	 	if(applnStatus=="B") {
	 		document.getElementById('paraFrm').action="TravelClaim_save.action?buttonType=B";
	 	} else {
	 		document.getElementById('paraFrm').action="TravelClaim_save.action?buttonType=N";
	 	}	 
	 
		/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'save') {
				//alert(document.all[i]);
				document.all[i].disabled=true;
			}
		}*/
  		document.getElementById('paraFrm').submit();
	}
	}catch(e){
	alert("tinshuk ------"+e);
	}
}
	
	
function sendforapprovalFun(){
   // document.getElementById('paraFrm_applnStatus').value="P";	
    if(!validateTourDetails()){
			return false;
	}	
    var count = '<%=total%>';
	//alert(count-1);
	
	try{
	var doc=document.getElementById('paraFrm_proofRequired').checked;
	var fileUp=document.getElementById('paraFrm_uploadLocFileName').value;
	if(doc && fileUp==""){
	alert("Please Attach Proof");
	document.getElementById('paraFrm_uploadLocFileName').focus();
	return false;
	}
	}catch(e){
	alert(e);
	}
	
	if(eval(count-1)>0) {
		
	} else {
		alert("Please add the expense details.");
		document.getElementById('paraFrm_trvlStartDate').focus();
		return false;
	}	
	
	
   /* if(document.getElementById('paraFrm_expTabLength').value=="false"){
	alert("Please add the expense details.");
	document.getElementById('paraFrm_trvlStartDate').focus();
	return false;
	}*/
	
	var comment =	document.getElementById('paraFrm_comment').value;
	if(comment != "" && comment.length >1000) {
		alert("Maximum length of "+document.getElementById('IdComments').innerHTML.toLowerCase()+" is 1000 characters.");
		return false;
    } else {
	  var conf=confirm("Do you really want to send for approval?");
  	  if(conf) {
  	 	 document.getElementById('paraFrm').action="TravelClaim_save.action?buttonType=P";
  	 	 document.getElementById('sendforapproval').disabled=true;
  		 document.getElementById('paraFrm').submit();
  	  }
  	}  	
}

		
	
function backFun(){

	document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		 else{
		document.getElementById('paraFrm').action="TravelClaim_input.action";
		}
	   document.getElementById('paraFrm').submit();
   }
   
   function sendunblockedrequestFun(){
   document.getElementById('paraFrm').action="TravelClaim_sendUnlockedRequest.action";
   document.getElementById('paraFrm').submit();
   }
   
  
	
	function showRecord(fileName)
{
	document.getElementById('paraFrm').target ="_blank";
	document.getElementById('paraFrm').action = "TravelMonitor_viewCV.action?fileName="+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target ="main";
}
	
	function viewpolicyFun(){
 	
 		var gradeId = document.getElementById('paraFrm_gradId').value;
 		win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradeId;
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main"; 
 	}
	
function dateValidation(){  
   if(!validateDate('paraFrm_trvlStartDate',"Trastdate")){
		document.getElementById('paraFrm_trvlStartDate').focus();
		return false;   	
   }
   if(!validateDate('paraFrm_trvlEndDate',"Traenddate")){
		document.getElementById('paraFrm_trvlEndDate').focus();
		return false;   	
   }
   if(!validateDate('paraFrm_expenseDate',"expense.date")){
		document.getElementById('paraFrm_expenseDate').focus();
		return false;   	
   }
	    }
	    
 function validateSaveDtls(){
 	
 	
 	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var currentDate=day+"-"+month+"-"+year ;
	
	var travelEndDate = document.getElementById('paraFrm_trvlEndDate').value;
	
	
		var travelEnddate =document.getElementById('paraFrm_trvlEndDate').value;
  		var applicationDate =document.getElementById('paraFrm_expAppDateDraft').value;
	
	var datdiffresignDate =dateDifferenceEqual(travelEnddate,applicationDate,'paraFrm_trvlEndDate','Traenddate','traappdate');
  		
  	 
  		
  		if(!datdiffresignDate){
  		return false;
  		}
	
	 
	
	
   if(trim(document.getElementById('paraFrm_trvlStartDate').value)==""){
	    alert('Please enter '+document.getElementById("Trastdate").innerHTML+'');
	    document.getElementById('paraFrm_trvlStartDate').focus();
	    return false;
	    
   }if(!validateDate('paraFrm_trvlStartDate',"Trastdate")){
		document.getElementById('paraFrm_trvlStartDate').focus();
		return false;
	}
	
 /*
   if(!dateCompare(document.getElementById('paraFrm_trvlStartDate').value,currentDate,'paraFrm_trvlStartDate','Trastdate')) {
     	return false;
   }
  */
     
   if(trim(document.getElementById('paraFrm_trvlEndDate').value)==""){    
   	    alert('Please enter '+document.getElementById("Traenddate").innerHTML+'');
	    document.getElementById('paraFrm_trvlEndDate').value="";
	    document.getElementById('paraFrm_trvlEndDate').focus();
	    return false;
	    
   }if(!validateDate('paraFrm_trvlEndDate',"Traenddate")){ 
		document.getElementById('paraFrm_trvlEndDate').focus();
		return false;
	}

/*
   if(!dateCompare(document.getElementById('paraFrm_trvlEndDate').value,currentDate,'paraFrm_trvlEndDate','Traenddate')) {
     	return false;
    }
 */    
   if(!dateDifferenceEqual(document.getElementById('paraFrm_trvlStartDate').value, document.getElementById('paraFrm_trvlEndDate').value, 'paraFrm_trvlStartDate', 'Trastdate', 'Traenddate')){
	      document.getElementById('paraFrm_trvlStartDate').focus();
	      return false;
					      
   }
   
   if(trim(document.getElementById('paraFrm_travelPurpose').value)==""){    
	    alert('Please select '+document.getElementById("Trapurpose").innerHTML+'');
	    document.getElementById('paraFrm_travelPurpose').focus();
	    return false;
	    
   }if(trim(document.getElementById('paraFrm_travelType').value)==""){    
	    alert('Please select '+document.getElementById("TravelType").innerHTML+'');
	     document.getElementById('paraFrm_travelType').focus();
	    return false;
	    
   }    

  return true;
 }
 
function dateCompare(fromDate, toDate, fieldName,toLabName){
	
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		
	if(starttime >= endtime) 
	{ 
		
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be less than the system date.");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	return true;
}
 
 
 function validateAddDtls()
 {	
 	try
 	{  			
 		var fieldNameStart = ["paraFrm_trvlStartDate","paraFrm_trvlEndDate","paraFrm_expenseDate"]; 
		var lableNameStart = ["Trastdate","Traenddate","expense.date"];
	  	
	  	if(trim(document.getElementById('paraFrm_expenseType').value)=="")
		{    
	    	alert('Please select '+document.getElementById("expense.type").innerHTML+'');
	    	document.getElementById('paraFrm_expenseType').focus();
	    	return false;	    
   		}    	
	  	if(trim(document.getElementById('paraFrm_expenseDate').value)=="")
  		{
	    	alert('Please enter '+document.getElementById("expense.date").innerHTML+'');
	    	document.getElementById('paraFrm_expenseDate').focus();
	    	return false;	    
   		}
   		if(!validateDate('paraFrm_expenseDate',"expense.date"))
   		{
			document.getElementById('paraFrm_expenseDate').focus();
			return false;   	
   		}    
   		if(!dateBetweenTwoDates(fieldNameStart, lableNameStart))
   		{
   			document.getElementById('expense.date').focus();
			return false;
		}	
   		if(trim(document.getElementById('paraFrm_expenseAmt').value)=="")
   		{    
		    alert('Please enter '+document.getElementById("expense.amount").innerHTML+'');
		    document.getElementById('paraFrm_expenseAmt').focus();
	    	return false;	    
   		}	
   		//checking for expense amount
   		  	var atActual =false;
   		  	try
   		  	{
   		  		if(document.getElementById('paraFrm_AtActual').value =='At Actual')
   		  		{
   		  			atActual =true;
   		  		}
   		  	}
   		  	catch(e)
   		  	{
   		  		atActual =false;
   		  	}	
   		if(!atActual)
   		{
   			var proff = document.getElementById('paraFrm_proofRequired').checked ;		
 		    var checkEligibleAmountWithBill = document.getElementById('paraFrm_amountWithBill').value; 		
 			var checkEligibleAmountWithoutBill = document.getElementById('paraFrm_amountWithoutBill').value; 		
 			var checkExpenseAmount = document.getElementById('paraFrm_expenseAmt').value;
 			var attachProofList=document.getElementById('hiddenProofCnt').value;
 			//alert("checkEligibleAmountWithBill : : "+checkEligibleAmountWithBill);
 			//alert("checkExpenseAmount : : "+checkExpenseAmount);
 			//alert("attachProofList : : "+attachProofList);
			//if(proff)
			if(eval(attachProofList)>0)
				{					
					if(eval(checkExpenseAmount) > eval(checkEligibleAmountWithBill))
					{
						//alert("Expense amount should be less than or equal to eligible amount with bill.");
						//return false;		
						
						var conf = confirm("You are applying for more than eligible amount."+"\n"+"This will go for additional approval to higher management."+"\n"+"Would You like to proceed ?");
						if(conf) {	
							document.getElementById('paraFrm_policyViolationText').value="Yes";					
					 		return true;
							} else {
							document.getElementById('paraFrm_policyViolationText').value="No";	
							return false;	
						}
				  		 	
				}
			}		
		else
		   { 
			 if( eval(checkExpenseAmount) > eval(checkEligibleAmountWithoutBill) ) 
			 {
			// alert("Expense amount should be less than or equal to eligible amount without bill.");
			//		return false;	
			 //}
			 
			
					var conf = confirm("You are applying for more than eligible amount."+"\n"+"This will go for additional approval to higher management."+"\n"+"Would You like to proceed ?");
					if(conf) {
						document.getElementById('paraFrm_policyViolationText').value="Yes";						
				 		return true;
					} else {
						document.getElementById('paraFrm_policyViolationText').value="No";						
						return false;	
					}	 	
				}
					
		  }
   		}
   		var particulars =	document.getElementById('paraFrm_particulars').value;
		if(particulars != "" && particulars.length >1000) {
			alert("Maximum length of "+document.getElementById('particulars').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
    	}   		
   		
   		
   		
    }  //End of try
    
    
    catch(e)
    {
    	alert("Exception occurred in validateAddDtls()=========>"+e);
    	return false;
    } 
    
    	return true;		
 }
 
 
 function uploadFile(fieldName)
{
	
	try
	{
		var path = document.getElementById("paraFrm_dataPath").value;
		//alert("UPLOADED PATH===========>"+path);
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	catch(e)
	{
		alert("Error occurred in uploadFile() ===> "+e);
	}
}


function callUpload()
{
	try
	{	
		var uploadFile = document.getElementById('paraFrm_uploadLocFileName').value;	
	
		if(uploadFile=="")
		{		
			alert("Please Select Document to upload.");
			return false;
		}	
		
		document.getElementById('paraFrm_proofRequired').checked=false;
	}
	catch(e)
	{
		alert("Error Occured in callUpload===================> "+e);		
	}
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="TravelClaim_addMultipleProof.action" ;
	document.getElementById("paraFrm").submit();
} 
 

function callForRemoveUpload(id)
{
   var conf=confirm("Are you sure !\n You want to Remove this record ?");
	if(conf)
	{
		document.getElementById('paraFrm_checkRemoveUpload').value=id;
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="TravelClaim_removeUploadFile.action";
		document.getElementById("paraFrm").submit();
	}	
}
 function showproofname(fileName)
	{
	 	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelClaim_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} 
 

 
function reportFun()
  {
  	document.getElementById('paraFrm').action="TravelClaim_report.action";
   document.getElementById('paraFrm').submit();
  }
	
	
function expenseTypeFunction()
{	
		var grdId=document.getElementById('paraFrm_gradId').value;	
		callDropdown('paraFrm_expenseType',300,250,'TravelClaim_f9ExpenseType.action?gradeId='+grdId,'false');
		
}	
 


//To view self-managed scheduled Travel Application
function viewAppDetails(travelAppId,travelIndiAppId,typeFlag,cancel){  //AppId,AppCode,self/guest
		
		var deskBookFlag = "true";
		if(cancel=="false"){
		 	deskBookFlag = "false";
		}
 		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; //id
		document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId;//code
		document.getElementById('paraFrm_tmsChkTypeFlg').value = typeFlag;
		document.getElementById('paraFrm_deskFlag').value = "true";
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelAppvr_callView.action?deskBookFlag="+deskBookFlag+"&reqFrom=appl&cancel="+cancel;
		var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
}
	         
	   function viewbookingdetailsFun(){
		try{
			var applicationId=document.getElementById('paraFrm_applnId').value;
			//alert(applicationId);
			win=window.open('','win','top=260,left=250,width=900,height=600,scrollbars=yes,status=no,resizable=yes');
					document.getElementById("paraFrm").target="win";
		 document.getElementById('paraFrm').action='TravelQuickBooking_viewBooking.action?applicationId='+applicationId;
			document.getElementById("paraFrm").submit();
					document.getElementById("paraFrm").target="main";
		}catch(e){ // alert("e-------"+e);
		}
	} 
	
	 
	  
	
	function uploadFile(fieldName){
		try	{
			var path = document.getElementById("paraFrm_dataPath").value;
			window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		}catch(e){
			alert("Error occurred in uploadFile() ===> "+e);
		}
	}
	
	function checkRemoveExpDtls(){
		       var count = 0; 
		     	for(i=1;i<document.getElementById('hExpCount').value;i++){
			        if(document.getElementById('chkExp'+i).checked){
			         	count++;
			         }
				 }
				 if(count==0){
				  		alert('Please select atleast one record to remove.');
				  		return false;
				 }else{
				 		
				        var conf = confirm('Do you really want to remove the record?');
				        if(!conf){
				        	
				        	for(i=1;i<document.getElementById('hExpCount').value;i++){
					        	document.getElementById('chkExp'+i).checked="";		         	
				 			}
				 			document.getElementById('chkExp').checked="";	
				        	return false; 
				        }
				        return true;
				        				 
				 }
				 
		     }
		     
		     function checkAllExpDtls(){
		     
		     		for(i=1;i<document.getElementById('hExpCount').value;i++){
		     			
				        if(document.getElementById('chkExp').checked){
				        
				         	if( !(document.getElementById('chkExp'+i).disabled==true) ){
					         	document.getElementById('chkExp'+i).checked='true';
					         	document.getElementById('expFlag'+i).value="Y";
				         	}
				         	
				         }else{
				         	
				         	document.getElementById('chkExp'+i).checked='';
				         	document.getElementById('expFlag'+i).value="N";
				         	
				         }
				         
			           }
			           
		     }
		     
		     function checkAExp(chkId,flagId){
			              
			              if(document.getElementById(chkId).checked){
			              	document.getElementById(flagId).value="Y";
			              }else{
			                document.getElementById(flagId).value="N";
			              }
			              
		     }
	var toggleBlock=1;     
	function expandCollapseRatings(tableId,imageId){
		
		toggleBlock++;
		if(toggleBlock % 2 == 0){
			document.getElementById(tableId).style.display="";
			document.getElementById(imageId).src= minus.src;
		}else{
			document.getElementById(tableId).style.display="none";
			document.getElementById(imageId).src= plus.src;
		}
	}
	/* STAR RATING CODE BEGINS*/
	
	var set=false;
	var v=0;
	var a;
	
	
	function loadStars() {
	 
		star1 = new Image();
   		star1.src = "../pages/images/starGrey.png";
   		star2 = new Image();
   		star2.src= "../pages/images/starYellow1.png";
		plus = new Image();
   		plus.src = "../pages/common/img/nolines_plus.gif";
   		minus = new Image();
   		minus.src= "../pages/common/img/nolines_minus.gif";
   		
   		var totalParameters='<%=totalCount%>';
   		var totalHotelParameters='<%=totalHotelCount%>';
   		
   		for (ii=1;ii<totalParameters;ii++){
   		
   		var hiddenValue=document.getElementById('paraFrm_deskRatingItt'+ii).value;
   		
   		for (i=1;i<=5;i++){
   				document.getElementById('paraFrm_deskRatingIdItt'+ii+''+i).src= star1.src;
   			}
   		for (i=1;i<=hiddenValue;i++){
   				document.getElementById('paraFrm_deskRatingIdItt'+ii+''+i).src= star2.src;
   			}
   		}
   		
   		
   		
   		if(document.getElementById('paraFrm_showHotelRatingFlag').value=='true')
   		{
   		for (ii=1;ii<totalHotelParameters;ii++){
   		
   		var hiddenHotelValue=document.getElementById('paraFrm_hotelRatingItt'+ii).value;
   		
   		for (i=1;i<=5;i++){
   				document.getElementById('paraFrm_hotelRatingIdItt'+ii+''+i).src= star1.src;
   			}
   		for (i=1;i<=hiddenHotelValue;i++){
   				document.getElementById('paraFrm_hotelRatingIdItt'+ii+''+i).src= star2.src;
   			}
   		}
   		
   		}
   		
   		
	}
	
	function highlight(x,hiddenRatingId,rowRatingId) {
   		
   		if (set==false) {
   			for (i=1;i<=5;i++){
   				document.getElementById(rowRatingId+''+i).src= star1.src;
   			}
   			for (i=1;i<=x;i++){
   				document.getElementById('paraFrm_vote').innerHTML=i+" star";
   				document.getElementById(rowRatingId+''+i).src= star2.src;
   				
   			}
   		}
	}
	function highlightHotel(x,hiddenRatingId,rowRatingId) {
   		
   		if (set==false) {
   			for (i=1;i<=5;i++){
   				document.getElementById(rowRatingId+''+i).src= star1.src;
   			}
   			for (i=1;i<=x;i++){
   				document.getElementById('paraFrm_Hotelvote').innerHTML=i+" star";
   				document.getElementById(rowRatingId+''+i).src= star2.src;
   				
   			}
   		}
	}
		
	function losehighlight(rowRatingId,id) {

		var hiddenValue=document.getElementById('paraFrm_deskRatingItt'+id).value;
   		for (i=1;i<=5;i++){
   				document.getElementById(rowRatingId+''+i).src= star1.src;
   			}
   		
   		for (i=1;i<=hiddenValue;i++){
   				document.getElementById(rowRatingId+''+i).src= star2.src;
   			}
   			document.getElementById('paraFrm_vote').innerHTML="";
   }
   
   	function loseHotelHighlight(rowRatingId,id) {
		var hiddenHotelValue=document.getElementById('paraFrm_hotelRatingItt'+id).value;
   	
   		for (i=1;i<=5;i++){
   				document.getElementById(rowRatingId+''+i).src= star1.src;
   			}
   		for (i=1;i<=hiddenHotelValue;i++){
   				document.getElementById(rowRatingId+''+i).src= star2.src;
   			}
   			document.getElementById('paraFrm_Hotelvote').innerHTML="";
   }
   
	function setStar(x,id, rating) {
		y=x*1+1
		document.getElementById(rating).value=x;
		document.getElementById('paraFrm_vote').innerHTML="Thank you for your vote!"
   		
	}
	function setHotelStar(x,id, rating) {
		y=x*1+1
		document.getElementById(rating).value=x;
		document.getElementById('paraFrm_Hotelvote').innerHTML="Thank you for your vote!"
   		
	}
	
	var blockId=1;
	function hideDisplayBlock(tableId){
		blockId++;
		if(blockId % 2 == 0){
			document.getElementById(tableId).style.display="";
		}else{
			document.getElementById(tableId).style.display="none";
		}
	}
	
	function callAddTourReports(){
		try{
			  var tbl = document.getElementById('followUpTable');
			  var lastRow = tbl.rows.length;
			  var iteration = lastRow-1;
			  var row = tbl.insertRow(lastRow);

	   		  var cell0 = row.insertCell(0);
			  var column0 = document.createElement('input');
	  		  column0.type = 'text';
			  column0.name = 'followUpCommentsItt';
			  column0.id = 'paraFrm_followUpCommentsItt'+iteration;
			  column0.size='30';
			  column0.maxLength='150';
			  cell0.className='sortableTD';
			  cell0.align='center';
			  cell0.appendChild(column0);
			  
	   		  var cell1 = row.insertCell(1);
			  var column1 = document.createElement('input');
	  		  column1.type = 'text';
			  column1.name = 'responsibleEmpItt';
			  column1.id = 'paraFrm_responsibleEmpItt'+iteration;
			  column1.readOnly = 'true';
			  column1.size='30';
			  column1.maxLength='30';
			  cell1.align='center';
			  cell1.className='sortableTD';
			  cell1.appendChild(column1);
			  
			  var cell2 = row.insertCell(2);
			  var column2 = document.createElement('img');
			  cell2.className='sortableTD';
			  column2.type='image';
			  column2.src="../pages/images/recruitment/search2.gif";
			  column2.align='absmiddle';
			  column2.id='img'+ iteration;
			  column2.theme='simple';
			  column2.onclick=function(){
			  try {
			  
			  document.getElementById('paraFrm_rowId').value=iteration;
			   		document.getElementById('paraFrm_rowId').value=iteration;
	        		javascript:callsF9(500,325,'TravelClaim_f9ResponsiblePerson.action');
			   		//setFieldId(event,iteration,'TravelClaim_f9ResponsiblePerson.action','paraFrm_responsibleEmpItt'+iteration);
			  }catch(e){alert(e);}
			  };
			  cell2.align='center';
			  cell2.appendChild(column2);

			  var cell3 = row.insertCell(3);
			  var column3 = document.createElement('input');
			  cell3.className='sortableTD';
			  column3.type = 'text';
	  		  column3.name = 'targetDateItt';
	  		  column3.onkeypress=function(){
			   return numbersWithHiphen();
			  };
			  column3.id = 'paraFrm_targetDateItt'+iteration;
			  column3.size ='10';
			  column3.maxLength ='10';
			  cell3.align='center';
			  cell3.appendChild(column3);
				  
			  var cell4 = row.insertCell(4);
			  var column4 = document.createElement('img');
			  cell4.className='sortableTD';
			  column4.type='image';
			  column4.src="../pages/images/recruitment/Date.gif";
			  column4.align='absmiddle';
			  column4.id='img'+ iteration;
			  column4.theme='simple';
			  column4.onclick=function(){
			  try {
					NewCal('paraFrm_targetDateItt'+iteration,'DDMMYYYY');
			  }catch(e){alert(e);}
			  };
			  cell4.align='center';
			  cell4.appendChild(column4);
			  
			  var cell5= row.insertCell(5);
			  var column5 = document.createElement('img');
			  cell5.className='sortableTD';
			  column5.type='image';
			  column5.src="../pages/common/css/icons/delete.gif";
			  column5.align='absmiddle';
			  column5.id='img'+ iteration;
			  column5.theme='simple';
			  cell5.align='center';
			
			  column5.onclick=function(){
			  try {
			  	deleteFollowUpAction(this);
			  }catch(e){alert(e);}
			  };
			  cell5.appendChild(column5);
			  
			  var column6 = document.createElement('input');
			  column6.type = 'hidden';
	  		  column6.name = 'responsibleEmpIdItt';
			  column6.id = 'paraFrm_responsibleEmpIdItt'+iteration;
			  column6.maxLength ='10';
			  cell3.appendChild(column6);
			  
			  var column7 = document.createElement('input');
			  column7.type = 'hidden';
	  		  column7.name = 'responsibleEmpTokenItt';
			  column7.id = 'paraFrm_responsibleEmpTokenItt'+iteration;
			  column7.maxLength ='10';
			  cell2.appendChild(column7);
				  
			}catch(e){
		}
	}
	
	function  deleteFollowUpAction(obj){
	 	
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		
	}
	
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
	
	function validateTourDetails(){
		var followUpTableRows=document.getElementById("followUpTable").rows.length-1;
		
		if(document.getElementById('paraFrm_tourComments').value==""){
			alert("Please enter tour report & achievement comments");
			return false;
		}
		if( followUpTableRows < 1){
		  		alert("Please add follow-up details");
		  		return false;
		  	}
		  	
		for( var i=0;i<followUpTableRows;i++){
			
				if(document.getElementById('paraFrm_followUpCommentsItt'+i).value==""){
 					alert("Please enter follow-up comments");
 					document.getElementById('paraFrm_followUpCommentsItt'+i).focus();
 					return false;
 				}
				if(document.getElementById('paraFrm_responsibleEmpItt'+i).value==""){
 					alert("Please select responsible employee");
 					document.getElementById('paraFrm_responsibleEmpItt'+i).focus();
 					return false;
 				}
 				if(document.getElementById('paraFrm_targetDateItt'+i).value==""){
 					alert("Please enter target Date");
 					document.getElementById('paraFrm_targetDateItt'+i).focus();
 					return false;
 				}
 				if(!validateDateFormat('paraFrm_targetDateItt'+i,'Target Date')){
 					document.getElementById('paraFrm_targetDateItt').focus();
 					return false;
 				}
		}
		return true; 
	}
	
	 	
 	function validateDateFormat(fieldName, labName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
		return true;
	}
	
	function setRowIdValue(rowNum){
		document.getElementById('paraFrm_rowId').value=rowNum;
		javascript:callsF9(500,325,'TravelClaim_f9ResponsiblePerson.action');
	}
	
function validateCurrency() {
	try {
		 var count = '<%=total%>';
		 var iterator = eval(count-1); 
		 var advanceAmtTaken = document.getElementById('paraFrm_advanceAmtTaken').value;
		 var currencyEmployeeAdvance = document.getElementById('paraFrm_currencyEmployeeAdvance').value;
		 var expenseCurrency = document.getElementById('paraFrm_currencyExpenseAmt').value;
		 if(eval(iterator)==0) {
		 	if (eval(advanceAmtTaken)>0) {
		 		if(currencyEmployeeAdvance != expenseCurrency) {
		 			alert("Currency of advance amount  and expense amount should same.");
		 			return false;
		 		} 
		 	}
		 } else {
		 	/*for(var i=1; i<=iterator; i++){
		 		var addedCurrency = document.getElementById('currencyExpenseAmtItr'+i).value;
		 		if(expenseCurrency != addedCurrency) {
		 			alert("Please claim your expenses in one type of currency only.");
		 			return false;	
		 		} 
		 	}*/
		 }
		 
		 return true;
	} catch(e) {
		alert("Validating Currency >>"+e);
	}
}	
</script>
