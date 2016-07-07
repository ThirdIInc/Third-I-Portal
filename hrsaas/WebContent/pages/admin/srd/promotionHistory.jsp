<!--@author prajakta B-->
<!--@ date 09 Jan 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="PromotionHistory" validate="true" id="paraFrm"
	theme="simple" target="main">
	<s:hidden name="editDetail" />
	<s:hidden name="editFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="paracode" />
	<s:hidden name="empId" />
	<s:hidden theme="simple" name="payScaleId" />
	<s:hidden theme="simple" name="postId" />
	<s:hidden theme="simple" name="tradeId" />
	<s:hidden theme="simple" name="deptId" />
	<s:hidden name="centerNo" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<fieldset><legend class="legend"> <img
				src="../pages/mypage/images/icons/profile_documentsubmit.png" />
			&nbsp;&nbsp;Promotion History</legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="middle">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td></td>
										</tr>
										<tr>
											<td height="0">
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">
					<s:if test="editFlag">
													<tr align="right">
														<s:if test="insertFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="1%">|</td>
														</s:if>
														<s:elseif test="updateFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="1%">|</td>
														</s:elseif>
														<td width="2%"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancelFun()" width="10" height="10" /></td>
														<td width="3%"><a href="#" onclick="cancelFun()"
															class="iconbutton">Cancel</a></td>
														<td>|</td>
													</tr>
												</s:if>

												<s:else>
													<tr align="right">
														<s:if test="insertFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/add.png"
																onclick="addFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="addFun()"
																class="iconbutton">Add</a></td>
															<td width="1%">|</td>
														</s:if>
														<s:if test="isNotGeneralUser">
															<td width="100%" align="right"><a href="#"><img
																src="../pages/mypage/images/icons/search.png"
																onclick="searchFun()" width="10" height="10" border="0" /></a></td>
															<td  align="right"><a href="#" onclick="searchFun()"
																class="iconbutton">Search</a></td>
															<td>|</td>
														</s:if>
													</tr>
												</s:else>
		
											</table>
											</td>
										</tr>
										<tr>
											<td height="1" bgcolor="#cccccc" class="style1"></td>
										</tr>
										<s:if test="editDetail">
											<tr>
												<td colspan="11">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
														<fieldset><legend class="legend1">Promotion
														History Details</legend>
														<table width="100%" border="0" align="center"
															cellpadding="2" cellspacing="3">
															<tr>
																<td width="22%"><label name="designation" id="designation"
																	ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
																</td>
																<td width="2%" class="star">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield size="25"
																	theme="simple" name="post" readonly="true" /></td>
																<td width="4%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="rankAction()" /></td>
																<td width="6%">&nbsp;</td>
																<td width="22%"><label name="department" id="department"
																	id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
																<td width="2%" class="star">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield size="25"
																	theme="simple" name="dept" readonly="true" /></td>
																<td width="4%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="deptAction()" /> </td>
															</tr>
															<tr>
																<td width="22%"><label name="branch" id="branch"
																	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
																<td width="2%" class="star">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield size="25"
																	theme="simple" name="centerName" readonly="true" /></td>
																<td width="4%"><input type="button" name="Submit3"
																	class="button" value="..." onclick="centerAction()" /></td>
																<td width="6%">&nbsp;</td>
																<td width="22%"><label name="ctc" id="ctc"
																	ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></td>
																<td width="2%" class="star">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield size="25"
																	 name="ctc" maxLength="15"
																	onkeypress="return numbersWithDot();" /></td>
																<td width="4%"></td>

															</tr>

															<tr>
																<td width="22%"><label name="promotiondate" id="promotiondate"
																	ondblclick="callShowDiv(this);"><%=label.get("promotiondate")%></label></td>
																<td width="2%" class="star">*</td>
																<td width="2%">:</td>
																<td width="17%"><s:textfield size="25"
																	theme="simple" name="promotionDate" maxlength="10"
																	onkeypress="return numbersWithHiphen();"
																	onblur="return validateDate('paraFrm_promotionDate', 'promotiondate')"/></td>
																<td width="4%"><s:a
																	href="javascript:NewCal('paraFrm_promotionDate','DDMMYYYY');">
																	<img class="iconImage"
																		src="../pages/images/recruitment/Date.gif" width="16"
																		height="16" border="0" align="absmiddle" />
																</s:a></td>
																<td width="6%">&nbsp;</td>
																<td width="22%"></td>
																<td width="2%"></td>
																<td width="2%"></td>
																<td width="17%"></td>
																<td width="4%"></td>
															</tr>
														</table>
														</fieldset>
														</td>
													</tr>
												</table>
												</td>
											</tr>
										</s:if>
										<tr>
											<td>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td colspan="5">
													<fieldset><legend class="legend1">Promotion
													History Details</legend>
													<table width="100%" border="0" cellpadding="2"
														cellspacing="3">
														<tr>
															<td>
															<table width="100%" border="0" cellpadding="1"
																cellspacing="1" class="sortable">
																<tr bgcolor="#EEF4FB">
																	<td width="23%" " align="center"><label
																		name="designation" id="designation"  ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
																	<td width="23%" align="center"><label
																		name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
																	<td width="23%" align="center"><label
																		name="promotiondate" id="promotiondate" ondblclick="callShowDiv(this);"><%=label.get("promotiondate")%></label></td>
																	<td width="23%" align="center"><label name="ctc" id="ctc"
																		ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></td>
																	<td width="8%" nowrap="nowrap" align="center">Edit
																	| Delete</td>
																</tr>
																<s:iterator value="promoList">
																	<tr class="sortableTD">
																		<s:hidden name="promoId" />
																		<td width="23%" class="text1"><s:property
																			value="post" /></td>
																		<td width="23%" class="text1"><s:property
																			value="dept" /></td>
																		<td width="23%" class="text1" align="center"><s:property
																			value="promotionDate" /></td>
																		<td width="23%" class="text1"><s:property
																			value="ctc" /></td>
																		<td width="8%" align="center"><s:if
																			test="updateFlg">
																			<img src="../pages/mypage/images/icons/edit.png"
																				width="10" height="10" border="0"
																				onclick="callForEdit('<s:property value="promoId"/>')" />
																		|</s:if> <s:if test="deleteFlg">
																			<img src="../pages/mypage/images/icons/delete.png"
																				width="10" height="10" border="0"
																				onclick="callForDelete('<s:property value="promoId"/>')" />
																		</s:if></td>
																	</tr>
																</s:iterator>
																<s:if test="noData">
																	<tr align="center">
																		<td align="center" colspan="5"><font color="red">No
																		data to display</font></td>
																	</tr>
																</s:if>
															</table>
															</td>
														</tr>
													</table>
													</fieldset>
													</td>
												</tr>
												<s:if test="prmlength">
													<tr>
														<td colspan="5">
														<table width="100%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td>
																<fieldset><legend class="legend1">Promotion
																Details</legend>
																<table width="100%" border="0" cellpadding="2"
																	cellspacing="3">
																	<tr>
																		<td>
																		<table width="100%" border="0" cellpadding="1"
																			cellspacing="1" class="sortable">
																			<tr bgcolor="#EEF4FB">
																				<td width="23%" class="sortable" align="center"><label
																					name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
																				<td width="23%" class="sortable" align="center"><label
																					name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
																				<td width="23%" class="sortable" align="center"><label
																					name="efectivedate" id="efectivedate" ondblclick="callShowDiv(this);"><%=label.get("efectivedate")%></label></td>
																				<td width="23%" class="sortable" align="center"><label
																					name="newCTC" id="newCTC" ondblclick="callShowDiv(this);"><%=label.get("newCTC")%></label></td>

																			</tr>
																			<s:iterator value="historyList">
																				<tr class="sortableTD">
																					<td width="23%" class="text1"><s:property
																						value="rank" /></td>
																					<td width="23%" class="text1"><s:property
																						value="dept" /></td>
																					<td width="23%" class="text1" align="center"><s:property
																						value="efectiveDate" /></td>
																					<td width="23%" class="text1"><s:property
																						value="newCtc" /></td>
																				</tr>
																			</s:iterator>
																		</table>
																		</td>
																	</tr>
																</table>
																</fieldset>
																</td>
															</tr>
														</table>
														</td>
													</tr>
												</s:if>
											</table>
											</td>
										</tr>

										<tr height="10">
											<td></td>
										</tr>
										<tr>
											<td height="1" bgcolor="#cccccc" class="style1"></td>
										</tr>
										<tr>
											<td height="0">
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">
													<s:if test="editFlag">
													<tr align="right">
														<s:if test="insertFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="1%">|</td>
														</s:if>
														<s:elseif test="updateFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="1%">|</td>
														</s:elseif>
														<td width="2%"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancelFun()" width="10" height="10" /></td>
														<td width="3%"><a href="#" onclick="cancelFun()"
															class="iconbutton">Cancel</a></td>
														<td>|</td>
													</tr>
												</s:if>

												<s:else>
													<tr align="right">
														<s:if test="insertFlg">
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/add.png"
																onclick="addFun()" width="10" height="10" border="0" /></a></td>
															<td width="2%"><a href="#" onclick="addFun()"
																class="iconbutton">Add</a></td>
															<td width="1%">|</td>
														</s:if>
														<s:if test="isNotGeneralUser">
															<td width="100%" align="right"><a href="#"><img
																src="../pages/mypage/images/icons/search.png"
																onclick="searchFun()" width="10" height="10" border="0" /></a></td>
															<td align="right"><a href="#" onclick="searchFun()"
																class="iconbutton">Search</a></td>
															<td>|</td>
														</s:if>
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
					</table>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</div>
</s:form>
<script>
function addFun(){
try{
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="PromotionHistory_add.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function searchFun(){
try{
javascript:callsF9(500,325,'PromotionHistory_f9actionEmployeeId.action');
}catch(e){alert(e);}
}

function rankAction(){
try{
javascript:callsF9(500,325,'PromotionHistory_f9actionPost.action');
}catch(e){alert(e);}
}

function deptAction(){
try{
javascript:callsF9(500,325,'PromotionHistory_f9actionDept.action');
}catch(e){alert(e);}
}

function centerAction(){
try{
javascript:callsF9(500,325,'PromotionHistory_f9actionCenterNo.action');
}catch(e){alert(e);}
}

function saveFun(){
try{
	var post = document.getElementById('paraFrm_post').value;
 	var dept = document.getElementById('paraFrm_dept').value;
	var branch = document.getElementById('paraFrm_centerName').value;
	var ctc = document.getElementById('paraFrm_ctc').value;
	var promoDate= document.getElementById('paraFrm_promotionDate').value;
	if(post==""){
		alert("Please select "+document.getElementById('designation').innerHTML.toLowerCase());
		return false;
	}
	if(dept==""){
		alert("Please select "+document.getElementById('department').innerHTML.toLowerCase());
		return false;
	}
	if(branch==""){
		alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
		return false;
	}
	if(ctc==""){
		alert("Please enter "+document.getElementById('ctc').innerHTML.toLowerCase());
		return false;
	}
	if(promoDate==""){
		alert("Please enter/select "+document.getElementById('promotiondate').innerHTML.toLowerCase());
		return false;
	}
	if(promoDate!="" ){
	  if(!validateDate('paraFrm_promotionDate','promotiondate')){
		    return false;
		}
  	}
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="PromotionHistory_save.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function cancelFun(){
try{
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="PromotionHistory_cancel.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function callForEdit(id){
try{
	   	document.getElementById("paraFrm").action="PromotionHistory_edit.action";
	    document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
   }
function callForDelete(id){
try{
	var r=confirm("Are you sure to delete this record?");
		if(r==false){
			return false;
		}else{
	   	document.getElementById("paraFrm").action="PromotionHistory_delete.action";
	   	document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }
}catch(e){alert(e);}	    
   }
   
   

</script>