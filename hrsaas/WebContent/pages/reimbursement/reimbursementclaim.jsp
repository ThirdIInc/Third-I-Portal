<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbursementClmAppl" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Claim  qwe
					Reimbursement </strong></td>
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
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>

							</tr>

							<%
							int i = 0;
							%>
							<%
							int k = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=k%><s:hidden
										name="appSrNo" value="%{<%=k%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"
										nowrap="nowrap"><s:property value="prevApproverDate" /><s:hidden
										name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
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
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td width="20%" colspan="1"><label class="set" id="employee1"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font> :</td>
					<td width="80%" colspan="4"><s:hidden name="empId"
						theme="simple" /> <s:property value="empToken" /> <s:property
						value="empName" /><s:hidden name="empName" /><s:hidden
						name="empToken" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%" height="22" colspan="2"><s:property
						value="designation" /><s:hidden name="designation" /></td>
					<td width="20%" colspan="1"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="2" height="22"><s:property
						value="branch" /><s:hidden name="branch" /></td>


				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="grade" name="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="25%" colspan="2"><s:property value="grade" /><s:hidden
						name="grade" /></td>
					<td width="20%" colspan="1"><label class="set"
						id="claim.date4" name="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label><font
						color="red">*</font> :</td>
					<s:if test="%{bean.showFlag}">
						<td width="15%" colspan="1"><s:textfield theme="simple"
							name="claimDate" onkeypress="return numbersWithHiphen();"
							size="12" /></td>

						<td width="15%" colspan="1"><s:a
							href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16" id="ctrlHide">
						</s:a></td>
					</s:if>
					<s:else>
						<td width="30%" colspan="2"><s:property value="claimDate" /><s:hidden
							name="claimDate" /></td>
					</s:else>


				</tr>
				<s:if test="%{bean.showFlag}"></s:if>
				<s:else>
					<tr>
						<td width="20%" colspan="1" class="formtext"><label
							class="set" id="application.id" name="application.id"
							ondblclick="callShowDiv(this);"><%=label.get("application.id")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property value="referenceId" /></td>
					</tr>
				</s:else>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="division" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<s:if test="%{bean.showFlag}">
						<td width="25%" colspan="1"><s:hidden name="divisionCode" />

						<s:textfield theme="simple" size="25" readonly="true"
							name="division" />
						<td width="5%" colspan="1"><img id="ctrlHide"
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'ReimbursementClmAppl_f9division.action');">
						</td>
					</s:if>
					<s:else>
						<td width="30%" colspan="1"><s:property value="division" /><s:hidden
							name="division" /></td>
					</s:else>

				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<!-- <tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead"> <label class="set"
							id="billDtl" name="billDtl"
							ondblclick="callShowDiv(this);"><%//=label.get("billDtl")%></label></strong>
						</td>
					</tr> -->
				<s:if test="preApprovedListFlag">
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead"> Pre-Approved Bill Details</strong></td>
					</tr>
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="formth" width="05%">Sr.No.</td>
								<td class="formth" width="05%">Pre-Approved Amount</td>
								<td class="formth" width="15%" align="center">Pre-Approved
								Bills</td>
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
														onclick="showRecord('<s:property value="prevProofFileName" />');">
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

		<s:if test="showFlag">
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead"> <label class="set"
							id="reimbursementDtl" name="reimbursementDtl"
							ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtl")%></label></strong>
						</td>
					</tr>

					<tr>
						<td width="20%"><label class="set" id="reimbursementType1"
							name="reimbursementType" ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label><font
							color="red">*</font>:</td>
						<td width="20%"><s:hidden name="reimbursementCrediCode" /><s:hidden
							name="reimbursementCrediName" /> <s:property
							value="reimbursementCrediName" /></td>
						<td width="20%"><label class="set" id="eligible.amount"
							name="eligible.amount" ondblclick="callShowDiv(this);"><%=label.get("eligible.amount")%></label></td>
						<td width="40%" colspan="2"><s:property
							value="reimbursBalance" /></td>

					</tr>
					<tr>

						<td width="20%"><label class="set"
							id="bills.carried.forward" name="bills.carried.forward"
							ondblclick="callShowDiv(this);"><%=label.get("bills.carried.forward")%></label>
						:</td>
						<td width="20%"><s:property value="billsCarriedForward" /><s:hidden
							name='billsCarriedForward' /></td>
						<td width="60%" colspan="3"><label class="set"
							id="min.bill.amount" name="min.bill.amount"
							ondblclick="callShowDiv(this);"><%=label.get("min.bill.amount")%></label>
						<s:property value="minBillAmount" /><s:hidden
							name='minBillAmount' /></td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="claim.amount"
							name="claim.amount" ondblclick="callShowDiv(this);"><%=label.get("claim.amount")%></label>
						<font color="red">*</font> :
						<td width="20%"><s:textfield size="15" name="claimAmount"
							maxlength="7" onkeypress="return checkNumbersWithDot(this);" /></td>
						<td width="20%"><label class="set" id="expense.date"
							name="expense.date" ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label><font
							color="red">*</font>:</td>
						<td width="15%" colspan="1"><s:textfield theme="simple"
							name="expenseDate" onkeypress="return numbersWithHiphen();"
							size="12" /></td>
						<td width="25%" colspan="1"><s:a
							href="javascript:NewCal('paraFrm_expenseDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16" id="ctrlHide">
						</s:a></td>
					</tr>
					<tr>

						<td width="20%" nowrap="nowrap"><label class="set"
							id="bill.amount" name="bill.amount"
							ondblclick="callShowDiv(this);"><%=label.get("bill.amount")%></label>
						<font color="red">*</font>:</td>
						<td width="20%"><s:textfield size="15" name="billAmount"
							maxlength="7" onkeypress="return checkNumbersWithDot(this);" /></td>
						<td width="20%"><label class="set" id="proof" name="proof"
							ondblclick="callShowDiv(this);"><%=label.get("proof")%></label> :</td>
						<td width="40%" colspan="2"><s:textfield
							name="userUploadFileName" size="12" maxlength="40"
							onfocus="clearText('paraFrm_userUploadFileName','Bill Reference No.')"
							onblur="setText('paraFrm_userUploadFileName','Bill Reference No.')" />
						<s:textfield name="uploadFileName" readonly="true" size="15" /></td>
					</tr>
					<tr>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="40%" colspan="2"><input name="Upload"
							type="button" class="token" value="Select Proof"
							onclick="uploadFile('uploadFileName');" /> 
							
							<s:submit name=""
							value="Upload Proof" cssClass="token"
							action="ReimbursementClmAppl_addMultipleProof"
							onclick="return callUpload();" />
						
							</td>
					</tr>



					<tr>
						<td colspan="2" width="40%"></td>
						<td colspan="3" width="60%">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<%
								int cnt = 0;
								int cnt1 = 0;
							%>

							<s:iterator value="proofList" status="stat">

								<tr>

									<td width="40%" colspan="3"><s:hidden name="proofSrNo" /><s:hidden
										name="proofName" /> <s:hidden name="proofFileName" /> <s:property
										value="proofFileName" /> -- <a href="#"
										onclick="showRecord('<s:property value="proofName" />');">
									<font color="blue"><s:property value="proofName" /></font> </a></td>

									<td width="60%"><a href="#"
										onclick="callForRemoveUpload(<%=++cnt%>);">Remove Proof</a></td>
								</tr>
								<%
								cnt1 = cnt;
								%>
							</s:iterator>
							<input type="hidden" name='count' id='proofCount'
								value='<%=cnt%>' />
							<%
							cnt1 = 0;
							%>
						</table>
						</td>

					</tr>


					<tr>
						<td colspan="5" align="center"><input type="button"
							class="add" value="   Add" onclick="return callAdd();" /> <input
							type="button" class="reset" value="  Clear"
							onclick="return callClear();" /></td>
					</tr>

				</table>
				</td>
			</tr>

		</s:if>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="5">
					<table width="100%">
						<tr>
							<td class="formth" width="05%"><label id="sno" name="sno"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
							<td class="formth" width="15%" align="center"><label
								id="reimbursementType2" name="reimbursementType"
								ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label></td>
							<td class="formth" width="15%"><label id="expense.date1"
								name="expense.date" ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></td>
							<td class="formth" width="10%" align="center" nowrap="nowrap"><label
								id="claim.amount1" name="claim.amount"
								ondblclick="callShowDiv(this);"><%=label.get("claim.amount")%></label></td>
							<td class="formth" width="10%" align="center" nowrap="nowrap"><label
								id="bill.amount1" name="bill.amount"
								ondblclick="callShowDiv(this);"><%=label.get("bill.amount")%></label></td>
							<td class="formth" width="50%"><label id="proof1"
								name="proof" ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></td>
							<s:if test="%{bean.showFlag}">
								<td class="formth" width="10%" align="center">Delete</td>
							</s:if>
						</tr>

						<%!int y = 0;%>
						<%
						int count = 0;
						%>


						<s:iterator value="list">
							<tr>
								<td class="sortableTD" width="5%"><%=++count%>&nbsp; <s:hidden
									name="srNo" value="%{<%=count%>}" /></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"
									align="center">&nbsp;<s:property
									value="reimbursementCrediNameItt" /> <s:hidden
									name="reimbursementCrediNameItt" /> <s:hidden
									name="reimbursementCrediCodeItt" /></td>
								<td class="sortableTD" width="10%" nowrap="nowrap"
									align="center">&nbsp;<s:hidden name="expenseDateItt" /> <s:property
									value="expenseDateItt" /></td>
								<td class="sortableTD" width="10%" nowrap="nowrap" align="right">&nbsp;<input
									type="hidden" name="claimAmountItt"
									value="<s:property value="claimAmountItt"/>"
									id="claimAmountItt<%=count %>" /> <s:property
									value="claimAmountItt" /> <s:hidden name="uploadDoc" /> <s:hidden
									name="uploadDocPath" /></td>
								<td class="sortableTD" width="10%" nowrap="nowrap" align="right">&nbsp;<input
									type="hidden" name="billAmountItt"
									id="billAmountItt<%=count %>"
									value="<s:property value="billAmountItt"/>" /> <s:property
									value="billAmountItt" /></td>
								<td class="sortableTD" width="50%" align="left"><s:iterator
									value="ittUploadList">
									<table>
										<tr>
											<td><s:property value="uploadDocPath" />-- <a href="#"
												onclick="showRecord('<s:property value="uploadDoc" />');">
											<font color="blue"><s:property value="uploadDoc" /></font> </a>
											</td>
										</tr>
									</table>
								</s:iterator>&nbsp;</td>
								<s:if test="%{bean.showFlag}">
									<td class="sortableTD" width="10%" nowrap="nowrap">&nbsp;
									<input type="button" class="rowDelete"
										onclick="callForDelete(<%=count %>)" /></td>
								</s:if>
							</tr>
						</s:iterator>
						<%
						y = count;
						%>
						<tr>
							<td align="right" colspan="3"><b>Total Amount:</b></td>
							<td align="left" colspan="1"><s:textfield name="totalAmt"
								size="20" id="totalAmt" theme="simple"
								cssStyle="background-color: #F2F2F2;text-align:right"
								readonly="true" /></td>
							<td align="left" colspan="1"><s:textfield
								name="totalBillAmt" size="20" id="totalBillAmt" theme="simple"
								cssStyle="background-color: #F2F2F2;text-align:right"
								readonly="true" /></td>

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
					<td width="20%" colspan="1"><label class="set"
						id="appcomments" name="appcomments"
						ondblclick="callShowDiv(this);"><%=label.get("appcomments")%></label>
					:</td>
					<td width="80%" colspan="3"><s:if test="%{bean.showFlag}">
						<s:textarea theme="simple" cols="80" rows="4" name="comments" />
					</s:if> <s:else>
						<s:property value="comments" />
						<s:hidden name="comments" />
					</s:else></td>
				</tr>

				<tr>
					<td width="20%" colspan="4"><label class="set" id="note"
						name="note" ondblclick="callShowDiv(this);"><%=label.get("note")%></label>
					</td>
					<td width="80%" colspan="3"></td>
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
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	<s:hidden name="dataPath" />
	<s:hidden name="checkRemoveUpload" />
	<s:hidden name="checkEdit" />
	<s:hidden name="hiddenCreditCode" />
	<s:hidden name="hiddenApplicationCode" />
	<s:hidden name="checkReportingStructure" />
	<s:hidden name="reimbursBalance" id="reimbursBalance" />
	<s:hidden name="referenceId" />
	<s:hidden name="applStatus" />
	<s:hidden name="adminStatus" />
	<s:hidden name="onHoldBalance" id="onHoldBalance" />
	<s:hidden name="sysdate" id="sysdate" />
	  <s:hidden name="source" id="source" />

</s:form>

<script>

	

callOnload();

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


function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}



function callClear()
{

document.getElementById('paraFrm_expenseDate').value="";
document.getElementById('paraFrm_claimAmount').value="";
document.getElementById('paraFrm_userUploadFileName').value="";
document.getElementById('paraFrm_uploadFileName').value="";
document.getElementById('paraFrm_billAmount').value="";
callOnload();
}


function callOnload()	{

	try{
	setText('paraFrm_userUploadFileName','Bill Reference No.');
	}catch(e)
	{
	//alert("vishu-fff---------"+e);
	}
  		try{
				var z='<%=y%>';
				 var total=0;
			 	 var total1=0;
			 var billTotal=0;
			  for(var m=1 ; m <= z ; m++){
			  	total =document.getElementById("claimAmountItt"+m).value ;
				 total1=parseFloat(total1)+parseFloat(total);
				 billTotal=parseFloat(billTotal)+parseFloat(document.getElementById("billAmountItt"+m).value);
			  }
			 
			  document.getElementById('totalAmt').value=roundNumber(total1,2);
			   document.getElementById('totalBillAmt').value=roundNumber(billTotal,2);
			   if(isNaN(document.getElementById('totalBillAmt').value)){
			   		document.getElementById('totalBillAmt').value=0;
			   }
			}catch(e)
			{
				//alert("vishu----------"+e);
			}
	          if(document.getElementById('paraFrm_billAmount').value==""){
	          	document.getElementById('paraFrm_billAmount').value="0";
	          } 
		}
		

function backFun_old()
{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReimbursementClmAppl_back.action';
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


function sendforapprovalFun()
{ 
var employee = document.getElementById('paraFrm_empName').value;
	 if(document.getElementById('paraFrm_claimDate').value==''){
		alert("Please enter "+document.getElementById('claim.date4').innerHTML);
		document.getElementById('paraFrm_claimDate').focus();
		return false;
	}else{
		if(!validateDate("paraFrm_claimDate","claim.date4")){
		return false;
		}
	}	
	 
	 if(eval(document.getElementById('totalAmt').value)==0)
	 {
	 		alert("Please add reimbursement details");
	 		return false;
	 }
	 
	   if(eval(document.getElementById('totalAmt').value)>eval(document.getElementById('reimbursBalance').value))
	 {
	 		alert("Claim amount should not be greater than balance amount");
	 		return false;
	 }
	 
	 
	 if(eval(document.getElementById('paraFrm_checkReportingStructure').value)==0)
	 {
		 
 			alert("Reporting structure is not defined for the nmployee\n"+employee);
 			return false;
 		 
	 }
	  if(eval(document.getElementById('paraFrm_checkReportingStructure').value)==1)
	 {
		 
 			alert("Configuration Not Defined");
 			return false;
 		 
	 }
	  var conf=confirm('Do you really want to send the application for approval?');
			 	if(!conf){
			 	return false;
			 	}
	/// for (var i = 0; i < document.all.length; i++) {
	///		if(document.all[i].id == 'sendforapproval') {
			//alert(document.all[i]);
			//document.all[i].value="Saving...";
	///		document.all[i].disabled=true;
	///	}
	//	}
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="ReimbursementClmAppl_save.action?status=P";	
		document.getElementById('paraFrm').submit();

}
 


function draftFun(){

var employee = document.getElementById('paraFrm_empName').value;

	 var totalClaimamount = document.getElementById('totalAmt').value;
	 var eligibleAmt = document.getElementById('reimbursBalance').value;
	 var totalBillAmount = document.getElementById('totalBillAmt').value;
	 var billCarriedForward = document.getElementById('paraFrm_billsCarriedForward').value;
	try{
	if(document.getElementById('paraFrm_claimDate').value==''){
		alert("Please enter "+document.getElementById('claim.date4').innerHTML);
		document.getElementById('paraFrm_claimDate').focus();
		return false;
	}else{
		if(!validateDate("paraFrm_claimDate","claim.date4")){
		return false;
		}
	}	
	 if(eval(totalClaimamount)>eval(eligibleAmt)){
	 	alert('Total claim amount should be less than or equal to '+document.getElementById('eligible.amount').innerHTML);
	 	return false;
	 }
	 if(eval(totalClaimamount)>eval(totalBillAmount+billCarriedForward)){
	 	alert('Total claim amount should be less than or equal to total bill amount +'+document.getElementById('bills.carried.forward').innerHTML);
	 	return false;
	 }
	 if(eval(document.getElementById('totalAmt').value)==0)
	 {
	 		alert("Please add reimbursement details");
	 		return false;
	 }
	 
	  if(eval(document.getElementById('totalAmt').value)>eval(document.getElementById('reimbursBalance').value))
	 {
	 			alert("Claim amount should not be greater than balance amount");
	 		return false;
	 }
	 
	 if(eval(document.getElementById('paraFrm_checkReportingStructure').value)==0)
	 {
		 
 			alert("Reporting structure is not defined for the nmployee\n"+employee);
 			return false;
 		 
	 }
	   if(eval(document.getElementById('paraFrm_checkReportingStructure').value)==1)
	 {
		 
 			alert("Configuration Not Defined");
 			return false;
 		 
	 }
	
	}catch(e){alert(e);}
	
	
	//// for (var i = 0; i < document.all.length; i++) {
		////	if(document.all[i].id == 'draft') {
			//alert(document.all[i]);
			//document.all[i].value="Saving...";
		///	document.all[i].disabled=true;
		///}
		///}
		
		
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="ReimbursementClmAppl_save.action?status=N";	
	document.getElementById('paraFrm').submit();
}


function resetFun(){
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='ReimbursementClmAppl_reset.action';	
	document.getElementById('paraFrm').submit();
}


function deleteFun()
{
 	var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			
			for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'delete') {
			document.all[i].disabled=true;
		}
		}
			
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='ReimbursementClmAppl_delete.action'; 
			document.getElementById('paraFrm').submit();

			 }
			 else
			 {
			 return false; 
			 
			 }
}

 


function callForDelete(id)
{
	var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkEdit').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="ReimbursementClmAppl_deleteData.action";
		  					document.getElementById("paraFrm").submit();
		  					}


}

function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "ReimbursementClmAppl_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	
	function callAdd()
	{
	 
	 var expensedate = document.getElementById('paraFrm_expenseDate').value;
	 var claimamount = document.getElementById('paraFrm_claimAmount').value;
	 var eligibleAmt = document.getElementById('reimbursBalance').value;
	 var billAmount = document.getElementById('paraFrm_billAmount').value;
	 var billCarriedForward = document.getElementById('paraFrm_billsCarriedForward').value;
 	 var sysdate = document.getElementById('paraFrm_claimDate').value;
 	var proofname = document.getElementById('paraFrm_userUploadFileName').value;
	var proofvalue =document.getElementById('paraFrm_uploadFileName').value;
 	var fields=['paraFrm_claimAmount','paraFrm_expenseDate','paraFrm_billAmount'];
 	var labels=['claim.amount','expense.date','bill.amount'];
 	var flags=['enter','select','enter'];
 	var checkAmount=eval(billAmount)+eval(billCarriedForward);
 	if(!validateBlank(fields,labels,flags)){
 		return false;
 	}
	 if(eval(claimamount)>eval(eligibleAmt)){
	 	alert(document.getElementById('claim.amount').innerHTML+' should be less than or equal to '+document.getElementById('eligible.amount').innerHTML);
	 	document.getElementById('paraFrm_claimAmount').focus();
	 	return false;
	 }
	 ///alert('claimamount=='+claimamount);
	 //alert('checkAmount=='+checkAmount);
	 if(eval(claimamount)>(eval(checkAmount))){
	 	alert(document.getElementById('claim.amount').innerHTML+' should be less than or equal to '+document.getElementById('bill.amount').innerHTML+
	 	'+'+document.getElementById('bills.carried.forward').innerHTML);
	 	document.getElementById('paraFrm_claimAmount').focus();
	 	return false;
	 }
	 var proofCount=document.getElementById('proofCount').value;
	 	if(eval(billAmount)>0 && proofCount==0){
	 		alert('Please upload proofs for the entered bill amount');
	 		return false;
	 	}
	 	var check1= validateDate('paraFrm_expenseDate', 'expense.date');
			if(!check1){
			return false;
		}
		 
		if(!(proofname=="" ||trim(proofname)=="Bill Reference No.") && !(proofvalue==""))
		{
			alert("Please upload proof");
	 		return false;
		}
		
		var datdiffdateofjoin = dateDifference(expensedate,sysdate);
  		
  		if(!datdiffdateofjoin){
  		document.getElementById('paraFrm_expenseDate').focus();
  		return false;
  		}
		
		
		 		document.getElementById('paraFrm').target="_self";
		 		 document.getElementById("paraFrm").action="ReimbursementClmAppl_addtoList.action";
		 		document.getElementById("paraFrm").submit();
	 
	 return true;
	 
	}
	
 
function dateDifference(fromDate, toDate){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert("Expense Date should not be greater than today's date.");
		return false;
	}
	return true;
}

	
	
function callUpload()
{
	var uploadFile = document.getElementById('paraFrm_userUploadFileName').value;
	var uploadFilePath = document.getElementById('paraFrm_uploadFileName').value;
	
	if(uploadFilePath=="")
	{
		alert("Please Upload proof");
		return false;
	}
	
	if(uploadFile=="" ||trim(uploadFile)=="Bill Reference No.")
	{
		if(uploadFilePath=="" )
		{
		alert("Please Upload proof");
		return false;
		}	
		else
		{
				alert("Please enter Bill Reference No.");
		return false;
		}	
	
	}
	return true;
}
	

  function callForRemoveUpload(id)
	    {
	    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemoveUpload').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="ReimbursementClmAppl_removeUploadFile.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
	    }	


function reportFun()
{
		
		document.getElementById('paraFrm').action = 'ReimbursementClmAppl_report.action';
		document.getElementById('paraFrm').submit();

}

</script>

