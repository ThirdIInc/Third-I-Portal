<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/lib/dropdown.jsp"%>

<s:form action="LicMisReport" method="post" id="paraFrm" theme="simple">
<s:hidden name="report" />
	<s:hidden name="reportAction" value='LicMisReport_getReport.action' />
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
					<td width="93%" class="txt"><strong class="text_head">LIC
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
			
								<!-- Division -->
								
								<tr>
									<td width="20%"><label class="set" name="division"
										id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
									</td>
									<td colspan="2"><s:hidden name="divCode" /> <s:textarea
										cols="100" rows="1" name="divisionName" theme="simple"
										readonly="true" /></td>
									<td width="35%"><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callDropdown('paraFrm_divisionName',350,250,'LicMisReport_f9div.action',event,'false','no','right'),callMe();">
									</td>

								</tr>
								
								<!--	BRANCH   	-->
								
								<tr>
									<td width="20%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
									</td>
									<td colspan="2"><s:hidden name="centerId" /> <s:textarea
										cols="100" rows="1" name="centerName" theme="simple"
										readonly="true" /></td>
									<td width="30%"><img
										src="../pages/images/recruitment/search2.gif" height="18" class="iconImage"
										align="absmiddle" width="18"
										onclick="javascript:callDropdown('paraFrm_centerName',350,250,'LicMisReport_f9center.action',event,'false','no','right'),callMe();">

									</td>
									
								</tr>
								<!-- Pay Bill Filter-->
								<tr>
									<td><label class="set" name="paybill" id="paybill"
										ondblclick="callShowDiv(this);"> <%=label.get("paybill")%></label>:
									</td>
									<td colspan="2"><s:hidden name="payBillId" /><s:textarea
										cols="100" rows="1" name="payBillName" theme="simple"
										readonly="true" /> </td>
										 <td width="30%"><img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18" class="iconImage"
										onclick="javascript:callDropdown('paraFrm_payBillName',350,250,'LicMisReport_f9payBill.action',event,'false','no','right'), callMe();">
									</td>
								</tr>
								<tr>
									<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
									</td>
								</tr>
								
								<!-- Employee Filter -->

								<tr>
									<td width="20%" colspan="1"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);">
									<%=label.get("employee")%></label>:</td>
									<td colspan="3"><s:textfield theme="simple" size="22"
										name="token" readonly="true" /> <s:hidden theme="simple"
										name="empid" /> <s:textfield theme="simple" name="empName"
										size="70" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'LicMisReport_f9action.action');">
									</td>
									

						
								</tr>
								<!--  -->



							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
							<td width="100%" colspan="6" align="right"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
						</tr>
					</table>
					</td>
				</tr>
				</td>
				</tr>
			</table>
			<br />
			<label></label></td>
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



	function mailReportFun(type){
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='LicMisReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	function callReset(){
		document.getElementById('paraFrm').action='LicMisReport_clear.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="_self";
	}
	
	function callReport(type){	
	try{
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	
	function callValidate()
	{
	  var divisionVar=document.getElementById("divisionName").value;
	  
	  if(divisionVar==""){
	  alert("Please Select division.");
	  return false;
	  }
	}
	
	
	function callMe() {
		document.getElementById("paraFrm_empName").value="";
		document.getElementById("paraFrm_empid").value="";
		document.getElementById("paraFrm_token").value="";
	}
	
</script>
