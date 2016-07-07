<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%
	String dataLocal = "" + request.getAttribute("dataLocal");

	if (dataLocal != null && !dataLocal.equals("null")) {
	} else {
		dataLocal = "none";
	}
	String status="";
	System.out.println(" ====== dataLocal===" + dataLocal);
%>
<script type="text/javascript">
	var records = new Array();
</script>

<s:form action="EmployeeInvestment" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="dataPath" />
	<s:hidden name="hiddenfrmId" id="hiddenfrmId" />
	<s:hidden name="cutOffDateFlag"/>
	<s:hidden name="periodFlag"/>
	
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Investment Declaration</strong></td><s:hidden name="generalFlag"></s:hidden>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><!--      <s:if test="%{viewFlag}">
                    <input type="button" class="search"	value="    Search11"
						onclick="javascript:callsF9(500,325,'EmployeeInvestment_f9action.action');" />
					</s:if>	
					
					<s:submit cssClass="reset" action="EmployeeInvestment_reset" 
					theme="simple" value="    Reset"   />
					<input name="Submit3" type="button" class="reset" value="    Reset11" onclick="return callReset();"/>
				
					<s:if test="%{viewFlag}">
					<s:submit cssClass="token" 
									theme="simple" value="   Report   " onclick="return callReport()" />
									</s:if>
									 --></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>

				</tr>
				
				<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>
				
				<!--<tr align="left">
					<td colspan="4"><s:if test="%{empInvestment.viewFlag}">
						<s:submit cssClass="token" action="EmployeeInvestment_view"
							theme="simple" value="View" onclick="return callView()" />

					</s:if> 
					<input type="button" class="token" theme="simple"
						value="View Calculator" onclick="callCalculator()" />
					<s:if test="%{viewFlag}">
						<s:submit cssClass="token" theme="simple" value="   Report   "
							onclick="return callReport()" />
					</s:if>  <s:submit
						cssClass="reset" action="EmployeeInvestment_resetEmpSection"
						theme="simple" value="   Reset   " /></td>
				</tr>

			--><tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" id="reportBodyTable" class="formbg">
			
				<tr>
					<td colspan="4">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td colspan="4" width="100%" class="formhead"><b>Financial
							Year:</b></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="taxFinYrFrm"
								name="taxFinYrFrm" ondblclick="callShowDiv(this);"><%=label.get("taxFinYrFrm")%></label><font
								color="red">*</font>:</td>
							<td width="25%"><s:textfield size="25"
								name="empInvestment.fromYear" maxlength="4"
								onkeypress="return numbersOnly();" onblur="add()" /></td>

							<td width="25%"><label class="set" id="taxFinYrTo"
								name="taxFinYrTo" ondblclick="callShowDiv(this);"><%=label.get("taxFinYrTo")%></label>
							<font color="red">*</font>:</td>
							<td width="25%"><s:textfield size="25"
								name="empInvestment.toYear" maxlength="4" readonly="true" /></td>
						</tr>
					</table>
					</td>
				</tr>



				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td colspan="4" width="100%" class="formhead"><b>Employee
									Information:</b></td>
								</tr>

								<tr>
									<td width="25%"><label class="set" id="employee"
										name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									<font color="red">*</font>:</td>
									<td width="25%" colspan="3"><s:hidden
										name="empInvestment.invCode" /> <s:hidden
										name="empInvestment.empID" /> <s:textfield
										name="empInvestment.empTokenNo" size="20" readonly="true" />
									<s:textfield name="empInvestment.empName" size="65"
										readonly="true" /> <s:if test="%{empInvestment.generalFlag}">
									</s:if> <s:else>
										<img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'EmployeeInvestment_f9empaction.action');">
									</s:else></td>
								</tr>
								<tr>
									<td width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td width="25%"><s:textfield size="25"
										name="empInvestment.center" readonly="true" /><s:hidden
										name="empInvestment.centerId" /> <s:hidden
										name="empInvestment.gender" /> <s:hidden
										name="empInvestment.joinedMonth" /> <s:hidden 
										name="empInvestment.age" /></td>

									<td width="25%"><label class="set" id="department"
										name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td width="25%"><s:textfield size="25"
										name="empInvestment.department" readonly="true" /></td>


								</tr>

								<tr>
									<td width="25%"><label class="set" id="designation"
										name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td width="25%"><s:textfield size="25"
										name="empInvestment.designation" readonly="true" /></td>

									<td width="25%"><label class="set" id="emp.grade"
										name="emp.grade" ondblclick="callShowDiv(this);"><%=label.get("emp.grade")%></label>
									:</td>
									<td width="25%"><s:textfield size="25"
										name="empInvestment.grade" readonly="true" /></td>




								</tr>
								<tr>
									<td><label class="set" id="joining.date"
										name="joining.date" ondblclick="callShowDiv(this);"><%=label.get("joining.date")%></label>:
									</td>
									<td><s:textfield size="25"
										name="empInvestment.joiningDate" readonly="true" /></td>

									<td><label class="set" id="panNum" name="panNum"
										ondblclick="callShowDiv(this);"><%=label.get("panNum")%></label>:
									</td>
									<td><s:textfield size="25" name="empInvestment.panNum"
										readonly="true" /></td>
								</tr>

								<tr>
									<td colspan="3" align="center">
									<input type="button" class="token" theme="simple"
						value="View Calculator" onclick="callCalculator()" />
									<img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>



							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td colspan="4" width="100%" class="formhead"><b>Investment
							Declaration:</b></td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="investment.section"
								name="investment.section" ondblclick="callShowDiv(this);"><%=label.get("investment.section")%></label><font
								color="red">*</font>:</td>
							<td width="25%"><s:select name="investmentType"
								cssStyle="width:320" headerKey="" headerValue="-- Select --"
								list="#{'E':'Exemption under 10-17','D':'Deductions under chapter VI-A','S':'Deductions under chapter VI(Sec 80C)','O':'Other Income','M':'Monthly Accommodation & Conveyance Declaration ','C':'Donation to approved funds & Charities'}"
								onchange="" />
								
								</td>

							<td width="25%"><input type="button" class="token"
								theme="simple" value="View " onclick="callForTable()" /></td>
							<td >&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="invDecTable">
					<s:if test="invDecFlag">

						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="2">

									<tr>
										<td height="27" class="formtxt" colspan="3"><strong>
										<%
											status = (String) request
															.getParameter("investmentType");

													if (status == null) {
														//out.println("HR Group");
													} else if (status.equals("E")) {
														out.println("Exemption under 10-17");
													} else if (status.equals("D")) {
														out.println("Deductions under chapter VI-A");
													} else if (status.equals("S")) {
														out.println("Deductions under chapter VI(Sec 80C)");
													} else if (status.equals("O")) {
														out.println("Other Income");
													}else if (status.equals("C")) {
														out.println("Donation to approved funds & Charities");
													}
													
												
										%> </strong></td>
									</tr>
									<tr> 
										<td  colspan="8">
										<table class="formbg" width="100%" border="0" id="tblSlabForMen">
											<tr>

												<td width="5%" colspan="1" class="formth"><label
													class="set" id="sr.no" name="sr.no"
													ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.name" name="inv.name"
													ondblclick="callShowDiv(this);"><%=label.get("inv.name")%></label></td>
												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.limit" name="inv.limit"
													ondblclick="callShowDiv(this);"><%=label.get("inv.limit")%></label></td>

												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.amt" name="inv.amt"
													ondblclick="callShowDiv(this);"><%=label.get("inv.amt")%></label></td>

												<td width="10%" colspan="1" class="formth"><label
													class="set" id="valid.amt" name="valid.amt"
													ondblclick="callShowDiv(this);"><%=label.get("valid.amt")%></label></td>

												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.proof" name="inv.proof"
													ondblclick="callShowDiv(this);"><%=label.get("inv.proof")%></label></td>

											</tr>
											<s:if test="invDecListLength">
											<%
													int count1 = 1;
		
													int filenCount = 0;
												
													int i = 0;
												
													int k = 0;									
											%>
												<s:iterator value="invDecList">


													<tr>

														<td class="sortableTD" align="center" width="5%"><%=count1++%>
														</td>
														<td width="20%" class="sortableTD" align="left" nowrap="nowrap"><s:hidden
															name="invDecListId" />
															
															<s:property value="investmentName"/>
															</td>
														<td width="10%" class="sortableTD" align="center"><s:textfield
															name="investmentlimit" readonly="true"
															id="<%="investmentlimit"+i%>"
															cssStyle="background-color: #F2F2F2;text-align:right" size="10"/></td>
														<td width="10%" class="sortableTD" ><s:textfield
															name="investmentAmt" id="<%="investmentAmt"+i%>"
															cssStyle="text-align:right"
															onblur="showValidAmt();" onkeyup="showValidAmt();" onkeypress="return checkNumbersWithDot(this);"
															maxlength="10" size="10"/></td>
														<td width="10%" class="sortableTD" align="center"><s:textfield
															name="validAmt" id="<%="validAmt"+i%>" readonly="true"
															cssStyle="background-color: #F2F2F2;text-align:right" size="10"/></td>
														<!--
												
											<td width="30%"><s:textfield name="investmentProof" 
													size="25" readonly="true" /> <input name="Browse"
													type="button" class="token" value="Upload"
													onclick="uploadFile('investmentProof');" /></td>	
												
											
											-->
											<!-- Multiple Upload Start-->
														<td width="30%" class="sortableTD">
														<!--<table>
															<tr>
																<td><input type="text" name="uploadLocFileName"
																	size="20" readonly="true"
																	id="paraFrm_uploadLocFileName<%=i%>"
																	value='<s:property value="uploadLocFileName" />'
																	size="20" /></td>
																<td><input type="button" name="uploadLoc"
																	value="Select Doc." class="token"
																	onclick="uploadFile('paraFrm_uploadLocFileName<%=i%>');" /></td>
																<td><input type="button" name="show" value="Show"
																	class="token"
																	onclick="showUploadRecord('paraFrm_uploadLocFileName<%=i%>');" /></td>
															</tr>
														</table>
														-->
														
														<div >
									<table class="" 
										id="<%="uploadInvTable"+k%>" >
										<tr>

										<td colspan="3" width="100%" align="right"><input type="button" class="add" align="right"
										value="Add Proof" onclick="addRowInvestUploadBlock('<%=k%>','<%=filenCount%>');" />
									</td>
									<% k++; %>
										</tr>
										<%!int d = 0; 
										int f=0;%>
									
										<s:iterator value="proofInvestList">
									<tr>					
							<td class="sortableTD" align="center">
							
							
							
							<input type="hidden" name="hFile<%=filenCount%>"	id="hFile"	
													value='<s:property value="hFile"/>' size="20" />
							
							
							
							<input
													type="text" name="uploadLocFileName"
													id="paraFrm_uploadLocFileName<%=k+""+f%>"
													value='<s:property value="uploadLocFileName"/>' size="20" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadLocFileName<%=k+""+f%>');" />
												</td>


												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadLocFileName<%=k+""+f%>');" /></td>

												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>

													
							</tr>
							<% f++;%>
							
							
							</s:iterator>

									</table></div>
														
														</td>

											<!-- Multiple Upload End-->
										
								
							

													</tr>
													<%
														i++;
													filenCount++;
														
													%>
												</s:iterator>

											</s:if>

											<s:else>
												<td align="center" colspan="6" nowrap="nowrap"><font
													color="red">There is no data to display</font></td>
											</s:else>
											
													<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						<td>&nbsp;</td>
							<td><strong>Total:</strong></td>
							

							<td><s:textfield name="totInvAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" /></td>

							<td>&nbsp;</td>
							
						</tr>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<s:if test="insertFlag">
								<s:if test="updateFlag">
							<tr align="left">
								<td colspan="4">
								
								<input type="button" class="token"
									theme="simple" value="Save Investment"
									onclick="return saveInvestment()" />
								</td>
							</tr></s:if></s:if>


						</table>
						</td>

					</s:if>
					<s:if test="monthAccomodationFlag">

						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="2">

									<tr>
										<td height="27" class="formtxt" colspan="3"><strong>
										<%
											status = (String) request
															.getParameter("investmentType");

													if (status == null) {
														//out.println("HR Group");
													} else if (status.equals("E")) {
														out.println("Exemption under 10-17");
													} else if (status.equals("D")) {
														out.println("Deductions under chapter VI-A");
													} else if (status.equals("S")) {
														out.println("Deductions under chapter VI(Sec 80C)");
													} else if (status.equals("O")) {
														out.println("Other Income");
													} else if (status.equals("M")) {
														out
																.println("Monthly Accommodation & Conveyance Declaration ");
													}
										%> </strong></td>
									</tr>
									<tr><s:hidden name="ittRentCode" />
										<td width="100%" colspan="4">
										<table class="formbg" width="100%" id="monthAccomodationTable">
											<tr>

												<td width="10%" colspan="1" class="formth"><label
													class="set" id="month" name="month"
													ondblclick="callShowDiv(this);"><%=label.get("month")%></label></td>
												
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="in.india" name="in.india"
													ondblclick="callShowDiv(this);"><%=label.get("in.india")%></label></td>
												
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="is.metro" name="is.metro"
													ondblclick="callShowDiv(this);"><%=label.get("is.metro")%></label>
												<td width="12%" colspan="1" class="formth"><label
													class="set" id="house.rent.paid" name="house.rent.paid"
													ondblclick="callShowDiv(this);"><%=label.get("house.rent.paid")%></label></td>
												
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="company.owned.house"
													name="company.owned.house" ondblclick="callShowDiv(this);"><%=label.get("company.owned.house")%></label></td>
												
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="city.population"
													name="city.population" ondblclick="callShowDiv(this);"><%=label.get("city.population")%></label></td>
												
												
													
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="rent.paid" name="rent.paid"
													ondblclick="callShowDiv(this);"><%=label.get("rent.paid")%></label></td>
												
												<!--<input type="checkbox" id="selAll" style="cursor: hand;"
													title="Select all records "
													onclick="selectAllRecords(this);" /></td>

												-->
												
												<td width="9%" colspan="1" class="formth"><label
													class="set" id="car.used" name="car.used"
													ondblclick="callShowDiv(this);"><%=label.get("car.used")%></label></td>

												<td width="10%" colspan="1" class="formth"><label
													class="set" id="cubic.capacity" name="cubic.capacity"
													ondblclick="callShowDiv(this);"><%=label.get("cubic.capacity")%></label></td>

												<td width="9%" colspan="1" class="formth"><label
													class="set" id="driver.used" name="driver.used"
													ondblclick="callShowDiv(this);"><%=label.get("driver.used")%></label></td>

												

											</tr>
											<tr> 
											 <td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">&nbsp;
											</td>
											<td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												
												<input type="checkbox" id="inIndia" style="cursor: hand;" title="Select all" 
												onclick="checkAllRecords(this, 'inIndiaflag', 'otherLengthVar');" />
												
											</td>
									
											<td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="isMetro" style="cursor: hand;" title="Select all" 
												onclick="checkAllRecords(this, 'isMetroflag', 'otherLengthVar');" />
											</td>
											
											<td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="houseRentpaidbyCompany" style="cursor: hand;" title="Select all" 
												onclick="unCompOwnedHouse();checkAllRecords(this, 'houseRentpaidbyCompanyflag', 'otherLengthVar');" />
											</td>
											
											<td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="compOwnedHouse" style="cursor: hand;" title="Select all" 
												onclick="unCheckCityPopulation();checkAllRecords(this, 'compOwnedHouseflag', 'otherLengthVar');" />
											</td>
											
											<td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="isCityPopulation" style="cursor: hand;" title="Select all" 
												onclick="checkAllRecords(this, 'isCityPopulationflag', 'otherLengthVar');" />
											</td>
											 
											 <td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">&nbsp;
											</td>
											<td width="3%" align="right" class="formth" id="ctrlShow" 
												nowrap="nowrap">
												<input type="checkbox" id="carUsed" style="cursor: hand;" title="Select all" 
												onclick="unCheckCarUsed();checkAllRecords(this, 'carUsedflag', 'otherLengthVar');" />
											</td>
											 
											 <td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="cubicCapacity" style="cursor: hand;" title="Select all" 
												onclick="checkAllRecords(this, 'cubicCapacityflag', 'otherLengthVar');" />
											</td>
											
											 <td width="3%" align="right" class="formth" id="ctrlShow"
												nowrap="nowrap">
												<input type="checkbox" id="driverUsed" style="cursor: hand;" title="Select all" 
												onclick="checkAllRecords(this, 'driverUsedflag', 'otherLengthVar');" />
											</td>
											
											 
											</tr>  
											<%
												int j = 1;
											%>
 
											<s:iterator value="monthList" status="stat">
												<tr>
													<!--<td class="sortableTD"><%=j%></td>
											-->
						 
													<td class="sortableTD"><s:hidden name="ittMonthCode"/>
													<s:hidden name="rentApplFlag" id="<%="rentApplFlag"+j%>"/>
													<s:hidden name="monthNo" id="monthNo" 
															cssStyle="background-color: #F2F2F2;"/>
													
													<s:property value="month" /></td>
													
													<td align="center" class="sortableTD">
														<s:if test='%{ittRentCode ==""}'>
												<input type="checkbox" class="checkbox" checked="checked" name="inIndiaflag" 
														id="<%="inIndiaflag"+j%>" 
														onclick="callChecked('inIndiaflag<%=j%>', 'inIndiaflag<%=j%>');"/>
											</s:if>	
											<s:else> 
												<input type="checkbox" class="checkbox" name="inIndiaflag" 
														id="<%="inIndiaflag"+j%>" 
														onclick="callChecked('inIndiaflag<%=j%>', 'inIndiaflag<%=j%>');"/>
													
														 
											</s:else>    
														<input type="hidden" class="text" name="inIndiaCheckedHiddenflag" 
														id="<%="inIndiaCheckedHiddenflag"+j%>" value='<s:property value="inIndiaCheckedHiddenflag" />'/>
														</td>
													
												<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="isMetroflag" 
														id="<%="isMetroflag"+j%>"
														onclick="callChecked('isMetroflag<%=j%>', 'isMetroflag<%=j%>');"/>
														
														 <input type="hidden" class="text" name="isMetroCheckedHiddenflag" value='<s:property value="isMetroCheckedHiddenflag" />'
														id="<%="isMetroCheckedHiddenflag"+j%>" />
														
														</td>
												<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="houseRentpaidbyCompanyflag" 
														id="<%="houseRentpaidbyCompanyflag"+j%>"
														onclick="callChecked('houseRentpaidbyCompanyflag<%=j%>', 'houseRentpaidbyCompanyflag<%=j%>');"/>
														
														
														<input type="hidden" class="text" name="houseRentpaidbyCompanyCheckedHiddenflag" value='<s:property value="houseRentpaidbyCompanyCheckedHiddenflag" />'
														id="<%="houseRentpaidbyCompanyCheckedHiddenflag"+j%>" />
															
														</td>
													
													
													<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="compOwnedHouseflag" 
														id="<%="compOwnedHouseflag"+j%>"
														onclick="callChecked('compOwnedHouseflag<%=j%>', 'compOwnedHouseflag<%=j%>');"/>
														
														
														<input type="hidden" class="text" name="compOwnedHouseCheckedHiddenflag" value='<s:property value="compOwnedHouseCheckedHiddenflag" />'
														id="<%="compOwnedHouseCheckedHiddenflag"+j%>" />
														
														</td>
													<td align="center" class="sortableTD">  <input type="checkbox" class="checkbox" name="isCityPopulationflag" 
														id="<%="isCityPopulationflag"+j%>"
														onclick="callChecked('isCityPopulationflag<%=j%>', 'isCityPopulationflag<%=j%>');"/>
														
													<input type="hidden" class="text" name="isCityPopulationCheckedHiddenflag"  value='<s:property value="isCityPopulationCheckedHiddenflag" />'
														id="<%="isCityPopulationCheckedHiddenflag"+j%>" />
															
													</td>
														 
														
													<td class="sortableTD">
													
													<input type="text" class="text" name="rentAmt"  value='<s:property value="rentAmt" />'
														id="<%="rentAmt"+j%>" onkeypress="return checkNumbersWithDot(this);" maxlength="10"
														Style="text-align:right" />  
													
													
													</td>
							

													<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="carUsedflag" 
														id="<%="carUsedflag"+j%>"
														onclick="callChecked('carUsedflag<%=j%>', 'carUsedflag<%=j%>');"/>
														
														<input type="hidden" class="text" name="carUsedCheckedHiddenflag" value='<s:property value="carUsedCheckedHiddenflag" />'
														id="<%="carUsedCheckedHiddenflag"+j%>" />
																
														</td>

													<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="cubicCapacityflag" 
														id="<%="cubicCapacityflag"+j%>"
														onclick="callChecked('cubicCapacityflag<%=j%>', 'cubicCapacityflag<%=j%>');"/>
														
														<input type="hidden" class="text" name="cubicCapacityCheckedHiddenflag"   value='<s:property value="cubicCapacityCheckedHiddenflag" />'
														id="<%="cubicCapacityCheckedHiddenflag"+j%>" />
															
														</td>
 
													<td align="center" class="sortableTD">
														
														<input type="checkbox" class="checkbox" name="driverUsedflag" 
														id="<%="driverUsedflag"+j%>"
														onclick="callChecked('driverUsedflag<%=j%>', 'driverUsedflag<%=j%>');"/>
														
														<input type="hidden" class="text" name="driverUsedCheckedHiddenflag" value='<s:property value="driverUsedCheckedHiddenflag" />'
														id="<%="driverUsedCheckedHiddenflag"+j%>" />
															 
														</td>
															
														
												</tr>
												<%
													j++;
												%>
											</s:iterator>
										<input type="hidden" name="otherLengthVar" id="otherLengthVar" value="<%=j%>"/>
										</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">
								<table width="100%" border="0" cellpadding="0" cellspacing="2"
									class="formbg">
									<tr>
							<td colspan="4" width="100%" class="formhead"><b>Upload Proofs:</b></td>
						</tr>
						
						<%
										int g = 0;		int filenCount1 = 0;							
									%>
									
									<tr>
										<!--<td><label class="set" name="upload.document"
											id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label>:</td>
										<td><s:textfield name="uploadLocFileName" readonly="true"
											size="40" />
										<input name="Upload" type="button"
											class="token" value="Browse"
											onclick="uploadFile('uploadLocFileName');" />&nbsp; <input
											type="button" name="Add Proof" value="Upload" class="token"
											onclick="return callUpload();" /></td>
											
											--><td align="right"><input type="button" class="add"
										value="Add Row" onclick="addRowUploadBlock('<%=g%>','<%=filenCount1%>');" />
									</td>
									
									
									</tr>
									<tr>
									<td width="100%" colspan="4">
									<div >
									
							
							
									<table class="formbg" width="100%"
										id="uploadTable<%=g %>" >
										<tr>

											<td width="30%" colspan="4" class="formth"><label
												class="set" id="upload.document" name="upload.document"
												ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label></td>
<td  width="30%"></td><td  width="30%"></td>
										</tr>
										<%
							int cnt = 0;
								int cnt1 = 0;
								int h=0;
						%>
										<s:iterator value="proofList">
									<tr>					
							<td class="sortableTD" align="center"><input
													type="text" name="uploadRentFileName"
													id="paraFrm_uploadRentFileName<%=h+""+g %>"
													value='<s:property value="uploadRentFileName"/>' size="50" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadRentFileName<%=h+""+g %>');" />
												</td>


												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadRentFileName<%=h+""+g %>');" /></td>

												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>

													
							</tr>
							<%
								cnt1 = cnt;
							%>
							
							<%
							++g;
							h++;
							%>
							</s:iterator>

									</table></div>
									</td>
								</tr>
								</table>
								</td>
							</tr>
							<s:if test="insertFlag">
								<s:if test="updateFlag">
							<tr align="left">
							
								<td colspan="4"><input type="button" class="token"
									theme="simple" value="Save Investment"
									onclick="return saveRentInvestment()" /></td>
									
							</tr>
						</s:if></s:if>

						</table>
						</td>

					</s:if>
					<!-- Donation Master Flag Start -->
					<s:if test="donationFlag">

						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="2">

									<tr>
										<td height="27" class="formtxt" colspan="3"><strong>
										<%
											 status = (String) request
															.getParameter("investmentType");

													if (status == null) {
														//out.println("HR Group");
													} else if (status.equals("E")) {
														out.println("Exemption under 10-17");
													} else if (status.equals("D")) {
														out.println("Deductions under chapter VI-A");
													} else if (status.equals("S")) {
														out.println("Deductions under chapter VI(Sec 80C)");
													} else if (status.equals("O")) {
														out.println("Other Income");
													}else if (status.equals("C")) {
														out.println("Donation to approved funds & Charities");
													}
													
												
										%> </strong></td>
									</tr>
									<tr>
										<td  colspan="4">
										<table class="formbg" width="100%" border="0" id="tblSlabForDonation">
											<tr> 

												<td width="5%" colspan="1" class="formth"><label
													class="set" id="sr.no" name="sr.no"
													ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.name" name="inv.name"
													ondblclick="callShowDiv(this);"><%=label.get("donation.name")%></label></td>
												<td width="10%" colspan="1" class="formth"><label
													class="set" id="inv.limit" name="inv.limit"
													ondblclick="callShowDiv(this);"><%=label.get("exemption")%></label></td>

												<td width="10%" colspan="1" class="formth"><label
													class="set" id="inv.amt" name="inv.amt"
													ondblclick="callShowDiv(this);"><%=label.get("inv.amt")%></label></td>

												<td width="10%" colspan="1" class="formth"><label
													class="set" id="valid.amt" name="valid.amt"
													ondblclick="callShowDiv(this);"><%=label.get("valid.amt")%></label></td>

												<td width="20%" colspan="1" class="formth"><label
													class="set" id="inv.proof" name="inv.proof"
													ondblclick="callShowDiv(this);"><%=label.get("inv.proof")%></label></td>

											</tr>
											<s:if test="donationListLength">
												<%
													int count2 = 1;
												%>
												<%
													int filenCount2 = 0;
												%>
												<%
													int b = 0;
												%>
												<%
										int r = 0;									
									%>
												<s:iterator value="donationList">


													<tr>

														<td class="sortableTD" align="center" width="5%"><%=count2++%>
														</td>
														<td width="10%" class="sortableTD" align="left" nowrap="nowrap"><s:hidden
															name="donationListId" /> 
															<s:property value="donationName"/>
															</td>
														<td width="10%" class="sortableTD" ><s:textfield
															name="exemption" readonly="true"
															id="<%="exemption"+b%>" 
															cssStyle="background-color: #F2F2F2;text-align:right" size="10"/></td>
														<td width="10%" class="sortableTD" align="center"><s:textfield
															name="donationAmt" id="<%="donationAmt"+b%>"
															onkeypress="return checkNumbersWithDot(this);" cssStyle="text-align:right" 
															onblur="showValidDonationAmt();" onkeyup="showValidDonationAmt();"
															maxlength="10" size="10"/></td>
														<td width="10%" class="sortableTD" align="center"><s:textfield 
															name="donationValidAmt" id="<%="donationValidAmt"+b%>" readonly="true"
															cssStyle="background-color: #F2F2F2;text-align:right" size="10"/></td>
														<!--
												
											<td width="30%"><s:textfield name="investmentProof" 
													size="25" readonly="true" /> <input name="Browse"
													type="button" class="token" value="Upload"
													onclick="uploadFile('investmentProof');" /></td>	
												
											
											-->
											<!-- Multiple Upload Start-->
														<td width="30%" class="sortableTD">
													
														
														<div >
									<table class="" 
										id="<%="uploadDonationTable"+r%>" >
										<tr>

										<td colspan="3" width="100%" align="right"><input type="button" class="add" align="right"
										value="Add Row" onclick="addRowDonationUploadBlock('<%=r%>','<%=filenCount2%>');" />
									</td>

										</tr>
									
									<%int o=0; %>
										<s:iterator value="proofInvestList">
									<tr>					
							<td class="sortableTD" align="center">
							
							<input type="hidden" name="hFile<%=filenCount2%>"	id="hFile"	
													value='<s:property value="hFile"/>' size="20" />
							
											<input
													type="text" name="uploadLocFileName"
													id="paraFrm_uploadLocFileName<%=r%><%=o%>"
													value='<s:property value="uploadLocFileName"/>' size="20" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadLocFileName<%=r%><%=o%>');" />
												</td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadLocFileName<%=r%><%=o%>');" /></td>

												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>
												<%o++; %>	
							</tr>
							</s:iterator>

							<%
							++r;
							%>
									</table></div>
														</td>

											<!-- Multiple Upload End-->
													</tr>
													<%
														b++;
														filenCount2++;
														
													%>
												</s:iterator>
											</s:if>

											<s:else>
												<td align="center" colspan="6" nowrap="nowrap"><font
													color="red">There is no data to display</font></td>
											</s:else>
										
										<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						<td>&nbsp;</td>
							<td><strong>Total:</strong></td>
							

							<td><s:textfield name="totExpAmt" readonly="true"
								cssStyle="background-color: #F2F2F2;text-align:right" size="15"/></td>

							<td>&nbsp;</td>
							
						</tr>
										
										
										</table>
										</td>
									</tr>
									
								</table>
								</td>
							</tr>
				<s:if test="insertFlag">
								<s:if test="updateFlag">
							<tr align="left">
								<td colspan="4"><input type="button" class="token"
									theme="simple" value="Save Donation"
									onclick="return saveDonation()" /></td>
							</tr>
				</s:if></s:if>

						</table>
						</td>

					</s:if>
					<!-- Donation Master Flag End -->
				</tr>
				
				<s:hidden name="empInvestment.invId" />
				<s:hidden name="empInvestment.paraID" />
				<s:hidden name="empInvestment.isFromEdit" />
				<s:hidden name="empInvestment.chkEdit" />
				<s:hidden name="checkRemoveUpload" />
				</table>
				</td>
				</tr>
			
	<tr>
		<td >
	<div id='bottomButtonTable'>
	
	</div>
		</td>
	</tr>
	</table>
	<s:hidden name="report" /> 
	<s:hidden name="reportAction" value='EmployeeInvestment_report.action'/>
	<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>
</s:form>

<script>

function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
				callReportCommon(type);
			}
}


function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			
			document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='EmployeeInvestment_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
			
		}	
	}
	
	function validateFields(){ 
		try{
		var empId =document.getElementById('paraFrm_empInvestment_empName').value;
	  var from = document.getElementById('paraFrm_empInvestment_fromYear').value;
	  
	 var empName=document.getElementById('employee').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase();
 
	    if(empId=="")
	    {
	    	alert("Please Select "+empName+".");
	    	return false;
	    }
	    
	     if(from=="")
	    {
	    	alert("Please "+finYrFrm+".");
	    	return false;
	    }

		return true;
		 }catch (e)
	 {
	 	alert(e);
	 }
	}
	
	function callReportMis(){
      try{
	//document.getElementById('paraFrm_rptType').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target="main";
	    document.getElementById('paraFrm').action="EmployeeInvestment_report.action";
	 	document.getElementById('paraFrm').submit();
			}
		    
	 }catch (e)
	 {
	 	alert(e);
	 }
	 	
   }
	
	function resetFun(){
	if(document.getElementById('paraFrm_generalFlag').value=='true'){
		
	}else{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmployeeInvestment_resetEmpSection.action";
		
		}
		document.getElementById('paraFrm').submit();
	}
	


onLoad();
showValidDonationAmt();
showValidAmt();
function onLoad(){
	
	try{
	onloadCall(); 
	
		
	var table = document.getElementById('uploadTable'+idValue); 
	
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowUploadBlock('uploadTable'+idValue);		
		}
		
		
	 var table = document.getElementById('uploadInvTable'+idValue);
	
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowInvestUploadBlock('uploadInvTable'+idValue);		
		}
		
	var donationtable = document.getElementById('uploadDonationTable'+idValue);
	
		var rowCount = donationtable.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowDonationUploadBlock('uploadDonationTable'+idValue);		
		}
		
		
		document.getElementById('ID_L').className='on';
		} catch(e){
		
		///alert(e);
		}
}
    function callForEdit(id){
	   	document.getElementById("paraFrm").action="EmployeeInvestment_edit.action";
	  	document.getElementById('paraFrm_empInvestment_paraID').value=id;
	    document.getElementById("paraFrm").submit();
   }
  
   
   function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}
	
	 function callView(){
	 var empName=document.getElementById('employee').innerHTML.toLowerCase();
	  var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase();
 
	  var empId =document.getElementById('paraFrm_empInvestment_empName').value;
	  var from = document.getElementById('paraFrm_empInvestment_fromYear').value;	 
	   
	    if(empId=="")
	    {
	    	alert("Please Select "+empName+".");
	    	return false;
	    }
	     if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm+".");
	    	return false;
	    }
	   
   }
   
   function add()
   {
    var from = document.getElementById('paraFrm_empInvestment_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_empInvestment_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_empInvestment_toYear').value=x;
	  }
   }
     function add1()
   { 
    var limit = document.getElementById('paraFrm_empInvestment_invLimit').value;
    var amt = document.getElementById('paraFrm_empInvestment_invAmount').value;
    var valid = document.getElementById('paraFrm_empInvestment_invValid').value;
   
     if(limit=="" || limit==0 ){
     document.getElementById('paraFrm_empInvestment_invValid').value=document.getElementById('paraFrm_empInvestment_invAmount').value;
     }
     else if(eval(limit)  < eval(amt))
     		{
     		document.getElementById('paraFrm_empInvestment_invValid').value=limit;
    		}
    		else{
    		document.getElementById('paraFrm_empInvestment_invValid').value=document.getElementById('paraFrm_empInvestment_invAmount').value;
    		}
    	
    }
   
   
    function save(){
	   	
	  var invstName=document.getElementById('taxInvstName').innerHTML.toLowerCase();
	  var invstAmt=document.getElementById('taxInvstAmt').innerHTML.toLowerCase();
	  //var upload=document.getElementById('upload').innerHTML.toLowerCase();	
	 
	 
	  var invName =document.getElementById('paraFrm_empInvestment_invName').value;
	  var amt = document.getElementById('paraFrm_empInvestment_invAmount').value;
	  var valAmt = document.getElementById('paraFrm_empInvestment_invValid').value;
	  //var proofAttach=document.getElementById("paraFrm_isProofAttach").value;
	    
	    if(invName=="")
	    {
	    	alert("Please Select "+invstName+".");
	    	return false;
	    }
	    
	     
	      if(amt=="")
	    {
	    	alert("Please Enter "+invstAmt+".");
	    	return false;
	    }
	  
	    
	 //    if(valAmt=="")
	//    {
	//    	alert("Please Enter Valid Amount.");
	//    	return false;
	//    }
	   
	       }
	       
	       
	       function callDelete(id)
   {
    var flag=formValidate1();
   if(flag)
   {
    	document.getElementById("paraFrm").action="EmployeeInvestment_delete.action";
  	   	document.getElementById('paraFrm_empInvestment_paraID').value=id;
	  	document.getElementById("paraFrm").submit();
	   
   	}
   	}
   	
   	function formValidate1(){
  		 		
  		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			return true;
  			
  			}else
  			{
  			return false;
  			}
  	 
  		
  		}
  		function callReset(){
	    
		document.getElementById('paraFrm').action = "EmployeeInvestment_input.action";
		document.getElementById('paraFrm').submit();
	}
	showtext();
	function showtext(){
			
				var proofAttach=document.getElementById("paraFrm_isProofAttach").value;
				if(proofAttach=="Y"){
					document.getElementById('div1').style.display='block';
				}else if(proofAttach=="N"){
					document.getElementById('div1').style.display='none';
				}
		}
		
	function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	function showRecord(fileName){
		if(fileName==" "){
			alert("Investment proof document not uploaded.");
			return false;
		}
		else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "EmployeeInvestment_viewDoc.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}	
   
   function callDivLoad(id)
{
	if(id == 'HR'){
		document.getElementById('paraFrm').action = "EmployeeInvestment_viewHra.action";
		document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').submit();
	} 
	//document.getElementById('HR').style.display = 'none';
	//document.getElementById('EM').style.display = 'none';
	document.getElementById('ID_L').className='on';
	//document.getElementById('EM_L').className='li';
}

function callCalculator(){
try{
	     var empId = document.getElementById('paraFrm_empInvestment_empID').value;
	     if(empId == ""){
	     	alert("Please select employee");
	     	return false;
	     }
	    var gender = document.getElementById('paraFrm_empInvestment_gender').value;
	    var frmYear = document.getElementById('paraFrm_empInvestment_fromYear').value;
	    var toYear = document.getElementById('paraFrm_empInvestment_toYear').value;
	     var age = document.getElementById('paraFrm_empInvestment_age').value;
	    var token = document.getElementById('paraFrm_empInvestment_empTokenNo').value;
	     var path = '<%=request.getContextPath() %>';
	     var empName = document.getElementById('paraFrm_empInvestment_empName').value;
	     var center = document.getElementById('paraFrm_empInvestment_center').value;
	      var rank = document.getElementById('paraFrm_empInvestment_designation').value;
	      var centerId = document.getElementById('paraFrm_empInvestment_centerId').value;
	      var joinedMonth = document.getElementById('paraFrm_empInvestment_joinedMonth').value;
	     
	     	window.open(path+'/incometax/TdsCalculator_viewCalculator.action?gender='+gender+'&bbb='+empId+'&frYr='+frmYear+'&toYr='+toYear+'&age='+age+'&token='+token+'&name='+empName+'&center='+center+'&rank='+rank+'&centerId='+centerId+'&joinedMonth='+joinedMonth,'','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');
}catch(e){alert(e);}
}

	function callForTable()
 {  
 try { 
	 var empCode=document.getElementById('paraFrm_empInvestment_empID').value;
	if(empCode==""){
	alert("Please Select Employee First.");
	document.getElementById('paraFrm_empInvestment_empName').focus();
	document.getElementById('paraFrm_investmentType').value='';
	return false;
	
	}
	
	  var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase();
	  var from = document.getElementById('paraFrm_empInvestment_fromYear').value;	 
	     if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm+".");
	    	document.getElementById('paraFrm_empInvestment_fromYear').focus();
	    	return false;
	    }
	   
	   var invTye = document.getElementById('paraFrm_investmentType').value;
	if(invTye==""){
	alert("Please Select Investment Section.");
	document.getElementById('paraFrm_investmentType').focus();
	return false;
	
	}
  var hCode=document.getElementById('paraFrm_investmentType').value;
  //alert(hCode);
	document.getElementById('hiddenfrmId').value = hCode; 
	
	callShowRecord();
	}
catch(e){
//alert(e);
}	
 
}
		 function callShowRecord()
			{
			 document.getElementById('invDecTable').style.display='';
				 var invType = document.getElementById('paraFrm_investmentType').value;
				 document.getElementById('paraFrm').action='EmployeeInvestment_viewInvDecReocrd.action?invType='+invType;
				 document.getElementById('paraFrm').submit();
				 
			}  
			
	function callUpload()
{
	try
	{	
		var uploadFile = document.getElementById('paraFrm_uploadLocFileName').value;	
	
		if(uploadFile=="")
		{		
			alert("Please Select Document to upload.");
			return false;
		}	
	}
	catch(e)
	{
		//alert("Error Occured in callUpload===================> "+e);		
	}
	 var invType = document.getElementById('hiddenfrmId').value;
				// alert(invType);
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action='EmployeeInvestment_addMultipleProof.action?invType='+invType;
	document.getElementById("paraFrm").submit();
} 

	function showValidAmt() {
	 
	try
	{
	
	var table = document.getElementById('tblSlabForMen');
					var rowCount = table.rows.length; 
		///alert(rowCount);
		var sum = '0.0';
		for(var i=0;i<(rowCount-2);i++){
			var invlimit= parseFloat(document.getElementById('investmentlimit'+i).value);
			
			if(document.getElementById('investmentlimit'+i).value=="NaN" || document.getElementById('investmentlimit'+i).value==''){
			   document.getElementById('investmentlimit'+i).value="0.0";
				}
				
				
		////alert("invlimit=="+invlimit);
		
		
				
		var investmentAmt = document.getElementById('investmentAmt'+i).value; 
		
		if(document.getElementById('investmentAmt'+i).value=="NaN"){
			   document.getElementById('investmentAmt'+i).value="0";
				}
				
		///alert("investmentAmt=="+investmentAmt); 
		
		
				
		 if(investmentAmt > invlimit && parseInt(invlimit)!=0){
	
			document.getElementById('validAmt'+i).value = Math.ceil(parseFloat(invlimit)); 
			
			var validDonationAmount = Math.ceil(invlimit); 
			sum = parseInt(sum) + parseInt(validDonationAmount);	
			
			} else {
		
				document.getElementById('validAmt'+i).value = parseFloat(investmentAmt); 
				var validDonationAmount = Math.ceil(investmentAmt); 
				sum = parseInt(sum) + parseInt(validDonationAmount);
				
		
			}
			
			if(invlimit==0.0){
			document.getElementById('validAmt'+i).value = parseFloat(investmentAmt); 
			}
			
			if(document.getElementById('validAmt'+i).value=="NaN"){
			   document.getElementById('validAmt'+i).value="0.0";
				}
			
		} 
		
		
		
		document.getElementById('paraFrm_totInvAmt').value = sum;
		if(document.getElementById('paraFrm_totInvAmt').value=="NaN"){
			   document.getElementById('paraFrm_totInvAmt').value="0.0";
				}
		
				
						
		}catch(e)
		{ 
			////alert("Error Occured in callUpload===================> "+e);		
		}
	}
	
	function showValidDonationAmt() {
	try
	{
	var table = document.getElementById('tblSlabForDonation');
					var rowCount = table.rows.length; 
		///alert(rowCount);
		var sum = 0;
		for(var i=0;i<(rowCount-2);i++){
			var donationAmt = parseFloat(document.getElementById('donationAmt'+i).value);
			if(!donationAmt=='' || Math.abs(donationAmt)==0){
			var exemption = parseFloat(document.getElementById('exemption'+i).value);
			
			var validDonationAmount = Math.ceil(eval(donationAmt * exemption)/100);
			//alert("validDonationAmount=="+validDonationAmount);
		document.getElementById('donationValidAmt'+i).value = validDonationAmount;
		sum = parseInt(sum) + parseInt(validDonationAmount);
		
			}
		}
		document.getElementById('paraFrm_totExpAmt').value = sum;
		
		
		///alert(sum);
	}
	catch(e)
	{
		////alert("Error Occured in callUpload===================> "+e);		
	}
	}
	function showUploadRecord(fieldName)
	{
		var fileName =document.getElementById(fieldName).value;
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "EmployeeInvestment_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		return true ; 
	}
	
	function saveInvestment(){
	if(!invDeclFlagFun()){
					return false;
				} else {
		var con = confirm('Do you really want to save Investment Declaration.');
	 	if(con) {
		
  			document.getElementById('paraFrm').action = 'EmployeeInvestment_saveInvestment.action';
			document.getElementById('paraFrm').submit();
			}
		}				
	}
	
	
	function showproofname(fileName)
	{
	 	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "EmployeeInvestment_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} 
 function callForRemoveUpload(id)
{
   var conf=confirm("Are you sure !\n You want to Remove this record ?");
	if(conf)
	{
	 var invType = document.getElementById('hiddenfrmId').value;
				// alert(invType);
		document.getElementById('paraFrm_checkRemoveUpload').value=id;
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action='EmployeeInvestment_removeUploadFile.action?invType='+invType;
		document.getElementById("paraFrm").submit();
	}	
}


 	function saveRentInvestment(){	
 	if(!invDeclFlagFun()){
					return false;
				} else {
 	var con = confirm('Do you really want to save Rent Investment Declaration.');
	 	if(con) {
	 	
  			document.getElementById('paraFrm').action = 'EmployeeInvestment_saveRentInvestment.action';
			document.getElementById('paraFrm').submit();
			}
		}	  
	}
	
	// Function For Adding More Bargaining Agreement
	function addRowUploadBlock(idValue,fileCount)	{
	try{
	
		  var tbl = document.getElementById('uploadTable'+idValue);
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell5= row.insertCell(0);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'uploadRentFileName';
		  column5.id = 'paraFrm_uploadRentFileName'+idValue+''+iteration;
		  column5.size ='50';
		  column5.maxLength ='20';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(1);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type='button';
		  column6.align='center';
		  column6.name='uploadBtn';
		  column6.value='Upload';
		  column6.className='token';
		  column6.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadRentFileName'+idValue+''+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
		  var cell7= row.insertCell(2);
		  var column7 = document.createElement('input');
		  cell7.className='token';
		  column7.type='button';
		  column7.align='center';
		  column7.name='showBtn';
		  column7.value='Show';
		 column7.className='token';
		  column7.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadRentFileName'+idValue+''+iteration);
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);

		  var cell8= row.insertCell(3);
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
		} catch(e){
		alert(e);
		}  
		  
	}


	// Function For Adding More Investment Proof
	function addRowInvestUploadBlock(idValue,fileCount)	{
	//alert(idValue);
	try{
	 var tbl = document.getElementById('uploadInvTable'+idValue);
	//alert(tbl);
		}
		catch (e)
		{
		alert(e);
		}
		 
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell5= row.insertCell(0);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'uploadLocFileName';
		  column5.id = 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		  column5.size ='20';
		  //column5.maxLength ='20';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(1);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type='button';
		  column6.align='center';
		  column6.name='uploadBtn';
		  column6.value='Upload';
		  column6.className='token';
		  column6.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
		  var cell7= row.insertCell(2);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type='button';
		  column7.align='center';
		  column7.name='showBtn';
		  column7.value='Show';
		 column7.className='token';
		  column7.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadLocFileName'+idValue+''+iteration);
		
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);

		  var cell8= row.insertCell(3);
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
		  
		    var cell9= row.insertCell(4);
		  var column9 = document.createElement('input');
		  cell9.className='sortableTD';
		  column9.type = 'hidden';
  		  column9.name = 'hFile'+fileCount;
		  column9.id = 'hFile';
		  column9.size ='20';
		  //column5.maxLength ='20';
		  cell9.align='center';
		  cell9.appendChild(column9);
	}


function uploadTicketFile(fieldName) {

		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	function showRecord(fieldName)
	{
		var fileName =document.getElementById(fieldName).value;
		//alert(fileName);
		
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "EmployeeInvestment_viewAttachedProof.action?fileName="+fileName;
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
	
	// Function For Adding More Investment Proof
	function addRowDonationUploadBlock(idValue,fileCount)	{
	///alert(idValue);
	try{ 
	 var tbl = document.getElementById('uploadDonationTable'+idValue);
					
	//alert(tbl);
		
		}
		catch (e)
		{
		alert(e);
		}
	
		 
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell5= row.insertCell(0);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'uploadLocFileName';
		  column5.id = 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		  column5.size ='20';
		  //column5.maxLength ='20';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(1);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type='button';
		  column6.align='center';
		  column6.name='uploadBtn';
		  column6.value='Upload';
		  column6.className='token';
		  column6.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
		  var cell7= row.insertCell(2);
		  var column7 = document.createElement('input');
		  cell7.className='sortableTD';
		  column7.type='button';
		  column7.align='center';
		  column7.name='showBtn';
		  column7.value='Show';
		 column7.className='token';
		  column7.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadLocFileName'+idValue+''+iteration);
		
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);

		  var cell8= row.insertCell(3);
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
		  
		    var cell9= row.insertCell(4);
		  var column9 = document.createElement('input');
		  cell9.className='sortableTD';
		  column9.type = 'hidden';
  		  column9.name = 'hFile'+fileCount;
		  column9.id = 'hFile';
		  column9.size ='20';
		  //column5.maxLength ='20';
		  cell9.align='center';
		  cell9.appendChild(column9);
		  
	}
	function invDeclFlagFun(){
		var cutOffVar = document.getElementById('paraFrm_cutOffDateFlag').value
					var periodVar = document.getElementById('paraFrm_periodFlag').value
					if(periodVar=='LOCK'){
						alert("Taxation process is finalized for this financial year.");
						return false;
					}
					else if(periodVar=='INVALID_PERIOD'){
						alert("Investment declaration is accepted only within allowed period.");
											//Investment declaration is accepted within allowed date wise declaration period.");
						return false;
					}
					else if(cutOffVar=='false'){
						alert("	Investment Declaration Cut-Off Date is over for this financial year.");	
						return false;
					}
					
					
					return true;
	}
		function saveDonation() {
		if(!invDeclFlagFun()){
					return false;
				} else {	
			var con = confirm('Do you really want to save Investment Declaration.');
		 	if(con) {
		 		
			  			document.getElementById('paraFrm').action = 'EmployeeInvestment_saveDonation.action';
						document.getElementById('paraFrm').submit();
				}				
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

function numbersOnly(e){
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function numbersWithDot(e){
	document.onkeypress = numbersWithDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function checkAllRecords(object, checkBox, totalRecordsField) {
try{
	totalRecords = document.getElementById(totalRecordsField).value;
	if(object.checked) {
			for(var i = 1; i <= totalRecords; i++) {
				document.getElementById(checkBox + i).checked = true;
				
				//IN India
				if(document.getElementById('inIndiaflag'+i).checked == true){
					if(document.getElementById('inIndiaflag'+i).disabled == true){
						document.getElementById('inIndiaflag'+i).checked = false;
						document.getElementById('inIndiaCheckedHiddenflag'+i).value="N";
					}else{
						document.getElementById('inIndiaCheckedHiddenflag'+i).value="Y";
					}
				}
				//Is Metro	 
				if(document.getElementById('isMetroflag'+i).checked == true){
					if(document.getElementById('isMetroflag'+i).disabled == true){
						document.getElementById('isMetroflag'+i).checked = false;
						document.getElementById('isMetroCheckedHiddenflag'+i).value="N";
					}else{
						document.getElementById('isMetroCheckedHiddenflag'+i).value="Y";
					}
				}
				
				///House Rent paid by Company
				if(document.getElementById('houseRentpaidbyCompanyflag'+i).checked == true){
					if(document.getElementById('houseRentpaidbyCompanyflag'+i).disabled == true){
					document.getElementById('isCityPopulation').disabled = false;
						document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
						document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="N";
					}else{
					document.getElementById('isCityPopulation').disabled = false;
						document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="Y";
						document.getElementById('rentAmt'+i).disabled = true;  
						document.getElementById('rentAmt'+i).value = 0; 
						document.getElementById('compOwnedHouse').checked = false; 
						document.getElementById('compOwnedHouseflag'+i).disabled = true;
						document.getElementById('compOwnedHouseflag'+i).checked = false; 
						document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N"; 
					}
				}
				//Company owned house	 
				 if(document.getElementById('compOwnedHouseflag'+i).checked == true){ 
					  try{
						  if(document.getElementById('compOwnedHouseflag'+i).disabled == true){
						  		document.getElementById('compOwnedHouseflag'+i).checked = false; 
									document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
						  }
						if(document.getElementById('compOwnedHouseflag'+i).disabled == false && document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value=="N"){
								document.getElementById('compOwnedHouseflag'+i).checked = true; 
								document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="Y";
						}
						if(document.getElementById('compOwnedHouseflag'+i).disabled == true && document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value=="N"){
								document.getElementById('compOwnedHouseflag'+i).checked = false; 
								document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
						}
						///document.getElementById('compOwnedHouse').checked = true;
					}catch(e){
						 alert(e);
					}
					///document.getElementById('compOwnedHouseflag'+i).checked = true;
				    ///document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="Y";
				}
				 
				///City Population >4 Lac
					if(document.getElementById('isCityPopulationflag'+i).checked == true){
						if(document.getElementById('carUsedflag'+i).disabled == true){
						document.getElementById('isCityPopulationflag'+i).disabled = true;
							document.getElementById('isCityPopulationflag'+i).checked = false;
					  		 document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="N";
						}else{
							document.getElementById('isCityPopulationflag'+i).checked = true;
					  		 document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="Y";
						}
					}
				///Car Used
					if(document.getElementById('carUsedflag'+i).checked == true){
						if(document.getElementById('carUsedflag'+i).disabled == true){
							document.getElementById('carUsedflag'+i).checked = false;
							document.getElementById('carUsedCheckedHiddenflag'+i).value="N";
						}else{
							document.getElementById('carUsedCheckedHiddenflag'+i).value="Y";
						}
					}
					///Cubic capacity < 1600
					if(document.getElementById('cubicCapacityflag'+i).checked == true){  
						if(document.getElementById('carUsedCheckedHiddenflag'+i).value=="N"){
						document.getElementById('cubicCapacityflag'+i).checked = false;
							document.getElementById('cubicCapacityCheckedHiddenflag'+i).value="N";
						}else{
							document.getElementById('cubicCapacityCheckedHiddenflag'+i).value="Y";
						}
					}
				///Driver Used
					if(document.getElementById('driverUsedflag'+i).checked == true){
					if(document.getElementById('carUsedCheckedHiddenflag'+i).value=="N"){
					document.getElementById('driverUsedflag'+i).checked = false;
						document.getElementById('driverUsedCheckedHiddenflag'+i).value="N";
					}else{
						document.getElementById('driverUsedCheckedHiddenflag'+i).value="Y";
					}
				}
			} 
		} else {
			for(var i = 1; i <= totalRecords; i++) {
				document.getElementById(checkBox + i).checked = false;
				//In India
				if(!document.getElementById('inIndiaflag'+i).checked == true){
						document.getElementById('inIndiaCheckedHiddenflag'+i).value="N";
					}
				//Is Metro
				if(!document.getElementById('isMetroflag'+i).checked == true){
					document.getElementById('isMetroCheckedHiddenflag'+i).value="N";
				}
				//House Rent paid by Company
				if(!document.getElementById('houseRentpaidbyCompanyflag'+i).checked == true){
					document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="N";
					document.getElementById('rentAmt'+i).disabled = false;  
				}
				
				if(!document.getElementById('compOwnedHouseflag'+i).checked == true){ 
					document.getElementById('compOwnedHouseflag'+i).checked = false;
					document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
				
				}
				///City Population >4 Lac
				if(!document.getElementById('isCityPopulationflag'+i).checked == true){
						 	document.getElementById('isCityPopulationflag'+i).checked = false;
					  		 document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="N";
					} 
				///Car Used
					if(!document.getElementById('carUsedflag'+i).checked == true){
						document.getElementById('carUsedflag'+i).checked = false;
						document.getElementById('carUsedCheckedHiddenflag'+i).value="N";
					} 
				///Cubic capacity < 1600
					if(!document.getElementById('cubicCapacityflag'+i).checked == true){
						document.getElementById('cubicCapacityCheckedHiddenflag'+i).value="N";
					}
				///Driver Used
					if(!document.getElementById('driverUsedflag'+i).checked == true){
						document.getElementById('driverUsedCheckedHiddenflag'+i).value="N";
					}
			
			} 
		}
		}catch(e){
			////alert(e);
		}
}

function callChecked(checkBox, field){
		try{
		var count=1;
			var countMetro=1;
			var houseRent=1;
			var compOwned=1;
			var isCityPopulation=1;
			var isCarUsed=1;
			var cubicCapacity=1;
			var driverUsed =1;
		
		if(document.getElementById(checkBox).checked == true){	  
			document.getElementById(field).value="Y";
			
			for(var i = 1; i < 13; i++) {
			if(document.getElementById('inIndiaflag'+i).checked == true){
				count=count+1;
				document.getElementById('inIndiaCheckedHiddenflag'+i).value="Y";
			}
			if(document.getElementById('isMetroflag'+i).checked == true){
				countMetro=countMetro+1;
				document.getElementById('isMetroCheckedHiddenflag'+i).value="Y";
			}
			///House Rent paid by Company  
				if(document.getElementById('houseRentpaidbyCompanyflag'+i).checked == true){
					houseRent=houseRent+1;  
					
					if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
					document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="N";
					}else{
						document.getElementById('compOwnedHouseflag'+i).disabled = true;   
						document.getElementById('compOwnedHouseflag'+i).checked = false;
						document.getElementById('rentAmt'+i).disabled = true; 
						document.getElementById('rentAmt'+i).value = 0;
						document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="Y";
						document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
						
					}
					 
					
				}
				///Company owned house
					if(document.getElementById('compOwnedHouseflag'+i).checked == true){
						compOwned=compOwned+1;
						if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
							document.getElementById('isCityPopulationflag'+i).disabled = true;
							document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
						}else{
							document.getElementById('isCityPopulationflag'+i).disabled = false;
							document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="Y";
							document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = true;
						}
					}
				///City Population >4 Lac
					if(document.getElementById('isCityPopulationflag'+i).checked == true){
						isCityPopulation=isCityPopulation+1;
						document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="Y";
					}
				///Car Used 
					if(document.getElementById('carUsedflag'+i).checked == true){ 
						isCarUsed=isCarUsed+1;
						document.getElementById('cubicCapacityflag'+i).disabled = false;
						document.getElementById('driverUsedflag'+i).disabled = false;
						document.getElementById('carUsedCheckedHiddenflag'+i).value="Y";
					} 
				///Cubic capacity < 1600
					if(document.getElementById('cubicCapacityflag'+i).checked == true){
						cubicCapacity=cubicCapacity+1;
						document.getElementById('cubicCapacityCheckedHiddenflag'+i).value="Y";
					}
				///Driver Used
					if(document.getElementById('driverUsedflag'+i).checked == true){
						driverUsed=driverUsed+1;
						document.getElementById('driverUsedCheckedHiddenflag'+i).value="Y";
					}
			}
			 
			document.getElementById('inIndia').checked =false;
			document.getElementById('isMetro').checked =false;
			document.getElementById('houseRentpaidbyCompany').checked =false;
			document.getElementById('compOwnedHouse').checked =false;
			document.getElementById('isCityPopulation').checked =false;
			document.getElementById('carUsed').checked =false;
				document.getElementById('cubicCapacity').checked =false;		
					document.getElementById('driverUsed').checked =false;
				
				
			if(count=='13'){
			document.getElementById('inIndia').checked =true;
			}
			if(countMetro=='13'){
			document.getElementById('isMetro').checked =true;
			}
			
			if(houseRent=='13'){
			document.getElementById('houseRentpaidbyCompany').checked =true;
			}
			if(compOwned=='13'){
			document.getElementById('compOwnedHouse').checked =true;
			
			}
			if(isCityPopulation=='13'){
			document.getElementById('isCityPopulation').checked =true;
			} 
			
			if(isCarUsed=='13'){
			document.getElementById('carUsed').checked =true;
			
			}
			if(cubicCapacity=='13'){
			document.getElementById('cubicCapacity').checked =true;	
			}
			
			if(driverUsed=='13'){
				document.getElementById('driverUsed').checked =true;
			}
			
	  	} else {
	  		////document.getElementById(field).value="N";
	  		for(var i = 1; i < 13; i++) {
					if(!document.getElementById('inIndiaflag'+i).checked == true){
						document.getElementById('inIndia').checked =false;
					 	document.getElementById('inIndiaCheckedHiddenflag'+i).value="N";
					}
			///IS METRO CHECKED
					if(!document.getElementById('isMetroflag'+i).checked == true){
						document.getElementById('isMetro').checked =false;
						document.getElementById('isMetroCheckedHiddenflag'+i).value="N";
					}
			///House Rent paid by Company
					if(!document.getElementById('houseRentpaidbyCompanyflag'+i).checked == true){
					
						document.getElementById('houseRentpaidbyCompany').checked =false;
						document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value="N";
						document.getElementById('compOwnedHouse').disabled = false;   
						document.getElementById('compOwnedHouseflag'+i).disabled = false;  
						document.getElementById('rentAmt'+i).disabled = false;
						document.getElementById('isCityPopulationflag'+i).disabled = false; 
						
				
						  
					}
				///Company owned house
					if(!document.getElementById('compOwnedHouseflag'+i).checked == true){
						if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
							document.getElementById('compOwnedHouseflag'+i).checked = false;
							document.getElementById('compOwnedHouseflag'+i).disabled = true; 
							document.getElementById('isCityPopulationflag'+i).checked = false;
							document.getElementById('isCityPopulationflag'+i).disabled = true; 
							document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = true; 
						}else{
							document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = false;
							document.getElementById('compOwnedHouse').checked =false;
							///document.getElementById('isCityPopulationflag'+i).checked = false;
							///document.getElementById('isCityPopulationflag'+i).disabled = true;
							document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value="N";
							
						}
					}
				///City Population >4 Lac
					if(document.getElementById('isCityPopulationflag'+i).checked == true){
						isCityPopulation=isCityPopulation+1;
						document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="Y";
					}else{
						if(document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="N"){
							document.getElementById('isCityPopulation').checked =false;
						}
				///document.getElementById('isCityPopulationCheckedHiddenflag'+i).value="N";
					}
				///Car Used   
					if(document.getElementById('carUsedflag'+i).checked == true){ 
						isCarUsed=isCarUsed+1;
						document.getElementById('carUsedCheckedHiddenflag'+i).value="Y";
					} else{
						document.getElementById('cubicCapacityflag'+i).checked = false;
						document.getElementById('cubicCapacityflag'+i).disabled = true;
						document.getElementById('driverUsedflag'+i).checked = false;
						document.getElementById('driverUsedflag'+i).disabled = true;
						document.getElementById('carUsedCheckedHiddenflag'+i).value="N";
					}
				///Cubic capacity < 1600
					if(!document.getElementById('cubicCapacityflag'+i).checked == true){
						document.getElementById('cubicCapacity').checked =false;
						document.getElementById('cubicCapacityflag'+i).checked == false;
						document.getElementById('cubicCapacityCheckedHiddenflag'+i).value="N";
					}
				///Driver Used
					if(!document.getElementById('driverUsedflag'+i).checked == true){ 
						document.getElementById('driverUsed').checked =false;
						document.getElementById('driverUsedCheckedHiddenflag'+i).value="N";
					}
			}
			document.getElementById('carUsed').checked =false;
	  		document.getElementById('isCityPopulation').checked =false;
			if(isCityPopulation=='13'){
			document.getElementById('isCityPopulation').checked =true;
			} 
			if(isCarUsed=='13'){
			document.getElementById('carUsed').checked =true;
			}
	  	} 
	  	} catch(e){  
	  		alert(e); 
	  	}
	}
	
		function onloadCall() {
			try{
			if(document.getElementById("paraFrm_ittRentCode").value==""){
				////document.getElementById('isCityPopulation').disabled = true;
				document.getElementById('cubicCapacity').disabled = true;
				document.getElementById('driverUsed').disabled = true;
			}
			for(var i = 1; i < 13; i++) {
				if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
					document.getElementById('inIndiaflag'+i).disabled = true;
					document.getElementById('inIndiaflag'+i).checked = false;
					document.getElementById('isMetroflag'+i).disabled = true;
					document.getElementById('isMetroflag'+i).checked = false;
					document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = true;
					document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
					document.getElementById('compOwnedHouseflag'+i).disabled = true;
					document.getElementById('compOwnedHouseflag'+i).checked = false;
					document.getElementById('isCityPopulationflag'+i).disabled = true;
					document.getElementById('isCityPopulationflag'+i).checked = false;
					document.getElementById('rentAmt'+i).readOnly = true;
					document.getElementById('rentAmt'+i).style.background = '#F2F2F2';
					document.getElementById('carUsedflag'+i).disabled = true;
					document.getElementById('carUsedflag'+i).checked = false;
					document.getElementById('cubicCapacityflag'+i).disabled = true;
					document.getElementById('cubicCapacityflag'+i).checked = false;
					document.getElementById('driverUsedflag'+i).disabled = true;
					document.getElementById('driverUsedflag'+i).checked = false;
				}
			} 
			 
		for(var i = 1; i < 13; i++) {
					////document.getElementById('isCityPopulationflag' + i).disabled = true;
					document.getElementById('cubicCapacityflag' + i).disabled = true;
					document.getElementById('driverUsedflag' + i).disabled = true;
					////document.getElementById('isCityPopulation').disabled = true;
					document.getElementById('cubicCapacity').disabled = true;
					document.getElementById('driverUsed').disabled = true;
					
				if(document.getElementById('inIndiaCheckedHiddenflag'+i).value=="Y"){
					document.getElementById('inIndiaflag'+i).checked = true;
				}	else{
					document.getElementById('inIndiaCheckedHiddenflag'+i).value=="N"
				}
				
				if(document.getElementById('isMetroCheckedHiddenflag'+i).value=="Y"){
					document.getElementById('isMetroflag'+i).checked = true;
				}	else{
					document.getElementById('isMetroCheckedHiddenflag'+i).value=="N"
				}
					
				if(document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="Y"){
					document.getElementById('houseRentpaidbyCompanyflag'+i).checked = true;
					document.getElementById('compOwnedHouseflag'+i).disabled = true;
					document.getElementById('rentAmt'+i).disabled = true;
					document.getElementById('rentAmt'+i).value = 0;
							 	
				}	else{
					document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="N"
					document.getElementById('rentAmt'+i).disabled = false;
				}
				
				if(document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value=="Y"){
					document.getElementById('compOwnedHouseflag'+i).checked = true;
					document.getElementById('isCityPopulationflag'+i).disabled = false;
				}	else{
					document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value=="N"
				}
				
				if(document.getElementById('isCityPopulationCheckedHiddenflag'+i).value=="Y"){
						document.getElementById('isCityPopulationflag'+i).checked = true;
				}	else{
						document.getElementById('isCityPopulationCheckedHiddenflag'+i).value=="N"
				}	 
				if(document.getElementById('carUsedCheckedHiddenflag'+i).value=="Y"){
						document.getElementById('carUsedflag'+i).checked = true;
						document.getElementById('cubicCapacityflag'+i).disabled = false;
						document.getElementById('driverUsedflag'+i).disabled = false;
				}else{ 
						document.getElementById('carUsedCheckedHiddenflag'+i).value=="N"
				}
				if(document.getElementById('cubicCapacityCheckedHiddenflag'+i).value=="Y"){
						document.getElementById('cubicCapacityflag'+i).checked = true;
				}	else{
						document.getElementById('cubicCapacityCheckedHiddenflag'+i).value=="N"
				}
				
				if(document.getElementById('driverUsedCheckedHiddenflag'+i).value=="Y"){
						document.getElementById('driverUsedflag'+i).checked = true;
				}	else{
						document.getElementById('driverUsedCheckedHiddenflag'+i).value=="N"
				}
					  
			}
			if(document.getElementById("paraFrm_ittRentCode").value==""){
				document.getElementById('inIndia').checked=true; 
			}
			}catch(e){
				 /////alert(e);
			}
		} 
				
			function unCheckCityPopulation(){
				try{ 
					for(var i = 1; i < 13; i++) {
						if(document.getElementById('compOwnedHouse').checked) {
						document.getElementById('compOwnedHouse').checked = true;
							if(document.getElementById('houseRentpaidbyCompanyflag'+i).checked == true){
								document.getElementById('houseRentpaidbyCompanyflag'+i).checked = true;
								document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = false;
								document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="Y"
							}else{
								document.getElementById('houseRentpaidbyCompany').disabled = true;
								document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="N"
								document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
								document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = true;
							}
						} else {
							if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
									document.getElementById('houseRentpaidbyCompany').disabled = false;
									document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="N"
									document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
									document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = true;
							}else{
								document.getElementById('compOwnedHouseflag'+i).checked = false;
								document.getElementById('compOwnedHouseCheckedHiddenflag'+i).value=="N"
								document.getElementById('houseRentpaidbyCompany').disabled = false;
								document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
								document.getElementById('houseRentpaidbyCompanyflag'+i).disabled = false;
							}
						}
					}
				} catch(e){
				////alert(e);
				} 
			}
			
			function unCompOwnedHouse(){ 
				try{ 
					for(var i = 1; i < 13; i++) {
						if(document.getElementById('houseRentpaidbyCompany').checked) {
							
							if(document.getElementById('compOwnedHouseflag'+i).checked == true){
								document.getElementById('compOwnedHouse').disabled = false; 
								document.getElementById('compOwnedHouseflag' + i).disabled = false;
							}else{
								document.getElementById('compOwnedHouse').disabled = true; 
								document.getElementById('compOwnedHouseflag' + i).disabled = true;
							}
							////document.getElementById('isCityPopulation').disabled = true;
						} else {
						
							if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
							
								document.getElementById('compOwnedHouseflag'+i).checked = false;
								document.getElementById('compOwnedHouse').checked = false;
								document.getElementById('compOwnedHouse').disabled = false;
								document.getElementById('compOwnedHouseflag' + i).disabled = true;
									/////document.getElementById('isCityPopulation').disabled = false; 
							}else{
								document.getElementById('houseRentpaidbyCompanyCheckedHiddenflag'+i).value=="N"
								document.getElementById('houseRentpaidbyCompanyflag'+i).checked = false;
								document.getElementById('compOwnedHouse').disabled = false; 
								document.getElementById('compOwnedHouseflag' + i).disabled = false;
							}
						}
					}
				} catch(e){
				////alert(e);
				} 
			}
			
			function unCheckCarUsed(){ 
				try{ 
					for(var i = 1; i < 13; i++) {
						if(document.getElementById('carUsed').checked) {
							if(document.getElementById('rentApplFlag'+i).value=="FALSE"){
								document.getElementById('cubicCapacity').disabled = true;
								document.getElementById('driverUsed').disabled = true;
								document.getElementById('cubicCapacityflag' + i).disabled = true;
								document.getElementById('driverUsedflag' + i).disabled = true;
							} else{
								document.getElementById('cubicCapacity').disabled = false;
								document.getElementById('driverUsed').disabled = false;
								document.getElementById('cubicCapacityflag' + i).disabled = false;
								document.getElementById('driverUsedflag' + i).disabled = false;
							}
					
						} else {
							document.getElementById('cubicCapacity').checked = false;
							document.getElementById('cubicCapacity').disabled = true;
							document.getElementById('driverUsed').checked = false;
							document.getElementById('driverUsed').disabled = true;
								document.getElementById('cubicCapacityflag'+i).checked = false;
							document.getElementById('cubicCapacityflag' + i).disabled = true;
							document.getElementById('driverUsedflag'+i).checked = false;
							document.getElementById('driverUsedflag' + i).disabled = true;
						}
					}
				} catch(e){
				///alert(e);
				}
			}
			
			
</script>