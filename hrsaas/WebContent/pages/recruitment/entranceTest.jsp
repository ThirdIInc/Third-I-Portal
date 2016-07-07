<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="EntranceTestMaster" method="post" name="EntranceTestMaster"
	validate="true" id="paraFrm" theme="simple">
<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" align="center"  colspan="6" class="pageHeader">Entrance Test</td>
		</tr>
		<tr align="center">
		
			<td colspan="4" width="100%" align="left" valign="middle" class="buttontr">
			<s:submit cssClass="pagebutton"   action="EntranceTestMaster_save"
				theme="simple" value="   Save   " />
				 <s:submit cssClass="pagebutton"  
				action="EntranceTestMaster_reset" theme="simple" value="  Reset  " />
			 <s:submit cssClass="pagebutton"  
				action="EntranceTestMaster_delete" theme="simple" value=" Delete "  />
			<input type="button" theme="simple"  class="pagebutton" value=" Report "  />
				
				
				 </td>
		</tr>
		<tr>
					<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
					
				<tr>
			
					<td  width="20%" colspan="1">Entrance Code:</td>
					<td width="80%" colspan="3"> 

					<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
					onclick="javascript:callsF9(500,325,'CashVoucherMaster_f9loanNo.action');">
					
				</td>
				</tr>
			
				<tr>
					<td colspan="1"  width="20%" >Candidate Code:
					<td colspan="1"  width="30%"><img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
					onclick="javascript:callsF9(500,325,'CashVoucherMaster_f9loanNo.action');">&nbsp;<s:textfield   size="100" 
						theme="simple" name="empName"  value="%{empName}"/></td>
				
				</tr>
				<tr>
					<td colspan="1"  width="20%" >Test Date:
					<td colspan="1"  width="30%"><s:textfield   size="25" 
						theme="simple" name="test"  value="%{test}"/><s:a
				href="javascript:NewCal('test','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle"
					width="16"></s:a></td>
				
				</tr>
	
					<tr>
			<td colspan="4">&nbsp</td>

		</tr>
	</table>

<table class="bodyTable" width="100%">

		<tr>
			<td width="20%" class="pageHeader" align="left">Candidate Entrance Exam</td>
		</tr>
	<tr>
			<td colspan="4">&nbsp</td>

		</tr>

		<tr>
			<td class="headercell" width="35%"> Test Name</td>
			<td class="headercell" width="20%">Total Marks</td>
			<td class="headercell" width="20%">Passing Marks</td>
			<td class="headercell" width="15%">Marks Obtained</td>
			<td class="headercell" width="10%">Rating</td>
		</tr>
		<tr>
        <td width="35%" class="bodycell"><span style="font-weight: 500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Core Java</span></td>
        <td width="20%"class="bodycell"><span style="font-weight: 500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;100</span></td>
         <td width="10%"class="bodycell"><span style="font-weight: 400">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Good</span></td>
         <td width="20%"class="bodycell"><span style="font-weight: 400">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;60</span></td>
         <td width="15%"class="bodycell">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;75</td>
        
      </tr>
				
		<tr>
			<td colspan="4">&nbsp</td>

		</tr>
		</table>
				</s:form>
				
				
				<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>