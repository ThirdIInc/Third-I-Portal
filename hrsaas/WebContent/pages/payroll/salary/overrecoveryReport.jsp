<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="overRecovery" id="paraFrm" validate="true" theme="simple">
	<table width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader">
			<p align="center">Over Recovery
			</td>
			<s:hidden name="recoverCode" />
		</tr>
		</table>
<!--
//String[] headers = {"Token No.","Employee Name","Payed Amount","Payed Month","Payed Year"
		,"recovered Amount","Recovered Month","Recovered Year",REMARKS,DEBIT NAME};
		String Repyear;
	String Repmonth;
	String debitHead;
	String debitcode;
	String empid;
	String recoverCode;
	String Remarks;
	String emp_token;
	String empnm;
	String center;
	String rank;
	String Recovermonth;
	String Recoveryear;
	String Recoveramount;
	String Paymonth;
	String Payyear;
	String Payamount;
		-->
		<table class="bodyTable" width="100%">
  	<tr>  			
		<td class="headercell" width="20%">Employee Token</td>
		<td class="headercell" width="30%">Employee Name</td>
		<td class="headercell" width="30%">Debit Name</td>
		<td class="headercell" width="30%">Payed Amount</td>
		<td class="headercell" width="30%">Payed Month</td>
		<td class="headercell" width="30%">Payed Year</td>
		<td class="headercell" width="30%">Recovered Amount</td>
		<td class="headercell" width="30%">Recovered Month</td>
		<td class="headercell" width="30%">Recovered Year</td>
		<td class="headercell" width="30%">REMARKS</td>
			
	</tr>	
		<s:iterator value="reqList"  status="stat">            	
	 <tr>	 
	      <td class="bodyCell" width="20%"><s:property value="emp_Token"/></td>
	      <td class="bodyCell" width="30%"><s:hidden value="empID"/>
	      <s:property value="empName"/></td>
          <td class="bodyCell" width="30%"><s:property value="debitName"/></td>
          <td class="bodyCell" width="30%"><s:property value="Payamount"/></td>
          <td class="bodyCell" width="30%"><s:property value="Paymonth"/></td>
           <td class="bodyCell" width="30%"><s:property value="Payyear"/></td>
           <td class="bodyCell" width="30%"><s:property value="Recoveramount"/></td>
           <td class="bodyCell" width="30%"><s:property value="Recovermonth"/></td>
           <td class="bodyCell" width="30%"><s:property value="Recoveryear"/></td>
            <td class="bodyCell" width="30%"><s:property value="Remarks"/></td>
                    
	  </tr>           
	</s:iterator>
	</table>
		</s:form>