<!--Bhushan Dasare--><!--Dec 3, 2009-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	var reqs;
	var moduleId;
</script>

<s:hidden name="moduleId" id="moduleId" />

<div id='authorizationDiv' style='position:absolute; z-index:3; width:350px; height:120px; display:none; border:2px solid; top: 120px; left: 200px; padding: 10px;' class="formbg">
	<table width="100%">
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="93%" align="center" class="formth" style="cursor:move" onmouseout="Drag.end();" 
						onmouseover="Drag.init(document.getElementById('authorizationDiv'), null, 0, 350, 0, 700);" >
							<b><label id="moduleName" style="cursor:move" /></b>
						</td>
						<td width="7%" align="center" border="1" class="formth" style="font-family:Arial; cursor: pointer" 
						onclick="hideAuthorizationDiv();">
							<b>X</b>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td><b>Please enter authorization password to unlock</b></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="23%" nowrap="nowrap">Password :</td>
						<td><input type="password" id="authPassword" size="35" maxlength="15" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="50%" align="right"><input type="submit" id="ctrlShow" value="Unlock" class="unlock" onclick="callUnlock();" /></td>
						<td><input id="ctrlShow" type="button" value="Cancel" class="cancel" onclick="hideAuthorizationDiv();"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
	function doAuthorisation(modId, moduleName, status) {
		if(status == 'U') {
			moduleId = modId;

			document.getElementById('moduleName').innerHTML = moduleName + ' Authorization';
			document.getElementById('authorizationDiv').style.display = '';
			document.getElementById('authPassword').focus();
		}
	}

	function hideAuthorizationDiv() {
		document.getElementById('authPassword').value = '';
		document.getElementById('authorizationDiv').style.display = 'none';
	}

	function callUnlock() {
		var authPassword = trim(document.getElementById('authPassword').value);
		
		if(authPassword == '') {
			alert('Password cannot be blank');
			document.getElementById('authPassword').value ="";
			document.getElementById('authPassword').focus();
		} else {
			var url = '<%=request.getContextPath()%>' + '/AppStudio/Authorization_doAuthorization.action?authPassword=' + authPassword + 
			'&moduleId=' + moduleId;
			
			retrieveAuthorisation(url);
		}
	}
	
	function retrieveAuthorisation(url) {
		try {
			url = url + '&' + Math.random();
		} catch(e) {
			alert(e);
		}
		if (window.XMLHttpRequest) { // Non-IE browsers
			reqs = new XMLHttpRequest(); // XMLHttpRequest object is created
		    reqs.onreadystatechange = processStateChangeForAuthorization;//XMLHttpRequest object is configured with a callback function
		    try {
		    	/**
		    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
		    	 * if false, send operations are synchronous, browser doesn't accept any input/output
		    	**/
		    	reqs.open("GET", url, true);
		    } catch (e) {
		      	alert("Problem Communicating with Server\n"+e);
		    }
		    reqs.send(null);
		} else if (window.ActiveXObject) { // IE 
			reqs = new ActiveXObject("Microsoft.XMLHTTP");
		    if (reqs) {
		    	reqs.onreadystatechange = processStateChangeForAuthorization;        
		      	reqs.open("GET", url, true);
		       	reqs.send();
		    }
		}
	}
	
	function processStateChangeForAuthorization() {
		// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(reqs.readyState == 4) { // Complete
			if (reqs.status == 200) { // OK response
		    	//responseXML: XML document of data returned from the server
		    	var result = reqs.responseText.split(','); // String version of data returned from the server
		    	
		    	alert(result[0]);
		    	
		    	hideAuthorizationDiv();
		    	if(result[1] == 'Y') {
		    		doUnlock();
		    	}
			}
			parent.frames[2].name = 'main';
		}
	}
</script>