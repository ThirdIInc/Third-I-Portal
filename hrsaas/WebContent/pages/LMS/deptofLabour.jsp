
<!-- Nilesh Dhandare -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="previousPage" value="input" />
	<s:hidden name="generalInfo.ReturnID" />
	<s:hidden name="generalInfo.GeneralInfoID" />
	<s:hidden name="generalInfo.ownershipOptionValue" />
	<s:hidden name="pageToShow" value="rentallowance" />
	<s:hidden name="workForceInfo.workForceId" />
	<s:hidden name="orgId" /><s:hidden name="frequency" />
	<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
	<s:hidden name="orgName" />
	
	<table width="99%" border="0" align="right" class="formbg"
		cellpadding="1" cellspacing="1">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">General
					Info </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="left"> <s:select
						name="actList" list="actMap" size="1" headerKey=""
						headerValue="--Select Act--" cssStyle="width:150" theme="simple" /><input type="submit" value="Go To"
						class="token" onclick="return goTo();"></div>
					</td>

				</tr>
			</table>
			</td>
		</tr>
		

		<tr>
			<td class="formtext">
			<table width="100%" border="0" style="border: 1px solid #565656;">

				<tr>
					<td align="left" colspan="5" width="100%"><b><label
						class="set" name="general_Info" id="general_Info"
						ondblclick="callShowDiv(this);"><%=label.get("general_Info")%></label></b></td>
				</tr>

				<tr class="clsTRBody">
					<td width="3%">&nbsp;1</td>
					<td width="37%"><label class="set" name="employee_name"
						id="employee_name" ondblclick="callShowDiv(this);"><%=label.get("employee_name")%></td>
					<td width="60%"><s:textfield
						name="generalInfo.EstablishmentName" size="70"
						onfocus="clearText('paraFrm_EstablishmentName','Establishment Name')"
						onblur="setText('paraFrm_EstablishmentName','Establishment Name')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td></td>
					<td><label class="set" name="employee_address"
						id="employee_address" ondblclick="callShowDiv(this);"><%=label.get("employee_address")%></label>
					&nbsp;</td>
					<td><s:textfield name="generalInfo.EstablishmentAddress"
						size="70"
						onfocus="clearText('paraFrm_EstablishmentAddress','Establishment Address.')"
						onblur="setText('paraFrm_EstablishmentAddress','Establishment Address.')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td></td>
					<td><label class="set" name="state" id="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label></td>
					<td><!--<s:select headerKey="" headerValue="--Select--"
						name="reportType" list="#{'M':'Maharastra'}" />--> <s:textfield
						name="generalInfo.state" size="70"
						onfocus="clearText('paraFrm_generalInfo_state','State.')"
						onblur="setText('paraFrm_generalInfo_state','State.')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="district" id="district"
						ondblclick="callShowDiv(this);"><%=label.get("district")%></label></td>
					<td><!--<s:select headerKey="" headerValue="--Select--"
						name="reportType"
						list="#{'1':'Pune(Pune)','2':'Wardha(Wardha)' ,'3':'Nagpur West(Nagpur)','4':'Washi(Washi)','5':'Thane(Thane)' ,'6':'Kandivali(Kandivali)','7':'Raigad(Raigad)','8':'Chiplun(Chiplun)' ,'9':'Goregaon(Goregaon)'}" />
					--> <s:textfield name="generalInfo.city" size="70"
						onfocus="clearText('paraFrm_city','City.')"
						onblur="setText('paraFrm_city','City.')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td></td>
					<td><label class="set" name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
					<td><s:textfield name="generalInfo.city" size="70"
						onfocus="clearText('paraFrm_city','City.')"
						onblur="setText('paraFrm_city','City.')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="pincode" id="pincode"
						ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
					</td>
					<td><s:textfield name="generalInfo.pincode" size="70" onkeypress="return numbersWithDot();"
						onfocus="clearText('paraFrm_pincode','Pincode.')"
						onblur="setText('paraFrm_pincode','Pincode.')" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>2</td>
					<td colspan=6>
					<table width="100%" cellpadding="6" cellspacing="0"
						class="formlayout" border="1" style="border: 1px solid #565656;">
						<tr class="clsTRPageCaption" width="100%"
							style="background-color: #AEAEAE;">
							<th width="35%">&nbsp;</th>
							<th><label class="set" name="owner_employer"
								id="owner_employer" ondblclick="callShowDiv(this);"><%=label.get("owner_employer")%></label>
							</th>
							<th><label class="set" name="manager" id="manager"
								ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
							</th>
						</tr>
						<tr class="clsTROdd">
							<td width="38%"><label class="set" name="employee_name"
								id="employee_name" ondblclick="callShowDiv(this);"><%=label.get("employee_name")%></label></td>
							<td width="31%"><s:textfield size="40" theme="simple"
								name="generalInfo.employerName"
								onfocus="clearText('paraFrm_employerName','Employer Name.')"
								onblur="setText('paraFrm_employerName','Employer Name.')" />
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.managerName"
								onfocus="clearText('paraFrm_managerName','Manager Name.')"
								onblur="setText('paraFrm_managerName','Manager Name.')" /></td>
						</tr>
						<tr class="clsTROdd">
							<td><label class="set" name="employee_desig"
								id="employee_desig" ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("employee_desig")%></label>
							</td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.employerDesignation"
								onfocus="clearText('paraFrm_employerDesignation','Employer Designation.')"
								onblur="setText('paraFrm_employerDesignation','Employer Designation.')" /></td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.managerDesignation"
								onfocus="clearText('paraFrm_managerName','Manager Name.')"
								onblur="setText('paraFrm_managerName','Manager Name.')" /></td>
						</tr>
						<tr class="clsTROdd">
							<td><label class="set" name="address" id="address"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("address")%></label>
							</td>

							<td><!--<s:textfield size="40" theme="simple"
								name="generalInfo.employerAddress"
								onfocus="clearText('paraFrm_employerAddress','Employer Address.')"
								onblur="setText('paraFrm_employerAddress','Employer Address.')" />
								--> <s:textfield size="40" theme="simple"
								name="generalInfo.EstablishmentAddress"
								onfocus="clearText('paraFrm_employerAddress','Employer Address.')"
								onblur="setText('paraFrm_employerAddress','Employer Address.')" />
							</td>
							<td><!--<s:textfield size="40" theme="simple"
								name="generalInfo.managerAddress"
								onfocus="clearText('paraFrm_managerAddress','Manager Address.')"
								onblur="setText('paraFrm_managerAddress','Manager Address.')" />-->
							<s:textfield size="40" theme="simple"
								name="generalInfo.EstablishmentAddress "
								onfocus="clearText('paraFrm_employerAddress','Employer Address.')"
								onblur="setText('paraFrm_employerAddress','Employer Address.')" />
							</td>
						</tr>
						<tr class="clsTROdd">
							<td><label class="set" name="telephone" id="telephone"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("telephone")%></label>
							</td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.employerTelephone"
								onkeypress="return numbersWithDot();"
								onfocus="clearText('paraFrm_employerTelephone','Employer Telephone.')"
								onblur="setText('paraFrm_employerTelephone','Employer Telephone.')" /></td>
							<td><s:textfield size="40" theme="simple"
								onkeypress="return numbersWithDot();"
								name="generalInfo.managerTelephone"
								onfocus="clearText('paraFrm_managerTelephone','Manager Telephone.')"
								onblur="setText('paraFrm_managerTelephone','Manager Telephone.')" /></td>
						</tr>
						<tr class="clsTROdd">
							<td><label class="set" name="email_id" id="email_id"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("email_id")%></label>
							</td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.employerEmail"
								onfocus="clearText('paraFrm_employerEmail','Employer Email.')"
								onblur="setText('paraFrm_employerEmaile','Employer Email.')" /></td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.managerEmail"
								onfocus="clearText('paraFrm_managerEmail','Manager Email.')"
								onblur="setText('paraFrm_managerEmail','Manager Email.')" /></td>
						</tr>
						<tr class="clsTREvenRow">
							<td><label class="set" name="fax_no" id="fax_no"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("fax_no")%></label>
							</td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.employerFax"
								onkeypress="return numbersWithDot();"
								onfocus="clearText('paraFrm_employerFax','Employer Fax.')"
								onblur="setText('paraFrm_employerFax','Employer Fax.')" /></td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.managerFax"
								onkeypress="return numbersWithDot();"
								onfocus="clearText('paraFrm_managerFax','Manager Fax.')"
								onblur="setText('paraFrm_managerFax','Manager Fax.')" /></td>
						</tr>
						<tr class="clsTROdd">
							<td><label class="set" name="fax_no" id="mob_no"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("mob_no")%></label></td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.employerMobile"
								onkeypress="return numbersWithDot();"
								onfocus="clearText('paraFrm_employerMobile','Employer Mobile.')"
								onblur="setText('paraFrm_employerMobile','Employer Mobile.')" /></td>
							<td><s:textfield size="40" theme="simple"
								name="generalInfo.managerMobile"
								onkeypress="return numbersWithDot();"
								onfocus="clearText('paraFrm_managerMobile','Manager Mobile.')"
								onblur="setText('paraFrm_managerMobile','Manager Mobile.')" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>6</td>
					<td colspan="2"><label class="set" name="registration_info"
						id="registration_info" ondblclick="callShowDiv(this);"><%=label.get("registration_info")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan="2">
					<table width="100%" cellpadding="6" cellspacing="0" border="1"
						style="border: 1px solid #565656;" class="formlayout">
						<tr class="clsTROdd">
							<td width="100%">
							<table width="100%" border="0" cellpadding="3" cellspacing="0">
								<tr>
									<th width="33%"><label class="set" name="regi_number"
										id="regi_number" ondblclick="callShowDiv(this);"><%=label.get("regi_number")%></label>
									</th>
									<th width="33%"><label class="set" name="exp_date"
										id="exp_date" ondblclick="callShowDiv(this);"><%=label.get("exp_date")%></label>
									</th>
									<th width="34%"><label class="set" name="title_of_act"
										id="title_of_act" ondblclick="callShowDiv(this);"><%=label.get("title_of_act")%></label>
									</th>
								</tr>
								<tr>
									<td><s:textfield size="40" theme="simple"
										name="generalInfo.RegistrationNumber"
										onfocus="clearText('paraFrm_RegistrationNumber','Registration No.')"
										onblur="setText('paraFrm_RegistrationNumber','Registration No.')" /></td>

									<td><s:textfield
										name="generalInfo.RegistrationExpiryDate" size="30"
										onkeypress="return numbersWithHiphen();"
										onfocus="clearText('paraFrm_generalInfo_RegistrationExpiryDate','dd-mm-yyyy');" />
									<s:a
										href="javascript:NewCal('paraFrm_generalInfo_RegistrationExpiryDate','DDMMYYYY');">
										<img src="../pages/common/css/default/images/Date.gif"
											width="16" height="16" border="0" />
									</s:a></td>

									<td><s:textfield size="40" theme="simple"
										name="generalInfo.TitleOfAct"
										onfocus="clearText('paraFrm_TitleOfAct','Title Of Act.')"
										onblur="setText('paraFrm_TitleOfAct','Title Of Act.')" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<div align="right"><input type="button" class="add"
								value="   Add More" onclick="return callAdd();" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>7</td>
					<td><label name="status_estb" id="status_estb"
						ondblclick="callShowDiv(this);"><%=label.get("status_estb")%></label>
					</td>
					<td><s:select headerKey="" headerValue="--Select--"  cssStyle="width:215"
						name="generalInfo.legalStatusOfEstablishment"
						list="#{'1':'Sole proprietor','2':'Partnership' ,'3':'Private company','4':'Public company','5':'Co-operative' ,'6':'Family business','7':'Govt./Semi Government'}" />
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>8</td>
					<td><label name="ownership" id="ownership"
						ondblclick="callShowDiv(this);"><%=label.get("ownership")%></label>
					</td>

					<td align="left"><s:select headerKey="" cssStyle="width:215"
						headerValue="--Select--" name="generalInfo.ownership" 
						list="#{'1':'National','2':'Foreign' ,'3':'Joint National and Eoreign '}" />
					</td>


				</tr>
				<tr class="clsTRBody">
					<td>9</td>
					<td><label name="type_of_eastablishment"
						id="type_of_eastablishment" ondblclick="callShowDiv(this);"><%=label.get("type_of_eastablishment")%></label>
					<br>
					</td>
					<td><s:select headerKey="" headerValue="--Select--" cssStyle="width:215"
						name="generalInfo.typeOfEmployment"
						list="#{'1':'Factory','2':'Retail or wholesale shop' ,'3':'Commercial establishment','4':'Hotel','5':'Restaurant/Eating House' ,'6':'Theatre','7':'Public Amusement or Entertainment','8':'Construction Establishment','9':'Other'}" />
					</td>
				</tr>

				<tr class="clsTRBody">
					<td>10</td>
					<td colspan="2"><label class="set"
						name="manufacturing_process" id="manufacturing_process"
						ondblclick="callShowDiv(this);"><%=label.get("manufacturing_process")%></label>
					</td>
					<td colspan="3" align="left" width="15%"></td>
				</tr>
				<tr class="clsTRBody">
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="35%"><label class="set"
						name="digit_code_manufacturing_process"
						id="digit_code_manufacturing_process"
						ondblclick="callShowDiv(this);"><%=label.get("digit_code_manufacturing_process")%></label></td>
					<td colspan="3" align="left" width="15%"><s:textfield
						onkeypress="return numbersWithDot();"
						name="generalInfo.digitCodeManufacturingProcess" size="30" /></td>
				</tr>

				<tr class="clsTRBody">
					<td>11</td>
					<td><label class="set" name="plan_approval_number_and_date"
						id="plan_approval_number_and_date" ondblclick="callShowDiv(this);"><%=label.get("plan_approval_number_and_date")%></label>
					</td>
					<td colspan="3" align="left" width="15%"></td>
				</tr>

				<tr class="clsTRBody">
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="35%"><label class="set"
						name="plan_approval_number" id="plan_approval_number"
						ondblclick="callShowDiv(this);"><%=label.get("plan_approval_number")%></label></td>
					<td colspan="3" align="left" width="15%"><s:textfield
						name="generalInfo.planApprovalNumber" size="30" /></td>
				</tr>
				<tr class="clsTRBody">
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="35%"><label class="set"
						name="plan_approval_date" id="plan_approval_date"
						ondblclick="callShowDiv(this);"><%=label.get("plan_approval_date")%></label></td>

					<td colspan="3" align="left" width="15%"><s:textfield
						name="generalInfo.planApprovalDate" size="30"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_generalInfo_planApprovalDate','dd-mm-yyyy');" />
					<s:a
						href="javascript:NewCal('paraFrm_generalInfo_planApprovalDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>

				</tr>

				<tr class="clsTRBody">
					<td>12</td>
					<td colspan="1" width="35%"><label class="set"
						name="factory_certificate" id="factory_certificate"
						ondblclick="callShowDiv(this);"><%=label.get("factory_certificate")%></label></td>
					<td colspan="3" align="left" width="15%"><s:checkbox
						id="generalInfo.factoryCertificateFlag"
						name="generalInfo.factoryCertificateFlag" /> <label class="set"
						name="yes/no" id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="35%"><label class="set"
						name="factory_certificate_date" id="factory_certificate_date"
						ondblclick="callShowDiv(this);"><%=label.get("factory_certificate_date")%></label></td>

					<td colspan="3" align="left" width="15%"><s:textfield
						name="generalInfo.factoryCertificateDate" size="30"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_generalInfo_factoryCertificateDate','dd-mm-yyyy');" />
					<s:a
						href="javascript:NewCal('paraFrm_generalInfo_factoryCertificateDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>

				</tr>
				<tr class="clsTRBody">
					<td>13</td>
					<td><label class="set" name="employment_type_as_per_shcedule"
						id="employment_type_as_per_shcedule"
						ondblclick="callShowDiv(this);"><%=label.get("employment_type_as_per_shcedule")%></label>

					</td>
					<td><s:select headerKey="" headerValue="--Select--" cssStyle="width:215"
						name="generalInfo.employmentTypeAsPerShcedule"
						list="#{'1':'Engineering','2':'Shops and Establishments' ,'3':'Laundry','4':'Rubber','5':'Plastics'}" />
					</td>
				</tr>

				<tr class="clsTRBody">
					<td>14</td>
					<td colspan="2"><label class="set"
						name="number_of_branch_business_names_addr"
						id="number_of_branch_business_names_addr"
						ondblclick="callShowDiv(this);"><%=label.get("number_of_branch_business_names_addr")%></label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan="2">
					<table width="100%" cellpadding="6" cellspacing="0" border="1"
						class="formlayout">
						<tr class="clsTROdd">
							<td width="100%">
							<table width="100%" border="0" cellpadding="3" cellspacing="0">
								<tr>
									<th width="20%"><label class="set" name="number_of_branch"
										id="number_of_branch" ondblclick="callShowDiv(this);"><%=label.get("number_of_branch")%></label>
									</th>
									<th width="20%"><label class="set" name="name_of_branch"
										id="name_of_branch" ondblclick="callShowDiv(this);"><%=label.get("name_of_branch")%></label>
									</th>
									<th width="20%"><label class="set"
										name="address_of_branch" id="address_of_branch"
										ondblclick="callShowDiv(this);"><%=label.get("address_of_branch")%></label>
									</th>
									<th width="20%"><label class="set" name="contact_person"
										id="contact_person" ondblclick="callShowDiv(this);"><%=label.get("contact_person")%></label>
									</th>
									<th width="20%"><label class="set" name="telephone_Number"
										id="telephone_Number" ondblclick="callShowDiv(this);"><%=label.get("telephone_Number")%></label>
									</th>
								</tr>
								<tr>
									<td><s:textfield name="numberOfBranchOrBusiness" size="30" /></td>
									<td><s:textfield name="nameOfBranchOrBusiness" size="30" /></td>
									<td><s:textarea name="addressofBranchOrBusiness" cols="45"
										rows="2" /></td>
									<td><s:textfield name="contactPersonOfBranchOrBusiness"
										size="30" /></td>
									<td><s:textfield name="telephoneNumberOfBranchOrBusiness"
										onkeypress="return numbersWithDot();" size="30" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr class="clsTROdd">
							<td>
							<div align="right"><input type="submit" name="Submit32"
								value="Add More " /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr class="clsTRBody">
					<td>15</td>
					<td><label class="set" name="date_of_commencement"
						id="date_of_commencement" ondblclick="callShowDiv(this);"><%=label.get("date_of_commencement")%></label></td>

					<td colspan="3" align="left" width="15%"><s:textfield
						name="generalInfo.dateofCommencement" size="30"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_generalInfo_dateofCommencement','dd-mm-yyyy');" />
					<s:a
						href="javascript:NewCal('paraFrm_generalInfo_dateofCommencement','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>


				</tr>
			</table>
			</td>
		</tr>

		<!-- Work Force -->

		<tr>
			<td class="formtext">

			<table width="100%" border="0"
				onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
				cellspacing="2" class="clsTable" style="border: 1px solid #565656;"
				align="center">
				<tr>
					<td class="formtext" colspan="5">
					<table width="100%" border="0">
						<tr>
							<td align="left" colspan="5" width="100%"><b><label
								class="set" name="work_force" id="work_force"
								ondblclick="callShowDiv(this);"><%=label.get("work_force")%></label></b></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">&nbsp;</td>
					<td width="45%">&nbsp;</td>
					<td width="12%" align="left">&nbsp;<label class="set"
						name="male" id="male" ondblclick="callShowDiv(this);"><%=label.get("male")%></label></td>
					<td width="14%" align="left">&nbsp;<label class="set"
						name="female" id="female" ondblclick="callShowDiv(this);"><%=label.get("female")%></label></td>
					<td width="12%" align="left"><label class="set" name="total"
						id="total" ondblclick="callShowDiv(this);"><%=label.get("total")%></label></td>
				</tr>

				<tr class="clsTRBody">
					<td rowspan=5>&nbsp;13</td>
					<td colspan="3"><label class="set" name="no_of_permanant_emp"
						id="no_of_permanant_emp" ondblclick="callShowDiv(this);"><%=label.get("no_of_permanant_emp")%></label>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="manager_and_supervisors"
						id="manager_and_supervisors" ondblclick="callShowDiv(this);"><%=label.get("manager_and_supervisors")%></label></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permanentManagersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permanentManagersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permanenetManagersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="worker_18" id="worker_18"
						ondblclick="callShowDiv(this);"><%=label.get("worker_18")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.workersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.workersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.workersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="worker_bet" id="worker_bet"
						ondblclick="callShowDiv(this);"><%=label.get("worker_bet")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.adolscentWorkersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.adolscentWorkersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.workersOver15ButLess18Total" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="total" id="total"
						ondblclick="callShowDiv(this);"><%=label.get("total")%></label></td>
					<td align="left"><input type="text" style="width: 50px;" /></td>
					<td align="left"><input type="text" style="width: 50px;" /></td>
					<td align="left"><input name="text5" type="text"
						style="width: 50px;" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>14</td>
					<td><label class="set" name="cont_workers" id="cont_workers"
						ondblclick="callShowDiv(this);"><%=label.get("cont_workers")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.contractWorkersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.contractWorkersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.contractWorkersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=3>15</td>
					<td><label class="set" name="daily_wage_worker"
						id="daily_wage_worker" ondblclick="callShowDiv(this);"><%=label.get("daily_wage_worker")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.dailywageWorkersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.dailywageWorkersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.dailywageWorkersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="temp_worker" id="temp_worker"
						ondblclick="callShowDiv(this);"><%=label.get("temp_worker")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.tempWorkersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.tempWorkersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.tempWorkersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="casual_worker" id="casual_worker"
						ondblclick="callShowDiv(this);"><%=label.get("casual_worker")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.casualWorkersMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.casualWorkersFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.casualWorkersTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=2>16</td>
					<td><label class="set" name="appt" id="appt"
						ondblclick="callShowDiv(this);"><%=label.get("appt")%></label></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.apprenticeMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.apprenticeFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.apprenticeTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="trainee" id="trainee"
						ondblclick="callShowDiv(this);"><%=label.get("trainee")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.traineeMale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.traineeFemale" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.traineeTotal" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=3>17</td>
					<td colspan="2"><label class="set" name="family_mem"
						id="family_mem" ondblclick="callShowDiv(this);"><%=label.get("family_mem")%></label>
					</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="paid" id="paid"
						ondblclick="callShowDiv(this);"><%=label.get("paid")%></label></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersMalePaid" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersFemalePaid" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersTotalPaid" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="unpaid" id="unpaid"
						ondblclick="callShowDiv(this);"><%=label.get("unpaid")%></label></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersMaleUnpaid" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersFemaleUnpaid" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.familyMembersTotalUnpaid" /></td>
				</tr>
				<tr class="clsTRBody">
					<td rowspan=5>18</td>
					<td colspan=4><label class="set"
						name="permannat_workers_years_of_service"
						id="permannat_workers_years_of_service"
						ondblclick="callShowDiv(this);"><%=label.get("permannat_workers_years_of_service")%></label>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="less_than_1" id="less_than_1"
						ondblclick="callShowDiv(this);"><%=label.get("less_than_1")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersMaleLessThanOne" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersFemaleLessThanOne" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersTotalLessThanOne" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="one_to_5" id="one_to_5"
						ondblclick="callShowDiv(this);"><%=label.get("one_to_5")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersMaleOneToFive" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersFemaleOneToFive" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersTotalOneToFive" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="five_years_to_ten"
						id="five_years_to_ten" ondblclick="callShowDiv(this);"><%=label.get("five_years_to_ten")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersMaleFiveToTen" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersFemaleFiveToTen" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersTotalFiveToTen" /></td>
				</tr>
				<tr class="clsTRBody">
					<td><label class="set" name="over_ten_years"
						id="over_ten_years" ondblclick="callShowDiv(this);"><%=label.get("over_ten_years")%></label>
					</td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersMaleMoreThanTen" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersFemaleMoreThanTen" /></td>
					<td align="left"><s:textfield size="10" theme="simple"
						onkeypress="return numbersWithDot();"
						name="workForceInfo.permWorkersTotalMoreThanTen" /></td>
				</tr>
			</table>

			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table width="100%">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%"></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="generalInfo.ownershipOptionValue"
		id="ownershipOptionValue" />
	<!-- Main Table -->
</s:form>


<script type="text/javascript"></script>
<script>
	function goTo() {
		var combo = trim(document.getElementById('paraFrm_actList').value);
		var pageValue='';
		if(combo==''){
			alert('Please select act');
			return false;
		}
		if(combo=='POG'){
			pageValue='gratuityrules';
		}
		else if(combo=='MHRA'){
			pageValue='rentallowance';
		}
		else if(combo=='POB'){
			pageValue='bonusrules';
		}
		else if(combo=='CL'){
			pageValue='childlabour';
		}
		else if(combo=='ER'){
			pageValue='equalrenumeration';
		}else if(combo=='MB'){
			pageValue='maternitybenefits';
		}else if(combo=='DISH'){
			pageValue='annualfactory';
		}
		document.getElementById('paraFrm_pageToShow').value = pageValue;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}


	function nextFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}
	
	function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
	onload();
	
	function saveandnextFun()  {
		try
		{
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_generalInfoSaveAndNextRecord.action';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert(e);
		}
	
	}
	
	
	
	function onload(){
		setText('paraFrm_EstablishmentName','Establishment Name')
	    setText('paraFrm_EstablishmentAddress','Establishment Address.')
        setText('paraFrm_city','Street,City,District')
	    setText('paraFrm_employerName','Employer Name.')
	    setText('paraFrm_employerDesignation','Employer Designation.')
	    setText('paraFrm_managerName','Manager Name.')
	    setText('paraFrm_managerDesignation','Manager Designation.')
	  
	}

	function setRadioValue(id){
	alert(id);
	var opt=document.getElementById('ownershipOptionValue').value =id.value;	
}
</script>
