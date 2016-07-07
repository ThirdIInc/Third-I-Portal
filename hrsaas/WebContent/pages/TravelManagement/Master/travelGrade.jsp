<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TravelGrade_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	
	<!-- Flagas used For Cancel Button -->
	
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2"  class="formbg" >
		
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel Grade</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- The Following code Denotes  Include JSP Page For Button Panel -->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						

						<tr>
							<td width="19%" height="22" class="formtext"><label  class = "set"  id="trv.grade" name="trv.grade" ondblclick="callShowDiv(this);"><%=label.get("trv.grade")%></label>
							<s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif> :</td>
							<td width="34%" height="22"><s:hidden name="gradeCode" />
							<s:if test="%{pageFlag}">
								<s:textfield name="gradeName" theme="simple" size="31"
									maxlength="50" readonly="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:textfield name="gradeName" theme="simple" size="31"
									maxlength="50" readonly="true" />
							</s:if> <s:elseif test="%{flag}">
								<s:textfield name="gradeName" theme="simple" size="31"
									maxlength="50" onkeypress="return allCharacters();" />
							</s:elseif><s:elseif test="%{saveFlag}">
								<s:label name="gradeName" theme="simple">

								</s:label>

							</s:elseif><s:elseif test="%{editFlag}">
								<s:textfield name="gradeName" theme="simple" size="31"
									maxlength="50" onkeypress="return allCharacters();" />
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:textfield name="gradeName" theme="simple" size="31"
									maxlength="50" onkeypress="return allCharacters();" />
							</s:elseif></td>
							<td width="27%" height="22" class="formtext">&nbsp;</td>
							<td width="18%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td valign="top" height="22" class="formtext"> <label  class = "set"  id="desc"  name="desc" ondblclick="callShowDiv(this);"><%=label.get("desc")%></label> :</td>
							<td height="22" colspan="3"><s:if test="%{pageFlag}">
								<s:textarea name="Description" cols="32" rows="4"
									readonly="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:textarea name="Description" cols="32" rows="4"
									readonly="true"></s:textarea>
							</s:if> <s:elseif test="%{flag}">
								<s:textarea name="Description" cols="32" rows="4"  onkeypress="return allCharacters();"></s:textarea>
							</s:elseif> <s:elseif test="%{saveFlag}">
								<s:label name="Description"></s:label>
							</s:elseif> <s:elseif test="%{editFlag}">
								<s:textarea name="Description" cols="32" rows="4"  onkeypress="return allCharacters();"></s:textarea>
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:textarea name="Description" cols="32" rows="4" onkeypress="return allCharacters();" ></s:textarea>
							</s:elseif></td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label  class = "set"  id="status"  name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
							<td height="22"><s:if test="%{pageFlag}">
								<s:select name="Status" disabled="false"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:select name="Status" disabled="true"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" />
							</s:if><s:elseif test="%{flag}">
								<s:select name="Status"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" />
							</s:elseif> <s:elseif test="%{saveFlag}">
								<s:label name="Status" />
							</s:elseif><s:elseif test="%{editFlag}">
								<s:select name="Status"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" />
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:select name="Status"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" />
							</s:elseif></td>
							<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>
							<td><input type="text" name="hiddenText" style="visibility: hidden;"></td>
						</tr>

						

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				
					<td width="75%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
				
								
				
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
					</select>
				
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
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead"><label  class = "set"  id="trv.list"  name="trv.list"ondblclick="callShowDiv(this);"><%=label.get("trv.list")%></label></strong></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label  class = "set"  id="sr.no"  name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td class="formth" align="center"><label  class = "set"  id="trv.grades"  name="trv.grade" ondblclick="callShowDiv(this);"><%=label.get("trv.grade")%></label></td>
							
							<td class="formth" align="center"><label  class = "set"  id="status4"  name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
							<td align="right" class="formth" ><input type="button"
								class="delete" theme="simple" value="Delete "
								onclick=" return chkDelete();" /></td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = PageNo1 * 20 - 20;
									int i = 0;
							%>
							<s:iterator value="gradeList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="gradeCode"/>');">


									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
									</td>


									<td width="40%" align="left" class="sortableTD"><s:hidden
										value="%{gradeCode}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="gradeName" /></td>
									
									<td width="40%" align="left" class="sortableTD"><s:property
										value="Status" /></td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td align="center" nowrap="nowrap" class="sortableTD" width="15%"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property  value="gradeCode"/>','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%
							d = i;
							%>
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




	</table>

</s:form>



<script>


callForFirstEdit();
function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_gradeName').focus();
}

}

var f9Fields= ["paraFrm_gradeName"];
var fieldName = ["paraFrm_gradeName"];
var lableName = ["trv.grade"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="TravelGrade_addNew.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_gradeName').focus();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="TravelGrade_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function updateFun()
{
	document.getElementById('paraFrm').action="TravelGrade_update.action";
	document.getElementById('paraFrm').submit();
}

function reportFun()
{  
     document.getElementById('paraFrm').target="_balnk";
	document.getElementById('paraFrm').action="TravelGrade_report.action";
	document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target="main";
}
function editFun()
{
	document.getElementById('paraFrm').action="TravelGrade_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="TravelGrade_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"TravelGrade_f9Action.action");
}

//For Cancel Button

function cancelFun()
{
	//alert("1pppk"+document.getElementById('paraFrm_loadFlag').value);
	//alert("2pppppp"+document.getElementById('paraFrm_addFlag').value);
	//alert("3pppppp"+document.getElementById('paraFrm_modFlag').value);
	
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in firast");
		document.getElementById('paraFrm').action="TravelGrade_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="TravelGrade_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="TravelGrade_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="TravelGrade_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}



function saveValidation(){	
  var val=document.getElementById('paraFrm_gradeName').value;
	  if(val==""){
		  alert("Please enter "+document.getElementById('trv.grade').innerHTML.toLowerCase());
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not allowed in travel grade  name!');
		 return false;
	  }
	

	
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_gradeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="TravelGrade_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_gradeName').value;
	if(val==""){
		  alert("Please enter "+document.getElementById('trv.grade').innerHTML.toLowerCase());
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not allowed in travel grade name!');
		 return false;
	}
	
   	return true;
}

function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="TravelGrade_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}

function next(){
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="TravelGrade_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="TravelGrade_callPage1.action";
		document.getElementById('paraFrm').submit();
}
   
   
function newRowColor(cell){
			cell.className='onOverCell';

}
	
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}else {
	cell.className='tableCell1';
		}
}
	
	
	
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	
	   	document.getElementById("paraFrm").action="TravelGrade_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	  }
	  else
	    document.getElementById('hdeleteCode'+i).value="";
}
  	
function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete this record ? ');
	 if(con){
	   document.getElementById('paraFrm').action="TravelGrade_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	     return false;
	     }
	     }
	 } else {
	 	alert('Please select atleast one record to delete');
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
	
function previous(){
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="TravelGrade_callPage1.action";
	   document.getElementById('paraFrm').submit();
}   

</script>

