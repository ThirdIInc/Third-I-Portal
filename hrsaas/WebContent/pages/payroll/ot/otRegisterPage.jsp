<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="OtRegister" validate="true" id="paraFrm"
	theme="simple">
	
	<div align="center" id="overlay"
		style="z-index: 3; 
		position: absolute; 
		width: 790px; 
		height: 900px; 
		margin: 0px; 
		left: 0; 
		top: 100; 
		background-color: #A7BEE2; 
		filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>
	
	<div id="confirmationDiv"
		style='position: absolute; 
		z-index: 3; 100 px; 
		height: 150px; 
		visibility: hidden; 
		top: 200px; 
		left: 100px;background-color: #FFF;'>
	</div>
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
<s:hidden name="dataPath" />
<s:hidden name="doubleOTflag" />
<s:hidden name="otRegisterID" />
<s:hidden name="show" value="%{show}" />
<s:hidden name="divisionID" />
<s:hidden name="configOTflag" />
<s:hidden name="mngDtlLength" />

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">OT Register </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Table starts for Button Panel-->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td width="70%"><input type="button" class="add" value=" Save "
										onclick="return callSave();" />&nbsp;
						
								<input type="button" class="add" value=" View OT"  
										onclick="return callView();" /> &nbsp;
						
					
						<input type="button" class="reset" value=" Reset "
										onclick="return resetFun();" /></td>
										
					<td  width="30%" align="right">
					<!--<input type="button" class="view" value=" Report"
										onclick="return callViewOtRegister();" />
										
										
					--><b>Export :</b>&nbsp;</td>
		<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
		<img src="../pages/images/buttonIcons/file-pdf.png" class="iconImage"
			align="absmiddle" " title="PDF"><span
			style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
		<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
		<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
			align="absmiddle" onclick="callReport('Xls');" title="Excel"><span
			style="padding-left: 5px;">Excel</span></a>					
										
										
										</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Table End for Button Panel-->
		
		<!-- Table starts for Date Period-->
		<tr>
			<td> 
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead"><label name="shift.period" id="shift.period"
									ondblclick="callShowDiv(this);"><%=label.get("shift.period")%></label></strong></td>
				</tr>
				<tr>
							<td width="12%" class="formtext"><label  class = "set"  id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font color="red">*</font></td>
							<td  width="25%" ><s:textfield name="fromDate" size="15"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="10%" class="formtext"><label  class = "set"  id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font color="red">*</font><s:hidden
								name="today" /></td>
							<td width="20%"  ><s:textfield name="toDate" size="15" 
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /><!-- <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a> --></td> 
							<td></td>
					</tr>
			</table>
			</td>
		</tr>
		<!-- Table end for Date Period-->
		<!-- Table starts for select calculate option-->
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="calculationOptionLabel" id="calculationOptionLabel" ondblclick="callShowDiv(this);"><%=label.get("calculationOptionLabel")%></label></b>
						 </td>
					</tr>
						<tr>
							 <td colspan="2">
							 	<s:checkbox name="systemCalculatedOtCheckBox" onclick="enableSystemCalculatedOtSection();"/>
							 	<label name="systemCalculatedOtLabel" id="systemCalculatedOtLabel" ondblclick="callShowDiv(this);"><%=label.get("systemCalculatedOtLabel")%></label>
							 </td>
							 <td width="30%">&nbsp;</td>
							 <td colspan="2">
							 	<s:checkbox name="manuallyCalculatedOtCheckBox" onclick="enableManuallyCalculatedOtTable();"/>
							 	<label name="manuallyCalculatedOtLabel" id="manuallyCalculatedOtLabel" ondblclick="callShowDiv(this);"><%=label.get("manuallyCalculatedOtLabel")%></label>
							 </td>
							<s:hidden name="hiddenCheckBoxFlag" id="hiddenCheckBoxFlag"/>
						</tr>
					</table>
				</td>
			</tr>
		<!-- Table end for select calculate option-->
		
		
		  <!-- Table starts for System Calculated Ot Start-->
		  
		<tr id="systemCalculatedOtTableID">
			<td colspan="6">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">  
		  
		<tr >
			<td colspan="6">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg"> 
					<tr>
						<td width="10%" colspan="1"><label name="manager" id="manager"
							ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>:<font color="red">*</font></td>
						<td width="75%" colspan="3">
							<s:hidden name="managerID" /><s:textfield name="managerToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="managerName" size="60" theme="simple" readonly="true" />
							<s:if test="generalFlag"></s:if> <s:else>
							 <img src="../pages/images/recruitment/search2.gif"
												height="16" align="absmiddle" width="18" theme="simple"
												id="ctrlHide" onclick="callsF9(500,325,'OtRegister_f9Manager.action');">
							</s:else>
						</td>
					</tr>
				    <tr>
						<td width="10%" colspan="1"><label name="employee" id="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font></td>
						<td width="75%"  colspan="3">
							<s:hidden name="employeeID" /><s:textfield name="employeeToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeName" size="60" theme="simple" readonly="true" />
							
							<s:if test="generalFlag"></s:if> <s:else>
							 <img id="ctrlHide"
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'OtRegister_f9employee.action');">
							</s:else>
						</td>
					</tr>
					
					
				</table>
				</td>
			</tr>
			
			
			
		
				</table>
			</td>
		</tr>

		<!-- Table starts for System Calculated Ot End-->
		<!-- Table starts for Manuall Calculated Ot Starts-->
			
		<tr id="manuallyCalculatedOtTableID">
				<td colspan="6">
					<table width="100%" border="0" align="right" cellpadding="1"
						cellspacing="1" class="formbg">	
			
			
			<tr >
				<td colspan="6">
					<table width="100%" border="0" align="right" cellpadding="1"
						cellspacing="1" class="formbg">
						
				<!--<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
							<td colspan="1" width="20%"><s:hidden name="divisionID" /> <s:textfield
								name="divisionName" theme="simple" readonly="true" maxlength="50"
								size="25" /> 
							</td>
							<td width="15%">	
								<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'OtRegister_f9Division.action');">
							</td>
							
							</tr>
				<tr>
							<td colspan="1" width="25%"><label id="branch" name="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="2" width="80%"><s:hidden name="branchCode" />
							<s:textarea cols="100" rows="1" name="branchName" theme="simple"
								readonly="true" /></td>
							<td width="2%"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_branchName',200,250,'OtRegister_f9Branch.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="25%"><label id="department"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="2" width="80%"><s:hidden name="departmentID" /> <s:textarea
								cols="100" rows="1" name="departmentName" theme="simple"
								readonly="true" /></td>
							<td width="2%"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_departmentName',200,250,'OtRegister_f9Department.action',event,'false','no','right')">
							</td>

						</tr>
						<tr>
							<td width="25%"><label class="set" id="paybill1"
								name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
							<td colspan="2" width="80%"><s:hidden name="paybillId" /> <s:textarea
								cols="100" rows="1" theme="simple" name="paybillName"
								readonly="true" /></td>
							<td width="2%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_paybillName',200,250,'OtRegister_f9Paybill.action',event,'false','no','right')">
							</td>

						</tr>
						<tr>
							<td colspan="1" width="25%"><label id="designation"
								name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td colspan="2" width="80%"><s:hidden name="desgCode" /> <s:textarea
								cols="100" rows="1" name="desgName" theme="simple"
								readonly="true" /></td>
							<td width="2%"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_desgName',200,250,'OtRegister_f9Desg.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="25%"><label id="costcenter"
								name="costcenter" ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label>
							:</td>
							<td colspan="2" width="80%"><s:hidden name="costcenterid" /> <s:textarea
								cols="100" rows="1" name="costcentername" theme="simple"
								readonly="true" /></td>
							<td width="2%"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_costcentername',200,250,'OtRegister_f9Costcenter.action',event,'false','no','right')">
							</td>
						</tr>
						
						
						<tr>

					<td width="25%" colspan="1"><label name="shift" id="shift"
						ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>:<font color="red">*</font></td>
					<td width="25%" colspan="2"><s:hidden name="shiftID" /> <s:textfield
						name="shiftName" readonly="true" size="25"
						cssStyle="background-color: #F2F2F2;" /> 
					
						<img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'OtRegister_f9shiftaction.action');">
						
					</td>
					<td width="25%"></td>
					
					</tr>
					
					 
						--><tr>
						<td width="10%" colspan="1"><label name="manager" id="manager"
							ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>:<font color="red">*</font></td>
						<td width="75%" colspan="3">
							<s:hidden name="managerManuallID" /><s:textfield name="managerManuallToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="managerManuallName" size="60" theme="simple" readonly="true" />
							<s:if test="generalFlag"></s:if> <s:else>
							 <img src="../pages/images/recruitment/search2.gif"
												height="16" align="absmiddle" width="18" theme="simple"
												id="ctrlHide" onclick="callsF9(500,325,'OtRegister_f9ManagerManuall.action');">
							</s:else>
						</td>
					</tr> 
				    <tr>
						<td width="10%" colspan="1"><label name="employee" id="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font color="red">*</font></td>
						<td width="75%"  colspan="3">
							<s:hidden name="employeeManuallID" /><s:textfield name="employeeManuallToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeManuallName" size="60" theme="simple" readonly="true" />
							
							<s:if test="generalFlag"></s:if> <s:else>
							 <img id="ctrlHide"
							src="../pages/images/recruitment/search2.gif" class="iconImage"
							height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'OtRegister_f9employeeManuall.action');">
							</s:else>
						</td>
					</tr>
				
					</table>
				</td>
			</tr>
			<!-- Section start for download template -->
			<tr >
				<td colspan="6">
					<table width="100%" border="0" align="right" cellpadding="1"
						cellspacing="1" class="formbg">
					
					<tr>
						 <td width="25%">
						 	<label name="downLaodTemplateForUploadingManualBonusLabel" id="downLaodTemplateForUploadingManualBonusLabel" ondblclick="callShowDiv(this);"><%=label.get("downLaodTemplateForUploadingManualBonusLabel")%></label>
						 </td>
						 <td colspan="4" width="75%">
						 	<a href="#" onclick="downloadManuallyCalculatedOtTemplate();"><b><u>Download Template</u></b></a>
						 </td>
					</tr>
					
					<tr id="fileUploadTable">
						 <td width="25%">
						 	<label name="fileNameForUploadManualBonusAllowanceLabel" id="fileNameForUploadManualBonusAllowanceLabel" ondblclick="callShowDiv(this);"><%=label.get("fileNameForUploadManualBonusAllowanceLabel")%></label>:<font color="red">*</font>
						 </td>
						 <td colspan="4" width="75%">
						 	<s:textfield name="fileNameForManuallyCalculatedOtAllowance" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
						 	<input type="button" value="   Browse   " class="token" onclick="selectFile('fileNameForManuallyCalculatedOtAllowance')"/>&nbsp;
							<input type="button" class="token" 	value=" Upload File " onclick="callManuallyUploadOt()" />
						 </td>
					</tr>
					<s:if test="displayNoteFlag">					
						<s:if test="status == 'Success'">					
							<tr>
								<td width="25%">
									<FONT color="green"><s:property value="status" /></FONT>
								</td>
								<!--<td colspan="4" width="60%">
						 			<a href="#" onclick="viewManualluUploadedFile();">
										<font color="blue"><u>Please click here to view uploaded file.</u></font>
									</a>
						 		</td>
							--></tr>
						</s:if><!--
						<s:else>
							<tr>
								<td width="25%">
									<FONT color="red"><s:property value="status" /></FONT>
								</td>
								<td colspan="4" width="75%">
						 			<FONT color="red">Note : <s:property value="note"/></FONT>
						 		</td>
							</tr>
							 
							<tr>
								<td width="100%" colspan="5" align="center">
									<a href="#" onclick="viewManualluUploadedFile();">
										<font color="blue"><u>Please click here to view uploaded file.</u></font>
									</a>
								</td>
							</tr>
						</s:else>
					--></s:if>
					<!--<tr>
						 <td width="25%">&nbsp;</td>
						 <td colspan="4" width="75%">
						 	<input type="button" value="   Browse   " class="token" onclick="selectFile('fileNameForManuallyCalculatedOtAllowance')"/>&nbsp;
							<input type="button" class="token" 	value=" Upload File " onclick="callManuallyUploadOt()" />
							<input type="button" class="view" value=" View Upload"
										onclick="return callViewOtRegister();" />&nbsp;&nbsp;
							
							
							<s:if test="viewAlreadyUploadedBonusFlag">	
								<input type="button" class="token" 	value=" View Uploaded Records " onclick="viewAlreadyUploadedBonus();" />
						   
						   
						   
						   
						   </s:if>
						 </td>
					</tr>	
						
					--></table>
				</td>
			</tr>
			
			
			</table>
		</td>
	</tr>
	
	<!-- TABLE FOR SHOWING OT REGISTER DETAILS START-->
	<s:if test="viewOtRegisterDtlFlag"> 
	
	<tr>
				<td colspan="8">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">
						
		<tr>
			<td width="100%">
				<table width="100%">
					<tr><s:hidden name="myPage" id="myPage" />
						<td width="70%">
						<% int totalPage = 0; int pageNo = 0; %>
						
						<strong
											class="forminnerhead"><label name="overtime.register.details" id="overtime.register.details"
							ondblclick="callShowDiv(this);"><%=label.get("overtime.register.details")%></label></strong></td>
						<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'OtRegister_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'OtRegister_input.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'OtRegister_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'OtRegister_input.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'OtRegister_input.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td>
						</s:if>
					</tr>
				</table>
			</td>			
		</tr>
			
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable"  id="tblSlabForMen">
						
						
						<tr class="td_bottom_border">

							<td class="formth" width="10%"><label name="shift" id="shift"
								ondblclick="callShowDiv(this);"><%=label.get("shift")%></label></td>
							<td class="formth"  width="20%"><label name="emp.name" id="emp.name"
								ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
							<td class="formth"  width="10%"><label name="date" id="date"
								ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
							<td class="formth"  width="15%"><label name="in" id="in"
								ondblclick="callShowDiv(this);"><%=label.get("in")%></label></td>
							<td class="formth"  width="15%"><label name="out" id="out"
								ondblclick="callShowDiv(this);"><%=label.get("out")%></label></td>
							<td class="formth"  width="15%"><label name="ot.hours" id="ot.hours"
								ondblclick="callShowDiv(this);"><%=label.get("ot.hours")%></label></td>
							<td class="formth"  width="15%"><label name="appr.ot.hours" id="appr.ot.hours"
								ondblclick="callShowDiv(this);"><%=label.get("appr.ot.hours")%></label></td>
							
						</tr>
						<tr class="td_bottom_border">

							<td class="formth" width="10%"></td>
							<td class="formth"  width="20%"></td>
							<td class="formth"  width="10%"></td> 
							<td class="formth"  width="15%"  align="center">
								<label name="shift.in" id="shift.in"
									ondblclick="callShowDiv(this);"><%=label.get("shift.in")%></label> | 
									<label name="actual.in" id="actual.in"
									ondblclick="callShowDiv(this);"><%=label.get("actual.in")%></label></td>
							<td class="formth"  width="15%"  align="center">
								<label name="shift.out" id="shift.out"
									ondblclick="callShowDiv(this);"><%=label.get("shift.out")%></label> | 
									<label name="actual.out" id="actual.out"
									ondblclick="callShowDiv(this);"><%=label.get("actual.out")%></label></td>
							<td class="formth"  width="15%"  align="center"><label name="single.ot" id="single.ot"
									ondblclick="callShowDiv(this);"><%=label.get("single.ot")%></label>
							<!--<s:if test="doubleOTflag=='YY'">|&nbsp;<label name="double.ot" id="double.ot"
									ondblclick="callShowDiv(this);"><%=label.get("double.ot")%></s:if>
							 --></td>
							<td class="formth"  width="15%"  align="center"><label name="single.appr.ot" id="single.appr.ot"
									ondblclick="callShowDiv(this);"><%=label.get("single.appr.ot")%></label>
									<s:if test="doubleOTflag=='YY'">|&nbsp;<label name="double.appr.ot" id="double.appr.ot"
									ondblclick="callShowDiv(this);"><%=label.get("double.appr.ot")%></label></s:if></td>
							
						</tr> 
						<s:if test="modeLength">	
						<%int count3=0; %>
										<%!int d=0; %>
										<%
										int i = 0;	
										int cn= pageNo*2-2;
											%>
						<s:iterator value="iteratorlist" status="stat">
							<% int count1=0; %>
							<tr class="sortableTD" onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this);">
								<td class="sortableTD"  width="10%">
									<input type="hidden" name="ittShiftID"  id="paraFrm_ittShiftID<%=count1%>"
									 value="<s:property value="ittShiftID"/>" /> 
									 <s:property value="ittShiftName"/>
								</td>
								<td> 
									<input type="hidden" name="ittEmployeeID"  id="paraFrm_ittEmployeeID<%=count1%>" 
									value="<s:property value="ittEmployeeID"/>" />
									<s:property value="ittEmployeeName"/>
								</td>
								<td class="sortableTD" width="10%">
									<s:property value="ittDate"/><s:hidden name="ittDate"/>
								 </td>
								 
								 <td class="sortableTD" width="10%" align="center">
									<s:property value="ittShiftIn"/> | <s:property value="ittActualIn"/>
								 </td>
								 
								 <td class="sortableTD" width="10%" align="center">
									<s:property value="ittShiftOut"/> | <s:property value="ittActualOut"/>
								 </td>
								 
								 <td class="sortableTD" width="10%" align="center">
									 
									
								<input type="text" readonly="readonly" size="5" style="background-color: #F2F2F2;" name="ittSingleOt" value='<s:property value="ittSingleOt"/>'>	
								<!--<s:if test="bean.doubleOTflag=='YY'"> | 
									<input type="text" readonly="readonly" size="5" style="background-color: #F2F2F2;" name="ittDoubleOt" value='<s:property value="ittDoubleOt"/>'>	 
								</s:if>		
									
									
								 --></td>
								 
								 <td class="sortableTD" width="10%" align="center">
								 <input	type="text" name="ittSingleApprOt" style="text-align:right"  
								 	value="<s:property value="ittSingleApprOt"/>"
									 id="<%="ittSingleApprOt"+i%>" size="4" maxlength="5" 
									 onkeypress="return numbersonlyWithColun(this);" 
									 onblur="otHoursCheck(<%=i%>);calTotalSingleOtSum();"/>
								
								<s:if test="bean.doubleOTflag=='YY'"> | 
									 <input	type="text" name="ittDoubleApprOt" style="text-align:right"  
									 value="<s:property value="ittDoubleApprOt"/>"
										 id="<%="ittDoubleApprOt"+i%>" size="4" maxlength="5" 
										 onkeypress="return numbersonlyWithColun(this);" 
										 onblur="otDoubleHoursCheck(<%=i%>);calTotalDoubleOtSum();"/>
							<!--
									<s:textfield name="ittDoubleApprOt" cssStyle="text-align:right"  id="<%="ittDoubleApprOt"+i%>" size="5" maxlength="5" onkeypress="return numbersWithDot();"  onblur="return showDoubleValidAmt();"/> 
								--></s:if>	 
									 
								 </td>
								
								</td>
							</tr>  
							
							<%count3++; i++;%>
						</s:iterator>
						</s:if>
						
						</table>
							<s:if test="modeLength"></s:if>
								<s:else>
									<table width="100%" >
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
									</s:else>
								<s:hidden name="paracode" /></td>
							</tr>
								
									</table>
								</td>
						</tr>
						<tr>
							
						<td colspan="7">
								<table width="100%" border="0" align="center" cellpadding="1"
											cellspacing="1">
									
									<tr>
									<td width="20%">
									 	<label class="set" id="total.no.employee"
										name="total.no.employee" ondblclick="callShowDiv(this);"><%=label.get("total.no.employee")%></label>:
									 	<b>
									 <s:property value="totalNoEmp"></s:property></b>
									 </td>
									 <td  width="10%"></td>
									  <td  width="10%"></td>
									   <td  width="15%"></td>
									    <td width="15%"></td>
									     <td width="15%"></td>
									      <td width="25%" align="center"><s:textfield name="totalApprSingleOtHours" id="totalApprSingleOtHours" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5"/>
								<s:if test="doubleOTflag=='YY'">|&nbsp;<s:textfield name="totalApprDoubleOtHours" id="totalApprDoubleOtHours" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="5"/></s:if>
								
								
								</td>
								</tr>	
									<!--<tr>
									<td >
									 	<label class="set" id="total.approved.single.ot.hours"
										name="total.approved.single.ot.hours" ondblclick="callShowDiv(this);"><%=label.get("total.approved.single.ot.hours")%></label>:
									 	<b>
									 <s:property value="totalApprSingleOtHours"></s:property></b>
									 </td>
									 
									 <td >
									 	<label class="set" id="total.approved.double.ot.hours"
										name="total.approved.double.ot.hours" ondblclick="callShowDiv(this);"><%=label.get("total.approved.double.ot.hours")%></label>:
									 	<b>
									 <s:property value="totalApprDoubleOtHours"></s:property></b>
									 </td>
								</tr>
						--></table>
					</td>
				</tr>
				   
			</s:if>
	
	
	
	
			<!-- Table for Manuall Calculated Ot End-->	
		<!-- Table starts for Button Panel-->	
				<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td width="70%"><input type="button" class="add" value=" Save "
										onclick="return callSave();" />&nbsp;
						
								<input type="button" class="add" value=" View OT"  
										onclick="return callView();" /> &nbsp;
						
					
						<input type="button" class="reset" value=" Reset "
										onclick="return resetFun();" /></td>
										
					<td  width="30%" align="right">
					<!--<input type="button" class="view" value=" Report"
										onclick="return callViewOtRegister();" />
										
										
					--><b>Export :</b>&nbsp;</td>
		<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
		<img src="../pages/images/buttonIcons/file-pdf.png" class="iconImage"
			align="absmiddle" " title="PDF"><span
			style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
		<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
		<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
			align="absmiddle" onclick="callReport('Xls');" title="Excel"><span
			style="padding-left: 5px;">Excel</span></a>					
										
										
										</td>
				</tr>
			</table>
			</td>
		</tr>
			
			<!-- Table Ens for Button Panel-->
			
			
		 </table>
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='OtRegister_getReport.action'/>
	 
	
</s:form>

<script>
	onLoadShowThisData();

function selectFile(fieldName) {
	var dataPath = document.getElementById('paraFrm_dataPath').value;
	window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
} 

	function onLoadShowThisData() {
 		try {
 		var hiddenCheckBoxChecked = document.getElementById('hiddenCheckBoxFlag').value;
 		if(hiddenCheckBoxChecked==''){
	 		document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked=true;
	 		document.getElementById('hiddenCheckBoxFlag').value="S";
 		}
	 		var manuallyCalculatedOtChecked = document.getElementById('paraFrm_manuallyCalculatedOtCheckBox').checked;
	 		if(manuallyCalculatedOtChecked) {
	 	 		document.getElementById('manuallyCalculatedOtTableID').style.display = '';
	 	 		////document.getElementById('hiddenCheckBoxFlag').value="S";
	 		} else {
	 		 	document.getElementById('manuallyCalculatedOtTableID').style.display = 'none';
	 		} 
	 		var systemCalculatedOtChecked = document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked;
	 		if(systemCalculatedOtChecked) {
	 	 		document.getElementById('systemCalculatedOtTableID').style.display = '';
	 	 		////document.getElementById('hiddenCheckBoxFlag').value="M";
	 		} else {
	 		 	document.getElementById('systemCalculatedOtTableID').style.display = 'none';
	 		} 
	 		var mngLength = document.getElementById('paraFrm_mngDtlLength').value;
		///alert("mngLength=="+mngLength);
	  if(mngLength==0) { 
 			
 			document.getElementById('fileUploadTable').style.display='none';
	
	 }
	 		
	 		//showValidAmt();
	 		//showDoubleValidAmt();
	 		calTotalDoubleOtSum();
	 		calTotalSingleOtSum();
	 	}catch(e){
	 		alert("Exception ONLOAD>>>>"+e);
	 	}
	}

	function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtRegister_reset.action';
		document.getElementById('paraFrm').submit();
	}

	function callView(){
	
		///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		
		
	///Date Shift for period end
	
			
			var mngCodeVar = document.getElementById('paraFrm_managerID').value;
		if(mngCodeVar=="")
		{
				alert("Please select Manager Name.");
		  		document.getElementById('paraFrm_managerName').focus();
		 		return false;
		 }	
	
	
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="OtRegister_viewOtRegisterDtl.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	}

	function enableManuallyCalculatedOtTable() {
	 	try {
	 		var manuallyCalculatedBonusChecked = document.getElementById('paraFrm_manuallyCalculatedOtCheckBox').checked;
	 		if(manuallyCalculatedBonusChecked) {
	 	 		document.getElementById('manuallyCalculatedOtTableID').style.display = '';
	 	 		document.getElementById('hiddenCheckBoxFlag').value = "M";
	 	 		document.getElementById('systemCalculatedOtTableID').style.display = 'none';
	 	 		document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked = false;
	 	 		
	 		} else {
	 		 	document.getElementById('manuallyCalculatedOtTableID').style.display = 'none';
	 		 	document.getElementById('systemCalculatedOtTableID').style.display = '';
	 		 	document.getElementById('hiddenCheckBoxFlag').value = "S";
	 		 	document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked = true;
	 		} 
	 		
	 		document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="OtRegister_calculatedOtChecked.action";
		    document.getElementById("paraFrm").submit();
		    
		    
	 	}catch(e){
	 		alert("enableManuallyCalculatedOtAllowanceTable >>>>"+e);
	 	}
	}

	function enableSystemCalculatedOtSection() {
		try {
	 		var systemCalculatedBonusChecked = document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked;
	 		if(systemCalculatedBonusChecked) {
	 	 		document.getElementById('systemCalculatedOtTableID').style.display = '';
	 	 		document.getElementById('hiddenCheckBoxFlag').value = "S";
	 	 		document.getElementById('manuallyCalculatedOtTableID').style.display = 'none';
	 	 		document.getElementById('paraFrm_manuallyCalculatedOtCheckBox').checked = false;
	 		} else {
	 		 	document.getElementById('systemCalculatedOtTableID').style.display = 'none';
	 		 	document.getElementById('manuallyCalculatedOtTableID').style.display = '';
	 		 	document.getElementById('hiddenCheckBoxFlag').value = "M";
	 		 	document.getElementById('paraFrm_manuallyCalculatedOtCheckBox').checked = true;
	 		 	
	 		}
	 		
	 		document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="OtRegister_calculatedOtChecked.action";
		    document.getElementById("paraFrm").submit();
		     
 	}catch(e){
 		alert("enableSystemCalculatedOtSection >>>>"+e);
 	}
}
	
	function validationForSelectPeriodAndDivision() {
	try {
		//Validation for Select Period Section -- BEGINS
		
			///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		var mngName="";
		 var systemCalculatedBonusChecked = document.getElementById('paraFrm_systemCalculatedOtCheckBox').checked;
	 		if(systemCalculatedBonusChecked) {
	 	 		mngName=document.getElementById('paraFrm_managerName').value;
	 		} else {
	 		 	///document.getElementById('paraFrm_managerManuallName').value;
	 		 	mngName=document.getElementById('paraFrm_managerManuallName').value;
	 		}
	/// alert("mngName="+mngName);
		var mngLength = document.getElementById('paraFrm_mngDtlLength').value;
		///alert("mngLength=="+mngLength);
	  if(mngLength==0) { 
 			alert("Reporting structure is not defined for the Manager\n'"+mngName+"'. Please contact your HR Manager.");
 			
 			document.getElementById('fileUploadTable').style.display='none';
	
 			return false;
	 }
	 
	///	if(trim(document.getElementById('paraFrm_toDate').value) == "") {
	///			alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
	///			document.getElementById('paraFrm_toDate').focus();
	///			return false;
	///	}
		
  	///	if(!validateDate("paraFrm_fromDate","from.date")){
  	///		return false;
  	//	
  	///	}
  	/////	if(!validateDate("paraFrm_toDate","to.date")){
	///	return false;
	///	}
		
	///	if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
	///	return false;
	///	}
		 
		
	///		var mngManualCodeVar = document.getElementById('paraFrm_managerManuallID').value;
	///	if(mngManualCodeVar=="")
	///	{
	///			alert("Please select Manager Name.");
	///	  		document.getElementById('paraFrm_managerManuallName').focus();
	///	 		return false;
	///	 }	
		
		
		
	///Date Shift for period end
	
		
	////	var divisionIDVar = trim(document.getElementById('paraFrm_divisionID').value);
	
	////	if (divisionIDVar=="") {
	////		alert("please select division");
	///		document.getElementById('paraFrm_divisionName').focus();
	///		return false;
	///	}	
	//Validation for Select Period Section -- ENDS	
		return true;
	} catch(e) {
		alert("Exception in validationForSelectPeriodAndDivision >>"+e);
		return false;
	}
}

	function downloadManuallyCalculatedOtTemplate() {
	if (!validationForSelectPeriodAndDivision()) {
			return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtRegister_downloadManuallyCalculatedOtTemplate.action';
		document.getElementById('paraFrm').submit();
	}	
}
	function callManuallyUploadOt() {
	
	try {
	var manuallyUploadedBonusAllowanceFile = trim(document.getElementById('paraFrm_fileNameForManuallyCalculatedOtAllowance').value);
	
	if (!validateMandetoryFields()) {
		return false;
	} else if (manuallyUploadedBonusAllowanceFile=="") {
		alert("Please "+document.getElementById('fileNameForUploadManualBonusAllowanceLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_fileNameForManuallyCalculatedOtAllowance').focus();
		return false;
	} else {
		var extension = (manuallyUploadedBonusAllowanceFile.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS') {
				alert('Please select Microsoft 97-2003 Excel file.');
				document.getElementById('paraFrm_fileNameForManuallyCalculatedOtAllowance').value="";
				document.getElementById('paraFrm_fileNameForManuallyCalculatedOtAllowance').focus();
			} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "OtRegister_uploadBonusAllowanceDetails.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	
	} catch(e) {
	alert("Exception while validating mandetory fields >"+e);
	
  }	
}
function validateMandetoryFields() {
try {
		if (!validationForSelectPeriodAndDivision()) {
			return false;
		}
		return true;
		} catch(e) {
	alert("Exception while validating mandetory fields >"+e);
	return false;
  }	
} 

	function callViewOtRegister(){
	if (!validationForSelectPeriodAndDivision()) {
			return false;
	} else {
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action ='OtRegister_viewIndividualRatingsAlreadyProcessedRecords.action';
		document.getElementById('paraFrm').submit();
	}	
	
	}

	function callSave(){
	var con=confirm('Do you want to save the shift detail(s) ?');
	 if(con){
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="OtRegister_saveOtDetails.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
	    }
}
	function showValidAmt() {
		try
		{
		 
			var table = document.getElementById('tblSlabForMen');
						var rowCount = table.rows.length; 
			///alert(rowCount);
			var sum = 0;
			var sumDoubleOt = 0;
			for(var i=0;i<(rowCount-2);i++){
			
			if(document.getElementById('ittSingleApprOt'+i).value==""){
					document.getElementById('ittSingleApprOt'+i).value="0:0";
			}
			var invlimit= parseFloat(document.getElementById('ittSingleApprOt'+i).value);
			
			////var validAmount = Math.ceil(invlimit); 
			sum = parseFloat(sum) + parseFloat(invlimit);	
			}
			
			document.getElementById('paraFrm_totalApprSingleOtHours').value = sum.toFixed(2);
			
		}catch(e)
		{
			///alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	
	function showDoubleValidAmt() {
		try
		{
		 
		var table = document.getElementById('tblSlabForMen');
						var rowCount = table.rows.length; 
			///alert(rowCount);
			var sumDoubleOt = 0;
			for(var i=0;i<(rowCount-2);i++){
			
			if(document.getElementById('ittDoubleApprOt'+i).value==""){
					document.getElementById('ittDoubleApprOt'+i).value="0:0";
			}
			var doubleApprOt= parseFloat(document.getElementById('ittDoubleApprOt'+i).value);
			
			///var doubleAppr = Math.ceil(doubleApprOt); 
			sumDoubleOt = parseFloat(sumDoubleOt) + parseFloat(doubleApprOt);
			
			}
			document.getElementById('paraFrm_totalApprDoubleOtHours').value = sumDoubleOt.toFixed(2);
			
		}catch(e)
		{
			///alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	function callReport(type){
	if (!validationForSelectPeriodAndDivision()) {
			return false;
	} else {
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtRegister_getReport.action';
		document.getElementById('paraFrm').submit();
	}
}


	function callPage(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			} 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		 
	}
	
	function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	         
	         document.getElementById('myPage').value=pageNo;
		   
		   
		   if(pageNo==""){
				document.getElementById('paraFrm').action=action;
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').submit();
		   }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		   
			
		} 
		}catch(e){alert(e);}
	}
	
	 
	 
	
	function displayConfirmDiv(){
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=150 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
 		document.getElementById("overlay").style.display = "block";
	}
	
	function proceedWithSave(){
		try{
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		enableBlockDiv();
		document.getElementById('paraFrm').action="OtRegister_saveOtDetails.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e){} 
	}
	function proceedWithoutSave(){
		try{
		enableBlockDiv();
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		document.getElementById('paraFrm').action="OtRegister_input.action";
		document.getElementById('paraFrm').submit();
		}catch(e){}
	}
	
	function cancel(){
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	}

	function enableBlockDiv(){
		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
		}catch(e){}
	  }
	function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			}catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }
	  function otHoursCheck(i){
	  
		  try{
		    
		 		 var table = document.getElementById('tblSlabForMen');
							var rowCount = table.rows.length; 
					
					
		  	 		if(document.getElementById('ittSingleApprOt'+i).value == ""){
	 					alert("Please enter Single OT Hours");
	 					return false;
	 				}
	 				
	 				if(!validateTimeMethod('ittSingleApprOt'+i, 'Single OT Hours ')){
	 					document.getElementById('ittSingleApprOt'+i).focus();
	 					document.getElementById('ittSingleApprOt'+i).value="0:0";
	 					return false; 
	 				}
	 				
	 				
	 				if(IsValidTime(document.getElementById('ittSingleApprOt'+i).value)){			
						}
						else{
						document.getElementById('ittSingleApprOt'+i).focus();
						return false;
						}
	 			
		  }catch(e){
		 	 alert(e);
		  }
	  } 
	  
	  
	  function otDoubleHoursCheck(i){
		  try{
	 		 var table = document.getElementById('tblSlabForMen');
						var rowCount = table.rows.length; 
	  	 		if(document.getElementById('ittDoubleApprOt'+i).value == ""){
 					alert("Please enter Double OT Hours");
 					return false;
 				}
 				if(!validateTimeMethod('ittDoubleApprOt'+i, 'Double OT Hours ')){
 					document.getElementById('ittDoubleApprOt'+i).focus();
 					document.getElementById('ittDoubleApprOt'+i).value="0:0";
 					return false; 
 				}
 				if(IsValidTime(document.getElementById('ittDoubleApprOt'+i).value)){			
				}
				else{
				document.getElementById('ittDoubleApprOt'+i).focus();
				return false;
				}
		  }catch(e){
		 	 alert(e);
		  }
	  } 
	  
	  function validateTimeMethod(name, labName) {
		var time = document.getElementById(name).value;
		if(time == "") { return true; }
		
		var timeExp = /^[0-9]{2}[:]?[0-9]{2}$/;
		var timeArray = time.split(":");
		var hour = timeArray[0];
		var min = timeArray[1];
	
		if(!(time.match(timeExp)) || time.length < 5) {
			alert(labName + " should be in 24Hours HH:MM format");
			
			document.getElementById(name).focus();
			return false;
		}
	
		if(hour > 23) {
			alert("Hour " + hour + " is not valid");
			document.getElementById(name).focus();
			return false;
		}
	
		if(min > 59) {
			alert("Minute " + min + " is not valid");
			document.getElementById(name).focus();
			return false;
		}
		return true;
	}
	
	  function IsValidTime(id) {		
			var timeStr=id;//document.getElementById('fromTime'+id).value;
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			var matchArray = timeStr.match(timePat);
			if (matchArray == null) {
			alert("Please enter time in a valid format.");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];
			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }
			
			if (hour < 0  || hour > 23) {
			alert("Hour must be between 0 and 23");
			return false;
			}
			if (minute<0 || minute > 59) {
			alert ("Minute must be between 0 and 59.");
			return false;
			}
			if (second != null && (second < 0 || second > 59)) {
			alert ("Second must be between 0 and 59.");
			return false;
			}
			return true;
		}
	  
	function calTotalSingleOtSum(){
		try{ 
			var table = document.getElementById('tblSlabForMen');
							var rowCount = table.rows.length; 
				///alert(rowCount);
			var hour=0;
			var min=0;
			///alert(hour);
			///alert(min);
			for(var i=0;i<(rowCount-2);i++){
			
			if(document.getElementById('ittSingleApprOt'+i).value==""){
					document.getElementById('ittSingleApprOt'+i).value="00:00";
			} 
			if(document.getElementById('ittSingleApprOt'+i).value=="0.00"){
			
					document.getElementById('ittSingleApprOt'+i).value="00:00";
			}
				if(document.getElementById('ittSingleApprOt'+i).value=="NaN"){
				   document.getElementById('ittSingleApprOt'+i).value="00:00"; 
				}
				var time=document.getElementById('ittSingleApprOt'+i).value;
				var timeSplit=time.split(':');
				hour =eval(hour)+eval(timeSplit[0]);
				min =eval(min)+eval(timeSplit[1]);
			} 
			hour=eval(hour)+eval(Math.floor(min/60));
			//alert(eval(min%60));
			min=eval(min%60);
			document.getElementById('totalApprSingleOtHours').value=hour+':'+min;
			
			////document.getElementById('total').value=hour+':'+min;
			//alert(eval(min/60));
		}catch(e){
			///alert(e);
		}
	}
	function calTotalDoubleOtSum(){
		try{ 
			var table = document.getElementById('tblSlabForMen');
							var rowCount = table.rows.length; 
				///alert(rowCount);
			var hour=0;
			var min=0;
			//alert(hour);
			//alert(min);
			for(var i=0;i<(rowCount-2);i++){
			
			if(document.getElementById('ittDoubleApprOt'+i).value==""){
					document.getElementById('ittDoubleApprOt'+i).value="00:00";
			}
			if(document.getElementById('ittDoubleApprOt'+i).value=="0.00"){
			
					document.getElementById('ittDoubleApprOt'+i).value="00:00";
			}
				if(document.getElementById('ittDoubleApprOt'+i).value=="NaN"){
				   document.getElementById('ittDoubleApprOt'+i).value="00:00";
				}
				var time=document.getElementById('ittDoubleApprOt'+i).value;
				var timeSplit=time.split(':');
				hour =eval(hour)+eval(timeSplit[0]);
				min =eval(min)+eval(timeSplit[1]);
			}
			hour=eval(hour)+eval(Math.floor(min/60));
			//alert(eval(min%60));
			min=eval(min%60);
			document.getElementById('totalApprDoubleOtHours').value=hour+':'+min;
			////document.getElementById('total').value=hour+':'+min;
			//alert(eval(min/60));
		}catch(e){
			///alert(e);
		}
	}
	
</script>