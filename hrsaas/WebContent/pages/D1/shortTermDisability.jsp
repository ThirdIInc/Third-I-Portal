<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="AjaxShortTerm.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ShortTermDisability" id="paraFrm" validate="true"  name="ShortTermForm" 
	target="main" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<s:hidden name="shortTermCode" />
			<s:hidden name="apptype"  />
			<s:hidden name="status"  />
			<s:hidden name="trackingNo"  />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Short Term Disability Form</strong>
					<s:hidden name="hiddenValue"/>
					</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			 <td colspan="4"><s:hidden name="flag"/>
			   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">		
						
						<s:if test="listComment">
						<tr>
							<td width="25%" colspan="4"><B>Comments By Approver</B>							</td>
							
						</tr>
						
							
						<tr>
						<td class="formth" width="05%">Sr No </td>
						<td class="formth" width="15%">Approver Name </td>
						<td class="formth" width="40%" align="center">Comments </td>
						<td class="formth" width="15%">Application Date </td>
						<td class="formth" width="15%">Status </td>						
						</tr>
						<%int tt=1; %>
						
						<s:iterator value="listComment">
						<tr>
						<td   class="sortableTD"><%=tt++ %> </td>
						<td   class="sortableTD"> <s:property value="ittApproverName"/></td>
						<td   class="sortableTD"><s:property value="ittComments"/> </td>
						<td   class="sortableTD"> <s:property value="ittApplicationDate"/></td>
						<td   class="sortableTD"> <s:property value="ittStatus"/></td>
						</tr>
						</s:iterator>
					</s:if>	
						
						
				</table>
				</td>
	</tr>
		
		
		
		
		
		
		<!-- Request Information start -->

				
		


				<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						
						<tr>
							<td colspan="4"><b><label class="set"
								name="employee.information" id="employee.information"
								ondblclick="callShowDiv(this);"><%=label.get("employee.information")%></label> :</b>
							</td>
						</tr>

						<tr>
								
							
						
								<td width="18%"><label id="pemployee.name" name="employee.name"
									ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
								:<font color="red">*</font></td>
								<td width="50%" colspan="3"><s:textfield  cssStyle="background-color: #F2F2F2;" 
									name="employeeToken" size="20" theme="simple" readonly="true" /><s:textfield
									name="employeeName" size="71" theme="simple" readonly="true"  cssStyle="background-color: #F2F2F2;" />
								<s:hidden name="employeeCode" />
								
								<s:if test="%{shortTermCode == ''}">
								<img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16" id='ctrlHide'
									onclick="javascript:callsF9(500,325,'ShortTermDisability_f9action.action');">
								</s:if>	
								</td>
						


						</tr>
						<tr>
							<td width="25%"><label id="dept.no"
								name="dept.no" ondblclick="callShowDiv(this);"><%=label.get("dept.no")%></label>
							:</td>
							<td width="25%"><s:textfield name="employeeDeptNo"
								 size="20"  maxlength="80" readonly="true"/><s:hidden name="departmentCode"></s:hidden>
								 
								 <s:if test="%{flag == ''}"> 
								 <img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16" id='ctrlHide'
									onclick="javascript:callsF9(500,325,'ShortTermDisability_f9department.action');">
								</s:if>									 
								 </td>

							<td width="25%" align="left"><label id="executive"
								name="executive" ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
							:</td>

							<td width="25%">
							<s:if test="%{flag == ''}"> <s:textfield name="executive"
								 size="20" maxlength="80" /> </s:if>
							<s:else><s:textfield name="executive"
								 size="20" maxlength="80" readonly="true"/></s:else>
							
								 
								 </td>

						</tr>
						
					</table>
					</td>
				</tr>
				<!-- Request Information end -->

				<!-- Shiping Information start -->
				
			<s:if test="%{flag == ''}">
				<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">

						<tr>
							<td colspan="4"><b><label class="set"
								name="short.term.disability.information" id="short.term.disability.information"
								ondblclick="callShowDiv(this);"><%=label.get("short.term.disability.information")%></label></b>
							</td>
						</tr>

						<tr>
							<td  colspan="4">
							
							Note: Employees are on STD when their absence is expected 7 calendar days and their illness or injury prevents them from doing their job. This form should be submitted as soon as it is known that the absence is expected to exceed 7 calendar days 
							  </td>
						</tr>



						<tr>
							<td width="25%"><label id="std.effective.date" name="std.effective.date"
								ondblclick="callShowDiv(this);"><%=label.get("std.effective.date")%></label>
							:<font color="red">*</font></td>
							<td  colspan="1">
								
								<s:textfield name="stdEffectiveDate" onkeypress="return numbersWithHiphen();"/><s:a
								href="javascript:calculateSTDDate();"  >
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a>
								</td>
							<td width="25%" colspan="1"><label name="std.eligible.date" id="std.eligible.date"
								ondblclick="callShowDiv(this);"><%=label.get("std.eligible.date")%></label>:</td>
							<td width="25%" colspan="1">
								<s:textfield name="stdEligibleDate" onkeypress="return numbersWithHiphen();" readonly="true" cssStyle="background-color: #F2F2F2;"/>
								</td>
						</tr>
						<tr>
							<td width="25%"><label id="action.sto" name="action.sto"
								ondblclick="callShowDiv(this);"><%=label.get("action.sto")%></label>
							:</td>
							<td width="25%">
								
								<s:textfield name="actionSTO" maxlength="100"/>
								
								</td>

							<td width="25%"><label id="region.sto" name="region.sto"
								ondblclick="callShowDiv(this);"><%=label.get("region.sto")%></label>
							:</td>
							<td width="25%">								
								
								<s:textfield name="regionSTO" maxlength="100"/>
								
								</td>

						</tr>

						

						<tr>
							<td colspan="4"><font color="black"><b>
							Note: Employees are not required to reveal medical reason for disability to their manager. Medical reason will be revealed to our Short Term Disability vendor at a later date. STD is effective the first day of absence.</b></font>
							</td>
						</tr>
						
						<tr>
							<td width="25%"><label id="work.related" name="work.related"
								ondblclick="callShowDiv(this);"><%=label.get("work.related")%></label>
							:</td>
						<td>
						<s:hidden name="workRelatedHidden" />	
				<input type="radio" name="workRelated" id="Yes" onclick="callWorkRelated('Yes')">Yes		
				<input type="radio" name="workRelated" id="No" onclick="callWorkRelated('No')"> No
						
						
							</td>


						</tr>
						
						
						
					</td>
				</tr>

			</table>
			</td>
		</tr>
</s:if>
<s:else>
<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">

						<tr>
							<td colspan="4"><b><label class="set"
								name="short.term.disability.information" id="short.term.disability.information"
								ondblclick="callShowDiv(this);"><%=label.get("short.term.disability.information")%></label></b>
							</td>
						</tr>

						<tr>
							<td  colspan="4">
							
							Note: Employees are on STD when their absence is expected 7 calendar days and their illness or injury prevents them from doing their job. This form should be submitted as soon as it is known that the absence is expected to exceed 7 calendar days 
							  </td>
						</tr>



						<tr>
							<td width="25%"><label id="std.effective.date" name="std.effective.date"
								ondblclick="callShowDiv(this);"><%=label.get("std.effective.date")%></label>
							:</td>
							<td  colspan="1">
								
								<s:textfield name="stdEffectiveDate" readonly="true" cssStyle="background-color: #F2F2F2;"  />
								</td>
							<td width="25%" colspan="1"><label name="std.eligible.date" id="std.eligible.date"
								ondblclick="callShowDiv(this);"><%=label.get("std.eligible.date")%></label>:</td>
							<td width="25%" colspan="1">
								<s:textfield name="stdEligibleDate" readonly="true" cssStyle="background-color: #F2F2F2;"  />
								</td>
						</tr>
						<tr>
							<td width="25%"><label id="action.sto" name="action.sto"
								ondblclick="callShowDiv(this);"><%=label.get("action.sto")%></label>
							:</td>
							<td width="25%">
								
								<s:textfield name="actionSTO" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"  />
								
								</td>

							<td width="25%"><label id="region.sto" name="region.sto"
								ondblclick="callShowDiv(this);"><%=label.get("region.sto")%></label>
							:</td>
							<td width="25%">								
								
								<s:textfield name="regionSTO" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"  />
								
								</td>

						</tr>

						

						<tr>
							<td colspan="4"><font color="black"><b>
							Note: Employees are not required to reveal medical reason for disability to their manager. Medical reason will be revealed to our Short Term Disability vendor at a later date. STD is effective the first day of absence.</b></font>
							</td>
						</tr>
						
						<tr>
							<td width="25%"><label id="work.related" name="work.related"
								ondblclick="callShowDiv(this);"><%=label.get("work.related")%></label>
							:</td>
						<td>
						<s:hidden name="workRelatedHidden" />	
				<input type="radio" name="workRelated" id="Yes" disabled="disabled" cssStyle="background-color: #F2F2F2;"   >Yes		
				<input type="radio" name="workRelated" id="No" disabled="disabled" cssStyle="background-color: #F2F2F2;"   > No
						
						
							</td>


						</tr>
						
						
						
					</td>
				</tr>

			</table>
			</td>
		</tr>

</s:else>		
		
		
		
		
		
		
		
		<!-- Shiping Information end -->

		<!-- SHORT TERM DISABILITY -->
		<tr>
			<td  colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				
				<tr>
					<td colspan="4"><b><label class="set"
						name="return.from.short.term.disability" id="return.from.short.term.disability"
						ondblclick="callShowDiv(this);"><%=label.get("return.from.short.term.disability")%></label></b>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">Please do not complete this section until the employee has physically return to work. Reminder: BEFORE the employee begins working the employee must present a doctor's note to the manager permitting the employee to return to work.  </td>
				</tr>
			<tr>
					<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;	</td>
			</tr>	
			<tr>
					<td  width="25%"><label name="did.employee.return.to.work.fulltime" 
						id="did.employee.return.to.work.fulltime" ondblclick="callShowDiv(this);"><%=label.get("did.employee.return.to.work.fulltime")%></label>:
					
					</td>
					<td width="75%" colspan="3">
				<s:hidden name="didEmployeereturnHidden" />	
				<input type="radio" name="didEmployeereturn" id="didYes" onclick="callDidEmployee('Yes')">Yes		
				<input type="radio" name="didEmployeereturn" id="didNo" onclick="callDidEmployee('No')"> No			
						
						 </td>
			</tr>
			
			<tr>
					<td colspan="4">&nbsp;
					</td>
			</tr>				
			<tr id="W1">
							<td width="25%"><label id="date.emp.return.to.work" name="date.emp.return.to.work"
								ondblclick="callShowDiv(this);"><%=label.get("date.emp.return.to.work")%></label>
							:</td>
							<td width="25%">
								
								<s:textfield name="dateEmpReturn" onkeypress="return numbersWithHiphen();" maxlength="10"/><s:a
								href="javascript:NewCal('paraFrm_dateEmpReturn','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /> </s:a>
								
								</td>

							<td width="25%">
							</td>
							<td width="25%">								
							</td>

			</tr>	
			
			<tr id="W2">
							<td width="25%"><label id="action.rfd" name="action.rfd"
								ondblclick="callShowDiv(this);"><%=label.get("action.rfd")%></label>
							:</td>
							<td width="25%">
								
								<s:textfield name="actionRFD" maxlength="200"/>								
								</td>

							<td width="25%"><label id="region.rfd" name="region.rfd"
								ondblclick="callShowDiv(this);"><%=label.get("region.rfd")%></label>
							</td>
							<td width="25%">
							<s:textfield name="regionRFD" maxlength="200"/>									
							</td>

			</tr>
			
			<tr id="W3">
							<td width="25%" id="W31"><label id="no.of.working.hrs" name="no.of.working.hrs"
								ondblclick="callShowDiv(this);"><%=label.get("no.of.working.hrs")%></label>
							:</td>
							<td width="25%" id="W32">
								
								<s:textfield name="noOfWorkingHrs" size="15" onkeypress="return numbersWithColon();" maxlength="5"/>	(HH24:MM)							
								</td>

							<td width="25%"><label id="day.of.absence" name="day.of.absence"
								ondblclick="callShowDiv(this);"><%=label.get("day.of.absence")%></label>
							</td>
							<td width="25%">
							<s:textfield name="daysOfAbsence" onkeypress="return numbersWithHiphen();" maxlength="8"/>									
							</td>

		</tr>
		<tr>
					<td colspan="4">&nbsp;
					</td>
		</tr>
		
		<tr>
		<td colspan="4"><strong>The Information regarding the employee's partial disability benefits will be send to Decision One by The Standard Insurance Company. Please submit this form ONLY when the employee has returned to work full time</strong>
					 </td>
		</tr>
		
		
				
			</table>
		</td>		
	</tr>
	
				
				

				

		<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">
	
	
	  <tr>
			<td width="25%" colspan="1">
			<strong><label class="set" name="completed.by" id="completed.by" ondblclick="callShowDiv(this);">
				<%=label.get("completed.by")%></label></strong>
			 :</td>
			<td width="20%" colspan="1"><s:property value="completedBy"/>
			<s:hidden name="completedBy"/> 
			<s:hidden name="completedOn"/> 
			</td>
			


			<td width="20%"  colspan="1">
			<strong><label class="completed.on" name="completed.on" id="sin" ondblclick="callShowDiv(this);">
				<%=label.get("completed.on")%></label></strong> :</td>
			<td width="25%"  colspan="1"><s:property value="completedOn"/></td>
				
 </tr>
	
	
</table>
</td>
</tr>






	
	<tr>
		<td><jsp:include
			page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>

	</table>




</s:form>


<script type="text/javascript">	
		//callForDelete
	
	
		//onLoad();
		
			function calculateSTDDate(){
			
			javascript:NewCal('paraFrm_stdEffectiveDate','DDMMYYYY');
			document.getElementById('paraFrm_stdEffectiveDate').focus();
			
			}
			
		
		onLoad();
		function onLoad(){
		callWorkRelatedOnload();
		callDidEmployeeOnload();
		}
		function callWorkRelatedOnload(){
		if(document.getElementById('paraFrm_workRelatedHidden').value==''){
			document.getElementById('paraFrm_workRelatedHidden').value='No';
			}
			document.getElementById(document.getElementById('paraFrm_workRelatedHidden').value).checked=true;
		}
		function callDidEmployeeOnload(){
			if(document.getElementById('paraFrm_didEmployeereturnHidden').value==''){
			document.getElementById('paraFrm_didEmployeereturnHidden').value='No';
			}		
			document.getElementById('did'+document.getElementById('paraFrm_didEmployeereturnHidden').value).checked=true;				
		callHidden1();
		callHidden();		
		}
		
		
		
		
		function callDidEmployee(id){
		document.getElementById('paraFrm_didEmployeereturnHidden').value=id;
		if(document.getElementById('paraFrm_didEmployeereturnHidden').value==''){
		document.getElementById('paraFrm_didEmployeereturnHidden').value='No';
		}
		document.getElementById('did'+document.getElementById('paraFrm_didEmployeereturnHidden').value).checked=true;				
		callHidden();	
		callHidden1();
		}
		function callWorkRelated(id){
		document.getElementById('paraFrm_workRelatedHidden').value=id;
		if(document.getElementById('paraFrm_workRelatedHidden').value==''){
		document.getElementById('paraFrm_workRelatedHidden').value='No';
		}
		document.getElementById(document.getElementById('paraFrm_workRelatedHidden').value).checked=true;	
		callHidden1();
		callHidden();				
		}
		
	
	function callHidden(){
	document.getElementById('W1').style.display='none';
	document.getElementById('W2').style.display='none';
	document.getElementById('W3').style.display='none';
		if(document.getElementById('paraFrm_workRelatedHidden').value=='Yes' ||document.getElementById('paraFrm_didEmployeereturnHidden').value=='Yes'){
			document.getElementById('W1').style.display='';
			document.getElementById('W2').style.display='';
			document.getElementById('W3').style.display='';
		}	
	}
	
	function callHidden1(){
	
	document.getElementById('W31').style.display='none';
	document.getElementById('W32').style.display='none';	
	if(document.getElementById('paraFrm_didEmployeereturnHidden').value=='No'){
		document.getElementById('W31').style.display='';
		document.getElementById('W32').style.display='';		
	}	
	}
	
	
	
	
	
	function callForDelete(id) {
	
		var vv=confirm("Do you really want to remove this application?");
	if(vv){
		document.getElementById('paraFrm_hiddenValue').value=id;
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ShortTermDisability_removeSelectType.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
	
	function callSelect() {
		
		if(document.getElementById('paraFrm_selectTypeCode').value==''){
		alert('Please Select Type');
		return false;
		}
		if(document.getElementById('paraFrm_noOfLots').value==''){
		alert('Please enter no of lots');
		return false;
		}
		
	}
	
	
	function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
		if(document.getElementById('paraFrm_apptype').value==''){
		document.getElementById('paraFrm').action = 'ShortTermDisability_back.action';
		}
      	else{
      	document.getElementById('paraFrm').action = 'ShortTermDisabilityAppr_back.action';
      	}
		document.getElementById('paraFrm').submit();
	}
	function printFun() {	
	window.print();
	}
	function resetFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ShortTermDisability_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function calculateDate(){
	var currentDate=document.getElementById('paraFrm_stdEffectiveDate').value;
	if(!currentDate==''){	
		var today = new Date(currentDate.substring(6, 10), currentDate.substring(3, 5)-1, currentDate.substring(0, 2));
		today.setDate(today.getDate() + 6);
			
		//alert(today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getYear());
		var dd=today.getDate();
		if(dd<10){
		dd='0'+dd;
		}
		var mm=today.getMonth()+1;
		if(mm<10){
		mm='0'+mm;
		}		
		document.getElementById('paraFrm_stdEligibleDate').value=dd+'-'+(mm)+'-'+today.getFullYear();
	}
	
	}
	
	
	//
	function draftFun() {
		
		
		
		if(!validate()){
	return false;
	}
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ShortTermDisability_draft.action';
		document.getElementById('paraFrm').submit();
	}
	function submitapplicationFun() {		
		if(!validate()){
		return false;
			}
	var vv=confirm("Do you really want to submit this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ShortTermDisability_sendForApproval.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
		function resubmitapplicationFun() {		
		if(!validate()){
		return false;
			}
	
	var vv=confirm("Do you really want to resubmit this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ShortTermDisability_resubmit.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
		
		
		function validate(){
		var fromDate = document.getElementById('paraFrm_stdEffectiveDate').value;
		if(document.getElementById('paraFrm_employeeCode').value==''){
		alert('Please select employee');
		return false;
		}
		if(fromDate==''){
		alert('Please enter std effective date');
		return false;
		}
		var check= validateDate('paraFrm_stdEffectiveDate', 'std.effective.date');
			if(!check){
			return false;
		}
		
		
		if(document.getElementById('paraFrm_dateEmpReturn').value==''){					
			}
			else{
				var check1= validateDate('paraFrm_dateEmpReturn', 'date.emp.return.to.work');
				if(!check1){
				return false;
				}
			}
		
		var toDate = document.getElementById('paraFrm_dateEmpReturn').value;
		if(!toDate==''){
			if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_dateEmpReturn', 'std.effective.date', 'date.emp.return.to.work')) {
				return false;
			}	
		}			
		
		
		
		
		
		
		if(document.getElementById('paraFrm_noOfWorkingHrs').value==''){
		}	
		else{
		if(IsValidTime(document.getElementById('paraFrm_noOfWorkingHrs').value)){
			}
			else{
			document.getElementById('paraFrm_noOfWorkingHrs').focus();
			return false;
			}
		}
		
		return true;
		}
	
	function deleteFun() {
	if(document.getElementById('paraFrm_shortTermCode').value==''){
		alert('Please first create draft');
		return false;
		}	
	var vv=confirm("Do you really want to delete this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ShortTermDisability_delete.action';
		document.getElementById('paraFrm').submit();
		}else{
		return false;
		}
	}
		
			function IsValidTime(id) {		
				
				if(id==''){
				return true;
				}
			var timeStr=id;//document.getElementById('fromTime'+id).value;
				
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			
			var matchArray = timeStr.match(timePat);
		
			if (matchArray == null) {
			alert("Please enter time in a valid format(HH24:MM)");
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
		
		
		</script>



<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>