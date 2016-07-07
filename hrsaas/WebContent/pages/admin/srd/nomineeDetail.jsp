<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="NomineeDetail"  id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="nomDetail.paracode" />
	<s:hidden name="nomDetail.oldFractionValue" />
	<s:hidden name="nomDetail.empID" />
	<s:hidden name="" />
	<s:hidden name="uploadFileName" />
	<s:hidden name="editFlag" />
	<s:hidden name="editDetail" />
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="deleteChk" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<form id="form1" name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"><img
						src="../pages/mypage/images/icons/profile_nominee.png" width="16"
						height="16" /> &nbsp;&nbsp;Nominee's Information</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="0">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="48%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="3%">|</td>
												</s:if>
												<s:elseif test="insertFlg">
													<td width="48%"><a href="#"><img
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
											<tr align="right">
												<s:if test="insertFlg">
													<td width="48%"><a href="#"><img
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
								<s:if test="editDetail">
									<tr>
										<td colspan="10">
										<fieldset><legend class="legend1">
										Nominee's Particulars </legend>
										<table width="99%" border="0" align="center" cellpadding="2"
											cellspacing="1">
											<tr>
												<td width="18%"><label name="nominationfor"
													id="nominationfor" ondblclick="callShowDiv(this);"><%=label.get("nominationfor")%></label></td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="24%"><s:hidden name="nomDetail.nomineeCode" />
												<s:select name="nomDetail.nomiType"
													list="#{'':'-------------Select-------------','G':'GRATUITY','F':'PF','P':'PENSION','S':'ESIC'}" /></td>
												<td width="2%"></td>
												<td width="6%">&nbsp;</td>
												<td width="18%"><label name="nomifract1"
													id="nomifract1" ondblclick="callShowDiv(this);"><%=label.get("nomifract1")%></label></td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="26%" valign="top" class="text1"><s:textfield
													size="27" maxlength="5" name="nomiFraction"
													onkeypress="return numbersWithDot();" /></td>
											</tr>


											<tr>

												<td width="18%"><label name="nominationName"
													id="nominationName" ondblclick="callShowDiv(this);"><%=label.get("nominationName")%></label></td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="24%" class="text1"><s:hidden
													name="nomDetail.memberCode" /> <s:textfield size="27"
													name="nomDetail.memberName" readonly="true" /></td>
												<td width="2%"><input type="button" name="Submit36323"
													class="button" value="..." onclick="memAction()" /></td>
												<td width="4%">&nbsp;</td>

												<td width="18%"><label name="nomidate" id="nomidate"
													ondblclick="callShowDiv(this);"><%=label.get("nomidate")%></label></td>
												<td width="1%" class="star">*</td>
												<td width="1%">:</td>
												<td width="26%" class="text1">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="40%" colspan="3"><s:textfield
															name="nomDetail.nomiDate" size="27" maxlength="10"
															theme="simple" onkeypress="return numbersWithHiphen();"
															onblur="return validateDate('paraFrm_nomDetail_nomiDate','nomidate');" /></td>
														<td width="1%">&nbsp;</td>
														<td width="58%"><s:a
															href="javascript:NewCal('paraFrm_nomDetail_nomiDate','DDMMYYYY');">
															<img src="../pages/images/recruitment/Date.gif"
																class="imageIcon" border="0" align="absmiddle" />
														</s:a></td>
													</tr>
												</table>
												</td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
									<tr>
										<%
										int g = 0;
										%>
										<td>
										<fieldset><legend class="legend1">Witness
										Details </legend>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>

												<td align="right"><input type="button" class="addnew"
													value="Add Row" onclick="addRow('<%=g%>')" /></td>
											</tr>

											<tr>
												<td>
												<table id="addrowTable<%=g%>" width="100%" border="0"
													align="center" cellpadding="2" cellspacing="2"
													class="border">


													<tr>
														<td width="30%" bgcolor="#EEF4FB" align="center"><label
															name="witnessName" id="witnessName"
															ondblclick="callShowDiv(this);"><%=label.get("witnessName")%></label></td>
														<td width="35%" bgcolor="#EEF4FB" align="center"><label
															name="witnessAddress" id="witnessAddress"
															ondblclick="callShowDiv(this);"><%=label.get("witnessAddress")%></label></td>
														<td width="10%" bgcolor="#EEF4FB" align="center"><label
															name="deletebutton" id="deletebutton"
															ondblclick="callShowDiv(this);"><%=label.get("deletebutton")%></label></td>
													</tr>
													<%!int k = 0;%>

													<%!int count = 0;%>
													<s:iterator value="witnessDetailsList">
														<tr>
															<td align="center"><input type="text" height="30"
																name='witnessName'
																id="witnessName<%=count%><%=k%>"
																value='<s:property value="witnessName"/>' size="40"
																maxlength="50" /><input type="hidden" name='witnessID'
																id="witnessID<%=count%><%=k%>"
																value='<s:property value="witnessID"/>' size="10" />&nbsp;
															</td>
															<td align="center"><s:textarea name='witnessAddress'
																id="witnessAddress<%=count%><%=k%>"  
																cols="50"
																rows="1" onkeydown="limitText(this,300);"
																onkeyup="limitText(this,300);" /></td>
															<s:if test="deleteFlg">
																<td align="center"><img
																	src="../pages/mypage/images/icons/delete.png"
																	onclick="callDeleteWitness('<s:property value="witnessID"/>');" />
																</td>
															</s:if>
														</tr>
														<%
														++g;
														%>
														<%
														++k;
														%>
													</s:iterator>
													
												</table>
												</td>
											</tr>
										</table>
										</fieldset>
										</td>
									</tr>
								</s:if>
								<tr>
									<td>
									<fieldset><legend class="legend1">
									Nominee's Details </legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2" class="border">

												<tr bgcolor="#EEF4FB">
													<td width="10%" align="center"><label
														name="nominationfor" id="nominationfor"
														ondblclick="callShowDiv(this);"><%=label.get("nominationfor")%></label></td>
													<td width="30%" align="center"><label
														name="nominationName" id="nominationName"
														ondblclick="callShowDiv(this);"><%=label.get("nominationName")%></label></td>
													<td width="13%" align="center"><label
														name="nomifract1" id="nomifract1"
														ondblclick="callShowDiv(this);"><%=label.get("nomifract1")%></label></td>
													<td width="10%" align="center"><label name="nomidate"
														id="nomidate" ondblclick="callShowDiv(this);"
														align="center"><%=label.get("nomidate")%></label></td>
													<td width="10%" align="center">Edit|Delete</td>
												</tr>
												<tr>
													<s:iterator value="nomDetail.nomList" status="stat">
														<tr>
															<td class="text1"><s:property value="nomiType" /></td>
															<td class="text1"><s:hidden value="nomineeCode" />
															<s:property value="memberName" /></td>
															<td class="text1" align="right"><s:property
																value="nomiFraction" />%</td>
															<td class="text1" align="center"><s:property
																value="nomiDate" /></td>
															<td>
															<div align="center"><s:if test="updateFlg">
																<img src="../pages/mypage/images/icons/edit.png"
																	width="10" height="10"
																	onclick="callForEdit('<s:property value="nomineeCode"/>','<s:property value="nomiFraction"/>')" />
															|</s:if> <s:if test="deleteFlg">
																<img src="../pages/mypage/images/icons/delete.png"
																	width="10" height="10"
																	onclick="callDelete('<s:property value="nomineeCode"/>')" />
															</s:if></div>
															</td>
														</tr>
													</s:iterator>
												</tr>
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
									<td>
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">

										<s:if test="editFlag">
											<tr align="right">
												<s:if test="updateFlg">
													<td width="48%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="8%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="3%">|</td>
												</s:if>
												<s:elseif test="insertFlg">
													<td width="48%"><a href="#"><img
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
											<tr align="right">
												<s:if test="insertFlg">
													<td width="48%"><a href="#"><img
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
					</fieldset>
					 </td>
				</tr>
			</table>
			</form>
			</td>
		</tr>

		<%
		String str = (String) request.getAttribute("photo");
		%>

	</table>
	</div>
</s:form>

<script>
onload();
function onload()
{
var idValue=0;

	var table = document.getElementById('addrowTable'+idValue); 
	if(table==null){
		idValue=0;
	}
	var rowCount = table.rows.length; 
	
	var iteration = rowCount-1;		
	if(!iteration>0)
	{
		addRow(idValue);		
	}
	
}

function saveFun(){
			
 	    
		var pre1 = document.getElementById('paraFrm_nomDetail_memberName').value;
		var pre2 = document.getElementById('paraFrm_nomiFraction').value;
		var pre4 = document.getElementById('paraFrm_nomDetail_nomiType').value;
		var pre3 = document.getElementById('paraFrm_nomDetail_nomiDate').value;
		 if((pre4=="")){
			alert ("Please Select "+document.getElementById('nominationfor').innerHTML.toLowerCase());
		  	return false;
		}
		
		
		if((pre1=="")){
			alert ("Please select "+document.getElementById('nominationName').innerHTML.toLowerCase());
		  	return false;
		}
		if((pre2=="")){
			alert ("Please enter "+document.getElementById('nomifract1').innerHTML.toLowerCase());
			return false;
		}
		if((pre3=="")){
			alert ("Please enter/select "+document.getElementById('nomidate').innerHTML.toLowerCase());
			return false;
		}
	 
		if(pre2 > 100 || pre2 > 100.0)
		{
			alert ("Value of  nomination fraction should not be greater than 100 ");
			return false;
		}
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="NomineeDetail_save.action";
	document.getElementById("paraFrm").submit();

	
} 

// Added by manish sakpal Begins
function addRow(idValue) {
	
	var table = document.getElementById('addrowTable'+idValue); 
	var rowCount = table.rows.length; 
	var iteration = rowCount-1;
	var row = table.insertRow(rowCount);
	var cell1 = row.insertCell(0); 
	var column1 = document.createElement("input"); 
	column1.type = "text"; 
	column1.name = 'witnessName';
	column1.id = 'witnessName'+idValue+''+iteration;
	cell1.appendChild(column1); 
	column1.size='40';
	column1.maxLength='50';
	cell1.align='center';
	
	var column4 = document.createElement("hidden"); 
	column4.type = "text"; 
	column4.name = 'witnessID';
	column4.id = 'witnessID'+idValue+''+iteration;
	cell1.appendChild(column4); 
	column4.size='10';		  
	
	var cell2 = row.insertCell(1); 
	var column2 = document.createElement("textarea"); 
	column2.name = 'witnessAddress';
	column2.id = 'witnessAddress'+idValue+''+iteration;
	column2.cols='50';
	column2.rows='2'; 
	column2.title="Maxlength is 300";
	column2.onkeyup=function (){
	limitNum=300;
	  if (this.value.length > limitNum) {
        this.value = this.value.substring(0, limitNum);
    } 
	}
	column2.onkeydown=function (){
	limitNum=300;
	  if (this.value.length > limitNum) {
        this.value = this.value.substring(0, limitNum);
    } 
	}
	cell2.appendChild(column2);
	cell2.align = 'center'; 
	          
	var cell3= row.insertCell(2);
	var dlt= document.getElementById('paraFrm_deleteChk').value;
	if(dlt=='true'){
	var column3 = document.createElement('img');		  	
	column3.type='image';
	column3.src="../pages/mypage/images/icons/delete.png" 	 	
	column3.id='img'+ iteration;
	column3.onclick=function(){
	 try {
		 var con = confirm("Do you really want to delete row?");
		 if(con)
		 {		  		  
		  	deleteCurrentRow(this);
		 }
		 else
		 {
		 	return false;
		 }
	 }catch(e){alert(e);}
	 };		  	
	 cell3.appendChild(column3);
	 cell3.align='center'; 
	}
	
	
} 
			
function deleteCurrentRow(obj){             
	var delRow = obj.parentNode.parentNode;
	var tbl = delRow.parentNode.parentNode;
	var rIndex = delRow.sectionRowIndex;
	var rowArray = new Array(delRow);
	deleteRows(rowArray);
}

function deleteRows(rowObjArray){		
	for (var i=0; i<rowObjArray.length; i++) {			
		var rIndex = rowObjArray[i].sectionRowIndex;
		rowObjArray[i].parentNode.deleteRow(rIndex);
	}
}
		
function callDeleteWitness(witnessid)
{			
 	var conf=confirm("Are you sure to delete this record?");
 	if(conf) {
		document.getElementById("paraFrm").target = "_self";   			
 	   	document.getElementById("paraFrm").action="NomineeDetail_deleteWitness.action?witnessid="+witnessid;	   			
 		document.getElementById("paraFrm").submit();
 	}else {
 		return false;
 	}
}	
function callForEdit(id,fractionValue){
	    document.getElementById('paraFrm_nomDetail_paracode').value=id;
	    document.getElementById('paraFrm_nomDetail_oldFractionValue').value=fractionValue;
	  	document.getElementById("paraFrm").action="NomineeDetail_edit.action";
	    document.getElementById("paraFrm").submit();
}
function callDelete(id){
	var conf=confirm("Are you sure to delete this record?");
	if(conf) {
		   	document.getElementById("paraFrm").action="NomineeDetail_delete.action";
		 	document.getElementById('paraFrm_nomDetail_paracode').value=id;
			document.getElementById("paraFrm").submit();
	}else {
		return false;
	}
}

function addFun(){
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action="NomineeDetail_nomineeDtls.action";
	document.getElementById("paraFrm").submit();
}


function memAction(){
	javascript:callsF9(500,325,'NomineeDetail_f9memaction.action');
}

function searchFun(){
	javascript:callsF9(500,325,'NomineeDetail_f9empaction.action');	
}

function cancelFun(){
	try{
		document.getElementById("paraFrm").action="NomineeDetail_cancel.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
}

function limitText(limitField, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
    } 
}

function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}
</script>