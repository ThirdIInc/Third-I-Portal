<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="ModuleMaster" method="post" validate="true" id="paraFrm"
	theme="simple">
	<%@include file="/pages/common/labelManagement.jsp"%>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="moduleCode" />
	<s:hidden name="myPage" id="myPage" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Module
					Master</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>

							<td width="78%"><s:if test="%{insertFlag}">
								<s:submit action="ModuleMaster_save" cssClass="add"
									onclick="return callSave();" value="  Add New" />
							</s:if><s:if test="%{updateFlag}">
								<s:submit cssClass="edit" action="ModuleMaster_save"
									onclick="return callUpdate();" value="   Update" />
							</s:if> <s:if test="%{viewFlag}">
								<input type="button" class="search" value="    Search"
									onclick="javascript:callsF9(500,325,'ModuleMaster_f9action.action');" />
							</s:if> <s:submit cssClass="reset" action="ModuleMaster_reset" onclick="return resetfun();"
								theme="simple" value="    Reset"  /> <s:if
								test="%{deleteFlag}">
								<s:submit cssClass="delete" action="ModuleMaster_delete"
									theme="simple" value="    Delete"
									onclick="return callDelete('paraFrm_moduleCode')"
									 />
							</s:if></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>

							<td width="25%" colspan="1" height="22"><label class="set"
								name="conf1" id="conf1" ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label>
							:<font color="red">*</font></td>
							<td width="25%" colspan="1" height="22"><s:textfield
								theme="simple" name="moduleName" size="30" maxlength="50"
								onkeypress="return alphaNumeric()" />
								
								
								</td>
						</tr>
						
						<tr>

							<td width="25%" colspan="1" height="22"><label class="set"
								name="conf4" id="conf4" ondblclick="callShowDiv(this);"><%=label.get("conf4")%></label>
							:<font color="red">*</font></td>
							<td width="25%" colspan="1" height="22"><s:textfield
								theme="simple" name="moduleType" size="30" maxlength="50"
								onkeypress="return charactersOnlyWithoutSpace()" />
								
								
								</td>
						</tr>
						
						
						<tr>

							<td width="25%" colspan="1" height="22"><label class="set"
								name="conf2" id="conf2" ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label>
							:</td>
							<td width="25%" colspan="1" height="22" ><s:textarea
								theme="simple" name="description" rows="3" cols="35"
								onkeyup="callLength('description','descCnt','499');"
								 /></td>
								 <td valign="bottom">
					<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
onclick="javascript:callWindow('paraFrm_description','conf2','','paraFrm_descCnt','499');" >
		&nbsp;Remaining chars <s:textfield name="descCnt"
	readonly="true" size="5"></s:textfield></td>
								 
								 
								 
								 
								 
								 
								 
								 
								 <!-- onkeypress="return allCharacters()" -->
						</tr>
						
						<tr>

							<td width="25%" colspan="1" height="22"><label class="set"
								name="conf3" id="conf3" ondblclick="callShowDiv(this);"><%=label.get("conf3")%></label>
							:</td>
							<s:hidden name="hidAuthflag" />
							
							<td width="25%" colspan="1" height="22">
							<input type="checkbox" class="checkbox"  id="paraFrm_authChk"   name="authChk" onclick="callCheck();" />
								
								
								</td>
						</tr>
						
						
						
						
						
						
						
						
						
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right">
		<b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("abc");
 	int pageNo = (Integer) request.getAttribute("xyz");
 %> <%
 		if (pageNo != 1) {
 		if (pageNo > totalPage) {
 		} else {
 %> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous();">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (pageNo == totalPage - 1 || pageNo == totalPage) {
 			for (int z = pageNo - 2; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo > 3) {
 			for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(pageNo == totalPage)) {

 		if (totalPage == 0 || pageNo > totalPage) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="next()"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /> </a> <%
 	}
 	}
 %>	<select name="selectname" onchange="on()" id="selectname" />
							 <%
 for (int i = 1; i <= totalPage; i++) {
 %>

							<option value="<%=i%>"><%=i%></option>
							<%
							}
							%>
							</td>

						</tr></table></td></tr>


				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="sortable">

						<tr>

							<td width="100%" colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="sortable">
								<tr class="sortabletr">

									<td class="formth" align="center">Sr No.</td>
									<td class="formth" align="center">
									<label class="set"
								name="conf1" id="confIt1" ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label>
								</td>
									<td class="formth" align="center">
									<label class="set"
								name="conf4" id="confIt2" ondblclick="callShowDiv(this);"><%=label.get("conf4")%></label>
									</td>
									<td align="right" class="formth" nowrap="nowrap">
										<input type="checkbox" class="checkbox"  id="checkAll"  name="checkAll" onclick="callCheckAll();" />
										<s:submit
										cssClass="delete" theme="simple" value="     Delete  "
										onclick=" return chkDelete()" />
										
										</td>

								</tr>
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
										int cnt = pageNo * 20 - 20;

										int i = 0;
								%>
								<s:iterator value="iteratorlist">

									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="moduleCode" />');">


										<td class="sortabletd"  width="10%" align="left"><%=++cnt%> <%
 ++i;
 %> <input type="hidden" name="CK" id="CK<%=i%>" value='<s:property value="moduleCode" />' /></td>



										<td class="sortabletd" width="40%" align="left"><s:property
											value="moduleName" />&nbsp;</td>

										<td  class="sortabletd" width="40%" align="left"><s:property
											value="moduleType" /> <input type="hidden"
											name="hdeleteCode" id="hdeleteCode<%=i%>" />&nbsp;</td>
											
										<td  class="sortabletd" align="center" nowrap="nowrap"><input type="checkbox"
											class="checkbox" id="confChk<%=i%>" name="confChk"
											onclick="callForDelete1('<s:property value="moduleCode" />','<%=i%>')" /></td>
									</tr>

								</s:iterator>
								<%
								d = i;
								%>



							</table>

							<%
									} catch (Exception e) {
									e.printStackTrace();
								}
							%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>




			</td></tr></table>
</s:form>
<script>


callCheck1();

function callCheck(){

if(document.getElementById("paraFrm_authChk").checked==true)
	{ 
		document.getElementById("paraFrm_hidAuthflag").value="Y";
		}else
		document.getElementById("paraFrm_hidAuthflag").value="N";

}

function callCheck1(){

if(document.getElementById("paraFrm_hidAuthflag").value=="Y")
	{
	document.getElementById("paraFrm_authChk").checked=true;
		}else
		document.getElementById("paraFrm_authChk").checked=false;

}

function callCheckAll()
	{
	
	  var totRecords='<%=d %>';
	  if(totRecords>0)
	  {
			
			 if(document.getElementById("checkAll").checked)
			 {
			  for(var i=1;i<=totRecords;i++)
				{ 
				  document.getElementById("confChk"+i).checked=true;
				//  document.getElementById("hidConf"+i).value="Y";
				document.getElementById('hdeleteCode'+i).value=document.getElementById('CK'+i).value;
				}
				
			  }else{
			  
			 	 for(var i=1;i<=totRecords;i++)
					{ 
				  document.getElementById("confChk"+i).checked=false;
				  document.getElementById('hdeleteCode'+i).value="";
				//  document.getElementById("hidConf"+i).value="N";
					}
			 
			  }	
	}
	
	
	}








var f9Fields=["paraFrm_moduleName","paraFrm_moduleType" ];
var fieldName = ["paraFrm_moduleName","paraFrm_moduleType"];
var lableName = ['conf1','conf4'];  // ["Module Name","Module Type"];
var type = ['enter','enter'];
function callSave()
 {
		if(!document.getElementById('paraFrm_moduleCode').value==""){
 		alert("Please click on Update.");
 			return false;
 }
     	if(!validateBlank(fieldName,lableName,type))  //checkMandatory
        	return false;
       	   
    	if(!f9specialchars(f9Fields))
		return false;
		
		
		 var desc =	document.getElementById('paraFrm_description').value;
	
	if(desc != "" && desc.length >500){
		alert("Maximum length of"+document.getElementById('conf2').innerHTML.toLowerCase()+" is 499 characters.");
		return false;
    }  
		callCheck();
		
		
		
		return true;
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_moduleCode').value==""){
 			alert("Please select the record to update.");
 			return false;
 		}else {
 			if(!validateBlank(fieldName,lableName,type))
        		return false;
          	if(!f9specialchars(f9Fields))
				return false;  
				
			 var desc =	document.getElementById('paraFrm_description').value;
	
	if(desc != "" && desc.length >500){
		alert("Maximum length of"+document.getElementById('conf2').innerHTML.toLowerCase()+" is 499 characters.");
		return false;
    }  	
				
			callCheck();	
					   
     	   	   return true;
 		}
 }

	
		
   function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="ModuleMaster_callPage.action";
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
	   document.getElementById('paraFrm').action="ModuleMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="ModuleMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="ModuleMaster_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
 	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  
  	 
  	
  	}

function resetfun(){
 document.getElementById('selectname').value="";
 document.getElementById('myPage').value="";
 document.getElementById('paraFrm_show').value="";
}
	function callForEdit(id){
	 // 	document.getElementById("paraFrm").target="_blank";
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="ModuleMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	   
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="ModuleMaster_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   
   

function callForDelete1(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
   
   	function newRowColor(cell)
   	 {
			cell.className='onOverCell';

	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to delete records ?');
	 if(con){
	   document.getElementById('paraFrm').action="ModuleMaster_deleteCheckedRecords.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {     
	   var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked=false;
	     document.getElementById('hdeleteCode'+a).value="";
	 	}
	 	 document.getElementById('checkAll').checked=false;
	     return false;
	     }
	     
	 }
	 else {
	 alert('Please select atleast one record to delete.');
	 	 return false;
	 }
	 
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
   
   
</script>