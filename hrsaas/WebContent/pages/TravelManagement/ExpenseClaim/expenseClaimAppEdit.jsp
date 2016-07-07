
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ExpenseClaimApp" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<s:hidden name="trvAppIdDtl"></s:hidden>
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;
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
			<td width="93%" class="txt"><strong class="formhead">Expense
			Claim Application</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>

			<s:hidden name="empId"></s:hidden>

			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
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
						color="red"></font></span></div>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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
						<td><strong class="formhead">Employee Informatiom</strong></td>




						<tr>
							<td width="20%" colspan="1" class="formtext" height="22">Employee
							Name :
							<td width="60%" colspan="3"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="employeeName" size="65" theme="simple" readonly="true" />
								<s:if test="%{generalFlag}"></s:if><s:else><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ExpenseClaimApp_f9Employee.action');"></s:else>
							<s:hidden name="employeeId"></s:hidden></td>

						</tr>


						<tr>
							<td width="10%" class="formtext" height="22">Branch:</td>
							<td width="25%"><s:textfield name="branchName"
								theme="simple" size="25" readonly="true" /></td>

							<td width="10%" class="formtext">Department:</td>
							<td width="25%"><s:textfield name="deptName" theme="simple"
								size="35" readonly="true" /></td>

						</tr>


						<tr>

							<td width="10%" class="formtext" height="22">Designation:</td>
							<td width="25%"><s:textfield name="desgName" theme="simple"
								size="25" readonly="true" /></td>
							<td width="10%" class="formtext">Application Date:</td>
							<td width="25%"><s:textfield name="applDate" size="10"
								readonly="true" theme="simple" maxlength="10" />
								<s:a
								href="javascript:NewCal('paraFrm_applDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" /></s:a></td>

						</tr>


						<tr>

							<td width="10%" class="formtext" height="22">Status:</td>
							<td width="25%"><s:textfield name="statusFld" theme="simple"
								size="25" readonly="true" /></td>
							<td width="10%" class="formtext">Grade:</td>
							<td width="25%"><s:textfield name="grdName" size="10"
								readonly="true" theme="simple" maxlength="10" /><s:hidden
								name="grdId" theme="simple"></s:hidden> </td>

						</tr>



					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>		

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<td><strong class="formhead">Expense Details </strong></td>


						<tr>
							<s:hidden name="policyId"></s:hidden>
							<td width="20%" class="formtext" height="22">Voucher Head :</td>
							<td width="25%"><s:textfield name="voucherHead"
								theme="simple" size="35" readonly="true" /><s:hidden
								name="voucherHeadCode" /> <s:hidden name="voucherAmt"></s:hidden>
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'ExpenseClaimApp_f9Voucher.action');"></td>

							<td width="10%" class="formtext">Expense Date :</td>
							<td width="25%"><s:textfield name="expenseDate"
								theme="simple" size="15" /><s:a
								href="javascript:NewCal('paraFrm_expenseDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" />
							</s:a><input type="button" class="token" value="Travel" /></td>

						</tr>

						<tr>
							<td width="20%" class="formtext" height="22">Amount:</td>
							<td width="25%"><s:textfield name="amount" theme="simple"
								size="25" /></td><s:hidden name="balAmt"/>

							<td class="formtext">Is proof attached :</td>
							<td><s:select name="proof" disabled="false"
								list="#{'N':'NO','Y':'Yes'}" theme="simple"
								onchange="showText1();" /></td>


						</tr>

						<tr>
							<td width="20%" class="formtext" height="22">Particulars :</td>
							<td width="25%"><s:textarea name="particulars"
								theme="simple" cols="35" rows="2" /></td>

							<td width="10%" class="formtext" id="chqId">Upload File :</td>
							<td width="25%" id="chqTextId"><s:textfield
								name="uploadFileName" theme="simple" size="25" readonly="true" /><input
								type="button" class="token" value="Browse"
								onclick="uploadFile('uploadFileName');" /></td>

						</tr>
						<tr>
							<td width="10%" class="formtext" height="22">&nbsp;&nbsp;&nbsp;</td>
							<td width="10%" class="formtext" height="22">&nbsp;&nbsp;&nbsp;</td>
							<td width="10%"><input type="button" class="add"
								value="   Add" align="center" onclick="callAdd();" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
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
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td width="20%"><input type="button" class="token"
								value="   Track Change" align="center" /></td>
							<td width="5%"><input type="button" class="token"
								value="   Delete" align="center" onclick="return chkDelete();"/></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="1" cellpadding="1" cellspacing="1"
						class="sortable">

						<tr>
							<td width="5%" valign="top" class="formth">Sr.No.</td>
							<td width="10%" valign="top" class="formth">Voucher Head</td>
							<td width="10%" valign="top" class="formth">Particulars</td>
							<td width="10%" valign="top" class="formth">Expense Date</td>
							<td width="10%" valign="top" class="formth">Amount(Rs.)</td>
							<td width="10%" valign="top" class="formth">Valid Amount</td>
							<td width="10%" valign="top" class="formth">Proof Attached</td>
							<td width="10%" valign="top" class="formth">Uploaded File</td>
							<td width="5%" valign="top" class="formth">&nbsp;</td>
						</tr>
							<%!int j = 0;%>
							<%
							int k = 0;
							%>
							

						<s:iterator value="expList">
							<tr>
								<td class="border2" width="5%"><%=++k%><input type="hidden"
																name="srNo" value="<%=k%>" /></td>
								<td class="border2" width="12%"><s:property
									value="itVoucher" /><s:hidden name="itVoucher" /><s:textfield name="itVoucherHeadCode"/><s:hidden value="itVoucherHeadCode"/></td>
								<td class="border2" width="22%"><s:property
									value="itParticulars" /><s:hidden name="itParticulars" /></td>
								<td class="border2" width="15%"><s:property
									value="itExpenseDate" /><s:hidden name="itExpenseDate" /></td>
								<td class="border2" width="22%"><s:property
									value="itAmount" /><s:hidden name="itAmount" /></td>
								<td class="border2" width="15%"><input type="text"
									name="itValAmount" value='<s:property value="itValAmount"/>' /><s:hidden name="itValAmount" /></td>
								<td class="border2" width="22%"><a href="#" onclick="return showRecord('<s:property value="itUpload" />');">show</a></td><s:hidden name="isProof"></s:hidden><s:property value="isProof"/>
								<td class="border2" width="15%"><s:property
									value="itUpload" /><s:hidden name="itUpload" /></td>
									<input type="hidden" name="hdelete"
										id="hdelete<%=k%>" />
								<td class="border2" width="15%" align="center"><input
									type="checkbox" name="confChk"  value="N"
										id="confChk<%=k%>" onclick="callForDelete('<%=k%>')" ></td>

							</tr>
							
						</s:iterator>
						<%
						j = k;
						%>

					</table>
					<s:hidden name="totAmt"></s:hidden>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<td><strong class="formhead"></strong></td>


								<tr>
									<td width="8%" class="formtext" height="22">Preferred
									Payment Mode :</td>
									<td width="24%"><s:select name="mode" disabled="false"
										list="#{'':'Select','1':'Cash','2':'Salary','3':'Cheque','4':'Transfer'}"
										theme="simple" onchange="showText();" /></td>


								</tr>

								<tr id="trnId">
									<td width="8%" class="formtext" height="22">Account No.:</td>
									<td width="24%"><s:textfield name="accNo" theme="simple"
										size="20" /></td><s:hidden name="bankName"></s:hidden>
								</tr>
								<tr id="salId">
									<td width="8%" class="formtext" height="22">Month:</td>
									<td width="24%"><s:select name="month" disabled="false"
										list="#{'':'Select','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"
										theme="simple" /></td>

								</tr>
								<tr id="salYrId">
									<td width="8%" class="formtext" height="22">Year:</td>
									<td width="24%"><s:textfield name="year" theme="simple"
										size="15" /></td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<td><strong class="formhead"></strong></td>


								<tr>
									<td width="8%" class="formtext" height="22">Comments :</td>
									<td width="24%"><s:textarea name="comment"
										theme="simple" cols="35" rows="2" /></td>


								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>



				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="22%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	showText();
	showText1();
	function showText(){
		//alert("showText");		
		document.getElementById('trnId').style.display='none';
		document.getElementById('salId').style.display='none';
		document.getElementById('salYrId').style.display='none';		
		var chq = document.getElementById('paraFrm_mode').value;					
				if(chq == "2" ){				
				document.getElementById('salId').style.display='';
				document.getElementById('salYrId').style.display='';
				}
				else if(chq=="4"){
				document.getElementById('trnId').style.display='';
				}				
			}		
			
			
			
			function showText1(){
		//alert("showText");		
		document.getElementById('chqId').style.display='none';
		document.getElementById('chqTextId').style.display='none';		
		var chq = document.getElementById('paraFrm_proof').value;					
				if(chq == "Y" ){				
				document.getElementById('chqId').style.display='';
				document.getElementById('chqTextId').style.display='';						
				}							
			}			
			
	function uploadFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/expenseClaim";		
		 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 document.getElementById('paraFrm').target="main";
		} 
		
		
	function showRecord(fieldName)
		{	
		if(fieldName=="")
		{
		alert('File not available');
		return false;
		}
		var path='<%=session.getAttribute("session_pool")%>';
		window.open('../pages/images/'+path+'/expenseClaim/'+fieldName);	
		}
	
	function callAdd(){ 
	    document.getElementById('paraFrm').target =""; 
	    document.getElementById('paraFrm').action = "ExpenseClaimApp_addExpense.action";
		document.getElementById('paraFrm').submit();
		
	}
	
	function chkDelete(){
	//alert("chkDelete");
	if(chk()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="ExpenseClaimApp_deleteExp.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=j %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChk'+a).checked=false;
	   document.getElementById('confChk'+a).checked="";
	    document.getElementById('hdelete'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	function chk(){
	 	var flag='<%=j %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDelete(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChk'+id).checked == true)
	   {	  
	    document.getElementById('hdelete'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdelete'+id).value="";	   
   		}
   		
   		
   		
   		function saveFun()
	{	
	document.getElementById('paraFrm').action="ExpenseClaimApp_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	}	
	
	  		
   		
   		
</script>
