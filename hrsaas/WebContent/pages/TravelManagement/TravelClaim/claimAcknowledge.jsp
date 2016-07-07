<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="ClmAcknowledgement" method="post" id="paraFrm"
	validate="true" target="main" theme="simple">
	<s:hidden name="hiddencode" />
	<s:hidden name="hiddenappldate" />
	<s:hidden name="hiddenapplCode" />
	<s:hidden name="hiddenapplId" />
	<s:hidden name="hiddengustflag" />
	<!-- for closed advance disburse  -->
	<s:hidden name="closedhiddencode" />
	<s:hidden name="closedhiddenappldate" />
	<s:hidden name="closedhiddenapplCode" />

	<s:hidden name="closedhiddenapplId" />
	<s:hidden name="gradeID" />
	<!-- CLAIM -->
	<s:hidden name="status" />
	<s:hidden name="noClaimData" />
	<s:hidden name="pen" />
	<s:hidden name="clsd" />
	<s:hidden name="totDisbrAmt" />
	<!-- For Paging -->
	<s:hidden name="myClaimPage" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="pageFlag" />
	<s:hidden name="trvlAppId" />
	<s:hidden name="trvlAppCode" />
	<!-- for navigation purpose -->
	<s:hidden name="tmsClmAppId" />
	<s:hidden name="tmsClmStatus" />
	<!-- CLAIM -->

	
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%" colspan="6">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Claim
					Acknowledgement List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="formbg">
				
				

<tr>
					<td colspan="6" ><strong
						class="text_head">Select Filters </strong></td>
				</tr>
				<tr>
					<td  width="10%" nowrap="nowrap"><label
						name="trvlid" id="travelIdName2"
						ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label>
					<font color="red"></font>:</td>
					<td  width="10%"><s:textfield name="travelId"
						theme="simple" size="15" /></td>
					<td  width="10%" nowrap="nowrap"><label
						name="employeeName" id="employeeName"
						ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label>
					:</td>
					<td  width="10%"><s:hidden name="searchempId"
						value="%{searchempId}" theme="simple" /> <s:hidden name="searchemptoken"
						value="%{searchemptoken}" theme="simple" /><s:textfield
						name="searchempName" theme="simple" size="15" readonly="true" />
					</td>
					<td  width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ClmAcknowledgement_f9employee.action');">
					</td>

					<td  width="10%" nowrap="nowrap"><a href="#"
						onclick="callsearch('0');">Search</a> | <a href="#" id="showLink"						
						onclick="clearSearch();">Clear</a></td>

				</tr>
				
				</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="6">

			<div id="first">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td>
					<table width="100%" class="formbg" id="table10" border="0">

					</table>

					</td>
				</tr>
				<%					
						int row1 = (Integer) request.getAttribute("row");
				%>
				<% int totalPage = 0;
			int pageNo = 0;	%>

				<s:if test="%{listType == 'pending'}">

					<!--------- DRAFT SECTION BEGINS - displaying the saved leave applications ------->



					<!-- PENDING CLAIM LIST BEGINS - ADDED BY Ganesh -->
					
					<tr>
						<td width="100%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0" class=formbg>
							<tr>
								<td height="27" class="formtxt"><strong>Pending
								Claim Acknowledgement List</strong></td>
								
								<td id="ctrlShow" width="70%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%>
									<a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'ClmAcknowledgement_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'ClmAcknowledgement_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'ClmAcknowledgement_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'ClmAcknowledgement_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ClmAcknowledgement_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>

							</tr>

							<tr>
								<td width="100%" colspan="10">
								<table width="100%" cellpadding="1" cellspacing="1"
									class="formbg">
									<tr>
										<td width="5%" class="formth" colspan="1"><strong>Sr.No</strong></td>
										<td width="10%" class="formth" colspan="1"><strong>Travel
										Id</strong></td>
										<td width="25%" class="formth" colspan="1"><strong>Employee
										Name</strong></td>
										<td width="10%" class="formth" colspan="1"><strong>Claim
										Type</strong></td>
										<td width="15%" class="formth" colspan="1"><strong>Application
										Date</strong></td>
										<td width="10%" class="formth" colspan="1"><strong>Claim
										Amount</strong></td>
										<td width="10%" class="formth" colspan="1">Status</td>
										<td width="30%" class="formth" colspan="2"><strong>Acknowledge</strong></td>
									</tr>
									<%!int i = 0;%>
									<%
										int k = 1, count2 = 0;
									%>

									<s:iterator value="clmDisbrList">
										<tr id="trvlRow_<%=k %>" <%if(count2%2==0){
						%>
											class="tableCell1" <%}else{ %> class="tableCell2"
											<%	}count2++; %> title=""
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count2%2 %>);"
											>
											<td class="sortableTD"><%=++row1%> <s:hidden
												name="itClmAppId" /> 
												
												<s:hidden name="itClmStatus"
												id='<%="itClmStatus"+k%>' /> <s:hidden name="currentStatus"
												id='<%="currentStatus"+k%>' />
											<td class="sortableTD"><s:hidden name="trvlId" /><s:property
												value="trvlId" /></td>
											<td class="sortableTD"><s:hidden name="appId" /><s:hidden
												name="appCode" /><s:hidden name="applicantId" /><s:property value="itEmpName" />&nbsp;</td>
											<td class="sortableTD" align="center"><s:property
												value="itclmType" />&nbsp;</td>
											<td class="sortableTD" nowrap="nowrap"><s:property
												value="itAppDate" />&nbsp;</td>
											<td class="sortableTD" align="right" nowrap="nowrap">
												<s:property value="itClmAmt" />
												<s:property value="totalCurrencyExpense" />
												&nbsp;
												<s:hidden name="acceptButton" />
											</td>
											<td class="sortableTD">
												<s:property value="displayClaimStatus" />
											</td>
											
											<td class="sortableTD" align="center">
												<s:if test="acceptButton">

											<!--<input type="button" class="token" value="View"
													onclick="javascript:viewDetails('<s:property value="itClmAppId"/>',
												'<s:property value="appId"/>','<s:property value="appCode"/>')"> -->
												
												<input type="button" class="token" value="View"
												onclick="viewDetails('<s:property value="itClmAppId"/>',
												'<s:property value="appId"/>','<s:property value="appCode"/>','<s:property value="applicantId"/>')">
												
												<!--<input type="button" class="token" value="Acknowledge"
													onclick="acceptClaimDisb('<s:property value="itClmAppId"/>',
												'<s:property value="appId"/>','<s:property value="appCode"/>')">
											


											--></s:if> <!--
												onclick="acceptClaimDisb('<s:property value="itClmAppId"/>',
												
												<input type="button" class="token" value="Disburse"
												onclick="viewDetails('<s:property value="itClmAppId"/>',
												'<s:property value="itClmStatus"/>','<s:property value="appId"/>',
												'<s:property value="appCode"/>','<s:property value="currentStatus"/>')">
											-->
											</td>

										</tr>

										<script>
							
								if(document.getElementById('currentStatus<%=k%>').value=="P"){
								
								   document.getElementById('trvlRow_<%=k%>').style.background ='#FFFF33';
								 }
								
								</script>

										<%
											k++;
										%>
									</s:iterator>
									<%
										i = k;
									%>


									<s:if test="noClaimData">
										<tr>
											<td width="100%" colspan="7" align="center"><font
												color="red">No Data To Display</font></td>
										</tr>
									</s:if>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				<tr>
			<td width="100%">
			<table border="0" width="100%">
				<tr>
						<td width="20%" align="right"><s:if test="clmDisbrList">
						<b>Total No. Of Records:</b>&nbsp;<s:property
							value="totalNoOfRecords" />
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
				</s:if>

			</table>
			</div>
			</td>
		</tr>


	</table>
</s:form>

<script>
 

		
		//========================CLAIM SCRIPTS ADDED BY Ganesh=====================
function acceptClaimDisb(clmAppId,appId,appCode){
alert(clmAppId);alert(appId);alert(appCode);
if(confirm('Do you want to accept the claim application?')){
	document.getElementById('paraFrm').action = 'ClmAcknowledgement_acceptClaimDisbursement.action?cailmAppId='+clmAppId
		+'&travelApplId='+appId+'&travelApplCode='+appCode;	  
	document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target = "main";
 
} 
else return false;
}		



function viewDetails(clmAppId,appId,appCode,applicantId){ 
	//alert("currentStatus -- "+currentStatus);
	
	//alert("clmAppId -- "+clmAppId);
	//alert("appId -- "+appId);
	//alert("appCode -- "+appCode);
	
	//alert("clmStatus -- "+clmStatus);
	
	document.getElementById('paraFrm_tmsClmAppId').value = clmAppId; 
	//document.getElementById('paraFrm_tmsClmStatus').value = clmStatus; 
	document.getElementById('paraFrm_trvlAppId').value = appId; 
	document.getElementById('paraFrm_trvlAppCode').value = appCode; 
	
	
		
	document.getElementById('paraFrm').action = 'TravelExpDisbrsmnt_callViewAck.action?cailmAppId='+clmAppId
		+'&travelApplId='+appId+'&travelApplCode='+appCode+' &applicantId='+applicantId;
	 
	document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target = "main";
}//end of viewDetails

function callsearch(id) 
 {
 		 
  	 	//document.getElementById('checkSearch').value=id;
  	 	 
  	 	 
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ClmAcknowledgement_input.action';
		document.getElementById('paraFrm').submit();

 }	
 
 function clearSearch()
 {
 		document.getElementById('paraFrm_travelId').value = ""; 
 		document.getElementById('paraFrm_searchempId').value = ""; 
 		document.getElementById('paraFrm_searchempName').value = ""; 
 		document.getElementById('paraFrm_searchemptoken').value = ""; 
 		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ClmAcknowledgement_input.action';
		document.getElementById('paraFrm').submit();	
 }

	
</script>



