<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>


<s:form action="QuestionBankAction"  id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		
			<tr>
          <td colspan="3"><table width="100%"  cellpadding="0" cellspacing="0"  class="formbg">   
        <tr>
          <td width="4%" valign="bottom" class="txt" ><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt" ><strong class="text_head" >Question Bank </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
       </tr>
     </table>
     </td>
     </tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
					
					 
					
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					
					
					<!--
					
					
					<s:submit cssClass="add"
						action="SkillMaster_save" value="   Add New"
						onclick="return savevalidation('addnew');" /> <s:submit
						cssClass="edit" action="SkillMaster_save" theme="simple"
						value="   Update" onclick="return savevalidation('update');" /> <input
						type="button" class="search" value="    Search"
						onclick="javascript:callsF9(500,325,'SkillMaster_f9action.action'); " />

					<s:submit cssClass="reset" value="    Reset"
						action="SkillMaster_reset" /> <s:submit cssClass="delete"
						action="SkillMaster_delete" value="    Delete " 
						onclick="return callDelete('paraFrm_skillMaster_skillCode');" />
					<input type="button" Class="token" value="    Report"
						onclick="return callReport('SkillMaster_report.action');" />
						
						
						
					--></td>



					<td width="22%">
						<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
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
							<td width="24%" height="22" class="formtext"><label  class = "set"  name="qsn" id="qsn" ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label> :</td>
							<td width="27%" height="22">
						<s:hidden name="quesCode"/>
						<s:textarea theme="simple" name="question"
						rows="3" cols="70" onkeypress="return allCharacters()"  readonly="true"/>
								
								
								
								
								
								
						</td>
							<td width="4%" height="22">&nbsp;</td>
							<td width="20%" height="22" class="formtext">&nbsp;</td>
							<td width="25%" height="22">&nbsp;</td>
						</tr>
						<tr>
							<td height="22" class="formtext"><label  class = "set"  name="ansopt" id="ansopt" ondblclick="callShowDiv(this);"><%=label.get("ansopt")%></label> :</td>
							<td height="22"><s:select theme="simple"  name="ansOptions"  onchange="showText();" disabled="true"
								 list="#{'':'Select','S':'Subjective','O':'Objective'}" /></td>
							<td height="22"><label  class = "set"  name="lim" id="lim" ondblclick="callShowDiv(this);"><%=label.get("lim")%></label> :</td>
							<td height="22" class="formtext"><s:textfield theme="simple" name="limit"
								size="22" onkeypress="return allCharacters()" readonly="true" /></td>
							<td height="22">&nbsp;</td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label  class = "set"  name="opt" id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label> :</td>
							<td height="22"><s:textfield name="option" theme="simple"
										size="22" maxlength="30" onkeyup="chkOption();"  readonly="true"/>
		 			
		 					</td>
							<td height="22">&nbsp;</td>
							<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>
						</tr>
						
						<tr>
							<td height="22" class="formtext"><label  class = "set"  name="ansflgopt" id="ansflg" ondblclick="callShowDiv(this);"><%=label.get("ansflg")%></label> :</td>
							<td height="22"><s:checkbox disabled="true"
										name="flag" onclick="callAnswer();"/></td>
							<td height="22"></td>
							<td height="22" class="formtext"></td>
							<td height="22">&nbsp;</td>
						</tr>

					<tr>
							<td height="22" class="formtext"><label  class = "set"  name="sub" id="sub" ondblclick="callShowDiv(this);"><%=label.get("sub")%></label> :</td>
							<td height="22"><s:hidden name="subjectCode"/><s:textfield theme="simple" name="subject"
								size="22"   readonly="true"/></td>
							<td height="22"><label  class = "set"  name="cat" id="cat" ondblclick="callShowDiv(this);"><%=label.get("cat")%></label> :</td>
							<td height="22" class="formtext"><s:textfield theme="simple" name="xxdx"
								size="22" readonly="true"/></td>
							
						</tr>

	<tr>
							<td height="22" class="formtext" nowrap="nowrap"><label  class = "set"  name="wtg" id="wtg" ondblclick="callShowDiv(this);"><%=label.get("wtg")%></label> :</td>
							<td height="22"><s:textfield theme="simple" name="qsnWtg"
								size="22"    readonly="true"/></td>
							<td height="22" nowrap="nowrap"><label  class = "set"  name="complvl" id="complvl" ondblclick="callShowDiv(this);"><%=label.get("complvl")%></label> :</td>
							<td height="22" class="formtext"><s:hidden name="categoryCode"/><s:textfield theme="simple" name="compLevel"
								size="22" onkeypress="return allCharacters()"   readonly="true"/></td>
							<td height="22">&nbsp;</td>
						</tr>
						
						<tr>
							<td height="22" class="formtext"><label  class = "set"  name="sts" id="sts" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label> :</td>
							<td height="22"><s:select theme="simple"  name="ansOptions"  onchange="showText();" disabled="true"
								 list="#{'A':'Active','D':'Deactive'}" /></td>
							<td height="22"></td>
							<td height="22" class="formtext"></td>
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
			<td><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		<tr><td colspan="3">
		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>
		</tr>
		
		
		
		
		<!--

		

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>





		--><tr>
			<td colspan="3">

			
			
			
				<tr><td></td>


</tr>
			

				<tr>
					<td align="right">
		</tr>



		<tr>
			<td colspan="3">
			
	
		</tr>



		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>

	</table>

</s:form>

<script>
function addnewFun(){

	document.getElementById('paraFrm').action="QuestionBankAction_addNew.action";
  		document.getElementById('paraFrm').submit();

}

function searchFun(){
callsF9(500,325,'QuestionBankAction_f9Search.action');


}




</script>

