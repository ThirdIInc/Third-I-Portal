
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TrvlSchl" validate="true" id="paraFrm" validate="true"
	theme="simple">

	<s:hidden name="noData" />
	<s:hidden name="stat" /> 
	<s:hidden name="pendingFlag" /> 
	
 
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
						class="text_head">Travel
					Schedule </strong></td>
				</tr>
			</table>
			</td>
		</tr>


		
		
		
		
		
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<tr>
					<td height="27" class="formtxt"><a
						href="TrvlSchl_callStatus.action?status=P">Pending List</a> |
					<a href="TrvlSchl_callStatus.action?status=ST">Scheduled
					Travel List</a> | <a href="TrvlSchl_callStatus.action?status=SA">Scheduled
					Approved List</a>| <a href="TrvlSchl_callStatus.action?status=SR">
					Scheduled Rejected List </a>| <a
						href="TrvlSchl_callStatus.action?status=SC">Schedule for
					pending Cancellation</a>| <a
						href="TrvlSchl_callStatus.action?status=SCD">Schedule for
					Cancellation</a></td>
				</tr>
			</table>
			</td>

		</tr>


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>

					<s:if test="%{pen}">
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
					</s:if>

					<s:elseif test="%{schTr}">
						<td height="27" class="formtxt"><strong>Scheduled
						Travel List</strong></td>
					</s:elseif>

					<s:elseif test="%{schAppr}">
						<td height="27" class="formtxt"><strong>Scheduled
						Approved List</strong></td>
					</s:elseif>

					<s:elseif test="%{schReg}">
						<td height="27" class="formtxt"><strong>Scheduled
						Rejected List</strong></td>
					</s:elseif>


					<s:elseif test="%{schCan}">
						<td height="27" class="formtxt"><strong>Schedule for
						pending Cancellation</strong></td>
					</s:elseif>


					<s:elseif test="%{schCanceled}">
						<td height="27" class="formtxt"><strong>Schedule for
						Cancellation</strong></td>
					</s:elseif>

					<s:else>
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
					</s:else>


				</tr>

				<tr>
					<td width="100%" colspan="5"><s:hidden name="hdPage"
						id="hdPage" value="%{hdPage}" /> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 %>


					<table width="100%" class="formbg">
						<tr>
							<td width="98%" align="right">    <s:if test="noData"> </s:if><s:else> <b>Page:</b></s:else>
							<%
							if (pageNo != 1) {
							%> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (pageNo == totalPage - 1 || pageNo == totalPage) {
 			for (int z = pageNo - 2; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo > 3) {
 			for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(pageNo == totalPage)) {
 		if (totalPage == 0) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /> </a> <%
 	}
 	}
 %>
							</td>
						</tr>
					</table>
					</td>
				</tr>







				<!-- iterator -->


				<s:hidden name="trvAppId" />
				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td  class="formth" align="center" width="4%" nowrap="nowrap"><label  class = "set"  id="tms.trvlScheduleSrNo" name="tms.trvlScheduleSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
							<td  class="formth" width="20%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td  class="formth" width="20%"><label  class = "set"  id="tms.trvlRqstName" name="tms.trvlRqstName" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlRqstName")%></label></td>
							<td  class="formth" width="10%" nowrap="nowrap" ><label  class = "set"  id="tms.trvlAppDate" name="tms.trvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAppDate")%></label></td>
							<td class="formth" width="12%"><label  class = "set"  id="tms.trvlLocType" name="tms.trvlLocType" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlLocType")%></label></td>
							<td valign="top" width="10%" class="formth" ><label  class = "set"  id="tms.trvlArrngment" name="tms.trvlArrngment" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlArrngment")%></label></td>


							<td valign="top" width="10%" class="formth" ><label  class = "set"  id="tms.trvlAccomodation" name="tms.trvlAccomodation" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAccomodation")%></label></td>
							<s:if test="%{pendingFlag}">
							<td  valign="top" width="10%"  class="formth" ><label  class = "set"  id="sch.forward" name="sch.forward" ondblclick="callShowDiv(this);"><%=label.get("sch.forward")%></label> </td>
							</s:if>  
							<td valign="top" width="7%" class="formth" >&nbsp;&nbsp;&nbsp;</td>
						</tr>






						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center" ><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>




						<s:hidden name="penFlag" />
						<s:hidden name="apprvdFlag" />
						<s:hidden name="regctedFlag" />
						<s:hidden name="trvlFlag" />


						<%!int i = 0;%>
						<%
						int k = 1;
						%>

<s:hidden name="altSchEmpId" /> <s:hidden name="altSchEmpToken" />  <s:hidden name="altSchEmpName" />

						<s:iterator value="trvlSchList">
							<tr>


								<td width="5%"  class="sortableTD"><%=k%><s:hidden
									name="travelAppId" />   </td>

								<td class="sortableTD"><s:property
									value="empName" />&nbsp;</td>

								<td   class="sortableTD"><s:property
									value="trvlReqName" />&nbsp;</td>

								<td    class="sortableTD"><s:property
									value="trvlDate" />&nbsp;</td>


								<td    class="sortableTD"><s:property
									value="locType" />&nbsp;</td>

								<td  class="sortableTD"><s:property
									value="trvlArgmt" />&nbsp;</td>

								<td  class="sortableTD"><s:property
									value="trvlAccom" />&nbsp;</td>
	 							<s:if test="penFlag">
							 	<s:if test="forwrdFlag"> 
									<td   class="sortableTD"> <!-- <s:property value="trvlAdv" />&nbsp; --> 
								
										       <input type="button" value="Forward" onclick="callForwardF9('<s:property value="travelAppId"/>');"> 
							            &nbsp;
							        </td> 
						       </s:if> 
						      </s:if>
							
							
								<s:if test="penFlag">  
								<td  width="20%" align="right" valign="middle" > 
									  <input 	type="button" name="view" class="token" value="Schedule"
										onclick="callSchedule('<s:property value="travelAppId"/>')" />
										&nbsp; 
								</td>
							 	</s:if>
								
								<s:elseif test="trvlFlag">

									<td  width="20%" align="right"><input
										type="button" name="view" class="token" value="  View"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td>
								</s:elseif>

								<s:elseif test="apprvdFlag">

									<td  width="20%" align="center"><input
										type="button" name="view" class="token" value="  View"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td>
								</s:elseif>
								<s:elseif test="regctedFlag">

									<td  width="20%" align="center"><input
										type="button" name="view" class="token" value="Reschedule"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td>
								</s:elseif>


								<s:elseif test="cancelFlag">

									<td width="20%" align="center"><input
										type="button" name="view" class="token" value="Cancel"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td>
								</s:elseif>


								<s:elseif test="canceledFlag">

									<td width="20%" align="center"><input
										type="button" name="view" class="token" value="View"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td>
								</s:elseif>


								<s:else>

									<td  width="20%" align="center"><input
										type="button" name="view" class="token" value="Cancel"
										onclick="callView('<s:property value="travelAppId"/>')" />&nbsp;&nbsp;

									</td> 
								</s:else> 
							</tr>
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


				<!-- iterator -->


				<tr>

					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>


			</table>
			</td>
		</tr>
	</table>


</s:form>



<script>
	
	
	function callSchedule(Id){ 
	//alert(Id);
	document.getElementById('paraFrm_trvAppId').value = Id;
	document.getElementById('paraFrm').action = "TrvlSchl_callSchedule.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
		 
	}
	
	
	function callView(Id){ 	
	//alert(Id);
	document.getElementById('paraFrm_trvAppId').value = Id;
	//alert(document.getElementById('paraFrm_trvAppId').value);
	document.getElementById('paraFrm').action = "TrvlSchl_callSchedule.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
		 
	}
	
	
	
	
	
	
	
	
	
	function callForward(id){ 
	
		document.getElementById('paraFrm_trvlAppId').value = Id;
	    document.getElementById('paraFrm_stat').value = "P"; 
		document.getElementById('paraFrm').action = "TravelSchAppr_callForward.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = ""; 
	}
	</script>





<script>
	function callPage(id)
	{

		if(id == 'P')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		if(id == 'N')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		document.getElementById('hdPage').value = id;
		document.getElementById('paraFrm').action = "TravelSchAppr_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
	
   function callForwardF9(Id)
     {   var conf=confirm("Do you really want to forward the application ?");
          if(conf){ 
             document.getElementById('paraFrm_trvAppId').value = Id;
              callsF9(500,325,'TrvlSchl_f9AlterScheduler.action?trvAppId='+Id);   
		  } 
     }
     
</script>


