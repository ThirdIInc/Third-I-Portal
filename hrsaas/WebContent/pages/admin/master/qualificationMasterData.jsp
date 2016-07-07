<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="QualificationMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="editFlag" />

	<table class="formbg" width="100%" align="right">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Qualification</strong></td>
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
					</table>
					<label></label></td>
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

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="20%" height="22" class="formtext"><label name="qual.name"
										class="set" id="qual.name" ondblclick="callShowDiv(this);"><%=label.get("qual.name")%></label>
							  :<font color="red">*</font></td>
									<td width="34%" height="22"><s:hidden name="qualificationID" />
									<s:textfield name="quaName" theme="simple" size="26"
											maxlength="50" readonly="false" onkeypress="return allCharacters();"
											 /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="20%" height="22" class="formtext" nowrap="nowrap"><label name="qual.abbr"
										class="set" id="qual.abbr" ondblclick="callShowDiv(this);"><%=label.get("qual.abbr")%></label>
									   :<font color="red">*</font></td>
									<td height="22">
										<s:textfield name="quaAbbr" theme="simple" size="26"
											maxlength="14" readonly="false" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								<tr>
									<td height="22" class="formtext" width="20%"><label name="qual.levl"
										class="set" id="qual.levl" ondblclick="callShowDiv(this);"><%=label.get("qual.levl")%></label> :
									</td>
									<td height="22"><s:hidden name="hidLevel" />
									
										<s:select name="qualevel" headerValue="--Select--" headerKey=""
											list="#{'UG':'Under Graduate','DI':'Diploma','GR':'Graduate','PG':'Post Graduate','PH':'Phd','DO':'Doctorate'}"
											cssStyle="width:157;z-index:5;" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td valign="top" height="22" class="formtext" width="20%"><label name="qual.desc"
										class="set" id="qual.desc" ondblclick="callShowDiv(this);"><%=label.get("qual.desc")%></label> :
									</td>
									<td height="22" colspan="1">
										<s:textarea name="desciption" cols="27" rows="3"
											readonly="false" onkeyup="callLength('descCnt');"  />
										<img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide'
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_desciption','qual.desc','','300','300');">											
									</td>
									<td  id='ctrlHide' colspan="2">Remaining chars<s:textfield name="descCnt"
										readonly="true" size="5"></s:textfield></td>																		
								</tr>

								<tr>
									<td height="22" class="formtext" width="20%"><label name="qual.isactive"
										class="set" id="qual.isactive" ondblclick="callShowDiv(this);"><%=label.get("qual.isactive")%></label>:
									</td>
									<td height="22">
									<s:hidden name="hidIsactive" />
										<s:select name="isactive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:156;z-index:5;" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								
			</table>
			</td>
			</tr>
			</table>
				</td></tr>
		</table></td>
		</tr>
		<tr>
									<td width="100%"><jsp:include
										page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
			</table>
		</s:form>

		
		<script>
		
		

var f9Fields= ["paraFrm_quaName","paraFrm_quaAbbr"];
var fieldName = ["paraFrm_quaName","paraFrm_quaAbbr"];
var fieldName1 = ["paraFrm_quaName"];
var lableName = ["qual.name","qual.abbr"];
var type = ['enter','enter'];	
		
function saveFun()
{
	if(!(validateBlank(fieldName,lableName,type))){
			return false;
        }
        
         if(!f9specialchars(fieldName1))
      {
              return false;
       }
    
	var desc =	document.getElementById('paraFrm_desciption').value;
	
	if(desc != "" && desc.length >300){
		alert("Maximum length of "+document.getElementById("qual.desc").innerHTML.toLowerCase()+" is 300 characters.");
		return false;
    }    

	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="QualificationMaster_save.action";
		document.getElementById('paraFrm').submit();
	
}
	
	
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'QualificationMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QualificationMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QualificationMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'QualificationMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
		function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_desciption').value;
					var remain = 300 - eval(cmt.length);
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