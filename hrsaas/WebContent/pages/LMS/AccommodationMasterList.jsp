<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>




<s:form action="AccommodationMaster" id="paraFrm" theme="simple">


	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="AccommodationId"></s:hidden>
	<s:hidden name="checkOutNo"></s:hidden>
	


 <s:hidden name="empID"></s:hidden>
	<s:hidden name="empName"></s:hidden>
	<s:hidden name="branchName"></s:hidden>
	<s:hidden name="designationName"></s:hidden>
	 <s:hidden name="colonyID"></s:hidden>
	 <s:hidden name="colonyName"></s:hidden>
	 <s:hidden name="houseNo"></s:hidden>
 <s:hidden name="houseAddr"></s:hidden>
	 <s:hidden name="houseID"></s:hidden>
	 <s:hidden name="accommodationID"></s:hidden>
	 <s:hidden name="validityDate"></s:hidden>
	 <s:hidden name="amount"></s:hidden>
 <s:hidden name="freeAccommodations"></s:hidden>
	 <s:hidden name="hraDeduction"></s:hidden>
	<s:hidden name="rentRec"></s:hidden>
	 <s:hidden name="rentAmount"></s:hidden>
	 <s:hidden name="individual"></s:hidden>
	 <s:hidden name="family"></s:hidden>
 <s:hidden name="myPage"></s:hidden>
	 <s:hidden name="radioValue"></s:hidden>
	 <s:hidden name="radio1Value"></s:hidden>

	<s:hidden theme="simple" name="empId" />
	<table width="100%" border="0" class="formbg" align="right">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Accommodation Master </strong></td>

				</tr>






			</table>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>

					<td align="left" colspan="2"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <%
 	int totalPage = 0;
 	int pageNo = 0;
 %>
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'AccommodationMaster_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'AccommodationMaster_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'AccommodationMaster_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'AccommodationMaster_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AccommodationMaster_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>





				</tr>
			</table>
			</td>
		</tr>








		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0">
				<tr>
					<td colspan="2" class="txt"><strong class="text_head">Accommodation Master </strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete()" /></td>
				</tr>

				</td>
				</tr>



				<table width="100%" border="0" cellpadding="1" cellspacing="1">

					<tr>
						<td class="formth" align="center" width="10%">Sr No</td>
						<td class="formth" align="center" width="50%"><label class="set"
							name="emp.name" id="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
						<td class="formth" align="center" width="10%"><label class="set"
							name="house.number" id="house.number"
							ondblclick="callShowDiv(this);"><%=label.get("house.number")%></label></td>
						<td class="formth" align="center" width="10%"><label class="set"
							name="colony" id="colony"
							ondblclick="callShowDiv(this);"><%=label.get("colony")%></label></td>

						<td class="formth" align="center" width="20%"><label class="set"
							name="checkOut" id="checkOut"
							ondblclick="callShowDiv(this);"><%=label.get("checkOut")%></label></td>

					</tr>
					<%
					int i = 0;
					%>
					<%!int total = 0;%>

						<s:iterator value="AccommodationMasterList">
						<tr
							ondblclick="javascript:callForEdit('<s:property  value="accommodationID"/>');">
							<td width="10%" align="center" class="sortableTD"><s:property
								value="ittSrN0" /></td>

							<td class="sortableTD" width="50%"><s:property
								value="ittEmployeeName" /></td>
							<td class="sortableTD" width="10%"><s:property
								value="ittHouseNo" /></td>
							<td class="sortableTD" width="10%">&nbsp;<s:property
								value="ittColonyName" /></td>

							<td align="center" nowrap="nowrap" class="sortableTD" width="20%"
								id="ctrlShow" ><input type="button" 
								 name="checkAvail" value="CheckOut"
								onclick="javascript:selectRecord('<s:property  value="accommodationID"/>');" /> </td>
								
							

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
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					</tr>
				</table>

			</table>
			</td>
		</tr>
	</table>


</s:form>
<script><!--
  	
  	function addnewFun(){
  	  
  	    document.getElementById('paraFrm').target = "_self";
   	    document.getElementById('paraFrm').action="AccommodationMaster_addNew.action";
	    document.getElementById('paraFrm').submit();
	   
  	}
  	
  		function searchFun(){
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='AccommodationMaster_search.action';
			document.getElementById("paraFrm").submit();
  	}
  	
  	function chkDelete() {
	// var con=confirm('Do you want to delete the record(s) ?');
	 //if(con){
		//document.getElementById('paraFrm').target = "_self";
      	//document.getElementById('paraFrm').action = 'AccommodationMaster_deleteCheck.action';
		//document.getElementById('paraFrm').submit();
	//}
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
	

	
	function selectRecord(id){
	   document.getElementById('paraFrm_AccommodationId').value=id;
  	   document.getElementById('paraFrm').target = "_self";
   	   document.getElementById('paraFrm').action="AccommodationMaster_checkOutData.action";
	   document.getElementById('paraFrm').submit();
	   return false;

}

	function callForEdit(id){
	   document.getElementById('paraFrm_AccommodationId').value=id;
  	   document.getElementById('paraFrm').target = "_self";
   	   document.getElementById('paraFrm').action="AccommodationMaster_dblClickItt.action";
	   document.getElementById('paraFrm').submit();
	   return false;
  	}
  	--></script>