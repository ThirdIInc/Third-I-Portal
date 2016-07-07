<!--@author Vishwambhar_Deshpande @date  2 Mar 2010 -->
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ExtraWorkBenefitMisBean" validate="true" id="paraFrm"
	theme="simple">
	<!-- Main Table Starts -->
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
					<td width="93%" class="txt"><strong class="text_head">Extra
					Work Benefit MIS Report </strong></td>
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
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> <input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
					<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" />
							<input type="button" class="search"
				theme="simple" value="  Search" onclick="javascript:search();"
				 /></td>
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
					 <s:textfield name="misreport.reportId"/>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="7" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>

				<!-- Month & Year -->
				<tr>
					<td><label class="set" name="extrawork.month"
						id="extrawork.month" ondblclick="callShowDiv(this);"><%=label.get("extrawork.month")%></label>
					:</td>
					<td colspan="2"><s:select name="month" headerKey="0"
						headerValue="--Select--" cssStyle="width:150"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td><label class="set" name="extrawork.year"
						id="extrawork.year" ondblclick="callShowDiv(this);"><%=label.get("extrawork.year")%></label>
					:</td>
					<td colspan="2"><s:textfield size="25" name="year"  maxlength="4" onkeypress="return numbersOnly();"/></td>
				</tr>

				<!-- Division & Benefit For -->
				<tr>
					<td width="20%" colspan="1"><label class="set" name="division"
						id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
					<td width="20%" colspan="1"><s:hidden name="divCode" /><s:textfield
						size="25" name="divName" readonly="true" /></td>
					<td width="23%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExtraWorkBenefitMisBean_f9divaction.action');"></td>

					<td width="20%" colspan="1"><label class="set"
						name="benefit.for" id="benefit.for"
						ondblclick="callShowDiv(this);"><%=label.get("benefit.for")%></label>:</td>
					<td width="20%" colspan="1">
					<s:select name="benefitsFor" headerKey="1" theme="simple"
						headerValue="--Select--" cssStyle="width:150"
						 list="#{'HLD':'Holiday','NHD':'National Holiday','SUN':'Sunday','MON':'Monday','TUE':'Tuesday','WED':'Wednesday',
						'THU':'Thursday','FRI':'Friday','SAT':'Saturday' }"  />
				</td>
					<td width="23%" colspan="1"></td>
				</tr>

				<!-- ExtraworkType &  Benefit Type -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="extraWork.Type" id="extraWork.Type"
						ondblclick="callShowDiv(this);"><%=label.get("extraWork.Type")%></label>:</td>
					<td width="20%" colspan="1"><s:select theme="simple"  headerKey="1" headerValue="--Select--"
						name="extraWorkType" cssStyle="width:150"
						list="#{'F':'Full Day','H':'Half Day','O':'Partial Day'}" /></td>
					<td width="23%" colspan="1"></td>
					
					<td width="20%" colspan="1"><label class="set" name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="employeeName" readonly="true" /> <s:hidden
						name="employeeToken" /><s:hidden name="employeeCode" /></td>
					<td width="23%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExtraWorkBenefitMisBean_f9employee.action');"></td>
					<!-- <td width="20%" colspan="1"><label class="set"
						name="benefit.type" id="benefit.type"
						ondblclick="callShowDiv(this);"></label>:</td>
					<td width="20%" colspan="1"><s:select theme="simple"
						id="benefitsType" name="benefitsType" headerKey="1" headerValue="--Select--"
						cssStyle="width:150"
						list="#{'EP':'Extra-Day Pay','EL':'Extra-Day Leave','FB':'Fixed Benefits','VB':'Variable Benefits' }" /></td>
					<td width="23%" colspan="1"></td> -->
				</tr>
 	
				<tr>
					<td width="20%" colspan="1"><label class="set" name="approvedBy"
						id="approvedBy" ondblclick="callShowDiv(this);"><%=label.get("approvedBy")%></label>:</td>
					<td width="20%" colspan="1"> <s:textfield size="25"
						name="approverName" readonly="true" /> <s:hidden
						name="approverCode" /><s:hidden name="approverToken" /></td>
					<td width="23%" colspan="1"> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExtraWorkBenefitMisBean_f9approver.action');"></td>

					<td width="20%" colspan="1"><label class="set"
						name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
					<td width="20%" colspan="1">
					<s:select name="misreport.status" headerKey="1" theme="simple"
						headerValue="--Select--" cssStyle="width:150"
						 list="#{'D':'Draft','B':'Send Back','P':'Pending','A':'Approved','R':'Rejected'}"  />
				</td>
					<td width="23%" colspan="1"></td>
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
						<!-- DESIGNATION,ExtraWork Date,Benefit Type,Benefit For  -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.rankFlag" onclick="AddItem('designation',this);" /></td>

							<td width="20%" align="right" colspan="1"><label class="set"
								name="extrawork.date" id="extrawork.date"
								ondblclick="callShowDiv(this);"><%=label.get("extrawork.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.extraWorkDateFlag"
								onclick="AddItem('extrawork.date',this);" /></td>

							<!--<td align="right" colspan="1"><label class="set"
								name="benefit.type" id="benefit.type1"
								ondblclick="callShowDiv(this);"></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.benefitTypeFlag" onclick="AddItem('benefit.type',this);" /></td>
							--><td align="right" colspan="1"><label class="set"
								name="benefit.for" id="benefit.for1"
								ondblclick="callShowDiv(this);"><%=label.get("benefit.for")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.benefitForFlag" onclick="AddItem('benefit.for',this);" /></td>
								
								<td align="right" colspan="1"><label class="set"
								name="approvedBy" id="approvedBy1"
								ondblclick="callShowDiv(this);"><%=label.get("approvedBy")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.approvedByFlag"
								onclick="AddItem('approvedBy',this);" /></td>
						</tr>
						<!-- Approved By -->
						 <tr>
							<td align="right" colspan="1"><label class="set"
								name="extraworkAmount" id="extraworkAmount"
								ondblclick="callShowDiv(this);"><%=label.get("extraworkAmount")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.extraworkAmountFlag"
								onclick="AddItem('extraworkAmount',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="application.status" id="application.status"
								ondblclick="callShowDiv(this);"><%=label.get("application.status")%></label>
							:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.empStatusFlag"
								onclick="AddItem('application.status',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="extrawork.starttime" id="extrawork.starttime"
								ondblclick="callShowDiv(this);"><%=label.get("extrawork.starttime")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.extraworkStartTime"
								onclick="AddItem('extrawork.starttime',this);" /></td>
							<td align="right" colspan="1"><label class="set"
								name="extrawork.endtime" id="extrawork.endtime"
								ondblclick="callShowDiv(this);"><%=label.get("extrawork.endtime")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="misreport.extraworkEndTime"
								onclick="AddItem('extrawork.endtime',this);" /></td>
						</tr>
						<tr>
						<td align="right" colspan="1"><label class="set"
								name="extrawork.approvalDate" id="extrawork.approvalDate"
								ondblclick="callShowDiv(this);"><%=label.get("extrawork.approvalDate")%></label>:</td>
						<td colspan="1"><s:checkbox theme="simple"
								name="misreport.extraworkApprovalDate"
								onclick="AddItem('extrawork.approvalDate',this);" /></td>
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
								<!--<option value="1">--Select--</option>-->
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
								<!--<option value="1">--Select--</option>-->
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
				style="border-collapse: collapse;" border="0">
				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>

				<tr>
					<td colspan="1" height="22">
					<label class="set"
								name="extrawork.date" id="extrawork.date"
								ondblclick="callShowDiv(this);"><%=label.get("extrawork.date")%></label>
					:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="ewdSelect" value="%{ewdSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'1','extraWork');" /></td>
					<td width="47" colspan="1" height="22"><s:textfield
						name="misreport.extraWorkFromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_misreport_extraWorkFromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_misreport_extraWorkFromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="90" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_misreport_extraWorkFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel" height="22"><s:textfield
								name="misreport.extraWorkToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_misreport_extraWorkToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_misreport_extraWorkToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker"><s:a
								href="javascript:NewCal('paraFrm_misreport_extraWorkToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="124" colspan="1" height="22">Amount :</td>
					<td width="80" colspan="1" height="22" align="left"><s:select
						theme="simple" name="amountSelect" value="%{amountSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}"
						onchange="callBetween(this,'1');" /></td>
					<td colspan="1" height="22"><s:textfield name="amountFrom"
						size="9" onkeypress="return numbersOnly();" maxlength="6" /></td>
					<td colspan="1" height="22">Rs.</td>
					<td id="betweenOn" colspan="3" height="22">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td colspan="1" height="22" width="74" align="center"
								id="toDivAnd"><strong>AND </strong></td>
							<td colspan="1" height="22" width="47" id="toDivAndText"><s:textfield
								name="amountTo" size="9" onkeypress="return numbersOnly();"
								maxlength="6" /></td>
							<td width="500" height="22" colspan="1" id="toDivAndYrs">Rs.
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS --> <!-- DIV FIFTH BEGINS - COMMON FIELDS FOR ALL DIVs -->
			<!--<div id="fifth">
			<table cellpadding="0" cellspacing="0" border="0" class="formbg"
				width="100%">
				<tr>
					<td colspan="4" width="100%">Report Layout</td>
				</tr>
			</table>
			</div>
			--><!-- DIV FIFTH ENDS - COMMON FIELDS FOR ALL DIVs --></td>
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
			<td width="100%"><input type="button" class="token"
				theme="simple" value="  Generate Report"
				onclick=" return generateReport();" /> <input type="button"
				class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
			<input name="button" type="button" value="Save report criteria"
				class="save" onclick="saveReport()" />
				<input type="button" class="search"
				theme="simple" value="  Search" onclick="javascript:search();"
				 /></td>

		</tr>
		<!-- BUTTON PANEL ENDS -->
	</table>
	<!-- Main Table Ends -->
	
	<s:hidden name="reportTypeStr"></s:hidden>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script><!--
	
	
	function search()
	{
		callsF9(500,325,'ExtraWorkBenefitMisBean_searchSavedReport.action');	
	}

	function generateReport()
	{
	
	
	
		try{
		
			var frmDate = document.getElementById('paraFrm_misreport_extraWorkFromDate').value;
			var toDate = document.getElementById('paraFrm_misreport_extraWorkToDate').value;
		
		
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
			
			if(document.getElementById("paraFrm_amountSelect").value!=""){
				//alert(document.getElementById("paraFrm_ageFrom").value);
				if(document.getElementById("paraFrm_amountFrom").value==""){
					alert("Please enter Amount");
					//document.getElementById('paraFrm_ageFrom').focus();
					return false;
				}
				if(document.getElementById("paraFrm_amountSelect").value=="BN"){
					if(document.getElementById("paraFrm_amountTo").value==""){
						alert("Please enter Amount");
						//document.getElementById('paraFrm_ageTo').focus();
						return false;
					}
				}
			}
				if(document.getElementById("paraFrm_ewdSelect").value!=""){
				//alert(document.getElementById("paraFrm_frmDate").value);
				if(frmDate=="" || frmDate=="dd-mm-yyyy"){
					alert("Please enter Extra Work Date ");
					//document.getElementById('paraFrm_misreport_DOBfromDate').focus();
					return false;
				}
				if(document.getElementById("paraFrm_ewdSelect").value=="BN"){
					if(toDate==""||toDate=="dd-mm-yyyy"){
						alert("Please enter Extra Work Date ");
						//document.getElementById('paraFrm_misreport_DOBToDate').focus();
						return false;
					}
				}
		 		if(frmDate!=""||frmDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_misreport_extraWorkFromDate'];
				  	var lbl=['extrawork.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  			if(toDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_misreport_extraWorkToDate'];
			  		var lbl=['extrawork.date'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	
		 
   	  
		}catch(e)
		{ 
		alert("e------------"+e);
		}
		
		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="ExtraWorkBenefitMisBean_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="ExtraWorkBenefitMisBean_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}	


function saveReport(){
		var settingName = trim(document.getElementById('paraFrm_settingName').value);
		if(settingName == ""){
			alert("Please enter "+document.getElementById('report.criteria').innerHTML);
			document.getElementById('paraFrm_settingName').focus();
			return false;
		}
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="ExtraWorkBenefitMisBean_saveReport.action";
		document.getElementById('paraFrm').submit();
	
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
	
function callToDateDispOnClick(obj,idNo,idName)
 	{
   		try{
   			var dateFilter= obj.value;
 			//alert(dateFilter);
   			document.getElementById('paraFrm_misreport_'+idName+'ToDate').value="";  
   			if(dateFilter=="BN"){
     			document.getElementById('toDateDiv').style.display=''; 
     			document.getElementById('toDateDivLabel').style.display='';  
	  			document.getElementById('toDatePicker').style.display='';
 
      			setText('paraFrm_misreport_'+idName+'ToDate',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_misreport_'+idName+'ToDate',"dd-mm-yyyy");
     			document.getElementById('toDateDiv').style.display='none'; 
     			document.getElementById('toDateDivLabel').style.display='none';     
  				document.getElementById('toDatePicker').style.display='none';  
   			} 
   		}catch(e){
   			alert(e);
   		}
 	}
	
	
	function callBetween(obj,idNo)
	{
		try{
			var dateFilter= obj.value;
   		if(dateFilter=="BN"){
			document.getElementById('betweenOn').style.display=''; 
		  	}
		  	else {
  			document.getElementById('betweenOn').style.display='none'; 
    		  
   		} 
		}
		catch(e)
		{
		 alert(e);
		 
		 }
		
 	}
 	
 	function callAllDisable(){
  		  
   			document.getElementById('toDateDiv').style.display='none'; 
     		document.getElementById('toDateDivLabel').style.display='none';     
	  		document.getElementById('toDatePicker').style.display='none';  
	 	 	document.getElementById('betweenOn').style.display='none';
	  	if(document.getElementById("paraFrm_amountSelect").value=="BN"){
	  		document.getElementById('betweenOn').style.display=''; 
	  	}
	  	if(document.getElementById("paraFrm_ewdSelect").value=="BN"){
	  		document.getElementById('toDateDiv').style.display=''; 
   			document.getElementById('toDateDivLabel').style.display='';  
  			document.getElementById('toDatePicker').style.display='';
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
    
    
    function resetForm()
	{
	
		document.getElementById('paraFrm_misreport_reportId').value='';
		document.getElementById('paraFrm_misreport_reportTitle').value='';
		document.getElementById('paraFrm_settingName').value='';
		 
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="ExtraWorkBenefitMisBean_reset.action";
		document.getElementById('paraFrm').submit();
	
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
	
    function AddItem(labelName,id)
    {
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
        	//alert("123------"+labelNameCheck);
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

    
</script>
