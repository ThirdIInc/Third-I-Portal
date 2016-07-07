<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CourseSubTypeMaster" id="paraFrm" validate="true" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">	
		<tr>
			<td valign="bottom" class="txt">
				<table  width="100%" border="0"  class="formbg" >
					<tr>
						<td>
							<strong class="text_head"><img	src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" />	</strong>
						</td>
						<td width="93%" class="txt">
							<strong class="text_head">Course Type </strong>
						</td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img 	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					    </td>
					</tr> 
				</table>
			</td>
		</tr>
		
	   <tr>
			<td colspan="3">
				<table width="100%" border="0"  >
					<tr>
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				        <td width="20%">
							<!--<div align="right"><span class="style2"><font	color="red">*</font></span> Indicates Required</div>
						--></td>
					</tr>
		       </table>
			</td>
		</tr>
		
		
	    <tr>
		   <td width="100%">
				<table width="100%" border="0" align="center"  class="formbg">
					<tr>
						<td class="formtext"><label  name="courseSubType" id="courseSubType" ondblclick="callShowDiv(this);"><%=label.get("courseSubType")%></label>:<font color="red">*</font></td>
						<td nowrap="nowrap">
						
							
								
								<s:textfield name="courseSubType"/>
								
							
						</td>
					</tr>
					<tr>	
						<td class="formtext"><label  class = "set" name="courseSubDesc" id="courseSubDesc" ondblclick="callShowDiv(this);"><%=label.get("courseSubDesc")%></label>:<font color="red">*</font>
						</td>
						<td nowrap="nowrap">
							
							
								
								<s:textarea name="courseSubDesc"  cols="30"  />
								
							
							
						</td>
					</tr>
					
					
					
					
					
					
					<tr>
						<td  colspan="2" align="center">
						  				<!--<input type="button" value="Add New" cssClass="add"  onclick="validate()" ></input>
							--><input type="button" value="Submit" cssClass="add"  onclick="validate()" ></input>
							<input type="button" value="Clear" cssClass="add"  onclick="resetFun()" ></input>
						
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
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'CourseTypeMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'CourseTypeMaster_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CourseTypeMaster_callPage.action');" >
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
							<td class="formth" align="center" width="20%"><label  name="courseSubType" id="courseSubType" ondblclick="callShowDiv(this);"><%=label.get("courseSubType")%></label></td>
							<td class="formth" align="center" width="20%"><label  name="courseSubDesc" id="courseSubDesc" ondblclick="callShowDiv(this);"><%=label.get("courseSubDesc")%></label></font></td>
							<td class="formth" align="center" width="20%"><label  name="created" id="created" ondblclick="callShowDiv(this);"><%=label.get("created")%></label></font></td>
							<td class="formth" align="center" width="20%"><label  name="" id="createdBy" ondblclick="callShowDiv(this);"><%=label.get("createdBy")%></label></font></td>
						<td class="formth" align="center" width="20%" ><label name="deleteEdit"   id="deleteEdit" ondblclick="callShowDiv(this);"><%=label.get("deleteEdit")%></label></td>
						</tr>
						
						<% int cnt = 0;int cn=0;%>
						<s:iterator value="courseSubList" >
						<tr>
							<td align="center">  
							     <%=++cn%>
							     <s:hidden name="subSrNoIt"/>
							</td>
							<td>  
								<s:hidden  name="courseSubTypeIt" />
							    <s:property value="courseSubTypeIt" />
							    <s:hidden name="courseSubCodeIt" />
							 </td>
							<td>
							
							  <s:hidden name="courseSubDescIt" />
							  <s:property value="courseSubDescIt" />
							
							  
							</td>
							
							<td>  
								<s:hidden  name="subCreatedOnIt" />
							    <s:property value="subCreatedOnIt" />
							 </td>
							 
							 
							<td>
							  <s:hidden name="subCreatedByIt" />
							  <s:property value="subCreatedByIt" />
							
							</td>
							
							<!--<td align="center" >
								<input type="button" class="rowEdit" theme="simple"  title="Edit Record"
								       onclick="editRecord(<%=cn %>, '<s:property value="courseSubTypeIt"/>',
								       								 '<s:property value="courseSubCodeIt"/>',	 
								       								 '<s:property value="courseSubDescIt"/>',
								       								 '<s:property value="subCreatedOnIt"/>',
								       								 '<s:property value="subCreatedByIt"/>')"/>
								
								<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" 
										onclick="deleteRecord('<s:property value="courseSubCodeIt"/>')"/>-->
										
											<td class="sortableTD" align="center"><img
													style="cursor: pointer;" border="0"
													onclick="editRecord(<%=cn %>, '<s:property value="courseSubTypeIt"/>',
								       								 '<s:property value="courseSubCodeIt"/>',	 
								       								 '<s:property value="courseSubDescIt"/>',
								       								 '<s:property value="subCreatedOnIt"/>',
								       								 '<s:property value="subCreatedByIt"/>')"
													src="../pages/mypage/images/icons/edit.png" /></td>
							<td class="sortableTD" align="center"><img
													style="cursor: pointer;" border="0"
													onclick="deleteRecord('<s:property value="courseSubCodeIt"/>',<%=cn %>);"
									src="../pages/mypage/images/icons/delete.png" /></td><!--
							</td>	
						--></tr>
						
					    </s:iterator>	
					
					</table>
					 
					<tr>
				
				    
				</tr>  
				</td>
				<input type="hidden" name="rowNum" id="rowNum" value="<%=cn%>"/>
				<s:hidden name="hiddenEdit" />     
		</tr>
		<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
		<s:hidden name="editCode" />
		
		<s:hidden name="courseSubCode" />
		
</table></s:form>



<script> 


 	    


function resetFun(){ 
	
	document.getElementById("paraFrm").action="CourseSubTypeMaster_reset.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}

function deleteRecord(courseSubCode){

	var con=confirm('Do you want to delete the record(s) ?');
		if(con){
	
		document.getElementById('paraFrm_courseSubCode').value=courseSubCode; 
	
		document.getElementById("paraFrm").action="CourseSubTypeMaster_deleteCourseSubType.action?courseSubCode="+courseSubCode;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").submit();
		return true;
		}else{
		return false;
		}
	}


 function editRecord(srNo,courseName,courseCode,corseDesc,courseCreatedOn,courseCreatedBy){
	document.getElementById('paraFrm_courseSubCode').value = courseCode;
    document.getElementById("paraFrm").action="CourseSubTypeMaster_editSubType.action?courseSubCode'"+courseCode+"'";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}

function validate(){
 var trnName=document.getElementById('paraFrm_courseSubType').value;
 
	if(trnName==""){
	alert("Please select Training Name");
			return false;
	} else {
			document.getElementById("paraFrm").action="CourseSubTypeMaster_saveCourseSubType.action";
		   	document.getElementById('paraFrm').target = "_self";
		    document.getElementById("paraFrm").submit();
		    return true;
	}
}

</script>