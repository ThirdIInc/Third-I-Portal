<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="SalarySummary" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Salary Summary Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				
				<td>
				<s:if test="viewFlag">
				<input type="button" class="token"  onclick="check('SalarySummary_getReport.action')" value="View Report"/>
				<input type="button" class="token"  onclick="resetField()" value="Reset"/>
				</s:if>
				</td>
				    
				<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<!--<tr>
							<td colspan="1" width="20%">Branch and Department wise :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="chkBrnDept" id="chkBrnDept" onclick="checkBranchDep();" /></td>
						</tr>
						-->
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="brnDeptWise" name="brnDeptWise"
										ondblclick="callShowDiv(this);"><%=label.get("brnDeptWise")%></label>:</td>
									<td colspan="3" width="100%"><input type="checkbox" name="allFlag" id="paraFrm_allFlag" checked="checked" onclick="chkOption('0');"/>All &nbsp;&nbsp;
									<s:checkbox name="branchFlag" onclick="chkOption('1');"/><label  class = "set"  id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> Wise&nbsp;&nbsp;
									<s:checkbox name="deptFlag" onclick="chkOption('1');"/><label><label  class = "set"  id="department1" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> Wise</td>
								</tr>
					
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> <font color="red">*</font> :</td>
							<td colspan="1" width="30%">
							<s:select theme="simple" name="month" cssStyle="width:170"
							list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font color="red">*</font> :</td>
							<td>
							<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="29"  />
							</td>
						</tr>	
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font color="red">*</font> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple"  readonly="true" maxlength="50" size="29" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'SalarySummary_f9div.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>:</td>
							<td>
								<s:select theme="simple" name="onHold" cssStyle="width:171"
							list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
						</tr>	
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%" nowrap="nowrap">
							<s:hidden name="brnCode" />
							<s:textarea name="brnName" cols="30" rows="2" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="18" onclick="return callf9Branch();">	
								
							</td>
						
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%" nowrap="nowrap">
							<s:hidden name="deptCode" />
								<s:textarea name="deptName" cols="30" rows="2" readonly="true" ></s:textarea>
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="18" onclick="return callf9Dept();">
								<!--  <img src="../pages/images/zoomin.gif" class="iconImage" onclick="javasvript:callWindow('paraFrm_deptName','department','readonly');">
							-->
							</td>
						</tr>		
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td colspan="1" width="30%" nowrap="nowrap">
							<s:hidden name="desgCode" />
							<s:textarea name="desgName" cols="30" rows="2" readonly="true"  />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'SalarySummary_f9Desg.action');">	
							<!-- <img src="../pages/images/zoomin.gif" class="iconImage" onclick="javasvript:callWindow('paraFrm_desgName','designation','readonly');">
							-->
							</td>
							<td colspan="1" width="20%"><label class="set"	id="pay.bill" name="pay.bill"
										ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
							<td colspan="1" width="30%"><s:hidden name="paybillId" /><s:textfield name="paybillName" theme="simple"
										readonly="true" maxlength="50" size="25" /> 
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SalarySummary_f9paybill.action');">
										
							<!--  <td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%//=label.get("report.type")%></label>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:171"
							list="#{'Pdf':'Pdf','Txt':'Doc'}" /></td>--></tr>
						</tr>	
						<tr>
							
							<td colspan="1" width="20%"><label id="summaryFor" name="summaryFor" ondblclick="callShowDiv(this);"><%=label.get("summaryFor")%></label> :</td>
							<td colspan="1" width="30%">
							<s:select theme="simple"
								name="summaryFor" cssStyle="width:150"
								list="#{'AL':'All','SA':'Salary','AR':'Arrears'}" />
							</td>
						</tr>
							
						
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		</td></tr></table>
		
		</s:form>
 <script>
 setCurrentYear('paraFrm_year');
 function callCheck(formAction){
 
	document.getElementById('paraFrm').action=formAction; 	
	document.getElementById('paraFrm').submit();
 
 }
 
function check(actionName)
 {
	 var month =document.getElementById("paraFrm_month").value;
	 var year =document.getElementById("paraFrm_year").value;
	 var division =document.getElementById("paraFrm_divCode").value;
	 //var rep = document.getElementById('paraFrm_reportType').value;
	
	 
	 if(month =='0'){
	 	alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_month').focus();
	 	return false;
	 }
	 if(year ==''){
	 	alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_year').focus();
	 	return false;
	 }
	 if(division =="")
	 {
	 	alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	 }
	// if(rep=='0'){
	 	//	alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 	//	return false;
		//	}

	callReport(actionName);
	}
	
	function resetField()
	{
	  document.getElementById("paraFrm_month").value='0';
	  document.getElementById("paraFrm_year").value="";
	  document.getElementById("paraFrm_divCode").value="";
	  document.getElementById('paraFrm_divName').value="";
	 // document.getElementById('paraFrm_reportType').value='0';
	  document.getElementById('paraFrm_onHold').value='A';
	  document.getElementById('paraFrm_brnName').value="";
	  document.getElementById('paraFrm_brnCode').value="";
	  document.getElementById('paraFrm_desgCode').value="";  
	  document.getElementById('paraFrm_desgName').value="";  
	  document.getElementById('paraFrm_deptCode').value="";  
	  document.getElementById('paraFrm_deptName').value="";  
	  document.getElementById('paraFrm_paybillName').value="";  
	  document.getElementById('paraFrm_paybillId').value="";  
		setCurrentYear('paraFrm_year');
	}
	function resetFilters()
	{
	  document.getElementById('paraFrm_brnName').value="";
	  document.getElementById('paraFrm_brnCode').value="";
	  document.getElementById('paraFrm_desgCode').value="";  
	  document.getElementById('paraFrm_desgName').value="";  
	  document.getElementById('paraFrm_deptCode').value="";  
	  document.getElementById('paraFrm_deptName').value="";  
	  document.getElementById('paraFrm_paybillName').value="";  
	  document.getElementById('paraFrm_paybillId').value="";  
		
	}
	
function chkOption(type)
{
	if(type==0)
	{
		if(document.getElementById('paraFrm_allFlag').checked)
		{
			document.getElementById('paraFrm_branchFlag').checked=false;
			document.getElementById('paraFrm_deptFlag').checked=false;
		}
		else
			document.getElementById('paraFrm_branchFlag').checked=true;
	}
	else
	{
		if(!document.getElementById('paraFrm_branchFlag').checked 
		   && !document.getElementById('paraFrm_deptFlag').checked)
		{
			document.getElementById('paraFrm_allFlag').checked=true;
		}
		else
		{
			document.getElementById('paraFrm_allFlag').checked=false;
		}
		
		if(document.getElementById('paraFrm_branchFlag').checked){
		 	document.getElementById('paraFrm_brnName').value="";
	  		document.getElementById('paraFrm_brnCode').value="";
		}
		if(document.getElementById('paraFrm_deptFlag').checked){
		 	document.getElementById('paraFrm_deptCode').value="";  
	  		document.getElementById('paraFrm_deptName').value=""; 
		}
		//resetFilters();
	}
}
function callf9Dept(){
if(document.getElementById('paraFrm_deptFlag').checked){
	alert("Please uncheck the "+document.getElementById('department').innerHTML+" Wise flag");
	return false;
}

callsF9(500,325,'SalarySummary_f9Dept.action');
return true;
}
function callf9Branch(){
if(document.getElementById('paraFrm_branchFlag').checked){
	alert("Please uncheck the "+document.getElementById('branch').innerHTML+" Wise flag");
	return false;
}
callsF9(500,325,'SalarySummary_f9Branch.action');
return true;
}

</script>