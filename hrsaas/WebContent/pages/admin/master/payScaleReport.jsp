<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">    
  	<tr>
  		<td  width="100%" colspan="14"  align="center" ><b>Pay Scale Report</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="14">&nbsp;</td>  
    	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="14">&nbsp;</td> 
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="14">&nbsp;</td>
  	</tr>
  
  	<tr>  			
		<td class="bodyCell" width="5%"><b>Pay Scale ID</td>
		<td class="bodyCell" width="15%"><b>Pay Scale NAME</td>
		<td class="bodyCell" width="10%"><b>Pay Scale START AMT</td>
		<td class="bodyCell" width="10%"><b>Pay Scale INCR AMT1</td>
		<td class="bodyCell" width="10%"><b>Pay Scale FINAL AMT1</td>
		<td class="bodyCell" width="10%"><b>Pay Scale INCR AMT2</td>
		<td class="bodyCell" width="5%"><b>Pay Scale FINAL AMT2</td>
		<td class="bodyCell" width="5%"><b>Pay Scale INCR AMT3</td>
		<td class="bodyCell" width="5%"><b>Pay Scale FINAL AMT3</td>
		<td class="bodyCell" width="5%"><b>Pay Scale COMMISSION</td>
		<td class="bodyCell" width="5%"><b>Pay Scale INCR AMT4</td>
		<td class="bodyCell" width="5%"><b>Pay Scale FINAL AMT4</td>
		<td class="bodyCell" width="5%"><b>Pay Scale INCR AMT5</td>
		<td class="bodyCell" width="5%"><b>Pay Scale FINAL AMT5</td>
		
	</tr>
	
	
	
	<s:iterator value="payScaleMaster.att">             	
	 	<tr>	 
		      <td class="bodyCell" width="5%"><s:property value="payID"/></td>
		      <td class="bodyCell" width="15%"><s:property value="payName"/></td>
		      <td class="bodyCell" width="10%"><s:property value="payStartAmt"/></td>
		      <td class="bodyCell" width="10%"><s:property value="payIncrAmt1"/></td>
		      <td class="bodyCell" width="10%"><s:property value="payFinalAmt1"/></td>
		      <td class="bodyCell" width="10%"><s:property value="payIncrAmt2"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payFinalAmt2"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payIncrAmt3"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payFinalAmt3"/></td>
		       <td class="bodyCell" width="5%"><s:property value="payCommission"/></td>
		       <td class="bodyCell" width="5%"><s:property value="payIncrAmt4"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payFinalAmt4"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payIncrAmt5"/></td>
		      <td class="bodyCell" width="5%"><s:property value="payFinalAmt5"/></td>
		      
		      
		</tr>
	</s:iterator>
	
 </table>