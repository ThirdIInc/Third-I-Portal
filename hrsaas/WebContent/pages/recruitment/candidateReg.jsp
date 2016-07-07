<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CandidateRegistration" id="paraFrm" theme="simple"
	method="POST">
	
		<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>
	
	<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
			src="../pages/common/css/default/images/logo.jpg"  /></div>
	<div class="logotext" style="float: right; width: 60%">
	<table width="80%" height="51" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="72%">
			<div align="right" class="logotext"><strong><%=comanyName%></strong></div>
			</td>
			<td width="4%">&nbsp;</td>
			<td width="24%">
			<%
				String cmpName = null;
				cmpName = (String) request.getAttribute("logo");

				System.out.println("String cmpName =" + cmpName);
				if (cmpName != null && !cmpName.equals("null")
						&& !cmpName.equals("")) {
			%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
				height="51" /> <%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
				src="../pages/common/css/default/images/logo.jpg"
				height="51" /> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>
	
	<tr>
			<td>
			<div
				style="background-color: #6979ac; float: left; width: 100%; margin: 0; padding: 0;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="20px" class="mainheader"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	
	
	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />
	<s:hidden name="submitFlag"></s:hidden>
	<table width="100%" colspan="4" cellpadding="0" cellspacing="0"
		align="right" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Registration </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
			</table>
			</td>
		</tr>
		<s:if test="submitFlag">
			<tr>
				<td colspan="2">
				<div id="mail">
				<table align="center">

					<tr>
						&nbsp;
					</tr>
					<tr>
						<td colspan="2" align="center">Registration process completed
						successfully. Your login details has been mailed, please check
						your mail box for login details.</td>

					</tr>
					<tr>
						<td colspan="1">&nbsp;</td>
						<td colspan="1" align="center">&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="token" theme="simple" value="Close Window"
							onclick="closeWin();" /></td>
					</tr>
				</table>
				</div>
				</td>

			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td colspan="5" class="formhead"><strong
									class="forminnerhead"></strong></td>
							</tr>



							<tr>
								<td width="15%">First Name :<font color="red">*</font></td>
								<td width="85%"><s:hidden name="candidateCode" /> <s:textfield
								value="%{firstName}"
									name="firstName" theme="simple"   size="30"
									maxlength="50" onkeypress="return charactersOnly();" /></td>




							</tr>

							<tr>
								<td width="15%">Last Name :<font color="red">*</font></td>
								<td width="85%"><s:textfield name="lastname" theme="simple"
								  size="30" maxlength="50"
									onkeypress="return charactersOnly();" /></td>

							</tr>


							<tr>
								<td width="15%">Email Id :<font color="red">*</font></td>
								<td width="85%" nowrap="nowrap">
								<s:textfield name="emailId"	theme="simple"  size="30" maxlength="50" /> 
								<input	type="button" class="token" value="Check Availability"
									onclick="callAvailability();" /></td>
							</tr>

							<tr>
								<td width="15%">Date Of Birth :<font color="red">*</font></td>
								<td width="85%"><s:textfield name="dateOfBirth"
									theme="simple" readonly="false" size="30" maxlength="10"
									onkeypress="return numbersWithHiphen();" /> <s:a
									href="javascript:NewCal('paraFrm_dateOfBirth','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" height="16" border="0" />
								</s:a></td>

							</tr>

							<tr>
								<td width="15%">Mobile No. :<font color="red">*</font></td>
								<td width="85%"><s:textfield name="mobile" theme="simple"
									readonly="false" size="30" maxlength="20"
									onkeypress="return validateMobile();" /></td>

							</tr>


						</table>
						</td>
					</tr>

					<tr>
						<td width="100%">
						<table width="100%">
							<tr>

								<td width="15%" class="style1" align="left"><font
									color="red">Image Authentication : Type the letters as
								shown below.</font></td>
							</tr>

						</table>

						</td>

					</tr>




					<tr>
						<td width="100%">
						<table width="100%" border="0">
							<tr>
								<td width="39%"><span class="wtxt"> <img
									src="CandidateRegistration_getKeyCode.action"
									alt="Enter the characters appearing in this image" border="0"
									align="left" /></span></td>
								<td width="61%"><input name="inCaptchaChars"
									id="inCaptchaChars" type="text" size="10" /></td>


							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td width="100%">
						<table width="100%" border="0">
							<tr>
								<td width="35%" align="right">
								<input type="button" class="token" value="Submit" id="submitBtn" onclick="callLogin();" /> 
								<input type="button" class="reset" value="Reset" onclick="resetFields();" /> 
							<!-- 
								<s:submit cssClass="reset" value=" Reset" action="CandidateRegistration_reset" />
							 -->	
								</td>
								<td width="65%"></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>
		<tr>
			<td height="28" bgcolor="#f2f2f2" class="border1">
			<table width="980" border="0" align="center">
				<tr>
					<td class="link">&reg;All Rights Reserved &copy;Copyright</td>
					<td class="txt">
					<div align="center"><span class="link">I / We
					acknowledge and accept the Terms and Conditions applicable and
					available on the site.</span></div>
					</td>
					<td class="txt">
					<div align="right"><script>
			function callWindowPage()
			{
			 window.open('../pages/policies/privacy.html','wind','width=550,height=275,scrollbars=no,resizable=yes,menubar=no,top=200,left=100');	 
			}
		 	
			</script><a href="javascript:void(0)"
						onClick="window.open('../pages/policies/Terms & Conditions.html','','width=800,height=600,scrollbars=yes,resizable=yes,menubar=no,top=0,left=0')"
						class="link"> Terms &amp; Conditions </a><strong class="link">
					| </strong><a href="javascript:void(0)" onClick="callWindowPage()"
						class="link">Privacy Policy</a></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td height="28">
			<table width="980" border="0" align="center">
				<tr>
					<td width="133" valign="top" class="txt">
					<div align="left"><img
						src="../pages/portal/loginimages/siteseal_sf_3_h_l_m.gif" alt=""
						width="132" height="31"
						onclick="window.open('https://seal.starfieldtech.com/verifySeal?sealID=jK9ZWVummW3yGgtypDRq8Jph5lCiEkC0DJ1OYelJbk74WEArSN');"
						src="<%=request.getContextPath()%>/pages/portal/images/siteseal_sf_3_h_l_m.gif" /></div>
					</td>
					<td width="837" valign="top" class="link"><a
						href="http://www.starfieldtech.com/" class="link">SSL</a></td>
				</tr>
			</table>
		</tr>
	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
function callAvailability(){

			var email=trim(document.getElementById('paraFrm_emailId').value);
	 if(email==null)
			{
			email="";
			}
			
			 if(email==""){
		    	alert("Please enter the Email Id.");
		    	return false;
		    }else if(!validateEmail('paraFrm_emailId')){
				return false;
			}else{
				
				document.getElementById('paraFrm').action='CandidateRegistration_checkAvailUser.action';
			    document.getElementById('paraFrm').submit();
			
			}

}


function callLogin(){

	 
			try{
			var fName=trim(document.getElementById('paraFrm_firstName').value);
			var lName=trim(document.getElementById('paraFrm_lastname').value);
			var email=trim(document.getElementById('paraFrm_emailId').value);
			var chars=trim(document.getElementById('inCaptchaChars').value);
			var dob=trim(document.getElementById('paraFrm_dateOfBirth').value);		
			var mob=trim(document.getElementById('paraFrm_mobile').value);
		 	
			if(fName==null)
			{
			fName="";
			}
				if(lName==null)
			{
			lName="";
			}
				if(email==null)
			{
			email="";
			}
				if(chars==null)
			{
			chars="";
			}
				if(dob==null)
			{
			dob="";
			}
				if(mob==null)
			{
			mob="";
			}
			
		    if(fName==""){
		    	alert("Please enter the First Name.");
		    	document.getElementById('paraFrm_firstName').focus();
		    	return false;
		    }
		   
		    else if(lName==""){
		    	alert("Please enter the Last Name.");
		    	document.getElementById('paraFrm_lastname').focus();
		    	return false;
		    }else if(email==""){
		    	alert("Please enter the Email Id.");
		    	document.getElementById('paraFrm_emailId').focus();
		    	return false;
		    }else if(!validateEmail('paraFrm_emailId')){
		    	return false;
		    }else if(dob==""){
				alert("Please enter or select Date Of Birth. ");
				document.getElementById('paraFrm_dateOfBirth').focus();
				return false;		    
		    }else if(!validateDate1('paraFrm_dateOfBirth')){
     				 return false;
     		}else if(mob==""){
     			alert("Please enter the Mobile No.");
     			document.getElementById('paraFrm_mobile').focus();
				return false;		    
		    
     		}else if(chars==""){
		    	alert("Please enter the letters as they are displayed.");
		    	document.getElementById('inCaptchaChars').focus();
		    	return false;
		    }else{
				 	document.getElementById('submitBtn').disabled=true;
				document.getElementById('paraFrm').action='CandidateRegistration_save.action';
			    document.getElementById('paraFrm').submit();
				
			}
	
			}catch(e)
			{
				alert("Error  "+e);
			}
}



function closeWin(){
	window.close();
}



function validateDate1(fieldName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert("Date Of Birth should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

function resetFields() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='CandidateRegistration_reset.action';
		document.getElementById('paraFrm').submit();
}

</script>


