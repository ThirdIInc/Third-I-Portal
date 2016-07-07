<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="HomePage" id="paraFrm" theme="simple" validate="true">
	<%
	String loginEmpCode = request.getParameter("loginEmpCode");
	%>

	<input type="hidden" name="loginEmpCode" value="<%=loginEmpCode %>"
		id="loginEmpCode" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="3" class="formbg">
		<tr>
			<td width="4%" align="left"><strong class="text_head"><img
				src="../pages/mypage/images/icons/46.png" /></strong></td>
			<td width="96%" class="txt" align="left" colspan="2"><strong class="text_head">CEO
			Connect</strong></td>
		</tr>
		<tr><td width="100%" colspan="3" height="1px" bgcolor="#ccc"></td></tr>
		
		<tr>
			<%
				int totalPage = 0;
				int pageNo = 0;
			%>
			<s:if test="modeLength">
			<td></td>
				<td id="ctrlShow" width="30%" align="right" colspan="3"><b>Page:</b>
				<%
					totalPage = (Integer) request.getAttribute("totalPage");
					pageNo = (Integer) request.getAttribute("pageNo");
				%> <a href="#"
					onclick="callPage('1', 'F', '<%=totalPage%>', 'HomePage_callPage.action');">
				<img title="First Page" src="../pages/common/img/first.gif"
					width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('P', 'P', '<%=totalPage%>', 'HomePage_callPage.action');">
				<img title="Previous Page" src="../pages/common/img/previous.gif"
					width="10" height="10" class="iconImage" /> </a> <input type="text"
					name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
					maxlength="10"
					onkeypress="callPageText(event, '<%=totalPage%>', 'HomePage_callPage.action');return numbersOnly();" />
				of <%=totalPage%> <a href="#"
					onclick="callPage('N', 'N', '<%=totalPage%>', 'HomePage_callPage.action')">
				<img title="Next Page" src="../pages/common/img/next.gif"
					class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HomePage_callPage.action');">
				<img title="Last Page" src="../pages/common/img/last.gif" width="10"
					height="10" class="iconImage" /> </a></td>
			</s:if>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="2" cellspadding="3"
				border="0">
				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="2" cellspacing="3">
						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortableTD">
								<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="10%"><label>Serial
									No. </label></td>
									<td class="formth" align="center" width="30%"><label>From</label></td>
									<td class="formth" align="center" width="30%"><label>Subject</label></td>
									<td class="formth" align="center" width="40%"><label>Description</label></td>
									<td class="formth" align="center" width="20%"><label>Date</label></td>
									<s:if test="modeLength">
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="msgList">
											<tr class="sortableTD">
												<td width="10%" align="center" class="sortableTD"><%=++cn%>
												<%
												++i;
												%><s:hidden name="srNo" />&nbsp;</td>
												<s:hidden value="%{ceoMsgCode}" id='<%="ceoMsgCode" + i%>' />
												<script type="text/javascript">
																	records[<%=i%>] = document.getElementById('dtlCode' + '<%=i%>').value;
																</script>
												<td width="30%" align="left" class="sortableTD"><s:property
													value="msgFromName" /></td>				
												<td width="30%" align="left" class="sortableTD"><s:property
													value="ceoMsgSub" /></td>
												<td width="40%" align="left" class="sortableTD"><s:property
													value="ceoMsgDesc" /></td>
												<td width="20%" align="center" class="sortableTD"><s:property
													value="ceoMsgDate" /></td>
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
					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="3"><s:if test="modeLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
		</tr>
	</table>
</s:form>
