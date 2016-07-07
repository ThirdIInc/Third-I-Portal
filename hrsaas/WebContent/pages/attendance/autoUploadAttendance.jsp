<!--Bhushan Dasare--><!--Feb 25, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="" name="" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="autoUploadID" /><s:hidden name="driver" /><s:hidden name="server" /><s:hidden name="radioFlag" />

	<table width="100%" align="right" class="formbg">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td><strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Auto Upload Attendance</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<%	int totalPage = 0, pageNo = 0; %>
		<s:if test="listLength">
			<tr>
				<td id="ctrlShow" width="100%" align="right" class="formbg">
					<b>Page:</b>
					<%	totalPage = (Integer)request.getAttribute("totalPage");
 						pageNo = (Integer)request.getAttribute("pageNo");
 					%>
 					<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
 					</a>&nbsp;
 					<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" />
 					</a>
 					<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10" 
 					onkeypress="callPageText(event, '<%=totalPage%>', 'AutoPresentAttendance_callPage.action'); return numbersOnly();" />
 					of <%=totalPage%>
 					<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action')">
 						<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
 					</a>&nbsp;
 					<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AutoPresentAttendance_callPage.action');">
 						<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" alt="" />
 					</a>
 				</td>
			</tr>
		</s:if>
		<tr>
			<td>
				<%	try { %>
				<table width="100%" class="formbg">
					<tr>
						<td class="formtext">
							<table width="100%">
								<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" width="5%">
										<label id="lbSrNo" name="lbSrNo" ondblclick="callShowDiv(this);"><%=label.get("lbSrNo")%></label>
									</td>
									<td class="formth" width="10%">
										<label id="lbDriver" name="lbDriver" ondblclick="callShowDiv(this);"><%=label.get("lbDriver")%></label>
									</td>
									<td class="formth" width="20%">
										<label id="lbServerName" name="lbServerName" ondblclick="callShowDiv(this);"><%=label.get("lbServerName")%></label>
									</td>
									<td class="formth" width="20%">
										<label id="lbUserName" name="lbUserName" ondblclick="callShowDiv(this);"><%=label.get("lbUserName")%></label>
									</td>
									<td class="formth" width="20%">
										<label id="lbDatabase" name="lbDatabase" ondblclick="callShowDiv(this);"><%=label.get("lbDatabase")%></label>
									</td>
									<s:if test="data_exit">
										<td align="center" class="formth" width="10%" id="ctrlShow">
											<input type="button"  class="delete" value="Delete" onclick="return chkDelete();" /><br>
											
											<input type="checkbox" id="allID" style="cursor: hand;" title="Select all records to remove" 
											onclick="selectAllRecords();" />
										</td>
									</s:if>
									<s:if test="data_exit">
										<%!	int d = 0; %>
										<%	int count = 0; int i = 0; int cnt=0; int dd=0; int cn = pageNo * 20 - 20; %>
										<s:iterator value="autoUploadList">
											<tr title="Double click for edit" 
											<%	if(count % 2 == 0) { %> class="tableCell1" <% } else { %> class="tableCell2" <% } count++; %>
											onmouseover="javascript:newRowColor(this);" onmouseout="javascript:oldRowColor(this, <%=count % 2%>);" 
											ondblclick="javascript:callForEdit('<s:property value="uploadId"/>');" >
												<td align="center" class="sortableTD">
													<%=++cnt%><%++i;%>
												</td>
												<td class="sortableTD" width="10%" nowrap="nowrap">
													<s:hidden name="uploadId" id='<%="uploadId" + i%>' />
													
													<s:property value="driver" />
												</td>
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('uploadId' + '<%=i%>').value;
												</script>
												<td class="sortableTD" width="25%" nowrap="nowrap">
													<s:property value="server" />
												</td>
												<td class="sortableTD" width="25%" nowrap="nowrap">
													<s:property value="userName" />
												</td>
												<td class="sortableTD" width="15%" nowrap="nowrap">
													<s:property value="database" />
												</td>
												<td class="sortableTD" id="ctrlShow" align="center" width="10%">
													<input type="checkbox" class="checkbox" id="confChk<%=dd++%>" name="confChk" 
													  value='<s:property value="uploadId"/>' />
												</td>
											</tr>
										</s:iterator>
										<% d = dd; i = 0; %>
									</s:if>
								</tr>
							</table>
							<s:if test="data_exit"></s:if>
							<s:else>
								<table width="100%">
									<tr>
										<td align="center"><font color="red">No Data To Display</font></td>
									</tr>
								</table>
							</s:else>
						</td>
					</tr>
				</table>
			<%	} catch(Exception e) {} %>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<td width="79%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right">
						<s:if test="isDataExists">
							<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
						</s:if>
					</td>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AutoUploadAttendance_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callForEdit(id) {
		document.getElementById('paraFrm_autoUploadID').value=id;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AutoUploadAttendance_edit.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {	
		javascript:callsF9(500,325,'AutoUploadAttendance_f9action.action');
	}
	
	function chkDelete() {
		var con = confirm("Do you want to delete record ");
		
		if(con) {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'AutoUploadAttendance_deleteAll.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function selectAllRecords(){
		var tot = '<%=d%>';
		
		for(var i = 0; i < tot; i++) {
			if(document.getElementById('allID').checked) {
				document.getElementById('confChk' + i).checked = true;
			} else {
				document.getElementById('confChk' + i).checked = false;
			}
		}
	}
</script>