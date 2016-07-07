<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/common/js/leaveAjax.js"></script>
<style>
.myText {
	 border: 0px;
}
</style>
<div align="center" id="overlay"
	style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :                     DXImageTransform .                     Microsoft .                     alpha(opacity =                     15); -moz-opacity: .1; opacity: .1;">
</div>
<s:form action="CashVoucher" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<s:hidden name="draftFlag" ></s:hidden>
			<s:hidden name="sendfrAppFlag" ></s:hidden>
			<s:hidden name="viewAppFlag" ></s:hidden>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash Voucher  
					Application Form</strong>
					</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<s:if test="saveDetailFlag">
				<td width="100%" colspan="3">
				<s:if test="appRejSendBackFlag">
				<input type="button" class="token"
					value="    Approve" onclick="return checkAppStatus(this,'A');"
					theme="simple" /> <input type="button" class="token"
					value="    Reject" onclick="return checkAppStatus(this,'R');"
					theme="simple" /> <input type="button" class="token"
					value="    Send Back" onclick="return checkAppStatus(this,'B')"
					theme="simple" /> 
					</s:if>
					<s:if test="approveAppCanFlag">
					
					<input type="button" class="token"
					value="    Approve Cancellation " onclick="return checkApproveCancellationStatus(this,'X');"
					theme="simple" />
					<input type="button" class="token"
					value="    Reject Cancellation " onclick="return checkApproveCancellationStatus(this,'Z');"
					theme="simple" />
					</s:if>
					<s:if test="leaveApplication.isAdminApprovalClick">
					<input type="button" class="token"
					value="    Back To List" onclick="return callBackAdmin();" theme="simple" />
					</s:if>
					<s:else>
						<input type="button" class="token"
					value="    Back To List" onclick="return callBack();" theme="simple" />
					</s:else>
					
					</td>
			</s:if>
		</tr>
		<tr>
				<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- TABLE FOR APPROVER COMMENTS START-->
		<s:if test="prevAppCommentFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="30%"><label name="approverComm" id="approverComm"
						ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label>:</td>
						<td width="70%">
							<s:if test="saveDetailFlag">
								<s:textarea theme="simple" cols="70" rows="3" name="approverComments" />
							</s:if>
							<s:else>
								<s:property value="approverComments"/>
							</s:else>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- TABLE FOR APPROVER COMMENTS ENDS-->
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

<s:if test="prevAppCommentListFlag">

			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td width="100%" colspan="7">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable">
									<tr>
										<td width="100%" nowrap="nowrap" colspan="7"><strong>Approver Details :</strong></td>
									</tr>
									<tr>
										<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
										<td class="formth" width="15%" height="22" valign="top">ApproverID</td>
										<td class="formth" width="25%" height="22" valign="top">Approver Name</td>
										<td class="formth" width="10%" height="22" valign="top">Date </td>
										<td class="formth" width="10%" height="22" valign="top">Status</td>
										<td class="formth" width="30%" height="22" valign="top">Comments</td>	
									</tr>
									<%int i = 0;%>
									<%int k = 1;%>
						<s:iterator value="approverCommentList" status="stat">
							<tr>
								<td width="10%" class="sortableTD" align="center"><%=k%>
									<s:hidden name="appSrNo" value="%{<%=k%>}"/> 
								</td>
								<td width="15%" class="sortableTD" align="left">
									<s:property value="prevApproverID"/>
									<s:hidden name="prevApproverID"/>
								</td>
								<td width="25%" class="sortableTD" align="left">
									<s:property value="prevApproverName"/>
									<s:hidden name="prevApproverName"/>
								</td>
								<td width="10%" class="sortableTD" align="center">
									<s:property value="prevApproverDate"/>
									<s:hidden name="prevApproverDate"/>
								</td>
								<td width="10%" class="sortableTD" align="left">&nbsp;
									<s:property value="prevApproverStatus"/>
									<s:hidden name="prevApproverStatus"/>
								</td>
								<td width="30%" class="sortableTD" align="left">&nbsp;
								<s:property value="prevApproverComment"/>
								<s:hidden name="prevApproverComment"/></td>
							</tr>
							<%k++;%>
						</s:iterator>
							<%i = k;k = 0;
							%>
							</table>
								
						</td>
					</tr>
				</table>
			</td>
		</tr>
</s:if>


		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->

		<!-- EMPLOYEE DETAILS TABLE  STARTS -->

		<tr>
			<td colspan="7">
					<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
						<s:hidden  theme="simple" name="empCode" />
							<tr>
								<td colspan="5" class="formhead">
								<strong class="forminnerhead">
									<label  class = "set" name="voucher.application" id="voucher.application" ondblclick="callShowDiv(this);"><%=label.get("voucher.application")%></label>
								 </strong>
								<s:hidden name="vCode" value="%{vCode}" /></td>
								<s:hidden name="level" value="%{level}" />
							</tr>
							
							
							<tr>
								<td width="25%" colspan="1" class="formtext">
									<label  class = "set" name="vouchertype" id="vouchertype" ondblclick="callShowDiv(this);"><%=label.get("vouchertype")%></label>
									<font	color="red">*</font> :
								</td>
								
									<td width="25%" colspan="1" class="formtext">
									
									<s:if test="showFlag">
										<s:select	name="type" headerKey="" theme="simple"  headerValue="Select" cssStyle="width:110"
											list="#{'Local':'Local','Out Station':'Out Station'}" />
									</s:if>
									<s:else>
									<s:select disabled="true"	name="type" headerKey="" theme="simple"  headerValue="Select" cssStyle="width:110"
											list="#{'Local':'Local','Out Station':'Out Station'}" />
									</s:else>				
									</td>
								
								<td colspan="1" width="25%"></td>
								<td colspan="1" width="25%"></td>
							</tr>
							
							
							<tr>
								<s:if test="%{generalFlag}">
									<td width="25%" colspan="1" class="formtext">
										<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font
											color="red">*</font> :
										 
									</td>
									<td width="75%" colspan="3">
										<s:textfield name="empToken" size="10" value="%{empToken}" theme="simple" readonly="true" />
										<s:textfield name="ename" size="50" value="%{ename}" theme="simple" readonly="true" />
									</td>
								</s:if>
								<s:else>
									<td width="25%" colspan="1" class="formtext">
										<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font
												color="red">*</font> :
										 
									</td>
									<td width="75%" colspan="3">
										<s:textfield name="empToken" size="10" value="%{empToken}" theme="simple" readonly="true" />
										<s:textfield name="ename" size="50" value="%{ename}" theme="simple"readonly="true" /> 
										<s:if test="editFlag1">
									    </s:if>
									    <s:else>
									    		<img src="../pages/images/recruitment/search2.gif" height="16"
												 align="absmiddle" width="16" onclick="return selectEmployee();">
									    </s:else>
									</td>
								</s:else>
							</tr>
							
							
							<tr>
								<td width="25%" class="formtext">
									<label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
							 	</td>
								<td width="25%">
									<s:textfield name="department" value="%{department}" theme="simple" size="25" readonly="true" />
								</td>
								<td colspan="1" width="25%"></td>
								<td colspan="1" width="25%"></td>
							</tr>
							
							
			
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
								<td width="25%">
									<s:textfield name="designation" value="%{designation}" theme="simple" size="25" readonly="true" />
								</td>
								<td width="25%" class="formtext"><label  class = "set" name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
								<td width="25%">
									<s:textfield name="grade" value="%{grade}"
									theme="simple" size="25" readonly="true" />
								</td>
							</tr>
							
							
			
							<tr>
								<td width="25%" class="formtext"><label  class = "set" name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label> <font color="red">*</font>:</td>
								<td width="25%">
									<s:textfield name="vchDate" size="25" onkeypress="return numbersWithHiphen();" 
												 theme="simple" value="%{vchDate}" maxlength="10" />
								</td>
								<td width="25%" class="formtext"><label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
								<td width="25%">
									<s:hidden name="hiddenStatus" value="%{hiddenStatus}" /> 
									<s:select theme="simple" name="status" disabled="true" cssStyle="width:130"
											  list="#{'D':'Draft','P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded','C':'Canceled','B':'Sent Back'}" />
								</td>
							</tr>
					</table>
			</td>
		</tr>
		<!-- EMPLOYEE DETAILS TABLE  ENDS -->

		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap">
						<strong>The Approver(s) for this application :</strong>
					</td>
					<td colspan="2" nowrap="nowrap"></td>
					<td width="11%" nowrap="nowrap">
						<strong>Keep Informed To: </strong>
					</td>
					<td width="13%" align="left">
						<s:if test="voucherDetailsFlag">
							<s:hidden name="kiEmpCode" />
							<s:hidden name="kiEmpToken" />
							<s:textfield name="kiEmpName" readonly="true" />
						</s:if>
					</td>
					<td width="5%" colspan="1">
						<s:if test="voucherDetailsFlag">
							<img src="../pages/common/css/default/images/search2.gif"
								 class="iconImage" width="16" height="15"
								 onclick="javascript:getKeepInformedEmp();" />
						</s:if>
					</td>
					<td width="15%">
						<s:if test="voucherDetailsFlag">
							<s:submit name="" value=" Add" cssClass="add"
									  action="CashVoucher_addKeepInformedEmpList"
									  onclick="return callKeepInformed();" />
						</s:if>
					</td>
				</tr>
				
				
				
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="approverList">
								<tr>
									<td align="left">
										<s:hidden name="approverName" />
										<STRONG>
											<s:property value="srNoIterator" />
										</STRONG> 
										<s:property value="approverName" />
									</td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>

				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%int counter11 = 0; int counter2 = 0;%>
						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="80%"><%=++counter11%>
									<s:hidden name="serialNo" />
									<s:hidden name="keepInformedEmpCode" />
									<s:hidden name="keepInformedEmpId" />
									<s:hidden name="keepInformedEmpName" />
									<s:property value="keepInformedEmpName" />
									
								</td>
								<td width="20%">
									<s:if test="voucherDetailsFlag">
										<a href="#"	onclick="callForRemove(<%=counter11%>,'<s:property value="keepInformedEmpCode"/>');">Remove
									    </a>
									</s:if>
								</td>
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- KEEP INFORMED LIST TABLE  ENDS -->

		
		<!--  Voucher Details List-->
		
				
		<s:if test="voucherDetailsFlag">
		<tr>
			<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
							<tr>
								<td colspan="4" class="formhead"><strong class="forminnerhead"><label  class = "set" name="voucherDtl" id="voucherDtl" ondblclick="callShowDiv(this);"><%=label.get("voucherDtl")%></label>  </strong></td>
							</tr>
							<tr>
								<td width="25%" colspan="1" class="formtext">
									<label  class = "set" name="Voucher.Head" id="Voucher.Head" ondblclick="callShowDiv(this);"><%=label.get("Voucher.Head")%></label> <font
									color="red">*</font> :
									<s:hidden name="vchHeadCode" value="%{vchHeadCode}" />
								</td>
								<td width="75%" colspan="3">
									<s:textfield size="25" name="VoucherHeadName" theme="simple" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
										 width="16" onclick="return callHead();">
								</td>
							</tr>
						
						
						
							<tr>
								<td width="25%" colspan="1" class="formtext">
									<label  class = "set" name="amount" id="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label> <font color="red">*</font> :
								</td>
								<td width="25%" colspan="1">
									<s:textfield name="vamount" theme="simple" size="25" 
												 onkeypress="return numbersWithDot();"	maxlength="25" />
								</td>
								<td width="25%" colspan="1" class="formtext">
									<label  class = "set" name="proofattached" id="proofattached" ondblclick="callShowDiv(this);"><%=label.get("proofattached")%></label>
								:</td>
								<td width="25%" colspan="1">
									<s:select theme="simple" name="hproof" cssStyle="width:75" list="#{'N':'No','Y':'Yes'}" />
								</td>
						    </tr>
							<tr>
								<td width="25%" colspan="1" class="formtext"><label  class = "set" name="partculars" id="partculars" ondblclick="callShowDiv(this);"><%=label.get("partculars")%></label>:</td>
								<td width="25%" colspan="1">
									<s:textarea cols="25" theme="simple" name="vremark" rows="2" />
								</td>
								<td width="25%" colspan="1" class="formtext">
									<label  class = "set" name="uploadfile" id="uploadfile" ondblclick="callShowDiv(this);"><%=label.get("uploadfile")%></label> :
								</td>
								<td width="25%" colspan="1" >
									<s:textfield name="uploadFileName" theme="simple" size="25" readonly="true" />&nbsp;
									<input type="button" class="token" name="browse" value="Browse"
										onclick="upload_File('uploadFileName');" />
								</td>
			
							</tr>
							<tr>
								<td width="25%" colspan="1">&nbsp;</td>
								<td colspan="3" width="75%"><s:submit cssClass="add"
									action="CashVoucher_addItem" theme="simple" value="Add"
									onclick="return adding();" /></td>
							</tr>
							
				</table>
			</td>
		</tr>		
	</s:if>
	
	<tr>
			<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
						<tr>
							<s:hidden name="tableLength" value="%{tableLength}" />
							<td width="5%" class="formth"><label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>.</td>
							<td width="10%" class="formth"><label  class = "set" name="Voucher.Head" id="Voucher.Head1" ondblclick="callShowDiv(this);"><%=label.get("Voucher.Head")%></label></td>
							<td width="10%" class="formth"><label  class = "set" name="amount" id="amount1" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label>(Rs.)</td>
							<td width="20%" class="formth"><label  class = "set" name="partculars" id="partculars2" ondblclick="callShowDiv(this);"><%=label.get("partculars")%></label></td>
							<td width="13%" class="formth"><label  class = "set" name="proofattached" id="proofattached1" ondblclick="callShowDiv(this);"><%=label.get("proofattached")%></label> ?</td>
							<td width="15%" class="formth"><label  class = "set" name="uploadfile" id="uploadfile1" ondblclick="callShowDiv(this);"><%=label.get("uploadfile")%></label></td>
						<s:if test="editFlag1">
							<td width="25%" class="formth"><label  class = "set" name="show" id=show" ondblclick="callShowDiv(this);"><%=label.get("show")%></label></td>
						</s:if>
						<s:else>
								<td width="25%" class="formth"><label  class = "set" name="edit/delete/show" id=edit/delete/show" ondblclick="callShowDiv(this);"><%=label.get("edit/delete/show")%></label></td>
						</s:else>	
						</tr>
						<% int cnt = 0;%>
						<s:if test="editFlag1">
							<s:iterator value="list">
								<tr>
									<td class="sortableTD" width="5%" align="center">
										<s:property value="srNo" />&nbsp;
										<s:hidden name="srNo"></s:hidden>
									</td>
									<td class="sortableTD" width="10%" nowrap="nowrap">
										<s:property value="VoucherHead" />&nbsp;
										<s:hidden name="VoucherHead"></s:hidden> 
										<s:hidden name="vchCode"></s:hidden>
									</td>
									<td class="sortableTD" width="10%" align="center">
										<s:property value="vamt" />&nbsp; 
										<s:hidden name="vamt"></s:hidden>
									</td>
									<td class="sortableTD" width="20%" align="left">
										<s:property 	value="vrem" />&nbsp;
										<s:hidden name="vrem"></s:hidden>
									</td>
									<td class="sortableTD" width="13%" align="left">
										<s:property	value="vproof" />&nbsp;
										<s:hidden name="vproof"></s:hidden>
									</td>
									<td class="sortableTD" width="15%" align="left" >
										<s:property value="uploadFile" />&nbsp;
										<s:hidden name="uploadFile"></s:hidden>
									</td>
									<td align="center" width="25%" class="td_bottom_border" >
									 	<input type="button" class="token"	value="   Show"
											onclick="return showRecord('<s:property value="uploadFile" />');" />
									</td>	
									
								</tr>
							
								<% cnt++; %>
							</s:iterator>
					 </s:if>
					 <s:else>
					 	<s:iterator value="list">
						
								<tr>
									<td class="sortableTD" width="5%" align="center">
										<s:property value="srNo" />&nbsp;
										<s:hidden name="srNo"></s:hidden>
									</td>
									<td class="sortableTD" width="10%" >
										<s:property value="VoucherHead" />&nbsp;
										<s:hidden name="VoucherHead"></s:hidden> 
										<s:hidden name="vchCode"></s:hidden>
									</td>
									<td class="sortableTD" width="10%" align="center">
										<s:property value="vamt" />&nbsp; 
										<s:hidden name="vamt"></s:hidden>
									</td>
									<td class="sortableTD" width="20%" align="left">
										<s:property 	value="vrem" />&nbsp;
										<s:hidden name="vrem"></s:hidden>
									</td>
									<td class="sortableTD" width="13%" align="left">
										<s:property	value="vproof" />&nbsp;
										<s:hidden name="vproof"></s:hidden>
									</td>
									<td class="sortableTD" width="15%" align="left" >
										<s:property value="uploadFile" />&nbsp;
										<s:hidden name="uploadFile"></s:hidden>
									</td>
									
										<td class="sortableTD" width="15%" align="center" >
											<input type="button" class="rowEdit" title="Edit Record"   onclick="callForEdit(<s:property value="vchCode"/>,
																									  '<s:property value="VoucherHead"/>',
																									  <s:property value="vamt"/>,
																									  '<s:property value="vrem" />',
																									  <s:property value="srNo"/>,
																									  '<s:property value="vproof" />',
												 													  '<s:property value="uploadFile"/>')" />
											 <input type="button" class="rowDelete" title="Delete Record"  onclick="callForDelete(<s:property value="srNo"/>,
											 																						<s:property value="vamt"/>)"/>
											 <input type="button" class="token"	value="   Show"
													onclick="return showRecord('<s:property value="uploadFile" />');" />
										</td>
									
								</tr>
							
								<% cnt++; %>
							</s:iterator>
					 </s:else>
				</table>
			</td>
	</tr>			
	<tr>
			<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">
						<tr>
							<td colspan="1" width="25%" class="formtext"><label  class = "set" name="Total.Amount" id="Total.Amount" ondblclick="callShowDiv(this);"><%=label.get("Total.Amount")%></label>:</td>
							<td width="75%" colspan="3"><s:hidden name="totalCheck" /><s:textfield
								size="15" theme="simple" name="totalamount" readonly="true" /></td>
						</tr>
						<tr>
							<td colspan="1" width="25%" class="formtext"><label  class = "set" name="vch.details" id="vch.details" ondblclick="callShowDiv(this);"><%=label.get("vch.details")%></label> :</td>
							<td colspan="3" width="75%">
								<s:textarea name="details" cols="100" rows="3"></s:textarea>
							</td>
						</tr>
				</table>
			</td>
    </tr>
	<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>
<s:hidden name="checkRemove" />
<s:hidden name="checkEdit" />
<s:hidden name="checkStatus" />
<s:hidden name="source" />
</table>
</s:form>		
<script type="text/javascript">

function adding()
  {
  	  
	  var amount=document.getElementById('paraFrm_vamount').value;
	  var amnt=document.getElementById('amount').innerHTML.toLowerCase();
	  var remark=document.getElementById('paraFrm_vremark').value;
	  var hprf=document.getElementById('paraFrm_hproof').value;
	  var upFileName=document.getElementById('paraFrm_uploadFileName').value;
	  if(hprf=='Y'){
	  	if(upFileName==""){	
	  		alert("Please upload file");
	  		return false;
	  	}	
	  	
	  }else{
	  	if(upFileName!=""){
	  		alert("Please change the proof attached field");
	  		return false;
	  	}	
	  }
	  var f9Fields=["paraFrm_vremark"];
	  if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			alert("You can't update the application.");
			return false;
	  }
	  var voucherhead=document.getElementById('Voucher.Head').innerHTML.toLowerCase();
	  var voucher=document.getElementById('paraFrm_vchHeadCode').value;
	  if(voucher==""){
		 alert("Please select "+voucherhead);
		 return false;
	  }
	  if(amount==""){
		   alert("Please enter "+amnt);
		   document.getElementById('paraFrm_vamount').focus();
		   return false;
	  }else if(isNaN(amount)) { 
		  	alert("Only one decimal point is allowed in "+amnt+" field.");
			document.getElementById('paraFrm_vamount').focus();
			return false;
	  }else if(remark!=""){
		if(!f9specialchars(f9Fields))
			return false;
	  }
	  if(amount=="0" || amount=="0." ) {
	  		alert("Amount should be great than 0 ");
			document.getElementById('paraFrm_vamount').focus();
			return false;
	  }
	  document.getElementById('paraFrm_status').value=document.getElementById('paraFrm_hiddenStatus').value;
	  return true;
 }
 
 
    var fieldName = ["paraFrm_type", "paraFrm_ename", "paraFrm_vchDate"];
	var lableName = ["vouchertype","employee","date"];
	var types = ['select','select','enter'];

						

 function calladd(obj)
 {
	if(!(document.getElementById('paraFrm_vCode').value=="")){
	  alert("Please click on Update button to update the record.");
	  return false;
	  }else {
	 if(!validateBlank(fieldName, lableName,types))
	   return false;
	
	 if(document.getElementById('paraFrm_tableLength').value==""){
		alert("Please enter "+document.getElementById('voucherDtl').innerHTML.toLowerCase());
		return false;
		}
	obj.disabled = true;
	document.getElementById("paraFrm").action="CashVoucher_save.action";
	document.getElementById("paraFrm").submit();
	}
 }

 function callHead(){
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
				alert("You can't update the application !");
				return false;
				}else
		callsF9(500,325,'CashVoucher_f9actionhd.action');
 }
 function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			if(document.getElementById('paraFrm_vCode').value=="")
				document.getElementById("paraFrm_vchDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}

function upload_File(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/voucher";
		//window.open('../pages/common/uploadFile.jsp?path='+path,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 //document.getElementById('paraFrm').target="main";
} 
	
function showRecord(fieldName){
	if(fieldName == ""){
		alert('File not available');
		return false;
	}
	var path='<%=session.getAttribute("session_pool")%>';
	window.open('../pages/images/'+path+'/voucher/'+fieldName);
}
function selectEmployee(){
	if(document.getElementById('paraFrm_status').value=='P'){
		alert("You can't update the application.");
		return false;
	}else 
		callsF9(500,325,'CashVoucher_f9actionEname.action');
}

/*function draftFun()
 {
 	document.getElementById('paraFrm_draftFlag').value="true";
 	document.getElementById('paraFrm_sendfrAppFlag').value="false";
 	var voucherhead=document.getElementById('Voucher.Head').innerHTML.toLowerCase();
	var voucher=document.getElementById('paraFrm_vchHeadCode').value;
	var amount=document.getElementById('paraFrm_vamount').value;
    var amnt=document.getElementById('amount').innerHTML.toLowerCase();
	if(document.getElementById('paraFrm_type').value==""){
		alert("Please select voucher type"); 	
	}else{
		if(document.getElementById('paraFrm_tableLength').value==""){
			if(voucher==""){
				 alert("Please select "+voucherhead);
				 return false;
			}else if(amount==""){
			   alert("Please enter "+amnt);
			   document.getElementById('paraFrm_vamount').focus();
			   return false;
			}else if(isNaN(amount)) { 
			  	alert("Only numbers are allowed in "+amnt+" field.");
				document.getElementById('paraFrm_vamount').focus();
				return false;
			}else{
			  alert("Please add at least one voucher details");
			}
		}else{
			var conf=confirm("Do you really want to draft the application ?");
			if(conf){
				document.getElementById("paraFrm_checkStatus").value="D";
	   			document.getElementById("paraFrm").action="CashVoucher_save.action";
				document.getElementById("paraFrm").submit();
			}	
		}
	}	
 }*/
 function resetFun(){
 	 document.getElementById("paraFrm").action="CashVoucher_reset.action";
	 document.getElementById("paraFrm").submit();
 }
 function backFun(){
 	 document.getElementById("paraFrm").action="CashVoucher_back.action";
	 document.getElementById("paraFrm").submit();
 }
 function reportFun(){
 	
 	 var vcode=document.getElementById('paraFrm_vCode').value;
 	 document.getElementById("paraFrm").action='CashVoucher_report.action?vCode='+vcode;
	 document.getElementById("paraFrm").submit();
 }


function sendforapprovalFun()
{
	document.getElementById('paraFrm_draftFlag').value="false";
	document.getElementById('paraFrm_sendfrAppFlag').value="true";
	var voucherhead=document.getElementById('Voucher.Head').innerHTML.toLowerCase();
	var voucher=document.getElementById('paraFrm_vchHeadCode').value;
	var amount=document.getElementById('paraFrm_vamount').value;
    var amnt=document.getElementById('amount').innerHTML.toLowerCase();
	if(document.getElementById('paraFrm_type').value==""){
		alert("Please select voucher type"); 	
	}else{
			if(document.getElementById('paraFrm_tableLength').value == ""){
				if(voucher==""){
					 alert("Please select "+voucherhead);
					 return false;
				}else if(amount==""){
				   alert("Please enter "+amnt);
				   document.getElementById('paraFrm_vamount').focus();
				   return false;
				}else if(isNaN(amount)) { 
				  	alert("Only numbers are allowed in "+amnt+" field.");
					document.getElementById('paraFrm_vamount').focus();
					return false;
				}else{
				  alert("Please add atleast one  voucher details");
				}
			}else{
				var conf=confirm("Do you really want to send for approval ?");
				if(conf){
					document.getElementById("paraFrm_checkStatus").value="P";
				 	document.getElementById("paraFrm").action="CashVoucher_save.action";
				 	document.getElementById("paraFrm").submit();
				} 				
		   }
	}	
}

function callKeepInformed()
	{
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can't add keep informed to for the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='D'){
				msg+=" Draft";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}
		 var empcode=document.getElementById('paraFrm_empCode').value;
		 var emp =document.getElementById('paraFrm_kiEmpName').value;
		 if(empcode==""){
			 alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
			 return false;
		 }
		 if(emp==""){
			 alert("Please select Keep Informed To ");
			 return false;
		 }
		return true;
	}
	function getKeepInformedEmp()
	{
		try
		{ 
			var empcode=document.getElementById('paraFrm_empCode').value;
			if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
			}else{
					callsF9(500,325,'CashVoucher_f9KeepInformedEmployee.action');
			}
		}catch(e){alert(e);} 
	}
	function callForRemove(id,empCode)
	{
	   	if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
				var msg="You can't remove the";
				var st=document.getElementById('paraFrm_status').value;
				if(st=='N'){
					msg+=" Cancelled";
				}
				if(st=='P'){
					msg+=" Submited";
				}
				if(st=='R'){
					msg+=" Rejected";
				}
				if(st=='A'){
					msg+=" Approved ";
				}
				if(st=='F'){
					msg+=" Forwarded ";
				}
				alert(  msg+" application !");
				return false;
		}else
	    {
	    	document.getElementById('paraFrm_checkRemove').value=id;
		    var conf=confirm("Are you sure !\n You want to Remove this record ?");
	  		if(conf){
				
				document.getElementById('paraFrm').target="_self";
				document.getElementById("paraFrm").action='CashVoucher_removeKeepInformed.action?kiInfrEmployeeCode='+empCode;
				document.getElementById("paraFrm").submit();
			}	
			else
			{
				return false;
			}
		}//outer else close
		return true;			

}


function callForEdit(id,vname,amt,rmrk,edit,proof, uploadFile){
		var stat=document.getElementById('paraFrm_status').value;
		if(stat=="A"||stat=="R"||stat=="P"||stat=="F")
		{
			alert("You can't edit the voucher details.");
		}else{
			if(proof=="No"){
				proof="N";
			}else {
				proof="Y";
			}
		  	document.getElementById("paraFrm_vchHeadCode").value=id;
		  	document.getElementById("paraFrm_totalCheck").value=amt;
		  	document.getElementById("paraFrm_VoucherHeadName").value=vname;
		  	document.getElementById("paraFrm_vamount").value=amt;
		  	document.getElementById("paraFrm_vremark").value=rmrk;	
		  	document.getElementById("paraFrm_checkEdit").value=edit;
		  	document.getElementById("paraFrm_hproof").value=proof;
		  	document.getElementById("paraFrm_uploadFileName").value=uploadFile;	
		  	//document.getElementById("paraFrm").action="CashVoucher_editVoucherDetails.action";
		   	//document.getElementById("paraFrm").submit();
	  }
}
  
  
function callForDelete(id,amt){
	var stat=document.getElementById('paraFrm_status').value;
	if(stat=="A"||stat=="R"||stat=="P"||stat=="F"){
			alert("You can't delete the voucher details.");
	}else{
		var conf=confirm("Do you really want to remove this record ?");
  		if(conf) {
			document.getElementById('paraFrm_checkEdit').value=id;
			document.getElementById("paraFrm_totalCheck").value=amt;
			document.getElementById("paraFrm").action="CashVoucher_remove.action";
		   	document.getElementById("paraFrm").submit();
  		}
	}	
} 		
function deleteFun(){
	var conf=confirm("Do you really want to delete this record ?");
  	if(conf) {
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="CashVoucher_deleteVoucherDetails.action";
		document.getElementById("paraFrm").submit();
	}
}
	    
autoDate();
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

