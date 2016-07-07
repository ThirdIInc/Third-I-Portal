<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<!--<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
-->

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />
	

<s:form action="SendMail" validate="true" id="paraFrm"  theme="simple">
	<s:hidden name="uploadFileName" />
	<s:hidden name="sendMail.mailCode" />
	<s:hidden name="sendMail.date" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Mass
					  Mail </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						value="    Send Mail" action="SendMail_sendMail"
						onclick="return send();" /> <s:submit cssClass="reset"
						value="    Reset" action="SendMail_reset" /> <input type="button"
						class="search" value="    Sent Mails"
						onclick="javascript:callsF9(500,325,'SendMail_f9action.action'); " />
					</td>
					<td width="22%">
						<div align="right"><font color="red">*</font> Indicates	Required</div>
					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td colspan="5">
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
						<tr>
							<td width="25%" height="22" class="formtext"><b><label
								class="set" name="subject" id="subject"
								ondblclick="callShowDiv(this);"><%=label.get("subject")%></label>
							:</b><font color="red">*</font></td>
							<td colspan="4"><s:textfield name="sendMail.subject" theme="simple"
								size="110" maxlength="60" onkeypress="return allCharacters();" />
							</td>
						</tr>
						<tr>
							
							<td class="formtext"><b><label class="set"
								name="send.to" id="send.to" ondblclick="callShowDiv(this);"><%=label.get("send.to")%></label>
							:</b></td>
							<td colspan="4"><b><label class="set"
								name="mass.mail" id="mass.mail"
								ondblclick="callShowDiv(this);"><%=label.get("mass.mail")%></label></b>
							</td>

						</tr>
						<tr>
							<td><label class="set"
								name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td colspan="4"><s:textfield size="35"
								name="divisionName" readonly="true" /> <s:hidden
								name="divisionCode" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0" onclick="javascript:callDivision();" />
							</td>
						</tr>
						<tr>
							<td><label class="set"
								name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="4"><s:textfield size="35"
								name="branchName" readonly="true" /> <s:hidden
								name="branchCode" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0" onclick="javascript:callBranch();" />
							</td>
						</tr>
						<tr>
							<td><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="4"><s:textfield size="35"
								name="deptName" readonly="true" /> <s:hidden name="deptCode" />
							<img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0" onclick="javascript:callDept();" /></td>
						</tr>
						<tr>
							<td><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="65%" colspan="4"><s:textfield size="35"
								name="desgName" readonly="true" /> <s:hidden name="desgCode" />
							<img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0"
								onclick="javascript:callDesignationt();" /></td>
						</tr>
						<tr>
							<td colspan="5" align="center"><b>( <label class="set"
								name="or" id="or" ondblclick="callShowDiv(this);"><%=label.get("or")%></label>
							)</b></td>
						</tr>
						<tr>
							<td class="formtext"><b><label class="set"
								name="send.to" id="send.to1" ondblclick="callShowDiv(this);"><%=label.get("send.to")%></label>
							:</b></td>
							<td colspan="4"><b><label class="set"
								name="individual.mail" id="individual.mail"
								ondblclick="callShowDiv(this);"><%=label.get("individual.mail")%></label></b>
							</td>
						</tr>
						<tr>
							<td><label class="set"
								name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td colspan="4"><s:textfield size="35"
								name="employeeName" readonly="true" /> <s:hidden
								name="employeeCode" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0" onclick="javascript:callEmployee();" />
							</td>
						</tr>
						<tr>
							<td><b><label
								class="set" name="ccemailId" id="ccemailId"
								ondblclick="callShowDiv(this);"><%=label.get("ccemailId")%></label>
							: </td>
							<td colspan="4"><s:textarea cols="75" rows="5"
								name="sendMail.ccMailId" /></td>
						</tr>
						<tr>
							<s:if test="viewModeFlag">
								<td class="formtext" nowrap="nowrap"><b>View Attachment : </td>
								<td colspan="4">
								<s:hidden name="attachmentFile"/>
								<a	href="#" onclick="callViewFile('<s:property value="attachmentFile" />');"><s:property value="attachmentFile" /></a>
								</td>
							</s:if><s:else>
								<td class="formtext" nowrap="nowrap"><b><label
								class="set" name="uploadfile" id="uploadfile"
								ondblclick="callShowDiv(this);"><%=label.get("uploadfile")%></label>
								: </td>
								<td colspan="4">
								<s:textfield name="attachmentFile" readonly="true" size="40" /> &nbsp;
								<input name="Upload" type="button" class="token" value="Browse"
								onclick="uploadFile('attachmentFile');" align="bottom"/>
							</td>
							</s:else>
						</tr>
						<tr>
							<td height="22" colspan="5" class="formtext"><b><label
								class="set" name="message" id="message"
								ondblclick="callShowDiv(this);"><%=label.get("message")%></label>
							:</b><font color="red">*</font> <s:hidden name="sendMail.message" /></td>
						</tr>
						<tr>
							<td colspan="5" width="100%"><b> Note: </b></td>
						</tr>
						<tr>
							<td colspan="5" width="100%">1.In CC Field email Ids should
							be (;) seperated.</td>
						</tr>
						<tr>
							<td colspan="5" width="100%">
								<textarea id="MyTextarea" name="MyTextarea" ></textarea>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="100%"><s:submit cssClass="save"
								value="    Send Mail" action="SendMail_sendMail"
								onclick="return send();" /> <s:submit cssClass="reset"
								value="    Reset" action="SendMail_reset" /></td>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="dataPath" />
</s:form>
<script type="text/javascript">
			//<![CDATA[
CKEDITOR.replace( 'MyTextarea');
				

			//]]>
</script>

<script>
	function setMessage(){
		try{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var ms=document.getElementById('paraFrm_sendMail_message').value=oEditor.GetHTML();
		var ms=document.getElementById('paraFrm_sendMail_message').value=oEditor.getData();
		} catch(e){
		//alert("Error:::"+e);
		}
	}
	function callEmployee(){
		setMessage();
		callsF9(500,325,'SendMail_f9employee.action');
	}

	function callDivision(){
		setMessage();
		var lucky=callsF9(500,325,'SendMail_f9division.action');
	}

	function callBranch(){
		setMessage();
		callsF9(500,325,'SendMail_f9branch.action');
	}

	function callDept(){
		setMessage();
		callsF9(500,325,'SendMail_f9dept.action');
	}

	function callDesignationt(){
		setMessage();
		callsF9(500,325,'SendMail_f9designation.action');
	}

/*
	var oFCKeditor;

	window.onload = function(){
		setMess();
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		oFCKeditor.ReplaceTextarea() ;
	}
*/


	function setMess(){
	var ms=document.getElementById('paraFrm_sendMail_message').value;
	document.getElementById('MyTextarea').value=ms;
	}

	function send(){
		var sub=document.getElementById('paraFrm_sendMail_subject').value;
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var ms=document.getElementById('paraFrm_sendMail_message').value=oEditor.GetHTML();
		var ms=document.getElementById('paraFrm_sendMail_message').value=oEditor.getData();
		var subject=document.getElementById('subject').innerHTML.toLowerCase();
		var message=document.getElementById('message').innerHTML.toLowerCase();
		var div = document.getElementById('paraFrm_divisionName').value;
		var branch = document.getElementById('paraFrm_branchName').value;
		var dept = document.getElementById('paraFrm_deptName').value;
		var desg = document.getElementById('paraFrm_desgName').value;
		var empCode = document.getElementById('paraFrm_employeeName').value;
		 
		if(sub==""){
			alert("Please enter "+subject);
			return false;
		}
		if(ms==""){
			alert("Please enter "+message );
			return false;
		}
		if(empCode == ""){
			if(div == "" && branch == "" && dept == "" && desg == ""){
				alert("Please select at least one criteria from send to options ");
				return false;
			}
		}
		return true;
	}

	function uploadFile(fieldName) {
		var path=document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	function callViewFile(fileName) {
	  	document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'SendMail_viewAttachmentFile.action?fileName='+fileName;
		document.getElementById('paraFrm').submit();
	} 
	
</script>
