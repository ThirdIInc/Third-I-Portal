<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelQuickBooking" validate="true" id="paraFrm"
	theme="simple">
	<!-- Main table starts here -->
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
					Booking Details  </strong></td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="8">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="8" class="text_head"><strong
						class="forminnerhead">Select Filters </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="10%" nowrap="nowrap"><label
						name="travelIdName" id="travelIdName2"
						ondblclick="callShowDiv(this);"><%=label.get("travelIdName")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="10%"><s:textfield name="travelId"
						theme="simple" size="15" /></td>
					<td colspan="1" width="10%" nowrap="nowrap"><label
						name="initiator.name" id="initiator.name1"
						ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label>
					:</td>
					<td colspan="1" width="10%"><s:hidden name="initiatorId"
						value="%{initiatorId}" theme="simple" /> <s:textfield
						name="initiaterName" theme="simple" size="15" readonly="true" />
					</td>
					<td colspan="1" width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TravelQuickBooking_f9employee.action');">
					</td>

					<td colspan="1" width="10%" nowrap="nowrap">Status</td>
					<td colspan="1" width="10%"><s:select theme="simple"
						name="status" cssStyle="width:100"
						list="#{'A':'Approved','T':'All','P':'Pending','F':'Revoke','C':'Booking Completed'}" />
					</td>
					<td colspan="1" width="10%" nowrap="nowrap"><a href="#"
						onclick="callsearch('0');">Search</a> | <a href="#" id="showLink"
						style="display: inline;"
						onclick="checkIsExpandSearch('1');showtr();">Expand Search</a>  <a
						href="#" id="hideLink" style="display: none;" onclick="showDiv();">Hide Search</a></td>

				</tr>
				<tr id="view" style="display: none;">
					<td colspan="1" width="10%" nowrap="nowrap">Source</td>
					<td colspan="1" width="10%"><s:textfield name="source"
						theme="simple" size="15" /></td>
					<td colspan="1" width="10%" nowrap="nowrap">Destination</td>
					<td colspan="1" width="10%"><s:textfield name="destination"
						theme="simple" size="15" /></td>
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1" width="10%" nowrap="nowrap">Travel Start Date</td>
					<td colspan="1" width="10%" nowrap="nowrap"><s:textfield
						name="travelStartDateFilter" theme="simple" size="15" /></td>
					<td colspan="1" width="10%"><s:a
						href="javascript:NewCal('paraFrm_travelStartDateFilter','DDMMYYYY');">

						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
						<!-- <a href="#" onclick="showtr();callsearch('1');">Search</a> -->


					</s:a></td>

				</tr>


			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='TravelQuickBooking_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="c"){
		     
		      	document.getElementById('paraFrm').action='TravelQuickBooking_getCompletedBookingList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		   
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending Booking
					Application</a> | <a href="#" onclick="setAction('c')">Completed
					Booking List</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<td width="30%"><b>Pending Booking Application</b></td>
					<td id="ctrlShow" width="70%" align="right"><s:if
						test="pendingData">
						<b>Page:</b>
						<%
 	int totalPage = 0;
 	int pageNo = 0;
 %>
						<%
 	totalPage = (Integer) request.getAttribute("totalPage");
 	pageNo = (Integer) request.getAttribute("pageNo");
 %>
						<a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'TravelQuickBooking_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'TravelQuickBooking_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a>
						<input type="text" name="pageNoField" id="pageNoField" size="3"
							value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TravelQuickBooking_input.action');return numbersOnly();" />
					of <%=totalPage%>
						<a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'TravelQuickBooking_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TravelQuickBooking_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a>
					</s:if></td>
				</table>
				</td>
			</tr>


			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="sortable">
					<tr>
						<td width="5%" class="formth"><label name="sr.no" id="sr.no"
							ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>


						<td width="10%" class="formth"><label name="travelIdName"
							id="travelIdName" ondblclick="callShowDiv(this);"><%=label.get("travelIdName")%></label></td>


						<td width="15%" class="formth"><label name="initiator.name"
							id="initiator.name" ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label></td>

						<td width="20%" class="formth">Travel Source Destination</td>


						<td width="10%" class="formth"><label name="travelStart.date"
							id="travelStart.date" ondblclick="callShowDiv(this);"><%=label.get("travelStart.date")%></label></td>

						<td width="10%" class="formth">Status</td>

						<td width="10%" class="formth">Start Booking</td>

						<td width="20%" class="formth">Assign To</td>
					</tr>




					<%!int i = 0;%>
					<%
					int k = 1, count2 = 0;
					%>

					<s:iterator value="travelBookingList">
						<tr class="sortableTD">

							<td width="5%" class="sortableTD" align="center"><s:property
								value="srNo" /></td>

							<td width="10%" class="sortableTD" align="center"><s:property
								value="ittTravelId" /></td>

							<td width="15%" class="sortableTD"><s:property
								value="ittTravelInitiator" /></td>

							<td width="20%" class="sortableTD"><s:property
								value="ittTravelSrcDestini" /></td>

							<td width="10%" class="sortableTD" align="center" nowrap="nowrap"><s:property
								value="ittTravelStartDate" /></td>

							<td id="td_<%=k%>" width="10%" class="sortabletd" align="center"
								nowrap="nowrap">&nbsp; <s:property value="ittStatus" /></td>

							<td width="10%" class="sortableTD" align="center"><a
								href="#" class="contlink"
								onclick="callAction('<s:property value="ittTravelApplicationCode"/>','P')">
							Book </a></td>


							<td width="20%" class="sortableTD" align="center"><s:hidden
								name='<%="violateFlag_"+k %>' value='%{violateFlag}'
								id='<%="violateFlag_"+k %>' /> <s:hidden name='statusFlag'
								value='%{statusFlag}' id='<%="statusFlag_"+k %>' /> <s:if
								test="travelQuickBook.assignSchedulerFlag">

								<s:if test="%{statusFlag=='_F'}"></s:if>
								<s:else>
									<a href="#" class="contlink"
										onclick="assignToSubscheduler('<s:property value="ittTravelApplicationCode"/>','<s:property value="ittAssignedEmployee"/>')">
									Assign To </a>
								</s:else>


							</s:if> <s:else>
							</s:else> <br>
							<s:property value="ittAssignedEmployee" /></td>


						</tr>


						<script> 
				if(document.getElementById('statusFlag_<%=k%>').value == 'P')
			{
	document.getElementById('td_<%=k%>').style.background='yellow';
			}
	</script>

						<script> 
				if(document.getElementById('violateFlag_<%=k%>').value == 'Y')
			{
	document.getElementById('td_<%=k%>').style.background='#F08080';
			}
	</script>


						<script> 
				if(document.getElementById('statusFlag_<%=k%>').value == '_F')
			{
	document.getElementById('td_<%=k%>').style.background='red';
			}
	</script>

						<%
						k++;
						%>


					</s:iterator>

					<%
							if (k == 1) {
							%>
					<tr align="center">
						<td colspan="8" class="sortableTD" width="100%"><font
							color="red"><b>There is No Data to display</b></font></td>
					</tr>
					<%
							}
							%>


				</table>
				</td>
			</tr>
			<!-- Table1 ends here -->

			<tr id="noteRow" style="display: inline">
				<td>
				<table width="100%">
					<tr>
						<td width="8%" nowrap="nowrap"><b>Note : -</b></td>
						<td width="92%"><input type="text" disabled="disabled"
							style="background-color: #F08080; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates policy violated records.</td>
					</tr>

					<tr>
						<td width="8%" nowrap="nowrap"></td>
						<td width="92%"><input type="text" disabled="disabled"
							style="background-color: yellow; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates records pending for approval.</td>
					</tr>

					<tr>
						<td width="8%" nowrap="nowrap"></td>
						<td width="92%"><input type="text" disabled="disabled"
							style="background-color: red; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates revoked records.</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>

		<s:if test="%{listType == 'completed'}">

			<tr>
				<td><b>Completed Booking Application</b></td>
			</tr>


			<tr>
				<td>
				<table width="100%">
					<%
						int totalPageCompleted = 0;
						int PageNoCompleted = 0;
					%>
					<s:if test="totalPageCompletedFlag">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageCompleted = (Integer) request
 			.getAttribute("totalPageCompleted");
 	PageNoCompleted = (Integer) request.getAttribute("PageNoCompleted");
 %> <a href="#"
								onclick="callPageCompleted('1', 'F', '<%=totalPageCompleted%>', 'TravelQuickBooking_getCompletedBookingList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageCompleted('P', 'P', '<%=totalPageCompleted%>', 'TravelQuickBooking_getCompletedBookingList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=PageNoCompleted%>" maxlength="10"
								onkeypress="callPageTextCompleted(event, '<%=totalPageCompleted%>', 'TravelQuickBooking_getCompletedBookingList.action');return numbersOnly();" />
							of <%=totalPageCompleted%> <a href="#"
								onclick="callPageCompleted('N', 'N', '<%=totalPageCompleted%>', 'TravelQuickBooking_getCompletedBookingList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageCompleted('<%=totalPageCompleted%>', 'L', '<%=totalPageCompleted%>', 'TravelQuickBooking_getCompletedBookingList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>


					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="sortable">
							<tr class="td_bottom_border">
								<td width="10%" class="formth"><label name="sr.no"
									id="sr.no1" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>

								<td width="10%" class="formth"><label name="travelIdName"
									id="travelIdName1" ondblclick="callShowDiv(this);"><%=label.get("travelIdName")%></label></td>



								<td width="10%" class="formth"><label name="initiator.name"
									id="initiator.name1" ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label></td>

								<td width="10%" class="formth"><label
									name="travelStart.date" id="travelStart.date1"
									ondblclick="callShowDiv(this);"><%=label.get("travelStart.date")%></label></td>

								<td width="20%" class="formth">Travel Source Destination</td>

								<td width="20%" class="formth">Status</td>
								<td width="20%" class="formth">View Booking</td>
							</tr>




							<%!int m = 0;%>
							<%
							int n = 1, count = 0;
							%>
							<%
							int i = 1;
							%>
							<s:iterator value="travelBookingCompleteList">
								<tr>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="srNo" /></td>

									<td width="10%" class="sortableTD" align="center"><s:property
										value="TravelIdCompleted" /></td>

									<td width="10%" class="sortableTD"><s:property
										value="ittTravelInitiatorCompleted" /></td>


									<td width="10%" class="sortableTD" align="center"
										nowrap="nowrap"><s:property
										value="ittTravelStartDateCompleted" /></td>

									<td width="20%" class="sortableTD" align="center"
										nowrap="nowrap"><s:property value="ittTravelSrcDestini" />
									</td>

									<td id="tr_<%=i%>" width="20%" class="sortabletd"
										align="center" nowrap="nowrap">&nbsp; <s:property
										value="ittStatus" /></td>

									<td width="20%" class="sortableTD" align="center" nowrap="nowrap"><a
										href="#"
										onclick="callAction('<s:property value="ittTravelCodeCompleted"/>','C')">View
									Booking</a>
									|
									<a
										href="#"
										onclick="callAction('<s:property value="ittTravelCodeCompleted"/>','E')">Extend
									Booking</a>
									
									 <s:hidden name='<%="violateFlag_"+i %>'
										value='%{violateFlag}' id='<%="violateFlag_"+i %>' /></td>
								</tr>




								<script> 
				if(document.getElementById('violateFlag_<%=i%>').value == 'Y')
			{
	document.getElementById('td_<%=i%>').style.background='#F08080';
			}
	</script>

								<%
								i++;
								%>
							</s:iterator>

							<%
							if (i == 1) {
							%>
							<tr align="center">
								<td colspan="8" class="sortableTD" width="100%"><font
									color="red"><b>There is No Data to display</b></font></td>
							</tr>
							<%
							}
							%>


						</table>
						</td>
					</tr>
					<!-- Table1 ends here -->

					<tr id="noteRow" style="display: inline">
						<td>
						<table width="100%">
							<tr>
								<td width="8%" nowrap="nowrap"><b>Note : -</b></td>
								<td width="92%"><input type="text" disabled="disabled"
									style="background-color: #F08080; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
								Indicates policy violated records.</td>
							</tr>

						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>
	</table>
	<s:hidden name="currentPage" id="currentPage" />
	<s:hidden name="myPageCompleted" id="myPageCompleted" />

	<s:hidden name="myPage" id="myPage" />

	<s:hidden name="authManagementId" />

	<s:hidden name="checkSearch" value="%{checkSearch}" id="checkSearch" />


</s:form>


<script>

 onload();
function onload()
{
		if(document.getElementById('checkSearch').value==1)
		{
				showtr();
		}
		
}

function showDiv()
{
document.getElementById('view').style.display = 'none';
document.getElementById('hideLink').style.display = 'none';
 	document.getElementById('showLink').style.display = '';

}
	
	function checkIsExpandSearch(id)
	{
	//alert(id);
	document.getElementById('checkSearch').value=id;
	}
 
 function showtr()
 {
 	document.getElementById('view').style.display = '';
 	document.getElementById('hideLink').style.display = '';
 	document.getElementById('showLink').style.display = 'none';
 	
 }
 
 function hidetr()
 {
 	document.getElementById('view').style.display = 'none';
 	document.getElementById('hideLink').style.display = 'none';
 	document.getElementById('showLink').style.display = '';
 	
 }
 
 
 
 
 function callsearch(id) 
 {
 		 
  	 	//document.getElementById('checkSearch').value=id;
  	 	 
  	 	 
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'TravelQuickBooking_search.action';
		document.getElementById('paraFrm').submit();

 }
 
 function assignToSubscheduler(applicationId,assignedEmployee){		   
	
	var authManagementId = document.getElementById('paraFrm_authManagementId').value;
	win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action='TravelQuickBooking_assignToSubscheduler.action?authManagementId='+authManagementId+'&applicationId='+applicationId+'&assignedEmployee='+assignedEmployee;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main"; 
	}


function callAction(applicationId,status)
{
	try{
			
			var applicationType="bookingApp";	
			var actionName ='TravelQuickBooking_startBooking.action?applicationId='+applicationId+'&applicationStatus='+status+'&applicationType='+applicationType;
			document.getElementById('paraFrm').action =actionName;	  
			document.getElementById('paraFrm').submit();
			   document.getElementById('paraFrm').target = "main";
			
	}
	catch(e)
	{
	alert("Exception value---------"+e);
	}
	
}



	function callPageCompleted(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageCompleted').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField2').value = actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField2').value=actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField2').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCompleted').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}




 
 function callPageTextCompleted(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageCompleted').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField4').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField2').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCompleted').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}		
		
		
		
	 
</script>

