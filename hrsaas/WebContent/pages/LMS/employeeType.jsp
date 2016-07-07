<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript">
<%try{%>
var records = new Array();
</script>

<s:form action="EmployeeType" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Type</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="20%">
					<div align="right"><span class="style2"></span> Indicates
					Required</div>
					</td>
				
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext">
			<table width="100%" border="0" class="formbg">
				<tr>

					<td width="60%" class="formth" align="center"><label
						class="set" name="emp_type" id="emp_type"
						ondblclick="callShowDiv(this);"><%=label.get("emp_type")%></label></td>
					<td width="40%" class="formth" align="center"><label
						class="set" name="type_employment" id="type_employment"
						ondblclick="callShowDiv(this);"><%=label.get("type_employment")%></label></td>
				</tr>

				<%int i=0; %>
				<s:iterator value="emptypeBean.employmentList" status="stat">

					<tr>
						<td align="left" class="sortableTD"><s:property
							value="emptype" /></td>

						<s:hidden name="empCode" />

						<td class="sortableTD" align="center"><s:select
							headerKey="" headerValue="--select--" name="typeofEmployment"
							list="map" /></td>
					</tr>
					<%
							i++;
							%>
				</s:iterator>
			</table>
		<tr>
			<td width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		</td>
		</tr>
	</table>

</s:form>
<script>

function saveFun()
{
/*	{
	alert("in saveFun");
	
	var type= document.getElementById('paraFrm_typeofEmployment').value;
	
	alert("value is "+type);
	if(type=="-1")
{
alert("Please select Type Of Employment");
return false;
}	*/
	
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='EmployeeType_save.action';
	  	document.getElementById('paraFrm').submit();
return true;	
	
	}
	
	function resetFun() 
	{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'EmployeeType_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	return true;
	}

</script>




<%}catch(Exception e){
e.printStackTrace();
}%>