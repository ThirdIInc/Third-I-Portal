<!--@author Reeba_Joseph @date 30-11-2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="accMISReport" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Accountant MIS
					Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<input type="button"  class="token"	theme="simple" value="  Generate Report" onclick=" return generateReport();" />
						<input type="button" class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
						<input name="button" type="button" value="Save report criteria" class="save" onclick="saveReport()" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->

		<!-- SEARCH AND REPORT TITLE FIELDS -->
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<td>
						<label class="set" name="saved.report" id="saved.report" ondblclick="callShowDiv(this);"><%=label.get("saved.report")%></label>:
					</td>
					<td width="25%">
						<s:textfield name="misreport.savedReport" size="30" readonly="true" />
					</td>
					<td align="left">
						<s:hidden name="misreport.reportId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="14" width="14" theme="simple"
							 onclick="javascript:callsF9(500,325,'accMISReport_searchSavedReport.action');" />
					</td>
					<td></td>
					<td><label class="set" name="report.title" id="report.title"
						ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label>:</td>
					<td colspan="2"><s:textfield size="30" maxlength="30"
						name="misreport.reportTitle" onkeypress="return allCharacters();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- SEARCH AND REPORT TITLE FIELDS ENDS -->


		<!-- NAVIGATION TABS BEGINS -->
		<tr>
			<td>
			<div id="tabnav" style="vertical-align: bottom">
			<ul>
				<li id="list1"><a href="#" id="menuid1" class="on"
					onclick="showCurrent('menuid1','first')"> <span>Filter</span></a></li>
				<li id="list2"><a href="#" id="menuid2"
					onclick="showCurrent( 'menuid2','second')"> <span>Column
				Definition </span></a></li>
				<li id="list3"><a href="#" id="menuid3"
					onclick="showCurrent( 'menuid3','third')"> <span>Sorting
				Option </span></a></li>
				<li id="list3"><a href="#" id="menuid4"
					onclick="showCurrent( 'menuid4','fourth')"> <span>Advance
				Filter </span></a></li>
			</ul>
			</div>
			</td>
		</tr>
		<!-- NAVIGATION TABS ENDS -->

		<tr>
			<td><!-- DIV FIRST BEGINS - FILTERS -->
			<div id="first">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>
				<!-- FIRST NAME & LAST NAME -->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">
						<tr>
							<td><label class="set" name="first.name" id="first.name"
								ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
							:</td>
							<td colspan="1"><s:textfield size="20"
								name="misreport.firstName" onkeyup="return validNames();" /></td>
							<td><label class="set" name="last.name" id="last.name"
								ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
							:</td>
							<td colspan="1"><s:textfield size="20"
								name="misreport.lastName" onkeyup="return validNames();" /></td>
						</tr>
						<!-- GENDER & MARITAL STATUS -->
						<tr>
							<td width="10%" colspan="1"><label class="set" name="gender"
								id="gender1" ondblclick="callShowDiv(this);"><%=label.get("gender")%></label>
							:</td>
							<td colspan="1"><s:select name="misreport.gender" list="map"
								headerKey="1" headerValue="---------Select---------"
								cssStyle="width:157" /></td>
							<td width="10%" colspan="1"><label class="set"
								name="marital.status" id="marital.status1"
								ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>:</td>
							<td colspan="1"><s:select
								label="%{getText('maritalStatus')}"
								name="misreport.maritalStatus" id="sta" headerKey="1"
								headerValue="---------Select---------" cssStyle="width:157"
								list="#{'M':'Married','U':'UnMarried','W':'Widow','A':'Widower','D':'Divorce' }"
								onselect="return callStatus();" /></td>
						</tr>
						<!-- BLOOD GROUP -->
						<tr>

							<td width="10%" colspan="1"><label class="set"
								name="blood.group" id="blood.group1"
								ondblclick="callShowDiv(this);"><%=label.get("blood.group")%></label>:</td>
							<td colspan="1"><s:select name="misreport.bldGroup"
								cssStyle="width:157" headerKey="0"
								headerValue="---------Select---------"
								list="#{'A+':'A+','A-':'A-','B+':'B+','B-':'B-','AB+':'AB+','AB-':'AB-','O+':'O+','O-':'O-'}" /></td>
							<td width="10%" colspan="1"><label class="set" name="status"
								id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
							<td colspan="1"><s:select name="misreport.status"
								list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}"
								headerKey="1" headerValue="---------Select---------"
								cssStyle="width:157" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">
						<!-- DESIGNATION-->
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="designation" id="designation1"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="10%" colspan="1"><s:hidden name="rankCode" /> <s:textarea
								cols="70" rows="1" name="rankName" readonly="true" /></td>
							<td width="70%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_rankName',350,250,'accMISReport_f9rankaction.action',event,'false','no','right')">
							</td>
						</tr>
						<!-- PAYBILL & BRANCH -->
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="pay.bill" id="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
							:</td>
							<td width="10%" colspan="1"><s:hidden name="payBillId" /> <s:textarea
								cols="70" rows="1" name="payBillName" readonly="true" /></td>
							<td width="70%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_payBillName',350,250,'accMISReport_f9payBillaction.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><label class="set" name="branch"
								id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="10%" colspan="1"><s:hidden name="centerCode" />
							<s:textarea rows="1" cols="70" name="centerName" readonly="true" /></td>
							<td width="70%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_centerName',350,250,'accMISReport_f9centeraction.action',event,'false','no','right')" />
							</td>
						</tr>
						<!-- DEPARTMENT & DIVISION -->
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="department" id="department1"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="10%" colspan="1"><s:hidden name="deptCode" /><s:textarea
								cols="70" rows="1" name="deptName" readonly="true" /></td>
							<td width="20%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_deptName',350,250,'accMISReport_f9deptaction.action',event,'false','no','right')" /></td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="division" id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="10%" colspan="1"><s:hidden name="divCode" /> <s:textarea
								rows="1" cols="70" name="divName" readonly="true" /></td>
							<td width="70%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_divName',350,250,'accMISReport_f9divaction.action',event,'false','no','right')" /></td>

						</tr>
						<!-- EMPLOYEE TYPE & SHIFT -->
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="employee.type" id="employee.type1"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td width="10%" colspan="1"><s:hidden name="typeCode" /> <s:textarea
								cols="70" rows="1" name="typeName" readonly="true" /></td>
							<td width="70%" colspan="1"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_typeName',350,250,'accMISReport_f9typeaction.action',event,'false','no','right')" />
							</td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><label class="set" name="shift"
								id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>:</td>
							<td width="10%" colspan="1"><s:hidden name="shiftCode" /> <s:textarea
								cols="70" rows="1" name="shiftType" readonly="true" /></td>
							<td width="70%" colspan="1"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="14" width="14" theme="simple"
								onclick="javascript:callDropdown('paraFrm_shiftType',350,250,'accMISReport_f9shiftaction.action',event,'false','no','right')" /></td>
						</tr>
						<!-- REPORTING TO & STATUS -->
						<tr>
							<td width="10%" colspan="1"><label class="set"
								name="reporting.to" id="reporting.to"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>:</td>
							<td width="10%" colspan="1"><s:hidden name="reportingID"
								value="%{reportingID}" /> <s:hidden
								value="%{misreport.reportingToken}"
								name="misreport.reportingToken" /> <s:textarea cols="70"
								rows="1" name="reportingTo" readonly="true" /></td>
							<td width="70%" colspan="2"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callDropdown('paraFrm_reportingTo',350,250,'accMISReport_f9reporingToaction.action',event,'false','no','right')" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV FIRST ENDS - FILTERS --> <!-- DIV SECOND BEGINS - COLUMN DEFINITIONS -->
			<div id="second">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="8" class="text_head"><strong
								class="forminnerhead"> <label class="set"
								name="report.generation" id="report.generation"
								ondblclick="callShowDiv(this);"><%=label.get("report.generation")%></label>
							</strong></td>
						</tr>						
						<!-- NAME,DIVISION,DEPARTMENT,BRANCH -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="name" id="name" ondblclick="callShowDiv(this);"><%=label.get("name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.empNameFlag" onclick="AddItem('name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.divFlag" onclick="AddItem('division',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.deptFlag" onclick="AddItem('department',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.centerFlag" onclick="AddItem('branch',this);" /></td>
						</tr>
						<!-- DESIGNATION,EMP TYPE,GRADE,SHIFT -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.rankFlag" onclick="AddItem('designation',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="employee.type" id="employee.type"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.typeFlag"
								onclick="AddItem('employee.type',this);" /></td>

							<td align="right" colspan="1"><label class="set"
								name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.empGradeFlag" onclick="AddItem('grade',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="shift" id="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.shiftFlag" onclick="AddItem('shift',this);" /></td>
						</tr>
						<!-- DATE OF BIRTH,JOINING,CONFIRMATION,LEAVING -->
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="date.birth" id="date.birth"
								ondblclick="callShowDiv(this);"><%=label.get("date.birth")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.dateBirthFlag"
								onclick="AddItem('date.birth',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="date.joining" id="date.joining"
								ondblclick="callShowDiv(this);"><%=label.get("date.joining")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.dateRegFlag"
								onclick="AddItem('date.joining',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="date.confirmation" id="date.confirmation"
								ondblclick="callShowDiv(this);"><%=label.get("date.confirmation")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.dateOfConf"
								onclick="AddItem('date.confirmation',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="date.leaving" id="date.leaving"
								ondblclick="callShowDiv(this);"><%=label.get("date.leaving")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.dateOfSepFlag"
								onclick="AddItem('date.leaving',this);" /></td>
						</tr>
						<!-- SERVICE TENURE,EMP STATUS,REPORTING TO,SALARY GRADE -->
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="service.tenure" id="service.tenure"
								ondblclick="callShowDiv(this);"><%=label.get("service.tenure")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.yearexperienceFlag"
								onclick="AddItem('service.tenure',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="employee.status" id="employee.status1"
								ondblclick="callShowDiv(this);"><%=label.get("employee.status")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.empStatusFlag"
								onclick="AddItem('employee.status1',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="reporting.to" id="reporting.to1"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.reportingToFlag"
								onclick="AddItem('reporting.to1',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="salGrade" id="salGrade" ondblclick="callShowDiv(this);"><%=label.get("salGrade")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.gradeFlage" onclick="AddItem('salGrade',this);" /></td>

						</tr>


						<!-- GROUP JOINING DATE -->
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="group.joindate" id="group.joindate"
								ondblclick="callShowDiv(this);"><%=label.get("group.joindate")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.groupjoindateFlag"
								onclick="AddItem('group.joindate',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="role_acc" id="role_acc"
								ondblclick="callShowDiv(this);"><%=label.get("role_acc")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.roleFlag"
								onclick="AddItem('role_acc',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="trade" id="trade"
								ondblclick="callShowDiv(this);"><%=label.get("trade")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.tradeFlag"
								onclick="AddItem('trade',this);" /></td>
							<td align="right" colspan="1"></td>
							<td colspan="1"></td>

						</tr>
						 <tr>
							<td colspan="8">
							<hr />
							</td>
						</tr>
						
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="gender1" id="gender1" ondblclick="callShowDiv(this);"><%=label.get("gender1")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.genderFlag" onclick="AddItem('gender1',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="mobile.no" id="mobile.no" ondblclick="callShowDiv(this);"><%=label.get("mobile.no")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.mobFlag" onclick="AddItem('mobile.no',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="email.id11" id="email.id11" ondblclick="callShowDiv(this);"><%=label.get("email.id11")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.emailFlag" onclick="AddItem('email.id11',this);" /></td>
							<td align="right" colspan="1"><label class="set" name="age"
								id="age" ondblclick="callShowDiv(this);"><%=label.get("age")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.ageFlag" onclick="AddItem('age',this);" /></td>
						</tr>

					 
						<tr>
							<td colspan="8">
							<hr />
							</td>
						</tr>
					<!-- PF NO,PAN NO,ESIC NO,PAY MODE -->
				
					
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="pf.number" id="pf.number" ondblclick="callShowDiv(this);"><%=label.get("pf.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.pfNoFlag" onclick="AddItem('pf.number',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="pan.number" id="pan.number"
								ondblclick="callShowDiv(this);"><%=label.get("pan.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.panNoFlag" onclick="AddItem('pan.number',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="esic.number" id="esic.number"
								ondblclick="callShowDiv(this);"><%=label.get("esic.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.esicNoFlag"
								onclick="AddItem('esic.number',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="pay.mode" id="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.payModeFlag" onclick="AddItem('pay.mode',this);" /></td>
						</tr> 
						<!-- SALARY A/C,SALARY BANK,REIMBURSEMENT A/C,REIMBURSEMENT BANK -->
						 <tr>
							<td align="right" colspan="1"><label class="set"
								name="salary.acc" id="salary.acc"
								ondblclick="callShowDiv(this);"><%=label.get("salary.acc")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.salAccFlag"
								onclick="AddItem('salary.acc',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="salary.bank" id="salary.bank"
								ondblclick="callShowDiv(this);"><%=label.get("salary.bank")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.salBankFlag"
								onclick="AddItem('salary.bank',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="reimbursement.acc" id="reimbursement.acc"
								ondblclick="callShowDiv(this);"><%=label.get("reimbursement.acc")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.reimbAccFlag"
								onclick="AddItem('reimbursement.acc',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="reimbursement.bank" id="reimbursement.bank"
								ondblclick="callShowDiv(this);"><%=label.get("reimbursement.bank")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.reimbBankFlag"
								onclick="AddItem('reimbursement.bank',this);" /></td>
						</tr>
						<!-- SALARY,CTC,ACOOUNT CATEGORY,COST CENTER -->
						
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="salary" id="salary" ondblclick="callShowDiv(this);"><%=label.get("salary")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.salaryFlag" onclick="AddItem('salary',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="ctc.flag" id="ctc.flag" ondblclick="callShowDiv(this);"><%=label.get("ctc.flag")%></label>
							:</td>
							<td><s:checkbox theme="simple" name="misreport.ctcFlag"
								onclick="AddItem('ctc.flag',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="accounting.category" id="accounting.category"
								ondblclick="callShowDiv(this);"><%=label.get("accounting.category")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.accCategoryFlag"
								onclick="AddItem('accounting.category',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="cost.center" id="cost.center"
								ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.costCenterFlag"
								onclick="AddItem('cost.center',this);" /></td>
						</tr>
						
						<!-- SUBCOST CENTER -->
					
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="subcost.center" id="subcost.center"
								ondblclick="callShowDiv(this);"><%=label.get("subcost.center")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.subCostCenterFlag"
								onclick="AddItem('subcost.center',this);" /></td>
								 
							<td align="right" colspan="1"><label class="set"
								name="customerRefNo" id="customerRefNo"
								ondblclick="callShowDiv(this);"><%=label.get("customerRefNo")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.customerRefNoFlag"
								onclick="AddItem('customerRefNo',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="payBill.name1" id="payBill.name1"
								ondblclick="callShowDiv(this);"><%=label.get("payBill.name1")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.paybillFlag"
								onclick="AddItem('payBill.name1',this);" /></td> 								
							<!--<td align="right" colspan="1"><label class="set"
								name="debit" id="debit"
								ondblclick="callShowDiv(this);"><%=label.get("debit")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.debitFlag"
								onclick="AddItem('debit',this);" /></td>-->
						</tr>
						<tr>
							<td align="right" colspan="1"><label class="set"
								name="gratuity" id="gratuity"
								ondblclick="callShowDiv(this);"><%=label.get("gratuity")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="gratuityIDFlag"
								onclick="AddItem('gratuity',this);" /></td>								 
							<td align="right" colspan="1"><label class="set"
								name="pensionAcc" id="pensionAcc"
								ondblclick="callShowDiv(this);"><%=label.get("pensionAcc")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="pensionAccNoFlag"
								onclick="AddItem('pensionAcc',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="pensionBank" id="pensionBank"
								ondblclick="callShowDiv(this);"><%=label.get("pensionBank")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="pensionBankFlag"
								onclick="AddItem('pensionBank',this);" /></td> 
						</tr>					
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV SECOND ENDS - COLUMN DEFINITIONS --> <!-- DIV THIRD BEGINS - SORTING OPTIONS -->
			<div id="third">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="505">
					<table width="100%" border="0" cellpadding="0" cellspacing="3"
						class="formbg">
						<tr>
							<td class="text_head" colspan="5"><strong
								class="forminnerhead">Select appropriate sorting option
							for report generation</strong></td>
						</tr>
						<!-- SORT BY STARTS -->
						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="sortByLabel" name="sortByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label>
							:</td>
							<td colspan="4"><select name="sortBy" id="sortBy"
								style="width: 200" onchange="callSortBy();">
								  <option value="1">--Select--</option>
								<option value="Employee Code">Employee Code</option>
							</select> <s:hidden name="hiddenSortBy" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder('A');" name="sortByOrder"
								<s:property value="sortByAsc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder('D');" name="sortByOrder"
								<s:property value="sortByDsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>
						<!-- SORT BY ENDS -->
						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="thenByLabel" name="thenByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
							:</td>
							<td colspan="4"><select name="thenBy1" id="thenBy1"
								style="width: 200" onchange="callThenBy1();">
								<option value="1">--Select--</option>
								<option value="Employee Code">Employee Code</option>
							</select> <s:hidden name="hiddenThenBy1" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder1('A');" name="thenByOrder1"
								<s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder1('D');" name="thenByOrder1"
								<s:property value="thenByOrder1Dsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="thenByLabel" name="thenByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
							:</td>
							<td colspan="4"><select name="thenBy2" id="thenBy2"
								style="width: 200" onchange="callThenBy2();">
								<option value="1">--Select--</option>
								<option value="Employee Code">Employee Code</option>
							</select> <s:hidden name="hiddenThenBy2" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder2Asc" />
							<s:hidden name="thenByOrder2Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder2('A');" name="thenByOrder2"
								<s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder2('D');" name="thenByOrder2"
								<s:property value="thenByOrder2Dsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>
					</table>
					</td>

					<td width="397" valign="top">
					<table width="366" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td class="text_head" colspan="5"><strong
								class="forminnerhead">Select appropriate order of
							columns for report generation</strong></td>
						</tr>

						<tr>
							<td width="123"><select id="columnOrdering" size="10"
								name="columnOrdering" multiple="true">

							</select> <s:textfield name="hiddenColumns" /></td>
							<td width="242"><input type="button" class="shuffleUp"
								onclick="listbox_move('columnOrdering', 'up');" /> <br />
							<br />
							<input type="button" class="shuffleDown"
								onclick="listbox_move('columnOrdering', 'down');" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV THIRD ENDS - SORTING OPTIONS --> <!-- DIV FOURTH BEGINS - ADVANCE FILTERS -->
			<div id="fourth">
			<table cellspacing="0" cellpadding="0" class="formbg" width="100%"
				style="border-collapse: collapse;" border="0">

				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate date format
					for report generation</label> </strong></td>
				</tr>
				<tr>
					<td colspan="7"><s:select theme="simple"
						name="misreport.dateFormatSelect"
						value="%{misreport.dateFormatSelect}" cssStyle="width: 120px"
						list="#{'':'--Select--','DD/MM':'DD/MM','DD/MM/YY':'DD/MM/YY','DD/MM/YYYY':'DD/MM/YYYY','MON/YY':'MON/YY','MONTH/YY':'MONTH/YY','DD/MON/YYYY':'DD/MON/YYYY','DD/MONTH/YYYY':'DD/MONTH/YYYY','DD-MM':'DD-MM','DD-MM-YY':'DD-MM-YY','DD-MM-YYYY':'DD-MM-YYYY','MON-YY':'MON-YY','DD-MON-YYYY':'DD-MON-YYYY','MONTH-YY':'MONTH-YY','DD-MONTH-YYYY':'DD-MONTH-YYYY','MON-YY':'MON-YY','MON DD,YYYY':'MON DD,YYYY','MONTH DD,YYYY':'MONTH DD,YYYY','DDth MON':'DDth MON','DDth MONTH':'DDth MONTH','DDth MON YYYY':'DDth MON YYYY','DDth MONTH YYYY':'DDth MONTH YYYY'}" /></td>
				</tr>
				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" height="22"><label
						id="TotalvacancyLabel" ondblclick="callShowDiv(this);"
						name="TotalvacancyLabel">Age </label> :</td>
					<td width="80" colspan="1" height="22" align="left"><s:select
						theme="simple" name="ageSelect" value="%{ageSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}"
						onchange="callBetween(this,'1');" /></td>
					<td colspan="1" height="22"><s:textfield name="ageFrom"
						size="9" onkeypress="return numbersOnly();" maxlength="2" /></td>
					<td colspan="1" height="22">Yrs</td>
					<td id="betweenOn1" colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td colspan="1" height="22" width="74" align="center"
								id="toDivAnd"><strong>AND </strong></td>
							<td colspan="1" height="22" width="47" id="toDivAndText"><s:textfield
								name="ageTo" size="9" onkeypress="return numbersOnly();"
								maxlength="2" /></td>
							<td width="500" height="22" colspan="1" id="toDivAndYrs">Yrs
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<!--  
				<tr>
					<td colspan="1" height="22"><label id="TotalvacancyLabel"
						ondblclick="callShowDiv(this);" name="TotalvacancyLabel">Gross
					Salary </label> :</td>
					<td colspan="1" height="22"><s:select theme="simple"
						name="grossSalSelect" value="%{grossSalSelect}"
						cssStyle="width: 76px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}"
						onchange="callBetween(this,'2');" /></td>
					<td colspan="1" height="22"><s:textfield name="grossSalFrom"
						size="9" onkeypress="return numbersOnly();" maxlength="10" /></td>
					<td colspan="1" height="22">Rs</td>
					<td id="betweenOn2" colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td colspan="1" height="22" width="74" align="center"
								id="toDivAnd"><strong>AND </strong></td>
							<td colspan="1" height="22" width="47" id="toDivAndText"><s:textfield
								name="grossSalTo" size="9" onkeypress="return numbersOnly();"
								maxlength="10" /></td>
							<td width="500" height="22" colspan="1" id="toDivAndYrs">Rs</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="1" height="22"><label id="TotalvacancyLabel"
						ondblclick="callShowDiv(this);" name="TotalvacancyLabel">CTC
					</label> :</td>
					<td colspan="1" height="22"><s:select theme="simple"
						name="ctcSelect" value="%{ctcSelect}" cssStyle="width: 76px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}"
						onchange="callBetween(this,'3');" /></td>
					<td colspan="1" height="22"><s:textfield name="ctcFrom"
						size="9" onkeypress="return numbersOnly();" maxlength="10" /></td>
					<td colspan="1" height="22">Lacs</td>
					<td id="betweenOn3" colspan="3">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td colspan="1" height="22" width="74" align="center"
								id="toDivAnd"><strong>AND </strong></td>
							<td colspan="1" height="22" width="47" id="toDivAndText"><s:textfield
								name="ctcTo" size="9" onkeypress="return numbersOnly();"
								maxlength="10" /></td>
							<td width="500" height="22" colspan="1" id="toDivAndYrs">
							Lacs</td>
						</tr>
					</table>
					</td>
				</tr>
				-->

				<tr>
					<td colspan="1" height="22"><label class="set"
						name="date.birth" id="date.birth1" ondblclick="callShowDiv(this);"><%=label.get("date.birth")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="dobSelect" value="%{dobSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'1','DOB');" /></td>
					<td width="47" colspan="1" height="22"><s:textfield
						name="misreport.DOBfromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_misreport_DOBfromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_misreport_DOBfromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="90" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_misreport_DOBfromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
					 
						<tr>
							<td width="69" id="toDateDiv1" align="center"><strong>AND 
							</strong></td>
							<td width="52" id="toDateDivLabel1" height="22"><s:textfield
								name="misreport.DOBToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_misreport_DOBToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_misreport_DOBToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker1"><s:a
								href="javascript:NewCal('paraFrm_misreport_DOBToDate','DDMMYYYY');">
								<img class="iconImage"  onclick="displayDate('paraFrm_misreport_DOBToDate');"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="1" height="22"><label class="set"
						name="date.joining" id="date.joining1"
						ondblclick="callShowDiv(this);"><%=label.get("date.joining")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="dojSelect" value="%{dojSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'2','regular');" /></td>
					<td width="47" colspan="1" height="22"><s:textfield size="9"
						name="misreport.regularFromDate"
						onkeypress="return numbersWithHiphen();"
						onblur="setText('paraFrm_misreport_regularFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_misreport_regularFromDate','dd-mm-yyyy')"
						maxlength="10" /> <!--  
					<input
						onkeypress="return numbersWithHiphen();" id="frmDate2"
						onblur="setText('frmDate2','dd-mm-yyyy')"
						onfocus="clearText('frmDate2','dd-mm-yyyy')" maxlength="10"
						size="9" value="dd-mm-yyyy" name="frmDate2" />--></td>
					<td width="90" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_misreport_regularFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv2" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel2" height="22"><s:textfield
								size="9" name="misreport.regularToDate"
								onkeypress="return numbersWithHiphen();"
								onblur="setText('paraFrm_misreport_regularToDate','dd-mm-yyyy')"
								maxlength="10"
								onfocus="clearText('paraFrm_misreport_regularToDate','dd-mm-yyyy')" />

							<!--  
							<input
								type="text" size="10" id="paraFrm_toDate2"
								onblur="setText('paraFrm_toDate2','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_toDate2','dd-mm-yyyy')" />--></td>
							<td width="449" id="toDatePicker2"><s:a
								href="javascript:NewCal('paraFrm_misreport_regularToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16" onclick="displayDate('paraFrm_misreport_regularToDate');"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="1" height="22"><label class="set"
						name="date.confirmation" id="date.confirmation1"
						ondblclick="callShowDiv(this);"><%=label.get("date.confirmation")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="docSelect" value="%{docSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'3','conf');" /></td>
					<td width="47" colspan="1" height="22"><s:textfield size="9"
						onkeypress="return numbersWithHiphen();"
						name="misreport.confFromDate"
						onblur="setText('paraFrm_misreport_confFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_misreport_confFromDate','dd-mm-yyyy')"
						maxlength="10" /></td>
					<td width="90" colspan="1"><s:a
						href="javascript:NewCal('paraFrm_misreport_confFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv3" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel3" height="22"><s:textfield
								size="9" onkeypress="return numbersWithHiphen();"
								name="misreport.confToDate"
								onblur="setText('paraFrm_misreport_confToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_misreport_confToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td width="449" id="toDatePicker3"><s:a
								href="javascript:NewCal('paraFrm_misreport_confToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16" onclick="displayDate('paraFrm_misreport_confToDate');"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="1" height="22"><label class="set"
						name="date.leaving" id="date.leaving1"
						ondblclick="callShowDiv(this);"><%=label.get("date.leaving")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="dolSelect" value="%{dolSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'4','leave' );" /></td>
					<td width="47" colspan="1" height="22"><s:textfield size="9"
						onkeypress="return numbersWithHiphen();"
						name="misreport.leaveFromDate"
						onblur="setText('paraFrm_misreport_leaveFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_misreport_leaveFromDate','dd-mm-yyyy')"
						maxlength="10" /></td>
					<td width="90" colspan="1"><s:a
						href="javascript:NewCal('paraFrm_misreport_leaveFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv4" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel4" height="22"><s:textfield
								size="9" onkeypress="return numbersWithHiphen();"
								name="misreport.leaveToDate"
								onblur="setText('paraFrm_misreport_leaveToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_misreport_leaveToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td width="449" id="toDatePicker4"><s:a
								href="javascript:NewCal('paraFrm_misreport_leaveToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16" onclick="displayDate('paraFrm_misreport_leaveToDate');"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>


				<!-- GROUP JOINING DATE ADDED BY VISHWAMBHAR -->

				<tr>
					<td colspan="1" height="22"><label class="set"
						name="group.joindate" id="group.joindate1"
						ondblclick="callShowDiv(this);"><%=label.get("group.joindate")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="groupjoindateSelect"
						value="%{groupjoindateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'5','groupjoin' );" /></td>
					<td width="47" colspan="1" height="22"><s:textfield size="9"
						onkeypress="return numbersWithHiphen();"
						name="misreport.groupjoinFromDate"
						onblur="setText('paraFrm_misreport_groupjoinFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_misreport_groupjoinFromDate','dd-mm-yyyy')"
						maxlength="10" /></td>
					<td width="90" colspan="1"><s:a
						href="javascript:NewCal('paraFrm_misreport_groupjoinFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv5" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel5" height="22"><s:textfield
								size="9" onkeypress="return numbersWithHiphen();"
								name="misreport.groupjoinToDate"
								onblur="setText('paraFrm_misreport_groupjoinToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_misreport_groupjoinToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td width="449" id="toDatePicker5"><s:a
								href="javascript:NewCal('paraFrm_misreport_groupjoinToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16" onclick="displayDate('paraFrm_misreport_groupjoinToDate');"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>





			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS --> <!-- DIV FIFTH BEGINS - COMMON FIELDS FOR ALL DIVs -->
			<div id="fifth">
			<table cellpadding="0" cellspacing="0" border="0" class="formbg"
				width="100%">
				<tr>
					<td colspan="4" width="100%">Report Layout</td>
				</tr>
			</table>
			</div>
			<!-- DIV FIFTH ENDS - COMMON FIELDS FOR ALL DIVs --></td>
		</tr>

		<!-- SHOW DISPLAY OPTIONS -->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="2" class="formtext"><b>Display
					option</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="formtext"><s:hidden
						name="hidReportView" /> 
						<s:hidden name="hidReportRadio" /> 
						<input	type="radio" value="V" name="reportView" id="reportViewV"
						<s:property value="hidReportView"/> onclick="callReportChk('N');">
					View On Screen</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext"><input
						type="radio" value="R" name="reportView" id="reportViewR"
						<s:property value="hidReportRadio"/> onclick="callReportChk('Y');">
					Report Type</td>
					<td width="83%" colspan="1" class="formtext">
					<div id="reportTypeDiv"><s:select headerKey="1"
						headerValue="--Select--" name="reportType"
						list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="1" class="formtext"><label
						class="set" id="report.criteria" name="report.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("report.criteria")%></label>
					:</td>
					<td width="83%" colspan="1"><s:textfield name="settingName"
						size="25" theme="simple" maxlength="40" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- DISPLAY OPTIONS ENDS -->

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name="reqStatus" />
			<td width="100%"><input type="button" class="token"
				theme="simple" value="  Generate Report"
				onclick=" return generateReport();" /> <input type="button"
				class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
			<input name="button" type="button" value="Save report criteria"
				class="save" onclick="saveReport()" /></td>

		</tr>
		<!-- BUTTON PANEL ENDS -->
		<s:hidden name="reportTypeStr"></s:hidden>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
function dateDifferenceEqual(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert("to "+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to  from "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

	function displayDate(dateStr)
	{
		clearText(dateStr,'dd-mm-yyyy');
	}
	var misCounter=0;
	onloadCall();
	function onloadCall(){
		document.getElementById('first').style.display='block';
		document.getElementById('second').style.display='none';
		document.getElementById('third').style.display='none';
		document.getElementById('fourth').style.display='none';
		document.getElementById('menuid1').className='on';
		document.getElementById('menuid2').className='li';
		document.getElementById('menuid3').className='li';
		document.getElementById('menuid4').className='li';
		document.getElementById('reportTypeDiv').style.display='none';  
		callAllDisable();
		//document.getElementById('betweenOn').style.display='none'; 
		setFieldsOnload();
		
	}
	
	 function callReportChk(status)
	 { 
		 if(status=="Y"){
		   document.getElementById('reportTypeDiv').style.display=''; 
		   document.getElementById('paraFrm_reqStatus').value ="R"; 
		  }else{
		   document.getElementById('reportTypeDiv').style.display='none';  
		   document.getElementById('paraFrm_reqStatus').value ="V"; 
		  }
	 }
 
	function showCurrent(menuId, id){
		document.getElementById('first').style.display='none';
		document.getElementById('second').style.display='none';
		document.getElementById('third').style.display='none';
		document.getElementById('fourth').style.display='none';
		document.getElementById('menuid1').className='li';
		document.getElementById('menuid2').className='li';
		document.getElementById('menuid3').className='li';
		document.getElementById('menuid4').className='li';
		document.getElementById(menuId).className='on';
		document.getElementById(id).style.display='block';
	}

	function callView(){	
		window.open("FormulaCalculator.html","show","height=400,width=400,status=yes,toolbar=no,menubar=no,location=no");
	}

	function callView1(){	
		window.open("LeaveName.html","show","height=400,width=400,status=yes,toolbar=no,menubar=no,location=no");
	}

	function callToDateDispOnClick(obj,idNo,idName)
 	{
   		try{
   			var dateFilter= obj.value;
 			// alert(dateFilter);
   			document.getElementById('paraFrm_misreport_'+idName+'ToDate').value="";  
   			if(dateFilter=="BN"){
     			document.getElementById('toDateDiv'+idNo).style.display=''; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='';  
	  			document.getElementById('toDatePicker'+idNo).style.display='';
 
      			setText('paraFrm_misreport_'+idName+'ToDate',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_misreport_'+idName+'ToDate',"dd-mm-yyyy");
     			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='none';     
  				document.getElementById('toDatePicker'+idNo).style.display='none';  
   			} 
   		}catch(e){
   			alert(e);
   		}
 	}
 
	function callBetween(obj,idNo)
	{
		var dateFilter= obj.value;
   		if(dateFilter=="BN"){
			document.getElementById('betweenOn'+idNo).style.display=''; 
		     //document.getElementById('toDivAnd').style.display=''; 
		     //document.getElementById('toDivAndText').style.display='';  
			 // document.getElementById('toDivAndYrs').style.display='';
	 
  		}else {
  			document.getElementById('betweenOn'+idNo).style.display='none'; 
    		// document.getElementById('toDateDiv').style.display='none'; 
     		//document.getElementById('toDivAndText').style.display='none';     
			// document.getElementById('toDivAndYrs').style.display='none';  
   		} 
 	}
 
 	function callAllDisable(){
  		for(idNo=1;idNo <=5;idNo++){ 
   			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     		document.getElementById('toDateDivLabel'+idNo).style.display='none';     
	  		document.getElementById('toDatePicker'+idNo).style.display='none';  
	  	}
	   /*for(idNo=1;idNo <=3;idNo++){ 
   			document.getElementById('betweenOn'+idNo).style.display='none'; 
	  	}*/
	  	
	  	document.getElementById('betweenOn1').style.display='none';
	  	
	  	
	  	//alert(document.getElementById("paraFrm_dobSelect").value);
	  	if(document.getElementById("paraFrm_ageSelect").value=="BN"){
	  		document.getElementById('betweenOn1').style.display=''; 
	  	}
	  	if(document.getElementById("paraFrm_dobSelect").value=="BN"){
	  		document.getElementById('toDateDiv1').style.display=''; 
   			document.getElementById('toDateDivLabel1').style.display='';  
  			document.getElementById('toDatePicker1').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_dojSelect").value=="BN"){
	  		document.getElementById('toDateDiv2').style.display=''; 
   			document.getElementById('toDateDivLabel2').style.display='';  
  			document.getElementById('toDatePicker2').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_docSelect").value=="BN"){
	  		document.getElementById('toDateDiv3').style.display=''; 
   			document.getElementById('toDateDivLabel3').style.display='';  
  			document.getElementById('toDatePicker3').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_dolSelect").value=="BN"){
	  		document.getElementById('toDateDiv4').style.display=''; 
   			document.getElementById('toDateDivLabel4').style.display='';  
  			document.getElementById('toDatePicker4').style.display='';
	  	}
	  	
	  		if(document.getElementById("paraFrm_groupjoindateSelect").value=="BN"){
	  		document.getElementById('toDateDiv5').style.display=''; 
   			document.getElementById('toDateDivLabel5').style.display='';  
  			document.getElementById('toDatePicker5').style.display='';
	  	}
	  	
	  	
 	}
 
 
	function listbox_move(listID, direction) {
		try{
			var listbox = document.getElementById(listID);
			var selIndex = listbox.selectedIndex;

			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}
			
			//alert(selIndex);

			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;

			/*if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
				alert('in if');
				return;
			}*/
			if((selIndex + increment) < 0){
				alert("You cannot move up the record");
				return;
			}
			if((selIndex + increment) > (listbox.options.length-1)){
				alert("You cannot move down the record");
				return;
			}

			var selValue = listbox.options[selIndex].value;
			var selText = listbox.options[selIndex].text;
			listbox.options[selIndex].value = listbox.options[selIndex + increment].value
			listbox.options[selIndex].text = listbox.options[selIndex + increment].text

			listbox.options[selIndex + increment].value = selValue;
			listbox.options[selIndex + increment].text = selText;

			listbox.selectedIndex = selIndex + increment;
			
        	var finalValue="";
        	var count=1;
        	for(var i=0;i<listbox.options.length;i++){
        		var opt1 = listbox.options[i];
        		//alert('Values in listbox : '+opt1.text);
        		finalValue+=count+"-"+opt1.text+",";
        		count++;
        	}
			document.getElementById('paraFrm_hiddenColumns').value=finalValue;
			}catch(e){
				alert(e);
			}
		}


	function generateReport()
	{
		try{
			if(!document.getElementById("reportViewV").checked && !document.getElementById("reportViewR").checked){
				alert("Please select any display option");
				return false;
			}
			
			if(document.getElementById("reportViewR").checked){
				if(document.getElementById('paraFrm_reportType').value=="1"){
					alert("Please select Report Type");
					return false;
				}
			}
			
			var jfrmDate=document.getElementById('paraFrm_misreport_regularFromDate').value;
			var jtoDate=document.getElementById('paraFrm_misreport_regularToDate').value;
			var lfrmDate=document.getElementById('paraFrm_misreport_leaveFromDate').value;
			var lToDate=document.getElementById('paraFrm_misreport_leaveToDate').value;
			var toDate=document.getElementById('paraFrm_misreport_DOBToDate').value;
			var frmDate = document.getElementById('paraFrm_misreport_DOBfromDate').value;
			var confFrmDate = document.getElementById('paraFrm_misreport_confFromDate').value;
			var confToDate = document.getElementById('paraFrm_misreport_confToDate').value;
			
			var groupjoinFromDate = document.getElementById('paraFrm_misreport_groupjoinFromDate').value;
			var groupjoinToDate = document.getElementById('paraFrm_misreport_groupjoinToDate').value;
			
			

 
			try{
			//alert(document.getElementById("paraFrm_ageSelect").value);
			if(document.getElementById("paraFrm_ageSelect").value!=""){
				//alert(document.getElementById("paraFrm_ageFrom").value);
				if(document.getElementById("paraFrm_ageFrom").value==""){
					alert("Please enter Age");
					//document.getElementById('paraFrm_ageFrom').focus();
					return false;
				}
				if(document.getElementById("paraFrm_ageSelect").value=="BN"){
					if(document.getElementById("paraFrm_ageTo").value==""){
						alert("Please enter Age");
						//document.getElementById('paraFrm_ageTo').focus();
						return false;
					}
					if((document.getElementById("paraFrm_ageFrom").value!="")&&(document.getElementById("paraFrm_ageTo").value!=""))
					{
						if(eval(document.getElementById("paraFrm_ageTo").value)<eval(document.getElementById("paraFrm_ageFrom").value))
						{
							alert("From Age should be equal to or less than To Age");
							return false;
						}
					}
				}
			}
			
			if(document.getElementById("paraFrm_dobSelect").value!=""){
				//alert(document.getElementById("paraFrm_frmDate").value);
				if(frmDate=="" || frmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('date.birth1').innerHTML);
					//document.getElementById('paraFrm_misreport_DOBfromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_dobSelect").value=="BN"){
					if(toDate==""||toDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('date.birth1').innerHTML);
						//document.getElementById('paraFrm_misreport_DOBToDate').focus();
						return false;
					}
				}
				
				if(frmDate!=""||frmDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_DOBfromDate'];
				  	var lbl=['date.birth1'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(toDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_DOBToDate'];
			  		var lbl=['date.birth1'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			   	
			   	if(document.getElementById("paraFrm_dobSelect").value=="BN"){
					if((frmDate!=""||frmDate!="dd-mm-yyyy" ) && (toDate!=""||toDate!="dd-mm-yyyy") ){
						if(!dateDifferenceEqual(frmDate,toDate,'misreport.DOBToDate','date.birth1','date.birth1'))
			 			{
			 				return false;
			 			}
					}
				}
			}
			
			if(document.getElementById("paraFrm_dojSelect").value!=""){
				if(jfrmDate=="" || jfrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('date.joining1').innerHTML);
					//document.getElementById('paraFrm_misreport_regularFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_dojSelect").value=="BN"){
					if(jtoDate==""||jtoDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('date.joining1').innerHTML);
						//document.getElementById('paraFrm_misreport_regularToDate').focus();
						return false;
					}
				}
				if(jfrmDate!="" ||jfrmDate!="dd-mm-yyyy"){
			  		var fld=['paraFrm_misreport_regularFromDate'];
			  		var lbl=['date.joining1'];
		   			var chkjfrmDate=validateDate(fld,lbl);
		   			if(!chkjfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(jtoDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_regularToDate'];
			  		var lbl=['date.joining1'];
		   			var chkjtoDate=validateDate(fld,lbl);
		   			if(!chkjtoDate) {
			    		return false;
			   		}
	  			}
	  			if(document.getElementById("paraFrm_dojSelect").value=="BN"){
					if((jfrmDate!=""||jfrmDate!="dd-mm-yyyy" ) && (jtoDate!=""||jtoDate!="dd-mm-yyyy") ){
						if(!dateDifferenceEqual(jfrmDate,jtoDate,'misreport.regularToDate','date.joining1','date.joining1'))
			 			{
			 				return false;
			 			}
					}
				}
			}
			
			if(document.getElementById("paraFrm_docSelect").value!=""){
				if(confFrmDate=="" || confFrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('date.confirmation1').innerHTML);
					//document.getElementById('paraFrm_misreport_confFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_docSelect").value=="BN"){
					if(confToDate==""||confToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('date.confirmation1').innerHTML);
						//document.getElementById('paraFrm_misreport_confToDate').focus();
						return false;
					}
				}
				if(confFrmDate!="" ||confFrmDate!="dd-mm-yyyy"){
				  	var fld=['paraFrm_misreport_confFromDate'];
				  	var lbl=['date.confirmation1'];
			   		var chkconfFrmDate=validateDate(fld,lbl);
			   		if(!chkconfFrmDate) {
				    	return false;
				   	}
	  			}
	  			if(confToDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_confToDate'];
				  	var lbl=['date.confirmation1'];
			   		var chkconfToDate=validateDate(fld,lbl);
			   		if(!chkconfToDate) {
				    	return false;
				   	}
	  			}
	  			if(document.getElementById("paraFrm_docSelect").value=="BN"){
					if((confFrmDate!=""||confFrmDate!="dd-mm-yyyy" ) && (confToDate!=""||confToDate!="dd-mm-yyyy") ){
						if(!dateDifferenceEqual(confFrmDate,confToDate,'misreport.confToDate','date.confirmation1','date.confirmation1'))
			 			{
			 				return false;
			 			}
					}
				}
	  			
			}
			
			if(document.getElementById("paraFrm_dolSelect").value!=""){
				if(lfrmDate=="" || lfrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('date.leaving1').innerHTML);
					//document.getElementById('paraFrm_misreport_leaveFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_dolSelect").value=="BN"){
					if(lToDate==""||lToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('date.leaving1').innerHTML);
						//document.getElementById('paraFrm_misreport_leaveToDate').focus();
						return false;
					}
				}
				if(lfrmDate!="" ||lfrmDate!="dd-mm-yyyy"){
			  		var fld=['paraFrm_misreport_leaveFromDate'];
			  		var lbl=['date.leaving1'];
		   			var chklfrmDate=validateDate(fld,lbl);
		   			if(!chklfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(lToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_leaveToDate'];
			  		var lbl=['date.leaving1'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
	  			}
	  			
	  			if(document.getElementById("paraFrm_dolSelect").value=="BN"){
					if((lfrmDate!=""||lfrmDate!="dd-mm-yyyy" ) && (lToDate!=""||lToDate!="dd-mm-yyyy") ){
						if(!dateDifferenceEqual(lfrmDate,lToDate,'misreport.leaveToDate','date.leaving1','date.leaving1'))
			 			{
			 				return false;
			 			}
					}
				}
			}
			
			
			
			
			if(document.getElementById("paraFrm_groupjoindateSelect").value!=""){
				if(groupjoinFromDate=="" || groupjoinFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('group.joindate1').innerHTML);
					//document.getElementById('paraFrm_misreport_confFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_groupjoindateSelect").value=="BN"){
					if(groupjoinToDate==""||groupjoinToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('group.joindate1').innerHTML);
						//document.getElementById('paraFrm_misreport_confToDate').focus();
						return false;
					}
				}
				if(groupjoinFromDate!="" ||groupjoinFromDate!="dd-mm-yyyy"){
				  	var fld=['paraFrm_misreport_groupjoinFromDate'];
				  	var lbl=['group.joindate1'];
			   		var chkconfFrmDate=validateDate(fld,lbl);
			   		if(!chkconfFrmDate) {
				    	return false;
				   	}
	  			}
	  			if(groupjoinToDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_groupjoinToDate'];
				  	var lbl=['group.joindate1'];
			   		var chkconfToDate=validateDate(fld,lbl);
			   		if(!chkconfToDate) {
				    	return false;
				   	}
	  			}
	  			
	  			if(document.getElementById("paraFrm_groupjoindateSelect").value=="BN"){
					if((groupjoinFromDate!=""||groupjoinFromDate!="dd-mm-yyyy" ) && (groupjoinToDate!=""||groupjoinToDate!="dd-mm-yyyy") ){
						if(!dateDifferenceEqual(groupjoinFromDate,groupjoinToDate,'misreport.groupjoinToDate','group.joindate1','group.joindate1'))
			 			{
			 				return false;
			 			}
					}
				}
			}
			
			
			
			
			
			}catch(e){
				alert("---------"+e);
			}
  			
  			// for sort the filter   
	   	  	var sortBy = document.getElementById('sortBy').value;
	   	  	var thenBy1 = document.getElementById('thenBy1').value;
	   	  	var thenBy2 = document.getElementById('thenBy2').value; 
	   	  
	   	  	if(sortBy!="1" &&thenBy1!="1"){
		   	    if(sortBy==thenBy1){ 
	   	      		//callTab('sortOpt','sort');
		   	     	alert("Duplicate sorting option."); 
		   	     	document.getElementById('thenBy1').focus();
		   	     	return false;
		   	    } 
	   	  	}
   	  
   	  		if(sortBy!="1" &&thenBy2!="1"){
   	    		if(sortBy==thenBy2){ 
   	      			//callTab('sortOpt','sort');
   	     			alert("Duplicate sorting option."); 
   	     			document.getElementById('thenBy2').focus();
   	     			return false;
   	    		} 
   	  		}
   	  
    		if(thenBy1!="1" &&thenBy2!="1"){
   	    		if(thenBy1==thenBy2){ 
   	      			//callTab('sortOpt','sort');
   	     			alert("Duplicate sorting option."); 
   	     			document.getElementById('thenBy2').focus();
   	     			return false;
   	    		} 
   	  		} 
   	  // == end of sort======
  			
  			
  			
  		}catch(e){
  			alert(e);
  		}
  		
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="accMISReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="accMISReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}
	
	function resetForm()
	{
	
		document.getElementById('paraFrm_misreport_reportId').value='';
		document.getElementById('paraFrm_misreport_reportTitle').value='';
		document.getElementById('paraFrm_settingName').value='';
		 
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="accMISReport_reset.action";
		document.getElementById('paraFrm').submit();
	
	}
	
	function saveReport(){
		var settingName = trim(document.getElementById('paraFrm_settingName').value);
		if(settingName == ""){
			alert("Please enter "+document.getElementById('report.criteria').innerHTML);
			document.getElementById('paraFrm_settingName').focus();
			return false;
		}
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="accMISReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	
	}


	function validNames(){
		var firstName = document.getElementById('paraFrm_misreport_firstName').value ;
		var lastName = document.getElementById('paraFrm_misreport_lastName').value ;
		
		if(!(firstName=="")){ 
			var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?";
			for (var i = 0; i < firstName.length; i++){		  	
	  			if (iChars.indexOf(firstName.charAt(i)) != -1){
		  			alert ("Please Enter Valid "+document.getElementById('first.name').innerHTML.toLowerCase());
		  			//document.getElementById('cadMaster.cadreName').value="";
	  				document.getElementById('paraFrm_misreport_firstName').focus();
		  			return false;				  	
			   }
			}
		}
		if(!(lastName=="")){ 
			var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?";
			for (var i = 0; i < lastName.length; i++){		  	
		  		if (iChars.indexOf(lastName.charAt(i)) != -1){
				  	alert ("Please Enter Valid "+document.getElementById('last.name').innerHTML.toLowerCase());
				  	//document.getElementById('cadMaster.cadreName').value="";
					document.getElementById('paraFrm_misreport_lastName').focus();
			  		return false;				  	
			   }
			}
		}
				
	 	if(!(firstName=="")){ 
			var count =0;
			var iChars =" ";
			for (var i = 0; i < firstName.length; i++){		
	  			if (iChars.indexOf(firstName.charAt(i))!= -1){
	  	 			count=count+1; 
		  		}
			}
			if(count==firstName.length){
				alert ("Blank Spaces Not Allowed");
				document.getElementById('paraFrm_misreport_firstName').value="";
				document.getElementById('paraFrm_misreport_firstName').focus();
				return false;	
			}
		}			
				
		if(!(lastName=="")){ 
			var count =0;
			var iChars =" ";
			for (var i = 0; i < lastName.length; i++){		
	  			if (iChars.indexOf(lastName.charAt(i))!= -1){
	  	 			count=count+1; 
	  			}
			}
			if(count==lastName.length){
				alert ("Blank Spaces Not Allowed");
				document.getElementById('paraFrm_misreport_lastName').value="";
				document.getElementById('paraFrm_misreport_lastName').focus();
				return false;	
			}
		}			
	}
	
	function AddItem(labelName,id)
    {
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
	        var checkedValue = id.checked;
	        //alert("checkedValue : "+checkedValue);
	        //alert(" labelNameCheck : "+labelNameCheck);
	        // Create an Option object                
	        var opt = document.createElement("option");
	        
	        var sortBy = document.getElementById("sortBy");
	        var option = document.createElement("option");
	        var thenBy1 = document.getElementById('thenBy1');
	        var option1 = document.createElement("option");
	   	  	var thenBy2 = document.getElementById('thenBy2');
	   	  	var option2 = document.createElement("option"); 
	        
	        if(checkedValue){
	        	//IF VALUE PRESENT INITIAL COUNTER SHOULD BE MAX KEY
	        	var checkValue=document.getElementById('paraFrm_hiddenColumns').value
	        	var backFlag=document.getElementById('paraFrm_backFlag').value
	        	//alert("backFlag .. "+backFlag);
	        	if(checkValue!="" && backFlag=="true"){
	        		checkValue=checkValue.substr(0,checkValue.length-1);
	        		var splitComma=checkValue.split(",");
	        		var splitHiphen = "";
	        		for(var i=0;i<splitComma.length;i++){
	        			splitHiphen = splitComma[i].split("-");
	        		}
	        		//alert(splitHiphen[0]);
	        		misCounter = splitHiphen[0];
	        	}
	        
	        
	        	misCounter++;
		        // Assign text and value to Option object
		        opt.text = labelNameCheck;
		        opt.value = labelNameCheck;
		        
		        option.text = labelNameCheck;
		        option.value = labelNameCheck;
		        
		        option1.text = labelNameCheck;
		        option1.value = labelNameCheck;
		        
		        option2.text = labelNameCheck;
		        option2.value = labelNameCheck;
		        
		        // Add an Option object to Drop Down/List Box
		        document.getElementById("columnOrdering").options.add(opt); 
		        //alert("opt : "+opt);
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					//alert('1');
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
		        
		        var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value+
		        			misCounter+"-"+labelNameCheck+",";
		        	
		        //alert("	hiddenValue : "+hiddenValue);			
		        document.getElementById('paraFrm_hiddenColumns').value=hiddenValue;
		        
		        
	        }else{
	        	var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value;
	        	hiddenValue=hiddenValue.substr(0,hiddenValue.length-1);
	        	var splittedValue=hiddenValue.split(",");
	        	var finalValue="";
	        	var count=1;
	        	for(var i=0;i<splittedValue.length;i++){
	        		var splitDash = splittedValue[i].split("-");
	        	
	        		if(labelNameCheck!=splitDash[1]){
	        			finalValue+=count+"-"+splitDash[1]+",";
	        			count++;
	        		}else{
	        			for(var j = 0; j < document.getElementById("columnOrdering").childNodes.length; j++) {
	        				var opt1 = document.getElementById("columnOrdering").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("columnOrdering").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("sortBy").childNodes.length; j++) {
	        				var opt1 = document.getElementById("sortBy").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("sortBy").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy1").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy1").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy1").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy2").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy2").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy2").removeChild(opt1);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        	document.getElementById('paraFrm_hiddenColumns').value=finalValue;
	        }
	   }catch(e){
	   		alert(e);
	   }
    }
    
    
   
    
    function setFieldsOnload(){
    	try{
    	
    	var columnOrderValues = document.getElementById('paraFrm_hiddenColumns').value;
    	//alert(columnOrderValues);
    	if(columnOrderValues!=""){
	    	columnOrderValues=columnOrderValues.substr(0,columnOrderValues.length-1);
	       	var splittedValue=columnOrderValues.split(",");
	       	for(var i=0;i<splittedValue.length;i++){
	       		//alert(splittedValue[i]);
	       		var sortBy = document.getElementById("sortBy");
		        var option = document.createElement("option");
		        var thenBy1 = document.getElementById('thenBy1');
		        var option1 = document.createElement("option");
		   	  	var thenBy2 = document.getElementById('thenBy2');
		   	  	var option2 = document.createElement("option"); 
		   	  	var opt = document.createElement("option");
	   	  	
	       		var splitDash = splittedValue[i].split("-");
	       		
	       		// Assign text and value to Option object
		        opt.text = splitDash[1];
		        opt.value = splitDash[1];
		        
	       		option.text = splitDash[1];
		        option.value = splitDash[1];
		        
		        option1.text = splitDash[1];
		        option1.value = splitDash[1];
		        
		        option2.text = splitDash[1];
		        option2.value = splitDash[1];
		        
		        // Add an Option object to Drop Down/List Box
		       	document.getElementById("columnOrdering").options.add(opt); 
		        
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
	   		}
       	}
       	//SET SELECTED OPTION ON BACK AND SEARCH (SORTING OPTIONS)
       	var hidSortBy = document.getElementById('paraFrm_hiddenSortBy').value;
       	if(hidSortBy!=""){
	    	for (var i=0;i<document.getElementById('sortBy').length;i++){
	    		//alert(document.getElementById('sortBy').options(i).text);
				if (hidSortBy == document.getElementById('sortBy').options(i).text){
					document.getElementById('sortBy').options(i).selected = true;
					break;
				}
			}
		}
		var hidThenBy1 = document.getElementById('paraFrm_hiddenThenBy1').value;
		if(hidThenBy1!=""){
	    	for (var x=0;x<document.getElementById('thenBy1').length;x++){
				if (hidThenBy1 == document.getElementById('thenBy1').options(x).text){
					document.getElementById('thenBy1').options(x).selected = true;
					break;
				}
			}
		}
		var hidThenBy2 = document.getElementById('paraFrm_hiddenThenBy2').value;
		if(hidThenBy2!=""){
	    	for (var y=0;y<document.getElementById('thenBy2').length;y++){
				if (hidThenBy2 == document.getElementById('thenBy2').options(y).text){
					document.getElementById('thenBy2').options(y).selected = true;
					break;
				}
			}
		}
		
    	}catch(e){
    		//alert(e);
    	}
    }
    
    function callSortBy(){
    	var sortBy = document.getElementById('sortBy').value;
    	document.getElementById('paraFrm_hiddenSortBy').value=sortBy;
    	
    }
    function callThenBy1(){
    	var thenBy1 = document.getElementById('thenBy1').value;
   		document.getElementById('paraFrm_hiddenThenBy1').value=thenBy1;
    	
    }
    function callThenBy2(){
    	var thenBy2 = document.getElementById('thenBy2').value;
   		document.getElementById('paraFrm_hiddenThenBy2').value=thenBy2;
    	
    }
    
    function callOrder(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_sortByDsc').value="";
    		document.getElementById('paraFrm_sortByAsc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_sortByAsc').value="";
    		document.getElementById('paraFrm_sortByDsc').value="checked";
    	}
    }
    function callOrder1(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_thenByOrder1Dsc').value="";
    		document.getElementById('paraFrm_thenByOrder1Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_thenByOrder1Asc').value="";
    		document.getElementById('paraFrm_thenByOrder1Dsc').value="checked";
    	}
    }
    function callOrder2(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_thenByOrder2Dsc').value="";
    		document.getElementById('paraFrm_thenByOrder2Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_thenByOrder2Asc').value="";
    		document.getElementById('paraFrm_thenByOrder2Dsc').value="checked";
    	}
    }

</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>