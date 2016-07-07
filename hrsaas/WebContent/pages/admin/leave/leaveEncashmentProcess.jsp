 <%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveEncashmentProcess" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td valign="bottom" class="txt">
							<strong class="text_head">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="93%" class="txt">
							<strong class="text_head">Leave Encashment Process </strong>
						</td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		
		<s:hidden name="show" value="%{show}" />
		<s:hidden name="hiddencode" />
		<s:if test="listFlag">
			<s:hidden name="divName" />
			<s:hidden name="processingDate" />
			<s:hidden name="lockFlag" value="%{lockFlag}" />
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="formbg">
							<td width="20%" class="formtxt"></td>
						<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("pageNo");
						%>
						<!-- Paging implementation -->
							<td id="showCtrl" width="80%" align="right">
								<s:if test="modeLength">
									<b>Page:</b>
									<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'LeaveEncashmentProcess_input.action');">
										<img title="First Page" src="../pages/common/img/first.gif"	width="10" height="10" class="iconImage" /> 
									</a>&nbsp;
									<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'LeaveEncashmentProcess_input.action');">
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /> 
									</a>
									<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'LeaveEncashmentProcess_input.action');return numbersOnly();" />
									 of <%=totalPage%>
									<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'LeaveEncashmentProcess_input.action')">
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> 
									</a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'LeaveEncashmentProcess_input.action');">
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> 
									</a>
								</s:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">
						<tr class="sortableTD">
							<s:hidden name="myPage" id="myPage" />
							<td width="10%" valign="top" class="formth">
								<label name="serial.no" id="serial.no" ondblclick="callShowDiv(this);">
									<%=label.get("serial.no")%>
								</label>
							</td>
							<td width="15%" valign="top" class="formth">
								<label name="processDate" id="processDate1" ondblclick="callShowDiv(this);">
									<%=label.get("processDate")%>
								</label>
							</td>
							<td width="20%" valign="top" class="formth">
								<label name="division" id="division1" ondblclick="callShowDiv(this);">
									<%=label.get("division")%>
								</label>
							</td>
							<td width="10%" valign="top" class="formth">
								<label name="headcount" id="headcount" ondblclick="callShowDiv(this);">
									<%=label.get("headcount")%>
								</label>
							</td>
							<td width="15%"  valign="top" class="formth">
								<label name="totencamt" id="totencamt" ondblclick="callShowDiv(this);">
									<%=label.get("totencamt")%>
								</label>
							</td>
							<td width="15%" valign="top" class="formth">
								<label name="salmonthyear" id="salmonthyear" ondblclick="callShowDiv(this);">
									<%=label.get("salmonthyear")%>
								</label>
							</td>
							
							<td width="15%" valign="top" class="formth" nowrap="nowrap">
								<label class="set" name="status" id="status" ondblclick="callShowDiv(this);">
									<%=label.get("status")%>
								</label>
							</td>
						</tr>
						<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
						</s:if>
						<%
						int count = 0;
						%>
						<%!int d = 0;%>
						<%
							int t = 0;
							int cn = pageNo * 20 - 20;
						%>
						<s:iterator value="tableList">

						<tr class="sortableTD" align="center" 
						<%	if(count%2==0){ %>
							class="tableCell1" 
						<%	}else{	%> 
							class="tableCell2" 
						<%	}count++; %>
							onmouseover="javascript:newRowColor(this);"	title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
							ondblclick="javascript:callForEdit('<s:property  value="processCodeItt"/>');">
							
							<td class="sortableTD" width="" align="center" >
								<%=++cn%> <% ++t; %>
							</td>
							<td class="sortableTD" width="" align="center">
								<s:property value="processDateItt" />
							</td>
							<td class="sortableTD" width="" align="left" >
								<s:property value="divisionItt" />
							</td>
							<td class="sortableTD" width="" align="center" >
								<s:property value="headcount"/>
								</label>
							</td>
							<td class="sortableTD" width="" align="center" >
								<s:property value="totalEncashAmt"/>
								</label>
							</td>
							<td class="sortableTD" width="" align="left" >
								<s:property value="salmonthyear"/>
								</label>
							</td>
							
							<td class="sortableTD" width="" nowrap="nowrap" align="center">
								<s:property value="statusItt" />
							</td>

						</tr>
						</s:iterator>

						<tr>
							<td colspan="7" align="right">
								<strong>Total Records: </strong>
								<s:property value="totalRecords" />
							</td>
						</tr>
					<% d = t; %>
					</table>
				</td>
			</tr>
		</s:if>

		<s:if test="listFlag"></s:if>
		<s:else>
			<s:hidden name="myPage" />
			<tr>
				<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
						<tr>
							<td colspan="3" class="text_head">
								<strong class="forminnerhead">Select filters </strong>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
								<label name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
								<font color="red">*</font>:
							</td>
							<td colspan="1" width="30%">
								<s:hidden name="divCode" /> 
								<s:textfield name="divName" theme="simple" size="25" readonly="true" /> 
								<s:if test="imageFlag">
									<img id="ctrlHide" src="../pages/images/recruitment/search2.gif" 
										height="18" class="iconImage" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'LeaveEncashmentProcess_f9div.action');">
								</s:if>
								<s:else></s:else>
							</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="1" width="20%" height="22">
								<label name="branch" id="branch" ondblclick="callShowDiv(this);">
									<%=label.get("branch")%>
								</label>:
							</td>
							<td colspan="1" width="30%">
								<s:hidden name="branchCode" value="%{branchCode}" theme="simple" /> 
								<s:textarea cols="85" rows="1" name="branchName" theme="simple" readonly="true" /> 
							</td>
							<td>
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_branchName',200,250,'LeaveEncashmentProcess_f9center.action',event,'false','','right')">
								</s:if>
								<s:else></s:else>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
								<label class="set" id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);">
									<%=label.get("pay.bill")%>
								</label>:
							</td>
							<td width="30%" >
								<s:hidden name="payBillNo" /> 
								<s:textarea cols="85" rows="1" theme="simple" readonly="true" name="payBillName" />
							</td>
							<td>	
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'LeaveEncashmentProcess_f9payBill.action',event,'false','','right')">
								</s:if>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
								<label class="set" id="grade" name="grade" ondblclick="callShowDiv(this);">
									<%=label.get("grade")%>
								</label>:
							</td>
							<td colspan="1" width="30%"><s:hidden name="cadreCode" />
								<s:textarea cols="85" rows="1" theme="simple" readonly="true" name="cadreName" /> 
							</td>
							<td>	
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'LeaveEncashmentProcess_f9grade.action',event,'false','','right')">
								</s:if>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
								<label class="set" id="costcenter" name="costcenter" ondblclick="callShowDiv(this);">
									<%=label.get("costcenter")%>
								</label>:
							</td>
							<td colspan="1" width="30%">
								<s:hidden name="costCenterCode" />
								<s:textarea cols="85" rows="1" theme="simple" readonly="true" name="costCenterName" /> 
							</td>
							<td>	
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'LeaveEncashmentProcess_f9costCenter.action',event,'false','','right')">
								</s:if>
							</td>
						</tr>
						<tr>	
							<td colspan="1" width="20%">
								<label name="department" id="department" ondblclick="callShowDiv(this);">
									<%=label.get("department")%>
								</label>:
							</td>
							<td colspan="1" width="30%">
								<s:hidden name="deptCode" value="%{deptCode}" theme="simple" />
								<s:textarea cols="85" rows="1" name="deptName" theme="simple" readonly="true" />
							</td>
							<td>
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_deptName',200,250,'LeaveEncashmentProcess_f9dept.action',event,'false','','right')">
								</s:if>
								<s:else></s:else>
							</td>
						</tr>

						<!--
						<tr>	
							<td colspan="1" width="20%"><label name="employee.type"
								id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="empTypeCode"
								value="%{empTypeCode}" theme="simple" /> <s:textarea  cols="85" rows="1" 
								name="empTypeName" theme="simple" readonly="true" /> 
								
							</td>
							<td>
								<s:if test="imageFlag">
								<img src="../pages/images/search2.gif" class="iconImage"
									height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_empTypeName',200,250,'LeaveEncashmentProcess_f9type.action',event,'false','','right')">
								</s:if>
								<s:else></s:else>
							</td>
						</tr>
						-->
					
						<tr>
							<td colspan="1" width="20%">
								<label name="levtypeLbl" id="levtypeLbl" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label>
								<font color="red">*</font>:
							</td>
							<td colspan="1" width="30%">
								<s:hidden name="levCode" value="%{levCode}" theme="simple" />
								<s:textarea cols="85" rows="1" name="levType" theme="simple" readonly="true" /> 
							</td>
							<td>
								<s:if test="imageFlag">
									<img src="../pages/images/search2.gif" class="iconImage"
										height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_levType',200,250,'LeaveEncashmentProcess_f9ltypeaction.action',event,'false','','right')">
								</s:if>
								<s:else></s:else>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						<tr>
							<td colspan="1" width="20%" nowrap="nowrap">
								<label name="excessBal" id="excessBal" ondblclick="callShowDiv(this);"><%=label.get("excessBal")%></label>
								<font color="red">*</font>:
							</td>
							<td colspan="1" >
								<s:if test="imageFlag">
									<s:textfield name="greaterEncashBal" size="10" maxlength="3"
										cssStyle="text-align: right"
										onkeypress="return numbersWithDot();" />
								</s:if>
								<s:else>
									<s:textfield  readonly="true"
										name="greaterEncashBal" size="10" maxlength="5"
										cssStyle="text-align: right"
										onkeypress="return numbersWithDot();" />
								</s:else>
							</td>
							<td colspan="1" width="15%">
								<label name="processDate" id="processDate" ondblclick="callShowDiv(this);">
									<%=label.get("processDate")%>
								</label>:
							</td>
							<td colspan="1" width="30%">
								<s:textfield name="processingDate" value="%{processingDate}" theme="simple"
									size="10" readonly="true" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						<tr>
							<td colspan="4" class="text_head">
								<strong class="forminnerhead">Select Parameters : </strong>
							</td>  
						</tr>
						<tr>
							<td width="20%">
								<label name="sal.month" id="sal.month" ondblclick="callShowDiv(this);"><%=label.get("sal.month")%></label>
								<font color="red">*</font>:
							</td>
							<td width="">
								<s:select name="salarymonth" headerKey="0" 
									headerValue="--Select--" cssStyle="width:90"
									list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td width="15%">
								<label name="sal.year" id="sal.year" ondblclick="callShowDiv(this);"><%=label.get("sal.year")%></label>
								<font color="red">*</font>:
							</td>
							<td width="30%">
								<s:textfield name="salaryyear" size="10"
									cssStyle="text-align: right" maxlength="4"
									onkeypress="return numbersOnly();" />
							</td>
						</tr>
						<tr>
							<td colspan="1" class="text_head">
								<label name="leaveencashcrdcode" id="leaveencashcrdcode" ondblclick="callShowDiv(this);"><%=label.get("leaveencashcrdcode")%></label>
								<font color="red">*</font>:
							</td>
							<td colspan="3">
								<s:hidden name="creditCode"  /> 
								<s:textfield name="creditType" size="20" readonly="true" />
								<img id='ctrlHide' src="../pages/common/css/default/images/search2.gif"
									class="iconImage" height="15" align="absmiddle" width="16" 
									onclick="javascript:callsF9(500,325,'LeaveEncashmentProcess_f9credits.action');"/>
							</td>
							
						</tr>
						<tr>
							<td colspan="4" class="text_head">
								<s:checkbox name="salaryCheck" />
								<label name="includeSal" id="includeSal" ondblclick="callShowDiv(this);">
									<%=label.get("includeSal")%>
								</label>
							</td>
						</tr>
						<tr>					
							<td colspan="4" class="text_head">
								<s:if test="processCode==''">
									<s:checkbox name="deductIncomeTax" />
								</s:if>
								<s:else>
									<s:checkbox name="deductIncomeTax" disabled="true" />
								</s:else>
								<label name="deductIT" id="deductIT" ondblclick="callShowDiv(this);">
									<%=label.get("deductIT")%>
								</label>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<s:if test="addFlag">
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
						<tr>
							<td colspan="3" class="text_head">
								<strong class="forminnerhead">Add Employee</strong>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%">
								<label class="set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							</td>
							<td colspan="3" width="80%">
								<s:hidden name="employeeCode" value="%{employeeCode}" />
								<s:textfield readonly="true" size="15" name="employeeToken" />
								<s:textfield size="50" name="employeeName" label="%{getText('employeeName')}"
									readonly="true" /> 
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/search2.gif" width="16"
									height="15" border="0" onclick="javascript:callEmployee();" />
								<s:if test="addFlag">
									<s:submit name="add" cssClass="add"
										action="LeaveEncashmentProcess_addEmployee" value=" Add"
										onclick="return callAdd();" />
								</s:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="showFlag">
			<tr>
				<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">
						<tr>
							<td width="5%" class="formth" nowrap="nowrap">Sr No.</td>
							<td width="10%" class="formth" nowrap="nowrap">Employee Id</td>
							<td width="25%" class="formth" nowrap="nowrap">Employee Name</td>
							<td width="30%" class="formth" nowrap="nowrap">Leave Type</td>
							<td width="5%" class="formth" nowrap="nowrap">Available Balance</td>
							<td width="5%" class="formth" nowrap="nowrap">Encashable Leave</td>
							<td width="5%" class="formth" nowrap="nowrap">Encash Amount</td>
							<s:if test="deductIncomeTax">
							<td width="5%" class="formth" nowrap="nowrap">TDS</td>
							<td width="5%" class="formth" nowrap="nowrap">NET Amount</td>
							</s:if>
							<s:if test="addFlag">
							<td align="right" width="5%" class="formth" nowrap="nowrap">
								<input type="button" class="delete" value="Delete"	onclick="callForDelete()" /><br/>
								<input type="checkbox" id="deleteAll" onclick="deleteCheckAll(this);" />
							</td>
							</s:if>
						</tr>
						<% int i = 0; %>
						<%! int t=0; %>
						<s:iterator value="list">
						<tr class="sortableTD">
							<td width="5%" class="sortableTD" align="center" >
								<%=i + 1%>
								<input type="hidden" name="srNo" value="<%=i + 1%>" />
							</td>
							<td width="10%" class="sortableTD">
								<s:property value="empToken" />
								<s:hidden name="empToken" /> 
								<s:hidden name="employeeId" />
								<s:hidden name="empGender" />
							</td>
							<td width="25%" class="sortableTD">
								<s:property value="empName" />
								<s:hidden name="empName" />
							</td>
							<td width="30%" class="sortableTD">
								<s:property value="leaveName" />
								<s:hidden name="leaveCode" />
								<s:hidden name="leaveName" />
							</td>
							<td width="5%" align="center" class="sortableTD">
								<!-- <s:property value="availableBal" /> -->
								<input type="text" onkeypress="return numbersWithDot();" name="currentBal"
										value='<s:property value="currentBal"/>'
										id="currentBal<%=i%>" theme="simple" readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
										size="10" />
									
								<input type="hidden" onkeypress="return numbersWithDot();" name="availableBal"
										value='<s:property value="availableBal"/>'
										id="availableBal<%=i%>" theme="simple" readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
										size="10" />
							</td>
							<td width="5%" align="center" class="sortableTD">
								<!--<s:property value="noOfencashLeave" />-->
								<input type="text" onkeypress="return numbersWithDot();" name="noOfencashLeave"
										id="noOfencashLeave<%=i %>"
										value='<s:property value="noOfencashLeave"/>' theme="simple"
										style="text-align: right" onkeyup="calculate(<%=i%>);"
										size="10" /> 
								<input type="hidden" name="encashFormula" 
										value='<s:property value="encashFormula"/>' theme="simple" />
								<input type="hidden" name="addFlagItt"
										value='<s:property value="addFlagItt"/>' theme="simple" />
								<input type="hidden" name="hiddenEncashDays"
										id="hiddenEncashDays<%=i%>"
										value='<s:property value="hiddenEncashDays"/>' />
							</td>
							<td width="5%" align="center" class="sortableTD">
								<!--<s:property value="encashAmount" /> -->
								<input type="hidden"
										name="amtPerDay" id="amtPerDay<%=i%>"
										value='<s:property value="amtPerDay"/>' /> 
								<input type="text" name="encashAmount" id="encashAmount<%=i%>"
										value='<s:property value="encashAmount"/>' theme="simple"
										readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
											size="10" />
							</td>
							<s:if test="deductIncomeTax">
							<td width="5%" class="sortableTD" nowrap="nowrap">
								<input type="text" onkeypress="return numbersWithDot();" name="tds"
										value='<s:property value="tds"/>'
										id="tds<%=i%>" theme="simple" readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
										size="10" />								
							</td>
							<td width="5%" class="sortableTD" nowrap="nowrap">
								<input type="text" onkeypress="return numbersWithDot();" name="netAmount"
										value='<s:property value="netAmount"/>'
										id="netAmount<%=i%>" theme="simple" readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
										size="10" />
							</td>
							</s:if>
							<s:else>
								<input type="hidden" name="tds" value='<s:property value="tds"/>' 
										id="tds<%=i%>" />								
								<input type="hidden" name="netAmount" value='<s:property value="netAmount"/>'
										id="netAmount<%=i%>" />
							</s:else>
							<s:if test="addFlag">
							<td width="10%" align="center" class="sortableTD" name="remove" >
								<!--<input type="button" class="rowDelete"
										onclick="callForDelete('<%=i+1%>')" />
								--><input type="checkbox" name="deleteCheck" id="deleteCheck<%=i%>" onclick="callForDelete1('<%=i+1%>','<%=i%>')" />
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" value=""/> 
							</td>
							</s:if>
						</tr>
						<%	i++; %>
						</s:iterator>
						<%	t=i; %>
						<%	if (i == 0) { %>
						<tr>
							<td colspan="7" align="center">
								<font color="red"> No data to display</font>
							</td>
						</tr>
						<%	} %>
					</table>
				</td>
			</tr>
			<%	if (i > 0) { %>
			<tr>
				<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">
						<tr>
							<td colspan="6" align="right" >
								Total Employee Count = <b><%=i %></b> <br/>
								Total Encash Amount = <b>
								<label><s:property value="totalEncashAmt"  /></label>
								</b>								
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<% } %>
			</s:if>
		</s:else>
		<tr>
			<td width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>

	<s:hidden name="processCode" />
	<s:hidden name="lockFlag" />
	<s:hidden name="hiddenEdit" />
	<s:hidden name="addFlag" />
	<s:hidden name="isAddFlag" />
	<s:hidden name="monthView" />
</s:form>

<script><!--

if(document.getElementById('pageNoField') != null ){
	window.onload = document.getElementById('pageNoField').focus();
}
	
function calculateTotalEncash(){
	var total = 0;
	var elements = document.getElementsByName('encashAmount');
	for(i=0; i<elements.length; i++){
		if(isNaN(elements[i].value)){
		}else{
			total = total + parseFloat(elements[i].value);
		} 
	}
	if(document.getElementById('totalEncashAmount') != null ){
		document.getElementById('totalEncashAmount').innerHTML = total;
	}
}

calculateTotalEncash();	
	
function chkLock(){
	if(document.getElementById('paraFrm_lockFlag').value == 'LOCK'){
		var ele = document.getElementsByName('remove');
		for(i=0; i<ele.length; i++){
			ele[i].style.display="none";
		}		
	}
}	
	
chkLock();
	
function addnewFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_addNew.action';
	document.getElementById('paraFrm').submit();
}
	
function bankstatementFun(){
	var processCode=document.getElementById('paraFrm_processCode').value;			
	var linkSource11='<%=request.getContextPath()%>/leaves/LeaveEncashmentProcess_callforedit.action?processCode='+processCode;
	var month=document.getElementById('paraFrm_salarymonth').value;
	var year=document.getElementById('paraFrm_salaryyear').value;			
	var divName=document.getElementById('paraFrm_divName').value;
	var monthText=document.getElementById('paraFrm_monthView').value;
	document.getElementById('paraFrm').action='../payroll/SalaryStatementBank_viewSalaryStatementLink.action?earningType=L&hiddenMonth='+month+'&earningYear='+year+'&earningCode='+
			processCode+'&divisionName='+divName+'&earningMonth='+monthText+'&linkSource='+linkSource11;
	document.getElementById('paraFrm').submit();
	//document.getElementById('paraFrm_linkSource').value='SalaryProcess_callForEdit.action?linkSourceCode='+ledgerCodeValue;
			
}
	
function taxchallanFun(){
	var processCode = document.getElementById('paraFrm_processCode').value;
	var type = 'L';
	var backAction = '<%=request.getContextPath()%>/leaves/LeaveEncashmentProcess_callforedit.action?processCode='+processCode;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/incometax/TaxChallan_input.action?applicationCode='+processCode+'&applicationType='+type+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
}
	
function callForDelete1(id,i){
	document.getElementById('deleteAll').checked = "";
	if(document.getElementById('deleteCheck'+i).checked == true){		  
		document.getElementById('hdeleteCode'+i).value=id;
	}else{
		document.getElementById('hdeleteCode'+i).value="";
	}
}

function deleteCheckAll(obj){
	var count = '<%=t%>';
	if(obj.checked == true){		
		for(var a = 0; a < count; a++) {
			document.getElementById("deleteCheck" + a).checked = "checked";
			document.getElementById('hdeleteCode'+a).value = a+1;
		}
	}else{
		for(var a = 0; a < count; a++) {
			document.getElementById("deleteCheck" + a).checked = "";
			document.getElementById('hdeleteCode'+a).value = "";
		}
	}
}
	
function chkDelete(){
	var flag='<%=d %>';
	if(chk()){
		var con=confirm('<%= label.get("confirm.delete")%>');
		if(con){
			document.getElementById('paraFrm').action="LeaveEncashmentProcess_deleteRecord.action";
			document.getElementById('paraFrm').submit();
		}else{
			return false;
		}
	} else {
		alert('Please select atleast one record to delete');
		return false;
	}
} 
	
function chk(){
	var flag='<%=d %>';
	for(var a=1;a<=flag;a++){	
		if(document.getElementById('confChk'+a).checked == true){	
			return true;
		}	   
	}
	return false;
}
	
function cancelFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_input.action';
	document.getElementById('paraFrm').submit();
}
		
function resetFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_reset.action';
	document.getElementById('paraFrm').submit();
}	
		
function newRowColor(cell){
	cell.className='onOverCell';
}
	
function oldRowColor(cell,val) {
	if(val=='1'){
		cell.className='tableCell2';
	}else {
		cell.className='tableCell1';
	}
}
	
function callForEdit(processCode){
	callButton('NA', 'Y', 2);
	try{
		//var resignCode = document.getElementById('resignCodeItt'+id).value;
		document.getElementById('paraFrm_hiddencode').value=processCode;
		document.getElementById("paraFrm").action="LeaveEncashmentProcess_callforedit.action?processCode="+processCode; 
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").submit();
	}catch(e){
		alert(e);
	}
}

function searchFun() {
	if(navigator.appName == 'Netscape') {
		var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} else {
		var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
		
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action = 'LeaveEncashmentProcess_f9action.action';
	document.getElementById("paraFrm").submit();
}
	
function lockFun() {
	var check = document.getElementById('paraFrm_salaryCheck').checked ;
	var month = document.getElementById('paraFrm_salarymonth').value;
	var year = document.getElementById('paraFrm_salaryyear').value;
	var creditcode = document.getElementById('paraFrm_creditCode').value;	
	if(check) {
		if(month=='0') {
			alert("Please select " + document.getElementById('sal.month').innerHTML);
			return false;
		}
		if(year=="") {
			alert("Please select " + document.getElementById('sal.year').innerHTML);
			return false;
		}
		if(creditcode=="") {
			alert("Please select " + document.getElementById('leaveencashcrdcode').innerHTML);
			return false;
		}
	}
	
	var conf=confirm("Do you really want to lock leave encashmet process  ?");
	if(conf){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_lock.action';
		document.getElementById('paraFrm').submit();
	} else {
		return false; 
	}
	return true;
}
	
function unlockFun() {
	var conf=confirm("Do you really want to unlock leave encashmet process  ?");
	if(conf){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_unlock.action';
		document.getElementById('paraFrm').submit();
	}else{
		return false; 
	}
	return true;
}
	 
function callAdd(){
	var div =document.getElementById('paraFrm_divCode').value;
	var leaveType =document.getElementById('paraFrm_levCode').value;
	var bal = document.getElementById('paraFrm_greaterEncashBal').value;
	
	var employee = document.getElementById('paraFrm_employeeCode').value;
	if(div==""){
		alert("Please select " + document.getElementById('division').innerHTML);
		return false;
	}
	if(leaveType==""){
		alert("Please select " + document.getElementById('levtypeLbl').innerHTML);
		return false;
	}
	if(bal==""){
		alert("Please enter " + document.getElementById('excessBal').innerHTML);
		return false;
	}
	if(employee==""){
		alert("Please select " + document.getElementById('employee').innerHTML);
		return false;
	}
	document.getElementById('paraFrm_isAddFlag').value='true';
	return true;
}

function processFun() {
	var div =document.getElementById('paraFrm_divCode').value;
	var leaveType =document.getElementById('paraFrm_levCode').value;
	var bal = document.getElementById('paraFrm_greaterEncashBal').value;
	if(div==""){
		alert("Please select " + document.getElementById('division').innerHTML);
		return false;
	}
	if(leaveType==""){
		alert("Please select " + document.getElementById('levtypeLbl').innerHTML);
		return false;
	}
	if(bal==""){
		alert("Please enter " + document.getElementById('excessBal').innerHTML);
		return false;
	}
	
	var month = document.getElementById('paraFrm_salarymonth').value;
	var year = document.getElementById('paraFrm_salaryyear').value;
	var creditcode = document.getElementById('paraFrm_creditCode').value;	
	
	if(month=='0') {
		alert("Please select " + document.getElementById('sal.month').innerHTML);
		return false;
	}
	if(year=="") {
		alert("Please select " + document.getElementById('sal.year').innerHTML);
		return false;
	}
	if(creditcode=="") {
		alert("Please select " + document.getElementById('leaveencashcrdcode').innerHTML);
		return false;
	}

	/*
	for (var i = 0; i < document.all.length; i++) {
		if(document.all[i].id == 'process') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
	}
	*/
	
	var conf=confirm("Do you really want to process ?");
	if(conf){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_process.action';
		document.getElementById('paraFrm').submit();
	}
}
  
function saveFun() {
	var value='<%=t%>'
	if(value=='0') {
		alert("There is no data to save.");
		return false;
	}
 		
	var division = document.getElementById('paraFrm_divCode').value;
	if(division=="") {
		alert("Please select " + document.getElementById('division').innerHTML);
		return false;
	}
	var check = document.getElementById('paraFrm_salaryCheck').checked ;
	var month = document.getElementById('paraFrm_salarymonth').value;
	var year = document.getElementById('paraFrm_salaryyear').value;
	var creditcode = document.getElementById('paraFrm_creditCode').value;	
	if(check) {
		if(month=='0') {
			alert("Please select " + document.getElementById('sal.month').innerHTML);
			return false;
		}
		if(year=="") {
			alert("Please select " + document.getElementById('sal.year').innerHTML);
			return false;
		}
		if(creditcode=="") {
			alert("Please select " + document.getElementById('leaveencashcrdcode').innerHTML);
			return false;
		}
	
	}

	/*
	for (var i = 0; i < document.all.length; i++) {
		if(document.all[i].id == 'save') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
	}
	*/	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_save.action';
	document.getElementById('paraFrm').submit();
}
 
function backFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_input.action';
	document.getElementById('paraFrm').submit();
}
 
function reportFun() {
	callReport('LeaveEncashmentProcess_report.action');
	/*document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_report.action';
	document.getElementById('paraFrm').submit();*/
}
 
function calculate(k){
	var availableBalance = document.getElementById("currentBal"+k).value;
	var encashLeave = document.getElementById("noOfencashLeave"+k).value;
	var amtPerDay = document.getElementById("amtPerDay"+k).value;
	if(encashLeave==''){
		encashLeave=0;
	}
	if(amtPerDay==''){
		amtPerDay=0;
	}
	if(parseFloat(encashLeave)<= parseFloat(availableBalance)){
	}else{
		alert('Encash Leave should not exceed Available Balance Leave ');
		document.getElementById("noOfencashLeave"+k).value='0';
		document.getElementById("encashAmount"+k).value='0';
		return false;
	}
		  
	var result=parseFloat(encashLeave*100/100)*parseFloat(amtPerDay);
	document.getElementById("encashAmount"+k).value=Math.round(result*100)/100;
	var encashAmount = document.getElementById("encashAmount"+k).value;
	var tds = document.getElementById("tds"+k).value;
	var netAmount = encashAmount - tds;
	document.getElementById("netAmount"+k).value = netAmount ;
}
	
function calculate_old(k){
	alert("vishu"+k);
	var oldBalance = document.getElementById("hiddenEncashDays"+k).value;
	var newBalance = document.getElementById("availableBal"+k).value;
	var encashLeave = document.getElementById("noOfencashLeave"+k).value;
	var amtPerDay = document.getElementById("amtPerDay"+k).value;
	var totalBalance=eval(oldBalance)+eval(newBalance);
		  
	if(encashLeave==''){
		encashLeave=0;
	}
	if(amtPerDay==''){
		amtPerDay=0;
	}
	if(parseFloat(encashLeave)<= parseFloat(totalBalance)){
	}else{
		alert('Encash Leave should not exceed Available Balance Leave ');
		document.getElementById("noOfencashLeave"+k).value='0';
		document.getElementById("encashAmount"+k).value='0';
		return false;
	}
	var result=parseFloat(encashLeave*100/100)*parseFloat(amtPerDay);
	document.getElementById("encashAmount"+k).value=Math.round(result*100)/100;
}
	
function deleteFun() {
	var conf=confirm("Do you really want to delete this record ?");
	if(conf){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveEncashmentProcess_delete.action';
		document.getElementById('paraFrm').submit();
	}else{
		return false; 
	}
	return true;
}
	 
function callForDelete(){
	var flag = '<%=t%>';
	var chk = false;
	for(var a = 0; a < flag; a++) {
		if(document.getElementById("deleteCheck" + a).checked == true) {
			chk = true;
		}
	}
	
	if(chk){
		var conf=confirm("Do you really want to delete ?");
		if(conf){
			//document.getElementById('paraFrm_hiddenEdit').value=id;
			document.getElementById("paraFrm").action="LeaveEncashmentProcess_deleteData.action";
			document.getElementById("paraFrm").submit();
		}else{
			return false; 
		}
	}else{
		alert('Please select atleast one record');
		return false; 
	}
	return true;
}
   		
   		
function callEmployee(){
	try{
		var divCode=document.getElementById('paraFrm_divCode').value ;
		var divName=document.getElementById('division').innerHTML.toLowerCase();
		if(divCode=='' ){
			alert("Please select " + document.getElementById('division').innerHTML);
			return false;
		}
	}catch(e){
		alert(e);
	}
	callsF9(500,325,'LeaveEncashmentProcess_f9employee.action');
}

function getYear(){
	var current = new Date();
	var year = current.getFullYear();
	var yr = document.getElementById("paraFrm_salaryyear").value;	
	if(yr==''){
		document.getElementById("paraFrm_salaryyear").value =year;
	}
}

getYear();  

--></script>