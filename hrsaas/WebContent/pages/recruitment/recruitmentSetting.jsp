<%@page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

	
<s:form action="RecruitmentSetting" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table  width="100%" align="center">
		<tr>
			<td class="pageHeader" colspan="4">
			<center>Recruitment Setting</center>
			</td>
		</tr>
		
		
		<tr align="center">
			<td colspan="4" width="100%" align="left" valign="middle" class="buttontr">
			<s:submit cssClass="pagebutton" action="RecruitmentSetting_save"
				value="  Save  " onclick="return callAdd()" /><s:submit cssClass="pagebutton"
				action="RecruitmentSetting_reset" value="  Reset  "  /> 
					
				<s:submit cssClass="pagebutton"
				action="RecruitmentSetting_delete"   value="  Delete  "
				onclick="return del()" />
			</td>
			
		</tr>
		<tr>
			<td width="100%" colspan="4"> &nbsp;</td>
		</tr>
		<tr>
		
			<td width="20%" colspan="1" >Select Recruitment :</td>
			<td colspan="2" width="55%"><s:hidden 
				theme="simple" name="recuisitonCode" /> 
					<img	src="../pages/images/search.gif" height="18" 
				width="18" onclick="javascript:callsF9(500,325,'RecruitmentSetting_f9setaction.action');"/>
			</td>
		</tr>
		
		
		<tr>
		
			<td width="20%" colspan="1" >Requirement No.<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield 
				theme="simple" name="reqNo" size="35"/><img	src="../pages/images/search.gif" height="18" 
				width="18" onclick="javascript:callsF9(800,625,'RecruitmentSetting_f9action.action');"/></td>
		</tr>
		
		<%		
		
		String audFlag=(String)request.getAttribute("data");
		String audFlag1=(String)request.getAttribute("data1");
		String audFlag2=(String)request.getAttribute("data2");
		
		
	
		%>

		<tr>
	
	
			<td >Requisition Work Flow :</td>
			<td>
				
			<input type="checkbox" <%=audFlag.equals("Y")?"checked":"N"%>    onclick="Checkme()" >
			<input type="hidden" name="reqWrkFlw"  value="<%=audFlag%>" />
			</td>
			</tr>
		
	<tr>
	
			<td >Requisition Plan Work Flow :</td>
			<td>
				<input type="checkbox" <%=audFlag1.equals("Y")?"checked":"N"%>    onclick="Checkme1()" >
			<input type="hidden" name="reqPlnFlw" value="<%=audFlag1%>" />
	</td>
		</tr>
		
		
		<tr>
		
			<td >Recruitment Schedule Work Flow :</td>
			<td>
				<input type="checkbox" <%=audFlag2.equals("Y")?"checked":"N"%>  onclick="Checkme2()" >
			<input type="hidden" name="schFlw" value="<%=audFlag2%>" />
			
			</td>
			</tr>	
	</table>
	
	
			

</s:form>

<script>


function Checkme(){

  if(document.getElementById("reqWrkFlw").value=="N"){
      document.getElementById("reqWrkFlw").value="Y";
    
       
       
  }else{
     document.getElementById("reqWrkFlw").value='N';
    
  }
 
}


function Checkme1(){

  if(document.getElementById("reqPlnFlw").value=="N"){
      document.getElementById("reqPlnFlw").value="Y";
    
       
       
  }else{
     document.getElementById("reqPlnFlw").value="N";
    
  }
 
}


function Checkme2(){

  if(document.getElementById("schFlw").value=="N"){
      document.getElementById("schFlw").value="Y";
    
       
       
  }else{
     document.getElementById("schFlw").value="N";
    
  }
 
}
</script>
