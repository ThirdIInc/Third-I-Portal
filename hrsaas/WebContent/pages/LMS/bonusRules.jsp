<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">

	<table width="100%" border="0" class="formbg"
		onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
		cellspacing="2" style="border: 0px solid #565656;" align="center">
		<s:hidden name="actsPaymentOfBonus.ReturnID" />
		<s:hidden name="actsPaymentOfBonus.PaymentOfBonusID"/>
		<s:hidden name="pageToShow" value="equalrenumeration" />
		<s:hidden name="previousPage" value="maternitybenefits" />
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
					<td width="93%" class="txt"><strong class="text_head">Payment
					of Bonus Act</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td class="formtext" colspan="4" >
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
		<tr class="clsTRBody">
			<td>
			<!-- Heading Section (Start) -->	
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				style="border: 1px solid #565656;" >				
			<!-- English heading section (Begin) -->	
				<tr>
					<td  align="center"><label  class = "set" name="bonus.heading"  id="bonus.heading" 
						ondblclick="callShowDiv(this);"><%=label.get("bonus.heading")%></label> 
						<s:property value="fromPeriod" /> - <s:property value="toPeriod" /> <br>	
						
					</td>
				</tr>
				<td>&nbsp;<br></td>
				<!-- English heading section (End) -->
				
				<!-- Marathi heading section (Begin) -->
				<tr>
					<td colspan="2" align="center">							
						<label  class = "set" name="bonus.marathi_heading1"  id="bonus.marathi_heading1" 
							ondblclick="callShowDiv(this);"><%=label.get("bonus.marathi_heading1")%>
						</label>					
						<br><s:property value="fromPeriod" /> - <s:property value="toPeriod" /><label  class = "set" name="bonus.marathi_heading2"  id="bonus.marathi_heading2" 
							ondblclick="callShowDiv(this);"><%=label.get("bonus.marathi_heading2")%>
						</label>
					</td>
				</tr>
				<td>&nbsp;<br></td>
				<!-- Marathi heading section (End) -->
					</td>
				</tr>
			</table>
			<!-- Heading Section (End) -->	
			
		
			<table width="100%" border="0" cellpadding="0" cellspacing="2">
				
				<tr>
					<td >
					<table width="100%" border="0"
						onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
						cellspacing="2" style="border: 1px solid #565656;" align="center">
						<tr class="clsTRPageCaption">
							<td colspan="8">Bonus eligibility</td>
						</tr>
						<tr >
							<td width="3%">&nbsp;</td>
							<td width="45%">&nbsp;</td>
							<td width="12%" align="left">&nbsp;<label class="set"
						name="male" id="male"
						ondblclick="callShowDiv(this);"><%=label.get("male")%></label></td>
							<td width="14%" align="left">&nbsp;<label class="set"
						name="female" id="female"
						ondblclick="callShowDiv(this);"><%=label.get("female")%></label></td>
							<td width="12%" align="left"><label class="set"
						name="total" id="total"
						ondblclick="callShowDiv(this);"><%=label.get("total")%></label></td>
						</tr>
						<tr class="clsTRBody">
							<td>&nbsp;19</td>
							<td><label class="set"
								name="total_no_employee" id="total_no_employee"
								ondblclick="callShowDiv(this);"><%=label.get("total_no_employee")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.totalEmployeesMale" /></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.NoOfEmployees_Female" /></td>
								
							<td align="left"><input name="text59" type="text" onkeypress="return numbersWithDot();"
								style="width: 50px;" /></td>
						</tr>
						<tr class="clsTRBody">
							<td>20</td>
							<td><label class="set"
								name="total_no_employee_pay" id="total_no_employee_pay"
								ondblclick="callShowDiv(this);"><%=label.get("total_no_employee_pay")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.EmpReceivingLT10K_Male" /></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.EmpReceivingLT10K_Female" /></td>
							<td align="left"><input name="text59" type="text" onkeypress="return numbersWithDot();"
								style="width: 50px;" /></td>
						</tr>
						<tr class="clsTRBody">
							<td>21</td>
							<td><label class="set"
								name="total_no_employee_working_days"
								id="total_no_employee_working_days"
								ondblclick="callShowDiv(this);"><%=label.get("total_no_employee_working_days")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple"
								name="actsPaymentOfBonus.EmpWorkingLT30Days_Male" /></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple"
								name="actsPaymentOfBonus.EmpWorkingLT30Days_Female" /></td>
							<td align="left"><input name="text522" type="text" onkeypress="return numbersWithDot();"
								style="width: 50px;" /></td>
						</tr>
						<tr class="clsTRBody">
							<td>22</td>
							<td><label class="set"
								name="total_no_employee_disqualified"
								id="total_no_employee_disqualified"
								ondblclick="callShowDiv(this);"><%=label.get("total_no_employee_disqualified")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.Disqualified_Male" /></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple"
								name="actsPaymentOfBonus.Disqualified_Female" /></td>
							<td align="left"><input name="text552" type="text"
								style="width: 50px;" /></td>
						</tr>
						<tr class="clsTRBody">
							<td>23</td>
							<td><label class="set"
								name="total_no_employee_eligible"
								id="total_no_employee_eligible" ondblclick="callShowDiv(this);"><%=label.get("total_no_employee_eligible")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.Eligible_Male" /></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.Eligible_Female" /></td>
							<td align="left"><input name="text586" type="text" onkeypress="return numbersWithDot();"
								style="width: 50px;" /></td>
						</tr>
						<tr class="clsTRBody">
							<td>24</td>
							<td><label class="set"
								name="total_no" id="total_no" ondblclick="callShowDiv(this);"><%=label.get("total_no")%></label></td>
							<td align="left"><s:textfield onkeypress="return numbersWithDot();"
								size="10" theme="simple" name="actsPaymentOfBonus.TotalEligible" /></td>
							
							
						</tr>
					</table><br>
					</td>
				</tr>
		
				<tr>
					<td colspan="5">
					<table width="100%" border="0" 
						onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
						cellspacing="2" style="border: 1px solid #565656;" align="center">
						<tr class="clsTRPageCaption">
							<td colspan="5">Bonus payment</td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1" width="2%">&nbsp;25</td>
							<td colspan="1" width="35%"><label class="set"
								name="percentage_rate" id="percentage_rate"
								ondblclick="callShowDiv(this);"><%=label.get("percentage_rate")%></label></td>
							<td colspan="3" align="left" width="30%"><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.percentageRate" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1"width="2%">&nbsp;</td>
							<td colspan="1" width="35%"><label class="set"
								name="percentageRateOfTheBonusPeriod" id="percentageRateOfTheBonusPeriod"
								ondblclick="callShowDiv(this);"><%=label.get("percentageRateOfTheBonusPeriod")%></label></td>
							<td colspan="3"align="left" width="30%"><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.percentageRateOfTheBonusPeriod" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1"width="2%">&nbsp;</td>
							<td colspan="1"><label class="set"
								name="flatRateBonusPeriod" id="flatRateBonusPeriod"
								ondblclick="callShowDiv(this);"><%=label.get("flatRateBonusPeriod")%></label></td>
							<td colspan="3"align="left" ><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.flatRateBonusPeriod" size="30" /></td>
						</tr>
						
						<tr class="clsTRBody">
							<td colspan="1">26</td>
							<td colspan="1"><label class="set"
								name="total_amount_of_bonus_payment"
								id="total_amount_of_bonus_payment"
								ondblclick="callShowDiv(this);"><%=label.get("total_amount_of_bonus_payment")%></label></td>
							<td  colspan="3"align="left" ><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.TotalBonusPaid" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">27</td>
							<td colspan="1"><label class="set"
								name="total_amount_of_advance_bonus"
								id="total_amount_of_advance_bonus"
								ondblclick="callShowDiv(this);"><%=label.get("total_amount_of_advance_bonus")%></label></td>
							<td colspan="3" align="left" ><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.AdvanceBonusPaid" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">28</td>
							<td colspan="1"><label class="set"
								name="advance_bonus_payment" id="advance_bonus_payment"
								ondblclick="callShowDiv(this);"><%=label.get("advance_bonus_payment")%></label></td>
							<td colspan="3" align="left" ><s:textfield onkeypress="return numbersWithDot();"
								name="actsPaymentOfBonus.NoOfEmpReceivingAdvBonus" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">29</td>
							<td colspan="1"><label class="set"
								name="date_of_bonus" id="date_of_bonus"
								ondblclick="callShowDiv(this);"><%=label.get("date_of_bonus")%></label></td>
															
							<td colspan="3" align="left" ><s:textfield
								name="actsPaymentOfBonus.BonusPaymentDate" size="30" onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_BonusPaymentDate','dd-mm-yyyy');"/>
								<s:a
								href="javascript:NewCal('paraFrm_BonusPaymentDate','DDMMYYYY');">
								<img src="../pages/common/css/default/images/Date.gif"
									width="16" height="16" border="0" />
							</s:a></td>
								
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">30</td>
							<td colspan="1"><label class="set"
								name="bonus_reason" id="bonus_reason"
								ondblclick="callShowDiv(this);"><%=label.get("bonus_reason")%></label></td>
							<td colspan="3" align="left" >
							<s:textarea
								name="actsPaymentOfBonus.ReasonsForNonPayment" cols="45" rows="5" />
							
							</td>
							
						</tr>
					</table><br>
					</td>
				</tr>
				<br>
				<tr>
					<td colspan="5">
					<table width="100%" border="0"
						onkeydown="return Dish_DisbaleBackButton(event);" cellpadding="0"
						cellspacing="2" style="border: 1px solid #565656;" align="center">
						<tr class="clsTRPageCaption">
							<td colspan="5">Ex gratia</td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1" width="2%">31</td>
							<td colspan="1" width="35%"><label class="set"
								name="exgratia_payment" id="exgratia_payment"
								ondblclick="callShowDiv(this);"><%=label.get("exgratia_payment")%></label></td>
							<td colspan="3" align="left" width="30%">
								<s:checkbox id="actsPaymentOfBonus.ExGratiaPaid"	name="actsPaymentOfBonus.ExGratiaPaid" />
							<label class="set"
								name="yes/no" id="yes/no"
								ondblclick="callShowDiv(this);"><%=label.get("yes/no")%></label>
							</td>
						</tr>
						
						<tr class="clsTRBody">
							<td colspan="1">29</td>
							<td colspan="1" width="30%"><label class="set"
								name="exgratiaPaymentYes" id="exgratiaPaymentYes"
								ondblclick="callShowDiv(this);"><%=label.get("exgratiaPaymentYes")%></label></td>
							<td colspan="3" align="left" width="15%"></td>
						</tr>
						
						
						<tr class="clsTRBody">
							<td colspan="1">&nbsp;</td>
							<td colspan="1" width="30%"><label class="set"
								name="percentage_of_exgratia" id="percentage_of_exgratia"
								ondblclick="callShowDiv(this);"><%=label.get("percentage_of_exgratia")%></label></td>
							<td colspan="3" align="left" width="15%"><s:textfield
								name="actsPaymentOfBonus.ExGratiaPercent" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">&nbsp;</td>
							<td colspan="1" width="30%"><label class="set"
								name="no_of_employee" id="no_of_employee"
								ondblclick="callShowDiv(this);"><%=label.get("no_of_employee")%></label></td>
							<td colspan="3" align="left" width="15%"><s:textfield
								name="actsPaymentOfBonus.NoOfEmp" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">&nbsp;</td>
							<td colspan="1" width="30%"><label class="set"
								name="amt_paid" id="amt_paid" ondblclick="callShowDiv(this);"><%=label.get("amt_paid")%></label></td>
							<td colspan="3" align="left" width="15%"><s:textfield
								name="actsPaymentOfBonus.ExGratiaAmount" size="30" /></td>
						</tr>
						<tr class="clsTRBody">
							<td colspan="1">&nbsp;</td>
							<td colspan="1" width="30%"><label class="set"
								name="payment_date" id="payment_date"
								ondblclick="callShowDiv(this);"><%=label.get("payment_date")%></label></td>
							<td colspan="3" align="left" width="15%"><s:textfield
								name="ExGratiaPaymentDate" size="30" onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_ExGratiaPaymentDate','dd-mm-yyyy');"/>
								<s:a
								href="javascript:NewCal('paraFrm_ExGratiaPaymentDate','DDMMYYYY');">
								<img src="../pages/common/css/default/images/Date.gif"
									width="16" height="16" border="0" />
							</s:a></td>
						</tr>
					</table>
					<table width="100%" border="0" align="right" >
				<tr>
					<td class="formtext" colspan="4">
					<table width="100%" >
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
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	
	}
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
	function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
function saveandnextFun()  {	
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_bonusPaymentSaveAndNextRecord.action';
		document.getElementById('paraFrm').submit();
	
	}


onload();
function onload()

{

}
</script>
