<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	

<table class="formbg" width="100%">

		
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">PF Configuration</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="78%">	
	   
	    </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
            <tr>
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Define PF Type applicability for Organization : </strong>  </td>
						</tr>
			
		<tr>
			<td width="20%">EPF :</td>
			<td width="30%" colspan="1"><s:checkbox theme="simple" name="EPFflag" /></td>
			<td width="20%">GPF :</td>
			<td width="30%" colspan="1"><s:checkbox theme="simple" name="GPFflag" /></td>
		
		</tr>
		
		<tr>
			<td width="20%">VPF :</td>
			<td width="30%" colspan="1"><s:checkbox theme="simple" name="VPFflag" /></td>
			<td width="20%">PF Trust :</td>
			<td width="30%" colspan="1"><s:checkbox theme="simple" name="PFTflag" /></td>
		
		</tr>
		<tr>
			<td width="100%" colspan="4" align="center"><input type="button" class="token" value='Submit' onclick="callSubmit();"> </td>
		</tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
    
  </table></td></tr></table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">
function callSubmit(){
	document.getElementById("paraFrm").action="PFParameter_savePFApplicability.action";
	document.getElementById("paraFrm").submit();
}

</script>		
		
