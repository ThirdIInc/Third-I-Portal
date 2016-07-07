<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/payrollAjax.js"></script>
<%@include file="../common/commonValidations.jsp"%>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<script type="text/javascript">
	var eCode = new Array();
	
</script>

<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 50px; left: 250px;'></div>
<div id="confirmationDiv"
	style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
<div align="center" id="overlay"
	style="z-index: 3; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;">
</div>
<div id="progressBar"
	style="z-index: 3; position: absolute; width: 770px;">
<table width="100%">
	<tr>
		<td height="200"></td>
	</tr>
	<tr>
		<td align="center"><img src="../pages/images/ajax-loader.gif">
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Processing...</span>
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Please
		do not close the browser and do not click anywhere</span></td>
	</tr>
</table>
</div>
<s:form action="NonIndustrialSalary" id="paraFrm" theme="simple">

	<s:hidden name="nonIndustrialSalary.typeCode" />
	<s:hidden name="nonIndustrialSalary.payBillNo" />
	<s:hidden name="nonIndustrialSalary.branchCode" />
	<s:hidden name="nonIndustrialSalary.deptCode" />
	<s:hidden name="nonIndustrialSalary.divisionCode" />
	<s:hidden name="emptypeFlag" />
	<s:hidden name="paybillFlag" />
	<s:hidden name="branchFlag" />
	<s:hidden name="departmentFlag" />
	<s:hidden name="divisionFlag" />
	<s:hidden name="ledgerStatus" />
	<s:hidden name="attCode" />
	<s:hidden name="status" />
	<s:hidden name="processStatus" />
	<s:hidden name="recoveryFlag" />
	<s:hidden name="profHandiFLag" />
	<s:hidden name="incomeTaxFlag" />
	<s:hidden name="lwfFlag" />
	<s:hidden name="lwfDebitCode" />
	<s:hidden name="lwfCreditCode" />
	<s:hidden name="vpfFlag" />
	<table width="100%" class="formbg">
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"> <img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /> </strong></td>
			<td width="90%" class="txt"><strong class="text_head">Salary  
			Process</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td><s:if test="%{nonIndustrialSalary.viewFlag}">
				<input type="button" class="search" value="    Search "
					onclick="callsF9(500,325,'NonIndustrialSalary_f9ShowRecord.action');" />
			</s:if> <s:if test="%{nonIndustrialSalary.insertFlag}">

				<input type="button" class="token" theme="simple"
					onclick="return check();" value="Process" />

				<s:if test="debitHeader">
					<s:if test="%{nonIndustrialSalary.insertFlag}">
						<input type="button" class="save" theme="simple"
							onclick="return checkSave('Save');" value="    Save" />
							<s:if test="%{status == 'SAL_START'}">
								<input type="button" class="lock" theme="simple"
									onclick="return checkSave('Lock');" value="    Lock" /></s:if>
							<s:if test="%{status == 'SAL_FINAL'}">		
								<input type="button" class="unlock" theme="simple"
									onclick="return unlockSal();" value="    Unlock" /></s:if>	
					</s:if>
				</s:if>


				<s:submit action="NonIndustrialSalary_reset" value="    Reset"
					cssClass="reset" />

			</s:if></td>
			<td align="right"><font color="red">*</font>Indicates Required</td>
		</tr>
	</table>
	<table width="100%" class="formbg">
		<tr>
			<td width="7%">&nbsp;</td>
			<td colspan="4">
			<table width="100%">

				<tr>
					<td colspan="4">
					<table width="100%">
						<tr>
							<td width="50%" align="right"><label class="set" id="month"
								name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
								color="red">*</font>: <s:select theme="simple"
								name="nonIndustrialSalary.month" cssStyle="z-index:5;width:80"
								list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td>&nbsp;&nbsp;<label class="set" id="year" name="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font
								color="red">*</font>: <s:textfield
								name="nonIndustrialSalary.year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="7"
								onblur="return checkYear('paraFrm_nonIndustrialSalary_year', 'Year');" />
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<s:if test="%{divisionFlag}">
					<tr>
						<td width="15%">&nbsp;</td>
						<td width="22%"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.divisionName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Div.action');">
						</s:else></td>

					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="15%">&nbsp;</td>
						<td width="22%"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.divisionName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Div.action');">
						</s:else></td>

					</tr>
				</s:else>

				<s:if test="%{branchFlag}">
					<tr>
						<td width="15%">&nbsp;</td>
						<td width="22%"><label class="set" id="branch"
							name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.branchName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Branch.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="15%">&nbsp;</td>
						<td width="22%"><label class="set" id="branch"
							name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.branchName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Branch.action');">
						</s:else></td>
					</tr>
				</s:else>

				<s:if test="%{departmentFlag}">
					<tr>
						<td width="15%">&nbsp;</td>
						<td width="20%"><label class="set" id="department"
							name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						<font color="red">*</font>:</td>
						<td><s:textfield name="nonIndustrialSalary.deptName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Dept.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="15%">&nbsp;</td>
						<td width="20%"><label class="set" id="department"
							name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						<font color="red">*</font>:</td>
						<td><s:textfield name="nonIndustrialSalary.deptName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9Dept.action');">
						</s:else></td>
					</tr>
				</s:else>

				<s:if test="%{emptypeFlag}">
					<tr>
						<td width="16%">&nbsp;</td>
						<td width="22%"><label class="set" id="employee.type" name="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.typeName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9type.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="15%">&nbsp;</td>
						<td width="30%"><label class="set" id="employee.type" name="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.typeName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9type.action');">
						</s:else></td>
					</tr>
				</s:else>
				<s:if test="%{paybillFlag}">
					<tr>
						<td width="15%">&nbsp;</td>
						<td width="20%"><label class="set" id="pay.bill"
							name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.payBillName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9payBill.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="15%">&nbsp;</td>
						<td width="20%"><label class="set" id="pay.bill"
							name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
						<font color="red">*</font>:</td>

						<td><s:textfield name="nonIndustrialSalary.payBillName"
							theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9payBill.action');">
						</s:else></td>
					</tr>
				</s:else>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<s:if test="debitHeader">
					<tr>
						<td width="15%">&nbsp;</td>
						<td width="20%"><label class="set" id="empSearch"
							name="empSearch" ondblclick="callShowDiv(this);"><%=label.get("empSearch")%></label>
						:</td>
						<td><s:hidden name="nonIndustrialSalary.empSearchId" /><s:hidden
							name="nonIndustrialSalary.empSearchToken" /> <s:hidden
							name="nonIndustrialSalary.empSearchStatus" /> <s:textfield
							name="nonIndustrialSalary.empSearchName" theme="simple"
							readonly="true" maxlength="50" size="25" /> <s:if
							test="%{nonIndustrialSalary.generalFlag}">
						</s:if><s:else>
							<img src="../pages/images/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'NonIndustrialSalary_f9SearchEmp.action');">
						</s:else>&nbsp;&nbsp; <s:submit cssClass="token" theme="simple"
							action="NonIndustrialSalary_empSearch" value="Search"
							onclick="return searchEmp();" /></td>
					</tr>
				</s:if>

			</table>
			</td>
		</tr>

	</table>




	<s:hidden name="searchFlagRes" />
	<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
	<s:hidden name="hdProcess" id="hdProcess" value="%{hdProcess}" />
	<s:if test="debitHeader">
		<table width="100%" class="formbg">
			<tr>
				<td colspan="4">
				<%
					Object[][] rows = (Object[][]) request.getAttribute("rows");
							Object[][] c = (Object[][]) request
									.getAttribute("creditLength");
							Object[][] d = (Object[][]) request
									.getAttribute("debitLength");
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							int index = (Integer) request.getAttribute("index");
				%>
				<table width="98%">
					<tr>
						<td width="50%" align="center">
						<%
							if (pageNo != 1) {
						%> <a href="#" onclick="callPage('1');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
						<img src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 			if (totalPage <= 5) {
 				if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 	} else {
 					for (int z = 1; z <= totalPage; z++) {
 						if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 	} else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 	}
 					}
 				}
 			} else {
 				if (pageNo == totalPage - 1 || pageNo == totalPage) {
 					for (int z = pageNo - 2; z <= totalPage; z++) {
 						if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 	} else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 	}
 					}
 				} else if (pageNo <= 3) {
 					for (int z = 1; z <= 5; z++) {
 						if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 	} else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 	}
 					}
 				} else if (pageNo > 3) {
 					for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 						if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 	} else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 	}
 					}
 				}
 			}
 			if (!(pageNo == totalPage)) {
 				if (totalPage == 0) {
 				} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
							src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
							href="#" onclick="callPage('<%=totalPage%>');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 			}
 %>
						</td>

						<td align="right"><input type="button" class="token"
							onclick="checkRecord()" value="OnHold" /> <input type="button"
							class="token" onclick="checkRecord1()" value="Remove OnHold" />
						<s:if test="%{nonIndustrialSalary.viewFlag}">
							<input type="button" class="edit" onclick="checkRecordRecal();"
								value="   Recalculate" />
						</s:if></td>
					</tr>

				</table>

				<table id="thetable" cellspacing="2" cellpadding="2" width="98%">

					<tr>

						<td><input class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="8" value="Employee Id" /></td>
						<td><input class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px;"
							type="text" size="24" value="Employee Name" /></td>
						<td><input class="tokenPay" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="8" value="Salary Days" /></td>
						<s:iterator value="creditHeader">
							<td><input class="tokenPay" type="text" size="4"
								readonly="readonly"
								style="text-align: center; border: none; margin: 1px"
								value="<s:property value="creditName" />" /></td>
						</s:iterator>
						<td><input class="tokenPay" type="text" size="6"
							style="text-align: center; border: none; margin: 1px"
							value="Total Credit" /></td>
						<s:iterator value="debitHeader">
							<td><input class="tokenPay" type="text" size="4"
								readonly="readonly"
								style="text-align: center; border: none; margin: 1px"
								value="<s:property value="debitName" />" /></td>
						</s:iterator>
						<td><input class="tokenPay" type="text" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" size="6"
							value="Total Debit" /></td>
						<td><input class="tokenPay" type="text" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" size="6"
							value="Net Pay" /></td>
						<td><input class="classCheck" type="checkbox"
							name="selectId" id="selectId" onclick="return selectAll();" /> <!-- <input id="allClr" type="checkbox" checked="checked" onclick="doAll('clear');" style="display: none;"/>  -->
						</td>
					</tr>
					<%
						try {
									int colVal = rows[0].length - 4;
									int i = 0;
									for (int k = 0; k < index; k++) {

										System.out.println("salary days"
												+ rows[k][rows[0].length - 3] + "------k="
												+ k);
										System.out.println("attendance days"
												+ rows[k][rows[0].length - 1] + "------k="
												+ k);
					%>


					<%
						if (!(String.valueOf(rows[k][rows[0].length - 3])
												.equals(String
														.valueOf(rows[k][rows[0].length - 1])))) {
					%>

					<tr>
						<td><input type="hidden" name="onholdEmp"
							id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" />
						<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"
							id="<%=rows[k][0]%>"> <input
							style="background-color: #FF8383" type="text" readonly="readonly"
							size="8" name="tokenNo" value="<%=rows[k][1] %>"
							id='<%=rows[k][0]+"empToken"%>'></td>
						<td><input style="background-color: #FF8383" type="text"
							size="24" readonly="readonly" name="empName"
							value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'>

						</td>
						<td><input style="background-color: #FF8383" type="text"
							size="8" readonly="readonly" name="salDays<%=k%>"
							id='<%=rows[k][0]+"saldays"%>'
							value="<%=rows[k][rows[0].length-3] %>"></td>
						<%
							i = 3;
						%>
						<s:iterator value="creditHeader">

							<td><input
								style="background-color: #FF8383; text-align: right" type="text"
								size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
								id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
								onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)">
							</td>

							<%
								i++;
							%>
						</s:iterator>
						<td><input
							style="background-color: #FF8383; text-align: right" type="text"
							size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"
							name="totalCredit<%=k%>" value="<%=rows[k][i] %>"></td>
						<%
							int dStart = i;
						%>
						<s:iterator value="debitHeader">
							<%
								i++;
							%>
							<td align="center" width="40"><input
								style="background-color: #FF8383; text-align: right" type="text"
								size="4" name="<%=k%>" value="<%=rows[k][i] %>"
								id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
								onkeyup="sumDebits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)">
							</td>
						</s:iterator>
						<%
							int iVal = i;
												iVal++;
						%>
						<td><input
							style="background-color: #FF8383; text-align: right" type="text"
							size="6" readonly="readonly" name="totalDebit<%=k%>"
							value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>">
						</td>
						<%
							iVal++;
						%>
						<td><input
							style="background-color: #FF8383; text-align: right" type="text"
							size="6" readonly="readonly" name="netPay<%=k%>"
							value="<%=rows[k][i+2] %>" id="<%=rows[k][0]+"c"+iVal%>">
						</td>
						<td><input class="classCheck"
							style="background-color: #FF8383" type="checkbox"
							name="onHoldFlag" id='<%=rows[k][0]+"onHoldChk"%>'
							value="<%= String.valueOf(rows[k][0])%>" /></td>
					</tr>
					<script>
										creDebCount = "<%=iVal%>";
									</script>

					<%
						} else if (String.valueOf(
												rows[k][rows[0].length - 2]).equals("Y")) {
					%>

					<tr>
						<td><input type="hidden" name="onholdEmp"
							id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" />
						<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"
							id="<%=rows[k][0]%>"> <input
							style="background-color: #C0EDFE" type="text" readonly="readonly"
							size="8" name="tokenNo" value="<%=rows[k][1] %>"
							id='<%=rows[k][0]+"empToken"%>'></td>
						<td><input style="background-color: #C0EDFE" type="text"
							size="24" readonly="readonly" name="empName"
							value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'>

						</td>
						<td><input style="background-color: #C0EDFE" type="text"
							size="8" readonly="readonly" name="salDays<%=k%>"
							id='<%=rows[k][0]+"saldays"%>'
							value="<%=rows[k][rows[0].length-3] %>"></td>
						<%
							i = 3;
						%>
						<s:iterator value="creditHeader">

							<td><input
								style="background-color: #C0EDFE; text-align: right" type="text"
								size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
								id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
								onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)">
							</td>

							<%
								i++;
							%>
						</s:iterator>
						<td><input
							style="background-color: #C0EDFE; text-align: right" type="text"
							size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"
							name="totalCredit<%=k%>" value="<%=rows[k][i] %>"></td>
						<%
							int dStart = i;
						%>
						<s:iterator value="debitHeader">
							<%
								i++;
							%>
							<td align="center" width="40"><input
								style="background-color: #C0EDFE; text-align: right" type="text"
								size="4" name="<%=k%>" value="<%=rows[k][i] %>"
								id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
								onkeyup="sumDebits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)">
							</td>
						</s:iterator>
						<%
							int iVal = i;
												iVal++;
						%>
						<td><input
							style="background-color: #C0EDFE; text-align: right" type="text"
							size="6" readonly="readonly" name="totalDebit<%=k%>"
							value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>">
						</td>
						<%
							iVal++;
						%>
						<td><input
							style="background-color: #C0EDFE; text-align: right" type="text"
							size="6" readonly="readonly" name="netPay<%=k%>"
							value="<%=rows[k][i+2] %>" id="<%=rows[k][0]+"c"+iVal%>">
						</td>
						<td><input class="classCheck"
							style="background-color: #C0EDFE" type="checkbox"
							name="onHoldFlag" id='<%=rows[k][0]+"onHoldChk"%>'
							value="<%= String.valueOf(rows[k][0])%>" /></td>
					</tr>
					<script>
										creDebCount = "<%=iVal%>";
									</script>

					<%
						} else {
					%>

					<tr>
						<td><input type="hidden" name="onholdEmp"
							id="onholdEmp<%=k%>" value="<%=rows[k][rows[0].length-2] %>" />
						<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"
							id="<%=rows[k][0]%>"> <input
							style="background-color: #F2F2F2" type="text" size="8"
							readonly="readonly" name="tokenNo" value="<%=rows[k][1] %>"
							id='<%=rows[k][0]+"empToken"%>'></td>
						<td><input style="background-color: #F2F2F2" type="text"
							size="24" readonly="readonly" name="empName"
							value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'>

						</td>
						<td><input style="background-color: #F2F2F2" type="text"
							size="8" readonly="readonly" name="salDays<%=k%>"
							id='<%=rows[k][0]+"saldays"%>'
							value="<%=rows[k][rows[0].length-3] %>"></td>
						<%
							i = 3;
						%>
						<s:iterator value="creditHeader">

							<td><input type="text" size="4" name="<%=k%>"
								value="<%=String.valueOf(rows[k][i]) %>"
								id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersOnly();"
								onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)"
								style="text-align: right"></td>
							<%
								i++;
							%>
						</s:iterator>
						<td><input
							style="background-color: #F2F2F2; text-align: right" type="text"
							size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"
							name="totalCredit<%=k%>" value="<%=rows[k][i] %>"
							style="text-align: right"></td>
						<%
							int dStart = i;
						%>
						<s:iterator value="debitHeader">
							<%
								i++;
							%>
							<td><input type="text" size="4" name="<%=k%>"
								value="<%=rows[k][i] %>" id="<%=rows[k][0]+"c"+i%>"
								onkeypress="return numbersOnly();"
								onkeyup="sumDebits(<%=d.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=dStart+1%>,<%=d.length %>)"
								style="text-align: right"></td>
						</s:iterator>
						<%
							int iVal = i;
												iVal++;
						%>
						<td><input
							style="background-color: #F2F2F2; text-align: right" type="text"
							size="6" readonly="readonly" name="totalDebit<%=k%>"
							value="<%=rows[k][i+1] %>" id="<%=rows[k][0]+"c"+iVal%>"
							style="text-align: right"></td>
						<%
							iVal++;
						%>
						<td><input
							style="background-color: #F2F2F2; text-align: right" type="text"
							size="6" readonly="readonly" id="<%=rows[k][0]+"c"+iVal%>"
							name="netPay<%=k%>" value="<%=rows[k][i+2] %>"></td>
						<input type="hidden" name="onHoldChkHid" />
						<td><input class="classCheck" type="checkbox"
							style="background-color: #F2F2F2" name="onHoldFlag"
							id='<%=rows[k][0]+"onHoldChk"%>'
							value="<%= String.valueOf(rows[k][0])%>" /></td>
					</tr>
					<script>
										creDebCount = "<%=iVal%>";
									</script>

					<%
						}
					%>
					<script>
									eCode[<%=k%>] = "<%=String.valueOf(rows[k][0])%>";
									
								</script>
					<%
						}
								} catch (Exception e) {

								}
					%>
					<s:if test="searchFlagRes">
						<script>
							    var empCode = document.getElementById('paraFrm_nonIndustrialSalary_empSearchId').value;
						   		var found = false;
						   		for(var i = 0; i < eCode.length; i++)
						   		{
						   			if(empCode == eCode[i])
						   			{
								   		document.getElementById(empCode+"saldays").style.background = "#FDFBB0";
										document.getElementById(empCode+"empToken").style.background = "#FDFBB0";
										document.getElementById(empCode+"empName").style.background = "#FDFBB0";
										document.getElementById(empCode+"onHoldChk").style.background = "#FDFBB0";
										for(var j =3;j<=creDebCount;j++){
												document.getElementById(empCode+"c"+j).style.background = "#FDFBB0";
										}
										
										found=true;
									}
								}
								if(!found)
						   		{
						   			alert('Employee Not Found!');
						   		}
									
								</script>
					</s:if>
				</table>

				</td>



			</tr>







			<tr>
				<td colspan="3"><img
					src="../pages/common/css/default/images/space.gif" width="5"
					height="4" /></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<!--<td colspan="3"><input name="Submit2" type="submit" class="add"
				value="  Add New" /> <input name="Submit222" type="submit"
				class="search" value="    Search" /></td>
		-->
			</tr>
		</table>
		<table width="100%" class="formbg">
			<tr>
				<td style="color: red;">Note:</td>
			</tr>
			<tr>
				<td style="color: red;">Records in Blue colour represent
				employees 'on hold'.</td>
			</tr>
			<tr>
				<td style="color: red;">Records in Red colour indicate change
				in eligible salary days. Please recalculate these records.</td>
			</tr>
		</table>
	</s:if>

</s:form>
<script type="text/javascript">
if(document.getElementById("paraFrm_ledgerStatus").value=="ATTN_UNLOCK"){
	alert("Attendance is unlocked. You cannot change the salary");
}

function callPage(id,totalPage){
var con;
var process=document.getElementById('hdProcess').value
 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
if(id=='P'){
var p=document.getElementById('hdPage').value;
id=eval(p)-1;
}

if(id=='N'){
var p=document.getElementById('hdPage').value;
id=eval(p)+1;
}

document.getElementById('hdPage').value=id;
if(process=="Pr")
{
	if(ledgerStatus=="SAL_FINAL" ||ledgerStatus=="ATTN_UNLOCK"){
		document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
		document.getElementById('paraFrm').submit();
	}
	
	else{
	 //con=confirm("Do you want to save the page and proceed to the next page");
	 document.getElementById("confirmationDiv").style.visibility = 'visible';
		 document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 	+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 	+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 	+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 	+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
	}
		
		
}
else{
	if(ledgerStatus=="SAL_FINAL" || ledgerStatus=="ATTN_UNLOCK"){
		document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
		document.getElementById('paraFrm').submit();
	}
	else{
		 //con=confirm("Do you want to save the page and proceed to the next page");
		 document.getElementById("confirmationDiv").style.visibility = 'visible';
		 document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 	+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 	+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 	+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 	+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
		
	
	}

}

gridScroll();

function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
}
function proceedWithSave(){
	try{
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	enableBlockDiv();
	document.getElementById('paraFrm').action="NonIndustrialSalary_saveNonIndustrialSalaries.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		alert(e);
	}
}
function proceedWithoutSave(){
	try{
	enableBlockDiv();
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		alert(e);
	}
}

function gridScroll(){
try{
enableBlockDiv(); 
myST = superTable("thetable", { fixedCols : 3,rightCols:2,viewCols:6
});
disableBlockDiv();
}
catch(e){
disableBlockDiv();
}

}




var cntExist=0;
function sumCredits(cLen,k,colLen,empId) {
		 
		
		var creditAmount=0;
		var debitAmount=0;
	var j=3;
	
	for(var i=0;i<cLen;i++){
		var values=document.getElementById(empId+"c"+j).value;
		
		if(values ==''){
 		 		values =0;
 		 }
 		 j++;
		creditAmount=eval(creditAmount)+eval((values*100)/100);
	}	
	debitAmount = document.getElementById(empId+"c"+eval(colLen-1)).value
	document.getElementById(empId+"c"+eval(cLen+3)).value=creditAmount;
	document.getElementById(empId+"c"+colLen).value=eval(creditAmount*100/100)-eval(debitAmount*100/100); 
		 
	}
	
function sumDebits(cLen,k,colLen,empId,id,dLen) {
			
		var creditAmount=0;
		var debitAmount=0;
	var j=id;
	
	for(var i=0;i<dLen;i++){
		var values=document.getElementById(empId+"c"+j).value;
		if(values ==''){
 		 		values =0;
 		 }
 		 j++;
		debitAmount=eval(debitAmount)+eval((values*100)/100);
	}	
	creditAmount = document.getElementById(empId+"c"+eval(id-1)).value
	
	document.getElementById(empId+"c"+eval(colLen-1)).value=debitAmount; 
	
	document.getElementById(empId+"c"+colLen).value=eval(creditAmount*100/100)-eval(debitAmount*100/100); 
		 
	}
 
 function checkSave(id){
 
 	 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
		}
		else{
			if(id=="Save"){
				enableBlockDiv();
				document.getElementById('paraFrm').action="NonIndustrialSalary_saveNonIndustrialSalaries.action";
				document.getElementById('paraFrm').submit();
			}
			if(id=="Lock"){
			 	con=confirm('Do you really want to lock the salary?');
			 	if(con){
			 	enableBlockDiv();
				document.getElementById('paraFrm').action="NonIndustrialSalary_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}
		}
 }
	function unlockSal() {
		doAuthorisation('7', 'Salary', 'U');
	}
	
	function doUnlock() {
		enableBlockDiv();
		document.getElementById('paraFrm').action="NonIndustrialSalary_unLockSalary.action";
		document.getElementById('paraFrm').submit();
	}

	function check()
	{
	try
	{
	 
	document.getElementById('hdProcess').value='Pr'
	
	 
	
		if(document.getElementById('paraFrm_nonIndustrialSalary_month').value == '0')
		{
			alert("Please select the "+document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_nonIndustrialSalary_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_nonIndustrialSalary_year').value == "")
		{
			alert("Please enter the "+document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_nonIndustrialSalary_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_emptypeFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_typeCode').value == "")
		{
			alert("Please select the "+document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_paybillFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_payBillNo').value == "")
		{
			alert("Please select the "+document.getElementById('billno').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_deptCode').value == "")
		{
			alert("Please select the "+document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_divisionCode').value == "")
		{
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_branchCode').value == "")
		{
			alert("Please select the "+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		
		
		enableBlockDiv();
		document.getElementById('paraFrm').action="NonIndustrialSalary_getNonIndustrialSalaries.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
		alert(e);
		}
		
		
 
 }


function callSelect(id)
{


document.getElementById("onHoldChkHid"+id).value=document.getElementById("onHoldChk"+id).value	

}

	function callChk(id){
   document.getElementById("pmChkId"+id).value='Y';
 }

function checkRecord()
	{
			
		var cnt = 0;
		 var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 
		try{
		if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else{
			
			var ledgerCode=document.getElementById("paraFrm_attCode").value;
			retrieveURLOnHold('NonIndustrialSalary_onholdSave.action?','paraFrm',ledgerCode);
			for(var i = 0; i < eCode.length; i++)
			{
			
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
					try{
						document.getElementById(eCode[i]+"saldays").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"empToken").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"empName").style.background = "#C0EDFE";
						document.getElementById(eCode[i]+"onHoldChk").style.background = "#C0EDFE";
						for(var j =3;j<=creDebCount;j++){
							document.getElementById(eCode[i]+"c"+j).style.background = "#C0EDFE";
						}
					}
					catch(e){
					//alert(e);
					}
					cnt =cnt +1;
					document.getElementById("onholdEmp"+i).value="Y";
				}
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
				
			}
			if(cnt==0)
			{	disableBlockDiv();
				alert("Please select atleast one employee");
				return false;
			}
		}
		
		document.getElementById("selectId").checked=false;
		
		}
		catch(e)
		{
			
		}
	}
	
	function checkRecord1()
	{
		var cnt = 0;
		var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		
		try{
			if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else{
			
			var ledgerCode=document.getElementById("paraFrm_attCode").value;
			retrieveURLOnHold('NonIndustrialSalary_onholdRemove.action?','paraFrm',ledgerCode);
			
			for(var i = 0; i < eCode.length; i++)
			{
			try{
			
			
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{		
					document.getElementById(eCode[i]+"saldays").style.background = "";
					document.getElementById(eCode[i]+"empToken").style.background = "";
					document.getElementById(eCode[i]+"empName").style.background = "";		
					document.getElementById(eCode[i]+"onHoldChk").style.background = "";
					for(var j =3;j<=creDebCount;j++){
							document.getElementById(eCode[i]+"c"+j).style.background = "";
					}
					
					cnt =cnt +1;
					document.getElementById("onholdEmp"+i).value="N";
				}
				}
				catch(e){
					//alert("in for"+e);
				}
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			
			}
			if(cnt==0)
			{	
				disableBlockDiv();
				alert("Please select atleast one employee");
				return false;
			}
		}
		
		document.getElementById("selectId").checked=false;
		
		}
		catch(e)
		{
			
		}
	}
	
	function checkRecordRecal()
	{
		var cnt = 0;
		var ledgerStatus = document.getElementById("paraFrm_ledgerStatus").value;
		try{
			if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
			for(var i = 0; i < eCode.length; i++)
			{
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
		}
		else{
			retrieveURLRecal('NonIndustrialSalary_recalSalary.action?','paraFrm');
			document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
			for(var i = 0; i < eCode.length; i++)
			{
				try{
					if(document.getElementById(eCode[i]+"onHoldChk").checked)
						{
							
							if(document.getElementById("onholdEmp"+i).value=="Y"){
								document.getElementById(eCode[i]+"saldays").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"empToken").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"empName").style.background = "#C0EDFE";
								document.getElementById(eCode[i]+"onHoldChk").style.background = "#C0EDFE";
								for(var j =3;j<=creDebCount;j++){
									document.getElementById(eCode[i]+"c"+j).style.background = "#C0EDFE";
								}
							}
							else{
								document.getElementById(eCode[i]+"saldays").style.background = "";
								document.getElementById(eCode[i]+"empToken").style.background = "";
								document.getElementById(eCode[i]+"empName").style.background = "";
								document.getElementById(eCode[i]+"onHoldChk").style.background = "";
								for(var j =3;j<=creDebCount;j++){
									document.getElementById(eCode[i]+"c"+j).style.background = "";
								}
							}
						}
					}
					catch(e){
				
					}
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
					cnt =cnt +1;
				}
				if(document.getElementById(eCode[i]+"onHoldChk").checked)
				{				
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
				}
			}
			if(cnt==0)
			{
				
				alert("Please select atleast one employee");
				return false;
			}
			
		}
		
		document.getElementById("selectId").checked=false;
		disableBlockDiv();
		}
		catch(e)
		{
			
		}
	}

	
	
	 function selectAll()
	       {
	      
	      	
	      if(document.getElementById("selectId").checked){  
		       for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked = true;
				}
	      }
	      else{
	      for(var i = 0; i < eCode.length; i++)
				{
					document.getElementById(eCode[i]+"onHoldChk").checked = false;
				}
	      }
	       
	       }
	  
	  function searchEmp(){
	  	var empCode=document.getElementById("paraFrm_nonIndustrialSalary_empSearchId").value;
	  		var empName=document.getElementById("paraFrm_nonIndustrialSalary_empSearchName").value;
	  	if(empCode==""){
	  		alert("Please select an Employee for searching");
	  		return false;
	  	}
	  	if(empName==""){
	  		alert("Please select an Employee for searching");
	  		return false;
	  	}
	  }
	  
	  /* ===================================================================================
	Function	: checkPincode
	Explanation : for blocking the screen (all frames with div) when request execution 
	prepared by : Venkatesh
   ===================================================================================*/
	function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
				document.getElementById("progressBar").style.visibility = "visible";
				document.getElementById("progressBar").style.display = "block";   
			}
			catch(e){
			}
	  }
	  
	  	/* ===================================================================================
		Function	: checkPincode
		Explanation : for disabling the blocking  screen (all frames ) when request execution completes
		prepared by : Venkatesh
	   ===================================================================================*/
	  function disableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";   
			}
			catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
			}
	  }
	 
</script>

