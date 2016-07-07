<!-- Date:06-12-2011 Ganesh -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PFSubsReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF
					Subs Report </strong></td>
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
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><s:if test="%{viewFlag}">
								<input type="button" class="token"
									onclick="return generateReport();" value=" Report " />
							</s:if> <s:submit cssClass="reset" action="PFSubsReport_reset"
								theme="simple" value=" Reset"  /></td>

							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="1"
								cellspacing="2">
								<tr>
									<td colspan="6" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<tr>
									<td colspan="1" width="25%"><label id="month1"
										name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
										color="red">*</font>:</td>
									<td colspan="1" width="25%"><s:select theme="simple"
										name="month" cssStyle="width:152"
										list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
									</td>
									<td colspan="1" width="25%"><label class="set" id="year1"
										name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font
										color="red">*</font></td>
									<td width="25%"><s:textfield name="year"
										onkeypress="return numbersOnly();" theme="simple"
										maxlength="4" size="25" /></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:<font color="red">*</font></td>
									<td colspan="1" width="25%"><s:hidden name="divCode" /> <s:textfield
										name="divName" theme="simple" readonly="true" maxlength="50"
										size="25" cssStyle="background-color: #F2F2F2;" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9div.action');">
									</td>

									<td colspan="1" width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="25%"><s:hidden name="branchCode" />
									<s:textfield name="branchName" theme="simple" readonly="true"
										maxlength="50" size="25" cssStyle="background-color: #F2F2F2;" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9brn.action');">
									</td>

									<!--<td colspan="1" width="25%"><label class="set" id="grade"
										name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:hidden name="cadreCode" />
									<s:textfield name="cadreName" size="25" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> <img
										class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="16"
										height="15" border="0"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9gradeaction.action');" />
									</td>


								-->
								</tr>
								<!--<tr>
									<td width="25%"><label id="emp.number" name="emp.number"
										ondblclick="callShowDiv(this);"><%=label.get("emp.number")%></label>
									:<font color="red" id='ctrlHide'></font></td>
									<td width="25%" colspan="3"><s:textfield
										name="employeeToken" size="25" theme="simple" readonly="true"
										cssStyle="background-color: #F2F2F2;" /><s:textfield
										name="employeeName" size="60" theme="simple" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> <s:hidden
										name="employeeCode" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" id='ctrlHide'
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9Employee.action');">
									</td>
								</tr>
								-->

								<!--<tr>

									<td colspan="1" width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="25%"><s:hidden name="branchCode" /> <s:textfield
										name="branchName" theme="simple" readonly="true" maxlength="50"
										size="25" cssStyle="background-color: #F2F2F2;" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9brn.action');">
									</td>



									<td colspan="1" width="25%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:hidden name="deptCode" />
									<s:textfield name="deptName" theme="simple"
										readonly="true" maxlength="50" size="25"
										cssStyle="background-color: #F2F2F2;" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9Department.action');">
									</td>

								</tr>

								-->
								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:hidden name="paybillId" /><s:textfield
										name="paybillName" theme="simple" readonly="true"
										maxlength="50" size="25" cssStyle="background-color: #F2F2F2;" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9PayBill.action');">
									</td>

									<!--<td colspan="1" width="25%"><label class="set"
										id="employee.type" name="employee.type"
										ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
									:</td>
									<td colspan="1" width="25%"><s:hidden
										name="typeCode" /><s:textfield name="typeName"
										theme="simple" readonly="true" maxlength="50" size="25"
										cssStyle="background-color: #F2F2F2;" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PFSubsReport_f9EmployeeType.action');">
									</td>


								-->
								</tr>


								<tr>

									<td colspan="1" width="25%"><label class="set"
										id="report.type" name="report.type"
										ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:select theme="simple"
										name="report" cssStyle="width:152"
										list="#{'Xls':'Xls','Pdf':'Pdf'}" /></td>
								<tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
getYear();
function reset(){
document.getElementById("paraFrm_month").value=="0";
document.getElementById("paraFrm_year").value=="";
document.getElementById("paraFrm_divCode").value=="";
document.getElementById("paraFrm_divName").value=="";
document.getElementById("paraFrm_brnCode").value=="";
document.getElementById("paraFrm_brnName").value=="";

document.getElementById("paraFrm_employeeCode").value=="";
document.getElementById("paraFrm_employeeName").value=="";
document.getElementById("paraFrm_employeeToken").value=="";
document.getElementById("paraFrm_leaveType").value=="";
document.getElementById("paraFrm_LeaveName").value=="";
document.getElementById("paraFrm_leaveTypecode").value=="";

getYear();
}

function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}


function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}

function generateReport()
 {	
	try{
	
		
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		var month  =document.getElementById("paraFrm_month").value;
		var mont=document.getElementById('month1').innerHTML.toLowerCase();
		if(month =='0'){
	 	alert("Select "+mont);
	 	return false;
	 }
		var divNm   =document.getElementById("paraFrm_divCode").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	 
	 }
	 
	 if(document.getElementById('paraFrm_report').value=='')
	
		{
		    alert('Please select ReportType');
		    document.getElementById('paraFrm_report').focus();
		     return false;
		    }
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }	
	 
	 document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="PFSubsReport_getReport.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
}

function reset(){
	document.getElementById("paraFrm_salReg_month").value="0";
	document.getElementById("paraFrm_salReg_year").value="";
	document.getElementById("paraFrm_salReg_report").value="0";
	getYear();
}

</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>