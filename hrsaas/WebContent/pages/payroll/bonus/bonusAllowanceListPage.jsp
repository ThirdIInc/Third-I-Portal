<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="BonusAllowance" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="bonusAllowanceID" />
		<s:hidden name="bonusAllowanceStatus" />
		
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Bonus/Allowance
					List View</strong></td>
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
			<table width="100%">
				<td width="40%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<%
						int totalPage = 0;
						int pageNo = 0;
				%>
				<s:if test="initialRecordListFlag">
					<td id="ctrlShow" width="60%" align="right"><b>Page:</b> <%
									totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
 								%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'BonusAllowance_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'BonusAllowance_input.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'BonusAllowance_input.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'BonusAllowance_input.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'BonusAllowance_input.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
				</s:if>
			</table>
			</td>
		</tr>

		<tr>
			<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0" border="0">
				<tr>
					<td class="formth" width="5%"><label name="srNo" id="srNo"
						ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>

					<td class="formth" width="20%"><label
						name="bonusAllowancePeriod" id="bonusAllowancePeriod"
						ondblclick="callShowDiv(this);"><%=label.get("bonusAllowancePeriod")%></label>
					</td>

					<td class="formth" width="10%"><label name="processDate"
						id="processDate" ondblclick="callShowDiv(this);"><%=label.get("processDate")%></label>
					</td>

					<td class="formth" nowrap="nowrap" width="10%"><label
						name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					</td>

					<td class="formth" nowrap="nowrap" width="10%"><label
						name="paybill" id="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label>
					</td>

					<td class="formth" width="10%"><label
						name="totalBonusAllowance" id="totalBonusAllowance"
						ondblclick="callShowDiv(this);"><%=label.get("totalBonusAllowance")%></label>
					</td>

					<td class="formth" nowrap="nowrap" width="10%"><label
						name="headcount" id="headcount" ondblclick="callShowDiv(this);"><%=label.get("headcount")%></label>
					</td>

					<td class="formth" width="15%"><label name="paidInSalaryMonth"
						id="paidInSalaryMonth" ondblclick="callShowDiv(this);"><%=label.get("paidInSalaryMonth")%></label>
					</td>

					<td class="formth" width="10%"><label
						name="lockUnlock" id="lockUnlock" ondblclick="callShowDiv(this);"><%=label.get("lockUnlock")%></label>
					</td>
				</tr>

				<s:if test="initialRecordListFlag">
					<%
					int srNo = 0;
					%>
					<s:iterator value="initialRecordListIterator">
						<tr onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this);"
							ondblclick="javascript:callForEdit('<s:property value="bonusAllowanceIDItr"/>');">
							
							<s:hidden name="bonusAllowanceIDItr" />

							<td class="sortableTD" width="5%" align="center"><%=++srNo%>
							</td>

							<td class="sortableTD" width="20%">
								<s:hidden name="bonusAllowancePeriodItr" />
								<s:property value="bonusAllowancePeriodItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%" align="center">
								<s:hidden name="processDateItr" />
								<s:property value="processDateItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%">
								<s:hidden name="divisionIDItr" /> 
								<s:hidden name="divisionNameItr" /> 
								<s:property value="divisionNameItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%">
								<s:hidden name="payBillIDItr" />
								<s:property value="payBillNameItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%" align="right">
								<s:property value="totalBonusAllowanceItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%" align="right">
								<s:property value="headCountItr" />&nbsp;
							</td>

							<td class="sortableTD" width="15%">
								<s:property value="paidInSalaryMonthItr" />&nbsp;
							</td>

							<td class="sortableTD" width="10%">
								<s:property value="lockUnlockItr" />&nbsp;
							</td>
						</tr>
					</s:iterator>
				</s:if>

				<s:if test="initialEmptyListFlag">
					<tr>
						<td colspan="9" align="center"><font color="red">There
						is no data to display</font></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			   <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			 </td>
		</tr>
	</table>
</s:form>

<script>
function addnewFun() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='BonusAllowance_addNewRecord.action';
	document.getElementById('paraFrm').submit();
}		

function callForEdit(bonusCode) {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='BonusAllowance_callForEdit.action?bonusCode='+bonusCode;
	document.getElementById('paraFrm').submit();
}

function searchFun() {
	if(navigator.appName == 'Netscape') {
	  var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	}  else {
	  var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='BonusAllowance_searchProcessedRecords.action';
	document.getElementById("paraFrm").submit();
}

</script>