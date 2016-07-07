<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VendorReportStructure" id="paraFrm" theme="simple"
	validate="true">
	 <s:hidden name="modeLength"/>
	 <s:hidden name="hiddenPartnerID"/>	
	 <s:hidden name="hiddenApproverID"/>
	 <s:hidden name="reportingID"/>
	<table width="100%" cellpadding="0" cellspacing="0" border="0"
		align="right" class="formbg">
		<tr>
			<td class="txt" valign="bottom">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vendor
					Reporting Structure </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><s:submit name="addNew" value=" AddNew"
				onclick=" addNewFun();" /></td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<%
					int totalPageReport = 0;
					int pageNoReport = 0;
				%>

				<s:if test="modeLength">
					<tr>
						<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	totalPageReport = (Integer) request.getAttribute("totalPage");
 	pageNoReport = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPageReport%>', 'VendorReportStructure_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPageReport%>', 'VendorReportStructure_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField2" id="pageNoField2" size="3"
							value="<%=pageNoReport%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPageReport%>', 'VendorReportStructure_input.action');return numbersOnly();" />
						of <%=totalPageReport%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPageReport%>', 'VendorReportStructure_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPageReport%>', 'L', '<%=totalPageReport%>', 'VendorReportStructure_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>

			<td>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" cellpadding="1" cellspacing="1"
						class="sortable" border="0">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label class="set"
								name="srno" id="srno1" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="partnerNm" id="partnerNm1" ondblclick="callShowDiv(this);"><%=label.get("partnerNm")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="approverNm" id="approverNm1"
								ondblclick="callShowDiv(this);"><%=label.get("approverNm")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="approver" id="approver1" ondblclick="callShowDiv(this);"><%=label.get("approver")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="lblLevel" id="lblLevel1" ondblclick="callShowDiv(this);"><%=label.get("lblLevel")%></label>
							</td>
							<s:if test="modeLength">
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
									int i = 0;
									int cn = pageNoReport * 20 - 20;
								%>
								<s:iterator value="reportStrList">
									<tr class="sortableTD" title="Double click for edit"
										<%if(count%2==0){
																%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="reportingID"/>');">
										<td width="10%" align="center" class="sortableTD"><%=++cn%><%++i;%><s:hidden name="srNo" />&nbsp;</td>
										<s:hidden value="%{partnerCodeItt}" id='<%="partnerCodeItt" + i%>' />
										<script type="text/javascript">
											records[<%=i%>] = document.getElementById('partnerCodeItt' + '<%=i%>').value;
										</script>
										<td width="30%" align="left" class="sortableTD"><s:property
											value="partnerNameItt" /> <input type="hidden"
											name="hdeleteCode" id="hdeleteCode<%=i%>" />&nbsp;</td>
										<td width="50%" align="left" class="sortableTD"><s:property
											value="approverNameItt" />&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="approverTypeItt" />&nbsp;</td>
										<td width="10%" align="center" class="sortableTD"><s:property
											value="level" />&nbsp; <s:hidden name="approverCodeItt"/></td>
									</tr>

								</s:iterator>
								<%
								d = i;
								%>
							</s:if>
							<s:if test="modeLength"></s:if>
							<s:else>
								<table width="100%">
									<tr>
										<td align="center"><font color="red">No Data To
										Display</font></td>
									</tr>
								</table>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>

		</tr>
	</table>
</s:form>
<script>
function addNewFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorReportStructure_addNew.action';
		document.getElementById('paraFrm').submit();
}

function callForEdit(id){
    document.getElementById('paraFrm_reportingID').value=id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'VendorReportStructure_callForEdit.action';
	document.getElementById('paraFrm').submit();
}
</script>

