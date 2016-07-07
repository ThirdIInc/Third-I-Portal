<%--Bhushan--%><%--Feb 29, 2008--%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript" src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript">
	var eCode = new Array();
	var empC = new Array();
	var remEmp = new Array();
	var remRec = new Array();
</script>

<s:form action="DailyAttendance" name="DailyAttendance" id="paraFrm" theme="simple">
	<s:hidden name="typeFlag" /><s:hidden name="payBillFlag" /><s:hidden name="brnFlag" /><s:hidden name="deptFlag" /><s:hidden name="divFlag" />
	<s:hidden name="flag" />
	
	<div align="center" id="overlay" style="z-index:3;position:absolute;width:776px;height:450px;margin:0px;left:0;top:0;background-color:#A7BEE2;background-image:url('images/grad.gif');filter:progid:DXImageTransform.Microsoft.alpha(opacity=15);-moz-opacity:.1;opacity:.1;"></div>
	<div id="progressBar" style="z-index:3;position:absolute;width:770px;">
		<table width="100%">
			<tr><td height="200"></td></tr>
			<tr><td align="center"><img src="../pages/images/ajax-loader.gif"></td></tr>
			<tr><td align="center"><span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Processing ....</span></td></tr>
			<tr>
				<td align="center">
					<span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Please do not close the browser and do not click anywhere</span>
				</td>
			</tr>
		</table>
	</div>	
	<div id="confirmationDiv" style='position:absolute;z-index:3; 100px; height:150px; visibility: hidden; top: 200px; left: 150px;'></div>
	
	<table width="100%" height="500px" class="formbg">
		<tr valign="top">
			<td>
				<table><tr><td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td></tr></table>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="90%" class="txt"><strong class="formhead">Daily Attendance</strong></td>
						<td width="4%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td>
							<s:submit cssClass="search" action="DailyAttendance_showAttendance" value="Show" onclick="return showAttendance();" />
							
							<s:submit cssClass="edit" action="DailyAttendance_update" value="Update" onclick="return callSave();"  />
							
							<s:submit cssClass="reset" action="DailyAttendance_reset" value="Reset" onclick="return reset();"/>
						</td>
						<td width="22%" align="right"><font color="red">*</font>Indicates Required</td>
					</tr>
				</table>
				<table width="100%" class="formbg">
					<tr>
						<td width="10%">
							<label id="frmdate" name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label> :<font color="red">*</font>
						</td>
						<td width="20%" nowrap="nowrap">							
							<s:textfield size="12" name = "fromDate" maxlength="10" onkeypress="return numbersWithHiphen();" 
							onblur="return validateDate('paraFrm_fromDate', 'From Date');" />
							
							<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							</s:a>
						</td>
						<td width="5%"></td>
						<td width="10%">
							<label id="todate" name="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label> :<font color="red">*</font>
						</td>
						<td nowrap="nowrap">
							<s:textfield size="12" name = "toDate" maxlength="10" 
							onkeypress="return numbersWithHiphen();" onblur="return validateDate('paraFrm_toDate', 'To Date');" />
							
							<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							</s:a>
						</td>
					</tr>
					<tr>
						<td width="10%">
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							
							<s:if test="%{divFlag}"> :<font color="red">*</font></s:if>
							<s:else>:</s:else>
						</td>
						<td width="15%" nowrap="nowrap">
							<s:hidden name="divCode" />
							
							<s:textfield name="divName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="5%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9Div.action');">
						</td>
						<td width="10%">
							<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							
							<s:if test="%{brnFlag}"> :<font color="red">*</font></s:if>
							<s:else>:</s:else>
						</td>
						<td width="20%">
							<s:hidden name="brnCode" />
							
							<s:textfield name="brnName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9Branch.action');">
						</td>
					</tr>
					<tr>
						<td width="10%">
							<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							
							<s:if test="%{deptFlag}"> :<font color="red">*</font></s:if>
							<s:else>:</s:else>
						</td>
						<td width="15%">
							<s:hidden name="deptCode" />

							<s:textfield name="deptName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="5%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9Dept.action');">
						</td>
						<td width="10%">
							<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							
							<s:if test="%{typeFlag}"> :<font color="red">*</font></s:if>
							<s:else>:</s:else>
						</td>
						<td width="20%">
							<s:hidden name="typeCode" />
						
							<s:textfield name="typeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>	
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9EmpType.action');">
						</td>
					</tr>					
					<tr>
						<td width="10%">
							<label id="billno" name="billno" ondblclick="callShowDiv(this);"><%=label.get("billno")%></label>
							
							<s:if test="%{payBillFlag}"> :<font color="red">*</font></s:if>
							<s:else>:</s:else>
						</td>
						<td width="15%">
							<s:hidden name="payBillNo" />
							
							<s:textfield name="payBillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="5%">	
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9PayBill.action');">
						</td>
						<td width="10%">
							<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
						</td>
						<td width="20%">
							<s:hidden name="eFilNo" />
							
							<s:textfield name="eFilName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>	
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'DailyAttendance_f9Emp.action');">
							
							<s:hidden name="eFilToken"/>
						</td>
					</tr>
					<tr>
						<td width="10%">
							<label id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :
						</td>
						<td width="20%">
							<s:select name="srchStatus1" list="#{'AL' : 'All', 'PR' : 'Present', 'WO' : 'Week-Off', 'HO' : 'Holiday', 'AB' : 'Absent'}" />
						</td>
						<td width="5%"></td>
						<td width="10%">
							<label id="stat" name="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :
						</td>
						<td width="20%">
							<s:select name="srchStatus2" list="#{'AL' : 'All', 'IN' : 'In-Time', 'LC' : 'Late-Coming', 'EL' : 'Early-Leaving', 
							'DL' : 'Dual-Late', 'HD' : 'Half-Day', 'AB' : 'Absent'}" />
						</td>
					</tr>
					<tr><td width="100%" colspan="6"></td></tr>
				</table>
				<s:if test="%{flag}">
					<table width="100%" class="formbg">
						<tr>
							<td width="10%">
								<label id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :
							</td>
							<td width="20%">
								<s:select name="bulkStatus1" list="#{'PR' : 'Present', 'WO' : 'Week-Off', 'HO' : 'Holiday', 'AB' : 'Absent'}" />
							</td>
							<td width="6%"></td>
							<td width="10%">
								<label id="stat" name="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :
							</td>
							<td width="20%">
								<s:select name="bulkStatus2" list="#{'IN' : 'In-Time', 'LC' : 'Late-Coming', 'EL' : 'Early-Leaving', 
								'DL' : 'Dual-Late', 'HD' : 'Half-Day', 'AB' : 'Absent'}" />
							</td>
							<td>
								<s:submit cssClass="edit" action="DailyAttendance_bulkStatusUpdate" value="Bulk Status Update" 
								onclick="return callBulkUpdate();"  />
							</td>
						</tr>
					</table>
					<table width="100%" class="formbg">
						<tr>
							<td width="100%">
								<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
								<%	int totalPage = (Integer) request.getAttribute("totalPage");
									int pageNo = (Integer) request.getAttribute("pageNo");
								%>
								<table width="100%">
									<tr>
										<td width="100%" align="center">
										<%	if(pageNo != 1) {
										%>		<a href="#" onclick="callPage('1');" >
													<img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
												</a>&nbsp;
												<a href="#" onclick="callPage('P')" >
													<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
												</a>
										<%	}
											if(totalPage <= 5) {
												if(totalPage == 1) {
										%>			<b><u><%=totalPage%></u></b>
										<%		} else {
													for(int z = 1; z <= totalPage; z++) {
										%>				<a href="#" onclick="callPage('<%=z%>');">
										<%				if(pageNo == z) {
										%>					<b><u><%=z%></u></b>
										<%				} else {
										%>					<%=z%></a>
										<%				}
													}
												}
											} else {
												if(pageNo == totalPage - 1 || pageNo == totalPage) {									
													for(int z = pageNo - 2; z <= totalPage; z++) {
										%>				&nbsp;<a href="#" onclick="callPage('<%=z%>');">
										<%				if(pageNo == z) {
										%>					<b><u><%=z%></u></b>
										<%				} else {
										%>					<%=z%></a>
										<%				}
													}
												} else if(pageNo <= 3) {
													for(int z = 1; z <= 5; z++) {
										%>				&nbsp;<a href="#" onclick="callPage('<%=z%>');">
										<%				if(pageNo == z) {
										%>					<b><u><%=z%></u></b>
										<%				} else {
										%>					<%=z%></a>
										<%				}
													}
												} else if(pageNo > 3) {
													for(int z = pageNo - 2; z <= pageNo + 2; z++) {
										%>				&nbsp;<a href="#" onclick="callPage('<%=z%>');">
										<%				if(pageNo == z) {
										%>					<b><u><%=z%></u></b>
										<%				} else {
										%>					<%=z%></a>
										<%				}
													}
												}
											}
											
											if(!(pageNo == totalPage)) {
												if(totalPage == 0){}
												else {
										%>			....<%=totalPage%>
													<a href="#" onclick="callPage('N')" >
														<img src="../pages/common/img/next.gif" class="iconImage" />
													</a>&nbsp;
													<a href="#" onclick="callPage('<%=totalPage%>');" >
														<img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
													</a>
										<%		}
											}
										%>
										</td>
									</tr>
								</table>
								<table id="tblAttn" width="100%">
									<tr>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="10%" 
											style="text-align: center;" value="Employee ID" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="20%" 
											style="text-align: center;" value="Name" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="7%" 
											style="text-align: center;" value="Date" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="20%" 
											style="text-align: center;" value="Shift" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="6%" 
											style="text-align: center;" value="In Time" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="6%" 
											style="text-align: center;" value="Out Time" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="6%" 
											style="text-align: center;" value="Work Hrs." />
										</td>
										<s:if test="showExtraHoursFlag">
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="6%" 
											style="text-align: center;" value="Extra Hrs." />
										</td>
										</s:if>
										<s:else>
											<td>
											<input type="hidden" class="tokenPay" readonly="readonly" size="6%" 
													style="text-align: center;" value="Extra Hrs." />
										</td>
										</s:else>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="12%" 
											style="text-align: center;" value="Late Coming Hrs." />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="13%" 
											style="text-align: center;" value="Early Leaving Hrs." />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="7%" 
											style="text-align: center;" value="Status 1" />
										</td>
										<td>
											<input type="text" class="tokenPay" readonly="readonly" size="7%" 
											style="text-align: center;" value="Status 2" />
										</td>
									</tr>
									<%!	int cnt = 0;
										Object[][] attdnData = null;
									%>
									<%	attdnData = (Object[][])request.getAttribute("attdnData");
										if(attdnData != null && attdnData.length > 0) {
										for(int i = 0; i < attdnData.length; i++){
									%>	<tr id="change<%=cnt%>">
											<td>
												<input type="hidden" name="eId" id="eId<%=cnt%>" value="<%=attdnData[i][0]%>" />
												
												<input type="text" name="eToken" id="eToken<%=cnt%>" size="10%" readonly="readonly" 
												style=" background-color: #F2F2F2;" value="<%=attdnData[i][1]%>">
												
												<script>
													eCode[<%=cnt%>] = document.getElementById('eId'+<%=cnt%>).value;
												</script>
											</td>
											<td>
												<input type="text" name="eName" id="eName<%=cnt%>" size="20%" readonly="readonly" 
												style="background-color: #F2F2F2;" value="<%=attdnData[i][2]%>">
											</td>
											<td>
												<input type="text" name="eDate" id="eDate<%=cnt%>" size="12%" readonly="readonly" 
												style=" background-color: #F2F2F2;" value="<%=attdnData[i][3]%>">
											</td>
											<td>
												<input type="hidden" name="eSId" id="eSId<%=cnt%>" value="<%=attdnData[i][4]%>" />
												
												<input type="text" name="eShift" id="eShift<%=cnt%>" size="20%" readonly="readonly" 
												style=" background-color: #F2F2F2;" value="<%=attdnData[i][5]%>">
											</td>
											<td align="center">
												<input type="text" name="eInTime" id="eInTime<%=cnt%>" size="6%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][6]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eInTime<%=cnt%>', 'In Time');" />
											</td>
											<td align="center">
												<input type="text" name="eOutTime" id="eOutTime<%=cnt%>" size="6%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][7]%>"
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eOutTime<%=cnt%>', 'Out Time');" />
											</td>
											<td align="center">
												<input type="text" name="eWHrs" id="eWHrs<%=cnt%>" size="5%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][8]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eWHrs<%=cnt%>', 'Working Hrs.');" />
											</td>
											<s:if test="showExtraHoursFlag">
											<td align="center">
												<input type="text" name="eExHrs" id="eExHrs<%=cnt%>" size="5%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][9]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eExHrs<%=cnt%>', 'Extra Hrs.');" />
											</td>
											</s:if>
											<s:else>
												<td align="center">
												<input type="hidden" name="eExHrs" id="eExHrs<%=cnt%>" size="5%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][9]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eExHrs<%=cnt%>', 'Extra Hrs.');" />
											</td>
											</s:else>
											<td align="center">
												<input type="text" name="eLtHrs" id="eLtHrs<%=cnt%>" size="10%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][10]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eLtHrs<%=cnt%>', 'Late Hrs.');" />
											</td>
											<td align="center">
												<input type="text" name="eErHrs" id="eErHrs<%=cnt%>" size="10%" maxlength="8" 
												style="text-align: center;" value="<%=attdnData[i][11]%>" 
												onkeypress="return numbersWithColon();" onblur="return validateTimeToSec('eErHrs<%=cnt%>', 'Early Hrs.');" />
											</td>
											<td align="center">
												<select name="eStatus1" id="eStatus1<%=cnt%>" >
													<% if(String.valueOf(attdnData[i][12]).equals("PR")){ %>
														<option id="PR" value="PR" selected="selected">Present</option>
													<% } else { %>
														<option id="PR" value="PR">Present</option>
													<% } if(String.valueOf(attdnData[i][12]).equals("WO")) { %>
														<option id="WO" value="WO" selected="selected">Week-Off</option>
													<% } else { %>
														<option id="WO" value="WO">Week-Off</option>
													<% } if(String.valueOf(attdnData[i][12]).equals("HO")) { %>
														<option id="HO" value="HO" selected="selected">Holiday</option>
													<% } else { %>
														<option id="HO" value="HO">Holiday</option>
													<% } if(String.valueOf(attdnData[i][12]).equals("AB")) { %>										
														<option id="AB" value="AB" selected="selected">Absent</option>
													<% } else { %>
														<option id="AB" value="AB">Absent</option>
													<% } %>
												</select>
											</td>
											<td align="center">
												<select name="eStatus2" id="eStatus2<%=cnt%>" >
													<% if(String.valueOf(attdnData[i][13]).equals("IN")) { %>
														<option id="IN" value="IN" selected="selected">In-Time</option>
													<% } else { %>
														<option id="IN" value="IN">In-Time</option>
													<% } if(String.valueOf(attdnData[i][13]).equals("LC")) { %>
														<option id="LC" value="LC" selected="selected">Late-Coming</option>
													<% } else { %>
														<option id="LC" value="LC">Late-Coming</option>
													<% } if(String.valueOf(attdnData[i][13]).equals("EL")) { %>
														<option id="EL" value="EL" selected="selected">Early-Leaving</option>
													<% } else { %>
														<option id="EL" value="EL">Early-Leaving</option>
													<% } if(String.valueOf(attdnData[i][13]).equals("DL")) { %>
														<option id="DL" value="DL" selected="selected">Dual-Late</option>
													<% } else { %>
														<option id="DL" value="DL">Dual-Late</option>
													<% } if(String.valueOf(attdnData[i][13]).equals("HD")) { %>
														<option id="HD" value="HD" selected="selected">Half-Day</option>
													<% } else { %>
														<option id="HD" value="HD">Half-Day</option>
													<% } if(String.valueOf(attdnData[i][13]).equals("AB")) { %>
														<option id="AB" value="AB" selected="selected">Absent</option>
													<% } else { %>
														<option id="AB" value="AB">Absent</option>
													<% } %>
												</select>
											</td>
										</tr>
									<%	++cnt; %>
									<%	} }
										cnt = 0;
									%>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<b>
									<label id="totalRecords" name="totalRecords" ondblclick="callShowDiv(this);"><%=label.get("totalRecords")%></label> : 
									
									<%=String.valueOf(request.getAttribute("totalRecords"))%>
								</b>
							</td>
						</tr>
					</table>
				</s:if>
			</td>
		</tr>
	</table>
</s:form>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<script type = "text/javascript" >
	var cntRem = 0;
	var cntExist = 0;
	
	function callPage(id) {
		if(id == 'P') {
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		
		if(id == 'N') {
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		
		document.getElementById('hdPage').value = id;
		
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>' +
		'<tr><td><b><center>Please select anyone of the option given below</td></tr>' +
		'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>' +
		'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithOutSave();"/>' +
		'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
	}
	
	function cancel() {
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
	}
	
	function proceedWithSave() {
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('paraFrm').action = "DailyAttendance_update.action";
		document.getElementById('paraFrm').submit();
	}
	
	function proceedWithOutSave() {
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('paraFrm').action = "DailyAttendance_showAttendance.action";
		document.getElementById('paraFrm').submit();
	}
	
	function checkNumbers(id) {
		var count = 0;
		var txtNo = document.getElementById(id).value;
		
		for(var i = 0; i < txtNo.length; i++) {
			if(txtNo.charAt(i) == '.') {
				count = count + 1;
			}
		}
		
		if(count > 0) {
			if(!numbersOnly()) {
				return false;
			}
		} else if(!numbersWithDot()) {
			return false;
		}
		
		return true;
	}

	function showAttendance() {
		var name = ['paraFrm_fromDate','paraFrm_toDate'];
		var label = ['frmdate','todate'];
		var flag = ["enter", "enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		if(!validateDate('paraFrm_fromDate','frmdate')) {
			return false;
		}
		
		if(!validateDate('paraFrm_toDate','todate')) {
			return false;
		}
		
		var fromDate = document.getElementById('paraFrm_fromDate').value;
		var toDate = document.getElementById('paraFrm_toDate').value;
		
		if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_toDate', 'frmdate', 'todate')) {
			return false;
		}
		
		if(document.getElementById('paraFrm_divFlag').value == 'true' && document.getElementById('paraFrm_divCode').value == "") {
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_brnFlag').value == 'true' && document.getElementById('paraFrm_brnCode').value == "") {
			alert("Please select the "+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_deptFlag').value == 'true' && document.getElementById('paraFrm_deptCode').value == "") {
			alert("Please select the "+document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_typeFlag').value == 'true' && document.getElementById('paraFrm_typeCode').value == "") {
			alert("Please select the "+document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && document.getElementById('paraFrm_payBillNo').value == "") {
			alert("Please select the "+document.getElementById('billno').innerHTML.toLowerCase());
			return false;
		}
		
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		return true;
	}
	
	function validateTimeToSec(id, labName) {
		var time = document.getElementById(id).value;
		var timeExp = /^[0-9]{2}[:]?[0-9]{2}[:][0-9]{2}$/
		var timeArray = time.split(":");
		var hour = timeArray[0];
		var min = timeArray[1];
		var sec = timeArray[2];
		
		if(!(time.match(timeExp))) {
			alert("" + labName + " should be in 24Hours HH:MM:SS format");
			document.getElementById(id).focus();
			return false;
		}
		
		if(hour > 23) {
			alert("Hour " + hour + " is not valid");
			document.getElementById(id).focus();
			return false;
		}
		
		if(min > 59) {
			alert("Minuite " + min + " is not valid");
			document.getElementById(id).focus();
			return false;
		}
		
		if(sec > 59) {
			alert("Seconds " + sec + " is not valid");
			document.getElementById(id).focus();
			return false;
		}
		return true;
	}
	
	function changeVal(name, to) {
		try {
			for(var i = 0; i < eCode.length; i++) {
				if(document.getElementById(name+i).checked) {
					document.getElementById(to+i).value = true;
				} else {
					document.getElementById(to+i).value = false;
				}
			}
		} catch(e) {
			alert(e.description);
		}
	}
	
	function callSave() {
		var flag = document.getElementById('paraFrm_flag').value;
		
		if(flag == 'false') {
			alert("Attendance Not Available!");
			document.getElementById('paraFrm_fromDate').focus();
			return false;
		}
		
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		return true;
	}
	
	function callStatus(id) {
		var status = document.getElementById('eStatus1'+id).value;
		
		if(status == 'AB') {
			document.getElementById('eStatus2'+id).value = 'AB';
			document.getElementById('eStatus2'+id).disabled = true;
		} else {
			document.getElementById('eStatus2'+id).selectedIndex = 0;
			document.getElementById('eStatus2'+id).disabled = "";
		}
	}
	
	function reset() {
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		document.getElementById('paraFrm').action = "DailyAttendance_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	gridScroll();
	
	function gridScroll() {
		try {
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			
			//myST = superTable("tblAttn", {fixedCols : 4,rightCols:2,viewCols:4});
			
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
		} catch(e) {
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
		}
	}
	
	function callBulkUpdate() {
		var name = ['paraFrm_fromDate','paraFrm_toDate'];
		var label = ['frmdate','todate'];
		var flag = ["enter", "enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		if(!validateDate('paraFrm_fromDate','frmdate')) {
			return false;
		}
		
		if(!validateDate('paraFrm_toDate','todate')) {
			return false;
		}
		
		var fromDate = document.getElementById('paraFrm_fromDate').value;
		var toDate = document.getElementById('paraFrm_toDate').value;
		
		if(!dateDifferenceEqual(fromDate, toDate, 'paraFrm_toDate', 'frmdate', 'todate')) {
			return false;
		}
		
		if(document.getElementById('paraFrm_divFlag').value == 'true' && document.getElementById('paraFrm_divCode').value == "") {
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		return true;
	}
</script>