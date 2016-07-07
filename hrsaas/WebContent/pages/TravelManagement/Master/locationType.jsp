<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="LocationType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="onLoadFlag"/>
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	<s:hidden name="cancelFlg"/>
	<s:hidden name="pageFlag"/>
	
	
	<s:hidden name="panelFlag"/>
	<s:hidden name="retrnFlag"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Type</strong></td>
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
			<td colspan="4">
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
			<td colspan="4" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
					

						<tr>
							<td width="25%" height="22" class="formtext" colspan="1"><label  class = "set"  id="location.name" name="location.name" ondblclick="callShowDiv(this);"><%=label.get("location.name")%></label>
							<font color="red">*</font>:</td>
							
							<td width="20%" height="22" colspan="2"><s:hidden name="locationCode" />
							
								<s:textfield name="locationName" theme="simple" size="31"
									maxlength="50" readonly="false" onkeypress="return alphaNumeric();"/>
							</td>
							<!--<td width="27%" height="22" class="formtext">&nbsp;</td>
							<td width="18%" height="22">&nbsp;</td>
						--></tr>
						<tr>
							<td valign="top" height="22" width="25%" class="formtext" colspan="1" ><label  class = "set"  id="desc" name="desc" ondblclick="callShowDiv(this);"><%=label.get("desc")%></label> :</td>
							<td height="22" width="20%" colspan="2">
								<s:textarea name="Description" cols="33" rows="4" onkeypress="return allCharacters();"
									readonly="false" onkeyup="callLength('Description','descCnt','2000');"/>
							</td>
							
							
							<td height="22" width="30%"  valign="bottom" colspan="1"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_Description','desc','','paraFrm_descCnt','2000');" >
										&nbsp;Remaining chars<s:textfield name="descCnt"
											readonly="true" size="5"></s:textfield></td>
						</tr>

				<tr>
							<td height="22" width="25%" class="formtext" colspan="1"><label  class = "set"  id="status"  name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label><font color="red">*</font> :</td>
							<td height="22" width="20%" colspan="3"><s:select theme="simple" name="Status" disabled="false" headerKey=" "  headerValue="--Select--"
									value="%{Status}" list="#{'A':'Active','D':'Deactive'}" /></td>
							<!--<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>
							--><td><input type="text" name="hiddenText" style="visibility: hidden;"></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
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
document.getElementById('paraFrm_locationName').focus();
}

}

var f9Fields= ["paraFrm_locationName"];
var fieldName = ["paraFrm_locationName","paraFrm_Status"];
var lableName = ["location.name","status"];
var type = ['enter','select'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="LocationType_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	var desc =	document.getElementById('paraFrm_Description').value;
	
	if(desc != "" && desc.length >2000){
		alert("Maximum length of "+document.getElementById('desc').innerHTML.toLowerCase()+" is 2000 characters.");
		document.getElementById('paraFrm_Description').focus();
		return false;
    }   
	document.getElementById('paraFrm').action="LocationType_save.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_gradeName').focus();
	return true;
}

//For Edit Button

function updateFun()
{
	document.getElementById('paraFrm').action="LocationType_update.action";
	document.getElementById('paraFrm').submit();
}

function reportFun()
{  
     document.getElementById('paraFrm').target="_balnk";
	document.getElementById('paraFrm').action="LocationType_report.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}
function editFun()
{
	document.getElementById('paraFrm').action="LocationType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="LocationType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"LocationType_f9Action.action");
}


function cancelFun(){
     	document.getElementById("paraFrm").action="LocationType_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }



	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_locationName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="LocationType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	

/*function callPage(id){
//alert("callPage");
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
		document.getElementById('paraFrm').action='LocationType_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}	

function next(){
//alert("next");
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="LocationType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
//alert("on");
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="LocationType_callPage1.action";
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
	
	   	document.getElementById("paraFrm").action="LocationType_calforedit.action";
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
  	

	
	
function previous(){
//alert("on");
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="LocationType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}   */

</script>

