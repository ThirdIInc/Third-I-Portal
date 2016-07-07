<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HotelType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flags used For Cancel Button -->

	<!--<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>-->
	<s:hidden name="cancelflag" />
	<s:hidden name="callpageflag" />
	<s:hidden name="editableflag" />
	<!--  for priority -->

	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="2" theme="simple" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Hotel
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<s:if test="%{editableflag}">
							<tr>
								<td width="20%" height="22" class="formtext" colspan="1"><label
									class="set" id="hotel.name" name="hotel.name"
									ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label>

								<font color="red">*</font>:</td>
								<td width="50%" height="22" colspan="2"><s:hidden
									name="hotelCode" /> <s:textfield name="hotelName"
									theme="simple" size="35" maxlength="30"
									onkeypress="return alphaNumeric();" /></td>

							</tr>

							<tr>
								<td valign="top" height="22" width="20%" class="formtext"
									colspan="1"><label class="set" id="desc" name="desc"
									ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>:</td>
								<td height="22" width="25%" colspan="1"><textarea
									name="Description" cols="40" id="paraFrm_Description" rows="5"
									onkeyup="callLength('Description','descCnt1','2000');"
									wrap="wrap"><s:property value="Description" /></textarea></td>

								<td height="22" width="35%" valign="bottom" colspan="1"><img
									src="../pages/images/zoomin.gif" height="12" align="absmiddle"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_Description','desc','','paraFrm_descCnt1','2000');">&nbsp;
								Remaining chars <s:textfield name="descCnt1" readonly="true"
									size="5"></s:textfield></td>
							</tr>


							<tr>
								<td height="22" class="formtext" colspan="1" width="20%"><label
									class="set" id="status" name="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label><font
									color="red">*</font> :</td>
								<td height="22" width="50%" colspan="2"><s:select
									name="Status" disabled="false" headerKey=" "
									headerValue="--Select--" list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" /></td>

								<!-- <td><input type="text" name="hiddenText"
									style="visibility: hidden;"></td>-->
							</tr>
						</s:if>




						<s:else>
							<tr>
								<td width="20%" height="22" class="formtext" colspan="1"><label
									class="set" id="hotel.name" name="hotel.name"
									ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label>




								<s:if test="%{noflag}">
									<font color="red">*</font>
								</s:if>:</td>
								<td width="50%" height="22" colspan="2"><s:hidden
									name="hotelCode" /> <s:hidden name="hotelName" theme="simple" />
								<s:property value="hotelName" /></td>

							</tr>

							<tr>
								<td valign="top" height="22" class="formtext" width="20%"
									colspan="1"><label class="set" id="desc" name="desc"
									ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>:</td>

								<s:hidden name="Description" />


								<td colspan="2" width="65%" style="overflow: hidden;"><textarea
									name="Description" cols="75" id="paraFrm_Description" rows="5"
									onkeyup="callLength('Description','descCnt1','2000');"
									wrap="wrap" readonly="true"
									style="border: none; overflow: hidden;"><s:property
									value="Description" /></textarea><!--<s:property value="Description"/></td>-->
							</tr>


							<tr>
								<td height="22" class="formtext" colspan="1" width="20%"><label
									class="set" id="status" name="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
								<td height="22" colspan="2" width="50%"><s:hidden
									name="Status" /> <s:property value="Status" /></td>

								<!--  <td><input type="text" name="hiddenText"
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
					<td width="75%"><jsp:include
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
var f9Fields= ["paraFrm_hotelName"];
var fieldName = ["paraFrm_hotelName","paraFrm_Status"];
var lableName = ["hotel.name","status"];
var type = ['enter','select'];
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


function cancelFun(){
     	document.getElementById("paraFrm").action="HotelType_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
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
   
   
   

  	
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	  }
	  else
	    document.getElementById('hdeleteCode'+i).value="";
}
  	


 

 
 
  		
</script>

