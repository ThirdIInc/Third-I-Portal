<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LoanApproval" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
<s:hidden name="listType" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><s:hidden name="hiddenCode" /><s:hidden name="level" /><s:hidden
						name="hiddenLoginfrmId" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" class="formbg">
				<td colspan="2" id="ctrlShow"><b>Loan Application ID:</b><font
					color="red"></font><s:property value="trackingNo" /> <s:hidden
					name="trackingNo" /></td>

			</table>
			</td>
		</tr>
		<!-- Approver Comments Section Begins -->
		<tr>
			<td>
			<table width="100%" class="formbg">
				<s:if test="approverCommentsFlag">
					<tr>
						<td colspan="2" id="ctrlShow"><b><label class="set"
							id="appr.cmt" name="appr.cmt" ondblclick="callShowDiv(this);"><%=label.get("appr.cmt")%></label></b>:<font
							color="red">*</font></td>
						<td colspan="3" id="ctrlShow"><s:textarea theme="simple"
							cols="70" rows="3" name="comment" id="comment"
							onkeypress="return imposeMaxLength(event, this, 500);" /></td>



					</tr>
					<s:if test="hrFlag">
						<tr>
							<td colspan="2" id="ctrlShow"><b><label class="set"
								id="appr.amt" name="appr.amt" ondblclick="callShowDiv(this);"><%=label.get("appr.amt")%></label></b>:<font
								color="red">*</font></td>
							<td id="ctrlShow"><s:textfield size="25" theme="simple"
								maxlength="8" name="apprLoanAmount"
								onblur="validateAmountLimit();"
								onkeypress="return checkNumbersWithDot(this);" /></td>

						</tr>
					</s:if>
				</s:if>
				<s:if test="listComment">



					<tr>
						<td class="formth" width="05%">Sr No</td>
						<td class="formth" width="15%">Approver Name</td>
						<td class="formth" width="40%" align="center">Comments</td>
						<td class="formth" width="15%">Application Date</td>
						<td class="formth" width="15%">Approve Amount</td>
						<td class="formth" width="15%">Status</td>
					</tr>
					<%
						int tt = 1;
					%>

					<s:iterator value="listComment">
						<tr>
							<td class="sortableTD"><%=tt++%></td>
							<td class="sortableTD"><s:property value="ittApproverName" />&nbsp;</td>
							<td class="sortableTD"><s:property value="ittComments" />&nbsp;</td>
							<td class="sortableTD"><s:property
								value="ittApplicationDate" />&nbsp;</td>
							<td class="sortableTD"><s:property value="ittApprAmount" />&nbsp;</td>
							<td class="sortableTD"><s:property value="ittStatus" />&nbsp;</td>
						</tr>
					</s:iterator>
				</s:if>

			</table>
			</td>
		</tr>
		<!-- Approver Comments Section Ends -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead"><label class="set" id="loanApp"
								name="loanApp" ondblclick="callShowDiv(this);"><%=label.get("loanApp")%></label></strong></td>
						</tr>

						<tr>
							
							<s:hidden name="year" />
							<s:hidden name="loanApplCode"></s:hidden>
							<s:hidden name='pfLoanCode' />
							<td colspan="1" width="25%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
								color="red">*</font></td>
							<td colspan="3" width="25%"><s:textfield size="10"
								theme="simple" name="empToken" readonly="true" /> <s:textfield
								size="50" theme="simple" name="empName" readonly="true" /> <s:hidden
								name="empCode" /> <s:if test="generalFlag"></s:if> <s:elseif
								test="isApprove"></s:elseif> <s:else>
								<s:if test="%{hiddenCode == ''}">
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="18" theme="simple" id="ctrlHide"
										onclick="callEmployee();">
								</s:if>
							</s:else></td>
							<td colspan="1" width="25%"></td>
							<td colspan="1" width="25%"></td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set" id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1" width="25%"><s:hidden name="branchCode" />
							<s:textfield size="25" theme="simple" name="branchName"
								readonly="true" /></td>
							<td colspan="1" width="25%"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td colspan="1" width="25%"><s:hidden name="deptCode" /> <s:textfield
								size="25" theme="simple" name="deptName" readonly="true" /></td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1" width="25%"><s:hidden name="desgCode" /> <s:textfield
								size="25" theme="simple" name="desgName" readonly="true" /></td>
							<td colspan="1" width="25%"><label class="set" id="joinDate"
								name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:</td>
							<td colspan="1" width="25%"><s:textfield size="25"
								theme="simple" name="confirmationDate" readonly="true" /></td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set" id="grade"
								name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td colspan="1" width="25%"><s:hidden name="gradeCode" /> <s:textfield
								size="25" theme="simple" name="grade" readonly="true" /></td>
							<!--<td colspan="1" width="23%" ><label  class = "set"  id="gross.sal" name="gross.sal" ondblclick="callShowDiv(this);"><%=label.get("gross.sal")%></label> :</td>
									<td colspan="1" width="23%">
										<s:textfield size="25" theme="simple" name="grossSalary" readonly="true"/></td>
								-->
							<td colspan="1" width="25%"><label class="set" id="dateApp"
								name="dateApp" ondblclick="callShowDiv(this);"><%=label.get("dateApp")%></label>:</td>
							<td colspan="1" width="25%"><s:textfield size="25"
								theme="simple" name="applicationdate" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

						</tr>

						<tr>


							<td colspan="1" width="25%"><label class="set"
								id="appStatus" name="appStatus" ondblclick="callShowDiv(this);"><%=label.get("appStatus")%></label>:</td>
							<td colspan="1" width="25%"><s:select
								name="applicationStatus" disabled="true" cssStyle="width:165"
								list="#{'D':'Draft', 'P':'Pending', 'A':'Approved', 'R':'Rejected', 'F':'Forwarded'}" /></td>

							<td><s:hidden name="divCode" /><s:hidden name="divName" /></td>
						</tr>

					</table>
					</td>
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
								<tr><s:hidden name="approverCode" />
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
			</table>
			</td>
		</tr>

		<!-- APPROVER LIST  TABLE  ENDS -->


		<!-- Loan Details Start -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead">Loan Details</strong></td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set" id="typeLoan"
								name="typeLoan" ondblclick="callShowDiv(this);"><%=label.get("typeLoan")%></label>:<font
								color="red">*</font> </td>
							<td colspan="1" width="25%"><s:hidden name="loanCode" /> <s:textfield
								size="25" theme="simple" name="loanType" readonly="true" /></td>
							<td width="25%"><label class="set" id="loanAmnt" name="loanAmnt"
								ondblclick="callShowDiv(this);"><%=label.get("loanAmnt")%></label>:</td>
							<td width="25%"><s:textfield size="25" theme="simple"
								name="loanAllowedLimit" onkeypress="" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

						</tr>
						<tr>
							<td colspan="1" width="22%"><label class="set" id="intType"
								name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>:</td>
							<td colspan="1" width="25%"><s:textfield maxlength="4"
								size="25" theme="simple" name="interestType" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
							<td colspan="1"><label class="set" id="intRate"
								name="intRate" ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>:<font color="red"></font></td>
							<td colspan="1"><s:textfield maxlength="4" size="25"
								theme="simple" name="interestRate" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

						</tr>


						<tr>
							<td colspan="1" width="25%" id='pfBalanceTD1'><label
								class="set" id="pfBalanceLable" name="pfBalanceLable"
								ondblclick="callShowDiv(this);"><%=label.get("pfBalanceLable")%></label>
							:</td>
							<td colspan="1" width="25%" id='pfBalanceTD2'><s:textfield
								size="25" theme="simple" name="pfBalance" readonly="true" />
						</tr>
						<tr>
							<td colspan="1" width="25%"><label class="set" id="amtLoan"
								name="amtLoan" ondblclick="callShowDiv(this);"><%=label.get("amtLoan")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="25%"><s:if test="isApprove">
								<s:textfield size="25" theme="simple" name="loanAmount"
									readonly="true" />
							</s:if> <s:else>
								<s:textfield size="25" theme="simple" name="loanAmount"
									onkeypress="return numbersWithDot();" maxlength="10"
									onblur="validateAmountLimit();" />



								<!--<input type="button" value="Calculate EMI" class="token" onclick="callCalculateEMI();"/>
										-->
							</s:else></td>
							<td colspan="1" width="25%" id="subTypeTD1"><label
								class="set" id="loanSubType" name="loanSubType"
								ondblclick="callShowDiv(this);"><%=label.get("loanSubType")%></label>:<font
								color="red">*</font> </td>
							<td colspan="1" width="25%" id="subTypeTD2"><s:if
								test="isApprove">
								<s:radio list="#{'Y':'Yes','N':'No'}" disabled="true"
									theme="simple" name="loanSubType" />
							</s:if><s:else>
								<s:radio list="#{'Y':'Yes','N':'No'}" theme="simple"
									name="loanSubType" />
							</s:else><s:hidden name='hiddenLoanSubType'></s:hidden></td>
						
						</tr>

						<tr>
							<td width="25%"><label class="set" id="calculate.emi"
								name="calculate.emi" ondblclick="callShowDiv(this);"><%=label.get("calculate.emi")%></label>:<font
								color="red">*</font></td>
							<td width="25%"><label class="set" id="expected.emi"
								name="expected.emi" ondblclick="callShowDiv(this);"><%=label.get("expected.emi")%></label>:<s:checkbox name="expectedEmi"
								onclick="return callOptions('expectedEmi');"></s:checkbox> <label
								class="set" id="tenure" name="tenure"
								ondblclick="callShowDiv(this);"><%=label.get("tenure")%></label>:<s:checkbox name="tenure"
								onclick="return callOptions('tenure');"></s:checkbox></td>
				<td  width="25%" colspan="2" id="calculatedBox"> 
										<!--<s:textfield size="20" theme="simple" name="expEmpValue"
											onkeypress="return checkNumbersWithDot(this);" maxlength="8"
											onblur="validateAmountLimit();" />
										
									--></td> 
							<s:hidden name="hiddenChechfrmId" id="hiddenChechfrmId" />
						</tr>

						<tr id='purposeTR'>

							<td colspan="1" width="25%"><label class="set" id="purpose"
								name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label>
							:</td>
							<td colspan="1" width="25%"><s:textfield
								name='loanPurposeValue' size="25" readonly="true"></s:textfield><s:hidden
								name='loanPurpose'></s:hidden> <s:if test="isApprove"></s:if><s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="18" theme="simple" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'LoanApplication_f9Purpose.action');">
							</s:else></td>
							<td colspan="1" width="25%"><label class="set"
								id="eligibility" name="eligibility"
								ondblclick="callShowDiv(this);"><%=label.get("eligibility")%></label>
							:</td>
							<td colspan="1" width="25%"><s:textarea cols="23" rows="2"
								theme="simple" name="loanEligibility" readonly="true" /></td>
						</tr>
						<tr id="limitTR">
							<td class="formtext" width="25%"><label class="set"
								id="minSanctLimit" name="minSanctLimit"
								ondblclick="callShowDiv(this);"><%=label.get("minSanctLimit")%></label>(<s:property
								value='minSanctionLimit' /> % of PF Balance) :</td>
							<td height="22" width="25%"><s:textfield
								name="minSanctionAmt" readonly="true" size="25" /><s:hidden
								name='minSanctionLimit' /></td>
							<td height="22" class="formtext" width="25%"><label
								class="set" id="maxSanctLimit" name="maxSanctLimit"
								ondblclick="callShowDiv(this);"><%=label.get("maxSanctLimit")%></label>(<s:property
								value='maxSanctionLimit' /> % of PF Balance) :</td>
							<td height="22" width="25%"><s:textfield
								name="maxSanctionAmt" readonly="true" size="25" /><s:hidden
								name='maxSanctionLimit' /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="purposeName" id="paraFrm_purposeName"
								ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label>:</td>
							<td width="25%" colspan="3"><s:textarea
								id="paraFrm_applicantComment" rows="2" cols="40"
								name="applicantComment" />
						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Loan Details End -->
		<!-- Initiator Details start -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td width="20%"><b>Completed By:</b></td>
					<td width="20%"><s:hidden name="initiatorCode" /> <s:property
						value="initiatorName" /></td>
					<td width="20%"><b>Completed On:</b></td>
					<td width="20%"><s:hidden name="initiatorDate"></s:hidden> <s:property
						value="initiatorDate" /></td>
				</tr>
			</table>
			</td>
		</tr>


		<!-- Initiator Details End -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
	</table>

<s:hidden name="source" id="source" /> 
</s:form>

<script>
 	function callLoanType(){
	
	
	
	var loanCode= document.getElementById('paraFrm_loanCode').value;
	var pfLoanCode =document.getElementById('paraFrm_pfLoanCode').value;
		if(loanCode==pfLoanCode && pfLoanCode!=""){
			document.getElementById('subTypeTD1').style.display='';
			document.getElementById('subTypeTD2').style.display='';
			document.getElementById('pfBalanceTD1').style.display='';
			document.getElementById('pfBalanceTD2').style.display='';
			document.getElementById('limitTR').style.display='';
			document.getElementById('purposeTR').style.display='';
		}else{
			document.getElementById('subTypeTD1').style.display='none';
			document.getElementById('subTypeTD2').style.display='none';
			document.getElementById('pfBalanceTD1').style.display='none';
			document.getElementById('pfBalanceTD2').style.display='none';
			document.getElementById('limitTR').style.display='none';
			document.getElementById('purposeTR').style.display='none';
		}
	}
	
	onloadFun();
	function onloadFun(){
		callLoanType();
		var loanSubType = document.getElementById('paraFrm_hiddenLoanSubType').value ;
		document.getElementById('paraFrm_loanSubType'+loanSubType).checked =true;
		
	}
	
	function backtolistFun_old()
		{
			try{
					document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					document.getElementById('paraFrm').submit();
			}
			catch(e)
			{
				//alert("Error-------"+e);
			}
		}
		
		function backtolistFun(){
			document.getElementById('paraFrm').target = "_self";
			//this is for mypage back button
			if(document.getElementById('source').value=='mymessages')
			{
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			}
			else{
			document.getElementById('paraFrm').action = 'LoanApproval_callBack.action';
			}
			document.getElementById('paraFrm').submit();

		}
		
		
		
		
		function approveFun() {
		var vv=confirm("Do you really want to approve this application?");
		if(vv){
		try{
			document.getElementById('paraFrm_applicationStatus').value = 'A';	
			var levelVal = document.getElementById('paraFrm_level').value;
			///alert(levelVal);
			
			var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value;
			///alert(loanCodeVal);
			
			var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
			
			var apprAmount = document.getElementById('paraFrm_apprLoanAmount').value;
			
			if(apprAmount==""){
				alert("Please enter "+document.getElementById('appr.amt').innerHTML.toLowerCase());
					document.getElementById("paraFrm_apprLoanAmount").focus();
				 	return false;
			}
			
			var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
			///alert(loginVal);
		
		
		} catch(e)
		
		{
			///alert(e);
		}
		
		if(loginVal=='H'){
		
		
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'LoanProcessing_saveHrApproval.action?status='+'A'+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount+'&loginVal='+loginVal;
			document.getElementById('paraFrm').submit();
			
		} else {
		
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'LoanApproval_saveApproval.action?status='+'A'+'&levelVal='+levelVal+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
			document.getElementById('paraFrm').submit();
		
		 }
		
		}
		
		
		//alert(document.getElementById('paraFrm_applicationStatus').value);
			
		
	}
	
	function rejectFun() {
	var vv=confirm("Do you really want to reject this application?");
	if(vv){
	
	var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
	
		document.getElementById('paraFrm_applicationStatus').value = 'B';	
			var levelVal = document.getElementById('paraFrm_level').value
			var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value
			var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
			
				var apprAmount = document.getElementById('paraFrm_apprLoanAmount').value
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'LoanApproval_sendBackApplication.action?status='+'R'+'&levelVal='+levelVal+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
				document.getElementById('paraFrm').submit();
			
		}
	}
	function sendbackFun() {
	try{
	var vv=confirm("Do you really want to sendBack this application?");
	if(vv){
	
	var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
		
			document.getElementById('paraFrm_applicationStatus').value = 'B';	
			var levelVal = document.getElementById('paraFrm_level').value
			var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value
			var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
			
				var apprAmount = document.getElementById('paraFrm_apprLoanAmount').value
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'LoanApproval_sendBackApplication.action?status='+'B'+'&levelVal='+levelVal+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
				document.getElementById('paraFrm').submit();
			
		}
	}catch(e){
	alert(e);
	}
	
	}
	
	function validateAmountLimit(){
	try{
	var amtValue = document.getElementById('paraFrm_loanAllowedLimit').value;
	if(amtValue!='0'){
	if (parseFloat((document.getElementById('paraFrm_apprLoanAmount').value)) > (parseFloat(document.getElementById('paraFrm_loanAllowedLimit').value))) {
    			 alert("Amount must be less than "+document.getElementById('paraFrm_loanAllowedLimit').value);
    			
    			document.getElementById('paraFrm_apprLoanAmount').focus();
    			 document.getElementById('paraFrm_apprLoanAmount').value='';
		 		return false;
			}
			}
			
			var apprLoanAmountValue = document.getElementById('paraFrm_apprLoanAmount').value;
			if(apprLoanAmountValue!=""){
			if(Math.abs(apprLoanAmountValue)==0){
 		 	alert("Zero not allowed in "+ document.getElementById('appr.amt').innerHTML.toLowerCase());
 		 	
 		 	document.getElementById('paraFrm_apprLoanAmount').value="";
 		 	document.getElementById('paraFrm_apprLoanAmount').focus();
 		 	return false;
 		 	}
 		 	}
 		 	if (parseFloat((document.getElementById('paraFrm_apprLoanAmount').value)) > (parseFloat(document.getElementById('paraFrm_loanAmount').value))) {
    			 alert("Amount must be less than "+document.getElementById('paraFrm_loanAmount').value);
    			
    			document.getElementById('paraFrm_apprLoanAmount').focus();
    			 document.getElementById('paraFrm_apprLoanAmount').value='';
		 		return false;
			}
 		 	
 		 	
			
	}catch(e){
		//alert(e);
	}
	}
	
	function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}

function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}

</script>