<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="4"  align="center" ><b>Trade Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td>  
    	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td> 
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td>
  	</tr>
  	<tr>  			
		<td class="bodyCell" width="20%"><b>Trade Code</td>
		<td class="bodyCell" width="40%"><b>Trade Name</td>
		<td class="bodyCell" width="20%"><b>Trade Desc</td>
		<td class="bodyCell" width="20%"><b>Trade Parent Code</td>
	</tr>
	
	<s:iterator value="tradeMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="20%"><s:property value="tradeCode"/></td>
		      <td class="bodyCell" width="40%"><s:property value="tradeName"/></td>
		      <td class="bodyCell" width="20%"><s:property value="tradeDesc"/></td>
		      <td class="bodyCell" width="20%"><s:property value="tradeParentCode"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>
  	