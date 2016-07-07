 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ConfigAuthorization" validate="true" id="paraFrm" theme="simple"> 
<script type="text/javascript" >
var listArray = new Array();
</script>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">  
	 <tr>
		 <td colspan="7" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt" colspan="2" ><strong class="text_head">Configure Authorization </strong></td>
						<td width="3%" valign="top" class="otxt"  ><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>  
			</td>
	 </tr>  
	 
	 <tr>
						<td width="100%" colspan="7" >
						<table height="18" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="btn-middlebg">
								<div id="tabnav" style="vertical-align: bottom">
								<ul>
									<li><a href="javascript:callDivLoad('ID');" id="ID_L">
									<div align="center"><span>Module Authorization  </span></div>
									</a></li>
									<li><a href="javascript:callDivLoad('HR');" id="HR_L">
									<div align="center"><span>Misc Salary Earnings Upload Authorization</span></div>
									</a></li>
								</ul>
								</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
	 
	 
	  <tr>
		  <td colspan="7" width="100%"> <input type="button" value="Save" class="save" onclick="callSave();"> 
		   <s:hidden name="empToken" />  
		  </td>
      </tr> 
	 <tr>
	   <td  nowrap="nowrap" class="formth" ><b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
	   <td width="30%" class="formth" ><b> <label id="moduleName"  name="moduleName" ondblclick="callShowDiv(this);"><%=label.get("moduleName")%></label> </b></td>
	   <td width="20%" class="formth" ><b> <label  class = "set"  id="authTypeLabel"  name="authTypeLabel" ondblclick="callShowDiv(this);"><%=label.get("authTypeLabel")%></label></b></td>
	   <td width="45%" class="formth" colspan="4" ><b> <label  class = "set"  id="authEmp"  name="authEmp" ondblclick="callShowDiv(this);"><%=label.get("authEmp")%></label></b> </td>
 	</tr>  
	<% int i=0; int j=1;%> <s:hidden name ="rowId"  /> <s:hidden name ="rowNum"/>  <s:hidden name ="cellNum"/> 
	<s:iterator value="moduleList">
	 <tr valign="top">
	   <td width="5%" class="sortableTD" valign="top" > <%=j%> <s:hidden name="moduleId" /> <s:hidden name ="<%="empId"+i %>" />   <s:hidden name="moduleName"/>  </td>
	   <td width="30%" class="sortableTD" valign="top" > <s:property value="moduleName"/> </td>
	   <td width="20%" class="sortableTD" valign="top" >  <s:hidden name ="hiddenAuthType" id="<%="hiddenAuthType"+i%>" />
	      <select name="authType" id="<%="authType"+i %>" onchange="callSelectAuth('<%=i%>');" >
				    <option value="N" selected="selected">Not Defined</option>
				    <option value="S">Self Authorization</option>
				    <option value="M">Manager Authorization</option> 
			</select>
	     </td>
	     <script>
				 listArray[<%=i%>] = document.getElementById('hiddenAuthType'+<%=i%>).value; 
		 </script>	 
		 
		 <td width="45%" class="sortableTD"  id ="<%="authEmpRowId"+i%>">
		 <table width="100%" border="0">
		   <tr>
		      <td width="100%" id ="<%="empNameRowId"+i%>" colspan="2" >
		        <table width="100%">
		           <tr>
		            <td width="60%"> <s:textfield  name ="<%="empName"+i %>" readonly="true"  cssStyle="background-color: #F2F2F2;"  size="34"/>   </td>  
		            <td width="10%" >  <img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="16" onclick="callF9Action('<%=i%>');">    </td>
		            <td width="30%" >  <input type="button" value="Add" class="add" onclick="callAdd('<%=i%>');">  </td>
		          </tr> 
		        </table>  
	  		   </td>
	      </tr> 
	      
	     <%int k=0; 
	      String checkBlank ="";
	     %>  
		  <s:iterator value="employeeList">  
		       <tr id="<%="empRowId_"+i+"_"+k%>" class="sortableTD" >
		       <% String dispEmpName =(String) request.getAttribute("dispEmpName_"+i+"_"+k);
		         String dispEmpId =(String) request.getAttribute("dispEmpId_"+i+"_"+k);    
		       %>  
		        <td width="70%" > 
		         <s:hidden name="<%="dispEmpName_"+i+"_"+k%>" value="<%=dispEmpName%>" /> 
		           <s:hidden name="<%="dispEmpId_"+i+"_"+k%>" id="<%="dispEmpId_"+i+"_"+k%>" value="<%=dispEmpId%>"  />
		          <%=dispEmpName%>  </td>
		        <td width="30%" >  <s:hidden name="<%="hidEmployee"+i%>"   value="ffff"/> 
		        <a href="#" onclick="callRemove('<%=i%>','<%=k%>');">Remove</a> </td>
		       </tr>   
		     <%k++; checkBlank="hi";%>
		 </s:iterator>  
		 </table>
		 <%if(checkBlank.equals("")){%>
		  &nbsp; <%}%>
	  </td> 
	 </tr>   
	
	<input type="hidden"  name="<%="counter_"+i%>"  id="<%="counter_"+i%>"  value="<%=k%>" />
	 <%i++; j++;%>
	</s:iterator> 
	  <tr>
		 <td colspan="7" width="100%">  <input type="button" value="Save" class="save" onclick="callSave();"> </td>
      </tr>
      
 </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
window.onload =callOnLoad;

function callOnLoad()
{ 
  var length = listArray.length; 
  for(z=0;z<length;z++){
 
   document.getElementById('authType'+z).value = document.getElementById('hiddenAuthType'+z).value;
  
   callSelectAuth(z);
  }
  
}

function callSelectAuth(id)
{	
	
    var authType =  document.getElementById('authType'+id).value;
    var counter = document.getElementById('counter_'+id).value;
    
   if(authType=="N" || authType=="S" )
   {   
   		
   	   document.getElementById('authEmpRowId'+id).style.className='sortableTD';
   		 
        document.getElementById('empNameRowId'+id).style.display='none';
       // document.getElementById('empImageRowId'+id).style.display='none';
       // document.getElementById('empButtonRowId'+id).style.display='none'; 
	   for(i=0;i < counter;i++)
	    {
	       document.getElementById('empRowId_'+id+'_'+i).style.display='none';
	   }
     }else{
        document.getElementById('empNameRowId'+id).style.display='';
       // document.getElementById('empImageRowId'+id).style.display='';
       // document.getElementById('empButtonRowId'+id).style.display='';
        
     	 for(i=0;i < counter;i++)
	    {
	       document.getElementById('empRowId_'+id+'_'+i).style.display='';
	   }
     }
     
   
}

function callF9Action(id)
{
  var authType =  document.getElementById('authType'+id).value;
  if(authType=="N" || authType=="S" ){
   alert("Please select Authorization type as Manager Authorization");
   return false;
  }
  document.getElementById('paraFrm_rowId').value=id;
  callsF9(500,325,'ConfigAuthorization_f9Employee.action');
 }
 
 function callAdd(id)
 {  
 
 	var empName = document.getElementById('paraFrm_empName'+id).value;
 	if(empName=="")
 	{
 	  alert("Please select the employee.");
 	  return false;
 	}
 	
  	document.getElementById('paraFrm_rowNum').value=id; 
    document.getElementById('paraFrm').action="ConfigAuthorization_addEmployee.action";
   document.getElementById('paraFrm').submit();
  
   }
   
    function callRemove(rowNum ,cellNum)
 {
  	document.getElementById('paraFrm_rowNum').value=rowNum; 
  	document.getElementById('paraFrm_cellNum').value=cellNum; 
    document.getElementById('paraFrm').action="ConfigAuthorization_removeEmployee.action";
   document.getElementById('paraFrm').submit();
  
   }
   
    function callSave()
   { 
   
   	 var length = listArray.length; 
 	 for(z=0;z<length;z++)
 	  {
  		if(document.getElementById('authType'+z).value =="M")
  		  {
  		    if(document.getElementById('counter_'+z).value =="0" )
  		      {
  		      alert("Please add atleast one Authorized Employee.");
  		      return false; 
  		      }
  		   } 
  		} 
   
    document.getElementById('paraFrm').action="ConfigAuthorization_save.action";
   	document.getElementById('paraFrm').submit();
  
   }
 
 function callDivLoad(id)
{
	if(id == 'HR'){
		document.getElementById('paraFrm').action = "ConfigAuthorization_viewMiscSalaryUpoad.action";
		document.getElementById('paraFrm').target = "main";
		document.getElementById('paraFrm').submit();
	} 
	//document.getElementById('HR').style.display = 'none';
	//document.getElementById('EM').style.display = 'none';
	document.getElementById('ID_L').className='on';
	//document.getElementById('EM_L').className='li';
}
  
  onLoad();
function onLoad(){
		
		document.getElementById('ID_L').className='on';
}
</script>