<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PartnerPostResume" id="paraFrm" theme="simple"
	validate="true">
	<s:hidden name="resumeCode" />
	<s:hidden name="formName" />
	<s:hidden name="candCode" />
	<s:hidden name="dataPath" />
	<s:hidden name="hiringManagerCode" />
	<s:hidden name="objectLength" />
	<s:hidden name="candPosition" />
	<s:hidden name="candiName" />
	<s:hidden name="candidateNotIn" />
	<s:hidden name="referalFlag" />
	<s:hidden name="partnerFlag" />
	<s:hidden name="reqApprStatusSer" />
	<s:hidden name="flagAdd" />
	<s:hidden name="dashletFlag" />
	<s:hidden name="RefFlag" />
	<s:hidden name="RefCanTestFlag" />

	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Post Resume</strong></td>
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
					<td width="78%" colspan="2"><s:submit cssClass="token"
						value="Submit Resume" action="PartnerPostResume_submitResume"
						onclick="return submitForm();" /> <s:if test="partnerFlag">
						<s:submit cssClass="cancel" value="Cancel"
							action="PartnerJobBoard_input" />
					</s:if> <s:else>
						<s:if test="referalFlag">
							<input type="button" class="cancel" value="Cancel"
								onclick="return Homepagecancel();" />
						</s:if>
						<s:else>
							<s:submit cssClass="cancel" value="Cancel"
								onclick="return HomepagePostcancel();" />
						</s:else>
					</s:else> <!-- 
						<input type="button" value=" Export In Xls " class="token"
						onclick="callReportForDisp('X');">
					 --></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>


		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4" class="txt"><strong
						class="text_head">Post Resume</strong></td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<!--Table 1-->

						<tr>
							<td width="18%" height="22"><label class="set"
								name="reqs.code" id="requisition.code"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="requisitionName" size="40" readonly="true" /> 
							<s:if test="postCandidateFromJobBoardFlag">
							</s:if>	
							<s:else>
								<img src="../pages/images/search2.gif" id="ctrlHide"
										class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'PartnerPostResume_f9RequisitionCodeAction.action');">
							</s:else>
										
							</td>
							<td width="15%" height="22">&nbsp;</td>
							<td width="25%" nowrap="nowrap" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="18%" height="22"><s:hidden name="requisitionCode" />
							<s:hidden name="requisitionDate" /> <s:hidden
								name="jobDescription" /> <label class="set" name="position"
								id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="25%" height="22"><s:textfield name="position"
								size="40" readonly="true" /></td>
							<td width="15%" height="22">&nbsp;</td>
							<td width="25%" nowrap="nowrap" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="hiring.mgr" id="hiring.manager"
								ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
							:</td>
							<td width="25%" height="22"><s:textfield
								name="hiringManager" size="40" readonly="true" /></td>
							<td width="15%" height="22">&nbsp;</td>
							<td width="25%" nowrap="nowrap" height="22">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="70%" colspan="3"><strong class="text_head">Candidate
					List</strong></td>
					<td width="30%" align="right"><s:submit cssClass="delete"
						value="Remove" action="PartnerPostResume_delete"
						onclick="return removeCandidate();" /></td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth"><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" name="cand.name" id="candidate.name"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
									<td width="12%" valign="top" class="formth"><label
										class="set" name="experience" id="experience1"
										ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></td>
									<td width="9%" valign="top" class="formth" nowrap="nowrap">
									<label class="set" name="posted.date" id="posted.date"
										ondblclick="callShowDiv(this);"><%=label.get("posted.date")%></label></td>
									<td width="8%" valign="top" class="formth"><label
										class="set" name="ctc" id="ctc"
										ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></td>
									<td width="6%" valign="top" class="formth"><label
										class="set" name="gend" id="gender1"
										ondblclick="callShowDiv(this);"><%=label.get("gend")%></label></td>
									<td width="12%" valign="top" class="formth"><label
										class="set" name="listing.status" id="listing.status"
										ondblclick="callShowDiv(this);"><%=label.get("listing.status")%></label></td>
									<td width="6%" valign="top" class="formth"><label
										class="set" name="view.cv" id="view.cv"
										ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></td>
									<td width="4%" valign="top" class="formth" align="center"><s:checkbox
										name="chkAll" onclick="selectAll();" /></td>
								</tr>

								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">There is no data to display</font></td>
									</tr>
								</s:if>

								<%!int i = 0;%>
								<%
									int k = 1;
										int c = 0;
								%>
								<s:iterator status="stat" value="candidateList">
									<tr>
										<td width="5%" class="sortabletd"><%=k%></td>
										<td width="15%" class="sortabletd"><s:property
											value="candidateName" /> <s:hidden name="candidateName" /><s:hidden
											name="candidateCode" /></td>
										<td width="12%" class="sortabletd"><s:property
											value="candExperience" /> <s:hidden name="candExperience" /></td>
										<td width="9%" class="sortabletd"><s:property
											value="postedDate" /> <s:hidden name="postedDate" /></td>
										<td width="8%" class="sortabletd"><s:property value="ctc" />&nbsp;
										<s:hidden name="ctc" /></td>
										<td width="6%" class="sortabletd"><s:property
											value="candGender" /> <s:hidden name="candGender" /></td>
										<td width="12%" class="sortabletd"><s:property
											value="status" /> <s:hidden name="status" /> <s:hidden
											name="firstNameIterator" /><s:hidden name="lastNameIterator" />
										<s:hidden name="emailIdIterator" /><s:hidden
											name="contactNoIterator" /> <s:hidden name="yearIterator" /><s:hidden
											name="monthIterator" /> <s:hidden name="expCtcIterator" /><s:hidden
											name="relocateIterator" /> <s:hidden name="uploadIterator" /><s:hidden
											name="locationIterator" /> <s:hidden name="stateIterator" /><s:hidden
											name="dobIterator" /> <s:hidden name="genderIterator" /><s:hidden
											name="maritalStatusIterator" /> <s:hidden
											name="checkBoxFlag" id="<%="chkFlag"+c %>" /></td>
										<td width="6%" class="sortabletd" align="center"><input
											type="button" class="token" value="View"
											onclick="showRecord('<s:property value="uploadIterator"/>');" /></td>
										<td width="4%" class="sortabletd" align="center"><s:if
											test="checkBoxFlag">
											<input type="checkbox" name="chk" id="<%="chk"+c %>"
												onclick="callChk(<%=c %>)" disabled="disabled" />
											<input type="hidden" name="checkBox" id="<%=c %>" value="N" />
										</s:if> <s:else>
											<input type="checkbox" name="chk" id="<%="chk"+c %>"
												onclick="deselectAll(); callChk(<%=c %>)" />
											<input type="hidden" name="checkBox" id="<%=c %>" value="N" />
										</s:else></td>
									</tr>
									<%
										k++;
												c++;
									%>
								</s:iterator>
							</table>

							</td>
						</tr>
					</table>
					</td>
					<input type="hidden" name="count" id="count" value="<%=c%>" />
				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4" class="txt"><strong
						class="text_head">Add New Candidate</strong></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<!--Table 1-->
						<s:if test="addCandidateFlag">
							<tr>
								<td width="100%" height="22" colspan="4" class="formtext">
								<label class="set" name="cand.databank" id="cand.databank"
									ondblclick="callShowDiv(this);"><%=label.get("cand.databank")%></label>
								: <input type="radio" name="selectCand" value="dataBank"
									onclick="disableFields();"> <label class="set"
									name="new.cand" id="new.cand" ondblclick="callShowDiv(this);"><%=label.get("new.cand")%>
								</label> : <input type="radio" name="selectCand" value="new"
									checked="checked" onclick="enableFields();"></td>
							</tr>
						</s:if>

						<tr>
							<td width="18%" height="22" class="formtext"><label
								class="set" name="first.name" id="candidate.fname"
								ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22" class="formtext">
							<s:textfield name="candFirstName" size="25" maxlength="50"
								onkeypress="return charactersOnly();" /> <span id="candName"><img
								class="iconImage" src="../pages/images/recruitment/search2.gif"
								width="15" height="16"
								onclick="javascript:callsF9(500,325,'PartnerPostResume_f9CandidateAction.action');" />
							</span></td>
							<td width="15%" height="22" class="formtext"><label
								class="set" name="email.id" id="email.id"
								ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22" class="formtext">
							<s:textfield name="emailId" size="25" maxlength="50"
								onkeypress="return emailCheck();" /></td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="last.name" id="candidate.lname"
								ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="candLastName" size="25" maxlength="50"
								onkeypress="return charactersOnly();" /></td>
							<td width="15%" height="22"><label class="set"
								name="contact.no" id="contact.no"
								ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="contactNo" size="25" maxlength="25"
								onkeypress="return validatePhoneNo();" /></td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="experience" id="experience"
								ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22">
								<s:textfield name="year" size="2" maxlength="2"
									onkeypress="return numbersOnly();" />Years 
								<s:textfield
								name="month" size="2" maxlength="2"
								onkeypress="return numbersOnly();" />Months</td>
							<td width="15%" height="22"><label class="set"
								name="current.location" id="current.location"
								ondblclick="callShowDiv(this);"><%=label.get("current.location")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="location" size="25" readonly="true" /> <span
								id="location1"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'PartnerPostResume_f9LocationAction.action');" /></span>
							<s:hidden name="locationCode" /><s:hidden name="state" /><s:hidden
								name="stateCode" /> <s:hidden name="country" /><s:hidden
								name="countryCode" /></td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="current.ctc" id="current.ctc"
								ondblclick="callShowDiv(this);"><%=label.get("current.ctc")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="currentCtc" size="25" maxlength="10"
								onkeypress="return numbersWithDot();" /></td>
							<td width="15%" height="22" nowrap="nowrap"><label
								class="set" name="dob" id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="dob" size="25" maxlength="10"
								onkeypress="return numbersWithHiphen();" /> <span
								id="birthDate"><s:a
								href="javascript:NewCal('paraFrm_dob','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></span></td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="exp.ctc" id="expected.ctc" ondblclick="callShowDiv(this);"><%=label.get("exp.ctc")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" height="22"><s:textfield
								name="expectedCtc" size="25" maxlength="10"
								onkeypress="return numbersWithDot();" /></td>
							<td width="15%" height="22"><label class="set" name="gend"
								id="gender" ondblclick="callShowDiv(this);"><%=label.get("gend")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:hidden
								name="hiddenGender" /> <s:select name="gender"
								cssStyle="width:150"
								list="#{'':'Select', 'M':'Male', 'F':'Female', 'O':'Other'}" /></td>
						</tr>


						<tr>
							<td width="18%" height="22"><label class="set"
								name="relocate" id="relocate" ondblclick="callShowDiv(this);"><%=label.get("relocate")%></label>
							:</td>
							<!--				            	 <font color="red" size="2">*</font>-->
							<td width="25%" nowrap="nowrap" height="22"><s:radio
								name="relocate" theme="simple" list="#{'Y':'Yes', 'N':'No'}" /></td>
							<td width="15%" height="22"><label class="set"
								name="marital.status" id="marital.status"
								ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap" height="22"><s:hidden
								name="hiddenMaritalStatus" /> <s:select name="maritalStatus"
								cssStyle="width:150"
								list="#{'':'Select', 'S':'Single', 'M':'Married', 'D':'Divorced'}" /></td>
						</tr>

						<tr>
							<td width="18%" height="22"><label class="set"
								name="upload.resume" id="upload.resume"
								ondblclick="callShowDiv(this);"><%=label.get("upload.resume")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="60%" nowrap="nowrap" height="22" colspan="3"><s:textfield
								name="uploadFileName" size="25" readonly="true" /> <span
								id="upload"><input type="button" class="token"
								theme="simple" value="Upload Resume"
								onclick="uploadFile('uploadFileName');" /></span></td>
						</tr>

						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="1" /></td>
						</tr>

						<tr>
							<td width="100%" height="22" colspan="4" align="center"><s:submit
								cssClass="token" value="  Add  Candidate"
								onclick="return validateAdd();"
								action="PartnerPostResume_postCandidate" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td width="100%" colspan="4" align="left"><s:submit
				cssClass="token" value="Submit Resume"
				action="PartnerPostResume_submitResume"
				onclick="return submitForm();" /> <s:if test="partnerFlag">
				<s:submit cssClass="cancel" value="Cancel"
					action="PartnerJobBoard_input" />
			</s:if> <s:else>
				<s:if test="referalFlag">
					<input type="button" class="cancel" value="Cancel"
						onclick="return Homepagecancel();" />
				</s:if>
				<s:else>
					<s:submit cssClass="cancel" value="Cancel"
						onclick="return HomepagePostcancel();" />
				</s:else>
			</s:else> <!--
					<input type="button" value=" Export In Xls " class="token"
						onclick="callReportForDisp('X');">
			--></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script>


	document.getElementById("candName").style.display = 'none';

	function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	function validateAdd(){
		var yes = document.getElementById('paraFrm_relocateY').checked;
		var no  = document.getElementById('paraFrm_relocateN').checked;
		var formName = document.getElementById("paraFrm_formName").value;
		var count   = document.getElementById("count").value;
		
		if(formName == "cndtInt"){
			if(count >=1){
				alert("You can add only one candidate for evaluation");
				return false;
			}
		}
		
		if(formName == "offer"){
			if(count >=1){
				alert("You can add only one candidate for offer");
				return false;
			}
		}
		
		if(formName == "appointment"){
			if(count >=1){
				alert("You can add only one candidate for appointment");
				return false;
			}
		}
		
		if(formName == "backGround"){
			if(count >=1){
				alert("You can add only one candidate for back ground check");
				return false;
			}
		}
		
		
		var fieldName = ["paraFrm_candFirstName", "paraFrm_candLastName", "paraFrm_year"];
		var lableName = ["candidate.fname", "candidate.lname", "experience"];
		var flag      = ["enter", "enter", "enter"];
		
		if(!validateBlank(fieldName, lableName, flag))
			return false;
				
		var expMonth = trim(document.getElementById("paraFrm_month").value);
		if(expMonth =="") {
			alert("Please enter experience in months if any otherwise enter 0.");
			document.getElementById("paraFrm_month").focus();
			return false;
		}	
		
		var month = document.getElementById("paraFrm_month").value;
		
		if(month != "" && eval(month) > 11){
			alert("Month should not be greater than 11");
			document.getElementById("paraFrm_month").focus();
			return false;
		}
		
		var fieldName11 = ["paraFrm_uploadFileName", "paraFrm_emailId"];
		var lableName11 = ["upload.resume", "email.id"];
		var flag11      = ["", "enter"];
		
		if(!validateBlank(fieldName11, lableName11, flag11)) return false;
		
		if(!validateEmail("paraFrm_emailId")) return false;
		
		var fieldName1 = ["paraFrm_contactNo", "paraFrm_location", "paraFrm_dob"];
		var lableName1 = ["contact.no", "current.location", "dob", "gender", "marital.status"];
		var flag1      = ["enter", "select", "select/enter"];
		
		if(!validateBlank(fieldName1, lableName1, flag1)) return false;
		
		if(!validateDate("paraFrm_dob", "dob")) return false;
		
		var fieldName2 = ["paraFrm_gender", "paraFrm_maritalStatus"];
		var lableName2 = ["gender", "marital.status"];
		var flag2      = ["select", "select"];
		
		if(!validateBlank(fieldName2, lableName2, flag2)) return false;
		
		document.getElementById('paraFrm_hiddenGender').value = document.getElementById('paraFrm_gender').value;
		document.getElementById('paraFrm_hiddenMaritalStatus').value = document.getElementById('paraFrm_maritalStatus').value;
		
		
		
		var amount=document.getElementById('paraFrm_currentCtc').value;
		var amountlabel=document.getElementById('current.ctc').innerHTML.toLowerCase();
	
		if(trim(amount)!=""){
			if(isNaN(amount)) { 
			  alert("More than one dot not allowed in "+amountlabel+"");
			 // document.getElementById(ctcfieldname).focus();
			  return false;
			 
			 }	
		 }
		 
		var amount=document.getElementById('paraFrm_expectedCtc').value;
		var amountlabel=document.getElementById('expected.ctc').innerHTML.toLowerCase();
	
		if(trim(amount)!=""){
			if(isNaN(amount)) { 
			  alert("More than one dot not allowed in "+amountlabel+"");
			 // document.getElementById(ctcfieldname).focus();
			  return false;
			 
			 }	
		 }
		
		
		/*if(!yes && !no){
			alert("Please select ready for relocate");
			document.getElementById('paraFrm_relocateY').focus();
			return false;
		}*/
	}
	
	function submitForm(){
	document.getElementById('paraFrm').target='_main';
		var fieldName = ["paraFrm_requisitionName"];
		var lableName = ["requisition.code"];
		var flag      = ["select"];
		
	//	if(!validateBlank(fieldName, lableName, flag)) return false;
		
		if(document.getElementById('count').value == 0){
			alert('Please add atleast one candidate in the list');
			return false;
		}
		
		var con = confirm("Do you really want to process?");
		if(con) {
			return true;
		} else {
			return false;
		}
		
	}
	
	function removeCandidate(){
		var count   = document.getElementById("count").value;
		var chkFlag = false;
		
		if(count == 0){
			alert("There is no record in the list");
			return false;
		}

		for(var i=0; i<count; i++){
			if(document.getElementById('chk'+i).checked){
				chkFlag = true;
			}
		}
		if(!chkFlag){
			alert('Please select atleast one record to delete');
			return false;
		}
		
		 var conf = confirm('Do you really want to remove the records?');
		 
		 if(!conf) return false;
		 
		 return true;
	}
	
	function selectAll(){
		var count = document.getElementById('count').value;
		
		if(document.getElementById('paraFrm_chkAll').checked){
			for(var i=0; i<count; i++){
				if(document.getElementById("chkFlag"+i).value != "true"){
					document.getElementById('chk'+i).checked = true;
					document.getElementById(i).value = "Y";
				}
			}
		}else{
			for(var i=0; i<count; i++){
				if(document.getElementById("chkFlag"+i).value != "true"){
					document.getElementById('chk'+i).checked = '';
					document.getElementById(i).value = "N";
				}
			}
		}
	}
	
	function deselectAll(){
		var count = document.getElementById('count').value;
		
		for(var i=0; i<count; i++){
			if(document.getElementById("chkFlag"+i).value != "true" && 
				!document.getElementById('chk'+i).checked){
				document.getElementById('paraFrm_chkAll').checked = '';
			}
		}
	}
	
	function showRecord(fileName){
	
		document.getElementById('paraFrm').target ="_blank";
		document.getElementById('paraFrm').action = "PartnerPostResume_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	}
	
	function enableFields(){
		clearFields();
		
		document.getElementById("candName").style.display = 'none';
		document.getElementById("location1").style.display = '';
		document.getElementById("birthDate").style.display = '';
		document.getElementById("upload").style.display = '';
	
		document.getElementById("paraFrm_candFirstName").readOnly = false;
		document.getElementById("paraFrm_emailId").readOnly = false;
		document.getElementById("paraFrm_candLastName").readOnly = false;
		document.getElementById("paraFrm_contactNo").readOnly = false;
		document.getElementById("paraFrm_year").readOnly = false;
		document.getElementById("paraFrm_month").readOnly = false;
		document.getElementById("paraFrm_currentCtc").readOnly = false;
		document.getElementById("paraFrm_dob").readOnly = false;
		document.getElementById("paraFrm_expectedCtc").readOnly = false;
		document.getElementById("paraFrm_gender").disabled = false;
		document.getElementById("paraFrm_maritalStatus").disabled = false;
		//document.getElementById("paraFrm_relocate").disabled = false;
	}
	
	function disableFields(){
		clearFields();
		
		document.getElementById("candName").style.display = '';
		document.getElementById("location1").style.display = 'none';
		document.getElementById("birthDate").style.display = 'none';
		document.getElementById("upload").style.display = 'none';
		
		document.getElementById("paraFrm_candFirstName").readOnly = true;
		document.getElementById("paraFrm_emailId").readOnly = true;
		document.getElementById("paraFrm_candLastName").readOnly = true;
		document.getElementById("paraFrm_contactNo").readOnly = true;
		document.getElementById("paraFrm_year").readOnly = true;
		document.getElementById("paraFrm_month").readOnly = true;
		document.getElementById("paraFrm_currentCtc").readOnly = true;
		document.getElementById("paraFrm_dob").readOnly = true;
		document.getElementById("paraFrm_expectedCtc").readOnly = true;
		document.getElementById("paraFrm_gender").disabled = true;
		document.getElementById("paraFrm_maritalStatus").disabled = true;
		//document.getElementById("paraFrm_relocate").disabled="true";
	}
	
	function clearFields(){
		document.getElementById("paraFrm_candFirstName").value = "";
		document.getElementById("paraFrm_emailId").value = "";
		document.getElementById("paraFrm_candLastName").value = "";
		document.getElementById("paraFrm_contactNo").value = "";
		document.getElementById("paraFrm_year").value = "";
		document.getElementById("paraFrm_month").value = "";
		document.getElementById("paraFrm_currentCtc").value = "";
		document.getElementById("paraFrm_dob").value = "";
		document.getElementById("paraFrm_expectedCtc").value = "";
		document.getElementById("paraFrm_gender").value = "";
		document.getElementById("paraFrm_maritalStatus").value = "";
		document.getElementById("paraFrm_locationCode").value = "";
		document.getElementById("paraFrm_location").value = "";
		document.getElementById("paraFrm_state").value = "";
		document.getElementById("paraFrm_candCode").value = "";
		document.getElementById("paraFrm_uploadFileName").value = "";
	}
	
	function Homepagecancel()
	{
	document.getElementById('paraFrm').target='main';
		    document.getElementById('paraFrm').action = "<%=request.getContextPath()%>/common/RecruitmentHome_input.action";
		document.getElementById('paraFrm').submit();
		
	}
	
	function HomepagePostcancel()
	{
	
	document.getElementById('paraFrm').target='main';
		    document.getElementById('paraFrm').action = "PartnerPostResume_cancelTransaction.action";
		document.getElementById('paraFrm').submit();
		
	}
	
	
	
	function clearKeepInformed()
	{
		document.getElementById('paraFrm_employeeId').value="";
		document.getElementById('paraFrm_employeeToken').value="";
		document.getElementById('paraFrm_employeeName').value="";
	}
	 function callReportForDisp(reportType)
		{ 
				if(document.getElementById('count').value == 0){
					alert('Please add atleast one candidate in the list');
					return false;
				}
				document.getElementById('paraFrm').target='_blank';
				document.getElementById('paraFrm').action="PartnerPostResume_report.action";
				document.getElementById('paraFrm').submit(); 
		}
	
</script>
