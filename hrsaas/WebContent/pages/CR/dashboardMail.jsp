<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/lib/dropdown.jsp"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<s:form action="DashBoard" validate="true" id="paraFrm" theme="simple">
	<script src="/ckeditor/sample.js" type="text/javascript"></script>
	<link href="/ckeditor/sample.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
	
	function callDropdown(obj,width, height, action,event,showSearch,multiple,align) {
		getDropdown(action,width, height,obj,event,showSearch,multiple,align);
	}
	function backFun() {
	/*	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DashBoard_input.action';
		document.getElementById('paraFrm').submit();*/
		
	}
	
	
	function sendMail(){
	if(!validateMail()){
			return false;
	} else {
	try{
	var description =trim(document.getElementById('description').value) ;
	description = description.replace(/(\r\n)|(\n)/g,"<br />");
	document.getElementById('descriptionBr').value=description;
	}catch(e){alert(e);}
	//alert("sendMail function is called");
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm_sentFlag').value='true';
		document.getElementById('paraFrm').action = 'DashBoard_sendEmail.action';
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target = "main";	
		//window.history.back();
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
	try{
	//var oEditor = CKEDITOR.instances.description;
	//var desc=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();
	var sub= document.getElementById('subject').value;
	var cc = document.getElementById('paraFrm_ccEmailIds').value;
	var toMail = document.getElementById('paraFrm_mailToEmployeeEmail').value;
	//var oEditor = FCKeditorAPI.GetInstance('description') ;
	//var desc=oEditor.GetHTML();
	var desc = document.getElementById('description').value;
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
  		document.getElementById('subject').focus();
  		return false;
	}
	if( desc == ""){
  		alert("Please enter description");
  		document.getElementById('description').focus();
  		return false;
	}
	}catch(e){
	alert(e);
	}
	
	return true;}
	
	function addBR(){
	var getTextAreaFileds=document.getElementById('description').value;
	
		getTextAreaFileds=getTextAreaFileds+'</br>'
	alert(getTextAreaFileds);
	}
	</script>
	<%
		String attachment = "";
		String backAction = "";
		String fileName = "";
		String DefoultSub="";
		try {
			attachment = String.valueOf(request.getAttribute("reportPath"));
			backAction = String.valueOf(request.getAttribute("action"));
			fileName = String.valueOf(request.getAttribute("fileName"));
			DefoultSub=String.valueOf(request.getAttribute("defoultSub"));
		} catch (Exception e) {
		}
	%>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<s:hidden name="attachmentPath" value="<%=attachment%>" />
		<s:hidden name="actionBack" value="<%=backAction%>" />
		<s:hidden name="fileName" value="<%=fileName%>" />
		<s:hidden name="htmlcode" />
		<s:hidden name="sentFlag" />
		<s:hidden name="DefoultSubject"></s:hidden>
		<s:hidden name="dashBoardID"/>
		<s:hidden id="accountID" name="accountID" />
		<s:hidden id="accountName" name="accountName" />
		<s:hidden name="descriptionBr" id="descriptionBr"> </s:hidden>
		<s:hidden id="screenWidth" name="screenWidth"/>
		<s:hidden name="isDashBoard"/>
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Email
					Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr height="25" valign="middle">
					<td width="80%"><!--  <input type="button" class="token"  onclick="sendMail()" value=" Send Mail "/>-->
					<s:if test="sentFlag=='true'"></s:if><s:else>
						<a href="#" id='mailHref' onclick="sendMail()"><img
							src="../pages/images/buttonIcons/email.png" class="iconImage"
							align="absmiddle" " title="PDF">&nbsp;Send Mail&nbsp;</a>
					</s:else> 
					
					<s:if test="isDashBoard!='YY'">
					<a href="#" onclick="history.back();"><img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" " title="PDF">&nbsp;Back</a>
					</s:if>
					<!-- <a href="#" onclick="history.back();"><img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" " title="PDF">&nbsp;Back</a> -->
						</td>
					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4"><strong class="text_head">Mail this
					Report </strong></td>
				</tr>
				<tr>
					<td>To</td>
					<td width="4"><font color="red">*</font></td>
					<td width="4">:</td>
					<td width="80%"><s:textarea name="mailToEmployeeEmail" id="paraFrm_mailToEmployeeEmail"
						cssStyle="overflow:hidden" cols="125" rows="1"
						onkeyup="javascript:callDropdown('paraFrm_mailToEmployeeEmail',200,250,'DashBoard_f9employeeToMail.action',event,'false','multiple')" />
					</td>
				</tr>
				<tr>
					<td>CC</td>
					<td></td>
					<td>:</td>
					<td><s:textarea name="ccEmailIds" id="paraFrm_ccEmailIds" cssStyle="overflow:hidden"
						cols="125" rows="1"
						onkeyup="javascript:callDropdown('paraFrm_ccEmailIds',200,250,'DashBoard_f9employeeCCMail.action',event,'false','multiple')" /></td>
				</tr>
				<tr>
					<td>Subject</td>
					<td><font color="red">*</font></td>
					<td>:</td>
					<%
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
					
					%>
					<td><s:textarea name="subject" id="subject" cssStyle="overflow:hidden" value="<%="Periodic Report: "+DefoultSub+" ("+sdf.format(new Date())+")" %>"
						cols="125" rows="1" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td nowrap="nowrap"><s:if test="sentFlag=='true'"></s:if><s:else>
						<img src="../pages/images/buttonIcons/Attachment.png"
							class="iconImage" align="absmiddle" " title=""
							style="cursor: inherit; padding-right: 5px;" />
						<u><%=fileName%></u>
					</s:else></td>
				</tr>
				<tr>
					<td valign="top">Message</td>
					<td valign="top"><font color="red">*</font></td>
					<td valign="top">:</td>								
					<td valign="top" nowrap="nowrap">
					<s:textarea name="description" id="description" cssStyle="overflow:hidden"
						cols="125" rows="4" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr height="25" valign="middle">
					<td width="100%"><s:if test="sentFlag=='true'"></s:if><s:else>
						<a href="#" onclick="sendMail()"><img
							src="../pages/images/buttonIcons/email.png" class="iconImage"
							align="absmiddle" " title="PDF">&nbsp;Send Mail&nbsp;</a>
					</s:else> 
					<s:if test="isDashBoard!='YY'">
					<a href="#" onclick="history.back();"><img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" " title="PDF">&nbsp;Back</a>
					</s:if>
						</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

