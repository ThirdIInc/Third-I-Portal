<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<s:form action="SettlmentDetails" validate="true" id="paraFrm" theme="simple">
<s:hidden name="resignCode" value="%{resignCode}" />
<s:hidden name="settCode" />
<s:hidden name="settDtlCode" />
<s:hidden name="settFlag" />
<s:hidden name="lockFlag" />
<s:hidden name="status" />
<s:hidden name="myPage" />
<s:hidden name="empCode" value="%{empCode}"/>
<s:hidden name="empId"/>
<s:hidden name="settlementFlag"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head">
						<img	src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Settlement Details</strong></td>
					<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					<div align="right"><span class="style2">
						<font color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%">
				<tr>
					<td width="25%"><label class="set"
						name="employee" id="employee2" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td colspan="3">
						<s:textfield name="empToken" size="12" value="%{empToken}" readonly="true"/>&nbsp;
						<s:textfield name="empName"	size="76" value="%{empName}" readonly="true" /></td>
				</tr>
				<tr>
					<td><label class="set" name="branch"
						id="branch2" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td><s:textfield size="20"
						name="branch" value="%{branch}" readonly="true" /></td>
					<td><label class="set"
						name="designation" id="designation2"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%"><s:textfield size="20"
						name="desgn" value="%{desgn}" readonly="true" /></td>
				</tr>
				<!-- Added By Anantha lakshmi -->
				<tr>
					<td><label class="set"
						name="grade" id="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:
					</td>
					<td>
						<s:textfield name="cadreName" value="%{cadreName}" size="20" readonly="true" />
					</td>
					<td>
						<label	class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
					<td>
						<s:textfield name="dateOfJoin"size="20" value="%{dateOfJoin}" readonly="true" />
					</td>
				</tr>
				<!-- ended By Anantha lakshmi -->
				<tr>
					<td><label class="set"
						name="resignation.date" id="resignation.date2"
						ondblclick="callShowDiv(this);"><%=label.get("resignation.date")%></label><font
						color="red">*</font> :</td>
					<td><s:textfield size="20"
						name="resignDate" readonly="true" /></td>
					<td><label class="set"
						name="seperation.date" id="seperation.date"
						ondblclick="callShowDiv(this);"><%=label.get("seperation.date")%></label><font
						color="red">*</font> :</td>
					<td><s:textfield size="20" 	name="sepDate" readonly="true" /></td>
				</tr>
				<tr>
					<td><label class="set" name="period"
						id="period2" ondblclick="callShowDiv(this);"><%=label.get("period")%></label><font
						color="red">*</font> :</td>
					<td colspan="3"><s:textfield size="5"
						theme="simple" name="noticePeriod"
						onkeypress="return numbersOnly();" readonly="true" /><s:select
						theme="simple" name="noticePeriodStatus" cssStyle="width:67"
						list="#{'D':'Days','M':'Month'}" disabled="true" /><s:hidden
						name="noticeStatus" /> <s:hidden name="noticePeriodStatus" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- setting leave days -->
		<s:hidden name="isFlag"/>
		<s:if test="isFlag">
			<tr class="td_bottom_border">
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="100%" colspan="5"><STRONG>Leave encashment details</STRONG></td>
					</tr>
					<s:hidden name="leaveLength" />
					<tr>
						<td class="formth"><label class="set" name="sr.no" id="sr.no"
							ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
						<td class="formth"><label class="set" name="leave.type"
							id="leave.type" ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label></td>
						<td class="formth"><label class="set" name="available.leave"
							id="available.leave" ondblclick="callShowDiv(this);"><%=label.get("available.leave")%></label></td>
						<td class="formth"><label class="set" name="leave.days"
							id="leave.days" ondblclick="callShowDiv(this);"><%=label.get("leave.days")%></label></td>
						<td class="formth"><label class="set" name="encashed.amount"
							id="encashed.amount" ondblclick="callShowDiv(this);"><%=label.get("encashed.amount")%></label></td>
					</tr>
					<%!int y = 0;%>
					<%
							try {
							String[][] leaveDtl = (String[][]) request
							.getAttribute("values");
							System.out.println("centerChk=====" + leaveDtl.length);
							y = leaveDtl.length;
							if (leaveDtl.length > 0) {
								for (int k = 0; k < leaveDtl.length; k++) {
					%>
					<tr>
						<td width="5%" class="sortableTD"><%=k + 1%></td>
						<input type="hidden" name="leaveId" id="levId<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][4]) %>" />
						<td width="20%" class="sortableTD"><%=String.valueOf(leaveDtl[k][0])%>
						<input type="hidden" name="leaveName" id="leaveName<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][0]) %>" />
						
						</td>
						<td width="20%" align="center" class="sortableTD"><s:property
							value="<%= String.valueOf(leaveDtl[k][7]) %>" /> <input
							type="hidden" name="avlBal" id="avlb<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][7]) %>"
							style="text-align: right;" /></td>

						<td width="20%" align="right" class="sortableTD"><input
							type="text" maxlength="5" onblur="setValue(<%=k%>);" name="clBal"
							id="clb<%=k%>"
							onkeyup="replaceBlankWithZero(this);calculateAmt('<%=k%>');"
							onkeypress="return numbersWithDot();"
							value="<%= String.valueOf(leaveDtl[k][1]) %>"
							style="text-align: right;" /></td>
						<td width="20%" align="right" class="sortableTD"><input
							type="text" name="total" id="total<%=k %>"
							value="<%= String.valueOf(leaveDtl[k][3]) %>" 
							style="text-align: right;" onkeyup="sumLeaveType();sumNet();" /></td>
						<input type="hidden" name="amtPerDay" id="amtPerDay<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][5]) %>" />
						<input type="hidden" name="maxEncashAmt" id="maxEncashAmt<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][6]) %>" />
						<input type="hidden" name="maxEncashLmt" id="maxEncashLmt<%=k%>"
							value="<%= String.valueOf(leaveDtl[k][2]) %>" />
					</tr>
					<%
							}
							}
						} catch (Exception e) {
							e.printStackTrace();

						}

						//int totalAmt=
					%>
					<tr>
						<td align="right" colspan="4" height="22">Total Amount.</td>
						<td colspan="1" align="right" height="22"><s:textfield
							name="totalAmt" size="20" theme="simple" onkeyup="replaceBlankWithZero(this);sumNet();"
							cssStyle="text-align:right;margin-right:5px" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:hidden name="totalLeaveDays" />
		<!-- end setting leave days -->

		<!-- setting loan days -->
		<s:hidden name="loanFlag" />
		<s:if test="loanFlag">
			<tr class="td_bottom_border">
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="4"><STRONG>Loan Details</STRONG></td>
					</tr>
					<s:hidden name="length1" />
					<tr>
						<td class="formth" width="25%"><label class="set"
							name="sr.no" id="sr.no1" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
						<td class="formth"><label class="set"
							name="loan.type" id="loan.type" ondblclick="callShowDiv(this);"><%=label.get("loan.type")%></label></td>
						<td class="formth"><label class="set"
							name="loan.amount" id="loan.amount"
							ondblclick="callShowDiv(this);"><%=label.get("loan.amount")%></label></td>
						<td class="formth" width="25%"><label class="set"
							name="loan.total" id="loan.total" ondblclick="callShowDiv(this);"><%=label.get("loan.total")%></label></td>
					</tr>
					<%!int i = 0;%>
					<%
							try {
							String[][] loanDtl = (String[][]) request
							.getAttribute("loanValues");
							System.out.println("loanDtl length=====" + loanDtl.length);
							i = loanDtl.length;
							if (loanDtl.length > 0) {
								for (int k = 0; k < loanDtl.length; k++) {
					%>
					<tr>
						<td class="sortableTD"><%=k + 1%></td>
						<input type="hidden" name="applCode" id="applCode<%=k%>"
							value="<%= String.valueOf(loanDtl[k][5]) %>" />
						<td class="sortableTD"><%=String.valueOf(loanDtl[k][0])%>
						<input type="hidden" name="loanType" id="loanType<%=k%>"
							value="<%= String.valueOf(loanDtl[k][0]) %>" />
						</td>

						<td align="center" class="sortableTD"><s:property
							value="<%= String.valueOf(loanDtl[k][1]) %>" /> 
							<input type="hidden" name="sancAmt" id="sancAmt<%=k%>"	value="<%= String.valueOf(loanDtl[k][1]) %>"
							style="text-align: right;" /></td>

						<td align="right" class="sortableTD" ><input
							type="text" maxlength="10" name="loanAmt" id="loanAmt<%=k%>"
							onkeyup="replaceBlankWithZero(this);calculateLoan('<%=k%>');"
							onkeypress="return numbersWithDot();"
							value="<%= String.valueOf(loanDtl[k][2]) %>"
							style="text-align: right;" /></td>
						<input type="hidden" name="debitCode" id="debitCode<%=k%>"
							value="<%= String.valueOf(loanDtl[k][4]) %>" />
					</tr>
					<%
							}
							}
						} catch (Exception e) {
							e.printStackTrace();

						}

						//int totalAmt=
					%>
					<tr>
						<td align="right" colspan="3" height="22">Total Amount.</td>
						<td align="right" height="22">
						<s:textfield name="totalLoanAmt" size="20" theme="simple"
							cssStyle="background-color: #F2F2F2;text-align:right;margin-right:5px"	readonly="true" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- end setting loan days -->
		
		<tr class="td_bottom_border">
				<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
						<tr>
							<td colspan="4"><STRONG>Gratuity Details</STRONG></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" name="service.tenure"
								id="service.tenure" ondblclick="callShowDiv(this);"><%=label.get("service.tenure")%></label>:&nbsp;</td>
							<td><s:textfield size="20" name="serviceTenure" cssStyle="text-align: right" value="%{serviceTenure}"
								onkeyup="replaceBlankWithZero(this);" onkeypress="return numbersWithDot();" /></td>
							<td><label class="set" name="gratAvgSalary"
								id="gratAvgSalary"  ondblclick="callShowDiv(this);"><%=label.get("gratAvgSalary")%></label>:&nbsp;</td>
							<td width="25%"><s:textfield size="20" cssStyle="text-align: right" name="gratuityAvgSalary" value="%{gratuityAvgSalary}"
								onkeyup="replaceBlankWithZero(this);" onkeypress="return numbersWithDot();" /></td>
						</tr>
						
						<tr>
							<td><label class="set" name="gratuity"
								id="gratuity" ondblclick="callShowDiv(this);"><%=label.get("gratuity")%></label>:&nbsp;</td>
							<td><s:textfield size="20"
								cssStyle="text-align: right" name="gratuity" value="%{gratuity}"
								onkeyup="replaceBlankWithZero(this);sumNet();"
								onblur="setGratuityValue();" onkeypress="return numbersWithDot();" /></td>
							<td colspan="2"><input type="button"  
								value="Calculate Gratuity" class="token" onclick="return calcGratuity();" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="3" class="formbg">
					<tr>
						<td width="25%"><label class="set"
							name="salary.amount" id="salary.amount2"
							ondblclick="callShowDiv(this);"><%=label.get("salary.amount")%></label>:</td>
						<td colspan="3"><s:textfield name="salaryAmt" size="20" cssStyle="background-color: #F2F2F2;text-align:right;"	readonly="true" /></td>
					</tr>
	
					<tr>
						<td>Leave Encashment Amount:</td>
						<td><s:textfield name="encashAmt"
							size="20" theme="simple" value="%{totalAmt}"
							cssStyle="text-align:right;margin-right:5px" onkeyup="replaceBlankWithZero(this);sumNet();"/></td>
						<td>Loan Amount:</td>
						<td><s:textfield name="loanAmtCh"
							size="20" theme="simple" value="%{totalLoanAmt}"
							cssStyle="background-color: #F2F2F2;text-align:right;margin-right:5px"
							readonly="true" /></td>
					</tr>
					<tr>
						<td><label class="set"
							name="reimbursements" id="reimbursements"
							ondblclick="callShowDiv(this);"><%=label.get("reimbursements")%></label>:&nbsp;</td>
						<td><s:textfield
							onblur="setReimbursementsValue();" size="20"
							cssStyle="text-align: right" name="reimburse" value="%{reimburse}"
							onkeyup="replaceBlankWithZero(this);sumNet();"
							onkeypress="return numbersWithDot();" /></td>
						<td><label class="set"
							name="other.deductions" id="other.deductions"
							ondblclick="callShowDiv(this);"><%=label.get("other.deductions")%></label>
						:</td>
						<td><s:textfield size="20"
							cssStyle="text-align: right" name="deduct" value="%{deduct}"
							onkeyup="replaceBlankWithZero(this);sumNet();"
							onkeypress="return numbersWithDot();" /></td>
					</tr>
					<!-- Added By Ganesh Start -->
					
					<tr>
						<td nowrap="nowrap"><label class="set" name="reimbursements.comments" id="reimbursements.comments"
							ondblclick="callShowDiv(this);"><%=label.get("reimbursements.comments")%></label>:&nbsp;</td>
						<td><s:textarea name="reimburseComments" cols="21"
							rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>
						<td nowrap="nowrap"><label class="set"
							name="other.deductions.comments" id="other.deductions.comments"
							ondblclick="callShowDiv(this);"><%=label.get("other.deductions.comments")%></label>
						:</td>
						<td><s:textarea name="deductComments" cols="21"	rows="2" onkeypress="return imposeMaxLength(event, this, 400);"/></td>
					</tr>
					
					<!-- Added By Ganesh End -->
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
					<tr>
						<td colspan="4"><STRONG>Outstanding Tax Amount</STRONG></td>
					</tr>
					
					
					<tr>
						<td width="25%">
							<label class="set" name="calctaxon" id="calctaxon"
							ondblclick="callShowDiv(this);"><%=label.get("calctaxon")%></label>
						</td>
						<td>
							<s:checkbox name="declaredInvChk" onclick="toggleCheck('D');"/>&nbsp;
							<label class="set" id="declaredInv" name="declaredInv" ondblclick="callShowDiv(this);"><%=label.get("declaredInv")%></label>
						</td>
						<td colspan="2">
							<s:checkbox name="verifiedInvChk" onclick="toggleCheck('V');"/>&nbsp;
							<label class="set" id="verifiedInv" name="verifiedInv" ondblclick="callShowDiv(this);"><%=label.get("verifiedInv")%></label>
						</td>
					</tr> 
					<tr>
						<td>
							<label class="set" name="tottax" id="tottax"
							ondblclick="callShowDiv(this);"><%=label.get("tottax")%></label>
						</td>
						<td><s:textfield size="20" name="taxValue" value="%{taxValue}"	cssStyle="text-align:right;"
							onkeyup="replaceBlankWithZero(this);sumNet();"	onkeypress="return numbersWithDot();" /></td>
						<td id="ctrlHide" align="center"><s:submit value="Calculate Tax" cssClass="token" action="SettlmentDetails_calculateTax" /></td>
						<td align="center">
							<a href="#" style="color:blue;" onclick="callVerifyInvestments();">Verify investments</a> 
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<s:hidden name="leaveDays" />
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="25%" colspan="1"><strong><label
							class="set" name="settelment.amount" id="settelment.amount2"
							ondblclick="callShowDiv(this);"><%=label.get("settelment.amount")%></label></strong>:</td>
						<td colspan="3">
							<s:textfield name="settleAmt" size="20" cssStyle="background-color: #F2F2F2;text-align:right;" readonly="true" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="4" nowrap="nowrap">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			</tr>
	</table>
</s:form>

<script type="text/javascript">
	formOnLoad();
	function formOnLoad(){
	/*Setting employee id variable to be used in investment verification*/
		document.getElementById('paraFrm_empId').value = document.getElementById('paraFrm_empCode').value
		document.getElementById('paraFrm_declaredInvChk').checked=true;
		document.getElementById('paraFrm_verifiedInvChk').checked=false;
	}
	
	function toggleCheck(chkStatus){
		if(chkStatus=='D'){
			document.getElementById('paraFrm_verifiedInvChk').checked= false;
		}
		if(chkStatus=='V'){
			document.getElementById('paraFrm_declaredInvChk').checked = false;
		}	
	}
	
	function callVerifyInvestments(){
		var resignDate = document.getElementById('paraFrm_resignDate').value;
		var applnStatus = document.getElementById('paraFrm_status').value;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="<%=request.getContextPath()%>/incometax/InvestmentVerification_input.action?resignDate="+resignDate+"&applnStatus="+applnStatus;
		document.getElementById('paraFrm').submit();
	}
	
	function setValue(k){
		if(document.getElementById("clb"+k).value=="")
			document.getElementById("clb"+k).value="0";
	}
	
	function setGratuityValue(){
		if(document.getElementById("paraFrm_gratuity").value=="")
			document.getElementById("paraFrm_gratuity").value="0";
	}
	
	function setReimbursementsValue(){
		if(document.getElementById("paraFrm_reimburse").value=="")
			document.getElementById("paraFrm_reimburse").value="0";
	}
	
	function calculateLoan(k){
		try{
		var loanAmt = parseFloat(document.getElementById("loanAmt"+k).value);
		document.getElementById('paraFrm_totalLoanAmt').value=roundNumber(eval(loanAmt),0);
		sumNet();
		}catch(e){
			alert(e);
		}
	}
	
	function calculateAmt(k){
	//alert("1.1");
		var balanceLeave = parseFloat(document.getElementById("clb"+k).value);
		var amtPerDay = document.getElementById("amtPerDay"+k).value;
		var total=document.getElementById("total"+k).value;
		var balLabel = document.getElementById('leave.days').innerHTML.toLowerCase();
		var availLabel = document.getElementById('available.leave').innerHTML.toLowerCase();
		var totAmount = document.getElementById("paraFrm_totalAmt").value;
		
		//alert("totAmount"+totAmount);
		try{
			var availableLeave = document.getElementById("avlb"+k).value;
		}catch(e){
			alert(e);
		}
		//var maxEncashLmt = parseFloat(document.getElementById("maxEncashLmt"+k).value);	
		try{
		/**
		Condition removed by Lakkichand
		*/
			if(eval(balanceLeave)>eval(availableLeave)){
				//alert(balLabel +" cannot be greater than "+availLabel);
				//document.getElementById("clb"+k).value = availableLeave;
				//var result=eval(availableLeave)*eval(amtPerDay);
				//document.getElementById("total"+k).value=roundNumber(eval(result),2);
				 	var conf = confirm('The available balance is ' +eval(availableLeave)+ ' days.\n Do you want to encash more than '+eval(availableLeave)+' days?');
			if(conf){
			var leaveDay=document.getElementById("clb"+k).value;
				//var result=eval(balanceLeave)*eval(amtPerDay);
				//var result=eval(availableLeave)*eval(amtPerDay);
				var result=eval(leaveDay)*eval(amtPerDay);
			  document.getElementById("total"+k).value=roundNumber(eval(result),2);
				onload1();
				return true;
			}else{
			document.getElementById("clb"+k).value=document.getElementById("avlb"+k).value;
			var result=eval(availableLeave)*eval(amtPerDay);
			document.getElementById("total"+k).value=roundNumber(eval(result),2);
				return false;
				}
			}
		}catch(e){
			//alert(e);
		}
		
		if(balanceLeave==""){
			balanceLeave="0";
		}
		var result=eval(balanceLeave)*eval(amtPerDay);
		document.getElementById("total"+k).value=roundNumber(eval(result),2);
		//sumNet();
		//alert("totAmount 2--"+totAmount);
		onload1();
	}

	function onload1()	{
		try{
		var z = document.getElementById("paraFrm_leaveLength").value;
		}catch(e){
			alert(e);
		}
		var balanceLeave = 0;
		var balanceLeave1 = 0;
		var total=0;
		var total1=0;
		var totAmount=document.getElementById('paraFrm_totalAmt').value;
		for(var m=0 ; m < z ; m++){
			balanceLeave = document.getElementById("clb"+m).value;
			
			total =document.getElementById("total"+m).value ;
			
			total1=eval(total1)+eval(total);
			
			balanceLeave1=eval(balanceLeave1)+eval(balanceLeave);
			
		}
		//alert("totAmount 1--"+totAmount);
		try{
	  		document.getElementById('paraFrm_totalAmt').value=roundNumber(eval(total1),2);
	  		document.getElementById('paraFrm_totalLeaveDays').value=roundNumber(eval(balanceLeave1),2);
		}catch(e){
	  		alert('Onload1 :-'+e);
		}
		sumNet();
		
		//alert("totAmount 2--"+totAmount);
	}

	function sumNet(){
		try{
			var gratuity =parseFloat(document.getElementById('paraFrm_gratuity').value);
			var reimburse=parseFloat(document.getElementById('paraFrm_reimburse').value);
			var deduct=parseFloat(document.getElementById('paraFrm_deduct').value);
			var salAmt = document.getElementById('paraFrm_salaryAmt').value;
			var taxValue = 0;
			try{
			taxValue = document.getElementById('paraFrm_taxValue').value;
			}catch(e){
		 		taxValue = 0;
		 	}
			if(gratuity=="")
			{
				gratuity=0;
			}
			if(reimburse=="")
			{
				reimburse=0;
			}
			if(deduct=="")
			{
				deduct=0;
			}
			if(salAmt=="")
			{
				salAmt=0;
			}
			var leaveEncash=0;
		    try{
		    	leaveEncash=document.getElementById('paraFrm_totalAmt').value;
		 	}catch(e){}
		 	
		 	var loanAmount=0;
		 	try{
		    	loanAmount=document.getElementById('paraFrm_totalLoanAmt').value;
		 	}catch(e){}
		 	document.getElementById('paraFrm_encashAmt').value=eval(leaveEncash);
		 	document.getElementById('paraFrm_loanAmtCh').value=eval(loanAmount);
		 	
			var netP =0;
		  	var setVal =0;
			
		    var netValue = eval(gratuity)+ eval(reimburse)+ eval(leaveEncash) + eval(salAmt)
		    			- eval(deduct) - eval(loanAmount) - eval(taxValue);
			var result = eval(netValue);
			document.getElementById('paraFrm_settleAmt').value= roundNumber(eval(result),0);
			
		}catch(e) {
			//alert(e);   						 
		}
	}

	function saveandnextFun(){
		document.getElementById("paraFrm").action ='SettlmentDetails_saveAndNextThird.action';
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target = "main";
	}

	function saveFun(){
		document.getElementById("paraFrm").action ='SettlmentDetails_saveThird.action';
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target = "main";
	}

	function previousFun(){
		document.getElementById("paraFrm").action ='SettlmentDetails_previousThird.action';
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target = "main";
	}

	function saveandpreviousFun(){
		document.getElementById("paraFrm").action ='SettlmentDetails_saveAndPreviousThird.action';
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target = "main";
	}

	function nextFun(){
		document.getElementById("paraFrm").action ='SettlmentDetails_nextThird.action';
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target = "main";
	}

	function unlockFun() {
  		doAuthorisation('4', 'Settlement', 'U');
	}
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'SettlmentDetails_unLock.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}

	function deleteFun() {
		if(document.getElementById('paraFrm_settCode').value==""){
			alert("Please select "+document.getElementById('employee2').innerHTML.toLowerCase());
			return false;
		}
		var con=confirm('Do you really want to delete the record?')
		if(con){
			var del="SettlmentDetails_delete.action";
			document.getElementById('paraFrm').action=del;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = "main";
		} else{
		     return false;
		}
	}

	function replaceBlankWithZero(obj){
		if(obj.value==""){
			obj.value=0;
		}
	}

	function roundNumber(num, dec) {
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}

	function reportFun(){
		var settCode=document.getElementById('paraFrm_settCode').value;
		if(settCode=="" || trim(settCode.toString())=="null"){
			alert('Please Select a record to generate report');
			return false;
		}
		document.getElementById('paraFrm').target="_blank";
	  	document.getElementById('paraFrm').action="SettlmentDetails_report.action";
	  	document.getElementById('paraFrm').submit();  
	  	document.getElementById('paraFrm').target="main"; 
	}

	function calcGratuity(){
		document.getElementById('paraFrm').action="SettlmentDetails_calcGratuity.action"
		document.getElementById('paraFrm').submit();  
	}

	function sumLeaveType(){
		var z = document.getElementById("paraFrm_leaveLength").value;
		var total=0;
		var totalAmt=0;
		for(var m=0 ; m < z ; m++){
			
			total =document.getElementById("total"+m).value;
			totalAmt =eval(total)+eval(totalAmt);
		}
		document.getElementById('paraFrm_totalAmt').value=eval(totalAmt);
		
	}
	
	function imposeMaxLength(Event, Object, MaxLen){
	        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}	
</script>