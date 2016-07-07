
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelAcknowledgement" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"	cellspacing="1" class="formbg">

		<s:hidden name="stat" />
		<s:hidden name="travelEmpId" />
		<s:hidden name="travelAppI" />
		<s:hidden name="trAppId" />
		<s:hidden name="empId" />
		<s:hidden name="status" />
		<s:hidden name="buttonFlag" />
		<s:hidden name="chkFlag" />
		<s:hidden name="empGrade" />
		<s:hidden name="grade" />
		<s:hidden name="reqFlag" />

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="98%" class="txt"><strong class="text_head">Travel Schedule </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>


					<td align="left" colspan="4"><s:if test="chkFlag">
						<!-- only for approved reject -->
						<s:submit value=" Back " theme="simple" cssClass="token" onclick="return callBack();" />
					</s:if> <s:else>
						<s:if test="buttonFlag">

						</s:if>
						<s:else>
							<!--only pending -->
							<s:submit cssClass="token" value=" Accept " onclick="return changeFun();" theme="simple" />
							<s:submit cssClass="token" value=" Reject " theme="simple" onclick="return changeFun1();" />
						</s:else>
						<!--cancel approved pending cancel reject -->
						<s:submit value=" Back " theme="simple" cssClass="token" onclick="return callBack();" />
					</s:else></td>
					<td width="22%" align="right">
					   <div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">

				<tr>
					<td  width="20%" colspan="1" class="formhead"><strong class="forminnerhead"><label
						class="set" id="employeeinformation" name="employeeinformation"
						ondblclick="callShowDiv(this);"><%=label.get("employeeinformation")%></label>
					</strong></td>

				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="emp.name1" name="emp.name"
						ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>:<font></font></td>
					<td width="30%" ><s:label name="empToken"
						theme="simple" /><s:label name="eName" theme="simple" /></td>
						
						<td width="20%" class="formtext">
						<label class="set" id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
					<td width="30%"><s:label name="dept" theme="simple" /></td>

				</tr>

				<s:hidden name="travelEmpId" id="travelEmpId" />
				<s:hidden name="travelAppId" id="travelAppId" />

				<tr>
					<td width="20%" class="formtext" height="22"><label
						class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="30%"><s:label name="branchName" theme="simple" /></td>
					
					<td width="20%" class="formtext"><label class="set"
						id="contact.no" name="contact.no" ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label> :</td>
					<td width="30%"><s:label name="contactNo" theme="simple" /></td>
					
				</tr>

				<tr>

					<td width="20%" class="formtext" height="22"><label
						class="set" id="employee.grade" name="employee.grade"
						ondblclick="callShowDiv(this);"><%=label.get("employee.grade")%></label> :</td>
					<td width="30%"><s:label name="grade" theme="simple" /></td>

					<td width="20%" class="formtext"><label class="set"
						id="app.date" name="app.date" ondblclick="callShowDiv(this);"><%=label.get("app.date")%></label> :</td>
					<td width="30%"><s:label name="aDate" theme="simple" />&nbsp;&nbsp;

				</tr>

				<tr>
					<td width="20%" class="formtext" height="22"><label
						class="set" id="travel.policy" name="travel.policy"
						ondblclick="callShowDiv(this);"><%=label.get("travel.policy")%></label>
					:</td>
					<td width="30%"><input type="button" value="Travel Policy"
						class="token" onclick="callTravelPolicy();"></td>
					<td>
			      <input type="button" value="View Requisition" class="token" onclick="return disp();" >	</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label
						class="set" id="booking.details" name="booking.details"
						ondblclick="callShowDiv(this);"><%=label.get("booking.details")%></label>
					</strong></td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<s:if test="travelFlag">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">

					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead"><label class="set" id="travel.details" name="travel.details"
							ondblclick="callShowDiv(this);"><%=label.get("travel.details")%></label>
						</strong></td>
					</tr>


					<tr>
						<td class="formtext">

						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">

							<tr>
								<td class="formth" width="5%"><label class="set"
									id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
								<td class="formth" width="15%"><label class="set"
									id="source.dest" name="source.dest"
									ondblclick="callShowDiv(this);"><%=label.get("source.dest")%></label></td>
								<td class="formth" width="15%"><label class="set"
									id=journey.date name="journey.date"
									ondblclick="callShowDiv(this);"><%=label.get("journey.date")%></label>(DD-MM-YYYY)</td>
								<td class="formth" width="10%"><label class="set" id="time"
									name="time" ondblclick="callShowDiv(this);"><%=label.get("time")%></label>(HH 24:MI)</td>
								<td class="formth" width="15%"><label class="set"
									id="travel.class" name="travel.class"
									ondblclick="callShowDiv(this);"><%=label.get("travel.class")%></label></td>
								<td class="formth" width="15%"><label class="set"
									id="bus.no" name="bus.no" ondblclick="callShowDiv(this);"><%=label.get("bus.no")%></label></td>
								<td class="formth" width="15%"><label class="set"
									id="ticket.no" name="ticket.no" ondblclick="callShowDiv(this);"><%=label.get("ticket.no")%></label></td>
								<td class="formth" width="20%"><label class="set"
									id="ticket.dtls" name="ticket.dtls"
									ondblclick="callShowDiv(this);"><%=label.get("ticket.dtls")%></label></td>
							</tr>

							<%int n = 0;%>
							<s:iterator value="travelDtlList">
								<tr>
									<td class="sortableTD"><%=++n%></td>
									<td class="sortableTD"><s:property value="itSource" />&nbsp;</td>
									<td class="sortableTD"><s:property value="itJourneyDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="itTime" />&nbsp;</td>
									<td class="sortableTD"><s:property value="itMode" />&nbsp;</td>
									<td class="sortableTD"><s:property value="itBTFNumber" />&nbsp;</td>
									<td class="sortableTD"><s:property value="itTicketNumber" />&nbsp;</td>
									<s:hidden name="flag" value="%{flag}" />
									<s:if test="flag">
										<td class="sortableTD" width="20%" align="center">
									</s:if>
									<a href="#"
										onclick="return ticketRecord('<s:property value="uploadTravelTicket" />');">Download</a>
									<!-- <input type="button" name="view_Details" class="token" value="Download" onclick = "viewDetails('<s:property value="empId"/>','<s:property value="appId"/>')"/>-->
						</td>

						<s:hidden name="itSource" />
						<s:hidden name="itJournyDate" />
						<s:hidden name="itTime" />
						<s:hidden name="itMode" />
						<s:hidden name="itBTFNumber" />
						<s:hidden name="itTicketNumber" />
						<s:hidden name="uploadTravelTicket" />
						<s:hidden name="itJourneyId" />
						<s:hidden name="itJourneyClassId" />
						<s:hidden name="itJourneyDate" />
						<s:hidden name="itJourneyTime" />
						</td>

					</tr>
					</s:iterator>

				</table></td>

		</tr>
	</table>
	</s:if>
	</td>
	</tr>

	<tr>
		    <td colspan="3"><s:if test="commentFlag"></s:if> <s:if test="convenceFlag">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">

				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label	class="set" id="local.details" name="local.details"
						ondblclick="callShowDiv(this);"><%=label.get("local.details")%></label></strong></td>
				</tr>

	   </tr>
		     	<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="sortable">
						<tr>
							<td width="5%" class="formth"><label class="set" id="sr.no1"
								name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="source" name="source" ondblclick="callShowDiv(this);"><%=label.get("source")%></label></td>
							<td width="10%" class="formth"><label class="set" id="dates"
								name="dates" ondblclick="callShowDiv(this);"><%=label.get("dates")%></label>(DD-MM-YYYY)</td>
							<td width="10%" class="formth"><label class="set"
								id="travel.mode" name="travel.mode"
								ondblclick="callShowDiv(this);"><%=label.get("travel.mode")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="trvMode.no" name="trvMode.no"
								ondblclick="callShowDiv(this);"><%=label.get("trvMode.no")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="contact.person" name="contact.person"
								ondblclick="callShowDiv(this);"><%=label.get("contact.person")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="contact.no1" name="contact.no"
								ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="pickup.person" name="pickup.person"
								ondblclick="callShowDiv(this);"><%=label.get("pickup.person")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="pickup.place" name="pickup.place"
								ondblclick="callShowDiv(this);"><%=label.get("pickup.place")%></label></td>	
							<td width="10%" class="formth"><label class="set"
								id="pickup.time" name="pickup.time"
								ondblclick="callShowDiv(this);"><%=label.get("pickup.time")%></label></td>


							<%int j = 0;%>

							<s:iterator value="conveyList">

								<tr>
									<td class="sortableTD"><%=++j%></td>
									<td class="sortableTD"><s:property value="sourcedt" />&nbsp;</td>
									<td class="sortableTD"><s:property value="date" />&nbsp;</td>
									<td class="sortableTD"><s:property value="travelMode" />&nbsp;</td>
									<td class="sortableTD"><s:property value="modeNumber" />&nbsp;</td>
									<td class="sortableTD"><s:property value="cntPerson" />&nbsp;</td>
									<td class="sortableTD"><s:property value="cntNumber" />&nbsp;</td>
									<td class="sortableTD"><s:property value="pickPerson" />&nbsp;</td>
									<td class="sortableTD"><s:property value="pickPlace" />&nbsp;</td>
									<td class="sortableTD"><s:property value="pickTime" />&nbsp;</td>
									<s:hidden name="sourcedt" />
									<s:hidden name="date" />
									<s:hidden name="travelMode" />
									<s:hidden name="modeNumber" />
									<s:hidden name="cntPerson" />
									<s:hidden name="cntNumber" />
									<s:hidden name="pickPerson" />
									<s:hidden name="pickPlace" />
									<s:hidden name="pickTime" />
									
								</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
		</s:if>
		</td>
	</tr>
	<tr>
		<td colspan="3"><s:if test="lodgeFlag">
			 <table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">

				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label	class="set" id="lodge.dtls" name="lodge.dtls"
						ondblclick="callShowDiv(this);"><%=label.get("lodge.dtls")%></label></strong></td>
				</tr>

				<tr>
					<td class="formtext" width="100%">
					    <table width="100%" border="0" cellpadding="1" cellspacing="1"	class="sortable">
						<tr>
							<td width="5%" class="formth"><label class="set" id="sr.no2"
								name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="10%" class="formth"><label class="set" id="city"
								name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="frm.date" name="frm.date" ondblclick="callShowDiv(this);"><%=label.get("frm.date")%></label>(DD-MM-YYYY)</td>
							<td width="10%" class="formth"><label class="set"
								id="checkin" name="checkin" ondblclick="callShowDiv(this);"><%=label.get("checkin")%></label>(HH 24:MI)</td>
							<td width="10%" class="formth"><label class="set"
								id="todate" name="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>(DD-MM-YYYY)</td>
							<td width="10%" class="formth"><label class="set"
								id="checkout" name="checkout" ondblclick="callShowDiv(this);"><%=label.get("checkout")%></label>(HH 24:MI)</td>
							<td width="10%" class="formth"><label class="set"
								id="hotel.type" name="hotel.type"
								ondblclick="callShowDiv(this);"><%=label.get("hotel.type")%></label></td>
							<td width="10%" class="formth"><label class="set"
								id="room.type" name="room.type" ondblclick="callShowDiv(this);"><%=label.get("room.type")%></label></td>
							<td width="15%" class="formth"><label class="set"
								id="address" name="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label></td>
							<td class="formth" width="15%"><label class="set"
								id="ticket.dtls1" name="ticket.dtls"
								ondblclick="callShowDiv(this);"><%=label.get("ticket.lodge")%></label></td>
						</tr>
						
					<%int k = 0;%>
						<s:iterator value="lodgeList">

							<tr>
								<td class="sortableTD"><%=++k%></td>
								<td class="sortableTD"><s:property value="city" />&nbsp;</td>
								<td class="sortableTD"><s:property value="fromDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="checkIn" />&nbsp;</td>
								<td class="sortableTD"><s:property value="toDate" />&nbsp;</td>
								<td class="sortableTD"><s:property value="checkOut" />&nbsp;</td>
								<td class="sortableTD"><s:property value="hotelType" />&nbsp;</td>
								<td class="sortableTD"><s:property value="roomType" />&nbsp;</td>
								<td class="sortableTD"><s:property value="address" />&nbsp;</td>
								<s:hidden name="city" />
								<s:hidden name="fromDate" />
								<s:hidden name="checkIn" />
								<s:hidden name="toDate" />
								<s:hidden name="checkOut" />
								<s:hidden name="hotelType" />
								<s:hidden name="hotelType" />
								<s:hidden name="roomType" />
								<s:hidden name="address" />
								<s:hidden name="lodgeticketDeatails" />
								
								<td class="sortableTD" width="10%" align="center">
								<a href="#"	onclick="return lodgeRecord('<s:property value="lodgeticketDeatails"/>');">Download</a>
								<!--  <input type="button" name="view_Details" class="token" value="Download" onclick = "lodgeRecord"/>-->
								</td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
		</s:if></td>
	</tr>
	<tr>
		<td colspan="3">

		<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
			<tr>
				<td>
			    	<table width="100%" border="0" align="center" cellpadding="0"	cellspacing="0">
					<tr>
						<td  width="15%" colspan="1" class="formhead"><strong class="forminnerhead">
						<label class="set" id="trvadvance"	name="trvadvance" ondblclick="callShowDiv(this);"><%=label.get("trvadvance")%></label></strong></td>
					</tr>
					<tr>
						<td width="15%" colspan="1" class="formtext" height="22">
						<label class="set" id="advance.amount" name="advance.amount"
							ondblclick="callShowDiv(this);"><%=label.get("advance.amount")%></label> :<font
							color="red"></font></td>
						<td width="70%" ><s:label name="addAmount" theme="simple" /></td>
					</tr>

					<tr>
						<td width="15%" class="formtext" height="22">
						<label	class="set" id="pay.mode" name="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label> :</td>
						<td width="70%"><s:label name="payMode" theme="simple" /></td>
					</tr>
					<tr>
						  <td width="15%" class="formtext">
						  <label class="set" id="schdulercommt" name="schdulercommt"
							ondblclick="callShowDiv(this);"><%=label.get("schdulercommt")%></label> :</td>
						  <td width="30%"><s:textarea name="schComment" theme="simple"
							rows="3" cols="100" readonly="true" /></td>
					</tr>
           
					<tr>
                      <s:if test="chkFlag">
                      
						     <td width="15%" class="formtext" height="22">
					     	<label	class="set" id="applicantcommts" name="applicantcommts"
							ondblclick="callShowDiv(this);"><%=label.get("applicantcommts")%></label> :</td>
						       <td width="30%"><s:textarea name="applComment"  readonly="true" theme="simple" rows="3" cols="100" /></td>
					   
					  </s:if>
						 <s:else>
						  <s:if test="buttonFlag">
                          <td width="15%" class="formtext" height="22">
					     	<label	class="set" id="applicantcommts" name="applicantcommts"
							ondblclick="callShowDiv(this);"><%=label.get("applicantcommts")%></label> :</td>
						       <td width="30%"><s:textarea name="applComment" readonly="true"   theme="simple" rows="3" cols="100" /></td>
					 
                        </s:if> <s:else>
                        
                          <td width="15%" class="formtext" height="22">
							 <label	class="set" id="applicantcommts" name="applicantcommts"
							      ondblclick="callShowDiv(this);"><%=label.get("applicantcommts")%></label> :</td>
						     <td width="30%"><s:textarea name="applComment" theme="simple" rows="3" cols="100"  /></td>
					  
                        </s:else>
                        
                        </s:else>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
	<s:hidden name="apprflag" />
	<s:hidden name="backFlag" />

	<tr>

		<td align="left" colspan="4"><s:if test="chkFlag">
			<!-- only for approved reject -->
			<s:submit value=" Back " theme="simple" cssClass="token" onclick="return callBack();" />
		</s:if> <s:else>
			<s:if test="buttonFlag">

			</s:if>
			<s:else>
				<!--only pending -->
				<s:submit cssClass="token" value=" Accept " onclick="return changeFun();" theme="simple" />
				<s:submit cssClass="token" value=" Reject " theme="simple" onclick="return changeFun1();" />
			</s:else>
			<!--cancel approved pending cancel reject -->
			<s:submit value=" Back " theme="simple" cssClass="token" onclick="return callBack();" />
		</s:else></td>
	</tr>
	
</table>
</s:form>

<script type="text/javascript">


callOnload();
function callOnload()
					{
					
					document.getElementById('paraFrm_applComment').focus();
					
					}

function ticketRecord(fileName)
					{
			
					if(fileName==" ")
					{
		             alert('Travel ticket details are not available');
		             return false;
		             }
	                  var path="images/<%=session.getAttribute("session_pool")%>/tickets/"+fileName; 	
	                  window.open('../pages/'+path);	
	                  document.getElementById('paraFrm').target ="main";	
                     }
		
function lodgeRecord(fileName)
					{	
					
					if(fileName==" ")
					{
		             alert('Lodge ticket details are not available');
		            return false;
		             }
	                  var path="images/<%=session.getAttribute("session_pool")%>/LodgeDetails/"+fileName; 	
	                 window.open('../pages/'+path);	
	                  document.getElementById('paraFrm').target ="main";	
                     }
	
function callBack()
                 {

		            var Lstatus=document.getElementById('paraFrm_status').value;
		            var Bstatus=document.getElementById('paraFrm_buttonFlag').value;
		            var Fstatus=document.getElementById('paraFrm_chkFlag').value;
		         	document.getElementById('paraFrm').action = "TravelAcknowledgement_DupcallStatus.action?dupstatus="+Lstatus+"&Bstatus="+Bstatus+"&Fstatus="+Fstatus; 
					document.getElementById('paraFrm').submit();
		
                 }
function changeFun()
                  {
		        	document.getElementById('paraFrm').action="TravelAcknowledgement_accept.action";
		 		    document.getElementById('paraFrm').submit();
			     }
		
function changeFun1()
                 {
			        document.getElementById('paraFrm').action="TravelAcknowledgement_reject.action";
		 		    document.getElementById('paraFrm').submit();
			
			     }
 
function callTravelPolicy()
                  {   
    				document.getElementById('paraFrm_empGrade').value=document.getElementById('paraFrm_grade').value;
				 	document.getElementById('paraFrm_trAppId').value=document.getElementById('travelAppId').value;
				 	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
					document.getElementById('paraFrm').target = "wind";
					document.getElementById('paraFrm').action = "TravelApplication_TravelPolicy.action";
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = "main";  
                }
    
 function disp()
			 {   	 
				  document.getElementById('paraFrm_reqFlag').value="false"; 
		          document.getElementById('paraFrm_trAppId').value=document.getElementById('travelAppId').value;
		          document.getElementById('paraFrm_empId').value= document.getElementById('travelEmpId').value;
			      document.getElementById('paraFrm').action = "TravelApplication_callAppDtl.action";
				  document.getElementById('paraFrm').submit();
				  document.getElementById('paraFrm').target ="main"; 
				 
			 }

  
</script>
