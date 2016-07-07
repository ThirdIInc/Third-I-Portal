<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>DECISIONONE A GLODYNE COMPANY</title>
<META http-equiv="Page-Enter" CONTENT="RevealTrans(Duration=4,Transition=undefined)">
<link href="../pages/CR/images/skin.css" rel="stylesheet" type="text/css" />




<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-repeat: repeat-x;
	
}

</style>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>
<s:form action="CustomerReportLogin" method="post" validate="true"
	theme="simple" name="Login" target="_top" id="paraFrm">
	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />
	<%
		Object[][] listObj = null;
		String imgName, respTime = null;
		imgName = (String) request.getAttribute("imgName");
		respTime = (String) request.getAttribute("respTime");
		listObj = (Object[][]) request.getAttribute("listObj");
	%>

	<%
		String comanyName = (String) request.getAttribute("comanyName");
		String pool_name = "abc";
	%>
  <table width="909" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr>
      <td width="301" height="70" valign="middle"><img src="../pages/CR/images/logo.gif" width="324" height="57" /></td>
      <td width="608" valign="bottom"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        
        					
							<%
								String cmpName = null;
								cmpName = (String) request.getAttribute("logo");

								System.out.println("String cmpName =" + cmpName);
								if (cmpName != null && !cmpName.equals("null")
										&& !cmpName.equals("")) {
									pool_name = (String) session.getAttribute("session_pool");
							%> <input type="hidden" name="compImg" value="<%=cmpName%>" /><%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> 
							<%
							}
							%>
        
          <td height="25"><div align="right" class="apphead">Customer Reports</div></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#EC7521" height="4px"></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><table width="905" height="320" border="0" cellpadding="0" cellspacing="0" class="dotborder">
        <tr>
          <td><table width="860" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="371"><img src="../pages/CR/images/wbt.jpg" width="389" height="258" /></td>
                <td width="429"><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div align="center" class="bighead">Transforming Technology Management</div></td>
                      </tr>
                      <tr>
                        <td height="30"><div align="right"></div></td>
                      </tr>
                    </table>
                  <table width="95%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="50" class="new">Sign In
                         </td>
                      </tr>
                      <tr>
                        <td><table width="95%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td class="login"><strong>USER NAME</strong></td>
                              <td><label class="textfield">
                               <s:textfield name="loginName" size="10" cssClass="textfield" />
                               
                              </label></td>
                              <td class="login"><strong>PASSWORD</strong></td>
                              <td><span class="textfield">
                               <s:password name="loginPassword"
												cssClass="textfield"  size="10"
												maxlength="15"
												 />
                              </span></td>
                              <td><label>
                            
                                <s:submit action="CustomerReportLogin_submitnew" theme="simple" 
											cssClass="button"	value="Login" onclick="return callSubmit();" />
                                </label>
                                </a></td>
                            </tr>
                        </table></td>
                      </tr>
                      <tr>
                        <td height="18"><a href="#" class="login"></a>
                            <table width="95%" border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td width="50%" height="35" class="login"><a href="#" class="login"></a>
                                    </label></td>
                                <td width="3%" class="login">&nbsp;</td>
                                <td width="47%"><label>
                                  
                                  <span class="login"></span> </label></td>
                              </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                      </tr>
                    </table>
                </div></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" height="4px"></td>
    </tr>
    <tr>
      <td colspan="2" class="heading"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="48%" height="30"></td>
            <td>&nbsp;</td>
            <td width="48%">&nbsp;</td>
          </tr>
          <!--<tr>
            <td colspan="3" class="text">DecisionOne has a personal commitment to provide engineered services that help clients improve their business results. As a leading multi-vendor technology support provider, we offer a full-lifecycle of service solutions</td>
          </tr>
          --><tr>
            <td height="30"></td>
            <td>&nbsp;</td>
            <td><a href="aboutus.html" target="_blank" class="link"></a></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="2"><hr /></td>
    </tr>
    <tr>
      <td height="25" colspan="2"><div align="center" class="text">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="left">&reg;All Rights Reserved &copy;Copyright</div></td>
            <td><div align="right"><a href="#" class="link" onclick="MM_openBrWindow('terms.html','','width=700,height=400')">Terms &amp; Conditions</a> | <a href="#" class="link" onclick="MM_openBrWindow('privacypolicy.html','','width=700,height=400')">Privacy Policy</a></div></td>
          </tr></table>
        <div align="right"></div><table width="100%" border="0" cellspacing="0" cellpadding="0">
        </table>
        </div></td>
    </tr>
  </table>
  <%
				System.out.println("hrsaas.......fkjXrJo4FKer5PR1Q5FJ5Q=="
				+ "fort_________TIxS2uuT3RMp9WGxdbhufQ=="
				+ "Test__________7mar5Lf/k7Ap9WGxdbhufQ=="
				+ "dummy_________LYWDHQrytDA+18JM3NC3Sw=="

				+ "DUMMY1------------LYWDHQrytDDy4hrnLugp3Q=="
				+ request.getParameter("infoId"));
	%>

	<input type="hidden" name="infoId"
		value='<%=request.getParameter("infoId") %>' />
	<s:hidden name="name" value="%{name}" />
	<s:hidden name="invalidFlag" value="%{invalidFlag}" />

	<s:hidden name="count" value="%{count}" />
	<s:hidden name="invalidCount" value="%{invalidCount}" id="invalidCount" />
	<s:hidden name="chatrooms" value="1" />
	<s:hidden name="actionMessage" id="actionMessage"/>
	<s:hidden name="settingFlag" />
</s:form>
</body>
</html>
<script>
function callSubmit() {
 	try
 	{
  		var pwd = document.getElementById('paraFrm_loginPassword').value; 
  		var name = document.getElementById('paraFrm_loginName').value;
 			if(name =="") {
  			alert("Please Enter User Name");
  			document.getElementById('paraFrm_loginName').focus();  			
  			return false;  			
  		}
  		if(pwd ==""){
  			alert("Please Enter Password");
  			document.getElementById('paraFrm_loginPassword').focus(); 
  			return false;
  		} else if(trim(pwd) == ""){
  			alert("White spaces are not allowed, Please enter valid password");
  			document.getElementById('paraFrm_loginPassword').value = ""; 
  			document.getElementById('paraFrm_loginPassword').focus(); 
  			return false;
  		}
  		var lname = document.getElementById('loginName').value; 
  		document.getElementById('Login_name').value=lname ;
  		return true;
  		}
  		catch(e)
  		{
  		}
  	}
	
  	</script>
  	<script>
function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}
callMessage();
</script>
