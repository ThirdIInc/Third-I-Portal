<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type">
<title>Login</title>
<link type="text/css" rel="stylesheet"
	href="../pages/kiosk/images/skin.css">
	<style >
.btnClass {
	width: 52px;
	height: 40px;
	background-color: #E2EEFC;
	color: #003366;
	font: bold 20px Arial
}
</style>
</head>
<body>
<s:form onsubmit="return callSubmit();" action="KioskLogin" method="post" id="paraFrm" name="form">
	<table width="99%" border="0">
		<tbody>
			<tr>
				<th width="100%" scope="row" colspan="3">&nbsp;</th>
			</tr>
			<tr>
				<th scope="col" width="20%" >&nbsp;</th>
				<th scope="col" width="60%" ><img height="128" width="116"
					src="../pages/kiosk/images/umc.gif"></th>
				<th width="20%">&nbsp;</th>
			</tr>
			<tr>
				<th width="20%">&nbsp;</th>
				<th scope="col" width="60%">
				<table height="240" width="425" cellspacing="0" cellpadding="0"
					border="0" id="Table_01" align="center" bordercolor="blue">
					<tbody>
						<tr>
							<td><img height="22" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_01.gif"></td>
							<td><img height="22" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_02.gif"></td>
							<td><img height="22" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_03.gif"></td>
						</tr>
						<tr>
							<td><img height="187" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_04.gif"></td>
							<td valign="top"
								background="../pages/kiosk/images/Untitled-1_05.gif">
							<table width="200" border="0" align="center">
								<tbody>
									<tr>
										<th scope="col"><img height="57" width="169"
											src="../pages/kiosk/images/user.gif"></th>
									</tr>
									<tr>
										<th height="35" valign="bottom" scope="col" class="txt">PF
										NO :</th>
									</tr>
									<tr>
										<th height="35" scope="col"><input type="text"
											class="border" name="loginName" id="paraFrm_loginName" ></th>
									</tr>
									<tr>
										<th height="35" scope="col"><input type="button"
											value="Submit" style = "width:80px;height:30px;background-color:#003366;color:white;font: bold 15px Arial";
											onclick="return callSubmit();"></th>
									</tr>
								</tbody>
							</table>
							</td>
							<td><img height="187" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_06.gif"></td>
						</tr>
						<tr>
							<td><img height="31" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_07.gif"></td>
							<td><img height="31" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_08.gif"></td>
							<td><img height="31" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_09.gif"></td>
						</tr>
					</tbody>
				</table>
				</th>
				<td width="20%" >
				<table width="100%" border="0" bordercolor="red" style="font-size: 20px;" >
				<tr>
					<td align="center" width="100%" colspan="1"><input
						type="button"  class="btnClass" name="btn1" value=" 1 "
						onclick="setValue(this);" /> <input type="button"  class="btnClass"
						name="btn2" value=" 2 " onclick="setValue(this);" /> <input
						type="button"  class="btnClass" name="btn3" value=" 3 "
						onclick="setValue(this);" /> </td>
				</tr>
				<tr >
					<td align="center" width="100%" colspan="1"><input
						type="button"  class="btnClass" name="btn4" value=" 4 "
						onclick="setValue(this);" /> <input type="button"  class="btnClass"
						name="btn5" value=" 5 " onclick="setValue(this);" /> <input
						type="button"  class="btnClass" name="btn6" value=" 6 "
						onclick="setValue(this);" /> </td>
				</tr>
				<tr >
					<td align="center" width="100%" colspan="1"><input
						type="button"   name="btn7" value=" 7 " class="btnClass"
						onclick="setValue(this);" /> <input type="button"  class="btnClass"
						name="btn7" value=" 8 " onclick="setValue(this);" /> <input
						type="button"  class="btnClass" name="btn8" value=" 9 "
						onclick="setValue(this);" /></td>
				</tr>
				<tr>
					<td align="center" width="100%" colspan="1">
					<input type="button"  class="btnClass"
						name="btnClr" value="CLR" onclick="callClear();" /> <input
						type="button"  class="btnClass" name="btn0" value=" 0 "
						onclick="setValue(this);" /> <input
						type="button"  class="btnClass" name="deletebtn" value="<<"
						onclick="callBackSpace();" /></td>
				</tr>
				</table></td>
			</tr>
			<tr>
				<th scope="col">&nbsp;</th>
				<th scope="col"><img height="60" width="231"
					src="../pages/kiosk/images/pp.gif"></th>
					<th scope="col">&nbsp;</th>
			</tr>
			
			
					
		</tbody>
	</table>
	 	<input type="hidden" name="infoId"
		value='<%=request.getParameter("infoId") %>' />
	<s:hidden name="clientName" value="%{clientName}" />
	<s:hidden name="name" value="%{name}" />
	<s:hidden name="invalidFlag" value="%{invalidFlag}" />
	<s:hidden name="count" value="%{count}" />
	<s:hidden name="invalidCount" value="%{invalidCount}" id="invalidCount" />
	<s:hidden name="chatrooms" value="1" />
	<s:hidden name="actionMessage" />
	<s:hidden name="settingFlag" />
	
	
</s:form>
</body>
</html>
<script>
document.getElementById('paraFrm_loginName').focus();

function callSubmit() {
 	 	var name = document.getElementById('paraFrm_loginName').value;
  	 		if(name =="") {
  				alert("Please Enter PF No");  
  				document.getElementById('paraFrm_loginName').focus();
  				return false;
  			} else {
  				document.getElementById('paraFrm').action = "KioskLogin_submit.action";
  				document.getElementById('paraFrm').submit();
  			}
  			return true;
  }
  
  function setValue(obj){
	//alert("obj.value"+LTrim1(RTrim1(obj.value))+"obj.value");
	document.getElementById('paraFrm_loginName').value+=LTrim1(RTrim1(obj.value));
	}
	
	// Trims all spaces to the left of a specific string
function LTrim1(str)
{
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160, 
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(0)) != -1) {
            // We have a string with leading blank(s)...
            var j=0, i = s.length;
            // Iterate from the far left of string until we
            // don't have any more whitespace...
            while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
                j++;
            // Get the substring from the first non-whitespace
            // character to the end of the string...
            s = s.substring(j, i);
        }
        return s;
}
// Trims all spaces to the right of a specific string
function RTrim1(str)
{
        // We don't want to trip JUST spaces, but also tabs,
        // line feeds, etc.  Add anything else you want to
        // "trim" here in whitespace
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160,
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
            // We have a string with trailing blank(s)...
            var i = s.length - 1;       // Get length of string
            // Iterate from the far right of string until we
            // don't have any more whitespace...
            while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
                i--;
            // Get the substring from the front of the string to
            // where the last non-whitespace character is...
            s = s.substring(0, i+1);
        }
        return s;
}

function callClear(){

	//var str=document.getElementById('paraFrm_loginName').value;

	//str=str.substring(0,str.length-1);

	document.getElementById('paraFrm_loginName').value="";
} 

function callBackSpace(){

	var str=document.getElementById('paraFrm_loginName').value;

	str=str.substring(0,str.length-1);

	document.getElementById('paraFrm_loginName').value=str;
} 
</script>