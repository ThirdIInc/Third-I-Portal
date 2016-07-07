 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"	src="../pages/common/datetimepicker.js"></script>
<s:form action="RecruitmentPartners" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="editViewFlag" />
	<s:hidden name="passMessage" /><s:hidden name="noData"/>
	<s:hidden name="currentView" />
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
					<td width="93%" class="txt"><strong class="text_head">Recruitment
					Partners</strong></td>
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
								page="/pages/ApplicationStudio/navigationPanel.jsp" />
								<!--  
								<input type="button" value="Send Mail" class="token" onclick="return sendmail();" />
								-->
								</td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
          
           <s:if test="editViewFlag">
           
           <tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td height="7" colspan="5" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
								
							

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.pname" id="recr.pname"
										ondblclick="callShowDiv(this);"><%=label.get("recr.pname")%></label>
									:</td>
									<td width="34%" height="22"><s:hidden
										name="recpartnerCode" /> <s:hidden name="recPartnerName" />
									<s:property value="recPartnerName" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<!-- Updated by Anantha Lakshmi -->
								<tr>
									<td width="17%" height="22" class="formtext">
										<label  name="recr.startDate" id="recr.startDate" ondblclick="callShowDiv(this);"><%=label.get("recr.startDate")%></label> :
									</td>
									<td width="28%" height="22">
										<s:property value="startDate"   /> 
										 <s:hidden name="startDate"></s:hidden> 
									</td>
									
									<td width="17%" height="22" class="formtext">
										<label  name="recr.endDate" id="recr.endDate" ondblclick="callShowDiv(this);"><%=label.get("recr.endDate")%></label> :
									</td>
									<td width="28%" height="22">
										<s:hidden name="endDate"></s:hidden>
										<s:property value="endDate"  /> 
										 
									</td>
									
								</tr>
								
								
								

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.coper" id="recr.coper"
										ondblclick="callShowDiv(this);"><%=label.get("recr.coper")%></label>
									:</td>
									<td width="34%" height="22"><s:hidden
										name="recContactPerson" /> <s:property
										value="recContactPerson" /></td>
										
									<td width="17%"  height="22" class="formtext">
										<label class="set" name="recr.ptype" id="recr.ptype"
										ondblclick="callShowDiv(this);"><%=label.get("recr.ptype")%></label>:
									</td>
									<td  width="28%" height="22">
										<s:hidden name="recPartnerType" /> <s:property
										value="pageRecPartnerType" />
									</td>
									
								</tr>

							
								<tr>
									<td width="22%" height="22" valign="top" class="formtext" colspan="1"
										 ><label class="set" name="recr.addre"
										id="recr.addre" ondblclick="callShowDiv(this);"><%=label.get("recr.addre")%></label>
									:</td>
									<td height="22" colspan="3"><s:hidden
										name="recPartnerAddress"></s:hidden> <s:property
										value="recPartnerAddress" /></td>
								</tr>

								<tr>
									<td height="22" class="formtext"><label class="set"
										name="city" id="recr.pcity"
										ondblclick="callShowDiv(this);"><%=label.get("city")%></label>:
									</td>
									<td height="22"><s:hidden name="cityCode" /> <s:hidden
										name="recPartnerCity"></s:hidden> <s:property
										value="recPartnerCity" /></td>

									<td height="22" class="formtext"><label class="set"
										name="recr.phono" id="recr.phono"
										ondblclick="callShowDiv(this);"><%=label.get("recr.phono")%></label>:
									</td>
									<td height="22"><s:hidden name="phoneNo"></s:hidden> <s:property
										value="phoneNo" /></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.pcode" id="recr.pcode"
										ondblclick="callShowDiv(this);"><%=label.get("recr.pcode")%></label>:
									</td>

									<td width="34%" height="22"><s:hidden name="pinCode"></s:hidden>
									<s:property value="pinCode" /></td>

									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.mobno" id="recr.mobno"
										ondblclick="callShowDiv(this);"><%=label.get("recr.mobno")%></label>:
									</td>

									<td width="34%" height="22"><s:hidden name="mobileNo"></s:hidden>
									<s:property value="mobileNo" /></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.email" id="recr.email"
										ondblclick="callShowDiv(this);"><%=label.get("recr.email")%></label>
									:</td>
									<td width="34%" height="22"><s:hidden name="email"></s:hidden>
									<s:property value="email" /></td>

									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.faxno" id="recr.faxno"
										ondblclick="callShowDiv(this);"><%=label.get("recr.faxno")%></label>
									:</td>
									<td width="34%" height="22"><s:hidden name="faxNo"></s:hidden>
									<s:property value="faxNo" /></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.charg" id="recr.charg"
										ondblclick="callShowDiv(this);"><%=label.get("recr.charg")%></label>
									:</td>
									<td width="34%" height="22"><s:hidden name="charges"></s:hidden>
									<s:property value="charges" /></td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" valign="top" class="formtext"
										nowrap="nowrap"><label class="set" name="recr.pdesc"
										id="recr.pdesc" ondblclick="callShowDiv(this);"><%=label.get("recr.pdesc")%></label>
									:</td>
									<td height="22" colspan="3"><s:hidden name="partnerDesc"></s:hidden>
									<s:property value="partnerDesc" /></td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.stats" id="recr.stats"
										ondblclick="callShowDiv(this);"><%=label.get("recr.stats")%></label>
									:</td>
									<td height="22"><s:hidden name="status"></s:hidden> <s:hidden name="pageStatus"/> <s:property
										value="pageStatus" /></td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								
								<tr>
									<td width="20%" height="22" class="formtext">
									<label class="set" name="recr.termsConditions" id="recr.termsConditions" ondblclick="callShowDiv(this);"><%=label.get("recr.termsConditions")%></label>:</td>
									<td width="61%" height="22" valign="bottom">
										<s:hidden name="termsCond"></s:hidden>
										<s:property value="termsCond" />
									</td>
									
								</tr>
								

								<tr>
									<td colspan="4"><b> <s:property value="passMessage"/></b> </td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
           
           
           </s:if>
           <s:else>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td height="7" colspan="5" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.pname" id="recr.pname"
										ondblclick="callShowDiv(this);"><%=label.get("recr.pname")%></label>
									<font color="red">*</font> :</td>
									<td width="34%" height="22"><s:hidden
										name="recpartnerCode" /> <s:textfield name="recPartnerName"
										theme="simple" size="25" maxlength="50"
										onkeypress="return allCharacters();" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="17%" height="22" class="formtext">
										<label  name="recr.startDate" id="recr.startDate" ondblclick="callShowDiv(this);"><%=label.get("recr.startDate")%></label> :
									</td>
									<td width="28%" height="22">
										<s:textfield name="startDate" size="12"  maxlength="10"  onkeypress="return numbersWithHiphen();" label="%{getText('endDate')}"  theme="simple"/> 
										  <s:a	href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
										  	<img src="../pages/images/recruitment/Date.gif"	align="absmiddle" class="iconImage" height="16" width="16">
										</s:a>
									</td>
									<td width="17%" height="22" class="formtext">
										<label  name="recr.endDate" id="recr.endDate" ondblclick="callShowDiv(this);"><%=label.get("recr.endDate")%></label> :
									</td>
									<td width="28%" height="22">
										<s:textfield name="endDate" size="12"  maxlength="10"  onkeypress="return numbersWithHiphen();" label="%{getText('endDate')}" theme="simple"/> 
										  <s:a	href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
										   	<img src="../pages/images/recruitment/Date.gif" align="absmiddle" class="iconImage" height="16" width="16">
										  </s:a>
									</td>
								</tr>


								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.coper" id="recr.coper"
										ondblclick="callShowDiv(this);"><%=label.get("recr.coper")%></label>
									<font color="red">*</font> :</td>
									<td width="34%" height="22"><s:textfield
										name="recContactPerson" theme="simple" size="25"
										maxlength="50" onkeypress="return allCharacters();" /></td>
									<td width="17%" height="22" class="formtext">
										<label
										class="set" name="recr.ptype" id="recr.ptype"
										ondblclick="callShowDiv(this);"><%=label.get("recr.ptype")%></label> <font color="red">*</font> :
									</td>
									<td width="28%" height="22">
										<s:select name="recPartnerType"
										list="#{'':'--Select--','C':'Job Consultant','AA':'Advertising Agency'}"
										cssStyle="width:151;" />
									</td>
								</tr>

							
								<tr>
									<td width="22%" height="22"  class="formtext"
										nowrap="nowrap"><label class="set" name="recr.addre"
										id="recr.addre" ondblclick="callShowDiv(this);"><%=label.get("recr.addre")%></label>
									<font color="red">*</font> :</td>
									<td width="61%" valign="bottom"><s:textarea name="recPartnerAddress"  cols="26"
										rows="3" onkeyup="callLength('recPartnerAddress','descCnt','2000');"></s:textarea>
										<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_recPartnerAddress','recr.addre','','paraFrm_descCnt','2000');" >
										&nbsp;Remaining chars <s:textfield name="descCnt"
											readonly="true" size="5"></s:textfield>
										
										</td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="city" id="recr.pcity"
										ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :
									</td>
									<td width="30%" height="22"><s:hidden name="cityCode" />
									<s:textfield name="recPartnerCity" theme="simple" size="25"
										maxlength="30" onkeypress="return allCharacters();"
										readonly="true" /> <span id="candName"><img
										class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="15"
										height="16"
										onclick="javascript:callsF9(500,325,'RecruitmentPartners_f9City.action');" />
									</span></td>

									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.phono" id="recr.phono"
										ondblclick="callShowDiv(this);"><%=label.get("recr.phono")%></label> :
									</td>
									<td width="30%" height="22"><s:textfield name="phoneNo"
										theme="simple" size="25" maxlength="25"
										onkeypress="return validatePhoneNo();" /></td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.pcode" id="recr.pcode"
										ondblclick="callShowDiv(this);"><%=label.get("recr.pcode")%></label> :
									</td>

									<td width="30%" height="22"><s:textfield name="pinCode"
										theme="simple" size="25" maxlength="6"
										onkeypress="return numbersOnly();" /></td>

									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.mobno" id="recr.mobno"
										ondblclick="callShowDiv(this);"><%=label.get("recr.mobno")%></label> :
									</td>

									<td width="30%" height="22"><s:textfield name="mobileNo"
										theme="simple" size="25" maxlength="25"
										onkeypress="return validatePhoneNo()" /></td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.email" id="recr.email"
										ondblclick="callShowDiv(this);"><%=label.get("recr.email")%></label>
									<font color="red">*</font> :</td>
									<td width="30%" height="22"><s:textfield name="email"
										theme="simple" size="25" maxlength="50"
										onkeypress="return emailCheck();" /></td>

									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.faxno" id="recr.faxno"
										ondblclick="callShowDiv(this);"><%=label.get("recr.faxno")%></label> :</td>
									
									<td width="30%" height="22"><s:textfield name="faxNo"
										theme="simple" size="25" maxlength="25"
										onkeypress="return numbersWithHiphen();" /></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="recr.charg" id="recr.charg"
										ondblclick="callShowDiv(this);"><%=label.get("recr.charg")%></label> :</td>
									
									<td width="34%" height="22"><s:textfield name="charges"
										theme="simple" size="25" maxlength="25"
										onkeypress="return allCharacters();" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"
										nowrap="nowrap"><label class="set" name="recr.pdesc"
										id="recr.pdesc" ondblclick="callShowDiv(this);"><%=label.get("recr.pdesc")%></label> :</td>
									
									<td width="61%" height="22" valign="bottom">
									<s:textarea name="partnerDesc" cols="26" rows="3"
										onkeyup="callLength('partnerDesc','descCnt1','2000');" ></s:textarea>
										<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_partnerDesc','recr.pdesc','','paraFrm_descCnt1','2000');" >
										&nbsp;Remaining chars	 <s:textfield name="descCnt1"
											readonly="true" size="5"></s:textfield>
										
										</td>
								</tr>


								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="recr.stats" id="recr.stats"
										ondblclick="callShowDiv(this);"><%=label.get("recr.stats")%></label>
									:</td>
									<td><s:select name="status"
										list="#{'A':'Active','D':'Deactive'}" cssStyle="width:151" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="20%" height="22" class="formtext">
									<label class="set" name="recr.termsConditions" id="recr.termsConditions" ondblclick="callShowDiv(this);"><%=label.get("recr.termsConditions")%></label>:</td>
									<td width="61%" height="22" valign="bottom">
										<s:textarea name="termsCond" cols="26" rows="3" onkeyup="callLength('termsCond','descCnt2','2000');" ></s:textarea>
										<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
											 onclick="javascript:callWindow('paraFrm_termsCond','recr.termsConditions','','paraFrm_descCnt2','2000');" >
											&nbsp;Remaining chars	 
										<s:textfield name="descCnt2" readonly="true" size="5"></s:textfield>
										
									</td>
									
								</tr>
								<tr>
									<td colspan="4"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				 </s:else>
				 <tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" />
								<!--  
								<input type="button" value="Send Mail" class="token" onclick="return sendmail();" />
								-->
								</td>
							<td width="22%">  &nbsp;
							 
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
       	</table>
			</td>
		</tr>



		 </table>
</s:form>
<script>

var f9Fields= ["paraFrm_recPartnerName","paraFrm_recContactPerson","paraFrm_recPartnerCity"];
var fieldName = ["paraFrm_recPartnerName","paraFrm_recContactPerson","paraFrm_recPartnerType","paraFrm_recPartnerAddress","paraFrm_email"];
var lableName = ["recr.pname","recr.coper","recr.ptype","recr.addre","recr.email"];
var type = ['enter','enter','select','enter','enter'];

var recDesc=document.getElementById('partnerDesc').value;
var recAddr=document.getElementById('recPartnerAddress').value;
//For Addnew Button 

function addnewFun()
{
	document.getElementById('paraFrm').action="RecruitmentPartners_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun(){
	 if(!validateBlank(fieldName,lableName,type))
          return false;
    
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc = document.getElementById("paraFrm_partnerDesc").value;
	var addr = document.getElementById("paraFrm_recPartnerAddress").value;
	var termsCond = document.getElementById("paraFrm_termsCond").value;
	var stDt = document.getElementById("paraFrm_startDate").value;
	var endDt = document.getElementById("paraFrm_endDate").value;
	if(stDt!=""){
		if(!validateDate("paraFrm_startDate","recr.startDate")){
			return false;
		}		
	}
	if(endDt!=""){
		if(!validateDate("paraFrm_endDate","recr.endDate"))
		{
			return false;
		}	
	}
			
	if(stDt!="" && endDt!=""){
		  if(!dateDifferenceEqual(stDt, endDt, "paraFrm_endDate", "recr.startDate", "recr.endDate")){
				return false;
		  }
	} 
	 
	if(addr != "" && addr.length > 2000){
		alert("Maximum length of  "+document.getElementById("recr.addre").innerHTML.toLowerCase()+ " is 2000 characters.");
		document.getElementById("paraFrm_recPartnerAddress").focus();
		return false;
	}
	
	if(!validateEmail("paraFrm_email")) return false;
	
	if(desc != "" && desc.length > 2000){
		alert("Maximum length of "+document.getElementById("recr.pdesc").innerHTML.toLowerCase()+ " is 2000 characters.");
		document.getElementById("paraFrm_partnerDesc").focus();
		return false;
	}
	
	if(termsCond != "" && termsCond.length > 2000){
		alert("Maximum length of Terms and Conditions is 2000 characters.");
		document.getElementById("paraFrm_termsCond").focus();
		return false;
	}
	
	document.getElementById('paraFrm').action="RecruitmentPartners_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}


//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="RecruitmentPartners_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="RecruitmentPartners_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"RecruitmentPartners_f9Action.action");
	//document.getElementById('paraFrm').submit();
}

//For Cancel Button
function cancelFun(){
    	document.getElementById('paraFrm').action="RecruitmentPartners_cancelFirst.action";
        document.getElementById("paraFrm").submit();
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="RecruitmentPartners_report.action";
    document.getElementById("paraFrm").submit();
}

function callDelete(){
	if(document.getElementById('paraFrm_recPartnerName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record?');
	if(con){
		   document.getElementById('paraFrm').action="RecruitmentPartners_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_recPartnerName').value;
	if(val==""){
		  alert('Please enter Partner Name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not Allowed in Partner Name!');
		 return false;
	}
	var abbr=document.getElementById('paraFrm_recContactPerson').value;
  
    if(abbr==""){
		  alert('Please Enter Contact Person');
		  return false;
  	}
  	var value = LTrim(RTrim(abbr));
	if(abbr!=value){
		alert('Space not Allowed in Contact Person');
		 return false;
	}
   	return true;
}

function callPage(id){
	
		
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="RecruitmentPartners_callPage.action";
	   	document.getElementById('paraFrm').submit();
		
	}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="RecruitmentPartners_callPage.action";
		document.getElementById('paraFrm').submit();
}
   
   
function newRowColor(cell){
			cell.className='onOverCell';

}
	
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}else {
	cell.className='tableCell1';
		}
}
	
	
	
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
		
	   	document.getElementById("paraFrm").action="RecruitmentPartners_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	  }
	  else
	    document.getElementById('hdeleteCode'+i).value="";
}
  	
 
	
function maxlengthAddr()
{
	var addr=document.getElementById('paraFrm_recPartnerAddress').value;
	var addrLength=addr.length;
	if(addrLength=='250')
	{
		return false;
	}
	else
	{
		return true;
	}
}	
function maxlengthDesc()
{
	var desc=document.getElementById('paraFrm_partnerDesc').value;
	var descLength=desc.length;
	if(descLength=='100')
	{
		return false;
	}
	else
	{
		return true;
	}
} 


function sendcredentialsFun() {
	if(!validateBlank(fieldName,lableName,type))
    return false;
    
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc = document.getElementById("paraFrm_partnerDesc").value;
	var addr = document.getElementById("paraFrm_recPartnerAddress").value;
	var termsCond = document.getElementById("paraFrm_termsCond").value;
	var stDt = document.getElementById("paraFrm_startDate").value;
	var endDt = document.getElementById("paraFrm_endDate").value;
	if(stDt!=""){
		if(!validateDate("paraFrm_startDate","recr.startDate")){
			return false;
		}		
	}
	if(endDt!=""){
		if(!validateDate("paraFrm_endDate","recr.endDate"))
		{
			return false;
		}	
	}
			
	if(stDt!="" && endDt!=""){
		  if(!dateDifferenceEqual(stDt, endDt, "paraFrm_endDate", "recr.startDate", "recr.endDate")){
				return false;
		  }
	} 
	if(addr != "" && addr.length > 2000){
		alert("Maximum length of  "+document.getElementById("recr.addre").innerHTML.toLowerCase() +" is 2000 characters.");
		document.getElementById("paraFrm_recPartnerAddress").focus();
		return false;
	}
	
	if(!validateEmail("paraFrm_email")) return false;
	
	if(desc != "" && desc.length > 2000){
		alert("Maximum length of "+document.getElementById("recr.pdesc").innerHTML.toLowerCase() +" is 2000 characters.");
		document.getElementById("paraFrm_partnerDesc").focus();
		return false;
	}
	
	
	if(termsCond != "" && termsCond.length > 2000){
		alert("Maximum length of Terms and Conditions is 2000 characters.");
		document.getElementById("paraFrm_termsCond").focus();
		return false;
	}
	
	if(document.getElementById('paraFrm_recpartnerCode').value == "") {
		alert("Please save the record first.");
		return false;
	}else{
		var con = confirm("Do you really want to send credentials ?");
		if(con) {
			document.getElementById("paraFrm").action="RecruitmentPartners_sendCredentials.action";
			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").submit();
			return true;
		} else {
			return false;
		}
	}
}
</script>

 