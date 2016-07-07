<!-- @author: Mangesh Jadhav 19 Jan 2011  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReimbDisbursement" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Reimbursement
					Claim Disbursement</strong></td>
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

		<!--<s:if test="approverListFlag">
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
									ondblclick="callShowDiv(this);"><%//=label.get("srno")%></label></td>
								<td class="formth" width="25%" height="22" valign="top"><label
									class="set" id="approver.name" name="approver.name"
									ondblclick="callShowDiv(this);"><%//=label.get("approver.name")%></label></td>
								<td class="formth" width="40%" height="22" valign="top"><label
									class="set" id="approver.comments1" name="approver.comments"
									ondblclick="callShowDiv(this);"><%//=label.get("approver.comments")%></label></td>
								<td class="formth" width="15%" height="22" valign="top"><label
									class="set" id="approval.date" name="approval.date"
									ondblclick="callShowDiv(this);"><%//=label.get("approval.date")%></label></td>

							</tr>


							<%
							//int m = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="5%" class="sortableTD"><%//=m%><s:hidden
										name="appSrNo" value="%{<%//=m%>}" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="approverNameList" /><s:hidden name="approverNameList" /></td>
									<td width="40%" class="sortableTD"><s:property
										value="approverCommentsList" /><s:hidden
										name="approverCommentsList" /></td>
									<td width="15%" class="sortableTD" align="center"><s:property
										value="approverDateList" /><s:hidden name="approverDateList" /></td>
								</tr>
								<%
								//m++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>-->
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
					<td width="30%" colspan="1"><s:hidden name="empId"
						theme="simple" /> <s:property value='empName' /><s:hidden
						name='applId'></s:hidden><s:hidden name='empName'></s:hidden><s:hidden name="disbursementCode"/></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" height="22"><s:property value='designation' /><s:hidden
						name='designation'></s:hidden></td>
				</tr>

				<tr>
					
					<td width="20%" colspan="1"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1" height="22"><s:property
						value='branch' /><s:hidden name='branch' /></td>
					<td width="20%" colspan="1" class="formtext"><s:hidden
						name="reimbHead" /><label class="set" id="grade" name="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td width="30%" colspan="1"><s:property value='grade' /><s:hidden
						name='grade' /></td>

				</tr>
				<tr>
					
					<td width="20%" colspan="1"><label class="set" id="claimDt"
						name="claimDt" ondblclick="callShowDiv(this);"><%=label.get("claimDt")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value='claimDate' /><s:hidden
						name='claimDate' /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="division" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="30%" colspan="1"><s:hidden name="divisionCode" /><s:property
						value='division' /><s:hidden name='division' /></td>

				</tr>

				<tr>
					
					<td width="20%" colspan="1"><label class="set" id="appId"
						name="appId" ondblclick="callShowDiv(this);"><%=label.get("appId")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value='applRefNo' /><s:hidden
						name='applRefNo' /></td>
					<s:if test="%{listType == 'closed'}"><td width="20%" colspan="1"><label class="set" id="appId"
						name="appId" ondblclick="callShowDiv(this);"><%=label.get("appId")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value='disbReferenceId' /></td></s:if>
				<s:hidden	name='disbReferenceId' />
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
					<label class="set" id="reimbursementDtls" name="reimbursementDtls"
						ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtls")%></label>
					:</strong></td>
				</tr>

				<tr>
					<td colspan="8">
					<table width="100%">
						<tr>
							<td class="formth" width="5%"><label id="srno" name="srno"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
							<td class="formth" width="15%" align="center"><label
								id="reimbursementDtl" name="reimbursementDtl"
								ondblclick="callShowDiv(this);"><%=label.get("reimbursementDtl")%></label></td>
							<td class="formth" width="15%" nowrap="nowrap"><label
								id="expense.date1" name="expense.date"
								ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></td>
							<td class="formth" width="5%" align="center"><label
								id="claimAmt" name="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
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
									value="expenseDate" /> <s:hidden name="expenseDate" /></td>
								<td class="sortableTD" width="5%" align="right"><s:property
									value="claimAmtHead" /><s:hidden name="claimAmtHead"
									id='<%="claimAmtHead"+srNo%>' /></td>
								
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
							<td class="sortableTD" width="35%">&nbsp;</td>
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
					<td width="25%"><label class="set" id="claimParticulars"
						name="claimParticulars" ondblclick="callShowDiv(this);"><%=label.get("claimParticulars")%></label>
					:</td>
					<td width="75%" colspan="3"><s:property
						value='claimParticular' /><s:hidden name='claimParticular' /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" nowrap="nowrap" colspan="5"><strong>
					Disbursement Details :</strong></td>

				</tr>
				<tr>
					<td width="20%"><label class="set" id="disb.date"
						name="disb.date" ondblclick="callShowDiv(this);"><%=label.get("disb.date")%></label><font
						color="red">*</font>:</td>
					<td width="15%" colspan="1"><s:textfield theme="simple"
						name="disbDate" onkeypress="return numbersWithHiphen();" size="12" /></td>
					<td width="20%" colspan="1"  ><s:a
						href="javascript:NewCal('paraFrm_disbDate','DDMMYYYY');" id="ctrlHide">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
					<td width="20%"><label class="set" id="disb.amount"
						name="disb.amount" ondblclick="callShowDiv(this);"><%=label.get("disb.amount")%></label>
					<font color="red">*</font> :
					<td width="20%" colspan="2"><s:textfield size="15" name="disbAmount"
						maxlength="7" onkeypress="return numbersWithDot();" /></td>

				</tr>
				<tr>
					<td width="20%"><label class="set" id="pay.mode"
						name="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label><font
						color="red">*</font>:</td>
					<td colspan="5"><s:select name="payMode" headerKey=""
						onchange="return callPayMode();" headerValue="--Select--"
						list="%{payModeHashmap}" cssStyle="width:165" /></td>
				</tr>

				<tr id='cheque_TR'>
					<td width="20%"><label class="set" id="cheque.date"
						name="cheque.date" ondblclick="callShowDiv(this);"><%=label.get("cheque.date")%></label><font
						color="red">*</font>:</td>
					<td width="15%" colspan="1" ><s:textfield theme="simple"
						name="chequeDate" onkeypress="return numbersWithHiphen();"
						size="12" /></td>
					<td width="20%" colspan="1" ><s:a
						href="javascript:NewCal('paraFrm_chequeDate','DDMMYYYY');" id="ctrlHide">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
					<td width="20%"><label class="set" id="cheque.no"
						name="cheque.no" ondblclick="callShowDiv(this);"><%=label.get("cheque.no")%></label>
					:
					<td width="20%" colspan="2"><s:textfield size="15" name="chequeNo"
						maxlength="7" /></td>

				</tr>

				<tr id='TX_TR'>
					<td width="20%"><label class="set" id="account.no"
						name="account.no" ondblclick="callShowDiv(this);"><%=label.get("account.no")%></label><font
						color="red">*</font>:</td>
					<td width="25%" colspan="2"><s:textfield theme="simple"
						name="accountNo" size="15" /></td>
					<td width="15%"><label class="set" id="bank" name="bank"
						ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> <font
						color="red">*</font> :</td>
					<td width="20%"><s:textfield size="15" name="bankName" /><s:hidden name="bank" /></td>
					<td width="20%" colspan="1"><img id="ctrlHide" src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'ReimbDisbursement_f9Bank.action');"></td>

				</tr>

				<tr id='salary_TR'>
					<td width="20%"><label class="set" id="sal.month"
						name="sal.month" ondblclick="callShowDiv(this);"><%=label.get("sal.month")%></label><font
						color="red">*</font>:</td>
					<td width="40%" colspan="2"><s:select name="month"
						headerKey="" headerValue="--Select--" title="Select a month"
						list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></td>

					<td width="20%"><label class="set" id="sal.year"
						name="sal.year" ondblclick="callShowDiv(this);"><%=label.get("sal.year")%></label>
					<font color="red">*</font> :
					<td width="20%" colspan="2"><s:textfield size="10" name="year"
						maxlength="7" onkeypress="return numbersOnly();" /></td>

				</tr>
				
				<tr>
					<td width="20%" colspan="1"><label class="set"
						id="acc.comments" name="acc.comments"
						ondblclick="callShowDiv(this);"><%=label.get("acc.comments")%></label>
					:</td>
					<td width="80%" colspan="3">
						<s:textarea theme="simple" cols="80" rows="4" name="accountantComments" />
						
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
					<td width="78%" colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				</tr>
			</table>
			</td>
		</tr>
 <s:hidden name="source" id="source" />
<s:hidden name="applStatus" />
		<s:hidden name="listType"/>
		<s:hidden name="alertFlag"/>
	</table>
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
		document.getElementById('paraFrm').action = "ReimbDisbursement_viewProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}
function returntolistFun(){
	var status=document.getElementById("paraFrm_listType").value;
	if(status=="pending"){
	backFun();
		document.getElementById('paraFrm').action='ReimbDisbursement_input.action';
		document.getElementById('paraFrm').submit();
	}else{
	backFun();
		document.getElementById('paraFrm').action='ReimbDisbursement_getClosedList.action';
		document.getElementById('paraFrm').submit();
	}
}
callPayMode();
autoDate ();
function callPayMode(){
	var payMode=document.getElementById('paraFrm_payMode').value;
	if(payMode=="CH"){
		document.getElementById('salary_TR').style.display="none";
		document.getElementById('TX_TR').style.display="none";
		document.getElementById('cheque_TR').style.display="";
	}else if(payMode=="SL"){
		document.getElementById('salary_TR').style.display="";
		document.getElementById('TX_TR').style.display="none";
		document.getElementById('cheque_TR').style.display="none";
	} else if(payMode=="TR"){
		document.getElementById('salary_TR').style.display="none";
		document.getElementById('TX_TR').style.display="";
		document.getElementById('cheque_TR').style.display="none";
	}else {
		document.getElementById('salary_TR').style.display="none";
		document.getElementById('TX_TR').style.display="none";
		document.getElementById('cheque_TR').style.display="none";
	}
}
function viewDetails(claimId,claimStatus,empCode){
	document.getElementById('paraFrm').action='ReimbDisbursement_retrieveDetails.action?claimId='+claimId+'&empCode='+empCode+'&status='+claimStatus;
   	document.getElementById('paraFrm').submit();
}
function callPageClosed(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoFieldClosed').value;	
		actPage = document.getElementById('myPageClosed').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldClosed').value = actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldClosed').value=actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldClosed').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageClosed').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageTextClosed(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldClosed').value;
		 	var actPage = document.getElementById('myPageClosed').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldClosed').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldClosed').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageClosed').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	function saveFun(){
		var payMode=document.getElementById('paraFrm_payMode').value;
		var disbDate=document.getElementById('paraFrm_disbDate').value;
		var claimDate=document.getElementById('paraFrm_claimDate').value;
		var totalClaimAmt=document.getElementById('paraFrm_totalClaimAmt').value;
		var disbAmount=document.getElementById('paraFrm_disbAmount').value;
		var fields;
		var labels;
		var flags;
		
		
		if(disbDate==""){
			alert("Please enter "+document.getElementById('disb.date').innerHTML);
			document.getElementById('paraFrm_disbDate').focus();
			return false;
			}
		if(disbAmount==""){
			alert("Please enter "+document.getElementById('disb.amount').innerHTML);
			document.getElementById('paraFrm_disbAmount').focus();
			return false;
		}else {
			if(disbAmount>totalClaimAmt){
			alert(document.getElementById('disb.amount').innerHTML+" should be less than or equal to Total claim amount");
			document.getElementById('paraFrm_disbAmount').focus();
		}
		}
		if(!validateDate("paraFrm_disbDate", "disb.date"))
			return false;
			
		if(!dateDifferenceEqual(claimDate, disbDate, 'paraFrm_disbDate', "claimDt", "disb.date"))
		return false;	
		try{
		if(payMode==""){
			alert("Please select "+document.getElementById('pay.mode').innerHTML);
			document.getElementById('paraFrm_payMode').focus();
			return false;
		}else if(payMode=="SL"){
				fields=["paraFrm_month","paraFrm_year"];
				labels=["sal.month","sal.year"];
				flags=["select","enter"];
		}else if(payMode=="TR"){
				fields=["paraFrm_accountNo","paraFrm_bank"];
				labels=["account.no","bank"];
				flags=["enter","select"];
		}else if(payMode=="CH"){
				fields=["paraFrm_chequeDate","paraFrm_chequeNo"];
				labels=["cheque.date","cheque.no"];
				flags=["enter","enter"];
		}
		if(!validateBlank(fields,labels,flags)){
		return false;
		}
		if(payMode=="CH"){
			if(!validateDate("paraFrm_chequeDate", "cheque.date"))
			return false;
		}
		}catch(e){
			}
		var conf=confirm('Do you really want to save the application?');
			 	if(!conf){
			 	return false;
			 	}
		document.getElementById('paraFrm').action = "ReimbDisbursement_save.action";
		document.getElementById('paraFrm').submit();
}
 function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			if(document.getElementById('paraFrm_disbDate').value=="")
				document.getElementById("paraFrm_disbDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
}
		function backFun(){
				document.getElementById('paraFrm').action = "ReimbDisbursement_bacck.action";
				document.getElementById('paraFrm').submit();
			}
			
</script>