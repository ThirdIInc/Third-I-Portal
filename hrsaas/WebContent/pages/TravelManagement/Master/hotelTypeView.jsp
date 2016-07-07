<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HotelType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flags used For Cancel Button -->

	<!--<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>-->
	<s:hidden name="callpageflag" />
	<!--  for priority -->

	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />



	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" theme="simple" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Hotel
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<%
 	int total1 = (Integer) request.getAttribute("abc");
 	int PageNo1 = (Integer) request.getAttribute("xyz");
 %>
					<%if(total1 >0){ %>
					<td align="right" colspan="2"><b>Page:</b> <%
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
					</select></td>
					&nbsp;
					<%} %>


				</tr>
			</table>
			</td>
		</tr>





		<s:hidden name="hotelCode" />

		<s:hidden name="hotelName" />
		<s:hidden name="Description" />
		<s:hidden name="Status" />




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
								id="hotel.list" name="hotel.list"
								ondblclick="callShowDiv(this);"><%=label.get("hotel.list")%></label></strong></td>
								<td align="center" class="formhead" nowrap="nowrap"><input
								type="button" class="delete" theme="simple" value="Delete "
								onclick=" return chkDelete();" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><strong><label
								class="set" id="sr.no" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
							<td class="formth" align="center"><strong><label
								class="set" id="hotel.name1" name="hotel.name"
								ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label></strong></td>

							<td class="formth" align="center"><strong><label
								class="set" id="status1" name="status"
								ondblclick="callShowDiv(this);"><%=label.get("status")%></label></strong></td>
							<td align="center" class="formth" width="10%"><strong><label
								class="set" id="priority" name="priority"
								ondblclick="callShowDiv(this);"><%=label.get("priority")%></label></strong></td>
							<td  align="center" class="formth" width="15%"  class="set" nowrap="nowrap" >
							<input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						     </td>

							<s:hidden name="statusUp" />
							<s:hidden name="statusDown" />
							<s:hidden name="upId" />

							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
									int cnt = PageNo1 * 20 - 20;
									int i = 0;
							%>
							<s:iterator value="hotelList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="hotelCode"/>');">


									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
									<s:hidden value="%{hotelCode}" id='<%="hotelCode" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('hotelCode' + '<%=i%>').value;
									</script>

									<s:hidden value="%{levelId}" />
									<td width="25%" align="left" class="sortableTD"><s:hidden
										value="%{hotelCode}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="hotelName" /></td>

									<td width="25%" align="left" class="sortableTD"><s:property
										value="Status" /></td>

									</td>


									</td>

									<td width="20%" align="center" class="sortableTD"><img
										src="../pages/common/css/default/images/up.GIF" width="16"
										height="15" onclick="callUP(<%=(cnt)%>);"> <img
										src="../pages/common/css/default/images/down.GIF" width="16"
										height="15" onclick="callDown(<%=(cnt)%>);"></td>
									<td align="center" width="10%" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDelete1('<s:property  value="hotelCode"/>','<%=i%>')" /><s:hidden
										name="itLevel" id="<%="itLevel"+cnt%>" /></td>


								</tr>

							</s:iterator>
							<%
							d = i;
							%>
						</tr>
						<s:hidden name="modeLength" />

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


function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_hotelName').focus();

}

}

var f9Fields= ["paraFrm_hotelName"];
var fieldName = ["paraFrm_hotelName"];
var lableName = ["hotel.name"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="HotelType_addNew.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_hotelName').focus();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="HotelType_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function updateFun()
{
	document.getElementById('paraFrm').action="HotelType_update.action";
	document.getElementById('paraFrm').submit();
}

function reportFun()
{  
     document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action="HotelType_report.action";
	document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target="main";
}
function editFun()
{
	document.getElementById('paraFrm').action="HotelType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="HotelType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"HotelType_f9Action.action");
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
		document.getElementById('paraFrm').action="HotelType_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="HotelType_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="HotelType_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="HotelType_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}



function saveValidation(){	
  var val=document.getElementById('paraFrm_hotelName').value;
	  if(val==""){
		  alert("Please enter "+document.getElementById('hotel.name').innerHTML.toLowerCase());
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not allowed in hotel type name!');
		 return false;
	  }
	

	
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_hotelName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="HotelType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_hotelName').value;
	if(val==""){
		  alert("Please enter "+document.getElementById('hotel.name').innerHTML.toLowerCase());
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		 alert('Space not allowed in hotel type name!');
		 return false;
	}
	
   	return true;
}

function callPage(id){
//alert('callpage');
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="HotelType_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}

function next(){
//alert('next page');
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="HotelType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
//alert('on page');
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="HotelType_callPage1.action";
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
	
	   	document.getElementById("paraFrm").action="HotelType_calforedit.action";
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
	   document.getElementById('paraFrm').action="HotelType_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {
		     var flag='<%=d %>';
		     
		     	document.getElementById('checkAll').checked = false;
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
//alert('previous page');
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="HotelType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}   



function callUP(id)
 	{  
 	 
 	  var pageNo=document.getElementById('paraFrm_show').value;
 	  //alert(id);
 	  if(id!=1)
 	  { 
 	     if(id%20==1)
       {
         var pgNumber =document.getElementById('selectname').value;
         var lastRecord = document.getElementById('paraFrm_lastRecordOfPage').value;
         var lastRecordArr = lastRecord.split(","); 
         document.getElementById('paraFrm_previousLevel').value = lastRecordArr[pgNumber-2];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else
       {
	 	   var preId= eval(id)-1;
	 	   var previous = document.getElementById('itLevel'+preId).value;
	 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
	 	   var current = document.getElementById('itLevel'+id).value;
	 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
 	   }
 	//alert('----'+pageNo); 
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	//alert(id);
 	if(id=="1")
 	{
  	alert("This is first record,so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="HotelType_upDataView.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id)
 {  
 
       //alert(id);
       var len=document.getElementById('paraFrm_modeLength').value;  
 	//alert("length"+len);
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	//id=(pageNo-1)*20+id;
 	// alert("id"+id);	
  	if(id==eval(len))
  	{    
  	alert("This is last record,so you cannot down the record.");
 	 return false;
 	 }
       if(id%20==0)
       {
        var pgNumber =document.getElementById('selectname').value;
        var firstRecord = document.getElementById('paraFrm_firstRecordOfPage').value;
        var firstRecordArr = firstRecord.split(",");
        // alert("pgNumber======== "+pgNumber+" ===== firstRecordArr[pgNumber-1]"+firstRecordArr[pgNumber-1]);
        document.getElementById('paraFrm_previousLevel').value = firstRecordArr[pgNumber-1];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else{
		       var preId= eval(id)+1;
		 	   var previous = document.getElementById('itLevel'+preId).value;
		 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
		 	   var current = document.getElementById('itLevel'+id).value;
		 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
   	document.getElementById('paraFrm_statusDown').value="true";
   	document.getElementById('paraFrm_statusUp').value="false";
   	document.getElementById('paraFrm_upId').value=id; 
   	document.getElementById('paraFrm').action="HotelType_upDataView.action";
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


/* function callUP(id)
 	{  
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	//alert(id);
 	if(id=="1")
 	{
  	alert("This is first record,so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="HotelType_upData.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id)
 {  
 alert('id'+id);
 
 	var len=document.getElementById('paraFrm_modeLength').value;  
 	alert("length"+len);
 	alert(document.getElementById('paraFrm_show').value);
 	var pageNo=document.getElementById('paraFrm_show').value;
 	alert('----'+pageNo); 
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	id=(pageNo-1)*20+id;
 	// alert("id"+id);	
  	if(id==eval(len))
  	{    
  	alert("This is last record,so you cannot down the record.");
 	 return false;
 	 }
  	
   	document.getElementById('paraFrm_statusDown').value="true";
   	document.getElementById('paraFrm_statusUp').value="false";
   	document.getElementById('paraFrm_upId').value=id; 
   	document.getElementById('paraFrm').action="HotelType_upData.action";
   	document.getElementById('paraFrm').submit(); 
   }*/
  		
</script>

