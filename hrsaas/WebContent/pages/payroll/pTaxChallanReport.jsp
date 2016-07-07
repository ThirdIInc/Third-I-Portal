<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PTaxChallanReport" validate="true" id="paraFrm"
	validate="true" theme="simple" target="main">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Professional
					Tax Challan Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />
			<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
						<tr>
							<td width="100%">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
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
								style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
							<iframe id="reportFrame" frameborder="0" onload=alertsize();
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
								marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
								width="100%" height="200"></iframe> </div>
							</td>
						</tr>
					</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable"  >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectPeriod" name="selectPeriod" ondblclick="callShowDiv(this);">
								<%=label.get("selectPeriod")%></label></b>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="month" name="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="month" cssStyle="width:152"
								list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label id="year" name="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield name="year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="10" /></td>
						</tr>
					</table>	
					</td>
				</tr>	
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
								
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectFilters" name="selectFilters" ondblclick="callShowDiv(this);">
								<%=label.get("selectFilters")%></label></b>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="divisionName" name="divisionName"
								ondblclick="callShowDiv(this);"><%=label.get("divisionName")%></label>:<font
								color="red">*</font></td>
							<td width="30%"><s:hidden name="divCode" /><s:hidden
								name="divAddress" /> <s:textfield name="divName" theme="simple"
								readonly="true" maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PTaxChallanReport_f9division.action');">
							</td>
							<td width="20%"><label id="state" name="state"
								ondblclick="callShowDiv(this);"><%=label.get("state")%></label>:<font
								color="red">*</font></td>
							<td width="30%"><s:hidden name="stateCode" /> <s:textfield
								name="stateName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PTaxChallanReport_f9state.action');">
							</td>
						</tr>
					</table>	
					</td>
					
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
									<td width="100%"><%@ include
										file="/pages/common/reportButtonPanelBottom.jsp"%>
									</td>
								</tr>
							</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->

	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='PTaxChallanReport_report.action' />
</s:form>

<script>
function callReport(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}	
} 

function validateFields() {
	var state =document.getElementById('paraFrm_stateCode').value;
	var division =document.getElementById('paraFrm_divCode').value;
	var month =document.getElementById('paraFrm_month').value;
	var yr =document.getElementById("paraFrm_year").value;
	if(month=="0") {
		alert("Please select month");
		return false;
	}
	if(yr==''){
	  	alert("Please select year");
		return false;
	}	
	if(division=="") {
		alert("Please select division");
		return false;
	}
	if(state=="") {
		alert("Please select state");
		return false;
	}
	return true;
} 

function resetFun(){
	document.getElementById('paraFrm').action='PTaxChallanReport_reset.action';
	document.getElementById('paraFrm').submit();
}

 function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
	  //	document.getElementById('vpf1').style.display='none'; 
 	//	document.getElementById('vpf2').style.display='none'; 
	  
}

getYear();

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='PTaxChallanReport_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}
</script>
