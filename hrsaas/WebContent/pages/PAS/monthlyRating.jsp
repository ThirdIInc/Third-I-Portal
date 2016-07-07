<!-- @author: REEBA_JOSEPH _ 22 OCTOBER 2010 -->
<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="MonthlyRating" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Monthly
					Performance Rating </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><s:submit cssClass="reset" action="MonthlyRating_reset" value=" Reset"/> 
					<s:submit cssClass="save" action="MonthlyRating_save" value=" Save"/></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
			<tr>
			<td width="100%">
				<table width="100%" class="formbg">
				<tr>
						<td width="15%" colspan="2"><strong>Upload Employee Rating</strong> </td>
						
						<td>
						</td>
					</tr>
				<tr>
						<td width="15%">Select File :<font color="red">*</font></td>
						<td width="33%">
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<input type="button" value="Select XLS File" class="token" onclick="uploadFile('uploadFileName')"/>
						</td>
					</tr>
					<tr>
						<td width="25%">Upload Rating :</td>
						<td width="10%">
							<input type="button" class="token" value=" Download" onclick="callDownload('Peoplepower_EmployeeRating.xls')" />
						</td>
						<td width="10%">
							<input type="button" class="token" value=" Upload" onclick="callUpload('uploadAssetMasterDetails')" />
						</td>
						
					</tr>
					<tr>
					<s:if test="uploadName == 'monthRating'">
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
					<s:if test="status == 'Fail' && uploadName == 'monthRating'">					
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
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
						<td width="15%" colspan="2"><strong>Define Employee Rating</strong> </td>
						
						<td>
						</td>
					</tr>
				<tr>
					<td width="20%"><label class="set" id="month" name="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%></label> <font
						color="red">*</font> :</td>
					<td width="30%"><s:select theme="simple" name="month"
						cssStyle="width:155"
						list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td></td>
					<td width="20%"><label class="set" id="year" name="year"
						ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font
						color="red">*</font> :</td>
					<td width="30%"><s:textfield name="year" size="25"
						maxlength="4" onkeypress="return numbersOnly();" />
				</tr>
			

				<tr>
					<td align="center" colspan="5"><input type="button"
						value="View Employees" class="token" title="View Employees"
						onclick="callViewEmployees()" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="viewEmp" /><s:hidden name="recordsAvailable" />
		<s:if test="viewEmp">
			<tr>
				<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
					border="0">
					<tr>
						<td colspan="2" class="txt"><strong class="text_head">Employee
						List</strong></td>
					</tr>
					<tr>
						<td width="100%" class="formbg" colspan="3">
						<table width="100%" class="sortableTD">
							<tr>
								<td width="10%" align="center" class="formth"><b><label
									name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b>
								</td>
								<td width="45%" align="center" class="formth"><b><label
									name="employee.id" id="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td width="40%" align="center" class="formth"><b><label
									name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
								</td>

								<td class="formth" align="center"><label name="rating"
									id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label></td>
							</tr>
							<s:if test="recordsAvailable">
								<%!int d = 0;%>
								<%
									int i = 0;
									int cn = 0;
								%>
								<s:iterator value="empList">
									<tr onmouseover="javascript: newRowColor(this);"
										onmouseout="javascript: oldRowColor(this);"
										ondblclick="javascript: callForEdit('<s:property value="typeID"/>');"
										style="cursor: hand;">
										<td title="Double click for edit" width="10%"
											class="sortableTD" align="center"><%=++cn%> <%
										 ++i;
										 %></td>
										<s:hidden  name="empId" />
										
										<td title="Double click for edit" width="20%"
											class="sortableTD"><s:property value="empToken" /> <input
											type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
										<td title="Double click for edit" width="50%"
											class="sortableTD"><s:property value="empName" /></td>

										<td class="sortableTD" width="50%"><s:textfield cssStyle="text-align: right"
											name="rating"  onkeypress="return numbersWithDot();"/></td>

									</tr>
								</s:iterator>
								<%
								d = i;
								%>
							</s:if>
						</table>
						<s:if test="recordsAvailable"></s:if> <s:else>
							<table width="100%">
								<tr>
									<td align="center"><font color="red">No Data To
									Display</font></td>
								</tr>
							</table>
						</s:else></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
<s:hidden name="dataPath" /><s:hidden name="status"/><s:hidden name="note"/>
	</table>
</s:form>

<script>
//setCurrentYear('paraFrm_year');

function callViewEmployees(){
	try{
		var month =document.getElementById("paraFrm_month").value;
		var year =document.getElementById("paraFrm_year").value;
		if(month=="0"){
			alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
			return false;
		}
		if(year==""){
			alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm').action="MonthlyRating_viewEmployees.action";	
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}
function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'MonthlyRating_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'MonthlyRating_viewUploadedFile.action';
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
				document.getElementById('paraFrm').action = "MonthlyRating_uploadEmployeeRating.action";
				document.getElementById('paraFrm').submit();
			}
		}
	}
</script>