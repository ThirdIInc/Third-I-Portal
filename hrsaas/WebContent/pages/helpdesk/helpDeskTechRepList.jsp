<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="HelpDeskTechReporting" validate="true" id="paraFrm"
	validate="true" theme="simple">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg" width="100%">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Team</strong></td>
					<td width="3%" valign="top" align="right">
					<img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<% int count = 0;
			int i = 0;%>
		<%!int d = 0;%>
		<%	int totalPage = 0;
			int pageNo = 0;
		%>
		 
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
				<tr>
									<s:if test="modeLength">
									<%	 totalPage = (Integer) request.getAttribute("totalPage");
											 pageNo = (Integer) request.getAttribute("pageNo");
										%>
										</s:if>
										
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right"><b>Page:</b> 
					<a href="#"	onclick="callPage('1', 'F', '<%=totalPage%>', 'HelpDeskTechReporting_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'HelpDeskTechReporting_input.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'HelpDeskTechReporting_input.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'HelpDeskTechReporting_input.action');">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HelpDeskTechReporting_input.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
					<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td colspan="2" class="txt"><strong class="text_head">
							Helpdesk Team Member List</strong></td>
							<td align="center"><input type="button" class="delete"
								id="ctrlShow" value=" Delete" onclick="return chkDelete();" /></td>
						</tr>
						<tr>
							<td class="formth" align="center"><label class="set"
								name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
							</td>
							<td class="formth" width="80%" align="center"><label
								class="set" name="manager" id="manager1"
								ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
							</td>
							<td align="center" class="formth"><input type="checkbox"
								id="selAll" style="cursor: hand;" title="Select all"
								onclick="selectAllRecords(this);" /></td>
						</tr>
						<%
						int cnt = pageNo * 20 - 20;
						%>
						<s:iterator value="managerList">
							<tr id="tr1" <%if(count%2==0){	%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property  value="managerIdItt"/>');">
								<td width="10%" align="center" class="sortableTD"><%=++cnt%>
								<%
								++i;
								%>
								</td>
								<td align="left" class="sortableTD"><input type="hidden"
									name="hdeleteCode" id="hdeleteCode" /> <s:hidden
									name="managerIdItt" /> <s:hidden name="managerTokenItt" /> <s:hidden
									value="%{managerIdItt}" id='<%="managerIdItt" + i%>' /> <s:property
									value="managerNameItt" /> <script type="text/javascript">
									records[<%=i%>] = document.getElementById('managerIdItt' + '<%=i%>').value;
									</script></td>
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<td id="ctrlShow" align="center" class="sortableTD"><input
									type="checkbox" class="checkbox" id="confChk<%=i%>"
									name="confChk"
									onclick="callForDelete1('<s:property  value="managerIdItt"/>','<%=i%>')" /></td>
							</tr>
						</s:iterator>
						<%
						d = i;
						%>
						<s:if test="modeLength">
						</s:if>
						<s:else>
							<tr>
								<td align="center" colspan="3"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td align="right"><b>Total No. Of Records:</b>&nbsp;
			<s:property	value="totalRecords" /></td>
		</tr>
	</table>
	<s:hidden name="hiddenManagerId" />
	<s:hidden name="managerToken" />
	<s:hidden name="managerName" />
	<s:hidden name="managerCode" />
	<s:hidden name="technicianToken" />
	<s:hidden name="technicianName" />
	<s:hidden name="technicianCode" />
</s:form>
<script>
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDeskTechReporting_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='HelpDeskTechReporting_f9Action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		} else { cell.className='tableCell1';
		}
	}
	
	function newRowColor(cell) {
		 cell.className='Cell_bg_first'; 
	}
	
	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddenManagerId').value=id;
	   	document.getElementById('paraFrm').target = "_self";
	   	document.getElementById("paraFrm").action="HelpDeskTechReporting_calforedit.action";
	    document.getElementById("paraFrm").submit();
   	}
   
    function callForDelete1(id,i){
	   var flag='<%=d %>';
	   if(document.getElementById('confChk'+i).checked == true) {	  
	    	document.getElementById('hdeleteCode'+i).value=id;
	   }else {
	   		document.getElementById('hdeleteCode'+i).value="";
	   }
   	}
	function chkDelete(){
		if(chk()){
			var con=confirm('Do you want to delete the record(s) ?');
			if(con){
				document.getElementById('paraFrm').action="HelpDeskTechReporting_deleteList.action";
	   			document.getElementById('paraFrm').target = "_self";
	    		document.getElementById('paraFrm').submit();
	    } else  { 
			document.getElementById('selAll').checked = false;
	    	var flag='<%=d %>';
	  		for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hdeleteCode'+a).value="";
			}
	     	return false;
	 	}
	 } else {
		 alert('Please select atleast one record');
	 	return false;
	 }
	}
	
	function chk(){
		var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   		if(document.getElementById('confChk'+a).checked == true){	
			    return true;
	   		}	   
	  	}
	  	return false;
	}
	
	function selectAllRecords(object) {
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
	}
	function chkDelete() {
	 if(chk()){
	 	var con=confirm('Do you want to delete the record(s) ?');
	 	if(con){
	   		document.getElementById('paraFrm').action="HelpDeskTechReporting_deleteList.action";
	   		document.getElementById('paraFrm').target = "_self";
	   		document.getElementById('paraFrm').submit();
	    } else { 
			document.getElementById('selAll').checked = false;
	    	var flag='<%=d %>';
	  		for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hdeleteCode'+a).value="";
			}
	     return false;
	 	}
	 } else {
	 alert('please select atleast one record');
	 return false;
	 }
	}
</script>
