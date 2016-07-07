<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>



<s:form name="" action="" validate="" id="paraFrm" theme="simple">
	<s:hidden name="listType" />
	<s:hidden name="appStatus" />
	<s:hidden name="appId" />
	<s:hidden name="appCode" />
	<s:hidden name="hAppFor" />
	<s:hidden name="empId" />
	<s:hidden name="initId" />

	<s:hidden name="myPageApproved" />
	<s:hidden name="myPageRejected" />
	<s:hidden name="myPageDraft" />
	<s:hidden name="myPageSubmit" />
	<s:hidden name="myPageReturn" />
	<s:hidden name="myPageCancel" />
	<s:hidden name="myPagePendingCancel" />
	<s:hidden name="myPageSchlTrvlList" />
	<s:hidden name="tmsTrvlId" />
	<s:hidden name="tmsTrvlIndiId" />
	<s:hidden name="tmsChkTypeFlg" />
	<s:hidden name="deskFlag" />
	<s:hidden name="applyFilterFlag" />
	<s:hidden name="chkSerch" />
	<s:hidden name="searchFlag" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<td><input type="hidden" name="userType" value="SCH">
					<a href="#" onclick="setAction('under_process')"> Applications
					Under Process</a> | <a href="#" onclick="setAction('processed')">
					Applications Processed</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<%
			/* Initializing common variables for TRAVEL APPLICATIONS, PROCESSED APPLICATIONS & UNDER PROCESS APPLICATIONS BLOCK */

			int srNoCount = 1;
			int viewBtn = 1;//view button
			int wDrawBtn = 1;//withdraw button
			int resendBtn = 1;//resend button
		%>
		<% int totalPage = 0;
			int pageNo = 0;	%>
		<!--------- TRAVEL APPLICATIONS SECTION DISPLAY WHEN LINK IS CLICKED ------->
		<s:if test="%{listType == 'under_process'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="20%"><b>Travel Applications </b></td>

						<td id="ctrlShow" width="80%" align="right"><b>Page:</b> <%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=under_process');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=under_process');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=under_process');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=under_process')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=under_process');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" width="2%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>

								<td class="formth" width="10%"><b><label class="set"
									name="travelIdName" id="travelIdName"
									ondblclick="callShowDiv(this);"><%=label.get("travelIdName")%></label></b>
								</td>

								<td class="formth"><b><label class="set"
									name="trainitor" id="trainitor1"
									ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="traReqname" id="traReqname1"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>
								<td class="formth"><b><label class="set" name="tradate"
									id="tradate1" ondblclick="callShowDiv(this);"><%=label.get("tradate")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="travelApplicationStatus" id="travelApplicationStatus"
									ondblclick="callShowDiv(this);"><%=label.get("appStatus")%></label></b>
								</td>
								<td class="formth">View Applications</td>
							</tr>

							<%	int underProc = pageNo * 20 - 20;	%>
							<s:iterator value="travelApplicationIteratorlist">
								<tr>

									<td class="sortableTD">
									<%++underProc;%><%=underProc%></td>
									<s:hidden name="travelApplicationIdItt"></s:hidden>
									<td class="sortableTD" align="left">&nbsp;<s:property
										value="travelNameItt" />&nbsp;</td>
									<td class="sortableTD" align="left">&nbsp;<s:property
										value="initiatorNameItt" />&nbsp;</td>
									<td class="sortableTD" align="center">&nbsp;<s:property
										value="travelRequestNameItt" />&nbsp;</td>
									<td class="sortableTD" align="center">&nbsp;<s:property
										value="travleDateItt" />&nbsp;</td>
									<td class="sortableTD" align="center">&nbsp; <s:property
										value="travleApplicationStatusNameItt" />&nbsp;</td>
									<td align="center" class="sortableTD"><input type="button"
										name="allApplicationStatusView" value="View" class="token"
										id="<%="allApplicationStatusView"+viewBtn%>"
										onclick="viewApplication('<s:property value="travelApplicationIdItt" />')" />&nbsp;</td>
								</tr>

							</s:iterator>

							<%
									if (underProc == 0) {
									%>
							<tr align="center">
								<td colspan="7" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
									} else {
										
									}
									%>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!--------- TRAVEL APPLICATIONS SECTION ENDS  ------->
		<!-------------------------------------- PROCESSED LIST OF APPLICATIONS [BEGINS]----------------------------->
		<s:if test="%{listType == 'processed'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="30%"><b>Travel Processed Applications List</b></td>

						<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=processed');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=processed');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=processed');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=processed')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TravelApplication_getAllApplications.action?status=processed');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" width="3%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>

								<td class="formth" width="10%"><b><label class="set"
									name="travelIdName" id="travelIdName"
									ondblclick="callShowDiv(this);"><%=label.get("travelIdName")%></label></b>
								</td>

								<td class="formth" width="20%"><b><label class="set"
									name="trainitor" id="trainitor1"
									ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label></b>
								</td>
								<td class="formth" width="24%"><b><label class="set"
									name="traReqname" id="traReqname1"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>
								<td class="formth" width="13%"><b><label class="set"
									name="tradate" id="tradate1" ondblclick="callShowDiv(this);"><%=label.get("tradate")%></label></b>
								</td>
								<td class="formth" width="13%"><b><label class="set"
									name="travelApplicationStatus" id="travelApplicationStatus"
									ondblclick="callShowDiv(this);"><%=label.get("appStatus")%></label></b>
								</td>
								<td class="formth" width="13%">View Applications</td>
							</tr>
							<%	int processed = pageNo * 20 - 20;	%>
							<s:iterator value="travelApplicationIteratorlist">
								<tr>

									<td class="sortableTD">
									<%++processed; %><%=processed%></td>
									<s:hidden name="travelApplicationIdItt" />

									<td class="sortableTD" align="left">&nbsp;<s:property
										value="travelNameItt" />&nbsp;</td>

									<td class="sortableTD"><s:property
										value="initiatorNameItt" /></td>
									<td class="sortableTD"><s:property
										value="travelRequestNameItt" /></td>
									<td class="sortableTD" align="center"><s:property
										value="travleDateItt" /></td>
									<td class="sortableTD" align="center"><s:hidden
										name="travleApplicationStatusItt" /> <s:property
										value="travleApplicationStatusNameItt" /></td>

									<td align="center" class="sortableTD" nowrap="nowrap"><input
										type="button" name="processedApplicationStatusView"
										value="View" class="token"
										id="<%="processedApplicationStatusView"+viewBtn%>"
										onclick="viewApplication('<s:property value="travelApplicationIdItt" />')" />


									<s:if test="%{travleApplicationStatusItt=='_C'}">
										<input type="button" name="processedApplicationStatusView"
											value="Booking Details" class="token"
											id="<%="processedApplicationStatusView"+viewBtn%>"
											onclick="viewBookApplication('<s:property value="travelApplicationIdItt" />','<s:property value="travleApplicationStatusItt" />')" />

									</s:if> &nbsp;</td>
								</tr>
								</tr>

							</s:iterator>
							<%
									if (processed == 0) {
									%>
							<tr align="center">
								<td colspan="7" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
									} else {
										
									}
									%>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-------------------------------------- PROCESSED LIST OF APPLICATIONS [ENDS] ------------------------------->
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>


	<s:hidden name="empId" />
	<s:hidden name="srNo" />

</s:form>

<script>
	
	function setAction(listType){
	     if(listType=="processed"){
		      	document.getElementById('paraFrm').action='TravelApplication_getAllApplications.action?status='+listType; 
		      	document.getElementById('paraFrm').submit();
		     }if(listType=="under_process"){
		      	document.getElementById('paraFrm').action='TravelApplication_getAllApplications.action?status='+listType;
		      	document.getElementById('paraFrm').submit();
		     }
    }
 //CALLED WHEN Add New BUTTON IS CLICKED
	function addnewFun(){	
		try{	
		document.getElementById('paraFrm').action="TravelApplication_addNew.action";
		document.getElementById('paraFrm').submit();
		}catch(e)
		{
			alert(""+e);
		}
	}
	
	function searchFun(){
	 	callsF9(500,325,'TravelApplication_f9TravelApp.action')
	}
	
	function viewApplication( applicationId ){
		
		document.getElementById('paraFrm').action="TravelApplication_viewApplications.action?applicationId="+applicationId;
		document.getElementById('paraFrm').submit();
		
	} 
	
	function viewBookApplication( applicationId ,status1 ){
		try{
			var applicationType="travelApp";
			var status =status1.replace("_","");
			document.getElementById('paraFrm').action='TravelQuickBooking_startBooking.action?applicationId='+applicationId+'&applicationStatus='+status+'&applicationType='+applicationType;
			document.getElementById('paraFrm').submit();
		}catch(e){ }
	} 
	
</script>