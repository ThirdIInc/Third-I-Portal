<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>

<s:form action="AutoPresentAttendance" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="show" value="%{show}" /><s:hidden name="hiddencode" /><s:hidden name="eToken" /><s:hidden name="empName" /><s:hidden name="empId" />
	<s:hidden name="isNewrecord" /><s:hidden name="waiveOffLate" /><s:hidden name="waiveOffHalfday" /><s:hidden name="waiveOffAbsent" />
	<s:hidden name="designation" />
	<table width="100%" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" class="formbg">
					<tr>
						<td>
							<strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25"/></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Auto Present Attendance</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
		<%	int totalPage = 0, pageNo = 0; %>
		<s:if test="listLength">
			<tr>
				<td id="ctrlShow" width="100%" align="right" class="formbg">
					<b>Page:</b>
					<%	totalPage = (Integer)request.getAttribute("totalPage");
 						pageNo = (Integer)request.getAttribute("pageNo");
 					%>
 					<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
 					</a>&nbsp;
 					<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" />
 					</a>
 					<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10" 
 					onkeypress="callPageText(event, '<%=totalPage%>', 'AutoPresentAttendance_callPage.action'); return numbersOnly();" />
 					of <%=totalPage%>
 					<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action')">
 						<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
 					</a>&nbsp;
 					<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" alt="" />
 					</a>
 				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="3">
				<%	try { %>
				<table width="100%" cellpadding="2" cellspacing="2" class="formbg">
					<tr>
						<td class="formtext">
							<table width="100%" cellpadding="1" cellspacing="1">
								<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" width="5%">
										<label id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
									</td>
									<td class="formth" width="10%">
										<label id="etoken" name="etoken" ondblclick="callShowDiv(this);"><%=label.get("etoken")%></label>
									</td>
									<td class="formth" width="25%">
										<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									</td>
									<td class="formth" width="15%" align="center">
										<label id="desg" name="desg" ondblclick="callShowDiv(this);"><%=label.get("desg")%></label>
									</td>
									<td class="formth" width="15%" nowrap="nowrap">
										<label id="waive1" name="waive1" ondblclick="callShowDiv(this);"><%=label.get("waive1")%></label>
									</td>
									<td class="formth" width="15%" nowrap="nowrap">
										<label id="waive2" name="waive2" ondblclick="callShowDiv(this);"><%=label.get("waive2")%></label>
									</td>
									<td class="formth" width="15%" nowrap="nowrap">
										<label id="waive3" name="waive3" ondblclick="callShowDiv(this);"><%=label.get("waive3")%></label>
									</td>
									<s:if test="listLength">
										<td align="center" class="formth" width="10%">
											<input type="button" id="ctrlShow" class="delete" value="Delete" onclick="return chkDelete();" /><br>
											
											<input type="checkbox" id="ctrlShow" style="cursor: hand;" title="Select all records to remove" 
											onclick="selectAllRecords(this);" />
										</td>
									</s:if>
									<s:if test="listLength">
										<%!	int d = 0; %>
										<%	int count = 0; int i = 0; int cn = pageNo * 20 - 20; %>
										<s:iterator value="empList">
											<tr title="Double click for edit" 
											<%	if(count % 2 == 0) { %> class="tableCell1" <% } else { %> class="tableCell2" <% } count++; %>
											onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this, <%=count % 2%>);" 
											ondblclick="javascript:callForEdit('<s:property value="LempId"/>');" >
												<td width="5%" align="center" class="sortableTD">
													<%=++cn%><%++i;%><s:hidden name="srNo" />
												</td>
												<td class="sortableTD" width="10%" nowrap="nowrap">
													<s:property value="empToken" />
													
													<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
													
													<s:hidden value="%{LempId}" id='<%="LempId" + i%>' />
												</td>
												<script type="text/javascript">
													records[<%=i - 1%>] = document.getElementById('LempId' + '<%=i%>').value;
												</script>
												<td class="sortableTD" width="25%" nowrap="nowrap">
													<s:property value="empName" />
												</td>
												<td class="sortableTD" width="25%" nowrap="nowrap">
													<s:property value="empDesg" />
												</td>
												<td class="sortableTD" width="15%" nowrap="nowrap">
													<s:property value="LwaiveOffLate" />
												</td>
												<td class="sortableTD" width="15%" nowrap="nowrap">
													<s:property value="LwaiveOffHalfday" />
												</td>
												<td class="sortableTD" width="15%" nowrap="nowrap">
													<s:property value="LwaiveOffAbsent" />
												</td>
												<td class="sortableTD" id="ctrlShow" align="center" width="10%">
													<input type="checkbox" class="checkbox" id="confChk<%=i%>" name="confChk" 
													onclick="callForDelete1('<s:property value='LempId'/>', '<%=i%>')" />
												</td>
											</tr>
										</s:iterator>
										<%	d = i; i = 0; %>
									</s:if>
								</tr>
							</table>
							<s:if test="listLength"></s:if>
							<s:else>
								<table width="100%">
									<tr>
										<td align="center"><font color="red">No Data To Display</font></td>
									</tr>
								</table>
							</s:else>
						</td>
					</tr>
				</table>
			<%	} catch(Exception e) {} %>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<td width="79%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right">
						<s:if test="listLength">
							<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
						</s:if>
					</td>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AutoPresentAttendance_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='AutoPresentAttendance_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'AutoPresentAttendance_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	function oldRowColor(cell, val) {
		if(val == '1') {
	 		cell.className='tableCell2';
		} else {
			cell.className='tableCell1';
		}
	}
	
	function newRowColor(cell) {
		cell.className='Cell_bg_first'; 
	}
		
	function callForEdit(id) {
  		document.getElementById('paraFrm_enableAll').value = 'N';
  		
	  	document.getElementById('paraFrm_hiddencode').value = id;
	   	document.getElementById("paraFrm").action = "AutoPresentAttendance_calforedit.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
	}

	function callForDelete1(id, i) {
		var flag = '<%=d%>';
		if(document.getElementById('confChk' + i).checked == true) {
			document.getElementById('hdeleteCode' + i).value = id;
	   	} else {
	   		document.getElementById('hdeleteCode' + i).value = "";
	   	}
	}
	
	function chkDelete() {	 
		if(chk()) {
			var con = confirm('Do you want to delete the record(s)?');
	 		if(con) {
	   			document.getElementById('paraFrm').action = "AutoPresentAttendance_delete1.action";
	   			document.getElementById('paraFrm').target = "_self";
	    		document.getElementById('paraFrm').submit();
	    	} else {
	    		var flag = '<%=d%>';
	  			for(var a = 1; a <= flag; a++) {	
					document.getElementById('confChk' + a).checked = false;
					document.getElementById('hdeleteCode' + a).value = "";
				}
				return false;
	 		}
	 	} else {
	 		alert('Please select atleast one record');
	 		return false;
	 	}
	}
	
	function chk() {
		var flag = '<%=d%>';
	  	for(var a = 1; a <= flag; a++) {	
	   		if(document.getElementById('confChk' + a).checked == true) {
				return true;
	   		}
	  	}
	  	return false;
	}
	
	function selectAllRecords(object) {
		var flag = '<%=d%>';
	 	try {
			if(object.checked) {
				for(var i = 1; i <= records.length; i++) {			
					document.getElementById('confChk' + i).checked = true;
					document.getElementById('hdeleteCode' + i).value = records[i];
				}
			} else {
				for(var i = 1; i <= records.length; i++) {			
					document.getElementById('confChk' + i).checked = false;
					document.getElementById('hdeleteCode' + i).value = "";
				}
			}
		} catch(e) {}
	}
	
	function callForDelete1(id, i) {
		var flag = '<%=d%>';
		
		if(document.getElementById('confChk' + i).checked == true) {
			document.getElementById('hdeleteCode' + i).value = id;
		} else {
			document.getElementById('hdeleteCode' + i).value = "";
		}
	}

	function chkDelete() {
		if(chk()) {
			var con = confirm('Do you want to delete the record(s)?');
			
			if(con) {
				document.getElementById('paraFrm').action = "AutoPresentAttendance_delete1.action";
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').submit();
			} else {
				var flag = '<%=d %>';
	  			for(var a = 1; a <= flag; a++) {
					document.getElementById('confChk' + a).checked = false;
					document.getElementById('hdeleteCode' + a).value = "";
				}
	     		return false;
	 		}
	 	} else {
			alert('Please select atleast one record');
	 		return false;
	 	}
	}
	
	function chk() {
		var flag = '<%=d%>';
		for(var a = 1; a <= flag; a++) {	
	   		if(document.getElementById('confChk' + a).checked == true) {
				return true;
			}
		}
		return false;
	}

   	function newRowColor(cell) {
		cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell, val) {	
		if(val == '1') {
			cell.className = 'tableCell2';
		} else {
			cell.className='tableCell1';
		}
	}
  	
  	function callDelete(id) {
		if(document.getElementById(id).value == "") {
			alert("Please select a record to delete");
			return false;
		}
      	
      	var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			return true;
  		}
	  	return false;
	}

   function callSearch(action) {
		myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'AutoPresentAttendance_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
</script>