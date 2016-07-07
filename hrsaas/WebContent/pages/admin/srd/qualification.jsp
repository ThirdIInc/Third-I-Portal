<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="QualificationDetail" validate="true" id="paraFrm"
	 theme="simple">
	<form action="" method="post" enctype="multipart/form-data" name="form1" id="form1">
		<s:hidden name="editFlag" />
		<s:hidden name="updateFlag" />
		<s:hidden name="editDetail" />
		<s:hidden name="paracode" /> 
		<s:hidden name="isNotGeneralUser" /> 
		<s:hidden name="empName" /> 
		<s:hidden name="uploadFileName" /> 
		<s:hidden name="empID" /> 
		<s:hidden name="qualDetail.qualificationCode" /> 
		<s:hidden name="qualDetail.qualCode" /> 
		<s:set name="updateFlg" value="updateFlag" /> 
		<s:set name="deleteFlg" value="deleteFlag" /> 
		<s:set name="insertFlg" value="insertFlag" />
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"><img
						src="../pages/mypage/images/icons/profile_qualification.png"
						width="16" height="16" />&nbsp;&nbsp;Qualification</legend>
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
												<s:if test="updateFlg">
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="3%">|</td>
												</s:if>
												<s:elseif test="insertFlg">
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="3%">|</td>
												</s:elseif>
												<td width="5%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="13%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
													<td >|</td>
											</tr>
										</s:if>
										<s:else>
											<s:if test="insertFlg">
												<td width="75%" align="right"><a href="#"><img
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
												<td >|</td>
											</s:if>

										</s:else>

									</table>
									</td>
								</tr>

								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr height="5"></tr>
								<s:if test="editDetail">
									<table width="100%" border="0" align="right" cellpadding="0"
										cellspacing="0">

										<tr>
											<td>
											<fieldset><legend class="legend1">
											Qualification Particulars </legend>
											<table width="98%" border="0" align="center" cellpadding="2"
												cellspacing="1">
												<tr>
													<td width="22%" height="22"><label name="qualname"
														id="qualname" ondblclick="callShowDiv(this);"><%=label.get("qualname")%></label>
													</td>
													<td><font color="red">*</font></td>
													<td>:</td>
													<td width="27%" height="22" nowrap="nowrap"><label>
													<s:textfield size="28" name="qualDetail.qualifyName"
														readonly="true" /> <input type="button" name="Submit3"
														class="button" value="..." onclick="qualAction()" />&nbsp;</label>
													<label></label></td>
													<td height="22"></td>
													<td width="17%" height="22"><label name="institute"
														id="institute" ondblclick="callShowDiv(this);"><%=label.get("institute")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td width="28%" height="22"><s:textfield size="28"
														name="qualDetail.institute" maxlength="150"
														onkeypress="return charactersOnly()" /></td>
													
												</tr>
												<tr>
													<td height="22"><label name="university"
														id="university" ondblclick="callShowDiv(this);"><%=label.get("university")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td width="27%"><s:textfield size="28"
														name="qualDetail.university" maxlength="150"
														onkeypress="return charactersOnly()"/></td>
													<td width="4%" height="22"></td>
													<td><label name="dateofpass" id="dateofpass"
														ondblclick="callShowDiv(this);"><%=label.get("dateofpass")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td width="30%"><s:textfield size="28" name="qualDetail.year"
														maxlength="10" onkeypress="return numbersWithHiphen();" 
														onblur="return validateDate('paraFrm_qualDetail_year', 'dateofpass')"/>
													<s:a
														href="javascript:NewCal('paraFrm_qualDetail_year','DDMMYYYY');">
														<img src="../pages/images/recruitment/Date.gif"
															class="iconImage" height="16" align="absmiddle" width="16">
													</s:a></td>
												</tr>
												<tr>
													<td height="22"><label name="grade" id="grade"
														ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td width="27%"><s:textfield size="28" maxlength="25"
														name="qualDetail.grade" /></td>
													<td height="22"></td>
													<td><label name="percentage" id="percentage"
														ondblclick="callShowDiv(this);"><%=label.get("percentage")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td><s:textfield size="28"
														name="qualDetail.percentage"
														onkeypress="return numbersWithDot();" maxlength="5" /></td>
													
												</tr>
												<tr>
													<td height="22"><label name="istechnical"
														id="istechnical" ondblclick="callShowDiv(this);"><%=label.get("istechnical")%></label>
													</td>
													<td></td>
													<td>:</td>
													<td width="27%"><s:select name="qualDetail.tech"
														headerKey="" headerValue="--Select--"
														list="#{'Y':'Yes','N':'No'}" /></td>
													<td height="22"></td>
													<td></td>
													<td></td>
													
												</tr>

											</table>
											</fieldset>
											</td>
										</tr>
									</table>
								</s:if>
								<tr>
									<td>
									<fieldset><legend class="legend1">
									Qualification Details </legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2">
												<tr>
													<td width="15%" bgcolor="#EEF4FB" align="center""><label
														name="qualname" id="qualname"
														ondblclick="callShowDiv(this);"><%=label.get("qualname")%></label>
													</td>
													<td width="12%" bgcolor="#EEF4FB" align="center"><label
														name="institute" id="institute"
														ondblclick="callShowDiv(this);"><%=label.get("institute")%></td>
													<td width="20%" bgcolor="#EEF4FB" align="center"> <label
														name="university" id="university"
														ondblclick="callShowDiv(this);"><%=label.get("university")%></label></td>
													<td width="8%" bgcolor="#EEF4FB" align="center"><label
														name="dateofpass" id="dateofpass"
														ondblclick="callShowDiv(this);"><%=label.get("dateofpass")%></label>
													</td>
													<td width="8%" bgcolor="#EEF4FB" align="center"><label name="grade"
														id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></td>
													<td width="8%" bgcolor="#EEF4FB" align="center"><label
														name="percentage" id="percentage"
														ondblclick="callShowDiv(this);"><%=label.get("percentage")%></label>
													</td>
													<td width="5%" bgcolor="#EEF4FB" align="center"><label
														name="istechnical" id="istechnical"
														ondblclick="callShowDiv(this);"><%=label.get("istechnical")%></label>
													</td>
													<td width="5%" bgcolor="#EEF4FB" align="center">Edit|Delete</td>
												</tr>
												<!-- LISTING -->
												<s:iterator value="qualDetail.qualList" status="stat">
													<tr>
														<td class="text1" title="<s:property value="qualifyName"/>"><s:hidden name="qualifyCode"  /> <s:property
															value="abbrQualifyName" /></td>
														<td class="text1" width="13%" title="<s:property value="institute"/>"><s:property
															value="abbrInstitute" /></td>
														<td class="text1" width="15%" title="<s:property value="university"/>"><s:property
															value="abbrUniversity" /></td>
														<td class="text1" width="16%"><s:property
															value="year" /></td>
														<td class="text1" width="11%" title="<s:property value="grade"/>" ><s:property
															value="abbrGrade" /></td>
														<td class="text1" width="11%"><s:property
															value="percentage" /> %</td>
														<td class="text1" width="19%"><s:property
															value="tech" /></td>
														<td class="text1" width="25%" align="center" nowrap>
														<s:if test="updateFlg">
															<img src="../pages/mypage/images/icons/edit.png"
																width="10" height="10" border="0"
																onclick="callForEdit('<s:property value="qualifyCode"/>')" />
														&nbsp;|&nbsp; </s:if> <s:if test="deleteFlg">
															<img src="../pages/mypage/images/icons/delete.png"
																width="10" height="10" border="0"
																onclick="callForDelete('<s:property value="qualifyCode"/>')">
														</s:if></td>
													</tr>
												</s:iterator>
											</table>
											</td>
										</tr>

										<s:if test="noData">
											<tr>
												<td colspan="4" align="center"><font color="red">No
												data to display</font></td>
											</tr>
										</s:if>
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
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="3%">|</td>
												</s:if>
												<s:elseif test="insertFlg">
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
												<td width="3%">|</td>
												</s:elseif>
												<td width="5%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="13%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<s:if test="insertFlg">
												<td width="75%" align="right"><a href="#"><img
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
	</div>
	</form>
</s:form>
<script>

  function callForEdit(id){
   try{
   			document.getElementById("paraFrm").action="QualificationDetail_edit.action";
	  		document.getElementById('paraFrm_paracode').value=id;
	    	document.getElementById("paraFrm").submit();
   	  }catch(e){
   			alert(e);
   		}
	   	
 }
   function callForDelete(id){
   	var conf=confirm("Are you sure to delete this record?");
   	if(conf){
     	document.getElementById("paraFrm").action="QualificationDetail_delete.action";
	   	document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }  
 }
 
function saveFun(){
	var per=document.getElementById('percentage').innerHTML.toLowerCase();
	var qua=document.getElementById('qualname').innerHTML.toLowerCase();
	var pre1 = document.getElementById('paraFrm_qualDetail_qualCode').value;
	var pre2 = document.getElementById('paraFrm_qualDetail_percentage').value;
	var pre3 = document.getElementById('paraFrm_qualDetail_year').value;
	if((pre1=="")){
		alert ("Please select "+qua);
		return false;
	}
	if(!(pre3=="")){
		  var fld=['paraFrm_qualDetail_year'];
		  var lbl=['dateofpass'];
	   	  var chkDb=validateDate(fld,lbl);
		  if(!chkDb){
			  return false;
		  }
  	}
   if(!(pre2 == "")){
	var iChars = "0123456789.";
 		for (var i = 0; i < pre2.length; i++) {			
	  		if (!(iChars.indexOf(pre2.charAt(i)) != -1)) {
		  		alert (per+" should be Numeric ");
		  		return false;
			}
	    }
   }
   
   
   if(pre3!==""){
			if(!(validateDate('paraFrm_qualDetail_year', 'dateofpass'))){
				return false;
			}
			}		
	
  var pr=document.getElementById('paraFrm_qualDetail_percentage').value;
  if(pr > 100){
		alert('Please enter '+per+' less than 100 ');
		document.getElementById('paraFrm_qualDetail_percentage').value='';
		return false;
  }
  
  document.getElementById("paraFrm").action="QualificationDetail_save.action";
  document.getElementById("paraFrm").submit();
  return true;
}
	     
function addFun(){
	document.getElementById("paraFrm").action="QualificationDetail_editEmp.action";
	document.getElementById("paraFrm").submit();
}
function cancelFun(){
	document.getElementById("paraFrm").action="QualificationDetail_cancelFunc.action";
	document.getElementById("paraFrm").submit();
}
function searchFun(){
	javascript:callsF9(500,325,'QualificationDetail_f9empaction.action');
}

function qualAction(){
	javascript:callsF9(500,325,'QualificationDetail_f9qualaction.action');
}

</script>
