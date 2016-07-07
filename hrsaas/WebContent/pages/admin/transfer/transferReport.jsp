<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="5"  align="center" ><b>Transfer Application Report</b></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
    	
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">1.&nbsp;&nbsp;</td>
    	<td width="25%">Employee Name :</td>
    	<td width="50%" colspan="3"><s:property value="empName"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">2.&nbsp;&nbsp;</td>
    	<td width="25%">Department :</td>
    	<td width="25%"><s:property value="curDept"/></td>
    	<!--<td width="25%">Designation :</td>
    	<td width="25%"><s:property value="empDesg"/></td>
  	--></tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">3.&nbsp;&nbsp;</td>
    	<td width="25%">Old Branch :</td>
    	<td width="25%"><s:property value="curCent"/></td>
    	<td width="25%">Status :</td>
    	<td width="25%"><s:property value="status"/></td>
    	
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  		
  	
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">4.&nbsp;&nbsp;</td>
    	<td colspan="4">Transfer Detail :</td>
  	</tr>
	 <tr><td colspan="5">
			 <table class="bodyTable" width="100%">
			  	<tr>  			
					<td class="bodyCell" width="20%"><b><font size="1" >Transfer Type</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >Approved/Recommended By</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >Approved/Recommended Date</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >New Branch</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >Comments</font></td>
					
				</tr>	
				
				<s:iterator value="trnRepList" status="stat">            	
				 <tr>	 
				      <td class="bodyCell" width="20%"><s:property value="type"/></td>
			          <td class="bodyCell" width="20%"><s:property value="appName"/></td>
				      <td class="bodyCell" width="20%"><s:property value="date"/></td>
				      <td class="bodyCell" width="20%"><s:property value="newCent"/></td>
					  <td class="bodyCell" width="20%"><s:property value="remark"/></td>
				  </tr>           
				</s:iterator>
			</table>
		</td>
	</tr>	
	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  <tr><td colspan="5">	
				<table class="bodyTable" width="100%">
				  	<!--<tr>  			
						<td align="center" colspan="3"><b><font size="1" >Approved /Not Approved </font></td>			
					</tr>	
					<tr>  			
						<td align="center" colspan="3">&nbsp;</td>			
					</tr>    	
					<tr> <td>Name :</td>	 
					      <td colspan="2" align="left" ><s:property value="aprName"/></td>
					 </tr>  
					   
					         
				--></table>	
		</td>
	</tr>

  
	</table>
