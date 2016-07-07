<!--Bhushan Dasare--><!--June 6, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="UploadArrears" name="UploadMonthlyAttendance" id="paraFrm" validate="true" target="main" theme="simple">	
    <s:hidden name="arrearsCode" /> 
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Upload Arrears</strong></td>
						<td width="4%" valign="middle" class="txt" align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> 
							<s:submit value=" Reset" cssClass="reset" action="UploadArrears_reset" title="Clear the fields" /> 
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="15%">
							<label id="paidMonth" name="paidMonth" ondblclick="callShowDiv(this);"><%=label.get("paidMonth")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:select name="month"  title="Select the month"
							list="#{'':'--Select--','1':'January','2':'Febuary','3':'March','4':'April','5':'May','6':'June','7':'July',
							'8':'August','9':'September','10':'October','11':'November','12':'December'}" /> 
						 </td>
						<td width="10%"></td>
						<td width="15%">
							<label id="paidYear" name="paidYear" ondblclick="callShowDiv(this);"><%=label.get("paidYear")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
							onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
						</td>
						<td width="25%" ><s:hidden name="divisionId" />
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />								
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'UploadArrears_f9Division.action');">
						</td>
						<td width="15%">
							<s:hidden name="branchId" />
							<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="branchName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'UploadArrears_f9Branch.action');">
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<s:hidden name="departmentId" /> 
							<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="departmentName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the department" onclick="callsF9(500,325,'UploadArrears_f9Department.action');">
						</td>
						
						<td width="15%">
							<s:hidden name="paybillId" /> 
							<label id="paybill" name="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="paybillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the pay bill" onclick="callsF9(500,325,'UploadArrears_f9paybill.action');">
						</td>
						<!--<td width="15%" align="left">
										<label id="credit.Name" name="credit.Name" ondblclick="callShowDiv(this);"><%=label.get("credit.Name")%></label> :<font color="red">*</font>
									</td>
						<td width="25%">
										<s:textfield name="uploadCreditName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
										<s:hidden name="uploadCreditCode"/>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'UploadArrears_f9CreditAction.action');">
						</td>
						</td>
						
					--></tr>   
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="55%" align="right">
							<label id="downLoad.template" name="downLoad.template" ondblclick="callShowDiv(this);"><%=label.get("downLoad.template")%></label>:
						</td>
						<td>
							<s:submit value="Download" cssClass="token" action="UploadArrears_downLoadFile" 
							title="Download an template" onclick="return callDownLoad();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">  
					<tr>
						<td width="100%" align="center">
							<label id="upload.Name" name="upload.Name" ondblclick="callShowDiv(this);"><%=label.get("upload.Name")%></label> :<font color="red">*</font>
							
							<s:textfield name="uploadFileName" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />&nbsp;&nbsp;&nbsp;
							
							<input type="button" class="token" theme="simple" value="Select XLS File" title="Select XLS file" 
							onclick="uploadFile('uploadFileName');" />
						</td>
					</tr>
					<tr>
						<td width="100%" align="center">
							<s:submit cssClass="token" theme="simple" value="Upload" action="UploadArrears_uploadArrears" 
							title="Upload XLS file" onclick="return callUpload();" /> 
						</td>
					</tr>
				</table>
	    	</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> 
							<s:submit value=" Reset" cssClass="reset" action="UploadArrears_reset" title="Clear the fields" /> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
 
	function callDownLoad() {
    	var fileds=['paraFrm_divisionName']; 
 		var labels=['division']; 
 		var flag=['select']; 
 	
 		if(!validateBlank(fileds,labels,flag)){
 		return false;
 		}
	}

	function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/arrears";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=50, left=100');
	}
	
 	function callUpload() { 
 	var fileds=['paraFrm_month','paraFrm_year','paraFrm_divisionName','paraFrm_uploadFileName']; //'paraFrm_uploadCreditName',
 	var labels=['paidMonth','paidYear','division','upload.Name']; //'credit.Name',
 	var flag=['select','enter','select','select']; //'select',
 	
 	if(!validateBlank(fileds,labels,flag)){
 		return false;
 	}
		return true;
	}
</script>