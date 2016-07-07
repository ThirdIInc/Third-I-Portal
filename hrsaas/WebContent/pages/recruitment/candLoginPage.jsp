<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="paraFrm" theme="simple" action="" method="post">
	
	
	
	<table width="100%" height="100%" border="0"  cellpadding="8" cellspacing="8"   align="center">
		<tr valign="middle">
		<td width="100%" valign="middle">
		<table width="80%" border="0" align="center" cellpadding="4" cellspacing="4"  class="formbg">
				<tr>
					<td colspan="2">
					<div id="mail">
					<table align="center">

						<tr>
							&nbsp;
						</tr>
						<tr>
							<td colspan="2" align="center">
							
							Your login details has been mailed.Please check your mail box for login details.</td>
							
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td colspan="1" align="center">
						

							&nbsp;&nbsp;&nbsp;&nbsp;<!--<s:submit cssClass="token" theme="simple"
						value="   close" onclick="closeBrowser();" /></td>-->
						</tr>
					</table>
					</div>
					</td>
					
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function callCancel()
{
alert("1");
try
{
alert("2");
 window.open('','_parent','');
alert("3");
}
catch(e)
{
alert(e);
}
window.close();

}
</script>