<!-- Nilesh Dhandare -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="MinimumWagesAct" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" class="formbg">
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
			<td width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td class="formtext">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td colspan="1" align="center"><b>DEPARTMENT OF LABOUR</b></td>
				</tr>
				<tr>
					<td colspan="1" align="center"><b>The Minimum Wages Act,
					1948</b></td>
				</tr>
				<tr>
					<td colspan="1" align="center"><b>The Maharashtra Minimum
					Wages Rules, 1963</b></td>
				</tr>
				<tr>
					<td colspan="1" align="center"><b>Wages return for the
					month ended </b> <s:select name="ownership" headerKey="1"
						headerValue="-----Select------"
						list="#{'jan ':'January','feb':'February','mar':'March','apr':'April','may':'May','june':'June','july':'July','aug':'August','sept':'September','oct':'Octomber','nov':'November','dec':'December'}"
						cssStyle="width:100" /><s:textfield size="10" theme="simple"
						name="year" onfocus="clearText('paraFrm_year','Year')"
						onblur="setText('paraFrm_year','Year')" /></td>
				</tr>
				<tr>
					<td colspan="1" align="center"><b>This return is to be
					submitted each month by all establishments employing</b></td>
				</tr>
				<tr>
					<td colspan="1" align="center"><b>one or more persons.</b></td>
				</tr>
				<td>&nbsp;</td>

				</tr>



				<tr>
					<td class="formtext">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td align="left"><b>Minimum Wages Act<b></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td class="formtext">
					<table width="100" border="1" class="formbg">


						<tr>
							<td width="15">Registration No</td>
							<td width="12">Employee Name</td>
							<td width="10">Date of birth</td>
							<td width="10">Date of joining</td>
							<td width="15">Gender</td>
							<td width="10">Designation</td>
							<td width="15">Total days worked in month</td>
							<td width="18">Minimum wage applicable</td>
							<td width="15">Actual wage paid</td>
							<td width="15">Piece rate wages paid</td>
							<td width="15">Overtime hours worked</td>
							<td width="15">Overtime pay rate</td>
							<td width="15">Overtime earnings</td>
							<td width="15">Allowances</td>
							<td width="15">Gross earnings payable</td>
							<td width="10">Deductions from gross earnings</td>
							<td width="15">Advances</td>
							<td width="15">PF</td>
							<td width="15">ESI</td>
							<td width="15">PT</td>
							<td width="15">IT</td>
							<td width="15">Welfare Board</td>
							<td width="15">Net earnings</td>
							<td width="15">Amount deposited in employee's bank or cheque
							issued</td>
							<td width="15">Name of employer's bank</td>
							<td width="15">Leave balance at end of previous month</td>
							<td width="15">Leave taken during the month</td>
							<td width="15">Leave balance at end of month</td>
						</tr>
					</table>

					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<!-- main table -->
</s:form>
<script type="text/javascript"></script>
<script>
	
function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'MinimumWagesAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}

onload();
	
	function onload(){
		setText('paraFrm_year','Year')
	  
	}



</script>
