<!--Updated By Prajakta B-->
<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<style type="text/css">
textarea {
	width: 98%;
}
</style>
<s:form action="PersonnelDetail" validate="true" id="paraFrm"
	 theme="simple" target="main">
	<s:hidden name="checkTabFlag" />
	<s:hidden name="editFlag" />
	<s:hidden name="readFlag" />
	<s:hidden name="writeFlag" />
	<s:hidden name="speakFlag" />
	<s:hidden name="motherFlag" />
	<s:hidden name="paraId" />
	<s:hidden name="employeeId" />
	<s:hidden name="langEditFlag" id="langEditFlag" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="uploadFileName" />
	<s:hidden name="empName" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"><img
						src="../pages/mypage/images/icons/profile_personalinfo.png"
						width="16" height="16" /> &nbsp;&nbsp;Personal Information </legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								
								<tr>
									<td  width="100%">
									<table width="100%" border="0" align="right" cellpadding="2"
										cellspacing="3">
											<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if>
												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td width="1%">|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="updateFlg">
													<td width="95%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													<td align="right" width="100%"><a href="#"><img
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
								<s:if test="editFlag">
								<tr><!-- -->
								<td colspan="11">
									<fieldset><legend class="legend1">Personal
									Details</legend>
									<table width="98%" border="0" align="center" cellpadding="2"
										cellspacing="1">
										<tr>
											<td width="22%"><label name="nation" id="nation"
												ondblclick="callShowDiv(this);"><%=label.get("nation")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield 
												
												name="nationality" size="28" id="nationality" onkeypress="return charactersOnly()" /></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="blood.group"
												id="blood.group" ondblclick="callShowDiv(this);"><%=label.get("blood.group")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:select
												 name="bloodGroup"
												headerKey="" headerValue="----select----"
												cssStyle="width:154" list="bgmap" /></td>
											<td width="2%"></td>
										</tr>
										<tr>
											<td width="22%"><label name="religion" id="religion"
												ondblclick="callShowDiv(this);"><%=label.get("religion")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:hidden name="religionCode" />
											<s:textfield 
												name="religion" size="28" readonly="true" /></td>
											<td width="2%"><input type="button" name="Submit3"
												class="button" value="..." onclick="religionAction()" /></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="caste.category"
												id="caste.category" ondblclick="callShowDiv(this);"><%=label.get("caste.category")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:hidden
												name="castCategoryCode" /> <s:textfield
												
												name="castCategory" size="28" readonly="true" /></td>
											<td width="2%"><input type="button" name="Submit3"
												class="button" value="..." onclick="categoryAction()" /></td>
										</tr>
										<tr>
											<td width="22%"><label name="caste" id="caste"
												ondblclick="callShowDiv(this);"><%=label.get("caste")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:hidden name="castCode" />
											<s:textfield 
												name="castName" size="28" readonly="true" /></td>
											<td width="2%"><input type="button" name="Submit3"
												class="button" value="..." onclick="catNameAction()" /></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="subCaste" id="subCaste"
												ondblclick="callShowDiv(this);"><%=label.get("subCaste")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield
												 name="subCast"
												size="28" onkeypress="return charactersOnly()" maxlength="20" /></td>
											<td width="2%"></td>
										</tr>
										<tr>
											<td width="22%"><label name="weight" id="weight"
												ondblclick="callShowDiv(this);"><%=label.get("weight")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield
												 name="weight"
												size="28" onkeypress="return numbersWithDot()" maxlength="6" /></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="height" id="height"
												ondblclick="callShowDiv(this);"><%=label.get("height")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield
												 name="height"
												size="28" onkeypress="return numbersWithDot()" maxlength="6" /></td>
											<td width="2%"></td>
										</tr>
										<tr>
											<td width="22%"><label name="is.handicap"
												id="is.handicap" ondblclick="callShowDiv(this);"><%=label.get("is.handicap")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:select name="isHandiCap"
												headerKey="" headerValue="----Select----"
												cssStyle="width:166" list="#{'Y':'Yes','N':'No'}"
												onchange="callHandicap();" /></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="handicap.desc"
												id="handicap.desc" ondblclick="callShowDiv(this);"><%=label.get("handicap.desc")%></label>
											</td>
											<td width="2%">&nbsp;<font color="red">*</font></td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield name="desc"
												 size="28"
												onkeypress="return charactersOnly()" maxlength="150" disabled="false"/></td>
											<td width="2%"></td>
										</tr>
										<tr>
											<td width="22%"><label name="marital.status"
												id="marital.status" ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:select
												name="maritalStatus" headerKey=""
												headerValue="----select----" cssStyle="width:166"
												list="assetmap" onclick="callMarriage()"/></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="marriage.date"
												id="marriage.date" ondblclick="callShowDiv(this);"><%=label.get("marriage.date")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield
												 disabled="false"
												name="marriageDate" size="28" 
												onkeypress="return numbersWithHiphen();"
												onblur="return validateDate('paraFrm_marriageDate','marriage.date');"
												maxlength="10" /></td>
											<td width="2%" id="imageCal"><s:a
												href="javascript:NewCal('paraFrm_marriageDate','DDMMYYYY');" >
												<img class="iconImage" class="iconImage"  
													src="../pages/images/recruitment/Date.gif" 
													class="iconImage" height="16" align="absmiddle" width="16">
											</s:a></td>
										</tr>
										<tr>
											<td width="22%"><label name="hobbies" id="hobbies"
												ondblclick="callShowDiv(this);"><%=label.get("hobbies")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textarea
												theme="simple" cols="25"
												rows="3" name="hobbies" onkeyup="return callLength('hobbies','hobCnt',100)" /></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="id.mark" id="id.mark"
												ondblclick="callShowDiv(this);"><%=label.get("id.mark")%></label>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textarea label="%{getText('markId')}"
												theme="simple" cols="25" rows="3" name="markId"
												onkeyup="return callLength('markId','idCnt',255)" /></td>
											<td width="2%"></td>
										</tr>
											<tr>
												<td width="22%"></td>
												<td width="2%"></td>
												<td width="2%">&nbsp;</td>
												<td width="17%" align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_hobbies','hobbies','','paraFrm_hobCnt','100');"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="hobCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
												<td width="10%">&nbsp;</td>
												<td width="22%"></td>
												<td width="2%"></td>
												<td width="2%"></td>
												<td width="17%"  align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_markId','id.mark','','paraFrm_idCnt','255');"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="idCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
											</tr>
											<tr>
											<td width="22%"><label name="hometown" id="hometown"
												ondblclick="callShowDiv(this);"><%=label.get("hometown")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textfield
												 name="homeTown"
												size="28" readonly="true" /> <s:hidden
												
												name="homeTownCode" /></td>
											<td width="2%"><input type="button" name="Submit3"
												class="button" value="..." onclick="homeTownAction()" /></td>
											<td width="10%">&nbsp;</td>
											<td width="22%">&nbsp;</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">&nbsp;</td>
											<td width="17%">&nbsp;</td>
											<td width="2%"></td>
										</tr>
										<tr>
											<td width="22%"><label name="conf4" id="conf4"
												ondblclick="callShowDiv(this);"><%=label.get("conf4")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:select name="isConvicted"
												headerKey="" headerValue="--Select--" cssStyle="width:166"
												list="#{'Y':'Yes','N':'No'}" onchange="callisConvicted();" /></td>
											<td width="2%"></td>
											<td width="10%">&nbsp;</td>
											<td width="22%"><label name="conf5" id="conf5"
												ondblclick="callShowDiv(this);"><%=label.get("conf5")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="2%">:</td>
											<td width="17%"><s:textarea theme="simple" cols="25"
												rows="3" name="criminalActs" onkeyup="return callLength('criminalActs','crmCnt',300)"/></td>
											<td width="2%"></td>
										</tr>
										<tr>
												<td width="22%"></td>
												<td width="2%"></td>
												<td width="2%">&nbsp;</td>
												<td width="17%"></td>
												<td width="2%"></td>
												<td width="10%">&nbsp;</td>
												<td width="22%"></td>
												<td width="2%"></td>
												<td width="2%"></td>
												<td width="17%"  align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_criminalActs','conf5','','paraFrm_crmCnt','300');"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="crmCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
											</tr>
									</table>
									</fieldset>
									</td>
									</tr>
								<tr>
									<td colspan="11">
									<fieldset><legend class="legend1"> Medical
									Details</legend>
									<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
										<tr>
											<td width="13%"><label
												name="conf1" id="conf1" ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label>
											</td>
											<td width="2%">&nbsp;</td>
											<td width="1%">:</td>
										<td width="23%"><s:textarea
												theme="simple" cols="25"  rows="3" name="ailments" onkeyup="return callLength('ailments','ailCnt',300)"/></td>
											<td width="2%"></td>
											<td width="5%">&nbsp;</td>
											<td width="11%" align="left"><label name="conf2" id="conf2"
												ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label></td>
											<td width="2%"></td>
											<td width="1%">:</td>
											<td width="23%" align="left"><s:textarea theme="simple"
												 rows="3" cols="25" name="allergies" onkeyup="return callLength('allergies','allCnt',300)"/></td>
											<td width="4%"></td>	
											</tr>
											<tr>
												<td width="13%"></td>
												<td width="2%"></td>
												<td width="1%">&nbsp;</td>
												<td width="23%" align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_ailments','conf1','','paraFrm_ailCnt','300');"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="ailCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
												<td width="5%">&nbsp;</td>
												<td width="11%"></td>
												<td width="2%">&nbsp;</td>
												<td width="1%"></td>
												<td width="23%" align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_allergies','conf2','','paraFrm_allCnt','300');"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="allCnt" readonly="true" size="1"></s:textfield></td>
												<td width="4%"></td>
											</tr>
										<tr>
											<td width="13%"><label name="conf3"
												id="conf3" ondblclick="callShowDiv(this);"><%=label.get("conf3")%></label></td>
											<td width="2%">&nbsp;</td>
											<td width="1%">:</td>
											<td width="23%" ><s:textarea theme="simple"
												 rows="3" cols="25" name="diseases" onkeyup="return callLength('diseases','disCnt',300)" /></td>
											<td width="2%"></td>
											<td width="5%" >&nbsp;</td>
											<td width="11%"></td>
											<td width="2%">&nbsp;</td>
											<td width="1%"></td>
											<td width="23%"></td>
											<td width="4%"></td>
										</tr>
										<tr>
												<td width="13%"></td>
												<td width="2%"></td>
												<td width="1%">&nbsp;</td>
												<td width="23%"  align="right"><label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="disCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
												<td width="5%">&nbsp;</td>
												<td width="11%"></td>
												<td width="2%">&nbsp;</td>
												<td width="1%"></td>
												<td width="23%" ></td>
												<td width="4%"></td>
											</tr>
									</table>
									</fieldset>
									</td>
								</tr>
								<tr>
									<td colspan="6">
									<fieldset><legend class="legend1">
									Languages Known </legend>
									<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="border">
										<tr>
											<td width="30%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr width="10%">
													<td><label name="languageName"
														id="languageName"
														ondblclick="callShowDiv(this);"><%=label.get("languageName")%></label>:</td>
													<td width="10%"><s:hidden name="languageCode" /> <s:textfield
														 name="langName" size="15"
														readonly="true" /></td>
													<td>&nbsp; <input type="button" name="Submit3"
														class="button" value="..." onclick="langAction()" /></td>
												</tr>
											</table>
											</td>
											<td width="10%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="17%"><input type="checkbox"
														class="checkbox" name="readLang" id="readLang"
														onclick="chkReadLang();" /></td>
														<td width="83%"><label class="set" name="read"
															id="read" ondblclick="callShowDiv(this);"><%=label.get("read")%></label></td>
													</tr>
											</table>
											</td>
											<td width="10%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
													<tr>
														<td width="17%"><input type="checkbox"
															class="checkbox" name="writeLang" id="writeLang"
															onclick="chkWriteLang();" /></td>
														<td width="83%"><label class="set" name="write"
															id="write" ondblclick="callShowDiv(this);"><%=label.get("write")%></label></td>
													</tr>
												</table>
											</td>
											<td width="10%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
													<tr>
														<td width="17%"><input type="checkbox"
															class="checkbox" name="speakLang" id="speakLang"
															onclick="chkSpeakFlag();" /></td>
														<td width="83%"><label class="set" name="speak"
															id="speak" ondblclick="callShowDiv(this);"><%=label.get("speak")%></label></td>
													</tr>
												</table>
											</td>
											<td width="30%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
													<tr>
														<td width="17%"><input type="checkbox"
															class="checkbox" name="motherLang" id="motherLang"
															onclick="chkMotherLangFlag();" /></td>
														<td width="83%"><label class="set"
															name="mothertonuge" id="mothertonuge"
															ondblclick="callShowDiv(this);"><%=label.get("mothertonuge")%></label>?</td>
													</tr>
												</table>
											</td>
											<td width="10%"><input type="button" name="add" align="right"
												onclick="return callAdd();" value="Add" class="add">
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
											<tr>
												<td width="15%" bgcolor="#EEF4FB"><label class="set"
													name="langtype" id="langtype"
													ondblclick="callShowDiv(this);"><%=label.get("langtype")%></label></td>
												<td width="12%" bgcolor="#EEF4FB"><label class="set"
													name="read" id="read" ondblclick="callShowDiv(this);"><%=label.get("read")%></label></td>
												<td width="12%" bgcolor="#EEF4FB"><label class="set"
													name="write" id="write" ondblclick="callShowDiv(this);"><%=label.get("write")%></label></td>
												<td width="12%" bgcolor="#EEF4FB"><label class="set"
													name="speak" id="speak" ondblclick="callShowDiv(this);"><%=label.get("speak")%></label></td>
												<td width="24%" bgcolor="#EEF4FB"><label class="set"
													name="mothertonuge" id="mothertonuge"
													ondblclick="callShowDiv(this);"><%=label.get("mothertonuge")%></label></td>
												<td width="5%" bgcolor="#EEF4FB" align="center">Edit/Delete</td>
											</tr>
											<%
											int cnt = 0;
											%>
										<%!int d1 = 0;%>
										<%
										int i1 = 0;
										%>
										<%
										int imgVar = 0;
										%>
										<s:iterator value="langList">
											<tr>
												<s:hidden name="langCode" />
												<td width="20%" align="left" class="sortableTD"><s:property
													value="langName" /><s:hidden
													name="hiddenItLangType" /> 
												</td>
												<td class="sortableTD" width="20%">
												<s:if test="readFlag">
													<input type="hidden" name="rowImgId" value="Y"
														id="rowImgId<%=imgVar%>1" />
													<img class="iconImage" src="../pages/mypage/images/icons/tick.png"
														width="10" height="10" />
													<input type="hidden" name="hiddenItReadLang"
														id="hiddenItReadLang<%=i1%>"
														value="<s:property value="hiddenItReadLang" />" />
												</s:if> <s:else>
													<input type="hidden" name="rowImgId" value="N"
														id="rowImgId<%=imgVar%>1" />
													<img src="../pages/mypage/images/icons/blank.png" width="10"
														height="10" />
													<input type="hidden" name="hiddenItReadLang"
														id="hiddenItReadLang<%=i1%>"
														value="<s:property value="hiddenItReadLang" />" />
												</s:else>
												</td>
												<td class="sortableTD" width="20%">
												<s:if test="writeFlag">
													<input type="hidden" name="rowImgId" value="Y"
														id="rowImgId<%=imgVar%>2" />
													<img class="iconImage" src="../pages/mypage/images/icons/tick.png"
														width="10" height="10" />
													<input type="hidden" name="hiddenItWriteLang"
														id="hiddenItWriteLang<%=i1%>"
														value="<s:property value="hiddenItWriteLang" />" />
												</s:if> <s:else>
													<input type="hidden" name="rowImgId" value="N"
														id="rowImgId<%=imgVar%>2" />
													<img src="../pages/mypage/images/icons/blank.png" width="10"
														height="10" />
													<input type="hidden" name="hiddenItWriteLang"
														id="hiddenItWriteLang<%=i1%>"
														value="<s:property value="hiddenItWriteLang" />" />
												</s:else>
												</td>
												<td class="sortableTD" width="20%">
												<s:if test="speakFlag">
													<input type="hidden" name="rowImgId" value="Y"
														id="rowImgId<%=imgVar%>3" />
													<img class="iconImage" src="../pages/mypage/images/icons/tick.png"
														width="10" height="10" />
													<input type="hidden" name="hiddenItSpeakLang"
														id="hiddenItSpeakLang<%=i1%>"
														value="<s:property value="hiddenItSpeakLang" />" />
												</s:if> <s:else>
													<input type="hidden" name="rowImgId" value="N"
														id="rowImgId<%=imgVar%>3" />
													<img src="../pages/mypage/images/icons/blank.png" width="10"
														height="10" />
													<input type="hidden" name="hiddenItSpeakLang"
														id="hiddenItSpeakLang<%=i1%>"
														value="<s:property value="hiddenItSpeakLang" />" />
												</s:else>
												</td>
												<td class="sortableTD" width="20%"><s:if
													test="motherFlag">
													<input type="hidden" name="rowImgId" value="Y"
														id="rowImgId<%=imgVar%>4" />
													<img class="iconImage" src="../pages/mypage/images/icons/tick.png"
														width="10" height="10" />
													<input type="hidden" name="hiddenItMotherLang"
														id="hiddenItMotherLang<%=i1%>"
														value="<s:property value="hiddenItMotherLang" />" />
												</s:if> <s:else>
													<input type="hidden" name="rowImgId" value="N"
														id="rowImgId<%=imgVar%>4" />
													<img src="../pages/mypage/images/icons/blank.png" width="10"
														height="10" />
													<input type="hidden" name="hiddenItMotherLang"
														id="hiddenItMotherLang<%=i1%>"
														value="<s:property value="hiddenItMotherLang" />" />
												</s:else>
												</td>
												<td>
												<div align="center"><img
													src="../pages/mypage/images/icons/edit.png" width="10" height="10"
													onclick="editLangKnows('<s:property value="langCode"/>','<s:property value="hiddenItLangType" />','rowImgId<%=imgVar%>1', 'rowImgId<%=imgVar%>2', 'rowImgId<%=imgVar%>3', 'rowImgId<%=imgVar%>4')" />|
												<img src="../pages/mypage/images/icons/delete.png" width="10"
													height="10" onclick="deleteLangKnows('<s:property value="langCode"/>','<s:property value="hiddenItLangType" />','rowImgId<%=imgVar%>1', 'rowImgId<%=imgVar%>2', 'rowImgId<%=imgVar%>3', 'rowImgId<%=imgVar%>4',<%=i1%>)" /></div>
												</td>
											</tr>
											<%
											imgVar++;
											%>
										</s:iterator>
										<%
										d1 = i1;
										%>
									</table>
								</fieldset>
							</td>
						</tr>
						</s:if>
								<s:else>
									<tr>
										<td colspan="9">
										<fieldset><legend class="legend1">
										Personal Details </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="22%"><label name="nation" id="nation"
													ondblclick="callShowDiv(this);"><%=label.get("nation")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:property
													value="nationality" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label name="blood.group"
													id="blood.group" ondblclick="callShowDiv(this);"><%=label.get("blood.group")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:property
													value="bloodGroup" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="religion" id="religion"
													ondblclick="callShowDiv(this);"><%=label.get("religion")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1" ><s:hidden
													name="religionCode" /> <s:property
													value="religion" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%" ><label name="caste.category"
													id="caste.category" ondblclick="callShowDiv(this);"><%=label.get("caste.category")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:hidden
													name="castCategoryCode" /> <s:property
													value="castCategory" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="caste" id="caste"
													ondblclick="callShowDiv(this);"><%=label.get("caste")%></label></td>
												<td width="2%" >&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:hidden
													name="castCode" /> <s:property
													value="castName" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label name="subCaste" id="subCaste"
													ondblclick="callShowDiv(this);"><%=label.get("subCaste")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="subCast" /></td>
											</tr>
											<tr>
												<td width="22%"><label
													name="weight" id="weight" ondblclick="callShowDiv(this);"><%=label.get("weight")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="weight" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label
													name="height" id="height" ondblclick="callShowDiv(this);"><%=label.get("height")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="height" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="is.handicap"
													id="is.handicap" ondblclick="callShowDiv(this);"><%=label.get("is.handicap")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="isHandiCap" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label
													name="handicap.desc" id="handicap.desc"
													ondblclick="callShowDiv(this);"><%=label.get("handicap.desc")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1" title="<s:property
													value="desc" />"><s:property
													value="abbrDesc" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="marital.status"
													id="marital.status" ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="maritalStatus" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label
													name="marriage.date" id="marriage.date"
													ondblclick="callShowDiv(this);"><%=label.get("marriage.date")%></label></td>
												<td  width="2%">&nbsp;</td>
												<td  width="2%">:</td>
												<td width="22%" class="text1"><s:property
													value="marriageDate" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="hobbies" id="hobbies"
													ondblclick="callShowDiv(this);"><%=label.get("hobbies")%></label>
												</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1" title="<s:property value="hobbies"/>"><s:property value="abbrHobbies" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label name="id.mark" id="id.mark"
													ondblclick="callShowDiv(this);"><%=label.get("id.mark")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1" title="<s:property
													value="markId" />"><s:property
													value="abbrMarkId" /></td>
											</tr>
											<tr>
												<td width="22%"><label
													name="hometown" id="hometown"
													ondblclick="callShowDiv(this);"><%=label.get("hometown")%></label>
												</td>
												<td  width="2%">&nbsp;</td>
												<td  width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="homeTown" /> <s:hidden
													name="homeTownCode" /></td>
												<td  width="3%">&nbsp;</td>
												<td width="22%">&nbsp;</td>
												<td  width="2%">&nbsp;</td>
												<td  width="2%">&nbsp;</td>
												<td width="22%">&nbsp;</td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td colspan="9">
										<fieldset><legend class="legend1">
										Criminal Details</legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="22%"><label name="conf4" id="conf4"
													ondblclick="callShowDiv(this);"><%=label.get("conf4")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1"><s:property
													value="isConvicted" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label name="conf5" id="conf5"
													ondblclick="callShowDiv(this);"><%=label.get("conf5")%></label>
												</td>
												<td  width="2%"></td>
												<td  width="2%">:</td>
												<td width="22%" class="text1" title="<s:property
													value="criminalActs" />" ><s:property
													value="abbrCriminalActs" /></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td colspan="9">
										<fieldset><legend class="legend1"> Medical
										Details </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="22%"><label name="conf1" id="conf1"
													ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1" title="<s:property
													value="ailments" />"><s:property
													value="abbrAilments" /></td>
												<td width="3%">&nbsp;</td>
												<td width="22%"><label name="conf2" id="conf2"
													ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label></td>
												<td  width="2%"></td>
												<td  width="2%">:</td>
												<td width="22%" class="text1" title="<s:property
													value="allergies" />"><s:property
													value="abbrAllergies" /></td>
											</tr>
											<tr>
												<td width="22%"><label name="conf3"
													id="conf3" ondblclick="callShowDiv(this);"><%=label.get("conf3")%></label></td>
												<td width="2%" >&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"  class="text1" title="<s:property
													value="diseases" />"><s:property
													value="abbrDiseases" /></td>
												<td  width="3%">&nbsp;</td>
												<td width="22%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="2%">&nbsp;</td>
												<td width="22%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<td>
										<fieldset><legend class="legend1">
										Languages Known </legend>
										<table width="100%" cellspacing="0" cellspacing="1" border="0">
											<tr>
												<td colspan="3">
												<%
												try {
												%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>

														<td class="formtext">
														<table width="100%" border="0" cellpadding="1"
															cellspacing="1">
															<tr>

																<td bgcolor="#EEF4FB" align="center" width="20%"><label 
																	class="set" name="langtype" id="langtype"
																	ondblclick="callShowDiv(this);"><%=label.get("langtype")%></label></td>
																<td bgcolor="#EEF4FB" align="center" width="20%"><label
																	class="set" name="read" id="read"
																	ondblclick="callShowDiv(this);"><%=label.get("read")%></label></td>
																<td bgcolor="#EEF4FB" align="center" width="20%"><label
																	class="set" name="write" id="write"
																	ondblclick="callShowDiv(this);"><%=label.get("write")%></label></td>
																<td bgcolor="#EEF4FB" align="center" width="20%"><label
																	class="set" name="speak" id="speak"
																	ondblclick="callShowDiv(this);"><%=label.get("speak")%></label></td>
																<td bgcolor="#EEF4FB" align="center" width="20%"><label
																	class="set" name="mothertonuge" id="mothertonuge"
																	ondblclick="callShowDiv(this);"><%=label.get("mothertonuge")%></label></td>

																<s:if test="modeLength">
																	<%
																	int count = 0;
																	%>
																	<%!int d = 0;%>
																	<%
																	int i = 0;
																	%>

																	<s:iterator value="langList">
																		<tr class="text1">

																			<td width="20%" align="left" class="text1"><s:hidden
																				name="langCode" /> <s:property value="langName" /><s:hidden
																				name="hiddenItLangType" /> <%
 ++i;
 %>
																			</td>

																			<td class="sortableTD" width="20%" align="center">
																			<s:if test="readFlag">
																				<img class="iconImage"
																					src="../pages/mypage/images/icons/tick.png" width="10"
																					height="10" />
																			</s:if> <s:else>
																				<img src="../pages/mypage/images/icons/blank.png"
																					width="10" height="10" />
																			</s:else></td>
																			<td class="sortableTD" width="20%" align="center">
																			<s:if test="writeFlag">
																				<img class="iconImage"
																					src="../pages/mypage/images/icons/tick.png" width="10"
																					height="10" />
																			</s:if> <s:else>
																				<img src="../pages/mypage/images/icons/blank.png"
																					width="10" height="10" />
																			</s:else></td>
																			<td class="sortableTD" width="20%" align="center">
																			<s:if test="speakFlag">
																				<img class="iconImage"
																					src="../pages/mypage/images/icons/tick.png" width="10"
																					height="10" />
																			</s:if> <s:else>
																				<img src="../pages/mypage/images/icons/blank.png"
																					width="10" height="10" />
																			</s:else></td>
																			<td class="sortableTD" width="20%" align="center">
																			<s:if test="motherFlag">
																				<img class="iconImage"
																					src="../pages/mypage/images/icons/tick.png" width="10"
																					height="10" />
																			</s:if> <s:else>
																				<img src="../pages/mypage/images/icons/blank.png"
																					width="10" height="10" />
																			</s:else></td>
																		</tr>
																	</s:iterator>
																	<%
																	d = i;
																	%>
																</s:if>
																<%
																	} catch (Exception e) {
																	}
																%>

															</tr>
														</table>
														<s:if test="noData">
															<table width="100%">
																<tr>
																	<td align="center"><font color="red">No
																	Data To Display</font></td>
																</tr>
															</table>
															</s:if>
														</td>
													</tr>
												</table>

												</td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
								</s:else>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td height="1px" bgcolor="#cccccc"></td>
								</tr>
								<tr>
									<td width="100%">
									<table width="100%" border="0" align="right" cellpadding="2"
										cellspacing="3">
															<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="95%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if>
												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="updateFlg">
													<td width="95%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													<td align="right" width="100%"><a href="#"><img
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
			</fieldset>
			</td>
		</tr>
</table>
</td>
</tr>
</table>
</s:form>

<script>

function callMarriage(){
	var marr= document.getElementById('paraFrm_maritalStatus').value;
     if(marr=="U" || marr=="" || marr==null) {
        document.getElementById('paraFrm_marriageDate').value='';
		document.getElementById('paraFrm_marriageDate').disabled=true; 
		document.getElementById('imageCal').style.display='none';  				 
	}else{
	document.getElementById('paraFrm_marriageDate').disabled=false; 
		document.getElementById('imageCal').style.display='';  
	}
	
} 

function saveFun(){	
	//callMarriage();
	try{
	var hDesc=document.getElementById('handicap.desc').innerHTML.toLowerCase();
	var hob=document.getElementById('hobbies').innerHTML.toLowerCase();
	var idm=document.getElementById('id.mark').innerHTML.toLowerCase();	
	var handicap = trim(document.getElementById('paraFrm_desc').value);
	var isHandi = document.getElementById('paraFrm_isHandiCap').value;
	var hometown = document.getElementById('paraFrm_homeTownCode').value;
	var mrgDate=document.getElementById('paraFrm_marriageDate').value;
		if(isHandi=="Y"){
			if(handicap=="" || handicap==null){
				alert("Please enter "+hDesc);
				return false;
			}
		}
		if(mrgDate!=""){
		if(!(validateDate('paraFrm_marriageDate','marriage.date'))){
		return false;
		}
		}
			var hobbies = document.getElementById('paraFrm_hobbies').value;
			var idMark = document.getElementById('paraFrm_markId').value;
			var  cr1= document.getElementById('paraFrm_criminalActs').value;
			var dise1 = document.getElementById('paraFrm_diseases').value;
			var aller1 = document.getElementById('paraFrm_allergies').value;
			var ail1 = document.getElementById('paraFrm_ailments').value;
			
			var message1=document.getElementById('conf1').innerHTML.toLowerCase();
			var message2=document.getElementById('conf2').innerHTML.toLowerCase();
			var message3=document.getElementById('conf3').innerHTML.toLowerCase();
			var message4=document.getElementById('conf5').innerHTML.toLowerCase();
			
		    if(hobbies!=null || hobbies!=""){
		    if(hobbies.length>100){
		    alert("Maximum 100 characters are allowed for "+hob);
			 document.getElementById('paraFrm_hobbies').focus();
			 return false;
		    }
		    }
		    if(idMark!=null || idMark!=""){
		    if(idMark.length>255){
		    alert("Maximum 255 characters are allowed for "+idm);
			 document.getElementById('paraFrm_markId').focus();
			 return false;
		    }
		    }
		    
			if(dise1!=null || dise1!="")
			{
			   if(dise1.length>300)
			   {
			    alert("Maximum 300 characters are allowed for "+message3);
			    document.getElementById('paraFrm_diseases').focus();
			    return false;
			   }
			 
			}
			if(aller1!=null || aller1!="")
			{
			   if(aller1.length>300)
			   {
			    alert("Maximum 300 characters are allowed for "+message2);
			    document.getElementById('paraFrm_allergies').focus();
			    return false;
			   }
			 
			}
			
				if(ail1!=null || ail1!="")
			{
			   if(ail1.length>300)
			   {
			    alert("Maximum 300 characters are allowed for "+document.getElementById('conf1').innerHTML.toLowerCase());
			    document.getElementById('paraFrm_ailments').focus();
			    return false;
			   }
			 
			}			
				if(cr1!=null || cr1!="")
			{
			   if(cr1.length>300)
			   {
			    alert("Maximum 300 characters are allowed for "+message4);
			    document.getElementById('paraFrm_criminalActs').focus();
			    return false;
			   }
			 
			}
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="PersonnelDetail_save.action";
		document.getElementById("paraFrm").submit();
		}catch(e){
		alert(e);
		}

}	
	
callMarriage();
callHandicap();

function callHandicap(){
	var isHandicap = document.getElementById('paraFrm_isHandiCap').value;
	if(isHandicap=="N"|| isHandicap=="" || isHandicap==null){
	document.getElementById('paraFrm_desc').value='';
	document.getElementById('paraFrm_desc').disabled=true;
	}else{
	document.getElementById('paraFrm_desc').disabled=false;
	}
}

function callisConvicted()
{
	var isConvicted = document.getElementById('paraFrm_isConvicted').value;
	if(isConvicted=="N"|| isConvicted=="" || isConvicted==null)
	{
	document.getElementById('paraFrm_criminalActs').value='';
	document.getElementById('paraFrm_crmCnt').value='';
	document.getElementById('paraFrm_criminalActs').disabled=true;
	
	}else {
		document.getElementById('paraFrm_criminalActs').disabled=false;
	}
}

function chkReadLang(){
	
   	if(document.getElementById('paraFrm_readLang').checked == true){
   		document.getElementById('hiddenItReadLang').value='Y';	
				
   	}else{
   		document.getElementById('hiddenItReadLang').value='N';
   	} 		
   		  		 			
}
function chkWriteLang(){
   	if(document.getElementById('paraFrm_writeLang').checked == true){
		document.getElementById('hiddenItWriteLang').value='Y';		
	}else{
		document.getElementById('hiddenItWriteLang').value='N'; 
	}   		     		 			
 }    	
function chkSpeakFlag(){
	if(document.getElementById('paraFrm_speakLang').checked == true){
		document.getElementById('hiddenItSpeakLang').value='Y';		
	}else{
		document.getElementById('hiddenItSpeakLang').value='N'; 
	}     		 			
}   	
  function chkMotherLangFlag(){
  	if(document.getElementById('paraFrm_motherLang').checked == true){
		document.getElementById('hiddenItMotherLang').value='Y';	
	}else{
	   	document.getElementById('hiddenItMotherLang').value='N'; 
	}   		 	
  } 		
  function editFun(){
  	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="PersonnelDetail_personelDetails.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
  }
 
  function cancelFun(){
  	try{
  		
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="PersonnelDetail_cancelFunc.action";
		document.getElementById("paraFrm").submit();
	}catch(e){
	alert(e);
	}
}
  
  function searchFun(){
  	try{
		javascript:callsF9(500,325,'PersonnelDetail_f9empaction.action')  	
	}catch(e){alert(e);}
  }
function callAdd()
{
	try{
	    if(document.getElementById('paraFrm_langName').value==""){
	    alert("Please select "+document.getElementById('languageName').innerHTML.toLowerCase());
	   	return false;
	    }
	    
	    if((document.getElementById('readLang').checked==false && document.getElementById('writeLang').checked==false && document.getElementById('speakLang').checked==false && document.getElementById('motherLang').checked==false)){
	    alert("Please select atleast one checkbox");
	    return false;
	    }
	   	document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='PersonnelDetail_addToList.action';
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}

function deleteLangKnows(code,type,read, write, speak, motherTongue){
	var conf = confirm("Do you really want to delete this record?");
	if(conf) {
		var rd = document.getElementById(read).value;
	    var wt = document.getElementById(write).value;
	    var sp = document.getElementById(speak).value;
	    var mt = document.getElementById(motherTongue).value;
	    document.getElementById('paraFrm_langName').value=type;
		document.getElementById('paraFrm_paraId').value=trim(code);
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'PersonnelDetail_deleteToList.action';
		document.getElementById('paraFrm').submit();
	}
}	
function editLangKnows(code,type,read, write, speak, motherTongue){
	try{
	
	var rd = document.getElementById(read).value;
	var wt = document.getElementById(write).value;
	var sp = document.getElementById(speak).value;
	var mt = document.getElementById(motherTongue).value;
	document.getElementById('paraFrm_langName').value=type;
	document.getElementById('paraFrm_paraId').value=trim(code);
	document.getElementById('langEditFlag').value="Y";
	if(rd=="Y"){
		document.getElementById('readLang').checked='true';
	}else {
		document.getElementById('readLang').checked='';
	}
	
	if(wt=="Y"){
		document.getElementById('writeLang').checked='true';
	}else{
		document.getElementById('writeLang').checked='';
	}
	
	if(sp=="Y"){
		document.getElementById('speakLang').checked='true';
	}else{
		document.getElementById('speakLang').checked='';
	}
	
	if(mt=="Y"){
		document.getElementById('motherLang').checked='true';
	}else{
		document.getElementById('motherLang').checked='';
	}
	}catch(e){alert(e);}
	
}

function langAction(){
	javascript:callsF9(500,325,'PersonnelDetail_f9langaction.action');
}

function categoryAction(){
	javascript:callsF9(500,325,'PersonnelDetail_f9castCataction.action');
}	
function religionAction(){
	javascript:callsF9(500,325,'PersonnelDetail_f9relgnaction.action');
}


function catNameAction(){
	javascript:callsF9(500,325,'PersonnelDetail_f9castaction.action');
} 

function homeTownAction(){
	javascript:callsF9(500,325,'PersonnelDetail_f9cityaction.action');
}

</script>
