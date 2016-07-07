
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelQuickBooking" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">
					Booking Detail </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<%
			int srCounter = 0;
			int fieldVal = 0;
			int srCounterJourney = 0;
			int srCounterAccomodation = 0;
			int accomVal = 0;
			int srCounterLocalConveyance = 0;
			int localConveyanceVal = 0;
		%>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit name="" value="Close"
						cssClass="back" onclick="return backFun()">
					</s:submit></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>



		<!-- Employee Information table starts -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">

				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Employee Information</strong></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="initiator.name" id="initiator.name"
						ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label>:
					</td>
					<td colspan="3" width="80%"><s:property value="initiatorName" />
					<s:hidden name="initiatorCode"></s:hidden>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="application.date" id="application.date"
						ondblclick="callShowDiv(this);"><%=label.get("application.date")%>
					</label> :</td>
					<td colspan="1" width="30%"><s:property
						value="applicationDate" /></td>
					<td colspan="1" width="20%"><label class="set"
						name="appStatus" id="appStatus" ondblclick="callShowDiv(this);"><%=label.get("appStatus")%>
					</label> :</td>
					<td colspan="1" width="30%"><s:property
						value="applicationStatus" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="travel.StartDate" id="travel.StartDate"
						ondblclick="callShowDiv(this);"><%=label.get("travel.StartDate")%></label>
					<font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:property
						value="travelStartDate" /></td>
					<td colspan="1" width="20%"><label class="set"
						name="travel.EndDate" id="travel.EndDate"
						ondblclick="callShowDiv(this);"><%=label.get("travel.EndDate")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:property value="travelEndDate" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="travel.requestName" id="travel.requestName"
						ondblclick="callShowDiv(this);"><%=label.get("travel.requestName")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:property
						value="travelRequestName" /></td>
					<td colspan="1" width="20%"><label class="set"
						name="travel.purpose" id="travel.purpose"
						ondblclick="callShowDiv(this);"><%=label.get("travel.purpose")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:property value="travelPurpose" />

					<s:hidden name="travelPurposeId"></s:hidden></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="travel.project" id="travel.project"
						ondblclick="callShowDiv(this);"><%=label.get("travel.project")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property value="travelProject" /></td>
					<td colspan="1" width="20%" nowrap="nowrap"><label class="set"
						name="travel.otherproject" id="travel.otherproject"
						ondblclick="callShowDiv(this);"><%=label.get("travel.otherproject")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property
						value="travelOtherProject" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="travel.customer" id="travel.customer"
						ondblclick="callShowDiv(this);"><%=label.get("travel.customer")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property value="travelCustomer" /></td>
					<td colspan="1" width="20%" nowrap="nowrap"><label class="set"
						name="travel.othercustomer" id="travel.othercustomer"
						ondblclick="callShowDiv(this);"><%=label.get("travel.othercustomer")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property
						value="travelOtherCustomer" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="travel.type" id="travel.type"
						ondblclick="callShowDiv(this);"><%=label.get("travel.type")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:property value="travelType" /></td>
					<td colspan="1" width="20%">&nbsp;&nbsp;</td>
					<td colspan="1" width="30%">&nbsp;&nbsp;</td>
				</tr>


			</table>
			</td>
		</tr>


		<!-- Employee Information table ends -->

		<!-- Employee Information table starts -->
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Employee Information </strong></td>
				</tr>


				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" colspan="1" width="10%"><label
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td class="formth" colspan="1" width="30%"><label
								name="travel.employee.guest" id="travel.employee.guest"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.guest")%></label></td>

							<td class="formth" colspan="1" width="10%"><label name="dob"
								id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label></td>

							<td class="formth" colspan="1" width="10%"><label
								name="travel.employee.age" id="travel.employee.age"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.age")%></label></td>
							<td class="formth" colspan="1" width="25%"><label
								name="travel.employee.contact" id="travel.employee.contact"
								ondblclick="callShowDiv(this);"><%=label.get("travel.employee.contact")%></label></td>
							<td class="formth" colspan="1" width="15%"><label
								name="travel.advance.amount" id="travel.advance.amount"
								ondblclick="callShowDiv(this);"><%=label.get("travel.advance.amount")%></label></td>

						</tr>
						<s:iterator value="travellerList">
							<tr>
								<td class="sortableTD" align="center" width="10%"><%=++srCounter%></td>
								<td class="sortableTD" align="left" width="30%"><s:property
									value="%{employeeNameFromList}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="10%"><s:property
									value="%{dateOfBirth}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="10%"><s:property
									value="%{employeeAgeFromList}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="25%"><s:property
									value="%{employeeContactFromList}" />&nbsp;</td>
								<td class="sortableTD" align="center" width="15%">
								<s:property value="%{currencyEmployeeAdvance}"/>&nbsp;
								<s:property	value="%{employeeAdvanceFromList}" />
									</td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>

				<!-- Employee Information table ends -->
			</table>
			</td>
		</tr>


		<!-- Journey Details table starts -->
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<td>Journey Details</td>
				<td></td>
				<td align="right"><s:if test="buttonShowFlag">
					<input type="button" value="Add" Class="token" theme="simple"
						onclick="addRowToJourneyBlock();" />
				</s:if></td>
				</tr>

				<!-- iterator for Journey Details   -->


				<tr>
					<td colspan="4">
					<table width="100%" id="journeyTable" class="sortable" border="0">
						<tr>
							<td class="formth"><label class="set" name="sno" id="sno1"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>

							<td class="formth"><label class="set" name="Frplace"
								id="Frplace1" ondblclick="callShowDiv(this);"><%=label.get("Frplace")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="Toplace"
								id="Toplace0" ondblclick="callShowDiv(this);"><%=label.get("Toplace")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="JMclass"
								id="JMclass" ondblclick="callShowDiv(this);"><%=label.get("JMclass")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="Jourdate"
								id="Jourdate0" ondblclick="callShowDiv(this);"><%=label.get("Jourdate")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="Timing"
								id="Timing" ondblclick="callShowDiv(this);"><%=label.get("Timing")%></label>
							</td>

							<td class="formth"><label class="set" name="agencyName1"
								id="agencyName1" ondblclick="callShowDiv(this);"><%=label.get("agencyName")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="journeyMedium1"
								id="journeyMedium1" ondblclick="callShowDiv(this);"><%=label.get("journeyMedium")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set"
								name="bustrainflightno" id="bustrainflightno"
								ondblclick="callShowDiv(this);"><%=label.get("bustrainflightno")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="ticket.number"
								id="ticket.number" ondblclick="callShowDiv(this);"><%=label.get("ticket.number")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="bestFare"
								id="bestFare" ondblclick="callShowDiv(this);"><%=label.get("bestFare")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="actualFare"
								id="actualFare" ondblclick="callShowDiv(this);"><%=label.get("actualFare")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="travel.details"
								id="travel.details" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
							</td>

							<td class="formth"><label class="set"
								name="travel.fileupload" id="travel.fileupload"
								ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
							</td>

							<td class="formth">&nbsp;</td>

						</tr>
						<!-- iterator for saved journey record starts-->

						<s:iterator value="journeyList">

							<tr>
								<td class="sortableTD" align="center" nowrap="nowrap"><%=++srCounterJourney%></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyFromPlace" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyToPlace" /></td>

								<td align="center" class="sortableTD" nowrap="nowrap"><s:property
									value="jourMode" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyDate" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyTime" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyAgency" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyMedium" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="busTrainNo" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="ticketNo" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap">
									<s:property value="currencyCost"/>
									<s:property value="cost" />
								</td>
								<td class="sortableTD" align="center" nowrap="nowrap">
									<s:property value="currencyActualCost"/>
									<s:property value="actualCost" />
								</td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeydetails" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="journeyFileUpload" /> <input type="hidden"
									name="journeyFileUpload" size="20" readonly="true"
									id="journeyFileUpload<%=fieldVal%>"
									value='<s:property value="journeyFileUpload"/>' size="20" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><input
									type="button" class="token" value="Show"
									onclick="return showRecord('journeyFileUpload<%=fieldVal%>');"/ >

								</td>

							</tr>
							<%
							fieldVal++;
							%>
						</s:iterator>
						<!-- iterator for saved journey record starts-->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Journey Details table ends -->


		<!-- Lodging Details table starts here -->

<s:if test="lodgeDtlFlag">


		<tr>
			<td width="100%" id="lodgingBlock" style='display: block'>

			<table width="100%" class="formbg">
				<tr>
					<td colspan="1">Lodging Details</td>
					<td></td>
					<td align="right"></td>
				</tr>
				<tr>
					<td width="100%" colspan="3">
					<table width="100%" border="0" id="accomodationTable">
						<tr>
							<td class="formth"><label class="set" name="sno" id="sno2"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>

							<td class="formth"><label class="set" name="LodType"
								id="LodType" ondblclick="callShowDiv(this);"><%=label.get("LodType")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="RoomType"
								id="RoomType2" ondblclick="callShowDiv(this);"><%=label.get("RoomType")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="traCity"
								id="traCity" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="Prefloc"
								id="Prefloc" ondblclick="callShowDiv(this);"><%=label.get("Prefloc")%></label>
							</td>

							<td class="formth"><label class="set" name="FrDate"
								id="FrDate" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="FrTime"
								id="FrTime" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="ToDate"
								id="ToDate" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label><font
								color="red">*</font></td>
							<td class="formth"><label class="set" name="ToTime"
								id="ToTime" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="hotelname1"
								id="hotelname1" ondblclick="callShowDiv(this);"><%=label.get("hotelname")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="noOfDays"
								id="noOfDays" ondblclick="callShowDiv(this);"><%=label.get("noOfDays")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="travel.recRate"
								id="travel.recRate" ondblclick="callShowDiv(this);"><%=label.get("travel.recRate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="corporaterate"
								id="corporaterate" ondblclick="callShowDiv(this);"><%=label.get("corporaterate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="travel.details"
								id="travel.details1" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
							</td>

							<td class="formth"><label class="set"
								name="travel.fileupload" id="travel.fileupload1"
								ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
							</td>

							<td class="formth" align="center">&nbsp;</td>

						</tr>

						<s:iterator value="accomodationList">
							<tr>
								<td class="sortableTD" align="center" nowrap="nowrap"><%=++srCounterAccomodation%></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationHotelType" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationRoomType" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationCity" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationPrefLocation" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationFromDate" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationFromTime" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationToDate" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationToTime" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="accomodationHotelName" /></td>

								<td class="sortableTD" align="center"><s:property
									value="noOfDays" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap">
									<s:property value="currencyBookingAmount"/>
									<s:property value="bookingAmount" />
								</td>
								<td class="sortableTD" align="center" nowrap="nowrap">
									<s:property value="currencyCorporateRate"/>
									<s:property value="corporateRate" />
								</td>

								<td class="sortableTD" align="center" nowrap="nowrap">&nbsp;<s:property
									value="bookingDetails" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="bookingFileUpload" />&nbsp;<input type="hidden"
									name="bookingFileUpload" size="20" readonly="true"
									id="paraFrm_bookingFileUpload<%=accomVal%>"
									value='<s:property value="bookingFileUpload" />' size="20" /></td>



								<td class="sortableTD" align="center" nowrap="nowrap"><input
									type="button" name="show" value="Show" class="token"
									onclick="showRecord('paraFrm_bookingFileUpload<%=accomVal%>');" />

								</td>

							</tr>
							<%
							accomVal++;
							%>
						</s:iterator>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>

</s:if>

		<!-- Lodging Details table ends here -->


		<!-- Local Conveyance Details table starts here -->

	<s:if test="localConvFlag">


		<tr>
			<td id="conveyanceBlock" style='display: block'>
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="1">Local Conveyance Details</td>
					<td></td>
					<td align="right"></td>
				</tr>

				<tr>
					<td width="100%" colspan="3">
					<table width="100%" border="0" id="localConveyanceTable">
						<tr>
							<td class="formth"><label class="set" name="sno" id="sno3"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>

							<td class="formth"><label class="set" name="traCity"
								id="traCity1" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label><font
								color="red">*</font></td>

							<td class="formth"><label class="set" name="TraDet"
								id="TraDet1" ondblclick="callShowDiv(this);"><%=label.get("TraDet")%></label>
							 </td>

							<td class="formth"><label class="set" name="locMedium"
								id="locMedium" ondblclick="callShowDiv(this);"><%=label.get("locMedium")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="FrDate"
								id="FrDate3" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="FrTime"
								id="FrTime3" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set" name="ToDate"
								id="ToDate3" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="ToTime"
								id="ToTime4" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label>
							<font color="red">*</font></td>

							<td class="formth"><label class="set"
								name="travel.tariffcost" id="travel.tariffcost"
								ondblclick="callShowDiv(this);"><%=label.get("travel.tariffcost")%></label>
							<font color="red">*</font></td>
							<td class="formth"><label class="set" name="travel.details"
								id="travel.details2" ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
							</td>
							<td class="formth"><label class="set"
								name="travel.fileupload" id="travel.fileupload2"
								ondblclick="callShowDiv(this);"><%=label.get("travel.fileupload")%></label>
							</td>
							<td class="formth"></td>

						</tr>
						<s:iterator value="localConveyanceList">
							<tr>
								<td class="sortableTD" align="center" nowrap="nowrap"><%=++srCounterLocalConveyance%></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceCity" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceTravelDetail" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceTravelMedium" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceFromDate" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceFromTime" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><s:property
									value="localConveyanceToDate" /></td>

								<td class="sortableTD" align="center" nowrap="nowrap">&nbsp;<s:property
									value="localConveyanceToTime" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap">&nbsp;
									<s:property value="currencyLocalConveyanceTariffCost"/>
									<s:property value="localConveyanceTariffCost" />
								</td>
								<td class="sortableTD" align="center" nowrap="nowrap">&nbsp;<s:property
									value="localConveyanceDetails" /></td>
								<td class="sortableTD" align="center" nowrap="nowrap">&nbsp;<s:property
									value="localConveyanceFileUpload" /><input type="hidden"
									name="localConveyanceFileUpload" size="20" readonly="true"
									id="paraFrm_localConveyanceFileUpload<%=localConveyanceVal%>"
									value='<s:property value="localConveyanceFileUpload" />' /></td>

								<td class="sortableTD" align="center" nowrap="nowrap"><input
									type="button" name="show" value="Show" class="token"
									onclick="showRecord('paraFrm_localConveyanceFileUpload<%=localConveyanceVal%>');" />
								</td>
							</tr>
							<%
							localConveyanceVal++;
							%>
						</s:iterator>
					</table>
					</div>
					</td>
				</tr>


				<%
				int k = 0;
				%>
				<input type="hidden" name="hLocCount" id="hLocCount" value="<%=k%>">
				<s:hidden name="delLoc" theme="simple" />
			</table>
			</div>
			</td>
		</tr>

</s:if>

		<!-- Local Conveyance Details table ends here -->




		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit name="" value="Close"
						cssClass="back" onclick="return backFun();">
					</s:submit></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- table ends here -->

<s:hidden  name="source" id="source"/>  
	</table>

</s:form>


<script>


function showRecord(fieldName)
	{
		
		var fileName =document.getElementById(fieldName).value;
		//alert(fileName);
		
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelQuickBooking_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
		return true ; 
	}

function backFun(){
try{

	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}else{
		window.close();
		}
		
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
	}
}
</script>
