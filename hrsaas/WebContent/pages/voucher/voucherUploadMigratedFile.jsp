<!--Bhushan Dasare--><!--Apr 21, 2010-->

<%!	String path = null, field = null; %>

<%
	try {
		path = request.getParameter("path");
   		field = request.getParameter("field");
	} catch(Exception e) {
		e.printStackTrace();
	}
%>

<html>
	<head>
		<script type="text/javascript">
			extArray = new Array(".xls");
			
			function LimitAttach(form, file) {
				var allowSubmit;
				
				if (!file) {
					return;
				}
				
				while (file.indexOf("\\") != -1) {
					file = file.slice(file.lastIndexOf("\\") + 1);
				}
				
				ext = file.slice(file.lastIndexOf(".")).toLowerCase();

				for (var i = 0; i < extArray.length; i++) {
					if (extArray[i] == ext) {
						allowSubmit = true;
  						break;
					} else if(extArray[i] != ext) {
						allowSubmit = false;
  					}
				}
				
				if (allowSubmit){
					form.submit();
 				} else {
					alert("Please only upload files that end in types:  " + (extArray.join("  ")) + "\nPlease select a new " + 
					"file to upload and submit again.");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<center>
			<form action="upload.jsp?path=<%=path%>&field=<%=field%>" name="form" method="post" enctype="multipart/form-data">
   				<b><font face=verdana size=2>Upload File:<br><br>
      			<input type="file" name="myFile" id="myFile"/><br><br>
    			<input type="submit" name="Submit" value="Submit" onclick="return callCheck();"/>
			</form>
		</center>
	</body>
</html>

<script type="text/javascript">
	function callCheck() {
		if(document.getElementById("myFile").value == "") {
			alert("Please select a file.");
			return false;
		} else {
			LimitAttach(this.form,this.form.myFile.value);
			return false;
		}
	}
</script>