
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelExpDisbur" method="post" id="paraFrm"
	theme="simple">

	<s:hidden name="navStatus" />
	<s:hidden name="expDisbId" />
	<s:hidden name="expAppId" />
	<s:hidden name="empId" />

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Full
					Payment Details </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"> <s:submit cssClass="token"
				value="Back " theme="simple" onclick=" callBack();" /></td>
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
			<table width="100%" align="center" cellpadding="2" cellspacing="2"
				class="formbg" theme="simple">

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<td><strong class="formhead"><label  class = "set"  id="tms.trvlExpDisbrEmpInf" name="tms.trvlExpDisbrEmpInf" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEmpInf")%></label></strong></td>
						<tr>
							<td width="20%" colspan="1" class="formtext" height="22"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
							<td width="60%" colspan="3"><s:label name="empToken"
								theme="simple" value="%{empToken}" />&nbsp;&nbsp; <s:label
								name="employeeName" theme="simple" value="%{employeeName}" /></td>

						</tr>
						<tr>
							<td width="10%" class="formtext" height="22"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="25%"><s:label name="brnchName" theme="simple"
								value="%{brnchName}" /></td>

							<td width="10%" class="formtext"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="25%"><s:label name="deptName" theme="simple"
								value="%{deptName}" /></td>
						</tr>
						<tr>

							<td width="10%" class="formtext" height="22"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="25%"><s:label name="desgName" theme="simple"
								value="%{desgName}" /></td>



							<td width="10%" class="formtext"><label  class = "set"  id="tms.trvlExpDisbrTrvlAppDate" name="tms.trvlExpDisbrTrvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlAppDate")%></label>:</td>
							<td width="25%"><s:label name="applDate" theme="simple"
								value="%{applDate}" /></td>
							</td>

						</tr>


						<tr>

							<td width="10%" class="formtext" height="22"><label  class = "set"  id="tms.trvlExpDisbrStatus" name="tms.trvlExpDisbrStatus" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrStatus")%></label>:</td>
							<td width="25%"><s:label name="statusFld" theme="simple"
								value="%{statusFld}" /></td>
							<td width="10%" class="formtext"><label  class = "set"  id="grade" name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td width="25%"><s:label name="grdName" theme="simple"
								value="%{grdName}" /></td>

						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">

			<table width="100%" align="center" cellpadding="2" cellspacing="2"
				class="formbg" theme="simple">

				<tr>
					<td height="27" class="formhead"><strong><label  class = "set"  id="tms.trvlExpDisbrPayDtls" name="tms.trvlExpDisbrPayDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPayDtls")%></label></strong></td>
					<td</td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrApprExpAmt" name="tms.trvlExpDisbrApprExpAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrApprExpAmt")%></label>:</td>
					<td width="75%" colspan="3"><s:label name="fullAppExpAmt"
						theme="simple" value="%{fullAppExpAmt}" /></td>
				</tr>


				<tr>
					<td width="17%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrLessAdvAmt" name="tms.trvlExpDisbrLessAdvAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAdvAmt")%></label>:</td>
					<td width="85%" colspan="3"><s:label name="fullAdvAmt"
						value="%{fullAdvAmt}" theme="simple" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrLessAnyOthrDed" name="tms.trvlExpDisbrLessAnyOthrDed" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAnyOthrDed")%></label>:</td>
					<td width="85%" colspan="3"><s:label name="fullAnyDedAmt"
						value="%{fullAnyDedAmt}" theme="simple" /></td>
				</tr>


			</table>


			</td>
		</tr>

		<!-- aaaaaaaa -->


		<tr>
			<td colspan="3">

			<table width="100%" align="center" cellpadding="2" cellspacing="2"
				class="formbg" theme="simple">



				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td width="5%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrSrNo" name="tms.trvlExpDisbrSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrSrNo")%></label></td>
							<td width="25%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrPaidAmt" name="tms.trvlExpDisbrPaidAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPaidAmt")%></label></td>
							<td width="25%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrTrvlDate" name="tms.trvlExpDisbrTrvlDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlDate")%></label></td>
							<td width="15%" valign="top" class="formth"><label  class = "set"  id="tms.trvlExpDisbrModPay" name="tms.trvlExpDisbrModPay" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrModPay")%></label></td>


						</tr>

						<%!int i = 0;%>
						<%
						int k = 1;
						%>

						<s:iterator value="trvlList">


							<tr>
								<td class="border2" width="5%"><%=k%></td>

								<s:hidden name="fullPaidAmt" />
								<s:hidden name="fullPaidDate" />
								<s:hidden name="fullPaidMode" />
								<td class="border2" width="12%"><s:property
									value="fullPaidAmt" />
								<td class="border2" width="22%"><s:property
									value="fullPaidDate" />
								<td class="border2" width="15%"><s:property
									value="fullPaidMode" /></td>

							</tr>
							<%
							k++;
							%>
						</s:iterator>
						<%
						i = k;
						%>

					</table>
					</td>
				</tr>
				<!-- ssssssss -->

			</table>
			</td>
		</tr>
		<tr>
			<td align="left" width="100%"><s:submit cssClass="token"
				value="Back " theme="simple" onclick=" callBack();" /></td>
		</tr>
	</table>


</s:form>

<script type="text/javascript">

function add()
{
return true;
}


</script>



<script>

function add(){

  return true;
 }

</script>
<script type="text/javascript">


	function callBack()
	{    
	   var staVar = document.getElementById('paraFrm_navStatus').value;
	   document.getElementById('paraFrm').action = "TravelExpDisbur_callStatus.action?status=F";  
		document.getElementById('paraFrm').submit();  
	 }

</script>

