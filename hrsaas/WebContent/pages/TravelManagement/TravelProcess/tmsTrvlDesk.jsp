
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelDesk" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="hidDeskCode" />
	<s:hidden name="noData" />
	<s:hidden name="trvAppId" />
	<s:hidden name="hiddenEdit" />
	<s:hidden name="trvlAsignedToEdit" />
	<s:hidden name="convAsignedToEdit" />
	<s:hidden name="lodgeAsignedToEdit" />
	<s:hidden name="myHidden" />
	<!--To send data from child window to main window  -->
	<s:hidden name="tmsTrvlId" />
	<s:hidden name="tmsTrvlIndiId" />
	<s:hidden name="tmsChkTypeFlg" />
	<s:hidden name="colorId" />
	<s:hidden name="singleClickAssignFlag" />
	<s:hidden name="singleClickBookFlag" />
	<s:hidden name="singleClickSubmitFlag" />	

	<s:hidden name="singleApplication" value="true" />
	<s:hidden name="isApprover" value="false" />

	<!-- For Paging -->
	<s:hidden name="myPage" />
	<s:hidden name="pageFlag" />
	<s:hidden name="status" />

	<!-- 		
		flags usefull for Travel application view		
		 -->
	<s:hidden name="deskFlag" />
	<s:hidden name="pen" />
	<s:hidden name="pendingCancelFlg" />
	<s:hidden name="chkSerch" />
	<s:hidden name="searchFlag" />
	<s:hidden name="applyFilterFlag" />
	<!--<s:hidden name="Apprvd" />
	<s:hidden name="Regcted" />
	<s:hidden name="Retrned" />
	<s:hidden name="showStatflag" />
	<s:hidden name="pendingCase" />
	<s:hidden name="pendingItrCase" />
	<s:hidden name="navStatus" />
	<s:hidden name="statusSave" />
	<s:hidden name="AppvrComment" />-->


	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Desk</strong></td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<s:if test="onLoadSubSchFlg">
					<tr>
						<td height="27" class="formtxt"><a
							href="TravelDesk_callStatus.action?status=A">Assigned List<s:if
							test="assgnedCnt>0">
							<strong> (<s:property value="assgnedCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=S">Scheduled
						Travel List<s:if test="bookedCnt>0">
							<strong> (<s:property value="bookedCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=PC">Pending For
						Cancellation List<s:if test="pendcancelCnt>0">
							<strong> (<s:property value="submitCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=CC">Cancelled
						List<s:if test="cancelCnt>0">
							<strong> (<s:property value="submitCnt" />)</strong>
						</s:if></a></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td height="27" class="formtxt"><a
							href="TravelDesk_callStatus.action?status=P">Pending Travel
						List<s:if test="pendCnt>0">
							<strong> (<s:property value="pendCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=A">Assigned
						List<s:if test="assgnedCnt>0">
							<strong> (<s:property value="assgnedCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=S">Scheduled
						Travel List<s:if test="bookedCnt>0">
							<strong> (<s:property value="bookedCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=M">Submit List
						<s:if test="submitCnt>0">
							<strong> (<s:property value="submitCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=PC">Pending For
						Cancellation List<s:if test="pendcancelCnt>0">
							<strong> (<s:property value="pendcancelCnt" />)</strong>
						</s:if></a> | <a href="TravelDesk_callStatus.action?status=CC">Cancelled
						List<s:if test="cancelCnt>0">
							<strong> (<s:property value="cancelCnt" />)</strong>
						</s:if></a></td>
					</tr>
				</s:else>
			</table>
			</td>

		</tr>



		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<tr>
					<td height="27" class="formtxt"><STRONG><s:if
						test="searchFlag">Applied Filter</s:if><s:else>Search the
					records by applying filters</s:else></STRONG></td>
					<td id="showFilter" align="right"><input type="button"
						class="token" value="Show Filter"
						onclick="return callShowFilter();"></td>

					<td id="hideFilter" align="right"><input type="button"
						class="token" value="Hide Filter"
						onclick="return callHideFilter();"></td>
				</tr>

				<tr>
					<td colspan="5">
					<div id="showFilterValue">
					<table width="100%" border="0">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="emp.name4" name="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
								name="filterEmpName" size="25" /><s:hidden name="filterEmpId" /><s:hidden
								name="filterEmpToken" /><img src="../pages/images/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TravelDesk_f9Emp.action');"></td>
							<td width="5%" colspan="1">&nbsp;</td>
							<td width="20%" colspan="1"><label class="set"
								id="init.name4" name="init.name" ondblclick="callShowDiv(this);"><%=label.get("init.name")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
								name="filterInitName" size="25" readonly="true" /><s:hidden
								name="filterInitId" /><s:hidden name="filterInitToken" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TravelDesk_f9Initiator.action');"></td>
						</tr>


						<s:if
							test="pen=='true' || rejected=='true' || submited=='true' || pendingCancel=='true'">
							<tr>
								<td width="20%" colspan="1"><label class="set"
									id="travel.id4" name="travel.id"
									ondblclick="callShowDiv(this);"><%=label.get("travel.id")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterTrvlId" size="25" /></td>
								<td width="5%" colspan="1">&nbsp;</td>
								<td width="20%" colspan="1"><label class="set"
									id="trvl.reqName" name="trvl.reqName"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.reqName")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterTrvlReq" size="25" /></td>
							</tr>
							<input type="hidden" name="chkReset" value="1" />
						</s:if>

						<s:else>
						<input type="hidden" name="chkReset" value="2" />
							<tr>
								<td width="20%" colspan="1"><label class="set"
									id="travel.id5" name="travel.id"
									ondblclick="callShowDiv(this);"><%=label.get("travel.id")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterTrvlId" size="25" /></td>
								<td width="5%" colspan="1">&nbsp;</td>
								<td width="20%" colspan="1"><label class="set"
									id="trvl.assignedTo" name="trvl.assignedTo"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.assignedTo")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterTrvlAsignedTo" size="25" readonly="true" /><s:hidden
									name="filterTrvlAsignedToId" /> <s:hidden
									name="filterTrvlAsignedToToken" /><img
									src="../pages/images/search2.gif" class="iconImage" height="18"
									align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelDesk_f9TrvlAssigned.action');"></td>
							</tr>


							<tr>
								<td width="20%" colspan="1"><label class="set"
									id="accom.assignedTo" name="accom.assignedTo"
									ondblclick="callShowDiv(this);"><%=label.get("accom.assignedTo")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterAccomAsignedTo" size="25" readonly="true" /><s:hidden
									name="filterAccomAsignedToId" /> <s:hidden
									name="filterAccomAsignedToToken" /><img
									src="../pages/images/search2.gif" class="iconImage" height="18"
									align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelDesk_f9AccomAssigned.action');"></td>
								<td width="5%" colspan="1">&nbsp;</td>
								<td width="20%" colspan="1"><label class="set"
									id="conv.assignedTo" name="conv.assignedTo"
									ondblclick="callShowDiv(this);"><%=label.get("conv.assignedTo")%></label>
								:</td>
								<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
									name="filterConvAsignedTo" size="25" readonly="true" /><s:hidden
									name="filterConvAsignedToId" /> <s:hidden
									name="filterConvAsignedToToken" /><img
									src="../pages/images/search2.gif" class="iconImage" height="18"
									align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelDesk_f9ConvAssigned.action');"></td>
							</tr>

						</s:else>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="trvl.applDate" name="trvl.applDate"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.applDate")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
								name="filterApplDate" size="25"  maxlength="10" 
								onkeypress="return numbersWithHiphen();" /><s:a
								href="javascript:NewCal('paraFrm_filterApplDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="5%" colspan="1">&nbsp;</td>
							<td width="20%" colspan="1"><label class="set"
								id="date.journey4" name="date.journey"
								ondblclick="callShowDiv(this);"><%=label.get("date.journey")%></label>
							:</td>
							<td width="25%" nowrap="nowrap" colspan="1"><s:textfield
								name="filterStartDate" size="25"  maxlength="10" 
								onkeypress="return numbersWithHiphen();" /><s:a
								href="javascript:NewCal('paraFrm_filterStartDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
						</tr>
						<tr>
							<td align="center" colspan="5"><s:submit cssClass="token"
								action="TravelDesk_applyFilter" theme="simple"
								value="Apply Filter" onclick="return chkFilter();" />&nbsp; <input
								type="button" class="reset" theme="simple"
								onclick="return callReset();" value=" Reset " /></td>
						</tr>

					</table>
					</div>
					</td>
				</tr>


				<tr>
					<td colspan="2">
					<%
						String[] dispArr = (String[]) request.getAttribute("dispArr");
						if (dispArr != null && dispArr.length > 0) {

							int k = 0;
							int count = 0;
							if (dispArr.length % 2 == 0) {
								k = dispArr.length / 2;

							} else {
								k = (dispArr.length / 2) + 1;

							}
					%>

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="enableFilterValue">

						<%
						for (int m = 0; m < k; m++) {
						%>

						<tr>
							<%
							if (count < dispArr.length) {
							%>

							<td width="20%" height="22" class="formtext"><%=dispArr[count]%>

							</td>
							<%
							count++;
							%>

							<%
							}
							%>


							<%
							if (count < dispArr.length) {
							%>
							<td width="20%" height="22" class="formtext"><%=dispArr[count]%>
							</td>
							<%
							count++;
							%>
							<%
							}
							%>
						</tr>




						<tr>

							<td align="center" colspan="5">&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return callClear();"
								value="Clear Filter" /></td>

						</tr>
					</table>
					<%
						}
						} // end of if
					%>
					</td>
				</tr>








			</table>
			</td>

		</tr>


		<script>
		callFilter();
	function callFilter(){
	var chkSearch=document.getElementById('paraFrm_chkSerch').value;
	if(chkSearch==""){
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
				}else{
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
				  document.getElementById('hideFilter').style.display='none';
			     document.getElementById('showFilter').style.display='none';
				}	            
	    }

	function callShowFilter(){
				document.getElementById('hideFilter').style.display='';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='inline';
				document.getElementById('enableFilterValue').style.display='none';				
		   }
	
	function callHideFilter(){
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
		    }
	function callClear(){
			  	document.getElementById('showFilterValue').style.display='none';
			 	document.getElementById("paraFrm_searchFlag").value='false';			 
			 	document.getElementById("paraFrm").action='TravelDesk_clearFilter.action';
			  	document.getElementById("paraFrm").submit();
		   }
		   
	function callReset()
   	{   
   	var chVal = document.getElementById('chkReset').value;
   	 			document.getElementById('paraFrm_filterEmpName').value="";
   				document.getElementById('paraFrm_filterEmpId').value="";
   				document.getElementById('paraFrm_filterEmpToken').value="";   	 
   				document.getElementById('paraFrm_filterInitToken').value="";
   	 			document.getElementById('paraFrm_filterInitName').value="";
   	 			document.getElementById('paraFrm_filterInitId').value="";
   	 			document.getElementById('paraFrm_filterApplDate').value="";   	 
   	 			document.getElementById('paraFrm_filterStartDate').value="";
   				document.getElementById('paraFrm_filterTrvlId').value="";
   				if(chVal == "1"){
   				document.getElementById('paraFrm_filterTrvlReq').value="";
   				}else{
   		 		document.getElementById('paraFrm_filterTrvlAsignedToId').value=""; 
   				document.getElementById('paraFrm_filterTrvlAsignedTo').value="";     	 
   	 			document.getElementById('paraFrm_filterAccomAsignedTo').value="";
   				document.getElementById('paraFrm_filterAccomAsignedToId').value="";
   	 			document.getElementById('paraFrm_filterConvAsignedToId').value="";
   	 			document.getElementById('paraFrm_filterConvAsignedTo').value="";
   				document.getElementById('paraFrm_filterTrvlAsignedToToken').value="";
   				document.getElementById('paraFrm_filterAccomAsignedToToken').value="";
   				document.getElementById('paraFrm_filterConvAsignedToToken').value="";
   				}
   	}
   	
   	function chkFilter(){
   	try{
   				var filterEmpName= document.getElementById('paraFrm_filterEmpName').value;   				
   				var filterInitName= document.getElementById('paraFrm_filterInitName').value;
   				var filterApplDate= document.getElementById('paraFrm_filterApplDate').value;
   				var filterStartDate= document.getElementById('paraFrm_filterStartDate').value;
   				var filterTrvlId= document.getElementById('paraFrm_filterTrvlId').value;
   				var filterSts=document.getElementById('paraFrm_status').value;
   				//alert(document.getElementById('paraFrm_status').value);
   				if(filterSts=="P" || filterSts=="R" || filterSts=="M" || filterSts=="PC")
   				{
   				
   				var filterTrvlReq= document.getElementById('paraFrm_filterTrvlReq').value;
   				if((filterEmpName=="")&&(filterInitName=="")&&(filterApplDate=="")&&(filterStartDate=="")&&(filterTrvlId=="")&&(filterTrvlReq==""))
  		  			{
  					alert('Please enter/select atleast one field to apply filter');
  					return false;
  					}   	
   				}
   				else
   				{
   				//alert(document.getElementById('paraFrm_status').value);
   					var filterTrvlAsignedTo= document.getElementById('paraFrm_filterTrvlAsignedTo').value;
   					var filterAccomAsignedTo= document.getElementById('paraFrm_filterAccomAsignedTo').value;
   					var filterConvAsignedTo= document.getElementById('paraFrm_filterConvAsignedTo').value;
   					if((filterEmpName=="")&&(filterInitName=="")&&(filterApplDate=="")&&(filterStartDate=="")&&(filterTrvlId=="") &&(filterTrvlAsignedTo=="") &&(filterAccomAsignedTo=="") &&(filterConvAsignedTo==""))
  		  				{
  						alert('Please enter/select atleast one field to apply filter');
  						return false;
  						}  
   				}
				  if(filterApplDate!="") {
					  if(!validateDate('paraFrm_filterApplDate','trvl.applDate'))
						return false;
						}
				
					if(filterStartDate!="")
					{
					if(!validateDate('paraFrm_filterStartDate','date.journey'))
					return false;
					}
   				
   				document.getElementById("paraFrm_searchFlag").value="true";
				document.getElementById("showFilter").style.display='none';
				document.getElementById("hideFilter").style.display='none';
				}catch(e){
				//alert(e);
				}
   	
   		}
		
		
		</script>



		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>
					<s:if test="%{pen}">
						<td height="27" class="formtxt"><strong>Pending
						Travel List</strong></td>
					</s:if>



					<s:elseif test="%{assigned}">
						<td height="27" class="formtxt"><strong> Assigned
						List</strong></td>
					</s:elseif>

					<s:elseif test="%{booked}">
						<td height="27" class="formtxt"><strong> Scheduled
						Travel List</strong></td>
					</s:elseif>


					<s:elseif test="%{rejected}">
						<td height="27" class="formtxt"><strong>Rejected
						List</strong></td>
					</s:elseif>

					<s:elseif test="%{submited}">
						<td height="27" class="formtxt"><strong>Submit List</strong></td>
					</s:elseif>

					<s:elseif test="%{pendingCancel}">
						<td height="27" class="formtxt"><strong>Pending
						Cancel List</strong></td>
					</s:elseif>

					<s:elseif test="%{canceled}">
						<td height="27" class="formtxt"><strong>Canceled
						List</strong></td>
					</s:elseif>

					<s:else>


						<td height="27" class="formtxt"><strong>Pending
						Travel List</strong></td>
					</s:else>



				</tr>

				<s:if test="pageFlag">
					<tr>
						<%!int totalPage = 0;
	int PageNo = 0;
	int row1 = 0;%>
						<%
								try {
								totalPage = (Integer) request.getAttribute("totalPage");
								PageNo = (Integer) request.getAttribute("pageNo");
								row1 = (Integer) request.getAttribute("row");
							} catch (Exception e) {

							}
						%>
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
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N','N')"> <img
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

				<!-- iterator -->


				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg"
						border="0">
						<tr>

							<td width="7%" valign="top" class="formth" nowrap="nowrap"
								colspan="1"><strong><label class="set" id="sr.No"
								name="sr.No" ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></strong></td>
							<td valign="top" class="formth" colspan="1"><strong><label
								class="set" id="travel.id" name="travel.id"
								ondblclick="callShowDiv(this);"><%=label.get("travel.id")%></label></strong></td>
							<td valign="top" class="formth" colspan="1"><strong><label
								class="set" id="emp.name" name="emp.name"
								ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></strong></td>
							<s:if
								test="pen=='true' || submited=='true' || pendingCancel=='true'">
								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="trvl.reqName1" name="trvl.reqNamee"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.reqName")%></label></strong></td>
							</s:if>

							<s:if test="%{rejected}">
								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="trvl.reqName2" name="trvl.reqName"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.reqName")%></label></strong></td>
							</s:if>

							<td width="18%" valign="top" class="formth" colspan="1"><strong><label
								class="set" id="init.name" name="init.name"
								ondblclick="callShowDiv(this);"><%=label.get("init.name")%></label></strong></td>

							<td width="13%" valign="top" class="formth" colspan="1"><strong><label
								class="set" id="appl.date" name="appl.date"
								ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label></strong></td>

							<td valign="top" class="formth" colspan="1"><strong><label
								class="set" id="date.journey" name="date.journey"
								ondblclick="callShowDiv(this);"><%=label.get("date.journey")%></label></strong></td>

							<s:if test="%{pen}">
								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="schdl.asgnmt" name="schdl.asgnmt"
									ondblclick="callShowDiv(this);"><%=label.get("schdl.asgnmt")%></label></strong></td>
								<!--  <td width="13%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="view.application" name="view.application"
									ondblclick="callShowDiv(this);"><%=label.get("view.application")%></label></strong></td>-->
							</s:if>

							<s:if test="canceled=='true' || assigned=='true'">
								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="trvl.asgn" name="trvl.asgn"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.asgn")%></label></strong></td>

								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="lodge.assgn" name="lodge.assgn"
									ondblclick="callShowDiv(this);"><%=label.get("lodge.assgn")%></label></strong></td>

								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="localConv.asgn" name="localConv.asgn"
									ondblclick="callShowDiv(this);"><%=label.get("localConv.asgn")%></label></strong></td>

								<s:if test="onLoadFlg">
									<td valign="top" class="formth" colspan="1"><strong><label
										class="set" id="reAssign.application"
										name="reAssign.application" ondblclick="callShowDiv(this);"><%=label.get("reAssign.application")%></label></strong></td>
								</s:if>


								<!-- <td width="5%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="view.application2" name="view.application"
									ondblclick="callShowDiv(this);"><%=label.get("view.application")%></label></strong></td>-->
								<!--<td width="13%" valign="top" class="formth" colspan="1">&nbsp;</td>
							-->
							</s:if>

							<s:if test="booked">

								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="trvl.asgn" name="trvl.asgn"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.asgn")%></label></strong></td>

								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="lodge.assgn" name="lodge.assgn"
									ondblclick="callShowDiv(this);"><%=label.get("lodge.assgn")%></label></strong></td>

								<td valign="top" class="formth" colspan="1"><strong><label
									class="set" id="localConv.asgn" name="localConv.asgn"
									ondblclick="callShowDiv(this);"><%=label.get("localConv.asgn")%></label></strong></td>
									
								<td valign="top" class="formth" colspan="1"><strong>Details</strong></td>


							</s:if>

							<s:if test="submited">
								<td width="13%" valign="top" class="formth" colspan="1">Options</td>
							</s:if>

						</tr>


						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>



						<%!int i = 0;%>
						<%
						int k = 1;
						%>

						<s:iterator value="travelList">
							<s:hidden name="editFlg" />
							<s:hidden name="penFlg" />
							<s:hidden name="assignedFlg" />
							<s:hidden name="bookedFlg" />
							<s:hidden name="rejectedFlg" />
							<s:hidden name="submitFlg" />
							<s:hidden name="cancelFlag" />
							<s:hidden name="travelIndAppId" />
							<s:hidden name="trvlAsignedTo" />
							<s:hidden name="convAsignedTo" />
							<s:hidden name="lodgeAsignedTo" />
							<s:hidden name="trvlAsignedToId" />
							<s:hidden name="convAsignedToId" />
							<s:hidden name="lodgeAsignedToId" />
							<s:hidden name="empGrade" />
							<s:hidden name="tourTrvSts" />
							<s:hidden name="tourConvSts" />
							<s:hidden name="tourAccomSts" />
							<s:hidden name="bookedDtlFlg" />

							<!--<s:if test="%{bookedFlg}">
								<tr id="editTr<%=k%>"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);"
									ondblclick="javascript:viewBookedDetails('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>',<s:property value="empId" />,<s:property value="applDate" />,'<s:property value="trvlinitrId"/>','<s:property value="holdStatus"/>');">
							</s:if>
							<s:else></s:else>
								--><tr id="editTr<%=k%>" title="double click to view"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this);"
									ondblclick="javascript:viewDetails('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property value="chkTypeFlag"/>','<s:property value="holdStatus"/>');">
							

							<s:hidden name="travelAppId" />
							<s:hidden name="trvlinitrId" />
							<s:hidden name="trvlEmpId" />
							<s:hidden name="applDate" />

							<s:hidden name="deskStatus" />
							<s:hidden name="chkTypeFlag" />
							<s:hidden name="deskId" />

							<s:hidden name="tpViolationStr" id="<%="tpViolationStr"+k %>" />

							<input type="hidden" name="holdStatus" id="holdStatus<%=k%>"
								value='<s:property value="holdStatus"/>' />
							<input type="hidden" name="dupDeskId" id="dupDeskId<%=k%>"
								value='<s:property value="deskId"/>' />
							<td class="sortableTD" nowrap="nowrap" colspan="1" align="center"><%=++row1%>
							</td>
							<td class="sortableTD" width="13%" colspan="1"><s:property
								value="deskTrvlAppId" /> &nbsp;</td>

							<td class="sortableTD" width="18%" colspan="1"><s:property
								value="trvlEmpName" />&nbsp;</td>

							<s:if
								test="penFlg=='true' || submitFlg=='true' || pendingCancelFlg=='true'">
								<td class="sortableTD" width="18%" colspan="1"><s:property
									value="trvlReqName" />&nbsp;</td>
							</s:if>

							<s:if test="%{rejectedFlg}">
								<td class="sortableTD" width="18%" colspan="1"><s:property
									value="trvlReqName" />&nbsp;</td>
							</s:if>

							<td class="sortableTD" width="18%" colspan="1"><s:property
								value="trvlInitrName" />&nbsp;</td>

							<td class="sortableTD" width="13%" colspan="1" align="center"><s:property
								value="applDate" />&nbsp;</td>

							<td class="sortableTD" width="13%" colspan="1" align="center"><s:property
								value="journeyDate" />&nbsp;</td>

							<s:if test="%{penFlg}">
								<td class="sortableTD" width="13%" align="center" colspan="1"><input
									type="checkbox" class="checkbox" id="chkAsgnmt<%=k%>"
									<s:property value="chkAsgnmt" /> name="chkAsgnmt"
									onclick="return chkConv('<%=k%>');" /> <input type="hidden"
									name="hidChkAsgnmt" id="hidChkAsgnmt<%=k%>"
									value="<s:property value="hidChkAsgnmt" />" /> <input
									type="hidden" name="hidTrvlEmpId" id="hidTrvlEmpId<%=k%>" />
								&nbsp;</td>



								<!--  <td class="sortableTD" nowrap="nowrap" align="center"
									colspan="1" width="13%"><input type="button"
									name="view_Details" class="token" value=" View "
									onclick="viewDetails('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property value="chkTypeFlag"/>')" />
								</td>cancelFlag-->
							</s:if>

							<s:elseif test="%{cancelFlag=='true' || assignedFlg=='true'}">
								<!--  <td class="sortableTD" align="center"><input
										type="checkbox" class="checkbox" name="chktrvlAsigned"
										id="chktrvlAsigned<%=k%>" <s:property value="chktrvlAsigned"/>
										disabled="disabled" /> <input type="hidden"
										name="hidChktrvlAsigned" id="hidChktrvlAsigned<%=k%>"
										value="<s:property value="hidChktrvlAsigned" />" /> &nbsp;</td>

									<td class="sortableTD" align="center"><input
										type="checkbox" class="checkbox" name="chkConvAsigned"
										id="chkConvAsigned<%=k%>" <s:property value="chkConvAsigned"/>
										disabled="disabled" /> <input type="hidden"
										name="hidChkConvAsigned" id="hidChkConvAsigned<%=k%>"
										value="<s:property value="hidChkConvAsigned" />" /> &nbsp;</td>

									<td class="sortableTD" align="center"><input
										type="checkbox" class="checkbox" name="chkLodgeAsigned"
										id="chkLodgeAsigned<%=k%>"
										<s:property value="chkLodgeAsigned"/> disabled="disabled" />
									<input type="hidden" name="hidChkLodgeAsigned"
										id="hidChkLodgeAsigned<%=k%>"
										value="<s:property value="hidChkLodgeAsigned" />" />&nbsp;</td>-->

								<td class="sortableTD" align="center"><s:property
									value="trvlAsignedTo" /> &nbsp;</td>

								<td class="sortableTD" align="center"><s:property
									value="lodgeAsignedTo" /> &nbsp;</td>

								<td class="sortableTD" align="center"><s:property
									value="convAsignedTo" /> &nbsp;</td>

								<s:if test="onLoadFlg">


									<td class="sortableTD" align="center"><s:if
										test='%{deskStatus!="NC"}'>
										<input type="button" class="token" value="Reassign"
											onclick="return callEdit('<s:property  value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property  value="deskId"/>','<%=k%>','<s:property  value="holdStatus"/>','<s:property  value="trvlAsignedToId"/>','<s:property  value="convAsignedToId"/>','<s:property  value="lodgeAsignedToId"/>');"/>
									</s:if> &nbsp;</td>


								</s:if>


							</s:elseif>

							<s:elseif test="%{bookedFlg}">
								<td class="sortableTD" align="center"><s:property
									value="trvlAsignedTo" /> &nbsp;</td>

								<td class="sortableTD" align="center"><s:property
									value="lodgeAsignedTo" /> &nbsp;</td>

								<td class="sortableTD" align="center"><s:property
									value="convAsignedTo" /> &nbsp;</td>
									
								<td class="sortableTD" align="center">
								<s:if test="bookedDtlFlg"><input type="button" value="Booking Details" onclick="viewBookedDetails('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>',<s:property value="empId" />,<s:property value="applDate" />,'<s:property value="trvlinitrId"/>','<s:property value="holdStatus"/>');"></s:if>
								<s:else><input type="button" value="Booking Details" onclick="viewAppDetails('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property value="chkTypeFlag"/>','<s:property value="holdStatus"/>');"></s:else></td>

							</s:elseif>

							<s:elseif test="%{submitFlg}">
								<td class="sortableTD" align="center" nowrap="nowrap"><s:if test="bookedDtlFlg"><input type="button"
									class="token" value="Give Option"
									onclick="giveOption('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property value="chkTypeFlag"/>')" />
								<input type="button" class="token" value="Start booking"
									onclick="startBooking('<s:property value="travelAppId"/>','<s:property value="travelIndAppId"/>','<s:property value="chkTypeFlag"/>')" /></s:if>
								&nbsp;</td>


							</s:elseif>



							</tr>
							<script>
							
						
							</script>
							<script>
							try{
							
								if(document.getElementById('tpViolationStr<%=k%>').value != "" 
								&& document.getElementById('tpViolationStr<%=k%>').value != "PND" 
								&& document.getElementById('tpViolationStr<%=k%>').value != "NOT")
								{
									//alert('in if : ');
									document.getElementById('editTr<%=k%>').style.background='#F08080';
								}
							
							
							
							if(document.getElementById('holdStatus<%=k%>').value=="H"){
							document.getElementById('editTr<%=k%>').style.background = '#E6E4DB';
							if(document.getElementById('paraFrm_status').value=="P")
							document.getElementById('chkAsgnmt<%=k%>').disabled = true;
							}
							if(document.getElementById('dupDeskId<%=k%>').value!=""){
								if(document.getElementById('dupDeskId<%=k%>').value==
								   document.getElementById('paraFrm_hiddenEdit').value)
								   document.getElementById('editTr<%=k%>').style.background = '#FFFFCC';
								   }
								   }catch(e){
								   //alert(e);
								   }
							</script>
							<%
							k++;
							%>
						</s:iterator>
						<%
						i = k;
						%>
					</table>
					</td>

				</tr>

				<s:if test="pen">

					<tr>
						<td width="100%" colspan="8">
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<s:if test="onLoadFlg">
									<td width="13%" align="right"><input type="button"
										class="token" value="Check Allocation"
										onclick="return callData();" />&nbsp;</td>
								</s:if>

								<td width="13%" align="left"><s:if test="noData"></s:if> <s:else>
									<input type="button" class="token" value=" Assign "
										onclick="return callSchdlr();">
								</s:else></td>
							</tr>
						</table>
						</td>
					</tr>

				</s:if>

				<s:if test="assigned">

					<tr>
						<td width="100%" colspan="8">
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<s:if test="onLoadFlg">
									<td width="13%" align="center"><input type="button"
										class="token" value="Check Allocation"
										onclick="return callData();" />&nbsp;</td>
								</s:if>
							</tr>
						</table>
						</td>
					</tr>

				</s:if>

				<!-- /iterator -->
			</table>
			</td>
		</tr>

		<s:if test="schViewFlg">
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class=formbg>

					<tr>
						<td height="27" class="formtxt"><strong>Scheduler/Sub-Scheduler
						List </strong></td>
					</tr>


					<tr>
						<td width="100%" colspan="8">
						<table width="100%" cellpadding="1" cellspacing="1" class="formbg"
							border="0">
							<tr>
								<td width="7%" valign="top" class="formth" nowrap="nowrap"
									colspan="1"><strong><label class="set"
									id="sr.No1" name="sr.No" ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></strong></td>
								<td width="15%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="emp.id" name="emp.id"
									ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></strong></td>
								<td width="22%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="schdlr.name" name="schdlr.name"
									ondblclick="callShowDiv(this);"><%=label.get("schdlr.name")%></label></strong></td>


								<td width="15%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="travel" name="travel"
									ondblclick="callShowDiv(this);"><%=label.get("travel")%></label></strong>
								</td>

								<td width="15%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="lodging" name="lodging"
									ondblclick="callShowDiv(this);"><%=label.get("lodging")%></label></strong>
								</td>

								<td width="15%" valign="top" class="formth" colspan="1"><strong><label
									class="set" id="local.conv" name="local.conv"
									ondblclick="callShowDiv(this);"><%=label.get("local.conv")%></label></strong></td>




							</tr>

							<%!int j = 0;%>
							<%
							int m = 1;
							%>

							<s:iterator value="schedlrList">
								<s:hidden name="hidSchCode" />
								<tr id="schTr<%=m%>">
									<td class="sortableTD" nowrap="nowrap" colspan="1"><%=m%></td>
									<td class="sortableTD" width="13%" colspan="1"><s:property
										value="schEmpToken" /> <s:hidden name="schEmpId" id='<%="schEmpId" + m%>'/><s:hidden
										name="schEmpToken" />&nbsp;</td>

									<td class="sortableTD" width="18%" colspan="1"><s:property
										value="schEmpName" /><s:hidden name="schEmpName" />&nbsp;</td>

									<td class="sortableTD" width="18%" colspan="1" align="center"><input
										type="radio" name="trvlRadio" id="trvlRadio<%=m%>"
										<s:property value="trvlRadio" /> onclick="return chkTravel();" />
									<input type="hidden" name="hidTrvlRadio"
										id="hidTrvlRadio<%=m%>"
										value="<s:property value="hidTrvlRadio" />" />&nbsp;</td>



									<td class="sortableTD" width="13%" colspan="1" align="center"><input
										type="radio" name="lodgeRadio" id="lodgeRadio<%=m%>"
										<s:property value="lodgeRadio" /> onclick="return chkLodge();">
									<input type="hidden" name="hidLodgeRadio"
										id="hidLodgeRadio<%=m%>"
										value="<s:property value="hidLodgeRadio" />" />&nbsp;</td>

									<td class="sortableTD" width="13%" colspan="1" align="center"><input
										type="radio" name="loclConvRadio" id="loclConvRadio<%=m%>"
										<s:property value="loclConvRadio" />
										onclick="return chkLocalConv();" /> <input type="hidden"
										name="hidLoclConvRadio" id="hidLoclConvRadio<%=m%>"
										value="<s:property value="hidLoclConvRadio" />" />&nbsp;</td>



								</tr>
								<script>								
								
								if(document.getElementById('schEmpId<%=m%>').value==
								   document.getElementById('paraFrm_colorId').value)
								   		document.getElementById('schTr<%=m%>').style.background = '#FFFFCC';
								
							</script>

								<%
								m++;
								%>
							</s:iterator>
							<%
							j = m;
							%>

						</table>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button"
							class="save" value="  Save" onclick="return callSave();"></td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>
		

		<s:if test="noData">
		</s:if>
		<s:else>
			<tr>

				<td>
				<table width="100%">
					<tr>
						<td width="8%"><b>Note : -</b></td>
						<td width="92%"><input type="text" disabled="disabled"
							style="background-color: #F08080; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates policy violated records.</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="text" disabled="disabled"
							style="background-color: #FFC488; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates records pending for cancellation.</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="text" disabled="disabled"
							style="background-color: #E6E4DB; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates hold records.</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="text" disabled="disabled"
							style="background-color: #FFFFCC; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
						Indicates Main Scheduler.</td>
					</tr>

				</table>
				</td>

			</tr>
		</s:else>






		<!--<s:if test="pen">
			<s:if test="noData"></s:if>
			<s:else>
				<tr>
					<td colspan="1" align="left"><input type="button"
						class="token" value="Assigned List" onclick="return callData();" /></td>
				</tr>
				<s:if test="colorFlag">
					<tr>
						<td colspan="1" align="left">Note :- Yellow color indicates
						Main Scheduler.</td>
					</tr>
				</s:if>
			</s:else>
			<s:if test="onLoadFlg">
				<tr>
					<td colspan="1" align="left"><input type="button"
						class="token" value="Check Allocation"
						onclick="return callData();" /></td>
				</tr>
			</s:if>
			<s:if test="noData"></s:if>
			<s:else>
				<s:if test="colorFlag">
					<tr>
						<td colspan="1" align="left">Note :- Yellow color indicates
						Main Scheduler.</td>
					</tr>
				</s:if>
			</s:else>
		</s:if>
		
		
	-->
	</table>
</s:form>

<script>

function newRowColor(cell)
   		 {   		
		   cell.className='Cell_bg_first';
	    }
	    
 function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
	}
function callSchdlr(){
if(chkTrv()){
	document.getElementById('paraFrm').target = "main";
	document.getElementById('paraFrm').action = "TravelDesk_callSchdlr.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
	}else{
	alert("Please select atleast one record to assign.");
	return false;
	}
}


function callSave(){
try{
if(document.getElementById('paraFrm_status').value=="A"){
//alert("status assigned");
if(chkSch()){
	document.getElementById('paraFrm').target = "main";
	document.getElementById('paraFrm').action = "TravelDesk_save.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";	
	return false;
	}else{
	alert("Please assign Travel, Accommodation & Local Conveyance.");
	return false;
	}

}else {
//alert("else");
callSaveDesk();
	
}
}catch(e){
//alert(e);
}
}
function callSaveDesk(){
	if(chkTrv()){
		if(chkSch()){		
	var con=confirm('Do you really want to assign this application ? ');
	if(con){
	document.getElementById('paraFrm').target = "main";
	document.getElementById('paraFrm').action = "TravelDesk_save.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
		}else{	
		return false;
		}	
	}else{
	alert("Please assign Travel, Accommodation & Local Conveyance.");
	return false;
	}	
}else{
	alert('Please select atleast one record to assign.');
	 return false;
	}	
}

function chkTrv(){
//alert(document.getElementById('paraFrm_status').value);
if(document.getElementById('paraFrm_status').value=="CC"){
	return true;
	}else{
	var flag='<%=i %>';
	//alert(flag);
	  for(var a=1;a<flag;a++){
	   if(document.getElementById('chkAsgnmt'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	 }
}
  
function chkSch(){
	var flag='<%=j %>';
	for(var a=1;a<flag;a++){
		var varTrvlRadio=document.getElementById('hidTrvlRadio'+a).value;
		if(document.getElementById('trvlRadio'+a).checked == true){
		if(varTrvlRadio=="Y")
		break;		
		}
	}

	for(var a=1;a<flag;a++){
	var varLoclConvRadio=document.getElementById('hidLoclConvRadio'+a).value;
		if(document.getElementById('loclConvRadio'+a).checked == true){
		if(varLoclConvRadio=="Y")
		break;	
		}
	}
	
	for(var a=1;a<flag;a++){
	var varLodgeRadio=document.getElementById('hidLodgeRadio'+a).value;	
		if(document.getElementById('lodgeRadio'+a).checked == true){
		if(varLodgeRadio=="Y")
		break;
		}
	}
	//alert("varTrvlRadio"+varTrvlRadio);
	//alert("varLoclConvRadio"+varLoclConvRadio);
	///alert("varLodgeRadio"+varLodgeRadio);
	if(varTrvlRadio == "Y" && varLoclConvRadio=="Y" && varLodgeRadio=="Y")
	   {	
	 	    return true;
	   }
	   else
	   {
	  		 return false;
	   }
	
	

}
	
	
	
	
	
	   /*if(varTrvlRadio == "Y" || varLoclConvRadio=="Y" || varLodgeRadio=="Y")
	   {	
	 	    return true;
	   }
	   else
	   {
	   return false;
	   }	*/   


function chkTravel(){
	var count=<%=j%>;
	for(var a=1;a<count;a++){
   		if(document.getElementById('trvlRadio'+a).checked == true){
		document.getElementById('hidTrvlRadio'+a).value='Y'; 
   		}else{
   		document.getElementById('hidTrvlRadio'+a).value='N'; 
   		}
	}
}


function chkLocalConv(){
	var count=<%=j%>;
	for(var a=1;a<count;a++){
   		if(document.getElementById('loclConvRadio'+a).checked == true){
		document.getElementById('hidLoclConvRadio'+a).value='Y'; 
   		}else{
   		document.getElementById('hidLoclConvRadio'+a).value='N'; 
   		}
	}
}



function chkLodge(){
	var count=<%=j%>;
	for(var a=1;a<count;a++){
   		if(document.getElementById('lodgeRadio'+a).checked == true){
		document.getElementById('hidLodgeRadio'+a).value='Y'; 
   		}else{
   		document.getElementById('hidLodgeRadio'+a).value='N'; 
   		}
	}
}

function chkConv(row){
	try{
   		if(document.getElementById('chkAsgnmt'+row).checked == true){   		
		document.getElementById('hidChkAsgnmt'+row).value='Y';
		document.getElementById('hidTrvlEmpId'+row).value=document.getElementById('dupDeskId'+row).value;
   		}else{
   		document.getElementById('hidChkAsgnmt'+row).value='N'; 
   		document.getElementById('hidTrvlEmpId'+row).value='';
   		}
	}catch(e){
	//alert(e);
	}

}

chkConvAfter();
function chkConvAfter(){
try{
	var count=<%=i%>;
	
	for(var a=1;a<count;a++){
   		if(document.getElementById('chkAsgnmt'+a).checked == true){ 
   		document.getElementById('hidTrvlEmpId'+a).value=document.getElementById('dupDeskId'+a).value;  		
		document.getElementById('hidChkAsgnmt'+a).value='Y';
   		}else{
   		document.getElementById('hidChkAsgnmt'+a).value='N'; 
   		}
	}
	}catch(e){
	//alert(e);
	}

}

function callEdit(travelAppId,travelIndiAppId,id,i,holdStatus,trvlToId,convToId,LodgToId){
//alert(holdStatus);
if(holdStatus!="H"){
	document.getElementById('paraFrm_singleClickAssignFlag').value="true";
	document.getElementById('paraFrm').target = "main";
	document.getElementById('paraFrm_tmsTrvlId').value=travelAppId;
	document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId; 
	document.getElementById('paraFrm_hiddenEdit').value=id;
	document.getElementById('paraFrm_trvlAsignedToEdit').value=trvlToId;
	document.getElementById('paraFrm_convAsignedToEdit').value=convToId;
	document.getElementById('paraFrm_lodgeAsignedToEdit').value=LodgToId;
	document.getElementById('editTr'+i).style.backgroundcolor='#FF8383';
	document.getElementById('paraFrm').action = "TravelDesk_edit.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
	}else{
	return false;
	}

}

/*function elseEdit(){
alert("You can not edit the assigned list.");
return false;
}*/

//To get assigned list by clicking Assigned list button.
function callData(){
	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
	document.getElementById('paraFrm').target = "wind";
	document.getElementById('paraFrm').action = "TravelDesk_callAsgnList.action";
	document.getElementById('paraFrm').submit();
}

//To view Travel Application
function viewDetails(travelAppId,travelIndiAppId,typeFlag,holdStatus){ 
 //AppId,AppCode,self/guest
  if(document.getElementById('paraFrm_singleClickAssignFlag').value=="true"){
  		document.getElementById('paraFrm_singleClickAssignFlag').value="false";
 }
 else if(document.getElementById('paraFrm_singleClickBookFlag').value=="true"){
	 document.getElementById('paraFrm_singleClickBookFlag').value="false";
 }
 else if(document.getElementById('paraFrm_singleClickSubmitFlag').value=="true"){
	 document.getElementById('paraFrm_singleClickSubmitFlag').value="false";
 }
 else{
 if(holdStatus!="H"){
 		 document.getElementById('paraFrm_singleClickAssignFlag').value="false";
 		 document.getElementById('paraFrm_singleClickBookFlag').value="false";
 		 document.getElementById('paraFrm_singleClickSubmitFlag').value="false";
 		 
 		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; 
		document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId; 
		document.getElementById('paraFrm_tmsChkTypeFlg').value = typeFlag; 	
		document.getElementById('paraFrm_deskFlag').value = "true"; 			
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelAppvr_callView.action";
		//window.open(actionName,'','top=200,left=200,width=800,height=450,scrollbars=yes')	  
		var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}else{
		return false;
		}
		}
		
}

//To view self-managed scheduled Travel Application
function viewAppDetails(travelAppId,travelIndiAppId,typeFlag,holdStatus){  //AppId,AppCode,self/guest
 if(holdStatus!="H"){
 document.getElementById('paraFrm_singleClickBookFlag').value="true";
 		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; 
		document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId; 
		document.getElementById('paraFrm_tmsChkTypeFlg').value = typeFlag; 	
		document.getElementById('paraFrm_deskFlag').value = "true"; 			
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelAppvr_callView.action?deskBookFlag=true";
		//window.open(actionName,'','top=200,left=200,width=800,height=450,scrollbars=yes')	  
		var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}else{
		return false;
		}
		
}


function startBooking(travelAppId,travelIndiAppId,typeFlag){
//alert("book");
		document.getElementById('paraFrm_singleClickSubmitFlag').value="true";
		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; 
		document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId; 
		document.getElementById('paraFrm_tmsChkTypeFlg').value = typeFlag; 	
		//document.getElementById('paraFrm_deskFlag').value = "true"; 			
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "TravelDesk_startBooking.action";
		var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
}

function giveOption(travelAppId,travelIndiAppId,typeFlag){
document.getElementById('paraFrm_singleClickSubmitFlag').value="true";
var con=confirm('Do you really want to move this record to Pending Travel List ? ');
	if(con){
		document.getElementById('paraFrm_tmsTrvlId').value = travelAppId; 
		document.getElementById('paraFrm_tmsTrvlIndiId').value = travelIndiAppId; 
		document.getElementById('paraFrm_tmsChkTypeFlg').value = typeFlag; 	
		document.getElementById('paraFrm').action = "TravelDesk_giveOption.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}else{
		return false;
		}

}



 function viewBookedDetails(appId,appCode,empId,appDate,initId,holdStatus){
 				//alert(holdStatus);
 				if(holdStatus!="H"){
 					document.getElementById('paraFrm_singleClickBookFlag').value="true";
	           		win=window.open('','win','top=260,left=250,width=900,height=600,scrollbars=yes,status=no,resizable=no');
					document.getElementById("paraFrm").target="win"; 
					//document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&userType=SCH"; 
					document.getElementById("paraFrm").action="TravelApplication_bookingDtls.action?applicationId="+appId+"&empApplId="+appCode+"&status=FI&empId="+empId+"&applDate="+appDate+"&iniEmpId="+initId+"&userType=SCH&reqFrmDesk=true&dtlsType=";					
					document.getElementById("paraFrm").submit();
					document.getElementById("paraFrm").target="main";
					}else{
					return false;
					}
	          }


	 
	/*function callPage(id)
	{
	    document.getElementById('paraFrm_myPage').value=id;		  
		document.getElementById('paraFrm').action = 'TravelDesk_callStatus.action?status='+document.getElementById('paraFrm_status').value;
		document.getElementById('paraFrm').submit();
	}*/
	
	function callPage(id)
	{
	 var pageNo=document.getElementById('paraFrm_myPage').value	
	if(id=="P")
	document.getElementById('paraFrm_myPage').value=eval(pageNo)-1;	
	else if(id=="N")	
	document.getElementById('paraFrm_myPage').value=eval(pageNo)+1;	
	else
	document.getElementById('paraFrm_myPage').value=id;		
	document.getElementById('paraFrm').action = 'TravelDesk_callStatus.action?status='+document.getElementById('paraFrm_status').value;
		document.getElementById('paraFrm').submit();
	}
	
	   function selectPage(appStatus)
   {     	
   		var pageNo;
   		 	pageNo = document.getElementById('selectname').value;
   		 	document.getElementById('paraFrm_myPage').value=pageNo;
   		 	document.getElementById('paraFrm').action = 'TravelDesk_callStatus.action?status='+document.getElementById('paraFrm_status').value;
   		 	document.getElementById('paraFrm').submit();
   	}
   	
   	

</script>


