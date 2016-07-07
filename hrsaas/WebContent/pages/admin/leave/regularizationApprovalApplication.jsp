 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/admin/leave/Ajax.js"></script>
<script language="javascript"
	src="../pages/admin/leave/mootools.v1.11.js"></script>
<script language="javascript"
	src="../pages/admin/leave/nogray_time_picker_min.js"></script>
<script language="javascript">

</script>


<s:form action="RegularizationApproval" method="post" name="LeavePolicy"
	id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<input type="hidden" name="time2" id="time2" onchange="aaa()" />
		<s:hidden name="month" />
		<s:hidden name="year" />

		<s:hidden name="shiftCode" />
		<s:hidden name="empGender" />
		<s:hidden name="policyCode" />
		<s:hidden name="applicationCode" />
		<s:hidden name="status" />
		<s:hidden name="applyFor" />
		<s:hidden name="condTrueFlag" />
		<s:hidden name="countValue" />
		<s:hidden name="viewApplFlag" />
		<s:hidden name="showButtonFlag" />
		<s:hidden name="commentsFlag" />
		<s:hidden name="swipeType" />
	 

		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>

					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					<s:if test="swipeFlag">Regularization Approval Form </s:if> <s:if
						test="lateFlag"> Late Regularization </s:if> <s:if
						test="redressalFlag">Redressal Application  </s:if> </strong></td>
					<td width="3%" valign="top" class="txt">
					 
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="showButtonFlag">
						<s:submit cssClass="token"
							action="RegularizationApproval_%{approveActionName}"
							value="  Approve" onclick="return callApplySwipe('approved')" />
						<s:submit cssClass="token"
							action="RegularizationApproval_%{rejectActionName}"
							value="  Reject" onclick="return callApplySwipe('reject')" />
						<s:submit cssClass="token"
							action="RegularizationApproval_%{sendBackActionName}"
							value="  Send Back" onclick="return callApplySwipe('send back')" />
					</s:if> 
					
					<input type="button" name="btn" value="Back" onclick="backFun();" />
					
					
					
					
					<!--<s:submit cssClass="back" action="RegularizationApproval_onLoad"
						value="   Back" />
						
						
						--></td>

					<td width="22%" valign="middle">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="6" class="text_head"><strong
						class="forminnerhead">Employee Details </strong></td>
				</tr>
				<tr>
					<td width="22%" height="22" class="formtext"><label
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:<font color="red">*</font></td>
					<td height="22" colspan="4">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="14%" colspan="1"><s:textfield name="empToken"
								size="10" readonly="true" /></td>
							<td width="86%" colspan="3"><s:hidden name="empCode" /> <s:textfield
								name="empName" size="85" readonly="true" /></td>
						</tr>
					</table>

					</td>
				</tr>
				<tr>
					<td height="22" class="formtext"><label name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="28%" height="22"><s:textfield name="empBranch"
						size="30" readonly="true" /></td>

					<td class="formtext" colspan="2"><label name="designation"
						id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="38%"><s:textfield name="empDesg" size="30"
						readonly="true" /></td>
				</tr>

				<tr>
					<td height="22" class="formtext" nowrap="nowrap"><label
						class="set" name="applied.date" id="applied.date"
						ondblclick="callShowDiv(this);"><%=label.get("applied.date")%></label>
					:</td>
					<td width="28%" height="22"><s:textfield
						name="applicationDate" size="30" readonly="true" /></td>

					<td class="formtext" colspan="3"><!--<s:if test="swipeFlag">
					
					
					<s:select    
							name="applyForDecode" value="%{swipeType}"  disabled="true"
							list="#{'-1':'--Select--','1':'Forgot to bring access card','2':'Forgot to login/logout ','3':'Access Card Not Issued','4':'Special Sanction',
				'5':'Swipe System Not Working ','6':'Outdoor visit','7':'Late Regularization'}" 
							>
						</s:select>
					 
					
					
					
						<s:select  name="applyForDecode"
							value="%{swipeType}"
							list="#{'1':'Forgot Flash card','2':'Forgotten to Flash Flash','3':'Special Sanction','4':'Flash Card Not Issued','5':'Swipe System Not Working On My System','6':'Swipe System Not Loading','7':'Swipe System Capturing Incorrect Data','8':'Swipe System - Forget To LOGIN / LOGOUT','9':'Swipe System - Forget To Bring Access Card','10':'Swipe System - Others','LR':'Late Regularization','RR':'Redressal'}">
						</s:select>
						
						
					</s:if> <s:else></s:else></td>-->


				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="6">
			<table width="100%" cellpadding="1" border="0" cellspacing="1">
				<tr>
					<td colspan="2"><strong>Approver List </strong></td>
					<td width="18%"><strong>Keep Inform To : </strong></td>
					<td width="21%" align="left"><s:hidden name="informCode" /> <s:hidden
						name="informToken" />&nbsp;</td>
					<td width="10%" nowrap="nowrap"><!--<img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'Regularization_f9informTo.action');" />
  <s:textfield name="informName" size="30" readonly="true"/>
  <s:submit value="  Add" cssClass="add" action="RegularizationApproval_addInformListForRedressal" onclick="return callAddInfo();"></s:submit> 

  --></td>
				</tr>

				<tr>
					<td colspan="2" valign="top">
					<table width="100%" cellpadding="1" cellspacing="1" border="0">
						<%
						int ap = 0;
						%>
						<s:iterator value="approverList">
							<tr valign="top">
								<td width="20%" nowrap="nowrap"><s:property
									value="apprSrNo" /><s:hidden name="approverCode" /></td>
								<td><s:property value="approverName" /><s:hidden
									name="approverName" /></td>
							</tr>

						</s:iterator>
					</table>
					</td>
					<td width="18%" align="right" valign="top"></td>
					<td width="16%" colspan="2" valign="top">
					<table width="100%" cellpadding="0" cellspacing="0">
						<%
						int kep = 0;
						%>
						<s:iterator value="informList">
							<tr>
								<td><%=++kep%> )</td>
								<s:hidden name="keepInformCode" />
								<td><s:property value="keepInform" /><s:hidden
									name="keepInform" /></td>
							</tr>

						</s:iterator>
					</table>

					</td>
				</tr>

			</table>

			</td>
		</tr>
		<%!int t = 0;%>
		<s:if test="lateFlag">
			<tr>
				<td colspan="6">
				<table width="100%" cellpadding="1" class="formbg" border="0">
					<tr>
						<td width="15%" colspan="1" align="center" class="formth"><label
							class="set" name="date" id="date111"
							ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
						<td width="13%" colspan="1" align="center" class="formth"><label
							class="set" name="shift.time" id="shift.name11"
							ondblclick="callShowDiv(this);"><%=label.get("shift.time")%></label></td>
						<td width="12%" colspan="1" align="center" class="formth"><label
							class="set" name="in.time" id="in.time"
							ondblclick="callShowDiv(this);"><%=label.get("in.time")%></label>
						</td>
						<td width="11%" align="center" class="formth"><label
							class="set" name="late.hrs" id="late.hrs"
							ondblclick="callShowDiv(this);"><%=label.get("late.hrs")%></label>
						</td>
						<td width="13%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set"
							name="late.hrs.to.be.deducted" id="late.hrs.to.be.deducted"
							ondblclick="callShowDiv(this);"><%=label.get("late.hrs.to.be.deducted")%></label>
						</td>
						<td width="13%" colspan="2" align="center" class="formth"><label
							class="set" name="late.hrs.to.be.deducted.from"
							id="late.hrs.to.be.deducted.from" ondblclick="callShowDiv(this);"><%=label.get("late.hrs.to.be.deducted.from")%></label>
						</td>

						<!-- 
  <td width="13%" colspan="1" align="center" class="formth">
   <s:submit name="submit" cssClass="delete" action="RegularizationApproval_deleteLate" value="  Delete" onclick="return callDeleteLate();"></s:submit>  
    <input type="checkbox" id="applyAll" name="checkbox2"  onclick="callCheckAll()"/>
       </td>       
-->

					</tr>

					<%
					int i = 0;
					%>

					<s:iterator value="regList">

						<tr id="<%=i%>">
							<td align="center" class="sortableTD"><s:property
								value="date" /><s:hidden name="date" /></td>
							<td align="center" class="sortableTD"><s:property
								value="shiftTime" /><s:hidden name="shiftTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="inTime" /><s:hidden name="inTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="lateHrs" /><s:hidden name="lateHrs" /></td>
							<td align="center" class="sortableTD"><s:property
								value="lateHrsDeduct" /><s:hidden name="lateHrsDeduct" /></td>
							<td align="center" colspan="2" class="sortableTD"><s:property
								value="lateHrsDeductFrom" /><s:hidden name="lateHrsDeductFrom" /><s:hidden
								name="lateHrsDeductFromCode" id="<%="lateHrsDeductFromCode"+i%>" /></td>

							<!--<td align="center"><input type="checkbox" id="<%="leaveCheck"+i%>" value='<s:property value="date"/>' name="sCheck"/>
</td>

-->
						</tr>



						<%
						i++;
						%>
					</s:iterator>
					<%
					t = i;
					%>

					<tr>
						<td width="15%" colspan="1" align="center"></td>
						<td width="13%" colspan="1" align="center"></td>
						<td width="12%" colspan="1" align="center"></td>
						<td width="11%" align="center"><strong>Total: </strong></td>
						<td width="13%" colspan="1" align="center"><strong><s:property
							value="totalLateHrs" /></strong></td>
						<td width="13%" colspan="1" align="center">&nbsp;</td>
						<td width="13%" colspan="1" align="center"><s:property
							value="totalLateHrsInMin" /></td>
					</tr>

				</table>
				</td>
			</tr>
			<%
			int kk = 0;
			%>

		</s:if>
		<%!int tt = 0;%>


		<s:if test="swipeFlag">
			<tr>
				<td colspan="6">
				<table width="100%" cellpadding="1" class="formbg" border="0" bordercolor="red">
					<tr>
						<td width="15%"  align="center" class="formth"><label
							class="set" name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
						<td width="15%"  align="center" class="formth"
							nowrap="nowrap"><label class="set" name="recorded.in.time"
							id="recorded.in.time" ondblclick="callShowDiv(this);"><%=label.get("recorded.in.time")%></label></td>
						<td width="15%"  align="center" class="formth"
							nowrap="nowrap"><label class="set" name="recorded.out.time"
							id="recorded.out.time" ondblclick="callShowDiv(this);"><%=label.get("recorded.out.time")%></label></td>
						<td width="15%" align="center" class="formth" nowrap="nowrap"><label
							class="set" name="actual.in.time" id="actual.in.time"
							ondblclick="callShowDiv(this);"><%=label.get("actual.in.time")%></label>
						</td>
						<td width="15%"  align="center" class="formth"
							nowrap="nowrap"><label class="set" name="actual.out.time"
							id="actual.out.time" ondblclick="callShowDiv(this);"><%=label.get("actual.out.time")%></label>
							</td>
					 

		<td width="25%"  align="center" class="formth"
							nowrap="nowrap">
							Reason
							</td>

					</tr>

					<%
					int s = 0;
					%>

					<s:iterator value="swipeList">
						<tr>
							<td align="center" class="sortableTD"><s:property
								value="date" /><s:hidden name="date" /></td>
							<td align="center" class="sortableTD"><s:property
								value="shiftTime" /><s:hidden name="shiftTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="inTime" /><s:hidden name="inTime" /></td>
							<td align="center" class="sortableTD" nowrap="nowrap"><s:textfield
								name="fromTime" id="<%="fromTime"+s%>" readonly="true" size="05" maxlength="5"
								onkeypress="return numbersonlyWithColun(this);" />(HH24:MI)</td>
							<td align="center" nowrap="nowrap" class="sortableTD"><s:textfield
								name="toTime" id="<%="toTime"+s%>" readonly="true" size="05" maxlength="5"
								onkeypress="return numbersonlyWithColun(this);" />(HH24:MI)</td>
								
							<td nowrap="nowrap">
							
									<s:select   disabled="true"
							name="reasonItt" value="%{reasonItt}"    
							list="#{'-1':'---Select---','1':'Forgot to bring access card','2':'Forgot to login/logout ','3':'Access Card Not Issued','4':'Special Sanction',
				'5':'Swipe System Not Working ','6':'Outdoor visit','7':'Late Regularization','8':'Work From Home'}" 
							/>
							
							</td>	

							<!--<td align="center" class="sortableTD"><input type="checkbox" id="<%="swipe"+s%>" value='<s:property value="date"/>' name="sCheck"  /></td>
								-->

						</tr>
						<%
						s++;
						%>
					</s:iterator>
					<%
					tt = s;
					%>
				</table>
				</td>
			</tr>

		</s:if>

		<s:if test="redressalFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">


					<tr>
						<td width="9%" class="formth"><label class="set" name=""
							id="" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="leave.type" id="leave.type345"
							ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="from.date" id="from.date35" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="to.date" id="to.date22" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="penalty.days" id="penalty.days"
							ondblclick="callShowDiv(this);"><%=label.get("penalty.days")%></label>
						</td>
						<td width="15%" class="formth"><label class="set"
							name="redressal.days" id="redressal.days"
							ondblclick="callShowDiv(this);"><%=label.get("redressal.days")%></label>
						</td>
						<!--						 
					<td width="16%" class="formth">					
					<s:submit name="submit" cssClass="delete" action="RegularizationApproval_deleteRedressal" value="  Delete" onclick="return callDeleteRed();"></s:submit>
      <input type="checkbox" id="redressal" name="checkbox2"  onclick="callCheckAllRed()"/>
       </td>				
					-->
					</tr>
					<%
					int red = 0;
					%>
					<%!int redCount = 0;%>
					<s:iterator value="list">
						<tr>
							<td width="9%" align="center" class="sortableTD"><%=red + 1%>
							</td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="leaveType" /><s:hidden name="rLeaveCode" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rFromDate" /><s:hidden name="rFromDate" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rToDate" /><s:hidden name="rToDate" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rPenaltyDays" /><s:hidden name="rPenaltyDays"
								id="<%="penDays"+red%>" /></td>
							<td width="15%" align="center" class="sortableTD"><input
								type="text" name="rrdressalDays" size="5"
								id="<%="redreDays"+red%>"
								value="<s:property value="rrdressalDays"/>"
								onkeyup="return checkValidation('<%=red%>');"
								onkeypress="return numbersonly(this);" maxlength="5" /> <s:hidden
								name="redressalAdjDays" /> <s:hidden name="redressalAdjStatus" />
							</td>
							<!--<td width="16%" align="center">
					  <input type="checkbox"
							name="lateCheckBox" id="<%="redre"+red%>"
							value='<s:property value="rFromDate"/>'
							 /> 
					</td>
				  -->
						</tr>
						<%
						red++;
						%>
					</s:iterator>
					<%
					redCount = red;
					%>
					<%
					if (redCount == 0) {
					%>
					<tr>
						<td colspan="6" align="center"><font color="red">
						There in no data to display</font></td>
					</tr>
					<%
					}
					%>
				</table>
				</td>
			</tr>

		</s:if>




		<tr>
			<s:if test="redressalFlag">
				<td nowrap="nowrap" width="15%"><label
					name="reason.for.redressal" id="reason.for.redressal"
					ondblclick="callShowDiv(this);"><%=label.get("reason.for.redressal")%></label>:</td>
			</s:if>
			<s:else>
				<td nowrap="nowrap" width="15%"><label name="reasons"
					id="reasons" ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:</td>
			</s:else>


			<td colspan="3" nowrap="nowrap" valign="top"><s:textarea
				name="reason" cols="30" rows="4" disabled="true"></s:textarea> 
				<s:if test="hideApproverCommentsSectionFlag">
				  <label class="set" name="approver.comments"
					id="approver.comments234" ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>:
					<s:textarea name="approverComents" cols="30" rows="4"></s:textarea>
			    </s:if>
			</td>
		</tr>

		<s:if test="commentsFlag">
			<tr>
				<td colspan="6"><b>Approver Comments:</b></td>
			</tr>
			<tr>
				<td colspan="6">
				<%
				int app = 0;
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" class="formth"><label class="set" name="sno"
							id="sno33333" ondblclick="callShowDiv(this);"><%=label.get("sno")%></td>
						<td colspan="2" class="formth"><label class="set"
							name="approver.name" id="approver.name4555"
							ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label>
						</td>
						<td colspan="2" class="formth"><label class="set"
							name="approver.comments" id="approver.comments234"
							ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
						</td>
					</tr>
					<s:iterator value="viewApproverList">
						<tr>
							<td colspan="1" align="center"><%=++app%></td>
							<td colspan="2"><s:property value="approverNameView" /></td>
							<td colspan="2"><s:property value="apprverComments" /></td>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="showButtonFlag">
						<s:submit cssClass="token"
							action="RegularizationApproval_%{approveActionName}"
							value="  Approve" onclick="return callApplySwipe('approved')" />
						<s:submit cssClass="token"
							action="RegularizationApproval_%{rejectActionName}"
							value="  Reject" onclick="return callApplySwipe('reject')" />
						<s:submit cssClass="token"
							action="RegularizationApproval_%{sendBackActionName}"
							value="  Send Back" onclick="return callApplySwipe('send back')" />
					</s:if> 
					
					<input type="button" name="btn" value="Back" onclick="backFun();" />
					
					<!--<s:submit cssClass="back" action="RegularizationApproval_onLoad"
						value="   Back" />
						
						--></td>
					<td width="22%" valign="middle"></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>


 <s:hidden name="source" id="source" />

</s:form>
<script>


function backFun()
{

	document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		document.getElementById('paraFrm').action = 'RegularizationApproval_onLoad.action';
		}
		document.getElementById('paraFrm').submit();


}

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789.").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			
			return false;
		}
	}



			onLoad();
			
			function onLoad(){
			var tot='<%=t%>';	
			var count='0';			
			if(tot>0){
			//if(document.getElementById('paraFrm_condTrueFlag').value=='true'){
				
					for(var i=0; i<tot;i++){
					if(document.getElementById('lateHrsDeductFromCode'+i).value==''){						
					document.getElementById(i).style.background='#FFC488';
					count++;
					}			
				}
				//}
				document.getElementById('paraFrm_countValue').value=count;	
				}
				var aa=document.getElementById('paraFrm_countValue').value;
				if(aa>0){
				document.getElementById('paraFrm_condTrueFlag').value='true';
				}
			}
			
						function checkValidation(id){						
						if(document.getElementById('redreDays'+id).value==''){
						document.getElementById('redreDays'+id).value='0';
						}										
						var penDay=document.getElementById('penDays'+id).value;			
					
						var redreDay=document.getElementById('redreDays'+id).value;						
						if(parseFloat(redreDay)>penDay){
						alert('Redressal days must be less than penalty days');
						document.getElementById('redreDays'+id).value=penDay;
						return false;
						}						
						}
			
			
							function IsValidTime(id) {			
				alert('id--'+id);
			var timeStr=document.getElementById('fromTime'+id).value;
				alert('--'+timeStr);
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			
			var matchArray = timeStr.match(timePat);
		
			if (matchArray == null) {
			alert("Time is not in a valid format.");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];
			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }
			
			if (hour < 0  || hour > 23) {
			alert("Hour must be between 1 and 12. (or 0 and 23 for military time)");
			return false;
			}
			if (hour <= 12 && ampm == null) {
			if (confirm("Please indicate which time format you are using.  OK = Standard Time, CANCEL = Military Time")) {
			alert("You must specify AM or PM.");
			return false;
			   }
			}
			if  (hour > 12 && ampm != null) {
			alert("You can't specify AM or PM for military time.");
			return false;
			}
			if (minute<0 || minute > 59) {
			alert ("Minute must be between 0 and 59.");
			return false;
			}
			if (second != null && (second < 0 || second > 59)) {
			alert ("Second must be between 0 and 59.");
			return false;
			}
			return false;
			}
				



				function callLeave(id){
				
				 // retrieveURL('Regularization_selCategory.action?','paraFrm');				
				javascript:callsF9(500,325,'Regularization_f9LeaveAction.action?srNo='+id);
				
				}


				function callAddInfo(){
				var info=document.getElementById('paraFrm_informName').value;
				if(info==''){
				alert('Please select keep inform to');
				return false;
				}				
				}

				function callDelete(){
				var con=confirm('Do you want to delete !');
				if(con){
				return true;
				}
				else{
				return false;
				}
				}


		function IsValidTime(id) {		
				
			var timeStr=id;//document.getElementById('fromTime'+id).value;
				
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			
			var matchArray = timeStr.match(timePat);
		
			if (matchArray == null) {
			alert("Please enter time in a valid format.");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];
			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }
			
			if (hour < 0  || hour > 23) {
			alert("Hour must be between 0 and 23");
			return false;
			}
		
			
			if (minute<0 || minute > 59) {
			alert ("Minute must be between 0 and 59.");
			return false;
			}
			if (second != null && (second < 0 || second > 59)) {
			alert ("Second must be between 0 and 59.");
			return false;
			}
			return true;
			}


			function callApplySwipe(status){			
			
			if(document.getElementById('paraFrm_condTrueFlag').value=='true'){
			var cc=document.getElementById('paraFrm_countValue').value;
			
				if(cc >0){
				alert("You don't have safficient balance ,please romove one or more record  ");
				return false;
				}
			
			}
			if(document.getElementById('paraFrm_applyFor').value=='RR'){
			}
			else if(document.getElementById('paraFrm_applyFor').value=='RR'){
			}
			else if(document.getElementById('paraFrm_applyFor').value=='PT'){
			}
			else{
				var swipe='<%=tt%>';	
			for(var i=0; i<swipe;i++){
			if(IsValidTime(document.getElementById('fromTime'+i).value)){			
			}
			else{
			document.getElementById('fromTime'+i).focus();
			return false;
			}	
			if(IsValidTime(document.getElementById('toTime'+i).value)){			
			}
			else{
			document.getElementById('toTime'+i).focus();
			return false;
			}
					
			}	
			
			}
			
			var con=confirm('Do you want to '+status+' this application ?');
				if(con){
				return true;
				}
				else{
				return false;
				}		
			}


function numbersonlyWithColun(myfield)
{

	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789:").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
function numbersonlyWithdot(myfield)
{

	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789.").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
function callCheckAll(){	
				var tot='<%=t%>';		
				if(document.getElementById('applyAll').checked){		
				for(var i=0; i<tot;i++){		
				document.getElementById('leaveCheck'+i).checked=true;
				}
				}	
				else{
				for(var i=0; i<tot;i++){		
				document.getElementById('leaveCheck'+i).checked=false;
				}
				}	
		}
		
		function callCheckAllSwipe(){	
				var tot='<%=tt%>';	
				if(document.getElementById('swipeCheck').checked){		
				for(var i=0; i<tot;i++){		
				document.getElementById('swipe'+i).checked=true;
				}
				}	
				else{
				for(var i=0; i<tot;i++){		
				document.getElementById('swipe'+i).checked=false;
				}
				}	
		}
		
		function callCheckAllRed(){	
				var tot='<%=redCount%>';			
				if(document.getElementById('redressal').checked){		
				for(var i=0; i<tot;i++){		
				document.getElementById('redre'+i).checked=true;
				}
				}	
				else{
				for(var i=0; i<tot;i++){		
				document.getElementById('redre'+i).checked=false;
				}
				}	
		}
		
		function callDeleteRed(){
				var tot='<%=redCount%>';		
				var count='0';
				for(var i=0; i<tot;i++){
				if(document.getElementById('redre'+i).checked){
				count=count+1;
				}
				}
				if(count=='0'){
				alert('Please select at least one check box');
				return false;
				}		
		var con=confirm('Do you want to delete !');
				if(con){
				return true;
				}
				else{
				return false;
				}
		}
		
		function callDeleteSwipe(){
				var tot='<%=tt%>';		
				var count='0';
				for(var i=0; i<tot;i++){
				if(document.getElementById('swipe'+i).checked){
				count=count+1;
				}
				}
				if(count=='0'){
				alert('Please select at least one check box');
				return false;
				}		
		var con=confirm('Do you want to delete !');
				if(con){
				return true;
				}
				else{
				return false;
				}
		}
		
		
		function callDeleteLate(){
				var tot='<%=t%>';		
				var count='0';
				for(var i=0; i<tot;i++){
				if(document.getElementById('leaveCheck'+i).checked){
				count=count+1;
				}
				}
				if(count=='0'){
				alert('Please select at least one check box');
				return false;
				}		
		var con=confirm('Do you want to delete !');
				if(con){
				return true;
				}
				else{
				return false;
				}
		}
</script>