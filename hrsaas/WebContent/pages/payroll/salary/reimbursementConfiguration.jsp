<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ReimbursementConfiguaration" id="paraFrm" validate="true" theme="simple">

	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tbody>
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<%
							int totalPage = 0;
							int pageNo = 0;
						%>
						<td width="30%" align="right" class="" id="ctrlShow"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
							
						%> <a
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ReimbursementConfiguaration_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ReimbursementConfiguaration_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a> <input type="text"
							onkeypress="callPageText(event, '1', 'ReimbursementConfiguaration_input.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField"
							name="pageNoField"> of <%=totalPage%> <a
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ReimbursementConfiguaration_input.action')"
							href="#"> <img class="iconImage"
							src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
						<a
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbursementConfiguaration_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/last.gif" title="Last Page"> </a></td>
					</tr>
			</table>
		</tr>
		<tr>
			<td>
		
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="5%"><label class="set"
								id="srNo" name="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<td class="formth" align="center"><label class="set"
								id="accountEmpId1" name="accountEmpId"
								ondblclick="callShowDiv(this);"><%=label.get("accountName")%></label></td>
							<td class="formth" align="center" width="40%"><label class="set"
								id="accountDivId1" name="accountDivId"
								ondblclick="callShowDiv(this);"><%=label.get("accountDivision")%></label></td>
							<td align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete" theme="simple" value="Delete"
								onclick=" return chkDelete();" /></td>
							<%int count=0; %>
							<%!	int d = 0; %>
									<%	int i = 0; int cn = pageNo * 20 - 20; %>

							<s:iterator value="accountantListArray">

								<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="accountantEmpIdItt" />','<s:property value="accountantDivIdItt" />');">
									<td class="sortableTD" align="left"><%=++cn%><%++i;%>
									</td>
									<td  class="sortableTD" align="left">
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />	
									<input type="hidden" name="hdeleteDivCode" id="hdeleteDivCode<%=i%>" />	
									<input type="hidden" value='<s:property value="accountantEmpIdItt"/>' />
									<s:property value="accountantEmpNameItt" />
									<input type="hidden" value='<s:property value="accountantDivIdItt"/>' />
									<td  class="sortableTD"><s:property value="accountantDivNameItt" /></td>
									<td align="center" class="sortableTD" id="ctrlShow">
										<input type="checkbox"
											   class="sortableTD" id="checkedBox<%=i%>" name="checkedBox"
											   onclick="callForDelete1('<s:property value="accountantEmpIdItt"/>','<s:property value="accountantDivIdItt"/>','<%=i%>');" /></td>
								</tr>
							</s:iterator>
							<%	d = i; %>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tbody>
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					</tr>
			</table>
		</tr>
	</table>
	
	<s:hidden name="hiddenEmpId" />
	<s:hidden name="accountantEmpId" />
	<s:hidden name="accountantEmpName" />
	<s:hidden name="accountantDivId" />
	<s:hidden name="accountantDivName" />
	<s:hidden name="accountantEmpToken" />
</s:form>

<script>
	function addnewFun() {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="ReimbursementConfiguaration_addNew.action";
		document.getElementById("paraFrm").submit();
	}
	
	function searchFun() {
	
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action="ReimbursementConfiguaration_f9action.action";
		document.getElementById("paraFrm").submit();
	}
	
	function newRowColor(cell){
 
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
	 		cell.className='tableCell2';
		}else {
			cell.className='tableCell1';
		}
	}
	
	function callForEdit(autoCode,divCode){
	 callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action="ReimbursementConfiguaration_callForEdit.action?autoCode="+autoCode+"&divCode="+divCode;
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
		 
	}
	
	function callForDelete1(accountantId,DivId,i) {
	
		if(document.getElementById('checkedBox'+i).checked == true)
		{
			document.getElementById('hdeleteCode'+i).value=accountantId;
			document.getElementById('hdeleteDivCode'+i).value=DivId;
		} else {
			document.getElementById('hdeleteCode'+i).value="";
			document.getElementById('hdeleteDivCode'+i).value="";
		}
	}
	
	function chkDelete() {
		var selected=false;
			var flag = <%=d%>;
			
			for(var check=1;check <= flag;check++) {
				if(document.getElementById('checkedBox'+check).checked){
					selected = true;
				}else{
				}
			}
			if(selected){
				var con=confirm('Do you really want to delete the records ?');
	 				if(con){
	 					document.getElementById('paraFrm').target = "_self";
						document.getElementById("paraFrm").action="ReimbursementConfiguaration_deleteMultipleRecordsFromList.action";
						document.getElementById("paraFrm").submit();
					}
					}else{
						alert("Select atleast one record to delete");
						return false;
					}
		
	}
	
	
	

</script>