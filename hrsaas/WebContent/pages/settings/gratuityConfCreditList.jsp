<!-- @author: Reeba Joseph @date: 04 FEB 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="GratuityConfig" id="paraFrm" validate="true" theme="simple"
	target="main">
	<table width="100%">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Credit
					List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<s:hidden name="creditCodeHidNext" />
					<s:hidden name="creditAbbrHidNext" />
					<td align="center" width="70%" class="formth">Credit Name</td>
					<td align="center" width="30%" class="formth">Select</td>
				</tr>
				<%int i = 1;%>
				<%!int j = 0;%>
				<s:iterator value="creditDataList">
					<tr>
						<input type="hidden" name="creditAbbr" id="creditAbbr<%=i%>"
							value='<s:property value="creditAbbr"/>' />
						<td class="border2"><input type="hidden" name="creditName"
							value='<s:property value="creditName"/>' id="creditName<%=i%>" /> <s:property
							value="creditName" /><input type="hidden" name="creditCode" id="creditCode<%=i%>"
							value='<s:property value="creditCode"/>' /></td>
						<td align="center" class="border2"><!-- <input type="checkbox"
							name="check" id="check<%=i%>" value='<s:property value="check"/>' /> -->
							<s:checkbox name="check" id="<%="checks"+i%>" onclick="callCheck();"/>
							<input type="hidden" id="check<%=i%>" value='<s:property value="check"/>' />
						<s:hidden name="checkFlag" id="checkFlag<%=i%>"/>
						</td>
						
						
					</tr>
					<%i++;%>
				</s:iterator>
				<%j = i;%>
			</table>
			</td>
		</tr>
		<tr>
			<td><s:hidden name="srNo" /> <input type="button" class="token"
				name="Ok" value="   OK   " onclick="selectCredit();" /></td>
		</tr>
	</table>
</s:form>

<script>

var creditCode = document.getElementById('paraFrm_creditCodeHidNext').value;
var creditAbbr = document.getElementById('paraFrm_creditAbbrHidNext').value;

function callCheck(){
	try{
	var count = '<%=j%>'; 
	var isChecked = "";
	for(var i=1; i<count; i++){
		//alert(document.getElementById('checks'+i).checked);
		if(document.getElementById('checks'+i).checked){
			document.getElementById('check'+i).value="true";
		}else{
			document.getElementById('check'+i).value="false";
		}
	}
	}catch(e){
		alert(e);
	}
}

function selectCredit(){
	var count = '<%=j%>'; 
	var isChecked = "";
	var selectedCreditCode = "";
	var selectedCreditAbbr = "";
	
	try{
   		for(var i=1; i<count; i++){
   			isChecked = trim(document.getElementById('check'+i).value);
   			if(isChecked == "true"){
	   			selectedCreditCode += document.getElementById('creditCode'+i).value+",";
	   			selectedCreditAbbr += trim(document.getElementById('creditAbbr'+i).value)+",";
   			}
   		}
	   	selectedCreditCode = selectedCreditCode.substring(0, selectedCreditCode.length-1);
	    selectedCreditAbbr = selectedCreditAbbr.substring(0, selectedCreditAbbr.length-1);
		
		opener.document.getElementById('paraFrm_'+creditCode).value = selectedCreditCode;
		opener.document.getElementById(creditAbbr).value = selectedCreditAbbr;
	}
	catch(e){
   		alert(e);
   	}
	window.close();
}

function setInitialValue(){
	 var count  = '<%=j%>'; 
     var oldvalue = opener.document.getElementById(creditAbbr).value;
     fieldList  = oldvalue.split(",");
     for(k = 1; k <= count; k++){
     	for(i=0; i < fieldList.length;i++) {
      		if(trim(document.getElementById('creditAbbr'+k).value) == fieldList[i]){
      			document.getElementById('checks'+k).checked = true;
      			document.getElementById('check'+k).value = "true";
     		 }
  		}
     }
}

setInitialValue();
</script>
