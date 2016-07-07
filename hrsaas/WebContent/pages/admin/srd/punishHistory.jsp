<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script>
var punishArray=new Array();
var periodArray=new Array();
var statusArray=new Array();
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="PunishHistory" target="main" theme="simple"
	validate="true" id="paraFrm">
	<s:hidden name="editableFlag" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:hidden name="empId" />
	<s:hidden name="paracode" />
	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />
	<fieldset>
	<legend class="legend"><img width="16" height="16"
		src="../pages/mypage/images/icons/profile_diciplinaryaction.png">&nbsp;&nbsp;&nbsp;Disciplinary
	Action</legend>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="0">
			<table width="100" border="0" align="right" cellpadding="2"
				cellspacing="3">
				<s:if test="editableFlag">
					<tr>
						<s:if test="updateFlg">
							<td width="48%"><a href="#"><img
								src="../pages/mypage/images/icons/save.png" onclick="callSave()"
								width="10" height="10" border="0" /></a></td>
							<td width="8%"><a href="#" onclick="callSave()"
								class="iconbutton">Save</a></td>
							<td width="3%">|</td>
						</s:if>

						<s:elseif test="insertFlg">
							<td width="75%"><a href="#"><img
								src="../pages/mypage/images/icons/save.png" onclick="callSave()"
								width="10" height="10" border="0" /></a></td>
							<td width="8%"><a href="#" onclick="callSave()"
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
					<tr>
						<s:if test="insertFlg">
							<td width="89%" align="right"><a href="#"><img
								src="../pages/mypage/images/icons/add.png"
									onclick="addFun()" width="10" height="10" border="0" /></a></td>
										<td><a href="#" onclick="addFun()"
											class="iconbutton">Add</a></td>
											<td>|</td>
						</s:if> 
						<s:if test="isNotGeneralUser">
							
								<td><a href="#"><img
									src="../pages/mypage/images/icons/search.png"
										onclick="searchFun()" width="10" height="10"
										border="0" /></a></td>
								<td><a href="#" onclick="searchFun()"
									class="iconbutton">Search</a></td>
									<td>|</td>
						</s:if>
					</tr>

				</s:else>
			</table>
			</td>
		</tr>
		<tr>
			<td height="1px" bgcolor="#cccccc"></td>
		</tr>
		<tr>
			<s:if test="editableFlag">
				<tr>
					<td>

					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="11">
							<fieldset><legend class="legend1">
							Disciplinary Particulars </legend>
							<table>
								<tr>
									<td width="25%" height="22"><label name="punishment"
										id="punishment" ondblclick="callShowDiv(this);"><%=label.get("punishment")%></label></td>
									<td width="1%" class="star"><font color="red">*</font></td>
									<td width="1%">:</td>
									<td>
									   <s:textfield size="25" theme="simple"
										name="punishHistory.dispAction" readonly="true" />
										<s:hidden theme="simple" name="punishHistory.dispActionId" /></td>
									<td><input name="relation" type="button" class="button" value="..."
										onclick="javascript:callsF9(500,325,'PunishHistory_f9actionDispAction.action');">
									</td>
									<td width="3%">&nbsp;</td>
									<td width="20%" height="22"><label name="authority"
										id="authority" ondblclick="callShowDiv(this);"><%=label.get("authority")%></label></td>
									<td width="1%" class="star"><font color="red">*</font></td>
									<td width="1%">:</td>
									<td width="23%" height="22"><s:textfield size="25"
										theme="simple" name="punishHistory.authority" maxlength="45" /></td>
									<td width="3%">&nbsp;</td>
								</tr>

								<tr>
									<td width="20%" height="22"><label name="action.date"
										id="action.date" ondblclick="callShowDiv(this);"><%=label.get("action.date")%></label></td>
									<td width="1%" class="star"><font color="red">*</font></td>
									<td width="1%">:</td>
									<td><s:textfield size="25" theme="simple"
										name="punishHistory.effFromDate" maxlength="10"
										onkeypress="numbersWithHiphen();" onblur="return validateDate('paraFrm_punishHistory_effFromDate','action.date');"/>
                                     <td><s:a href="javascript:NewCal('paraFrm_punishHistory_effFromDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="3%">&nbsp;</td>
									<td width="20%"><label name="action.date1"
										id="action.date1" ondblclick="callShowDiv(this);"><%=label.get("action.date1")%></label></td>
									<td width="1%" class="star"><font color="red">*</font></td>
									<td width="1%">:</td>
									<td width="23%"><s:textfield size="25" theme="simple"
										name="punishHistory.effToDate" maxlength="10"
										onkeypress="numbersWithHiphen();" onblur="return validateDate('paraFrm_punishHistory_effToDate','action.date1');"/></td>
										<td><s:a href="javascript:NewCal('paraFrm_punishHistory_effToDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>

								<tr>
									<td width="18%" height="22"><label name="period"
										id="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
									<td width="1%">&nbsp;</td>
									<td width="1%">:</td>
									<td><s:textfield size="25" theme="simple"
										name="punishHistory.period" maxlength="10"
										onkeypress="return numbersOnly();"/></td>
									<td width="3%" height="22">&nbsp;</td>
									<td width="3%" height="22">&nbsp;</td>
								
									<td width="18%" height="22"><label
										name="offence.description" id="offence.description"
										ondblclick="callShowDiv(this);"><%=label.get("offence.description")%></label></td>
									<td width="1%">&nbsp;</td>
									<td width="1%">:</td>
									<td><s:textarea name="description" cols="22"
										wrap="true" rows="3"
										onkeyup="calculateRemainingChars('descCnt');" />
										<img src="../pages/images/zoomin.gif" height="12"
														align="absmiddle" width="12" theme="simple"
														onclick="javascript:callWindow('paraFrm_description','offence.description','','200','200');">
										Remaining chars<s:textfield name="descCnt"
										readonly="true" size="2"></s:textfield></td>
									<td width="3%" height="22">&nbsp;</td></tr>
								<tr>
									<td width="18%"><label class="set" name="upload"
										id="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label></td>
									<td width="1%">&nbsp;</td>
									<td width="1%">:</td>
									<td width="20%"><input name="uploadFileNameTxt"
										id="paraFrm_uploadFileNameTxt"
										value='<s:property value="uploadFileNameTxt"/>' size="22"
										readonly="true" />
									<td><input name="Submit32" type="button"
										class="button" value="Browse"
										onclick="uploadFile('paraFrm_uploadFileNameTxt');" /></td>
									<td width="1%">&nbsp;</td>
									<td width="20%"><label name="punishStatus"
										id="punishStatus" ondblclick="callShowDiv(this);"><%=label.get("punishStatus")%></label></td>
									<td width="1%">&nbsp;</td>
									<td width="1%">:</td>
									<td width="23%"><s:select name="punishStatus"
										cssStyle="width:150" list="#{'A':'Active','I':'In Active'}" /></td>
									<td width="3%" height="22">&nbsp;</td>

								</tr>
								
								<tr>
								<td width="1%">&nbsp;</td>
								<td width="1%">&nbsp;</td>
								<td width="1%">&nbsp;</td>
								<td width="1%">&nbsp;</td>
								<td><input type="button" value="Add" Class="add"
										onclick="return callUpload();" size="20"></td>
								</tr>
								<tr>
									<td></td>
									<td colspan="3">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										id="uploadTable">
										<%
														int counter = 1;
														%>
										<s:iterator value="keepInformedList">
											<tr>
												<td width="10%"><s:hidden name="uploadFil"/><a
																href="#"
																onclick="javascript:showRecord('<s:property value="uploadFil"/>');"
																title="Click to view file"></a></td>
                                                 <td><img src="../pages/mypage/images/icons/delete.png"
													onclick="deleteCurrentRow(this);"></td>

											</tr>
											<%
								counter++;
								%>
										</s:iterator>

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

			</s:if>
		<tr>
			<td>
			<fieldset><legend class="legend1">Disciplinary
			Details </legend>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="border">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>

							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								id="tblPunish">

								<tr>
									<td width="5%" class="formth"><label name="serial.no"
										id="serialno" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td width="15%" class="formth"><label
										name="action.details" id="action.details"
										ondblclick="callShowDiv(this);"><%=label.get("action.details")%></label></td>
									<td class="formth" width="10%"><label name="punishStatus"
										id="punishStatus" ondblclick="callShowDiv(this);"><%=label.get("punishStatus")%></label></td>
									<td class="formth" width="10%"><label name="authority"
										id="authority1" ondblclick="callShowDiv(this);"><%=label.get("authority")%></label></td>
									<td class="formth" width="15%"><label
										name="offence.description" id="offence.description"
										ondblclick="callShowDiv(this);"><%=label.get("offence.description")%></label></td>
									<td class="formth" width="12.5%"><label name="action.date"
										id="action.dateFrom" ondblclick="callShowDiv(this);"><%=label.get("action.date")%></label></td>
									<td class="formth" width="12.5%"><label
										name="action.date1" id="action.dateTo"
										ondblclick="callShowDiv(this);"><%=label.get("action.date1")%></label></td>
									<td class="formth" width="10%"><label name="period"
										id="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
									<td class="formth" width="10%"><label name="view.document"
										id="view.document" ondblclick="callShowDiv(this);"><%=label.get("view.document")%></label></td>
									<td class="formth" nowrap="nowrap" width="10%">Edit |
									Delete</td>
								</tr>
								<%
												int p1 = 0;
												%>
								<%
												int cnt2 = 0;
												%>
								<s:iterator value="punishList">
									<tr class="text1">
										<td width="5%" align="center"><%=++p1%><s:hidden
											name="hDesc" /></td>
										<td width="15%"><s:hidden
											name="PunishId" /><s:hidden name="hSuspensionId" /><s:hidden
											name="hSuspensionType" id='<%="hSuspensionType"+cnt2%>' />&nbsp;<s:property
											value="hSuspensionType" /></td>
										<td width="10%" align="center">&nbsp;<s:property
											value="status" /><s:hidden name="status"
											id='<%="status"+cnt2%>' /></td>
										<td width="15%">&nbsp;<s:property value="hAuth" /><s:hidden
											name="hAuth" /><s:hidden name="hAuth" /></td>
										<td width="15%" title="<s:property value="listOffncDesc" />"><s:property value="abbrPunishHistory" /><s:hidden
											value="listOffncDesc" /></td>
										<td width="12.5%">&nbsp;<s:property value="hEffDate" /><s:hidden
											name="hEffDate" /></td>
										<td width="12.5%">&nbsp;<s:property value="hEffToDate" /><s:hidden
											name="hEffToDate" /></td>
										<td width="10%">&nbsp;<s:property value="hPeriod" /><s:hidden
											name="hPeriod" id='<%="hPeriod"+cnt2%>' /></td>
										<td>
										<table>
											<tr>
												<td>
												<table>
													<s:iterator value="imageList">
														<tr>
															<td width="10%" class="border"><s:hidden name="hView" /><a
																href="#"
																onclick="javascript:showRecord('<s:property value="hView"/>');"
																title="Click to view file"> <s:property
																value="hView" /></a></td>
														</tr>
													</s:iterator>

												</table>
												</td>
											</tr>
										</table>
										</td>
										<td width="10%" align="center">&nbsp; <script
											type="text/javascript">
																		punishArray[<%=cnt2%>] = document.getElementById('hSuspensionType'+<%=cnt2%>).value;
																		periodArray[<%=cnt2%>]=document.getElementById('hPeriod'+<%=cnt2%>).value;
																		statusArray[<%=cnt2%>]=document.getElementById('status'+<%=cnt2%>).value;
															</script> <s:if test="updateFlg">
											<img src="../pages/mypage/images/icons/edit.png" width="10"
												height="10"
												onclick="callForEdit('<s:property value="PunishId"/>');" />
										</s:if>| <s:if test="deleteFlg">
											<img src="../pages/mypage/images/icons/delete.png" width="10"
												height="10"
												onclick="callDelete('<s:property value="PunishId" />');" />
										</s:if></td>
									</tr>
									<%
													++cnt2;
													%>

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
					</td>
				</tr>
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
				<tr>
					<td height="0">
					<table width="100" border="0" align="right" cellpadding="2"
						cellspacing="3">
						<s:if test="editableFlag">
							<tr>
								<s:if test="updateFlg">
									<td width="48%"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="8%"><a href="#" onclick="callSave()"
										class="iconbutton">Save</a></td>
									<td width="3%">|</td>
								</s:if>

								<s:elseif test="insertFlg">
									<td width="75%"><a href="#"><img
										src="../pages/mypage/images/icons/save.png"
										onclick="callSave()" width="10" height="10" border="0" /></a></td>
									<td width="8%"><a href="#" onclick="callSave()"
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
							<tr>
						<s:if test="insertFlg">
							<td width="89%" align="right"><a href="#"><img
								src="../pages/mypage/images/icons/add.png"
									onclick="addFun()" width="10" height="10" border="0" /></a></td>
										<td><a href="#" onclick="addFun()"
											class="iconbutton">Add</a></td>
											<td>|</td>
						</s:if> 
						<s:if test="isNotGeneralUser">
							
								<td><a href="#"><img
									src="../pages/mypage/images/icons/search.png"
										onclick="searchFun()" width="10" height="10"
										border="0" /></a></td>
								<td><a href="#" onclick="searchFun()"
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

	<br />
	</fieldset>
</s:form>
<script>
function addFun(){
  document.getElementById('paraFrm').action='PunishHistory_addNewRecord.action';
   //document.getElementById('selectFlag').value=="true";
   document.getElementById('paraFrm').submit();
}



function saveFun(){
  	try{
  	
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="PunishHistory_save.action";
		document.getElementById("paraFrm").submit();
	}catch(e){}
  }  
  
  
  
  
 function searchFun(){ 
   
  	try{
  			  javascript:callsF9(500,325,'PunishHistory_f9actionEmployeeId.action');  
  		}catch(e){
  		  
  		}	   
  			  
  }

function cancelFun(){      
  	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="PunishHistory_cancelFunc.action";
		document.getElementById("paraFrm").submit();
	}catch(e){}
  }



function callReset(){
		document.getElementById("paraFrm").action="PunishHistory_reset.action";
		document.getElementById("paraFrm").submit();

}

function deleteFun(){
	var tbl = document.getElementById('tblPunish');
	var lastRow = tbl.rows.length;
	var emp=document.getElementById('paraFrm_punishHistory_empId').value;
	if(emp==""){
			alert("Please select the "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
	}else if(!(lastRow>1)){
			alert("There is no data in the list to delete.");
			return false;
	}else{
		var r=confirm("Are you sure to delete  records ?");
		if(r){
			document.getElementById("paraFrm").action="PunishHistory_deleteDataInList.action";
			document.getElementById("paraFrm").submit();
		}
	}
}
function callSave(){
	var action  =document.getElementById('paraFrm_punishHistory_dispAction').value;
	var auth    =trim(document.getElementById('paraFrm_punishHistory_authority').value);
	var frmdate =document.getElementById('paraFrm_punishHistory_effFromDate').value;
	var todate  =document.getElementById('paraFrm_punishHistory_effToDate').value;
	var fileUpload  =document.getElementById('paraFrm_uploadFileNameTxt').value;
	
	
	var actionLabel=document.getElementById('punishment').innerHTML;
	var authLabel=document.getElementById('authority').innerHTML;
	var frmdateLabel=document.getElementById('action.Date').innerHTMl;
	var todateLabel=document.getElementById('action.Date1').innerHTMl;
	
	
	
	if(fileUpload!="")
	{
	alert("Please click Add button");
	return false;
	}
	
	if(action==""){
	alert("Please select Suspension Type");
	return false;
	}
	
	if(auth==""){
	alert("Please enter "+authLabel);
	return false
	}
	
	if(frmdate=="")
	{
	 alert("Please enter/select "+frmdateLabel);
	 return false;
	}
	else
	{
	if(!(validateDate('paraFrm_punishHistory_effFromDate','action.date')))
	{
	return false;
	}
   }
	if(todate=="")
	{
	  alert("Please enter/select Effective To Date");
	  return false;
	 }
	 else
	 {
	 if(!(validateDate('paraFrm_punishHistory_effToDate','action.date1')))
	 {
	   return false;
	 }
	 }
	
	if(frmdate!=""&& todate!="")
  		     {
  		     if(!dateDifferenceEqual(frmdate,todate,'paraFrm_punishHistory_effToDate', 'action.date','action.date1'))
				return false;
			 }
	var description1 = document.getElementById('paraFrm_description').value;
		if(description1.length > 200){
			alert("Maximum length of  Offence/Action Description  is 200 characters.");
			return false;
		}		 
			 
			 
			 
		document.getElementById("paraFrm").action="PunishHistory_save.action";
		document.getElementById("paraFrm").submit();
}

function callAdd(){
	var emp     =document.getElementById('paraFrm_punishHistory_empId').value;
	var action  =document.getElementById('paraFrm_punishHistory_dispAction').value;
	var auth    =trim(document.getElementById('paraFrm_punishHistory_authority').value);
	var per     =trim(document.getElementById('paraFrm_punishHistory_period').value);
	var letterDt=trim(document.getElementById('paraFrm_punishHistory_effFromDate').value);
	var toDate=trim(document.getElementById('paraFrm_punishHistory_effToDate').value);
	

		if(emp==""){
					alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
					return false;
			}
			
		if(action==""){
				alert ("Please select "+document.getElementById('punishment').innerHTML.toLowerCase());
				document.getElementById('paraFrm_punishHistory_dispAction').focus();
				return false;
			}
		if(auth==""){
				alert ("Please enter "+document.getElementById('authority').innerHTML.toLowerCase());
				document.getElementById('paraFrm_punishHistory_authority').focus();
				return false;
			}
		
		if(!checkMaxChars())return false;
		
		if(letterDt!="" ){
			  var fld=['paraFrm_punishHistory_letterDate'];
			  var lbl=['action.date'];
		      var chkDb=validateDate(fld,lbl);
		    if(!chkDb) {
			     return false;
			  }
	  			
	  	}
	  	if(toDate!="" ){
			  var fld=['paraFrm_punishHistory_effToDate'];
			  var lbl=['action.date1'];
		      var chkDb1=validateDate(fld,lbl);
		    if(!chkDb1) {
			     return false;
			  }
	  			
	  	}
	  	
	  	
	 if(letterDt!=""&& toDate!="")
  		     {
  		     if(!dateDifferenceEqual(letterDt,toDate,'paraFrm_punishHistory_effToDate', 'action.date','action.date1'))
				return false;
			 } 	
	  	
	  	var updtFlg=document.getElementById("paraFrm_update").value;
	 
		var para=document.getElementById('paraFrm_paracode').value;
		var flag=false;
		var textStatus=document.getElementById('paraFrm_punishStatus').value;
		if(updtFlg=="true"){
		
			for(var i=0;i<punishArray.length;i++){
				if(para-1!=i){
				
					if(per!=""){
					
						  var st=statusArray[i].charAt(0);	
						    if(st=="A" && textStatus=="A"){
							   if(action==punishArray[i] && per==periodArray[i] ){						  
								flag=true;  
						     }
						   } 					
						}
					}
			    }
		
				if(flag){
					alert("Suspension Type "+action+" for Period "+per+" is already active.");	
					return false;					  
		          }
		
	  }else{
		          
					for(var i=0;i<punishArray.length;i++){
				
				
					if(per!=""){
				
						  var st=statusArray[i].charAt(0);	
						
						    if(st=="A" && textStatus=="A"){
							   if(action==punishArray[i] && per==periodArray[i] ){
								//alert("Suspension Type "+punishArray[i]+" for Period "+periodArray[i]+" is already active.");						  
								flag=true;  
						     }
						   } 					
						}
			
			    }
		
				if(flag){
					alert("Suspension Type "+action+" for Period "+per+" is already active.");	
					return false;					  
		          }
		}
		 
	  	
	  	
}


	function checkMaxChars(){
		var offence = document.getElementById('paraFrm_punishHistory_description').value;
		var remainChar = 300 - eval(offence.length);
				
		document.getElementById('paraFrm_offenceRemainingChars').value = remainChar;
				
		if(eval(remainChar)< 0){
			document.getElementById('paraFrm_punishHistory_description').style.background = '#FFFF99';
			alert("Maximum 300 characters are allowed for offence description.");
			document.getElementById('paraFrm_punishHistory_description').focus();
			return false;
		}
		else document.getElementById('paraFrm_punishHistory_description').style.background = '#FFFFFF';
		return true;
	}

 function callDelete(listpunishid){
   	document.getElementById('paraFrm_paracode').value=listpunishid; 
   	   
	var r=confirm("Are you sure to delete this record?");
		if(r==false){
			return false;
		}else{

	   	document.getElementById("paraFrm").action="PunishHistory_delPunish.action";
	 
	    document.getElementById("paraFrm").submit();
	    }
   }
   
   function alphabetsonly(myfield)
{
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


function alphanumericsonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
function showRecord(fieldName)
{
  
if(fieldName=="" || fieldName=="null")
{
alert('File not available');
return false;
}
var path='<%=session.getAttribute("session_pool")%>';

window.open('../pages/images/'+path+'/punishment/'+fieldName);
}
 
function numericsonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar)> -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function calculateRemainingChars(type){
if(type=='descCnt')
{
	var offence = document.getElementById('paraFrm_description').value;
	var remainChar = 200- eval(offence.length);
			
	document.getElementById('paraFrm_descCnt').value = remainChar;
			
	if(eval(remainChar)< 0){
		document.getElementById('paraFrm_description').style.background = '#FFFF99';
		
	}
	else document.getElementById('paraFrm_description').style.background = '#FFFFFF';  
}
}
  
function uploadFile(fieldName) {
	
	    	document.getElementById(fieldName).focus();
		var path="images/<%=session.getAttribute("session_pool")%>/punishment";
		 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 document.getElementById('paraFrm').target="main";
		}
	 
	
 function callUpload(){
		try{
			var uploadFil = document.getElementById('paraFrm_uploadFileNameTxt').value;
		
		  if(uploadFil==""){
			  alert("Please select file");
			  return false;
		  }
		  
		  var tbl = document.getElementById('uploadTable');
		  var lastRow = tbl.rows.length;
		  var iteration = (lastRow+1)+" ) ";
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'uploadFil';
		  column1.value = uploadFil; /*value to be set in the added cell*/
		  column1.id = 'uploadFil'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  column1.readOnly='true';
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/mypage/images/icons/delete.png";
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';

		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	 
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);
		  
		 
		  
	 
		}catch(e){}
		document.getElementById('paraFrm_uploadFileNameTxt').value="";
	
	}
 
function deleteCurrentRow(obj){
var doIt=confirm("Do you want to delete?");
if(doIt)
{

		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
}
		
	}
  
  function callForEdit(id){   
    
 try{ 
		document.getElementById('paraFrm_paracode').value=id;
		document.getElementById("paraFrm").action="PunishHistory_edit.action";
	    document.getElementById("paraFrm").submit();
  
		   }catch(e){
		 }
     }
</script>