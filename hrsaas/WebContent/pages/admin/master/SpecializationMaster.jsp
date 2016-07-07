<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var specArray = new Array();
</script>
<s:form action="SpecializationMaster" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->

	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="editFlag" />
<s:hidden name="flagShow"/>
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
					<td width="93%" class="txt"><strong class="text_head">Specialization</strong></td>
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
										class="set" name="spec.name" id="spec.name"
										ondblclick="callShowDiv(this);"><%=label.get("spec.name")%></label>
									&nbsp:<font color="red">*</font></td>
									<td width="25%" height="22"><s:hidden
										name="specializationCode" /> <s:textfield
										name="specializationName" theme="simple" size="30"
										maxlength="50" readonly="false" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td height="22" width="22%" class="formtext" nowrap="nowrap"><label
										class="set" name="spec.abbr" id="spec.abbr"
										ondblclick="callShowDiv(this);"><%=label.get("spec.abbr")%></label>
									&nbsp:<font color="red">*</font></td>
									<td height="22" width="22%"><s:textfield
										name="specializationAbbr" theme="simple" size="30"
										maxlength="10" readonly="false" /></td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" valign="top" class="formtext"
										nowrap="nowrap"><label class="set" name="spec.desc"
										id="spec.desc" ondblclick="callShowDiv(this);"><%=label.get("spec.desc")%></label>
									:</td>
									<td height="22" nowrap="nowrap"><s:textarea name="specializationDesc"
										cols="32" rows="4" readonly="false"
										onkeyup="callLength('specializationDesc','descCnt','2000');"></s:textarea>
									<img src="../pages/images/zoomin.gif" 
										height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_specializationDesc','spec.desc','','paraFrm_descCnt','2000');">
									&nbsp;Remaining chars <s:textfield name="descCnt"
										readonly="true" size="5"></s:textfield></td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="spec.stat" id="spec.stat"
										ondblclick="callShowDiv(this);"><%=label.get("spec.stat")%></label>
									:</td>
									<td height="22"><s:select name="specializationStatus"
										disabled="false" list="#{'A':'Active','D':'Deactive'}"
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

var f9Fields= ["paraFrm_specializationName","paraFrm_specializationAbbr"];
var fieldName = ["paraFrm_specializationName","paraFrm_specializationAbbr"];
var lableName = ["spec.name","spec.abbr"];
var type = ['enter','enter'];

// For Save Button

function saveFun()
{
	   if(!validateBlank(fieldName,lableName,type))
          return false;
          
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc =	document.getElementById('paraFrm_specializationDesc').value;
	
	if(desc != "" && desc.length > 2000){
		alert("Maximum length of"+document.getElementById('spec.desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
    }    
	
	document.getElementById('paraFrm').action="SpecializationMaster_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Cancel Button

function cancelFun()
{

		document.getElementById('paraFrm').action="SpecializationMaster_cancelFirst.action";
		document.getElementById('paraFrm').submit();
}

</script>

