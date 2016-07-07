<!--Prakash Shetkar--><!--April 20, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="EmpDetailsUpload" action="EmpDetailsUpload" validate="true" id="paraFrm" theme="simple">
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
						<td width="25%">Official Details :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Official_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadOfficialDetails')" />
						</td>
						<s:if test="uploadName == 'Official'">
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
					<s:if test="status == 'Fail' && uploadName == 'Official'">					
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
						<td width="25%">Personal / Account Details  :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" 
							onclick="callDownload('PeoplePower_Employee_Personal_And_Account_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload"  onclick="callUpload('uploadPersonal')" />
						</td>
						<s:if test="uploadName == 'Personal'">
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
					<s:if test="status == 'Fail' && uploadName == 'Personal'">	
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
						<td width="25%">Address Details  :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Address.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadAddress')" />
						</td>
						<s:if test="uploadName == 'Address'">
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
					<s:if test="status == 'Fail' && uploadName == 'Address'">
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
						<td width="25%">Family Details :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Family_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadFamily')" />
						</td>
						<s:if test="uploadName == 'Family'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Family'">					
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
						<td width="25%">Experience :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Experience_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadExperienceDtl')" />
						</td>
						<s:if test="uploadName == 'Experience'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Experience'">					
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
						<td width="25%">Leave Details :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Leave_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadLeaveDtl')" />
						</td>
						<s:if test="uploadName == 'Leave'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Leave'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<!-- Updated By Anantha Lakshmi -->
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Increment History :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Increment_History.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadIncrementHistoryDetails')" />
						</td>
						<s:if test="uploadName == 'Increment'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Increment'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		
		<!-- Promotion History -->
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Promotion History :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Employee_Promotion_History.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadPromotionHistoryDetails')" />
						</td>
						<s:if test="uploadName == 'Promotion'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'Promotion'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<!-- Ended By Anantha Lakshmi -->
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Employee Credit Config. :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" 
							onclick="callDownload('PeoplePower_Employee_Credit_Configuration.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadEmpCredit')" />
						</td>
						<s:if test="uploadName == 'Credit'">
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
					<s:if test="status == 'Fail' && uploadName == 'Credit'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		
		<!-- ADDED BY REEBA FOR DEBIT UPLOAD -->
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Employee Debit Config. :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" 
							onclick="callDownload('PeoplePower_Employee_Debit_Configuration.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadEmpDebit')" />
						</td>
						<s:if test="uploadName == 'Debit'">
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
					<s:if test="status == 'Fail' && uploadName == 'Debit'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		<!-- ADDITION BY REEBA ENDS -->
		
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Employee Qualification :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" 
							onclick="callDownload('PeoplePower_Employee_Qualification.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadEmpQualification')" />
						</td>
						<s:if test="uploadName == 'Qualification'">
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
					<s:if test="status == 'Fail' && uploadName == 'Qualification'">					
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
						<td width="25%">House Rent Declaration :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_House_Rent_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadHouseRentDtl')" />
						</td>
						<s:if test="uploadName == 'HouseRent'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'HouseRent'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		
		<!-- Start of Posting Details Added by Nilesh --> 
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Posting Details :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_Posting_Details.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadPostDetails')" />
						</td>
						<s:if test="uploadName == 'PostingDetails'">
							<td width="10%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="10%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'PostingDetails'">					
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
		document.getElementById('paraFrm').action = 'EmpDetailsUpload_reset.action';
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
		document.getElementById('paraFrm').action = 'EmpDetailsUpload_downloadTemplate.action?templateName=' + templateName;
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
				document.getElementById('paraFrm').action = "EmpDetailsUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'EmpDetailsUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>