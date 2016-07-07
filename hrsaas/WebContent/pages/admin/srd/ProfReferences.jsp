<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="ProfReferences" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="empId" />
	<s:hidden name="paraId" />
	<s:hidden name="newFlag" />
	<s:hidden name="editDetail" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<fieldset><legend class="legend"> <img
				height="16" width="16"
				src="../pages/mypage/images/icons/profile_references.png" />
			&nbsp;&nbsp;Professional References</legend>
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
												<td colspan="10">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="3">
													<tr>
														<td>
														<fieldset><legend class="legend1">Reference
														Particulars</legend>
														<table width="98%" border="0" align="center"
															cellpadding="2" cellspacing="1">
															<tr>
																<td width="18%"><label name="profname1"
																	id="profname1" ondblclick="callShowDiv(this);"><%=label.get("profname1")%></label></td>
																<td width="1%"><font color="red">*</font></td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="profFname" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"><label name="phone1" id="phone1"
																	ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="30%"><s:textfield size="25"
																	maxlength="15" name="phoneNo"
																	onkeypress="return validatePhoneNo()" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="profname2"
																	id="profname2" ondblclick="callShowDiv(this);"><%=label.get("profname2")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="profMname" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="20%"><label name="extension"
																	id="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="25%"><s:textfield size="25"
																	name="extension" maxlength="10"
																	onkeypress="return validatePhoneNo()" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="profname3"
																	id="profname3" ondblclick="callShowDiv(this);"><%=label.get("profname3")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="profLname" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="20%"><label name="fax" id="fax"
																	ondblclick="callShowDiv(this);"><%=label.get("fax")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="25%"><s:textfield size="25"
																	name="faxNo" maxlength="15"
																	onkeypress="return numbersWithHiphen()" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="address" id="address"
																	ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
																</td>
																<td width="1%"><font color="red">*</font></td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="address1" maxlength="150" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="20%"><label name="mobile.no"
																	id="mobile.no" ondblclick="callShowDiv(this);"><%=label.get("mobile.no")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="25%"><s:textfield size="25"
																	maxlength="15" name="mobileNo"
																	onkeypress="return validatePhoneNo()" /></td>
															</tr>
															<tr>
																<td width="18%">&nbsp;</td>
																<td width="1%">&nbsp;</td>
																<td width="1%">&nbsp;</td>
																<td width="23%"><s:textfield size="25"
																	name="address2" maxlength="150" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"><label name="email" id="email"
																	ondblclick="callShowDiv(this);"><%=label.get("email")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="25%"><s:textfield size="25"
																	name="emailId" maxlength="45" /></td>
															</tr>
															<tr>
																<td width="18%">&nbsp;</td>
																<td width="1%">&nbsp;</td>
																<td width="1%">&nbsp;</td>
																<td width="23%"><s:textfield size="25"
																	name="address3" maxlength="150" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="20%"><label name="prof.occupation"
																	id="prof.occupation" ondblclick="callShowDiv(this);"><%=label.get("prof.occupation")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="25%"><s:textfield size="25"
																	name="occupation" maxlength="100"
																	onkeypress="return charactersOnly()" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="city" id="city"
																	ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
																<td width="1%"><font color="red">*</font></td>
																<td width="1%">:</td>
																<td width="23%"><s:hidden name="cityCode" /> <s:textfield
																	size="25" name="cityName" readonly="true" /></td>
																<td width="5%"><input type="button" value="..."
																	onclick="cityAction()"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"><label name="company" id="company"
																	ondblclick="callShowDiv(this);"><%=label.get("company")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="30%"><s:textfield size="25"
																	name="companyName" maxlength="100"
																	onkeypress="return alphaNumeric()" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="pin" id="pin"
																	ondblclick="callShowDiv(this);"><%=label.get("pin")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="pinCode" maxlength="6"
																	onkeypress="return numbersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"><label name="other" id="other"
																	ondblclick="callShowDiv(this);"><%=label.get("other")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="30%"><s:textfield size="25"
																	name="otherInfo" maxlength="100" /></td>
															</tr>
															<tr>
																<td width="18%"><label name="state" id="state"
																	ondblclick="callShowDiv(this);"><%=label.get("state")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="stateName" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"></td>
																<td width="1%">&nbsp;</td>
																<td width="1%"></td>
																<td width="30%"></td>
															</tr>
															<tr>
																<td width="18%"><label name="country" id="country"
																	ondblclick="callShowDiv(this);"><%=label.get("country")%></label></td>
																<td width="1%">&nbsp;</td>
																<td width="1%">:</td>
																<td width="23%"><s:textfield size="25"
																	name="countryName" maxlength="30"
																	onkeypress="return charactersOnly()" /></td>
																<td width="5%"></td>
																<td width="3%">&nbsp;</td>
																<td width="15%"></td>
																<td width="1%">&nbsp;</td>
																<td width="1%"></td>
																<td width="30%"></td>
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
											<td colspan="6">
											<fieldset><legend class="legend">References
											Details</legend>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td>
													<table width="100%" border="0" cellpadding="1"
														cellspacing="1" class="sortable">
														<tr>
															<td><s:iterator value="profList" status="stat">
																<table width="100%" border="0" align="center"
																	cellpadding="2" cellspacing="2">
																	<tr>
																		<td width="99%" height="20" colspan="5"
																			bgcolor="#EEF4FB">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td width="100%" height="20">
																				<table width="100%" border="0" cellspacing="0"
																					cellpadding="0">
																					<tr>
																						<td width="95%"><s:hidden name="profRefId" />
																						<s:property value="profrefName" />&nbsp; <s:property
																							value="ProfMname" />&nbsp; <s:property
																							value="ProfLname" /></td>
																						<td><s:if test="updateFlg">
																							<img src="../pages/mypage/images/icons/edit.png"
																								width="10" height="10" border="0"
																								onclick="callForEdit('<s:property value="profRefId"/>')" />
																						|</s:if>  <s:if test="deleteFlg">
																							<img
																								src="../pages/mypage/images/icons/delete.png"
																								width="10" height="10" border="0"
																								onclick="callDelete('<s:property value="profRefId"/>')" />
																						</s:if></td>
																					</tr>
																				</table>
																				</td>
																			</tr>
																		</table>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="6">
																		<table width="100%">
																			<tr>
																				<td width="18%"><label name="address"
																					id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label></td>
																				<td width="1%">:</td>
																				<td width="30%" class="text1"
																					title="<s:property value="address1"/>"><s:property
																					value="abbrAdd1" />
																				<td width="18%"><label name="phone1"
																					id="phone1" ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label></td>
																				<td width="1%">:</td>
																				<td class="text1"><s:property
																					value="phoneNo" /></td>

																			</tr>
																			<tr>
																				<td width="18%"></td>
																				<td width="1%"></td>
																				<td width="30%" class="text1"
																					title="<s:property value="address2"/>"><s:property
																					value="abbrAdd2" />
																				<td width="18%"><label name="extension"
																					id="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1"><s:property
																					value="extension" /></td>
																			</tr>
																			<tr>
																				<td width="18%"></td>
																				<td width="1%"></td>
																				<td width="30%" class="text1"
																					title="<s:property value="address3"/>"><s:property
																					value="abbrAdd3" />
																				<td width="18%"><label name="fax" id="fax"
																					ondblclick="callShowDiv(this);"><%=label.get("fax")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1"><s:property value="faxNo" /></td>
																			</tr>
																			<tr>
																				<td width="18%"><label name="city" id="city"
																					ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
																				<td width="1%">:</td>
																				<td width="30%" class="text1"><s:property
																					value="cityName" /></td>
																				<td width="18%"><label name="mobile.no"
																					id="mobile.no" ondblclick="callShowDiv(this);"><%=label.get("mobile.no")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1"
																					><s:property
																					value="mobileNo" /></td>
																			</tr>
																			<tr>
																				<td width="18%"><label name="pin" id="pin"
																					ondblclick="callShowDiv(this);"><%=label.get("pin")%></label></td>
																				<td width="1%">:</td>
																				<td width="30%" class="text1"><s:property
																					value="pinCode" /></td>
																				<td width="18%"><label name="email" id="email"
																					ondblclick="callShowDiv(this);"><%=label.get("email")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1" title="<s:property
																					value="emailId" />"><s:property
																					value="abbrEmailId" /></td>
																			</tr>
																			<tr>
																				<td width="18%"><label name="state" id="state"
																					ondblclick="callShowDiv(this);"><%=label.get("state")%></label></td>
																				<td width="1%">:</td>
																				<td width="30%" class="text1"><s:property
																					value="stateName" /></td>
																				<td width="18%"><label name="prof.occupation"
																					id="prof.occupation"
																					ondblclick="callShowDiv(this);"><%=label.get("prof.occupation")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1" title="<s:property value="occupation"/>"
																					><s:property
																					value="abbrOccupation" /></td>
																			</tr>
																			<tr>
																				<td width="18%"><label name="country"
																					id="country" ondblclick="callShowDiv(this);"><%=label.get("country")%></label></td>
																				<td width="1%">:</td>
																				<td width="30%" class="text1"><s:property
																					value="countryName" /></td>
																				<td width="18%"><label name="company"
																					id="company" ondblclick="callShowDiv(this);"><%=label.get("company")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1" title="<s:property value="companyName"/>"><s:property
																					value="abbrCompany" /></td>
																			</tr>
																			<tr>
																				<td width="18%"></td>
																				<td width="1%"></td>
																				<td width="30%" class="text1"></td>
																				<td width="18%"><label name="other" id="other"
																					ondblclick="callShowDiv(this);"><%=label.get("other")%></label></td>
																				<td width="1%">:</td>
																				<td width="32%" class="text1" title="<s:property value="otherInfo"/>"><s:property
																					value="abbrOtherInfo" /></td>
																			</tr>

																		</table>
																		</td>
																	</tr>
																</table>
																<tr height="7"></tr>
															</s:iterator></td>
														</tr>
														<tr>
															<s:if test="noData">
																<td width="100%" align="center"><font color="red">No
																data to display</font></td>
															</s:if>
														</tr>
													</table>
													</td>
												</tr>
											</table>
											</fieldset>
											</td>
										</tr>

										<tr height="10"></tr>
										<tr>
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
 function myOnload(){
   var  flag= document.getElementById("paraFrm_newFlag").value;
   if(flag){
		document.getElementById("paraFrm_type").disabled=true;
    }
 }
 function callForEdit(id){    
   	document.getElementById('paraFrm_paraId').value=id;   
   	document.getElementById("paraFrm").action="ProfReferences_edit.action";
  	 document.getElementById("paraFrm").submit();	   
 }
   
 function callDelete(id){
    document.getElementById('paraFrm_paraId').value=id;
   	var conf=confirm("Are you sure to delete this record?");
   	if(conf) {
   		document.getElementById("paraFrm").action="ProfReferences_delete.action";
		document.getElementById("paraFrm").submit();
   	} else {
   		document.getElementById('paraFrm_paraId').value="";
   		return false;	
  }
 }
   
 function addFun(){
	   	document.getElementById("paraFrm").action="ProfReferences_add.action";
	    document.getElementById("paraFrm").submit();	
 }

 function searchFun(){
   try{
  		javascript:callsF9(500,325,'ProfReferences_f9action.action');
  	}catch(e){
  		alert(e);  
  	}			
 }

 function cancelFun(){      
    document.getElementById("paraFrm").action="ProfReferences_cancel.action";
	document.getElementById("paraFrm").submit();	
 }
 function saveFun(){
         var proffirstname=document.getElementById('paraFrm_profFname').value;
	     var ad1=document.getElementById('paraFrm_address1').value;
		 var ad2=document.getElementById('paraFrm_address2').value;
		 var ad3=document.getElementById('paraFrm_address3').value;
		 var city=document.getElementById('paraFrm_cityName').value;
		 var emailid=document.getElementById('paraFrm_emailId').value;		 
		 if(proffirstname==""){
			 alert("Please enter "+document.getElementById('profname1').innerHTML.toLowerCase());
		 	return false;
		 }		 
		 if(ad1=="" && ad2=="" && ad3==""){
		 	alert("Please enter "+document.getElementById('address').innerHTML.toLowerCase());
		 	return false;
		 }
		 if(city==""){
			 alert("Please select " +document.getElementById('city').innerHTML.toLowerCase());
			 return false;
		 }
		 if(!emailid ==""){
			var abc=validateEmail('paraFrm_emailId');
			if(!abc){
			return false;
			}
		}		
        document.getElementById("paraFrm").action="ProfReferences_save.action";
	    document.getElementById("paraFrm").submit();
 }

 function cityAction(){
	javascript:callsF9(500,325,'ProfReferences_f9cityaction.action');
 }
</script>

