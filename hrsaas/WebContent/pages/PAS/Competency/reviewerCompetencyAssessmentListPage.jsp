<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReviewerCompetencyAssessment" validate="true"
	id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reviewer
					Competency Assessment</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
				<s:hidden name="listType" />
				<s:hidden name="myPage" id="myPage" />
				<s:hidden name="myProcessedPage" id="myProcessedPage" />
				<s:hidden name="source" id="source" />
				<table class="" width="100%">
					<tr>
						<script>
		    				function setAction(listType){
		     					if (listType=="pending") {
		      						document.getElementById('paraFrm').action='ReviewerCompetencyAssessment_input.action';
		      						document.getElementById('paraFrm').submit();
		     					} if(listType=="processed") {
		      						document.getElementById('paraFrm').action='ReviewerCompetencyAssessment_getProcessedList.action';
		      						document.getElementById('paraFrm').submit();
		     					} 
		   			 		}
		   				</script>
						<td>
							<a href="#" onclick="setAction('pending')">Pending Reviewer Competency Assessment</a> | 
							<a href="#" onclick="setAction('processed')">Processed Reviewer Competency Assessment</a>
						</td>	
					</tr>
				</table>
			</td>
		</tr>

<!-- Pending Reviewer Competency Assessment List - BEGINS -->
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td width="30%" align="left"><strong class="text_head">
						Pending Reviewer Competency Assessment</strong></td>
						<td width="70%" align="right">
						<%
							int totalPendingPage = 0;
							int pendingPageNo = 0;
						%> 
						<s:if test="pendingListLengthFlag">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalPendingPage = (Integer) request
												.getAttribute("totalPendingPage");
										pendingPageNo = (Integer) request.getAttribute("pendingPageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'ReviewerCompetencyAssessment_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'ReviewerCompetencyAssessment_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pendingPageNo%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalPendingPage%>', 'ReviewerCompetencyAssessment_input.action');return numbersOnly();" />
									of <%=totalPendingPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'ReviewerCompetencyAssessment_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'ReviewerCompetencyAssessment_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
									</td>
								</tr>
							</table>
						</s:if>
					  </td>
					</tr>

					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" class="formth" align="center"><label
											class="set" name="srNo" id="srNo"
											ondblclick="callShowDiv(this);"> <%=label.get("srNo")%></label>
										</td>

										<td class="formth" align="center" width="20%"><label
											class="set" name="competencyCalender" id="competencyCalender"
											ondblclick="callShowDiv(this);"> <%=label.get("competencyCalender")%></label>
										</td>

										<td class="formth" align="center" width="30%"><label
											class="set" name="employeeName" id="employeeName"
											ondblclick="callShowDiv(this);"> <%=label.get("employeeName")%></label>
										</td>

										<td class="formth" align="center" width="20%"><label
											class="set" name="reviewDate" id="reviewDate"
											ondblclick="callShowDiv(this);"> <%=label.get("reviewDate")%></label>
										</td>
										
										<td class="formth" align="center" width="20%"><label
											class="set" name="department" id="department"
											ondblclick="callShowDiv(this);"> <%=label.get("department")%></label>
										</td>
										
										<td class="formth" align="center" width="20%"><label
											class="set" name="designation" id="designation"
											ondblclick="callShowDiv(this);"> <%=label.get("designation")%></label>
										</td>

										<td class="formth" align="center" width="%20"><label
											class="set" name="action" id="action"
											ondblclick="callShowDiv(this);"> <%=label.get("action")%></label>
										</td>
									</tr>

									<s:if test="pendingListLengthFlag">
										<%
										int i = pendingPageNo * 20 - 20;
										%>
										<s:iterator value="pendingIteratorList">
											<tr>
												<s:hidden name="competencyIDItr" />
												<s:hidden name="competencyAssesmentIDItr" />
												<s:hidden name="competencyAssesmentEmployeeIDItr" />
												<s:hidden name="competencyAssesmentLevelItr" />
												<td class="sortableTD" nowrap="nowrap" width="5%" align="center"><%=++i%></td>
												
												<td class="sortableTD" width="20%">
													<s:property value="competencyNameItr" />
												</td>
												
												<td class="sortableTD" nowrap="nowrap" width="20%" align="center">
													<s:property value="competencyAssesmentEmployeeNameItr" />
												</td>
												
												<td class="sortableTD" nowrap="nowrap" width="15%" align="center">
													<s:property value="competencyAssesmentReviewDateItr" />
												</td>
												
												<td class="sortableTD" width="15%" align="center">
													<s:property value="competencyAssesmentDepartmentItr" />
												</td>
												
												<td class="sortableTD" width="15%" align="center">
													<s:property value="competencyAssesmentDesignationItr" />
												</td>
												
												<td class="sortableTD" width="10%" align="center">
													<input type="button" name="Process" value="Process" class="token"
														onclick="getCompetencyDetails('<s:property value="competencyIDItr"/>','<s:property value="competencyAssesmentIDItr"/>','<s:property value="competencyAssesmentEmployeeIDItr"/>','<s:property value="competencyAssesmentLevelItr"/>');">
												</td>
											</tr>
										</s:iterator>
									</s:if>

									<s:else>
										<td align="center" colspan="6" nowrap="nowrap"><font
											color="red">There is no data to display</font></td>
									</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
<!-- Pending Reviewer Competency Assessment List - ENDS -->

<!-- Processed Reviewer Competency Assessment List - BEGINS -->
		<s:if test="%{listType == 'processed'}">
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td width="30%" align="left"><strong class="text_head">
						Processed Reviewer Competency Assessment</strong></td>
						<td width="70%" align="right">
						<%
							int totalProcessedPage = 0;
							int processedPageNo = 0;
						%> 
						<s:if test="processedListLengthFlag">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalProcessedPage = (Integer) request
												.getAttribute("totalProcessedPage");
										processedPageNo = (Integer) request.getAttribute("processedPageNo");
									%> <a href="#"
										onclick="callPageProcessed('1', 'F', '<%=totalProcessedPage%>', 'ReviewerCompetencyAssessment_getProcessedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageProcessed('P', 'P', '<%=totalProcessedPage%>', 'ReviewerCompetencyAssessment_getProcessedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="processedPageNoField"
										id="processedPageNoField" size="3" value="<%=processedPageNo%>"
										maxlength="10"
										onkeypress="callPageProcessedText(event, '<%=totalProcessedPage%>', 'ReviewerCompetencyAssessment_getProcessedList.action');return numbersOnly();" />
									of <%=totalProcessedPage%> <a href="#"
										onclick="callPageProcessed('N', 'N', '<%=totalProcessedPage%>', 'ReviewerCompetencyAssessment_getProcessedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageProcessed('<%=totalProcessedPage%>', 'L', '<%=totalProcessedPage%>', 'ReviewerCompetencyAssessment_getProcessedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
									</td>
								</tr>
							</table>
						</s:if>
					  </td>
					</tr>

					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" class="formth" align="center"><label
											class="set" name="srNo" id="srNo"
											ondblclick="callShowDiv(this);"> <%=label.get("srNo")%></label>
										</td>

										<td class="formth" align="center" width="20%"><label
											class="set" name="competencyCalender" id="competencyCalender"
											ondblclick="callShowDiv(this);"> <%=label.get("competencyCalender")%></label>
										</td>

										<td class="formth" align="center" width="30%"><label
											class="set" name="employeeName" id="employeeName"
											ondblclick="callShowDiv(this);"> <%=label.get("employeeName")%></label>
										</td>

										<td class="formth" align="center" width="20%"><label
											class="set" name="reviewDate" id="reviewDate"
											ondblclick="callShowDiv(this);"> <%=label.get("reviewDate")%></label>
										</td>
										
										<td class="formth" align="center" width="20%"><label
											class="set" name="department" id="department"
											ondblclick="callShowDiv(this);"> <%=label.get("department")%></label>
										</td>
										
										<td class="formth" align="center" width="20%"><label
											class="set" name="designation" id="designation"
											ondblclick="callShowDiv(this);"> <%=label.get("designation")%></label>
										</td>

										<td class="formth" align="center" width="%20"><label
											class="set" name="action" id="action"
											ondblclick="callShowDiv(this);"> <%=label.get("action")%></label>
										</td>
									</tr>

									<s:if test="processedListLengthFlag">
										<%
										int j = processedPageNo * 20 - 20;
										%>
										<s:iterator value="processedIteratorList">
											<tr>
												<s:hidden name="competencyIDItr" />
												<s:hidden name="competencyAssesmentIDItr" />
												<s:hidden name="competencyAssesmentEmployeeIDItr" />
												<s:hidden name="competencyAssesmentLevelItr" />
												<td align="center" class="sortableTD"><%=++j%></td>

												<td class="sortableTD" width="20%"><s:property
													value="competencyNameItr" /></td>

												<td class="sortableTD" nowrap="nowrap" width="20%"
													align="center"><s:property
													value="competencyAssesmentEmployeeNameItr" /></td>
												
												<td class="sortableTD" nowrap="nowrap" width="15%"
													align="center"><s:property
													value="competencyAssesmentReviewDateItr" /></td>
												
												<td class="sortableTD" width="15%" align="center"><s:property
													value="competencyAssesmentDepartmentItr" /></td>
												
												<td class="sortableTD" width="15%" align="center"><s:property
													value="competencyAssesmentDesignationItr" /></td>
												
												<td class="sortableTD" width="10%" align="center"><input
													type="button" name="view Details" value="View Details" class="token"
													onclick="callProcessedCompetencyDetails('<s:property value="competencyIDItr"/>','<s:property value="competencyAssesmentIDItr"/>','<s:property value="competencyAssesmentEmployeeIDItr"/>','<s:property value="competencyAssesmentLevelItr"/>');">
												</td>
											</tr>
										</s:iterator>
									</s:if>

									<s:else>
										<td align="center" colspan="7" nowrap="nowrap" class="sortableTD"><font
											color="red">There is no data to display</font></td>
									</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
<!-- Processed Reviewer Competency Assessment List - ENDS -->

	</table>
</s:form>

<script>
function getCompetencyDetails(competencyIDItr,competencyAssessmentIDItr,competencyAssessmentEmployeeIDItr, competencyAssessmentLevelItr) {
		var con = confirm("Do you really want to process this application?");	
		if(con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='ReviewerCompetencyAssessment_getCompetencyDetails.action?competencyIDItr='+competencyIDItr+'&competencyAssessmentIDItr='+competencyAssessmentIDItr+'&competencyAssessmentEmployeeIDItr='+competencyAssessmentEmployeeIDItr+'&competencyAssessmentLevelItr='+competencyAssessmentLevelItr;
			document.getElementById('paraFrm').submit();
		} else {
			return false;
		}
}  
function callProcessedCompetencyDetails(competencyIDItr,competencyAssessmentIDItr,competencyAssessmentEmployeeIDItr, competencyAssessmentLevelItr) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='ReviewerCompetencyAssessment_getCompetencyDetails.action?competencyIDItr='+competencyIDItr+'&competencyAssessmentIDItr='+competencyAssessmentIDItr+'&competencyAssessmentEmployeeIDItr='+competencyAssessmentEmployeeIDItr+'&competencyAssessmentLevelItr='+competencyAssessmentLevelItr;
		document.getElementById('paraFrm').submit();
}  


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