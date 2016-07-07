<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<s:form action="OfferApproval" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="apptFlag" />
		<s:hidden name="offerFlag" />
		<s:hidden name="myPage" />
		<s:hidden name="myPage1" />
		<s:hidden name="apprflag" />
		<s:hidden name="listLength" />
		<s:hidden name="status" />

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Offer/Appointment
					Approval</strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%">
				<tr>
					<td></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 2-->
				<tr>
					<td width="100%"><strong>Offer Details</strong></td>
				</tr>


			</table>
			<!--Table 2--></td>
			<!--end of vacancy search-->
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<!--Table 4-->
								<tr>
									<td height="27" class="formtxt"><a
										href="OfferApproval_ckeckdata.action?status=P">Pending
									List</a> | <a href="OfferApproval_ckeckdata.action?status=A">Approved
									List</a> | <a href="OfferApproval_ckeckdata.action?status=R">Rejected
									List</a></td>
								</tr>

							</table>
							<!--Table 4--></td>
						</tr>
					</table>
					</td>
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
									<td align="left" height="27" class="formtxt"><strong>
									<%String status = (String)request.getAttribute("stat");
                    		String statusPass="";
	                    	if(status!=null){
	                    		if (status.equals("Approved List")) {
		                    		statusPass = "A";
								}
		                    	else if (status.equals("Rejected List")) {
		                    		statusPass = "R";
								}else{
									statusPass="P";
								}
	                    		out.println(status);
	                    	}
	                    	else{
	                    		out.println("Pending List");
	                    		statusPass="P";
	                    	}
	                    	
	                    	
	                    	%> </strong></td>
									<!-- <input type="hidden" name="myPage143" value='<%--<%=statusPass%>--%>'/> -->
									<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
									<s:if test="noData">
									</s:if>
									<s:else>
										<td align="right"><b>Page:</b> <input type="hidden"
											name="totalPage" id="totalPage" value="<%=totalPage%>">
										<a href="#" onclick="callPage('1','F','<%=statusPass%>');">
										<img title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp; <a
											href="#" onclick="callPage('P','P','<%=statusPass%>');">
										<img title="Previous Page"
											src="../pages/common/img/previous.gif" width="10" height="10"
											class="iconImage" /> </a> <input type="text" name="pageNoField"
											id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
											onkeypress="callPageText(event,'<%=statusPass%>');return numbersOnly()"
											maxlength="4" title="press Enter" /> of <%=totalPage%> <a
											href="#" onclick="callPage('N','N','<%=statusPass%>')">
										<img title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp; <a href="#"
											onclick="callPage('<%=totalPage%>','L','<%=statusPass%>');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a></td>
									</s:else>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<!--Table 6-->
						<tr >
							<td width="3%" valign="top" class="formth" nowrap="nowrap"><label
								class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td width="25%" valign="top" class="formth"><label
								class="set" name="reqs.code" id="reqs.code"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></td>
							<td width="25%" valign="top" class="formth"><label
								class="set" name="cand.name" id="cand.name"
								ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
							<td width="20%" valign="top" class="formth"><label
								class="set" name="position" id="position"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td>
							<!--
	                           <s:if test="pending">
	                          		<td width="12%" valign="top" class="formth">Due Since Days</td>
	                          </s:if><s:elseif test="approved">
	                                 <td width="12%" valign="top" class="formth">Approved Date</td>                 
	                          
	                          </s:elseif><s:elseif test="rejected">
	                          
	                          		 <td width="12%" valign="top" class="formth">Rejected Date</td>     
	                          </s:elseif>
	                       
	                          -->
							<s:if test="statusFlag">
								<td width="6%" valign="top" class="formth"><label
									class="set" name="stat" id="stat"
									ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
							</s:if>
							
							<td width="11%" valign="top" class="formth"><label
								class="set" name="view.eval" id="view.eval"
								ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></td>
							<td width="10%" valign="top" class="formth"><label
								class="set" name="view.cv" id="view.cv"
								ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></td>
								<!-- added ganesh -->
							<td width="10%" valign="top" class="formth"><label
								class="set" name="view.previewOffer" id="view.previewOffer"
								ondblclick="callShowDiv(this);"><%=label.get("view.previewOffer")%></label></td>
								<!-- added ganesh end -->
						</tr>

						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>
						<%! int i = 0 ; %>
						<% int k = 1; int c=0;
		                    	int s=0;
		                    %>
						<%
											int cnt = pageNo * 20 - 20;
											int m = 0,count=0;
									%>
						<s:iterator value="list">
							<tr <%if(count%2==0){%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								title="Double click for view Requisition"
								ondblclick="javascript:viewRequisition('<s:property value="reqCode" />','offer');">
								<td width="3%" class="sortableTD">&nbsp;<%=++cnt%> <%++m;%><s:hidden
									name="statusFlag" /></td>
								<td width="25%" class="sortableTD"><s:property
									value="reqName" /> <s:hidden name="reqCode" /></td>
								<td width="25%" class="sortableTD"><s:hidden
									name="candCode" /><s:property value="candName" /></td>
								<td width="20%" class="sortableTD"><s:property
									value="position" /></td>
								<!--
									<td width="12%" class="border2" align="center"><s:property value="days"/>&nbsp;</td>
								    -->
								<s:if test="statusFlag">
									<td width="6%" align="center" class="sortableTD"><s:select
										name="offerStatus" id='<%="offerStatus"+c %>'
										cssStyle="width:70" theme="simple"
										list="#{'':'Select','A':'Approved','R':'Rejected'}" /></td>
								</s:if>
								<s:else></s:else>
								
								<td width="11%" class="sortableTD" align="center"><input
									type="button" class="token" value='View'
									onclick="return viewInterviewDetails('<s:property value="reqCode" />','<s:property value="candCode" />');" />
								</td>
								<td width="15%" class="sortableTD" align="center"><s:hidden
									name="offerCode" /> <s:hidden name="resume" /> <input
									type="button" class="token" value='View'
									onclick="return showRecord('<s:property value="resume" />');" />
								</td>
								<!-- added ganesh -->
								
								<td width="15%" class="sortableTD" align="center"><s:hidden name="templateCode" />
								 <input
									type="button" class="token" value='View'
									onclick="return validatePreviewForTemplate('<s:property value="reqCode" />','<s:property value="candCode" />','<s:property value="templateCode" />')"  />
									</td>
								
						
								<!-- added ganesh end -->
							</tr>
							<%k++; c++;%>
						</s:iterator>
						<%m=i; %>
						<% i=k ; %>
					</table>
				<input type="hidden" name="count" id="count" value="<%=c%>" />
				</td>
				</tr>
			</table>
			<!--Table 5--></td>
		</tr>
		<tr>
			<td colspan="3" align="center"><s:if test="saveFlag">
				<input type="button" class="save" value=" Save "
					onclick="saveValidate();" />
			</s:if> <input type="submit" class="reset" value="   Clear"
				onclick="return resetStatus();" /></td>
		</tr>


		<tr>

			<td width="78%"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /><br />
			</td>

		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 2-->
				<tr>
					<td width="100%"><strong>Appointment Details</strong></td>
				</tr>


			</table>
			<!--Table 2--></td>
			<!--end of vacancy search-->
		</tr>


		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><a
										href="OfferApproval_ckeckAptData.action?status=P">Pending
									List</a> | <a href="OfferApproval_ckeckAptData.action?status=A">Approved
									List</a> | <a href="OfferApproval_ckeckAptData.action?status=R">Rejected
									List</a>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="3" width="100%">
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><strong> <%String astatus = (String)request.getAttribute("appt");
                    		String astatusPass="";
	                    	
	                    	
							if(astatus!=null){
	                    		
	                    		if (astatus.equals("Approved List")) {
	                    			astatusPass = "A";
								}
		                    	else if (astatus.equals("Rejected List")) {
		                    		astatusPass = "R";
								}else{
									astatusPass="P";
								}
	                    		out.println(astatus);
	                    	}
	                    	else{
	                    		out.println("Pending List");
	                    		astatusPass="P";
	                    	}
	                    	
	                    	%> </strong></td>
									<!-- <input type="hidden" name="myPage143" value='<%--<%=astatusPass%>--%>'/>-->
									<%
							int totalPageAppt = (Integer) request.getAttribute("totalPageAppt");
							int pageNoAppt = (Integer) request.getAttribute("PageNoAppt");
							%>
									<s:if test="noApt">
									</s:if>
									<s:else>
										<td align="right"><b>Page:</b> <input type="hidden"
											name="totalPageApnt" id="totalPageApnt"
											value="<%=totalPageAppt%>"> <a href="#"
											onclick="callPageApnt('1','F','<%=astatusPass%>');"> <img
											title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp; <a
											href="#" onclick="callPageApnt('P','P','<%=astatusPass%>');">
										<img title="Previous Page"
											src="../pages/common/img/previous.gif" width="10" height="10"
											class="iconImage" /> </a> <input type="text"
											name="pageNoFieldApnt" id="pageNoFieldApnt" theme="simple"
											size="3" value="<%=pageNoAppt%>"
											onkeypress="callPageTextApnt(event,'<%=astatusPass%>');return numbersOnly()"
											maxlength="4" title="press Enter" /> of <%=totalPageAppt%> <a
											href="#" onclick="callPageApnt('N','N','<%=astatusPass%>')">
										<img title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp; <a href="#"
											onclick="callPageApnt('<%=totalPageAppt%>','L','<%=astatusPass%>');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a></td>
									</s:else>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="3%" valign="top" class="formth" nowrap="nowrap"><label
								class="set" name="serial.no" id="serial.no1"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td width="25%" valign="top" class="formth"><label
								class="set" name="reqs.code" id="reqs.code1"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></td>
							<td width="25%" valign="top" class="formth"><label
								class="set" name="cand.name" id="cand.name1"
								ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
							<td width="20%" valign="top" class="formth"><label
								class="set" name="position" id="position1"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td>
							<!--
	                          <s:if test="pendingFlag">
	                          		<td width="12%" valign="top" class="formth">Due Since Days</td>
	                          </s:if><s:elseif test="approvedFlag">
	                                 <td width="12%" valign="top" class="formth">Approved Date</td>                 
	                          
	                          </s:elseif><s:elseif test="rejectedFlag">
	                          
	                          		 <td width="12%" valign="top" class="formth">Rejected Date</td>     
	                          </s:elseif>
	                          
	                          
	                          -->
							<s:if test="aptStatusFlag">
								<td width="6%" valign="top" class="formth"><label
									class="set" name="stat" id="stat1"
									ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
							</s:if>
							
							<td width="11%" valign="top" class="formth"><label
								class="set" name="view.eval" id="view.eval1"
								ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></td>
							<td width="10%" valign="top" class="formth"><label
								class="set" name="view.cv" id="view.cv1"
								ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></td>
								
							<!-- added ganesh -->
							
							<td width="10%" valign="top" class="formth"><label
								class="set" name="view.previewAppointment" id="view.previewAppointment"
								ondblclick="callShowDiv(this);"><%=label.get("view.previewAppointment")%></label></td>
								<!-- added ganesh end -->
						</tr>

						<s:if test="noApt">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>
						<%
											int cnt1 = pageNoAppt * 20 - 20;
											int m1 = 0;
									%>

						<%! int m = 0 ; %>
						<% int l = 1; int a=0;
		                    	int r=0;
		                    %>

						<s:iterator value="aptmtList">
							<tr <%if(count%2==0){%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								title="Double click for view Requisition"
								ondblclick="javascript:viewRequisition('<s:property value="aptReqnCode" />','appoint');"">
								<td width="3%" class="sortableTD"><%=++cnt1%> <%++m1;%>
								</td>
								<td width="25%" class="sortableTD"><s:property
									value="aptReqName" /> <s:hidden name="aptReqnCode" /></td>
								<td width="25%" class="sortableTD"><s:hidden
									name="aprCandCode" /><s:property value="aptCandName" /></td>
								<td width="20%" class="sortableTD"><s:property
									value="aptPos" /></td>
								<!--<td width="12%" class="border2" align="center"><s:property value="aptDays"/>&nbsp;</td>
								    -->
								<s:if test="aptStatusFlag">
									<td width="6%" align="center" class="sortableTD"><s:select
										name="aptStatus" id='<%="aptStatus"+a %>' cssStyle="width:70"
										theme="simple"
										list="#{'':'Select','A':'Approved','R':'Rejected'}" /></td>
								</s:if>
								<s:else></s:else>
								
								<td width="11%" class="sortableTD" align="center"><input
									type="button" class="token" value='View'
									onclick="return viewInterviewApptmtDetails('<s:property value="aptReqnCode" />','<s:property value="aprCandCode" />');" />
								</td>
								<td width="10%" class="sortableTD" align="center"><s:hidden
									name="aptId" /> <s:hidden name="aptResume" /> <input
									type="button" class="token" value='View'
									onclick="return showRecord('<s:property value="aptResume" />');" />
								</td>
								<td width="15%" class="sortableTD" align="center"><s:hidden name="templateCode" />
								 <input
									type="button" class="token" value='View'
									onclick="return validateAppointmentPreviewForTemplate('<s:property value="reqCode" />','<s:property value="candCode" />','<s:property value="templateCode" />')"  />
									</td>
								
							</tr>
							<%l++; a++;%>
						</s:iterator>
						<%m1= m;%>
						<% m=l ; %>

					</table>
					<input type="hidden" name="counter" id="counter" value="<%=a%>" />
					</td>
				</tr>

				<!--<tr>
                    <td  class="cellbg"><table width="130" border="0" align="right" cellpadding="0" cellspacing="0">
                        <tr>
                          <td><strong>Page </strong></td>
                          <td><img src="../pages/images/recruitment/first.gif" width="10" height="10" /></td>
                          <td><img src="../pages/images/recruitment/previous.gif" width="10" height="10" /></td>
                          <td><div align="center">0-1</div></td>
                          <td><img src="../pages/images/recruitment/next.gif" width="10" height="10" /></td>
                          <td><img src="../pages/images/recruitment/last.gif" width="10" height="10" /></td>
                        </tr>
                    </table></td>
                  </tr>
                  
                  -->


			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3" align="center"><s:if test="aptSaveFlag">
				<input type="button" class="save" value=" Save "
					onclick="apptValidation();" />
			</s:if> <input type="submit" class="reset" value="   Clear"
				onclick="return apptmtResetStatus();" /></td>
		</tr>

	</table>
	</td>
	</tr>


</s:form>
<script>
 function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	     cell.className='Cell_bg_second';
		
	}
	function apptValidation(){
	
		var count = document.getElementById("counter").value;
		var flag  = false;
		for(var i=0; i<count; i++){
			if(document.getElementById("aptStatus"+i).value !="")
			flag = true;
			
			
		}
		if(count == 0){
			alert("There is no record to save.");
			return false;
		}else if(!flag){
			alert("Please change the status of atleast one record.");
			return false;
		}else {
		
			document.getElementById('paraFrm').action ="OfferApproval_aptSave.action";
			document.getElementById('paraFrm').submit();
		}
	
	
	
	
	}
	
		function saveValidate(){
		var count = document.getElementById("count").value;
		var flag  = false;
		for(var i=0; i<count; i++){
			if(document.getElementById("offerStatus"+i).value !="")
			flag = true;
			
			
		}
		if(count == 0){
			alert("There is no record to save.");
			return false;
		}else if(!flag){
			alert("Please change the status of atleast one record.");
			return false;
		}else {
		
			document.getElementById('paraFrm').action = "OfferApproval_save.action";
			document.getElementById('paraFrm').submit();
		}
		
		
	}
	
	function resetStatus(){
		document.getElementById("paraFrm").action="OfferApproval_reset.action";
 		document.getElementById("paraFrm").submit();
	}
	 
	function apptmtResetStatus(){
		document.getElementById("paraFrm").action="OfferApproval_aptReset.action";
 		document.getElementById("paraFrm").submit();
	} 
		
	
	//function showRequisition(reqCode,status){

	//	document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=OfferApproval_ckeckdata.action&statusKey='+status;
	//    document.getElementById("paraFrm").submit();
	//}
	
	
	function viewInterviewDetails(reqCode,candCode)
	{
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	
	function showApptmtRequisition(reqCode,appoint){
	
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=OfferApproval_ckeckAptData.action&statusKey='+appoint;
	    document.getElementById("paraFrm").submit();
	}
	
	
	function viewInterviewApptmtDetails(reqCode,candCode)
	{
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	function showRecord(fileName)
	{	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "OfferApproval_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';
		
	}
	
	function viewRequisition(reqCode,type){
	var status = "";
		var action = "OfferApproval_input.action";
		
		if(type == "offer"){
			status = document.getElementById("paraFrm_offerFlag").value;
		
			action = "OfferApproval_ckeckdata.action";
		}else{
			status = document.getElementById("paraFrm_apptFlag").value;
			action = "OfferApproval_ckeckAptData.action";
		}
		
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction='+action+'&statusKey='+status;;
	    document.getElementById("paraFrm").submit();
	}
	 
	 
function callPage(id,pageImg,status){

 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id; 
		document.getElementById('paraFrm').action='OfferApproval_ckeckdata.action?status='+status;
		document.getElementById('paraFrm').submit(); 
	}	
	 
	 
	 function callPageText(id,status){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
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
	        
	         document.getElementById('paraFrm_myPage').value=pageNo; 
			document.getElementById('paraFrm').action='OfferApproval_ckeckdata.action?status='+status;
			document.getElementById('paraFrm').submit();
		} 
	}	
	
	
function callApptPage(id,status1){
		var con;
		if(status1=="null" || status1=="" ){		
			status1="P";
		}
		if(id=='P'){
		var p=document.getElementById('paraFrm_myPage1').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('paraFrm_myPage1').value;
		id=eval(p)+1;
		}
		document.getElementById('paraFrm_myPage1').value=id;
		
		if(status1=="P"){		
			document.getElementById('paraFrm').action='OfferApproval_ckeckAptData.action?status=P';
			document.getElementById('paraFrm').submit();
		}
		else if(status1=="A"){
			document.getElementById('paraFrm').action='OfferApproval_ckeckAptData.action?status=A';
			document.getElementById('paraFrm').submit();
		}
		else if(status1=="R"){
			document.getElementById('paraFrm').action='OfferApproval_ckeckAptData.action?status=R';
			document.getElementById('paraFrm').submit();
		} 	
	}	
	
	
	 function callPageApnt(id,pageImg,status){  
 	 pageNo =document.getElementById('pageNoFieldApnt').value;	
 	 totalPage =document.getElementById('totalPageApnt').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage1').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApnt').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoFieldApnt').value=actPage;
			     document.getElementById('pageNoFieldApnt').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoFieldApnt').value=actPage;
			     document.getElementById('pageNoFieldApnt').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApnt').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoFieldApnt').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoFieldApnt').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoFieldApnt').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoFieldApnt').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoFieldApnt').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage1').value=id; 
		document.getElementById('paraFrm').action='OfferApproval_ckeckAptData.action?status='+status;
		document.getElementById('paraFrm').submit(); 
	}	
	
	
	function callPageTextApnt(id,status){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApnt').value;	 
		 	totalPage =document.getElementById('totalPageApnt').value;	
		 	var actPage = document.getElementById('paraFrm_myPage1').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApnt').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApnt').focus();
		     document.getElementById('pageNoFieldApnt').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApnt').focus();
		     document.getElementById('pageNoFieldApnt').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApnt').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage1').value=pageNo;
		   
			document.getElementById('paraFrm').action='OfferApproval_ckeckAptData.action?status='+status;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	// added ganesh 
	function validatePreviewForTemplate(reqCode,candCode,templateCode){
	
			callReport("OfferApproval_previewoffer.action?templateCode="+templateCode+"&reqCode="+reqCode+"&candCode="+candCode);
	} 
	
	function validateAppointmentPreviewForTemplate(reqCode,candCode,templateCode){
	
			callReport("OfferApproval_previewappointment.action?templateCode="+templateCode+"&reqCode="+reqCode+"&candCode="+candCode);
	} 
	// added ganesh  end
	</script>
