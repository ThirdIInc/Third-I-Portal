
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelDesk" validate="true" id="paraFrm" theme="simple">

	<table width="98%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<s:hidden name="path" value="%{getText('data_path')}" id="pathFld"/>
		<s:hidden name="tmsTrvlId" />
		<s:hidden name="tmsTrvlIndiId" />
		<s:hidden name="tmsChkTypeFlg" />
		<s:hidden name="selfCheck" />
		<s:hidden name="tmsApprvrLevel" />
		<s:hidden name="navStatus" />
		<s:hidden name="statusSave" />
		<s:hidden name="apprvrLevel" />
		<s:hidden name="empIdSubmit" />
		<s:hidden name="pen" />
		<s:hidden name="Apprvd" />
		<s:hidden name="Regcted" />
		<s:hidden name="Retrned" />
		<s:hidden name="showStatflag" />
		<s:hidden name="pendingCase" />
		<s:hidden name="pendingItrCase" />
		<s:hidden name="keepInfFlag" />
		<s:hidden name="gradId" />
		<s:hidden name="cmtlableName" />
		<s:hidden name="initIdSubmit" />
		<!-- 		
		flags use full for Travel Desk		
		 -->
		<s:hidden name="deskFlag" />
		<!-- for Travel Policy view purpose -->
		<s:hidden name="appDate" />
		<s:hidden name="startDate" />
		<s:hidden name="endDate" />
		<!-- For Policy Violation -->
		<s:hidden name="polViolationStr" />
		<s:hidden name="trvlAppStatus" />
		<s:hidden name="schFlag" />
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
						
						<td width="93%" class="txt"><strong class="formhead">Booking Details</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
					<tr>						
						<td width="78%" align="left"><s:submit value="  Save" action="TravelDesk_book" onclick="return saveValidate();"
						theme="simple" cssClass="save" />&nbsp;<input type="button"
							value="  Close" theme="simple" onclick=" callClose();"
							class="token" /></td>
					</tr>
				
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<s:if test="selfTrvlFlag">
					<tr>
						<td><strong><label class="set" name="trvl.empInfo"
							id="trvl.empInfo" ondblclick="callShowDiv(this);"><%=label.get("trvl.empInfo")%></label></strong></td>
					</tr>
					<tr>
						<td>
						<table width="100%">



							<s:if test='%{tmsChkTypeFlg=="S"}'>


								<s:if test='%{selfCheck=="Y"}'>

									<tr>
										<td width="20%" colspan="1" class="formtext" height="22"><label
											class="set" name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
										<td colspan="3">
										 <s:label
											name="employeeName" theme="simple" value="%{employeeName}" /></td>
									</tr>

								</s:if>
								<s:else>

									<tr>
										<td width="20%" colspan="1" class="formtext" height="22"><label
											class="set" name="init.name" id="init.name"
											ondblclick="callShowDiv(this);"><%=label.get("init.name")%></label>:</td>
										<td colspan="1"><s:label
											name="employeeName" theme="simple" value="%{employeeName}" /></td>
										<td colspan="1" class="formtext" height="22"><label
											class="set" name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
										<td colspan="1"><s:label name="othersName" theme="simple"
											value="%{othersName}" /></td>
									</tr>

								</s:else>

							</s:if>
							<s:else>

								<tr>
									<td width="20%" colspan="1" class="formtext" height="22"><label
										class="set" name="trvl.initrName" id="trvl.initrName"
										ondblclick="callShowDiv(this);"><%=label.get("trvl.initrName")%></label>:</td>
									<td colspan="1"> <s:label
										name="employeeName" theme="simple" value="%{employeeName}" /></td>
									<td colspan="1" class="formtext" height="22"><label
										class="set" name="employee" id="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
									<td colspan="1"><s:label name="othersName" theme="simple"
										value="%{othersName}" /></td>
								</tr>
							</s:else>

							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="branch" id="branch"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
								<td width="25%"><s:label name="brnchName" theme="simple"
									value="%{brnchName}" /></td>

								<td width="20%" class="formtext"><label class="set"
									name="department" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
								<td width="25%"><s:label name="deptName" theme="simple"
									value="%{deptName}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="designation" id="designation"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
								<td width="25%"><s:label name="desgn" theme="simple"
									value="%{desgn}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="appl.date" id="appl.date6"
									ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>:</td>
								<td width="25%"><s:label name="applDateSubmit" theme="simple"
									value="%{applDateSubmit}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.status" id="trvl.status"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.status")%></label>:</td>
								<td width="25%"><s:label name="statusView" theme="simple"
									value="%{statusView}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
								<td width="25%"><s:label name="grade" theme="simple"
									value="%{grade}" />&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<input
									type="button" value="View Travel Policy" class="token"
									onclick="viewPolicy(document.getElementById('paraFrm_gradId').value);"></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.age" id="trvl.age"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.age")%></label>:</td>
								<td width="25%"><s:label name="age" theme="simple"
									value="%{age}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.conNo" id="trvl.conNo"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.conNo")%></label>:</td>
								<td width="25%"><s:label name="contactNo" theme="simple"
									value="%{contactNo}" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
				<s:if test="guestTrvlFlag">
					<tr>
						<td><strong><label class="set" name="trvl.guestInfo"
							id="trvl.guestInfo" ondblclick="callShowDiv(this);"><%=label.get("trvl.guestInfo")%></label></strong></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<tr>
								<td width="20%" colspan="1" class="formtext" height="22"><label
									class="set" name="init.name" id="init.name8"
									ondblclick="callShowDiv(this);"><%=label.get("init.name")%></label>
								:</td>
								<td colspan="3"><s:label
									name="employeeNameG" theme="simple" value="%{employeeNameG}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.guestName" id="trvl.guestName"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.guestName")%></label>:</td>
								<td width="25%"><s:label name="guestName" theme="simple"
									value="%{guestName}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="appl.date" id="appl.date3"
									ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>:</td>
								<td width="25%"><s:label name="applDateG" theme="simple"
									value="%{applDateG}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.status" id="trvl.status1"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.status")%></label>:</td>
								<td width="25%"><s:label name="statusViewG" theme="simple"
									value="%{statusViewG}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.age" id="trvl.age1" ondblclick="callShowDiv(this);"><%=label.get("trvl.age")%></label>:</td>
								<td width="25%"><s:label name="ageG" theme="simple"
									value="%{ageG}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.conNo" id="trvl.conNo"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.conNo")%></label>:</td>
								<td width="25%"><s:label name="contactNoG" theme="simple"
									value="%{contactNoG}" /></td>
								<td width="20%" class="formtext"></td>
								<td width="25%"></td>
							</tr>
						</table>
						</td>
					</tr>

				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<s:if test="tourFlag">
					<tr>
						<td><strong><label class="set" name="trvl.tourDtls"
							id="trvl.tourDtls" ondblclick="callShowDiv(this);"><%=label.get("trvl.tourDtls")%></label></strong></td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%">
							<tr>
								<td width="20%" colspan="1" class="formtext" height="22"><label
									class="set" name="trvl.trvlAppFor" id="trvl.trvlAppFor"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlAppFor")%></label>:</td>
								<td width="25%" colspan="1"><s:label name="trvlAppFor"
									theme="simple" value="%{trvlAppFor}" /></td>

								<td width="20%" class="formtext"><label class="set"
									name="trvl.trvlId" id="trvl.trvlId"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlId")%></label>:</td>
								<td width="25%"><s:label name="travelReqId" theme="simple"
									value="%{travelReqId}" /></td>

							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.reqName" id="trvl.reqName5"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.reqName")%></label>:</td>
								<td width="25%"><s:label name="trvlReqName" theme="simple"
									value="%{trvlReqName}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.trvlPurpose" id="trvl.trvlPurpose"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlPurpose")%></label>:</td>
								<td width="25%"><s:label name="trvlPurpose" theme="simple"
									value="%{trvlPurpose}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.accomodation" id="trvl.accomodation"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.accomodation")%></label>:</td>
								<td width="25%"><s:label name="trvlAccom" theme="simple"
									value="%{trvlAccom}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.arrngmnt" id="trvl.arrngmnt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.arrngmnt")%></label>:</td>
								<td width="25%"><s:label name="trvlArrngmt" theme="simple"
									value="%{trvlArrngmt}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.locConv" id="trvl.locConv"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConv")%></label>:</td>
								<td width="25%"><s:label name="trvlLocCon" theme="simple"
									value="%{trvlLocCon}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.trvlType" id="trvl.trvlType"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.trvlType")%></label>:</td>
								<td width="25%"><s:label name="trvlType" theme="simple"
									value="%{trvlType}" /></td>
							</tr>
							<tr>
								<td width="20%" class="formtext" height="22"><label
									class="set" name="trvl.tourStrtDt" id="trvl.tourStrtDt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.tourStrtDt")%></label>:</td>
								<td width="25%"><s:label name="tourStrtDate" theme="simple"
									value="%{tourStrtDate}" /></td>
								<td width="20%" class="formtext"><label class="set"
									name="trvl.tourEndDt" id="trvl.tourEndDt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.tourEndDt")%></label>:</td>
								<td width="25%"><s:label name="tourEndDate" theme="simple"
									value="%{tourEndDate}" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>

			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">


				<s:if test="jourDtlFlag">
					<tr>
						<td><strong>Journey Details</strong></td>
					</tr>
					<!-- iterator for Journey Details   -->
					<tr>
						<td width="100%" colspan="11">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">
							<tr>
								<td class="formth" colspan="1" width="10" nowrap="nowrap"><strong><label
									class="set" name="sr.No" id="sr.No3"
									ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.frmPlace" id="trvl.frmPlace"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.frmPlace")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.toPlace" id="trvl.toPlace"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.toPlace")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.jourMdClass" id="trvl.jourMdClass"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.jourMdClass")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.jourDt" id="trvl.jourDt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.jourDt")%></label></strong></td>
								<td class="formth" colspan="1"  nowrap="nowrap"><strong><label
									class="set" name="trvl.jourTime" id="trvl.jourTime"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.jourTime")%></label>(HH24:MI)</strong></td>
									
									
									<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong>Bus/Train/Flight No <font color="red"> * </font></strong></td>
									
									<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong>Ticket Number <font color="red"> * </font></strong></td>
									
									<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong>Cost(Rs.)<font color="red"> * </font> </strong></td>
									
									<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong><label id="details">Details</label></strong></td>
									<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong>Upload</strong></td>
							</tr>
							<s:if test="!travelJourDtl">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>
							
							<%!int i = 0,jourCount;%>
							<%
							int k = 1;
							%>
							<s:iterator value="travelJourDtl">

								<tr id="jour_<%=k %>">
									<s:hidden name="JournDtlId" />
									<s:hidden name="JournDtlCode" />
									<s:hidden name='<%="violateFlag_"+k %>' value='%{violateFlag}'
										id='<%="violateFlag_"+k %>' />
									<td class="sortableTD"><%=k%></td>
									<td class="sortableTD" colspan="1"><s:property
										value="jourFrm" /><s:hidden name="jourFrm"/>&nbsp;</td>
									<td class="sortableTD"><s:property value="jourTo" /><s:hidden name="jourTo"/>&nbsp;</td>
									<td class="sortableTD"><s:property value="JourModeCls" /><s:hidden name="JourModeCls"/>&nbsp;</td>
									<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
										value="jourDate" /><s:hidden name="jourDate"/>&nbsp;</td>
									<td class="sortableTD" width="30" nowrap="nowrap"><s:property
										value="jourTime" /><s:hidden name="jourTime"/>&nbsp;</td>
										<td class="sortableTD"><input type="text" name="jourNo" id="jourNo<%=k %>" maxlength="20"
									 value='<s:property value="jourNo"/>' size="15"  />&nbsp;</td>
										<td class="sortableTD"><input type="text" name="ticketNo" " id="ticketNo<%=k %>" maxlength="20"
									 value='<s:property value="ticketNo"/>' size="15" />&nbsp;</td>
										<td class="sortableTD"><input type="text" name="jourCost" " id="jourCost<%=k %>" maxlength="7"
									 value='<s:property value="jourCost"/>' onkeypress="return numbersWithDot();" 
									 size="15%" />&nbsp;</td>
										<td class="sortableTD"><textarea name="jourDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,295);"
																	 value='<s:property value="jourDetails" />' id="details1_<%=k%>"></textarea>&nbsp;
																	 
									 <input type="hidden" name="remCount" id='remCount1_<%=k%>' >
									 <img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details1_<%=k%>','details','','remCount1_<%=k%>',250);"></td>
									<td class="sortableTD" align="center" nowrap="nowrap"><input type="text" name="uploadFileName"	id="uploadFileName_<%=k%>"
									value='<s:property value="uploadFileName" />' size="10" readonly="true" /> 
									<input name="Browse" type="button"	class="token" value="Upload" onclick="uploadFile('uploadFileName_<%=k%>');" />&nbsp;</td>
								</tr>
								<script>
								//alert(document.getElementById('violateFlag_<%=k%>').value);
										if(document.getElementById('violateFlag_<%=k%>').value == 'Y')
										{
									 		document.getElementById('jour_<%=k%>').style.background='#F08080';
									 	}
										</script>


								<%
								k++;
								%>
							</s:iterator>
							<%if(k==1) {%>
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						<%
						}
						i = k;
						jourCount=i;
						i=0;
							%>
						</table>
						</td>
					</tr>
					<!-- iterator for journey Details end -->
				</s:if>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<s:if test="lodgDtlFlag">
					<tr>
						<td><strong>Accommodation Details</strong></td>
					</tr>
					<!-- iterator for lodging Details   -->
					<tr>
						<td width="100%" colspan="10">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">
							<tr>
								<td class="formth" colspan="1" width="10" nowrap="nowrap"><strong><label
									class="set" name="sr.No" id="sr.No4"
									ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.lodgHotelType" id="trvl.lodgHotelType"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.lodgHotelType")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.lodgRoomType" id="trvl.lodgRoomType"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.lodgRoomType")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.lodgCity" id="trvl.lodgCity"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.lodgCity")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.lodgPreffLoc" id="trvl.lodgPreffLoc"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.lodgPreffLoc")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConFrmDt" id="trvl.locConFrmDt2"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConFrmDt")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConFrmTim" id="trvl.locConFrmTim2"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConFrmTime")%></label>(HH24:MI)</strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConToDt" id="trvl.locConToDt2"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConToDt")%></label></strong></td>
								<td class="formth" colspan="1" width="22" nowrap="nowrap"><strong><label
									class="set" name="trvl.locConToTime" id="trvl.locConToTime2"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConToTime")%></label>(HH24:MI)</strong></td>
									
								<td class="formth" colspan="1" width="22" nowrap="nowrap"><strong>Booking Amount <font color="red"> * </font></strong></td>
									
								<td class="formth" colspan="1" width="22" nowrap="nowrap"><strong>Details</strong></td>
								<td class="formth" colspan="1" width="20" nowrap="nowrap"><strong>Upload</strong></td>
							</tr>
							<s:if test="!travelLodgDtl">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>
							<%!int c = 0,accCount=0;%>
							<%
							int y = 1;
							%>
							<s:iterator value="travelLodgDtl">
								<tr id="lodg_<%=y %>">

									<s:hidden name="lodgId" />
									<s:hidden name="hotelTypeId" />
									<s:hidden name="lodgCode" />
									<s:hidden name='<%="violateFlagRm_"+y %>'
										value='%{violateFlagRm}' id='<%="violateFlagRm_"+y %>' />
									<td class="sortableTD"><%=y%></td>
									<td class="sortableTD" colspan="1"><s:property
										value="lodgHotel" /><s:hidden name="lodgHotel"/>&nbsp;</td>
									<td class="sortableTD" width="8%" nowrap="nowrap"><s:property
										value="lodgRoom" /><s:hidden name="lodgRoom"/>&nbsp;</td>
									<td class="sortableTD"><s:property value="lodgCity" /><s:hidden name="lodgCity"/>&nbsp;</td>
									<td class="sortableTD" width="12%" nowrap="nowrap"><s:property
										value="lodgPreLoc" /><s:hidden name="lodgPreLoc"/>&nbsp;</td>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="lodgFrmDate" /><s:hidden name="lodgFrmDate"/>&nbsp;</td>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="lodgFrmTime" /><s:hidden name="lodgFrmTime"/>&nbsp;</td>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="lodgToDate" /><s:hidden name="lodgToDate"/>&nbsp;</td>
									<td class="sortableTD" width="22"><s:property
										value="lodgToTime" /><s:hidden name="lodgToTime"/>&nbsp;</td>
									<td class="sortableTD" width="22"><input type="text" size="6%" " id="lodgeBookAmt<%=y %>" maxlength="7"
								name="lodgBookAmt" value='<s:property value="lodgBookAmt"/>'  onkeypress="return numbersWithDot();"/>&nbsp;</td>
									<td class="sortableTD" width="22"><s:textarea name="accDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,295);" id='<%="details2_"+y%>'/>&nbsp;
									<input type="hidden" name="remCount" id='remCount2_<%=y%>' >
									 <img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details2_<%=y%>','details','','remCount2_<%=y%>',250);"></td>
									<td class="sortableTD" align="center" nowrap="nowrap"><input type="text" name="uploadLodgeFileName"	id="uploadLodgeFileName_<%=y%>"
									value='<s:property value="uploadLodgeFileName" />' size="10" readonly="true" /> 
									<input name="Browse" type="button"	class="token" value="Upload" onclick="uploadFile('uploadLodgeFileName_<%=y%>');" />&nbsp;</td>
								</tr>



								<script>
								
										if(document.getElementById('violateFlagRm_<%=y%>').value == 'Y')
										{
									 		document.getElementById('lodg_<%=y%>').style.background='#F08080';
									 	}
										</script>

								<%
								y++;
								%>
							</s:iterator>
							<%if(y==1) {%>
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						<%}
							c = y;
							accCount=c;
							c=0;
							%>
						</table>
						</td>
					</tr>
					<!-- iterator for lodging Details end -->
				</s:if>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" align="center" class="formbg" theme="simple">
				<s:if test="locConDtlFlag">
					<tr>
						<td><strong>Local Conveyance Details	</strong></td>
					</tr>
					<!-- iterator for local conveyance Details   -->
					<tr>
						<td width="100%" colspan="8">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">
							<tr>
								<td class="formth" width="10" nowrap="nowrap" colspan="1"><strong><label
									class="set" name="sr.No" id="sr.No1"
									ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConCity" id="trvl.locConCity"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConCity")%></label></strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConSource" id="trvl.locConSource"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConSource")%></label></strong></td>


								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.medium" id="trvl.medium"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.medium")%></label></strong></td>

								<td class="formth" colspan="1" nowrap="nowrap"><strong><label
									class="set" name="trvl.locConFrmDt" id="trvl.locConFrmDt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConFrmDt")%></label></strong></td>
								<td class="formth" colspan="1" width="22" nowrap="nowrap"><strong><label
									class="set" name="trvl.locConFrmTim" id="trvl.locConFrmTim"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConFrmTime")%></label>(HH24:MI)</strong></td>
								<td class="formth" colspan="1"><strong><label
									class="set" name="trvl.locConToDt" id="trvl.locConToDt"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConToDt")%></label></strong></td>
								<td class="formth" colspan="1" width="22" nowrap="nowrap"><strong><label
									class="set" name="trvl.locConToTime" id="trvl.locConToTime"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.locConToTime")%></label>(HH24:MI)</strong></td>
									
								<td class="formth" width="10%">Tariff Cost<font color="red"> * </font></td>
								<td class="formth" width="10%">Details</td>
								<td class="formth" width="10%">Upload</td>
								
							</tr>
							<s:if test="!travelLocConDtl">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>
							<%!int j = 0,locCount=0;%>
							<%
							int x = 1;
							%>
							<s:iterator value="travelLocConDtl">
								<tr>
									<s:hidden name="locConId" />
									<s:hidden name="locConCode" />
									<td class="sortableTD" width="10"><%=x%></td>
									<td class="sortableTD" colspan="1"><s:property
										value="locConCity" /><s:hidden name="locConCity"/>&nbsp;</td>
									<td class="sortableTD"><s:property value="locConSource" /><s:hidden name="locConSource"/>&nbsp;</td>
									<td class="sortableTD"><s:property value="medium" /><s:hidden name="medium"/>&nbsp;</td>
									<td class="sortableTD" width="10%"><s:property
										value="locConFrmDate" /><s:hidden name="locConFrmDate"/>&nbsp;</td>
									<td class="sortableTD" nowrap="nowrap"><s:property
										value="locConFrmTime" /><s:hidden name="locConFrmTime"/>&nbsp;</td>
									<td class="sortableTD" width="10%"><s:property
										value="locConToDate" /><s:hidden name="locConToDate"/>&nbsp;</td>
									<td class="sortableTD" width="22"><s:property
										value="locConToTime" /><s:hidden name="locConToTime"/>&nbsp;</td>
									<td class="sortableTD" width="22"><input type="text" name="locConCost" size="15%"  id="locConCost<%=x %>" maxlength="7"
								value='<s:property value="locConCost"/>' onkeypress="return numbersWithDot();" >&nbsp;</td>
									<td class="sortableTD" width="22"><s:textarea name="locDetails" theme="simple" rows="1" cols="10" onkeyup="textCounter(this,295);"
								id='<%="details3_"+x%>'/>&nbsp;
								<input type="hidden" name="remCount" id='remCount3_<%=x%>' >
								 <img src="../pages/images/zoomin.gif" class="iconImage" height="10" align="absmiddle" width="10"
									onclick="callWindow('details3_<%=x%>','details','','remCount3_<%=x%>',250);"></td>
								<td class="sortableTD" align="center" nowrap="nowrap"><input type="text" name="uploadLocFileName"	id="uploadLocFileName_<%=x%>"
									value='<s:property value="uploadLocFileName" />' size="10" readonly="true" /> 
									<input name="Browse" type="button"	class="token" value="Upload" onclick="uploadFile('uploadLocFileName_<%=x%>');" />&nbsp;</td>
										
								</tr>
								<%
								x++;
								%>
							</s:iterator>
							<%if(x==1) { %>
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
							<%} %>
						<%
							j = x;
						locCount=j;
						j=0;
							%>
						</table>
						</td>
					</tr>
					<!-- iterator for local Conveyance Details end -->
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong><label class="set" name="trvl.advDtls"
						id="trvl.advDtls" ondblclick="callShowDiv(this);"><%=label.get("trvl.advDtls")%></label></strong></td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvl.advAmt" id="trvl.advAmt"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.advAmt")%></label>:</td>
							<td width="25%"><s:label name="trvlAdvAmt" theme="simple"
								value="%{trvlAdvAmt}" /></td>

							<td width="20%" class="formtext"><label class="set"
								name="trvl.preffPayMd" id="trvl.preffPayMd"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.preffPayMd")%></label>:</td>
							<td width="25%"><s:label name="trvlPrefPayMode"
								theme="simple" value="%{trvlPrefPayMode}" /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvl.expSettleDt" id="trvl.expSettleDt"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.expSettleDt")%></label>
							:</td>
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
					<td><strong><label class="set" name="trvl.idDtls"
						id="trvl.idDtls" ondblclick="callShowDiv(this);"><%=label.get("trvl.idDtls")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvl.idProof" id="trvl.idProof"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.idProof")%></label>:</td>
							<td width="25%"><s:label name="idProof" theme="simple"
								value="%{idProof}" /></td>
							<td width="20%" class="formtext"><label class="set"
								name="trvl.idNo" id="trvl.idNo" ondblclick="callShowDiv(this);"><%=label.get("trvl.idNo")%></label>:</td>
							<td width="25%"><s:label name="idNumber" theme="simple"
								value="%{idNumber}" /></td>
						</tr>
						<tr>

							<td width="20%" class="formtext" height="22"><label
								class="set" name="trvl.cmts" id="trvl.cmts3"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.cmts")%></label>
							:</td>
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
						
						<td width="78%" align="left"><s:submit value="  Save" action="TravelDesk_book" onclick="return saveValidate();"
						theme="simple" cssClass="save" />&nbsp;<input type="button"
							value="  Close" theme="simple" onclick=" callClose();"
							class="token" /></td>
					</tr>
				
				



			</table>
			</td>
		</tr>
		
		<tr>

				<td>
				<table width="100%">
					<tr>
						<td width="8%"><b>Note : -</b></td>
						<td width="92%"><input type="text" disabled="disabled"
							style="background-color: #F08080; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates policy violated records.</td>
					</tr>
					

				</table>
				</td>

			</tr>
		

	</table>
</s:form>

<script>

/*function uploadFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/ticketBooked";
		//window.open('../pages/common/uploadFile.jsp?path='+path,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 document.getElementById('paraFrm').target="main";
		} */
		
function uploadFile(fldId)
{
try{
	var path=document.getElementById('pathFld').value+"/TMS/<%=session.getAttribute("session_pool")%>/Tickets";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fldId,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}catch(e)
	{
	//alert(e);
	}
}

/*function showTicketFile(fileName)
{
	document.getElementById('paraFrm').target ="_blank";
	document.getElementById('paraFrm').action = "TravelMonitor_viewCV.action?fileName="+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target ="main";
}*/
		
		
		
		
		
		
		


function callClose()
{
window.close();

}

  function viewPolicy(gradId){		     
					win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
						document.getElementById("paraFrm").target="win";
						document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
						document.getElementById("paraFrm").submit();	
						document.getElementById("paraFrm").target="main"; 
   }
   
   
  function saveValidate(){
  //alert("save");
  try {
		var jourCount=<%=jourCount-1%>;
		var locConCount=<%=locCount-1%>;
		var accomCount=<%=accCount-1%>;
		try {
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
		}catch(e)
		{
		}
		
		
		
		try 
		{
			for(i=1;i<=accomCount;i++)
			{
				if(document.getElementById('lodgeBookAmt'+i).value == '')
				{
					alert('Please enter Booking Amount ');
					document.getElementById('lodgeBookAmt'+i).focus();
					return false;
				}
			}
		}catch(e)
		{
		}
		
		
		try{
			for(i=1;i<=locConCount;i++)
			{
				if(document.getElementById('locConCost'+i).value == '')
				{
					alert('Please enter Tariff Cost');
					document.getElementById('locConCost'+i).focus();
					return false;
				}
			}
		}catch(e)
		{
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
   

</script>
