
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelExpDisbur" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="balId" />
	<s:hidden name="navStatus" />
	<s:hidden name="expDisbId" />
	<s:hidden name="expAppId" />


	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Balance
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
					<td width="78%" align="left"> <s:submit cssClass="save"
				action="TravelExpDisbur_save" value="Save " onclick=" return add();"
				theme="simple" /> <s:submit cssClass="token" value="Back "
				theme="simple" onclick=" callBack();" /></td>
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
					<td><strong><label  class = "set"  id="tms.trvlExpDisbrEmpInf" name="tms.trvlExpDisbrEmpInf" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEmpInf")%></label></strong></td>
				</tr>

				<tr>
							<td width="20%" colspan="1" class="formtext" height="22"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
							<td width="60%" colspan="3"><s:label name="empToken"
								theme="simple" value="%{empToken}" />&nbsp;&nbsp; <s:label
								name="employeeName" theme="simple" value="%{employeeName}" /></td>

						</tr>


				<tr>
					<td width="10%" class="formtext" height="22"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="25%"><s:label name="brnchName" theme="simple"
						value="%{brnchName}" /></td>

					<td width="10%" class="formtext"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="25%"><s:label name="deptName" theme="simple"
						value="%{deptName}" /></td>

				</tr>


				<tr>

					<td width="10%" class="formtext" height="22"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="25%"><s:label name="desgName" theme="simple"
						value="%{desgName}" /></td>
					<td width="10%" class="formtext"><label  class = "set"  id="tms.trvlExpDisbrTrvlAppDate" name="tms.trvlExpDisbrTrvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTrvlAppDate")%></label>:</td>
					<td width="25%">
					<s:hidden name="applDate"/>
					<s:label name="applDate1" theme="simple"
						value="%{applDate}" /></td>



				</tr>


				<tr>

					<td width="10%" class="formtext" height="22"><label  class = "set"  id="tms.trvlExpDisbrStatus" name="tms.trvlExpDisbrStatus" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrStatus")%></label>:</td>
					<td width="25%"><s:label name="statusFld" theme="simple"
						value="%{statusFld}" /></td>
					<td width="10%" class="formtext"><label  class = "set"  id="grade" name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
					<td width="25%"><s:label name="grdName" theme="simple"
						value="%{grdName}" /></td>

				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">
				<td height="27" class="formhead"><strong><label  class = "set"  id="tms.trvlExpDisbrPayDtls" name="tms.trvlExpDisbrPayDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPayDtls")%></label></strong></td>


				
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrApprExpAmt" name="tms.trvlExpDisbrApprExpAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrApprExpAmt")%></label>:</td>
					<td width="75%" colspan="3">
					<s:label name="balApprAmt1"
						theme="simple" value="%{balApprAmt}" />
					
					<s:hidden name="balApprAmt" value="%{balApprAmt}"/></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrLessAdvAmt" name="tms.trvlExpDisbrLessAdvAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAdvAmt")%></label>:</td>
					<td width="75%" colspan="3">
					
					<s:label name="balLessAdvAmt1"
						theme="simple" value="%{balLessAdvAmt}" />
					
					<s:hidden name="balLessAdvAmt"
						value="%{balLessAdvAmt}"/></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrLessAnyOthrDed" name="tms.trvlExpDisbrLessAnyOthrDed" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrLessAnyOthrDed")%></label>:</td>
					<td width="75%" colspan="3">
					
					<s:label name="balLessAnyAmt1"
						theme="simple" value="%{balLessAnyAmt}" />
						
					<s:hidden name="balLessAnyAmt"
						value="%{balLessAnyAmt}" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrPaidAmt" name="tms.trvlExpDisbrPaidAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPaidAmt")%></label>:</td>
					<td width="75%" colspan="3">
					<s:label name="balPiadAmt1"
						theme="simple" value="%{balPiadAmt}" />
						
					<s:hidden name="balPiadAmt"
						value="%{balPiadAmt}" /></td>
				</tr>



				<!-- total disbursement means balance  -->

				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrTotDisbrAmt" name="tms.trvlExpDisbrTotDisbrAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrTotDisbrAmt")%></label>:</td>
					<td width="75%" colspan="3">
					<s:label name="balTotDisbAmt1"
						theme="simple" value="%{balTotDisbAmt}" />
					
					<s:hidden name="balTotDisbAmt"
						value="%{balTotDisbAmt}"  /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrEntrDisbrAmt" name="tms.trvlExpDisbrEntrDisbrAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEntrDisbrAmt")%></label><font
						color="red"> :*</font></td>
					<td width="75%" colspan="3"><s:textfield name="balEntDisbAmt"
						value="%{balEntDisbAmt}" size="12" theme="simple"
						onkeypress="return numbersOnly();" onkeyup="calculate1();"  maxlength="8"/></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrBalAmt" name="tms.trvlExpDisbrBalAmt" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrBalAmt")%></label>:</td>
					<td width="75%" colspan="3"><s:textfield name="balanceAmt"
						value="%{balanceAmt}" size="12" theme="simple"
						readonly="true"/></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrPrefModePay" name="tms.trvlExpDisbrPrefModePay" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPrefModePay")%></label>:</td>
					<td width="75%" colspan="3"><s:label name="balPrefModPay"
						theme="simple" value="%{balPrefModPay}" /></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrPayDate" name="tms.trvlExpDisbrPayDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrPayDate")%></label> <font color="red">
					:*</font></td>
					<td width="75%" colspan="3"><s:textfield name="balPayDate"
						size="20" theme="simple" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_balPayDate','tms.trvlExpDisbrPayDate');"
									maxlength="10"  /> <s:a
						href="javascript:NewCal('paraFrm_balPayDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="18" height="18" border="0" align="absmiddle" />
					</s:a></td>
				</tr>


				<tr>
					<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrModPay" name="tms.trvlExpDisbrModPay" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrModPay")%></label> <font color="red">
					:*</font></td>
					<td width="75%" colspan="3"><s:select theme="simple"
						name="balModPayment"
						list="#{'N':'-- Select --','C':'Cash','S':'Salary','Q':'Check','T':'Transfer'}"
						id="chkFlag" onchange="checkMode();"></s:select></td>
					</td>
				</tr>



				<!-- kriiii -->

				<tr id="checkId">
					<td colspan="4" width="100%">
					<table width="100%" align="center" cellpadding="1" cellspacing="1">
						<tr>

							<td width="25%" colspan="1"><label  class = "set"  id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> <font color="red">
							:*</font></td>
							<td width="75%" colspan="3"><s:select theme="simple"
								name="balBank"
								list="#{'':'-- Select --','S':'SBI','H':'HDFC','I':'ICICI'}">
							</s:select></td>
							</td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrEntrChkNo" name="tms.trvlExpDisbrEntrChkNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrEntrChkNo")%></label>.<font color="red">
							:*</font></td>
							<td width="75%" colspan="3"><s:textfield name="balCheckNo"
								size="20" theme="simple" /></td>
						</tr>


						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrChkDate" name="tms.trvlExpDisbrChkDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrChkDate")%></label><font color="red">
							:*</font></td>
							<td width="75%" colspan="3"><s:textfield name="balCheckDate"
								size="20" theme="simple" onkeypress="return numbersWithHiphen();"
									onblur="return validateDate('paraFrm_balCheckDate','tms.trvlExpDisbrChkDate');"
									maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_balCheckDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="18"
									height="18" border="0" align="absmiddle" />
							</s:a></td>

						</tr>

					</table>
					</td>
				</tr>


				<tr id="checkId2">
					<td colspan="4" width="100%">
					<table width="100%" align="center" cellpadding="1" cellspacing="1">



						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrMon" name="tms.trvlExpDisbrMon" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrMon")%></label><font color="red">
							:*</font></td>
							<td width="75%" colspan="3"><s:select theme="simple"
								name="balMon"
								list="#{'':'-- Select --','JA':'JAN','FE':'FEB','MA':'MAR'}">
							</s:select></td>
						</tr>


						<tr>
							<td width="25%" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrYr" name="tms.trvlExpDisbrYr" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrYr")%></label><font color="red"> :*</font></td>
							<td width="75%" colspan="3"><s:textfield
								name="balYr" size="10"  theme="simple"
								maxlength="4" /></td>
						</tr>

					</table>
					</td>
				</tr>

				<!-- kriiii -->

				<tr>
					<td width="25%" class="formtext" colspan="1"><label  class = "set"  id="tms.trvlExpDisbrCmts" name="tms.trvlExpDisbrCmts" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlExpDisbrCmts")%></label>:</td>
					<td width="75%" colspan="3"><s:textarea name="balComment"
						theme="simple" cols="100" rows="3" /></td>
				</tr>
			</table>

			</td>
		</tr>

		<tr>
			<td align="left" colspan="4"><s:submit cssClass="save"
				action="TravelExpDisbur_save" value="Save " onclick=" return add();"
				theme="simple" /> <s:submit cssClass="token" value="Back "
				theme="simple" onclick=" callBack();" /></td>

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
	document.getElementById('paraFrm_balMon').value='';
	document.getElementById('paraFrm_Year').value='';
	
	}
	else if(chkVal == 'S')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = '';
	
	document.getElementById('paraFrm_balBank').value='';
	document.getElementById('paraFrm_balCheckNo').value='';
	document.getElementById('paraFrm_balCheckDate').value='';
	}
	else if(chkVal == 'C')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_balMon').value='';
	document.getElementById('paraFrm_Year').value='';
	document.getElementById('paraFrm_balBank').value='';
	document.getElementById('paraFrm_balCheckNo').value='';
	document.getElementById('paraFrm_balCheckDate').value='';
	
	}
	else if(chkVal == 'S')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_balMon').value='';
	document.getElementById('paraFrm_Year').value='';
	document.getElementById('paraFrm_balBank').value='';
	document.getElementById('paraFrm_balCheckNo').value='';
	document.getElementById('paraFrm_balCheckDate').value='';
	}
	else if(chkVal == 'T')
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_balMon').value='';
	document.getElementById('paraFrm_Year').value='';
	document.getElementById('paraFrm_balBank').value='';
	document.getElementById('paraFrm_balCheckNo').value='';
	document.getElementById('paraFrm_balCheckDate').value='';
	
	}
	else
	{
	document.getElementById('checkId').style.display = 'none';
	document.getElementById('checkId2').style.display = 'none';
	document.getElementById('paraFrm_balMon').value='';
	document.getElementById('paraFrm_Year').value='';
	document.getElementById('paraFrm_balBank').value='';
	document.getElementById('paraFrm_balCheckNo').value='';
	document.getElementById('paraFrm_balCheckDate').value='';
	}
 
} 
 
</script>

<script type="text/javascript">


	function callBack()
	{    
	   var staVar = document.getElementById('paraFrm_navStatus').value;
	   
	   document.getElementById('paraFrm').action = "TravelExpDisbur_callStatus.action?status=B";  
		document.getElementById('paraFrm').submit();  
	 }
</script>
<script type="text/javascript">
<!--

//-->

function calculate()
{

		
		var appExpAmt=document.getElementById('paraFrm_balApprAmt').value;
		var lsAdvAmt=document.getElementById('paraFrm_balLessAdvAmt').value;
		var anyOtrDed=document.getElementById('paraFrm_balLessAnyAmt').value;	
		var totalDisbur=document.getElementById('paraFrm_balTotDisbAmt').value;			
		
		if(anyOtrDed=="")
		{
		anyOtrDed=0;
		}
		
		
		totalDisbur=eval(appExpAmt)-(eval(lsAdvAmt)+eval(anyOtrDed))		
		document.getElementById('paraFrm_balTotDisbAmt').value=totalDisbur;
		calculate1();
		
		
}

function calculate1()
{
		
		var totalDisbur=document.getElementById('paraFrm_balTotDisbAmt').value;			
		var enteredAmt=Math.abs(document.getElementById('paraFrm_balEntDisbAmt').value);		
		
		if(enteredAmt=="")
		{
		enteredAmt=0;
		}
		
		document.getElementById('paraFrm_balanceAmt').value=(eval(totalDisbur)-eval(enteredAmt))	;	
		
		
}



</script>



<script>

function add()
{
var entAmt=Math.abs(document.getElementById('paraFrm_balEntDisbAmt').value);		
var payDt=document.getElementById('paraFrm_balPayDate').value;		
var chkVal=document.getElementById('chkFlag').value;
//chkflag-----salary===== Month,Year -------check====bank,check number and check date
var month=document.getElementById('paraFrm_balMon').value;		
var year=document.getElementById('paraFrm_balYr').value;		
var bank=document.getElementById('paraFrm_balBank').value;		
var checkNo=document.getElementById('paraFrm_balCheckNo').value;	
var chkDate=document.getElementById('paraFrm_balCheckDate').value;	




var frmDate=document.getElementById('paraFrm_applDate').value;
var TopayDate=document.getElementById('paraFrm_balPayDate').value;

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
     
   
   if(!dateDifference(frmDate,TopayDate, "balPayDate",'tms.trvlExpDisbrTrvlAppDate','tms.trvlExpDisbrPayDate'))
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
document.getElementById('paraFrm_balEntDisbAmt').focus();

}

</script>






