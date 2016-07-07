<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="PayslipMsg" id="paraFrm" theme="simple">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Payslip Message</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><!-- <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PayslipMsg_save" onclick="return call()"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PayslipMsg_save" onclick="return call()"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PayslipMsg_f9msgAction.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PayslipMsg_reset"
					theme="simple" value="    Reset"  />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="    Delete" action="PayslipMsg_delete"  onclick="return deleteValidation()"  />
	 </s:if>
	 --> <s:submit cssClass="add" action="PayslipMsg_save"
						onclick="return call('addnew')" value="  Add New" /> <s:submit
						cssClass="edit" action="PayslipMsg_save" onclick="return call('update')"
						value="   Update" /> <input type="button" class="search"
						value="    Search"
						onclick="javascript:callsF9(500,325,'PayslipMsg_f9msgAction.action');" />

					<s:submit cssClass="reset" action="PayslipMsg_reset" theme="simple"
						value="    Reset"  /> <s:submit cssClass="delete"
						theme="simple" value="    Delete" action="PayslipMsg_delete"
						onclick="return deleteValidation()"  /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
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
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Payslip Message</strong></td>
						</tr>
						<tr>
							<td class="formth" align="center">Please select to whom you want to send a
							message
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class="formth"></td>

						</tr>
						<tr>
							<td width="12%" nowrap>For All Employees :&nbsp; <input
								type="checkbox" name="allEmp" value="allEmp" id="allEmp"
								onclick="callChk()" /></td>
							<td width="10%" nowrap>For Single Employee :&nbsp; <input
								type="checkbox" name="singleEmp" value="singleEmp"
								id="singleEmp" onclick="callChk1()" /></td>
						</tr>
						<!-- Changed the level Division in place of Payroll by Abhijit -->
						<tr>
							<td width="12%" nowrap>For All Employees in a Division :
							&nbsp; <input type="checkbox" name="allEmpPaybill"
								value="allEmpPaybill" id="allEmpPaybill" onclick="callChk2()" /></td>
						</tr>
						<!-- Ended by Abhijit -->
						<tr>
							<td colspan="3"><img src="../pages/images/space.gif"
								width="5" height="4" /></td>
						</tr>
						<tr>
							<td colspan="3"><img src="../pages/images/space.gif"
								width="5" height="4" /></td>
						</tr>

						<tr>
							<table width="100%" border="0" align="right" cellpadding="0"
								cellspacing="0">
								<tr>
									<td colspan="2">
									<div id="allEmpDiv">
									<table width="100%">

										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>Month <font color="red">*</font></td>
											<td><s:select name="month" headerKey="-1"
												headerValue="Select"
												list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October',
                       			  '11':'November','12':'December'}" />
											</td>
											<td colspan="1">Year<font color="red">*</font></td>
											<td colspan="1"><s:textfield label="%{getText('year')}"
												maxLength="4" theme="simple" name="year"
												onkeypress="return numbersonly(this);" /></td>
											<td colspan="1">&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									</div>
									</td>
								</tr>
							</table>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" align="right" cellpadding="0"
								cellspacing="0">
								<tr>
									<td colspan="2">
									<div id="singleEmpDiv">
									<table width="100%">

										<tr>
											<td class="formth">Select Employee if you want to send
											individual Message</td>
										</tr>
										<!-- Changed Level Employee in place of Payroll -->
										<tr>
											<td>Employee :<s:hidden name="empID" /> <s:textfield
												name="empTokenNo" size="20" readonly="true" /> <s:textfield
												name="empName" size="40" readonly="true" /><img
												src="../pages/images/recruitment/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'PayslipMsg_f9empaction.action');"></td>
										</tr>
										<!-- Ended by Abhijit -->
										<tr>
											<td>&nbsp;</td>
										</tr>

									</table>
									</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
<tr>
<td>
<table width="100%" border="0" align="right" cellpadding="0"
							cellspacing="0">
							<tr>
								<td colspan="2">
								<div id="allEmpPaybillDiv">
								<table width="100%">
									<tr>
										<!--<td class="formth" width="100%">Select Pay Bill if you
										want to send Message for all the Employees in the Pay
										Bill</td>

									-->
									<!-- changed Division in place of Payroll by Abhijit -->
									<td class="formth" width="100%">Select Division Name if you
										want to send Message for all the Employees in the Divion</td>
									</tr>
									<!-- Ended by Abhijit -->
									<!-- <tr>
										<td>Select Pay Bill Name :&nbsp;<s:textfield size="25"
											name="payBillName" size="25" readonly="true" /><s:hidden
											name="payBillNo" /><img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'PayslipMsg_f9payBill.action');"></td>

									</tr>  -->
									<!-- Added divisionId and divisionName fields by Abhijit -->
									<tr>
										<td>Division :&nbsp;<s:textfield size="25"
											name="divisionName" size="25" readonly="true" />
											<s:hidden name="divisionId" />
											<s:hidden name="payBillNo" /><img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'PayslipMsg_f9payBill.action');"></td>

									</tr>
									<!-- Ended by Abhijit -->
									<tr>
										<td>&nbsp;</td>
									</tr>

								</table>
								</div>
								</td>
							</tr>
							<tr>
								<td class="formth" colspan="2">Please enter message you
								want to display</td>
								<td class="formth"></td>
							</tr>
							<tr>
								<td width="10%">Message<font color="red">*</font> :</td>
								<td><s:hidden name="msgID" /><s:textarea name="msgName"
									rows="4" cols="60"></s:textarea></td>
							</tr>

							<tr>
								<td colspan="3"><img src="../pages/images/space.gif"
									width="5" height="4" /></td>
							</tr>
						</table>

</td>
</tr>

						
						</tr>


					</table>

					</td>
				</tr>



			</table>

			</td>
		</tr>
	</table></td></tr></table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>



display();
function display()
{
		document.getElementById('allEmpDiv').style.display='';
		document.getElementById('singleEmpDiv').style.display='';
		document.getElementById('allEmpPaybillDiv').style.display='';
}

function callChk(){
	if(document.getElementById('singleEmp').checked)
		{
			document.getElementById('allEmpDiv').style.display='';
		}
	if(document.getElementById('allEmpPaybill').checked)
		{
			document.getElementById('allEmpDiv').style.display='';
		}
	else if(document.getElementById('allEmpDiv').style.display=='none')
		document.getElementById('allEmpDiv').style.display='';
	else
		document.getElementById('allEmpDiv').style.display='none';
}
function callChk1(){
	
	if(document.getElementById('singleEmpDiv').style.display=='none')
		document.getElementById('singleEmpDiv').style.display='';
	else
		document.getElementById('singleEmpDiv').style.display='none';
		
	if(document.getElementById('allEmpPaybill').checked){
		document.getElementById('allEmpDiv').style.display='';
	}	
	else if(document.getElementById('allEmpDiv').style.display=='none')
		document.getElementById('allEmpDiv').style.display='';
	else if(!(document.getElementById('allEmp').checked))
		document.getElementById('allEmpDiv').style.display='none';
	
}
function callChk2(){
	
	if(document.getElementById('allEmpPaybillDiv').style.display=='none')
		document.getElementById('allEmpPaybillDiv').style.display='';
	else
		document.getElementById('allEmpPaybillDiv').style.display='none';
		
	if(document.getElementById('singleEmp').checked){
		document.getElementById('allEmpDiv').style.display='';
	}
	else if(document.getElementById('allEmpDiv').style.display=='none')
		document.getElementById('allEmpDiv').style.display='';
	else if(!(document.getElementById('allEmp').checked))
		document.getElementById('allEmpDiv').style.display='none';
}

function call(type)
{
if(type == 'addnew')
		{
			if(!document.getElementById('paraFrm_msgID').value =="")
			{
				alert("Please Click on Update");
				return false;
			}
		}
		else
		{
			if(document.getElementById('paraFrm_msgID').value =="")
			{
				alert("Please Select the Record to Update");
				return false;
			}
		}
		
		
		var month = document.getElementById('paraFrm_month').value;
		var year = document.getElementById('paraFrm_year').value;
		var msgName = document.getElementById('paraFrm_msgName').value;
		if(month==-1)
		{
		alert("Please Select Month ");
		return false;
		}
		var fieldName = ["paraFrm_year", "paraFrm_msgName"];
		var lableName = ["Year", "Payslip Message"];
		var badflag = ["enter","enter"];
     if(!checkMandatory(fieldName,lableName,badflag)){
              return false;
        }
		
		
		
		
		
	/*	if(year=="")
		{
		alert("Please Enter Year ");
		return false;
		}
		if(msgName=="")
		{
		alert("Please Enter Message");
		return false;
		}*/

}

function deleteValidation()
			{
			var empname=document.getElementById('paraFrm_msgID').value;
			if(empname=="")
			{
			alert('Please Select Message ');
			return false;
			}
			else {
  			var conf=confirm("Are you sure to delete this record?");
  			if(conf) {
  				return true;
  			}
  			else
  			{
  				return false;
  			}
  			}
			return true;
			}

function callRepot()
{
  		       document.getElementById('paraFrm').target="_blank";
		   document.getElementById('paraFrm').action='Request_reqReport.action';	
			                                            
			   document.getElementById('paraFrm').submit();	
			   document.getElementById('paraFrm').target="main";
}



function callValid()
{
 

  		       document.getElementById('paraFrm').target="_blank";
			   document.getElementById('paraFrm').action='Request_report.action';	
			                                            
			   document.getElementById('paraFrm').submit();	
			   document.getElementById('paraFrm').target="main";
}

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}

    
</script>