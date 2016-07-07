<%@ taglib prefix="s" uri="/struts-tags" %>

<head>

</head>
<body>
<s:form action="ExpendMaster" validate="true" id="paraFrm"
	theme="simple">
	
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
      <td width="93%" class="txt"><strong class="formhead">Expenditure </strong></td>
      <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/help.gif" width="16" height="16" /></div></td>
    </tr>
    <tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
	</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="78%">	<s:if test="%{expend.insertFlag}">	
				<s:submit cssClass="add" action="ExpendMaster_save"
 theme="simple" value="   Add New  "
					onclick="return callsave('addnew');"/>
					</s:if>
					<s:if test="%{expend.updateFlag}">	
				<s:submit cssClass="edit" action="ExpendMaster_save" theme="simple" value="   Update"
					onclick="return callsave('update');"/>
					</s:if>
					<s:if test="%{expend.viewFlag}">
					<input type="button" class="search"
							onclick="javascript:callsF9(500,325,'ExpendMaster_f9action.action');"
							value="    Search " />
					</s:if>		
				<s:submit cssClass="reset" action="ExpendMaster_reset"  theme="simple" value="    Reset"/>
			
		<s:if test="%{expend.deleteFlag}">	
		<s:submit cssClass="delete" action="ExpendMaster_delete"  theme="simple" value="   Delete  "
					onclick="return callDelete('paraFrm_expend_expCode');"/>
					</s:if>   <s:if test="%{expend.viewFlag}">
				<input type="button" class="token" onclick="callReport('ExpendMaster_report.action');" value="  Report  "/>
			</s:if></td>
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
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Expenditure</strong></td>
								<s:hidden name="expend.expCode" /> 

						</tr>
            
              <tr>
                <td width ="18%" height="22" class="formtext">Expenditure Name <font color="red">*</font> :</td>
                <td width="25%" height="22"><s:textfield name="expend.expName" size="35" maxlength="75" 
					onkeypress="return allCharacters();"></s:textfield></td>
                <td width="1%" height="22">&nbsp;</td>
                <td width="24%" class="formtext">&nbsp;</td>
                <td width="25%">&nbsp;</td>
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
 		if (!(PageNo1 == 1)) {
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
							<td class="formth" align="center">Sr No.</td>
							<td class="formth" align="center">Expenditure Name</td>
							<td  align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete"  theme="simple"
								value="     Delete  "  onclick=" return chkDelete()"/></td>


							<%int count=0; %>
							<%! int d=0; %>
							<%
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
									ondblclick="javascript:callForEdit('<s:property value="expCode" />');">


									<td width="10%" align="left"><%=++i%></td>
									<s:hidden value="%{expCode}"></s:hidden>

									<td width="90%" align="left">
                                                                       <s:property value="expName" />
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="expCode" />','<%=i%>')" /></td>
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
</body>
<script>
function callsave(type){
	var fieldName = ["paraFrm_expend_expName"];
var lableName = ["Expenditure Name"];

var fieldName1 = ["paraFrm_expend_expName"];

if(type == 'update'){
		if(document.getElementById('paraFrm_expend_expCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_expend_expCode').value==""){
			alert("Please Click Update ");
  			return false;
			}
		
	}
     if(!checkMandatory(fieldName, lableName)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      
     	    return true;

}
	
   function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="ExpendMaster_callPage.action";
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
	   document.getElementById('paraFrm').action="ExpendMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="ExpendMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="ExpendMaster_callPage.action";
	   
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
	   	document.getElementById("paraFrm").action="ExpendMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="ExpendMaster_calfordelete.action";
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
	 var con=confirm('Do You Want To  Delete The Record');
	 if(con){
	   document.getElementById('paraFrm').action="ExpendMaster_delete1.action";
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
