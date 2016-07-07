<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>

<s:form action="EmpTypeMaster" name="EmpTypeMaster" id="paraFrm"
	validate="true" target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="typMaster.typeID" />
	<s:hidden name="typMaster.typeName" />

	<s:hidden name="report" />
	<s:hidden name="reportAction" value='EmpTypeMaster_getReport.action' />

	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Type</strong></td>
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
					<td width="18%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="82%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
				<tr>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
				</tr>
				
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>
		

		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0" id="reportBodyTable">
				<tr>
					<s:if test="recordsAvailable">
						<td id="ctrlShow" width="100%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'EmpTypeMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'EmpTypeMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'EmpTypeMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'EmpTypeMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmpTypeMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a>
						</td>
					</s:if>

					<td align="right"><input type="button" id="ctrlShow"
						class="delete" value=" Delete" onclick="return chkDelete();" /></td>
				</tr>
				<tr>
					<td width="100%" class="formbg" colspan="3">
					<table width="100%" class="sortableTD">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td width="10%" align="center" class="formth"><b><label
								name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
							</td>
							<td width="45%" align="center" class="formth"><b><label
								name="employee.type" id="employee.type1"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></b>
							</td>
							<td width="40%" align="center" class="formth"><b><label
								name="typeabbr" id="typeabbr1" ondblclick="callShowDiv(this);"><%=label.get("typeabbr")%></label></b>
							</td>

							<td class="formth" align="center"><label name="is.active"
								id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>

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
							<s:iterator value="typeList">
								<tr onmouseover="javascript: newRowColor(this);"
									onmouseout="javascript: oldRowColor(this);"
									ondblclick="javascript: callForEdit('<s:property value="typeID"/>');"
									style="cursor: hand;">
									<td title="Double click for edit" width="10%"
										class="sortableTD" align="center"><%=++cn%> <% ++i; %> <s:hidden
										name="srNo" /></td>
									<s:hidden id='<%="typeID" + i%>' value="%{typeID}" />
									<script type="text/javascript">
												records[<%=i%>] = document.getElementById('typeID' + '<%=i%>').value;
											</script>
									<td title="Double click for edit" width="40%"
										class="sortableTD"><s:property value="typeName" /> <input
										type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
									<td title="Double click for edit" width="50%"
										class="sortableTD">&nbsp;<s:property value="typeAbbr" />
									</td>

									<td class="sortableTD" width="50%">&nbsp; <s:property
										value="isActive" /></td>

									<td id="ctrlShow" align="center" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick=" callForDelete1('<s:property value="typeID"/>', '<%=i%>')" />
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
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="18%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="100%" colspan="6"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
					
				</tr>
				<tr>
				<td></td>
				<s:if test="recordsAvailable">
						<td align="right"><b>Total No. of Records:<s:property value="totalRecords" /></b></b></td>
				</s:if>
				</tr>
			</table>
		</td>			
	</tr>
		<!--<tr>
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
	--><tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>
<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
</s:form>

<script>
	window.onload = document.getElementById('pageNoField').focus();
	
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'EmpTypeMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'EmpTypeMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'EmpTypeMaster_report.action';
		document.getElementById('paraFrm').submit();
	}

	function callForEdit(id) {
		callButton('NA', 'Y', 2);
		
		document.getElementById('paraFrm_hiddencode').value = id;
	   	document.getElementById("paraFrm").action = 'EmpTypeMaster_calforedit.action';
	   	document.getElementById('paraFrm').target = '_self';
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
		 		document.getElementById('paraFrm').target = "_self";
		   		document.getElementById('paraFrm').action = "EmpTypeMaster_delete1.action";
		    	document.getElementById('paraFrm').submit();
			} else {	    
		    	var flag = '<%=d%>';
				document.getElementById('selAll').checked = false;
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
		cell.className = 'onOverCell';
	}
	
	function oldRowColor(cell) {
		cell.className = 'tableCell1';
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
	
	//Added by Tinshuk Banafar
	
	function callReport(type)
 {	
	try{
	
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
	
		 }
		 catch (e)
	    {
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
				
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='EmpTypeMaster_mailReport.action';
		document.getElementById('paraFrm').submit();
			//return true;
		}
		
		
</script>