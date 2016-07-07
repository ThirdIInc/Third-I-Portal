
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelExpDisbur" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<s:hidden name="navStatus" />
		<s:hidden name="expDisbId" />
		<s:hidden name="expAppId" />


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Expense
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
					<td width="78%" align="left"><s:submit cssClass="save"
						action="TravelExpDisbur_save" value="Save "
						onclick=" return add();" theme="simple" /> <s:submit
						cssClass="save" value="Back " theme="simple"
						onclick=" callBack();" cssClass="token" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">


				<tr>
					<td><strong><label class="set"
						id="tms.trvlExpDisbrEmpInf" name="tms.trvlExpDisbrEmpInf"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEmpInf")%></label></strong></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
					<s:label name="empToken" theme="simple" value="%{empToken}" />&nbsp;&nbsp;
					<s:label name="employeeName" theme="simple" value="%{employeeName}" /></td>

				</tr>


				<tr>
					<td width="10%" class="formtext" height="22"><label
						class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="25%"><s:label name="brnchName" theme="simple"
						value="%{brnchName}" /></td>

					<td width="10%" class="formtext"><label class="set"
						id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="25%"><s:label name="deptName" theme="simple"
						value="%{deptName}" /></td>

				</tr>


				<tr>

					<td width="10%" class="formtext" height="22"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="25%"><s:label name="desgName" theme="simple"
						value="%{desgName}" /></td>
					<td width="10%" class="formtext"><label class="set"
						id="tms.trvlExpDisbrTrvlAppDate"
						name="tms.trvlExpDisbrTrvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlAppDate")%></label>:</td>
					<td width="25%">
					<s:hidden name="applDate"/>
					<s:label name="applDate1" theme="simple"
						value="%{applDate}" /></td>



				</tr>


				<tr>

					<td width="10%" class="formtext" height="22"><label
						class="set" id="tms.trvlExpDisbrStatus"
						name="tms.trvlExpDisbrStatus" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrStatus")%></label>:</td>
					<td width="25%"><s:label name="statusFld" theme="simple"
						value="%{statusFld}" /></td>
					<td width="10%" class="formtext"><label class="set" id="grade"
						name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
					<td width="25%"><s:label name="grdName" theme="simple"
						value="%{grdName}" /></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong><label
						class="set" id="tms.trvlExpDisbrPayDtls"
						name="tms.trvlExpDisbrPayDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPayDtls")%></label></strong></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrApprExpAmt" name="tms.trvlExpDisbrApprExpAmt"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrApprExpAmt")%></label>:</td>
					<td width="75%" colspan="3">
					
					<s:label name="ApprExpAmt1"
						theme="simple" value="%{ApprExpAmt}" />
						
						
					
					<s:hidden name="ApprExpAmt"
						value="%{ApprExpAmt}" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrLessAdvAmt" name="tms.trvlExpDisbrLessAdvAmt"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAdvAmt")%></label>:</td>
					<td width="75%" colspan="3">
					
					<s:label name="lessAdvAmt1"
						theme="simple" value="%{lessAdvAmt}" />
						
					
					<s:hidden name="lessAdvAmt"
						value="%{lessAdvAmt}" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrLessAnyOthrDed"
						name="tms.trvlExpDisbrLessAnyOthrDed"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAnyOthrDed")%></label>:</td>
					<td width="75%" colspan="3"><s:textfield name="lessAnyAmt"
						value="%{lessAnyAmt}" size="12" theme="simple"
						onkeyup="calculate();" onkeypress="return numbersOnly();" maxlength="8" /></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrTotDisbrAmt"
						name="tms.trvlExpDisbrTotDisbrAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTotDisbrAmt")%></label>:</td>
					<td width="75%" colspan="3">
					
					
					
					<s:textfield name="totDisbAmt"
						value="%{totDisbAmt}" size="12" theme="simple" readonly="true"
						onkeypress="return numbersOnly();" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrEntrDisbrAmt"
						name="tms.trvlExpDisbrEntrDisbrAmt"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEntrDisbrAmt")%></label><font
						color="red"> *</font>:</td>
					<td width="75%" colspan="3"><s:textfield name="entDisbAmt"
						value="%{entDisbAmt}" size="12" theme="simple"
						onkeypress="return numbersOnly();" onkeyup="calculate1();" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrBalAmt" name="tms.trvlExpDisbrBalAmt"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrBalAmt")%></label>:</td>
					<td width="75%" colspan="3"><s:textfield name="balAmt"
						value="%{balAmt}" size="12" theme="simple" readonly="true"
						onkeypress="return numbersOnly();" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrPrefModePay"
						name="tms.trvlExpDisbrPrefModePay" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPrefModePay")%></label>:</td>
					<td width="75%" colspan="3"><s:label name="prefModPay"
						theme="simple" value="%{prefModPay}" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrPayDate" name="tms.trvlExpDisbrPayDate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPayDate")%></label>
					<font color="red"> *</font>:</td>
					<td width="75%" colspan="3"><s:textfield name="payDate"
						size="12" theme="simple" onkeypress="return numbersWithHiphen();"
						onblur="return validateDate('paraFrm_payDate','tms.trvlExpDisbrPayDate');"
						maxlength="10" /> 
						
						
						<s:a
						href="javascript:NewCal('paraFrm_payDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="18" height="18" border="0" align="absmiddle" />
					</s:a></td>
				</tr>



				<tr>
					<td width="25%" colspan="1"><label class="set"
						id="tms.trvlExpDisbrModPay" name="tms.trvlExpDisbrModPay"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrModPay")%></label><font
						color="red"> *</font>:</td>
					<td width="75%" colspan="3"><s:select theme="simple"
						name="modPayment"
						list="#{'N':'-- Select --','C':'Cash','S':'Salary','Q':'Check','T':'Transfer'}"
						id="chkFlag" onchange="checkMode()"></s:select></td>
					</td>
				</tr>




				<!-- kriiii -->

				<tr id="checkId">
					<td colspan="4">
					<table width="100%" cellpadding="1" cellspacing="1">
						<tr>

							<td width="25%" colspan="1"><label class="set" id="bank"
								name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>
							<font color="red"> *</font>:</td>
							<td width="75%" colspan="3"><s:select theme="simple"
								name="bank"
								list="#{'':'-- Select --','S':'SBI','H':'HDFC','I':'ICICI'}">
							</s:select></td>
							</td>
						</tr>

						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.trvlExpDisbrEntrChkNo" name="tms.trvlExpDisbrEntrChkNo"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEntrChkNo")%></label>.<font
								color="red"> *</font>:</td>
							<td width="75%" colspan="3"><s:textfield name="checkNo"
								size="20" theme="simple" /></td>
						</tr>


						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.trvlExpDisbrChkDate" name="tms.trvlExpDisbrChkDate"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrChkDate")%></label>
							<font color="red"> *</font>:</td>
							<td width="75%" colspan="3"><s:textfield name="checkDate"
								size="12" theme="simple"
								onkeypress="return numbersWithHiphen();"
								onblur="return validateDate('paraFrm_checkDate','tms.trvlExpDisbrChkDate');"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_checkDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" />
							</s:a></td>

						</tr>

					</table>
					</td>
				</tr>


				<!-- kriiii -->





				<!-- kriiii -->



				<tr id="checkId2">
					<td colspan="4" width="100%">
					<table width="100%" align="center" cellpadding="1" cellspacing="1">

						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.trvlExpDisbrMon" name="tms.trvlExpDisbrMon"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrMon")%></label>
							<font color="red"> *</font>:</td>
							<td width="75%" colspan="3"><s:select theme="simple"
								name="expMon"
								list="#{'':'-- Select --','1':'JAN','2':'FEB','3':'MAR'}">
							</s:select></td>
						</tr>


						<tr>
							<td width="25%" colspan="1"><label class="set"
								id="tms.trvlExpDisbrYr" name="tms.trvlExpDisbrYr"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrYr")%></label>
							<font color="red"> *</font>:</td>
							<td width="75%" colspan="3"><s:textfield name="expYr"
								size="10" theme="simple" maxlength="4" /></td>
						</tr>






					</table>
					</td>
				</tr>


				<!-- kriiii -->

				<tr>
					<td width="25%" colspan="1" class="formtext" height="22"><label
						class="set" id="tms.trvlExpDisbrCmts" name="tms.trvlExpDisbrCmts"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrCmts")%></label>:</td>
					<td width="75%" colspan="3"><s:textarea name="comment"
						theme="simple" cols="100" rows="3" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="4"><s:submit cssClass="save"
				action="TravelExpDisbur_save" value="Save " onclick=" return add();"
				theme="simple" /> <s:submit cssClass="save" value="Back "
				theme="simple" onclick=" callBack();" cssClass="token" /></td>

		</tr>



	</table>
</s:form>


<script type="text/javascript">


checkMode();

function checkMode()
{

 
	//document.getElementById('checkId').style.display = '';
	var chkVal=document.getElementById('chkFlag').value;
	if(chkVal== 'Q')
	{
	document.getElementById('checkId').style.display = '';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_expMon').value='';
	document.getElementById('paraFrm_expYr').value='';
	
	}
	else if(chkVal == 'S')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = '';
	
	document.getElementById('paraFrm_bank').value='';
	document.getElementById('paraFrm_checkNo').value='';
	document.getElementById('paraFrm_checkDate').value='';
	}
	else if(chkVal == 'C')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_expMon').value='';
	document.getElementById('paraFrm_expYr').value='';	
	document.getElementById('paraFrm_bank').value='';
	document.getElementById('paraFrm_checkNo').value='';
	document.getElementById('paraFrm_checkDate').value='';
	
	}
	else if(chkVal == 'S')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_expMon').value='';
	document.getElementById('paraFrm_expYr').value='';	
	document.getElementById('paraFrm_bank').value='';
	document.getElementById('paraFrm_checkNo').value='';
	document.getElementById('paraFrm_checkDate').value='';
	
	}
	else if(chkVal == 'T')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_expMon').value='';
	document.getElementById('paraFrm_expYr').value='';	
	document.getElementById('paraFrm_bank').value='';
	document.getElementById('paraFrm_checkNo').value='';
	document.getElementById('paraFrm_checkDate').value='';
	
	}
	else
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_expMon').value='';
	document.getElementById('paraFrm_expYr').value='';	
	document.getElementById('paraFrm_bank').value='';
	document.getElementById('paraFrm_checkNo').value='';
	document.getElementById('paraFrm_checkDate').value='';
	}
 
} 
 
</script>


<script type="text/javascript">


	function callBack()
	{    
	   var staVar = document.getElementById('paraFrm_navStatus').value;
	   document.getElementById('paraFrm').action = "TravelExpDisbur_callStatus.action?status=P";  
		document.getElementById('paraFrm').submit();  
	 }

</script>

<script type="text/javascript">


function calculate()
{
var appExpAmt=document.getElementById('paraFrm_ApprExpAmt').value;
		var lsAdvAmt=document.getElementById('paraFrm_lessAdvAmt').value;
		var anyOtrDed=Math.round(document.getElementById('paraFrm_lessAnyAmt').value);	
		
		var totalDisbur=document.getElementById('paraFrm_totDisbAmt').value;			
		if(anyOtrDed=="")
		{
		anyOtrDed=0;
		}
		
		totalDisbur=eval(appExpAmt)-(eval(lsAdvAmt)+eval(anyOtrDed))		
		
		document.getElementById('paraFrm_totDisbAmt').value=eval(totalDisbur);
calculate1();	

}

function calculate1()
{

		var totalDisbur=document.getElementById('paraFrm_totDisbAmt').value;			
		var enteredAmt=Math.abs(document.getElementById('paraFrm_entDisbAmt').value);		
		if(enteredAmt=="")
		{
		enteredAmt=0;
		}

		document.getElementById('paraFrm_balAmt').value=(eval(totalDisbur)-eval(enteredAmt))	;	
}

calcOnLoad();

function calcOnLoad()
{
	var appExpAmt=document.getElementById('paraFrm_ApprExpAmt').value;
	var lsAdvAmt=document.getElementById('paraFrm_lessAdvAmt').value;
   document.getElementById('paraFrm_totDisbAmt').value=(eval(appExpAmt)-eval(lsAdvAmt))	;	
}

</script>



<script>

function add()
{
var entAmt=document.getElementById('paraFrm_entDisbAmt').value;		
var payDt=document.getElementById('paraFrm_payDate').value;		
var chkVal=document.getElementById('chkFlag').value;
//chkflag-----salary===== Month,Year -------check====bank,check number and check date
var month=document.getElementById('paraFrm_expMon').value;		
var year=document.getElementById('paraFrm_expYr').value;		
var bank=document.getElementById('paraFrm_bank').value;		
var checkNo=document.getElementById('paraFrm_checkNo').value;	
var chkDate=document.getElementById('paraFrm_checkDate').value;	

var frmDate=document.getElementById('paraFrm_applDate').value;
var TopayDate=document.getElementById('paraFrm_payDate').value;

   if(entAmt=="")
   {
   alert("Please Enter disbursement Amount");
  return false;
   }	

   else if(payDt=="")
   {
   alert("Please Select Paymemt Date");
   return false;
   }	
     
   
   if(!dateDifference(frmDate,TopayDate, "paraFrm_payDate",'tms.trvlExpDisbrTrvlAppDate','tms.trvlExpDisbrPayDate'))
		 {
		  return false;
		 }  
    
    
    
    else if(!(chkVal=='N'))
      {
               if(chkVal=='2')
               {
                  if(month=="")
                  {
                  alert("Plese Select month");
                  return false;
                  }
                  else if(year=="")
                  {
                   alert("Plese Enter Yer");
                   return false;
                  
                  }
                   
               }
               
               else if(chkVal=='3')
               
               {
                    if(bank=="")
                    {                  
                     alert("Plese Select Bank Name");
                     return false;
                    }
                   else if(checkNo=="")
                    {                  
                     alert("Plese Enter check Number");
                     return false;
                    }
                     else if(chkDate=="")
                    {                  
                     alert("Plese Select check Date");
                     return false;
                    }
       }	
   
   }
   
else if(chkVal=='N')
{

alert("Please Select Mode of Payment");
return false;
}

else
{
return true;

}
}
</script>
<script>

callOnload();
function callOnload()
{
document.getElementById('paraFrm_lessAnyAmt').focus();

}

</script>

