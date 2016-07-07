<!-- JSP file for front end for Answer Option Details -->

<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="QuestionBankAction" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

<s:hidden name="quesCode"/>
		<s:hidden name="show" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="categoryCode" />
		<s:hidden name="subjectCode" />
		<s:hidden name="cancelFlag" />
		<s:hidden name="editFlag" />
		<s:hidden name="hiddencode" />
		<s:hidden name="paraId" />
		<s:hidden name="optionId" />
		<s:hidden name="optionFlag" />
		<s:hidden name="subject" />
		<s:hidden name="category" />
		<s:hidden name="question" />
		<s:hidden name="descCnt" />
		<s:hidden name="ansOptions" />
		<s:hidden name="limit" />
		<s:hidden name="compLevel" />
		<s:hidden name="qsnStatus" />

		<tr>
			<td width="100%">
			<!-- <table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Answer
					Options </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>-->
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
					<input type="button" class="add"
						value=" Add " onclick="return addValidation();" />
					<input type="button" class="cancel"
						value=" Close " onclick="return callClose();" />
						
						
						</td>
					<td width="22%">
					<!-- onkeypress="return allCharacters()" <div align="right"><font color="red">*</font> Indicates
					Required</div>-->
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
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Answer Options</strong></td>
						</tr>
						<tr>
							<td width="21%" height="22" class="formtext" valign="top" nowrap="nowrap"><label
								class="set" name="ansopt" id="ansopt"
								ondblclick="callShowDiv(this);"><%=label.get("ansopt")%></label><font
								color="red">*</font>:</td>
							<td width="35%" height="22"><s:textarea theme="simple"
								name="optionTextarea" rows="4" cols="50"
								
								onkeyup="callLength('optionTextarea','descCnt1','1000');"
								readonly="false" /></td>
							<td height="22" colspan="3" valign="bottom">&nbsp;&nbsp;<img
								src="../pages/images/zoomin.gif" height="12" align="bottom"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_optionTextarea','ansopt','','paraFrm_descCnt1','1000');">
							&nbsp;Remaining chars <s:textfield name="descCnt1"
								readonly="true" size="5"></s:textfield></td>
						</tr>

						<tr>
							<td width="13%" height="22" class="formtext"><label
								class="set" name="ansflg" id="ansflg"
								ondblclick="callShowDiv(this);"><%=label.get("ansflg")%></label>
							: <s:hidden name="hiddenCode_option" id="hiddenCode_option" /></td>
							<td><s:select theme="simple" name="flag" disabled="false"
								list="#{'N':'Wrong','Y':'Right'}" /></td>
   
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr><td>
		<input type="button" class="add"
						value=" Add " onclick="return addValidation();" />
					<input type="button" class="cancel"
						value=" Close " onclick="return callClose();" /></td>
		
		</tr>


		<!-- <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						id="tblOption">
						<tr>
							<td class="formth" align="center" width="10%" nowrap="nowrap"><label
								class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);">//<//%=//label.get("serial.no")%></td>
							<td class="formth" align="center" width="35%"><label
								class="set" name="opt" id="opt" ondblclick="callShowDiv(this);">//<//%=//label.get("opt")%></td>
							<td class="formth" align="center" width="35%"><label
								class="set" name="ansflg" id="ansflg"
								ondblclick="callShowDiv(this);"><//%=//label.get("ansflg")%></td>
							<td class="formth" align="center" width="20%">&nbsp;</td>
						
							//int j = 0;
						
						</tr>
 <s:hidden name="concatEx" /> 
 <s:hidden name="concatFlg"/>
						<s:iterator value="optionList">


							<tr>

								<td width="10%" align="center">//<//%=//++j%><s:hidden
									name="srNo" /></td>

								<td width="25%"><s:property value="optionName" /><s:hidden
									name="optionName" /></td>
								<td width="10%" align="center"><s:property value="flagItt" /><s:hidden
									name="flagItt" /> <s:hidden name="optionCode" /></td>
								<input type="hidden" name="hdeleteOp<//%=j%>" id="hdeleteOp<//%=j%>" />
								<td width="10%" align="center" nowrap="nowrap"></td>
								
								
								<td width="20%" align="center" class="sortableTD" nowrap="nowrap" >
								<input type="button" class="edit" value="Edit" onclick="callforedit('<s:property value="optionName"/>','////=j','<s:property value="flagItt" />','<s:property value="optionCode"/>');" />								
								<input type="button" class="delete" value="Remove" onclick="callfordel('//////=//j%>','<s:property value="optionCode"/>');" />		
								</td>
							</tr>


						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>-->
		<%int j=0; %>
		<s:iterator value="optionList"> 
						 <%++j;%><s:hidden name="srNo" /> 
							 <s:hidden name="optionName" /> 
 					 <s:hidden name="flagItt" /> <s:hidden name="optionCode" /></td>
							<input type="hidden" name="hdeleteOp<%=j%>" id="hdeleteOp<%=j%>" /> 
		 </s:iterator> 
	</table>
</s:form>


<script>
	
  function callforedit(id,id1,id2,id3){
	
		document.getElementById("paraFrm_option").value=id;
		document.getElementById("paraFrm_paraId").value=id1;
	
		document.getElementById("paraFrm_optionId").value=id3;
		if(id2=="Right"){
			document.getElementById("paraFrm_flag").value="Y";
			//document.getElementById("paraFrm_hflag").value="Yes";
		}else{
			document.getElementById("paraFrm_flag").value="N";//=false;
			//document.getElementById("paraFrm_hflag").value='';
		
		}
		
			
	} 
   

function callfordel(id,id1){
	
	
		var conf=confirm("Do you really want to delete this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm_optionId").value=id1;
		document.getElementById("paraFrm").action="QuestionBankAction_deleteForOption.action";
	    document.getElementById("paraFrm").submit();
	    }

   }
   
function callClose(){

       // document.getElementById("paraFrm").action="QuestionBankAction_callClose.action";
	    // document.getElementById("paraFrm").submit();
	   /// window.opener.document.getElementById('paraFrm_optionString').value=document.getElementById('paraFrm_concatEx').value;
		///window.opener.document.getElementById('paraFrm_flagString').value=document.getElementById('paraFrm_concatFlg').value; 
		 window.close();

}   
     
function addValidation(){
var val=trim(document.getElementById('paraFrm_optionTextarea').value);
var finalVal;

if (val.search(/\r/g) != -1) {
   //alert('enter found');
   finalVal = val.replace(/\r/g, " ");
  // alert(finalVal);
   
  }else{
  finalVal=val;
  }


 opener.document.getElementById('paraFrm_optionTextarea').value =document.getElementById('paraFrm_optionTextarea').value;
  opener.document.getElementById('paraFrm_flag').value =document.getElementById('paraFrm_flag').value;
 
	//document.getElementById('paraFrm_concatEx').value+=document.getElementById('paraFrm_option').value +',';
	//document.getElementById('paraFrm_concatFlg').value+=document.getElementById('paraFrm_flag').value +',';
	//alert(document.getElementById('paraFrm_concatEx').value);
	//alert("222"+document.getElementById('paraFrm_concatFlg').value);

	 if(val==""){
		alert('Please enter the '+document.getElementById('ansopt').innerHTML.toLowerCase());
		return false;

	}else if(val.length > 999){
		alert('Maximum length of '+document.getElementById('ansopt').innerHTML.toLowerCase()+" is 1000 characters");
		return false;
	
	}else {
		opener.document.getElementById('paraFrm').target='main';
		opener.document.getElementById('paraFrm').action='QuestionBankAction_addOption.action'; 
		opener.document.getElementById('paraFrm').submit();
		window.close();
	}
}
	
	</script>

