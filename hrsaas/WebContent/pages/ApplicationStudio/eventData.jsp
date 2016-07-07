<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="EventAction" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Event
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
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

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
								<td width="21%" colspan="1" height="22">
									<label class="set" name="event" id="event" ondblclick="callShowDiv(this);"><%=label.get("event")%></label>
									:<font color="red">*</font>
								</td>
								<td>
								<s:hidden name="eventCode" />
									<s:textfield name="event" size="40"/>
								</td>
							</tr>
							<tr>
								<td width="21%" colspan="1" height="22"><label class="set"
									name="mailId" id="mailId"
									ondblclick="callShowDiv(this);"><%=label.get("mailId")%></label>
								:<font color="red">*</font></td>
								<td width="75%" colspan="2"><s:textfield
									name="mailId" maxlength="80"
									onkeypress="return allCharacters();" size="25" /></td>
							</tr>
							
						</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>
<script>


	function saveFun()
	{
		try{
			var fieldName = ["paraFrm_event","paraFrm_mailId"];
			var lableName = ["event","mailId"];
			var flag = ["select","enter"];
	     	if(!validateBlank(fieldName, lableName, flag)) {
	              return false;
	        }
	        if(!mailId =="")
		    {
				var abc=validateEmail('paraFrm_mailId');
				if(!abc){
					return false;
				}
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action = 'Event_save.action';
				document.getElementById('paraFrm').submit();
				return true;
		    }	   
		}catch(e){ alert(e);}
	}	
	function backFun()
	{
	  document.getElementById('paraFrm').target = "_self";
	  document.getElementById('paraFrm').action = 'Event_back.action';
	  document.getElementById('paraFrm').submit();
	  return true; 
	}
	
	function resetFun()
	{
	  document.getElementById('paraFrm').target = "_self";
	  document.getElementById('paraFrm').action = 'Event_reset.action';
	  document.getElementById('paraFrm').submit();
	  return true; 
	}
	
	
	function deleteFun()
	{
	  document.getElementById('paraFrm').target = "_self";
	  document.getElementById('paraFrm').action = 'Event_delete.action';
	  document.getElementById('paraFrm').submit();
	  return true; 
	}
	function editFun() {
		return true;
	}
</script>
				