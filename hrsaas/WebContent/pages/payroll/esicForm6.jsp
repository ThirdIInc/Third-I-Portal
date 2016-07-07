<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="EsicForm6" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="reportType" />
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">ESIC Form 6 Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>&nbsp;</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
				<tr>
			<td colspan="3" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetField();">
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
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg" >
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
							<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="finYearFrm" name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label> <font
								color="red">*</font>:</td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
								name="fromYear" theme="simple" size="25" onkeyup="return add();"
								onblur="return add();" maxlength="4"
								onkeypress="return numbersOnly();" /></td>
							<td width="25%" colspan="1"><label  class = "set"  id="finYearTo" name="finYearTo" ondblclick="callShowDiv(this);"><%=label.get("finYearTo")%></label> <font
								color="red">*</font>:</td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
								name="toYear" readonly="true" theme="simple" size="25" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="contribution.period" name="contribution.period" ondblclick="callShowDiv(this);"><%=label.get("contribution.period")%></label> :</td>
							<td width="25%" colspan="1">
							<s:select
								name="contributionPeriod"
								list="#{'A':'April - September','O':'October - March'}" />
							<s:hidden name="repType" />
							<s:hidden name="reportAction" value='EsicForm6_report.action'/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						class="formbg">
						<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Filter</strong></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:hidden
								name="divisionCode" /> <s:textfield name="divisionName"
								size="25" readonly="true" /> <img
								src="../pages/images/search2.gif" class="iconImage" height="17"
								align="absmiddle" width="17"
								onclick="javascript:callsF9(500,325,'EsicForm6_f9divisionAction.action');">

							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							
						</tr>
						
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetField();">
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
		
	</tr>
	</table>
</s:form>


<script>
function validateFields()
	{
		 var branch =document.getElementById('paraFrm_divisionName').value;
	  var contrPeriod = document.getElementById("paraFrm_contributionPeriod").value;
	  var year =document.getElementById('paraFrm_fromYear').value;
	   var toyear =document.getElementById('paraFrm_toYear').value;
	   
	  if(branch==""){
	    	alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
	    	return false;
	    }
	  if(year==""){
	    	alert("Please enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
	    	return false;
	    }
	    if(toyear==""){
	    	alert("Please enter "+document.getElementById('finYearTo').innerHTML.toLowerCase());
	    	return false;
	    }
		return true;
	}
 function check(name,type)
 {
 	var branch =document.getElementById('paraFrm_divisionName').value;
	  var contrPeriod = document.getElementById("paraFrm_contributionPeriod").value;
	  var year =document.getElementById('paraFrm_fromYear').value;
	   
	  if(branch==""){
	    	alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
	    	return false;
	    }
	  if(year==""){
	    	alert("Please enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
	    	return false;
	    }
	    else{
	    document.getElementById('paraFrm_repType').value = type;
	    document.getElementById('paraFrm').target="_blank";
	    document.getElementById('paraFrm').action=name;
	 	document.getElementById('paraFrm').submit();
	 	}
 }
 
 function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		document.getElementById('paraFrm_repType').value=type;
		document.getElementById('paraFrm').action='EsicForm6_reportMail.action';
			document.getElementById('paraFrm').submit();
			//callDropdown(obj.name,200,250,'Form6Report_mailReport.action','false');
			//return true;
		}	
	}  
	
	function callReport(type){
		document.getElementById('paraFrm_repType').value=type;
		if(!validateFields()){
				return false;
			} else {
				/*document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="ESICReport_report.action";
				document.getElementById('paraFrm').submit();*/
				callReportCommon(type);
			}
	}
	   
   
   
   function add() {
    var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="") {
    	 document.getElementById('paraFrm_toYear').value="";
    } else {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
   function resetField(){
   		document.getElementById('paraFrm_toYear').value="";
   		document.getElementById('paraFrm_fromYear').value="";
   		document.getElementById('paraFrm_fromYear').value="";
   		document.getElementById("paraFrm_contributionPeriod").value="A";
   		document.getElementById("paraFrm_divisionName").value="";
   }
   function contriPeriod(){
   		var contrPeriod = document.getElementById("paraFrm_contributionPeriod").value;
   		
   		if(contrPeriod == "A"){
   			document.getElementById("aprilDiv").style.display = '';
			document.getElementById("octDiv").style.display = 'none';
   		}else 
   		if(contrPeriod == "O"){
   			document.getElementById("aprilDiv").style.display = 'none';
			document.getElementById("octDiv").style.display = '';
   		}
   }
   
   function onload(){
   			document.getElementById("aprilDiv").style.display = '';
			document.getElementById("octDiv").style.display = 'none';
   }
   
   
	
	function getYear(){
		var current = new Date();
		var year =current.getFullYear();
		var yr =document.getElementById("paraFrm_fromYear").value;
		if(yr==''){
			document.getElementById("paraFrm_fromYear").value =year;
		}
	}
   
   getYear();
   </script>