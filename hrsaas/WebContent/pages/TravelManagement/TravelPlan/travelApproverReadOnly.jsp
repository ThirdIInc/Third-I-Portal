    
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
 	System.out.println("dataLodg11===" + dataLodg
 			+ " ====== dataLocal===" + dataLocal);
 %>

<s:form action="TravelApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="1">  
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
				<td colspan="3"> <s:hidden name="hidAppForRadio" />
								<s:hidden name="hidAccommodationReqFlag" />
								<s:hidden name="hidtrArrgFlag" /> <s:hidden name="rowId" />
								<s:hidden name="hidLocalConReq" />  
								<s:hidden name="settleNumOfDay" /> 
								<s:hidden name="settleFlag" /> 
								<s:hidden name="validTrMode" />      
								<s:hidden name="validHotelType" /> 
								<s:hidden name="validRoomType" /> 
								<s:hidden name ="trAppCanStatus" />
	   <s:hidden name="loadFlag" />   <s:hidden name="addNewFlag" />
			<s:hidden name="editFlag" />   <s:hidden name="saveFlag" /> <s:hidden name="hidFlag" /> <s:hidden name="trAppId" />
		
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Employee.Information.loadFlag"  name="Employee.Information.loadFlag" ondblclick="callShowDiv(this);"><%=label.get("Employee.Information.loadFlag")%></label></STRONG></td>

							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Employee.Name" ondblclick="callShowDiv(this);"><%=label.get("Employee.Name")%></label><font color="red">*</font>
								:</td>
								<td colspan="3"><s:hidden name="empToken" /> <s:property value="empToken" />
								  <s:hidden name="empName" />   <s:property value="empName" />
								<s:hidden name="empId" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Branch.Name" ondblclick="callShowDiv(this);"><%=label.get("Branch.Name")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="branchName"  />
								   <s:property value="branchName" />
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Department" ondblclick="callShowDiv(this);"><%=label.get("Department")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden   name="department"  />  <s:property value="department" /></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Designation" ondblclick="callShowDiv(this);"><%=label.get("Designation")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden  name="designation"  />  <s:property value="designation" /> </td>
								<td width="15%" colspan="1"><label  class = "set"  id="Grade" ondblclick="callShowDiv(this);"><%=label.get("Grade")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden  name="empGrade"  />  <s:property value="empGrade" /> 
								<s:hidden name="empGradeId" />
								 </td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Age" ondblclick="callShowDiv(this);"><%=label.get("Age")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden  name="empAge" /> <s:property value="empAge" /> <s:hidden name="empDob" />
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Contact.Number" ondblclick="callShowDiv(this);"><%=label.get("Contact.Number")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden  name="contactNumber" /> <s:property value="contactNumber" />  </td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Status" ondblclick="callShowDiv(this);"><%=label.get("Status")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="hidStatus" /> <s:hidden name="appStatus" />
								<s:hidden name="disAppStatus" />  <s:property value="disAppStatus" /> 
								
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Application.Date" ondblclick="callShowDiv(this);"><%=label.get("Application.Date")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="appDate" />  <s:property value="appDate" /> 
									 
								 </td>
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
				int srNo2 = 0;
				%> <s:iterator value="cityList">
					<input type="hidden" value='<s:property  value="cityName"/>'
						name="cityName" id="cityName<%=srNo2 %>" />
					<s:hidden name="cityId" />
					<script>	
		   cityArr[<%=srNo2%>] = document.getElementById('cityName'+<%=srNo2%>).value;   
		 </script>
					<%
					srNo2++;
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
							cellspacing="2" >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Tour.Details" ondblclick="callShowDiv(this);"><%=label.get("Tour.Details")%></label></STRONG>
								</td>

							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Start.Date" ondblclick="callShowDiv(this);"><%=label.get("Tour.Start.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:hidden name="tourStartDate" />  <s:property value="tourStartDate" />  </td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.end.Date" ondblclick="callShowDiv(this);"><%=label.get("Tour.end.Date")%></label><font
									color="red">*</font> :</td>
								<td width="25%" colspan="1"><s:hidden name="tourEndDate" /> <s:property value="tourEndDate" />
								 </td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Application.For" ondblclick="callShowDiv(this);"><%=label.get("Tour.Application.For")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden name="guestAppFlag" />
								<s:if test="guestAppFlag">
									<s:radio name="appFor" value="%{'G'}" disabled="true"
										list="#{'S':'Self &nbsp;','G':'Guest'}"
										onclick="callCheckForGuest();"></s:radio>
								</s:if> <s:else>
									<s:radio name="appFor" value="%{'S'}" disabled="true"
										list="#{'S':'Self &nbsp;','G':'Guest'}"
										onclick="callCheckForGuest();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1" id="guestCol"><label  class = "set"  id="Guest.Name" ondblclick="callShowDiv(this);"><%=label.get("Guest.Name")%></label> :</td>
								<td width="25%" colspan="1" id="guestColText"><s:hidden   name="guestName" />
								<s:property value="guestName"/>
								</td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Request.Name" ondblclick="callShowDiv(this);"><%=label.get("Travel.Request.Name")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden  name="travelRequest"  />
								<s:property value="travelRequest"/>
								</td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Purpose" ondblclick="callShowDiv(this);"><%=label.get("Travel.Purpose")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:hidden name="travelPurpose"  />  
								<s:property value="travelPurpose"/> <s:hidden  name="travelPurposeId" />  
								</td>
							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Accommodation" ondblclick="callShowDiv(this);"><%=label.get("Accommodation")%></label> :</td>
								<td width="25%" colspan="1"><s:if test="accomFlag">
									<s:radio name="accommodationReq" value="%{'C'}" disabled="true"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:if> <s:else>
									<s:radio name="accommodationReq" value="%{'S'}"  disabled="true"
										list="#{'S':'Self &nbsp;','C':'Company'}"
										onclick="callForAccod();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Travel.Arrangement" ondblclick="callShowDiv(this);"><%=label.get("Travel.Arrangement")%></label> :</td>

								<td width="25%" colspan="1"><s:if test="trArFlag">
									<s:radio name="trArrg" value="%{'C'}"  disabled="true"
										list="#{'S':'Self &nbsp;','C':'Company'}"></s:radio>
								</s:if> <s:else>
									<s:radio name="trArrg" value="%{'S'}"  disabled="true"
										list="#{'S':'Self &nbsp;','C':'Company'}"></s:radio>
								</s:else></td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Local.Conveyance" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance")%></label> :</td>
								<td width="25%" colspan="1"><s:if test="localFlag">
									<s:radio name="localConReq" value="%{'C'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"  disabled="true"
										onclick="callForLocalConv();"></s:radio>
								</s:if> <s:else>
									<s:radio name="localConReq" value="%{'S'}"
										list="#{'S':'Self &nbsp;','C':'Company'}"  disabled="true"
										onclick="callForLocalConv();"></s:radio>
								</s:else></td>
								<td width="15%" colspan="1"><label  class = "set"  id="Tour.Location.Type" ondblclick="callShowDiv(this);"><%=label.get("Tour.Location.Type")%></label><font color="red">*</font>  :</td>
								<td width="25%" colspan="1"><s:hidden name="tourLocTypeId" /><s:hidden 	name="tourLocType" />  <s:property	value="tourLocType" /> 
								</td>
							</tr>
							
                          	<tr height="20"> 
							  <td width="15%" colspan="1"><label  class = "set"  id="FoodType" ondblclick="callShowDiv(this);"><%=label.get("FoodType")%></label> :</td>
								<td width="25%" colspan="1"><s:hidden
									name="foodType"   />  <s:hidden
									name="foodTypeId"   />  <s:property value="foodType"/>
									  </td>
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
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Journey.Details" ondblclick="callShowDiv(this);"><%=label.get("Journey.Details")%></label> </strong></td>
								<td colspan="1">  	</td>
							</tr>
							<tr height="20">
								<td width="100%" colspan="2">
								<table class="formbg" width="100%" class="formbg" >
									<tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set"  id="Sr.No" ondblclick="callShowDiv(this);"><%=label.get("Sr.No")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="From.Place" ondblclick="callShowDiv(this);"><%=label.get("From.Place")%></label></td>
										<td width="20%" colspan="1" class="formth"><label  class = "set"  id="To.Place" ondblclick="callShowDiv(this);"><%=label.get("To.Place")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  id="Journey.Mode-Class" ondblclick="callShowDiv(this);"><%=label.get("Journey.Mode-Class")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="Journey.Date" ondblclick="callShowDiv(this);"><%=label.get("Journey.Date")%></label>
										</td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  id="jourDtlTime" ondblclick="callShowDiv(this);"><%=label.get("jourDtlTime")%></label>
										</td>
										<td width="5%" colspan="1" class="formth"> &nbsp;</td>
									</tr>
									<%
									int i2 = 1;
									%>
									<%
									int j2 = 0;
									%>
									<s:iterator value="trModeClassList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=i2%></td>
											<td width="20%" colspan="1" class="sortableTD">
											
											        <%
			      	   		                               String jourFromPlace1 = (String)request.getAttribute("jourFromPlace"+i2);  
			      	   		                               String jourFromPlaceId1 = (String)request.getAttribute("jourFromPlaceId"+i2);   
			      	   		                               
			      	   		                             String jourToPlace1 = (String)request.getAttribute("jourToPlace"+i2);  
		      	   		                                 String jourToPlaceId1 = (String)request.getAttribute("jourToPlaceId"+i2);   
		      	   		                               
	      	   		                               %>
	      	   		                                   <%=jourFromPlace1%>
		      	   		                                <s:hidden name ="<%="jourFromPlace"+i2 %>"   value="<%=jourFromPlace1%>" /> 
		      	   		                                <s:hidden name ="<%="jourFromPlaceId"+i2%>"  value="<%=jourFromPlaceId1%>" />
										  </td>
											<td width="20%" colspan="1" class="sortableTD">
											  <%=jourToPlace1%> 
											  <s:hidden name ="<%="jourToPlace"+i2 %>"   value="<%=jourToPlace1%>" /> 
		      	   		                       <s:hidden name ="<%="jourToPlaceId"+i2%>"  value="<%=jourToPlaceId1%>" />
											
										 </td>
											<td width="25%" colspan="1" class="sortableTD">
											
											 <%
			      	   		                               String trModeClass1 = (String)request.getAttribute("trModeClass"+i2);  
			      	   		                               String trModeClassId1 = (String)request.getAttribute("trModeClassId"+i2);   
	      	   		                               %>
		      	   		                                <s:hidden name ="<%="trModeClass"+i2 %>"   value="<%=trModeClass1%>" /> 
		      	   		                                <s:hidden name ="<%="trModeClassId"+i2%>"  value="<%=trModeClassId1%>" />
											        <%=trModeClass1%>
											 </td>
											<td nowrap="nowrap" colspan="1" class="sortableTD"><s:hidden name="jourDate" /> <s:property value="jourDate" /> &nbsp; </td>
											<td nowrap="nowrap" colspan="1" class="sortableTD"><s:hidden name="jourTime" /> <s:property value="jourTime" /> &nbsp; </td>
											<td width="5%" colspan="1" class="sortableTD"> 
											</td>
										</tr>
										<script>	
		                                  travelArr[<%=j2%>] = document.getElementById('jourDate').value;   
		                            </script>
										<%
										i2++;
										%>
										<%
										j2++;
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
			 
			<tr id="localConTable" style="display:<%=dataLocal%>;" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"  >

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Local.Conveyance.Details" ondblclick="callShowDiv(this);"><%=label.get("Local.Conveyance.Details")%></label> </strong></td>
								<td colspan="1"> &nbsp;
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
										<td width="5%" colspan="1" class="formth"> &nbsp;</td>
									</tr>
									<%
									int p2 = 1;
									%>
									<%
									int q2 = 0;
									%>

									<s:iterator value="localConList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=p2%></td>
											<td width="25%" colspan="1" class="sortableTD">
											  <%
		      	   		                               String localCity1 = (String)request.getAttribute("localCity"+p2);  
		      	   		                               String localCityId1 = (String)request.getAttribute("localCityId"+p2);   
      	   		                               %>
      	   		                               <%=localCity1%>
	      	   		                                <s:hidden name ="<%="localCity"+p2 %>"   value="<%=localCity1%>"  /> 
	      	   		                                <s:hidden name ="<%="localCityId"+p2%>"  value="<%=localCityId1%>" />  
											<input
												type="hidden" name="localCity" 	value='<s:property  value="localCity"/>'
												id="localCity<%=p2%>" size="25"  /> <s:property  value="localCity"/> </td>
											<td width="25%" colspan="1" class="sortableTD"><s:hidden name="localSource" /> <s:property  value="localSource"/> &nbsp; </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden 	name="localFromDate" /> <s:property  value="localFromDate"/> &nbsp;  </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="localFromTime" /> <s:property  value="localFromTime"/> &nbsp;  </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden name="localToDate" />  <s:property  value="localToDate"/> &nbsp; </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="localToTime" /> <s:property  value="localToTime"/> &nbsp; </td>
											<td width="5%" colspan="1" class="sortableTD"> <input	type="hidden" name="hidLocalChkBox" id="hidLocalChkBox<%=p2%>"></td>
										</tr>
										<script>	
		                                  localArr[<%=q2%>] = document.getElementById('localToDate').value;   
		                            </script>
										<%
										q2++;
										%>
										<%
										p2++;
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
 
			
			<tr id="lodgTable" style="display:<%=dataLodg %>" >
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >

							<tr height="20">
								<td width="82%" colspan="1"><strong><label  class = "set"  id="Lodging.Details" ondblclick="callShowDiv(this);"><%=label.get("Lodging.Details")%></label> </strong></td>
								<td colspan="1"> </td>
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
										<td width="5%" colspan="1" class="formth"> </td>
									</tr>
									<%
									int s2 = 1;
									%>
									<%
									int t2 = 0;
									%>
									<s:iterator value="lodgList">
										<tr>
											<td width="5%" colspan="1" class="sortableTD"><%=s2%></td>
											<td width="12%" colspan="1" class="sortableTD">
											         <%
			      	   		                               String hotelType1 = (String)request.getAttribute("hotelType"+s2);  
			      	   		                               String hotelTypeId1 = (String)request.getAttribute("hotelTypeId"+s2);  
			      	   		                               
			      	   		                              String roomType1 = (String)request.getAttribute("roomType"+s2);  
		      	   		                                  String roomTypeId1 = (String)request.getAttribute("roomTypeId"+s2);
	      	   		                               %> 
		      	   		                                <s:hidden name ="<%="hotelType"+s2 %>"   value="<%=hotelType1%>" /> 
		      	   		                                <s:hidden name ="<%="hotelTypeId"+s2%>"  value="<%=hotelTypeId1%>" />
		      	   		                                <%=hotelType1%>
											   </td>
											<td width="12%" colspan="1" class="sortableTD">  
												 <s:hidden name ="<%="roomType"+s2 %>"   value="<%=roomType1%>" /> 
		      	   		                         <s:hidden name ="<%="roomTypeId"+s2%>"  value="<%=roomTypeId1%>" /> 
		      	   		                      <%=roomType1%> 
												</td>
											<td width="15%" colspan="1" class="sortableTD">
											
											      <%
		      	   		                               String lodgCity1 = (String)request.getAttribute("lodgCity"+s2);  
		      	   		                               String lodgCityId1 = (String)request.getAttribute("lodgCityId"+s2);   
      	   		                                   %>
      	   		                                   <%=lodgCity1%>
	      	   		                                <s:hidden name ="<%="lodgCity"+s2 %>"   value="<%=lodgCity1%>" /> 
	      	   		                                <s:hidden name ="<%="lodgCityId"+s2%>"  value="<%=lodgCityId1%>" /> 
											
											<input
												type="hidden" name="lodgCity" value='<s:property  value="lodgCity"/>' id="lodgCity<%=s2%>" />
												<s:property  value="lodgCity"/> </td>
											<td width="15%" colspan="1" class="sortableTD"><s:hidden name="lodgLocation"  /> <s:property  value="lodgLocation"/> </td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  	name="lodgFromdate"/>  <s:property  value="lodgFromdate"/>&nbsp;  </td>
											<td width="8%" colspan="1" class="sortableTD"><s:hidden 	name="lodgFromtime" />  <s:property  value="lodgFromtime"/> &nbsp;</td>
											<td width="10%" colspan="1" class="sortableTD"><s:hidden  name="lodgTodate" /> <s:property  value="lodgTodate"/> &nbsp; </td>
											<td width="8%" colspan="1" class="sortableTD"><s:hidden name="lodgTotime"  /> <s:property  value="lodgTotime"/>&nbsp; </td>
											<td width="5%" colspan="1" class="sortableTD">  <input
												type="hidden" name="hidLodgChkBox" id="hidLodgChkBox<%=s2%>">
											</td>
											<script>	
		                                  lodgArr[<%=t2%>] = document.getElementById('lodgTodate').value;   
		                            </script>
											<%
											s2++;
											%>
											<%
											t2++;
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
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"   >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Advance.Details" ondblclick="callShowDiv(this);"><%=label.get("Advance.Details")%></label> </STRONG></td>

							</tr>

							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Advance.Amount" ondblclick="callShowDiv(this);"><%=label.get("Advance.Amount")%></label><font color="red">*</font>   :</td>
								<td width="25%" colspan="1"><s:hidden  	name="advanceAmt"   /><s:property value="advanceAmt"/> </td>
								<td width="20%" colspan="1"><label  class = "set"  id="Preferred.Payment.Mode" ondblclick="callShowDiv(this);"><%=label.get("Preferred.Payment.Mode")%></label> :</td>
								<td width="25%" colspan="1">
									<s:hidden name="payMode"/> <s:property value="disPayMode"/>
								</td>
							</tr>
							<tr height="20">
								<td width="20%" colspan="1"><label  class = "set"  id="Expected.Settlement.Date" ondblclick="callShowDiv(this);"><%=label.get("Expected.Settlement.Date")%></label>:</td>
								<td width="25%" colspan="1"><s:hidden name="settleDate" /> <s:property value="settleDate"/>  </td>
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
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2"  >
							<tr height="20">
								<td width="20%" colspan="4"><STRONG><label  class = "set"  id="Identity.Details" ondblclick="callShowDiv(this);"><%=label.get("Identity.Details")%></label> </STRONG></td>

							</tr>

							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="ID.Proof" ondblclick="callShowDiv(this);"><%=label.get("ID.Proof")%></label> :</td>
								<td width="25%" colspan="1">
							 
								<s:hidden name="disIdProof"/> 
								<s:property value="disIdProof"/>
								 <s:hidden name="idProof"/>  
								 </td>
								<td width="10%" colspan="1"><label  class = "set"  id="ID.Number" ondblclick="callShowDiv(this);"><%=label.get("ID.Number")%></label> :</td>
								<td width="30%" colspan="1"><s:hidden name="idProofNumber" />  <s:property value="idProofNumber"/>  </td>
							</tr>
							<tr height="20">
								<td width="15%" colspan="1"><label  class = "set"  id="Comments" ondblclick="callShowDiv(this);"><%=label.get("Comments")%></label> :</td>
								<td width="80%" colspan="3"><s:hidden name="appComments" /> <s:property value="appComments"/> 
									 </td>

							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>  
			<s:if test="commentFlag"> 
							<tr>
								<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">
									<tr>
										<td colspan="4">
										<table width="98%" border="0" align="center" cellpadding="0"
											cellspacing="0">

											<tr>
												<td colspan="5" class="formhead"><strong
													class="forminnerhead">Approver Details </strong></td>
											</tr>
										</table>
										</td>
									</tr>
									
									<tr>
										<td class="formtext">
										<table width="100%" border="0" cellpadding="1" cellspacing="1"
											 >
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
														value="apprComments" /> &nbsp;</td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
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

  function approveFun()
     {   
         document.getElementById('paraFrm').action = "TravelApprNew_saveForSingleApprover.action?status=A";   
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";  
		 
    }
    
     function rejectFun()
     {  
         document.getElementById('paraFrm').action = "TravelApprNew_saveForSingleReject.action?status=R";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
 function backFun()
 {
          status= document.getElementById('paraFrm_appStatus').value;
          
            if(status=="P")
          { 
           document.getElementById('paraFrm').action = "TravelApprNew_callStatus.action?status=P";
          }  
          
          if(status=="A")
          { 
           document.getElementById('paraFrm').action = "TravelApprNew_callStatus.action?status=A";
          }  
           if(status=="R")
          { 
           document.getElementById('paraFrm').action = "TravelApprNew_callStatus.action?status=R";
          } 
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main"; 
 } 
 
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
   
</script>
 