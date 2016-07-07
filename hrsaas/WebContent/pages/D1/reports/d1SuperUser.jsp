<!--@author Ayyappa @date 30-03-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<style type="text/css">
#myselect {
	width: 230px;
}
</style>

<s:form action="D1SuperUser" validate="true" id="paraFrm" theme="simple">

	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageOngoing" id="myPageOngoing" />
	
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
					<td width="93%" class="txt"><strong class="text_head">
					Super User</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="20%">Select Application Type <font color="red" id="ctrlHide">*</font>:</td>
					<td><s:select name="applicationType" headerKey="-1" headerValue="--------------Select-----------------"
						list="#{'D1-APPLN_SEC_REQ':'Application Security Request','D1-AUTHTRVL':'Authorized Travel Request','D1-ASIPO':'ASIPO Reconciliation Form','D1-BRD':'Business Requirement Document','D1-CASHADV':'Cash Advance Request','D1-CEAR':'Capital Expenditure','D1-CBT':'CBT Self Study Enrollment Form Application','D1-CDROM':'CD ROM Request','D1-ENROLL':'Class Enrollment Form','D1-CLASS':'Class Request','D1-CCR':'Credit Check Request','D1-DEPT':'Department/Location Change ','D1-EXP':'Expense Request',   'D1-HWSW':'Hardware Software Request','D1-ISCR':'Information System Change Request','D1-HIRE':'New Hire/Rehire Form',	'D1-NONINV':'Non Inventory Purchase', 'D1-DATACHG':'Personal Data Change','D1-PERSONAL':'Personal Requisition','D1-QUICK PROJECT':'Quick Project','D1-REPCHG':'Report Request Change', 'D1-REQBACKOUT':'Request to Back Out Voucher ','D1-SHORTDIS':'Short Term Disability','D1-TERMNT':'Termination Form','D1-INJURY':'Workers Comp Injury/Illness'}"
						cssStyle="width :160">
					</s:select></td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="20%">Select Applicant :</td>
					<td colspan="2"><s:hidden name="applicantCode" /> <s:textfield
						name="applicantToken" readonly="true" size="15" /> <s:textfield
						name="applicantName" readonly="true" size="50" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'D1SuperUser_f9applicant.action');" />
					</td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="20%">Application Status :</td>
					<td colspan="2"><s:select name="applicationStatus"
						list="#{'':'--------------Select-----------------', 'D':'Draft','P':'Pending with Manager','F':'Forwarded','S':'Pending with Group','A':'Approved By Group','R':'Rejected','B':'Send Back','C':'Closed Ticket' ,'H':' Forwarded by group'}"
						cssStyle="width :160">
					</s:select></td>
				</tr>


				<tr>

					<td colspan="4" align="center"><s:submit value="View Records" onclick="viewRecords();"
						cssClass="token" ></s:submit></td>

				</tr>


			</table>
			</td>
		</tr>

		<s:if test="displayApplicationFlag">
			<tr>
				<td colspan="8">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>


						<td height="27" class="formtxt" colspan="3"><strong>
						<%
							String status = (String) request.getParameter("applicationType");

							if (status == null) {
								//out.println("HR Group");
							} else if (status.equals("D1-EXP")) {
								out.println("Expense Request");
							} else if (status.equals("D1-NONINV")) {
								out.println("Non Inventory Purchase");
							} else if (status.equals("D1-AUTHTRVL")) {
								out.println("Authorized Travel Request");
							} else if (status.equals("D1-REPCHG")) {
								out.println("Report Request Change");
							} else if (status.equals("D1-PERSONAL")) {
								out.println("Personal Requisition");
							} else if (status.equals("D1-DATACHG")) {
								out.println("Personal Data Change");
							} else if (status.equals("D1-ENROLL")) {
								out.println("Class Enrollment Form");
							} else if (status.equals("D1-HIRE")) {
								out.println("New Hire/Rehire Form");
							} else if (status.equals("D1-REQBACKOUT")) {
								out.println("Request to Back Out Voucher");
							} else if (status.equals("D1-CLASS")) {
								out.println("Class Request");
							} else if (status.equals("D1-DEPT")) {
								out.println("Department/Location Change");
							} else if (status.equals("D1-INJURY")) {
								out.println("Workers Comp Injury/Illness");
							}

							else if (status.equals("D1-SHORTDIS")) {
								out.println("Short Term Disability");
							} else if (status.equals("D1-TERMNT")) {
								out.println("Termination Form");
							} else if (status.equals("D1-ASIPO")) {
								out.println("ASIPO Reconciliation Form");
							} else if (status.equals("D1-HWSW")) {
								out.println("Hardware Software Request");
							} else if (status.equals("D1-CCR")) {
								out.println("Credit Check Request");
							} else if (status.equals("D1-CDROM")) {
								out.println("CD ROM Request");
							} else if (status.equals("D1-CBT")) {
								out.println("CBT Self Study Enrollment");
							}

							else if (status.equals("D1-CASHADV")) {
								out.println("Cash Advance Request");
							} else if (status.equals("D1-APPLN_SEC_REQ")) {
								out.println("Application Security Request");
							} else if (status.equals("D1-CEAR")) {
								out.println("Capital Expenditure");
							} else if (status.equals("D1-QUICK PROJECT")) {
								out.println("Quick Project");
							} else if (status.equals("D1-ISCR")) {
								out.println("Information System Change Request");
							}
						%> </strong></td>




						<td align="right" colspan="5">
						<%
							int totalPage = 0;
							int pageNo = 0;
						%> <s:if test="superUserListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'D1SuperUser_view.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'D1SuperUser_view.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'D1SuperUser_view.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'D1SuperUser_view.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'D1SuperUser_view.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>

					<tr>
						<td class="formth" width="5%">Sr No</td>
						<td class="formth" width="15%">Tracking No</td>
						<td class="formth" width="12%">Employee Id</td>
						<td class="formth" width="23%">Employee Name</td>
						<td class="formth" width="15%">Application Date</td>
						<td class="formth" width="8%">Status</td>
						<td class="formth" width="10%">Pending With</td>
						<s:if test="scheduleDateFlag">
							<td class="formth" width="10%">Schedule Date</td>
						</s:if>
						<td class="formth" width="10%">View</td>
					</tr>

					<s:if test="superUserListLength">
						<%
						int count = 0;
						%>
						<%!int d = 0;%>
						<%
						int i = pageNo * 20 - 20;
						%>

						<s:iterator value="list">
							<tr>
								<td align="center" class="sortableTD"><%=++i%></td>
								<td align="center" class="sortableTD">&nbsp;<s:property
									value="ittTrackingNo" /></td>
								<td align="center" class="sortableTD">&nbsp;<s:property
									value="ittEmpToken" /></td>
								<td align="left" class="sortableTD">&nbsp;<s:property
									value="ittEmpame" /></td>

								<td align="center" class="sortableTD">&nbsp;<s:property
									value="ittApplicationDate" /></td>
								<td align="left" class="sortableTD">&nbsp;<s:property
									value="ittStatus" /></td>
								<td align="left" class="sortableTD">&nbsp;<s:property
									value="ittPendingWith" /></td>
								<s:if test="%{bean.scheduleDateFlag}">
									<td align="left" class="sortableTD">&nbsp;<s:property
										value="ittScheduleDate" /></td>
								</s:if>

								<td align="left" class="sortableTD">&nbsp;<s:hidden
									name="ittApplicationCode" /> <input type="button"
									class="token" value="View "
									onclick="viewDetails('<s:property value="ittApplicationCode" />');">
							</tr>
						</s:iterator>
						<%
						d = i;
						%>
					</s:if>

					<s:else>
						<td align="center" colspan="9" nowrap="nowrap"><font
							color="red">There is no data to display</font></td>
					</s:else>

				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="BrdFlag">
			<tr>
				<td colspan="8">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>


						<td height="27" class="formtxt" colspan="3"><strong>
						<%
									String applicationStatus = (String) request
									.getParameter("applicationType");

							if (applicationStatus == null) {
								//out.println("HR Group");
							} else if (applicationStatus.equals("D1-BRD")) {
								out.println("Business Requirement Document");
							}
						%> </strong></td>
						<td align="right" colspan="5">
						<%
							int totalPageBRD = 0;
							int pageNoBRD = 0;
						%> <s:if test="superUserBRDListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPageBRD = (Integer) request.getAttribute("totalPageBRD");
										pageNoBRD = (Integer) request.getAttribute("pageNoBRD");
									%> <a href="#"
										onclick="callPageBRD('1', 'F', '<%=totalPageBRD%>', 'D1SuperUser_view.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageBRD('P', 'P', '<%=totalPageBRD%>', 'D1SuperUser_view.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldBrd" id="pageNoFieldBrd" size="3"
										value="<%=pageNoBRD%>" maxlength="10"
										onkeypress="callPageTextBRD(event, '<%=totalPageBRD%>', 'D1SuperUser_view.action');return numbersOnly();" />
									of <%=totalPageBRD%> <a href="#"
										onclick="callPageBRD('N', 'N', '<%=totalPageBRD%>', 'D1SuperUser_view.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageBRD('<%=totalPageBRD%>', 'L', '<%=totalPageBRD%>', 'D1SuperUser_view.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>

					<tr>
						<td class="formth" width="5%">Sr No</td>
						<td class="formth" width="18%">BRD Ticket No</td>
						<td class="formth" width="12%">Project Name</td>
						<td class="formth" width="10%" nowrap="nowrap">Expected End Date</td>
						<td class="formth" width="10%" nowrap="nowrap">Current Stage</td>
						<td class="formth" width="10%" nowrap="nowrap">Pending With Role</td>
						<td class="formth" width="5%">Status</td>
						<td class="formth" width="25%">Pending With Name</td>
						<td class="formth" width="5%">View</td>
					</tr>
					<s:if test="superUserBRDListLength">
						<%
							int b = 0;
							%>


						<s:iterator value="brdList" status="stat">
							<tr>
								<td width="5%" class="sortableTD" align="center"><s:hidden
									name="serialNo" /><%=++b%></td>

								<td width="18%" align="left" class="sortableTD">&nbsp; <s:property
									value="ittBrdNo" /></td>

								<td width="12%" align="center" class="sortableTD">&nbsp; <s:property
									value="ittProjName" /></td>

								<td width="10%" align="center" class="sortableTD">&nbsp; <s:property
									value="ittExpDate" /></td>

								<td width="10%" align="left" class="sortableTD">&nbsp; <s:property
									value="ittCurrentStage" /></td>

								<td width="10%" align="left" class="sortableTD">&nbsp; <s:property
									value="ittPendingWithRole" /></td>
								
								<td width="5%" align="left" class="sortableTD">&nbsp; <s:property
									value="ittProjStatus" /></td>	

								<td width="25%" align="left" class="sortableTD">&nbsp; <s:property
									value="ittPendingWithName" /></td>

								<td align="left" class="sortableTD" width="5%">&nbsp;<s:hidden
									name="ittHiddenCode" /> <input type="button" class="token"
									value="View "
									onclick="viewDetails('<s:property value="ittHiddenCode" />');"></td>

								</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<td align="center" colspan="9" nowrap="nowrap"><font
							color="red">There is no data to display</font></td>
					</s:else>




				</table>
				</td>
			</tr>
		</s:if>
	</table>
</s:form>


<script>

  function viewRecords()
  {
 
   var appType = document.getElementById('paraFrm_applicationType').value;
   
  if(appType=="-1")
  {
  alert("Please select Application Type");
  return false;
  
  }
   
        document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='D1SuperUser_view.action';
		document.getElementById('paraFrm').submit();
  
  
  }


		function viewDetails(id) {
		var formAction='';
		var applType=document.getElementById('paraFrm_applicationType').value;
		if(applType=='D1-EXP'){
		formAction='ExpenseApprovalRegAppr_editApplication.action?applCode='+id;
		}
		if(applType=='D1-NONINV'){
		formAction='NonInventoryPurchaseAppr_editApplication.action?applCode='+id;
		}
		if(applType=='D1-INJURY'){
		formAction='WorkersCompInjury_editApplication.action?applCode='+id;
		}
		if(applType=='D1-SHORTDIS'){
		formAction='ShortTermDisability_editApplication.action?applCode='+id;
		}
		if(applType=='D1-QUICK PROJECT'){
		formAction='QuickProjectAppr_editApplication.action?applCode='+id;
		}
		
		
		
		if(applType=='D1-PERSONAL'){
		formAction='PersonalRequisitionApprover_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-ENROLL'){
		formAction='ClassEnrollmentApproverForm_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-DATACHG'){
		formAction='PersonalDataChangeApproval_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-HIRE'){
		formAction='NewHireRehireApprover_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-CASHADV'){
		formAction='CashAdvanceRequestApproval_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-ISCR'){
		formAction='InformationSystemChangeRequestAppr_viewApplicationFunction.action?applCode='+id;
		}
		
		if(applType=='D1-DEPT'){
		formAction='DepartmentLocationChange_retrivePendingDetails.action?applCode='+id;
		}
		
		if(applType=='D1-AUTHTRVL'){
		formAction='TravelApplication_viewDetails.action?applCode='+id;
		}
		if(applType=='D1-HWSW'){
		formAction='HardwareSoftwareRequestApprover_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-APPLN_SEC_REQ'){
		formAction='ApplicationSecurityRequestApproval_viewDetails.action?applCode='+id;
		}
		if(applType=='D1-REPCHG'){
		formAction='ReportRequestChange_viewDetails.action?applCode='+id;
		}
		
		
		
		
		if(applType=='D1-TERMNT'){
		formAction='TerminationApproval_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-REQBACKOUT'){
		formAction='ReqToBackOutVoucher_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-ASIPO'){
		formAction='ASIPOReconciliation_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-CEAR'){
		formAction='CapitalExpenditure_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-CBT'){
		formAction='CBTForm_viewApplicationFunction.action?applCode='+id;
		}
		if(applType=='D1-CCR'){
		formAction='CreditCheckRequest_viewApplicationFunction?applCode='+id;
		}
		if(applType=='D1-CDROM'){
		formAction='CDRomRequest_callView.action?applCode='+id;
		}
		if(applType=='D1-CLASS'){
		formAction='ClassRequest_retrivePendingDetails.action?applCode='+id;
		}
		if(applType=='D1-BRD'){
		formAction='BusinessRequirementDocument_viewSuperUserData.action?applCode='+id;
		}
		
		if(navigator.appName == 'Netscape') {
			var win = window.open('', 'win', 'top = 150, left = 200, width = 800, height = 880, scrollbars = yes, status = no, resizable = yes');
		} else {
			var win = window.open('', 'win', 'top = 230, left = 200, width = 800, height = 880, scrollbars = yes, status = no, resizable = yes');
		}		
		document.getElementById('paraFrm').target = 'win';
		document.getElementById('paraFrm').action =formAction;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}



function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	///BRD
	
	
function callPageBRD(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoFieldBrd').value;	
		actPage = document.getElementById('myPageOngoing').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldBrd').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldBrd').value = actPage;
			    document.getElementById('pageNoFieldBrd').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldBrd').value=actPage;
			    document.getElementById('pageNoFieldBrd').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldBrd').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldBrd').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldBrd').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldBrd').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldBrd').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldBrd').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageOngoing').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageTextBRD(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldBrd').value;
		 	var actPage = document.getElementById('myPageOngoing').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldBrd').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldBrd').focus();
		     document.getElementById('pageNoFieldBrd').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldBrd').focus();
		     document.getElementById('pageNoFieldBrd').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldBrd').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageOngoing').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
</script>
