<!-- @author: Mangesh Jadhav 19 Jan 2011  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReimbProcess" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Process</strong></td>
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
      		<table width="100%" border="0" cellpadding="2" cellspacing="2">         
          		<tr>
            		<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
            		<td width="22%">
            			<div align="right"><font color="red">*</font> Indicates Required </div>
            		</td>
          		</tr>
        	</table>
      </td>
    </tr>
		<tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
				
					<td><a href="#" onclick="setAction('p')">Process List
					</a> | <a href="#" onclick="setAction('f')">Finalized List</a></td>
				</tr>
			
		
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPagePending = 0;
								int pageNoPending = 0;
					%>
					<s:if test="pendingLength">
						<tr>
							<td width="30%"><b>Process List</b></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPagePending = (Integer) request.getAttribute("totalPagePending");
 				pageNoPending = (Integer) request.getAttribute("pageNoPending");
 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPagePending%>', 'ReimbProcess_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPagePending%>', 'ReimbProcess_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNoPending%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPagePending%>', 'ReimbProcess_input.action');return numbersOnly();" />
							of <%=totalPagePending%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPagePending%>', 'ReimbProcess_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'ReimbProcess_input.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr >
						<s:hidden name="myPage" id="myPage" />
						<td width="5%" class="formth"><label class="set" id="srno"
							name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td width="25%" class="formth"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="reimbHead" name="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="processDate" name="processDate" ondblclick="callShowDiv(this);"><%=label.get("processDate")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="processFrom" name="processFrom" ondblclick="callShowDiv(this);"><%=label.get("processFrom")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="processTo" name="processTo" ondblclick="callShowDiv(this);"><%=label.get("processTo")%></label></td>

					</tr>
					<%int count = 0; %>
					<s:if test="pendingLength">
						<%
							int p = pageNoPending * 20 - 20;
							
						%>
						<s:iterator value="processList">
							<tr <%if(count%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:viewDetails('<s:property value="processCodeList"/>','UNLOCK')">
								<td width="5%" class="sortableTD" align="center"><%=++p%>
								<%++count;%>
								</td>
								<td width="25%" class="sortableTD" width="30%"><s:property
									value="divisionNameList" /><s:hidden name="processCodeList" /><s:hidden
									name="divisionNameList" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="reimbHeadList" /><s:hidden name="reimbHeadList" /><s:hidden name="reimbHeadCodeList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:property
									value="processDateList" /><s:hidden name="processDateList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="center"><s:property
									value="processFromList" /><s:hidden name="processFromList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="center"><s:property
									value="processToList" /><s:hidden name="processToList" /></td>

							</tr>

						</s:iterator>

					</s:if>
					<s:else>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
					<input type="hidden" name="count" id="count" value='<%=count%>' />
					</table></td></tr>
		</s:if>

		<s:if test="%{listType == 'locked'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPage = 0;
								int pageNo = 0;
					%>
					<s:if test="lockedLength">
						<tr>
							<td width="30%"><b>Locked List</b></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 				pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
								onclick="callPageLocked('1', 'F', '<%=totalPage%>', 'ReimbProcess_getLockedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageLocked('P', 'P', '<%=totalPage%>', 'ReimbProcess_getLockedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoFieldLocked" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageTextLocked(event, '<%=totalPage%>', 'ReimbProcess_getLockedList.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
								onclick="callPageLocked('N', 'N', '<%=totalPage%>', 'ReimbProcess_getLockedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageLocked('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbProcess_getLockedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr >
						<s:hidden name="myPageLocked" id="myPageLocked" />
						<td width="5%" class="formth"><label class="set" id="srno"
							name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td width="25%" class="formth"><label class="set" id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="reimbHead" name="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="processDate" name="processDate" ondblclick="callShowDiv(this);"><%=label.get("processDate")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="processFrom" name="processFrom" ondblclick="callShowDiv(this);"><%=label.get("processFrom")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="processTo" name="processTo" ondblclick="callShowDiv(this);"><%=label.get("processTo")%></label></td>

					</tr>
					<%int countLocked = 0; %>
					<s:if test="lockedLength">
						<%
							int pc = pageNo * 20 - 20;
							
						%>
						<s:iterator value="lockedList">
							<tr <%if(countLocked%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}countLocked++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=countLocked%2 %>);"
												ondblclick="javascript:viewDetails('<s:property value="processCodeList"/>','LOCK')">
								<td width="5%" class="sortableTD" align="center"><%=++pc%>
								<%++countLocked;%>
								</td>
								<td width="25%" class="sortableTD" width="30%"><s:property
									value="divisionNameList" /><s:hidden name="processCodeList" /><s:hidden
									name="divisionNameList" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="reimbHeadList" /><s:hidden name="reimbHeadList" /><s:hidden name="reimbHeadCodeList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="center"><s:property
									value="processDateList" /><s:hidden name="processDateList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="center"><s:property
									value="processFromList" /><s:hidden name="processFromList" /></td>
								<td width="15%" class="sortableTD" width="25%" align="center"><s:property
									value="processToList" /><s:hidden name="processToList" /></td>

							</tr>

						</s:iterator>

					</s:if>
					<s:else>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
					<input type="hidden" name="count" id="count" value='<%=countLocked%>' />
					</table></td></tr>

		</s:if>
		<s:hidden name="listType"></s:hidden>
		<s:hidden name="status"></s:hidden>
		<s:hidden name="divisionName"></s:hidden>
		<s:hidden name="reimbHeadName"></s:hidden>
		<s:hidden name="processDate"></s:hidden>
		<s:hidden name="fromMonthView"></s:hidden>
		<s:hidden name="toMonthView"></s:hidden>
		<s:hidden name="reimbYear"></s:hidden>
		<s:hidden name="processCode"></s:hidden>
		
		
	</table></td></tr></table>
</s:form>

<script>
	
	function searchFun(){
		callsF9(500,325,'ReimbProcess_f9Search.action');
	}
	function setAction(listType){
		if(listType=="p"){
			document.getElementById('paraFrm').action='ReimbProcess_input.action';
			document.getElementById('paraFrm').submit();
		} else if(listType=="f"){
			document.getElementById('paraFrm').action='ReimbProcess_getLockedList.action';
			document.getElementById('paraFrm').submit();
		 }
					     
	}
	
	function addnewFun(){
		document.getElementById('paraFrm').action='ReimbProcess_addNew.action';
		document.getElementById('paraFrm').submit();
    }
    	
	function newRowColor(cell)
   	{
		 cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		}
		else 
		cell.className='tableCell1';
	}
	
	function viewDetails(prCode,status){
		document.getElementById('paraFrm').action='ReimbProcess_viewDetails.action?processCode='+prCode+'&status='+status;
		document.getElementById('paraFrm').submit();
	}
function callPageLocked(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoFieldLocked').value;	
		actPage = document.getElementById('myPageLocked').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldLocked').value = actPage;
			    document.getElementById('pageNoFieldLocked').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldLocked').value=actPage;
			    document.getElementById('pageNoFieldLocked').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldLocked').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldLocked').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldLocked').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldLocked').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldLocked').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldLocked').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageLocked').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageTextLocked(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldLocked').value;
		 	var actPage = document.getElementById('myPageLocked').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldLocked').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldLocked').focus();
		     document.getElementById('pageNoFieldLocked').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldLocked').focus();
		     document.getElementById('pageNoFieldLocked').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldLocked').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageLocked').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
</script>