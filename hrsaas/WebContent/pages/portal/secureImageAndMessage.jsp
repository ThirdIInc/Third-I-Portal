 
<%@ taglib uri="/struts-tags" prefix="s"%>
 
 
	<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
	
<s:form id="form" name="GlodyneLogin" theme="simple" method="post">
	<%
	String pool = "";
	String user = "";
	String comanyName = "";
		try{
		
		pool = (String) request.getParameter("infoId");
		user = (String) request.getParameter("loginName");
		comanyName = (String) request.getAttribute("comanyName");
		}catch(Exception e)
		{
			
		}
	%>
	
	<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
			src="../pages/common/css/default/images/" width="193"
		height="51" /></div>
	<div class="logotext" style="float: right; width: 60%">
	<table width="80%" height="51" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="72%">
			<div align="right" class="logotext"><strong><%=comanyName%></strong></div>
			</td>
			<td width="4%">&nbsp;</td>
			<td width="24%">
			<%
				String cmpName = null;
				cmpName = (String) request.getAttribute("logo");

				System.out.println("String cmpName =" + cmpName);
				if (cmpName != null && !cmpName.equals("null")
						&& !cmpName.equals("")) {
			%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
				height="51" /> <%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
				src="../pages/common/css/default/images/"
				height="51" /> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<td valign="top" class="txt">
		<table class="formbg" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td><strong class="text_head"><img
					src="../pages/images/recruitment/review_shared.gif" width="25"
					height="25" /></strong></td>
				<td width="93%" class="txt" align="left"><strong class="text_head">Secure Authentication Image And Message </strong></td>
				<td width="3%" valign="top" class="txt">
				<div align="right"><img
					src="../pages/images/recruitment/help.gif"></div>
				</td>
			</tr>
		</table>
		</td>
		</tr>
		</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" align="left"><s:submit cssClass="token"
						action="GlodyneLogin_saveImage" theme="simple" value="Save"
						onclick="return callSave();" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">


				<tr>
					<td width="20%" colspan="1">Secure Authentication Message <font color="red">*</font>:</td>
					<td width="80%" colspan="2" align="left"><s:textfield name="userText" 
					id="userText"
					maxlength="30"/><input type="text" name="login" style="visibility: hidden;" /></td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
					<tr>
							<td width="100%" colspan="4">Select Secure Authentication Image </td>
						<tr>
				
				<tr>
					<td width="10%" colspan="1"><input type="radio" name="imgName" id="imgName0" onclick="setVal(id);"
						value="1" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/1.jpg" height="100"
						width="100" /></td>
					<td width="10%" colspan="1"><input type="radio" name="imgName"  id="imgName1"  onclick="setVal(id);"
						onclick="call();" value="2" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/2.jpg" height="100"
						width="100" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><input type="radio" name="imgName"  id="imgName2" onclick="setVal(id);"
						value="3" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/3.jpg" height="100"
						width="100" /></td>
					<td width="10%" colspan="1"><input type="radio" name="imgName"  id="imgName3" onclick="setVal(id);"
						value="4" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/4.jpg" height="100"
						width="100" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="10%" colspan="1"><input type="radio" name="imgName" id="imgName4" onclick="setVal(id);"
						value="5" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/5.jpg" height="100"
						width="100" /></td>
					<td width="10%" colspan="1"><input type="radio" name="imgName" id="imgName5"  onclick="setVal(id);"
						value="6" /></td>
					<td width="40%" colspan="1"><img
						src="../pages/common/css/default/images/6.jpg" height="100"
						width="100" /></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<s:hidden name="loginName" value="<%=user%>" />
	<s:hidden name="LoginCode" /> 
	<s:hidden name="infoId" value="<%=pool%>" />
	<s:hidden name="hiddenRadio" />
</s:form>


<script>



onload();
function onload()
{
try
{
 document.getElementById('userText').focus();
	 
}
catch(e)
{
	 
}
}
 
function callSave()
{

try
{
 
    var fields=["userText"];
    var labels=["secure authentication message"];
    var flag = ["enter"];
 	 if(!checkMandatory(fields,labels,flag))
     return false;
  
 if(document.getElementById('userText').value=="")
 {
 	alert("Please enter secure authentication message");
 	document.getElementById('userText').focus();
 	return false;
 }
 
 if(document.getElementById('form_hiddenRadio').value=="")
 {
 alert("Please select at least one image");
 	return false;
 }
 }
 catch(e)
 {
 }
 
 return true;
}


function setVal(id)
{
 if(document.getElementById(id).checked==true)
 {
 	document.getElementById('form_hiddenRadio').value="Y";
 }
}



</script>