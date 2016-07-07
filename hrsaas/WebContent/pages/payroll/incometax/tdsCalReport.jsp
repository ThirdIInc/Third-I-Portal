<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TdsCalReport" id="paraFrm" theme="simple">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">TDS Report </strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	<table  width="100%" border="0" cellpadding="0" cellspacing="0">
		
		<tr>
			<td><s:if test="%{tdsCalReport.viewFlag}"><input type="button" class="search" theme="simple" value="Report"
				onclick="chk();" /> <s:submit cssClass="reset" theme="simple"
				action="TdsCalReport_reset" name="Reset" value="Reset" /></s:if></td>
			<td width="22%">
			<div align="right"><font color="red">*</font>Indicates Required</div>
			</td>
		</tr>
	</table></td></tr>
	<tr>
	<td colspan="4" width="100%">
	<table width="100%" class="formbg">
		<tr align="center">
			<td colspan="4">

			<table width="100%">
				<tr>

					<td width="22%" ><label  class = "set" name="taxFrmYr" id="taxFrmYr" ondblclick="callShowDiv(this);"><%=label.get("taxFrmYr")%></label><font color="red">*</font>:</td>
					<td width="25%" ><s:textfield size="25" maxlength="4" name="tdsCalReport.fromYear"
						onkeypress="return numbersonly(this);" onblur="add()" /></td>
					<td width="22%"><label  class = "set" name="taxToYr" id="taxToYr" ondblclick="callShowDiv(this);"><%=label.get("taxToYr")%></label><font color="red">*</font>:</td> 
					<td width="25%" ><s:textfield size="25"
						maxlength="4" name="tdsCalReport.toYear"
						onkeypress="return numbersonly(this);" readonly="true" /></td>
				</tr>
					<tr>
					<td width="22%"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
						<td><s:textfield size="25" name="tdsCalReport.divisionName"
							size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TdsCalReport_f9Div.action');"></td>
					<td width="22%"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
						<td><s:textfield size="25" name="tdsCalReport.branchName"
							size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TdsCalReport_f9Branch.action');"></td>
					</tr>
				<tr>
						<td width="20%"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
						<td><s:textfield size="25" name="tdsCalReport.deptName"
							size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TdsCalReport_f9Dept.action');"></td>
						<td width="22%"><label  class = "set" name="employee.type" id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
						<td><s:textfield size="25" name="tdsCalReport.typeName"
							size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TdsCalReport_f9type.action');"></td>
					</tr>
				
				
					<tr>
						<td width="20%"><label  class = "set" name="taxSelctPayGrp" id="taxSelctPayGrp" ondblclick="callShowDiv(this);"><%=label.get("taxSelctPayGrp")%></label> :</td>
						<td><s:textfield size="25" name="tdsCalReport.payBillNo"
							size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'TdsCalReport_f9payBill.action');"></td>
					</tr>
				
			</table>
			<s:hidden name="emptypeFlag" /> 
			<s:hidden name="paybillFlag" /> 
			<s:hidden name="branchFlag" /> 
			<s:hidden name="departmentFlag" /> 
			<s:hidden name="divisionFlag" /></td>
			<s:hidden name="tdsCalReport.branchCode" />
			<s:hidden name="tdsCalReport.deptCode" />
			<s:hidden name="tdsCalReport.divisionCode" />
			<s:hidden name="tdsCalReport.typeCode" />
	</table></td></tr></table>
</s:form>
<script>

	function chk()
	{
		 var from = document.getElementById('paraFrm_tdsCalReport_fromYear').value;
	     var payBillNo = document.getElementById('paraFrm_tdsCalReport_payBillNo').value;
	    
	     var finYrFrm=document.getElementById('taxFrmYr').innerHTML.toLowerCase(); 
	    
	  	if(from==""){
	  		alert("Please enter "+finYrFrm);
	  		return false;
	  	}
	  	 if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	return false;
	    }
	    
	  	
		document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="TdsCalReport_report.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";		
	}
	
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
    var from = document.getElementById('paraFrm_tdsCalReport_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_tdsCalReport_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_tdsCalReport_toYear').value=x;
	  }
   }

</script>