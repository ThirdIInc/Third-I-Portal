<!-- Added by manish sakpal  created on 16th March 2011  -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TerminationApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	<s:hidden name="listType" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />

	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Termination Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span> <font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	
		<!-- Approver Comments Section Begins -->
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr id="pendingAndCancelationApplicationID">
						<td colspan="2"><b>Approver Comments</b></td>
						<td colspan="3"><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="ctrlShow" /></td>
					</tr>
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
							<td class="sortableTD" align="center"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
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
		<!-- Approver Comments Section Ends -->
		
		<!-- Employee Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4"><b>Employee Information</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"> <%=label.get("employee")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3">
						<s:hidden name="employeeNumber" /> 
						<s:textfield name="employeeToken" size="25" readonly="true" /> 
						<s:textfield name="employeeName" size="73" readonly="true" /> 
					</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="homeAddress"
						id="homeAddress" ondblclick="callShowDiv(this);"> <%=label.get("homeAddress")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textarea name="homeAddress"
						cols="27" rows="4" readonly="true" /> 
					</td>
					<td class="formtext" width="15%"><label name="zipCode"
						id="zipCode" ondblclick="callShowDiv(this);"> <%=label.get("zipCode")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="zipCode"
						size="25" readonly="true" /></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="city" id="city"
						ondblclick="callShowDiv(this);"> <%=label.get("city")%> </label><font
						color="red">*</font> :</td>
					<td  width="25%"><s:hidden name="cityID" /> <s:textfield
						name="cityName" size="25" readonly="true" /> 
					</td>
					<td class="formtext" width="15%"><label name="state"
						id="state" ondblclick="callShowDiv(this);"> <%=label.get("state")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="state"
						size="25" readonly="true" /></td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%"><label name="depetNumber"
						id="depetNumber" ondblclick="callShowDiv(this);"> <%=label.get("depetNumber")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:hidden name="deptID" /> <s:textfield
						name="depetNumber" size="25" readonly="true"/> 
					</td>
					<td class="formtext" width="15%"><label name="executive"
						id="executive" ondblclick="callShowDiv(this);"> <%=label.get("executive")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:hidden name="executiveID" /> <s:textfield
						name="executiveName" size="25" readonly="true"/> 
					</td>
				</tr>
				
				<tr>
					
					<td width="15%">Status : </td>
					<td  width="25%">
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:160" list="#{'P':'Pending','A':'Approved','R':'Rejected'}"></s:select>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Employee Information Section Ends -->
	<!-- Manager Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg" border="0">
				<tr>
					<td colspan="4" ><b>Manager Information</b></td>
				</tr>
				
				
				<tr>
					<td width="18%"><label id="manager"
							name="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
						:<font color="red" id='ctrlHide'></font></td>
						<td colspan="3"><s:textfield name="managerToken" size="15"/> <s:textfield
							name="managerName" size="73" theme="simple"
							readonly="true" cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="managerCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'Termination_f9Approver.action');">
						</td>
					
						
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Manager Information Section Ends -->

		<!-- Termination Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4"><b>Termination Information</b></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="terminationDate"
						id="terminationDate" ondblclick="callShowDiv(this);"> <%=label.get("terminationDate")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%">
						<s:textfield name="terminationDate" size="25"/> 
					</td>
					<td class="formtext" width="15%">
						<label name="lastDayWorkDate" id="lastDayWorkDate" ondblclick="callShowDiv(this);"> <%=label.get("lastDayWorkDate")%>
						</label><font color="red">*</font> :
					</td>
					<td width="25%">
						<s:textfield name="lastDayWorkDate" size="25" onkeypress="return numbersWithHiphen();" /> 
					</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="ifTerDateAndLastDayWorkDateDiffer"
						id="ifTerDateAndLastDayWorkDateDiffer"
						ondblclick="callShowDiv(this);"> <%=label.get("ifTerDateAndLastDayWorkDateDiffer")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textarea
						name="ifTerDateAndLastDayWorkDateDiffer" cols="27" rows="4" /> 
					</td>
					
					<td class="formtext" width="15%" id="ifOtherReasonLabel">
						<label name="ifOtherTerminationReason" id="ifOtherTerminationReason" ondblclick="callShowDiv(this);"> <%=label.get("ifOtherTerminationReason")%></label> :
					</td>
					<td  width="25%" id="ifOtherReasonTextArea">
						<s:textarea name="ifOtherTerminationReasonTextArea" cols="27" rows="4" /> 
					</td>		
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="terminationType"
						id="terminationType" ondblclick="callShowDiv(this);"> <%=label.get("terminationType")%>
					</label><font color="red">*</font> :</td>
					<td width="25%"><s:radio name="terminationType"
						value="%{terminationType}" list="#{'vo':'Voluntary'}">
					</s:radio>&nbsp;&nbsp; <s:radio name="terminationType"
						value="%{terminationType}" list="#{'ivo':'Involuntary'}">
					</s:radio>&nbsp;&nbsp; <s:radio name="terminationType"
						value="%{terminationType}" list="#{'ot':'Other'}">
					</s:radio></td>
				</tr>
				
				<tr id="voluntaryID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3"><s:select headerKey="" disabled="true" id="ctrlShow"
						headerValue="-- Please Select Reason --" name="voluntaryTerminationReason"
						list="#{'BUA':'Other Employment','DWB':'Dissatisfied with Benefits','DWH':'Dissatisfied with Hours','DWT':'Dissatisfied with Training','FAM':'Family Reasons','HEA':'Health Reasons','JOB':'Job Abandonment','LOC':'Dissatisfied with Location','LVE':'Failure to Return From Leave','MIL':'Military','MOV':'Moved(Provide New Add. for W2 Mailing)','NAC':'Did Not Accept Offer','PAY':'Dissatisfied with Pay','PER':'Personal Reasons','POL':'Dissatisfied with Company Policy','PRM':'Dissatisfied with Promotion Opportunity','RET':'Retired','SCH':'Return to School','SEM':'Self Employment','STB':'Dissatisfied w/co Stability','SUP':'Dissatisfied with Manager','TYP':'Dissatisfied with Type of Work','UNK':'Unknown','WOR':'Dissatisfied with Work Conditions'}"
						cssStyle="width:220" /></td>
				</tr>	
				
				<tr id="inVoluntaryID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3" ><s:select headerKey="" disabled="true" id="ctrlShow"
						headerValue="-- Please Select Reason --" name="inVoluntaryTerminationReason"
						list="#{'ATT':'Attendance/Tardiness','COI':'Conflict of Interest','CON':'Misconduct/Violation of Company Rules','CRR':'Customer-Related Reduction','ELI':'Position Eliminated','INS':'Insubordination','LRF':'STD Return with Job Filled','RED':'Staff Reduction','SOC':'Sale of Company/Unit','UNS':'Unsatisfactory Performance'}"
						cssStyle="width:220" /></td>
				</tr>
				
				<tr id="otherID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3" ><s:select headerKey=""  disabled="true" id="ctrlShow"
						headerValue="-- Please Select Reason --" name="otherTerminationReason" 
						list="#{'DEA':'Death','FOC':'Failed Offer Letter Contingency','FOT':'Failed Offer Letter Training','FRP':'Failed Requirements of Position','OUD':'Srvcs Outsourced - Declined Offer','OUT':'Srvcs Outsourced - Accepted Offer','PTD':'Partial/Total Disability','SCL':'Return to School-End User Only','TMP':'End of Temporary Employment','USA':'Unauthorized to Work in USA','OTHR':'Other'}"
						cssStyle="width:220" /></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="eligibleToRehire" id="eligibleToRehire"
						ondblclick="callShowDiv(this);"> <%=label.get("eligibleToRehire")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:select name="eligibleToRehire" disabled="true" id="ctrlShow"
						headerKey="" headerValue="---- Please Select -----"
						list="#{'yes':'Yes','no':'No','pro':'Provisional'}"
						cssStyle="width:160"/></td>
					<td class="formtext" width="15%"><label
						name="ifNoOrProvisional" id="ifNoOrProvisional"
						ondblclick="callShowDiv(this);"> <%=label.get("ifNoOrProvisional")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textarea
						name="ifNoOrProvisional" cols="27" rows="4" /> 
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%"><label
						name="vacationHrsTaken" id="vacationHrsTaken"
						ondblclick="callShowDiv(this);"> <%=label.get("vacationHrsTaken")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield
						name="vacationHrsTaken" size="25" maxlength="10"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Termination Section Ends -->

		<!-- Comments Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" width="18%"><label name="commentsEntered"
						id="commentsEntered" ondblclick="callShowDiv(this);"> <%=label.get("commentsEntered")%>
					</label> :</td>
					<td colspan="3"><s:textarea name="commentsEntered" cols="108" rows="3" /> 
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Comments Section Ends -->
		
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" width="15%"><b><label name="completedBy"
						id="completedBy" ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
					</label></b> :</td>
					<td  width="25%"><s:hidden name="completedByID" />
					<s:property value="completedByName"/>
					<s:hidden name="completedByName" /></td>
					<td class="formtext" width="15%"><b><label name="completedDate"
						id="completedDate" ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
					</label></b> :</td>
					<td  width="25%">
						<s:property value="completedDate"/>
						<s:hidden name="completedDate"/></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	<s:hidden name="terminationID" />
	<s:hidden name="status" />
	<input type="hidden" name="terminationTypeRadioOptionValue"
		id="terminationTypeRadioOptionValue"
		value='<s:property value="terminationTypeRadioOptionValue"/>' />
	<s:hidden name="trackingNumber"/>	
</s:form>

<script>
 getDataOnLoad();
 function getDataOnLoad()
 {	
 	var statusVar = document.getElementById('paraFrm_status').value;
 	if(statusVar=="P" || statusVar=="C"){
 		document.getElementById('pendingAndCancelationApplicationID').style.display = '';
 	}else {
 		document.getElementById('pendingAndCancelationApplicationID').style.display = 'none';
 	}
 	
 	var terminationRadioValue = document.getElementById('terminationTypeRadioOptionValue').value;
 	if(terminationRadioValue=='vo')
		{
			document.getElementById('voluntaryID').style.display = '';
			document.getElementById('inVoluntaryID').style.display = 'none';
			document.getElementById('otherID').style.display = 'none';
			
		}else if(terminationRadioValue=='ivo') {
			document.getElementById('inVoluntaryID').style.display = '';
			document.getElementById('voluntaryID').style.display = 'none';
			document.getElementById('otherID').style.display = 'none';
			
		}else if(terminationRadioValue=='ot')
		{
			document.getElementById('otherID').style.display = '';
			document.getElementById('voluntaryID').style.display = 'none';
			document.getElementById('inVoluntaryID').style.display = 'none';
			
		}
		
	var reason = document.getElementById('otherTerminationReason').value;
 		if(reason == "OTHR"){
 			document.getElementById('ifOtherReasonLabel').style.display = '';
 			document.getElementById('ifOtherReasonTextArea').style.display = '';
 		}else {
 			document.getElementById('ifOtherReasonLabel').style.display = 'none';
 			document.getElementById('ifOtherReasonTextArea').style.display = 'none';
 		}
 }


function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		if(document.getElementById('paraFrm_status').value == 'P')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}
		
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'X';
		}
			
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'TerminationApproval_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
		if(document.getElementById('paraFrm_status').value == 'C')
		{
			document.getElementById('paraFrm_status').value = 'Z';
		}else {
			document.getElementById('paraFrm_status').value = 'R';	
		}
		
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'TerminationApproval_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm_status').value = 'B';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'TerminationApproval_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}


function backtolistFun()
{
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='TerminationApproval_backFunction.action';
	document.getElementById('paraFrm').submit();
}
	function printFun() {	
	window.print();
	}
	function closeFun() {
					window.close();
			}
</script>
