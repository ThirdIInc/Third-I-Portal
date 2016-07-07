    
<script type="text/javascript">
 var cityArr = new Array();
  var travelArr = new Array();
  var localArr = new Array();
  var lodgArr = new Array();
</script>

<%@ taglib prefix="s" uri="/struts-tags"%>  
<%@ include file="/pages/common/labelManagement.jsp"%>

 <%
 	String dataLodg = "" + request.getAttribute("dataLodg");
 	String dataLocal = "" + request.getAttribute("dataLocal");
 	if (dataLodg != null && !dataLodg.equals("null")) {
 	} else {
 		dataLodg = "none";
 	}
 	if (dataLocal != null && !dataLocal.equals("null")) {
 	} else {
 		dataLocal = "none";
 	}
 	System.out.println("dataLodg===" + dataLodg
 			+ " ====== dataLocal===" + dataLocal);
 %>

<s:form action="TravelApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="0">
		  
		<tr>
			<td  height="2"> </td>
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
			<td height="2" colspan="3"> </td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
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
			<td height="2"><s:hidden name="hidAppForRadio" />
								<s:hidden name="hidAccommodationReqFlag" />
								<s:hidden name="hidtrArrgFlag" /> <s:hidden name="rowId" />
								<s:hidden name="hidLocalConReq" />  
								<s:hidden name="settleNumOfDay" /> 
								<s:hidden name="settleFlag" /> 
								<s:hidden name="validTrMode" />      
								<s:hidden name="validHotelType" /> 
								<s:hidden name="validRoomType" /> 
								<s:hidden name="approverPanelFlag" />
								<s:hidden name ="trAppCanStatus" />   </td>   
			<td>  <s:hidden name="loadFlag" />   <s:hidden name="addNewFlag" />
			<s:hidden name="editFlag" />   <s:hidden name="saveFlag" /> <s:hidden name="hidFlag" /> <s:hidden name="trAppId" /></td>
		</tr> 
 
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"  >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set" name="Employee.Information.loadFlag"  id="Employee.Information.loadFlag" ondblclick="callShowDiv(this);"><%=label.get("Employee.Information.loadFlag")%></label></STRONG></td>

							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set" name="Employee.Name"  id="Employee.Name" ondblclick="callShowDiv(this);"><%=label.get("Employee.Name")%></label><font color="red">*</font>
								:</td>
								<td colspan="3"><s:textfield name="empToken"
									readonly="true" /> <s:textfield name="empName" size="50"
									readonly="true" />
								<s:hidden name="empId" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Branch.Name" name="Branch.Name"  ondblclick="callShowDiv(this);"><%=label.get("Branch.Name")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="branchName" readonly="true" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Department" name="Department"  ondblclick="callShowDiv(this);"><%=label.get("Department")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="department" readonly="true" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Designation"  name="Designation" ondblclick="callShowDiv(this);"><%=label.get("Designation")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="designation" readonly="true" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Grade" name="Grade"  ondblclick="callShowDiv(this);"><%=label.get("Grade")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="10"
									name="empGrade" readonly="true" /> <s:hidden name="empGradeId" />
								<input type="button" value="Travel Policy" class="token"
									onclick="callTravelPolicy();"></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Age"  name="Age"  ondblclick="callShowDiv(this);"><%=label.get("Age")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="empAge" readonly="true" /> <s:hidden name="empDob" />
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Contact.Number" name="Contact.Number"  ondblclick="callShowDiv(this);"><%=label.get("Contact.Number")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="contactNumber" readonly="true" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Status" name="Status" ondblclick="callShowDiv(this);"><%=label.get("Status")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="hidStatus" /> <s:select
									name="appStatus" disabled="true"
									list="#{'P':'Pending','A':'Approved','R':'Rejected'}" /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Application.Date" name="Application.Date" ondblclick="callShowDiv(this);"><%=label.get("Application.Date")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield name="appDate"
									size="10" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_appDate','Application.Date');"
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
				<td height="2" colspan="3"> 
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
							cellspacing="2"   >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Tour.Details" name="Tour.Details"   ondblclick="callShowDiv(this);"><%=label.get("Tour.Details")%></label></STRONG>
								</td>

							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Start.Date" name="Tour.Start.Date" ondblclick="callShowDiv(this);"><%=label.get("Tour.Start.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:textfield
									name="tourStartDate" size="10"
									onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_tourStartDate','Tour.Start.Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_tourStartDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.end.Date" name="Tour.end.Date"  ondblclick="callShowDiv(this);"><%=label.get("Tour.end.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:textfield name="tourEndDate"
									size="10" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_tourEndDate','Tour.end.Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_tourEndDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Application.For" name="Tour.Application.For"  ondblclick="callShowDiv(this);"><%=label.get("Tour.Application.For")%></label> :</td>
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
								<td width="15%" colspan="1" > <div id="guestCol" > <label  class = "set"  id="Guest.Name" name="Guest.Name"  ondblclick="callShowDiv(this);"><%=label.get("Guest.Name")%></label><font color="red">*</font> : </div></td>
								<td width="25%" colspan="1" > <div id="guestColText" > <s:textfield  size="25" name="guestName" /> </div> </td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Request.Name" name="Travel.Request.Name"  ondblclick="callShowDiv(this);"><%=label.get("Travel.Request.Name")%></label><font color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:textfield size="25"
									name="travelRequest"  /></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Purpose" name="Travel.Purpose"  ondblclick="callShowDiv(this);"><%=label.get("Travel.Purpose")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:textfield size="25" 	name="travelPurpose"  readonly="true" /> <s:hidden
									name="travelPurposeId" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9Purpose.action');">
								</td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"> <label  class = "set"  id="Local.Conveyance"  name="Local.Conveyance" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance")%></label> :
								</td>
								<td width="25%" colspan="1"> <s:if test="localFlag">
									<s:radio name="localConReq" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForLocalConv();"></s:radio>
								</s:if> <s:else>
									<s:radio name="localConReq" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForLocalConv();"></s:radio>
								</s:else> 
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Arrangement" name="Travel.Arrangement" ondblclick="callShowDiv(this);"><%=label.get("Travel.Arrangement")%></label> :</td>

								<td width="25%" colspan="1"><s:if test="trArFlag">
									<s:radio name="trArrg" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"></s:radio>
								</s:if> <s:else>
									<s:radio name="trArrg" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}" onclick="callForTrArng();" ></s:radio>
								</s:else></td>
							</tr>
							<tr height="20"> 
								<td width="15%" colspan="1"><label  class = "set"  id="Accommodation" name="Accommodation"  ondblclick="callShowDiv(this);"><%=label.get("Accommodation")%></label> : </td>
								<td width="25%" colspan="1"><s:if test="accomFlag">
									<s:radio name="accommodationReq" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:if> <s:else>
									<s:radio name="accommodationReq" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Location.Type"  name="Tour.Location.Type" ondblclick="callShowDiv(this);"><%=label.get("Tour.Location.Type")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:hidden name="tourLocTypeId" /><s:textfield
									name="tourLocType" size="25" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9LocationType.action');">
								</td>
							</tr>
							
							<tr height="20"> 
							  <td width="15%" colspan="1"><label  class = "set"  id="FoodType" name="FoodType"  ondblclick="callShowDiv(this);"><%=label.get("FoodType")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield
									name="foodType" size="25" readonly="true" />  <s:hidden
									name="foodTypeId"   /> 
									<img src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelApplication_f9FoodType.action');"> </td>
								<td width="15%" colspan="1">&nbsp;</td>
								<td width="25%" colspan="1">&nbsp;</td>
							</tr>  

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="2" colspan="3"> </td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"  >

							<tr height="20">
								<td width="82%" colspan="2"><strong><label  class = "set"  id="Journey.Details" name="Journey.Details"  ondblclick="callShowDiv(this);"><%=label.get("Journey.Details")%></label> </strong></td>
								 
								<td colspan="1">
								<s:submit cssClass="add"     value="   Add   " onclick="callAddTravel();" /> </td>
								 <td colspan="1"> <s:submit cssClass="add" name="DeleteJour"
									value="   Delete   " onclick="return callTravelDelete();" /> </td>
								</td>
							</tr>
							<tr height="20">   
								<td width="100%" colspan="4">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No"  name="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="From.Place" name="From.Place"  ondblclick="callShowDiv(this);"><%=label.get("From.Place")%></label> <font color="red">*</font> </td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="To.Place"  name="To.Place" ondblclick="callShowDiv(this);"><%=label.get("To.Place")%></label> <font color="red">*</font> </td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Journey.Mode-Class" name="Journey.Mode-Class"  ondblclick="callShowDiv(this);"><%=label.get("Journey.Mode-Class")%></label> <font color="red">*</font> </td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="Journey.Date" name="Journey.Date"  ondblclick="callShowDiv(this);"><%=label.get("Journey.Date")%></label><font color="red">*</font>
										</td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="jourDtlTime" name="jourDtlTime"  ondblclick="callShowDiv(this);"><%=label.get("jourDtlTime")%></label>
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
											<td width="5%" colspan="1" class="sortableTD"><%=i1%></td>
											<td width="20%" colspan="1" class="sortableTD">
											
											        <%
			      	   		                               String jourFromPlace = (String)request.getAttribute("jourFromPlace"+i1);  
			      	   		                               String jourFromPlaceId = (String)request.getAttribute("jourFromPlaceId"+i1);   
			      	   		                               
			      	   		                             String jourToPlace = (String)request.getAttribute("jourToPlace"+i1);  
		      	   		                                 String jourToPlaceId = (String)request.getAttribute("jourToPlaceId"+i1);   
		      	   		                               
	      	   		                               %>
		      	   		                               <s:textfield name ="<%="jourFromPlace"+i1 %>"   value="<%=jourFromPlace%>" readonly="true" size="15"/> 
		      	   		                                <s:hidden name ="<%="jourFromPlaceId"+i1%>"  value="<%=jourFromPlaceId%>" />
		      	   		                                <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
		      	   		                                 onclick = "callFromPlace('<s:property value="<%=""+i1%>"/>')">   
										    </td>
											 <td width="20%" colspan="1" class="sortableTD"> 
											            <s:textfield name ="<%="jourToPlace"+i1 %>"   value="<%=jourToPlace%>" readonly="true" size="15"/> 
		      	   		                                <s:hidden name ="<%="jourToPlaceId"+i1%>"  value="<%=jourToPlaceId%>" /> 
		      	   		                                <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
		      	   		                                 onclick = "callToPlace('<s:property value="<%=""+i1%>"/>')">    
		      	   		                                
											  </td>
											<td width="25%" colspan="1" class="sortableTD"> 
											
											       <%
			      	   		                               String trModeClass = (String)request.getAttribute("trModeClass"+i1);  
			      	   		                               String trModeClassId = (String)request.getAttribute("trModeClassId"+i1);   
	      	   		                               %>
		      	   		                               <s:textfield name ="<%="trModeClass"+i1 %>"   value="<%=trModeClass%>" readonly="true"/> 
		      	   		                                <s:hidden name ="<%="trModeClassId"+i1%>"  value="<%=trModeClassId%>" />
						  						         <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
		      	   		                                 onclick = "callJourneyClass('<s:property value="<%=""+i1%>"/>')">    
											
											</td>
											<td nowrap="nowrap" colspan="1" class="sortableTD"> <input type="text"   name="jourDate" id="jourDate<%=i1%>" value="<s:property value="jourDate"/>" size="10"  onblur="return validateDate('jourDate<%=i1%>','Journey.Date');" 
												 onkeypress="return numbersWithHiphen();"  maxlength="10" ondblclick="javascript:NewCal('<%="jourDate"+i1%>','DDMMYYYY');" > 
												 </td>
											<td nowrap="nowrap" colspan="1" class="sortableTD">
											<input type="text"
												name="jourTime" size="5"  id="jourTime<%=i1%>"  value='<s:property value="jourTime"/>'
												onkeypress="return numbersWithColon();" maxlength="5"  onblur="return validateTime('<%="jourTime"+i1%>','jourDtlTime');" ></td>
											<td width="5%" colspan="1" class="sortableTD"><input
												type="checkbox" name="jourChkBox" id="jourChkBox<%=i1%>"
												onclick="travelDeleteCheckbx('<%=i1%>');"> <input
												type="hidden" name="hidHourChkBox" id="hidHourChkBox<%=i1%>">
											</td>
										</tr>
										<script>	
		                                  travelArr[<%=j1%>] = document.getElementById('jourDate'+<%=i1%>).value;   
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
				<td height="2" colspan="3"></td>
			</tr>
			<tr id="localConTable" style="display:<%=dataLocal%>;" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >

							<tr height="20">
								<td width="82%" colspan="2"><strong><label  class = "set"  id="Local.Conveyance.Details" name="Local.Conveyance.Details"  ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance.Details")%></label> </strong></td>
					 
								<td colspan="1">
								<s:submit cssClass="add"  value="   Add   " onclick="callLocalConAdd();" /> </td> 
								<td colspan="1"> <s:submit cssClass="add" name="DeleteLocal"
									value="   Delete   " onclick="return callLocalConDelete();" />
								</td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="4">
								<table class="formbg" width="100%">
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" name="Sr.No"  ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="City"  name="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Source" name="Source"  ondblclick="callShowDiv(this);"><%=label.get("Source")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" name="From.Date"  ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Time" name="From.Time" ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date" name="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Time" name="To.Time" ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
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
											<td width="5%" colspan="1" class="sortableTD"><%=p1%></td>
											<td width="25%" colspan="1" class="sortableTD">
											
										        <%
		      	   		                               String localCity = (String)request.getAttribute("localCity"+p1);  
		      	   		                               String localCityId = (String)request.getAttribute("localCityId"+p1);   
      	   		                               %>
	      	   		                                <s:textfield name ="<%="localCity"+p1 %>"   value="<%=localCity%>"  readonly="true" size="18"/> 
	      	   		                                <s:hidden name ="<%="localCityId"+p1%>"  value="<%=localCityId%>" /> 
	      	   		                                <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
	      	   		                                 onclick = "callLocalCity('<s:property value="<%=""+p1%>"/>')">   
											
											</td>
											<td width="25%" colspan="1" class="sortableTD"><s:textfield
												name="localSource" size="25" /></td>
											<td width="10%" colspan="1" class="sortableTD">
											<input type="text"   name="localFromDate" id="localFromDate<%=p1%>" value="<s:property value="localFromDate"/>" size="10"   onblur="return validateDate('localFromDate<%=p1%>','From.Date');" 
												 onkeypress="return numbersWithHiphen();"  maxlength="10" ondblclick="javascript:NewCal('<%="localFromDate"+p1%>','DDMMYYYY');" > 
											 
											 </td>
											<td width="10%" colspan="1" class="sortableTD"><input type="text"
												name="localFromTime" size="5" name="localFromTime"    value='<s:property value="localFromTime"/>'
												onkeypress="return numbersWithColon();" maxlength="5" id="localFromTime<%=p1%>"  onblur="return validateTime('<%="localFromTime"+p1%>','From.Time');"  ></td>
											<td width="10%" colspan="1" class="sortableTD">
											<input type="text"   name="localToDate" id="localToDate<%=p1%>" value="<s:property value="localToDate"/>" size="10"  onblur="return validateDate('localToDate<%=p1%>','To.Date');" 
												 onkeypress="return numbersWithHiphen();"  maxlength="10" ondblclick="javascript:NewCal('<%="localToDate"+p1%>','DDMMYYYY');" > 
											  </td>
											<td width="10%" colspan="1" class="sortableTD"><input type="text"   value="<s:property value="localToTime"/>"
												name="localToTime" size="5" id="localToTime<%=p1%>" onblur="return validateTime('<%="localToTime"+p1%>','To.Time');" 
												onkeypress="return numbersWithColon();" maxlength="5" ></td>
											<td width="5%" colspan="1" class="sortableTD"><input
												type="checkbox" name="localChkBox" id="localChkBox<%=p1%>"
												onclick="localDeleteCheckbx('<%=p1%>');"> <input
												type="hidden" name="hidLocalChkBox"
												id="hidLocalChkBox<%=p1%>"></td>
										</tr>
										<script>	
		                                  localArr[<%=q1%>] = document.getElementById('localFromDate'+<%=p1%>).value;   
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
				<td height="2" colspan="3"></td>
			</tr>
			<tr id="lodgTable" style="display:<%=dataLodg %>" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" >

							<tr height="20">
								<td width="82%" colspan="2"><strong><label  class = "set"  id="Lodging.Details" name="Lodging.Details"  ondblclick="callShowDiv(this);"><%=label.get("Lodging.Details")%></label> </strong></td>
							 	<td colspan="1">
								 <s:submit cssClass="add" 	 value="   Add   "  onclick="callLodgAdd();" /> </td>
								 <td colspan="1"> <s:submit name="DeleteLodg" cssClass="delete"
									value="   Delete   " onclick="return callLodgDelete();" /></td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="4">
								<table class="formbg" width="100%" class="formbg" >
									<tr>
										<td width="2%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" name="Sr.No"  ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="18%" colspan="1" class="formth"><label  class = "set"  id="Hotel.Type" name="Hotel.Type"  ondblclick="callShowDiv(this);"><%=label.get("Hotel.Type")%></label> <font color="red">*</font> </td>
										<td width="12%" colspan="1" class="formth"><label  class = "set"  id="Room.Type" name="Room.Type"  ondblclick="callShowDiv(this);"><%=label.get("Room.Type")%></label> <font color="red">*</font> </td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="City" name="City" ondblclick="callShowDiv(this);"><%=label.get("City")%></label> <font color="red">*</font> </td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="Preferred.location" name="Preferred.location"  ondblclick="callShowDiv(this);"><%=label.get("Preferred.location")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="From.Date" name="From.Date" ondblclick="callShowDiv(this);"><%=label.get("From.Date")%></label> <font color="red">*</font> </td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="From.Time" name="From.Time"  ondblclick="callShowDiv(this);"><%=label.get("From.Time")%></label> </td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="To.Date"  name="To.Date" ondblclick="callShowDiv(this);"><%=label.get("To.Date")%></label> <font color="red">*</font> </td>
										<td width="8%" colspan="1" class="formth"><label  class = "set"  id="To.Time" name="To.Time"  ondblclick="callShowDiv(this);"><%=label.get("To.Time")%></label></td>
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
											<td width="2%" colspan="1" class="sortableTD"><%=s1%></td>
											<td width="18%" colspan="1" class="sortableTD">
											
											        <%
			      	   		                               String hotelType = (String)request.getAttribute("hotelType"+s1);  
			      	   		                               String hotelTypeId = (String)request.getAttribute("hotelTypeId"+s1);  
			      	   		                               
			      	   		                              String roomType = (String)request.getAttribute("roomType"+s1);  
		      	   		                                  String roomTypeId = (String)request.getAttribute("roomTypeId"+s1);
	      	   		                               %>
		      	   		                               <s:textfield name ="<%="hotelType"+s1 %>"   value="<%=hotelType%>" readonly="true" size="6"/> 
		      	   		                                <s:hidden name ="<%="hotelTypeId"+s1%>"  value="<%=hotelTypeId%>" />
						  						         <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
		      	   		                                 onclick = "callHotelType('<s:property value="<%=""+s1%>"/>')">   
											  </td>
											<td width="15%" colspan="1" class="sortableTD">
											 <s:textfield name ="<%="roomType"+s1 %>"   value="<%=roomType%>" readonly="true" size="6"/> 
		      	   		                                <s:hidden name ="<%="roomTypeId"+s1%>"  value="<%=roomTypeId%>" />
						  						         <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
		      	   		                                 onclick = "callRoomType('<s:property value="<%=""+s1%>"/>')"> 
											 </td>
											<td width="15%" colspan="1" class="sortableTD">
											        <%
		      	   		                               String lodgCity = (String)request.getAttribute("lodgCity"+s1);  
		      	   		                               String lodgCityId = (String)request.getAttribute("lodgCityId"+s1);   
      	   		                                   %>
	      	   		                                <s:textfield name ="<%="lodgCity"+s1 %>"   value="<%=lodgCity%>"  readonly="true" size="6"/> 
	      	   		                                <s:hidden name ="<%="lodgCityId"+s1%>"  value="<%=lodgCityId%>" /> 
	      	   		                                <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
	      	   		                                 onclick = "callLodgCity('<s:property value="<%=""+s1%>"/>')">    
											 </td>
											<td width="15%" colspan="1" class="sortableTD"><s:textfield
												name="lodgLocation" size="12"  /></td>
											<td width="10%" colspan="1" class="sortableTD">
											<input type="text"   name="lodgFromdate" id="lodgFromdate<%=s1%>" value="<s:property value="lodgFromdate"/>" size="8"  onblur="return validateDate('lodgFromdate<%=s1%>','From.Date');" 
												 onkeypress="return numbersWithHiphen();"  maxlength="10" ondblclick="javascript:NewCal('<%="lodgFromdate"+s1%>','DDMMYYYY');" > 
											  </td>
											<td width="8%" colspan="1" class="sortableTD"><input type="text"    value="<s:property value="lodgFromtime"/>" 
												name="lodgFromtime" size="4" id="lodgFromtime<%=s1%>"  onblur="return validateTime('<%="lodgFromtime"+s1%>','From.Time');" ></td>
											<td width="10%" colspan="1" class="sortableTD">
												<input type="text"   name="lodgTodate" id="lodgTodate<%=s1%>" value="<s:property value="lodgTodate"/>" size="8"  onblur="return validateDate('lodgTodate<%=s1%>','To.Date');" 
												 onkeypress="return numbersWithHiphen();"  maxlength="10" ondblclick="javascript:NewCal('<%="lodgTodate"+s1%>','DDMMYYYY');" > 
										 </td>
											<td width="8%" colspan="1"  ><input type="text" id="lodgTotime<%=s1%>"     value="<s:property value="lodgTotime"/>" 
												name="lodgTotime" size="4" onblur="return validateTime('<%="lodgTotime"+s1%>','To.Time');"  ></td>
											<td width="5%" colspan="1" class="sortableTD"><input
												type="checkbox" name="lodgChkBox" id="lodgChkBox<%=s1%>"
												onclick="lodgDeleteCheckbx('<%=s1%>');"> <input
												type="hidden" name="hidLodgChkBox" id="hidLodgChkBox<%=s1%>">
											</td>
											<script>	
		                                  lodgArr[<%=t1%>] = document.getElementById('lodgTodate'+<%=s1%>).value;   
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
				<td height="2" colspan="3"> </td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Advance.Details" name="Advance.Details"  ondblclick="callShowDiv(this);"><%=label.get("Advance.Details")%></label> </STRONG></td>

							</tr>

							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Advance.Amount"   name="Advance.Amount" ondblclick="callShowDiv(this);"><%=label.get("Advance.Amount")%></label> :</td>
								<td width="25%" colspan="1"><s:textfield size="25" 
									name="advanceAmt" /></td>
								<td width="20%" colspan="1"><label  class = "set"  id="Preferred.Payment.Mode" name="Preferred.Payment.Mode" ondblclick="callShowDiv(this);"><%=label.get("Preferred.Payment.Mode")%></label> :</td>
								<td width="25%" colspan="1"><s:select name="payMode"
									headerKey="B" headerValue="Select"
									list="#{'C':'Cash','Q':'Cheque','S':'Salary','T':'Transfer'}" />
								</td>
							</tr>
							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Expected.Settlement.Date"  name="Expected.Settlement.Date" ondblclick="callShowDiv(this);"><%=label.get("Expected.Settlement.Date")%></label><font color="red">*</font>:</td>
								<td width="25%" colspan="1"><s:textfield name="settleDate"  
									size="10" onkeypress="return numbersWithHiphen();"  onblur="return validateDate('paraFrm_settleDate','Date');"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_settleDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
						
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
				<td height="2" colspan="3"> </td>
			</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="1" width="80%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2"  >
						<tr height="20">
							<td width="20%" colspan="4"><STRONG><label
								class="set" id="Identity.Details" name="Identity.Details"
								ondblclick="callShowDiv(this);"><%=label.get("Identity.Details")%></label>
							</STRONG></td>

						</tr>

						<tr height="20">
							<td width="15%" colspan="1"><label class="set" id="ID.Proof" name="ID.Proof"
								ondblclick="callShowDiv(this);"><%=label.get("ID.Proof")%></label>
							:</td>
							<td width="25%" colspan="1"><s:select name="idProof"
								headerKey="B" headerValue="Select"
								list="#{'V':'Voter Identity Card ','P':'Passport','I':' PAN Card','D':'Driving Licence ',
										'G':'Photo identity cards issued by Central/State Government'}"
								theme="simple" /></td>
							<td width="10%" colspan="1"><label class="set"
								id="ID.Number" ondblclick="callShowDiv(this);"><%=label.get("ID.Number")%></label>
							:</td>
							<td width="30%" colspan="1"><s:textfield size="25"
								name="idProofNumber"  maxlength="50" /></td>
						</tr>
						<tr height="20">
							<td width="15%" colspan="1"><label class="set" id="applicant.Comments" name="applicant.Comments"
								ondblclick="callShowDiv(this);"><%=label.get("Comments")%></label>
							:</td>
							<td width="80%" colspan="3"><s:textarea name="appComments"
								readonly="true" cols="110" rows="2"></s:textarea></td>

						</tr>
						<tr height="20">
							<td width="15%" colspan="1"><label class="set"
								id="approverComments" name="approverComments" ondblclick="callShowDiv(this);"><%=label.get("approverComments")%></label>
							:</td>
							<td width="80%" colspan="3"><s:textarea
								name="approverComments" cols="110" rows="2"></s:textarea></td>
						</tr>

						<tr>
							<td colspan="3" height="2"> </td>
						</tr> <s:hidden name="commentFlag" /> 
						<s:if test="commentFlag">
							<tr>
								<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">

									<tr>
										<td>
										<table width="98%" border="0" align="center" cellpadding="0"
											cellspacing="0">

											<tr>
												<td colspan="5" class="formhead"><strong
													class="forminnerhead">Approver Details </strong></td>
											</tr>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">
									<tr>
										<td class="formtext">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											class="sortable">
											<tr>
												<td width="5%" class="formth">Sr.No.</td>
												<td width="35%" class="formth">Approver Name</td>
												<td width="20%" class="formth">Approved Date</td>
												<td width="40%" class="formth">Comments</td>
											</tr>
											<% int j = 0; %>
											<s:iterator value="apprList">
												<tr>
													<td class="sortableTD" width="5%" align="center"><%=++j%></td>
													<td class="sortableTD" width="35%"><s:property
														value="apprEmpName" /></td>
													<td class="sortableTD" width="20%" align="left"><s:property
														value="apprDate" /></td>
													<td class="sortableTD" width="40%" align="left"><s:property
														value="apprComments" /></td>
												</tr>
											</s:iterator>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						 </s:if>
						 <tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">&nbsp;  
					 
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
	</table>
	</s:form>
		
		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>  
<script> 
 
  function backFun()
 { 
        document.getElementById('paraFrm').action = "TravelApprNew_callStatus.action?status=P";
        document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main"; 
 }
   
   
 function addDays()
 { 
     settleNumOfDay=  document.getElementById('paraFrm_settleNumOfDay').value;
     settleFlag=  document.getElementById('paraFrm_settleFlag').value; 
     var tourDate ="";
    if(settleNumOfDay!="")
    {
       if(settleFlag=="F")
       {
	    tourDate=  document.getElementById('paraFrm_tourStartDate').value;
	    }
	    
	    if(settleFlag=="T")
        {
	    tourDate=  document.getElementById('paraFrm_tourEndDate').value;
	    }
	     if(settleFlag=="A")
        {
	     tourDate=  document.getElementById('paraFrm_appDate').value;
	    }
	    
	    dateVal=tourDate.split("-"); 
	 	var myDate = new Date(dateVal[2],dateVal[1]-1,dateVal[0]);  
	 	myDate.setDate(myDate.getDate()+ eval(settleNumOfDay));  
	  
	 	chkDate= myDate.getDate() + '-' +(myDate.getMonth()+1) + '-' + myDate.getFullYear() ;
	 	settleDate =document.getElementById('paraFrm_settleDate').value;  
		   
		 if(dateDifference1( settleDate,chkDate, "paraFrm_tourStartDate",settleFlag))
		 {
		  return true;
		 }
	 }
	  return true;
  
 }
    
  function dateDifference1(fromDate, toDate, fieldName, settleFlag){
	
	strDate1 = fromDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = toDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	if(endtime <= starttime) 
	{   
	      var conf=confirm("Expected Settlement date should not be greater than "+chkDate 
	      +"\n Do you like to continue ?");
	        if(conf) 
		  	 	 {
		  	 	   return true;
		  	 	 }else
		  	 	 {
		  	 	    document.getElementById(fieldName).focus(); 
		  	 	    return false;
		  	 	 } 
	}
	return true;
}
 
function callLodgCity(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9LodgCity.action');  
    }
function callLocalCity(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9LocalCity.action');  
    }
 
function callFromPlace(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9FromPlace.action');  
    }
    
    function callToPlace(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9ToPlace.action');  
    }
    

    function callJourneyClass(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9TravelMode.action');  
    }
      function callHotelType(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9HotelType.action');  
    }
    
      function callRoomType(id)
    {
        document.getElementById('paraFrm_rowId').value=id; 
        callsF9(500,325,'TravelApplication_f9RoomType.action');  
    }
    
   

 function cancelFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_cancel.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
 
function callForReadOnly()
{    
    	var formElements = document.forms['paraFrm'].elements;
 
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
	       
	     if(document.getElementById('paraFrm_empGradeId').value==""||document.getElementById('paraFrm_empGradeId').value=="null")
	       {
	        alert("Please define the employee garde for selected employee.");
	        return false;
	       }
	        
	    var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelApplication_TravelPolicy.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
    }
    
     function callTrackForTravel()
      {  
      if(document.getElementById('paraFrm_trAppId').value=="") 
      {
        alert("Please save the application.");
        return false;
      }else
      {
	    var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelApplication_TrackForTravelMode.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
     }
     
     function callTrackForLocal()
      {  
	 if(document.getElementById('paraFrm_trAppId').value=="") 
       {
        alert("Please save the application.");
        return false;
      }else
      {
	    var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelApplication_TrackForLocal.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
     }
     
      function callTrackForLodg()
      { 
      if(document.getElementById('paraFrm_trAppId').value=="") 
	      {
	        alert("Please save the application.");
	        return false;
	      }else
        { 
	    var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelApplication_TrackForLodg.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
     }
     
    
  function addnewFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_addNew.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
     function saveFun()
     {   
      
      if(!callSave())  {      return false;     }  
	      
	  if(!callIteratorValidate()) {  return false;    } 
	  
	   if(document.getElementById('paraFrm_settleDate').value=="")  
       {
	        alert("Please enter Expected Settlement Date.") ;
	        document.getElementById('paraFrm_settleDate').focus();
	        return false;
       }
	       
       if(!callValidMode()) {  return false; } 
	      
       if(!callValidHotelType())    {  return false;     }  
         
     
       
         if(!addDays())    {   return false;     } 
          
         document.getElementById('paraFrm_approverPanelFlag').value="true";
         document.getElementById('paraFrm').action = "TravelApplication_saveForApproval.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
       function callSave()
    {
        if(document.getElementById('paraFrm_empId').value=="")
	       {
	        alert("Please select the employee.");
	        return false;
	       }
	       
	    if(document.getElementById('paraFrm_tourStartDate').value=="")  
         {
        alert("Please enter/select tour start Date.") ;
        document.getElementById('paraFrm_tourStartDate').focus();
        return false;
       }
       
       if(document.getElementById('paraFrm_tourEndDate').value=="")  
       {
        alert("Please enter/select tour end Date.") ;
        document.getElementById('paraFrm_tourEndDate').focus();
        return false;
       } 
      
       if(document.getElementById('paraFrm_hidAppForRadio').value=='Y')
       {   
	       var guestName = LTrim(document.getElementById('paraFrm_guestName').value); 
	       if(guestName=="")
	       {
	        alert("Please enter guest name.") ; 
	        document.getElementById('paraFrm_guestName').focus();
	        return false;
	       }
       }
	    
	      var travelReuest  = document.getElementById('paraFrm_travelRequest').value;
	       var travelReuestChk = LTrim(travelReuest);  
	 		if(travelReuestChk=="")  
	        {
	        alert("Please enter travel request name.") ; 
	        document.getElementById('paraFrm_travelRequest').focus();
	        return false;
	       }
    
       if(document.getElementById('paraFrm_travelPurpose').value=="")  
       {
        alert("Please select travel purpose.") ; 
        return false;
       }
       
        if(document.getElementById('paraFrm_tourLocTypeId').value=="")  
       {
        alert("Please select Tour location type.") ; 
        return false;
       }
        
	      var fromDate = document.getElementById('paraFrm_tourStartDate').value;
	      var toDate=document.getElementById('paraFrm_tourEndDate').value;
	      var appDate=document.getElementById('paraFrm_appDate').value;
	      var settleDate = document.getElementById('paraFrm_settleDate').value;
	      
	        if(!dateDifferenceEqual(appDate, fromDate,"paraFrm_tourStartDate", "Application.Date", "Tour.Start.Date"))
	       {
	        return false;
	       } 
	       
	         if(!dateDifferenceEqual(appDate, toDate,"paraFrm_tourEndDate", "Application.Date", "Tour.end.Date"))
	       {
	        return false;
	       } 
	       
	      if(!dateDifferenceEqual(fromDate, toDate,"paraFrm_tourEndDate", "Tour.Start.Date", "Tour.end.Date"))
	       {
	        return false;
	       } 
	       
	      if(!dateDifferenceEqual(fromDate, settleDate,"paraFrm_settleDate", "Tour.Start.Date", "Expected.Settlement.Date"))
	       {
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
	     if(document.getElementById('paraFrm_hidStatus').value=="Approved"){
			alert("Application is approved, so cannot be deleted.");
			return false;
		  }
		  if(document.getElementById('paraFrm_hidStatus').value=="Rejected"){
			alert("Application is Rejected, so cannot be deleted.");
			return false;
		  }else
		  {    var conf=confirm("Do you really want to delete this record ?");
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
        var j =1;
   		var m =0;
	    for(i=0;i<travelArr.length;i++)
	    {    if(document.getElementById('hidHourChkBox'+j).value=='Y')
		     { m++;
		     } 
		     j++; 
	    }
	    if(travelArr.length==m)
	    {
	     document.getElementById('selectAllJour').checked=true;
	    }	else{
	   		 document.getElementById('selectAllJour').checked=false;
	    } 
  }
  
 function callAddTravel()
  {
            document.getElementById('paraFrm_approverPanelFlag').value="true";
	        document.getElementById('paraFrm').action='TravelApplication_addTravelMode.action'; 
	        document.getElementById('paraFrm').submit();	
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
	
	if(travelArr.length==1)
	{ 
	  alert("You cannot deleted the record,There should be more than one record for deletion");
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
		
		var y =1;
		var z=0;		
		 for(x=0;x<travelArr.length;x++)
	    {  
	      if(document.getElementById('hidHourChkBox'+y).value=='Y') 
	      { z++;
	      }
	      y++;
	    }  
	    if(travelArr.length==z)
	    {
	        alert("You cannot deleted the all record,please deselect one or more record.");
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
	  	var j =1;
   		var m =0;
	    for(i=0;i<localArr.length;i++)
	    {    if(document.getElementById('hidLocalChkBox'+j).value=='Y')
		     { m++;
		     } 
		     j++; 
	    }
	    if(localArr.length==m)
	    {
	        document.getElementById('selectAllLocal').checked=true;
	    }	else{
	   		 document.getElementById('selectAllLocal').checked=false;
	    }  
  }
   
   function callLocalConAdd()
   {
            document.getElementById('paraFrm_approverPanelFlag').value="true";
            document.getElementById('paraFrm').action='TravelApplication_addLocalCon.action'; 
	        document.getElementById('paraFrm').submit();	 
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
	
	if(localArr.length==1)
	{ 
	   alert("You cannot deleted the record,There should be more than one record for deletion");
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
		
		var y =1;
		var z=0;		
		 for(x=0;x<localArr.length;x++)
	    {  
	      if(document.getElementById('hidLocalChkBox'+y).value=='Y') 
	      { z++;
	      }
	      y++;
	    }  
	    if(localArr.length==z)
	    {
	        alert("You cannot deleted the all record,please deselect one or more record.");
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
	 
	 	var j =1;
   		var m =0;
	    for(i=0;i<lodgArr.length;i++)
	    {    if(document.getElementById('hidLodgChkBox'+j).value=='Y')
		     { m++;
		     } 
		     j++; 
	    }
	    if(lodgArr.length==m)
	    {
	        document.getElementById('selectAllLodg').checked=true;
	    }	else{
	   		 document.getElementById('selectAllLodg').checked=false;
	    } 
  }
  
  function callLodgAdd()
  {
             document.getElementById('paraFrm_approverPanelFlag').value="true";
            document.getElementById('paraFrm').action='TravelApplication_addLodg.action'; 
	        document.getElementById('paraFrm').submit();
   
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
	
	if(lodgArr.length==1)
	{ 
   	 alert("You cannot deleted the record,There should be more than one record for deletion");
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
		
		var y =1;
		var z=0;		
		 for(x=0;x<lodgArr.length;x++)
	    {  
	      if(document.getElementById('hidLodgChkBox'+y).value=='Y') 
	      { z++;
	      }
	      y++;
	    }  
	    if(lodgArr.length==z)
	    {
	        alert("You cannot deleted the all record,please deselect one or more record.");
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
   document.getElementById('paraFrm_guestName').value="";
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
 
 
 //================= FOR APPROVAL=============
  function approveFun()
     {   
   if(!callSave())  {      return false;     }  
	      
	  if(!callIteratorValidate()) {  return false;    } 
	  
	   if(document.getElementById('paraFrm_settleDate').value=="")  
       {
	        alert("Please enter Expected Settlement Date.") ;
	        document.getElementById('paraFrm_settleDate').focus();
	        return false;
       }
	       
       if(!callValidMode()) {  return false; } 
	      
       if(!callValidHotelType())    {  return false;     }  
         
     
       
         if(!addDays())    {   return false;     } 
         
      	 document.getElementById('paraFrm').action = "TravelApplication_saveForApprovalUpadte.action?status=A"; 
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";  
		 
    }
    
     function rejectFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_saveForRejectUpadte.action?status=R"; 
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
   
   
    function callIteratorValidate()
   {
            // for journey Deatils
            var j =1;
		    for(i=0;i<travelArr.length;i++)
		    { 
				     if(document.getElementById('paraFrm_jourFromPlaceId'+j).value=="")
				     {
				      alert("Please select From Place for Journey Details at row no."+j);
				      return false;
				     }
				     
				       if(document.getElementById('paraFrm_jourToPlaceId'+j).value=="")
				     {
				      alert("Please select To Place for Journey Details at row no."+j);
				      return false;
				     }
				     
				    if(document.getElementById('paraFrm_jourFromPlaceId'+j).value==document.getElementById('paraFrm_jourToPlaceId'+j).value)
				     {
				      alert("From place and To place should not be same.");
				      return false;
				     }
				      
				      
				     if(document.getElementById('paraFrm_trModeClassId'+j).value=="")
				     {
				       alert("Please select Travel Mode-Class for Journey Details at row no."+j);
				       return false;
				     }
				     
				     if(document.getElementById('jourDate'+j).value=="")
				     {
				       alert("Please enter/select Journey Date for Journey Details at row no."+j);
				       return false;
				     }  
				         var fromDate = document.getElementById('jourDate'+j).value;   
				 	     var startDate = document.getElementById('paraFrm_tourStartDate').value;   
				 	      var endDate = document.getElementById('paraFrm_tourEndDate').value;   
				 	     if(!dateDifferenceEqual(startDate, fromDate,"jourDate"+k, "Tour.Start.Date", "Journey.Date"))
					       {
					        return false;
					       }  
					       
					       if(!dateDifferenceEqual(fromDate, endDate,"jourDate"+k, "Journey.Date", "Tour.end.Date"))
					       { 
					        return false;
					       }  
				     j++; 
		    }  
		    // for local conv
		    var p =1;
		     hidLocalConReq = document.getElementById('paraFrm_hidLocalConReq').value;
		     
		     if(hidLocalConReq=='Y')
		     { 
		      for(q=0;q<localArr.length;q++)
			    {        var fromDate = document.getElementById('localFromDate'+p).value;   
			      		 var fromDate1 = document.getElementById('localToDate'+p).value;  
				 	     var startDate = document.getElementById('paraFrm_tourStartDate').value; 
				 	     var endDate = document.getElementById('paraFrm_tourEndDate').value;   
				 	     if(fromDate!="")
				 	     	{
					 	     if(!dateDifferenceEqual(startDate, fromDate,"localFromDate"+p, "Tour.Start.Date", "From.Date"))
						       {
						        return false;
						       } 
							       
						      if(!dateDifferenceEqual(fromDate,endDate,"localFromDate"+p, "From.Date", "Tour.end.Date"))
						       {
						        return false;
						       } 
					       }
					       
					         if(fromDate1!="")
				 	     	{
					 	     if(!dateDifferenceEqual(startDate, fromDate1,"localToDate"+p, "Tour.Start.Date","To.Date"))
						       {
						        return false;
						       } 
							       
						      if(!dateDifferenceEqual(fromDate1,endDate,"localToDate"+p, "To.Date", "Tour.end.Date"))
						       {
						        return false;
						       } 
					       } 
					} 
		     }
		    
		     // for Lodging Details
            var k =1; 
            hidAccommodationReqFlag = document.getElementById('paraFrm_hidAccommodationReqFlag').value;
             
            if(hidAccommodationReqFlag=='Y')
            {
                for(m=0;m<lodgArr.length;m++)
			    { 
					     if(document.getElementById('paraFrm_hotelTypeId'+k).value=="")
					     {
					      alert("Please select Hotel Type for Lodging Details at row no."+k);
					      return false;
					     }
					     
					       if(document.getElementById('paraFrm_roomTypeId'+k).value=="")
					     {
					     alert("Please select Room Type for Lodging Details at row no."+k);
					      return false;
					     }
					      
					     if(document.getElementById('paraFrm_lodgCityId'+k).value=="")
					     {
					      alert("Please select City for Lodging Details at row no."+k);
					       return false;
					     }
					     
					     if(document.getElementById('lodgFromdate'+k).value=="")
					     {
					      alert("Please enter/select From date for Lodging Details at row no."+k);
					       return false;
					     } 
					     
				 	     var fromDate = document.getElementById('lodgFromdate'+k).value;   
				 	     var startDate = document.getElementById('paraFrm_tourStartDate').value; 
				 	     var endDate = document.getElementById('paraFrm_tourEndDate').value;   
				 	     if(!dateDifferenceEqual(startDate, fromDate,"lodgFromdate"+k, "Tour.Start.Date", "From.Date"))
					       {
					        return false;
					       } 
					       
					      if(!dateDifferenceEqual(fromDate,endDate,"lodgFromdate"+k, "From.Date", "Tour.end.Date"))
					       {
					        return false;
					       } 
					       
					      // for end date 
					     var fromDate1 = document.getElementById('lodgTodate'+k).value;   
				 	     var startDate1 = document.getElementById('paraFrm_tourStartDate').value; 
				 	     var endDate1 = document.getElementById('paraFrm_tourEndDate').value;   
				 	     if(!dateDifferenceEqual(startDate1, fromDate1,"lodgTodate"+k, "Tour.Start.Date", "To.Date"))
					       {
					        return false;
					       }  
					      if(!dateDifferenceEqual(fromDate1,endDate1,"lodgTodate"+k, "To.Date", "Tour.end.Date"))
					       {
					        return false;
					       }  
			      k++; 
			    }  
		   }
		    
		     return true;
   }
   
  function callValidMode()
   { 
   
    validTrMode = document.getElementById('paraFrm_validTrMode').value; 
      
    var flag ="false";
    var count =0;
    var invalidTrMode = "";
    var j =1;
     var k =1;
    if(validTrMode!="")
    {
    for(var i=0;i<travelArr.length;i++)
     { 
    
	    trModeClassId ="*"+document.getElementById('paraFrm_trModeClassId'+j).value+"*"; 
	    trModeClass =document.getElementById('paraFrm_trModeClass'+j).value; 
	       if(validTrMode.indexOf(trModeClassId)==-1)
			    {   
				    if(k==1)
				    {
				     invalidTrMode=invalidTrMode+""+trModeClass;     flag ="true"; 
				     } else{
				     invalidTrMode=invalidTrMode+","+trModeClass;     flag ="true"; 
				     }
				     k++;
				} else { flag ="false"; }
				
			 j++;
			 if( flag =="true")
			 {
			  count=count+1;
			 }
		}	  
	}
	
		if(count>0)	 
		{
		
		   var conf = confirm("You are not entitle to travel by "+invalidTrMode+" journey.Do you want to continue ? ");
			   if(conf==true)
			     { 
			        return true;
			     }else
			     {   return false;
			     }
		} 
    return true;
   }
   
       function callValidHotelType()
   {  
    validHotelType = document.getElementById('paraFrm_validHotelType').value; 
    hotelTypeIdFrst = document.getElementById('paraFrm_hotelTypeId1').value;  
    var flag ="false";
    var count =0;
    var invalidHotelType= "";
    var j =1; 
    var k =1; 
    
    if(validHotelType!="" && hotelTypeIdFrst!="")
     {
	    for(var i=0;i<lodgArr.length;i++)
	     {  
		    hotelTypeId ="*"+document.getElementById('paraFrm_hotelTypeId'+j).value+"*H"; 
		    roomTypeId ="*"+document.getElementById('paraFrm_roomTypeId'+j).value+"*R"; 
		    var appHotRoom = hotelTypeId+roomTypeId;
		    hotelType =document.getElementById('paraFrm_hotelType'+j).value;  
		    roomType =document.getElementById('paraFrm_roomType'+j).value;  
		    
		     if(hotelTypeId!="0" && hotelTypeId !="" && hotelTypeId !="null")
		    { 
		       if(validHotelType.indexOf(appHotRoom)==-1)
				    { 
				    if(k==1)   {
				     invalidHotelType=invalidHotelType+""+hotelType+"-"+roomType;     flag ="true"; 
				      }else
				      {
				      invalidHotelType=invalidHotelType+","+hotelType+"-"+roomType;     flag ="true"; 
				      }
				      k++;
					} else { flag ="false"; }
					
				 j++;
				 if( flag =="true")
				 {
				  count=count+1;
				 }
			 }
			}	  
	 } 
		if(count>0)	 
		{
		   var conf = confirm("You are not entitle for "+invalidHotelType+" Hotel type.Do you want to continue ? ");
			   if(conf==true)
			     { 
			     return true;
			     }else
			     {   return false;
			     }
		} 
    return true;
   }
   
</script>
 