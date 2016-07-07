
<script type="text/javascript">
 var cityArr = new Array();
  var travelArr = new Array();
  var localArr = new Array();
  var lodgArr = new Array();
</script>

<%@ taglib prefix="s" uri="/struts-tags"%>  
<%@ include file="/pages/common/labelManagement.jsp"%>

 <%
  String dataLodg =""+request.getAttribute("dataLodg");
  String dataLocal =""+request.getAttribute("dataLocal"); 
 if(dataLodg!=null && !dataLodg.equals("null") )
 {  
 }else
 {
	 dataLodg="none";
 }
 if(dataLocal!=null && !dataLocal.equals("null") )
 {  
 }else
 {
	 dataLocal="none";
 }
 System.out.println("dataLodg==="+dataLodg+" ====== dataLocal==="+dataLocal);
 %>

<s:form action="TravelApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
  		<tr>
		 <td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Application </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
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
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td><img src="../pages/images/recruitment/space.gif" width="5"
				height="4" /></td> <s:hidden name="hidAppForRadio" />
								<s:hidden name="hidAccommodationReqFlag" />
								<s:hidden name="hidtrArrgFlag" />
								<s:hidden name="hidLocalConReq" />
			<td>  <s:hidden name="loadFlag" />   <s:hidden name="addNewFlag" />
			<s:hidden name="editFlag" />   <s:hidden name="saveFlag" /> <s:hidden name="hidFlag" /> <s:hidden name="trAppId" /></td>
		</tr>
		 
	<!-- addNewFlag -->
	
	 
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Employee.Information.loadFlag" ondblclick="callShowDiv(this);"><%=label.get("Employee.Information.loadFlag")%></label></STRONG></td>

							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Employee.Name" ondblclick="callShowDiv(this);"><%=label.get("Employee.Name")%></label><font color="red">*</font>
								:</td>
								<td colspan="3"><s:textfield name="empToken"
									readonly="true" /> <s:textfield name="empName" size="50"
									readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9Employee.action');">
								<s:hidden name="empId" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Branch.Name" ondblclick="callShowDiv(this);"><%=label.get("Branch.Name")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="branchName" readonly="true" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Department" ondblclick="callShowDiv(this);"><%=label.get("Department")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="department" readonly="true" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Designation" ondblclick="callShowDiv(this);"><%=label.get("Designation")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="designation" readonly="true" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Grade" ondblclick="callShowDiv(this);"><%=label.get("Grade")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="10"
									name="empGrade" readonly="true" /> <s:hidden name="empGradeId" />
								<input type="button" value="Travel Policy" class="token"
									onclick="callTravelPolicy();"></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Age" ondblclick="callShowDiv(this);"><%=label.get("Age")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="empAge " readonly="true" /> <s:hidden name="empDob" />
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Contact.Number" ondblclick="callShowDiv(this);"><%=label.get("Contact.Number")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="contactNumber" readonly="true" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Status" ondblclick="callShowDiv(this);"><%=label.get("Status")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="hidStatus" /> <s:select
									name="appStatus " disabled="false"
									list="#{'P':'Pending','A':'Approved','R':'Rejected'}" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Application.Date" ondblclick="callShowDiv(this);"><%=label.get("Application.Date")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield name="appDate"
									size="10" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_appDate','Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_appDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										name="appDateImg" class="iconImage" height="16"
										align="absmiddle" width="16">
								</s:a></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" />
				<%
				int srNo1 = 0;
				%> <s:iterator value="cityList">
					<input type="hidden" value='<s:property  value="cityName"/>'
						name="cityName" id="cityName<%=srNo1 %>" />
					<s:hidden name="cityId" />
					<script>	
		   cityArr[<%=srNo1%>] = document.getElementById('cityName'+<%=srNo1%>).value;   
		 </script>
					<%
					srNo1++;
					%>
				</s:iterator></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Tour.Details" ondblclick="callShowDiv(this);"><%=label.get("Tour.Details")%></label></STRONG>
								</td>

							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Application.For" ondblclick="callShowDiv(this);"><%=label.get("Tour.Application.For")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="guestAppFlag" />  
								<s:if test="guestAppFlag">
									<s:radio name="appFor" value="%{'G'}"
										list="#{'S':'Self &nbsp;','G':'Guest'}"
										onclick="callCheckForGuest();"></s:radio>
								</s:if> <s:else>
									<s:radio name="appFor" value="%{'S'}"
										list="#{'S':'Self &nbsp;','G':'Guest'}"
										onclick="callCheckForGuest();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1" id="guestCol"><label  class = "set"  id="Guest.Name" ondblclick="callShowDiv(this);"><%=label.get("Guest.Name")%></label> :</td>
								<td width="25%" colspan="1" id="guestColText"><s:textfield
									size="25" name="guestName" /></td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Request.Name" ondblclick="callShowDiv(this);"><%=label.get("Travel.Request.Name")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="travelRequest"  /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Purpose" ondblclick="callShowDiv(this);"><%=label.get("Travel.Purpose")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="travelPurpose"  /> <s:hidden
									name="travelPurposeId" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9Purpose.action');">
								</td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Accommodation" ondblclick="callShowDiv(this);"><%=label.get("Accommodation")%></label> :</td>
								<td width="25%" colspan="1"><s:if test="accomFlag">
									<s:radio name="accommodationReq" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:if> <s:else>
									<s:radio name="accommodationReq" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Arrangement" ondblclick="callShowDiv(this);"><%=label.get("Travel.Arrangement")%></label> :</td>

								<td width="25%" colspan="1"><s:if test="trArFlag">
									<s:radio name="trArrg" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"></s:radio>
								</s:if> <s:else>
									<s:radio name="trArrg" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}" onclick="callForTrArng();" ></s:radio>
								</s:else></td>
							</tr>
							<tr height="20"> 
								<td width="15%" colspan="1"><label  class = "set"  id="Local.Conveyance" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance")%></label> :</td>
								<td width="25%" colspan="1"><s:if test="localFlag">
									<s:radio name="localConReq" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForLocalConv();"></s:radio>
								</s:if> <s:else>
									<s:radio name="localConReq" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForLocalConv();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Location.Type" ondblclick="callShowDiv(this);"><%=label.get("Tour.Location.Type")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:hidden name="tourLocTypeId" /><s:textfield
									name="tourLocType" size="25" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9LocationType.action');">
								</td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Start.Date" ondblclick="callShowDiv(this);"><%=label.get("Tour.Start.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:textfield
									name="tourStartDate" size="10"
									onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_tourStartDate','Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_tourStartDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.end.Date" ondblclick="callShowDiv(this);"><%=label.get("Tour.end.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:textfield name="tourEndDate"
									size="10" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_tourEndDate','Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_tourEndDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Journey.Details" ondblclick="callShowDiv(this);"><%=label.get("Journey.Details")%></label> </strong></td>
								<td colspan="1"><s:submit cssClass="add"
									   value="   Add   " onclick="callAddTravel();" />
								&nbsp; <s:submit cssClass="add" name="DeleteJour"
									value="   Delete   " onclick="return callTravelDelete();" />
								</td>
							</tr>
							<tr height="20">  
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="From.Place" ondblclick="callShowDiv(this);"><%=label.get("From.Place")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="To.Place" ondblclick="callShowDiv(this);"><%=label.get("To.Place")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Journey.Mode-Class" ondblclick="callShowDiv(this);"><%=label.get("Journey.Mode-Class")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="Journey.Date" ondblclick="callShowDiv(this);"><%=label.get("Journey.Date")%></label>
										</td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="jourDtlTime" ondblclick="callShowDiv(this);"><%=label.get("jourDtlTime")%></label>
										</td>
										<td width="5%" colspan="1" class="formth"><input
											type="checkbox" name="selectAllJour" id="selectAllJour"
											onclick="callSelectAllTravel()"></td>
									</tr>
									<%
									int i1 = 1;
									%>
									<%
									int j1 = 0;
									%>
									<s:iterator value="trModeClassList">
										<tr>
											<td width="5%" colspan="1" class="border2"><%=i1%></td>
											<td width="20%" colspan="1" class="border2"><input
												type="text" name="jourFromPlace"
												value='<s:property  value="jourFromPlace"/>'
												id="jourFromPlace<%=i1%>" size="25"
												onkeydown="callCity('<%=i1%>');"
												ondblclick="callCity('<%=i1%>');"
												onclick="callCity('<%=i1%>');" /></td>
											<td width="20%" colspan="1" class="border2"><input
												type="text" name="jourToPlace"
												value='<s:property  value="jourToPlace"/>'
												id="jourToPlace<%=i1%>" size="25"
												onkeydown="callCity('<%=i1%>');"
												ondblclick="callCity('<%=i1%>');"
												onclick="callCity('<%=i1%>');" /></td>
											<td width="25%" colspan="1" class="border2"><s:select
												name="trModeClass" list="%{modeMap}"
												headerValue="--Select--" headerKey="0"></s:select></td>
											<td nowrap="nowrap" colspan="1" class="border2"><s:textfield
												name="jourDate" size="10"
												onkeypress="return numbersWithHiphen();"
												onblur="return validateDate('paraFrm_jourDate','Journey Date');"
												maxlength="10" /></td>
											<td nowrap="nowrap" colspan="1" class="border2"><s:textfield
												name="jourTime" size="5"
												onkeypress="return numbersWithColon();" maxlength="5" /></td>
											<td width="5%" colspan="1" class="border2"><input
												type="checkbox" name="jourChkBox" id="jourChkBox<%=i1%>"
												onclick="travelDeleteCheckbx('<%=i1%>');"> <input
												type="hidden" name="hidHourChkBox" id="hidHourChkBox<%=i1%>">
											</td>
										</tr>
										<script>	
		                                  travelArr[<%=j1%>] = document.getElementById('jourFromPlace'+<%=i1%>).value;   
		                            </script>
										<%
										i1++;
										%>
										<%
										j1++;
										%>
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
			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
			</tr>
			<tr id="localConTable" style="display:<%=dataLocal%>;" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Local.Conveyance.Details" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance.Details")%></label> </strong></td>
								<td colspan="1"><s:submit cssClass="add"
									action="TravelApplication_addLocalCon" value="   Add   " />
								&nbsp; <s:submit cssClass="add" name="DeleteLocal"
									value="   Delete   " onclick="return callLocalConDelete();" />
								</td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Source" ondblclick="callShowDiv(this);"><%=label.get("Source")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Time" ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Time" ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
										<td width="5%" colspan="1" class="formth"><input
											type="checkbox" name="selectAllLocal" id="selectAllLocal"
											onclick="callSelectAllLocal();"></td>
									</tr>
									<%
									int p1 = 1;
									%>
									<%
									int q1 = 0;
									%>

									<s:iterator value="localConList">
										<tr>
											<td width="5%" colspan="1" class="border2"><%=p1%></td>
											<td width="25%" colspan="1" class="border2"><input
												type="text" name="localCity"
												value='<s:property  value="localCity"/>'
												id="localCity<%=p1%>" size="25"
												onkeydown="callLocalCity('<%=p1%>');"
												ondblclick="callLocalCity('<%=p1%>');"
												onclick="callLocalCity('<%=p1%>');" /></td>
											<td width="25%" colspan="1" class="border2"><s:textfield
												name="localSource" size="25" /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="localFromDate" size="10"
												onkeypress="return numbersWithHiphen();"
												onblur="return validateDate('paraFrm_localFromDate','Local From Date');"
												maxlength="10" /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="localFromTime" size="5"
												onkeypress="return numbersWithColon();" maxlength="5" /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="localToDate" size="10"
												onkeypress="return numbersWithHiphen();"
												onblur="return validateDate('paraFrm_localToDate','Local To Date');"
												maxlength="10" /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="localToTime" size="5"
												onkeypress="return numbersWithColon();" maxlength="5" /></td>
											<td width="5%" colspan="1" class="border2"><input
												type="checkbox" name="localChkBox" id="localChkBox<%=p1%>"
												onclick="localDeleteCheckbx('<%=p1%>');"> <input
												type="hidden" name="hidLocalChkBox"
												id="hidLocalChkBox<%=p1%>"></td>
										</tr>
										<script>	
		                                  localArr[<%=q1%>] = document.getElementById('localCity'+<%=p1%>).value;   
		                            </script>
										<%
										q1++;
										%>
										<%
										p1++;
										%>
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

			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
			</tr>
			<tr id="lodgTable" style="display:<%=dataLodg %>" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Lodging.Details" ondblclick="callShowDiv(this);"><%=label.get("Lodging.Details")%></label> </strong></td>
								<td colspan="1"><s:submit cssClass="add"
									action="TravelApplication_addLodg" value="   Add   " />
								&nbsp; <s:submit name="DeleteLodg" cssClass="delete"
									value="   Delete   " onclick="return callLodgDelete();" /></td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="12%" colspan="1" class="formth"><label  class = "set"  id="Hotel.Type" ondblclick="callShowDiv(this);"><%=label.get("Hotel.Type")%></label></td>
										<td width="12%" colspan="1" class="formth"><label  class = "set"  id="Room.Type" ondblclick="callShowDiv(this);"><%=label.get("Room.Type")%></label></td>
										<td width="15%" colspan="1" class="formth"><label  class = "set"  id="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
										<td width="15%" colspan="1" class="formth"><label  class = "set"  id="Preferred.location" ondblclick="callShowDiv(this);"><%=label.get("Preferred.location")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label></td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="From.Time" ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label></td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="To.Time" ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
										<td width="5%" colspan="1" class="formth"><input
											type="checkbox" name="selectAllLodg" id="selectAllLodg"
											onclick="callSelectAllLodg()"></td>
									</tr>
									<%
									int s1 = 1;
									%>
									<%
									int t1 = 0;
									%>
									<s:iterator value="lodgList">
										<tr>
											<td width="5%" colspan="1" class="border2"><%=s1%></td>
											<td width="12%" colspan="1" class="border2"><s:select
												name="hotelType" headerKey="0" headerValue="select"
												list="hotelMap" /></td>
											<td width="12%" colspan="1" class="border2"><s:select
												name="roomType" headerKey="0" headerValue="select"
												list="roomMap" /></td>
											<td width="15%" colspan="1" class="border2"><input
												type="text" name="lodgCity"
												value='<s:property  value="lodgCity"/>' id="lodgCity<%=s1%>"
												size="20" onkeydown="callLodgCity('<%=s1%>');"
												ondblclick="callLodgCity('<%=s1%>');"
												onclick="callLodgCity('<%=s1%>');" /></td>
											<td width="15%" colspan="1" class="border2"><s:textfield
												name="lodgLocation" size="12"  /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="lodgFromdate" size="8" /></td>
											<td width="8%" colspan="1" class="border2"><s:textfield
												name="lodgFromtime" size="4"  /></td>
											<td width="10%" colspan="1" class="border2"><s:textfield
												name="lodgTodate" size="8"  /></td>
											<td width="8%" colspan="1"  ><s:textfield
												name="lodgTotime" size="4"  /></td>
											<td width="5%" colspan="1" class="border2"><input
												type="checkbox" name="lodgChkBox" id="lodgChkBox<%=s1%>"
												onclick="lodgDeleteCheckbx('<%=s1%>');"> <input
												type="hidden" name="hidLodgChkBox" id="hidLodgChkBox<%=s1%>">
											</td>
											<script>	
		                                  lodgArr[<%=t1%>] = document.getElementById('lodgCity'+<%=s1%>).value;   
		                            </script>
											<%
											s1++;
											%>
											<%
											t1++;
											%>
										</tr>

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
			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Advance.Details" ondblclick="callShowDiv(this);"><%=label.get("Advance.Details")%></label> </STRONG></td>

							</tr>

							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Advance.Amount" ondblclick="callShowDiv(this);"><%=label.get("Advance.Amount")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:textfield size="25" 
									name="advanceAmt" onkeypress="return numbersOnly();" /></td>
								<td width="20%" colspan="1"><label  class = "set"  id="Preferred.Payment.Mode" ondblclick="callShowDiv(this);"><%=label.get("Preferred.Payment.Mode")%></label> :</td>
								<td width="25%" colspan="1"><s:select name="payMode"
									headerKey="1" headerValue="Select"
									list="#{'C':'Cash','Q':'Cheque','S':'Salary','T':'Transfer'}" />
								</td>
							</tr>
							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Expected.Settlement.Date" ondblclick="callShowDiv(this);"><%=label.get("Expected.Settlement.Date")%></label>:</td>
								<td width="25%" colspan="1"><s:textfield name="settleDate"
									size="10" onkeypress="return numbersWithHiphen();"  onblur="return validateDate('paraFrm_settleDate','Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_settleDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								</td>
								<td width="15%" colspan="1">&nbsp;</td>
								<td width="25%" colspan="1">
							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td height="5" colspan="3"><img
					src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Identity.Details" ondblclick="callShowDiv(this);"><%=label.get("Identity.Details")%></label> </STRONG></td>

							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="ID.Proof" ondblclick="callShowDiv(this);"><%=label.get("ID.Proof")%></label> :</td>
								<td width="25%" colspan="1"><s:select name="idProof"
									headerKey="1" headerValue="Select"
									list="#{'V':'Voter Identity Card ','P':'Passport','I':' PAN Card','D':'Driving Licence ',
										'G':'Photo identity cards issued by Central/State Government'}"
									theme="simple" /></td>
								<td width="10%" colspan="1"><label  class = "set"  id="ID.Number" ondblclick="callShowDiv(this);"><%=label.get("ID.Number")%></label> :</td>
								<td width="30%" colspan="1"><s:textfield size="25" 
									name="idProofNumber" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Comments" ondblclick="callShowDiv(this);"><%=label.get("Comments")%></label> :</td>
								<td width="80%" colspan="3"><s:textarea name="appComments"
									cols="110" rows="2"></s:textarea></td>

							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
	    </tr> 
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">&nbsp;</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

 function cancelFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_cancel.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
 
function callForReadOnly()
{    
    	var formElements = document.forms['paraFrm'].elements;
    	alert(formElements.length );
		for (var i = formElements.length - 1; i >= 0; i--)
 		{ 
 	 
 		 if(formElements[i].name!="")
 		 {
 		  document.getElementById('paraFrm'+formElements[i].name).disabled="disabled";  
 		  }
 		}
}

 function searchFun()
     {   
         
          callsF9(500,325,'TravelApplication_f9action.action'); 
		  
    }
    
    function callTravelPolicy()
    { 
	       if(document.getElementById('paraFrm_empId').value=="")
	       {
	        alert("Please select the empyloyee.");
	        return false;
	       }
	    var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelApplication_TravelPolicy.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
    }
    
  function addnewFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_addNew.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
     function saveFun()
     {   if(!callSave())
         {
         return false;
         }
         document.getElementById('paraFrm').action = "TravelApplication_save.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    function callSave()
    {
        if(document.getElementById('paraFrm_empId').value=="")
	       {
	        alert("Please select the empyloyee.");
	        return false;
	       }
    
       if(document.getElementById('paraFrm_travelPurpose').value=="")  
       {
        alert("Please select travel purpose.") ; 
        return false;
       }
       
        if(document.getElementById('paraFrm_tourLocTypeId').value=="")  
       {
        alert("Please select location type.") ; 
        return false;
       }
       
       
       if(document.getElementById('paraFrm_tourStartDate').value=="")  
       {
        alert("Please enter tour start Date.") ;
        document.getElementById('paraFrm_tourStartDate').focus();
        return false;
       }
       
       if(document.getElementById('paraFrm_tourEndDate').value=="")  
       {
        alert("Please enter tour end Date.") ;
        document.getElementById('paraFrm_tourEndDate').focus();
        return false;
       }
       
        if(document.getElementById('paraFrm_advanceAmt').value=="")  
       {
        alert("Please enter advance amount.") ;
        document.getElementById('paraFrm_advanceAmt').focus();
        return false;
       } 
         return true;
    }
    function editFun()
     {   
	      
         document.getElementById('paraFrm_hidFlag').value="edit";
         document.getElementById('paraFrm').action = "TravelApplication_edit.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    

     function deleteFun()
     {  
     var conf=confirm("Do you really want to delete this record ?");
  	  if(conf) 
  	 	 {
  		 document.getElementById('paraFrm').action = "TravelApplication_delete.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";
  		 }else
  		 {
  		   return false;
  		 } 
    }

 //===================for travel================
function callSelectAllTravel()
{

	 if(document.getElementById('selectAllJour').checked)
	  {   
	    var j =1;
		    for(i=0;i<travelArr.length;i++)
		    { 
		      document.getElementById('jourChkBox'+j).checked='true' 
		     document.getElementById('hidHourChkBox'+j).value='Y' 
		      
		     j++;
		    }
	  }else
	  {
	       var j =1;
		    for(i=0;i<travelArr.length;i++)
		    { 
		      document.getElementById('jourChkBox'+j).checked=false
		      document.getElementById('hidHourChkBox'+j).value='N' 
		     j++;
		    } 
	  }
 }
 
 function travelDeleteCheckbx(code){
   
	 if(document.getElementById('jourChkBox'+code).checked)
	  {  
	   document.getElementById('hidHourChkBox'+code).value='Y' 
	 }else
	 {
	  document.getElementById('hidHourChkBox'+code).value='N' 
	 }  
  }
  
  
   function callTravelDelete(){
 
// if(!(document.getElementById('paraFrm_status').value=="P")){
//	alert("You can't update the application !");
//	return false;
//	}
 
	if(travelArr.length==0)
	{ 
	  alert("There is no row for delete.");
	  return false;
	} 
	    var flag="notChecked";
        var j =1;
	    for(i=0;i<travelArr.length;i++)
	    {  
	      if(document.getElementById('hidHourChkBox'+j).value=='Y') 
	      {
	       flag="checked";
	        break;
	      }
	     j++;
	    } 
		
		if( flag=="notChecked")
		{
		   alert("Please select at least one record for delete.");
	       return false;
		}    
		    
    var conf=confirm("Do you really want to delete this record ?");
  	 		if(conf) 
  	 	    {  
	        document.getElementById('paraFrm').action='TravelApplication_delTravelMode.action'; 
	        document.getElementById('paraFrm').submit();	 
  			 	return true;
  			 }
	  		 else
	  		 {  return false;  } 
	return true;
}

//===========end of travel================
//===========LOcal Con================
	
 function localDeleteCheckbx(code){
   
	 if(document.getElementById('localChkBox'+code).checked)
	  {  
	   document.getElementById('hidLocalChkBox'+code).value='Y' 
	 }else
	 {
	  document.getElementById('hidLocalChkBox'+code).value='N' 
	 }  
  }
   
  
   function callLocalConDelete(){
 
// if(!(document.getElementById('paraFrm_status').value=="P")){
//	alert("You can't update the application !");
//	return false;
//	}
 
	if(localArr.length==0)
	{ 
	  alert("There is no row for delete.");
	  return false;
	} 
	    var flag="notChecked";
        var j =1;
	    for(i=0;i<localArr.length;i++)
	    {  
	      if(document.getElementById('hidLocalChkBox'+j).value=='Y') 
	      {
	       flag="checked";
	        break;
	      }
	     j++;
	    } 
		
		if( flag=="notChecked")
		{
		   alert("Please select at least one record for delete.");
	       return false;
		}    
		    
    var conf=confirm("Do you really want to delete this record ?");
  	 		if(conf) 
  	 	    {  
	        document.getElementById('paraFrm').action='TravelApplication_delLoaclConv.action'; 
	        document.getElementById('paraFrm').submit();	 
  			 	return true;
  			 }
	  		 else
	  		 {  return false;  } 
	return true;
}

function callSelectAllLocal()
{ 
   if(document.getElementById('selectAllLocal').checked)
	  {   
	    var j=1;
		    for(i=0;i<localArr.length;i++)
		    {  
		      document.getElementById('localChkBox'+j).checked=true;  
		      document.getElementById('hidLocalChkBox'+j).value='Y'   
		     j++;
		    }
	  }else
	  {
	       var j =1;
		    for(i=0;i<localArr.length;i++)
		    { 
		      document.getElementById('localChkBox'+j).checked=false;
		      document.getElementById('hidLocalChkBox'+j).value='N' 
		     j++;
		    } 
	  }
 }
 
  
 //===========End Local Con================
 
  //===================for Lodging================    
function callSelectAllLodg()
{

	 if(document.getElementById('selectAllLodg').checked)
	  {   
	    var j =1;
		    for(i=0;i<lodgArr.length;i++)
		    { 
		      document.getElementById('lodgChkBox'+j).checked='true' 
		     document.getElementById('hidLodgChkBox'+j).value='Y' 
		      
		     j++;
		    }
	  }else
	  {
	       var j =1;
		    for(i=0;i<lodgArr.length;i++)
		    { 
		      document.getElementById('lodgChkBox'+j).checked=false
		      document.getElementById('hidLodgChkBox'+j).value='N' 
		     j++;
		    } 
	  }
 }
 
 function lodgDeleteCheckbx(code){
   
	 if(document.getElementById('lodgChkBox'+code).checked)
	  {  
	   document.getElementById('hidLodgChkBox'+code).value='Y' 
	 }else
	 {
	  document.getElementById('hidLodgChkBox'+code).value='N' 
	 }  
  }
  
  
   function callLodgDelete(){
 
// if(!(document.getElementById('paraFrm_status').value=="P")){
//	alert("You can't update the application !");
//	return false;
//	}
 
	if(lodgArr.length==0)
	{ 
	  alert("There is no row for delete.");
	  return false;
	} 
	    var flag="notChecked";
        var j =1;
	    for(i=0;i<lodgArr.length;i++)
	    {  
	      if(document.getElementById('hidLodgChkBox'+j).value=='Y') 
	      {
	       flag="checked";
	        break;
	      }
	     j++;
	    } 
		
		if( flag=="notChecked")
		{
		   alert("Please select at least one record for delete.");
	       return false;
		}    
		    
    var conf=confirm("Do you really want to delete this record ?");
  	 		if(conf) 
  	 	    {  
	        document.getElementById('paraFrm').action='TravelApplication_delLodg.action'; 
	        document.getElementById('paraFrm').submit();	 
  			 	return true;
  			 }
	  		 else
	  		 {  return false;  } 
	return true;
}

//===========end of Lodging================
callForRadio();
function callForRadio()
{ 
   document.getElementById('guestCol').style.display='none'; 
   document.getElementById('guestColText').style.display='none'; 
   callCheckForGuest();  
}

function callCheckForGuest()
{   
  if(document.getElementById('paraFrm_appForG').checked)
  { 
    document.getElementById('paraFrm_hidAppForRadio').value='Y';
  
   document.getElementById('guestCol').style.display=''; 
   document.getElementById('guestColText').style.display='';  
  }
   if(document.getElementById('paraFrm_appForS').checked)
  { 
   document.getElementById('paraFrm_hidAppForRadio').value='N';
   document.getElementById('guestCol').style.display='none'; 
   document.getElementById('guestColText').style.display='none';  
  }
} 

function callForAccod()
 {    				 
	 							
  if(document.getElementById('paraFrm_accommodationReqC').checked)
	  { 
	  
	   document.getElementById('lodgTable').style.display='';  
	   document.getElementById('paraFrm_hidAccommodationReqFlag').value='Y';
	  }
	   if(document.getElementById('paraFrm_accommodationReqS').checked)
	  { 
	   document.getElementById('lodgTable').style.display='none';  
	   document.getElementById('paraFrm_hidAccommodationReqFlag').value='N';
	  }
 }   
 
 function callForLocalConv()
 {   
   if(document.getElementById('paraFrm_localConReqC').checked)
	  { 
	   document.getElementById('localConTable').style.display='';  
	     document.getElementById('paraFrm_hidLocalConReq').value='Y';
	  }
	   if(document.getElementById('paraFrm_localConReqS').checked)
	  { 
	  	     document.getElementById('paraFrm_hidLocalConReq').value='N';
	   document.getElementById('localConTable').style.display='none';  
	  }
 }
 
  function callForTrArng()
 {   
   if(document.getElementById('paraFrm_trArrgC').checked)
	  {  
	     document.getElementById('paraFrm_hidtrArrgFlag').value='Y';
	  }
	   if(document.getElementById('paraFrm_trArrgS').checked)
	  { 
	  	     document.getElementById('paraFrm_hidtrArrgFlag').value='N';
	   
	  }
 }
 
</script>









<style type="text/css">
.autocomplete {
	font-family: Tahoma;
	font-size: 8pt;
	background-color: white;
	border: 1px solid black;
	position: absolute;
	cursor: default;
	overflow: auto;
	overflow-x: hidden;
}

.autocomplete_item {
	padding: 1px;
	padding-left: 5px;
	color: black;
	width: 100%;
}

.autocomplete_item_highlighted {
	padding: 1px;
	padding-left: 5px;
	color: white;
	background-color: #0A246A;
}
</style>
<script>
 /**
* This library is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
* 
* © Copyright 2005 Richard Heyes
*/

    /**
    * Global vars
    */
    __AutoComplete = new Array();

    // Basic UA detection
    isIE = document.all ? true : false;
    isGecko = navigator.userAgent.toLowerCase().indexOf('gecko') != -1;
    isOpera = navigator.userAgent.toLowerCase().indexOf('opera') != -1;


    /**
    * Attachs the autocomplete object to a form element. Sets
    * onkeypress event on the form element.
    * 
    * @param string formElement Name of form element to attach to
    * @param array  data        Array of strings of which to use as the autocomplete data
    */
    function AutoComplete_Create (id, data)
    {
        __AutoComplete[id] = {'data':data,
                              'isVisible':false,
                              'element':document.getElementById(id),
                              'dropdown':null,
                              'highlighted':null};

        __AutoComplete[id]['element'].setAttribute('autocomplete', 'off');
        __AutoComplete[id]['element'].onkeydown  = function(e) {return AutoComplete_KeyDown(this.getAttribute('id'), e);}
        __AutoComplete[id]['element'].onkeyup    = function(e) {return AutoComplete_KeyUp(this.getAttribute('id'), e);}
        __AutoComplete[id]['element'].onkeypress = function(e) {if (!e) e = window.event; if (e.keyCode == 13 || isOpera) return false;}
        __AutoComplete[id]['element'].ondblclick = function() {AutoComplete_ShowDropdown(this.getAttribute('id'));}
        __AutoComplete[id]['element'].onclick    = function(e) {if (!e) e = window.event; e.cancelBubble = true; e.returnValue = false;}

        // Hides the dropdowns when document clicked
        var docClick = function()
        {
           for (id in __AutoComplete) {
               AutoComplete_HideDropdown(id);
           }
        }

        if (document.addEventListener) {
            document.addEventListener('click', docClick, false);
        } else if (document.attachEvent) {
            document.attachEvent('onclick', docClick, false);
        }


        // Max number of items shown at once
        if (arguments[2] != null) {
            __AutoComplete[id]['maxitems'] = arguments[2];
            __AutoComplete[id]['firstItemShowing'] = 0;
            __AutoComplete[id]['lastItemShowing']  = arguments[2] - 1;
        }
        
        AutoComplete_CreateDropdown(id);
        
        // Prevent select dropdowns showing thru
        if (isIE) {
            __AutoComplete[id]['iframe'] = document.createElement('iframe');
            __AutoComplete[id]['iframe'].id = id +'_iframe';
            __AutoComplete[id]['iframe'].style.position = 'absolute';
            __AutoComplete[id]['iframe'].style.top = '0';
            __AutoComplete[id]['iframe'].style.left = '0';
            __AutoComplete[id]['iframe'].style.width = '0px';
            __AutoComplete[id]['iframe'].style.height = '0px';
            __AutoComplete[id]['iframe'].style.zIndex = '98';
            __AutoComplete[id]['iframe'].style.visibility = 'hidden';
            
            __AutoComplete[id]['element'].parentNode.insertBefore(__AutoComplete[id]['iframe'], __AutoComplete[id]['element']);
        }
    }


    /**
    * Creates the dropdown layer
    * 
    * @param string id The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_CreateDropdown(id)
    {
        var left  = AutoComplete_GetLeft(__AutoComplete[id]['element']);
        var top   = AutoComplete_GetTop(__AutoComplete[id]['element']) + __AutoComplete[id]['element'].offsetHeight;
        var width = __AutoComplete[id]['element'].offsetWidth;
    
        __AutoComplete[id]['dropdown'] = document.createElement('div');
        __AutoComplete[id]['dropdown'].className = 'autocomplete'; // Don't use setAttribute()
    
        __AutoComplete[id]['element'].parentNode.insertBefore(__AutoComplete[id]['dropdown'], __AutoComplete[id]['element']);
        
        // Position it
        __AutoComplete[id]['dropdown'].style.left       = left + 'px';
        __AutoComplete[id]['dropdown'].style.top        = top + 'px';
        __AutoComplete[id]['dropdown'].style.width      = width + 'px';
        __AutoComplete[id]['dropdown'].style.zIndex     = '99';
        __AutoComplete[id]['dropdown'].style.visibility = 'hidden';
    }
    
    
    /**
    * Gets left coord of given element
    * 
    * @param object element The element to get the left coord for
    */
    function AutoComplete_GetLeft(element)
    {
        var curNode = element;
        var left    = 0;

        do {
            left += curNode.offsetLeft;
            curNode = curNode.offsetParent;

        } while(curNode.tagName.toLowerCase() != 'body');

        return left;
    }
    
    
    /**
    * Gets top coord of given element
    * 
    * @param object element The element to get the top coord for
    */
    function AutoComplete_GetTop(element)
    {
        var curNode = element;
        var top    = 0;

        do {
            top += curNode.offsetTop;
            curNode = curNode.offsetParent;

        } while(curNode.tagName.toLowerCase() != 'body');

        return top;
    }

    
    /**
    * Shows the dropdown layer
    * 
    * @param string id The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_ShowDropdown(id)
    {
        AutoComplete_HideAll();

        var value = __AutoComplete[id]['element'].value;
        var toDisplay = new Array();
        var newDiv    = null;
        var text      = null;
        var numItems  = __AutoComplete[id]['dropdown'].childNodes.length;

        // Remove all child nodes from dropdown
        while (__AutoComplete[id]['dropdown'].childNodes.length > 0) {
            __AutoComplete[id]['dropdown'].removeChild(__AutoComplete[id]['dropdown'].childNodes[0]);
        }

        // Go thru data searching for matches
        for (i=0; i<__AutoComplete[id]['data'].length; ++i) {
            if (__AutoComplete[id]['data'][i].substr(0, value.length) == value) {
                toDisplay[toDisplay.length] = __AutoComplete[id]['data'][i];
            }
        }
        
        // No matches?
        if (toDisplay.length == 0) {
            AutoComplete_HideDropdown(id);
            return;
        }



        // Add data to the dropdown layer
        for (i=0; i<toDisplay.length; ++i) {
            newDiv = document.createElement('div');
            newDiv.className = 'autocomplete_item'; // Don't use setAttribute()
            newDiv.setAttribute('id', 'autocomplete_item_' + i);
            newDiv.setAttribute('index', i);
            newDiv.style.zIndex = '99';
            
             // Scrollbars are on display ?
            if (toDisplay.length > __AutoComplete[id]['maxitems'] && navigator.userAgent.indexOf('MSIE') == -1) {
                newDiv.style.width = __AutoComplete[id]['element'].offsetWidth - 22 + 'px';
            }

            newDiv.onmouseover = function() {AutoComplete_HighlightItem(__AutoComplete[id]['element'].getAttribute('id'), this.getAttribute('index'));};
            newDiv.onclick     = function() {AutoComplete_SetValue(__AutoComplete[id]['element'].getAttribute('id')); AutoComplete_HideDropdown(__AutoComplete[id]['element'].getAttribute('id'));}
            
            text   = document.createTextNode(toDisplay[i]);
            newDiv.appendChild(text);
            
            __AutoComplete[id]['dropdown'].appendChild(newDiv);
        }
        
        
        // Too many items?
        if (toDisplay.length > __AutoComplete[id]['maxitems']) {
            __AutoComplete[id]['dropdown'].style.height = (__AutoComplete[id]['maxitems'] * 15) + 2 + 'px';
        
        } else {
            __AutoComplete[id]['dropdown'].style.height = '';
        }

        
        /**
        * Set left/top in case of document movement/scroll/window resize etc
        */
        __AutoComplete[id]['dropdown'].style.left = AutoComplete_GetLeft(__AutoComplete[id]['element']);
        __AutoComplete[id]['dropdown'].style.top  = AutoComplete_GetTop(__AutoComplete[id]['element']) + __AutoComplete[id]['element'].offsetHeight;


        // Show the iframe for IE
        if (isIE) {
            __AutoComplete[id]['iframe'].style.top    = __AutoComplete[id]['dropdown'].style.top;
            __AutoComplete[id]['iframe'].style.left   = __AutoComplete[id]['dropdown'].style.left;
            __AutoComplete[id]['iframe'].style.width  = __AutoComplete[id]['dropdown'].offsetWidth;
            __AutoComplete[id]['iframe'].style.height = __AutoComplete[id]['dropdown'].offsetHeight;
            
            __AutoComplete[id]['iframe'].style.visibility = 'visible';
        }


        // Show dropdown
        if (!__AutoComplete[id]['isVisible']) {
            __AutoComplete[id]['dropdown'].style.visibility = 'visible';
            __AutoComplete[id]['isVisible'] = true;
        }

        
        // If now showing less items than before, reset the highlighted value
        if (__AutoComplete[id]['dropdown'].childNodes.length != numItems) {
            __AutoComplete[id]['highlighted'] = null;
        }
    }
    
    
    /**
    * Hides the dropdown layer
    * 
    * @param string id The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_HideDropdown(id)
    {
        if (__AutoComplete[id]['iframe']) {
            __AutoComplete[id]['iframe'].style.visibility = 'hidden';
        }


        __AutoComplete[id]['dropdown'].style.visibility = 'hidden';
        __AutoComplete[id]['highlighted'] = null;
        __AutoComplete[id]['isVisible']   = false;
    }
    
    
    /**
    * Hides all dropdowns
    */
    function AutoComplete_HideAll()
    {
        for (id in __AutoComplete) {
            AutoComplete_HideDropdown(id);
        }
    }
    
    
    /**
    * Highlights a specific item
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    * @param int    index The index of the element in the dropdown to highlight
    */
    function AutoComplete_HighlightItem(id, index)
    {
        if (__AutoComplete[id]['dropdown'].childNodes[index]) {
            for (var i=0; i<__AutoComplete[id]['dropdown'].childNodes.length; ++i) {
                if (__AutoComplete[id]['dropdown'].childNodes[i].className == 'autocomplete_item_highlighted') {
                    __AutoComplete[id]['dropdown'].childNodes[i].className = 'autocomplete_item';
                }
            }
            
            __AutoComplete[id]['dropdown'].childNodes[index].className = 'autocomplete_item_highlighted';
            __AutoComplete[id]['highlighted'] = index;
        }
    }


    /**
    * Highlights the menu item with the given index
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    * @param int    index The index of the element in the dropdown to highlight
    */
    function AutoComplete_Highlight(id, index)
    {
        // Out of bounds checking
        if (index == 1 && __AutoComplete[id]['highlighted'] == __AutoComplete[id]['dropdown'].childNodes.length - 1) {
            __AutoComplete[id]['dropdown'].childNodes[__AutoComplete[id]['highlighted']].className = 'autocomplete_item';
            __AutoComplete[id]['highlighted'] = null;
        
        } else if (index == -1 && __AutoComplete[id]['highlighted'] == 0) {
            __AutoComplete[id]['dropdown'].childNodes[0].className = 'autocomplete_item';
            __AutoComplete[id]['highlighted'] = __AutoComplete[id]['dropdown'].childNodes.length;
        }

        // Nothing highlighted at the moment
        if (__AutoComplete[id]['highlighted'] == null) {
            __AutoComplete[id]['dropdown'].childNodes[0].className = 'autocomplete_item_highlighted';
            __AutoComplete[id]['highlighted'] = 0;

        } else {
            if (__AutoComplete[id]['dropdown'].childNodes[__AutoComplete[id]['highlighted']]) {
                __AutoComplete[id]['dropdown'].childNodes[__AutoComplete[id]['highlighted']].className = 'autocomplete_item';
            }

            var newIndex = __AutoComplete[id]['highlighted'] + index;

            if (__AutoComplete[id]['dropdown'].childNodes[newIndex]) {
                __AutoComplete[id]['dropdown'].childNodes[newIndex].className = 'autocomplete_item_highlighted';
                
                __AutoComplete[id]['highlighted'] = newIndex;
            }
        }
    }


    /**
    * Sets the input to a given value
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_SetValue(id)
    {
        __AutoComplete[id]['element'].value = __AutoComplete[id]['dropdown'].childNodes[__AutoComplete[id]['highlighted']].innerHTML;
    }
    
    
    /**
    * Checks if the dropdown needs scrolling
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_ScrollCheck(id)
    {
        // Scroll down, or wrapping around from scroll up
        if (__AutoComplete[id]['highlighted'] > __AutoComplete[id]['lastItemShowing']) {
            __AutoComplete[id]['firstItemShowing'] = __AutoComplete[id]['highlighted'] - (__AutoComplete[id]['maxitems'] - 1);
            __AutoComplete[id]['lastItemShowing']  = __AutoComplete[id]['highlighted'];
        }
        
        // Scroll up, or wrapping around from scroll down
        if (__AutoComplete[id]['highlighted'] < __AutoComplete[id]['firstItemShowing']) {
            __AutoComplete[id]['firstItemShowing'] = __AutoComplete[id]['highlighted'];
            __AutoComplete[id]['lastItemShowing']  = __AutoComplete[id]['highlighted'] + (__AutoComplete[id]['maxitems'] - 1);
        }
        
        __AutoComplete[id]['dropdown'].scrollTop = __AutoComplete[id]['firstItemShowing'] * 15;
    }


    /**
    * Function which handles the keypress event
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_KeyDown(id)
    {
        // Mozilla
        if (arguments[1] != null) {
            event = arguments[1];
        }

        var keyCode = event.keyCode;

        switch (keyCode) {

            // Return/Enter
            case 13:
                if (__AutoComplete[id]['highlighted'] != null) {
                    AutoComplete_SetValue(id);
                    AutoComplete_HideDropdown(id);
                }
                
                event.returnValue = false;
                event.cancelBubble = true;
                break;

            // Escape
            case 27:
                AutoComplete_HideDropdown(id);
                event.returnValue = false;
                event.cancelBubble = true;
                break;
            
            // Up arrow
            case 38:
                if (!__AutoComplete[id]['isVisible']) {
                    AutoComplete_ShowDropdown(id);
                }
                
                AutoComplete_Highlight(id, -1);
                AutoComplete_ScrollCheck(id, -1);
                return false;
                break;
            
            // Tab
            case 9:
                if (__AutoComplete[id]['isVisible']) {
                    AutoComplete_HideDropdown(id);
                }
                return;
            
            // Down arrow
            case 40:
                if (!__AutoComplete[id]['isVisible']) {
                    AutoComplete_ShowDropdown(id);
                }
                
                AutoComplete_Highlight(id, 1);
                AutoComplete_ScrollCheck(id, 1);
                return false;
                break;
        }
    }


    /**
    * Function which handles the keyup event
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_KeyUp(id)
    {
        // Mozilla
        if (arguments[1] != null) {
            event = arguments[1];
        }

        var keyCode = event.keyCode;

        switch (keyCode) {
            case 13:
                event.returnValue = false;
                event.cancelBubble = true;
                break;

            case 27:
                AutoComplete_HideDropdown(id);
                event.returnValue = false;
                event.cancelBubble = true;
                break;
            
            case 38:
            case 40:
                return false;
                break;

            default:
                AutoComplete_ShowDropdown(id);
                break;
        }
    }
    
    /**
    * Returns whether the dropdown is visible
    * 
    * @param string id    The form elements id. Used to identify the correct dropdown.
    */
    function AutoComplete_isVisible(id)
    {
        return __AutoComplete[id]['dropdown'].style.visibility == 'visible';
    }
     
     </script>


<script language="javascript" type="text/javascript">  
       function callCity(id)
        {  
           data = cityArr.sort(); 
			AutoComplete_Create('jourFromPlace'+id, data); 
			AutoComplete_Create('jourToPlace'+id, data);  
	  }
	  
	    function callLocalCity(id)
        { 
			data = cityArr.sort(); 
			AutoComplete_Create('localCity'+id, data);  
	  }
	  
	   function callLodgCity(id)
        { 
			data = cityArr.sort(); 
			AutoComplete_Create('lodgCity'+id, data);  
	  }
 
						 
	</script>























