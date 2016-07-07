
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
			

<s:form action="Company" id="paraFrm" validate="true" target="main"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Company
			  </strong></td>
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
					<td colspan="4"><s:if test="%{insertFlag}">
						<s:submit cssClass="edit" action="Company_save" 
							value="    Update    " onclick="return callsave();" />
					</s:if></td>
					<s:hidden name="company.compCode" />
					<td width="22%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
				</tr>

				
		</tr>

			</table>

			<label></label></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">






						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="comp" id="comp" ondblclick="callShowDiv(this);"><%=label.get("comp")%></label><font color="red">*</font>
							:</td>
							<td height="27"><s:textfield theme="simple" name="company.compName"
								size="40" maxlength="80" /></td>
						</tr>
						
						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="diaplayName" id="diaplayName" ondblclick="callShowDiv(this);"><%=label.get("diaplayName")%></label><font color="red">*</font>
							:</td>
							<td height="27"><s:textfield theme="simple" name="displayName"
								size="40" maxlength="50" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label> :</td>
							<td height="27"> <s:textarea theme="simple" name="company.address"
								cols="36" rows="3" /></td>
						</tr>


						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="city" id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label><font color="red">*</font>
							:</td>
							<td height="27"><s:hidden name="company.cityId" /> 
							<s:textfield  name="company.cityName"  readonly="true" /> 
							<img src="../pages/common/css/default/images/search2.gif"
								 width="16" height="15"
								onclick="javascript:callsF9(500,275,'Company_f9city.action');">
							</td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="pincode" id="pincode" ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label> :</td>
							<td height="27"><s:textfield theme="simple" name="company.Pincode"
								size="40" maxlength="12"  onkeypress="return numbersOnly();"/></td>
						</tr>


						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="telephone" id="telephone" ondblclick="callShowDiv(this);"><%=label.get("telephone")%></label> :</td>
							<td height="27"><s:textfield theme="simple" name="company.telephone"
								size="40" maxlength="13" onkeypress="return numbersOnly();"/></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="web" id="web" ondblclick="callShowDiv(this);"><%=label.get("web")%></label> :</td>
							<td height="27"><s:textfield theme="simple" name="company.website"
								size="40" maxlength="90" /></td>
						</tr>


						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="tan" id="tan" ondblclick="callShowDiv(this);"><%=label.get("tan")%></label> :</td>
							<td height="27"><s:textfield theme="simple" name="company.tanNo"
								size="40" maxlength="10" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="bank" id="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :</td>
							<td height="27"><s:textfield theme="simple" name="company.bankName"
								size="40" maxlength="90" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label  class = "set" name="logo" id="logo" ondblclick="callShowDiv(this);"><%=label.get("logo")%></label>:</td>
							<td height="27"><s:textfield size="40" name="uploadFileName"
								readonly="true" /> <input type="button" class="token"
								name="Browse" value="Browse"
								onclick="uploadFile('uploadFileName');" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
	</table>
</s:form>

<script>


 /*function uploadFile(fieldName) 
{
	var path="upload";
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
  */
  
  function uploadFile(fieldName) 
{
	var path="Logo/<%=session.getAttribute("session_pool")%>";
	//window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100,');
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		
		document.getElementById('paraFrm').target="main";
}
 </script>
<script type="text/javascript">


 
 function callsave()
 {
 
 try
 {
 var web =document.getElementById('paraFrm_company_website').value;
//alert("save "+document.getElementById('company.cityId').value);
var fieldName = ["paraFrm_company_compName","paraFrm_displayName","paraFrm_company_cityName"];
var lableName = ["comp","diaplayName","city"];
var flag = ['enter','enter','select'];
var website=document.getElementById('web').innerHTML.toLowerCase();

     if(!validateBlank(fieldName, lableName,flag)){
        return false;
       }
       
      var chkStart = web.substring(0,4);
		if(chkStart.length!=0)
		{
		if(!(chkStart=="www." || chkStart=="WWW."))
		{
			alert(website+" Should Start with www. ");
			return false;
		}
	}
      
 document.getElementById('paraFrm').target="main";            	   
  return true;   
  
  }
  
  catch(e)
  {
  alert(e);
  }	   
  
}

	


</script>






