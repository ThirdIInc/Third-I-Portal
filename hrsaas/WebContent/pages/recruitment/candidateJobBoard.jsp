<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="CandidateJobBoard" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="advtName" />
	<s:hidden name="checkUploadProfile" />
	<table class="formbg" width="100%">
		<!--Final Table -->
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Job
					Board</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 3-->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<!--Table 5-->
						<tr>
							<td height="27" class="tesxt_head"><strong>Job
							Board Details</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="5%" valign="top" class="mypageTh" >Sr.No</td>
							<td width="30%" valign="top" class="mypageTh">Job Name</td>
							<td width="20%" valign="top" class="mypageTh">Position</td>
							<td width="20%" valign="top" class="mypageTh">Department</td>
							<td width="15%" valign="top" class="mypageTh" >Location</td>
							<td width="5%" valign="top" class="mypageTh">Apply</td>
							<td width="5%" valign="top" class="mypageTh">Upload Document</td>
						</tr>
						<s:if test="jobBoardNoRecord">
							<tr>
								<td width="100%" colspan="9" align="center"><font
									color="red"><b>No data to display</b></font></td>
							</tr>
						</s:if>
						<%!int i = 0;%>
						<% int k = 1;%>
						<s:iterator value="list">
							<tr>
								<td class="sortableTD" nowrap="nowrap" align="center"><%=k %></td>
								<td class="sortableTD">&nbsp;
								
								<a
								href="javascript:void(0);"
								onClick="callJobNameDetailsWindow('CandidateJobBoard_getJobNameDetails.action?reqCodeStr=<s:property value='reqCode'/>&&app_random=<%=java.lang.Math.random()%>',event)"
								class="contlink"
								title="<s:property value="reqName" />"
								target="main">
								<s:property value="reqName" />
								</a>
								<s:hidden name="reqCode" /></td>
								<td class="sortableTD"><s:property value="reqPositionName" />
								<s:hidden name="reqPositionCode" /></td>
								<td class="sortableTD"><s:property value="reqDept" /></td>
								<td class="sortableTD">&nbsp;<s:property value="reqBrn" />
								<s:hidden name="reqBrnCode" /></td>
								<!--<td class="sortableTD" align="center">&nbsp;<s:property
									value="noOfVac" /></td>

								--><td class="sortableTD" width="10%"><s:if
									test="applyFlag">

									<a href="javascript:void(0);" title="Apply for job"
										class="contlink"
										onclick="apply('<s:property value="reqCode" />');"> Apply
									</a>

								</s:if> <s:else>
									<font color="red">Applied Successfully</font>
								</s:else></td>
								<td class="sortableTD"><s:if
									test="applyFlag">
								</s:if> <s:else>

									<a href="javascript:void(0);" title="Upload Document"
										class="contlink"
										onclick="uploadDocument('<s:property value="reqCode" />');">
									Upload </a>

								</s:else></td>

							</tr>
							<%
									k++;
									%>
						</s:iterator>
						<%
									i = k;
									k = 1;
									%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!--End of Final Table -->
</s:form>
<script>
 						function callJobNameDetailsWindow(action,e) {
 						
 						//alert(action);
			 					callPageDisplay(action,'500','300','false',e);	 
							}


function uploadDocument(reqCode)
{
document.getElementById("paraFrm").action='UploadDocuments_showCheckList.action?requisitionCode='+reqCode;
	    document.getElementById("paraFrm").submit();

}

function viewRequisition(reqCode,status){
	
		document.getElementById("paraFrm").action='EmployeeRequi_candJobBoardViewReq.action?reqCode='+reqCode+'&formAction=MyRequisition_callstatus.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
}

function apply(reqCode){
	var profileUpdate = document.getElementById('paraFrm_checkUploadProfile').value;
	if(profileUpdate == "no"){
		alert("Please fill your profile details.");
	} //end of if
	else{
		window.open('','new','top=100,left=300,width=600,height=300,scrollbars=yes,status=yes,resizable=no');
		document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action = 'CandidateJobBoard_sourceOfResume.action?requisitionCode='+reqCode; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	} //end of else
}

</script>