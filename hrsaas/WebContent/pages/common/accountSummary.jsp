<%@ taglib prefix="s" uri="/struts-tags"%>
<body>
<s:form action="AccountSummary" id="paraFrm" theme="simple" name="form">

	<table width="100%" cellspacing="2" cellpadding="1" align="center"
		class="formbg">
		<tr>
			<td colspan="2" width="50%" align="center">
			<table border="0" width="80%">
				<tr>
					<td width="50%" class="txt" colspan="3"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Activity On this account</strong></td>
				</tr>


					<tr>
					<td colspan="3">&nbsp; </td>
					</tr>
				
					<tr>
					<td colspan="3"><strong>Recent Activity:</strong></td>
					</tr>
				<tr>
					<td colspan="3">&nbsp; </td>
					</tr>
			
					<tr>
					<td colspan="1"><strong>Access Type</strong> </td>
					<td colspan="1"><strong>IP Address</strong> </td>
					<td colspan="1"><strong>Date/Time</strong> </td>
					</tr>
			
			<s:iterator value="accItt">
			
			<tr >
					<td colspan="1"><s:property  value="accessType"/></td>
					<td colspan="1"><s:property  value="ipAddress"/></td>
					<td colspan="1"><s:property  value="dateTime"/></td>
					</tr>
			
			</s:iterator>
			
			
			
				<tr>
					<td colspan="3">&nbsp; </td>
					</tr>
			
			
				<tr>
					<td colspan="3">* indicate activity from the current session</td>					
				</tr>
				<tr>
					<td colspan="3">This computer is using IP address <s:property value="compIPAddress"/></td>					
				</tr>
				
			</table>
			</td>
		</tr>

	</table>


</s:form>
</body>
<script>
</script>


<script>



function callJfun()

{

document.getElementById('emailIdfg').value="";
document.getElementById('empIdfg').value="";
return true;

}

function callJfun1()
{

document.getElementById('userNamefg').value="";
document.getElementById('empIdfg').value="";
return true;
}

function callJfun2()
{

document.getElementById('emailIdfg').value="";
document.getElementById('userNamefg').value="";
return true;
}

function locate()
{
document.getElementById('paraFrm').action="Login_input.action";
document.getElementById('paraFrm').submit();

}
function submitFun()
{
try
{
var uName=document.getElementById('userNamefg').value;
var eMail=document.getElementById('emailIdfg').value;
var empId=document.getElementById('empIdfg').value;

 
   if(uName=="" && eMail=="" && empId=="")
   {
   alert("Please enter any of above field");
   return false;
   }
  
  }
  catch(e)
  {
  	//alert(e);
  }
   return true;
  
}



</script>
