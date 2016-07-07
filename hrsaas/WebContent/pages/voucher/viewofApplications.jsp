<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CashVoucher" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp; <s:hidden
				name="cvoucher.isApprove" /> 
			  <s:hidden name="checkEdit"
				value="%{checkEdit}" />  </td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Voucher
			Application </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				
					<td width="78%"></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red"></font></span> </div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
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
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Voucher Application </strong> <s:hidden name="vCode" value="%{vCode}" /></td>
						 <s:hidden name="level" value="%{level}" /></tr>
						<tr>
							
							<td width="25%" colspan="1" class="formtext">Type of Voucher:<font color="red">*</font></td>
					<td width="25%" colspan="1"  class="formtext"><s:select
						name="type" headerKey="" disabled="true" theme="simple" headerValue="Select"
						cssStyle="width:110"
						list="#{'Local':'Local','Out Station':'Out Station'}" />
						</td>
							
								
					
						</tr>

						<tr>
							<td width="25%" colspan="1" class="formtext">Employee Name :<font
								color="red">*</font><s:hidden name="empCode" value="%{empCode}" /></td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="ename" size="50" value="%{ename}" theme="simple"
								readonly="true" /> </td>

						</tr>


						<tr>
							<td width="25%" class="formtext">Department:</td>
							<td width="25%"><s:textfield name="department"
								value="%{department}" theme="simple" size="25" readonly="true" /></td>
							

						</tr>

						<tr>
							<td width="25%" class="formtext">Designation:</td>
							<td width="25%"><s:textfield name="designation"
								value="%{designation}" theme="simple" size="25" readonly="true" /></td>
							<td width="25%" class="formtext">Grade:</td>

							<td width="25%"><s:textfield name="grade" value="%{grade}"
								theme="simple" size="25" readonly="true" /></td>
						</tr>

						<tr>
							<td width="25%" class="formtext">Date:<font color="red">*</font></td>
							<td width="25%"><s:textfield name="vchDate" size="25"
								onkeypress="return numbersWithHiphen();" readonly="true"
								theme="simple" value="%{vchDate}" maxlength="10" /></td>
							<td width="25%" class="formtext">Status:</td>

							<td width="25%"><s:hidden	name="hiddenStatus" value="%{hiddenStatus}" /> <s:select theme="simple" name="status"
								disabled="true" cssStyle="width:130"
								list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded'}" /></td>
						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr><td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Voucher Details </strong></td>
						</tr>
						</table>
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
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr><s:hidden name="tableLength" value="%{tableLength}" />
							<td width="5%" class="formth">Sr.No.</td>
							<td width="20%" class="formth">Voucher Head</td>
							<td width="20%" class="formth">Amount(Rs.)</td>
							<td width="25%" class="formth">Particulars</td>
							<td width="15%" class="formth">Proof Attached ?</td>
							<td width="15%" class="formth">Uploaded File</td>
							<td width="25%" class="formth">Show</td>
							
						</tr>
						<% int i = 0;%>

						<s:iterator value="list">


							<tr>

								<td class="border2" width="5%" align="center"><s:property value="srNo" /><s:hidden
									name="srNo"></s:hidden></td>
								<td class="border2" width="25%"><s:property
									value="VoucherHead" /><s:hidden name="VoucherHead"></s:hidden>
								<s:hidden name="vchCode"></s:hidden></td>
								<td class="border2" width="25%" align="left"><s:property
									value="vamt" /> <s:hidden name="vamt"></s:hidden></td>
								<td class="border2" width="25%" align="left"><s:property
									value="vrem" /><s:hidden name="vrem"></s:hidden></td>
								<td class="border2" width="10%" align="left"><s:property
									value="vproof" /><s:hidden name="vproof"></s:hidden></td>
								<td class="border2" width="15%" align="left">
									<s:property value="uploadFile" />&nbsp;</td>
								<td align="center" width="25%" class="border2">
									<input type="button" class="token"  value="   Show" onclick="return showRecord('<s:property value="uploadFile" />');"/>
									</td>
								</tr>

							<%
			i++;
			%>
						</s:iterator>
			
								<tr>

								<td class="border2" width="5%" align="center">&nbsp;</td>
								<td class="border2" width="25%">&nbsp;</td>
								<td class="border2" width="25%" align="left">&nbsp;</td>
								<td class="border2" width="25%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								</tr>
								<tr>

								<td class="border2" width="5%" align="center">&nbsp;</td>
								<td class="border2" width="25%">Total Amount</td>
								<td class="border2" width="25%" align="left"><s:property value="totalamount"/></td>
								<td class="border2" width="25%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								<td class="border2" width="10%" align="left">&nbsp;</td>
								</tr>
					</table>

					</td>
				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">&nbsp;</td>
					<td class="formtext">&nbsp;</td>
				</tr>
				
				<tr>
					<td class="formtext">&nbsp;&nbsp;Details :</td>
					<td class="formtext"><s:textarea name="details"  readonly="true" cols="100"
						rows="3"></s:textarea></td>
				</tr>
				<tr>
					<td class="formtext">&nbsp;</td>
					<td class="formtext">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr><td colspan="3"><s:if test="commentFlag"></s:if><s:else>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Approver Details </strong></td>
						</tr>
						</table>
					</td>
				</tr>
			</table></s:else>
			</td>
		</tr>
		<s:if test="commentFlag"></s:if><s:else><tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td width="5%" class="formth" >Sr.No.</td>
							<td width="30%" class="formth">Approver Name</td>
							<td width="20%" class="formth">Approved Date</td>
							<td width="15%" class="formth">Status</td>
							<td width="30%" class="formth">Comments</td>
									
							
						</tr>
						<% int j = 0;%>

						<s:iterator value="approveList">


							<tr>

								<td class="border2" width="5%" align="center"><%= ++j%></td>
								<td class="border2" width="30%"><s:property
									value="apprName" />
								</td>
								<td class="border2" width="20%" align="left"><s:property
									value="apprDate" /> </td>
								<td class="border2" width="15%" align="left"><s:property
									value="appStatus" /> </td>
								<td class="border2" width="30%" align="left"><s:property
									value="apprComments" />&nbsp;</td>
								</tr>

							
						</s:iterator>
			
			
					</table>

					</td>
				</tr>
			</table>
			
			</td>
		</tr> </s:else></table>
	<br />


	<label></label>
</s:form>
<script type="text/javascript">

</script>
<script>
function showRecord(fieldName){
	if(fieldName == ""){
		alert('File not available.');
		return false;
	}
	var path='<%=session.getAttribute("session_pool")%>';
	window.open('../pages/images/'+path+'/voucher/'+fieldName);
}

</script>
