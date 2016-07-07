<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AddressDetailsMis" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='AddressDetailsMis_report2.action' />
	<s:hidden name="report" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Address
					Details MIS </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%" colspan="1"><a href="#"
								onclick="callReset();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a></td>
							<td width="92%"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%>
							</td>
						</tr>

					</table>
					<label></label></td>
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
				<tr>
					<td colspan="4">
					<table width="100%" class="formbg" border="0" id="reportBodyTable">
						<tr>
							<td width="20%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
							<td colspan="2"><s:hidden name="divCode" /> <s:textarea
								cols="100" rows="1" theme="simple" readonly="true"
								name="divisionName" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_divisionName',350,250,'AddressDetailsMis_f9div.action',event,'false','no','right')"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="department"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
							<td colspan="2"><s:hidden name="deptCode" /> <s:textarea
								cols="100" rows="1" theme="simple" readonly="true"
								name="deptName" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_deptName',350,250,'AddressDetailsMis_f9dept.action',event,'false','no','right')"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="branch" name="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
							<td colspan="2"><s:hidden name="centerNo" /> <s:textarea
								cols="100" rows="1" theme="simple" readonly="true"
								name="centerName" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_centerName',350,250,'AddressDetailsMis_f9center.action',event,'false','no','right')"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="designation"
								name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
							<td colspan="2"><s:hidden name="desgCode" /> <s:textarea
								cols="100" rows="1" theme="simple" readonly="true"
								name="desgName" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_desgName',350,250,'AddressDetailsMis_f9desg.action',event,'false','no','right')"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td colspan="3"><s:textfield theme="simple" readonly="true"
								name="empToken" size="22" /> <s:hidden name="employeeId" /><s:textfield
								size="70" theme="simple" readonly="true" name="empName" /> <img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'AddressDetailsMis_f9action.action');">
							</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="empStatus"
								name="empStatus" ondblclick="callShowDiv(this);"><%=label.get("empStatus")%></label></td>
							<td colspan="3"><s:select name="employeeStatus"
								cssStyle="width:140;z-index:5;"
								list="#{'-1':'--Select--','S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" /></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="add.type"
								name="add.type" ondblclick="callShowDiv(this);"><%=label.get("add.type")%></label></td>
							<td colspan="3"><s:select name="addressType" list="assetmap"
								headerKey="-1" headerValue="--All--" size="1"
								cssStyle="width:140;z-index:5;" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%" colspan="1"><a href="#"
								onclick="callReset();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a></td>
							<td width="92%"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%>
							</td>
						</tr>

					</table>
					<label></label></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
		document.getElementById("bottomButtonTable").appendChild(obj); 
		
function callReset()
 		{	 
 			document.getElementById('paraFrm').target = "_self";
	 		document.getElementById('paraFrm').action="AddressDetailsMis_clear.action";
	 		document.getElementById('paraFrm').submit();
	 		
		}
function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='AddressDetailsMis_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	



function callReport(type){
		document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
			
}

function mailReportFun(type)
{
		
			document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='AddressDetailsMis_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		
	}



</script>
