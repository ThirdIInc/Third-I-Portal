<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="CourseTypeMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="courseCode"/>
	<s:hidden name="dataPath"/>
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Course Creation
					</strong></td>
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
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'CourseTypeMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'CourseTypeMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
				border="0">
				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortableTD">
								<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="10%"><label
										class="set" name="srNo" id="srNo"
										ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>

									<td class="formth" align="center" width="30%"><label
										class="set" name="cName" id="cName"
										ondblclick="callShowDiv(this);"><%=label.get("cName")%></label></td>
									<td class="formth" align="center" width="40%"><label
										class="set" name="cType" id="cType"
										ondblclick="callShowDiv(this);"><%=label.get("cType")%></label></td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="desc" id="desc"
										ondblclick="callShowDiv(this);"><%=label.get("desc")%></label></td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="addOn" id="addOn"
										ondblclick="callShowDiv(this);"><%=label.get("addOn")%></label></td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="addBy" id="addBy"
										ondblclick="callShowDiv(this);"><%=label.get("addBy")%></label></td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="edit" id="edit"
										ondblclick="callShowDiv(this);"><%=label.get("edit")%></label></td>
									<td class="formth" align="center" width="20%"><label
										class="set" name="deleteRecord" id="deleteRecord"
										ondblclick="callShowDiv(this);"><%=label.get("deleteRecord")%></label></td>
									<s:if test="modeLength">
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="courseTypeList">
											<tr class="sortableTD" title="Double click for edit"
												<%if (count % 2 == 0) {%>
												class="tableCell1" <%} else {%>
												class="tableCell2" <%}count++;%>
												onmouseover = "javascript:newRowColor(this);" 
												title="Double click for edit" 
												onmouseout = "javascript:oldRowColor(this,<%=count % 2%>);"
												ondblclick = "javascript:callForEdit('<s:property value="courseCode" />');">
												<td width="10%" align="center" class="sortableTD"><%=++cn%><%++i;%>
												<s:hidden name="srNo" />&nbsp;</td>
												<s:hidden value="%{courseCode}" id='<%="courseCode" + i%>' />
												<script type="text/javascript">
																	records[<%=i%>] = document.getElementById('courseCode' + '<%=i%>').value;
																</script>
												<td width="30%" align="left" class="sortableTD"><s:property
													value="courseName" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" />&nbsp;</td>
												<td width="50%" align="left" class="sortableTD"><s:property
													value="courseType" />&nbsp;</td>
												<td width="10%" align="center" class="sortableTD"><s:property
													value="skillAdv" />&nbsp;</td>
												<td width="10%" align="center" class="sortableTD"><s:property
													value="courseAddDate" />&nbsp;</td>
												<td width="10%" align="center" class="sortableTD"><s:property
													value="courseAddedBy" />&nbsp;</td>
												<td class="sortableTD" align="center"><img
													style="cursor: pointer;" border="0"
													onclick="callForEdit('<s:property value="courseCode"/>',<%=i %>);"
													src="../pages/mypage/images/icons/edit.png" /></td>
												<td class="sortableTD" align="center"><img
													style="cursor: pointer;" border="0"
													onclick="callfordelete('<s:property value="courseCode"/>',<%=i %>);"
													src="../pages/mypage/images/icons/delete.png" /></td>
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
			<td width="100%">
			<table border="0" width="100%">
				<td width="75%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="25%" align="Right"><s:if test="modeLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	
function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'CourseTypeMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='CourseTypeMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'CourseTypeMaster_report.action';
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
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="CourseTypeMaster_callForEdit.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
function callForDelete1(id,i){
	   var flag='<%=d %>';
	   if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
</script>

