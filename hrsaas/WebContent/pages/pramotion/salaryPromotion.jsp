<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PromotionMaster" id="paraFrm" theme="simple">

<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader">Promotion</td>
		</tr>
		
		<tr>
			<td width="20%" colspan="1">Employee Code :</td>
			<td width="80%" colspan="3">
				<img src="../pages/images/search.gif" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'PromotionMaster_f9empaction.action');">
				 <s:textfield name="empName" size="90" value="%{empName}"
				theme="simple" readonly="true" /><s:hidden name="empCode"
				value="%{empCode}" /></td>
		</tr>
		<tr>
		<td width="20%" colspan="1">
			Effective Date :</td><td width="80%" colspan="1"><s:textfield
				maxLength="5" cssStyle="width:130" size="8" theme="simple"
				name="effDate" value="%{effDate}" /><s:a
				href="javascript:NewCal('effDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"   height="16" align="absmiddle"
					width="16">
			</s:a></td>
			</tr>
			<tr>
			<td width="100%" colspan="4" ></td>
			</tr>
	
</table>
<table>		

	<tr>
			<td width="60%" class="sectionHead" align="left">Salary Details</td>
		</tr>

		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td class="headercell" width="01%" >Code</td>
			<td class="headercell" width="35%">Salary Head</td>
			<td class="headercell" width="34%" >Current Amount</td>
			<td class="headercell" width="30%" >New Amount</td>
			
		</tr>
		
		<s:iterator value="salList">
		<tr>
						<td align="center" class="bodycell"><s:property
							value="salCode"/><s:hidden name="salCode" /></td>
						<td class="bodycell" align="center"><s:property
							value="salHead" /><s:hidden name="salHead" /></td>
						<td class="bodycell" align="center"><s:property
							value="curAmt" /><s:hidden name="curAmt" /></td>
						<td class="bodycell" align="center"><s:textfield
							name="newAmt" /><s:hidden name="newAmt" /></td>		
				
		</tr>
		</s:iterator>
		
		<tr align="center">
			<td colspan="4"><s:submit cssClass="pagebutton"  action="PromotionMaster_saveSalary"
				value="  Save  " />
				</td>
				</tr>
			
			
		
</table>		
</s:form>