<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="FoodType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->

	<!--
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	-->
	<s:hidden name="editableflag" />
	<s:hidden name="cancelflag" />
	<s:hidden name="callpageflag" />
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
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="3" width="100%">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<s:if test="%{editableflag}">
							<tr>
								<td width="25%" height="22" class="formtext" colspan="1"><label
									class="set" name="food.type" id="food.type"
									ondblclick="callShowDiv(this);"><%=label.get("food.type")%></label>

								<font color="red">*</font> :</td>
								<td width="50%" height="22" colspan="2"><s:hidden
									name="typeId" /> <s:textfield name="typeName" theme="simple"
									size="35" maxlength="70" onkeypress="return alphaNumeric();" />
								</td>

							</tr>



							<tr>
								<td height="22" class="formtext" colspan="1" width="25%"><label
									class="set" name="food.desc" id="food.desc"
									ondblclick="callShowDiv(this);"><%=label.get("food.desc")%></label>
								:</td>

								<td height="22" colspan="1" width="35%"><s:textarea
									name="description" cols="36" rows="4"
									onkeypress="return allCharacters(); "
									onkeyup="callLength('description','descCnt1','2000');" /> <img
									src="../pages/images/zoomin.gif" height="12" 
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_description','food.desc','','paraFrm_descCnt1','2000');">


								</td>
								<td height="22" valign="bottom" colspan="1" width="30%">
								Remaining chars <s:textfield name="descCnt1" readonly="true"
									size="5"></s:textfield></td>
							</tr>

							<tr>
								<td height="22" class="formtext" colspan="1" width="25%"><label
									class="set" name="food.sts" id="food.sts"
									ondblclick="callShowDiv(this);"><%=label.get("food.sts")%></label>
								<font color="red">*</font> :</td>
								<td height="22" class="formtext" colspan="2" width="50%"><s:select
									name="status" disabled="false" headerKey=" " headerValue="--Select--"
									list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" /></td>
								<td height="22" class="formtext">&nbsp;</td>
								<td height="22">&nbsp;</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td width="20%" height="22" class="formtext" colspan="1" wid><label
									class="set" name="food.type" id="food.type"
									ondblclick="callShowDiv(this);"><%=label.get("food.type")%></label>
								<s:if test="%{noflag}">
									<font color="red">*</font>
								</s:if>:</td>
								<td width="60%" height="22" colspan="2"><s:hidden name="typeId" /> <s:hidden
									name="typeName" theme="simple" /> <s:property value="typeName" />

								</td>

							</tr>

							<tr>
								<td valign="top" height="22" width="20%" class="formtext" colspan="1"><label
									class="set" name="food.desc" id="food.desc"
									ondblclick="callShowDiv(this);"><%=label.get("food.desc")%></label>
								:</td>
								<s:hidden name="description" />
								<td colspan="2" width="60%" style="overflow: hidden;"><s:textarea
									readonly="true" cssStyle="border:none;overflow: hidden;"
									name="description" cols="75" rows="5" /></td>

							</tr>


							<tr>
								<td height="22" class="formtext" width="20%" colspan="1"><label
									class="set" name="food.sts" id="food.sts"
									ondblclick="callShowDiv(this);"><%=label.get("food.sts")%></label>
								:</td>
								<td height="22" width="60%" colspan="2"><s:hidden name="status" /> <s:property
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
var fieldName = ["paraFrm_typeName","paraFrm_status"];
var lableName = ["food.type","food.sts"];
var type = ['enter','select'];
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	
	
	var desc =	document.getElementById('paraFrm_description').value;
	if(desc != "" && desc.length >2000){
		alert("Maximum length of "+document.getElementById('food.desc').innerHTML.toLowerCase()+" is 2000 characters.");
		document.getElementById('paraFrm_description').focus();
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
   
   

  	

  	



	
	

</script>

