<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpGoalSetting" method="post" name="form"
	id="paraFrm" theme="simple">

	

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Goal Setting </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- FORM NAME END -->
		<!-- BUTTON PANEL START -->
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- BUTTON PANEL END -->
		<!--------------------------------------  Draft LIST OF APPLICATIONS [BEGINS]----------------------------->
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='EmpGoalSetting_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='EmpGoalSetting_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					List</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> 
				</tr>
			</table>
			</td>
		</tr>
		<!--------------------------------------  PENDING LIST [BEGINS]----------------------------->

	<s:hidden name='empName'></s:hidden>
	<s:hidden name='goalPeriod'></s:hidden>
	<s:hidden name='goalFromDate'></s:hidden>
	<s:hidden name='goalToDate'></s:hidden>
	<s:hidden name="empGoalId" />
	<s:hidden name="approvalStatus" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="approveLength" />
	<s:hidden name="isGoalCategory" />
	<s:hidden name="reportingType" />
		<s:if test="%{listType == 'pending'}">

			<!--------- DRAFT SECTION BEGINS - displaying the saved applications ------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Draft List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="goalPeriod" id="goalPeriod1" ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="goalFromDate" id="goalFromDate1"
									ondblclick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="goalToDate" id="goalToDate1"
									ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label></b>
								</td>

							</tr>

							<%
							int i = 1;
							%>
							<s:iterator value="draftList">
								<tr title="Doubl click to edit goals" ondblclick="viewDetails('<s:property value="draftGoalNo"/>','1')">
									<td class="sortableTD" width="5%" align="center"><%=i%></td>
									<td class="sortableTD" width="40%"><s:hidden
										name="empCodeList" /><s:hidden name="draftGoalNo" /> <s:property value="goalPeriodList" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="goalFromDateList" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="goalToDateList" /></td>
								</tr>
								<%
								i++;
								%>
							</s:iterator>

							<%
							if (i == 1) {
							%>
							<tr align="center">
								<td colspan="4" class="sortableTD" width="100%"><font
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

			<!--------- DRAFT SECTION ENDS - displaying the saved applications -------->
			<!--------- SUBMIT SECTION BEGINS - displaying the sent applications -------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Applications In Process</b></td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%" class="sorttable">
							<tr>
								<td>
								<table width="100%" class="sorttable">
							
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="goalPeriod" id="goalPeriod2" ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="goalFromDate" id="goalFromDate2"
									ondblclick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="goalToDate" id="goalToDate2"
									ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label></b>
								</td>

							</tr>

									<%
									int pen = 1;
									%>
									<s:iterator value="submitList">
									
									<tr title="Doubl click to edit goals" ondblclick="viewDetails('<s:property value="submitGoalNo"/>','2')">
										<td class="sortableTD" width="5%" align="center"><%=pen%></td>
										<td class="sortableTD" width="40%"><s:hidden
											name="empCodeList" /><s:hidden name="submitGoalNo" /> <s:property value="goalPeriodList" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="goalFromDateList" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="goalToDateList" /></td>
									</tr>
										<%
										pen++;
										%>
									</s:iterator>

									<%
									if (pen == 1) {
									%>
									<tr align="center">
										<td colspan="4" class="sortableTD" width="100%"><font
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
				</table>
				</td>
			</tr>

			<!--------- SUBMIT SECTION ENDS - displaying the sent applications -------->

			<!--------- RETURN SECTION BEGINS - displaying the returned applications --->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Returned Applications (Please view the comments,
						update application and submit again) </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="goalPeriod" id="goalPeriod3" ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="goalFromDate" id="goalFromDate3"
									ondblclick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="goalToDate" id="goalToDate3"
									ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label></b>
								</td>
								

							</tr>

									<%
									int ret = 1;
									%>
									<s:iterator value="returnList">
									
									<tr title="Doubl click to edit goals" ondblclick="viewDetails('<s:property value="returnedGoalNo"/>','4')">
										<td class="sortableTD" width="5%" align="center"><%=ret%></td>
										<td class="sortableTD" width="40%"><s:hidden
											name="empCodeList" /><s:hidden name="returnedGoalNo" /> <s:property value="goalPeriodList" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="goalFromDateList" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="goalToDateList" /></td>
										
									</tr>
										<%
										ret++;
										%>
									</s:iterator>

									<%
									if (ret == 1) {
									%>
									<tr align="center">
										<td colspan="4" class="sortableTD" width="100%"><font
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

				</table>
				</td>
			</tr>
			
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Applications For Revision (Please view the comments,
						update application and submit again) </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="goalPeriod" id="goalPeriod4" ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="goalFromDate" id="goalFromDate4"
									ondblclick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="goalToDate" id="goalToDate4"
									ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label></b>
								</td>
								

							</tr>

									<%
									int rev = 1;
									%>
									<s:iterator value="revisedList">
									
									<tr title="Doubl click to edit goals" ondblclick="viewDetails('<s:property value="revisedGoalNo"/>','6')">
										<td class="sortableTD" width="5%" align="center"><%=rev%></td>
										<td class="sortableTD" width="40%"><s:hidden
											name="empCodeList" /><s:hidden name="revisedGoalNo" /> <s:property value="goalPeriodList" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="goalFromDateList" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="goalToDateList" /></td>
										
									</tr>
										<%
										rev++;
										%>
									</s:iterator>

									<%
									if (rev == 1) {
									%>
									<tr align="center">
										<td colspan="4" class="sortableTD" width="100%"><font
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

				</table>
				</td>
			</tr>
		</s:if>

		<!--------- RETURN SECTION ENDS - displaying the returned applications ----->

		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<%
								int totalPage = 0;
								int pageNo = 0;
							%>
							<s:if test="approveLength">
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'EmpGoalSetting_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'EmpGoalSetting_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'EmpGoalSetting_getApprovedList.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'EmpGoalSetting_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmpGoalSetting_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable">
							
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b><label class="set"
									name="goalPeriod" id="goalPeriod4" ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="goalFromDate" id="goalFromDate4"
									ondblclick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="goalToDate" id="goalToDate4"
									ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label></b>
								</td>
								

							</tr>
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="approvedList">
								<tr title="Doubl click to edit goals" ondblclick="viewDetails('<s:property value="approvedGoalNo"/>','3')">
										<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
										<td class="sortableTD" width="40%"><s:hidden
											name="empCodeList" /><s:hidden name="approvedGoalNo" /> <s:property value="goalPeriodList" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="goalFromDateList" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="goalToDateList" /></td>
										
									</tr>
										
										</s:iterator>
										
									<s:if test="approveLength"></s:if>
									<s:else>
									<tr align="center">
										<td colspan="4" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			
		</s:if>
		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [ENDS] ------------------------------->

		
		
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>

</s:form>


<script>


	function addnewFun() {
	
		document.getElementById('paraFrm').action = 'EmpGoalSetting_addNew.action';
		document.getElementById('paraFrm').submit();
	}

	

 function viewDetails(goalNo,status){
		    
		      	document.getElementById('paraFrm').action='EmpGoalSetting_retriveGoalDetails.action?empGoalId='+goalNo+'&approvalStatus='+status;
		      	document.getElementById('paraFrm').submit();
		     
		   }

	
function searchFun(){
	callsF9(500,325,'EmpGoalSetting_f9action.action'); 
}
	 

</script>
