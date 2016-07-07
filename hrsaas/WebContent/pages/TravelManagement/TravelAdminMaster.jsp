<%@taglib  prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form  action="TravelAdminMaster" id="paraFrm" target="main" validate="true" theme="simple">
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp; 
			    </td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td width="4%" colspan="1" valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25"  /></strong></td>
			<td width="93%" colspan="2" class="txt" ><strong class="formhead">Travel Admin Master</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				
					<td width="78%">
					<s:if test="%{insertFlag}"><s:submit  action="TravelAdminMaster_save" cssClass="add"   onclick="return addsave();"
						value="  Add New" /></s:if><s:if test="%{updateFlag}"> <s:submit  cssClass="edit" action="TravelAdminMaster_save" onclick="return callUpdate();"
						value="   Update" /></s:if>
						<s:if test="%{viewFlag}">
						 <input type="button" class="search"
						value="    Search"
						onclick="javascript:callsF9(500,325,'TravelAdminMaster_f9search.action');" />
						</s:if>
						<s:submit cssClass="reset" action="TravelAdminMaster_reset" 
					theme="simple" value="    Reset"  />
				<s:if test="%{deleteFlag}">	<s:submit cssClass="delete" action="TravelAdminMaster_delete"
					theme="simple" value="    Delete" onclick="return callDelete('paraFrm_travelmastercode')"  /></s:if>
				 <s:if test="%{viewFlag}">
		  			<input type="button" class="token"   onclick="callReport();"  value="  Report " />
		  		</s:if>

					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Travel Admin Master</strong><s:hidden name="travelmastercode" />  </td>
						</tr>
						<tr>
							
							
								
					<tr>
			
			
		</tr>
		<tr>
			
			<td width="10%" colspan="1"><label  class = "set" name="travel.branch" id="travel.branch" ondblclick="callShowDiv(this);"><%=label.get("travel.branch")%></label> 
			<font color="red">*</font> :</td>
			<td colspan="2" width="55%">
			<s:textfield theme="simple"	name="Branchname" size="35" readonly="true"   />
			<img src="../pages/images/search2.gif"
									class="iconImage" height="18" width="18" align="absmiddle"
									onclick="javascript:callsF9(500,325,'TravelAdminMaster_f9branch.action');" />
			<s:hidden name="Branchcode"></s:hidden>
			</td>
		</tr>
		<tr>
			
			<td width="10%" colspan="1"><label  class = "set" name="travel.empname" id="travel.empname" ondblclick="callShowDiv(this);"><%=label.get("travel.empname")%></label> 
			<font color="red">*</font> :</td>
			<td colspan="2" width="55%">
			<s:textfield theme="simple"	name="Employeename" size="35"  readonly="true"    />
			<img src="../pages/images/search2.gif"
									class="iconImage" height="18" width="18" align="absmiddle"
									onclick="javascript:callsF9(500,325,'TravelAdminMaster_f9emp.action');" />
			<s:hidden name="empid"></s:hidden><s:hidden name="empToken"></s:hidden>
			</td>
		</tr>
					</table>
					</td>
				</tr>


			</table>
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
							<td class="formth" align="center">
								<label  class = "set" name="travel.srno" id="travel.srno" ondblclick="callShowDiv(this);"><%=label.get("travel.srno")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="travel.branch" id="travel.branch1" ondblclick="callShowDiv(this);"><%=label.get("travel.branch")%></label>
							</td>
							<td class="formth" align="center">
								<label  class = "set" name="travel.empname" id="travel.empname1" ondblclick="callShowDiv(this);"><%=label.get("travel.empname")%></label>
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
									ondblclick="javascript:callForEdit('<s:property value="travelmastercode" />');">


									<td width="10%" align="left"><%=++cnt%><%++i;%></td>
									<s:hidden value="%{travelmastercode}"></s:hidden>
                                    
                                    <td width="40%" align="left">
                                                                       <s:property value="Branchname" /> 
                                                                       </td>  
									<td width="40%" align="left">
                                                                       <s:property value="Employeename" /> 
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="travelmastercode" />','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%d=i; %>
						</tr>

						
					</table>

					<%
				} catch (Exception e) {
					out.println(e);
				}
			%>
					</td>
				</tr>
			</table>
			
			
			
			</td></tr></table>


</s:form>

<script>
var fields =["paraFrm_Branchname","paraFrm_Employeename"];
	var labels =["travel.branch","travel.empname"];
	var types =["select","select"];

function addsave()
 { 
 	
 if(document.getElementById('paraFrm_travelmastercode').value!=""){
 			alert("Please click on Update");
 			return false;
 		}
	if(!validateBlank(fields,labels,types))
          return false;  	   
          		     	   	  
 			
}
 function callUpdate(){

 		if(document.getElementById('paraFrm_travelmastercode').value==""){
 			alert("Please select the record to update.");
 			return false;
 		}else {
 		
 		if(!validateBlank(fields,labels,types))
          return false;  	   
        
         return true;  		     	   	  
 		}
 		
 }


		
   function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="TravelAdminMaster_callPage.action";
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
	   document.getElementById('paraFrm').action="TravelAdminMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="TravelAdminMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="TravelAdminMaster_callPage.action";
	   
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
	   	document.getElementById("paraFrm").action="TravelAdminMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="TravelAdminMaster_calfordelete.action";
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
	 var con=confirm('Do you really want to  delete the record ?');
	 if(con){
	   document.getElementById('paraFrm').action="TravelAdminMaster_delete1.action";
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
	
	 function callReport()
	 {  
		      document.getElementById('paraFrm').target ='_blank';	 
			 document.getElementById('paraFrm').action = "TravelAdminMaster_report.action";
			 document.getElementById('paraFrm').submit();
			 document.getElementById('paraFrm').target ="";
		 
	 }

</script>