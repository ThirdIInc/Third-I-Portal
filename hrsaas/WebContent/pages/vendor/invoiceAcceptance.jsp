<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="InvoiceAcceptance" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="invoiceIDIA" />
	<s:hidden name="partnerCodeIA" />
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="middle">
			<fieldset><legend class="legend"><img
				src="../pages/mypage/images/icons/earnings16.png" height="16"
				width="16"> &nbsp;Partner Invoice Acceptance</legend>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>
					<table border="0" cellpadding="2" cellspacing="2" align="right">
						<s:if test="accButtonFlag">
							<tr>
								<td width="70%"></td>
								<td width="1%"><img
									src="../pages/mypage/images/icons/draft.png" width="10"
									height="10" /></td>
								<td width="3%"><s:a href="#" onclick="acceptFun();">Accept</s:a></td>
								<td width="1%">|</td>
								<td width="1%"><img
									src="../pages/mypage/images/icons/sendforapproval.png"
									width="10" height="10" border="0" /></td>
								<td width="8%"><s:a href="#" onclick="sendBackFun();">Send Back</s:a></td>
								<td width="1%">|</td>
								<td width="1%"><img
									src="../pages/mypage/images/icons/reset.png" width="10"
									height="10" /></td>
								<td width="3%"><s:a href="#" onclick="backFun();">Back</s:a></td>
								<td width="1%">|</td>
							</tr>
						</s:if>
						<s:else>
							<td></td>
							<td width="2%" align="right"><img
								src="../pages/mypage/images/icons/back.png" width="10"
								height="10" /></td>
							<td width="5%"><s:a href="#" onclick="backFun();">Back</s:a></td>
						</s:else>
					</table>
					</td>
				</tr>
				<tr>
					<td height="1" bgcolor="#cccccc" class="style1"></td>
				</tr>
				<s:if test="isAppComtListIA">
					<tr>
						<td>
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<fieldset><legend class="legend1">Comments
								By Approver</legend>
								<table width="100%" cellpadding="1" cellspacing="1"
									class="sortable">
									<tr>
										<td class="formth" width="5%"><label class="set"
											name="srno" id="srno2" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="12%"><label class="set"
											name="approverId" id="approverId2"
											ondblclick="callShowDiv(this);"><%=label.get("approverId")%></label></td>
										<td class="formth" width="30%" nowrap="nowrap"><label
											class="set" name="approverName" id="approverName2"
											ondblclick="callShowDiv(this);"><%=label.get("approverName")%></label></td>
										<td class="formth" width="11%" align="center"><label
											class="set" name="date" id="date2"
											ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
										<td class="formth" width="12%" align="center"><label
											class="set" name="status" id="status2"
											ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
										<td class="formth" width="30%"><label class="set"
											name="commentsByApprover" id="commentsByApprover2"
											ondblclick="callShowDiv(this);"><%=label.get("commentsByApprover")%></label></td>
									</tr>
									<tr>
										<%
										int i = 0;
										%>
										<%
										int k = 1;
										%>
										<s:iterator value="aprCommentListIA" status="stat">
											<tr>
												<td width="5%" class="sortableTD"><%=k%><s:property
													value="SrNo" value="%{<%=k%>}" /></td>
												<td width="12%" class="sortableTD"><s:property
													value="approverIDIA" /></td>
												<td width="30%" class="sortableTD"><s:property
													value="approverNameIA" /></td>
												<td width="11%" class="sortableTD"><s:property
													value="approveDateIA" /></td>
												<td width="12%" class="sortableTD" align="center"><s:property
													value="approveStatusIA" /></td>
												<td width="30%" class="sortableTD">&nbsp;<s:property
													value="approverComtIA" /></td>
											</tr>
											<%
											k++;
											%>
										</s:iterator>
										<%
											i = k;
											k = 0;
										%>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>
					<table width="100%" align="right" cellpadding="2" cellspacing="1"
						border="0">
						<tr>
							<td>
							<fieldset><legend class="legend1">Invoice
							Information</legend>
							<table>
								<tr>
									<td width="18%"><label class="set" name="partnername"
										id="partnername2" ondblclick="callShowDiv(this);"><%=label.get("partnername")%></label></td>
									<td width="1%" class="star">*</td>
									<td width="1%">:</td>
									<td width="20%"><label><span class="text1" /><s:property
										value="partnerNameIA" /></label></td>
									<td width="15%">&nbsp;</td>
									<td width="15%"><label class="set" name="partnercode"
										id="partnercode2" ondblclick="callShowDiv(this);"><%=label.get("partnercode")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td class="text1"><s:property value="partnerCodeIA" /></td>
								</tr>
								<tr>
									<td><label class="set" name="invoicedate"
										id="invoicedate2" ondblclick="callShowDiv(this);"><%=label.get("invoicedate")%></label></td>
									<td class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td width="25%" class="text1"><s:property
										value="invoiceDateIA" /></td>
									<td>&nbsp;</td>
									<td width="15%"><label class="set" name="partnertype"
										id="partnertype2" ondblclick="callShowDiv(this);"><%=label.get("partnertype")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td width="30%" class="text1"><s:property value="partnerTypeIA" /></td>
								</tr>
								<tr>
									<td><label class="set" name="fromDate" id="fromDate2"
										ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label></td>
									<td class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td><s:property value="invoiceFromDateIA" /></td>
									<td>&nbsp;</td>
									<td><label class="set" name="project" id="project2"
										ondblclick="callShowDiv(this);"><%=label.get("project")%></label></td>
									<td class="star" width="1%">&nbsp;</td>
									<td width="1%">:</td>
									<td class="text1"><s:property value="projectNameIA" /><label></label></td>
								</tr>
								<tr>
									<td><label class="set" name="toDate" id="toDate2"
										ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td class="text1"><s:property value="invoiceToDateIA" /></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">&nbsp;</td>
									<td class="text1"><label></label></td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr><!--
						<tr>
							<td>
							<fieldset><legend class="legend1">Partner
							Goal Information</legend>
							<table border="0" align="center" cellpadding="2" cellspacing="2"
								class="border">
								<tr>
									<td width="14%" bgcolor="#EEF4FB"><label name="goaltitle"
										id="goaltitle2" ondblclick="callShowDiv(this);"><%=label.get("goaltitle")%></label></td>
									<td width="29%" bgcolor="#EEF4FB"><label name="goaldesc"
										id="goaldesc2" ondblclick="callShowDiv(this);"><%=label.get("goaldesc")%></label></td>
									<td width="14%" bgcolor="#EEF4FB"><label name="goalfrmdt"
										id="goalfrmdt2" ondblclick="callShowDiv(this);"><%=label.get("goalfrmdt")%></label></td>
									<td width="20%" bgcolor="#EEF4FB"><label name="goaltodt"
										id="goaltodt2" ondblclick="callShowDiv(this);"><%=label.get("goaltodt")%></label></td>
									<td width="14%" bgcolor="#EEF4FB"><label name="achivement"
										id="achivement2" ondblclick="callShowDiv(this);"><%=label.get("achivement")%></label></td>
									<td width="9%" bgcolor="#EEF4FB"><label name="goalstatus"
										id="goalstatus2" ondblclick="callShowDiv(this);"><%=label.get("goalstatus")%></label></td>
								</tr>
								<tr>
									<td class="text1"><s:property value="goalTitle" /></td>
									<td class="text1"><s:property value="goalDescription" /></td>
									<td class="text1"><s:property value="goalFrmDate" /></td>
									<td class="text1"><s:property value="goalToDta" /></td>
									<td class="text1"><s:property value="goalAchivements" /></td>
									<td class="text1"><s:property value="goalStatus" /></td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td>
							<fieldset><legend class="legend1">Goal
							Achivement Details</legend>
							<table border="0" cellpadding="2" cellspacing="1" align="center">
								<tr>
									<td width="15%"><label name="goalachivement"
										id="goalachivement2" ondblclick="callShowDiv(this);"><%=label.get("goalachivement")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td width="25%" class="text1"><s:property
										value="achivementIA" /></td>
									<td width="3%">&nbsp;</td>
									<td width="10%">&nbsp;</td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">&nbsp;</td>
									<td width="28%" class="text1"><label></label></td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
						--><tr>
							<td>
							<fieldset><legend class="legend1">Invoice
							Details</legend>
							<table border="0" cellpadding="2" cellspacing="1" align="center">
								<tr>
									<td width="18%"><label name="invoiceAttached"
										id="invoiceAttached2" ondblclick="callShowDiv(this);"><%=label.get("invoiceAttached")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<!--<td width="30%" class="text1"><s:property
										value="invoiceAttachedIA" /></td>
									--><td><a href="#"
										onclick="return viewFile('<s:property value="invoiceAttachedIA" />');">
									<font color="blue"><s:property value="invoiceAttachedIA" /></font> </a></td>
									<td width="15%"></td>
									<td width="2%"></td>
									<td width="2%"></td>
									<td width="25%"></td>
									<td width="6%"></td>
								</tr>
								<tr>
									<td><label name="totalAmount" id="totalAmount2"
										ondblclick="callShowDiv(this);"><%=label.get("totalAmount")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td class="text1"><s:property value="invoiceAmountIA" /></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td class="star">&nbsp;</td>
									<td>&nbsp;</td>
									<td class="text1">&nbsp;</td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td>
							<fieldset><legend class="legend1">Partner
							Comments</legend>
							<table border="0" cellpadding="2" cellspacing="1" align="center">
								<tr>
									<td width="15%" align="left"><label name="partnerComments"
										id="partnerComments2" ondblclick="callShowDiv(this);"><%=label.get("partnerComments")%></label></td>
									<td width="1%" class="star">&nbsp;</td>
									<td width="1%">:</td>
									<td width="25%" class="text1"><s:property value="partnerCommentsIA" /></td>
									<td width="3%" class="text1">&nbsp;</td>
									<td width="10%" class="text1">&nbsp;</td>
									<td width="1%" class="text1">&nbsp;</td>
									<td width="1%" class="text1">&nbsp;</td>
									<td width="28%" class="text1"></td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td>
							<fieldset><legend class="legend1">Payment
							Details</legend>
							<table border="0" cellpadding="2" cellspacing="1" align="center">
								<tr>
									<td width="18%" align="left"><label
										name="expectDisburseDt" id="expectDisburseDt2"
										ondblclick="callShowDiv(this);"><%=label.get("expectDisburseDt")%></label></td>
									<td width="1%" class="star">*</td>
									<td width="1%">:</td>

									<s:if test="disburseFlg">
										<td><s:property value="disburseDate" /></td>
									</s:if>
									<s:else>
										<td><s:textfield name="disburseDate" size="20"
											title="Select Disburse Date" readonly="false"/><s:a
											href="javascript:NewCal('paraFrm_disburseDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif" width="16"
												height="16">
										</s:a></td>
									</s:else>
									<td width="3%" class="text1">&nbsp;</td>
									<td width="10%" class="text1">&nbsp;</td>
									<td width="1%" class="text1">&nbsp;</td>
									<td width="1%" class="text1">&nbsp;</td>
									<td width="28%" class="text1"></td>
								</tr>
							</table>
							</fieldset>
							</td>
						</tr>
						<tr>
							<td height="1" bgcolor="#cccccc" class="style1"></td>
						</tr>
						<tr>
							<td>
							<table border="0" cellpadding="2" cellspacing="2" align="right">
								<s:if test="accButtonFlag">
									<tr>
										<td width="70%"></td>
										<td width="1%"><img
											src="../pages/mypage/images/icons/draft.png" width="10"
											height="10" /></td>
										<td width="3%"><s:a href="#" onclick="acceptFun();">Accept</s:a></td>
										<td width="1%">|</td>
										<td width="1%"><img
											src="../pages/mypage/images/icons/sendforapproval.png"
											width="10" height="10" border="0" /></td>
										<td width="8%"><s:a href="#" onclick="sendBackFun();">Send Back</s:a></td>
										<td width="1%">|</td>
										<td width="1%"><img
											src="../pages/mypage/images/icons/reset.png" width="10"
											height="10" /></td>
										<td width="3%"><s:a href="#" onclick="backFun();">Back</s:a></td>
										<td width="1%">|</td>
									</tr>
								</s:if>
								<s:else>
									<td></td>
									<td width="2%" align="right"><img
										src="../pages/mypage/images/icons/back.png" width="10"
										height="10" /></td>
									<td width="5%"><s:a href="#" onclick="backFun();">Back</s:a></td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
</s:form>

<script>

function validate(){
		var disburseDt = document.getElementById('paraFrm_disburseDate').value;
		if(disburseDt ==""){
		    alert("Please Select Disburse Date");
			return false;
		}
		else {	
		  var chkfrmDate = validateDate('paraFrm_disburseDate','expectDisburseDt2');
		  if(!chkfrmDate) {
			return false;
		  }
		}
		return true;
}

function backFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'InvoiceAcceptance_input.action';
		document.getElementById('paraFrm').submit();
	}
	
function acceptFun(){
		var invoiceCode = document.getElementById('paraFrm_invoiceIDIA').value;		
		var conf;		
		if(validate ()){
		  conf= confirm('Do you Really want to Accept the Application');
		  if(conf){
		    document.getElementById('paraFrm').target = "_self";
		    document.getElementById('paraFrm').action = 'InvoiceAcceptance_acknowledgeInvoice.action?&invoiceAccCode='+invoiceCode;
		    document.getElementById('paraFrm').submit();		 
		  }
		  else
		   return false;
	    }
}

function sendBackFun(){
		var invoiceID = document.getElementById('paraFrm_invoiceIDIA').value;
		var conf;
		if(validate()){
			conf= confirm('Do you Really want to Send Back the Application');
			if(conf){		 
		  	  document.getElementById('paraFrm').target = "_self";
		      document.getElementById('paraFrm').action = 'InvoiceAcceptance_sendBackInvoice.action?&invoiceCode='+invoiceID;
		   	  document.getElementById('paraFrm').submit();
		  	}
		    else
		      return false;
		}
}

function viewFile(fileName)
	{
		if(fileName==" "){
			alert("Invoice Attach Document not Uploaded.");
			return false;
		}
		else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "InvoiceAcceptance_viewFile.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}

</script>