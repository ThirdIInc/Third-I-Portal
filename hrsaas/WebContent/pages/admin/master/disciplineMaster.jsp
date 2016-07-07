<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="DisciplineMaster" id="paraFrm" validate="true" theme="simple">
<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Discipline
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
	
	
	<tr>
		<td colspan="4">
  		<s:if test="dispMaster.insertFlag">
  		
  		<s:submit cssClass="add" action="DisciplineMaster_save" onclick="return callSave('addnew');" theme="simple"  value="    Add New   " />
  		</s:if>
  		<s:if test="%{updateFlag}">
					<s:submit cssClass="edit" action="DisciplineMaster_save"  theme="simple" value="   Update" 
							onclick="return callSave('update'); " />
				</s:if>
  		 <input type="button" class="search" value="    Search"
					onclick="javascript:callsF9(500,325,'DisciplineMaster_f9action.action'); " />
						
							<s:hidden  name="dispMaster.disciplineID" /> 
  		<s:submit cssClass="reset" action="DisciplineMaster_reset"  theme="simple"   value="    Reset  " onclick="resetData();"/>
  		<s:if test="dispMaster.deleteFlag">
  		<s:submit cssClass="delete" action="DisciplineMaster_delete" onclick="return callDelete('paraFrm_dispMaster_disciplineID');" theme="simple"   value="    Delete  " /> 		
  		</s:if>
  		
  		
  		<s:if test="dispMaster.viewFlag"><input type="button" class="token" onclick="return callReport('DisciplineMaster_report.action')"  theme="simple"   value="  Report " />	
		</s:if></td>
		<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
		</tr>
		
	
		
		
			</table>

			<label></label></td>
		</tr>

	
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
			
  		
		
		
		<tr>
				
		<td width="30%" colspan="1" height="22" ><label  class = "set" name="disciplinename" id="disciplinename" ondblclick="callShowDiv(this);"><%=label.get("disciplinename")%></label><font color="red">*</font>: </td>
		<td width="70%" colspan="2"   height="22">
  		<s:textfield  maxlength="70" theme="simple"  name="dispMaster.disciplineName" onkeypress="return allCharacters();"/>
  		</td>
  		</tr>
  		<tr>
  		
  		<td width="30%" colspan="1" height="22"  >
  		<label  class = "set" name="disciplinedescription" id="disciplinedescription" ondblclick="callShowDiv(this);"><%=label.get("disciplinedescription")%></label><font color="red">*</font>:  </td>
  		<td width="70%" colspan="2"   height="22">
  		<s:textfield  maxlength="100" theme="simple"  name="dispMaster.disciplineDesc" onkeypress="return allCharacters();"/>	
  		</td>
  		</tr>
  		
  		<tr>
  		
  		<td width="30%" colspan="1" height="22" ><label  class = "set" name="disciplineparent" id="disciplineparent" ondblclick="callShowDiv(this);"><%=label.get("disciplineparent")%></label> <font color="red">*</font>:</td>
  		<td width="70%" colspan="2"   height="22">
  		<s:hidden  name="dispMaster.disciplineParID" /> 
  		<s:textfield    theme="simple"  name="dispMaster.disciplineParName"  maxlength="17" /><img src="../pages/common/css/default/images/search2.gif"
									width="16" height="15" onclick="javascript:callsF9(500,325,'DisciplineMaster_f9Dispaction.action');"  />
  		</td>
  		</tr>
  		
  			</table></td></tr></table>
  			
  	  <tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">

				<tr>
					<td align="right"><b>Page:</b> <%
 	int total1 = (Integer) request.getAttribute("abc");
 	int PageNo1 = (Integer) request.getAttribute("xyz");
 %> <%
 if (!(PageNo1 == 1)) {
 %><a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> <%
 	}
 	if (total1 < 5) {
 		for (int i = 1; i <= total1; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}

 	if (total1 >= 5) {
 		for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}
 	if (!(PageNo1 == total1)) {
 %>...<a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
						href="#" onclick="callPage('<%=total1%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="on()" id="selectname">
						<%
						for (int i = 1; i <= total1; i++) {
						%>

						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
					</select></td>
				</tr>



			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center">Sr No.</td>
					
							<td class="formth" align="center">Discipline Name</td>
								<td class="formth" align="center">Discipline Desc</td>
						<td  align="right" class="formth" nowrap="nowrap">
						<s:submit	cssClass="delete" theme="simple"	value="     Delete  "    onclick="return chkDelete()"/></td>
						<%int count=0; %>
							<%!int d=0; %>
							<%
								int i = 0;
								%>
							<s:iterator value="dispArray">
							

								<tr 
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="disciplineID"/>');">


									<td width="10%" align="left"><%=++i%><s:hidden name="srNo" /></td>
									
							<s:hidden value="%{disciplineID}"></s:hidden>
									<td width="50%" align="left"><s:property
										value="disciplineName" /> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
										
										<td width="40%" align="left"><s:property
										value="disciplineDesc" /></td>
											<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="disciplineID"/>','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%d=i; %>
					</tr>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
		
	
	</table>
	
			

	
</s:form>
  	<script>
  		 function callForDelete1(id,i)
	   {
// alert('aa');
	   var flag='<%=d %>';
	// alert('id----1-----'+id);
//	alert('i----1-----'+i);
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }


 function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="DisciplineMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('Please select atleast one record');
	 return false;
	 }
	}

function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}
 
/* function callSave()
 {

		var fieldName = ["paraFrm_dispMaster_disciplineName","paraFrm_dispMaster_disciplineDesc","paraFrm_dispMaster_disciplineParID"];
		var lableName = ["Discipline Name","Discipline Description","Discipline ParentCode"];
  var fieldName1 = ["paraFrm_dispMaster_disciplineName","paraFrm_dispMaster_disciplineDesc","paraFrm_dispMaster_disciplineParID"];




     if(!checkMandatory(fieldName, lableName)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      
     	    return true;
}*/
  	
  	function resetData()
  	{
  	
  	 document.getElementById('paraFrm_show').value='1';
  	}
  	
  	function callSave(type)
 {

		var fieldName = ["paraFrm_dispMaster_disciplineName","paraFrm_dispMaster_disciplineDesc","paraFrm_dispMaster_disciplineParName"];
		var lableName = ["Discipline Name","Discipline Description","Discipline ParentName"];
		var flag = ["enter","enter","select"];
  var fieldName1 = ["paraFrm_dispMaster_disciplineName","paraFrm_dispMaster_disciplineDesc","paraFrm_dispMaster_disciplineParName"];

var parent=document.getElementById('paraFrm_dispMaster_disciplineParName').value;
if(parent=='')
{
alert('please select Discipline Parent');
}
if(type == 'update'){
		if(document.getElementById('paraFrm_dispMaster_disciplineID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_dispMaster_disciplineID').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}


     if(!checkMandatory(fieldName, lableName,flag)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      
     	    return true;
}
  	
  	/*
  	function del(){
	    if(document.getElementById("paraFrm_dispMaster_disciplineName").value==""){
    		 alert('Please Select Discipline ');
		     return false;
    	}else{
    		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			 document.getElementById('paraFrm').submit();
  			 return true;
  			}
   		} 
   return false;
}
  	function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
		
			return true;	
		else {
			myfield.focus();
		
			return false;
		}
	}
  	function call(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	document.getElementById('paraFrm').target="main";
  	}
	
	function callAdd(){
	
	var disName = document.getElementById('paraFrm_dispMaster_disciplineName').value;
		var disdesc = document.getElementById('paraFrm_dispMaster_disciplineDesc').value;
		var disId = document.getElementById('paraFrm_dispMaster_disciplineParID').value;
	
		if(disName=="")
		{
			alert ("Please Enter Discipline Name");
			return false;
		}
		
						 if(!(disName==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < disName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(disName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==disName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;	
  					}
				}
		
			
		if(disdesc==""){
			alert ("Please Enter Discipline Description");
			return false;
		}
		
		
				 if(!(disdesc==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < disdesc.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(disdesc.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==disdesc.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;	
  					}
				}
		
		if(disId==""){
			alert ("Please Enter Discipline Parent Code");
			return false;
		}
		
		
				 if(!(disId==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < disId.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(disId.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==disId.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;	
  					}
				}
		
		}*/
		
		
		 function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="DisciplineMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
   
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="DisciplineMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="DisciplineMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="DisciplineMaster_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
  	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="DisciplineMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   /* function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="DisciplineMaster_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }*/
   function callForDelete(id){
  
    var conf=confirm("Do you really want to delete this record ?");
  			if(conf) 
  			{
  				
  				document.getElementById('paraFrm_hiddencode').value=id;
  		
  				document.getElementById("paraFrm").action="DisciplineMaster_calfordelete.action";
  			
			   	document.getElementById("paraFrm").target="main";
			    document.getElementById("paraFrm").submit();
			    return true;
  			}
	  		else
	  		{
	  			
	  			 return false;
	  			 
	  		}
	  		
	   
   }
    
  	
	
   
    
  	
	
  	
  	
	
  			
</script>

  
