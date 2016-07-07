<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="HelpDeskStatusCateg" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Status
					Category Master</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="25%">

							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<%
									int totalPage = 0;
									int pageNo = 0;
								%>
								<s:if test="modeLength">
									<tr>
										<td id="ctrlShow" width="100%" align="right" class=""><b>Page:</b>
										<%
											totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
										%> <a href="#"
											onclick="callPage('1', 'F', '<%=totalPage%>', 'HelpDeskStatusCateg_callPage.action');">
										<img title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp; <a
											href="#"
											onclick="callPage('P', 'P', '<%=totalPage%>', 'HelpDeskStatusCateg_callPage.action');">
										<img title="Previous Page"
											src="../pages/common/img/previous.gif" width="10" height="10"
											class="iconImage" /> </a> <input type="text" name="pageNoField"
											id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
											onkeypress="callPageText(event, '<%=totalPage%>', 'HelpDeskStatusCateg_callPage.action');return numbersOnly();" />
										of <%=totalPage%> <a href="#"
											onclick="callPage('N', 'N', '<%=totalPage%>', 'HelpDeskStatusCateg_callPage.action')">
										<img title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp; <a href="#"
											onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HelpDeskStatusCateg_callPage.action');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a></td>
									</tr>
								</s:if>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="3" width="100%">
							<table class="formbg" width="100%" cellspacing="0"
								cellspacing="1" border="0">
								<tr>
									<td colspan="2" class="txt"><strong class="text_head">Status
									Category Master</strong></td>
									<td align="right"><s:if test="modeLength">
										<input type="button" class="delete" id="ctrlShow"
											value=" Delete" onclick="return chkDelete();" />
									</s:if></td>
								</tr>
								<tr>
									<td colspan="3">
									<%
									try {
									%>
									<table width="100%" border="0" class="formbg" cellpadding="0"
										cellspacing="0">

										<tr>

											<td class="formtext">
											<table width="100%" border="0">

												<tr>
													<s:hidden name="myPage" id="myPage" />
													<td class="formth" align="center"><label class="set"
														name="serial.no" id="serial.no"
														ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
													</td>
													<td class="formth" align="center"><label class="set"
														name="status.categ" id="status.categ1"
														ondblclick="callShowDiv(this);"><%=label.get("status.categ")%></label>
													</td>
													<td class="formth" align="center"><label class="set"
														name="status.abbrev" id="status.abbrev1"
														ondblclick="callShowDiv(this);"><%=label.get("status.abbrev")%></label>
													</td>
													<s:if test="modeLength">
														<td align="right" class="formth" nowrap="nowrap"
															id="ctrlShow"><input type="checkbox" id="selAll"
															style="cursor: hand;"
															title="Select all records Name  to remove"
															onclick="selectAllRecords(this);" /></td>

													</s:if>
													<s:if test="modeLength">
														<%
														int count = 0;
														%>
														<%!int d = 0;%>
														<%
																int cnt = pageNo * 20 - 20;
																int i = 0;
														%>
														<s:iterator value="statusCategList">

															<tr id="tr1" <%if(count%2==0){
									%>
																class="tableCell1" <%}else{%> class="tableCell2"
																<%	}count++; %>
																onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


																<td width="5%" align="center" class="sortableTD"
																	title="Double click for edit"
																	ondblclick="javascript:callForEdit('<s:property  value="statusCategCodeItr"/>');">
																<%=++cnt%> <%
 ++i;
 %>
																</td>


																<td width="30%" align="left" class="sortableTD"
																	title="Double click for edit"
																	ondblclick="javascript:callForEdit('<s:property  value="statusCategCodeItr"/>');">
																<s:hidden value="%{statusCategCodeItr}"
																	id='<%="statusCategCodeItr" + i%>'>
																</s:hidden> <script type="text/javascript">
										records[<%=i%>] = document.getElementById('statusCategCodeItr' + '<%=i%>').value;
									</script> <input type="hidden" name="hdeleteCode" id="hdeleteCode" /> <!--<input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" />--> <s:property
																	value="statusCategItr" /></td>
																<td width="30%" align="left" class="sortableTD"
																	title="Double click for edit"
																	ondblclick="javascript:callForEdit('<s:property  value="statusCategCodeItr"/>');">
																<s:property value="statusAbbrevItr" /></td>
																<input type="hidden" name="hdeleteCode"
																	id="hdeleteCode<%=i%>" />

																<td width="5%" id="ctrlShow" align="center"
																	nowrap="nowrap" class="sortableTD"><input
																	type="checkbox" class="checkbox" id="confChk<%=i%>"
																	name="confChk"
																	onclick="callForDelete1('<s:property  value="statusCategCodeItr"/>','<%=i%>')" /></td>
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
														<td align="center"><font color="red">No Data
														To Display</font></td>
													</tr>
												</table>
											</s:else> <%
 	} catch (Exception e) {
 	}
 %>
											</td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</td>
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
				<td width="70%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="30%" align="right"><s:if test="modeLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
			</table>
			</td>
		</tr>
		<s:hidden name="show" /> <s:hidden name="hiddencode" /> <s:hidden name="statusCateg" />
		<s:hidden name="statusCategCode" /> <s:hidden name="statusAbbrev" /> <s:hidden name="statusOrder" />
		<s:hidden name="isStatusLast" /> 
	</table>
</s:form>

<script type="text/javascript">
	function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDeskStatusCateg_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='HelpDeskStatusCateg_f9Action.action';
		document.getElementById("paraFrm").submit();
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
		callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="HelpDeskStatusCateg_calforedit.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   	}
   
    function callForDelete1(id,i){
	   var flag='<%=d %>';
	 	//alert('id----1-----'+id);
		//alert('i----1-----'+i);
		if(document.getElementById('confChk'+i).checked == true)
	   	{	  
	    	document.getElementById('hdeleteCode'+i).value=id;
	   	}
	   	else
	   		document.getElementById('hdeleteCode'+i).value="";
   	}
	
	function chkDelete()
	{
 		if(chk()){
		 	var con=confirm('Do you want to delete the record(s) ?');
	 		if(con){
	   			document.getElementById('paraFrm').action="HelpDeskStatusCateg_deleteList.action";
	   			document.getElementById('paraFrm').target = "_self";
	   	 		document.getElementById('paraFrm').submit();
	    	}
	    	else
	    	{ 
				document.getElementById('selAll').checked = false;
	    		var flag='<%=d %>';
	  			for(var a=1;a<=flag;a++){	
					document.getElementById('confChk'+a).checked = false;
					document.getElementById('hdeleteCode'+a).value="";
				}
	     		return false;
	 		}
	 	}
	 	else {
	 		alert('Please select atleast one record');
	 		return false;
	 	}
	}
	
	function chk(){
		var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
		   	if(document.getElementById('confChk'+a).checked == true)
		   	{	
		    	return true;
		   	}	   
	  	}
	  	return false;
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
</script>
