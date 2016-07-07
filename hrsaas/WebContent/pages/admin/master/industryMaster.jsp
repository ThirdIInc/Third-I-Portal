<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var specArray = new Array();
</script>
<s:form action="IndustryMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="industryCode" /><s:hidden name="flagShow"/>
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
					<td width="93%" class="txt"><strong class="text_head">Industry
					Type</strong></td>
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
							<td width="22%"><jsp:include
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
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>


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
										class="set" name="indu.name" id="indu.name"
										ondblclick="callShowDiv(this);"><%=label.get("indu.name")%></label>
									&nbsp:<font color="red">*</font></td>
									<td width="25%" height="22"><s:textfield
										name="industryName" theme="simple" size="30" maxlength="50" />
									</td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="indu.abbr" id="indu.abbr"
										ondblclick="callShowDiv(this);"><%=label.get("indu.abbr")%></label>
									&nbsp:<font color="red">*</font></td>
									<td width="25%" height="22"><s:textfield
										name="industryAbbr" theme="simple" size="30" maxlength="10" />
									</td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="indu.desc" id="indu.desc"
										ondblclick="callShowDiv(this);"><%=label.get("indu.desc")%></label>:
									</td>
									<td height="25"><s:textarea name="industryDesc" cols="32"
										rows="4" onkeyup="callLength('industryDesc','descCnt','2000');"></s:textarea></td>
									<td height="22" valign="bottom"><img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle"
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_industryDesc','indu.desc','','paraFrm_descCnt','2000');">
									Remaining chars <s:textfield name="descCnt" readonly="true"
										size="5"></s:textfield></td>

								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label
										class="set" name="indu.stat" id="indu.stat"
										ondblclick="callShowDiv(this);"><%=label.get("indu.stat")%></label>:
									</td>
									<td><s:select name="industryStatus"
										list="#{'A':'Active','D':'Deactive'}"
										cssStyle="width:180;z-index:5;" /></td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td colspan="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="22%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>
<script>

var f9Fields= ["paraFrm_industryName", "paraFrm_industryAbbr"];
var fieldName = ["paraFrm_industryName", "paraFrm_industryAbbr"];
var lableName = ["indu.name", "indu.abbr"];
var type = ['enter','enter'];

// For Save Button

function saveFun()
{
	 if(!validateBlank(fieldName,lableName,type))
          return false;
    
	var desc = document.getElementById("paraFrm_industryDesc").value;
	
	if(desc != "" && desc.length > 2000){
		alert("Maximum length of "+document.getElementById("indu.desc").innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
	}
	
	document.getElementById('paraFrm').action="IndustryMaster_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Cancel Button

function cancelFun(){
    	document.getElementById('paraFrm').action="IndustryMaster_cancelFirst.action";
        document.getElementById("paraFrm").submit();
    
}
</script>

