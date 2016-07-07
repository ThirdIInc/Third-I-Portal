<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmailConfig" validate="true" id="paraFrm"
	theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center"> Configuration
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	<!--<tr>
			<td width="100%" colspan="4" class="sectionHead">Mail Box Configuration
			 </td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="1" width="25%"> MailBox Server :</td>
			<td colspan="3" width="75%"><s:textfield size="20"
				name="mailServer" /></td>
		</tr>
		<tr>
			<td colspan="1" width="25%">MailBox Protocol :</td>
			<td width="75%" colspan="3"><s:select name="mailProtocol" headerKey="1"
				headerValue="--Select--" cssStyle="width:125"
				list="#{'P3':'POP3','P2':'POP2','P1':'POP1'}" /></td>
		
			
		</tr>
		<tr>
			<td colspan="1" width="25%"> MailBox Port :</td>
			<td colspan="3" width="75%"><s:textfield size="20"
				name="mailPort" /></td>
		</tr>
		<tr>
			<td colspan="1" width="25%">MailBox UserName :</td>
			<td colspan="3" width="75%"><s:textfield size="20"
				name="mailUsernae" /></td>
		</tr>
		<tr>
			<td colspan="1" width="25%">MailBox Password :</td>
			<td colspan="3" width="75%"><s:textfield size="20"
				name="mailPasswd" /></td>
		</tr>
		<tr >
			<td colspan="1">&nbsp;</td>
			<td colspan="1" ><input type="button" class="pagebutton"
				name="Save" value="Save" />&nbsp;&nbsp;
				<input type="button" class="pagebutton"
				name="Save" value="Clear" /></td>
			
		</tr>

		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		--><tr>
			<td width="100%" colspan="4" class="sectionHead">Theme Setting</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="1" width="25%">Select a theme :</td>
			<td colspan="1" width="75%"><s:select name="color" 
				headerKey="1" headerValue="-- Select --" list=" #{'r':'Red','b':'Blue','o':'Orange'}" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr align="left">
			<td colspan="1">&nbsp;</td>
			<td align="left"><input type="button" class="pagebutton"
				name="Save" value="Save" /></td>
		</tr>

		
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
