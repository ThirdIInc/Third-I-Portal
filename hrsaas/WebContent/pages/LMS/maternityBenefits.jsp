<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">

	<table width="99%" border="0" class="formbg"
		onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
		cellspacing="2" style="border: 0px solid #565656;" align="center">
		<s:hidden name="actsMaternityBenefits.ReturnID" />
		<s:hidden name="actsMaternityBenefits.MaternityBenefitsID" />
		<s:hidden name="previousPage" value="gratuityrules" />
		<s:hidden name="pageToShow" value="bonusrules" />
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
					<td width="93%" class="txt"><strong class="text_head">Maternity
					Benefits Act </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="formtext" colspan="4">
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
			<td class="formtext"><!-- Heading Section (Start) -->
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				style="border: 1px solid #565656;">
				<!-- English heading section (Begin) -->
				<tr>
					<td align="center"><label class="set" name="maternity.heading"
						id="maternity.heading" ondblclick="callShowDiv(this);"><%=label.get("maternity.heading")%></label>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><br>

					</td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- English heading section (End) -->

				<!-- Marathi heading section (Begin) -->
				<tr>
					<td colspan="2" align="center"><label class="set"
						name="maternity.marathi_heading1" id="maternity.marathi_heading1"
						ondblclick="callShowDiv(this);"><%=label.get("maternity.marathi_heading1")%>
					</label> <br>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><label class="set" name="maternity.marathi_heading2"
						id="maternity.marathi_heading2" ondblclick="callShowDiv(this);"><%=label.get("maternity.marathi_heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- Marathi heading section (End) -->
				</td>
				</tr>
			</table>
			<!-- Heading Section (End) --> <br />
		
			<table width="100%" border="0"
				onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
				cellspacing="2" style="border: 1px solid #565656;" align="center">
				<tr class="clsTRBody">
					<td width="3%">14</td>
					<td width="45%"><label class="set"
						name="maternity_leave_period" id="maternity_leave_period"
						ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_period")%></label></td>
					<td width="25%" colspan="3" align="left"><s:checkbox
						id="actsMaternityBenefits.MaternityLeavesApplied"
						name="actsMaternityBenefits.MaternityLeavesApplied" /> <label
						class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="maternity_leave_period_reason"
						id="maternity_leave_period_reason" ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_period_reason")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.maternityLeavePeriodReason" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>15</td>
					<td><label class="set"
						name="maternity_leave_approved_benefits"
						id="maternity_leave_approved_benefits"
						ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_approved_benefits")%></label></td>
					<td colspan="3" align="left"><s:checkbox
						id="actsMaternityBenefits.AllApplicationsApproved"
						name="actsMaternityBenefits.AllApplicationsApproved" /> <label
						class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="appl_rejected" id="appl_rejected"
						ondblclick="callShowDiv(this);"><%=label.get("appl_rejected")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.NoOfRejectedApplications" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="why" id="why"
						ondblclick="callShowDiv(this);"><%=label.get("why")%></label></td>
					<td colspan="3" align="left"><s:textarea
						name="actsMaternityBenefits.ReasonsForRejection" cols="45" rows="5" /></td>
				</tr>
				<tr class="clsTRBody">
					<td>16</td>
					<td><label class="set"
						name="maternity_leave_period_medical_bonus"
						id="maternity_leave_period_medical_bonus"
						ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_period_medical_bonus")%></label>
					<br>
					</td>
					<td colspan="3" align="left"><s:checkbox
						id="actsMaternityBenefits.MedicalBonusPaid"
						name="actsMaternityBenefits.MedicalBonusPaid" />
					<label class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="amount_of_bonus"
						id="amount_of_bonus" ondblclick="callShowDiv(this);"><%=label.get("amount_of_bonus")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.MedicalBonusAmountPerPerson" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>17</td>
					<td><label class="set" name="maternity_leave_period_benefit"
						id="maternity_leave_period_benefit"
						ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_period_benefit")%></label></td>
					<td colspan="3" align="left"><s:checkbox
						id="actsMaternityBenefits.MaternityBenefitPaid"
						name="actsMaternityBenefits.MaternityBenefitPaid" /> <label
						class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set" name="maternity_benefit_payments"
						id="maternity_benefit_payments" ondblclick="callShowDiv(this);"><%=label.get("maternity_benefit_payments")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.TotalAmountOfMaternityBenefitPaid" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>18</td>
					<td><label class="set" name="maternity_days_calculation"
						id="maternity_days_calculation" ondblclick="callShowDiv(this);"><%=label.get("maternity_days_calculation")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.numofDays" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>19</td>
					<td><label class="set" name="highest_maternity_benefit_paid"
						id="highest_maternity_benefit_paid"
						ondblclick="callShowDiv(this);"><%=label.get("highest_maternity_benefit_paid")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.HighestMaternityBenefitPaid" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>20</td>
					<td><label class="set" name="lowest_maternity_benefit_paid"
						id="lowest_maternity_benefit_paid" ondblclick="callShowDiv(this);"><%=label.get("lowest_maternity_benefit_paid")%></label></td>
					<td colspan="3" align="left"><s:textfield
						name="actsMaternityBenefits.LowestMaternityBenefitPaid" size="16" onkeypress="return numbersWithDot();"/></td>
				</tr>
				<tr class="clsTRBody">
					<td>21</td>
					<td><label class="set" name="maternity_leave_dismissed"
						id="maternity_leave_dismissed" ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_dismissed")%></label></td>
					<td colspan="3" align="left"><s:checkbox
						id="actsMaternityBenefits.NoOfDismissedEmployees"
						name="actsMaternityBenefits.NoOfDismissedEmployees" /> <label
						class="set" name="yes/no" id="yes/no"
						ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label></td>
				</tr>
				<tr class="clsTRBody">
					<td>&nbsp;</td>
					<td><label class="set"
						name="maternity_leave_dismissed_reasons"
						id="maternity_leave_dismissed_reasons"
						ondblclick="callShowDiv(this);"><%=label.get("maternity_leave_dismissed_reasons")%></label></td>
					<td colspan="3" align="left"><s:textarea
						name="actsMaternityBenefits.DismissalReasons"
						cols="45" rows="5" /></td>
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
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function nextFun()  {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	
	}
	
function saveandnextFun()  {
		try
		{
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_maternityBenefitsSaveAndNextRecord.action';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert(e);
		}
	
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

