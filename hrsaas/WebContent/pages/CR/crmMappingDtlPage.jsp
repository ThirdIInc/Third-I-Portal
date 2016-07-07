<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="AccountMaster" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="accountCode" />
<s:hidden name="myPage" id="myPage" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Assign
					CRM/Reports</strong></td>
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
				<td width="80%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="20%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="15%"><label id="acc.id" name="acc.id"
						ondblclick="callShowDiv(this);"><%=label.get("acc.id")%></label>:<font color="red"></font></td>
					<td width="25%"><s:textfield name="accountId" size="25" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
								
							<td width="15%" colspan="1" height="22"><label class="set"
								name="business.name" id="business.name"
								ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>:<font color="red"></font></td>
							<td width="85%" colspan="2" height="22"><s:textfield
								theme="simple" name="businessName" size="25" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td width="15%"><label class="set" id="reports"
								name="reports" ondblclick="callShowDiv(this);"><%=label.get("reports")%></label>:<font
								color="red">*</font></td>

							<td colspan="6"><s:hidden name="reportCode" /> <s:textarea
								cols="88" rows="2" theme="simple" readonly="true"
								name="reportName" /> <img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_reportName',350,250,'AccountMaster_f9report.action',event,'false','no','right')"></td>
						</tr>
						<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->



					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td class="formhead"><strong class="forminnerhead">Add
							CRM </strong></td>
						</tr>

						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="0">
								<tr>

									<td width="15%" nowrap="nowrap" colspan="3"><label
										class="set" id="crm" name="crm"
										ondblclick="callShowDiv(this);"><%=label.get("crm")%></label>:<font
										color="red">*</font></td>
									<td nowrap="nowrap"><s:hidden name="keepHidden" /> <s:hidden
										name="informCode" id="paraFrm_informCode" /> <s:textfield
										name="informToken" readonly="true"
										cssStyle="background-color: #F2F2F2;"/> <s:textfield name="informName"
										id="paraFrm_informName" size="40" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> <img
										src="../pages/images/recruitment/search2.gif" width="16"
										height="15" class="iconImage" id="ctrlShow"
										onclick="javascript:callsF9(500,325,'AccountMaster_f9informTo.action');" />
									<input type="button" value="Add" Class="add"
										onclick="return callAddKeepInfo();"></td>
								</tr>
								<tr valign="top">
									
									<!-- KEEP INFORMED LIST TABLE  STARTS -->
									<td align="left" colspan="4">
									<table width="100%" border="0"   class="sortable">
										<tr>
											<td width="2%" class="formth"><strong class="text_head">Sr.No.</strong></td>
											<td width="20%" class="formth"><strong class="text_head">Emp ID</strong></td>
											<td width="30%" class="formth"><strong class="text_head">CRM
											Name</strong></td>
											<td width="10%" align="center" class="formth"><strong class="text_head">Remove</strong></td>
										</tr>
									</table>
									</td>
								</tr>

								<tr valign="top">
									
									<!-- KEEP INFORMED LIST TABLE  STARTS -->
									<td colspan="4"> 
									<table width="100%" border="0" id="keepInformedTable">

										<%
											int counter = 1;
										%>
										<s:iterator value="keepInformedList">
											<tr>

												<td width="5%" align="left"><input type="hidden"
													name="keepInformToCode"
													value='<s:property value="keepInformToCode" />' /> <%=counter%>)
												</td>
												<td width="20%" align="left"><s:property value="keepInformToToken" />
												&nbsp;</td>
												<td width="30%">&nbsp; <s:property
													value="keepInformToName" /> &nbsp;</td>
												<td width="10%" align="center"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);"></td>

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
			</table>
			</td>
		</tr>


		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>


<script type="text/javascript">
	function saveFun(){	
		if(document.getElementById('paraFrm_reportCode').value==""){
			alert("Please select report.");
			document.getElementById('paraFrm_reportName').focus();
  			return false;
		}
		
		var table = document.getElementById('keepInformedTable');
	var rowCount = table.rows.length; 
		///alert("rowCount=="+rowCount); 
	  if(rowCount==0){
 			alert("Please select CRM/s.");
 			document.getElementById('paraFrm_informName').focus();
 			return false;
	 }
			
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='AccountMaster_saveCrmMappingDtl.action';
		document.getElementById("paraFrm").submit();
		return true;
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccountMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function callAddKeepInfo(){
		try{
			
		  var keepInformCode = document.getElementById("paraFrm_informCode").value;
		  var keepInformedName = document.getElementById("paraFrm_informName").value;
		  var keepInformedToken = document.getElementById("paraFrm_informToken").value;
		  
		  if(keepInformedName==""){
		  	alert("Please select CRM");
		  	return false;
		  }
		  var tbl = document.getElementById('keepInformedTable');
		  var lastRow = tbl.rows.length;
		  var iteration = (lastRow+1)+" ) ";
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'keepInformedToken';
		  column1.value = keepInformedToken; /*value to be set in the added cell*/
		  column1.id = 'keepInformedToken'+iteration;
		  column1.size='20';
		  column1.readOnly='true';
		  column1.maxLength='50';
		  cell1.align='left';
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(2);
		  var column2 = document.createElement('input');
  		  column2.type = 'text';
  		  column2.style.border = 'none';
		  column2.name = 'keepInformToName';
		  column2.value = keepInformedName; /*value to be set in the added cell*/
		  column2.id = 'keepInformToName'+iteration;
		  column2.size='30';
		  column2.maxLength='50';
		  column2.readOnly='true';
		  cell2.align='left';
		  cell2.appendChild(column2);
		  
		  
		  var cell3= row.insertCell(3);
		  var column3 = document.createElement('img');
		  column3.type='image';
		  column3.src="../pages/common/css/icons/delete.gif";
		  column3.align='absmiddle';
		  column3.id='img'+ iteration;
		  column3.cursor= 'hand';
		  column3.theme='simple';
		 // column3.align='center';
		  column3.size='10';
		  cell3.align='center';

		  column3.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
		  var column4 = document.createElement('input');
		  column4.type = 'hidden';
  		  column4.name = 'keepInformToCode';
  		  column4.value = keepInformCode; /*value to be set in the added cell*/
		  column4.id = 'keepInformToCode'+iteration;
		  column4.maxLength ='2';
		  cell3.appendChild(column4);
		  
	 
		}catch(e){alert(e);}
		document.getElementById("paraFrm_informName").value="";
		document.getElementById("paraFrm_informToken").value="";
	
	}
	
	function deleteCurrentRow(obj){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		}
	}
		
		</script>
