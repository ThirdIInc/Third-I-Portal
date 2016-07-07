<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script language="javascript"	src="../pages/admin/leave/mootools.v1.11.js"></script>
<script language="javascript"	src="../pages/admin/leave/nogray_time_picker_min.js"></script>
<script language="javascript"></script>

<s:form action="Regularization" method="post" name="Regularization"
	id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="dataBaseFromTime" />
		<s:hidden name="dataBaseToTime" />
		<s:hidden name="shiftStartTime" />
		<s:hidden name="shiftEndTime" />

		<input type="hidden" name="time2" id="time2" onchange="aaa()" />
		<s:hidden name="month_old" />
		<s:hidden name="year" />
		<s:hidden name="status" />
		<s:hidden name="shiftCode" />
		<s:hidden name="empGender" />
		<s:hidden name="policyCode" />
		<s:hidden name="applyFor" />
		<s:hidden name="condTrueFlag" />
		<s:hidden name="countValue" />
		<s:hidden name="redressalFlag" />
		<s:hidden name="applicationCode" />
		<s:hidden name="backActionName" />
		<s:hidden name="commentsFlag" />
		<s:hidden name="ptType" />
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>

					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Personal Time Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
						test="viewApplFlag">
						<s:if test="approverFlag">
							<s:submit cssClass="token"
								action="RegularizationApproval_%{approveActionName}"
								value="  Approve" onclick="return callApprover('approved')" />
							<s:submit cssClass="token"
								action="RegularizationApproval_%{rejectActionName}"
								value="  Reject" onclick="return callApprover('reject')" />
							<s:submit cssClass="token"
								action="RegularizationApproval_%{sendBackActionName}"
								value="  Send Back" onclick="return callApprover('send back')" />
							<s:submit cssClass="back" action="%{backActionName}"
								value="   Back" />
						</s:if>
						<s:else>
							<s:submit cssClass="back" action="%{backActionName}"
								value="   Back" />
						</s:else>

					</s:if> <s:else>
						<input type="button" class="token" id="addButton"
							value="  Send For Approval" onclick="return callApplySwipe()" />
						<s:submit cssClass="back" action="Regularization_input"
							value="   Back" />
					</s:else></td>

					<td width="22%" valign="middle">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Employee Details </strong></td>
				</tr>
				<tr>
					<td width="22%" height="22" class="formtext"><label
						class="set" name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
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
					<td height="22" class="formtext"><label class="set"
						name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="28%" height="22"><s:textfield name="empBranch"
						size="30" readonly="true" /></td>

					<td class="formtext" colspan="2" align="center"><label
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
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

					<td class="formtext" colspan="3">&nbsp;</td>


				</tr>
				<s:if test="swipeFlag">
					<tr>
						<td height="22" class="formtext" nowrap="nowrap"><label
							class="set" name="application.purpose" id="application.purpose"
							ondblclick="callShowDiv(this);"><%=label.get("application.purpose")%></label>
						:</td>
						<td class="formtext" colspan="4"><s:select disabled="true"
							name="applyForDecode" value="%{applyFor}"
							list="#{'1':'Forgot Flash card','2':'Forgotten to Flash Flash','3':'Special Sanction','4':'Flash Card Not Issued','5':'Swipe System Not Working On My System','6':'Swipe System Not Loading','7':'Swipe System Capturing Incorrect Data','8':'Swipe System - Forget To LOGIN / LOGOUT','9':'Swipe System - Forget To Bring Access Card','10':'Swipe System - Others','LR':'Late Regularization','RR':'Redressal'}">
						</s:select></td>
					</tr>
				</s:if>


			</table>
			</td>
		</tr>

		<%!int keep = 0;%>

		<tr>
			<td colspan="5">
			<table width="100%" cellpadding="1" border="0" cellspacing="1">
				<tr>
					<td colspan="2"><strong>Approver List </strong></td>
					<td width="18%"><strong>Keep Inform To </strong></td>
					<td width="21%" align="left"><s:hidden name="informCode" /> <s:hidden
						name="informToken" /> <s:if test="viewApplFlag">
					</s:if> <s:else> &nbsp;
 <s:textfield name="informName" size="30" readonly="true" />
					</s:else></td>
					<td width="10%" nowrap="nowrap"><s:if test="viewApplFlag">
					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif" width="16"
							height="15" class="iconImage"
							onclick="javascript:callsF9(500,325,'Regularization_f9informTo.action');" />
						<s:submit value="  Add" cssClass="add"
							action="Regularization_addInformListForRedressal"
							onclick="return callAddInfo();"></s:submit>
					</s:else></td>
				</tr>

				<tr>
					<td colspan="2" valign="top">
					<table width="100%" cellpadding="1" cellspacing="1">
						<%
						int ap = 0;
						%>
						<s:iterator value="approverList">
							<tr>
								<td width="20%" nowrap="nowrap"><s:property
									value="apprSrNo" /><s:hidden name="approverCode" /></td>
								<td><s:property value="approverName" /><s:hidden
									name="approverName" /></td>
							</tr>
							<%
							ap++;
							%>
						</s:iterator>
						<%
						keep = ap;
						%>
					</table>
					</td>
					<td width="18%" align="right" valign="top"></td>
					<td width="16%" colspan="2" valign="top"><s:hidden
						name="keepHidden" />
					<table width="100%" cellpadding="0" cellspacing="0">
						<%
						int kep = 0;
						%>
						<s:iterator value="informList">
							<tr>
								<td><%=kep + 1%> )</td>
								<s:hidden name="keepInformCode" />
								<td><s:property value="keepInform" /><s:hidden
									name="keepInform" /></td>
								<s:if test="regularization.viewApplFlag">
								</s:if>
								<s:else>
									<td align="right"><b> <a href="#"
										onclick="callRemoveKeep('<%=kep%>')">Remove</a> </b></td>
								</s:else>

							</tr>
							<%
							kep++;
							%>
						</s:iterator>
					</table>

					</td>
				</tr>

			</table>

			</td>
		</tr>
		<!--    CODING FOR PERSONAL TIME -->
		<s:if test="personalTimeFlag">
			<tr>
				<td colspan="5">
				<table width="100%" cellpadding="1" class="formbg" border="0">
					<tr>
						<td colspan="5"><b>Personal Time</b></td>
					</tr>

					<tr>
						<td colspan="1" width="10%"><label class="set" name="date"
							id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
						:<font color="red">*</font></td>
						<td colspan="1" width="35%" nowrap="nowrap"><s:hidden
							name="hiddenDate" /> <s:textfield name="ptDate" size="10"
							maxlength="15" onkeypress="return numbersWithHiphen();"
							onblur="getBlank();" /> <s:a href="#">
							<img class="iconImage" id="ctrlHide"
								src="../pages/images/recruitment/Date.gif" width="16"
								height="16" border="0" align="absmiddle"
								onclick="callDatePick();" />
						</s:a> <s:if test="viewApplFlag">
						</s:if> <s:else>
							<b> <a href="#" onclick="callAvailable();">Check
							Available time</a> </b>
						</s:else></td>
						<td colspan="1" nowrap="nowrap"><b>Available Personal
						Time</b></td>
						<td colspan="2"><s:textfield name="personalTimeHH_MI"
							size="15" readonly="true" /> <s:hidden name="personalTime" /> <s:hidden
							name="differencePT" /></td>
					</tr>
					<tr>
					<td colspan="1" width="18%"><label class="set"
						name="from.time" id="from.time1" ondblclick="callShowDiv(this);"><%=label.get("from.time")%></label>
					:<font color="red">*</font></td>
					<td colspan="1" width="35%"><s:textfield name="ptFromTime"
						size="15" maxlength="5" />(HH24:MI)</td>
					<td colspan="1" width="20%"><label class="set" name="to.time"
						id="to.time" ondblclick="callShowDiv(this);"><%=label.get("to.time")%></label>
					:<font color="red">*</font></td>
					<td colspan="2"><s:textfield name="ptToTime" size="15"
						maxlength="5" />(HH24:MI)</td>
					</tr>
					<s:if test="viewApplFlag">
					</s:if>
					<s:else>
						<tr>
							<td colspan="5" align="center"><s:hidden name="hiddenEdit" />
							<s:submit value="Add" cssClass="add"
								action="Regularization_addPersonalTime"
								onclick="return callPT();"></s:submit> <input type="button"
								value="Clear" class="delete" onclick="return callClear();">
							</td>
						</tr>
					</s:else>
					<!-- CODING FOR ADD ITTERATOR LIST -->
					<tr>
						<td colspan="5">
						<table width="100%" cellpadding="1" class="formbg" border="0">
							<tr>
								<td class="formth"><label class="set" name="sno" id="sno"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
								<td class="formth"><label class="set" name="date" id="date"
									ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
								<td class="formth"><label class="set" name="from.time"
									id="from.time2" ondblclick="callShowDiv(this);"><%=label.get("from.time")%></label></td>
								<td class="formth"><label class="set" name="to.time"
									id="to.time2" ondblclick="callShowDiv(this);"><%=label.get("to.time")%></label></td>

								<td class="formth">Total</td>
								<s:if test="viewApplFlag">
								</s:if>
								<s:else>
									<td class="formth">Edit | Remove</td>
								</s:else>
							</tr>
							<%
							int p = 0;
							%>
							<%!int pTot = 0;%>
							<s:iterator value="ptList">
								<tr>
									<td class="sortableTD" align="center"><%=p + 1%></td>
									<td class="sortableTD" align="center"><s:property
										value="ptDateItt" /><s:hidden name="ptDateItt"
										id="<%="ptDateItt"+p%>" /></td>
									<td class="sortableTD" align="center"><s:property
										value="ptFromTimeItt" /><s:hidden name="ptFromTimeItt"
										id="<%="ptFromTimeItt"+p%>" /></td>
									<td class="sortableTD" align="center"><s:property
										value="ptToTimeItt" /><s:hidden name="ptToTimeItt"
										id="<%="ptToTimeItt"+p%>" /></td>
									<td class="sortableTD" align="center"><s:textfield
										name="difference" readonly="true" size="5" /></td>
									<s:if test="regularization.viewApplFlag">
									</s:if>
									<s:else>
										<td class="sortableTD" align="center"><input
											type="button" class="rowEdit"
											onclick="callForEditPT('<%=p%>')" /> <input type="button"
											class="rowDelete" onclick="callDeletePT('<%=p%>')" /></td>
									</s:else>
								</tr>
								<%
								p++;
								%>
							</s:iterator>

							<%
							pTot = p;
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="1"></td>
				<td colspan="4"></td>
			</tr>
		</s:if>

		<%!int t = 0;%>
		<%!int tt = 0;%>
		<%!int redCount = 0;%>

		<s:if test="approverFlag">
			<tr>
				<td nowrap="nowrap" width="15%"><label class="set"
					name="reason" id="reason" ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:</td>

				<td colspan="3" nowrap="nowrap" valign="top"><s:textarea
					name="reason" cols="30" rows="4" disabled="true"></s:textarea> <label
					class="set" name="approver.comments" id="approver.comments"
					ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
				<s:textarea name="approverComents" cols="30" rows="4"></s:textarea>

				</td>
			</tr>
		</s:if>
		<s:else>

			<tr>
				<td nowrap="nowrap"><label class="set" name="reason"
					id="reason" ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:</td>
				<td colspan="4"><s:textarea name="reason" cols="90" rows="4"></s:textarea>
				</td>
			</tr>
		</s:else>

		<s:if test="commentsFlag">
			<tr>
				<td colspan="5"><b>Approver Comments:</b></td>
			</tr>
			<tr>
				<td colspan="5">
				<%
				int app = 0;
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" class="formth"><label class="set" name="sno"
							id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
						</td>
						<td colspan="2" class="formth"><label class="set"
							name="approver.name" id="approver.name"
							ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label>
						</td>
						<td colspan="2" class="formth">Approver Comments</td>
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
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="viewApplFlag">
						<s:if test="approverFlag">
							<s:submit cssClass="token"
								action="RegularizationApproval_%{approveActionName}"
								value="  Approve" onclick="return callApprover('approved')" />
							<s:submit cssClass="token"
								action="RegularizationApproval_%{rejectActionName}"
								value="  Reject" onclick="return callApprover('reject')" />
							<s:submit cssClass="token"
								action="RegularizationApproval_%{sendBackActionName}"
								value="  Send Back" onclick="return callApprover('send back')" />
							<s:submit cssClass="back" action="%{backActionName}"
								value="   Back" />
						</s:if>
						<s:else>
							<s:submit cssClass="back" action="%{backActionName}"
								value="   Back" />
						</s:else>
					</s:if> <s:else>
						<input type="button" class="token" id="addButton1"
							value="  Send For Approval" onclick="return callApplySwipe()" />
						<s:submit cssClass="back" action="Regularization_input"
							value="   Back" />
					</s:else></td>
					<td width="22%" valign="middle"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function callDatePick(){
  document.getElementById('paraFrm_dataBaseFromTime').value=document.getElementById('paraFrm_ptDate').value;
  NewCal('paraFrm_ptDate','DDMMYYYY');			
}

function callClear(){
	document.getElementById('paraFrm_personalTimeHH_MI').value='';
	document.getElementById('paraFrm_personalTime').value='';
	document.getElementById('paraFrm_ptDate').value='';
	document.getElementById('paraFrm_ptFromTime').value='';	
	document.getElementById('paraFrm_ptToTime').value='';	
	document.getElementById('paraFrm_hiddenEdit').value='';
}

function getBlank(){
	document.getElementById('paraFrm_personalTimeHH_MI').value='';
	document.getElementById('paraFrm_personalTime').value='';
}

function callAvailable(){		
	var frmDate1=document.getElementById('paraFrm_ptDate').value;
	if(frmDate1==''){
		alert('Please enter date ');
		return false;			
	}else{
	   document.getElementById("paraFrm").action="Regularization_getValidDate.action";	  
	   document.getElementById("paraFrm").submit();
	}
}

function callRemoveKeep(id){
	var r=confirm("Are you sure to remove this record?");
	if(r==false){
		return false;
	}else{
	 	document.getElementById('paraFrm_keepHidden').value=id;
	   	document.getElementById("paraFrm").action="Regularization_removeKeep.action";	  
	    document.getElementById("paraFrm").submit();
	}
}

function callForEditPT(id){
   	document.getElementById('paraFrm_personalTimeHH_MI').value='';
	document.getElementById('paraFrm_personalTime').value='';
   	document.getElementById('paraFrm_ptDate').value=document.getElementById('ptDateItt'+id).value;
   	document.getElementById('paraFrm_ptFromTime').value=document.getElementById('ptFromTimeItt'+id).value;
  	document.getElementById('paraFrm_ptToTime').value=document.getElementById('ptToTimeItt'+id).value;	   	
   	document.getElementById('paraFrm_hiddenEdit').value=id;	 
   	//document.getElementById('paraFrm_hiddenDate').value=document.getElementById('ptDateItt'+id).value;
    //document.getElementById("paraFrm").action="Regularization_editPersonalTime.action";	   
	// document.getElementById("paraFrm").submit();
}

function callDeletePT(id){
	var r=confirm("Are you sure to remove this record?");
	if(r==false){
		return false;
	}else{
	  	document.getElementById("paraFrm").action="Regularization_removePersonalTime.action";
	   	document.getElementById('paraFrm_hiddenEdit').value=id;
	    document.getElementById("paraFrm").submit();
	}
}
			
function ReplaceAll(Source,stringToFind,stringToReplace){
	var temp = Source;
	var index = temp.indexOf(stringToFind);
	while(index != -1){
	   temp = temp.replace(stringToFind,stringToReplace);
	   index = temp.indexOf(stringToFind);        
	}
	return temp;
}
			
function TimeDiff(fromTime,toTime){
	var temp ;
	fieldList1 = fromTime.split(":");
	fieldList2 = toTime.split(":");
			 
	var mm=eval(fieldList2[1])-eval(fieldList1[1]);		
	var hh=eval(fieldList2[0])-eval(fieldList1[0]);			
	temp=eval(mm)+eval(hh*60);
	return eval(temp);
}
			
function callPT(){			
	var reporting='<%=keep%> ';				
	if(eval(reporting)=='0'){
		alert("Reporting structure not define ");
		return false;
	}
	var prvDate=document.getElementById('paraFrm_dataBaseFromTime').value;
	var frmDate1=document.getElementById('paraFrm_ptDate').value;
	if(prvDate==''){			
	}else if(ReplaceAll(prvDate,'-','') == ReplaceAll(frmDate1,'-','')){			
	}else{
		document.getElementById('paraFrm_personalTimeHH_MI').value='';
		document.getElementById('paraFrm_dataBaseFromTime').value='';
	}
			
	if(frmDate1==''){
		alert('Please enter date ');
		return false;			
	}
	fieldList = frmDate1.split("-");
	//alert(fieldList[1]);
	//alert(fieldList[2]);
	var mm=document.getElementById('paraFrm_month_old').value;
	var yy=document.getElementById('paraFrm_year').value;
	if(document.getElementById('paraFrm_applicationCode').value ==''){
		if(mm !=eval(fieldList[1])){			
	 		alert('applied month & selected month must be same ');
			return false;
		}
		if(yy !=eval(fieldList[2])){			
			alert('applied year & selected year most be same ');
			return false;
		}
	}
			
	if(document.getElementById('paraFrm_personalTimeHH_MI').value==''){
		alert('Please first check available personal time ');
		return false;			
	}
			
	if(document.getElementById('paraFrm_ptFromTime').value==''){
		alert('Please enter from time ');
		return false;			
	}					
	
	if(IsValidTime(document.getElementById('paraFrm_ptFromTime').value)){			
	}else{
		document.getElementById('paraFrm_ptFromTime').focus();
		return false;
	}	
	
	if(document.getElementById('paraFrm_ptToTime').value==''){
		alert('Please enter to time ');
		return false;			
	}			
	
	if(IsValidTime(document.getElementById('paraFrm_ptToTime').value)){			
	}else{
		document.getElementById('paraFrm_ptToTime').focus();
		return false;
	}	
	
	var dd=document.getElementById('paraFrm_ptDate').value;
	var frmTime=document.getElementById('paraFrm_ptFromTime').value;
	var toTime=document.getElementById('paraFrm_ptToTime').value;
	frmTime=ReplaceAll(frmTime,':','');
	toTime=ReplaceAll(toTime,':','');		
	//alert(parseInt(frmTime));
	//alert(parseInt(toTime));
	if(eval(frmTime)>=eval(toTime)){
		alert('To time must be greater than from time');
		return false
	}
			//CHECK FOR ITTERATOR RECORD
	var total='<%=pTot%>';
	var editCount=document.getElementById('paraFrm_hiddenEdit').value;	
	if(editCount==''){
			editCount=100;
	}
			
	for(var i=0; i<total;i++){			
		var dateItt=document.getElementById('ptDateItt'+i).value;			
		var frmTimeItt=document.getElementById('ptFromTimeItt'+i).value;
		var toTimeItt=document.getElementById('ptToTimeItt'+i).value;			
		frmTimeItt=ReplaceAll(frmTimeItt,':','');
		toTimeItt=ReplaceAll(toTimeItt,':','');			
		var abc=i;
		if(editCount !=abc){
			if(dateItt==dd && parseInt(frmTime)>parseInt(frmTimeItt) && parseInt(frmTime)<parseInt(toTimeItt)){
					alert('You already apply for this slab');
					return false;
			}
					
			if(dateItt==dd && parseInt(toTime)>parseInt(frmTimeItt)  && parseInt(toTime)<parseInt(toTimeItt) ){
					alert('You already apply for this slab');
					return false;
			}
					
			if(dateItt==dd && parseInt(frmTime)<parseInt(frmTimeItt)  && parseInt(toTime)>parseInt(toTimeItt) ){
					alert('You already apply for this slab');
					return false;
			}
					
			if(dateItt==dd && parseInt(frmTime)==parseInt(frmTimeItt)  && parseInt(toTime)==parseInt(toTimeItt) ){
					alert('You already apply for this slab');
					return false;
			}
		}
	}
	
	//check available balance 
	var avl=document.getElementById('paraFrm_differencePT').value;
	var difff=TimeDiff(document.getElementById('paraFrm_ptFromTime').value,document.getElementById('paraFrm_ptToTime').value);
	if(eval(difff)>eval(avl)){
			alert("Your available balance is "+document.getElementById('paraFrm_personalTimeHH_MI').value+"(HH24:MI) ");
			return false;			
	}
}	


function IsValidTime(id) {		
	var timeStr=id;//document.getElementById('fromTime'+id).value;
	var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
	var matchArray = timeStr.match(timePat);
	if (matchArray == null) {
		alert("Please enter time in a valid format(HH24:MI)");
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


function callApprover(status){
	var con=confirm('Do you want to '+status+' this application ?');
	if(con){
		return true;
	}else{
		return false;
	}
}
			

function callApplySwipe(){								
	var reporting='<%=keep%> ';	
	var total=<%=pTot %>;	
		
	if(eval(reporting)=='0'){
		alert("Reporting structure not define ");
		return false;
	}					
	if(eval(total)=='0'){
		alert("You can not send this application,please add one or more record ");
		return false;
	}
	
	var con=confirm('Do you want to send this application for approval');
	if(con){				
		document.getElementById('paraFrm').action="Regularization_applyPersonalTimeApplication.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
		document.getElementById('addButton').disabled=true;	
		document.getElementById('addButton1').disabled=true;			
	}else{
		return false;
	}	
}


function callAddInfo(){
	var info=document.getElementById('paraFrm_informName').value;
	if(info==''){
		alert('Please select keep inform to');
		return false;
	}				
}

function callDelete(){
	var con=confirm('Do you want to remove !');
	if(con){
		return true;
	}else{
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

function validateTimeLocal(name, labName){
	var time = document.getElementById(name).value;
	if(time=="")return true;
	var timeExp 	= /^[0-9]{2}[:]?[0-9]{2}$/
	var timeArray 	= time.split(":");
	var hour	    = timeArray[0];
	var min         = timeArray[1];
	if(!(time.match(timeExp)) || time.length<5){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in 24Hours HH:MI format");
		document.getElementById(name).focus();
		return false;
	}
	
	if(hour>23){
		alert("Hour "+hour+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	
	if(min>59){
		alert("minuite "+min+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	return true ;
}
		
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>