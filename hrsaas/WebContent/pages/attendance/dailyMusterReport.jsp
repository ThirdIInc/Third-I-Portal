<%--@author:Reeba Joseph--%>
<%--August 11, 2010--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="DailyMusterReport" name="DailyMusterReport" id="paraFrm"
	theme="simple">

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Daily
					Muster Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td><!--<input type="button" name="report" value="Show Report"
						class="token" onclick="return showReport();" /> <s:submit
						cssClass="reset" action="DailyMusterReport_reset"
						theme="simple" value="Reset"  />
						--></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>
		<tr>
			<td>
			<table width=100% " class="formbg" border="0" id="reportBodyTable">
				<s:hidden name="vCode" value="%{vCode}" />

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">

						<tr>
							<td> 
							<table width="100%" border="0" align="center" cellpadding="1"
								cellspacing="2">
								<tr>
									<td colspan="6" class="formhead"><strong
										class="forminnerhead">Select Period </strong></td>
								</tr>
								<tr>
									<td width="13%"><label id="month" name="month"
										ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
										color="red">*</font>:</td>
									<td width="23%"><s:select theme="simple" name="month"
										cssStyle="width:152"
										list="#{'0':'--Select--','01':'January','02':'February','03':'March','04':'April','05':'May',
						'06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /></td>
									
									<td width="15%"><label id="year" name="year"
										ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
										color="red">*</font>:</td>
									<td width="25%"><s:textfield name="year"
										onkeypress="return numbersOnly();" theme="simple"
										maxlength="4" size="8" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"> 
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">

						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="1"
								cellspacing="2">
								<tr>
									<td class="formhead"><strong class="forminnerhead">Select
									Filters </strong></td>
								</tr>


								<tr>
									<td width="15%"><label class="set" id="division1"
										name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
										color="red">*</font></td>
									<td></td>
									<td></td>
									<td colspan="6"><s:hidden name="divCode" /> <s:textarea
										cols="100" rows="1" theme="simple" readonly="true"
										name="divName" /></td>
									<td width="25%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_divName',350,250,'DailyAttendanceReport_f9Div.action',event,'false','no','right')"></td>
								</tr>

								<tr>
									<td width="15%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td></td>
									<td></td>
									<td colspan="6"><s:hidden name="brnCode" /> <s:textarea
										cols="100" rows="1" theme="simple" readonly="true"
										name="brnName" /></td> 
									<td width="25%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_brnName',350,250,'DailyAttendanceReport_f9Branch.action',event,'false','no','right')"></td>
								</tr> 


								<!--<tr>
					<td width="15%"><label id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label><font
						color="red">*</font>:</td>
					<td><s:select name="reportType" headerKey="1"
						headerValue="--Select--" list="#{'Pdf':'Pdf', 'Xls':'Xls'}" /></td>
				</tr>
			-->

							</table>
							</td>
						</tr>



					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
							<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="100%"><%@ include
								file="/pages/common/reportButtonPanelBottom.jsp"%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table></td></tr></table>
			<s:hidden name="reportType" /> <s:hidden name="reportAction"
				value='DailyMusterReport_getReport.action' />
</s:form>

<script type="text/javascript">
getYear();
function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_reportType').value=type;
				callReportCommon(type);
			}
}
function generateReport(type)
 {	
	try{
	//document.getElementById('paraFrm_report').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="DailyMusterReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
	 }catch (e)
	 {
	 	alert(e);
	 }	
}

function validateFields(){
		try{
				var name = ['paraFrm_year'];
				var label = ['year'];
				var flag = ["enter"];
				
				if(!validateBlank(name, label, flag)) {
					return false;
				}
				   
				var month  =document.getElementById("paraFrm_month").value;
				var mont=document.getElementById('month').innerHTML.toLowerCase();
				if(month =='0'){
				 	alert("Select "+mont); 
				 	return false; 
				}
				
				var divNm   =document.getElementById("paraFrm_divCode").value;
				if(divNm==""){
				 	alert("Please select the Division");
				 	document.getElementById('paraFrm_divName').focus();
				 	return false;
			 	}
			 
			 }catch(e){
			 ///alert(e);
			 }
		return true;
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='DailyMusterReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="DailyMusterReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	
</script>