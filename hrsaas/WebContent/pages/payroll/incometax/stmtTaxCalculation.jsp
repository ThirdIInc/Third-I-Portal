<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>


<s:form action="StmtCalculation" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Statement of Tax calculation</strong></td>
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

					<td><input type="button" class="token" onclick=" return validation();"
						value="  Report " /></td>
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
							<td><label class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
								<font color="red">*</font>:</td>
							<td colspan="3"><s:hidden name="empId" /> <s:textfield
								name="empTokenNo" size="20" readonly="true" /> <s:textfield
								name="empName" size="60" readonly="true" /> <img
								src="../pages/common/css/default/images/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'StmtCalculation_f9employee.action');">
							</td>
						</tr>
						<tr>

							<td colspan="1" width="20%"><label  class = "set" name="tax.FInYrFrm" id="tax.FInYrFrm" ondblclick="callShowDiv(this);"><%=label.get("tax.FInYrFrm")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield theme="simple"
								name="fromYear" maxlength="4" onkeypress="return numbersOnly();"
								onkeyup="add();" onblur="add();" /></td>

							<td colspan="1" width="20%"><label  class = "set" name="tax.FinYrTo" id="tax.FinYrTo" ondblclick="callShowDiv(this);"><%=label.get("tax.FinYrTo")%></label>:</td>
							<td><s:textfield theme="simple" name="toYear" maxlength="4"
								readonly="true" /></td>
						<tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table></td></tr></table>
</s:form>
<script>

function validation()
{
   	var year=document.getElementById('paraFrm_fromYear').value;	
	var name=document.getElementById('paraFrm_empName').value;	
	 var empName=document.getElementById('employee').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('tax.FInYrFrm').innerHTML.toLowerCase();
 
	
	
	if(name=="")
	{
		alert("please select "+empName);
		return false;
	}
	if(year=="")
	{
		alert("Please enter "+finYrFrm);	
		return false;
	}	
	document.getElementById("paraFrm").target="_blank";
	document.getElementById("paraFrm").action="StmtCalculation_taxReport.action";
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
}

function add()
{
    var from = document.getElementById('paraFrm_fromYear').value;
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 	 var x=eval(from) +1;
	 	 document.getElementById('paraFrm_toYear').value=x;
	}
}
</script>



