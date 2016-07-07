<!-- Created by manish sakpal on 10th Jully 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AccidentDetails" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" align="right" class="formbg">
		<s:hidden name="accidentID" />
		
		<tr>
			<td width="100%">
			<table width="100%" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head">
					<img src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Accident Details </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
					<div align="right"><span class="style2"></span> <font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" align="center" class="formbg">
				<tr>
					<td colspan="4"><strong class="text_head">Accident
					Details</strong></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="casualType" id="casualType" ondblclick="callShowDiv(this);">
					<%=label.get("casualType")%> </label> :</td>
					<td height="22" colspan="3">
					<s:radio name="casualType"  value="%{accBean.casualType}"
						list="#{'Ac':'Accident &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', 'Ne':'Nearmiss'}"
						onclick="setType(this);"></s:radio></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="accidentType" id="accidentType"
						ondblclick="callShowDiv(this);"> <%=label.get("accidentType")%>
					</label> :</td>
					<td height="22"><s:select name="accidentType" headerKey="0"
						headerValue="-- Select type --"
						list="#{'F':'Fatal', 'N':'Non-Fatal'}" /></td>

					
					<td class="formtext" width="25%"><label class="set"
						name="accidentDate" id="accidentDate"
						ondblclick="callShowDiv(this);"> <%=label.get("accidentDate")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield maxLength="10"
						theme="simple" name="accidentDate" value="%{accidentDate}"
						size="25" onkeypress="return numbersWithHiphen();" /> <a
						href="javascript:NewCal('paraFrm_accidentDate','DDMMYYYY');">
					<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="accidentPlace" id="accidentPlace"
						ondblclick="callShowDiv(this);"> <%=label.get("accidentPlace")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield name="accidentPlace"
						size="25" /></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="accidentTime" id="accidentTime"
						ondblclick="callShowDiv(this);"> <%=label.get("accidentTime")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="accidentTime"
						onkeypress="return numbersWithColon();" size="25" 
						onfocus="clearText('paraFrm_accidentTime','(HH:24MI)')"
						onblur="setText('paraFrm_accidentTime','(HH:24MI)')" 
						/></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="causeOfAccident" id="causeOfAccident"
						ondblclick="callShowDiv(this);"> <%=label.get("causeOfAccident")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield
						name="causeOfAccident" size="25" /></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="investigatedBy" id="investigatedBy"
						ondblclick="callShowDiv(this);"> <%=label.get("investigatedBy")%>
					</label> :</td>
					<td height="22" width="25%"><s:select name="investigatedBy"
						headerKey="0" headerValue="------ Is  Investigated -----"
						list="#{'Y':'Yes', 'N':'No'}" /></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="generalLocation" id="generalLocation"
						ondblclick="callShowDiv(this);"> <%=label.get("generalLocation")%>
					</label> :</td>
					<td height="22" width="25%"><s:select name="generalLocation"
						headerKey="0" headerValue="----- Select  Location -----"
						list="#{'I':'Inside', 'O':'Outside'}" /></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="specificLocation" id="specificLocation"
						ondblclick="callShowDiv(this);"> <%=label.get("specificLocation")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield
						name="specificLocation" size="25" /></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="investigationMeans" id="investigationMeans"
						ondblclick="callShowDiv(this);"> <%=label.get("investigationMeans")%>
					</label> :</td>
					<td height="22" width="30%"><s:textarea
						name="investigationMeans" cols="27" rows="3" /> <img id='ctrlHide' 
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_investigationMeans','investigationMeans','', 'praFrm_investigationMeans', '1000','1000');">
					</td>
					
					<td class="formtext" width="25%"><label class="set"
						name="preventiveMeasure" id="preventiveMeasure"
						ondblclick="callShowDiv(this);"> <%=label.get("preventiveMeasure")%>
					</label> :</td>
					<td height="22" width="25%"><s:textarea
						name="preventiveMeasure" cols="27" rows="3" /> <img id='ctrlHide' 
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_preventiveMeasure','preventiveMeasure','', 'praFrm_preventiveMeasure', '1000','1000');">
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" align="center" class="formbg">
				<tr>
					<td colspan="4"><strong class="text_head">Victim
					Details</strong></td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="victim" id="victim" ondblclick="callShowDiv(this);">
					<%=label.get("victim")%> </label><font color="red">*</font> :</td>

					<td height="22" colspan="3"><s:hidden name="victimID"/> 
					<s:textfield name="victimToken" size="10"
						readonly="true" /> <s:textfield name="victimName" size="70"
						readonly="true" /> <img id='ctrlHide' 
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AccidentDetails_f9Victim.action');">
					</td>
				</tr>

				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="status" id="status" ondblclick="callShowDiv(this);">
					<%=label.get("status")%> </label> :</td>

					<td height="22" colspan="3"><s:radio name="status"
						list="#{'I':'Injured &nbsp; &nbsp;&nbsp;&nbsp;','D':'Death'}"
						onclick="setStatus(this);"></s:radio></td>
				</tr>


				<tr id="injuredID">
					<td class="formtext" width="20%"><label class="set"
						name="injuryDetails" id="injuryDetails"
						ondblclick="callShowDiv(this);"><%=label.get("injuryDetails")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield name="injuryDetails"
						size="25" /></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="bodyPartsAffected" id="bodyPartsAffected"
						ondblclick="callShowDiv(this);"> <%=label.get("bodyPartsAffected")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield
						name="bodyPartsAffected" size="25" /></td>
				</tr>


				<tr id="deathID">
					<td class="formtext" width="20%"><label class="set"
						name="dateOfDeath" id="dateOfDeath"
						ondblclick="callShowDiv(this);"><%=label.get("dateOfDeath")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield maxLength="10"
						theme="simple" name="dateOfDeath" value="%{dateOfDeath}" size="25" 
						onkeypress="return numbersWithHiphen();" />
					<a href="javascript:NewCal('paraFrm_dateOfDeath','DDMMYYYY');">
					<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="timeOfDeath" id="timeOfDeath"
						ondblclick="callShowDiv(this);"> <%=label.get("timeOfDeath")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="timeOfDeath"
						size="25" onkeypress="return numbersWithColon();" 
						onfocus="clearText('paraFrm_timeOfDeath','(HH:24MI)')"
						onblur="setText('paraFrm_accidentTime','(HH:24MI)')" 
						/></td>
				</tr>


				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="insuranceNumber" id="insuranceNumber"
						ondblclick="callShowDiv(this);"><%=label.get("insuranceNumber")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield
						name="insuranceNumber" size="25" maxlength="15"/></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="amtOfCompensation" id="amtOfCompensation"
						ondblclick="callShowDiv(this);"> <%=label.get("amtOfCompensation")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield
						name="amtOfCompensation" size="25" maxlength="15" onkeypress="return numbersOnly();" /></td>
				</tr>


				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="legalHiersEmployed" id="legalHiersEmployed"
						ondblclick="callShowDiv(this);"> <%=label.get("legalHiersEmployed")%>
					</label> :</td>

					<td height="22" colspan="3"><s:radio name="legalHiersEmployed"
						list="#{'Y':'Yes &nbsp;&nbsp;&nbsp;&nbsp;', 'N':'No'}"
						onclick="setLegalHeirs(this);"></s:radio></td>
				</tr>


				<tr id="legalHeirsDiv">
					<td class="formtext" width="20%"><label class="set"
						name="nameOfHiers" id="nameOfHiers"
						ondblclick="callShowDiv(this);"><%=label.get("nameOfHiers")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield name="nameOfHiers"
						size="25" /></td>
					
					<td class="formtext" width="25%"><label class="set"
						name="relationShip" id="relationShip"
						ondblclick="callShowDiv(this);"> <%=label.get("relationShip")%>
					</label> :</td>
					<td height="22" width="25%"><s:select headerKey=""
						headerValue="--- Select Relationship ---" name="relationShip"
						list="#{'F':'Father','M':'Mother','B':'Brother','S':'Sister','W':'Wife','N':'Son','D':'Daughter'}" />
					</td>
				</tr>



				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="meansOfHospitalization" id="meansOfHospitalization"
						ondblclick="callShowDiv(this);"><%=label.get("meansOfHospitalization")%>
					</label> :</td>
					<td height="22" width="30%"><s:textfield
						name="meansOfHospitalization" size="25" /></td>
					<td class="formtext" width="25%">&nbsp;</td>
					<td height="22" width="25%">&nbsp;</td>
				</tr>


				<tr>
					<td class="formtext" width="20%"><label class="set"
						name="workedPerformed" id="workedPerformed"
						ondblclick="callShowDiv(this);"> <%=label.get("workedPerformed")%>
					</label> :</td>
					<td height="22" width="30%"><s:textarea name="workedPerformed"
						cols="27" rows="3" /> <img id='ctrlHide' src="../pages/images/zoomin.gif"
						height="12" align="absmiddle" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_workedPerformed','workedPerformed','', 'praFrm_workedPerformed', '1000','1000');">
					</td>
					
					<td class="formtext" width="25%"><label class="set"
						name="protectiveMeasures" id="protectiveMeasures"
						ondblclick="callShowDiv(this);"> <%=label.get("protectiveMeasures")%>
					</label> :</td>
					<td height="22" width="25%"><s:textarea
						name="protectiveMeasures" cols="27" rows="3" /> <img id='ctrlHide' 
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_protectiveMeasures','protectiveMeasures','', 'praFrm_protectiveMeasures', '1000','1000');">
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td align="center" colspan="4">
						<input type="button"
						class="addnew" value="Add Victim" onclick="addVictimDetails();" />&nbsp;&nbsp;
						&nbsp;&nbsp;<input type="button"
						class="reset" value="Clear" onclick="clearDetails();" />
					</td>
				</tr>
<!-- Victim List Begins -->
				<tr>
					<td colspan="4">
					<table width="100%">
						<tr>
							<td class="formth" width="10%"><label name="SerialNum"
								id="SerialNum" ondblclick="callShowDiv(this);"> <%=label.get("SerialNum")%></label>
							</td>
							<td class="formth" width="10%"><label name="victimIDNumber"
								id="victimIDNumber" ondblclick="callShowDiv(this);"> <%=label.get("victimIDNumber")%></label>
							</td>
							<td class="formth" width="25%"><label name="victimName"
								id="victimName" ondblclick="callShowDiv(this);"> <%=label.get("victimName")%></label>
							</td>
							<td class="formth" width="15%"><label name="status"
								id="status" ondblclick="callShowDiv(this);"> <%=label.get("status")%></label>
							</td>
							<td class="formth" width="15%"><label name="insuranceNumber"
								id="insuranceNumber" ondblclick="callShowDiv(this);"> <%=label.get("insuranceNumber")%></label>
							</td>
							<td class="formth" width="15%"><label
								name="amtOfCompensation" id="amtOfCompensation"
								ondblclick="callShowDiv(this);"> <%=label.get("amtOfCompensation")%></label>
							</td>
							
							<td class="formth" width="10%"><label name="deleteVictim"
								id="deleteVictim" ondblclick="callShowDiv(this);"> <%=label.get("deleteVictim")%></label>
							</td>
						
						</tr>
				
				
						<s:if test="victimListLength">
							<%!int total = 0; 
							%>
							
							<%
							int count = 0;
							
							%>
							<s:iterator value="victimDetailsList">
								<tr>
									<td class="sortableTD" nowrap="nowrap" align="center"><%=++count%>
									<s:hidden name="serialNum" value="%{<%=count%>}" />
									<s:hidden name="VictimHiddenCodeItr" />
									</td>
									<td class="sortableTD" nowrap="nowrap" align="center">
									<s:hidden name="victimIDItr" id="<%= "victimIDItr"+count %>" /> 
									<s:hidden name="victimTokenItr" />
									<s:property value="victimTokenItr" />&nbsp;
									</td>

									<td class="sortableTD" nowrap="nowrap" align="center">
											<s:hidden name="victimNameItr"/>								
											<s:property value="victimNameItr" />&nbsp;
									</td>

									<td class="sortableTD" nowrap="nowrap" align="center">
									<s:hidden name="injuryDetailsItr" /> 
									<s:hidden name="bodyPartsAffectedItr" /> 
									<s:hidden name="dateOfDeathItr" /> 
									<s:hidden name="timeOfDeathItr" />
									<s:hidden name="statusItr" />
									<s:property value="statusItr" />&nbsp;
									</td>

									<td class="sortableTD" nowrap="nowrap" align="center">
									<s:hidden name="legalHiersEmployedItr" /> 
									<s:hidden name="nameOfHiersItr" /> 
									<s:hidden name="relationShipItr" />
									<s:hidden name="insuranceNumberItr" />
									<s:property value="insuranceNumberItr" />&nbsp;
									</td>

									<td class="sortableTD" nowrap="nowrap" align="center">
									<s:hidden name="meansOfHospitalizationItr" /> 
									<s:hidden name="workedPerformedItr" />
									<s:hidden name="protectiveMeasuresItr" />
									<s:hidden name="amtOfCompensationItr" />
										 <s:property value="amtOfCompensationItr" />&nbsp;
									</td>
								 
									<td align="center">&nbsp;
										<img  src="../pages/common/css/icons/delete.gif"
										onclick="callDeleteVictim(<%=count %>,'<s:property value="VictimHiddenCodeItr" />');" />
									</td>
									
								</tr>
							</s:iterator>
							<input type="hidden" name="rowNum" id="rowNum" value="<%=count %>"/>
							<%
						count++;
						%>
						
						<%
							total = count;
						%>
						
						</s:if>

						<s:else>
							<td align="center" colspan="6" nowrap="nowrap"><font
								color="red">There is no data to display</font></td>
						</s:else>
						
						
					<!-- Victim List Ends -->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" align="center" class="formbg">
				<tr>
					<td><strong class="text_head">Accident Witness
					Details</strong></td>

					<td align="right"><input type="button" class="addnew"
						value="Add Witness" onclick="addWitnessRow('addWitnessRowTable')" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<table id="addWitnessRowTable" width="100%">
						<%
						int winessCount = 0;
						%>
						<tr>
							<td class="formth" width="20%"><label name="witnessName"
								id="witnessName" ondblclick="callShowDiv(this);"> <%=label.get("witnessName")%></label>
							</td>
							<td class="formth" width="40%"><label name="witnessAddress"
								id="witnessAddress" ondblclick="callShowDiv(this);"> <%=label.get("witnessAddress")%></label>
							</td>
							<td class="formth" width="20%"><label
								name="witnessOccupation" id="witnessOccupation"
								ondblclick="callShowDiv(this);"> <%=label.get("witnessOccupation")%></label>
							</td>
							<td class="formth" width="10%"><label name="deleteWitness"
								id="deleteWitness" ondblclick="callShowDiv(this);"> <%=label.get("deleteWitness")%></label>
							</td>
						</tr>

						<s:iterator value="witnessDetailsList">
							<tr>
								<td align="center"><input type="hidden" name='witnessID'
									id="paraFrm_witnessID<%=winessCount%>"
									value='<s:property value="witnessID"/>' size="10" />&nbsp; <input
									type="text" name='witnessName'
									id="paraFrm_witnessName<%=winessCount%>"
									value='<s:property value="witnessName"/>' size="30"
									maxlength="50" height="22" />&nbsp;</td>
								<td align="center"><s:textarea name='witnessAddress'
									id="paraFrm_witnessAddress<%=winessCount%>" cols="50" rows="2" />&nbsp;
								</td>
								<td align="center"><input type="text"
									name='witnessOccupation'
									id="paraFrm_witnessOccupation<%=winessCount%>"
									value='<s:property value="witnessOccupation"/>' size="30"
									maxlength="50" height="22" />&nbsp;</td>
								<td align="center"><img  
									src="../pages/common/css/icons/delete.gif"
									onclick="callDeleteWitness('<s:property value="witnessID"/>');" />
								</td>
							</tr>
						</s:iterator>
						<%
						winessCount++;
						%>
					</table>
					</td>
				</tr>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />						
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="checkDelete" />
		<s:hidden name="casualTypeRadio" />
		<s:hidden name="statusRadio" />
		<s:hidden name="legalHeirsRadio" />
	</table>
</s:form>


<script>
	onload();
	function onload()
	{	
		setText('paraFrm_accidentTime','(HH:24MI)')
		setText('paraFrm_timeOfDeath','(HH:24MI)')
		document.getElementById('injuredID').style.display = 'none';
		document.getElementById('deathID').style.display = 'none';
		document.getElementById('legalHeirsDiv').style.display = 'none';
		
		var victable = document.getElementById('addWitnessRowTable'); 
		var vicrowCount = victable.rows.length;
		var viciteration = vicrowCount-1;		
		if(!viciteration>0)
		{
			addWitnessRow('addWitnessRowTable');		
		}
	}
	
	
		// Add row Witness Details Begins
	function addWitnessRow(tableID) {
			var table = document.getElementById(tableID); 
			var rowCount = table.rows.length; 
			var iteration = rowCount-1;
			var row = table.insertRow(rowCount);
						
			var cell1 = row.insertCell(0); 
			var column1 = document.createElement("input"); 
            column1.type = "text"; 
            column1.name = 'witnessName';
            column1.id = 'paraFrm_witnessName'+iteration;
            cell1.appendChild(column1); 
			column1.size='30';
		  	column1.maxLength='50';
		  	cell1.align='center';
			
			var column5 = document.createElement("hidden"); 
            column5.type = "text"; 
            column5.name = 'witnessID';
            column5.id = 'paraFrm_witnessID'+iteration;
            cell1.appendChild(column5); 
			column5.size='10';		  
			
            var cell2 = row.insertCell(1); 
            var column2 = document.createElement("textarea"); 
            column2.name = 'witnessAddress';
            column2.id = 'paraFrm_witnessAddress'+iteration;
			column2.cols='50';
		  	column2.rows='2'; 
		  	cell2.appendChild(column2);
		  	cell2.align = 'center'; 
             
            var cell3 = row.insertCell(2); 
			var column3 = document.createElement("input"); 
            column3.type = "text"; 
            column3.name = 'witnessOccupation';
            column3.id = 'paraFrm_witnessOccupation'+iteration;
            cell3.appendChild(column3); 
			column3.size='30';
		  	column3.maxLength='50';
		  	cell3.align='center';
             
            var cell4= row.insertCell(3);
		  	var column4 = document.createElement('img');		  	
		  	column4.type='image';
		  	column4.src="../pages/common/css/icons/delete.gif";		  	
		  	column4.id='img'+ iteration;
		  	
		  column4.onclick=function(){
		  try {
		  var con = confirm("Do you really want to delete row?");
		  if(con)
		  {		  		  
		   	deleteCurrentRow(this);
		  }
		  else
		  {
		  	return false;
		  }
		  
		  }catch(e){alert(e);}
		  };		  	
		  	cell4.appendChild(column4);
		  	cell4.align='center';
       } 
	// Add row Witness Details Ends
	
			
       function deleteCurrentRow(obj){ 
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		}
		
	function deleteRows(rowObjArray){		
		for (var i=0; i<rowObjArray.length; i++) {			
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
			}
		}
	
	function setType(id){	
	var setvalue=document.getElementById('paraFrm_casualTypeRadio').value = id.value;	
}	

	function setStatus(id)
	{
		var setvalue=document.getElementById('paraFrm_statusRadio').value =id.value;
		if(setvalue=='I')
		{
			document.getElementById('injuredID').style.display = '';
			document.getElementById('deathID').style.display = 'none';
			document.getElementById('paraFrm_dateOfDeath').value = '';
			document.getElementById('paraFrm_timeOfDeath').value = '';
		}	
		else
		{
			document.getElementById('deathID').style.display = '';	
			document.getElementById('injuredID').style.display = 'none';
			document.getElementById('paraFrm_injuryDetails').value = '';
			document.getElementById('paraFrm_bodyPartsAffected').value = '';
			setText('paraFrm_timeOfDeath','(HH:24MI)')
		}
		
	}
	
	function setLegalHeirs(id)
	{
		
		var setvalue=document.getElementById('paraFrm_legalHeirsRadio').value =id.value;
		if(setvalue=='Y')
		{
			document.getElementById('legalHeirsDiv').style.display = '';
		}	
		else
		{
			document.getElementById('legalHeirsDiv').style.display = 'none';
			document.getElementById('paraFrm_nameOfHiers').value = '';
			document.getElementById('paraFrm_relationShip').value = '';
		}
	}
	
	function addVictimDetails()
	{
	try
		{	
			var rowCount = document.getElementById('rowNum').value;			
			var victmFormID = document.getElementById('paraFrm_victimID').value;
			
			for(var total = 1; total<=rowCount; total++)
			{
				var victmIDItr = document.getElementById('victimIDItr'+total).value;
				
				if(victmIDItr == victmFormID)
				{
					alert("Victim already added, Please select another Victim.");
					return false;
				}else {
				}
			}
			
		
		/*	
			if(!document.getElementById('status').checked)
			{
				alert("Please select Status.");
				return false;
			}
		*/				
			var victimIDVar = document.getElementById('paraFrm_victimID').value;
			if(victimIDVar=="")
			{
				alert("Please Select Victim.");
				return false;
			}
		
		/*	
			if(!document.getElementById('legalHiersEmployed').checked)
			{
				alert("Please select Whether Legal Heirs Employed OR Not.");
				
			}
		*/
			
			
			
			
		var timeOfDeathVar = trim(document.getElementById('paraFrm_timeOfDeath').value);
		
		if(timeOfDeathVar!="" && timeOfDeathVar!="(HH:24MI)")
		{
			if(!validateTime('paraFrm_timeOfDeath',"timeOfDeath")){
					document.getElementById('paraFrm_timeOfDeath').focus();
					return false;   	
   				}
		}
			
		}catch(e){
			alert(e);
			return false;
		}	
		
		document.getElementById("paraFrm").target = "_self";
		document.getElementById("paraFrm").action = 'AccidentDetails_addVictimDetails.action';
		document.getElementById("paraFrm").submit();
	}
	
	function callDeleteVictim(id,victimDeleteCode)
	{
		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  		if(conf){
			  		if(victimDeleteCode=="")
			  		{
			  			document.getElementById('paraFrm_checkDelete').value=id;
			  			document.getElementById('paraFrm').target="_self";
		  		    	document.getElementById("paraFrm").action="AccidentDetails_deleteVictimFromJsp.action";
		  				document.getElementById("paraFrm").submit();
		  			}
		  			else
		  			{
		  				document.getElementById('paraFrm').target="_self";
		  		    	document.getElementById("paraFrm").action="AccidentDetails_deleteVictimRecordFromDatabase.action?victimDeleteCode="+victimDeleteCode;
		  				document.getElementById("paraFrm").submit();
		  			}
		  		}
	}
	
	function clearDetails()
	{
		try
		{
		document.getElementById('paraFrm_victimID').value = '';
		document.getElementById('paraFrm_victimToken').value = '';
		document.getElementById('paraFrm_victimName').value = '';
		document.getElementById('paraFrm_injuryDetails').value = '';
		document.getElementById('paraFrm_bodyPartsAffected').value = '';
		document.getElementById('paraFrm_dateOfDeath').value = '';
		document.getElementById('paraFrm_timeOfDeath').value = '';
		document.getElementById('paraFrm_insuranceNumber').value = '';
		document.getElementById('paraFrm_amtOfCompensation').value = '';		
		document.getElementById('paraFrm_nameOfHiers').value = '';
		document.getElementById('paraFrm_relationShip').value = '';
		document.getElementById('paraFrm_meansOfHospitalization').value = '';
		document.getElementById('paraFrm_workedPerformed').value = '';
		document.getElementById('paraFrm_protectiveMeasures').value = '';
		}
		catch(e)
		{
			alert("Exception occur in clearDetails() : "+e);
		}
	}
	
	function saveFun()
	{
		var accidentDateVar = trim(document.getElementById('paraFrm_accidentDate').value);
		if(accidentDateVar!="")
		{			
			if(!validateDate('paraFrm_accidentDate',"accidentDate")){
					document.getElementById('paraFrm_accidentDate').focus();
					return false;   	
   				}		
		}
		
		var accidentTimeVar = trim(document.getElementById('paraFrm_accidentTime').value);
		if(accidentTimeVar!="" && accidentTimeVar!="(HH:24MI)")
		{
			if(!validateTime('paraFrm_accidentTime',"accidentTime")){
					document.getElementById('paraFrm_accidentTime').focus();
					return false;   	
   				}
		}
		
			  
		var listcount = '<%=total%>';
		if(eval(listcount-1)>0) {
		
		}else{
		alert("Please add atleast one victim details.");
		document.getElementById('paraFrm_victimName').focus();
		return false;
		}
		
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='AccidentDetails_save.action';
	  	document.getElementById('paraFrm').submit();
	}
	
	function resetFun()
	{
		try
		{
			document.getElementById('paraFrm_casualTypeRadio').value = '';
			document.getElementById('paraFrm_accidentType').value = '';
			document.getElementById('paraFrm_accidentDate').value = '';
			document.getElementById('paraFrm_accidentPlace').value = '';
			document.getElementById('paraFrm_accidentTime').value = '';
			document.getElementById('paraFrm_causeOfAccident').value = '';
			document.getElementById('paraFrm_investigatedBy').value = '';
			document.getElementById('paraFrm_generalLocation').value = '';
			document.getElementById('paraFrm_specificLocation').value = '';
			document.getElementById('paraFrm_investigationMeans').value = '';
			document.getElementById('paraFrm_preventiveMeasure').value = '';
		}
		catch(e)
		{
			alert("Exception occur in resetAccidentDetails() : "+e);
		}
		
	}
	
	function backFun() 
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccidentDetails_back.action';
		document.getElementById('paraFrm').submit();
	}

	function editFun()
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccidentDetails_edit.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callDeleteWitness(witnessCode)
	{		
		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  		if(conf){
		  				document.getElementById('paraFrm').target="_self";
		  		    	document.getElementById("paraFrm").action="AccidentDetails_deleteWitnessRecord.action?witnessCode="+witnessCode;
		  				document.getElementById("paraFrm").submit();
		  		}
		
	}
	
	function deleteFun()
	{		
		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  		if(conf){
		  				document.getElementById('paraFrm').target="_self";
		  		    	document.getElementById("paraFrm").action="AccidentDetails_deleteAccidentRecord.action";
		  				document.getElementById("paraFrm").submit();
		  		}
	}
</script>
