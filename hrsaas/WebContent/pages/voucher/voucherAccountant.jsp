
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>


<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TravelExpDisbrsmnt" method="post" id="paraFrm"
	validate="true" target="main" theme="simple">
<s:hidden name="myPage" id="myPage" />

 
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%" colspan="7">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Voucher Disbursement </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	<tr>
			<td width="100%" colspan="7">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%"><b><label name="disbDiv" id="disbDiv6"
						ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label>
					:</b></td>
					<td width="30%"><s:hidden name="divisionCode" /><s:textfield
						name="divisionName" theme="simple" size="30" readonly="true" />&nbsp;
					<img src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TravelExpDisbrsmnt_f9division.action');"
						id="ctrlHide"></td>
					<td width="10%" align="center"><input type="button"
						name="searchBtn" class="token" value="   Filter   "
						onclick="searchByFilter();"></td>
					<td width="10%" align="center"><input type="button"
						name="clearBtn" class="token" value="   Clear   "
						onclick="clearFilter();"></td>
					<td width="30%" align="center"><input type="button"
						name="reportBtn" class="token" value="Generate Bank Statement"
						onclick="statementReport();"></td>
				</tr>

			</table>
			</td>
		</tr>
		<%	int totalPage = 0;
					int pageNo = 0;
			%>
		<tr>
			<td width="100%" colspan="7">
			<div id="first">
			<table width="100%" class="formbg" border="0">
				<tr>
					<script><!--
		    	function setAction(listType){
		    	document.getElementById('myPage').value="";
			    if(listType=="p"){
			      	document.getElementById('paraFrm').action='TravelExpDisbrsmnt_input.action';
			      	document.getElementById('paraFrm').submit();
			    }
			    if(listType=="c"){
			      	document.getElementById('paraFrm').action='TravelExpDisbrsmnt_getClosedList.action';
			      	document.getElementById('paraFrm').submit();
			     }
		    	}
		   			--></script>
					<td><a href="#" onclick="setAction('p')">Pending Claim List</a> | <a href="#" onclick="setAction('c')">Closed Claim List</a></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
			<%
			int row1 = 0;
		%>
		<tr>
				<td width="100%" colspan="7">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class=formbg>
					<tr>
						<td width="80%" class="formtxt"><strong>Pending
						Claim Disbursement List</strong></td>
						
						<td width="30%" align="right" ><b>Page:</b>
										<%
									
										%> <a href="#"
											onclick="callPage('1', 'F', '<%=totalPage%>', 'TravelExpDisbrsmnt_input.action');">
										<img title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp; <a
											href="#"
											onclick="callPage('P', 'P', '<%=totalPage%>', 'TravelExpDisbrsmnt_input.action');">
										<img title="Previous Page"
											src="../pages/common/img/previous.gif" width="10" height="10"
											class="iconImage" /> </a> <input type="text" name="pageNoField"
											id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
											onkeypress="callPageText(event, '<%=totalPage%>', 'TravelExpDisbrsmnt_input.action');return numbersOnly();" />
										of <%=totalPage%> <a href="#"
											onclick="callPage('N', 'N', '<%=totalPage%>', 'TravelExpDisbrsmnt_input.action');">
										<img title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp; <a href="#"
											onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TravelExpDisbrsmnt_input.action');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a></td>
						

					</tr>
					
					<tr>
						<td colspan=""><strong> &nbsp; </strong></td>
						
						<td  align="center"><input type="button"
							name="disburseBtn" class="token" value=" Disburse "
							onclick="callClaimDisburse();"></td>
						
					</tr>

					<tr>
						<td width="100%" colspan="7">
						<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
							<tr>
								<td width="5%" class="formth" colspan="1"><strong>Sr.No</strong></td>
								<td width="10%" class="formth" colspan="1"><strong>Division</strong></td>
								<td width="10%" class="formth" colspan="1"><strong>Voucher ID</strong></td>
								<td width="25%" class="formth" colspan="1"><strong>Employee
								Name</strong></td>
								<!-- 
										<td width="10%" class="formth" colspan="1"><strong>Claim
										Type</strong></td>
										 -->
								<td width="15%" class="formth" colspan="1"><strong>Application
								Date</strong></td>
								<td width="15%" class="formth" colspan="1"><strong>Claim
								Amount</strong></td>
									<td width="15%" class="formth" colspan="1"><strong>Disburse
								Amount</strong></td>
								<td width="15%" class="formth" colspan="1">Status</td>
								<td width="15%" class="formth" colspan="1"><strong>Disburse</strong></td>
							</tr>
							<%!int i = 0;%>
							<%! int b = 0;%>
							<%
									int k = 1, count2 = 0, x = 0;
							int Sr = pageNo * 20 - 20;
									%>

							<s:iterator value="clmDisbrList">
								<tr id="trvlRow_<%=k %>" <%if(count2%2==0){
						%>
									class="tableCell1" <%}else{ %> class="tableCell2"
									<%	}count2++; %> title="double click to edit"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count2%2 %>);"
									ondblclick="javascript:viewDetails('<s:property value="itClmAppId"/>','<s:property value="itClmStatus"/>','<s:property value="appId"/>','<s:property value="appCode"/>','<s:property value="currentStatus"/>')">
									<td class="sortableTD"><%=++Sr%> <s:hidden
										name="itClmAppId" /> <s:hidden name="itClmStatus"
										id='<%="itClmStatus"+k%>' /> <s:hidden name="currentStatus"
										id='<%="currentStatus"+k%>' />
									<td class="sortableTD"><s:property
										value="pendingClaimListDivision" /></td>
									<td class="sortableTD"><s:hidden name="trvlId" /><s:property
										value="trvlId" /></td>
									<td class="sortableTD"><s:hidden name="appId" /><s:hidden
										name="appCode" /><s:property value="itEmpName" />&nbsp;</td>
									<!-- 
											<td class="sortableTD" align="center"><s:property
												value="itclmType" />&nbsp;</td>
											 -->
									<td class="sortableTD" nowrap="nowrap"><s:property
										value="itAppDate" />&nbsp;</td>
									<td class="sortableTD" align="right"><s:property
										value="itClmAmt" />&nbsp;<s:hidden name="acceptButton" /></td>
									<td class="sortableTD" align="right"><s:property
										value="itClmAmt" />&nbsp;<s:hidden name="acceptButton" /></td>	
									<td class="sortableTD"><s:if test='%{currentStatus =="T"}'>	
											Added to statement
											</s:if><s:else>
										<s:property value="displayClaimStatus" />
									</s:else></td>
									<td class="sortableTD" align="center"><!--<s:if test="acceptButton">
											<input
												type="button" class="token" value="Acknowledge"
												onclick="acceptClaimDisb('<s:property value="itClmAppId"/>',
												'<s:property value="appId"/>','<s:property value="appCode"/>')"> 
											</s:if>	
												--> <s:if test='%{currentStatus =="T"}'>

										<input type="hidden" name="hidClaimCode"
											id="hidClaimCode<%=x%>" />
										<input type="checkbox" class="sortableTD" id="clmChkBox<%=x%>"
											name="clmChkBox"
											onclick="callForClaimDisburse('<s:property value="appCode"/>','<%=x%>')" />
									</s:if><s:else>
										<input type="button" class="token" value="Add to Statement"
											onclick="viewDetails('<s:property value="itClmAppId"/>',
												'<s:property value="itClmStatus"/>','<s:property value="appId"/>',
												'<s:property value="appCode"/>','<s:property value="currentStatus"/>','<s:property value="displayClaimStatus"/>');">
									</s:else></td>

								</tr>

								<script>
							
								if(document.getElementById('currentStatus<%=k%>').value=="P"){
								
								   document.getElementById('trvlRow_<%=k%>').style.background ='#FFFF33';
								 }
								
								</script>

								<%
										k++;
										x++;
										%>
							</s:iterator>
							<%
									i = k;
									b = x;
									%>


							<s:if test="true">
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
	</table>

</s:form>

<script>


function viewDetails(clmAppId,clmStatus,appId,appCode,currentStatus, claimAppStatus){ 
	 
	try{
	
	 
	document.getElementById('paraFrm_tmsClmAppId').value = clmAppId; 
	document.getElementById('paraFrm_tmsClmStatus').value = clmStatus; 
	document.getElementById('paraFrm_trvlAppId').value = appId; 
	document.getElementById('paraFrm_trvlAppCode').value = appCode; 
	document.getElementById('paraFrm').action = 'TravelExpDisbrsmnt_callView.action?disStatus='+currentStatus+'&claimAppStatus='+claimAppStatus;	  
	 
	document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target = "main";
	}catch(e){alert(e);}

	
}//end of viewDetails

function callForClaimDisburse(id,i){
 		if(document.getElementById('clmChkBox'+i).checked == true){
			document.getElementById('hidClaimCode'+i).value=id;
		} else {
			document.getElementById('hidClaimCode'+i).value="";
		}
 	}


function callClaimDisburse(){
	 		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action='TravelExpDisbrsmnt_sendClaimDisbursementMailSMS.action';
		   	document.getElementById('paraFrm').submit();
 	}
  	
 	function clearFilter(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'TravelExpDisbrsmnt_clearFilter.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function searchByFilter(){
		try{
				filterStatus = document.getElementById('paraFrm_status').value;
				if(filterStatus=="P"){
				   	document.getElementById('paraFrm').action='TravelExpDisbrsmnt_input.action';
				   	document.getElementById('paraFrm').submit();
				}
				if(filterStatus=="C") {
				   	document.getElementById('paraFrm').action='TravelExpDisbrsmnt_getClosedList.action';
				   	document.getElementById('paraFrm').submit();
				}
		}
		catch(e)
		{
			alert(e);
		}
	}
	
	function statementReport(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'TravelExpDisbrsmnt_generateBankStatementReport.action';
		document.getElementById('paraFrm').submit();
	}
 	
</script>
