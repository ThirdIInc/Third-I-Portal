<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TitleMaster" id="paraFrm" validate="true" theme="simple" target="main">
	<s:hidden name="show" value="%{show}" /><s:hidden name="hiddencode" /><s:hidden name="titleCode" />

	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" align="right" class="formbg">
					<tr>
						<td>
							<strong class="text_head">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Title</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><td width="100%"><table width="100%">
			<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="20%">
									<div align="right"><span class="style2"><font
										color="red">*</font></span> Indicates Required</div>
									</td>
									</table></td>
		</tr>
	<tr>
			<td colspan="3">
				<table width="100%" class="formbg">
					<tr>
						<td>	
							<table width="100%">
								<tr>
									<td width="15%" colspan="1" height="22">
										<label class="set" name="title" id="title" ondblclick="callShowDiv(this);"><%=label.get("title")%></label> :<font color="red">*</font>
									</td>
									<td width="85%" colspan="2" height="22">
										<s:textfield label="%{getText('titleName')}" theme="simple" name="titleName" maxlength="50" size="25"  />
									</td>
								</tr>
								
								
								<tr>
									<td width="20%" height="22" class="formtext"><label name="is.active"
										class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
									</td>
									<td>
										<s:select name="isActive" 
											list="#{'Y':'Yes','N':'No'}"
											cssStyle="width:151;z-index:5;" /> <!-- isActive button is added by Abhijit Samanta -->
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								
								
							</table>
						</td>
					</tr>
					</table>
					</td></tr>
					<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
					</table>
					</s:form>
			
		
<script type="text/javascript">

	function saveFun(type) {
		var fieldName = ["paraFrm_titleName"];
		var lableName = ["title"];
		var flag = ["enter"];
		var fieldName1 = ["paraFrm_titleName"];
		
	/*if(type == 'update') {
			if(document.getElementById('paraFrm_titleCode').value == "") {
				alert("Please select a record to update ");
  				return false;
			}
		} else {
			if(!document.getElementById('paraFrm_titleCode').value == "") {
				alert("Please Click on Update ");
  				return false;
			}
		}*/
     	if(!validateBlank(fieldName, lableName, flag)) {
              return false;
        }
      	if(!f9specialchars(fieldName)) {
              return false;
       	}
       	document.getElementById('paraFrm').target = "_self";
       	document.getElementById('paraFrm').action = 'TitleMaster_save.action';
		document.getElementById('paraFrm').submit();
      	return true;
	}
  	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TitleMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TitleMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	
	
	 var conf=confirm("Are you sure !\n You want to delete this record ?");
  				if(conf){
  					document.getElementById('paraFrm').target = "_self";
			      	document.getElementById('paraFrm').action = 'TitleMaster_delete.action';
					document.getElementById('paraFrm').submit();
  					return true;
  				}else{
  				return false;
  				}
		
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'TitleMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}	
	function editFun() {
		return true;
	}	
		
		
		
		</script>
		