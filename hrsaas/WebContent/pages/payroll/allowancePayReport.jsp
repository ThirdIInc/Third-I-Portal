<!-- @author aa0431 Lakkichand Golegaonkar
 * @date 17 Nov 2010*/ --> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AllowancePayReport" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Allowance
					Pay Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="token"
						onclick="return callReport();" value=" Report"  />


					<s:submit cssClass="reset" action="AllowancePayReport_reset"
						theme="simple" value="    Reset" /></td>
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

						<tr>

							<td colspan="1"><label class="set" id="month" name="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
								color="red">*</font>:</td>
							<td colspan="1"><s:select theme="simple"
								name="month" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1"><label class="set" id="year" name="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
								color="red">*</font>:</td>
							<td><s:textfield name="year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>

						<tr>

							<td colspan="1"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td colspan="1"><s:hidden name="divCode" /> <s:textfield
								name="divName" theme="simple" readonly="false" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'AllowancePayReport_f9Div.action');">

							</td>

							<td colspan="1"><label class="set" id="branch" name="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td><s:hidden name="brnCode" /> <s:textfield name="brnName"
								theme="simple" readonly="false" maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'AllowancePayReport_f9Branch.action');">

							</td>
						</tr>



						<tr>

							<td colspan="1"><label class="set" id="department"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="1"><s:hidden name="deptCode" /> <s:textfield
								name="deptName" theme="simple" readonly="false" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'AllowancePayReport_f9Dept.action');">

							</td>

							<td colspan="1"><label class="set" id="creditHead"
								name="creditHead" ondblclick="callShowDiv(this);"><%=label.get("creditHead")%></label>
							:</td>
							<td colspan="1"><s:hidden name="creditCode" /> <s:textfield
								name="creditName" theme="simple" readonly="false" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'AllowancePayReport_f9Credit.action');">

							</td>
						</tr>



						<tr>
						<tr>
							<td><label class="set" id="report.type" name="report.type"
								ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
							:</td>
							<td colspan="1"><s:select theme="simple" name="reportType"
								cssStyle="width:152" list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>


<script type="text/javascript">

function callReport(){
	 
	
	
	  var month  =document.getElementById("paraFrm_month").value;
	 var year   =document.getElementById("paraFrm_year").value;
	var rep     =document.getElementById("paraFrm_reportType").value;

	
	 if(month =='0'){
	 	alert("Please Select "+document.getElementById('month').innerHTML.toLowerCase());
	 	return false;
	 }
	 if(year ==''){
	 	alert("Please Enter "+document.getElementById('year').innerHTML.toLowerCase());
	 	return false;
	 }

	 if(rep=='0'){
	 	alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	 	return false;
	}
	
	
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='AllowancePayReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	 
	}



</script>
