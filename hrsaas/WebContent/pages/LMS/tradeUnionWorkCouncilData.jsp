<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<%
	String dataLodg = "" + request.getAttribute("dataLodg");
	String dataLocal = "" + request.getAttribute("dataLocal");
	if (dataLodg != null && !dataLodg.equals("null")) {
	} else {
		dataLodg = "none";
	}
	if (dataLocal != null && !dataLocal.equals("null")) {
	} else {
		dataLocal = "none";
	}
	System.out.println("dataLodg===" + dataLodg
			+ " ====== dataLocal===" + dataLocal);
%>



<s:form action="TradeUnionWorkCouncil" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<input type="hidden" name="fieldName" id="paraFrm_fieldName">
	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />
	<s:hidden name="bargAgrrOption" />
	<s:hidden name="procgrievanceOption" />
	<input type="hidden" name="bargAgrrGrievanceoption"
		id="bargAgrrGrievanceoption" />

	<s:hidden name="tradeUnionWorkCouncilID"></s:hidden>
	<s:hidden name="committeeDtlId"></s:hidden>
	<s:hidden name="committeeAgreementGrievanceId"></s:hidden>

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
					<td width="93%" class="txt"><strong class="text_head">Trade
					Union / Work Council </strong></td>

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
					<td><label class="set" name="type.of.committee"
						id="type.of.committee" ondblclick="callShowDiv(this);"><%=label.get("type.of.committee")%></label><font
						color="red">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="committeeType"
						list="#{'T':'Trade Union','W':'Work Council'}" /></td>
				</tr>
				<tr>
					<td><label class="set" name="committee.Name"
						id="committee.Name" ondblclick="callShowDiv(this);"><%=label.get("committee.Name")%></label><font
						color="red">*</font> :</td>



					<td><s:hidden theme="simple" name="committeeId" /><s:textfield
						size="25" theme="simple" name="committeeName" /></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="name.of.leader"
						id="name.of.leader" ondblclick="callShowDiv(this);"><%=label.get("name.of.leader")%></label><font
						color="red">* :</td>


					<td><s:textfield size="25" theme="simple" name="leaderName" /></td>
				</tr>
				<tr>
					<td colspan="3" class="formtext">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td colspan="10">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								</td>
								</tr>

								<tr>
									<td><strong class="text_head"><label class="set"
										id="board.members.Details" name="board.members.Details"
										ondblclick="callShowDiv(this);"><%=label.get("board.members.Details")%></label></strong></td>


									<td align="right"><input type="button" class="add"
										value="Add Row" onclick="addRowToTableVacancy();" /></td>
								</tr>

								<tr>
									<td colspan="4">
									<table width="100%" id="tblVac" class="sortable">
										<tr>

											<td width="22%" valign="top" class="formth" align="left"
												nowrap="nowrap"><b><label class="set"
												id="board.members.name" name="board.members.name"
												ondblclick="callShowDiv(this);"><%=label.get("board.members.name")%></label></b><font
												color="red">*</font></td>
											<td width="22%" valign="top" class="formth" align="left"><b><label
												class="set" id="represented.by" name="represented.by"
												ondblclick="callShowDiv(this);"><%=label.get("represented.by")%></label></b><font
												color="red">*</font></td>

											<td width="4%" valign="top" class="formth" align="center"></td>


										</tr>
										<s:iterator value="boardMembersDetailsList">
											<tr>
												<s:hidden name="committeeDtlId"></s:hidden>

												<td class="sortableTD" align="center"><input
													type="text" name='boardMemberName'
													id="paraFrm_boardMemberName"
													value='<s:property value="boardMemberName"/>' width="100"
													size="60" /></td>
												<td class="sortableTD" align="center"><s:select
													headerKey="" headerValue="--Select--"
													name="boardMemberType"
													list="#{'M':'Manager Representative','W':'Work Representative'}" />

												</td>
												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>

											</tr>

										</s:iterator>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>



				<tr>

					<td width="15%" colspan="1"><label class="set"
						id="board.members.bargaining.agreement"
						name="board.members.bargaining.agreement"
						ondblclick="callShowDiv(this);"><%=label.get("board.members.bargaining.agreement")%></label>
					:</td>
					<td width="25%" colspan="1"><s:radio
						name="bargainingAgreement" 
						list="#{'B':'Yes &nbsp;','N':'No'}"
						onclick="callForBargainAgreement();"></s:radio></td>





				</tr>
				<tr id="localConTable" style="display: <%= dataLocal %>;">
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td width="82%" colspan="2"><strong><label
										class="set" id="Local.Conveyance.Details"
										name="Local.Conveyance.Details"
										ondblclick="callShowDiv(this);"><%=label.get("board.members.bargaining.agreement")%></label>
									</strong></td>
									<td align="right"><input type="button" class="add"
										value="Add Row" onclick="addRowToBargainingAgreementBlock();" />
									</td>


								</tr>
								<tr>
									<td width="100%" colspan="4">
									<table class="formbg" width="100%"
										id="bargainingAgreementTable">
										<tr>

											<td width="20%" colspan="1" class="formth"><label
												class="set" id="City" name="City"
												ondblclick="callShowDiv(this);"><%=label.get("agreement.name")%></label></td>
											<td width="20%" colspan="1" class="formth"><label
												class="set" id="Source" name="Source"
												ondblclick="callShowDiv(this);"><%=label.get("agreement.details")%></label></td>
											<td width="20%" colspan="2" class="formth"><label
												class="set" id="From.Date" name="From.Date"
												ondblclick="callShowDiv(this);"><%=label.get("expiry.Date")%></label></td>

											<td width="30%" colspan="3" class="formth"><label
												class="set" id="To.Time" name="To.Time"
												ondblclick="callShowDiv(this);"><%=label.get("agreement.documents")%></label></td>

										</tr>
										<s:iterator value="bargainAggreementList">
											<tr>
												<s:hidden name="committeeAgreementGrievanceId"></s:hidden>

												<td class="sortableTD" align="center"><input
													type="text" name='agreementName' id="paraFrm_agreementName"
													value='<s:property value="agreementName"/>' size="30"
													maxlength="100" /></td>
												<td class="sortableTD" align="center"><input
													type="text" name="agreementDetails"
													id="paraFrm_agreementDetails"
													value='<s:property value="agreementDetails"/>' size="30"
													maxlength="100" /></td>
												<td class="sortableTD" align="center"><input
													type="text" name="agrrementExpiryDate"
													id="paraFrm_agrrementExpiryDate"
													value='<s:property value="agrrementExpiryDate"/>' size="15" />
												<a
													href="javascript:NewCal('paraFrm_agrrementExpiryDate','DDMMYYYY');">
												<img src="../pages/images/Date.gif" class="iconImage"
													height="16" align="absmiddle" width="16"></td>
												<td></td>
												<td class="sortableTD" align="center"><input
													type="text" name="uploadLocFileName"
													id="paraFrm_uploadLocFileNamee"
													value='<s:property value="uploadLocFileName"/>' size="20" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadLocFileName');" />
												</td>


												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadLocFileName');" /></td>

												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>



											</tr>

										</s:iterator>

									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>



				<tr>
					<td width="15%" colspan="1"><label class="set"
						id="board.members.grievance.procedure"
						name="board.members.grievance.procedure"
						ondblclick="callShowDiv(this);"><%=label.get("board.members.grievance.procedure")%></label>
					:</td>
					<td width="25%" colspan="1"><s:radio name="procedureGrievance"
						list="#{'G':'Yes &nbsp;','N':'No'}"
						onclick="callForProcedureGrievance();"></s:radio></td>

				</tr>


				<tr id="lodgTable" style="display: <%= dataLocal %>;"
					style="display: &amp; amp;">
					<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="2" width="100%"><strong><label
										class="set" id="board.members.grievance.procedure"
										name="board.members.grievance.procedure"
										ondblclick="callShowDiv(this);"><%=label.get("board.members.grievance.procedure")%></label>
									</strong></td>
									<td align="right"><input type="button" class="add"
										value="Add Row" onclick="addRowToGrievanceProcedureBlock();" />
									</td>

								</tr>

								<tr>
									<td width="100%" colspan="7">
									<table class="formbg" width="100%" id="grievanceProcedureTable">
										<tr>

											<td width="20%" colspan="1" class="formth"><label
												class="set" id="Hotel.Type" name="Hotel.Type"
												ondblclick="callShowDiv(this);"><%=label.get("board.members.grievance.procedure.name")%></label></td>
											<td width="20%" colspan="1" class="formth"><label
												class="set" id="Room.Type" name="Room.Type"
												ondblclick="callShowDiv(this);"><%=label
										.get("board.members.grievance.procedure.details")%></label></td>
											<td width="15%" colspan="3" class="formth"><label
												class="set" id="City" name="City"
												ondblclick="callShowDiv(this);"><%=label.get("agreement.documents")%></label></td>
											<td width="2%" colspan="1"></td>

										</tr>
										<s:iterator value="procedureGrievanceList">
											<tr>
												<s:hidden name="committeeAgreementGrievanceId"></s:hidden>

												<td class="sortableTD" align="center"><input
													type="text" name='procedureName'
													id="paraFrm_localConveyanceCode"
													value='<s:property value="procedureName"/>' size="30" /></td>
												<td class="sortableTD" align="center"><input
													type="text" name="procedureDetails"
													id="paraFrm_localConveyanceTravelDetail"
													value='<s:property value="procedureDetails"/>' size="30" /></td>

												<td class="sortableTD" align="center"><input
													type="text" name="uploadProcGrievanceLocFileName"
													id="paraFrm_uploadProcGrievanceLocFileName"
													value='<s:property value="uploadProcGrievanceLocFileName"/>'
													size="20" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadProcGrievanceLocFileName');" />
												</td>


												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadProcGrievanceLocFileName');" />

												</td>

												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>



											</tr>

										</s:iterator>

									</table>
									</td>
								</tr>
							</table>
							</td>
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
	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />
</s:form>


<script>

	onload();
	function onload()
	{
		var table = document.getElementById('tblVac'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowToTableVacancy('tblVac');		
		}
		
		var table = document.getElementById('bargainingAgreementTable'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowToBargainingAgreementBlock('bargainingAgreementTable');		
		}
		
		var table = document.getElementById('grievanceProcedureTable'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowToGrievanceProcedureBlock('grievanceProcedureTable');		
		}
		
		
		 // to show bargain agreement table when value = Y
		 
		 var barGainAgreement=document.getElementById('paraFrm_bargAgrrOption').value;
		 if(barGainAgreement=='B')
		 {
		 	 document.getElementById('paraFrm_bargainingAgreementB').checked='B';
		 	 document.getElementById('localConTable').style.display='';  
		 }
		 else{
		 	 document.getElementById('paraFrm_bargainingAgreementN').checked='N';
		 }
		 
		  // to show procedure grievance table when value = Y
		  
		 var procedureGrievance=document.getElementById('paraFrm_procgrievanceOption').value;
		 if(procedureGrievance=='G')
		 {
		 	 document.getElementById('paraFrm_procedureGrievanceG').checked='G';
		 	 document.getElementById('lodgTable').style.display='';  
		 }
		 else{
		 	  document.getElementById('paraFrm_procedureGrievanceN').checked='N';
		 }
		 
		 
		 
	}
	
  	
  	function saveFun() {
		
		
		var committeeType=document.getElementById('paraFrm_committeeType').value;
  		
  		
  	if(committeeType==""){
			alert("Please select "+document.getElementById('type.of.committee').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_committeeType').focus();
			return false;
	}
	

  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'TradeUnionWorkCouncil_save.action';
		document.getElementById('paraFrm').submit();
      	
		
  	
  			}
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TradeUnionWorkCouncil_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="TradeUnionWorkCouncil_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TradeUnionWorkCouncil_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
	
  
  function callForBargainAgreement()
 {   
   if(document.getElementById('paraFrm_bargainingAgreementB').checked)
	  { 
	   document.getElementById('localConTable').style.display='';  
	     document.getElementById('paraFrm_bargainingAgreement').value='B';
	  }
	   if(document.getElementById('paraFrm_bargainingAgreementN').checked)
	  {  document.getElementById('localConTable').style.display='none';  
	  	     document.getElementById('paraFrm_bargainingAgreement').value='N';
	  
	  }
 }
 
 
 function callForProcedureGrievance()
 {    				 
	 	 			
  if(document.getElementById('paraFrm_procedureGrievanceG').checked)
	  { 
	  
	   document.getElementById('lodgTable').style.display='';  
	   document.getElementById('paraFrm_procedureGrievance').value='G';
	  }
	   if(document.getElementById('paraFrm_procedureGrievanceN').checked)
	  { 
	   document.getElementById('lodgTable').style.display='none';  
	   document.getElementById('paraFrm_procedureGrievance').value='N';
	  }
 } 
 


	function addRowToTableVacancy()
	{
		  var tbl = document.getElementById('tblVac');
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  
		  var cellNoOfVac = row.insertCell(0);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'boardMemberName';
		  vac.id = 'boardMemberName'+iteration;
		  cellNoOfVac.className='sortableTD';
		  vac.maxLength='50';
		  vac.size='50';
		  cellNoOfVac.align='center';
		
		  cellNoOfVac.appendChild(vac);
  
		  
		 
		 
		 var cell5 = row.insertCell(1);
			  var column5 = document.createElement('SELECT');
			  column5.name = 'boardMemberType';
	  		  column5.id = 'paraFrm_boardMemberType'+iteration;
	  		  cell5.className='sortableTD';
			  cell5.appendChild(column5);
			  
			  var option = document.createElement('option');
			  option.value = 'M';
			  option.appendChild(document.createTextNode('Manager Representative'));
   			  column5.appendChild(option);
   			  
   			  option = document.createElement('option');
			  option.value = 'W';
			  option.appendChild(document.createTextNode('Work Representative'));
   			  column5.appendChild(option);
 
		 
	  
		  var cell6= row.insertCell(2);
		  var column6 = document.createElement('img');
		  cell6.className='sortableTD';
		  column6.type='image';
		  column6.src="../pages/common/css/icons/delete.gif";
		  column6.align='absmiddle';
	  	  column6.id='img'+ iteration;
		  column6.theme='simple';
		  cell6.align='center';

		  column6.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
	} 
	
	// Function For Adding More Bargaining Agreement
	function addRowToBargainingAgreementBlock()	{
	
		  var tbl = document.getElementById('bargainingAgreementTable');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell1 = row.insertCell(0);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
		  column1.type = 'text';
  		  column1.name = 'agreementName';
		  column1.id = 'paraFrm_agreementName'+iteration;
		  column1.size ='30';
		  column1.maxLength ='100';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  
		 
		  
		  var cell2 = row.insertCell(1);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
		  column2.type = 'text';
  		  column2.name = 'agreementDetails';
		  column2.id = 'paraFrm_agreementDetails'+iteration;
		  column2.size ='30';
		  column2.maxLength ='1000';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  
		    var cell3 = row.insertCell(2);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
  		  column3.name = 'agrrementExpiryDate';
		  column3.id = 'paraFrm_agrrementExpiryDate'+iteration;
		  column3.size ='15';
		  column3.maxLength ='10';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  
		  var cell4 = row.insertCell(3);
		  var column4 = document.createElement('img');
		  cell4.className='sortableTD';
		  column4.type='image';
		  column4.src="../pages/images/recruitment/Date.gif";
		  column4.align='center';
		  column4.id='img'+ iteration;
		  column4.theme='simple';
		  column4.onclick=function(){
		  try {
				NewCal('paraFrm_agrrementExpiryDate'+iteration,'DDMMYYYY');
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  
		 
		  
		  var cell5= row.insertCell(4);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'uploadLocFileName';
		  column5.id = 'paraFrm_uploadLocFileName'+iteration;
		  column5.size ='20';
		  column5.maxLength ='20';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(5);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type='button';
		  column6.align='center';
		  column6.name='uploadBtn';
		  column6.value='Upload';
		  column6.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadLocFileName'+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
		  var cell7= row.insertCell(6);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type='button';
		  column7.align='center';
		  column7.name='showBtn';
		  column7.value='Show';
		  column7.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadLocFileName'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);

		  var cell8= row.insertCell(7);
		  var column8 = document.createElement('img');
		  cell8.className='sortableTD';
		  column8.type='image';
		  column8.src="../pages/common/css/icons/delete.gif";
		  column8.align='absmiddle';
	  	  column8.id='img'+ iteration;
		  column8.theme='simple';
		  cell8.align='center';

		  column8.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell8.appendChild(column8);
		  
		  
	}
	
	// Function For Adding More Grievance Procedure
	function addRowToGrievanceProcedureBlock()	{
	
		  var tbl = document.getElementById('grievanceProcedureTable');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell1 = row.insertCell(0);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
		  column1.type = 'text';
  		  column1.name = 'procedureName';
		  column1.id = 'paraFrm_procedureName'+iteration;
		  column1.size ='40';
		  column1.maxLength ='100';
		  cell1.align='center';
		  cell1.appendChild(column1);
		  
		 
		  
		  var cell2 = row.insertCell(1);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
		  column2.type = 'text';
  		  column2.name = 'procedureDetails';
		  column2.id = 'paraFrm_procedureDetails'+iteration;
		  column2.size ='40';
		  column2.maxLength ='1000';
		  cell2.align='center';
		  cell2.appendChild(column2);
		  
		  
		  
		  var cell3= row.insertCell(2);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type = 'text';
  		  column3.name = 'uploadProcGrievanceLocFileName';
		  column3.id = 'paraFrm_uploadProcGrievanceLocFileName'+iteration;
		  column3.size ='20';
		  column3.maxLength ='20';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(3);
		  var column4 = document.createElement('input');
		  cell4.className='sortableTD';
		  column4.type='button';
		  column4.align='center';
		  column4.name='uploadBtn';
		  column4.value='Upload';
		  column4.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadProcGrievanceLocFileName'+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell4.appendChild(column4);
		  
		  
		  var cell5= row.insertCell(4);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type='button';
		  column5.align='center';
		  column5.name='showBtn';
		  column5.value='Show';
		  column5.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadProcGrievanceLocFileName'+iteration);
		  }catch(e){alert(e);}
		  };
		  cell5.appendChild(column5);

		  var cell6= row.insertCell(5);
		  var column6 = document.createElement('img');
		  cell6.className='sortableTD';
		  column6.type='image';
		  column6.src="../pages/common/css/icons/delete.gif";
		  column6.align='absmiddle';
	  	  column6.id='img'+ iteration;
		  column6.theme='simple';
		  cell6.align='center';

		  column6.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
	}
	
	
	function uploadTicketFile(fldId)
{

	var path=document.getElementById('pathFld').value+"\\TMS\\<%=session.getAttribute("session_pool")%>\\Tickets";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fldId,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	
	function showRecord(fieldName)
	{
		
		var fileName =document.getElementById(fieldName).value;
		alert(fileName);
		
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TradeUnionWorkCouncil_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
		return true ; 
	}
	
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

  	</script>


