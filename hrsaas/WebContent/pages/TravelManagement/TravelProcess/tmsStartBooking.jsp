
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelMonitor" validate="true" id="paraFrm" theme="simple">

	<table width="98%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="tvlStatus" />
		<s:hidden name="accStatus" />
		<s:hidden name="locConStatus" />
		<s:hidden name="path" value="%{getText('data_path')}" id="pathFld"/>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">
					Booking Details</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit value="  Save" action="TravelMonitor_book" onclick="return saveValidate();"
						theme="simple" cssClass="save" /> <input type="button" value="Close" class="token"
						 onclick="return callClose();"/></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="empId" id="hiddEmpId" />
		<s:hidden name="applicationId" id="hiddApplicationId" />
		<s:hidden name="applicationDate" id="hiddApplicationDate" />
		<s:hidden name="empApplId" id="hiddEmpApplId" />
		<s:hidden name="currentPage" id="currentPage" />
		<s:hidden name="iniEmpId" id="iniEmpId" />
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
					<td width="15%"><label name="travel.policy" id="travel.policy"
						ondblclick="callShowDiv(this);"><%=label.get("travel.policy")%></label>
					:</td>
					<td width="25%" ><s:property value="policyName" /></td>
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
						ondblclick="callShowDiv(this);"><%=label.get("tourStartDate")%></label> :</td>
					<td><s:property value="tourStrtDate" /></td>
					<td width="15%"><label name="tourEndDate"
						id="tourEndDate" ondblclick="callShowDiv(this);"><%=label.get("tourEndDate")%></label>
					:</td>
					<td width="45%"><s:property value="tourEndDate" /></td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td width="100%" colspan="2"><s:if test="empId != 0" ><input
						type="button" name="ViewPolicy" value="View Policy" class="token"
						onclick="viewPolicy('<s:property value="gradeId" />')"; > &nbsp;&nbsp;&nbsp;&nbsp;</s:if><input type="button" name="ViewReq"
						value="View Application" class="token"
						onclick="viewDetails('<s:property value="applicationId" />','<s:property value="empApplId" />','S')"; >
					</td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">

				<tr>
					<td><strong>Tour Details</strong></td>
				</tr>


				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%" colspan="1" class="formtext" height="22">Travel
							Application For:</td>
							<td colspan="3"><s:label name="trvlAppFor" theme="simple"
								value="%{trvlAppFor}" /></td>

						</tr>



						<tr>
							<td width="20%" class="formtext" height="22">Travel Request
							Name:</td>
							<td width="25%"><s:label name="trvlReqName" theme="simple"
								value="%{trvlReqName}" /></td>

							<td width="20%" class="formtext">Travel Purpose:</td>
							<td width="25%"><s:label name="trvlPurpose" theme="simple"
								value="%{trvlPurpose}" /></td>

						</tr>


						<tr>

							<td width="20%" class="formtext" height="22">Accommodation:</td>
							<td width="25%"><s:label name="trvlAccom" theme="simple"
								value="%{trvlAccom}" /></td>
							<td width="20%" class="formtext">Travel Arrangement:</td>
							<td width="25%"><s:label name="trvlArrngmt" theme="simple"
								value="%{trvlArrngmt}" /></td>



						</tr>


						<tr>

							<td width="20%" class="formtext" height="22">Local
							Conveyance:</td>
							<td width="25%"><s:label name="trvlLocCon" theme="simple"
								value="%{trvlLocCon}" /></td>
							<td width="20%" class="formtext">Tour Location:</td>
							<td width="25%"><s:label name="trvlType" theme="simple"
								value="%{trvlType}" /></td>

						</tr>


						<tr>

							<td width="20%" class="formtext" height="22">Tour Start
							Date:</td>
							<td width="25%"><s:label name="tourStrtDate" theme="simple"
								value="%{tourStrtDate}" /></td>
							<td width="20%" class="formtext">Tour End Date:</td>
							<td width="25%"><s:label name="tourEndDate" theme="simple"
								value="%{tourEndDate}" /></td>

						</tr>


					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<%Object[][] status = null;
		
			try{
				status = (Object[][])request.getAttribute("statObj");
			}catch(Exception e)
			{
				status = new Object[1][1];
			}
		%>
		<%!
			boolean tvlStatusFlag=false,accStatusFlag=false,locStatusFlag=false;
		%>
		
		<s:if test="tvlStatus == 'SC'">
			<%
				tvlStatusFlag=true;
			%>
		</s:if>
		<s:if test="accStatus == 'SC'">
			<%
				accStatusFlag=true;
			%>
		</s:if>
		<s:if test="locConStatus == 'SC'">
			<%
				locStatusFlag=true;
			%>
		</s:if>
		<s:if test="(userType=='SCH') || (tvlStatus=='SC' || tvlStatus=='SB' || tvlStatus=='Y ')">
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Travel Details </strong></td>
				</tr>

				<!-- iterator for Journey Details   -->


				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" colspan="1" width="10%"><label name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td class="formth" colspan="1" width="25%"><label name="source" id="source" ondblclick="callShowDiv(this);"><%=label.get("source")%></label></td>
							<td class="formth" colspan="1" width="25%"><label name="destination" id="destination" ondblclick="callShowDiv(this);"><%=label.get("destination")%></label></td>
							<td class="formth" colspan="1" width="30%"><label name="travelmodeclass" id="travelmodeclass" ondblclick="callShowDiv(this);"><%=label.get("travelmodeclass")%></label></td>
							<td class="formth" colspan="1" width="10%"><label name="travel.date" id="travel.date" ondblclick="callShowDiv(this);"><%=label.get("travel.date")%></label></td>
							<td class="formth" colspan="1" width="10%"><label name="time" id="time" ondblclick="callShowDiv(this);"><%=label.get("time")%></label>(HH24:MI)</td>
							<td class="formth" colspan="1" width="10%"><label name="bustrainflightno" id="bustrainflightno" ondblclick="callShowDiv(this);"><%=label.get("bustrainflightno")%></label><font color="red"> * </font></td>
							<td class="formth" colspan="1" width="10%"><label name="ticket.number" id="ticket.number" ondblclick="callShowDiv(this);"><%=label.get("ticket.number")%></label><font color="red"> * </font></td>
							<td class="formth" colspan="1" width="10%"><label name="cost" id="cost" ondblclick="callShowDiv(this);"><%=label.get("cost")%></label><font color="red"> * </font></td>
							<td class="formth" width="10%"><label id="details">Details</label></td>
							<td class="formth" width="10%"><label id="details">File Upload</label></td>
							<s:if test="tvlStatus == 'SC'">
								<td class="formth" width="10%"><label id="cancel.charge">Cancellation Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt">Refund Amount</label></td>
								<td class="formth" width="10%"><label id="comments">Comments</label></td>
							</s:if>
						</tr>
						<%!int i = 0,jourCount=0;%>
						<%
							int k = 1;
							%>



						<s:iterator value="travelJourDtl">


							<tr id="tr_Tvl_<%=k%>">
								<s:hidden name="JournDtlId" />
								<s:hidden name="jourClsId"/>
								<td class="sortableTD"><%=k%></td>
								<td class="sortableTD" colspan="1">
								<input type="hidden" name="jourFrm" id="jourFrm<%=k %>" value='<s:property
									value="jourFrm" />' >
								<s:property
									value="jourFrm" />&nbsp;</td>
								<td class="sortableTD" width="8%" >
								<input type="hidden" name="jourTo" id="jourTo<%=k %>" value='<s:property
									value="jourTo" />' >
								<s:property
									value="jourTo" />&nbsp;
									</td>
								<td class="sortableTD"><s:property value="JourModeCls" />&nbsp;</td>
								<td class="sortableTD" width="12%" ><s:property
									value="jourDate" /><s:hidden name="jourDate"/>&nbsp;</td>
								<td class="sortableTD" width="10%" ><s:property
									value="jourTime" /><s:hidden name="jourTime"/>&nbsp;</td>
								<td class="sortableTD" width="10%" >
								<s:if test="jourCancelFlag == 'CC'">
									<s:property value="jourNo"/>
									<input type="hidden" name="jourNo" id="jourNo<%=k %>"
										 value='<s:property value="jourNo"/>' >
								</s:if>
								<s:else>
									<input type="text" name="jourNo" id="jourNo<%=k %>" maxlength="50"
										 value='<s:property value="jourNo"/>' size="15"  />&nbsp;
								</s:else>
								</td>
								<td class="sortableTD" width="10%" >
								<s:if test="jourCancelFlag == 'CC'">
									<s:property value="ticketNo"/>
									<input type="hidden" name="ticketNo" " id="ticketNo<%=k %>" 
									 value='<s:property value="ticketNo"/>' />
								</s:if>
								<s:else>
								<input type="text" name="ticketNo" " id="ticketNo<%=k %>" maxlength="50"
									 value='<s:property value="ticketNo"/>' size="15" />&nbsp;
								</s:else>
									 </td>
								<td class="sortableTD" width="10%" >
								<s:if test="jourCancelFlag == 'CC'">
									<s:property value="jourCost"/>
									<input type="hidden" name="jourCost" " id="jourCost<%=k %>"
									 value='<s:property value="jourCost"/>' >
								</s:if>
								<s:else>
									<input type="text" name="jourCost" " id="jourCost<%=k %>" maxlength="10"
									 value='<s:property value="jourCost"/>' onkeypress="return numbersWithDot();" 
									 size="15%" />&nbsp;
								</s:else>
								</td>
								<s:if test="jourCancelFlag == 'CC'">
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:textarea name="jourDetails" rows="1" cols="10" onkeyup="textCounter(this,500);"
																	 id='<%="details1_"+k%>' readonly="true"/>
								<input type="hidden" name="remCount" id='remCount1_<%=k%>' >
								<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details1_<%=k%>','details','readonly','remCount1_<%=k%>',500);">								
								</td>
								</s:if>
								<s:else>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:textarea name="jourDetails" rows="1" cols="10" onkeyup="textCounter(this,500);"
																		 id='<%="details1_"+k%>' />
									<input type="hidden" name="remCount" id='remCount1_<%=k%>' >
									<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
										onclick="callWindow('details1_<%=k%>','details','','remCount1_<%=k%>',500);">								
									</td>
								</s:else>
								<s:hidden name="jourCancelFlag" id='<%="jourCancelFlag"+k%>'/>
								<s:if test="jourCancelFlag == 'CC'">
									<script>
										document.getElementById('tr_Tvl_<%=k%>').style.background='#FFC488';
									</script>
								<%
									if(tvlStatusFlag)
									{
								%>
									<input type="hidden" name="uploadFileTvl" id='<%="uploadFileTvl"+k%>'
											value='<s:property value="uploadFileTvl" />' >
									<td class="sortableTD" width="25%">
									<a style="cursor: pointer;" onclick="showTicketFile('<s:property value="uploadFileTvl" />');"><s:property value="uploadFileTvl" /></a>
										</td>
									<td class="sortableTD" width="10%" >
									<input type="text" name="cancelChargeTvl" size="10" maxlength="10" onkeypress="return numbersOnly();"
									   value='<s:property value="cancelChargeTvl" />' id='<%="cancelChargeTvl"+k%>'></td>
									<td class="sortableTD" width="10%" >
										<input type="text" name="refundAmtTvl" size="10" maxlength="10" onkeypress="return numbersOnly();"
											value='<s:property value="refundAmtTvl" />' id='<%="refundAmtTvl"+k%>'>
										</td>
										<td class="sortableTD" width="10%" nowrap="nowrap">
										<textarea name="commentsTvl" rows="1" cols= "10" onkeyup="textCounter(this,500);" id='<%="comments1_"+k%>' ><s:property value="commentsTvl" /></textarea>
										<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
										onclick="callWindow('comments1_<%=k%>','comments','','remCount1_<%=k%>',500);">
									</td>
									<%
										}
									else
									{ %>
										<td class="sortableTD" width="10%" >&nbsp;</td>
										<input type="hidden" name="cancelChargeTvl" value='<s:property value="cancelChargeTvl" />'>
									<input type="hidden" name="refundAmtTvl" value='<s:property value="refundAmtTvl" />'>
									<input type="hidden" name="commentsTvl" value='<s:property value="commentsTvl" />'>
								<%	}
								%>
								</s:if>
								<s:else>
									<input type="hidden" name="cancelChargeTvl" value='<s:property value="cancelChargeTvl" />'>
									<input type="hidden" name="refundAmtTvl" value='<s:property value="refundAmtTvl" />'>
									<input type="hidden" name="commentsTvl" value='<s:property value="commentsTvl" />'>
									<td class="sortableTD" width="25%" nowrap="nowrap">
										<input type="text" name="uploadFileTvl" size="20" readonly="true" id="uploadFileTvl<%=k%>">
										<input type="button" name="uploadTvl" value="Upload" class="token" onclick="uploadTicketFile('uploadFileTvl<%=k%>');">
									</td>
								</s:else>
							</tr>
							<%
								k++;
								%>
						</s:iterator>
							<%if(String.valueOf(status[0][0]).trim().equals("N")) {%>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Not Required</strong></font></td>
							</tr>
						<%
						}
						else if(String.valueOf(status[0][0]).trim().equals("CN"))
						{ %>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Self Managed</strong></font></td>
							</tr>	
							<%} else if(k==1){
						 %>
								<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red"><strong>Cancelled</strong></font></td>
							</tr>	
						<% }
						i = k;
						jourCount=i;
						i=0;
							%>
					</table>
					</td>

				</tr>

				<!-- iterator for journey Details end -->


			</table>
			</td>
		</tr>
		</s:if>
		<s:if test="(userType=='SCH') || (accStatus=='SC' || accStatus=='SB' || accStatus=='Y ')">
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">


				<tr>
					<td><strong>Accommodations Details </strong></td>
				</tr>

				<!-- iterator for lodging Details   -->


				<tr>
					<td width="100%" colspan="9">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" colspan="1" width="6%">Sr No</td>
							<td class="formth" colspan="1" width="15%">Hotel Type</td>
							<td class="formth" colspan="1" width="15%">Room Type</td>
							<td class="formth" colspan="1" width="15%">City</td>
							<td class="formth" colspan="1" width="15%">Address</td>
							<td class="formth" colspan="1" width="10%">From Date</td>
							<td class="formth" colspan="1" width="8%">From Time(HH24:MI)</td>
							<td class="formth" colspan="1" width="10%">To Date</td>
							<td class="formth" colspan="1" width="8%">To Time(HH24:MI)</td>
							<td class="formth" colspan="1" width="6%">Booking Amount <font color="red"> * </font></td>
							<td class="formth" width="10%">Details</td>
							<td class="formth" width="10%"><label id="details">File Upload</label></td>
							<s:if test="accStatus == 'SC'">
								<td class="formth" width="10%"><label id="cancel.charge1">Cancellation Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt1">Refund Amount</label></td>
								<td class="formth" width="10%"><label id="comments1">Comments</label></td>
							</s:if>
						</tr>
						<%!int c = 0,accCount=0;%>
						<%
							int y = 1;
							%>



						<s:iterator value="travelLodgDtl">
							<tr id="tr_Acc_<%=y%>">
								<s:hidden name="lodgId" />
								<s:hidden name="lodgDtlId" />
								<td class="sortableTD" width="6%"><%=y%></td>
								<td class="sortableTD" colspan="1"><s:property
									value="lodgHotel" />&nbsp;</td>
								<td class="sortableTD" width="15%" ><s:property
									value="lodgRoom" />&nbsp;</td>
								<td class="sortableTD" width="15%">
								<input type="hidden" value='<s:property value="lodgCity" />' name="lodgCity" id="lodgCity<%=y%>">
								<s:property value="lodgCity" />&nbsp;</td>
								<td class="sortableTD" width="15%" ><s:property
									value="lodgPreLoc" />&nbsp;</td>
								<td class="sortableTD" width="10%" ><s:property
									value="lodgFrmDate" />&nbsp;</td>
								<td class="sortableTD" width="8%" ><s:property
									value="lodgFrmTime" />&nbsp;</td>
								<td class="sortableTD" width="10%" ><s:property
									value="lodgToDate" />&nbsp;</td>
								<td class="sortableTD" width="8%" ><s:property
									value="lodgToTime" />&nbsp;</td>
								<td class="sortableTD" width="6%" >
								<s:hidden name="lodgCancelFlag" id='<%="lodgCancelFlag"+y%>'/>
								<s:if test="lodgCancelFlag == 'CC'">
									<s:property value="lodgBookAmt"/>
									<input type="hidden" id="lodgeBookAmt<%=y%>" 
									name="lodgBookAmt" value='<s:property value="lodgBookAmt"/>' >
								</s:if>
								<s:else>
									<input type="text" size="6%" " id="lodgeBookAmt<%=y %>" maxlength="10"
									name="lodgBookAmt" value='<s:property value="lodgBookAmt"/>'  onkeypress="return numbersOnly();"/>&nbsp;
								</s:else>
								</td>
								<s:if test="lodgCancelFlag == 'CC'">
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:textarea name="accDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,500);" id='<%="details2_"+y%>' readonly="true"/>
								<input type="hidden" name="remCount" id='remCount2_<%=y%>' >
								<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details2_<%=y%>','details','readonly','remCount2_<%=y%>',500);">
								</td>
								</s:if>
								<s:else>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:textarea name="accDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,500);" id='<%="details2_"+y%>'/>
								<input type="hidden" name="remCount" id='remCount2_<%=y%>' >
								<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details2_<%=y%>','details','','remCount2_<%=y%>',500);">
								</td>
								</s:else>
								<s:if test="lodgCancelFlag == 'CC'">
								<script>
									document.getElementById('tr_Acc_<%=y%>').style.background='#FFC488';
								</script>
								<%
									if(accStatusFlag)
									{
								%>
								<input type="hidden" name="uploadFileAcc" id='<%="uploadFileAcc"+y%>'
										value='<s:property value="uploadFileAcc" />' >
								<td class="sortableTD" width="25%" >
								<a style="cursor: pointer;" onclick="showTicketFile('<s:property value="uploadFileAcc" />');"><s:property value="uploadFileAcc" /></a>
									</td>
								<td class="sortableTD" width="10%" >
								<input type="text" name="cancelChargeAcc" id='<%="cancelChargeAcc"+y %>' size="10" maxlength="10" onkeypress="return numbersOnly();"
								   value='<s:property value="cancelChargeAcc" />' id='<%="cancelChargeAcc"+y%>'></td>
								<td class="sortableTD" width="10%" >
									<input type="text" name="refundAmtAcc" id='<%="refundAmtAcc"+y %>' size="10" maxlength="10" onkeypress="return numbersOnly();"
										value='<s:property value="refundAmtAcc" />' id='<%="refundAmtAcc"+y%>'>
									</td>
									<td class="sortableTD" width="10%" nowrap="nowrap">
									<textarea name="commentsAcc" rows="1" cols= "10" id='<%="comments2_"+y %>' onkeyup="textCounter(this,500);" id='<%="comments2_"+y%>'><s:property value="commentsAcc" /></textarea>
									<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('comments2_<%=y%>','comments','','remCount1_<%=y%>',500);">
								</td>
								<%
									}
									else
									{ %>
										<td class="sortableTD" width="10%" >&nbsp;</td>
										<input type="hidden" name="cancelChargeAcc" value='<s:property value="cancelChargeAcc" />'>
									<input type="hidden" name="refundAmtAcc" value='<s:property value="refundAmtAcc" />'>
									<input type="hidden" name="commentsAcc" value='<s:property value="commentsAcc" />'>
								<%	}
								%>
								</s:if>
								<s:else>
									<input type="hidden" name="cancelChargeAcc" value='<s:property value="cancelChargeAcc" />'>
									<input type="hidden" name="refundAmtAcc" value='<s:property value="refundAmtAcc" />'>
									<input type="hidden" name="commentsAcc" value='<s:property value="commentsAcc" />'>
									<td class="sortableTD" width="25%" nowrap="nowrap">
										<input type="text" name="uploadFileAcc" size="20" readonly="true" id="uploadFileAcc<%=y%>">
										<input type="button" name="uploadAcc" value="Upload" class="token" onclick="uploadTicketFile('uploadFileAcc<%=y%>');">
									</td>
								</s:else>
							</tr>
							<%
								y++;
								%>
						</s:iterator>
							<%if(String.valueOf(status[0][1]).trim().equals("N")) {%>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Not Required</strong></font></td>
							</tr>
						<%
						}
						else if(String.valueOf(status[0][1]).trim().equals("CN"))
						{ %>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Self Managed</strong></font></td>
							</tr>	
							<%} else if(y==1){
						 %>
								<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red"><strong>Cancelled</strong></font></td>
							</tr>	
						<% }
							c = y;
							accCount=c;
							c=0;
							%>
					</table>
					</td>

				</tr>

				<!-- iterator for lodging Details end -->


			</table>
			</td>
		</tr>
		</s:if>	
		<s:if test="(userType=='SCH') || (locConStatus=='SC' || locConStatus=='SB' || locConStatus=='Y ')">
		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">


				<tr>
					<td><strong>Local Conveyance Details </strong></td>
				</tr>

				<!-- iterator for local conveyance Details   -->
				

				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" width="9%">Sr No</td>
							<td class="formth" width="12%">City</td>
							<td class="formth" width="14%">Travel Mode</td>
							<td class="formth" width="10%">Date</td>
							<td class="formth" width="10%">From Time(HH24:MI)</td>
							<td class="formth" width="10%">To Time(HH24:MI)</td>
							<td class="formth" width="10%">Tariff Cost<font color="red"> * </font></td>
							<td class="formth" width="10%">Details</td>
							<td class="formth" width="10%"><label id="details">File Upload</label></td>
							<s:if test="locConStatus == 'SC'">
								<td class="formth" width="10%"><label id="cancel.charge2">Cancellation Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt2">Refund Amount</label></td>
								<td class="formth" width="10%"><label id="comments2">Comments</label></td>
							</s:if>
						</tr>
						<%!int j = 0,locCount=0;%>
						<%
							int x = 1;
							%>



						<s:iterator value="travelLocConDtl">


							<tr id="tr_Loc_<%=x%>">
								<s:hidden name="locConId" />
								<s:hidden name="locConDtlId" />
								<td class="sortableTD"><%=x%></td>
								<td class="sortableTD" colspan="1">
								<input type="hidden" value='<s:property value="locConCity" />' name="locConCity" id="locConCity<%=x%>">
								<s:property
									value="locConCity" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="locConMode" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="locConDate" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="locConFrmTime" />&nbsp;</td>
								<td class="sortableTD" ><s:property
									value="locConToTime" />&nbsp;</td>						
								<s:if test="locCancelFlag == 'CC'">
								<td class="sortableTD" >
									<s:property value="locConCost"/>&nbsp;
									<input type="hidden" name="locConCost" id="locConCost<%=x %>" 
										value='<s:property value="locConCost"/>' " >
									</td>
								</s:if>
								<s:else>
									<td class="sortableTD" >
									<input type="text" name="locConCost" size="15%"  id="locConCost<%=x %>" maxlength="10"
									value='<s:property value="locConCost"/>' onkeypress="return numbersWithDot();" >&nbsp;
									</td>
								</s:else>
								<s:if test="locCancelFlag == 'CC'">
								<input type="hidden" name="remCount" id='remCount3_<%=x%>' >
								<td class="sortableTD" width="10%" nowrap="nowrap">
								<s:textarea name="locDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,500);" id='<%="details3_"+x%>' readonly="true"/>
								<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details3_<%=x%>','details','readonly','remCount3_<%=x%>',500);">
								</td>
								</s:if>
								<s:else>
									<input type="hidden" name="remCount" id='remCount3_<%=x%>' >
								<td class="sortableTD" width="10%" nowrap="nowrap">
								<s:textarea name="locDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,500);" id='<%="details3_"+x%>'/>
								<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details3_<%=x%>','details','','remCount3_<%=x%>',500);">
								</td>
								</s:else>
								<s:hidden name="locCancelFlag" id='<%="locCancelFlag"+x%>'/>
								<s:if test="locCancelFlag == 'CC'">
								<script>
									document.getElementById('tr_Loc_<%=x%>').style.background='#FFC488';
								</script>
								<%
									if(locStatusFlag)
									{
								%>
								
								<input type="hidden" name="uploadFileLoc" id='<%="uploadFileLoc"+x%>'
										value='<s:property value="uploadFileLoc" />' >
								<td class="sortableTD" width="25%" >
								<a style="cursor: pointer;" onclick="showTicketFile('<s:property value="uploadFileLoc" />');"><s:property value="uploadFileLoc" /></a>
									</td>
								<td><input type="text" name="cancelChargeLoc" id='<%="cancelChargeLoc"+x %>' id='<%="cancelChargeLoc"+x%>' size="10" maxlength="10" onkeypress="return numbersOnly();"
								  value='<s:property value="cancelChargeLoc" />'></td>
								<td class="sortableTD" width="10%" >
									<input type="text" name="refundAmtLoc" id='<%="refundAmtLoc"+x %>' id='<%="refundAmtLoc"+x%>' size="10" maxlength="10" onkeypress="return numbersOnly();"
										value='<s:property value="refundAmtLoc" />'>
									</td>
								<td class="sortableTD" width="10%" nowrap="nowrap">
									<textarea name="commentsLoc" rows="1" cols= "10" onkeyup="textCounter(this,500);" id='<%="comments3_"+x%>'><s:property value="commentsLoc" /></textarea>
									<img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('comments3_<%=x%>','comments','','remCount3_<%=x%>',500);">
								</td>
								<%
									}
									else
									{ %>
										<td class="sortableTD" width="10%" >&nbsp;</td>
										<input type="hidden" name="cancelChargeLoc" value='<s:property value="cancelChargeLoc" />'>
									<input type="hidden" name="refundAmtLoc" value='<s:property value="refundAmtLoc" />'>
									<input type="hidden" name="commentsLoc" value='<s:property value="commentsLoc" />'>
								<%	}
								%>
								</s:if>
								<s:else>
									<input type="hidden" name="cancelChargeLoc" value='<s:property value="cancelChargeLoc" />'>
									<input type="hidden" name="refundAmtLoc" value='<s:property value="refundAmtLoc" />'>
									<input type="hidden" name="commentsLoc" value='<s:property value="commentsLoc" />'>
									<td class="sortableTD" width="25%" nowrap="nowrap">
										<input type="text" name="uploadFileLoc" size="20" readonly="true" id="uploadFileLoc<%=x%>">
										<input type="button" name="uploadLoc" value="Upload" class="token" onclick="uploadTicketFile('uploadFileLoc<%=x%>');">
									</td>
								</s:else>
							</tr>
							<%
								x++;
								%>
						</s:iterator>
							<%if(String.valueOf(status[0][2]).trim().equals("N")) {%>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Not Required</strong></font></td>
							</tr>
						<%
						}
						else if(String.valueOf(status[0][2]).trim().equals("CN"))
						{ %>
							<tr>
								<td width="100%" colspan="4" align="right"><font
									color="red"><strong>Self Managed</strong></font></td>
							</tr>	
							<%} else if(x==1){
						 %>
								<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red"><strong>Cancelled</strong></font></td>
							</tr>	
						<% }
							j = x;
						locCount=j;
						j=0;
							%>
					</table>
					</td>

				</tr>

				<!-- iterator for local Conveyance Details end -->


			</table>
			</td>
		</tr>
		</s:if>
		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Advance Details</strong></td>
				</tr>


				<tr>
					<td width="100%">
					<table width="100%">

						<tr>
							<td width="20%" class="formtext" height="22">Advance Amount:</td>
							<td width="25%"><s:label name="trvlAdvAmt" theme="simple"
								value="%{trvlAdvAmt}" /></td>

							<td width="20%" class="formtext">Preferred Payment Mode:</td>
							<td width="25%"><s:label name="trvlPrefPayMode"
								theme="simple" value="%{trvlPrefPayMode}" /></td>

						</tr>

						<tr>

							<td width="20%" class="formtext" height="22">Expected
							Settlement Date :</td>
							<td width="25%"><s:label name="trvlExpSettleDate"
								theme="simple" value="%{trvlExpSettleDate}" /></td>
							<td width="20%" class="formtext"></td>
							<td width="25%"></td>

						</tr>

					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>


		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Identity Details </strong></td>
				</tr>


				<tr>
					<td width="100%">
					<table width="100%">


						<tr>
							<td width="20%" class="formtext" height="22">Id Proof:</td>
							<td width="25%"><s:label name="idProof" theme="simple"
								value="%{idProof}" /></td>

							<td width="20%" class="formtext">Id Number:</td>
							<td width="25%"><s:label name="idNumber" theme="simple"
								value="%{idNumber}" /></td>

						</tr>


						<tr>

							<td width="20%" class="formtext" height="22">Comments :</td>
							<td width="25%"><s:label name="idCmts" theme="simple"
								value="%{idCmts}" /></td>
							<td width="20%" class="formtext"></td>
							<td width="25%"></td>

						</tr>

					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit value="  Save" action="TravelMonitor_book" onclick="return saveValidate();"
						theme="simple" cssClass="save" /> <input type="button" value="Close" class="token"
						 onclick="return callClose();"/></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>
function callClose(){
window.close();
}
function saveValidate()
{
	try {
		var jourCount=<%=jourCount-1%>;
		var locConCount=<%=locCount-1%>;
		var accomCount=<%=accCount-1%>;
		try {
			for(i=1;i<=jourCount;i++)
			{
				if(document.getElementById('jourCancelFlag'+i).value != 'CC')
				{
					if(document.getElementById('jourNo'+i).value == '')
					{
						alert('Please enter bus/train/flight no');
						document.getElementById('jourNo'+i).focus();
						return false;
					}
					if(document.getElementById('ticketNo'+i).value == '')
					{
						alert('Please enter ticket no');
						document.getElementById('ticketNo'+i).focus();
						return false;
					}
					if(document.getElementById('jourCost'+i).value == '')
					{
						alert('Please enter cost');
						document.getElementById('jourCost'+i).focus();
						return false;
					}
				}
				if(document.getElementById('jourCancelFlag'+i).value == 'CC')
				{
					var cost = document.getElementById('jourCost'+i).value;
					var cancelCharge = document.getElementById('cancelChargeTvl'+i).value;
					var refundAmt =  document.getElementById('refundAmtTvl'+i).value;
					if(cancelCharge == '')
						cancelCharge = 0;
					if(refundAmt == 0)
						refundAmt = 0;
					if(eval(cost) < eval(cancelCharge))
					{
						alert("Cancellation charge for journey from "+document.getElementById('jourFrm'+i).value
						+" to "+document.getElementById('jourTo'+i).value + "\n should not exceed the actual cost." );
						document.getElementById('cancelChargeTvl'+i).focus();
						return false;
					}
					if(eval(cost) < eval(refundAmt))
					{
						alert("Refund Amount for journey from "+document.getElementById('jourFrm'+i).value
						+" to "+document.getElementById('jourTo'+i).value + " \n should not exceed the actual cost." );
						document.getElementById('refundAmtTvl'+i).focus();
						return false;
					}
					if(eval(cost) < eval(eval(cancelCharge)+eval(refundAmt)))
					{
						alert("The sum of cancellation charge and refund Amount for journey \n from "+document.getElementById('jourFrm'+i).value
						+" to "+document.getElementById('jourTo'+i).value + " should not exceed the actual cost." );
						document.getElementById('cancelChargeTvl'+i).focus();
						return false;
					}
				}
			}
		}catch(e)
		{
			//alert(e);
		}
		try 
		{
			for(i=1;i<=accomCount;i++)
			{
				if(document.getElementById('lodgCancelFlag'+i).value != 'CC')
				{
					if(document.getElementById('lodgeBookAmt'+i).value == '')
					{
						alert('Please enter booking amount');
						document.getElementById('lodgeBookAmt'+i).focus();
						return false;
					}
				}
				if(document.getElementById('lodgCancelFlag'+i).value == 'CC')
				{
					var cost = document.getElementById('lodgeBookAmt'+i).value;
					var cancelCharge = document.getElementById('cancelChargeAcc'+i).value;
					var refundAmt =  document.getElementById('refundAmtAcc'+i).value;
					if(cancelCharge == '')
						cancelCharge = 0;
					if(refundAmt == 0)
						refundAmt = 0;
					if(eval(cost) < eval(cancelCharge))
					{
						alert("Cancellation charge for accommodation in "+document.getElementById('lodgCity'+i).value
							+" \n should not exceed the booking amount." );
						document.getElementById('cancelChargeAcc'+i).focus();
						return false;
					}
					if(eval(cost) < eval(refundAmt))
					{
						alert("Refund Amount for accommodation in "+document.getElementById('lodgCity'+i).value
							+" \n should not exceed the booking amount." );
						document.getElementById('refundAmtAcc'+i).focus();
						return false;
					}
					if(eval(cost) < eval(eval(cancelCharge)+eval(refundAmt)))
					{
						alert("The sum of cancellation charge and refund Amount for \n accommodation in "+document.getElementById('lodgCity'+i).value
						+ " should not exceed the booking amount." );
						document.getElementById('cancelChargeAcc'+i).focus();
						return false;
					}
				}
			}
		}catch(e)
		{
			//alert(e);
		}
		
		try{
			for(i=1;i<=locConCount;i++)
			{
				if(document.getElementById('locCancelFlag'+i).value != 'CC')
				{
					if(document.getElementById('locConCost'+i).value == '')
					{
						alert('Please enter tariff cost');
						document.getElementById('locConCost'+i).focus();
						return false;
					}
				}
				if(document.getElementById('locCancelFlag'+i).value == 'CC')
				{
					var cost = document.getElementById('locConCost'+i).value;
					var cancelCharge = document.getElementById('cancelChargeLoc'+i).value;
					var refundAmt =  document.getElementById('refundAmtLoc'+i).value;
					if(cancelCharge == '')
						cancelCharge = 0;
					if(refundAmt == 0)
						refundAmt = 0;
					if(eval(cost) < eval(cancelCharge))
					{
						alert("Cancellation charge for local conveyance in "+document.getElementById('locConCity'+i).value
							+" \n should not exceed the tariff cost." );
						document.getElementById('cancelChargeLoc'+i).focus();
						return false;
					}
					if(eval(cost) < eval(refundAmt))
					{
						alert("Refund Amount for local conveyance in "+document.getElementById('locConCity'+i).value
							+" \n should not exceed the tariff cost." );
						document.getElementById('refundAmtLoc'+i).focus();
						return false;
					}
					if(eval(cost) < eval(eval(cancelCharge)+eval(refundAmt)))
					{
						alert("The sum of cancellation charge and refund Amount for local conyance in "+document.getElementById('locConCity'+i).value
						+ " \n should not exceed the tariff cost." );
						document.getElementById('cancelChargeLoc'+i).focus();
						return false;
					}
				}
			}
		}catch(e)
		{
			//alert(e);
		}
		if(!confirm("Are you sure want to save the records? \n\nclick ok to confirm and cancel to recheck."))
		{
			return false;
		}
		document.getElementById('paraFrm').target="main";
		window.close();
		return true;
		}catch(e)
		{
			return false;
		}
}

function viewPolicy(gradeId)
{
	var applDate = document.getElementById('hiddApplicationDate').value;
	window.open('TravelApplication_getTravelPolicy.action?gradeId='+gradeId+'&appDate='+applDate,'polA','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
}

function viewDetails(travelAppId,travelIndiAppId,typeFlag){  //AppId,AppCode,self/guest
 		typeFlag='S';
		if(document.getElementById('hiddEmpId').value == 0)
			typeFlag='G';
		window.open('TravelAppvr_callView.action?tmsTrvlId='+travelAppId+'&tmsTrvlIndiId='+travelIndiAppId+'&chkFlg='+typeFlag+'&deskFlag=true','viewDtlT','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
}

function uploadTicketFile(fldId)
{
	var path=document.getElementById('pathFld').value+"/TMS/<%=session.getAttribute("session_pool")%>/Tickets";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fldId,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function showTicketFile(fileName)
{
	document.getElementById('paraFrm').target ="";
	document.getElementById('paraFrm').action = "TravelMonitor_viewCV.action?fileName="+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target ="main";
}



</script>

