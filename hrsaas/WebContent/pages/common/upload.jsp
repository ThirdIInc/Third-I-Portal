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
			String filename  = f.getName();
			int appender=1; 
			try{					
					appender = Integer.parseInt(filename.substring(0,1));
					appender++;
					filename = appender+filename.substring(1,filename.length());
			} catch(Exception e) {
				filename = appender+filename;
			}
			String path = f.getParentFile().getPath();
			return uniqueFile(new File(path+"\\"+filename),pa);
		}
		return f;		
	}
%>


<%
	File fullFile = null;
	FileItem item= null;	
try {
        String path = request.getParameter("path");
        String field = request.getParameter("fieldName");
        
        boolean isMultipart = FileUpload.isMultipartContent(request);
		if (!(isMultipart))
			out.println("not a multipart request");
				
		DiskFileUpload upload = new DiskFileUpload();
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		String msg = "";
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
				//imageName = imageName.();
					File f = new File(getServletContext().getRealPath("/")+"pages/" + path);
					f.mkdirs();
					File savedFile = new File(getServletContext().getRealPath("/")+"pages/" + path + "/",
					imageName);
					fullFile = uniqueFile(savedFile,path);
					item.write(fullFile);
					msg = "file uploaded successfully";		
				%>
				<script>					
					
					<% if(!imageName.equals("")){%>					
					window.opener.document.getElementById('paraFrm').<%=field%>.value="<%=fullFile.getName()%>";					
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
			e.printStackTrace();
			System.out.println("************Exception in fileUpload="+e);
			msg = "Problem occured while uploading file.<br>Please try again";			
		}
		}
	} catch (Exception e) {}
%>
<script>
	window.close();
</script>
<%}catch(Exception e)
{
System.out.println("************Error in upload Jsp="+e.getMessage());
}
%>
</body>

