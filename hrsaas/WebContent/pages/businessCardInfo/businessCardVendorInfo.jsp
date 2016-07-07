<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="BusinessCardInfo" method="post" name="BusinessCardInfoForm" id="paraFrm" theme="simple">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">

		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Mail to Vendor</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr><td>Note: Please enter semicolon separated email ID</td></tr>
					<tr>
						<td><label id="tomail" name="tomail" class="set" 
								   ondblclick="callShowDiv(this)"><%=label.get("tomail")%></label><font color="red">*</font> :</td>
						<td><s:textarea  name="tomail" rows="3" cols="30" /></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td><label id="subject" name="subject" class="set"
						 ondblclick="callShowDiv(this)"><%=label.get("subject")%></label><font color="red">*</font> :</td>
						<td><s:textarea name="subject" rows="3" cols="30"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
			</table>
			</td>
		</tr>
</table>
</s:form>
<script type="text/javascript">
function cancelFun()
{
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
	document.getElementById('paraFrm').submit();
}
function sendmailFun(){
	var emailID=document.getElementById("paraFrm_tomail").value;
	var subject=document.getElementById("paraFrm_subject").value;
	if(emailID =="") {
		alert("please enter "+document.getElementById("paraFrm_tomail").innerHTML.toLowerCase());
		document.getElementById("paraFrm_tomail").focus();
		return false;
	} else {
		var mySplitResult = emailID.split(",");
		for(var i = 0; i < mySplitResult.length; i++){
			if(!validateEmailAddress(mySplitResult[i])){
					return false;
			}
		}
	}
	if(subject==""){
		alert("Please enter subject ");
			return false;
	}
	document.getElementById('paraFrm').target="_self";
	document.getElementById("paraFrm").action="BusinessCardInfo_sendMailToVendor.action";
	document.getElementById("paraFrm").submit();
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
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			return false;
	}
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

