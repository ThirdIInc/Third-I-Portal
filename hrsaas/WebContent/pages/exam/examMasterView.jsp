  <%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var subjectArray = new Array();
var categoryArray=new Array();
</script>
<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
<%
			int categoryCount = request.getAttribute("categoryListSize") == null ? 0
			: Integer.parseInt(request.getAttribute("categoryListSize")
			.toString());
%>

<s:form action="ExamMaster" validate="true" id="paraFrm" 
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="checkEdit" />
	<!--<s:hidden name="editFlag"/>-->
	<s:hidden name="cancelFlag" />
	<s:hidden name="subjectCode" />
	<s:hidden name="categoryListlength" />
	<s:hidden name="updateCatFlag" />
	<s:hidden name="ctgryCode" id="ctgryCode" />
	<s:hidden name="category" id="category" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Module</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
		System.out.println("catch here");
		%>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" align="left"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<%
										int totalPage = request.getAttribute("abc") == null ? 0
										: (Integer) request.getAttribute("abc");
								int pageNo = request.getAttribute("xyz") == null ? 0
										: (Integer) request.getAttribute("xyz");
							%>
							<s:if test="flagShow">

								<td align="right" width="22%"><b>Page :</b> <input
									type="hidden" name="totalPage" id="totalPage"
									value="<%=totalPage%>"> <a href="#"
									onclick="callPage('1','F');"> <img  border="0" title="First Page"
									src="../pages/common/img/first.gif" width="10" height="10"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P','P');"> <img  border="0" title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event);return numbersOnly()"
									maxlength="4" /> of <%=totalPage%> <a href="#"
									onclick="callPage('N','N')"> <img  border="0" title="Next Page"
									src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
								<a href="#" onclick="callPage('<%=totalPage%>','L');"> <img  border="0" 
									title="Last Page" src="../pages/common/img/last.gif" width="10"
									height="10" class="iconImage" /> </a></td>
							</s:if>

						</tr>
					</table>
					</td>
				</tr>

				<s:if test="flagShow">
					<tr>
						<td colspan="3">
						<%
						try {
						%>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="formbg">

							<tr>

								<td class="formtext">
								<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr>
										<td colspan="6" width="100%">
										<table width="100%">
											<tr>
												<td width="85%" align="left"><strong class="text_head">Module
												List : </strong></td>
												<!-- <td width="15%" align="right">
											    	<input type="button" class="delete" value="Delete" onclick="chkDelete();">
											    </td> -->
											</tr>
										</table>
										</td>
									</tr>

									<tr>
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" align="center" nowrap="nowrap"><label
											class="set" name="serial.no" id="serial.no"
											ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
										</td>
										<td class="formth" align="center"><label class="set"
											name="modulenamelabel" id="modulenamelabel1"
											ondblclick="callShowDiv(this);"><%=label.get("modulenamelabel")%></label>
										</td>
										<td class="formth" align="center"><label class="set"
											name="module.content" id="module.content"
											ondblclick="callShowDiv(this);"><%=label.get("module.content")%></label>
										</td>
										<td class="formth" align="center"><label class="set"
											name="module.sections" id="module.sections"
											ondblclick="callShowDiv(this);"><%=label.get("module.sections")%></label>
										</td>
										<td class="formth" align="center"><label class="set"
											name="module.questions" id="module.questions"
											ondblclick="callShowDiv(this);"><%=label.get("module.questions")%></label>
										</td>

										<td class="formth" align="center"><!-- <input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callSubjectAll();"/>  -->
										<label class="set" name="module.active" id="module.active"
											ondblclick="callShowDiv(this);"><%=label.get("module.active")%></label>
										</td>
										<!-- <td class="formth" align="center">
										<input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callSubjectAll();"/>													
									</td> -->

										<%
												int count = 0;
												int cn = pageNo * 20 - 20;
										%>
										<%!int d = 0;%>
										<%
										int i = 0, counter = 0;
										%>
										<s:if test="%{subjectList == null}">

											<tr>
												<td width="100%" align="center" colspan="5"><font
													color="red">There is no data to display.</font></td>
											</tr>
										</s:if>
										<s:else>
											<s:iterator value="subjectList">

												<tr <%if(count%2==0){
									%> class="tableCell1"
													<%}else{%> class="tableCell2" <%	}count++; %>
													onmouseover="javascript:newRowColor(this);"
													onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">
													<td width="5%" align="center" class="sortableTD">
													<%
													++i;
													%><%=++cn%></td>
													<td align="left"><a class="contlink" href="#"
														onclick="javascript:callForEdit(<s:property value="subjectCode"/>);" 
														title="<s:property value="subjectName" />">
												
												<!-- <s:hidden
											name="subjectName" value=""    />
											<s:hidden  value=""
											name="subjectAbbr" />
 												-->	
													<s:property value="subjectName"/> </td>
													<td align="center"><u><a class="contlink"
														href="ExamMaster_setContent.action?subjectCode=<s:property value="subjectCode"/>&from=list"
														onclick="javascript:callForContent(<s:property value="subjectCode"/>);"
														title="Manage Content"><font color="blue"><%=label.get("module.manage")%></font></a></u>
													</td>
													<td align="center"><u><a class="contlink" href="#"
														onclick="javascript:callForEdit(<s:property value="subjectCode"/>);"
														title="Manage Section"><font color="blue"><%=label.get("module.manage")%></font></a></u>
													</td>
													<td align="center"><u><a class="contlink"
														href="QuestionBankAction_setSubjectQues.action?subjectCode=<s:property value="subjectCode"/>&from=list"
														title="Manage Questions"><font color="blue"><%=label.get("module.manage")%></font></a></u>
													</td>

													<td align="center"><!-- <input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callSubjectAll();"/>  -->
													<s:property value="subjectStatus" /></td>

													<!-- <td class="sortableTD" width="15%" align="center">
												<input type="checkbox" class="checkbox" id="confChk<%=i%>"
													name="confChk"
													onclick="callForDelete1('<s:property value="subjectCode"/>','<%=i%>','<s:property value="categoryId"/>')" />
											</td> -->
												</tr>
												<%
												++counter;
												%>
											</s:iterator>
										</s:else>
										<%
										d = i;
										%>
									</tr>
								</table>

								<%
									} catch (Exception e) {
									}
								%>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>

				<s:else>

					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="0">

									<tr>
										<td width="10%" height="22" class="formtext"><label
											class="set" name="modulenamelabel" id="modulenamelabel2"
											ondblclick="callShowDiv(this);"><%=label.get("modulenamelabel")%></label>
										:<font color="red">*</font></td>
										<td width="22%" height="22"><s:textfield
											name="subjectName" maxlength="50" theme="simple" size="35" /></td>
										<td width="10%" height="22" class="formtext" nowrap="nowrap"><label
											class="set" name="module.abbr" id="module.abbr"
											ondblclick="callShowDiv(this);"><%=label.get("module.abbr")%></label>
										:<font color="red">*</font></td>
										<td height="22" width="22%"><s:textfield  maxlength="5"
											name="subjectAbbr"  size="5" /></td>

									</tr>
									<tr>
										<td width="10%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set" name="module.desc"
											id="module.desc" ondblclick="callShowDiv(this);"><%=label.get("module.desc")%></label>:
										</td>
										<td height="22" width="22%" colspan="1"><s:textarea
											name="subjectDesc" theme="simple" cols="32" rows="4"
											onkeyup="callLength('subjectDesc','descCnt','300');"></s:textarea>
										<img  border="0" src="../pages/images/zoomin.gif" id='ctrlHide'
											height="12" align="absmiddle" width="12" theme="simple"
											onclick="javascript:callWindow('paraFrm_subjectDesc','module.desc','','paraFrm_descCnt','300');">
										</td>
										<!--<td id='ctrlHide' width="10%" valign="bottom">
										Remaining char</td>
										<td valign="bottom" width="22%"><s:textfield  name="descCnt" readonly="true"
											size="5"/></td>
									--></tr>
									<tr>
										<td width="10%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set" name="module.stat"
											id="module.stat" ondblclick="callShowDiv(this);"><%=label.get("module.stat")%></label>:
										</td>
										<td height="22" colspan="3"><s:select
											name="subjectStatus" theme="simple"
											list="#{'A':'Active','D':'Inactive'}" /></td>
									</tr>
									<tr>
									<s:if test="subjectCode ==''">
									</s:if>
									<s:else>
										
											<td></td>
											<td width="10%" height="22" class="formtext" colspan="3">

											<a
												href="ExamMaster_setInstruction.action?subjectCode=<s:property value="subjectCode"/>&from=edit"
												onclick="javascript:return addInstruction();"> <u><font
												color="blue" size="2">Manage Instructions </font></u></a> &nbsp;&nbsp;&nbsp;
											<a
												href="ExamMaster_setContent.action?subjectCode=<s:property value="subjectCode"/>&from=edit"
												onclick="javascript:return addInstruction();"> <u><font
												color="blue" size="2"> Manage Content </font></u></a> &nbsp;&nbsp;&nbsp; <a
												href="QuestionBankAction_setSubjectQues.action?subjectCode=<s:property value="subjectCode"/>"
												onclick="javascript:return addInstruction();"> <u><font
												color="blue" size="2"> Manage Questions </font></u></a></td>										
									</s:else>
									</tr>
									<tr>
										<td width="10%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set"
											name="module.enableSections" id="module.enableSections"
											ondblclick="callShowDiv(this);"><%=label.get("module.enableSections")%></label>:
										</td>
										<td height="22" colspan="3"><s:checkbox
											name="enableSection" onclick="checkEnableSec();" /></td>
									</tr>
									<tr id="addSectionTr">
										<td width="10%" height="22" class="formtext" nowrap="nowrap">
										<label class="set" name="module.moduleSections"
											id="module.moduleSections" ondblclick="callShowDiv(this);"><%=label.get("module.moduleSections")%></label>
										:</td>
										<td height="22" width="10%"><s:textfield
											name="categoryName" maxlength="50" theme="simple" size="35" value="" /></td>
										<td><s:submit value="Add/Update"
											action="ExamMaster_addSection" onclick="return addSection();" /></td>
										<td height="22"  class="formtext">&nbsp;</td>
										
									</tr>
									
									<tr style="" id="categoryStatusTr">
										<td width="10%" height="22" class="formtext" nowrap="nowrap">
										<label class="set" name="module.moduleSectionsStatus"
											id="module.moduleSectionsStatus"
											ondblclick="callShowDiv(this);"> <%=label.get("module.moduleSectionsStatus")%></label>
										:</td>
										<td colspan="3"><s:select name="categoryStatus" theme="simple"
											cssStyle="" list="#{'Y':'Active','N':'Inactive'}" /></td>
									</tr>




									<!-- 
								<tr>
									<td width="22%" class="formtext" nowrap="nowrap">
									<label  class = "set" name="module.content" id="module.content" ondblclick="callShowDiv(this);"><%=label.get("module.content")%></label>:
									</td>
									<td width="88%" colspan="3">
								
										<s:hidden name="htmlcontent"/>
										<textarea style="" id="content" name="content" theme="simple" 
						onfocus="oFCKeditor.onClickFocus('content');" onblur="onClickFocus('content');"></textarea>
										
									</td>
									
								</tr>
								-->

									<!-- List of Records of Subject Category -->


									<tr>
										<td colspan="4"><s:if test="%{catList}">

											<%
											try {
											%>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" class="formbg">
												<tr>

													<td class="formtext">
													<table width="100%" border="0" cellpadding="1"
														cellspacing="1">
														<tr>
															<td height="15" class="formhead" nowrap="nowrap"><strong
																class="forminnerhead">Section List</strong></td>
														</tr>

														<tr>

															<td class="formth" align="center"><label class="set"
																name="module.srno" id="module.srno"
																ondblclick="callShowDiv(this);"><%=label.get("module.srno")%></label>
															</td>

															<td class="formth" align="center"><label class="set"
																name="module.sectionName" id="module.sectionName"
																ondblclick="callShowDiv(this);"><%=label.get("module.sectionName")%></label>
															</td>
															<td class="formth" align="center"><label class="set"
																name="module.order" id="module.order"
																ondblclick="callShowDiv(this);"><%=label.get("module.order")%></label></td>
															<td class="formth" align="center"><label class="set"
																name="module.content" id="module.content"
																ondblclick="callShowDiv(this);"><%=label.get("module.content")%></label></td>
															<td class="formth" align="center"><label class="set"
																name="module.questions" id="module.questions"
																ondblclick="callShowDiv(this);"><%=label.get("module.questions")%></label></td>
															<td class="formth" align="center"><label class="set"
																name="module.active" id="module.active"
																ondblclick="callShowDiv(this);"><%=label.get("module.active")%></label></td>
															<td class="formth" align="center"><label class="set"
																name="module.delete" id="module.delete"
																ondblclick="callShowDiv(this);"><%=label.get("module.delete")%></label></td>

														</tr>
														<%
														int counts = 0;
														%>
														<%!int cc = 0;%>
														<%
														int ii = 0;
														%>
														<s:hidden name="hEditCatCode" />
														<s:iterator value="categoryList">
															<tr class="tableCell1">
																<td align="center" class="sortableTD"><%=++ii%></td>


																<td align="left" class="sortableTD"
																	id="catCodeTd<%=ii %>"><input type="hidden"
																	name="categoryCode" id="categoryCode<%=ii%>"
																	value="<s:property value="categoryCode"/>" /> <u><a
																	href="#"
																	onclick="javascript:callForEditCat('<s:property value="categoryCode"/>','<s:property value="categoryName" />','<s:property value="catStatus" />');"
																	title="<s:property value="categoryName" />"> <u><s:property
																	value="categoryName" /></a></u><input type="hidden"
																	name="hdeleteCatCode" id="hdeleteCatCode<%=ii%>" /></td>
																<td align="center"><input type="hidden"
																	name="categoryOrder" id="categoryOrder<%=ii%>"
																	value="<%=ii %>" />
																<table width="100%">
																	<tr width="100%">
																		<td align="right" width="50%">
																		<%
																		if (categoryCount == 1) {
																		%> - <%
																		}
																		%> <%
 if (ii != 1) {
 %> <a href="#"
																			onclick="orderUp('<s:property value="categoryOrder"/>',<%=ii %>);">
																		<img  border="0" src="../pages/common/css/default/images/up.GIF"
																			width="10" height="10"> </a> <%
 }
 %>
																		</td>
																		<td align="left" width="50%">
																		<%
																		if (ii != categoryCount) {
																		%> <a href="#"
																			onclick="orderDown('<s:property value="categoryOrder"/>',<%=ii %>);">
																		<img border="0" src="../pages/common/css/default/images/down.GIF"
																			width="10" height="10"> </a> <%
 }
 %>
																		</td>
																	</tr>
																</table>
																</td>
																<td align="center" id="catMngContTd<%=ii %>"><u><a
																	href="ExamMaster_setContent.action?categoryCode=<s:property value="categoryCode"/>&from=catedit"><font color="blue">Manage</font></a></u></td>
																<td align="center" id="catMngQueTd<%=ii %>"><u><a
																	href="QuestionBankAction_setCategoryQues.action?categoryCode=<s:property value="categoryCode"/>"><font color="blue">Manage</font></a></u></td>
																<td align="center" id="catStatusTd<%=ii %>"><!-- <s:select name="catStatus" theme="simple" 
																	list="#{'Y':'Active','N':'Inactive'}" />
																--> <s:if test='catStatus=="Y"'>Active</s:if> <s:else>Inactive</s:else>
																</td>
																<td align="center" id="catDelTd<%=ii %>"><u><a
																	href="#"
																	onclick="javascript:callForCatDelete(<s:property value="categoryCode"/>,<%=ii%>);">
																<img  border="0" style="cursor: pointer;"
																	src="../pages/mypage/images/icons/delete.png"
																	title="Delete"> </a></u></td>

															</tr>
														</s:iterator>
														<%
																cc = ii;
																ii = 0;
														%>

													</table>
													<%
															} catch (Exception e) {
															e.printStackTrace();
														}
													%>
													</td>
												</tr>
											</table>
										</s:if></td>
									</tr>


								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</s:else>


			</table>
			</td>
		</tr>
		<tr>
			<td width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="22%"><s:if test="flagShow">
				<strong class="forminnerhead">Total no. of Records :<s:property
					value="dataLength" /></strong>
			</s:if></td>
		</tr>
	</table>
</s:form>

<script>
 
var f9Fields= ["paraFrm_subjectName","paraFrm_subjectAbbr"];
var fieldName = ["paraFrm_subjectName","paraFrm_subjectAbbr"];
var lableName = ["modulenamelabel","module.abbr"];
var type = ['enter','enter'];

//For Addnew Button 

function addnewFun()
{
	document.getElementById('paraFrm').action="ExamMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun() {	
	
	if(!validateBlank(fieldName,lableName,type))
		return false;
          
	if(!f9specialchars(f9Fields)) {
		alert("Special characters not allowed");
		return false;
	}
 
	var desc = document.getElementById('paraFrm_subjectDesc').value;
	var categoryCount = '<%=categoryCount %>'
	
	if(desc != "" && desc.length >250){
		
		alert("Maximum length of <%=label.get("module.desc")%> is 250 characters.");
		return false;
    }
    if( document.getElementById('paraFrm_subjectCode').value != "" && categoryCount == 0 && document.getElementById('paraFrm_enableSection').checked){
		
		alert("Please add atleast one section");
		return false;
    }
  
  document.getElementById('paraFrm').action="ExamMaster_save.action";
  document.getElementById('paraFrm').submit();
  return true;
}

//For Edit Button

function editFun() {
	//document.getElementById('paraFrm').action="ExamMaster_edit.action";
	//document.getElementById('paraFrm').submit();
	return true;
}

//For Delete Button

function deleteFun() {
	var con=confirm('Are you sure to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="ExamMaster_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"ExamMaster_f9Action.action");
	
}

//For Cancel Button

function backFun(){
    	document.getElementById('paraFrm').action="ExamMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
    
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="ExamMaster_report.action";
    document.getElementById("paraFrm").submit();
}

function addSection() {
	var fields= ["paraFrm_categoryName"];
	if(!f9specialchars(fields)) {
		alert("Special characters not allowed");
		return false;
	}	
	var subjectCode=document.getElementById('paraFrm_subjectCode').value;
	var catName=document.getElementById('paraFrm_categoryName').value;
	catName = trim(catName);
	if(catName=="")	{
		alert('Please enter ' + document.getElementById('module.moduleSections').innerHTML );
		document.getElementById('paraFrm_categoryName').value = "";
		document.getElementById('paraFrm_categoryName').focus();
		return false;
	} else if(subjectCode=="")	{
		alert('Please save Module first');			
		return false;		
	} else {
		return true;
	} 
	
}
function callPage(id,pageImg){

	pageNo =document.getElementById('pageNoField').value;	
	totalPage =document.getElementById('totalPage').value;	 
 	 
	if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  

		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
	document.getElementById('myPage').value=id;
	//document.getElementById('paraFrm_show').value=id;
 	document.getElementById('paraFrm').action="ExamMaster_callPage.action";
 	document.getElementById('paraFrm').submit();
		
	}	


function callForEditCat(catCode, catName, catstatus){

	//if(document.getElementById('paraFrm_enableSection').checked){
		document.getElementById('paraFrm_hEditCatCode').value=catCode;
		document.getElementById('paraFrm_categoryName').value=catName;
		document.getElementById('categoryStatusTr').style.display = "";
		document.getElementById('addSectionTr').style.display = "";
		document.getElementById('paraFrm_categoryStatus').selected="N";
		document.getElementById('paraFrm_enableSection').checked = 'true';
		
		if(catstatus=='Y') {
			document.getElementById('paraFrm_categoryStatus').options[0].text="Active";
			document.getElementById('paraFrm_categoryStatus').options[0].value="Y";
			document.getElementById('paraFrm_categoryStatus').options[0].selected="selected";
			document.getElementById('paraFrm_categoryStatus').options[1].text="Inactive";
			document.getElementById('paraFrm_categoryStatus').options[1].value="N";	
		} else {
		 	document.getElementById('paraFrm_categoryStatus').options[0].text="Inactive";
			document.getElementById('paraFrm_categoryStatus').options[0].value="N";
			document.getElementById('paraFrm_categoryStatus').options[0].selected="selected";
			document.getElementById('paraFrm_categoryStatus').options[1].text="Active";
			document.getElementById('paraFrm_categoryStatus').options[1].value="Y";	
		}
	/*}else{
		alert("Please enable section first");
	}
	*/	   
}



function changeCatStatus(id, catCode){
	var categoryCode = document.getElementById('paraFrm_categoryCode' + catCode).value;
	var status = id.value;
	alert(categoryCode +"," + status);
/*
	$.ajax({
		url : "ExamMaster_updateCategoryStatus.action?categoryCode="+ categoryCode +"&categoryStatus=" + status,
		success : function(result){
			alert(result);
		}
	});
*/
}

function callForEdit(id,id1){
	//document.getElementById('paraFrm_hiddencode').value=id;
	document.getElementById('paraFrm_hiddencode').value=id;
	document.getElementById('paraFrm_subjectCode').value=id;
	document.getElementById("paraFrm").action="ExamMaster_calforedit.action";
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").submit();
}

function callForDelete(edit){
//alert(edit);
document.getElementById('paraFrm_checkEdit').value=edit;
//alert(document.getElementById('paraFrm_checkEdit').value);
	    var conf=confirm("Are you sure to delete this subject ?");
  		if(conf) {
  	
  		document.getElementById('paraFrm_checkEdit').value=edit;
		document.getElementById("paraFrm").action="ExamMaster_calfordelete.action";
		
		document.getElementById("paraFrm").submit();
			//document.getElementById('paraFrm_checkEdit').value="";
		}
}
function callForDelete1(id,i,id1)
{
		if(document.getElementById('confChk'+i).checked == true)
		{	  
		  	 document.getElementById('hdeleteCode'+i).value=id;
		   	 document.getElementById('hdeleteCatId'+i).value=id1;
		}else{
			document.getElementById('hdeleteCode'+i).value="";
			document.getElementById('hdeleteCatId'+i).value="";
		}
}
function callForCatDelete(edit,ii) {
	var con=confirm('Do you really want to delete the records ?');
	if(con) {
		document.getElementById('hdeleteCatCode'+ii).value=edit;
		document.getElementById('paraFrm').action="ExamMaster_deleteSection.action";
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
			
}
function newRowColor(cell)
{
		cell.className='Cell_bg_first';
}
function oldRowColor(cell,val) {
		
 			cell.className='Cell_bg_second';
		
}
function chkDelete() {
	if(chk()) {
		var con=confirm('Do you really want to delete the record ?');
		if(con) {
			document.getElementById('paraFrm').action="ExamMaster_delete1.action";
			document.getElementById('paraFrm').submit();
		} else {
			var flag='<%=d %>';
			for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hdeleteCode'+a).value="";
				document.getElementById('hdeleteCatId'+a).value="";
				document.getElementById('paraFrm_chkAll').checked=false;
			}
			return false;
		}
	} else {
		alert('Please select atleast one record to delete');
		return false;
	}
}
function chk(){
		var flag='<%=d %>';
		for(var a=1;a<=flag;a++){	
		if(document.getElementById('confChk'+a).checked == true){	
			return true;
		}	   
}
return false;
}
<%--
function chkDeleteCat()
{
		if(chkCat()){
			var con=confirm('Do you really want to delete the records ?');
		if(con){
			document.getElementById('paraFrm').action="ExamMaster_calfordelete.action";
			document.getElementById('paraFrm').submit();
		}
		else{
			var flag='<%=cc %>';
			for(var a=1;a<=flag;a++){	
			document.getElementById('confChkCat'+a).checked = false;
			document.getElementById('hdeleteCatCode'+a).value="";
		}
		return false;
		}
		}
		else {
			alert('Please select atleast one record to delete');
			return false;
		}
}
function chkCat(){
		var flag='<%=cc %>';
		for(var a=1;a<=flag;a++){	
		if(document.getElementById('confChkCat'+a).checked == true){	
			return true;
		}	   
}
return false;
}

--%>

function callSubjectAll() {
	var rowLen ='<%=d %>';	
	if (document.getElementById("paraFrm_chkAll").checked){
		for(var idx=1;idx<=rowLen;idx++){
			document.getElementById("confChk"+idx).checked ="true";
			document.getElementById('hdeleteCode'+idx).value=subjectArray[idx-1];
			document.getElementById('hdeleteCatId'+idx).value=categoryArray[idx-1];
		}
	} else {
		for (var idx = 1; idx <= rowLen; idx++) {
			document.getElementById("confChk"+idx).checked = false;
			document.getElementById('hdeleteCode'+idx).value="";
			document.getElementById('hdeleteCatId'+idx).value="";
		}
	}	 
}    

function callPageText(id){
 
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	        document.getElementById('myPage').value=pageNo;
		    document.getElementById('paraFrm').action='ExamMaster_callPage.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	function callForCatQuestions(categoryCode){
		document.getElementById('paraFrm').action='QuestionBankAction_setCategoryQues.action?categoryCode = ' + categoryCode;
		document.getElementById('paraFrm').submit();
	}
	
	
	function checkEnableSec(){
		if(document.getElementById('paraFrm_enableSection').checked){
			document.getElementById('paraFrm_enableSection').value = "Y";
			document.getElementById('addSectionTr').style.display = "";
			document.getElementById('categoryStatusTr').style.display = "";
		}else{
			document.getElementById('paraFrm_enableSection').value = "N";
			document.getElementById('addSectionTr').style.display = "none";
			document.getElementById('categoryStatusTr').style.display = "none";
			document.getElementById('paraFrm_categoryName').value = "";
		}
		 
	}

	
	checkEnableSec();
	
	function addInstruction(){
		var subjectCode=document.getElementById('paraFrm_subjectCode').value;
		if(subjectCode=="")	{
			alert('Please save Module first');
			return false;		
		}  else {
			return true;
		}
	}
	
	function orderUp(order, ii){
		var temp = ""; //document.getElementById('categoryOrder'+ii).value;
		/*document.getElementById('categoryOrder'+ii).value = document.getElementById('categoryOrder' +(ii-1)).value;
		document.getElementById('categoryOrder' +(ii-1)).value = temp;
		*/
		if(document.getElementById('catCodeTd' +(ii-1)) == null ){
			alert("This is first section you cannot go up");
		} else {
		temp = document.getElementById('catDelTd'+ii).innerHTML;
		document.getElementById('catDelTd'+ii).innerHTML = document.getElementById('catDelTd' +(ii-1)).innerHTML;
		document.getElementById('catDelTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('catMngContTd'+ii).innerHTML;
		document.getElementById('catMngContTd'+ii).innerHTML = document.getElementById('catMngContTd' +(ii-1)).innerHTML;
		document.getElementById('catMngContTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('catMngQueTd'+ii).innerHTML;
		document.getElementById('catMngQueTd'+ii).innerHTML = document.getElementById('catMngQueTd' +(ii-1)).innerHTML;
		document.getElementById('catMngQueTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('catStatusTd'+ii).innerHTML;
		document.getElementById('catStatusTd'+ii).innerHTML = document.getElementById('catStatusTd' +(ii-1)).innerHTML;
		document.getElementById('catStatusTd' +(ii-1)).innerHTML = temp;
		
		temp = document.getElementById('catCodeTd'+ii).innerHTML;
		document.getElementById('catCodeTd'+ii).innerHTML = document.getElementById('catCodeTd' +(ii-1)).innerHTML;
		document.getElementById('catCodeTd' +(ii-1)).innerHTML = temp;
		}
	}
	
	function orderDown(order, ii){
		var temp = ""; //document.getElementById('categoryOrder'+ii).value;
		/*document.getElementById('categoryOrder'+ii).value = document.getElementById('categoryOrder' +(ii+1)).value;
		document.getElementById('categoryOrder' +(ii+1)).value = temp;
		*/
		if(document.getElementById('catCodeTd' +(ii+1)) == null ){
			alert("This is last section you cannot go down");
		} else {
		temp = document.getElementById('catDelTd'+ii).innerHTML;
		document.getElementById('catDelTd'+ii).innerHTML = document.getElementById('catDelTd' +(ii+1)).innerHTML;
		document.getElementById('catDelTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('catMngContTd'+ii).innerHTML;
		document.getElementById('catMngContTd'+ii).innerHTML = document.getElementById('catMngContTd' +(ii+1)).innerHTML;
		document.getElementById('catMngContTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('catMngQueTd'+ii).innerHTML;
		document.getElementById('catMngQueTd'+ii).innerHTML = document.getElementById('catMngQueTd' +(ii+1)).innerHTML;
		document.getElementById('catMngQueTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('catStatusTd'+ii).innerHTML;
		document.getElementById('catStatusTd'+ii).innerHTML = document.getElementById('catStatusTd' +(ii+1)).innerHTML;
		document.getElementById('catStatusTd' +(ii+1)).innerHTML = temp;
		
		temp = document.getElementById('catCodeTd'+ii).innerHTML;
		document.getElementById('catCodeTd'+ii).innerHTML = document.getElementById('catCodeTd' +(ii+1)).innerHTML;
		document.getElementById('catCodeTd' +(ii+1)).innerHTML = temp;
		}
	}
	
/*	
var oFCKeditor;
window.onload = function() {
	setMessage();
	oFCKeditor = new FCKeditor( 'content' ) ;
	oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
	oFCKeditor.ReplaceTextarea();
}


//call on save
function setMessage() {	
	var oEditor = FCKeditorAPI.GetInstance('content') ;
	var ms=document.getElementById('paraFrm_htmlcontent').value=oEditor.GetHTML();	
	
	alert(ms);
	alert(document.getElementById('paraFrm_htmlcontent').value);
}
*/ 
</script>