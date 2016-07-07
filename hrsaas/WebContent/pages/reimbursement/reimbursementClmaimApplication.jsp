<!-- @author: VISHWAMBHAR DESHPANDE 16 DECEMBER 2010 -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReimbursementClmAppl" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement   
										Application   </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- ###################### CASH BALANCE BLOCK BEGINS ############################## -->

		<tr>
			<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				class="formbg">

				<tr>
					<td width="100%">
					<table width="100%" cellspacing="0" cellpadding="0" border="0"
						align="center">


						<tr>
							<td  width="100%" class="text_head"><strong
								class="forminnerhead">&nbsp;&nbsp; <label class="set"
								name="reimbursementBal" id="reimbursementBal"
								ondblclick="callShowDiv(this);"><%=label.get("reimbursementBal")%></label>
							</strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellspacing="1" cellpadding="1" border="0"
								class="sortable">
								<tbody>
									<tr>
										<td width="25%" valign="top" class="formth"><label
											class="set" name="reimbursementType" id="reimbursementType"
											ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label>
											</td>
										<td width="25%" valign="top" class="formth"><label
											class="set" name="reimbursementBalance"
											id="reimbursementBalance" ondblclick="callShowDiv(this);"><%=label.get("reimbursementBalance")%></label>
										</td>
										<td width="25%"  class="formth">History</td>
										<td width="25%"  class="formth">Claim</td>
									</tr>
									<s:iterator value="balanceList" status="stat">
										<tr>
											<s:hidden name="creditCode" value="%{creditCode}" />
											<td class="sortableTD" width="25%" nowrap="nowrap" ><s:property
												value="creditType" /></td>
											<td class="sortableTD" width="25%" align="center"><s:property
												value="balance" /></td>
											<td class="sortableTD" width="25%" nowrap="nowrap" align="center">
											<s:hidden name="empCode"></s:hidden><s:hidden name="billsCarriedForwardList"></s:hidden>
											<input 
												type="button" name="view_Details" class="token"
												value=" View History "
												onclick="viewHistory('<s:property value="creditCode"/>')"/>
											</td>
											<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><input
												type="button" name="view_Details" class="token"
												value=" Claim Reimbursement "
												onclick="viewDetails('<s:property value="empCode"/>','<s:property value="creditCode"/>','<s:property value="creditType"/>',
												'<s:property value="balance"/>','<s:property value="billsCarriedForwardList"/>')" />
											</td>
										</tr>
									</s:iterator>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
			<!--------- SECTION BEGINS - displaying the saved leave applications ------->

			<!--<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Application In Process </b></td>
					</tr>
					<tr>
					<script>
		    function setAction(listType){
		    
		      	document.getElementById('paraFrm').action='ReimbursementClmAppl_getApplications.action?listStatus='+listType;
		      	document.getElementById('paraFrm').submit();
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('P')">Pending
					Application</a> | <a href="#" onclick="setAction('C')">Closed Applications</a> </td>
				</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="application.id" id="application.id"
									ondblclick="callShowDiv(this);"><%=label.get("application.id")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap">
								<b><label
											class="set" name="reimbursementType" id="reimbursementType1"
											ondblclick="callShowDiv(this);"><%=label.get("reimbursementType")%></label>
											</b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="appdate" id="appdate1" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="claim.amount" id="claim.amount1"
									ondblclick="callShowDiv(this);"><%=label.get("claim.amount")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="status" id="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b>View Claim</b></td>

							</tr>

							<%
							int i = 1;
							%>
							<s:iterator value="draftList">
								<tr>
									<td class="sortableTD" width="5%" align="center" nowrap="nowrap"><%=i%></td>
									<td class="sortableTD" width="15%"><s:hidden
										name="EmpIdItt" /><s:hidden name="applicationCodeItt" /> 
										  <s:property value="reimCodeItt" /></td>
									
									<td class="sortableTD" width="15%"> <s:hidden name="creditNameItt" /> 
										  <s:property value="creditNameItt" /></td>
										  	  
									<td class="sortableTD" width="15%" align="center" nowrap="nowrap"><s:property
										value="applicationDateItt" /></td>
									<td class="sortableTD" width="15%" align="center" nowrap="nowrap"><s:property
										value="claimAmtItt" /></td>
											<td class="sortableTD" width="15%" align="center"><s:property
										value="statusNameItt" />
										<s:hidden name="statusItt"></s:hidden>
										</td>
									<td class="sortableTD" width="15%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" View Claim "
										onclick="viewClaimDetails('<s:property value="applicationCodeItt"/>','<s:property value="statusItt"/>','<s:property value="reimCodeItt" />')" /></td>
								</tr>
								<%
								i++;
								%>
							</s:iterator>

							<%
							if (i == 1) {
							%>
							<tr align="center">
								<td colspan="7" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			--><!--------- DRAFT SECTION ENDS - displaying the saved leave applications -------->
	</table>

	<!-- ###################### CASH BALANCE BLOCK ENDS ############################## -->

<s:hidden name="historyCreditCode" /> 

 <s:hidden name="source" id="source" />

</s:form>



<script>



 function viewHistory(creditCode){
 	
 		document.getElementById('paraFrm_historyCreditCode').value=creditCode;
		var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
	   document.getElementById('paraFrm').action = 'ReimbursementClmAppl_retriveForHistory.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "_self";
	}
	
	

 function viewDetails(empCode,creditCode,creditName,reimbursBalance,billsCarriedForward){
		    
		     
		    // alert("empCode"+empCode);
		     //  alert("creditCode"+creditCode);
		    //    alert("creditName"+creditName);
		    
		   //  alert("reimbursBalance  "+reimbursBalance);
		    
		    if(eval(reimbursBalance)<=0)
		    {
		    	alert("You don't have sufficient balance for claim");
		    	return false;
		    }
		      	document.getElementById('paraFrm').action='ReimbursementClmAppl_retriveDetails.action?empCode='+empCode+'&creditCode='+creditCode+'&creditName='+creditName+'&reimbursBalance='+reimbursBalance
		      	+'&carriedForwardBills='+billsCarriedForward;
		      	document.getElementById('paraFrm').submit();
		     
		     
		    }
		    
		    
		    
 function viewClaimDetails(appCode,status,reimbursementCode){
		    
		     
		    //alert("appCode"+appCode);
		     //  alert("status"+status);
		   // alert("reimbursementCode"+reimbursementCode);
		   
		      	document.getElementById('paraFrm').action='ReimbursementClmAppl_viewClaimDetails.action?appCode='+appCode+'&status='+status+'&reimbursementCode='+reimbursementCode;
		      	document.getElementById('paraFrm').submit();
		     
		     
		    }
		    
		    

</script>