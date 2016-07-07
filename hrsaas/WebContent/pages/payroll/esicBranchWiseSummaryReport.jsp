<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="EsicBranchWiseReport" validate="true" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
<s:hidden name="divAbbr"/>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="3%" valign="bottom" class="txt"><strong
							class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">ESIC Branchwise Summary Report</strong></td>
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
					<tr valign="middle">
						<td nowrap="nowrap">
							<a href="#" onclick="resetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
						</td>
						<td width="100%">
							<%@ include file="/pages/common/reportButtonPanel.jsp"%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div name="htmlReport" id='reportDiv' style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
					<iframe id="reportFrame" frameborder="0" onload=alertsize();
					style="vertical-align: top; float: left; border: 0px solid;"
					allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
					marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
					width="100%" height="200"></iframe>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg" id='reportBodyTable'> 
					<tr>
						<td colspan="4">
							<strong>
								<label id="selectPeriod" name="selectPeriod"	ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label  class = "set"  id="frmmonth" name="frmmonth" ondblclick="callShowDiv(this);"><%=label.get("frmmonth")%></label><font color="red">*</font>:</td>
						<td width="30%">
							<s:select theme="simple" name="month" cssStyle="width:152" list="#{'':'--Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
						</td>
						<td width="20%">
							<label  class = "set"  id="toyear" name="toyear" ondblclick="callShowDiv(this);"><%=label.get("toyear")%></label><font color="red">*</font>:</td>
						<td width="30%">
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25"  />
						</td>
					</tr>	
					<tr>
						<td colspan="4">
							<strong>
								<label id="selectReportFilter" name="selectReportFilter" ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
						<td colspan="3">
						<s:hidden name="divId" /> 
						<s:textfield name="divName" theme="simple" readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif"	class="iconImage" height="18" align="absmiddle" width="18"	onclick="javascript:callsF9(500,325,'EsicBranchWiseReport_f9division.action');">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
					<tr valign="middle">
						<td nowrap="nowrap">
							<a href="#" onclick="resetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
						</td>
						<td width="100%">
							<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='EsicBranchWiseReport_report.action'/>
</s:form>

<script>
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				callReportCommon(type);
			}
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='EsicBranchWiseReport_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function reportFun(){
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="EsicBranchWiseReport_report.action";
				document.getElementById('paraFrm').submit();
			}
	}
	
	function validateFields(){
		if(document.getElementById("paraFrm_month").value==""){
	 		alert("Please select the "+document.getElementById('frmmonth').innerHTML.toLowerCase());
	 		return false;
 		}
 		if(document.getElementById("paraFrm_year").value==""){
 			alert("Please enter "+document.getElementById('toyear').innerHTML.toLowerCase());
 			return false;
 		}
 		if(document.getElementById("paraFrm_divName").value==""){
 			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
 			return false;
 		}	
 		return true;
	} 
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EsicBranchWiseReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
</script>