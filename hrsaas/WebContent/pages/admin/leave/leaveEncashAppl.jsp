<%@ taglib prefix="s" uri="/struts-tags"%>
 
 <%@include file="/pages/common/labelManagement.jsp" %>
 
<s:form action="LeaveEncashment" method="post" id="paraFrm"
	name="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave Encashment Application Form</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="leaveEn.enCode" />
		<s:hidden name="leaveEn.gender" />
		<s:hidden name="approverCode" />
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
					<tr>
						<td width="78%">
							<s:if test="status=='PD'||approveStatusFlag=='PB'">
							
							<input type="button" class="token" value=" Draft"
								onclick="return callAdd();" />
								
							<!--<s:submit cssClass="save" action="LeaveEncashment_save" theme="simple" value=" Draft "
								onclick="return callAdd();"  /> 
								
								
							<s:submit cssClass="add" action="LeaveEncashment_forwardToApprover" 
									value=" Send for Approval" onclick=" return forwardForm();" />
							-->
							<input type="button" class="token" value=" Send for Approval"
								onclick="return forwardForm();" />
							
							
							<s:submit cssClass="reset"	action="LeaveEncashment_reset" value=" Reset" /> 
								
							<!--<input type="button" class="token" value=" Back"
								onclick="callBack()" /> 
								-->
							</s:if>
								<input type="button" class="token" value=" Back"
								onclick="callBack()" /> 
						</td>
						<td width="22%">
							<div align="right">
								<span class="style2"><font color="red">*</font></span> Indicates Required
							</div>
						</td>
					</tr>
				
			</table>

			</td>
		</tr>
	
			<s:if test="approveStatusFlag=='PA'">
				
				<tr>
					<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
								<tr>
									<td colspan="4" class="text_head">
										<strong class="forminnerhead">Payment Details : </strong>
									</td>  
								</tr>
								<tr>
									<td colspan="4" class="text_head">
										<s:checkbox name="salaryCheck" disabled="true" />
										<label name="includeSal" id="includeSal" ondblclick="callShowDiv(this);">
											<%=label.get("includeSal")%>
										</label>
									</td>
								</tr>
								<tr>
									<td width="20%">
										<label name="sal.month" id="sal.month" ondblclick="callShowDiv(this);"><%=label.get("sal.month")%></label>
										:
									</td>
									<td width="">
										<s:select name="salarymonth" headerKey="0" disabled="true"
											headerValue="--Select--" cssStyle="width:90"
											list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
									</td>
									<td width="15%">
										<label name="sal.year" id="sal.year" ondblclick="callShowDiv(this);"><%=label.get("sal.year")%></label>
										:
									</td>
									<td width="30%">
										<s:textfield name="salaryyear" size="10" disabled="true"
											cssStyle="text-align: right" maxlength="4"
											onkeypress="return numbersOnly();" />
									</td>
								</tr>
								<tr>
									<td colspan="1" class="text_head">
										<label name="leaveencashcrdcode" id="leaveencashcrdcode" ondblclick="callShowDiv(this);"><%=label.get("leaveencashcrdcode")%></label>
										:
									</td>
									<td colspan="3">
										<s:hidden name="creditCode"  /> 
										<s:textfield name="creditType" size="20" readonly="true" disabled="true" />
									</td>
								</tr>
							</table>
					</td>
				</tr>
			</s:if>
		
		
		<tr>
			<td colspan="4">
				<table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
					<tr>
						<td class="text_head">
								<strong class="forminnerhead">Employee Details</strong>
						</td>  
					</tr>
					<tr>
						<td>
							<table border="0" width="100%"> 
								<tr>
									<td width="18%" height="22" class="formtext">
										<label name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> 
											<font color="red">*</font>:
									</td>
									<td height="22" width="15%">
										<s:textfield size="20" name="leaveEn.empToken" 
											readonly="true" cssStyle="background-color: #F2F2F2;"/>
										<s:hidden name="leaveEn.empId" />
									</td>
								    <td height="22" width="53%">
								    	<s:textfield size="59" name="leaveEn.empName" 
								    		readonly="true" cssStyle="background-color: #F2F2F2;"/>
								  
								  	<s:if test="generalFlag"></s:if><s:else>
									<s:if test="statusFlag==''||approveStatusFlag=='PB'">
								<img src="../pages/common/css/default/images/search2.gif"
											width="16" height="15"  class="iconImage"
											onclick="javascript:callsF9(500,325,'LeaveEncashment_f9empaction.action');" />
									</s:if>
									<s:elseif  test="approveStatusFlag=='PA'">
									
									</s:elseif>	
										
									</s:else>	 
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" width="100%">
								<tr>
									<td width="20%" height="22" class="formtext">
										<label name="branch" id="branch" ondblclick="callShowDiv(this);">
											<%=label.get("branch")%></label> :
									</td>
									<td width="27%" height="22">
										<s:textfield size="25" name="leaveEn.centerName" value="%{centerName}" 
											readonly="true" cssStyle="background-color: #F2F2F2;"/>
									</td>
									<td width="16%" class="formtext">
										<label name="designation" id="designation" ondblclick="callShowDiv(this);">
											<%=label.get("designation")%>
										</label> :
									</td>
									<td width="">
										<s:textfield size="25" name="leaveEn.rankName" value="%{rankName}" 
											readonly="true" cssStyle="background-color: #F2F2F2;"/>
									</td>
								</tr>
								
								<tr>
									<td width="20%" height="22" class="formtext">
										<label name="dept" id="dept" ondblclick="callShowDiv(this);">
											<%=label.get("dept")%></label> :
									</td>
									<td width="27%" height="22">
										<s:textfield size="25" name="leaveEn.dept" value="%{dept}" 
											readonly="true" cssStyle="background-color: #F2F2F2;"/>
									</td>
									<td width="16%"class="formtext">
										<label name="encashdate" id="encashdate" ondblclick="callShowDiv(this);">
											<%=label.get("encashdate")%>
										</label>
										:</td>
									<td>
										<s:textfield size="25" name="leaveEn.encashDate" 
											cssStyle="background-color: #F2F2F2;"/>
									</td>
								</tr>
								
								<tr>
									<td width="20%" height="22" class="formtext">
										<label name="appcode" id="appcode" ondblclick="callShowDiv(this);">
											<%=label.get("appcode")%></label>
											<font color="red">*</font> :
									</td>
									<td width="27%" height="22">
										<s:textfield size="25" name="leaveEn.appcode"  
											readonly="true" cssStyle="background-color: #F2F2F2;"/>
									</td>
									<td width="15%" height="22" class="formtext">
										<label name="stat" id="stat" ondblclick="callShowDiv(this);">
											<%=label.get("stat")%>
										</label> :
									</td>
									<td width="" height="22" colspan="1">
										<s:hidden name="hiddenStatus" /> 
										<s:select theme="simple" name="status" disabled="true" cssStyle="width:150"
											list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded','N':'Cancelled','B':'Send Back'}" />
										<s:hidden name="level" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		


		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap" colspan="3"><strong>The
					Approver(s) for this application :</strong></td>
					
					<td width="11%" nowrap="nowrap" colspan="3" ><strong>Keep Informed
					To : </strong>
					 
					<s:hidden name="keepHidden" /> <s:hidden
									name="informCode" id="paraFrm_informCode" /> <s:hidden
									name="informToken" /> <s:textfield name="informName"
									id="paraFrm_informName" size="40" readonly="true"  cssStyle="background-color: #F2F2F2;"/> 
								<s:if test="status=='PD'||approveStatusFlag=='PB'">
								<img src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'LeaveEncashment_f9informTo.action');" />
								<input type="button" value="Add" Class="add"
									onclick="return callAddKeepInfo();">
									</s:if>
									<s:elseif  test="approveStatusFlag=='PA'">
									
									</s:elseif>
					</td>
					
				</tr>
				
				
				<tr valign="top">
					<!-- APPROVER LIST  TABLE  STARTS -->
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"  id="approverListValue">
						<tr>
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

								</tr>
							</s:iterator>
						</tr>
					</table>
					</td>
					<!-- APPROVER LIST  TABLE  ENDS -->
					<!-- KEEP INFORMED LIST TABLE  STARTS -->
					<td align="left" colspan="3">
						<table width="100%" border="0" id="keepInformedTable">
							
							<%
														int counter = 1;
														%>
							<s:iterator value="keepInformedList">
								<tr>
									<td width="188px">
										<input type="hidden" name="keepInformToCode" id="paraFrm_keepHidden<%=counter%>" 
											value='<s:property value="keepInformToCode" />' />
										<%=counter%>) &nbsp;
										<s:property value="keepInformToName" /> &nbsp;
									</td>
	
									<td>
									<s:if test="statusFlag==''&& statusFlag=='PD'">
									<img src="../pages/common/css/icons/delete.gif"
										onclick="deleteCurrentRow(this);">
									</s:if>
										</td>
	
								</tr>
								<%
								counter++;
								%>
							</s:iterator>
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3"><s:if test="isFlag">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">

					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr><td colspan="9">
							<STRONG>Leave Encashment Details : </STRONG> 
							</td></tr>
							<tr>
								<td class="formth" width="5%"><label
								name="srno" id="srno"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
								<td class="formth" width="20%"><label
								name="levtype" id="levtype"
								ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label></td>
								<td class="formth" width="10%"><label
								name="ballev" id="ballev"
								ondblclick="callShowDiv(this);"><%=label.get("ballev")%></label></td>
								<td class="formth" width="10%"><label
								name="encashlim" id="encashlim"
								ondblclick="callShowDiv(this);"><%=label.get("encashlim")%></label></td>
								<td class="formth" width="10%"><label
								name="encashleavesofar" id="encashleavesofar"
								ondblclick="callShowDiv(this);"><%=label.get("encashleavesofar")%></label></td>
								<!--
								<td class="formth" width="10%"><label
								name="onholdbal" id="onholdbal"
								ondblclick="callShowDiv(this);"><%=label.get("onholdbal")%></label></td>
								-->
								<td class="formth" width="10%"><label
								name="availabellim" id="availabellim"
								ondblclick="callShowDiv(this);"><%=label.get("availabellim")%></label></td>
								<td class="formth" width="20%"><label
								name="encashed" id="encashed"
								ondblclick="callShowDiv(this);"><%=label.get("encashed")%></label></td>
								<td class="formth" width="10%"><label
								name="amt" id="amt"
								ondblclick="callShowDiv(this);"><%=label.get("amt")%></label></td>
							</tr>
								<%!int y = 0;%>

							<%
									try {

									String[][] centerChk = (String[][]) request
									.getAttribute("values");
									System.out.println("================" + centerChk.length);
									y = centerChk.length;
									if (centerChk.length > 0) {
										for (int k = 0; k < centerChk.length; k++) {
							%>
							<tr>
								<td width="5%" align="center"><%=k + 1%></td>
								<input type="hidden" name="leaveId" id="levId<%=k%>"
									value="<%= String.valueOf(centerChk[k][4]) %>" />

								<td name="leaveEn.leaveName" width="20%"><%=String.valueOf(centerChk[k][0])%></td>

								<td width="10%"><input type="text"
									name="leaveEn.clBal<%=k %>" id="clb<%=k %>"
									value="<%= String.valueOf(centerChk[k][1]) %>"
									onkeypress="return numbersonly(this);" readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/></td>

								<td width="10%"><input type="text" name="encashlimit"
									id="encashlimit<%=k %>"
									value="<%= String.valueOf(centerChk[k][8]) %>"
									readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/></td>
								
								<td width="10%"><input type="text" name="encashedleavesofar"
									id="encashedleavesofar<%=k %>"
									value="<%= String.valueOf(centerChk[k][9]) %>"
									readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/></td>
																
								<td width="10%"><input type="hidden" name="onhold"
									id="onhold<%=k %>"
									value="<%= String.valueOf(centerChk[k][7]) %>"
									readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/>
									
									<input type="text" name="availabellimit"
										id="availabellimit<%=k %>"
										value="<%= String.valueOf(centerChk[k][10]) %>"
										readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/>	
								</td>
								<td width="20%">
									
										<input type="text" name="enBal<%=k %>"
											id="enb<%=k %>" onkeypress="return numbersWithDot();"
							 				value="<%= String.valueOf(centerChk[k][3]) %>"
											onkeyup="calculate(<%=k%>),onload1();" onblur="onload1();"  maxlength="4" style="text-align: right;"/>
									
								</td>
								<td width="10%"><input type="text" name="total"
									id="total<%=k %>"
									value="<%= String.valueOf(centerChk[k][2]) %>"
									readonly="readonly" style="background-color: #F2F2F2; text-align: right;"/></td>

								<input type="hidden" name="amtPerDay"
									id="amtPerDay<%=k%>"
									value="<%= String.valueOf(centerChk[k][5]) %>" />

								<input type="hidden" name="maxEncashAmt"
									id="maxEncashAmt<%=k%>"
									value="<%= String.valueOf(centerChk[k][6]) %>" />
							<!-- hidden -->
								
								<input type="hidden" name="hiddenEncashDays"
									id="hiddenEncashDays<%=k%>"
									value="<%= String.valueOf(centerChk[k][3]) %>" />
							 

							</tr>
							<%
									}
									}
								} catch (Exception e) {
									//e.printStackTrace();

								}
							%>
<s:if test="isTotal">
			<tr>
				<td colspan="7" align="right">Total Rs.</td>
				<td><s:textfield name="leaveEn.totalAmt" size="20" 
					theme="simple" cssStyle="background-color: #F2F2F2;text-align:right" readonly="true" /></td>

				<td></td>
			</tr>
		</s:if>

						</table>
						</td>
					</tr>
					
				</table>
			</s:if></td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table border="0" width="100%">
						<tr>
							<td width="15%" class="formtext"><label name="encashcomment"
								id="encashcomment" ondblclick="callShowDiv(this);"><%=label.get("encashcomment")%></label>
							<font color="red">*</font> :</td>
							<td width="85%">
							
							<s:if test="statusFlag!='P'">
								<s:textarea cols="70" rows="3" 
									name="leaveEn.enComment"/>
							</s:if>
							<s:elseif  test="hiddenStatusFlag!='PD'">
									<s:textarea cols="70" rows="3" readonly="true"
									name="leaveEn.enComment"  cssStyle="background-color: #F2F2F2;"/>
							</s:elseif>
							
							</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="approveList">
		
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">

					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">

							<tr>
								<td colspan="5" class="formhead"><strong
									class="forminnerhead"><label
								name="apprDtl" id="apprDtl"
								ondblclick="callShowDiv(this);"><%=label.get("apprDtl")%></label> </strong></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="5%" class="formth"><label
								name="srno" id="srno1"
								ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
								<td width="35%" class="formth"><label
								name="approverName" id="approverName"
								ondblclick="callShowDiv(this);"><%=label.get("approverName")%></label></td>
								<td width="20%" class="formth"><label
								name="approverDate" id="approverDate"
								ondblclick="callShowDiv(this);"><%=label.get("approverDate")%></label></td>
								<td width="40%" class="formth"><label
								name="appr.comments" id="appr.comments"
								ondblclick="callShowDiv(this);"><%=label.get("appr.comments")%></label></td>


							</tr>
							<%
							int j = 0;
							%>

							<s:iterator value="approveList">


								<tr class="sortableTD" onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this);">

									<td class="border2" width="8%" align="center"><%=++j%></td>
									<td class="border2" width="32%"><s:property
										value="apprName" /></td>
									<td class="border2" width="20%" align="left"><s:property
										value="apprDate" /></td>
									<td class="border2" width="40%" align="left"><s:property
										value="apprComments" /></td>
								</tr>


							</s:iterator>


						</table>

						</td>
					</tr>
				</table>

				</td>
			</tr>
		</s:if>
			<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
					<tr>
						<td width="78%">
							<s:if test="status=='PD'||approveStatusFlag=='PB'">
							
							<input type="button" class="token" value=" Draft"
								onclick="return callAdd();" />
								
							<!--<s:submit cssClass="save" action="LeaveEncashment_save" theme="simple" value=" Draft "
								onclick="return callAdd();"  /> 
								
								
							<s:submit cssClass="add" action="LeaveEncashment_forwardToApprover" 
									value=" Send for Approval" onclick=" return forwardForm();" />
							-->
							<input type="button" class="token" value=" Send for Approval"
								onclick="return forwardForm();" />
							
							
							<s:submit cssClass="reset"	action="LeaveEncashment_reset" value=" Reset" /> 
								
							<!--<input type="button" class="token" value=" Back"
								onclick="callBack()" /> 
								-->
							</s:if>
								<input type="button" class="token" value=" Back"
								onclick="callBack()" /> 
						</td>
						<td width="22%">
							<div align="right">
								<span class="style2"><font color="red">*</font></span> Indicates Required
							</div>
						</td>
					</tr>
				
			</table>

			</td>
		</tr>
	</table>
	<s:hidden name="leaveEn.encashLock" theme="simple" />
	<s:hidden name="leaveEn.statusFlag" />
	<s:hidden name="approveStatusFlag" />
</s:form>
<script>

 
function callApprove(){
	var comments = document.getElementById('paraFrm_leaveEn_approverComments').value;
	if(comments==""){
		alert("Please select " + document.getElementById("approverComment").innerHTML);
		return false;
	}
	var month = document.getElementById('paraFrm_salarymonth').value;
	var year = document.getElementById('paraFrm_salaryyear').value;
	var creditcode = document.getElementById('paraFrm_creditCode').value;	
	
	if(month=='0') {
		alert("Please select " + document.getElementById('sal.month').innerHTML);
		return false;
	}
	if(year=="") {
		alert("Please select " + document.getElementById('sal.year').innerHTML);
		return false;
	}
	if(creditcode=="") {
		alert("Please select " + document.getElementById('leaveencashcrdcode').innerHTML);
		return false;
	}
		
	var con = confirm('Do you really want to approve?');
	if(con){
		document.getElementById('paraFrm').target="main";
  		var encode = document.getElementById('paraFrm_leaveEn_enCode').value;
  		var empCode = document.getElementById("paraFrm_leaveEn_empId").value;
  		var checkStatus='A';
  		///var level='1';
  		var level = document.getElementById('paraFrm_level').value;
			///alert(level);
  		var statusRemark=comments;
  		var date=document.getElementById("paraFrm_leaveEn_encashDate").value;
		document.getElementById('paraFrm').action="LeaveEncashApproval_saveStatus.action?encashAppNo="+encode+"&empCode="+empCode+"&checkStatus="+checkStatus+"&level="+level+"&statusRemark="+statusRemark+"&date="+date+" ";	
		document.getElementById('paraFrm').submit();
	}else{
		return false;
	}	
  					
  } 
  
function callReject(){
	var comments = document.getElementById('paraFrm_leaveEn_approverComments').value;
	if(comments==""){
		alert ("Please enter "+document.getElementById('encashcomment').innerHTML.toLowerCase() );
		return false;
	}
	
	var con = confirm('Do you really want to reject?');
	if(con){   
		document.getElementById('paraFrm').target="main";
		var encode = document.getElementById('paraFrm_leaveEn_enCode').value;
		var empCode = document.getElementById("paraFrm_leaveEn_empId").value;
		var checkStatus='R';
		var level='1';
		var statusRemark=comments;
		var date=document.getElementById("paraFrm_leaveEn_encashDate").value;
		document.getElementById('paraFrm').action="LeaveEncashApproval_saveStatus.action?encashAppNo="+encode+"&empCode="+empCode+"&checkStatus="+checkStatus+"&level="+level+"&statusRemark="+statusRemark+"&date="+date+" ";	
		document.getElementById('paraFrm').submit();
	}else{
		return false;
	}	
} 

function callSendBack(){
	var comments = document.getElementById('paraFrm_leaveEn_approverComments').value;
	if(comments==""){
		alert ("Please enter "+document.getElementById('encashcomment').innerHTML.toLowerCase() );
		return false;
	}

	var con = confirm('Do you really want to send back?');
	if(con){   
		document.getElementById('paraFrm').target="main";
		var encode = document.getElementById('paraFrm_leaveEn_enCode').value;
		var empCode = document.getElementById("paraFrm_leaveEn_empId").value;
		var checkStatus='D';
		var level='1';
		var statusRemark=comments;
		var date=document.getElementById("paraFrm_leaveEn_encashDate").value;
		document.getElementById('paraFrm').action="LeaveEncashApproval_saveStatus.action?encashAppNo="+encode+"&empCode="+empCode+"&checkStatus="+checkStatus+"&level="+level+"&statusRemark="+statusRemark+"&date="+date+" ";	
		document.getElementById('paraFrm').submit();
	}else{
		return false;
	}	
} 
   
  function callBack(){
  				document.getElementById('paraFrm').target="main";  	
  				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';			
				///document.getElementById('paraFrm').action="LeaveEncashApproval_input.action";
				document.getElementById('paraFrm').submit();	
  }
  
  
 function callAddKeepInfo(){
		try{
			var empId = document.getElementById("paraFrm_leaveEn_empId").value;
		  var keepInformCode = document.getElementById("paraFrm_informCode").value;
		  var keepInformedName = document.getElementById("paraFrm_informName").value;
		  if(empId==""){
			  alert("Please select " + document.getElementById("employee").innerHTML);
			  return false;
		  }
		  if(keepInformedName==""){
		  	alert("Please select keep informed to");
		  	return false;
		  }
		  var tbl = document.getElementById('keepInformedTable');
		  var lastRow = tbl.rows.length;
		  var iteration = (lastRow+1)+" ) ";
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'keepInformToName';
		  column1.value = keepInformedName; /*value to be set in the added cell*/
		  column1.id = 'keepInformToName'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/common/css/icons/delete.gif";
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';

		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);
		  
		  var column3 = document.createElement('input');
		  column3.type = 'hidden';
  		  column3.name = 'keepInformToCode';
  		  column3.value = keepInformCode; /*value to be set in the added cell*/
		  column3.id = 'keepInformToCode'+iteration;
		  column3.maxLength ='2';
		  cell2.appendChild(column3);
		  
	 
		}catch(e){alert(e);}
		document.getElementById("paraFrm_informName").value="";
	
	}
	
	function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		
	}
	
function callAdd(){
	try{
		var pre1 = document.getElementById('paraFrm_leaveEn_empId').value;
		var encode = document.getElementById('paraFrm_leaveEn_enCode').value;
		var lock = document.getElementById('paraFrm_leaveEn_encashLock').value;
		var statusFlag = document.getElementById('paraFrm_leaveEn_statusFlag').value;
  		var comments = document.getElementById('paraFrm_leaveEn_enComment').value;
  		
		if(statusFlag != 0) {
			alert("Leave Encashment already forwarded so can't be modified!");
			return false;
		}		
		if(pre1==""){
			alert ("Please select the "+document.getElementById('employee').innerHTML.toLowerCase() );
			return false;
		}
		
		
		var totalAmt=document.getElementById('paraFrm_leaveEn_totalAmt').value;
		if(totalAmt=='' || totalAmt==0){
			alert("There are no leaves to be encashed.\nRecord cannot be saved. ");
			return false;
		}
		if(comments==""){
			alert ("Please enter "+document.getElementById('encashcomment').innerHTML.toLowerCase() );
			return false;
		}
		//document.getElementById("overlay").style.visibility = "visible";
		//document.getElementById("overlay").style.display = "block";
		
		document.getElementById('paraFrm_hiddenStatus').value = 'D';	
		///alert(document.getElementById('paraFrm_hiddenStatus').value);
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'LeaveEncashment_save.action?status=D';
			document.getElementById('paraFrm').submit();
		
		}
		catch(e){
			//alert(e);
		}
	}
		
		function callReport(name) {
		
		if(document.getElementById('paraFrm_leaveEn_enCode').value == ""){
  				alert("Please select application");
  			}else{
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
			}	
							
	}	
	
function forwardForm() {
	var statusFlag = document.getElementById('paraFrm_leaveEn_statusFlag').value;
	var pre1 = document.getElementById('paraFrm_leaveEn_empId').value;
	var encode = document.getElementById('paraFrm_leaveEn_enCode').value;
	var lock = document.getElementById('paraFrm_leaveEn_encashLock').value;
	var empNameVar = trim(document.getElementById('paraFrm_leaveEn_empName').value);
	if(statusFlag != 0) {
		alert("Leave Encashment already forwarded!");
		return false;
	}
			
	if(pre1==""){
		alert ("Please select the "+document.getElementById('employee').innerHTML.toLowerCase() );
		return false;
	}

	var totalAmt = document.getElementById('paraFrm_leaveEn_totalAmt').value;
	if(totalAmt=='' || totalAmt==0){
		alert("There are no leaves to be encashed.\nRecord cannot be saved. ");
		return false;
	}
	
	var comments = document.getElementById('paraFrm_leaveEn_enComment').value;
	if(comments==""){
		alert ("Please enter "+document.getElementById('encashcomment').innerHTML.toLowerCase() );
		return false;
	}
	
	var table = document.getElementById('approverListValue');
	var rowCount = table.rows.length; 
		////alert("rowCount=="+rowCount);
	  if(eval(rowCount==1))
	 {
 			alert("Reporting structure is not defined for the employee\n"+empNameVar+"\n Please contact your HR Manager.");
 			return false;
	 }
	 
	 
	con=confirm('Do you really want to send for approval?');
	if(con){
	document.getElementById('paraFrm_hiddenStatus').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='LeaveEncashment_forwardToApprover.action?status=P';
			document.getElementById('paraFrm').submit();
			
		return true;
	}else{
		return false;
	}
	//document.getElementById("overlay").style.visibility = "visible";
	//document.getElementById("overlay").style.display = "block";
}	


	function calculate(k)
	{
		var newBalance = document.getElementById("clb"+k).value;
		var availabellimit = document.getElementById("availabellimit"+k).value;
		var oldBalance = document.getElementById("hiddenEncashDays"+k).value;
		var encashLeave = document.getElementById("enb"+k).value;
		var totalBalance=eval(oldBalance)+eval(newBalance);
		var amtPerDay = document.getElementById("amtPerDay"+k).value;
		var encashLimit = document.getElementById("maxEncashAmt"+k).value;
		var total=document.getElementById("total"+k).value;
			if(encashLeave=='')
			{
				encashLeave=0;
			}
			if(amtPerDay=='')
				{
					amtPerDay=0;
				}
			 
			if(availabellimit == "NA" || parseFloat(encashLeave) <= parseFloat(availabellimit)){
			}else{
				alert('Encash Leave should not exceed availabel limit ');
				document.getElementById("enb"+k).value='0';
				document.getElementById("total"+k).value='0';
				onload1();
				return false;
			}
			
			if(parseFloat(encashLeave)<= parseFloat(totalBalance))
			{
			}
			else 
			{
				alert('Encash Leave should not exceed Balance Leave ');
				document.getElementById("enb"+k).value='0';
				document.getElementById("total"+k).value='0';
				onload1();
				return false;
			}
					/*if(parseFloat(encashLeave)<=parseFloat(encashLimit))
				{
				}
				else
				{
						if(parseFloat(encashLimit)==0)
						{
						}
						else
						{
						alert('Encash Leave should be less than encash maximum limit that is \n '+ encashLimit);
						document.getElementById("enb"+k).value=0;
						document.getElementById("total"+k).value='0';
						onload1();
						return false;
						}
				}*/
				var result=parseFloat(encashLeave*100/100)*parseFloat(amtPerDay);
				document.getElementById("total"+k).value=result;
	
	}
	
	
		
function onload1()	{
	try{
		var z='<%=y%>';
		var total=0;
		var total1=0;
			 
		for(var m=0 ; m < z ; m++){
			if(document.getElementById("enb"+m).value == ''){
				document.getElementById("enb"+m).value=0;
			}
			total =document.getElementById("total"+m).value ;
			total1=eval(total1)+eval(total);
		}
		document.getElementById('paraFrm_leaveEn_totalAmt').value=Math.round(total1);
	}
	catch(e){
		 alert(e);
	}
}
		
function getYear(){
	var current = new Date();
	var year = current.getFullYear();
	var yr = document.getElementById("paraFrm_salaryyear").value;
	if(yr==''){
		document.getElementById("paraFrm_salaryyear").value =year;
	}
}

getYear();  		
		   
	
  </script>
 