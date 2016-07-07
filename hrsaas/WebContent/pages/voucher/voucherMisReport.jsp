<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CashVoucherReport" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
        <tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Voucher
					MIS Report </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td width="78%"><s:if test="%{viewFlag}"><input type="button" 
					class="token" onclick="return callReportMis();"	value=" Show Report" /></s:if>
				<s:submit cssClass="reset" action="CashVoucherReport_reset"
					theme="simple" value=" Reset"  /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
						
						<s:if test="%{generalFlag}">
							
							<tr>
								<td width="25%"  class="formtext"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>  :<s:hidden name="empCode" value="%{empCode}" />
								</td>
								<td width="75%" colspan="3"><s:textfield name="empToken"
									size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
									name="ename" size="50" value="%{ename}" theme="simple"
									readonly="true" /> 
								</td>
							</tr>
							
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="fromdate" id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label> :</td>
								<td width="25%"><s:textfield name="frmDate" size="25"
									onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
									width="16"> </s:a>
									</td>
								<td width="25%" class="formtext"><label  class = "set" name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label> :<s:hidden name="today"/></td>
								<td width="25%"><s:textfield name="toDate" size="25"
									onkeypress="return numbersWithHiphen();" theme="simple" value="%{vchDate}" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
									width="16"> </s:a>
								</td>	
							</tr>

							<tr>
								<td width="25%"  class="formtext"><label  class = "set"  id="vchHead"  name="vchHead" ondblclick="callShowDiv(this);"><%=label.get("vchHead")%></label> :<s:hidden name="vchHeadCode" /></td>
								<td width="25%" ><s:textfield name="vchHeadName" size="25" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'CashVoucherReport_f9VchHead.action');"></td>
								<td width="25%" class="formtext"><label  class = "set" name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :</td>
								<td width="25%"><s:select theme="simple" name="status"
									 cssStyle="width:130"
									list="#{'':'Select','D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdraw'}" /></td>
							</tr>
						
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="repType" id="repType" ondblclick="callShowDiv(this);"><%=label.get("repType")%></label> :</td>
								<td width="25%"><s:select name="repType" headerKey="Pdf" headerValue="Pdf" theme="simple"
								list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
							</tr>
							
						</s:if>
						<s:else>
							<tr>
								<td width="25%"  class="formtext"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>  :<s:hidden name="empCode" value="%{empCode}" />
								</td>
								<td width="75%" colspan="3"><s:textfield name="empToken"
									size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
									name="ename" size="50" value="%{ename}" theme="simple"
									readonly="true" /> 
									<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="16" 
											onclick="javascript:callsF9(500,325,'CashVoucherReport_f9actionEname.action');">
								</td>
							</tr>
							
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
								<td width="25%"><s:textfield name="division" size="25"
									readonly="true" theme="simple"  maxlength="10" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="16" 
											onclick="javascript:callsF9(500,325,'CashVoucherReport_f9Division.action');"><s:hidden name="divCode"></s:hidden>
									</td>
								<td width="25%" class="formtext"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
								<td width="25%"><s:textfield name="branch" size="25"
									readonly="true" theme="simple"  maxlength="10" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="16" 
											onclick="javascript:callsF9(500,325,'CashVoucherReport_f9Branch.action');"><s:hidden name="branchCode"></s:hidden>
								</td>	
							</tr>
							
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
								<td width="25%"><s:textfield name="department" size="25"
									readonly="true" theme="simple"  maxlength="10" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="16" 
											onclick="javascript:callsF9(500,325,'CashVoucherReport_f9Department.action');"><s:hidden name="deptCode"></s:hidden>
									</td>
								<td width="25%" class="formtext"><label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
								<td width="25%"><s:textfield name="designation" size="25"
									readonly="true" theme="simple"  maxlength="10" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="16" 
											onclick="javascript:callsF9(500,325,'CashVoucherReport_f9Deignation.action');"><s:hidden name="desgCode"></s:hidden>
									</td>	
							</tr>
							
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="fromdate" id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label> :</td>
								<td width="25%"><s:textfield name="frmDate" size="25"
									onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
									width="16"> </s:a>
									</td>
								<td width="25%" class="formtext"><label  class = "set" name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label> :<s:hidden name="today"/></td>
								<td width="25%"><s:textfield name="toDate" size="25"
									onkeypress="return numbersWithHiphen();" theme="simple" value="%{vchDate}" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
									width="16"> </s:a>
								</td>	
							</tr>

							<tr>
								<td width="25%"  class="formtext"><label  class = "set"  id="vchHead"  name="vchHead" ondblclick="callShowDiv(this);"><%=label.get("vchHead")%></label> :<s:hidden name="vchHeadCode" /></td>
								<td width="25%" ><s:textfield name="vchHeadName" size="25" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'CashVoucherReport_f9VchHead.action');"></td>
								<td width="25%" class="formtext"><label  class = "set" name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :</td>
								<td width="25%"><s:select theme="simple" name="status"
									 cssStyle="width:130"
									list="#{'':'Select','D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdraw'}" /></td>
	
							</tr>
						
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="repType" id="repType" ondblclick="callShowDiv(this);"><%=label.get("repType")%></label> :</td>
								<td width="25%"><s:select name="repType" headerKey="Pdf" headerValue="Pdf" theme="simple"
								list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
							</tr>
							
						</s:else>
					</table>
					</td>
					</tr>
					</table>
					
					
				
</s:form>
<script type="text/javascript">

function callReportMis(){
  	var emp=document.getElementById("paraFrm_ename").value;
  	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	var status=document.getElementById("paraFrm_status").value;
  	var today=document.getElementById("paraFrm_today").value;
  	
  	if(frmDate!=""){
	  	if(!validateDate("paraFrm_frmDate","fromdate")){
			return false;
		}
  	}else if(toDate!=""){
	  	if(!validateDate("paraFrm_toDate","todate")){
			return false;
		}
  	}
  	
  	if ((frmDate!="") && (toDate!="")) {
	  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'fromdate', 'todate'))
		{
		return false;
		}
	}
  	callReport('CashVoucherReport_report.action');
}
  	function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
}
autoDate();
</script>
