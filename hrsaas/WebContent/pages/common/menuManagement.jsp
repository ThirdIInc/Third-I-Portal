
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="MenuManagement" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="menuParPath"></s:hidden>
		<s:hidden name="parname"></s:hidden>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Menu
					Management </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">


				<tr>
					<td colspan="4"><s:if test="menu.insertFlag">

						<s:if test="menu.updateFlag">
							<s:submit cssClass="save" action="MenuManagement_save"
								theme="simple" value="    Save   "
								onclick="return formValidate();" />
						</s:if>
					</s:if> <s:if test="menu.viewFlag">
						<s:submit cssClass="reset" action="MenuManagement_reset"
							theme="simple" value="    Reset  " />
					</s:if> <s:if test="divMast.deleteFlag">
						<s:submit cssClass="delete" action="MenuManagement_delete"
							theme="simple" value="    Delete  "
							onclick="return callDelete('paraFrm_menu_menuID');" />
					</s:if> <s:if test="menu.viewFlag">
						<input type="button" class="search" value="    Search"
							onclick="javascript:callsF9(500,325,'MenuManagement_f9action.action'); " />
					</s:if> <s:hidden theme="simple" name="menu.menuID" /> <s:if
						test="menu.viewFlag">

						<input type="button" class="token"
							onclick="callReport('MenuManagement_report.action')"
							value="  Report " />
					</s:if></td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>



			</table>

			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">

						<tr>

							<td width="15%" colspan="1" height="22">Menu Name<font
								color="red">*</font>:</td>
							<td colspan="2" width="50%"><s:textfield size="40"
								theme="simple" maxlength="180" name="menu.menuName" /></td>
						</tr>

						<tr>

							<td>Menu Link<font color="red">*</font>:</td>
							<td colspan="2" width="50%" height="22"><s:textfield
								size="40" theme="simple" name="menu.menuLink" /></td>
						</tr>

						<tr>

							<td>Parent Menu Name<font color="red">*</font>:</td>
							<td colspan="2" width="50%" height="22"><s:hidden
								name="menu.menuParntId" /> <s:textfield readonly="true"
								theme="simple" name="menu.menuParValue" size="30" /> <img
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15"
								onclick="javascript:callsF9(500,325,'MenuManagement_f9Menuaction.action');" />

							</td>
						</tr>

						<tr>
							<td width="15%" colspan="1">Menu Target<font color="red">*</font>:</td>

							<td width="85%" colspan="2" height="22"><s:select
								list=" #{'main':'Same Window','_blank':'New window'}"
								name="menu.target" /></td>

						</tr>

						<tr>

							<td width="25%" colspan="1" height="22">Menu ToolTip Message<font
								color="red">*</font>:</td>
							<td colspan="3"><s:textfield maxlength="50" size="30"
								theme="simple" name="menu.message" /></td>
						</tr>
						<tr>

							<td width="25%" colspan="1" height="22">Menu Placement:</td>
							<td colspan="3"><s:textfield maxlength="50" size="40"
								theme="simple" name="menu.placement"
								onkeypress="return numbersOnly();" /></td>
						</tr>
						<tr>

							<td width="25%" colspan="1" height="22">Tab Order:</td>
							<td colspan="3"><s:textfield maxlength="50" size="40"
								theme="simple" name="menu.order"
								onkeypress="return numbersOnly();" /></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
	</table>
</s:form>

<script>
function callReport(name) {
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";						
	}
	
	
	function formValidate(){
  		var menuName =document.getElementById('paraFrm_menu_menuName').value;
  		var menuLink =document.getElementById('paraFrm_menu_menuLink').value;
  		var menuParntId =document.getElementById('paraFrm_menu_menuParntId').value;
  		var menuParValue =document.getElementById('paraFrm_menu_menuParValue').value;
  		var target =document.getElementById('paraFrm_menu_target').value;
  		var message =document.getElementById('paraFrm_menu_message').value;
  		
	 	var fieldName = ["paraFrm_menu_menuName","paraFrm_menu_menuLink","paraFrm_menu_menuParValue","paraFrm_menu_target","paraFrm_menu_message"];
		var lableName = ["Menu Name","Menu Link","Menu Parent Name","Menu Target","Menu Tooltip Message"];
		var fieldName1 = ["paraFrm_menu_menuName"];



     if(!checkMandatory(fieldName, lableName)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
  		
  			
   return true ;
  		 		
  		
  	}	
</script>