<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Upload User File</title>
</head>

<body>
<center>

<s:form id="paraFrm" name='paraFrm' method="post" enctype="multipart/form-data">
	<%System.out.println("inside upload struts jsp"); 
	String path=request.getParameter("path");
	System.out.println("path=="+path); 
	path=path.replace('\\','@');
	path=path.replace('/','@');
	System.out.println("path after=="+path); 
	String field=request.getParameter("field");
	%>
	<b><font face=verdana size=2>Upload File:</font></b>
	<tr><td colspan="5"><s:file name="userImage" /></td></tr><br><br>
	
	<tr><td colspan="5" align="center" ><input type="button" value="Submit" align="center" onclick="uploadFile('<%=field%>');" /></td></tr>
<script>

extArray = new Array(".xls");     // , ".pdf",".xls",".csv",".ppt",".doc",  ".txt",".rtf");
function uploadFile(){
if(document.getElementById("paraFrm_userImage").value=="")
	{
		alert("Please Select a File");
		return false;
	}else
	{
	if(limitAttach(document.getElementById("paraFrm_userImage").value)){
	
var pathAlter='<%=path%>';
pathAlter=replaceAll(pathAlter,'@','\\');


var field='<%=field%>';
		var userImage=document.getElementById('paraFrm_userImage').value;
		document.getElementById('paraFrm').action="<%=request.getContextPath()%>/common/FileUpload_uplaodFileWithPath.action?path="+pathAlter+"&field="+field+"&userImage="+userImage;
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target = "_self";	
		}else{
		alert("Please only upload files that end in types:  " 
+ (extArray.join("  ")) + "\nPlease select a new file to upload and submit again.");
return false;
}
		}
		}
		
		function replaceAll(Source,stringToFind,stringToReplace){
			  var temp = Source;
			  var index = temp.indexOf(stringToFind);
			   while(index != -1){
			   temp = temp.replace(stringToFind,stringToReplace);
			   index = temp.indexOf(stringToFind);        }
			   return temp;
		}
		
		
		function limitAttach(file) {
		
var allowSubmit;
if (!file) return;
while (file.indexOf("\\") != -1)
file = file.slice(file.lastIndexOf("\\") + 1);
ext = file.slice(file.lastIndexOf(".")).toLowerCase();
for (var i = 0; i < extArray.length; i++) {
if (extArray[i] == ext) {

 allowSubmit = true;
  break; 
}else if(extArray[i] != ext) {
	allowSubmit = false;
  	

}

}
return allowSubmit;
}



</script>
</s:form>
</center>
</body>
</html>
