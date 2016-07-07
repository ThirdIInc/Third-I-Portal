<!-- Nilesh D -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="ClassRequestApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">

<!-- Hidden Fields Start -->
	<s:hidden name="classReqAppCode" />
	<s:hidden name="uploadFileAdditionalInfoDocFlag" />
	<s:hidden name="dataPath" />
    <s:hidden name="hotelFile"/>
	<s:hidden name="applnStatus" />
	<s:hidden name="level" />
<s:hidden name="trackingNum" />
<!-- Hidden Fields End -->



	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Class
					Request Approval </strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" colspan="3">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
<s:if test ="appFlag">
		<tr>
			<td colspan="5">
			<table width="100%" class="formbg">
				<tr>
					<td colspan="1" width="30%"><b>Approver Comments </b><font
						id='ctrlHide' color="red">*</font></td>
					<td colspan="4" width="70%" id="ctrlShow"><s:textarea theme="simple"
						cols="70" rows="3" name="approverComments" id="approverComments" /></td>
				</tr>
			</table>
			</td>
		</tr></s:if>
		<tr>
			<td colspan="5">
			<table width="100%" class="formbg">
				<tr>
					<td colspan="1" width="10%" class="formth">Sr. No.</td>
					<td colspan="1" width="30%" class="formth">Approver Name</td>
					<td colspan="1" width="35%" class="formth">Comments</td>
					<td colspan="1" width="15%" class="formth">Date</td>
					<td colspan="1" width="10%" class="formth">Status</td>
				</tr>
				<%
				int count = 0;
				%>
				<s:iterator value="approverCommentList">
					<tr>
						<td colspan="1" width="10%" class="sortableTD" align="center"><%=++count%></td>
						<td colspan="1" width="30%" class="sortableTD"><s:property
							value="apprName" /></td>
						<td colspan="1" width="35%" class="sortableTD"><s:property
							value="apprComments" /></td>
						<td colspan="1" width="15%" class="sortableTD" align="center"><s:property
							value="apprDate" /></td>
						<td colspan="1" width="10%" class="sortableTD"><s:property
							value="apprStatus" /></td>
					</tr>
				</s:iterator>
				<%
				if (count == 0) {
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




		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="class_name" id="class_name" ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield name="className" /></td>
					<td colspan="1" width="30%"><label class="set"
						name="class_desc" id="class_desc" ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textarea
						name="classDescription" cols="27" rows="3" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td colspan="1" width="30%"><s:hidden name="divId" /><s:textfield
						size="25" theme="simple" maxlength="30" name="classDivision"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ClassRequest_f9division.action');"
						id="ctrlHide"></td>
					<td colspan="1" width="30%"><label class="set" name="pre_req"
						id="pre_req" ondblclick="callShowDiv(this);"><%=label.get("pre_req")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textfield size="25"
						theme="simple" maxlength="30" name="preRequisite" /></td>
				</tr>


				<tr>
					<td colspan="1" width="30%"><label class="set"
						name="instructor_id" id="instructor_id"
						ondblclick="callShowDiv(this);"><%=label.get("instructor_id")%></label>
					:</td>
					<td colspan="3" width="70%"><s:textfield size="25"
						theme="simple" maxlength="30" name="instructorId" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="start_date" id="start_date" ondblclick="callShowDiv(this);"><%=label.get("start_date")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield name="startDate"
						size="25" theme="simple" cssStyle="background-color: #F2F2F2;" /><s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
					<td colspan="1" width="30%"><label class="set" name="end_date"
						id="end_date" ondblclick="callShowDiv(this);"><%=label.get("end_date")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="20%"><s:textfield name="endDate"
						size="25" theme="simple" cssStyle="background-color: #F2F2F2;" /><s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
				</tr>


				<tr>
					<td colspan="1" width="30%"><label class="set"
						name="duration_of_class" id="duration_of_class"
						ondblclick="callShowDiv(this);"><%=label.get("duration_of_class")%></label>
					:</td>
					<td colspan="3" width="70%"><s:textfield size="25"
						theme="simple" maxlength="30" name="durationofClass" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="start_time" id="start_time" ondblclick="callShowDiv(this);"><%=label.get("start_time")%></label>
					</td>
					<td colspan="1" width="30%"><s:textfield
						label="%{startTime('startTime')}" theme="simple" name="startTime"
						size="12" />(HH24:MM)</td>

					<td colspan="1" width="30%"><label class="set" name="end_time"
						id="end_time" ondblclick="callShowDiv(this);"><%=label.get("end_time")%></label>
					</td>
					<td colspan="1" width="20%"><s:textfield
						label="%{endTime('endTime')}" theme="simple" name="endTime"
						size="12" />(HH24:MM)</td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="class_room" id="class_room" ondblclick="callShowDiv(this);"><%=label.get("class_room")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						theme="simple" maxlength="30" name="classRoom"
						onkeypress="return isNumberKey(event)" /></td>
					<td colspan="1" width="30%"><label class="set"
						name="max_no_of_student" id="max_no_of_student"
						ondblclick="callShowDiv(this);"><%=label.get("max_no_of_student")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textfield size="25"
						theme="simple" maxlength="30" name="maxnumOfStudents"
						onkeypress="return isNumberKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="location"
						id="location" ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:hidden name="locId" /><s:textfield
						size="25" theme="simple" maxlength="30" name="location"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ClassRequest_f9location.action');"
						id="ctrlHide"></td>
					<td colspan="1" width="30%"><label class="set" name="address"
						id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textfield size="25"
						theme="simple" maxlength="30" name="address" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="telephone" id="telephone" ondblclick="callShowDiv(this);"><%=label.get("telephone")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						theme="simple" maxlength="30" name="telephone" /></td>
					<td colspan="1" width="30%"><label class="set"
						name="contact_name" id="contact_name"
						ondblclick="callShowDiv(this);"><%=label.get("contact_name")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textfield size="25"
						theme="simple" maxlength="30" name="contactName" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="hotel_info" id="hotel_info" ondblclick="callShowDiv(this);"><%=label.get("hotel_info")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textarea
						name="hotelInformation" cols="27" rows="3" /></td>

					<td colspan="1" width="30%"><label class="set" name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
					:</td>
					<td colspan="1" width="20%"><s:textarea name="comments"
						cols="27" rows="3" /></td>
				</tr>
				
								<!-- file attachment starts -->


				<tr>
					<td width="100%" colspan="4"> <b><label
						id="attch_file" name="attch_file" ondblclick="callShowDiv(this);"><%=label.get("attch_file")%></label></b>
					</td>
				</tr>

				<tr id="ctrlHide">
					<td width="25%" colspan="1"><label id="attach_here"
						name="attach_here" ondblclick="callShowDiv(this);"><%=label.get("attach_here")%></label>
					<font id="ctrlHide" color="red">*</font></td>
					<td width="25%" colspan="1"><s:textfield
						name="hotelAttachments" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2; width: 275px;" /></td>
					<td width="25%" colspan="1"><input type="button" class="token"
						theme="simple" value="Select File" title="Select file"
						onclick="uploadFile('hotelAttachments');" /> <input type="button"
						class="token" theme="simple" value="Attach File"
						title="Attach File" onclick="attachFile();" /></td>
					<td colspan="1" width="25%">&nbsp;</td>


				</tr>

				<tr>
					<td width="30%" colspan="1"><label id="employee_file_attached"
						name="employee_file_attached" ondblclick="callShowDiv(this);"><%=label.get("employee_file_attached")%></label>
					</td>
					<td width="70%" colspan="3"><a href="#" id="ctrlShow"
						onclick="openAttachedFile()" style="cursor: hand;"> <label
						id="attachedFile" /></a></td>
				</tr>
				
				
				<!-- Completed By start -->
				
				<tr>
					<td class="formtext" width="15%">
						<label name="completedBy" id="completedBy"
							ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
						</label> :
					</td>
					<td height="22" width="25%">
						<s:hidden name="completedByID"/>						
						<s:hidden name="completedBy" />
						<s:property value="completedBy"/>
					</td>
					<td class="formtext" width="15%">
						<label name="completedDate" id="completedDate"
							ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
						</label> :
					</td>
					<td height="22" width="25%">	
						<s:hidden name="completedDate"/>
						<s:property value="completedDate"/>													
					</td>
				</tr>
				
				
				<tr>


					<td colspan="1" width="20%">Status:</td>
					<td colspan="1" width="30%"><s:hidden name="applnActualStatus"></s:hidden>
					<s:select headerKey="" headerValue="--Select--" name="applnStatus"
						disabled="true"
						list="#{'D':'Draft','P':'PENDING','B':'Sent Back','A':'APPROVED','R':'REJECTED',
						'N':'CANCELLED','F':'FORWARDED','C':'APPLIED FOR CANCELLATION','X':'Cancellation Approved','Z':'Cancellation Rejected'}"
						cssStyle="width=130" /></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- 	<tr>
			<td width="20%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><b><label class="set" name="from_app" id="from_app"
						ondblclick="callShowDiv(this);"><%=label.get("from_app")%></label></b>
					</td>
				</tr>
				<tr>
					<td><b><label class="set" name="first_app" id="first_app"
						ondblclick="callShowDiv(this);"><%=label.get("first_app")%></label></b>
					:</td>

					<td width="80%" colspan="3"><s:hidden name="firstApproverCode" />
					<s:property value="appToken" />&nbsp;&nbsp;&nbsp; <s:property
						value="approverName" /></td>
				</tr>
				<s:if test="secondAppFlag">
					<tr>
						<td><b><label class="set" name="second_app"
							id="second_app" ondblclick="callShowDiv(this);"><%=label.get("second_app")%></label></b>
						:</td>

						<td width="80%" colspan="3"><s:hidden name="secondApproverId" />
						<s:property value="secondApproverToken" />&nbsp;&nbsp;&nbsp; <s:property
							value="secondApproverName" /></td>
					</tr>
				</s:if>

				<tr>
					<td><b><label class="set" name="app_name" id="app_name"
						ondblclick="callShowDiv(this);"><%=label.get("app_name")%></label>:</b></td>

					<td width="80%" colspan="3"><s:textfield name="approverToken"
						size="20" theme="simple" readonly="true" /> <s:textfield
						name="selectapproverName" size="96" theme="simple" readonly="true" />
					<s:hidden name="approverCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ClassRequest_f9approver.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>  -->

		<tr>
			<td colspan="4" width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>
	</td>
	</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

function draftFun()
{
var className =  document.getElementById('paraFrm_className').value; 
  var startDate =  document.getElementById('paraFrm_startDate').value; 
  var endDate =  document.getElementById('paraFrm_endDate').value; 
  var location =  document.getElementById('paraFrm_location').value; 
  var hotelInformation =  document.getElementById('paraFrm_hotelInformation').value; 	
  var hotelAttachments =  document.getElementById('paraFrm_hotelAttachments').value; 
	
	
	 if(className==""){
			 alert("Please Enter Class Name");
		  return false;
		}
	
      	
   	if(!(className==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < className.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(className.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==className.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
     
    		
	//date validation start
	
	var startDate = trim(document.getElementById('paraFrm_startDate').value);
				
				
				if(startDate == "") {
					alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_startDate',"start_date")){
							document.getElementById('paraFrm_startDate').focus();
							return false;   	
	   					}
				}
				
				var endDate = trim(document.getElementById('paraFrm_endDate').value);
				if(endDate == "") {
					alert("Please select "+document.getElementById('end_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_endDate').focus();
					return false;
				}else {			
					if(!validateDate('paraFrm_endDate',"end_date")){
						document.getElementById('paraFrm_endDate').focus();
						return false;   	
	   				}		
				}
				
				if(!startDate == "" && !endDate == ""){
				//alert("if");
				if(!dateDifferenceEqual(startDate,endDate,'paraFrm_startDate','start_date','end_date')){
						document.getElementById('paraFrm_endDate').focus();
						return false;   	
	   				}		
				
	}
	
		//date validation end
	
   	//time validation start
	
	var startTime = trim(document.getElementById('paraFrm_startTime').value);
				
				
				if(startTime == "") {
					alert("Please enter "+document.getElementById('start_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startTime').focus();
					return false;
				}else { 
					if(!validateTime('paraFrm_startTime',"start_time")){
							document.getElementById('paraFrm_startTime').focus();
							return false;   	
	   					}
				}
				
				var endTime = trim(document.getElementById('paraFrm_endTime').value);
				if(endTime == "") {
					alert("Please enter "+document.getElementById('end_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_endTime').focus();
					return false;
				}else {			
					if(!validateTime('paraFrm_endTime',"end_time")){
						document.getElementById('paraFrm_endTime').focus();
						return false;   	
	   				}		
				}
				
				if(!startTime == "" && !endTime == ""){
				
				if(!timeDifference(startTime,endTime,'paraFrm_startTime','start_time','end_time')){
						document.getElementById('paraFrm_endTime').focus();
						return false;   	
	   				}		
	}
		//time validation end
   
	
      if(location==""){
			 alert("Please Select Location");
		  return false;
		}
	
	
	 if(hotelInformation==""){
			 alert("Please Enter Hotel Information");
		  return false;
		}
	
		if(!(hotelInformation==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < hotelInformation.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(hotelInformation.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==hotelInformation.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
	 if(hotelAttachments==""){
			 alert("Please Select File for attachment");
		  return false;
		}
	
	
  	
  	 
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ClassRequestApproval_saveDraftData.action';
		document.getElementById('paraFrm').submit();
     
  }	 



  /*  function saveFun()
{
        document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ClassRequest_saveVendorData.action';
		document.getElementById('paraFrm').submit();
  	  	
  			
  }	 */
	
	
function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ClassRequest_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
	
	function cancelFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	
	function cancelapplicationFun() {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ClassRequestApproval_updateStatus.action';
			document.getElementById('paraFrm').submit();
		}
	
		
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequestApproval_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function backtolistFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequestApproval_cancel.action';
		document.getElementById('paraFrm').submit();
	
	}
	function printFun() {	
	window.print();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequestApproval_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function sendforapprovalFun()
	{
	   var className =  document.getElementById('paraFrm_className').value; 
  var startDate =  document.getElementById('paraFrm_startDate').value; 
  var endDate =  document.getElementById('paraFrm_endDate').value; 
  var location =  document.getElementById('paraFrm_location').value; 
  var hotelInformation =  document.getElementById('paraFrm_hotelInformation').value; 	
  var hotelAttachments =  document.getElementById('paraFrm_hotelAttachments').value; 
	
	
	 if(className==""){
			 alert("Please Enter Class Name");
		  return false;
		}
	
      	
   	if(!(className==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < className.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(className.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==className.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
     
   		
	//date validation start
	
	var startDate = trim(document.getElementById('paraFrm_startDate').value);
				
				
				if(startDate == "") {
					alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_startDate',"start_date")){
							document.getElementById('paraFrm_startDate').focus();
							return false;   	
	   					}
				}
				
				var endDate = trim(document.getElementById('paraFrm_endDate').value);
				if(endDate == "") {
					alert("Please select "+document.getElementById('end_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_endDate').focus();
					return false;
				}else {			
					if(!validateDate('paraFrm_endDate',"end_date")){
						document.getElementById('paraFrm_endDate').focus();
						return false;   	
	   				}		
				}
				
				if(!startDate == "" && !endDate == ""){
				//alert("if");
				if(!dateDifferenceEqual(startDate,endDate,'paraFrm_startDate','start_date','end_date')){
						document.getElementById('paraFrm_endDate').focus();
						return false;   	
	   				}		
				
	}
	
		//date validation end
	
   	//time validation start
	
	var startTime = trim(document.getElementById('paraFrm_startTime').value);
				
				
				if(startTime == "") {
					alert("Please enter "+document.getElementById('start_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startTime').focus();
					return false;
				}else { 
					if(!validateTime('paraFrm_startTime',"start_time")){
							document.getElementById('paraFrm_startTime').focus();
							return false;   	
	   					}
				}
				
				var endTime = trim(document.getElementById('paraFrm_endTime').value);
				if(endTime == "") {
					alert("Please enter "+document.getElementById('end_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_endTime').focus();
					return false;
				}else {			
					if(!validateTime('paraFrm_endTime',"end_time")){
						document.getElementById('paraFrm_endTime').focus();
						return false;   	
	   				}		
				}
				
				if(!startTime == "" && !endTime == ""){
				
				if(!timeDifference(startTime,endTime,'paraFrm_startTime','start_time','end_time')){
						document.getElementById('paraFrm_endTime').focus();
						return false;   	
	   				}		
	}
		//time validation end
   

	
	
      if(location==""){
			 alert("Please Select Location");
		  return false;
		}
	
	
	 if(hotelInformation==""){
			 alert("Please Enter Hotel Information");
		  return false;
		}
	
		if(!(hotelInformation==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < hotelInformation.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(hotelInformation.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==hotelInformation.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
	 if(hotelAttachments==""){
			 alert("Please Select File for attachment");
		  return false;
		}
	
		    document.getElementById('paraFrm').target = "_self";
	  		document.getElementById('paraFrm').action = 'ClassRequestApproval_approve.action';
	     	document.getElementById('paraFrm').submit(); 
     	   
	
	
	}
	
	
 function approveFun() 
	{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		
		if(document.getElementById('paraFrm_applnStatus').value == 'P')
		{
			document.getElementById('paraFrm_applnStatus').value = 'A';
		}
		
		if(document.getElementById('paraFrm_applnStatus').value == 'C')
		{
			document.getElementById('paraFrm_applnStatus').value = 'X';
		}
			
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'ClassRequestApproval_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}
			
	
	function rejectFun() {
		var con=confirm('Do you want to reject the application ?');
	 if(con){
		
		if(document.getElementById('paraFrm_applnStatus').value == 'C')
		{
			document.getElementById('paraFrm_applnStatus').value = 'Z';
		}else {
			document.getElementById('paraFrm_applnStatus').value = 'R';	
		}
		
		document.getElementById('paraFrm').action = 'ClassRequestApproval_rejectApplication.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		
    	}
	}
	function sendbackFun() {
	var con=confirm('Do you want to send back the application ?');
	 if(con){
	    document.getElementById('paraFrm_applnStatus').value = 'B';	
		document.getElementById('paraFrm').action = 'ClassRequestApproval_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		window.opener.viewList();
		window.close();
	}
	}
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ClassRequestApproval_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function openAttachedFile() {
		document.getElementById('paraFrm').action = 'ClassRequest_openAttachedFile.action';  
		document.getElementById('paraFrm').submit();
	}
	
	setAddedFile();
	
	function setAddedFile() {
		var hotelFile = document.getElementById('paraFrm_hotelFile').value;
		/*alert(hotelFile);*/
		document.getElementById('attachedFile').innerHTML =hotelFile;
	}
	
	
	function uploadFile(fieldName) {
		/*alert("fieldName");*/
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
		
		}
	
	function attachFile() {
	try{
	
	
		var hotelAttachments = document.getElementById('paraFrm_hotelAttachments').value;
	
	
		if(hotelAttachments == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile').innerHTML = hotelAttachments;
			document.getElementById('paraFrm_hotelFile').value = hotelAttachments;
			document.getElementById('paraFrm_hotelAttachments').value = '';
		}
		
		} catch(e){
		alert(e);
		
		}
	}
	
	
/*	function uploadAdditionalFile(fieldName) {
		
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
		
		}*/
	
	
function validateDate(fld) {
var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
if (!((fld.match(RegExPattern)) && (fld!=''))){
    return false;
}
    return true;
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
	 
	 function charactersOnly(e){
		/* alert("char only");*/
	document.onkeypress = charactersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91)|| key == 8 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
		
		 
</script>


