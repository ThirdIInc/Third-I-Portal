<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="AccountMaster" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="hiddencode" />
	<s:hidden name="accountCode" />
<s:hidden name="dataPath" /> 
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Account
					Master </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<td width="80%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="20%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" class="formbg" border="0">
				<tr>
					<td width="25%" colspan="1"><label id="business.name"
						name="business.name" ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="businessName" size="25"
						maxlength="100" /></td>

					<td width="25%"><label id="acc.id" name="acc.id"
						ondblclick="callShowDiv(this);"><%=label.get("acc.id")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="accountId" size="25"
						maxlength="100" onkeypress=""/></td>
				</tr>

				<tr>
					<td width="25%" height="22" class="formtext"><label
						name="is.parent" class="set" id="is.parent"
						ondblclick="callShowDiv(this);"><%=label.get("is.parent")%></label>:</td>
					<td width="25%"><s:checkbox name="isParent" onclick="showCalculatedBox();"/></td>
					<td width="25%" id="calculatedBox"><label id="parent" name="parent"
						ondblclick="callShowDiv(this);"><%=label.get("parent")%></label>:<font color="red">*</font></td>
					<td width="25%" colspan="1" id="calculatedBox1" nowrap="nowrap"><s:hidden name="parentCode" /> <s:textfield
						name="parentName" size="25" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'AccountMaster_f9parent.action');"></td>
						<s:hidden name="hiddenCheckBoxFlag" id="hiddenCheckBoxFlag"/>
				</tr>
				<!--<tr>
					<td width="20%" height="22" class="formtext"><label
						name="part.wait.time.req" class="set" id="part.wait.time.req"
						ondblclick="callShowDiv(this);"><%=label.get("part.wait.time.req")%></label>
					:</td>
					<td><s:checkbox name="isPartWaitTimeChecked"  onclick="showPartWaitTimeBox();"/>
					<s:hidden name="hiddenPartWaitTimeFlag" id="hiddenPartWaitTimeFlag"/></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>
				</tr>
				--><tr>
					<td width="25%%" colspan="1"><label id="contact.name"
						name="contact.name" ondblclick="callShowDiv(this);"><%=label.get("contact.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="contactName" size="25"
						maxlength="100" /></td>
						
					<td width="25%"><label id="email.address" name="email.address"
						ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
					:</td>
					<td width="25%"><s:textfield name="emailAddress" size="25"
						maxlength="150"  /></td>
				</tr>	
				<tr>
							<td width="25%" colspan="1"><label class="set"	name="address"	id="address"
								ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
							:<font id='ctrlHide' color="red"></font></td>

							<td  colspan="2"  nowrap="nowrap"><s:textarea name="address"
								cols="75" rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_address','address','','400','400');"></td></td>
							
							
			</tr>
			<tr>
					<td width="25%" colspan="1"><label id="city.name"
						name="city.name" ondblclick="callShowDiv(this);"><%=label.get("city.name")%></label>:<font color="red"></font></td>
					<td width="25%" colspan="1"><s:textfield
						name="cityName" size="25" maxlength="30" /> <!--<img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'AccountMaster_f9cityaction.action');">
					--></td>

					<td width="25%"><label id="country.name" name="country.name"
						ondblclick="callShowDiv(this);"><%=label.get("country.name")%></label>:</td>
					<td width="25%"><s:textfield name="countryName" size="25"
						maxlength="30"/></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="state.name"
						name="state.name" ondblclick="callShowDiv(this);"><%=label.get("state.name")%></label>:</td>
					<td width="25%"><s:textfield name="stateName" size="25"
						maxlength="30"/></td>

					<td width="25%"><label id="zip.code" name="zip.code"
						ondblclick="callShowDiv(this);"><%=label.get("zip.code")%></label>:<font color="red"></font></td>
					<td width="25%"><s:textfield name="zipCode" size="25"
						maxlength="8" onkeypress="return numbersOnly();"/></td>
				</tr>
				<tr>
							<td width="25%" colspan="1" height="27"><label  class = "set" name="logo" id="logo" ondblclick="callShowDiv(this);"><%=label.get("logo")%></label>:</td>
							<td height="27" colspan="2"><s:textfield size="40" name="uploadFileName"
								readonly="true" /> <input type="button" class="token"
								name="Browse" value="Browse"
								onclick="uploadFile('uploadFileName');" /></td>
				</tr>
				<!--<tr>
						<td> </td>
						<td><a href="#" onclick="viewUploadedFile();"><font color="blue"><u>click here to view uploaded logo</u></font></a>		 </td>
						
						</tr>
				--><tr>
					<td width="20%" height="22" class="formtext"><label
						name="is.active" class="set" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td><s:checkbox name="isActive"  onclick="showActiveBox();"/>
					<s:hidden name="hiddenActiveFlag" id="hiddenActiveFlag"/></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
		<td width="100%"><jsp:include
			page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>
	</table>
	
	
	
</s:form>


<script type="text/javascript">
onLoad();
	function onLoad(){
		//document.getElementById('paraFrm_isParent').checked =true;
		///document.getElementById('paraFrm_isActive').checked =true;
		var hiddenCheckBoxChecked = document.getElementById('paraFrm_accountCode').value;
 		var checkValue = document.getElementById('hiddenCheckBoxFlag').value;
 		var parentCheckboxVar = document.getElementById('paraFrm_isParent').checked;
 		if((parentCheckboxVar == false) && checkValue=='L'){
	 		document.getElementById('hiddenCheckBoxFlag').value="L";
			document.getElementById('paraFrm_isParent').checked=false;
 		}else{
 			document.getElementById('paraFrm_isParent').checked=true;
			document.getElementById('hiddenCheckBoxFlag').value="S";
 		}
 		
 		var checkActiveValue = document.getElementById('hiddenActiveFlag').value;
 		var checkboxActiveVar = document.getElementById('paraFrm_isActive').checked;
 		
 		if((checkboxActiveVar == false) && checkActiveValue=='L'){
	 		document.getElementById('hiddenActiveFlag').value="L";
			document.getElementById('paraFrm_isActive').checked =false;
 		}else{
 			document.getElementById('paraFrm_isActive').checked=true;
			document.getElementById('hiddenActiveFlag').value="S";
 		}
 		
 		
 		var checkPartWaitTimeValue = document.getElementById('hiddenPartWaitTimeFlag').value;
 		var checkboxPartWaitTimeVar = document.getElementById('paraFrm_isPartWaitTimeChecked').checked;
 		if((checkboxPartWaitTimeVar == false) && checkPartWaitTimeValue=='L'){
	 		document.getElementById('hiddenPartWaitTimeFlag').value="L";
			document.getElementById('paraFrm_isPartWaitTimeChecked').checked =false;
 		}else{
 			document.getElementById('paraFrm_isPartWaitTimeChecked').checked=true;
			document.getElementById('hiddenPartWaitTimeFlag').value="S";
 		}
 		
		showCalculatedBox();
		showActiveBox();
		showPartWaitTimeBox();
	}
function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}
	
	function saveFun(){	
		if(document.getElementById('paraFrm_businessName').value==""){
			alert("Please enter Account Name.");
			document.getElementById('paraFrm_businessName').focus();
  			return false;
		}
		
		if(document.getElementById('paraFrm_accountId').value==""){
			alert("Please enter Account ID.");
			document.getElementById('paraFrm_accountId').focus();
  			return false;
		}
		///if(document.getElementById('paraFrm_cityName').value==""){
		///	alert("Please enter a City.");
		///	document.getElementById('paraFrm_cityName').focus();
  		///	return false;
		///}
		///if(document.getElementById('paraFrm_zipCode').value==""){
		///	alert("Please enter ZipCode.");
		///	document.getElementById('paraFrm_zipCode').focus();
  		///	return false;
		///}
		
		
		var checkValue = document.getElementById('hiddenCheckBoxFlag').value;
 		var parentCheckboxVar = document.getElementById('paraFrm_isParent').checked;
 		if((parentCheckboxVar == false) && checkValue=='L'){
 		
			if(document.getElementById('paraFrm_parentCode').value==""){
			alert("Please select parent.");
			document.getElementById('paraFrm_parentName').focus();
  			return false;
			}
 		}
		
		try
			{
				if(!document.getElementById('paraFrm_emailAddress').value==''){
					var fields=["paraFrm_emailAddress"];
		    		var labels=["email.address"];
		    		var flag = ["enter"];
		 	 		if(!validateBlank(fields,labels,flag))
		    		 return false;
			 
			  		 if(!validateEmailAPS('paraFrm_emailAddress')){
					 	return false;
					 }
				 }
				 
			}catch(e){
			//alert("Exception occured in draft function : "+e);
			}
			
			try {
				var logoFile = trimData(document.getElementById('paraFrm_uploadFileName').value);
				
				if (!logoFile=='') {
					var ext = (logoFile.replace('.', '@').split('@')[1]).toLowerCase();
			if (ext != "gif" && ext != "jpeg" && ext != "jpg" && ext != "png") { 
			alert("Only jpg, png, gif, bmp image types are supported");
							document.getElementById('paraFrm_uploadFileName').value="";
							document.getElementById('paraFrm_uploadFileName').focus();
							
							return false;
						} 
						
				}
				
				
				
				
			} catch(e) {
				alert("Exception while validating mandetory fields >"+e);
				
			}	
			
			
			
			
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AccountMaster_save.action';
		document.getElementById("paraFrm").submit();
		return true;
	}
  	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AccountMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccountMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'AccountMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
	function editFun() {
		return true;
	}	
		
	function showCalculatedBox(){
	
		if(document.getElementById('paraFrm_isParent').checked ==true){
			document.getElementById('calculatedBox').style.display='none';
			document.getElementById('calculatedBox1').style.display='none';
			document.getElementById('hiddenCheckBoxFlag').value="S";
			document.getElementById('paraFrm_parentCode').value="";
			document.getElementById('paraFrm_parentName').value="";
		} else {
		
			document.getElementById('calculatedBox').style.display='';
			document.getElementById('calculatedBox1').style.display='';
			document.getElementById('hiddenCheckBoxFlag').value="L";
			///document.getElementById('paraFrm_parentCode').value="";
			///document.getElementById('paraFrm_parentName').value="";
		}
	}	
	
	
	function showActiveBox(){
	
		if(document.getElementById('paraFrm_isActive').checked ==true){
			document.getElementById('hiddenActiveFlag').value="S";
		} else {
			document.getElementById('hiddenActiveFlag').value="L";
		}
	}
	 
	function showPartWaitTimeBox(){
	
		if(document.getElementById('paraFrm_isPartWaitTimeChecked').checked ==true){
			document.getElementById('hiddenPartWaitTimeFlag').value="S";
		} else {
			document.getElementById('hiddenPartWaitTimeFlag').value="L";
		}
	}
	
	function imposeMaxLength(Event, Object, MaxLen)	{
	        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
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
			
			if(user.indexOf(".")!=-1 && user.split(".").length>2){
			 		alert("Only one dot is allowed in user name of email id");
					document.getElementById(name).focus();
					return false;
			 	}
			
			
			 	
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
	 function uploadFile_4(fieldName){
	 var dataPath="pages/CR/logo/";
		///var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}	
	
	 function uploadFile(fieldName) 
{
	var path="CR/logo";
	//window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100,');
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		
		document.getElementById('paraFrm').target="main";
}

	function viewUploadedFile() {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'AccountMaster_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}

</script>
