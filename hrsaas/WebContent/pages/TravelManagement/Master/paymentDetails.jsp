<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="TravelAdvanceDisbursement" validate="true" id="paraFrm"
	validate="true" theme="simple"   >
 
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		
		
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="98%" class="txt"><strong class="text_head">Payment Details </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td>
			        <input type="button" class="token" value="  Back  "  onclick=" return addBack();" />
				</td>
					<td width="22%">
								<div align="right"><span class="style2"><font
									color="red">*</font></span> Indicates Required</div>
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
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<td  ><strong class="formhead"><label  class = "set"  id="emp.info" name="emp.info" ondblclick="callShowDiv(this);"><%=label.get("emp.info")%></label></strong></td>
<s:hidden name="empId"  id="empId"/>
<s:hidden name="appId" />

						<tr>
							<td width="20%" colspan="1" class="formtext" height="22"><label  class = "set"  id="emp.name" name="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>  :
							<td width="30%" ><s:label name="empToken"
								theme="simple"  /><s:label	name="emppName"  theme="simple"  /></td>
								<td width="20%" class="formtext"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>  :</td>
							<td width="30%"><s:label name="dept" theme="simple" /></td>
						</tr>
                       <tr>
							<td width="20%" class="formtext" height="22"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>  :</td>
							<td width="30%"><s:label name="brName" theme="simple" /></td>
                             <td width="20%" class="formtext"><label  class = "set"  id="app.date" name="app.date" ondblclick="callShowDiv(this);"><%=label.get("app.date")%></label>  :</td>
							<td width="30%"><s:label name="applDate" theme="simple"  /></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>  :</td>
							<td width="30%"><s:label name="desg" theme="simple" /></td>
							
							<td width="20%" class="formtext" height="22"><label  class = "set"  id="grade" name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>  :</td>
							<td width="30%" colspan="3"><s:label name="gradeName" theme="simple" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<td height="27" class="formhead"><strong><label  class = "set"  id="pay.details" name="pay.details" ondblclick="callShowDiv(this);"><%=label.get("pay.details")%></label></strong></td>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="req.amount" name="req.amount" ondblclick="callShowDiv(this);"><%=label.get("req.amount")%></label>  :</td>
					<td width="75%" colspan="3"> 
					<s:label name="reqAdvName" theme="simple" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="iss.amount" name="iss.amount" ondblclick="callShowDiv(this);"><%=label.get("iss.amount")%></label>  : <font color="red">
							*</font></td>
					<td width="75%" colspan="3"><s:label name="issueAmount"
						theme="simple"  /></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="pay.date" name="pay.date" ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label>  : <font color="red">
							*</font></td>
					<td width="75%" colspan="3"><s:label name="paymentDate"
						 theme="simple" /> </td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="pre.mode" name="pre.mode" ondblclick="callShowDiv(this);"><%=label.get("pre.mode")%></label>  :</td>
					<td width="75%" colspan="3"><s:label name="paymentMode"
					  theme="simple" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="mod.payment" name="mod.payment" ondblclick="callShowDiv(this);"><%=label.get("mod.payment")%></label>  : <font color="red">
							*</font></td>
					<td width="75%" colspan="3">
					<s:hidden id="chkFlag" value="%{modPayment}"/>	<s:property value="Banker"/>
					
			</td>
				
				</tr>
	
				<tr id="divId1">
					<td colspan="4" width="100%">
					<table width="100%" calspan="1" cellspan="1" border="0">
						<tr>

							<td width="25%" colspan="1"><label  class = "set"  id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>  :<font color="red">
							</font></td>
							<td width="75%" colspan="3">
						
							<s:property value="Banker1"/>
						</td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="acc.no" name="acc.no" ondblclick="callShowDiv(this);"><%=label.get("acc.no")%></label>  :<font color="red">
							*</font></td>
							<td width="75%" colspan="3"><s:label name="accNo"
								 theme="simple" /></td>
						</tr>
						

					</table>
					</td>
				</tr>
				
				<tr id="divId">
					<td colspan="4" width="100%">
					<table width="100%" calspan="1" cellspan="1" border="0">
						<tr>

							<td width="25%" colspan="1" nowrap="nowrap"><label  class = "set"  id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>  :<font color="red">
							*</font></td>
							<td width="75%" colspan="3" nowrap="nowrap">
							
							<s:property value="Banker1"/>
							</td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="check" name="check" ondblclick="callShowDiv(this);"><%=label.get("check")%></label>   :<font color="red">
							*</font></td>
							<td width="75%" colspan="3"><s:label name="checkNo"
								 theme="simple" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="ch.date" name="ch.date" ondblclick="callShowDiv(this);"><%=label.get("ch.date")%></label>   :<font color="red">
							*</font></td>
							<td width="75%" colspan="3"><s:label name="checkDate"
								 theme="simple"  /> </td>
								

						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td width="10%" class="formtext" height="22"><label  class = "set"  id="commt" name="commt" ondblclick="callShowDiv(this);"><%=label.get("commt")%></label> :</td>
					<td colspan="3"><s:label name="comment" theme="simple"
						 /></td>
				</tr>
			</table>

		
		<tr>
		
			<tr><td>
			  <input type="button" class="token" value="  Back  "  onclick=" return addBack();" />
				</td></tr>
		
		
		
 </table>
 </s:form>
	 
<script type="text/javascript">

checkVal();
function checkVal()
{
 
	document.getElementById('divId').style.display = 'none';
	document.getElementById('divId1').style.display = 'none';
	if(document.getElementById('chkFlag').value == 'Q')
	{
	document.getElementById('divId').style.display = '';
	document.getElementById('divId1').style.display = 'none';
	}
	else if(document.getElementById('chkFlag').value == 'T')
	{
	document.getElementById('divId1').style.display = '';
	document.getElementById('divId').style.display = 'none';
	}
	else if(document.getElementById('chkFlag').value == 'C')
	{
	document.getElementById('divId').style.display = 'none';
	document.getElementById('divId1').style.display = 'none';
	}
	
	else if(document.getElementById('chkFlag').value == '')
	{
	
	document.getElementById('divId').style.display = 'none';
	
	}
	
	else
	document.getElementById('divId1').style.display = 'none';
	


} 
 </script>

<script>



function addBack()
{
  document.getElementById('paraFrm').action ="TravelAdvanceDisbursement_callStatus.action?payStatus=paid";  
	document.getElementById('paraFrm').submit();  
}
</script>
