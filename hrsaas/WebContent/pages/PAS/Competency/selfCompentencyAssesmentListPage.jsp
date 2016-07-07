<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="SelfCompentencyAssesment" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Self Competency Assessment</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td><s:hidden name="listType" />
			<s:hidden name="myPendingPage" id="myPendingPage" />
			<s:hidden name="myProcessedPage" id="myProcessedPage" />
			<s:hidden name="source" id="source" />
			  <table class="" width="100%">
				<tr>
					<script>
		    			function setAction(listType){
		     				if (listType=="pending") {
		      					document.getElementById('paraFrm').action='SelfCompentencyAssesment_input.action';
		      					document.getElementById('paraFrm').submit();
		     				} if(listType=="processed") {
		      					document.getElementById('paraFrm').action='SelfCompentencyAssesment_getProcessedList.action';
		      					document.getElementById('paraFrm').submit();
		     				} 
		   			 	}
		   			</script>
					<td>
						<a href="#" onclick="setAction('pending')">Pending Application</a> | 
						<a href="#" onclick="setAction('processed')">Processed Application</a>  
				</tr>
			</table>
			</td>
		</tr>
			
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<s:if test="%{listType == 'pending'}">
					<tr>
						<td colspan="2">
							<b>Pending Application</b>
						</td>
						
						<td colspan="4" align="right">
						<%
							int totalPendingPage = 0;
							int pendingPageNo = 0;
						%> 
						<s:if test="pendingRecordListFlag">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalPendingPage = (Integer) request.getAttribute("totalPendingPage");
									pendingPageNo = (Integer) request.getAttribute("pendingPageNo");
									%> <a href="#"
										onclick="callPagePending('1', 'F', '<%=totalPendingPage%>', 'SelfCompentencyAssesment_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPagePending('P', 'P', '<%=totalPendingPage%>', 'SelfCompentencyAssesment_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pendingPageNoField"
										id="pendingPageNoField" size="3" value="<%=pendingPageNo%>"
										maxlength="10"
										onkeypress="callPagePendingText(event, '<%=totalPendingPage%>', 'SelfCompentencyAssesment_input.action');return numbersOnly();" />
									of <%=totalPendingPage%> <a href="#"
										onclick="callPagePending('N', 'N', '<%=totalPendingPage%>', 'SelfCompentencyAssesment_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPagePending('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'SelfCompentencyAssesment_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
									</td>
								</tr>
							</table>
						</s:if>
					  </td>
					</tr>
				</s:if>
				
				<s:if test="%{listType == 'processed'}">
					<tr>
						<td colspan="2">
							<b>Processed Application</b>
						</td>
						
						<td colspan="4" align="right">
						<%
							int totalProcessedPage = 0;
							int processedPageNo = 0;
						%> 
						<s:if test="processedRecordListFlag">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalProcessedPage = (Integer) request.getAttribute("totalProcessedPage");
									processedPageNo = (Integer) request.getAttribute("processedPageNo");
									%> <a href="#"
										onclick="callPageProcessed('1', 'F', '<%=totalProcessedPage%>', 'SelfCompentencyAssesment_getProcessedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageProcessed('P', 'P', '<%=totalProcessedPage%>', 'SelfCompentencyAssesment_getProcessedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="processedPageNoField"
										id="processedPageNoField" size="3" value="<%=processedPageNo%>"
										maxlength="10"
										onkeypress="callPageProcessedText(event, '<%=totalProcessedPage%>', 'SelfCompentencyAssesment_getProcessedList.action');return numbersOnly();" />
									of <%=totalProcessedPage%> <a href="#"
										onclick="callPageProcessed('N', 'N', '<%=totalProcessedPage%>', 'SelfCompentencyAssesment_getProcessedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageProcessed('<%=totalProcessedPage%>', 'L', '<%=totalProcessedPage%>', 'SelfCompentencyAssesment_getProcessedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
									</td>
								</tr>
							</table>
						</s:if>
					  </td>
					</tr>
				</s:if>
				<tr>
					<td class="formth" nowrap="nowrap" width="5%">
						<label name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label> 
					</td>
					<td class="formth" nowrap="nowrap" width="35%">
						<label name="competencyCalender" id="competencyCalender" ondblclick="callShowDiv(this);"><%=label.get("competencyCalender")%></label>   
					</td>
					<td class="formth" nowrap="nowrap" width="15%">
						<label name="fromDate" id="fromDate" ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label> 
					</td>
					<td class="formth" nowrap="nowrap" width="15%">
						<label name="toDate" id="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label>  
					</td>
					<td class="formth" nowrap="nowrap" width="15%">
						<label name="reviewDate" id="reviewDate" ondblclick="callShowDiv(this);"><%=label.get("reviewDate")%></label>
					</td>
					<td class="formth" nowrap="nowrap" width="10%">
						<label name="action" id="action" ondblclick="callShowDiv(this);"><%=label.get("action")%></label>  
					</td>
				</tr>
		
	<!-- Pending List Data BEGINS -->	
		<s:if test="pendingRecordListFlag">		
			<% int srNo = 0;%>
			<s:iterator value="pendingRecordList"> 
				<tr><s:hidden name="competencyIDItr"/>
					<s:hidden name="competencyAssementIDItr"/>
					<td class="sortableTD" nowrap="nowrap" width="5%" align="center"><%= ++srNo %></td>
					<td class="sortableTD" width="40%"> <s:property value="competencyNameItr"/>  </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyFromDate"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyToDate"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyReviewDateItr"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="10%" align="center">
						<input type="button" name="Process" value="Process" class="token" onclick="getCompetencyDetails('<s:property value="competencyIDItr"/>','<s:property value="competencyAssementIDItr"/>','<s:property value="competencyNameItr"/>');">
					</td>
				</tr>
			</s:iterator>
		</s:if>
		
		<s:if test="pendingListEmptyFlag">
			<tr> 
				<td colspan="5" align="center">
					<font color="red">There is no data to display</font>
				</td>
			</tr>
		</s:if>
	<!-- Pending List Data ENDS -->	
		
	<!-- Processed List Data BEGINS -->		
		<s:if test="processedRecordListFlag">	
			<% int processSrNo = 0;%>
			<s:iterator value="processedRecordList"> 
				<tr><s:hidden name="competencyIDItr"/>
					<s:hidden name="competencyAssementIDItr"/>
					<td class="sortableTD" nowrap="nowrap" width="5%" align="center"><%= ++processSrNo %></td>
					<td class="sortableTD" width="40%"> <s:property value="competencyNameItr"/>  </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyFromDate"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyToDate"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="15%" align="center"> <s:property value="competencyReviewDateItr"/> </td>
					<td class="sortableTD" nowrap="nowrap" width="10%" align="center">
						<input type="button" name="view" value="View Details" class="token" onclick="viewProcessedDetails('<s:property value="competencyIDItr"/>','<s:property value="competencyAssementIDItr"/>');">
					</td>
				</tr>
			</s:iterator>
		</s:if>
			
		<s:if test="processListEmptyFlag">
			<tr> 
				<td colspan="5" align="center">
					<font color="red">There is no data to display</font>
				</td>
			</tr>
		</s:if>
	<!-- Pending List Data BEGINS -->			
			</table>
			</td>
		</tr>
	 
	</table>
</s:form>

<script>
function getCompetencyDetails(competencyIDItr, competencyAssementIDItr, competencyNameItr) {
		var con = confirm("Do you really want to process this record?");
		if (con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='SelfCompentencyAssesment_getCompetencyDetails.action?competencyIDItr='+competencyIDItr+'&competencyAssementIDItr='+competencyAssementIDItr+'&competencyNameItr='+competencyNameItr;
			document.getElementById('paraFrm').submit();
		} else {
			 return false;
		}
}
	
function viewProcessedDetails(competencyIDItr, competencyAssementIDItr) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='SelfCompentencyAssesment_viewProcessedDetails.action?competencyIDItr='+competencyIDItr+'&competencyAssementIDItr='+competencyAssementIDItr;
			document.getElementById('paraFrm').submit();
}	

// Function for Pending List Begins
function callPagePending(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pendingPageNoField').value;	
		actPage = document.getElementById('myPendingPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pendingPageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pendingPageNoField').value = actPage;
			    document.getElementById('pendingPageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pendingPageNoField').value=actPage;
			    document.getElementById('pendingPageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pendingPageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pendingPageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pendingPageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pendingPageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pendingPageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pendingPageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPendingPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
}


function callPagePendingText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pendingPageNoField').value;
		 	var actPage = document.getElementById('myPendingPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pendingPageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pendingPageNoField').value=actPage;  
		      document.getElementById('pendingPageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pendingPageNoField').value=actPage;  
		     document.getElementById('pendingPageNoField').focus();
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pendingPageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPendingPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
}
// Function Pending List Ends

// Function for Processed List Begins
function callPageProcessed(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('processedPageNoField').value;	
		actPage = document.getElementById('myProcessedPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('processedPageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('processedPageNoField').value = actPage;
			    document.getElementById('processedPageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('processedPageNoField').value=actPage;
			    document.getElementById('processedPageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('processedPageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('processedPageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('processedPageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('processedPageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('processedPageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('processedPageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myProcessedPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
}


function callPageProcessedText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('processedPageNoField').value;
		 	var actPage = document.getElementById('myProcessedPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('processedPageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('processedPageNoField').focus();
		     document.getElementById('processedPageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('processedPageNoField').focus();
		     document.getElementById('processedPageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('processedPageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myProcessedPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
}
// Function Approved List Ends

</script>