<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReimbAdminApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">



	<s:hidden name="myPageProcessed"  id="myPageProcessed"/>
	<s:hidden name="myPage" id="myPage" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Admin Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

<tr>
			<td colspan="10">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="10" class="text_head"><strong
						class="forminnerhead">Select Filters </strong></td>
				</tr>
				<tr valign="top">
					<td width="22%" nowrap="nowrap"><label name="application.id"
						id="application.id3" ondblclick="callShowDiv(this);"><%=label.get("application.id")%></label>
					<font color="red">*</font>:</td>
					<td width="28%" colspan="2"><s:textfield name="applIdSearch"
						theme="simple" size="25"  /></td>
					<td width="22%" nowrap="nowrap"><label name="employee"
						id="employee3" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="22%"><s:hidden name="empIdSearch"
						theme="simple" /> <s:hidden name="empTokenSearch"	theme="simple" /><s:textfield
						name="empNameSearch" theme="simple" size="25" readonly="true" />
						</td>
					<td width="6%">
					<img id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbAdminApproval_f9employeeSearch.action');">
						</td>
					 
				</tr>
				
				<tr valign="top"><td width="22%" nowrap="nowrap"><label name="reimbHead"
						id="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
					<td width="22%"><s:hidden name="reimbHeadSearch"/>
						<s:textfield name="reimbHeadNameSearch" theme="simple" size="25" readonly="true" />
					</td>
					<td width="6%">
					<img id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbAdminApproval_f9reimbHeadSearch.action');">
						</td>
					<td width="22%" nowrap="nowrap"><label name="claim.date"
						id="claim.date3" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
					<td width="22%"><s:textfield theme="simple"
							name="claimDateSearch" onkeypress="return numbersWithHiphen();"
							size="25" /></td>
					<td width="5%">
					<s:a href="javascript:NewCal('paraFrm_claimDateSearch','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16" id="ctrlHide">
						</s:a></td>
					<td width="20%" nowrap="nowrap"><a href="#" onclick="return callSearch();">Search</a> 	
					  <a href="#" style="display: inline;" onclick="return clearSearch();">Clear Search</a> 
					<!--<a href="#" id="hideLink" style="display: none;" onclick="showDiv();">Hide Expand Search</a> 
					--> </td>
				</tr>
 

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="" width="100%">
				<tr><s:hidden name="listType"/>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='ReimbAdminApproval_getAdminPendingList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }else if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='ReimbAdminApproval_getProcessedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Processed Applications</a> </td>
				</tr>
			</table>
			</td>
		</tr>

		<!--------- PENDING APPLICATION SECTION BEGINS - displaying the pending applications ------->

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Pending Reimbursement Application</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%">
							<%
								int totalPagePending = 0;
								int PageNoPending = 0;
							%>
								<s:if test="pendingLength">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPagePending = (Integer) request
 			.getAttribute("totalPage");
 	PageNoPending = (Integer) request.getAttribute("PageNo");
 %> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPagePending%>', 'ReimbAdminApproval_getAdminPendingList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPagePending%>', 'ReimbAdminApproval_getAdminPendingList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=PageNoPending%>"
									maxlength="10"
									onkeypress="callPageText(event, '<%=totalPagePending%>', 'ReimbAdminApproval_getAdminPendingList.action');return numbersOnly();" />
								of <%=totalPagePending%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPagePending%>', 'ReimbAdminApproval_getAdminPendingList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'ReimbAdminApproval_getAdminPendingList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
					</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>
										<td width="2%" valign="top" class="formth" ><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="20%" valign="top" class="formth"><label
											name="application.id" id="application.id"
											ondblclick="callShowDiv(this);"><%=label.get("application.id")%></label></td>
										<td width="30%" valign="top" class="formth"><label
											name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="claim.date" id="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="reimbHead" id="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
										<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
											name="claimAmt" id="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
										<td width="10%" valign="top" class="formth" nowrap="nowrap"><label 
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									<s:if test="pendingLength">
									<%
										 
										int cn = PageNoPending * 20 - 20;
									%>

									<s:iterator value="pendingList">

										<tr class="sortableTD">
											<td width="2%" align="left" class="sortableTD"><%=++cn%></td>
											<td class="sortableTD" width="20%"><s:property
												value="applRefNoList" /><s:hidden name="applRefNoList" /><s:hidden name="applIdList" /><s:hidden
												name="level" /></td>
											<td class="sortableTD" width="30%"><s:property
												value="empNameList" /></td>
											<td class="sortableTD" width="15%" 
												align="center"><s:property value="claimDateList" /><s:hidden
												name="claimDate" /></td>
											<td class="sortableTD" width="20%" 
												align="left"><s:property value="reimbHeadList" /><s:hidden
												name="reimbHeadList" /></td>
											<td class="sortableTD" width="10%" 
												align="right"><s:property value="claimAmt" /><s:hidden
												name="claimAmt" /></td>
											<td class="sortableTD" width="10%" ><input
												type="button" name="view_Details" class="token"
												value=" View / Approve "
												onclick="viewDetails('<s:property value="applIdList"/>','<s:property value="pendingStatus"/>')" />&nbsp;</td>


										</tr>
										 
									</s:iterator>
									</s:if>

									 <s:if test="pendingLength"></s:if>
									 <s:else>
									<tr align="center">
										<td colspan="7" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
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

			<!--------- PENDING APPLICATION SECTION ENDS - displaying the pending applications ------->

			
		</s:if>
		<s:if test="%{listType == 'processed'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Processed Applications List</b></td>
					</tr>
						<%
								int totalPageProcessed = 0;
								int PageNoProcessed = 0;
							%>
								<s:if test="processedLength">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
								totalPageProcessed = (Integer) request
 			.getAttribute("totalPageProcessed");
								PageNoProcessed = (Integer) request.getAttribute("PageNoProcessed");
 %> <a href="#"
									onclick="callPageProcessed('1', 'F', '<%=totalPageProcessed%>', 'ReimbAdminApproval_getProcessedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageProcessed('P', 'P', '<%=totalPageProcessed%>', 'ReimbAdminApproval_getProcessedList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField2"
									id="pageNoField2" size="3" value="<%=PageNoProcessed%>"
									maxlength="10"
									onkeypress="callPageTextProcessed(event, '<%=totalPageProcessed%>', 'ReimbAdminApproval_getProcessedList.action');return numbersOnly();" />
								of <%=totalPageProcessed%> <a href="#"
									onclick="callPageProcessed('N', 'N', '<%=totalPageProcessed%>', 'ReimbAdminApproval_getProcessedList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageProcessed('<%=totalPageProcessed%>', 'L', '<%=totalPageProcessed%>', 'ReimbAdminApproval_getProcessedList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
							</s:if>
					<tr>
						<td>
						<table width="100%">
							
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td width="2%" valign="top" class="formth" ><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="20%" valign="top" class="formth"><label
											name="application.id" id="application.id"
											ondblclick="callShowDiv(this);"><%=label.get("application.id")%></label></td>
										<td width="30%" valign="top" class="formth"><label
											name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="claim.date" id="claim.date" ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="reimbHead" id="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
										<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
											name="claimAmt" id="claimAmt" ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></td>
										<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
										<td width="10%" valign="top" class="formth" nowrap="nowrap"><label 
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									<s:if test="processedLength">
 								 	<%
									 
										int cn2 = PageNoProcessed * 20 - 20;
									%>

									<s:iterator value="processedList">
										<tr class="sortableTD">
											<td width="2%" align="left" class="sortableTD"><%=++cn2%></td>
											<td class="sortableTD" width="20%"><s:property
												value="applRefNoList" /><s:hidden name="applRefNoList" /><s:hidden name="applIdList" /><s:hidden
												name="level" /></td>
											<td class="sortableTD" width="30%"><s:property
												value="empNameList" /></td>
											<td class="sortableTD" width="15%" 
												align="center"><s:property value="claimDateList" /><s:hidden
												name="claimDate" /></td>
											<td class="sortableTD" width="20%" 
												align="left"><s:property value="reimbHeadList" /><s:hidden
												name="reimbHeadList" /></td>
											<td class="sortableTD" width="10%" 
												align="right"><s:property value="claimAmt" /><s:hidden
												name="claimAmt" /></td>
											<td class="sortableTD" width="10%" 
												align="right"><s:property value="applStatus" /><s:hidden
												name="applStatus" /></td>
											<td class="sortableTD" width="10%" ><input
												type="button" name="view_Details" class="token"
												value=" View "
												onclick="viewDetails('<s:property value="applIdList"/>','<s:property value="applStatus"/>')" />&nbsp;</td>


										</tr>
									 
									</s:iterator>
									 </s:if>
								 <s:if test="processedLength"></s:if>
								 <s:else>
									<tr align="center">
										<td colspan="8" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
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
		
	</table>

</s:form>


<script>
     
		    
		     function viewDetails(claimId,status)
		 {
		 
	    //var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		//document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').action = 'ReimbAdminApproval_retriveForApproval.action?claimId='+claimId;
		document.getElementById('paraFrm').submit();
		 //document.getElementById('paraFrm').target = "_self";
		}
	
	
	
	  function callSearch() 
 {
  	 	 if(document.getElementById('paraFrm_applIdSearch').value=="")
 		{
 			alert("Please enter Application Id ");
 			return false;
 		}
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReimbAdminApproval_searchFilter.action';
		document.getElementById('paraFrm').submit();

 }
 
 function clearSearch(){
 		document.getElementById('paraFrm_empIdSearch').value='';
 		document.getElementById('paraFrm_empNameSearch').value='';
 		document.getElementById('paraFrm_empTokenSearch').value='';
 		document.getElementById('paraFrm_applIdSearch').value='';
 		document.getElementById('paraFrm_claimDateSearch').value='';
 		document.getElementById('paraFrm_reimbHeadNameSearch').value='';
 		document.getElementById('paraFrm_reimbHeadSearch').value='';
 		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReimbAdminApproval_searchFilter.action';
		document.getElementById('paraFrm').submit();
 }
function callPageApproveCancel(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageApprovedPending').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField1').value = actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField1').value=actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField1').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApprovedPending').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextProcessedCancel(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField1').value;
		 	var actPage = document.getElementById('myPageApprovedPending').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField1').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField1').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApprovedPending').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	// FOR APPROVED APPLICATIONS LIST
	
	function callPageProcessed(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageProcessed').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField2').value = actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField2').value=actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField2').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageProcessed').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	function callPageTextProcessed(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageProcessed').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField2').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField2').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageProcessed').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
		    // REJECTED APPLICATIONS LIST
	
	function callPageRejected(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField3').value = actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField3').value=actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField3').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	function callPageTextReject(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField3').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField3').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField3').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
		    
</script>










		