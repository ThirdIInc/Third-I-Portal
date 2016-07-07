 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Advertisement" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	<tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Advertisement</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table> 
				<s:hidden name="reqName"/> <s:hidden name="postionName"/> <s:hidden name="noOfVaccany"/>
				<s:hidden name="postionId"/> <s:hidden name="reqCode"/> <s:hidden name="advertiseCode"/>
				 <s:hidden name="myPage"/> <s:hidden name="show"  /> <s:hidden name="totalRecords"  /> 
				
			</td>
		</tr>
		<tr>
			<td  width="78%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td>
			<td  width="22%"><div align="right">  </td>
		</tr>
		 
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 5-->
					     <tr>
						     <td>
									<table width="99%" border="0" align="center" cellpadding="0" 	cellspacing="0">
										<tr>
											<td height="27" class="text_head"><strong>Advertisement Details </strong></td>
										   <%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
                                 <s:if test="noData"> </s:if> <s:else>
                                          <td align="right"><b>Page:</b>
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
							</td>
					 </s:else>	  
 
										 
										</tr>
										</td>
									</tr> 
								 </table>
							 </td> 
						</tr>
						
						<tr>
						
						<td colspan="3" width="100%">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
								<tr>
									<td width="5%" valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td width="33%" valign="top" class="formth"><label  class = "set" name="reqCodeLabel" id="reqCodeLabel" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqCodeLabel")%></label></td>
									<td width="30%" valign="top" class="formth"><label  class = "set" name="position" id="position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td> 
			            			<td width="10%" valign="top" class="formth"><label  class = "set" name="noOfVaccanies" id="noOfVaccanies" 
			            		ondblclick="callShowDiv(this);"><%=label.get("noOfVaccanies")%></label></td> 
									<td width="12%" valign="top" class="formth" ><label  class = "set" name="cost" id="cost" 
			            		ondblclick="callShowDiv(this);"><%=label.get("cost")%></label></td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="responses" id="responses" 
			            		ondblclick="callShowDiv(this);"><%=label.get("responses")%></label></td> 
								</tr>
								<% int cnt=0;
								   int count = 0; 
								   int pgNo = pageNo * 20 - 20;
								   %>
							 
								<s:iterator value="advertiseMainList"> 
									<tr  <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<%=(cnt+1)%>');">
									
									<td width="5%"  class="sortableTD" nowrap="nowrap"><%++cnt;%> <%=++pgNo%><s:hidden name="itReqName"  id='<%="itReqName"+cnt%>' /> <s:hidden name="itPosition" id='<%="itPosition"+cnt%>'/>
									 <s:hidden name="itVaccany" id='<%="itVaccany"+cnt%>' /> <s:hidden name="itCost"/>  <s:hidden name="itResponse"/> <s:hidden name="itReqCode" id='<%="itReqCode"+cnt%>'/>
									 	 <s:hidden name="itPositionId" id='<%="itPositionId"+cnt%>' /> <s:hidden name="itAdvertiseCode"  id='<%="itAdvertiseCode"+cnt%>'   />  
									 </td>
									<td width="33%" class="sortableTD"  > <s:property value="itReqName"/> </td>
									<td width="30%"  class="sortableTD"> <s:property value="itPosition"/></td>
									<td width="10%"  class="sortableTD" nowrap="nowrap" align="right" ><s:property value="itVaccany"/> </td>
									<td width="12%"  class="sortableTD" nowrap="nowrap" align="right" > <s:property value="itCost"/></td> 
									<td width="10%"  class="sortableTD" nowrap="nowrap" align="right" > <s:property value="itResponse"/></td> 
								</tr>  
								</s:iterator>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">There is no data to display.</font></td>
									</tr>
								</s:if> 
							</table>
							</td>
							</tr>		
							</table><!--Table 5-->
						</td>
					</tr>					
				</table><!--Table 3-->
			</td>
		</tr><!--end of vacancy listing-->
	</table><!-- Final Table -->	
	</td>
	</tr>
	<tr>
			<td  width="78%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td>
			<td  width="30%" align="Right"><s:if test="noData"> </s:if>	<s:else> <b>Total records:</b>&nbsp;<s:property value="totalRecords" /></s:else></td>
			 
		</tr>
	</table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
window.onload=   document.getElementById('pageNoField').focus();
	function addnewFun(){ 
	    document.getElementById("paraFrm").action="Advertisement_addNew.action";
	    document.getElementById("paraFrm").submit();
	}
	
  function searchFun(){ 
	    callsF9(500,325,'Advertisement_f9Advertise.action');
	}
	 
	    
   function newRowColor(cell)
   		 {
		   cell.className='onOverCell'; 
	    }
	
	function oldRowColor(cell,val) { 
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1'; 
	}
	
   function callForEdit(id){   
         document.getElementById("paraFrm_reqName").value= document.getElementById("itReqName"+id).value; 
         document.getElementById("paraFrm_reqCode").value= document.getElementById("itReqCode"+id).value; 
         document.getElementById("paraFrm_postionName").value= document.getElementById("itPosition"+id).value;  
         document.getElementById("paraFrm_postionId").value= document.getElementById("itPositionId"+id).value; 
         document.getElementById("paraFrm_noOfVaccany").value= document.getElementById("itVaccany"+id).value; 
         document.getElementById("paraFrm_advertiseCode").value= document.getElementById("itAdvertiseCode"+id).value;
         document.getElementById("paraFrm").action="Advertisement_showSavedDtl.action";
	     document.getElementById("paraFrm").submit();
	}
	 
	
	 function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='Advertisement_input.action';
		document.getElementById('paraFrm').submit(); 
	}	
	 
 function callPageText(id){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='Advertisement_input.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="Advertisement_input.action";
	document.getElementById('paraFrm').submit();
   }
   
   	pgshow();
  	function pgshow()
  	{
	  	var pgno=document.getElementById('paraFrm_show').value;  
	  	if(!(pgno=="")){
	  	 document.getElementById('selectname').value=pgno;
	  	 }
  	}

</script>