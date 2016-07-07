<!--Bhushan Dasare-->
<!--August 21, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="EmployeeSurvey" name="EmployeeSurvey" id="paraFrm"
	validate="true" target="main" theme="simple">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head"><label
						id="empSurvey.label" name="empSurvey.label"
						ondblclick="callShowDiv(this);"><%=label.get("empSurvey.label")%></label></strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">

				<s:if test="dataExists">
					<tr>
						<td width="100%" align="right" class="formbg"><b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'EmployeeSurvey_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'EmployeeSurvey_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'EmployeeSurvey_callPage.action'); return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'EmployeeSurvey_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmployeeSurvey_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" alt="" /> </a></td>
					</tr>
				</s:if>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td align="center" class="formth" width="10%"><label
								id="lbSrNo" name="lbSrNo" ondblclick="callShowDiv(this);"><%=label.get("empSurvey.SrNo")%></label>
							</td>
							<td align="center" class="formth" width="50%"><label
								id="lbSrNo" name="lbSrNo" ondblclick="callShowDiv(this);"><%=label.get("empSurvey.surveyName")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								id="lbSrNo" name="lbSrNo" ondblclick="callShowDiv(this);"><%=label.get("empSurvey.surveyFromDate")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								id="lbSrNo" name="lbSrNo" ondblclick="callShowDiv(this);"><%=label.get("empSurvey.surveyToDate")%></label>
							</td>
						</tr>
						<s:if test="dataExists">
							<%
								int count = 0;
								int i = 0;
							%>
							<s:iterator value="surveyList">
								<tr class="sortableTD" title="Double click for edit"
									<%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="surveyCode"/>');">
									<td class="sortableTD" nowrap="nowrap"><s:hidden
										value="%{surveyCode}" id='<%="surveyCode" + i%>' /><s:property
										value="srNo" /></td>
									<td class="sortableTD" nowrap="nowrap"><s:property
										value="surveyName" /></td>
									<td class="sortableTD" nowrap="nowrap" align="center"><s:property
										value="surveyFromDate" /></td>
									<td class="sortableTD" nowrap="nowrap" align="center"><s:property
										value="surveyToDate" /></td>
								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="4" align="center"><font color="red">No
								Data to Display</font></td>
							</tr>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="hiddencode" />
</s:form>

<script>
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
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="EmployeeSurvey_callSurveyPage.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
</script>