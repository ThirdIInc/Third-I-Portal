<!--@author REEBA_JOSEPH @date 21-09-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="PromotionArrears" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<s:hidden name="listLength" />

	<s:hidden name="divName" /><s:hidden name="divCode" />
	<s:hidden name="brnName" /><s:hidden name="brnCode" />
	<s:hidden name="arrPaidMonth" /> 
	<s:hidden name="arrPaidYear" /> 
	<s:hidden name="arrCode" />
	<s:hidden name="arrPaidMonthCode" />
	<s:hidden name="payinSalFlag" />
	<s:hidden name="deductTaxFlag" />
	<s:hidden name="status" />
	

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion
					Arrears</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" align="left" colspan="3">
			<table width="100%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> 
					</td>
					<%
						int totalPage = 0;
							int pageNo = 0;
					%>
					<s:if test="iteratorlist!=null">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'PromotionArrears_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'PromotionArrears_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'PromotionArrears_input.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'PromotionArrears_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PromotionArrears_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td class="txt" colspan="2"><strong class="text_head">Promotional Arrears</strong></td>
					<td colspan="1" align="right"></td>
				</tr>
				<tr>
					<td colspan="3">
					<%
						try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">
						<tr class="sortableTD">
							<s:hidden name="myPage" id="myPage" />
							<td class="formth"><label class="set" name="serial.no"
								id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
							</td>
							
							<td class="formth"><label class="set"
								name="calculateFrom" id="calculateFrom1"
								ondblclick="callShowDiv(this);"><%=label.get("calculateFrom")%></label>
							</td>
							<td class="formth"><label class="set" name="calculateTo"
								id="calculateTo1" ondblclick="callShowDiv(this);"><%=label.get("calculateTo")%></label>
							</td>
							<td class="formth"><label class="set" name="division"
								id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							</td>
							
							<td class="formth"><label class="set" name="branch"
								id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							</td>
							<td class="formth"><label class="set" name="status"
								id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
							</td>
							<%
								int count = 0;
							%>
							<%!int d = 0;%>
							<%
								int i = 0;
										int cnt = 0;
							%>
						</tr>
						<s:iterator value="iteratorlist">
							<tr <%if(count%2==0){
												%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								title="Double click for edit"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="listArrearCode"/>','<s:property value="listPaidMonth"/>','<s:property value="listReflectingMonthCode"/>','<s:property value="listPaidYear"/>',
																'<s:property value="listDivName"/>','<s:property value="listDivId"/>','<s:property value="listBranch"/>','<s:property value="listBranchId"/>'
																,'<s:property value="listPayinSalFlag"/>','<s:property value="listDeductTaxFlag"/>','<s:property value="listStatus"/>');">
								<script type="text/javascript">
													records[<%=i%>] = document.getElementById('listArrearCode' + '<%=i%>').value;									
												</script>
								<!-- SNO -->
								<td width="5%" align="center" class="sortableTD"><%=++cnt%>
								<%
									++i;
								%>
								</td>
								<s:hidden value="%{listArrearCode}"
									id='<%="listArrearCode" + i%>'></s:hidden>
								
								<!-- MONTH -->
								<td width="10%" align="left" class="sortableTD">
								<s:property	value="listFromMonth" />
								
								<s:property	value="listPaidMonth" /> <s:hidden
									name="listPaidMonth" /> <s:hidden
									name="listPayinSalFlag" /><s:hidden
									name="listDeductTaxFlag" /><s:hidden
									name="listReflectingMonthCode" /></td>
								<!-- YEAR -->
								<td width="10%" align="center" class="sortableTD">
								<s:property	value="listToMonth" />
								<s:property
									value="listPaidYear" /><s:hidden name="listPayInFlag" /></td>
									<!-- DIVISION -->
								<td width="15%" align="left" class="sortableTD"><s:property
									value="listDivName" />&nbsp; <s:hidden name="listDivId" /></td>
									<!-- BRANCH -->
								<td width="15%" align="left" class="sortableTD"><s:property
									value="listBranch" />&nbsp; <s:hidden name="listBranchId" /></td>
								<td width="15%" align="left" class="sortableTD"><s:property
									value="listStatus" />&nbsp; <s:hidden name="listStatus" /></td>
							</tr>
						</s:iterator>
						<%
							d = i;
						%>
					</table>
					<s:if test="iteratorlist"></s:if> <s:else>
						<table width="100%">
							<tr>
								<td align="center"><font color="red">No Data To
								Display</font></td>
							</tr>
						</table>
					</s:else> <%
 	} catch (Exception e) {
 		}
 %>
					</td>
				</tr>
				<s:if test="iteratorlist">
					<tr>

						<td align="right"><b>Total No. of Records: <s:property
							value="toTotRec" /></b></b></td>

					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
   	function newRowColor(cell)
   	{
		 cell.className='Cell_bg_first'; 
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		}
		else 
		cell.className='tableCell1';
	}
	
	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='PromotionArrears_addNew.action';
		document.getElementById("paraFrm").submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'PromotionArrears_f9Search.action';
		document.getElementById("paraFrm").submit();
	}
	
	
	function callForEdit(arrearCode, month, monthCode, year,
	divname,divid,branch,brnId,paySalFlag,deductTaxFlag,status) 
	{
		try{
		document.getElementById("paraFrm_arrCode").value 		= arrearCode;
		//document.getElementById("paraFrm_arrPaidMonth").value 	= monthCode;
		//document.getElementById("paraFrm_arrPaidMonthCode").value 		= monthCode;
		//document.getElementById("paraFrm_arrPaidYear").value	 	= year;
		//document.getElementById("paraFrm_divName").value 		= divname;
		//document.getElementById("paraFrm_divCode").value 		= divid;
		//document.getElementById("paraFrm_brnName").value 		= branch;
		//document.getElementById("paraFrm_brnCode").value 		= brnId;
		//document.getElementById("paraFrm_payinSalFlag").value=paySalFlag;
		//document.getElementById("paraFrm_deductTaxFlag").value=deductTaxFlag;
		document.getElementById("paraFrm_status").value=status;
		document.getElementById("paraFrm").action="PromotionArrears_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	    }catch(e){
	    	alert(e);
	    }
	}
	function callForEdit1(arrearCode) 
	{
		alert(arrearCode);
	}
	
</script>