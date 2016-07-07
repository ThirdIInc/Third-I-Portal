<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="TaxChallan" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
		<!--Main Table  -->
<s:hidden name="actAddQuesPage" id="actAddQuesPage" />
<s:hidden name="challanID" />
<s:hidden name="month" />
<s:hidden name="year" />
<s:hidden name="divName" />
<s:hidden name="divId" />
<s:hidden name="dummyField"/>
<s:hidden name="monthName"/>
<s:hidden name="hidIncludeSal" id="hiIncludeSal" />
<s:hidden name="hidIncludeSettle" id="hidIncludeSettle" />
<s:hidden name="hidIncludeAllow" id="hidIncludeAllow" />
<s:hidden name="applicationType"/>
<s:hidden name="applicationCode"/>
<s:hidden name="backAction"/>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax
					Challan </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td nowrap="nowrap">
						<input type="button" class="token" onclick="addnewFun()" value="Add New" />	
						<input type="button" class="token" onclick="searchFun()" value="Search" />	
						<s:if test='%{applicationType != ""}'>
							<input type="button" class="token" onclick="backToBonus()" value="Back" />	
						</s:if>
					</td>
					
					<s:if test="listNoData"></s:if>
					<s:else>
						<td colspan="3" align="right" id="ctrlShow">
						<% int totalPage = 0; int pageNo = 0; %> <b>Page:</b> <%
						 	totalPage = (Integer) request.getAttribute("totalPage");
						 	pageNo = (Integer) request.getAttribute("pageNo");
						 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'TaxChallan_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'TaxChallan_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TaxChallan_input.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'TaxChallan_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TaxChallan_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td class="formtext" colspan="2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<!--table 1  -->
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" nowrap="nowrap"><!--Sr No.--> <label
								class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
							</td>
							<td class="formth" align="center" nowrap="nowrap"><!--Challan month--> <label
								class="set" name="challan.month" id="challan.month"
								ondblclick="callShowDiv(this);"><%=label.get("challan.month")%></label>
							</td>
							<td class="formth" align="center" nowrap="nowrap"><!--Challan year--> <label
								class="set" name="challan.year" id="challan.year"
								ondblclick="callShowDiv(this);"><%=label.get("challan.year")%></label>
							</td>
							<td class="formth" align="center" nowrap="nowrap"><!--division--> <label
								class="set" name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							</td>
							<td class="formth" align="center" nowrap="nowrap"><!--division--> <label
								class="set" name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("listTax")%></label>
							</td>
						</tr>
						<s:if test="listNoData">
							<tr>
								<td width="100%" colspan="6" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>
						<%!int a = 0;%>
						<%
						int count = 0;
						%>
						<%
							int h1 = 1;
							int f1 = 0;
						%>
						<s:iterator value="iteratorlist">
							<tr <%if(count%2==0){
											%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								title="Double click for edit"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="challanListCode" />','<s:property value="challanListMonthId" />',
															'<s:property value="challanListYear" />','<s:property value="challanListDivName" />','<s:property value="challanListDivId" />','<s:property value="challanListMonth" />');">
								<td width="10%" align="left" class="sortableTD"><%=h1 %><s:hidden 
									name="listRowNum" /></td>
								<s:hidden value="%{challanListCode}"
									id='<%="challanListCode"+f1%>' />
								<td width="15%" align="left" class="sortableTD"><s:property
									value="challanListMonth" /> <s:hidden
									value="%{challanListMonthId}" id='<%="challanListMonthId"+f1%>' />
								</td>
								<td width="20%" align="left" class="sortableTD"><s:property
									value="challanListYear" /></td>
								<td width="35%" align="left" class="sortableTD"><s:property
									value="challanListDivName" /> <s:hidden
									value="%{challanListDivId}" id='<%="challanListDivId"+f1%>' />
								</td>
								<td width="20%" align="right" class="sortableTD"><s:property
									value="listTotalTax" /></td>
							</tr>
							<%
								h1++;
								f1++;
							%>
						</s:iterator>
						<%
						a = f1;
						%>
					</table>
					<!--end of table 1  --></td>
				</tr>
			</table>
			<!--end of on load ist table  --></td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td nowrap="nowrap">
						<input type="button" class="token" onclick="addnewFun()" value="Add New" />	
						<input type="button" class="token" onclick="searchFun()" value="Search" />	
						<s:if test='%{applicationType != ""}'>
							<input type="button" class="token" onclick="backToBonus()" value="Back" />	
						</s:if>
					</td>
					<s:if test="listNoData"></s:if>
					<s:else>	
					<td align="right"><b>Total No. of Records: <s:property
						value="totalListRecords" /></b></b></td></s:else>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!--Main Table End -->
</s:form>
<script>

function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'TaxChallan_f9action.action';
		document.getElementById("paraFrm").submit();
}

function addnewFun(){
	document.getElementById("paraFrm").action = 'TaxChallan_addNew.action';
	document.getElementById("paraFrm").target = 'main';
	document.getElementById("paraFrm").submit();
}

function callForEdit(id,month,year,divName,divId,monthName){
   	document.getElementById("paraFrm").action='TaxChallan_callForEdit.action?challanCode='+id+'&month='+month+'&year='+year+'&divName='+divName+'&divId='+divId+'&monthName='+monthName;
   	document.getElementById("paraFrm").target="main";
    document.getElementById("paraFrm").submit();
}

function newRowColor(cell)
{
	cell.className='Cell_bg_first'; 
} //end of newRowColor method

function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
} //end of oldRowColor method
function backToBonus(){
	var backAction = document.getElementById('paraFrm_backAction').value;
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action=backAction;
	document.getElementById('paraFrm').submit();
}
</script>