<!-- REEBA JOSEPH -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReturnAct" validate="true" id="paraFrm"
	target="main" theme="simple">

	<table width="100%" border="0" class="formbg">
		<tr>
			<td width="100%">
			<s:hidden name="orgId" /><s:hidden name="frequency" />
			<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
			<s:hidden name="pageToShow" value="" />
			<s:hidden name="previousPage" value="monthlyActs" />
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Minimum
					Wages Act</strong></td>
					<td width="3%" valign="middle" class="txt" align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>

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

		<tr>
			<td colspan="2">
			<table width="100%" border="0" style="border: 1px solid #565656;">
				<tr>
					<td align="center"><b>DEPARTMENT OF LABOUR</b></td>
				</tr>
				<tr>
					<td align="center"><b>The Minimum Wages Act, 1948</b></td>
				</tr>
				<tr>
					<td align="center"><b>The Maharashtra Minimum Wages Rules,
					1963</b></td>
				</tr>
				<tr>
					<td align="center"><b>Wages return for the month ended  
					<s:property value="fromPeriod" /></b></td>
				</tr>
				<tr>
					<td align="center"><b>This return is to be submitted each
					month by all establishments employing</b></td>
				</tr>
				<tr>
					<td align="center"><b>one or more persons.</b></td>
				</tr>
			</table
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100" border="0" style="border: 1px solid #565656;">
				<tr style="border: 1px solid #565656;">
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Registration No</b></td>
					<td width="12" align="center" style="border: 1px solid #565656;"><b>Employee Name</b></td>
					<td width="10" align="center" style="border: 1px solid #565656;"><b>Date of birth</b></td>
					<td width="10" align="center" style="border: 1px solid #565656;"><b>Date of joining</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Gender</b></td>
					<td width="10" align="center" style="border: 1px solid #565656;"><b>Designation</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Total days worked in month</b></td>
					<td width="18" align="center" style="border: 1px solid #565656;"><b>Minimum wage applicable</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Actual wage paid</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Piece rate wages paid</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Overtime hours worked</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Overtime pay rate</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Overtime earnings</b></td>
					<% 
					try{
					Object[] creditHead = (Object[])request.getAttribute("creditHead"); 
					for(int i = 0; i < creditHead.length; i++){ %>
						<td width="10" align="center" style="border: 1px solid #565656;"><b><%= creditHead[i] %></b></td>
					<%}
					}catch(Exception e){
						
					}
					%>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Allowances</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Gross earnings payable</b></td>
					<td width="10" align="center" style="border: 1px solid #565656;"><b>Deductions from gross earnings</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Advances</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>PF</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>ESI</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>PT</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>IT</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Welfare Board</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Net earnings</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Amount deposited in employee's
					bank or cheque issued</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Name of employer's bank</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Leave balance at end of previous
					month</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Leave taken during the month</b></td>
					<td width="15" align="center" style="border: 1px solid #565656;"><b>Leave balance at end of month</b></td>
				</tr>
				<%int count = 0; 
				Object[][] creditValues = null;
				try{
				creditValues = (Object[][])request.getAttribute("creditValues"); 
				}catch(Exception e){
					
				}
				%>
				<s:iterator value="minimumWages.minWagesRecord">
					<tr>
						<td width="15" class="sortableTD"><s:textfield name="registrationNo" size="5"/></td>
						<td width="12" class="sortableTD"><s:textfield
							name="employeeName" /></td>
						<td width="10" class="sortableTD"><s:property
							value="dateOfBirth" /></td>
						<td width="10" class="sortableTD"><s:property
							value="dateOfJoining" /></td>
						<td width="15" class="sortableTD"><s:property value="gender" /></td>
						<td width="10" class="sortableTD"><s:property
							value="designation" /></td>
						<td width="15" class="sortableTD"><s:textfield
							name="totalWorkingDays" size="5" cssStyle="text-align: right;"/></td>
						<td width="18" class="sortableTD"><s:textfield
							name="minimumWagesApplicable" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="actualWagesPaid" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="pieceRateWagesPaid" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="overTimeHrsWorked" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="overTimePayRate"  size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="overTimeEarnings"  size="5" cssStyle="text-align: right;"/></td>
						<%
							try{
							for(int j = 2; j < creditValues[0].length; j++){ %>
							<td width="10" class="sortableTD">
							<s:textfield id="crAmt<%=count%>" value="<%=String.valueOf(creditValues[count][j]) %>" 
							 size="5" cssStyle="text-align: right;"/>
							</td>
						<%	}
							}catch(Exception e){
								
							}
						%>
						<td width="15" class="sortableTD"><s:textfield
							name="totalAllowances" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="grossEarnings" size="5" cssStyle="text-align: right;"/></td>
						<td width="10" class="sortableTD"><s:textfield
							name="totalDeductions" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="advances" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="pf" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="esi" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="pt" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="it" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="lwb" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="netEarnings" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield
							name="amountDepositedInBank" size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:property
							value="employersBankName" /></td>
						<td width="15" class="sortableTD"><s:textfield name="leaveOpeningBalance" 
							size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="leavesTaken" 
							size="5" cssStyle="text-align: right;"/></td>
						<td width="15" class="sortableTD"><s:textfield name="leaveClosingBalance" 
							size="5" cssStyle="text-align: right;"/></td>
					</tr>
						<%count++; %>
				</s:iterator>
			</table>
			</td>
		</tr>

	</table>
	<!-- main table -->
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

function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}

onload();
	
	function onload(){
		setText('paraFrm_year','Year')
	  
	}



</script>
