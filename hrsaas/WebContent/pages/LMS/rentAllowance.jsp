<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="99%" cellspacing="2" class="formbg" align="center">
		<s:hidden name="rentAllowanceBean.minimumHRAID" value="1" />
		<s:hidden name="rentAllowanceBean.hraReturnID" value="1" />
		<s:hidden name="pageToShow" value="gratuityrules" />
		<s:hidden name="previousPage" value="general_workforce" />
		<s:hidden name="orgId" />
		<s:hidden name="frequency" />
		<s:hidden name="fromPeriod" />
		<s:hidden name="toPeriod" />
		<s:hidden name="orgName" />
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">House
					Rent Allowance Act </strong></td>
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
					<div align="left"><s:select name="actList" list="actMap"
						size="1" headerKey="" headerValue="--Select Act--"
						cssStyle="width:150" theme="simple" /><input type="submit"
						value="Go To" class="token" onclick="return goTo();"></div>
					</td>

				</tr>
			</table>

			<!-- Heading Section (Start) -->
			<table width="100%" style="border: 1px solid #565656;">
				<!-- English heading section (Begin) -->
				<tr>
					<td align="center"><label class="set" name="hra.heading"
						id="hra.heading" ondblclick="callShowDiv(this);"><%=label.get("hra.heading")%></label>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><br>
					<label class="set" name="hra.heading2" id="hra.heading2"
						ondblclick="callShowDiv(this);"><%=label.get("hra.heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- English heading section (End) -->

				<!-- Marathi heading section (Begin) -->
				<tr>
					<td colspan="2" align="center"><label class="set"
						name="hra.marathi_heading1" id="hra.marathi_heading1"
						ondblclick="callShowDiv(this);"><%=label.get("hra.marathi_heading1")%>
					</label> <br>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><label
						class="set" name="hra.marathi_heading2" id="hra.marathi_heading2"
						ondblclick="callShowDiv(this);"><%=label.get("hra.marathi_heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- Marathi heading section (End) -->
			</table>
			<!-- Heading Section (End) --> <br />

			<!-- Start of Rent allowance Section -->
			<table width="100%" cellspacing="2"
				style="border: 1px solid #565656;" align="center">
				<tr>
					<td colspan="3"><label class="set"
						name="hra.rent_allowance_head" id="hra.rent_allowance_head"
						ondblclick="callShowDiv(this);"><%=label.get("hra.rent_allowance_head")%>
					</label></td>
				</tr>
				<tr>
					<td width="3%">16</td>
					<td width="66%"><label class="set"
						name="hra.employee_provide_accomodation"
						id="hra.employee_provide_accomodation"
						ondblclick="callShowDiv(this);"><%=label.get("hra.employee_provide_accomodation")%>
					</label></td>
					<td width="30%" align="left"><s:checkbox
						name="rentAllowanceBean.AccommodationProvided"
						id="rentAllowanceBean.AccommodationProvided" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set"
						name="hra.employee_provide_accomodation_yes"
						id="hra.employee_provide_accomodation_yes"
						ondblclick="callShowDiv(this);"><%=label.get("hra.employee_provide_accomodation_yes")%>
					</label></td>
					<td align="left"><s:checkbox
						name="rentAllowanceBean.FreeAccommodationProvided"
						id="rentAllowanceBean.FreeAccommodationProvided" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>17</td>
					<td><label class="set"
						name="hra.how_many_employee_paid_house_rent"
						id="hra.how_many_employee_paid_house_rent"
						ondblclick="callShowDiv(this);"><%=label.get("hra.how_many_employee_paid_house_rent")%>
					</label></td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.NoOfEmpHRAPaid" size="5" /></td>
				</tr>
				<tr>
					<td>18</td>
					<td><label class="set"
						name="hra.is_house_rent_allowance_calculated"
						id="hra.is_house_rent_allowance_calculated"
						ondblclick="callShowDiv(this);"><%=label.get("hra.is_house_rent_allowance_calculated")%>
					</label></td>
					<td align="left"><s:checkbox
						name="rentAllowanceBean.HRACalculatedAs5Percent"
						id="rentAllowanceBean.HRACalculatedAs5Percent" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set"
						name="hra.is_house_rent_allowance_calculated_no"
						id="hra.is_house_rent_allowance_calculated_no"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.is_house_rent_allowance_calculated_no")%>
					</label></td>
					<td align="left"><s:textarea
						name="rentAllowanceBean.HRACalculationFormula" cols="40" rows="2" /></td>
				</tr>
				<tr>
					<td>19</td>
					<td><label class="set" name="hra.highest_house_rent_allowance"
						id="hra.highest_house_rent_allowance"
						ondblclick="callShowDiv(this);"><%=label.get("hra.highest_house_rent_allowance")%>
					</label></td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.MaxHRAPaid" size="5" /></td>
				</tr>
				<tr>
					<td>20</td>
					<td><label class="set" name="hra.total_house_rent_allowance"
						id="hra.total_house_rent_allowance"
						ondblclick="callShowDiv(this);"><%=label.get("hra.total_house_rent_allowance")%>
					</label></td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.totalHRAPaid" size="5" /></td>
				</tr>
				<tr>
					<td>21</td>
					<td><label class="set"
						name="hra.house_rent_allowance_paid_before_the_month_head"
						id="hra.house_rent_allowance_paid_before_the_month_head"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.house_rent_allowance_paid_before_the_month_head")%>
					</label></td>
					<td align="left"><input type="radio"
						name="rentAllowanceBean.HRAPaymentMode" id="hraBeforeMonth"
						onclick="setRentAllowanceRadioValue(this);" value="B"> <label
						class="set" name="hra.house_rent_allowance_paid_before_the_month"
						id="hra.house_rent_allowance_paid_before_the_month"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.house_rent_allowance_paid_before_the_month")%>
					</label> <br>
					<input type="radio" name="rentAllowanceBean.HRAPaymentMode"
						id="hraDurringMonth" onclick="setRentAllowanceRadioValue(this);"
						value="D"> <label class="set"
						name="hra.house_rent_allowance_paid_during_the_month"
						id="hra.house_rent_allowance_paid_during_the_month"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.house_rent_allowance_paid_during_the_month")%>
					</label> <br>
					<input type="radio" name="rentAllowanceBean.HRAPaymentMode"
						id="hraAfterMonth" onclick="setRentAllowanceRadioValue(this);"
						value="A"> <label class="set"
						name="hra.house_rent_allowance_paid_after_the_month"
						id="hra.house_rent_allowance_paid_after_the_month"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.house_rent_allowance_paid_after_the_month")%>
					</label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set"
						name="hra.house_rent_allowance_if_after_how_many_days"
						id="hra.house_rent_allowance_if_after_how_many_days"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.house_rent_allowance_if_after_how_many_days")%>
					</label> <br>
					</td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.AfterDays" size="5" /></td>
				</tr>
			</table>
			<!-- End of Rent allowance Section --> <br />

			<!-- Start of Disputes Section -->
			<table width="100%" border="0"
				onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
				cellspacing="2" class="clsTable" style="border: 1px solid #565656;"
				align="center">
				<tr>
					<td colspan="3"><label class="set" name="hra.dispute_header"
						id="hra.dispute_header" ondblclick="callShowDiv(this);"><%=label.get("hra.dispute_header")%>
					</label></td>
				</tr>
				<tr>
					<td width="3%">22</td>
					<td width="66%"><label class="set"
						name="hra.dispute_over_house_rent_allowance"
						id="hra.dispute_over_house_rent_allowance"
						ondblclick="callShowDiv(this);"><%=label.get("hra.dispute_over_house_rent_allowance")%>
					</label></td>
					<td width="30%" align="left"><s:checkbox
						name="rentAllowanceBean.HRADisputesReceived"
						id="rentAllowanceBean.HRADisputesReceived" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="hra.dispute_if_yes_how_many"
						id="hra.dispute_if_yes_how_many" ondblclick="callShowDiv(this);"><%=label.get("hra.dispute_if_yes_how_many")%>
					</label></td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.NoOfHRADisputes" size="23" /></td>
				</tr>
				<tr>
					<td>23</td>
					<td><label class="set"
						name="hra.dispute_workers_reprented_by_trade_union"
						id="hra.dispute_workers_reprented_by_trade_union"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.dispute_workers_reprented_by_trade_union")%>
					</label></td>
					<td align="left"><s:checkbox
						name="rentAllowanceBean.WorkersRepresentedByTU"
						id="rentAllowanceBean.WorkersRepresentedByTU" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set"
						name="hra.dispute_workers_reprented_by_trade_union_yes"
						id="hra.dispute_workers_reprented_by_trade_union_yes"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.dispute_workers_reprented_by_trade_union_yes")%>
					</label></td>
					<td align="left"><s:textfield
						name="rentAllowanceBean.TUName" size="23" /></td>
				</tr>
				<tr>
					<td>24</td>
					<td><label class="set" name="hra.dispute_over_rent_allowance"
						id="hra.dispute_over_rent_allowance"
						ondblclick="callShowDiv(this);"><%=label.get("hra.dispute_over_rent_allowance")%>
					</label></td>
					<td align="left"><input type="radio"
						name="rentAllowanceBean.DisputesResolutionMethod"
						id="rentAllowanceBean.hraNegotiation"
						onclick="setDisputeRadioValue(this);" value="N"> <label
						class="set" name="hra.dispute_over_rent_allowance_Negotiation"
						id="hra.dispute_over_rent_allowance_Negotiation"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.dispute_over_rent_allowance_Negotiation")%>
					</label> <br>
					<input type="radio" name="rentAllowanceBean.DisputesResolutionMethod"
						id="rentAllowanceBean.hraConciliation"
						onclick="setDisputeRadioValue(this);" value="C"> <label
						class="set" name="hra.dispute_over_rent_allowance_Conciliation"
						id="hra.dispute_over_rent_allowance_Conciliation"
						ondblclick="callShowDiv(this);"><%=label
										.get("hra.dispute_over_rent_allowance_Conciliation")%>
					</label> <br>
					<input type="radio" name="rentAllowanceBean.DisputesResolutionMethod"
						id="rentAllowanceBean.hraOther"
						onclick="setDisputeRadioValue(this);" value="O"> <label
						class="set" name="hra.dispute_over_rent_allowance_Other"
						id="hra.dispute_over_rent_allowance_Other"
						ondblclick="callShowDiv(this);"><%=label.get("hra.dispute_over_rent_allowance_Other")%>
					</label></td>
				</tr>
			</table>
			<!-- End of Disputes Section -->
			<table width="100%" cellspacing="2" align="center">
				<tr>
					<td class="formtext" colspan="3">
					<table width="100%">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<input type="text"
		name="rentAllowanceBean.rentAllowanceRadioOptionValue"
		id="rentAllowanceRadioOptionValue" />
	<input type="text" name="rentAllowanceBean.disputeRadioOptionValue"
		id="disputeRadioOptionValue" />
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
function setDisputeRadioValue(id){
	var opt=document.getElementById('disputeRadioOptionValue').value =id.value;	
}	

function setRentAllowanceRadioValue(id){
	var opt=document.getElementById('rentAllowanceRadioOptionValue').value =id.value;	
}

function saveandnextFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_rentAllowanceSaveAndNextRecords.action';
		document.getElementById('paraFrm').submit();
}
</script>


