 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ include file="commonValidations.jsp" %>

<%@ include file="commonCSS.jsp" %>

<html>
	<body topmargin="0" leftmargin="0">
		<tiles:insertAttribute name="body" />
		<s:hidden name="actionMessage"/>
	</body>
</html>

<script>
	parent.frames[1].location=parent.frames[1].location;
	
	function callsF9(width, height, action) {
		if(navigator.appName == 'Netscape') {
			var win = window.open('', 'win', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var win = window.open('', 'win', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'win';
		document.getElementById("paraFrm").action = action;
		document.getElementById("paraFrm").submit();
		document.getElementById("paraFrm").target = 'main';
	}	
	
	function callMessage() {
		var message = document.getElementById("actionMessage").value;
		if(message !="" ){
			alert (message);
		}
	}
	
	function trim(stringToTrim) {
		return stringToTrim.replace(/^\s+|\s+$/g,"");
	}
	
	callMessage();
</script>