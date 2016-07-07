<!--@author Prajakta.bhandare  -->
<!--@date 23 Jan 2013 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<style type="text/css">
textarea {
	width: 95%;
}
</style>
<s:form action="EmployeeExperience" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="editFlag" />
	<s:hidden name="empId" />
	<s:hidden name="paraExpId"/>
	<s:hidden name="paraProjId"/>
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"> <img
						src="../pages/mypage/images/icons/profile_workexp.png" width="16"
						height="16" />&nbsp;&nbsp;Work Experience </legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="100%">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag"><!-- if edit flag -->
											<tr align="right">
												<s:if test="updateFlg"><!-- if update flag -->
													<td width="18%" ><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:if><!--end if update flag -->
												<s:elseif test="insertFlg"><!-- elseif insert flag -->
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:elseif><!--end elseif insert flag -->
												<td width="18%"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" /></td>
												<td width="18%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>

										</s:if><!--end if edit flag -->
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
								
								<s:if test="editFlag"><!-- if edit flag -->
									<tr>
										<td colspan="11">
										<fieldset><legend class="legend1"> Work
										Experience Particulars </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="20%"><label name="preuniemp" id="preuniemp"
													ondblclick="callShowDiv(this);"><%=label.get("preuniemp")%></label></td>
												<td width="2%" class="star">*</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield name="employer" size="25"
													maxlength="100" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="20%"><label name="designation"
													id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield theme="simple"
													name="designation" size="25" maxlength="50" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="joindate" id="joindate"
													ondblclick="callShowDiv(this);"><%=label.get("joindate")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield name="joiningDate"
													size="25" maxlength="10"
													onkeypress="return numbersWithHiphen();"
													onblur="return validateDate('paraFrm_joiningDate','joindate');" /></td>
												<td width="2 %"><s:a
													href="javascript:NewCal('paraFrm_joiningDate','DDMMYYYY');">

													<img src="../pages/images/recruitment/Date.gif"
														class="iconImage" height="16" align="absmiddle" width="16">
												</s:a></td>

												<td width="4%">&nbsp;</td>
												<td width="20%"><label name="relivedate"
													id="relivedate" ondblclick="callShowDiv(this);"><%=label.get("relivedate")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:textfield
													name="relDate" size="25" maxlength="10"
													onkeypress="return numbersWithHiphen();"
													onblur="return validateDate('paraFrm_relDate','relivedate');" /></td>
												<td width="2%"><s:a
													href="javascript:NewCal('paraFrm_relDate','DDMMYYYY');">
													<img src="../pages/images/recruitment/Date.gif"
														class="iconImage" height="16" align="absmiddle" width="16">
												</s:a></td>
											</tr>
											<tr>
												<td width="20%"><label name="emplastsal"
													id="emplastsal" ondblclick="callShowDiv(this);"><%=label.get("emplastsal")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield theme="simple"
													name="empSal" maxlength="10" size="25"
													onkeypress="return numbersWithDot();" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="20%"><label name="ctc" id="ctc"
													ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield
													onkeypress="return numbersWithDot();" theme="simple"
													name="ctc" size="25" maxlength="10" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="20%"><label name="pfaccno" id="pfaccno"
													ondblclick="callShowDiv(this);"><%=label.get("pfaccno")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield theme="simple" name="pfno"
													maxlength="15" size="25" onkeypress="return alphaNumeric()" /></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="20%"><label name="jobdesc" id="jobdesc"
													ondblclick="callShowDiv(this);"><%=label.get("jobdesc")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textarea theme="simple"
													name="jobDesc" rows="3"
													onkeyup="callLength('jobDesc','disCnt','255');" /></td>
												<td width="2%"></td>
											</tr>
											<tr>
												<td width="20%"></td>
												<td width="2%">&nbsp;</td>
												<td width="2%"></td>
												<td width="22%"></td>
												<td width="2%"></td>
												<td width="4%">&nbsp;</td>
												<td width="20%"></td>
												<td width="2%">&nbsp;</td>
												<td width="2%"></td>
												<td width="22%" align="right"><img
													src="../pages/images/zoomin.gif" height="10"
													align="absmiddle" width="10" theme="simple"
													onclick="javascript:callWindow('paraFrm_jobDesc','jobdesc','','paraFrm_disCnt','255');"><label><font
													size="0.5">&nbsp;&nbsp;Remaining Chars:</font></label><s:textfield
													name="disCnt" readonly="true" size="1"></s:textfield></td>
												<td width="2%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
								</s:if><!--end if edit flag -->
								<tr>
									<td>
									<fieldset><legend class="legend1">Work
									Experience Details</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<table width="100%" border="0" align="left" cellpadding="2"
												cellspacing="1">
												<tr bgcolor="#EEF4FB">
													<td align="center" width="17%"><label name="preuniemp"
														id="preuniemp" ondblclick="callShowDiv(this);"><%=label.get("preuniemp")%></label></td>
													<td align="center" width="17%"><label
														name="designation" id="designation3"
														ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
													</td>
													<td align="center" width="18%"><label name="jobdesc"
														id="jobdesc1" ondblclick="callShowDiv(this);"><%=label.get("jobdesc")%></label>
													</td>
													<td align="center" width="12%"><label name="joindate"
														id="joindate1" ondblclick="callShowDiv(this);"><%=label.get("joindate")%></label></td>
													<td align="center" width="12%"><label
														name="relivedate" id="relivedate"
														ondblclick="callShowDiv(this);"><%=label.get("relivedate")%></label></td>
													<td width="17%" align="center"><label
														name="projectDtl" id="projectDtl"
														ondblclick="callShowDiv(this);"><%=label.get("projectDtl")%></label></td>
													<td width="7%" align="center"><label id="edit">Edit|Delete</label></td>


												</tr>
												<!-- Iterator to display previous employer details -->
												<s:iterator value=" empExpList" status="stat">
													<tr class="sortableTD">
														<td height="21" valign="top" class="text1" title="<s:property value="employer" />"><s:hidden
															name="expId" /><s:property value="abbrEmployer" /></td>
														<td valign="top" class="text1" title="<s:property
															value="designation" />"><s:property
															value="abbrDesignation" /></td>
														<td valign="top" class="text1" title="<s:property
															value="jobDesc" />"><s:property
															value="abbrJobDesc" /></td>
														<td align="center" valign="top" class="text1"><s:property
															value="joiningDate" /></td>
														<td align="center" valign="top" class="text1"><s:property
															value="relDate" /></td>
														<td valign="top" class="text1" ><font style="color:#003366 ;text-decoration: underline; "><a
															href="#" onclick="projectFun('<s:property value="expId"/>')"><label style="color:#003366; cursor:pointer;" >Add Project</label></a></font>
														<%int i=0; %>
														<!--nested Iterator to display previous employers project names -->
														<s:iterator value="projNameList">
															<table >
															<tr></tr>
																<tr>
																	<s:hidden name="projCode" /><s:hidden name="projExpId" />
																	<td class="text1" ><%=++i%>.</td>
																	<td title="<s:property value="projName"/>"><font style="color:#003366 ;text-decoration: underline; "><a
															href="#" onclick="editProjectFun('<s:property value="projCode"/>','<s:property value="projExpId"/>')"><label style="color:#003366; cursor:pointer;" ><s:property value="abbrProjName"/></label></a></font></td>
																</tr>
															</table>
														</s:iterator><!-- end nested Iterator to display previous employers project names -->
														</td>
														<td align="center" valign="top"><s:if test="updateFlg">
															<a href="#"><img src="../pages/mypage/images/icons/edit.png"
																width="10" height="10" border="0"
																onclick="callForEdit('<s:property value="expId"/>')" /></a>
														</s:if> | <s:if test="deleteFlg">
															<a href="#"><img src="../pages/mypage/images/icons/delete.png"
																width="10" height="10" border="0"
																onclick="callForDelete('<s:property value="expId"/>')" /></a>
														</s:if></td>
													</tr>
												</s:iterator><!--end of Iterator to display previous employer details -->
											</table>
											</td>
										</tr>
										<s:if test="noData"><!-- if nodata -->
											<tr>
												<td colspan="8" align="center"><font color="red">No
												data to display</font></td>
											</tr>
										</s:if><!--end if nodata -->
									</table>
									</fieldset>
									</td>
								</tr>


								<tr height="10"></tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr>
									<td width="100%">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag"><!-- if edit flag -->
											<tr align="right">
												<s:if test="updateFlg"><!-- if update flag -->
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:if><!--end  if update flag -->
												<s:elseif test="insertFlg"><!-- elseif insert flag -->
													<td width="18%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="18%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="18%">|</td>
												</s:elseif><!--end elseif insert flag -->
												<td width="18%"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" /></td>
												<td width="18%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>

										</s:if><!--end if edit flag -->
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
					</fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
</s:form>

<script>
function addFun(){
	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="EmployeeExperience_add.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}

}

function callForEdit(id){
		document.getElementById('paraFrm_paraExpId').value=id;
		document.getElementById('paraFrm').action="EmployeeExperience_edit.action";
		document.getElementById('paraFrm').submit();
}

function callForDelete(id){
	 var conf=confirm("Are you sure to delete this record ? ");
		if(conf){
				document.getElementById('paraFrm_paraExpId').value=id;
				document.getElementById('paraFrm').action="EmployeeExperience_delete.action";
				document.getElementById('paraFrm').submit();
   		}
}

function cancelFun(){
	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="EmployeeExperience_cancelFunc.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}

function searchFun(){
	 javascript:callsF9(500,325,'EmployeeExperience_f9empaction.action'); 
}

function saveFun(){
	try{
		if(trim(document.getElementById('paraFrm_employer').value)==""){
		alert("Please enter "+document.getElementById('preuniemp').innerHTML.toLowerCase());
		return false;
		}
		
		if(!document.getElementById('paraFrm_joiningDate').value==""){
		if(! validateDate('paraFrm_joiningDate','joindate')){
		return false;
		}
		}
		if(!document.getElementById('paraFrm_relDate').value==""){
		if(! validateDate('paraFrm_relDate','relivedate')){
		return false;
		}
		}
		if(!document.getElementById('paraFrm_jobDesc').value==""){
		if(document.getElementById('paraFrm_disCnt').value < 0){
		alert("Maximum 255 characters are allowed in "+document.getElementById('jobdesc').innerHTML.toLowerCase());
		return false;
		}
		}
		var doj=document.getElementById('paraFrm_joiningDate').value;
		var dor=document.getElementById('paraFrm_relDate').value;
		if(!(doj=="" && dor=="")){
		if(!dateDifference(doj,dor, 'paraFrm_relDate','joindate','relivedate')){
		return false;
		}
		}
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="EmployeeExperience_save.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}

function projectFun(id){
try{
	document.getElementById('paraFrm_paraExpId').value=id;
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="EmployeeExperience_addProject.action";
	document.getElementById('paraFrm').submit();
  }catch(e){}
}

function editProjectFun(id1,id2){
try{
	document.getElementById('paraFrm_paraProjId').value=trim(id1);
	document.getElementById('paraFrm_paraExpId').value=trim(id2);
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="EmployeeExperience_editProject.action";
	document.getElementById('paraFrm').submit();
  }catch(e){}
}
</script>