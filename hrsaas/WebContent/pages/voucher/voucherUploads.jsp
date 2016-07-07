<!--Anantha Lakshmi-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form name="VoucherUploadMaster" action="VoucherUploadAction" validate="true" id="paraFrm"  theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/>
	
	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Voucher Upload </strong></td>
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
						<td width="90%">
						<input type="button" class="token" value="Download Template"  onclick="callDownload('PeoplePower_VoucherMaster.xls')" />
						<input type="button"  class="token" value="Bank Statement" onclick="callDownloadStatement()" />
						<input type="button"  class="token" value="Delete" onclick="deleteVoucherData()" /> 
						<input type="button" value="Reset" class="reset" onclick="callReset();" /> 
						</td>
						<td align="right" nowrap="nowrap"><font color="red">*</font>Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- <tr>
		   <td width="100%">
			 	<table width="100%" class="formbg">
			 		<tr>
						<td width="25%" ><strong>Download Voucher Template: </strong></td>
						<td width="10%">
						 <input type="button" class="token" value="Download"  onclick="callDownload('PeoplePower_VoucherMaster.xls')" />	
						</td>
					</tr>
					
				</table>
			</td>
		 </tr>	-->
		<tr>
		   <td width="100%">
			 	<table width="100%" class="formbg">
			 		<tr>
						<td colspan='4'><strong>Upload Voucher Data:</strong></td>
					</tr>
					<tr>
						<td >Select File :<font color="red">*</font></td>
						<td>
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')"/>
						</td>
						<td >
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadVoucherMasterDetails')" />
						</td>
					</tr>
					<tr>	
						
						<s:if test="uploadName == 'VoucherMaster'">
							<td></td>
							<td >
								<s:property value="status"/>
							   &nbsp;&nbsp;
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</s:if>
					 </tr>
					 <s:if test="status == 'Fail' && uploadName == 'VoucherMaster'">					
						<tr>
							<td  colspan="6">
								<FONT color="red">Note : <s:property value="note"/></FONT>
							</td>
						</tr>
					</s:if>
				</table>
			</td>
		 </tr>
		 
		  <!-- Delete Voucher Data  
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
							<tr>
								<td width="35%"><strong>Delete Voucher Data</strong></td>
								<td></td>
							</tr>
							<tr>
								<td width="15%" nowrap="nowrap">Division :<font color="red">*</font></td>
								<td width="300%">
									<s:hidden name="divisionId1" />
									<s:textfield name="divisionName1" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
									<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
									width="18" title="Select the division" onclick="callsF9(500,325,'VoucherUploads_f9DeleteDivision.action');">
								</td>
							</tr>
							<tr>
								    <td width="20%" class="formtext"><label  class = "set" name="fromdate1" id="fromdate1" ondblclick="callShowDiv(this);"><%=label.get("fromdate1")%></label>:</td>
									<td  width="30%" nowrap="nowrap"><s:textfield name="frmDate1" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
										<s:a href="javascript:NewCal('paraFrm_frmDate1','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
										height="16" align="absmiddle" width="16"> </s:a>
									</td>
									
									<td width="50%" class="formtext">
										<label class="set" name="todate1" id="todate1" ondblclick="callShowDiv(this);"><%=label.get("todate1")%></label>:
									</td>
									<td width="30%" nowrap="nowrap"><s:textfield name="toDate1" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
										<s:a href="javascript:NewCal('paraFrm_toDate1','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
										height="16" align="absmiddle" width="16"> </s:a>
									</td>
							</tr>
							<tr><td></td>
							    <td width="10%" align="center" >
									<input type="button"  class="token" value="Delete" onclick="deleteVoucherData()" />
								</td>
							</tr>
					</table>
				
			</td>
		</tr><!-- End of Delete Voucher Data -->
			
		 <tr>
				<td width="100%">
					<table width="100%" class="formbg">
							<tr>
								<td width="35%" colspan='4'><strong>Select Filters</strong></td>
								<td></td>
							</tr>
							<tr>
								<td width="25%">Division :<font color="red">*</font></td>
								<td width="75%" colspan='3'>
									<s:hidden name="divisionId" />
									<s:textfield name="divisionName" readonly="true" size="25" cssStyle="background-color: #F2F2F2;" />
									<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
									width="18" title="Select the division" onclick="callsF9(500,325,'VoucherUploads_f9Division.action');">
								</td>
							</tr>
							<tr>
								    <td width="25%" class="formtext"><label  class = "set" name="fromdate" id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label> :</td>
									<td  width="25%" nowrap="nowrap"><s:textfield name="frmDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
										<s:a href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
										height="16" align="absmiddle" width="16"> </s:a>
									</td>
									
									<td width="15%" class="formtext">
										<label class="set" name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label> :
									</td>
									<td width="25%" nowrap="nowrap"><s:textfield name="toDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
										<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
										height="16" align="absmiddle" width="16"> </s:a>
									</td>
							</tr>
							<tr><td></td>
							    <td width="10%" align="center" >
									
								</td>
							</tr>
					</table>
					<s:hidden name="today1" />
				</td>	
			
		</tr>
		<tr>
			<td width="100%">
						<input type="button" class="token" value="Download Template"  onclick="callDownload('PeoplePower_VoucherMaster.xls')" />
						<input type="button"  class="token" value="Bank Statement" onclick="callDownloadStatement()" />
						<input type="button"  class="token" value="Delete" onclick="deleteVoucherData()" /> 
						<input type="button" value="Reset" class="reset" onclick="callReset();" /> 
</td>
		</tr>
		
	</table>
</s:form>

<script type="text/javascript">
	function callReset() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'VoucherUploads_reset.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/voucher/voucherUploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'VoucherUploads_downloadTemplate.action?templateName=' + templateName;
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
				document.getElementById('paraFrm').action = "VoucherUploads_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
	function callDownloadStatement(){
		
		var divisionName = document.getElementById('paraFrm_divisionName').value;
		var frmDate = document.getElementById('paraFrm_frmDate').value;
		var toDate = document.getElementById('paraFrm_toDate').value;
		
		if(divisionName==''){
			alert("Please select Division");
		}
		else if((frmDate!="") && (toDate!="")) {
		  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate','fromdate', 'todate')){
				return false;
			}
			else{
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').action = 'VoucherUploads_vouhcerUploadStmtData.action';
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = 'main';
		    }	
		}
		else{
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'VoucherUploads_vouhcerUploadStmtData.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
		}
	}
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'VoucherUploads_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	function deleteVoucherData() {
		var divisionName = document.getElementById('paraFrm_divisionName').value;
		var frmDate = document.getElementById('paraFrm_frmDate').value;
		var toDate = document.getElementById('paraFrm_toDate').value;
		
		if(divisionName==''){
			alert("Please select Division");
		}
		
		
		else if((frmDate!="") && (toDate!="")) {
		  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate','fromdate', 'todate')){
				return false;
			}
			else{
				var conf=confirm("Are you sure to delete vouchers for selected filters?");
				if(conf){
					document.getElementById('paraFrm').target = '_self';
					document.getElementById('paraFrm').action = 'VoucherUploads_deleteVoucherUploadData.action';
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = 'main';
				
				} else {
				
				return false;
				
				}
				
		    }	
		}
		else{
		var conf=confirm("Are you sure to delete vouchers for selected filters?");
				if(conf){
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'VoucherUploads_deleteVoucherUploadData.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
			}
			else {
				
				return false;
				
				}
		}
		
	}
</script>