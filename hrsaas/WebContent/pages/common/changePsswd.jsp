<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="Ajax.js"></script>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<%
			Object passInfoObj[][] = (Object[][]) request
			.getAttribute("passInfoObj");
	String minLength = "6";
	String maxLength = "15";
	String msg = "";
	boolean flag = false;
	if (passInfoObj != null) {
		if (!String.valueOf(passInfoObj[0][0]).equals("0"))
			minLength = String.valueOf(passInfoObj[0][0]);
		if (!String.valueOf(passInfoObj[0][1]).equals("0"))
			maxLength = String.valueOf(passInfoObj[0][1]);
		if (String.valueOf(passInfoObj[0][2]).equals("Y"))
			msg = " Alphabets,";
		if (String.valueOf(passInfoObj[0][3]).equals("Y"))
			msg += " Special Characters,";
		if (String.valueOf(passInfoObj[0][4]).equals("Y"))
			msg += " Numbers,";
		if (String.valueOf(passInfoObj[0][5]).equals("Y"))
			msg += " At least one Capital Letter,";
	} else
		System.out.println("In Null >>>>>>>>>>>>>>>>>>>>>>>>>");
	if (!msg.equals(""))
		msg = " and must contain" + msg.substring(0, msg.length() - 1);
%>


<s:form action="ChangePassword" theme="simple" id="paraFrm"
	validate="true">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" height="150px">
		<tr>
			<td colspan="1"><s:hidden name="checkFlag_hr" /> <s:hidden
				name="checkFlag_ql" /><s:hidden name="checkFlag_gs" /></td>
		</tr>


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My  
					Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td width="100%">
			<table height="18" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td class="btn-middlebg">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li><a href="javascript:callDivLoad('HR');" id="HR_L">
						<div align="center"><span>Password Management&nbsp;&nbsp;</span></div>
						</a></li>
						<li><a href="javascript:callDivLoad('EM');" id="EM_L">
						<div align="center"><span>Change Question Answers&nbsp;&nbsp;</span></div>
						</a></li>
						<li><a href="javascript:callDivLoad('IM');" id="IM_L">
						<div align="center"><span>Change Image and Message&nbsp;&nbsp;</span></div>
						</a></li><!--
						<li><a href="javascript:callDivLoad('DT');" id="DT_L">
						<div align="center"><span>Set Default Tab&nbsp;&nbsp;</span></div>
						</a></li>
						<li><a href="javascript:callDivLoad('EC');" id="EC_L">
						<div align="center"><span>Configure Email Account&nbsp;&nbsp;</span></div>
						</a></li>
						--><li><a href="javascript:callDivLoad('AP');" id="AP_L">
						<div align="center"><span>Authorization Password&nbsp;&nbsp;</span></div>
						</a></li>
						
					</ul>
					</div>
					</td>
				</tr>
			</table>
			<s:hidden name="pssword" /><s:hidden name="hiddenDivID" />
			<div id="HR" style="display: none;height: 700px" >
			<table class="formbg" width="100%" border="0">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="text_head">Password
					Management </strong></td>
				</tr>

				<s:hidden name="emp_id" />
				<tr>
					<td width="22%" nowrap="nowrap"><label class="set"
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td><s:textfield theme="simple" name="empnm" readonly="true"
						onkeypress="return charactersOnly();" size="25" /></td>
				</tr>
				<tr>
					<td width="22%" nowrap="nowrap"><label class="set"
						name="oldpass" id="oldpass" ondblclick="callShowDiv(this);"><%=label.get("oldpass")%></label><font
						color="red"> *</font> :</td>
					<td><s:password theme="simple" name="oldpsswd" size="25" /></td>
				</tr>
				<tr>
					<td width="22%" nowrap="nowrap"><label class="set"
						name="newpass" id="newpass" ondblclick="callShowDiv(this);"><%=label.get("newpass")%></label><font
						color="red"> *</font> :</td>
					<td width="23%"><s:password theme="simple" name="newpsswd1"
						size="25" onkeyup="testPassword(paraFrm.newpsswd1.value);" /></td>
					<td width="53%">
					<table>
						<tr>
							<td nowrap="nowrap"><font size="1"> Password
							Strength:</font></td>
							<td><a id="Words"><font size="1"></font></a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"><label class="set"
						name="confpass" id="confpass" ondblclick="callShowDiv(this);"><%=label.get("confpass")%></label><font
						color="red"> *</font> :</td>
					<td><s:password theme="simple" name="newpsswd2" size="25" /></td>
					<td width="53%" colspan="1">(Password should be Min. <%=minLength%>
					and Max. <%=maxLength%> Characters in length <%=msg%>)</td>

				</tr>

				<tr>

					<td>&nbsp;</td>
					<td colspan="1">
					
					<s:if test="%{bean.insertFlag || bean.updateFlag}">
						<input type="button" class="add"
						theme="simple" value="Save" id="savebtn"
						onclick="return calladd('hrwork');" />
					</s:if> 
					<s:if test="%{bean.generalFlag}"></s:if>   
				
					
					
					</td>
				</tr>

				</tr>
			</table>
			</div>

			<div id="EM" style="display: none;height: 150px">
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="text_head">Change
					Question Answers </strong></td>
				</tr>


				<tr>

					<td width="20%" nowrap="nowrap"><label class="set" name="que1"
						id="que1" ondblclick="callShowDiv(this);"><%=label.get("que1")%></label><font
						color="red"> *</font> :</td>
					<td width="53%" nowrap="nowrap"><s:select name="secureQue1"
						cssStyle="width:350" size="1" list="tmap" theme="simple" /><s:textfield
						name="secureAns1" value="" /></td>

				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"><label class="set" name="que2"
						id="que2" ondblclick="callShowDiv(this);"><%=label.get("que2")%></label><font
						color="red"> *</font> :</td>
					<td width="53%" nowrap="nowrap"><s:select name="secureQue2"
						cssStyle="width:350" size="1" list="qmap" theme="simple" /><s:textfield
						name="secureAns2" value="" /></td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"><label class="set" name="que3"
						id="que3" ondblclick="callShowDiv(this);"><%=label.get("que3")%></label><font
						color="red"> *</font> :</td>
					<td width="53%" nowrap="nowrap"><s:select name="secureQue3"
						cssStyle="width:350" size="1" list="quesmap" theme="simple" /><s:textfield
						name="secureAns3" value="" />
				</tr>



				<tr>
					<td>&nbsp;</td>
					<td colspan="1">
					
					<s:if test="%{bean.insertFlag || bean.updateFlag}">
					<input type="button" class="add"
						theme="simple" value="   Save" onclick="return calladd('email');" />
					</s:if>   <s:if test="bean.generalFlag"></s:if>
					
					</td>
				</tr>

			</table>
			</div>
			<div id="IM" style="height: 700px;display: none">
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="text_head">Change
					Image and Message </strong></td>
				</tr>



				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="3" width="100%">
							
								<s:if test="%{bean.insertFlag || bean.updateFlag}">
				<input type="button"
								class="add" theme="simple" value="   Save"
								onclick="return calladd('image');" />
					</s:if> <s:if test="bean.generalFlag"></s:if> 
							
							</td>
						</tr>

						<tr>
							<td width="30%" colspan="1"><label class="set"
								name="securemsg" id="securemsg" ondblclick="callShowDiv(this);"><%=label.get("securemsg")%></label>
							:</td>
							<td width="10%" colspan="1"><s:textfield name="userText"
								size="30" maxLength="30" /><input type="text" name="login"
								style="visibility: hidden;" /></td>
							<td width="60%" colspan="1" align="center"><s:if
								test="isImage">
								<%
									String imgName = null;
									imgName = (String) request.getAttribute("imgName");
								%>
								<%
											if (imgName != null && !imgName.equals("null")
											&& !imgName.equals("")) {
								%><img height="100" src="<%=imgName%>" width="100">
								<%
								} else {
								%>
								<%
								}
								%>
							</s:if></td>
						</tr>

						<tr>

						</tr>
						<tr>
							<td colspan="3" width="100%">&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">

						<tr>
							<td width="100%" colspan="4"><label class="set"
								name="secureimg" id="secureimg" ondblclick="callShowDiv(this);"><%=label.get("secureimg")%></label>
							:</td>
						<tr>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName0" onclick="setVal(id);" value="1" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/1.jpg" height="100"
								width="100" /></td>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName1" onclick="setVal(id);"
								onclick="call();" value="2" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/2.jpg" height="100"
								width="100" /></td>
						</tr>
						<tr>
							<td width="100%" colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName2" onclick="setVal(id);" value="3" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/3.jpg" height="100"
								width="100" /></td>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName3" onclick="setVal(id);" value="4" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/4.jpg" height="100"
								width="100" /></td>
						</tr>
						<tr>
							<td width="100%" colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName4" onclick="setVal(id);" value="5" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/5.jpg" height="100"
								width="100" /></td>
							<td width="10%" colspan="1"><input type="radio"
								name="imgName" id="imgName5" onclick="setVal(id);" value="6" /></td>
							<td width="40%" colspan="1"><img
								src="../pages/common/css/default/images/6.jpg" height="100"
								width="100" /></td>
						</tr>
						<!--


						<tr>
							<td>&nbsp;</td>
							<td colspan="1"><input type="button" class="add"
								theme="simple" value="   Save"
								onclick="return calladd('image');" /></td>
						</tr>

					-->
					</table>

					</td>
				</tr>
			</table>
			<s:hidden name="hiddenRadio" />
			</div>
			<div id="DT" style="display: none;height: 100px">
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="text_head">Set Default Tab
					  </strong></td>
				</tr>
				<tr>

					<td width="20%" nowrap="nowrap"><label class="set" name="defTab"
						id="defTab" ondblclick="callShowDiv(this);"><%=label.get("defTab")%></label><font
						color="red"> *</font> :</td>
					<td width="53%" nowrap="nowrap"><s:if test="homeTab"><s:select name="selHomeTab"
						list="homeTab" theme="simple" /></s:if>
						<s:else><s:select name="selHomeTab" list="#{'':''}"/></s:else></td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"></td>
					<td width="53%" nowrap="nowrap"><input type="button" class="save" name="save" value=" Save" onclick="setDefaultTab();"/></td>
				</tr>
				</table>
				</div>
				
			<div id="EC" style="display: none;height: 100px">
			<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
	 
		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" >
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configure Email Account</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>	<s:if test="%{bean.viewFlag}">
							<td width="78%"><s:submit cssClass="add" value=" Add Account" action="ChangePassword_addAccount" />		</td>
						</s:if>
					
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
		
	
		<s:hidden name="hiddenCode" />
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
									
						<tr>
							<td class="formth" width="8%" colspan="1"><label
								name="check.name" id="check.name"
								ondblclick="callShowDiv(this);"><%=label.get("SrNo")%></label></td>
								<td width="25%" class="formth" colspan="1" ><label
								name="check.description" id="check.description"
								ondblclick="callShowDiv(this);"><%=label.get("IttAccountName")%></label></td>
							<td width="25%" class="formth" colspan="1" ><label
								name="check.description" id="check.description"
								ondblclick="callShowDiv(this);"><%=label.get("IttUserName")%></label></td>
							
							<td width="15%" class="formth" colspan="1"><label
								name="check.submit" id="check.submit"
								ondblclick="callShowDiv(this);"><%=label.get("isOfficialMail")%></label></td>						
							
							<td width="18%" class="formth" colspan="1">
							<input type="submit" value="Edit" class="edit" onclick="return callEdit('<s:property	value="ittHiddenCode" />');">
							<input type="submit" value="Delete" class="delete" onclick=" return callDelete('<s:property	value="ittHiddenCode" />');">
						
							</td>

						</tr>
						<%
						try {
						%>
						<%int i=1; %>
						<%!int tot=0; %>
						<s:iterator value="bean.list">							
							<tr>									
							<td  width="5%" class="sortableTD" colspan="1"><%=i %>
							<input type="hidden" name="ittHiddenCode" id="ittHiddenCode<%=i%>" value='<s:property value="ittHiddenCode" />'/>
							</td>
							<td width="25%" class="sortableTD" colspan="1" align="center"><s:property value="ittAccountName" /></td>
							<td width="25%" class="sortableTD" colspan="1" align="center"><s:property value="ittUserName" /></td>
							
							<td width="15%" class="sortableTD" colspan="1" align="center"><s:hidden	name="ittServerName" />
							
							<s:property value="ittOffcicialCheckBox" />
							</td>
						
							<td width="18%" class="sortableTD" colspan="1" align="center">						
							<input type="checkbox" name="editDelete" id="chk<%=i%>">	
						
							</td>							
							</tr>
							<%i++; %>
						</s:iterator>
						
						<%tot=i;
							} catch (Exception e) {
							}
						%>
						
				
			</table>
			</td>
		</tr>	
		
		<tr>	<s:if test="%{bean.viewFlag}">
							<td width="78%">
						<s:submit cssClass="add" value="Add Account" action="ChangePassword_addAccount" />		
								
								</td>
						</s:if>
					
					<td width="22%">&nbsp;</td>
				</tr>
	</table>
			</div>	
			<div id="AP" style="display: none;height: 150px" >
			<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="1" class="formbg">
		 
		<tr>
			<td width="100%" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" >
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Authorization Password</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		 
				<tr>
					<td width="22%" nowrap="nowrap"><label class="set"
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td><s:textfield theme="simple" name="employeeName" readonly="true"
						onkeypress="return charactersOnly();" size="25" /></td>
				</tr>
				<tr>
					<td width="22%" nowrap="nowrap"><label class="set"
						name="authpass" id="authpass" ondblclick="callShowDiv(this);"><%=label.get("authpass")%></label><font
						color="red"> *</font> :</td>
					<td width="23%"><s:password theme="simple" name="authorizationPassword"
						size="25" onkeyup="testAuthPassword(paraFrm.authorizationPassword.value);" /></td>
					<td width="53%">
					<table>
						<tr>
							<td nowrap="nowrap"><font size="1"> Password
							Strength:</font></td>
							<td><a id="AuthWords"><font size="1"></font></a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="20%" nowrap="nowrap"><label class="set"
						name="confauthpass" id="confauthpass" ondblclick="callShowDiv(this);"><%=label.get("confauthpass")%></label><font
						color="red"> *</font> :</td>
					<td><s:password theme="simple" name="confirmAuthorizationPassword" size="25" /></td>
					<td width="53%" colspan="1">(Password should be Min. <%=minLength%>
					and Max. <%=maxLength%> Characters in length <%=msg%>)</td>

				</tr>

				<tr>

					<td>&nbsp;</td>
					<td colspan="1">
					
					<s:if test="%{bean.insertFlag || bean.updateFlag}">
					<input type="button" class="add"
						theme="simple" value="    Save"
						onclick="return calladd('authpass');" />
					</s:if> <s:if test="bean.generalFlag"></s:if> 
					</td>
				</tr>
				
		 <s:hidden name="flagForCongMail" />
	</table></div></td></tr></table> 
	
	
</s:form>
<script type="text/javascript">

callOnLoad();

function callOnLoad()
{
 	document.getElementById('HR').style.display = 'none';
	document.getElementById('EM').style.display = 'none';
	document.getElementById('IM').style.display = 'none';
	document.getElementById('DT').style.display = 'none';
	document.getElementById('EC').style.display = 'none';
 	document.getElementById('AP').style.display = 'none';
	document.getElementById('HR_L').className='li';
	document.getElementById('EM_L').className='li';
	document.getElementById('IM_L').className='li';
	//document.getElementById('DT_L').className='li';
	//document.getElementById('EC_L').className='li';
	document.getElementById('AP_L').className='li';
	
	if(document.getElementById('paraFrm_flagForCongMail').value == "shashi"){
		document.getElementById('EC').style.display = '';
		document.getElementById('EC_L').className='on';	
	}
	
	else if(document.getElementById('paraFrm_hiddenDivID').value == "")
	{
		document.getElementById('HR').style.display = '';
		document.getElementById('HR_L').className='on';
	}
	else
	{
		document.getElementById(document.getElementById('paraFrm_hiddenDivID').value).style.display = '';
		document.getElementById(document.getElementById('paraFrm_hiddenDivID').value+"_L").className='on';
	}
}

function callDivLoad(id)
{
	 
	document.getElementById('HR').style.display = 'none';
	document.getElementById('EM').style.display = 'none';
	document.getElementById('IM').style.display = 'none';
	document.getElementById('DT').style.display = 'none';
	document.getElementById('EC').style.display = 'none';
	document.getElementById('AP').style.display = 'none';
	document.getElementById('HR_L').className='li';
	document.getElementById('EM_L').className='li';
	document.getElementById('IM_L').className='li';
	//document.getElementById('DT_L').className='li';
	//document.getElementById('EC_L').className='li';
	document.getElementById('AP_L').className='li';
	document.getElementById(id).style.display = '';
	document.getElementById(id+"_L").className = 'on';
	document.getElementById('paraFrm_flagForCongMail').value='';
	if(id=='EC'){
	document.getElementById('paraFrm_flagForCongMail').value='shashi';
	}
}

function calladd(type)
{
	try{
		
	if(type == 'hrwork')
	{alert(type);
	 
			var fieldName1 = ["paraFrm_oldpsswd","paraFrm_newpsswd1","paraFrm_newpsswd2"];
			var labelName1 = ["oldpass","newpass","confpass"];
			var flag = ["enter","enter","enter"];
			var fieldName2 = ["paraFrm_userName","paraFrm_oldPass"];
			var labelName2 = ["Email Address","Password"];
		
		if(!validateBlank(fieldName1, labelName1, flag)){
			return false;
		}
		var oldpssd  = document.getElementById('paraFrm_oldpsswd').value;                               
		var newpssd1 = document.getElementById('paraFrm_newpsswd1').value;
		var newpssd2 = document.getElementById('paraFrm_newpsswd2').value;
		var pssword  = document.getElementById('paraFrm_pssword').value;
		if(newpssd1.length < eval(<%= minLength%>) || newpssd1.length > eval(<%= maxLength%>)){
			alert(document.getElementById('newpass').innerHTML.toLowerCase()+' should be of min <%= minLength%> and max <%= maxLength%> characters.');
			document.getElementById('paraFrm_newpsswd1').focus();
			return false;
		} 
		if(!validatePassword(pssword, oldpssd, newpssd1, newpssd2)){
			alert("Please enter old password correctly");
			return false;
		}
			
		alert("action");
		document.getElementById('paraFrm').action="ChangePassword_savePsswd.action";
		document.getElementById('paraFrm').submit();
		
	}
	
	if(type == 'email'){
		
		var fieldName1 = ["paraFrm_secureAns1","paraFrm_secureAns2","paraFrm_secureAns3"];
		var labelName1 = ["que1","que2","que3"];
		var flag = ["enter","enter","enter"];
		var question1=document.getElementById('paraFrm_secureQue1').value;
		var question2=document.getElementById('paraFrm_secureQue2').value;
		var question3=document.getElementById('paraFrm_secureQue3').value;
		var answer_1=document.getElementById('paraFrm_secureAns1').value;
		var answer_2=document.getElementById('paraFrm_secureAns2').value;
		var answer_3=document.getElementById('paraFrm_secureAns3').value;
	
		if(!validateBlank(fieldName1, labelName1, flag)){
			return false;
		}
		if(question1==""){
			alert('Please select '+document.getElementById('que1').innerHTML.toLowerCase());
			return false;
		}
		if(answer_1==""){
			alert('Please enter answer of '+document.getElementById('que1').innerHTML.toLowerCase());
			return false;
		}
		if(question2==""){
			alert('Please select '+document.getElementById('que2').innerHTML.toLowerCase());
			return false;
		}
		if(answer_2==""){
			alert('Please enter answer of '+document.getElementById('que2').innerHTML.toLowerCase());
			return false;
		}
		if(question3==""){
			alert('Please select '+document.getElementById('que3').innerHTML.toLowerCase());
			return false;
		}
		if(answer_3==""){
			alert('Please enter answer of '+document.getElementById('que3').innerHTML.toLowerCase());
			return false;
		}
		
		document.getElementById('paraFrm').action="ChangePassword_saveQuestion.action";
		document.getElementById('paraFrm').submit();
	}
	
	if(type == 'image'){
		
		var fieldName1 = ["paraFrm_userText"];
		var labelName1 = ["securemsg"];
		var flag = ["enter"];
		if(!validateBlank(fieldName1, labelName1, flag)){
			return false;
		}
		document.getElementById('paraFrm_userText').focus;
		if(document.getElementById('paraFrm_userText').value==""){
 			
			alert("Please enter "+document.getElementById('securemsg').innerHTML.toLowerCase());;
 			return false;
 		}
 
 		if(document.getElementById('paraFrm_hiddenRadio').value==""){
 			alert("Please select at least one image");
 			return false;
 		}
 		
 		document.getElementById('paraFrm').action="ChangePassword_saveImage.action";
		document.getElementById('paraFrm').submit();
	}

	if(type == 'authpass'){
		var fieldName1 = ["paraFrm_authorizationPassword","paraFrm_confirmAuthorizationPassword"];
		var labelName1 = ["authpass","confauthpass"];
		var flag = ["enter","enter"];
		var authpass = document.getElementById('paraFrm_authorizationPassword').value;
		var confirmAuthPass = document.getElementById('paraFrm_confirmAuthorizationPassword').value;
		var pssword  = document.getElementById('paraFrm_pssword').value; 
		
		if(!validateBlank(fieldName1, labelName1, flag)){
			return false;
		}
			
		var authorizationPassword = document.getElementById('paraFrm_authorizationPassword').value;
		var confirmAuthorizationPassword = document.getElementById('paraFrm_confirmAuthorizationPassword').value;
	 
		if(authorizationPassword.length < eval(<%= minLength%>) || authorizationPassword.length > eval(<%= maxLength%>)){
			
			alert(document.getElementById('authpass').innerHTML.toLowerCase()+' should be of min <%= minLength%> and max <%= maxLength%> characters.');
			document.getElementById('paraFrm_authorizationPassword').focus();
			return false;
		} 
		
		if(!validateAuthPassword(pssword,authpass, confirmAuthPass)){
			return false;
		}
		document.getElementById('paraFrm').action="ChangePassword_saveAuthorizationPsswd.action";
		document.getElementById('paraFrm').submit();
		 
	}		
		

	}
	catch(e){
		// alert(e);
	}
}



function setVal(id)
{
 if(document.getElementById(id).checked==true)
 {
 	document.getElementById('paraFrm_hiddenRadio').value="Y";
 }
}
var passwd=document.getElementById("paraFrm_newpsswd1").value
testPassword(passwd);

var password=document.getElementById("paraFrm_authorizationPassword").value
testAuthPassword(password);

function testAuthPassword(passwd)
{
	var description = new Array();
	description[0] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8 width=30 bgcolor=#ff0000></td><td height=8 width=120 bgcolor=tan></td></tr></table></td><td>   <b>Weakest</b></td></tr></table>";
	description[1] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=60 bgcolor=#990000></td><td height=8  width=90 bgcolor=tan></td></tr></table></td><td>   <b>Weak</b></td></tr></table>";
	description[2] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=90 bgcolor=#990099></td><td height=8  width=60 bgcolor=tan></td></tr></table></td><td>   <b>Improving</b></td></tr></table>";
	description[3] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=120 bgcolor=#000099></td><td height=8  width=30 bgcolor=tan></td></tr></table></td><td>   <b>Strong</b></td></tr></table>";
	description[4] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=#0000ff></td></tr></table></td><td>   <b>Strongest</b></td></tr></table>";
	description[5] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=tan></td></tr></table></td><td>   <b></b></td></tr></table>";
	var base = 0
	var combos = 0
	if (passwd.match(/[a-z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/[A-Z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/\d+/))
	{
		base = (base+10);
	}
    if (passwd.match(/[>!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]/))
	{
		base = (base+33);
	}
	combos=Math.pow(base,passwd.length);
	if(combos == 1)
	{
 		strVerdict = description[5];
	}
    else if(combos > 1 && combos < 1000000)
	{
		strVerdict = description[0];
	}
	else if (combos >= 1000000 && combos < 1000000000000)
	{
		strVerdict = description[1];
	}
	else if (combos >= 1000000000000 && combos < 1000000000000000000)
	{
		strVerdict = description[2];
	}
	else if (combos >= 1000000000000000000 && combos < 1000000000000000000000000)
	{
		strVerdict = description[3];
	}
	else
	{
		strVerdict = description[4];
	}
		document.getElementById("AuthWords").innerHTML= (strVerdict);
	
}
function testPassword(passwd){

	var description = new Array();
	description[0] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8 width=30 bgcolor=#ff0000></td><td height=8 width=120 bgcolor=tan></td></tr></table></td><td>   <b>Weakest</b></td></tr></table>";
	description[1] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=60 bgcolor=#990000></td><td height=8  width=90 bgcolor=tan></td></tr></table></td><td>   <b>Weak</b></td></tr></table>";
	description[2] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=90 bgcolor=#990099></td><td height=8  width=60 bgcolor=tan></td></tr></table></td><td>   <b>Improving</b></td></tr></table>";
	description[3] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=120 bgcolor=#000099></td><td height=8  width=30 bgcolor=tan></td></tr></table></td><td>   <b>Strong</b></td></tr></table>";
	description[4] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=#0000ff></td></tr></table></td><td>   <b>Strongest</b></td></tr></table>";
	description[5] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=tan></td></tr></table></td><td>   <b></b></td></tr></table>";
	var base = 0
	var combos = 0
	if (passwd.match(/[a-z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/[A-Z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/\d+/))
	{
		base = (base+10);
	}
    if (passwd.match(/[>!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]/))
	{
		base = (base+33);
	}
	combos=Math.pow(base,passwd.length);
	if(combos == 1)
	{
 		strVerdict = description[5];
	}
    else if(combos > 1 && combos < 1000000)
	{
		strVerdict = description[0];
	}
	else if (combos >= 1000000 && combos < 1000000000000)
	{
		strVerdict = description[1];
	}
	else if (combos >= 1000000000000 && combos < 1000000000000000000)
	{
		strVerdict = description[2];
	}
	else if (combos >= 1000000000000000000 && combos < 1000000000000000000000000)
	{
		strVerdict = description[3];
	}
	else
	{
		strVerdict = description[4];
	}
		document.getElementById("Words").innerHTML= (strVerdict);

	}
	
	
	
function validatePassword(password, oldPass, newPass, confPass){
	
	try
	{
	if(password!=oldPass){
		//alert("Please enter "+document.getElementById('oldpass').innerHTML.toLowerCase()+" correctly");
		return false;
	}
	
	if(oldPass==newPass){
		alert(" "+document.getElementById('oldpass').innerHTML.toLowerCase()+" and "+document.getElementById('newpass').innerHTML.toLowerCase()+" must not be same");
		return false;
	}
	
	if(newPass!=confPass){
		alert(" "+document.getElementById('newpass').innerHTML.toLowerCase()+" and "+document.getElementById('confpass').innerHTML.toLowerCase()+" must be same");
		return false;
	}
	
	return true;
	
	}
	catch(e)
	{
	}
}

function validateAuthPassword(oldpass,newPass, confPass){
	 
	if(newPass!=confPass){
		alert(" "+document.getElementById('authpass').innerHTML.toLowerCase()+" and "+document.getElementById('confauthpass').innerHTML.toLowerCase()+" must be same");
		return false;
	 }
	 
	 if(oldpass==newPass){
		alert("Login password and "+document.getElementById('authpass').innerHTML.toLowerCase()+" must not be same");
		return false;
	}
	return true;
	
	 
}

function setDefaultTab()
{
	var homeCode = document.getElementById('paraFrm_selHomeTab').value;
	var combo = document.getElementById('paraFrm_selHomeTab');
	saveDefaultTab('ChangePassword_saveHomeTab.action?homeCode='+homeCode);
	alert("Selected tab has been set as default tab");
	
}
	
	//VALIDATION FOR CONFIGURE EMAIL ACCOUNT
	function callDelete(){

	var mess=confirm('Are you sure want to delete');
	if(mess){
			var hidCode="";
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittHiddenCode'+i).value;
					cnt=cnt+1;	
					hidCode +=id+",";			
				}	
			}				
			hidCode=hidCode.substring(0,eval(hidCode.length)-1);		
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	

    document.getElementById('paraFrm_hiddenCode').value=hidCode;
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="ChangePassword_delete.action";
	document.getElementById('paraFrm').submit();
	}
	else{
	return false;
	}
	}
	
	
				function callEdit(){		
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittHiddenCode'+i).value;
					cnt=cnt+1;				
				}	
			}				
				
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	
					if(eval(cnt)>eval(1)){
					alert('Please select only one checkBox');
					return false;
					}					
						
			    document.getElementById('paraFrm_hiddenCode').value=id;
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="ChangePassword_edit.action";
				document.getElementById('paraFrm').submit();		
	          }
	
	
</script>