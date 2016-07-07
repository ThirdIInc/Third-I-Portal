<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbAdminApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Claim
					Reimbursement</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="25%"><label class="set" id="approver.comments"
							name="approver.comments" ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label><font
							color="red">*</font> :</td>
						<td width="75%" colspan="3"><s:textarea cols="100" rows="3"
							name='approverComments'>
						</s:textarea></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:if test="approverListFlag">
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="4"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="5%" height="22" valign="top"><label
									class="set" id="srno1" name="srno"
									ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
								<td class="formth" width="25%" height="22" valign="top"><label
									class="set" id="approver.name" name="approver.name"
									ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label></td>
								<td class="formth" width="40%" height="22" valign="top"><label
									class="set" id="approver.comments1" name="approver.comments"
									ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label></td>
								<td class="formth" width="15%" height="22" valign="top"><label
									class="set" id="approval.date" name="approval.date"
									ondblclick="callShowDiv(this);"><%=label.get("approval.date")%></label></td>

							</tr>


							<%
							int m = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="5%" class="sortableTD"><%=m%><s:hidden
										name="appSrNo" value="%{<%=m%>}" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="approverNameList" /><s:hidden name="approverNameList" /></td>
									<td width="40%" class="sortableTD"><s:property
										value="approverCommentsList" /><s:hidden
										name="approverCommentsList" /></td>
									<td width="15%" class="sortableTD" align="center"><s:property
										value="approverDateList" /><s:hidden name="approverDateList" /></td>
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
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" nowrap="nowrap" colspan="4"><strong>
					Employee Details :</strong></td>

				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set" id="employee1"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="80%" colspan="3"><s:hidden name="empId"
						theme="simple" /> <s:property value='empName' /><s:hidden
						name='applId'></s:hidden><s:hidden name='empName'></s:hidden></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" height="22"><s:property value='designation' /><s:hidden
						name='designation'></s:hidden></td>
					<td width="20%" colspan="1"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1" height="22"><s:property
						value='branch' /><s:hidden name='branch' /></td>


				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><s:hidden
						name="reimbHead" /><label class="set" id="grade" name="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="30%" colspan="1"><s:property value='grade' /><s:hidden
						name='grade' /></td>
					<td width="20%" colspan="1"><label class="set"
						id="claim.date4" name="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value='claimDate' /><s:hidden
						name='claimDate' /></td>


				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="division" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="30%" colspan="1"><s:hidden name="divisionCode" /><s:property
						value='division' /><s:hidden name='division' /></td>
					<td width="20%" colspan="1"><label class="set" id="appl.No"
						name="appl.No" ondblclick="callShowDiv(this);"><%=label.get("appl.No")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value='applRefNo' /><s:hidden
						name='applRefNo' /></td>


				</tr>

			</table>
			</td>
		</tr>
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
							class="forminnerhead"> Pre-Approved Bill Details </strong> </td>
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
										</s:iterator></td>
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
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label class="set" id="reimbursementDtls" name="reimbursementDtls"
						ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtls")%></label> :</strong>
					</td>
				</tr>

				<tr>
					<td colspan="8">
					<table width="100%">
						<tr>
							<td class="formth" width="5%"><label id="srno" name="srno"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
							<td class="formth" width="15%" align="center"><label
								id="reimbHead" name="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
							<td class="formth" width="15%" nowrap="nowrap"><label
								id="expense.date1" name="expense.date"
								ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></td>
							<td class="formth" width="5%" align="center"><label
								id="claimAmt" name="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
							<td class="formth" width="5%" align="center"><label
								id="approved.Amt" name="approved.Amt"
								ondblclick="callShowDiv(this);"><%=label.get("approved.Amt")%></label></td>
							<td class="formth" width="5%" align="center"><label
								id="billAmt" name="billAmt" ondblclick="callShowDiv(this);"><%=label.get("billAmt")%></label></td>
							<td class="formth" width="5%" align="center"><label
								id="approvedBillAmt" name="approvedBillAmt"
								ondblclick="callShowDiv(this);"><%=label.get("approvedBillAmt")%></label></td>
							<td class="formth" width="35%"><label id="proof1"
								name="proof" ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></td>

						</tr>
						<%
						int srNo = 0;
						%>
						<s:iterator value="expenseList">
							<tr>
								<td class="sortableTD" width="5%"><%=++srNo%></td>
								<td class="sortableTD" width="15%" align="center"><s:property
									value="reimbHeadList" /></td>
								<td class="sortableTD" width="10%" align="center"><s:property
									value="expenseDate" /> <s:hidden name="reimbHeadCodeList" /><s:hidden name="expenseDate" /></td>
								<td class="sortableTD" width="5%" align="right"><s:property
									value="claimAmtHead" /><s:hidden name="claimAmtHead"
									id='<%="claimAmtHead"+srNo%>' /></td>
								<td class="sortableTD" width="5%" size="10" align="right"><s:textfield
									name="approvedAmt" size="20"
									onkeypress="return numbersWithDot();"
									id='<%="approvedAmt"+srNo%>' cssStyle=" text-align: right"
									onkeyup="return callTotalAmount();"></s:textfield></td>
								<td class="sortableTD" width="5%" align="right">&nbsp;<s:property
									value="billAmt" /><s:hidden name="billAmt"
									id='<%="billAmt"+srNo%>' /></td>
								<td class="sortableTD" width="5%" size="10" align="right"><s:textfield
									name="approvedBillAmt" size="20"
									onkeypress="return numbersWithDot();"
									id='<%="approvedBillAmt"+srNo%>' cssStyle=" text-align: right"
									onkeyup="return callBillTotalAmount();"></s:textfield></td>
								<td class="sortableTD" width="35%" colspan="2"><s:hidden
									name='proofHidden' /><s:hidden name='proofRefNoHidden' />
								<table width="100%">
									<s:iterator value="proofList">
										<tr>
											<td width="15%" align="left"><s:property
												value="proofRefNo" />-<a href="#"
												onclick="return viewProof('<s:property value="proof" />')"><font
												color="blue"><s:property value="proof" /></font></a></td>
										</tr>
									</s:iterator>
								</table>

								</td>
							</tr>

						</s:iterator>
						<input type="hidden" value="<%=srNo%>" name='count' id='count'>
						<tr>
							<td class="sortableTD" width="5%">&nbsp;</td>
							<td class="sortableTD" width="15%" align="center">&nbsp;</td>
							<td class="sortableTD" width="15%" align="center">Total</td>
							<td class="sortableTD" width="5%" align="right">&nbsp;<s:textfield
								name="totalClaimAmt" cssStyle=" border: none;text-align: right"
								readonly="true" /></td>
							<td class="sortableTD" width="5%" align="right">&nbsp;<s:textfield
								name="totalApprovedAmt"
								cssStyle=" border: none;text-align: right" readonly="true" /></td>
							<td class="sortableTD" width="5%" align="right">&nbsp;<s:textfield
								name="totalBillAmt" cssStyle=" border: none;text-align: right"
								readonly="true" /></td>
							<td class="sortableTD" width="5%" align="right">&nbsp;<s:textfield
								name="totalApprovedBillAmt"
								cssStyle=" border: none;text-align: right" readonly="true" /></td>
							<td class="sortableTD" width="35%">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
			<s:hidden name="listType" />
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="25%"><label class="set" id="claim.particular"
						name="claim.particular" ondblclick="callShowDiv(this);"><%=label.get("claim.particular")%></label>
					:</td>
					<td width="75%" colspan="3"><s:property
						value='claimParticular' /><s:hidden name='claimParticular' /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
<s:hidden name="source" id="source" />
<s:hidden name="approverId" />


</s:form>
<script>
function viewProof(fileName)
	{
		if(fileName==" "){
			alert("proof document not uploaded.");
			return false;
		}
		else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ReimbAdminApproval_viewProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}
	function backFun_old(){
		var listType=document.getElementById('paraFrm_listType').value;
		if(listType=='pending'){
			document.getElementById('paraFrm').action='ReimbAdminApproval_getAdminPendingList.action';
		}else{
			document.getElementById('paraFrm').action='ReimbAdminApproval_getProcessedList.action';
		}
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
		var listType=document.getElementById('paraFrm_listType').value;
		if(!validate()){
		return false;
		}
		if(!validateApprovedAmt()){
		return false;
		}
		if(!validateApprovedBillAmt()){
		return false;
		}
		if(!confirmProcess()){
		return false;
	}
		if(listType=='pending'){
			document.getElementById('paraFrm').action='ReimbursementClmAppl_approveClaimAppl.action?status=A';
		}else{
			alert('Appliaction is already processed');
			return false;
		}
		document.getElementById('paraFrm').submit();
	}
	function rejectFun(){
		var listType=document.getElementById('paraFrm_listType').value;
		if(!validate()){
		return false;
		}
		if(!confirmProcess()){
		return false;
	}
		////if(listType=='pending'){
			document.getElementById('paraFrm').action='ReimbursementClmAppl_rejectClaimAppl.action?status=R';
		///}else{
		///	alert('Appliaction is already processed');
		///	return false;
		///}
		document.getElementById('paraFrm').submit();
	}
	function sendbackFun(){
		var listType=document.getElementById('paraFrm_listType').value;
		if(!validate()){
		return false;
		}
	if(!confirmProcess()){
		return false;
	}
		///if(listType=='pending'){
			document.getElementById('paraFrm').action='ReimbursementClmAppl_sendBackClaimAppl.action?status=B';
		///}else{
		///	alert('Appliaction is already processed');
		///	return false;
		///}
		document.getElementById('paraFrm').submit();
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
		var label=['approver.comments'];
		var flag=['enter'];
		
		if(!validateBlank(field,label,flag)){
			return false;
		}
		return true;
	}
	function callTotalAmount(){
		var countAmt =document.getElementById('count').value;
		var totalAmt=0;
		//'alert(countAmt);
		try{
		for(x=1;x<=countAmt;x++){
		//alert(x);
		//alert(eval(document.getElementById('approvedAmt'+x).value));
		if(document.getElementById('approvedAmt'+x).value==''){
			document.getElementById('approvedAmt'+x).value=0;
		}
		
			totalAmt = totalAmt+eval(document.getElementById('approvedAmt'+x).value);
		}
		}catch(e){
			alert(e);
		}
		document.getElementById('paraFrm_totalApprovedAmt').value=totalAmt;
	}
	
	function callBillTotalAmount(){
		var countAmt =document.getElementById('count').value;
		var totalAmt=0;
		//'alert(countAmt);
		try{
		for(x=1;x<=countAmt;x++){
		//alert(x);
		//alert(eval(document.getElementById('approvedAmt'+x).value));
		if(document.getElementById('approvedBillAmt'+x).value==''){
			document.getElementById('approvedBillAmt'+x).value=0;
		}
		
			totalAmt = totalAmt+eval(document.getElementById('approvedBillAmt'+x).value);
		}
		}catch(e){
			alert(e);
		}
		document.getElementById('paraFrm_totalApprovedBillAmt').value=totalAmt;		
	}
	function validateApprovedAmt(){
		var countAmt =document.getElementById('count').value;
		var totalAmt=0;
		//'alert(countAmt);
		try{
		for(x=1;x<=countAmt;x++){
		if(document.getElementById('approvedAmt'+x).value==''){
			document.getElementById('approvedAmt'+x).value=0;
		}
		var claimAmt=document.getElementById('claimAmtHead'+x).value;
		var approvedAmt=document.getElementById('approvedAmt'+x).value;
		if(eval(claimAmt)<eval(approvedAmt)){
			alert(document.getElementById('approved.Amt').innerHTML+" should be less than or equal to "+document.getElementById('claimAmt').innerHTML);
			return false;
		}
		}
		}catch(e){
			alert(e);
		}
		return true;
	}
	function validateApprovedBillAmt(){
		var countAmt =document.getElementById('count').value;
		var totalAmt=0;
		//'alert(countAmt);
		try{
		for(x=1;x<=countAmt;x++){
		if(document.getElementById('approvedBillAmt'+x).value==''){
			document.getElementById('approvedBillAmt'+x).value=0;
		}
		var claimAmt=document.getElementById('billAmt'+x).value;
		var approvedAmt=document.getElementById('approvedBillAmt'+x).value;
		if(eval(claimAmt)<eval(approvedAmt)){
			alert(document.getElementById('approvedBillAmt').innerHTML+" should be less than or equal to "+document.getElementById('billAmt').innerHTML);
			return false;
		}
		}
		}catch(e){
			alert(e);
		}
		return true;
	}
	
</script>
