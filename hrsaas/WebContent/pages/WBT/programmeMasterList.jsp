 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ProgrammeMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="programeId" />
	<s:hidden name="programeName" />
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Program
					Master </strong></td>
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
			<table border="0" cellpadding="2" cellspacing="0" align="left">
				<tr>
					<td width="2%"><s:submit name="addNew" value="Add New"
						onclick="addNewFun();" /></td>
					<td width="60%"><s:submit name="Search" value="Search"
						onclick="searchFun();" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="modeLength">
						<td id="ctrlShow" width="38%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ProgrammeMaster_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ProgrammeMaster_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ProgrammeMaster_input.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ProgrammeMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ProgrammeMaster_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortableTD">
						<tr>
							<td class="formth" align="center" width="5%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<td class="formth" align="center" width="15%"><label
								class="set" name="programe" id="programe"
								ondblclick="callShowDiv(this);"><%=label.get("programe")%></label></td>
							<td class="formth" align="center" width="15%"><label
								class="set" name="typeLbl" id="typeLbl"
								ondblclick="callShowDiv(this);"><%=label.get("typeLbl")%></label></td>
							<td class="formth" align="center" width="20%"><label
								class="set" name="instruction" id="instruction"
								ondblclick="callShowDiv(this);"><%=label.get("instruction")%></label></td>
							<td class="formth" align="center" width="20%"><label
								class="set" name="modules" id="modules"
								ondblclick="callShowDiv(this);"><%=label.get("modules")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="dur" id="dur" ondblclick="callShowDiv(this);"><%=label.get("dur")%></label></td>
							<td class="formth" align="center" width="5%"><label
								class="set" name="active" id="active"
								ondblclick="callShowDiv(this);"><%=label.get("active")%></label></td>
							<td class="formth" align="center" width="10%"><label
								class="set" name="preview" id="preview"
								ondblclick="callShowDiv(this);"><%=label.get("preview")%></label></td>
							<s:if test="modeLength">
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
									int i = 0;
									int cn = pageNo * 20 - 20;
								%>
								<s:iterator value="programmeList">
									<tr class="sortableTD" 
										<%if(count%2==0){%> class="tableCell1" <%}else{%>
										class="tableCell2" <%}count++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										>
										<td width="10%" align="center" class="sortableTD"><%=++cn%>
										<%
										++i;
										%><s:hidden name="srNo" />&nbsp;</td>
										<s:hidden value="%{programeId}" id='<%="programeId" + i%>' />
										<script type="text/javascript">
												records[<%=i%>] = document.getElementById('programeId' + '<%=i%>').value;
										</script>
										<td width="40%" align="left" class="sortableTD">
										<u><a href="javascript:void(0)" onclick="javascript:callForEdit('<s:property value="programeId"/>');" >
										<s:property
											value="programeName" /></a></u></td>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="type" />&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><a
											href="ProgrammeMaster_setInstruction.action?programeId=<s:property value="programeId"/>&from=programlist">
										<font color="blue"><u>Manage</u></font></a>&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><a
											href="ProgrammeMaster_callForEdit.action?programeId=<s:property value="programeId"/>"><font
											color="blue"><u>Manage</u></font></a>&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="duration" />&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="isActive" />&nbsp;</td>
										<td width="10%" align="center" class="sortableTD">
											<a href="javascript:openPreview(<s:property value="programeId"/>)" >
											<img border="0" 
											src="../pages/WBT/images/Preview.png" 
											align="absmiddle"  >
											</a>
										</td>
									</tr>

								</s:iterator>
								<%
								d = i;
								%>
							</s:if>
						</tr>
					</table>
					<s:if test="modeLength"></s:if> <s:else>
						<table width="100%">
							<tr>
								<td align="center"><font color="red">No Data To
								Display</font></td>
							</tr>
						</table>
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		 <s:hidden name="EmpID" />
		<tr>
			<td>
			<table border="0" cellpadding="2" cellspacing="2" align="left">
				<tr>
					<td width="2%"><s:submit name="addNew" value="Add New"
						onclick="addNewFun();" /></td>
					<td width="2%"><s:submit name="Search" value="Search"
						onclick="searchFun();" /></td>
					<td width="96%" align="Right"><s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function addNewFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ProgrammeMaster_addNew.action';
		document.getElementById('paraFrm').submit();
}

function oldRowColor(cell,val) {
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
	}
	
function newRowColor(cell) {
		 cell.className='Cell_bg_first'; 
}
	
function callForEdit(id){
	  	document.getElementById('paraFrm_programeId').value = id;
	   	document.getElementById("paraFrm").action = "ProgrammeMaster_callForEdit.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
 function searchFun() {
	if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ProgrammeMaster_f9action.action';
		document.getElementById("paraFrm").submit();
}

function openPreview(programId){
	if(navigator.appName == 'Netscape') {
		var win = window.open('', 'win', 'top = 150, left = 200, width = 1024, height = 750, scrollbars = yes, status = yes, resizable = yes');
	} else {
		var win = window.open('', 'win', 'top = 230, left = 200, width = 1024, height = 750, scrollbars = yes, status = yes, resizable = yes');
	}

	document.getElementById("paraFrm").target = 'win';
	document.getElementById("paraFrm_EmpID").value="1";
	document.getElementById("paraFrm").action ='OnlineProgram_startTest.action?ProgramCode='+ programId +'&EmpID=1&userType=T&DateTime=11/21/2012 4:34:17 PM&INSTANCE_NAME=DEVGOLIVE';
	document.getElementById("paraFrm").submit();
}
</script>