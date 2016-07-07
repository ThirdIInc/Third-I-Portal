<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ExperienceMISReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction"
		value='ExperienceMISReport_expReport.action' />
	<s:hidden name="report" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Experience
					MIS Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap" width="5%"><a href="#"
								onclick="callReset()();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
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
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg" id="reportBodyTable">
						<tr>
							<td>
							<table width="100%" border="0"  align="center" cellpadding="1"
								cellspacing="2">
								<!--  Division	and Department-->
								<tr>
									<td width="20%"><label class="set" name="division" id="division"
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
									<td colspan="2"><s:hidden name="divCode" /><s:textarea
										name="divsion" theme="simple" rows="1" cols="100" readonly="true" /></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_divsion',350,250,'ExperienceMISReport_f9MultiDiv.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="department" id="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td colspan="2"><s:hidden name="deptCode"
										theme="simple" /> <s:textarea name="deptName" theme="simple"
										rows="1" cols="100" readonly="true" /></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18"  width="18"
										onclick="javascript:callDropdown('paraFrm_deptName',350,250,'ExperienceMISReport_f9MultiDept.action',event,'false','no','right')"></td>
								</tr>
								<!-- BRANCH AND DESIGNATIONs -->
								<tr>
									<td width="20%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
									</td>
									<td colspan="2"><s:hidden name="centerId"
										theme="simple" /><s:textarea name="centerName"
										theme="simple" rows="1" cols="100" readonly="true" /></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" width="18"
										onclick="javascript:callDropdown('paraFrm_centerName',350,250,'ExperienceMISReport_f9Brch.action',event,'false','no','right')"></td>
								</tr>
								<tr>
									<td width="20%"><label class="set" name="designation"
										id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
									</td>
									<td colspan="2"><s:hidden name="desgCode"
										theme="simple" /> <s:textarea name="desgName" theme="simple"
										rows="1" cols="100" readonly="true" /></td>
									<td width="35%"><img src="../pages/images/recruitment/search2.gif" height="18" width="18"
										onclick="javascript:callDropdown('paraFrm_desgName',350,250,'ExperienceMISReport_f9MultiRank.action',event,'false','no','right')">
									</td>
								</tr>
								<!--  Employee -->
								
								
								<tr>
									<td width="20%" colspan="1"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
									</td>
									<td colspan="2"><s:textfield theme="simple" name="token"
										readonly="true"  size="20"/><s:hidden theme="simple" name="empid" /><s:textfield
										theme="simple" name="empName" size="70" readonly="true" />
									<img
										src="../pages/images/recruitment/search2.gif" height="18"
										width="18"
										onclick="javascript:callsF9(500,325,'ExperienceMISReport_f9action.action');">
									</td>
								</tr>													`
								<!-- Service Status -->
								<tr>
									<!--<td><label class="set" name="report.type" id="report.type"
										ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
									:</td>  
									<td><s:select theme="simple" name="reportType"
										headerKey="Pdf" headerValue="Pdf"
										list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>-->
									<td class="formtext"><label name="status" id="status"
										ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
									<td colspan="3"><label> <s:select name="status" headerKey="-1"
										headerValue="--Select--"
										list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
									</label></td>
								</tr>
								<!-- Added by Nilesh 20th June 2011 -->
								<tr>
									<td width="20%"><label class="set"
										id="total.experience.years" name="total.experience.years"
										ondblclick="callShowDiv(this);"><%=label.get("total.experience.years")%></label>:</td>
									<td colspan="3"><input type="checkbox"
										name="checkFlag" id="checkFlag" onclick="callCheck();" /></td>
								</tr>
								<!-- Added by Nilesh End here 20th June 2011 -->					
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<br />
			<label></label></td>
		</tr>
		<tr>
			<td>
			<div id="bottomButtonTable"></div>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap" width="5%"><a href="#"
						onclick="callReset()();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript">

function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='ExperienceMISReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
		
	
function callReset(){
  	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='ExperienceMISReport_clear.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="_self";
	}
	
 function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked;
 
 	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	}
 }
 
   function callReport(type){
	document.getElementById('paraFrm_report').value=type;
	callReportCommon(type);		
 	}
 	
 	function mailReportFun(type)
 	{
  		document.getElementById('paraFrm_report').value=type;		
		//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
		document.getElementById('paraFrm').action='ExperienceMISReport_mailReport.action';
		document.getElementById('paraFrm').submit();
		//return true;
	}
	
</script>

<script language="JavaScript">
  var obj= document.getElementById("topButtonTable").cloneNode(true);
  document.getElementById("topButtonTable").appendChild(obj);
</script>


