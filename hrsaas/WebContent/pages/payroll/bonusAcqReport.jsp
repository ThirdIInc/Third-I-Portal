<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="BonusAcqReport" id="paraFrm" validate="true" theme="simple">

	<table class="formbg" width="100%">
		<tr>
			<td width="100%" colspan="4" >
			
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"> Bonus Acquaintance Roll</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4"></td>
		</tr>

<tr>
			<td width="100%" colspan="4" >
				<table width="100%" cellpadding="0"
				cellspacing="0" class="formbg">
		<tr>
			<td width="20%" colspan="1">Select Bonus:</td>
			<td width="80%" colspan="3"><img
				src="../pages/images/search2.gif" height="18" align="absmiddle"
				width="18" theme="simple"
				onclick="javascript:callsF9(500,325,'BonusAcqReport_f9actionBonusParam.action'); ">
			<s:hidden label="%{getText('bonusCode')}" theme="simple"
				name="bonusAcq.bonusCode" /> <s:hidden label="%{getText('empId')}"
				theme="simple" name="bonusAcq.empId" /></td>
		</tr>

		<tr>
			<td width="20%">Bonus Period From:</td>
			<td width="30%"><s:textfield label="%{getText('bonusFrom')}"
				theme="simple" name="bonusAcq.bonusFrom" readonly="true" /></td>
			<td width="20%">Bonus Period To:</td>
			<td width="30%"><s:textfield label="%{getText('bonusTo')}"
				theme="simple" name="bonusAcq.bonusTo" readonly="true"/></td>

		</tr>


		<tr>
			<td width="20%">Bonus Type:</td>
			<td width="30%"><s:textfield label="%{getText('bonusType')}"
				theme="simple" name="bonusAcq.bonusType" readonly="true" /></td>
			<td width="20%">Bonus Days:</td>
			<td width="30%"><s:textfield label="%{getText('bonusDays')}"
				theme="simple" name="bonusAcq.bonusDays" readonly="true" /></td>
		</tr>

		<tr>
			<td width="20%">Employee Type:</td>
			<td width="30%"><s:textfield label="%{getText('bonusEmptype')}"
				theme="simple" name="bonusAcq.bonusEmptype" readonly="true" /></td>
			<s:hidden label="%{getText('typeCode')}" theme="simple"
				name="bonusAcq.typeCode" />

			<td width="20%">Select Paybill<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonusPaybill')}"
				theme="simple" name="bonusAcq.bonusPaybill" readonly="true" /> <img
				src="../pages/images/search2.gif" height="18" align="absmiddle"
				width="18" theme="simple"
				onclick="javascript:callsF9(500,325,'BonusAcqReport_f9actionBonusPaybill.action'); ">
			<s:hidden label="%{getText('paybillId')}" theme="simple"
				name="bonusAcq.paybillId" /> <s:hidden
				label="%{getText('empFlag')}" theme="simple" name="bonusAcq.empFlag" /></td>
		</tr>






		<tr align="center" >
		<td align="center" colspan="4">
			
			<!--<s:submit cssClass="pagebutton" action="BonusAcqReport_report" theme="simple" value=" Report "
					
				onclick="call()"/></td>-->
				
				<input type="button" class="pagebutton" 
				onclick="call()" theme="simple"
				value="  Report " />
				</td>
		</tr>
	</table>
	</td></tr></table>

</s:form>
<script>



	
  	function call(){
    	
     	
	  	
	  	  if( document.getElementById('bonusAcq.bonusCode').value=="")
  			{
  				alert("Please Select Bonus Code. ");
  				return false;
  			}
  			
	  if( document.getElementById('bonusAcq.bonusPaybill').value=="")
  			{
  				alert("Please Select Paybill. ");
  				return false;
  			}
  			
  		document.getElementById('paraFrm').target="_blank";
	  	document.getElementById('paraFrm').action='BonusAcqReport_report.action'
	  	document.getElementById('paraFrm').submit();
	  
	  	document.getElementById('paraFrm').target="main";
	  		document.getElementById('paraFrm').target="";
  			return true;
  	}
  
 
  
 
  
  
  	
  	
  
  	</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

