<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeaveMaster"  method="post" validate="true" id="paraFrm" target="_top">
	<s:textfield label="%{getText('levCode')}" name="levMaster.leavCode" />

	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
		width="18"
		onclick="javascript:callsF9(500,325,'LeaveMaster_f9action.action');">
		
		
		<a onclick="javascript:callF9();">click me</a>

	<s:textfield label="%{getText('levName')}" name="levMaster.leavName" />
	<s:textfield label="%{getText('levDesc')}" name="levMaster.leavDesc" />
	<s:iterator value="testList" id="bean" status="stat">
		<s:property value="name" /> is the 
	    	<s:textfield name="testListid" value="%{id}" />
	</s:iterator>
	<s:submit cssClass="pagebutton" action="LeaveMaster_save" value="save" />&nbsp;
	<s:submit cssClass="pagebutton" action="" value='Reset' />&nbsp;<s:submit cssClass="pagebutton" action="" value='Report' />
</s:form>