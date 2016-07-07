<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>

<s:form action="RoundTypeMaster" name="RoundTypeMaster" id="paraFrm"
	validate="true" target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="roundCode" />
	<s:hidden name="roundType" />

	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Round
					Type Master</strong></td>
					<td width="3%" valign="middle" class="txt" align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="recordsAvailable">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'RoundTypeMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'RoundTypeMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'RoundTypeMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'RoundTypeMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'RoundTypeMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0">
				<tr>
					<td colspan="2" class="txt"><strong class="text_head">Round
					Type Master</strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" value=" Delete" onclick="return chkDelete();" /></td>
				</tr>
				<tr>
					<td width="100%" class="formbg" colspan="3">
					<table width="100%" class="sortableTD">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td width="10%" align="center" class="formth"><b><label
								name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b>
							</td>
							<td width="45%" align="center" class="formth"><b><label
								class="set" name="round.Type" id="round.Type1"
								ondblclick="callShowDiv(this);"><%=label.get("round.Type")%></label></b>
							</td>

							<s:if test="recordsAvailable">
								<td width="5%" align="center" class="formth" id="ctrlShow">
								<input type="checkbox" id="selAll" style="cursor: hand;"
									title="Select all records to delete"
									onclick="selectAllRecords(this);" /></td>
							</s:if>
						</tr>
						<s:if test="recordsAvailable">
							<%!int d = 0;%>
							<%
								int i = 0;
								int cn = pageNo * 20 - 20;
							%>
							<s:iterator value="roundTypeMasterItt">
								<tr onmouseover="javascript: newRowColor(this);"
									onmouseout="javascript: oldRowColor(this);"
									ondblclick="javascript: callForEdit('<s:property value="ittRoundTypeCode"/>');"
									style="cursor: hand;">
									<td title="Double click for edit" width="10%"
										class="sortableTD" align="center"><%=++cn%> <%
 ++i;
 %><s:hidden name="ittSrN0" /></td>
									<s:hidden id='<%="ittRoundTypeCode" + i%>'
										value="%{ittRoundTypeCode}" />
									<script type="text/javascript">
												records[<%=i%>] = document.getElementById('ittRoundTypeCode' + '<%=i%>').value;
											</script>
									<td title="Double click for edit" width="40%"
										class="sortableTD"><s:property value="ittRoundType" /> <input
										type="hidden" name="hidCode" id="hidCode<%=i%>" /></td>

									<td id="ctrlShow" align="center" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick=" callForDelete1('<s:property value="ittRoundTypeCode"/>', '<%=i%>')" />
									</td>
								</tr>
							</s:iterator>
							<%
							d = i;
							%>
						</s:if>
					</table>
					<s:if test="recordsAvailable"></s:if> <s:else>
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
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="recordsAvailable">
						<td align="right"><b>Total No. of Records: <s:property
							value="totalRecords" /></b></b></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	window.onload = document.getElementById('pageNoField').focus();
	
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'RoundTypeMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'RoundTypeMaster_search.action';
		document.getElementById("paraFrm").submit();
	}
	
	

	function callForEdit(id) {
		callButton('NA', 'Y', 2);
		
		document.getElementById('paraFrm_hiddencode').value = id;
	   	document.getElementById("paraFrm").action = 'RoundTypeMaster_dblClickItt.action';
	   	document.getElementById('paraFrm').target = '_self';
		document.getElementById("paraFrm").submit();
   	}

	function callForDelete1(id, i) {
	   	var flag = '<%=d%>';
	 	if(document.getElementById('confChk' + i).checked == true) {
	    	document.getElementById('hidCode' + i).value = id;
	   	} else {
	   		document.getElementById('hidCode' + i).value = "";
	   	}
   	}

	function chkDelete() {
		try{
		if(chk()) {
		 	var con = confirm('Do you want to delete the record(s)?');
		 	if(con) {
		 		document.getElementById('paraFrm').target = "_self";
		   		document.getElementById('paraFrm').action = "RoundTypeMaster_deleteCheck.action";
		    	document.getElementById('paraFrm').submit();
			} else {	    
		    	var flag = '<%=d%>';
				document.getElementById('selAll').checked = false;
		  		for(var a = 1; a <= flag; a++) {	
					document.getElementById('confChk' + a).checked = false;
					document.getElementById('hidCode' + a).value = "";
				}
		     	return false;
		 	}
		} else {
	 		alert('Please select atleast one record');
	 		return false;
	 	}
	 	}catch(e){alert(e);}
	}
	
	function chk() {
		try{
		var flag = '<%=d%>';
		//alert(flag);
	  	for(var a = 1; a <= flag; a++) {
	   		if(document.getElementById('confChk' + a).checked == true) {
	  			return true;
	   		}
	  	}
	  	}catch(e){alert(e);}
	  	return false;
	}

	function newRowColor(cell) {
		cell.className = 'onOverCell';
	}
	
	function oldRowColor(cell) {
		cell.className = 'tableCell1';
	}
  	
	function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hidCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hidCode' + i).value = "";
			}
		}
	}
</script>