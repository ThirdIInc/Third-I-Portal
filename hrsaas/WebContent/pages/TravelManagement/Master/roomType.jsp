<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="RoomType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
    <s:hidden name="upDownFlag" />
	<!-- Flagas used For Cancel Button -->
	<!--  
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	<s:hidden name="editFlag"/>
	-->
	<s:hidden name="editableflag" />
	<s:hidden name="cancelflag" />
	<s:hidden name="callpageflag" />

	<!--  for priority -->

	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<!-- <tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Room Type</strong></td>
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
					<td width="93%" class="txt"><strong class="text_head">Room
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
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="%{noflag}">
						<td width="22%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
						</td>
					</s:if>
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
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<s:if test="%{editableflag}">
						<tr>
								<td width="25%" height="22" class="formtext" colspan="1"><label  class = "set" name="hotel.name" id="hotel.name" ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label>

								<font color="red">*</font> :</td>
								<td width="50%" height="22" colspan="3"><s:hidden name="hotelId" /> <s:textfield
									name="hotelName" theme="simple" size="30" maxlength="30" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'RoomType_f9Hotel.action');"></td>
								
							</tr>
						
							<tr>
								<td width="25%" height="22" class="formtext" colspan="1"><label
									class="set" name="room.type" id="room.type"
									ondblclick="callShowDiv(this);"><%=label.get("room.type")%></label>

								<font color="red">*</font> :</td>
								<td width="50%" height="22" colspan="3"><s:hidden name="typeId" /> <s:textfield
									name="typeName" theme="simple" size="30" maxlength="30" onkeypress="return alphaNumeric();"/></td>
								
							</tr>
							
							


							<tr>
								<td height="22" class="formtext" colspan="1" width="25%"><label class="set"
									name="room.desc" id="room.desc" ondblclick="callShowDiv(this);"><%=label.get("room.desc")%></label>
								:</td>

								<td height="22" colspan="2" width="30%"><s:textarea name="desciption" cols="32"
									rows="4" onkeypress="return allCharacters();"
									onkeyup="callLength('desciption','descCnt1','2000');"></s:textarea>
								</td>
								<td height="22" valign="bottom" colspan="1" width="35%"><img src="../pages/images/zoomin.gif" height="12"
									align="absmiddle" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_desciption','room.desc','','paraFrm_descCnt1','2000');">&nbsp;Remaining chars <s:textfield
									name="descCnt1" readonly="true" size="5"></s:textfield></td>
							</tr>

							<tr>
								<td height="22" class="formtext" colspan="1" width="25%"><label class="set"
									name="room.sts" id="room.sts" ondblclick="callShowDiv(this);"><%=label.get("room.sts")%></label>
								<font color="red">*</font>:</td>
								<td height="22" colspan="3" width="50%"><s:select name="status" disabled="false" headerKey=" " headerValue="--Select--"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" /></td>
								
							</tr>
						</s:if>

						<s:else>
						
							<tr>
								<td bordercolor="red" width="25%" height="22" class="formtext" colspan="1"><label  class = "set" name="hotel.name" id="hotel.name" 
								ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label>

								<s:if test="%{noflag}">
									<font color="red">*</font>
								</s:if>:</td>
								<td width="50%" height="22" colspan="3"><s:hidden name="hotelId" /> <s:hidden
									name="hotelName" theme="simple" /> <s:property
									value="hotelName" /></td>
							</tr>
							<tr>
								<td bordercolor="red" width="25%" height="22" class="formtext" colspan="1"><label
									class="set" name="room.type" id="room.type"
									ondblclick="callShowDiv(this);"><%=label.get("room.type")%></label>




								<s:if test="%{noflag}">
									<font color="red">*</font>
								</s:if>:</td>
								<td width="50%" height="22" colspan="3"><s:hidden name="typeId" /> <s:hidden
									name="typeName" theme="simple" /> <s:property
									value="typeName" /></td>
							</tr>
							
							
						

							<tr>
								<td valign="top" width="25%" height="22" class="formtext" colspan="1"><label
									class="set" name="room.desc" id="room.desc"
									ondblclick="callShowDiv(this);"><%=label.get("room.desc")%></label>
								:</td>

								<s:hidden name="desciption" />

								<td width="50%" style="overflow: hidden;" colspan="3"><s:textarea
									readonly="true" cssStyle="border:none;overflow: hidden;"
									name="desciption" cols="75" rows="5" /></td>

							</tr>


							<tr>
								<td height="22" class="formtext" width="25%" colspan="1"><label
									class="set" name="room.sts1" id="room.sts"
									ondblclick="callShowDiv(this);"><%=label.get("room.sts")%></label>
								:</td>
								<td height="22" width="50%" colspan="3"><s:hidden name="status" /> <s:property
									value="status" /></td>
								
								<!-- <td><input type="text" name="hiddenText"
									style="visibility: hidden;"></td>-->
							</tr>
						</s:else>







					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">


						<tr>
							<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>

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
var lableName = ["room.type"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="RoomType_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
var fieldName = ["paraFrm_hotelName","paraFrm_typeName","paraFrm_status"];
var lableName = ["hotel.name","room.type","room.sts"];
var f9Fields= ["paraFrm_hotelName","paraFrm_typeName"];
var type = ['select','enter','select'];

	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	try{
	var desc =	document.getElementById('paraFrm_desciption').value;
	if(desc != "" && desc.length >2000){
		alert("Maximum length of"+document.getElementById('room.desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
		}
		}catch(e)
		{
		alert(e);
		}
	document.getElementById('paraFrm').action="RoomType_save.action";
	document.getElementById('paraFrm').submit();
	//return true;
}

//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="RoomType_report.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="RoomType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="RoomType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"RoomType_f9Action.action");
}

//For Cancel Button

function cancelFun(){

     	document.getElementById("paraFrm").action="RoomType_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }



function saveValidation(){	
  var val=document.getElementById('paraFrm_typeName').value;
	  if(val==""){
		  alert('Please enter Room Type Name');
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not Allowed in Room Type Name!');
		 return false;
	  }
	

	
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="RoomType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_typeName').value;
	if(val==""){
		  alert('Please enter Room Type Name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not Allowed in Room Type Name!');
		 return false;
	}

   	return true;
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
	
	   	document.getElementById("paraFrm").action="RoomType_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   

  	


	
 

  
  

</script>

