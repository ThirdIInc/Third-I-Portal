<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script>
var categoryArray=new Array();
var subjectArray=new Array();
var catgryArray=new Array();
</script>

<s:form action="ExamMaster" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="checkEdit" />
	<s:hidden name="editFlag" />
	<s:hidden name="paraId" />
	<s:hidden name="flagShow" />
	<s:hidden name="categoryId" />
	<s:hidden name="updateCatFlag" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="catList" />
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
					<td width="93%" class="txt"><strong class="formhead">Subject</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="subj.name" id="subj.name"
										ondblclick="callShowDiv(this);"><%=label.get("subj.name")%></label>
									:<font color="red">*</font></td>
									<td width="25%" height="22" align="left"><s:hidden name="subjectCode" />
									<s:textfield name="subjectName" theme="simple" size="30"
										maxlength="50" onkeypress="return allCharacters();" /></td>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="subj.abbr" id="subj.abbr"
										ondblclick="callShowDiv(this);"><%=label.get("subj.abbr")%></label>
									:<font color="red">*</font></td>
									<td width="25%" height="22"><s:textfield
										name="subjectAbbr" theme="simple" size="30" maxlength="10" />
									</td>
									<td>&nbsp;</td>
								</tr>


								<tr>
									<td height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="subj.desc" id="subj.desc"
										ondblclick="callShowDiv(this);"><%=label.get("subj.desc")%></label>:</td>
									<td height="22" nowrap="nowrap"><s:textarea
										name="subjectDesc" cols="32" rows="4"
										onkeyup="callLength('subjectDesc','descCnt','300');"></s:textarea></td>
									<td valign="bottom"><img src="../pages/images/zoomin.gif"
										height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_subjectDesc','subj.desc','','paraFrm_descCnt','300');">
									Remaining chars <s:textfield name="descCnt" readonly="true"
										size="5"></s:textfield></td>


								</tr>
								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="sub.stat" id="sub.stat2"
										ondblclick="callShowDiv(this);"><%=label.get("sub.stat")%></label>

									:</td>
									<td width="25%" height="22"><s:select theme="simple"
										name="status" value="%{status}"
										list="#{'A':'Active','D':'Deactive'}" /></td>
									<td>&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="subj.cnam" id="subj.cnam"
										ondblclick="callShowDiv(this);"><%=label.get("subj.cnam")%></label>
									:<font color="red">*</font></td>
									<td nowrap="nowrap" height="22"><s:textfield
										name="subjectCategoryName" theme="simple" size="30"
										maxlength="50" onkeypress="return allCharacters();" />&nbsp;&nbsp;
									</td>
									<td class="formtext"><input type="button" class="Add"
										value=" Add" size="5" theme="simple"
										onclick="return enterSubCat();" /></td>

								</tr>


								<tr>
									<td colspan="3"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>


								<!-- List of Records of Subject Category -->


								<tr>
									<td colspan="3"><s:if test="%{catList}">

										<%
									try {
									%>
										<table width="75%" border="1" cellpadding="0" cellspacing="1"
											class="formbg">
											<tr>

												<td class="formtext">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" id="subCategoryList">
													<tr>
														<td height="15" class="formhead" nowrap="nowrap"><strong
															class="forminnerhead">Category List</strong></td>
													</tr>

													<tr>

														<td width="10%" class="formth" align="center"><label
															class="set" name="sub.srno" id="sub.srno"
															ondblclick="callShowDiv(this);"><%=label.get("sub.srno")%></label>
														</td>

														<td width="40%" class="formth" align="center"><label
															class="set" name="cat.name" id="cat.name"
															ondblclick="callShowDiv(this);"><%=label.get("cat.name")%></label>
														</td>
														<td width="25%" class="formth" align="center"></td>
													</tr>
													<%
													int counts = 0;int cntr=0;
													%>
													<%!int cc = 0;%>
													<%
													int ii = 0;
													%>
													<s:iterator value="categoryList">
														<tr class="tableCell1">
															<td width="10%" align="left" class="sortableTD"><%=++ii%>

															<s:hidden name="SrNo" /> <s:hidden
																name="categoryCodeItr" /></td>

															<td width="40%" align="left" class="sortableTD"><s:property
																value="subjectCategoryNameItr" /> <s:hidden
																name="subjectCategoryNameItr"
																id='<%="subjectCategoryNameItr"+cntr%>' /></td>

															<script type="text/javascript">
							
																categoryArray[<%=cntr%>] = document.getElementById('subjectCategoryNameItr'+<%=cntr%>).value;
							
															</script>

															<input type="hidden" name="hdeleteCatCode"
																id="hdeleteCatCode<%=ii%>" />
															<td align="center" nowrap="nowrap" width="25%"
																class="sortableTD">&nbsp; <input type="button"
																class="edit"
																onclick="calForedit('<s:property value="subjectCategoryNameItr"/>','<s:property value="SrNo"/>')"
																value=" Edit" /> <input type="button" class="delete"
																value="Remove"
																onclick=" return deleteCat('<%=ii%>','<s:property value="hdeleteCatCode"/>','<s:property value="categoryCodeItr"/>');" />
															</td>
															<%cntr++; %>
														
													</s:iterator>
													<%
															cc = ii;
															ii = 0;
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
									</s:if></td>
									<s:hidden name="categoryListlength" />
								</tr>

								<tr>
									<td colspan="3"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
 
var f9Fields= ["paraFrm_subjectName","paraFrm_subjectAbbr"];
var fieldName = ["paraFrm_subjectName","paraFrm_subjectAbbr"];
var lableName = ["subj.name","subj.abbr"];
var type = ['enter','enter'];

// For Save Button

function saveFun()
{
	
	var list=document.getElementById('paraFrm_catList').value;
	if(!validateBlank(fieldName,lableName,type))
          return false;
          
  if(!f9specialchars(f9Fields))
  return false;
 
  var desc = document.getElementById('paraFrm_subjectDesc').value;
	
	if(desc != "" && desc.length > 300){
		
		alert("Maximum length of <%=label.get("subj.desc")%> is 300 characters.");
		return false;
    }  
    if(list=="false"){
    
    
    	alert("Please add atleast one "+document.getElementById('subj.cnam').innerHTML.toLowerCase());
    	document.getElementById('paraFrm_subjectCategoryName').focus();
    	return false;
    }      
  
  document.getElementById('paraFrm').action="ExamMaster_save.action";
  document.getElementById('paraFrm').submit();
  return true;
}



//For Cancel Button

function cancelFun(){
    	document.getElementById('paraFrm').action="ExamMaster_cancelFirst.action";
        document.getElementById("paraFrm").submit();
    
}

function deleteCat(id,hidcode,category)
{	

	
	if(!(category=="" || category=="null")){
	
		document.getElementById("ctgryCode").value=category; 
	
	}
	var con=confirm('Are you sure to remove this record?')
	if(con){
		   document.getElementById("paraFrm_paraId").value=id; 	
		   document.getElementById("paraFrm_categoryId").value=hidcode; 
		   document.getElementById('paraFrm').action="ExamMaster_calfordelete.action";
		   document.getElementById('paraFrm').submit();
	} else{
		 //alert("else");	
	     return false;
	}
}

function enterSubCat()
{	var flg=document.getElementById('paraFrm_updateCatFlag').value;
	var catName=trim(document.getElementById('paraFrm_subjectCategoryName').value);
	
	if(!validateBlank(fieldName,lableName,type))
          return false;
      
          
 	 if(!f9specialchars(f9Fields))
 	     return false;
 	 
 	 
	if(catName=="")
	{
		alert("Please enter the "+document.getElementById('subj.cnam').innerHTML.toLowerCase());
		document.getElementById('paraFrm_subjectCategoryName').focus();
		return false;
	}
	var edit=document.getElementById('paraFrm_checkEdit').value;
	var flag=false;
	if(flg=="true"){
						 for(var i = 0; i <categoryArray.length; i++)
						        {
						     
						          if(edit-1!=i){
						            if(catName==categoryArray[i]){
						        	    	flag=true;
						        
						               }
						        }
						                   
						        }
					  		 	if(flag){
									alert(document.getElementById('subj.cnam').innerHTML+" "+"already exists.");  			
					  				return false;
					  			
					  			}
					
	
	}else{
			for(var i=0;i<categoryArray.length;i++){
				if(catName==categoryArray[i]){
					flag=true;	
				
				}
			
			}
			if(flag){
			
					alert(document.getElementById('subj.cnam').innerHTML+" "+"already exists.");  			
					  				return false;
			
			}
	
	}
	

		document.getElementById('paraFrm').action="ExamMaster_addSubCat.action";
		document.getElementById('paraFrm').submit();
	
}

function calForedit(catName,edit)
{
	
		document.getElementById('paraFrm_updateCatFlag').value="true";
		document.getElementById('paraFrm_checkEdit').value=edit;
		document.getElementById('paraFrm_subjectCategoryName').value=catName;
		//alert(document.getElementById('paraFrm_checkEdit').value);
}
function callForDeleteCat(edit,ii)
{
	if(document.getElementById('confChkCat'+ii).checked == true)
		{	  
		document.getElementById('hdeleteCatCode'+ii).value=edit;
		}			
		else
		document.getElementById('hdeleteCatCode'+ii).value="";
}


</script>