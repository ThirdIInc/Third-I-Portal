<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelApplicationApp" id="paraFrm" theme="simple" validate="true">
	<s:hidden name="authorizedToken"/>
	<s:hidden name="hiddenCode" />
	<s:hidden name="readOnlyDetails" />
	<s:hidden name="level" />
	<s:hidden name="travelID" />
	<s:hidden name="forApproval" id="forApproval" />
	<input type="hidden" id="labelCount" />
	<s:hidden name="actionMessage"/>
	<s:hidden  name="flag"/>
	
	<s:hidden name="listType" />
	<s:hidden name="pageForPendingApp" id="pageForPendingApp" />
	<s:hidden name="pageForApprovedApp" id="pageForApprovedApp" />
	<s:hidden name="pageForRejectedApp" id="pageForRejectedApp" />
	
	<%
		String valueChk = "";
		try {
			if (request.getAttribute("radioValueYesNo") != null) {
				valueChk = (String) request
				.getAttribute("radioValueYesNo");
			} else {
				valueChk = "";
			}
			System.out.println("JSP value1:---" + valueChk);
		} finally {
			if (valueChk == null) {
				valueChk = "";
			}
		}
	%>

	<table width="100%" class="formbg" align="right">
		
			<tr>
				<td colspan="5">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong
							class="formhead"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td width="92%" class="txt"><strong class="text_head">Authorized Travel Request for Approval 
						 </strong></td>
						<td width="4%" valign="middle" align="right" class="txt"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="80%" colspan="2"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
				</table>
				</td>
			</tr>
		
		
			<!--<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<s:if test="forApproval">
				<tr><td width="20%" colspan="1" id="ctrlShow"><b>Tracking Number: </b></td>
				<td width="80%" colspan="3"><s:property value="authorizedToken"/><s:hidden name="authorizedToken"/></td>
				</tr>
				
				</s:if>
				<s:else>
				<tr><td width="20%" colspan="1" id="ctrlShow"><b>Tracking Number: </b></td>
				<td width="80%" colspan="3"><s:property value="authorizedToken"/></td>
				</tr>
				</s:else>
				
		</table>
		</td>
		</tr>
		
			--><tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr><td><b>Instruction for Forwarding,Approving,Submitting:</b></td>
				</tr>
				
				
				<tr><td>1: Traveler completes form.Traveler line manager approve it  or If line manager is not Final approver then line manager forward it to next person </td>
				</tr>
				<tr><td> 2: If Next person is not 'Authorised' Final approver,he again forword to next one.</td>
				</tr>
				<tr><td> 3: 'AUTHORISED SIGN-OFF' button should only be 'clicked' by final approver. He must select is final approver Flag & Put mail-id
      of 'World Travel ,Inc' </td>
				</tr>
				
		</table>
		</td>
		</tr>
		
		
			<tr>
				<td width="100%" colspan="5">
				<table width="100%" class="formbg">
					<s:if test="forApproval">
					<tr>
						<td width="20%" colspan="2"><b>Approver Comments:</b><font id='ctrlHide'
							color="red">*</font></td>
						<td id="ctrlShow" width="80%"><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="approverComments" onkeypress="return imposeMaxLength(event, this, 500);"/></td>
					</tr>
					</s:if>
					<!--<s:if test="worldTravelFlag">
					<tr>
						<td width="20%" colspan="2"><b>Is Final Approver:</b></td>
						<td id='ctrlShow' width="80%"><s:checkbox name="finalApprover" onclick=" callEmailText();"/></td>
					</tr>
						
					<tr id="emailId" style="display:none">
						<td width="20%" colspan="2"><b>Email to World Travel:<font 
							color="red">*</font></b></td>
						<td id='ctrlShow' width="80%"  ><s:textfield name="emailWorldTravel" /></td>
					</tr>
					</s:if>
					-->
					<s:if test="forwardManager">		
					<tr id="forwardManager">
						<td width="20%" colspan="2"><b>Forward to Manager :</b></td>
						<td width="80%" colspan="3" id="ctrlShow"><s:textfield name="forwardApproverToken"
							size="20"  theme="simple" readonly="true" />
						<s:textfield name="forwardApproverName" size="70"
							theme="simple" readonly="true" />
						<s:hidden name="forwardApproverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" 
							onclick="javascript:callsF9(500,325,'TravelApplication_f9ForwardApprover.action');">
						</td>
					</tr>
					</s:if>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Approved Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center" nowrap="nowrap"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
						}
					%>
				</table>
				</td>
			</tr>
		
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="travel.info" id="travel.info"
						ondblclick="callShowDiv(this);"><%=label.get("travel.info")%></label></b>
					</td>
				</tr>

				<tr>

				
						<td width="20%"><label id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:<font color="red" id="ctrlHide">*</font></td>
						<td width="80%" colspan="3"><s:textfield name="employeeToken"
							size="20" theme="simple" readonly="true" /><s:textfield
							name="employeeName" size="70" theme="simple" readonly="true" />
						<s:hidden name="employeeCode" /><img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'TravelApplication_f9Employee.action');">
						</td>
						


				</tr>

				<tr>
					<td width="25%"><label id="desg" name="desg"
						ondblclick="callShowDiv(this);"><%=label.get("desg")%></label>
					:</td>
					<td width="25%"><s:textfield name="designation" readonly="true"
						size="20" cssStyle="background-color: #F2F2F2;" /></td>
						
					<!--<td width="25%"><label id="date.ATR" name="date.ATR"
						ondblclick="callShowDiv(this);"><%=label.get("date.ATR")%></label>
					:<font color="red" id="ctrlHide">*</font></td>
					<td width="25%"><s:textfield
								name="dateOfAtr" size="20"
								value="%{dateOfAtr}" theme="simple" 
								 /><s:a
								href="javascript:NewCal('paraFrm_dateOfAtr','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>

					--><td width="25%" align="left"><label id="ph.no" name="dept.code"
						ondblclick="callShowDiv(this);"><%=label.get("dept.code")%></label> :</td>

					<td width="25%"><s:textfield name="deptName" readonly="true"
						size="20" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				
				
			
				
					<tr>
					<!--<td width="25%"><label id="desg" name="desg"
						ondblclick="callShowDiv(this);"><%=label.get("desg")%></label>
					:</td>
					<td width="25%"><s:textfield name="designation" readonly="true"
						size="20" cssStyle="background-color: #F2F2F2;" /></td>

					-->
					<td width="25%"><label id="date.ATR" name="date.ATR"
						ondblclick="callShowDiv(this);"><%=label.get("date.ATR")%></label>
					:<font color="red" id="ctrlHide">*</font></td>
					<td width="25%"><s:textfield
								name="dateOfAtr" size="20"
								value="%{dateOfAtr}" theme="simple" 
								 /><s:a
								href="javascript:NewCal('paraFrm_dateOfAtr','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>
					
					<td width="25%" align="left"><label id="emp.travel" name="emp.travel"
						ondblclick="callShowDiv(this);"><%=label.get("emp.travel")%></label> :<font color="red" id="ctrlHide">*</font></td>

					<td width="25%"><s:textfield name="empTravel" 
						size="20" onkeyup="  return isNumberKey(event);"/></td>
				</tr>
				
				<tr>
					<td width="25%"><label id="from" name="from"
						ondblclick="callShowDiv(this);"><%=label.get("from")%></label>:<font color="red" id="ctrlHide">*</font></td>
						<td width="25%"><s:textfield
								name="fromDate" size="20"
								value="%{fromDate}" theme="simple" 
								 /><s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>


					<td width="25%" align="left"><label id="to" name="to"
						ondblclick="callShowDiv(this);"><%=label.get("to")%></label> :<font color="red" id="ctrlHide">*</font></td>

				<td width="25%"><s:textfield
								name="toDate" size="20"
								value="%{toDate}" theme="simple" 
								/><s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									id="ctrlHide">
							</s:a></td>
				</tr>
				
				
				<tr>
					<!-- Added ganesh start -->
					<td width="25%" align="left"><label id="destination" name="destination"
						ondblclick="callShowDiv(this);"><%=label.get("destination")%></label> :<font color="red" id="ctrlHide"></font></td>

					<td width="25%"><s:textfield name="destination" 
						size="20" maxlength="100"/></td>
				<!-- Added ganesh endt -->
				<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%">
					<s:hidden name="status"></s:hidden>
					<s:select disabled="true" cssStyle="width:125"
						name="applnStatus"
						list="#{'D':'Draft','P':'PENDING','B':'Sent Back','A':'APPROVED','R':'REJECTED',
						'N':'CANCELLED','F':'FORWARDED','C':'APPLIED FOR CANCELLATION','X':'Cancellation Approved','Z':'Cancellation Rejected'}"  />
					</td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
					</table>
			</td>
		</tr>
		
				
				<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="billing" id="just"
						ondblclick="callShowDiv(this);"><%=label.get("just")%></label>:<font color="red" id="ctrlHide">*</font></b>
					</td>
				</tr>
				
				<tr>
					<td width="25%"><label id="cust" name="cust"
						ondblclick="callShowDiv(this);"><%=label.get("cust")%></label>
					
					</td>
					<td width="25%" nowrap="nowrap"><s:checkbox name="customer" /></td>
						<td width="25%" align="left"><label id="mgm.trainee" name="mgm.trainee"
						ondblclick="callShowDiv(this);"><%=label.get("mgm.trainee")%></label></td>
					<td width="25%"><s:checkbox name="managmentTraining" /> </td>
				</tr>
				
				<tr>
				<td width="25%" align="left"><label id="other" name="other"
						ondblclick="callShowDiv(this);"><%=label.get("other")%></label></td>
					<td width="25%"><s:checkbox name="Other" /> </td>
					
					<td width="25%" align="left"><label id="training" name="training"
						ondblclick="callShowDiv(this);"><%=label.get("training")%></label></td>
					<td width="25%"><s:checkbox name="training" /> </td>
				</tr>
				
				<tr>
					<td width="25%"><label id="acq" name="acq"
						ondblclick="callShowDiv(this);"><%=label.get("acq")%></label></td>
					<td width="25%">
						<s:checkbox name="acquisition" />
					</td>
					<td width="25%" align="left"></td>
					<td width="25%"></td>
				</tr>
				
				<tr>
					<td width="25%"><label id="comments" name="comments"
							ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
						<td width="25%"><s:textarea theme="simple" cols="22" rows="3"
								name="comments" id="comments" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="billing" id="billing"
						ondblclick="callShowDiv(this);"><%=label.get("billing")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="25%"><label id="air" name="air"
						ondblclick="callShowDiv(this);"><%=label.get("air")%></label></td>
					<td width="25%">
						<s:checkbox name="air" />
					</td>

					<td width="25%"><label id="car" name="car"
						ondblclick="callShowDiv(this);"><%=label.get("car")%></label>
					</td>
					<td width="25%"><s:checkbox name="car" /></td>
				</tr>
				
				<tr>
					<td width="25%"><label id="hotel" name="hotel"
						ondblclick="callShowDiv(this);"><%=label.get("hotel")%></label></td>
					<td width="25%">
					<s:checkbox name="hotel" />
					</td>

					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				
				
				<tr>
				<td width="25%"></td>
				<td width="25%"></td>
								 <td width="25%"></td>
								 <td width="25%"></td>
				</tr>
				
				<tr>
					<td width="25%"><label id="low.cost" name="low.cost"
						ondblclick="callShowDiv(this);"><%=label.get("low.cost")%></label>
					:<font color="red" id="ctrlHide">*</font></td>
					<td width="25%">
					<input type="radio"
								name="lowCost" id="lowCost"
								onclick="setRadioValue(this);" value="Y"
								<%=valueChk.equals("Y")?"checked":"" %>
								 />Yes &nbsp;&nbsp;
								 <input type="radio"
								name="lowCost" id="lowCost"
								onclick="setRadioValue(this);" value="N" <%=valueChk.equals("N")?"checked":"" %>
								 />No
					</td>

					<td width="25%">
					</td>
					
					<td width="25%">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="policy"
						id="policy" ondblclick="callShowDiv(this);"><%=label.get("policy")%></label>
					 </b></td>
				</tr>

				<tr>
					<td width="25%">Connections:</td>
					<td width="25%"><s:checkbox name="connections" /> </td>
					<td colspan="1" width="25%">ATR: </td>
					<td colspan="1" width="25%"><s:checkbox name="atr" /></td>
				</tr>
				
				<tr>
					<td width="25%">Times: </td>
					<td width="25%"><s:checkbox name="times" /> </td>
					<td width="25%" colspan="1">Not booked through DecisionOne travel agency: </td>
					<td width="25%" colspan="1"><s:checkbox name="notBooked"/></td>
				</tr>

				<tr>
					<td width="25%">Carrier preference: </td>
					<td width="25%"><s:checkbox name="carrierPreference" /></td>
					<td colspan="1" width="25%">Other:</td>
					<td colspan="1" width="25%"><s:checkbox name="otherChk" /> </td>
				</tr>
				
				<tr>
				<td width="25%">Non-refundable ticket: </td>
					<td width="25%" colspan="1"><s:checkbox name="nonRefundable" /> </td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5"><b><label class="set"
						name="travel.exp"
						id="travel.exp" ondblclick="callShowDiv(this);"><%=label.get("travel.exp")%></label>
					 </b></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="exp.air"
						id="exp.air" ondblclick="callShowDiv(this);"><%=label.get("exp.air")%></label></td>
					
					<!--<td width="20%" colspan="1"><s:textfield name="airExp" 
						size="20" onkeypress="return isNumberKey(event);"/></td>
					
						-->
						
						<td width="20%" colspan="1"><s:hidden name="airExp" /></td>
					<td width="20%" colspan="1"></td>
					<td width="20%" colspan="1">$<s:textfield name="totalAirExp" 
						size="20" onkeyup="total(); return isNumberKey(event); " /></td>
						<td width="20%" colspan="1">Round Trip</td>
				</tr>

			
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="exp.car"
						id="exp.car" ondblclick="callShowDiv(this);"><%=label.get("exp.car")%></label></td>
					
					<td width="20%" colspan="1">$<s:textfield name="carExp" 
						size="20" onkeypress=" return isNumberKey(event);"/></td>
					
					<td width="20%" colspan="1">Per Day</td>
					<td width="20%" colspan="1" nowrap="nowrap">$<s:textfield name="totalCarExp" 
						size="20" onkeyup=" total(); return isNumberKey(event);"/></td>
						<td width="20%" colspan="1" nowrap="nowrap">Total For Duration</td>
				</tr>
		
				
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="exp.hotel"
						id="exp.hotel" ondblclick="callShowDiv(this);"><%=label.get("exp.hotel")%></label></td>
					
					<td width="20%" colspan="1">$<s:textfield name="hotelExp" onkeypress="return isNumberKey(event);"
						size="20"/></td>
					
					<td width="20%" colspan="1">Per Night</td>
					<td width="20%" colspan="1">$<s:textfield name="totalHotelExp" onkeyup=" total();  return isNumberKey(event);"
						size="20"/></td>
						<td width="20%" colspan="1">Total For Duration</td>
				</tr>
				
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="exp.meal"
						id="exp.meal" ondblclick="callShowDiv(this);"><%=label.get("exp.meal")%></label></td>
					
					<td width="20%" colspan="1">$<s:textfield name="mealExp" onkeypress="return isNumberKey(event); "
						size="20"/></td>
					
					<td width="20%" colspan="1">Per Day</td>
					<td width="20%" colspan="1" nowrap="nowrap">$<s:textfield name="totalMealExp" onkeyup=" total(); return isNumberKey(event);" 
						size="20"/></td>
						<td width="20%" colspan="1">Total For Duration</td>
				</tr>
				

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="exp.other"
						id="exp.other" ondblclick="callShowDiv(this);"><%=label.get("exp.other")%></label></td>
					
					<td width="20%" colspan="1">$<s:textfield name="otherExp"  onkeypress="return isNumberKey(event);"
						size="20"/></td>
					
					<td width="20%" colspan="1"></td>
					<td width="20%" colspan="1" nowrap="nowrap">$<s:textfield name="totalOtherExp"  onkeyup=" total(); return isNumberKey(event);" size="20"/> </td>
					<td width="20%" colspan="1">Total For Duration</td>
				</tr>
				<tr>
					<td>
						<br/>
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1"></td>
					<td width="20%" colspan="1">&nbsp;&nbsp;</td>
					<td width="20%" colspan="1">Total Expenses:</td>
					<td width="20%" colspan="1">$<s:textfield name="totalValue"  onkeypress="return isNumberKey(event);" size="20" readonly="true"/> </td>
					<td width="20%" colspan="1"></td>
				</tr>
			</table>
			</td>
		</tr>
	<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="3"><b><label id="general.comments" name="general.comments"
						ondblclick="callShowDiv(this);"><%=label.get("general.comments")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="20%"><label id="gen.comments" name="gen.comments"
						ondblclick="callShowDiv(this);"><%=label.get("gen.comments")%></label> :</td>
					<td colspan="2" width="80%"><s:textarea name="generalComments" cols="100" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_generalComments','generalComments','','400','400');">
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<!--<s:if test="forApproval">
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="80%" colspan="2"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		
			--><tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td><b>Form Approval</b></td>
					</tr>
					
					<tr>
						<td><label class="set" name="first_app"
							id="first_app" ondblclick="callShowDiv(this);"><%=label.get("first_app")%></label>
						:</td>

						<td width="80%" colspan="3"><s:hidden
							name="firstApproverCode" /> <s:property value="firstApproverToken" />&nbsp;&nbsp;&nbsp;
						<s:property value="firstApproverName" /></td>
					</tr>
					<s:if test="secondAppFlag">
					<tr>
						<td><label class="set" name="second_app"
							id="second_app" ondblclick="callShowDiv(this);"><%=label.get("second_app")%></label>
						:</td>

						<td width="80%" colspan="3"><s:hidden
							name="secondApproverCode" /> <s:property value="secondApproverToken" />&nbsp;&nbsp;&nbsp;
							<s:property value="secondApproverName" /> 
						</td>
					</tr>
					</s:if>
					<tr>
						<td width="20%">Change My Manager :</td>
						<td width="80%" colspan="3"><s:textfield name="approverToken"
							size="20" value="%{approverToken}" theme="simple" readonly="true" />
						<s:textfield name="approverName" size="70"
							value="%{approverName}" theme="simple" readonly="true" />
						<s:hidden name="approverCode" value="%{approverCode}" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'TravelApplication_f9Approver.action');">
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
					<tr>
						<td width="20%"><b><label class="set" name="by"
							id="by" ondblclick="callShowDiv(this);"><%=label.get("by")%></label></b>
						:</td>
						<td width="20%">
							<s:hidden name="completedByCode" />
							<s:hidden name="completedBy" />
							<s:property value="completedBy"/>   
						</td>
						<td width="20%"><b><label class="set" name="on"
							id="on" ondblclick="callShowDiv(this);"><%=label.get("on")%></label></b>
						:</td>
						<td width="20%">
							<s:hidden name="completedDate" />
							<s:property value="completedDate"/>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="80%" colspan="2"><jsp:include
									page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
	</table>
<s:hidden name="radioValue" id="radioValue" value="<%=valueChk %>"></s:hidden>
</s:form>

<script>
total();
	
	function setRadioValue(id){
		var opt=document.getElementById('radioValue').value =id.value;	
	}
	
	 
	function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}
	 
	function total()
	{
	
	var total;
	
	try{
	
	var totalAirExp=document.getElementById('paraFrm_totalAirExp').value;
	var totalCarExp=document.getElementById('paraFrm_totalCarExp').value;
	var totalHotelExp=document.getElementById('paraFrm_totalHotelExp').value;
	var totalMealExp=document.getElementById('paraFrm_totalMealExp').value;
	var totalOtherExp= document.getElementById('paraFrm_totalOtherExp').value;
	
	if(totalAirExp=="")
	{
		totalAirExp=0;
	}
	if(totalCarExp=="")
	{
		totalCarExp=0;
	}
	if(totalHotelExp=="")
	{
		totalHotelExp=0;
	}
	if(totalMealExp=="")
	{
		totalMealExp=0;
	}
	if(totalOtherExp=="")
	{
		totalOtherExp=0;
	}
	
 
	 
	 total=eval(totalAirExp)+eval(totalCarExp)+eval(totalHotelExp)+eval(totalMealExp)+eval(totalOtherExp);
	  
		  
			document.getElementById('paraFrm_totalValue').value=roundNumber(total,4);
			
						if(document.getElementById('paraFrm_totalValue').value=="NaN"){
						   document.getElementById('paraFrm_totalValue').value="";
						}
	}catch(e){
		alert(e);
	}	
	return total;
	}


	function makeFieldsDisabled() {
		var readOnlyDetails = document.getElementById('paraFrm_readOnlyDetails').value;
		var applnStatus = document.getElementById('paraFrm_applnStatus').value;
		if(applnStatus != 'D' && applnStatus != '' && applnStatus != 'B' ) {
			disableFormFields();
		}

		if(readOnlyDetails == 'true' && (applnStatus == 'F' || applnStatus == 'R' || applnStatus == 'A' || applnStatus == 'P' || applnStatus == 'B')) {
			disableApproverComments();
			disableEmailWorldTravel();
		}
	}
	
	
	
	function disableApproverComments() {
		var approverComments = document.getElementById('approverComments');
		var labelCount = document.getElementById('labelCount').value;
		var lbl = document.createElement('label');
		lbl.id = 'lbl' + labelCount;
		labelCount++;
		document.getElementById('labelCount').value = labelCount;
		lbl.innerHTML = approverComments.value + ' ';
		var cell = approverComments.parentNode;
		cell.appendChild(lbl);
		approverComments.style.display = 'none';
	}
	
	function disableEmailWorldTravel() {
		var emailWorldTravel = document.getElementById('emailWorldTravel');
		var labelCount = document.getElementById('labelCount').value;
		var lbl = document.createElement('label');
		lbl.id = 'lbl' + labelCount;
		labelCount++;
		document.getElementById('labelCount').value = labelCount;
		lbl.innerHTML = emailWorldTravel.value + ' ';
		var cell = emailWorldTravel.parentNode;
		cell.appendChild(lbl);
		emailWorldTravel.style.display = 'none';
	}
	
	function disableFormFields() {
		var labelCount = 0;
		
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id != 'approverComments' &&  document.all[i].id != 'emailWorldTravel') {
				if(document.all[i].id == 'ctrlHide') {
					document.all[i].style.display = 'none';
				} else if((document.all[i].type == 'checkbox' || document.all[i].type == 'radio') 
				&& document.all[i].id != 'ctrlShow' && document.all[i].parentNode.id != 'ctrlShow') {
					document.all[i].disabled = true;
				} else if((document.all[i].type == 'text' || document.all[i].type == 'password' || document.all[i].type == 'textarea' 
				|| document.all[i].type == 'select-one' || document.all[i].type == 'select-multiple' || document.all[i].type == 'button') 
				&& document.all[i].name != 'navigationButtons' && document.all[i].id != 'ctrlShow' && document.all[i].name != 'label' 
				&& document.all[i].name != 'newLabel' && document.all[i].name != 'changeLabelButtons' && document.all[i].id != 'authPassword') {
					hideFields(document.all[i], labelCount);
				}
			}
		}
		document.getElementById('labelCount').value = labelCount;
	}
	
	function hideFields(field, labelCount) {
		if(field.parentNode.id != 'ctrlShow') {
			if(field.type != 'button') {
				var lbl = document.createElement('label');
				lbl.id = 'lbl' + labelCount;
				labelCount++;
				
				if(field.type == 'select-one') {
					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									lbl.innerHTML = field.childNodes[j].innerHTML;
								}
							}
						}
					}
				} else if(field.type == 'select-multiple') {
					var recordSelected = 0;

					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									recordSelected += 1;
									
									if(recordSelected == 1) {
										lbl.innerHTML += field.childNodes[j].innerHTML;
									} else {
										lbl.innerHTML += ', ' + field.childNodes[j].innerHTML;
									}
								}
							}
						}
					}
				} else {
					lbl.innerHTML = field.value + ' ';
				}
	
				var cell = field.parentNode;
				cell.appendChild(lbl);
			}
			
			field.style.display = 'none';
		}
	}
	
	callAddressType();
	function callAddressType(){
	var addressCode= document.getElementById('paraFrm_sameAddressType').value;
	
		if(addressCode=='N'){
		
		
		
		document.getElementById('relationAddress').readOnly=''; 
			document.getElementById('relationAddress').style.background='white';
		
		}else{
			
			document.getElementById('relationAddress').value='';
			document.getElementById('relationAddress').readOnly='true'; 
			 document.getElementById('relationAddress').style.background='#F2F2F2'; 
		}
	}
function sendforapprovalFun()
{	

	try{
		var empName=document.getElementById('paraFrm_employeeName').value
				var atrDate=document.getElementById('paraFrm_dateOfAtr').value
				if(trim(empName)==""){
					alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());
					document.getElementById('paraFrm_employeeName').focus();
					return false;
				}
				
				if(trim(document.getElementById('paraFrm_dateOfAtr').value) == "") {
					alert("Please select or enter the " + document.getElementById('date.ATR').innerHTML.toLowerCase());
					document.getElementById('paraFrm_dateOfAtr').focus();
					return false;
				}
			
				if(!dateCheckWithToday('paraFrm_dateOfAtr', 'date.ATR')) {
					document.getElementById('paraFrm_dateOfAtr').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_dateOfAtr',"date.ATR")){
							document.getElementById('paraFrm_dateOfAtr').focus();
							return false;   	
	   					}
				}
				
				if(trim(document.getElementById('paraFrm_empTravel').value) == "") {
					alert("Please enter the " + document.getElementById('emp.travel').innerHTML.toLowerCase());
					document.getElementById('paraFrm_empTravel').focus();
					return false;
				}
				//Date
				var fromDate = trim(document.getElementById('paraFrm_fromDate').value);
				if(fromDate == "") {
					alert("Please select "+document.getElementById('from').innerHTML.toLowerCase());
					document.getElementById('paraFrm_fromDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_fromDate',"from")){
							document.getElementById('paraFrm_fromDate').focus();
							return false;   	
	   					}
				}
				
				var toDate = trim(document.getElementById('paraFrm_toDate').value);
				if(toDate == "") {
					alert("Please select "+document.getElementById('to').innerHTML.toLowerCase());
					document.getElementById('paraFrm_toDate').focus();
					return false;
				}else {			
					if(!validateDate('paraFrm_toDate',"to")){
						document.getElementById('paraFrm_toDate').focus();
						return false;   	
	   				}		
				}
	
	  
	   			if(!dateDifferenceEqual(fromDate,toDate, 'paraFrm_fromDate', 'from', 'to')){
		      		document.getElementById('paraFrm_fromDate').focus();
		      		return false;
	   			} 
	   			
				//end date				
				
				

		var cust = document.getElementById('paraFrm_customer').checked;
		var mgm = document.getElementById('paraFrm_managmentTraining').checked;
		var training = document.getElementById('paraFrm_training').checked;
		var other = document.getElementById('paraFrm_Other').checked;
		var acq = document.getElementById('paraFrm_acquisition').checked;
		
		if((cust == false) && (mgm == false) && (training == false) && (other == false) && (acq == false))
		{
			alert("Please select atleast one Justification for travel");
			return false;
		}
		
		if(trim(document.getElementById('radioValue').value)==""){
			alert("Please select lowest cost option utilized");
			return false;
		}
		 var con=confirm('Do you really want to send this application for approval?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm_status').value='P'
	    	document.getElementById('paraFrm').action='TravelApplication_sendForApproval.action';
			document.getElementById('paraFrm').submit();
		}	
		
		}catch(e){
		
		}	
}

	function draftFun(status){
		try{
				var empName=document.getElementById('paraFrm_employeeName').value
				var atrDate=document.getElementById('paraFrm_dateOfAtr').value
				if(trim(empName)==""){
					alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());
					document.getElementById('paraFrm_employeeName').focus();
					return false;
				}
				
				if(trim(document.getElementById('paraFrm_dateOfAtr').value) == "") {
					alert("Please select or enter the " + document.getElementById('date.ATR').innerHTML.toLowerCase());
					document.getElementById('paraFrm_dateOfAtr').focus();
					return false;
				}
			
				if(!dateCheckWithToday('paraFrm_dateOfAtr', 'date.ATR')) {
					document.getElementById('paraFrm_dateOfAtr').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_dateOfAtr',"date.ATR")){
							document.getElementById('paraFrm_dateOfAtr').focus();
							return false;   	
	   					}
				}
				if(trim(document.getElementById('paraFrm_empTravel').value) == "") {
					alert("Please enter the " + document.getElementById('emp.travel').innerHTML.toLowerCase());
					document.getElementById('paraFrm_empTravel').focus();
					return false;
				}
				
				//Date
				var fromDate = trim(document.getElementById('paraFrm_fromDate').value);
				if(fromDate == "") {
					alert("Please select "+document.getElementById('from').innerHTML.toLowerCase());
					document.getElementById('paraFrm_fromDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_fromDate',"from")){
							document.getElementById('paraFrm_fromDate').focus();
							return false;   	
	   					}
				}
				
				var toDate = trim(document.getElementById('paraFrm_toDate').value);
				if(toDate == "") {
					alert("Please select "+document.getElementById('to').innerHTML.toLowerCase());
					document.getElementById('paraFrm_toDate').focus();
					return false;
				}else {			
					if(!validateDate('paraFrm_toDate',"to")){
						document.getElementById('paraFrm_toDate').focus();
						return false;   	
	   				}		
				}
	
	  
	   			if(!dateDifferenceEqual(fromDate,toDate, 'paraFrm_fromDate', 'from', 'to')){
		      		document.getElementById('paraFrm_fromDate').focus();
		      		return false;
	   			} 
	   			
				//end date				
				
			

		var cust = document.getElementById('paraFrm_customer').checked;
		var mgm = document.getElementById('paraFrm_managmentTraining').checked;
		var training = document.getElementById('paraFrm_training').checked;
		var other = document.getElementById('paraFrm_Other').checked;
		var acq = document.getElementById('paraFrm_acquisition').checked;
		
		if((cust == false) && (mgm == false) && (training == false) && (other == false) && (acq == false))
		{
			alert("Please select atleast one Justification for travel");
			return false;
		}
	
	
		if(trim(document.getElementById('radioValue').value)==""){
			alert("Please select lowest cost option utilized");
			return false;
		}
			
				
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm_status').value='D'
		  		document.getElementById('paraFrm').action = 'TravelApplication_save.action?status='+status;
				document.getElementById('paraFrm').submit();
		    }
			catch(e)
			{
				//alert(e);
			}
	
	}

	function backtolistFun() {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="TravelApplicationApp_input.action";
		  	document.getElementById('paraFrm').submit();  
	}
	
	function printFun() {	
	window.print();
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TravelApplication_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	}
	function reportFun() 
{
	alert("No Record To Display Report ");
}

	function cancelApprovedFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="TravelApplication_cancel.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function resetFun() {
		
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'TravelApplication_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
  	
	/* Numvers Only Function*/
	function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=46)
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
             }
             return true;
      
      }		 
	 

//Approval Start
function authorizedsignoffFun() {
		try{
		
		
		
		}catch(e){
			//alert(e)
		}
		var conf = confirm('Do you want to approve the application?');
		if(conf) {
			document.getElementById('paraFrm').action="TravelApplication_approve.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function rejectFun() {
		var conf = confirm('Do you want to reject the application?');
		if(conf) {
			document.getElementById('paraFrm').action="TravelApplication_reject.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun() {
		var conf = confirm('Do you want to send back the application?');
		if(conf) {
			document.getElementById('paraFrm').action="TravelApplication_sendBack.action";
			document.getElementById('paraFrm').submit();
		}
	}
//End Approval
function callEmailText(){
	try{
	var final=document.getElementById('paraFrm_finalApprover').checked;
	
	if(final==true){
		document.getElementById('emailId').style.display="";
		document.getElementById('forwardManager').style.display="none";
	}else {
		document.getElementById('emailId').style.display="none";
		document.getElementById('forwardManager').style.display="";
	}
	}catch(e){}
}

function forwardFun() {
try{
	var forwardApproverName=document.getElementById('paraFrm_forwardApproverName').value
	if(forwardApproverName==""){
		alert("Please select forward to manager");
		document.getElementById('paraFrm_forwardApproverName').focus();
		return false;
	}
	
}catch(e){
	//alert(e)
}
	
	var conf = confirm('Do you want to forward the application?');
	if(conf) {
		document.getElementById('paraFrm').action="TravelApplication_forward.action";
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}
	

function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
function closeFun() {
					window.close();
			}

</script>