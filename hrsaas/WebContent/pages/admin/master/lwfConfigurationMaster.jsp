<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="LWFConfigurationMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="dataPath" />
	<s:hidden name="sTabOrder" />
	<s:hidden name="sMode" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
<!-- Added By Ganesh Start -->
<s:hidden name="lwfID" /><s:hidden name="hiddencode" id="hiddencode"/>
	<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">LWF Configuration
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td align="right">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<!-- LWF Applicability Start -->
				<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">LWF Applicability </strong></td>
				</tr>
				
				<tr>
							<td width="25%"><label class="set" id="tab1.lwf.debithead"
								name="tab1.lwf.debithead" ondblclick="callShowDiv(this);"><%=label.get("tab1.lwf.debithead")%></label>
							<font color="#FF0000">*</font>:</td>

							<td width="25%" nowrap="nowrap"><s:hidden name="sLwfDebitHeadCode" /> <s:textfield
								name="sLwfDebitHead" readonly="true" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'LWFConfigurationMaster_f9DebitHead.action');">
							</td>
							<td width="25%">&nbsp;</td>
							<td width="25%">&nbsp;</td>
						</tr>

						<!--<tr>
							<td width="30%"><label class="set" id="tab1.lwf.credithead"
								name="tab1.lwf.credithead" ondblclick="callShowDiv(this);"><%=label.get("tab1.lwf.credithead")%></label>
							<font color="#FF0000">*</font>:</td>

							<td width="65%"><s:hidden name="sLwfCreditHeadCode" /> <s:textfield
								name="sLwfCreditHead" readonly="true" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'LWFConfigurationMaster_f9CreditHead.action');">
							</td>
							<td width="25%">&nbsp;</td>
							<td width="25%">&nbsp;</td>
						</tr>
				
				--></table>
			</td>
			</tr>
				<!-- LWF Applicability End -->
				
				<!-- LWF Slab Start -->
				<tr><s:hidden name="sLwfCode" />
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">LWF Slab </strong></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="25%"><label class="set"
								id="tab2.lwf.effectivedate" name="tab2.lwf.effectivedate"
								ondblclick="callShowDiv(this);"><%=label.get("tab2.lwf.effectivedate")%></label>
							: <font color="#FF0000">*</font></td>
							<td width="25%"><s:textfield name="sEffectiveDate" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10"></s:textfield>
							<s:a
								href="javascript:NewCal('paraFrm_sEffectiveDate','DDMMYYYY');">
								<img id="ctrlHide" src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="25%">&nbsp;</td>
							<td width="25%">&nbsp;</td>
						</tr>

						<tr>
							<td width="25%"><label class="set" id="tab2.lwf.state"
								name="tab2.lwf.state" ondblclick="callShowDiv(this);"><%=label.get("tab2.lwf.state")%></label>
							: <font color="#FF0000">*</font></td>
							<td width="25%" nowrap="nowrap"><s:hidden name="sStateCode"></s:hidden><s:textfield
								name="sState" readonly="true" size="30" /> <img id="ctrlHide"
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'LWFConfigurationMaster_f9State.action')">
							</td>
							<td width="25%">&nbsp;</td>
							<td width="25%">&nbsp;</td>
						</tr>

						<tr>
							<td width="25%" nowrap="nowrap"><label class="set"
								id="tab2.lwf.deductionmonths" name="tab2.lwf.deductionmonths"
								ondblclick="callShowDiv(this);"><%=label.get("tab2.lwf.deductionmonths")%></label>
							: <font color="#FF0000">*</font></td>

							<td width="25%">
								<select name="sDeductionMonth" 
									size="4" multiple="multiple">
									<option id="1" value="01">January</option>
									<option id="2" value="02">February</option>
									<option id="3" value="03">March</option>
									<option id="4" value="04">April</option>
									<option id="5" value="05">May</option>
									<option id="6" value="06">June</option>
									<option id="7" value="07">July</option>
									<option id="8" value="08">August</option>
									<option id="9" value="09">September</option>
									<option id="10" value="10">October</option>
									<option id="11" value="11">November</option>
									<option id="12" value="12">December</option>
								</select>
			            		
			            		<s:hidden name="sDeductionMonthCode" />
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td height="25" valign="top" class="formhead"><strong
								class="forminnerhead"> <label name="tab2.table.heading"
								class="set" id="tab2.table.heading"
								ondblclick="callShowDiv(this);"><%=label.get("tab2.table.heading")%></label>
							</strong></td>
							<td align="right"><input type="button" value="Add Row"
								name="add1" class="add" onClick="addRowFun()" /> <!--<input
								type="button" value="Remove" name="deleteRow" class="delete"
								onClick="callRemove();" />--></td>
						</tr>

						<tr>
							<td colspan="2">
							<table width="100%" id="tblRef" border="0" cellpadding="2" 
								cellspacing="1" class="formbg">
								<tr>
									<td width="10%" valign="top" align="center" class="formth">
									<label name="tab2.sr.no" class="set" id="tab2.sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("tab2.sr.no")%></label>
									</td>
									<td width="20%" valign="top" class="formth" align="left"><label
										name="tab2.basic.from" class="set" id="tab2.basic.from"
										ondblclick="callShowDiv(this);"><%=label.get("tab2.basic.from")%><font color="#FF0000">*</font></label>
									</td>
									<td width="20%" valign="top" class="formth" align="left"><label
										name="tab2.basic.to" class="set" id="tab2.basic.to"
										ondblclick="callShowDiv(this);"><%=label.get("tab2.basic.to")%><font color="#FF0000">*</font></label>
									</td>
									<td width="20%" valign="top" class="formth" align="left"><label
										name="tab2.employee.contribution" class="set"
										id="tab2.employee.contribution"
										ondblclick="callShowDiv(this);"><%=label.get("tab2.employee.contribution")%><font color="#FF0000">*</font></label>
									</td>
									<td width="20%" valign="top" class="formth" align="left"><label
										name="tab2.employer.contribution" class="set"
										id="tab2.employer.contribution"
										ondblclick="callShowDiv(this);"><%=label.get("tab2.employer.contribution")%><font color="#FF0000">*</font></label>
									</td>
									<td width="10%" valign="top" class="formth" align="left">
									Delete/remove</td>
										
								</tr>

								<%
								int count1 = 0;
								%>

								<s:iterator value="lstSlabDefinition">
									<%
									++count1;
									%>
									<tr>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="iSrNo" /> <s:hidden name="iSrNo"
											id="iSrNo<%=count1%>"></s:hidden></td>

										<td width="20%" align="left" class="sortableTD"><input
											type="text" name="sBasicFrm" id="sBasicFrm<%=count1%>"
											value="<s:property value='sBasicFrm' />" maxlength="10"
											size="25" onkeypress="return numbersOnly()" /></td>

										<td width="20%" align="left" class="sortableTD"><input
											type="text" name="sBasicTo" id="sBasicTo<%=count1%>"
											value="<s:property value='sBasicTo' />" maxlength="10"
											size="25" onkeypress="return numbersOnly()" /></td>

										<td width="20%" align="left" class="sortableTD"><input
											type="text" name="sEmployeeContribution"
											id="sEmployeeContribution<%=count1%>"
											value="<s:property value='sEmployeeContribution' />"
											maxlength="10" size="25" onkeypress="return checkNumbersWithDot(this);" />
										</td>

										<td width="20%" align="left" class="sortableTD"><input
											type="text" name="sEmployerContribution"
											id="sEmployerContribution<%=count1%>"
											value="<s:property value='sEmployerContribution' />"
											maxlength="10" size="25" onkeypress="return checkNumbersWithDot(this);" />
										</td>

										<!--<td width="10%" align="center" class="sortableTD"><input
											type="checkbox" align="right" class="checkbox"
											name="sSlabChk" id="sSlabChk<%=count1%>"
											onclick="callForSelect(<%=count1%>)" /> <input type="hidden"
											name="hdelete" id="hdelete<%=count1%>" />
										</td>
										--><td  width="5%"  align="center" class="sortableTD"><img  id="ctrlHide"
															src="../pages/common/css/icons/delete.gif"
															onclick="deleteCurrentRow(this);" /></td>
									</tr>
								</s:iterator>
								<input type="hidden" name="count1" id="count1"
									value="<%=count1%>" />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
			</table>
			</td>
			</tr>
			
			<!-- LWF Slab End -->
			<!-- Employee Applicability Start -->
				<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg" id="employeeApplicablityTable">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Employee Applicability </strong></td>
				</tr>
				
				<tr>
									<td width="25%"><label class="set"
										id="division" name="division"
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:<font color="#FF0000">*</font></td>
									<td width="25%"><s:hidden name="sDivCode"/> <s:textarea
										name="sDivName" cols="25" rows="2" readonly="true"></s:textarea>
										<img src="../pages/images/recruitment/search2.gif" height="16"  id="ctrlHide"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'LWFConfigurationMaster_f9Div.action'); ">
									</td>
									<td width="25%"></td>
									<td width="25%"></td>
					</tr>
				
								<tr>
									<td width="25%" ><label class="set"
										id="tab3.all.employee" name="tab3.all.employee"
										ondblclick="callShowDiv(this);"><%=label.get("tab3.all.employee")%></label>
									:</td>

									<td width="25%"><s:checkbox name="sApplicableAll"
										onclick="javascript:callOptionTable()"></s:checkbox></td>
										
									<s:hidden name="hiddenChechAllEmpfrmId" id="hiddenChechAllEmpfrmId"/>
									
									
								</tr>
							
						<tr id="optionTable">
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">	
							
							
							<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Select options to download Template </strong><font color="red">*</font></td>
				</tr>
				<tr>
									<td width="25%" ><label class="set"
										id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>

									<td width="25%"><s:checkbox name="designation"
										onclick="return callOptions('designation');"></s:checkbox></td>
										
										<td width="25%" ><label class="set"
										id="grade" name="grade"
										ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:</td>

									<td width="25%"><s:checkbox name="grade"
										onclick="return callOptions('grade');"></s:checkbox></td>
										
										<s:hidden name="hiddenChechfrmId" id="hiddenChechfrmId"/>
								</tr>
								<tr>
						<td width="25%">
							<label id="downLoad.template" name="downLoad.template" ondblclick="callShowDiv(this);"><%=label.get("downLoad.template")%></label>:
						</td>
						<td width="25%">
						
						<input type="button" class="token" theme="simple" value="Download" title="Upload XLS file" 
							onclick="return callDownLoad();" />
						
						
							<!--<s:submit value="Download" cssClass="token" action="LWFConfigurationMaster_downLoadFile" 
							title="Download an template" onclick="return callDownLoad();" />
						--></td>
						
						<td width="25%"></td>
									<td width="25%"></td>
					</tr>
					<tr>
						<td width="25%" >
							<label id="upload.Name" name="upload.Name" ondblclick="callShowDiv(this);"><%=label.get("upload.Name")%></label> :<font color="red">*</font>
							</td>
						<td width="25%" colspan="1">
							<s:textfield name="uploadFileName" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />
							
							
						</td>
						<td width="25%"><input type="button" class="token" theme="simple" value="Select XLS File" title="Select XLS file" 
							onclick="uploadFile('uploadFileName');" /></td>
									<td width="25%"></td>
					</tr>			
			<tr>
			<td colspan="5">
				<table width="100%" class="formbg">  
					
					<tr>
						<td width="100%" align="center">
						<input type="button" class="token" theme="simple" value="Save Employee Applicability" title="Upload XLS file" 
							onclick="return calUploadTemplate();" />
						
						<input type="button" class="token" theme="simple" value="View Employee Applicability" title="View XLS file" 
							onclick="return calViewTemplate();" />
							
							<!--<s:submit cssClass="token" theme="simple" value="Upload" action="" 
							title="Upload XLS file" onclick="return calUploadTemplate();" /> 
						-->
						
						<s:if test="viewUploadFileFlag">
					
					</s:if>
						
						
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
	<!-- Employee Applicability End -->	
	<!-- Status Start -->	
	<tr>
							<td>
							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								
								<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Status</strong></td>
				</tr>
							<tr>
									<td width="100%">
									 	<label class="set" id="total.employee.in.division"
								name="total.employee.in.division" ondblclick="callShowDiv(this);"><%=label.get("total.employee.in.division")%></label>:
										 
									 	<b>
									 	<s:property value="totalEmpDiv"></s:property></b>
									 	
									 	
									 </td>
								</tr>	
								<tr>
									<td width="100%">
									 	<label class="set" id="total.employee.in.selected.division"
								name="total.employee.in.selected.division" ondblclick="callShowDiv(this);"><%=label.get("total.employee.in.selected.division")%></label>:
										
									 	<b><s:property value="totalEmpLWFDiv"></s:property></b>
								
									 	
									 </td>
								</tr>	
								
							</table>
							</td>
						</tr>
	
		<!-- Status End -->		
		<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td align="right">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>	
<!-- Added By Ganesh End -->
		
	</table>
</s:form>

<script type="text/javascript">
		callOnLoad ();
	
		function callOnLoad () {
		
		callOptionTable();
		
		
		var table = document.getElementById('tblRef'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowFun('tblRef');		
		}
		setDeductionMonthsTab2();
		
		
		
		
			var obj = document.getElementById('paraFrm_sTabOrder').value;
			displayTab(obj,'');
			
			/* Tab1 */
			if (obj == 1) {
				setCheckboxOnloadTab1();
			} else if (obj == 2) {
				setDeductionMonthsTab2();
			} else if (obj == 3) {
				///setCheckBoxOnloadTab3();
				
				if(document.getElementById('paraFrm_sApplicableAll').checked) {
					document.getElementById('specificEmployeeId').style.display='none';
					document.getElementById('specificEmployeeTbl').style.display='none';
				} else {
					document.getElementById('specificEmployeeId').style.display='';
					document.getElementById('specificEmployeeTbl').style.display='';
				}
			}
		}
	
		function setCheckboxOnloadTab1() {
			if (document.getElementById('sLwfApplicableOrg').value == 'Y') {
				document.getElementById('sLwfApplicableOrgChk').checked = false;
			} else {
				document.getElementById('sLwfApplicableOrgChk').checked = false;
			}
		}
		
		function setDeductionMonthsTab2() {
			var sMonth = document.getElementById("paraFrm_sDeductionMonthCode").value;
			if (sMonth != "") {
				var aMonth = sMonth.split(",");
				for (k=0; k < aMonth.length;k++){
					if (trim(aMonth[k]) == '01') {
						document.getElementById('1').selected = true;
					} else if (trim(aMonth[k]) == '02') {
						document.getElementById('2').selected = true;
					} else if (trim(aMonth[k]) == '03') {
						document.getElementById('3').selected = true;
					} else if (trim(aMonth[k]) == '04') {
						document.getElementById('4').selected = true;
					} else if (trim(aMonth[k]) == '05') {
						document.getElementById('5').selected = true;
					} else if (trim(aMonth[k]) == '06') {
						document.getElementById('6').selected = true;
					} else if (trim(aMonth[k]) == '07') {
						document.getElementById('7').selected = true;
					} else if (trim(aMonth[k]) == '08') {
						document.getElementById('8').selected = true;
					} else if (trim(aMonth[k]) == '09') {
						document.getElementById('9').selected = true;
					} else if (trim(aMonth[k]) == '10') {
						document.getElementById('10').selected = true;
					} else if (trim(aMonth[k]) == '11') {
						document.getElementById('11').selected = true;
					} else if (trim(aMonth[k]) == '12') {
						document.getElementById('12').selected = true;
					}
				}
			}
			//document.getElementById("jan").selected = true;
		}
		
		
		
		function displayTab(tabId,frm) { 	
			document.getElementById('tap1opt').className = ''; 
			document.getElementById('tap2opt').className = ''; 
			document.getElementById('tap3opt').className = ''; 
			document.getElementById(getTabId(tabId)).className = 'on';
			
			if(tabId == "1") {   
				document.getElementById('LWFApplicabilityDisp').style.display='';
			   	document.getElementById('SlapConfigurationDisp').style.display='none'; 
			   	document.getElementById('EmployeeApplicabilityDisp').style.display='none';  
			   	document.getElementById('paraFrm_sTabOrder').value = '1'; 
			   	if (frm == "tab") {
				   	document.getElementById("paraFrm").action="LWFConfigurationMaster_inputTab1.action";
					document.getElementById("paraFrm").submit();
				}
				//alert(1);
			}
		  	else if(tabId == "2") {   
		   		document.getElementById('LWFApplicabilityDisp').style.display='none'; 
		   		document.getElementById('SlapConfigurationDisp').style.display=''; 
		   		document.getElementById('EmployeeApplicabilityDisp').style.display='none';  
		   		document.getElementById('paraFrm_sTabOrder').value = '2'; 
		   		
		   		if (frm == "tab") {
		   			document.getElementById("paraFrm_sEffectiveDate").value = '';
		   			document.getElementById("paraFrm_sState").value = '';
					document.getElementById("paraFrm_sStateCode").value = '';
					//document.getElementById("paraFrm_sDeductionMonth").value = '';
					//document.getElementById("paraFrm_sDeductionMonth").selectedIndex = -1;
					document.getElementById("paraFrm_sDeductionMonthCode").value = '';
	   			
		   			document.getElementById("paraFrm").action="LWFConfigurationMaster_inputTab2.action";
					document.getElementById("paraFrm").submit();
				}
		  	}
		   	else if(tabId == "3") { 
		   		document.getElementById('LWFApplicabilityDisp').style.display='none';   
		   		document.getElementById('SlapConfigurationDisp').style.display='none';
		   		document.getElementById('EmployeeApplicabilityDisp').style.display=''; 
		   		document.getElementById('paraFrm_sTabOrder').value = '3'; 
		   		if (frm == "tab") {
		   			document.getElementById("paraFrm").action="LWFConfigurationMaster_inputTab3.action";
					document.getElementById("paraFrm").submit();
				}
		  	} 
		  	else {
		  		document.getElementById('LWFApplicabilityDisp').style.display='';
			   	document.getElementById('SlapConfigurationDisp').style.display='none'; 
			   	document.getElementById('EmployeeApplicabilityDisp').style.display='none';  
			   	document.getElementById('EmployeeApplicabilityDisp').value = '1';  
			   	if (frm == "tab") {
			   		document.getElementById("paraFrm").action="LWFConfigurationMaster_inputTab1.action";
					document.getElementById("paraFrm").submit();
				}
		  	}
	 	}
	 	
	 	function getTabId(typeCode) {
			
			switch(eval(typeCode)) {
				case 1: return "tap1opt";
				case 2: return "tap2opt";
				case 3: return "tap3opt";
				default: return "tap1opt";
			}
		}
		
		function getEmpList() {
				//var sDivCode = document.getElementById('paraFrm_sDivCode').value;
				//var sBranch = document.getElementById('paraFrm_sBranch').value;
				//var sDepartment = document.getElementById('paraFrm_sDepartment').value;
				//var sDesignation = document.getElementById('paraFrm_sDesignation').value;
				//var sEmpTypeId = document.getElementById('paraFrm_sEmpTypeId').value;
				//var sEmpCode = document.getElementById('paraFrm_sEmpCode').value;
				
				//if (sDivCode == "" && sBranch == "" && sDepartment == "" && sDesignation == "" && sEmpTypeId == "" && sEmpCode == "") {
				//	alert("Please select any above list.");
				//	return false;
				//}
				
				document.getElementById('paraFrm').action="LWFConfigurationMaster_viewEmployee.action";
	  			document.getElementById('paraFrm').submit();
		}
		
		function callPage(id,pageImg) {
 	 		pageNo =document.getElementById('pageNoField').value;	
 	 		totalPage =document.getElementById('totalPage').value;	 
 	 
 	 
 	   		if(pageNo=="") {
	        	alert("Please Enter Page Number.");
	        	document.getElementById('pageNoField').focus();
			 	return false;
	        }
	 		
	 		if(Number(pageNo)<=0) {
		    	alert("Page number should be greater than zero.");
		     	document.getElementById('pageNoField').focus();
			 	return false;
		    }
	  		
	  		if(Number(totalPage)<Number(pageNo)) {
		     	alert("Page number should not be greater than "+totalPage+".");
		     	document.getElementById('pageNoField').focus();
			 	return false;
		    }
		    	
       		if(pageImg=="F") {
	        	if(pageNo=="1") {
	         		alert("This is first page.");
	         		document.getElementById('pageNoField').focus();
	         		return false;
	        	} 
       		}  
       
       		if(pageImg=="L") {
	        	if(eval(pageNo)==eval(totalPage)) {
	         		alert("This is last page.");
	         		document.getElementById('pageNoField').focus();
	         		return false;
	        	} 
       		} 
       
        	if(pageImg=="P")
       		{
	        	if(pageNo=="1"){
	         		alert("There is no previous page.");
	         		document.getElementById('pageNoField').focus();
	         		return false;
	        	}  
       		}
       		  
       		if(pageImg=="N") {
	        	if(Number(pageNo)==Number(totalPage)){
	         		alert("There is no next page.");
	         		document.getElementById('pageNoField').focus();
	         		return false;
	        	}  
       		}  
       
         	var p=document.getElementById('pageNoField').value;
         	//alert(p);
         	//alert(id);
			if(id=='P') {
				id=eval(p)-1;
			}
			
			if(id=='N') {
				id=eval(p)+1;
			} 
		
			document.getElementById('paraFrm_myPage').value=id;
			document.getElementById('paraFrm').action="LWFConfigurationMaster_viewEmployee.action"
			document.getElementById('paraFrm').submit();
		}	
		
		
		function callPageText(id,status1){
	   		if(status1=="null" || status1=="" ){		
				status1="P";
			}
			
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			
			clear();
					
			if(key==13) 
			{ 
				pageNo =document.getElementById('pageNoField').value;		
		 		totalPage =document.getElementById('totalPage').value;		
				document.getElementById('paraFrm_myPage').value=pageNo;
	      
	        if(pageNo=="") {
	        	alert("Please Enter Page Number.");
	        	document.getElementById('pageNoField').focus();
			 	return false;
	        }
		   
		    if(Number(pageNo)<=0) {
		     	alert("Page number should be greater than zero.");
		     	document.getElementById('pageNoField').focus();
			 	return false;
		    }
		    
		    if(Number(totalPage)<Number(pageNo)) {
		     	alert("Page number should not be greater than "+totalPage+".");
		     	document.getElementById('pageNoField').focus();
			 	return false;
		    }
		 	
		 	document.getElementById('paraFrm').action="LWFConfigurationMaster_viewEmployee.action"
			document.getElementById('paraFrm').submit();
			}
		}
		
		function saveFun_old() {
			if (document.getElementById('paraFrm_sTabOrder').value == 1) {
					
					if (document.getElementById('paraFrm_sLwfDebitHeadCode').value == '') {
						alert("Please select 'Debit Head'");
						return false;
					}
			
					if (document.getElementById('paraFrm_sLwfCreditHeadCode').value == '') {
						alert("Please select 'Credit Head'");
						return false;
					}
			
			} else if (document.getElementById('paraFrm_sTabOrder').value == 2) {
				
				if (document.getElementById('paraFrm_sEffectiveDate').value == '') {
					alert("Please select 'Effective Date'");
					return false;
				}
				
				if (document.getElementById('paraFrm_sStateCode').value == '') {
					alert("Please select the 'State'");
					return false;
				}
				
				var flg = 0;
				for (k=1; k <= 12;k++) {
					if (document.getElementById(k).selected == true) {
						flg = 1;
					}
				}
				
				if (flg == 0) {
					alert("Please select 'Deduction Month'");
					return false;
				}
				
				/* Slab Definition */
				//alert("count1");
				var tableLength1 = document.getElementById("count1").value;
				//var tbl = document.getElementById('tblRef');
				//var tableLength1 = tbl.rows.length;
				//alert(tableLength1);
				for(j=1;j<=tableLength1;j++) {
					/* Basic From */
					var sBasicFrom = trim(document.getElementById("sBasicFrm"+j).value);
					if (sBasicFrom == "")	{
						alert ("'Basic From' should not be blank");
						document.getElementById("sBasicFrm"+j).value = '';
						document.getElementById("sBasicFrm"+j).focus();
						return false;
					}
					
					/* Basic To */
					var sBasicTo = trim(document.getElementById("sBasicTo"+j).value);
					if (sBasicTo == "")	{
						alert ("'Basic To' should not be blank");
						document.getElementById("sBasicTo"+j).value = '';
						document.getElementById("sBasicTo"+j).focus();
						return false;
					}
					
					/* Employee Contribution */
					var sEmployeeContribution = trim(document.getElementById("sEmployeeContribution"+j).value);
					if (sEmployeeContribution == "") {
						alert ("'Employee Contribution' should not be blank");
						document.getElementById("sEmployeeContribution"+j).value = '';
						document.getElementById("sEmployeeContribution"+j).focus();
						return false;
					}
					
					/* Employer Contribution */
					var sEmployerContribution = trim(document.getElementById("sEmployerContribution"+j).value);
					if (sEmployerContribution == "") {
						alert ("'Employer Contribution' should not be blank");
						document.getElementById("sEmployerContribution"+j).value = '';
						document.getElementById("sEmployerContribution"+j).focus();
						return false;
					}
				}
				
				
				var countCheck = document.getElementById("count1").value;
				//var tbl1 = document.getElementById('tblRef');
				//var tableLength2 = tbl1.rows.length;
				
				for (k=1; k<= countCheck;k++){
					var fromBasic = document.getElementById("sBasicFrm"+k).value;
					var toBasic = document.getElementById("sBasicTo"+k).value;
					if(eval(fromBasic) >= eval(toBasic)){
						alert("'From Basic' must be less than 'To Basic'.");
						return false;
					}
				}
				
				if(!checkRange()) {
					return false;
				}
				
			} else if (document.getElementById('paraFrm_sTabOrder').value == 3) {
				if (document.getElementById('paraFrm_sApplicableAll').checked == false) {
					var empConfigTable = document.getElementById("count3").value;
					if (empConfigTable == "0") {
						alert("Please select employee before save.");
						return false;	
					}
				}
			}
			
			document.getElementById("paraFrm").action="LWFConfigurationMaster_save.action";
			document.getElementById("paraFrm").submit();	
			
			return false;
		}
		
		
		function checkRange() {
		
		
		
			var countCheck = document.getElementById("count1").value;
			//var tbl = document.getElementById('tblRef');
			//var countCheck = tbl.rows.length;
			
			for (k=1; k<= countCheck;k++){
				var fromScore = document.getElementById("sBasicFrm"+k).value;
				var toScore = document.getElementById("sBasicTo"+k).value;
				if(eval(fromScore) >= eval(toScore)){
					alert("LWF Gross From (Rs) must be less than LWF Gross To (Rs).");
					return false;
				}
			}
			
			for (i=1; i<= countCheck;i++) {
			//alert("for");
				var fromScore = document.getElementById("sBasicFrm"+i).value;
				var toScore = document.getElementById("sBasicTo"+i).value;
				//alert("fromScore="+fromScore);
				//alert("toScore="+toScore);
				for(j=1; j<= countCheck ;j++){
				//alert("i="+i);
				//alert("j="+j);
				if(i != j) {
				
						//alert("document.getElementById(sBasicFrm+j)"+document.getElementById("sBasicFrm"+j).value);
						//alert("document.getElementById(sBasicTo+j)"+document.getElementById("sBasicTo"+j).value);
						
						if((fromScore >=eval(document.getElementById("sBasicFrm"+j).value) && fromScore <=eval(document.getElementById("sBasicTo"+j).value)))
						{
							alert("Please check the range, specified range is not correct1.");
							//alert("inside if from Score=="+fromScore+" and list FromScore=="+document.getElementById("sBasicFrm"+j).value+" and list toScore="+document.getElementById("sAppOverAllScoreTo"+j).value);
							document.getElementById("sBasicFrm"+i).focus();
							return false;
						}
						if(toScore >=eval(document.getElementById("sBasicFrm"+j).value) && toScore <=eval(document.getElementById("sBasicTo"+j).value))
						{
						//alert("inside if to Score=="+toScore+" and list FromScore=="+document.getElementById("sBasicFrm"+j).value+" and list toScore="+document.getElementById("sAppOverAllScoreTo"+j).value);
							alert("Please check the range, specified range is not correct2.");
							document.getElementById("sBasicTo"+i).focus();
							return false;
						}
					}
				}
			}
			return true;
		}
		
		function callAllEmplAppl() {
			if(document.getElementById('paraFrm_sApplicableAll').checked) {
				document.getElementById('specificEmployeeId').style.display='none';
				document.getElementById('specificEmployeeTbl').style.display='none';
			} else {
				document.getElementById('specificEmployeeId').style.display='';
				document.getElementById('specificEmployeeTbl').style.display='';
			}
			
			document.getElementById("paraFrm").action="LWFConfigurationMaster_reset.action";
			document.getElementById("paraFrm").submit();	
			
		}
		
		function addRowFun() {
		  var tbl = document.getElementById('tblRef');
		  var lastRow = tbl.rows.length;
		  //alert(lastRow);
		 
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		 	
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.className = 'sortableTD';
		  cellLeft.align = 'center';
		  cellLeft.appendChild(textNode);
	  
	  	  /* Basic From */	
   		  var cellBasicFrm = row.insertCell(1);
      	  var basicFrm = document.createElement('input');
      	  cellBasicFrm.className='sortableTD';
		  basicFrm.type = 'text';
		  basicFrm.name = 'sBasicFrm';
		  basicFrm.id = 'sBasicFrm'+iteration;
		  basicFrm.size='25';
		  basicFrm.maxLength='10';
		  cellBasicFrm.align='center';
		  basicFrm.onkeypress = numbersOnly;				//Called Validation 'NumberOnly'
		  cellBasicFrm.appendChild(basicFrm);
		  
		  /* Basic To */
		  var cellBasicTo = row.insertCell(2);
      	  var basicTo = document.createElement('input');
      	  cellBasicTo.className='sortableTD';
		  basicTo.type = 'text';
		  basicTo.name = 'sBasicTo';
		  basicTo.id = 'sBasicTo'+iteration;
		  basicTo.size='25';
		  basicTo.maxLength='10';
		  cellBasicTo.align='center';
		  basicTo.onkeypress = numbersOnly;					//Called Validation 'NumberOnly'
		  cellBasicTo.appendChild(basicTo);
		  
		  /* Employee Contribution */
		  var cellEmpContribution = row.insertCell(3);
      	  var empContribution = document.createElement('input');
      	  cellEmpContribution.className='sortableTD';
		  empContribution.type = 'text';
		  empContribution.name = 'sEmployeeContribution';
		  empContribution.id = 'sEmployeeContribution'+iteration;
		  empContribution.size='25';
		  empContribution.maxLength='10';
		  cellEmpContribution.align='center';
	////	  empContribution.onkeypress = numbersOnly;			//Called Validation 'NumberOnly'
		  
		  
		  	  empContribution.onkeypress = function(){
  			document.onkeypress = checkNumbersWithDot(this) ;
	var count = 0;
	var txtNo = this.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
  }
		  
		  
		  
		  
		  
		  cellEmpContribution.appendChild(empContribution);
		  
		  /* Employer Contribution */
		  var cellEmprContribution = row.insertCell(4);
      	  var emprContribution = document.createElement('input');
      	  cellEmprContribution.className='sortableTD';
		  emprContribution.type = 'text';
		  emprContribution.name = 'sEmployerContribution';
		  emprContribution.id = 'sEmployerContribution'+iteration;
		  emprContribution.size='25';
		  emprContribution.maxLength='10';
		  cellEmprContribution.align='center';
	///	  emprContribution.onkeypress = numbersOnly;			//Called Validation 'NumberOnly'
		  
		  	  emprContribution.onkeypress = function(){
  			document.onkeypress = checkNumbersWithDot(this) ;
	var count = 0;
	var txtNo = this.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
  }
		  
		  
		  
		  cellEmprContribution.appendChild(emprContribution);
		  
		  /* CheckBox */
		  
		  
		   var cell3 = row.insertCell(5);
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
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell3.appendChild(column3);
		  
		  
		  
		  
		  
		  var hiddenDel=row.insertCell(6);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'hdelete';
		  hid.id = 'hdelete' + iteration;
		  hiddenDel.appendChild(hid);
		  
		  /* Add the row count */
		  var tablecount1 = document.getElementById("count1").value;
		  document.getElementById("count1").value = (eval(tablecount1) + 1);
		}
		
		function callRemove()	{
			var tbl = document.getElementById('tblRef');
			var rowLen = tbl.rows.length;
	 		if(chkVac()) {
	 			var con=confirm('Do you really want to remove this record ?');
	 			if(con) {
	   				document.getElementById('paraFrm').action="LWFConfigurationMaster_deleteRowRef.action";
	    			document.getElementById('paraFrm').submit();
	    		} else {
	    			for (var idx = 1; idx < rowLen; idx++) {
						document.getElementById('sSlabChk'+idx).checked = false;
						document.getElementById('hdelete'+idx).value="";
						document.getElementById('chkAll').checked=false;
     				}
	    		}
	 		} else {
	 			alert('Please select a record to remove');
	 	 		return false;
	 		}
		}
		
		function chkVac() {
			var tbl = document.getElementById('tblRef');
			var rowLen = tbl.rows.length;
	
	  		for(var a=1;a<rowLen;a++) {	
	   			if(document.getElementById('sSlabChk'+a).checked == true)
	   			{	
	 	    		return true;
	   			}	   
	  		}
	  		return false;
		}
		
		function callForSelect(id) {
	    	if(document.getElementById('sSlabChk'+id).checked == true) {	  
	    		document.getElementById('hdelete'+id).value="Y";
	   		} else {
	   			document.getElementById('hdelete'+id).value="N";
   			}
  		}
		
		function funSelectAll() {
			var tbl = document.getElementById('tblRef');
			var rowLen = tbl.rows.length;
			if (document.getElementById('chkAll').checked == true) {
				for (var idx = 1; idx < rowLen; idx++) {
					document.getElementById('sSlabChk'+idx).checked = true;
					document.getElementById('hdelete'+idx).value="Y";
		    	}
 			} else {
				for (var idx = 1; idx < rowLen; idx++) {
					document.getElementById('sSlabChk'+idx).checked = false;
					document.getElementById('hdelete'+idx).value="N";
     			}
  			}
		}
		
		function callSelectEmployee(empid,id) {
	    	document.getElementById('sConfigEmp'+id).value = empid;
	    	
	    	if (document.getElementById('sLwfApplicableChk'+id).checked == true) {
				document.getElementById('isLwfApplicable'+id).value = 'Y'
	    	} else {
	    		document.getElementById('isLwfApplicable'+id).value = 'N'
	    	}
  		}
  		
  		function setCheckboxValueTab1(){
			try{
			if(document.getElementById('sLwfApplicableOrgChk').checked){
				document.getElementById('sLwfApplicableOrg').value ='Y';
			}else{
				document.getElementById('sLwfApplicableOrg').value ='N';
			}
			}catch(e){}
		}

		function searchFun() {
 			callsF9(500,325,'LWFConfigurationMaster_f9Search.action');
		}

		
		
		function cancelFun() {
	    	document.getElementById("paraFrm_sTabOrder").value = '2'; 
	    	
	    	/* Reset Data */
		   	document.getElementById("paraFrm_sEffectiveDate").value = '';
			document.getElementById("paraFrm_sState").value = '';
			document.getElementById("paraFrm_sStateCode").value = '';
			//document.getElementById("paraFrm_sDeductionMonth").value = '';
			document.getElementById("paraFrm_sDeductionMonthCode").value = '';
			document.getElementById("paraFrm_sLwfCode").value = '';
			document.getElementById("paraFrm_sMode").value = '';
	    	
	    	document.getElementById("paraFrm").action="LWFConfigurationMaster_inputTab2.action";
			document.getElementById("paraFrm").submit();	
			return false;
		}
		
		function resetFun() {
			//alert(document.getElementById('paraFrm_sTabOrder').value);
			if (document.getElementById('paraFrm_sTabOrder').value == '1') {
				document.getElementById('sLwfApplicableOrgChk').checked = false;
				document.getElementById('sLwfApplicableOrg').value = '';
				document.getElementById('paraFrm_sLwfDebitHead').value = '';
				document.getElementById('paraFrm_sLwfDebitHeadCode').value = '';
				document.getElementById('paraFrm_sLwfCreditHead').value = '';
				document.getElementById('paraFrm_sLwfCreditHeadCode').value = '';
				
			} else if (document.getElementById('paraFrm_sTabOrder').value == '2') {
				document.getElementById('paraFrm_sEffectiveDate').value = '';
				document.getElementById('paraFrm_sState').value = '';
				document.getElementById('paraFrm_sStateCode').value = '';
				
				for (k=1; k <= 12;k++) {
					document.getElementById(k).selected = false;
				}
				document.getElementById('paraFrm_sDeductionMonthCode').value = '';
				
				var table1Len = document.getElementById('count1').value;
				
				for (var idx = 1; idx <= table1Len; idx++) {
					document.getElementById('sBasicFrm'+idx).value = '';
					document.getElementById('sBasicTo'+idx).value = '';
					document.getElementById('sEmployeeContribution'+idx).value = '';
					document.getElementById('sEmployerContribution'+idx).value = '';
					document.getElementById('sSlabChk'+idx).checked = false;
				}
				
			} else if (document.getElementById('paraFrm_sTabOrder').value == '3') {
				document.getElementById('paraFrm_sApplicableAll').checked = false;
				callAllEmplAppl();
				
				document.getElementById('paraFrm_sDivCode').value = '';
				document.getElementById('paraFrm_sDivName').value = '';
				document.getElementById('paraFrm_sBranch').value = '';
				document.getElementById('paraFrm_sBranchName').value = '';
				document.getElementById('paraFrm_sDepartment').value = '';
				document.getElementById('paraFrm_sDepartmentName').value = '';
				document.getElementById('paraFrm_sDesignation').value = '';
				document.getElementById('paraFrm_sDesignationName').value = '';
				document.getElementById('paraFrm_sEmpType').value = '';
				document.getElementById('paraFrm_sEmpTypeId').value = '';
				document.getElementById('paraFrm_sEmpName').value = '';
				document.getElementById('paraFrm_sEmpCode').value = '';
			}
			return false;
		}
		
		function selectAllTab3() {
			var checkAll= document.getElementById('sConfigAllChk').checked;
			var count =document.getElementById('count3').value;
			
			if(checkAll){
				for(var i=1;i<=count;i++){
					document.getElementById('sLwfApplicableChk'+i).checked=true;
					document.getElementById('isLwfApplicable'+i).value='Y';
					
					var empid = document.getElementById('sEmployeeId'+i).value;
					//alert(empid);
					document.getElementById('sConfigEmp'+i).value = empid;
				}
				document.getElementById('sConfigAllChk').checked = true;
				
			} else {
				for(var i=1;i<=count;i++) {
					document.getElementById('sLwfApplicableChk'+i).checked = false;
					document.getElementById('isLwfApplicable'+i).value = 'N';
					
					var empid = document.getElementById('sEmployeeId'+i).value;
					document.getElementById('sConfigEmp'+i).value = empid;
				}
				document.getElementById('sConfigAllChk').checked = false;
			}
		}
		
		function clearDeductionMonth() {
		
		}
function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LWFConfigurationMaster_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callOptionTable() {
			if(document.getElementById('paraFrm_sApplicableAll').checked) {
				document.getElementById('optionTable').style.display='none';
				
				document.getElementById('hiddenChechAllEmpfrmId').value = "Y";
			} else {
				document.getElementById('optionTable').style.display='';
				document.getElementById('hiddenChechAllEmpfrmId').value = "";
			}
						
		}
		
		function callDownLoad() {
    	try{
		if(document.getElementById('paraFrm_sDivCode').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
		var desgCheckboxVar = document.getElementById('paraFrm_designation').checked;
		var gradeCheckboxVar = document.getElementById('paraFrm_grade').checked;
		
		if((desgCheckboxVar == false) && (gradeCheckboxVar == false) )
		{
			alert("Select options to download Template ");
			return false;
		}
		
		
		document.getElementById("paraFrm").action="LWFConfigurationMaster_downLoadFile.action";
			document.getElementById("paraFrm").submit();
		return true;
		}catch(e)
		{
			///alert(e);
		}
		
	}
	
	function callOptions(type){
		if(type!="designation"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_designation').checked =false;
				document.getElementById('hiddenChechfrmId').value = "G";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_grade').checked =false;
				document.getElementById('hiddenChechfrmId').value = "D";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			}
		}
}
	
		function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/attendance";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=50, left=100');
	}
	
	//function uploadFile(fieldName) {
	//	var dataPath = document.getElementById('paraFrm_dataPath').value;
	//	window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
	//	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	//}
	
	function calUploadTemplate() {  
		
		
		if(document.getElementById('paraFrm_lwfID').value == "") {
			alert("Please first save the records.");
			return false;
		}
		
		if(document.getElementById('paraFrm_sDivCode').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
		var desgCheckboxVar = document.getElementById('paraFrm_designation').checked;
		var gradeCheckboxVar = document.getElementById('paraFrm_grade').checked;
		
		if((desgCheckboxVar == false) && (gradeCheckboxVar == false) )
		{
			alert("Select options to download Template ");
			return false;
		}
		
			
	///	if(document.getElementById('paraFrm_uploadFileName').value == "") {
	///			alert("Please first " + document.getElementById('upload.Name').innerHTML.toLowerCase());
	///			document.getElementById('paraFrm_uploadFileName').focus();
	///			return false;
	///		} 
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
		
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
				document.getElementById('paraFrm_uploadFileName').value="";
				document.getElementById('paraFrm_uploadFileName').focus();
			} else {
				document.getElementById("paraFrm").action="LWFConfigurationMaster_uploadEmpAplicability.action";
			document.getElementById("paraFrm").submit();	
			}
		}
		
			
		
			
			 
		return true;
	}
	
	
		function calViewTemplate() {  
		
		try{
		
		
		var desgCheckboxVar = document.getElementById('paraFrm_designation').checked;
		var gradeCheckboxVar = document.getElementById('paraFrm_grade').checked;
		
		if((desgCheckboxVar == false) && (gradeCheckboxVar == false) )
		{
			alert("Please first Save Employee Applicability. ");
			return false;
		}
		
		
		document.getElementById("paraFrm").action="LWFConfigurationMaster_viewEmpAplicability.action";
			document.getElementById("paraFrm").submit();	
		
		//if(document.getElementById('paraFrm_sDivCode').value == "") {
		//	alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
		//	return false;
		//}
		
	 } catch (e)
	 {
	 	///alert(e);
	 }
			
		
			
			 
		
	}
	
	
	function saveFun()
{	
	try
	{
		if (document.getElementById('paraFrm_sLwfDebitHeadCode').value == '') {
						alert("Please select Debit Head");
						document.getElementById('paraFrm_sLwfDebitHead').focus();
						return false;
					}
	
		 
		 if (document.getElementById('paraFrm_sEffectiveDate').value == '') {
					alert("Please select Effective Date");
					document.getElementById('paraFrm_sEffectiveDate').focus();
					return false;
				}
				
				if (document.getElementById('paraFrm_sStateCode').value == '') {
					alert("Please select the State");
					document.getElementById('paraFrm_sState').focus();
					return false;
				}
				
				var flg = 0;
				for (k=1; k <= 12;k++) {
					if (document.getElementById(k).selected == true) {
						flg = 1;
					}
				}
				
				if (flg == 0) {
					alert("Please select Deduction Month");
					return false;
				}
				
						var tblSenior = document.getElementById('tblRef');
	var lastRowSenior = tblSenior.rows.length;
	//alert(lastRowSenior);
		if(lastRowSenior<2){
		alert("Please add at least one row value in Slab Definition.");
		return false;
		}
		
				/* Slab Definition */
				//alert("count1");
				var tableLength1 = document.getElementById("count1").value;
				//var tbl = document.getElementById('tblRef');
				//var tableLength1 = tbl.rows.length;
				//alert(tableLength1);
				
		
		
		
				for(j=1;j<=tableLength1;j++) {
					/* Basic From */
					var sBasicFrom = trim(document.getElementById("sBasicFrm"+j).value);
					if (sBasicFrom == "")	{
						alert ("LWF Gross From (Rs) should not be blank");
						document.getElementById("sBasicFrm"+j).value = '';
						document.getElementById("sBasicFrm"+j).focus();
						return false;
					}
					
					/* Basic To */
					var sBasicTo = trim(document.getElementById("sBasicTo"+j).value);
					if (sBasicTo == "")	{
						alert ("LWF Gross To (Rs) should not be blank");
						document.getElementById("sBasicTo"+j).value = '';
						document.getElementById("sBasicTo"+j).focus();
						return false;
					}
					
					/* Employee Contribution */
					var sEmployeeContribution = trim(document.getElementById("sEmployeeContribution"+j).value);
					if (sEmployeeContribution == "") {
						alert ("Employee Contribution (Rs) should not be blank");
						document.getElementById("sEmployeeContribution"+j).value = '';
						document.getElementById("sEmployeeContribution"+j).focus();
						return false;
					}
					
					/* Employer Contribution */
					var sEmployerContribution = trim(document.getElementById("sEmployerContribution"+j).value);
					if (sEmployerContribution == "") {
						alert ("Employer Contribution (Rs) should not be blank");
						document.getElementById("sEmployerContribution"+j).value = '';
						document.getElementById("sEmployerContribution"+j).focus();
						return false;
					}
				}
				
				
				var countCheck = document.getElementById("count1").value;
				//var tbl1 = document.getElementById('tblRef');
				//var tableLength2 = tbl1.rows.length;
				
				for (k=1; k<= countCheck;k++){
					var fromBasic = document.getElementById("sBasicFrm"+k).value;
					var toBasic = document.getElementById("sBasicTo"+k).value;
					if(eval(fromBasic) >= eval(toBasic)){
						alert("LWF Gross From (Rs) must be less than LWF Gross To (Rs).");
						return false;
					}
				}
				
				
		
		
				if(!checkRange()) {
					return false;
				}
		 
		if(document.getElementById('paraFrm_sDivCode').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		 
		 if(document.getElementById('paraFrm_sApplicableAll').checked) {
				document.getElementById('optionTable').style.display='none';
				
				document.getElementById('hiddenChechAllEmpfrmId').value = "Y";
					if(document.getElementById('paraFrm_sDivCode').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
			} else {
				document.getElementById('optionTable').style.display='';
				document.getElementById('hiddenChechAllEmpfrmId').value = "";
			}
		 
		  var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(!uploadFileName == '') {
		
		if(document.getElementById('paraFrm_sDivCode').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
		var desgCheckboxVar = document.getElementById('paraFrm_designation').checked;
		var gradeCheckboxVar = document.getElementById('paraFrm_grade').checked;
		
		if((desgCheckboxVar == false) && (gradeCheckboxVar == false) )
		{
			alert("Select options to download Template ");
			return false;
		}
		
			
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
				document.getElementById('paraFrm_uploadFileName').value="";
				document.getElementById('paraFrm_uploadFileName').focus();
				return false;
			}
		} 
		
		 
		}catch(e)
	{
	///alert("Exception occured in save function : "+e);
	}
		
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'LWFConfigurationMaster_saveFunction.action';
			document.getElementById('paraFrm').submit();
				
		  

	}
	function resetFun() {
		document.getElementById('paraFrm').target="main";		  
		document.getElementById("paraFrm").action="LWFConfigurationMaster_reset.action";
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target="main";
	}   

function viewUploadedFile() {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'LWFConfigurationMaster_viewEmpAplicability.action.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	//For Delete Rows
		function deleteCurrentRow(obj){ 
		var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		}
	}
		function editFun() {
	
		return true;
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LWFConfigurationMaster_deleteSelectedRecord.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	
	function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}

 	</script>


