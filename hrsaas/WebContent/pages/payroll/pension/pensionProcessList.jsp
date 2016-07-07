<!--@author REEBA_JOSEPH @date 11-10-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="PensionProcess" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<s:hidden name="listLength" />

	<s:hidden name="penProcess.monthView" />
	<s:hidden name="penProcess.year" />
	<s:hidden name="penProcess.divName" />
	<s:hidden name="penProcess.status" />
	<s:hidden name="penProcess.ledgerCode" />
	<s:hidden name="penProcess.month" />
	<s:hidden name="penProcess.divCode" />
	<s:hidden name="flag" />
	<s:hidden name="penProcess.status" />
	<!--<s:hidden name="brnCode" />
	<s:hidden name="payinSalFlag"/>

	--><table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pension Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" align="left" colspan="3">
			<table width="100%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <!--<s:submit action="SalaryProcess_addNew" value="Add New" cssClass="token"  
							onclick="callAddNew()" /> 
							<input type="button" class="search" value="    Search " 
							onclick="callsF9(500,325,'SalaryProcess_f9action.action');" />-->
					</td>
					<%
						int totalPage = 0;
							int pageNo = 0;
					%>
					<s:if test="listLength">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'PensionProcess_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'PensionProcess_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="listPageNoField" id="listPageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'PensionProcess_input.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'PensionProcess_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PensionProcess_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td class="txt" colspan="2"><strong class="text_head">Pension
					Process </strong></td>
					<td colspan="1" align="right"></td>
				</tr>
				<tr>
					<td colspan="3">
					<%
						try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">
						<tr class="sortableTD">
							<s:hidden name="listMyPage" id="listMyPage" />
							<td class="formth"><label class="set" name="serial.no"
								id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
							</td>
							<td class="formth"><label class="set"
								name="pension.month" id="pension.month1"
								ondblclick="callShowDiv(this);"><%=label.get("pension.month")%></label>
							</td>
							<td class="formth"><label class="set" name="pension.year"
								id="pension.year1" ondblclick="callShowDiv(this);"><%=label.get("pension.year")%></label>
							</td>
							<td class="formth"><label class="set" name="division"
								id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							</td>
							<td class="formth"><label class="set" name="pension.status"
								id="pension.status1" ondblclick="callShowDiv(this);"><%=label.get("pension.status")%></label>
							</td>
							<%
								int count = 0;
							%>
							<%!int d = 0;%>
							<%
								int i = 0;
										int cnt = pageNo * 20 - 20;
							%>
						</tr>
						<s:iterator value="iteratorlist">
							<tr <%if(count%2==0){
												%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								title="Double click for edit"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="listPensionCode"/>','<s:property value="listMonth"/>',
																'<s:property value="listMonthCode"/>','<s:property value="listYear"/>',
																'<s:property value="listDivName"/>',
																'<s:property value="listDivId"/>','<s:property value="listPensionStatus"/>');">
								<script type="text/javascript">
													records[<%=i%>] = document.getElementById('listPensionCode' + '<%=i%>').value;									
												</script>
								<!-- SNO -->
								<td width="5%" align="center" class="sortableTD"><%=++cnt%>
								<%
									++i;
								%>
								</td>
								<s:hidden value="%{listPensionCode}"
									id='<%="listPensionCode" + i%>'></s:hidden>
								<!-- MONTH -->
								<td width="20%" align="left" class="sortableTD"><s:property
									value="listMonth" /> <s:hidden
									name="listMonth" /> <s:hidden
									name="listMonthCode" /></td>
								<!-- YEAR -->
								<td width="20%" align="center" class="sortableTD"><s:property
									value="listYear" /></td>
								<!-- DIVISION -->
								<td width="30%" align="left" class="sortableTD"><s:property
									value="listDivName" />&nbsp; <s:hidden name="listDivId" /></td>
								<!-- STATUS -->
								<td width="15%" align="left" class="sortableTD"><s:property
									value="listPensionStatus" /> <s:hidden name="listPensionStatus" />
								</td>
							</tr>
						</s:iterator>
						<%
							d = i;
						%>
					</table>
					<s:if test="listLength"></s:if> <s:else>
						<table width="100%">
							<tr>
								<td align="center"><font color="red">No Data To
								Display</font></td>
							</tr>
						</table>
					</s:else> <%
 	} catch (Exception e) {
 		}
 %>
					</td>
				</tr>
				<s:if test="listLength">
					<tr>

						<td align="right"><b>Total No. of Records: <s:property
							value="totalRecords" /></b></b></td>

					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
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
	
	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='PensionProcess_addNew.action';
		document.getElementById("paraFrm").submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'PensionProcess_f9ShowRecord.action';
		document.getElementById("paraFrm").submit();
	}
	
	
	function callForEdit(pensionCode, month, monthCode, year, divname,divid,status) 
	{
		document.getElementById("paraFrm_penProcess_ledgerCode").value = pensionCode;
		document.getElementById("paraFrm_penProcess_monthView").value = month;
		document.getElementById("paraFrm_penProcess_month").value = monthCode;
		document.getElementById("paraFrm_penProcess_year").value = year;
		document.getElementById("paraFrm_penProcess_divName").value = divname;
		document.getElementById("paraFrm_penProcess_divCode").value = divid;
		document.getElementById("paraFrm_penProcess_status").value = status;
		document.getElementById("paraFrm").action="PensionProcess_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	}
	
	function callPage(id, pageImg, totalPageHid, action) {		
		pageNo = document.getElementById('listPageNoField').value;	
		actPage = document.getElementById('listMyPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('listPageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('listPageNoField').value = actPage;
			    document.getElementById('listPageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('listPageNoField').value=actPage;
			    document.getElementById('listPageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('listPageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('listPageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('listPageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('listPageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('listPageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('listPageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('listMyPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('listPageNoField').value;
		 	var actPage = document.getElementById('listMyPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('listPageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('listPageNoField').focus();
		     document.getElementById('listPageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('listPageNoField').focus();
		     document.getElementById('listPageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('listPageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('listMyPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
</script>