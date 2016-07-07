<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CompOffReport" method="post" id="paraFrm">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
			        	<td valign="bottom" class="txt">
			        		<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
			        	</td>
			          	<td width="93%" class="txt"><strong class="text_head">Compensatory Off MIS Report </strong></td>
			          	<td width="3%" valign="top" class="txt">
			          		<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
		
		<!--<tr>
			<td>
				<table width="100%">
					<tr>
						<td>
							<input type="button" class="token" onclick="return callReportMis();" value="Show Report" />
							<s:submit cssClass="reset" action="CompOffReport_reset" theme="simple" value="Reset"  />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		--><tr>
			<td>
				<table width="100%" class="formbg" id="reportBodyTable">
					<tr>
						<td colspan="5" class="formhead">
							<strong class="forminnerhead"><label id="attendence.report" name="attendence.report" ondblclick="callShowDiv(this);"><%=label.get("attendence.report")%></label>! </strong><s:hidden name="vCode" value="%{vCode}" />
						</td>
					</tr>
					<tr>
						<td width="15%" colspan="1" class="formtext">
							<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>  :
						</td>
						<td width="75%" colspan="3">
							<s:textfield name="empToken" size="10" theme="simple" readonly="true" />
							<s:textfield name="empName" size="50"  theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18" 
							onclick="javascript:callsF9(500,325,'CompOffReport_f9Employee.action');">
							<s:hidden name="empId" />
						</td>
					</tr>
					<tr>
						<td width="15%" class="formtext"><label id="frmdate" name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
						<td width="25%">
							<s:textfield name="fromDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" 
							onblur="return validateDate('paraFrm_fromDate','From Date');" />
							<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
						<td width="10%" class="formtext"><label id="todate" name="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:<s:hidden name="today"/></td>
						<td width="25%">
							<s:textfield name="toDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" 
							onblur="return validateDate('paraFrm_toDate','To Date');"/>
							<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
					</tr>
					<tr>
						<td width="15%" class="formtext">
							<label id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:
						</td>
						<td width="25%">
							<s:select theme="simple" name="status" cssStyle="width:130" 
							list="#{'':'Select','P':'Pending','A':'Approved','R':'Rejected'}" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	
	<s:hidden name="report" />
	
	<s:hidden name="reportAction"
		value='CompOffReport_getReport.action' />
		
</s:form>

<script type="text/javascript">

function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
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
				document.getElementById('paraFrm').action="CompOffReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
	 }catch (e)
	 {
	 	alert(e);
	 }	
}

function validateFields(){
		var fromDate = document.getElementById('paraFrm_fromDate').value;
	    var toDate = document.getElementById('paraFrm_toDate').value;
	    
	    if(fromDate != "") {
			if(toDate == "") {
				alert ("Please enter the " + document.getElementById('todate').innerHTML.toLowerCase());
				return false;
		    }
	    }
		return true;
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='CompOffReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="CompOffReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	function callReportMis() {
	    var fromDate = document.getElementById('paraFrm_fromDate').value;
	    var toDate = document.getElementById('paraFrm_toDate').value;
	    
	    if(fromDate != "") {
			if(toDate == "") {
				alert ("Please enter the " + document.getElementById('todate').innerHTML.toLowerCase());
				return false;
		    }
	    }
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CompOffReport_report.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = ""; 
	}
</script>