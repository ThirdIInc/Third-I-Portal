<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<s:form action="BonusAllowance" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
<s:hidden name="dataPath" />
<s:hidden name="bonusAllowanceStatus" />
<s:hidden name="bonuseStatus" />
<s:hidden name="bonusAllowanceID"/>
<s:hidden name="fileNameManuallyUploadedBonusAllowance"/>
<s:hidden name="fileNameIndividualRatingsUploaded"/>
<s:hidden name="processedFromMonth" />
<s:hidden name="processedToMonth" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Bonus/Allowance
					Processing </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td valign="bottom" class="txt">
			 	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="selectPeriod" id="selectPeriod" ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label></b>
							:<font color="red">*</font>
						 </td>
					</tr>
				<s:if test="processedRecordFlag">	
					<tr>
						 <td width="20%">
						 	<label name="fromPeriod" id="fromPeriod" ondblclick="callShowDiv(this);"><%=label.get("fromPeriod")%></label>
							:
						 </td>
						 <td width="30%">
						 	<s:select name="fromMonth" headerKey="-1"  headerValue="----Select----" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
						 	&nbsp;<s:textfield name="fromYear" readonly="true" cssStyle="background-color: #F2F2F2;" size="10" maxLength="4" onkeypress="return numbersOnly();"/>			
						 </td>
						 <td width="20%">
						 	<label name="toPeriod" id="toPeriod" ondblclick="callShowDiv(this);"><%=label.get("toPeriod")%></label>
							:
						 </td>
						 <td width="30%">
						 	<s:select name="toMonth" headerKey="-1"  headerValue="----Select----" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>
						 	&nbsp;<s:textfield name="toYear" readonly="true" cssStyle="background-color: #F2F2F2;" size="10" maxLength="4" onkeypress="return numbersOnly();"/>		
						 </td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						 <td>
						 	<label name="fromPeriod" id="fromPeriod" ondblclick="callShowDiv(this);"><%=label.get("fromPeriod")%></label>
							:
						 </td>
						 <td>
						 	<s:select name="fromMonth" headerKey="-1" headerValue="----Select----" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" onchange="setHiddenFromAndToMonth();"></s:select>
						 	&nbsp;<s:textfield name="fromYear" size="10" maxLength="4" onkeypress="return numbersOnly();"/>			
						 </td>
						 <td>
						 	<label name="toPeriod" id="toPeriod" ondblclick="callShowDiv(this);"><%=label.get("toPeriod")%></label>
							:
						 </td>
						 <td>
						 	<s:select name="toMonth" headerKey="-1" headerValue="----Select----" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" onchange="setHiddenFromAndToMonth();"></s:select>
						 	&nbsp;<s:textfield name="toYear" size="10" maxLength="4" onkeypress="return numbersOnly();"/>		
						 </td>
					</tr>
				</s:else>	
				</table>
			</td>
		</tr>
		
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="selectFilters" id="selectFilters" ondblclick="callShowDiv(this);"><%=label.get("selectFilters")%></label></b>: 
						 </td>
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
						 </td>
			 			<td width="80%">
			 				<s:textfield name="divisionName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;" />
			 				<s:hidden name="divisionID"/>
			 			<s:if test="processedRecordFlag">&nbsp;</s:if>
			 			<s:else>
			 				<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
								onclick="callsF9(500,325,'BonusAllowance_f9Division.action');">	
			 			</s:else>
			 			</td>
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
						 </td>
			 			<td>
			 				<s:hidden name="branchID"/>
			 				<s:textarea cols="100" rows="1" theme="simple"	readonly="true" cssStyle="background-color: #F2F2F2;" name="branchName"  />
			 			<s:if test="processedRecordFlag">&nbsp; </s:if>
			 			<s:else>
			 				<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_branchName',350,250,'BonusAllowance_f9Branch.action',event,'false','no','right')">
			 			</s:else>	
			 			</td>	
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="paybill" id="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label>
							:
						 </td>
						 <td colspan="4">
			 				<s:hidden name="paybillID"/>
			 				<s:textarea cols="100" rows="1" theme="simple"	readonly="true" cssStyle="background-color: #F2F2F2;" name="paybillName"  />
			 			<s:if test="processedRecordFlag"> &nbsp; </s:if>
			 			<s:else> 
			 				<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'BonusAllowance_f9Paybill.action',event,'false','no','right')">
			 			</s:else>	
			 			</td>	
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:
						 </td>
						 <td colspan="4">
			 				<s:hidden name="departmentID"/>
			 				<s:textarea cols="100" rows="1" theme="simple"	readonly="true" cssStyle="background-color: #F2F2F2;" name="departmentName"  />
			 			<s:if test="processedRecordFlag"> &nbsp; </s:if>
			 			<s:else>
			 				<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_departmentName',350,250,'BonusAllowance_f9Department.action',event,'false','no','right')">
			 			</s:else>	
			 			</td>	
					</tr>
					<tr>
						 <td width="20%">
						 	<label class="set" id="employee" name="employee"><%=label.get("employee")%></label>:
						 </td>
						 <td colspan="4">
							<s:hidden name="filterEmpId"/>
							<s:hidden name="filterEmpToken"/>
			 				<s:textarea name="filterEmpName" cols="100" rows="1" readonly="true" cssStyle="background-color: #F2F2F2;"/>
			 			<s:if test="processedRecordFlag"> &nbsp; </s:if>
			 			<s:else>
			 				<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
							onclick="validateFilteredEmployee();">
			 			</s:else>	
			 			</td>	
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="selectParameters" id="selectParameters" ondblclick="callShowDiv(this);"><%=label.get("selectParameters")%></label></b>:
						 </td>
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="salaryMonthLabel" id="salaryMonthLabel" ondblclick="callShowDiv(this);"><%=label.get("salaryMonthLabel")%></label> :<font color="red">*</font>
						 </td>
						 <td width="30%">
						 	<s:select name="salaryMonth" headerKey="-1" headerValue="----Select----" list="#{'1':'January','2':'February','3':'March','4':'April','5':'May', '6':'June',
						 			'7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"></s:select>		
						 </td>
						 <td width="20%">
						 	<label name="salaryYearLabel" id="salaryYearLabel" ondblclick="callShowDiv(this);"><%=label.get("salaryYearLabel")%></label> :<font color="red">*</font>
						 </td>
						 <td width="30%" colspan="2">
						 	<s:textfield name="salaryYear" size="10" maxLength="4"/>
						 </td>
					</tr>
					<tr>
						 <td width="20%">
						 	<label name="payInComponentLabel" id="payInComponentLabel" ondblclick="callShowDiv(this);"><%=label.get("payInComponentLabel")%></label> :<font color="red">*</font>
						 </td>
						 <td colspan="4" width="80%">
						 	<s:hidden name="payInComponentID"/>
						 	<s:hidden name="payInComponentAbbreviation"/>
						 	<s:textfield name="payInComponentName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;" />
						 	<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'BonusAllowance_f9PayInComponent.action');">	
						 </td>
					</tr>		
					<tr>
						 <td>
						 	<s:checkbox name="payInSalaryCheckBox" onclick="showSalaryMonthYear();" />&nbsp;
						 	<label name="payInSalary" id="payInSalary" ondblclick="callShowDiv(this);"><%=label.get("payInSalary")%></label>
						 </td>
						 <td>
						 	<!--  onclick="deductIncomeTaxCeckedFunction();" -->
						 	<s:checkbox name="deductIncomeTaxCheckBox"/>&nbsp;
						 	<label name="deductIncomeTax" id="deductIncomeTax" ondblclick="callShowDiv(this);"><%=label.get("deductIncomeTax")%></label>
						 </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="4">
						 	<b><label name="calculationOptionLabel" id="calculationOptionLabel" ondblclick="callShowDiv(this);"><%=label.get("calculationOptionLabel")%></label></b>
						 </td>
					</tr>
					<tr>
						 <td colspan="2">
						 	<s:checkbox name="systemCalculatedBonusCheckBox" onclick="enableSystemCalculatedBonusSection();"/>
						 	<label name="systemCalculatedBonusLabel" id="systemCalculatedBonusLabel" ondblclick="callShowDiv(this);"><%=label.get("systemCalculatedBonusLabel")%></label>
						 </td>
						 <td colspan="2">
						 	<s:checkbox name="manuallyCalculatedBonusCheckBox" onclick="enableManuallyCalculatedBonusAllowanceTable();"/>
						 	<label name="manuallyCalculatedBonusLabel" id="manuallyCalculatedBonusLabel" ondblclick="callShowDiv(this);"><%=label.get("manuallyCalculatedBonusLabel")%></label>
						 </td>
					</tr>
				</table>
			</td>
		</tr>
		
<!-- If "System Calculated Bonus/Allowance" is Checked then enable below defined sections = BEGINS -->	
		<tr id="systemCalculatedBonusAllowanceTableID">
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<s:checkbox name="calculatePaidDaysCheckBox"/>
						 	<label name="calculatePaidDaysLabel" id="calculatePaidDaysLabel" ondblclick="callShowDiv(this);"><%=label.get("calculatePaidDaysLabel")%></label>
						 </td>
					</tr>
					<tr>
						 <td colspan="3" width="45%">
						 	<label name="calculateBonusComponentLabel" id="calculateBonusComponentLabel" ondblclick="callShowDiv(this);"><%=label.get("calculateBonusComponentLabel")%></label> :<font color="red">*</font>
						 </td>
						 
						 <td width="25%">
							 <s:textfield name="calCulatedBonusComponents" size="30" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
						 </td>
						 <td width="30%">
							 <input type="button" class="token" name="calculateFormula" id="paraFrm_calculateFormula" value="Formula" 
								onclick="callFormulaBuilder('paraFrm_calCulatedBonusComponents');"/>		
						 </td>
					</tr>
					
					<tr>
						 <td colspan="5">
						 	<b><label name="calculationMethodLabel" id="calculationMethodLabel" ondblclick="callShowDiv(this);"><%=label.get("calculationMethodLabel")%></label></b>
						 </td>
					</tr>
					
					<tr>	 
						 <td colspan="2" width="40%">
							 <s:checkbox name="slabwiseMethodCheckbox" onclick="showSlabWiseSection();"/>&nbsp;
							 <label name="slabwiseMethodLabel" id="slabwiseMethodLabel" ondblclick="callShowDiv(this);"><%=label.get("slabwiseMethodLabel")%></label>
						 </td>
						 <td colspan="3" width="60%">
							 | Note: Define slab wise Bonus/Allowance percentage.	
						 </td>
					</tr>
					<tr>	 
						 <td colspan="2" width="40%">
							 <s:checkbox name="individualRatingsMethodCheckbox" onclick="showIndividualRatingsSection();"/>&nbsp;
							 <label name="individualRatingsMethodLabel" id="individualRatingsMethodLabel" ondblclick="callShowDiv(this);"><%=label.get("individualRatingsMethodLabel")%></label>
						 </td>
						 <td colspan="3" width="60%">
							 | Note: Upload individual performance /calculation rating. E.g. 80%. System calculated Bonus/Allowance = (Basic) x 80/100
						 </td>
					</tr>
					<tr>	 
						 <td colspan="2" width="40%">
							 <s:checkbox name="flatRateMethodCheckbox" onclick="showFlatRateSection();"/>&nbsp;
							 <label name="flatRateMethodLabel" id="flatRateMethodLabel" ondblclick="callShowDiv(this);"><%=label.get("flatRateMethodLabel")%></label>
						 </td>
						 <td colspan="3" width="60%">
							  | Note: Define a flat rate. System calculates Bonus/Allowance with this rate for all employees. E.g. Flat rate 15. System calculated Bonus/Allowance = (Basic) x 15/100	
						 </td>
					</tr>
				</table>
			</td>
		</tr>
	
	<!-- Slab Defins Method Table = BEGINS -->	
		<tr id="slabWiseBonusCalculationMethodTableID">
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="2">
						 	<b><label name="defineSlabLabel" id="defineSlabLabel" ondblclick="callShowDiv(this);"><%=label.get("defineSlabLabel")%></label></b>
						 </td>
						 <td width="30%">&nbsp;</td>
						 <td colspan="2" align="right">
						 	<a href="#" onclick="addRowForSlabs('addSlabsRowTableID');"><b><u>Add Row</u></b></a>
						 </td>
					</tr>
					<tr>
						 <td colspan="5">
						 	<table id="addSlabsRowTableID" width="100%">
								<tr>
									<td class="formth" width="20%">
										<label name="slabFromAmountLabel" id="slabFromAmountLabel" ondblclick="callShowDiv(this);"> <%=label.get("slabFromAmountLabel")%></label><font color="red">*</font>
									</td>
									<td class="formth" width="20%"><label name="slabToAmountLabel"
										id="slabToAmountLabel" ondblclick="callShowDiv(this);"> <%=label.get("slabToAmountLabel")%></label><font color="red">*</font>
									</td>
									<td class="formth" width="10%"><label
										name="slabPercentageLabel" id="slabPercentageLabel"
										ondblclick="callShowDiv(this);"> <%=label.get("slabPercentageLabel")%></label><font color="red">*</font>
									</td>
									<td class="formth" width="20%"><label name="slabMinLabel"
										id="slabMinLabel" ondblclick="callShowDiv(this);"> <%=label.get("slabMinLabel")%></label>
									</td>
									<td class="formth" width="20%"><label name="slabMaxLabel"
										id="slabMaxLabel" ondblclick="callShowDiv(this);"> <%=label.get("slabMaxLabel")%></label>
									</td>
									<td class="formth" width="10%"><label name="slabDeleteLabel"
										id="slabDeleteLabel" ondblclick="callShowDiv(this);"> <%=label.get("slabDeleteLabel")%></label>
									</td>
								</tr>

							<%
									int slabsCount = 0;
							%>
							<s:iterator value="slabDetailsIterator">
								<tr>
									<td align="center" class="sortableTD" width="20%">
										<input type="hidden" name='slabItrID' id="paraFrm_slabItrID<%=slabsCount%>"
										value='<s:property value="slabItrID"/>' />&nbsp; 
										<input type="text" name='slabItrFromAmount'
										id="paraFrm_slabItrFromAmount<%=slabsCount%>"
										value='<s:property value="slabItrFromAmount"/>' 
										height="22" size="18"/> 
									</td>
								<td align="center" class="sortableTD" width="20%">
									<input type="text" name='slabItrToAmount'
										id="paraFrm_slabItrToAmount<%=slabsCount%>"
										value='<s:property value="slabItrToAmount"/>' 
										height="22"  size="18"/>
								</td>
								<td align="center" class="sortableTD" width="10%">
									<input type="text" name='slabItrPercentage'
									id="paraFrm_slabItrPercentage<%=slabsCount%>"
									value='<s:property value="slabItrPercentage"/>' 
									height="22"  size="10"/>
								</td>
								
								<td align="center" class="sortableTD" width="20%">
									<input type="text" name='slabItrMin'
									id="paraFrm_slabItrMin<%=slabsCount%>"
									value='<s:property value="slabItrMin"/>' 
									height="22" size="15"/>
								</td>
								
								<td align="center" class="sortableTD" width="20%">
									<input type="text" name='slabItrMax'
									id="paraFrm_slabItrMax<%=slabsCount%>"
									value='<s:property value="slabItrMax"/>' 
									height="22" size="15"/>
								</td>
								
								<td align="center" class="sortableTD" width="10%">
									<img src="../pages/common/css/icons/delete.gif" 
										onclick="deleteCurrentRow(this);" />
								</td>
							</tr>
							<%
								slabsCount++;
							%>
						</s:iterator>
						
					</table>
				   </td>
				  </tr>
				</table>
			</td>
		</tr>
	<!-- Slab Defins Method Table = ENDS -->	
		
	<!-- Individual Ratins Method Table = BEGINS -->	
		<tr id="individualRatinsMethodTableID">
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="uploadIndividualRatingsLabel" id="uploadIndividualRatingsLabel" ondblclick="callShowDiv(this);"><%=label.get("uploadIndividualRatingsLabel")%></label></b>
						 </td>
					</tr>
					<tr>
						 <td width="40%">
						 	<label name="downLoadTemplateForIndividualRatingsLabel" id="downLoadTemplateForIndividualRatingsLabel" ondblclick="callShowDiv(this);"><%=label.get("downLoadTemplateForIndividualRatingsLabel")%></label>
						 </td>
						 <td colspan="4" width="60%">
						 	<a href="#" onclick="downloadTemplateForIndividualRatings();"><b><u>Download Template</u></b></a>
						 </td>
					</tr>
					
					<tr>
						 <td width="40%">
						 	<label name="fileNameForIndividualRatingsLabel" id="fileNameForIndividualRatingsLabel" ondblclick="callShowDiv(this);"><%=label.get("fileNameForIndividualRatingsLabel")%></label> :
						 	<font color="red">*</font>
						 </td>
						 <td colspan="4" width="60%">
						 	<s:textfield name="fileNameForIndividualRatings" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
						 </td>
					</tr>
					
					<s:if test="displayNoteFlag">					
						<s:if test="status == 'Success'">					
							<tr>
								<td width="40%">
									<FONT color="green"><s:property value="status" /></FONT>
								</td>
								 <td colspan="4" width="60%">
						 			<a href="#" onclick="viewIndividualRatingsUploadedFile();">
										<font color="blue"><u>Please click here to view uploaded file.</u></font>
									</a>
						 		</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="40%">
									<FONT color="red"><s:property value="status" /></FONT>
								</td>
								<td colspan="4" width="60%">
									<FONT color="red">Note : <s:property value="note"/></FONT>
						 		</td>
							</tr>
							 
							<tr>
								<td width="100%" colspan="5" align="center">
									<a href="#" onclick="viewIndividualRatingsUploadedFile();">
										<font color="blue"><u>Please click here to view uploaded file.</u></font>
									</a>
								</td>
							</tr>
						</s:else>
					</s:if>
					<tr>
						 <td width="40%">&nbsp;</td>
						 <td colspan="4" width="60%">
						 <s:if test="bonuseStatus!='LL'">
						 	<input type="button" value="   Browse   " class="token" onclick="selectFile('fileNameForIndividualRatings')"/>&nbsp;
							<input type="button" class="token" 	value=" Upload File " onclick="callIndividualRatingsUploadBonus();" />
					</s:if>
					<!-- 
							<s:if test="viewAlreadyUploadedRatingsDetailsFlag">	
								<input type="button" class="token" 	value=" View Uploaded Records " onclick="viewAlreadyProcessedRatings();" />
						   </s:if>
					 -->	
					
						 </td>
					</tr>
				</table>
			</td>
		</tr>
	<!-- Individual Ratins Method Table = ENDS -->	
	
	<!-- Flat Rate Method Table = BEGINS -->	
		<tr id="flatRateMethodTableID">
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td colspan="5">
						 	<b><label name="defineFlatRateLabel" id="defineFlatRateLabel" ondblclick="callShowDiv(this);"><%=label.get("defineFlatRateLabel")%></label></b>
						 </td>
					</tr>
					<tr>
						 <td width="40%">
						 	<label name="flatRateBonusAllowanceCalculationLabel" id="flatRateBonusAllowanceCalculationLabel" ondblclick="callShowDiv(this);"><%=label.get("flatRateBonusAllowanceCalculationLabel")%></label>
						 </td>
						 <td colspan="4" width="60%">
						 	<s:textfield name="flatRateBonusAllowanceCalculation" onkeypress="return numbersWithDot();"/>
						 </td>
					</tr>
				
				<!-- 
					<tr>
						 <td width="40%">
						 	<b><label name="flatRateMaximumBonusAllowanceLabel" id="flatRateMaximumBonusAllowanceLabel" ondblclick="callShowDiv(this);"><%=label.get("flatRateMaximumBonusAllowanceLabel")%></label></b>
						 </td>
						 <td colspan="4" width="60%">
						 	<s:textfield name="flatRateMaximumBonusAllowance"/>
						 </td>
					</tr>
					
					<tr>
						 <td width="40%">
						 	<b><label name="flatRateMinimumBonusAllowanceLabel" id="flatRateMinimumBonusAllowanceLabel" ondblclick="callShowDiv(this);"><%=label.get("flatRateMinimumBonusAllowanceLabel")%></label></b>
						 </td>
						 <td colspan="4" width="60%">
						 	<s:textfield name="flatRateMinimumBonusAllowance"/>
						 </td>
					</tr>
				 -->	
					
				</table>
			</td>
		</tr>
	<!-- Flat Rate Method Table = BEGINS -->
<!-- If "System Calculated Bonus/Allowance" is Checked then enable below defined sections = BEGINS -->	
	
	<!-- Manually Calculated Bonus/Allowance Table = BEGINS -->	
		<tr id="manuallyCalculatedBonusAllowanceTableID">
			<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						 <td width="40%">
						 	<label name="downLaodTemplateForUploadingManualBonusLabel" id="downLaodTemplateForUploadingManualBonusLabel" ondblclick="callShowDiv(this);"><%=label.get("downLaodTemplateForUploadingManualBonusLabel")%></label>
						 </td>
						 <td colspan="4" width="60%">
						 	<a href="#" onclick="downloadManuallyCalculatedBonusTemplate();"><b><u>Download Template</u></b></a>
						 </td>
					</tr>
					
					<tr>
						 <td width="40%">
						 	<label name="fileNameForUploadManualBonusAllowanceLabel" id="fileNameForUploadManualBonusAllowanceLabel" ondblclick="callShowDiv(this);"><%=label.get("fileNameForUploadManualBonusAllowanceLabel")%></label> :
						 	<font color="red">*</font>
						 </td>
						 <td colspan="4" width="60%">
						 	<s:textfield name="fileNameForManuallyCalculatedBonusAllowance" readonly="true" size="30" cssStyle="background-color: #F2F2F2;"/>
						 </td>
					</tr>
					<s:if test="displayNoteFlag">					
						<s:if test="status == 'Success'">					
							<tr>
								<td width="40%">
									<FONT color="green"><s:property value="status" /></FONT>
								</td>
								<td colspan="4" width="60%">
						 			<a href="#" onclick="viewManualluUploadedFile();">
										<font color="blue"><u>Please click here to view uploaded file.</u></font>
									</a>
						 		</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="40%">
									<FONT color="red"><s:property value="status" /></FONT>
								</td>
								<td colspan="4" width="60%">
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
					</s:if>
					<tr>
						 <td width="40%">&nbsp;</td>
						 <td colspan="4" width="60%">
						  <s:if test="bonuseStatus!='LL'">
						 	<input type="button" value="   Browse   " class="token" onclick="selectFile('fileNameForManuallyCalculatedBonusAllowance')"/>&nbsp;
							<input type="button" class="token" 	value=" Upload File " onclick="callManuallyUploadBonus()" />
						</s:if>
					<!-- 
							<s:if test="viewAlreadyUploadedBonusFlag">	
								<input type="button" class="token" 	value=" View Uploaded Records " onclick="viewAlreadyUploadedBonus();" />
						   </s:if>
					 -->	
							
						 </td>
					</tr>
				</table>
			</td>
		</tr>
	<!-- Manually Calculated Bonus/Allowance Table = BEGINS -->	
		
		<tr>
			<td valign="bottom" class="txt">
			 	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		
	</table>
</s:form>

<script>
onLoadShowThisData();

function selectFile(fieldName) {
	var dataPath = document.getElementById('paraFrm_dataPath').value;
	window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}

//For Manually Uploaded Bonus Allowance -- BEGINS
function viewManualluUploadedFile() {
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'BonusAllowance_viewManuallyUploadedBonusFile.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}

//BEGINS This method is used to view data(Bonus/Allowance) available in the database for given bonus code
function viewAlreadyUploadedBonus() {
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'BonusAllowance_viewAlreadyProcessedBonusAllowanceRecords.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
//ENDS This method is used to view data(Bonus/Allowance) available in the database for given bonus code

function callManuallyUploadBonus() {
	var manuallyUploadedBonusAllowanceFile = trim(document.getElementById('paraFrm_fileNameForManuallyCalculatedBonusAllowance').value);
	
	if (!validateMandetoryFields()) {
		return false;
	} else if (manuallyUploadedBonusAllowanceFile=="") {
		alert("Please "+document.getElementById('fileNameForUploadManualBonusAllowanceLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_fileNameForManuallyCalculatedBonusAllowance').focus();
		return false;
	} else {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "BonusAllowance_uploadBonusAllowanceDetails.action";
		document.getElementById('paraFrm').submit();
	}
}
//For Manually Uploaded Bonus Allowance -- ENDS



//For Individual Ratings Bonus Allowance -- BEGINS
function viewIndividualRatingsUploadedFile() {
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'BonusAllowance_viewIndividualRatingsUploadedFile.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
//BEGINS This method is used to view data(Ratings) available in the database for given bonus code
function viewAlreadyProcessedRatings() {
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'BonusAllowance_viewIndividualRatingsAlreadyProcessedRecords.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
//ENDS This method is used to view data(Ratings) available in the database for given bonus code


function callIndividualRatingsUploadBonus() {
	var individualRatingsBonusAllowanceFile = trim(document.getElementById('paraFrm_fileNameForIndividualRatings').value);
	
	if (!validateMandetoryFields()) {
		return false;
	} else if (individualRatingsBonusAllowanceFile=="") {
		alert("Please "+document.getElementById('fileNameForIndividualRatingsLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_fileNameForIndividualRatings').focus();
		return false;
	} else {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "BonusAllowance_uploadIndividualRatingsDetails.action";
		document.getElementById('paraFrm').submit();
	}
}
//For Individual Ratings Bonus Allowance -- ENDS



function onLoadShowThisData() {
 	try {
 		var table = document.getElementById('addSlabsRowTableID'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;
		 if (iteration==0) {
		 	addRowForSlabs('addSlabsRowTableID');
		 }
 		
 		var payInSalaryChecked = document.getElementById('paraFrm_payInSalaryCheckBox').checked;
 		
 		/*if(payInSalaryChecked) {
 	 		document.getElementById('showSalaryMonthYearID').style.display = '';
 	 		document.getElementById('showPayInComponentID').style.display = '';
 		} else {
 		 	document.getElementById('showSalaryMonthYearID').style.display = 'none';
 		 	document.getElementById('showPayInComponentID').style.display = 'none';
 		}*/
 		
 		var manuallyCalculatedBonusChecked = document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked;
 		if(manuallyCalculatedBonusChecked) {
 	 		document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = '';
 		} else {
 		 	document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = 'none';
 		} 
 		
 		var systemCalculatedBonusChecked = document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked;
 		var slabwiseMethodChecked = document.getElementById('paraFrm_slabwiseMethodCheckbox').checked;
 		var individualRatingsMethodChecked = document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked;
 		var flatRateMethodChecked = document.getElementById('paraFrm_flatRateMethodCheckbox').checked;
 		if(systemCalculatedBonusChecked) {
 	 		document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = '';
 	 		
 	 		if(slabwiseMethodChecked) {
 	 			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = '';
 	 			document.getElementById('individualRatinsMethodTableID').style.display = 'none';
 	 			document.getElementById('flatRateMethodTableID').style.display = 'none';
 	 		}
 	 		
 	 		if(individualRatingsMethodChecked) {
 	 			document.getElementById('individualRatinsMethodTableID').style.display = '';
 	 			document.getElementById('flatRateMethodTableID').style.display = 'none';
 	 			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
 	 		}
			
 	 		if(flatRateMethodChecked) {
 	 			document.getElementById('flatRateMethodTableID').style.display = '';
 	 			document.getElementById('individualRatinsMethodTableID').style.display = 'none';
 	 			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
 	 		}
 		} else {
 		 	document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = 'none';
 		 	document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = false;
 		 	document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
 		 	
 		 	document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = false;
 		 	document.getElementById('individualRatinsMethodTableID').style.display = 'none';
 		 	
 		 	document.getElementById('paraFrm_flatRateMethodCheckbox').checked = false;
 		 	document.getElementById('flatRateMethodTableID').style.display = 'none';
 		} 
 	}catch(e){
 		alert("Exception ONLOAD>>>>"+e);
 	}
}
 
function showSalaryMonthYear() {
 	try {
 		var payInSalaryChecked = document.getElementById('paraFrm_payInSalaryCheckBox').checked;
 		/*if(payInSalaryChecked) {
 	 		document.getElementById('showSalaryMonthYearID').style.display = '';
 	 		document.getElementById('showPayInComponentID').style.display = '';
 	 		//document.getElementById('paraFrm_deductIncomeTaxCheckBox').checked = false;
 		} else {
 		 	document.getElementById('showSalaryMonthYearID').style.display = 'none';
 		 	document.getElementById('showPayInComponentID').style.display = 'none';
 		 	//document.getElementById('paraFrm_deductIncomeTaxCheckBox').checked = true;
 		} */
 	}catch(e){
 		alert("showSalaryMonthYear >>>>"+e);
 	}
}


function enableManuallyCalculatedBonusAllowanceTable() {
 	try {
 		var manuallyCalculatedBonusChecked = document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked;
 		if(manuallyCalculatedBonusChecked) {
 	 		document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = '';
 	 		document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = 'none';
 	 		document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked = false;
 	 		document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = false; 
 	 		document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
 	 		document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = false; 
 	 		document.getElementById('individualRatinsMethodTableID').style.display = 'none';
 	 		document.getElementById('paraFrm_flatRateMethodCheckbox').checked = false; 
 	 		document.getElementById('flatRateMethodTableID').style.display = 'none';
 		} else {
 		 	document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = 'none';
 		 	document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = '';
 		 	document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked = true;
 		} 
 	}catch(e){
 		alert("enableManuallyCalculatedBonusAllowanceTable >>>>"+e);
 	}
}

function enableSystemCalculatedBonusSection() {
	try {
 		var systemCalculatedBonusChecked = document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked;
 		if(systemCalculatedBonusChecked) {
 	 		document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = '';
 	 		document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = 'none';
 	 		document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked = false;
 		} else {
 		 	document.getElementById('systemCalculatedBonusAllowanceTableID').style.display = 'none';
 		 	document.getElementById('manuallyCalculatedBonusAllowanceTableID').style.display = '';
 		 	document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked = true;
 		 	
 		 	document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = false;
			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
 		 	document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = false;
			document.getElementById('individualRatinsMethodTableID').style.display = 'none';
			document.getElementById('flatRateMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_flatRateMethodCheckbox').checked = false;
 		} 
 	}catch(e){
 		alert("enableSystemCalculatedBonusSection >>>>"+e);
 	}
}

function deductIncomeTaxCeckedFunction(){
	try {
 		var deductIncomeTaxChecked = document.getElementById('paraFrm_deductIncomeTaxCheckBox').checked;
 		if(deductIncomeTaxChecked) {
 	 		//document.getElementById('showSalaryMonthYearID').style.display = 'none';
 	 		//document.getElementById('showPayInComponentID').style.display = 'none';
 	 		document.getElementById('paraFrm_payInSalaryCheckBox').checked = false;
 		} else {
 		 	//document.getElementById('showSalaryMonthYearID').style.display = '';
 	 		//document.getElementById('showPayInComponentID').style.display = '';
 		 	document.getElementById('paraFrm_payInSalaryCheckBox').checked = true;
 		} 
 	}catch(e){
 		alert("deductIncomeTaxCeckedFunction >>>>"+e);
 	}
}

function showSlabWiseSection() {
	try {
		var slabwiseMethodChecked = document.getElementById('paraFrm_slabwiseMethodCheckbox').checked;
		if(slabwiseMethodChecked) {
			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = '';
			document.getElementById('individualRatinsMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = false;
			document.getElementById('flatRateMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_flatRateMethodCheckbox').checked = false;
		} else {
		    document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = true;
		}
	} catch(e) {
		alert("showSlabWiseSection >>>>"+e);
	}
}

function showIndividualRatingsSection() {
	try {
		var individualRatingsMethodChecked = document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked;
		if(individualRatingsMethodChecked) {
			document.getElementById('individualRatinsMethodTableID').style.display = '';
			document.getElementById('flatRateMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_flatRateMethodCheckbox').checked = false;
			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = false;
		} else {
		    document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = true;
		}
	} catch(e) {
		alert("showIndividualRatingsSection >>>>"+e);
	}
}


function showFlatRateSection() {
	try {
		var flatRateMethodChecked = document.getElementById('paraFrm_flatRateMethodCheckbox').checked;
		if(flatRateMethodChecked) {
			document.getElementById('flatRateMethodTableID').style.display = '';
			document.getElementById('slabWiseBonusCalculationMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_slabwiseMethodCheckbox').checked = false;
			document.getElementById('individualRatinsMethodTableID').style.display = 'none';
			document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked = false;
		} else {
		    document.getElementById('paraFrm_flatRateMethodCheckbox').checked = true;
		}
	} catch(e) {
		alert("showFlatRateSection >>>>"+e);
	}
}


function backtolistFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='BonusAllowance_input.action';
	document.getElementById('paraFrm').submit();
}

function resetFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='BonusAllowance_reset.action';
	document.getElementById('paraFrm').submit();
}

function viewemployeeFun() {
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action = 'BonusAllowance_viewEmployeeRecords.action';
	document.getElementById('paraFrm').submit();
}

function processFun() {
	try {
		var fileNameManuallyUploadedBonusAllowance = trim(document.getElementById('paraFrm_fileNameManuallyUploadedBonusAllowance').value);
		var fileNameIndividualRatingsUploaded = trim(document.getElementById('paraFrm_fileNameIndividualRatingsUploaded').value);
		var manuallyCalculatedBonusCheckBoxVar = document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked;
		var systemCalculatedBonusCheckBoxVar = document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked;
		var individualRatingsMethodCheckboxVar = document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked;
		if(!validateMandetoryFields()) {
			return false;
		} else if (manuallyCalculatedBonusCheckBoxVar) {
			if (fileNameManuallyUploadedBonusAllowance=="") {
				alert("Please select downloaded template for Bonus if any, otherwise first download respective template.\n Then upload bonus data");
				return false;
			}
		} else if (systemCalculatedBonusCheckBoxVar) {
			if (individualRatingsMethodCheckboxVar) {
			if (fileNameIndividualRatingsUploaded=="") {
				alert("Please select downloaded template for Individual ratings if any, otherwise first download respective template.\n Then upload individual ratings");
				return false;
			}
		}
	   }  
	
	var con = confirm("Do you really want to process this record?");
	if (con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'BonusAllowance_processBonusAllowance.action?buttonAction=ProcessAction';
		document.getElementById('paraFrm').submit();		
	} else {
		return false;
	}
  } catch(e) {
  	alert("Exception in processFun >>>>"+e);
  	return false;
  }
}


function reprocessFun() {
	try {
		if(!validateMandetoryFields()) {
			return false;
		} 
	
	var con = confirm("Do you really want to re-process this record?");
	if (con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'BonusAllowance_processBonusAllowance.action?buttonAction=ReProcessAction';
		document.getElementById('paraFrm').submit();		
	} else {
		return false;
	}
  } catch(e) {
  	alert("Exception in re-processFun >>>>"+e);
  	return false;
  }
}

function validateMandetoryFields() {
try {
		if (!validationForSelectPeriodAndDivision()) {
			return false;
		} 
//Validation if Pay in Salary is checked -- BEGINS	
	var payInSalaryCheckBoxVar = document.getElementById('paraFrm_payInSalaryCheckBox').checked;
	var salaryMonthVar = document.getElementById('paraFrm_salaryMonth').value;
	var salaryYearVar = trim(document.getElementById('paraFrm_salaryYear').value);
	var payInComponentIDVar =  trim(document.getElementById('paraFrm_payInComponentID').value);
	
	//if (payInSalaryCheckBoxVar) {
		if (salaryMonthVar=="-1") {
			alert("Please select "+document.getElementById('salaryMonthLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_salaryMonth').focus();
			return false;
		} 
		
		if (salaryYearVar=="") {
			alert("Please enter "+document.getElementById('salaryYearLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_salaryYear').focus();
			return false;
		} else if(salaryYearVar.length < 4) {
   			alert(document.getElementById('salaryYearLabel').innerHTML.toLowerCase()+" should have 4 digits");
   			document.getElementById('paraFrm_salaryYear').focus();
   			return false;
   		}
   		
   		if (payInComponentIDVar=="") {
   			alert("Please select "+document.getElementById('payInComponentLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_payInComponentName').focus();
			return false;
   		}	
	//}
//Validation if Pay in Salary is checked -- ENDS


//Validation if System Calculated Bonus/Allowance is checked -- BEGINS	
	var manuallyCalculatedBonusCheckBoxVar = document.getElementById('paraFrm_manuallyCalculatedBonusCheckBox').checked;
	var systemCalculatedBonusCheckBoxVar = document.getElementById('paraFrm_systemCalculatedBonusCheckBox').checked;
	var calCulatedBonusComponentsVar = trim(document.getElementById('paraFrm_calCulatedBonusComponents').value);
	var slabwiseMethodCheckboxVar = document.getElementById('paraFrm_slabwiseMethodCheckbox').checked;
	var individualRatingsMethodCheckboxVar = document.getElementById('paraFrm_individualRatingsMethodCheckbox').checked;
	var flatRateMethodCheckbox = document.getElementById('paraFrm_flatRateMethodCheckbox').checked;
	
	if (!systemCalculatedBonusCheckBoxVar && !manuallyCalculatedBonusCheckBoxVar) {
		alert("Please "+document.getElementById('calculationOptionLabel').innerHTML.toLowerCase());
		return false;
	} 
	 
	if (systemCalculatedBonusCheckBoxVar) {
		if (calCulatedBonusComponentsVar=="") {
			alert("Please "+document.getElementById('calculateBonusComponentLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_calCulatedBonusComponents').focus();
			return false;
		} 
		
		if (!slabwiseMethodCheckboxVar && !individualRatingsMethodCheckboxVar && !flatRateMethodCheckbox) {
			alert("Please "+document.getElementById('calculationMethodLabel').innerHTML.toLowerCase());
			return false;
		}
		
		if (slabwiseMethodCheckboxVar) {
			var table = document.getElementById('addSlabsRowTableID'); 
			var rowCount = table.rows.length; 
			var iteration = rowCount-1;
			if (eval(iteration)==0) {
				alert("Please define slabs.");
				return false;
			}
			
			 
			for (var i=0; i<iteration; i++) {
				var slabItrFromAmount = trim(document.getElementById('paraFrm_slabItrFromAmount'+i).value);
				var slabItrToAmount = trim(document.getElementById('paraFrm_slabItrToAmount'+i).value);
				var slabItrPercentage = trim(document.getElementById('paraFrm_slabItrPercentage'+i).value);
				if (slabItrFromAmount=="") {
					alert("Please enter "+document.getElementById('slabFromAmountLabel').innerHTML.toLowerCase());
					document.getElementById('paraFrm_slabItrFromAmount'+i).focus();
					return false;
				} else if (eval(slabItrFromAmount)<= 0) {
					alert(document.getElementById('slabFromAmountLabel').innerHTML.toLowerCase()+" must me greater than zero.");
					document.getElementById('paraFrm_slabItrFromAmount'+i).value = "";
					document.getElementById('paraFrm_slabItrFromAmount'+i).focus();
					return false;
				}
				
				if (slabItrToAmount=="") {
					alert("Please enter "+document.getElementById('slabToAmountLabel').innerHTML.toLowerCase());
					document.getElementById('paraFrm_slabItrToAmount'+i).focus();
					return false;
				} else if (eval(slabItrToAmount)<= 0) {
					alert(document.getElementById('slabToAmountLabel').innerHTML.toLowerCase()+" must me greater than zero.");
					document.getElementById('paraFrm_slabItrToAmount'+i).value = "";
					document.getElementById('paraFrm_slabItrToAmount'+i).focus();
					return false;
				}
				
				if (eval(slabItrToAmount) < eval(slabItrFromAmount)) {
					alert(document.getElementById('slabToAmountLabel').innerHTML.toLowerCase()+" must me greater than "+document.getElementById('slabFromAmountLabel').innerHTML.toLowerCase());
					return false;
				}
				
				if (slabItrPercentage=="") {
					alert("Please enter "+document.getElementById('slabPercentageLabel').innerHTML.toLowerCase());
					document.getElementById('paraFrm_slabItrPercentage'+i).focus();
					return false;
				} else if (eval(slabItrPercentage)<= 0) {
					alert(document.getElementById('slabPercentageLabel').innerHTML.toLowerCase()+" must me greater than zero.");
					document.getElementById('paraFrm_slabItrPercentage'+i).value = "";
					document.getElementById('paraFrm_slabItrPercentage'+i).focus();
					return false;
				}
				
				
			//BEGINS SLAB-VALIDATION	
				var slabItrPreviousFromAmount = '0.0';
				var slabItrPreviousToAmount = '0.0';
				if (i>0) {
					slabItrPreviousFromAmount = trim(document.getElementById('paraFrm_slabItrFromAmount'+(i-1)).value);	
					slabItrPreviousToAmount = trim(document.getElementById('paraFrm_slabItrToAmount'+(i-1)).value);
					if(eval(slabItrFromAmount) <= eval(slabItrPreviousToAmount))	{
						alert("Invalid Slab");
						document.getElementById('paraFrm_slabItrFromAmount'+i).focus();
						return false;
					}
				}
			//ENDS SLAB-VALIDATION	
			}
		}
		
		if (flatRateMethodCheckbox) {
			var flatRateBonusAllowanceCalculation = trim(document.getElementById('paraFrm_flatRateBonusAllowanceCalculation').value);
			if (flatRateBonusAllowanceCalculation=="") {
				alert("Please enter "+document.getElementById('flatRateBonusAllowanceCalculationLabel').innerHTML.toLowerCase());
				document.getElementById('paraFrm_flatRateBonusAllowanceCalculation').focus();
				return false;
			}
		}
		
	}
//Validation if System Calculated Bonus/Allowance is checked -- ENDS
	return true;
  } catch(e) {
	alert("Exception while validating mandetory fields >"+e);
	return false;
  }	
}


function lockFun() {
	var con = confirm("Do you really want to lock this record?");
	if (con) {
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="BonusAllowance_updateBonusAllowanceStatus.action?status=L";
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}

function unlockFun() {
	var con = confirm("Do you really want to unlock this record?");
	if (con) {
		doAuthorisation('8', 'Bonus', 'U');
		//document.getElementById('paraFrm').target="_self";
		//document.getElementById('paraFrm').action="BonusAllowance_updateBonusAllowanceStatus.action?status=U";
		//document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}
function doUnlock() {
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="BonusAllowance_updateBonusAllowanceStatus.action?status=U";
		document.getElementById('paraFrm').submit();
	}

function reportFun() {
	var fromMonthBonus = document.getElementById('paraFrm_fromMonth').value;
	var fromYearBonus = document.getElementById('paraFrm_fromYear').value;
	var toMonthBonus = document.getElementById('paraFrm_toMonth').value;
	var toYearBonus = document.getElementById('paraFrm_toYear').value;
	var bonusAllowanceId = document.getElementById('paraFrm_bonusAllowanceID').value;
	var bonusAllowanceStatus = document.getElementById('paraFrm_bonusAllowanceStatus').value;
	document.getElementById('paraFrm').target="_self";
	//document.getElementById('paraFrm').action="BonusAllowance_getProcessedDataReport.action";
	document.getElementById('paraFrm').action="BonusReport_fromBonusAllowance.action?fromMonthBonus="+fromMonthBonus+'&fromYearBonus='+fromYearBonus+'&toMonthBonus='+toMonthBonus+'&toYearBonus='+toYearBonus+'&bonusAllowanceId='+bonusAllowanceId+'&bonusAllowanceStatus='+bonusAllowanceStatus;
	document.getElementById('paraFrm').submit();
}

function validationForSelectPeriodAndDivision() {
	try {
		//Validation for Select Period Section -- BEGINS
		var fromMonthVar = document.getElementById('paraFrm_fromMonth').value; 
		var fromYearVar = trim(document.getElementById('paraFrm_fromYear').value);
		var toMonthVar = document.getElementById('paraFrm_toMonth').value;
		var toYearVar = trim(document.getElementById('paraFrm_toYear').value);
		var divisionIDVar = trim(document.getElementById('paraFrm_divisionID').value);
	
		if (fromMonthVar=="-1") {
			alert("Please select from month");
			document.getElementById('paraFrm_fromMonth').focus();
			return false;
		}
		if (fromYearVar=="") {
			alert("Please select from year");
			document.getElementById('paraFrm_fromYear').focus();
			return false;
		} else if(fromYearVar.length < 4) {
   			alert("from year should have 4 digits");
   			document.getElementById('paraFrm_fromYear').focus();
   			return false;
   		}
	
	
		if (toMonthVar=="-1") {
			alert("Please select to month");
			document.getElementById('paraFrm_toMonth').focus();
			return false;
		}
		if (toYearVar=="") {
			alert("Please select to year");
			document.getElementById('paraFrm_toYear').focus();
			return false;
		} else if(eval(toYearVar.length) < 4) {
   			alert("to year should have 4 digits");
   			document.getElementById('paraFrm_toYear').focus();
   			return false;
   		}
	
		if ((fromMonthVar!=""||fromYearVar!="")&&(toMonthVar!=""||toYearVar!="")) {
			if (eval(fromMonthVar)==eval(toMonthVar)) {
				if (eval(fromYearVar)>eval(toYearVar)) {
					alert("from year should not be greater than to year");
					document.getElementById('paraFrm_fromYear').focus();
					return false;
				}
			}
		
			if (eval(fromYearVar)==eval(toYearVar)) {
				if (eval(fromMonthVar)>eval(toMonthVar)) {
					alert("from month should not be greater than to month");
					document.getElementById('paraFrm_fromMonth').focus();
					return false;
				}
			}
		
			if (((eval(fromMonthVar)>eval(toMonthVar))&&(eval(fromYearVar)>eval(toYearVar)))
											||
				((eval(fromMonthVar)<eval(toMonthVar))&&(eval(fromYearVar)>eval(toYearVar)))							
				) {
				alert("from period should not be greater than to period");
				return false;
			}
		}
	
		if (divisionIDVar=="") {
			alert("please select division");
			document.getElementById('paraFrm_divisionName').focus();
			return false;
		}	
	//Validation for Select Period Section -- ENDS	
		return true;
	} catch(e) {
		alert("Exception in validationForSelectPeriodAndDivision >>"+e);
		return false;
	}
}

function downloadManuallyCalculatedBonusTemplate() {
	if (!validationForSelectPeriodAndDivision()) {
			return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='BonusAllowance_downloadManuallyCalculatedBonusTemplate.action';
		document.getElementById('paraFrm').submit();
	}	
}

function downloadTemplateForIndividualRatings() {
	if (!validationForSelectPeriodAndDivision()) {
			return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='BonusAllowance_downloadTemplateForIndividualRatings.action';
		document.getElementById('paraFrm').submit();
	}	
}

function validateFilteredEmployee() {
	if (!validationForSelectPeriodAndDivision()) {
		return false;
	} else {
		callsF9(800,525,'BonusAllowance_f9employee.action'); 
	}
}


function setHiddenFromAndToMonth() {
	document.getElementById('paraFrm_processedFromMonth').value = document.getElementById('paraFrm_fromMonth').value;
	document.getElementById('paraFrm_processedToMonth').value = document.getElementById('paraFrm_toMonth').value;
}
	
function bankstatementFun(){
	var fromMonthBonus = document.getElementById('paraFrm_fromMonth').value;
	fromMonthBonus = getMonthName(fromMonthBonus);
	var fromYearBonus = document.getElementById('paraFrm_fromYear').value;
	var toMonthBonus = document.getElementById('paraFrm_toMonth').value;
	toMonthBonus = getMonthName(toMonthBonus);
	var toYearBonus = document.getElementById('paraFrm_toYear').value;
	var bonusAllowanceId = document.getElementById('paraFrm_bonusAllowanceID').value;
	var divisionID = document.getElementById('paraFrm_divisionID').value;
	var divisionName = document.getElementById('paraFrm_divisionName').value;
	var bonusAllowanceStatus = document.getElementById('paraFrm_bonusAllowanceStatus').value;
	var hiddenMonth = document.getElementById('paraFrm_salaryMonth').value;
	
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="SalaryStatementBank_fromBonusAllowance.action?fromMonthBonus="+fromMonthBonus+'&fromYearBonus='+fromYearBonus+'&toMonthBonus='+toMonthBonus+'&toYearBonus='+toYearBonus+'&bonusAllowanceId='+bonusAllowanceId+'&divisionID='+divisionID+'&divisionName='+divisionName+'&bonusAllowanceStatus='+bonusAllowanceStatus+'&hiddenMonth='+hiddenMonth;
	document.getElementById('paraFrm').submit();
}
	
function getMonthName(enteredMonth) {
	if (enteredMonth=="1") {
		return "January";
	}
	if (enteredMonth=="2") {
		return "February";
	}
	if (enteredMonth=="3") {
		return "March";
	}
	if (enteredMonth=="4") {
		return "April";
	}
	if (enteredMonth=="5") {
		return "May";
	}
	if (enteredMonth=="6") {
		return "June";
	}
	if (enteredMonth=="7") {
		return "Jully";
	}
	if (enteredMonth=="8") {
		return "August";
	}
	if (enteredMonth=="9") {
		return "September";
	}
	if (enteredMonth=="10") {
		return "October";
	}
	if (enteredMonth=="11") {
		return "November";
	}
	if (enteredMonth=="12") {
		return "December";
	}
}	
// Add row Witness Details Begins
function addRowForSlabs(tableID) {
			var table = document.getElementById(tableID); 
			var rowCount = table.rows.length; 
			var iteration = rowCount-1;
			var row = table.insertRow(rowCount);
						
			var cell1 = row.insertCell(0); 
			var column1 = document.createElement("input");
			cell1.className='sortableTD'; 
            column1.type = "text"; 
            column1.name = 'slabItrFromAmount';
            column1.id = 'paraFrm_slabItrFromAmount'+iteration;
            column1.onkeypress=function(){
		   		return numbersWithDot();
		  	};
			column1.size='18';
		  	column1.maxLength='20';
		  	cell1.align='center';
		  	cell1.appendChild(column1); 
			
			var column7 = document.createElement("hidden"); 
            column7.type = "hidden"; 
            column7.name = 'slabItrID';
            column7.id = 'paraFrm_slabItrID'+iteration;
            cell1.appendChild(column7); 
			
            var cell2 = row.insertCell(1); 
            var column2 = document.createElement("input"); 
            cell2.className='sortableTD';
            column2.type = "text"; 
            column2.name = 'slabItrToAmount';
            column2.id = 'paraFrm_slabItrToAmount'+iteration;
			column2.size='18';
			column2.maxLength='20';
			column2.onkeypress=function(){
		   		return numbersWithDot();
		  	};
		  	cell2.appendChild(column2);
		  	cell2.align = 'center'; 
             
            var cell3 = row.insertCell(2); 
			var column3 = document.createElement("input"); 
            cell3.className='sortableTD';
            column3.type = "text"; 
            column3.name = 'slabItrPercentage';
            column3.id = 'paraFrm_slabItrPercentage'+iteration;
			column3.size='10';
		  	column3.maxLength='20';
		  	column3.onkeypress=function(){
		   		return numbersWithDot();
		  	};
		  	cell3.align='center';
		  	cell3.appendChild(column3); 
		  	
            var cell4 = row.insertCell(3); 
			var column4 = document.createElement("input"); 
            cell4.className='sortableTD';
            column4.type = "text"; 
            column4.name = 'slabItrMin';
            column4.id = 'paraFrm_slabItrMin'+iteration;
			column4.size='15';
		  	column4.maxLength='50';
		  	column4.onkeypress=function(){
		   		return numbersWithDot();
		  	};
		  	cell4.align='center';
		  	cell4.appendChild(column4); 
		  	
		  	var cell5 = row.insertCell(4); 
			var column5 = document.createElement("input"); 
            cell5.className='sortableTD';
            column5.type = "text"; 
            column5.name = 'slabItrMax';
            column5.id = 'paraFrm_slabItrMax'+iteration;
			column5.size='15';
		  	column5.maxLength='50';
		  	column5.onkeypress=function(){
		   		return numbersWithDot();
		  	};
		  	cell5.align='center';
		  	cell5.appendChild(column5); 
		  	
		  	var cell6 = row.insertCell(5);
		  	var column6 = document.createElement('img');		  	
		  	cell6.className='sortableTD';
		  	column6.type='image';
		  	column6.src="../pages/common/css/icons/delete.gif";		  	
		  	column6.id='img'+ iteration;
		  	column6.onclick=function(){
		   		deleteCurrentRow(this);
		   	};		  	
		   cell6.appendChild(column6);
		   cell6.align='center';
} 
// Add row Witness Details Ends

function deleteCurrentRow(obj){ 
	var delRow = obj.parentNode.parentNode;
	var tbl = delRow.parentNode.parentNode;
	var rIndex = delRow.sectionRowIndex;
	var rowArray = new Array(delRow);
	var con = confirm("Do you really want to delete row?");
	if(con) {	
		deleteRows(rowArray);
	} else {
		return false;
	}
}
		
function deleteRows(rowObjArray){	
	for (var i=0; i<rowObjArray.length; i++) {			
		var rIndex = rowObjArray[i].sectionRowIndex;
		rowObjArray[i].parentNode.deleteRow(rIndex);
	}
}

function taxchallanFun(){
	var bonusCode = document.getElementById('paraFrm_bonusAllowanceID').value;
	var type = 'B';
	var backAction = "<%=request.getContextPath()%>/payroll/BonusAllowance_callForEdit.action?bonusCode="+bonusCode;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/incometax/TaxChallan_input.action?applicationCode='+bonusCode+'&applicationType='+type+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
}
 
</script>