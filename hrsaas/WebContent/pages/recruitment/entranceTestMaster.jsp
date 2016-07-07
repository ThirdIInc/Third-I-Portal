<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EntranceTestMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="4" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Entrance Test
			</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
	
	
	<tr>
		<td colspan="4">
			<tr >
  		<td colspan="4">
  		<s:if test="%{insertFlag}">
  		<s:submit cssClass="add"   action="EntranceTestMaster_save" theme="simple"  value="    Add New   " onclick="return saveValidate('addnew');"/>
  		</s:if>
  		<s:if test="%{updateFlag}">
					<s:submit cssClass="edit" action="EntranceTestMaster_save"  theme="simple" value="   Update" 
							onclick="return saveValidate('update'); " />
				</s:if>
				<input type="button" class="search" value="    Search"
						onclick="javascript:callsF9(500,325,'EntranceTestMaster_f9actionEntranceTest.action'); " />
						
					<s:hidden name="entrance.examCode" /> 
  		
  		<s:submit cssClass="reset"   action="EntranceTestMaster_reset" theme="simple"   value="   Reset  "  onclick="resetData();" />
  		
		<s:if test="%{deleteFlag}">	
  		<s:submit cssClass="delete"   action="EntranceTestMaster_delete" theme="simple"   value="    Delete  " onclick="return callDelete('paraFrm_entrance_examCode');"/> 		
  		</s:if><s:if test="%{viewFlag}">
  		  
 
  		<input type="button"  class="token" onclick="callReport('EntranceTestMaster_report.action')" value="  Report " />	
		</s:if>
		</td>
		
			<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
		</tr>
			
		
		<tr>
					<td height="5" colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>

			</table>

			<label></label></td>
		</tr>

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
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
				
			<td colspan="1" height="22">Exam Name <font color="red">*</font> : 
			<s:textfield size="25" name="entrance.examName" maxlength="50"  onkeypress="return allCharacters();"/> </td>
			
		</tr>
		<tr>
			<td width="50%" colspan="1" height="22">Total Marks <font color="red">*</font> :
			<s:textfield size="25" name="entrance.totalMarks"  maxlength="10"  onkeypress="return numbersWithDot();"/> </td>
			
		
			<td width="50%" colspan="1" height="22">Passing Marks <font color="red">*</font> : 
			<s:textfield size="25" name="entrance.passMarks" maxlength="10"  onkeypress="return numbersWithDot();"/> </td>
			
		</tr>
		 	</table></td></tr></table>
		 		<tr>
					<td height="5" colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>
		  <tr>
			<td colspan="3">
 <%
 	int total1 = (Integer) request.getAttribute("abc");
 	int PageNo1 = (Integer) request.getAttribute("xyz");
 %> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>	<%if(total1 >0){ %>
					<td align="right"><b>Page:</b><%
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
					</select></td><%} %>&nbsp;
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
							
							<td class="formth" align="center">Exam Name</td>
								<td class="formth" align="center">Total Marks</td>
								<td class="formth" align="center">Passing Marks</td>
						<td  align="right" class="formth" nowrap="nowrap">
						<s:submit	cssClass="delete" theme="simple"	value="     Delete  "    onclick="return chkDelete()"/></td>
						<%int count=0; %>
							<%!int d=0; %>
							<%
								int i = 0;
							int cn= PageNo1*20-20;
								%>
							<s:iterator value="distList">
							

								<tr 
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="examCode"/>');">


									<td width="10%" align="left"><%=++cn%><%++i;%><s:hidden name="srNo" /></td>
										<s:hidden value="%{examCode}"></s:hidden>
									
						
									<td width="40%" align="left"><s:property
										value="examName" /> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
										
										<td width="25%" align="left"><s:property
										value="totalMarks" /></td>
										<td width="25%" align="left"><s:property
										value="passMarks" /></td>
											<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="examCode"/>','<%=i%>')" /></td>
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
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

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
	 var con=confirm('Do you wane to  delete the record');
	 if(con){
	   document.getElementById('paraFrm').action="EntranceTestMaster_delete1.action";
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
	 	    return true;
	   }	   
	  }
	  return false;
	}
	function resetData()
  	{
  	
  	 document.getElementById('paraFrm_show').value='1';
  	}
function saveValidate(type)
{

	 
	var totMarks = document.getElementById('paraFrm_entrance_totalMarks').value;
	var passMarks = document.getElementById('paraFrm_entrance_passMarks').value;
	var fieldName = ["paraFrm_entrance_examName","paraFrm_entrance_totalMarks","paraFrm_entrance_passMarks"];
	var labelName = ["Exam Name","Total Marks","Passing Marks"];
	 var flag=["enter"];
	if(type == 'update'){
		if(document.getElementById('paraFrm_entrance_examCode').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_entrance_examCode').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}
	
	if(!checkMandatory(fieldName,labelName,flag))
	{
		return false;
	}
	if((totMarks - passMarks) < 0)
	{
   		alert("Total Marks can not be Less than Passing Marks");
   		return false;
  	}
 }
 
  function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="EntranceTestMaster_callPage.action";
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
	   document.getElementById('paraFrm').action="EntranceTestMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="EntranceTestMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="EntranceTestMaster_callPage.action";
	   
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
	   	document.getElementById("paraFrm").action="EntranceTestMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="EntranceTestMaster_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
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
    
  	
	
  	
 
</script>
