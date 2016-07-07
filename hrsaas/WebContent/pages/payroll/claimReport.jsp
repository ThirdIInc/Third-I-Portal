 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ClaimMisReport" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Claim MIS Report</strong></td>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="search"
						value='    View ' onclick="callSub()" /> <s:submit
						cssClass="reset" action="ClaimMisReport_reset" value="   Reset " />

					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font color="red">*</font></span> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>

		</tr>

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">



						<tr>

							<td width="16%" colspan="1"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="24%" colspan="1"><s:textfield size="25"
								theme="simple" name="divName" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Div.action');">
							<s:hidden name="divCode" /></td>

							<td width="10%" colspan="1"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td width="30%" colspan="3"><s:textfield size="25"
								theme="simple" name="branchname" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Brnach.action');">
							<s:hidden name="branchcode" /></td>
						</tr>

						<tr>
							<td width="16%" colspan="1"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="24%" colspan="1"><s:textfield size="25"
								theme="simple" name="department" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Dept.action');">
							<s:hidden name="deptCode" /></td>
							<td width="15%" colspan="1"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td width="35%" colspan="3"><s:textfield size="25"
								theme="simple" name="desgname" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Desg.action');">
							<s:hidden name="desgCode" /></td>
						</tr>
						<tr>
							
							<td width="15%" colspan="1"><label  class = "set"  id="credit.name" name="credit.name" ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label> :</td>
							<td width="35%" colspan="1"><s:textfield size="25"
								theme="simple" name="creditName" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Credit.action');">
							<s:hidden name="creditCode" /><s:hidden name="creditAbbr" /></td>
							
							<td width="15%" colspan="1"><label  class = "set"  id="vchHead" name="vchHead" ondblclick="callShowDiv(this);"><%=label.get("vchHead")%></label> :</td>
							<td width="35%" colspan="1"><s:textfield size="25"
								theme="simple" name="vchName" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9Voucher.action');">
							<s:hidden name="vchCode" /></td>
						</tr>
	
						<tr>
							<td width="16%" class="formtext"><label  class = "set"  id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:</td>
							<td width="24%"><s:textfield name="fromDate" size="25"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="15%" class="formtext"><label  class = "set"  id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<s:hidden
								name="today" /></td>
							<td width="25%"><s:textfield name="toDate" size="25"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
						</tr>

						



						<tr>
							<td width="16%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:<s:hidden name="empId" /></td>
							<td width="84%" colspan="4"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ClaimMisReport_f9emp.action');">
							</td>

						</tr>
						<tr>
							<td width="16%" class="formtext"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td width="24%"><s:select name="reporttype" cssStyle="width:100"
								list="#{'Pdf':'Pdf','Xls':'Xls'}" theme="simple" /></td>
							<td width="16%" colspan="1"><label  class = "set"  id="stat" name="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :</td>
							<td width="24%" colspan="1"><s:select theme="simple" name="status" cssStyle="width:158"
								list="#{'':'All','P':'Pending','A':'Approved','D':'Disbursed','R':'Rejected'}" />


							</td>

						</tr>



					</table>





					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"></td>
		</tr>

	</table></td></tr></table>

</s:form>

<script>
  
  function callSub(){
  
 var fDate=document.getElementById('paraFrm_fromDate').value;
 var tDate=document.getElementById('paraFrm_toDate').value;
 
 
  	if(!validateDate("paraFrm_fromDate","from.date")){
  			return false;
  		
  		
  		}
  	
  	
  		if(!validateDate("paraFrm_toDate","to.date")){
		return false;
	}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
	
	return false;
	}
 else {
 
 
  			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='ClaimMisReport_submit.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
			
			
			}	
			
			}
  
  </script>

  <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>