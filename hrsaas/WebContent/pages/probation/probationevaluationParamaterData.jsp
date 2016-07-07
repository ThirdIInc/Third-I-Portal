<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<s:form action="ProbationEvaluationParameter" validate="true"
	id="paraFrm" theme="simple">
	<!-- Form Hidden Fields -->
	<s:hidden name="probationItemCode" />
	<s:hidden name="probationCode" />
	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="2">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Probation
					Evaluation Parameter </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%">
				<tr>
					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" colspan="1">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Question Name Block Starts -->
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="20%" colsspan="1"><label class="set"
								name="question.name" id="question.name"
								ondblclick="callShowDiv(this);"><%=label.get("question.name")%></label>
							:<font color="red">*</font></td>
							<td width="80%" colsspan="1"><s:textfield size="75"
								name="questionName" maxlength="150"
								onkeypress="return isCharactersKey(event)" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Question Name Block Ends -->

		<!-- Options Block Starts -->
		
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="formbg">

					<tr>
						<td width="100%" colspan="2" class="formth"><b>OPTIONS</b></td>
					</tr>


					<tr>
						<td width="20%" colspan="1"><label class="set"
							name="question.attribute" id="question.attribute"
							ondblclick="callShowDiv(this);"><%=label.get("question.attribute")%></label>
						:<font color="red">*</font></td>
						<td width="80%" colspan="1"><s:hidden name="deleteId" /><s:textfield
							size="30" maxlength="30" name="questionAttr"
							onkeypress="return isCharactersKey(event)" /></td>
					</tr>

					<tr>
						<td width="20%" colspan="1"><label class="set"
							name="parameter.value" id="parameter.value"
							ondblclick="callShowDiv(this);"><%=label.get("parameter.value")%></label>
						:<font color="red">*</font></td>
						<td width="80%" colspan="1"><s:textfield size="15"
							maxlength="2" name="attributeValue"
							onkeypress="return isNumberKey(event)" /></td>

					</tr>
					<tr align="center">
						<td colspan="2" align="center"><s:submit cssClass="add"
							action="ProbationEvaluationParameter_addItem" theme="simple"
							value="   Add   " onclick="return callAdd();" id="ctrlHide"/></td>
					</tr>
					
				</table>
				</td>
			</tr>
		
		<!-- Options Block Ends -->


		<!-- List Starts -->

		<s:if test="true">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr align="center">

				<td colspan="4">
				<table width="100%" cellspacing="2" class="formbg" border="0">
					<tr>
						<td width="10%" class="formth"><label class="set" id="sr.no"
							name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
						<td width="60%" class="formth"><label class="set"
							id="question.attribute" name="question.attribute"
							ondblclick="callShowDiv(this);"><%=label.get("question.attribute")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="parameter.value" name="parameter.value"
							ondblclick="callShowDiv(this);"><%=label.get("parameter.value")%></label></td>
						<td width="20%" class="formth">Edit/Delete</td>

					</tr>
					<%!int loopcount = 0;%>
					<%
					int n = 0;
					%>
					<s:iterator value="probevalList" status="stat">
						<tr>
							<td width="10%" class="sortableTD" align="center"><s:hidden
								name="srNo" /><%=++n%></td>

							<td width="60%" align="left" class="sortableTD">&nbsp; <s:property
								value="attribute" /> <s:hidden name="attribute" /></td>
							<td width="10%" align="right" class="sortableTD">&nbsp; <s:property
								value="value" /> <s:hidden name="value" /></td>
							<td width="20%" align="center" class="sortableTD" id='ctrlHide'>

							<input type="button" class="rowEdit" title="Edit Record"
								onclick="javascript:callForEditData('<%=n%>','<s:property value="attribute"/>','<s:property value="value"/>')" />
							<input type="button" class="rowDelete"
								onclick="callDeleteRecord(<%=n%>)" title="Delete Record" /></td>
						</tr>
						
                        
					</s:iterator>
					
					<%
						loopcount=n;
					
						//out.println("loopcount               "+loopcount);
						
						//out.println("n          "+n);
					%>

				</table>
				</td>
			</tr>
		</s:if>
		<!-- List Endss -->

		<tr>
			<td width="100%" colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

	<s:hidden name="hiddenEdit" id="hiddenEdit" />
</s:form>

<script>
 function callAdd(){
       
    	var questAttr = document.getElementById('paraFrm_questionAttr').value;
        var attrValue = document.getElementById('paraFrm_attributeValue').value;    	
    
    	var field=["paraFrm_questionAttr","paraFrm_attributeValue"];
	    	var label=["question.attribute","parameter.value"];
	    	var types=["Enter","Enter"];
	    	
	     		if(!validateBlank(field,label,types)){
			return false;
			}
  
   }

function saveFun()
{
    	
    	
    	  var questName=document.getElementById('paraFrm_questionName').value;
		  if(questName=="")
		  {
			  alert('Please Enter '+document.getElementById('question.name').innerHTML.toLowerCase());
			  return false;
		  }
		  
		  if(!(questName==""))
  		{
  				var count =0;
					var iChars =" ";
		  			for (var i = 0; i < questName.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(questName.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==questName.length){
  					alert ("Blank Spaces Not Allowed");
  					return false;
  		}
  		
  	}	
		  
		  var total ='<%=loopcount%>';
		  if(eval(total)== 0)
		  {
		  alert("Please Add Options");
		  return false;
		  
		  }
		  
        document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ProbationEvaluationParameter_saveEvalParameter.action';
		document.getElementById('paraFrm').submit();
		
return true;
   }




 function callForEditData(editCode,attribute,attribValue)
   {
	document.getElementById('hiddenEdit').value=editCode;
	document.getElementById('paraFrm_questionAttr').value=attribute;
	document.getElementById('paraFrm_attributeValue').value=attribValue;

		 	
}

function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ProbationEvaluationParameter_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ProbationEvaluationParameter_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function cancelFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ProbationEvaluationParameter_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function deleteFun() {
		var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ProbationEvaluationParameter_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'ProbationEvaluationParameter_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	


function editFun() {
		return true;
	}	
	
	 function callForDelete2(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChkop'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdeleteOp'+id).value="";
	    //alert(i+"flag=="+document.getElementById('confChkop'+id).checked);
	   //alert("id for delete"+document.getElementById('hdeleteOp'+id).value);
   		}
   		
   	
  
  /* Numvers Only Function*/
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=46)
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
             }
             return true;
      
      }		 
  
   function callDeleteRecord(rowId){
   		var conf=confirm("Are you sure to delete this record?");
   		if(conf) {
   		
   		document.getElementById("hiddenEdit").value=rowId;
   	   	document.getElementById("paraFrm").action="ProbationEvaluationParameter_deleteData.action";
    	document.getElementById("paraFrm").submit();
   
   } 
    
   
   }
  
  
   
</script>


