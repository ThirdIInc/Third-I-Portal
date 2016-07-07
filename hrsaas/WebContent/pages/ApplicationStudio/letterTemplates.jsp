<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<%--
<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/fckeditor.js"></script>
--%>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script src="../ckeditor/sample.js" type="text/javascript"></script>
	<link href="../ckeditor/sample.css" rel="stylesheet" type="text/css" />
	
<s:form action="LetterTemplate" method="post" name="LetterTemplate"
	validate="true" id="paraFrm" theme="simple">
	<s:hidden name="showOnLoadFlag" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Letter
					Template</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><s:hidden name="tempCode" value="%{tempCode}" />
					<s:hidden name="tempContent" value="%{tempContent}" /> 
					<s:hidden name="htmlcode" />  
					<input type="button" class="search"
						value="    Search" onclick="javascript:callPopup();" /> <s:submit
						cssClass="save" action="LetterTemplate_save" theme="simple"
						value="   Save" onclick="return callSave(); " /><s:submit
						cssClass="reset" action="LetterTemplate_resetLetterTemplate" theme="simple"
						value="   Reset" onclick="return callReset(); " />
						<s:submit
						cssClass="duplicate" action="LetterTemplate_duplicate"
						theme="simple" cssClass="save" value=" Duplicate"
						onclick="return callDuplicateSave();"></s:submit>
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
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" class="btn-middlebg">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li><a href="javascript:callDivLoad('TEMP');" id="TEMP_L">
						<div align="center"><span>Template </span></div>
						</a></li>
						<li><a href="javascript:callDivLoad('QUE');" id="QUE_L">
						<div align="center"><span>Query</span></div>
						</a></li>
					</ul>
					</div>

					</td>

					<td width="22%">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan=4 width="100%">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4" width="100%">
					<div id="TEMP" style="display: none">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">

						<tr>
							<td width="20%" colspan="1"><label name="template.name"
								id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							<font color="red">*</font>:</td>
							<td width="30%" colspan="1"><s:textfield name="tempName"
								size="30" /><s:hidden name="hiddenEdit" /></td>
							<td width="20%" colspan="1"><label class="set"
								name="temp.type" id="temp.type" ondblclick="callShowDiv(this);"><%=label.get("temp.type")%></label><font
								color="red">*</font>:</td>
							<td width="30%" colspan="1"><!--<s:select theme="simple" name="tempType"
				headerKey="0" headerValue="--Select--"
				list="#{'1':'Offer Template','2':'Appointment Template','3':'Acceptance Template','E':'Experience Template'}"  cssStyle="width:133"/>
			--> <s:select name="tempType" list="tempTypeMap" /></td>
						</tr>
						<tr>
					<td><label name="duplicatetemplate.name"
						id="duplicatetemplate.name" ondblclick="callShowDiv(this);"><%=label.get("duplicatetemplate.name")%></label>:<font
						color="red">*</font>:</td>
					<td height="20"><s:textfield name="dupTempName"
						size="30" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
						<!-- Variable template block starts -->

						<s:if test="templateOptFlag">
							<tr>
								<td colspan="1"><label name="variablename"
									id="variablename" ondblclick="callShowDiv(this);"><%=label.get("variablename")%></label>
								<font color="red">*</font>:</td>
								<td colspan="1"><s:textfield name="variableName"
									id="paraFrm_variableName" size="30" /></td>
								<td colspan="1">&nbsp;</td>
								<td colspan="1">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="1"><label name="variablevalue"
									id="variablevalue" ondblclick="callShowDiv(this);"><%=label.get("variablevalue")%></label>
								<font color="red">*</font>:</td>
								<td colspan="3"><s:textfield name="variableValue"
									id="paraFrm_variableValue" size="100" /> <input type="button"
									value='Add' class="add" onclick="callAddVariable();" /></td>
							</tr>

						<tr>
					<td colspan="1"><label name="variablelist"
								id="variablelist" ondblclick="callShowDiv(this);"><%=label.get("variablelist")%></label></td>
							<td colspan="1"><s:select name="letterTempVar"
								list="letterTempMap" /></td>
							<td colspan="1"><input type="button" value='Add To Template'
								class="add" onclick="editLetterTempVar();" />
								</td>
							<td><input type="button" value='Remove'
								class="delete" onclick="removeLetterTempVar();" /></td>
						</tr>
						

						<!-- Variable template block ends -->
						
							<tr>
								<td colspan="1">Place Holder</td>
								<td colspan="1"><s:select name="templateOpt"
									list="templateOptMap" /></td>
								<td colspan="1"><input type="button"
									value='Add To Template' class="add" onclick="editAreaOpt();" />
								</td>
								<td colspan="1"><input type="button"
									value='Add To Variable' class="add"
									onclick="callAddToVariable();" /></td>
							</tr>


							<%
							int sal = 1;
							%>
							<s:iterator value="salaryItt">
								<tr>
									<td align="left" colspan="1">
										<s:property value="queryNameItt" /></td>
									<td colspan="1">
										<s:select name="templateOptSalary" id="<%="paraFrm_templateOptSalary"+sal%>"
										list="templateOptSalaryMap" />
									</td>
									<td>
										<input type="button" value='Add To Template' class="add" onclick="editAreaOptSalary('<%=sal%>');" />
									</td>
									<td colspan="1">
										<input type="button" value='Add To Variable' class="add" onclick="callAddToVariableFromMap('<%=sal%>');" />
									</td>
								</tr>
								<%
								sal++;
								%>
							</s:iterator>

						</s:if>
					

				<tr>
					<td valign="middle" colspan="5" width="100%">
					<table width="100%" border="0">
						<tr>
							<%
							int k = 0;
							%>
							<td width="100%"><s:iterator value="iterate">


								<input type="hidden" name="field"
									value='<s:property value="field"/>' id="field<%=k%>" />
								<%
								k++;
								%>

							</s:iterator> <%
 k = 0;
 %>
							</td>
						</tr>
					</table>
					</td>
				</tr>
<tr>
					<td colspan="4" width="100%">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td><textarea style="" id="MyTextarea" name="MyTextarea"></textarea></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>


			</div>
			</td>
		</tr>
		</table>
					</td>
				</tr>
<!-- ######################################################################## -->
		<tr>
			<td colspan="4" width="100%">
			<div id="QUE" style="display: none;">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="20%" colspan="1"><label name="query.name"
						id="query.name" ondblclick="callShowDiv(this);"><%=label.get("query.name")%></label>
					<font color="red">*</font>:</td>
					<td width="30%" colspan="1"><s:textfield name="queryNameTab"
						size="30" /></td>
					<td width="20%" colspan="1">&nbsp;</td>
					<td width="30%" colspan="1">&nbsp;</td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label name="query"
						id="query" ondblclick="callShowDiv(this);"><%=label.get("query")%></label>
					:</td>
					<td width="75%" colspan="2" height="22"><s:textarea
						name="qName" rows="8" cols="90" /></td>
					<td width="10%" colspan="2" height="22" valign="middle"><s:submit
						cssClass="add" action="LetterTemplate_add" theme="simple"
						value="    Add   " onclick="return callAdd()" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="query.type" id="query.type" ondblclick="callShowDiv(this);"><%=label.get("query.type")%></label>
					:</td>
					<td colspan="1" height="22"><s:select name="qType"
						onchange="getNoOfRows();"  
						list="#{'':'-------------Select------------','S':'Salary','O':'Other','D':'Table Data'}" />
					</td>
					<td colspan="2">
					<div id="OTH" style="display: none;">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><label name="no.of.row" id="no.of.row"
								ondblclick="callShowDiv(this);"><%=label.get("no.of.row")%></label>
							:<s:textfield name="noOfRows" size="05" /></td>
							<td><label name="no.of.column" id="no.of.column"
								ondblclick="callShowDiv(this);"><%=label.get("no.of.column")%></label>
							:<s:textfield name="noOfColumn" size="05" /></td>
						</tr>
					</table>
					</div>
					 
					 

				  
					    
					<div id="TBL" style="display: none;">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><label name="cols_width" id="cols_width"
								ondblclick="callShowDiv(this);"><%=label.get("cols_width")%></label>
							:  
							 <s:textfield name="cols_width" size="10" /></td>  
							  
						</tr>  
					</table>
					</div>  
					</td>  
</tr>

				 
				<tr>
					<td colspan="5" width="100%">
					<table class="formbg" width="100%">

						<tr>
							<td align="center" class="formth" width="5%"><label
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
							</td>
							<td align="center" class="formth" width="80%"><label
								name="query" id="query1" ondblclick="callShowDiv(this);"><%=label.get("query")%></label>
							</td>
							<td align="center" class="formth" width="15%"><label
								name="query.type" id="query.type1"
								ondblclick="callShowDiv(this);"><%=label.get("query.type")%></label>
							</td>
							<!--<td align="right" class="formth" nowrap="nowrap" width="10%"><s:submit
								cssClass="del" theme="simple" value="     Remove  "
								onclick=" return chkDelete()" /></td>
							 
							-->
							<td class="formth" nowrap="nowrap">Edit | Delete</td>
							<%!int d1 = 0;%>
							<%
							int ii = 0;
							%>


							<s:iterator value="list">

								<tr>
									<td width="5%"><%=++ii%>
										<input type="hidden" name="srNo" value="<%=ii%>" />
									</td>
									<td width="80%">
										<s:property value="queryName" />
										<s:hidden name="queryName" />
									</td>
									<td width="15%" align="center">
										<s:property	value="queryType" /><s:hidden name="queryType" />
										<s:hidden name="noOfRowsItt" /><s:hidden name="noOfColumnItt" />  
					                    <s:hidden name="colsWidthItt" /><s:hidden name="queryNameItt" />    
									</td>    
									<td align="center"> 
										<input type="button" class="rowEdit" onclick="callForEdit('<%=ii %>')" />
										<input type="button" class="rowDelete" onclick="callForDelete('<%=ii %>')" />
									</td>
								</tr>
							</s:iterator>
							<%
							d1 = ii;
							%>
						</tr>
						<s:hidden name="subcode" />
						<s:hidden name="tableLength" />
					</table>
					</td>
				</tr>

			</table>
			</div>

			</td>
		</tr>


	</table>
	<s:hidden name="variablePriority" />

</s:form>
<script type="text/javascript">
			//<![CDATA[
CKEDITOR.replace( 'MyTextarea');
				

			//]]>
</script>
<script>


	function getNoOfRows(){
		document.getElementById('OTH').style.display = 'none';
		document.getElementById('TBL').style.display = 'none';
	
		if(document.getElementById('paraFrm_qType').value=='S'){
		document.getElementById('OTH').style.display = '';
		}
		if(document.getElementById('paraFrm_qType').value=='D'){
		document.getElementById('TBL').style.display = '';
		}
		}

		getNoOfRows();
		
		/*
		var oFCKeditor;
		window.onload = function()
		{

		
		oFCKeditor = new FCKeditor( 'MyTextarea' ) ;
		oFCKeditor.BasePath = 'http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/pages/common/fckeditor/';
		
		oFCKeditor.ReplaceTextarea() ;
		
		
		}
		
		*/
		function setMess()
		{ 
		var ms=document.getElementById('paraFrm_htmlcode').value;
		document.getElementById('MyTextarea').value=ms;
		
		//document.getElementById('TEMP_L').className='on';
		//document.getElementById('TEMP').style.display = '';
		
		if(document.getElementById('paraFrm_showOnLoadFlag').value=='QUE'){
		document.getElementById('QUE_L').className='on';
		document.getElementById('QUE').style.display = '';
		}
		else{
		document.getElementById('TEMP_L').className='on';
		document.getElementById('TEMP').style.display = '';
		}
		
		}
		
		setMess();
		
		function setMessage()
		{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
		var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();
		}
		
				function callDivLoad(id)
		{
			 //alert('id  : '+id);
			 
			 document.getElementById('paraFrm_showOnLoadFlag').value=id;
			document.getElementById('TEMP').style.display = 'none';
			document.getElementById('QUE').style.display = 'none';			
			document.getElementById('TEMP_L').className='li';
			document.getElementById('QUE_L').className='li';			
			document.getElementById(id).style.display = '';
			document.getElementById(id+"_L").className = 'on';
			
		}


</script>

<script>
function callAdd(){
	setMessage();
	var fields=["paraFrm_qName"];
    var labels=["query"];
    var flag = ["enter"];
 	 if(!validateBlank(fields,labels,flag))
     return false;

	if(document.getElementById('paraFrm_queryNameTab').value==''){
	alert("Please enter "+document.getElementById('query.name').innerHTML.toLowerCase());
	return false;
	}
	if(document.getElementById('paraFrm_qType').value=='S'){
	
		if(document.getElementById('paraFrm_noOfRows').value=='' ||document.getElementById('paraFrm_noOfRows').value=='0'){
	alert("Please enter "+document.getElementById('no.of.row').innerHTML.toLowerCase());
	return false;
	}
	if(document.getElementById('paraFrm_noOfColumn').value=='' ||document.getElementById('paraFrm_noOfColumn').value=='0'){
	alert("Please enter "+document.getElementById('no.of.column').innerHTML.toLowerCase());
	return false;
	}
		}
		
		if(document.getElementById('paraFrm_qType').value=='D'){
	
		if(document.getElementById('paraFrm_cols_width').value=='' ||document.getElementById('paraFrm_cols_width').value=='0'){
	alert("Please enter "+document.getElementById('cols_width').innerHTML.toLowerCase());
	return false;
	}
	 
		}  
		
		

}

function newRowColor(cell) {
	cell.className='onOverCell';
}
		
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
function callForEdit(id){
	setMessage();
	document.getElementById('paraFrm_hiddenEdit').value=id;
	document.getElementById("paraFrm").action="LetterTemplate_editQuery.action";
	document.getElementById("paraFrm").submit();
 	
}
	
	
  function callForDelete(id)
	   {
	 	  //alert(id);
	 	  setMessage();
	 	document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="LetterTemplate_deleteQuery.action";
		document.getElementById("paraFrm").submit();
	   
   		}
   		
 
  
</script>



<script>

		function editLetterTempVar(id){
		
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		var var1 = oEditor.GetHTML();
		var var1 = oEditor.getData();
		var ms = '<#'+ document.getElementById("paraFrm_letterTempVar").value+'#>' ;
		oEditor.insertHtml(ms);
		return true; 
		}

	function editAreaOpt(id)
		{
		
		//var a= 'field'+id
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var var1=oEditor.GetHTML();
		var var1 = oEditor.getData();
		var ms='<#'+ document.getElementById("paraFrm_templateOpt").value+'#>' ;
		oEditor.insertHtml(ms);
		 return true;        
		}
		
	function editAreaOptSalary(id)
		{
		//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
		var oEditor = CKEDITOR.instances.MyTextarea;
		//var var1=oEditor.GetHTML();
		var var1 = oEditor.getData();
		var ms='<#'+ document.getElementById("paraFrm_templateOptSalary"+id).value+'#>' ;
		oEditor.insertHtml(ms);
		 return true;        
		 
		}
   
   


function editArea(id)
{

 //alert(id);
var a= 'field'+id

//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var oEditor = CKEDITOR.instances.MyTextarea;
//var var1=oEditor.GetHTML();
var var1 = oEditor.getData();
var ms='<#'+ document.getElementById(a).value+'#>' ;
oEditor.insertHtml(ms);
 return true;        
 
}

function setMessage()
{
//var oEditor = FCKeditorAPI.GetInstance('MyTextarea') ;
var oEditor = CKEDITOR.instances.MyTextarea;
//var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.GetHTML();
var ms=document.getElementById('paraFrm_htmlcode').value=oEditor.getData();

}


</script>

<script>
function callSave(){

try
{
var fieldName = ["paraFrm_tempName"];
var lableName = ["template.name"];
var flag = ["enter"];
var svar=document.getElementById('paraFrm_tempName').value;
var tempType=document.getElementById('paraFrm_tempType').value;
 //alert(tempType);
if(svar=="")
{
alert("Please enter "+document.getElementById('template.name').innerHTML.toLowerCase());
return false;
}
if(tempType=="0")
{
alert("Please select "+document.getElementById('temp.type').innerHTML.toLowerCase());
return false;
}

if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
      	if(!f9specialchars(fieldName)){
           	return false;
   		}
setMessage();
}
catch(e)
{
}
return true;
}


function callDuplicateSave()
{
var tvar=trim(document.getElementById('paraFrm_tempName').value); 
var dvar=trim(document.getElementById('paraFrm_dupTempName').value);
var tempcode=document.getElementById('paraFrm_tempCode').value
if(tempcode=="")
{
alert("Please First Select Template Name to Duplicate by Searching");
return false;
}
if(dvar=="")
{
alert("Please enter Duplicate Template Name");
return false;

}
if(dvar == tvar){
alert("This Template Name already exist please enter different name");
return false;}

 else{
return true;
}

//setMessage();
return true;
}

		  function chkDelete()
	{
	 	if(chk()){
	 		var con=confirm('<%=label.get("confirm.delete")%>');
 			if(con){
	   			document.getElementById('paraFrm').action="LetterTemplate_deleteDtl.action";
	    		document.getElementById('paraFrm').submit();
    		}
    		else{
	    		var flag='<%=d1 %>';
	  			for(var a=1;a<=flag;a++){	
	  			document.getElementById('confChkop'+a).checked=false;
	   			document.getElementById('confChkop'+a).checked="";
	   		 	document.getElementById('hdeleteOp'+a).value="";
	    	}
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	return false;
	 	}
	}
	
	function chk(){
	 	var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkop'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}	
	
	function callPopup(){
		setMessage();
		callsF9(500,325,'LetterTemplate_f9action.action');
	
	}
	function callAddVariable(){
	try{
	
	var fieldName = ["paraFrm_variableName","paraFrm_variableValue"];
	var lableName = ["variablename","variablevalue"];
	var flag = ["enter","enter"];
	
	if(!validateBlank(fieldName,lableName,flag)){
			return false;
        } else {
        document.getElementById("paraFrm").action="LetterTemplate_addVariable.action";
		document.getElementById("paraFrm").submit();
        
        
        }
	
	
	}catch(e){alert(e);}
	
	}
	
	function callAddToVariable(){
		try {
			var placeHolder = '<#'+document.getElementById('paraFrm_templateOpt').value+'#>';
			document.getElementById('paraFrm_variableValue').value += placeHolder;
		}catch(e){alert(e);}
	}
	
	function callAddToVariableFromMap(id){
		try {
			var newVals = '<#'+document.getElementById("paraFrm_templateOptSalary"+id).value+'#>';
			document.getElementById('paraFrm_variableValue').value += newVals;
		}catch(e){alert(e);}
	}
	function callReset(){
	
		document.getElementById("paraFrm").action="LetterTemplate_resetLetterTemplate.action";
		document.getElementById("paraFrm").submit();
		
	}
	
	function removeLetterTempVar(){
				
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
	    		document.getElementById("paraFrm").action="LetterTemplate_deleteVariableLetterTemplate.action";
				document.getElementById("paraFrm").submit();
		}
	}
	
		
		
</script>