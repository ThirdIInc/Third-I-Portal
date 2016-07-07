<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TrainingTypeMaster" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">	
		<tr>
			<td valign="bottom" class="txt">
				<table  width="100%" border="0"  class="formbg" >
					<tr>
						<td>
							<strong class="text_head"><img	src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />	</strong>
						</td>
						<td width="93%" class="txt">
							<strong class="text_head">Training Type </strong>
						</td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img 	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					    </td>
					</tr> 
				</table>
			</td>
		</tr><!--
		
	   <tr>
			<td colspan="3">
				<table width="100%" border="0"  >
					<tr>
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				        <td width="20%">
							<div align="right"><span class="style2"><font	color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
		       </table>
			</td>
		</tr>
		
		
	    --><tr>
		   <td width="100%">
				<table width="100%" border="0" align="center"  class="formbg">
					<tr>
						<td class="formtext"><label  name="trnName" id="trnName" ondblclick="callShowDiv(this);"><%=label.get("trnName")%></label>:<font color="red">*</font></td>
						<td nowrap="nowrap">
						
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								<s:hidden name="trnCode" />
								<s:textfield name="trnName"/>
								<s:hidden name="trnCreated" />
								<s:hidden name="trnCreatedBy" />
							</s:else>	
						</td>
					</tr>
					<tr>	
						<td class="formtext"><label  class = "set" name="trndes" id="trndes" ondblclick="callShowDiv(this);"><%=label.get("trndes")%></label>:<font color="red">*</font>
						</td>
						<td nowrap="nowrap">
							
							<s:if test="%{editFlag}">
							</s:if>
							<s:else>
								
								<s:textarea name="trndes"  cols="30"  />
								
							</s:else>
							
						</td>
					</tr>
					
					
					
					
					
					
					
					<tr>
						<td  colspan="2" align="center">
						    <s:if test="%{editFlag}">
							</s:if>
							<s:else>
							<!--<input type="button" value="Add New" cssClass="add"  onclick="validate()" ></input>
							--><input type="button" value="Submit" cssClass="add"  onclick="validate()" ></input>
							<input type="button" value="Clear" cssClass="add"  onclick="resetFun()" ></input>
							</s:else>
						</td>
					</tr>
			   </table>
		    </td>
		</tr>
		
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					  	<% int totalPage = 0; int pageNo = 0; %>
					  	<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'TrainingTypeMaster_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'TrainingTypeMaster_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TrainingTypeMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'TrainingTypeMaster_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TrainingTypeMaster_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>						
						</s:if>						
					</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td>
				<table  id="myTable"  width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg" >
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="20%"><label  name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<td class="formth" align="center" width="20%"><label  name="trnName" id="trnName" ondblclick="callShowDiv(this);"><%=label.get("trnName")%></label></td>
							<td class="formth" align="center" width="20%"><label  name="trndes" id="trndes" ondblclick="callShowDiv(this);"><%=label.get("trndes")%></label></font></td>
							<td class="formth" align="center" width="20%"><label  name="created" id="created" ondblclick="callShowDiv(this);"><%=label.get("created")%></label></font></td>
							<td class="formth" align="center" width="20%"><label  name="" id="createdBy" ondblclick="callShowDiv(this);"><%=label.get("createdBy")%></label></font></td>
						<td class="formth" align="center" width="20%" ><label name="deleteEdit"   id="deleteEdit" ondblclick="callShowDiv(this);"><%=label.get("deleteEdit")%></label></td>
						</tr>
						
						<% int cnt = 0;int cn=0;%>
						<s:iterator value="trnTypeList" >
						<tr>
							<td align="center">  
							     <%=++cn%>
							     <s:hidden name="srNo"/>
							</td>
							<td>  
								<s:hidden  name="trnNameIt" />
							    <s:property value="trnNameIt" />
							    <s:hidden name="trnCodeIt" />
							 </td>
							<td>
							
							  <s:hidden name="trnDescIt" />
							  <s:property value="trnDescIt" />
							
							  
							</td>
							
							<td>  
								<s:hidden  name="createdOnIt" />
							    <s:property value="createdOnIt" />
							 </td>
							 
							 
							<td>
							  <s:hidden name="createdByIt" />
							  <s:property value="createdByIt" />
							
							</td>
							
							<td align="center" >
								<input type="button" class="rowEdit" theme="simple"  title="Edit Record"
								       onclick="editRecord(<%=cn %>, '<s:property value="trnNameIt"/>',
								       								 '<s:property value="trnCodeIt"/>',	 
								       								 '<s:property value="trnDescIt"/>',
								       								 '<s:property value="createdOnIt"/>',
								       								 '<s:property value="createdByIt"/>')"/>
								
								<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" 
										onclick="deleteRecord('<s:property value="trnCodeIt"/>')"/>
							</td>	
						</tr>
						
					    </s:iterator>	
					
					</table>
					 
					<tr>
				
				    
				</tr>  
				</td>
				<input type="hidden" name="rowNum" id="rowNum" value="<%=cn%>"/>
				<s:hidden name="hiddenEdit" />     
		</tr>
		<tr></td></tr>
		<s:hidden name="editCode" />
		
		
		<s:hidden name="oldvalue" id="oldvalue"/>   
</table></s:form>



<script> 


 	    


function resetFun(){ 
	
	document.getElementById("paraFrm").action="TrainingTypeMaster_reset.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}

function searchFun(){ 
	
		if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'TrainingTypeMaster_f9action.action';
			document.getElementById("paraFrm").submit();
}


function deleteRecord(trnCode){

	var con=confirm('Do you want to delete the record(s) ?');
		if(con){
	
		document.getElementById('paraFrm_trnCode').value=trnCode; 
	
		document.getElementById("paraFrm").action="TrainingTypeMaster_deleteTrn.action?trnCode="+trnCode;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").submit();
		return true;
		}else{
		return false;
		}
	}


 function editRecord(srNo,trnName,trnCode,trndesc,trnCreatedOn,trnCreatedBy){

    document.getElementById('paraFrm_hiddenEdit').value=srNo;
	document.getElementById('paraFrm_trnName').value = trnName;
	document.getElementById('paraFrm_trndes').value = trndesc;
	document.getElementById('paraFrm_trnCode').value = trnCode;
	document.getElementById('paraFrm_trnCreatedBy').value =trnCreatedBy;
	document.getElementById('paraFrm_trnCreated').value = trnCreatedOn;
}

function validate(){
 var trnName=document.getElementById('paraFrm_trnName').value;
 
	if(trnName==""){
	alert("Please select Training Name");
			return false;
	} else {
			document.getElementById("paraFrm").action="TrainingTypeMaster_submitTrnType.action";
		   	document.getElementById('paraFrm').target = "_self";
		    document.getElementById("paraFrm").submit();
		    return true;
	}
}

</script>