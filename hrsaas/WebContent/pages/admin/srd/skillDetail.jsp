<!--@author prajakta B-->
<!--@ date 2 Jan 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="SkillDetails" validate="true" id="paraFrm"
	theme="simple" target="main">
	<s:hidden name="editDetail" />
	<s:hidden name="editFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="paracode" />
	<s:hidden name="empId"/>
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<fieldset><legend class="legend"> <img
				src="../pages/mypage/images/icons/profile_documentsubmit.png" />
			&nbsp;&nbsp;Skills Information </legend>
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
												<td colspan="13">
												<fieldset><legend class="legend1">Skill
												Particulars</legend>
												<table width="100%" border="0" align="center"
													cellpadding="2" cellspacing="1">
													<tr>
														<td width="20%"><label class="set" name="skilltype" id="skilltype"
															ondblclick="callShowDiv(this);"><%=label.get("skilltype")%></label>
														</td>
														<td width="2%"></td>
														<td width="2%">:</td>
														<td width="17%"><s:select theme="simple"
															name="skilltype" cssStyle="width:130"
															list="#{'P':'Primary','S':'Secondary','T':'Tertiary'}" />
														</td>
														<td width="4%"></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;</td>
														<td width="2%"></td>
														<td width="20%"></td>
														<td width="2%"></td>
														<td width="2%"></td>
														<td width="17%"></td>
														<td width="4%"></td>
													</tr>
													<tr>
														<td width="20%"><label name="skill" id="skill"
															ondblclick="callShowDiv(this);"><%=label.get("skill")%></label>
														</td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td><s:hidden name="skillValue"/>
														<td width="17%"><s:textfield readonly="true" name="skillName" size="25"  />
														</td>
														<td width="4%"><input name="sName" type="button"
															class="button" value="..."
															onclick="javascript:getSkill();"></td>
														<td width="2%"></td>
														<td width="6%">&nbsp;<label>(OR)</label></td>
														<td width="2%"></td>
														<td width="20%"><label name="otherskill"
															id="otherskill" ondblclick="callShowDiv(this);"><%=label.get("otherskill")%></label></td>
														<td width="2%" class="star">*</td>
														<td width="2%">:</td>
														<td width="17"><s:textfield name="otherSkill"
															size="25" maxlength="150" /></td>
														<td width="4%"></td>
													</tr>
													<tr>
														<td width="20%"><label class="set" name="level" id="level"
															ondblclick="callShowDiv(this);"><%=label.get("level")%></label></td>
														<td width="2%"></td>
														<td width="2%"></td>
														<td width="17%"><s:select theme="simple" name="level"
															cssStyle="width:60"
															list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
														<td width="4%"></td>
														<td width="2%"></td>
														<td width="6%"></td>
														<td width="2%"></td>
														<td width="20%"><label class="set" id="duration"
															name="duration" ondblclick="callShowDiv(this);"><%=label.get("duration")%></label></td>
														<td width="2%"></td>
														<td width="2%">:</td>
														<td width="17%"><s:textfield name="duration" size="25"
															onkeypress="return numbersOnly();" maxLength="3" /></td>
														<td width="4%"><s:select
															theme="simple" name="durationType" cssStyle="width:130"
															list="#{'D':'Day(s)','M':'Month(s)','Y':'Year(s)'}" /></td>
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
													<td colspan="7">
													<fieldset><legend class="legend1">Skill
												Details</legend>
													<table width="100%" border="0" cellpadding="2"
														cellspacing="1" class="sortable" align="center">
														<tr bgcolor="#EEF4FB">
															<td width="3%" align="center"><label id="sr.no" name="sr.no" 
																ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
															<td width="18%" align="center"><label
																name="skilltype" id="skilltype"
																ondblclick="callShowDiv(this);"><%=label.get("skilltype")%></label></td>
															<td width="18%" align="center"><label
																name="skill" id="skill" ondblclick="callShowDiv(this);"><%=label.get("skill")%></label></td>
															<td width="18%" align="center"><label
																name="otherskill" id="otherskill"
																ondblclick="callShowDiv(this);"><%=label.get("otherskill")%></label></td>
															<td  width="18%" align="center"><label
																name="level" id="level" ondblclick="callShowDiv(this);"><%=label.get("level")%></label></td>
															<td width="18" align="center"><label
																name="duration" id="duration"
																ondblclick="callShowDiv(this);"><%=label.get("duration")%></label></td>
															<td width="7%" align="center">Edit|Delete</td>
														</tr>
														<%!int loopCount = 0;%>
														<%
														int n = 0;
														%>
														<s:iterator value="List">
															<tr>
																<td width="3%" class="text1" align="left"><s:hidden
																	name="skillId"/><%=++n%></td>

																<td width="18%" align="left" class="text1"
																	nowrap="nowrap">&nbsp; <s:property
																	value="skilltype" /></td>

																<td width="18%" align="left" class="text1"
																	nowrap="nowrap" title="<s:property
																	value="skillName" />">&nbsp; <s:property
																	value="abbrSkill" /></td>

																<td width="18%" align="left" class="text1"
																	nowrap="nowrap" title="<s:property
																	value="otherSkill" />">&nbsp;<s:property
																	value="abbrOtherSkill" /> </td>


																<td width="15%" align="center" class="text1"
																	nowrap="nowrap">&nbsp; <s:property
																	value="level" /></td>

																<td width="15%" align="left" class="text1"
																	nowrap="nowrap">&nbsp; <s:property
																	value="duration" /> <s:property
																	value="durationType" /></td>
																<td  width="13%" class="text1" align="center"><s:if test="updateFlg">
																	<img src="../pages/mypage/images/icons/edit.png"
																		width="10" height="10" border="0"
																		onclick="callForEdit('<s:property value="skillId"/>')" />
																		|</s:if> <s:if test="deleteFlg">
																	<img src="../pages/mypage/images/icons/delete.png"
																		width="10" height="10" border="0"
																		onclick="callForDelete('<s:property value="skillId"/>')" />
																</s:if></td>
															</tr>
															<input type="hidden" name="count" id="count"
															value="<%=n%>" />
														</s:iterator>
														<s:if test="noData">
													<tr align="center">
														<td align="center" colspan="7"><font color="red">No data
														to display</font></td>
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
function saveFun()
{
try{
	var Skill=trim(document.getElementById('paraFrm_skillName').value);
	var otherSkill=trim(document.getElementById('paraFrm_otherSkill').value);
	otherSkill = trim(otherSkill);
	if(Skill=="" && otherSkill==""){
		alert("Please Select/Enter Skill");
		return false;
	}
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="SkillDetails_save.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function getSkill(){
	try{
 	callsF9(500,325,'SkillDetails_f9actionSkill.action');
 	}catch(e){alert(e);}
}

function cancelFun(){
try{
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="SkillDetails_cancel.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function addFun(){
try{
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="SkillDetails_add.action";
	document.getElementById("paraFrm").submit();
}catch(e){alert(e);}
}

function searchFun(){
try{
	 javascript:callsF9(500,325,'SkillDetails_f9action.action');
}catch(e){alert(e);}
}
function callForEdit(id)
   {
	document.getElementById('paraFrm_paracode').value=id;
	document.getElementById("paraFrm").action="SkillDetails_edit.action";
	document.getElementById("paraFrm").submit();
}
 function callForDelete(id)
  {
  	document.getElementById('paraFrm_paracode').value=id;
    var conf=confirm("Are you sure to delete this record?");
   	if(conf) {
   	   	document.getElementById("paraFrm").action="SkillDetails_deleteSkillDetails.action";
    	document.getElementById("paraFrm").submit();
              } 
   }
</script>