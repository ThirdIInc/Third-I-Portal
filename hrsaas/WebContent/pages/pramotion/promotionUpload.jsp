<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PromotionUpload" validate="true" id="paraFrm" theme="simple">
<s:hidden name="dataPath" />
<s:hidden name="status"/>
<s:hidden name="uploadedFile"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	<tr>
		<td colspan="3" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt">
						<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
					</td>
					<td width="93%" class="txt"><strong class="text_head">Promotion Upload </strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="80%">
					<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					<input type="button" class="token"  onclick="downloadtemplateFun()" value="  Download Template  "/>
				</td>
				<td width="20%">
					<div align="right"><font color="red">*</font> Indicates Required</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4"><strong>Download Template Filters</strong></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="division1"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
					</td>
					<td width="25%"><s:hidden name="divisionId"/>
					<s:textfield theme="simple"	readonly="true" name="divisionName"  />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9division.action');">
					
					</td>
					<td width="20%"><label class="set" id="branch1"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="25%"><s:hidden name="branchId"/>
					<s:textfield name="branchName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9branch.action');">
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="department1"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="25%"><s:hidden name="departmentId"/>
					<s:textfield name="departmentName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9department.action');">
					</td>
					<td colspan="1" width="20%"><label id="designation"
								name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:hidden name="desgCode" /> 
					<s:textfield name="desgName" theme="simple" readonly="true"	maxlength="50"  /> 
					<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PromotionUpload_f9Rank.action');">
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="employee.type"
						name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
					<td width="25%"><s:hidden name="empTypeId"/>
					<s:textfield theme="simple" name="empTypeName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9EmpType.action');">
					</td>
					<td width="20%"><label class="set" id="grade1"
						name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td width="25%"><s:hidden name="gradeId"/>
					<s:textfield theme="simple" readonly="true"  name="gradeName" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9grade.action');">
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="paybill1"
						name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
					<td width="25%"><s:hidden name="paybillId"/>
					<s:textfield theme="simple" name="paybillName" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9paybill.action');">
					</td>
					<td width="20%"><label class="set" id="employee1"
						name="employee"><%=label.get("employee")%></label>:</td>
					<td width="25%"><s:hidden name="empId"/>
					<s:textarea name="empName" cols="30" rows="2" readonly="true" />
					<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'PromotionUpload_f9employee.action');">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<table width="100%" class="formbg" border="0">
			<tr>
				<td colspan="4"><strong>Upload Data</strong></td>
			</tr>
			<tr>
				<td width="20%"><label class="set" id="selectFile1"
						name="selectFile"><%=label.get("selectFile")%></label>:<font color="red">*</font></td>
				<td width="45%" colspan="2">
					<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
				</td>
				<td width="25%" >
					<input type="button" value="   Browse   " class="token" onclick="selectFile('uploadFileName')"/>&nbsp;
					<input type="button" class="token" 	value=" Upload File "
						onclick="callUpload()" />
				</td>
			</tr>
			<s:if test="displayNoteFlag">		
			<s:if test="status == 'Success'">					
					<tr>
						<td width="100%" colspan="4">
							<FONT color="green"><s:property value="status" /></FONT>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="4" align="center">
							<a href="#"
								onclick="viewUploadedFile();"><font color="blue"><u>Please
							click here to view uploaded file.</u></font></a>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="100%" colspan="4">
							<FONT color="red"><s:property value="status" /></FONT>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="4">
							<FONT color="red">Note : <s:property value="note"/></FONT>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="4" align="center">
							<a href="#"
								onclick="viewUploadedFile();"><font color="blue"><u>Please
							click here to view uploaded file.</u></font></a>
						</td>
					</tr>
				</s:else>
				</s:if>
				<s:else>
					<tr>
						<td width="100%" colspan="4">
							<strong><FONT color="red">Note : Upload only 50 records at a time.</FONT></strong>
						</td>
					</tr>
				</s:else>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" colspan="3">
					<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					<input type="button" class="token"  onclick="downloadtemplateFun()" value="  Download Template  "/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
<script>
	function resetFun(){
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'PromotionUpload_reset.action';
		document.getElementById('paraFrm').submit();
	}
	function downloadtemplateFun() {
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert("Please select division.");
			return false;
		}
	  	document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'PromotionUpload_downloadTemplate.action';
		document.getElementById('paraFrm').submit();
	}
	function selectFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	function callUpload() {
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert("Please select division.");
			return false;
		}
		if(document.getElementById('paraFrm_uploadFileName').value==""){
			alert("Please select file to upload.");
			return false;
		}
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "PromotionUpload_uploadPromotionDetails.action";
			document.getElementById('paraFrm').submit();
	}	
		
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'PromotionUpload_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>