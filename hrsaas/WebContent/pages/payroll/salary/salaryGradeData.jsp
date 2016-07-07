<%@ taglib uri="/struts-tags" prefix="s" %>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="SalaryGrade" validate="true" id="paraFrm" name="paraFrm" theme="simple">

<s:hidden name="salGrade.gradeCode" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td width="100%" colspan="3">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td valign="bottom" class="txt"><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Salary Grade
						  </strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="5" class="formhead"><strong
							class="forminnerhead">Salary Grade </strong>   </td>
					</tr>
					<tr>
						<td width="20%" colspan="1"><label  class = "set" name="salary.gradeName" id="salary.gradeName" ondblclick="callShowDiv(this);"><%=label.get("salary.gradeName")%></label>
						<font color="red">*</font>:</td>
						<td width="55%" colspan="2"><s:textfield name="salGrade.gradeName"
							 size="30%" maxlength="29" theme="simple"  /></td>
					</tr>
			       <tr>
				       <td colspan="3">  
				          <table width="100%" align="center" border="0">
								<tr>
									<td class="formth" width="15%"><label  class = "set" name="salary.srno" id="salary.srno" ondblclick="callShowDiv(this);"><%=label.get("salary.srno")%></label>
										
									</td>
									<td class="formth" width="50%"><label  class = "set" name="salary.creditname" id="salary.creditname" ondblclick="callShowDiv(this);"><%=label.get("salary.creditname")%></label>
										
									</td>
									
									<td class="formth" width="30%"><label  class = "set" name="salary.creditamount" id="salary.creditamount" ondblclick="callShowDiv(this);"><%=label.get("salary.creditamount")%></label>
										
									</td>
								</tr>
								<%
									int i=0;
								
								%>
								<s:iterator value="creditlist">
									<tr>
										<td  width="5%" class="sortableTD"><%=++i%></td>
										<s:hidden name="creditCode"/>
										<td width="60%" class="sortableTD"><s:property value="creditName" /></td> <s:hidden name="creditName" />
								<td  width="35%" class="sortableTD" align="right"><s:textfield name="salAmt" onkeypress="return numbersOnly();" cssStyle="text-align:right;" /></td>
									</tr>
								</s:iterator>
						
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>		
	</table>
</s:form>
<script type="text/javascript">
	function saveFun()
	{	
		var fieldName = ["paraFrm_salGrade_gradeName"];
		var lableName = ["salary.gradeName"];
		var badFlag = ["enter"];
        if(!validateBlank(fieldName,lableName,badFlag))
        	return false;
        if(!f9specialchars(fieldName))
        	return false;
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='SalaryGrade_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SalaryGrade_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "SalaryGrade_reset1.action";
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'SalaryGrade_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
</script>