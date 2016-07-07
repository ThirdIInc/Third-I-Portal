<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
	Object[][] corpList = null;
	Object[][] eventDataObj = null;
	String[][] knowledgeList= null;
	Object[][] downloadList = null;
	String[][] corpInfoList= null;
	String dashletCode = "";
	try {
		dashletCode = (String) request.getAttribute("dashletCode");
		corpList = (Object[][]) request.getAttribute("corpList");
		eventDataObj = (Object[][]) request
		.getAttribute("eventDataObj");

		knowledgeList = (String[][]) request
		.getAttribute("knowList");
		downloadList=(Object[][])request.getAttribute("downloadList");
		  corpInfoList = (String[][]) request
			.getAttribute("corpInfoList");

	} catch (Exception e) {
		e.printStackTrace();
		dashletCode = "";
	}
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="100%" valign="top">
		<table width="97%" border="0" cellspacing="0" cellpadding="0"
			align="left">
			<s:form action="EmployeePortal" id="paraFrm" theme="simple"
				name="employeePortalForm">
 
				<%
				if (!dashletCode.equals("") && dashletCode.equals("1")) {
				%>
				
				<tr>
				<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Announcements 
					  </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			 
				<%
							if (corpList != null && corpList.length > 0) {
							for (int i = 0; i < corpList.length; i++) {
				%>

				<tr>
					<td height="20" valign="middle"><img align="absmiddle"
						src="../pages/portal/images/icons/arrow.jpg" />&nbsp;&nbsp;<a  class="contlink"
						href="javascript:void(0);" onclick="show('<%=corpList[i][1] %>');"><%=corpList[i][0]%></a></td>
				</tr>

				<%
						}
						}
					}
				%>
				<%
				if (!dashletCode.equals("") && dashletCode.equals("2")) {
				%>				
				<tr>
						<td valign="bottom" class="txt">
						<table width="100%" border="0" align="right" cellpadding="0"
							cellspacing="0" class="formbg">
							<tr>
								<td><strong class="text_head"><img
									src="../pages/images/recruitment/review_shared.gif" width="25"
									height="25" /></strong></td>
								<td width="93%" class="txt"><strong class="text_head">Corporate Event </strong></td>
								<td width="3%" valign="top" class="txt">
								<div align="right"></div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				<%
				String url="EmployeePortal_showMoreInfo.action?dashletCode=2";
				
				%>
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td width="20%" class="formtxt"></td>
								<!-- Paging implementation -->
								<td id="showCtrl" width="80%" align="right"><s:if
									test="pageFlag">
									<%
										int totalPage = (Integer) request.getAttribute("totalPage");
										int pageNo = (Integer) request.getAttribute("pageNo");
									%>
									<b>Page:</b>
									<a href="javascript:void(0);"
										onclick="callPage('1', 'F', '<%=totalPage%>', '<%=url%>');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
										onclick="callPage('P', 'P', '<%=totalPage%>', '<%=url%>');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a>
									<input type="text" name="pageNoField" id="pageNoField" size="3"
										value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>','<%=url%>');return numbersOnly();" /> of <%=totalPage%>
									<a href="javascript:void(0);"
										onclick="callPage('N', 'N', '<%=totalPage%>','<%=url%>')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', '<%=url%>');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
								</s:if></td>
							</tr>
						</table>
						</td>
					</tr>

				<tr>
					<td>
					<table width="100%" border="0">
						<tr height="20">
							<td height="20"   width="5%" class="formth"><strong>Sr.No</strong></td>
							<!--<td   width="15%"  class="formth" nowrap="nowrap"><strong>Event
							Cataegory </strong></td>
							--><td   width="65%"  class="formth"><strong>Event
							Name </strong></td>
							<td   width="15%"  class="formth"><strong>Event
							Year </strong></td>
							<td   width="15%"  class="formth"><strong>Event
							Location</strong></td>
						</tr>

						<%
						
						int srNo = 0;
						try {
							srNo = (Integer) request.getAttribute("loopStartIndexEvent");
						} catch (Exception e) {
							e.printStackTrace();
						}
							if (eventDataObj != null && eventDataObj.length > 0) {
							for (int i = 0; i < eventDataObj.length; i++) {
								 
						%>
						 <%int count=0; %>
						
						<tr height="20"
											<%if(count%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"  
												title="Double click for event gallery"  style="cursor: hand"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="callPageAction('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=<%=eventDataObj[i][2]%>&eventCode=<%=eventDataObj[i][0]%>&yearValue=<%=eventDataObj[i][3]%>');">
												
							<td height="20" align="center"  class="sortableTD"><%=++srNo%></td>
							<!--<td align="left"  class="sortableTD"><%=Utility.checkNull(String
									.valueOf(eventDataObj[i][5]))%></td>

							--><td align="left"  class="sortableTD"> 
							<%=eventDataObj[i][1]%> </td>

							<td align="center"  class="sortableTD"><%=Utility.checkNull(String
									.valueOf(eventDataObj[i][3]))%></td>

							<td align="left"  class="sortableTD"><%=Utility.checkNull(String
									.valueOf(eventDataObj[i][4]))%></td>
						</tr>
						<%}} %>
					</table>
					</td>
				</tr>

				<% } %>
				
				
					<%
				if (!dashletCode.equals("") && dashletCode.equals("3")) {
				%>
				
				<tr>
				<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Knowledge Information 
					  </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			 
				<%
							if (knowledgeList != null && knowledgeList.length > 0) {
							for (int i = 0; i < knowledgeList.length; i++) {
				%>

				<tr>
					<td height="20" valign="middle"><img align="absmiddle"
						src="../pages/portal/images/icons/arrow.jpg" />&nbsp;&nbsp;<a  class="contlink"
						href="javascript:void(0);" 
						onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showKnowledge.action?knowCode=<%=knowledgeList[i][0]%>');"><%=knowledgeList[i][0]%></a></td>
				</tr>

				<%
						}
						}
					}
				%>
				
				
						<%
				if (!dashletCode.equals("") && dashletCode.equals("4")) {
				%>
				
				<tr>
				<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Quick Download 
					  </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			 
				<%
							if (downloadList != null && downloadList.length > 0) {
							for (int i = 0; i < downloadList.length; i++) {
				%>

				<tr>
					<td height="20" valign="middle"><img align="absmiddle"
						src="../pages/portal/images/icons/arrow.jpg" />&nbsp;&nbsp;<a  class="contlink"
						href="javascript:void(0);" 
						onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showQuickDownload.action?downloadCode=<%=downloadList[i][0]%>');"><%=downloadList[i][0]%></a></td>
				</tr>

				<%
						}
						}
					}
				%>
				
				
				
				
									<%
				if (!dashletCode.equals("") && dashletCode.equals("5")) {
				%>
				
				<tr>
				<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Corporate Information 
					  </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			 
				<%
							if (corpInfoList != null && corpInfoList.length > 0) {
							for (int i = 0; i < corpInfoList.length; i++) {
				%>

				<tr>
					<td height="20" valign="middle"><img align="absmiddle"
						src="../pages/portal/images/icons/arrow.jpg" />&nbsp;&nbsp;<a
			href="javascript:void(0);" class="contlink"
			onclick="show('<%=corpInfoList[i][1] %>');"><%=corpInfoList[i][0]%></a></td>
				</tr>

				<%
						}
						}
					}
				%>
				
				
			</s:form>
		</table>
		</td>
	</tr>
</table>
<script>


 function callMore(id){
		document.getElementById('paraFrm').action=id;
		document.getElementById('paraFrm').submit();	
 }


 function oldRowColor(cell,val) {	
	if(val=='1'){
	 cell.className='tableCell2';
	}else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}

  function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		 cell.className='Cell_bg_first'; 
		//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}

 function callPageAction(actionName){ 
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
 }

 function show(id){		 
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
 } 
</script>
