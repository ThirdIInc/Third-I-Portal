<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="CadreMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="cadMaster.cadreID" />
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Grade
					</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="21%" colspan="1" height="22"><label class="set"
								name="gradename" id="gradename" ondblclick="callShowDiv(this);"><%=label.get("gradename")%></label>
							:<font color="red">*</font></td>
							<td width="75%" colspan="2"><s:textfield
								name="cadMaster.cadreName" size="25" maxlength="45"
								onkeypress="return allCharacters();" /></td>
						</tr>
						<tr>
							<td width="21%" colspan="1" height="22"><label class="set"
								name="gradedescription" id="gradedescription"
								ondblclick="callShowDiv(this);"><%=label.get("gradedescription")%></label>
							:</td>
							<td width="75%" colspan="2"><s:textfield
								name="cadMaster.cadreDesc" maxlength="80"
								onkeypress="return allCharacters();" size="25" /></td>
						</tr>
						<tr>
							<td width="21%" colspan="1" height="22"><label class="set"
								name="gradeabbrevation" id="gradeabbrevation"
								ondblclick="callShowDiv(this);"><%=label.get("gradeabbrevation")%></label>:
							</td>
							<td width="75%" colspan="2"><s:textfield
								name="cadMaster.cadreAbbr" maxlength="8"
								onkeypress="return allCharacters();" size="25" />
							<td width="50%"></td>
						</tr>
						<tr>
							<td width="21%" colspan="1" height="22"><label class="set"
								name="gradeparentname" id="gradeparentname"
								ondblclick="callShowDiv(this);"><%=label.get("gradeparentname")%></label>:
							</td>
							<s:hidden name="cadMaster.cadreParID" />
							<s:hidden name="cadMaster.cadreIsActive" />
							<td width="75%" colspan="2"><s:textfield
								name="cadMaster.cadreParName" maxlength="20" readonly="true"
								size="25" /> <img id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" onclick="callSearch('f9Cadreaction');" /></td>

						</tr>
						<tr>
							<td width="20%" height="22" class="formtext"><label
								name="is.active" class="set" id="is.active"
								ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							:</td>
							<td><s:select name="cadreIsActive"
								list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
							</td>
							<td height="22" class="formtext">&nbsp;</td>
							<td height="22">&nbsp;</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--	Updated by Anantha Laskhmi-->

		<!-- <tr>
			<td width="100%" colspan="3" id='ctrlHide'>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="2" class="formth"><b>OPTIONS</b></td>
				</tr>
				<tr>
					<td width="25%">&nbsp;</td>
					<td width="75%">&nbsp;</td>
				</tr>

				<tr>
					<td width="25%" colspan="1">
						<label class="set"	name="competency" id="competency" ondblclick="callShowDiv(this);"><%=label.get("competency")%></label>:
					<font color="red">*</font></td>
					<td width="75%" colspan="1">
						<s:textarea   cols="10" rows="2" name="competency1" /> 
						<s:hidden  name="hiddenEdit" />
					</td>

				</tr>

				<tr>
					<td width="25%" colspan="1">&nbsp;</td>
					<td colspan="1">
						<s:submit cssClass="add" action="CadreMaster_addItem"
								  theme="simple" value="Add" onclick="return callAdd()" />
					</td>
				</tr>
				<tr>
					<td width="25%">&nbsp;</td>
					<td width="75%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr> -->
		<tr>
			<td width="100%" colspan="3">
			<table class="formbg" width="100%">
				<!-- <tr>
					<td align="center" class="formth" width="10%"><label
						class="set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label>
					</td>
					<td align="center" class="formth" width="40%"><label
						class="set" name="competency" id="competency"
						ondblclick="callShowDiv(this);"><%=label.get("competency")%></label>
					</td>
					<td align="right" class="formth" nowrap="nowrap" width="10%">
					<input type="button" class="token" theme="simple" value="Delete"
						onclick="return chkDelete2()" /></td>
				</tr> -->
				<%
				int count1 = 0;
				%>
				<%!int d1 = 0;%>
				<%
				int ii = 0;
				%>
				<%!int val = 0;%>

				<s:iterator value="list">

					<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
						class="tableCell2" <%	}count1++; %>
						onmouseover="javascript:newRowColor(this);"
						title="Double click for edit"
						onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
						ondblclick="javascript:callForEdit('<s:property value="competency"/>',<s:property value="srNo"/>);">

						<td width="10%" align="center" class="sortableTD"><%=++ii%> <input
							type="hidden" name="srNo" value="<%=ii%>" /></td>
						<td class="sortableTD"><s:property value="competency" />&nbsp;
						<s:hidden name="competency" /></td>
						<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
						<td width="10%" align="center" nowrap="nowrap" class="sortableTD">
						<input type="checkbox" class="checkbox" value="N"
							id="confChkop<%=ii%>" name="confChkop"
							onclick="callForDelete2('<%=ii%>')" /></td>
					</tr>

				</s:iterator>
				<%
				d1 = ii;
				%>
				<input type="hidden" name="hiddenCompetency" id="hiddenCompetency"  value="<%=d1%>" />
				<s:hidden name="subcode" />
				<s:hidden name="tableLength" />

			</table>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>
<script>
	function callAdd()
	{
		var fields=["paraFrm_competency1"];
	  	var val=document.getElementById('paraFrm_competency1').value;
		if(val==" "){
		  alert("Please enter "+document.getElementById('competency1').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_competency1').value='';
		  return false;
		}
		if(!f9specialchars(fields)){
	          return false;
	    }
	    document.getElementById('paraFrm').target = "main";
	    return true;
	}

	 function saveFun()
	 {
		var fieldName = ["paraFrm_cadMaster_cadreName"];
		var lableName = ["gradename"];
		var flag = ["enter"];
	    var cadeName=trim(document.getElementById('paraFrm_cadMaster_cadreName').value);
	    var hidcomtency=document.getElementById('hiddenCompetency').value;
	    if(!f9specialchars(fieldName)){
	           return false;
	    }  
	    if(cadeName==""){
		    alert("please enter "+document.getElementById('gradename').innerHTML.toLowerCase());
		    document.getElementById('paraFrm_cadMaster_cadreName').value="";
		    document.getElementById('paraFrm').target = "main";
		    document.getElementById('paraFrm_cadMaster_cadreName').focus();
		    return false;
	 	}   
	 	
	 	/*if(hidcomtency=="" || hidcomtency=="0"){
		    alert("please enter competency");
		    document.getElementById('paraFrm_competency1').value="";
		    document.getElementById('paraFrm').target = "main";
		    document.getElementById('paraFrm_competency1').focus();
		    return false;
	 	}   */
	 
		 document.getElementById('paraFrm').target = "_self";
		 document.getElementById('paraFrm').action = 'CadreMaster_save.action';
		 document.getElementById('paraFrm').submit();
		 return true;
	}

	function resetFun() {
			document.getElementById('paraFrm_show').value = '1';
	  	 	document.getElementById('paraFrm').target = "_self";
	  	 	document.getElementById('paraFrm').action = 'CadreMaster_reset.action';
			document.getElementById('paraFrm').submit();
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CadreMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		 var con=confirm('Do you want to delete the record(s) ?');
		 if(con){
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'CadreMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'CadreMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	function callForDelete2(id)
	{	
	   var i=eval(id)-1;
	   if(document.getElementById('confChkop'+id).checked == true){	  
	    document.getElementById('hdeleteOp'+id).value=eval(id)-1;
	   }
	   else{
	   	document.getElementById('hdeleteOp'+id).value="";
	   }
   	}
	function chkDelete2()
	{
		 if(chk2()){
			 var con=confirm('Do you want to delete these records');
			 if(con){
			    document.getElementById('paraFrm').action="CadreMaster_deleteDtl.action";
			    document.getElementById('paraFrm').submit();
			    return false;
			  }
			  else{
			    var flag='<%=d1 %>';
			    
			  	for(var a=1;a<=flag;a++){	
			       document.getElementById('confChkop'+a).checked=false;
			       document.getElementById('confChkop'+a).checked="";
			       document.getElementById('hdeleteOp'+a).value="";
			    }
			    return false;
			 }//if close
		 }
		 else {
		 	 alert('Please Select Atleast one Record To Delete');
		 	 return false;
		 }
	}
	function chk2(){
		 var flag='<%=d1 %>';
		  for(var a=1;a<=flag;a++){	
			   if(document.getElementById('confChkop'+a).checked == true){	
			 	    return true;
			   }	   
		  }
		  return false;
	}
	function editFun() {
		return true;
	}	
</script>
