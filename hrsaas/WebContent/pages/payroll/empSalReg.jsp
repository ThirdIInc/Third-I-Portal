<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpSalReg" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="empId"/> 
<s:hidden name="empType"/>
<s:hidden name="brnName"/>
<s:hidden name="empStatus"/>
<s:hidden name="empBranch"/>
<s:hidden name="empDiv"/>
<s:hidden name="empGrade" /> 
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
					<td width="93%" class="txt"><strong class="text_head">Employee
					Earnings</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
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
			<table width="100%" border="0" class="formbg" id="reportBodyTable">
				<tr>
					<td colspan="3">
						<table width="100%" border="0"  >
							<tr>
								<td colspan="8">
								<strong class="formhead"><label
									id=selectPeriod name="selectPeriod"
									ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
								</strong></td>
							</tr>
							<tr>
								<td width="15%"><label class="set" id="fromMonth"
									name="fromMonth" ondblclick="callShowDiv(this);"><%=label.get("fromMonth")%></label><font
									color="red">*</font> :</td>
								<td><s:select theme="simple" name="fromMonth" cssStyle="width:129"	onchange="return getToYear();"
									list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
								</td>
								<td><s:textfield name="fromYear" maxlength="4" onkeypress="return numbersOnly();" onkeyup="return getToYear();" onblur="return getToYear();" theme="simple"/></td>
								<td><label class="set"
									id="toMonth" name="toMonth" ondblclick="callShowDiv(this);"><%=label.get("toMonth")%></label>
								:</td>
								<td><s:select theme="simple" name="toMonth" cssStyle="width:129" onchange="return getToYear();"
									list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
								</td>
								<td><s:textfield name="toYear" maxlength="4" onkeypress="return numbersOnly();" readonly="true"	theme="simple"/></td>
							</tr>
						</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0"  >
						<tr>
							<td width="15%"><label class="set"
								id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							: <font color="red">*</font></td>
							<td>
							<s:textfield theme="simple" readonly="true" name="empToken" size="20" />
							<s:textfield theme="simple" readonly="true" size="65" name="empName" /> 
								<s:if test="%{generalFlag}">
								</s:if>
								<s:else>
									<img src="../pages/images/recruitment/search2.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'EmpSalReg_f9emp.action');">
								</s:else></td>
						</tr>
				</table>
			</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
			<tr valign="middle">
				<td nowrap="nowrap">
					<a href="#" onclick="resetFun();">
						<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
				</td>
				<td width="100%">
					<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='EmpSalReg_report.action'/>
</s:form>
<script type="text/javascript">
	
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				callReportCommon(type);
			}
	}
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='EmpSalReg_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function validateFields(){
		var empName = document.getElementById('paraFrm_empName').value;
		var fromMonth =document.getElementById('paraFrm_fromMonth').value;
		var fromYear = document.getElementById('paraFrm_fromYear').value;
		var toMonth = document.getElementById('paraFrm_toMonth').value;
		
	    if(fromMonth==""){
			alert("Please select "+document.getElementById('fromMonth').innerHTML.toLowerCase());
			return false;
		}
		if(fromYear==""){
			alert("Please enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
			return false;
		}
		if(toMonth==""){
			alert("Please select "+document.getElementById('toMonth').innerHTML.toLowerCase());
			return false;
		}
		if(empName==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
	    }
		/*
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmpSalReg_report.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
		*/
		return true;
	}

	function resetFun(){
		document.getElementById('paraFrm').action="EmpSalReg_reset.action";	
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
   
    function getToYear(){
		try{
		 	var fromMonth =document.getElementById('paraFrm_fromMonth').value;
		 	var fromYear =document.getElementById('paraFrm_fromYear').value;
		 	var toMonth =document.getElementById('paraFrm_toMonth').value;
		 	if(!(fromMonth=="" || toMonth=="" || fromYear=="")){ 
		 	if(eval(fromMonth) <= eval(toMonth)){
		 		document.getElementById('paraFrm_toYear').value=fromYear;
		 	}else{
		 		document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
		 	}
		 	}else{
		 		document.getElementById('paraFrm_toYear').value="";
		 	}
	 	}catch(e){alert(e);}
	}
</script>
