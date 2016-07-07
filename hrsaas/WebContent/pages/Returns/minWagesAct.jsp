
<!-- Nilesh Dhandare -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


<s:form action="MinimumWagesAct" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" class="formbg"
		cellpadding="1" cellspacing="1">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Minimum
					Wages Act </strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font color="red">*</font>Indicates
					Required</div>
					</td>

				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext">
			<table width="100%" border="0" class="formbg">
				

				<tr>
					<td class="formtext" colspan="5">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td align="left" colspan="5" width="100%"><b>General
							Information</b></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td class="formtext" colspan="5">
					<table width="100%" border="0" class="formbg" cellpadding="1"
						cellspacing="1">
						<tr>

							<td colspan="1" width="40%"><label class="set"
								name="employee_name" id="employee_name"
								ondblclick="callShowDiv(this);"><%=label.get("employee_name")%></label></td>
							<td colspan="4" align="left" width="60%"><s:textfield
								name="establishmentName" size="70"
								onfocus="clearText('paraFrm_establishmentName','Establishment Name')"
								onblur="setText('paraFrm_establishmentName','Establishment Name')" />
							</td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="employee_address" id="employee_address"
								ondblclick="callShowDiv(this);"><%=label.get("employee_address")%></label></td>
							<td colspan="4" width="60%"><s:textfield
								name="establishmentAddress" size="70"
								onfocus="clearText('paraFrm_establishmentAddress','Establishment Address.')"
								onblur="setText('paraFrm_establishmentAddress','Establishment Address.')" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="street_city_district" id="street_city_district"
								ondblclick="callShowDiv(this);"><%=label.get("street_city_district")%></label></td>
							<td colspan="4" width="60%"><s:textfield name="city"
								size="70"
								onfocus="clearText('paraFrm_city','Street,City,District')"
								onblur="setText('paraFrm_city','Street,City,District')" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="employee_name" id="employee_name"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("employee_name")%></label></td>
							<td colspan="4" width="60%"><s:textfield size="70"
								theme="simple" name="employerName"
								onfocus="clearText('paraFrm_employerName','Employer Name.')"
								onblur="setText('paraFrm_employerName','Employer Name.')" /></td>


						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="employee_desig" id="employee_desig"
								ondblclick="callShowDiv(this);"
								onfocus="clearText('paraFrm_  ','   ')"
								onblur="setText('paraFrm_   ','  ')"><%=label.get("employee_desig")%></label></td>
							<td colspan="4" width="60%"><s:textfield size="70"
								theme="simple" name="employerDesignation"
								onfocus="clearText('paraFrm_employerDesignation','Employer Designation.')"
								onblur="setText('paraFrm_employerDesignation','Employer Designation.')" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="manager_name" id="manager_name"
								ondblclick="callShowDiv(this);"><%=label.get("manager_name")%></label></td>
							<td colspan="4" width="60%"><s:textfield size="70"
								theme="simple" name="managerName"
								onfocus="clearText('paraFrm_managerName','Manager Name.')"
								onblur="setText('paraFrm_managerName','Manager Name.')" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="manager_desig" id="manager_desig"
								ondblclick="callShowDiv(this);"><%=label.get("manager_desig")%></label></td>
							<td colspan="4" width="60%"><s:textfield size="70"
								theme="simple" name="managerDesignation"
								onfocus="clearText('paraFrm_managerDesignation','Manager Designation.')"
								onblur="setText('paraFrm_managerDesignation','Manager Designation.')" /></td>
						</tr>

						<tr>

							<td colspan="1" width="40%"><label class="set"
								name="contact_detail_manager" id="contact_detail_emp"
								ondblclick="callShowDiv(this);"><%=label.get("contact_detail_emp")%></label></td>

							<td colspan="1" width="5%"><label class="set" name="tel_no"
								id="tel_no" ondblclick="callShowDiv(this);"><%=label.get("tel_no")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="employerTelephone" maxlength="12" /></td>
							<td colspan="1" width="5%"><label class="set" name="fax_no"
								id="fax_no" ondblclick="callShowDiv(this);"><%=label.get("fax_no")%></label></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" name="employerFax" maxlength="12" /></td>
						</tr>

						<tr>

							<td colspan="1" width="40%"></td>
							<td colspan="1" width="5%"><label class="set"
								name="email_id" id="email_id" ondblclick="callShowDiv(this);"><%=label.get("email_id")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="employerEmail" maxlength="12" /></td>

							<td colspan="1" width="5%"><label class="set" name="mob_no"
								id="mob_no" ondblclick="callShowDiv(this);"><%=label.get("mob_no")%></label></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" name="employerMobile" maxlength="12" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="contact_detail_manager" id="contact_detail_manager"
								ondblclick="callShowDiv(this);"><%=label.get("contact_detail_manager")%></label></td>

							<td colspan="1" width="5%"><label class="set" name="tel_no"
								id="tel_no" ondblclick="callShowDiv(this);"><%=label.get("tel_no")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="managerTelephone" maxlength="12" /></td>
							<td colspan="1" width="5%"><label class="set" name="fax_no"
								id="fax_no" ondblclick="callShowDiv(this);"><%=label.get("fax_no")%></label></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" name="managerFax" maxlength="12" /></td>

						</tr>

						<tr>
							<td colspan="1" width="40%"></td>
							<td colspan="1" width="5%"><label class="set"
								name="email_id" id="email_id" ondblclick="callShowDiv(this);"><%=label.get("email_id")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="managerEmail" maxlength="12" /></td>

							<td colspan="1" width="5%"><label class="set" name="mob_no"
								id="mob_no" ondblclick="callShowDiv(this);"><%=label.get("mob_no")%></label></td>
							<td colspan="1" width="30%"><s:textfield size="20"
								theme="simple" name="managerMobile" maxlength="12" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="registration" id="registration"
								ondblclick="callShowDiv(this);"><%=label.get("registration")%></label>
							</td>
							<td colspan="1" width="5%"><label class="set"
								name="regi_number" id="regi_number"
								ondblclick="callShowDiv(this);"><%=label.get("regi_number")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="registrationNo" maxlength="12" /></td>

							<td colspan="1" width="5%" nowrap="nowrap"><label
								class="set" name="exp_date" id="exp_date"
								ondblclick="callShowDiv(this);"><%=label.get("exp_date")%></label></td>
							<td colspan="1" width="30%"><s:textfield maxLength="10"
								cssStyle="width:100" size="12" theme="simple"
								name="registrationExpiryDate" value="%{registrationExpiryDate}" /><s:a
								href="javascript:NewCal('paraFrm_registrationExpiryDate','DDMMYYYY');">
								<img src="../pages/images/cal.gif" class="iconImage" height="18"
									align="absmiddle" width="18">
							</s:a></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"></td>
							<td colspan="1" width="5%"><label class="set"
								name="title_of_act" id="title_of_act"
								ondblclick="callShowDiv(this);"><%=label.get("title_of_act")%></label></td>
							<td colspan="1" width="20%"><s:textfield size="20"
								theme="simple" name="titleOfAct" /></td>
							<td colspan="1" width="5%">&nbsp;</td>
							<td colspan="1" width="30%">&nbsp;</td>

						</tr>

						<tr>
							<td colspan="5" width="100%">&nbsp;</td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label name="status_estb"
								id="status_estb" ondblclick="callShowDiv(this);"><%=label.get("status_estb")%></label></td>
							<td colspan="4" width="60%"><s:select
								name="legalStatusOfEstablishment" headerKey="1"
								headerValue="-----------Select-------------"
								list="#{'sole proprietor':'Sole Proprietor','Partnership':'Partnership','Private Company':'Private Company','Public Company':'Public Company','Family Business':'Family Business','Other':'Other'}"
								cssStyle="width:150" /></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label name="ownership"
								id="ownership" ondblclick="callShowDiv(this);"><%=label.get("ownership")%></label></td>
							<td colspan="4" width="60%"><s:select name="ownership"
								headerKey="1" headerValue="-----------Select-------------"
								list="#{'National ':'National ','Foreign':'Foreign','Joint National and Foreign':'Joint National and Foreign'}"
								cssStyle="width:150" /></td>

						</tr>


						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="type_of_emp" id="type_of_emp"
								ondblclick="callShowDiv(this);"><%=label.get("type_of_emp")%></label>
							</td>

							<td colspan="4" width="60%"><s:textfield size="45"
								theme="simple" name="typeOfEmployment" />D/D Schedule of Employment (Tick
							as appropriate)</td>


						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="date_of_commencement" id="date_of_commencement"
								ondblclick="callShowDiv(this);"><%=label.get("date_of_commencement")%></label></td>
							<td colspan="4" width="60%"><s:textfield maxLength="10"
								cssStyle="width:130" size="8" theme="simple"
								name="dateofCommencement" value="%{dateofCommencement}" /><s:a
								href="javascript:NewCal('paraFrm_dateofCommencement','DDMMYYYY');">
								<img src="../pages/images/cal.gif" class="iconImage" height="18"
									align="absmiddle" width="18">
							</s:a></td>


						</tr>


						<!--work force table start-->

						<tr>
							<td class="formtext" colspan="5">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td align="left" colspan="5" width="100%"><b>Work
									Force</b></td>
								</tr>
							</table>
							</td>
						</tr>

						<!--work force table  -->


						<tr>
							<td colspan="1" width="40%"><label class="set"></label></td>
							<td colspan="1" width="15%" align="center"><label
								class="set"><b>Male</b></label></td>
							<td colspan="1" width="15%" align="center"><label
								class="set"><b>Female</b></label></td>
							<td colspan="1" width="15%" align="center"><label
								class="set"><b>Total</b></label></td>
							<td colspan="1" width="15%"><label class="set"></label></td>
						</tr>





						<tr>
							<td colspan="5" width="100%"><label class="set"
								name="no_of_permanant_emp" id="no_of_permanant_emp"
								ondblclick="callShowDiv(this);"><%=label.get("no_of_permanant_emp")%></label></td>
						</tr>

						<tr>
							<td colspan="1" width="40%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="manager_and_supervisors"
								id="manager_and_supervisors" ondblclick="callShowDiv(this);"><%=label.get("manager_and_supervisors")%></label></td>
							<td colspan="1" width="15%" align="center"><s:textfield
								size="10" theme="simple" name="permanentManagersMale" /></td>
							<td colspan="1" width="15%" align="center"><s:textfield
								size="10" theme="simple" name="permanentManagersMale" /></td>
							<td colspan="1" width="15%" align="center"><s:textfield
								size="10" theme="simple" name="permanenetManagersTotal" /></td>
							<td colspan="1" width="15%"></td>

						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="worker_18" id="worker_18"
								ondblclick="callShowDiv(this);"><%=label.get("worker_18")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="workersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="workersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="workersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>
						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="worker_bet" id="worker_bet"
								ondblclick="callShowDiv(this);"><%=label.get("worker_bet")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="adolscentWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="adolscentWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="adolscentWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>
						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="worker_under_15" id="worker_under_15"
								ondblclick="callShowDiv(this);"><%=label.get("worker_under_15")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="childWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="childWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="childWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>




						<tr>

							<td colspan="1" width="40%"><label class="set"
								name="cont_workers" id="cont_workers"
								ondblclick="callShowDiv(this);"><%=label.get("cont_workers")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="contractWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="contractWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="contractWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>




						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="daily_wage_worker" id="daily_wage_worker"
								ondblclick="callShowDiv(this);"><%=label.get("daily_wage_worker")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="dailywageWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="dailywageWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="dailywageWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>

						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="temp_worker" id="temp_worker"
								ondblclick="callShowDiv(this);"><%=label.get("temp_worker")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="tempWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="tempWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="tempWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>
						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="casual_worker" id="casual_worker"
								ondblclick="callShowDiv(this);"><%=label.get("casual_worker")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="casualWorkersMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="casualWorkersFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="casualWorkersTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>





						<tr>
							<td colspan="1" width="40%"><label class="set" name="appt"
								id="appt" ondblclick="callShowDiv(this);"><%=label.get("appt")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="apprenticeMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="apprenticeFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="apprenticeTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>
						<tr>
							<td colspan="1" width="40%"><label class="set"
								name="trainee" id="trainee" ondblclick="callShowDiv(this);"><%=label.get("trainee")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="traineeMale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="traineeFemale" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="traineeTotal" /></td>
							<td colspan="1" width="15%"></td>
						</tr>




						<tr>
							<td colspan="5" width="100%"><label class="set"
								name="family_mem" id="family_mem"
								ondblclick="callShowDiv(this);"><%=label.get("family_mem")%></label></td>
						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="paid" id="paid"
								ondblclick="callShowDiv(this);"><%=label.get("paid")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersMalePaid" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersFemalePaid" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersTotalPaid" /></td>
							<td colspan="1" width="15%"></td>
						</tr>
						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="unpaid" id="unpaid"
								ondblclick="callShowDiv(this);"><%=label.get("unpaid")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersMaleUnpaid" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersFemaleUnpaid" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="familyMembersTotalUnpaid" /></td>
							<td colspan="1" width="15%"></td>
						</tr>


						<tr>
							<td colspan="5" width="100%"><label class="set"
								name="permannat_workers_years_of_service"
								id="permannat_workers_years_of_service"
								ondblclick="callShowDiv(this);"><%=label.get("permannat_workers_years_of_service")%></label></td>
						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="less_than_1" id="less_than_1"
								ondblclick="callShowDiv(this);"><%=label.get("less_than_1")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersMaleLessThanOne" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersFemaleLessThanOne" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersTotalLessThanOne" /></td>
							<td colspan="1" width="15%"></td>
						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="one_to_5" id="one_to_5"
								ondblclick="callShowDiv(this);"><%=label.get("one_to_5")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersMaleOneToFive" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersFemaleOneToFive" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersTotalOneToFive" /></td>
							<td colspan="1" width="15%"></td>
						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="five_years_to_ten" id="five_years_to_ten"
								ondblclick="callShowDiv(this);"><%=label.get("five_years_to_ten")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersMaleFiveToTen" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersFemaleFiveToTen" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersTotalFiveToTen" /></td>
							<td colspan="1" width="15%"></td>
						</tr>

						<tr>
							<td align="left" colspan="1" width="40%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
								class="set" name="over_ten_years" id="over_ten_years"
								ondblclick="callShowDiv(this);"><%=label.get("over_ten_years")%></label></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersMaleMoreThanTen" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersFemaleMoreThanTen" /></td>
							<td align="center" colspan="1" width="15%"><s:textfield
								size="10" theme="simple" name="permWorkersTotalMoreThanTen" /></td>
							<td colspan="1" width="15%"></td>
						<tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="5"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<!-- Main Table -->
</s:form>


<script type="text/javascript"></script>
<script>
function nextFun() {
	/*alert("next new called----");*/
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'MinimumWagesAct_next.action';
		document.getElementById('paraFrm').submit();
	}
	
	onload();
	
	function onload(){
		setText('paraFrm_establishmentName','Establishment Name')
	    setText('paraFrm_establishmentAddress','Establishment Address.')
        setText('paraFrm_city','Street,City,District')
	    setText('paraFrm_employerName','Employer Name.')
	    setText('paraFrm_employerDesignation','Employer Designation.')
	    setText('paraFrm_managerName','Manager Name.')
	    setText('paraFrm_managerDesignation','Manager Designation.')
	  
	}


</script>
