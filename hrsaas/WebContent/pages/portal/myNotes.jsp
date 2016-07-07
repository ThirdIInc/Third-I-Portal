
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<%
	Object[][] noteDataObj = null;
	try {
		noteDataObj = (Object[][]) request.getAttribute("noteData");

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<div id='div_Id'
	style='position: absolute; z-index: 3; width: 165px; height: 20px; display: none; border: 1px solid #CCC; top: 100px; left: 250px; padding: 0px; background-color: #FFFFCC;'>

<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr>

		<td style="background-color: #0055E5; padding: 3px;" colspan="1"><img
			src="../pages/mypage/images/icons/notes24.png" /></td>

		<td align="center" style="cursor: move; width: 90%" bgcolor="#0055E5"
			onmouseout="Drag.end();"
			onmouseover="Drag.init(document.getElementById('div_Id'), null, 0, 900, 0, 800);" colspan="1">
		 <span id="moduleName" style="cursor: move; color: white;"><b>My Notes</b></span></td>
		<td border="1" bgcolor="#0055E5" width="10px" colspan="1"
			style="font-family: Arial; position: relative; cursor: pointer; color: white; text-align: center;"
			onclick="hide_Div();">&nbsp;<b>X</b>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="3">
		 
		<s:textarea theme="simple" cols="65" rows="10"  onkeypress="setValue();"
			cssStyle="background-color: #FFFFCC;border: 0px solid; margin:0; padding:0;"
			id="description" name="description" /></td>
	</tr>
	<tr>
		<td colspan="3"
			style="border-top: 1px solid #CCC; background-color: #FFFFCC;">
		<div id="noteDate"></div>

		</td>
	</tr>

</table>

</div>



<s:form action="MyNotes" id="paraFrm" theme="simple">

	<s:hidden name="hiddenNoteId" id="hiddenNoteId" />
	<s:hidden name="hiddenEditCode" />
<s:hidden name="checkFlag" id="checkFlag"/> 
	 

	<table width="100%" border="0" align="left" cellpadding="0"
		cellspacing="0">

 
		
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/notes24.png"  
						  /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Notes </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table border="0" cellpadding="0" cellspacing="0" bordercolor="red">


				<tr>
					<td  nowrap="nowrap"><a href="javascript:void(0);" class="contlink"
						title="Add Note" theme="simple" onclick="return callDiv();"> <img
						src="../pages/mypage/images/icons/draft.png"
						align="absmiddle" border="0" /> Add Note</a></td>
					<td width="22%">
					<div align="right"><span class="style2"> </span></div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<%
		int srNo = 0;
		%>

		<tr height="20">
			<td>
			<table width="100%" border="0">
				<tr>
					<td height="20" width="10%" class="formth" nowrap="nowrap"><strong>Sr.No</strong></td>
					<td height="20" class="formth" width="52%" ><strong>Note</strong></td>
					<td height="20" class="formth" width="15%" nowrap="nowrap"><strong>Created On </strong></td>
					<td height="20" class="formth" width="15%" nowrap="nowrap"><strong>Updated On </strong></td>
					<td height="20" class="formth" width="8%" nowrap="nowrap"></td>
				</tr>

				<%
						if (noteDataObj != null && noteDataObj.length > 0) {

						for (int i = 0; i < noteDataObj.length; i++) {
				%>



				<tr style="cursor: pointer;" height="20"
					onmouseover="newRowColor(this);" onmouseout="oldRowColor(this);"
					ondblclick="openDiv('<%=Utility.checkNull(String.valueOf(noteDataObj[i][5]))%>','<%=Utility.checkNull(String.valueOf(noteDataObj[i][0]))%>')">

					<td height="20" align="center" class="sortableTD"  title="Double click to view note" ><%=++srNo%>
					</td>
					<td height="20" class="sortableTD"  title="Double click to view note"><%=Utility.checkNull(String
									.valueOf(noteDataObj[i][2]))%>..</td>
					<td height="20" class="sortableTD" align="center"  title="Double click to view note"><%=Utility.checkNull(String
									.valueOf(noteDataObj[i][3]))%></td>
					<td  height="20"class="sortableTD" width="15%" align="center"  title="Double click to view note"><%=Utility.checkNull(String
									.valueOf(noteDataObj[i][4]))%></td>
					<td height="20" align="center" class="sortableTD"  nowrap="nowrap" title="Double click to view note">
					
					<img style="cursor: pointer;"
					onclick="openDiv('<%=Utility.checkNull(String.valueOf(noteDataObj[i][5]))%>','<%=Utility.checkNull(String.valueOf(noteDataObj[i][0]))%>')"
						src="../pages/mypage/images/icons/edit.png" title="Edit note" />
						|
					<img title="Remove note"
						src="../pages/mypage/images/icons/delete.png"  style="cursor: pointer;"
						onclick="callDelete('<%=Utility.checkNull(String.valueOf(noteDataObj[i][0])) %>')" />

					</td>
				</tr>

				<%
					}
					} else {
				%>
				<tr>
					<td height="20" align="center" colspan="6" width="100%"
						class="border">No Notes</td>
				</tr>
				<%
				}
				%>
			</table>
			</td>
		</tr>
	</table>


</s:form>

<script>

 autoDate () ;
 
 
 function setValue()
 {
 
 document.getElementById('checkFlag').value='1';
 
 }

function ismaxlength(obj){
		 
				var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
						if (obj.getAttribute && obj.value.length>mlength)
							obj.value=obj.value.substring(0,mlength)
			}

function autoDate() 
 	{
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		var tHours =tDay.getHours();
		var tMinutes =tDay.getMinutes();
		
		
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			
			if ( tHours < 10) tHours = "0"+tHours;
			
			if ( tMinutes < 10) tMinutes = "0"+tMinutes;
			
			
			var then =tDate+"/"+tMonth+"/"+tDay.getFullYear();
			then += ' '+tHours+':'+tMinutes; 
			document.getElementById("noteDate").innerHTML=then;
			 
				
}

function callDiv()
	{	
	try{
	
 		document.getElementById('div_Id').style.display = '';
 		document.getElementById('description').value='';
		document.getElementById('description').focus();
		
		}catch(e){ alert(e);}
	}

function hide_Div() {
		
		var description =trim(document.getElementById('description').value) ;
	 	
		description = description.replace(/(\r\n)|(\n)/g,"<br />");
	 	 
 var noteCode =document.getElementById('hiddenNoteId').value ;
 
 if(document.getElementById('checkFlag').value=="")
 {
 document.getElementById('hiddenNoteId').value="";
 }
 
	//document.getElementById('hiddenNoteId').value='';
		document.getElementById('div_Id').style.display = 'none';
		
		document.getElementById('description').value =trim(document.getElementById('description').value);
	if(description!="" && !document.getElementById('checkFlag').value=="")
	{
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/MyNotes_addNotes.action?note='+description+'&noteCode='+noteCode;
	
	 document.getElementById('paraFrm').submit();
	
	}
		
		
	}
	
	function openDiv(note,noteId)
	{
		 
		document.getElementById('div_Id').style.display = '';
		
		note = note.replace(/<br \/>/g, "\n"); 
		
		document.getElementById('description').value=note;
		
		document.getElementById('hiddenNoteId').value=noteId;
	
	}
	
	
	
  
  function callAdd()
{
 
var description =trim(document.getElementById('description').value) ;
 var noteCode =document.getElementById('hiddenNoteId').value ;
  document.getElementById('description').value =trim(document.getElementById('description').value);
	if(description=="")
	{
	
		alert("Please add note");
		document.getElementById('description').focus();
		return false;			
	}
	
	 
	
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/MyNotes_addNotes.action?note='+description+'&noteCode='+noteCode;
	 document.getElementById('paraFrm').submit();
	
	return true;
}


function callDelete(id){
    
    
    	//  alert("id  "+id);
    	 var conf=confirm("Do you really want to delete note?");
    	 
    	 if(conf)
    	 {
    	   document.getElementById('paraFrm_hiddenEditCode').value=id;
	   	document.getElementById("paraFrm").action="MyNotes_delete.action";
	  	document.getElementById("paraFrm").submit();
    	 }
    	 else{
    	 	return false;
    	 }
      
	 return true;
	   
   }

 
	

</script>

