<!-- Modefied By @author Prajakta.bhandare  -->
<!-- @date 21 Jan 2013 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AddressDetails" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="editFlag" />
	<s:hidden name="empId" />
	<s:hidden name="uploadFileName" />
	<s:hidden name="newFlag"/>
	<s:hidden name="paraId"/>
	<s:hidden  name="emeFlag"/>
	<s:hidden  name="offiFlag"/>
	<s:hidden  name="localFlag"/>
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>
			<div style="float: left; width: 100%">
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<form id="form1" name="form1" method="post" action="">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="middle">
							<fieldset><legend class="legend"> <img
								src="../pages/mypage/images/icons/profile_address.png"
								width="16" height="16" /> &nbsp;&nbsp;Address and Contact</legend>
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
												<s:if test="editFlag"><!-- if edit flag -->
													<tr>
														<s:if test="insertFlg"><!-- if insert flag -->
															<td width="18%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="18%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="18%">|</td>
														</s:if><!--end if insert flag -->
														<s:elseif test="updateFlg"><!-- elseif update flag -->
															<td width="18%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="18%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="18%">|</td>
														</s:elseif><!--end elseif update flag -->
														<td width="18%"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancelFun()" width="10" height="10" /></td>
														<td width="18%"><a href="#" onclick="cancelFun()"
															class="iconbutton">Cancel</a></td>
															<td>|</td>
													</tr>
												</s:if>

												<s:else>
													<tr align="right">
														<s:if test="insertFlg"><!-- if insert flag -->
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/add.png"
																onclick="addFun()" width="10" height="10" border="0" /></a></td>
															<td width="3%"><a href="#" onclick="addFun()"
																class="iconbutton">Add</a></td>
															<td width="1%">|</td>
														</s:if><!-- if insert flag -->
														<s:if test="isNotGeneralUser"><!-- if notGeneralUser flag -->
															<td align="right" width="100%"><a href="#"><img
																src="../pages/mypage/images/icons/search.png"
																onclick="searchFun()" width="10" height="10" border="0" /></a></td>
															<td align="right"><a href="#" onclick="searchFun()"
																class="iconbutton">Search</a></td>
																<td>|</td>
														</s:if><!--end if notGeneralUser flag -->
													</tr>
												</s:else>
											</table>
											</td>
										</tr>
										<tr>
											<td height="1" bgcolor="#cccccc" class="style1"></td>
										</tr>

										<!--Display record in editable mode -->
										<s:if test="editFlag"><!-- if edit flag -->
											<tr>
												<td colspan="10">
												<fieldset><legend class="legend1">
												Address and Contact Particulars </legend>
												<table width="100%" border="0" align="center" cellpadding="2"
													cellspacing="1">
													<tr>
														<td width="24%"><label name="contact" id="contact"
															ondblclick="callShowDiv(this);"><%=label.get("contact")%></label></td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="18%"><s:select name="type"
															list="assetmap" size="1" cssStyle="width:180;z-index:5;" />
														</td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"></td>
														<td width="2%">&nbsp;</td>
														<td width="2%"></td>
														<td width="18%" valign="top"></td>
													</tr>
													<tr>
														<td width="24%"><label name="address" id="address"
															ondblclick="callShowDiv(this);"><%=label.get("address")%></label></td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="18%" class="text1"><s:textfield size="25"
															name="address1" maxlength="95" /></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="phone1" id="phone1"
															ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%" valign="top"><s:textfield size="25"
															maxlength="12" name="phone1"
															onkeypress="return validatePhoneNo()" /></td>
													</tr>
													<tr>
														<td width="24%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="18%"><s:textfield size="25"
															name="address2" maxlength="95"/></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="phone2" id="phone2"
															ondblclick="callShowDiv(this);"><%=label.get("phone2")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%" valign="top"><s:textfield size="25"
															maxlength="12" name="phone2"
															onkeypress="return validatePhoneNo()" /></td>
													</tr>
													<tr>
														<td width="24%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="18%"><s:textfield size="25"
															name="address3" maxlength="95" /></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="extension"
															id="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="2%" valign="top"><s:textfield size="25"
															maxlength="6" name="extension"
															onkeypress="return numbersOnly()" /></td>
													</tr>
													<tr>
														<td width="24%"><label name="city" id="city"
															ondblclick="callShowDiv(this);"><%=label.get("city")%></label></td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="18%"><s:hidden name="cityId" /> <s:textfield
															size="25" name="cityName" readonly="true" /></td>
														<td width="2%"><input name="Submit3632" type="button"
															class="button" value="..." onclick="cityAction()" /></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="fax.no" id="fax.no"
															ondblclick="callShowDiv(this);"><%=label.get("fax.no")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%" valign="top"><s:textfield size="25"
															maxlength="12" name="faxNo"
															onkeypress="return validatePhoneNo()" /></td>
													</tr>
													<tr>
														<td width="24%"><label name="state" id="state"
															ondblclick="callShowDiv(this);"><%=label.get("state")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%"><s:textfield size="25"
															name="stateName" onkeypress="return charactersOnly()"
															readonly="true" /></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="mobile.no"
															id="mobile.no" ondblclick="callShowDiv(this);"><%=label.get("mobile.no")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%" valign="top"><s:textfield size="25"
															maxlength="12" name="mobNo"
															onkeypress="return validateMobile()" /></td>
													</tr>
													<tr>
														<td width="24%"><label name="pincode" id="pincode"
															ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%"><s:textfield size="25" name="pinCode"
															maxlength="6" onkeypress="return numbersOnly()" /></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%"><label name="email" id="email"
															ondblclick="callShowDiv(this);"><%=label.get("email")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%"><s:textfield size="25" maxlength="45"
															name="emailId" /></td>
													</tr>
													<tr>
														<td width="2%"><label name="country" id="country"
															ondblclick="callShowDiv(this);"><%=label.get("country")%></label></td>
														<td width="2%">&nbsp;</td>
														<td width="2%">:</td>
														<td width="18%"><s:textfield size="25" name="country"
															onkeypress="return charactersonly(this)" readonly="true" />
														</td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="24%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="2%">&nbsp;</td>
														<td width="18%"></td>
													</tr>
												</table>
												</fieldset>
												</td>
											</tr>
										</s:if><!-- end if editflag -->
										<!--end of Display record in editable mode -->
											<tr>
												<td colspan="6">
												<fieldset><legend class="legend1">
												Address and Contact Details </legend>
												<table width="100%" border="0" 
													cellpadding="1" cellspacing="1">
													
													<% int count=0;
													%>
													<s:iterator value="addressList">
													<s:if test="noData"><!-- if nodata true -->
													<%if(count%3==0) {%>
													<tr>
													<%} %>
													
													<td valign="top">
													
													<table width="100%"  border="0" cellspacing="1"
															cellpadding="1">
															<tr height="10"></tr>
														<tr>
														<td width="33%" bgcolor="#EEF4FB" colspan="2">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="79%"><s:property value="emeType"/></td>
																<td width="20%"><s:hidden name="offiType"/>
																<s:if test="emeFlag">
																<div align="right"><s:if test="updateFlg">
																	<img src="../pages/mypage/images/icons/edit.png"
																		width="10" height="10"
																		onclick="callForEdit('<s:property value="offiType"/>')" />
																| </s:if> <s:if test="deleteFlg">
																	<img src="../pages/mypage/images/icons/delete.png"
																		width="10" height="10"
																		onclick="callDelete('<s:property value="offiType" />')" />
																</s:if></div>
																</s:if>
																</td>
															</tr>
														</table>
														</td>
													</tr>
													
													<tr>
														<td class="text1"><label name="address" id="address"
															ondblclick="callShowDiv(this);"><%=label.get("address")%></label>:</td>
														<td class="text1" title="<s:property value="emeAddress" />"><s:property value="abbrEmeAddress" /><s:hidden
															name="emeAddress" /></td>
														
													</tr>
													<tr>
														<td class="text1"></td>
														<td class="text1"><s:property value="emeCity" /></td>
														
													</tr>
													<tr>

														<td class="text1"></td>
														<td class="text1"><s:property value="emeState" /></td>
													
													</tr>
													<tr>
														<td class="text1"></td>
														<td class="text1"><s:property value="emePinCode" /></td>
														
													</tr>
													<tr>
														<td class="text1"></td>
														<td class="text1"><s:property value="emeCountry" /></td>
														
													</tr>
													<tr>
														<td class="text1"><label name="phone.no"
															id="phone.no" ondblclick="callShowDiv(this);"><%=label.get("phone.no")%></label>:</td>
														<td class="text1"><s:property value="emePhoneNum" /></td>
														
													</tr>
													<tr>
														<td class="text1"><label name="extension"
															id="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label>:</td>
														<td class="text1"><s:property value="emeExtNum" /></td>
														
													</tr>
													<tr>
														<td class="text1"><label name="fax.no" id="fax.no"
															ondblclick="callShowDiv(this);"><%=label.get("fax.no")%></label>:</td>
														<td class="text1"><s:property value="emeFaxNum" /></td>
														
													</tr>
													<tr>
														<td class="text1"><label name="mobile.no"
															id="mobile.no" ondblclick="callShowDiv(this);"><%=label.get("mobile.no")%></label>:</td>
														<td class="text1"><s:property value="emeMobileNum" /></td>
														
													</tr>
													<tr>
														<td class="text1"><label name="email" id="email"
															ondblclick="callShowDiv(this);"><%=label.get("email")%></label>:</td>
														<td class="text1" title="<s:property value="emeEmail" />"><s:property value="abbrEmeEmail" /></td>
														
													</tr>
													
													</table>
													</td>
													<%if(count%3==2) {%>
													</tr>
													<%}else{%>
													<% int counter = (Integer) request.getAttribute("count"); ;
													if(count==counter){%>
														</tr>
													<% }
													} %>
													<%count++; %>
													</s:if><!-- end if nodata true -->
													</s:iterator>
													
												</table>
												</fieldset>
												</td>
											</tr>
										
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td height="1px" bgcolor="#cccccc"></td>
										</tr>
										<tr>
											<td height="0">
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">
												<s:if test="editFlag"><!-- if edit flag -->
													<tr>
														<s:if test="insertFlg"><!-- if insert flag -->
															<td width="18%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="18%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="18%">|</td>
														</s:if><!--end if insert flag -->
														<s:elseif test="updateFlg"><!-- elseif update flag -->
															<td width="18%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="18%"><a href="#" onclick="saveFun()"
																class="iconbutton">Save</a></td>
															<td width="18%">|</td>
														</s:elseif><!--end elseif update flag -->
														<td width="18%"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancelFun()" width="10" height="10" /></td>
														<td width="18%"><a href="#" onclick="cancelFun()"
															class="iconbutton">Cancel</a></td>
															<td>|</td>
													</tr>
												</s:if><!--end  if edit flag -->

												<s:else>
													<tr align="right">
														<s:if test="insertFlg"><!-- if insert flag -->
															<td width="93%"><a href="#"><img
																src="../pages/mypage/images/icons/add.png"
																onclick="addFun()" width="10" height="10" border="0" /></a></td>
															<td width="3%"><a href="#" onclick="addFun()"
																class="iconbutton">Add</a></td>
															<td width="1%">|</td>
														</s:if><!--end if insert flag -->
														<s:if test="isNotGeneralUser"><!-- if notGeneralUser flag -->
															<td align="right" width="100%"><a href="#"><img
																src="../pages/mypage/images/icons/search.png"
																onclick="searchFun()" width="10" height="10" border="0" /></a></td>
															<td align="right"><a href="#" onclick="searchFun()"
																class="iconbutton">Search</a></td>
																<td>|</td>
														</s:if><!--end if notGeneralUser flag -->
													</tr>
												</s:else>
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
					</form>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>

</s:form>
<script>
myOnload();
//function gets called when jsp loads
function myOnload(){
	var  flag= document.getElementById("paraFrm_newFlag").value;
	if(flag=="true"){
		document.getElementById("paraFrm_type").disabled=true;
	}
}

//function to save record
function saveFun() {
try{
	if(document.getElementById('paraFrm_type').value==""){//if type null
	alert("Please select "+document.getElementById('contact').innerHTML.toLowerCase());
	document.getElementById('paraFrm_type').focus();
	return false;
	}//end if type null
	 
  	if((trim((document.getElementById('paraFrm_address1')).value)=="")&&(trim((document.getElementById('paraFrm_address2')).value)=="")&&(trim((document.getElementById('paraFrm_address3')).value)=="")){//if address null
  	alert("Please enter "+document.getElementById('address').innerHTML.toLowerCase());
  	document.getElementById('paraFrm_address1').focus();
  	return false;
  	}//end if address null		
  	
  	if(document.getElementById('paraFrm_cityName').value==""){//if city null
  	alert("Please select "+document.getElementById('city').innerHTML.toLowerCase());
  	document.getElementById('paraFrm_cityName').focus();
  	return false;
  	}//end if city null
  	if(document.getElementById('paraFrm_emailId').value!=""){//if email id not null
  	if(!(validateEmail('paraFrm_emailId'))){//if validate email
  	document.getElementById('paraFrm_emailId').focus();
  	return false;
  	}//end if validate email
  	}//end if emailid not null
  	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="AddressDetails_save.action";	
	document.getElementById('paraFrm').submit();
			
  	}catch(e){
  		alert(e);
  	}
  	
}

//function to adit particular address
function callForEdit(id){
    try{
    document.getElementById('paraFrm_paraId').value=id;
    document.getElementById("paraFrm").action="AddressDetails_edit.action";
   	document.getElementById("paraFrm").submit();
   	}catch(e){alert(e);}
}
//function to delete particular address
function callDelete(id){
	 document.getElementById('paraFrm_paraId').value=id;
	 var conf=confirm("Are you sure to delete this record?");
	 if(conf) {//if conf true
	   	document.getElementById("paraFrm").action="AddressDetails_delete.action";
		document.getElementById("paraFrm").submit();
	 }//end if conf true
	 else {
		return false;	
	 }//end of else
}

//function to add address
function addFun(){
	document.getElementById("paraFrm").action="AddressDetails_add.action";
	document.getElementById("paraFrm").submit();
}
//function to search city
function cityAction(){
	javascript:callsF9(500,325,'AddressDetails_f9cityaction.action');
}
//function to search employee
function searchFun(){
	javascript:callsF9(500,325,'AddressDetails_f9action.action');
}

//function to cancel current operation
function cancelFun(){
	document.getElementById("paraFrm").action="AddressDetails_cancelFunc.action";
	document.getElementById("paraFrm").submit();
}	
 </script>
