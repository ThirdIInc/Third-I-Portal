<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="OfficalPartialUpload" name="" id="paraFrm"
	validate="true" target="main" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Official
					Details</strong></td>
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
					<td width="78%" colspan="1"><input type="button" value="Reset"
						class="reset" onclick="callReset();" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					<s:hidden name="dataPath"></s:hidden></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td colspan="4" width="20%" class="formtext" height="22"><strong><label
						name="filters" id="filters" ondblclick="callShowDiv(this);"><%=label.get("filters")%></label></strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					<font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="divName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9divAction.action');"><s:hidden
						name='divCode' /></td>

					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="branchName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9branchAction.action');"><s:hidden
						name='branchCode' /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="deptName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9deptAction.action');"><s:hidden
						name='deptCode' /></td>

					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="desgName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9desgAction.action');"><s:hidden
						name='desgCode' /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="empType" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9empTypeAction.action');"><s:hidden
						name='empTypeCode' /></td>

					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						name="gradeName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'OfficalPartialUpload_f9gradeAction.action');"><s:hidden
						name='gradeCode' /></td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="6" width="20%" class="formtext" height="22"><strong><label
						name="columnDef" id="columnDef" ondblclick="callShowDiv(this);"><%=label.get("columnDef")%></label></strong></td>
				</tr>
				<tr>
					<td width="140"><label name="lable.firstName"
						id="lable.firstName" ondblclick="callShowDiv(this);"><%=label.get("lable.firstName")%></label></td>
					<td width="49"><s:checkbox name="firstName" id="firstName" /></td>
					<td width="140"><label name="lable.middleName"
						id="lable.middleName" ondblclick="callShowDiv(this);"><%=label.get("lable.middleName")%></label></td>
					<td width="50"><s:checkbox name="middleName" id='middleName' /></td>
					<td width="140"><label name="lable.lastName"
						id="lable.lastName" ondblclick="callShowDiv(this);"><%=label.get("lable.lastName")%></label></td>
					<td width="59"><s:checkbox name="lastName" id='lastName' /></td>
				</tr>
				<tr>
					<td><label name="lable.division" id="lable.division"
						ondblclick="callShowDiv(this);"><%=label.get("lable.division")%></label>
					:</td>
					<td><label> <s:checkbox name="division" id="division" />
					</label></td>
					<td><label name="lable.branch" id="lable.branch"
						ondblclick="callShowDiv(this);"><%=label.get("lable.branch")%></label>
					:</td>
					<td><label> <s:checkbox name="branch" /> </label></td>
					<td><label name="lable.department" id="lable.department"
						ondblclick="callShowDiv(this);"><%=label.get("lable.department")%></label>
					:</td>
					<td><label> <s:checkbox name="department"
						id="department" /> </label></td>
				</tr>
				<tr>
					<td><label name="lable.designation" id="lable.designation"
						ondblclick="callShowDiv(this);"><%=label.get("lable.designation")%></label>
					:</td>
					<td><label> <s:checkbox name="designation"
						id="designation" /> </label></td>
					<td><label name="lable.employeeTitle" id="lable.employeeTitle"
						ondblclick="callShowDiv(this);"><%=label.get("lable.employeeTitle")%></label>
					:</td>
					<td><label> <s:checkbox name="employeeTitle"
						id="employeeTitle" /> </label></td>
					<td><label name="lable.employeeType" id="lable.employeeType"
						ondblclick="callShowDiv(this);"><%=label.get("lable.employeeType")%></label>
					:</td>
					<td><label> <s:checkbox name="employeeType"
						id="employeeType" /> </label></td>
				</tr>
				<tr>
					<!--<td><label name="lable.reportingTo" id="lable.reportingTo"
						ondblclick="callShowDiv(this);"><%=label.get("lable.reportingTo")%></label>
					:</td>
					<td><label> <s:checkbox name="reportingTo"
						id="reportingTo" /> </label></td>
					--><td><label name="lable.shift" id="lable.shift"
						ondblclick="callShowDiv(this);"><%=label.get("lable.shift")%></label>
					:</td>
					<td><label> <s:checkbox name="shift" id="shift" /> </label></td>
					<td><label name="lable.paybill" id="lable.paybill"
						ondblclick="callShowDiv(this);"><%=label.get("lable.paybill")%></label>
					:</td>
					<td><label> <s:checkbox name="paybill" id="paybill" />
					</label></td>
					<td><label name="lable.gender" id="lable.gender"
						ondblclick="callShowDiv(this);"><%=label.get("lable.gender")%></label>
					:</td>
					<td><label> <s:checkbox name="gender" /> </label></td>
				</tr>
				<tr>
					<td><label name="lable.birthDate" id="lable.birthDate"
						ondblclick="callShowDiv(this);"><%=label.get("lable.birthDate")%></label>
					:</td>
					<td><label> <s:checkbox name="birthDate"
						id="birthDate" /> </label></td>
					<td><label name="lable.grade" id="lable.grade"
						ondblclick="callShowDiv(this);"><%=label.get("lable.grade")%></label>
					:</td>
					<td><label> <s:checkbox name="grade" id="grade" /> </label></td>
					<td><label name="lable.joiningDate" id="lable.joiningDate"
						ondblclick="callShowDiv(this);"><%=label.get("lable.joiningDate")%></label>
					:</td>
					<td><label> <s:checkbox name="joiningDate"
						id="joiningDate" /> </label></td>
				</tr>
				<tr>
					
					<td><label name="lable.leaving" id="lable.leaving"
						ondblclick="callShowDiv(this);"><%=label.get("lable.leaving")%></label>:
					</td>
					<td><s:checkbox name="leaving" id="leaving" /></td>
					<td><label name="lable.status" id="lable.status"
						ondblclick="callShowDiv(this);"><%=label.get("lable.status")%></label>
					:</td>
					<td><s:checkbox name="status" id="status" /></td>
					
					<td><label name="lable.groupjoindate" id="lable.groupjoindate"
						ondblclick="callShowDiv(this);"><%=label.get("lable.groupjoindate")%></label>
					:</td>
					<td><s:checkbox name="groupJoinDateCheck" id="groupJoinDateCheck" /></td>
				</tr>
				
				<tr>
					
					<td><label name="lable.dateofconfirm" id="lable.dateofconfirm"
						ondblclick="callShowDiv(this);"><%=label.get("lable.dateofconfirm")%></label>:
					</td>
					<td><s:checkbox name="dateofconfirm" id="dateofconfirm" /></td>
					<td><label name="lable.reportingto" id="lable.reportingto"
						ondblclick="callShowDiv(this);"><%=label.get("lable.reportingto")%></label>
					:</td>
					<td><s:checkbox name="reportingTo" id="reportingTo" /></td>
					<td><label name="label.trade" id="label.trade"
						ondblclick="callShowDiv(this);"><%=label.get("label.trade")%></label>:</td>
					<td><s:checkbox name="trade" id="trade" /></td>							
				</tr>
				
				<tr>					
					<td><label name="label.role" id="label.role"
						ondblclick="callShowDiv(this);"><%=label.get("label.role")%></label>:</td>
					<td><s:checkbox name="role" id="role" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>							
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
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="6" width="20%" class="formtext" height="22"><strong>Upload
					File</strong></td>
				</tr>
				<tr>
					<td width="20%">Select File :<font color="red">*</font></td>
					<td width="33%"><s:textfield name="uploadFileName" size="70"
						readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
					<td><input type="button" value="Select XLS File" class="token"
						onclick="uploadFile('uploadFileName')" /></td>
				</tr>
				<tr>
					<td width="100%" colspan="6" align="center"><input
						type='button' class="token" onclick="return callUpload();"
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
						<td><a href="#" onclick="viewUploadedFile();"><font
							color="blue"><u>Please click here to view uploaded
						file.</u></font></a></td>
					</tr>
					<s:if test="status == 'Fail'">
						<tr>
							<td width="100%" colspan="6"><FONT color="red">Note :
							<s:property value="note" /></FONT></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" value="Reset"
						class="reset" onclick="callReset();" /></td>
					<td width="22%"></td>
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
		document.getElementById('paraFrm').action = 'OfficalPartialUpload_getURL.action';
		document.getElementById('paraFrm').submit();
	}
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
				document.getElementById('paraFrm').action = "OfficalPartialUpload_uploadTemplate.action";
				document.getElementById('paraFrm').submit();
			}
		}
}

function downloadTemplate(rowstart) {
		document.getElementById("paraFrm").action = 'OfficalPartialUpload_downloadTemplate.action?rangeValue=' + eval(rowstart);
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById("paraFrm").submit();	
}

function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'OfficalPartialUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
}

function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'OfficalPartialUpload_reset.action';
		document.getElementById('paraFrm').submit();
}
</script>