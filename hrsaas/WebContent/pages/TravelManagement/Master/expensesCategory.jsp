<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ExpensesCategory_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->

	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="editModeFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="onLoadFlag" />
	<s:hidden name="pageFlag" />

	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />


	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<!--  <tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Expenses Category</strong></td>
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
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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
							<td height="22" class="formtext" width="26%" colspan="1"><label
								class="set" name="expense.type" id="expense.type"
								ondblclick="callShowDiv(this);"><%=label.get("expense.type")%></label>

							<font color="red">*</font> :</td>
							<td width="50%" height="22" colspan="3"><s:hidden
								name="expenseId" /> <s:textfield name="expenseName"
								theme="simple" size="30" maxlength="90"
								onkeypress="return alphaNumeric();" /></td>

						</tr>

						<tr>
							<td height="22" class="formtext" width="26%" colspan="1"><label
								class="set" name="expense.unit" id="expense.unit"
								ondblclick="callShowDiv(this);"><%=label.get("expense.unit")%></label>


							<font color="red">*</font> :</td>
							<td width="50%" height="22" colspan="3"><s:select
								name="expenseUnit"
								list="#{'':'--Select--','D':'Per Day','T':'Per Travel'}"
								cssStyle="width:180;z-index:5;" /></td>
						</tr>

						<tr>
							<td height="22" class="formtext" width="26%" colspan="1"><label
								class="set" name="expense.desc" id="expense.desc"
								ondblclick="callShowDiv(this);"><%=label.get("expense.desc")%></label>
							:</td>
							<td width="30%" height="22" colspan="2"><s:textarea
								name="description" cols="32" rows="4"
								onkeyup="callLength('description','descCnt','2000');" /></td>
							<td height="22" valign="bottom" colspan="1" width="35%"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_description','expense.desc','','paraFrm_descCnt','2000');">
							&nbsp;Remaining chars<s:textfield name="descCnt" readonly="true"
								size="5"></s:textfield></td>

						</tr>


						<tr>
							<td height="22" class="formtext" width="26%" colspan="1"><label
								class="set" name="expense.sts" id="expense.sts"
								ondblclick="callShowDiv(this);"><%=label.get("expense.sts")%></label>
							<font
						color="red">*</font>:</td>
							<td width="50%" height="22" colspan="3"><s:select
								name="status" disabled="false"
								list="#{'':'--Select--','A':'Active','D':'Deactive'}"
								cssStyle="width:180;z-index:5;" /></td>
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


callForFirstEdit();
function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_expenseName').focus();

}


}
var f9Fields= ["paraFrm_expenseName","paraFrm_expenseUnit"];
var fieldName = ["paraFrm_expenseName","paraFrm_expenseUnit","paraFrm_status"];
var lableName = ["expense.type","expense.unit","expense.sts"];
var type = ['enter','select','select'];

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
	
	
	var desc =	document.getElementById('paraFrm_description').value;
	
	if(desc != "" && desc.length >2000){
		alert("Maximum length of "+document.getElementById('expense.desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
    }    
	document.getElementById('paraFrm').action="ExpensesCategory_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

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



//cancelSec-----krishna


function cancelFun(){
     	document.getElementById("paraFrm").action="ExpensesCategory_cancelFirst.action";
	    document.getElementById("paraFrm").submit();
        
    
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
	

</script>

