<!--@author prajakta B-->
<!--@ date 07 Jan 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="PayHistory" validate="true" id="paraFrm" theme="simple"
	target="main">
	<s:hidden name="editDetail" />
	<s:hidden name="editFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="paracode" />
	<s:hidden name="empId" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<fieldset><legend class="legend"> <img
				src="../pages/mypage/images/icons/profile_documentsubmit.png" />
			&nbsp;&nbsp;Increment History </legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
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
															<td align="right"><a href="#" onclick="searchFun()"
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
												<fieldset><legend class="legend1">Increment History
												Particulars</legend>
												<table width="100%" border="0" align="center"
													cellpadding="2" cellspacing="1">
													<tr>
													<td width="22%"><label class="set" name="incrType" id="incrType"
															ondblclick="callShowDiv(this);"><%=label.get("incrType")%></label>
														</td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="17%"><s:select name="PayType" headerKey="0"
															headerValue="-------select-------" cssStyle="width:166"
															list="assetmap"/></td>
														<td width="10%">&nbsp;</td>
														<td width="22%"><label name="oldctc"  id="oldctc"
															ondblclick="callShowDiv(this);"><%=label.get("oldctc")%></label></td>
														<td width="2%"></td>
														<td width="2%">:</td>
														<td width="17%"><s:textfield size="25" theme="simple" maxlength="15"
															name="octc" onkeypress="return numbersWithDot();" /></td>
														<td width="4%"></td>
													</tr>
													<tr>
													<td width="22%"><label class="set" name="newctc" id="newctc"
															ondblclick="callShowDiv(this);"><%=label.get("newctc")%></label>
														</td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="17%"><s:textfield size="25" theme="simple"
															maxlength="15" name="nctc"
															onkeypress="return numbersWithDot();" /></td>
														<td width="10%">&nbsp;</td>
														<td width="22%"><label name="dateofincr"  id="dateofincr"
															ondblclick="callShowDiv(this);"><%=label.get("dateofincr")%></label></td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="17%"><s:textfield size="25" theme="simple"
															maxlength="10" name="incrementDate"
															onkeypress="return numbersWithHiphen();"
															onblur="return validateDate('paraFrm_incrementDate','dateofincr');" />
														</td>
														<td width="4%"><s:a
															href="javascript:NewCal('paraFrm_incrementDate','DDMMYYYY');">
															<img src="../pages/images/recruitment/Date.gif"
																class="imageIcon" border="0" align="absmiddle" />
														</s:a></td>
													</tr>
													</table>
												</fieldset>
												</td>
											</tr>
										</s:if>
										<tr>
											<td>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td colspan="5">
													<fieldset><legend class="legend1">Increment History
													Details</legend>
													<table width="100%" border="0" cellpadding="2"
														cellspacing="1" class="sortable" align="center">
														<tr bgcolor="#EEF4FB">
															<td width="23%" align="center"><label id="incrType"
																name="incrType" ondblclick="callShowDiv(this);"><%=label.get("incrType")%></label></td>
															<td width="23%" align="center"><label
																name="oldctc" id="oldctc"
																ondblclick="callShowDiv(this);"><%=label.get("oldctc")%></label></td>
															<td width="23%" align="center"><label name="newctc"
																id="newctc" ondblclick="callShowDiv(this);"><%=label.get("newctc")%></label></td>
															<td width="23%" align="center"><label
																name="dateofincr" id="dateofincr"
																ondblclick="callShowDiv(this);"><%=label.get("dateofincr")%></label></td>
															<td width="8%" align="center">Edit|Delete</td>
														</tr>
														<s:iterator value="payList">
															<tr class="sortableTD">
															    <s:hidden name="payId" />
																<td width="23" class="text1"><s:property
																	value="payType" /></td>
																<td width="23" class="text1"><s:property
																	value="octc" /></td>
																<td width="23" class="text1"><s:property
																	value="nctc" /></td>
																<td width="23" class="text1" align="center"><s:property
																	value="incrementDate" /></td>
																<td width="8%" align="center"><s:if test="updateFlg">
																	<img src="../pages/mypage/images/icons/edit.png"
																		width="10" height="10" border="0"
																		onclick="callForEdit('<s:property value="payId"/>')" />
																		|</s:if> <s:if test="deleteFlg">
																	<img src="../pages/mypage/images/icons/delete.png"
																		width="10" height="10" border="0"
																		onclick="callForDelete('<s:property value="payId"/>')" />
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
													</fieldset>
													</td>
												</tr>
											</table>
											</td>
										</tr>
										<tr height="10"></tr>
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
	document.getElementById("paraFrm").action="PayHistory_add.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function cancelFun(){
try{
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="PayHistory_cancel.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function searchFun(){
javascript:callsF9(500,325,'PayHistory_f9action.action');
}

function callForEdit(id){
	   	document.getElementById("paraFrm").action="PayHistory_edit.action";
	    document.getElementById('paraFrm_paracode').value=trim(id);
	    document.getElementById("paraFrm").submit();
   }
function callForDelete(id){
	var r=confirm("Are you sure to delete this record?");
		if(r==false){
			return false;
		}else{
	   	document.getElementById("paraFrm").action="PayHistory_delete.action";
	   	document.getElementById('paraFrm_paracode').value=trim(id);
	    document.getElementById("paraFrm").submit();
	   }
   }
   

function saveFun(){
  
	
	var nctc = document.getElementById('paraFrm_nctc').value;
	var octc = document.getElementById('paraFrm_octc').value;
	var incrementDate = document.getElementById('paraFrm_incrementDate').value;	
	if(document.getElementById('paraFrm_PayType').value=="0")
	{
	alert("Please select "+document.getElementById('incrType').innerHTML.toLowerCase());
	return false;
	}
	if(nctc==""){
			alert ("Please enter "+document.getElementById('newctc').innerHTML.toLowerCase());
			return false;
		}
		
		if(!(incrementDate=="")) {
	     if(!validateDate('paraFrm_incrementDate','dateofincr')){
		    return false;
		  }
	  }		
	if(incrementDate==""){
	alert ("Please enter/select "+document.getElementById('dateofincr').innerHTML.toLowerCase());
	return false;
	}
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="PayHistory_save.action";
	document.getElementById("paraFrm").submit();
}
</script>