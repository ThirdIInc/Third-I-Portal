<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>


<%
Object annualAttendanceDataObj[][] = (Object[][]) request.getAttribute("annualAttendanceData");
double totalMonthDays = 0;
double totalPresentDays = 0;
double totalLeaveDays = 0;
double totalAbsentDays = 0; 
double totalWeeklyOffs = 0;
double totalHoliDays = 0;
%>

<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">



	<div style="float: left; width: 100%">


	<div style="float: left; width: 100%">



	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>

			<td width="100%" valign="top">
			<table width="100%" border="0" cellspacing="1" cellpadding="1"
				height="100%">

				<tr>
					<td height="25">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="100%" class="formbg">
						<tr>
							<td><a style="cursor: hand" id="mytimecard"
								title="My Timecard" href="javascript:void(0);" class="contlink"
								onclick="javascript:setLeaveAttendanceType('mytimecard');">
							My Timecard </a> <span><strong>|</strong></span> <a
								href="javascript:void(0);" class="contlink" style="cursor: hand"
								id="attendanceregularization" title="Attendance Regularization"
								onclick="javascript:setLeaveAttendanceType('attendanceregularization');">
							Attendance Regularization </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="annualattendance"
								title="Annual Attendance" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('annualattendance');">
							Annual Attendance </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="myleaves"
								title="My Leaves" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('myleaves');"> My
							Leaves </a> <span><strong>|</strong></span> <a class="contlink"
								style="cursor: hand" id="holidays" title="Holidays"
								href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('holidays');">
							Holidays </a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" height="40%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td valign="top" height="28" colspan="10">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="pageHeader">
								<tr>
									<td width="4%"><img
										src="../pages/mypage/images/icons/annualatten.png" width="24"
										height="24" /></td>
									<td width="96%">Annual Attendance</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td valign="top" colspan="10" height="28">Please select
							months & year to view your monthly attendance for specific
							period:</td>
						</tr>
						<tr>
							<td width="20%" height="28" colspan="1">Employee
							&nbsp;&nbsp;&nbsp;: <s:hidden name="annualAttendanceEmpCode" /></td>
							<td width="80%" colspan="9"><s:hidden
								name="annualAttendanceEmpToken"
								value="%{annualAttendanceEmpToken}" /> <s:textfield
								name="annualAttendanceEmpName" size="50"
								value="%{annualAttendanceEmpName}" theme="simple"
								readonly="true" />
								
								<s:if test="%{bean.generalFlag}">
								</s:if>
								<s:else><!--
								 <input style="cursor: hand;" name="Submit3"
								align="absmiddle" type="button" class="button" value="..."
								onclick="Call();" id="ctrlHide" />
								--></s:else>
								</td>
						</tr>

						<tr height="28">
							<td width="20%">From Month :</td>
							<td width="10%"><s:select name="annualAttendanceFromMonth"
								list="#{'1':'January', '2':'February', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', '8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}"></s:select>
							</td>
							<td width="5%">Year :</td>
							<td width="20%">
							<s:textfield name="annualAttendanceFromYear" size="5" maxlength="4" cssStyle="text-align: right;" onkeypress="return numbersOnly();"/> 
						<!-- 	<s:select name="annualAttendanceFromYear"
								list="attendanceFromyearMap" cssStyle="width:70;height:2;" /> -->
							<td width="15%">To Month :</td>
							<td width="10%"><s:select name="annualAttendanceToMonth"
								list="#{'1':'January', '2':'February', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', '8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}"></s:select>
							</td>
							<td width="5%">Year :</td>
							<td width="10%">
							<s:textfield name="annualAttendanceToYear" size="5" maxlength="4" cssStyle="text-align: right;" onkeypress="return numbersOnly();"/> 
							<!--  
							<s:select name="annualAttendanceToYear"
								list="attendanceToyearMap" cssStyle="width:70;height:2;" />
							-->		
								</td>

							<td width="3%"><input type="button" name="show" value="Show"
								class="token" onclick="showFunction();"></td>

							<td width="2%">&nbsp;</td>
						</tr>



					</table>
					</td>
				</tr>
				<tr>
					<td valign="top" height="100%">
					<table width="100%" cellspacing="1" cellpadding="0" border="0"
						bordercolor="red">
						<tr>
							<td width="5%" height="25" class="mypageTh">Month</td>
							<td width="5%" height="25" class="mypageTh" align="center">Year</td>
							<td width="10%" height="25" class="mypageTh" align="right">Month
							Days</td>
							<td width="10%" height="25" class="mypageTh" align="right">Present
							Days</td>
							<td width="10%" height="25" class="mypageTh" align="right">Leave
							Days</td>
							<td width="10%" height="25" class="mypageTh" align="right">Absent
							Days</td>
							<td width="10%" height="25" class="mypageTh" align="right">Weekly
							Offs</td>
							<td width="10%" height="25" class="mypageTh" align="right">Holidays</td>
						</tr>
						<%
						if(annualAttendanceDataObj!=null && annualAttendanceDataObj.length > 0){
							for(int i=0;i<annualAttendanceDataObj.length;i++)	{
					%>
						<tr>
							<td height="25" class="sortableTD" align="left"><%=Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][0])) %></td>
							<td height="25" class="sortableTD" align="center"><%=Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][1])) %></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][2]))) %></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][3]))) %></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][4])))%></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][5]))) %></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][6]))) %></td>
							<td height="25" class="sortableTD" align="right"><%=Utility.twoDecimals(Utility.checkNull(String.valueOf(annualAttendanceDataObj[i][7]))) %></td>
						</tr>

						<%	
						totalMonthDays = totalMonthDays + Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][2]));
					totalPresentDays = totalPresentDays + Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][3]));
					totalLeaveDays = totalLeaveDays +Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][4]));
					totalAbsentDays = totalAbsentDays + Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][5]));
					totalWeeklyOffs = totalWeeklyOffs + Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][6]));
					totalHoliDays = totalHoliDays + Double.parseDouble(String.valueOf(annualAttendanceDataObj[i][7]));
						}
					%>
						<tr align="right" class="sumTD">
							<td height="20">
							<div class="sumTD">Total</div>
							</td>
							<td height="20">&nbsp;</td>
							<td height="20"><%=Utility.twoDecimals(totalMonthDays)  %></td>
							<td height="20"><%=Utility.twoDecimals(totalPresentDays)  %></td>
							<td height="20"><%= Utility.twoDecimals(totalLeaveDays) %></td>
							<td height="20"><%= Utility.twoDecimals(totalAbsentDays) %></td>
							<td height="20"><%= Utility.twoDecimals(totalWeeklyOffs) %></td>
							<td height="20"><%=Utility.twoDecimals(totalHoliDays)  %></td>
						</tr>

						<%		
						}
					%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	</div>
</s:form>

<script> 

	onLoad();
	function onLoad()
	{
		document.getElementById('annualattendance').className='contlink';
	}

function Call(){
	try{
	//alert("befor action call " );
	callsF9(500,325,'MypageProcessManagerAlerts_f9actionEmpAnnualAttendance.action');
	//alert("after action call ");
	}catch(e){
		alert("Error:::::"+e.description);
	}
}

function setLeaveAttendanceType(type){
		//document.getElementById('menuoremployee').value=type;
		//document.getElementById('emp').className='';
		//document.getElementById('menu').className='';
		if(type=="mytimecard"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="attendanceregularization"){
		document.getElementById('mytimecard').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_attendanceRegularization.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="annualattendance"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('mytimecard').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_annualAttendance.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="myleaves"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('mytimecard').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myLeaves.action';
		document.getElementById('paraFrm').submit();
		}
		
		if(type=="holidays"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('mytimecard').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action';
		document.getElementById('paraFrm').submit();
		}
}

function showFunction(){
	
		try{
	var annualAttendanceEmpCode = document.getElementById('paraFrm_annualAttendanceEmpCode').value;
	var annualAttendanceEmpToken = document.getElementById('paraFrm_annualAttendanceEmpToken').value;
	var annualAttendanceEmpName = document.getElementById('paraFrm_annualAttendanceEmpName').value;
	var annualAttendanceFromMonth=document.getElementById('paraFrm_annualAttendanceFromMonth').value;
	var annualAttendanceFromYear=document.getElementById('paraFrm_annualAttendanceFromYear').value;
	var annualAttendanceToMonth=document.getElementById('paraFrm_annualAttendanceToMonth').value;
	var annualAttendanceToYear=document.getElementById('paraFrm_annualAttendanceToYear').value;
	document.getElementById('paraFrm').target = "_self";
	/*
	if(annualAttendanceEmpCode==""){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_annualAttendance.action?annualAttendanceFromMonth='+annualAttendanceFromMonth+'&annualAttendanceFromYear='+annualAttendanceFromYear+'&annualAttendanceToMonth='+annualAttendanceToMonth+'&annualAttendanceToYear='+annualAttendanceToYear;
	}else{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_annualAttendance.action?annualAttendanceEmpCode='+annualAttendanceEmpCode+'&annualAttendanceFromMonth='+annualAttendanceFromMonth+'&annualAttendanceFromYear='+annualAttendanceFromYear+'&annualAttendanceToMonth='+annualAttendanceToMonth+'&annualAttendanceToYear='+annualAttendanceToYear;		
	}
	document.getElementById('paraFrm').submit();
	*/
	
	if(annualAttendanceFromYear!=annualAttendanceToYear)
	{
	
		alert("From year and to year should be same.");
		return false;
	}
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_annualAttendance.action?annualAttendanceEmpCodeStr='+annualAttendanceEmpCode+'&annualAttendanceEmpTokenStr='+annualAttendanceEmpToken+'&annualAttendanceEmpNameStr='+annualAttendanceEmpName+'&annualAttendanceFromMonthStr='+annualAttendanceFromMonth+'&annualAttendanceFromYearStr='+annualAttendanceFromYear+'&annualAttendanceToMonthStr='+annualAttendanceToMonth+'&annualAttendanceToYearStr='+annualAttendanceToYear;		
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		alert("Error Annual Attendance  "+e);
	}
	
}
</script>