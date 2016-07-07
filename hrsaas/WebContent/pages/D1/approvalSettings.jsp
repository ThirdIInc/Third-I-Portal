<!-- Bhushan Dasare -->
<!-- Feb 14, 2011 -->
<!-- Nilesh D 31 Mar 11 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="ApprovalSettings" name="ApprovalSettings" id="paraFrm"
	validate="true" target="main" theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageAdmin" id="myPageAdmin" />
	<s:hidden name="myFinancePage" id="myFinancePage" />
	<s:hidden name="myPayrollPage" id="myPayrollPage" />
	<s:hidden name="myLogisticsPage" id="myLogisticsPage" />
	<s:hidden name="myItPage" id="myItPage" />
	<s:hidden name="myTrainingAuthPage" id="myTrainingAuthPage" />
	<s:hidden name="myEducationMinistryPage" id="myEducationMinistryPage" />
	<s:hidden name="myFinanaceATRPAge" id="myFinanaceATRPAge" />
	<s:hidden name="myCorporateProGroupPage" id="myCorporateProGroupPage" />
	<s:hidden name="myWorldTravelPage" id="myWorldTravelPage" />
	<s:hidden name="myConfigureOptionPage" id="myConfigureOptionPage" />


	<table width="100%" class="formbg" align="center" border="0">
		<tr>
			<td colspan="6">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="100%" class="txt"><strong class="text_head">Configure
					Groups</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- add two list type-->
		<!--
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="h"){
		     
		      	document.getElementById('paraFrm').action='ApprovalSettings_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='ApprovalSettings_getAdminList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     if(listType=="f"){
		     
		      	document.getElementById('paraFrm').action='ApprovalSettings_getFinanceList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		      if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getFinanceGroupATRList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		       if(listType=="c"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getCorporateProcGroupList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		     if(listType=="p"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getPayrollList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     if(listType=="l"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getLogisticsList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     if(listType=="i"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getITList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     if(listType=="t"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getTrainingList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     if(listType=="e"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getEducationList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     if(listType=="w"){
		        
		      	document.getElementById('paraFrm').action='ApprovalSettings_getWorldTravelList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		    
		    //Add two new links
		   </script>
					<td><a href="#" onclick="setAction('h');">HR Group </a><b>
					| </b><a href="#" onclick="setAction('a');"> Admin Group </a><b> |
					</b><a href="#" onclick="setAction('f');">Finance Group </a><b> | </b><a
						href="#" onclick="setAction('r');">Finance Group-ATR</a><b> |
					</b><a href="#" onclick="setAction('p');">Payroll Group</a> <b> | </b><a
						href="#" onclick="setAction('l');">Corporate Procurement
					Group-NONINV</a><b> | </b><a href="#" onclick="setAction('c');">Corporate
					Procurement Group-HS</a><b> | </b><a href="#" onclick="setAction('i');">IT
					Group</a> <b> | </b><a href="#" onclick="setAction('t');">Training
					Authority Group</a> <b> | </b><a href="#" onclick="setAction('e');">Education
					Ministry Group</a><b> | </b><a href="#" onclick="setAction('w');">World
					Travel Group</a></td>
				</tr>
			</table>
			</td>
		</tr>
		-->
		<tr>
			<td colspan="6">
			<table width="100%" class="formbg" border="0" cellpadding="1"
				cellspacing="1">
				<tr>
					<td width="15%">Configure Groups<font color="red">*</font> :</td>
					<!-- Updated by Anantha lakshmi for Customer Concierge Program Service Alert request Group -->
					<td width="85%"><s:select headerKey=""
						headerValue="--Select--" cssStyle="width:220"
						name="configureGroupType"
						list="#{'H':'HR Group','F':'Finance Group','R':'Finance Group-ATR','P':'Payroll Group','L':'Corporate Procurement	Group-NONINV','C':'Corporate Procurement Group-HS','I':'IT Group(CD Rom)','K':'IT Group(App Security)','M':'IT Group(Report Request)','N':'IT Group(System Change)','T':'Training	Authority Group','E':'Education	Ministry Group','W':'World Travel Group','S':'CEAR Approval Group','D':'Customer Concierge Program Group','B':'BRD StakeHolder'}" />
					<input type="button" class="pagebutton" name="  Go  "
						value="   Go  " onclick="configureGroup();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- World Travel --Nilesh Start  -->
		<s:if test="worldTravelFlag">

			<tr id="worldTravelTable">
				<td colspan="3">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="15%" colspan="1"><strong>World Travel
						Group</strong></td>
						<td width="27%" colspan="1" align="right">
						<td width="58%" colspan="1" align="right">
						<%
							int totalWorldTravelPage = 0;
							int worldTravelPageNo = 0;
						%> <s:if test="worldTravelListPage">
							<table>
								<tr>
									<td align="right"><b>Page:</b> <%
									totalWorldTravelPage = (Integer) request
												.getAttribute("totalWorldTravelPage");
									worldTravelPageNo = (Integer) request
												.getAttribute("worldTravelPageNo");
									%> <a href="#"
										onclick="callWorldTravelPage('1', 'F', '<%=totalWorldTravelPage%>', 'ApprovalSettings_getWorldTravelList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callWorldTravelPage('P', 'P', '<%=totalWorldTravelPage%>', 'ApprovalSettings_getWorldTravelList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoWorldTravelField" id="pageNoWorldTravelField"
										size="3" value="<%=worldTravelPageNo%>" maxlength="10"
										onkeypress="callPageWorldTravelText(event, '<%=totalWorldTravelPage%>', 'ApprovalSettings_getWorldTravelList.action');return numbersOnly();" />
									of <%=totalWorldTravelPage%> <a href="#"
										onclick="callWorldTravelPage('N', 'N', '<%=totalWorldTravelPage%>', 'ApprovalSettings_getWorldTravelList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callWorldTravelPage('<%=totalWorldTravelPage%>', 'L', '<%=totalWorldTravelPage%>', 'ApprovalSettings_getWorldTravelList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>

				</table>
				</td>
			</tr>
			<!-- here 8 -->
			<tr>
				<td colspan="3">
				<table cellpadding="1" cellspacing="1" border="0" width="100%"
					class="formbg">
					<tr>
						<td colspan="1" width="10%"><b><label name="email.id"
							id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b>
						<font color="red">*</font>:</td>
						<td colspan="1" width="30%"><s:hidden name="worldId" /> <s:textfield
							size="40" name="emailId" /></td>
						<td colspan="1" width="60%"><s:submit
							action="ApprovalSettings_addWorldTavel" value=" Add to Group"
							cssClass="add" title="Save" onclick="return addWorldTavel();" /></td>
					</tr>
					<tr>
						<td width="100%" colspan="4">
						<table width="100%" class="formbg">
							<tr class="td_bottom_border">
								<td colspan="1" class="formth" width="10%">Sr. No.</td>
								<td colspan="1" class="formth" width="70%">Email_Id</td>
								<td colspan="1" class="formth" width="20%">Delete</td>
							</tr>
							<s:if test="worldTravelListPage">
								<%
								int count10 = 0;
								%>
								<%!int wt = 0;%>
								<%
								int z = worldTravelPageNo * 20 - 20;
								%>
								<s:iterator value="worldTravelList">
									<tr>
										<td colspan="1" width="10%" class="sortableTD"><%=++z%></td>
										<td colspan="1" width="70%" class="sortableTD"><s:hidden
											name="travelId" /> <s:property value="emailId" /></td>

										<td colspan="1" width="20%" class="sortableTD" align="center"><img
											src="../pages/common/css/icons/delete.gif" align="absmiddle"
											title="Delete World Travel"
											onclick="return deleteWorldTravel('<s:property value='travelId'/>');">
										</td>
									</tr>

								</s:iterator>
								<%
								wt = z;
								%>
							</s:if>

							<s:else>
								<td align="center" colspan="3" width="100%" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- World Travel Ends -->
		<s:if test="configureOptionTypeFlag">
			<tr>
				<td colspan="6">
				<table width="100%" class="formbg">
					<tr>
						<td height="27" class="formtxt"><strong> <%
											 	String status = (String) request.getParameter("configureGroupType");
											
											 			if (status == null) {
											 				//out.println("HR Group");
											 			} else if (status.equals("A")) {
											 				out.println("Admin Group");
											 			} else if (status.equals("F")) {
											 				out.println("Finance Group");
											 			} else if (status.equals("R")) {
											 				out.println("Finance Group-ATR");
											 			} else if (status.equals("P")) {
											 				out.println("Payroll Group");
											 			} else if (status.equals("L")) {
											 				out.println("Corporate Procurement Group-NONINV");
											 			}else if (status.equals("C")) {
											 				out.println("Corporate Procurement Group-HS");
											 			}
											 			else if (status.equals("I")) {
											 				out.println("IT Group(CD Rom)");
											 			}
											 			else if (status.equals("K")) {
											 				out.println("IT Group(App Security)");
											 			}
											 			else if (status.equals("M")) {
											 				out.println("IT Group(Report Request)");
											 			}
											 			else if (status.equals("N")) {
											 				out.println("IT Group(System Change)");
											 			}
											 			else if (status.equals("T")) {
											 				out.println("Training Authority Group");
											 			}else if (status.equals("E")) {
											 				out.println("Education Ministry Group");
											 			}
											 			else if (status.equals("W")) {
											 				out.println("World Travel Group");
											 			}
											 			else if (status.equals("H")) {
											 				out.println("HR Group");
											 			}
											 			else if (status.equals("S")) {
											 				out.println("CEAR Approval Group");
											 			}
											 			
											 			else if (status.equals("D")) {
											 				out.println("Customer Concierge Program Group");
											 			}
											 %> </strong></td>




						<td width="45%" colspan="1" align="right">
						<td width="25%" colspan="1" align="right">
						<%
							int totalConfigureOptionPage = 0;
							int configureOptionPageNo = 0;
						%> <s:if test="configureOptionListPage">
							<!--<table>
								<tr>
									<td id="ctrlShow" align="right"><b>Page:</b> <%
									totalConfigureOptionPage = (Integer) request
												.getAttribute("totalConfigureOptionPage");
									configureOptionPageNo = (Integer) request
												.getAttribute("configureOptionPageNo");
									%> <a href="#"
										onclick="callConfigureAuthPage('1', 'F', '<%=totalConfigureOptionPage%>', 'ApprovalSettings_getTrainingList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callConfigureAuthPage('P', 'P', '<%=totalConfigureOptionPage%>', 'ApprovalSettings_getTrainingList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoTrainingAuthField" id="pageNoTrainingAuthField"
										size="3" value="<%=configureOptionPageNo%>" maxlength="10"
										onkeypress="callPageConfigureAuthText(event, '<%=totalConfigureOptionPage%>', 'ApprovalSettings_getTrainingList.action');return numbersOnly();" />
									of <%=totalConfigureOptionPage%> <a href="#"
										onclick="callConfigureAuthPage('N', 'N', '<%=totalConfigureOptionPage%>', 'ApprovalSettings_getTrainingList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callConfigureAuthPage('<%=totalConfigureOptionPage%>', 'L', '<%=totalConfigureOptionPage%>', 'ApprovalSettings_getTrainingList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						-->
						</s:if></td>
					</tr>

				</table>
				</td>
			</tr>
			<tr>
				<td colspan="6">
				<table cellpadding="1" cellspacing="1" border="0" width="100%"
					class="formbg">
					<tr>
						<td width="15%" colspan="1"><strong>Group Email Ids
						</strong></td>
						<td width="27%" colspan="1" align="right">
						<td width="58%" colspan="1" align="right"></td>
					</tr>
					<tr>
						<td colspan="1" width="10%"><b><label name="email.id"
							id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b>
						<font color="red">*</font>:</td>
						<td colspan="1" width="30%"><s:hidden
							name="configHiddenEmailId" /> <s:textfield size="40"
							name="configEmail" /></td>
						<td colspan="1" width="60%"><s:submit
							action="ApprovalSettings_addConfigEmail" value=" Add to Group"
							cssClass="add" title="Save" onclick="return addConfigEmail();" /></td>
					</tr>
					<tr>
						<td width="100%" colspan="4">
						<table width="100%" class="formbg">
							<tr class="td_bottom_border">
								<td colspan="1" class="formth" width="10%">Sr. No.</td>
								<td colspan="1" class="formth" width="70%">Email_Id</td>
								<td colspan="1" class="formth" width="20%">Edit | Delete</td>
							</tr>
							<s:if test="worldTravelListPage">
								<%
									int count11 = 0;
								%>
								<%!
									int wt1 = 0;
								%>
								<%
									int w = 0;
								%>
								<s:iterator value="worldTravelList">
									<tr>
										<td colspan="1" width="10%" class="sortableTD"><%=++w%></td>
										<td colspan="1" width="70%" class="sortableTD"><s:hidden
											name="configEmailId" /> <s:property value="configEmail" /></td>

										<!--<td colspan="1" width="20%" class="sortableTD" align="center"><img
											src="../pages/common/css/icons/delete.gif" align="absmiddle"
											title="Delete World Travel"
											onclick="return deleteWorldTravel('<s:property value='configEmailId'/>');">
										</td>
										-->
										<td width="25%" class="sortableTD" align="center" nowrap>
										<input type="button" class="rowEdit"
											onclick="callForEditEmail('<s:property value="configEmailId"/>')" />
										| <input type="button" class="rowDelete"
											onclick="callDeleteEmail('<s:property value="configEmailId" />')" />
										</td>
									</tr>
								</s:iterator>
								<%
									wt1 = w;
								%>
							</s:if>

							<s:else>
								<td align="center" colspan="3" width="100%" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- here 6 -->
			<tr>
				<td colspan="6">
				<table cellpadding="1" cellspacing="1" border="0" width="100%"
					class="formbg">
					<tr>
						<td width="100%" colspan="6"><strong>Employees in
						the Group </strong></td>

					</tr>

					<tr>
						<s:hidden name="configAuthId" />
						<td colspan="1" width="15%">Employee :<font color="red">*</font></td>
						<td colspan="5" width="50%"><s:hidden
							name="searchConfigAuthId" /> <s:textfield size="10"
							name="searchConfigAuthToken" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:textfield size="50"
							name="searchConfigAuthName" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							title="Select the Employee"
							onclick="callsF9(500,325,'ApprovalSettings_f9ConfigAuth.action');">
						</td>

					</tr>

					<tr>
						<td>Action<font color="red"></font> :</td>

						<td><s:checkbox name="configReceivedFlag" onclick="" /></td>



					</tr>
					<tr>
						<td colspan="4" align="center"><s:submit
							action="ApprovalSettings_addConfigAuth" value=" Add to Group"
							cssClass="add" title="Add " onclick="return addConfigAuth();" /></td>
					</tr>
					<tr>
						<td width="100%" colspan="6">
						<table width="100%" class="formbg">
							<tr class="td_bottom_border">
								<td colspan="1" width="5%" class="formth">Sr.No.</td>
								<td colspan="1" class="formth" width="10%">Employee Id</td>
								<td colspan="1" class="formth" width="20%">Employee Name</td>

								<td colspan="1" class="formth" width="15%">Action</td>

								<td colspan="1" class="formth" width="20%">Edit | Delete</td>
							</tr>
							<s:if test="configureOptionListPage">
								<%
								int count6 = 0;
								%>
								<%!int tr = 0;%>
								<%
								int i = configureOptionPageNo * 20 - 20;
								%>
								<s:iterator value="trainingAuthList">
									<tr>
										<td colspan="1" width="5%" class="sortableTD"><%=++i%></td>
										<td colspan="1" width="10%" class="sortableTD"><s:hidden
											name="configAuthHiddenId" /> <s:hidden
											name="searchConfigEmpAuthId" /> <s:property
											value="configEmpToken" /></td>
										<td colspan="1" width="20%" class="sortableTD"><s:property
											value="configEmpName" /></td>

										<td align="center" class="sortableTD" width="15%"><s:property
											value="isActive" /></td>

										<!--<td colspan="1" width="20%" class="sortableTD" align="center"><img
											src="../pages/common/css/icons/delete.gif" align="absmiddle"
											title="Delete World Travel"
											onclick="return deleteWorldTravel('<s:property value='travelId'/>');">
										</td>
									-->
										<td width="25%" class="sortableTD" align="center" nowrap>
										<input type="button" class="rowEdit"
											onclick="callForEdit('<s:property value="configAuthHiddenId"/>')" />
										| <input type="button" class="rowDelete"
											onclick="callDelete('<s:property value="configAuthHiddenId" />')" />
										</td>
									</tr>

								</s:iterator>
								<%
								tr = i;
								%>
							</s:if>

							<s:else>
								<td align="center" colspan="6" width="100%" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
	</table>
</s:form>



<script type="text/javascript">
//call();

function callForEditEmail(configEmailId){
    //alert(configEmailId);
    
    	
        document.getElementById('paraFrm').target = "_self";
	   	document.getElementById('paraFrm').action="ApprovalSettings_editEmail.action?configEmailId="+configEmailId;
	    document.getElementById('paraFrm').submit();
	    
	   
   }
   
	function callForEdit(configAuthHiddenId){
   // alert(configAuthHiddenId);
    
    	
        document.getElementById('paraFrm').target = "_self";
	   	document.getElementById('paraFrm').action="ApprovalSettings_edit.action?configAuthHiddenId="+configAuthHiddenId;
	    document.getElementById('paraFrm').submit();
	    
	   
   }
   
   function callDeleteEmail(configHiddenEmailId){
  		//alert(configHiddenEmailId); 
   		var conf=confirm("Are you sure to delete this record?");
   		if(conf) {
   		document.getElementById('paraFrm').target = "_self";
   	   	document.getElementById("paraFrm").action="ApprovalSettings_deleteEmail.action?configHiddenEmailId="+configHiddenEmailId;
	    	document.getElementById("paraFrm").submit();
   } else {
   		return false;	
   }
   }
   
   function callDelete(configAuthId){
  		//alert(configAuthId); 
   		var conf=confirm("Are you sure to delete this record?");
   		if(conf) {
   		document.getElementById('paraFrm').target = "_self";
   	   	document.getElementById("paraFrm").action="ApprovalSettings_delete.action?configAuthId="+configAuthId;
	    	document.getElementById("paraFrm").submit();
   } else {
   		return false;	
   }
   }

	function addHRApprover() {

   var searchHrEmpId = document.getElementById('paraFrm_searchHrEmpId').value;
   var emailId = document.getElementById('paraFrm_emailId').value;
	
		if(searchHrEmpId == '') {
			alert('Please select an employee for HR Group');
			return false;
		}

       if(emailId==""){
    alert("Please enter email address");
          return false;

}

		return true;
	}

function addConfigAuth() {

   var searchHrEmpId = document.getElementById('paraFrm_searchConfigAuthName').value;
   
		if(searchHrEmpId == '') {
			alert('Please select an employee for the Group');
			document.getElementById('paraFrm_searchConfigAuthName').focus();
			return false;
		}
    		return true;
	}
	
	function deleteHRApprover(hrApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteHRApprover.action?hrApproverEmpId=' + hrApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function addAdminApprover() {
		var searchAdminEmpId = document.getElementById('paraFrm_searchAdminEmpId').value;

		if(searchAdminEmpId == '') {
			alert('Please select an employee for Admin Group');
			return false;
		}

		return true;
	}
	function addFinanceApprover() {
		var searchFinanceEmpId = document.getElementById('paraFrm_searchFinanceEmpId').value;

		if(searchFinanceEmpId == '') {
			alert('Please select an employee for Finance Group');
			return false;
		}

		return true;
	}
	
	
	
	function addFinanceATRApprover() {
		var searchFinanceATREmpId = document.getElementById('paraFrm_searchFinanceATREmpId').value;

		if(searchFinanceATREmpId == '') {
			alert('Please select an employee for Finance Group-ATR');
			return false;
		}

		return true;
	}
	
	function addCorporateApprover() {
		var searchCorporateEmpId1 = document.getElementById('paraFrm_searchCorporateEmpId1').value;

		if(searchCorporateEmpId1 == '') {
			alert('Please select an employee for Corporate Procurement Group-HS');
			return false;
		}

		return true;
	}
	
	function addPayrollApprover() {
		var searchPayrollEmpId = document.getElementById('paraFrm_searchPayrollEmpId').value;

		if(searchPayrollEmpId == '') {
			alert('Please select an employee for Payroll Group');
			return false;
		}

		return true;
	}
	
	function addLogisticsApprover() {
		var searchLogisticsEmpId = document.getElementById('paraFrm_searchLogisticsEmpId').value;

		if(searchLogisticsEmpId == '') {
			alert('Please select an employee for Corporate Procurement Group-NONINV ');
			return false;
		}

		return true;
	}
	function addItApprover() {
		var searchItEmpId = document.getElementById('paraFrm_searchItEmpId').value;

		if(searchItEmpId == '') {
			alert('Please select an employee for IT Group');
			return false;
		}

		return true;
	}
	
	function addTrainingAuth() {
		var searchTrainingAuthId = document.getElementById('paraFrm_searchTrainingAuthId').value;

		if(searchTrainingAuthId == '') {
			alert('Please select an employee for Training Authority Group');
			return false;
		}

		return true;
	}
	
	function addEducationMinistry() {
		var searchEducationMinistryId = document.getElementById('paraFrm_searchEducationMinistryId').value;

		if(searchEducationMinistryId == '') {
			alert('Please select an employee for Education Ministry Group');
			return false;
		}

		return true;
	}
	function addWorldTavel() {
	var fields=["paraFrm_emailId"];
    var labels=["email.id"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;
	 
   if(!validateEmailAPS('paraFrm_emailId')){
		 	return false;
		 }
	}
	
	
	function addConfigEmail() {
	
	/*alert("emailid");*/
	try{
	var fields=["paraFrm_configEmail"];
    var labels=["email.id"];
    var flag = ["enter"];
   
 	 if(!validateBlank(fields,labels,flag))
     return false;
	
   if(!validateEmailAPS('paraFrm_configEmail')){
		 	return false;
		 }
		 
		 }catch(e)
		 {
		 alert(e);
		 }
	}
	
	
	function validateEmailAPS (name) {
	var name1 = document.getElementById(name).value.toLowerCase();
	var emailStr        = name1;
	if(emailStr=="")return true;
	
	var checkTLD        = 1;
	
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	var emailPat		= /^(.+)@(.+)$/;
	
	var specialChars	= "\\(\\)><@,;:-\\\\\\\"\\.\\[\\]";
	
	var validChars		= "\[^\\s" + specialChars + "\]";
	
	var quotedUser		= "(\"[^\"]*\")";
	
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	var atom			= validChars + '+';
	
	var word			= "(" + atom + "|" + quotedUser + ")";
	
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	
	var matchArray		= emailStr.match(emailPat);

	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		document.getElementById(name).focus();
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	
/*	
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			document.getElementById(name).focus();
			return false;
	 	}
*/	
	
	 	
	 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127 ) {
				alert("The username contains invalid characters.");
				document.getElementById(name).focus();
				return false;
		 }
	}

	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			document.getElementById(name).focus();
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		document.getElementById(name).focus();
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				document.getElementById(name).focus();
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		document.getElementById(name).focus();
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		document.getElementById(name).focus();
		return false;
	}
	
	return true;
}
	
	
	/* function addWorldTavel() {
		var emailId = document.getElementById('paraFrm_emailId').value;

		if(emailId == '') {
			alert("Please Enter Email-Id");
			return false;
		}

		return true;
	}*/
	

	function deleteAdminApprover(adminApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteAdminApprover.action?adminApproverEmpId=' + adminApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteFinanceApprover(financeApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteFinanceApprover.action?financeApproverEmpId=' + financeApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteFinanceATRApprover(financeATRApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteFinanceATRApprover.action?financeATRApproverEmpId=' + financeATRApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	
	function deleteCorporateApprover(corporateApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteCorporateProcurement.action?corporateApproverEmpId=' + corporateApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deletePayrollApprover(payrollApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deletePayrollApprover.action?payrollApproverEmpId=' + payrollApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteLogisticsApprover(logisticsApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteLogisticsApprover.action?logisticsApproverEmpId=' + logisticsApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	function deleteItApprover(itApproverEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteItApprover.action?itApproverEmpId=' + itApproverEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteTrainingAuth(trainingAuthEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteTrainingAuth.action?trainingAuthEmpId=' + trainingAuthEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteEducationMinistry(educationMinistryEmpId) {
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteEducationMinistry.action?educationMinistryEmpId=' + educationMinistryEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function deleteWorldTravel(travelId) {
	/*alert("travelId "+travelId);*/
		var con=confirm('Do you want to delete the record(s) ?');
		 if(con)
		 {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ApprovalSettings_deleteWorldTravel.action?travelId=' + travelId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	
	
	
	
	
	
	// Function for HR Approver List Begins
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
	// Function HR APPROVE List Ends
	
	// Function for ADMIN APPROVE List Begins
	function callAdminPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoAdminField').value;	
		actPage = document.getElementById('myPageAdmin').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoAdminField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoAdminField').value = actPage;
			    document.getElementById('pageNoAdminField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoAdminField').value=actPage;
			    document.getElementById('pageNoAdminField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoAdminField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoAdminField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoAdminField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoAdminField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoAdminField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoAdminField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageAdmin').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageAdminText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoAdminField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoAdminField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoAdminField').focus();
		     document.getElementById('pageNoAdminField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoAdminField').focus();
		     document.getElementById('pageNoAdminField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoAdminField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageAdmin').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function ADMIN APPROVE List Ends
	// Function for Finance APPROVE List Begins
	function callFinancePage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoFinanceField').value;	
		actPage = document.getElementById('myFinancePage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFinanceField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFinanceField').value = actPage;
			    document.getElementById('pageNoFinanceField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFinanceField').value=actPage;
			    document.getElementById('pageNoFinanceField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFinanceField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFinanceField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFinanceField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFinanceField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFinanceField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFinanceField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myFinancePage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageFinanceText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFinanceField').value;
		 	var actPage = document.getElementById('myFinancePage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFinanceField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFinanceField').focus();
		     document.getElementById('pageNoFinanceField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFinanceField').focus();
		     document.getElementById('pageNoFinanceField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFinanceField').focus();
		      return false;
	        }
	        
	         document.getElementById('myFinancePage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	//Finance Group ATR start
	
		function callFinanceATRPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoFinanceATRField').value;	
		actPage = document.getElementById('myFinanaceATRPAge').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFinanceATRField').value = actPage;
			    document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFinanceATRField').value=actPage;
			    document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFinanceATRField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFinanceATRField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFinanceATRField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myFinanaceATRPAge').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	//Finance Group ATR end
	
	//Finance ATR page text start
	
	function callPageFinanceATRText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFinanceATRField').value;
		 	var actPage = document.getElementById('myFinanaceATRPAge').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFinanceATRField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFinanceATRField').focus();
		     document.getElementById('pageNoFinanceATRField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFinanceATRField').focus();
		     document.getElementById('pageNoFinanceATRField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFinanceATRField').focus();
		      return false;
	        }
	        
	         document.getElementById('myFinanaceATRPAge').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	//Corporate Pro Grp Page
	 
	 function callCorporatePage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoFinanceATRField').value;	
		actPage = document.getElementById('myFinanaceATRPAge').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFinanceATRField').value = actPage;
			    document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFinanceATRField').value=actPage;
			    document.getElementById('pageNoFinanceATRField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFinanceATRField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFinanceATRField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFinanceATRField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFinanceATRField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myFinanaceATRPAge').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	//Corporate Pro Grp page text start
	
	function callPageCorporateText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFinanceATRField').value;
		 	var actPage = document.getElementById('myFinanaceATRPAge').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFinanceATRField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFinanceATRField').focus();
		     document.getElementById('pageNoFinanceATRField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFinanceATRField').focus();
		     document.getElementById('pageNoFinanceATRField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFinanceATRField').focus();
		      return false;
	        }
	        
	         document.getElementById('myFinanaceATRPAge').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Function ADMIN APPROVE List Ends
	
	// Function for Payroll APPROVE List Begins
	function callPayrollPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoPayrollField').value;	
		actPage = document.getElementById('myPayrollPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoPayrollField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoPayrollField').value = actPage;
			    document.getElementById('pageNoPayrollField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoPayrollField').value=actPage;
			    document.getElementById('pageNoPayrollField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoPayrollField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoPayrollField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoPayrollField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoPayrollField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoPayrollField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoPayrollField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPayrollPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPagePayrollText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoPayrollField').value;
		 	var actPage = document.getElementById('myPayrollPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoPayrollField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoPayrollField').focus();
		     document.getElementById('pageNoPayrollField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoPayrollField').focus();
		     document.getElementById('pageNoPayrollField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoPayrollField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPayrollPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Payroll APPROVE List Ends
	
	// Function for IT APPROVE List Begins
	function callLogisticsPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoLogisticsField').value;	
		actPage = document.getElementById('myLogisticsPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoLogisticsField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoLogisticsField').value = actPage;
			    document.getElementById('pageNoLogisticsField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoLogisticsField').value=actPage;
			    document.getElementById('pageNoLogisticsField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoLogisticsField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoLogisticsField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoLogisticsField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoLogisticsField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoLogisticsField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoLogisticsField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myLogisticsPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageLogisticsText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoLogisticsField').value;
		 	var actPage = document.getElementById('myLogisticsPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoLogisticsField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoLogisticsField').focus();
		     document.getElementById('pageNoLogisticsField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoLogisticsField').focus();
		     document.getElementById('pageNoLogisticsField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoLogisticsField').focus();
		      return false;
	        }
	        
	         document.getElementById('myLogisticsPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Logistics APPROVE List Ends
	
	// Function for Logistics APPROVE List Begins
	function callITPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoITField').value;	
		actPage = document.getElementById('myItPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoITField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoITField').value = actPage;
			    document.getElementById('pageNoITField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoITField').value=actPage;
			    document.getElementById('pageNoITField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoITField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoITField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoITField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoITField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoITField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoITField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myItPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageITText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoITField').value;
		 	var actPage = document.getElementById('myItPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoITField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoITField').focus();
		     document.getElementById('pageNoITField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoITField').focus();
		     document.getElementById('pageNoITField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoITField').focus();
		      return false;
	        }
	        
	         document.getElementById('myItPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function IT APPROVE List Ends
	
	// Function for Training Authority List Begins
	function callTrainingAuthPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoTrainingAuthField').value;	
		
		actPage = document.getElementById('myTrainingAuthPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoTrainingAuthField').value = actPage;
			    document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoTrainingAuthField').value=actPage;
			    document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoTrainingAuthField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoTrainingAuthField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoTrainingAuthField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myTrainingAuthPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageTrainingAuthText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoTrainingAuthField').value;
		 	var actPage = document.getElementById('myTrainingAuthPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoTrainingAuthField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoTrainingAuthField').focus();
		     document.getElementById('pageNoTrainingAuthField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoTrainingAuthField').focus();
		     document.getElementById('pageNoTrainingAuthField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoTrainingAuthField').focus();
		      return false;
	        }
	        
	         document.getElementById('myTrainingAuthPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Training Authority List Ends
	
	// Function for Education Ministry List Begins
	function callEducationMinistryPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoEducationMinistryField').value;	
		
		actPage = document.getElementById('myEducationMinistryPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoEducationMinistryField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoEducationMinistryField').value = actPage;
			    document.getElementById('pageNoEducationMinistryField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoEducationMinistryField').value=actPage;
			    document.getElementById('pageNoEducationMinistryField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoEducationMinistryField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoEducationMinistryField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoEducationMinistryField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoEducationMinistryField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoEducationMinistryField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoEducationMinistryField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myEducationMinistryPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageEducationMinistryText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoEducationMinistryField').value;
		 	var actPage = document.getElementById('myEducationMinistryPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoEducationMinistryField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoEducationMinistryField').focus();
		     document.getElementById('pageNoEducationMinistryField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoEducationMinistryField').focus();
		     document.getElementById('pageNoEducationMinistryField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoEducationMinistryField').focus();
		      return false;
	        }
	        
	         document.getElementById('myEducationMinistryPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Education Ministry List Ends
	
	//World Travel Starts here---Nilesh
	
	function callWorldTravelPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoWorldTravelField').value;	
		
		actPage = document.getElementById('myWorldTravelPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoWorldTravelField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoWorldTravelField').value = actPage;
			    document.getElementById('pageNoWorldTravelField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoWorldTravelField').value=actPage;
			    document.getElementById('pageNoWorldTravelField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoWorldTravelField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoWorldTravelField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoWorldTravelField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoWorldTravelField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoWorldTravelField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoWorldTravelField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myWorldTravelPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageWorldTravelText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoWorldTravelField').value;
		 	var actPage = document.getElementById('myWorldTravelPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoWorldTravelField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoWorldTravelField').focus();
		     document.getElementById('pageNoWorldTravelField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoWorldTravelField').focus();
		     document.getElementById('pageNoWorldTravelField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoWorldTravelField').focus();
		      return false;
	        }
	        
	         document.getElementById('myWorldTravelPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	// Function for Authority List Begins
	function callConfigureAuthPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoTrainingAuthField').value;	
		
		actPage = document.getElementById('myConfigureOptionPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoTrainingAuthField').value = actPage;
			    document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoTrainingAuthField').value=actPage;
			    document.getElementById('pageNoTrainingAuthField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoTrainingAuthField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoTrainingAuthField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoTrainingAuthField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoTrainingAuthField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myConfigureOptionPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageConfigureAuthText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoTrainingAuthField').value;
		 	var actPage = document.getElementById('myConfigureOptionPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoTrainingAuthField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoTrainingAuthField').focus();
		     document.getElementById('pageNoTrainingAuthField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoTrainingAuthField').focus();
		     document.getElementById('pageNoTrainingAuthField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoTrainingAuthField').focus();
		      return false;
	        }
	        
	         document.getElementById('myConfigureOptionPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Authority List Ends
	
	function configureGroup(){
	try{		
		var configureOption = document.getElementById('paraFrm_configureGroupType').value;
		//alert(configureOption);
		if(configureOption==''){  
		alert("Please select group to configure.");
		      document.getElementById('paraFrm_configureGroupType').focus();
		      return false;
	        }	       
	       }catch(e){alert(e);}
	         document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'ApprovalSettings_configureOptionType.action?configureOption=' + configureOption;	
	    document.getElementById('paraFrm').submit();
	  
	}	
</script>