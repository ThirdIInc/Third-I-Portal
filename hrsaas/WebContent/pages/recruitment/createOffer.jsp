<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CreateOffer" validate="true" id="paraFrm"
 theme="simple">
	<s:hidden name="offerStatus" />
	<s:hidden name="appointmentStatus" />
	<s:hidden name="hiddenOfferCode" />
	<s:hidden name="hiddenRequisitionCode"/>
	<s:hidden name="hiddenAppointmentCode" />
	<s:hidden name="buttonName" />
	<s:hidden name="myPageOffer" />
	<s:hidden name="myPageApp" />
	<s:hidden name="show" />
	<s:hidden name="recordLength" />
	<s:hidden name="searchFlag" />
	<s:hidden name="chkSerch" />
	<s:hidden name="reqCodeFlag" />
	<s:hidden name="candFlag" />
	<s:hidden name="positionFlag" />
	<s:hidden name="hiringFlag" />
	<s:hidden name="dueDaysFlag" />

	<table class="formbg" width="100%">
		<!--main table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Create
					Offer Letter</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4" align="left"><s:if
				test="offerDueFlag">
				<s:submit cssClass="token" value="Issue Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
				<s:submit cssClass="token" value="Create New Offer"
					action="CreateOffer_toOfferDetails" onclick="createNewOffer();" />
			</s:if> <s:elseif test="offerIssueFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
				<s:submit cssClass="token" value="Cancel Offer"
					action="CreateOffer_cancelOffer" onclick="return cancelOffer('I');" />
			</s:elseif> <s:elseif test="offerAccFlag">
				<s:submit cssClass="token" value="Cancel Offer"
					action="CreateOffer_cancelOffer"
					onclick="return cancelOffer('OA');" />
			</s:elseif> <s:elseif test="offerRejFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
			</s:elseif> <s:elseif test="offerCancelFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
			</s:elseif></td>
		</tr>
		<tr>
			<td colspan="5" class="formbg">
			<table width="100%">
				<tr>
					<td width="78%" colspan="2"><strong class="text_head">Offer
					Details </strong></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 1 -->
				<tr>
					<td>
						<a href="CreateOffer_showOfferList.action?status=D">Offer Due</a> | 
						<a href="CreateOffer_showOfferList.action?status=P"	onclick="showApprovedOffer();">Offer Approval</a> | 
						<a href="CreateOffer_showOfferList.action?status=I">Offer Issued</a> | 
						<a href="CreateOffer_showOfferList.action?status=OA">Offer Accepted</a> | 
						<a href="CreateOffer_showOfferList.action?status=S"> Offer Rejected</a> | 
						<a href="CreateOffer_showOfferList.action?status=C"> Offer Cancelled</a>
					</td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength"></s:hidden>
			</table>
			<!-- table 1 --></td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<!-- table option -->
				<tr>
					<td colspan="2"><s:if test="clearFlag"></s:if><s:else>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>

								<td width="40%"><strong class="text_head"><s:if
									test="searchFlag">Applied Filter</s:if><s:else>
									<label class="set" name="searchApply.filter"
										id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></label>
								</s:else></strong></td>

								<td id="showFilter" align="right"><input type="button"
									value="Show Filter" cssClass="token"
									onclick="return callShowFilter();"></td>

								<td id="hideFilter" align="right"><input type="button"
									value="Hide Filter" cssClass="token"
									onclick="return callHideFilter();"></td>
							</tr>
						</table>
					</s:else></td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="3"
						id="showFilterToApp">

						<tr>
							<td width="20%" height="22" class="formtext"><label
								class="set" name="reqs.code" id="reqs.code1"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="30"
								name="searchRequisitionCode" readonly="true" maxlength="30" />
							</td>
							<td width="10%" nowrap="nowrap">
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateOffer_f9Reqs.action');">


							<s:hidden name="searchHidRequisitionCode" /><s:hidden name="searchHidRequiredbyDate" /></td>
							
							<td width="20%" height="22" class="formtext"><label
								class="set" name="cand.name" id="cand.name1"
								ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="25"
								name="searchCandName" readonly="true" maxlength="30" /></td> 
							<td width="10%" nowrap="nowrap"><img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateOffer_f9Candidate.action');">
							</td>
							<s:hidden name="searchCandCode" />

						</tr>

						<tr>
							<td width="20%" height="22" class="formtext"><label
								class="set" name="position" id="position1"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="30"
								name="searchPosition" readonly="true" maxlength="30" /></td>
								<td width="10%" nowrap="nowrap"> <img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateOffer_f9Position.action');">
							<s:hidden name="searchPositionId" /></td>
							

							<td width="20%" height="22" class="formtext"><label
								class="set" name="hiring.mgr" id="hiring.mgr1"
								ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
							:</td>
							<td width="13%" height="22" nowrap="nowrap"><s:textfield size="25"
								name="searchHiringMgr" readonly="true" maxlength="30" /></td> 
							<td width="10%" nowrap="nowrap">	<img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'CreateOffer_f9Hiring.action');">
							</td>
							<s:hidden name="searchHiringMgrId" />

						</tr>
						<s:if
							test="%{ offerApprPendingFlag || offerApprRejFlag || offerApprFlag || offerDueFlag }">
							<tr>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="due" id="due1"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label> :</td>
								<td width="13%" height="22" nowrap="nowrap"><s:textfield size="10"
									name="searchDueSinceDays" onkeypress="return numbersOnly();"
									maxlength="3" /></td>
								<td width="10%"></td>	
							</tr>
						</s:if>
						<s:if
							test="%{offerIssueFlag || offerAccFlag || offerRejFlag || offerCancelFlag}">

							<tr>

								<td width="20%"><label class="set" name="offer.frdate"
									id="offer.frdate" ondblclick="callShowDiv(this);"><%=label.get("offer.frdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offFrmDate" size="30"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									readonly="false" /></td><td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_offFrmDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
								
								<td width="20%"><label class="set" name="offer.todate"
									id="offer.todate" ondblclick="callShowDiv(this);"><%=label.get("offer.todate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offToDate" size="25"
									maxlength="10" onkeypress="return numbersWithHiphen();"
									readonly="false" /></td><td width="10%"><s:a
									href="javascript:NewCal('paraFrm_offToDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
							</tr>
						</s:if>



						<s:if test="%{offerAccFlag}">

							<tr>

								<td width="20%"><label class="set" name="offer.accFrdate"
									id="offer.accFrdate" ondblclick="callShowDiv(this);"><%=label.get("offer.accFrdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offAccFrmDate" size="30"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td><td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_offAccFrmDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
								
								<td width="20%"><label class="set" name="offer.accTodate"
									id="offer.accTodate" ondblclick="callShowDiv(this);"><%=label.get("offer.accTodate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offAccToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td><td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_offAccToDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
							</tr>
						</s:if>


						<s:if test="%{offerRejFlag}">

							<tr>

								<td width="20%"><label class="set" name="offer.RejFrdate"
									id="offer.RejFrdate" ondblclick="callShowDiv(this);"><%=label.get("offer.RejFrdate")%></label>
								:</td>
								<td width="13%"><s:textfield name="offRejFrmDate" size="30"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td><td width="10%"><s:a
									href="javascript:NewCal('paraFrm_offRejFrmDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
								
								<td width="20%"><label class="set" name="offer.RejTodate"
									id="offer.RejTodate" ondblclick="callShowDiv(this);"><%=label.get("offer.RejTodate")%></label>
								:</td>
								<td width="13%"><s:textfield name="offRejToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td><td width="10%"><s:a
									href="javascript:NewCal('paraFrm_offRejToDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
							</tr>
						</s:if>


						<s:if test="%{offerCancelFlag}">

							<tr>

								<td width="20%"><label class="set" name="offer.canFrdate"
									id="offer.canFrdate" ondblclick="callShowDiv(this);"><%=label.get("offer.canFrdate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offCanFrmDate" size="30"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td>
									<td width="10%" nowrap="nowrap">
									<s:a
									href="javascript:NewCal('paraFrm_offCanFrmDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
								
								<td width="20%"><label class="set" name="offer.canTodate"
									id="offer.canTodate" ondblclick="callShowDiv(this);"><%=label.get("offer.canTodate")%></label>
								:</td>
								<td width="13%" nowrap="nowrap"><s:textfield name="offCanToDate" size="25"
									readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();" /></td>
									<td width="10%" nowrap="nowrap"><s:a
									href="javascript:NewCal('paraFrm_offCanToDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" border="0" />
								</s:a></td>
							</tr>
						</s:if>

						<tr>
							<td align="center" colspan="5"><input type="button"
								class="token" theme="simple" value="Apply Filter"
								onclick="return applyFilter();" />&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return calReset();"
								value="Reset " /></td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>

					<td width="100%">
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

							<td width="20%" height="22" class="formtext"><%=dispArr[count]%></td>
							<%
							count++;
							%>
							<%
							}
							%>
							<%
							if (count < dispArr.length) {
							%>

							<td width="20%" height="22" class="formtext"><%=dispArr[count]%></td>
							<%
							count++;
							%>
							<%
							}
							%>


						</tr>

						<%
							}
						%>
						<tr>
							<td align="center" colspan="5">&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return callClear();"
								value="Clear Filter" /> &nbsp;
								
								<input type="button" class="token" theme="simple" onclick="callShowFilter();"
								value="Edit Filter"  />
								
								</td>
						</tr>

					</table>


					<%
							} 
						%>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 2 -->
				<tr id="approvedOffer">
					<td width="100%" colspan="9">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!-- table 3 -->
						<tr>

							<td><strong>Offer Approval List</strong></td>
						</tr>
						<tr>
							<td><a href="CreateOffer_showOfferList.action?status=P">Pending
							List</a> | <a href="CreateOffer_showOfferList.action?status=A">Approved
							List</a> | <a href="CreateOffer_showOfferList.action?status=R">Rejected
							List</a></td>
						</tr>
					</table>
					<!-- table 3 --></td>
				</tr>

				<tr>
					<td height="27" class="formtxt"><strong>
					<%
                    					String offerStatus = (String) request
                    					.getAttribute("offerListStatus");
                    			if (offerStatus != null) {
                    				out.println(offerStatus);
                    			} else {
                    				out.println("Offer Due List");
                    			}
                    		%>
					</strong></td>

					<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					%>
					<s:if test="noData"></s:if>
					<s:else>
						<td align="right"><b>Page:</b> <input type="hidden"
							name="totalPage" id="totalPage" value="<%=totalPage%>"> <a
							href="#"
							onclick="callPage('1','F','<s:property value="offerStatus"/>');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P','P','<s:property value="offerStatus"/>');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" theme="simple" size="3"
							value="<%= pageNo%>"
							onkeypress="callPageText(event,'<s:property value="offerStatus"/>');return numbersOnly()"
							maxlength="4" /> of <%=totalPage%> <a href="#"
							onclick="callPage('N','N','<s:property value="offerStatus"/>')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>','L','<s:property value="offerStatus"/>');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:else>

				</tr>

				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- table 4 -->
						<s:if test="offerDueFlag">
							<tr>
								<td width="1%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="due" id="due"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label></b></td>
								 
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								 
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.eval" id="view.eval"
									ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="view.cv" id="view.cv"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerIssueFlag">
							<tr>
								<td width="1%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="offer.date" id="offer.date"
									ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label></b></td>
								
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="offered.ctc" id="offered.ctc"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								 
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offer" id="view.offer"
									ondblclick="callShowDiv(this);"><%=label.get("view.offer")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerAccFlag">
							<tr>
								<td width="1%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="offer.date" id="offer.date"
									ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="acc.date"
									id="acc.date" ondblclick="callShowDiv(this);"><%=label.get("acc.date")%></label></b></td>
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="offered.ctc" id="offered.ctc"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								 
								  <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								 <td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offerDetails" id="view.offerDetails"
									ondblclick="callShowDiv(this);"><%=label.get("view.offerDetails")%></label></b></td>
							 	<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offerLetter" id="view.offerLetter"
									ondblclick="callShowDiv(this);"><%=label.get("view.offerLetter")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerRejFlag">
							<tr>
								<td width="1%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="offer.date" id="offer.date"
									ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="rej.date"
									id="rej.date" ondblclick="callShowDiv(this);"><%=label.get("rej.date")%></label></b></td>
								
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="offered.ctc" id="offered.ctc"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
									
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offerDetails" id="view.offerDetails"
									ondblclick="callShowDiv(this);"><%=label.get("view.offerDetails")%></label></b></td>	
								 
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offer" id="view.offer"
									ondblclick="callShowDiv(this);"><%=label.get("view.offer")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerCancelFlag">
							<tr>
								<td width="1%" class="formth">&nbsp;</td>
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="offer.date" id="offer.date"
									ondblclick="callShowDiv(this);"><%=label.get("offer.date")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set"
									name="cancel.date" id="cancel.date"
									ondblclick="callShowDiv(this);"><%=label.get("cancel.date")%></label></b></td>
								
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="offered.ctc" id="offered.ctc"
									ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label></b></td>
								 
								<td width="8%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.offer" id="view.offer"
									ondblclick="callShowDiv(this);"><%=label.get("view.offer")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerApprPendingFlag">
							<tr>
								<!--				        				<td width="1%" class="formth"></td>-->
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>	
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="due" id="due"
									ondblclick="callShowDiv(this);"><%=label.get("due")%></label></b></td>
								 
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.eval" id="view.eval"
									ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.cv" id="view.cv"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerApprFlag">
							<tr>
								<!--				        				<td width="1%" class="formth"></td>-->
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="12%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center"> 
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>		
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="apprDate" id="apprDate"
									ondblclick="callShowDiv(this);"><%=label.get("apprDate")%></label></b></td>
								 
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.eval" id="view.eval"
									ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.cv" id="view.cv"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="offerApprRejFlag">
							<tr>
								<!--				        				<td width="1%" class="formth"></td>-->
								<td width="6%" valign="top" class="formth" align="center"
									nowrap="nowrap"><b><label class="set" name="serial.no"
									id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
								<b><label class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="15%" valign="top" class="formth" align="center">
								<b><label class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
				     					<b><label  class = "set" name="signing.authority" id="signing.authority" 
											ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label></b>
								</td>		
								<td width="8%" valign="top" class="formth" align="center">
								<b><label class="set" name="rej.date" id="rej.date"
									ondblclick="callShowDiv(this);"><%=label.get("rej.date")%></label></b></td>
								 
								 <td width="12%" valign="top" class="formth" align="center">
									<b>
										<label class="set" name="joiningDateInList" id="joiningDateInList" ondblclick="callShowDiv(this);"><%=label.get("joiningDateInList")%></label>
									</b>
								</td> 
								
								<td width="12%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.eval" id="view.eval"
									ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></b></td>
								<td width="10%" valign="top" class="formth" align="center">
									<b><label class="set" name="view.cv" id="view.cv"
									ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
							</tr>
						</s:if>

						<s:if test="noOfferDataFlag">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>

						<%!int i = 0;%>
						<%
					            	int k = 1;
					            	int c = 0;
					            %>

						<%
					            					            		int cnt = pageNo * 20 - 20;
					            					            		int m = 0;
					            					            		int countRow = 0;
					            					            	%>


						<s:if test="offerDueFlag">
							<s:iterator value="offerList">
									<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" >
									 
									<td widtg="6%" class="sortableTD" valign="middle"><input
										type="hidden" name="radioOffer" id='<%="radioOffer"+c %>'
										value="N"> <input type="radio" name="radOffer"
										id="<%="radOffer"+c %>"
										onclick="callChk('<%="radioOffer"+c %>')" /></td>
									<td width="6%" class="sortableTD" valign="middle"
										nowrap="nowrap"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' />
									</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/><s:hidden
										name="reqNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" /></td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="dueDays" /> <s:hidden
										name="dueDays" />&nbsp;</td> 
									
									 <td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:hidden name="resumeOffer" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeOffer"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerIssueFlag">
							<s:iterator value="offerList">
									<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 
									<!--					            			<td width="6%" class="border2" valign="middle">-->
									<!--					            				<input type="radio" name="radOffer" id="<%="radOffer"+c %>" onclick="callChk('<%="radioOffer"+c %>')"/>-->
									<!--												<input type="hidden" name="radioOffer" id="<%="radioOffer"+c %>" value="N">&nbsp;</td>-->
									<td width="6%" class="sortableTD" valign="middle"><input
										type="radio" name="radOffer" id="<%="radOffer"+c %>"
										onclick="callChk('<%="radioOffer"+c %>')" /> <input
										type="hidden" name="radioOffer" id="<%="radioOffer"+c %>"
										value="N"></td>
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' />
									</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="offerDate" /> <s:hidden
										name="offerDate" />&nbsp;</td>
									
									 <td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
									<s:hidden name="joiningDateIterator" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><s:property value="offeredCtc" /> <s:hidden
										name="offeredCtc" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="offerTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewOfferTemplate('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>',
						                    			'<s:property value="offerTemplate"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerAccFlag">
							<s:iterator value="offerList">
								<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick= "viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');"  title="Double click for view Requisition" > 
									<td width="4%" class="sortableTD" valign="middle"><input
										type="radio" name="radOffer" id="<%="radOffer"+c %>"
										onclick="callChk('<%="radioOffer"+c %>')" /> <input
										type="hidden" name="radioOffer" id='<%="radioOffer"+c %>'
										value="N"></td>
									<td width="4%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' />
									</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="left" nowrap="nowrap"><s:property value="offerDate" /> <s:hidden
										name="offerDate" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><s:property value="offerAcceptedDate" /> <s:hidden
										name="offerAcceptedDate" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="offeredCtc" /> <s:hidden
										name="offeredCtc" />&nbsp;</td>
									
									<td width="12%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><input type="button" class="token"
										value="View"
										onclick="viewOfferDetails('<s:property value="offerCode"/>','<s:property value="reqCodeOffer"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="offerTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewOfferTemplate('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>',
						                    			'<s:property value="offerTemplate"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerRejFlag">
							<s:iterator value="offerList">
								
				<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 

									<td width="4%" class="sortableTD" width="6%" valign="middle"><input
										type="radio" name="radOffer" id="<%="radOffer"+c %>"
										onclick="callChk('<%="radioOffer"+c %>')" /> <input
										type="hidden" name="radioOffer" id='<%="radioOffer"+c %>'
										value="N"></td>
									<td width="4%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' />
									</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><s:property value="offerDate" /> <s:hidden
										name="offerDate" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="offerAcceptedDate" /> <s:hidden
										name="offerAcceptedDate" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="offeredCtc" /> <s:hidden
										name="offeredCtc" />&nbsp;</td>									 
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><input type="button" class="token"
										value="View"
										onclick="viewOfferDetails('<s:property value="offerCode"/>','<s:property value="reqCodeOffer"/>')" />&nbsp;</td>
									
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="offerTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewOfferTemplate('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>',
						                    			'<s:property value="offerTemplate"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerCancelFlag">
							<s:iterator value="offerList">
								
				<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 
									<td class="sortableTD" width="4%" valign="middle"><input
										type="radio" name="radOffer" id="<%="radOffer"+c %>"
										onclick="callChk('<%="radioOffer"+c %>')" /> <input
										type="hidden" name="radioOffer" id='<%="radioOffer"+c %>'
										value="N"></td>
									<td width="4%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' />
									</td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><s:property value="offerDate" /> <s:hidden
										name="offerDate" />&nbsp;</td>
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="offerAcceptedDate" /> <s:hidden
										name="offerAcceptedDate" />&nbsp;</td>
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="offeredCtc" /> <s:hidden
										name="offeredCtc" />&nbsp;</td> 
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="offerTemplate" /> <input
										type="button" value="View" class="token"
										onclick="viewOfferTemplate('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>',
						                    			'<s:property value="offerTemplate"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerApprPendingFlag">
							<s:iterator value="offerList"> 
							<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' /></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="12%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOfferCode" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:property value="dueDays" /> <s:hidden
										name="dueDays" />&nbsp;</td> 
									
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="10%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeOffer" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeOffer"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerApprFlag">
							<s:iterator value="offerList">
							
				<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        				<s:hidden name="offerCode" id='<%="offerCode"+c %>' /></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOffer" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="offerApprovedDate" /> <s:hidden
										name="offerApprovedDate" />&nbsp;</td>
									 
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeOffer" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeOffer"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>

						<s:if test="offerApprRejFlag">
							<s:iterator value="offerList">
								
						<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return viewRequisition('<s:property value="reqCodeOffer"/>', 'offer');" title="Double click for view Requisition" > 
									
									<td width="6%" class="sortableTD" valign="middle"><%=++cnt%>
									<%
					        				++m;
					        				%> 
					        			<s:hidden name="offerCode" id='<%="offerCode"+c %>' /></td>
									<td width="10%" class="sortableTD" align="left" valign="middle"><s:property
										value="reqNameOffer" /> <!--<input type="button" class="token" value='<s:property value ="reqNameOffer"/>' 
					      	   						onclick = "viewRequisition('<s:property value="reqCodeOffer"/>')"/>
												--><s:hidden name="empCodeOffer" />
												<s:hidden name="reqCodeOffer" id='<%="reqCodeOffer"+c %>'/> 
												<s:hidden name="reqNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="canddNameOffer" /> <s:hidden name="candCodeOffer" /><s:hidden
										name="canddNameOffer" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="positionOffer" /> <s:hidden name="positionOfferCode" />&nbsp;</td>
									<td width="15%" class="sortableTD" valign="middle"><s:property
										value="hireMgrOffer" /> <s:hidden name="hireMgrOfferCode" />&nbsp;</td>
									<td width="10%" class="sortableTD" valign="middle"><s:property 
									    value="signAuthorOffer" /><s:hidden name="signAuthorOffer" />&nbsp;</td>	
									<td width="8%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="offerApprovedDate" /> <s:hidden
										name="offerApprovedDate" />&nbsp;</td>
									 
									<td width="10%" valign="middle" class="sortableTD"
										align="center" nowrap="nowrap"><s:property value="joiningDateIterator" />
										<s:hidden name="joiningDateIterator" />&nbsp;</td>
										
									<td width="12%" valign="middle" class="sortableTD"
										align="center"><input type="button" value="View"
										class="token"
										onclick="viewInterviewDetails('<s:property value="reqCodeOffer"/>', '<s:property value="candCodeOffer"/>')" />&nbsp;</td>
									<td width="8%" valign="middle" class="sortableTD"
										align="center"><s:hidden name="resumeOffer" /> <input
										type="button" class="token" value="View"
										onclick="viewCV('<s:property value="resumeOffer"/>');" />&nbsp;</td>
								</tr>
								<%
					                		k++;
					                		c++;
					                	%>
							</s:iterator>
							<%
					            	m = i;
					            	%>
						</s:if>
						<%
			              		i = k;
			              		%>
					</table>
					<!-- table 4 --></td>
				</tr>
			</table>
			<!-- table 2 --></td>
			<input type="hidden" name="countOffer" id="countOffer" value="<%=c%>" />
		</tr>
		<tr align="left">
			<td width="50%" align="left"><s:if test="offerDueFlag">
				<s:submit cssClass="token" value="Issue Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
				<s:submit cssClass="token" value="Create New Offer"
					action="CreateOffer_toOfferDetails" onclick="createNewOffer();" />
			</s:if> <s:elseif test="offerIssueFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
				<s:submit cssClass="token" value="Cancel Offer"
					action="CreateOffer_cancelOffer" onclick="return cancelOffer('I');" />
			</s:elseif> <s:elseif test="offerAccFlag">
				<s:submit cssClass="token" value="Cancel Offer"
					action="CreateOffer_cancelOffer"
					onclick="return cancelOffer('OA');" />
			</s:elseif> <s:elseif test="offerRejFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
			</s:elseif> <s:elseif test="offerCancelFlag">
				<s:submit cssClass="token" value="Revise Offer"
					action="CreateOffer_toOfferDetails" onclick="return createOffer();" />
			</s:elseif></td>
			<td width="50%" align="Right"><s:if test="recordLength">
				<b>Number of records :&nbsp;<s:property value="totalRecords" /></b>
			</s:if></td>
		</tr>
	</table>
	<!-- main table -->
</s:form>
<script>
//window.onload=   document.getElementById('pageNoField').focus();
showApprovedOffer();

	function showApprovedOffer(){
		offerStatus = document.getElementById("paraFrm_offerStatus").value;
		
		if(offerStatus == "P" || offerStatus == "A" || offerStatus == "R"){
			document.getElementById("approvedOffer").style.display = '';
		}else{
			document.getElementById("approvedOffer").style.display = 'none';
		}
		
	}
		
	function createOffer(){
		var countOffer = document.getElementById("countOffer").value;
		var checkFlag  = false;
		
		document.getElementById("paraFrm_buttonName").value = "Y";
		
		for(var i=0; i<countOffer; i++){
			if(document.getElementById("radOffer"+i).checked){
				checkFlag = true;
				document.getElementById("paraFrm_hiddenOfferCode").value = document.getElementById("offerCode"+i).value;
				document.getElementById("paraFrm_hiddenRequisitionCode").value = document.getElementById("reqCodeOffer"+i).value;
			}
		}
		
		if(countOffer == 0){
			alert("There is no record in the list");
			return false;
		}
		
		if(!checkFlag){
			alert("Please select a candidate");
			return false;
		}
		return true;
	}
	
	function cancelOffer(status){
		if(!createOffer())return false;
		
		document.getElementById("paraFrm_offerStatus").value = status;
		//alert(document.getElementById("paraFrm_offerStatus").value);
		
		var conf = confirm("Do you really want to cancel the offer?");
		
		if(conf){
			return true;
		}else{
	  		return false;
	  	}
	  	return true;
	}
	
	
	
	function viewOfferTemplate(reqsCode, candCode, templateCode){
	//alert("reqsCode "+reqsCode+" candCode "+candCode+" templateCode "+templateCode);
		//document.getElementById('paraFrm').target = "_blank";
		document.getElementById("paraFrm").action = 'OfferDetails_previewoffer.action?requisitionCode='+reqsCode+'&candidateCode='+candCode+'&templateCode='+templateCode; 
	  	document.getElementById("paraFrm").submit();
	  	//document.getElementById('paraFrm').target = "main";
	}
	
	function viewRequisition(reqCode, type){
		var status = "";
		var action = "CreateOffer_input.action";
		
		if(type == "offer"){
			status = document.getElementById("paraFrm_offerStatus").value;
			action = "CreateOffer_showOfferList.action";
		}
		else {
			status = document.getElementById("paraFrm_appointmentStatus").value;
			action = "CreateOffer_showAppointmentList.action";
		}
		
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction='+action+'&statusKey='+status;;
	    document.getElementById("paraFrm").submit();
	}
	
	function viewInterviewDetails(reqCode, candCode){
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+reqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
	
	function createNewOffer(){
		document.getElementById("paraFrm_buttonName").value = "";
	}
	
	function viewCV(fileName){
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CreateOffer_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	

// Created by Guru Prasad  	
 

  function callShowFilter(){
  			document.getElementById("showFilter").style.display='none';//button ShowFilter
  			document.getElementById("hideFilter").style.display='';//button HideFilter
  			document.getElementById("showFilterToApp").style.display='';//to appear all fields for enter data
  			document.getElementById('enableFilterValue').style.display='none';
 }

 function callHideFilter(){
 calReset();
  			document.getElementById("showFilter").style.display='';//button ShowFilter 			
  			document.getElementById("hideFilter").style.display='none';//button HideFilter
  			document.getElementById("showFilterToApp").style.display='none';//to appear all fields for enter data
  			//document.getElementById("appliedFilterValue").style.display='none';//after apply filter that is in view mode
 } 
 
 callFilter();
	function callFilter(){
		    var chkSearch=document.getElementById('paraFrm_chkSerch').value;
	if(chkSearch=="")
	        {  
	        	document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('showFilterToApp').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	      }
	else
	      {
	      		document.getElementById('showFilterToApp').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
	        }
	    }
 
  	
 function calReset(){
   var status=document.getElementById('paraFrm_offerStatus').value;
   if(status=="I"){	
	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequiredbyDate').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_offFrmDate').value="";
	   document.getElementById('paraFrm_offToDate').value="";
   }else if(status=="OA"){
	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequiredbyDate').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_offFrmDate').value="";
	   document.getElementById('paraFrm_offToDate').value="";
	   document.getElementById('paraFrm_offAccFrmDate').value="";
   	   document.getElementById('paraFrm_offAccToDate').value="";	   	
   }else if(status=="S"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequiredbyDate').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_offFrmDate').value="";
	   document.getElementById('paraFrm_offToDate').value="";
	   document.getElementById('paraFrm_offRejFrmDate').value="";
   	   document.getElementById('paraFrm_offRejToDate').value="";
   }else if(status=="C"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequiredbyDate').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_offFrmDate').value="";
	   document.getElementById('paraFrm_offToDate').value="";
	   document.getElementById('paraFrm_offCanFrmDate').value="";
	   document.getElementById('paraFrm_offCanToDate').value=""; 	
   }else if(status=="D" || status=="R" || status=="A" || status=="P"){
   	   document.getElementById('paraFrm_searchRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequisitionCode').value="";
	   document.getElementById('paraFrm_searchHidRequiredbyDate').value="";
	   document.getElementById('paraFrm_searchCandName').value="";
	   document.getElementById('paraFrm_searchCandCode').value="";
	   document.getElementById('paraFrm_searchPosition').value="";
	   document.getElementById('paraFrm_searchPositionId').value="";
	   document.getElementById('paraFrm_searchHiringMgr').value="";
	   document.getElementById('paraFrm_searchHiringMgrId').value="";
	   document.getElementById('paraFrm_searchDueSinceDays').value="";
   }
}
function applyFilter(){
var status=document.getElementById('paraFrm_offerStatus').value;
//alert("status ----------> "+status);
if(status=="I"){
	var fromDate=document.getElementById('paraFrm_offFrmDate').value;
	var toDate=document.getElementById('paraFrm_offToDate').value;
	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_offFrmDate','offer.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_offToDate','offer.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_offToDate', 'offer.frdate','offer.todate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="OA"){
	var fromDate=document.getElementById('paraFrm_offFrmDate').value;
	var toDate=document.getElementById('paraFrm_offToDate').value;
	var accFromDate=document.getElementById('paraFrm_offAccFrmDate').value;
   	var accToDate=document.getElementById('paraFrm_offAccToDate').value;
   	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
   	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_offFrmDate','offer.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_offToDate','offer.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_offToDate', 'offer.frdate','offer.todate'))
	  return false;
	}
	if(accFromDate!="")
	{
	  if(!validateDate('paraFrm_offAccFrmDate','offer.accFrdate'))
	  return false; 
	}
	if(accToDate!="")
	{
	  if(!validateDate('paraFrm_offAccToDate','offer.accTodate'))
	  return false;
	}
	if(accFromDate!="" && accToDate!="")
	{
	  if(!dateDifferenceEqual(accFromDate,accToDate,'paraFrm_offAccToDate', 'offer.accFrdate','offer.accTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" &&accFromDate == "" && accToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="S"){
	var fromDate=document.getElementById('paraFrm_offFrmDate').value;
	var toDate=document.getElementById('paraFrm_offToDate').value;
	var rejFromDate=document.getElementById('paraFrm_offRejFrmDate').value;
   	var rejToDate=document.getElementById('paraFrm_offRejToDate').value;
   	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
   	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_offFrmDate','offer.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_offToDate','offer.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_offToDate', 'offer.frdate','offer.todate'))
	  return false;
	}
	if(rejFromDate!="")
	{
	  if(!validateDate('paraFrm_offRejFrmDate','offer.RejFrdate'))
	  return false; 
	}
	if(rejToDate!="")
	{
	  if(!validateDate('paraFrm_offRejToDate','offer.RejTodate'))
	  return false;
	}
	if(rejFromDate!="" && rejToDate!="")
	{
	  if(!dateDifferenceEqual(rejFromDate,rejToDate,'paraFrm_offRejToDate', 'offer.RejFrdate','offer.RejTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" &&rejFromDate == "" && rejToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="C"){
	var fromDate=document.getElementById('paraFrm_offFrmDate').value;
	var toDate=document.getElementById('paraFrm_offToDate').value;
	var canFromDate=document.getElementById('paraFrm_offCanFrmDate').value;
	var canToDate=document.getElementById('paraFrm_offCanToDate').value;
	var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
	var candName=document.getElementById('paraFrm_searchCandName').value;
	var position=document.getElementById('paraFrm_searchPosition').value;
	var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value; 
	if(fromDate!="")
	{
	  if(!validateDate('paraFrm_offFrmDate','offer.frdate'))
	  return false; 
	}
	if(toDate!="")
	{
	  if(!validateDate('paraFrm_offToDate','offer.todate'))
	  return false;
	}
	if(fromDate!="" && toDate!="")
	{
	  if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_offToDate', 'offer.frdate','offer.todate'))
	  return false;
	}
	if(canFromDate!="")
	{
	  if(!validateDate('paraFrm_offCanFrmDate','offer.canFrdate'))
	  return false; 
	}
	if(canToDate!="")
	{
	  if(!validateDate('paraFrm_offCanToDate','offer.canTodate'))
	  return false;
	}
	if(canFromDate!="" && canToDate!="")
	{
	  if(!dateDifferenceEqual(canFromDate,canToDate,'paraFrm_offCanToDate', 'offer.canFrdate','offer.canTodate'))
	  return false;
	}
	if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && fromDate == "" && toDate == "" &&canFromDate == "" && canToDate == ""){
		alert("Please enter/select atleast one field to apply filter");
		return false;
	}
	document.getElementById('showFilterToApp').style.display='';
}else if(status=="D" || status=="P" || status=="A" || status=="R"){
   var reqCode=document.getElementById('paraFrm_searchRequisitionCode').value;
   var candName=document.getElementById('paraFrm_searchCandName').value;
   var position=document.getElementById('paraFrm_searchPosition').value;
   var hiringMgr=document.getElementById('paraFrm_searchHiringMgr').value;
   var days=document.getElementById('paraFrm_searchDueSinceDays').value;
   if(reqCode == "" && candName == "" && position == "" && hiringMgr == "" && days == ""){
   	   alert("Please enter/select atleast one field to apply filter");
		return false;
   }
   document.getElementById('showFilterToApp').style.display='';
}   	
   document.getElementById('showFilterToApp').style.display='none';
   document.getElementById('hideFilter').style.display='none';
   document.getElementById('showFilter').style.display='none';
   document.getElementById("paraFrm_searchFlag").value="true";
   document.getElementById('paraFrm').action='CreateOffer_getOfferDetailsOnSearch.action';
   document.getElementById('paraFrm').submit();
}	

function callClear(){
document.getElementById("hideFilter").style.display='none';
document.getElementById("showFilterToApp").style.display='none';
document.getElementById("paraFrm_searchFlag").value='false';
document.getElementById("paraFrm").action="CreateOffer_reset.action";
document.getElementById("paraFrm").submit();
document.getElementById("showFilter").style.display='';
}


//Modified Paging by Guru Prasad


function callPageText(id,status1){  
	   
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
		 	var actPage = document.getElementById('paraFrm_myPageOffer').value;   
	     
		 	 
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
	        
	         document.getElementById('paraFrm_myPageOffer').value=pageNo;
		   
			document.getElementById('paraFrm').action='CreateOffer_showPageList.action?status='+status1;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	
	
 function callPage(id,pageImg,status1){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
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
		
		if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("enableFilterValue").style.display='';
		 }
		document.getElementById('paraFrm_myPageOffer').value=id;
		document.getElementById('paraFrm').action='CreateOffer_showPageList.action?status='+status1;
		document.getElementById('paraFrm').submit(); 
	} 
	
	function viewOfferDetails(offerCode,reqCode){
		var abc = true;
		document.getElementById("paraFrm").action='OfferDetails_viewOfferDetails.action?offerCode='+offerCode+'&reqsCode='+reqCode+'&doubleClickEditFlag='+abc;
	    document.getElementById("paraFrm").submit();
	}
	
	function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}

</script>


