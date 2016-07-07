
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="Form12BA" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">FORM
					NO. 12BA</strong></td>
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
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><s:submit cssClass="reset"
						action="Form12BA_reset" theme="simple" value="Reset"
						 /> <input type="button" class="token"
						name="mailReportBtn" onclick="mailReportFun(this);"
						value=" Mail Report " /></td>
					<td align="right">Export as: <img
						src="../pages/images/icons/file-pdf.png" class="iconImage"
						height="20" align="absmiddle" width="20"
						onclick="callReportMis('Pdf');" title="PDF"> <img src="../pages/images/icons/file-xls.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="generateReport('Xls');" title="Excel">
						<img src="../pages/images/icons/file_extension_html.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="generateReport('Html');" title="HTML">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		-->
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" id="reportBodyTable" class="formbg">
				
				<tr>
						<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
									<td colspan="6" class="formhead"><strong
										class="forminnerhead">Select Financial Year</strong></td>
								</tr>
								<tr>
							<td width="10%" height="22"><label id="fromyear"
								name="fromyear" ondblclick="callShowDiv(this);"><%=label.get("fromyear")%></label>:<font
								color="red">*</font></td>
							<td width="10%"><s:textfield name="fromYear" theme="simple"
								maxlength="4" onkeypress="return numbersOnly();" size="8"
								onblur="callYear();" /></td>

							<td width="10%"><label id="toyear" name="toyear"
								ondblclick="callShowDiv(this);"><%=label.get("toyear")%></label>
							<font color="red">*</font> :</td>
							<td width="25%"><s:textfield name="toYear" theme="simple"
								readonly="true" size="8" /></td>

						</tr>
							</table>
							</td></tr>
				
				
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
<tr>
									<td colspan="6" class="formhead"><strong
										class="forminnerhead">Select Employee</strong></td>
								</tr>

						<tr>
							<td class="formtext" height="22"><label id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
								color="red">*</font><s:hidden name="empId" /> <s:hidden
								name="divId" /> <s:hidden name="desg" /> <s:hidden
								name="panNo" /></td>
							<td width="82%" colspan="4"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /> <s:textfield
								name="empName" size="75" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'Form12BA_f9Employee.action');">
							</td>

						</tr>

						

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td width="80%"><s:submit cssClass="reset"
						action="Form12BA_reset" theme="simple" value="Reset"
						 /> <input type="button" class="token"
						name="mailReportBtn" onclick="mailReportFun(this);"
						value=" Mail Report " /></td>
					<td align="right">Export as: <img
						src="../pages/images/icons/file-pdf.png" class="iconImage"
						height="20" align="absmiddle" width="20"
						onclick="callReportMis('Pdf');" title="PDF"> <img src="../pages/images/icons/file-xls.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="generateReport('Xls');" title="Excel">
						<img src="../pages/images/icons/file_extension_html.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="generateReport('Html');" title="HTML">
					</td>
				</tr>
			</table>
			</td>
		</tr>
-->
	<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
<tr>
		<td >
	<div id='bottomButtonTable'>
	
	</div>
		</td>
	</tr>
	</table>
	<s:hidden name="rptType" /> 
	<s:hidden name="reportAction" value='Form12BA_report.action'/>
	<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>
	
</s:form>
<script type="text/javascript">

function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_rptType').value=type;
				callReportCommon(type);
			}
}
function callReportMis(){ 
     
      try{
	
	//document.getElementById('paraFrm_rptType').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="Form12BA_report.action";
				document.getElementById('paraFrm').submit();
			}
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }
	 
}

function callYear()
{
 var fromYear = document.getElementById('paraFrm_fromYear').value;
 document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
}

function validateFields(){
		
var empName = document.getElementById('paraFrm_empName').value;
var fromYear = document.getElementById('paraFrm_fromYear').value;
var toYear = document.getElementById('paraFrm_toYear').value;
	if(empName=="")
	{   alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		 return false;  
		}	
	if(fromYear=="")
	{	 alert("Please enter "+document.getElementById('fromyear').innerHTML.toLowerCase());
	     document.getElementById('paraFrm_fromYear').focus();
		return false;  
		 }
  if(toYear=="")
	  {	 alert("Please enter "+document.getElementById('toyear').innerHTML.toLowerCase());
	     document.getElementById('paraFrm_toYear').focus();
		 return false;  
		 }
   if(fromYear>toYear)
	    {
		  alert("To year should be greater than From year.");
		return false; 
		 }
		return true;
	}
	
 function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			
			document.getElementById('paraFrm_rptType').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='Form12BA_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
			
		}	
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="Form12BA_reset.action";
		document.getElementById('paraFrm').submit();
	}
</script>
