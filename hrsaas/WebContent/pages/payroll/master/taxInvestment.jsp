<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TaxInv" id="paraFrm" validate="true" theme="simple">
<s:hidden name="myPage" id="myPage" />
<s:hidden name="fromYear"/>
<s:hidden name="toYear"/>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Tax Configuration </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<tr>
			<td colspan="3">
			<table width="100%" border="0">
				<tr>
					<td width="70%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="30%" align="right" class="" id="ctrlShow"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a
							onclick="callPage('1', 'F', '<%=totalPage%>', 'TaxInv_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'TaxInv_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a> <input type="text"
							onkeypress="callPageText(event, '1', 'TaxInv_input.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField"
							name="pageNoField"> of <%=totalPage%> <a
							onclick="callPage('N', 'N', '<%=totalPage%>', 'TaxInv_input.action')"
							href="#"> <img class="iconImage"
							src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
						<a
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TaxInv_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/last.gif" title="Last Page"> </a></td>
				</tr>
				<tr>
			<td colspan="3">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td colspan="3"><strong>
							Tax Investment Period</strong>
						</td>
					</tr>
					<tr>
						<td class="formth">
							<label class="set" id="srNo"
							name="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label>
						</td>
						<td class="formth">
							<label class="set" id="frmYr2"
							name="frmYr"><%=label.get("frmYr")%></label>
						</td>
						<td class="formth">
							<label class="set" id="toYr2"
							name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label>
						</td>
					</tr>
					<%
						int count = 0;
					%>
					<%
						int cnt = pageNo * 20 - 20;
					%>

						<s:iterator value="investmentPeriodList">
							<tr <%if(count%2==0){
									%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callEdit('<s:property value="investmentFromYearListItt" />','<s:property value="investmentToYearListItt" />');">
								<td class="sortableTD" align="center"><%=++cnt%> 
									</td>
								<td class="sortableTD" align="center">
									<s:property value="investmentFromYearListItt"/></td>
								<td class="sortableTD" align="center">
									<s:property value="investmentToYearListItt"/></td>
							</tr>
						</s:iterator>
					<%if(cnt==0){%>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					<%}%>	
				</table>
			</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>

<script>
	function addnewFun(){
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').action='TaxInv_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	function searchFun(){
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById('paraFrm').action='TaxInv_f9action.action';
		document.getElementById('paraFrm').submit();
	}
	function callEdit(frmYear, toYr){
		document.getElementById('paraFrm').action='TaxInv_edit.action?frmYear='+frmYear+'&toYr='+toYr;
		document.getElementById('paraFrm').target = 'main';
		document.getElementById('paraFrm').submit();
	}
</script>

