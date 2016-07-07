 <%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<form action="policyTravelDtl.jsp" name = "paraFrm" method="post" enctype="multipart/form-data"> 
<html>

<head> 
<%String name = request.getParameter("fieldName") ;  
String windowName = request.getParameter("windowName") ;
String charCntName = request.getParameter("charCntName") ; 
String maxLength = request.getParameter("maxLength") ;
String readFlag = (String)request.getParameter("readFlag") ;%> 
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
			   <%
			   if(String.valueOf(readFlag).trim().equals("readonly")){
				%>
			  <textarea name="popUpTextarea"  id="popUpTextarea"  readonly="true"   cols="72"   rows="20" onkeyup="textCounter(event,this,<%=maxLength%>)" />
			  </textarea>
			  (Max <%=maxLength%> chars)
			  <%} 
			   else{%>
			    <textarea name="popUpTextarea"  id="popUpTextarea"      cols="72"   rows="20" onkeyup="textCounter(event,this,<%=maxLength%>)"  />
			    </textarea>
			    (Max <%=maxLength%> chars)
			   <%}%>
			   </td>
			 </tr>  
			 </table>
 		</td>
 	</tr>
 <input type="hidden"  value="<%=name%>" id="textName" name="textName" />
 <input type="hidden" value="<%=charCntName%>" id="charCntName" name="charCntName" /> 
  <input type="hidden" value="<%=maxLength%>" id="maxLength" name="maxLength" />
  	<tr>
  		<td>
		 <table width="100%">
		   <tr>
		   <td align="center" colspan="3" >
		     <input  type="button" class="token" value="    OK    " onclick="callOk();">
		     <input type="button" class="token" value="Cancel" cssClass="token" onclick="callCancel();"> 
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
 
 	function textCounter(id , field,  maxlimit) {  
 	
 	 	var  spaceLength=document.getElementById('spaceLength').value; 
 	 	var totLength= eval(field.value.length)+ eval(spaceLength);
 	 try
 	  {
 		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else{ 
				key = id.which 
				if (totLength< maxlimit)
				    {
			    	 if(key==13){ 
			    	   document.getElementById('spaceLength').value=eval(spaceLength)+2;
			    	  }
				    }
			   	} // end of else
 	    }catch(e)
 	    {  }    
  
		if (totLength> maxlimit){
			field.value = field.value.substring(0, maxlimit); 
		 
			field.focus;
			return false;
		}
		return true;
	}
 
 
 
 function callmeOnload()
	{   
		 var textFiledName = document.getElementById('textName').value; 
		 document.getElementById('popUpTextarea').value=opener.document.getElementById(textFiledName).value;
		 document.getElementById('popUpTextarea').focus();
	}
	
  function callOk()
	{  
	  try{ 
	  			var spaceLength=0;
			    var textFiledName = document.getElementById('textName').value;
				var popUpTextarea = document.getElementById('popUpTextarea').value;
				 var maxLength = document.getElementById('maxLength').value;
				var textVal="";
				
				if (window.event)
				{
			    key = event.keyCode
			    textVal =popUpTextarea; 
			    }
				else
				 { 
					var charStr;
		 			for(var i =0; i < eval(popUpTextarea.length);i++)
		 			 {
		 				 charStr =popUpTextarea.substring(i,eval(i+1));
		 			   if(charStr=='\n')
		 				 { 
		 				  textVal=textVal+'  '; 
		 				 }else{
		 				  textVal=textVal+charStr;
		 				 }
		 			}  // end of for loop
	 			}  // end of else
 	 		    
 	 		 if (textVal.length> maxLength){ 
 	 		 		textVal= textVal.substring(0, (maxLength-1));  
 	 		 		textValArr=textVal.split('  ');
 	 		 		textAreaVal="";
 	 		 		for(i=0;i<textValArr.length;i++)
 	 		 		{
 	 		 		 if(i<textValArr.length-1){
 	 		 			textAreaVal=textAreaVal+textValArr[i]+'\n';
 	 		 			}else{
 	 		 			textAreaVal=textAreaVal+textValArr[i];
 	 		 			}
 	 		 		}  
			  		opener.document.getElementById(textFiledName).value=textAreaVal;
				}else{
				  opener.document.getElementById(textFiledName).value=popUpTextarea;
				} 
			  var charCntName = document.getElementById('charCntName').value; 
			  
			  if(charCntName!="undefined" || maxLength!="undefined"){ 
			  var remain =eval(maxLength) - eval(textVal.length) 
			  	if(eval(remain)< 0){
				opener.document.getElementById(textFiledName).style.background = '#FFFF99';
				}
				else{
				opener.document.getElementById(textFiledName).style.background = '#FFFFFF';
				}
			   opener.document.getElementById(charCntName).value = remain;
		  	}
	  }
	  catch(e){
	//  alert(e);
	  }
	  window.close();
	}

function callCancel()
{  
 window.close();
}



</script>