<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Form24" validate="true" id="paraFrm" theme="simple">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Form 24 Report</strong></td>
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

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>

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
							<td><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td>
								<s:textfield size="25" name="form24.divName"
								maxlength="4" readonly="true"
								onblur="add()" /><s:hidden name="form24.divCode" /> 
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'Form24_f9division.action');">
								</td>

						</tr>
						
						<tr>
							<td><label  class = "set"  id="assetFinYrFrm" name="assetFinYrFrm" ondblclick="callShowDiv(this);"><%=label.get("assetFinYrFrm")%></label> <font color="red">*</font>:</td>
							<td><s:textfield size="25" name="form24.fromYear"
								maxlength="4" onkeypress="return numbersonly(this);"
								onblur="add()" /></td>

							<td><label  class = "set"  id="assetFinYrTo" name="assetFinYrTo" ondblclick="callShowDiv(this);"><%=label.get("assetFinYrTo")%></label> <font color="red">*</font>:</td>
							<td><s:textfield size="25" name="form24.toYear"
								maxlength="4" onkeypress="return numbersonly(this);"
								readonly="true" /></td>
						</tr>
						
						<tr>
							<td width="25%"><label  class = "set"  id="assetSelQrtr" name="assetSelQrtr" ondblclick="callShowDiv(this);"><%=label.get("assetSelQrtr")%></label>:</td>
							<td colspan="2" width="40%"><s:select name="form24.quarter"
								theme="simple" headerKey="-1"
								list="#{'':'-------------Select-----------','June':'June','September':'September','December':'December','March':'March'}" />

							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td><label  class = "set"  id="assetSelSignAuthrty" name="assetSelSignAuthrty" ondblclick="callShowDiv(this);"><%=label.get("assetSelSignAuthrty")%></label><font color="red">*</font>:</td>
							<td>
								<s:textfield size="25" name="form24.signAuthName"
								maxlength="4" readonly="true"
								onblur="add()" /><s:hidden name="form24.signAuthEmpId" /> 
								<s:hidden name="form24.signAuthEmpDesg" />
								<s:hidden name="form24.signAuthEmpFather" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'Form24_f9signAuthority.action');">
								</td>

						</tr>
						
						<tr>
							<td width="25%"><label  class = "set"  id="assetStatusOfDed" name="assetStatusOfDed" ondblclick="callShowDiv(this);"><%=label.get("assetStatusOfDed")%></label>:</td>
							<s:hidden name="form24.empToken" />
							<td colspan="2" width="40%"><s:select name="form24.deductorStat"
								theme="simple" headerKey="-1"
								list="#{'C':'C  CentralGovt','O':'O  Other'}" />

							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>

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
	</table></td></tr></table>
</s:form>
<script>

function add()
   {
    var from = document.getElementById('paraFrm_form24_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_form24_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_form24_toYear').value=x;
	  }
   }
   
    function callView()
   {
   	  var divName =document.getElementById('paraFrm_form24_divName').value;
	  var from = document.getElementById('paraFrm_form24_fromYear').value;
	  var signAuth = document.getElementById('paraFrm_form24_signAuthName').value;
	  var quarter = document.getElementById('paraFrm_form24_quarter').value;
	 
	  var division=document.getElementById('division').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('assetFinYrFrm').innerHTML.toLowerCase();
	  var qurtr=document.getElementById('assetSelQrtr').innerHTML.toLowerCase();
	  var signAuthority=document.getElementById('assetSelSignAuthrty').innerHTML.toLowerCase();
	 
	
	 
	    if(divName=="")
	    {
	    	alert("Please Select "+division);
	    	return false;
	    }
	    
	    else if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	     else if(quarter=="")
	    {
	    	alert("Please Select "+qurtr);
	    	return false;
	    }
	    else if(signAuth=="")
	    {
	    	alert("Please Select "+signAuthority);
	    	return false;
	    }
	    
	    
	    else{
	   		//document.getElementById('paraFrm').target="_blank";	
			document.getElementById('paraFrm').action="Form24_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
			}
   
   }
   
   
   
</script>