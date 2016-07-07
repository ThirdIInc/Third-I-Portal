<!--Anantha lakshmi-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="CandidateDetailsUpload" action="CandidateDetailsUpload" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/>
	
	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Bulk Upload </strong></td>
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
						<td width="33%">
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Candidate Details :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Recruitment_Candidate_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadCandidateDetails')" />
						</td>
						<s:if test="uploadName == 'Candidate'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="20%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Candidate'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
	
		<!-- Start of Posting Details	 --> 
		
		<tr>
			<td width="100%"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
		</tr>
	</table>
	
</s:form>

<script type="text/javascript">
	function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'CandidateDetailsUpload_reset.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'CandidateDetailsUpload_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function callUpload(method) {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "CandidateDetailsUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'CandidateDetailsUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>