
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
var disbursement = new Array();
</script>

<s:form action="InvoiceDisbursement" validate="true" id="paraFrm"
	theme="simple" method="post">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1" width="4%"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>


					<td width="93%" class="txt" colspan="1"><strong
						class="text_head"> Invoice Disbursement</strong></td>

					<td width="3%" valign="top" class="txt" colspan="1">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td width="100%" colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">



				<tr>
					<td width="78%" align="left"><s:if test="disburseFlag"></s:if><s:else>
						<input type="button" value="Save" class="token"
							onclick="callSave();" />
						<input type="button" value="Reset" class="token"
							onclick="callReset();" />
					</s:else> <input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"><s:if test="disburseFlag"></s:if><s:else>
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</s:else></td>
				</tr>



			</table>
			</td>
		</tr>

		<s:if test="approverCommentsFlag">
			<tr>
				<td>
				<table class="formbg" width="100%">
					<tr>
						<td width="25%"><label class="set" name="trvlClm.apprCmts"
							id="trvlClm.apprCmts" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.apprCmts")%></label>
						:</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="clmApprCmts" /></textarea></td>
					</tr>

				</table>
				</td>
			</tr>

		</s:if>


		<s:if test="true">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top"
									ondblclick="callShowDiv(this);">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
								</td>

							</tr>


							<%
							int m = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=m%><s:hidden
										name="appSrNo" value="%{<%=m%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="prevApproverDate" /><s:hidden name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
								</tr>
								<%
								m++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>

		<tr>
			<td width="100%" colspan="4">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td colspan="4" width="30%"><strong>Partner Details</strong></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%">
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="partner.name" id="partner.name"
								ondblclick="callShowDiv(this);"><%=label.get("partner.name")%></label>
							:</td>
							<td width="30%" colspan="1">
							<s:property value="partnerName"/>
							</td>

							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="partner.code" id="partner.code"
								ondblclick="callShowDiv(this);"><%=label.get("partner.code")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property value="partnerCode"/></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="partner.date" id="partner.date"
								ondblclick="callShowDiv(this);"><%=label.get("partner.date")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property value="partnerDate"/></td>
							</td>
							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="partner.type" id="partner.type"
								ondblclick="callShowDiv(this);"><%=label.get("partner.type")%></label>

							:</td>
							<td width="30%" colspan="1"><s:property value="partnerType"/></td>
						</tr>
						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="partner.invoicefrmdate"
								id="partner.invoicefrmdate" ondblclick="callShowDiv(this);"><%=label.get("partner.invoicefrmdate")%></label>

							:</td>
							<td width="30%" colspan="1"><s:property value="partnerInvoiceFrmDate"/></td>
							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="partner.project" id="partner.project"
								ondblclick="callShowDiv(this);"><%=label.get("partner.project")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property value="partnerProject"/></td>
						</tr>

						<tr>
							<td width="20%" class="formtext" height="22" colspan="1"><label
								class="set" name="partner.invoicetodate"
								id="partner.invoicetodate" ondblclick="callShowDiv(this);"><%=label.get("partner.invoicetodate")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property value="partnerInvoiceToDate"/></td>
							<td width="20%" class="formtext" colspan="1"><label
								class="set" name="partner.otherproject"
								id="partner.otherproject" ondblclick="callShowDiv(this);"><%=label.get("partner.otherproject")%></label>

							:</td>
							<td width="30%" colspan="1"></td>
						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" align="center" class="formbg" theme="simple">
				<tr>
					<td><strong>Payment Details</strong></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%">
						<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="totDisAmt" id="totDisAmt"
								ondblclick="callShowDiv(this);"><%=label.get("totDisAmt")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield size="10"
								name="totalInvoiceDisbrAmt" value="%{totalInvoiceDisbrAmt}" readonly="true"
								theme="simple" /></td>
						</tr>

					</table>
					</td>
				</tr>



			</table>
			</td>
		</tr>

		<tr id="hideTR">
			<td colspan="4" width="100%">

			<table width="100%" class="formbg" border="0" theme="simple"
				id="disbursement">


				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="pay.date" id="pay.date"
						ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:textfield name="disburseDate"
						size="15" maxlength="10" onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_disburseDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_disburseDate','dd-mm-yyyy')" /> <s:a
						href="javascript:NewCal('paraFrm_disburseDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="pay.mode" id="pay.mode"
						ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label><font
						color="red">*</font>:</td>
					<td width="30%" colspan="1"> <s:select name="disburseMode" list="payModeMap" size="1"
						headerKey="" headerValue="--Select--" cssStyle="width:100"
						theme="simple" onchange="return changePayMode();" /></td>
				</tr>
				<tr id="cheque">
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="chk.date" id="chk.date"
						ondblclick="callShowDiv(this);"><%=label.get("chk.date")%></label>
					:<font color="red">*</font></td>
					<td width="30%" nowrap="nowrap" colspan="1"><s:textfield
						name="disburseChqDate" size="15" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_disburseChqDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_disburseChqDate','dd-mm-yyyy')" /> <s:a
						href="javascript:NewCal('paraFrm_disburseChqDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16"
							height="16" border="0" />
					</s:a></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="chk.no" id="chk.no"
						ondblclick="callShowDiv(this);"><%=label.get("chk.no")%></label> :<font
						color="red">*</font></td>
					<td width="30%"><s:textfield name="disburseChqNo" size="25"
						maxlength="10" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr id="bank">
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="acco.no" id="acco.no"
						ondblclick="callShowDiv(this);"><%=label.get("acco.no")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:textfield
						name="disburseAccount" size="25" maxlength="10"
						onkeypress="return numbersOnly();" /></td>
					<td width="20%" class="formtext" colspan="1"><label
						class="set" name="bank.name" id="bank.name"
						ondblclick="callShowDiv(this);"><%=label.get("bank.name")%></label>
					:<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:hidden name="bankCode" /> <s:hidden
						name="bankIFSCCode" /> <s:hidden name="bankBranch" /> <s:hidden
						name="bankBSRCode" /> <s:textfield name="disburseBankName"
						size="25" maxlength="30"  />

					</td>
				</tr>


				<tr>
					<td width="20%" class="formtext" height="22" colspan="1"><label
						class="set" name="amount" id="amount1"
						ondblclick="callShowDiv(this);"><%=label.get("amount")%></label> :<font
						color="red">*</font></td>

					<td width="30%" colspan="1"><s:textfield name="disburseAmount"
						size="15" maxlength="10" onkeypress="return numbersWithDot();"
						  /> </td>
					<td width="20%" class="formtext" valign="middle" colspan="1"><label
						class="set" name="comments" id="comments1"
						ondblclick="callShowDiv(this);"><%=label.get("comments")%>
					:</label></td>
					<td width="30%" nowrap="nowrap" colspan="1"><s:hidden
						name="descCnt1" /> <s:textarea name="disbursementComment"
						rows="3" cols="23" readonly="false" /><img
						src="../pages/images/zoomin.gif" height="12" align="bottom"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_disbursementComment','comments','','paraFrm_descCnt1','2000');"></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="disburseFlag"></s:if><s:else>

			<tr id="addremove">
				<td width="100%" colspan="4" align="center"><input type="button"
					class="add" value="Add" onclick="callAdd();" /> <input
					type="button" class="delete" value="Remove" onclick="callRemove();" />
				</td>


			</tr>
		</s:else>
		
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" class="formbg" border="0" theme="simple"
				id="claimDisbursement">
				<tr>
					<td class="formth" width="5%" colspan="1"><b><label
						class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
					<td class="formth" width="10%" colspan="1"><b><label
						class="set" name="pay.date" id="pay.date"
						ondblclick="callShowDiv(this);"><%=label.get("pay.date")%></label></b></td>
					<td class="formth" width="15%" colspan="1" align="left"><b><label
						class="set" name="pay.mode" id="pay.mode"
						ondblclick="callShowDiv(this);"><%=label.get("pay.mode")%></label></b></td>
					<td class="formth" width="15%"><b><label class="set"
						name="payDet" id="payDet" ondblclick="callShowDiv(this);"><%=label.get("payDet")%></label></b></td>
					<td class="formth" width="10%"><b><label class="set"
						name="amount" id="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></b></td>
					<td class="formth" width="30%"><b><label class="set"
						name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></b></td>
					<s:if test="disburseFlag">
						<td class="formth" width="10%">&nbsp;</td>
						<td class="formth" width="5%">&nbsp;</td>
					</s:if>
					<s:else>
						<td class="formth" width="10%"><b>Delete</b></td>
						<td class="formth" width="5%"><input type="checkbox"
							name="checkAll" id="checkAll" onclick="callAll();" /></td>
					
					</s:else>

				</tr>

				<%
					int i = 1;
				%>
				<s:iterator value="paymentList">

					<tr id="editRow<%=i%>">
						<td class="sortableTD" width="5%" align="center">
						
						<s:hidden name="creditCodeItt" />
						<s:hidden name="creditNameItt" />
						
						<%=i%> <input
							type="hidden" name="editButton" id="editButton<%=i %>"
							value="<s:property value="editButton" />"><input
							type="hidden" name="disbBalId" id="disbBalId<%=i %>"
							value="<s:property value="disbBalId" />"></td>
						<td class="sortableTD" align="center" width="15%"><s:hidden
							name="disbBalDate" /><s:property value="disbBalDate" />&nbsp;</td>
						<td class="sortableTD" width="15%"><s:hidden
							name="disbBalPayMode" /><s:property value="disbBalPayMode" />&nbsp;</td>
						<td class="sortableTD" width="28%"><s:hidden name="bankId" /><s:hidden
							name="bankName" /><s:hidden name="accountNo" /><s:hidden
							name="chqDate" /><s:hidden name="chqNo" /><s:hidden name="month" />
							<s:hidden name="year" /><s:property
							value="paymentDet" /><s:hidden name="paymentDet" />&nbsp;</td>
						<td class="sortableTD" width="12%" align="right">
							<s:hidden name="disbBalAmt" id='<%="disbBalAmt"+i%>' />
							<s:property value="disbBalAmt" />
							
							&nbsp;
						</td>
						<td class="sortableTD" width="20%" align="center"><s:property
							value="disbBalCmt" /><s:hidden
							name="disbBalCmt" id="<%="disbBalCmt"+i %>" /> <!--<img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							width="12" theme="simple"
							onclick="javascript:callWindow('<%="disbBalCmt"+i %>','comments','readonly','2000','2000');">
						--><s:hidden name="disbursementId" />&nbsp; <input type="hidden"
							name="payFlag" id="payFlag<%=i%>" value="N"></td>
						<script type="text/javascript">
							
							disbursement[<%=i%>] = document.getElementById('disbBalAmt'+<%=i%>).value;
							
						</script>



						<td class="sortableTD" width="5%"><s:if test="editButton"></s:if><s:else>
							<!--  <input type="button" class="token" value="Edit"
								onclick="callForEdit('<%=i %>','<s:property value="disbBalId"/>','<s:property value="disbBalDate"/>',
								'<s:property value="disbBalPayMode"/>','<s:property value="bankId"/>','<s:property value="accountNo"/>',
								'<s:property value="chqDate"/>','<s:property value="chqNo"/>','<s:property value="disbBalAmt"/>',
								'<s:property value="disbBalCmt"/>','<s:property value="bankName"/>','<s:property value="month"/>','<s:property value="year"/>');" />
							-->
						</s:else> &nbsp;</td>
						<td class="sortableTD" width="5%" valign="middle">&nbsp; <s:if
							test="editButton">

						</s:if><s:else>
							<input type="checkbox" name="payChk" id="payChk<%=i%>"
								onclick="callCheck('<%=i %>');">
						</s:else></td>


					</tr>

					<%
						i++;
					%>
				</s:iterator>



			</table>

			</td>
		</tr>

<tr>
			<td colspan="8">
			<table border="0" width="100%">
				<tr>
					<td align="right" colspan="6"><label class="set" name="totAmt"
						id="totAmt" ondblclick="callShowDiv(this);"><%=label.get("totAmt")%></label>
					:</td>
					<td colspan="2" align="center">
						<s:textfield size="10" name="totalAmount" value="%{totalAmount}" readonly="true" theme="simple" />
						 
					</td>
				</tr>
				
				<tr>
					<td align="right" colspan="6"><label class="set" name="balAmt"
						id="balAmt" ondblclick="callShowDiv(this);"><%=label.get("balAmt")%></label>
					:</td>
					<td colspan="2" align="center">
						<s:textfield size="10" name="balanceAmount" value="%{balanceAmount}" readonly="true" theme="simple" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!--<tr>
							<td width="30%" class="formtext" height="22" colspan="1"><label
								class="set" name="totDisAmt" id="totDisAmt"
								ondblclick="callShowDiv(this);"><%=label.get("totDisAmt")%></label>
							:</td>
							<td width="25%" colspan="2">
								<s:textfield size="10" name="totDisbrAmt" value="%{totDisbrAmt}" readonly="true" theme="simple" />
								 
							</td>
						</tr>
		
		<tr>
			--><td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:if test="disburseFlag"></s:if><s:else>
						<input type="button" value="Save" class="token"
							onclick="callSave();" />
						<input type="button" value="Reset" class="token"
							onclick="callReset();" />
					</s:else> <input type="button" value="Return to List" class="token"
						onclick="callBack();" /></td>
					<td width="22%" align="right"></td>
				</tr>
				
	</table>
<s:textfield name="paraId" />
<s:textfield name="hiddenInvoiceCode"></s:textfield>
 </td></table></s:form>

<script>


var currentTime = new Date();
var month = currentTime.getMonth() + 1;
var day = currentTime.getDate();
var year = currentTime.getFullYear();
var currentDate=day+"-"+month+"-"+year ;

changePayMode();



function callCheck(id){
		if(document.getElementById('payChk'+id).checked ==true)
			document.getElementById('payFlag'+id).value="Y";
		else
			document.getElementById('payFlag'+id).value="N";
		}//End of function
		
		

function callAdd(){

try{
					if(document.getElementById('paraFrm_disburseMode').value==""){						
							       alert("Please select the "+document.getElementById('pay.mode').innerHTML.toLowerCase());
							    	return false;
								
						}
						if(trim(document.getElementById('paraFrm_disburseDate').value)==""){
							    alert("Please select or enter the "+document.getElementById('pay.date').innerHTML.toLowerCase());
							     return false;
			
			          		}
			try{
					     if(!validateDate("paraFrm_disburseDate","pay.date")){
				               document.getElementById('paraFrm_disburseDate').focus();
				               return false;
				
				             }
				             
				           var pay=document.getElementById('paraFrm_disburseDate').value 
				          if(!dateCompare(currentDate,pay,'paraFrm_disburseDate','pay.date'))
				               return false;
			
						  if(document.getElementById('paraFrm_disburseMode').value=="TR"){						
							    if(document.getElementById('paraFrm_disburseAccount').value==""){							       
							       alert("Please enter the "+document.getElementById('acco.no').innerHTML.toLowerCase());
							    	return false;
							    }
							    if(document.getElementById('paraFrm_disburseBankName').value==""){							    
							     alert("Please select the "+document.getElementById('bank.name').innerHTML.toLowerCase());
							    	return false;
							    
							    }
						
								
						}//end of transfer
			 
			 		   if(document.getElementById('paraFrm_disburseMode').value=="CH"){
						   if(trim(document.getElementById('paraFrm_disburseChqDate').value)==""){
						   		alert("Please select or enter the "+document.getElementById('chk.date').innerHTML.toLowerCase());
						   		return false;
						   }
						   if(!validateDate("paraFrm_disburseChqDate","chk.date")){
				               document.getElementById('paraFrm_disburseChqDate').focus();
				               return false;
				
				           }
			             	var chq=document.getElementById('paraFrm_disburseChqDate').value;
							var pay=document.getElementById('paraFrm_disburseDate').value;
							if(!dateDifferenceEqual(pay, chq, 'paraFrm_disburseChqDate', 'pay.date','chk.date'))
		  							return false;
						    if(document.getElementById('paraFrm_disburseChqNo').value==""){
					    		alert("Please enter the "+document.getElementById('chk.no').innerHTML.toLowerCase());
					    	   	return false;
						    }
					  	}//end of cheque
			
			
						if(trim(document.getElementById('paraFrm_disburseAmount').value)==""){
								alert("Please enter the "+document.getElementById('amount').innerHTML.toLowerCase());
								return false;
						}
						
					
						
						
						if(!valCTC('paraFrm_disburseAmount','amount'))
							return false;
							
							
						var deduction=document.getElementById('paraFrm_disburseAmount').value;	
						
						
						
						if(deduction.indexOf(".")!=-1){
						 var dot=deduction.split('.');
				         if(dot[1].length >2){
					      alert("Only two digits are allowed after the dot.");
					      document.getElementById('paraFrm_disburseAmount').focus();
					      return false;
     			          }
						}
					}catch(e){
						alert(e);
					}
					
				
						var disbursedAmt=document.getElementById('paraFrm_totalInvoiceDisbrAmt').value;
						var amount=0;
						var disburse=document.getElementById('paraFrm_disburseAmount').value
						for(var i=1;i<disbursement.length;i++){
						
							amount+=parseFloat(disbursement[i]);
							
							
						 }
		
			alert('amount '+amount);

 if(parseFloat(amount)==0){ 
 
  
 		  
			if(parseFloat(disburse) > parseFloat(disbursedAmt)){
				
					alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
					return false;
		
			}
			else
			{
			 
			}
	}else{
	
	 
			if(document.getElementById('paraFrm_paraId').value==""){
			
			 
				
				alert(parseFloat(disbursedAmt));	
						if((parseFloat(amount)+parseFloat(disburse)) > parseFloat(disbursedAmt)){						
							alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
							return false;
						
						}
				}else{		
						
						  var id=document.getElementById('paraFrm_paraId').value;
						  
						  var editAmt=document.getElementById('disbBalAmt'+id).value;	
						  
						  amount=eval(amount)-eval(editAmt);
						 
						  if(eval(amount)+eval(disburse) > eval(disbursedAmt)){
						    alert(document.getElementById('amount').innerHTML.toLowerCase()+ " should not exceed the "+document.getElementById('totDisAmt').innerHTML.toLowerCase()+".");
							return false;
						
				  }	
	
	          }		
	
	
	}	
			var comment=trim(document.getElementById('paraFrm_disbursementComment').value);
			if(comment.length >2000){
			
				alert("Maximum length of "+document.getElementById('comments').innerHTML.toLowerCase()+" is 2000 characters");
				return false;
			}
			
			
				
				
		
	if(parseFloat(amount)==0){
			document.getElementById('paraFrm_totalAmount').value=parseFloat(disburse);
			var total=document.getElementById('paraFrm_totalAmount').value;
			document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);
		
	   }else{
	
				if(document.getElementById('paraFrm_paraId').value==""){
					document.getElementById('paraFrm_totalAmount').value=parseFloat(amount)+parseFloat(disburse);
					var total=document.getElementById('paraFrm_totalAmount').value;
					document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);
				}else{
						  var id=document.getElementById('paraFrm_paraId').value;
						  var editAmt=document.getElementById('disbBalAmt'+id).value;	
						  document.getElementById('paraFrm_totalAmount').value=parseFloat(amount)+parseFloat(disburse);
					      var total=document.getElementById('paraFrm_totalAmount').value;
					      document.getElementById('paraFrm_balanceAmount').value=parseFloat(disbursedAmt)-parseFloat(total);	
				}
        }		
        
        
			
		if(document.getElementById('paraFrm_paraId').value==""){
		
		     		document.getElementById('paraFrm').action ="InvoiceDisbursement_addPayDtls.action";  
			        document.getElementById('paraFrm').submit();  
		        }else{
		        callReset();
			        document.getElementById('paraFrm').action ="InvoiceDisbursement_editPayDtls.action";  
			        document.getElementById('paraFrm').submit(); 
		        }
		        callReset();
	}catch(e){alert("Final Exception to be printed  "+e);}	        
		
    }
    
    
    function dateCompare(fromDate, toDate, fieldName,toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to system date.");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
		
		
	function changePayMode(){
    
    var mode=document.getElementById('paraFrm_disburseMode').value;
   	
    if(mode=="TR"){
    	
    		document.getElementById('cheque').style.display='none';
    		document.getElementById('bank').style.display='';
    		
    		
    		document.getElementById('paraFrm_disburseChqDate').value="";
    		document.getElementById('paraFrm_disburseChqNo').value="";
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    			document.getElementById('creditHeadShowId').style.display='none';
    		
            
    }else if(mode=="CH"){
            //document.getElementById('paraFrm_bankCode').value="";
    		//document.getElementById('paraFrm_disburseBankName').value="";
            //document.getElementById('paraFrm_disburseAccount').value="";
            document.getElementById('cheque').style.display='';
    		document.getElementById('bank').style.display='none';
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_monthClaim').value="";
    		document.getElementById('paraFrm_yearClaim').value="";
    		document.getElementById('creditHeadShowId').style.display='none';
    
        } else{
        
            //document.getElementById('paraFrm_bankCode').value="";
    		//document.getElementById('paraFrm_disburseBankName').value="";
            //document.getElementById('paraFrm_disburseAccount').value="";
            document.getElementById('cheque').style.display='none';
    		document.getElementById('bank').style.display='none';
    		document.getElementById('salary').style.display='none';
    		document.getElementById('paraFrm_disburseChqDate').value="";
    		document.getElementById('paraFrm_disburseChqNo').value="";
    		document.getElementById('paraFrm_monthClaim').style.display='none';
    		document.getElementById('paraFrm_yearClaim').style.display='none';
    		document.getElementById('creditHeadShowId').style.display='none';
        
        }
        
        } 	
        
        
       
 		function callBack()
		{    	
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "InvoiceDisbursement_input.action";
		document.getElementById('paraFrm').submit();  
		}
		
		
		
	function callReset(){
	 
	 	try{
		document.getElementById("paraFrm_disburseDate").value="";
		document.getElementById("paraFrm_disburseMode").value="CA";
		document.getElementById("paraFrm_disburseChqDate").value="";
		document.getElementById("paraFrm_disburseChqNo").value="";
		document.getElementById("paraFrm_disburseAccount").value="";
		document.getElementById("paraFrm_disburseBankName").value="";
		document.getElementById("paraFrm_bankCode").value="";
		document.getElementById("paraFrm_disburseAmount").value="";
		document.getElementById("paraFrm_disbursementComment").value="";
		document.getElementById("paraFrm_disburseChqNo").value="";
	    document.getElementById("paraFrm_disburseChqDate").value="";	
	    //document.getElementById("paraFrm_disburseStatus").value="S";  
	    document.getElementById('cheque').style.display='none';
    	document.getElementById('bank').style.display='none';
    	//document.getElementById('paraFrm_disburseStatus').disabled=false;
    	
    	}catch(e)
    	{
    		alert("Error "+e);
    	}
    
     
     }
     
     function valCTC(fieldName,labelName)
	{
	var amount=document.getElementById(fieldName).value;
	var amountLabel=document.getElementById(labelName).innerHTML.toLowerCase();
	if(trim(amount)!=""){
		 if(isNaN(amount)){ 
		    alert("Only one dot is allowed in "+amountLabel+" field.");
		    document.getElementById(fieldName).focus();
		    return false;
		    }	
		  }
		return true;
  }
  
  
  
  function callAll(){
	var tbl = document.getElementById('claimDisbursement');
		var rowLen = tbl.rows.length;
		//alert('rowLen'+rowLen);
	if (document.getElementById('checkAll').checked == true){
	
	for (var idx = 1; idx <= rowLen; idx++) {
	
	
	    if(document.getElementById('disbBalId'+idx).value=='')
	    {
				document.getElementById('payChk'+idx).checked = true;
				 document.getElementById('payFlag'+idx).value="Y";
			}
			else{
			continue;
			}
	    }

 }else{
 
	for (var idx =1; idx <= rowLen; idx++) {
	 if(document.getElementById('disbBalId'+idx).value=='')
	    {
			
	document.getElementById('payChk'+idx).checked = false;
	document.getElementById('payFlag'+idx).value="N";
	}
	else{
			continue;
			}
	
     }
  }	 


}
	 
	 
	 	function callRemove(){
	//alert('in remove');
			var result=false;
			var tbl = document.getElementById('claimDisbursement');
			//alert('tbl.rows.length'+tbl.rows.length);
			var rowLen = tbl.rows.length-1;
			
		//alert('in rowLen'+rowLen);
			
			
			
			
			for (var idx = 1; idx <= rowLen; idx++) {
			//
			 if(document.getElementById('disbBalId'+idx).value=='')
	    {
			
	    		if(document.getElementById('payChk'+idx).checked)
	    		 result=true;	
	    }
	    		 else{
	    		//alert('in else');
			 continue;
			}
	    		  
	        }	
	        if(!result){
	        	alert("There is no record to remove.");
	        	return false;
	        }else{
			    var conf=confirm("Do you really want to remove the records?");
			    if(conf){
				document.getElementById('paraFrm').action ="InvoiceDisbursement_removePayDtls.action";  
				document.getElementById('paraFrm').submit();
				}
			}	
		}//End of function
		
		
		function callSave()
		{
			var tbl = document.getElementById('claimDisbursement');
		var rowLen = tbl.rows.length;
				if(rowLen==1)
			{
				alert('Please add payment details. ');
				return false;
			}
			
				document.getElementById('paraFrm').action ="InvoiceDisbursement_saveData.action";  
	document.getElementById('paraFrm').submit();
		
		}
		
		
		
		
		
		function callSave_OLD(){
//alert('enter');

try{
		
	var tbl = document.getElementById('claimDisbursement');
	//var disburseStatus=document.getElementById('paraFrm_disburseStatus').value;
	//var disbAmt = document.getElementById('paraFrm_totDisbrAmt').value;
	
	
	var rowLen = tbl.rows.length;
	//&& disbAmt>0
	if(rowLen==1)
	{
		alert('Please add payment details. ');
		return false;
	}
	var sttamt=document.getElementById('paraFrm_disburseAmount').value;
	
	/*if(sttamt!='')
	{
	   alert('Please add payment details.');
	   return false;
	}*/
	
	var amt=eval(document.getElementById('paraFrm_balanceAmount').value)
	/*if(disburseStatus=='S')
	{ alert('Please select status');
	  return false;
	}
	
	if(amt==0.00 && disburseStatus=='P')
	{
		alert('You cannot keep status as partially closed ');
		return false;
	
	}
	else if(amt>0 && disburseStatus=='C')
	{
		alert('you cannot keep status as  Closed ');
		return false;
	
	}
	else if(amt>=0 && disburseStatus=='X')
	{
		alert('Status should not be over Payment');
		return false;
	
	}*/
	if(disbAmt<0){
		if(document.getElementById('paraFrm_recoveryMonth').value=="0"){
			alert("Please select "+document.getElementById("recovery.month").innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_recoveryYear').value==""){
			alert("Please enter "+document.getElementById("recovery.year").innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_recoveryDebitCode').value==""){
			alert("Please select "+document.getElementById("recovery.debit").innerHTML.toLowerCase());
			return false;
		}
	
	}
	
	if(rowLen==1 && eval(document.getElementById('paraFrm_totDisbrAmt').value>0)){
	//alert("In ifffffffffff");
		alert("There are no records to save.");
		return false;
	}
	else{
	//alert('disburseStatus'+document.getElementById('paraFrm_disburseStatus').value);
	document.getElementById('paraFrm').action ="InvoiceDisbursement_saveData.action";  
	document.getElementById('paraFrm').submit();
	}
	}catch(e){
		alert(e);
		return false;
	}
}

</script>
