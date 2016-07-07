 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ResignationApplication" method="post" id="paraFrm"
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
					Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="source" id="source" /> 
		<tr>
			
				<td width="100%" colspan="3">
				<s:if test="buttonFlag">
				<input type="button" class="token"
					value="    Approve" onclick="return checkAppStatus(this,'A');"
					theme="simple" /> <input type="button" class="token"
					value="    Reject" onclick="return checkAppStatus(this,'R');"
					theme="simple" /> 
					</s:if>
					<s:if test="closeBtnFlag">
					<input type="button" class="token"
					value="    Back To List" onclick="return callBack();" theme="simple" />
					</s:if>
					</td>
			
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="prevAppCommentListFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date </td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
								</td>

							</tr>
							
							<%int i = 0;%>
							<%
							int k = 1;
							%>
				<s:iterator value="approverCommentList" status="stat">
					<tr>
					<td width="10%" class="sortableTD"><%=k%><s:hidden name="appSrNo" value="%{<%=k%>}"/> </td>
						<td width="15%" class="sortableTD"><s:property value="prevApproverID"/><s:hidden name="prevApproverID"/></td>
							<td width="25%" class="sortableTD"><s:property value="prevApproverName"/><s:hidden name="prevApproverName"/></td>
								<td width="10%" class="sortableTD" align="center"><s:property value="prevApproverDate"/><s:hidden name="prevApproverDate"/></td>
								<td width="10%" class="sortableTD">&nbsp;<s:property value="prevApproverStatus"/><s:hidden name="prevApproverStatus"/></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property value="prevApproverComment"/><s:hidden name="prevApproverComment"/></td>
					</tr>
							<%
								k++;
								%>
					</s:iterator>
					<%
								i = k;
								k = 0;
							%>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>


		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->

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
								class="set" name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
								color="red">*</font> <s:hidden name="empCode" value="%{empCode}" /></td>
							<td width="80%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empName" size="78" value="%{empName}" theme="simple"
								readonly="true" /> <s:if test="%{showFlag}">
									<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16"
									onclick="javascript:callsF9(500,325,'ResignationApplication_f9action.action');">
								
								</s:if>
								<s:else>
							</s:else></td>

						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:textfield size="20"
								theme="simple" name="designationName" value="%{designationName}"
								readonly="true" /></td>

							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="30%" colspan="1"><s:textfield size="20"
								theme="simple" name="branchName" value="%{branchName}"
								readonly="true" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
							<td width="30%" colspan="1"><s:textfield name="dateOfJoin"
								size="20" onkeypress="return numbersWithHiphen();"
								theme="simple" value="%{dateOfJoin}" maxlength="10"
								readonly="true" /></td>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="resignationDt" id="resignationDt"
								ondblclick="callShowDiv(this);"><%=label.get("resignationDt")%></label>:<font
								color="red">*</font> <s:hidden name="today" /></td>
							<td width="30%" colspan="1"><s:if test="imageFlag">
								<s:textfield name="resignDate" size="20"
									onkeypress="return numbersWithHiphen();" theme="simple"
									 maxlength="10" readonly="true" />

								<!--<s:a href="javascript:NewCal('paraFrm_resignDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a>-->
							</s:if> <s:else>
								<s:textfield name="resignDate" size="20"
									onkeypress="return numbersWithHiphen();" theme="simple"
									value="%{resignDate}" maxlength="10" readonly="true" />

							</s:else></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="tentativeSepDate" id="tentativeSepDate"
								ondblclick="callShowDiv(this);"><%=label.get("tentativeSepDate")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:if test="imageFlag">
								<s:textfield name="seperationDate" size="20"
									onkeypress="return numbersWithHiphen();" theme="simple"
									value="%{seperationDate}" maxlength="10" />

								<s:a
									href="javascript:NewCal('paraFrm_seperationDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a>
							</s:if> <s:else>
								<s:textfield name="seperationDate" size="20"
									onkeypress="return numbersWithHiphen();" theme="simple"
									value="%{seperationDate}" maxlength="10" readonly="true" />
							</s:else></td>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="status" id="status"
								ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
							<td width="30%" colspan="1"><s:select theme="simple"
								name="status" disabled="true" cssStyle="width:130"
								list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdrawn','Y':'Hr Approved','Z':'Hr Rejected'}" />
							<s:hidden name="hiddenStatus" /><s:hidden name="level" /></td>

						</tr>
		
			 			</table>
					</td>
				</tr>


			</table>
		 
			<s:if test="hrApprovalFlag">
			<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
					<td width="30%" colspan="1">
				 		<s:select theme="simple"
						name="statusSelect" cssStyle="width:130"  disabled="true"
						list="#{'s':'--select--','m':'Mutual','d':'Dismissal','n':'Normal'}" />
					 	</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="noticeperiodact" id="noticeperiodact"
						ondblclick="callShowDiv(this);"><%=label.get("noticeperiodact")%></label>:<font color="red">*</font> 
						 </td>
					<td width="30%" colspan="1">
				  	<s:textfield size="10"
						theme="simple" name="noticePeriodActual" readonly="true"
						onkeypress="return numbersOnly();" maxlength="2"  /> <s:hidden name="noticePeriod" /><s:select
						theme="simple" name="noticeperiodselect" cssStyle="width:60"  disabled="true"
						list="#{'D':'Days','M':'Month'}" />
						 
						</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="waveoff" id="waveoff"
						ondblclick="callShowDiv(this);"><%=label.get("waveoff")%></label>:</td>
					<td width="30%" colspan="1">
				 
					<s:textfield  name="waveOffPeriod" size="20" onkeypress="return numbersOnly();"  onkeyup="setNoticePeriod();"  readonly="true" maxlength="2"></s:textfield>(Days)
				 
					</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="waveoffappr" id="waveoffappr"
						ondblclick="callShowDiv(this);"><%=label.get("waveoffappr")%></label>: 
						 </td>
					<td width="30%" colspan="1">
					  
						<s:textfield size="25"  
						theme="simple" name="apprName" readonly="true" /> <s:hidden name="apprToken" value="%{apprToken}" /><s:hidden
						name="apprCode" value="%{apprCode}" />
					 
						</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="hrComments" id="hrComments"
						ondblclick="callShowDiv(this);"><%=label.get("hrComments")%></label>:<font
						color="red">*</font></td>
					<td width="80%" colspan="3">
				 
						<s:textarea name="hrComments"
						rows="5" cols="68" readonly="true" />
					 	</td>

				</tr>

				<tr>
					<td><label name="withdraw" id="withdraw"
						ondblclick="callShowDiv(this);"><%=label.get("withdraw")%></label>
					:</td>
					<td colspan="6">
					 
					<s:checkbox name="withDrawn" id="withDrawn" disabled="true"/>
				 
					</td>
				</tr>

			</table>
			</td>
		</tr>
	 	</s:if>
	 	
	 		<!--<tr>
			
				<td width="100%" colspan="3">
				<s:if test="buttonFlag">
				<input type="button" class="token"
					value="    Approve" onclick="return checkAppStatus(this,'A');"
					theme="simple" /> <input type="button" class="token"
					value="    Reject" onclick="return checkAppStatus(this,'R');"
					theme="simple" /> 
					</s:if>
					<s:if test="closeBtnFlag">
					<input type="button" class="token"
					value="    Back To List" onclick="return callBack();" theme="simple" />
					</s:if>
					</td>
			
		</tr>
		
				--><!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

					<td width="11%" nowrap="nowrap"><strong>Keep Informed
					to HR: <font color="red">*</font></strong></td>
					<td width="13%"><s:if test="imageFlag">
						<s:hidden name="employeeId" />
						<s:hidden name="employeeToken" />
						<s:textfield name="employeeName" readonly="true" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="imageFlag">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="imageFlag">
						<s:submit name="" value=" Add" cssClass=" add"
							action="ResignationApplication_addKeepInformedEmpList"
							onclick="return callKeepInformed();" />
					</s:if></td>
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
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

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
							int counter11 = 0;
							
						%>
						<%!int counter2 = 0; %>

						<s:iterator value="keepInformedList" status="stat">

							<tr>

								<td width="80%"><%=++counter11%><s:hidden name="serialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
								<td width="20%">
									<s:if test="resignApp.imageFlag">
								<a href="#"
									onclick="callForRemove(<%=counter11%>);">Remove</a>
									</s:if>
									</td>
							</tr>
							
						</s:iterator>
						<%
							counter2 = counter11;
					 
							%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- KEEP INFORMED LIST TABLE  ENDS -->
		
		<!--   COMMENTS TABLE  STARTS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="0"  
				class="formbg">
			 		
						<tr>
								<td width="20%"><label name="comments" id="comments"
									ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:</td>
								<td>
								<s:if test="imageFlag">
								<s:textarea name="appComment" rows="5"
									cols="68" readonly="false"
									onkeyup="callLength('appComment','descCnt2','500');" /><img
									src="../pages/images/zoomin.gif" height="12" align="absmiddle"
									width="12" theme="simple" id="ctrlHide"
									onclick="javascript:callWindow('paraFrm_appComment','comments','','paraFrm_descCnt2','500');">
								Remaining chars <s:textfield
									name="descCnt2" readonly="true" size="5" />
									</s:if>
									<s:else>
									<s:textarea name="appComment" rows="5"
									cols="68" readonly="true"
									onkeyup="callLength('appComment','descCnt2','500');" />
									
									</s:else>
									</td>

							</tr> 
						
						<tr>
								<td width="20%"><label name="reason" id="reason"
									ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:</td>
								<td>
								<s:if test="imageFlag">
								<s:textarea name="appReason" rows="5"
									cols="68" readonly="false"
									onkeyup="callLength('appReason','descCnt3','500');" /><img
									src="../pages/images/zoomin.gif" height="12" align="absmiddle"
									width="12" theme="simple" id="ctrlHide"
									onclick="javascript:callWindow('paraFrm_appReason','reason','','paraFrm_descCnt3','500');">
								Remaining chars <s:textfield
									name="descCnt3" readonly="true" size="5" />
									</s:if>
									<s:else>
									<s:textarea name="appReason" rows="5"
									cols="68" readonly="true"
									onkeyup="callLength('appReason','descCnt3','500');" />
									
									</s:else>
									</td>

							</tr>

			</table>
			</td>
		</tr>

		<!--   COMMENTS TABLE  ENDS -->
		
			<s:if test="resignApp.approvalFlag">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="acceptDate" id="acceptDate" ondblclick="callShowDiv(this);"><%=label.get("acceptDate")%></label>:</td>
					<td width="30%" colspan="1">
					<s:if test="approverDtlFlag">
					<s:textfield name="acceptDate"
						size="20" onkeypress="return numbersWithHiphen();" theme="simple"
						value="%{acceptDate}" maxlength="10"   />
						<s:a href="javascript:NewCal('paraFrm_acceptDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a>
						</s:if>
						<s:else>
						<s:textfield name="acceptDate"
						size="20" onkeypress="return numbersWithHiphen();" theme="simple"
						value="%{acceptDate}" maxlength="10"   readonly="true"/>
						
						</s:else>
						
						</td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="actualSepDate" id="actualSepDate"
						ondblclick="callShowDiv(this);"><%=label.get("actualSepDate")%></label>:<font
						color="red">*</font>  </td>
					<td width="30%" colspan="1"> 
					<s:if test="approverDtlFlag">					
					<s:textfield name="seperationDateActual" size="20"
									onkeypress="return numbersWithHiphen();" theme="simple"
									value="%{seperationDateActual}" maxlength="10" />
					 
						<s:a href="javascript:NewCal('paraFrm_seperationDateActual','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a>
						</s:if>
						<s:else>
							<s:textfield name="seperationDateActual" size="20" onkeypress="return numbersWithHiphen();" theme="simple"
							 maxlength="10"  readonly="true"/>
						
						</s:else>
					 </td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="appComments" id="appComments" ondblclick="callShowDiv(this);"><%=label.get("appComments")%></label>:<font
						color="red">*</font></td>
					<td width="80%" colspan="3">
					<s:if test="approverDtlFlag">
					<s:textarea name="approverComments"
						rows="5" cols="68" readonly="false" />
						</s:if>
						<s:else>
							<s:textarea name="approverComments"
						rows="5" cols="68" readonly="true" />
						
						</s:else>
						</td>

				</tr>


			</table>
			</td>
		</tr>
		</s:if>
		

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>

			</table>
			</td>
		</tr>
		</td>
		</tr>
		
		
		<tr>
			
				<td width="100%" colspan="3">
				<s:if test="buttonFlag">
				<input type="button" class="token"
					value="    Approve" onclick="return checkAppStatus(this,'A');"
					theme="simple" /> <input type="button" class="token"
					value="    Reject" onclick="return checkAppStatus(this,'R');"
					theme="simple" /> 
					</s:if>
					<s:if test="closeBtnFlag">
					<input type="button" class="token"
					value="    Back To List" onclick="return callBack();" theme="simple" />
					</s:if>
					</td>
			
		</tr>

	</table>
	<s:hidden name="resignCode" />
	<s:hidden name="checkApproveRejectStatus" />
	<s:hidden name="checkRemove" />
	 
	 
</s:form>


<script>
autoDate();
function autoDate () 
 	{
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			document.getElementById("paraFrm_today").value=tDate+"-"+tMonth+"-"+tDay.getFullYear();
			if(document.getElementById('paraFrm_resignCode').value=="" || document.getElementById('paraFrm_status').value=="D" )
			{
				document.getElementById("paraFrm_resignDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				
				
			}
				
}


	 function callForRemove(id)
	    {
	    		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemove').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="ResignationApplication_removeKeepInformed.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
		  				else
		  				{
		  					return false;
		  				}
		   
		  	return true;			
	    }


	function callKeepInformed()
	{
	 
		 var empcode=document.getElementById('paraFrm_empCode').value;
		 var emp =document.getElementById('paraFrm_employeeId').value;
		 if(empcode==""){
			 alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		 return false;
			 }
			if(emp=="")
			{
			alert("Please select Keep Informed To HR");
				return false;
			}
	
		return true;
	}
	


function getKeepInformedEmp()
	{
	var empcode=document.getElementById('paraFrm_empCode').value;
			 if(empcode==""){
				alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
				return false;
			}
			else{
			callsF9(500,325,'ResignationApplication_f9KeepInformedEmployee.action');
		 	}
	}

function backFun()
{
		/*document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationApplication_back.action';
		document.getElementById('paraFrm').submit();*/
		
		document.getElementById('paraFrm').target = "_self";
		
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else if(document.getElementById('source').value=='mytimecard')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_mytimeCard.action';
		}
		else{
		document.getElementById('paraFrm').action = 'ResignationApplication_back.action';
		}
	 
		document.getElementById('paraFrm').submit();

}


	function withdrawFun() {
		
		try{ 
		 var conf=confirm("Do you really want to withdraw this application ?");
    	 if(conf)
				{
			document.getElementById('withdraw').disabled=true;
			/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'withdraw') {
			//alert(document.all[i]);
			//document.all[i].value="Saving...";
			document.all[i].disabled=true;
		}
		}*/
				
				  	document.getElementById("paraFrm").target="main";
			 		document.getElementById('paraFrm').action = 'ResignationApplication_withdrawApplication.action?checkStatus=W';
					document.getElementById("paraFrm").submit();
		  		 }
				 else
				 {
				 return false; 
  				}
				return true;
			}
			catch(e){alert(e);}
			}

function draftFun()
{
  try{
  
  		var keepInfoLength='<%=counter2%>';
  	 
 		 var empCode = document.getElementById('paraFrm_empCode').value;
		 var resignDate=document.getElementById('paraFrm_resignDate').value;
		  var tentSeperationDate=document.getElementById('paraFrm_seperationDate').value;
			  var dateOfJoin=document.getElementById('paraFrm_dateOfJoin').value;
  		if(empCode == ""){
  			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
  			return false;
  		}
  		if(resignDate == ""){
  			alert("Please select "+document.getElementById('resignationDt').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(tentSeperationDate == ""){
  			alert("Please select "+document.getElementById('tentativeSepDate').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(!validateDate('paraFrm_resignDate','resignationDt'))
  				return false;
  				
  					if(!validateDate('paraFrm_seperationDate','tentativeSepDate'))
  				return false;
  				
  		var datdiff  =dateDifference(dateOfJoin,resignDate,'paraFrm_dateOfJoin','doj','resignationDt');
  		
  		if(!datdiff){
  		return false;
  		}
  				
 		 
  		var datdiffresignDate =dateDifferenceEqual(resignDate,tentSeperationDate,'paraFrm_resignDate','resignationDt','tentativeSepDate');
  		
  		if(!datdiffresignDate){
  		return false;
  		}
  		
  		var appComment = document.getElementById('paraFrm_appComment').value;
  		
  		var appReason = document.getElementById('paraFrm_appReason').value;
		 
		if(appComment != "" && appComment.length >500){
			alert("Maximum length of "+ document.getElementById('comments').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }   
	    
	    
	    if(appReason != "" && appReason.length >500){
			alert("Maximum length of "+ document.getElementById('reason').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }    
	  
	  if(keepInfoLength==0)
	  		{
			alert("Please add Keep Informed to HR ");
				return false;
			}
	
	    
  		
  	 }catch(e)
  	 {
  	 
  	 alert("e-----"+e);
	
	}	 
	         document.getElementById('draft').disabled=true;
	         
			/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'draft') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
		}*/
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='ResignationApplication_save.action?checkStatus=D'; 
		document.getElementById('paraFrm').submit();

}

	 
	
	function sendforapprovalFun()
	{
      try
      {
 		 var empCode = document.getElementById('paraFrm_empCode').value;
		 var resignDate=document.getElementById('paraFrm_resignDate').value;
		  var tentSeperationDate=document.getElementById('paraFrm_seperationDate').value;
		    var dateOfJoin=document.getElementById('paraFrm_dateOfJoin').value;
		    	var keepInfoLength='<%=counter2%>';
			 
  		if(empCode == ""){
  			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
  			return false;
  		}
  		if(resignDate == ""){
  			alert("Please select "+document.getElementById('resignationDt').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(tentSeperationDate == ""){
  			alert("Please select "+document.getElementById('tentativeSepDate').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			if(!validateDate('paraFrm_resignDate','resignationDt'))
  				return false;
  				
  					if(!validateDate('paraFrm_seperationDate','tentativeSepDate'))
  				return false;
  				
  				
  		var datdiff  =dateDifference(dateOfJoin,resignDate,'paraFrm_dateOfJoin','doj','resignationDt');
  		
  		if(!datdiff){
  		return false;
  		}
  				
 		 
  		var datdiffresignDate =dateDifferenceEqual(resignDate,tentSeperationDate,'paraFrm_resignDate','resignationDt','tentativeSepDate');
  		
  		if(!datdiffresignDate){
  		return false;
  		}
  		
  		var appComment = document.getElementById('paraFrm_appComment').value;
  		
  		var appReason = document.getElementById('paraFrm_appReason').value;
		 
		if(appComment != "" && appComment.length >500){
			alert("Maximum length of "+ document.getElementById('comments').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }   
	    
	    
	    if(appReason != "" && appReason.length >500){
			alert("Maximum length of "+ document.getElementById('reason').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }   
	    
	      if(keepInfoLength==0)
	  		{
			alert("Please add Keep Informed to HR ");
				return false;
			}
	
	       document.getElementById('sendforapproval').disabled=true;
	 	
	 		/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'sendforapproval') {
			//alert(document.all[i]);
			//document.all[i].value="Saving...";
			document.all[i].disabled=true;
		}
		}*/
	 	
	 	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='ResignationApplication_save.action?checkStatus=P'; 
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
		alert(e);
		}

}


function resetFun()
{
		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationApplication_reset.action';
		document.getElementById('paraFrm').submit();

}


function deleteFun()
{
 	var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			
			document.getElementById('delete').disabled=true;
			
			/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'delete') {
			document.all[i].disabled=true;
		}
		}*/
			
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='ResignationApplication_delete.action'; 
			document.getElementById('paraFrm').submit();

			 }
			 else
			 {
			 return false; 
			 
			 }
}

function checkAppStatus(obj,id)
  {
   
  	try{
   
    var resignDate=document.getElementById('paraFrm_resignDate').value;
 	var dateOfJoin=document.getElementById('paraFrm_dateOfJoin').value;
 	var actualSeperationDate=document.getElementById('paraFrm_seperationDateActual').value;
   	var approverComments = document.getElementById('paraFrm_approverComments').value;
   	var actualSepDate = document.getElementById('paraFrm_seperationDateActual').value;
   	var acceptDate = document.getElementById('paraFrm_acceptDate').value;
   		
   	var conf;
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	 
      if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
       {
          if(actualSepDate == ""){
  			alert("Please select "+document.getElementById('actualSepDate').innerHTML.toLowerCase());
  			return false;
 		 		}
 		}	
        if(!validateDate('paraFrm_seperationDateActual','actualSepDate'))
  				return false;
  				
  		var date_diff  =dateDifference(dateOfJoin,acceptDate,'paraFrm_dateOfJoin','doj','acceptDate');

		  		if(!date_diff){
		  		return false;
		  		}
  		
  			var date_diff1  =dateDifferenceEqual(resignDate,acceptDate,'paraFrm_resignDate','resignationDt','acceptDate');
  		
		  		if(!date_diff1){
		  		return false;
		  		}
  		  		
  		   var datdiff  =dateDifference(dateOfJoin,actualSeperationDate,'paraFrm_dateOfJoin','doj','actualSepDate');
  		
  		if(!datdiff){
  		return false;
  		}
  				
 		 
  		var datdiffresignDate =dateDifferenceEqual(resignDate,actualSeperationDate,'paraFrm_resignDate','resignationDt','actualSepDate');
  		
  		if(!datdiffresignDate){
  		return false;
  		}
  		
  			 
  		var datdiff1 =dateDifferenceEqual(acceptDate,actualSeperationDate,'paraFrm_acceptDate','acceptDate','actualSepDate');
  		
  		if(!datdiff1){
  		return false;
  		}
          
          var fieldName=["paraFrm_approverComments"];
		  var lableName=["appComments"];
		  var flag = ["enter"];
		    	if(!(validateBlank(fieldName,lableName,flag))){
					return false;
		        }
		        
		        	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
       					conf=confirm("Do you really want to approve this application ?");
      				if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
       					conf=confirm("Do you really want to reject this application ?");
    		 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
			 		document.getElementById("paraFrm").action="ResignationApplication_approveRejApplication.action";
					document.getElementById("paraFrm").submit();
		  			window.close();
				 }
				 else
				 {
				 return false; 
  				}
  				
  			}
  		catch(e){alert(e);}	
  		return true; 		
  }
  
  
  function callBack()
	{ 
		/*document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationApplication_backToApprovalList.action';
		document.getElementById('paraFrm').submit();*/
		try{
	
		document.getElementById('paraFrm').target = "_self";
		
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
		document.getElementById('paraFrm').action = 'ResignationApplication_backToApprovalList.action';
		}
		document.getElementById("paraFrm").submit();
		}catch(e)
		{
			alert("Err  "+e);
		}
	}
  
	 
	
	function search()
	{
		callsF9(500,325,'ResignationApplication_searchSavedRecord.action');	
	}
</script>
 