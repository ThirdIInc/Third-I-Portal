<%try{
	 System.out.println("**********Path in upload.jsp=***********+++++");
 %>
<%@ page import = " java.io.*, java.util.*" %>


<%@page import="org.apache.commons.fileupload.*" %>

<body onBlur="window.focus()">
<%!
	public File uniqueFile(File f,String pa) {
	if(pa.equals("files/Recovery")||pa.equals("images/employee")||pa.equals("images/family"))
	{
		return f;
	}
	
	else if (f.exists()) {
			String path = f.getParentFile().getPath();
			 System.out.println("**********Path in upload.jsp=++++++++++++++++++++++++++++"+path);
			String file = "1"+f.getName();
			return uniqueFile(new File(path+"\\"+file),pa);
		} 
		return f;		
	}
%>


<%
	System.out.println("Upload 1");
	File fullFile = null;
	FileItem item= null;	
	String field = request.getParameter("field");
try {
        String path = request.getParameter("path");
       
        
        System.out.println("Upload 2 " + path);
        
        boolean isMultipart = FileUpload.isMultipartContent(request);
		if (!(isMultipart))
			out.println("not a multipart request");
		
		System.out.println("Upload 3");
		
		DiskFileUpload upload = new DiskFileUpload();
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		String msg = "";
		
		System.out.println("Upload 4");
		
		while(itr.hasNext()) {
		try {
			item = (FileItem) itr.next();
			if(item.isFormField()) {
					String fieldName = item.getFieldName();
					if(fieldName.equals("name"))
						request.setAttribute("msg", "Thank You: " + item.getString());
			} else {
				fullFile  = new File(item.getName());
				String imageName = fullFile.getName();
				imageName = imageName.toLowerCase();
					File f = new File(getServletContext().getRealPath("/")+"pages/" + path);
					f.mkdirs();
					File savedFile = new File(getServletContext().getRealPath("/")+"pages/" + path + "/",
					imageName);
					fullFile = uniqueFile(savedFile,path);
					System.out.println("*********3a"+fullFile);
					item.write(fullFile);
					msg = "file uploaded successfully";		
					
					
					
				%>
				<script>					
					<% if(!imageName.equals("")){%>
					
					window.opener.document.getElementById('<%=field%>').value="<%=fullFile.getName()%>";					
					//window.opener.document.getElementById('paraFrm').uploadFileName.value="<%=imageName%>";
					window.opener.document.getElementById('paraFrm').target="main";
					window.opener.document.getElementById('paraFrm').action.value='addFile';			
					//window.opener.document.getElementById('paraFrm').submit();							
				<%}else{%>
				alert("Please select the file");
				<%}%>	
				</script>
				<%
			}
		} catch (Exception e) {
			System.out.println("************Exception in fileUpload="+e);
			msg = "Problem occured while uploading file.<br>Please try again";
			System.out.println("************Exception in fileUpload="+msg);
			
		}
		}
//	out.println("<br><br><center><b><font face=verdana size=2>"+msg);
//	out.println("<br><br><a href='javascript:window.close();'>Close this Window</a>");
	} catch (Exception e) {System.out.println("sdfasdfadfasdfasdfasdfadfad");}
%>
<script>
	window.close();
</script>
<%}catch(Exception e)
{
System.out.println("************Error in Jsp="+e.getMessage());
}
%>
</body>

