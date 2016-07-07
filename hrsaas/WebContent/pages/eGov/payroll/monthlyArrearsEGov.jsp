<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 70px; left: 250px;'></div>
<s:form action="MonthlyArrearsEGov" target="main" theme="simple"
	validate="true" id="paraFrm">
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Monthly
					Arrears</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
				<s:hidden name="divFlag" />
	<s:hidden name="typeFlag" /><s:hidden name="eArrMonth" />
	<s:hidden name="payBillFlag" />
	<s:hidden name="brnFlag" />
	<s:hidden name="deptFlag" /><s:hidden name="empToken" /> 
	<s:hidden name="empId" />
	<s:hidden name="proccessDate" />
	<s:hidden name="empChkFlag" />
	<s:hidden name="status" />
	<s:hidden name="eArrMonth" />
	<s:hidden name="refMonth" />
	<s:hidden name="salStatus" />
	<s:hidden name="joiningDaysFlag" /><s:hidden name="filterFlag" />
	<s:hidden name="arrCode" />
	<s:hidden name="divEsicZone" />
	<s:hidden name="payinSalFlag" />
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><!-- <input type="button" class="search"
						value=" Search"
						onclick="javascript:callsF9(500,325,'MonthlyArrearsEGov_f9Search.action');" />
					<s:submit cssClass="save" action="MonthlyArrearsEGov_saveArrears"
						onclick="return callValidateSave();" value=" Save" /> <s:submit
						cssClass="reset" value=" Reset" action="MonthlyArrearsEGov_reset" /> -->
						<jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> </td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="%{filterFlag}"></s:if><s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label
						class="set" id="monthArr" name="monthArr"
						ondblclick="callShowDiv(this);"><%=label.get("monthArr")%></label></strong></td>
				</tr>
				<tr>
					<td width="20%" height="22"><label class="set"
						id="reflecting.month" name="reflecting.month"
						ondblclick="callShowDiv(this);"><%=label.get("reflecting.month")%></label>
					<font color="red">*</font> :</td>
					<td width="30%"><s:hidden name="monthRef" /><s:select name="arrRefMonth" headerKey="0"
						headerValue="--Select--" cssStyle="width:125"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td width="15%"><label class="set" id="reflecting.year" name="reflecting.year"
						ondblclick="callShowDiv(this);"><%=label.get("reflecting.year")%></label> <font
						color="red">*</font> :</td>
					<td width="35%"><s:textfield size="4" theme="simple"
						name="arrRefYear" onkeypress="return numbersOnly();" maxlength="4" /></td>
				</tr>
				<s:hidden name="divCode" />
				<s:if test="%{divFlag}">
					<tr>
						<td width="20%"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
							color="red">*</font>:</td>
						<td><s:textfield name="divName" readonly="true"
							maxlength="50" size="30" /> <s:if test="flag" /> <s:else>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'MonthlyArrearsEGov_f9Div.action');">
						</s:else></td>
						<td width="20%"><label class="set" id="arrearPayType"
							name="arrearPayType" ondblclick="callShowDiv(this);"><%=label.get("arrearPayType")%></label><font
							color="red">*</font>:</td>
						<td width="30%"><s:select name="arrearPayType" headerKey="0"
						 cssStyle="width:125" list="#{'ADD':'Additive','DED':'Deductive'}" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="20%"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
							color="red">*</font>: <s:textfield name="divName" /></td>
					</tr>
				</s:else>
				<s:hidden name="brnCode" />
				<s:if test="%{brnFlag}">
					<tr>
						<td width="20%"><label class="set" id="branch" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
							color="red">*</font>:</td>
						<td><s:textfield name="brnName" readonly="true"
							maxlength="50" size="30" /> <s:if test="flag" /> <s:else>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'MonthlyArrearsEGov_f9Branch.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="20%"><label class="set" id="branch" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
							color="red">*</font>:<s:textfield name="brnName" /></td>
					</tr>
				</s:else>
				<s:hidden name="deptCode" />
				<s:if test="%{typeFlag}">
					<tr>
						<td width="20%"><label class="set" id="department"
							name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
							color="red">*</font>:</td>
						<td><s:textfield name="deptName" readonly="true"
							maxlength="50" size="30" /> <s:if test="flag" /> <s:else>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'MonthlyArrearsEGov_f9EmpType.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="20%"><label class="set" id="department"
							name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
							color="red">*</font>: <s:textfield name="deptName" /></td>
					</tr>
				</s:else>
				<s:hidden name="typeCode" />
				<s:if test="%{typeFlag}">
					<tr>
						<td width="20%"><label class="set" id="employee.type"
							name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label><font
							color="red">*</font>:</td>
						<td><s:textfield name="typeName" readonly="true"
							maxlength="50" size="30" /> <s:if test="flag" /> <s:else>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'MonthlyArrearsEGov_f9EmpType.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="20%"><label class="set" id="employee.typ"
							name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label><font
							color="red">*</font>: <s:textfield name="typeName" /></td>
					</tr>
				</s:else>
				<s:hidden name="payBillNo" />
				<s:if test="%{payBillFlag}">
					<tr>
						<td width="20%"><label class="set" id="pay.bill"
							name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label><font
							color="red">*</font>:</td>
						<td><s:textfield name="payBillName" readonly="true"
							maxlength="50" size="30" /> <s:if test="flag" /> <s:else>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="callsF9(500,325,'MonthlyArrearsEGov_f9PayBill.action');">
						</s:else></td>
					</tr>
				</s:if>
				<s:else>
					<tr style="display: none;">
						<td width="20%"><label class="set" id="billno" name="billno"
							ondblclick="callShowDiv(this);"><%=label.get("billno")%></label><font
							color="red">*</font>: <s:textfield name="payBillName" /></td>
					</tr>
				</s:else>
				<tr>
					<td width="20%" ><label class="set" id="pay.salary" name="pay.salary"
						ondblclick="callShowDiv(this);"><%=label.get("pay.salary")%></label> :</td>
					<td width="30%"><s:checkbox  name="payinSalFlagCheckBox" onclick="callCheckbox();" /></td>
					<td width="15%"></td>
					<td width="35%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="flag" />
		
		</s:else>
		
		
		<!-- ADDED BY REEBA BEGINS -->
		
		<!-- FILTER SECTION -->
		<s:if test="%{filterFlag}">
		
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					
					<td nowrap="nowrap" width="15%"><label class="set" id="reflecting.month"
						name="reflecting.month" ondblclick="callShowDiv(this);"><%=label.get("reflecting.month")%></label><font
						color="red"> *</font> :</td>
					<td width="20%">
					<s:property value="monthRef" /><s:hidden name="monthRef" />
					<s:hidden name="arrRefMonth" />
					<td width="15%"><label class="set" id="reflecting.year2"
						name="reflecting.year" ondblclick="callShowDiv(this);"><%=label.get("reflecting.year")%></label><font
						color="red"> *</font> :</td>
					<td width="20%"><s:property value="arrRefYear" /><s:hidden name="arrRefYear" /></td>
					<td width="15%"><label class="set" id="arrearPayType2" name="arrearPayType"
						ondblclick="callShowDiv(this);"><%=label.get("arrearPayType")%></label>
					:</td>
					<td width="20%"><s:property value="arrearPayTypeName" /><s:hidden name="arrearPayTypeName" />
					<s:hidden name="arrearPayType" /></td>
				</tr>
				<tr>
					<td width="15%" ><label class="set" id="pay.salary" name="pay.salary"
						ondblclick="callShowDiv(this);"><%=label.get("pay.salary")%></label> :</td>
					<td width="20%"><s:checkbox  name="payinSalFlagCheckBox" onclick="callCheckbox();" /></td>
					<td width="35%" colspan="2"></td>
					<td width="35%" colspan="2"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<s:hidden name="divCode" />
				<s:hidden name="branchViewId" />
				<tr>
					<td width="15%"><label id="division2" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red">*</font></td>
					<td width="35%"><s:textfield name="divName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="15%"><label id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="35%"  id="ctrlShow"><s:textfield name="branchViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the branch"
						onclick="callsF9(500,325,'MonthlyArrearsEGov_f9BranchView.action');"></td>
				</tr>
				<s:hidden name="departmentViewId" />
				<s:hidden name="employeeTypeViewId" />
				<tr>
					<td width="15%"><label id="department" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="departmentViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the department"
						onclick="callsF9(500,325,'MonthlyArrearsEGov_f9DepartmentView.action');"></td>
					<td width="15%"><label id="employee.type" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="employeeTypeViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the type of an employee"
						onclick="callsF9(500,325,'MonthlyArrearsEGov_f9EmployeeTypeView.action');">
					</td>
				</tr>
				<s:hidden name="payBillViewId" />
				<s:hidden name="empViewId" />
				<s:hidden name="empStatusView" />
				<s:hidden name="empTokenView" />
				<tr>
					<td width="15%"><label id="pay.bill" name="pay.bill"
						ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="payBillViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the pay bill group"
						onclick="callsF9(500,325,'MonthlyArrearsEGov_f9PayBillView.action');">
					</td>
					<td width="15%"><label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="empViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the employee"
						onclick="callsF9(500,325,'MonthlyArrearsEGov_f9employeeView.action');">
					</td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td width="35%" align="right" id="ctrlShow"><input type="button"
						value="Show Records" class="token" title="Show"
						onclick="return callEditableArrears();" /></td>
					<td width="15%"></td>
					<td width="35%"></td>
				</tr>
			</table>
			</td>
		</tr>

		</s:if>
		
		
		<s:hidden name="addFlag" />
		<s:if test="addFlag" >
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label
						class="set" id="monthArr1" name="monthArr"
						ondblclick="callShowDiv(this);"><%=label.get("monthArr")%></label>
					</strong></td>
				</tr>

				<tr>
					<td width="15%" height="22"><label class="set"
						id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font> :</td>
					<td width="30%" id="ctrlShow"> <s:textfield size="30" theme="simple"
						name="empName" readonly="true" /> <img
						src="../pages/images/search2.gif" width="16" height="16" id="ctrlShow"
						align="absmiddle" class="iconImage" onclick="callEmpValidate();" /></td>
					<td><label class="set" id="noDay" name="noDay"
						ondblclick="callShowDiv(this);"><%=label.get("noDay")%></label> <font
						color="red">*</font> :</td>
					<td><s:textfield name="arrDays" theme="simple" id="ctrlShow"
						onkeypress="return numbersWithDot();" maxlength="5" size="2" /></td>

				</tr>


				<tr>
					<td width="20%" height="22"><label class="set"
						id="arrForMonth" name="arrForMonth"
						ondblclick="callShowDiv(this);"><%=label.get("arrForMonth")%></label>
					<font color="red">*</font> :</td>
					<td width="25%"><s:select name="arrMonth" headerKey="0"
						headerValue="--Select--" cssStyle="width:125" id="ctrlShow"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td width="12%"><label class="set" id="arr.foryear" name="arr.foryear"
						ondblclick="callShowDiv(this);"><%=label.get("arr.foryear")%></label><font
						color="red">*</font> :</td>
					<td width="38%"><s:textfield size="4" theme="simple" id="ctrlShow"
						name="arrYear" onkeypress="return numbersOnly();" maxlength="4" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" width="100"><s:submit cssClass="add" id="ctrlShow"
						name="process" value=" Add" action="MonthlyArrearsEGov_processArrears"
						onclick="return callValidateAdd();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		</s:if>
		
		
		
		
		<%
			Object[][] rows = null;
			try {
				rows = (Object[][]) request.getAttribute("rows");
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
		<%
		if (rows != null && rows.length > 0) {
		%>
		<s:if test="debitHeader">
			<tr>
				<td colspan="3" valign="bottom" align="center" class="txt"><input
					type="text" size="75" style="visibility: hidden;"> <input
					type="button" class="token" value="Recalculate" id="ctrlShow"
					onclick="return recalculate();" action="MonthlyArrearsEGov_recalculate" />&nbsp;
				<s:submit cssClass="token" value="Remove" onclick="return remove();"
					action="MonthlyArrearsEGov_removeRecord" id="ctrlShow"/></td>
			</tr>
		</s:if>
		<%
		}
		%>
		<tr>
			<td colspan="3"><%!int j = 0, z = 0;%> <s:if test="debitHeader">
				<table id="thetable" cellspacing="2" cellpadding="2" class="formbg">
					<%
									try {
									Object[][] c = (Object[][]) request
									.getAttribute("creditLength");
									System.out.println("After credit");
									if (rows != null && rows.length > 0) {
										int colVal = rows[0].length - 3;
										int i = 0;
							%>
					<tr id="ctrlShow">
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="10" value="Employee Id" /></td>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="24" value="Employee Name" /></td>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="5" value="Period" /></td>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="10" value="Arrears Days" /></td>
						<s:iterator value="creditHeader">
							<td><input class="token" readonly="readonly"
								style="text-align: center; border: none; margin: 1px"
								type="text" size="4" value="<s:property	value="creditName" />" /></td>
						</s:iterator>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="6" value="Total Credit" /></td>
						<s:iterator value="debitHeader">
							<td><input class="token" readonly="readonly"
								style="text-align: center; border: none; margin: 1px"
								type="text" size="4" value="<s:property value="debitName" />" /></td>
						</s:iterator>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="6" value="Total Debit"></td>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="6" value="Net Pay"></td>
						<td><input class="classcheck"
							style="text-align: center; border: none; margin: 1px"
							type="checkbox" size="2" name="chkEmpAll" id="chkEmpAll"
							onclick="callChkAll();"></td>

					</tr>
					<%
							for (int k = 0; k < rows.length; k++) {
							%>
					<tr>
						<td id="ctrlShow"><input type="hidden" readonly="readonly" name="emp_id"
							value="<%=rows[k][0] %>" id='<%="empId"+k%>'> <input
							type="text" readonly="readonly" size="10" name="eToken"
							value="<%=rows[k][1] %> " style="background-color: #F2F2F2">
						</td>
						<td id="ctrlShow"><input type="text" size="24" name="eName"
							id="empName<%=k %>" value="<%=rows[k][2] %>" readonly="readonly"
							style="background-color: #F2F2F2"></td>
						<td id="ctrlShow"><input type="text" size="5" readonly="readonly"
							name="period" id='<%=rows[k][0]+"period"%>'
							style="background-color: #F2F2F2"
							value="<%=rows[k][rows[0].length-4] %>"> <input
							type="hidden" name="hMonth" id='<%="hMonth"+k%>'
							value="<%=rows[k][rows[0].length-3] %>" /> <input type="hidden"
							name="year" id='<%="hYear"+k%>'
							value="<%=rows[k][rows[0].length-2] %>"> <input
							type="hidden" name="eligDays" id='<%="eligDays"+k%>'
							value="<%=rows[k][rows[0].length-1] %>"> <input
							type="hidden" name="orgArrDays" id='<%="orgArrDays"+k%>'
							value="<%=rows[k][rows[0].length-5] %>"></td>
						<td id="ctrlShow"><input type="text" size="10" name="salDays"
							onkeyup="return callEditArrDay(<%=k %>);"
							onkeypress="return numbersWithDot();" maxlength="5" size="2"
							id='<%="saldays"+k%>' value="<%=rows[k][rows[0].length-5] %>"></td>
						<%
								i = 3;
								%>
						<s:iterator value="creditHeader">

							<td id="ctrlShow"><input type="text" size="4" name="<%=k%>"
								value="<%=String.valueOf(rows[k][i]) %>"
								id="<%=rows[k][0]+"c"+i+rows[k][rows[0].length-3]%>"
								onkeypress="return numbersOnly();" maxlength="8"
								onkeyup="sum(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=rows[k][rows[0].length-3] %>)"
								style="text-align: right"></td>
							<%
									i++;
									%>
						</s:iterator>
						<td id="ctrlShow"><input type="text" size="6" readonly="readonly"
							id="<%=rows[k][0]+"c"+i+rows[k][rows[0].length-3]%>"
							name="totalCredit" value="<%=rows[k][i] %>"
							style="text-align: right"></td>
						<s:iterator value="debitHeader">
							<%
									i++;
									%>
							<td id="ctrlShow"><input type="text" size="4" name="<%=k%>"
								value="<%=rows[k][i] %>"
								id="<%=rows[k][0]+"c"+i+rows[k][rows[0].length-3]%>"
								onkeypress="return numbersOnly();" maxlength="8"
								onkeyup="sum(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>,<%=rows[k][rows[0].length-3] %>)"
								style="text-align: right"></td>
						</s:iterator>
						<%
										int iVal = i;
										iVal++;
								%>
						<td id="ctrlShow"><input type="text" size="6" readonly="readonly"
							name="totalDebit" value="<%=rows[k][i+1] %>"
							id="<%=rows[k][0]+"c"+iVal+rows[k][rows[0].length-3]%>"
							style="text-align: right"></td>
						<%
								iVal++;
								%>
						<td id="ctrlShow"><input type="text" size="6"
							style="background-color: #F2F2F2" readonly="readonly"
							name="netPay" value="<%=rows[k][i+2] %>"
							id="<%=rows[k][0]+"c"+iVal+rows[k][rows[0].length-3]%>"></td>
						<td id="ctrlShow"><input type="checkbox" size="2"
							style="background-color: #F2F2F2" class="classCheck"
							name="chkEmp" id="chk<%=k%>"
							value="<%= rows[k][0]+"&"+rows[k][rows[0].length-3]+"&"+rows[k][rows[0].length-2]+"&"+rows[k][rows[0].length-5]+"&"+k%>"
							onclick="callChkEmp();"></td>
					</tr>
					<%
										j++; // Variable taken to count no. of rows in validations(Java Script)
										}

									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							%>
				</table>
			</s:if> <%
 	z = j;
 	j = 0;
 %>
			</td>
			
		</tr>
		
	</table>
</s:form>
<script><!--
if(document.getElementById('paraFrm_arrRefYear').value=='')
{
	setCurrentYear('paraFrm_arrRefYear');
	setCurrentYear('paraFrm_arrYear');
}
//alert(document.getElementById("paraFrm_payinSalFlag").value);
if(document.getElementById("paraFrm_payinSalFlag").value=="true"){
	document.getElementById("paraFrm_payinSalFlagCheckBox").checked=true;
}
gridScroll();
function gridScroll(){
myST = superTable("thetable", { fixedCols : 4,rightCols:2,viewCols:5
});

}
	function viewValidate(){
		var arrMonth = document.getElementById('paraFrm_arrRefMonth').value;
		var arrYear = document.getElementById('paraFrm_arrRefYear').value;
		if(arrMonth == 0)
		{
			alert("Please select "+document.getElementById('reflecting.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_arrMonth').focus();
			return false;
		}
		if(arrYear == "")
		{
			alert("Please enter "+document.getElementById('reflecting.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_arrYear').focus();
			return false;
		}
		return true;
	}
	
	function callEmpValidate()
	{
		if(document.getElementById('paraFrm_divFlag').value == 'true' && document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_typeFlag').value == 'true' && document.getElementById('paraFrm_typeCode').value == "")
		{
			alert("Please select the "+document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && document.getElementById('paraFrm_payBillNo').value == "")
		{
			alert("Please select "+document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_brnFlag').value == 'true' && document.getElementById('paraFrm_brnCode').value == "")
		{
			alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_deptFlag').value == 'true' && document.getElementById('paraFrm_deptCode').value == "")
		{
			alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		callsF9(500,325,'MonthlyArrearsEGov_f9Employee.action');
	}
	
	function callValidateAdd()
	{
		try{
			var rowCount = <%= z%>
			var arrMonth = document.getElementById('paraFrm_arrMonth').value;
			var arrYear = document.getElementById('paraFrm_arrYear').value;
			var refMon = document.getElementById('paraFrm_arrRefMonth').value;
			var refYr = document.getElementById('paraFrm_arrRefYear').value;
			if(document.getElementById('paraFrm_empId').value == "")
			{
				alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
				return false;
			}
			if(arrMonth == 0)
			{
				alert("Please select "+document.getElementById('arrForMonth').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrMonth').focus();
				return false;
			}
			if(arrYear == "")
			{
				alert("Please enter "+document.getElementById('arr.foryear').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrYear').focus();
				return false;
			}
	
			var arrDays = document.getElementById('paraFrm_arrDays').value;
			if(arrDays == "")
			{
				alert("Please enter "+document.getElementById('noDay').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrDays').focus();
				return false;
			}
			if(arrDays.lastIndexOf(".") == arrDays.length || arrDays.indexOf(".") == 0 
			   			|| arrDays.indexOf(".") != arrDays.lastIndexOf("."))
			{
				alert("Please enter "+document.getElementById('noDay').innerHTML.toLowerCase()+" properly");
				document.getElementById('paraFrm_arrDays').focus();
				return false;
			}
			if(eval(document.getElementById('paraFrm_arrDays').value) == 0)
			{
				alert(document.getElementById('noDay').innerHTML.toLowerCase()+" should be greater than zero");
				document.getElementById('paraFrm_arrDays').focus();
				return false;
			}
			if(refMon == 0)
			{
				alert("Please select "+document.getElementById('arrMonth').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrRefMonth').focus();
				return false;
			}
			
			if(refYr == "")
			{
				alert("Please enter "+document.getElementById('reflecting.year').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrRefYear').focus();
				return false;
			}
			if(arrYear == 0)
			{
				alert(document.getElementById('arr.foryear').innerHTML.toLowerCase()+" cannot be 0");
				document.getElementById('paraFrm_arrYear').focus();
				return false;
			}
			if(refYr == 0)
			{
				alert(document.getElementById('reflecting.year').innerHTML.toLowerCase()+" cannot be 0");
				document.getElementById('paraFrm_arrRefYear').focus();
				return false;
			}
			if(arrYear > refYr)
			{
				alert(document.getElementById('reflecting.year').innerHTML.toLowerCase()+" cannot be less than "+document.getElementById('arr.foryear').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrYear').focus();
				return false;
			}
			else if((refYr - arrYear) > 1)
			{
				alert("Please enter "+document.getElementById('arr.foryear').innerHTML.toLowerCase());
				document.getElementById('paraFrm_arrYear').focus();
				return false;
			}
			else if(arrYear == refYr)
			{
				if(eval(arrMonth) > eval(refMon))
				{
					alert("Please select "+document.getElementById('arrForMonth').innerHTML.toLowerCase());
					document.getElementById('paraFrm_arrMonth').focus();
					return false;
				}
				
			}
			if(!chkMonthDays('paraFrm_arrDays','paraFrm_arrMonth','paraFrm_arrYear'))
				return false;
			for(var k=0; k < rowCount; k++)
			{
				var empId = document.getElementById('empId'+k).value;
				var hMonth = document.getElementById('hMonth'+k).value;
				var hYear = document.getElementById('hYear'+k).value;
				if(document.getElementById('paraFrm_empId').value == empId
					&& document.getElementById('paraFrm_arrMonth').value == hMonth 
					&& document.getElementById('paraFrm_arrYear').value == hYear)
				{
					alert("Arrears for "+document.getElementById('paraFrm_empName').value+" for "+getMonth(eval(hMonth))+"-"+hYear
					+" has been already added.");
					return false;
				}
			}
		}catch(e)
		{
			//alert(e);
		}
		return true;
	}
	
	function callValidateSave() // THIS FUNCTION CAN BE USED FOR SAVING AND FOR VIEWING AS WELL
	{							// SO THIS IS ALSO CALLED ON VIEW RECORDS
		var rowCount = <%= z%>
		var refMonth = document.getElementById('paraFrm_arrRefMonth').value;
		var refyear = document.getElementById('paraFrm_arrRefYear').value;
		if(refMonth == 0)
		{
			alert("Please select "+document.getElementById('reflecting.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_arrRefMonth').focus();
			return false;
		}
		if(refyear == "")
		{
			alert("Please enter "+document.getElementById('reflecting.year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_arrRefYear').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divFlag').value == 'true' && document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_typeFlag').value == 'true' && document.getElementById('paraFrm_typeCode').value == "")
		{
			alert("Please select "+document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && document.getElementById('paraFrm_payBillNo').value == "")
		{
			alert("Please select "+document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_brnFlag').value == 'true' && document.getElementById('paraFrm_brnCode').value == "")
		{
			alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_deptFlag').value == 'true' && document.getElementById('paraFrm_deptCode').value == "")
		{
			alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_salStatus').value == 'L')
		{
			alert("Salary has been locked so monthly arrears cannot be modified.")
			return false;
		}
		for(j=0; j < rowCount; j++)
		{
			if(!callEditArrDay(j))
				return false;
			if(parseFloat(document.getElementById('saldays'+j).value)> parseFloat(document.getElementById('eligDays'+j).value))
			{
				alert("Maximum eligible days for "+document.getElementById('empName'+j).value+" are "+document.getElementById('eligDays'+j).value);
			    document.getElementById('saldays'+j).focus();
			    return false;
			}
		}
		for(j=0; j < rowCount; j++)
		{
			if(!callEditArrDay(j))
				return false;
			if(parseFloat(document.getElementById('orgArrDays'+j).value)!=parseFloat(document.getElementById('saldays'+j).value))
			{
				alert("Arrears days for "+document.getElementById('empName'+j).value+" have been changed. Please recalculate the record.");
			    document.getElementById('saldays'+j).focus();
			    return false;
			}
		} 
		return true;
	}

	function sum(cLen,k,colLen,empId,month) {
		var formElements=document.getElementsByName(k);
		var creditAmount=0;
		var debitAmount=0;
 	 	for (var i=formElements.length-1; i>=0; --i ){
 		 	if(i<cLen)
 		 	{
	 		 	var values=formElements[i].value;
	 		 	if(values ==''){
	 		 		values =0;
	 		 	}
			creditAmount=creditAmount+eval((values*100)/100);
		    }
		   else
		   {
				var values=formElements[i].value;
	 		 	if(values ==''){
	 		 		values =0;
	 		 	}
				debitAmount=debitAmount+eval((values*100)/100);
			} 		
 		}
  
 	//var totalCredit=totalCredit+k;
 	
 
	document.getElementById(empId+"c"+eval(cLen+3)+month).value=creditAmount;
	document.getElementById(empId+"c"+eval(colLen-4)+month).value=debitAmount; 
	document.getElementById(empId+"c"+(colLen-3)+month).value=eval(creditAmount*100/100)-eval(debitAmount*100/100); 
	}
	
	function getMonth(month)
	{
		switch (month)
		{
			case 1 : return "Jan";
			case 2 : return "Feb";
			case 3 : return "Mar";
			case 4 : return "Apr";
			case 5 : return "May";
			case 6 : return "Jun";
			case 7 : return "Jul";
			case 8 : return "Aug";
			case 9 : return "Sep";
			case 10 : return "Oct";
			case 11 : return "Nov";
			case 12 : return "Dec";
		}
	}
	
	function recalculate()
	{
		var rowCount = <%= z%>
		var flag = false;
		if(rowCount == 0)
		{
			alert("Please select the arrear record to recalculate");
			return false;
		}
		if(document.getElementById('paraFrm_salStatus').value == 'L')
		{
			alert("Salary has been locked so monthly arrears cannot be modified.")
			document.getElementById('chkEmpAll').checked = false;
			for(i=0; i < rowCount; i++)
				document.getElementById('chk'+i).checked = false;
			return false;
		}
		for(i=0; i < rowCount; i++)
		{
			if(document.getElementById('chk'+i).checked)
			{
				flag = true;
				break;
			}
		}
		if(!flag)
		{
			alert("Please select atleast one record to recalculate");
			return false;
		}
		
		try {
		for(j=0; j < rowCount; j++)
		{
			if(!callEditArrDay(j))
				return false;
			if(parseFloat(document.getElementById('saldays'+j).value)> parseFloat(document.getElementById('eligDays'+j).value))
			{
				alert("Maximum eligible days for "+document.getElementById('empName'+j).value+" are "+document.getElementById('eligDays'+j).value);
				document.getElementById('saldays'+j).value = document.getElementById('orgArrDays'+j).value;
			    document.getElementById('saldays'+j).focus();
			    return false;
			}
		}
		}catch(e)
		{
			alert(e);
		}
		
		retrieveURLMonArrearsRecal('MonthlyArrearsEGov_recalculate.action?','paraFrm');
		for(i=0; i < rowCount; i++)
			document.getElementById('chk'+i).checked = false;
		document.getElementById('chkEmpAll').checked = false;
		
		return false;
	}
	function callChkAll()
	{
		try{
		rowCount = <%= z %>
		var chkVal = true;
		if(document.getElementById('chkEmpAll').checked == false)
			chkVal = false;
		for(i = 0; i < rowCount; i++)
			document.getElementById('chk'+i).checked = chkVal;
		}
		catch(e){
			alert(e);
		}
	}
	
	function callChkEmp()
	{
		var flag = true;
		rowCount = <%= z %>
		for(i = 0; i < rowCount; i++)
		{
			if(document.getElementById('chk'+i).checked == false)
			{
				flag = false;
				break;
			}
		}
		document.getElementById('chkEmpAll').checked = flag;
	}
	
	function callEditArrDay(rowNo)
	{
		var empId = document.getElementById('empId'+rowNo).value;
		var month = document.getElementById('hMonth'+rowNo).value;
		var year = document.getElementById('hYear'+rowNo).value;
		var arrDays = document.getElementById('saldays'+rowNo).value;
		if(arrDays == "")
		{
			alert("Please enter arrears days");
			document.getElementById('saldays'+rowNo).focus();	
			return false;
		}
		
		if(arrDays.lastIndexOf(".") == arrDays.length || arrDays.indexOf(".") == 0 
			   			|| arrDays.indexOf(".") != arrDays.lastIndexOf("."))
		{
				alert("Please enter arrears days properly");
				document.getElementById('saldays'+rowNo).focus();
				return false;
		}
		if(!chkMonthDays('saldays'+rowNo,'hMonth'+rowNo,'hYear'+rowNo))
		{
			document.getElementById('saldays'+rowNo).focus();
			return false;
		}
		document.getElementById('chk'+rowNo).value = empId+"&"+month+"&"+year+"&"+arrDays+"&"+rowNo;
		return true;
	}
	
	function chkMonthDays(arrDaysId,arrMonthId,arrYearId)
	{
		var arrDays = document.getElementById(arrDaysId).value;
		var month = eval(document.getElementById(arrMonthId).value);
		var year = document.getElementById(arrYearId).value;
		if(month == 4 || month == 6 || month == 9 || month == 11)
		{
			if(arrDays > 30)
			{
				alert('Arrears days cannot exceed 30 days for '+getMonth(month));
				document.getElementById(arrDaysId).focus();
				return false;
			}
		}
		else if(month == 2)
		{
			if(year % 4 == 0)
			{
				if(arrDays > 29)
				{
					alert('Arrears days cannot exceed 29 days for '+getMonth(month));
					document.getElementById(arrDaysId).focus();
					return false;
				}
			}
			else if(arrDays > 28)
			{
				alert('Arrears days cannot exceed 28 days for '+getMonth(month));
				document.getElementById(arrDaysId).focus();
				return false;
			}
		}
		else
		{
			if(arrDays > 31)
			{
				alert('Arrears days cannot exceed 31 days for '+getMonth(month));
				document.getElementById(arrDaysId).focus();
				return false;
			}
		}
		return true;
	}
	
	function remove()
	{
		var rowCount = <%= z%>
		var flag = false;
		if(rowCount == 0)
		{
			alert("Please select the arrear record to remove");
			return false;
		}
		if(document.getElementById('paraFrm_salStatus').value == 'L')
		{
			alert("Salary has been locked so monthly arrears cannot be modified.")
			document.getElementById('chkEmpAll').checked = false;
			for(i=0; i < rowCount; i++)
				document.getElementById('chk'+i).checked = false;
			return false;
		}
		for(i=0; i < rowCount; i++)
		{
			if(document.getElementById('chk'+i).checked)
			{
				flag = true;
				break;
			}
		}
		if(!flag)
		{
			alert("Please select atleast one record to remove");
			return false;
		}
		if(!confirm('<%=label.get("confirm.delete")%>'))
			return false;
		return true;
	}
	
	function callEditableArrears(){
	
		document.getElementById('paraFrm').action='MonthlyArrearsEGov_viewRecords.action';
		document.getElementById('paraFrm').submit();
	}
	
	function saveFun(){
		try{
			if(!callValidateSave()){
				return false
			}
		}catch(e){
			//alert(e);
		}
		document.getElementById("paraFrm").target='_self';
		document.getElementById('paraFrm').action='MonthlyArrearsEGov_saveArrears.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun(){
		document.getElementById("paraFrm").target='_self';
		document.getElementById('paraFrm').action='MonthlyArrearsEGov_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function backFun(){
		document.getElementById("paraFrm").target='_self';
		document.getElementById('paraFrm').action='MonthlyArrearsEGov_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callCheckbox(){
	try{
		document.getElementById("paraFrm_payinSalFlag").value=document.getElementById("paraFrm_payinSalFlagCheckBox").checked;
		}catch(e){
			alert(e);
		}
	}
	
	
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
