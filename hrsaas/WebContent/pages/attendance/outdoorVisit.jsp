<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="OutdoorVisit" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Outdoor
					Visit </strong><s:hidden name="flagHrs" /></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- TABLE FOR APPROVER COMMENTS BEGINS -->

		<tr>
			<td class="formtext" colspan="7">
			<table width="100%" cellpadding="1" cellspacing="1" class="sortable">
				<tr>
					<s:if test="isApprove">
						<td width="30%"><label name="approverComm" id="approverComm"
							ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label>
						<font color="red">*</font>:</td>
					</s:if>
					<td width="70%" id="ctrlShow"><s:if
						test="approverCommentsFlag">
						<s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" />
						</textarea>
					</s:if></td>
				</tr>

			</table>
			</td>
		</tr>



		<s:if test="approverCommentsListFlag">
			<tr>
				<td class="formtext" colspan="7">
				<table width="100%" cellpadding="1" cellspacing="1" class="sortable">
					<tr>
						<td width="5%" class="formth"><label id="sno" name="sno"
							ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
						<td width="35%" class="formth"><label id="appName"
							name="appName" ondblclick="callShowDiv(this);"><%=label.get("appName")%></label></td>
						<td width="20%" class="formth"><label id="appDate"
							name="appDate" ondblclick="callShowDiv(this);"><%=label.get("appDate")%></label></td>
						<td width="40%" class="formth"><label id="comment"
							name="comment" ondblclick="callShowDiv(this);"><%=label.get("comment")%></label></td>
					</tr>
					<% int j = 0;%>
					<s:iterator value="approveList">
						<tr>
							<td class="sortableTD" width="5%" align="center"><%=++j%></td>
							<td class="sortableTD" width="35%"><s:property
								value="apprName" /></td>
							<td class="sortableTD" width="20%" align="center"><s:property
								value="apprDate" /></td>
							<td class="sortableTD" width="40%" align="left"><s:property
								value="apprComments" /></td>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- TABLE FOR APPROVER COMMENTS ENDS -->


		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<s:hidden name="outvCode" />
				<tr>
					<td width="20%" colspan="1"><label id="employee"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
						color="red">*</font>:</td>
					<td width="75%" colspan="3"><s:textfield name="outdoor.eToken"
						size="25" cssStyle="background-color: #F2F2F2;" /> <s:textfield
						theme="simple" name="outdoor.empName" readonly="true" size="73"
						cssStyle="background-color: #F2F2F2;" /> <s:if
						test="%{generalFlag}"></s:if> <s:else>
						<img src="../pages/images/search2.gif" id="ctrlHide"
							class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'OutdoorVisit_f9empaction.action');">
					</s:else></td>
					<s:hidden name="ecode" />
					<s:hidden name="outdoor.empAppr" />
				</tr>

				<tr>
					<td width="20%"><label id="date" name="date"
						ondblclick="callShowDiv(this);"><%=label.get("date")%></label><font
						color="red">*</font>:</td>
					<td width="30%"><s:textfield size="25" name="outdoor.outDate"
						onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_outdoor_outDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
							class="iconImage" height="16" align="absmiddle" width="16">
					</s:a></td>
					
					<td width="50%" colspan="2">
						<input type="button" id="ctrlShow" name="View Attendance" value="View Attendance" class="token" onclick="showAttendanceFunction();">
					</td>
					
				</tr>

				<tr>
					<td width="20%" colspan="1"><label id="sTime" name="sTime"
						ondblclick="callShowDiv(this);"><%=label.get("sTime")%></label>
					[HH:MI]<font color="red">*</font>:</td>
					<td width="30%" colspan="1"><s:textfield theme="simple"
						name="outdoor.frTime" size="25" maxlength="5"
						onkeypress="return numbersWithColon();" /></td>
					<td width="50%" colspan="2"><label id="eTime" name="eTime"
						ondblclick="callShowDiv(this);"><%=label.get("eTime")%></label>
					[HH:MI]<font color="red">*</font>: <s:textfield theme="simple"
						size="25" name="outdoor.toTime" maxlength="5"
						onkeypress="return numbersWithColon();" /></td>
				</tr>

				<tr>
					<td width="20%"><label id="location" name="location"
						ondblclick="callShowDiv(this);"><%=label.get("location")%></label><font
						color="red">*</font>:</td>
					<td width="30%"><s:textfield theme="simple" maxlength="90"
						name="outdoor.outLoc" size="25" /></td>
				</tr>

				<tr>
					<td width="20%"><label id="purpose" name="purpose"
						ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label><font
						color="red">*</font>:</td>
					<td width="30%"><s:textarea cols="50" rows="4"
						name="outdoor.purpose" onkeyup="return callLength('isappcount');" />
					</td>
					<td width="50%" colspan="2"><s:hidden name="isappcount" /></td>
				</tr>

				<tr>
					<td width="20%"><label id="applnStatus" name="applnStatus"
						ondblclick="callShowDiv(this);"><%=label.get("applnStatus")%></label>
					:</td>
					<td width="30%">
					<s:select theme="simple"
						name="outdoor.applnStatus" disabled="true" id="ctrlShow"
						list="#{'':'New Application', 'Dr':'Draft', 'Pn':'Pending', 'Bk':'Sent Back', 'Ap':'Approved', 'Rj':'Rejected', 'Fr':'Forwarded'}" />
					<s:hidden name="hiddenStatus" /></td>

				</tr>

			</table>
			</td>
		</tr>

		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

					<td width="11%" nowrap="nowrap"><strong>Keep Informed
					To : </strong></td>
					<td width="13%"><s:hidden name="keepHidden" /> <s:hidden
						name="informCode" /> <s:hidden name="informToken" /> <s:textfield
						name="informName" size="30" readonly="true" /></td>
					<td width="5%" colspan="1"><s:if
						test="%{applnStatus=='Pn' || applnStatus=='Ap' || applnStatus=='Rj' || applnStatus=='Fr'}"></s:if>
					<s:else>
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:callsF9(500,325,'OutdoorVisit_f9informTo.action');" />
					</s:else></td>
					<td width="15%"><s:if
						test="%{applnStatus=='Pn' || applnStatus=='Ap' || applnStatus=='Rj'|| applnStatus=='Fr'}"></s:if>
					<s:else>
						<s:submit value="Add" cssClass="add"
							action="OutdoorVisit_addKeepInfo" onclick="return callAddInfo();"></s:submit>
					</s:else></td>
				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="applicationApproverIteratorList">
								<tr>
									<td><s:hidden name="applicationApproverName" /> <STRONG>
									<s:property value="srNoIterator" /> </STRONG> <s:property
										value="applicationApproverName" /></td>
								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>
				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
						int kep = 0;
						%>
						<s:iterator value="informList">
							<tr>
								<td colspan="3" align="right"><%=kep + 1%> ) <s:hidden
									name="keepInformCode" /></td>
								<td><s:property value="keepInform" /><s:hidden
									name="keepInform" /></td>

								<td id="ctrlHide"><b> <a href="#"
									onclick="callRemoveKeep('<%=kep%>')">Remove</a></b></td>
							</tr>
							<%
							kep++;
							%>
						</s:iterator>
					</table>
					</td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- KEEP INFORMED LIST TABLE  ENDS -->
		
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

	<s:hidden name="status" />
	<s:hidden name="checkApproveRejectStatus" />
	<s:hidden name="applicationLevel" />

</s:form>



<script type="text/javascript">
//callOnLoadFunction();

/*
function callOnLoadFunction(){
	document.getElementById('attendanceRecord').style.display = 'none';
	document.getElementById('showAttendanceFlag').style.display = '';
	document.getElementById('hideAttendanceFlag').style.display = 'none';
}
*/

function approveFun(){
	checkAppStatus('A');
}

function rejectFun(){
	checkAppStatus('R');
}

function sendbackFun(){
	checkAppStatus('B');
}

	 function checkAppStatus(id)
  {
  	try{
  	var approverComments = document.getElementById('paraFrm_approverComments').value;
   	var conf;
   	 var fieldName=["paraFrm_approverComments"];
		    var lableName=["approverComm"];
		    var flag = ["enter"];
		   	if(!(validateBlank(fieldName,lableName,flag))){
				return false;
		    }
		    
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
       conf=confirm("Do you really want to approve this application ?");
      if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
       conf=confirm("Do you really want to reject this application ?");
     
       /*
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       {
		     var fieldName=["paraFrm_approverComments"];
		    var lableName=["approverComm"];
		    var flag = ["enter"];
		    	if(!(validateBlank(fieldName,lableName,flag))){
					return false;
		        }
		    
       }
       */
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       conf=confirm("Do you really want to send back this application ?");
       
        
 		 if(conf)
				{ 
				 	//obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="OutdoorApproval_approveRejSendBackApplication.action";
		  			document.getElementById("paraFrm").submit();
		  			window.close();
				 }
				 else
				 {
				 return false; 
  				}
  				
  			}catch(e){alert(e);}	
  		return true; 		
  }
  


			function callAddInfo(){
				var info=document.getElementById('paraFrm_informName').value;
				if(info==''){
				alert('Please select keep inform to');
				return false;
				}				
				}

		      function callRemoveKeep(id){
				var r=confirm('Do you really want to remove the record?');
				if(r==false){
				return false;
			}else{
		 		document.getElementById('paraFrm_keepHidden').value=id;
	   		document.getElementById("paraFrm").action="OutdoorVisit_removeKeepInfo.action";	  
	   	 document.getElementById("paraFrm").submit();
	    }
   }




	function validate(type) {
 		if(type == "update") {
			if(document.getElementById('paraFrm_outvCode').value == "") {
  				alert("Please select a record to update.");
  				return false;
			}
  			if(!(document.getElementById('paraFrm_status').value == "P" || 
  			document.getElementById('paraFrm_status').value == "")) {
				alert("You can modify only pending Applications.");
				return false;
			}
		}
	
    	var name = ["paraFrm_outdoor_empName", "paraFrm_outdoor_outDate", "paraFrm_outdoor_frTime", "paraFrm_outdoor_toTime", "paraFrm_outdoor_outLoc", "paraFrm_outdoor_purpose"];
		var label = ["employee", "date", "sTime", "eTime", "location", "purpose"];
		var flag = ["select", "enter", "enter", "enter", "enter", "enter"];
	
		if(!validateBlank(name, label, flag)) {
    		return false;
    	}
    	
    	if(!validateDate('paraFrm_outdoor_outDate', 'date')) {
			return false;
		}
		     
		var ftime = document.getElementById('paraFrm_outdoor_frTime').value;    
		var totime = document.getElementById('paraFrm_outdoor_toTime').value;		
	
		if(!validateTime('paraFrm_outdoor_frTime', 'sTime')) {
			document.getElementById('paraFrm_outdoor_frTime').value = "";
			return false;
		}
		
		if(!validateTime('paraFrm_outdoor_toTime', 'eTime')) {
			document.getElementById('paraFrm_outdoor_toTime').value = "";
			return false;
		}
		     
      	if(!timeDifference(ftime,totime,'paraFrm_outdoor_toTime', 'sTime', 'eTime')) {
			return false;
		}
		
		var cmt = document.getElementById('paraFrm_outdoor_purpose').value;
		var remain = 300 - eval(cmt.length);
					
		if(eval(remain) < 0) {
			alert("Maximum characters for purpose field is 300 Remove Extra " + remain + " Characters.");
			return false;
		}
		
		if(type == "add") {
			if(document.getElementById('paraFrm_outvCode').value == "") {
				return true;
			} else {
		  		alert("Please click on 'Update' button");
		  		return false;
		 	}
		}
	}
 
/* 
 	function deleteFun() {
  		if(document.getElementById('paraFrm_outvCode').value == "") {
  			alert("Please select a record to delete.");
  			return false;
		}
  		
  		if(!(document.getElementById('paraFrm_outdoor_status').value == "P" || 
  		document.getElementById('paraFrm_outdoor_status').value == "")) {
			alert("You Can not Delete Approved or Rejected Outdoor visit Applications.");
			return false;
		}
	}
*/	

		function deleteFun() {
			var con = confirm("Do you really want to delete this application?");
			if(con){
				document.getElementById('paraFrm').target = "_self";
      			document.getElementById('paraFrm').action='OutdoorVisit_deleteFunction.action';
	  			document.getElementById('paraFrm').submit();	
			}
		}
 	function callLength(type) { 
		if(type == 'isappcount') {
			var cmt = document.getElementById('paraFrm_outdoor_purpose').value;
			var remain = 300 - eval(cmt.length);
			document.getElementById('paraFrm_isappcount').value = remain;
			
			if(eval(remain) < 0) {
				document.getElementById('paraFrm_outdoor_purpose').style.background = '#FFFF99';
			} else {
				document.getElementById('paraFrm_outdoor_purpose').style.background = '#FFFFFF';
			}
		}
 	}
 	
 function draftFun(){	
	try	{
			var empNameVar = trim(document.getElementById('paraFrm_outdoor_empName').value);
			if(empNameVar==""){	
				alert("Please select employee.");
				document.getElementById('paraFrm_outdoor_empName').focus();
				return false;
			}
			
			var visitDateVar = trim(document.getElementById('paraFrm_outdoor_outDate').value);
			if(visitDateVar == "") {
				alert("Please select/enter "+document.getElementById('date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_outdoor_outDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_outdoor_outDate',"date")){
						document.getElementById('paraFrm_outdoor_outDate').focus();
						return false;   	
   					}
			}
			
			var ftime = document.getElementById('paraFrm_outdoor_frTime').value;    
			var totime = document.getElementById('paraFrm_outdoor_toTime').value;		
			
			if(ftime==""){
				alert("Please enter start time");
				document.getElementById('paraFrm_outdoor_frTime').focus();    
				return false;
			}else if(!validateTime('paraFrm_outdoor_frTime', 'sTime')) {
				document.getElementById('paraFrm_outdoor_frTime').value = "";
				return false;
			}
			
			
			if(totime==""){
				alert("Please enter end time");
				document.getElementById('paraFrm_outdoor_toTime').focus();    
				return false;
			}else if(!validateTime('paraFrm_outdoor_toTime', 'eTime')) {
				document.getElementById('paraFrm_outdoor_toTime').value = "";
				return false;
			}
		     
      		if(!timeDifference(ftime,totime,'paraFrm_outdoor_toTime', 'sTime', 'eTime')) {
				return false;
			}
		
			var cmt = document.getElementById('paraFrm_outdoor_purpose').value;
			var remain = 300 - eval(cmt.length);
					
			if(eval(remain) < 0) {
					alert("Maximum characters for purpose field is 300 Remove Extra " + remain + " Characters.");
				return false;
			}
		
			var outLocVar = trim(document.getElementById('paraFrm_outdoor_outLoc').value);
			if(outLocVar==""){
				alert("Please enter location");
				document.getElementById('paraFrm_outdoor_outLoc').focus();
				return false;
			}
			
			var purposeVar = trim(document.getElementById('paraFrm_outdoor_purpose').value);
			if(purposeVar==""){
				alert("Please enter purpose for the visit.");
				document.getElementById('paraFrm_outdoor_purpose').focus();
				return false;
			}
			
			document.getElementById('paraFrm_status').value = 'D';
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action='OutdoorVisit_draftFunction.action';
	  		document.getElementById('paraFrm').submit();	
 	}catch(e){
 		alert(e);
 	}
 }	
 
 
 function sendforapprovalFun(){	
	try	{
			var empNameVar = trim(document.getElementById('paraFrm_outdoor_empName').value);
			if(empNameVar==""){	
				alert("Please select employee.");
				document.getElementById('paraFrm_outdoor_empName').focus();
				return false;
			}
			
			var visitDateVar = trim(document.getElementById('paraFrm_outdoor_outDate').value);
			if(visitDateVar == "") {
				alert("Please select/enter "+document.getElementById('date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_outdoor_outDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_outdoor_outDate',"date")){
						document.getElementById('paraFrm_outdoor_outDate').focus();
						return false;   	
   					}
			}
			
			var ftime = document.getElementById('paraFrm_outdoor_frTime').value;    
			var totime = document.getElementById('paraFrm_outdoor_toTime').value;		
			
			if(ftime==""){
				alert("Please enter start time");
				document.getElementById('paraFrm_outdoor_frTime').focus();    
				return false;
			}else if(!validateTime('paraFrm_outdoor_frTime', 'sTime')) {
				document.getElementById('paraFrm_outdoor_frTime').value = "";
				return false;
			}
			
			
			if(totime==""){
				alert("Please enter end time");
				document.getElementById('paraFrm_outdoor_toTime').focus();    
				return false;
			}else if(!validateTime('paraFrm_outdoor_toTime', 'eTime')) {
				document.getElementById('paraFrm_outdoor_toTime').value = "";
				return false;
			}
		     
      		if(!timeDifference(ftime,totime,'paraFrm_outdoor_toTime', 'sTime', 'eTime')) {
				return false;
			}
		
			var cmt = document.getElementById('paraFrm_outdoor_purpose').value;
			var remain = 300 - eval(cmt.length);
					
			if(eval(remain) < 0) {
					alert("Maximum characters for purpose field is 300 Remove Extra " + remain + " Characters.");
				return false;
			}
		
			var outLocVar = trim(document.getElementById('paraFrm_outdoor_outLoc').value);
			if(outLocVar==""){
				alert("Please enter location");
				document.getElementById('paraFrm_outdoor_outLoc').focus();
				return false;
			}
			
			var purposeVar = trim(document.getElementById('paraFrm_outdoor_purpose').value);
			if(purposeVar==""){
				alert("Please enter purpose for the visit.");
				document.getElementById('paraFrm_outdoor_purpose').focus();
				return false;
			}
			
		var con = confirm("Do you really want to send this application for approval?");
		if(con){
			document.getElementById('paraFrm_status').value = 'P';
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action='OutdoorVisit_sendForApproval.action';
	  		document.getElementById('paraFrm').submit();	
		}	
 	}catch(e){
 		alert(e);
 	}
 }
 
 function backFun()
{
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OutdoorVisit_backFunction.action';
		document.getElementById('paraFrm').submit();
}


function reportFun(){
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OutdoorVisit_report.action';
		document.getElementById('paraFrm').submit();
}

function resetFun(){
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OutdoorVisit_reset.action';
		document.getElementById('paraFrm').submit();
}


function backtolistFun(){
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OutdoorApproval_backToApprovalList.action';
		document.getElementById('paraFrm').submit();
}		

function showAttendanceFunction(){
	try{
		var outDoorVisitDate = trim(document.getElementById('paraFrm_outdoor_outDate').value);
		if(outDoorVisitDate==""){
			alert("Please enter visit date.");
			document.getElementById('paraFrm_outdoor_outDate').focus();
			return false;
		}else { 
				if(!validateDate('paraFrm_outdoor_outDate',"date")){
						document.getElementById('paraFrm_outdoor_outDate').focus();
						return false;   	
   					}else{
   						
 						win=window.open('','win','top=250,left=100,width=900,height=200,scrollbars=no,status=no,resizable=no');
						document.getElementById("paraFrm").target="win";
						document.getElementById("paraFrm").action="OutdoorVisit_setAttendanceData.action?outDoorVisitDate="+outDoorVisitDate;
						document.getElementById("paraFrm").submit();	
						document.getElementById("paraFrm").target="main"; 
   					}
			}
	}catch(e){
		alert("Exception occured in showAttendanceFunction() : "+e);
	}
}

</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
