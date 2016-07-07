<!--Bhushan Dasare--><!--Apr 27, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmployeewiseUpload" name="EmployeewiseUpload" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/><s:hidden name="dataExists" />
	
	<table width="100%" align="right" class="formbg">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Employeewise Upload</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
						<td align="right" nowrap="nowrap"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg" border="0"> 
					<tr>
						<td colspan="5" width="100%"><b>Download Template</b></td>
					</tr>
					<tr>
						<td colspan="5" width="100%"><b>Select any/all search criteria to download a template!</b></td>
					</tr>
					<tr>
						<td width="15%" nowrap="nowrap">Division :<font color="red">*</font></td>
						<td width="25%">
							<s:hidden name="divisionId" />
							
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'EmployeewiseUpload_f9Division.action');">
						</td>
						<td width="15%">Branch</td>
						<td width="25%">
							<s:hidden name="branchId" />
							
							<s:textfield name="branchName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'EmployeewiseUpload_f9Branch.action');">
						</td>
					</tr>
					<tr>
						<td width="15%">Department</td>
						<td width="25%">
							<s:hidden name="departmentId" />
							
							<s:textfield name="departmentName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the department" onclick="callsF9(500,325,'EmployeewiseUpload_f9Department.action');">
						</td>
						<td width="15%">Designation</td>
						<td width="25%">
							<s:hidden name="designationId" />
							
							<s:textfield name="designationName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the type of an employee" onclick="callsF9(500,325,'EmployeewiseUpload_f9Designation.action');">
						</td>
					</tr>
					<tr>
						<td width="15%">Employee Type</td>
						<td width="25%">
							<s:hidden name="employeeTypeId" />
							
							<s:textfield name="employeeTypeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the pay bill group" onclick="callsF9(500,325,'EmployeewiseUpload_f9EmployeeType.action');">
						</td>
						<td width="15%">Paybill Group</td>
						<td width="25%">
							<s:hidden name="payBillId" />
							
							<s:textfield name="payBillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="callsF9(500,325,'EmployeewiseUpload_f9PayBill.action');">
						</td>
					</tr>
					<tr>
					<s:hidden name="invChapter"></s:hidden><s:hidden name="invSection"></s:hidden>
					<s:hidden name="invCode"/><s:hidden name="perCode"/>
						<td width="15%">Download Template :<font color="red">*</font></td>
						<td width="25%">
							<s:select name="downloadType" headerKey="" headerValue="--Select--" 
							list="#{'PER' : 'Perquisites', 'INV' : 'Investments'}" onchange="callInvestment(this);" />
						</td>
						<td></td>
						<!-- ganesh start -->
						<td width="15%" id='perTD1'>Perquisites Name :<font color="red">*</font></td>
						<td width="25%" id='perTD2'>
							<s:textfield name="perName" size="40" readonly="true"  cssStyle="background-color: #F2F2F2;"/> </td>
							<td id='perTD3'><img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'EmployeewiseUpload_f9perqaction.action');">
						</td>
						<!-- ganesh end -->
						<td width="15%" id='invTD1'>Investment Name :<font color="red">*</font></td>
						<td width="25%" id='invTD2'>
							<s:textfield name="invName" size="40" readonly="true"  cssStyle="background-color: #F2F2F2;"/> </td>
							<td id='invTD3'><img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'EmployeewiseUpload_f9invaction.action');">
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="6" align="center">
							<input type="button" class="token" value="Download Template" onclick="getTemplateLinks();" />
						</td>
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
			<td width="100%" align="center">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2" width="100%"><b>Upload Data</b></td>
					</tr>
					<tr>
						<td width="15%">Type of Upload :<font color="red">*</font></td>
						<td>
							<s:select name="uploadType" headerKey="" headerValue="--Select--" 
							list="#{'PER' : 'Perquisites', 'INV' : 'Investments'}" />
						</td>
					</tr>
					<tr>
						<td width="15%">Select File :<font color="red">*</font></td>
						<td width="45%">
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')"/>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="3" align="center">
							<input type="button" class="token" value="Upload" onclick="callUpload();" />
						</td>
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
onload();
	function onload(){
		var downloadType = document.getElementById('paraFrm_downloadType').value;
		///alert(downloadType); 
		if(downloadType=="INV"){  
			document.getElementById('invTD1').style.display='';
			document.getElementById('invTD2').style.display='';
			document.getElementById('invTD3').style.display='';
			document.getElementById('perTD1').style.display='none';
			document.getElementById('perTD2').style.display='none';
			document.getElementById('perTD3').style.display='none';
		}
		if(downloadType=="PER"){
		 	document.getElementById('perTD1').style.display='';
			document.getElementById('perTD2').style.display='';
			document.getElementById('perTD3').style.display='';
			document.getElementById('invTD1').style.display='none';
			document.getElementById('invTD2').style.display='none';
			document.getElementById('invTD3').style.display='none';
		}
		if(downloadType==""){
		 	document.getElementById('perTD1').style.display='none';
			document.getElementById('perTD2').style.display='none';
			document.getElementById('perTD3').style.display='none';
			document.getElementById('invTD1').style.display='none';
			document.getElementById('invTD2').style.display='none';
			document.getElementById('invTD3').style.display='none';
		}
	}
			
	function getTemplateLinks() {
		try{
			var divisionId = document.getElementById('paraFrm_divisionId').value;
			var invName = document.getElementById('paraFrm_invName').value;
			var perName = document.getElementById('paraFrm_perName').value;
			var downloadType = document.getElementById('paraFrm_downloadType').value;
			
			if(divisionId == '') {
				alert('Please select a Division');
			} else if(invName == '' && downloadType=='INV') { 
				alert('Please select a Investment Name');
			} else if(perName == '' && downloadType=='PER') { 
				alert('Please select a Perquisites Name');
			}  
			else if(downloadType == '') {
				alert('Please select a template type to download');
				document.getElementById('paraFrm_uploadType').focus();
			} else {
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').action = 'EmployeewiseUpload_getTemplateLinks.action';
				document.getElementById('paraFrm').submit();
			}
		}catch(e){
		//// alert(e);
		}
	}
	
	function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'EmployeewiseUpload_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function downloadTemplate(rowstart) {
		document.getElementById("paraFrm").action = 'EmployeewiseUpload_downloadTemplate.action?rangeValue=' + eval(rowstart);
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById("paraFrm").submit();
 	}
 	
 	function callUpload() {
 		var uploadType = document.getElementById('paraFrm_uploadType').value;
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadType == '') {
			alert('Please select a Type of Upload');
			document.getElementById('paraFrm_uploadType').focus();
		} else if(uploadFileName == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "EmployeewiseUpload_uploadExcel.action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
	function callInvestment(obj){
		if(obj.value=="INV"){
			document.getElementById('invTD1').style.display='';
			document.getElementById('invTD2').style.display='';
			document.getElementById('invTD3').style.display='';
			document.getElementById('paraFrm_perCode').value="";
			document.getElementById('paraFrm_perName').value="";
			document.getElementById('perTD1').style.display='none';
			document.getElementById('perTD2').style.display='none';
			document.getElementById('perTD3').style.display='none';
		}else if(obj.value=="PER"){
			document.getElementById('perTD1').style.display='';
			document.getElementById('perTD2').style.display='';
			document.getElementById('perTD3').style.display='';
			document.getElementById('paraFrm_invCode').value="";
			document.getElementById('paraFrm_invName').value="";
			document.getElementById('invTD1').style.display='none';
			document.getElementById('invTD2').style.display='none';
			document.getElementById('invTD3').style.display='none';
		}else if(obj.value==""){
			document.getElementById('paraFrm_perCode').value="";
			document.getElementById('paraFrm_perName').value="";
			document.getElementById('paraFrm_invCode').value="";
			document.getElementById('paraFrm_invName').value="";
			document.getElementById('perTD1').style.display='none';
			document.getElementById('perTD2').style.display='none';
			document.getElementById('perTD3').style.display='none';
			document.getElementById('invTD1').style.display='none';
			document.getElementById('invTD2').style.display='none';
			document.getElementById('invTD3').style.display='none';
		}
	}
	
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'EmployeewiseUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>