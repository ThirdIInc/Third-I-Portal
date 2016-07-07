<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" href="../pages/common/js_color_picker_v2.css"
	media="screen">
<script src="../pages/common/include/javascript/color_functions.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/js_color_picker_v2.js"></script>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="SettingMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="pollDate"/>
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3" width="100%"><%@ include
				file="hrConfigHeader.jsp"%></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Opinion Poll
					</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="search"
						onclick="callsF9(500,325,'SettingMaster_f9actionPoll.action'); " value=" Search" />&nbsp;&nbsp;<s:submit cssClass="save"
						onclick="return addValidatePoll('save'); "
						action="SettingMaster_savePoll" theme="simple" value=" Save" />
					 &nbsp;&nbsp;<s:submit cssClass="delete"
						action="SettingMaster_deletePoll" theme="simple"
						onclick=" return deletePoll();" value=" Delete" />&nbsp;&nbsp;
					<s:submit cssClass="reset" action="SettingMaster_resetPoll"
						theme="simple" value=" Reset" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<s:hidden name="setting.pollCode" />
					<td colspan="1" width="15%"><label name="question" id="question" ondblclick="callShowDiv(this);"><%= label.get("question")%> </label> <font color="red">*</font>
					:</td>
					<td colspan="3" width="77%"><s:textfield size="60"
						name="setting.question" />&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="1" width="15%"><label name="poll.expiry" id="poll.expiry" ondblclick="callShowDiv(this);"><%= label.get("poll.expiry")%> </label> :</td>
					<td colspan="3" width="77%"><s:textfield size="20"
						name="setting.expiry" /><s:a
						href="javascript:NewCal('paraFrm_setting_expiry','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" class="imageIcon" border="0"
							align="absmiddle" />
					</s:a></td>
				<tr>
					<td colspan="1" width="15%"><label name="option" id="option" ondblclick="callShowDiv(this);"><%= label.get("option")%> </label> :</td>
					<td colspan="3" width="77%"><s:textfield size="20"
						name="setting.option" />&nbsp;&nbsp;</td>

				</tr>
				<tr>
					<td><label name="color" id="color" ondblclick="callShowDiv(this);"><%= label.get("color")%> </label>:</td>
					<td><s:textfield id="paraFrm_optionColor" name="optionColor"
						readonly="true" /> <input type="button" value="Select Color"
						class="token" onclick="callColor(this);">&nbsp;&nbsp; <s:submit
						cssClass="add" action="SettingMaster_addPoll" theme="simple"
						value=" Add" onclick="return addValidatePoll('add');" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label class="set" name="applDiv" id="applDiv"
						ondblclick="callShowDiv(this);"><%=label.get("applDiv")%></label>
					:</td>
					<td width="76%"><s:hidden name="pollDivCode"/>
					<s:textarea name="pollDivName" cols="25" rows="2"
						readonly="true"></s:textarea> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_pollDivName','paraFrm_pollDivCode'); ">
					</td>

				</tr>
			</table>
			<tr><td>
			<table class="formbg" width="100%" cellpadding="2" cellspacing="2" border="0">
				<tr>
					<td class="formth" width="10%" colspan="1"><label name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%= label.get("sr.no")%> </label></td>
					<td class="formth" width="50%" colspan="1"><label name="option" id="option1" ondblclick="callShowDiv(this);"><%= label.get("option")%> </label></td>
					<td class="formth" width="10%" colspan="1"><label name="color" id="color1" ondblclick="callShowDiv(this);"><%= label.get("color")%> </label></td>
					<td class="formth" width="30%" colspan="1">&nbsp;</td>
					<s:hidden name="hiddenOptionCode" />
				</tr>
				<%!int i = 0;
	int s = 0;%>
				<s:iterator value="list">
					<tr>
						<td class="td_bottom_border" width="10%" colspan="1"><%=++i%></td>
						<td class="td_bottom_border" width="60%" colspan="1"><s:property
							value="optionList" /></td>
						<td class="td_bottom_border" width="10%" colspan="1" align="center">
						<table width="30%" bgcolor="<s:property  value='color' />">
							<tr>
								<s:hidden name="color" id='<%="color"+i %>' />
								<s:hidden name="optionCode" id='<%="hCode"+i %>' />
								<td>&nbsp;</td>
							</tr>

						</table>
						<td class="td_bottom_border" width="30%" colspan="1" align="center"><input
							type="button" title="Edit Record" class="rowEdit"
							onclick="callEdit('<s:property value="optionCode"/>')" /> <input
							type="button" title="Delete Record" class="rowDelete"
							onclick="return callDelete('<s:property value="optionCode"/>');" /></td>

					</tr>
				</s:iterator>
				<%
					s = i;
					i = 0;
				%>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	document.getElementById('paraFrm_optionColor').style.backgroundColor = document.getElementById('paraFrm_optionColor').value;
function callDelete(id){
			if(!confirm("The votes on the "+document.getElementById('option').innerHTML.toLowerCase()+" will be deleted!.\n Are you sure you want to delete the "+document.getElementById('option').innerHTML.toLowerCase()+"?")){
			return false;
			}
			else{			
			document.getElementById('paraFrm').action='SettingMaster_delete.action';			
			document.getElementById('paraFrm_hiddenOptionCode').value=id;			
		    document.getElementById('paraFrm').submit();
		    return true;
		    }		    
	}
		
function callEdit(id){
		document.getElementById('paraFrm').action='SettingMaster_edit.action';
		document.getElementById('paraFrm_hiddenOptionCode').value=id;
		document.getElementById('paraFrm').submit();
	}
	
function deletePoll(){
		if(document.getElementById('paraFrm_setting_pollCode').value == ""){
				alert("Please select poll to delete");
				return false;
		}
		if(!confirm('<%=label.get("confirm.delete")%>'))
			return false;
		return true;
	}

function addValidatePoll(value) {
	var rowCount = <%= s%>;
	if(document.getElementById('paraFrm_setting_question').value == ""){
		alert("Please enter "+document.getElementById('question').innerHTML.toLowerCase());
		document.getElementById('paraFrm_setting_question').focus();
		return false;
	}
	if(value == "add"){
		if(LTrim(document.getElementById('paraFrm_setting_option').value) == ""){
			alert("Please enter "+document.getElementById('option').innerHTML.toLowerCase());
			document.getElementById('paraFrm_setting_option').value="";
			document.getElementById('paraFrm_setting_option').focus();
			return false;
		}
		else if(document.getElementById('paraFrm_optionColor').value == ""){
			alert("Please choose the "+document.getElementById('color').innerHTML.toLowerCase());
			document.getElementById('paraFrm_optionColor').focus();
			return false;		
		}
		if(rowCount == 5 && document.getElementById('paraFrm_hiddenOptionCode').value==''){
			document.getElementById('paraFrm_optionColor').value = "";
			document.getElementById('paraFrm_optionColor').style.backgroundColor = "#FFFFFF";
			document.getElementById('paraFrm_setting_option').value = "";
			alert("You cannot add more than five "+document.getElementById('option').innerHTML.toLowerCase());
			return false;
		}
	}
	else if(rowCount < 1){
		alert("Please add atleast one "+document.getElementById('option').innerHTML.toLowerCase());
		return false; 
	}
		var count;
		var editCode = document.getElementById('paraFrm_hiddenOptionCode').value;
		var checkValue = document.getElementById('paraFrm_optionColor').value;
		for(count=1;count <= rowCount;count++){
			var optionColor = document.getElementById('color'+count).value;
			var hCode = document.getElementById('hCode'+count).value;
			if(editCode == "") {
				if(checkValue == optionColor){
					alert("Please choose different "+document.getElementById('color').innerHTML.toLowerCase()+"! Two "+document.getElementById('option').innerHTML.toLowerCase()+" cannot have same "+document.getElementById('color').innerHTML.toLowerCase());
					return false;
				}
			}
			else{
				if(checkValue == optionColor){
					if(hCode!=editCode){
						alert("Please choose different "+document.getElementById('color').innerHTML.toLowerCase()+"! Two "+document.getElementById('option').innerHTML.toLowerCase()+" cannot have same "+document.getElementById('color').innerHTML.toLowerCase());
						return false;
					}
				}
			}
		}	
	var expDate=document.getElementById('paraFrm_setting_expiry').value;	
	if(expDate==""){
		if(!confirm('Are you sure you Want to save without an expiry?')){
			return false;
		}
		return true;
	}
	var thisDate=new Date();
	//alert('date--'+thisDate);
	var checkDate = datedifference();	
	
	if(expDate!="") {		
		if (checkDate){
			alert(document.getElementById('poll.expiry').innerHTML.toLowerCase()+" must be greater than today's date");
			return false;
	 	}
	}
	
	if(!(expDate=="")) {
		var fld=['paraFrm_setting_expiry'];
	  	var lbl=['poll.expiry'];
	   	var chkdld=validateDate(fld,lbl);
	  	if(!chkdld) {
		   return false;
	   	}
	 }
}

function datedifference() { 	
		var expDate=document.getElementById('paraFrm_setting_expiry').value;
		var thisDate=new Date();		
		expDate=expDate.split("-");
		expTime = new Date(expDate[2],expDate[1]-1,expDate[0]);		
		if(expTime<thisDate){ 
			return true;			
		}else{
			return false;
		}	
	}


function callColor(obj){
	showColorPicker(obj,document.forms[0].optionColor,document.forms[0].color);
}

function callF9Function(divName1,divCode1){    
   callsF9(500,325,'SettingMaster_f9applDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
	
</script>