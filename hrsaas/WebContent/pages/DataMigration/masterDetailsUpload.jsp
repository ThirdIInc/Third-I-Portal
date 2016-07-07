<!--Prakash Shetkar--><!--April 20, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="MasterDetailsUpload" action="MasterDetailsUpload" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/>

	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Masters Bulk Upload</strong></td>
						<td width="3%" valign="top" class="txt" align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="90%"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
						<td align="right" nowrap="nowrap"><font color="red">*</font>Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" align="center">
				<table width="100%" class="formbg">
					<tr>
						<td width="15%">Select File :<font color="red">*</font></td>
						<td width="33%"><s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						<td>
							<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Branch :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Branch_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadBranchMasterDtls')" />
						</td>
						<s:if test="uploadName == 'branch'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'branch'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Department :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Deptartment_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadDeptMasterDtls')" />
						</td>
						<s:if test="uploadName == 'department'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'department'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Designation :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Designation_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadDesgnMasterDtls')" />
						</td>
						<s:if test="uploadName == 'designation'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'designation'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Asset Category :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Asset_Category_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadAssetCategoryDtls')" />
						</td>
						<s:if test="uploadName == 'assetCategory'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'assetCategory'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Asset Sub Type :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Asset_SubType_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadAssetSubTypeDtls')" />
						</td>
						<s:if test="uploadName == 'assetSubType'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'assetSubType'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Holiday :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Holiday_Master.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadHolidayMaster')" />
						</td>
						<s:if test="uploadName == 'holiday'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'holiday'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Branchwise Holiday :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Branchwise_Holiday.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadBranchHoliday')" />
						</td>
						<s:if test="uploadName == 'branchHoliday'">
							<td width="10%"><s:property value="status" /></td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'branchHoliday'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function callUpload(method) {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file');
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "MasterDetailsUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}

	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'MasterDetailsUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}

	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'MasterDetailsUpload_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'MasterDetailsUpload_reset.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>