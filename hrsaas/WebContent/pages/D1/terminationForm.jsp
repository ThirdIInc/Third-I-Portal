<!-- Added by manish sakpal  created on 16th March 2011  -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="Termination" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="listType" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="myPageSentBack" id="myPageSentBack" />
	
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />
	
	<s:hidden name="myPageCancel" id="myPageCancel" />
	
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Termination
					Form</strong></td>
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
		<s:if test="approverCommentFlag">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Date</td>
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
		  </s:if>
		<!-- Approver Comments Section Ends -->
		
		
		<!-- Employee Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4" ><b>Employee Information</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"> <%=label.get("employee")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3"><s:hidden name="employeeNumber" /> <s:textfield
						name="employeeToken" size="25" readonly="true" /> <s:textfield
						name="employeeName" size="73" readonly="true" /> 
						<s:if test="%{status=='_D' || status=='_B'}">
						</s:if>
						<s:else>
							<img id='ctrlHide' src="../pages/images/recruitment/search2.gif"
								height="16" align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'Termination_f9Employee.action');">
						</s:else>
					</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="homeAddress"
						id="homeAddress" ondblclick="callShowDiv(this);"> <%=label.get("homeAddress")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textarea name="homeAddress"
						cols="27" rows="4" onkeypress="return imposeMaxLength(event, this, 1000);"/> 
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_homeAddress','homeAddress','', '', '1000','1000');">
					</td>
					<td class="formtext" width="15%"><label name="zipCode"
						id="zipCode" ondblclick="callShowDiv(this);"> <%=label.get("zipCode")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="zipCode" size="25" maxlength="15" /></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="city" id="city"
						ondblclick="callShowDiv(this);"> <%=label.get("city")%> </label><font
						color="red">*</font> :</td>
					<td  width="25%"><s:hidden name="cityID" /> <s:textfield
						name="cityName" size="25" maxlength="50"/> 
					</td>
					<td class="formtext" width="15%"><label name="state"
						id="state" ondblclick="callShowDiv(this);"> <%=label.get("state")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="state" maxlength="50"
						size="25" /></td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%"><label name="depetNumber"
						id="depetNumber" ondblclick="callShowDiv(this);"> <%=label.get("depetNumber")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%">
						<s:hidden name="deptID"/>
						<s:textfield name="depetNumber" readonly="true" size="25" maxlength="40"/>
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'Termination_f9DepartmentNum.action');">
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
						<s:select id="ctrlShow" name="applicationStatus" disabled="true" cssStyle="width:135" list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected'}"></s:select>
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
						<td colspan="3"><s:textfield name="managerToken" size="15" readonly="true" cssStyle="background-color: #F2F2F2;" /> <s:textfield
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
					<td  width="25%"><s:textfield
						name="terminationDate" size="25"
						onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_terminationDate','DDMMYYYY');">
					<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage"
						height="16" align="absmiddle" width="16"> </a></td>
					<td class="formtext" width="15%"><label name="lastDayWorkDate"
						id="lastDayWorkDate" ondblclick="callShowDiv(this);"> <%=label.get("lastDayWorkDate")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield
						name="lastDayWorkDate" size="25"
						onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_lastDayWorkDate','DDMMYYYY');">
					<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage"
						height="16" align="absmiddle" width="16"> </a></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="ifTerDateAndLastDayWorkDateDiffer"
						id="ifTerDateAndLastDayWorkDateDiffer"
						ondblclick="callShowDiv(this);"> <%=label.get("ifTerDateAndLastDayWorkDateDiffer")%>
					</label> :</td>
					<td  width="25%">
						<s:textarea	name="ifTerDateAndLastDayWorkDateDiffer" cols="27" rows="4" onkeypress="return imposeMaxLength(event, this, 1000);"/> 
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_ifTerDateAndLastDayWorkDateDiffer','ifTerDateAndLastDayWorkDateDiffer','', '', '1000','1000');">
					</td>
					
					<td class="formtext" width="15%">&nbsp;</td>
					<td  width="25%">&nbsp;</td>		
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="terminationType"
						id="terminationType" ondblclick="callShowDiv(this);"> <%=label.get("terminationType")%>
					</label><font color="red">*</font> :</td>
					<td width="25%"><s:radio name="terminationType"
						value="%{terminationType}" list="#{'vo':'Voluntary'}"
						onclick="setTerminationType(this);">
					</s:radio>&nbsp;&nbsp; <s:radio name="terminationType"
						value="%{terminationType}" list="#{'ivo':'Involuntary'}"
						onclick="setTerminationType(this);">
					</s:radio>&nbsp;&nbsp; <s:radio name="terminationType"
						value="%{terminationType}" list="#{'ot':'Other'}"
						onclick="setTerminationType(this);">
					</s:radio></td>
				</tr>
				
				<tr id="voluntaryID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3"><s:select headerKey=""
						headerValue="-- Please Select Reason --" name="voluntaryTerminationReason"
						list="#{'BUA':'Other Employment','DWB':'Dissatisfied with Benefits','DWH':'Dissatisfied with Hours','DWT':'Dissatisfied with Training','FAM':'Family Reasons','HEA':'Health Reasons','JOB':'Job Abandonment','LOC':'Dissatisfied with Location','LVE':'Failure to Return From Leave','MIL':'Military','MOV':'Moved(Provide New Add. for W2 Mailing)','NAC':'Did Not Accept Offer','PAY':'Dissatisfied with Pay','PER':'Personal Reasons','POL':'Dissatisfied with Company Policy','PRM':'Dissatisfied with Promotion Opportunity','RET':'Retired','SCH':'Return to School','SEM':'Self Employment','STB':'Dissatisfied w/co Stability','SUP':'Dissatisfied with Manager','TYP':'Dissatisfied with Type of Work','UNK':'Unknown','WOR':'Dissatisfied with Work Conditions'}"
						cssStyle="width:220" /></td>
				</tr>	
				
				<tr id="inVoluntaryID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td colspan="3" ><s:select headerKey=""
						headerValue="-- Please Select Reason --" name="inVoluntaryTerminationReason"
						list="#{'ATT':'Attendance/Tardiness','COI':'Conflict of Interest','CON':'Misconduct/Violation of Company Rules','CRR':'Customer-Related Reduction','ELI':'Position Eliminated','INS':'Insubordination','LRF':'STD Return with Job Filled','RED':'Staff Reduction','SOC':'Sale of Company/Unit','UNS':'Unsatisfactory Performance'}"
						cssStyle="width:220" /></td>
				</tr>
				
				<tr id="otherID">
					<td class="formtext" width="15%"><label
						name="terminationReason" id="terminationReason"
						ondblclick="callShowDiv(this);"> <%=label.get("terminationReason")%>
					</label><font color="red">*</font> :</td>
					<td width="25%" ><s:select headerKey=""  
						headerValue="-- Please Select Reason --" name="otherTerminationReason" onchange="setOtherReason();"
						list="#{'DEA':'Death','FOC':'Failed Offer Letter Contingency','FOT':'Failed Offer Letter Training','FRP':'Failed Requirements of Position','OUD':'Srvcs Outsourced - Declined Offer','OUT':'Srvcs Outsourced - Accepted Offer','PTD':'Partial/Total Disability','SCL':'Return to School-End User Only','TMP':'End of Temporary Employment','USA':'Unauthorized to Work in USA','OTHR':'Other'}"
						cssStyle="width:220" /></td>
					
					<td class="formtext" width="15%" id="ifOtherReasonLabel">
						<label name="ifOtherTerminationReason" id="ifOtherTerminationReason" ondblclick="callShowDiv(this);"> <%=label.get("ifOtherTerminationReason")%></label> :
					</td>
					<td  width="25%" id="ifOtherReasonTextArea">
						<s:textarea name="ifOtherTerminationReasonTextArea" cols="27" rows="4" onkeypress="return imposeMaxLength(event, this, 1000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_ifOtherTerminationReasonTextArea','ifOtherTerminationReasonTextArea','', '', '1000','1000');"> 
					</td>		
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="eligibleToRehire" id="eligibleToRehire"
						ondblclick="callShowDiv(this);"> <%=label.get("eligibleToRehire")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:select name="eligibleToRehire"
						headerKey="" headerValue="---- Please Select -----"
						list="#{'yes':'Yes','no':'No','pro':'Provisional'}"
						cssStyle="width:160" onchange="rehireFunction();" /></td>
					<td class="formtext" width="15%"><label
						name="ifNoOrProvisional" id="ifNoOrProvisional"
						ondblclick="callShowDiv(this);"> <%=label.get("ifNoOrProvisional")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textarea
						name="ifNoOrProvisional" cols="27" rows="4" onkeypress="return imposeMaxLength(event, this, 1000);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_ifNoOrProvisional','ifNoOrProvisional','', '', '1000','1000');"> 
					</td>
				</tr>
				
				<!--				
				<tr>
					<td class="formtext" width="15%"><label name="managerName"
						id="managerName" ondblclick="callShowDiv(this);"> <%=label.get("managerName")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="managerName"
						size="25" /></td>
					<td class="formtext" width="15%"><label name="managerPhone"
						id="managerPhone" ondblclick="callShowDiv(this);"> <%=label.get("managerPhone")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield name="managerPhone"
						size="25" maxlength="20" onkeypress="return numbersWithHiphen();"/></td>
				</tr>
				-->
				
				<tr>
					<td class="formtext" width="15%"><label
						name="vacationHrsTaken" id="vacationHrsTaken"
						ondblclick="callShowDiv(this);"> <%=label.get("vacationHrsTaken")%>
					</label><font color="red">*</font> :</td>
					<td  width="25%"><s:textfield
						name="vacationHrsTaken" size="25" maxlength="10"
						onkeypress="return numbersWithColon();" 
						onfocus="clearText('paraFrm_vacationHrsTaken','(HH:MI)')"
						onblur="setText('paraFrm_vacationHrsTaken','(HH:MI)')" 
						/></td>
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
					<td colspan="3">
						<s:textarea name="commentsEntered" cols="108" rows="3" onkeypress="return imposeMaxLength(event, this, 1000);"/>
						 <img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_commentsEntered','commentsEntered','', '', '1000','1000');">
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
 	setText('paraFrm_vacationHrsTaken','(HH:MI)')
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
			
		}else {
			document.getElementById('voluntaryID').style.display = 'none';
			document.getElementById('inVoluntaryID').style.display = 'none';
			document.getElementById('otherID').style.display = 'none';		
		}
 	
 	var getRehireValue = document.getElementById('paraFrm_eligibleToRehire').value;
 	if(getRehireValue=="" || getRehireValue=="yes"){
	document.getElementById('paraFrm_ifNoOrProvisional').readOnly='true';	
	document.getElementById('paraFrm_ifNoOrProvisional').style.background='#F2F2F2'; 
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

function rehireFunction()
{
	var getRehireValue = document.getElementById('paraFrm_eligibleToRehire').value;
	
	if(getRehireValue=="yes" || getRehireValue==""){
	document.getElementById('paraFrm_ifNoOrProvisional').readOnly='true';	
	document.getElementById('paraFrm_ifNoOrProvisional').style.background='#F2F2F2';
	document.getElementById('paraFrm_ifNoOrProvisional').value = ''; 
	}else{
		document.getElementById('paraFrm_ifNoOrProvisional').readOnly='';
		document.getElementById('paraFrm_ifNoOrProvisional').style.background='white'; 	
	}
}

function setTerminationType(id){
	var opt = document.getElementById('terminationTypeRadioOptionValue').value =id.value;	
	var terminationRadioValue = document.getElementById('terminationTypeRadioOptionValue').value;
	
		if(terminationRadioValue=='vo')
		{
			document.getElementById('voluntaryID').style.display = '';
			document.getElementById('inVoluntaryID').style.display = 'none';
			document.getElementById('paraFrm_inVoluntaryTerminationReason').value = '';
			document.getElementById('otherID').style.display = 'none';
			document.getElementById('paraFrm_otherTerminationReason').value = '';
			
			document.getElementById('ifOtherReasonLabel').style.display = 'none';
 			document.getElementById('ifOtherReasonTextArea').style.display = 'none';
 			document.getElementById('paraFrm_ifOtherTerminationReasonTextArea').value = '';
 			
		}else if(terminationRadioValue=='ivo') {
			document.getElementById('inVoluntaryID').style.display = '';
			document.getElementById('voluntaryID').style.display = 'none';
			document.getElementById('paraFrm_voluntaryTerminationReason').value = '';
			document.getElementById('otherID').style.display = 'none';
			document.getElementById('paraFrm_otherTerminationReason').value = '';
			
			document.getElementById('ifOtherReasonLabel').style.display = 'none';
 			document.getElementById('ifOtherReasonTextArea').style.display = 'none';
 			document.getElementById('paraFrm_ifOtherTerminationReasonTextArea').value = '';
		}else 
		{
			document.getElementById('otherID').style.display = '';
			document.getElementById('voluntaryID').style.display = 'none';
			document.getElementById('paraFrm_voluntaryTerminationReason').value = '';
			document.getElementById('inVoluntaryID').style.display = 'none';
			document.getElementById('paraFrm_inVoluntaryTerminationReason').value = '';
		}
	}

function draftFun()
{
		try {
			var employeeVar = document.getElementById('paraFrm_employeeNumber').value;
			if(employeeVar=="")	{
				alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
				document.getElementById('paraFrm_employeeName').focus();
				return false;
			}
			
			
			var homeAddressVar = trim(document.getElementById('paraFrm_homeAddress').value);
			if(homeAddressVar=="")	{
				alert("Please enter "+document.getElementById('homeAddress').innerHTML.toLowerCase());
				document.getElementById('paraFrm_homeAddress').focus();
				return false;
			}
			
			var zipCodeVar = trim(document.getElementById('paraFrm_zipCode').value);
			if(zipCodeVar=="")	{
				alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase());
				document.getElementById('paraFrm_zipCode').focus();
				return false;
			}
			
			
			var cityNameVar = trim(document.getElementById('paraFrm_cityName').value);
			if(cityNameVar=="")	{
				alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase());
				document.getElementById('paraFrm_cityName').focus();
				return false;
			}
			
			var stateVar = trim(document.getElementById('paraFrm_state').value);
			if(stateVar=="")	{
				alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase());
				document.getElementById('paraFrm_state').focus();
				return false;
			}
			
			
			var terminationDateVar = trim(document.getElementById('paraFrm_terminationDate').value);
			if(terminationDateVar == "") {
				alert("Please enter "+document.getElementById('terminationDate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_terminationDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_terminationDate',"terminationDate")){
						document.getElementById('paraFrm_terminationDate').focus();
						return false;   	
   					}
			}
			
			var lastDayWorkDateVar = trim(document.getElementById('paraFrm_lastDayWorkDate').value);
			if(lastDayWorkDateVar == "") {
				alert("Please enter "+document.getElementById('lastDayWorkDate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_lastDayWorkDate').focus();
				return false;
			}else {			
				if(!validateDate('paraFrm_lastDayWorkDate',"lastDayWorkDate")){
					document.getElementById('paraFrm_lastDayWorkDate').focus();
					return false;   	
   				}		
			}
  
   			if(!dateDifferenceEqual(lastDayWorkDateVar, terminationDateVar, 'paraFrm_lastDayWorkDate', 'lastDayWorkDate', 'terminationDate')){
	      		document.getElementById('paraFrm_terminationDate').focus();
	      		return false;
   			} else {
   				if(!ifDateDifferentThen(terminationDateVar, lastDayWorkDateVar, 'paraFrm_terminationDate', 'terminationDate', 'lastDayWorkDate')){
	      			document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').focus();
	      			return false;
   				}
   			}

			var terminationTypeRadioOptionValueVar = document.getElementById('terminationTypeRadioOptionValue').value;
			if(terminationTypeRadioOptionValueVar =="")
			{
				alert("Please select "+document.getElementById('terminationType').innerHTML.toLowerCase());
				return false;
			}else if(terminationTypeRadioOptionValueVar =="vo") {
				var voluntaryTerminationReasonVar = trim(document.getElementById('paraFrm_voluntaryTerminationReason').value);
				if(voluntaryTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}else if(terminationTypeRadioOptionValueVar =="ivo") {
				var inVoluntaryTerminationReasonVar = trim(document.getElementById('paraFrm_inVoluntaryTerminationReason').value);
				if(inVoluntaryTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}else if(terminationTypeRadioOptionValueVar =="ot") {
				var otherTerminationReasonVar = trim(document.getElementById('paraFrm_otherTerminationReason').value);
				if(otherTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}
   			
   			var eligibleToRehireVar = document.getElementById('paraFrm_eligibleToRehire').value;
   			var ifNoOrProvisionalVar = trim(document.getElementById('paraFrm_ifNoOrProvisional').value);
   			if(eligibleToRehireVar == "") {
   				alert("Please select "+document.getElementById('eligibleToRehire').innerHTML.toLowerCase());
   				return false;
   			}else if(eligibleToRehireVar == "no") {
   				if(ifNoOrProvisionalVar =="")
   				{
   					alert("Please enter Explaination");
   					return false;
   				}	
   			}else if(eligibleToRehireVar == "pro") {
   				if(ifNoOrProvisionalVar =="") 
   				{
   					alert("Please enter Explaination");
   					return false;
   				}	
   			}
   			
   			var vacationHrsTakenVar = trim(document.getElementById('paraFrm_vacationHrsTaken').value);
   			if(vacationHrsTakenVar!="")
			{
				if(!validateTime('paraFrm_vacationHrsTaken',"vacationHrsTaken")){
					document.getElementById('paraFrm_vacationHrsTaken').focus();
					return false;   	
   				}
			}
   			
   				document.getElementById('paraFrm_status').value = "D";
   				document.getElementById('paraFrm').target = "_self";
    			document.getElementById('paraFrm').action='Termination_draftFunction.action';
				document.getElementById('paraFrm').submit();
   			
		} 
	 catch(e) {
		alert("Exception occured in Save Function : "+e);
	 }	
 }
 
 function ifDateDifferentThen(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	if(endtime < starttime) 
	{ 
		var terDateAndLastDayWorkDateVar = trim(document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').value);
		if(terDateAndLastDayWorkDateVar ==""){
			alert("Please enter Explaination");
			document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').readOnly = '';
			document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').style.background='white';
			return false;
		}
	}else {
		document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').readOnly = 'true';
		document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').style.background='#F2F2F2';
		document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').value = '';
	}
	return true;
}
 
function backtolistFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='Termination_backFunction.action';
	document.getElementById('paraFrm').submit();
}
		
function printFun() {	
	window.print();
}

function deleteFun() {
   var con = confirm("Do you really want to delete this application?");
  		if(con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='Termination_deleteFunction.action';
			document.getElementById('paraFrm').submit();
	}	
}
	
function resetFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='Termination_reset.action';
	document.getElementById('paraFrm').submit();
}
	
function sendforapprovalFun()
{
	try {
			var employeeVar = document.getElementById('paraFrm_employeeNumber').value;
			if(employeeVar=="")	{
				alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
				document.getElementById('paraFrm_employeeName').focus();
				return false;
			}
		
			var homeAddressVar = trim(document.getElementById('paraFrm_homeAddress').value);
			if(homeAddressVar=="")	{
				alert("Please enter "+document.getElementById('homeAddress').innerHTML.toLowerCase());
				document.getElementById('paraFrm_homeAddress').focus();
				return false;
			}
			
			var zipCodeVar = trim(document.getElementById('paraFrm_zipCode').value);
			if(zipCodeVar=="")	{
				alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase());
				document.getElementById('paraFrm_zipCode').focus();
				return false;
			}
			
			
			var cityNameVar = trim(document.getElementById('paraFrm_cityName').value);
			if(cityNameVar=="")	{
				alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase());
				document.getElementById('paraFrm_cityName').focus();
				return false;
			}
			
			var stateVar = trim(document.getElementById('paraFrm_state').value);
			if(stateVar=="")	{
				alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase());
				document.getElementById('paraFrm_state').focus();
				return false;
			}
			
			var terminationDateVar = trim(document.getElementById('paraFrm_terminationDate').value);
			if(terminationDateVar == "") {
				alert("Please enter "+document.getElementById('terminationDate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_terminationDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_terminationDate',"terminationDate")){
						document.getElementById('paraFrm_terminationDate').focus();
						return false;   	
   					}
			}
			
			var lastDayWorkDateVar = trim(document.getElementById('paraFrm_lastDayWorkDate').value);
			if(lastDayWorkDateVar == "") {
				alert("Please enter "+document.getElementById('lastDayWorkDate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_lastDayWorkDate').focus();
				return false;
			}else {			
				if(!validateDate('paraFrm_lastDayWorkDate',"lastDayWorkDate")){
					document.getElementById('paraFrm_lastDayWorkDate').focus();
					return false;   	
   				}		
			}
  
   			if(!dateDifferenceEqual(lastDayWorkDateVar, terminationDateVar, 'paraFrm_lastDayWorkDate', 'lastDayWorkDate', 'terminationDate')){
	      		document.getElementById('paraFrm_terminationDate').focus();
	      		return false;
   			} else {
   				if(!ifDateDifferentThen(terminationDateVar, lastDayWorkDateVar, 'paraFrm_terminationDate', 'terminationDate', 'lastDayWorkDate')){
	      			document.getElementById('paraFrm_ifTerDateAndLastDayWorkDateDiffer').focus();
	      			return false;
   				}
   			}

			var terminationTypeRadioOptionValueVar = document.getElementById('terminationTypeRadioOptionValue').value;
			if(terminationTypeRadioOptionValueVar =="")
			{
				alert("Please select "+document.getElementById('terminationType').innerHTML.toLowerCase());
				return false;
			}else if(terminationTypeRadioOptionValueVar =="vo") {
				var voluntaryTerminationReasonVar = trim(document.getElementById('paraFrm_voluntaryTerminationReason').value);
				if(voluntaryTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}else if(terminationTypeRadioOptionValueVar =="ivo") {
				var inVoluntaryTerminationReasonVar = trim(document.getElementById('paraFrm_inVoluntaryTerminationReason').value);
				if(inVoluntaryTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}else if(terminationTypeRadioOptionValueVar =="ot") {
				var otherTerminationReasonVar = trim(document.getElementById('paraFrm_otherTerminationReason').value);
				if(otherTerminationReasonVar == ""){
					alert("Please select "+document.getElementById('terminationReason').innerHTML.toLowerCase());
					return false;
				}
			}
   			
   			var eligibleToRehireVar = document.getElementById('paraFrm_eligibleToRehire').value;
   			var ifNoOrProvisionalVar = trim(document.getElementById('paraFrm_ifNoOrProvisional').value);
   			if(eligibleToRehireVar == "") {
   				alert("Please select "+document.getElementById('eligibleToRehire').innerHTML.toLowerCase());
   				return false;
   			}else if(eligibleToRehireVar == "no") {
   				if(ifNoOrProvisionalVar =="")
   				{
   					alert("Please enter Explaination");
   					document.getElementById('paraFrm_ifNoOrProvisional').focus();
   					return false;
   				}	
   			}else if(eligibleToRehireVar == "pro") {
   				if(ifNoOrProvisionalVar =="") 
   				{
   					alert("Please enter Explaination");
   					document.getElementById('paraFrm_ifNoOrProvisional').focus();
   					return false;
   				}	
   			}
   			
   			var vacationHrsTakenVar = document.getElementById('paraFrm_vacationHrsTaken').value;
   			if(vacationHrsTakenVar!="")
			{
				if(!validateTime('paraFrm_vacationHrsTaken',"vacationHrsTaken")){
					document.getElementById('paraFrm_vacationHrsTaken').focus();
					return false;   	
   				}
			}
   			
   			var con = confirm("Do you really want to send this application for approval?");
   			if(con)
   			{
   				document.getElementById('paraFrm_status').value = 'P';
   				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').action ='Termination_sendForApproval.action';
				document.getElementById('paraFrm').submit();
   			}
   			
		} 
	 catch(e) {
		alert("Exception occured in Save Function : "+e);
	 }	
}
 
function setOtherReason()
{
 	try{
 		var reason = document.getElementById('paraFrm_otherTerminationReason').value;
 		if(reason == "OTHR"){
 			document.getElementById('ifOtherReasonLabel').style.display = '';
 			document.getElementById('ifOtherReasonTextArea').style.display = '';
 		}else {
 			document.getElementById('ifOtherReasonLabel').style.display = 'none';
 			document.getElementById('ifOtherReasonTextArea').style.display = 'none';
 			document.getElementById('paraFrm_ifOtherTerminationReasonTextArea').value = '';
 		}
 	}catch(e){
 		alert("Error : "+e);
 	}
}
 
function imposeMaxLength(Event, Object, MaxLen)
{
    return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}	
</script>
