<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<body onload="callOnLoad();">
<s:form action="Suggestion" validate="true" id="paraFrm" theme="simple">
	<table class="bodyTable" width="100%" >
		<tr>
			<td width="100%" colspan="4" class="text_head" align="center">Suggestion</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4" class="sectionHead">Suggestion Of
			The Day</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="1" width="25%">Enter Suggestion :</td>

			<td colspan="3" width="65%"><s:textfield size="60" name="sugg.suggestion" />
			<s:hidden name="hiddenCode_sg" />&nbsp;
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td align="center" colspan="3"><s:submit cssClass="pagebutton"
				action="Suggestion_addSuggestion" theme="simple" value="   Add   "
				onclick="return check();" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>


	<table class="bodyTable" width="100%">
		<tr>
			<td class="headercell" width="5%" colspan="1">Sr. No.</td>
			<td class="headercell" width="75%" colspan="2">Suggestion</td>
			<td class="headercell" width="20%" colspan="1"></td>

		</tr>
		<% int i=0; %>

		<s:iterator value="list_suggestion">

			<tr>

				<td class="bodycell" width="10%" colspan="1"><%= ++i %></td>
				<td class="bodycell" width="60%" colspan="2"><s:hidden
					value="suggestionCode" /> <s:property value="suggestionSub" /><s:hidden
					value="suggestionFlag" /></td>
				<td class="bodycell" width="30%" colspan="1"><input
					type="button" class="pagebutton" value="Edit"
					onclick="callForEdit('<s:property value="suggestionCode"/>','Suggestion_editSuggestion.action')" />
				<input type="button" class="pagebutton" value="Delete"
					onclick="callForDelete('<s:property value="suggestionCode"/>','Suggestion_deleteSuggestion.action')" />
				</td>
			</tr>
		</s:iterator>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
var suggestionLab=document.getElementById('suggestion').innerHTML.toLowerCase();
function check()
{
var val=document.getElementById('paraFrm_sugg_suggestion').value;
	var value = LTrim(RTrim(val));
	if(val!=value)
	{alert('Blank space not allowed !');
	return false;
	}
	

if(document.getElementById('paraFrm_sugg_suggestion').value=="")
{
alert('Please enter '+suggestionLab);
return false;
}
return true;
}

var fieldName = ['sugg.suggestion'];
var labelName = ['Suggestion '];
 function callForEdit(editCode,action,hiddenCodeTxt)
	{	
		document.getElementById('paraFrm_hiddenCode_sg').value = editCode;
		document.getElementById('paraFrm').action=action;
	    document.getElementById('paraFrm').submit();
	}
	
	function callForDelete(delCode,action,hiddenCodeTxt)
	{
		
	
  		
  		
  			var conf=confirm("Are you sure to delete this record?");
  			if(conf) {
  				document.getElementById('paraFrm_hiddenCode_sg').value = delCode;
  				document.getElementById('paraFrm').action =action;
				document.getElementById('paraFrm').submit();
				return true;
  			}
  			else
  			{
  				document.getElementById('paraFrm').submit();
  				return false;
  			}
		
		
  		
  			
  			
		
		
	}
	function addValidate()
	{
   					
		var val = document.getElementById('paraFrm_suggestion').value;
		alert("value=="+document.getElementById("suggestion").value);
		if(val=="")
		{
			alert("Please enter "+suggestionLab+" and click Add.");
			return false;
		}
		
	}
</script>