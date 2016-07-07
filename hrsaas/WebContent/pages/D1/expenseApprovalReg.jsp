<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="AjaxShortTerm.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ExpenseApprovalReg" id="paraFrm" validate="true"  name="ShortTermForm" 
	target="main" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<s:hidden name="expenseApproverCode" />
			<s:hidden name="apptype"  />
			<s:hidden name="status"  />
			<s:hidden name="dataPath" />
			<s:hidden name="flag"/>
			<s:hidden name="myPage" id="myPage" />
			<s:hidden name="myPage1" id="myPage1" />
			<s:hidden name="myPage2" id="myPage2" />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Expense Request Form11111111111</strong>
					<s:hidden name="hiddenValue"/>
					</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="listComment">
		<tr>
			 <td colspan="4">
			   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">		
						
						
						<tr>
							<td width="25%" colspan="4"><B>Comments By Approver</B>							</td>
							
						</tr>
						
							
						<tr>
						<td class="formth" width="05%">Sr No </td>
						<td class="formth" width="15%">Approver Name </td>
						<td class="formth" width="40%" align="center">Comments </td>
						<td class="formth" width="15%">Application Date </td>
						<td class="formth" width="15%">Status </td>						
						</tr>
						<%int tt=1; %>
						
						<s:iterator value="listComment">
						<tr>
						<td   class="sortableTD"><%=tt++ %> </td>
						<td   class="sortableTD"> <s:property value="ittApproverName"/></td>
						<td   class="sortableTD"><s:property value="ittComments"/> </td>
						<td   class="sortableTD"> <s:property value="ittApplicationDate"/></td>
						<td   class="sortableTD"> <s:property value="ittStatus"/></td>
						</tr>
						</s:iterator>						
				</table>
				</td>
	</tr>
	</s:if>		
		
		
		
			
						
		<s:hidden name="fileheaderName"/>
		
		<!-- Request Information start -->

			<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						<tr>
							<td colspan="4"><label class="set"
								name="main.header.mess" id="main.header.mess"
								ondblclick="callShowDiv(this);"><%=label.get("main.header.mess")%></label>
							</td>
						</tr>
					</table>
					</td>
		</tr>
		
					
				
				<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						
						<tr>
							<td colspan="4"><b><label class="set"
								name="employee.information" id="employee.information"
								ondblclick="callShowDiv(this);"><%=label.get("employee.information")%></label> </b>
							</td>
						</tr>

							<tr>
								<td width="18%"><label id="pemployee.name" name="employee.name"
									ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
								:<font color="red">*</font></td>
								<td width="50%" colspan="3"><s:textfield  cssStyle="background-color: #F2F2F2;" 
									name="employeeToken" size="20" theme="simple" readonly="true" /><s:textfield
									name="employeeName" size="71" theme="simple" readonly="true"  cssStyle="background-color: #F2F2F2;" />
								<s:hidden name="employeeCode" />
								
								<s:if test="%{employeeCode == ''}">
								<img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16" id='ctrlHide'
									onclick="javascript:callsF9(500,325,'ExpenseApprovalReg_f9action.action');">
								</s:if>	
								</td>
						</tr>
						
						
						
						<tr>
							<td width="25%"><label id="address"
								name="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
							:</td>
							<td width="25%"><s:textfield name="address"
								 size="20" maxlength="400" /></td>
							<td width="25%" align="left"></td>
							
							<td width="25%"></td>
						</tr>
						
						<tr>
							<td width="25%"><label id="city"
								name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
							:</td>
							<td width="25%"><s:textfield name="city"
								maxlength="200" size="20"  /></td>
							<td width="25%" align="left"><label id="state"
								name="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
							:</td>
							<td width="25%"><s:textfield name="state"
								 size="20" maxlength="200" /></td>
						</tr>
						
							<tr>
							<td width="25%"><label id="zip.code"
								name="zip.code" ondblclick="callShowDiv(this);"><%=label.get("zip.code")%></label>
							:</td>
							<td width="25%"><s:textfield name="zipCode"
								maxlength="20" size="20"  /></td>
							<td width="25%" align="left"><label id="telephone.no"
								name="telephone.no" ondblclick="callShowDiv(this);"><%=label.get("telephone.no")%></label>
							:</td>
							<td width="25%"><s:textfield name="telephoneNo"
								maxlength="20" size="20"  /></td>
						</tr>
						
					</table>
					</td>
				</tr>
				
				
				
				<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">											
						
						<tr>
						<td colspan="4"><strong>Business Purpose Of Expense  </strong>
						 </td>
						</tr>
											
						<tr>
							<td width="25%"><label id="business.purpose.of.expense"
								name="business.purpose.of.expense" ondblclick="callShowDiv(this);"><%=label.get("business.purpose.of.expense")%></label> :<font color="red">*</font>
							</td>	
							<td>
								<s:textarea name="businessPurpose"  cols="98" rows="3" onkeypress="return imposeMaxLength(event, this, 400);"/> 
								 
								 </td>						
						</tr>
						
					<tr>
							<td width="25%"><label id="template"
								name="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label> :<font color="red">*</font>
							</td>	
							<td> 
							<input type="button" class="token" value=" Download Expense Template" onclick="callDownload('Expense Form 3.xls')" />
							</td>						
						</tr>
						
						
					</table>
					</td>
				</tr>
				
				
			
				<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">						
											
						
						<tr>
						<td colspan="4"><strong>Expense Report </strong>
						 </td>
						</tr>
											
						<tr>
							<td width="25%"><label id="attach.expense.report"
								name="attach.expense.report" ondblclick="callShowDiv(this);"><%=label.get("attach.expense.report")%></label>
							:<font color="red">*</font></td>
							<td width="75%"><s:textfield name="uploadFileName" onclick="uploadFile('uploadFileName')"
								readonly="true" size="20" cssStyle="background-color: #F2F2F2;" />
								
						<input type="button" value="Upload File" class="token" onclick="uploadFile('uploadFileName')" />
								
						
								</td>
							
						</tr>
						
						<tr>
						<td> </td>
						<td><a href="#" onclick="viewUploadedFile();"><font color="blue"><u>click here to view expense report</u></font></a>		 </td>
						
						</tr>
						
						
						<tr>
							<td width="25%"><label id="total.expense.dollar.amount"
								name="total.expense.dollar.amount" ondblclick="callShowDiv(this);"><%=label.get("total.expense.dollar.amount")%></label>
							:<font color="red">*</font></td>
							<td width="25%"><s:textfield name="totalExpenseDollarAmt"
								 size="20" onkeypress="return numbersWithDot();" /></td>
							<td width="25%" align="left"></td>
							<td width="25%"></td>
						</tr>
						
						
					</table>
					</td>
				</tr>
		


			<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">						
											
						
						<tr>
						<td colspan="4">
						 </td>
						</tr>											
						
						
						<tr>
							<td width="25%"><label id="pre.approoved.policy.exception"
								name="pre.approoved.policy.exception" ondblclick="callShowDiv(this);"><%=label.get("pre.approoved.policy.exception")%></label>
							:</td>
							<td width="25%"><s:checkbox name="preApprovedPolicy" onclick="checkPolicy()"/></td>
							<td width="25%" align="left"></td>
							<td width="25%"></td>
						</tr>
						<tr id="PP">
							<td width="25%"><label id="comments"
								name="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
							:</td>
							<td width="25%" colspan="3"><s:textarea name="preApprovedPolicyComments"  cols="98" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/></td>
							<td width="25%" align="left"></td>
							<td width="25%"></td>
						</tr>
						
					</table>
					</td>
				</tr>	<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						<tr>
							<td colspan="4"><label class="set"
								name="header.mess" id="header.mess"
								ondblclick="callShowDiv(this);"><%=label.get("header.mess")%></label>
							</td>
						</tr>
					</table>
					</td>
		</tr>

<%!int count=0; %>	

<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b>Form Approval</b></td>
				</tr>
				
				

				<tr valign="top">
					<td colspan="1" width="25%">
					<label id="defoult"
								name="defoult" ondblclick="callShowDiv(this);"><%=label.get("defoult")%></label>
					 :</td>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%int ii=0; %>
					
							<s:iterator value="approverList">
								<tr>
									<td>
									<s:textfield name="apprSrNo" readonly="true" size="20" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/>
									
									</td>
									<td><s:hidden name="approverCode" />
									<s:textfield name="approverName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;"  cssStyle="border:none;"/>
									</td>

								</tr>
							<%ii++; %>
							<%count=ii; %>
							</s:iterator>

					</table>
					</td>

				</tr>


				<tr valign="top">
					<td colspan="1" >					
					<s:hidden name="checkData" id="checkData" />
					<label id="change.me.manager"
								name="change.me.manager" ondblclick="callShowDiv(this);"><%=label.get("change.me.manager")%></label>
					 :</td>
					<td  colspan="3"><s:textfield name="changeApproverToken"
						size="20"  theme="simple" readonly="true" />
					<s:textfield name="changeApproverName" size="70"
						 theme="simple" readonly="true" /> <s:hidden
						name="changeApproverCode"  /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide' 
						onclick="javascript:callsF9(500,325,'ExpenseApprovalReg_f9changeMang.action');">
					</td>

				</tr>







			</table>
			</td>
		</tr>

	
	<tr>
 <td colspan="4">
   <table width="100%" cellspacing ="1" cellpadding="1" class="formbg" border="0">
	
	
	  <tr>
			<td width="25%" colspan="1">
			<strong><label class="set" name="completed.by" id="completed.by" ondblclick="callShowDiv(this);">
				<%=label.get("completed.by")%></label></strong>
			 :</td>
			<td width="20%" colspan="1"><s:property value="completedBy"/>
			<s:hidden name="completedBy"/> 
			<s:hidden name="completedOn"/> 
			</td>
			


			<td width="20%"  colspan="1">
			<strong><label class="completed.on" name="completed.on" id="sin" ondblclick="callShowDiv(this);">
				<%=label.get("completed.on")%></label></strong> :</td>
			<td width="25%"  colspan="1"><s:property value="completedOn"/></td>
				
 </tr>
	
	
</table>
</td>
</tr>
	
		
	
	<tr>
		<td><jsp:include
			page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>

	</table>




</s:form>


<script type="text/javascript">	

		function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
		
		
		checkPolicy();
		
		function checkPolicy(){
			document.getElementById('paraFrm_preApprovedPolicyComments').disabled=true;
			if(document.getElementById('paraFrm_preApprovedPolicy').checked==true){			
			document.getElementById('paraFrm_preApprovedPolicyComments').disabled=false;
			}
			else{
			document.getElementById('paraFrm_preApprovedPolicyComments').value='';
			}		
		}
		
		
		function validate(){
			if(document.getElementById('paraFrm_employeeCode').value==''){
				alert('Please select '+document.getElementById('pemployee.name').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_businessPurpose').value==''){
				alert('Please enter '+document.getElementById('business.purpose.of.expense').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_uploadFileName').value==''){
				alert('Please select '+document.getElementById('attach.expense.report').innerHTML.toLowerCase());
				return false;
			}
			var extension = (document.getElementById('paraFrm_uploadFileName').value.replace('.', '@').split('@')[1]).toUpperCase();
			
			
			
			if(document.getElementById('paraFrm_totalExpenseDollarAmt').value==''){
				alert('Please enter '+document.getElementById('total.expense.dollar.amount').innerHTML.toLowerCase());
				return false;
			}
			if(isNaN(document.getElementById('paraFrm_totalExpenseDollarAmt').value)){
			alert('Please enter total expense dollar amount as a number');
			document.getElementById('paraFrm_totalExpenseDollarAmt').focus();
			return false;			
			}
			
		var cc='<%=count%>';
		if(document.getElementById('checkData').value=='0'){
			if(document.getElementById('paraFrm_changeApproverCode').value==''){		
			alert('Please select change my manager');
			return false;
			}
		}
		
		return true;
		}
	


			function draftFun() {
				if(!validate()){
				return false;
			}
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'ExpenseApprovalReg_draft.action';
				document.getElementById('paraFrm').submit();
			}
		function sendforapprovalFun() {
				if(!validate()){
				return false;
			}
				var vv=confirm("Do you really want to send for approval this application?");
			if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'ExpenseApprovalReg_sendForApproval.action';
				document.getElementById('paraFrm').submit();
				}else{
				return false;
				}
			}	
			
	
	function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ExpenseApprovalReg_back.action';
		document.getElementById('paraFrm').submit();
	}
	function printFun() {	
	window.print();
	}
	function resetFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ExpenseApprovalReg_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	
		function deleteFun() {	
			var vv=confirm("Do you really want to delete this application?");
			if(vv){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = 'ExpenseApprovalReg_delete.action';
				document.getElementById('paraFrm').submit();
				}else{
				return false;
				}
			}


	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'ExpenseApprovalReg_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
		
	
	function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	
	function callUpload(method) {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
			alert('Please select a file to upload.');
		} else {
			var extension = (uploadFileName.replace('.', '@').split('@')[1]).toUpperCase();

			if(extension != 'XLS'||extension != 'XLSX') {
				alert('Please select Excel file.');
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = "EmpDetailsUpload_" + method + ".action";
				document.getElementById('paraFrm').submit();
			}
		}
	}	
		
		function viewUploadedFile() {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'ExpenseApprovalReg_viewUploadedFile.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
		
		</script>



<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>