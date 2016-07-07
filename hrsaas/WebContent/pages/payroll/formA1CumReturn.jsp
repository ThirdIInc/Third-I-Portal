<%--@author:Reeba Joseph--%>
<%--August 13, 2010--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="FormA1CumReturn" name="FormA1CumReturn" id="paraFrm"
	theme="simple">

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form
					A-1 Cum Return </strong></td>
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
			<td width="100%">
			<table width="100%"  border="0" id="reportBodyTable">
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="1"
										cellspacing="2" class="formbg">
							<tr>
									<td class="formhead"><strong
												class="forminnerhead">Select Financial Year </strong></td>
							</tr>
						<tr>

							<td colspan="1" width="20%"><label class="set" id="from.year"
								name="from.year" ondblclick="callShowDiv(this);"><%=label.get("from.year")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield name="fromYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" onblur="add();" /></td>
		
							<td colspan="1" width="20%"><label class="set" id="to.year"
								name="to.year" ondblclick="callShowDiv(this);"><%=label.get("to.year")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:textfield name="toYear"
								theme="simple" maxlength="4" size="25" readonly="true" /></td>
						</tr>
							
						</table>
					</td>
				</tr>
			
			<tr> 
				<td>
					<table width="100%" border="0" align="center" cellpadding="1"
										cellspacing="2"  class="formbg">
						<tr>
								<td class="formhead"><strong class="forminnerhead">Select
											Filters </strong></td>
						</tr>
					<tr>

						<td colspan="1" width="20%"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
							color="red">*</font>:</td>
						<td colspan="1" width="30%"><s:hidden name="divisionId" /> <s:textfield
							name="divisionName" theme="simple" readonly="true" maxlength="50"
							size="25" /> <img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'FormA1CumReturn_f9division.action');">
	
						</td>
							<td colspan="1" width="20%"></td>
							<td colspan="1" width="30%"></td>
	
					</tr>
										
				</table>
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
	
		<!--<tr>
			<td>
			<table width="100%">
				<tr>
					<td><input type="button" name="report" value="Show Report"
						class="token" onclick="return showReport();" /> <s:submit
						cssClass="reset" action="FormA1CumReturn_reset" theme="simple"
						value="Reset"  /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				

				

			</table>
		</td>
		</tr>
	--></table>
	<s:hidden name="report" />
	<s:hidden name="reportAction"
		value='FormA1CumReturn_getReport.action' />
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
						document.getElementById('paraFrm').action="FormA1CumReturn_getReport.action";	
						document.getElementById('paraFrm').submit();
					}
				    
				    
			 }catch (e)
			 {
			 	alert(e);
			 }	
			 
			 
		}
function validateFields(){
		var from = document.getElementById('paraFrm_fromYear').value;
	 
	   var finYrFrm=document.getElementById('from.year').innerHTML.toLowerCase();
	  
	    	   
	    if(from=="")
	    {
	    	alert("Please Enter "+finYrFrm);
	    	return false;
	    }
	    
	    if(eval(from)<2000){
	    	alert(finYrFrm+" cannot be less than 2000");
	    	return false;
	    }
		
		
		var divNm   =document.getElementById("paraFrm_divisionId").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divisionName').focus();
	 	return false;
	 
	 }
		return true;
	}

	function mailReportFun(type){
			if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
				//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
				document.getElementById('paraFrm').action='FormA1CumReturn_mailReport.action';
				document.getElementById('paraFrm').submit();
				//return true;
			}	
		}
	
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="FormA1CumReturn_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function add(){
		var from = document.getElementById('paraFrm_fromYear').value;
		if(from==""){
			document.getElementById('paraFrm_toYear').value="";
		}else{
			var x=eval(from) +1;
			document.getElementById('paraFrm_toYear').value=x;
		}
	}
	
	function getYear(){
		var current = new Date();
		var year =current.getFullYear();
		var yr =document.getElementById("paraFrm_fromYear").value;
		if(yr==''){
			document.getElementById("paraFrm_fromYear").value =year;
			add();
		}
	}
	getYear();
</script>