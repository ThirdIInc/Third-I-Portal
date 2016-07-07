<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


<s:form action="CandidateSchedule" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">
			Candidate Schedule </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="add" value="    Save"
						action="CandidateSchedule_save" onclick="return saveValidation();" />

					<s:submit cssClass="reset" value="    Reset"
						action="CandidateSchedule_reset" /> <s:submit cssClass="del"
						value="    Delete" action="CandidateSchedule_delete"
						onclick="return deletefun();" /> <input type="button"
						class="search" value="    Search"
						onclick="javascript:callsF9(500,325,'CandidateSchedule_f9action.action'); " />
					<s:hidden name="candidateSchedule.scheduleCode" />
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
							Required</div>
					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td height="7" colspan="5" class="formtext"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>

						<tr>

							<td width="10%" class="formtext">Candidate Name<font
								color="red">*</font> :</td>
							<td width="20%"><s:textfield size="25"
								name="candidateSchedule.candidate" readonly="true" /> <s:hidden
								name="candidateSchedule.candidateCode" /> <s:if
								test="candidateSchedule.chk">

							</s:if> <s:else>
								<img src="../pages/images/search2.gif" height="16"
									align="middle" width="16" theme="simple"
									onclick="javascript:callsF9(500,325,'CandidateSchedule_f9actionCandidate.action');">
							</s:else></td>
						</tr>

						<tr>
							<td width="10%" class="formtext">Paper Name<font
								color="red">*</font> :</td>
							<td width="20%"><s:textfield size="25"
								name="candidateSchedule.paperName" readonly="true" /> <s:hidden
								name="candidateSchedule.paperCode" /> <img
								src="../pages/images/search2.gif" height="16" align="middle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'CandidateSchedule_f9actionPaper.action');">
							</td>
						</tr>



						<tr>
							<td width="17%" class="formtext">Schedule Date <font
								color="red">*</font>:</td>
							<td width="28%"><s:textfield
								name="candidateSchedule.scheduleDate" size="12" /> <s:a
								href="javascript:NewCal('paraFrm_candidateSchedule_scheduleDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>

						<tr>
							<td width="10%" class="formtext">Schedule Time <font
								color="red">*</font> :</td>
							<td width="20%"><s:textfield size="25"
								name="candidateSchedule.time"
								onkeypress="return numbersWithColon();" maxlength="5" /> [Time
							should be in 24Hours HH:MM format]</td>
						</tr>
						<tr>
							
							<td width="20%"><s:hidden 
								name="candidateSchedule.uname"/> </td>
						</tr>


						<tr>
							<td colspan="5"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>


	</table>
	</td>
	</tr>
	<tr>
		<td colspan="3"><img
			src="../pages/common/css/default/images/space.gif" width="5"
			height="4" /></td>
	</tr>


	</table>



</s:form>


<script>
 function saveValidation()
  {
    var val=document.getElementById('paraFrm_candidateSchedule_candidate').value;
    var totDt=""; 
 	var currentTime = new Date();
 	var month = currentTime.getMonth()+1;
 	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
 	var ch=String.valueOf(month).length;
	 //alert("Length of month"+ch);
 	if(ch==1){
 	totDt=day+"-0"+month+"-"+year;
 	}else {
 	totDt=day+"-"+month+"-"+year;
 	}
 
 	//alert("dt of td"+totDt);
  
 	 if(val=="")
  	{
 	 alert('Please select Candidate');
  	return false;
  	}
  	var que=document.getElementById('paraFrm_candidateSchedule_paperName').value;
 	if(que=="")
 	{
 	 alert('Please select Paper');
 	return false;
  	}
 	 var sdate=document.getElementById('paraFrm_candidateSchedule_scheduleDate').value;
 	 if(sdate=="")
  	{
  	alert('Please select Date');
  	return false;
 	 }
 	 var time=document.getElementById('paraFrm_candidateSchedule_time').value;
 	 if(time=="")
 	 {
 	 alert('Please enter Time');
  	return false;
  	}
  
  	var s1 = time.substring(0,2);
	var s2 = time.substring(2,3);
	var s3 = time.substring(3,5);
	
	
	if(!(s1>0 && s1<=23)){
	alert("Hour "+s1+" is not valid ");
	return false;
	}
	if(!(s2==":")){
	alert(" Time should be in hh:mm format");
	return false;
	}
	if(!(s3>=0 && s3<=59)){
	alert("Minute "+s3+" is not valid");
	return false;
	}
	
	if(!dateDifferenceEqual(totDt, sdate, 'paraFrm_candidateSchedule_scheduleDate', 'Current Date', 'Schedueled Date')){
	
	return false;
	}
		
	
	
   return true;
  	}
  
  	function deletefun()
    {
    var val= document.getElementById('paraFrm_candidateSchedule_scheduleCode').value;
  
    if(val=="")
    {
    alert('Please Select a Record');
    return false;
    }
    else {
    var conf=confirm("Are you sure to delete this record?");
  	if(conf) {
  		return true;
  		}
  	else
  		{
  		return false;
  		}
     
      }
    
   return true;
    }
  
  
</script>























