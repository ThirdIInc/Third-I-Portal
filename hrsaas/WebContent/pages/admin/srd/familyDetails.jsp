<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<style type="text/css">
textarea {
	width: 95%;
}
</style>
<s:form action="FamilyDetails" validate="true" id="paraFrm" theme="simple" target="main">
	<s:hidden name="editFlag" />
	<s:hidden name="editDetail" />
	<s:hidden name="paracode" />
	<s:hidden name="empID" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />

	<fieldset><legend class="legend"> <img
		src="../pages/mypage/images/icons/profile_familyinfo.png" />
	&nbsp;&nbsp;Family Information </legend>
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
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="18%">|</td>
												</s:if>
												<s:elseif test="updateFlg">
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="18%">|</td>
												</s:elseif>
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
												<s:if test="insertFlg">
													<td width="71%"><a href="#"><img
														src="../pages/mypage/images/icons/add.png"
														onclick="addFun()" width="10" height="10" border="0" /></a></td>
													<td width="8%"><a href="#" onclick="addFun()"
														class="iconbutton">Add</a></td>
											<td width="3%">|</td>
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
								<!-- Edit Detail Starts here -->
								<s:if test="editDetail">
									<tr>
										<td colspan="11">
										<fieldset><legend class="legend1"> Family
										Particulars </legend>
										<table width="100%" border="0" align="center" cellpadding="0"
											cellspacing="2">
											<tr>
												<td width="22%"><label name="first.name"
													id="first.name" ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
												</td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="22%"><s:textfield size="25" name="firstName"
													maxlength="20" onkeypress=" return charactersOnly();" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="photo" id="photo"
													ondblclick="callShowDiv(this);"><%=label.get("photo")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" rowspan="3">
												<table width="50" height="50" border="0" cellpadding="0"
													cellspacing="0" class="borderPhoto">
													<tr>
														<td bgcolor="#FFFFFF">
														<s:if test="%{famDetail.flag}">
															<img src="../pages/images/employee/NoImage.jpg "
																height="50" width="50" />
														</s:if>
													    <s:else>

															<%
															String str = (String) request.getAttribute("familyPhoto");
															%>
															<%
															if (str.equals("NoImage.jpg")) {
															%>
															<img src="../pages/images/employee/NoImage.jpg"
																height="50" width="50" />


															<%
															} else {
															%>

															<img
																src="../pages/images/<%=session.getAttribute("session_pool") %>/family/<%=str %>"
																height="50" width="50" />
															<%
															}
															%>
														</s:else></td>
													</tr>
												</table>
												</td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="middle.name"
													id="middle.name" ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label>
												</td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="middleName" maxlength="20"
													onkeypress=" return charactersOnly();" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%">&nbsp;</td>
												<td width="1%">&nbsp;</td>
												<td width="1%">&nbsp;</td>
												<td width="22%"></td>
												<td width="5%"></td>

											</tr>
											<tr>
												<td width="20%"><label name="last.name" id="last.name"
													ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="lastName" maxlength="20"
													onkeypress=" return charactersOnly();" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%">&nbsp;</td>
												<td width="1%">&nbsp;</td>
												<td width="1%">&nbsp;</td>
												<td width="22%"></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="relation" id="relation"
													ondblclick="callShowDiv(this);"><%=label.get("relation")%></label></td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="relName" readonly="true" /> <s:hidden name="relCode" />
												</td>
												<td width="2%"><input name="relation" type="button"
													class="button" value="..."
													onclick="javascript:callsF9(500,325,'FamilyDetails_f9relaction.action');"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="upload.photo"
													id="upload.photo" ondblclick="callShowDiv(this);"><%=label.get("upload.photo")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="uploadFileName" readonly="true" /></td>
												<td width="5%"><input name="Browse" type="button"
													class="token" value="Upload"
													onclick="uploadFile('uploadFileName');" /></td>
											</tr>
											<tr>
												<td width="20%"><label name="marital.status"
													id="marital.status" ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:select
													name="maritalStatus" list="marriagemap" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="se" id="se"
													ondblclick="callShowDiv(this);"><%=label.get("se")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:select name="sex"
													list="assetmap" size="1" /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="email" id="email"
													ondblclick="callShowDiv(this);"><%=label.get("email")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="email" maxlength="45" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="phone" id="phone"
													ondblclick="callShowDiv(this);"><%=label.get("phone")%></label>
												</td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="phone" onkeypress="return validatePhoneNo()"
													maxlength="15" /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="bday" id="bday"
													ondblclick="callShowDiv(this);"><%=label.get("bday")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="birthday"  /></td>
												<td width="2%"><s:a
													href="javascript:NewCal('paraFrm_birthday','DDMMYYYY');">
													<img src="../pages/images/recruitment/Date.gif"
														class="iconImage" height="16" align="absmiddle" width="16">
												</s:a></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="profession"
													id="profession" ondblclick="callShowDiv(this);"><%=label.get("profession")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="profession" onkeypress="return charactersOnly()"
													maxlength="150" /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="identification"
													id="identification" ondblclick="callShowDiv(this);"><%=label.get("identification")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:textfield size="25"
													name="identification" maxlength="50" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="isAlive" id="isAlive"
													ondblclick="callShowDiv(this);"><%=label.get("isAlive")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:select name="alive"
													list="#{'N':'No','Y':'Yes'}" onchange="callIsAlive()" /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="employmentStatus"
													id="employmentStatus" ondblclick="callShowDiv(this);"><%=label.get("employmentStatus")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:select
													name="employmentSts"
													list="#{'E':'Employed','U':' Unemployed '}" /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="isdependent"
													id="isdependent" ondblclick="callShowDiv(this);"><%=label.get("isdependent")%></label></td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1"><s:select
													name="dependent" list="#{'N':'No','Y':'Yes'}" /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20"><label name="address" id="address"
													ondblclick="callShowDiv(this);"><%=label.get("address")%></label><br />
												</td>
												<td width="1%">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1" title="(Max.chars:255)"><s:textarea
													rows="2" cols="18" name="address"  onkeyup="return callLength('address','addCnt',255)"
													 /></td>
												<td width="2%"></td>
												<td width="9%">&nbsp;</td>
												<td width="20%"><label name="otherinform" 
													id="otherinform" ondblclick="callShowDiv(this);"><%=label.get("otherinform")%></label><br />
												<span class="formsmalltext"></span></td>
												<td width="1%" class="star">&nbsp;</td>
												<td width="1%">:</td>
												<td width="22%" class="text1" title="(Max.chars:300)"><s:textarea 
													rows="2" cols="18" name="otherInfo" 
													onkeyup="return callLength('otherInfo','infoCnt',300)"  /></td>
												<td width="5%"></td>
											</tr>
											<tr>
												<td width="20%"></td>
												<td width="1%"></td>
												<td width="1%">&nbsp;</td>
												<td width="22%" align="right"><label><font size="0.5">Remaining Chars:</font></label><s:textfield name="addCnt"
													readonly="true" size="1"></s:textfield></td>
											<td width="2%"></td>
											<td width="9%">&nbsp;</td>
											<td width="20%"></td>
											<td width="1%"></td>
											<td width="1%"></td>		
											<td width="22%" align="right"> <label><font size="0.5">Remaining Chars:</font></label><s:textfield name="infoCnt"
													readonly="true" size="1"></s:textfield></td>	
											<td width="5%"></td>	
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
								</s:if>

								<!-- Edit Details Ends Here -->

								<tr>
									<td width="98%">
									<fieldset><legend class="legend1"> Family
									Details </legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="2" class="border">

												<tr class="sortableTD">
													<td colspan="9"><s:iterator value="famDetail.famList"
														status="stat">
														<tr>
															<td width="99%" height="20" colspan="3" bgcolor="#EEF4FB">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="100%" height="20">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td width="94%">
																			<s:hidden name="familyCode" />
																			<s:if test="spouse_photo=='NoImage.jpg'">
																			<img
																				src="../pages/images/employee/NoImage.jpg"
																				height="25" width="25" />
																			</s:if>
																			<s:else>
																			<img
																				src="../pages/images/<%=session.getAttribute("session_pool") %>/family/<s:property value="spouse_photo"/>"
																				height="25" width="25" />
																			</s:else> <s:property
																				value="firstName" />-<s:property value="relName" /></td>
																			<td align="right"><s:if test="updateFlg">
																				<img src="../pages/mypage/images/icons/edit.png"
																					width="10" height="10" border="0"
																					onclick="callForEdit('<s:property value="familyCode"/>')" />
																			 |</s:if> <s:if test="deleteFlg">
																				<img src="../pages/mypage/images/icons/delete.png"
																					width="10" height="10" border="0"
																					onclick="callDelete('<s:property value="familyCode"/>')" />
																			</s:if></td>
																		</tr>
																	</table>
																	</td>
																	<td width="20%">&nbsp;</td>
																</tr>
															</table>
															</td>
														</tr>

														<tr>
															<td colspan="3" class="labeltext">
															<table width="99%" border="0" align="center"
																cellpadding="2" cellspacing="2">
																<tr>
																	<td><label name="marital.status"
																		id="marital.status" ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label></td>
																	<td class="star">&nbsp;</td>
																	<td width="1%">:</td>
																	<td width="34%" class="text1"><label><s:property
																		value="maritalStatus" /></label></td>
																	<td>&nbsp;</td>
																	<td width="15%"><label name="phone" id="phone"
																		ondblclick="callShowDiv(this);"><%=label.get("phone")%></label></td>
																	<td class="star">&nbsp;</td>
																	<td>:</td>
																	<td width="34%" valign="top" class="text1"><s:property
																		value="phone" /></td>
																</tr>
																<tr>
																	<td width="15%"><label name="se" id="se"
																		ondblclick="callShowDiv(this);"><%=label.get("se")%></label>
																	</td>
																	<td width="1%">&nbsp;</td>
																	<td>:</td>
																	<td class="text1"><s:property value="sex" /></td>
																	<td>&nbsp;</td>
																	<td><label name="profession" id="profession"
																		ondblclick="callShowDiv(this);"><%=label.get("profession")%></label>
																	</td>
																	<td width="1%" class="star">&nbsp;</td>
																	<td width="1%">:</td>
																	<td valign="top" class="text1" title="<s:property
																		value="profession" />"><s:property
																		value="abbrProfession" /></td>
																</tr>
																<tr>
																	<td><label name="email" id="email"
																		ondblclick="callShowDiv(this);"><%=label.get("email")%></label>
																	</td>
																	<td>&nbsp;</td>
																	<td>:</td>
																	<td class="text1" title="<s:property value="email" />"><s:property value="abbrEmail" /></td>
																	<td>&nbsp;</td>
																	<td><label name="identification" id="identification" ondblclick="callShowDiv(this);"><%=label.get("identification")%></label></td>
																	<td class="star">&nbsp;</td>
																	<td>:</td>
																	<td valign="top" class="text1" title="<s:property value="identification" />" >
																	<s:property value="abbrIdentification" /></td>
																</tr>
																<tr>
																	<td><label name="bday" id="bday"
																		ondblclick="callShowDiv(this);"><%=label.get("bday")%></label></td>
																	<td>&nbsp;</td>
																	<td>:</td>
																	<td class="text1"><s:property value="birthday" /></td>
																	<td>&nbsp;</td>
																	<td><label name="isAlive" id="isAlive"
																		ondblclick="callShowDiv(this);"><%=label.get("isAlive")%></label></td>
																	<td class="star">&nbsp;</td>
																	<td>:</td>
																	<td valign="top" class="text1"><s:property
																		value="alive" /></td>
																</tr>
																<tr>
																	<td width="15%"><label name="address" id="address"
																		ondblclick="callShowDiv(this);"><%=label.get("address")%></label></td>
																	<td width="1%">&nbsp;</td>
																	<td>:</td>
																	<td rowspan="2" valign="top" class="text1" title="<s:property value="address" />">
																	<s:property value="abbrAddress" /></td>
																	<td>&nbsp;</td>
																	<td><label name="otherinform" id="otherinform"
																		ondblclick="callShowDiv(this);"><%=label.get("otherinform")%></td>
																	<td class="star">&nbsp;</td>
																	<td>:</td>
																	<td rowspan="2" valign="top" class="text1" title="<s:property value="otherInfo" />"><s:property value="abbrOtherInfo" /></td>
																</tr>
																<tr>
																	<td width="15%">&nbsp;</td>
																	<td width="1%">&nbsp;</td>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td width="1%" class="star">&nbsp;</td>
																	<td width="1%">&nbsp;</td>
																</tr>

															</table>
															</td>
														</tr>
													</s:iterator></td>
												</tr>

												<s:if test="noData">
													<tr>
														<td align="center"><font color="red">No data
														to display</font></td>
													</tr>
												</s:if>
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
								<s:if test="editFlag">

									<tr align="right">
										<s:if test="insertFlg">
											<td width="18%"><a href="#"><img
												src="../pages/mypage/images/icons/save.png"
												onclick="saveFun()" width="10" height="10" border="0" /></a></td>
											<td width="18%"><a href="#" onclick="saveFun()"
												class="iconbutton">Save</a></td>
										<td width="18%">|</td>
										</s:if>
										<s:elseif test="updateFlg">
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="18%">|</td>
												</s:elseif>
										
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
										<s:if test="insertFlg">
											<td width="71%"><a href="#"><img
												src="../pages/mypage/images/icons/add.png"
												onclick="addFun()" width="10" height="10" border="0" /></a></td>
											<td width="8%"><a href="#" onclick="addFun()"
												class="iconbutton">Add</a></td>
											<td width="3%">|</td>
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
	</fieldset>
</s:form>



<SCRIPT>
function addFun(){

  document.getElementById('paraFrm').action='FamilyDetails_add.action';
   //document.getElementById('selectFlag').value=="true";
   document.getElementById('paraFrm').submit();
}

function saveFun(){
  	try{
  	var fname=document.getElementById('paraFrm_firstName').value;
   	var relName=document.getElementById('paraFrm_relName').value;
   	var email=document.getElementById('paraFrm_email').value;
   	var bd=document.getElementById('paraFrm_birthday').value;
   	var add=document.getElementById('paraFrm_addCnt').value;
   	var address=document.getElementById('paraFrm_address').value;
   	var otherInfo=document.getElementById('paraFrm_otherInfo').value;
   	var info=document.getElementById('paraFrm_infoCnt').value;
   	if(fname==""){
   	alert("Please Enter "+document.getElementById('first.name').innerHTML.toLowerCase());
   	return false;
   	} 
   	if(relName==""){
   		alert("Please Select "+document.getElementById('relation').innerHTML.toLowerCase());
   		return false;
   	}
   	if(!email==""){
		if(!validateEmail('paraFrm_email')){
	 	 	return false;
		}
	}	
	  	
		if(!(bd=="")) {
		   var fld=['paraFrm_birthday'];
		   var lbl=['bday'];
		   var chkDb=validateDate(fld,lbl);
		   
		   if(!chkDb) {
		   	  return false;
		   }
	  }
	if(!(address=="")){
	
	if(address.length > 255){
	    alert("Maximum length of "+document.getElementById("address").innerHTML.toLowerCase()+" is 255 characters.");
		return false;
		}
	}
	
	if(!(otherInfo=="")){
	if(otherInfo.length > 300){
		alert("Maximum length of "+document.getElementById("otherinform").innerHTML.toLowerCase()+" is 300 characters.");
		return false;
	}else
	{ 
	  
	}
	}
	    document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="FamilyDetails_save.action";
		document.getElementById("paraFrm").submit();  		    
	}catch(e){
	alert(e);
    }
  }  

 function searchFun(){
   	try{
  	   javascript:callsF9(500,325,'FamilyDetails_f9empaction.action');  
  		}catch(e){
  		alert(e);  
  		}	   
 }

function cancelFun(){      
  	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="FamilyDetails_cancel.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
  }
  
function callIsAlive()
{
	var isAlive = document.getElementById('paraFrm_alive').value;
	if(isAlive =="Y")
	{
	  document.getElementById('div1').style.display='';
	}else if(isAlive=="N" || isAlive==""){
	    document.getElementById('div1').style.display='';
	    document.getElementById('paraFrm_dependent').value='N';
		document.getElementById('div1').style.display='none';
   }
}
      	
function callForEdit(id){
    document.getElementById('paraFrm_paracode').value= trim(id);

    document.getElementById("paraFrm").action="FamilyDetails_callForEdit.action";
	 //document.getElementById("paraFrm").target="main";
	  document.getElementById("paraFrm").submit();
  }
  
function callDelete(id){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
   		  		  
	   	document.getElementById("paraFrm").action="FamilyDetails_delete.action";
	  	document.getElementById('paraFrm_paracode').value=trim(id);
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
   }else {
   		return false;
   
   }
 }
 
function uploadFile(fieldName) {
	
	var path="images/<%=session.getAttribute("session_pool")%>/family";
	
		//window.open('../pages/common/uploadFile.jsp?path='+path,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 window.open('../pages/common/uploadFileHrm.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		    document.getElementById('paraFrm').target="main";
} 

function callReset() {
	document.getElementById('paraFrm').target="main";		  
	document.getElementById("paraFrm").action="FamilyDetails_reset.action";
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target="main";
}


  </SCRIPT>

