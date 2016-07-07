<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="QualificationReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='QualificationReport_getReport.action' />

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
					<td width="93%" class="txt"><strong class="text_head">Qualification
					MIS Report</strong></td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
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
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg" id="reportBodyTable">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<!--  Division	-->
								<tr>
									<td width="20%"><label class="set" name="division"
										id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
									</td>
									<td colspan="2"><s:hidden name="divCode" /> <s:textarea
										cols="100" rows="1" name="divsion" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_divsion',350,250,'QualificationReport_f9div.action',event,'false','no','right')">
									</td>

								</tr>
								<!--  Department -->
								<tr>
									<td width="20%"><label class="set" name="department"
										id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
									</td>

									<td colspan="2"><s:hidden name="deptCode" /> <s:textarea
										cols="100" rows="1" name="deptName" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_deptName',350,250,'QualificationReport_f9dept.action',event,'false','no','right')">
									</td>
								</tr>
								<!-- BRANCH   -->

								<tr>
									<td width="20%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
									</td>

									<td colspan="2"><s:hidden name="centerId" /> <s:textarea
										cols="100" rows="1" name="centerName" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_centerName',350,250,'QualificationReport_f9center.action',event,'false','no','right')">
									</td>

								</tr>
								<!-- DESIGNATIONs -->
								<tr>
									<td width="20%"><label class="set" name="designation"
										id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
									</td>

									<td colspan="2"><s:hidden name="desgCode" /> <s:textarea
										cols="100" rows="1" name="desgName" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_desgName',350,250,'QualificationReport_f9desg.action',event,'false','no','right')">
									</td>
								</tr>
								<!-- Qualification -->
								<tr>
									<td width="20%"><label class="set" name="qualification"
										id="qualification" ondblclick="callShowDiv(this);"><%=label.get("qualification")%></label>
									:</td>

									<td colspan="2"><s:hidden name="quaCode" /> <s:textarea
										cols="100" rows="1" name="quaName" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_quaName',350,250,'QualificationReport_f9qua.action',event,'false','no','right')">
									</td>

								</tr>
								<!--  Employee -->
								<tr>
									<td width="20%"><label class="set" name="employee"
										id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
									</td>
									<td colspan="3"><s:textfield theme="simple"
										name="empToken" size="22" readonly="true" /><s:hidden
										theme="simple" name="empid" /><s:textfield theme="simple"
										name="empName" size="70" readonly="true" />
										<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'QualificationReport_f9action.action');">
									</td>

									<!--<td width="80%" colspan="3"><s:hidden theme="simple"
										name="empid" /> <s:textarea cols="78" rows="1" theme="simple"
										name="empName" readonly="true" /> 
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'QualificationReport_f9action.action');">
									</td>

									-->
									<!--<td width="70%" colspan="2"> <s:hidden name="empid" /> 
										<s:textarea cols="78" rows="1" theme="simple" name="empName" readonly="true" />
								
										<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_empName',350,250,'QualificationReport_f9action.action',event,'false','no','right')">
								   </td>
								-->
								</tr>

									<!-- Show all qualification -->
								<tr>
									<td></td>
									<td width="20%"><label class="set" name="showAllQuali"
										id="showAllQuali" ondblclick="callShowDiv(this);"><%=label.get("showAllQuali")%></label>:
									<input type="checkbox" name="showAllQualifications"
										id="showAllQualificationsID" onclick="callAllQualCheck();" />
									</td>
									<td width="30%"> 
									<label class="set" name="showHighestQuali"
										id="showHighestQuali" ondblclick="callShowDiv(this);"><%=label.get("showHighestQuali")%></label>:
									<input type="checkbox" name="showHighestQualification"
										id="showHighestQualificationID"
										onclick="callHighestQualCheck();" /></td.
								</tr>

								<!-- Status -->
								<tr />
								<tr>
									<td class="formtext" width="20%"><label name="status"
										id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
									:</td>
									<td colspan="3"><label> <s:select name="status" headerKey=""
										headerValue="--Select--"
										list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
									</label></td>
								</tr>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="callReset();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>

</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
<script type="text/javascript">

	function callMisReport(){
	  	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action='QualificationReport_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}
	
	function callReset(){
	  	document.getElementById('paraFrm').target='_self';
		document.getElementById('paraFrm').action='QualificationReport_clear.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="_self";
	}

	function callAllQualCheck(){
	  if(document.getElementById("showAllQualificationsID").checked==true){
	  	  document.getElementById("showHighestQualificationID").checked=false;
	  	  document.getElementById("showAllQualificationsID").checked=true;
	  	  var checkDefault = document.getElementById("showAllQualificationsID").checked;
 	
		 	if(checkDefault){
		 			document.getElementById("showAllQualificationsID").value="Y";
		 	}
	  }
	}
	function callHighestQualCheck(){
	  if(document.getElementById("showHighestQualificationID").checked==true){
	  	 document.getElementById("showAllQualificationsID").checked=false;
	  	 document.getElementById("showHighestQualificationID").checked=true;
	  	 var checkDefault = document.getElementById("showHighestQualificationID").checked;
 	
		 	if(checkDefault){
		 			document.getElementById("showHighestQualificationID").value="Y";
		 	}
	  }
	}
	
	//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='QualificationReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	//Added by Tinshuk Banafar ...End....
	
</script>
