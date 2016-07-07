<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CheckListMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	
	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Check
					List</strong></td>
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
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>

									<td width="15%" colspan="1" height="22"><label class="set" name="clist.type"
										id="clist.type" ondblclick="callShowDiv(this);"><%=label.get("clist.type")%></label>
										
									:<font color="red">*</font></td>
									<td width="85%" colspan="2" height="22">
									<s:hidden name="typeCheck" />
									<s:select theme="simple" name="checkType"
											headerKey="" headerValue="   --Select--      " cssStyle="width:156;z-index:5;"
											list="#{'J':'Joining CheckList','M':'Medical CheckList','T':'Transfer CheckList','I':'Interview CheckList','B':'Background Verification CheckList','P':'Prejoining CheckList'}" />
									</td>
								</tr>

								<tr>
									<td width="15%" colspan="1" height="22"><label class="set" name="clist.name"
													id="clist.name" ondblclick="callShowDiv(this);"><%=label.get("clist.name")%></label>
										
									:<font color="red">*</font></td>
									<td width="85%" colspan="2" height="22">
									<s:hidden theme="simple" name="checkCode" /> 
										<s:textfield theme="simple" name="checkName" cssStyle="width:156;"
											maxlength="50" size="25" readonly="false"
											onkeypress="return allCharacters();" />
									</td>
								</tr>

								<tr>
									<td width="15%" valign="top" colspan="1" height="22"
										nowrap="nowrap"><label class="set" name="clist.desc" id="clist.desc"
										ondblclick="callShowDiv(this);"><%=label.get("clist.desc")%></label> :</td>
									<td  height="22">
										<s:textarea theme="simple" name="desciption" readonly="false"
											rows="3" cols="27"  onkeyup="callLength('descCnt');" />									
										<img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide'
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_desciption','clist.desc','','200','200');"></td>
									<td width="55%" id='ctrlHide'>Remaining chars<s:textfield name="descCnt"
										readonly="true" size="5"></s:textfield></td>									
								</tr>
								<tr>
                                        <s:hidden name="hidStatus" />
									<td width="15%" colspan="1" height="22"><label class="set" name="clist.stat"
										id="clist.stat" ondblclick="callShowDiv(this);"><%=label.get("clist.stat")%></label>:</td>
									<td width="85%" colspan="2" height="22">
										<s:select theme="simple" name="status" value="%{status}" cssStyle="width:156;z-index:5;"
											list="#{'D':'Deactive','A':'Active'}" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

				</table></td></tr>
						
						<tr>
							<td width="75%"><jsp:include
									page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr></table></s:form>
						
						
						
		<script>	
		
		
function saveFun()
{


var f9Fields= ["paraFrm_checkType", "paraFrm_checkName"];
var fieldName = ["paraFrm_checkType", "paraFrm_checkName"];
var lableName = ["clist.type", "clist.name"];
var type = ['select','enter'];
	 if(!validateBlank(fieldName,lableName,type))
          return false;
    
   if(!f9specialchars(f9Fields))
	return false;
	
	var desc = document.getElementById("paraFrm_desciption").value;
	
	if(desc != "" && desc.length >250){
	alert("Maximum length of "+document.getElementById("clist.desc").innerHTML.toLowerCase() +" is 250 characters.");
	return false;
	}
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='CheckListMaster_save.action';
	document.getElementById('paraFrm').submit();
	return true;
}			
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CheckListMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CheckListMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CheckListMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'CheckListMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_desciption').value;
					var remain = 200 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_desciption').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_desciption').style.background = '#FFFFFF';
				
				}	
}
	
function editFun() {
		return true;
	}
</script>
						