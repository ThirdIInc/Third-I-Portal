<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@page import="java.util.HashMap;"%>
<s:hidden name="application.empId" />
<s:form action="ConferenceBooking" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="application.empId" />
	<s:hidden name="confRoomBranch" />
	<s:hidden name="confRoomResPerson" />
	<s:hidden name="application.empId" />
	<s:hidden name="appCancelFlg"/>
	<s:hidden name="source" id="source" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<s:hidden name="approverCode" />
		<tr>
			<td colspan="3" width="100%"></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Conference
					Booking Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
				<s:hidden name="confBooking.confCode"></s:hidden>
				<s:hidden name="level"></s:hidden>
			</table>
			</td>
		</tr>
		<tr>

		</tr>

		<s:if test="isApprove">
			<tr>
				<td><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%" id="ctrlHide">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>

						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>
		<!-- EMployee Details Table Starts Here -->
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td>
					<table width="98%" cellpadding="0" cellspacing="2" align="center">
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="booked.by"  id="booked.by" ondblclick="callShowDiv(this);"><%=label.get("booked.by")%></label>
							:<font color="red" size="2">*</font></td>
							<td colspan="3" width="75%" nowrap="nowrap"><s:hidden
								name="confBooking.bookByCode"></s:hidden> <s:textfield
								name="confBooking.bookByToken" size="25" readonly="true"
								cssStyle="background-color: #F2F2F2;" /> <s:textfield
								theme="simple" name="confBooking.bookBy" size="50"
								maxlength="30" readonly="true"
								cssStyle="background-color: #F2F2F2;" /> <s:if
								test="generalFlag"></s:if> <s:elseif test="isApprove"></s:elseif>
							<s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="18" theme="simple" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'ConferenceBooking_f9BookedBy.action'); ">
							</s:else></td>
						</tr>
						<tr>
							<td><label class="set" id="branch" name="branch"
								onDblClick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td><s:textfield size="25" name="confBooking.branch"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td><label class="set" id="department" name="department"
								onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:textfield size="25" name="confBooking.dept"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="designation" name="designation"
								onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td><s:textfield size="25" name="confBooking.desig"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							<td><label class="set" id="appDate" name="appDate"
								onDblClick="callShowDiv(this);"><%=label.get("appDate")%></label>
							<font color="red">*</font>:</td>
							<td><s:textfield size="25" name="confBooking.confAppDate"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--  Employee Details Ends here -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"><label class="set"
								name="conferencebookapp" id="conferencebookapp"
								ondblclick="callShowDiv(this);"><%=label.get("conferencebookapp")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" name="conference.date"
								id="conference.date" ondblclick="callShowDiv(this);"><%=label.get("conference.date")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:if
								test="isApprove">
								<s:textfield size="20" name="confBooking.requireDate"
									readonly="true" />
							</s:if> <s:else>
								<s:textfield size="20" name="confBooking.requireDate"
									onkeypress="return numbersWithHiphen();" maxlength="10" />
								<s:a
									href="javascript:NewCal('paraFrm_confBooking_requireDate','DDMMYYYY');">
									<img src="../pages/images/Date.gif" class="iconImage"
										height="16" align="absmiddle" width="16" id="ctrlHide">
								</s:a>
							</s:else></td>
							<td width="25%" colspan="1"><label class="set"
								name="conference.room" id="conference.room"
								ondblclick="callShowDiv(this);"><%=label.get("conference.room")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:hidden
								name="confBooking.venueCode"></s:hidden> <s:textfield size="20"
								name="confBooking.venue" readonly="true" /> <s:if
								test="isApprove"></s:if> <s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" id="ctrlHide" width="18" theme="simple"
									onclick="javascript:callsF9(500,325,'ConferenceBooking_f9Venueaction.action');">
							</s:else></td>

						</tr>
						<tr>
							<td nowrap="nowrap" width="25%"><label class="set"
								name="from.time" id="from.time" ondblclick="callShowDiv(this);"><%=label.get("from.time")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%"><s:if test="isApprove">
								<s:textfield size="20" name="confBooking.fromTime"
									readonly="true" />
							</s:if> <s:else>
								<s:textfield size="20" name="confBooking.fromTime"
									onkeypress="return numbersWithColonandDot();" maxlength="5"
									onfocus=" clearText('paraFrm_confBooking_fromTime','HH24:MI');"
									onblur="setText('paraFrm_confBooking_fromTime','HH24:MI');" />
							</s:else></td>
							<td nowrap="nowrap"><label class="set" name="to.time"
								id="to.time" ondblclick="callShowDiv(this);"><%=label.get("to.time")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%"><s:if test="isApprove">
								<s:textfield size="20" name="confBooking.toTime" readonly="true" />
							</s:if> <s:else>
								<s:textfield size="20" name="confBooking.toTime"
									onkeypress="return numbersWithColonandDot();" maxlength="5"
									onfocus=" clearText('paraFrm_confBooking_toTime','HH24:MI');"
									onblur="setText('paraFrm_confBooking_toTime','HH24:MI');" />
							</s:else></td>
						</tr>
						<!-- Booked by row here only previously -->
						<s:if test="isApprove">
							<tr>
								<td width="25%"><label class="set" name="minPart"
									id="minPart" ondblclick="callShowDiv(this);"><%=label.get("minPart")%></label>
								:</td>
								<td width="25%"><s:textfield size="20" readonly="true"
									name="minParticipant" onkeypress="return numbersOnly();"
									maxlength="4" /></td>
								<td><label class="set" name="maxPart" id="maxPart"
									ondblclick="callShowDiv(this);"><%=label.get("maxPart")%></label>
								:</td>
								<td width="25%"><s:textfield size="20" readonly="true"
									name="maxParticipant" onkeypress="return numbersOnly();"
									maxlength="4" /></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="25%"><label class="set" name="minPart"
									id="minPart" ondblclick="callShowDiv(this);"><%=label.get("minPart")%></label>
								:</td>
								<td width="25%"><s:textfield size="20"
									name="minParticipant" onkeypress="return numbersOnly();"
									onkeyup="calNoOfParticipants()" maxlength="5" /></td>
								<td><label class="set" name="maxPart" id="maxPart"
									ondblclick="callShowDiv(this);"><%=label.get("maxPart")%></label>
								:</td>
								<td width="25%"><s:textfield size="20"
									name="maxParticipant" onkeyup="calNoOfParticipants()"
									onkeypress="return numbersOnly();" maxlength="5" /></td>
							</tr>
						</s:else>
						<tr>
							<td width="25%"><label class="set" name="participant"
								id="participant" ondblclick="callShowDiv(this);"><%=label.get("participant")%></label>
							:</td>
							<td width="25%"><s:if test="isApprove">
								<s:textfield size="20" name="confBooking.noParticipant"
									readonly="true" />
							</s:if> <s:else>
								<s:textfield size="20" name="confBooking.noParticipant"
									onkeypress="return numbersOnly();" readonly="true"
									maxlength="5" />
							</s:else></td>
							<td><label class="set" name="status" id="status"
								ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
							:</td>
							<td width="25%"><s:select name="status" theme="simple"
								disabled="true" value="%{status}"
								list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdrawn','C':'Cancelled'}" />
							<s:hidden name="hiddenStatus" /></td>
						</tr>
						<!-- ##################################### KEEP INFORMED BEGINS #########################-->
						<tr>
							<td align="left" colspan="4">
							<table width="100%" border="0" id="keepInformedTable">
								<tr>
									<td width="26%" align="left"><label class="set"
										name="keepInformedTo" id="keepInformedTo"
										ondblclick="callShowDiv(this);"><%=label.get("keepInformedTo")%></label>:</td>
									<td><s:hidden name="keepHidden" /> <s:hidden
										name="informCode" id="paraFrm_informCode" /> <s:hidden
										name="informToken" /> <s:textfield name="informName"
										id="paraFrm_informName" size="26" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" width="16"
										height="15" class="iconImage"
										onclick="javascript:callsF9(500,325,'ConferenceBooking_f9KeepinformTo.action');"
										id="ctrlHide" /> <input type="button" value="Add" Class="add"
										onclick="chkCom();return callAddKeepInfo();"></td>
									&nbsp;
									<td><label class="set" name="otherEmailAdd"
										id="otherEmailAdd" ondblclick="callShowDiv(this);"><%=label.get("otherEmailAdd")%></label>
									:</td>
									<td width="20%"><s:textarea name="otherEmail" cols="32"
										rows="2" readonly="false" /></td>
								</tr>
								<%
								int counter = 1;
								%>
								<s:iterator value="keepInformedList">
									<tr>
										<td width="188px"><s:hidden name="keepInformToCode"
											id="paraFrm_keepHidden<%=counter%>" /><%=counter%> ) <s:property
											value="keepInformToName" /> &nbsp;</td>
										<td><img src="../pages/common/css/icons/delete.gif"
											onclick="deleteCurrentRow(this);" id="ctrlHide"></td>

									</tr>
									<%
									counter++;
									%>
								</s:iterator>
							</table>
							</td>
						</tr>
						<!-- ##################################### KEEP INFORMED ENDS #########################-->
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>

					<table class="formbg" width="100%">
						<tr>
							<td class="formth" width="5%"><label class="set" name="srno"
								id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>.</td>
							<!--<td class="headercell" width="20%">Code</td>-->
							<td class="formth" width="20%"><label class="set"
								name="accessory" id="accessory" ondblclick="callShowDiv(this);"><%=label.get("accessory")%></label></td>
							<td class="formth" width="20%"><label class="set"
								name="required" id="required" ondblclick="callShowDiv(this);"><%=label.get("required")%></label></td>
							<td class="formth" width="15%"><label class="set"
								name="quantity" id="quantity" ondblclick="callShowDiv(this);"><%=label.get("quantity")%></label></td>
						</tr>
						<%
							int i = 0;
							int j = 1;
							HashMap afdata = (HashMap) request.getAttribute("data");
						%>
						<%!int m = 0;%>
						<s:iterator value="tableDetailList" status="stat">
							<%
							String audFlag = (String) afdata.get("" + i);
							%>
							<tr>
								<td width="5%" class="sortableTD" align="center"><%=j%></td>
								<td width="25%" class="sortableTD"><s:property
									value="accessoryName" /></td>
								<s:hidden name="accessoryName" />
								<!--        %{accessoryCode}-->
								<td class="sortableTD"><s:if test="%{chkFlag}">
									<input type="checkbox" class="checkbox" checked="checked"
										name="confChk" id='<%="h"+i%>'
										value="<%=audFlag.equals("Y")?"checked":""%>"
										onclick="callChk(<%=i%>)" />
								</s:if> <s:else>
									<input type="checkbox" class="checkbox" name="confChk"
										value="<%=audFlag.equals("Y")?"checked":""%>" id='<%="h"+i%>'
										onclick="callChk(<%=i%>)" />
								</s:else> <input type="hidden" name="confChkFlag" id="<%=i%>"
									value="<%=audFlag.equals("A")?"N":audFlag%>" /> <s:hidden
									name="accessoryCode" value="%{accessoryCode}"></s:hidden></td>
								<td class="sortableTD"><s:textfield name="quantity"
									size="28" onkeypress="return numbersOnly();" maxlength="3"
									id='<%="t"+i%>' /></td>
							</tr>
							<%
								i++;
								j++;
							%>
						</s:iterator>
						<%
						m = i;
						%>



					</table>
					</td>
				</tr>



				<tr>
					<td width="15%" colspan="1"><label class="set" name="purpose"
						id="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label>:
					</td>

					<s:if test="isApprove">
						<td width="85%" colspan="2"><s:textarea cols="60" rows="2"
							name="confBooking.purpose" readonly="true" /></td>
					</s:if>
					<s:else>
						<td><s:textarea cols="60" rows="2" name="confBooking.purpose"
							onblur="return checkBlankSpace(this);"
							onkeyup="callLength('descCnt');" /> <img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id='ctrlHide' width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_confBooking_purpose','purpose','','500','500');">
						</td>
						<td id='ctrlHide'>Remaining chars<s:textfield name="descCnt"
							readonly="true" size="5"></s:textfield></td>
					</s:else>
				</tr>
				<s:if test="isApprove=='true' || isSentBack=='true'">
					<s:if test="appCommentFlag">
						<tr>
							<td colspan="4">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable">
										<tr>
											<td colspan="4" class="formhead"><strong
												class="forminnerhead">Comments By Approver</strong></td>
										</tr>
										<tr>
											<td class="formth" width="8%"><label class="set"
												name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
											<td class="formth" width="30%"><label class="set"
												name="Approver.Name" id="Approver.Name"
												ondblclick="callShowDiv(this);"><%=label.get("Approver.Name")%></label></td>
											<td class="formth" width="15%"><label class="set"
												name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
											<td class="formth" width="10%"><label class="set"
												name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
											<td class="formth" width="37%"><label class="set"
												name="comments" id="comments"
												ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
										</tr>

										<%
										int k = 1;
										%>
										<s:iterator value="commentList" status="stat">
											<tr>
												<s:hidden name="approverName" />
												<s:hidden name="approvedDate" />
												<s:hidden name="approvedStatus" />
												<s:hidden name="approverComment" />

												<td width="8%" class="border2" align="center"><%=k%></td>
												<td width="30%" class="border2"><s:property
													value="approverName" />&nbsp;</td>
												<td width="15%" class="border2" align="center"><s:property
													value="approvedDate" />&nbsp;</td>
												<td width="10%" class="border2"><s:property
													value="approvedStatus" />&nbsp;</td>
												<td width="37%" class="border2"><s:property
													value="approverComment" />&nbsp;</td>
											</tr>
											<%
											k++;
											%>
										</s:iterator>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</s:if>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>
callFun();
function callFun(){
	setText('paraFrm_confBooking_fromTime','HH24:MI');
	setText('paraFrm_confBooking_toTime','HH24:MI');
   }
///document.getElementById("paraFrm_confBooking_fromTime").onkeypress = CheckNumeric;
     var fieldName  = ["paraFrm_confBooking_requireDate", "paraFrm_confBooking_venue", "paraFrm_confBooking_fromTime", 
					  "paraFrm_confBooking_toTime", "paraFrm_confBooking_bookBy"];				  
     var lableName  = ["conference.date","conference.room","from.time", "to.time","booked.by"];
     var flag	   = ["enter", "select", "enter", "enter", "select"];
				  

function validate() {
	var rowCount = <%=m%>;
	var date       = document.getElementById("paraFrm_confBooking_requireDate").value;
	var fromTime   = document.getElementById("paraFrm_confBooking_fromTime").value;
	var toTime     = document.getElementById("paraFrm_confBooking_toTime").value;
	if(fromTime == 'HH24:MI'){
		clearText('paraFrm_confBooking_fromTime','HH24:MI');
	}
	if(toTime == 'HH24:MI'){
		clearText('paraFrm_confBooking_toTime','HH24:MI');
	}
	if(!(validateBlank(fieldName, lableName, flag)))
		return false;
	if(!validateDate("paraFrm_confBooking_requireDate", 'conference.date'))return false;
	if(!checkDatewithCurrentDate(date, fromTime, "conference.date"))return false;
	if(!validateTime("paraFrm_confBooking_fromTime", 'from.time'))return false;
	if(!validateTime("paraFrm_confBooking_toTime", 'to.time'))	return false;
	if(!timeDifference(fromTime, toTime, "paraFrm_confBooking_fromTime", "from.time", "to.time"))return false;
	for(var count=0;count<eval(rowCount);count++){
		idValue = document.getElementById('h'+count).checked;
		quantityValue = document.getElementById('t'+count).value;
		if(idValue && quantityValue==0){
			alert('Please enter required quantity.');
			document.getElementById('t'+count).focus();
			return false;
		}if(!idValue && quantityValue>0){
			alert('Please select required accessory.');
			document.getElementById('h'+count).focus();
			return false;
		}
	}
	 var val=document.getElementById('paraFrm_confBooking_purpose').value
       if(eval(val.length)>500) {
       		alert(document.getElementById('purpose').innerHTML.toLowerCase()+' accepts only 500 ' + 
		            ' characters. Please remove ' + (eval(val.length) - 500) + ' characters.');
		    return false;
       }
	 return true;
   }

function draftFun(){
	document.getElementById('paraFrm_hiddenStatus').value = document.getElementById('paraFrm_status').value;
	if(validate())
	{
	document.getElementById("paraFrm").action="ConferenceBooking_save.action";
	document.getElementById("paraFrm").submit();
	}
	else
	{
	return false;
	}
}

function sendforapprovalFun(){
	document.getElementById('paraFrm_hiddenStatus').value = 'P';
	if(document.getElementById('paraFrm_status').value == "B"){
		document.getElementById('paraFrm_hiddenStatus').value = 'B';
	}
	if(validate()){
	document.getElementById("paraFrm").action="ConferenceBooking_save.action";
	document.getElementById("paraFrm").submit();
	}
	else{
	return false;
	}
   }

function withdrawFun(){
	 document.getElementById('paraFrm_hiddenStatus').value = document.getElementById('paraFrm_status').value;
	 document.getElementById('paraFrm').target = "_self";
     document.getElementById('paraFrm').action = 'ConferenceBooking_withdrawApplication.action';
	 document.getElementById('paraFrm').submit();
   }

function backFun() {
	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('source').value=='mymessages'){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
	 }
	else if(document.getElementById('source').value=='myservices'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
	}
	else{
       document.getElementById('paraFrm').action = 'ConferenceBooking_cancel.action';
    }
	document.getElementById('paraFrm').submit();
   }

function resetFun() {
 		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action = "ConferenceBooking_reset.action";
		document.getElementById('paraFrm').submit();
	}

function editFun(){
	 return true;
	}

function deleteFun(){
	/*if(document.getElementById('paraFrm_status').value != 'P'){
		alert('You can not delete the record.');
		return false;
	}
	if(!callDelete('paraFrm_confBooking_confCode'))return false;*/
	var conf = confirm("Do you really want to delete this record?");
 	if(conf) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ConferenceBooking_delete.action';
		document.getElementById('paraFrm').submit();
	  }
	}

function reportFun() {
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = 'ConferenceBooking_report.action';
	document.getElementById('paraFrm').submit();
	}

function callLength(type){ 
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_confBooking_purpose').value;
					var remain = 500 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_confBooking_purpose').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_confBooking_purpose').style.background = '#FFFFFF';
				}
	}
	
function calNoOfParticipants(){
		var inPart = document.getElementById('paraFrm_minParticipant').value;
		var exPart = document.getElementById('paraFrm_maxParticipant').value;
		if(inPart!="" && exPart!="")
			document.getElementById('paraFrm_confBooking_noParticipant').value = eval(inPart) + eval(exPart);
		else if(inPart!="")
			document.getElementById('paraFrm_confBooking_noParticipant').value = eval(inPart) ;
		else if(exPart!="")
			document.getElementById('paraFrm_confBooking_noParticipant').value = eval(exPart);
		else
			document.getElementById('paraFrm_confBooking_noParticipant').value = "";
	}
	
function callAddKeepInfo(){
		try{
		  var keepInformCode = document.getElementById("paraFrm_informCode").value;
		  var keepInformedName = document.getElementById("paraFrm_informName").value;
		  if(keepInformedName==""){
		  	alert("Please select keep informed to");
		  	return false;
		  }
		  var tbl = document.getElementById('keepInformedTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow+" ) ";
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'keepInformToName';
		  column1.value = keepInformedName; /*value to be set in the added cell*/
		  column1.id = 'keepInformToName'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/common/css/icons/delete.gif";
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';
		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this);		  	
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);		  
		  var column3 = document.createElement('input');
		  column3.type = 'hidden';
  		  column3.name = 'keepInformToCode';
  		  column3.value = keepInformCode; /*value to be set in the added cell*/
		  column3.id = 'keepInformToCode'+iteration;
		  column3.maxLength ='2';
		  cell2.appendChild(column3);
		}catch(e){alert(e);}
		document.getElementById("paraFrm_informName").value="";	
	}
	
function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		
	}
	
function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
	
function chkCom(){
		var emailStr = document.getElementById('paraFrm_otherEmail').value;
		var emailLength = emailStr.length;
		var subStr =emailStr.substring(emailLength-3,emailLength);
		var subStr1 =emailStr.substring(emailLength-2,emailLength);
		
		if(subStr == "com" || subStr1 == "in"){
			document.getElementById('paraFrm_otherEmail').value=emailStr+',';
		}
	}

function cancelFun(){	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ConferenceBooking_cancelStatus.action';
		document.getElementById('paraFrm').submit();	
	}
	
	
function backtolistFun(){
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ConferenceBooking_backToList.action';
		document.getElementById('paraFrm').submit();	
}
</script>

