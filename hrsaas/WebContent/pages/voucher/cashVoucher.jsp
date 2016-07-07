 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CashVoucher" method="post" name="CashForm"
	id="paraFrm" theme="simple">

	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelled" id="myPageCancelled" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<s:hidden name="myPageCancelApproved" id="myPageCancelApproved" />
	<s:hidden name="myPage" id="myPage" />
   
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head">
							<img src="../pages/images/recruitment/review_shared.gif" width="25"
								 height="25" />
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Voucher	Application cash</strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- FORM NAME END -->
		<!-- BUTTON PANEL START -->
		<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- BUTTON PANEL END -->
		<!--------------------------------------  Draft LIST OF APPLICATIONS [BEGINS]----------------------------->
		<tr>
			<td>
			<table  width="100%"  border="0" align="right" class="formbg">
				<tr>
					<script>
						    function setAction(listType){
						    
								     if(listType=="p"){
								      	document.getElementById('paraFrm').action='CashVoucher_input.action';
								      	document.getElementById('paraFrm').submit();
								     }if(listType=="a"){
								      	document.getElementById('paraFrm').action='CashVoucher_getApprovedList.action';
								      	document.getElementById('paraFrm').submit();
								     }
								     if(listType=="r"){
								      	document.getElementById('paraFrm').action='CashVoucher_getRejectedList.action';
								      	document.getElementById('paraFrm').submit();
								        
								     } 
								     
						     
						    }
		  			 </script>
					<td><a href="#" onclick="setAction('p')">Pending Application</a> | 
						<a href="#" onclick="setAction('a')">Approved List</a> | 
						<a href="#" onclick="setAction('r')">Rejected List</a> 
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--------------------------------------  PENDING LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'pending'}">

			<!--------- DRAFT SECTION BEGINS - displaying the saved leave applications ------->

			<tr>
				<td>
				<table width="100%"  border="0" align="right" class="formbg">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%"  border="0" align="right" class="formbg">
							<tr>
								<s:hidden name="viewAppFlag"></s:hidden>
								<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="vouchertype" id="vouchertype"
											ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="ename" id="ename"
											ondblclick="callShowDiv(this);"><%=label.get("ename")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="vchdate" id="vchdate" ondblclick="callShowDiv(this);"><%=label.get("vchdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="totalamount" id="totalamount" ondblclick="callShowDiv(this);"><%=label.get("totalamount")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>Edit Application</b></td>

							</tr>

									<%
										int me = 1;
									%>
									<s:iterator value="draftList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=me%></td>
											<td class="sortableTD" width="20%">
												<s:hidden name="empCode" />
												<s:hidden name="vCode" />
												<s:hidden name="type"/>
												<s:property value="type" />
											</td>
											<td class="sortableTD" width="20%">
												<s:property value="ename" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="vchDate" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="totalamount"  />
											</td>
											<td class="sortableTD" width="20%" nowrap="nowrap">
												<s:hidden name="hiddenStatus" />
												<s:property value="status" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<input	type="button" name="view_Details" class="token"
														value="Edit Application" onclick="viewDetails('<s:property value="vCode"/>',
																										'<s:property value="status"/>',
																										'<s:property value="empCode"/>')" />
											</td>
										</tr>
										<%
										me++;
										%>
									</s:iterator>

									<%
									if (me == 1) {
									%>
									<tr align="center">
										<td colspan="7" class="sortableTD" width="100%"><font
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

			<!--------- DRAFT SECTION ENDS - displaying the saved leave applications -------->
			<!--------- SUBMIT SECTION BEGINS - displaying the sent leave applications -------->

			<tr>
				<td>
				<table width="100%"  border="0" align="right" class="formbg">
					<tr>
						<td><b>Applications In Process</b></td>
					</tr>
					<tr>
								<td>
								<table width="100%"  border="0" align="right" class="formbg">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="vouchertype" id="vouchertype"
											ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="ename" id="ename"
											ondblclick="callShowDiv(this);"><%=label.get("ename")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="vchdate" id="vchdate" ondblclick="callShowDiv(this);"><%=label.get("vchdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="totalamount" id="status" ondblclick="callShowDiv(this);"><%=label.get("totalamount")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>

									<%
									int i = 1;
									%>
									<s:iterator value="submitList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=i%></td>
											<td class="sortableTD" width="20%">
												<s:hidden name="empCode" />
												<s:hidden name="vCode" />
												<s:hidden name="type"/>
												<s:property value="type" />
											</td>
											<td class="sortableTD" width="20%">
												<s:property value="ename" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="vchDate" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="totalamount" />
											</td>
											<td class="sortableTD" width="20%" nowrap="nowrap">
												<s:hidden name="hiddenStatus" />
												<s:property value="status" />
											</td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="vCode"/>','<s:property value="status"/>','<s:property value="empCode"/>')" /></td>
										</tr>
										<%
										i++;
										%>
									</s:iterator>

									<%
									if (i == 1) {
									%>
									<tr align="center">
										<td colspan="7" class="sortableTD" width="100%"><font
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
			<!--------- SUBMIT SECTION ENDS - displaying the sent leave applications -------->
			
			<!--------- SEND BACK LIST - displaying the sent leave applications -------->
			<tr>
				<td>
				<table width="100%"  border="0" align="right" class="formbg">
					<tr>
						<td><b>Send back list </b></td>
					</tr>
					<tr>
						
								<td>
								<table width="100%"  border="0" align="right" class="formbg">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="vouchertype" id="vouchertype"
											ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="ename" id="ename"
											ondblclick="callShowDiv(this);"><%=label.get("ename")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="vchdate" id="vchdate" ondblclick="callShowDiv(this);"><%=label.get("vchdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="totalamount" id="status" ondblclick="callShowDiv(this);"><%=label.get("totalamount")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>Edit Application</b></td>

									</tr>

									<%
									int i1 = 1;
									%>
									<s:iterator value="sendBackList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=i1%></td>
											<td class="sortableTD" width="20%">
												<s:hidden name="empCode" />
												<s:hidden name="vCode" />
												<s:hidden name="type"/>
												<s:property value="type" />
											</td>
											<td class="sortableTD" width="20%">
												<s:property value="ename" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="vchDate" />
											</td>
											<td class="sortableTD" width="20%" align="center">
												<s:property value="totalamount" />
											</td>
											<td class="sortableTD" width="20%" nowrap="nowrap">
												<s:hidden name="hiddenStatus" />
												<s:property value="status" />
											</td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="vCode"/>','<s:property value="status"/>','<s:property value="empCode"/>')" /></td>
										</tr>
										<%
										i1++;
										%>
									</s:iterator>

									<%
									if (i1 == 1) {
									%>
									<tr align="center">
										<td colspan="7" class="sortableTD" width="100%"><font
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
			<!-- SEND BACK LIST IS ENDED -->
			</s:if>
		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%"  border="0" align="right" class="formbg">
							<%	int totalPage = 0;	int pageNo = 0;%>
							<tr >
							<td width="50%"><b>Approved Applications List</b></td>
							
									<s:if test="approveLength">
								
										<td  id="ctrlShow" width="50%" align="right"><b>Page:</b>
											<%
												totalPage = (Integer) request.getAttribute("totalPage");
												pageNo = (Integer) request.getAttribute("pageNo");
											%> 
											<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'CashVoucher_getApprovedList.action');">
												<img title="First Page" src="../pages/common/img/first.gif"
													width="10" height="10" class="iconImage" />
											</a>&nbsp; 
											<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'CashVoucher_getApprovedList.action');">
												<img title="Previous Page"
													src="../pages/common/img/previous.gif" width="10" height="10"
													class="iconImage" /> 
											</a>
											
										    <input type="text" name="pageNoField"
												   id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
											   onkeypress="callPageText(event, '<%=totalPage%>', 'CashVoucher_getApprovedList.action');return numbersOnly();" />
											of <%=totalPage%> 
											<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'CashVoucher_getApprovedList.action')">
												<img title="Next Page" src="../pages/common/img/next.gif"
													 class="iconImage" />
										    </a>&nbsp;
											<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CashVoucher_getApprovedList.action');">
												<img title="Last Page" src="../pages/common/img/last.gif"
													width="10" height="10" class="iconImage" /> 
											</a>
									   </td>
									
								</s:if>
						
							</tr>
							
							<tr>
								<td colspan="2">
								<table width="100%"  border="0" align="right" class="formbg">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="vouchertype" id="vouchertype"
											ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="ename" id="ename"
											ondblclick="callShowDiv(this);"><%=label.get("ename")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="vchdate" id="vchdate" ondblclick="callShowDiv(this);"><%=label.get("vchdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="totalamount" id="status" ondblclick="callShowDiv(this);"><%=label.get("totalamount")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="approveLength">
									   <%
									   		int cnt=1;
											int ii = pageNo * 20 - 20;
										%>
										<s:iterator value="approvedList">
											<tr>
													<td class="sortableTD" width="5%" align="center">
														
														<%=cnt++%>
													</td>
													<td class="sortableTD" width="20%">
														<s:hidden name="empCode" />
														<s:hidden name="vCode" />
														<s:hidden name="type"/>
														<s:property value="type" />
													</td>
													<td class="sortableTD" width="20%">
														<s:property value="ename" />
													</td>
													<td class="sortableTD" width="20%" align="center">
														<s:property value="vchDate" />
													</td>
													<td class="sortableTD" width="20%" align="center">
														<s:property value="totalamount" />
													</td>
													<td class="sortableTD" width="20%" nowrap="nowrap">
														<s:hidden name="hiddenStatus" />
														<s:property value="status" />
													</td>
													<td class="sortableTD" width="20%" align="center"><input
														type="button" name="view_Details" class="token"
														value=" View Application "
														onclick="viewDetails('<s:property value="vCode"/>','<s:property value="status"/>','<s:property value="empCode"/>')" />
													</td>
											</tr>
											<%ii++;%>

										</s:iterator>
										</s:if>
										<s:if test="approveLength"></s:if>
										<s:else>
											<tr align="center">
												<td colspan="7" class="sortableTD" width="100%"><font
													color="red">No Data to display</font></td>
											</tr>
									</s:else>
								</table>
								</td>
							</tr>
				
				</table>
				</td>
			</tr>

			
		</s:if>
		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [ENDS] ------------------------------->

	    <!-------------------------------------- REJECTED APPLICATIONS LIST [BEGINS]----------------------------->

		<s:if test="%{listType == 'rejected'}">
			<tr>
				<td>
				<table width="100%"  class="formbg">
					<%
						int totalPageRejected = 0;
						int PageNoRejected = 0;
					%>
					<tr>
						<td><b>Rejected Applications List</b></td>
						<s:if test="rejectedLength">
						
							<td id="ctrlShow"  align="right"><b>Page:</b> 
								<%
									totalPageRejected = (Integer) request.getAttribute("totalPageRejected");
								 	PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
								 %> 
	 							<a href="#" onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'CashVoucher_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										 width="10" height="10" class="iconImage" />
							    </a>&nbsp; 
							    <a href="#" onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'CashVoucher_getRejectedList.action');">
									<img title="Previous Page" src="../pages/common/img/previous.gif"
										width="10" height="10" class="iconImage" /> 
								</a> 
								<input type="text" name="pageNoField3" id="pageNoField3" size="3"
									   value="<%=PageNoRejected%>" maxlength="10"
									   onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'CashVoucher_getRejectedList.action');return numbersOnly();" />
										of <%=totalPageRejected%>
								 <a href="#" onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'CashVoucher_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" />
								 </a>&nbsp; 
								 <a href="#" onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'CashVoucher_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> 
								 </a>
							</td>
						
					</s:if>
					</tr>
					
					
					<tr>
								<td colspan="2">
								<table width="100%" >
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="vouchertype" id="vouchertype"
											ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="ename" id="ename"
											ondblclick="callShowDiv(this);"><%=label.get("ename")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="vchdate" id="vchdate" ondblclick="callShowDiv(this);"><%=label.get("vchdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="totalamount" id="status" ondblclick="callShowDiv(this);"><%=label.get("totalamount")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="rejectedLength">
										<%
										int cntRejLst=1;
										int cn3 = PageNoRejected * 20 - 20;
										%>

										<s:iterator value="rejectedList">
											<tr>
												<td class="sortableTD" width="5%" align="center">
													
													<%=cntRejLst++%>
												</td>
													<td class="sortableTD" width="20%">
														<s:hidden name="empCode" />
														<s:hidden name="vCode" />
														<s:hidden name="type"/>
														<s:property value="type" />
													</td>
													<td class="sortableTD" width="20%">
														<s:property value="ename" />
													</td>
													<td class="sortableTD" width="20%" align="center">
														<s:property value="vchDate" />
													</td>
													<td class="sortableTD" width="20%" align="center">
														<s:property value="totalamount" />
													</td>
													<td class="sortableTD" width="20%">
														<s:hidden name="hiddenStatus" />
														<s:property value="status" />
													</td>
													<td class="sortableTD" width="20%" align="center"><input
														type="button" name="view_Details" class="token"
														value=" View Application "
														onclick="viewDetails('<s:property value="vCode"/>','<s:property value="status"/>','<s:property value="empCode"/>')" />
													</td>
											</tr>
											<%cn3++;%>
										</s:iterator>
									</s:if>

									<s:if test="rejectedLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="7" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
									</s:else>
								</table>
								</td>
							</tr>
				</table>
				</td>
			</tr>
		
		</s:if>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

</s:form>


<script>

 
function addapplicationFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "CashVoucher_addNew.action";
		 //?levapp_random='+Math.random();
		document.getElementById('paraFrm').submit();
}
function addhrsapplicationFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'CashVoucher_addNew.action?flag=flag';
	document.getElementById('paraFrm').submit();
}
	

function viewDetails(appNo,status,empID){
	document.getElementById('paraFrm').action='CashVoucher_retrive.action?vCode='+appNo+'&cvStatus='+status+'&empCode='+empID;
   	document.getElementById('paraFrm').submit();
}
 
	// APPROVED CANCELLATION APPLICATIONS LIST
function callPageApproveCancel(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageCancelApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField1').value = actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField1').value=actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField1').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextApproveCancel(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField1').value;
		 	var actPage = document.getElementById('myPageCancelApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField1').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField1').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}

	// FOR CANCELLED APPLICATIONS LIST
	
	function callPageCancelled(id, pageImg, totalPageHid, action) {	
	
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageCancelled').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField2').value = actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField2').value=actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField2').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelled').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	function callPageTextCancel(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageCancelled').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField2').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField2').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelled').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	

		
		// REJECTED APPLICATIONS LIST
	
	function callPageRejected(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField3').value = actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField3').value=actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField3').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	function callPageTextReject(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField3').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField3').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField3').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	




// Rejected Cancellation Applications List
	
	function callPageRejectedCancel(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoField4').value;	
		actPage = document.getElementById('myPageCancelRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField4').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField4').value = actPage;
			    document.getElementById('pageNoField4').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField4').value=actPage;
			    document.getElementById('pageNoField4').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField4').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField4').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField4').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextRejectCancel(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField4').value;
		 	var actPage = document.getElementById('myPageCancelRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField4').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField4').focus();
		     document.getElementById('pageNoField4').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField4').focus();
		     document.getElementById('pageNoField4').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField4').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
</script>
 