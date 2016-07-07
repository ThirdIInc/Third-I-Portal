<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form action="InductionSchedule" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="inductionName" />
	<s:hidden name="inductionFrom" />
	<s:hidden name="inductionTo" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Schedule
					Induction</strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<!-- tr option -->
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- table option -->
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!-- table 1 -->
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<!--Table 4-->
								<tr>
									<td height="27" class="formtxt"><a href="#"
										onclick="callFun('N');">Induction Due List </a> | <a href="#"
										onclick="callScheduledInd('Y');">Schedule Induction </a></td>
								</tr>
								<s:hidden name="listLength"></s:hidden>
							</table>
							<!--Table 4--></td>
						</tr>
					</table>
					<!-- table 1 --></td>
				</tr>

				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!--Table 3-->
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<!--Table 5-->
								<tr>
									<td height="27" class="formtxt"><strong> <%
												 	
												 		out.println("Scheduled Induction");
												 
												 %> </strong></td>
									<%
														int totalPage = (Integer) request.getAttribute("totalPage");
														int pageNo = (Integer) request.getAttribute("PageNo");
													%>
									<td id="showCtrl" width="80%" align="right"><s:if
										test="modeLength">
										<b>Page:</b>
										<a href="#"
											onclick="callPage('1', 'F', '<%=totalPage%>', 'InductionSchedule_getScheduledInduction.action');">
										<img title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp;
													<a href="#"
											onclick="callPage('P', 'P', '<%=totalPage%>', 'InductionSchedule_getScheduledInduction.action');">
										<img title="Previous Page"
											src="../pages/common/img/previous.gif" width="10" height="10"
											class="iconImage" /> </a>
										<input type="text" name="pageNoField" id="pageNoField"
											size="3" value="<%=pageNo%>" maxlength="10"
											onkeypress="callPageText(event, '<%=totalPage%>', 'InductionSchedule_input.action');return numbersOnly();" /> of <%=totalPage%>
										<a href="#"
											onclick="callPage('N', 'N', '<%=totalPage%>', 'InductionSchedule_getScheduledInduction.action')">
										<img title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp;
													<a href="#"
											onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'InductionSchedule_getScheduledInduction.action');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a>
									</s:if></td>


								</tr>
								<tr>
									<td colspan="3" width="100%">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable" id="tblStatus">
										<!--Table 6-->
										<tr>
											<td width="10%" valign="top" class="formth"><b> <label
												class="set" name="serial.no" id="serial.no"
												ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
											</b></td>
											<td width="60%" valign="top" class="formth"><b><label
												class="set" name="indname" id="indname"
												ondblclick="callShowDiv(this);"><%=label.get("indname")%></label>
											</b></td>
											<td nowrap="nowrap" valign="top" class="formth"><b>
											<label class="set" name="fDate" id="fDate"
												ondblclick="callShowDiv(this);"><%=label.get("fDate")%></label>
											</b></td>
											<td nowrap="nowrap" valign="top" class="formth"><b>
											<label class="set" name="tDate" id="tDate"
												ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label>
											</b></td>
										</tr>
										<%
															int count = 0;
														%>
										<%
															int cnt = pageNo * 20 - 20;
															int m = 0;
														%>
										<%! int c=0; %>

										<%int j=1; %>
										<s:iterator status="stat" value="inductionList">
											<s:hidden name="inductionCode" />
											<tr <%if(count%2==0){
																%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click to view details"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="inductionCode"/>');">
												<td width="10%" class="sortableTD" align="center"><%=++cnt%>
												<%++m;%>
												</td>
												<td width="60%" class="sortableTD"><s:property
													value="inducListName" /> <s:hidden name="inducListName"
													id="<%="inducListName"+j%>" /></td>
												<td nowrap="nowrap" class="sortableTD"><s:property
													value="inducFrmDt" /> <s:hidden name="inducFrmDt"
													id="<%="inducFrmDt"+j%>" /></td>
												<td nowrap="nowrap" class="sortableTD"><s:property
													value="inducToDt" /> <s:hidden name="inducToDt"
													id="<%="inducToDt"+j%>" /></td>
											</tr>
											<%j++;%>
										</s:iterator>
										<%c=j;
														  	m=c;
														  %>
									</table>
									<!--Table 6--></td>
								</tr>
							</table>
							<!--Table 5--></td>
						</tr>
					</table>
					<!--Table 3--></td>
				</tr>
			</table>
			<!-- table option --></td>
			<s:hidden name="totalRecords" />
			<s:hidden name="modeLength" />
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%" align="right"><s:if test="modeLength">
						<b> Total No. of Records:&nbsp;</b>
						<s:property value="totalRecords" />
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
</s:form>


<script>
	function callFun(status)
	{//status is kept 'N'....it is for INT_CONDUCT_STATUS = 'N'
		try
		{
			document.getElementById('paraFrm').target="main";
			document.getElementById('myPage').value="";
	    	document.getElementById("paraFrm").action='InductionSchedule_getInducDueList.action?status='+status;
	    	document.getElementById("paraFrm").submit();
	    }
	    catch(e)
	    {
	    	alert("Error : "+e);
	    }	
	}
	
	function addnewFun(){
		document.getElementById("paraFrm").action="InductionSchedule_addNew.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function callChkAll()  {
	 
		var tbl = document.getElementById('tblStatus');
		var rowLen = tbl.rows.length;
		
		if(document.getElementById('chkAll').checked){
		
		 for(i = 1; i < rowLen; i++){
		 
		 	  document.getElementById('resumeChk'+i).checked = true;
			  document.getElementById(i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			   document.getElementById('resumeChk'+i).checked =false;
			   document.getElementById(i).value="";
		 }
	  }	
	
  }
  
  function searchFun(){
		callsF9(500,325,'InductionSchedule_f9Search.action');
	}
	
	function callScheduledInd(status)
	{
		try
		{
			document.getElementById('paraFrm').target="main";
			document.getElementById('myPage').value="";
	    	document.getElementById("paraFrm").action='InductionSchedule_getScheduledInduction.action?status='+status;
	    	document.getElementById("paraFrm").submit();
	    }
	    catch(e)
	    {
	    	alert("Error : "+e);
	    }	
	}


function callForEdit(inductionID) {
	try
		{
			document.getElementById("paraFrm").action="InductionSchedule_viewInductionDetails.action?inductionID="+inductionID;
			document.getElementById('paraFrm').target = "_self";
			document.getElementById("paraFrm").submit();
	 	} catch(e)  {
	    	alert("Error : "+e);
	    }
}

</script>


