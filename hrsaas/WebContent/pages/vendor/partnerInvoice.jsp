<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PartnerInvoice" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="invoiceID" />
	<s:hidden name="invoiceIDApp" />
	<s:hidden name="invoiceNumber" />
	<s:hidden name="chkStatus" />
	<s:hidden name="invoiceLevel" />

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="middle">
			<fieldset><legend class="legend"><img
				src="../pages/mypage/images/icons/earnings16.png" height="16"
				width="16"> &nbsp;Partner Invoice Creation</legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>
					<table border="0" cellpadding="2" cellspacing="2" align="right">
						<tr>
							<s:if test="buttonFlag">
								<td width="6%"><img
									src="../pages/mypage/images/icons/draft.png" width="10"
									height="10" /></td>
								<td width="11%"><s:a href="#" onclick="draftFun();">Draft</s:a></td>
								<td width="3%">|</td>
								<td width="6%"><img
									src="../pages/mypage/images/icons/sendforapproval.png"
									width="10" height="10" border="0" /></td>
								<td width="35%"><s:a href="#"
									onclick="sendForApprovalFun();">Send for Approval</s:a></td>
								<td width="3%">|</td>
								<td width="6%"><img
									src="../pages/mypage/images/icons/reset.png" width="10"
									height="10" /></td>
								<td width="12%"><s:a href="#" onclick="return callReset();">Reset</s:a></td>
								<td width="3%">|</td>

								<td width="5%"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="10%"><s:a href="#" onclick="backFun();">Back</s:a></td>
							</s:if>
							<s:if test="approverCommentFlag">
								<td width="50%"></td>
								<td align="right"><img
									src="../pages/mypage/images/icons/draft.png" width="10"
									height="10" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'A')">Approve</s:a></td>
								<td>|</td>
								<td><img
									src="../pages/mypage/images/icons/sendforapproval.png"
									width="10" height="10" border="0" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'R')">Reject</s:a></td>
								<td>|</td>
								<td><img src="../pages/mypage/images/icons/reset.png"
									width="10" height="10" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'B')">Send Back</s:a></td>
								<td>|</td>

								<td><img src="../pages/mypage/images/icons/back.png"
									width="10" height="10" /></td>
								<td><s:a href="#" onclick="backFunForApproval();">Back</s:a></td>
							</s:if>
							<s:if test="backButtonFlg">
								<td></td>
								<td width="2%" align="right"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="5%"><s:a href="#" onclick="backFun();">Back</s:a></td>
							</s:if>
							<s:if test="backButtonApprovalFlg">
								<td></td>
								<td width="2%" align="right"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="5%"><s:a href="#"
									onclick="backFunForApproval();">Back</s:a></td>
							</s:if>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="1" bgcolor="#cccccc" class="style1"></td>
				</tr>

				<s:if test="isApproverCommentList">
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
										<td class="formth" width="5%">Sr.No</td>
										<td class="formth" width="12%">Approver ID</td>
										<td class="formth" width="30%" nowrap="nowrap">Approver
										Name</td>
										<td class="formth" width="11%" align="center">Date</td>
										<td class="formth" width="12%" align="center">Status</td>
										<td class="formth" width="30%">Comments By Approver</td>
									</tr>
									<tr>
										<%
										int i = 0;
										%>
										<%
										int k = 1;
										%>
										<s:iterator value="approverCommentList" status="stat">
											<tr>
												<td width="5%" class="sortableTD"><%=k%><s:property
													value="appSrNo" value="%{<%=k%>}" /></td>
												<td width="12%" class="sortableTD"><s:property
													value="approverID" /></td>
												<td width="30%" class="sortableTD"><s:property
													value="approverName" /></td>
												<td width="11%" class="sortableTD"><s:property
													value="approveDate" /></td>
												<td width="12%" class="sortableTD" align="center"><s:property
													value="approveStatus" /></td>
												<td width="30%" class="sortableTD">&nbsp;<s:property
													value="approverComment" /></td>
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
				<s:if test="approverCommentFlag">
					<tr>
						<td>
						<fieldset><legend class="legend1">Approver
						Comments</legend>
						<table border="0" cellpadding="2" cellspacing="1" align="center">
							<tr>
								<td width="20%">Approver Comments</td>
								<td width="1%" class="star">&nbsp;</td>
								<td width="1%">:</td>
								<td width="20%"><s:textarea name="approverComment"
									cols="50" title="Approver Comments"></s:textarea> <s:hidden
									name="approverComment" /></td>
								<td width="3%" class="text1">&nbsp;</td>
								<td width="10%" class="text1">&nbsp;</td>
								<td width="1%" class="text1">&nbsp;</td>
								<td width="1%" class="text1">&nbsp;</td>
								<td width="28%" class="text1">&nbsp;</td>
							</tr>
						</table>
						</fieldset>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>
					<fieldset><legend class="legend1">Invoice
					Information</legend>
					<table border="0" align="center" cellpadding="2" cellspacing="1">
						<tr>
							<td width="18%">Partner Name/ Title</td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td><label><span class="text1" /><s:property
								value="partnerName" /></label></td>
							<td width="3%">&nbsp;</td>
							<td>Partner code</td>
							<td class="star">&nbsp;</td>
							<td>:</td>
							<td class="text1"><s:property value="partnerCode" /></td>
						</tr>
						<tr>
							<td>Invoice Date</td>
							<td class="star">&nbsp;</td>
							<td width="1%">:</td>
							<td width="25%" class="text1"><s:textfield readonly="true"
								name="invoiceDate" id="invoiceDate" title="Invoice Date" /></td>
							<td>&nbsp;</td>
							<td width="15%">Partner Type</td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td width="30%"><s:textfield name="partnerType" size="20"
								title="Select Partner Type" /></td>
						</tr>
						<tr>
							<td>Invoice From Date</td>
							<td class="star">*</td>
							<td width="1%">:</td>
							<td><s:textfield name="creationFromDt" size="20"
								title="Select From Date" readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_creationFromDt','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" width="16"
									height="16">
							</s:a></td>
							<td>&nbsp;</td>
							<td>Project</td>
							<td class="star" width="1%">*</td>
							<td width="1%">:</td>
							<td class="text1"><s:textfield name="projectName"
								title="Select Project name" size="20" /><label><!--<input
								name="SubmitProject" type="submit" class="button" value="..." />
							--></label></td>
						</tr>
						<tr>
							<td>Invoice To Date</td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>
							<td class="text1"><s:textfield name="creationToDt" size="20"
								title="Select To Date" readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_creationToDt','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif">
							</s:a></td>
							<td>&nbsp;</td>
							<!--<td>If other project</td>
							<td width="1%" class="star">&nbsp;</td>
							<td width="1%">:</td>
							<td class="text1"><label><s:textfield
								name="otherProject" title="Other Project" />Please Specify</label></td>-->
							<td>&nbsp;</td>
							<td width="1%" class="star">&nbsp;</td>
							<td width="1%">&nbsp;</td>
							<td class="text1"><label></label></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<!--<tr>
					<td>
					<fieldset><legend class="legend1">Partner  Goal Information</legend>
					<table border="0" align="center" cellpadding="2" cellspacing="2"
						class="border">
						<tr>
							<td width="14%" bgcolor="#EEF4FB"><label name="goaltitle" id="goaltitle"
								ondblclick="callShowDiv(this);"><%=label.get("goaltitle")%></label></td>
							<td width="29%" bgcolor="#EEF4FB"><label name="goaldesc" id="goaldesc"
								ondblclick="callShowDiv(this);"><%=label.get("goaldesc")%></label></td>
							<td width="14%" bgcolor="#EEF4FB"><label name="goalfrmdt" id="goalfrmdt"
								ondblclick="callShowDiv(this);"><%=label.get("goalfrmdt")%></label></td>
							<td width="20%" bgcolor="#EEF4FB"><label name="goaltodt" id="goaltodt"
								ondblclick="callShowDiv(this);"><%=label.get("goaltodt")%></label></td>
							<td width="14%" bgcolor="#EEF4FB"><label name="achivement" id="achivement"
								ondblclick="callShowDiv(this);"><%=label.get("achivement")%></label></td>
							<td width="9%" bgcolor="#EEF4FB"><label name="goalstatus" id="goalstatus"
								ondblclick="callShowDiv(this);"><%=label.get("goalstatus")%></label></td>
						</tr>
						<tr>
							<td class="text1">Data Collection</td>
							<td class="text1">Collection of 10,00,000 labor data at
							Thane region</td>
							<td class="text1">01-04-2012</td>
							<td class="text1">31-08-2012</td>
							<td class="text1">70%</td>
							<td class="text1">In Complete</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				-->
				<!--<tr>
					<td>
					<fieldset><legend class="legend1">Goal
					Achivement Details</legend>
					<table border="0" cellpadding="2" cellspacing="1" align="center">
						<tr>
							<td width="20%">Goal Achivement Details</td>
							<td width="1%" class="star">&nbsp;</td>
							<td width="1%">:</td>
							<td width="25%" class="text1"><s:textarea
								name="goalAchivement" cols="50" title="Goal Achivement Details"></s:textarea></td>
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
					<table border="0" cellpadding="2" cellspacing="1">
						<tr>
							<td width="18%">Invoice Attached</td>
							<td width="2%" class="star">&nbsp;</td>
							<td width="1%">:</td>
							<td width="30%" class="text1"><s:textfield
								name="uploadFileName" size="20" readonly="true"
								cssStyle="background-color: #F2F2F2;" />&nbsp; <input
								type="button" class="token" theme="simple" value="Upload"
								onclick="uploadFile('uploadFileName');" /></td>
							<td width="15%"></td>
							<td width="2%"></td>
							<td width="2%"></td>
							<td width="25%"></td>
							<td width="6%"></td>
						</tr>
						<tr>
							<td>Total Invoice Amount(Rs)</td>
							<td width="2%" class="star">*</td>
							<td width="1%">:</td>
							<td class="text1"><s:textfield name="invoiceAmount"
								title="Total Invoice Amount" size="20"
								onkeypress="return numbersOnly()"></s:textfield></td>
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
							<td width="20%">Partner Comments</td>
							<td width="1%" class="star">&nbsp;</td>
							<td width="1%">:</td>
							<td width="20%"><s:textarea name="partnerComments" cols="50"
								title="Partner Comments"></s:textarea></td>
							<td width="3%" class="text1">&nbsp;</td>
							<td width="10%" class="text1">&nbsp;</td>
							<td width="1%" class="text1">&nbsp;</td>
							<td width="1%" class="text1">&nbsp;</td>
							<td width="28%" class="text1">&nbsp;</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="1px" bgcolor="#cccccc"></td>
				</tr>
				<tr>
					<td>
					<table border="0" cellpadding="2" cellspacing="1" align="right">
						<tr>
							<s:if test="buttonFlag">
								<td><img src="../pages/mypage/images/icons/draft.png"
									width="10" height="10" /></td>
								<td width="11%"><s:a href="#" onclick="draftFun();">Draft</s:a></td>
								<td width="3%">|</td>
								<td width="6%"><img
									src="../pages/mypage/images/icons/sendforapproval.png"
									width="10" height="10" border="0" /></td>
								<td width="35%"><s:a href="#"
									onclick="sendForApprovalFun();">Send for Approval</s:a></td>
								<td width="3%">|</td>
								<td width="6%"><img
									src="../pages/mypage/images/icons/reset.png" width="10"
									height="10" /></td>
								<td width="12%"><s:a href="#" onclick="return callReset();">Reset</s:a></td>
								<td width="3%">|</td>
								<td width="5%"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="10%"><s:a href="#" onclick="backFun();">Back</s:a></td>
							</s:if>
							<s:if test="approverCommentFlag">
								<td width="50%"></td>
								<td><img src="../pages/mypage/images/icons/draft.png"
									width="10" height="10" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'A')">Approve</s:a></td>
								<td>|</td>
								<td><img
									src="../pages/mypage/images/icons/sendforapproval.png"
									width="10" height="10" border="0" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'R')">Reject</s:a></td>
								<td>|</td>
								<td><img src="../pages/mypage/images/icons/reset.png"
									width="10" height="10" /></td>
								<td><s:a href="#" onclick="return chkStatus(this,'B')">Send Back</s:a></td>
								<td>|</td>
								<td><img src="../pages/mypage/images/icons/back.png"
									width="10" height="10" /></td>
								<td><s:a href="#" onclick="backFunForApproval();">Back</s:a></td>
							</s:if>
							<s:if test="backButtonFlg">
								<td></td>
								<td width="2%" align="right"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="5%"><s:a href="#" onclick="backFun();">Back</s:a></td>
							</s:if>
							<s:if test="backButtonApprovalFlg">
								<td></td>
								<td width="2%" align="right"><img
									src="../pages/mypage/images/icons/back.png" width="10"
									height="10" /></td>
								<td width="5%"><s:a href="#"
									onclick="backFunForApproval();">Back</s:a></td>
							</s:if>
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

<script language="JavaScript">

     function validate(){
       var typeParner = document.getElementById('paraFrm_partnerType').value;
       var frmDate = document.getElementById('paraFrm_creationFromDt').value;
       var toDate  = document.getElementById('paraFrm_creationToDt').value;  
       var proj = document.getElementById('paraFrm_projectName').value;   
       var amt = document.getElementById('paraFrm_invoiceAmount').value;  
	   
	   if(typeParner ==""){
		    alert("Please enter Partner Type");
       		return false;
			}	
			
	   if(frmDate ==""){
		    alert("Please enter From Date");
       		return false;
			}
	   if(toDate ==""){
			alert("Please enter To Date");
       		return false;
			}
		if(proj == ""){
			alert("Please enter Project Name");
			return false;
		}
		if(amt == ""){
			alert("Please enter Total Amount");
			return false;
		}
		
	   if(!validateDate('paraFrm_creationFromDt', 'Invoice From Date'))
		return false;
	   if(!validateDate('paraFrm_creationToDt', 'Invoice To Date'))
		return false;
	   if ((frmDate!="") && (toDate!="")) {
  		if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_creationFromDt', 'fromDate', 'toDate'))
			return false;
	   }
		return true;
     }
      function draftFun(){	
		if(validate()){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PartnerInvoice_save.action?checkStatus=D'; 
		document.getElementById('paraFrm').submit();
		return true;
		}else{
		return false;
		}
	  }
	  
	   function sendForApprovalFun(){		
		if(validate()){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PartnerInvoice_save.action?checkStatus=P'; 
		document.getElementById('paraFrm').submit();
		return true;
		}else{
		return false;
		}
	  }

	  function callSearch() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='PartnerInvoice_f9action.action';
		document.getElementById("paraFrm").submit();
	  }
	
	  function callReset() {	 
		document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="PartnerInvoice_reset.action";
		document.getElementById("paraFrm").submit();	
	  }
	
	  function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/vendor";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	} 
	
	function backFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PartnerInvoice_input.action?status=all';
		document.getElementById('paraFrm').submit();
	}
	
	function backFunForApproval(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PartnerInvoiceApproval_input.action?status=p';
		document.getElementById('paraFrm').submit();
	}
	
function chkStatus(obj, status){
	var conf;
	var invoiceID=document.getElementById('paraFrm_invoiceID').value;
	var appComment =document.getElementById('paraFrm_approverComment').value;
	document.getElementById('paraFrm_chkStatus').value = status;
	
	if(document.getElementById('paraFrm_chkStatus').value=='A')
		conf = confirm('Do you Really want to Approve the Application');

	if(document.getElementById('paraFrm_chkStatus').value=='R')
		conf = confirm('Do you Really want to Reject the Application');

	if(document.getElementById('paraFrm_chkStatus').value=='B')
		conf = confirm('Do you Really want to Send Back the Application');

	if(conf){
		document.getElementById("paraFrm").target='main';
		document.getElementById("paraFrm").action='PartnerInvoiceApproval_approveRejSendBackApp.action?&appStatus='+status+'&invoiceCode='+invoiceID+'&appComments='+appComment;
		document.getElementById("paraFrm").submit();
		window.close();
	}
	else{
	return false;
	}
		return true;
	}
</script>