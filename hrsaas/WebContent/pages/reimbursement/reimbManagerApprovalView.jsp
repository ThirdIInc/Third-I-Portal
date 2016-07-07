<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form name="paraFrm" action="ReimbManagerApproval" id="paraFrm" theme="simple">

<s:hidden name="listType" />
<s:hidden name="claimId" />
<s:hidden name="employeeId" />
<s:hidden name="approverId" />
<s:hidden name="creditCode" />
<s:hidden name="level" />
<s:hidden name="adminStatus" />

<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement
					Details </strong></td>
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
					<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="commentListFlag">
		<tr>
			<td>
				<table class="formbg" width="100%">
					<tr>
						<td width="25%"><label class="set" name="approverComm"
							id="approverComm" ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label>
						<font color="red">*</font>:</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" /></textarea>
						</td>
					</tr>

				</table>
				</td>
			</tr>
			</s:if>
			<s:if test="approverCommentListFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td width="100%" colspan="5">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="5"><strong>
								Previous Approver Comments :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="5%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="12%" height="22" valign="top">
								Date </td>
								<td class="formth" width="30%" height="22" valign="top" colspan="2">Comments
								</td>
							</tr>
							<%
							int m = 1;
							%>
				<s:iterator value="approverCommentList" status="stat">
					<tr>
							<td width="5%" class="sortableTD"><%=m%><s:hidden name="appSrNo" value="%{<%=m%>}"/> </td>
							<td  class="sortableTD"><s:property value="prevApproverNameItt"/><s:hidden name="prevApproverNameItt"/></td>
							<td  class="sortableTD" align="center"><s:property value="prevApproverDateItt"/><s:hidden name="prevApproverDateItt"/></td>
							<td  class="sortableTD" colspan="2">&nbsp;<s:property value="prevApproverCommentItt"/><s:hidden name="prevApproverCommentItt"/></td>
					</tr>
							<%
							m++;
							%>
					</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			</s:if>
			<!-- Employee Information block begins -->
			<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg" cellpadding="2" cellspacing="5">
				<tr>
					<td><label class="set" name="empName1" id="empName1"
						ondblclick="callShowDiv(this);"><%=label.get("empName")%></label>:
					</td>
					<td colspan="3"><s:property value="employeeToken" /> <s:property
						value="employeeName" /></td>
				</tr>
				<tr>
					<td><label class="set" name="desig" id="desig1"
						ondblclick="callShowDiv(this);"><%=label.get("desig")%>
					</label> :</td>
					<td><s:property value="employeeDesignation" /></td>
					<td><label class="set" name="branch" id="branch1"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%>
					</label> :</td>
					<td><s:property value="employeeBranch" /></td>
				</tr>
				<tr>
					<td width="25%"><label class="set" name="grade"
						id="grade1" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td width="25%"><s:property value="employeeGrade" />
					</td>
					<td width="25%"><label class="set" name="claim.date"
						id="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label>:</td>
					<td width="25%"><s:property value="claimDate" />
					</td>
				</tr>
				<tr>
					<td><label class="set" name="div" id="div"
						ondblclick="callShowDiv(this);"><%=label.get("div")%></label>
					:</td>
					<td><s:property value="employeeDivision" /></td>
					<td width="20%"><label class="set" name="appId" id="appId1"
						ondblclick="callShowDiv(this);"><%=label.get("appId")%></label>
					:</td>
					<td><s:property value="applicationId"/></td>
				</tr>
				<!--  <tr>
					<td><label class="set" name="reimbUnder" id="reimbUnder"
						ondblclick="callShowDiv(this);"><%//=label.get("reimbUnder")%></label>
					:</td>
					<td><s:property value="reimbHead" /> </td>
					<td colspan="2">&nbsp;</td>
				</tr>
				-->
			</table>
			</td>
		</tr>
		<!-- Employee Information block ends -->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label class="set" id="billDtls" name="billDtls"
						ondblclick="callShowDiv(this);"><%=label.get("billDtls")%></label> :</strong>
					</td>
				</tr>

				<tr>
					<td width="20%"><label class="set" id="eligible.amount"
						name="eligible.amount" ondblclick="callShowDiv(this);"><%=label.get("eligible.amount")%></label></td>
					<td width="30%"><s:property value="reimburseBalance" /></td>
					<td width="20%"><label class="set" id="bills.carried.forward"
						name="bills.carried.forward" ondblclick="callShowDiv(this);"><%=label.get("bills.carried.forward")%></label>:</td>
					<td width="30%" colspan="2"><s:property
						value="billsCarriedForward" /><s:hidden
						name='billsCarriedForward' /></td>
				</tr>
				<tr>
					<td width="20%" colspan="2"></td>
					<td width="30%" colspan="3"></td>
				</tr>
				<s:if test="preApprovedListFlag">
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead"> Pre-Approved Bill Details</strong></td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="formth" width="05%">Sr.No.</label></td>
								<td class="formth" width="05%">Pre-Approved Amount</label></td>
								<td class="formth" width="15%" align="center" colspan="2">Pre-Approved
								Bills</label></td>
								<%
									int preCnt = 0;
									int preCnt1 = 0;
								%>

								<s:iterator value="preApprovedList">

									<tr>
										<td width="10%" class="sortableTD"><%=++preCnt%></td>
										<td width="30%" class="sortableTD"><s:property
											value="prevProofAmt" /><s:hidden name="prevProofAmt" /></td>
										<td width="40%" class="sortableTD"><s:iterator
											value="preApprovedProofList">
											<table>
												<tr>
													<td><s:property value="prevProofRefNo" />-- <a
														href="#"
														onclick="return viewProof('<s:property value="prevProofFileName" />');">
													<font color="blue"><s:property
														value="prevProofFileName" /></font> <s:hidden
														name="prevProofRefNo" /> <s:hidden
														name="preProofFileName" /></a></td>
												</tr>
											</table>
										</s:iterator>
									</tr>
									<%
									preCnt1 = preCnt;
									%>
								</s:iterator>
								<%
								preCnt1 = 0;
								%>
							
						</table>
						</td>

					</tr>

				</s:if>


			</table>
			</td>
		</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label class="set" id="reimbursementDtls" name="reimbursementDtls"
						ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtls")%></label>
					:</strong></td>
				</tr>
				<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td class="formth" width="5%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="25%" height="22" valign="top">
								Reimbursement Head</td>
								<td class="formth" width="25%" height="22" valign="top">
								Expense Date</td>
								<td class="formth" width="10%" height="22" valign="top">
								Claim Amount </td>
								<td class="formth" width="10%" height="22" valign="top">
								Bill Amount </td>
								<td class="formth" width="30%" height="22" valign="top">View Proofs
								</td>
							</tr>
							<%
							int Z = 1;
							%>
				<s:iterator value="expenseList">
					<tr>
							<td width="5%" class="sortableTD"><%=Z%><s:hidden name="appSrNo" value="%{<%=Z%>}"/> </td>
							<td  class="sortableTD"><s:property value="headItt"/><s:hidden name="headCodeItt"/><s:hidden name="headItt"/></td>
							<td  class="sortableTD"><s:property value="expenseDateItt"/></td>
							<td  class="sortableTD" align="right"><s:property value="expenseClaimAmountItt"/></td>
							<td  class="sortableTD" align="right"><s:property value="expenseBillAmountItt"/></td>
							<td  class="sortableTD">
							<s:iterator value="listProof">
							<table>
							<tr>
							<td  width="15%" align="left"><s:property value="proofReferenceIdItt" />-<a href="#"  onclick="return viewProof('<s:property value="proofDocItt" />')"><font color="blue"><s:property value="proofDocItt" /></font></a></td>
							</tr>
							</table>
							</s:iterator>
							&nbsp;</td>
					</tr>
							<%
							Z++;
							%>
					</s:iterator>
							<tr>
									<td class="sortableTD" width="5%">&nbsp;</td>
									<td class="sortableTD" width="15%" align="center">Total</td>
									<td class="sortableTD" width="10%" align="right">&nbsp;<s:property value="claimAmountTotal" /><s:hidden name="claimAmountTotal" /></td>
									<td class="sortableTD" width="10%" align="right">&nbsp;<s:property value="billAmountTotal" /><s:hidden name="billAmountTotal" /></td>
									<td class="sortableTD" width="15%" align="center">&nbsp;</td>
									<td class="sortableTD" width="15%" align="center">&nbsp;</td>
							</tr>
							
							
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="25%"><label class="set" name="claim.particular"
						id="claim.particular" ondblclick="callShowDiv(this);"><%=label.get("claim.particular")%></label>
					:</td>
					<td colspan="3"><s:property value="claimParticulars" /></td>
					
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
</table>
<s:hidden name="source" id="source" />
</s:form>
<script>
	function backFun_old(){
		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action="ReimbManagerApproval_back.action";
		document.getElementById('paraFrm').submit();
	}
	function backFun() 
		{
			try{
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
				document.getElementById('paraFrm').submit();
			}
			catch(e)
			{
				alert("Error-------"+e);
			}
	}
	
	function approveFun(){
	if(!validate()){
		return false;	
	}else {
	if(!confirmProcess()){
		return false;
	}
		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action='ReimbursementClmAppl_approveRejectSendBack.action?btnStatus=A';
		document.getElementById('paraFrm').submit();
		}
		
	}
	
	function rejectFun(){
	if(!validate()){
		return false;	
	}else {
	if(!confirmProcess()){
		return false;
	}
		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action='ReimbursementClmAppl_approveRejectSendBack.action?btnStatus=R';
		document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun(){
	if(!validate()){
		return false;	
	}else {
	if(!confirmProcess()){
		return false;
	}
		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action='ReimbursementClmAppl_approveRejectSendBack.action?btnStatus=B';
		document.getElementById('paraFrm').submit();
		}
	}
	function confirmProcess(){
			var conf=confirm('Do you really want to process the application?');
			 	if(!conf){
			 	return false;
			 	}
			 return true;
	}	
	function validate(){
		var commnets=document.getElementById('paraFrm_approverComments').value;
		
		var field=['paraFrm_approverComments'];
		var label=['approverComm'];
		var flag=['enter'];
		
		if(!validateBlank(field,label,flag)){
			return false;
		}
		return true;
	}
	function viewProof(fileName)
	{
		if(fileName==" "){
			alert("proof document not uploaded.");
			return false;
		}
		else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ReimbManagerApproval_viewProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}
</script>
		
