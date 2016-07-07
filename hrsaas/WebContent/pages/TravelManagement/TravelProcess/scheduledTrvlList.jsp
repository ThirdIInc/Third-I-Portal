
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelMonitor" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="appId" />
	<s:hidden name="appCode" />
	<s:hidden name="reqFrmDesk" />
	<s:hidden name="empId" />
	<s:hidden name="noData" />
	<!--Added by krishna for disabling the cancel button [Claim module]  -->
	<s:hidden name="clmFlag" />
	<%
		Object[][] status = null;

		try {
			status = (Object[][]) request.getAttribute("statObj");
		} catch (Exception e) {
			status = new Object[1][1];
		}
	%>


	<table width="98%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Travel
					Booking </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:if
						test="status!='cancellationDtls'">
						<s:if test="reqFrmDesk"></s:if>
						<s:else>

							<s:if test="clmFlag">
							</s:if>
							<s:else>
								<!-- ADD A CONDITION SPECIFIC TO DESK BELOW -->
								<input type="button" value="Cancel" class="cancel"
									onclick="cancelApp();" />
							</s:else>

						</s:else>

					</s:if> <input type="button" value="    Close    " class="token"
						onclick="return callClose();" /> <script>
						  function cancelApp(){
						  		
						  		var endDt = document.getElementById('paraFrm_tourEndDate').innerHTML.toLowerCase()
						  		//alert("endDt"+endDt);
						  		var currentTime = new Date();
								var month = currentTime.getMonth() + 1;
								var day = currentTime.getDate();
								var year = currentTime.getFullYear();
								var today=day+"-"+month+"-"+year ;
								//alert("today - "+today);
									strDate1 = endDt.split("-"); 
								starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
								//alert("starttime - "+starttime);
								strDate2 = today.split("-"); 
								endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
								//alert("endtime - "+endtime);
								if(endtime > starttime) 
								{ 
									alert("Travel End Date should be greater than or equal to Today");
									document.getElementById(fieldName).focus();
									return false;
								}	
							  	var conf = confirm('Do you really want to cancel \nthe scheduled travel application?');
							  	
							  	if(conf){
							  			if(!(document.getElementById('paraFrm_cancelComm').value=="")){
							  			
								  			var divDesc =document.getElementById('paraFrm_cancelComm').value;
											if (eval(divDesc.length) > 900)
										    {
										      alert('Description field accepts only 900 characters. Please remove ' + (divDesc.length - 900) + ' characters.');
										      return false; 
										  	}
							  			
										   	document.getElementById("paraFrm").target="main";
										   	document.getElementById('paraFrm').action="TravelApplication_updateAppStatus.action?appId="+document.getElementById('paraFrm_appId').value+"&appCode="+document.getElementById('paraFrm_appCode').value+"&appFor="+document.getElementById('paraFrm_trvlAppFor').value+"&reqFrmDesk="+document.getElementById('paraFrm_reqFrmDesk').value+"&cancelComm="+document.getElementById('paraFrm_cancelComm').value;
										   	document.getElementById('paraFrm').submit(); 
										   	self.close();
									   	}else{
									   		alert('Please enter cancellation comments.');
									   		document.getElementById('paraFrm_cancelComm').focus();
									   	}
							   	}
							   	
						  }
						 </script></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple"
				border="0">
				<s:if test="empId !=0">
					<tr>
						<td><strong>Employee Information</strong></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td><strong>Guest Information</strong></td>
					</tr>
				</s:else>
				<s:if test="empId !=0">
					<tr>
						<td>
						<table width="100%" border="0">
							<tr>
								<td width="5%" colspan="1" class="formtext" height="22">Employee
								Name:</td>
								<td colspan="3" width="95%"><s:property value="applicant" />
								<!--<s:label name="employeeName" theme="simple" value="%{empName}" />--></td>
							</tr>

							<tr>
								<td width="10%" class="formtext" height="22">Branch:</td>
								<td width="40%"><s:label name="brnchName" theme="simple"
									value="%{brnchName}" /></td>
								<td width="10%" class="formtext">Department:</td>
								<td width="40%"><s:label name="deptName" theme="simple"
									value="%{deptName}" /></td>
							</tr>


							<tr>

								<td width="20%" class="formtext" height="22">Designation:</td>
								<td width="25%"><s:label name="desgn" theme="simple"
									value="%{desgn}" /></td>
								<td width="20%" class="formtext">Application Date:</td>
								<td width="25%"><s:label name="applDate" theme="simple"
									value="%{applDate}" /></td>
							</tr>


							<tr>

								<td width="20%" class="formtext" height="22">Grade:</td>
								<td width="25%"><s:label name="grade" theme="simple"
									value="%{grade}" />&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<input
									type="button" value="View Travel Policy" class="token"
									onclick="viewPolicy('<s:property value="gradeId" />','<s:property value="applDate"/>');">
								</td>
								<td width="20%" class="formtext">Age:</td>
								<td width="25%"><s:label name="age" theme="simple"
									value="%{age}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22">Contact No:</td>
								<td width="25%"><s:label name="contactNo" theme="simple"
									value="%{contactNo}" /></td>
								<td width="20%" class="formtext"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td>
						<table width="100%">
							<tr>
								<td width="20%" colspan="1" class="formtext" height="22">Guest
								Name:</td>
								<td colspan="3"><s:property value="applicant" /> <!--<s:label name="employeeName" theme="simple" value="%{empName}" />--></td>
							</tr>

							<tr>
								<td width="20%" class="formtext">Application Date:</td>
								<td width="25%"><s:label name="applDate" theme="simple"
									value="%{applDate}" /></td>
								<td width="20%" class="formtext" height="22">Age:</td>
								<td width="25%"><s:label name="age" theme="simple"
									value="%{age}" /></td>
							</tr>

							<tr>
								<td width="20%" class="formtext" height="22">Contact No:</td>
								<td width="25%"><s:label name="contactNo" theme="simple"
									value="%{contactNo}" /></td>
								<td width="20%" class="formtext"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:else>
				<s:hidden name="empApplId" id="empApplId" />
				<s:hidden name="applicationId" id="applicationId" />
				<s:hidden name="gradeId" id="gradeId" />
				<s:hidden name="currentPage" id="currentPage" />
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
							<td colspan="1" width="25%"><s:label name="trvlAppFor"
								theme="simple" value="%{trvlAppFor}" /></td>
							<s:hidden name="trvlAppFor" />
							<td width="20%" class="formtext">Travel Id:</td>
							<td width="25%"><s:label name="travelId" theme="simple"
								value="%{travelId}" /></td>
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
							<td width="20%" class="formtext" height="22">Accomodation:</td>
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


		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Journey Details </strong></td>
				</tr>

				<!-- iterator for Journey Details   -->
				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td class="formth" colspan="1" width="10%">S.No</td>
							<td class="formth" colspan="1" width="25%">Source</td>
							<td class="formth" colspan="1" width="25%">Destination</td>
							<td class="formth" colspan="1" width="30%">Journey
							Mode-Class</td>
							<td class="formth" colspan="1" width="10%">Travel Date</td>
							<td class="formth" colspan="1" width="10%">Time(HH24:MI)</td>
							<td class="formth" colspan="1" width="10%">Bus/Train/Flight
							No<font color="red">*</font></td>
							<td class="formth" colspan="1" width="10%">Ticket Number<font
								color="red">*</font></td>
							<td class="formth" colspan="1" width="10%">Cost(Rs.)<font
								color="red">*</font></td>
							<td class="formth" width="10%"><label class="set"
								name="dtls" id="dtls" ondblclick="callShowDiv(this);"><%=label.get("dtls")%></label>
							</td>
							<td class="formth" width="10%"><label id="details">File
								Download</label></td>
							<s:if test="status=='cancellationDtls'">
								
								<td class="formth" width="10%"><label id="cancel.charge">Cancellation
								Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt">Refund
								Amount</label></td>
								<td class="formth" width="10%"><label id="comments"><label
									class="set" name="IdComments" id="IdComments"
									ondblclick="callShowDiv(this);"><%=label.get("IdComments")%></label></label></td>
							</s:if>
						</tr>
						<%!int i = 0;%>
						<%
						int k = 1;
						%>

						<s:iterator value="travelJourDtl">
							<tr>
								<s:hidden name="JournDtlId" />
								<s:hidden name="jourClsId" />
								<td class="sortableTD"><%=k%></td>
								<td class="sortableTD" colspan="1"><s:property
									value="jourFrm" />&nbsp;</td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="jourTo" />&nbsp;</td>
								<td class="sortableTD"><s:property value="JourModeCls" />&nbsp;</td>
								<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
									value="jourDate" /><s:hidden name="jourDate" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="jourTime" /><s:hidden name="jourTime" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="jourNo" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="ticketNo" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="jourCost" />&nbsp;</td>
								<td class="sortableTD" width="10%"><textarea rows="1"
									cols="10" id="paraFrm_trvlDtls<%=k %>"><s:property
									value="jourDetails" /></textarea> <!--
									<s:textarea name="accDetails" theme="simple" rows="1" cols="10" onclick="textCounter(this,295);"/>
									--> <img src="../pages/images/zoomin.gif" class="iconImage"
									height="12" align="absmiddle" width="12"
									onclick="callWindow('paraFrm_trvlDtls<%=k%>','dtls','','paraFrm_descCnt<%=k %>',100);">
								<td class="sortableTD"><a style="cursor: pointer;"
										onclick="showTicketFile('<s:property value="uploadFileTvl" />');"><s:property
										value="uploadFileTvl" /></a>&nbsp;</td>
								<input type="hidden" id="paraFrm_descCnt<%=k %>"></td>
								<s:if test="status=='cancellationDtls'">
									
									<td class="sortableTD" width="10%"><s:property
										value="cancelChargeTvl" />&nbsp;</td>
									<td class="sortableTD" width="10%"><s:property
										value="refundAmtTvl" />&nbsp;</td>
									<td class="sortableTD" width="10%"><textarea rows="1"
										cols="10" id="paraFrm_commentsTvl<%=k %>"><s:property
										value="commentsTvl" /></textarea> <img src="../pages/images/zoomin.gif"
										class="iconImage" height="12" align="absmiddle" width="12"
										onclick="callWindow('paraFrm_commentsTvl<%=k%>','IdComments','','paraFrm_descCnt<%=k %>',100);">
									<input type="hidden" id="paraFrm_descCnt<%=k %>"></td>
								</s:if>
							</tr>
							<%
							k++;
							%>
						</s:iterator>
						<%
						if (String.valueOf(status[0][0]).equals("CN")) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">Self Managed</font></td>
						</tr>

						<%
						} else {
						%>
						<%
						if (k == 1) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
						<%
							}
							}
							i = k;
						%>

					</table>
					</td>
				</tr>
				<!-- iterator for journey Details end -->
			</table>
			</td>
		</tr>

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
							<td class="formth" colspan="1" width="6%">S.No</td>
							<td class="formth" colspan="1" width="15%">Hotel Type</td>
							<td class="formth" colspan="1" width="15%">Room Type</td>
							<td class="formth" colspan="1" width="15%">City</td>
							<td class="formth" colspan="1" width="15%">Address</td>
							<td class="formth" colspan="1" width="10%">From Date</td>
							<td class="formth" colspan="1" width="8%">From Time(HH24:MI)</td>
							<td class="formth" colspan="1" width="10%">To Date</td>
							<td class="formth" colspan="1" width="8%">To Time(HH24:MI)</td>
							<td class="formth" colspan="1" width="6%">Booking Amount <font
								color="red">*</font></td>
							
							<td class="formth" width="10%">Details</td>
							<td class="formth" width="10%"><label id="details">File
								Download</label></td>
							<s:if test="status=='cancellationDtls'">
								
								<td class="formth" width="10%"><label id="cancel.charge1">Cancellation
								Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt1">Refund
								Amount</label></td>
								<td class="formth" width="10%"><label id="comments1"><label
									class="set" name="IdComments" id="IdComments1"
									ondblclick="callShowDiv(this);"><%=label.get("IdComments")%></label></label></td>
							</s:if>
						</tr>
						<%!int c = 0;%>
						<%
						int y = 1;
						%>



						<s:iterator value="travelLodgDtl">
							<tr>
								<s:hidden name="lodgId" />
								<s:hidden name="lodgDtlId" />
								<td class="sortableTD" width="6%"><%=y%></td>
								<td class="sortableTD" colspan="1"><s:property
									value="lodgHotel" />&nbsp;</td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:property
									value="lodgRoom" />&nbsp;</td>
								<td class="sortableTD" width="15%"><s:property
									value="lodgCity" />&nbsp;</td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:property
									value="lodgPreLoc" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="lodgFrmDate" />&nbsp;</td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="lodgFrmTime" />&nbsp;</td>
								<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
									value="lodgToDate" />&nbsp;</td>
								<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
									value="lodgToTime" />&nbsp;</td>
								<td class="sortableTD" width="6%" nowrap="nowrap"><s:property
									value="lodgBookAmt" />&nbsp;</td>
								<td class="sortableTD" width="10%"><textarea
									id="paraFrm_accDetails<%=y%>" rows="1" cols="10"><s:property
									value="accDetails" /></textarea> <img src="../pages/images/zoomin.gif"
									class="iconImage" height="12" align="absmiddle" width="12"
									onclick="callWindow('paraFrm_accDetails<%=y%>','dtls','','paraFrm_descCnt<%=y %>',100);">
								<input type="hidden" id="paraFrm_descCnt<%=y %>"></td>
								<td class="sortableTD"><a style="cursor: pointer;"
										onclick="showTicketFile('<s:property value="uploadFileAcc" />');"><s:property
										value="uploadFileAcc" /></a>&nbsp;</td>
								<s:if test="status=='cancellationDtls'">
									
									<td class="sortableTD"><s:property value="cancelChargeAcc" />&nbsp;</td>
									<td class="sortableTD"><s:property value="refundAmtAcc" />&nbsp;</td>
									<td class="sortableTD"><textarea rows="1" cols="10"
										id="paraFrm_commentsAcc<%=y %>"><s:property
										value="commentsAcc" /></textarea> <img src="../pages/images/zoomin.gif"
										class="iconImage" height="12" align="absmiddle" width="12"
										onclick="callWindow('paraFrm_commentsAcc<%=y%>','IdComments1','','paraFrm_descCnt<%=y %>',100);">
									<input type="hidden" id="paraFrm_descCnt<%=y %>"></td>
								</s:if>


							</tr>
							<%
							y++;
							%>
						</s:iterator>
						<%
						if (String.valueOf(status[0][1]).equals("CN")) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">Self Managed</font></td>
						</tr>
						<%
						} else {
						%>
						<%
						if (y == 1) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
						<%
								}
								c = y;
						%>
						<%
						}
						%>
					</table>
					</td>
				</tr>
				<!-- iterator for lodging Details end -->
			</table>
			</td>
		</tr>

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
							<td class="formth" width="10%">S.No</td>
							<td class="formth" width="15%">City</td>
							<!--  <td class="formth" width="15%">Source</td>-->
							<td class="formth" width="10%">Travel Mode</td>
							<td class="formth" width="10%">Date</td>
							<td class="formth" width="10%">From Time(HH24:MI)</td>
							<td class="formth" width="10%">To Time(HH24:MI)</td>
							<td class="formth" width="10%">Tariff Cost <font color="red">*</font></td>
							<td class="formth" width="10%">Details</td>
							<td class="formth" width="10%"><label id="details">File
								Download</label></td>
							<s:if test="status=='cancellationDtls'">
								
								<td class="formth" width="10%"><label id="cancel.charge2">Cancellation
								Charge</label></td>
								<td class="formth" width="10%"><label id="refund.amt2">Refund
								Amount</label></td>
								<td class="formth" width="10%"><label id="comments2">Comments</label></td>
							</s:if>
						</tr>
						<%!int j = 0;%>
						<%
						int x = 1;
						%>
						<s:iterator value="travelLocConDtl">
							<tr>
								<s:hidden name="locConId" />
								<s:hidden name="locConDtlId" />
								<td class="sortableTD"><%=x%></td>
								<td class="sortableTD" colspan="1"><s:property
									value="locConCity" />&nbsp;</td>
								<!--  <td class="sortableTD"><s:property value="locConSource" />&nbsp;</td>-->
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="locConMode" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="locConDate" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="locConFrmTime" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="locConFrmTime" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap"><s:property
									value="locConCost" />&nbsp;</td>
								<td class="sortableTD" width="10%"><textarea
									id="paraFrm_locDetails<%=x%>" rows="1" cols="10"><s:property
									value="locDetails" /></textarea> <img src="../pages/images/zoomin.gif"
									class="iconImage" height="12" align="absmiddle" width="12"
									onclick="callWindow('paraFrm_locDetails<%=x%>','dtls','','paraFrm_descCnt<%=x %>',100);">
								<input type="hidden" id="paraFrm_descCnt<%=x %>"></td>
								<td class="sortableTD"><a style="cursor: pointer;"
										onclick="showTicketFile('<s:property value="uploadFileLoc" />');"><s:property
										value="uploadFileLoc" /></a>&nbsp;</td>
								<s:if test="status=='cancellationDtls'">
									
									<td class="sortableTD"><s:property value="cancelChargeLoc" />&nbsp;</td>
									<td class="sortableTD"><s:property value="refundAmtLoc" />&nbsp;</td>
									<td class="sortableTD"><textarea rows="1" cols="10"
										id="paraFrm_commentsLoc<%=x %>"><s:property
										value="commentsLoc" /></textarea> <img src="../pages/images/zoomin.gif"
										class="iconImage" height="12" align="absmiddle" width="12"
										onclick="callWindow('paraFrm_commentsLoc<%=x%>','comments2','','paraFrm_descCnt<%=x %>',100);">
									<input type="hidden" id="paraFrm_descCnt<%=x %>"></td>
								</s:if>
							</tr>
							<%
							x++;
							%>
						</s:iterator>
						<%
						if (String.valueOf(status[0][2]).equals("CN")) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">Self Managed</font></td>
						</tr>
						<%
						} else {
						%>
						<%
						if (x == 1) {
						%>
						<tr>
							<td width="100%" colspan="8" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
						<%
						}
						%>
						<%
						j = x;
						%>
						<%
						}
						%>
					</table>
					</td>
				</tr>
				<!-- iterator for local Conveyance Details end -->
			</table>
			</td>
		</tr>


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
							<td width="25%"><s:if test="%{trvlPrefPayMode!='Salary'}">
								<s:label name="trvlPrefPayMode" theme="simple"
									value="%{trvlPrefPayMode}" />
							</s:if> <s:else>

							</s:else></td>


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

						


						<s:if test="clmFlag">
						</s:if>
						<s:else>
							<tr>
								<td colspan="4" align="right"><a href="#"
									title="Click here" onclick="return showTrail();"><STRONG>Comments
								Trail</STRONG></a></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label id="canCom" name="canCom">Cancellation
								Comments</label> <font color="red">*</font>:</td>
								<!-- IF CANCELLATION DETAILS ARE VIEWED IN CANCELLED LIST SECTION -->
								<s:if test="status!='cancellationDtls'">
								<td width="25%">
									<s:textarea name="cancelComm" rows="3" cols="60" 
									onkeyup="callLength('descCnt');"/>
									<img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide'
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_cancelComm','canCom','','900','900');"></td>
					<td colspan="2" id='ctrlHide'>Remaining chars<s:textfield name="descCnt"
						readonly="true" size="5"></s:textfield></td>
						
						
								</s:if> <s:else>
								<td width="25%">
									<s:textarea name="cancelComm" rows="3" cols="60"
										readonly="true"></s:textarea>
										</td>
										<td width="20%" class="formtext"></td>
								<td width="25%"></td>
								</s:else>
								

							</tr>
						</s:else>
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
					<td width="78%" align="left"><s:if
						test="status!='cancellationDtls'">
						<s:if test="reqFrmDesk"></s:if>
						<s:else>

							<s:if test="clmFlag">
							</s:if>
							<s:else>
								<!-- ADD A CONDITION SPECIFIC TO DESK BELOW -->
								<input type="button" value="Cancel" class="cancel"
									onclick="cancelApp();" />
							</s:else>

						</s:else>

					</s:if> <input type="button" value="    Close    " class="token"
						onclick="return callClose();" /> <!-- ADD A CONDITION SPECIFIC TO DESK BELOW reqFrmDesk-->



					</td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>

function callLength(type){ 
	 if(type=='descCnt'){
		var cmt =document.getElementById('paraFrm_cancelComm').value;
		var remain = 900 - eval(cmt.length);
		document.getElementById('paraFrm_descCnt').value = remain;
			if(eval(remain)< 0){
		document.getElementById('paraFrm_cancelComm').style.background = '#FFFF99';
		
		}else document.getElementById('paraFrm_cancelComm').style.background = '#FFFFFF';
	}
}  	


function showTicketFile(fileName)
{
	document.getElementById('paraFrm').target ="_blank";
	document.getElementById('paraFrm').action = "TravelMonitor_viewCV.action?fileName="+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target ="main";
}



function callClose(){
window.close();
}

function saveValidate()
{
	try {
	var jourCount=<%=i-1%>;
	var locConCount=<%=j-1%>;
	var accomCount=<%=c-1%>;
	for(i=1;i<=jourCount;i++)
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
	for(i=1;i<=locConCount;i++)
	{
		if(document.getElementById('locConCost'+i).value == '')
		{
			alert('Please enter cost');
			document.getElementById('locConCost'+i).focus();
			return false;
		}
	}
	for(i=1;i<=accomCount;i++)
	{
		if(document.getElementById('lodgeBookAmt'+i).value == '')
		{
			alert('Please enter cost');
			document.getElementById('lodgeBookAmt'+i).focus();
			return false;
		}
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
 
function viewPolicy(gradeId,applDate)
{
		window.open('TravelApplication_getTravelPolicy.action?gradeId='+gradeId+'&appDate='+applDate,'polStr','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=yes');
}
</script>


<script>
 	function showTrail(){
		    var applId=document.getElementById('paraFrm_appId').value;
			var applCode=document.getElementById('paraFrm_appCode').value;		
		  	var wind = window.open('','win','width=600,height=300,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100','location=no');	 
			document.getElementById('paraFrm').target = "wind";
			document.getElementById('paraFrm').action = "TravelAppvr_showCmtsTrail.action?applicationId="+applId+"&applicationCode="+applCode;
			document.getElementById('paraFrm').submit();
		    document.getElementById('paraFrm').target = "main";
		
  	}
	</script>

