
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ExpenseClaimApp" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="exAppId" />
		<s:hidden name="trvAppIdDtl" />
		<s:hidden name="hiddencode" />
		<s:hidden name="cancelFlag" />
		<s:hidden name="level" />
		<s:hidden name="approverFlag" />
		<s:hidden name="approvedFlag" />

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Expense
					Claim Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3"><s:hidden name="empId"></s:hidden>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<td><strong class="formhead">Employee Information</strong></td>




						<tr>
							<td width="13%" colspan="1" class="formtext" height="22"><label
								class="set" name="employee" id="employee1"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:
							<td width="60%" colspan="3"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="employeeName" size="65" theme="simple" readonly="true" />
							<s:if test="%{generalFlag}"></s:if><s:else>
								<img src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'ExpenseClaimApp_f9Employee.action');">
							</s:else> <s:hidden name="employeeId"></s:hidden></td>

						</tr>


						<tr>
							<td width="13%" class="formtext" height="22"><label
								class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="25%"><s:textfield name="branchName"
								theme="simple" size="25" readonly="true" /></td>

							<td width="10%" class="formtext"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="25%"><s:textfield name="deptName" theme="simple"
								size="35" readonly="true" /></td>

						</tr>


						<tr>

							<td width="13%" class="formtext" height="22"><label
								class="set" name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="25%"><s:textfield name="desgName" theme="simple"
								size="25" readonly="true" /></td>
							<td width="10%" class="formtext"><label class="set"
								name="application.date" id="application.date1"
								ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>:</td>
							<td width="25%"><s:textfield name="applDate" size="10"
								readonly="true" theme="simple" maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_applDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" />
							</s:a></td>

						</tr>


						<tr>

							<td width="13%" class="formtext" height="22"><label
								class="set" name="sts" id="sts1" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label>:</td>
							<td width="25%"><s:textfield name="statusFld" theme="simple"
								size="25" readonly="true" /><s:hidden name="hidSts" /></td>
							<td width="10%" class="formtext"><label class="set"
								name="grd" id="grd1" ondblclick="callShowDiv(this);"><%=label.get("grd")%></label>:</td>
							<td width="25%"><s:textfield name="grdName" size="10"
								readonly="true" theme="simple" maxlength="10" /><s:hidden
								name="grdId" theme="simple"></s:hidden></td>

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
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<td colspan="4"><strong class="formhead">Travel
						Request </strong></td>

						<tr>
							<td width="13%" class="formtext" height="22" colspan="1"><label
								class="set" name="request" id="request"
								ondblclick="callShowDiv(this);"><%=label.get("request")%></label>
							<s:if test="addNewFlag">
								<font color="red">*</font>
							</s:if>:</td>
							<td width="25%" colspan="1"><s:if test="addNewFlag">
								<s:textfield name="requestName" theme="simple" size="30"
									maxlength="50" />
							</s:if><s:else>
								<s:textfield name="requestName" theme="simple" size="30"
									maxlength="50" readonly="true" />
							</s:else><s:hidden name="requestCode"></s:hidden></td>

							<td width="12%" class="formtext" colspan="1"><label
								class="set" name="advance" id="advance"
								ondblclick="callShowDiv(this);"><%=label.get("advance")%></label>
							:</td>
							<td width="27%" colspan="1"><s:if test="addNewFlag">
								<s:textfield name="advAmt" theme="simple" size="20"
									onkeypress="return numbersOnly();" maxlength="10" />
							</s:if><s:else>
								<s:textfield name="advAmt" theme="simple" size="20"
									onkeypress="return numbersOnly();" maxlength="10"
									readonly="true" />
							</s:else></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="">
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<td colspan="4"><strong class="formhead">Expense
						Details  </strong></td>


						<tr height="22">
							<s:hidden name="policyId"></s:hidden>
							<td width="13%" class="formtext" colspan="1"><label
								class="set" name="voucher.head" id="voucher.head"
								ondblclick="callShowDiv(this);"><%=label.get("voucher.head")%></label><font
								color="red">*</font>:</td>
							<td width="25%" colspan="1"><s:textfield name="voucherHead"
								theme="simple" size="30" readonly="true" /><s:hidden
								name="voucherHeadCode" /><s:hidden name="hiddenEdit" /> <input
								type="hidden" name="voucherAmt" id="paraFrm_voucherAmt" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ExpenseClaimApp_f9Voucher.action');"></td>

							<td width="12%" class="formtext" colspan="1"><label
								class="set" name="expense.date" id="expense.date"
								ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label>
							<font color="red">*</font>:</td>
							<td width="27%" colspan="1"><s:textfield name="expenseDate"
								theme="simple" size="10" maxlength="10"
								onblur="return validateDate('paraFrm_expenseDate','expense.date');"
								onkeypress="return numbersWithColonandDot();" /><s:a
								href="javascript:NewCal('paraFrm_expenseDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" />
							</s:a><!--  <input type="button" class="token" value="Travel" />--></td>

						</tr>

						<tr height="22" >
							<td width="13" class="formtext" colspan="1"><label
								class="set" name="amount" id="amount"
								ondblclick="callShowDiv(this);"><%=label.get("amount")%></label><font
								color="red">*</font>:</td>
							<td width="25%" colspan="1"><s:textfield name="amount"
								theme="simple" size="10" onkeypress="return numbersOnly();"
								maxlength="10" /><s:hidden name="hidAmt" /></td>
							<s:hidden name="balAmt" />

							<td class="formtext" width="12%" colspan="1"><label
								class="set" name="proof" id="proof"
								ondblclick="callShowDiv(this);"><%=label.get("proof")%></label>
							:</td>
							<td width="27%" colspan="1"><s:select name="proof"
								disabled="false" list="#{'N':'NO','Y':'Yes'}" theme="simple"
								onchange="showText1();" /></td>


						</tr>

						<tr height="22" >
							<td width="13%" class="formtext" colspan="1"><label
								class="set" name="particulars" id="particulars"
								ondblclick="callShowDiv(this);"><%=label.get("particulars")%></label>
							:</td>
							<td width="25%" colspan="1"><s:textarea name="particulars"
								theme="simple" cols="32" rows="2" /></td>

							<td width="10%" class="formtext"  colspan="1"> <div id="chqId" > <label
								class="set" name="upload" id="upload"
								ondblclick="callShowDiv(this);"><%=label.get("upload")%></label>
							<font color="red">*</font>: </div> </td>
							<td width="27%"  colspan="1"> <div id="chqTextId" > <s:textfield
								name="uploadFileName" theme="simple" size="25" readonly="true" /><input
								type="button" class="token" value="Browse"
								onclick="uploadFile('uploadFileName');" /> </div> </td>

						</tr>
						<tr height="22" >
							<td colspan="5">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="45%" colspan="1"></td>
									<td width="8%" colspan="1"><input type="button"
										class="add" value="   Add" align="center" onclick="callAdd();" /></td>
									<td width="45%" colspan="1"></td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td width="20%"></td>
							<!--<input type="button" class="token"
								value="   Track Change" align="center" />
							 <td width="5%"><input type="button" class="token"
								value="   Delete" align="center" onclick="return chkDelete();"/></td>-->
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="sortable">

						<tr>
							<td width="5%" valign="top" class="formth"><label
								class="set" name="sr.no" id="sr.no1"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="15%" valign="top" class="formth"><label
								class="set" name="voucher.head" id="voucher.head1"
								ondblclick="callShowDiv(this);"><%=label.get("voucher.head")%></label></td>
							<td width="12%" valign="top" class="formth"><label
								class="set" name="particulars" id="particulars"
								ondblclick="callShowDiv(this);"><%=label.get("particulars")%></label></td>
							<td width="17%" valign="top" class="formth"><label
								class="set" name="expense.date" id="expense.date1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.date")%></label></td>
							<td width="12%" valign="top" class="formth"><label
								class="set" name="amount" id="amount1"
								ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
							<td width="10%" valign="top" class="formth"><label
								class="set" name="valid.amount" id="valid.amount"
								ondblclick="callShowDiv(this);"><%=label.get("valid.amount")%></label></td>
							<td width="10%" valign="top" class="formth"><label
								class="set" name="proof.attached" id="proof.attached"
								ondblclick="callShowDiv(this);"><%=label.get("proof.attached")%></label></td>
							<td width="12%" valign="top" class="formth"><label
								class="set" name="upload.file" id="upload.file"
								ondblclick="callShowDiv(this);"><%=label.get("upload.file")%></label></td>
							<td width="10%" valign="top" class="formth"><input
								type="button" class="token" value="   Remove" align="center"
								onclick="return chkDelete();" /></td>
						</tr>
						<%!int j = 0;%>
						<%
						int k = 0;
						%>


						<s:iterator value="expList">
							<tr
								ondblclick="javascript:callForEdit('<s:property value="itVoucher"/>','<s:property value="itVoucherHeadCode"/>','<s:property value="itvoucherAmt"/>','<s:property value="itParticulars"/>','<s:property value="itExpenseDate"/>','<s:property value="itAmount"/>','<s:property value="isProof"/>','<s:property value="itUpload"/>','<%=k+1%>');">
								<td class="sortableTD" width="5%"><%=++k%><input
									type="hidden" name="srNo" value="<%=k%>" /></td>
								<td class="sortableTD" width="15%"><s:property
									value="itVoucher" /><s:hidden name="itVoucher" /><s:hidden
									name="itVoucherHeadCode" /><s:hidden value="itVoucherHeadCode" /><s:hidden
									name="itvoucherAmt" /><s:hidden value="itvoucherAmt" /></td>
								<td class="sortableTD" width="20%"><s:property
									value="itParticulars" /><s:hidden name="itParticulars" /></td>
								<td class="sortableTD" width="15%"><s:property
									value="itExpenseDate" /><s:hidden name="itExpenseDate" /></td>
								<td class="sortableTD" width="15%" align="right"><s:property
									value="itAmount" /><s:hidden name="itAmount" /></td>
								<td class="sortableTD" width="10%" align="center"><input
									type="text" align="middle" size="8" readonly="true"
									onkeypress="return numbersOnly();" name="itValAmount"
									value='<s:property value="itValAmount"/>' /><input
									type="hidden" name="itBalance"
									value='<s:property value="itBalance"/>' /></td>
								<td class="sortableTD" width="10%"><a href="#"
									onclick="return showRecord('<s:property value="itUpload" />');">show</a></td>
								<s:hidden name="isProof"></s:hidden>
								<s:hidden value="isProof" />
								<td class="sortableTD" width="15%"><s:property
									value="itUpload" /><s:hidden name="itUpload" /></td>
								<input type="hidden" name="hdelete" id="hdelete<%=k%>" />
								<td class="sortableTD" width="15%" align="center"><input
									type="checkbox" name="confChk" value="N" id="confChk<%=k%>"
									onclick="callForDelete('<%=k%>')"></td>

							</tr>

						</s:iterator>
						<%
						j = k;
						%>
						<s:if test="expLength">
							<tr>
								<td class="sortableTD"></td>
								<td class="sortableTD"></td>
								<td class="sortableTD"></td>
								<td align="right"><label class="set" name="total.amount"
									id="total.amount" ondblclick="callShowDiv(this);"><%=label.get("total.amount")%></label>:</td>
								<td class="sortableTD" align="right"><s:property
									value="expAmt" /><s:hidden name="expAmt" /></td>
								<td class="sortableTD"></td>
								<td class="sortableTD"></td>
								<td class="sortableTD"></td>
								<td class="sortableTD"></td>
							</tr>
						</s:if>
						<s:if test="maxLimit">
							<tr>
								<td colspan="8"><font color="red">You have crossed
								your maximum limit which is <s:label name="voucherAmt"
									theme="simple" value="%{voucherAmt}" /></font></td>
							</tr>
						</s:if>

					</table>
					<s:hidden name="expListLength" value="%{expListLength}" /> <s:hidden
						name="totAmt"></s:hidden></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<td colspan="4"><strong class="formhead"></strong></td>


						<tr>
							<td width="15%" class="formtext" height="22" colspan="1"><label
								class="set" name="payment.mode" id="payment.mode"
								ondblclick="callShowDiv(this);"><%=label.get("payment.mode")%></label>
							:</td>
							<td width="65%" colspan="1"><s:select name="mode"
								disabled="false"
								list="#{'':'Select','C':'Cash','S':'Salary','Q':'Cheque','T':'Transfer'}"
								theme="simple" onchange="showText();" /></td>


						</tr>
						<s:hidden name="bankName"></s:hidden>
						<s:hidden name="accNo"></s:hidden>
						<tr id="salId">
							<td width="13%" class="formtext" height="22" colspan="1"><label
								class="set" name="month" id="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
								color="red">*</font>:</td>
							<td width="65%" colspan="1"><s:select name="salMonth"
								headerKey="0" headerValue="Select"
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September' ,'10':'October','11':'November','12':'December'}" />

							</td>
						</tr>
						<tr id="salYrId">
							<td width="13%" class="formtext" height="22" colspan="1"><label
								class="set" name="year" id="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
								color="red">*</font>:</td>
							<td width="65%" colspan="1"><s:textfield name="year"
								theme="simple" size="10" maxlength="4"
								onkeypress="return numbersOnly();" /></td>

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
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<td colspan="4"><strong class="formhead"></strong></td>


						<tr>
							<td width="13%" class="formtext" height="22" colspan="4"><label
								class="set" name="comments" id="comments"
								ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
							:</td>
							<td width="60%"><s:textarea name="comment" theme="simple"
								cols="110" rows="3" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="isApprove">
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<td colspan="4"><strong class="formhead"></strong></td>


							<tr>
								<td width="13%" class="formtext" height="22" colspan="4"><label
									class="set" name="approver.comments" id="approver.comments"
									ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
								:</td>

								<td width="60%"><s:textarea name="approverComments"
									theme="simple" cols="110" rows="3" /></td>

							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="%{isApprove}">
			<s:if test="commentFlag">
			</s:if>
			<s:else>
				<tr>
					<td colspan="4">
					<div align="center">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong>Approver
							Comments</strong></td>
						</tr>

						<tr>
							<td class="formth" width="8%"><label class="set"
								name="sr.no" id="sr.no2" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td class="formth" width="30%"><label class="set"
								name="approver.name" id="approver.name"
								ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label></td>
							<td class="formth" width="15%" align="center"><label
								class="set" name="date" id="date"
								ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
							<td class="formth" width="10%" align="center"><label
								class="set" name="sts" id="sts4" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></td>
							<td class="formth" width="30%"><label class="set"
								name="comments" id="comments1" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
						</tr>
						<%
						int j = 1;
						%>
						<s:iterator value="apprList" status="stat">

							<tr>
								<td width="8%" align="center" class="td_bottom_border"><%=j++%></td>
								<td width="30%" class="td_bottom_border"><s:property
									value="approverName" /></td>
								<td width="15%" align="center" class="td_bottom_border"><s:property
									value="approvedDate" /></td>
								<td width="10%" align="center" class="td_bottom_border"><s:property
									value="approveStatus" /></td>
								<td width="30%" class="td_bottom_border"><s:property
									value="approverComment" />&nbsp;</td>

							</tr>
						</s:iterator>
					</table>
					</div>
					</td>
				</tr>
			</s:else>
		</s:if>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>
<script>
	showText();
	showText1();
	function showText(){		
		document.getElementById('salId').style.display='none';
		document.getElementById('salYrId').style.display='none';		
		var chq = document.getElementById('paraFrm_mode').value;					
				if(chq == "S" ){				
				document.getElementById('salId').style.display='';
				document.getElementById('salYrId').style.display='';
				}
							
			}		
			
			
			
			function showText1(){
		//alert("showText");		
		document.getElementById('chqId').style.display='none';
		document.getElementById('chqTextId').style.display='none';		
		var chq = document.getElementById('paraFrm_proof').value;	
		//alert(chq);				
				if(chq == "Y" ){				
				document.getElementById('chqId').style.display='';
				document.getElementById('chqTextId').style.display='';						
				}							
			}			
			
	function uploadFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/expenseClaim";		
		 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 document.getElementById('paraFrm').target="main";
		} 
		
		
	function showRecord(fieldName)
		{	
		if(fieldName=="")
		{
		alert('File not available');
		return false;
		}
		var path='<%=session.getAttribute("session_pool")%>';
		window.open('../pages/images/'+path+'/expenseClaim/'+fieldName);	
		}
	
	function callAdd(){
	
	var fieldName = ["paraFrm_voucherHead","paraFrm_expenseDate","paraFrm_amount"];
	var lableName = ["voucher.head","expense.date","amount"];
	var type = ['select','select','enter'];
	if(!validateBlank(fieldName, lableName, type))
    return false; 
    
    var chq = document.getElementById('paraFrm_proof').value;					
				if(chq == "Y" ){
				var upload = document.getElementById('paraFrm_uploadFileName').value;
				if(upload==""){
				alert("Please upload file.");
				return false;
				}
				}
	    document.getElementById('paraFrm').target =""; 
	    document.getElementById('paraFrm').action = "ExpenseClaimApp_addExpense.action";
		document.getElementById('paraFrm').submit();
		
	}
	
	function chkDelete(){
	//alert("chkDelete");
	if(chk()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="ExpenseClaimApp_deleteExp.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=j %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChk'+a).checked=false;
	   document.getElementById('confChk'+a).checked="";
	    document.getElementById('hdelete'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	function chk(){
	 	var flag='<%=j %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDelete(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChk'+id).checked == true)
	   {	  
	    document.getElementById('hdelete'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdelete'+id).value="";	   
   		}
   		
   		
   		
   		function saveFun()
	{
	var vhead = document.getElementById('paraFrm_voucherHead').value;
	var vdate = document.getElementById('paraFrm_expenseDate').value;
	var vamt = document.getElementById('paraFrm_amount').value;
	var status = document.getElementById('paraFrm_hidSts').value;
	var chq = document.getElementById('paraFrm_mode').value;					
				if(chq == "S" ){
				 var month = document.getElementById('paraFrm_salMonth').value;
				 var year = document.getElementById('paraFrm_year').value;
				 if(month==""){
				 alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
				 return false;
				 }
				 if(year==""){
				 alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
				 return false;
				 }
				}
				
				
	var listLen=<%=j%>;
			if(vhead=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('voucher.head').innerHTML.toLowerCase());
				return false;	
			}	
			if(vdate=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('expense.date').innerHTML.toLowerCase());
				return false;	
			}
			if(vamt=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('amount').innerHTML.toLowerCase());
				return false;	
			}
	if(listLen=="0"){
	alert("Please add Expense Details.");
	return false;	
	}	
	else
	{
	document.getElementById('paraFrm').action="ExpenseClaimApp_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	}
	
	//alert(listLen);
	
	}	
	
	function cancelFun(){
		   document.getElementById('paraFrm').action="ExpenseClaimApp_cancel.action";
  		   document.getElementById('paraFrm').submit();
}
	//For Edit Button

function editFun()
{	
	var status = document.getElementById('paraFrm_hidSts').value;
	//alert(status);
	if(status=="N"){
	document.getElementById('paraFrm').action="ExpenseClaimApp_edit.action";
	document.getElementById('paraFrm').submit();}
	else{
	alert("Application can not be edited.");
	return false;
	}
}
//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="ExpenseClaimApp_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For Cancel Button
function cancelFun(){
		/*if(document.getElementById('paraFrm_cancelFlag').value=="true"){
			alert("1");
			document.getElementById('paraFrm').action="ExpenseClaimApp_cancelSec.action";
  		    document.getElementById('paraFrm').submit();
		
		}else{
		
			alert("2");
		   document.getElementById('paraFrm').action="ExpenseClaimApp_cancel.action";
  		   document.getElementById('paraFrm').submit();
  		}*/
  		document.getElementById('paraFrm').action="ExpenseClaimApp_cancel.action";
  		   document.getElementById('paraFrm').submit();

}	

function sendforapprovalFun()
{	
try{
	var vhead = document.getElementById('paraFrm_voucherHead').value;
	var vdate = document.getElementById('paraFrm_expenseDate').value;
	var vamt = document.getElementById('paraFrm_amount').value;
	var status = document.getElementById('paraFrm_hidSts').value;
	//alert(status);
	
	if(status=="A" || status=="P"){
		alert("This record is already send for approval.");
		return false;
	}
	else if(status=="R"){
		alert("This record is already rejected.");
		return false;
	}
	else{
		//alert(status);	
		var con=confirm('Are you sure to send for approval?');
		if(con){
		//alert(vhead);
		//alert(vdate);
		//alert(vamt);
			var listLen=<%=j%>;
			if(vhead=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('voucher.head').innerHTML.toLowerCase());
				return false;	
			}	
			if(vdate=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('expense.date').innerHTML.toLowerCase());
				return false;	
			}
			if(vamt=="" && listLen=="0"){	
				alert("Please enter "+document.getElementById('amount').innerHTML.toLowerCase());
				return false;	
			}
			if(listLen=="0"){	
				alert("Please add expense details ");
				return false;	
			}
		else
			{	
				document.getElementById('paraFrm').action="ExpenseClaimApp_sendforapproval.action";
				document.getElementById('paraFrm').submit();
				return true;
			}
			//alert(listLen);
		}
	}
	}catch(e){
	//alert(e);
	}
}


function approveFun(){
	var status = document.getElementById('paraFrm_hidSts').value;
	//alert(status);
	
	if(status=="A" || status=="R"){
		alert("This record is already send for approval.");
		return false;
	}else{
		document.getElementById('paraFrm').action="ExpenseClaimApproval_approve.action";
  		document.getElementById('paraFrm').submit();
  		   }
}
function rejectFun(){
	var status = document.getElementById('paraFrm_hidSts').value;
	//alert(status);
	
	if(status=="R"){
		alert("This record is already rejected");
		return false;
	}else{
		document.getElementById('paraFrm').action="ExpenseClaimApproval_reject.action";
  		document.getElementById('paraFrm').submit();
  		   }
}
function backFun(){
		   document.getElementById('paraFrm').action="ExpenseClaimApproval_checkData.action";
  		   document.getElementById('paraFrm').submit();
}


	function callForEdit(editVoucher,editVoucherHeadCode,editvoucherAmt,editParticulars,editExpenseDate,editAmount,editProof,editUpload,sno){
   			
	 	document.getElementById('paraFrm_voucherHead').value=editVoucher;	
	 	document.getElementById('paraFrm_voucherHeadCode').value=editVoucherHeadCode;
	 	document.getElementById('paraFrm_voucherAmt').value=editvoucherAmt;	
	 	document.getElementById('paraFrm_particulars').value=editParticulars;	
	 	document.getElementById('paraFrm_expenseDate').value=editExpenseDate;	
	 	document.getElementById('paraFrm_amount').value=editAmount;	
	 	document.getElementById('paraFrm_hidAmt').value=editAmount;
	 	document.getElementById('paraFrm_proof').value=editProof;
	 	document.getElementById('paraFrm_uploadFileName').value=editUpload;	
	 	document.getElementById('paraFrm_hiddenEdit').value=sno;
	 	var chq = document.getElementById('paraFrm_proof').value;	
		//alert(chq);				
				if(chq == "Y" ){				
				document.getElementById('chqId').style.display='';
				document.getElementById('chqTextId').style.display='';						
				}	else{
				document.getElementById('chqId').style.display='none';
				document.getElementById('chqTextId').style.display='none';		
				}
	 	//alert("+++++++++hhhhhhhhhhhh"+sno);
	   	return false;
  		}
	  		
   		
   		
</script>
