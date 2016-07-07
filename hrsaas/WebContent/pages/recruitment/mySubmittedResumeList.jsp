
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="MySubmittedResume" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			  <table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt">
						<strong class="text_head">My Submitted Resume </strong>
					</td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			 </table>
			<s:hidden name="myPage" />
			<s:hidden name="noData" /> 
			<s:hidden name="show"  />
		  </td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			  <table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1"
								class="sortable">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="27" class="text_head"> </td>
											<%
												int totalPage = (Integer) request.getAttribute("totalPage");
												int pageNo = (Integer) request.getAttribute("PageNo");
											%>
											<s:if test="noData">
											</s:if>
											<s:else>
												<td align="right"><b>Page:</b> <input type="hidden"
													name="totalPage" id="totalPage" value="<%=totalPage%>">
												<a href="#" onclick="callPage('1','F');"> <img
													title="First Page" src="../pages/common/img/first.gif"
													width="10" height="10" class="iconImage" /> </a>&nbsp; <a
													href="#" onclick="callPage('P','P');"> <img
													title="Previous Page"
													src="../pages/common/img/previous.gif" width="10"
													height="10" class="iconImage" /> </a> <input type="text"
													name="pageNoField" id="pageNoField" theme="simple" size="3"
													value="<%= pageNo%>"
													onkeypress="callPageText(event);return numbersOnly()"
													maxlength="4" title="press Enter" /> of <%=totalPage%> <a
													href="#" onclick="callPage('N','N')"> <img
													title="Next Page" src="../pages/common/img/next.gif"
													class="iconImage" /> </a>&nbsp; <a href="#"
													onclick="callPage('<%=totalPage%>','L');"> <img
													title="Last Page" src="../pages/common/img/last.gif"
													width="10" height="10" class="iconImage" /> </a></td>
											</s:else>
										</tr>
										</td>
										</tr>
									</table>
									</td>
								</tr>

								<tr>
									<td colspan="3" width="100%">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable">
										<tr>
											<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
												class="set" name="serial.no" id="serial.no"
												ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
											
											<td width="25%" valign="top" class="formth"><label
												class="set" name="requisitionName" id="requisitionName"
												ondblclick="callShowDiv(this);"><%=label.get("requisitionName")%></label></td>
											
											<td width="25%" valign="top" class="formth"><label
												class="set" name="requisitionPosition" id="requisitionPosition"
												ondblclick="callShowDiv(this);"><%=label.get("requisitionPosition")%></label></td>
												
											<td width="30%" valign="top" class="formth"><label
												class="set" name="candidateName" id="candidateName"
												ondblclick="callShowDiv(this);"><%=label.get("candidateName")%></label></td>
											 
											<td width="15%" valign="top" class="formth" nowrap="nowrap"><label
												class="set" name="viewResume" id="viewResume"
												ondblclick="callShowDiv(this);"><%=label.get("viewResume")%></label></td>
										</tr>
										<%
											int cnt = 0;
											int count = 0;
											int pgNo = pageNo * 20 - 20;
										%>

										<s:iterator value="submittedResumeList">
											<tr>

												<td width="5%" class="sortableTD" align="center">
													<% ++cnt; %> <%=++pgNo%>
												</td>
												
												<td width="25%" class="sortableTD">
													<s:hidden name="itrRequisitionName" />
													<s:hidden name="itrRequisitionCode" />
													<s:property value="itrRequisitionName" />
												</td>
												
												<td width="25%" class="sortableTD">
													<s:hidden name="itrPosition" />
													<s:property	value="itrPosition" />
												</td>
												
												<td width="30%" class="sortableTD">
													<s:hidden name="itrCandidateCode" />
													<s:hidden name="itrCandidateName" />
													<s:property	value="itrCandidateName" />
												</td>
												
												<td width="15%" class="sortableTD" align="center">
													<s:hidden name="itrResumeName" />
													<input type="button" value="View " class="token"
														onclick="viewResume('<s:property value="itrResumeName"/>');">
												</td>
											</tr>
										</s:iterator>
										<s:if test="noData">
											<tr>
												<td width="100%" colspan="7" align="center" class="sortableTD"><font
													color="red">There is no data to display.</font></td>
											</tr>
										</s:if>
										
										<tr>
											<td colspan="5" align="right"><b>Total Recods : <s:property	value="totalRecods" /></b></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							<!--Table 5--></td>
						</tr>
					</table>
					<!--Table 3--></td>
				</tr>
				<!--end of vacancy listing-->
			</table>
			<!-- Final Table --></td>
		</tr>

	</table>
</s:form>
<script>  
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
			document.getElementById('paraFrm').action='MySubmittedResume_input.action';
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
		document.getElementById('paraFrm').action='MySubmittedResume_input.action';
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
  	
  	function viewResume(resumeName){ 
  	    var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = yes, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action='MySubmittedResume_viewCV.action?resumeName='+resumeName;
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = 'main';
	}
</script>