<!-- Date: 09-08-2012 Ganesh -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AccountMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Usage Tracking Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right" width="100%">
			<div align="right"><font color="red">*</font> Indicates
			Required</div>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td width='60%' nowrap="nowrap"><a href="#"
						onclick="resetFun()"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" onclick="resetFun()" title="Reset"><span
						style="padding-left: 5px;">Reset</span></a> &nbsp;|&nbsp;
						<a href="#"
						onclick="callBackFun()"> <img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" onclick="callBackFun()" title="Back"><span
						style="padding-left: 5px;">Back</span></a> &nbsp;&nbsp;
						</td>

					<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
					<img src="../pages/images/buttonIcons/file-pdf.png"
						class="iconImage" align="absmiddle" " title="PDF"><span
						style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
					<img src="../pages/images/buttonIcons/file-xls.png"
						class="iconImage" align="absmiddle" onclick="callReport('Xls');"
						title="Excel"><span style="padding-left: 5px;">Excel</span></a>
					&nbsp;&nbsp;</td>
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
			<td width="100%">
			<table width="100%" border="0" id="reportBodyTable">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">

						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="1"
								cellspacing="2">
								<tr>
									<td colspan="6" class="formhead"><strong
										class="forminnerhead">Select Period </strong></td>
								</tr>
								<tr>
									<td width="17%" class="formtext"><label class="set"
										id="from.date" name="from.date"
										ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font
										color="red">*</font></td>
									<td width="25%"><s:textfield name="fromDate" size="15"
										onkeypress="return numbersWithHiphen();" theme="simple"
										maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="15%" class="formtext"><label class="set"
										id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font
										color="red">*</font><s:hidden name="today" /></td>
									<td width="20%"><s:textfield name="toDate" size="15"
										onkeypress="return numbersWithHiphen();" theme="simple"
										maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
									<td></td>
								</tr>
							</table>
							</td>
						</tr>
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
					<td width='60%' nowrap="nowrap"><a href="#"
						onclick="resetFun()"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" onclick="resetFun()" title="Reset"><span
						style="padding-left: 5px;">Reset</span></a>  &nbsp;|&nbsp;
						<a href="#"
						onclick="callBackFun()"> <img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" onclick="callBackFun()" title="Back"><span
						style="padding-left: 5px;">Back</span></a> &nbsp;&nbsp;</td>

					<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
					<img src="../pages/images/buttonIcons/file-pdf.png"
						class="iconImage" align="absmiddle" " title="PDF"><span
						style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
					<img src="../pages/images/buttonIcons/file-xls.png"
						class="iconImage" align="absmiddle" onclick="callReport('Xls');"
						title="Excel"><span style="padding-left: 5px;">Excel</span></a>
					&nbsp;&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="report" />

	<s:hidden name="reportAction"
		value='CustomerHistoryReport_getReport.action' />

</s:form>
<script>


function callReport(type){
try{
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
				//alert(type);
				document.getElementById('paraFrm').action="AccountMaster_generateCustomerHistoryReport.action";	
				document.getElementById('paraFrm').submit();
			}
			}catch(e){
			alert(e);
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
				document.getElementById('paraFrm').action="AccountMaster_generateCustomerHistoryReport.action";	
				document.getElementById('paraFrm').submit();
			}
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }	
	 
	 
}

function callBackFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AccountMaster_input.action';
		document.getElementById('paraFrm').submit();
}




function resetFun(){ 
	document.getElementById("paraFrm_fromDate").value="";
	document.getElementById("paraFrm_toDate").value="";
}
function validateFields(){
		///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		
		if(trim(document.getElementById('paraFrm_toDate').value) == "") {
				alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
		}
		
  		if(!validateDate("paraFrm_fromDate","from.date")){
  			return false;
  		
  		}
  		if(!validateDate("paraFrm_toDate","to.date")){
		return false;
		}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
		return false;
		}
		
		return true;
	}

function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
	//	alert(1);
		document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'CustomerHistoryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='CustomerHistoryReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>