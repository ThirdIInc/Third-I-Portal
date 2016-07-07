<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<link rel="stylesheet" href="../pages/common/js_color_picker_v2.css"
	media="screen">
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script src="../pages/common/include/javascript/color_functions.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/js_color_picker_v2.js"></script>
	<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<s:form action="SettingMaster" validate="true" id="paraFrm" name="Setting"
	theme="simple">
	<table width="100%">
		<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">


		<tr>
			<td colspan="1"><s:hidden name="checkFlag_hr" /> <s:hidden
				name="checkFlag_ql" /><s:hidden name="checkFlag_gs" /></td>
				<s:hidden name="divFlag_CR" /><s:hidden name="divFlag_KN" />
				<s:hidden name="divFlag_GE" /><s:hidden name="divFlag_PO" />
				<s:hidden name="divFlag_CM" /><s:hidden name="divFlag_TH" />
				<s:hidden name="divFlag_QL" /><s:hidden name="divFlag_ML" />
				<s:hidden name="divFlag_EM" /><s:hidden name="divFlag_PS" /><s:hidden name="divFlag_TS" />
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td valign="bottom" class="txt"></td>
					<td width="93%" class="txt"><strong class="formhead">HrWorK Configuration
					 </strong></td>
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
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<s:hidden name="checkFlag_hr" /> <s:hidden
				name="checkFlag_cs" /> <s:hidden name="checkFlag_ks" /> <s:hidden
				name="checkFlag_cms" /> <s:hidden name="checkFlag_Bbs" /> <s:hidden
				name="checkFlag_Ql" /><s:hidden name="checkFlag_gs" /><s:hidden name="checkFlag_TS"/>
		<tr>
			<td width="100%" colspan="5"><s:hidden name="hiddenDivId"/>
			 <table height="18" border="0" cellpadding="0"
					cellspacing="0">
			 	<tr>
						<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a href="javascript:callDivLoad('CR');">
							<div align="center">Corporate Info</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
							
							
							<!-- Start of Tips -->
						
						<td style="padding-left: 2px;"><img src="../pages/common/css/default/images/btn_left_carve.gif" width="5"></td>
						<td class="btn-middlebg"><a href="javascript:callDivLoad('TS');"><div align="center">Tips Settings</div></a></td>
						<td style="padding-right: 2px;"><img src="../pages/common/css/default/images/btn_right_carve.gif" width="5"></td>
						<td style="padding-left: 2px;"><img src="../pages/common/css/default/images/btn_left_carve.gif" width="5"></td>
							
							<!-- End of Tips -->
							
							
							
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('KN');"><div align="center">Knowledge</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a	href="javascript:callDivLoad('GE');;"><div align="center">General Info</div></a></td>
						<td style="padding-right: 2px;"><img src="../pages/common/css/default/images/btn_right_carve.gif" width="5"></td>
							<td style="padding-left: 2px;"><img src="../pages/common/css/default/images/btn_left_carve.gif" width="5"></td>
						<td class="btn-middlebg"><a href="javascript:callDivLoad('PO');">
							<div align="center"></div>Poll</a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('CM');">
							<div align="center">Company Comm</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('TH');">
							<div align="center">Thought</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('ML');">
							<div align="center">Mail</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('QL');">
							<div align="center">Quick Link</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('EM');">
							<div align="center">Email</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
							<td style="padding-left: 2px;"><img
							src="../pages/common/css/default/images/btn_left_carve.gif"
							width="5"></td>
						<td class="btn-middlebg"><a
							href="javascript:callDivLoad('PS');">
							<div align="center">Password Settings</div></a></td>
						<td style="padding-right: 2px;"><img
							src="../pages/common/css/default/images/btn_right_carve.gif"
							width="5"></td>
					</tr></table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<s:if test="divFlag_CR">
			<div id="CR"  >
			
				<table class="formbg" width="100%">

				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Corporate
					Information Settings </strong></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%">Link Name <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="linkNameCs" /> <s:hidden name="hiddenCode_Cs" /></td>
				</tr>   

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:if test="checkFlag_cs">
						<input type="radio" name="chkCs" value="link_cs"
							onclick="chkRadio('link_cs','upload_cs','linkCs','uploadCs');"
							checked="checked">link :&nbsp;
					<input type="radio" name="chkCs" value="uploadDoc_cs"
							onclick="chkRadio('upload_cs','link_cs','linkCs','uploadCs');">Upload Document &nbsp;
				</s:if> <s:else>
						<input type="radio" name="chkCs" value="link_cs"
							onclick="chkRadio('link_cs','upload_cs','linkCs','uploadCs');">link :&nbsp;
					<input type="radio" name="chkCs" value="uploadDoc_cs"
							checked="checked"
							onclick="chkRadio('upload_cs','link_cs','linkCs','uploadCs');">Upload Document &nbsp;
				</s:else></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_cs">
					<table>
						<tr>
							<td colspan="2" width="20%">Upload <font color="red">*</font>
							:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26" name="uploadCs"
								readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse1" value="Browse" onclick="uploadFile('uploadCs');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_cs">
					<table>
						<tr>
							<td colspan="2" width="20%">Link <font color="red">*</font>
							:</td>

							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkCs" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;Active :</td>
					<td colspan="2" width="15%"><s:checkbox name="checkCorp"></s:checkbox></td>
				</tr>
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="10%"><s:submit cssClass="add"
						action="SettingMaster_saveCorporate" theme="simple"
						value="    Add"
						onclick="return callValidate('linkNameCs','linkCs','uploadCs','upload_cs');" /> &nbsp;&nbsp;&nbsp;
					<input type="button" class="reset" onclick="resetCs();"
						theme="simple" value="    Reset" /></td>
				</tr>

					<tr>
						<td colspan="3" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>

				</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="50%" colspan="1">Link Name</td>
					<td class="formth" width="25%" colspan="1">File Name</td>
					<td class="formth" width="5%" colspan="1">Active</td>
					<td class="formth" width="20%" colspan="1"></td>

				</tr>

				<s:iterator value="list_csLink">
					<tr>
						<td class="border2" width="50%" colspan="1"><s:hidden
							value="linkCode_Cs" /> <s:property value="linkName_Cs" /></td>
						<td class="border2" width="25%" colspan="1"><s:property
							value="linkFile_Cs" /></td>
						<td class="border2" width="5%" colspan="1"><s:property
							value="linkActive_Cs" /></td>
						<td class="border2" width="20%" colspan="2" align="center"><input
							type="button" value="    Edit" class="edit"
							onclick="callForEdit('<s:property value="linkCode_Cs"/>','SettingMaster_editCs.action','hiddenCode_Cs')" />&nbsp;&nbsp;
						<input type="button" value="    Delete" class="delete"
							onclick="callForDelete('<s:property value="linkCode_Cs"/>','SettingMaster_deleteCs.action','hiddenCode_Cs')" />
						</td>
					</tr>
				</s:iterator>


			</table>

			</div>
			</s:if>
			
			<!-- Added by Nilesh Dhandare On 17th Jan 2011 for Tips Link -->
			<s:if test="divFlag_TS">
			<div id="TS">
			
				<table class="formbg" width="100%">

				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Tips Settings </strong></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%">Link Name <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="tipsName" /> <s:textfield name="hiddenCode_TS" /></td>
				</tr>   

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1">
			   <s:if test="checkFlag_TS">
					<input type="radio" name="chkTs" value="link_ts" onclick="chkRadio('link_ts','upload_ts','linkTs','uploadTs');"checked="checked">link :&nbsp;
					<input type="radio" name="chkTs" value="uploadDoc_ts" 	onclick="chkRadio('upload_ts','link_ts','linkTs','uploadTs');">Upload Document &nbsp;
				</s:if>
			    <s:else>
					<input type="radio" name="chkTs" value="link_cs" onclick="chkRadio('link_ts','upload_ts','linkTs','uploadTs');">link :&nbsp;
					<input type="radio" name="chkTs" value="uploadDoc_ts" checked="checked" onclick="chkRadio('upload_ts','link_ts','linkTs','uploadTs');">Upload Document &nbsp;
				</s:else>
				</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_ts">
					<table>
						<tr>
							<td colspan="2" width="20%">Upload <font color="red">*</font>
							:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26" name="uploadTs"
								readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse1" value="Browse" onclick="uploadFile('uploadTs');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_ts">
					<table>
						<tr>
							<td colspan="2" width="20%">Link <font color="red">*</font>
							:</td>

							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkTs" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;Active :</td>
					<td colspan="2" width="15%"><s:checkbox name="activeTip"></s:checkbox></td>
				</tr>
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="10%"><s:submit cssClass="add"
						action="SettingMaster_saveTips" theme ="simple"	value ="Add"
						onclick="return callValidate('tipsName','linkTs','uploadTs','upload_ts');" /> &nbsp;&nbsp;&nbsp;
					<input type="button" class="reset" onclick="resetTips();" theme="simple" value="    Reset" /></td>
				</tr>

					<tr>
						<td colspan="3" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>

				</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="50%" colspan="1">Link Name</td>
					<td class="formth" width="25%" colspan="1">File Name</td>
					<td class="formth" width="5%" colspan="1">Active</td>
					<td class="formth" width="20%" colspan="1"></td>

				</tr>

				<s:iterator value="list_TipsLink">
					<tr>
						<td class="border2" width="50%" colspan="1"><s:hidden
							value="linkCode_TL" /> <s:property value="linkName_TL" /></td>
						<td class="border2" width="25%" colspan="1"><s:property
							value="linkFile_TL" /></td>
						<td class="border2" width="5%" colspan="1"><s:property
							value="linkActive_TL" /></td>
						<td class="border2" width="20%" colspan="2" align="center"><input
							type="button" value="    Edit" class="edit"
							onclick="callForEdit('<s:property value="linkCode_TL"/>','SettingMaster_editTs.action','hiddenCode_TS')" />&nbsp;&nbsp;
						<input type="button" value="    Delete" class="delete"
							onclick="callForDelete('<s:property value="linkCode_TL"/>','SettingMaster_deleteTs.action','hiddenCode_TS')" />
						</td>
					</tr>
				</s:iterator>


			</table>

			</div>
			</s:if>
			
			
			<!-- End Of Tips -->
			
			
			<s:if test="divFlag_KN">
			<div id="KN">
			<table class="formbg" width="100%">
				
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Knowledge
					Settings </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%">Link Name <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="linkNameKs" /> <s:hidden name="hiddenCode_Ks" /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:if test="checkFlag_ks">
						<input type="radio" name="chkKs" value="link_ks"
							onclick="chkRadio('link_ks','upload_ks','linkKs','uploadKs');"
							checked="checked" id="chkKs">link :&nbsp;
				<input type="radio" name="chkKs" value="uploadDoc_ks" id="chkKs1"
							onclick="chkRadio('upload_ks','link_ks','linkKs','uploadKs');">Upload Document &nbsp;
			</s:if> <s:else>
						<input type="radio" name="chkKs" value="link_ks" id="chkKs"
							onclick="chkRadio('link_ks','upload_ks','linkKs','uploadKs');">link :&nbsp;
				<input type="radio" name="chkKs" value="uploadDoc_ks" id="chkKs1"
							checked="checked"
							onclick="chkRadio('upload_ks','link_ks','linkKs','uploadKs');">Upload Document &nbsp;
			</s:else></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_ks">
					<table>
						<tr>
							<td colspan="2" width="20%">Upload <font color="red">*</font>
							:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26" name="uploadKs"
								readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse" value="Browse" onclick="uploadFile('uploadKs');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_ks">
					<table>
						<tr>
							<td colspan="2" width="20%">Link <font color="red">*</font>
							:</td>

							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkKs" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;Active :</td>
					<td colspan="2" width="15%"><s:checkbox name="checkKnow"></s:checkbox></td>
				</tr>
				
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="10%"><s:submit cssClass="add"
						action="SettingMaster_saveKnowledge" theme="simple"
						value="    Add"
						onclick="return callValidate('linkNameKs','linkKs','uploadKs','upload_ks');" /> &nbsp;&nbsp;&nbsp;
					<input type="button" class="reset" onclick="resetKs();"
						theme="simple" value="    Reset" /></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

			</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">

				<tr>
					<td class="formth" width="50%" colspan="1">Link Name</td>
					<td class="formth" width="25%" colspan="1">File Name</td>
					<td class="formth" width="5%" colspan="1">Active</td>
					<td class="formth" width="20%" colspan="1"></td>
				</tr>

				<s:iterator value="list_ksLink">
					<tr>
						<td class="border2" width="50%" colspan="1"><s:hidden
							value="linkCode_Ks" /> <s:property value="linkName_Ks" /></td>
						<td class="border2" width="25%" colspan="1"><s:property
							value="linkFile_Ks" /></td>
						<td class="border2" width="5%" colspan="1"><s:property
							value="linkActive_Ks" /></td>
						<td class="border2" width="20%" colspan="1" align="center"><input
							type="button" value="    Edit" class="edit"
							onclick="callForEdit('<s:property value="linkCode_Ks"/>','SettingMaster_editKs.action','hiddenCode_Ks')" />&nbsp;&nbsp;
						<input type="button" value="    Delete" class="delete"
							onclick="callForDelete('<s:property value="linkCode_Ks"/>','SettingMaster_deleteKs.action','hiddenCode_Ks')" />
						</td>
					</tr>
				</s:iterator>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

			</table>
			</div>
			</s:if>
			<s:if test="divFlag_GE">
			<div id="GE">
			<table width="100%" class="formbg">
				
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">General
					Information </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				<tr>
					<td class="formth" width="90%" colspan="1">Link</td>
					<td class="formth" width="10%" colspan="1">Active</td>

				</tr>
				<%
				int m = 1;
				%>
				<s:iterator value="list_gsLink">
					<tr>
						<td class="border2" width="90%" colspan="1"><s:hidden
							name="linkCode_Gs" /> <s:property value="linkName_Gs" /></td>
						<td class="border2" align="center" width="10%" colspan="1"><s:if
							test="%{Check_G}">
							<input type="checkbox" name="Check_G"
								onclick="callChk(<%=m%>,'g');" checked="checked" />
							<input type="hidden" name="chkGlink" id='<%="g"+m%>'
								value="<%="Y"%>" />
						</s:if><s:else>
							<input type="checkbox" name="Check_G"
								onclick="callChk(<%=m%>,'g');" />

							<input type="hidden" name="chkGlink" id='<%="g"+m%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					m++;
					%>
				</s:iterator>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				<tr>
					<td align="center"> <s:submit cssClass="add"
						theme="simple" action="SettingMaster_saveGeneral" value="    Save" />
					</td>

				</tr>
				
				
			</table>

			</div>
			</s:if>
			<s:if test="divFlag_PO">
			<div id="PO" >
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Poll
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				<tr>

					<td width="15%" colspan="1">Select Poll :</td>
					<td colspan="3" width="75%"><s:hidden theme="simple"
						name="setting.pollCode" /> <img src="../pages/images/search2.gif"
						height="16" align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'SettingMaster_f9actionPoll.action'); ">
					</td>
				</tr>
				<tr>
					<td colspan="1" width="15%">Question <font color="red">*</font>
					:</td>
					<td colspan="3" width="77%"><s:textfield size="60"
						name="setting.question" />&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td colspan="1" width="15%">Poll Expiry :</td>
					<td colspan="3" width="77%"><s:textfield size="20"
						name="setting.expiry" /><s:a
							href="javascript:NewCal('paraFrm_setting_expiry','DDMMYYYY');">
							<img class="iconImage" src="../pages/images/recruitment/Date.gif"
								width="16" height="16" class="imageIcon" border="0"
								align="absmiddle" />
						</s:a>
					</td>
				<tr>
					<td colspan="1" width="15%">Option :</td>
					<td colspan="3" width="77%"><s:textfield size="20"
						name="setting.option" />&nbsp;&nbsp;</td>

				</tr>
				<tr>
					<td>Choose Color:</td>
					<td><s:textfield id="paraFrm_optionColor" name="optionColor" readonly="true"/>
					<input type="button" value="Select Color" class="token"
						onclick="callColor(this);">&nbsp;&nbsp; <s:submit
						cssClass="add" action="SettingMaster_addPoll"
						theme="simple" value="    Add"
						onclick="return addValidatePoll('add');" /></td>
				</tr>
				
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><s:submit cssClass="add"
						onclick="return addValidatePoll('save'); "
						action="SettingMaster_savePoll" theme="simple" value="    Save" />
					&nbsp;&nbsp; <s:submit cssClass="delete"
						action="SettingMaster_deletePoll" theme="simple"
						onclick=" return deletePoll();" value="    Delete" />&nbsp;&nbsp;
					<s:submit cssClass="reset" action="SettingMaster_resetPoll"
						theme="simple" value="    Reset" /></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

			</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="10%" colspan="1">Sr.No.</td>
					<td class="formth" width="50%" colspan="1">Option</td>
					<td class="formth" width="10%" colspan="1">Color</td>
					<td class="formth" width="30%" colspan="1"></td>

					<s:hidden name="hiddenOptionCode" />
				</tr>
				<%!int i = 0;
	int s = 0;%>
				<s:iterator value="list">
					<tr>
						<td class="border2" width="10%" colspan="1"><%=++i%></td>
						<td class="border2" width="60%" colspan="1"><s:property
							value="optionList" /></td>
						<td class="border2" width="10%" colspan="1" align="center">
						<table width="30%" bgcolor="<s:property  value='color' />">
							<tr>
								<s:hidden name="color" id='<%="color"+i %>' />
								<s:hidden name="optionCode" id='<%="hCode"+i %>'/>
								<td>&nbsp;</td>
							</tr>

						</table>
						<td class="border2" width="30%" colspan="1" align="center"><input
						type="button" value="   Edit" class="edit"
						onclick="callEdit('<s:property value="optionCode"/>')" /> <input
						type="button" value="   Delete" class="delete"
						onclick="return callDelete('<s:property value="optionCode"/>');" /></td>
							
					</tr>
				</s:iterator>
				<%
					s = i;
					i = 0;
				%>
				
			<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
			</table>

			</div>
			</s:if>
			<s:if test="divFlag_CM">
			<div id="CM" >
			<table class="formbg" width="100%">

				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Company
					 Communication Settings</strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="15%">Link Name <font color="red">*</font>
					:</td>
					<td colspan="1" width="85%"><s:textfield size="40"
						name="linkNameCms" /> <s:hidden name="hiddenCode_Cms"
						 /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:if test="checkFlag_cms">
						<input type="radio" name="chkCms" value="link_Cms"
							onclick="chkRadio('link_cms','upload_cms','linkCms','uploadCms');"
							checked="checked">link :&nbsp;
				<input type="radio" name="chkCms" value="uploadDoc_Cms"
							onclick="chkRadio('upload_cms','link_cms','linkCms','uploadCms');">Upload Document &nbsp;
			</s:if> <s:else>
						<input type="radio" name="chkCms" value="link_Cms"
							onclick="chkRadio('link_cms','upload_cms','linkCms','uploadCms');">link :&nbsp;
				<input type="radio" name="chkCms" value="uploadDoc_Cms"
							checked="checked"
							onclick="chkRadio('upload_cms','link_cms','linkCms','uploadCms');">Upload Document &nbsp;
			</s:else></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="upload_cms">
					<table>
						<tr>
							<td colspan="2" width="20%">Upload <font color="red">*</font>
							:</td>
							<td>&nbsp;</td>
							<td colspan="1">&nbsp;<s:textfield size="26"
								name="uploadCms" readonly="true" /></td>
							<td colspan="1"><input type="button" class="token"
								name="Browse" value="Browse" onclick="uploadFile('uploadCms');" />&nbsp;&nbsp;</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2" width="50%">
					<div id="link_cms">
					<table>
						<tr>
							<td colspan="2" width="20%">Link <font color="red">*</font>
							:</td>

							<td colspan="2" align="left">&nbsp;<s:textfield size="29"
								name="linkCms" /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;Active :</td>
					<td colspan="2" width="15%"><s:checkbox name="checkComp"></s:checkbox></td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="20%"><s:submit cssClass="add"
						action="SettingMaster_saveCompanyComm" theme="simple"
						value="    Add"
						onclick="return callValidate('linkNameCms','linkCms','uploadCms','upload_cms');" /> &nbsp;&nbsp;
					<input type="button" class="reset" onclick="resetCms();"
						theme="simple" value="    Reset" /></td>

				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

			</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="50%" colspan="1">Link Name</td>
					<td class="formth" width="25%" colspan="1">File Name</td>
					<td class="formth" width="5%" colspan="1">Active</td>
					<td class="formth" width="20%" colspan="1"></td>

				</tr>

				<s:iterator value="list_cmsLink">
					<tr>
						<td class="border2" width="50%" colspan="1"><s:hidden
							value="linkCode_Cms" /> <s:property value="linkName_Cms" /></td>
						<td class="border2" width="25%" colspan="1"><s:property
							value="linkFile_Cms" /></td>
						<td class="border2" width="5%" colspan="1"><s:property
							value="linkActive_Cms" /></td>
						<td class="border2" width="20%" colspan="1" align="center"><input
							type="button" value="    Edit" class="edit"
							onclick="callForEdit('<s:property value="linkCode_Cms"/>','SettingMaster_editCms.action','hiddenCode_Cms')" />&nbsp;&nbsp;
						<input type="button" value="    Delete" class="delete"
							onclick="callForDelete('<s:property value="linkCode_Cms"/>','SettingMaster_deleteCms.action','hiddenCode_Cms')" />
						</td>
					</tr>
				</s:iterator>

			</table>

			</div>
			</s:if>
			<s:if test="divFlag_TH">
			<div id="TH" >
			<table class="formbg" width="100%">

				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Thought of the Day
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%">Enter Thought of the Day<font
						color="red">*</font> :</td>
					<td colspan="1" width="65%"><s:textfield size="60"
						name="thougth" id="thougth"/> <s:hidden name="hiddenCode_th"/> 
						<s:submit cssClass="add"  theme="simple" action="SettingMaster_addThougth"
						value="    Add"
						onclick="return addValidate();" />
						</td>
				</tr>
			
			</table>
			<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="7%" colspan="1">Sr. No.</td>
					<td class="formth" width="65%" colspan="1">Thought of the
					day</td>
					<td class="formth" width="10%" colspan="1">Date</td>
					<td class="formth" width="19%" colspan="1"></td>

				</tr>

				<%
				int j = 1;
				%>
				<s:iterator value="list_thougth">

					<tr>
						<td class="border2" width="5%" colspan="1"><%=j++%></td>
						<td class="border2" width="65%" colspan="1"><s:hidden
							value="thougthCode" /> <s:property value="thougthName" /></td>
						<td class="border2" width="10%" colspan="1"><s:property
							value="thougthDate" /></td>
						<td class="border2" width="20%" colspan="1" align="center"><input
							type="button" value="    Edit" class="edit"
							onclick="callForEdit('<s:property value="thougthCode"/>','SettingMaster_editThougth.action','hiddenCode_th')" />&nbsp;&nbsp;
						<input type="button" value="    Delete" class="delete"
							onclick="callForDelete('<s:property value="thougthCode"/>','SettingMaster_deleteThougth.action','hiddenCode_th')" />
						</td>
					</tr>

				</s:iterator>

			</table>

			</div>
			</s:if>
			<s:if test="divFlag_QL">
			<div id="QL" >
			<table width="100%" class="formbg">


				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Quick Link Settings
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>


				<tr>
					<td class="formth" width="90%" colspan="3">Link Name</td>
					<td class="formth" width="10%" colspan="1">Active</td>

				</tr>
				<%
				int k = 0;
				%>
				<s:iterator value="list_QlLink">
					<tr>
						<td class="border2" width="90%" colspan="3"><s:hidden
							name="linkCode_Ql" /> <s:property value="linkName_Ql" /></td>
						<td class="border2" width="10%" colspan="1"><s:if
							test="%{Check_Q}">
							<input type="checkbox" name="Check_Q"
								onclick="callChk(<%=k%>,'q')" checked="checked" />
							<input type="hidden" name="chkQlink" id='<%="q"+k%>'
								value="<%="Y"%>" />
						</s:if> <s:else>
							<input type="checkbox" name="Check_Q"
								onclick="callChk(<%=k%>,'q')" />
							<input type="hidden" name="chkQlink" id='<%="q"+k%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					k++;
					%>
				</s:iterator>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><s:submit cssClass="add"
						theme="simple" action="SettingMaster_saveLink" value="    Save" /></td>

				</tr>

			</table>

			</div>
			</s:if>
			

			
			<s:if test="divFlag_EM">
			<div id="EM" >
			<table width="100%" class="formbg">


				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Email-Info Settings
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>


				<tr>
					<td class="formth" width="90%" colspan="3">Module Name</td>
					<td class="formth" width="10%" colspan="1">Send Mail</td>

				</tr>
				<%
				int p = 0;
				%>
				<s:iterator value="list_EM">
					<tr>
						<td class="border2" width="85%" colspan="3"><s:hidden
							name="linkCode_EM" /> <s:property value="linkName_EM" /></td>
						<td class="border2" width="15%" colspan="1"><s:if
							test="%{Check_EM}">
							<input type="checkbox" name="Check_EM"
								onclick="callChk(<%=p%>,'em')" checked="checked" />
							<input type="hidden" name="chkemail" id='<%="em"+p%>'
								value="<%="Y"%>" />
						</s:if> <s:else>
							<input type="checkbox" name="Check_EM"
								onclick="callChk(<%=p%>,'em')" />
							<input type="hidden" name="chkemail" id='<%="em"+p%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					p++;
					%>
				</s:iterator>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><s:submit cssClass="add"
						theme="simple" action="SettingMaster_saveEmail" value="    Save" /></td>

				</tr>

			</table>

			</div>
			</s:if>
			<s:if test="divFlag_PS">
			<div id="PS" >
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Password Settings
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%">Expiry Flag:</td>
					<td colspan="3" width="75%"><s:checkbox name="expFlag"
						id="expFlag" onclick="ExpFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Expiry Periodicity(In Days) :</td>
					<td width="75%" colspan="3"><s:textfield name="expPeriodicity"
						maxlength="4" onkeypress="return numbersOnly();" /></td>


				</tr>
				<tr>
					<td colspan="1" width="25%">Prevent Reuse Password :</td>
					<td colspan="3" width="75%"><s:checkbox name="reuseFlag"
						id="reuseFlag" onclick="ReuseFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Prevent Reuse Password Periodicity :</td>
					<td colspan="3" width="75%"><s:textfield name="reusePassPed"
						maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Lock Flag :</td>
					<td colspan="3" width="75%"><s:checkbox name="lockFlag"
						id="lockFlag" onclick="LockFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Lock Periodicity(In Hrs) :</td>
					<td colspan="3" width="75%"><s:textfield name="lockPrd"
						maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Lock Attempts :</td>
					<td colspan="3" width="75%"><s:textfield name="lockAttmpt"
						maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>
				
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="3" align="left"><s:submit cssClass="add"
						action="SettingMaster_savePasswordSetting" theme="simple"
						value="    Save" onclick="return saveValidation();" /></td>
				</tr>

			</table>
			</div>
			</s:if>
			<s:if test="divFlag_ML">
			<div id="ML" >
			<table class="formbg" width="100%">

				<tr>
					<td width="75%" class="txt" colspan="3">
						<strong class="formhead">&nbsp;&nbsp;&nbsp;&nbsp;Inbound Settings
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				

				<tr>
					<td colspan="1" width="25%">MailBox Server :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailServer" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox Protocol :</td>
					<td width="75%" colspan="3"><s:select name="mailProtocol"
						headerKey="-1" headerValue="--Select--" cssStyle="width:125"
						list="#{'POP2':'POP2','POP3':'POP3','IMAP':'IMAP'}" /></td>


				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox Port :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailPort" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox UserName :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailUsername" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox Password :</td>
					<td colspan="3" width="75%"><s:password theme="simple"
						name="mailPasswd" /></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="3" align="left"><s:submit cssClass="add"
						action="SettingMaster_saveMailbox" theme="simple"
						value="    Save" /></td>
				</tr>


				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
					<tr>
					<td width="75%" class="txt" colspan="3">
						<strong class="formhead">&nbsp;&nbsp;&nbsp;&nbsp;Outbound Settings
					 </strong></td>
				</tr>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%">MailBox Server :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailServerOut" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox Protocol :</td>
					<td width="75%" colspan="3"><s:select name="mailProtocolOut"
						headerKey="-1" headerValue="--Select--" cssStyle="width:125"
						list="#{'SMTP':'SMTP','IMAP':'IMAP'}" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%">MailBox Port :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailPortOut" onkeypress="return numbersOnly();" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Authentication Required :</td>
					<td colspan="3" width="75%"><s:checkbox name="authChk"
						onclick="return callCheckAuth();" /></td>
				</tr>
				<tr>

					<td colspan="1" width="25%">MailBox UserName :</td>
					<td colspan="3" width="75%"><s:textfield size="20"
						name="mailUsernameOut" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">MailBox Password :</td>
					<td colspan="3" width="75%"><s:password theme="simple"
						name="mailPasswdOut" /></td>

				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="3" align="left"><s:submit cssClass="add"
						action="SettingMaster_saveMailboxOut" theme="simple"
						onclick="return chkMailData();" value="    Save" /></td>
				</tr>
				
				<tr>
					<td width="75%" class="txt" colspan="3">
						<strong class="formhead">&nbsp;&nbsp;&nbsp;&nbsp;System Mails
					 </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="25%">Email Id :</td>
					<td colspan="3" width="75%"><s:textfield theme="simple" size="35"
						name="sysEmail" /><s:hidden name="hiddenMailCode" /> </td>

				</tr>
				<tr>
					<td colspan="1" width="25%">Password :</td>
					<td colspan="3" width="75%"><s:password theme="simple"
						name="sysEmailPsswd" /></td>
				</tr>
				
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="3" align="left"><s:submit cssClass="add"
						action="SettingMaster_saveSysMail" theme="simple"
						onclick="return chkSysMail();" value="    Add" /></td>
				</tr>
				</table>
				<img
				src="../pages/images/recruitment/space.gif" width="5" height="5" />
				<table class="formbg" width="100%">
				<tr>
				<td class="formth" width="10%">Sr. No.</td>
				<td class="formth" width="60%">Email Id</td>
				<td class="formth" width="30%"></td>
				</tr>
				<% int count = 0;%>
					
						<s:iterator value="sysMailList">
						<tr>
							<td class="border2"><%=++count%></td>
							<td class="border2"><s:hidden name="sysMailCode" /><s:property
								value="sysEmailId" /></td>
							<td class="border2" align="center"><input type="button" value="    Edit"
								class="edit"
								onclick="callForEdit('<s:property value="sysMailCode"/>','SettingMaster_editSysMail.action','hiddenMailCode');" />&nbsp;&nbsp;
							<input type="button" value="    Delete" class="delete"
								onclick="callForDelete('<s:property value="sysMailCode"/>','SettingMaster_deleteSysMail.action','hiddenMailCode');" /></td>
						</tr>
				  </s:iterator>
					<%count=0; %>
				</table>
			
			</div>
			</s:if>
			<script type="text/javascript">
					var countries=new ddtabcontent("countrytabs");
					countries.setpersist(true);
					countries.setselectedClassTarget("link"); //"link" or "linkparent"
					countries.init();
				</script></td>
		</tr>
	</table>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>

<script type="text/javascript">

var flag_cs = document.getElementById("paraFrm_checkFlag_cs").value;
var flag_ks = document.getElementById("paraFrm_checkFlag_ks").value;
var flag_cms = document.getElementById("paraFrm_checkFlag_cms").value;
var flag_Ql = document.getElementById("paraFrm_checkFlag_Ql").value;
var flag_gs = document.getElementById("paraFrm_checkFlag_gs").value;
var flag_ts = document.getElementById("paraFrm_checkFlag_TS").value;


callCheckAuth();
function saveValidation()
{
	if(document.getElementById('expFlag').checked==true)
	{
		if(document.getElementById('paraFrm_expPeriodicity').value=="")
		{
			alert('Please Enter Expiry Periodicity ');
			return false;
		}
	}

	if(document.getElementById('reuseFlag').checked==true)
	{
		if(document.getElementById('paraFrm_reusePassPed').value=="")
		{
			alert('Please Enter Reuse Password Periodicity  ');
			return false;
		}
	}

	if(document.getElementById('lockFlag').checked==true)
	{
		if(document.getElementById('paraFrm_lockPrd').value=="")
		{
			alert('Please Enter Lock Periodicoty   ');
			return false;
		}
		if(document.getElementById('paraFrm_lockAttmpt').value=="")
		{
			alert('Please Enter Lock Attempts   ');
			return false;
		}
	}
	return true;
}

function ExpFlag()
{
	if(document.getElementById('expFlag').checked==true)
	{
		document.getElementById('paraFrm_expPeriodicity').disabled=false;
	}
	else
	{
		document.getElementById('paraFrm_expPeriodicity').value="";
		document.getElementById('paraFrm_expPeriodicity').disabled=true;
	}
}
function ReuseFlag()
{
	if(document.getElementById('reuseFlag').checked==true)
		document.getElementById('paraFrm_reusePassPed').disabled=false;

	else
	{
		document.getElementById('paraFrm_reusePassPed').value="";
		document.getElementById('paraFrm_reusePassPed').disabled=true;
	}
}

function LockFlag()
{
	if(document.getElementById('lockFlag').checked==true)
	{
		document.getElementById('paraFrm_lockPrd').disabled=false;
		document.getElementById('paraFrm_lockAttmpt').disabled=false;
	}
	else
	{
		document.getElementById('paraFrm_lockPrd').value="";
		document.getElementById('paraFrm_lockAttmpt').value="";
		document.getElementById('paraFrm_lockPrd').disabled=true;
		document.getElementById('paraFrm_lockAttmpt').disabled=true;
	}
}
	
function callDelete(id)
{
		if(!confirm("The votes on the option will be deleted!.\n Are you sure you want to delete the option?")){
		
		return false;
		}
		else{
		
		document.getElementById('paraFrm').action='SettingMaster_delete.action';
		
		document.getElementById('paraFrm_hiddenOptionCode').value=id;
		
	    document.getElementById('paraFrm').submit();
	    return true;
	    }
	    
}

function chkSysMail()
{
	var fieldName = ['paraFrm_sysEmail','paraFrm_sysEmailPsswd'];
	var labelName = ['System Email Id','Email Password'];
	if(!checkMandatory(fieldName,labelName))
		return false;
	if(!validateEmail('paraFrm_sysEmail'))
		return false;
	return true;
}

function callEdit(id)
{
	document.getElementById('paraFrm').action='SettingMaster_edit.action';
	document.getElementById('paraFrm_hiddenOptionCode').value=id;
	document.getElementById('paraFrm').submit();
}

function uploadFile(fieldName) 
{
	var path="upload";
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function chkRadio(divId,hideDivId,link,upload)
{
alert("divId ----"+divId);
alert("divId----"+hideDivId);
alert("divId ----"+link);
alert("divId ----"+upload);


	document.getElementById(divId).style.display = '';
	document.getElementById(hideDivId).style.display = 'none';
	document.getElementById('paraFrm_'+link).value = '';
	document.getElementById('paraFrm_'+upload).value = '';
}

function callOnLoad()
{
	var getDivId = document.getElementById('paraFrm_hiddenDivId').value;
	if(getDivId == 'PO')
		document.getElementById('paraFrm_optionColor').style.backgroundColor = document.getElementById('paraFrm_optionColor').value;
	if(getDivId == 'CR')
	{
	if(flag_cs=="false")
	{
		document.getElementById('upload_cs').style.display = '';
		document.getElementById('link_cs').style.display = 'none';
	}
	else
	{
		document.getElementById('upload_cs').style.display = 'none';
		document.getElementById('link_cs').style.display = '';
	}
	}
	if(getDivId == 'KN')
	{
	if(flag_ks=="false")
	{
		document.getElementById('upload_ks').style.display = '';
		document.getElementById('link_ks').style.display = 'none';
	}
	else
	{
		document.getElementById('upload_ks').style.display = 'none';
		document.getElementById('link_ks').style.display = '';
	}
	}
	if(getDivId == 'CM')
	{
	if(flag_cms=="false")
	{
		document.getElementById('upload_cms').style.display = '';
		document.getElementById('link_cms').style.display = 'none';
	}
	else
	{
		document.getElementById('upload_cms').style.display = 'none';
		document.getElementById('link_cms').style.display = '';
	}
	}
}

function callValidate(name_link,link,upload,divId)
{
	var chkLink = document.getElementById("paraFrm_"+name_link).value;
	var chkUploadName = document.getElementById("paraFrm_"+upload).value;
	var chkLinkName = document.getElementById("paraFrm_"+link).value;
	var chkLinkBlank = LTrim(chkLink);
	if(chkLink=="")
	{
		alert("Please Enter the Link Name");
		document.getElementById("paraFrm_"+name_link).focus();
		return false;
	}
	if(chkLinkBlank == "")
		{
			alert("Please Enter the Link Name");
			document.getElementById("paraFrm_"+name_link).focus();
			return false;
		}
		
	var chkStart = chkLinkName.substring(0,4);
	if(chkStart.length!=0)
	{
		if(!(chkStart=="http" || chkStart=="HTTP"))
		{
			alert("Link Name Should Start with http ");
			return false;
		}
	}
	var chkDiv = document.getElementById(divId).style.display;
	if(chkDiv == "none")
	{
		if(chkLinkName =="")
		{
			alert("Please Enter the Link Path/URL");
			document.getElementById("paraFrm_"+link).focus();
			return false;
		}
		var val  = document.getElementById(link).value;
		var value = LTrim(val);
		if(value=="")
		{
			alert("Please Enter the Link Path/URL");
			document.getElementById("paraFrm_"+link).value = "";
			document.getElementById("paraFrm_"+link).focus();
			return false;
		}
	}
	else
	{
		if(chkUploadName == "")
		{
			 alert("Please Submit Your File. Click Browse to Submit");
			 document.getElementById("paraFrm_"+upload).focus();
			 return false;
		}
			
	}
	document.getElementById('paraFrm').target="main"; 
	document.getElementById('paraFrm').target="";
	return true;
		
}

function callForEdit(editCode,action,hiddenCodeTxt)
{	
	document.getElementById("paraFrm_"+hiddenCodeTxt).value = editCode;
	document.getElementById('paraFrm').action=action;
	document.getElementById('paraFrm').submit();
}

function callForDelete(delCode,action,hiddenCodeTxt)
{
	if(confirm("Are you sure want to delete?"))
	{	
		document.getElementById("paraFrm_"+hiddenCodeTxt).value = delCode;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').submit();
	}
}

function addValidate()
{
	var val = document.getElementById('thougth').value;
	if(val=="")
	{
		alert("Please Enter Thought and Click Add.");
		return false;
	}
	return true;
}

function callChk(id,chkName)
{	
  	var idName = chkName+id;
  	
	if(document.getElementById(idName).value=='Y')
 		document.getElementById(idName).value='N';
	else if(document.getElementById(idName).value=='N')
 		document.getElementById(idName).value='Y';
}
function callCheckAuth()
{
	
	var getDivId = document.getElementById('paraFrm_hiddenDivId').value;
	if(getDivId == "PS")
	{
		ExpFlag();
	    ReuseFlag();
	    LockFlag();
	}
	try{
	callOnLoad();
	if(getDivId == 'ML')
	{
	var chkBoxStatus = document.getElementById('paraFrm_authChk').checked;
	if(!chkBoxStatus)
	{
		
		document.getElementById('paraFrm_mailUsernameOut').disabled = true;
		document.getElementById('paraFrm_mailPasswdOut').disabled = true;
		document.getElementById('paraFrm_mailUsernameOut').value = "";
	    document.getElementById('paraFrm_mailPasswdOut').value = "";
	}
	else
	{
		document.getElementById('paraFrm_mailUsernameOut').disabled = false;
		document.getElementById('paraFrm_mailPasswdOut').disabled = false;
	}
	}
	}
	catch(e){
	}

}
function chkMailData()
{
	var chkBoxStatus = document.getElementById('paraFrm_authChk').checked;
	if(chkBoxStatus)
	{
		var fieldName = ['paraFrm_mailUsernameOut','paraFrm_mailPasswdOut'];
		var labelName = ['User Name', 'Password'];
		if(chkBoxStatus)
		{
			if(!checkMandatory(fieldName,labelName))
				return false;
		}
	}
	else
	{
		document.getElementById('paraFrm_mailUsernameOut').value = "";
		document.getElementById('paraFrm_mailPasswdOut').value = "";
	}
	return true;
}
function deletePoll()
{
	if(document.getElementById('paraFrm_setting_pollCode').value == "")
		{
			alert("Please Select Poll To Delete");
			return false;
		}
	if(!confirm("Are You sure you want to delete the Poll?"))
		return false;
	return true;
}

function addValidatePoll(value) 
{
	var rowCount = <%= s%>;
	if(document.getElementById('paraFrm_setting_question').value == "")
	{
		alert("Please Enter Your Question");
		document.getElementById('paraFrm_setting_question').focus();
		return false;
	}
	if(value == "add")
	{
		if(document.getElementById('paraFrm_setting_option').value == "")
		{
			alert("Please Enter Your Option");
			document.getElementById('paraFrm_setting_option').focus();
			return false;
		}
		else if(document.getElementById('paraFrm_optionColor').value == "")
		{
			alert("Please Choose the Color");
			document.getElementById('paraFrm_optionColor').focus();
			return false;		
		}
		if(rowCount == 5 && document.getElementById('paraFrm_hiddenOptionCode').value=='')
		{
			document.getElementById('paraFrm_optionColor').value = "";
			document.getElementById('paraFrm_optionColor').style.backgroundColor = "#FFFFFF";
			document.getElementById('paraFrm_setting_option').value = "";
			alert("You Cannot add more than five Options");
			return false;
		}
	}
	else if(rowCount < 1)
	{
		alert("Please Add atleast one Option");
		return false; 
	}
		var count;
		var editCode = document.getElementById('paraFrm_hiddenOptionCode').value;
		var checkValue = document.getElementById('paraFrm_optionColor').value;
		for(count=1;count <= rowCount;count++)
		{
			var optionColor = document.getElementById('color'+count).value;
			var hCode = document.getElementById('hCode'+count).value;
			if(editCode == "") 
			{
				if(checkValue == optionColor)
				{
					alert("Please Choose Different Color! Two Options cannot have same color");
					return false;
				}
			}
			else
			{
				if(checkValue == optionColor)
				{
					if(hCode!=editCode)
					{
						alert("Please Choose Different Color! Two Options cannot have same color");
						return false;
					}
				}
			}
		}
	
	
	var expDate=document.getElementById('paraFrm_setting_expiry').value;
	
	if(expDate==""){
		if(!confirm('Are You Sure You Want to Save without an Expiry?'))
		{
			return false;
		}
		return true;
	}
	var thisDate=new Date();
	//alert('date--'+thisDate);
	var checkDate = datedifference();	
	
	if(expDate!="") {
		
		
		if (checkDate){
			alert("Expiry Date must be greater than Today's Date");
			return false;
	 	}
	}
	
	if(!(expDate=="")) {
		var fld=['paraFrm_setting_expiry'];
	  	var lbl=['Poll Expiry'];
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
		
		if(expTime<thisDate)
		{ 
			return true;
			
		}else{
			return false;
		}
	
	}

function callColor(obj)
{
	showColorPicker(obj,document.forms[0].optionColor,document.forms[0].color);
}

function callDivLoad(id)
{

	document.getElementById('paraFrm').action='SettingMaster_showOnlyInfo.action';
	document.getElementById('paraFrm_hiddenDivId').value = id;
	document.getElementById('paraFrm').submit();
}
	function resetCs(){

		document.getElementById('paraFrm_uploadCs').value="";	
		document.getElementById('paraFrm_linkNameCs').value="";
		document.getElementById('paraFrm_linkCs').value="";
		document.getElementById('paraFrm_hiddenCode_Cs').value="";
		document.getElementById('paraFrm_checkFlag_cs').value="true";
		document.getElementById('paraFrm_checkCorp').checked=false;
		
		}
		
function resetTips(){

		document.getElementById('paraFrm_uploadTs').value="";	
		document.getElementById('paraFrm_tipsName').value="";
		document.getElementById('paraFrm_linkTs').value="";
		document.getElementById('paraFrm_hiddenCode_Ts').value="";
		document.getElementById('paraFrm_checkFlag_TS').value="true";
		document.getElementById('paraFrm_activeTip').checked=false;
		
		}		
		
		
		
	function resetCms(){
		document.getElementById('paraFrm_uploadCms').value="";
		document.getElementById('paraFrm_linkNameCms').value="";
		document.getElementById('paraFrm_linkCms').value="";
		document.getElementById('paraFrm_hiddenCode_Cms').value="";
		document.getElementById('paraFrm_checkFlag_cms').value="true";
		document.getElementById('paraFrm_checkComp').checked=false;
		
		}
		
	function resetKs(){
		document.getElementById('paraFrm_uploadKs').value="";
		document.getElementById('paraFrm_linkKs').value="";
		document.getElementById('paraFrm_linkNameKs').value="";
		document.getElementById('paraFrm_hiddenCode_Ks').value="";
		document.getElementById('paraFrm_checkFlag_ks').value="true";
		document.getElementById('paraFrm_checkKnow').checked=false;

		}

</script>
