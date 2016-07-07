<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="IncomeTaxDeclaration" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Income Tax Declaration Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
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
					<td><input type="button" class="token"
						onclick="return validation();" value=" Generate Declaration " />
					<s:submit value=" Reset" cssClass="token"
						action="IncomeTaxDeclaration_reset" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
									<td><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red">*</font>:</td>
									<td colspan="3"><s:textfield
										name="taxDeclaration.empTokenNo" size="20" readonly="true" />
									<s:textfield name="taxDeclaration.empName" size="72"
										readonly="true" /><s:hidden name="taxDeclaration.taxCode" />
									<s:hidden name="taxDeclaration.empID" /> <s:if
										test="%{taxDeclaration.generalFlag}">
									</s:if> <s:else>
										<img src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="javascript:callsF9(500,325,'IncomeTaxDeclaration_f9empaction.action'); ">
									</s:else></td>
								</tr>


								<tr>
									<td><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
									<td><s:textfield size="25"
										name="taxDeclaration.department" readonly="true" /></td>

									<td><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
									<td><s:textfield size="25" name="taxDeclaration.center"
										readonly="true" /></td>
								</tr>
								<tr>
									<td><label  class = "set"  id="taxFinYearFrm" name="taxFinYearFrm" ondblclick="callShowDiv(this);"><%=label.get("taxFinYearFrm")%></label> <font color="red">*</font>:</td>
									<td><s:textfield size="25" name="taxDeclaration.fromYear"
										maxlength="4" onkeypress="return numbersOnly();"
										onblur="add()" /></td>

									<td><label  class = "set"  id="taxFinYearTo" name="taxFinYearTo" ondblclick="callShowDiv(this);"><%=label.get("taxFinYearTo")%></label> <font color="red">*</font>:</td>
									<td><s:textfield size="25" name="taxDeclaration.toYear"
										maxlength="4" readonly="true" /></td>
								</tr>
								<tr>
									<td colspan="3"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>







							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table></td></tr></table>
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
    var from = document.getElementById('paraFrm_taxDeclaration_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_taxDeclaration_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_taxDeclaration_toYear').value=x;
	  }
   }
    
function validation()
{

	var empId =document.getElementById('paraFrm_taxDeclaration_empName').value;
	  var from = document.getElementById('paraFrm_taxDeclaration_fromYear').value;
	  
	 var empName=document.getElementById('employee').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('taxFinYearFrm').innerHTML.toLowerCase();
	    
	    if(empId=="")
	    {
	    	alert("Please Select "+empName);
	    	return false;
	    }
	    
	     if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	    
	    callReport('IncomeTaxDeclaration_generateDeclaration.action');
	   // document.getElementById('paraFrm').action="IncomeTaxDeclaration_generateDeclaration.action";
	

	}
  
  		
  	  	</script>