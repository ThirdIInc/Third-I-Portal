<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="EmailPayrollReport" validate="true" id="paraFrm" theme="simple">

<%
String attachment="";
String backAction="";
String fileName="";
try{
	 attachment = String.valueOf(request.getAttribute("reportPath")); 
	 backAction = String.valueOf(request.getAttribute("action")); 
	 fileName = String.valueOf(request.getAttribute("fileName")); 
}catch(Exception e){
	
}
%>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
<s:hidden name="attachmentPath" value="<%=attachment%>"/>
<s:hidden name="actionBack" value="<%=backAction%>"/>
<s:hidden name="fileName" value="<%=fileName%>"/>
<s:hidden name="htmlcode"/>
<s:hidden name="sentFlag"/>
	<tr>
		<td colspan="4" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt">
						<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
					</td>
					<td width="93%" class="txt"><strong class="text_head">Email Report</strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr height="25" valign="middle">
				<td width="80%">
					<!--  <input type="button" class="token"  onclick="sendMail()" value=" Send Mail "/>-->
					<s:if test="sentFlag=='true'"></s:if><s:else>
					<a href="#" id='mailHref' onclick="sendMail()"><img src="../pages/images/buttonIcons/email.png" class="iconImage"  align="absmiddle" 
						" title="PDF">&nbsp;Send Mail&nbsp;</a></s:else>
					<a href="#" onclick="backFun()"><img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" 
						" title="PDF">&nbsp;Back</a>
					
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
					<td colspan="4">
					<strong class="text_head">Mail this Report </strong>
					</td>
				</tr>
				<tr>
					<td>To</td>
					<td width="4"><font color="red">*</font></td>
					<td width="4">:</td>
					<td width="80%"><s:textarea name="mailToEmployeeEmail" cssStyle="overflow:hidden"	cols="125" rows="1"
						onkeyup="javascript:callDropdown('paraFrm_mailToEmployeeEmail',200,250,'EmailPayrollReport_f9employeeToMail.action',event,'false','multiple')" />

					</td>

				</tr>

				<tr>
					<td>CC</td>
					<td></td>
					<td>:</td>
					<td><s:textarea name="ccEmailIds" cssStyle="overflow:hidden" cols="125"
						rows="1" onkeyup="javascript:callDropdown('paraFrm_ccEmailIds',200,250,'EmailPayrollReport_f9employeeCCMail.action',event,'false','multiple')" /></td>
				</tr>
				<tr>
					<td>Subject </td>
					<td><font color="red">*</font></td>
					<td>:</td>
					<td><s:textarea name="subject" cssStyle="overflow:hidden" cols="125" rows="1" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td nowrap="nowrap">
					<s:if test="sentFlag=='true'"></s:if><s:else>
					<img
				src="../pages/images/buttonIcons/Attachment.png" class="iconImage"
				align="absmiddle" " title="" style="cursor: inherit;padding-right: 5px;"/><u><%=fileName%></u>
				</s:else>
				</td>
				</tr>
				<tr>
					<td valign="top">Message </td>
					<td valign="top"><font color="red">*</font></td>
					<td valign="top">:</td>
					<td nowrap="nowrap" valign="top"></td>
				</tr>
			<tr>
					<td valign="top" colspan="4"><textarea cssStyle="overflow:hidden" name="description" cols="125" rows="4" ></textarea></td>
				</tr>
				
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr height="25" valign="middle">
				<td width="100%">
				<s:if test="sentFlag=='true'"></s:if><s:else>
					<a href="#" onclick="sendMail()"><img src="../pages/images/buttonIcons/email.png" class="iconImage"  align="absmiddle" 
						" title="PDF">&nbsp;Send Mail&nbsp;</a></s:else>
					<a href="#" onclick="backFun()"><img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" 
						" title="PDF">&nbsp;Back</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
	
			<script type="text/javascript">
			//<![CDATA[
CKEDITOR.replace( 'description');
				

			//]]>
			</script>
<script>



function backFun(){
	var calledAction = document.getElementById("paraFrm_actionBack").value;
	//alert(calledAction);
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="<%=request.getContextPath()%>"+calledAction;
		document.getElementById('paraFrm').submit();
}
 

function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
}

function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
}

function sendMail(){
	if(!validateMail()){
			return false;
	} else {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm_sentFlag').value='true';
		//alert(+'/payroll/EmailPayrollReport_sendEmail.action');
		//alert(1);
		//document.getElementById('paraFrm').action="EmailPayrollReport_sendEmail.action";
		document.getElementById('paraFrm').action="<%=request.getContextPath()%>/payroll/EmailPayrollReport_sendEmail.action";
		
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target = "main";	
		
		//clearFun();
		//backFun();
	}
}
function clearFun(){
	document.getElementById('paraFrm_mailToEmployeeEmail').value="";
	document.getElementById('paraFrm_subject').value="";
	document.getElementById('paraFrm_ccEmailIds').value="";
	document.getElementById('paraFrm_htmlcode').value="";
}
function validateMail(){

	var oEditor = CKEDITOR.instances.description;
	var desc=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();
	
	var sub = document.getElementById('paraFrm_subject').value;
	var cc = document.getElementById('paraFrm_ccEmailIds').value;
	var toMail = document.getElementById('paraFrm_mailToEmployeeEmail').value;
	//var oEditor = FCKeditorAPI.GetInstance('description') ;
	//var desc=oEditor.GetHTML();
	//var desc = document.getElementById('paraFrm_description').value;
	//var tbl = document.getElementById('mailToTable');
	//var lastRow = tbl.rows.length;
	
		
	if(toMail == "" && cc == ""){
  		alert("There must be atleast one address in the To:, or Cc: fields");
  		document.getElementById('paraFrm_mailToEmployeeEmail').focus();
  		return false;
	}
	/*if(cc != ""){
		if(!validateEmail('paraFrm_ccEmailIds')){
 					return false;
 			}
	}*/
	if(sub == ""){
  		alert("Please enter subject");
  		document.getElementById('paraFrm_subject').focus();
  		return false;
	}
	if( desc == ""){
  		alert("Please enter description");
  		document.getElementById('paraFrm_description').focus();
  		return false;
	}
	
	return true;
}

</script>