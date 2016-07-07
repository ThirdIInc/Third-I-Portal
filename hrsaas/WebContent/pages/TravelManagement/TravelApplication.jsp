
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript"> 
	var eadd = new Array();
	var schArr = new Array();
	var hotelArr = new Array();
	var expenArr = new Array();  
</script>
<s:form action="TravelAppMngt" validate="true" id="paraFrm"
	theme="simple">


	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">





		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Application</strong></td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td><s:if test="travelAppFlag">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><s:if test="insertFlag">
							<s:submit cssClass="add" action="TravelAppMngt_save"
								theme="simple" value="   Add New " onclick="return callSave();" />
						</s:if> <s:if test="%{updateFlag}">
							<s:submit cssClass="edit" action="TravelAppMngt_save"
								theme="simple" value="   Update " onclick="return callUpdate();" />
						</s:if> <s:if test="%{viewFlag}">
							<input type="button" class="search"
								onclick="javascript:callsF9(500,325,'TravelAppMngt_f9action.action'); "
								value="    Search " />
						</s:if> <s:submit cssClass="reset" action="TravelAppMngt_reset"
							theme="simple" value="    Reset" /> <s:if test="deleteFlag">
							<s:submit cssClass="delete" action="TravelAppMngt_delete"
								theme="simple" value="   Delete"
								onclick="return callDelete('paraFrm_trApp_travelAppId');" />
						</s:if> <s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="callReport();"
								value="  Report " />
						</s:if></td>
						<td align="right"><font color="red">*</font>Indicates
						Required</td>
					</tr>
				</table>
			</s:if></td>
		</tr>

		<tr>
			<td>

			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<tr>
					<s:hidden name="trApp.travelAppId" />
					<s:hidden name="checkEdit" />
					<s:hidden name="trApp.empId" />
					<s:hidden name="trApp.fromPlaceId" />
					<s:hidden name="trApp.toPlaceId" />
					<s:hidden name="trApp.journeyClassId" />
					<s:hidden name="trApp.journeyId" />
					<s:hidden name="serialNo" />
					<s:hidden name="travelAppFlag" />
					<s:hidden name="statusSch" />
					<s:hidden name="trHidSchStatus" />
					<s:hidden name="schSaveFlag" />
					<s:hidden name="hidStatus" />
					<s:hidden name="scheduleFinalFlag" />
					<s:hidden name="travelReportId" />
					<s:hidden name="gradeJourneyId" />
					<s:hidden name="gradeClassId" />
					<s:hidden name="travelOtherCost" />
					<s:hidden name="hotelPerDayCost" />
					<s:hidden name="hotelOtherCost" />
					<s:hidden name="appDispFlag" />
					<s:hidden name="cssClassTYpe" />
					<s:if test="%{generalFlag}">
						<td width="25%" colspan="1">Employee Name<font color="red">*</font>:</td>
						<td width="75%" colspan="3"><s:textfield
							name="trApp.empToken" size="20" readonly="true" /> <s:textfield
							size="55" name="trApp.empName" readonly="true" /></td>
					</s:if>
					<s:else>
						<td width="25%" colspan="1">Select Employee<font color="red">*</font>:</td>
						<td width="75%" colspan="3"><s:textfield
							name="trApp.empToken" size="20" readonly="true" /> <s:textfield
							size="55" name="trApp.empName" readonly="true" /> <s:if
							test="travelAppFlag">
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TravelAppMngt_f9Employee.action');">
						</s:if></td>
					</s:else>
				</tr>
				<tr>
					<td width="25%" colspan="1">Branch :</td>
					<td width="35%" colspan="1"><s:textfield size="30"
						name="trApp.branchName" readonly="true" /></td>
					<td width="20%" colspan="1">Department:</td>
					<td width="20%" colspan="1"><s:textfield size="30"
						maxlength="40" name="trApp.dept" readonly="true" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1">Designation:</td>
					<td width="35%" colspan="1"><s:textfield size="30"
						name="trApp.desg" readonly="true" /></td>
					<td width="20%" colspan="1">Application Date<font color="red">*</font>:</td>
					<td width="20%" colspan="1"><s:textfield size="12"
						name="trApp.appDate" maxlength="10"
						onblur="return validateDate('paraFrm_trApp_appDate','Date');"
						onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_trApp_appDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a></td>
				</tr>
				<tr>
					<td width="25%" colspan="1">Status :</td>
					<td width="35%" colspan="1"><s:select theme="simple"
						name="status" value="%{status}"
						list="#{'P':'Pending','A':'Approved','R':'Rejected','D':'Schedule','C':'Confirm','N':'Cancel In Process','K':'Cancel'}"
						disabled="true"></s:select></td>
				</tr>
				<tr>
					<td width="25%" colspan="1">Age :</td>
					<td width="35%" colspan="1"><s:textfield size="15"
						name="trApp.empAge" readonly="true" /></td>
					<td width="20%" colspan="1">Contact Number:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="trApp.contactNo" readonly="true" /></td>
				</tr>

				<s:if test="travelAppFlag">

					<tr>
						<td width="25%" colspan="1">ID Proof<font color="red">*</font>
						:</td>
						<td width="35%" colspan="1"><s:select name="trApp.idProof"
							headerKey="1" headerValue="Select"
							list="#{'V':'Voter Identity Card ','P':'Passport','I':' PAN Card','D':'Driving Licence ',
										'G':'Photo identity cards issued by Central/State Government'}"
							theme="simple" /></td>
						<td width="20%" colspan="1">ID Number</td>
						<td width="20%" colspan="1"><s:textfield size="25"
							name="trApp.idNumber" /></td>
					</tr>

					<tr>
						<td width="25%" colspan="1">Comments :</td>
						<td colspan="3"><s:textarea name="applicantComment"
							cols="100" rows="2" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="25%" colspan="1">ID Proof :</td>
						<td width="35%" colspan="1"><s:textfield name="trApp.idProof"
							size="35" readonly="true" /></td>
						<td width="20%" colspan="1">ID Number</td>
						<td width="20%" colspan="1"><s:textfield size="25"
							name="trApp.idNumber" readonly="true" /></td>
					</tr>

					<tr>
						<td width="20%" colspan="1">Comments :</td>
						<td colspan="3"><s:textarea name="applicantComment"
							cols="100" rows="2" readonly="true" /></td>
					</tr>
				</s:else>
			</table>



			</td>
		</tr>


		<tr>
			<td>


			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<tr>
					<td width="17%" colspan="1">Grade :</td>
					<td width="85%" colspan="3"><s:hidden name="trApp.gradeCode" />
					&nbsp;&nbsp;<s:textfield size="25" name="trApp.gradeName"
						readonly="true" /></td>
				</tr>

				<tr>
					<td width="17%" colspan="1">Travel Entitlement</td>
					<td colspan="3"><s:property value="appModeName" /></td>
				</tr>
				<tr>
					<td><img src="../pages/images/recruitment/space.gif" width="5"
						height="2" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1">Other Entitlement</td>
					<td colspan="1" width="40%"><s:property value="trOtherExp" />
					</td>
					<td colspan="2"><s:property value="lodgOtherAllow" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1">&nbsp;</td>
					<td colspan="1" width="40%"><s:property
						value="lodgAllowPerDay" /></td>
					<td colspan="2"><s:property value="outPocketAllow" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1">&nbsp;</td>
					<td colspan="3"><s:property value="foodAllow" /></td>
				</tr>
			</table>

			</td>
		</tr>








		<tr>
			<td>

			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<s:if test="travelAppFlag">
					<tr>
						<td colspan="5"><strong class="formhead">Journey
						Details</strong></td>
					</tr>
					<tr>
						<td width="15%" colspan="1">From Place<font color="red">*</font>:</td>
						<td width="25%" colspan="1"><s:textfield size="25"
							name="trApp.fromPlace" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TravelAppMngt_f9FromPlace.action');">
						</td>
						<td width="15%" colspan="1">To Place<font color="red">*</font>:</td>
						<td width="18%" colspan="1"><s:textfield size="25"
							name="trApp.toPlace" readonly="true" /></td>
						<td width="10%" colspan="1"><img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TravelAppMngt_f9ToPlace.action');">
						</td>
					</tr>
					<tr>
						<td width="15%" colspan="1">Journey Mode<font color="red">*</font>:</td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="trApp.journeyName" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="return callJourneyModeF9Popup();"></td>
						<td width="15%" colspan="1">Class<font color="red">*</font>:</td>
						<td width="18%" colspan="1"><s:textfield size="25"
							name="trApp.journeyClassName" readonly="true" /></td>
						<td width="10%" colspan="1"><img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="return callClass();"></td>
					</tr>

					<tr>
						<td width="15%" colspan="1">Bus/Rail/Air Number:</td>
						<td width="30%" colspan="1"><s:textfield size="25"
							name="trApp.journeyModeRefNumber" /></td>
					</tr>
					<tr>
						<td width="15%" colspan="1">Journey Start Date
						&nbsp;&nbsp;(DD-MM-YYYY) <font color="red">*</font>:</td>
						<td width="30%" colspan="1"><s:textfield size="12"
							name="trApp.journeyDate" maxlength="10"
							onblur="return validateDate('paraFrm_trApp_journeyDate','Date');"
							onkeypress="return numbersWithHiphen();" /> <s:a
							href="javascript:NewCal('paraFrm_trApp_journeyDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18">
						</s:a></td>
						<td width="15%" colspan="1">Journey Start Time(HH24:MI) :</td>
						<td width="18%" colspan="1"><s:textfield size="12"
							maxlength="4" name="trApp.journeyTime"
							onblur="return validateTime('paraFrm_trApp_journeyTime','Journey Time');"
							maxlength="5" onkeypress="return numbersWithColon();" /></td>
					</tr>
					<tr>
						<td width="45%" align="right" colspan="2"><s:submit
							cssClass="add" action="TravelAppMngt_addIterator"
							value="   Add   " onclick="return callAdd();" />&nbsp;</td>
						<td width="55%" align="left" colspan="5"></td>
					</tr>
				</s:if>
			</table>


			</td>
		</tr>




		<tr>
			<td><s:if test="travelAppFlag">
				<s:if test="noDataFlag">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr>
							<td class="formth" width="05%">Sr No</td>
							<td class="formth" width="15%">From Place</td>
							<td class="formth" width="15%">To Place</td>
							<td class="formth" width="10%">Journey Mode</td>
							<td class="formth" width="12%">Bus/Rail/Air Number:</td>
							<td class="formth" width="10%">Class</td>
							<td class="formth" width="10%">Journey Date</td>
							<td class="formth" width="5%">Time</td>
							<td class="formth" width="15%">Edit/Delete</td>
						</tr>
						<%
						int srNo = 0;
						%>
						<s:iterator value="journeyList">

							<tr>
								<td class="border2"><%=++srNo%></td>
								<td class="border2"><s:property value="itFromPlaceName" /></td>
								<td class="border2"><s:property value="itToPlaceName" /></td>
								<td class="border2"><s:property value="itJourneyName" /></td>
								<td class="border2"><s:property
									value="itJourneyModeRefNumber" /></td>
								<td class="border2"><s:property value="itJourneyClassName" /></td>
								<td class="border2"><s:property value="itJourneyDate" /></td>
								<td class="border2"><s:property value="itJourneyTime" /> <s:hidden
									name="itFromPlaceName" /><s:hidden name="itToPlaceName" /><s:hidden
									name="itJourneyName" /> <s:hidden name="itJourneyClassName" />
								<s:hidden name="itFromPlaceId" /><s:hidden name="itToPlaceId" /><s:hidden
									name="itJourneyId" /> <s:hidden name="itJourneyClassId" /> <s:hidden
									name="itJourneyDate" /><s:hidden name="itJourneyTime" /> <s:hidden
									name="itJourneyModeRefNumber" /></td>
								<td class="border2"><input type="button" class="token"
									value="Edit"
									onclick="return setEdit('<%=srNo-1%>','<s:property value="itFromPlaceName" />','<s:property value="itToPlaceName" />',
												'<s:property value="itJourneyName" />','<s:property value="itJourneyClassName" />','<s:property value="itJourneyDate" />',
												'<s:property value="itJourneyTime" />',
												'<s:property value="itFromPlaceId" />','<s:property value="itToPlaceId" />','<s:property value="itJourneyId" />',
												'<s:property value="itJourneyClassId" />','<s:property value="itJourneyModeRefNumber"/>');" />
								<input type="button" class="token" value="Delete"
									onclick="return setSerialNo(<%=(srNo-1)%>);" /> <script>
													eadd[<%=srNo%>] = document.getElementById('paraFrm_itFromPlaceName').value;
												 </script></td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
			</s:if></td>
		</tr>




		<tr>
			<td><s:else>
				<s:hidden name="schJournyId" id="schJournyId" />
				<s:hidden name="alertSchMode" id="alertSchMode" />
				<s:hidden name="alertSchFlag" id="alertSchFlag" />
				<s:if test="noDataFlag">
					<div style="overflow-x: auto; width: 770;">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr height="30">
							<td class="headercell" colspan="4"><strong class="formth">Applicant
							Journey Details </strong></td>
							<td class="headercell" colspan="9"><strong class="formth">Approver
							Journey Details </strong></td>
						</tr>
						<tr>
							<td class="formth" nowrap="nowrap">Sr No</td>
							<td class="formth" nowrap="nowrap">From Place-To Place</td>
							<td class="formth" nowrap="nowrap">Journey Mode-Class</td>
							<td class="formth" nowrap="nowrap">Journey Date-Time</td>
							<td class="formth" nowrap="nowrap">Journey Mode</td>
							<td class="formth" nowrap="nowrap">Journey Class</td>
							<td class="formth" width="6%">Bus/Rail/Air Number</td>
							<td class="formth" width="6%">Ticket Number</td>
							<td class="formth">Journey Date(DD-MM-YYYY)</td>
							<td class="formth">Time(HH24:MI)</td>
							<td class="formth" width="5%">Cost(Rs.)</td>
							<td class="formth" nowrap="nowrap">Other Amt.</td>
							<td class="formth" width="5%">Comments</td>
						</tr>
						<%
						int cnt = 0;
						%>
						<s:iterator value="schJourneyList">
							<tr>
								<td class="border2"><%=++cnt%> <s:hidden
									name="itFromPlaceName" /><s:hidden name="itToPlaceName" /> <s:hidden
									name="itJourneyClassName" /><s:hidden name="itJourneyName" />
								<s:hidden name="itFromPlaceId" /><s:hidden name="itToPlaceId" /><s:hidden
									name="itJourneyId" /> <s:hidden name="itJourneyClassId" /><s:hidden
									name="itJourneyDate" /> <s:hidden name="itJourneyTime" /> <s:hidden
									name="schViewFlag" /></td>
								<td class="border2" nowrap="nowrap"><s:property
									value="itFromPlaceName" />-<s:property value="itToPlaceName" /></td>
								<td class="border2" nowrap="nowrap"><s:property
									value="itJourneyName" />-<s:property
									value="itJourneyClassName" /></td>
								<td class="border2" nowrap="nowrap"><s:property
									value="itJourneyDate" />-<s:property value="itJourneyTime" /></td>
								<td class="border2" nowrap="nowrap"><s:if
									test="schViewFlag">

									<%
												String jourMode = (String) request.getAttribute("schJourneyMode"
												+ cnt);
										String jourModeId = (String) request
												.getAttribute("schJourneyModeId" + cnt);
									%>

									<s:textfield name="<%="schJourneyMode"+cnt %>"
										value="<%=jourMode%>" readonly="true" />
									<s:hidden name="<%="schJourneyModeId"+cnt%>"
										value="<%=jourModeId%>" />
									<img src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callJourneyMode('<s:property value="<%=""+cnt%>"/>')">
								</s:if> <s:else>
									<s:textfield name="<%="schJourneyMode"+cnt %>" size="8"
										readonly="true" />
									<s:hidden name="<%="schJourneyModeId"+cnt%>" />
									<img src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callJourneyMode('<s:property value="<%=""+cnt%>"/>')">
								</s:else></td>
								<td class="border2" nowrap="nowrap"><s:if
									test="schViewFlag">
									<%
												String jourClass = (String) request.getAttribute("schJourneyClass"
												+ cnt);
										String jourClassId = (String) request
												.getAttribute("schJourneyClassId" + cnt);
									%>
									<s:textfield name="<%="schJourneyClass"+cnt %>"
										value="<%=jourClass%>" readonly="true" />
									<s:hidden name="<%="schJourneyClassId"+cnt%>"
										value="<%=jourClassId%>" />
									<img src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callJourneyClass('<s:property value="<%=""+cnt%>"/>')">
								</s:if> <s:else>
									<s:textfield name="<%="schJourneyClass"+cnt %>" size="8"
										readonly="true" />
									<s:hidden name="<%="schJourneyClassId"+cnt%>" />
									<img src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callJourneyClass('<s:property value="<%=""+cnt%>"/>')">
								</s:else></td>
								<td class="border2" width="8"><input type="text"
									name="schModeNumber" id="schModeNumber<%=cnt%>"
									value="<s:property value="schModeNumber"/>" size="8"></td>
								<td class="border2" width="8"><input type="text"
									name="schTicketNumber" id="schTicketNumber<%=cnt%>" size="8"
									value="<s:property value="schTicketNumber"/>"></td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="schJourneyDate" id="schJourneyDate<%=cnt%>"
									value="<s:property value="schJourneyDate"/>" size="7"
									onblur="return validateDate('schJourneyDate<%=cnt%>','Journey  Date');"
									onkeypress="return numbersWithHiphen();" maxlength="10">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('<%="schJourneyDate"+cnt%>','DDMMYYYY');">
								</td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="schJourneyTime" id="schJourneyTime<%=cnt%>" size="3"
									value="<s:property value="schJourneyTime"/>"
									onblur="return validateTime('schJourneyTime<%=cnt%>','Time(HH24:MI)');"
									maxlength="5" onkeypress="return numbersWithColon();"></td>
								<td class="border2" width="5%"><input type="text"
									style="text-align: right" name="schJourneyCost"
									id="schJourneyCost<%=cnt%>"
									value="<s:property value="schJourneyCost"/>" size="6"
									onkeyup="additionForSchedule();"
									onkeypress="return numbersWithDot();"></td>
								<td class="border2"><input type="text"
									style="text-align: right" name="schExtraAmt"
									id="schExtraAmt<%=cnt%>"
									value="<s:property value="schExtraAmt" />" size="6"
									onkeyup="additionForSchedule();"
									onkeypress="return numbersWithDot();"></td>
								<td class="border2" width="5%"><s:textarea
									name="schJourneyComment" rows="2" cols="40" /></td>
								<script>
												 	schArr[<%=cnt%>] = document.getElementById('paraFrm_schJourneyComment').value;
											</script>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="9">&nbsp;</td>
							<td colspan="2" align="right">Total Journey Cost :</td>
							<td><input type="text" style="text-align: right"
								name="totalJCost" id="totalJCost"
								value="<s:property value="totalJCost"/>" size="6"
								readonly="true"></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="13">&nbsp;</td>

						</tr>

					</table>
					</div>

					<div style="overflow-x: auto; width: 770;">
					<table width="99%">
						<tr height="30">

							<td class="headercell" colspan="2"><s:submit
								cssClass="token" theme="simple" action="TravelAppMngt_addHotel"
								value="Add Hotel" /></td>
							<td class="headercell" colspan="8"><strong class="formhead">Staying
							Details </strong></td>

						</tr>
						<tr>
							<td class="formth" nowrap="nowrap">Sr No</td>
							<td class="formth" nowrap="nowrap">Hotel Name</td>
							<td class="formth" nowrap="nowrap">Address</td>
							<td class="formth" nowrap="nowrap">Booking From Date</td>
							<td class="formth">From Time(HH24:MI)</td>
							<td class="formth" nowrap="nowrap">To Date</td>
							<td class="formth">To Time(HH24:MI)</td>
							<td class="formth" nowrap="nowrap">Bill Amt.</td>
							<td class="formth" nowrap="nowrap">Other Amt.</td>
							<td class="formth" nowrap="nowrap">Comments</td>
						</tr>
						<%
						int k = 0;
						%>
						<s:iterator value="hotelList">
							<tr>
								<td class="border2" width="2%"><%=++k%></td>
								<td class="border2" nowrap="nowrap"><s:textfield
									name="hotelName" size="10" /></td>
								<td class="border2" width="8"><s:textarea
									name="hotelAddress" rows="2" cols="35" /></td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="chkInDate" id="chkInDate<%=k%>"
									value="<s:property value="chkInDate"/>" size="10"
									onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('chkInDate<%=k%>','Staying From Date');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('<%="chkInDate"+k%>','DDMMYYYY');">
								</td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="chkInTime" id="chkInTime<%=k%>"
									value="<s:property value="chkInTime"/>" size="6"
									onkeypress="return numbersWithColon();" maxlength="6"
									onblur="return validateTime('chkInTime<%=k%>','Staying From Time');">
								</td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="chkOutDate" id="chkOutDate<%=k%>"
									value="<s:property value="chkOutDate"/>" size="10"
									onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('chkOutDate<%=k%>','Staying To Date');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('<%="chkOutDate"+k%>','DDMMYYYY');">
								<td class="border2" nowrap="nowrap"><input type="text"
									name="chkOutTime" id="chkOutTime<%=k%>"
									value="<s:property value="chkOutTime"/>" size="4"
									onkeypress="return numbersWithColon();" maxlength="6"
									onblur="return validateTime('chkOutTime<%=k%>','Staying To Time');">
								</td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="hotelBill" id="hotelBill<%=k%>"
									value="<s:property value="hotelBill"/>" size="10"
									onkeyup="additionForHotel();"
									onkeypress="return numbersWithDot();"></td>
								<td class="border2" nowrap="nowrap"><input type="text"
									name="extraHotelBill" id="extraHotelBill<%=k%>"
									value="<s:property value="extraHotelBill"/>" size="10"
									onkeyup="additionForHotel();"
									onkeypress="return numbersWithDot();"></td>
								<td class="border2" width="8"><s:textarea
									name="hotelComment" rows="2" cols="35" /></td>
								<script>
													hotelArr[<%=k%>] = document.getElementById('paraFrm_hotelComment').value;
												</script>
							</tr>
						</s:iterator>

						<tr>
							<td colspan="7">&nbsp;</td>
							<td nowrap="nowrap">Total Bill</td>
							<td nowrap="nowrap"><s:textfield name="hotelTotalBill"
								id="hotelTotalBill" size="10" readonly="true" /></td>
						</tr>
						<tr>
							<td colspan="9">&nbsp;</td>
						</tr>

					</table>
					</div>
					<table width="99%">
						<tr>
							<td align="center" colspan="1" width="15%">Total Amount :</td>
							<td align="center" colspan="1" width="10%"><s:textfield
								name="totalTravelCost" id="totalTravelCost" size="10"
								readonly="true" /></td>
							<td align="center" colspan="9">&nbsp;</td>
						</tr>
						<tr>
							<td align="center" colspan="11"><s:submit
								action="TravelAppMngt_travelScheduleSave" cssClass="token"
								theme="simple" value="   Save "
								onclick="return callScheduleSave('false');" /> <s:submit
								action="TravelAppMngt_travelScheduleSave" cssClass="token"
								theme="simple" value=" Finalize Schedule"
								onclick="return callScheduleSave('true');" /> <input
								type="button" class="token" onclick="return callReportMis();"
								value="    Show Report" /> <input type="button" Class="token"
								theme="simple" value="   Back " onclick="callBack();" /></td>
						</tr>
					</table>
				</s:if>
			</s:else></td>
		</tr>

	</table>



</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>


function callClass()
{
 var journeyId = document.getElementById('paraFrm_trApp_journeyId').value;
	if(journeyId=="")
	{
	  alert("Please select Journey Mode !");
	  return false;
	}else
	{
	  callsF9(500,325,'TravelAppMngt_f9JourneyClass.action');
	  return true;
	}
	 return true;
}

function callJourneyModeF9Popup()
 { 	 
  
  document.getElementById('paraFrm_trApp_journeyId').value ="";  
  document.getElementById('paraFrm_trApp_journeyClassName').value ="";  
  callsF9(500,325,'TravelAppMngt_f9Journey.action'); 
	  return true; 
}

   function additionForSchedule()
    {
         var JCost =0;
         var JextraCost =0;
         var JTotal =0;
       for(var i=1; i<schArr.length;i++)
       {
        var tempCost =document.getElementById('schJourneyCost'+i).value; 
        if(tempCost=="")
        tempCost=0;
        var tempExCost =document.getElementById('schExtraAmt'+i).value;  
        if(tempExCost=="")
           tempExCost=0;
        JCost= Math.round(JCost) + Math.round(tempCost); 
        JextraCost =Math.round(JextraCost)+ Math.round(tempExCost); 
       }  
       JTotal =Math.round(JCost)+Math.round(JextraCost); 
       if(JTotal =="")
       JTotal="0";
       document.getElementById('totalJCost').value =JTotal; 
       var hTotal = document.getElementById('hotelTotalBill').value;
       if(hTotal=="")
       {
       hTotal =0;
       }
        document.getElementById('totalTravelCost').value =Math.round(JTotal)+Math.round(hTotal);
    }
    
    
     function additionForHotel()
    {    var JTotal =  document.getElementById('totalJCost').value;  
         var HCost =0;
         var HextraCost =0; 
		       for(var i=1; i<hotelArr.length;i++)
		       {
		        var tempCost =document.getElementById('hotelBill'+i).value;  
		        if(tempCost=="")
		        tempCost=0;
		        var tempExCost =document.getElementById('extraHotelBill'+i).value;  
		        if(tempExCost=="")
		           tempExCost=0;
		        HCost= Math.round(HCost) + Math.round(tempCost); 
		        HextraCost =Math.round(HextraCost)+ Math.round(tempExCost); 
		       }  
          HTotal =Math.round(HCost)+Math.round(HextraCost); 
          if(hTotal="")
          hTotal=0;
          document.getElementById('hotelTotalBill').value = HTotal; 
          document.getElementById('totalTravelCost').value = Math.round(JTotal)+Math.round(HTotal);
       
    }
    
 function setSerialNo(code){
 
 if(!(document.getElementById('paraFrm_status').value=="P")){
	alert("You can't update the application !");
	return false;
	}
    var conf=confirm("Do you really want to delete this record ?");
  	 		if(conf) 
  	 	{
  		 
  			  document.getElementById('paraFrm_serialNo').value=code; 
  			 
	        document.getElementById('paraFrm').action='TravelAppMngt_delItem.action';
	        
	        document.getElementById('paraFrm').submit();	
	         
  			 	return true;
  			 }
	  		 else
	  		 {
	  		 	 return false;
	  		 }
 
      
	return true;
}

function callAdd()
{
      empName =  document.getElementById("paraFrm_trApp_empName").value;
  	  fromPlace =  document.getElementById("paraFrm_trApp_fromPlace").value;
	  toPlace = document.getElementById("paraFrm_trApp_toPlace").value;
	  journeyName = document.getElementById("paraFrm_trApp_journeyName").value;
	  journeyClassName = document.getElementById("paraFrm_trApp_journeyClassName").value;
	  journeyDate = document.getElementById("paraFrm_trApp_journeyDate").value; 
	
	   gradeJourneyId = document.getElementById("paraFrm_gradeJourneyId").value;
	   gradeClassId = document.getElementById("paraFrm_gradeClassId").value;  
	   
	   journeyId = document.getElementById("paraFrm_trApp_journeyId").value;  
	   journeyClassId = document.getElementById("paraFrm_trApp_journeyClassId").value;
		  if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 if(fromPlace =="")
			 {
			   alert("Please select From Place !"); 
			   return false;
			 }
			  if(toPlace =="")
			 {
			   alert("Please select To Place !"); 
			   return false;
			 }
			  if(!validateDate('paraFrm_trApp_journeyDate','Journey Date'))
  		      {
			       return false;
		           }
		 
			   if(journeyName =="")
			 {
			   alert("Please select Journey Mode !"); 
			   return false;
			 }
			 
			if(journeyClassName =="")
			 {
			   alert("Please select Journey Class!"); 
			   return false;
			 }  
			 if(journeyDate =="")
			 {
			   alert("Please enter Journey Date!"); 
			   return false;
			 } 
	var appDispFlag =document.getElementById("paraFrm_appDispFlag").value;  
			   
		 if(appDispFlag=="false")
		 {	   
	   var flag="";
	   var contiflag="";  
		var jid="*"+journeyId+"*";
		var cid="*"+journeyClassId+"*";  
	     if(gradeJourneyId.indexOf(jid)==-1)
		    {      
               var conf = confirm("You are not entitle to travel by "+journeyName+" journey.Do you want to continue ? ");
			   if(conf==true)
			     { 
			        flag ="true"; 
			        contiflag="flag";
			       }else
			         { 
			          flag ="false"; 
			          return false; 
			        } 
			         
			  }else
			    { 
				 flag ="true"; 	  
			       } 
			       
		if(flag=="true" && contiflag=="")   
		 {   
		    if(gradeClassId.indexOf(cid)==-1)
		         {     
                var conf = confirm("You are not entitle to travel by "+journeyClassName+" journey .Do you want to continue ?");
               
			   if(conf==true)
			     {  
			       }else
			         {  
			          return false; 
			        }  
			      } 
			   }   
			  }
       return true;
}
 

function setEdit(id,itFromPlaceName,itToPlaceName,itJourneyName,itJourneyClassName,itJourneyDate,
itJourneyTime,itFromPlaceId,itToPlaceId,itJourneyId,itJourneyClassId,itJourneyModeRefNumber){
if(!(document.getElementById('paraFrm_status').value=="P")){
	alert("You can't update the application !");
	return false;
	}    
 
	     document.getElementById("paraFrm_checkEdit").value=id;	 
	  	document.getElementById("paraFrm_trApp_fromPlace").value=itFromPlaceName;
	  	document.getElementById("paraFrm_trApp_toPlace").value=itToPlaceName;
	  	document.getElementById("paraFrm_trApp_journeyName").value=itJourneyName;
	  	document.getElementById("paraFrm_trApp_journeyClassName").value=itJourneyClassName;
	  	document.getElementById("paraFrm_trApp_journeyDate").value=itJourneyDate;
	  	document.getElementById("paraFrm_trApp_journeyTime").value=itJourneyTime;
	  	document.getElementById("paraFrm_trApp_fromPlaceId").value=itFromPlaceId;
	  	document.getElementById("paraFrm_trApp_toPlaceId").value=itToPlaceId;
	  	document.getElementById("paraFrm_trApp_journeyId").value=itJourneyId;
	  	document.getElementById("paraFrm_trApp_journeyClassId").value=itJourneyClassId; 
	  	document.getElementById("paraFrm_trApp_journeyModeRefNumber").value=itJourneyModeRefNumber;
	  	 
	  	 
	  	//	document.getElementById("paraFrm").submit();
	  		
	 }
	 
	 function callTime()
	 {
	    var startTime = document.getElementById('paraFrm_comp_startTime').value;
       var endTime = document.getElementById('paraFrm_comp_endTime').value;
	    var start = startTime.split(":");
	    var end = endTime.split(":");
	    
	    var first = eval(start[0])* eval(start[1]) ;
	    var second = eval(end[0])* eval(end[1]) ; 
	 }
	 
	 
	 function callSave()
   { 
       travelAppId =  document.getElementById("paraFrm_trApp_travelAppId").value; 
       empName =  document.getElementById("paraFrm_trApp_empName").value; 
        
  	 
		     if(travelAppId!="")
			 {
			   alert("Please update record !");
			   return false;
			 } 
		  if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 
			 
			 if(eadd.length <=0)
			 {
			  alert("Please add atleast one record !");
	           return false; 
			 } 
		  
         return true;
}
	   function callUpdate()
	    { 
	         travelAppId =  document.getElementById("paraFrm_trApp_travelAppId").value; 
             empName =  document.getElementById("paraFrm_trApp_empName").value;
  	 
		      if(travelAppId=="")
			 {
			   alert("Please select record to update !");
			   return false;
			 } 
			 
		   if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 
			 if(eadd.length <=0)
			 {
			  alert("Please add atleast one record !");
	           return false; 
			 }  
	 return true;
	 }
	    
	 
	 function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			if(document.getElementById('paraFrm_trApp_appDate').value=="")
				document.getElementById("paraFrm_trApp_appDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
  }
autoDate();


 function callReport()
	 {
	 
	 var id = document.getElementById('paraFrm_trApp_travelAppId').value;
	 if(id=="")
	 {
	   alert(" Please select the record !");
	   return false;
	 }
	 else
	 {
	 document.getElementById('paraFrm').target ='_blank';	 
	 document.getElementById('paraFrm').action = "TravelAppMngt_report.action";
	 document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="";
	 }
	 }
    
    //scheduled javascript
    function callJourneyMode(id)
    {
    
      document.getElementById('schJournyId').value=id; 
     //  document.getElementById('paraFrm_schJourneyMode'+id).value =  document.getElementById('schViewJourneyMode'+id).value;
      callsF9(500,325,'TravelAppMngt_f9SchJourney.action'); 
      // var schJourneyMode = document.getElementById('paraFrm_schJourneyMode'+id).value
      /// alert(schJourneyMode);
      // document.getElementById('schViewJourneyMode'+id).value = schJourneyMode;
     
    }
    function callJourneyClass(id)
    {
      document.getElementById('schJournyId').value=id;
      sModeId = document.getElementById('paraFrm_schJourneyModeId'+id).value; 
     if(sModeId=="" ||sModeId=="0")
       {
       alert("Please select Journey Mode !");
     } else
      {
      callsF9(500,325,'TravelAppMngt_f9SchJourneyClass.action');
      }
    }
    
    function callHotel(id)
    {
      document.getElementById('schJournyId').value=id;
      callsF9(500,325,'TravelAppMngt_f9SchHotel.action');
    }
    
    function callBack()
    { 
        var statusFlag =document.getElementById('paraFrm_statusSch').value; 
        document.getElementById('paraFrm').action = action="TravelSchdule_callStatus.action?status="+statusFlag;
		document.getElementById('paraFrm').submit();
		 
    }
    
function callScheduleSave(finalFlag)
{   
var appDispFlag =  document.getElementById('paraFrm_appDispFlag').value;
if(finalFlag=="true")
{
document.getElementById('paraFrm_scheduleFinalFlag').value ="final";
}
 for(var i=1; i<hotelArr.length;i++)
	{ 
	    
		    if(!validateTime('chkInTime'+i,'Staying From Time'))
		    {
		     return false;
		    }    
		     if(!validateTime('chkOutTime'+i,'Staying To Time'))
		    {
		     return false;
		    }   
	 } 
	 
	  for(var i=1; i<schArr.length;i++)
	  { 
	    if(!validateTime('schJourneyTime'+i,'Time(HH24:MI)'))
		    {
		     return false;
		    } 
		    
		    if(!validateDate('schJourneyDate'+i,'Journey  Date'))
		    {
		     return false;
		    }  
	 } 
	 
	 // ========== for validate juorney mode==========
	 if(appDispFlag=="false")
	 {
	  
	   var  gradeJourneyId = document.getElementById("paraFrm_gradeJourneyId").value;
	   var gradeClassId = document.getElementById("paraFrm_gradeClassId").value;   
	   var travelOtherCost = document.getElementById("paraFrm_travelOtherCost").value;
	   var hotelPerDayCost = document.getElementById("paraFrm_hotelPerDayCost").value; 
	    var hotelOtherCost = document.getElementById("paraFrm_hotelOtherCost").value; 
	     var dispAlert =""; 
	      var travelFlag ="";
	       var hotelPerDayFlag ="";
	        var hotelOtherFlag =""; 
	     var alertSchFlag = document.getElementById("alertSchFlag").value;   
	     var alertSchMode = document.getElementById("alertSchMode").value;   
	    if(alertSchFlag=="true")
			  {
		       dispAlert +=  " The Applicant is not entitled to traveling by \n"+alertSchMode+" . \n  ";
			 }
	    
	  for(var i=1; i<schArr.length;i++)
	  {    
	    var trSchOtherAmt = document.getElementById("schExtraAmt"+i).value;    
			   
       if(eval(trSchOtherAmt)>eval(travelOtherCost))
	       {
	         travelFlag ="true"; 
	       }   
	 } // end of for 
	  
 for(var i=1; i<hotelArr.length;i++)
	 {  
	     var trSchHotelPerDayAmt = document.getElementById("hotelBill"+i).value;  
	   if(eval(trSchHotelPerDayAmt)>eval(hotelPerDayCost))
	    {
	      hotelPerDayFlag ="true";
	      break;
	      }  
	 }  
	 
 for(var i=1; i<hotelArr.length;i++)
	 { 
	  var trSchHotelOtherAmt = document.getElementById("extraHotelBill"+i).value;   
	  
	   if(eval(trSchHotelOtherAmt)>eval(hotelOtherCost))
	    {
	        hotelOtherFlag ="true";
	        break;
	       }  
	 } 
	   
	  if(travelFlag =="true" ||  hotelPerDayFlag =="true" ||  hotelOtherFlag =="true" )
	 {
	   dispAlert += "\n The following expenses are more than the entitled amount"; 
	   if(travelFlag =="true")
	   { 
	     dispAlert +="\n 1. Travel Miscellaneous Expenses";
	   }
	    if(hotelPerDayFlag =="true")
	   { 
	     dispAlert +="\n 2. Lodging  Expenses";
	   }
	    if(hotelOtherFlag =="true")
	   { 
	     dispAlert +="\n 3. Lodging Miscellaneous Expenses";
	   }
	 }
	  
	  dispAlert += " \n Do you really want to save the record."   
	   var conf = confirm(dispAlert);
			   if(conf==true)
			     { 
			       // document.getElementById("schExtraAmt"+i).focus();
			       }else
			         {  
			        // document.getElementById("schExtraAmt"+i).focus();
			          return false; 
			        } 
	 }
	 
 return true;
}
     //callScheduleSaveFinal
     
     
 function callScheduleSaveFinal()
{ 



 for(var i=1; i<hotelArr.length;i++)
	{ 
	    
		    if(!validateTime('chkInTime'+i,'Staying From Time'))
		    {
		     return false;
		    }    
		     if(!validateTime('chkOutTime'+i,'Staying To Time'))
		    {
		     return false;
		    }   
	 } 
	 
	  for(var i=1; i<schArr.length;i++)
	  { 
	    if(!validateTime('schJourneyTime'+i,'Time(HH24:MI)'))
		    {
		     return false;
		    } 
		    
		    if(!validateDate('schJourneyDate'+i,'Journey  Date'))
		    {
		     return false;
		    }  
	 } 
  return true;
}


function callReportMis(){  
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="TravelScheduleReport_report.action";
      document.getElementById("paraFrm_travelReportId").value = document.getElementById("paraFrm_trApp_travelAppId").value ;
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main"; 
	
}


</script>


