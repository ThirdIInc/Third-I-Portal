<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="EmployeePosting" validate="true" id="paraFrm"
	 theme="simple">
	<form action="" method="post" enctype="multipart/form-data" name="form1" id="form1">
	<s:hidden name="editFlag" />
	<s:hidden name="fromDateCode" id="fromDateCode" />
	<s:hidden name="toDateCode" id="toDateCode" />
	<s:hidden name="updateFlag" />
	<s:hidden name="editDetail" />
	<s:hidden name="paraId" />
	<s:hidden name="empPostingId"/>
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="empName" />
	<s:hidden name="empID" />
	<s:set name="updateFlg" value="updateFlag" />
	<s:set name="deleteFlg" value="deleteFlag" />
	<s:set name="insertFlg" value="insertFlag" />
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
	 <tr>
	  <td>
	   <table width="100%" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		 <td valign="middle">
		  <fieldset>
		   <legend class="legend"><img width="16" height="16" src="../pages/mypage/images/icons/profile_jobposting.png">&nbsp;&nbsp;Job Posting Information</legend>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
			  <td>
		       <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			    <tr>
	            <td></td>
			    </tr>
		        <tr>
				 <td height="0">
				  <table width="100" border="0" align="right" cellpadding="2"cellspacing="3">
				   <s:if test="editFlag">
					<tr>
					 <s:if test="updateFlg">
					  <td width="75%">
					    <a href="#">
					     <img src="../pages/mypage/images/icons/save.png"
						  onclick="callUpdate()" width="10" height="10" border="0" />
						</a>
					  </td>
                      <td width="8%">
                        <a href="#" onclick="callUpdate()" class="iconbutton">Save
                        </a>
                      </td>
					  <td width="3%">|</td>
					</s:if>
					<s:elseif test="insertFlg">
					  <td width="75%">
					    <a href="#">
					     <img src="../pages/mypage/images/icons/save.png"
						  onclick="callUpdate()" width="10" height="10" border="0" />
						</a>
					  </td>
                      <td width="8%">
                       <a href="#" onclick="callUpdate()" class="iconbutton">Save
                       </a>
                      </td>
					  <td width="3%">|</td>
				    </s:elseif>
								<td width="5%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancel()" width="10" height="10" border="0" /></a></td>
												<td width="13%"><a href="#" onclick="cancel()"
													class="iconbutton">Cancel</a></td>
											</tr>
										</s:if>
										<s:else>
										    <tr>
												<s:if test="%{empPosting.insertFlag}">
													<td width="89%" align="right"><a href="#"><img
															src="../pages/mypage/images/icons/add.png"
															onclick="callForAdd()" width="10" height="10" border="0" /></a></td>
															<td><a href="#" onclick="addNew()"
															class="iconbutton">Add</a></td>
																

													</s:if> 
													<s:if test="isNotGeneralUser">
													<td>|</td>
														<td><a href="#"><img
															src="../pages/mypage/images/icons/search.png"
															onclick="searchAction()" width="10" height="10"
															border="0" /></a></td>
															<td><a href="#" onclick="searchAction()"
															class="iconbutton">Search</a></td>
													</s:if>
													</tr>
										
										</s:else>

									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr height="5"></tr>
								<s:if test="editDetail">
									<table width="100%" border="0" align="right" cellpadding="0"
										cellspacing="0">
										<tr>
											<td colspan="9">
											<fieldset><legend class="legend1">
											Posting Particulars </legend>
											<table width="98%" border="0" align="center" cellpadding="2"
												cellspacing="1">
												<tr>
													<td width="22%" height="22"><label name="site.name"
														id="site.name" ondblclick="callShowDiv(this);"><%=label.get("site.name")%></label></td>
													<td width="2%"><font color="red">*</font></td>
													<td width="2%">:</td>
													<td width="20%" height="22" nowrap="nowrap">
													<s:textfield size="28" name="siteName"
														readonly="true" /> &nbsp;<input type="button"
														name="Submit36323" class="button" value="..."
														onclick="empPostAction()" />&nbsp;<s:hidden
														name="siteCode" /></td><td></td>

													<td width="6%"></td>
													<td width="22%" height="22"><label
														name="site.location" id="site.location" ondblclick="callShowDiv(this);"><%=label.get("site.location")%></label>
													</td>
													<td width="2%">:</td>
													<td width="20%" height="22"><s:textfield
														size="28" name="locationName" readonly="true" /><s:hidden
														name="locationCode" value="%{locationCode}" /></td>
												</tr>
												<tr>
													<td width="22%" height="22"><label name="fromDate"
														id="fromDate" ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label></td>
													<td><font color="red">*</font></td>
													<td>:</td>
													<td width="27%" height="22" nowrap="nowrap">
													<s:textfield size="28" name="frmDate" maxlength="10" 
													onkeypress="numbersWithHiphen();"/>&nbsp;<s:a
														href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
														<img src="../pages/images/recruitment/Date.gif"
															class="iconImage" height="16" width="16">
													</s:a></td>
												</tr>
												<tr>
													<td width="22%" height="22"><label name="toDate"
														id="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label></td>
													<td><font color="red">*</font></td>
													<td>:</td>

													<td width="27%" height="2" nowrap="nowrap">
													<s:textfield size="28" name="toDate" maxlength="10" 
													onkeypress="numbersWithHiphen();"/>&nbsp;<s:a
														href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
														<img src="../pages/images/recruitment/Date.gif"
															class="iconImage" height="16" width="16">
													</s:a></td><td></td><td></td>
													<td width="22%" height="10"><label name="description"
														id="description" ondblclick="callShowDiv(this);"><%=label.get("description")%></label></td>
													<td>:</td>
													<td width="30%" rowspan="2" valign="top" class="text1">
													<s:textarea theme="simple" name="empPostingDesc" rows="2"
														cols="25" onkeyup="callLength('descCnt');" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														&nbsp;&nbsp;&nbsp;&nbsp;<img src="../pages/images/zoomin.gif" height="12"
														align="absmiddle" width="12" theme="simple"
														onclick="javascript:callWindow('paraFrm_empPostingDesc','description','','200','200');">
													&nbsp;Remaining chars<s:textfield name="descCnt"
														readonly="true" size="2"></s:textfield></td>
														<td></td>
													<td></td>
												</tr>
											</table>
											</fieldset>
											</td>
										</tr>

									</table>
								</s:if>

								<tr>
									<td>
									<fieldset><legend class="legend1"> Job
									Posting Details</legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="6">
											<table width="98%" border="0" align="center" cellpadding="2"
												cellspacing="1">
												<tr>
													<td width="15%" bgcolor="#EEF4FB"><label
														name="site.name" id="site.name" ondblclick="callShowDiv(this);"><%=label.get("site.name")%></label>
													</td>
													<td width="20%" bgcolor="#EEF4FB"><label
														name="site.location" id="site.location" ondblclick="callShowDiv(this);"><%=label.get("site.location")%></td>
													<td width="15%" bgcolor="#EEF4FB"><label
														name="fromDate" id="fromDate" ondblclick="callShowDiv(this);"><%=label.get("fromDate")%></label></td>
													<td width="15%" bgcolor="#EEF4FB"><label name="toDate"
														id="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label>
													</td>
													<td width="30%" bgcolor="#EEF4FB"><label
														name="description" id="description" ondblclick="callShowDiv(this);"><%=label.get("description")%></label>
													</td>
													<td width="5%" bgcolor="#EEF4FB">Edit/Delete</td>
												</tr>
												<%  int i1=0; %>
												<s:iterator value="postingList" status="stat">
													<tr class="sortableTD">
														<s:hidden name="empPostingIdItt" id='<%="empPostingIdItt" + i1%>'/>
														<td valign="top" class="text1"><s:hidden
															name="siteCodeItt" id='<%="siteCodeItt" + i1%>' /> <s:property
															value="SiteNameItt" /></td>

														<td valign="top" class="text1">&nbsp; <s:property
															value="locationNameItt" /></td>
														<td valign="top" class="text1">&nbsp; <s:property
															value="frmDateItt" /></td>
														<td valign="top" class="text1">&nbsp; <s:property
															value="toDateItt" /></td>
														<td valign="top" class="text1"><s:property
															value="empPostingDescItt" /></td>

														<td><s:if test="updateFlg">
															<img src="../pages/mypage/images/icons/edit.png"
																width="10" height="10" border="0"
																onclick="callForEdit('<s:property value="empPostingIdItt"/>','<s:property value="frmDateItt"/>','<s:property value="toDateItt"/>')" />&nbsp;|&nbsp;		  </s:if>
														<s:if test="deleteFlg">
															<img src="../pages/mypage/images/icons/delete.png"
																width="10" height="10" border="0"
																onclick="callDelete('<s:property value="empPostingIdItt"/>','<s:property value="frmDateItt"/>','<s:property value="toDateItt"/>')" />
														</s:if></td>
													</tr>
													<%i1++; %>
												</s:iterator>
												
											</table>
									</td>
										</tr>
										<s:if test="noData">
											<tr>
												<td colspan="4" align="center"><font color="red">No
												data to display</font></td>
											</tr>
										</s:if>
									</table>
									</fieldset>
									</td>
								</tr>
								
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
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="callUpdate()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="callUpdate()"
														class="iconbutton">Save</a></td>
													<td width="3%">|</td>
												</s:if>
												<s:elseif test="insertFlg">
													<td width="75%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="callUpdate()" width="10" height="10" border="0" /></a></td>

													<td width="8%"><a href="#" onclick="callUpdate()"
														class="iconbutton">Save</a></td>
													<td width="3%">|</td>
												</s:elseif>
												<td width="5%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancel()" width="10" height="10" border="0" /></a></td>
												<td width="13%"><a href="#" onclick="cancel()"
													class="iconbutton">Cancel</a></td>
											</tr>
										</s:if>
										<s:else>
											
												<tr>
												<s:if test="%{empPosting.insertFlag}">
													<td width="89%" align="right"><a href="#"><img
															src="../pages/mypage/images/icons/add.png"
															onclick="callForAdd()" width="10" height="10" border="0" /></a></td>
															<td><a href="#" onclick="addNew()"
															class="iconbutton">Add</a></td>
																

													</s:if> 
													<s:if test="isNotGeneralUser">
													<td>|</td>
														<td><a href="#"><img
															src="../pages/mypage/images/icons/search.png"
															onclick="searchAction()" width="10" height="10"
															border="0" /></a></td>
															<td><a href="#" onclick="searchAction()"
															class="iconbutton">Search</a></td>
													</s:if>
													</tr>
											
										</s:else>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
	</form>
</s:form>

<script>
function callForEdit(id,id2,id3){
	
	  document.getElementById('paraFrm_paraId').value=id;
	  
	    document.getElementById('fromDateCode').value=id2;
	   	document.getElementById('toDateCode').value=id3;	
	   document.getElementById("paraFrm").action="EmployeePosting_edit.action";   
	    document.getElementById("paraFrm").submit();
}
   
   
function callDelete(id,id2,id3){	
  var conf=confirm("Are you sure to delete this record ? ");
  if(conf) {
	    document.getElementById('paraFrm_paraId').value=id;
	    //alert("ID--"+id);
	    document.getElementById('fromDateCode').value=id2;
	   	document.getElementById('toDateCode').value=id3;
	    document.getElementById("paraFrm").action="EmployeePosting_delete.action";
		document.getElementById("paraFrm").submit();
	}
}  

   
   
function callAdd(){
 
  	var empl=document.getElementById('employee').innerHTML.toLowerCase();
	var emp =  document.getElementById('paraFrm_empName').value;
	var qua=document.getElementById('site').innerHTML.toLowerCase();
	var sitename =  document.getElementById('paraFrm_siteName').value;
	var code = document.getElementById('paraFrm_siteCode').value;
	var fdate = document.getElementById('paraFrm_frmDate').value;
	var tdate = document.getElementById('paraFrm_toDate').value;
	var empPostingId= document.getElementById("paraFrm_empPostingId").value;
       
	 if(empPostingId!="") {
	  alert("You cann't Insert. Please Update");
	 return false;
	 } 
		if(emp==""){
		alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}
		
		if(sitename==""){
		alert("Please select "+document.getElementById('site.name').innerHTML.toLowerCase());
		return false;
		}
		if(fdate!=""){
		  if(!validateDate('paraFrm_frmDate','fromDate'))
		  return false; 
		}
		if(tdate!=""){
		  if(!validateDate('paraFrm_toDate','toDate'))
		  return false;
		}
		if(fdate!="" && tdate!=""){
		  if(!dateDifferenceEqual(fdate,tdate,'paraFrm_frmDate', 'fromDate','ToDate'))
		  return false;
		}
			
		var empPostingDesc = document.getElementById('paraFrm_empPostingDesc').value;
		if(empPostingDesc.length > 200){
			alert("Maximum length of "+document.getElementById('description').innerHTML.toLowerCase()+
			" is 200 characters.");
			return false;
		}
					
					    
}

	
function callUpdate(){

		
		//var pre1 = document.getElementById('paraFrm_empPosting_empName').value;
		
		var pre2 = document.getElementById('paraFrm_siteName').value;	
		
        var fdate= document.getElementById('paraFrm_frmDate').value;
      
		var tdate= document.getElementById('paraFrm_toDate').value;
		   
        var code= document.getElementById("paraFrm_siteCode").value;
        var empPostingId= document.getElementById("paraFrm_empPostingId").value;
        document.getElementById('paraFrm_paraId').value= empPostingId;
       

		if((pre2=="")){
			alert("Please select posting name ");
			return false;
		}
				
		
		if(fdate==""){
		alert("Please select/enter from date ");
		return false;
		}
		
	    if(tdate==""){
		alert("Please select/enter to date ");
		return false;
		}

  		if(fdate!="")
		{
		  if(!validateDate('paraFrm_frmDate','fromDate'))
		  return false; 
		}
		if(tdate!="")
		{
		  if(!validateDate('paraFrm_toDate','toDate'))
		  return false;
		}
		if(fdate!="" && tdate!="")
		{
		  if(!dateDifferenceEqual(fdate,tdate,'paraFrm_frmDate', 'fromDate','toDate'))
		  return false;
		}
		
		var empPostingDesc = document.getElementById('paraFrm_empPostingDesc').value;
		if(empPostingDesc.length > 200){
			alert("Maximum length of  posting description  is 200 characters.");
			return false;
		}
		
		document.getElementById("paraFrm").action="EmployeePosting_save.action";
  		document.getElementById("paraFrm").submit();
  		return true;
					
}   
				
function callLength(type){ 
 if(type=='descCnt'){
	var cmt =document.getElementById('paraFrm_empPostingDesc').value;
	var remain = 200 - eval(cmt.length);
	document.getElementById('paraFrm_descCnt').value = remain;
	if(eval(remain)< 0){
		document.getElementById('paraFrm_empPostingDesc').style.background = '#FFFF99';
	}else document.getElementById('paraFrm_empPostingDesc').style.background = '#FFFFFF';
 }
}  



function addNew(){
	document.getElementById("paraFrm").action="EmployeePosting_addNew.action";
	document.getElementById("paraFrm").submit();
}				

function searchAction(){
	javascript:callsF9(500,325,'EmployeePosting_f9empaction.action');
}
function cancel(){
	document.getElementById("paraFrm").action="EmployeePosting_cancelFunc.action";
	document.getElementById("paraFrm").submit();
}				

function empPostAction(){
	javascript:callsF9(500,325,'EmployeePosting_f9site.action');
}
				  
</script>