<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="ClassRequest" validate="true" id="paraFrm"
	validate="true" theme="simple">

<!-- Hidden Fields -->
	<s:hidden name="level" />
	<s:hidden name="classCode" />
	<s:hidden name="uploadFileAdditionalInfoDocFlag" />
	<s:hidden name="dataPath" />
	<s:hidden name="hotelFile" />
	<s:hidden name="status" />
	<s:hidden name="employeeId" />
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
					Request </strong></td>

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

		


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="4" width="100%"><font size="1" align="center">The
					Purpose of this form is to add a new class to the Education
					Schedule.</font></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="class_name" id="class_name" ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="25%"><s:textfield size="25"
						theme="simple" maxlength="30" name="className"
						onkeypress="return isCharactersKey(event)" /></td>
					<td colspan="1" width="25%"><label class="set"
						name="class_desc" id="class_desc" ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label>
					<font color="red">*</font> :</td>

					<td colspan="1" width="30%"><s:textarea
						name="classDescription" cols="27" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_classDescription','class_desc','', '', '2000','2000');"></td>

					<s:hidden name="classId" />
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="25%"><s:hidden name="divId" /><s:textfield
						size="25" theme="simple" maxlength="30" name="classDivision"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ClassRequest_f9division.action');"
						id="ctrlHide"></td>
					<td colspan="1" width="25%"><label class="set" name="pre_req"
						id="pre_req" ondblclick="callShowDiv(this);"><%=label.get("pre_req")%></label><font
						color="red">*</font> :</td>

					<td colspan="1" width="30%"><s:textarea name="preRequisite"
						cols="27" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_preRequisite','pre_req','', '', '2000','2000');"></td>
					
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="instructor_id" id="instructor_id"
						ondblclick="callShowDiv(this);"><%=label.get("instructor_id")%></label><font color="red">*</font>
					:</td>
					<td colspan="3" width="80%"><s:textfield size="25"
						theme="simple" maxlength="20" name="instructorName"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="start_date" id="start_date" ondblclick="callShowDiv(this);"><%=label.get("start_date")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="25%"><s:textfield name="startDate"
						maxlength="10" size="25" theme="simple"
						cssStyle="background-color: #F2F2F2;" /><s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
					<td colspan="1" width="25%"><label class="set" name="end_date"
						id="end_date" ondblclick="callShowDiv(this);"><%=label.get("end_date")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield name="endDate"
						maxlength="10" size="25" theme="simple"
						cssStyle="background-color: #F2F2F2;" /><s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
				</tr>


				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="duration_of_class" id="duration_of_class"
						ondblclick="callShowDiv(this);"><%=label.get("duration_of_class")%></label><font color="red">*</font>
					:</td>
					<td colspan="3" width="80%"><s:textfield size="25"
						theme="simple" maxlength="8" name="durationofClass"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="start_time" id="start_time" ondblclick="callShowDiv(this);"><%=label.get("start_time")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="25%"><s:textfield
						label="%{startTime('startTime')}" theme="simple" name="startTime"
						size="19" onkeypress="return isNumWithCharKey(event)"
						maxlength="5" />(HH24:MM)</td>

					<td colspan="1" width="25%"><label class="set" name="end_time"
						id="end_time" ondblclick="callShowDiv(this);"><%=label.get("end_time")%></label><font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield
						label="%{endTime('endTime')}" theme="simple" name="endTime"
						size="19" onkeypress="return isNumWithCharKey(event)"
						maxlength="5" />(HH24:MM)</td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="class_room" id="class_room" ondblclick="callShowDiv(this);"><%=label.get("class_room")%></label><font color="red">*</font>
					:</td>
					<td colspan="1" width="25%"><s:textfield size="25"
						theme="simple" maxlength="8" name="classRoom" /></td>
					<td colspan="1" width="25%"><label class="set"
						name="max_no_of_student" id="max_no_of_student"
						ondblclick="callShowDiv(this);"><%=label.get("max_no_of_student")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						theme="simple" maxlength="5" name="maxnumOfStudents"
						onkeypress="return isNumberKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="location"
						id="location" ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="25%"><!--<s:hidden name="locId" />--><s:textfield
						size="25" theme="simple" maxlength="30" name="location"
						readonly="false" /> <!-- <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ClassRequest_f9location.action');"
						id="ctrlHide"> --></td>
					<td colspan="1" width="25%"><label class="set" name="address"
						id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label><font color="red">*</font>
					:</td>
					<td colspan="1" width="30%"><s:textarea name="address"
						cols="27" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_address','address','', '', '2000','2000');"></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="telephone" id="telephone" ondblclick="callShowDiv(this);"><%=label.get("telephone")%></label><font color="red">*</font>
					:</td>
					<td colspan="1" width="25%"><s:textfield size="25"
						theme="simple" maxlength="15" name="telephone"
						onkeypress="return isphoneNumber(event)" /></td>
					<td colspan="1" width="25%"><label class="set"
						name="contact_name" id="contact_name"
						ondblclick="callShowDiv(this);"><%=label.get("contact_name")%></label><font color="red">*</font>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						theme="simple" maxlength="30" name="contactName"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="hotel_info" id="hotel_info" ondblclick="callShowDiv(this);"><%=label.get("hotel_info")%></label>
					:</td>

					<td colspan="1" width="30%"><s:textarea
						name="hotelInformation" cols="27" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_hotelInformation','hotel_info','', '', '2000','2000');"></td>

					<td colspan="1" width="25%"><label class="set" name="comments"
						id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textarea name="comments"
						cols="27" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments','comments','', '', '2000','2000');"></td>

				</tr>

				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4" width="100%"><font color="red">Note: </font>Time
					Should be in 24-Hour Format</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- file attachment starts -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="100%" colspan="4"><b><label id="attch_file"
						name="attch_file" ondblclick="callShowDiv(this);"><%=label.get("attch_file")%></label></b>
					</td>
				</tr>

				<tr id="ctrlHide">
					<td width="30%" colspan="1"><label id="attach_here"
						name="attach_here" ondblclick="callShowDiv(this);"><%=label.get("attach_here")%></label>
					<font id="ctrlHide"></font>:</td>
					<td width="30%" colspan="1"><s:textfield
						name="hotelAttachments" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2; width: 275px;" /></td>
					<td width="20%" colspan="1"><input type="button" class="token"
						theme="simple" value="Select File" title="Select file"
						onclick="uploadFile('hotelAttachments');" /> <input type="button"
						class="token" theme="simple" value="Attach File"
						title="Attach File" onclick="attachFile();" /></td>
					<td colspan="1" width="20%">&nbsp;</td>


				</tr>

				<tr>
					<td width="20%" colspan="1"><label id="employee_file_attached"
						name="employee_file_attached" ondblclick="callShowDiv(this);"><%=label.get("employee_file_attached")%></label>
					</td>
					<td width="80%" colspan="3"><a href="#" id="ctrlShow"
						onclick="openAttachedFile()" style="cursor: hand;"> <label
						id="attachedFile" /></a></td>
				</tr>


				<!-- Completed By start -->

				<tr>
					<td class="formtext" width="15%"><label name="completedBy"
						id="completedBy" ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
					</label> :</td>
					<td height="22" width="25%"><s:hidden name="completedByID" />
					<s:hidden name="completedBy" /> <s:property value="completedBy" />
					</td>
					<td class="formtext" width="15%"><label name="completedDate"
						id="completedDate" ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
					</label> :</td>
					<td height="22" width="25%"><s:hidden name="completedDate" />
					<s:property value="completedDate" /></td>
				</tr>




				</td>
			</table>
			</td>
		</tr>
		<!--	<tr>
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
				</tr> -->

		<tr>
			<td colspan="4" width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
function draftFun()
{
var className =  document.getElementById('paraFrm_className').value; 
  
  var classDescription = document.getElementById('paraFrm_classDescription').value;
 
  var location =  document.getElementById('paraFrm_location').value; 
 
 var classDivision =  document.getElementById('paraFrm_classDivision').value; 	
 
 var instructorName =  document.getElementById('paraFrm_instructorName').value; 
 
  var preRequisite =  document.getElementById('paraFrm_preRequisite').value; 	
  
  var maxnumOfStudents = document.getElementById('paraFrm_maxnumOfStudents').value; 	
  
var durationofClass =  document.getElementById('paraFrm_durationofClass').value; 

var classRoom =  document.getElementById('paraFrm_classRoom').value; 

var maxnumOfStudents =  document.getElementById('paraFrm_maxnumOfStudents').value; 

var address =  document.getElementById('paraFrm_address').value; 

var telephone =  document.getElementById('paraFrm_telephone').value; 	

var contactName =  document.getElementById('paraFrm_contactName').value; 		
	
	
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
     
     if(classDescription=="")
     {
     alert("Class Description is required, enter N/A if there is None");
     return false;
     
     }
     
     	if(!(classDescription==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < classDescription.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(classDescription.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==classDescription.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
    
    if(classDivision=="")
    {
    alert("Please select Division");
    return false;
    }
    
    
     if(preRequisite=="")
     {
    alert("pre Requisite is required, enter N/A if there is None");
     return false;
     }
     if(!(preRequisite==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < preRequisite.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(preRequisite.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==preRequisite.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
    
    		
    if(instructorName=="")
    {
    alert("Please enter Instructor Name");
    return false;
    }		
    	if(!(instructorName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < instructorName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(instructorName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==instructorName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}		
    		
	//date validation start
	
	var startDate = trim(document.getElementById('paraFrm_startDate').value);
				
				
				if(startDate == "") {
				alert("Class Start Date is Required");
				//	alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
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
				alert("Class End Date is Required");
				
					//alert("Please select "+document.getElementById('end_date').innerHTML.toLowerCase());
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
			
		if(durationofClass==""){
		alert("Class Duration is required");
		return false;
		}
		
		if(!(durationofClass==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < durationofClass.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(durationofClass.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==durationofClass.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
   	//time validation start
	
	var startTime = trim(document.getElementById('paraFrm_startTime').value);
				
				
				if(startTime == "") {
					alert("Please enter "+document.getElementById('start_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startTime').focus();
					return false;
				}else { 
					if(!validateTimeD1('paraFrm_startTime',"start_time")){
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
					if(!validateTimeD1('paraFrm_endTime',"end_time")){
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
	
		if(classRoom==""){
		alert("Classroom number is required");
		return false;
		}
		
				if(!(classRoom==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < classRoom.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(classRoom.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==classRoom.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
		
		
   if(maxnumOfStudents=="")
   {
   alert("Enter the maximum number of students ")
   return false;
   }
   
   if(maxnumOfStudents==0)
   {
   alert("Max Number of Students is required");
   return false;
   }
   
   	if(!(maxnumOfStudents==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < maxnumOfStudents.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(maxnumOfStudents.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==maxnumOfStudents.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
   
      if(location==""){
			 alert("Facility Location is required");
		  return false;
		}
	
	if(!(location==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < location.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(location.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==location.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
  	
  	if(address==""){
  	alert("Facility Address is required");
  	return false;
  	
  	}
  	
  	if(!(address==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < address.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(address.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==address.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
  	
  	if(telephone=="")
  	{
  	alert("Facility Telephone Number is required");
  	return false;
  	}


if(contactName==""){
alert("Facility Contact Name is required");
return false;

}
  	
  		if(!(contactName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < contactName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(contactName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==contactName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
  	
  	
  	
  	
	/* if(hotelInformation==""){
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
  		
  	}	*/
	
/*	if(document.getElementById('attachedFile').innerHTML == '') {
				alert("Please select file for attachment");
				return false;
			}*/
  	
  	
	  
	  
	  document.getElementById('paraFrm_status').value='D';
     /* 	alert(document.getElementById('paraFrm_status').value);*/
      	   document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ClassRequest_saveDraftData.action';
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
	
	function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'C';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}
	
	/*	function backFun() {	
		document.getElementById('paraFrm').target = "_self";
		
		var forApproval = document.getElementById('forApproval').value;
		var forFlagComment=document.getElementById('forFlagComment').value;
		
		if(forApproval == 'true') {
			document.getElementById('paraFrm').action="ClassRequest_input.action";
		} else {
			document.getElementById('paraFrm').action="ClassRequest_input.action";
		}
		
		
		document.getElementById('paraFrm').submit();
	}*/
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequeste_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	
	function backtolistFun() {
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequest_input.action';
		document.getElementById('paraFrm').submit();
	
	}
	function printFun() {	
	window.print();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClassRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function sendforapprovalFun()
	{
	
  var className =  document.getElementById('paraFrm_className').value; 
  
  var classDescription = document.getElementById('paraFrm_classDescription').value;
 
  var location =  document.getElementById('paraFrm_location').value; 
 
 var classDivision =  document.getElementById('paraFrm_classDivision').value; 	
 
 var instructorName =  document.getElementById('paraFrm_instructorName').value; 
 
  var preRequisite =  document.getElementById('paraFrm_preRequisite').value; 	
  
  var maxnumOfStudents = document.getElementById('paraFrm_maxnumOfStudents').value; 	
  
var durationofClass =  document.getElementById('paraFrm_durationofClass').value; 

var classRoom =  document.getElementById('paraFrm_classRoom').value; 

var maxnumOfStudents =  document.getElementById('paraFrm_maxnumOfStudents').value; 

var address =  document.getElementById('paraFrm_address').value; 

var telephone =  document.getElementById('paraFrm_telephone').value; 	

var contactName =  document.getElementById('paraFrm_contactName').value; 	
		
     
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
     
     if(classDescription=="")
     {
     alert("Class Description is required, enter N/A if there is None");
     return false;
     
     }
     
     	if(!(classDescription==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < classDescription.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(classDescription.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==classDescription.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
    
    if(classDivision=="")
    {
    alert("Please select Division");
    return false;
    }
    
    
     if(preRequisite=="")
     {
    alert("pre Requisite is required, enter N/A if there is None");
     return false;
     }
     if(!(preRequisite==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < preRequisite.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(preRequisite.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==preRequisite.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
    
    		
    if(instructorName=="")
    {
    alert("Please enter Instructor Name");
    return false;
    }		
    	if(!(instructorName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < instructorName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(instructorName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==instructorName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}		
    		
	//date validation start
	
	var startDate = trim(document.getElementById('paraFrm_startDate').value);
				
				
				if(startDate == "") {
				alert("Class Start Date is Required");
				//	alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
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
				alert("Class End Date is Required");
				
					//alert("Please select "+document.getElementById('end_date').innerHTML.toLowerCase());
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
			
		if(durationofClass==""){
		alert("Class Duration is required");
		}
		
		if(!(durationofClass==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < durationofClass.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(durationofClass.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==durationofClass.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
	
   	//time validation start
	
	var startTime = trim(document.getElementById('paraFrm_startTime').value);
				
				
				if(startTime == "") {
					alert("Please enter "+document.getElementById('start_time').innerHTML.toLowerCase());
					document.getElementById('paraFrm_startTime').focus();
					return false;
				}else { 
					if(!validateTimeD1('paraFrm_startTime',"start_time")){
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
					if(!validateTimeD1('paraFrm_endTime',"end_time")){
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
	
		if(classRoom==""){
		alert("Classroom number is required");
		return false;
		}
		
				if(!(classRoom==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < classRoom.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(classRoom.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==classRoom.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
		
		
   if(maxnumOfStudents=="")
   {
   alert("Enter the maximum number of students ")
   return false;
   }
   
   if(maxnumOfStudents==0)
   {
   alert("Max Number of Students is required");
   return false;
   }
   
   	if(!(maxnumOfStudents==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < maxnumOfStudents.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(maxnumOfStudents.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==maxnumOfStudents.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
   
      if(location==""){
			 alert("Facility Location is required");
		  return false;
		}
	
	if(!(location==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < location.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(location.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==location.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
  	
  	if(address==""){
  	alert("Facility Address is required");
  	return false;
  	
  	}
  	
  	if(!(address==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < address.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(address.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==address.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
  	
  	if(telephone=="")
  	{
  	alert("Facility Telephone Number is required");
  	return false;
  	}


if(contactName==""){
alert("Facility Contact Name is required");
return false;

}
  	
  		if(!(contactName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < contactName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(contactName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==contactName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}
	
   	
	
	
/*	 if(hotelInformation==""){
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
  		
  	}	*/
	
/*	if(document.getElementById('attachedFile').innerHTML == '') {
				alert('Please select ' + document.getElementById('employee_file_attached').innerHTML.toLowerCase());
				return false;
			}*/
	 
	 
	        document.getElementById('paraFrm_status').value='P';
		    document.getElementById('paraFrm').target = "_self";
	  		document.getElementById('paraFrm').action = 'ClassRequest_approve.action';
	     	document.getElementById('paraFrm').submit(); 
     	
	
	}
	
	function approveFun() {
	var con=confirm('Do you want to approve the application ?');
	 if(con){
				document.getElementById('paraFrm').action = 'ClassRequest_approveData.action';
				document.getElementById('paraFrm').submit();
				document.getElementById("paraFrm").target = 'main';
				window.opener.viewList();
				window.close();
			}
		}
	
	function rejectFun() {
		document.getElementById('paraFrm').action = 'ClassRequest_reject.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		window.opener.viewList();
		window.close();
	}
	
	function sendbackFun() {
		document.getElementById('paraFrm').action = 'ClassRequest_sendBack.action';
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target = 'main';
		window.opener.viewList();
		window.close();
	
	}
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ClassRequest_viewAttachedProof.action?fileName="+fileName;
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
	
	
		/* phone validations*/
	function isphoneNumber(evt)
	{
	var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=39)
            {
              if (charCode > 31 && (charCode < 40 || charCode > 57))
                return false;
             }
             return true;
	}
	
//only char allowed	
function ischarsKey(evt)
{
 var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=64)
            {
             if ( (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
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
	 
	 //Text Area 
	 function enterMaxLength(Event, Object, MaxLen)
	{
	
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
	 
	/*Function only Characters in small & capital letters*/
function isCharactersKey(evt)
	 {
	
         var charCode = (evt.which) ? evt.which : event.keyCode
            
          //  alert("charCode--"+charCode);
            
            if(charCode!=65)
            {
             if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) &&(charCode==34 ) || (charCode==39) || (charCode==63) )
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
