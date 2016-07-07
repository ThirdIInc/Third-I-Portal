
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ResignationHRApproval" method="post" id="paraFrm"
	theme="simple">

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
					<td width="93%" class="txt"><strong class="text_head">Resignation
					HR Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>

			<td width="100%" colspan="3">
			<s:if test="showFlag">
			<input type="button" class="token"
				value="    Approve" onclick="return checkAppStatus(this,'Y');"
				theme="simple" /> <input type="button" class="token"
				value="    Reject" onclick="return checkAppStatus(this,'Z');"
				theme="simple" />
				
				</s:if>
				 <input type="button" class="token"
				value="    Back" onclick="return callBack();" theme="simple" /></td>

		</tr>
 
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="employee.name" id="employee.name"
								ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>:<font
								color="red">*</font> 
								<s:hidden name="employeeCode" value="%{employeeCode}" /></td>
							<td width="80%" colspan="3">
								<s:property value="empToken" />
								<s:property value="employeeName" />
							</td>

						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property
								value="%{designationName}" /></td>

							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property
								value="%{branchName}" /></td>
						</tr>
						<tr>
						
						    <td width="20%" colspan="1" class="formtext">
								<label class="set" name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td width="30%" colspan="1">
								<s:property  value="%{cardeName}"/>
							</td>
												
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
							<td width="30%" colspan="1"><s:property
								value="%{dateOfJoin}" /></td>
							
						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="appdate" id="appdate"
								ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:property
								value="resignDate" /><s:hidden name="resignHrApproval.resignDate" /></td>
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="tentativeSepDate" id="tentativeSepDate"
								ondblclick="callShowDiv(this);"><%=label.get("tentativeSepDate")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:property
								value="%{seperationDate}" /></td>
							

						</tr>

						<tr>
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="status" id="status"
								ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
							<td width="30%" colspan="1"><s:select theme="simple"
								name="applicationStatus" disabled="true" cssStyle="width:130"
								list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdrawn','Y':'Hr Approved','Z':'Hr Rejected'}" />
							<s:hidden name="hiddenStatus" /><s:hidden name="level" /></td>
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="comments" id="comments"
								ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:</td>
							<td width="30%" colspan="1"><s:property value="appComment" /></td>
							
						</tr>
						<tr>
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="reason" id="reason"
								ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:property value="appReason" /></td>
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="acceptDate" id="acceptDate"
								ondblclick="callShowDiv(this);"><%=label.get("acceptDate")%></label>:</td>
							<td width="30%" colspan="1"><s:property
								value="%{acceptDate}" /></td>
							
						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="actualSepDate" id="actualSepDate"
								ondblclick="callShowDiv(this);"><%=label.get("actualSepDate")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:property
								value="%{actualSeperationDate}" /></td>
								
						
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="appComments" id="appComments"
								ondblclick="callShowDiv(this);"><%=label.get("appComments")%></label>:<font
								color="red">*</font></td>
							<td width="80%" colspan="3">
							<s:property 	value="approverComments" />
							
							</td>
							

						</tr>


					</table>
					</td>
				</tr>


			</table>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
					<td width="30%" colspan="1">
					
					<s:if test="approvalFlag">
					<s:select theme="simple"
						name="status" cssStyle="width:130"  
						list="#{'s':'--select--','m':'Mutual','d':'Dismissal','n':'Normal'}" />
						</s:if>
						<s:else>
						<s:select theme="simple"
						name="status" cssStyle="width:130"  disabled="true"
						list="#{'s':'--select--','m':'Mutual','d':'Dismissal','n':'Normal'}" />
						</s:else>
						</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="noticeperiodact" id="noticeperiodact"
						ondblclick="callShowDiv(this);"><%=label.get("noticeperiodact")%></label>:<font color="red">*</font> 
						 </td>
					<td width="30%" colspan="1">
					<s:if test="approvalFlag">
					<s:textfield size="10"
						theme="simple" name="noticePeriodActual"
						onkeypress="return numbersOnly();" maxlength="2"  onkeyup="setNoticePeriod();"/> <s:select
						theme="simple" name="noticeperiodselect" cssStyle="width:60" 
						list="#{'D':'Days','M':'Month'}" /><s:hidden name="noticePeriod" /></s:if>
						<s:else>
						<s:textfield size="10"
						theme="simple" name="noticePeriodActual" readonly="true"
						onkeypress="return numbersOnly();" maxlength="2" onkeyup="setNoticePeriod();"/> <s:hidden name="noticePeriod" /><s:select
						theme="simple" name="noticeperiodselect" cssStyle="width:60"  disabled="true"
						list="#{'D':'Days','M':'Month'}" />
						
						</s:else>
						
						</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="waveoff" id="waveoff"
						ondblclick="callShowDiv(this);"><%=label.get("waveoff")%></label>:</td>
					<td width="30%" colspan="1">
					
						<s:if test="approvalFlag">
					<s:textfield  name="waveOffPeriod" size="20" onkeypress="return numbersOnly();"  onkeyup="setNoticePeriod();" maxlength="2"></s:textfield>(Days)
					</s:if>
					<s:else>
					<s:textfield  name="waveOffPeriod" size="20" onkeypress="return numbersOnly();"  onkeyup="setNoticePeriod();"  readonly="true" maxlength="2"></s:textfield>(Days)
					</s:else>
					</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="waveoffappr" id="waveoffappr"
						ondblclick="callShowDiv(this);"><%=label.get("waveoffappr")%></label>: 
						 </td>
					<td width="30%" colspan="1">
					
						<s:if test="approvalFlag">
					<s:textfield size="25"
						theme="simple" name="apprName" readonly="true" /> <s:hidden name="apprToken" value="%{apprToken}" /><s:hidden
						name="apprCode" value="%{apprCode}" /><img class="iconImage"
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="16" width="15" onclick="getApprover();">
						</s:if>
						<s:else>
						<s:textfield size="25"  
						theme="simple" name="apprName" readonly="true" /> <s:hidden name="apprToken" value="%{apprToken}" /><s:hidden
						name="apprCode" value="%{apprCode}" />
						</s:else>
						</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="hrComments" id="hrComments"
						ondblclick="callShowDiv(this);"><%=label.get("hrComments")%></label>:<font
						color="red">*</font></td>
					<td width="80%" colspan="3">
					<s:if test="approvalFlag">
					<s:textarea name="hrComments"
						rows="5" cols="68" readonly="false" /></s:if>
						<s:else>
						<s:textarea name="hrComments"
						rows="5" cols="68" readonly="true" />
						
						</s:else>
						
						</td>

				</tr>

				<tr>
					<td><label name="withdraw" id="withdraw"
						ondblclick="callShowDiv(this);"><%=label.get("withdraw")%></label>
					:</td>
					<td colspan="6">
					<s:if test="approvalFlag">
					<s:checkbox name="withDrawn" id="withDrawn" />
					</s:if>
					<s:else>
					<s:checkbox name="withDrawn" id="withDrawn" disabled="true"/>
					</s:else>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>

			<td width="100%" colspan="3">
				<s:if test="showFlag">
			<input type="button" class="token"
				value="    Approve" onclick="return checkAppStatus(this,'Y');"
				theme="simple" /> <input type="button" class="token"
				value="    Reject" onclick="return checkAppStatus(this,'Z');"
				theme="simple" /> 
				</s:if>
				<input type="button" class="token"
				value="    Back" onclick="return callBack();" theme="simple" /></td>

		</tr>


	</table>
	<s:hidden name="resignCode" />
	<s:hidden name="checkApproveRejectStatus" />
	<s:hidden name="deptCode"></s:hidden>
	 

</s:form>

<script>


function getApprover(){

		callsF9(500,325,'ResignationHRApproval_f9appraction.action');
	}
	
function checkAppStatus(obj,id)
  {
   
  	try{
    
   	var conf;
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	
  	var status= document.getElementById('paraFrm_status').value;	 
 	 var noticePeriod =document.getElementById('paraFrm_noticePeriodActual').value;	
 	 	 var waveOffApprovedBy =document.getElementById('paraFrm_apprCode').value;	
 	 	 var hrComments =document.getElementById('paraFrm_hrComments').value;	
 	 	
 	 	
 	 	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=='Y')
 	 	{
 	 	if(noticePeriod=="")
 	 	{
 	 			alert("Please enter notice period");
 	 			return false;
 	 	}
 	 	}
 	 	if(hrComments=="")
 	 	{
 	 			alert("Please enter hr comments");
 	 			return false;
 	 	}
	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="Y")
       conf=confirm("Do you really want to approve this application ?");
      if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="Z")
       conf=confirm("Do you really want to reject this application ?");
    	 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
			 		document.getElementById("paraFrm").action="ResignationHRApproval_approveRejApplication.action";
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
  
  
   function callBack()
	{ 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationHRApproval_backToList.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function setNoticePeriod(){
	
		try{
			 
		var type = document.getElementById("paraFrm_noticeperiodselect").value;
		if(type=="D"){
			var actualperiod = document.getElementById("paraFrm_noticePeriodActual").value;
			var waveOff = document.getElementById("paraFrm_waveOffPeriod").value;
			var servedPeriod = document.getElementById("paraFrm_noticePeriod").value;
			if(waveOff==""){
				document.getElementById("paraFrm_noticePeriod").value=document.getElementById("paraFrm_noticePeriodActual").value;
			}else{
				servedPeriod = actualperiod-waveOff;
				if(servedPeriod < 0){
					alert(document.getElementById('waveoff').innerHTML.toLowerCase()+" cannot be greater than "+document.getElementById('noticeperiodact').innerHTML.toLowerCase());
					document.getElementById("paraFrm_waveOffPeriod").value=0;
					document.getElementById("paraFrm_noticePeriod").value=document.getElementById("paraFrm_noticePeriodActual").value;
					return false;
				}
				document.getElementById("paraFrm_noticePeriod").value = servedPeriod;
			}
		}else{
			return true;
		}
		
		}catch(e){ alert("e----------"+e);}
	}

</script>