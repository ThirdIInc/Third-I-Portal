<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:hidden name="surveyName" />
<s:hidden name="frmDate" />
<s:hidden name="toDate" />
<s:hidden name="surveyCode" />



<s:form action="EmployeeSurveyMaster" id="paraFrm" theme="simple">
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Survey List </strong></td>

				</tr>
			</table>
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
					<s:if test="noData"></s:if>
					<s:else>
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'EmployeeSurveyMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'EmployeeSurveyMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'EmployeeSurveyMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'EmployeeSurveyMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmployeeSurveyMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else>
				</tr>
			</table>
			</td>
		</tr>









		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0">
				<tr>
					<td colspan="2" class="txt"><strong class="text_head">Employee
					Survey List </strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete()" /></td>
				</tr>
				<!-- <input type="button"
						id="ctrlShow" class="add" theme="simple" value=" Add New"
						onclick="return chkAdd()" /> <input type="button" id="ctrlShow"
						class="search" theme="simple" value=" Search"
						onclick="return chkSearch()" /></td>
					<td align="right"> -->
				</td>
				</tr>



				<table width="100%" border="0" cellpadding="1" cellspacing="1">

					<tr>
						<td class="formth" align="center" width="10%"><b>Sr No</td>

						<!--<td class="formth" align="center" width="30%"><b>Survey Name</td>
		<td class="formth" align="center" width="20%"><b>Start Date</td>
		<td class="formth" align="center" width="20%"><b>End Date</td>
		
		-->
						<td class="formth" align="center"><label class="set"
							name="surveyName" id="surveyName" ondblclick="callShowDiv(this);"><%=label.get("surveyName")%></label></td>
						<td class="formth" align="center"><label class="set"
							name="startDate" id="startDate" ondblclick="callShowDiv(this);"><%=label.get("startDate")%></label></td>
						<td class="formth" align="center"><label class="set"
							name="endDate" id="endDate" ondblclick="callShowDiv(this);"><%=label.get("endDate")%></label></td>


						<td align="right" id="ctrlShow" class="formth" nowrap="nowrap">
						<br />
						<input type="checkbox" id="selAll" style="cursor: hand;"
							title="Select all records Name to remove"
							onclick="selectAllRecords(this);" /></td>

					</tr>
					<%
					int i = 0;
					%>
					<%!int total = 0;%>
					<s:iterator value="empSurveyFirstItt">
						<tr>
							<td width="10%" align="center" class="sortableTD"><%=(i + 1)%></td>
							<td class="sortableTD" width="40%"><s:property
								value="ittSurveyName" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property
								value="ittStartDate" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property
								value="ittEndDate" /></td>
							<td align="center" nowrap="nowrap" class="sortableTD"
								id="ctrlShow"><input type="checkbox" class="checkbox"
								id="confChk<%=i%>" name="confChk"
								onclick="selectRecord('<%=i%>');" /><s:hidden
								name="ittsurveyCode" /> <input type="hidden" name="hidCode"
								size="2" id="hidCode<%=i%>"/ ></td>

						</tr>
						<%
						i++;
						%>
						<%
						total = i;
						%>
					</s:iterator>
					<tr>

						<td align="left" colspan="2"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</tr>
				</table>

			</table>
			</td>
		</tr>
	</table>

	<s:hidden name="myPage" id="myPage"></s:hidden>
</s:form>
<script>
  	
  	function addnewFun(){
  	    document.getElementById('paraFrm').target = "_self";
   	    document.getElementById('paraFrm').action="EmployeeSurveyMaster_addNew.action";
	    document.getElementById('paraFrm').submit();
	   
  	}
  	
  		function searchFun(){
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='EmployeeSurveyMaster_search.action';
			document.getElementById("paraFrm").submit();
  	}
  	
  	function chkDelete() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmployeeSurveyMaster_deleteCheck.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
  	
  	
  		
	
	function selectAllRecords(object) {
			var count='<%=total%>';
		if(object.checked ) {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = true;
				 document.getElementById('hidCode' + i).value="Y";
			}
		} else {
			for(var i = 0; i <= count; i++) {
				document.getElementById('confChk' + i).checked = false;
				 document.getElementById('hidCode' + i).value="";
			}
		}
	}
	

	
	function selectRecord(i){
	
if(document.getElementById('confChk' + i).checked == true ) {
	document.getElementById('hidCode' + i).value="Y";
	}
	else{
	
   document.getElementById('confChk' + i).checked =false;
	document.getElementById('hidCode' + i).value=" ";
	}		 


}
  	</script>