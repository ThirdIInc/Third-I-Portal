<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="FoodType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="typeId" />
	<s:hidden name="typeName" />
	<s:hidden name="status" />
	<!-- Flagas used For Cancel Button -->
	
	<!--
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	-->
	<s:hidden name="editableflag"/>
	<s:hidden name="cancelflag"/>
	<s:hidden name="callpageflag"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		
		<!-- <tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Food Type</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td> 
		</tr>-->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Food
					Type </strong></td>
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
			
			</td>
		</tr>

		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
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
							<td height="15" class="formhead" nowrap="nowrap" colspan="3"><strong
								class="forminnerhead"><label  class = "set" name="food.list" id="food.list" ondblclick="callShowDiv(this);"><%=label.get("food.list")%></label></strong></td>
								<td align="right" class="formhead" nowrap="nowrap" width="20%"><s:submit
								cssClass="delete" theme="simple" value="    Delete"
								onclick=" return chkDelete();" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="5%"><strong><label  class = "set" name="food.no" id="food.no" ondblclick="callShowDiv(this);"><%=label.get("food.no")%></label></strong></td>
							<td class="formth" align="center" width="20%"><strong><label  class = "set" name="food.type" id="food.type1" ondblclick="callShowDiv(this);"><%=label.get("food.type")%></label></strong></td>							
							<td class="formth" align="center" width="20%"><strong><label  class = "set" name="food.sts" id="food.sts1" ondblclick="callShowDiv(this);"><%=label.get("food.sts")%></label></strong></td>
							<td  align="center" class="formth" width="15%"  class="set" nowrap="nowrap" >
							<input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						     </td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = PageNo1 * 20 - 20;
									int i = 0;
							%>
							<s:iterator value="typeList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="typeId"/>');">


									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
									<s:hidden value="%{typeId}" id='<%="typeId" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('typeId' + '<%=i%>').value;
									</script>


									<td width="40%" align="left" class="sortableTD"><s:hidden
										value="%{typeId}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="typeName" /></td>									
									<td width="40%" align="left" class="sortableTD"><s:property
										value="status" />&nbsp;</td>
									
									<td align="center" nowrap="nowrap" class="sortableTD"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property  value="typeId"/>','<%=i%>')" /></td>
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
   <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>



<script>


function textCounter(field,  maxlimit) {
		
	          // if too long...trim it!
		if (field.value.length > maxlimit){
			
			//alert('Field value should not exceed '+maxlimit+' chars.');	 
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;
			
		}
		
		return true;
		
	}

function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_typeName').focus();

}

}

var f9Fields= ["paraFrm_typeName"];
var fieldName = ["paraFrm_typeName"];
var lableName = ["food.type"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="FoodType_addNew.action";	
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_typeName').focus();
	
}

// For Save Button

function saveFun()
{var f9Fields= ["paraFrm_typeName"];
var fieldName = ["paraFrm_typeName"];
var lableName = ["food.type"];
var type = ['enter'];
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	
	
	var desc =	document.getElementById('paraFrm_description').value;
	if(desc != "" && desc.length >400){
		alert("Maximum length of "+document.getElementById('food.desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
    }    
	
	
	document.getElementById('paraFrm').action="FoodType_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}
// For Update Button

/*function updateFun()
{
	if(!checkMandatory(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="FoodType_update.action";
	document.getElementById('paraFrm').submit();
	return true;
}*/
//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="FoodType_report.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="FoodType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
	
		 var del="FoodType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"FoodType_f9Action.action");
}

//For Cancel Button

/* function cancelFun()
{
	//alert("1pppk"+document.getElementById('paraFrm_loadFlag').value);
	//alert("2pppppp"+document.getElementById('paraFrm_addFlag').value);
	//alert("3pppppp"+document.getElementById('paraFrm_modFlag').value);
	
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in first");
		document.getElementById('paraFrm').action="FoodType_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="FoodType_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");q
		document.getElementById('paraFrm').action="FoodType_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
	//alert("inside 5th");
		document.getElementById('paraFrm').action="FoodType_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}*/

function cancelFun(){
     	document.getElementById("paraFrm").action="FoodType_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }

function saveValidation(){	
  var val=document.getElementById('paraFrm_typeName').value;
	  if(val==""){
		  alert('Please enter Food Type Name');
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not Allowed in Food Type Name!');
		 return false;
	  }
	

	/* var abbr=document.getElementById('paraFrm_industryAbbr').value;
		  
	if(abbr==""){
		alert('Please enter Industry Abbr!');
		 return false;
	}
	var value = LTrim(RTrim(abbr));
	if(abbr!=value)	{
			alert('Space not Allowed in Industry Abbr.');
			return false;
	}*/
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="FoodType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_typeName').value;
	if(val==""){
		  alert('Please enter FoodType Name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not Allowed in FoodType Name!');
		 return false;
	}
	/*var abbr=document.getElementById('paraFrm_industryAbbr').value;
  
    if(abbr==""){
		  alert('Please Enter Industry Abbr.');
		  return false;
  	}
  	var value = LTrim(RTrim(abbr));
	if(abbr!=value){
		alert('Space not Allowed in Industry Abbr.');
		 return false;
	}*/
   	return true;
}

function callPage(id){
alert('hhhha');
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="FoodType_callPage2.action";
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
	   document.getElementById('paraFrm').action="FoodType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="FoodType_callPage1.action";
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
	
	   	document.getElementById("paraFrm").action="FoodType_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno=="")){
  	 document.getElementById('selectname').value=pgno;
  	}
  	callForFirstEdit();
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
	   document.getElementById('paraFrm').action="FoodType_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {
		     var flag='<%=d %>';
		      document.getElementById('checkAll').checked=false;
		     for(var a=1;a<=flag;a++)
		     	document.getElementById('confChk'+a).checked = false;
	     	 return false;
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
	   document.getElementById('paraFrm').action="FoodType_callPage1.action";
	   document.getElementById('paraFrm').submit();
} 
 	function selectAllRecords(object) {
		
		try{
		if(object.checked) {
		
			for(var i = 1; i <= records.length; i++) {
			
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
		}catch(e)
		{
		//alert(e);
		}
	}	  

</script>

