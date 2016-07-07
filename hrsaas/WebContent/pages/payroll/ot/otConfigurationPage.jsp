<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="OtConfiguration" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
<s:hidden name="otConfigID"/>

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">OT
					Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td valign="bottom" class="txt"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">

				<tr>

					<td width="25%"><label name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
					<td width="25%"><s:hidden name="divisionID" /> <s:textfield
						name="divisionName" readonly="true" size="30"
						cssStyle="background-color: #F2F2F2;" /> 
						<s:if test="otConfigID==''">
						<img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'OtConfiguration_f9Division.action');"></s:if>
					</td>
					<td width="25%"></td>
					<td width="25%"></td>
			</table>
			</td>
		</tr>
		<!-- Select Overtime duration criteria: -->
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Select Overtime duration criteria:</strong></td>
				</tr>
				<tr>
					<td width="25%"><label class="set" name="actual.hours.worked.Shift.hours"
						id="actual.hours.worked.Shift.hours" ondblclick="callShowDiv(this);"><%=label.get("actual.hours.worked.Shift.hours")%></label><font
						color="red"></font>:</td>

					<td width="25%"><s:checkbox name="actualHoursWorkedShiftHoursFlag"
						onclick="return callOptions('actualHoursWorkedShiftHoursFlag');"/> </td>

					<td width="25%"></td>
					<td width="25%"></td>


				</tr>

				<tr>
					<td width="25%"><label class="set" name="actual.out.time.shift.out.time"
						id="actual.out.time.shift.out.time" ondblclick="callShowDiv(this);"><%=label.get("actual.out.time.shift.out.time")%></label><font
						color="red"></font>:</td>

					<td width="25%"><s:checkbox name="actualOutTimeShiftOutTimeFlag"
						onclick="return callOptions('actualOutTimeShiftOutTimeFlag');"/></td>

					<td width="25%"></td>
					<td width="25%"></td>

				</tr>

				<tr>
					<td height="22" class="formtext" colspan="1"><label
						name="ot.hours.round.off.options" id="ot.hours.round.off.options"
						ondblclick="callShowDiv(this);"><%=label.get("ot.hours.round.off.options")%></label>:</td>
					<td height="22" class="formtext" colspan="1"><s:select
						name="otHoursRoundOffOptions"
						list="#{'':'-------------Select------------','15':'Round to 15 Min','30':'Round to 30 Min','60':'Round to 60 Min','No':'No Round'}"
						cssStyle="width:178" /></td>

				</tr>
				
				<tr>
						 <td colspan="1" width="25%">
						 	<label name="ot.hourly.rate.formula" id="ot.hourly.rate.formula" ondblclick="callShowDiv(this);"><%=label.get("ot.hourly.rate.formula")%></label>:<font color="red">*</font>
						 </td>
						 
						 <td width="25%">
							 <s:textfield name="calOtHourlyRateFormula" size="30" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
						 </td>
						 <td width="25%">
							 <input type="button" class="token" name="calculateFormula" id="paraFrm_calculateFormula" value="Formula" 
								onclick="callFormulaBuilder('paraFrm_calOtHourlyRateFormula');"/>		
						 </td>
						 <td width="25%"></td>
					</tr>

				<!--<tr>
					<td width="25%"><label class="set" name="enable.single.OT.double.OT.system"
						id="enable.single.OT.double.OT.system" ondblclick="callShowDiv(this);"><%=label.get("enable.single.OT.double.OT.system")%></label><font
						color="red"></font> :</td>

					<td width="25%"><s:checkbox name="actualAmtPensionPfFlag"
						onclick="return callPensionPf('actualAmtPensionPfFlag');" /> <s:hidden
						name="actualAmtPensionPf" /></td>

					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
			-->
				<tr>
						 <td colspan="1" width="25%">
						 	<label name="weekly.ot.hourly.rate.formula" id="weekly.ot.hourly.rate.formula" ondblclick="callShowDiv(this);"><%=label.get("weekly.ot.hourly.rate.formula")%></label>:<font color="red"></font>
						 </td>
						 
						 <td width="25%">
							 <s:textfield name="calWeeklyOtHourlyRateFormula" size="30" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
						 </td>
						 <td width="25%">
							 <input type="button" class="token" name="calculateFormula" id="paraFrm_calculateFormula" value="Formula" 
								onclick="callFormulaBuilder('paraFrm_calWeeklyOtHourlyRateFormula');"/>		
						 </td>
						 <td width="25%"></td>
					</tr>
			<tr>
					<td width="25%"><label class="set" name="double.ot"
						id="double.ot" ondblclick="callShowDiv(this);"><%=label.get("double.ot")%></label>:</td>
					<td width="25%" nowrap="nowrap"><s:checkbox
						name="doubleOtFlag" id="doubleOtFlag" onclick="hideDivTable();" />
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
			<tr id="doubleOTTableId">
						 <td colspan="1" width="25%">
						 	<label name="double.ot.hourly.rate.formula" id="double.ot.hourly.rate.formula" ondblclick="callShowDiv(this);"><%=label.get("double.ot.hourly.rate.formula")%></label>:<font color="red">*</font>
						 </td>
						 
						 <td width="25%">
							 <s:textfield name="calDoubleOtHourlyRateFormula" size="30" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
						 </td>
						 <td width="25%">
							 <input type="button" class="token" name="calculateFormula" id="paraFrm_calculateFormula" value="Formula" 
								onclick="callFormulaBuilder('paraFrm_calDoubleOtHourlyRateFormula');"/>		
						 </td>
						 <td width="25%"></td>
					</tr>
			
			</table>
			</td>
		</tr>


		<tr>
			<td valign="bottom" class="txt"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>

<script>
hideDivTable();
	function callOptions(type){
		if(type!="actualHoursWorkedShiftHoursFlag"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_actualHoursWorkedShiftHoursFlag').checked =false;
				
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_actualOutTimeShiftOutTimeFlag').checked =false;
				
			}
		}
}

	function saveFun(){

		var divNm   =document.getElementById("paraFrm_divisionID").value;
			if(divNm==""){
		 	alert("Please select the Division");
		 	document.getElementById('paraFrm_divisionName').focus();
		 	return false;
			}
			
		var calCulatedOtComponentsVar = trim(document.getElementById('paraFrm_calOtHourlyRateFormula').value);
		
		if (calCulatedOtComponentsVar=="") {
			alert("Please select OT Hourly Rate formula");
			document.getElementById('paraFrm_calOtHourlyRateFormula').focus();
			return false;
		} 
		
		
		if(document.getElementById('doubleOtFlag').checked){
			var doubleOt   =document.getElementById('paraFrm_calDoubleOtHourlyRateFormula').value;
			if(doubleOt==""){
		 	alert("Please select Double OT Hourly Rate formula");
		 	document.getElementById('paraFrm_calDoubleOtHourlyRateFormula').focus();
		 	return false;
			}
		}
		
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'OtConfiguration_saveConfiguration.action';
			document.getElementById('paraFrm').submit();
		
			
	}

	function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtConfiguration_reset.action';
		document.getElementById('paraFrm').submit();
	}

	function editFun() {
	
		return true;
	}

	function backFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtConfiguration_input.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'OtConfiguration_deleteSelectedRecords.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function hideDivTable(){
		if(document.getElementById('doubleOtFlag').checked){
			document.getElementById('doubleOTTableId').style.display = '';
			///document.getElementById('paraFrm_calDoubleOtHourlyRateFormula').value = ''; 
			
		}
		else{
			document.getElementById('doubleOTTableId').style.display = 'none';
			///document.getElementById('paraFrm_calDoubleOtHourlyRateFormula').value = ''; 
			document.getElementById('doubleOtFlag').value = ''; 
		}
	}
 
</script>