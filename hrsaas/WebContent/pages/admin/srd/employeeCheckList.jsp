<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<!--
@modified by priyanka. kumbhar
@modified on 23-01-2013
-->
<!--
	     Start of main fieldset, table and form
-->
<s:form action="EmployeeCheckList" validate="true" id="paraFrm"
	 theme="simple" >
	<!--
	    employee id used for selecting a particular list of that employee.
	    isNotGeneralUser flag used for checking whether logged employee is a general user or not.
	    set the insert flag for inserting values into the employee record.
	-->
	<s:hidden name="employeeCheckList.empID" />
	<s:hidden name="isNotGeneralUser" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<fieldset><legend class="legend"><img
		src="../pages/mypage/images/icons/profile_documentsubmit.png"
		width="16" height="16" />&nbsp;&nbsp;&nbsp;Documents Submitted</legend>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

		<tr>
			<td height="0">
			<table width="100" border="0" align="right" cellpadding="2"
				cellspacing="3">
				<!-- check if editflag is true which will enable ADD and SEARCH action for the user.
                 -->
				<s:if test="editFlag">
					<tr>
						<!-- check if updateFlag is true which will enable ADD action for the user.
                         -->
						<s:if test="updateFlg">
							<td width="89%" align="right"><a href="#"><img
								src="../pages/mypage/images/icons/add.png" width="10"
								height="10" border="0" onclick="addfun()" /></a></td>
							<td><a href="#" onclick="addfun()" class="iconbutton">Edit</a></td>
							<td>|</td>
						</s:if>
						<!-- check if IsNotGeneralUser is true which will enable SEARCH action for the user.
                         -->
						<s:if test="IsNotGeneralUser">
							<td><a href="#"><img
								src="../pages/mypage/images/icons/search.png" width="10"
								height="10" border="0" onclick="searchFun()" /></a></td>
							<td><a href="#" onclick="searchFun()" class="iconbutton">Search</a></td>
						</s:if>
					</tr>
				</s:if>
				<!--
				else enable SAVE and CANCEL action
				-->
				<s:else>
					<tr>
						<td width="18%"><a href="#"><img
							src="../pages/mypage/images/icons/save.png" width="10"
							height="10" border="0" onclick="saveFun()" /></a></td>
						<td width="18%"><a href="#" onclick="saveFun()"
							class="iconbutton">Save</a></td>
						<td width="18%">|</td>
						<td width="18%"><a href="#"><img src="../pages/mypage/images/icons/cancel.png"
						onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
						<td width="18%"><a href="#" onclick="cancelFun()"
						class="iconbutton">Cancel</a></td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
		<tr>
			<td height="1" bgcolor="#cccccc" class="style1"></td>
		</tr>
		<!--
            if the editflag is true display the list available of that particular employee.
		-->
		<s:if test="editFlag">


			<tr>
				<td>
				<fieldset><legend class="legend1">Document
				Details</legend>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="border">
					<!--
                      retrieve the table heading from the property file as labels
					-->
					<tr>
						<td width="25%" height="20" bgcolor="#EEF4FB"><label
							name="check.name" id="check.name" ondblclick="callShowDiv(this);"><%=label.get("check.name")%></label></td>
						<td width="40%" height="20" bgcolor="#EEF4FB"><label
							name="check.description" id="check.description"
							ondblclick="callShowDiv(this);"><%=label.get("check.description")%></label>
						</td>
						<td width="15%" height="20" bgcolor="#EEF4FB"><label
							name="check.submit" id="check.submit"
							ondblclick="callShowDiv(this);"><%=label.get("check.submit")%></label></td>
						<td width="25%" height="20" bgcolor="#EEF4FB"><label
							name="view.document" id="view.document"
							ondblclick="callShowDiv(this);"><%=label.get("view.document")%></label></td>
					</tr>
					<!--
					  through hashmap retrieve the value set in the model display the list
					-->
					<%
					try {
					%>
					<%
							java.util.HashMap hmChk = (java.util.HashMap) request
							.getAttribute("chkText");
					%><!--
                   counter variables
					--><%
					int i = 0;
					%>
					<%!int g = 1;%>
					<%
					int b = 1;
					%>

					<s:iterator value="employeeCheckList.chkList">
						<%
						String chk = (String) hmChk.get("" + i);
						%>
						<tr class="text1">

							<td width="30%"><s:hidden name="chkId" /> <s:property
								value="chkName" /></td>

							<td width="40%"><s:property value="chkDesc" /></td>


							<td width="15%"><s:property value="chkSubmit" /></td>

							<td>

                          

							<table>

								<s:iterator value="uploadList">
									<tr>
										<td width="15%" class="border"><a href="#"
											onclick="javascript:showRecord('<s:property value="uploadFileName"/>');"
											title="Click to view file"> <s:property
											value="uploadFileName" /></a></td>

									</tr>
								</s:iterator>
							</table>


							</td>
						</tr>

						<tr>
							<td colspan="5" valign="top" class="text1">
							<hr size="1" color="#f2f2f2" />
							</td>
						</tr>


						<%
								i++;
								b++;
						%>
					</s:iterator>


					<%
							g = b;
							b = 1;
					%>
					<%
						} catch (Exception e) {
						}
					%>
					<input type="hidden" name="count" value="<%=g-1%>" />
				</table>
				</td>
			</tr>
		</s:if>
		<!-- ---------------------------else inside the editable mode-------- -->
		<s:else>
			<tr>
				<td>
				<fieldset><legend class="legend1">Document
				Submitted</legend>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="border">

					<tr>
						<td width="25%" height="20" bgcolor="#EEF4FB"><label
							name="check.name" id="check.name" ondblclick="callShowDiv(this);"><%=label.get("check.name")%></label></td>
						<td width="20%" height="20" bgcolor="#EEF4FB"><label
							name="check.description" id="check.description"
							ondblclick="callShowDiv(this);"><%=label.get("check.description")%></label>(Maximum
						characters:400)</td>
						<td width="15%" height="20" bgcolor="#EEF4FB"><label
							name="check.submit" id="check.submit"
							ondblclick="callShowDiv(this);"><%=label.get("check.submit")%></label></td>
						<td width="15%" height="20" bgcolor="#EEF4FB"><label
							name="attach.proof" id="attach.proof"
							ondblclick="callShowDiv(this);"><%=label.get("attach.proof")%></label></td>
						<td width="35%" height="20" bgcolor="#EEF4FB"><label
							name="view.document" id="view.document"
							ondblclick="callShowDiv(this);"><%=label.get("view.document")%></label></td>

					</tr>
					<%
					try {
					%>
					<%
							java.util.HashMap hmChk = (java.util.HashMap) request
							.getAttribute("chkText");
					%><!--
                Counter variables
					--><%
					int i = 0;
					%>
					<%!int g1 = 1;%>
					<%
					int b = 1;
					%>

					<s:iterator value="employeeCheckList.chkList">
						<%
						String chk = (String) hmChk.get("" + i);
						%>
						<tr class="text1">
										
								<td width="10%"><s:hidden name="chkId" /><s:property
									value="chkName" /></td>
								<td width="10%"><!--<textarea cols="30" rows="3"
									name="chkDesc" id=''
									onkeyup="calculateRemainingChars(');"  ></textarea> --><s:textarea cols="30" rows="3"
									name="chkDesc" id="chkDesc<%=b%>" 
									onkeydown="calculateRemainingChars1(this, 400);" onkeyup="calculateRemainingChars1(this, 400);" ></s:textarea><input
									type="hidden" name="hiddenName" id='<%="hiddenName"+b%>'
									value='<s:property value="chkName" />'>
									<!--<img src="../pages/images/zoomin.gif" height="12"
														align="absmiddle" width="12" theme="simple"
														onclick="javascript:callWindow('paraFrm_chkDesc','check.description','','200','200');">
									Remaining Chars:
									<s:textfield name='<%="descCnt"+b%>' id='<%="descCnt"+b%>'  readonly="true" size="2"></s:textfield>--></td>
								<td width="10%"> <s:select name="chkSubmitTest"
													list="#{'NA':'NA','Pen':'Pending','Yes':'Yes'}" /></td>
													
							   <td width="20%">
					 				<input name="employeeCheckList.uploadFileName"  
									value='<s:property value="uploadFileName<%=i%>" />'
									id="uploadFileName<%=i%>" size="15" readonly="true"/> <br>&nbsp;&nbsp;
									<input
									type="button" class="token" value="Upload"
									onclick="uploadFile('uploadFileName<%=i%>');" size="15"/> 
									<input type="button" value="Add" Class="add" size="15"
									onclick="return callUpload('uploadFileName<%=i%>',<%=i%>);"> 
									
									</td>  
				 
							 <td colspan="3"> <s:hidden name="uploadFileNameNew" id='<%="uploadFileNameNew"+i%>'/>
							  
								<table width="100%" border="0" cellpadding="0" cellspacing="0" id="<%="uploadTable"+i%>" >
			
									<%
									int counter = 1;
														
									%>
						
									<tr><td>
										<s:iterator value="uploadList"><s:hidden name="myFlag" id="myFlag"/>
										<tr class="text1">
											<td width="188px"><s:hidden name="uploadFileName"  id='<%="uploadFileName"+i+counter%>'  />
											<a href="#"
											onclick="return showRecord('<s:property value="uploadFileName" />');" title="Click to view file">
											<s:property value="uploadFileName" />
											</td>
											<s:if test="deleteFlg">
											<td><img src="../pages/mypage/images/icons/delete.png" 
											onclick="deleteCurrentRow(this,<%=counter%>,<%=i%>);" title="Remove file"></td>
											</s:if>
										</tr>
										<%counter++;%>
						 			</s:iterator> 
								  </td></tr>
					   			</table>
					      </td>
			          </tr>
						<tr>
							<td colspan="5" valign="top" class="text1">
							<hr size="1" color="#f2f2f2" />
							</td>
						</tr>
						<%
								i++;
								b++;
						%>

					</s:iterator>
					<%
							g1 = b;
							b = 1;
					%>
					<%
						} catch (Exception e) {
						}
					%>
					<input type="hidden" name="count" value="<%=g-1%>" />

				</table>
				</fieldset>
				</td>
			</tr>
		</s:else>

		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td height="1px" bgcolor="#cccccc"></td>
		</tr>
		<tr>
			<td height="0">
			<table width="100" border="0" align="right" cellpadding="2"
				cellspacing="3">

				<s:if test="editFlag">
					<tr>
						<s:if test="updateFlg">

							<td width="89%" align="right"><a href="#"><img
								src="../pages/mypage/images/icons/add.png" width="10"
								height="10" border="0" onclick="addfun()" /></a></td>
							<td><a href="#" onclick="addfun()" class="iconbutton">Edit</a></td>
							<td>|</td>
						</s:if>

						<s:if test="IsNotGeneralUser">
							<td><a href="#"><img
								src="../pages/mypage/images/icons/search.png" width="10"
								height="10" border="0" onclick="searchFun()" /></a></td>
							<td><a href="#" onclick="searchFun()" class="iconbutton">Search</a></td>
						</s:if>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="18%"><a href="#"><img
							src="../pages/mypage/images/icons/save.png" width="10"
							height="10" border="0" onclick="saveFun()" /></a></td>
						<td width="18%"><a href="#" onclick="saveFun()"
							class="iconbutton">Save</a></td>
						<td width="18%">|</td>
						<td width="18%"><a href="#"><img src="../pages/mypage/images/icons/cancel.png"
						onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
						<td width="18%"><a href="#" onclick="cancelFun()"
						class="iconbutton">Cancel</a></td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
	</table>
	</fieldset>
</s:form>
<!--
 End of main fieldset, table and form 
-->
<script>

 //addfun() function to add/update the record
 
function addfun(){
  document.getElementById('paraFrm').action='EmployeeCheckList_addnew.action';  
   document.getElementById('paraFrm').submit();
}


 //savefun() function to save the updated or added record
 
function saveFun(){
try{
        var documentUpload= document.getElementById('uploadFileName').value;
        
	  	document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="EmployeeCheckList_save.action";
		
		document.getElementById("paraFrm").submit();
	}catch(e){alert("Error in save()--->"+e);}
  }  
  
  //searchfun() function to search the records of a particular employee
 function searchFun(){
   
  	try{
  			  javascript:callsF9(500,325,'EmployeeCheckList_f9empaction.action');  
  		}catch(e){
  		alert(e);  
  		}	   
  			  
  }
  //cancelfun() function to go back to the previous page 
  
function cancelFun(){      
  	try{
	  	document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="EmployeeCheckList_cancelFunc.action";
		document.getElementById("paraFrm").submit();
	}catch(e){alert(e);}
  }
 
  <% int loop=g;
   
  %>
 var loop=<%=loop%>;
 
 setBtns();
 

function setBtns()
{
;  for(var x=0;x<loop;x++)
  {
  var text=document.getElementById('uploadFileName'+x).value;
  if(text=="")
	  {
	   document.getElementById('showBtn'+x).style.display='none';
	   document.getElementById('uploadBtn'+x).style.display='';
	  }
	  else
	  {
	   document.getElementById('showBtn'+x).style.display='';
	   document.getElementById('uploadBtn'+x).style.display='none';
	  }
  }
}
//showRecord function used for dispalying the uploaded file 

function showRecord(fieldName)
{
    if(fieldName==""){
		alert('File not available');
		return false;
		}
	var path='<%=session.getAttribute("session_pool")%>';
	window.open('../pages/images/'+path+'/checkList/'+fieldName);
}
  //to limit the length of string entered in the description part
  
//to upload gif,jpg,png etc file from the system

function uploadFile(fieldName) 
{
 	document.getElementById(fieldName).focus();
  	var path="images/<%=session.getAttribute("session_pool")%>/checkList";
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
 	 document.getElementById('paraFrm').target="main";
 
 
}
	
	//to add the uploaded file to the list
	
  function callUpload(fieldName,id){
		try{
			var uploadFile = document.getElementById(fieldName).value;
		    
		  if(uploadFile==""){
			  alert("Please select file to upload");
		  return false;
	  }
		  
		  var tbl = document.getElementById('uploadTable'+id);
		  var lastRow = tbl.rows.length;
		  var iteration = tbl.rows.length;
		  var row = tbl.insertRow(lastRow);
		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		  cell0.appendChild(column0);
	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'uploadFil'+id;
		  column1.value = uploadFile; /*value to be set in the added cell*/
		  column1.id = 'uploadFil'+iteration;
		  column1.size='20';
		  column1.readOnly='true';
		  column1.maxLength='50';
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/mypage/images/icons/delete.png" ;
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';

		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this,iteration,iteration);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);
	 
		}catch(e){
		alert(e);
		}
		document.getElementById('uploadFileName'+id).value="";
		
	
	}
	function calculateRemainingChars(type,i)
	{
	if(type=='descCnt'+i)
	{
	   var offence = document.getElementById('chkDesc'+i).value;
	   var remainChar = 400- eval(offence.length);
	   document.getElementById('descCnt'+i).value = remainChar;
	if(eval(remainChar)< 0){
		document.getElementById('chkDesc'+i).style.background = '#FFFF99';
		
	}
	else document.getElementById('chkDesc'+i).style.background = '#FFFFFF';  
	}
}
 
function deleteCurrentRow(obj,loc,i){
 var allValue=document.getElementById('uploadFileNameNew'+i).value;
 var  oneValue=document.getElementById('uploadFileName'+i+loc).value;
 var  newValue="";
  var doIt=confirm('Do you want to delete the file?');
 if(doIt)
 {
   
       xyz=allValue.split(",");
                    for(k=0; k<(xyz.length); k++){
                           if(xyz[k]==oneValue){
                           oneValue="";
                           newValue+=oneValue;
                           }else{                           
							newValue+=xyz[k]+",";
						   }
						}
				
				document.getElementById('uploadFileNameNew'+i).value=newValue;	


		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		}
		
	}
	function calculateRemainingChars1(limitField, limitNum) {
    if (limitField.value.length > limitNum) {
        limitField.value = limitField.value.substring(0, limitNum);
        alert("Maximum 400 characters only");
        limitField.focus();
    } 
}
 </script>