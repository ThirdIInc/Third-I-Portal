<%@include file="/pages/common/labelManagement.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="TravelClaimDefaulter" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<s:hidden name="defaulterStatus" />
		<s:hidden name="myPage" />
		<s:hidden name="appId" />
		<tr>
			<s:hidden name="gradeId" />
			<s:hidden name="appCode" />
			<s:hidden name="appDate" />
			<s:hidden name="startDate" />
			<td width="100%"><s:hidden name="endDate" />
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Advance Settlement</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><input type="button" class="token"
						onclick="return check();" value="Return to List" /></td>

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
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="70%" colspan="3"><strong class="text_head">Employee
							Details :</strong></td>

						</tr>

						<tr>

							<td colspan="1" width="20%" height="22"><label id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="employee" />
							</td>

							<td colspan="1" width="20%"><label id="trvlStartDate"
								name="trvlStartDate" ondblclick="callShowDiv(this);"><%=label.get("trvlStartDate")%></label>
							:</td>
							<td><s:property value="trvlStartDate" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%" height="22"><label id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="branch" /></td>

							<td colspan="1" width="20%"><label id="trvlEndDate"
								name="trvlEndDate" ondblclick="callShowDiv(this);"><%=label.get("trvlEndDate")%></label>
							:</td>
							<td><s:property value="trvlEndDate" /></td>
						</tr>



						<tr>

							<td colspan="1" width="20%" height="22"><label
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="department" />

							</td>

							<td colspan="1" width="20%"><label id="grade" name="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td><s:property value="grade" /></td>
						</tr>



						<tr>

							<td colspan="1" width="20%" height="22"><label
								id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="desg" /></td>

							<td colspan="1" width="10%"><input type="button"
								value="View Travel Policy" class="token"
								onclick="viewPolicy(document.getElementById('paraFrm_gradeId').value);"
								align="top"></td>
							<td></td>
						</tr>


						<tr>
							<td colspan="1" width="20%" height="22"><label id="appDate"
								name="appDate" ondblclick="callShowDiv(this);"><%=label.get("appDate")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="appDate" /></td>

							<!--<td colspan="1" width="20%"><input type="button"
								class="token" value="Booking Details"
								onclick="showBookingDtls(document.getElementById('paraFrm_appId').value,document.getElementById('paraFrm_appCode').value,document.getElementById('paraFrm_empCode').value,document.getElementById('paraFrm_appDate').value,document.getElementById('paraFrm_initCode').value);">
							</td>
						--></tr>

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
					<td width="70%" colspan="3"><strong class="text_head">Travel
					Details :</strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" height="22"><label
						id="trvlRqeName" name="trvlRqeName"
						ondblclick="callShowDiv(this);"><%=label.get("trvlRqeName")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property value="requestName" /></td>

					<td colspan="1" width="20%"><label id="trvPurpose"
						name="trvPurpose" ondblclick="callShowDiv(this);"><%=label.get("trvPurpose")%></label>
					:</td>
					<td><s:property value="trvPurpose" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" height="22"><label id="advAmtTak"
						name="advAmtTak" ondblclick="callShowDiv(this);"><%=label.get("advAmtTak")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property value="advance" /></td>

					<td colspan="1" width="20%"><label id="trvType" name="trvType"
						ondblclick="callShowDiv(this);"><%=label.get("trvType")%></label>
					:</td>
					<td><s:property value="trvlType" /></td>
				</tr>


			</table>
			</td>
		</tr>
		<s:if test="defaulterStatus">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">

					<tr>
						<td width="70%" colspan="3"><strong class="text_head">Settlement
						Amount</strong></td>
					</tr>
					<tr>
						<td colspan="1" width="20%" height="22"><label id="amtDeduct"
							name="amtDeduct" ondblclick="callShowDiv(this);"><%=label.get("amtDeduct")%></label>
						:</td>
						<td colspan="1" width="30%"><s:property value="deduction" />
						</td>

						<td colspan="1" width="20%"><label id="dedDate"
							name="dedDate" ondblclick="callShowDiv(this);"><%=label.get("dedDate")%></label>
						:</td>
						<td><s:property value="dateOfDed" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>

			<td width="100%"><input type="button" class="token"
				onclick="return check();" value="Return to List" /></td>
		</tr>

	</table>
	<s:hidden name="empCode" />
	<s:hidden name="initCode" />
</s:form>
<script>

	             function showBookingDtls(appId,appCode,empId,appDate,initId){	             
		           		win=window.open('','win','top=260,left=250,width=900,height=600,scrollbars=yes,status=no,resizable=no');
						document.getElementById("paraFrm").target="win";
						//document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&dtlsType=SCH&userType=SCH";
						document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&dtlsType=SCH&userType=SCH&dtlsFor=CLAIM";
						document.getElementById("paraFrm").submit();
						document.getElementById("paraFrm").target="main";
	                 }

		         function viewPolicy(gradId){		     
							win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
							document.getElementById("paraFrm").target="win";
							document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
							document.getElementById("paraFrm").submit();	
							document.getElementById("paraFrm").target="main"; 
		                }


					function check(){
						if(document.getElementById("paraFrm_defaulterStatus").value=="true"){
							document.getElementById("paraFrm").action="TravelClaimDefaulter_closedDefaltr.action";
							document.getElementById("paraFrm").submit();
						}else{
							document.getElementById("paraFrm").action="TravelClaimDefaulter_input.action";
							document.getElementById("paraFrm").submit();
						}
					
					}
</script>




