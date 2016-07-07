<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AdvClmDisbursement" method="post" id="paraFrm"
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
	<s:hidden name="gradeID"/>
	<!-- CLAIM -->
	<s:hidden name="status" id="paraFrm_status"/>
	<s:hidden name="noClaimData" />
	<s:hidden name="pen" />
	<s:hidden name="clsd" />
	<s:hidden name="totDisbrAmt" />
	<!-- For Paging -->
	<s:hidden name="myClaimPage" />
	<s:hidden name="myPage" id="myPage"/>
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
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Advance 
					Disbursement </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!--<tr>
			<td width="100%">
			<table width="100%" align="center" id="table1">
				<tr>
					<td>
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li id="list1"><a href="AdvClmDisbursement_input.action"
							id="menuid1" class="on" onclick="showCurrent('menuid1','first')">
						<span>Advance Disbursement</span></a></li>

						<li id="list2"><a
							href="TravelExpDisbrsmnt_callStatus.action?status=P" id="menuid2"
							onclick="showCurrent( 'menuid2','second')"> <span>Claim
						Disbursement</span></a></li>

					</ul>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		-->
		<!-- DIV 1 for Advance disbursement Begin -->
		<tr>
		<td width="100%" colspan="5">
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
								onclick="javascript:callsF9(500,325,'AdvClmDisbursement_f9division.action');"
								id="ctrlHide"></td>
							<td width="10%" align="center"><input type="button" name="searchBtn"
								class="token" value="   Filter   " onclick="searchByFilter();"></td>
							<td width="10%" align="center"><input type="button" name="clearBtn"
								class="token" value="   Clear   " onclick="clearFilter();"></td>
							<td width="30%" align="center"><input type="button"
								name="reportBtn" class="token" value="Generate Bank Statement"
								onclick="statementReport();"></td>
						</tr>
				
				<!-- 
				<tr>
					<td class="text_head" colspan="3" width="90%"><strong
						class="forminnerhead">Select Filters </strong></td>
						<td width="10%"><input type="button" name="hideShowBtn" id="hideShowBtn" value="  Show  " class="token" onclick="showHideFilter();"></td>
				</tr>
				
				<tr>
					<td colspan="4">
					<table id="filterTable" style='display: none'>
						<tr>
							<td width="20%"><b><label name="disbDiv" id="disbDiv6"
								ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label>
							:</b></td>
							<td width="30%"><s:hidden name="divisionCode" /><s:textfield
								name="divisionName" theme="simple" size="30" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'AdvClmDisbursement_f9division.action');"
								id="ctrlHide"></td>
							<td width="20%"><input type="button" name="searchBtn"
								class="token" value="   Filter   " onclick="searchByFilter();"></td>
							<td width="30%" align="center"><input type="button"
								name="reportBtn" class="token" value="Generate Bank Statement"
								onclick="statementReport();"></td>
						</tr>
					</table>
					</td>
				</tr>
				 -->
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">

			<div id="first">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td>
					<table width="100%" class="formbg" id="table10" border="0">

						<tr>
							<script>
		    function setAction(listType){
		    	
			    if(listType=="p"){
			      	document.getElementById('paraFrm').action='AdvClmDisbursement_input.action';
			      	document.getElementById('paraFrm').submit();
			    }
			    if(listType=="c"){
			      	document.getElementById('paraFrm').action='AdvClmDisbursement_getClosedList.action';
			      	document.getElementById('paraFrm').submit();
			     }
			    
		    }
		   </script>
							<td><a href="#" onclick="setAction('p')">Pending
							Disbursement List</a> | <a href="#" onclick="setAction('c')">Closed
							Disbursement List</a></td>
						</tr>



					</table>

					</td>
				</tr>
				<%
					//int totalPage = (Integer) request.getAttribute("totalPage");
					//int PageNo = (Integer) request.getAttribute("pageNo");
					//int row1 = (Integer) request.getAttribute("row");
				%>

				<s:if test="%{listType == 'pending'}">
				
				<!-- ADVANCE DEFAULTERS LIST BEGINS -->
				
				<tr>
						<td width="100%" colspan="7">
						<table width="100%" class="formbg" col>
							<tr>
								<td><b>Advance Defaulters List</b></td>
							</tr>
							<tr>
								<td>
								<table width="100%" class="sorttable" id="advance">
									<tr>
										<td class="formth" width="5%" colspan="1"><b><label
											class="set" name="sno" id="sno3"
											ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="trvlid" id="trvlid3"
											ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="trvlid" id="trvlid3"
											ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label></b>
										</td>
										<td class="formth" width="30%" colspan="1"><b><label
											class="set" name="employee" id="employee3"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="appl.date" id="appl.date3"
											ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label></b>
										</td>
										<!-- 
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt1"
											ondblclick="callShowDiv(this);"><%=label.get("adv.amt")%></label></b>
										</td>
										 -->
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.applApprvdAdvAmt")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="settl.date" id="settl.date"
											ondblclick="callShowDiv(this);"><%=label.get("settl.date")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b>Deduct Advance</b>
										</td>

									</tr>
									<%
									int defCount = 0;
									%>
									<%
									int def = 1;
									%>
									<s:iterator value="defaulterList">
										<tr id="pending<%=def%>" class="sortableTD"
											title="Double click for edit"
											<%if(defCount%2==0){
														%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}defCount++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=defCount%2 %>);"
											ondblclick="javascript:deductAdvance('<s:property value="defEmpId"/>','<s:property value="defApplId"/>','<s:property
												value="defAdvAmt" />','<s:property value="defStatus"/>');">
											<td class="sortableTD" align="center"><%=def%></td>
											<td class="sortableTD" width="5%" align="left"><s:property value="defDivision" /></td>
											<td class="sortableTD" width="5%" align="left"><s:hidden
												name="defTrvlId" /><s:property value="defTrvlId" /></td>
											<td class="sortableTD"><s:hidden name="defEmpId" /><s:property value="defEmpName" /></td>
											<td class="sortableTD" nowrap="nowrap"><s:hidden
												name="defApplDate" /><s:property
												value="defApplDate" /><s:hidden name="defEmpGrade" /></td>
											<!-- 
											<td class="sortableTD" align="right"><s:property
												value="defAdvAmt" /> </td> -->
											<td class="sortableTD" align="right" nowrap="nowrap">
												<s:hidden name="defApplId" />
												<s:property value="applApprvdAdvAmt" />
												<s:property value="applApprvdAdvAmtCurrency" />  
											</td>
											<td class="sortableTD" nowrap="nowrap">&nbsp;<s:property
												value="settlDate" /></td>
											
											<td class="sortableTD" align="right"><input
												type="button" class="token" value="Deduct Advance"
												onclick="deductAdvance('<s:property value="defEmpId"/>','<s:property value="defApplId"/>','<s:property
												value="defAdvAmt" />','<s:property value="defStatus"/>');"></td>


										</tr>
										<%
										def++;
										%>
									</s:iterator>
									<%
									defCount = def;
									%>

									<%
									if (def == 1) {
									%>
									<tr align="center">
										<td colspan="6" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									} else {
									%>

									<%
									}
									%>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				
				
				
				<!-- ADVANCE DEFAULTERS LIST ENDS -->
				
				

					<!--------- DRAFT SECTION BEGINS - displaying the saved leave applications ------->

					<tr>
						<td width="100%" colspan="7">
						<table width="100%" class="formbg" col>
							<tr>
								<td colspan="6" width="90%"><b>Pending Advance Disbursement List</b></td>
								<td width="20%"><input type="button" name="disburseBtn"
								class="token" value=" Disburse " onclick="callDisburse();"></td>
							</tr>
							<tr>
								<td colspan="7">
								<table width="100%" class="sorttable" id="advance">
									<tr>
										<td class="formth" width="5%" colspan="1"><b><label
											class="set" name="sno" id="sno1"
											ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="disbDiv" id="disbDiv1"
											ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="trvlid" id="trvlid"
											ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label></b>
										</td>
										<td class="formth" width="20%" colspan="1"><b><label
											class="set" name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<!-- 
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.type" id="adv.type"
											ondblclick="callShowDiv(this);"><%=label.get("adv.type")%></label></b>
										</td>
										 -->
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="appl.date" id="appl.date"
											ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label></b>
										</td>
										<!-- td class="formth" width="10%" colspan="1"><b><label
											class="set" name="req.date" id="req.date"
											ondblclick="callShowDiv(this);"><%=label.get("req.date")%></label></b>
										</td
										
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.amt")%></label></b>
										</td> -->
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.applApprvdAdvAmt")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1">Status</td>
										<td class="formth" width="10%" colspan="1"><b>Disburse</b></td>
										<td class="formth" width="5%" colspan="1">
											<input type="button" value="Remove" onclick="removeAdvanceDisburseRecord('<s:property value="empId"/>','<s:property value="applId"/>','<s:property value="applCode"/>')">
										</td>
									</tr>
									<%
									int count = 0;
									%>
									<%! int r = 0;%>
									<%	int i = 1, 	y = 1;
									%>
									<s:iterator value="pendingList">
										<tr id="pending<%=i%>" class="sortableTD"
											title="Double click for edit"
											<%if(count%2==0){
														%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="empId"/>','<s:property value="applId"/>','<s:property value="applCode"/>','<s:property value="guestflag"/>','<s:property value="hidstatus" />');">
											<td class="sortableTD" align="center"><%=i%></td>
											<td class="sortableTD" width="5%" align="left"><s:property value="pendingListDivision" /></td>
											<td class="sortableTD" width="5%" align="left"><s:hidden
												name="trvlId" /><s:property value="trvlId" /></td>
											<td class="sortableTD"><s:hidden name="empId" /><s:hidden
												name="guestflag" /><s:property value="empName" /></td>
											<!-- 
											<td class="sortableTD" align="center"><s:property
												value="advanceType" /></td>
												 -->
											<td class="sortableTD" nowrap="nowrap"><s:hidden
												name="applicationDate" /><s:property
												value="applicationDate" /></td>
											<!--  td class="sortableTD" nowrap="nowrap">&nbsp;<s:property
												value="advReqDate" /></td
											<td class="sortableTD" align="right"><s:property
												value="advAmount" /> </td>-->
											<td class="sortableTD" align="right" nowrap="nowrap">
												<s:hidden name="applId" /> 
												<s:hidden name="applCode" />
												<s:property value="applApprvdAdvAmt" />
												<s:property value="applApprvdAdvAmtCurrency" />   
											</td>
											<td class="sortableTD">
											<input type="hidden" name="hidstatus" id="hidstatus<%=i %>"
												value="<s:property value="hidstatus" />">
											<s:if test='%{hidstatus =="T"}'>	
											Added to statement
											</s:if><s:else>
											<s:property value="applStatus" />
											</s:else>
											
											</td>
											
											<td class="sortableTD" align="center">
											
											<s:if test='%{hidstatus =="T"}'>
										
												<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=y%>" />
												<input type="checkbox" class="sortableTD" id="checkedBox<%=y%>" name="checkedBox"
											   onclick="callForDisburse('<s:property value="applCode"/>','<%=y%>')" />
											  
											</s:if><s:else>
											<input	type="button" class="token" value="Add to Statement"
												onclick="callForEdit('<s:property value="empId"/>','<s:property value="applId"/>','<s:property value="applCode"/>'
													,'<s:property value="guestflag"/>','<s:property value="applStatus" />');">
											</s:else>
											</td>
											
											<td align="center">
												<input type="checkbox" name="removeSelectedRecordCheckBox" id="removeSelectedRecordCheckBox<%=y%>" onclick="getSelectedRecord('<s:property value="empId"/>','<s:property value="applId"/>','<s:property value="applCode"/>','<%=y%>');">
												<input type="hidden" name="reomveEmpID" id="reomveEmpID<%=y%>"/>
												<input type="hidden" name="removeApplicationID" id="removeApplicationID<%=y%>"/>
												<input type="hidden" name="removeApplicationCode" id="removeApplicationCode<%=y%>"/>
											</td>
										</tr>
										<script>
								
								//var data=document.getElementById('hidstatus').value;
								var tbl = document.getElementById('advance');
		                        var rowLen = tbl.rows.length;
		                      // alert('gg'+rowLen);
		                        for(var w=1;w<rowLen;w++)
								{
								//alert('in for');
								data=document.getElementById("hidstatus"+w).value;
								if(data=="P")
								{
								//alert('in if');
								document.getElementById('pending'+w).style.background='#FFFF00';
								}
								
							}
								</script>
										<%
										i++;
										y++;
										%>
									</s:iterator>
									<%
									count = i;
									r = y;
									%>
								
									<%
									if (i == 1) {
									%>
									<tr align="center">
										<td colspan="6" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									} else {
									%>

									<!--<tr >

										<td colspan="6">
										<table width="100%">
											<tr>
												<td width="20%" colspan="1"><b>Note : -</b></td>
												<td width="80%" colspan="4"><input type="text" disabled="disabled"
													style="background-color: #FFFF00; height: 15px; width: 15px">&nbsp;&nbsp;&nbsp;
												Indicates Partially Paid .</td>
											</tr>


										</table>
										</td>

									</tr>

									-->
									<%
									}
									%>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>


					
				</s:if>




				<!--for closed advance disbursedlist  -->
				<s:if test="%{listType == 'closed'}">
				
				<!-- ADVANCE DEFAULTERS LIST BEGINS -->
				
				<tr>
						<td width="100%" colspan="7">
						<table width="100%" class="formbg" col>
							<tr>
								<td><b>Closed Advance Defaulters List</b></td>
							</tr>
							<tr>
								<td>
								<table width="100%" class="sorttable" id="advance">
									<tr>
										<td class="formth" width="5%" colspan="1"><b><label
											class="set" name="sno" id="sno4"
											ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="disbDiv" id="disbDiv3"
											ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="trvlid" id="trvlid4"
											ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label></b>
										</td>
										<td class="formth" width="30%" colspan="1"><b><label
											class="set" name="employee" id="employee4"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="appl.date" id="appl.date4"
											ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label></b>
										</td>
										<!--  
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt2"
											ondblclick="callShowDiv(this);"><%=label.get("adv.amt")%></label></b>
										</td>
										-->
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.applApprvdAdvAmt")%></label></b>
										</td>
										<td class="formth" width="15%" colspan="1"><b><label
											class="set" name="status" id="status3"
											ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b>View Details</b>
										</td>

									</tr>
									<%
									int defCount1 = 0;
									%>
									<%
									int def1 = 1;
									%>
									<s:iterator value="closedDefaulterList">
										<tr id="pending<%=def1%>" class="sortableTD"
											title="Double click for edit"
											<%if(defCount1%2==0){
														%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}defCount1++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=defCount1%2 %>);"
											ondblclick="javascript:deductAdvance('<s:property value="defEmpId"/>','<s:property value="defApplId"/>','<s:property
												value="defAdvAmt" />','<s:property value="defStatus"/>');">
											<td class="sortableTD" align="center"><%=def1%></td>
											<td class="sortableTD" width="10%" align="left"><s:property value="closedDefDivision" /></td>
											<td class="sortableTD" width="5%" align="left"><s:hidden
												name="defTrvlId" /><s:property value="defTrvlId" /></td>
											<td class="sortableTD"><s:hidden name="defEmpId" /><s:property value="defEmpName" /></td>
											<td class="sortableTD" nowrap="nowrap"><s:hidden
												name="defApplDate" /><s:property
												value="defApplDate" /><s:hidden name="defEmpGrade" /></td>
											
											<!-- 
											<td class="sortableTD" align="right"><s:property
												value="defAdvAmt" /> </td> -->
											<td class="sortableTD" align="right"><s:property
												value="applApprvdAdvAmt" /> <s:hidden name="defApplId" /></td>	
												
											<td class="sortableTD" nowrap="nowrap">Advance deducted from Salary</td>
											
											<td class="sortableTD" align="right"><input
												type="button" class="token" value="View Advance"
												onclick="deductAdvance('<s:property value="defEmpId"/>','<s:property value="defApplId"/>','<s:property
												value="defAdvAmt" />','<s:property value="defStatus"/>');"></td>


										</tr>
										<%
										def1++;
										%>
									</s:iterator>
									<%
									defCount1 = def1;
									%>

									<%
									if (def1 == 1) {
									%>
									<tr align="center">
										<td colspan="6" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									} else {
									%>

									<%
									}
									%>

								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				
				
				
				<!-- ADVANCE DEFAULTERS LIST ENDS -->

					<!--------- CLOSED SECTION BEGINS - displaying the CLOSED applications ------->

					<tr>
						<td width="100%" colspan="7">
						<table width="100%" class="formbg" border="0">
							<tr>
								<td width="70%"><b>Closed Advance Disbursement List</b></td>
								<%
									int totalPages = 0;
									int advPageNo = 0;
								%>
		<s:if test="advRecordsAvailable">
				<td id="ctrlShow" width="30%" align="right" colspan="6">
					<b>Page:</b>
					<%
						totalPages = (Integer) request.getAttribute("totalPages");
						advPageNo = (Integer) request.getAttribute("advPageNo");
					%>
					<a href="#" onclick="callPage('1', 'F', '<%=totalPages%>', 'AdvClmDisbursement_getClosedList.action');">
						<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
					</a>&nbsp;
					<a href="#" onclick="callPage('P', 'P', '<%=totalPages%>', 'AdvClmDisbursement_getClosedList.action');" >
						<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
					</a> 
					<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=advPageNo%>" maxlength="10"
					onkeypress="callPageText(event, '<%=totalPages%>', 'AdvClmDisbursement_getClosedList.action');return numbersOnly();" /> of <%=totalPages%>
					<a href="#" onclick="callPage('N', 'N', '<%=totalPages%>', 'AdvClmDisbursement_getClosedList.action')" >
						<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
					</a>&nbsp;
					<a href="#" onclick="callPage('<%=totalPages%>', 'L', '<%=totalPages%>', 'AdvClmDisbursement_getClosedList.action');" >
						<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
					</a>
				</td>
		</s:if>				
							</tr>
							
							
			
							<tr>
								<td width="100%" colspan="7">
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" colspan="1"><b><label
											class="set" name="sno" id="sno1"
											ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="disbDiv" id="disbDiv4"
											ondblclick="callShowDiv(this);"><%=label.get("disbDiv")%></label></b>
										</td>
										<td class="formth" width="20%" colspan="1"><b><label
											class="set" name="trvlid" id="trvlid"
											ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label></b>
										</td>
										<td class="formth" width="25%" colspan="1"><b><label
											class="set" name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<!-- 
										<td class="formth" width="20%" colspan="1"><b><label
											class="set" name="adv.type" id="adv.type"
											ondblclick="callShowDiv(this);"><%=label.get("adv.type")%></label></b>
										</td>
										 -->
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="appl.date" id="appl.date"
											ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label></b>
										</td>
										<!-- td class="formth" width="10%" colspan="1"><b><label
											class="set" name="req.date" id="req.date"
											ondblclick="callShowDiv(this);"><%=label.get("req.date")%></label></b>
										</td
										<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.amt")%></label></b>
										</td> -->
											<td class="formth" width="10%" colspan="1"><b><label
											class="set" name="adv.amt" id="adv.amt"
											ondblclick="callShowDiv(this);"><%=label.get("adv.applApprvdAdvAmt")%></label></b>
										</td>
										<td class="formth" width="10%" colspan="1"><b>View</b></td>

									</tr>
									<%
									int closecount = 0;
									%>
									<%
									int n = 1;
									%>
									<s:iterator value="closedList">
										<tr id="pending" class="sortableTD"
											title="Double click for edit"
											<%if(closecount%2==0){
														%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}closecount++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=closecount%2 %>);"
											ondblclick="javascript:callForclosedEdit('<s:property value="closedempId"/>','<s:property value="closedapplId"/>','<s:property value="closedapplCode"/>');">
											<td class="sortableTD" align="center"><%=n%></td>
											<td class="sortableTD" align="left"><s:property value="closedAdvDisbDivision" /></td>
											<td class="sortableTD" align="left"><s:hidden
												name="closedtrvlId" /><s:property value="closedtrvlId" /></td>
											<td class="sortableTD"><s:hidden name="closedempId" /><s:property
												value="closedempName" /></td>
											<!-- 
											<td class="sortableTD" align="center"><s:property
												value="closedadvanceType" /></td>
												 -->
											<td class="sortableTD" nowrap="nowrap"><s:hidden
												name="closedapplicationDate" /><s:property
												value="closedapplicationDate" /></td>
											<!-- td class="sortableTD" nowrap="nowrap">&nbsp;<s:property
												value="closedadvReqDate" /></td
											<td class="sortableTD" align="right"><s:property
												value="closedadvAmount" /> </td> -->
											<td class="sortableTD" align="right"><s:hidden name="closedhidstatus" /><s:hidden name="closedapplId" />
											<s:hidden name="closedapplCode" /><s:property
												value="applApprvdAdvAmt" /> </td>
											<td class="sortableTD" align="right"><input
												type="button" class="token" value="View"
												onclick="callForclosedEdit('<s:property value="closedempId"/>','<s:property value="closedapplId"/>','<s:property value="closedapplCode"/>');">
											</td>

										</tr>

										<%
										n++;
										%>
									</s:iterator>

									<%
									if (n == 1) {
									%>
									<tr align="center">
										<td colspan="6" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									}
									%>


								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

				 

				</s:if>
				<!-- end  of    for closed advance disbursedlist  -->

			</table>
			</div>
			</td>
		</tr>








		<!-- DIV 1 Advance disbursement End -->


		<!-- DIV 2 Claim Disbursement Begin -->
		<!--<tr>
			<td width="100%">
			<div id="second">
			<table width="100%" id="table20" class="formbg" border="0">
				<tr>
					<td>
					<table width="502" border="0">


					</table>
					</td>
				</tr>

			</table>
			</div>
			</td>
		</tr>

		-->
		<!-- DIV 2Claim Disbursement End -->


	</table>
</s:form>

<script>
	function clearFilter(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AdvClmDisbursement_clearFilter.action';
		document.getElementById('paraFrm').submit();
	}
	
	function statementReport(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'AdvClmDisbursement_generateBankStatementReport.action?isDisburseOrClaim='+false;
		document.getElementById('paraFrm').submit();
	}
	
	function searchByFilter(){
		try{
			filterStatus = document.getElementById('paraFrm_status').value;
			 
			if(filterStatus=="P"){
			   	document.getElementById('paraFrm').action='AdvClmDisbursement_input.action';
			   	document.getElementById('paraFrm').submit();
			}
			if(filterStatus=="C") {
			   	document.getElementById('paraFrm').action='AdvClmDisbursement_getClosedList.action';
			   	document.getElementById('paraFrm').submit();
			}
		}
		catch(e){alert(e);}
	}
	var filterCount=0;
	function showHideFilter(){
		++filterCount;
		if(filterCount%2==0){
			document.getElementById('hideShowBtn').value="  Show  ";
			document.getElementById('filterTable').style.display='none';
		}else{
			document.getElementById('hideShowBtn').value="   Hide    ";
			document.getElementById('filterTable').style.display='';
		}
	}
 	
 	
 	
 	function callForDisburse(id,i) {
		if(document.getElementById('checkedBox'+i).checked == true)	{
			document.getElementById('hdeleteCode'+i).value=id;
		
		} else {
			document.getElementById('hdeleteCode'+i).value="";
		}
	}
	function callDisburse(){
	 		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action='AdvClmDisbursement_sendAdvanceDisbursementMailSMS.action';
		   	document.getElementById('paraFrm').submit();
 	}
 	
	
 	
	function callForClaimDisburse(id,i){
 		if(document.getElementById('clmChkBox'+i).checked == true){
			document.getElementById('hidClaimCode'+i).value=id;
		} else {
			document.getElementById('hidClaimCode'+i).value="";
		}
 	}
 	
 	function callClaimDisburse(){
	 		document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action='AdvClmDisbursement_sendClaimDisbursementMailSMS.action';
		   	document.getElementById('paraFrm').submit();
 	}
 	
 	
///onloadCall();
	function onloadCall(){
		document.getElementById('first').style.display='block';
		document.getElementById('second').style.display='none';
		document.getElementById('menuid1').className='on';
		document.getElementById('menuid2').className='li';
	}
	
	function showCurrent(menuId, id){
	try{
		document.getElementById('first').style.display='none';
		document.getElementById('second').style.display='none';
		document.getElementById('menuid1').className='li';
		document.getElementById('menuid2').className='li';
		document.getElementById(menuId).className='on';
		document.getElementById(id).style.display='block';
	}catch(e){
		alert(e);
	}
	}
	
	
	function callForEdit(id,applid,applcode,gustflag,status){	
		document.getElementById('paraFrm_hiddencode').value=id;		
		//document.getElementById('paraFrm_hiddenappldate').value=date;
		//alert('nd'+document.getElementById('paraFrm_hiddenappldate').value);
		document.getElementById('paraFrm_hiddenapplId').value=applid;
		document.getElementById('paraFrm_hiddenapplCode').value=applcode;
		document.getElementById('paraFrm_hiddengustflag').value=gustflag
	   	document.getElementById("paraFrm").action="AdvClmDisbursement_calforedit.action?hiddencode="
						+id+"&hiddenapplId="+applid+"&hiddenapplCode="+applcode+"&hiddengustflag="+gustflag+"&hidApplStatus="+status;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
  function callForclosedEdit(id,applid,applcode)
  {//alert('call  this');
  
      document.getElementById('paraFrm_closedhiddencode').value=id;
      //alert('id'+applid);
		//alert('applcode'+applcode);
		//document.getElementById('paraFrm_hiddenappldate').value=date;
		//alert('nd'+document.getElementById('paraFrm_hiddenappldate').value);
		document.getElementById('paraFrm_closedhiddenapplId').value=applid;
		document.getElementById('paraFrm_closedhiddenapplCode').value=applcode;
	   	document.getElementById("paraFrm").action="AdvClmDisbursement_callForclosedEdit.action";
	   //document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
  
  }
  
  function newRowColor(cell) {   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
		}
		
		//========================CLAIM SCRIPTS ADDED BY REEBA=====================
function acceptClaimDisb(clmAppId,appId,appCode){
if(confirm('Do you want to accept the claim application?')){
	document.getElementById('paraFrm').action = 'AdvClmDisbursement_acceptClaimDisbursement.action?cailmAppId='+clmAppId
		+'&&travelApplId='+appId+'&travelApplCode='+appCode;	  
	document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target = "main";
} 
else return false;
}		
		
function viewDetails(clmAppId,clmStatus,appId,appCode,currentStatus, claimAppStatus){ 
	//alert("currentStatus -- "+currentStatus);
	document.getElementById('paraFrm_tmsClmAppId').value = clmAppId; 
	document.getElementById('paraFrm_tmsClmStatus').value = clmStatus; 
	document.getElementById('paraFrm_trvlAppId').value = appId; 
	document.getElementById('paraFrm_trvlAppCode').value = appCode; 
	document.getElementById('paraFrm').action = 'TravelExpDisbrsmnt_callView.action?disStatus='+currentStatus+'&claimAppStatus='+claimAppStatus;	  
	document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target = "main";
}//end of viewDetails

function deductAdvance(empCode, applId, advanceAmt, status){
	try{
	document.getElementById("paraFrm").action="AdvClmDisbursement_deductAdvance.action?empCode="
					+empCode+"&applId="+applId+"&advanceAmt="+advanceAmt+"&status="+status;
   	document.getElementById('paraFrm').target = '_self';
    document.getElementById("paraFrm").submit();
    }catch(e){alert(e);}
}

function removeAdvanceDisburseRecord(empID,applicationID,applicationCode){
	if(checkForSelectedRecords()) {
		var con = confirm("Do you really want to remove selected record(s)?") 
		if(con) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='AdvClmDisbursement_removeSelectedAdvanceDisbursementRecords.action';
			document.getElementById('paraFrm').submit();
		}else{
			return false;
		}
	} else  {
	 	alert('please select atleast one record');
	 	return false ;
	 }
}


function checkForSelectedRecords() {   
	var recordCount='<%=r-1%>';
	for(var i=1; i<=recordCount; i++) {	
	   	if(document.getElementById('removeSelectedRecordCheckBox'+i).checked == true) {	
	    	return true;
	   	} 	   
	}
	return false;
}


function getSelectedRecord(empID,applicationID,applicationCode,count) {
	try {
		if (document.getElementById('removeSelectedRecordCheckBox'+count).checked == true)  {
			document.getElementById('reomveEmpID'+count).value = empID; 
			document.getElementById('removeApplicationID'+count).value = applicationID;
			document.getElementById('removeApplicationCode'+count).value = applicationCode; 
		} else {
			document.getElementById('reomveEmpID'+count).value = ""; 
			document.getElementById('removeApplicationID'+count).value = ""; 
			document.getElementById('removeApplicationCode'+count).value = ""; 
		} 
	}catch(e) {
		alert("getSelectedRecord Error >>"+e);
		return false;
	}
}
</script>

<script>
	 
	function callClaimPage(id)
	{
	 var pageNo=document.getElementById('paraFrm_myClaimPage').value	
	if(id=="P")
	document.getElementById('paraFrm_myClaimPage').value=eval(pageNo)-1;	
	else if(id=="N")	
	document.getElementById('paraFrm_myClaimPage').value=eval(pageNo)+1;	
	else
	document.getElementById('paraFrm_myClaimPage').value=id;		
	document.getElementById('paraFrm').action = 'AdvClmDisbursement_getClosedList.action';
		document.getElementById('paraFrm').submit();
	}//end of callPage
	
	   function selectPage()
   {     	
   		    var pageNo;
   		 	pageNo = document.getElementById('selectname').value;
   		 	//alert('pageNo'+pageNo);
   		 	document.getElementById('paraFrm_myClaimPage').value=pageNo;
   		 	
   		 	document.getElementById('paraFrm').action = 'AdvClmDisbursement_getClosedList.action';
   		 	document.getElementById('paraFrm').submit();
   		}//end of selectPage
   		

function callPage(id, pageImg, totalPageHid, action) {	
		alert(id);
		alert(pageImg);
		alert(totalPageHid);
		alert(action);
		try{	
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
		alert(pageNo);
		alert(actPage);
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		}catch(e){
			alert(e);
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageText(id, totalPage, action){   
			try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   }catch(e){
			alert(e);
		}
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	
 </script>