<%--Bhushan--%><%--Feb 12, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript" src="../pages/attendance/Ajax.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/syncscroll.js"></script>

<script type="text/javascript">
	var eCode = new Array();
	var eDate = new Array();
	var eLMs = new Array();
	var eHDs = new Array();
	var empRecal = new Array();
	var eChk = new Array();
	
	function wrongRecs(id)
	{
		try
		{
			var wrong = 'false';
			var attdnDays = eval(document.getElementById('attdnDays'+id).value);
			var salDays = eval(document.getElementById('salDays'+id).value);
			var presentDays = eval(document.getElementById('presentDays'+id).value);
			var unPaidLevs = eval(document.getElementById('unPaidLevs'+id).value);
			
			if(attdnDays < 0 || salDays > presentDays || unPaidLevs < 0)
			{
				wrong = 'true';
			}
			
			if(wrong == 'true')
			{
				document.getElementById('eToken'+id).style.background = '#FF8383';
				document.getElementById('eName'+id).style.background = '#FF8383';
				document.getElementById('attdnDays'+id).style.background = '#FF8383';
				document.getElementById('weeklyOffs'+id).style.background = '#FF8383';
				document.getElementById('holidays'+id).style.background = '#FF8383';
				document.getElementById('paidLevs'+id).style.background = '#FF8383';
				document.getElementById('unPaidLevs'+id).style.background = '#FF8383';
				document.getElementById('recoveryDays'+id).style.background = '#FF8383';
				document.getElementById('salDays'+id).style.background = '#FF8383';
			}
			else
			{
				var eSearch = document.getElementById('eSearch'+id).value;
				
				if(eSearch == 'true')
				{
					document.getElementById('eToken'+id).style.background = '#FDFBB0';
					document.getElementById('eName'+id).style.background = '#FDFBB0';
					document.getElementById('attdnDays'+id).style.background = '#FDFBB0';
					document.getElementById('weeklyOffs'+id).style.background = '#FDFBB0';
					document.getElementById('holidays'+id).style.background = '#FDFBB0';
					document.getElementById('paidLevs'+id).style.background = '#FDFBB0';
					document.getElementById('unPaidLevs'+id).style.background = '#FDFBB0';
					document.getElementById('recoveryDays'+id).style.background = '#FDFBB0';
					document.getElementById('salDays'+id).style.background = '#FDFBB0';
				}
				else
				{
					document.getElementById('eToken'+id).style.background = '#F2F2F2';
					document.getElementById('eName'+id).style.background = '#F2F2F2';
					document.getElementById('attdnDays'+id).style.background = 'white';
					document.getElementById('weeklyOffs'+id).style.background = 'white';
					document.getElementById('holidays'+id).style.background = 'white';
					document.getElementById('paidLevs'+id).style.background = 'white';
					document.getElementById('unPaidLevs'+id).style.background = 'white';
					document.getElementById('recoveryDays'+id).style.background = 'white';
					document.getElementById('salDays'+id).style.background = '#F2F2F2';
				}
			}
		}catch(e){}
	}
</script>

<s:form action="MonthAttendance" name="MonthAttendance" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="typeFlag" />
	<s:hidden name="payBillFlag" />
	<s:hidden name="brnFlag" />
	<s:hidden name="deptFlag" />
	<s:hidden name="divFlag" />
	<s:hidden name="attConnFlag" />
	<s:hidden name="status" />
	<s:hidden name="statusFlag" />
	<s:hidden name="lockFlag" />
	<s:hidden name="savedFlag" />
	<s:hidden name="searchFlag" />
	<s:hidden name="attdnCode" />
	<s:hidden name="currValue" />
	<s:hidden name="attnCurrVal" />
	<s:hidden name="hiddenEmpid" />
	<s:hidden name="rowid" />
	<s:hidden name="pLevs" />
	<s:hidden name="totAbsLD" />
	<s:hidden name="brnHDayFlag" />
	<s:hidden name="empSearch" />
	<s:hidden name="levConnFlag" />
	<s:hidden name="unlockFlag" />
	<s:hidden name="process" />
	<s:hidden name="remEmps" />
	<s:hidden name="remDate" />
	<s:hidden name="remLMs" />
	<s:hidden name="remHDs" />
	<s:hidden name="fDate" />
	<s:hidden name="tDate" />
	<s:hidden name="attConn" />
	<s:hidden name="recoveryFlag" />
	
	<div align="center" id="overlay" style="z-index:3;position:absolute;width:776px;height:450px;margin:0px;left:0;top:0;background-color:#A7BEE2;background-image:url('images/grad.gif');filter:progid:DXImageTransform.Microsoft.alpha(opacity=15);-moz-opacity:.1;opacity:.1;"></div>
	<div id="progressBar" style="z-index:3;position:absolute;width:770px;">
		<table width="100%">
			<tr>
				<td height="200"></td>
			</tr>
			<tr>
				<td align="center">
					<img src="../pages/images/ajax-loader.gif">
				</td>
			</tr>
			<tr>
				<td align="center">
					<span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Processing ....</span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Please do not close the browser and do not click anywhere</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="confirmationDiv" style='position:absolute;z-index:3; 100px; height:150px; visibility: hidden; top: 200px; left: 150px;'></div>
	
	<table width="100%" class="formbg"><tr><td>
	<table>
		<tr><td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td></tr>
	</table>
	<table width="100%" class="formbg">
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
			<td width="92%" class="txt"><strong class="formhead">Monthly Attendance </strong></td>
			<td width="4%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
		</tr>
	</table>
	<table width="100%">
		<tr><td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td></tr>
		<tr>
			<td>
				<input type="button" class="search" value="Search" onclick="callsF9(500,325,'MonthAttendance_f9ShowAttdn.action');" />
				<s:if test="lockFlag"></s:if>
				<s:else>
					<s:if test="process">
						<s:submit cssClass="add" action="MonthAttendance_save" theme="simple" value="Save" 
						onclick="return callSave();" />
					</s:if>
				</s:else>
				<s:if test="process"></s:if>
				<s:else>
					<s:submit cssClass="token" action="MonthAttendance_calAttendance" theme="simple" value="Process" 
					onclick="return processAttn();" />
				</s:else>					
				<s:if test="statusFlag"></s:if>
				<s:else>
					<s:if test="lockFlag">
						<s:submit cssClass="unlock" action="MonthAttendance_unLockAttendance" theme="simple" value="UnLock" 
						onclick="return unLockAttn();" />
					</s:if>
					<s:else>
						<s:if test="savedFlag">
							<s:submit cssClass="lock" action="MonthAttendance_lockAttendance" theme="simple" value="Lock" 
							onclick="return lockAttdn();" />
						</s:if>
					</s:else>
				</s:else>
				<s:submit cssClass="reset" action="MonthAttendance_reset" theme="simple" value="Reset" />
			</td>
			<td align="right"><font color="red">*</font>Indicates Required</td>
		</tr>
		<tr><td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td></tr>
	</table>
	<table width="100%" class="formbg">
		<tr>
			<s:hidden name="month" />
			<s:if test="process">
				<td width="40%" align="right"><label  class = "set"  id="month1" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="monthName" size="7" readonly="true" cssStyle="text-align: center; background-color: #F2F2F2;" 
					value="%{monthName}" />&nbsp;&nbsp;&nbsp;
					<label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:
					<s:textfield name="year" size="5" readonly="true" cssStyle="text-align: center; background-color: #F2F2F2;" />
				</td>
			</s:if>
			<s:else>
				<td width="40%" align="right"><label  class = "set"  id="month1" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
				<td>
					<s:select name="monthName" headerKey="0" headerValue="--Select--" onchange="callMonth();"
					list="#{'1':'January','2':'Febuary','3':'March','4':'April','5':'May','6':'June','7':'July',
					'8':'August','9':'September','10':'October','11':'November','12':'December'}" />&nbsp;&nbsp;&nbsp;
					<label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:
					<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" 
					onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'Year');" />
				</td>
			</s:else>
		</tr>
		<s:hidden name="divCode" />
		<s:if test="divFlag">
			<tr>
				<td width="40%" align="right"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="divName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
					<s:if test="process" />
					<s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
						onclick="callsF9(500,325,'MonthAttendance_f9Div.action');">
					</s:else>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr style="display: none;">
				<td width="49%" align="right">
					<label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>: <s:textfield name="divName" />
				</td>
			</tr>
		</s:else>
		<s:hidden name="brnCode" />
		<s:if test="brnFlag">
			<tr>
				<td width="40%" align="right"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="brnName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
					<s:if test="process" />
					<s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
						align="absmiddle" width="18" onclick="callsF9(500,325,'MonthAttendance_f9Branch.action');">
					</s:else>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr style="display: none;">
				<td width="49%" align="right">
					<label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font>:<s:textfield name="brnName" />
				<s:textfield name="brnName" />
				</td>
			</tr>
		</s:else>
		<s:hidden name="deptCode" />
		<s:if test="deptFlag">
			<tr>
				<td width="40%" align="right"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="deptName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
					<s:if test="process" />
					<s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
						align="absmiddle" width="18" onclick="callsF9(500,325,'MonthAttendance_f9Dept.action');">
					</s:else>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr style="display: none;">
				<td width="49%" align="right">
				<s:textfield name="deptName" />
				</td>
			</tr>
		</s:else>
		<s:hidden name="typeCode" />
		<s:if test="typeFlag">
			<tr>
				<td width="40%" align="right"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="typeName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
					<s:if test="process" />
					<s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
						align="absmiddle" width="18" onclick="callsF9(500,325,'MonthAttendance_f9EmpType.action');">
					</s:else>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr style="display: none;">
				<td width="49%" align="right">
					<label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label><font color="red">*</font>: <s:textfield name="typeName" />
					<s:textfield name="typeName" />
				</td>
			</tr>
		</s:else>
		<s:hidden name="payBillNo" />
		<s:if test="payBillFlag">
			<tr>
				<td width="40%" align="right"><label  class = "set"  id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label><font color="red">*</font>:</td>
				<td>
					<s:textfield name="payBillName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
					<s:if test="process" />
					<s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
						align="absmiddle" width="18" onclick="callsF9(500,325,'MonthAttendance_f9PayBill.action');">
					</s:else>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr style="display: none;">
				<td width="49%" align="right">
					<label  class = "set"  id="billno" name="billno" ondblclick="callShowDiv(this);"><%=label.get("billno")%></label><font color="red">*</font>: <s:textfield name="payBillName" />
					<s:textfield name="payBillName" />
				</td>
			</tr>
		</s:else>
	</table>
	<s:if test="flag">
		<table width="100%" class="formbg">
			<s:if test="lockFlag">
				<tr>
					<td width="40%" align="right">
						<label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<s:hidden name="empCode" /><s:hidden name="empToken" />
					</td>
					<td>
						<s:textfield size="33" theme="simple" readonly="true" name="empName" cssStyle="background-color: #F2F2F2;" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
						width="18" onclick="return callSearch();">&nbsp;&nbsp;
						<s:submit cssClass="token" action="MonthAttendance_empSearch" theme="simple" value="Search" 
						onclick="return search();" />
					</td>
				</tr>
			</s:if>
			<s:else>
				<s:if test="savedFlag">
					<tr>
						<td width="49%">
							<label  class = "set"  id="aEmployee" name="aEmployee" ondblclick="callShowDiv(this);"><%=label.get("aEmployee")%></label> :<s:hidden name="newECode" /><s:hidden name="newEToken" /><s:hidden name="newJoinDate" />
							<s:textfield size="25" theme="simple" readonly="true" name="newEName" cssStyle="background-color: #F2F2F2;" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="return callAddEmp();">&nbsp;&nbsp;
							<input type="button" class="token" value="   Add   " onclick="return addEmp();">
						</td>
						<td width="49%" align="right">
							<label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<s:hidden name="empCode" /><s:hidden name="empToken" />
							<s:textfield size="25" theme="simple" readonly="true" name="empName" cssStyle="background-color: #F2F2F2;" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="return callSearch();">&nbsp;&nbsp;
							<s:submit cssClass="token" action="MonthAttendance_empSearch" theme="simple" value="Search" onclick="return search();" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="40%" align="right">
							<label  class = "set"  id="sEmployee" name="sEmployee" ondblclick="callShowDiv(this);"><%=label.get("sEmployee")%></label>:<s:hidden name="empCode" /><s:hidden name="empToken" />
						</td>
						<td>
							<s:textfield size="33" theme="simple" readonly="true" name="empName" cssStyle="background-color: #F2F2F2;" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" onclick="return callSearch();">&nbsp;&nbsp;
							<s:submit cssClass="token" action="MonthAttendance_empSearch" theme="simple" value="Search" 
							onclick="return search();" />
						</td>
					</tr>
				</s:else>
			</s:else>
		</table>	
		<table width="100%" class="formbg">
			<tr>
				<td width="98%">
					<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
					<table width="98%">
						<tr>
							<td width="98%" align="center">
							<%	if(pageNo != 1)
								{
							%>		<a href="#" onclick="callPage('1');" >
										<img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P')" >
										<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a>
							<%	}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%>			<b><u><%=totalPage%></u></b>
							<%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0){}
									else
									{
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
							<td align="right">
								<s:if test="lockFlag" />
								<s:elseif test="savedFlag">
									<input type="button" class="token" value="Recalculate" onclick="return removeRecal('recalculate');" />
								</s:elseif>
							</td>
							<td align="right">
								<s:if test="lockFlag" />
								<s:elseif test="savedFlag">
									<s:submit cssClass="delete" action="MonthAttendance_remove" theme="simple" value="  Remove" 
									onclick="return removeRecal('remove');" />
								</s:elseif>
							</td>
						</tr>
					</table>
					<table id="tblAttn" width="98%">
						<tr>
							<td>
								<input type="text" class="tokenPay" readonly size="10%" 
								style="text-align: center; border: none; margin: 1px;" value="Employee ID">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="35%" 
								style="text-align: center; border: none; margin: 1px;" value="Employee Name">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="12%" 
								style="text-align: center; border: none; margin: 1px;" value="Attendance Days">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="8%" 
								style="text-align: center; border: none; margin: 1px;" value="Weekly Offs">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="5%" 
								style="text-align: center; border: none; margin: 1px;" value="Holidays">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="8%" 
								style="text-align: center; border: none; margin: 1px;" value="Paid Leaves">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="10%" 
								style="text-align: center; border: none; margin: 1px;" value="UnPaid Leaves">
							</td>
							<td>
								<s:if test="recoveryFlag">
									<input type="text" class="tokenPay" readonly size="10%" 
										style="text-align: center; border: none; margin: 1px;" value="Recovery Days">
								</s:if>
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="8%" 
								style="text-align: center; border: none; margin: 1px;" value="Salary Days">
							</td>
							<td>
								<input type="text" class="tokenPay" readonly size="8%" 
								style="border: none; margin: 1px;" value="Leave Details">
							</td>
							<td>
								<s:if test="lockFlag" />
								<s:elseif test="savedFlag">	
									<center>
										<input type="checkbox" class="classCheck" id="selectId" onclick="return selectAll();" />
									</center>
								</s:elseif>
							</td>
						</tr>
						<%int cnt = 0;%>
						<%int chk = 0;%>
						<s:iterator value="attdnList">
							<tr>
								<td>
									<s:hidden name="reCal" id='<%="reCal"+cnt%>' />
									<s:hidden name="eSearch" id='<%="eSearch"+cnt%>' />
									<s:hidden name="eId" id='<%="eId"+cnt%>' />
									<input type="text" name="eToken" id="eToken<%=cnt%>" size="10%" readonly 
									style="text-align: left; background-color: #F2F2F2;" value="<s:property value='eToken' />" />
								</td>
								<td>
									<input type="text" name="eName" id="eName<%=cnt%>" size="35%" readonly 
									style=" background-color: #F2F2F2;" value="<s:property value='eName' />" />
									<s:hidden name="eJoinDate" id='<%="eJoinDate"+cnt%>' />
								</td>
								<td>
									<s:if test="lockFlag">										
										<input type="text" name="attdnDays" id="attdnDays<%=cnt%>" readonly
										size="12%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='attdnDays' />" />
									</s:if>
									<s:else>
										<input type="text" name="attdnDays" id="attdnDays<%=cnt%>" maxlength="5"
										size="12%" style="text-align: right;" value="<s:property value='attdnDays' />"
										onfocus="setPreviousValues('attdnDays','<%=cnt%>');" onblur="return callCal('attdnDays','<%=cnt%>');" 
										onkeypress="return checkNumbers('attdnDays<%=cnt%>');" />
									</s:else>
								</td>
								<td>
									<s:if test="lockFlag">
										<input type="text" name="weeklyOffs" id="weeklyOffs<%=cnt%>" readonly
										size="8%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='weeklyOffs' />" />
									</s:if>
									<s:else>
										<input type="text" name="weeklyOffs" id="weeklyOffs<%=cnt%>" maxlength="5" 
										size="8%" style="text-align: right;" value="<s:property value='weeklyOffs' />"
										onfocus="setPreviousValues('weeklyOffs','<%=cnt%>');" onblur="return callCal('weeklyOffs','<%=cnt%>');" 
										onkeypress="return checkNumbers('weeklyOffs<%=cnt%>');" />
									</s:else>
								</td>
								<td>
									<s:if test="lockFlag">
										<input type="text" name="holidays" id="holidays<%=cnt%>" readonly
										size="5%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='holidays' />" />
									</s:if>
									<s:else>
										<input type="text" name="holidays" id="holidays<%=cnt%>" maxlength="5" 
										size="5%" style="text-align: right" value="<s:property value='holidays' />"
										onfocus="setPreviousValues('holidays','<%=cnt%>');" onblur="return callCal('holidays','<%=cnt%>');" 
										onkeypress="return checkNumbers('holidays<%=cnt%>');" />
									</s:else>
								</td>
								<td>
									<s:if test="lockFlag">
										<input type="text" name="paidLevs" id="paidLevs<%=cnt%>" readonly
										size="8%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='paidLevs' />" />
									</s:if>
									<s:else>
										<input type="text" name="paidLevs" id="paidLevs<%=cnt%>" maxlength="5" 
										size="8%" style="text-align: right" value="<s:property value='paidLevs' />"
										onfocus="setPreviousValues('paidLevs','<%=cnt%>');" onblur="return callCal('paidLevs','<%=cnt%>');" 
										onkeypress="return checkNumbers('paidLevs<%=cnt%>');" />
									</s:else>
								</td>
								<td>
									<s:if test="lockFlag">
										<input type="text" name="unPaidLevs" id="unPaidLevs<%=cnt%>" readonly
										size="10%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='unPaidLevs' />" />
									</s:if>
									<s:else>
										<input type="text" name="unPaidLevs" id="unPaidLevs<%=cnt%>" maxlength="5" 
										size="10%" style="text-align: right" value="<s:property value='unPaidLevs' />"
										onfocus="setPreviousValues('unPaidLevs','<%=cnt%>');" onblur="return callCal('unPaidLevs','<%=cnt%>');" 
										onkeypress="return checkNumbers('unPaidLevs<%=cnt%>');" />
									</s:else>
								</td>
								<td>
									<s:if test="recoveryFlag">
										<s:if test="lockFlag">
											<input type="text" name="recoveryDays" id="recoveryDays<%=cnt%>" readonly
											size="10%" style="text-align: right; background-color: #F2F2F2;" value="<s:property value='recoveryDays' />" />
										</s:if>
										<s:else>
											<input type="text" name="recoveryDays" id="recoveryDays<%=cnt%>" maxlength="5" 
											size="10%" style="text-align: right" value="<s:property value='recoveryDays' />" onkeypress="return checkNumbers('recoveryDays<%=cnt%>');"/>
										</s:else>
									</s:if>
									<s:else>
										<input type="hidden" name="recoveryDays" id="recoveryDays<%=cnt%>" value="<s:property value='recoveryDays' />"/>
									</s:else>		
								</td>
								<td>
									<s:hidden name="presentDays" id='<%="presentDays"+cnt%>' />
									<input type="text" name="salDays" id="salDays<%=cnt%>" readonly size="8%" 
									style="text-align: center; background-color: #F2F2F2;" value="<s:property value='salDays' />" />
								</td>
								<td>
									<s:hidden name="lateMarks" id='<%="lateMarks"+cnt%>' />
									<s:hidden name="halfDays" id='<%="halfDays"+cnt%>' />
									<s:hidden name="sysAdjUnpaid" id='<%="sysAdjUnpaid"+cnt%>' />
									<s:hidden name="manAdjUnpaid" id='<%="manAdjUnpaid"+cnt%>' />
									<s:hidden name="totAbs" id='<%="totAbs"+cnt%>' />
									<s:hidden name="fromDate" id='<%="fromDate"+cnt%>' />
									<s:hidden name="toDate" id='<%="toDate"+cnt%>' />
									<s:hidden name="eSave" id='<%="eSave"+cnt%>' />
									<s:hidden name="dailyConFlag" id='<%="dailyConFlag"+cnt%>' />
									
									<div id="details<%=cnt%>" align="center">
										<a href="#" onclick="viewDetails('<s:property value="eId"/>','<s:property value="attdnCode"/>',
										'<s:property value="paidLevs<%=cnt%>"/>','<s:property value="totAbs"/>','<s:property value="fromDate"/>',
										'<s:property value="toDate"/>','<s:property value="dailyConFlag"/>','<%=cnt%>')">Details</a>
									</div>
									
									<script>
										eCode[<%=cnt%>] = document.getElementById('eId'+<%=cnt%>).value;
										eDate[<%=cnt%>] = document.getElementById('eJoinDate'+<%=cnt%>).value;
										eLMs[<%=cnt%>] = document.getElementById('lateMarks'+<%=cnt%>).value;
										eHDs[<%=cnt%>] = document.getElementById('halfDays'+<%=cnt%>).value;
									</script>
								</td>
								<td>
									<s:if test="lockFlag" />
									<s:elseif test="eSave">
										<script>
											eChk[<%=chk%>] = document.getElementById('eId'+<%=cnt%>).value;
										</script>
										<center>
											<input type="checkbox" class="classCheck" name="remRec" id="remRec<%=cnt%>" size="2%" value="<%=cnt%>"
											onclick="checkRecord('<%=cnt%>');" />
										</center>
										<%++chk;%>
									</s:elseif>
								</td>
							</tr>
							<s:if test="reCal">
								<script type="text/javascript">
									wrongRecs(<%=cnt%>);
								</script>
							</s:if>
							<%++cnt;%>
						</s:iterator>
						<%cnt = 0;%>
						<s:if test="empSearch">
						   	<script type="text/javascript">
						   		var empCode = document.getElementById('paraFrm_empCode').value;
						   		var found = false;
						   		for(var i = 0; i < eCode.length; i++)
						   		{
						   			if(empCode == eCode[i])
						   			{
						   				document.getElementById('eToken'+i).style.background = '#FDFBB0';
										document.getElementById('eName'+i).style.background = '#FDFBB0';
										document.getElementById('attdnDays'+i).style.background = '#FDFBB0';
										document.getElementById('weeklyOffs'+i).style.background = '#FDFBB0';
										document.getElementById('holidays'+i).style.background = '#FDFBB0';
										document.getElementById('paidLevs'+i).style.background = '#FDFBB0';
										document.getElementById('unPaidLevs'+i).style.background = '#FDFBB0';
										document.getElementById('recoveryDays'+i).style.background = '#FDFBB0';
										document.getElementById('salDays'+i).style.background = '#FDFBB0';
										document.getElementById('eSearch'+i).value = 'true';
										found = true;
										break;
						   			}
						   		}
						   		if(!found)
						   		{
						   			if(empCode != "")
						   			{
						   				alert('Employee Not Found!');
						   			}
						   		}
						   		document.getElementById('paraFrm_empCode').value = "";
						   		document.getElementById('paraFrm_empToken').value = "";
						   		document.getElementById('paraFrm_empName').value = "";
							</script>
						</s:if>
					</table>
					<%} catch(Exception e) {
				    	e.printStackTrace();
					}%>
				</td>
			</tr>
		</table>
		<table width="100%" class="formbg">
			<tr>
				<td style="color: red;">Note: Records shown in RED colour (if any) represent incorrect attendance records. Please rectify Daily Attendance records and recalculate.</td>
			</tr>
		</table>
	</s:if>
	<s:else>
		<s:if test="savedFlag">
			<script>
				alert('Attendance not available.');
			</script>
		</s:if>
	</s:else>
	</td></tr></table>
</s:form>

<script type="text/javascript">
	var cntRem = 0;
	
	function callSave()
	{
		var statusFlag = document.getElementById('paraFrm_statusFlag').value;
		if(statusFlag == 'true')
		{
			alert("Salary is already Finalized. So attendance can't be modified!");
			return false;
		}
		var lockFlag = document.getElementById('paraFrm_lockFlag').value;
		if(lockFlag == 'true')
		{
			alert("Attendance is locked.");
			return false;
		}
		else
		{
			if(eCode.length < 1)
			{
				alert("Process the Attendance first!");
				document.getElementById('paraFrm_monthName').focus();
				return false;
			}
		}
		
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		return true;
	}
	
	function callPage(id)
	{
		if(id == 'P')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		if(id == 'N')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		document.getElementById('hdPage').value = id;
		
		if(document.getElementById('paraFrm_lockFlag').value != 'true')
		{
			document.getElementById("confirmationDiv").style.visibility = 'visible';
			document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
			+'<tr><td><b><center>Please select anyone of the option given below</td></tr>'
			+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
			+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithOutSave();"/>'
			+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
		else 
		{
			document.getElementById('paraFrm').action = "MonthAttendance_showAttendance.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function cancel()
	{
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
	}
	
	function proceedWithSave()
	{
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
				
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('paraFrm').action = "MonthAttendance_proceed.action?option=S";
		document.getElementById('paraFrm').submit();
	}
	
	function proceedWithOutSave()
	{
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('paraFrm').action = "MonthAttendance_proceed.action?option=C";
		document.getElementById('paraFrm').submit();
	}
	
	function callMonth()
	{
		document.getElementById('paraFrm_month').value = document.getElementById('paraFrm_monthName').selectedIndex;
	}
	
	function lockAttdn()
	{
		if(eCode.toString() == "")
		{
			alert("Attendnace Not Available!");
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		else
		{
			if(document.getElementById('paraFrm_attdnCode').value == '')
			{
				alert('Please save the Attendnace!');
				return false;
			}
		}		
		if(document.getElementById('paraFrm_lockFlag').value == 'true')
		{
			alert('Attendance is already Locked!');
			return false;
		}
		else
		{
			var conf = confirm('Are you sure!\n You want to Lock the Attendance?');
			if(conf)
			{
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
				document.getElementById("progressBar").style.visibility = "visible";
				document.getElementById("progressBar").style.display = "block";
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	function unLockAttn()
	{
		var conf = confirm('Are you sure!\n You want to Unlock the Attendance?');
		if(conf)
		{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			return true;
		}
		else
		{
			return false;
		}
	}
	
	function processAttn()
	{
		if(document.getElementById('paraFrm_process').value == 'true')
		{
			alert('Attendance is already processed!');
		}
		else if(calAttendance())
		{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			return true;
		}
		return false;
	}
	
	function callAddEmp()
	{
		if(document.getElementById('paraFrm_statusFlag').value == 'true')
		{
			alert("Salary is already finalized! So you can't add employee.");
			return false;
		}
		if(document.getElementById('paraFrm_lockFlag').value == 'true')
		{
			alert('Attendance is already locked!');
			return false;
		}
		
		var savedFlag = document.getElementById('paraFrm_savedFlag').value;		
		if(eCode.length < 1 && savedFlag == 'false')
		{
			alert("Attendance Not Available!");
			return false;
		}
		if(document.getElementById('paraFrm_attdnCode').value == '')
		{
			alert('Please save the Attendnace!');
			return false;
		}
		callsF9(500,325,'MonthAttendance_f9AddEmp.action');
		return true;
	}

	function addEmp()
	{
		try
		{
			var newECode = document.getElementById('paraFrm_newECode').value;
			if(newECode == "")
			{
				alert("Please select an Employee");
				return false;
			}
			
			if(eCode.length > 0)
			{
				for(var i = 0; i < eCode.length; i++)
				{
					if(eCode[i] == newECode)
					{
						alert("Employee is already exists. Please choose another one.");
						document.getElementById('paraFrm_newECode').value = "";
						document.getElementById('paraFrm_newEToken').value = "";
						document.getElementById('paraFrm_newEName').value = "";
						return false;
					}
				}
			}
			
			document.getElementById("paraFrm").action = "MonthAttendance_addEmp.action";
			document.getElementById("paraFrm").submit();
		}
		catch(e)
		{
			alert(e.description);
		}
		return true;
	}
	
	function callSearch()
	{
		if(eCode.toString() == "")
		{
			alert("Attendnace Not Available!");
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		else
		{
			callsF9(500,325,'MonthAttendance_f9SearchEmp.action');
		}
		return true;
	}

	function search()
	{
		try
		{
			var empCode = document.getElementById('paraFrm_empCode').value;
			if(empCode == "")
			{
				alert("Please select an Employee!");
				return false;
			}
			
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			
			var empCode = document.getElementById('paraFrm_empCode').value;
			var found = false;
			for(var i = 0; i < eCode.length; i++)
			{
				if(empCode == eCode[i])
				{
					document.getElementById('eToken'+i).style.background = '#FDFBB0';
					document.getElementById('eName'+i).style.background = '#FDFBB0';
					document.getElementById('attdnDays'+i).style.background = '#FDFBB0';
					document.getElementById('weeklyOffs'+i).style.background = '#FDFBB0';
					document.getElementById('holidays'+i).style.background = '#FDFBB0';
					document.getElementById('paidLevs'+i).style.background = '#FDFBB0';
					document.getElementById('unPaidLevs'+i).style.background = '#FDFBB0';
					document.getElementById('recoveryDays'+i).style.background = '#FDFBB0';
					document.getElementById('salDays'+i).style.background = '#FDFBB0';
					document.getElementById('eSearch'+i).value = 'true';
					found = true;
				}
				else
				{
					document.getElementById('eToken'+i).style.background = '#F2F2F2';
					document.getElementById('eName'+i).style.background = '#F2F2F2';
					
					var lockFlag = document.getElementById('paraFrm_lockFlag').value;
					if(lockFlag == 'true')
					{
						document.getElementById('attdnDays'+i).style.background = '#F2F2F2';
						document.getElementById('weeklyOffs'+i).style.background = '#F2F2F2';
						document.getElementById('holidays'+i).style.background = '#F2F2F2';
						document.getElementById('paidLevs'+i).style.background = '#F2F2F2';
						document.getElementById('unPaidLevs'+i).style.background = '#F2F2F2';
						document.getElementById('recoveryDays'+i).style.background = '#F2F2F2';
					}
					else
					{
						document.getElementById('attdnDays'+i).style.background = 'white';
						document.getElementById('weeklyOffs'+i).style.background = 'white';
						document.getElementById('holidays'+i).style.background = 'white';
						document.getElementById('paidLevs'+i).style.background = 'white';
						document.getElementById('unPaidLevs'+i).style.background = 'white';
						document.getElementById('recoveryDays'+i).style.background = 'white';
					}
					document.getElementById('salDays'+i).style.background = '#F2F2F2';
				}
			}
			if(found)
			{
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
				return false;
			}
			
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			return true;
		}
		catch(e)
		{
			alert('Employee Not Found!');
			return false;
		}
	}

	function calAttendance()
	{
	 
		if(document.getElementById('paraFrm_monthName').selectedIndex == 0)
		{
			alert("Please select the "+document.getElementById('month1').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "")
		{
			alert("Please enter the "+document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divFlag').value == 'true' && document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_typeFlag').value == 'true' && document.getElementById('paraFrm_typeCode').value == "")
		{
			alert("Please select the "+document.getElementById('employee.type').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_payBillFlag').value == 'true' && document.getElementById('paraFrm_payBillNo').value == "")
		{
			alert("Please select the  "+document.getElementById('pay.bill').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_brnFlag').value == 'true' && document.getElementById('paraFrm_brnCode').value == "")
		{
			alert("Please select the  "+document.getElementById('branch').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_deptFlag').value == 'true' && document.getElementById('paraFrm_deptCode').value == "")
		{
			alert("Please select the  "+document.getElementById('department').innerHTML.toLowerCase());
			return false;
		}
		return true;
	}
	
	function getSalDays(name,id)
	{
		try
		{
			var attdnDays = eval(setToZero(document.getElementById('attdnDays'+id).value));
			var weeklyOffs = eval(setToZero(document.getElementById('weeklyOffs'+id).value));
			var holidays = eval(setToZero(document.getElementById('holidays'+id).value));
			var paidLevs = eval(setToZero(document.getElementById('paidLevs'+id).value));
			var unPaidLevs = eval(setToZero(document.getElementById('unPaidLevs'+id).value));
			var presentDays = eval(setToZero(document.getElementById('presentDays'+id).value));
			var salDays = attdnDays + weeklyOffs + holidays + paidLevs;
			
			document.getElementById('salDays'+id).value = salDays;
			
			wrongRecs(id);
		}
		catch(e)
		{
			alert('Please enter numeric values only!');
			wrongRecs(id);
		}
	}
	
	function getAttdnDays(name, id)
	{
		try
		{
			var attdnDays = 0;
			//Connected Attendance Days
			if(document.getElementById('paraFrm_attConnFlag').value == 'true')
			{
				var prevVal = eval(setToZero(document.getElementById("paraFrm_currValue").value));
				var currVal = eval(setToZero(document.getElementById(name+id).value));
				var attnPrevVal = eval(setToZero(document.getElementById("paraFrm_attnCurrVal").value));
				
				var diff = currVal - prevVal;
				attdnDays = attnPrevVal - diff;
			}
			
			//Calculated Attendance Days
			else
			{
				var weeklyOffs = eval(setToZero(document.getElementById('weeklyOffs'+id).value));
				var holidays = eval(setToZero(document.getElementById('holidays'+id).value));
				var paidLevs = eval(setToZero(document.getElementById('paidLevs'+id).value));
				var unPaidLevs = eval(setToZero(document.getElementById('unPaidLevs'+id).value));
				var presentDays = eval(document.getElementById('presentDays'+id).value);
				attdnDays = presentDays - (weeklyOffs + holidays + paidLevs + unPaidLevs);								
			}
			
			document.getElementById('attdnDays'+id).value = attdnDays;
			
			wrongRecs(id);
		}
		catch(e)
		{
			alert('Please enter numeric values only!');
			wrongRecs(id);
		}
	}
	
	function doCal(name, id)
	{
		try
		{
			if(name != "attdnDays")
			{
				getAttdnDays(name,id);
			}			
			getSalDays(name,id);
			return true;
		}
		catch(e)
		{
			alert('Please enter numeric values only!');
			wrongRecs(id);
			return false;
		}
	}
	
	function setToZero(value)
	{
		if(value == "")
		{
			return "0";
		}
		else
		{
			return value;
		}
	}
	
	function callCal(name, id)
	{
		var value = document.getElementById(name+id).value;
		if(value == "")
		{
			if(document.getElementById("paraFrm_currValue").value == "")
			{
				document.getElementById(name+id).value = "0";
			}
			else
			{
				document.getElementById(name+id).value = document.getElementById("paraFrm_currValue").value;
			}
		}
		else
		{
			try
			{
				if(doCal(name, id))
				{
					return true;
				}
			}
			catch(e)
			{
				alert('Please enter numeric values only!');
				wrongRecs(id);
			}
			return false;
		}		
	}
	
	function setPreviousValues(name, id)
	{
		document.getElementById("paraFrm_currValue").value = document.getElementById(name+id).value;
		document.getElementById("paraFrm_attnCurrVal").value = document.getElementById('attdnDays'+id).value;
	}
	
	function checkNumbers(name)
	{
		var count = 0;
		var txtNo = document.getElementById(name).value;
		
		for(var i = 0; i < txtNo.length; i++)
		{
			if(txtNo.charAt(i) == '.')
			{
				count = count + 1;
			}
		}
		
		if(count > 0)
		{
			if(!numbersOnly())
			{
				return false;
			}
		}
		else if(!numbersWithDot(event))
		{
			return false;
		}
		return true;
	}
	
	function viewDetails(eId, attdnCode, paidLevs, totAbsLD, fDate, tDate, dailyConFlag, rowid)
	{
		var save = document.getElementById('eSave'+rowid).value;
		if(save == 'false')
		{
			alert('Please save the attendnace!');
			return false;
		}
		document.getElementById('paraFrm_hiddenEmpid').value = eId;
		document.getElementById('paraFrm_rowid').value = rowid;
		document.getElementById('paraFrm_pLevs').value = paidLevs;
		document.getElementById('paraFrm_totAbsLD').value = totAbsLD;
		document.getElementById('paraFrm_fDate').value = fDate;
		document.getElementById('paraFrm_tDate').value = tDate;
		document.getElementById('paraFrm_attConn').value = dailyConFlag;
		
	 	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "PaidLeaveDetails_retriveDetails.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function selectAll()
	{
		if(document.getElementById("selectId").checked)
		{
			for(var i = 0; i < eCode.length; i++)
			{
				for(var j = 0; j < eChk.length; j++)
				{
					if(eCode[i] == eChk[j])
					{
						document.getElementById('remRec'+i).checked = true;
					}
				}
			}
			cntRem = eChk.length;
	    }
	    else
	    {
	    	for(var i = 0; i < eCode.length; i++)
			{
				for(var j = 0; j < eChk.length; j++)
				{
					if(eCode[i] == eChk[j])
					{
						document.getElementById('remRec'+i).checked = false;
					}
				}
			}
			cntRem = 0;
		}
	}
	
	function checkRecord(id)
	{
		if(document.getElementById('remRec'+id).checked)
		{
			cntRem = cntRem + 1;
		}
		else
		{
			cntRem = cntRem - 1;
		}
		
		if(cntRem == eChk.length)
		{
			document.getElementById("selectId").checked = true;
		}
		else
		{
			document.getElementById("selectId").checked = false;
		}
	}
	
	function removeRecal(fun)
	{
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		
		var cnt = 0;
		var remEmps = "";
		var remDate = "";
		var remLMs = "";
		var remHDs = "";
		
		for(var i = 0; i < eCode.length; i++)
		{
			for(var j = 0; j < eChk.length; j++)
			{
				if(eCode[i] == eChk[j])
				{
					if(document.getElementById('remRec'+i).checked)
					{
						remEmps += eCode[i] + ',';
						remDate += eDate[i] + ',';
						remLMs += eLMs[i] + ',';
						remHDs += eHDs[i] + ',';
						empRecal [cnt] = i + ',' + eCode[i];
						cnt = cnt + 1;
					}
				}
			}			
		}
		
		document.getElementById('paraFrm_remEmps').value = remEmps;
		document.getElementById('paraFrm_remDate').value = remDate;
		document.getElementById('paraFrm_remLMs').value = remLMs;
		document.getElementById('paraFrm_remHDs').value = remHDs;
		
		if(eCode.length < 1)
		{
			alert("Attendance not available!");
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
			return false;
		}
		if(cnt < 1)
		{
			alert('Please select at least one record to ' + fun + '!');
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
			return false;
		}
		
		var conf = confirm('Are you sure!\n You want to ' + fun + ' selected record?');
		if(conf)
		{
			if(fun == 'recalculate')
			{
				retrieveRecalURL('MonthAttendance_reCalculate.action?', 'MonthAttendance', empRecal);
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
				document.getElementById("progressBar").style.visibility = "visible";
				document.getElementById("progressBar").style.display = "block";
			}
		}
		else
		{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
			
			document.getElementById("selectId").checked = false;
			
			for(i = 0; i < eCode.length; i++)
			{
				if(document.getElementById('eSave'+i).value == 'true')
				{
					document.getElementById('remRec'+i).checked = false;
				}
			}
						
			return false;
		}
		
		document.getElementById("overlay").style.visibility = "hidden";
		document.getElementById("overlay").style.display = "none";
		document.getElementById("progressBar").style.visibility = "hidden";
		document.getElementById("progressBar").style.display = "none";
		return true;
	}
	
	gridScroll();
	function gridScroll()
	{
		try
		{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
			
			myST = superTable('tblAttn', {fixedCols : 2, rightCols:3, viewCols:4});
			
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
		}
		catch(e)
		{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";
		}
	}
</script>