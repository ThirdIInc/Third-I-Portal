<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="SubjectiveMarks" id="paraFrm" validate="true" target="main"	theme="simple">

<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode"  />
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="4" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			    <td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" />
				</td>
		</tr>
		<tr>
				<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" />
				</td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong>
			</td>
			<td width="93%" class="txt"><strong class="formhead">Subjective</strong></td>
			<td width="3%" valign="top" class="txt">
			   <div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" />
			  </div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img	src="../pages/common/css/default/images/space.gif" width="5" height="7" />
			</td>
			
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
		
		<tr>
		<td colspan="4">
			<s:if test="subMarks.insertFlag">
			<s:if test="subMarks.updateFlag"><s:submit  cssClass="save" action="SubjectiveMarks_save"
				theme="simple" value="     Save   " onclick="return callsave();" /></s:if></s:if>
			<s:submit cssClass="reset" action="SubjectiveMarks_reset" theme="simple"
				value="    Reset  " />
			<s:if test="subMarks.deleteFlag">	
			 <s:submit cssClass="delete" action="SubjectiveMarks_delete"
				theme="simple" value="    Delete  " onclick="return callDelete('paraFrm_SubjectiveMarks_centerID');" /></s:if>
				 <input type="button" class="search" value="    Search"
							onclick="javascript:callsF9(500,325,'SubjectiveMarks_f9action.action'); " />
							<s:hidden
				theme="simple"
				name="subMarks.centerID" />
							
			<s:if test="subMarks.viewFlag">
			<input  type="button" class="token"
				onclick="callReport('SubjectiveMarks_report.action')" value="  Report " /></s:if>

			</td>
			<td width="22%">
					<div align="right"><span class="style2">
					<font	color="red">*</font></span> Indicates Required</div>
					</td>
		</tr>
			<tr>
					<td height="5" colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>

			</table>

			<label></label></td>
		</tr>

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
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
		<td colspan="1">From Date:</td>
		<td colspan="1"><s:textfield name="SubjectiveMarks.fromDate"/>
		 <s:a href="javascript:NewCal('paraFrm_SubjectiveMarks_fromDate','DDMMYYYY');">
				<img src="../pages/images/recruitment/Date.gif"
				class="iconImage" height="16" align="absmiddle" width="16">
				</s:a>
		</td>
		<td colspan="1">To Date:</td>
		<td colspan="1"><s:textfield name="SubjectiveMarks.toDate"/>
		<s:a href="javascript:NewCal('paraFrm_SubjectiveMarks_toDate','DDMMYYYY');">
				<img src="../pages/images/recruitment/Date.gif"
				class="iconImage" height="16" align="absmiddle" width="16">
				</s:a>
		</td>
		</tr>
		
		<tr>
		<td colspan="1">Paper:</td>
		<td colspan="3"><s:hidden name="SubMarks.paperCode"/><s:textfield name="subMarks.paperName"/>
		<img src="../pages/images/recruitment/search2.gif" height="18"
			align="absmiddle" width="18"
		onclick="javascript:callsF9(500,325,'SubjectiveMarks_f9Paperaction.action');">
		 </td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	
	<tr>
	
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
			</td>
				</tr>
				
				
				<tr>
					<td class="formth" width="100%">CandidateName</td>
					<b><td class="formth" width="100%">Marks:</td>
					
					
					</b>
					<b><td class="formth" width="100%">MaxMarks:</td>			
					</b>
										
				</tr>
				<tr>
				<td>
					<%int i = 0;%>
					<%int j = 0;%>
					
		  <s:iterator value="showList">
		  <% ++i; %>
						<tr>					
						<td>&nbsp;&nbsp;<b><%=i%>)<s:property   value="%{candidateName}"/></b>	<s:hidden name="candidateName"/></td>
						</tr>
						
						<tr><% int k = 1;%>
						<s:iterator value="showListOption">
							
                        <tr><s:hidden name="quesCode"/><s:hidden name="subjectQuestCode" />	<s:hidden name="testCode" />				
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=k%>)<s:property value="quesDescItt" /> </td>
				
						</tr>
						<tr>	
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="SubjOpt"  rows="01" cols="20"></s:textarea>
					
						</td>
						<td align="right"><input type="text" name="marks"  id="marks<%=i%><%=k%>" value='<s:property value="marks"/>' size="02" onkeypress="return numbersOnly();" onkeyup="condition('<%=i%><%=k%>');"/>
											
						</td>
						<td align="right"><input type="text" name="maxMarks" id="maxMarks<%=i%><%=k%>" value='<s:property value="maxMarks"/>' size="02" onkeypress="return numbersOnly();" onkeyup="condition('<%=i%><%=k%>');" />
						</td>
							</tr>
								<%	++k;%>
						</s:iterator>
								
				</s:iterator>
				
			</td>
				</tr>
			</table>
				
			<table>
			 	
			    	<td><s:submit cssClass="save" action="SubjectiveMarks_save"  theme="simple"  value="    Save   " onclick="return save();" /></td>
			  		
					
					</table>
					</td>
					</tr>
					</table>
					
					
			 </s:form>
 
			
			<script type="text/javascript">
			
			
			function save()
			  {      
			     var val=document.getElementById('paraFrm_SubjectiveMarks_FromDate').value;
			  
			  if(val=="")
			  {
			  alert ('Please Enter FromDate:');
			  
			  return false;
				  }
				         
					
			   var val2=document.getElementById('paraFrm_SubjectiveMarks_ToDate').value;	
			 if(val2==""){
			 
			  alert('Please Enter ToDate:');
			return false;
				}
			      
			   var val3=document.getElementById('paraFrm_SubMarks_paperName').value;
			  
			  if(val3=="")
			  {
			  alert ('Please Enter Paper Name:');
			  return false;
			  }
			   var val4=document.getElementById('paraFrm_SubjectiveMarks_Marks').value;
			  
			  if(val4=="")
			  {
			  alert ('Please Enter Marks:');
			  return false;
			  }
			     return true;
			
			}
			function condition(id)
            
            {
 	  
			  var v1=document.getElementById("marks"+id).value ;
			 
			  var v2=document.getElementById("maxMarks"+id).value;
			 
		      if(eval(v1)>eval(v2))
		      {
		      alert('Marks can not greater than MaxMark:');		     
		      document.getElementById('paraFrm_SubMarks_Marks').focus();
		      return false;
		 }
		 
		}
		
		
		</script>

