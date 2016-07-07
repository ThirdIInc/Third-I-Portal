<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
 <s:form action="PTaxReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Statutory  Report</strong></td>
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
				<s:if test="ptax.viewFlag">
				<input type="button" class="token"  onclick="check('PTaxReport_report.action')" value="TDS Report"/>
				<input type="button" class="token"  onclick="check('PTaxReport_esiReport.action')" value="ESI Report"/>
				<input type="button" class="token"  onclick="check('PTaxReport_pfReport.action')" value="PF Report"/>
				<input type="button" class="token"  onclick="check('PTaxReport_lwfReport.action')" value="LWF Report"/>
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
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
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
									<td colspan="3" width="100%"><input type="checkbox" name="allFlag" id="paraFrm_allFlag" checked="checked" onclick="chkOption('0');"/>All &nbsp;&nbsp;<s:checkbox name="branchFlag" onclick="chkOption('1');"/><label><%=label.get("branch") + " Wise"%></label>
									&nbsp;&nbsp;
									<!--<s:checkbox name="deptFlag" onclick="chkOption('1');"/><label><%=label.get("department") + " Wise"%></label>
									--></td>
								</tr>
					<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="report.opttion" name="report.opttion" ondblclick="callShowDiv(this);"><%=label.get("report.opttion")%></label>:</td>
						<td colspan="1" width="30%">
						<s:select theme="simple" name="ptax.reportOption" cssStyle="width:152"
							list="#{'O':'All','S':'Only Salary','A':'Only Arrears','se':'Only Settlement'}" />
							
						</td>
					</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%">
							<s:select theme="simple" name="ptax.month" cssStyle="width:152"
							list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td>
							<s:textfield name="ptax.year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25"  />
							</td>
						</tr>	
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%">
							<s:hidden name="ptax.divCode" />
							<s:hidden name="ptax.divAdd" /><s:hidden name="ptax.divEsiCode" />
							<s:textfield name="ptax.divName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PTaxReport_f9div.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label>:</td>
							<td>
								<s:select theme="simple" name="onHold" cssStyle="width:152"
							list="#{'A':'All','N':'No','Y':'Yes'}" />
							</td>
						</tr>	
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="ptax.brnCode" />
							<s:textfield name="ptax.brnName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PTaxReport_f9brn.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td>
							<s:hidden name="ptax.deptCode" />
								<s:textfield name="ptax.deptName" theme="simple"  readonly="true" maxlength="50" size="25" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PTaxReport_f9dept.action');">
							</td>
						</tr>		
						
						
						
						<tr>
							
							<td colspan="1" width="20%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
							<td colspan="1" width="30%">
							<s:hidden name="ptax.typeCode" />
							<s:textfield name="ptax.typeName" theme="simple" readonly="true"  maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PTaxReport_f9type.action');">	
							
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :</td>
							<td>
							<s:hidden name="ptax.payBillNo" />
							<s:textfield name="ptax.payBillName" theme="simple"  readonly="true" maxlength="50" size="25" />
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PTaxReport_f9payBill.action');">
							</td>
						</tr>	
						
						
							
						<tr>
										
						<!-- UPDATED BY REEBA -->
							<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:152"
							list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td></tr>
						
						
						
						
						<tr>
						<td colspan="1" width="20%"><label  class = "set"  id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label> :</td> 
						<td colspan="1" width="30%"><input type="checkbox" name="checkFlag"  id="checkFlag"
						 onclick="callCheck();" /></td>
						 
						 <td colspan="1" width="20%"> <label  class = "set"  id="edliSalary" name="edliSalary" ondblclick="callShowDiv(this);"><%=label.get("edliSalary")%></label>:</td> 
						<td colspan="1" width="30%"><input type="checkbox" name="checkEdliSal"  id="checkEdliSal"
						 onclick="callCheck();" /></td>
						</tr>
							
							<tr>
							
							
							
							<td>&nbsp;</td>	<td>&nbsp;</td>
							</tr>
						</tr>
							
							
							<tr>
							<td colspan="3" >
									<CENTER>
										
										
										
									</CENTER>
									</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		
		</table>
		</td></tr></table>
		
		</s:form>
 <script>
 setCurrentYear('paraFrm_ptax_year');
 function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked;
 var checkEdli=document.getElementById('checkEdliSal').checked;
 
 	if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	
 	}
 	
 	
 	if(checkEdli){
 	
 	         document.getElementById('checkEdliSal').value="Y";
 	}
 	
 
 }
 
function check(name)
 {
	 var month =document.getElementById("paraFrm_ptax_month").value;
	 var year =document.getElementById("paraFrm_ptax_year").value;
	 var division =document.getElementById("paraFrm_ptax_divCode").value;
	 var rep = document.getElementById('paraFrm_reportType').value;
	
	 
	 if(month =='0'){
	 	alert("Select "+document.getElementById('month').innerHTML.toLowerCase());
	 	return false;
	 }
	 if(year ==''){
	 	alert("Enter "+document.getElementById('year').innerHTML.toLowerCase());
	 	return false;
	 }
	 if(!checkYear('paraFrm_ptax_year','year')){
	 	return false;	 
	 }
	 if(division =="")
	 {
	 	alert("Select "+document.getElementById('division').innerHTML.toLowerCase());
	 	return false;
	 }
	 if(rep=='0'){
	 		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 		return false;
			}

	document.getElementById('paraFrm').action=name;	
	document.getElementById('paraFrm').submit();	
	}
	
	function resetField()
	{
	  document.getElementById("paraFrm_ptax_month").value='0';
	  document.getElementById("paraFrm_ptax_year").value="";
	  document.getElementById("paraFrm_ptax_typeName").value="";
	  document.getElementById("paraFrm_ptax_payBillNo").value="";
	  document.getElementById("paraFrm_ptax_divCode").value="";
	  document.getElementById('paraFrm_reportType').value='0';
	  document.getElementById('paraFrm_ptax_divName').value="";
	  document.getElementById('paraFrm_onHold').value='A';
	  document.getElementById('paraFrm_ptax_brnName').value="";
	  document.getElementById('paraFrm_ptax_brnCode').value="";
	  document.getElementById('paraFrm_ptax_typeCode').value="";  
	  document.getElementById('paraFrm_ptax_deptCode').value="";  
	  document.getElementById('paraFrm_ptax_deptName').value="";  
	 	document.getElementById('checkFlag').checked=''; 
	 	document.getElementById('checkEdliSal').checked=''; 
	 		document.getElementById('paraFrm_ptax_reportOption').value='O'; 
		
	}
	
	function checkBranchDep(){
 
 var chk=document.getElementById('chkBrnDept').checked;
 
 

 if(chk){
 			document.getElementById('chkBrnDept').value="Y";
 	
 	}
 	}
 
function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_ptax_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_ptax_year").value =year;
	  }
}
getYear();
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
	}
}

</script>