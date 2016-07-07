<%@ taglib prefix="s" uri="/struts-tags" %> 


<s:form action="TDSReport"  id="paraFrm" theme="simple">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">TDS Details</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
  <table class="formbg" width="100%" >
  	<tr>
  		<td  width="100%" colspan="12"  align="center" ><b>TDS Details</td>
  	</tr>
  	
  	<% 
  	try {
  		
  		String[][] data = (String[][]) request
								.getAttribute("row"); 
  	
  	
  	
  	System.out.println("----------------------------"+data.length);
  	
  	%>
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>      	
  	</tr>
  	
  	<tr>
  	  
    	<td width="25%">1. Name :</td>
    	<td width="50%" colspan="2"><%= String.valueOf(data[0][12]) %></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	<tr>
  	
  	
  	<tr>
  	  
    	<td width="25%">2.Total Salary:</td>
    	<td width="50%" colspan="2"><%= String.valueOf(data[0][0]) %></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  	<tr>
  	  
    	<td width="25%">3.Loss Of Pay:</td>
    	<td width="50%" colspan="2"><s:property  value="lop"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  		
  	<tr>
  	  
    	<td width="25%">4.OT Amount:</td>
    	<td width="50%" colspan="2"><s:property value="otherInc"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  	<tr>
  	  
    	<td width="25%">5.NDA Amount:</td>
    	<td width="50%" colspan="2"><s:property value="nda"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  	<tr>
  	  
    	<td width="25%">6.DA Arrear Amount:</td>
    	<td width="50%" colspan="2"><s:property value="daArr"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  		
  	<tr>
  	  
    	<td width="25%">7.Gross Salary:</td>
    	<td width="50%" colspan="2"><%= String.valueOf(data[0][11]) %></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	
  	
  	

 </table>
    	
   <table class="bodyTable" width="100%" >
   
   <tr>
   <td width="9%">8.Investments of Employee :</td>
   
   <s:if test="%{tdscal.str}">
   <tr>
              <td width="20%" class="bodyCell" align="center">Sr No.</td>
              
              <td width="30%" class="bodyCell" align="center">Investment Name</td>
              <td width="20%" class="bodyCell" align="center">Investment Chapter</td>
              <td width="30%" class="bodyCell" align="center">Investment Amount</td>
              
              
   </tr>
            
     
            <%int j=1; %>
      <s:iterator value="tdscal.invList" status="stat"> 
      	  <tr>
      	<td width="20%" class="bodyCell"><%=j%></td>
	   
	    <td width="30%" class="bodyCell"><s:property value="invName"/></td>
	    <td width="20%" class="bodyCell"><s:property value="invChapter"/></td>
	    <td width="30%" class="bodyCell"><s:property value="invAmount"/></td>
	   
	   
	  </tr>
	 	 <%
			j++;
			%>
       </s:iterator>
      </s:if> 
      <s:else> 
      <td width="25%" colspan="3">No Investments for the Employee</td>
    	
      </s:else>
      </tr>
      
      <tr>
  	<td >&nbsp;</td>
  	</tr>
  	</table>
  	<table class="bodyTable" width="100%">
       	<tr>
  	  
    	<td width="25%">9.Total Excemptions:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][1]) %></td>
    	
  
  	  
    	<td width="25%">10.Total Rebate:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][2]) %></td>
    	
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  
  	
  		<tr>
  	  
    	<td width="25%">11.Other Income Total:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][3]) %></td>
    	
  
  	  
    	<td width="25%">12.Total Deductions:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][4]) %></td>
    	
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  
  	<tr>
  	  
    	<td width="25%">13.Taxable Income:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][5]) %></td>
    	
  	
  	
  	  
    	<td width="25%">14.Total Tax:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][6]) %></td>
    	
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	<tr>
  	<tr>
  	
  		<tr>
  	  
    	<td width="25%">15.Net Tax:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][7]) %></td>
    	
  	
    	<td width="25%">16.Tax per Month:</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][8]) %></td>
    	
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	<tr>
  	<tr>
  	
  		<tr>
  	  
    	<td width="25%">14.Tax Paid</td>
    	<td width="25%" colspan="2"><%= String.valueOf(data[0][9]) %></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
  	<td >&nbsp;</td>
  	</tr>
  	<tr>
  	
  	
     <% }
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	} %>
       
 </table></td></tr></table>
 
 </s:form>