  <%@ taglib uri="/struts-tags" prefix="s"%>
  <%@include file="/pages/common/labelManagement.jsp" %>
  <s:form 
action="Requisition"
validate="true" id="paraFrm" theme="simple">

<s:hidden name="show"  />
<s:hidden name="hiddencode" />

	 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
    <tr>
      <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" valign="bottom" background="../pages/images/lines.gif" class="txt"><img src="../pages/images/lines.gif" width="16" height="1" /></td>
    </tr>
    <tr>
      <td colspan="3" valign="bottom" class="txt"><img src="../pages/images/space.gif" width="5" height="5" /></td>
    </tr>
    <tr>
      <td valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
      <td width="93%" class="txt"><strong class="formhead">Cash Requisition Head </strong></td>
      <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/help.gif" width="16" height="16" /></div></td>
    </tr>
    <tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
	</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit  action="Requisition_save" cssClass="add"   onclick="return callsave('addnew')"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="Requisition_save" onclick="return callsave('update')"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'Requisition_f9action.action');" />
						</s:if>
	<s:submit cssClass="reset" action="Requisition_reset"
					theme="simple" value="    Reset"  />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" action="Requisition_delete"  onclick="return callDelete('paraFrm_requis_requisitionCode')" 
					theme="simple" value="    Delete"  />
	 </s:if>
	<s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="return callReport('Requisition_report.action');" />	
	</s:if>
	    
	    </td>
	    
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   <tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
            <tr>
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Cash Requisition </strong>  </td>
						</tr>
					<tr>
			
			<td width="20%" colspan="1"><label  class = "set" name="requisition.name" id="requisition.name" ondblclick="callShowDiv(this);"><%=label.get("requisition.name")%></label>:<font color="red">*</font> </td>
			<td colspan="2" width="55%"><s:hidden name="requis.requisitionCode" /> <s:textfield theme="simple" size="30"
				name="requis.reuisitionName" maxlength="50"  onkeypress="return allCharacters();" />
			</td>
		</tr>
					
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
    
    
    <tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">

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
 		if (!(PageNo1 ==1)) {
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
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center">Sr No.
								<label  class = "set" name="requisition.srno" id="requisition.srno" ondblclick="callShowDiv(this);"><%=label.get("requisition.srno")%></label>
							</td>
							<td class="formth" align="center">Requisition Name
								<label  class = "set" name="requisition.name" id="requisition.name1" ondblclick="callShowDiv(this);"><%=label.get("requisition.name")%></label>
							</td>
							<td  align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete"  theme="simple"
								value="     Delete  "  onclick=" return chkDelete()"/></td>


							<%int count=0; %>
							<%! int d=0; %>
							<%
							int cnt= PageNo1*20-20;	
							int i = 0;
								%>
							<s:iterator value="iteratorlist">

								<tr 
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="requisitionCode" />');">


									<td width="10%" align="left"><%=++cnt%><%++i;%></td>
									<s:hidden value="%{requisitionCode}"></s:hidden>

									<td width="90%" align="left">
                                                                        <s:property value="reuisitionName" />
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="requisitionCode" />','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%d=i; %>
						</tr>

						
					</table>

					<%
				} catch (Exception e) {
				}
			%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    
    
    <tr>
      <td colspan="3"><img src="../pages/images/space.gif" width="5" height="4" /></td>
    </tr>
    
  </table>
  
	</s:form>
  


<script>
	function callsave(type)
 {
 

var fieldName = ["paraFrm_requis_reuisitionName"];
var lableName = ["requisition.name"];
var fieldName1 = ["paraFrm_requis_reuisitionName"];
var badflag = ["enter"];
if(type == 'addnew')
		{
			if(!document.getElementById('paraFrm_requis_requisitionCode').value == "")
			{
				alert("Please Click on Update");
				return false;
			}
		}
		else
		{
			if(document.getElementById('paraFrm_requis_requisitionCode').value == "")
			{
				alert("Please Select the Record to Update");
				return false;
			}
		}


     
         if(!validateBlank(fieldName,lableName,badflag))
          return false;
 	
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      
     	    return true;
}

function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="Requisition_callPage.action";
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
	   document.getElementById('paraFrm').action="Requisition_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="Requisition_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="Requisition_callPage.action";
	   
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
	   	document.getElementById("paraFrm").action="Requisition_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="Requisition_calfordelete.action";
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
	 var con=confirm('Do you really want to delete these records ?');
	 if(con){
	   document.getElementById('paraFrm').action="Requisition_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {     
	    var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked=false;
	     document.getElementById('hdeleteCode'+a).value="";
	 	}
	     return false;
	     }
	 }
	 else {
	 alert('Please Select Atleast one Record To Delete');
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
 