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
	validate="true" theme="simple">
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:hidden name="editFlag" />
	<s:hidden name="empId" />
	<s:hidden name="paraExpId"/>
	<s:hidden name="paraProjId"/>
	<s:hidden name="projectExpId"/>
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
										<tr align="right">
										<s:if test="editFlag">
											<s:if test="updateFlg">
												<td width="18%"><a href="#"><img
													src="../pages/mypage/images/icons/save.png"
													onclick="saveFun()" width="10" height="10" border="0" /></a></td>
												<td width="18%"><a href="#" onclick="saveFun()"
													class="iconbutton">Save</a></td>
												<td width="18%">|</td>
											</s:if>
											<s:elseif test="insertFlg">
												<td width="18%"><a href="#"><img
													src="../pages/mypage/images/icons/save.png"
													onclick="saveFun()" width="10" height="10" border="0" /></a></td>
												<td width="18%"><a href="#" onclick="saveFun()"
													class="iconbutton">Save</a></td>
												<td width="18%">|</td>
											</s:elseif>
											</s:if>
											<td width="93%"align="right"><img
												src="../pages/mypage/images/icons/cancel.png"
												onclick="cancelFun()" width="10" height="10" /></td>
											<td width="5%"align="right"><a href="#" onclick="cancelFun()"
												class="iconbutton">Cancel</a></td>
											<td width="2%">|</td>
											
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr id="projectDtls">
									<s:if test="editFlag">
										<td colspan="11">
										<fieldset><legend class="legend1">
										Employee Project Particulars</legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<s:hidden name="projectCode" />
											<tr>
												<td width="18%"><label name="project.name"
													id="project.name" ondblclick="callShowDiv(this);"><%=label.get("project.name")%></label></td>
												<td width="2%" class="star">*</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield name="projectName"
													size="25" onkeypress="return allCharacters();"
													maxlength="100" /></td>
												<td width="3%"></td>
												<td width="3%">&nbsp;</td>
												<td width="18%"><label name="project.duration"
													id="project.duration" ondblclick="callShowDiv(this);"><%=label.get("project.duration")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield name="projectDuration"
													size="25" onkeypress="return numbersOnly();" maxlength="2" /></td>
												<td width="3%"></td>
											</tr>
											<tr>
												<td width="18%"><label name="project.Tsize"
													id="project.Tsize" ondblclick="callShowDiv(this);"><%=label.get("project.Tsize")%></label></td>
												<td width="2%"></td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:textfield
													name="teamSize" size="25"
													onkeypress="return numbersOnly();" maxlength="3" /></td>
												<td width="3%"></td>
												<td width="3%">&nbsp;</td>
												<td width="18%"><label name="project.role"
													id="project.role" ondblclick="callShowDiv(this);"><%=label.get("project.role")%></label></td>
												<td width="2%"></td>
												<td width="2%">:</td>
												<td width="22%"><s:textfield name="ProjectRole"
													size="25" onkeypress="return allCharacters();"
													maxlength="100" /></td>
												<td width="3%"></td>
											</tr>
											<tr>
												<td width="18%"><label name="project.description"
													id="project.description" ondblclick="callShowDiv(this);"><%=label.get("project.description")%></label></td>
												<td width="2%">&nbsp;</td>
												<td width="2%">:</td>
												<td width="22%" class="text1"><s:textarea
													theme="simple" name="projectDesc" rows="3"
													onkeyup="callLength('projectDesc','descCnt','500');" /></td>
												<td width="3%"></td>
												<td width="3%">&nbsp;</td>
												<td width="18%"></td>
												<td width="2%"></td>
												<td width="2%"></td>
												<td width="22%"></td>
												<td width="3%"></td>
											</tr>
											<tr>
												<td width="18%"></td>
												<td width="2%">&nbsp;</td>
												<td width="2%"></td>
												<td width="22%" align="right"><label><font
													size="0.5">&nbsp;&nbsp;Remaining Chars:</font></label><s:textfield
													name="descCnt" readonly="true" size="1"></s:textfield></td>
												<td width="3%"></td>
												<td width="3%"></td>
												<td width="18%"></td>
												<td width="2%"></td>
												<td width="2%"></td>
												<td width="22%"></td>
												<td width="3%"></td>
											</tr>
										</table>
										</fieldset>
										</td>
									</s:if>
								</tr>
								<tr id="projectInfo">
									<td>
									<fieldset><legend class="legend1">Employee
									Project Details</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
									<td align="right"><input type="button" class="token"
								name="Add" value="Add Project" onclick="addProjFun();" />
									</td>
									</tr>
										<tr>
											<td>
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2">

												<tr>
													<td align="center" width="8%" bgcolor="#EEF4FB"><label
														name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
													<td align="center" width="17%" bgcolor="#EEF4FB"><label
														name="project.name" id="project.name1"
														ondblclick="callShowDiv(this);"><%=label.get("project.name")%></label>
													</td>
													<td align="center" width="16%" bgcolor="#EEF4FB"><label
														name="project.duration" id="project.duration1"
														ondblclick="callShowDiv(this);"><%=label.get("project.duration")%></label></td>
													<td align="center" width="17%" bgcolor="#EEF4FB"><label
														name="project.Tsize" id="project.Tsize1"
														ondblclick="callShowDiv(this);"><%=label.get("project.Tsize")%></label></td>
													<td align="center" width="17%" bgcolor="#EEF4FB"><label
														name="project.role" id="project.role1"
														ondblclick="callShowDiv(this);"><%=label.get("project.role")%></label></td>
													<td align="center" width="19%" bgcolor="#EEF4FB"><label
														name="project.description" id="project.description1"
														ondblclick="callShowDiv(this);"><%=label.get("project.description")%></label></td>
													<td width="8%" bgcolor="#EEF4FB" align="center">Edit/Delete</td>

												</tr>
												<%
													int i = 0;
													%>
												<s:iterator value=" projectList">
													<tr class="sortableTD">
														<s:hidden name="projectItt" />
														<td valign="top" class="text1" class="sortableTD" align="center"><%=++i %>
														<s:hidden name="srNo" /></td>
														<s:hidden name="projectExpId"></s:hidden>
														<td valign="top" class="text1" title="<s:property
															value="projectNameItt" />"><s:property
															value="abbrProjectName" /><s:hidden name="projectNameItt" /></td>
														<td valign="top" class="text1" align="center"><s:property
															value="projectDurationItt " /><s:hidden name="projectDurationItt" /></td>
														<td valign="top" class="text1" align="center"><s:property
															value="teamSizeItt" /><s:hidden name="teamSizeItt" /></td>
														<td valign="top" class="text1" title="<s:property
															value="projectRoleItt" />" ><s:property
															value="abbrProjectRole" /><s:hidden name="projectRoleItt" /></td>
														<td valign="top" class="text1" title="<s:property
															value="projectDescItt" />"><s:property
															value="abbrProjectDesc" /><s:hidden name="projectDescItt" /></td>
														<td valign="top" class="text1" class="sortableTD"
															align="center"><s:if test="%{empExp.updateFlag}">
															<a href="#"><img src="../pages/mypage/images/icons/edit.png"
																width="10" height="10" class="iconImage"
																onclick="return editProject('<s:property value="projectItt"/>');" /></a>
														</s:if> | <s:if test="%{empExp.deleteFlag}">
															<a href="#"><img src="../pages/mypage/images/icons/delete.png"
																width="10" height="10" class="iconImage"
																onclick="deleteProject('<s:property value="projectItt"/>');" /></a>
														</s:if></td>
													</tr>
												</s:iterator>

											</table>
											</td>
										</tr>

										<s:if test="noProjData">
											<tr>
												<td colspan="8" align="center"><font color="red">No
												data to display</font></td>
											</tr>
										</s:if>
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
										<tr align="right">
										<s:if test="editFlag">
											<s:if test="updateFlg">
												<td width="18%"><a href="#"><img
													src="../pages/mypage/images/icons/save.png"
													onclick="saveFun()" width="10" height="10" border="0" /></a></td>
												<td width="18%"><a href="#" onclick="saveFun()"
													class="iconbutton">Save</a></td>
												<td width="18%">|</td>
											</s:if>
											<s:elseif test="insertFlg">
												<td width="18%"><a href="#"><img
													src="../pages/mypage/images/icons/save.png"
													onclick="saveFun()" width="10" height="10" border="0" /></a></td>
												<td width="18%"><a href="#" onclick="saveFun()"
													class="iconbutton">Save</a></td>
												<td width="18%">|</td>
											</s:elseif>
											</s:if>
											<td width="93%" align="right"><img
												src="../pages/mypage/images/icons/cancel.png"
												onclick="cancelFun()" width="10" height="10" /></td>
											<td width="5%" align="right"><a href="#" onclick="cancelFun()"
												class="iconbutton">Cancel</a></td>
											<td width="2%">|</td>
										
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
			</td>
		</tr>
	</table>
	</div>
</s:form>

<script>

function cancelFun(){
try{
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="EmployeeExperience_cancelFunc.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}

function addProjFun(){
try{
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="EmployeeExperience_addProject.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}
function editProject(id){
try{	
		document.getElementById('paraFrm_paraProjId').value=trim(id);
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="EmployeeExperience_editProject.action";
		document.getElementById('paraFrm').submit();
	}catch(e){alert(e);}
}

function deleteProject(id){
 var conf=confirm("Are you sure to delete this record ? ");
		if(conf){
				document.getElementById('paraFrm_paraProjId').value=trim(id);
				document.getElementById('paraFrm').target="_self";
				document.getElementById('paraFrm').action="EmployeeExperience_deleteProject.action";
				document.getElementById('paraFrm').submit();
   		}
}
function saveFun(){
	if(trim(document.getElementById('paraFrm_projectName').value)==""){
	alert("Please enter "+document.getElementById('project.name').innerHTML.toLowerCase());
	return false;
	}
	if(!document.getElementById('paraFrm_projectDesc').value==""){
		if(document.getElementById('paraFrm_descCnt').value < 0){
		alert("Maximum 500 characters are allowed in "+document.getElementById('project.description').innerHTML.toLowerCase());
		return false;
		}
		}
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="EmployeeExperience_saveProject.action";
	document.getElementById('paraFrm').submit();
}
</script>