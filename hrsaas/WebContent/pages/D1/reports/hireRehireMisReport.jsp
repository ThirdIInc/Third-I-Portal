<!--@author Ayyappa @date 30-03-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HireRehireMisReport" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"> Hire/Rehire
						Mis Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- BUTTON PANEL BEGINS -->
		<tr><s:hidden name='backFlag' />
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> <input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
						<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> 
						<input name="button" type="button" value="  Search"
						class="search" onclick="searchReport()" /></td>
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
					<td width="20%"><s:hidden name="misreport.savedReport" />
					<s:hidden name="misreport.reportId" /><!-- Report ID -->
					<label class="set" name="report.title" id="report.title"
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
			<td>
			<!-- DIV FIRST BEGINS - FILTERS -->
			<div id="first">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td colspan="7" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>
				<!-- DIVISION -->
				<tr>
					<td width="20%"><label class="set" name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.divName" readonly="true" /></td>
					<td width="5%" align="left"><s:hidden name="misreport.divId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9division.action');" />
					</td>
					<td colspan="4"></td>
				</tr>
				
				<!-- DEPARTMENT & BRANCH -->
				<tr>
					<td width="18%"><label class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.deptName" readonly="true" /></td>
					<td width="5%" align="left"><s:hidden name="misreport.deptId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9department.action');" />
					</td>
					<td width="4%"></td>
					<td width="18%"><label class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.branchName" readonly="true" /></td>
					<td width="5%" align="left"><s:hidden name="misreport.branchId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9branch.action');" />
					</td>					
				</tr>
				
				<!-- EMPLOYEE TYPE & DESIGNATION -->
				<tr>
					<td width="18%"><label class="set" name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.empTypeName" readonly="true" /></td>
					<td width="5%" align="left"><s:hidden name="misreport.empTypeId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9empType.action');" />
					</td>
					<td width="4%"></td>
					<td width="18%"><label class="set" name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.desigName" readonly="true" /></td>
					<td width="5%" align="left"><s:hidden name="misreport.desigId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9designation.action');" />
					</td>					
				</tr>	

				
				
				<!--  EMPLOYEE FIRST NAME & TRACKING NUMBER -->
				<tr>
					<td width="18%"><label class="set" name="employee.first.name" id="employee.first.name"
						ondblclick="callShowDiv(this);"><%=label.get("employee.first.name")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.empFirstName" readonly="true" /></td>
					<td width="5%" align="left">
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9empFirstName.action');" />
					</td>
					<td width="4%"></td>
					<td width="18%"><label class="set" name="tracking.number" id="tracking.number"
						ondblclick="callShowDiv(this);"><%=label.get("tracking.number")%></label>
					:</td>
					<td width="25%"><s:textfield size="30"
						name="misreport.trackingNumber" readonly="true" /></td>
					<td width="5%" align="left">
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9trackingNumber.action');" />
					</td>					
				</tr>
				
				<!-- SHIFT -->
				<tr>
					<td width="18%"><label class="set" name="action.reason" id="action.reason"
						ondblclick="callShowDiv(this);"><%=label.get("action.reason")%></label>
					:</td>
					<td width="25%"><s:select name="misreport.actionReason" headerKey="1" theme="simple"
						headerValue="--Select--" cssStyle="width:150"
						 list="#{'H':'Hire','R':'Rehire','A':'Acquisition'}"  />
					</td>
					<td width="4%"></td>
					
					<td width="6%"></td>
					<td width="18%"><label class="set" name="appl.status" id="appl.status"
						ondblclick="callShowDiv(this);"><%=label.get("appl.status")%></label>
					:</td>
					<td width="25%"><s:select name="misreport.status" headerKey="1" theme="simple"
						headerValue="--Select--" cssStyle="width:150"
						 list="#{'D':'Draft','P':'Pending','A':'Approved','R':'Rejected','B':'Send Back'}"  />
					</td>
				</tr>	
				
				<!-- OR -->
				<tr>
					<td colspan="7" align="center"><strong class="text_head">OR</strong></td>
				</tr>
				
				<!-- EMPLOYEE -->
				<tr>
					<td width="18%"><label class="set" name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="77%" colspan="5"><s:textfield size="20"
						name="misreport.empToken" readonly="true" /><s:textfield size="85"
						name="misreport.empName" readonly="true" />
					</td>
					<td width="5%" align="left"><s:hidden name="misreport.empId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'HireRehireMisReport_f9employee.action');" />
					</td>					
				</tr>				
			</table>
			</div>
			<!-- DIV FIRST ENDS - FILTERS -->
			
			<!-- DIV SECOND BEGINS - COLUMN DEFINITIONS -->
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
						
						<!-- FIRST NAME,MIDDLE NAME, LAST NAME ,SECURITY NUMBER -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="first.name" id="first.name" ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.firstNameFlag" onclick="AddItem('first.name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="middle.name" id="middle.name" ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.middleNameFlag" onclick="AddItem('middle.name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="last.name" id="last.name"
								ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.lastNameFlag" onclick="AddItem('last.name',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="social.security.number" id="social.security.number" ondblclick="callShowDiv(this);"><%=label.get("social.security.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.socialSecurityNumberFlag" onclick="AddItem('social.security.number',this);" /></td>
						</tr>
						<!-- CITY,STATE, PHONE NUMBER ,SEX -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="city" id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.cityFlag" onclick="AddItem('city',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="state" id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.stateFlag" onclick="AddItem('state',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="phone.number" id="phone.number"
								ondblclick="callShowDiv(this);"><%=label.get("phone.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.phoneNumberFlag" onclick="AddItem('phone.number',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="sex" id="sex" ondblclick="callShowDiv(this);"><%=label.get("sex")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.sexFlag" onclick="AddItem('sex',this);" /></td>
						</tr>
						
						<!-- MARTIAL STATUS,EDUCATION, BIRTH DATE ,REFFERAL SOURCE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="martial.status" id="martial.status" ondblclick="callShowDiv(this);"><%=label.get("martial.status")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.martialStatusFlag" onclick="AddItem('martial.status',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="education" id="education" ondblclick="callShowDiv(this);"><%=label.get("education")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.educationFlag" onclick="AddItem('education',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="birth.date" id="birth.date"
								ondblclick="callShowDiv(this);"><%=label.get("birth.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.birthDateFlag" onclick="AddItem('birth.date',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="referal.source" id="referal.source" ondblclick="callShowDiv(this);"><%=label.get("referal.source")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.referalSourceFlag" onclick="AddItem('referal.source',this);" /></td>
						</tr>
						
						<!-- HIRE DATE,WORK LOCATION, ACTION REASON ,GRADE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="hire.date" id="hire.date" ondblclick="callShowDiv(this);"><%=label.get("hire.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.hireDateFlag" onclick="AddItem('hire.date',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="physical.work.location" id="physical.work.location" ondblclick="callShowDiv(this);"><%=label.get("physical.work.location")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.physicalWorkLocationFlag" onclick="AddItem('physical.work.location',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="action.reason" id="action.reason"
								ondblclick="callShowDiv(this);"><%=label.get("action.reason")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.actionReasonFlag" onclick="AddItem('action.reason',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.gradeFlag" onclick="AddItem('grade',this);" /></td>
						</tr>
						
						<!-- PAY GROUP,ANNUAL SALARY, EXECUTIVE EMPLOYEE ,TRACKING NUMBER -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="pay.group" id="pay.group" ondblclick="callShowDiv(this);"><%=label.get("pay.group")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.payGroupFlag" onclick="AddItem('pay.group',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="annual.salary" id="annual.salary" ondblclick="callShowDiv(this);"><%=label.get("annual.salary")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.annualSalaryFlag" onclick="AddItem('annual.salary',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="executive.employee" id="executive.employee"
								ondblclick="callShowDiv(this);"><%=label.get("executive.employee")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.executiveEmployeeFlag" onclick="AddItem('executive.employee',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tracking.number" id="tracking.number" ondblclick="callShowDiv(this);"><%=label.get("tracking.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.trackingNumberFlag" onclick="AddItem('tracking.number',this);" /></td>
						</tr>
						
						<!-- NAME,DIVISION,DEPARTMENT,BRANCH -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="social.insurance.number" id="social.insurance.number" ondblclick="callShowDiv(this);"><%=label.get("social.insurance.number")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.socialInsuranceNumberFlag" onclick="AddItem('social.insurance.number',this);" /></td>
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
								name="misreport.branchFlag" onclick="AddItem('branch',this);" /></td>
						</tr>						
						<!-- DESIGNATION,EMPLOYEE TYPE,APPLICATION DATE,ATTENDANCE DATE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.desigFlag" onclick="AddItem('designation',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="employee.type" id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.empTypeFlag" onclick="AddItem('employee.type',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="application.date" id="application.date"
								ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.appliDateFlag" onclick="AddItem('application.date',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="application.status" id="application.status" ondblclick="callShowDiv(this);"><%=label.get("application.status")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.statusFlag" onclick="AddItem('application.status',this);" /></td>	
						</tr>			
						
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV SECOND ENDS - COLUMN DEFINITIONS -->
			
			<!-- DIV THIRD BEGINS - SORTING OPTIONS -->
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
								<option value="Employee Code">Employee Code</option>
							</select>
							<s:hidden name="hiddenSortBy" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A" onclick="callOrder('sortBy','A');"
								name="sortByOrder" <s:property value="sortByAsc"/> ></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D" onclick="callOrder('sortBy','D');"
								name="sortByOrder" <s:property value="sortByDsc"/> ></td>

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
								<option value="Employee Code">Employee Code</option>
							</select>
							<s:hidden name="hiddenThenBy1" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"  onclick="callOrder('thenByOrder1','A');"
								name="thenByOrder1" <s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"  onclick="callOrder('thenByOrder1','D');"
								name="thenByOrder1" <s:property value="thenByOrder1Dsc"/>></td>

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
								<option value="Employee Code">Employee Code</option>
							</select>
							<s:hidden name="hiddenThenBy2" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder2Asc" />
							<s:hidden name="thenByOrder2Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A" onclick="callOrder('thenByOrder2','A');"
								name="thenByOrder2" <s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D" onclick="callOrder('thenByOrder2','D');"
								name="thenByOrder2" <s:property value="thenByOrder2Dsc"/>></td>

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
			<!-- DIV THIRD ENDS - SORTING OPTIONS -->
			
			<!-- DIV FOURTH BEGINS - ADVANCE FILTERS -->
			<div id="fourth">
			<table cellspacing="0" cellpadding="0" class="formbg" width="100%"
				style="border-collapse: collapse;" border="0">
				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>
				
				<!-- APPLICATION DATE -->
				<tr>
					<td colspan="1" height="22" width="15%"><label class="set"
								name="application.date" id="application.date"
								ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label>:</td>
					<td colspan="1" width="10%" align="left"><s:select theme="simple"
						name="appliDateSelect" value="%{appliDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'1','appli');" /></td>
					<td width="10% colspan="1" height="22"><s:textfield
						name="misreport.appliFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_misreport_appliFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_misreport_appliFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="5%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_misreport_appliFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22" width="60%">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv1" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel1" height="22"><s:textfield
								name="misreport.appliToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_misreport_appliToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_misreport_appliToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker1"><s:a
								href="javascript:NewCal('paraFrm_misreport_appliToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>	
				<!-- HIRE DATE -->
				<tr>
					<td colspan="1" height="22" width="15%"><label class="set"
								name="hire.date" id="hire.date"
								ondblclick="callShowDiv(this);"><%=label.get("hire.date")%></label>:</td>
					<td colspan="1" width="10%" align="left"><s:select theme="simple"
						name="attDateSelect" value="%{attDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'2','att');" /></td>
					<td width="10%" colspan="1" height="22"><s:textfield
						name="misreport.attFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_misreport_attFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_misreport_attFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="5%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_misreport_attFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22" width="60%">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv2" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel2" height="22"><s:textfield
								name="misreport.attToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_misreport_attToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_misreport_attToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker2"><s:a
								href="javascript:NewCal('paraFrm_misreport_attToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>	
			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS -->
			</td>
		</tr>
		<!-- DISPLAY OPTIONS BEGINS -->
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
						name="hidReportView" /> <s:hidden name="hidReportRadio" /> <input
						type="radio" value="V" name="reportView" id="reportViewV"
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
			<td width="100%">
				<input type="button" class="token" theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> 
				<input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" /> 
				<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> 
				<input name="button" type="button" value="  Search"
						class="search" onclick="searchReport()" />
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->					
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">
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
		document.getElementById('paraFrm_misreport_reportId').value='';
		document.getElementById('paraFrm_misreport_reportTitle').value='';
		document.getElementById('paraFrm_settingName').value='';
			
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="HireRehireMisReport_reset.action";
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
		document.getElementById('paraFrm').action="HireRehireMisReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	}	
	
	function searchReport() {
		callsF9(500,325,'HireRehireMisReport_searchSavedReport.action');
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
  		for(idNo=1;idNo <=2;idNo++){ 
   			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     		document.getElementById('toDateDivLabel'+idNo).style.display='none';     
	  		document.getElementById('toDatePicker'+idNo).style.display='none';  
	  	}
	  	if(document.getElementById("paraFrm_appliDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv1').style.display=''; 
   			document.getElementById('toDateDivLabel1').style.display='';  
  			document.getElementById('toDatePicker1').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_attDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv2').style.display=''; 
   			document.getElementById('toDateDivLabel2').style.display='';  
  			document.getElementById('toDatePicker2').style.display='';
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
    
	function callToDateDispOnClick(obj,idNo,idName)
 	{
   		try{
   			var dateFilter= obj.value;
 			//alert(dateFilter);
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
			
			var apFromDate = document.getElementById('paraFrm_misreport_appliFromDate').value;
			var apToDate = document.getElementById('paraFrm_misreport_appliToDate').value;
			var atFromDate = document.getElementById('paraFrm_misreport_attFromDate').value;
			var atToDate = document.getElementById('paraFrm_misreport_attToDate').value;
			
			if(document.getElementById("paraFrm_appliDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_appliFromDate").value);
				if(apFromDate=="" || apFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('application.date').innerHTML);
					//document.getElementById('paraFrm_misreport_appliFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_appliDateSelect").value=="BN"){
					if(apToDate==""||apToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('application.date').innerHTML);
						//document.getElementById('paraFrm_misreport_appliToDate').focus();
						return false;
					}
				}
				
				if(apFromDate!=""||apFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_appliFromDate'];
				  	var lbl=['application.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(apToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_appliToDate'];
			  		var lbl=['application.date'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Appli - IF		
			
			if(document.getElementById("paraFrm_attDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_attFromDate").value);
				if(atFromDate=="" || atFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('attendance.date').innerHTML);
					//document.getElementById('paraFrm_misreport_attFromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_attDateSelect").value=="BN"){
					if(atToDate==""||atToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('attendance.date').innerHTML);
						//document.getElementById('paraFrm_misreport_attToDate').focus();
						return false;
					}
				}
				
				if(atFromDate!=""||atFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_attFromDate'];
				  	var lbl=['attendance.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(atToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_attToDate'];
			  		var lbl=['attendance.date'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Att - IF					
			
		}catch(e){
			alert(e);
		}
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="HireRehireMisReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="HireRehireMisReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}		
	}
</script>
