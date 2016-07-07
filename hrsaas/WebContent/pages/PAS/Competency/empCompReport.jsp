<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="EmpCompitencyReport" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='EmpCompitencyReport_genReport.action'/>
<s:hidden name="report" />
	
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
   
   
    	<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Employee  Wise Competency Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    
    
  		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">

						<tr>
							<td colspan="1" width="20%"><label class="set" id="compName"
								name="compName" ondblclick="callShowDiv(this);"><%=label.get("compName")%></label> : <font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:hidden name="compId"/><s:hidden name="compFromDate"/><s:hidden name="compToDate"/><s:textfield
								name="compName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'EmpCompitencyReport_f9CompAction.action');">

							</td>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"></td>
						</tr>
						
						<tr>
							<td colspan="1" width="20%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
							</td>
							<td colspan="3" width="80%"><s:hidden name="empId" /> <s:textfield
								theme="simple" readonly="true" name="empToken" size="20" /> <s:textfield
								theme="simple" readonly="true" size="50" name="empName" /> 
								<img src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'EmpCompitencyReport_f9employee.action');">


							</td>
						</tr>
						
					</table></td>
          </tr>
      
        <tr>
         <table width="100%" border="0" align="center" cellpadding="2" cellspacing="0">
        <td>  
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	          <tr>
			
					
						<!--  <td>
							<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
						</td>-->
						<td nowrap="nowrap">
							<a href="#" onclick="callReset();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
							 title="PDF"> Reset </a>&nbsp;&nbsp;
						</td>
						<td width="100%">
							<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
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
	</table>
	</tr>
	
      </table></td>
    </tr>
    
     
	
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script><!--


function callReport(type){
var fields=["paraFrm_compName"];
	var labels=["compName"];
	var flags=["select"];
	if(!validateBlank(fields,labels,flags)){
		return false;
	}
	
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}
function mailReportFun(type){
var fields=["paraFrm_compName"];
	var labels=["compName"];
	var flags=["select"];
	if(!validateBlank(fields,labels,flags)){
		return false;
	}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='EmpCompitencyReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	document.getElementById('paraFrm_empToken').value="";
	document.getElementById('paraFrm_empId').value="";
	document.getElementById('paraFrm_empName').value="";
	document.getElementById('paraFrm_compName').value="";
	document.getElementById('paraFrm_compId').value="";
}

--></script>