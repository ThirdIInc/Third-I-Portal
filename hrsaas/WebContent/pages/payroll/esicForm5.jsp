<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="EsicForm5" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">ESIC Form 5 Report</strong></td>
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
									src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
									align="absmiddle" title="Reset"> Reset </a>&nbsp;&nbsp;</td>
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
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable" >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg" >
						
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"> <label class="set"
								id="selectFinancialYear" name="selectFinancialYear"
								ondblclick="callShowDiv(this);"><%=label.get("selectFinancialYear")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="finYearFrm" name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label> :<font
								color="red">*</font></td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
								name="fromYear" theme="simple" size="25" onkeyup="return add();"
								onblur="return add();" maxlength="4"
								onkeypress="return numbersOnly();" /></td>
							<td width="25%" colspan="1"><label  class = "set"  id="finYearTo" name="finYearTo" ondblclick="callShowDiv(this);"><%=label.get("finYearTo")%></label> :<font
								color="red">*</font></td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
								name="toYear" readonly="true" theme="simple" size="25" /></td>
						</tr>
					</table>						
					</td>
				</tr>	
				<tr>
					<td>	
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg" >
						<tr>
							<td colspan="4"><b><label class="set"
								id="selectFilterOption" name="selectFilterOption"
								ondblclick="callShowDiv(this);"><%=label.get("selectFilterOption")%></label></b>
							</td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : <font color="red">*</font></td>
							<td width="25%" colspan="1" nowrap="nowrap"><s:hidden
								name="divisionAbbrevation" /><s:hidden
								name="divId" /> <s:textfield name="divName"
								size="25" readonly="true" /> <img
								src="../pages/images/search2.gif" class="iconImage" height="17"
								align="absmiddle" width="17"
								onclick="javascript:callsF9(500,325,'EsicForm5_f9div.action');">

							</td>
							<td width="25%" colspan="1"></td>
							<td width="25%" colspan="1"></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="contribution.period" name="contribution.period" ondblclick="callShowDiv(this);"><%=label.get("contribution.period")%></label> :</td>
							<td width="25%" colspan="1"><s:select
								name="contributionPeriod"
								list="#{'A':'April - September','O':'October - March'}" /></td>
						</tr>
						
						
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
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
		
	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='EsicForm5_report.action' />
	
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

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='EsicForm5_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function validateFields() {
	var branch =document.getElementById('paraFrm_divName').value;
	var contrPeriod = document.getElementById("paraFrm_contributionPeriod").value;
	var year =document.getElementById('paraFrm_fromYear').value;
		   
	if(branch==""){
		alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
	}
	if(year==""){
		alert("Please enter "+document.getElementById('finYearfrm').innerHTML.toLowerCase());
		return false;
	}
	return true;
}
 
function resetFun(){
	document.getElementById('paraFrm').action='EsicForm5_reset.action';
	document.getElementById('paraFrm').submit();
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
   function callReset(){
   		document.getElementById('paraFrm_toYear').value="";
   		document.getElementById('paraFrm_fromYear').value="";
   		document.getElementById('paraFrm_fromYear').value="";
   		document.getElementById("paraFrm_contributionPeriod").value="A";
   		document.getElementById("paraFrm_divisionName").value="";
   		document.getElementById("paraFrm_divisionAbbrevation").value="";
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
   
function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}
   </script>