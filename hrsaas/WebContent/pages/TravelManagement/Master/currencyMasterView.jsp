<%@ taglib uri="/struts-tags" prefix="s" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="Currency" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="96%" class="txt"><strong class="text_head">Currency
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Currency</strong></td>
								<s:hidden name="curCode" /> 
						</tr>
						<tr>
						<td width="22%" colspan="1"   class="formtext"><label  class = "set" name="currencyname" id="currencyname" ondblclick="callShowDiv(this);"><%=label.get("currencyname")%></label>
						<font color="red">*</font> :</td>
			                <td colspan="3"><s:textfield name="curName" size="25"  maxlength="100" 
								onkeypress="return allCharacters();"></s:textfield></td>
			            </tr>						
			            <tr>
						<td width="22%" class="formtext"><label  class = "set" name="currencyabbr" id="currencyabbr" ondblclick="callShowDiv(this);"><%=label.get("currencyabbr")%></label>
						<font color="red">*</font> :</td>
			            <td colspan="3"><s:textfield name="curAbbr" size="25"  maxlength="3" 
								onkeypress="return allCharacters();"></s:textfield></td>
			            </tr>						

						<tr>
							<td width="22%" height="22" valign="top" class="formtext" nowrap="nowrap"><label name="currencydescription"
								class="set" id="currencydescription" ondblclick="callShowDiv(this);"><%=label.get("currencydescription")%></label> :
							</td>
							<td height="22" colspan="1"  width="17%">
								<s:textarea name="description" cols="26" rows="3" onkeyup="callLength('descCnt');"></s:textarea>
							</td>
						<td height="22" colspan="1"  valign="bottom" id='ctrlHide'><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_description','currencydescription','','2000','2000');" >
							&nbsp;Remaining chars<s:textfield name="descCnt" 
							readonly="true" size="5"></s:textfield></td>
						</tr>

						<tr>
							<td width="20%" height="22" class="formtext"><label name="currencystatus"
								class="set" id="currencystatus" ondblclick="callShowDiv(this);"><%=label.get("currencystatus")%></label> 
								<font color="red">*</font> :
							</td>
							<td>
								<s:select name="curStatus"  
									list="#{' ':'--Select--','A':'Active','D':'Deactive'}"
									cssStyle="width:151;z-index:5;" />
							</td>
							<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>
						</tr>			            
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>		
		</table>		
</s:form>
<script>

	function saveFun()
	{			 
		var fieldName = ["paraFrm_curName", "paraFrm_curAbbr","paraFrm_curStatus"];
		var lableName = ["currencyname", "currencyabbr", "currencystatus"];
		var typeName = ["enter","enter","select"];
		var fieldName1 = ["paraFrm_curName", "paraFrm_curAbbr"];
	   	if(!validateBlank(fieldName,lableName,typeName))
	   	{
	   		//alert("jsp");
	    	return false;
	     }   
	    if(!f9specialchars(fieldName1))
	    {
			return false;
	    }	
	    if(document.getElementById('paraFrm_descCnt').value < 0)
	    {
		alert('Description field accepts only 2000 characters. Please remove ' 
			+ (-1 * (document.getElementById('paraFrm_descCnt')).value) + ' characters.');
		return false;
		}
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='Currency_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}

	function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'Currency_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'Currency_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'Currency_delete.action';
			document.getElementById('paraFrm').submit();
		}		
	}

function callLength(type){ 
		
		 if(type=='descCnt'){
			
					var cmt =document.getElementById('paraFrm_description').value;
					var remain = 2000 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_description').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_description').style.background = '#FFFFFF';
				
				}
				}  
</script>