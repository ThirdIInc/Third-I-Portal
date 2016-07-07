<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@page import="java.util.HashMap"%>




<s:form action="HomePage" id="paraFrm" validate="true" theme="simple">
  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="12"  class="pageHeader" align="center" ><b>My Mails</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>      	
  	</tr>
  	
  	 <% String[] indexData=(String[])request.getAttribute("index");
  	String[][] pageIndex=(String[][])request.getAttribute("pageIndex");
  	 Object msgLen=request.getAttribute("msgData");  	
  	 	boolean result=false,result1=false;
  	  
  	 int index= Integer.parseInt(indexData[1])/15;
  	 if(indexData[0].equals("0")){
  		 result=true;
  	 }
  	
  	 if(Integer.parseInt(indexData[1]) >= Integer.parseInt(msgLen.toString())){
  		 result1=true;
  	 }
	 %>
	 
	
  	<tr>
  	  
    	<td >INBOX(<s:property value="noOfMails"/>)&nbsp;
    
    	<input type="button" value="Delete" onclick="callDelete();" />&nbsp;
    	<input type="button" value="Compose" onclick="callCompose()" />
    	</td>
  	</tr>
  	<tr>
  	<td >&nbsp;  </td>
  	</tr>
   </table>
  	
  	
   	
   <table class="bodyTable" width="100%">
   <tr>
    	<td width="100%" colspan="3">&nbsp;</td>      	
  	</tr>
   <tr>
   <td colspan="2">List of Mails :</td>
     <td align="right" colspan="5">
     <% 
     if(!result){ %> 
     <<a class="txt" href="<s:url action="HomePage_mailChk"></s:url>">First</a>>&nbsp;
     <<a class="txt" href="<s:url action="HomePage_previousMsgs"><s:param name="startInd" value="<%= String.valueOf(indexData[0]) %>"/><s:param name="endInd" value="<%= String.valueOf(indexData[1]) %>"/></s:url>">Previous</a>>&nbsp;
      <%} 
     if(pageIndex.length <=5) {
    	  for(int i=0;i<pageIndex.length;i++) {
 	    	 %> <a class="txt" href="<s:url action="HomePage_pageMsgs"><s:param name="PageStartInd" value="<%= String.valueOf(pageIndex[i][0]) %>"/>
 	    	 <s:param name="PageEndInd" value="<%= String.valueOf(pageIndex[i][1]) %>"/></s:url>"><b><%if(i+1==index) {%><b><u><%=i+1%></u></b><%}else { %><%=i+1%><%} %></a>
 	    	 <%}
     }
     else if(index<=3){
    	 System.out.println("--------------------------------- in elseif (index<=3)"+index);
         for(int i=0;i<5;i++) {
   	    	 %> <a class="txt" href="<s:url action="HomePage_pageMsgs"><s:param name="PageStartInd" value="<%= String.valueOf(pageIndex[i][0]) %>"/>
   	    	 <s:param name="PageEndInd" value="<%= String.valueOf(pageIndex[i][1]) %>"/></s:url>"><%if(i+1==index) {%><b><u><%=i+1%></u></b><%}else { %><%=i+1%><%} %></a><% } %><%="..."+pageIndex.length %>
        
        <%}
        else{%>
			
   	     <%if(index+2<pageIndex.length){ 
   	     for(int i=index-2;i<=index+2;i++) {
   	    	 System.out.println("--------------------------------- in else index and i ="+index+""+i);
   	    	 %> <a class="txt" href="<s:url action="HomePage_pageMsgs"><s:param name="PageStartInd" value="<%= String.valueOf(pageIndex[i-1][0]) %>"/>
   	    	 <s:param name="PageEndInd" value="<%= String.valueOf(pageIndex[i-1][1]) %>"/></s:url>"><%if(i==index) {%><b><u><%=i%></u></b><%}else { %><%=i%><%} %></a>
   	    	 <%}%><%="..."+pageIndex.length %>
   	    	 <%
   	          } 
   	     else{
   	      for(int i=index-2;i<=pageIndex.length;i++) {
    	    	 System.out.println("--------------------------------- in else index and i++++++++++++++++++++ ="+index+""+i);
    	    	 %> <a class="txt" href="<s:url action="HomePage_pageMsgs"><s:param name="PageStartInd" value="<%= String.valueOf(pageIndex[i-1][0]) %>"/>
    	    	 <s:param name="PageEndInd" value="<%= String.valueOf(pageIndex[i-1][1]) %>"/></s:url>"><%if(i==index) {%><b><u><%=i%></u></b><%}else { %><%=i%><%} %></a>
    	    	 <%}
   	     }
   	     } %>&nbsp;
	    <%if(!result1){ %>< 
	      <a class="txt" href="<s:url action="HomePage_nextMsgs"><s:param name="startInd" value="<%= String.valueOf(indexData[0]) %>"/><s:param name="endInd" value="<%= String.valueOf(indexData[1]) %>"/></s:url>">Next</a>> 
	  <%   for(int i=pageIndex.length-1;i<pageIndex.length;i++) { %>
	      <<a class="txt" href="<s:url action="HomePage_pageMsgs"><s:param name="PageStartInd" value="<%= String.valueOf(pageIndex[i][0]) %>"/>
    	    	 <s:param name="PageEndInd" value="<%= String.valueOf(pageIndex[i][1]) %>"/></s:url>">Last</a>>
	      <% } } %>&nbsp;
	      
	  </td>   
   </tr>
   <tr>
   		
              <td  width="5%" class="headercell"  align="center">Select</td>
              <td  width="30%" class="headercell"  align="center">Sender</td>
               <td  width="40%" class="headercell"  align="center">Subject</td>
              <td  width="10%" class="headercell"  align="center">Date</td>
         	<td  width="4%" class="headercell" align="center">Size(KB)</td>
           
              
   </tr>
            
     <% int i=0;HashMap afdata=(HashMap)request.getAttribute("data"); 
     	
	 %>
  	
            <%int j=1;%>
            

  
  
      <s:iterator value="mailList" status="stat"> 
      <%String audFlag=(String)afdata.get(""+i);
    %>  
      	  <tr>
      
        <s:hidden name="msgCode1" value="%{msgCode}"/>
                     <td width="5%" class="bodyCell"  align="center" ><input type="checkbox" class="checkbox"  name="leaveChk" value="<%=audFlag.equals("Y")?"checked":""%>" onclick="callChk(<%=i%>)"   />
	   		 <input type="hidden" name="pmChkFlag" id="<%=i%>" value="<%=audFlag.equals("A")?"N":audFlag%>" /></td>
      	  <td  width="30%" class="bodyCell"><a class="txt" target="_blank" href="<s:url action="HomePage_description"><s:param name="msgNo" value="msgCode"/></s:url>"><s:property value="sender"/></a></td>
	    <td  width="40%" class="bodyCell"><s:if test="%{attChkFlag}"><img src="../pages/images/attach.png"/>&nbsp;</s:if><s:property value="subject" /></td>
	   
	    <td  width="10%" class="bodyCell" ><s:property value="Date"/></td>
	       <td  width="4%" class="bodyCell" align="center"><s:property value="mailSize"/></td>
	    	    
	   
	  </tr>
	 	 <%
			j++;i++;
			%>
       </s:iterator>	
    <tr>
			<td width="100%" colspan="6"><input type="hidden" name="hid" value="<%=i%>"/>&nbsp;</td>
		</tr>  	
 </table>
 </s:form>
 <%
 request.setAttribute("data","data");
 %>
 <script>
  
  	
  	function callChk(id){

 if(document.getElementById(id).value=='Y'){
  document.getElementById(id).value='N';
   }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
   } 
}

function callCompose()
{
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="HomePage_mailCompose.action";	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";	
}


	
	function callDelete()
	{
	
	
		count=document.getElementById("paraFrm").hid.value;
   tmp=0;
   for(i=0;i<count;i++){
	  if(document.getElementById(i).value=="N"){
   	    tmp++;
   	  }   
   }
   if(count==tmp){
   		alert('Please select atleast one mail to Delete.');
   		return false;
   	}
		else{
		
		document.getElementById('paraFrm').action="HomePage_mailDelete.action";	
			document.getElementById('paraFrm').submit();	
		
			}	
	
	}
</script>
