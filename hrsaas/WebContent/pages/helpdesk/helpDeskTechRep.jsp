<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskTechReporting" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="1" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Team</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td width="20%"><label
								name="manager" class="set" id="manager"
								ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" align="left"><s:hidden  name="managerCode" />
							<s:textfield  name="managerToken" />
							<s:textfield name="managerName" theme="simple" size="50"
								readonly="true" />
								<img id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="javascript:callsF9(500,325,'HelpDeskTechReporting_f9manageraction.action');">
						</tr>
						<tr>
							<td width="20%"><label name="technician" class="set"
								id="technician" ondblclick="callShowDiv(this);"><%=label.get("technician")%></label>
							:<font color="red">*</font></td>
							<td align="left"><s:hidden name="technicianCode" /><s:textfield
								name="technicianToken" /><s:textfield name="technicianName" theme="simple"
								size="50" readonly="true" /> <img id='ctrlHide'
								align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="chkManagerSelect();">
							<td align="center"><input type="button" value="Add to List"
								class="token" align="middle"
								onclick="callAddRowToTechnicians();"></td>
						</tr>
						<tr>
							<td colspan="4">
							<table id="technicianTable" border="0" cellpadding="0" cellspacing="0" width="100%" class="sortable">
								<%
								int counter = 1;
								%>
								<tr>
								<td class="formth" align="center" width="10%"><label class="set"
								name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
								</td>
								<td colspan="2" class="formth" align="center" width="70%"><label class="set"
								name="technician" id="technician1" ondblclick="callShowDiv(this);"><%=label.get("technician")%></label>
								</td>
								</td>
								<td class="formth" align="center" width="20%">
										Delete
									</td>
								</tr>
								<s:iterator value="techniciansList">
									<tr>
									<td class="sortableTD" align="center"><%=counter%></td>
									<td class="sortableTD" width="20%" align="left">
										<s:hidden name="technicianIdItt"
											id="paraFrm_technicianIdItt<%=counter%>" />
											<s:property	value="technicianTokenItt" /></td>
									<td class="sortableTD" width="50%" align="left">
											<s:property	value="technicianNameItt" /> &nbsp;</td>
									<td class="sortableTD" width="20%" align="center"><img src="../pages/common/css/icons/delete.gif"
											onclick="deleteTechnician(this);" id="ctrlHide">&nbsp;</td>
									</tr>
									<%
									counter++;
									%>
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
		<!-- 
		<tr>
			<td colspan="3">
			<table class="formbg" border="0" width="100%">
				<tr>
					<td colspan="2"><b><label class="set" name="technician"
						id="technician" ondblclick="callShowDiv(this);"><%=label.get("technician")%></label>
					</b></td>
					<td align="right"><input type="button" value="   Add   "
						class="token" align="middle" onclick="callAddTechnicians();"></td>
					
				</tr>
				<tr>
					<td width="100%" colspan="4">
					<table id="technicianTable" width="100%" border="0" cellpadding="1"	cellspacing="1">
						<tr>
							<td class="formth" colspan="3"><b><label class="set"
								name="techName" id="techName" ondblclick="callShowDiv(this);"><%=label.get("techName")%></label></b>
							<font color="red">*</font></td>
							<td class="formth" align="center" colspan="2">&nbsp;</td>
						</tr>
						<s:iterator value="techniciansList">
							<tr>
								<td class="sortableTD" align="left">
								<input type="hidden" id="paraFrm_technicianIdItt<%=counter%>" name="technicianIdItt"
									value='<s:property value="technicianIdItt"/>' />
									<input type="text" id="paraFrm_technicianTokenItt<%=counter%>" name="technicianTokenItt"
									value='<s:property value="technicianTokenItt"/>' size="20"
									readonly="true"/>&nbsp;</td>  
								<td class="sortableTD" align="left">
								<input type="text" id="paraFrm_technicianNameItt<%=counter%>"
									name="technicianNameItt"
									value='<s:property value="technicianNameItt"/>' size="60"
									readonly="true" />&nbsp;</td>
								<td align="center" class="sortableTD">
								<img src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="16" align="center" width="16"
									onclick="setRowIdValue(<%=counter%>);" id='ctrlHide'>&nbsp;</td>
								<td class="sortableTD" align="center">
								<img src="../pages/common/css/icons/delete.gif"	onclick="deleteTechnician(this);">&nbsp;</td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
		</tr>
		-->
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="rowId" />
</s:form>

<script>
	//onload();
	function onload(){
		if(document.getElementById("technicianTable").rows.length-1 < 1){
		 			callAddTechnicians();
		 }
	}
	
	function saveFun(){
	if(!validateBlank()){
		return false;
	}else{
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskTechReporting_save.action";
	    document.getElementById("paraFrm").submit();
	    }
	}
	function resetFun(){
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskTechReporting_reset.action";
	    document.getElementById("paraFrm").submit();
	}
	function backFun(){
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskTechReporting_back.action";
	    document.getElementById("paraFrm").submit();
	}
	function deleteFun(){
		var con=confirm('Do you want to delete the record ?');
	 	if(con){
			document.getElementById("paraFrm").target="main";
		 	document.getElementById("paraFrm").action="HelpDeskTechReporting_delete.action";
		    document.getElementById("paraFrm").submit();
	    }
	}
	function editFun() {
		return true;
	}
	
	function chkManagerSelect(){
		var manager = document.getElementById('paraFrm_managerName').value;
		if(manager==""){
	   		 alert("Please select "+document.getElementById('manager').innerHTML.toLowerCase());
	     	 document.getElementById("paraFrm_managerName").focus();
	     	 return false;
	   	}else{
	   		javascript:callsF9(500,325,'HelpDeskTechReporting_f9Technician.action');
	   	}
	}
	
	function validateBlank(){
		var manager = document.getElementById('paraFrm_managerName').value;
		var technicianTableRows=document.getElementById("technicianTable").rows.length-1;
		if(manager==""){
	   		 alert("Please select "+document.getElementById('manager').innerHTML.toLowerCase());
	     	 document.getElementById("paraFrm_managerName").focus();
	     	 return false;
	   	}
	   	//alert(technicianTableRows);
	   	if(technicianTableRows=="0"){
	   		alert("Please add atleast one team member");
			return false;
	   	}
	   	return true;
	}
	/*
	function callAddTechnicians(){
		try{
			  var tbl = document.getElementById('technicianTable');
			  var lastRow = tbl.rows.length;
			  var iteration = lastRow-1;
			  var row = tbl.insertRow(lastRow);
			  
	   		  var cell0 = row.insertCell(0);
			  var column0 = document.createElement('input');
	  		  column0.type = 'text';
			  column0.name = 'technicianTokenItt';
			  column0.id = 'paraFrm_technicianTokenItt'+iteration;
			  column0.size='20';
			  column0.maxLength='50';
			  column0.readOnly = 'true';
			  cell0.className='sortableTD';
			  cell0.align='left';
			  cell0.appendChild(column0);
			  
	   		  var cell1 = row.insertCell(1);
			  var column1 = document.createElement('input');
	  		  column1.type = 'text';
			  column1.name = 'technicianNameItt';
			  column1.id = 'paraFrm_technicianNameItt'+iteration;
			  column1.size='60';
			  column1.maxLength='150';
			  column1.readOnly = 'true';
			  cell1.className='sortableTD';
			  cell1.align='left';
			  cell1.appendChild(column1);
			  
			  var cell2 = row.insertCell(2);
			  var column2 = document.createElement('img');
			  cell2.className='sortableTD';
			  column2.type='image';
			  column2.src="../pages/images/recruitment/search2.gif";
			  column2.align='absmiddle';
			  column2.id='img'+ iteration;
			  column2.theme='simple';
			  column2.onclick=function(){
			  try {
			  	if(document.getElementById('paraFrm_managerName').value==""){
			  		alert("Please select manager");
			  		return false;
			  	}else{
			  		document.getElementById('paraFrm_rowId').value=iteration;
	        		javascript:callsF9(500,325,'HelpDeskTechReporting_f9Technician.action');
	        		}
			  }catch(e){alert(e);}
			  };
			  cell2.align='center';
			  cell2.appendChild(column2);
			  
			  var cell3= row.insertCell(3);
			  var column3 = document.createElement('img');
			  cell3.className='sortableTD';
			  column3.type='image';
			  column3.src="../pages/common/css/icons/delete.gif";
			  column3.align='absmiddle';
			  column3.id='img'+ iteration;
			  column3.theme='simple';
			  cell3.align='center';
			  column3.onclick=function(){
			  try {
			  	deleteTechnician(this);
			  }catch(e){alert(e);}
			  };
			  cell3.appendChild(column3);
			  
			  var column4 = document.createElement('input');
			  column4.type = 'hidden';
	  		  column4.name = 'technicianIdItt';
			  column4.id = 'paraFrm_technicianIdItt'+iteration;
			  column4.maxLength ='10';
			  cell2.appendChild(column4);
			  
			}catch(e){
		}
	}
	*/
	function callAddRowToTechnicians(){
		try{
		  var technicianToken = document.getElementById("paraFrm_technicianToken").value;
		  var technicianId = document.getElementById("paraFrm_technicianCode").value;
		  var technicianName = document.getElementById("paraFrm_technicianName").value;
		  if(technicianName==""){
		  	alert("Please select team member");
		  	return false;
		  }
		  var tbl = document.getElementById('technicianTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.align='center';
		  cell0.className='sortableTD';
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'technicianTokenItt';
		  column1.value = technicianToken; /*value to be set in the added cell*/
		  column1.id = 'technicianTokenItt'+iteration;
		  column1.size='20';
		  column1.maxLength='30';
		  cell1.className='sortableTD';
		  cell1.align='left';
		  cell1.appendChild(column1);
		  
   		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
  		  column2.type = 'text';
  		  column2.style.border = 'none';
		  column2.name = 'technicianNameItt';
		  column2.value = technicianName; /*value to be set in the added cell*/
		  column2.id = 'technicianNameItt'+iteration;
		  column2.size='50';
		  column2.maxLength='50';
		  cell2.align='left';
		  cell2.className='sortableTD';
		  cell2.appendChild(column2);
		  
		  var cell3= row.insertCell(3);
		  var column3 = document.createElement('img');
		  column3.type='image';
		  column3.src="../pages/common/css/icons/delete.gif";
		  column3.align='absmiddle';
		  column3.id='img'+ iteration;
		  column3.theme='simple';
		  cell3.align='center';
		  cell3.className='sortableTD';
			
		  column3.onclick=function(){
		  try {
		   	deleteTechnician(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
		  var column4 = document.createElement('input');
		  column4.type = 'hidden';
  		  column4.name = 'technicianIdItt';
  		  column4.value = technicianId; /*value to be set in the added cell*/
		  column4.id = 'paraFrm_technicianIdItt'+iteration;
		  column4.maxLength ='2';
		  cell1.appendChild(column4);
	
		}catch(e){alert(e);}
		
		document.getElementById("paraFrm_technicianToken").value="";
		document.getElementById("paraFrm_technicianCode").value="";
		document.getElementById("paraFrm_technicianName").value="";
	}
	
	function  deleteTechnician(obj){
		var con=confirm('Do you want to delete the record(s) ?');
	 	if(con){
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		}
	}
	
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
	function setRowIdValue(rowNum){
		document.getElementById('paraFrm_rowId').value=rowNum;
		javascript:callsF9(500,325,'HelpDeskTechReporting_f9Technician.action');
	}
	

</script>
