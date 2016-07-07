<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TaxChallan" validate="true" id="paraFrm" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form
					16</strong></td>
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

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>

							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>

				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td><label class="set" id="employee" name="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									<font color="red">*</font>:</td>
									<td colspan="3"><s:hidden name="form16.empID" /> <s:textfield
										name="form16.empTokenNo" size="20" readonly="true" /> <s:textfield
										name="form16.empName" size="67" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'Form16_f9empaction.action');">
									</td>

								</tr>


								<tr>
									<td><label class="set" id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td><s:textfield size="25" name="form16.rank"
										readonly="true" /></td>

									<td><label class="set" id="branch" name="branch"
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td><s:textfield size="25" name="form16.center"
										readonly="true" /></td>
								</tr>
								<tr>
									<td><label class="set" id="taxFinYrFrm" name="taxFinYrFrm"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrFrm")%></label><font
										color="red">*</font>:</td>
									<td><s:textfield size="25" name="form16.fromYear"
										maxlength="4" onkeypress="return numbersonly(this);"
										onblur="add()" /></td>

									<td><label class="set" id="taxFinYrTo" name="taxFinYrTo"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrTo")%></label>
									<font color="red">*</font>:</td>
									<td><s:hidden name="form16.signAuthEmpId" /> <s:hidden
										name="form16.signAuthEmpDesg" /> <s:hidden
										name="form16.signAuthEmpFather" /> <s:hidden
										name="form16.signAuthName" /><s:textfield size="25"
										name="form16.toYear" maxlength="4"
										onkeypress="return numbersonly(this);" readonly="true" /></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>

								<!--  <tr>
							<td>Select Signing Authority<font color="red">*</font>:</td>
							<td>
							    <s:hidden name="form16.signAuthtoken" />
								<s:textfield size="25" name="form16.signAuthName"
								maxlength="4" onkeypress="return numbersonly(this);"
								onblur="add()" />
								<img
								src="../pages/images/search.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'Form16_f9signAuthority.action');">
								</td>

						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>-->

								<tr align="center">

									<td colspan="4"><input type="button" class="token"
										theme="simple" value="   Submit  " onclick="return callView()" /></td>
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

<script>
 
  
   
   function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}
	
	
   function add()
   {
    var from = document.getElementById('paraFrm_form16_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_form16_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_form16_toYear').value=x;
	  }
   }
   
   
   function callView()
   {
   	  var empId =document.getElementById('paraFrm_form16_empName').value;
	  var from = document.getElementById('paraFrm_form16_fromYear').value;
	  var empName=document.getElementById('employee').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase(); 
	    
	    if(empId=="")
	    {
	    	alert("Please Select "+empName);
	    	return false;
	    }
	    
	    else if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	    
	    else{
	   document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="Form16_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
			}
   
   }
   
   
	   
   </script>