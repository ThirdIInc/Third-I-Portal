<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
 var adverList = new Array();
</script>
<s:form action="PartnerChangePassword" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	 <tr>
	  <td colspan="3" width="100%">
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Change password </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>
		</td>
	  </tr>
	  
	  <tr>
			<td  width="70%"> <input type="button" value="  Save  " class="save" onclick="return callSave();">  </td>
			<td  width="30%"><div align="right"><font color="red">*</font>Indicates Required</div> </td>
		</tr>
		<tr>
			<td colspan="3" width="100%"> <s:hidden name="editFlag"/>  <s:hidden name="searchFlag"/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					 
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="1" cellspacing="2"><!--Table 1-->	
								 
								<tr> 
									<td width="20%">
									<label name="laPartnerName" id="laPartnerName" ondblclick="callShowDiv(this);"> <%=label.get("laPartnerName")%> </label>
									:</td>
									<td width="25%"> 
									       <s:textfield name="partnerName" size="25" readonly="true" />  
									 </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr>
								<tr> 
									<td width="20%">
										<label name="oldPassword" id="oldPassword" ondblclick="callShowDiv(this);"> <%=label.get("oldPassword")%> </label>
										<font color="red">*</font> :</td>
									<td width="25%"> <s:password name="oldPass" size="25" />  </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr> 
								
								<tr> 
									<td width="20%">
										<label name="newPassword" id="newPassword" ondblclick="callShowDiv(this);"> <%=label.get("newPassword")%> </label>
										<font color="red">*</font> :</td>
									<td width="25%"> <s:password name="newPass" size="25" />  </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr>  
								
								<tr> 
									<td width="20%">
										<label name="rePass" id="rePass" ondblclick="callShowDiv(this);"> <%=label.get("rePass")%> </label>
										<font color="red">*</font> :</td>
									<td width="25%"> <s:password name="reNewPass" size="25"  />  </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr> 
								
								<tr>
									<td  colspan="2" align="center"></td> 
								</tr> 
							</table><!--Table 1-->	
						</td>
					</tr>		
				</table><!--Table 2-->	
			</td><!--end of vacancy search-->	
		</tr> 
			 
	</table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
 <script>
  function callSave()
  {
   var oldPass = document.getElementById('paraFrm_oldPass').value;
   var newPass = document.getElementById('paraFrm_newPass').value;
   var reNewPass = document.getElementById('paraFrm_reNewPass').value;
    
   
	   if(oldPass=="")
	   {
	        alert("Please enter the "+document.getElementById('oldPassword').innerHTML.toLowerCase());
			document.getElementById('paraFrm_oldPass').focus();
			 return false; 	
	   }
	   
	    if(newPass=="")
	   {
	        alert("Please enter the "+document.getElementById('newPassword').innerHTML.toLowerCase());
			document.getElementById('paraFrm_newPass').focus();
			 return false; 	
	   }
	   
	  if(reNewPass=="")
	   {
	        alert("Please enter the "+document.getElementById('rePass').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reNewPass').focus();
			 return false; 	
	   }
	   
	    if(oldPass==newPass)
	   {
	   alert('Old password and new Password must not be same');
		document.getElementById('paraFrm_newPass').focus();
	    return false; 	
	   }
	   
	   if(newPass!=reNewPass)
	   {
	    alert('New Password and confirm password must be same');
		document.getElementById('paraFrm_reNewPass').focus();
	    return false; 	
	   } 
	   
	 document.getElementById('paraFrm').action="PartnerChangePassword_save.action"; 
     document.getElementById('paraFrm').submit();
	   
  }
 </script>