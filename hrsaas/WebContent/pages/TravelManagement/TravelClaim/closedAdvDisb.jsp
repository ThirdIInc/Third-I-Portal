
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="AdvClmDisbursement" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" align="center" cellpadding="2"
		class="formbg" cellspacing="1">
		<tr>
			<td colspan="2" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
			<table width="100%" border="0"  class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="1"><strong
						class="text_head"> Advance Disbursement </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<td width="20%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<s:hidden name="gradeID"/>


		<tr>
			<td colspan="4" width="100%">

			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">

				<tr>
					<td width="20%" colspan="4"><strong>Employee Details</strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" height="22"><label class="set" name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="advclosedEmpName" /><s:hidden name="advclosedEmpId" /></td>
					<td width="20%" colspan="1"><label class="set"
						name="travelstartdate" id="travelstartdate"
						ondblclick="callShowDiv(this);"><%=label.get("travelstartdate")%></label></td>
					<td width="30%" colspan="1"><s:property
						value="closedtrvlStrtDate" /> <s:hidden name="closedtrvlStrtDate" />
				</tr>

				<tr>
					<td width="20%" colspan="1" height="22"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="closedbranchName" /></td>
					<td width="20%" colspan="1"><label class="set"
						name="travelenddate" id="travelenddate"
						ondblclick="callShowDiv(this);"><%=label.get("travelenddate")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="closedtravelEnddate" /> <s:hidden
						name="closedtravelEnddate" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" height="22"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label :</td>
					<td width="30%" colspan="1"><s:property
						value="closeddepartmentName" /></td>
					<td width="20%" colspan="1"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>

					<td width="30%" colspan="1"><s:property
						value="closedgradeName" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" height="22"><label class="set"
						id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value="closeddesig" /></td>

					<td width="20%" colspan="1"><label class="set"
						id="requiredadvamount" name="requiredadvamount"
						ondblclick="callShowDiv(this);"><%=label.get("requiredadvamount")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="closedrequireingadvAmount" /></td>
					<s:hidden name="closedrequireingadvAmount" />
				</tr>

				<tr>
					<td width="20%" colspan="1" height="22"><label class="set" id="appl.date"
						name="appl.date" ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="closedApplicationDate" /></td>
					<td width="20%" colspan="1"><label class="set" id="trvlid"
						name="trvlid" ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value="TravelcloseId" /></td>
					<s:hidden name="closedadvapplId" />
					<s:hidden name="closedadvapplCode" />

				</tr>


				</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="6" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- Payment Details   -->
				<tr>
					<td>
					<table width="100%" class="formbg">
						<tr>
							<td width="70%" colspan="3"><b>Payment Details for
							closed</b></td>

						</tr>
						<tr>
							<td colspan="6" width="100%">
							<table width="100%" class="sorttable" id="disbursement">
								<tr>
									<td class="formth" width="5%"><b><label class="set"
										name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
									</td>
									<td class="formth" width="15%"><b><label class="set"
										name="pay.date" id="pay.date" ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label></b>
									</td>

									<td class="formth" width="30%"><b><label class="set"
										name="pay.mode" id="pay.mode" ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label></b>
									</td>
									<td class="formth" width="20%">Payment Details</td>
									<td class="formth" width="10%"><b><label class="set"
										name="amount" id="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></b>
									</td>
									<td class="formth" width="20%"><b><label class="set"
										name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></b>
									</td>



								</tr>
								<%
								int i = 1;
								%>
								<s:iterator value="closedpayList">

									<tr>
										<td class="sortableTD"><%=i%>
										<td class="sortableTD" align="center"><s:property
											value="closeditpaymentdate" /></td>


										<td class="sortableTD" align="center"><s:property
											value="closeditpaymentmode" /> <input type=hidden
											name="closeditpaymentmode" id="closeditpaymentmode<%=i %>"
											value="<s:property value="closeditpaymentmode" />" /></td>
										<td class="sortableTD" align="center"><input
											type="hidden" name="closeditcheckno"
											id="closeditcheckno<%=i %>"
											value="<s:property value="closeditcheckno" />"
											readonly="true" /><s:property value="closeditcheckno" /><br>
										<input type="hidden" name="closeditcheckdate"
											id="closeditcheckdate<%=i %>"
											value="<s:property value="closeditcheckdate" />"
											readonly="true" /><s:property value="closeditcheckdate" />
										<input type="hidden" name="closedaccountnumber"
											id="closedaccountnumber<%=i %>"
											value="<s:property value="closedaccountnumber" />"
											readonly="true" /><s:property value="closedaccountnumber" />
										<input type="hidden" name="closedbankname"
											id="closedbankname<%=i %>"
											value="<s:property value="closedbankname" />" readonly="true" /><s:property
											value="closedbankname" /></td>
										</td>

										<td class="sortableTD" align="right"><s:property
											value="closeditamount" /></td>
										<td class="sortableTD" align="center"><s:property
											value="closeditcomment" /></textarea></td>
											
											<td class="sortableTD" align="right"><s:property
											value="recMonth" /></td>
											
											<td class="sortableTD" align="right"><s:property
											value="recYear" /></td>

									</tr>
									<script>
									   var tbl = document.getElementById('disbursement');
											var rowLen = tbl.rows.length;
                                          
                                        // alert('ranu'+rowLen);
											for(var q=1;q<rowLen;q++)
										{
											
												
									var status=document.getElementById("closeditpaymentmode"+q).value;
									//alert('status'+status)
									if(status=='Cash')
									{
									//alert('in if')
									document.getElementById('closeditcheckno'+q).style.display='none';
									document.getElementById('closeditcheckdate'+q).style.display='none';
									document.getElementById('closedaccountnumber'+q).style.display='none';
									document.getElementById('closedbankname'+q).style.display='none';
									}
									else if(status=="Check")
									{
										
									//alert('in else if');
									document.getElementById('closeditcheckno'+q).style.display='';
									document.getElementById('closeditcheckdate'+q).style.display='';
									document.getElementById('closedaccountnumber'+q).style.display='none';
									document.getElementById('closedbankname'+q).style.display='none';
									//alert('in else if22')
									}
									else
									{
									//alert('in else if');
								   document.getElementById('closeditcheckno'+q).style.display='none';
									document.getElementById('closeditcheckdate'+q).style.display='none';
									document.getElementById('closedaccountnumber'+q).style.display='';
									document.getElementById('closedbankname'+q).style.display='';
									}
									}
									</script>
									<%
									i++;
									%>



								</s:iterator>







							</table>
						<tr>


							<td align="right" colspan="4" width=70%"><label class="set"
								name="advpaid" id="advpaid" ondblclick="callShowDiv(this);"><%=label.get("advpaid")%></label> :

							</td>
							<td colspan="1" width="10%" align="right"><s:property
								value="closedhiddenAdvPaid" /></td>
							<td colspan="1" width="20%">&nbsp;</td>

						</tr>

						<tr>
							<td colspan="4" width="70%" align="right"><label class="set"
								name="bal.amount" id="bal.amount"
								ondblclick="callShowDiv(this);"><%=label.get("bal.amount")%></label> :</td>
							<td colspan="1" width="10%" align="right"><s:property
								value="closedhiddenBalanceAmt" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="statusname" name="statusname"
								ondblclick="callShowDiv(this);"><%=label.get("statusname")%></label>
							:</td>
							<td><s:select name="closedstatus"
								list="#{'S':'--Select--','P':'Partially Paid','C':'Closed Application'}" />
							</td>

						</tr>
				
						<script>
								
								//alert('grf'+document.getElementById('paraFrm_disb_hidstatus').value);
								
								document.getElementById('paraFrm_closedstatus').disabled="true";
						</script>

					</table>
					</td>
				</tr>
				

				<!-- end of payment details -->
			</table></td></tr>
			
	</table>
</s:form>
<script>
function returntolistFun()
	{
	document.getElementById('paraFrm').action = "AdvClmDisbursement_returnHome.action";
	 document.getElementById('paraFrm').submit();
	
	
	}
 function newRowColor(cell) {   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
		}	
	
	

</script>

