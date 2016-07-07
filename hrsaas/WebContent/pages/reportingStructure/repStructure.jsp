<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />

<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="RepStruct" validate="true" id="paraFrm" theme="simple">
	<s:hidden theme="simple" name="empId" />
	<table class="formbg" width="100%">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="3%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Reporting Structure Report</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" class="txt">
				<div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required</div>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" align="center">
							<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font color="red">*</font>:
							<s:textfield size="10" name="empTokenNo" readonly="true" value="%{empTokenNo}" />
							<s:textfield size="50" name="empName" readonly="true" />
							<s:if test="%{RepStruct.generalFlag}"></s:if>
							<s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="18" theme="simple"
								onclick="javascript:callsF9(500,325,'RepStruct_f9Selectemp.action'); ">
							</s:else>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" size="10" class="token" theme="simple" value="Show Report"
							onclick="return callReport('RepStruct_report.action')" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function callReport(name) {

var repType=document.getElementById('paraFrm_empName').value;
          if(repType=="")
          {
          alert("Please Select "+document.getElementById('employee').innerHTML.toLowerCase());
          return false;
          }
          else
          {
        document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
		
		return true;
          }
     	
     		
     						
	}
	
</script>

