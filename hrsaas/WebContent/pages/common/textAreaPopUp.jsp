 <%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="policyTravelDtl.jsp" name = "paraFrm" method="post" enctype="multipart/form-data"> 
<html>

<head> 
<%String name = request.getParameter("fieldName") ;

String windowName = request.getParameter("windowName") ;
 
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title ><%=windowName%></title>
</head>
<body>
<link rel="stylesheet" type="text/css" title="default-theme"
		href="../../pages/common/css/commonCSS.jsp" />
<table width="100%"  border="0" class="formbg" >
	  <tr>
	   <td align="left" colspan="3" >
	  	<%=windowName%>
	   </td>
	 </tr>  
	<tr>
		<td>
			 <table width="100%"  border="0" >
			 
			 <tr>
			   <td align="left" class="formtext">
			  <textarea name="popUpTextarea"  id="popUpTextarea"  readonly="true"   cols="72"   rows="20"  />
			  </textarea>
			   </td>
			 </tr>  
			 </table>
 		</td>
 	</tr>
 <input type="hidden"  value="<%=name%>" id="textName" name="textName" />
 
  	<tr>
  		<td>
		 <table width="100%">
		   <tr>
		   <td align="center" colspan="3" >
		     <input  type="button" class="token" value="   Close    " onclick="callCancel();">
		     
		   <input type="hidden" name="spaceLength" id="spaceLength" value="0" />
		   </td>
		 </tr> 
		 </table>
	</td>
	</tr>
	</table>	 
	
</body>
</html>
</form>
<script>
 callmeOnload();
  
 function callmeOnload()
	{   
		 var textFiledName = document.getElementById('textName').value; 
		 document.getElementById('popUpTextarea').value=opener.document.getElementById(textFiledName).value;
		 document.getElementById('popUpTextarea').focus();
	}
	

function callCancel()
{  
 window.close();
}



</script>