<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SchdInterviewList" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="buttonName" value="int" />
	<s:hidden name="myPage" />
	<s:hidden name="show" />
	<s:hidden name="modeLength"/>
	<s:hidden name="reqNameId" />
    <s:hidden name="codeOfCandidate" />
	<s:hidden name="intervId" />
	<s:hidden name="ChkSerch"/>
	<s:hidden name="checkFilterFlag" />
	<s:hidden name="fakeRankName"></s:hidden>
<s:hidden name="searchFlag"/>
<s:hidden name="selectname"/>
<s:hidden name="searchFilterFlag"/>
<s:hidden name="checkList"/>
<s:hidden name="cancelStatus"/>
	<table width="100%" class="formbg">
				
		<!-- Final Table -->

		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong
							class="formhead"><img
							src="../pages/common/css/default/images/review_shared.gif"
							width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Interview
						Schedule List </strong></td>
						<td width="3%" valign="top" class="txt">
					<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
				
		<tr>
						<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<!-- table 1 -->
								<tr>
									<td>
										<table width="100%" border="0" align="center" cellpadding="0"
											cellspacing="2">
											<!--Table 4-->
											<tr>
												<td height="27" class="formtxt"><a href="#"
													onclick="callFun('N');">Scheduled Interviews </a> | <a
													href="#" onclick="callIntStauts('Y');">Interview Status </a>
													| <a href="#" onclick="callCancelStatus('C')">Canceled Interviews</a></td>
											</tr>
											<s:hidden name="listLength"></s:hidden>
										</table>
										<!--Table 4-->
									</td>
								</tr>
							</table>
					<!-- table 1 -->
					</td>
				</tr>
     		
		
     		 <tr>
     		 		<td colspan="3">
						
							<table width="100%" border="0"  cellspacing="0" cellpadding="0" class="formbg">
							  <tr>

								<td colspan="2" nowrap="nowrap"><strong class="text_head"><s:if test="searchFilterFlag">Applied Filter</s:if><s:else>Narrow the list by applying the filters.</s:else></strong></td>
	
								<td id="showFilter" align="right" colspan="2"><input
									type="button" value="Show Filter" cssClass="token"
									onclick="return callShowFilter();"></td>
	
								<td id="hideFilter" align="right"><input type="button"
									value="Hide Filter" cssClass="token"
									onclick="return callHideFilter();"></td>
        					    </tr>
			                
					 <tr>
				 <td colspan="3">
				 <div id="showFilterValue"  >
				    <table  width="100%">
				         <tr>
						
									<td width="10%" colspan="1">
										<label  class = "set" name="reqs.code" id="reqs.code1" 
							            	ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%> :</td>
									<td width="20%" colspan="1" nowrap="nowrap"><s:textfield name="nameOfReq" size="25"
										readonly="true" /><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'SchdInterviewList_f9Requisition.action');"></td>
									
						        <td width="10%">
						            <label  class = "set" name="cand.name" id="cand.name1" 
						            	ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> : </td>
						         <td width="20%" nowrap="nowrap">
						         	<s:textfield name="nameOfcandidate" size="25" maxlength="50"  onkeypress="return charactersOnly();"/>
						         	<img class="iconImage" src="../pages/images/recruitment/search2.gif" width="18" align="absmiddle" 
							height="18" onclick="javascript:callsF9(500,325,'SchdInterviewList_f9CandidateNameAction.action');"/>
									</td>
		
		                          </tr>
		                          
		                   <tr>
									
							<td width="10%" ><label class="set" name="intstatus" id="intstatus1" ondblclick="callShowDiv(this);"><%=label.get("intstatus")%></label>:</td>
							<td width="20%" nowrap="nowrap"><s:select	name="stats" cssStyle="width:80" list="#{'':'---Select---', 'S':'SELECTED', 'R':'REJECTED','O':'ONHOLD'}" /></td>
		               
							<td width="10%" >
							<label class="set" name="moffer" id="moffer1" ondblclick="callShowDiv(this);"><%=label.get("moffer")%></label>:</td>
					   		<td width="20%" nowrap="nowrap">
					   	 	<s:select	name="makeOfferLetter" cssStyle="width:80"	list="#{'':'---Select---', 'Y':'Yes', 'N':'No'}" /></td>
							</tr>
							<tr>
							<td width="10%" >
							<label class="set" name="nxtround" id="nxtround1" ondblclick="callShowDiv(this);"><%=label.get("nxtround")%></label>:</td>
						  <td width="20%" nowrap="nowrap">
						   <s:select name="intnextRound" cssStyle="width:80"	list="#{ '':'---Select---', 'Y':'Yes', 'N':'No'}" /></td>			
					</tr>	
					<tr>	
					      <td width="100%" height="22" colspan="4" align="center">
						         	<input type="button" class="token" value=" Apply Filter  " 
						         		 onclick="return callApply();"/>&nbsp;
						         		
						         		<input type="button" class="reset" theme="simple" onclick="return calReset();"
								         value="Reset " />
						
						         </td>
						      </tr>
					</table>
					</div>
			      		</td>
			   		</tr>
			  <tr>
			  
			<td colspan="3">
		     			
		     						  <% 
		     						    String [] dispArr = (String [])request.getAttribute("dispArr"); 
		     						  if(dispArr!=null && dispArr.length >0)
		     						  {
		     							 
		     						      int k =0;
		     						      int count =0;
		     						      if(dispArr.length % 2==0)
		     						      {
		     						    	   k =dispArr.length/2;
		     						    	  
		     						    	   
		     						      }else
		     						      {
		     						    	 k =(dispArr.length/2)+1;
		     						    	 
		     						      } 
		     						  %>
		     						   
		     							  <table width="100%" border="0" cellpadding="0" cellspacing="0" id="enableFilterValue">
									           
									      <% for(int m=0;m<k;m++) 
									    	  {%>
									       
									       <tr> 
									       <% if(count<dispArr.length){ %>
									       
										       <td width="20%" height="22" class="formtext">  <%=dispArr [count]%>
										       
										        </td> 
										           <% count++;%>
										          
										       <%} %> 
										    
										       
										        <% if(count<dispArr.length){ %>
										       <td width="20%" height="22" class="formtext" >  <%=dispArr [count]%>  
										        </td> 
										         <% count++;%>
										       <%} %> 
									      </tr>
									      <% }
		     						  } // end of iff
									      %>
									       
									           
									         
								            <tr>
											
										           <td align="center" colspan="5">&nbsp;
														<input type="button" class="reset" theme="simple" onclick="return callClear();"
															value="Clear Filter" />
														<input type="button" class="token" theme="simple" onclick="return callEditFilter();" value="Edit Filter" />	
															</td>
									
								          </tr>	
					        </table>
		     			</td>
     				 </tr>
     		 </table>
     		</td>
		  </tr>
		
		<tr>
					 
					<td align="left" colspan="3"><s:submit cssClass="token" theme="simple"
						value="Create An Offer" action="SchdInterviewList_toOfferDetails"
						onclick="return checkCreateOffer('<%=k %>');" /> <s:submit
						cssClass="token"
						action="InterviewSchedule_fromScheduleInterviewList"
						theme="simple" value="Schedule Next Interview Round"
						onclick="return checkNextIntRound('<%=k %>');" /></td>
						
						 
				</tr>
				
		<tr>
			<!-- tr option -->
			<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- table option --> 
						
				<tr>
					<td colspan="3" width="100%">

					<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
						<!--Table 3-->

						<tr>
							<td width="100%">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="sortable">
								<!--Table 5-->
								<tr>
									<strong>
																	<%
									String status = (String) request.getAttribute("stat");

									out.println("Interview Status");
								 %> </strong>&nbsp;&nbsp; 
							<%
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("PageNo");
						%>
						<s:if test="modeLength"> <td align="right"><b>Page:</b> 
					      <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a> 
					   </td> 	
					 </s:if>
                   </tr>
						
								<tr>
									<td width="100%">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="sortable">
										<!--Table 6-->
										<tr>
											<td width="2%" valign="top" class="formth">&nbsp;</td>
											<td width="5%" valign="top" class="formth"><b><label
												class="set" name="serial.no" id="serial.no"
												ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="reqs.code" id="reqs.code"
												ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
											<td width="15%" valign="top" class="formth"><b><label
												class="set" name="cand.name" id="cand.name"
												ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
											<td   valign="top" class="formth"  ><b><label
												class="set" name="intstatus" id="intstatus"
												ondblclick="callShowDiv(this);"><%=label.get("intstatus")%></label></b></td>
											<td   valign="top" class="formth"  ><b><label
												class="set" name="moffer" id="moffer"
												ondblclick="callShowDiv(this);"><%=label.get("moffer")%></label></b></td>
											<td   valign="top" class="formth"><b><label
												class="set" name="nxtround" id="nxtround"
												ondblclick="callShowDiv(this);"><%=label.get("nxtround")%></label></b></td>
											<td width="20%" valign="top" class="formth"><b><label
												class="set" name="nxtinterviewer" id="nxtinterviewer"
												ondblclick="callShowDiv(this);"><%=label.get("nxtinterviewer")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="Comments1" id="Comm1"
												ondblclick="callShowDiv(this);"><%=label.get("Comments1")%></label></b></td>
										<!-- 	<td width="2%" valign="top" class="formth" align="center">&nbsp;</td>	
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="view.reqs" id="viewreq"
												ondblclick="callShowDiv(this);"><%=label.get("view.reqs")%></label></b></td>  -->
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="preschdlist" id="preschdlist"
												ondblclick="callShowDiv(this);"><%=label.get("preschdlist")%></label></b></td>
											<td>&nbsp;</td>
									</tr>
										<s:hidden name="noData" />
										<s:if test="noData">
											<tr align="center">
												<td width="100%" colspan="7" align="center"><font
													color="red">No Data To Display</font></td>
											</tr>
										</s:if>
										<%
											int cnt = pageNo * 20 - 20;
											int m = 0;
										%>
										<%!int i = 0;%>
										<%
										int k = 1;
										int countRow = 0;
										%>
										<s:iterator value="list">
											<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick= "viewRequisition('<s:property value="requisitionCode"/>')"  title="Double click for view Requisition" > 
												<td class="sortableTD" width="2%" nowrap="nowrap"><input
													type="radio" name="radioButton" id="<%="radioButton"+k %>"
													value="<%=k %>" onclick="setRadioValue();" /></td>
												<td class="sortableTD" width="5%" align="center" nowrap="nowrap"><%=++cnt%>
												<%
												++m;
												%><s:hidden name="radioOffer" id="<%="radioOffer"+k %>" /></td>
												<td class="sortableTD" width="15%">&nbsp;<s:property
													value="requisitionName" /></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="candidateCode" /> &nbsp;<s:property
													value="candidateName" /></td>
												<td class="sortableTD"  nowrap="nowrap" >&nbsp;<s:property
													value="intStatus" /></td>
												<td class="sortableTD" nowrap="nowrap" >&nbsp;<s:property
													value="makeOffer" /></td>
												<td class="sortableTD" nowrap="nowrap" >&nbsp;<s:property
													value="nextRound" /></td>
												<td class="sortableTD" width="20%">&nbsp;<s:property
													value="interviewer" /><s:hidden name="interviewerCode" /></td>
											<!-- 	<td class="sortableTD" width="10%"><s:textarea
													label="%{getText('Comments1')}" theme="simple" cols="15"
													rows="2" name="comments" id="<%="comments"+k%>" /></td> -->
												<td class="sortableTD" width="2%"> <s:hidden name="comments" id="<%="comments"+k%>" /> 
												 <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
												onclick="javascript:callWindow('<%="comments"+k%>','Comm1','readonly','500','500');" title="Click for view comments" ></td>
													
											<!-- 	<td class="sortableTD" width="10%" align="center"><input type="button"
													name="publish"
													onclick="viewRequisition('<s:property value="requisitionCode"/>')"
													class="token" value="View" /></td>  -->
												<td class="sortableTD" width="10%" align="center">
												<s:hidden
													name="requisitionCode" /> <s:hidden name="interviewCode" /><s:hidden
													name="interviewDtlCode" />
												
												<input
													type="button" name="view" class="token" value="View"
													onclick="viewInterviewDetails('<s:property value="requisitionCode"/>','<s:property value="candidateCode"/>')" /></td>
												<td><s:hidden name="nextRound" id="<%="nextRound"+k %>" />
												<s:hidden name="makeOffer" id="<%="makeOffer"+k %>" /> <s:hidden
													name="offerCode" /> <s:hidden name="status" /> <s:hidden
													name="reqCodeOffer" /></td>
											</tr>
											
											<%
											k++;
											%>
										</s:iterator>
										<input type="hidden" name="count" id="count" value="<%=k %>" />
										<%
											i = k;
											k = 1;
											m = i;
										%>
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
			 
		
				<tr>
					 
					<td align="left" colspan="3" width="100%">
					
					 <table width="100%"> 
						  <tr>
						     <td colspan="2">
						      	<s:submit cssClass="token" theme="simple"
						value="Create An Offer" action="SchdInterviewList_toOfferDetails"
						onclick="return checkCreateOffer('<%=k %>');" /> <s:submit
						cssClass="token"
						action="InterviewSchedule_fromScheduleInterviewList"
						theme="simple" value="Schedule Next Interview Round"
						onclick="return checkNextIntRound('<%=k %>');" />
						     </td>
						  <td colspan="1"align="Right"><s:if test="modeLength"><b>Total No. of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
						 </tr> 
					 </table> 
						
						</td> 
				</tr>
			</table>
			<!-- table option --></td>
		</tr>
		<!-- tr option -->
	</table>
	<!-- Final Table -->

</s:form>

<script>

   function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}
	
	
myduponload();
function myduponload(){

     if(document.getElementById('paraFrm_searchFilterFlag').value=="true")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	      }  
}

	
	function callFun(status){//status is kept 'N'....it is for INT_CONDUCT_STATUS = 'N'
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm_myPage').value="";
	    document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status='+status;
	    document.getElementById("paraFrm").submit();
	}
	
	function callIntStauts(status){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm_myPage').value="";
	    document.getElementById("paraFrm").action='SchdInterviewList_interviewStatus.action?status='+status;
	    document.getElementById("paraFrm").submit();
	
	}
	
	function checkNextIntRound(id){
		var rowCount = <%=i%>;
		var flag = false;
		
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('radioButton'+i).checked){
					flag = true;
					if(document.getElementById('nextRound'+i).value == 'No'){
					alert('Cannot schedule for next interview round');
					return false;
					}
				}
			}
			if(!flag){
				alert('Please select at least one record');
				return false;
			}
		return true;
	}
	
	function checkCreateOffer(id){
	var rowCount = <%=i%>;
		var flag = false;
		if(document.getElementById('paraFrm_noData').value=="true"){
				alert("No records to create offer.");
				return false;
			}
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('radioButton'+i).checked){
					flag = true;
					if(document.getElementById('makeOffer'+i).value == 'No'){
					alert("Cannot create offer.");
					return false;
					}
				}
			}
			if(!flag){
				alert('Please select at least one record');
				return false;
			}
		return true;
	}
	
	function viewRequisition(reqCode){
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=SchdInterviewList_interviewStatus.action';
	    document.getElementById("paraFrm").submit();
	}
	
	function viewInterviewDetails(reqCode,candCode)
	{
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	function setRadioValue(){
		var count = document.getElementById("count").value;
		
		for(var i=1; i<count; i++){
			if(document.getElementById("radioButton"+i).checked){
				document.getElementById("radioOffer"+i).value = "Y";
			}
		}
	}
	 
 
  function callPage(id,pageImg){  
  
    document.getElementById('showFilterValue').style.display='none';
	document.getElementById('hideFilter').style.display='none';
	document.getElementById('showFilter').style.display='';  
				
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
		document.getElementById("paraFrm").action='SchdInterviewList_interviewStatus.action?status=Y';
	    document.getElementById("paraFrm").submit();
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
		document.getElementById("paraFrm").action='SchdInterviewList_interviewStatus.action?status=Y';
	    document.getElementById("paraFrm").submit();
		}
		
	}	
	
	

//pgshow(); 
 
  	
  	 	pgshow();
  	function pgshow()
  	{
	  	var pgno=document.getElementById('paraFrm_show').value;  
	  	if(!(pgno=="")){
	  	 document.getElementById('selectname').value=pgno;
	  	 }
  	}
  	
 

showEnable();
function showEnable(){

			
			if(document.getElementById("paraFrm_searchFilterFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("hideFilter").style.display='none';
	}
}  

callFilter();
	function callFilter(){
		        
	         var chkSearch=document.getElementById('paraFrm_searchFilterFlag').value;
	if(document.getElementById('paraFrm_searchFilterFlag').value=="true")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	      }
	else
	      {
	      		document.getElementById('showFilter').style.display='';
	      		  document.getElementById('hideFilter').style.display='none';
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	        }
	                
	        
	    }

function callShowFilter(){//callShowFilter()
     document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	document.getElementById('showFilterValue').style.display='inline';
	document.getElementById('enableFilterValue').style.display='none';
	

}

function callHideFilter(){
	document.getElementById('showFilterValue').style.display='none';
	document.getElementById('hideFilter').style.display='none';
	document.getElementById('showFilter').style.display='';
	
}



function callClear(){
 document.getElementById('showFilterValue').style.display='none';
 document.getElementById("paraFrm_searchFilterFlag").value='false';
 document.getElementById("paraFrm").action='SchdInterviewList_input.action';
 document.getElementById("paraFrm").submit();
  
 	
 }
 
function calReset()
 { 
    document.getElementById('paraFrm_reqNameId').value=""; 
    document.getElementById('paraFrm_nameOfReq').value=""; 
    document.getElementById('paraFrm_codeOfCandidate').value="";
    document.getElementById('paraFrm_nameOfcandidate').value="";
    document.getElementById('paraFrm_stats').value="";
    document.getElementById('paraFrm_makeOfferLetter').value="";
    document.getElementById('paraFrm_intnextRound').value=""; 
    
   }
  function callApply(){
      var DupreqName= document.getElementById('paraFrm_nameOfReq').value;
      var candidateName1= document.getElementById('paraFrm_nameOfcandidate').value;
      var stats= document.getElementById('paraFrm_stats').value;
      
      var makeOfferLetter= document.getElementById('paraFrm_makeOfferLetter').value;
      var intnextRound= document.getElementById('paraFrm_intnextRound').value;
    
    			

  if((DupreqName=="")&&(candidateName1=="")&&(stats=="")&&(makeOfferLetter=="")&&(intnextRound=="")){
   alert('Please enter/select atleast one field to apply filter');
   return false;
   }
   document.getElementById("paraFrm_searchFilterFlag").value="true";
   document.getElementById("paraFrm").action='SchdInterviewList_interviewStatus.action';
 document.getElementById("paraFrm").submit();
}

function callCancelStatus(status){
			document.getElementById('paraFrm_cancelStatus').value = true;
			 document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status='+status;
	         document.getElementById('paraFrm').target="main";
	         document.getElementById("paraFrm").submit();
	} //end of method
	
  function  callEditFilter(){  
	document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	document.getElementById('showFilterValue').style.display='';
	document.getElementById('enableFilterValue').style.display='none';
       }
</script>