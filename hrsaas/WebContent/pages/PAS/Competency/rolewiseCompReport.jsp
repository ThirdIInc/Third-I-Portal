<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="RoleWiseCompMisReport" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reportAction" value='RoleWiseCompMisReport_genReport.action'/>
<s:hidden name="report" />
	
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
   
   
    	<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Role Wise Competency Master</strong></td>
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
								color="red"></font></td>
							<td colspan="1" width="30%"><s:hidden name="compId"/><s:hidden name="compFromDate"/><s:hidden name="compToDate"/><s:textfield
								name="compName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'RoleWiseCompMisReport_f9CompAction.action');">

							</td>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"></td>
						</tr>
						<tr>
						<td colspan="1" width="20%"><label class="set" id="desgName"
								name="desgName" ondblclick="callShowDiv(this);"><%=label.get("desgName")%></label> : </td>
							<td colspan="1" width="30%"><s:hidden name="roleId"/><s:textfield
								name="role" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'RoleWiseCompMisReport_f9Rank.action');">

							</td>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"></td>
						</tr>
						<tr>
						<td colspan="1" width="20%"><label class="set" id="compCatagory"
								name="compCatagory" ondblclick="callShowDiv(this);"><%=label.get("compCatagory")%></label> : </td>
							<td colspan="1" width="30%"><s:hidden name="compCatcode"/><s:textfield
								name="compCatagory" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'RoleWiseCompMisReport_f9Catagory.action');">

							</td>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"></td>
						</tr>
						
					</table></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
         
          <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
          <tr>
			<td >
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
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
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
		
	</tr>	</table>
      </table></td>
    </tr>
	</table>
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script><!--


function callReport(type){

		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}
function mailReportFun(type){

		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='RoleWiseCompMisReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	document.getElementById('paraFrm_compName').value="";
	document.getElementById('paraFrm_compId').value="";
	document.getElementById('paraFrm_role').value="";
	document.getElementById('paraFrm_roleId').value="";
	document.getElementById('paraFrm_compCatagory').value="";
	document.getElementById('paraFrm_compCatagoryId').value="";
	
}

--></script>