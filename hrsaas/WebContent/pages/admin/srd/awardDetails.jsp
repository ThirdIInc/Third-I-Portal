<!-- JSP file for front end for Award Details -->

<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AwardDetails" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="editFlag"></s:hidden>
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="paraId" />
	<s:hidden name="empId" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<table width="100%" border="0" align="right" cellpadding="0"
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
							<fieldset><legend class="legend">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><img src="../pages/mypage/images/icons/profile_award.png" height="16" width="16"></td>
									<td>&nbsp;&nbsp;Awards &amp; Recognition</td>
								</tr>
							</table>
							</legend>
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
													<tr>
														<s:if test="updateFlg">
															<td width="48%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="formValidate()" width="10" height="10"
																border="0" /></a></td>
															<td width="8%"><a href="#" onclick="formValidate()"
																class="iconbutton">Save</a></td>
															<td width="3%">|</td>
														</s:if>

														<s:elseif test="insertFlg">
															<td width="75%"><a href="#"><img
																src="../pages/mypage/images/icons/save.png"
																onclick="saveFun()" width="10" height="10" border="0" /></a></td>
															<td width="8%"><a href="#" onclick="formValidate()"
																class="iconbutton">Save</a></td>
															<td width="3%">|</td>
														</s:elseif>

														<td width="5%"><a href="#"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancel()" width="10" height="10" border="0" /></a></td>
														<td width="13%"><a href="#" onclick="cancel()"
															class="iconbutton">Cancel</a></td>
														<td> |</td>
													</tr>
												</s:if>
												<s:else>
												<tr>
													<s:if test="insertFlg">
														<td width="89%" align="right"><a href="#"><img
															src="../pages/mypage/images/icons/add.png"
															onclick="callForAdd()" width="10" height="10" border="0" /></a></td>
															<td><a href="#" onclick="callForAdd()"
															class="iconbutton">Add</a></td>
																<td> |</td>

													</s:if> 
													<s:if test="isNotGeneralUser">
													
														<td><a href="#"><img
															src="../pages/mypage/images/icons/search.png"
															onclick="searchAction()" width="10" height="10"
															border="0" /></a></td>
															<td><a href="#" onclick="searchAction()"
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

											<tr>
												<td colspan="9">
												<fieldset><legend class="legend1"> <strong>Award
												Particulars</strong> </legend>
												<table width="99%" border="0" align="center" cellpadding="2"
													cellspacing="1">
													<tr>
														<td width="22%" height="22"><label
															name="award.typecode" id="award.typecode" ondblclick="callShowDiv(this);"><%=label.get("award.typecode")%></label></td>
														<td width="1%" class="star">*</td>
														<td width="1%">:</td>
														<td><label class="text1"> <s:hidden
															name="awdId" /><s:hidden name="recordId" /> <s:textfield
															label="%{getText('awardType')}" theme="simple"
															readonly="true" size="23" name="awardType" /> <input
															type="button" name="Submit3" class="button" value="..."
															onclick="awardAction()" /> </label></td>
														<td width="3%">&nbsp;</td>
														<td width="20%"><label name="view.document"
															id="view.document" ondblclick="callShowDiv(this);"><%=label.get("view.document")%></label></td>
														<td width="1%" class="star">&nbsp;</td>
														<td width="1%">:</td>
														<td width="23%" valign="top" class="text1"><input
															type="text" name="uploadMyFileNameTxt"
															id="uploadMyFileNameTxt"
															value='<s:property value="uploadMyFileNameTxt" />'
															size="15" readonly="true" /> <input name="Submit32"
															type="button" class="button" value="..."
															onclick="uploadFile('uploadMyFileNameTxt');" /></td>
													</tr>
													<tr>
														<td><label name="award.description"
															id="award.description" ondblclick="callShowDiv(this);"><%=label.get("award.description")%></label></td>
														<td class="star">&nbsp;</td>
														<td width="1%">:</td>
														<td width="30%" class="text1"><!--<s:textfield
															label="%{getText('awdDesc')}" name="awdDesc" size="25" />
															-->
															<s:textarea theme="simple" name="awdDesc" rows="2"
														cols="22" onkeyup="callLength('descCnt');" />
														</td>
														<td>&nbsp;</td>
														<td><label name="upload" id="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label></td>
														<td class="star">&nbsp;</td>
														<td>:</td>
														<td width="23%" valign="top" class="text1"><input
															type="text" name="uploadImageName" id="uploadImageName"
															value='<s:property value="uploadImageName" />' size="15"
															readonly="true" /> <input type="button" name="Submit33"
															class="button" value="..."
															onclick="uploadFile('uploadImageName');" /></td>
													</tr>
													<tr>
													<td></td>
													<td></td>
													<td></td>
													<td>
													<img src="../pages/images/zoomin.gif" height="12"
														align="absmiddle" width="12" theme="simple"
														onclick="javascript:callWindow('paraFrm_awdDesc','award.description','','200','200');">
													&nbsp;Remaining chars
													<s:textfield name="descCnt"
														readonly="true" size="2"></s:textfield>
														</td>
													</tr>
													<tr>
														<td><label name="award.date" id="award.date" ondblclick="callShowDiv(this);"><%=label.get("award.date")%></label></td>
														<td class="star">*</td>
														<td>:</td>
														<td class="text1">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="41%"><s:textfield
																	label="%{getText('awdDt')}" theme="simple" name="awdDt"
																	size="25" maxlength="10" onkeypress="numbersWithHiphen();"/></td>
																<td width="59%"><s:a
																	href="javascript:NewCal('paraFrm_awdDt','DDMMYYYY');">&nbsp;
															<img src="../pages/images/recruitment/Date.gif"
																		class="iconImage" height="16" align="absmiddle" width="16">
																</s:a></td>
															</tr>
														</table>
														</td>
														<td>&nbsp;</td>
														<td><label name="activeDashlet" id="activeDashlet" ondblclick="callShowDiv(this);"><%=label.get("activeDashlet")%></label></td>
														<td class="star">&nbsp;</td>
														<td>:</td>
														<td valign="top" class="text1"><s:checkbox
															name="activeInDashlet" id="activeInDashlet" /></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="star">&nbsp;</td>
														<td>&nbsp;</td>
														<td class="text1">&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td class="star">&nbsp;</td>
														<td>&nbsp;</td>
														<td class="text1">&nbsp;</td>
													</tr>
												</table>
												</fieldset>
												</td>
											</tr>
										</s:if>
										<tr>
											<td>
											<fieldset><legend class="legend1">Award
											Details</legend>
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
													<table width="100%" border="0" align="center"
														cellpadding="2" cellspacing="2" class="border">
														<tr>
															<td width="17%" bgcolor="#EEF4FB"><label
																name="award.typecode" id="award.typecode" ondblclick="callShowDiv(this);"><%=label.get("award.typecode")%></label>
															</td>
															<td width="9%" bgcolor="#EEF4FB"><label
																name="award.date" id="award.date" ondblclick="callShowDiv(this);"><%=label.get("award.date")%></td>
															<td width="22%" bgcolor="#EEF4FB"><label
																name="award.description" id="award.description" ondblclick="callShowDiv(this);"><%=label.get("award.description")%></label></td>
															<td width="9%" bgcolor="#EEF4FB"><label
																name="view.document" id="view.document" ondblclick="callShowDiv(this);"><%=label.get("view.document")%></label>
															</td>
															<td width="8%" bgcolor="#EEF4FB"><label
																name="upload" id="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label>
															</td>
															<td width="12%" bgcolor="#EEF4FB"><label
																name="activeDashlet" id="activeDashlet" ondblclick="callShowDiv(this);"><%=label.get("activeDashlet")%></label>
															</td>
															<td width="8%" bgcolor="#EEF4FB" align="center">Edit/Delete</td>
														</tr>

														<s:iterator value="awardList" status="stat">
															<tr>
																<td height="21" valign="top" class="text1"><s:hidden
																	name="awdCode" /> <s:property value="awardType" /></td>
																<td valign="top" class="text1"><s:property
																	value="awdDt" /></td>
																<td valign="top" class="text1"><s:property
																	value="awdDesc" /></td>
																<td valign="top" class="text1"><a href="#"
																	onclick="return showRecord('<s:property value="uploadMyFileName" />');">Certificate</a></td>
																<td valign="top" class="text1"><a href="#"
																	onclick="return showRecord('<s:property value="uploadImageNameItt" />');">Award Pic</a></td>
																<td valign="top" class="text1"><s:property
																	value="isActiveItt" /></td>
																<td valign="top">
																<div align="center"><s:if test="updateFlg">
																	<img src="../pages/mypage/images/icons/edit.png"
																		width="10" height="10"
																		onclick="callForEdit('<s:property value="awdCode"/>')" /> |
					                                     </s:if> <s:if test="deleteFlg">
																	<img src="../pages/mypage/images/icons/delete.png"
																		width="10" height="10"
																		onclick="callDelete('<s:property value="awdCode"/>')" />
																</s:if></div>
																</td>
															</tr>
														</s:iterator>

													</table>
													</td>
												</tr>

												<s:if test="noData">
													<tr>
														<td colspan="7" align="center"><font color="red">No
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
											<td>
											<table width="100" border="0" align="right" cellpadding="2"
												cellspacing="3">

												<s:if test="editFlag">
													<tr>
														<td width="48%"><a href="#"><img
															src="../pages/mypage/images/icons/save.png"
															onclick="formValidate()" width="10" height="10"
															border="0" /></a></td>
														<td width="8%"><a href="#" onclick="formValidate()"
															class="iconbutton">Save</a></td>
														<td width="3%">|</td>
														<td width="5%"><a href="#"><img
															src="../pages/mypage/images/icons/cancel.png"
															onclick="cancel()" width="10" height="10" border="0" /></a></td>
														<td width="13%"><a href="#" onclick="cancel()"
															class="iconbutton">Cancel</a></td>
														<td>|</td>
													</tr>
												</s:if>
												<s:else>
												<tr>
													<s:if test="insertFlg">
														<td width="89%" align="right"><a href="#"><img
															src="../pages/mypage/images/icons/add.png"
															onclick="callForAdd()" width="10" height="10" border="0" /></a></td>
															<td><a href="#" onclick="callForAdd()"
															class="iconbutton">Add</a></td>
															<td>|</td>	

													</s:if> 
													<s:if test="isNotGeneralUser">
														<td><a href="#"><img
															src="../pages/mypage/images/icons/search.png"
															onclick="searchAction()" width="10" height="10"
															border="0" /></a></td>
															<td><a href="#" onclick="searchAction()"
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
function call1() {
	if(document.getElementById('paraFrm_empName').value=="" ) {
	alert("Please select the employee");
							
	} else {
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action='AwardDetails_report.action';	
		document.getElementById('paraFrm').submit();		
	
	}
}
	
	
	
function callForEdit(id){
 	try{
 	   //document.getElementById('paraFrm_recordId').value=id;
 	    document.getElementById('paraFrm_paraId').value=id;
 		document.getElementById("paraFrm").action="AwardDetails_edit.action";		
    	document.getElementById("paraFrm").submit();
 	}catch(e){alert(e);}
 	
}


function callDelete(id){
   var conf=confirm("Are you sure to delete this record ?");
   if(conf) {
   	   	document.getElementById("paraFrm").action="AwardDetails_delete.action";
	   	document.getElementById('paraFrm_paraId').value=id;
	  	document.getElementById("paraFrm").submit();
   }
}
	
function formValidate() {		
        var FromDate = document.getElementById("paraFrm_awdDt").value;
        //var awdcode=document.getElementById("awdCode").value; 	
		/*if(!(document.getElementById("paraFrm_awdCode").value == "")){
  			alert("You can't Insert.Please Update ");
  			return false;
  		}*/
     	if(document.getElementById("paraFrm_empId").value=="") {
		alert("Please select  Employee ");  
		return false;
		}
		
		if(document.getElementById("paraFrm_awardType").value==""){
		alert("Please select award category");  
		return false;
		}

		if(document.getElementById("paraFrm_awdDt").value==""){
		alert("Please enter/select award date ");  
		return false;
		
		}	
	   if(FromDate!="" ){
		   var fld=['paraFrm_awdDt'];
		   var lbl=['award.date'];
		   var chkDb=validateDate(fld,lbl);
		   if(!chkDb) {
			    return false;
		   }
		   var awdDesc = document.getElementById('paraFrm_awdDesc').value;
		if(awdDesc.length > 200){
			alert("Maximum length of  award description  is 200 characters.");
			return false;
		}
	   }
	 document.getElementById('paraFrm').target="main";
	 document.getElementById('paraFrm').action="AwardDetails_save.action";
	 document.getElementById('paraFrm').submit();	  
  	 return true;
}
  			
  			
function calldel(name) {
	if(document.getElementById("paraFrm_empName").value=="") {
		alert("First select the record");
	} else {
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	}
}
  			
function charactersonly(myfield){
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function numbersonly(myfield){
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}		
function showRecord(fieldName){  
	if(fieldName==""){
		alert('File not available');
		return false;
	}
	var path='<%=session.getAttribute("session_pool")%>';
	window.open('../pages/images/'+path+'/award/'+fieldName);
}
	
function uploadFile(fieldName) {
	 var path="images/<%=session.getAttribute("session_pool")%>/award";
	 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	 document.getElementById('paraFrm').target="main";
} 
   
function formVal() {
	  var FromDate = document.getElementById("paraFrm_awdDt").value;
      if(document.getElementById("paraFrm_awdCode").value == ""){
  			alert("You can't Update.Please Insert ");
  			return false;
  	  }
      if(document.getElementById("paraFrm_empName").value=="") {
  			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
  			return false;
  	  }
  	  if(document.getElementById("paraFrm_awardType").value==""){
  			alert("Please select "+document.getElementById('award.type').innerHTML.toLowerCase());
  			return false;
  	  }
  	  if(document.getElementById("paraFrm_awdDt").value==""){
  			alert("Please enter "+document.getElementById('award.date').innerHTML.toLowerCase());
  			return false;  			
  	  }	
  	   if(FromDate!="" ){
			var fld=['paraFrm_awdDt'];
		    var lbl=['award.date'];
	        var chkDb=validateDate(fld,lbl);
	   if(!chkDb) {		   
		    return false;
	    }
  			
  	}
}
//Updated By Priyanka Kumbhar
function callForAdd(){
	document.getElementById("paraFrm").action="AwardDetails_addNew.action";
	document.getElementById("paraFrm").submit();
}				

function searchAction(){
	javascript:callsF9(500,325,'AwardDetails_f9emplaction.action');
}
function cancel(){
	document.getElementById("paraFrm").action="AwardDetails_cancelFunc.action";
	document.getElementById("paraFrm").submit();
}			
function awardAction(){
	javascript:callsF9(500,325,'AwardDetails_f9awardaction.action');
}	


function callLength(type){ 
 if(type=='descCnt'){
	var cmt =document.getElementById('paraFrm_awdDesc').value;
	var remain = 200 - eval(cmt.length);
	document.getElementById('paraFrm_descCnt').value = remain;
	if(eval(remain)< 0){
		document.getElementById('paraFrm_awdDesc').style.background = '#FFFF99';
	}else document.getElementById('paraFrm_awdDesc').style.background = '#FFFFFF';
 }
}  
</script>

