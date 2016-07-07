<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<s:form action="BirthdayMail" validate="true" id="paraFrm"
 theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="flag" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Birthday/Anniversary
					Mail </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="5%"><input type="checkbox" class="checkbox"
						id="confchk1" name="confChk" onclick="toggle('0');" /></td>

					<td width="95%">Birthday Mail</td>


					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>

				</tr>
				<tr>
					<td width="5%"><input type="checkbox" class="checkbox"
						id="confchk2" name="anniconfChk" onclick="toggle('1');" /></td>
					<td width="95%">Anniversary Mail</td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="2" width="50%" align="center"><input
						type="button" class="token" value="   Go"
						onclick="return callpage();" /></td>

					</td>
					<td colspan="2" width="50%">&nbsp;</td>

				</tr>
			</table>
			</td>
		</tr>

		</td>
		</tr>
	</table>
</s:form>

<script>
function callpage()
{
if(document.getElementById('confchk1').checked==false && document.getElementById('confchk2').checked==false)
{
alert('Please select atleast any one of the above option');
}
//alert('in');
//alert('hhh'+document.getElementById('confchk1').checked);
if(document.getElementById('confchk1').checked==true)
{
  document.getElementById('paraFrm').action="BirthdayMail_sendBirthDay.action"
  document.getElementById('paraFrm').submit();
}
	  else if(document.getElementById('confchk2').checked==true)
	    {
	    document.getElementById('paraFrm').action="BirthdayMail_sendAnniversary.action"
       document.getElementById('paraFrm').submit();
	    }
 else
  document.getElementById('paraFrm').action="BirthdayMail_defaultpage.action"
}



function toggle(type)
{
	if(type==0)
	{
		if(document.getElementById('confchk1').checked)
			document.getElementById('confchk2').checked=false;
		else
			document.getElementById('confchk2').checked=true;
	}
	else
	{
		if(document.getElementById('confchk2').checked)
			document.getElementById('confchk1').checked=false;
		else
			document.getElementById('confchk1').checked=true;
	}
}
</script>


