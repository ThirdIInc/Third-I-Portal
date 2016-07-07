<!--Bhushan Dasare--><!--Apr 21, 2010-->

<%@ page import = "java.io.*, java.util.*, org.apache.commons.fileupload.*" %>

<% try { %>
	<body onBlur="window.focus()">
<%!		public File uniqueFile(File f,String pa) {
			if(pa.equals("files/Recovery") || pa.equals("images/employee") || pa.equals("images/family")) {
				return f;
			} else if (f.exists()) {
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
<%		File fullFile = null;
		FileItem item = null;	
		
		try {
	        String path = request.getParameter("path");
	        String field = request.getParameter("field");
	        
	        boolean isMultipart = FileUpload.isMultipartContent(request);
	        
			if(!(isMultipart)) {
				out.println("not a multipart request");
			}
			
			DiskFileUpload upload = new DiskFileUpload();
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			String msg = "";
			
			while(itr.hasNext()) {
				try {
					item = (FileItem) itr.next();
				
					if(item.isFormField()) {
						String fieldName = item.getFieldName();
						if(fieldName.equals("name")) {
							request.setAttribute("msg", "Thank You: " + item.getString());
						}
					} else {
						fullFile = new File(item.getName());
						String imageName = fullFile.getName();
						File f = new File(path);
						f.mkdirs();
						File savedFile = new File(path + "/", imageName);
						fullFile = uniqueFile(savedFile,path);
						item.write(fullFile);
						msg = "file uploaded successfully";		
%>						<script>					
							<% if(!imageName.equals("")){ %>
								window.opener.document.getElementById('paraFrm').<%=field%>.value="<%=fullFile.getName()%>";					
								window.opener.document.getElementById('paraFrm').target="main";
								window.opener.document.getElementById('paraFrm').action.value='addFile';			
							<% } else { %>
								alert("Please select the file");
							<%}%>
						</script>
<%					}
				} catch (Exception e) {
					e.printStackTrace();
					msg = "Problem occured while uploading file.<br>Please try again";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
%>
	
	<script>
		window.close();
	</script>

<%	} catch(Exception e) {
		e.printStackTrace();
	}
%>
</body>