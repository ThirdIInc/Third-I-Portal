<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="WelfareInformation"  id="paraFrm"
	 theme="simple">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Welfare
					Information </strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>

					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
							<td><b><label class="set" name="welfare.details"
								id="welfare.details" ondblclick="callShowDiv(this);"><%=label.get("welfare.details")%></label></b>:</td>
						</tr>
				<!-- facility.details -->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td><b><label class="set" name="facility.details"
								id="facility.details" ondblclick="callShowDiv(this);"><%=label.get("facility.details")%></label></b>:</td>
						</tr>

						<tr>
							<td><label class="set" name="drinking.water.facility"
								id="drinking.water.facility" ondblclick="callShowDiv(this);"><%=label.get("drinking.water.facility")%></label><font
								color="red"></font> :</td>

							<td><s:checkbox
								name="drinkWaterFacilityFlag" onclick="showDrinkWaterFacilityFlag();" /> </td>

							<td><label class="set" name="child.minding.facility"
								id="child.minding.facility" ondblclick="callShowDiv(this);"><%=label.get("child.minding.facility")%></label><font
								color="red"></font> :</td>

							<td><s:checkbox
								name="childMindingFacilityFlag" onclick="showChildMindingFacilityFlag();" /></td>

						</tr>
						<tr>
							<td><label class="set" name="locker.facility"
								id="locker.facility" ondblclick="callShowDiv(this);"><%=label.get("locker.facility")%></label><font
								color="red"></font> :</td>

							<td><s:checkbox
								name="lockerFacilityFlag" onclick="showLockerFacilityFlag();" /></td>
						</tr>

						<tr>
							<td width="23%"><label class="set" name="meal.facility"
								id="meal.facility" ondblclick="callShowDiv(this);"><%=label.get("meal.facility")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="mealFacilityFlag" onclick="showMealFacilityFlag();" /> <span
								id="mealFacility"><s:select headerKey="0"
								headerValue="--Select--" name="mealFacility"
								list="#{'F':'Free','P':'Payable'}" /></span></td>


							<td width="23%"><label class="set"
								name="medical.service.facility" id="medical.service.facility"
								ondblclick="callShowDiv(this);"><%=label.get("medical.service.facility")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="medicalFacilityFlag" onclick="showMedicalFacilityFlag();" />
							<span id="medicalFacility"><s:select headerKey="0"
								headerValue="--Select--" name="medicalFacility"
								list="#{'F':'Free','P':'Payable'}" /></span></td>

						</tr>
					</table>
				
					</td>
				</tr>

				<!-- provisionOfAccommodation -->
				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td><b><label class="set"
								name="provisionOfAccommodation " id="provisionOfAccommodation"
								ondblclick="callShowDiv(this);"><%=label.get("provisionOfAccommodation")%></label></b>:</td>
							<td></td>
							<td><b><label class="set"
								name="availability.welfare.officer"
								id="availability.welfare.officer"
								ondblclick="callShowDiv(this);"><%=label.get("availability.welfare.officer")%></label></b>:</td>
						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="onsite.accomodation" id="onsite.accomodation"
								ondblclick="callShowDiv(this);"><%=label.get("onsite.accomodation")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="onsiteAccomodationFlag"
								onclick="showOnsiteAccomodationFlag();" /><span
								id="onsiteAccomodation"> <s:select headerKey="0"
								headerValue="--Select--" name="onsiteAccomodation"
								list="#{'F':'Free','P':'Payable'}" /></span></td>


							<td width="23%"><label class="set" name="full.time"
								id="full.time" ondblclick="callShowDiv(this);"><%=label.get("full.time")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="fullTimeFlag" onclick="showfullTimeFlag();" /><span
								id="fullTime"><s:textfield name="fullTime" size="20"
								onkeypress="return numbersOnly();" 
								 /></span></td>

						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="offsite.accomodation" id="offsite.accomodation"
								ondblclick="callShowDiv(this);"><%=label.get("offsite.accomodation")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="offsiteAccomodationFlag"
								onclick="showOffsiteAccomodation();" /> <span
								id="offsiteAccomodation"><s:select headerKey="0"
								headerValue="--Select--" name="offsiteAccomodation"
								list="#{'F':'Free','P':'Payable'}" /></span></td>


							<td width="23%"><label class="set" name="part.time"
								id="part.time" ondblclick="callShowDiv(this);"><%=label.get("part.time")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="partTimeFlag" onclick="showPartTimeFlag();" /><span
								id="partTime"><s:textfield name="partTime" size="20"
								onkeypress="return numbersOnly();" 
								/></span></td>

						</tr>
					</table>
					

					</td>
				</tr>

				<!-- Availability Of Doctors -->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td><b><label class="set" name="Availability.doctors"
								id="Availability.doctors" ondblclick="callShowDiv(this);"><%=label.get("Availability.doctors")%></label></b>:</td>
							<td></td>
							<td><b><label class="set" name="Availability.nurses"
								id="Availability.nurses" ondblclick="callShowDiv(this);"><%=label.get("Availability.nurses")%></label></b>:</td>
						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="Availability.doctors.fullTime"
								id="Availability.doctors.fullTime"
								ondblclick="callShowDiv(this);"><%=label.get("Availability.doctors.fullTime")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="docFullTimeFlag" onclick="showDocFullTimeFlag();" /><span
								id="docFullTime"><s:textfield name="docFullTime"
								size="20" onkeypress="return numbersOnly();" 
								 /></span></td>


							<td width="23%"><label class="set"
								name="Availability.nurses.fullTime"
								id="Availability.nurses.fullTime"
								ondblclick="callShowDiv(this);"><%=label.get("Availability.nurses.fullTime")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="nurseFullTimeFlag" onclick="showNurseFullTimeFlag();" /><span
								id="nurseFullTime"><s:textfield name="nurseFullTime"
								size="20" onkeypress="return numbersOnly();" 
								 /></span></td>
								

						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="Availability.doctors.partTime"
								id="Availability.doctors.partTime"
								ondblclick="callShowDiv(this);"><%=label.get("Availability.doctors.partTime")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="docPartTimeFlag" onclick="showDocPartTimeFlag();" /><span
								id="docPartTime"><s:textfield name="docPartTime"
								size="20" onkeypress="return numbersOnly();" 
							 /></span></td>


							<td width="23%"><label class="set"
								name="Availability.nurses.partTime"
								id="Availability.nurses.partTime"
								ondblclick="callShowDiv(this);"><%=label.get("Availability.nurses.partTime")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:checkbox
								name="nursePartTimeFlag" onclick="showNursePartTimeFlag();" /><span
								id="nursePartTime"><s:textfield name="nursePartTime"
								onkeypress="return numbersOnly();" 
								 /></span></td>

						</tr>
					</table>
					
					</td>
				</tr>

				<!-- General Information -->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td><b><label class="set" name="general.information"
								id="general.information" ondblclick="callShowDiv(this);"><%=label.get("general.information")%></label></b>:</td>

						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="number.changing.rooms" id="number.changing.rooms"
								ondblclick="callShowDiv(this);"><%=label.get("number.changing.rooms")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfChangingRooms" size="25" onkeypress="return numbersOnly();" /></td>


							<td width="23%"><label class="set" name="number.rest.rooms"
								id="number.rest.rooms" ondblclick="callShowDiv(this);"><%=label.get("number.rest.rooms")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfRestRooms" size="25"
								onkeypress="return numbersOnly();" /></td>

						</tr>

						<tr>
							<td width="23%"><label class="set" name="number.mens.toilet"
								id="number.mens.toilet" ondblclick="callShowDiv(this);"><%=label.get("number.mens.toilet")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfMensToilet" size="25" onkeypress="return numbersOnly();" /></td>


							<td width="23%"><label class="set"
								name="number.womens.toilet" id="number.womens.toilet"
								ondblclick="callShowDiv(this);"><%=label.get("number.womens.toilet")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfWomensToilet" size="25"
								onkeypress="return numbersOnly();" /></td>

						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="number.mens.urinals" id="number.mens.urinals"
								ondblclick="callShowDiv(this);"><%=label.get("number.mens.urinals")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfMensUrinals" size="25" onkeypress="return numbersOnly();" /></td>


							<td width="23%"><label class="set" name="number.clinics"
								id="number.clinics" ondblclick="callShowDiv(this);"><%=label.get("number.clinics")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfClinics" size="25"
								onkeypress="return numbersOnly();" /></td>

						</tr>

						<tr>
							<td width="23%"><label class="set"
								name="number.emergency.rooms" id="number.emergency.rooms"
								ondblclick="callShowDiv(this);"><%=label.get("number.emergency.rooms")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfEmergencyRooms" size="25" onkeypress="return numbersOnly();" /></td>


							<td width="23%"><label class="set"
								name="number.of.ambulance" id="number.of.ambulance"
								ondblclick="callShowDiv(this);"><%=label.get("number.of.ambulance")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfAmbulance" size="25"
								onkeypress="return numbersOnly();" /></td>

						</tr>

						<tr>
							<td width="23%"><label class="set" name="number.of.uniforms"
								id="number.of.uniforms" ondblclick="callShowDiv(this);"><%=label.get("number.of.uniforms")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfUniforms" size="25" onkeypress="return numbersOnly();" /></td>


							<td width="23%"><label class="set"
								name="number.of.raincoats" id="number.of.raincoats"
								ondblclick="callShowDiv(this);"><%=label.get("number.of.raincoats")%></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="numberOfRainCoats" size="25"
								onkeypress="return numbersOnly();" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>

</s:form>


<script>
	
	showMealFacilityFlag();
	showMedicalFacilityFlag();
	showOnsiteAccomodationFlag();
	showOffsiteAccomodation();
	showfullTimeFlag();
	showPartTimeFlag();
	showDocFullTimeFlag();
	showDocPartTimeFlag();
	showNurseFullTimeFlag();
	showNursePartTimeFlag();
	//onload();
	
	
	function saveFun() {
		
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'WelfareInformation_save.action';
		document.getElementById('paraFrm').submit();
     }
     
     function resetFun() 
{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'WelfareInformation_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	return true;
}
     
	
	

	function showMealFacilityFlag(){
		if(document.getElementById('paraFrm_mealFacilityFlag').checked){
			document.getElementById('mealFacility').style.display = '';
			//document.getElementById('paraFrm_mealFacility').value = '0';  	 
		}
		else{
			document.getElementById('mealFacility').style.display = 'none';
			document.getElementById('paraFrm_mealFacility').value = '0';
		}
	}
	function showMedicalFacilityFlag(){
		if(document.getElementById('paraFrm_medicalFacilityFlag').checked){
			document.getElementById('medicalFacility').style.display = '';
			//document.getElementById('paraFrm_medicalFacility').value = '0'; 
		}
		else{
			document.getElementById('medicalFacility').style.display = 'none';
			document.getElementById('paraFrm_medicalFacility').value = '0'; 
		}
	}
	function showOnsiteAccomodationFlag(){
		if(document.getElementById('paraFrm_onsiteAccomodationFlag').checked){
			document.getElementById('onsiteAccomodation').style.display = '';
			//document.getElementById('paraFrm_onsiteAccomodation').value = '0'; 
		}
		else{
			document.getElementById('onsiteAccomodation').style.display = 'none';
		}
	}
	function showOffsiteAccomodation(){
		if(document.getElementById('paraFrm_offsiteAccomodationFlag').checked){
			document.getElementById('offsiteAccomodation').style.display = '';
			//document.getElementById('paraFrm_offsiteAccomodation').value = '0'; 
		}
		else{
			document.getElementById('offsiteAccomodation').style.display = 'none';
		}
	}
	function showfullTimeFlag(){
		if(document.getElementById('paraFrm_fullTimeFlag').checked){
			document.getElementById('fullTime').style.display = '';
			
		}
		else{
			document.getElementById('fullTime').style.display = 'none';
			document.getElementById('paraFrm_fullTime').value = ''; 
		}
	}
	function showPartTimeFlag(){
		if(document.getElementById('paraFrm_partTimeFlag').checked){
			document.getElementById('partTime').style.display = '';
			
		}
		else{
			document.getElementById('partTime').style.display = 'none';
			document.getElementById('paraFrm_partTime').value = ''; 
		}
	}
	function showDocFullTimeFlag(){
		if(document.getElementById('paraFrm_docFullTimeFlag').checked){
			document.getElementById('docFullTime').style.display = '';
			
		}
		else{
			document.getElementById('docFullTime').style.display = 'none';
			document.getElementById('paraFrm_docFullTime').value = ''; 
		}
	}
	function showDocPartTimeFlag(){
		if(document.getElementById('paraFrm_docPartTimeFlag').checked){
			document.getElementById('docPartTime').style.display = '';
			
		}
		else{
			document.getElementById('docPartTime').style.display = 'none';
			document.getElementById('paraFrm_docPartTime').value = ''; 
		}
	}
	function showNurseFullTimeFlag(){
		if(document.getElementById('paraFrm_nurseFullTimeFlag').checked){
			document.getElementById('nurseFullTime').style.display = '';
		}
		else{
			document.getElementById('nurseFullTime').style.display = 'none';
			document.getElementById('paraFrm_nurseFullTime').value = ''; 
		}
	}
	function showNursePartTimeFlag(){
		if(document.getElementById('paraFrm_nursePartTimeFlag').checked){
			document.getElementById('nursePartTime').style.display = '';
		}
		else{
			document.getElementById('nursePartTime').style.display = 'none';
			document.getElementById('paraFrm_nursePartTime').value = ''; 
		}
	}
	function onload(){
		setText('paraFrm_fullTime','Number of Welfare Officer')
	    setText('paraFrm_partTime','Number of Welfare Officer')
	   setText('paraFrm_docFullTime','Number of Available Doctors')
	   setText('paraFrm_nurseFullTime','Number of Available Nurses')
	   setText('paraFrm_docPartTime','Number of Available Doctors')
	   setText('paraFrm_nursePartTime','Number of Available Nurses')
	}

  	</script>


