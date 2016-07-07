<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="EmpBonus" id="paraFrm" validate="true" theme="simple">

	<table class="formbg" width="100%">
		<tr>
			<td width="100%" colspan="4">
			<table width="100%" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Bonus  </strong></td>
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
			<td width="100%" colspan="4">
<table class="formbg" width="100%">
		<tr>
			<td width="20%" colspan="1">Select Bonus:</td>
			<td width="80%" colspan="3"><img
				src="../pages/images/search2.gif" height="18" align="absmiddle"
				width="18" 
				onclick="javascript:callsF9(500,325,'EmpBonus_f9actionBonusParam.action'); ">
			<s:hidden label="%{getText('bonusCode')}" theme="simple"
				name="empBonus.bonusCode" /> <s:hidden label="%{getText('empId')}"
				theme="simple" name="empBonus.empId" /></td>
		</tr>
		
		<tr>
			<td width="20%">Bonus Period From:</td>
			<td width="30%"><s:textfield label="%{getText('bonusFrom')}"
				theme="simple" name="empBonus.bonusFrom" readonly="true" /></td>
			<td width="20%">Bonus Period To:</td>
			<td width="30%"><s:textfield label="%{getText('bonusTo')}"
				theme="simple" name="empBonus.bonusTo" readonly="true"/></td>

		</tr>


		<tr>
			<td width="20%">Bonus Type:</td>
			<td width="30%"><s:textfield label="%{getText('bonusType')}"
				theme="simple" name="empBonus.bonusType" readonly="true" /></td>
			<td width="20%">Bonus Days:</td>
			<td width="30%"><s:textfield label="%{getText('bonusDays')}"
				theme="simple" name="empBonus.bonusDays" readonly="true" /></td>
		</tr>

		<tr>
			<td width="20%">Employee Type:</td>
			<td width="30%"><s:textfield label="%{getText('bonusEmptype')}"
				theme="simple" name="empBonus.bonusEmptype" readonly="true" /></td>
			<s:hidden label="%{getText('typeCode')}" theme="simple"
				name="empBonus.typeCode" />

			<td width="20%">Select Paybill<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonusPaybill')}"
				theme="simple" name="empBonus.bonusPaybill" readonly="true" /> <img
				src="../pages/images/search2.gif" height="18" align="absmiddle"
				width="18" 
				onclick="javascript:callsF9(500,325,'EmpBonus_f9actionBonusPaybill.action'); ">
			<s:hidden label="%{getText('paybillId')}" theme="simple"
				name="empBonus.paybillId" /> <s:hidden
				label="%{getText('empFlag')}" theme="simple" name="empBonus.empFlag" /></td>
		</tr>
		





		<tr>
		<td align="center" colspan="4">
			<s:submit cssClass="pagebutton" action="EmpBonus_view" theme="simple" value="  View  "
				onclick="return calView();" />
			&nbsp;
			<s:submit cssClass="pagebutton" action="EmpBonus_process" value=" Process "
				onclick="return proc();" theme="simple" />
			&nbsp;
			<s:if test="%{empBonus.empFlag}">
				<s:submit cssClass="pagebutton" action="EmpBonus_save" theme="simple" value=" Save "
					 />
			</s:if>
			<s:else></s:else>
			
			<s:submit cssClass="pagebutton" action="EmpBonus_reset"
				theme="simple" value="  Reset  " /></td>
		</tr>
	</table></td></tr></table>

 	<s:if test="flag">
	<table class="tableBody" width="100%">

		<tr>
			<td class="headercell" width="10%">Serial No.</td>
			<td class="headercell" width="10%">Employee Token</td>
			<td class="headercell" width="40%">Employee Name</td>
			<td class="headercell" width="15%">Bonus Eligible Days</td>
			<td class="headercell" width="25%">Bonus Amount</td>
		</tr>
		<%
		int i = 1;
		%>
		<s:iterator value="list" status="stat">
			<tr>
				<td nowrap="nowrap" width="10%" align="center"><%=i%></td>
				<td width="10%" align="center"><s:property value="%{cEmpToken}"
					id="cEmpToken1" /> <s:hidden value="%{cEmpId}" name="cEmpId" /></td>
				<td width="40%" align="left"><s:property value="%{cEmpName}"
					id="cEmpName1" /> <s:hidden value="%{cEmpName}" name="cEmpName" /></td>
				<td width="15%" align="center"><s:property
					value="%{cEmpBonusDays}" id="cEmpBonusDays" /> <s:hidden
					value="%{cEmpBonusDays}" name="cEmpBonusDays" /></td>
				<td width="25%" align="center"><s:textfield value="%{cAmount}"
					id="cAmount1" name="cAmount1" theme="simple" /> <s:hidden
					value="%{cAmount}" name="cAmount" /></td>

			</tr>
			<%
			i++;
			%>
		</s:iterator>



	</table>
</s:if>


</s:form>
<script>

  
  function calView(){
    if( document.getElementById('empBonus.bonusCode').value=="")
  			{
  				alert("Please Select Bonus Code. ");
  				return false;
  			}
  			
	  if( document.getElementById('empBonus.bonusPaybill').value=="")
  			{
  				alert("Please Select Bonus Paybill. ");
  				return false;
  			}
  			
	  	return true;	
  }
  
  
  	
  		function proc(){
  	
  	 if( document.getElementById('empBonus.bonusCode').value=="")
  			{
  				alert("Please Select Bonus Code. ");
  				return false;
  			}
  			
	  if( document.getElementById('empBonus.bonusPaybill').value=="")
  			{
  				alert("Please Select Paybill. ");
  				return false;
  			}
  			
  	   id=true;
  	   
	  	return true;
  		}
  
  
  </script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

