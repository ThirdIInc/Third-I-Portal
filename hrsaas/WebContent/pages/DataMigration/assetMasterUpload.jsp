<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form name="AssetMasterUpload" action="AssetMasterUpload" validate="true" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/>
	
	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Asset Master Upload </strong></td>
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
		<!-- 
		<tr>
			<td width="100%" align="center">
				<table width="100%" class="formbg">
					<tr>
						<td width="25%">Select File :<font color="red">*</font></td>
						<td width="50%" colspan="2">
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="25%">
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
						<td width="25%">Asset Master :</td>
						<td width="25%">
							<input type="button" class="token" value=" Download" onclick="callDownload('PeoplePower_AssetMaster.xls')" />
						</td>
						<td width="25%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadAssetMasterDetails','paraFrm_uploadFileName')" />
						</td>
						<s:if test="uploadName == 'AssetMaster'">
							<td width="25%">
								<s:property value="status"/>
							</td>
							<td>
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
						<s:else>
							<td width="25%"></td>
							<td></td>
						</s:else>
					</tr>
					<s:if test="status == 'Fail' && uploadName == 'AssetMaster'">					
						<tr>
							<td width="100%" colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		</tr>
		-->
		<tr>
			<td width="100%">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="20%">Step 1 : Select asset type</td>
					</tr>
					<tr>
						<td width="20%">Asset Category <font color="red">*</font>:</td>
						<td width="20%">
							<s:hidden name="assetCategoryCode"/>
							<s:textfield name="assetCategoryName" size="16" readonly="true"/>
							<img class="imageIcon"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="javascript:callsF9(500,325,'AssetMasterUpload_f9assetCategory.action'); ">
						</td>
						<td width="20%">Asset Sub Type <font color="red">*</font>: </td>
						<td width="20%">
							<s:hidden name="assetSubTypeCode"/>
							<s:textfield name="assetSubTypeName" size="16" readonly="true"/>
							<img class="imageIcon"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple"
							onclick="checkCategory();">
						</td>
						<!--<td width="20%" align="center"><input
							type="button" class="token" value=" Download "
							onclick="callDownload('PeoplePower_AssetMaster.xls')" /></td>
					--></tr>
					<!--<tr>
						<td width="25%">&nbsp;</td>
						<td width="50%" colspan="2" align="center"><input
							type="button" class="token" value=" Download "
							onclick="callDownload('PeoplePower_AssetMaster.xls')" /></td>
						<td width="25%">&nbsp;</td>
					</tr>
			--></table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="25%">Step 2 : Download the template</td>
					<td width="50%" colspan="2" align="center">
					<input type="button" class="token" value=" Download "
						onclick="callDownload('PeoplePower_AssetMaster.xls')" /></td>
						<td width="25%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="20%">Step 3 : Upload the data</td>
				</tr>
				
				<tr>
					<td width="20%">Select File <font color="red">*</font>:</td>
					<td width="20%">
						<s:textfield name="uploadFileName" size="55" readonly="true" cssStyle="background-color: #F2F2F2;" />
					</td>
					<td width="15%">
						<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')"/>
					</td>
					<td width="20%" align="left"><input type="button" class="token"
						value="   Upload    "
						onclick="callUpload('uploadAssetMasterDetails','paraFrm_uploadFileName')" />
					</td>
				</tr>
				<!--<tr>
					<td width="25%">&nbsp;</td>
					<td width="50%" colspan="2" align="center"><input type="button" class="token"
						value="   Upload    "
						onclick="callUpload('uploadAssetMasterDetails','paraFrm_uploadFileName')" />
					</td>
					<td width="25%">&nbsp;</td>
				</tr>-->
					<s:if test="uploadName == 'AssetMaster'">
					<tr>
						<td width="25%"><s:property value="status" /></td>
						<td width="50%" colspan="2" align="center"><a href="#"
							onclick="viewUploadedFile();"><font color="blue"><u>Please
						click here to view uploaded file.</u></font></a></td>
						<td width="25%">&nbsp;</td>
					</tr>
				</s:if>
				<s:if test="status == 'Fail' && uploadName == 'AssetMaster'">					
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
			<td width="100%"><strong class="text_head">Note :</strong></label>
			</td>
		</tr>
		<tr>
			<td width="100%"><label class="set" name="note" id="note"
						ondblclick="callShowDiv(this);"><%=label.get("note")%></label>
			</td>
		</tr>
		<tr>
			<td width="100%"><input type="button" value="Reset" class="reset" onclick="callReset();" /></td>
		</tr>
		
	</table>
</s:form>

<script type="text/javascript">
	function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'AssetMasterUpload_reset.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function callDownload_OLD(templateName) {
	  	document.getElementById('paraFrm').target = 'self';
		document.getElementById('paraFrm').action = 'AssetMasterUpload_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
	}
	 
	function callDownload(templateName) {
		if(document.getElementById('paraFrm_assetCategoryName').value==""){
				alert("Please select asset category");
				return false;
			} else if(document.getElementById('paraFrm_assetSubTypeName').value==""){
				alert("Please select asset sub type");
				return false;
			} else {
		  	document.getElementById('paraFrm').target = 'self';
			document.getElementById('paraFrm').action = 'AssetMasterUpload_downloadTemplate.action?templateName=' + templateName;
			document.getElementById('paraFrm').submit();
		}
	}
 
	
	function callUpload_OLD(method, fileName) {
		var uploadedFile = document.getElementById(fileName).value;
		
		if(uploadedFile == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadedFile.replace('.', '@').split('@')[1]).toUpperCase();
			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "AssetMasterUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
	 function callUpload(method, fileName) {
		var uploadedFile = document.getElementById(fileName).value;
		
		if(uploadedFile == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadedFile.replace('.', '@').split('@')[1]).toUpperCase();
			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
				return false;
			}else if(document.getElementById('paraFrm_assetCategoryName').value==""){
				alert("Please select asset category");
				return false;
			} else if(document.getElementById('paraFrm_assetSubTypeName').value==""){
				alert("Please select asset sub type");
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "AssetMasterUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	} 
	
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'AssetMasterUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function checkCategory(){
		if(document.getElementById('paraFrm_assetCategoryName').value==""){
			alert("Please select asset category");
			return false;
		}else{
			javascript:callsF9(500,325,'AssetMasterUpload_f9assetSubType.action');
		}
		 
	
	}
</script>