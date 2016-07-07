<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var skillArray = new Array();
</script>
<!--<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>-->

<s:form action="SkillMaster" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="flagView" /><s:hidden name="flagShow"/>

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
					<td width="93%" class="txt"><strong class="text_head">Skill</strong></td>
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
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>



							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
					</table>
					</td>
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
									<td width="15%" height="22" class="formtext"><label
										class="set" name="skill.name" id="skill.name"
										ondblclick="callShowDiv(this);"><%=label.get("skill.name")%></label>
									:<font color="red">*</font></td>
									<td width="27%" height="22"><s:hidden
										name="skillMaster.skillCode" /><s:hidden name="hskillCode" />

									<s:textfield name="skillMaster.skills" theme="simple"
										readonly="false" maxlength="50" size="30" /></td>
									<td width="4%" height="22">&nbsp;</td>
									<td width="20%" height="22" class="formtext">&nbsp;</td>
									<td width="25%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td height="22" class="formtext" width="15%" nowrap="nowrap"><label
										class="set" name="skill.abbr" id="skill.abbr"
										ondblclick="callShowDiv(this);"><%=label.get("skill.abbr")%></label>
									:<font color="red">*</font></td>
									<td height="22"><s:textfield name="skillMaster.skillsAbbr"
										theme="simple" readonly="false" size="30" maxlength="15" />
									</td>
									<td height="22">&nbsp;</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td height="22" valign="top" class="formtext" width="15%"><label
										class="set" name="skill.desc" id="skill.desc"
										ondblclick="callShowDiv(this);"><%=label.get("skill.desc")%></label>
									:</td>
									<td height="22"><s:textarea theme="simple"
										name="desciption" rows="4" cols="32"
										onkeyup="callLength('desciption','descCnt','2000');" /></td>

									<td height="22" colspan="3" valign="bottom"><img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle"
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_desciption','skill.desc','','paraFrm_descCnt','2000');">
									&nbsp;Remaining chars <s:textfield name="descCnt"
										readonly="true" size="5"></s:textfield></td>
									<td height="22" class="formtext"></td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="15%" height="22" class="formtext"><label
										class="set" name="skill.stat" id="skill.stat"
										ondblclick="callShowDiv(this);"><%=label.get("skill.stat")%></label>
									:</td>
									<td height="22"><s:select theme="simple" name="status"
										disabled="false" value="%{status}"
										list="#{'A':'Active','D':'Deactive'}" 
										cssStyle="width:180;z-index:5;"
										/></td>
									<td height="22">&nbsp;</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>







							</table>
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
			</td>
		</tr>
	</table>
</s:form>
<script>


function callingStage(stage){
		document.getElementById('paraFrm').action = "SkillMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}


	
	function saveFun()
 {
var f9Fields= ["paraFrm_skillMaster_skills","paraFrm_skillMaster_skillsAbbr"];
var fieldName = ["paraFrm_skillMaster_skills","paraFrm_skillMaster_skillsAbbr"];
var lableName = ["skill.name","skill.abbr"];
var flag = ["enter","enter"];

var desc=document.getElementById('paraFrm_desciption').value;

     if(!validateBlank(fieldName,lableName,flag)){
          	return false;
         }else if(desc.length > 2000){
         	alert("Maximum length of "+document.getElementById("skill.desc").innerHTML.toLowerCase()+" is 2000 characters.");
		 	return false;
         }else{
        	 document.getElementById("paraFrm").action="SkillMaster_save.action";
	   		 document.getElementById("paraFrm").submit();
         
         }     
     
   
     	
    }
    
    
    
    
     function deleteRecord(){
		if(!callDelete('paraFrm_skillMaster_skills'))return false;
		
		else{
			document.getElementById('paraFrm').action="SkillMaster_delete.action";
		   document.getElementById('paraFrm').submit();
		}
	}
 
  
function maxlength()
{
	var length=document.getElementById('paraFrm_desciption').value;
	var l=length.length;
	if(l=='300')
	{
		return false;
	}
	else
	{
		return true;
	}
}	


	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
		document.getElementById("paraFrm").action="SkillMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   
   function editRow(id){	
	
	  	document.getElementById('paraFrm_hiddencode').value = id;
	
	   	document.getElementById("paraFrm").action="SkillMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }
    
    function cancelFun(){
     	document.getElementById("paraFrm").action="SkillMaster_cancelFirst.action";
	    document.getElementById("paraFrm").submit();
    
    
    }
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="SkillMaster_report.action";
    document.getElementById("paraFrm").submit();
}
    
 
 
 
   	
</script>

