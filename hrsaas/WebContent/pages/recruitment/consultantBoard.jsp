 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ConsultantPortal" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	<tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Consultant Portal Board </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>  
				 <s:hidden name="myPage"/> <s:hidden name="show"  />   
				  <s:hidden name="formAction" id="formAction"/>   
				  <s:hidden name="requisitionCode"  />  <s:hidden name="requisitionName"  /> <s:hidden name="partnerFlag"  />  
				   <s:hidden name="position"  /> 
				   <s:hidden name="noData"  /> <s:hidden name="apprvFlag" value="false" />   <s:hidden name="hiringManagerCode"  /> <s:hidden name="hiringManager"  />
			</td>
		</tr> 
		 
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
		  <tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1" class="sortable"><!--Table 5-->
					     <tr>
		 										     <td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="27" class="text_head"><strong>Open Vacancy List </strong></td>
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
									<td width="20%" valign="top" class="formth"><label  class = "set" name="laReqName" id="laReqName" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laReqName")%></label></td>
									<td width="20%" valign="top" class="formth"><label  class = "set" name="laPosition" id="laPosition" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laPosition")%></label></td> 
			            			<td width="8%" valign="top" class="formth"><label  class = "set" name="laLocation" id="laLocation" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laLocation")%></label></td> 
									<td width="8%" valign="top" class="formth" ><label  class = "set" name="laOpenNoOfVac" id="laOpenNoOfVac" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laOpenNoOfVac")%></label></td>
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="laTotalNoOfVac" id="laTotalNoOfVac" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laTotalNoOfVac")%></label></td>  
			            		
			            		<td width="12%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="laPostCandidate" id="laPostCandidate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laPostCandidate")%></label></td>  
			            		<td width="12%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="laViewReq" id="laViewReq" 
			            		ondblclick="callShowDiv(this);"><%=label.get("laViewReq")%></label></td> 
								</tr>
								<% int cnt=0;
								   int count = 0; 
								   int pgNo = pageNo * 20 - 20;
								   %>
							 
								<s:iterator value="partnerJobList"> 
									<tr>
									
									<td width="5%"  class="sortableTD" nowrap="nowrap"><%++cnt;%> <%=++pgNo%><s:hidden name="itReqName"  id="<%="itReqName"+cnt%>" /> <s:hidden name="itPosition" id="<%="itPosition"+cnt%>"/>
									 <s:hidden name="itLocation" id="<%="itLocation"+cnt%>" /> <s:hidden name="itNoOfOpenVac"/>  <s:hidden name="itTotalNoPostVac"/> <s:hidden name="itReqCode" id="<%="itReqCode"+cnt%>"/>
									  <s:hidden name="itHiringManager"/>   <s:hidden name="itHiringManagerId"/> 
									  </td>
									<td width="20%" class="sortableTD"  > <s:property value="itReqName"/> </td>
									<td width="20%"  class="sortableTD"> <s:property value="itPosition"/>&nbsp;</td>
									<td width="15%"  class="sortableTD"  ><s:property value="itLocation"/>&nbsp; </td>
									<td    class="sortableTD" nowrap="nowrap" align="right" > <s:property value="itNoOfOpenVac"/></td> 
									<td    class="sortableTD" nowrap="nowrap" align="right" > <s:property value="itTotalNoPostVac"/></td> 
								    <td width="12%"  class="sortableTD"  > <input type="button" value="Post Candidate" class="token" onclick="callPostCandidate('<s:property value="itReqCode"/>','<s:property value="itReqName"/>','<s:property value="itPosition"/>','<s:property value="itHiringManager"/>','<s:property value="itHiringManagerId"/>')" > </td> 
									<td width="12%"  class="sortableTD"   ><input type="button" value="View " class="token"  onclick="viewRequisition('<s:property value="itReqCode"/>');" > </td> 
								
								
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
 
	</table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>  
window.onload=   document.getElementById('pageNoField').focus();
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
			document.getElementById('paraFrm').action='PartnerJobBoard_input.action';
			document.getElementById('paraFrm').submit();
		}
		
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
		document.getElementById('paraFrm').action='PartnerJobBoard_input.action';
		document.getElementById('paraFrm').submit(); 
	}	 
	 
	 
 function onPage()
   {

  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val; 
	document.getElementById('paraFrm').action="PartnerJobBoard_input.action";
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
  	
  	function viewRequisition(reqCode,status){ 
  	    document.getElementById('paraFrm_apprvFlag').value="false";
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&apprvFlag=false'
	  	document.getElementById("paraFrm").submit();
	}
	
    function callPostCandidate(reqCode,requisitionName,position,hiringManager,hiringManagerCode)
    {   
       	document.getElementById('paraFrm_requisitionCode').value=reqCode;
	    document.getElementById('paraFrm_requisitionName').value=requisitionName; 
	    document.getElementById('paraFrm_position').value=position;
	    document.getElementById('paraFrm_hiringManager').value=hiringManager;
	    document.getElementById('paraFrm_hiringManagerCode').value=hiringManagerCode; 
	    document.getElementById('paraFrm_partnerFlag').value="true";  
		document.getElementById("paraFrm").action='PostResume_input.action';
	  	document.getElementById("paraFrm").submit();
	}
</script>