<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="ClientMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="bean.hiddencode" />
	<!-- hiddencode used to get data back during edit function	-->
	<s:hidden name="bean.clientUserNo" />
	

	<!-- Table 1 Started	-->
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<!-- Table 2 Started	-->
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Client
					User </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
				<!-- Table 2 Ended	-->
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<!-- Table 3 Started	-->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>


			</table>
			<!-- Table 3 Ended	-->
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<!-- Table 4 Started	-->
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
			<!-- Table 5 Started	-->	
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">


						<tr>

							<td width="15%" colspan="1" height="22"><label class="set"
								name="first.name" id="first.name"
								ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
							:<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield
								size="25" theme="simple" maxlength="60" name="bean.firstName"
								onkeypress="return allCharacters();" /></td>
						</tr>

						<tr>

							<td width="15%" colspan="1" height="22"><label class="set"
								name="last.name" id="last.name" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>:<font
								color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield
								size="25" theme="simple" maxlength="20" name="bean.lastName"
								onkeypress="return allCharacters();" /></td>
						</tr>

						<tr>




							<td width="15%" colspan="1"></td>
							<td width="85%" colspan="2"></td>
						</tr>

						<tr>

							<td width="15%" colspan="1"><label class="set"
								name="email.address" " id="email.address"
								" ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
							:<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:textfield
								size="25" theme="simple" maxlength="90"
								name="bean.emailClientAddress" /></td>

						</tr>

						<tr>
							<td width="15%" colspan="1"><label class="set"
								name="password" id="password" ondblclick="callShowDiv(this);"><%=label.get("password")%></label>
							:<font color="red">*</font></td>
							<td width="85%" colspan="2" height="22"><s:password
								name="bean.password" size="25" theme="simple" maxlength="25"
								onkeypress="return allCharacters();" /> </td>

						</tr>
						<tr>
							<td width="15%" colspan="1"><label class="set"
								name="cell.number" id="cell.number"
								ondblclick="callShowDiv(this);"><%=label.get("cell.number")%></label>
							:</td>
							<td width="85%" colspan="2" height="22"><s:textfield
								size="25" theme="simple" maxlength="12"
								onkeypress="return numbersOnly();" name="bean.cellNumber" /></td>
						</tr>

						<tr>
							<td colspan="1"><label class="set" name="is.active"
								id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							:<font color="red">*</font></td>
							<td colspan="2"><s:checkbox name="isClientActive"
								onclick="showCalculatedBox();" /> <s:hidden name="ittIsActive"
								id="ittIsActive" /></td>



						</tr>
						<s:hidden name="bean.ittClientUserNo" />





					</table>
					<!-- Table 5 Ended	-->
					</td>
				</tr>
			</table>
			<!-- Table 4 Ended	-->
		<tr>
			<td width="80%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<!-- Table 1 Ended	-->
</s:form>



<script type="text/javascript">
onLoad();
	function onLoad(){
		var checkValue = document.getElementById('ittIsActive').value;
 		var parentCheckboxVar = document.getElementById('paraFrm_isClientActive').checked;
 		if((parentCheckboxVar == false) && checkValue=='L'){
	 		document.getElementById('ittIsActive').value="L";
			document.getElementById('paraFrm_isClientActive').checked =false;
 		}else{
 		
	 		document.getElementById('paraFrm_isClientActive').checked =true;
			document.getElementById('ittIsActive').value="S";
 		}
 		showCalculatedBox();
	}
	
	function showCalculatedBox(){
	
		if(document.getElementById('paraFrm_isClientActive').checked ==true){
			
			document.getElementById('ittIsActive').value="S";
		} else {
		
			
			document.getElementById('ittIsActive').value="L";
		}
	}	
	
	function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}

function saveFun(type)
 {

try{
	
	
	var qtt=trimData(document.getElementById('paraFrm_bean_firstName').value);
		
		if(document.getElementById('paraFrm_bean_firstName').value==''){
			 alert ("Please enter " +document.getElementById('first.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bean_firstName').focus();
			return false;
		}
		
		if(qtt==''){
			 alert ("Please enter " +document.getElementById('first.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bean_firstName').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_bean_lastName').value==''){
			 alert ("Please enter " +document.getElementById('last.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bean_lastName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_bean_emailClientAddress').value==''){
			 alert ("Please enter " +document.getElementById('email.address').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bean_emailClientAddress').focus();
			return false;
		}
		if(document.getElementById('paraFrm_bean_password').value==''){
			 alert ("Please enter " +document.getElementById('password').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_bean_password').focus();
			return false;
		}
		
		
		
		
		
		try
			{
				if(!document.getElementById('paraFrm_bean_emailClientAddress').value==''){
					var fields=["paraFrm_bean_emailClientAddress"];
		    		var labels=["bean_email.address"];
		    		var flag = ["enter"];
		 	 		if(!validateBlank(fields,labels,flag))
		    		 return false;
			 
			  		 if(!validateEmailAPS('paraFrm_bean_emailClientAddress')){
					 	return false;
					 }
				 }
				 
			}catch(e){
			//alert("Exception occured in draft function : "+e);
			}
	
	}catch (e)
	{
	
	}
    
      document.getElementById('paraFrm').target = "_self";
     	
  		document.getElementById('paraFrm').action = 'ClientMaster_addClientUserLstDtl.action';
     	document.getElementById('paraFrm').submit(); 
     	    return true;
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ClientMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClientMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ClientMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'ClientMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
		
		
		function editFun() {
		
		return true;
		
	}	
	
		function validateEmailAPS (name) {
			var name1 = document.getElementById(name).value.toLowerCase();
			var emailStr        = name1;
			if(emailStr=="")return true;
			
			var checkTLD        = 1;
			
			var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
			
			var emailPat		= /^(.+)@(.+)$/;
			
			var specialChars	= "\\(\\)><@,;:-\\\\\\\"\\.\\[\\]";
			
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
				document.getElementById(name).focus();
				return false;
			}
			var user			= matchArray[1];
			var domain			= matchArray[2];
			
			/*if(user.indexOf(".")!=-1 && user.split(".").length>2){
			 		alert("Only one dot is allowed in user name of email id");
					document.getElementById(name).focus();
					return false;
			 	}*/
			
			
			 	
			 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
			 		alert("The username doesn't seem to be valid for email.");
					document.getElementById(name).focus();
					return false;
			 	}
			for (i=0; i<user.length; i++) {
				if (user.charCodeAt(i)>127 ) {
						alert("The username contains invalid characters.");
						document.getElementById(name).focus();
						return false;
				 }
			}
		
			if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
			 		alert("The domain doesn't seem to be valid for email.");
					document.getElementById(name).focus();
					return false;
			 	}
		
			for (i=0; i<domain.length; i++) {
				if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
					alert("The domain name contains invalid characters.");
					document.getElementById(name).focus();
					return false;
		   		}
			}
		
		
			if (user.match(userPat)==null) {
				alert("The username doesn't seem to be valid for email.");
				document.getElementById(name).focus();
				return false;
			}
		
			var IPArray=domain.match(ipDomainPat);
			if (IPArray!=null) {
				for (var i=1;i<=4;i++) {
					if (IPArray[i]>255) {
						alert("Destination IP address is invalid!");
						document.getElementById(name).focus();
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
				document.getElementById(name).focus();
				return false;
			}if(dot<hiphen && (hiphen-dot)<2){
				alert("The domain name does not seem to be valid");
				document.getElementById(name).focus();
				return false;
			}if(len>3){
				alert("Maximum two dots are allowed in domain name");
				document.getElementById(name).focus();
				return false;
			}
			
			for (i=0;i<len;i++) {
				if (domArr[i].search(atomPat)==-1) {
					alert("The domain name does not seem to be valid");
					document.getElementById(name).focus();
					return false;
			   }
			}if(domain.indexOf("-")!=-1){
				if(domain.indexOf("-")<2){
					alert("The domain name does not seem to be valid");
					document.getElementById(name).focus();
					return false;
				}
			}
		
			if (len<2) {
				alert("This email address is missing a hostname!");
				document.getElementById(name).focus();
				return false;
			}
			
			return true;
	}
		</script>



