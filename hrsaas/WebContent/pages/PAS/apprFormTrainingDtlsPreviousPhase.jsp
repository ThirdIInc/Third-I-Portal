<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

	<%
	 Object data[][] = (Object[][])request.getAttribute("data"); //PREVIOUS PHASES DATA
	 Object recommData[][] = (Object[][])request.getAttribute("recommData"); //PREVIOUS PHASES DATA
	 String flag = (String )request.getAttribute("FLAG");
	%>
		
<s:form  theme="simple" action="ApprFormTrainingDtls" validate="true" id="paraFrm">	
 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >         
  <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <%if(flag.equals("TRNGATT")){ %>
				          	<td width="93%" class="txt"><strong class="text_head">Previous Phase Training Details</strong></td>
				          <%} %>
				          <%if(flag.equals("TRNGREC")){ %>
						  	<td width="93%" class="txt"><strong class="text_head">Previous Phase Training Recommendations Details</strong></td>
						  <%} %>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
       
      <tr> 
     <tr>
     	    <%if(flag.equals("TRNGREC")){ %>
            <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" class="">
               <tr>				   
				   <td width="5%" class="formth">
            	   <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>           
            	   </td>
            	   <td width="5%" class="formth">
            	   <label  name="trng.phase"  class = "set"  id="trng.phase" ondblclick="callShowDiv(this);"><%=label.get("trng.phase")%></label>           
            	   </td>
            	   <td width="30%" class="formth"> 
            	   <label  name="trng.ques.descr"  class = "set"  id="trng.ques.descr" ondblclick="callShowDiv(this);"><%=label.get("trng.ques.descr")%></label>
            		</td>
            		<td width="63%" class="formth">
            		<label  name="trng.ques.comments"  class = "set"  id="trng.ques.comments" ondblclick="callShowDiv(this);"><%=label.get("trng.ques.comments")%></label>
            		</td>
		  		</tr> 						
				<%if(recommData != null && recommData.length != 0) {
							 %>
				 <%
					for(int i=0;i<recommData.length;i++){
				%> 
						<tr>
						 <td width="5%" class="td_bottom_border"><%=i+1 %></td>
						 <td width="15%" class="td_bottom_border"><%=recommData[i][1] %></td>
						 <td width="25%" class="td_bottom_border" ><%=recommData[i][2] %></td>
						 <td width="15%" class="td_bottom_border" ><%=recommData[i][3] %></td>
						</tr>
				<%
					}}else{				 
				%>
				
				 <tr>
				  <td class="td_bottom_border" colspan="4" align="center"> <font color="red">No Data To Display</font></td>
				 </tr> 
				
				<%} %>
        </table>
       </td>
       <%} %>
       
       
			 <%if(flag.equals("TRNGATT")){ %>
            <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" class="">
              	<tr>
					<td class="formth" width="3%">
		            <label  name="quest.sno"  class = "set"  id="quest.sno" ondblclick="callShowDiv(this);"><%=label.get("quest.sno")%></label>            
		            </td>
		            <td class="formth" width="5%">
		            <label  name="trng.phase"  class = "set"  id="trng.phase" ondblclick="callShowDiv(this);"><%=label.get("trng.phase")%></label>
		            </td>
		            <td class="formth" width="15%">
		            <label  name="trng.desc"  class = "set"  id="trng.desc" ondblclick="callShowDiv(this);"><%=label.get("trng.desc")%></label>
		            </td>
		            <td class="formth" width="5%">
		            <label  name="trng.duration"  class = "set"  id="trng.duration" ondblclick="callShowDiv(this);"><%=label.get("trng.duration")%></label>
		            </td>
		            <td class="formth" width="10%">
		            <label  name="trng.from"  class = "set"  id="trng.from" ondblclick="callShowDiv(this);"><%=label.get("trng.from")%></label>            
		            </td>
		            <td class="formth" width="10%">
		            <label  name="trng.to"  class = "set"  id="trng.to" ondblclick="callShowDiv(this);"><%=label.get("trng.to")%></label>           
		            </td>
		            <td class="formth" width="10%">
		            <label  name="trng.sponsor"  class = "set"  id="trng.sponsor" ondblclick="callShowDiv(this);"><%=label.get("trng.sponsor")%></label>
		            </td>
					<td class="formth" width='42%'>
					<label  name="trng.appr.comments"  class = "set"  id="trng.appr.comments" ondblclick="callShowDiv(this);"><%=label.get("trng.appr.comments")%></label>					</td>
				</tr> 						
				<%if(data != null && data.length != 0) {%>							 
				 <%	for(int i=0;i<data.length;i++){%> 
						<tr>
						 <td width='3%' class="sortableTD"><%=i+1 %></td>
						 <td width='4%' class="sortableTD"><%=data[i][2] %></td>
						 <td width='15%' class="sortableTD"><%=data[i][3] %></td>
						 <td width='5%' class="sortableTD"><%=data[i][7] %></td>
						 <td width='10%' class="sortableTD" align="center"><%=data[i][4] %></td>
						 <td width='10%' class="sortableTD" align="center"><%=data[i][5] %></td>
						 <td width='10%' class="sortableTD"><%=data[i][6] %></td>
						 <td width='42%' class="sortableTD"><%=data[i][8] %></td>
						</tr>
				<%
					}}else{				 
				%>
						<tr>
				  			<td colspan="8" align="center"> <font color="red">No Data To Display</font></td>
				 		</tr>
					
				<%
						}
				%>		
							         
        </table>
       </td>
       <%} %>
       
       
       
      </tr>
     </table>
    </td>
    </tr>      
   </table>
  </td>
 </tr>
</table>
</s:form>