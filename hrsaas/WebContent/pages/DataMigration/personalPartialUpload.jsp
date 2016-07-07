<!--Prakash Shetkar--><!--Apr 29, 2010-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PesonalPartialUpload" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Partial Personal/Account Details</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div><s:hidden name="dataPath"></s:hidden>
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
					<td colspan="4" width="20%" class="formtext" height="22"><strong>Select Filters</strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>  <font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="divName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9divAction.action');"><s:hidden name='divCode'/></td>
						
					<td colspan="1" width="20%" class="formtext" height="22"><label name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="branchName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9branchAction.action');"><s:hidden name='branchCode'/></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="deptName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9deptAction.action');"><s:hidden name='deptCode'/></td>
						
					<td colspan="1" width="20%" class="formtext" height="22"><label name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="desgName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9desgAction.action');"><s:hidden name='desgCode'/></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="employee.type" id="employee.type"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="empType" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9empTypeAction.action');"><s:hidden name='empTypeCode'/></td>
						
					<td colspan="1" width="20%" class="formtext" height="22"><label name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"	name="gradeName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PesonalPartialUpload_f9gradeAction.action');"><s:hidden name='gradeCode'/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="formbg">
				<tr>
					<td colspan="6" width="20%" class="formtext" height="22"><strong>Select Columns</strong></td>
				</tr>
				<tr>
                	<td width="140"><label name="nationality" id="nationality" ondblclick="callShowDiv(this);"><%=label.get("nationality")%></label></td>
                	<td width="49"><s:checkbox name="nationalityChk" id='nationalityChk' /></td>
                	<td width="140"><label name="blood.group" id="blood.group" ondblclick="callShowDiv(this);"><%=label.get("blood.group")%></label></td>
                	<td width="50"><s:checkbox name="bloodGroupChk" id='bloodGroupChk' /></td>
	                <td width="140"><label name="religion" id="religion" ondblclick="callShowDiv(this);"><%=label.get("religion")%></label></td>
	                <td width="59"><s:checkbox name="religionChk" id='religionChk'  /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="caste.cat" id="caste.cat" ondblclick="callShowDiv(this);"><%=label.get("caste.cat")%></label></td>
                	<td width="49"><s:checkbox name="casteCatChk" id='casteCatChk'/></td>
                	<td width="140"><label name="caste" id="caste" ondblclick="callShowDiv(this);"><%=label.get("caste")%></label></td>
                	<td width="50"><s:checkbox name="casteChk" id='casteChk' /></td>
	                <td width="140"><label name="subCaste " id="subCaste" ondblclick="callShowDiv(this);"><%=label.get("subCaste")%></label></td>
	                <td width="59"><s:checkbox name="subCasteChk" id='subCasteChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="weight" id="weight" ondblclick="callShowDiv(this);"><%=label.get("weight")%></label></td>
                	<td width="49"><s:checkbox name="weightChk" id='weightChk'/></td>
                	<td width="140"><label name="height" id="height" ondblclick="callShowDiv(this);"><%=label.get("height")%></label></td>
                	<td width="50"><s:checkbox name="heightChk" id='heightChk'/></td>
	                <td width="140"><label name="isHandicap" id="isHandicap" ondblclick="callShowDiv(this);"><%=label.get("isHandicap")%></label></td>
	                <td width="59"><s:checkbox name="isHandicapChk" id='isHandicapChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="handicapDesc" id="handicapDesc" ondblclick="callShowDiv(this);"><%=label.get("handicapDesc")%></label></td>
                	<td width="49"><s:checkbox name="handicapDescChk" id='handicapDescChk' /></td>
                	<td width="140"><label name="maritalStatus" id="maritalStatus" ondblclick="callShowDiv(this);"><%=label.get("maritalStatus")%></label></td>
                	<td width="50"><s:checkbox name="maritalStatusChk" id='maritalStatusChk' /></td>
	                <td width="140"><label name="marriageDate" id="marriageDate" ondblclick="callShowDiv(this);"><%=label.get("marriageDate")%></label></td>
	                <td width="59"><s:checkbox name="marriageDateChk" id='marriageDateChk' /></td>
              	</tr>
           		<tr>
                	<td width="140"><label name="hobbies" id="hobbies" ondblclick="callShowDiv(this);"><%=label.get("hobbies")%></label></td>
                	<td width="49"><s:checkbox name="hobbiesChk" id='hobbiesChk' /></td>
                	<td width="140"><label name="idMark" id="idMark" ondblclick="callShowDiv(this);"><%=label.get("idMark")%></label></td>
                	<td width="50"><s:checkbox name="idMarkChk" id='idMarkChk' /></td>
	                <td width="140"><label name="passportNo" id="passportNo" ondblclick="callShowDiv(this);"><%=label.get("passportNo")%></label></td>
	                <td width="59"><s:checkbox name="passportNoChk" id='passportNoChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="passExpDate" id="passExpDate" ondblclick="callShowDiv(this);"><%=label.get("passExpDate")%></label></td>
                	<td width="49"><s:checkbox name="passExpDateChk" id='passExpDateChk' /></td>
                	<td width="140"><label name="homeTown" id="homeTown" ondblclick="callShowDiv(this);"><%=label.get("homeTown")%></label></td>
                	<td width="50"><s:checkbox name="homeTownChk" id='homeTownChk' /></td>
	                <td width="140">
	                <td width="59">
              	</tr>
              	<tr>
                	<td width="140"><label name="epfAppl" id="epfAppl" ondblclick="callShowDiv(this);"><%=label.get("epfAppl")%></label></td>
                	<td width="49"><s:checkbox name="epfApplChk" id='epfApplChk' /></td>
                	<td width="140"><label name="gpfAppl" id="gpfAppl" ondblclick="callShowDiv(this);"><%=label.get("gpfAppl")%></label></td>
                	<td width="50"><s:checkbox name="gpfApplChk" id='gpfApplChk' /></td>
	                <td width="140"><label name="vpfAppl" id="vpfAppl" ondblclick="callShowDiv(this);"><%=label.get("vpfAppl")%></label></td>
	                <td width="59"><s:checkbox name="vpfApplChk" id='vpfApplChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="pfTrustAppl" id="pfTrustAppl" ondblclick="callShowDiv(this);"><%=label.get("pfTrustAppl")%></label></td>
                	<td width="49"><s:checkbox name="pfTrustApplChk" id='pfTrustApplChk' /></td>
                	<td width="140"><label name="pfNo" id="pfNo" ondblclick="callShowDiv(this);"><%=label.get("pfNo")%></label></td>
                	<td width="50"><s:checkbox name="pfNoChk" id='pfNoChk'/></td>
	                <td width="140"><label name="panNo" id="panNo" ondblclick="callShowDiv(this);"><%=label.get("panNo")%></label></td>
	                <td width="59"><s:checkbox name="panNoChk" id='panNoChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="esicNo" id="esicNo" ondblclick="callShowDiv(this);"><%=label.get("esicNo")%></label></td>
                	<td width="49"><s:checkbox name="esicNoChk" id='esicNoChk' /></td>
                	<td width="140"><label name="gratuityNo" id="gratuityNo" ondblclick="callShowDiv(this);"><%=label.get("gratuityNo")%></label></td>
                	<td width="50"><s:checkbox name="gratuityNoChk" id='gratuityNoChk' /></td>
	                <td width="140"><label name="salACNo" id="salACNo" ondblclick="callShowDiv(this);"><%=label.get("salACNo")%></label></td>
	                <td width="59"><s:checkbox name="salACNoChk" id='salACNoChk' /></td>
              	</tr>
           		<tr>
                	<td width="140"><label name="salBank" id="salBank" ondblclick="callShowDiv(this);"><%=label.get("salBank")%></label></td>
                	<td width="49"><s:checkbox name="salBankChk" id='salBankChk' /></td>
                	<td width="140"><label name="reimbAcNo" id="reimbAcNo" ondblclick="callShowDiv(this);"><%=label.get("reimbAcNo")%></label></td>
                	<td width="50"><s:checkbox name="reimbAcNoChk" id='reimbAcNoChk' /></td>
	                <td width="140"><label name="reimbBank" id="reimbBank" ondblclick="callShowDiv(this);"><%=label.get("reimbBank")%></label></td>
	                <td width="59"><s:checkbox name="reimbBankChk" id='reimbBankChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="pensionACNo" id="pensionACNo" ondblclick="callShowDiv(this);"><%=label.get("pensionACNo")%></label></td>
                	<td width="49"><s:checkbox name="pensionACNoChk" id='pensionACNoChk' /></td>
                	<td width="140"><label name="pensionBank" id="pensionBank" ondblclick="callShowDiv(this);"><%=label.get("pensionBank")%></label></td>
                	<td width="50"><s:checkbox name="pensionBankChk" id='pensionBankChk' /></td>
	                <td width="140"><label name="pensionable" id="pensionable" ondblclick="callShowDiv(this);"><%=label.get("pensionable")%></label></td>
	                <td width="59"><s:checkbox name="pensionableChk" id='pensionableChk' /></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="payMode" id="payMode" ondblclick="callShowDiv(this);"><%=label.get("payMode")%></label></td>
                	<td width="49"><s:checkbox name="payModeChk" id='payModeChk' /></td>
                	<td width="140"><label name="accountCatg" id="accountCatg" ondblclick="callShowDiv(this);"><%=label.get("accountCatg")%></label></td>
                	<td width="50"><s:checkbox name="accountCatgChk" id='accountCatgChk'/></td>
	                <td width="140"><label name="costCenter" id="costCenter" ondblclick="callShowDiv(this);"><%=label.get("costCenter")%></label></td>
	                <td width="59"><s:checkbox name="costCenterChk" id='costCenterChk'/></td>
              	</tr>
              	<tr>
                	<td width="140"><label name="subCostCenter" id="subCostCenter" ondblclick="callShowDiv(this);"><%=label.get("subCostCenter")%></label></td>
                	<td width="49"><s:checkbox name="subCostCenterChk" id='subCostCenterChk' /></td>
                	<td width="140"><label name="custRefNo" id="custRefNo" ondblclick="callShowDiv(this);"><%=label.get("custRefNo")%></label></td>
                	<td width="49"><s:checkbox name="custReferenceNo" id='custReferenceNo' /></td>
	                <td width="140">
	                <td width="59">
              	</tr>
              	<tr>
					<td width="100%" colspan="6" align="center"></td>
				</tr>
				 <tr>
					<td width="100%" colspan="6" align="center"><input type="button" class="token"
						onclick="return callDownload();" value="Download Template"  />
				</tr>
			</table>
		</td>
	</tr>
	<s:if test="dataExists">
			<tr>
				<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%"><b>Download Template</b></td>
						</tr>
						<%	try {
								int i = 0, totalRecords = 0, recPerPage = 0;
								totalRecords = (Integer) request.getAttribute("totalRecords");
								recPerPage = (Integer) request.getAttribute("recPerPage");
								int total = totalRecords;
								int loopCount = 0, pageCount = recPerPage, extraRecords = 0;

								if (totalRecords != 0 ) {
									if (total > pageCount) {
										loopCount = total / pageCount;
										extraRecords = total % pageCount;
									}

									if (loopCount > 0) {
										for (i = 0; i < loopCount; i++) {
						%>					<tr>
												<td nowrap="nowrap">	
													<a href="#" onclick="downloadTemplate('<%=i%>');">
														<font color="blue">
															<u>Click here to download template for <%=(i * pageCount) + 1%> to 
																<%=(i * pageCount) + pageCount%></u>
														</font>
													</a>
												</td>
											</tr>
						<%				}

										if (extraRecords > 0) {
						%>					<tr>
												<td nowrap="nowrap">
													<a href="#" onclick="downloadTemplate('<%=i%>');">
														<font color="blue">
															<u>Click here to download template for <%=(i * pageCount) + 1%> to <%=total%></u>
														</font>
													</a>
												</td>
											</tr>
						<% 				}
									} else {
						%>				<tr>
											<td nowrap="nowrap">
												<a href="#" onclick="downloadTemplate('<%=i%>');">
													<font color="blue">
														<u>Click here to download template for <%=(i * pageCount) + 1%> to <%=total%></u>
													</font>
												</a>
											</td>
										</tr>
						<% 			}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						%>
					</table>
				</td>
			</tr>
		</s:if>
	<tr>
		<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"	class="formbg">
				<tr>
					<td colspan="6" width="20%" class="formtext" height="22"><strong>Upload File</strong></td>
				</tr>
				<tr>
					<td width="20%">Select File :<font color="red">*</font></td>
					<td width="33%">
						<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
					</td>
					<td>
						<input type="button" value="Select XLS File" class="token"  onclick="uploadFile('uploadFileName')"/>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="6" align="center"><input type='button' class="token" onclick="return callUpload();"
						theme="simple" value="Upload" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<s:if test="fileUploaded">
			<tr>
				<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="15%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</tr>
						<s:if test="status == 'Fail'">					
							<tr>
								<td width="100%" colspan="6">
									<FONT color="red">Note : <s:property value="note"/></FONT>
								</td>
							</tr>
						</s:if>
					</table>
				</td>
			</tr>
	</s:if>
	<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="100%"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
function callDownload(){
	if(document.getElementById('paraFrm_divName').value==''){
		alert('Please select '+document.getElementById('division').innerHTML);
		document.getElementById('paraFrm_divName').focus();
		return false;
	}else{
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'PesonalPartialUpload_getURL.action';
		document.getElementById('paraFrm').submit();
	}
}
function downloadTemplate(rowstart) {
		document.getElementById("paraFrm").action = 'PesonalPartialUpload_downloadTemplate.action?rangeValue=' + eval(rowstart);
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById("paraFrm").submit();	
}
function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}
function callUpload() {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		if(uploadFileName == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "PesonalPartialUpload_uploadTemplate.action";
				document.getElementById('paraFrm').submit();
			}
		}
}
function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'PesonalPartialUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
}
function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'PesonalPartialUpload_reset.action';
		document.getElementById('paraFrm').submit();
}	
</script>


