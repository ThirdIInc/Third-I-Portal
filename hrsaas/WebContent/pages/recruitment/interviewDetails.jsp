<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="InterviewDetails" validate="true" id="paraFrm" theme="simple">
<s:hidden name="rowId" />
<s:hidden name="statusFlag"/>
<s:hidden name="myPage"/>
<s:hidden name="show"/>
<s:hidden name="interviewRoundTypeConductInterviewID" />
<s:hidden name="candidateEvaluationCode" />
<s:hidden name="interviewDetailCode" />
<%@include file="/pages/common/labelManagement.jsp" %>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		
		<tr>
	       <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					 <tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Interview Details</strong></td>
						 <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>
			</td>
		</tr>
		
		 <tr><!--Schedule Test-->
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 1-->
				<s:if test="pendingFlag">
					<tr>
						<td width="50%"><strong>
							<a href="#" onclick="javascript:changeHeading('N')" style="color: black">Pending Interviews List</a> </strong> | 
							<a href="#" onclick="javascript:changeHeading('Y')" style="color: black">Completed Interviews List</a> |
							<a href="#" onclick="javascript:canceledList('C')" style="color: black">Canceled Interviews</a> | 
							<a href="#" onclick="javascript:changeHeading('O')" style="color: black">On Hold Interviews List</a>
						</td>
					</tr>
				</s:if>
				<s:elseif test="canceledFlag">
					<tr>
						<td width="50%"><a href="#" onclick="javascript:changeHeading('N')" style="color: black">Pending Interviews List</a> | 
						<a href="#" onclick="javascript:changeHeading('Y')" style="color: black">Completed Interviews List</a> |
						<strong><a href="#" onclick="javascript:canceledList('C')" style="color: black">Canceled Interviews</a> | </strong>
						<a href="#" onclick="javascript:changeHeading('O')" style="color: black">On Hold Interviews List</a>
						</td>
					</tr>
				</s:elseif>
				<s:else>
					<tr>
						<td width="50%"><a href="#" onclick="javascript:changeHeading('N')" style="color: black">Pending Interviews List</a> | 
						<strong><a href="#" onclick="javascript:changeHeading('Y')" style="color: black">Completed Interviews List</a></strong> |
						<a href="#" onclick="javascript:canceledList('C')" style="color: black">Canceled Interviews</a> | 
						<a href="#" onclick="javascript:changeHeading('O')" style="color: black">On Hold Interviews List</a>
						</td>
					</tr>
				</s:else>
				</table>
			</td>
		</tr>
		<%
		String status=(String)request.getAttribute("stat");
				if(status.equals("") || status.equals("null")){
					status="N";
				}else {
					status=status;
				}
		%>
		<tr>
			<s:if test="pendingFlag">
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="2" >
				<tr>
				<s:if test="intrList">
					<td colspan="3">
				 		<!--
				 			<s:submit cssClass="token" theme="simple"	action="ConductInterview_conductInterview"	value="Conduct Interview" onclick="return conductValidation();" />
				 		 -->
				 		
				 		<input type="button" class="token" theme="simple" value="Conduct Interview" onclick="conductValidation('<%=status %>');"/>
				</s:if>
				<s:submit cssClass="token" action="ConductInterview_addNewInterview"
					theme="simple" value="Add New Interview" />
				<s:if test="intrList" >
				 
				<input type="button" class="token" theme="simple" value="Clear" onclick="fromClearButton('<%=status %>');"/>
				</td> </s:if></tr></table></td>
			</s:if>
		</tr>

		 <tr><!--Schedule Test-->
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" class="soratble">
					<tr>
						<td>  
							<% 
								if(status.equals("N")){
							%>
								 	<b>Pending Interviews List</b>
							<%}
								if(status.equals("Y")){ 
							%>
									<b>Completed Interviews List</b>
							<%}
								if(status.equals("C")){ 
							%>
									<b>Canceled Interviews</b>
							
							<%	}
								if(status.equals("O")) {
							%> 
							       <b>On Hold Interviews</b>
							<%
							    }
							%>
						</td>
					</tr>
					<tr>
					    <%
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("PageNo");
						%>
                  <s:if test="intrList">  
                     <td align="right"><b>Page:</b>
							 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F','<%=status%>');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P','<%=status%>');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event,'<%=status%>');return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N','<%=status%>')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L','<%=status%>');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
							</td>
				    </s:if>   
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable" id="tblCandidateList"><!-- table 6 -->
								<tr>
									<s:if test="pendingFlag" ><td width="3%" valign="top" class="formth" nowrap="nowrap">&nbsp;</td></s:if>
									<td width="3%" valign="top" class="formth" nowrap="nowrap"><b><label  class = "set" name="serial.no" id="serial.no" 
			            		ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<td width="3%" valign="top" class="formth" align="center"><b><label  class = "set" name="reqs.code" id="reqs.code" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label  class = "set" name="cand.name" id="cand.name" 
			            		ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
									<td width="12%" valign="top" class="formth" align="center"><b><label  class = "set" name="position" id="position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
									<td width="7%" valign="top" class="formth" align="center"><b><label  class = "set" name="intDate" id="intDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label></b></td>
									<td width="6%" valign="top" class="formth" align="center"><b><label  class = "set" name="intTime" id="intTime" 
			            		ondblclick="callShowDiv(this);"><%=label.get("intTime")%></label></b></td>
									<td width="10%" valign="top" class="formth" align="center"><b><label  class = "set" name="intRnd" id="intRnd" 
			            		ondblclick="callShowDiv(this);"><%=label.get("intRnd")%></label></b></td>
									<td width="5%" valign="top" class="formth" align="center"><b><label  class = "set" name="view.cv" id="view.cv" 
			            		ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
									<td width="12%" valign="top" class="formth" align="center"><b><label  class = "set" name="prvIntFed" id="prvIntFed" 
			            		ondblclick="callShowDiv(this);"><%=label.get("prvIntFed")%></label></b></td>
									<td width="2%" valign="top" class="formth" align="center"><b><label  class = "set" name="schedulerComments" id="schedulerComments" 
			            		ondblclick="callShowDiv(this);"><%=label.get("schedulerComments")%></label></b></td>
								</tr>
								<%
											int cnt = pageNo * 20 - 20;
											int m = 0;
											int countRow = 0;
									%>
								
								<%!int c = 0;%>
				  				<%!
				  				int j = 1;
				  				%>
				  				<s:if test="intrList">
				  				<s:if test="pendingFlag">
								<s:iterator  value="intrList">
                        				<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return showRequisition('<s:property value="reqCode" />','N');" title="Double click for view Requisition" >
									  
                        				<td width="3%" class="sortableTD" nowrap="nowrap">
                        				<s:hidden name="intRoundTypeCode" />
                        				<s:hidden name="hiddenCandidateEvaluationCode" />
                        				<s:hidden name="hiddenInterviewDetailCode" />
                        				<input type="radio" name="selCand" id='<%="selCand"+j%>' value="<%=j-1 %>" onclick="setRoundTypeCode('<s:property value = "intRoundTypeCode"/>','<s:property value = "hiddenCandidateEvaluationCode"/>','<s:property value = "hiddenInterviewDetailCode"/>');"/></td>
		                        		<td width="3%" class="sortableTD" align="center"><%=++cnt%>
											<%
											++m;
											%><s:hidden name="division" /><s:hidden name="branch" /><s:hidden name="department" />
		                        		<s:hidden name="divisionId" /><s:hidden name="branchId" /><s:hidden name="deptId" />
		                        		<s:hidden name="intCode" /><s:hidden name="intDtlCode" /></td>
		                        		<td width="8%" class="sortableTD"><s:hidden name="reqCode" id='<%="reqCode"+j%>' /><s:hidden name="reqName" />
		                        		<s:property value="reqName" /></td>
		                        		<td width="15%" class="sortableTD"><s:property value="candName"/>
		                        			<s:hidden name="candName" id='<%="candName"+j%>'/><s:hidden name="candCode" id='<%="candCode"+j%>'/></td>
										<td width="12%" class="sortableTD"><s:property value="position"/> <s:hidden name="position" /><s:hidden name="positionId" /></td>
										<td width="12%" class="sortableTD" nowrap="nowrap">&nbsp;<s:property value="intDate" /><s:hidden name="intDate" /></td>
										<td width="6%" class="sortableTD">&nbsp;<s:property value="intTime" /><s:hidden name="intTime" /></td>
										<td width="10%" class="sortableTD"><s:property value="intRoundType" /><s:hidden name="intRoundType" /></td>
										<td width="5%" class="sortableTD" align="center"><input type="button" class="token" value="View" onclick="showRecord('<s:property value="resumeName"/>');"/></td>
										<td width="12%" class="sortableTD" align="center"><input type="button" value="View" onclick="viewInterviewDetails('<s:property value="reqCode" />','<s:property value="candCode" />','<s:property value="candCode" />');" class="token"/></td>
										<td class="sortableTD" width="2%"><s:hidden name="comments" id='<%="comments"+j%>' /><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
												onclick="javascript:callWindow('<%="comments"+j%>','schedulerComments','readonly','300','300');" ></td>
												
										</td>
                        		  </tr>
                        <%
                        j++;
                        %>
								</s:iterator>
								</s:if>
								<s:else>
								<s:iterator value="intrList">
									<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return showRequisition('<s:property value="reqCode" />','Y');" title="Double click for view Requisition" >
									  
		                        		<td width="3%" class="sortableTD" align="center"><%=++cnt%>
											<%
											++m;
											%><s:hidden name="division" /><s:hidden name="branch" /><s:hidden name="department" />
		                        		<s:hidden name="divisionId" /><s:hidden name="branchId" /><s:hidden name="deptId" />
		                        		<s:hidden name="intCode" /><s:hidden name="intDtlCode" /></td>
		                        		<td width="8%" class="sortableTD"><s:hidden name="reqCode" id='<%="reqCode"+j%>' /><s:hidden name="reqName" />
		                        		<s:property value="reqName" /></td>
		                        		<td width="15%" class="sortableTD"><s:property value="candName"/>
		                        			<s:hidden name="candName" id='<%="candName"+j%>'/><s:hidden name="candCode" id='<%="candCode"+j%>'/></td>
										<td width="12%" class="sortableTD"><s:property value="position"/> <s:hidden name="position" /><s:hidden name="positionId" /></td>
										<td width="7%" class="sortableTD" nowrap="nowrap">&nbsp;<s:property value="intDate" /><s:hidden name="intDate" /></td>
										<td width="6%" class="sortableTD">&nbsp;<s:property value="intTime" /><s:hidden name="intTime" /></td>
										<td width="10%" class="sortableTD"><s:property value="intRoundType" /><s:hidden name="intRoundType" /></td>
										<td width="5%" class="sortableTD" align="center"><input type="button" class="token" value="View" onclick="showRecord('<s:property value="resumeName"/>');"/></td>
										<td width="12%" class="sortableTD" align="center"><input type="button" value="view" onclick="viewInterviewDetails('<s:property value="reqCode" />','<s:property value="candCode" />');" class="token"/></td>
										<td class="sortableTD" width="2%"><s:hidden name="comments" id='<%="comments"+j%>' /><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
												onclick="javascript:callWindow('<%="comments"+j%>','schedulerComments','readonly','300','300');" ></td>
                        		  </tr>
                        		  <%
                        		  j++;
                        		  %>
                        		  </s:iterator>
								</s:else>
								</s:if>
								<s:else>
									<tr align="center">
		                        		<td width="100%" colspan="12">
		                        		<font color="red">* There is no record to display</font></td>
		                        		</tr>
								</s:else>
						  <%
						  	c = j;
						  	j = 1;
						  	m=c;
						  %>
							</table><!-- table 6 -->
						</td>
					</tr>					
					
         			 </table>
         			 <tr>
         			 <td colspan="3">
         			 <table width="100%" border="0" cellpadding="0" cellspacing="0" >
         			 <tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="2" ><!--Table 7-->
								<tr>
						<s:if test="pendingFlag">
						<td>
						<%
						if (c > 1) {
						%>
							<input type="button" class="token" theme="simple" value="Conduct Interview" onclick="conductValidation('<%=status %>');"/>
							<!-- 
							<s:submit cssClass="token"  theme="simple" action="ConductInterview_conductInterview"
								value="Conduct Interview" onclick="return conductValidation();" />
							 -->
							
						<%
						}
						%>
								<s:submit cssClass="token" action="ConductInterview_addNewInterview" theme="simple"
								value="Add New Interview"  />
						<%
						if (c > 1) {
						%>
						 
						<input type="button" class="token" theme="simple" value="Clear" onclick="fromClearButton('<%=status %>');"/>
						<%
						}
						%>
						</td></s:if>
					</tr>		
         			 		</table><!--Table 7-->
         			 	</td>
         			 </tr>					
				</table><!--Table 1-->
			</td>
		</tr><!--Schedule Test-->
	</table><!-- Final Table -->
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>
<script>

function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}
	
	function changeHeading(type)
	{	document.getElementById('paraFrm_myPage').value="";
		document.getElementById('paraFrm_statusFlag').value=type;
		document.getElementById('paraFrm').action = "InterviewDetails_showInterviewCandList.action?status="+type;
		document.getElementById('paraFrm').submit();
	}
	
	function canceledList(type)
	{	document.getElementById('paraFrm_myPage').value="";
		document.getElementById('paraFrm_statusFlag').value=type;
		document.getElementById('paraFrm').action = "InterviewDetails_showCancelList.action?status="+type;
		document.getElementById('paraFrm').submit();
	}
	
	function fromClearButton(listType) {
		document.getElementById('paraFrm').action = "InterviewDetails_showInterviewCandList.action?status="+listType;
		document.getElementById('paraFrm').submit();
		 
	}
	
	
	function conductValidation(listType)
	{
		var rowCount = <%= c%>;
		var flag = "false";
		for(i=1;i<rowCount;i++)
		{
			if(document.getElementById('selCand'+i).checked == true)
			{
				flag="true";
				break;
			}
		}
		if(flag=="false")
		{
			alert("Please select atleast one record");
			return false;
		}
		
		if(flag=="true") {
			document.getElementById('paraFrm').action = "ConductInterview_conductInterview.action?listType="+listType;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function showRequisition(reqCode,status){
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=InterviewDetails_showInterviewCandList.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
	}
	
	function viewRequisition(reqCode, status)
	{
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=InterviewDetails_showInterviewCandList.action&statusKey='+status;
		//document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=InterviewDetails_showInterviewCandList.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
	}
		
	function viewInterviewDetails(reqCode,candCode)
	{
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target ="_blank";
		document.getElementById('paraFrm').action = "InterviewDetails_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	}
	
	 function callPage(id,pageImg,status){  
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
		document.getElementById('paraFrm').action = "InterviewDetails_showInterviewCandList.action?status="+status;
		document.getElementById('paraFrm').submit(); 
	}	
	 


function callPageText(id,status){   
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
			document.getElementById('paraFrm').action = "InterviewDetails_showInterviewCandList.action?status="+status;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	function setRoundTypeCode(roundCode, hiddenCandidateEvalCode, interviewDtlCode) {
		document.getElementById('paraFrm_interviewRoundTypeConductInterviewID').value = roundCode;  
		document.getElementById('paraFrm_candidateEvaluationCode').value = hiddenCandidateEvalCode;  
		document.getElementById('paraFrm_interviewDetailCode').value = interviewDtlCode;  
	}
</script>