<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TestReschedule" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		<tr>
	       <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					 <tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Test Reschedule</strong></td>
						<td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table> <s:hidden name="myPage" />  <s:hidden name="show" /> 
			</td>
		</tr>
		 <tr>
						<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 1 -->
								<tr>
									<td>
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2"><!--Table 4-->
											<tr>
												<td height="27" class="formtxt"><s:hidden name="testStatus"/> <a
													href="#"  onclick="callFun('N');">Scheduled Test List
													</a> | <a href="#" onclick="cancelList('C');">Cancelled Test List
													</a> 
												</td>
											</tr>
										</table><!--Table 4-->
									</td>
								</tr>
							</table><!-- table 1 -->
						</td>
					</tr>
		<tr><!-- Button panel Top -->
			<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="2" >
						<tr>
							<td colspan="3">
							<s:if test="buttonFlag">
				 				<s:submit cssClass="token" action="TestSchedule_fromTestRescheduleList" theme="simple"
									value="   Reschedule " onclick="return reschedule('<%=k %>');" />
								<s:submit cssClass="token" theme="simple" value="Cancel Schedule" onclick="return cancel('<%=k %>');" />
							</s:if>
							<s:else></s:else>	
							</td>
						</tr>
					</table>
				</td>
		</tr><!-- Button panel Top -->
		
		 <tr><!--Reschedule Test-->
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" class="soratble">

					<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable" id="tblCandidateList"><!-- table 6 -->
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 5-->
										<tr>
											<strong> <%
												String status = (String) request.getAttribute("stat");
												if (status != null) {
													out.println(status);
												} else {
													out.println("Cancelled Test List");
												}
												%> </strong> 
										    <%
												int totalPage = (Integer) request.getAttribute("totalPage");
												int pageNo = (Integer) request.getAttribute("PageNo");
												%>
									 <s:if test="modeLength"> 
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
					 </s:if> 
                   </tr>
                   	<tr>
							<td width="100%">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
								<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable" id="tblCandidateList"><!-- table 6 -->
									<tr>
										<td width="3%" valign="top" class="formth" nowrap="nowrap">&nbsp;</td>
										<td width="3%" valign="top" class="formth" nowrap="nowrap"><b><label  class = "set" name="serial.no" id="serial.no" 
				            		ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
										<td width="3%" valign="top" class="formth" align="center"><b><label  class = "set" name="reqs.code" id="reqs.code" 
				            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
										<td width="15%" valign="top" class="formth" align="center"><b><label  class = "set" name="cand.name" id="cand.name" 
				            		ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
				            		<td width="10%" valign="top" class="formth" align="center"><b><label  class = "set" name="test.round" id="test.round" 
			            				ondblclick="callShowDiv(this);"><%=label.get("test.round")%></label></b></td>
										<td width="7%" valign="top" class="formth" align="center"><b><label  class = "set" name="testDate" id="testDate" 
				            		ondblclick="callShowDiv(this);"><%=label.get("testDate")%></label></b></td>
				            		
										<td width="6%" valign="top" class="formth" align="center"><b><label  class = "set" name="testTime" id="testTime" 
				            		ondblclick="callShowDiv(this);"><%=label.get("testTime")%></label></b></td>
				            		
				            			<td width="6%" valign="top" class="formth" align="center"><b><label  class = "set" name="testType" id="testType" 
				            		ondblclick="callShowDiv(this);"><%=label.get("testType")%></label></b></td>
				            		
										<td width="10%" valign="top" class="formth"><b><label  class = "set" name="Comments" id="Comments" 
											ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
											<td width="2%" valign="top" class="formth" align="center">&nbsp;</td>
											</tr>
									<s:if test="noData">
												<tr align="center">
													<td width="100%" colspan="9" align="center"><font
														color="red">There is no data to display</font></td>
												</tr>
									</s:if>
												<%
														int cnt = pageNo * 20 - 20;
														int m = 0;
												%>
											<%!int i = 0;%>
											<%
											int k = 1;
											%>
										<s:iterator value="list">
												<tr  onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);"
									  ondblclick="return viewRequisition('<s:property value="requisitionCode"/>');" title="Double click for view Requisition" > 
												<td class="sortableTD" width="3%" nowrap="nowrap"><input type="radio" name="radioButton" id="<%="radioButton"+k %>"  value="<%=k %>"  /></td>
												<td class="sortableTD" width="5%" align="center" nowrap="nowrap"><%=++cnt%>
													<%
													++m;
													%></td>
												<td class="sortableTD" width="15%"><s:hidden name="requisitionId"/>&nbsp;<s:property
													value="requisitionName" /></td>
												<td class="sortableTD" width="15%"><s:hidden name="candidateCode"/>
													&nbsp;<s:property value="candidateName" /></td>
												<td class="sortableTD" nowrap="nowrap"><s:property
													value="testRound" /> &nbsp; </td>
												<td class="sortableTD" nowrap="nowrap"><s:property
													value="testDate" /> &nbsp; </td>
												<td class="sortableTD"  nowrap="nowrap" ><s:property
													value="testTime" /> &nbsp; </td>
												<td class="sortableTD"  nowrap="nowrap" ><s:property
													value="testType" /> &nbsp; </td>	
													
												<td class="sortableTD" width="10%"><s:textarea label="%{getText('Comments')}" readonly="true"
																theme="simple" cols="15" rows="2" name="comments" id="<%="comments"+k%>" /></td>
												<td class="sortableTD" width="2%" valign="bottom"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
													onclick="javascript:callWindow('<%="comments"+k%>','Comments','readonly','300','300');" >
													<s:hidden name="requisitionCode"/>
													<s:hidden name="testCode"/><s:hidden name="testDtlCode"/> </td>  
										 	</tr>
												<%
												k++;
												%>	
											</s:iterator>
												<%
													i = k;
													k = 1;
													m=i;
												%>		
							</table>
						</td>
					</tr>			
								</table>
							</td>
					</tr>		
										</table>
									</td>
								</tr>	
							</table>
						</td>
					</tr>			
				</table>
			</td>
		</tr>		
		
		
		<tr><!-- Button panel Bottom -->
			<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="2" >
						<tr>
						<td align="left" colspan="1">
						<s:if test="buttonFlag">
				 				<s:submit cssClass="token" action="TestSchedule_fromTestRescheduleList" theme="simple"
									value="   Reschedule " onclick="return reschedule('<%=k %>');" />
								<s:submit cssClass="token" theme="simple" value="Cancel Schedule" onclick="return cancel('<%=k %>');" />
							</s:if>
						<s:else></s:else>		
						</td>	
							<td colspan="3"  align="Right"><s:if test="modeLength"><b>Total No. of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
						</tr>
					</table>
				</td>
		</tr><!-- Button panel Bottom -->
</table><!-- Final Table -->
</s:form>
<script>
window.onload=   document.getElementById('pageNoField').focus();

  function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell) { 
	cell.className='Cell_bg_second'; 
	}
	
	 function callPage(id,pageImg){  
	 var status = document.getElementById("paraFrm_testStatus").value;
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
		if(status=="N")
		{
		  document.getElementById("paraFrm").action='TestReschedule_input.action?status='+status;
		  document.getElementById('paraFrm').submit(); 
		 } else {
		  document.getElementById("paraFrm").action='TestReschedule_cancelTestList.action?status='+status;
	      document.getElementById("paraFrm").submit(); 
		 }
	}	
	 
	 function callPageText(id){ 
	  var status = document.getElementById("paraFrm_testStatus").value;  
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
		 if(status=="N")
		  {
		  document.getElementById("paraFrm").action='TestReschedule_input.action?status='+status;
		  document.getElementById('paraFrm').submit(); 
		 } else {
		  document.getElementById("paraFrm").action='TestReschedule_cancelTestList.action?status='+status;
	      document.getElementById("paraFrm").submit(); 
		 }
		}
		
	}	
	
	function viewRequisition(reqCode){
	  var status = document.getElementById("paraFrm_testStatus").value;  
	   if(status=="N")
		  {
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=TestReschedule_input.action';
		document.getElementById("paraFrm").submit();
		}else{
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=TestReschedule_cancelTestList.action';
		document.getElementById("paraFrm").submit();
		}
	}
	
	function callFun(status){//status is kept 'N'....it is for INT_CONDUCT_STATUS = 'N'
	    document.getElementById('paraFrm_myPage').value="1";
		document.getElementById("paraFrm_testStatus").value="N";
	    document.getElementById("paraFrm").action='TestReschedule_input.action?status='+status;
	    document.getElementById('paraFrm').target="main";
	    document.getElementById("paraFrm").submit();
	}
	
	function cancelList(status){
	             document.getElementById('paraFrm_myPage').value="1";
			  document.getElementById("paraFrm_testStatus").value="C";
			  document.getElementById("paraFrm").action='TestReschedule_cancelTestList.action?status='+status;
	    	  document.getElementById('paraFrm').target="main";
	          document.getElementById("paraFrm").submit();
	}
	
	function reschedule(id){
		var rowCount = <%=i%>;
		var flag = false;
		
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('radioButton'+i).checked){
					flag = true;
				}
			}
			if(!flag){
				alert('Please select at least one record');
				return false;
			}
		return true;
	}
	
	function cancel(id){
		var rowCount = <%=i%>;
		var flag = false;
		
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('radioButton'+i).checked){
					flag = true;
				}
			}
			if(!flag){
				alert('Please select at least one record');
				return false;
			}
			
			var aa=confirm("Are you sure to cancel the Test Schedule ?");
			if(aa){
				window.close();
				document.getElementById("paraFrm").target="main";
				document.getElementById('paraFrm').action = "TestReschedule_cancelTestSchedule.action";
				document.getElementById('paraFrm').submit();
			}
			else{
				return false;
			}
		return true;
	} 
</script>