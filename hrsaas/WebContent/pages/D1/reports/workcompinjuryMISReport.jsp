<!--@author Nilesh Dhandare on 18th May 11 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="WorkCompInjuryMISReport" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">
					Work Comp Injury Illness Mis Report</strong></td>
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
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> <input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
					<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> <input name="button"
						type="button" value="  Search" class="search"
						onclick="searchReport()" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->

		<!--  REPORT TITLE FIELDS -->
		<tr>
			<td colspan="4">
			<table border="0" cellpadding="1" cellspacing="1" class="formbg"
				width="100%">
				<tr>
					<td colspan="1" width="20%"><s:hidden name="savedReport" /> <s:hidden
						name="reportId" /><!-- Report ID --> <label class="set"
						name="report.title" id="report.title"
						ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label>:</td>
					<td colspan="3" width="80%"><s:textfield size="30"
						maxlength="30" name="reportTitle"
						onkeypress="return allCharacters();" /></td>
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
			<td colspan="4"><!-- DIV FIRST BEGINS - FILTERS -->
			<div id="first">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" class="text_head" width="100%"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>

				<!-- DIVISION & DEPT -->
				<tr>
					<td colspan="1" width="20%"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="divName" readonly="true" /><s:hidden name="divId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9division.action');" /></td>

					<td colspan="1" width="20%"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="deptName" readonly="true" /><s:hidden name="deptId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9department.action');" /></td>
				</tr>

				<!-- BRANCH & Designation -->
				<tr>
					<td colspan="1" width="20%"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="branchName" readonly="true" /><s:hidden name="branchId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9branch.action');" /></td>

					<td colspan="1" width="20%"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="desigName" readonly="true" /><s:hidden name="desigId" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9designation.action');" /></td>

				</tr>
				<!--Employee type & Manager Name -->
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="empTypeName" readonly="true" /><s:hidden name="empTypeId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9empType.action');" /></td>

                   <td colspan="1" width="20%"><label class="set" name="manager"
						id="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="managerName" readonly="true" /><s:hidden name="managerId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9manager.action');" /></td>

				</tr>

				<!-- Manager & Status -->
				<tr>
					<!--<td colspan="1" width="20%"><label class="set" name="manager"
						id="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="managerName" readonly="true" /><s:hidden name="managerId" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9manager.action');" /></td>

					--><!--<td colspan="1" width="20%"><label class="set" name="status"
						id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
					:</td>
					<td colspan="1" width="30%"><s:select name="empstatus"
						headerKey="1" theme="simple"
						headerValue="-----------------Select------------------"
						cssStyle="width:181"
						list="#{'S':'Service','R':'Retired','RS':'Resigned','T':'Terminated'}" />
					</td>
				--></tr>

				<!--Trackig No & Application Status -->

				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="req.tracking.no" id="req.tracking.no"
						ondblclick="callShowDiv(this);"><%=label.get("req.tracking.no")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						name="trackingNumber" readonly="true" /><s:hidden
						name="trackingId" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9trackingNo.action');" /></td>

					<td colspan="1" width="20%"><label class="set"
						name="appl.status" id="appl.status"
						ondblclick="callShowDiv(this);"><%=label.get("appl.status")%></label>
					:</td>
					<td colspan="1" width="30%"><s:select name="applStatus"
						headerKey="1" theme="simple"
						headerValue="-----------------Select------------------"
						cssStyle="width:165"
						list="#{'P':'Pending','A':'Approved','R':'Rejected','B':'Send Back'}" />
					</td>

				</tr>

				<tr>
					<td colspan="4" width="100%" align="center"><strong
						class="text_head">OR</strong></td>
				</tr>

				<!-- Employee Info -->
				<tr>
					<td colspan="1" width="20%"><label class="set" name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td colspan="3" width="80%" align="left"><s:textfield
						size="15" name="empToken" readonly="true" /><s:textfield
						size="65" name="empName" readonly="true" /><s:hidden name="empId" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'WorkCompInjuryMISReport_f9employee.action');" /></td>
					</td>

				</tr>
				<!-- Employee Info End-->


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
								name="empNameFlag" onclick="AddItem('name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="divFlag"
								onclick="AddItem('division',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="deptFlag"
								onclick="AddItem('department',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="branchFlag"
								onclick="AddItem('branch',this);" /></td>
						</tr>
						<!-- DESIGNATION,EMPLOYEE TYPE,APPLICATION DATE,ATTENDANCE DATE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="desigFlag"
								onclick="AddItem('designation',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="employee.type" id="employee.type"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="empTypeFlag" onclick="AddItem('employee.type',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="req.tracking.no" id="req.tracking.no"
								ondblclick="callShowDiv(this);"><%=label.get("req.tracking.no")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trackingNumberFlag"
								onclick="AddItem('req.tracking.no',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="manager" id="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="managerNameFlag" onclick="AddItem('manager',this);" /></td>
						</tr>
						<!-- City,Num of dependant,date of injury & time of injury -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="city.name" id="city.name" ondblclick="callShowDiv(this);"><%=label.get("city.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="cityFlag"
								onclick="AddItem('city.name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="num.dependant" id="num.dependant"
								ondblclick="callShowDiv(this);"><%=label.get("num.dependant")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="numberofDependantFlag"
								onclick="AddItem('num.dependant',this);" /></td>
							<!--<td width="20%" align="right" colspan="1"><label class="set"
								name="aaaaa" id="aaaaa"
								ondblclick="callShowDiv(this);"><%=label.get("aaaaa")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="dateofInjuryFlag" onclick="AddItem('aaaaa',this);" /></td>
							-->
							<td width="20%" align="right" colspan="1"><label class="set"
								name="aaaaa" id="aaaaa" ondblclick="callShowDiv(this);"><%=label.get("aaaaa")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="aaaaaFlag"
								onclick="AddItem('aaaaa',this);" /></td>


							<td width="20%" align="right" colspan="1"><label class="set"
								name="time.injury" id="time.injury"
								ondblclick="callShowDiv(this);"><%=label.get("time.injury")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="timeofInjuryFlag" onclick="AddItem('time.injury',this);" /></td>
						</tr>

						<!--Hours Worked Date Of Injury,Normal Working Hours From,Normal Working Hours To & Date Employer knew of Injury -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="injury.work.date" id="injury.work.date"
								ondblclick="callShowDiv(this);"><%=label.get("injury.work.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="hoursWorkedFlag"
								onclick="AddItem('injury.work.date',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="working.hours.from" id="working.hours.from"
								ondblclick="callShowDiv(this);"><%=label.get("working.hours.from")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="normalworkingHoursFlag"
								onclick="AddItem('working.hours.from',this);" /></td>


							<td width="20%" align="right" colspan="1"><label class="set"
								name="working.hours.to" id="working.hours.to"
								ondblclick="callShowDiv(this);"><%=label.get("working.hours.to")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="workinghoursToFlag"
								onclick="AddItem('working.hours.to',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="injury.date" id="injury.date"
								ondblclick="callShowDiv(this);"><%=label.get("injury.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="injuryDateFlag" onclick="AddItem('injury.date',this);" /></td>

						</tr>

						<!-- Did Incident Result in loss of workdays,(If lost work days,first full day out), Has injured returned to work & If so possible length of disability -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="loss.workdays" id="loss.workdays"
								ondblclick="callShowDiv(this);"><%=label.get("loss.workdays")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="lossworkDaysFlag" onclick="AddItem('loss.workdays',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="fulldayout" id="fulldayout"
								ondblclick="callShowDiv(this);"><%=label.get("fulldayout")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="lostFlag"
								onclick="AddItem('fulldayout',this);" /></td>


							<td width="20%" align="right" colspan="1"><label class="set"
								name="injuered.return" id="injuered.return"
								ondblclick="callShowDiv(this);"><%=label.get("injuered.return")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="injueredreturnFlag"
								onclick="AddItem('injuered.return',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="length.disebility" id="length.disebility"
								ondblclick="callShowDiv(this);"><%=label.get("length.disebility")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="disebilityFlag"
								onclick="AddItem('length.disebility',this);" /></td>

						</tr>
						<!-- Application Date & Application Status -->
						<tr>
							
							<td width="20%" align="right" colspan="1"><label class="set"
								name="application.date" id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="applDateFlag" onclick="AddItem('application.date',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="appl.status" id="appl.status"
								ondblclick="callShowDiv(this);"><%=label.get("appl.status")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="applStatusFlag" onclick="AddItem('appl.status',this);" /></td>
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
								<!--  <option value="1">--Select--</option>-->
								<option value="Employee Code">Name</option>
								<option value="Employee Code">Division</option>
								<option value="Employee Code">Department</option>
								<option value="Employee Code">Branch</option>
								<option value="Employee Code">Designation</option>
								<option value="Employee Code">Employee Type</option>
								<option value="Employee Code">Request Tracking Number</option>
								<option value="Employee Code">Manager</option>
								<option value="Employee Code">City</option>
								<option value="Employee Code">Number Of Dependant Under
								Age 18</option>
								<option value="Employee Code">Date of Injury</option>
								<option value="Employee Code">Time Of Injury</option>
								<option value="Employee Code">Hours Worked Date Of
								Injury</option>
								<option value="Employee Code">Normal Working Hours From</option>
								<option value="Employee Code">Normal Working Hours To</option>
								<option value="Employee Code">Date Employer knew of
								Injury</option>
								<option value="Employee Code">Did Incident Result in
								loss of workdays</option>
								<option value="Employee Code">If lost work days,first
								full day out</option>
								<option value="Employee Code">Has injured returned to
								work</option>
								<option value="Employee Code">If so possible length of
								disability</option>
								<option value="Employee Code">Application Date</option>
								<option value="Employee Code">Application status</option>



							</select> <s:hidden name="hiddenSortBy" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder('sortBy','A');" name="sortByOrder"
								<s:property value="sortByAsc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder('sortBy','D');" name="sortByOrder"
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
								<!--<option value="1">--Select--</option>-->
								<option value="Employee Code">Name</option>
								<option value="Employee Code">Division</option>
								<option value="Employee Code">Department</option>
								<option value="Employee Code">Branch</option>
								<option value="Employee Code">Designation</option>
								<option value="Employee Code">Employee Type</option>
								<option value="Employee Code">Request Tracking Number</option>
								<option value="Employee Code">Manager</option>
								<option value="Employee Code">City</option>
								<option value="Employee Code">Number Of Dependant Under
								Age 18</option>
								<option value="Employee Code">Date of Injury</option>
								<option value="Employee Code">Time Of Injury</option>
								<option value="Employee Code">Hours Worked Date Of
								Injury</option>
								<option value="Employee Code">Normal Working Hours From</option>
								<option value="Employee Code">Normal Working Hours To</option>
								<option value="Employee Code">Date Employer knew of
								Injury</option>
								<option value="Employee Code">Did Incident Result in
								loss of workdays</option>
								<option value="Employee Code">If lost work days,first
								full day out</option>
								<option value="Employee Code">Has injured returned to
								work</option>
								<option value="Employee Code">If so possible length of
								disability</option>
								<option value="Employee Code">Application Date</option>
								<option value="Employee Code">Application status</option>

							</select> <s:hidden name="hiddenThenBy1" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder('thenByOrder1','A');" name="thenByOrder1"
								<s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder('thenByOrder1','D');" name="thenByOrder1"
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
								<!--<option value="1">--Select--</option>-->
								<option value="Employee Code">Name</option>
								<option value="Employee Code">Division</option>
								<option value="Employee Code">Department</option>
								<option value="Employee Code">Branch</option>
								<option value="Employee Code">Designation</option>
								<option value="Employee Code">Employee Type</option>
								<option value="Employee Code">Request Tracking Number</option>
								<option value="Employee Code">Manager</option>
								<option value="Employee Code">City</option>
								<option value="Employee Code">Number Of Dependant Under
								Age 18</option>
								<option value="Employee Code">Date of Injury</option>
								<option value="Employee Code">Time Of Injury</option>
								<option value="Employee Code">Hours Worked Date Of
								Injury</option>
								<option value="Employee Code">Normal Working Hours From</option>
								<option value="Employee Code">Normal Working Hours To</option>
								<option value="Employee Code">Date Employer knew of
								Injury</option>
								<option value="Employee Code">Did Incident Result in
								loss of workdays</option>
								<option value="Employee Code">If lost work days,first
								full day out</option>
								<option value="Employee Code">Has injured returned to
								work</option>
								<option value="Employee Code">If so possible length of
								disability</option>
								<option value="Employee Code">Application Date</option>
								<option value="Employee Code">Application status</option>

							</select> <s:hidden name="hiddenThenBy2" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder2Asc" />
							<s:hidden name="thenByOrder2Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder('thenByOrder2','A');" name="thenByOrder2"
								<s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder('thenByOrder2','D');" name="thenByOrder2"
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

							</select> <s:hidden name="hiddenColumns" /></td>
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
				style="border-collapse: collapse;" border="1">
				<tr>
					<td colspan="7" width="100%"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>

				<!-- APPLICATION DATE -->
				<tr>
					<td colspan="1" height="22" width="15%"><label class="set"
						name="application.date" id="application.date"
						ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>:</td>
					<td colspan="1" width="10%" align="left"><s:select
						theme="simple" name="appliDateSelect" value="%{appliDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'1','appli');" /></td>
					<td width="10% colspan=" 1" height="22"><s:textfield
						name="appliFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_appliFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_appliFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="5%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_appliFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22" width="60%">
					<table cellspacing="0" cellpadding="0" border="1"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv1" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel1" height="22"><s:textfield
								name="appliToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_appliToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_appliToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker1"><s:a
								href="javascript:NewCal('paraFrm_appliToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>

				<!-- Date of Injury -->
				<tr>
					<td colspan="1" height="22" width="15%"><label class="set"
						name="aaaaa" id="aaaaa"
						ondblclick="callShowDiv(this);"><%=label.get("aaaaa")%></label>:</td>
					<td colspan="1" width="10%" align="left"><s:select
						theme="simple" name="injuryDateSelect" value="%{injuryDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'2','injury');" /></td>
					<td width="10%" colspan="1" height="22"><s:textfield
						name="injuryFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_injuryFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_injuryFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="5%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_injuryFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22" width="60%">
					<table cellspacing="0" cellpadding="0" border="1"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv2" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel2" height="22"><s:textfield
								name="injuryToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_injuryToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_injuryToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker2"><s:a
								href="javascript:NewCal('paraFrm_injuryToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>


				<!--  Approval Date -->
				<!-- <tr>
					<td colspan="1" height="22" width="15%"><label class="set"
						name="appr.date" id="appr.date" ondblclick="callShowDiv(this);"><%=label.get("appr.date")%></label>:</td>
					<td colspan="1" width="10%" align="left"><s:select
						theme="simple" name="approvalDateSelect"
						value="%{approvalDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'3','ter');" /></td>
					<td width="10%" colspan="1" height="22"><s:textfield
						name="approvalFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_approvalFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_approvalFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="5%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_approvalFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22" width="60%">
					<table cellspacing="0" cellpadding="0" border="1"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv3" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel3" height="22"><s:textfield
								name="approvalToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_approvalToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_approvalToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker3"><s:a
								href="javascript:NewCal('paraFrm_approvalToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr> -->





			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS --></td>
		</tr>
		<!-- DISPLAY OPTIONS BEGINS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="4" class="formtext"><b>Display
					option</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="4" class="formtext"><s:hidden
						name="hidReportView" /> <s:hidden name="hidReportRadio" /> <input
						type="radio" value="V" name="reportView" id="reportViewV"
						<s:property value="hidReportView"/> onclick="callReportChk('N');">
					View On Screen</td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><input
						type="radio" value="R" name="reportView" id="reportViewR"
						<s:property value="hidReportRadio"/> onclick="callReportChk('Y');">
					Report Type</td>
					<td width="80%" colspan="3" class="formtext">
					<div id="reportTypeDiv"><s:select headerKey="1"
						headerValue="--Select--" name="reportType"
						list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
					</td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap" colspan="1" class="formtext"><label
						class="set" id="report.criteria" name="report.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("report.criteria")%></label>
					:</td>
					<td width="80%" colspan="3"><s:textfield name="settingName"
						size="40" theme="simple" maxlength="40" /></td>
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
				class="save" onclick="saveReport()" /> <input name="button"
				type="button" value="  Search" class="search"
				onclick="searchReport()" /></td>
		</tr>
		<!-- BUTTON PANEL ENDS -->
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript"><!--
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
		setFieldsOnload();
	}

	function resetForm()
	{
		document.getElementById('paraFrm_reportId').value='';
		document.getElementById('paraFrm_reportTitle').value='';
		document.getElementById('paraFrm_settingName').value='';
			
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="WorkCompInjuryMISReport_reset.action";
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
		document.getElementById('paraFrm').action="WorkCompInjuryMISReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	}	
	
	function searchReport() {
		callsF9(500,325,'WorkCompInjuryMISReport_searchSavedReport.action');
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
		
	function AddItem(labelName,id)
    {
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
	        var checkedValue = id.checked;
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
    
    function callAllDisable(){
  		for(idNo=1;idNo<=2;idNo++){ 
   			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     		document.getElementById('toDateDivLabel'+idNo).style.display='none';     
	  		document.getElementById('toDatePicker'+idNo).style.display='none';  
	  	}
	  	if(document.getElementById("paraFrm_appliDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv1').style.display=''; 
   			document.getElementById('toDateDivLabel1').style.display='';  
  			document.getElementById('toDatePicker1').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_injuryDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv2').style.display=''; 
   			document.getElementById('toDateDivLabel2').style.display='';  
  			document.getElementById('toDatePicker2').style.display='';
	  	}
	  	
	 /*  if(document.getElementById("paraFrm_approvalDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv2').style.display=''; 
   			document.getElementById('toDateDivLabel2').style.display='';  
  			document.getElementById('toDatePicker2').style.display='';
	  	} */
	  	
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
    
	function callToDateDispOnClick(obj,idNo,idName)
 	{
 	///alert(obj);
 	///alert(idNo);
 	///alert(idName);
   		try{
   			var dateFilter= obj.value;
 			///alert(dateFilter);
 			///alert("1111111");
   			document.getElementById('paraFrm_'+idName+'ToDate').value="";  
   			///alert("1111111+++++++");
   			if(dateFilter=="BN"){
     			document.getElementById('toDateDiv'+idNo).style.display=''; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='';  
	  			document.getElementById('toDatePicker'+idNo).style.display='';
 
      			setText('paraFrm_'+idName+'ToDate',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_'+idName+'ToDate',"dd-mm-yyyy");
     			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='none';     
  				document.getElementById('toDatePicker'+idNo).style.display='none';  
   			} 
   		}catch(e){
   			alert(e);
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
    
    function callOrder(sortName,id){
    	if(id=="A"){
    		document.getElementById('paraFrm_'+sortName+'Dsc').value="";
    		document.getElementById('paraFrm_'+sortName+'Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_'+sortName+'Asc').value="";
    		document.getElementById('paraFrm_'+sortName+'Dsc').value="checked";
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
			
			var appliFromDate = document.getElementById('paraFrm_appliFromDate').value;
			var appliToDate = document.getElementById('paraFrm_appliToDate').value;
			var injuryFromDate = document.getElementById('paraFrm_injuryFromDate').value;
			var injuryToDate = document.getElementById('paraFrm_injuryToDate').value;
		 
		  ///var approvalFromDate = document.getElementById('paraFrm_approvalFromDate').value;
		
		///	var approvalToDate = document.getElementById('paraFrm_approvalToDate').value;
		
			
			
			if(document.getElementById("paraFrm_appliDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_appliFromDate").value);
				if(appliFromDate=="" || appliFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('application.date').innerHTML);
					//document.getElementById('paraFrm_misreport_appliFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_aappliDateSelect").value=="BN"){
					if(appliToDate==""||appliToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('application.date').innerHTML);
						//document.getElementById('paraFrm_misreport_appliToDate').focus();
						return false;
					}
				}
				
				if(appliFromDate!=""||appliFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_appliFromDate'];
				  	var lbl=['application.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(appliToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_appliToDate'];
			  		var lbl=['application.date'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Appli - IF		
		
		//injury date 
		
			if(document.getElementById("paraFrm_injuryDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_attFromDate").value);
				if(injuryFromDate=="" || injuryFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('aaaaa').innerHTML);
					//document.getElementById('paraFrm_misreport_attFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_injuryDateSelect").value=="BN"){
					if(injuryToDate==""||injuryToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('aaaaa').innerHTML);
						//document.getElementById('paraFrm_misreport_attToDate').focus();
						return false;
					}
				}
				
				if(injuryFromDate!=""||injuryFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_injuryFromDate'];
				  	var lbl=['aaaaa'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(injuryToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_injuryToDate'];
			  		var lbl=['aaaaa'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Att - IF					
			
			// approval date 
			
		/*	if(document.getElementById("paraFrm_approvalDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_attFromDate").value);
				if(approvalFromDate=="" || approvalFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('appr.date').innerHTML);
					//document.getElementById('paraFrm_misreport_attFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_approvalDateSelect").value=="BN"){
					if(approvalToDate==""||approvalToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('appr.date').innerHTML);
						//document.getElementById('paraFrm_misreport_attToDate').focus();
						return false;
					}
				}
				
				if(approvalFromDate!=""||approvalFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_approvalFromDate'];
				  	var lbl=['appr.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(approvalToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_approvalToDate'];
			  		var lbl=['appr.date'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Att - IF	*/	
			
			
		}catch(e){
			alert(e);
		}
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="WorkCompInjuryMISReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="WorkCompInjuryMISReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}		
	}
--></script>
