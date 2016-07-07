<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="RankMaster" id="paraFrm"  theme="simple">
	<table border="0" cellpadding="0"	cellspacing="0" class="formbg" align="center" width="100%">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	<s:hidden name="designationCode" /> 
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"	cellspacing="0" class="formbg">
				<tr>
					<td width="4%"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" ><strong class="text_head">Designation</strong></td>
					<td width="3%" >
						<div align="right">
						<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- The Following code Denotes  Include JSP Page For Button Panel -->
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="80%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		<tr>
			<td colspan="3" width="100%">
					<table width="100%%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
						<tr>
							<td width="27%" height="22" class="formtext"><label name="desg.name"
								class="set" id="desg.name" ondblclick="callShowDiv(this);"><%=label.get("desg.name")%></label> :<font color="red">*</font></td>
							<td width="17%" height="22">
									<s:textfield name="designationName" theme="simple" size="29"
												 maxlength="50"  onkeypress="return allCharacters();" />
							</td>
							<td width="27%" height="22" class="formtext">&nbsp;</td>
							<td width="25%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="27%" height="22" class="formtext"><label name="desg.abbr"
								class="set" id="desg.abbr" ondblclick="callShowDiv(this);"><%=label.get("desg.abbr")%></label> :<font color="red">*</font></td>
							<td width="25%" height="22">
								<s:textfield name="designationAbbr" theme="simple" size="29" maxlength="15" />
							</td>
							<td width="27%" height="22" class="formtext">&nbsp;</td>
							<td width="18%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="22%" height="22" valign="top" class="formtext" nowrap="nowrap"><label name="desg.desc"
								class="set" id="desg.desc" ondblclick="callShowDiv(this);"><%=label.get("desg.desc")%></label> :
							</td>
							<td height="22" colspan="1">
								<s:textarea name="designationDesc" cols="30" rows="3" onkeyup="callLength('descCnt');"></s:textarea>
							</td>
						<td height="22" colspan="1"  valign="bottom" id='ctrlHide'><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_designationDesc','desg.desc','','200','200');" >
							&nbsp;Remaining chars<s:textfield name="descCnt"
							readonly="true" size="5"></s:textfield></td>
							<td width="18%" height="22">&nbsp;</td>
						</tr>
						<tr>
							<td width="20%" colspan="1" class="formtext"><label name="is.active"
								class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
							</td>
							<td colspan="3"><s:checkbox name="isActive" /></td>
						</tr>
						<tr>
							<td width="20%" height="22" class="formtext"><label name="range"
									class="set" id="range" ondblclick="callShowDiv(this);"><%=label.get("range")%></label> :
							</td>
							<td>
								<s:textfield name="salaryRange" size="29" onkeypress="return numbersWithHiphen();"/>
							</td>
						</tr>
						<tr>
							<td colspan="4">
							<Strong><font color="red">Note : </font></Strong>&nbsp;
							Salary range format must be e.g.: 10000-20000
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
		</table>
</s:form>
				
<script>
// For Save Button

function saveFun(){	
	var val=trim(document.getElementById('paraFrm_designationName').value);
	if(val==""){
		alert("Please enter "+document.getElementById('desg.name').innerHTML.toLowerCase());
		document.getElementById('paraFrm_designationName').focus();
		return false;
	}
	
	var abbr=document.getElementById('paraFrm_designationAbbr').value;
		  
	if(abbr==""){
		alert("Please enter "+document.getElementById('desg.abbr').innerHTML.toLowerCase());
		return false;
	}
	
	var value = LTrim(RTrim(abbr));
	if(abbr!=value)	{
			alert("Space not Allowed in "+document.getElementById('desg.abbr').innerHTML.toLowerCase());
			return false;
	}
	var empPostingDesc = document.getElementById('paraFrm_designationDesc').value;
	if(empPostingDesc.length > 200){
		alert("Maximum length of "+document.getElementById('desg.desc').innerHTML.toLowerCase()+
		" is 200 characters.");
		return false;
	}
	var salaryAmt = document.getElementById('paraFrm_salaryRange').value;
	if(salaryAmt!=""){
		if(!verifySalaryRange()){
			return false;
		}
	}
	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'RankMaster_save.action';
	document.getElementById('paraFrm').submit();
  	//return true;
}  

function verifySalaryRange(){
	var salAmt = document.getElementById('paraFrm_salaryRange').value;
	var salArray = salAmt.split("-");
	if(eval(salArray[1])<eval(salArray[0])){
		alert("Please verify the salary range, from amount cannot be greater than to amount.");
		return false;
	}
	return true;
}

function callLength(type){ 
	if(type=='descCnt'){
			var cmt =document.getElementById('paraFrm_designationDesc').value;
			var remain = 200 - eval(cmt.length);
			document.getElementById('paraFrm_descCnt').value = remain;
			
				if(eval(remain)< 0){
			document.getElementById('paraFrm_designationDesc').style.background = '#FFFF99';
			
			}else document.getElementById('paraFrm_designationDesc').style.background = '#FFFFFF';
		}
}  

function resetFun() {
	document.getElementById('paraFrm_show').value = '1';
 	 	document.getElementById('paraFrm').target = "_self";
 	 	document.getElementById('paraFrm').action = 'RankMaster_reset.action';
	document.getElementById('paraFrm').submit();
}
	
function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RankMaster_cancel.action';
		document.getElementById('paraFrm').submit();
}
	
function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RankMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'RankMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
}
	
function editFun() {
		return true;
}
</script>
