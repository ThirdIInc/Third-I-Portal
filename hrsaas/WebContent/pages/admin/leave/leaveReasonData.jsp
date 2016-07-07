<%@ taglib uri="/struts-tags" prefix="s" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="LeaveReason" validate="true" id="paraFrm" name="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Leave Reason
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
								class="forminnerhead">Leave Reason</strong></td>
								<s:hidden name="reaId" /> 
						</tr>
						<tr>
						<td width="25%" colspan="1"   class="formtext"><label  class = "set" name="leavereason" id="leavereason" ondblclick="callShowDiv(this);"><%=label.get("leavereason")%></label>
						<font color="red">*</font> :</td>
			            <td width="75%" colspan="2"><s:textarea name="reaName" cols="68"
						rows="3" onkeyup="callLength('descCnt');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle" id='ctrlHide'
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reaName','leavereason','','1000','1000');"></td>
					<td colspan="2" id='ctrlHide'>Remaining chars<s:textfield name="descCnt"
						readonly="true" size="5"></s:textfield></td>
			            </tr>
			            
			            <tr>
							<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
							</td>
							<td>
								<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
							</td>
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
		var fieldName = ["paraFrm_reaName"];
		var lableName = ["leavereason"];
		var typeName = ["enter"];
	   	if(!validateBlank(fieldName,lableName,typeName))
	    	return false;
	    
	    var cmt =document.getElementById('paraFrm_reaName').value;
		var remain = 1000 - eval(cmt.length);
		if(eval(remain) < 0) {
			alert("Please remove "+ (eval(cmt.length) - 1000)+"letters from "+document.getElementById('leavereason').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='LeaveReason_save.action';
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
  	 	document.getElementById('paraFrm').action = 'LeaveReason_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm_reaId').value = "";
		document.getElementById('paraFrm_reaName').value = "";
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LeaveReason_input.action';
		document.getElementById('paraFrm').submit();
	}
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'LeaveReason_delete.action';
			document.getElementById('paraFrm').submit();
		}
			
	}
	
	function callLength(type){ 
			
		if(type=='descCnt'){
			var cmt =document.getElementById('paraFrm_reaName').value;
			var remain = 1000 - eval(cmt.length);
			document.getElementById('paraFrm_descCnt').value = remain;
			
			if(eval(remain)< 0){
			document.getElementById('paraFrm_reaName').style.background = '#FFFF99';
			
			}else document.getElementById('paraFrm_reaName').style.background = '#FFFFFF';
		}
	} 
</script>
