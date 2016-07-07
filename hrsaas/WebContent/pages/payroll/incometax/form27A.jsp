<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="Form27A" validate="true" id="paraFrm" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form
					27A Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
						<!--<tr>
							<td width="78%">
								<s:hidden name="reportType" />
								<input type="button" class="token" onclick="resetField()" value="Reset" /> 
								<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun();" value=" Mail Report "/>
								<s:if test="bean.viewFlag">
								 <input type="button" class="token" theme="simple" value="   Report  " onclick="return callView()" />  
								</s:if>
							</td>
							<td width="22%" align="right">
								Exported as : &nbsp;
								<img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
								onclick="return callView();" title="PDF">
							</td>
						</tr>
					-->
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
								cellspacing="2" class="formbg">
								<tr>
									<td colspan="4">
										<b><label class="set" id="selectFinancialYear" name="selectFinancialYear" ondblclick="callShowDiv(this);">
										<%=label.get("selectFinancialYear")%></label></b>
									</td>
								</tr>

								<tr>
									<td width="25%">
									<label	class="set" id="finYearFrm" name="finYearFrm"
								ondblclick="callShowDiv(this);"><%=label.get("analytics.taxation.finYrFrm")%></label>
									<font color="red">*</font>:</td>
									<td><s:textfield size="25" name="finFrmYr" maxlength="4"
										onblur="add()" onkeypress="return numbersOnly();" /></td>

									<td>
									<label class="set" id="finYearTo" name="finYearTo"
								ondblclick="callShowDiv(this);"><%=label.get("analytics.taxation.finYrTo")%></label>
										<font color="red">*</font>:</td>
									<td><s:textfield size="23" name="finToYr" readonly="true" /></td>
								</tr>
								</table>
							</td>
						</tr>
			
						<tr>
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="2" class="formbg">			
																
								<tr>
									<td colspan="4">
										<b><label class="set" id="selectFilterOption" name="selectFilterOption" ondblclick="callShowDiv(this);">
										<%=label.get("selectFilterOption")%></label></b>
									</td>
								</tr>
								<tr>
									<td width="25%">
									<label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									<font color="red">*</font>:</td>
									<td><s:textfield size="22" name="divName" readonly="true" /><s:hidden
										name="divId" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'Form27A_f9division.action');">
									</td>

								</tr>

								<tr>
									<td>
										<label  class = "set"  id="selectQuarter" name="selectQuarter" ondblclick="callShowDiv(this);"><%=label.get("selectQuarter")%></label>:
									</td>
									<td colspan="2" ><s:select name="quarter"
										theme="simple" headerKey="-1"
										list="#{'June':'June','September':'September','December':'December','March':'March'}" />
									</td>
								</tr>

								<tr>
									<td>
									<label  class = "set"  id="selectSigningAuthority" name="selectSigningAuthority" ondblclick="callShowDiv(this);"><%=label.get("selectSigningAuthority")%></label>
									<font color="red">*</font>:</td>
									<td><s:textfield size="22" name="signAuthName"
										readonly="true" /><s:hidden name="signAuthId" /> <s:hidden
										name="empToken" /> <s:hidden name="signAuthDesg" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'Form27A_f9signAuthority.action');">
									</td>

								</tr>
								
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!--<tr>
					<td width="78%">
						<s:if test="bean.viewFlag">
						<input type="button" class="token" onclick="resetField()" value="Reset" />
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun();" value=" Mail Report "/>
						</s:if>
					</td>
					<td width="22%" align="right">
						Exported as : &nbsp;
						<img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
							onclick="return callView();" title="PDF">
					</td>
				</tr>
			-->
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
			</td>
		</tr>
	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='Form27A_report.action' />
	
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

function resetFun() {
	document.getElementById('paraFrm').action='Form27A_reset.action';
	document.getElementById('paraFrm').submit();
}



function add()
   {
    var from = document.getElementById('paraFrm_finFrmYr').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_finToYr').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_finToYr').value=x;
	  }
   }
   
    function validateFields()
   {
   	  var divName =document.getElementById('paraFrm_divName').value;
	  var from = document.getElementById('paraFrm_finFrmYr').value;
	  var signAuth = document.getElementById('paraFrm_signAuthName').value;
	
	    
	    if(divName=="")
	    {
	    	alert("Please Select Division.");
	    	return false;
	    }
	    
	    else if(from=="")
	    {
	    	alert("Please Enter Financial Year  From.");
	    	return false;
	    }
	
	    else if(signAuth=="")
	    {
	    	alert("Please Select Signing Authority.");
	    	return false;
	    }
	    
	   return true; 
	   
   
   }
   
function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='Form27A_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}
   
function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_finFrmYr").value;
	if(yr==''){
		document.getElementById("paraFrm_finFrmYr").value =year;
	}
}
	
getYear();   
 
function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}	   
   
   
</script>