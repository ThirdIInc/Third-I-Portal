<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0"
		onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
		cellspacing="2" class="formbg" style="border: 1px solid #565656;"
		align="center">
	<s:hidden name="actsChildLabour.ChildLaboutActID"/>
		<s:hidden name="actsChildLabour.ReturnID"/>
		<s:hidden name="pageToShow" value="annualfactory" />
		<s:hidden name="previousPage" value="equalrenumeration" />
		<s:hidden name="orgId" /><s:hidden name="frequency" />
		<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
		<s:hidden name="orgName" />
		<tr>
			<td valign="bottom" class="txt" colspan="4">
			<table width="100%" border="0" align="right" class="formbg">

				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Child
					Labour </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext" colspan="3">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="left"><s:select
						name="actList" list="actMap" size="1" headerKey=""
						headerValue="--Select Act--" cssStyle="width:150" theme="simple" /> 
						<input type="submit" value="Go To"
						class="token" onclick="return goTo();"></div>
					</td>

				</tr>
			</table>
			</td>
		</tr>
		<tr class="clsTRBody">
			<td colspan="3"><!-- Heading Section (Start) -->
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				style="border: 1px solid #565656;">
				<!-- English heading section (Begin) -->
				<tr>
					<td align="center"><label class="set"
						name="childlabour.heading" id="childlabour.heading"
						ondblclick="callShowDiv(this);"><%=label.get("childlabour.heading")%></label>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><br>

					<label class="set" name="childlabour.heading2"
						id="childlabour.heading2" ondblclick="callShowDiv(this);"><%=label.get("childlabour.heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- English heading section (End) -->

				<!-- Marathi heading section (Begin) -->
				<tr>
					<td colspan="2" align="center"><label class="set"
						name="childlabour.marathi_heading1"
						id="childlabour.marathi_heading1" ondblclick="callShowDiv(this);"><%=label.get("childlabour.marathi_heading1")%>
					</label> <br><s:property value="fromPeriod" /> - <s:property value="toPeriod" /><label class="set"
						name="childlabour.marathi_heading3"
						id="childlabour.marathi_heading3" ondblclick="callShowDiv(this);"><%=label.get("childlabour.marathi_heading3")%>
					</label>
					<br><label class="set"
						name="childlabour.marathi_heading2"
						id="childlabour.marathi_heading2" ondblclick="callShowDiv(this);"><%=label.get("childlabour.marathi_heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- Marathi heading section (End) -->
				</td>
				</tr>
			</table>
			<!-- Heading Section (End) --> 

			<table width="100%" border="0"
				onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
				cellspacing="2" style="border: 1px solid #565656;" align="center">
				<tr>
					<td class="formtext" colspan="5">
					<table width="100%" border="0" >
						<tr>
							<td align="left"><b>Child labour (Prohibited occupations
							and processes)<b></td>
						</tr>
					</table>
					</td>
				</tr>

				<br />

				<tr class="clsTRBody">
					<td width="3%">16</td>
					<td><label class="set" name="occupations_and_processes"
						id="occupations_and_processes" ondblclick="callShowDiv(this);"><%=label.get("occupations_and_processes")%></label></td>
					<td colspan=3><s:checkbox id="actsChildLabour.AreHazardousProcessesCarriedOut" name="actsChildLabour.AreHazardousProcessesCarriedOut" />
					<label class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td></td>
					<td><label class="set" name="occupation_or_process"
						id="occupation_or_process" ondblclick="callShowDiv(this);"><%=label.get("occupation_or_process")%></label></td>

				</tr>

				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="occupation" id="occupation"
						ondblclick="callShowDiv(this);"><%=label.get("occupation")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.HazardousOccupationDetails" size="30" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="processes" id="processes"
						ondblclick="callShowDiv(this);"><%=label.get("processes")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.HazardousProcessDetails" size="30" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="child_in_regulatedpart"
						id="child_in_regulatedpart" ondblclick="callShowDiv(this);"><%=label.get("child_in_regulatedpart")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.childinRegulatedPart"
						size="30" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>17</td>
					<td><label class="set" name="persons_under_the_age14"
						id="persons_under_the_age14" ondblclick="callShowDiv(this);"><%=label.get("persons_under_the_age14")%></label></td>
					<td colspan=3><s:checkbox id="actsChildLabour.ChildLaboursEmployed"
						name="actsChildLabour.ChildLaboursEmployed" /> <label class="set" name="yes/no"
						id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="how_many_peremployed"
						id="how_many_peremployed" ondblclick="callShowDiv(this);"><%=label.get("how_many_peremployed")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.NumberOfChildLaboursEmployed" size="30" onkeypress="return numbersWithDot();"/>
					</td>
				</tr>
				<tr class="clsTRBody">
					<td>18</td>
					<td><label class="set" name="work_of_people_under14"
						id="work_of_people_under14" ondblclick="callShowDiv(this);"><%=label.get("work_of_people_under14")%></label></td>
					<td colspan=3><s:textarea name="actsChildLabour.WorkDetails" cols="45"
						rows="5" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>19</td>
					<td><label class="set" name="work_hours_by_child"
						id="work_hours_by_child" ondblclick="callShowDiv(this);"><%=label.get("work_hours_by_child")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WorkHrsPerDay" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>20</td>
					<td><label class="set" name="work_hours_by_child_withoutbreak"
						id="work_hours_by_child_withoutbreak"
						ondblclick="callShowDiv(this);"><%=label.get("work_hours_by_child_withoutbreak")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WorkHrsWithoutBreak"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>21</td>
					<td><label class="set" name="break_duration_of_child"
						id="break_duration_of_child" ondblclick="callShowDiv(this);"><%=label.get("break_duration_of_child")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.BreakDuration"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>22</td>
					<td><label class="set" name="child_labours_max_hours"
						id="child_labours_max_hours" ondblclick="callShowDiv(this);"><%=label.get("child_labours_max_hours")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.MaxSpreadOverHrs"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>23</td>
					<td><label class="set" name="child_labour_work_after7"
						id="child_labour_work_after7" ondblclick="callShowDiv(this);"><%=label.get("child_labour_work_after7")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.NoOfChildLaboursWorkingEarlynLate"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>24</td>
					<td><label class="set" name="child_labour_holiday"
						id="child_labour_holiday" ondblclick="callShowDiv(this);"><%=label.get("child_labour_holiday")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.NumberOfHolidays" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>25</td>
					<td colspan="4"><label class="set" name="child_labour_wage"
						id="child_labour_wage" ondblclick="callShowDiv(this);"><%=label.get("child_labour_wage")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_hour" id="per_hour"
						ondblclick="callShowDiv(this);"><%=label.get("per_hour")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WagesPerHr" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_day" id="per_day"
						ondblclick="callShowDiv(this);"><%=label.get("per_day")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WagesPerDay" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_week" id="per_week"
						ondblclick="callShowDiv(this);"><%=label.get("per_week")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WagesPerWeek" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_month" id="per_month"
						ondblclick="callShowDiv(this);"><%=label.get("per_month")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.WagesPerMonth" size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>26</td>
					<td><label class="set" name="child_labour_safety"
						id="child_labour_safety" ondblclick="callShowDiv(this);"><%=label.get("child_labour_safety")%></label></td>
					<td colspan=3><s:checkbox id="actsChildLabour.SafetyNHealthInitiativesTaken"
						name="actsChildLabour.SafetyNHealthInitiativesTaken" /> <label class="set" name="yes/no"
						id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="child_labour_safety_Yes"
						id="child_labour_safety_Yes" ondblclick="callShowDiv(this);"><%=label.get("child_labour_safety_Yes")%></label></td>
					<td colspan=3></td>
				</tr>
				<tr class="clsTRBody">
					<td>27</td>
					<td><label class="set" name="child_labour_education"
						id="child_labour_education" ondblclick="callShowDiv(this);"><%=label.get("child_labour_education")%></label></td>
					<td colspan=3><s:checkbox id="actsChildLabour.ChildLaboursAttendSchool"
						name="actsChildLabour.ChildLaboursAttendSchool" /> <label class="set" name="yes/no"
						id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></td>
				</tr>
				<!--<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td colspan="4"><label class="set"
						name="child_labour_education" id="child_labour_education"
						ondblclick="callShowDiv(this);"><%=label.get("child_labour_education")%></label></td>
				</tr>
				--><tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_day_labour_education"
						id="per_day_labour_education" ondblclick="callShowDiv(this);"><%=label.get("per_day_labour_education")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.SchoolHrsPerDay"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="per_week_labour_education"
						id="per_week_labour_education" ondblclick="callShowDiv(this);"><%=label.get("per_week_labour_education")%></label></td>
					<td colspan=3><s:textfield name="actsChildLabour.SchoolHrsPerWeek"
						size="30" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set"
						name="child_labour_financial_other_assistance"
						id="child_labour_financial_other_assistance"
						ondblclick="callShowDiv(this);"><%=label.get("child_labour_financial_other_assistance")%></label></td>
					<td colspan=3><s:checkbox
						id="actsChildLabour.childLabourFinancialOtherAssistance"
						name="actsChildLabour.childLabourFinancialOtherAssistance" /> <label class="set"
						name="yes/no" id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0"
				onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
				cellspacing="2" class="clsTable" style="border: 1px solid #565656;"
				align="center">
				<tr class="clsTRPageCaption">
					<td colspan="5">Notices and Register</td>
				</tr>
				<tr class="clsTRBody">
					<td width="3%">28</td>
					<td><label class="set" name="child_labours_details"
						id="child_labours_details" ondblclick="callShowDiv(this);"><%=label.get("child_labours_details")%></label></td>
					<td width="30%" align="left"><s:checkbox
						id="actsChildLabour.NotifiedToLabourDept" name="actsChildLabour.NotifiedToLabourDept" /> <label
						class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="date_for_notice"
						id="date_for_notice" ondblclick="callShowDiv(this);"><%=label.get("date_for_notice")%></label></td>

					<td colspan="3" align="left" width="15%"><s:textfield
						name="NoticeDate" size="30"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_NoticeDate','dd-mm-yyyy');" /> <s:a
						href="javascript:NewCal('paraFrm_NoticeDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>


				</tr>
				<tr class="clsTRBody">
					<td>29</td>
					<td><label class="set" name="child_labours_work_details"
						id="child_labours_work_details" ondblclick="callShowDiv(this);"><%=label.get("child_labours_work_details")%></label></td>
					<td><s:checkbox id="actsChildLabour.ChildLabourRegisterMaintained"
						name="actsChildLabour.ChildLabourRegisterMaintained" /> <label class="set"
						name="yes/no" id="yes/no" ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></td>
				</tr>
			</table>
			<table width="100%" border="0" align="right">
				<tr>
					<td class="formtext" colspan="4">
					<table width="100%">
						<tr>
							<td width="78%" colspan="1"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

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
	
	function nextFun()  {
		/*alert("previousFun");*/
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	
	}
	
function saveandnextFun()  {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_childLabourSaveAndNextRecord.action';
		document.getElementById('paraFrm').submit();
	
	}function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}

onload();
function onload()

{

}
</script>