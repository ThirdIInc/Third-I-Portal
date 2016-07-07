<%--Bhushan Dasare--%><%--Jan 2, 2009--%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<s:hidden name="enableAll" /><s:hidden id="cnt" />
<s:hidden name="insertFlag" /><s:hidden name="updateFlag" /><s:hidden name="deleteFlag" /><s:hidden name="viewFlag" />
<s:hidden name="generalFlag" /><s:hidden id="currentMode" name="currentMode" />

<%! String currentMode; %>
<%	Object[][] settingsList = (Object[][])request.getAttribute("settingsList");
	currentMode = String.valueOf(request.getAttribute("currentMode"));
	if(settingsList != null) {
%>	<table>
		<tr id="trButton">
			<td id="tdButton">
				<%	for(int i = 0; i < settingsList.length; i++) { %>
						<input type='button' name="navigationButtons" 
						id='<%=String.valueOf(settingsList[i][0]).replaceAll(" ","").toLowerCase()%>'
						value='<%=String.valueOf(settingsList[i][0])%>' 
						class='<%=String.valueOf(settingsList[i][4])%>'
						onclick="callButton('<%=String.valueOf(settingsList[i][0]).replaceAll(" ", "").toLowerCase() + "Fun"%>', 
						'<%=String.valueOf(settingsList[i][2])%>', '<%=String.valueOf(settingsList[i][3])%>');" />
				<%		out.print("");
					}
				%>
			</td>
		</tr>
	</table>
<%	}
%>

<script type="text/javascript">
	//window.onload = callCommon;
	
	
	
	function callCommon() {
	 
		document.getElementById('currentMode').value = <%=currentMode%>;
	
		var enableAll = document.getElementById('paraFrm_enableAll').value;
		
		if(enableAll == 'N') {
			disableFields();
			//document.getElementById('paraFrm_enableAll').value = 'Y';
		}
	}
	
	callCommon();
	
	function callButton(buttonName, enableAll, nextMode) {
	
		document.getElementById('paraFrm_enableAll').value = enableAll;
		var flag = false;
		
		try {
			flag = window[buttonName]();
			if(flag == undefined) {
				flag = false;
			}
		} catch(e) {
			flag = false;
		}
		if(flag) {
			if(enableAll == 'Y') {
				enableFields();
				document.getElementById('currentMode').value = nextMode;
				var menuCode= document.getElementById('paraFrm_menuCode').value ;
				getNavigationButtons('<%=request.getContextPath()%>' + '/AppStudio/NavigationPanel_getNavigationButtons.action?nextMode=' + nextMode + '&menuCode='+menuCode + '&');
			}
		}
	}
	
	function disableFields() {
		var currentMode = <%=currentMode%>;
		var cnt = 0;
				
		for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
			if(document.getElementsByTagName("*")[i].id == 'ctrlHide') {
				document.getElementsByTagName("*")[i].style.display = 'none';
			} else if((document.getElementsByTagName("*")[i].type == 'checkbox' || document.getElementsByTagName("*")[i].type == 'radio') 
			&& document.getElementsByTagName("*")[i].id != 'ctrlShow' && document.getElementsByTagName("*")[i].parentNode.id != 'ctrlShow') {
				document.getElementsByTagName("*")[i].disabled = true;
			} else if((document.getElementsByTagName("*")[i].type == 'text' || document.getElementsByTagName("*")[i].type == 'password' || document.getElementsByTagName("*")[i].type == 'textarea' 
			|| document.getElementsByTagName("*")[i].type == 'select-one' || document.getElementsByTagName("*")[i].type == 'select-multiple' || document.getElementsByTagName("*")[i].type == 'button') && document.getElementsByTagName("*")[i].name != 'navigationButtons' 
			&& document.getElementsByTagName("*")[i].id != 'ctrlShow' && document.getElementsByTagName("*")[i].name != 'label' && document.getElementsByTagName("*")[i].name != 'newLabel' 
			&& document.getElementsByTagName("*")[i].name != 'changeLabelButtons' && document.getElementsByTagName("*")[i].id != 'authPassword') {
				hideFields(document.getElementsByTagName("*")[i], cnt, currentMode);
			}
		}
		document.getElementById('cnt').value = cnt;
	}
	
	function hideFields(field, cnt, currentMode) {
		if(field.parentNode.id != 'ctrlShow') {
			if(field.type != 'button') {
				var lbl = document.createElement('label');
				lbl.id = 'lbl' + cnt;
				cnt++;
				
				if(field.type == 'select-one') {
					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									if(eval(currentMode) > 1) {
										lbl.innerHTML = field.childNodes[j].innerHTML;
									}
								}
							}
						}
					}
				} else if(field.type == 'select-multiple') {
					var recordSelected = 0;
					for(var j = 0; j < field.childNodes.length; j++) {
						if(field.childNodes[j].innerHTML != undefined) {
							if(field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == -1 || 
							field.childNodes[j].innerHTML.toLowerCase().indexOf('select') == 0) {
								if(field.childNodes[j].selected == true) {
									if(eval(currentMode) > 1) {
										recordSelected += 1;
										if(recordSelected == 1) {
											lbl.innerHTML += field.childNodes[j].innerHTML;
										} else {
											lbl.innerHTML += ', ' + field.childNodes[j].innerHTML;
										}
									}
								}
							}
						}
					}
				} else {
					lbl.innerHTML = field.value + ' ';
				}
	
				var cell = field.parentNode;
				cell.appendChild(lbl);
			}
			field.style.display = 'none';
		}
	}
	
	function enableFields() {
		for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
			if(document.getElementsByTagName("*")[i].type == 'text' || document.getElementsByTagName("*")[i].type == 'password' || document.getElementsByTagName("*")[i].type == 'textarea' || document.getElementsByTagName("*")[i].type == 'select-one'
			|| document.getElementsByTagName("*")[i].type == 'select-multiple' || document.getElementsByTagName("*")[i].type == 'button' || document.getElementsByTagName("*")[i].id == 'ctrlHide') {
				document.getElementsByTagName("*")[i].style.display = 'inline';
			}
			
			if(document.getElementsByTagName("*")[i].type == 'checkbox' || document.getElementsByTagName("*")[i].type == 'radio') {
				document.getElementsByTagName("*")[i].disabled = false;
			}
			
			if(document.getElementsByTagName("*")[i].id == 'ctrlDisabled' || document.getElementsByTagName("*")[i].parentNode.id == 'ctrlDisabled') {
				document.getElementsByTagName("*")[i].disabled = true;
			}
			
			if(document.getElementsByTagName("*")[i].id.indexOf('lbl') != -1) {
				document.getElementsByTagName("*")[i].style.display = 'none';
			}
		}
	}
	
	// Global Variables
	var req;
	
	function getNavigationButtons(url) {
	//alert(url);
		try {
			url = url + getFieldValues() + '&' + Math.random();
		} catch(e) {
			alert(e);
		}
		if (window.XMLHttpRequest) { // Non-IE browsers
			req = new XMLHttpRequest(); // XMLHttpRequest object is created
		    req.onreadystatechange = processStateChange; // XMLHttpRequest object is configured with a callback function
		    try {
		    	/**
		    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
		    	 * if false, send operations are synchronous, browser doesn't accept any input/output
		    	 */
		    	req.open("GET", url, true);
		    	
		    } catch (e) {
				alert("Problem Communicating with Server\n" + e);
			}
			req.send(null);
		} else if (window.ActiveXObject) { // IE 
			req = new ActiveXObject("Microsoft.XMLHTTP");
		    if (req) {
		    	req.onreadystatechange = processStateChange;
		      	req.open("GET", url, true);
		       	req.send();
		    }
		}
	}
	
	function getFieldValues() {
		var returnString = '';
		try {
			var formElements = document.forms['paraFrm'].elements;
			
			for (var i = 0; i < formElements.length; i++) {
				if(formElements[i].name == 'menuCode' || formElements[i].name == 'insertFlag' || formElements[i].name == 'updateFlag'|| 
	 			formElements[i].name == 'deleteFlag' || formElements[i].name == 'viewFlag' || formElements[i].name == 'generalFlag') {
	 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
	 			}
	 		}
		} catch(e) {
			alert(e);
		}
		return returnString;
	}
	
	function processStateChange() {
		// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(req.readyState == 4) { // Complete
			if (req.status == 200) { // OK response
				var response = req.responseText; // String version of data returned from the server
				setButtons(response);
		    }
			parent.frames[2].name = 'main';
		}
	}
	
	function setButtons(response) {
		var trButton;
		for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
			if(document.getElementsByTagName("*")[i].id == 'trButton') {
				trButton = document.getElementsByTagName("*")[i];
			}
			if(document.getElementsByTagName("*")[i].id == 'tdButton') {
				trButton.removeChild(document.getElementsByTagName("*")[i]);
			}
		}
		
		for (var i = 0; i < document.getElementsByTagName("*").length; i++) {
			if(document.getElementsByTagName("*")[i].id == 'trButton') {
				trButton = document.getElementsByTagName("*")[i];
				
				var buttonList = response.split('@');
				
				var tdPosition = 0;
			
				for(var j = 0; j < buttonList.length; j++) {
					var tdButton = trButton.insertCell(tdPosition);
					tdButton.id = 'tdButton';
				
					var btnProperties = buttonList[j].split(',');					
					var btnName = btnProperties[0];
					var btnClass = btnProperties[4];
					
					var button = document.createElement('input');
					button.type = 'button';
					button.name = 'navigationButtons';
					button.id = replaceAllSpaces(btnName).toLowerCase();
					button.value = btnName;
					button.className = btnClass;
					button.onclick = function() {
						getButton(buttonList, this);
					}
					
					tdButton.appendChild(button);
					tdPosition++;
				}
			}
		}
	}
	
	function getButton(buttonList, button) {
		for(var i = 0; i < buttonList.length; i++) {
			var btnProperties = buttonList[i].split(',');
			var btnName = replaceAllSpaces(btnProperties[0]);
			
			if(btnName.toLowerCase() == button.id) {
				var enableAll = btnProperties[2];
				var nextMode = btnProperties[3];
				
				var buttonName = String(button.id + 'Fun');
				callButton(buttonName, enableAll, nextMode);
				
				break;
			}
		}
	}
	
	function replaceAllSpaces(str) {
    	var index = str.indexOf(' ');
        while(index != -1) {
            str = str.replace(' ', '');
            index = str.indexOf(' ');
        }
        return str;
    }
</script>