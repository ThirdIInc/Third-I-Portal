<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
.textAreaButton {
	background-color: #EFEFDE;
	border: 1px outset #CCCCCC;
	font: Arial 11px;
	font-size: bold;
}
</style>
<%@include file="/pages/common/labelManagement.jsp"%>


<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
	
<s:form action="BackGroundCheck" method="post" name="EmailTemplate"
	validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Back Ground Check</strong></td>
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
					<td colspan="4"><s:hidden name="htmlcode" /> <input
						type="button" class="token" value="    Send Mail    "
						onclick="sendMailFunction();"> <input type="button" class="cancel"
						value="Cancel" onclick="callCancel();"></td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3" width="100%">
				<b> Note : </b>Please enter semicolon separated email ID.
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="1" width="15%" nowrap="nowrap">
						<label name="bgcheck.toMailId" id="bgcheck.toMailId"
						ondblclick="callShowDiv(this);"><%=label.get("bgcheck.toMailId")%></label>
					:<font color="red">*</font>
					<td colspan="3"><s:textfield name="bgcheckMailToMailID"
						size="50" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="15%" nowrap="nowrap"><label
						name="bgcheck.mailsubject" id="bgcheck.mailsubject"
						ondblclick="callShowDiv(this);"><%=label.get("bgcheck.mailsubject")%></label>
					:<font color="red">*</font>
					<td colspan="3"><s:textfield name="bgcheckMailSubject"
						size="100" /></td>
				</tr>

			</table>
			</td>
		</tr>



		<tr>

			<td colspan="4" width="100%" valign="top">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td><textarea style="" id="MyTextarea" name="MyTextarea"
						onfocus="oFCKeditor.onClickFocus('MyTextarea');"
						onblur="onClickFocus('MyTextarea');"></textarea></td>
				</tr>
			</table>

			</td>


		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4"><input type="button" class="token"
						value="    Send Mail    " onclick="sendMailFunction();"> <input
						type="button" class="cancel" value="Cancel"
						onclick="callCancel();"></td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<s:hidden name="bgcheckCode" />
</s:form>

<script>




var oFCKeditor;
window.onload = function() {
		setMess();
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		oFCKeditor.ReplaceTextarea();
}
	
function setMess() {
		
		var ms=document.getElementById('paraFrm_htmlcode').value;
		document.getElementById('MyTextarea').value=ms;
}
	
	
function setMessage() {
	
	
	var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
	var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
}
	
function callCancel() {  
 window.close();
}

 function sendMailFunction() {  
		try {
		setMessage();
		debugger;
				var emailID = trim(document.getElementById("paraFrm_bgcheckMailToMailID").value);
				if(emailID =="") {
					alert("please enter "+document.getElementById('bgcheck.toMailId').innerHTML.toLowerCase());
					document.getElementById("paraFrm_bgcheckMailToMailID").focus();
					return false;
				} else {
					var mySplitResult = emailID.split(";");
					for(var i = 0; i < mySplitResult.length; i++)
					{
						if(!validateEmailAddress(mySplitResult[i])){
		 					return false;
		 				}
					}
				}
				
				var subject = trim(document.getElementById("paraFrm_bgcheckMailSubject").value);
				if(subject =="") {
					alert("please enter "+document.getElementById('bgcheck.mailsubject').innerHTML.toLowerCase());
					document.getElementById("paraFrm_bgcheckMailSubject").focus();
					return false;
				}
				
				var surveyMailSubject = document.getElementById('paraFrm_bgcheckMailSubject').value;
				var surveyMailMessage = document.getElementById('paraFrm_htmlcode').value;
				var surveyCode =opener.document.getElementById('paraFrm_bgcheckCode').value ;
				 
				document.getElementById('paraFrm').action='BackGroundCheck_sendMailForBackGroundCheck.action';
			  	document.getElementById('paraFrm').submit();
			} catch(e) { 
				alert("value of e-------------"+e);
			}
}


function validateEmailAddress (name) {
	var emailStr = name;
	if(emailStr=="")return true;
	
	var checkTLD        = 1;
	
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	var emailPat		= /^(.+)@(.+)$/;
	
	var specialChars	= "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	
	var validChars		= "\[^\\s" + specialChars + "\]";
	
	var quotedUser		= "(\"[^\"]*\")";
	
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	var atom			= validChars + '+';
	
	var word			= "(" + atom + "|" + quotedUser + ")";
	
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	
	var matchArray		= emailStr.match(emailPat);

	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	
/*	
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			return false;
	 	}
*/	
	
	 	
	 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			return false;
	 	}
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127 || user.indexOf("-")!=-1) {
				alert("The username contains invalid characters.");
				return false;
		 }
	}

	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			return false;
	 	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		return false;
	}
	
	return true;
}

</script>
