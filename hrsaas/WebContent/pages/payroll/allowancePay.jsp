
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="AllowancePay" method="post" id="paraFrm" theme="simple">



	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Allowance
					Pay </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>

			<td width="100%" colspan="3"><input type="button" class="token"
				value="    Process" onclick="return validate(this);" theme="simple" />

			<s:submit name="" value="Reset" action="AllowancePay_reset"
				cssClass="reset"></s:submit></td>

		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">


				<tr>
					<td colspan="1" width="20%"><label name="month" id="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%></label> <font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:select name="month"
						headerKey="0" headerValue="--Select--" cssStyle="width:150"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />

					</td>
					<td colspan="1" width="20%"><label name="year" id="year"
						ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font
						color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="25" name="year"
						maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>


				<tr>

					<td colspan="1" width="20%"><label name="credit.name"
						id="credit.name" ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:hidden name="creditCode"
						value="%{creditCode}" theme="simple" /> <s:textfield
						name="creditName" theme="simple" size="25" readonly="true" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AllowancePay_f9credit.action');">

					</td>

					<td colspan="1" width="20%" nowrap="nowrap"><label
						name="credit.amount" id="credit.amount"
						ondblclick="callShowDiv(this);"><%=label.get("credit.amount")%></label>
					<font color="red">*</font> :</td>

					<td colspan="1" width="30%"><s:textfield name="creditAmount"
						size="10"  cssStyle="text-align: right"
						onkeypress="return numbersWithDot();" />
				</tr>


				<tr>
					<td colspan="1" width="20%"><label name="processDate"
						id="processDate" ondblclick="callShowDiv(this);"><%=label.get("processDate")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield name="processingDate"
						value="%{processingDate}" theme="simple" size="25" readonly="true" /></td>
					</td>


					<td colspan="1" width="20%" nowrap="nowrap"></td>

					<td colspan="1" width="30%"></td>

				</tr>


				<tr>
					<td colspan="1" width="20%"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
						color="red">*</font>
					:</td>
					<td colspan="3" width="30%"><s:hidden name="employeeCode"
						value="%{employeeCode}" /><s:textfield readonly="true" size="15"
						name="employeeToken" /> <s:textfield size="50"
						name="employeeName" label="%{getText('employeeName')}"
						readonly="true" /> <img class="iconImage" id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callEmployee();" /></td>
					</td>

				</tr>


				<tr>
					<td colspan="1" width="20%"><label name="remarks" id="remarks"
						ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label><font
						color="red">*</font>
					:</td>
					<td colspan="3" width="30%"><s:textarea theme="simple"
						cols="70" rows="3" name="remarks" /></textarea></td>
					</td>

				</tr>


				<tr>

					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="center"><input type="button"
								class="save" theme="simple" value="Add To Llist"
								onclick=" return callValidate(this);" /></td>

						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

		<s:if test="showFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">

							<tr>
								<td width="5%" class="formth" nowrap="nowrap">Sr No.</td>
								<td width="10%" class="formth" nowrap="nowrap">Employee Id</td>
								<td width="25%" class="formth" nowrap="nowrap">Employee
								Name</td>
								<td width="10%" class="formth" nowrap="nowrap">Credit Name</td>
								<td width="10%" class="formth" nowrap="nowrap">Credit
								Amount</td>
								<td width="30%" class="formth" nowrap="nowrap">Remarks</td>
								<td align="right" width="10%" class="formth" nowrap="nowrap">
								Remove</td>

							</tr>

							<%
							int i = 0;
							%>

							<%!int t = 0;%>



							<s:iterator value="list">

								<tr class="sortableTD">
									<td width="5%" class="sortableTD" nowrap="nowrap"><s:hidden
										name="allowanceCode"></s:hidden> 
										
										<s:hidden
										name="monthItt" />
										
										<s:hidden
										name="yearItt" />
										
										<%=i + 1%> <input
										type="hidden" name="srNo" value="<%=i + 1%>" /></td>
									<td width="10%" class="sortableTD" nowrap="nowrap"><s:property
										value="empToken" /><s:hidden name="empToken" /> <s:hidden
										name="employeeId" /></td>
									<td width="25%" class="sortableTD" nowrap="nowrap"><s:property
										value="empName" /><s:hidden name="empName" /></td>
									<td width="10%" class="sortableTD" nowrap="nowrap"><s:property
										value="creditNameItt" /><s:hidden name="creditCodeItt" /> <s:hidden
										name="creditNameItt" /></td>
									<td width="10%" align="center" class="sortableTD"
										nowrap="nowrap"><s:property value="creditAmountItt" /> <input
										type="hidden" onkeypress="return numbersWithDot();"
										name="creditAmountItt"
										value='<s:property value="creditAmountItt"/>'
										id="creditAmountItt<%=i%>" theme="simple" readonly="true"
										style="background-color: #F2F2F2; text-align: right;"
										size="10" /></td>
									<td width="30%" align="left" class="sortableTD"
										nowrap="nowrap"><s:property value="remarksItt" /> <input
										type="hidden" name="remarksItt" id="remarksItt<%=i %>"
										value='<s:property value="remarksItt"/>' theme="simple" /></td>

									<td width="10%" align="center" class="sortableTD"><input
										type="button" class="rowDelete"
										onclick="callForDelete('<s:property value="allowanceCode"/>','<s:property value="monthItt"/>','<s:property value="yearItt"/>')" /></td>

								</tr>
								<%
								i++;
								%>

							</s:iterator>

							<%
							t = i;
							%>

							<%
							if (i == 0) {
							%>
							<tr>
								<td colspan="6" align="center"><font color="red"> No
								data to display</font></td>
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
		</s:if>


		<tr>

			<td width="100%" colspan="3"><input type="button" class="token"
				value="    Process" onclick="return validate(this);" theme="simple" />

			<s:submit name="" value="Reset" action="AllowancePay_reset"
				cssClass="reset"></s:submit>
				
	
				
				
				</td>

		</tr>
	</table>



			<s:hidden name="hiddenEdit" />
	<s:hidden name="hiddenMonthEdit" />
	<s:hidden  name="hiddenYearEdit" />
	

</s:form>


<script>


	function validate(obj)
	{
	 var month = document.getElementById('paraFrm_month').value;
	 var year = document.getElementById('paraFrm_year').value;
	 
	 if(month==0)
	 {
	 	alert("Please select month");
	 	return false;
	 }
	 
	 if(year=="")
	 {
	 	alert("Please enter year");
	 	return false;
	 }
	 
	 
	 
	 				obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="AllowancePay_process.action";
		  			document.getElementById("paraFrm").submit();
	 
	 
	 
	 return true;
	
	}
	

	function callEmployee(){
	 
		callsF9(500,325,'AllowancePay_f9employee.action');
	}
	
	
	function callForDelete(id,month,year)
	   {
	 	 
	 	 // alert(id);
	 	  
	 	  var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			document.getElementById('paraFrm_hiddenEdit').value=id;
				document.getElementById('paraFrm_hiddenMonthEdit').value=month;
					document.getElementById('paraFrm_hiddenYearEdit').value=year;
   		document.getElementById("paraFrm").action="AllowancePay_deleteData.action";
		document.getElementById("paraFrm").submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
	 	 
	   
   		}
   		
	

 function callValidate(obj)
  {
   
  				try{
  				
  				 var month = document.getElementById('paraFrm_month').value;
				  var year = document.getElementById('paraFrm_year').value;
				  var creditName = document.getElementById('paraFrm_creditCode').value;
				  var creditAmount = document.getElementById('paraFrm_creditAmount').value;
				  var employee = document.getElementById('paraFrm_employeeCode').value;
				    var remark = document.getElementById('paraFrm_remarks').value;
					 
					 if(month==0)
					 {
					 	alert("Please select month");
					 	return false;
					 }
					 
					 if(year=="")
					 {
					 	alert("Please enter year");
					 	return false;
					 }
  				
  				
  				 if(creditName=="")
					 {
					 	alert("Please select credit name");
					 	return false;
					 }
					 
					 
					  if(creditAmount=="")
					 {
					 	alert("Please enter credit amount");
					 	return false;
					 }
					 
					 
					 	  if(employee=="")
					 {
					 	alert("Please select employee");
					 	return false;
					 }
					 
					 
					 
					   if(remark=="")
					 {
					 	alert("Please enter remarks  ");
					 	return false;
					 }
  			 
  		  		 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="AllowancePay_addToList.action";
		  			document.getElementById("paraFrm").submit();
		  		 
				 
  				}
  		 catch(e){alert(e);}	
  		return true; 		
  }
  

</script>


