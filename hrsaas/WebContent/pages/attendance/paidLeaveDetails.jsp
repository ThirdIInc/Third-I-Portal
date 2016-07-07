
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PaidLeaveDetails" method="post" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					Details </strong></td>
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
					<td width="78%"><input type="button" class="save"
						value="     Save " onclick="callSave();" /> <input type="button"
						class="token" value="     Close " onclick="callClose();" /></td>

				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">


						<tr>
							<td width="20%" colspan="1">Employee Name<span
								class="formtext"> </span><span class="style2"> :</span></td>
							<td width="80%" colspan="4"><s:property value="eName" /><s:hidden
								name="eName" /></td>

						</tr>

						<tr>
							<td width="20%" colspan="1">Month<span class="formtext">
							</span><span class="style2"> :</span></td>
							<s:hidden name="monthInt" />
							<td width="30%" colspan="1"><s:property value="month" /><s:hidden
								name="month"></s:hidden></td>
							<td width="20%" colspan="1">Year<span class="formtext">
							</span><span class="style2"> :</span></td>
							<td width="30%" colspan="2"><s:property value="year" /><s:hidden
								name="year"></s:hidden></td>
						</tr>

						<tr>
							<td width="20%" colspan="1">Late Marks<span class="formtext">
							</span><span class="style2"> :</span></td>
							<td width="30%" colspan="1"><s:textfield name="lateMarks1"
								value="%{lateMarks1}" onkeypress="return numbersOnly();"
								maxLength="2" size="10" cssStyle="text-align: right;"
								onfocus="setPreviousValues();" onblur="return setValue();" /></td>
							<td width="20%" colspan="1">Half Day<span class="formtext">
							</span><span class="style2"> :</span></td>
							<td width="30%" colspan="2"><s:textfield name="halfDay1"
								value="%{halfDay1}" onkeypress="return numbersOnly();"
								maxLength="2" size="10" cssStyle="text-align: right;"
								onfocus="setPreviousValues();" onblur="return setValue();" /> <s:submit
								cssClass="token" action="PaidLeaveDetails_recalculate"
								value="Recalculate" onclick="return recalculate();" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="showPaidFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="3" width="100%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="22" width="100%" class="formhead"><strong
									class="forminnerhead">&nbsp;&nbsp;Paid Leaves Details</strong></td>
							</tr>
							<tr>
								<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr>
										<td width="40%" valign="top" class="formth">Leave Type</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">Original
										Leave Balance</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">System
										Adjusted Leaves</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">System
										Adjusted Late Marks</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">
										System Adjusted Half Days</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">Manually
										Adjusted Leaves</td>
										<td width="5%" valign="top" class="formth" nowrap="nowrap">Current
										Leave Balance</td>
									</tr>
									<%
									int y = 1;
									%>
									<%!int z = 0;%>
									<s:iterator value="leaveList" status="stat">

										<tr>
											<s:hidden name="leaveCode" value="%{leaveCode}" />
											<td class="border2" width="40%"><s:property
												value="leaveName" /></td>
											<td class="border2" width="5%"><input type="text"
												id="originalBal<%=y %>" name="originalBal"
												value='<s:property value="originalBal"/>' theme="simple"
												readonly="true"
												style="background-color: #F2F2F2; text-align: right;"
												size="10" /></td>
											<td class="formth" width="5%"><input type="text"
												id="sysAdjustLeave<%=y %>"
												onkeypress="return numbersWithDot();" name="sysAdjustLeave"
												value='<s:property value="sysAdjustLeave"/>' theme="simple"
												readonly="true"
												style="background-color: #F2F2F2; text-align: right;"
												size="10" /></td>

											<td class="formth" width="5%"><input type="text"
												id="adjustLateMark<%=y %>" name="adjustLateMark"
												readonly="true"
												style="background-color: #F2F2F2; text-align: right;"
												value='<s:property value="adjustLateMark"/>' theme="simple"
												size="10" /></td>

											<td class="formth" width="5%"><input type="text"
												id="adjustHalfDays<%=y %>" name="adjustHalfDays"
												readonly="true"
												style="background-color: #F2F2F2; text-align: right;"
												value='<s:property value="adjustHalfDays"/>' theme="simple"
												size="10" /></td>

											<td class="formth" width="5%"><input type="text"
												id="manualAdjustLeave<%=y %>" name="manualAdjustLeave"
												value='<s:property value="manualAdjustLeave"/>'
												onkeypress="return numbersWithDot();" theme="simple"
												onkeyup="calculate('<%=y%>')" maxlength="5" size="10"
												style="text-align: right;" /></td>

											<td class="border2" width="5%"><input type="text"
												id="balance<%=y %>" name="balance" readonly="true"
												style="background-color: #F2F2F2; text-align: right;"
												value='<s:property value="balance"/>' theme="simple"
												size="10" /> <input type="hidden" id="hiddenBalance<%=y %>"
												name="hiddenBalance"
												value='<s:property value="hiddenBalance"/>' /></td>


										</tr>
										<%
										y++;
										%>
									</s:iterator>
									<%
									z = y;
									%>


									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>

										<td class="formth" width="5%" colspan="2" align="right">
										Total Paid Leaves</td>
										<td class="formth" width="5%" align="left"><s:textfield
											name="totalPaidLeave" readonly="true" size="10" maxlength="5"
											theme="simple"
											cssStyle="background-color: #F2F2F2; text-align: right;" /></td>
										<td class="formth">&nbsp;</td>
										<td class="border2">&nbsp;</td>
									</tr>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td class="formhead"><strong class="forminnerhead">&nbsp;UnPaid
							Leaves Details</strong></td>
						</tr>

						<tr>
							<td width="24%">System Adjusted Unpaid Leaves<span
								class="formtext"> </span><span class="style2"> :</span></td>
							<td height="22" colspan="4"><s:textfield
								name="sysAdjustUnpaid" value="%{sysAdjustUnpaid}"
								readonly="true"
								cssStyle="background-color: #F2F2F2; text-align: right;"
								size="10" /></td>
							<td width="24%">Manually Adjusted Unpaid Leaves<span
								class="formtext"> </span><span class="style2"> :</span></td>
							<td height="22" colspan="4"><s:textfield
								name="manualAdjustUnpaid" value="%{manualAdjustUnpaid}"
								onkeypress="return numbersWithDot();"
								onkeyup="calculateUnpaid()" maxlength="5"
								cssStyle="text-align: right;" size="10"
								onfocus="setPreviousValues();" onblur="return setValue();" /></td>
						</tr>
						<tr>
							<td width="10%" valign="top" class="formth">Total UnPaid
							Leaves</td>
							<td width="10%" valign="top" class="formth"><s:textfield
								name="totalUnPaidLeave" readonly="true" size="10" theme="simple"
								cssStyle="background-color: #F2F2F2; text-align: right;" /></td>
						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="save"
						value="     Save " onclick="return callSave();" /> <input
						type="button" class="token" value="     Close "
						onclick="return callClose();" /></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="attCode" value="%{attCode}" />
	<s:hidden name="hiddenEmpid" />
	<s:hidden name="paidLevs" />
	<s:hidden name="rowid" />
	<s:hidden name="unpaidLM" />
	<s:hidden name="unpaidHD" />
	<s:hidden name="unpaidLMHD" />
	<s:hidden name="unpaidSysAdjust" />
	<s:hidden name="showPaidFlag" />
	<s:hidden name="totAbsLD" />
	<s:hidden name="attConnFlagLD" />
	<s:hidden name="currLateValue" />
	<s:hidden name="currHalfValue" />
	<s:hidden name="currUnpaidValue" />
	<s:hidden name="fDate" />
	<s:hidden name="tDate" />
</s:form>

<script>


function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}


function setPreviousValues()
	{
	
		document.getElementById("paraFrm_currLateValue").value = document.getElementById('paraFrm_lateMarks1').value;
		document.getElementById("paraFrm_currHalfValue").value = document.getElementById('paraFrm_halfDay1').value;
		document.getElementById("paraFrm_currUnpaidValue").value = document.getElementById('paraFrm_manualAdjustUnpaid').value;
	}

function setValue() 
{
	if(document.getElementById('paraFrm_lateMarks1').value=="")
	{
	if(document.getElementById("paraFrm_currLateValue").value == "")
			{
				document.getElementById("paraFrm_currLateValue").value = "0";
			}
		else
		{
		document.getElementById('paraFrm_lateMarks1').value=document.getElementById('paraFrm_currLateValue').value;
		}
	}
	if(document.getElementById('paraFrm_halfDay1').value=="")
	{
	if(document.getElementById("paraFrm_currLateValue").value == "")
			{
				document.getElementById("paraFrm_currLateValue").value = "0";
			}
		else
		{
		document.getElementById('paraFrm_halfDay1').value=document.getElementById('paraFrm_currHalfValue').value;
		}
	}
	if(document.getElementById('paraFrm_manualAdjustUnpaid').value=="")
	{
	if(document.getElementById("paraFrm_currUnpaidValue").value == "")
			{
				document.getElementById("paraFrm_currUnpaidValue").value = "0";
			}
		else
		{
		document.getElementById('paraFrm_manualAdjustUnpaid').value=document.getElementById('paraFrm_currUnpaidValue').value;
		calculateUnpaid();
		}
	}
}

 
 onload();
 
function onload()
	{
	
	try
	{
			var flagValue =document.getElementById('paraFrm_showPaidFlag').value;
			var late = document.getElementById('paraFrm_lateMarks1').value;
			
			if(late!=0)
			{
			document.getElementById('paraFrm_lateMarks1').value=late;
			}
			else
			{
			late=0;
			}
			var half = document.getElementById('paraFrm_halfDay1').value;
			if(half!=0)
			{
			document.getElementById('paraFrm_halfDay1').value=half;
			}
			else
			{
			half=0;
			}
			if(flagValue=="true")
			{
				 var value='<%=z%>'; 
				  	 var total=0;
					 var total2=0;
				  for(var q=1;q<value;q++)
				  		{
					  	total = document.getElementById('sysAdjustLeave'+q).value;
					  	 total2=eval(total2)+eval(total);
						  }
					  
					  	 var total3=0;
					 var total4=0;
				  for(var q=1;q<value;q++){
					  	total3 = document.getElementById('adjustLateMark'+q).value;
					  	
					  total4=eval(total4)+eval(total3);
					  
					  }
					  
					  	 var total5=0;
					 var total6=0;
				  for(var q=1;q<value;q++){
					  	total5 = document.getElementById('adjustHalfDays'+q).value;
					  	
					  total6=eval(total6)+eval(total5);
					
					  }
					   	 var total7=0;
					 var total8=0;
				  for(var q=1;q<value;q++){
						  	
					  	total7 = document.getElementById('manualAdjustLeave'+q).value;
					  	
					  	if(total7=="")
					  	{
					  		total7=0;
					  	}
					  	 
					  total8=eval(total8)+eval(total7);
					  	
					  }
					  
					
					document.getElementById('paraFrm_totalPaidLeave').value =roundNumber(eval(total2)+eval(total4)+eval(total6)+eval(total8),2);
			}	
		
	}
	catch(e)
	{
	alert(e);
	}  
	
	
  calculateUnpaid();
}


 function calculate(q)
 {
 
   var value='<%=z%>'; 
  var balance=0;
  var manualAdjustLeave=0;
  var result=0;
  
    balance = document.getElementById('balance'+q).value;
    manualAdjustLeave = document.getElementById('manualAdjustLeave'+q).value;
   hiddenBalance= document.getElementById('hiddenBalance'+q).value;
   
    	if(parseFloat(manualAdjustLeave) > parseFloat(hiddenBalance)){
 		alert('Manually Adjusted Leaves should not exceed Balance');
 		document.getElementById('manualAdjustLeave'+q).value = 0;
 		document.getElementById('balance'+q).value=(eval(hiddenBalance));
	 	 onload();
		return false;
	 	}
   
    manualAdjustLeave =document.getElementById('manualAdjustLeave'+q).value
			    if(manualAdjustLeave=="")
			    {
			    	document.getElementById('manualAdjustLeave'+q).value='0';
			    }
    
   hiddenBalance =document.getElementById('hiddenBalance'+q).value;
   balance = document.getElementById('balance'+q).value;
    manualAdjustLeave = document.getElementById('manualAdjustLeave'+q).value;
    result = parseFloat(hiddenBalance)- parseFloat(manualAdjustLeave);
   	document.getElementById('balance'+q).value=Math.round(result*100)/100;
   	 var total=0;
	 var total2=0;
  for(var q=1;q<value;q++){
	  	total = document.getElementById('sysAdjustLeave'+q).value;
	  	
	  total2=eval(total2)+eval(total);
	  
	
	  }
	  
	  	 var total3=0;
	 var total4=0;
  for(var q=1;q<value;q++){
	  	total3 = document.getElementById('adjustLateMark'+q).value;
	  	
	  total4=eval(total4)+eval(total3);
	  
	
	  }
	  
	  	 var total5=0;
	 var total6=0;
  for(var q=1;q<value;q++){
	  	total5 = document.getElementById('adjustHalfDays'+q).value;
	  	
	  total6=eval(total6)+eval(total5);
	  

	  }
	   	 var total7=0;
	 var total8=0;
  for(var q=1;q<value;q++){
		  	
	  	total7 = document.getElementById('manualAdjustLeave'+q).value;
	  	
	  	if(total7=="")
	  	{
	  		total7=0;
	  	}
	  	 
	  total8=eval(total8)+eval(total7);
	  
	 }
	  
	  document.getElementById('paraFrm_totalPaidLeave').value=roundNumber(eval(total2)+eval(total4)+eval(total6)+eval(total8),2);
	  
	
   }

  function callSave()
  {
  try
  {
 	var flagValue =document.getElementById('paraFrm_showPaidFlag').value;
			  var rowid=document.getElementById('paraFrm_rowid').value;
			 if(trim(document.getElementById('paraFrm_lateMarks1').value)==''){
			 document.getElementById('paraFrm_lateMarks1').value=0;
			 }
			 if(trim(document.getElementById('paraFrm_halfDay1').value)==''){   
			 document.getElementById('paraFrm_halfDay1').value=0;
			 }
			 if(opener.document.getElementById('paraFrm_statusFlag').value=='true')
			 {
			 	alert("Salary is already Finalized. So Leave Details  can't be modified!");
			 	return false;
			 }
			else if(opener.document.getElementById('paraFrm_lockFlag').value == 'true')
				{
					alert('Attendance is already locked!');
					return false;
				}
			 
			 else
			 {	 
			 if(flagValue == "true")
			 {
			 	var attdnDays= eval(opener.document.getElementById('attdnDays'+rowid).value);
			 	var paidDays = eval(opener.document.getElementById('paidLevs'+rowid).value);
			 	var attnDays = eval(attdnDays + paidDays);
				 if(eval(document.getElementById('paraFrm_totalPaidLeave').value) > attnDays)
				 {
				 	alert('Attendance days should be greater than Total Paid Leaves.');
				  	return false;
				 }
				 else
				 {
				  
				    var totalPaidLeaves = document.getElementById('paraFrm_totalPaidLeave').value;
				   opener.document.getElementById('paidLevs'+rowid).value=totalPaidLeaves;
				     
				      var newAttdnDays = eval(attnDays-eval(totalPaidLeaves));
		 			opener.document.getElementById('attdnDays'+rowid).value = newAttdnDays;
				 }
			}
			
		if(eval(document.getElementById('paraFrm_totalUnPaidLeave').value) > eval(opener.document.getElementById('attdnDays'+rowid).value))
				 {
					 	alert('Attendance days should be greater than Total UnPaid Leaves.');
					  	return false;
				 }
				 else
		  		{
	  			 var unpaidDays =eval(opener.document.getElementById('unPaidLevs'+rowid).value);
		  		opener.document.getElementById('unPaidLevs'+rowid).value=document.getElementById('paraFrm_totalUnPaidLeave').value;
				var newAttdnDays1=eval(opener.document.getElementById('attdnDays'+rowid).value)+eval(unpaidDays)-document.getElementById('paraFrm_totalUnPaidLeave').value;
			 opener.document.getElementById('attdnDays'+rowid).value=eval(newAttdnDays1);
		   	var salDays= eval(opener.document.getElementById('salDays'+rowid).value);
		  	opener.document.getElementById('sysAdjUnpaid'+rowid).value=document.getElementById('paraFrm_sysAdjustUnpaid').value;
		 	opener.document.getElementById('manAdjUnpaid'+rowid).value=document.getElementById('paraFrm_manualAdjustUnpaid').value;
		 	
		 	opener.document.getElementById('lateMarks'+rowid).value=document.getElementById('paraFrm_lateMarks1').value;
		 
		 	opener.document.getElementById('halfDays'+rowid).value=document.getElementById('paraFrm_halfDay1').value;
		
		
		   	var newSalDays=eval(opener.document.getElementById('salDays'+rowid).value)+eval(unpaidDays)-document.getElementById('paraFrm_totalUnPaidLeave').value;
		 	
		    opener.document.getElementById('salDays'+rowid).value = eval(newSalDays);
		   
		 //  alert('aa'+opener.document.getElementById('eSave'+rowid).value);
		 opener.document.getElementById('eSave'+rowid).value='false'; 
		  //  alert('value'+rowid);
		 	
		 		  document.getElementById('paraFrm').action="PaidLeaveDetails_save.action";
		 		   document.getElementById('paraFrm').submit();
		 		}
  			}
  		window.close();
  	}
  	catch(e)
  	{
  		alert(e);
  	}
  			
  }
 
  
  function calculateUnpaid()
  {
  try
  {
   		var sysAdjustUnpaid = document.getElementById('paraFrm_sysAdjustUnpaid').value;
		var manualAdjustUnpaid = document.getElementById('paraFrm_manualAdjustUnpaid').value;
		var totalUnPaidLeave= document.getElementById('paraFrm_totalUnPaidLeave').value;
		var result=0;
  		if(manualAdjustUnpaid=="")
  		{
  			manualAdjustUnpaid=0;
  		}
  		 result =eval(sysAdjustUnpaid)+eval(manualAdjustUnpaid);
  		totalUnPaidLeave= result;
  		document.getElementById('paraFrm_totalUnPaidLeave').value=roundNumber(totalUnPaidLeave,2);
  
  	}
  	catch(e)
  	{
  	//alert(e);
  	}
  }
  
   function callClose()
 {
 
 	var conf=confirm("Are you sure !\n You want to Close this window ?");
  
  		 		if(conf)
			 		{
			 				window.close();
			 				return true;
			 			
			 		}
			 		else
			 		{
			 		return false;
			 		}
  
 }
 
   
 function recalculate()
 {

			if(document.getElementById('paraFrm_lateMarks1').value =="")
			{
			document.getElementById('paraFrm_lateMarks1').value=0;
			}
			if(document.getElementById('paraFrm_halfDay1').value=="")
			{
			document.getElementById('paraFrm_halfDay1').value=0;
			}
			 if(opener.document.getElementById('paraFrm_statusFlag').value=='true')
			 {
			 	alert("Salary is already Finalized. So Leave Details  can't be modified!");
			 	return false;
			 }
			if(opener.document.getElementById('paraFrm_lockFlag').value == 'true')
				{
					alert('Attendance is already locked!');
					return false;
				}
				
				return true;
 }
 
</script>
