<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type">
<title>PF Slip</title>
<link type="text/css" rel="stylesheet"
	href="../pages/kiosk/images/skin.css">
</head>
<body>
<s:form action="KioskPFSlipMaster" method="post" validate="true"
	theme="simple" id="paraFrm" name="form" target="_top">
	<table width="99%" border="0">
		<tbody>
			<tr>
		<td align="right"><input type="button" value="Logout" class="button" name="Submit" style="width:80px;height:30px;background-color:white;color:red;font: bold 16px Arial" onclick="return callLogout();"></td>
		</tr>
			<tr>
				<th scope="col"><img height="128" width="116"
					src="../pages/kiosk/images/umc.gif"></th>
			</tr>
			<tr>
				<th scope="col">
				<table height="240" width="37%" cellspacing="0" cellpadding="0"
					border="0" id="Table_01" align="center">
					<tbody>
						<tr>
							<td width="23"><img height="22" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_01.gif"></td>
							<td width="699"
								background="../pages/kiosk/images/Untitled-1_02.gif"><img
								height="22" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_02.gif"></td>
							<td width="40"><img height="22" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_03.gif"></td>
						</tr>
						<tr>
							<td><img height="187" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_04.gif"></td>
							<td valign="top"
								background="../pages/kiosk/images/Untitled-1_05.gif">
							<table width="100%" border="0">
								<tbody>
									<tr>
										<th width="73%" valign="top" scope="col">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<th scope="col"><img height="52" width="56"
														src="../pages/kiosk/images/b5.gif"></th>
												</tr>
												<tr>
													<th scope="row" class="txt">PF Slip</th>
												</tr>
											</tbody>
										</table>
										</th>
									</tr>
									<tr>
										<th valign="top" scope="col">
										<table width="76%" border="0">
											<tbody>
												<tr><th>&nbsp;</th>
													<th scope="col" class="txt">Year <span class="red">*</span></th>
													<th scope="col"><s:select name="sFinancialYearFrm"
														id="paraFrm_salyr" cssStyle="width:100"
														list="#{'0':'Select --','2000':'2000 - 01','2001':'2001 - 02','2002':'2002 - 03','2003':'2003 - 04','2004':'2004 - 05','2005':'2005 - 06','2006':'2006 - 07','2007':'2007 - 08',
			'2008':'2008 - 09','2009':'2009 - 10','2010':'2010 - 11','2011':'2011 - 12','2012':'2012 - 13','2013':'2013 - 14','2014':'2014 - 15','2015':'2015 - 16','2016':'2016 - 17',
			'2017':'2017 - 18','2018':'2018 - 19','2019':'2019 - 20','2020':'2020 - 21'}" /></th>
												</tr>
												<tr>
													<th>&nbsp;</th>
												</tr>
												<tr>
													<th scope="col">&nbsp;</th>
													<th scope="col"><input type="button" value="Submit"
														style="width: 80px; height: 23px; background-color: #003366; color: white; font: bold 15px Arial"
														;name="Submit" onclick="return callSubmit();"></th>
													<th><input type="button" value="Back" style="width: 80px; height: 23px; background-color: #003366; color: white; font: bold 15px Arial"
														;name="Submit" onclick="return callBack();"></th>
												</tr>
											</tbody>
										</table>
										</th>
									</tr>
									<tr>
										<th valign="top" scope="col">&nbsp;</th>
									</tr>
								</tbody>
							</table>
							</td>
							<td><img height="187" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_06.gif"></td>
						</tr>
						<tr>
							<td><img height="31" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_07.gif"></td>
							<td background="../pages/kiosk/images/Untitled-1_08.gif"><img
								height="31" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_08.gif"></td>
							<td><img height="31" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_09.gif"></td>
						</tr>
					</tbody>
				</table>
				</th>
			</tr>
			<tr>
				<th scope="col"><img height="60" width="231"
					src="../pages/kiosk/images/pp.gif"></th>
			</tr>
		</tbody>
	</table>
	<s:hidden name="empCode" />
	<s:hidden name="sSelectedReportType" value="pdf" />
	<s:hidden name="loginName" />
	<s:hidden name="infoId" />

	</form>
</s:form>
</body>
</html>

<script>

function callSubmit(){
	var salaryYear;
	
	salaryYear = document.getElementById('paraFrm_salyr').value;
	
  	if( salaryYear == 0 ) {
  		alert("Please select year");  
  		return false;
  	}else {
  		document.getElementById('paraFrm').action = "KioskPFSlipMaster_pfSlipReport.action";
  		document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').submit();
  		document.getElementById('paraFrm').target = '';
  	}
  	return true;
}

function callBack() {
 	var empName=document.getElementById('paraFrm_loginName').value;
 	var infoId=document.getElementById('paraFrm_infoId').value;
	document.getElementById('paraFrm').action ="KioskLogin_returnHome.action?empName="+empName+'&infoIds='+infoId;
  	document.getElementById('paraFrm').submit();
  	document.getElementById('paraFrm').target = '';
}
function callLogout(){
			var loginName=document.getElementById('paraFrm_loginName').value;
			document.getElementById('paraFrm').action = "KioskLogin_logout.action?loginName="+loginName;
  			document.getElementById('paraFrm').submit();
}
</script>
