<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="ProbationMisReport" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Probation
					Mis Report </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1">
						<input type="button" class="token"	onclick="return callMisReport();"
								value="Show Report"  /> 
						<s:submit cssClass="reset" action="ProbationMisReport_clear" 
								  theme="simple" 	value="Reset" />
					</td>
					<td width="22%">
						<div align="right">
							<font color="red">*</font> Indicates Required
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					  :</td>
					<td colspan="3" width="80%">
					   <s:hidden name="divCode" />  
					   <s:textfield name="division" theme="simple" size="25" readonly="true" /> 
					   <img	src="../pages/images/recruitment/search2.gif" height="18"
						    class="iconImage" align="absmiddle" width="18"
						    onclick="javascript:callsF9(500,325,'ProbationMisReport_f9div.action');">
					</td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
					</td>
					<td colspan="1" width="30%">
						<s:hidden name="deptCode" value="%{deptCode}" theme="simple" /> 
						<s:textfield name="deptName" theme="simple" size="25" readonly="true" />
						<img	src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ProbationMisReport_f9dept.action');">
					</td>
					<td colspan="1" width="20%" class="formtext"><label
						name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%">
						<s:hidden name="centerNo" value="%{centerNo}" theme="simple" /> 
						<s:textfield name="centerName" 	theme="simple" size="25" readonly="true" /> 
						<img  src="../pages/images/recruitment/search2.gif" height="18"
							  align="absmiddle" width="18" class="iconImage"
						      onclick="javascript:callsF9(500,325,'ProbationMisReport_f9center.action');">
					</td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%">
						<s:hidden name="typeCode" value="%{typeCode}" theme="simple" />
						 <s:textfield name="empType" theme="simple" size="25" readonly="true" /> 
						<img src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'ProbationMisReport_f9type.action');">
					</td>

					<td colspan="1" width="20%" class="formtext">
						<label	name="designation" id="designation" 	ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
					</td>
					<td colspan="1" width="30%">
						<s:hidden name="desgCode" value="%{desgCode}" theme="simple" />
						 <s:textfield name="desgName" theme="simple" size="25" readonly="true" /> 
						 <img	src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ProbationMisReport_f9desg.action');">
					</td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22">
						<label
							name="fromdate" id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label>
						:
					</td>
					<td colspan="1" width="30%">
						<s:textfield theme="simple"	onkeypress="return numbersWithHiphen();" 
									 name="fromDate"  maxlength="10" size="25" />
						 <s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
							   <img src="../pages/images/recruitment/Date.gif" class="iconImage"
									height="16" align="absmiddle" width="16">
						</s:a>
					</td>
					<td colspan="1" width="20%" class="formtext">
						<label
							name="todate" id="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%>
						</label>
						:
					</td>
					<td colspan="1" width="30%">
						<s:textfield theme="simple"	onkeypress="return numbersWithHiphen();" name="toDate"
									 maxlength="10" size="25" /> 
						<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a>
					</td>
				</tr>
			 <tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label
						name="report.type" id="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td colspan="1" width="25%">
						<s:select theme="simple" name="reportType" headerKey="Pdf" headerValue="Pdf"
								  list="#{'Xls':'Xls', 'Txt':'Text'}" />
					</td>
					<td colspan="1" width="25%" class="formtext" height="22"><label
						name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="25%">
						<s:select theme="simple" name="status" cssStyle="width:130" 
								  list="#{'-1':'Select','C':'Confirmed','E':'Extended Probation','T':'Terminated'}"
								  />
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1">
						<input type="button" class="token" 	onclick="return callMisReport();"
								value="Show Report"  />
						<s:submit cssClass="reset" action="ProbationMisReport_clear" theme="simple" value="Reset" /></td>
					<td width="22%">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	</table>

</s:form>
<script type="text/javascript">
	function callMisReport(){
		try{
		 	var fromDt =document.getElementById('paraFrm_fromDate').value ;
		    var toDate = document.getElementById('paraFrm_toDate').value ;
		    var sts= document.getElementById('paraFrm_status').value ;
		   if(sts==-1){
		   			alert("Please select Status");
		   			return false;
		   }
		   
	       if(!validateDate('paraFrm_fromDate', 'fromdate'))
  				return false;
  	       if(!validateDate('paraFrm_toDate', 'todate'))
  				return false;	
  		   var datdiff = dateDifferenceEqual(fromDt,toDate,'paraFrm_fromDate','fromdate','todate');
  	  	   if(!datdiff){
  				return false;
  			} 
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action='ProbationMisReport_report.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";
		}catch(e){ alert("exception---------"+e);}
	}
	
	 

</script>
