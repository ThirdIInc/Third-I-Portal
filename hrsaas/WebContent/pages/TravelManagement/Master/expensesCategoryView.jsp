<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ExpensesCategory_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->




	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="onLoadFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="pageFlag" />

	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />



	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">


		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Expenses
					Category </strong></td>
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
					<s:if test="onLoadFlag">
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:if>
					<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
				</tr>
			</table>
			</td>
		</tr>


		<s:hidden name="expenseId" />
		<s:hidden name="expenseName" />
		<s:hidden name="expenseUnit" />
		<s:hidden name="description" />
		<s:hidden name="status" />





		<tr>
			<td colspan="4" width="100%"><s:if test="onLoadFlag">


				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">


					<tr>
						<td colspan="4" width="100%">

						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr>
								<td width="26%" height="22" class="formtext" colspan="1"><label
									class="set" name="expense.type" id="expense.type"
									ondblclick="callShowDiv(this);"><%=label.get("expense.type")%></label>:</td>
								<td width="50%" height="22" colspan="3"><s:property
									value="expenseName" /></td>

							</tr>



							<tr>
								<td valign="top" height="22" class="formtext" width="26%"
									colspan="1"><label class="set" name="expense.unit"
									id="expense.unit" ondblclick="callShowDiv(this);"><%=label.get("expense.unit")%></label>:

								</td>
								<td width="50%" colspan="3"><s:property value="expenseUnit" /></td>
							</tr>



							<tr>
								<td valign="top" height="22" class="formtext" width="26%"
									colspan="1"><label class="set" name="expense.desc"
									id="expense.desc" ondblclick="callShowDiv(this);"><%=label.get("expense.desc")%></label>
								:</td>
								<td colspan="3" width="50%" style="overflow: hidden;"><s:textarea
									readonly="true" cssStyle="border:none;overflow: hidden;"
									name="description" cols="70" rows="4" /> <s:hidden
									name="description" /></td>

							</tr>

							<tr>
								<td height="22" class="formtext" width="26%" colspan="1"><label
									class="set" name="expense.sts" id="expense.sts"
									ondblclick="callShowDiv(this);"><%=label.get("expense.sts")%></label>
								:</td>
								<td width="50%" colspan="3"><s:property value="status" /></td>

							</tr>


						</table>
						</td>
					</tr>
				</table>
			</s:if> <s:else>
			</s:else></td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<s:if test="onLoadFlag">
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:if>
					<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
				</tr>
			</table>
			</td>
		</tr>

<s:if test="onLoadFlag"></s:if><s:else>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<td align="right"><b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("abc");
 	int pageNo = (Integer) request.getAttribute("xyz");
 %> <%
 		if (pageNo != 1) {
 		if (pageNo > totalPage) {
 		} else {
 %> <a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P');">
					<img src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <%
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
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=totalPage%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <%
 	}
 	}
 %><select name="selectname" onchange="on()" id="selectname">
						<%
						for (int i = 1; i <= totalPage; i++) {
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
							<td height="15" class="formhead" nowrap="nowrap" colspan="4"><strong
								class="forminnerhead"><label class="set"
								name="expense.list" id="expense.list"
								ondblclick="callShowDiv(this);"><%=label.get("expense.list")%></label></strong></td>
								<td align="right" class="formhead" nowrap="nowrap"><s:submit
								cssClass="delete" theme="simple" value="   Delete"
								onclick=" return chkDelete();" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label class="set"
								name="expense.no" id="expense.no"
								ondblclick="callShowDiv(this);"><%=label.get("expense.no")%></label></td>
							<td class="formth" align="center"><label class="set"
								name="expense.type" id="expense.type1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.type")%></label></td>
							<td class="formth" align="center"><label class="set"
								name="expense.unit" id="expense.unit1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.unit")%></label></td>
							<td class="formth" align="center"><label class="set"
								name="expense.sts" id="expense.sts1"
								ondblclick="callShowDiv(this);"><%=label.get("expense.sts")%></label></td>
							<td align="right" class="formth" nowrap="nowrap"><input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						     </td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = pageNo * 20 - 20;
									int i = 0;
							%>


							<s:iterator value="expenseList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="expenseId1"/>');">

									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
										<s:hidden value="%{expenseId1}" id='<%="expenseId1" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('expenseId1' + '<%=i%>').value;
									</script>


									<td width="40%" align="left" class="sortableTD"><s:hidden
										value="%{expenseId1}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="expenseName" /></td>
									<td width="40%" align="left" class="sortableTD"><s:property
										value="expenseUnit" /></td>
									<td width="40%" align="left" class="sortableTD"><s:property
										value="status" /></td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDelete1('<s:property  value="expenseId1"/>','<%=i%>')" /></td>
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
		</tr></s:else>
		
		<s:if test="onLoadFlag"></s:if><s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr></s:else>
	</table>
</s:form>

<script>



function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_expenseName').focus();

}


}
var f9Fields= ["paraFrm_expenseName","paraFrm_expenseUnit"];
var fieldName = ["paraFrm_expenseName","paraFrm_expenseUnit"];
var lableName = ["expense.type","expense.unit"];
var type = ['enter','select'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="ExpensesCategory_addNew.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_expenseName').focus();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="ExpensesCategory_save.action";
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
	document.getElementById('paraFrm').action="ExpensesCategory_update.action";
	document.getElementById('paraFrm').submit();
	return true;
}*/
//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="ExpensesCategory_report.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="ExpensesCategory_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="ExpensesCategory_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"ExpensesCategory_f9Action.action");
}

//For Cancel Button
/*
function cancelFun()
{
	//alert("1pppk"+document.getElementById('paraFrm_loadFlag').value);
	//alert("2pppppp"+document.getElementById('paraFrm_addFlag').value);
	//alert("3pppppp"+document.getElementById('paraFrm_modFlag').value);
	
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in firast");
		document.getElementById('paraFrm').action="ExpensesCategory_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="ExpensesCategory_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="ExpensesCategory_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="ExpensesCategory_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}

*/

//cancelSec-----krishna


function cancelFun(){
     	document.getElementById("paraFrm").action="ExpensesCategory_cancelSec.action";
	    document.getElementById("paraFrm").submit();
        
    
    }


function saveValidation(){	
  var val=document.getElementById('paraFrm_expenseName').value;
	  if(val==""){
		  alert('Please enter expenseName');
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not Allowed in expenseName!');
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
	if(document.getElementById('paraFrm_expenseName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="ExpensesCategory_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_expenseName').value;
	if(val==""){
		  alert('Please enter expenseType Name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not Allowed in expenseType Name!');
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



/*


function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="ExpensesCategory_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}


*/



function callPage(id){

		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='ExpensesCategory_callPageView.action';
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
	   document.getElementById('paraFrm').action="ExpensesCategory_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	



   
function on(){


		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="ExpensesCategory_callPageView.action";
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
	
	   	document.getElementById("paraFrm").action="ExpensesCategory_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	 
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
	   document.getElementById('paraFrm').action="ExpensesCategory_delete1.action";
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
	   document.getElementById('paraFrm').action="ExpensesCategory_callPage1.action";
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

