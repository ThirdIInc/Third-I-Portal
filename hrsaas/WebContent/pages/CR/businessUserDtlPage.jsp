<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="AccountMaster" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="show" />
	1:<s:textfield name="hiddencode" />
	2:<s:textfield name="accountCode" />
<s:hidden name="myPage" id="myPage" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Client
					User list</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left"><input type="button" class="reset"
				value="   Back " onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
						
							<td width="25%"><label id="acc.id" name="acc.id"
						ondblclick="callShowDiv(this);"><%=label.get("acc.id")%></label>:<font color="red"></font></td>
					<td width="25%"><s:textfield name="accountId" size="25" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						
							<td width="15%" colspan="1" height="22"><label class="set"
								name="business.name" id="business.name"
								ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>:<font color="red"></font></td>
							<td width="75%" colspan="2" height="22"><s:textfield
								theme="simple" name="businessName" size="25" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td width="15%%" colspan="1"><label id="first.name"
								name="first.name" ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:textfield name="firstName" size="25"
								maxlength="100" /></td>

							<td width="15%"><label id="last.name" name="last.name"
								ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:textfield name="lastName" size="25"
								maxlength="100" /></td>
						</tr>
						<tr>

							<td width="15%"><label id="email.address"
								name="email.address" ondblclick="callShowDiv(this);"><%=label.get("email.address")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:if test="paracode==''">
								<s:textfield name="emailClientAddress" size="25" maxlength="150" />
							</s:if> <s:else>
								<s:textfield name="emailClientAddress" size="25" maxlength="150"
									readonly="true" cssStyle="background-color: #F2F2F2;" />
							</s:else></td>

							<td width="15%"><label id="password" name="password"
								ondblclick="callShowDiv(this);"><%=label.get("password")%></label>
							:<font color="red">*</font></td>
							<td width="15%"><s:password name="password" size="25"
								maxlength="25" /></td>
						</tr>
						<tr>

							<td width="15%"><label id="cell.number" name="cell.number"
								ondblclick="callShowDiv(this);"><%=label.get("cell.number")%></label>
							:</td>
							<td width="15%"><s:textfield name="cellNumber" size="25"
								maxlength="12" onkeypress="return numbersOnly();" /></td>

						</tr>
						<tr>
							<td width="15%"><label class="set" id="sub.account"
								name="sub.account" ondblclick="callShowDiv(this);"><%=label.get("sub.account")%></label>:<font
								color="red">*</font></td>

							<td colspan="3" nowrap="nowrap"><s:hidden name="parentClientCode" /> <s:textarea
								cols="90" rows="1" theme="simple" readonly="true"
								name="parentClientName" /> <img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_parentClientName',350,250,'AccountMaster_f9ClientParent.action?accountParentCode=<s:property value='accountCode'/>',event,'false','no','right')"></td>
						</tr>
						<tr>
							<td width="15%" height="22" class="formtext"><label
								name="is.active" class="set" id="is.active"
								ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							:</td>
							<td><s:checkbox name="isClientActive"
								onclick="showCalculatedBox();" /> <s:hidden
								name="hiddenClientActive" id="hiddenClientActive" /></td>
							<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>Note: User's details will be send from E-mail Id.</td>
				</tr>

			</table>
			</td>
		</tr>
		--><tr>
			<td>
			<table width="100%">
				<tr>
					<td>
					<table width="100%">

						<tr>
							<td colspan="4" align="center"><input type="button"
								class="add" value=" Generate User" onclick="return callAdd();" />
							<input type="button" class="reset" value="   Reset "
								onclick="return resetFun();" /></td>
						</tr>

						<tr>
							<td colspan="10">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">

								<tr>
									<td colspan="10">
									<table width="100%" border="0" align="center" cellpadding="2"
										cellspacing="1">
										<tr>
											<%
												int totalPage = 0;
													int pageNo = 0;
											%>
											<s:if test="modeLength">
												<td colspan="10" width="20%" class="text_head"><strong
													class="forminnerhead">Client User Details : </strong></td>

												<td id="ctrlShow" width="80%" align="right" class=""><b>Page:</b>
												<%
													totalPage = (Integer) request.getAttribute("totalPage");
															pageNo = (Integer) request.getAttribute("pageNo");
												%> <a href="#"
													onclick="callPage('1', 'F', '<%=totalPage%>', 'AccountMaster_callForBusinessUserPaging.action');">
												<img title="First Page" src="../pages/common/img/first.gif"
													width="10" height="10" class="iconImage" /> </a>&nbsp; <a
													href="#"
													onclick="callPage('P', 'P', '<%=totalPage%>', 'AccountMaster_callForBusinessUserPaging.action');">
												<img title="Previous Page"
													src="../pages/common/img/previous.gif" width="10"
													height="10" class="iconImage" /> </a> <input type="text"
													name="pageNo" id="pageNo" size="3" value="<%=pageNo%>"
													maxlength="10"
													onkeypress="callPageText(event, '<%=totalPage%>', 'AccountMaster_callForBusinessUserPaging.action');return numbersOnly();" />
												of <%=totalPage%> <a href="#"
													onclick="callPage('N', 'N', '<%=totalPage%>', 'AccountMaster_callForBusinessUserPaging.action')">
												<img title="Next Page" src="../pages/common/img/next.gif"
													class="iconImage" /> </a>&nbsp; <a href="#"
													onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AccountMaster_callForBusinessUserPaging.action');">
												<img title="Last Page" src="../pages/common/img/last.gif"
													width="10" height="10" class="iconImage" /> </a></td>
											</s:if>
										</tr>
									</table>
									</td>


								</tr>
								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable">
										<tr class="td_bottom_border">

											<td class="formth" width="3%"><label name="srno"
												id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
											<td class="formth" width="8%"><label name="First.Name"
												id="First.Name" ondblclick="callShowDiv(this);"><%=label.get("First.Name")%></label></td>
											<td class="formth" width="8%"><label name="Last.Name"
												id="Last.Name" ondblclick="callShowDiv(this);"><%=label.get("Last.Name")%></label></td>
											<!--<td class="formth" width="8%"><label
												name="business.name" id="business.name"
												ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>
											</td>
											-->
											<td class="formth" width="25%"><label name="sub.name"
												id="sub.name" ondblclick="callShowDiv(this);"><%=label.get("sub.name")%></label>
											</td>
											<td class="formth" width="4%"><label name="Is.Active"
												id="Is.Active" ondblclick="callShowDiv(this);"><%=label.get("Is.Active")%></label></td>
											<!--<td class="formth" width="11%"><label name="created.on"
												id="created.on" ondblclick="callShowDiv(this);"><%=label.get("created.on")%></label></td>

											--><td class="formth" width="11%"><label
												name="last.modified" id="last.modified"
												ondblclick="callShowDiv(this);"><%=label.get("last.modified")%></label></td>


											<td class="formth" nowrap="nowrap" width="5%">Edit |
											Delete</td>
										</tr>
										<%
											int i = pageNo * 5 - 5;
										%>

										<%
											int count1 = 0;
										%>
										<s:iterator value="clientUserList" status="stat">

											<tr class="sortableTD">
												<td class="sortableTD" width="3%"><s:hidden
													name="ittClientUserId" /> <s:hidden name="ittAccountCode" /><%=++i%>
												</td>
												<td width="8%"><s:property value="ittFirstName" /></td>
												<td class="sortableTD" width="8%"><s:property
													value="ittLastName" />&nbsp;</td>
												<!--<td class="sortableTD" width="8%"><s:property
													value="ittAccountName" /></td>
												-->
												<td  class="sortableTD" width="25%">
											<!--	
												<s:hidden name="ittSubAccountId"/>
							<s:textarea cols="50" rows="2" theme="simple" cssStyle=""	readonly="true" name="ittSubAccountName"  />
												
												-->
												
												<s:hidden
													name="ittSubAccountId" /><s:property
													value="ittSubAccountName" />
													
													
													
													</td>
												<td class="sortableTD" width="4%"><s:property
													value="ittIsActive" />&nbsp;</td>
												<!--<td class="sortableTD" width="11%"><s:property
													value="ittCreatedOn" />&nbsp;</td>

												--><td class="sortableTD" width="11%"><s:property
													value="ittModifiedOn" />&nbsp;</td>

												<td class="sortableTD" width="5%" id="ctrlShow"
													align="center"><input type="button" class="rowEdit"
													onclick="callForEdit('<s:property value="ittClientUserId"/>')" />
												| <input type="button" class="rowDelete"
													onclick="callDelete('<s:property value="ittClientUserId"/>')" />
												<!--
							 
							 <%count1++; %>
							 
						
						--></td>
											</tr>
										</s:iterator>
									</table>
									<s:if test="modeLength"></s:if> <s:else>
										<table width="100%">
											<tr>
												<td align="center"><font color="red">No Data To
												Display</font></td>
											</tr>
										</table>
									</s:else> <s:hidden name="paracode" /></td>
								</tr>


							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


	</table>
</s:form>


<script type="text/javascript">
onLoad();
	function onLoad(){
		var checkValue = document.getElementById('hiddenClientActive').value;
 		var parentCheckboxVar = document.getElementById('paraFrm_isClientActive').checked;
 		if((parentCheckboxVar == false) && checkValue=='L'){
	 		document.getElementById('hiddenClientActive').value="L";
			document.getElementById('paraFrm_isClientActive').checked =false;
 		}else{
 		
	 		document.getElementById('paraFrm_isClientActive').checked =true;
			document.getElementById('hiddenClientActive').value="S";
 		}
 		showCalculatedBox();
	}
	
	function showCalculatedBox(){
	
		if(document.getElementById('paraFrm_isClientActive').checked ==true){
			
			document.getElementById('hiddenClientActive').value="S";
		} else {
		
			
			document.getElementById('hiddenClientActive').value="L";
		}
	}	
	
	function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccountMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
		function callAdd(){
	try{
	
	
	var qtt=trimData(document.getElementById('paraFrm_firstName').value);
		
		if(document.getElementById('paraFrm_firstName').value==''){
			 alert ("Please enter " +document.getElementById('first.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_firstName').focus();
			return false;
		}
		
		if(qtt==''){
			 alert ("Please enter " +document.getElementById('first.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_firstName').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_lastName').value==''){
			 alert ("Please enter " +document.getElementById('Last.Name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_lastName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_emailClientAddress').value==''){
			 alert ("Please enter " +document.getElementById('email.address').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_emailClientAddress').focus();
			return false;
		}
		if(document.getElementById('paraFrm_password').value==''){
			 alert ("Please enter " +document.getElementById('password').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_password').focus();
			return false;
		}
		
		
		if(document.getElementById('paraFrm_parentClientCode').value==''){
			 alert ("Please enter " +document.getElementById('sub.account').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_parentClientName').focus();
			return false;
		}
		
		
		try
			{
				if(!document.getElementById('paraFrm_emailClientAddress').value==''){
					var fields=["paraFrm_emailClientAddress"];
		    		var labels=["email.address"];
		    		var flag = ["enter"];
		 	 		if(!validateBlank(fields,labels,flag))
		    		 return false;
			 
			  		 if(!validateEmailAPS('paraFrm_emailClientAddress')){
					 	return false;
					 }
				 }
				 
			}catch(e){
			//alert("Exception occured in draft function : "+e);
			}
	
	}catch (e)
	{
	alert(e);
	}
		
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="AccountMaster_addClientUserLstDtl.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
		
	}
		function callDelete(clientUserId){
		
				var conf=confirm("Are you sure to delete this record?");   
		   		if(conf) {
		   		  		  
				   	document.getElementById("paraFrm").action="AccountMaster_deleteClientUserLstDtl.action?clientUserId="+clientUserId;
				    document.getElementById('paraFrm').target = "_self";
	   				 document.getElementById("paraFrm").submit();
			   }else {
			   		return false;
			   
			   }
   		}
   		
   		function callForEdit(clientUserId){
			    try{
				   	document.getElementById("paraFrm").action="AccountMaster_editClientUserLstDtl.action";
				  	document.getElementById('paraFrm_paracode').value=clientUserId;
				  
				  	document.getElementById("paraFrm").target="main";
				    document.getElementById("paraFrm").submit();
			   	}catch (e)
				{
				alert(e);
				}
		}
		
		function resetFun(){
		onLoad(); 
			document.getElementById('paraFrm_firstName').value="";
			document.getElementById('paraFrm_lastName').value="";
			document.getElementById('paraFrm_emailClientAddress').value="";
			document.getElementById('paraFrm_cellNumber').value="";
			document.getElementById('paraFrm_parentClientCode').value="";
			document.getElementById('paraFrm_parentClientName').value="";
			document.getElementById('paraFrm_password').value="";
			document.getElementById('hiddenClientActive').value="";
			
			var checkValue = document.getElementById('hiddenClientActive').value;
	 		var parentCheckboxVar = document.getElementById('paraFrm_isClientActive').checked;
	 		if((parentCheckboxVar == false) && checkValue=='L'){
		 		document.getElementById('hiddenClientActive').value="L";
				document.getElementById('paraFrm_isClientActive').checked =false;
	 		}else{
		 		document.getElementById('paraFrm_isClientActive').checked =true;
				document.getElementById('hiddenClientActive').value="S";
	 		}
			
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
	
	// Application In-Process List Begins
	function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNo').value;	
		actPage = document.getElementById('myPageInProcess').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNo').value = actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNo').value=actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNo').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageInProcess').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNo').value;
		 	var actPage = document.getElementById('myPageInProcess').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNo').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNo').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageInProcess').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Application In-Process List Ends
	
	
		</script>
