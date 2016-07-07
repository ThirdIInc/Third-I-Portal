<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelAppvr" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="navStatus" />
	<s:hidden name="status" />

	<s:hidden name="noData" />
	<s:hidden name="pen" />
	<s:hidden name="Apprvd" />
	<s:hidden name="Retrned" />

	<!-- For Paging -->
	<s:hidden name="myPage" />
	<s:hidden name="pageFlag" />

	<!-- for navigation purpose -->
	<s:hidden name="tmsClmAppId" />
	<s:hidden name="tmsTrvlId" />
	<s:hidden name="tmsTrvlCode" />
	<s:hidden name="tmsExpType" />
	<s:hidden name="tmsApprvrLevel" />

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Claim Approval</strong></td>
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
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<tr>
					<a href="TravelClmAppvr_callStatus.action?status=A">Approved Claim Travel List</a> 
				</tr>
			</table>
			</td>

		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>					
					<s:if test="%{Apprvd}">
						<td height="27" class="formtxt"><strong> Approved
						Claim Travel List</strong></td>
					</s:if>
				</tr>

				<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int PageNo = (Integer) request.getAttribute("pageNo");
					int row1 = (Integer) request.getAttribute("row");
				%>

				<s:if test="pageFlag">
					<tr>

						<td align="right" colspan="5"><b>Page:</b> <%
 		if (PageNo != 1) {
 		if (PageNo > totalPage) {
 		} else {
 %> <a href="#" onclick="callPage('1');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P');">
						<img src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (PageNo == totalPage - 1 || PageNo == totalPage) {
 			for (int z = PageNo - 2; z <= totalPage; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNo > 3) {
 			for (int z = PageNo - 2; z <= PageNo + 2; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNo == totalPage)) {
 		if (totalPage == 0 || PageNo > totalPage) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
							src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
							href="#" onclick="callPage('<%=totalPage%>');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 %><select name="selectname" onchange="selectPage()" id="selectname">
							<%
							for (int j = 1; j <= totalPage; j++) {
							%>

							<option value="<%=j%>" <%=PageNo==j?"selected":"" %>><%=j%></option>
							<%
							}
							%>
						</select></td>
					</tr>

				</s:if>


				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
						<tr>
							<td width="8" valign="top" class="formth" nowrap="nowrap"><strong><label
								class="set" name="trvlClm.srNo" id="trvlClm.srNo"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.srNo")%></label></strong></td>
							<td width="20%" valign="top" class="formth"><strong><label
								class="set" name="trvlClm.trvlClmId" id="trvlClm.trvlClmId"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.trvlClmId")%></label></strong></td>
							<td width="20%" valign="top" class="formth"><strong><label
								class="set" name="trvlClm.empNameRguestName"
								id="trvlClm.empNameRguestName" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.empNameRguestName")%></label></strong></td>
							<td width="22%" valign="top" class="formth"><strong><label
								class="set" name="trvlClm.trvlClmReqName"
								id="trvlClm.trvlClmReqName" ondblclick="callShowDiv(this);"><%=label.get("trvlClm.trvlClmReqName")%></label></strong></td>
							<td width="18%" valign="top" class="formth"><strong><label
								class="set" name="trvlClm.initr" id="trvlClm.initr"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.initr")%></label></strong></td>

							<td width="13%" valign="top" class="formth" nowrap="nowrap"><strong><label
								class="set" name="trvlClm.trvlDate" id="trvlClm.trvlDate"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.trvlDate")%></label></strong></td>
							<td width="16%" valign="top" class="formth"><strong><label
								class="set" name="trvlClm.AppDate" id="trvlClm.AppDate"
								ondblclick="callShowDiv(this);"><%=label.get("trvlClm.AppDate")%></label></strong></td>

						</tr>
						<%!int i = 0;%>
						<%
						int k = 1, count2 = 0;
						%>

						<s:iterator value="travelClmList">
							<tr id="trvlRow_<%=k %>" <%if(count2%2==0){
						%>
								class="tableCell1" <%}else{ %> class="tableCell2"
								<%	}count2++; %> title="double click to edit"
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count2%2 %>);"
								ondblclick="javascript:viewDetails('<s:property value="trvlClmId"/>','<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>','<s:property value="expType"/>','<s:property value="apprvrLevel"/>')">
								<td class="sortableTD" width="5"><%=++row1%> <s:hidden
									name="trvlClmId" /> <s:hidden name="trvlAppCode" /> <s:hidden
									name="trvlIndAppId" /> <s:hidden name="expType" /> <s:hidden
									name="apprvrLevel" /> <s:hidden name="trvlEmpId" /> <s:hidden
									name="initrId" />
								<td class="sortableTD"><s:property value="travelId" />&nbsp;</td>
								<td class="sortableTD"><s:property value="empRguestName" />&nbsp;</td>
								<td class="sortableTD"><s:property value="trvlRqstName" />&nbsp;</td>
								<td class="sortableTD"><s:property value="initrName" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap" align="center"><s:property value="trvlDate" />&nbsp;</td>
								<td class="sortableTD" nowrap="nowrap" align="center"><s:property value="clmAppDate" />&nbsp;</td>

							</tr>
							<%
							k++;
							%>
						</s:iterator>
						<%
						i = k;
						%>


						<s:if test="noData">
							<tr>
								<td width="100%" colspan="6" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


	</table>


</s:form>

<script>
	 
	function callPage(id)
	{
	 var pageNo=document.getElementById('paraFrm_myPage').value	
	if(id=="P")
	document.getElementById('paraFrm_myPage').value=eval(pageNo)-1;	
	else if(id=="N")	
	document.getElementById('paraFrm_myPage').value=eval(pageNo)+1;	
	else
	document.getElementById('paraFrm_myPage').value=id;		
	document.getElementById('paraFrm').action = 'TravelClmAppvr_callStatus.action?status='+document.getElementById('paraFrm_status').value;
		document.getElementById('paraFrm').submit();
	}//end of callPage
	
	   function selectPage(appStatus)
   {     	
   		var pageNo;
   		 	pageNo = document.getElementById('selectname').value;
   		 	document.getElementById('paraFrm_myPage').value=pageNo;
   		 	document.getElementById('paraFrm').action = 'TravelClmAppvr_callStatus.action?status='+document.getElementById('paraFrm_status').value;
   		 	document.getElementById('paraFrm').submit();
   		}//end of selectPage
 </script>

<script>

function viewDetails(expAppId,travelAppId,travelAppCode,expType,apprvrLevel){  

		
//alert(expAppId);
		document.getElementById('paraFrm_tmsClmAppId').value = expAppId; 
		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; 
		document.getElementById('paraFrm_tmsTrvlCode').value = travelAppCode; 
		document.getElementById('paraFrm_tmsExpType').value = expType; 
		document.getElementById('paraFrm_tmsApprvrLevel').value = apprvrLevel; 
		document.getElementById('paraFrm').action = 'TravelClmAppvr_callView.action';	  
		document.getElementById('paraFrm').submit();
	    document.getElementById('paraFrm').target = "main";
}//end of viewDetails

 function newRowColor(cell) {   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
		}

</script>