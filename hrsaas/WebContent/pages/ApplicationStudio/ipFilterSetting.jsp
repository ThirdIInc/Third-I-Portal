<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<%@page import="java.util.HashMap"%>

<s:form action="IPFilter" method="post" name="IPFilter" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr valign="top">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td><strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">IP Filtering</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td width="78%"><s:submit cssClass="save" action="IPFilter_save" value=" Save" onclick="return callValidate();" /></td>
						<td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span>Indicates Required</div></td>
					</tr>	
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
				<table class="formbg" width="100%">
					<tr>
						<td width="20%" colspan="1" class="txt">
							<strong class="formhead"><label name="ipFilterApp" id="ipFilterApp" ondblclick="callShowDiv(this);"><%=label.get("ipFilterApp") %></label></strong> :
						</td>
						<td width="80" colspan="5"><s:checkbox name="ipFilterFlag" id="ipFilterFlag" onclick="return isApplicable();" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">
				<div id="org" style="width: 100%; border: 1px">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">		
						<tr>
							<td width="20%" colspan="1" class="txt">
								<strong class="formhead">
									<label name="organisationwise" id="organisationwise" ondblclick="callShowDiv(this);"><%=label.get("organisationwise") %></label> :
								</strong>
							</td>
							<td width="80%" colspan="3" align="left"><s:checkbox name="orgWiseCheck" id="orgWiseCheck" onclick="return orgWise();" /></td>
						</tr>		
						<tr>
							<td width="100%" colspan="4">
								<div id="orgText" style="width: 100%; border: 1px; display: none">
									<table width="100%">
										<tr>
											<td width="15%" colspan="1">
												<label name="from.ipaddress" id="from.ipaddress" ondblclick="callShowDiv(this);"><%=label.get("from.ipaddress") %></label> :
											</td>
											<td width="20%" colspan="1">
												<s:textfield name="orgFrom" maxlength="15" value="%{orgFrom}" onkeypress="return numbersWithDot();" />
											</td>
											<td width="15%" colspan="1">
												<label name="to.ipaddress" id="to.ipaddress" ondblclick="callShowDiv(this);"><%=label.get("to.ipaddress") %></label> :
											</td>
											<td width="100%" colspan="1">
												<s:textfield name="orgTo" maxlength="15" value="%{orgTo}" onkeypress="return numbersWithDot();" />
												<s:submit cssClass="add" value="Add Ranges" action="IPFilter_addOrgIpRange" onclick="return addRange();" />
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
								<div id="orgList" style="width: 100%; border: 1px; display: none">
									<table width="100%" cellpadding="1" cellspacing="1" class="sortable">
										<tr>
											<td valign="top" class="formth"><label name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no") %></label></td>
											<td colspan="1" valign="top" class="formth"><label name="from.ipaddress" id="from.ipaddress1" ondblclick="callShowDiv(this);"><%=label.get("from.ipaddress") %></label></td>
											<td colspan="1" valign="top" class="formth"><label name="to.ipaddress" id="to.ipaddress1" ondblclick="callShowDiv(this);"><%=label.get("to.ipaddress") %></label></td>
											<td valign="top" class="formth">
												<div align="center">
													<input type="checkbox" name="checkMe" id="checkMe" onclick="return selectAllOrg();" />
													<input type="button" class="delete" value="Remove" onclick="return deleteOrgIp();" />
												</div>
											</td>
										</tr>
										<%!int k = 0;%>
										<%
											int y = 0;
											int i = 0;
											HashMap afdata = (HashMap) request.getAttribute("data");
										%>
										<s:iterator value="list">
											<%
												if (afdata == null) {
													afdata = new HashMap();
													afdata.put("" + i, "G");
												}
											%>
											<tr class="border2">
												<td width="10%" class="border2"><%=++y%><s:hidden name="srNo" /></td>
												<td width="35%" class="border2"><s:property value="fromIpAdd" /><s:hidden name="fromIpAdd" /></td>
												<td width="35%" class="border2"><s:property value="toIpAdd" /><s:hidden name="toIpAdd" /></td>
												<td width="20%" class="border2">
													<div align="center">
														<input type="checkbox" class="checkbox" id="<%="leaveChk"+i%>" name="leaveChk" value="<%=i%>" onclick="callChk()" />
														<input type="hidden" name="pmChkFlag" id="<%=i%>" value='G' />
													</div>
												</td>
											</tr>
											<%
												i++;
											%>
										</s:iterator>
										<%
											k = i;
										%>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">
				<div id="branch" style="width: 100%; border: 1px; display: none">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
						<tr>		
							<td width="20%" colspan="1" class="txt">
								<strong class="formhead">
									<label name="branchwiseip" id="branchwiseip" ondblclick="callShowDiv(this);" ><%=label.get("branchwiseip") %></label> :
								</strong>
							</td>
							<td width="80" colspan="5"><s:checkbox id="branchWiseCheck" name="branchWiseCheck" onclick="return brnWise();" /></td>
						</tr>
						<tr id="selBrn">		
							<td colspan="1" width="20%"><label name="branch" id="branch1" ondblclick="callShowDiv(this);" ><%=label.get("branch") %></label>:</td>
							<td colspan="3" width="100%">
								<s:textfield name="branchName" readonly="true" size="30" cssStyle="background-color: #F2F2F2;" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
								onclick="callsF9(500,325,'IPFilter_f9BranchName.action');">
								<s:hidden name="branchCode" />
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
								<div id="selBrnText" style="width: 100%; border: 1px; display: none">
									<table width="100%">
										<tr>
											<td width="15%" colspan="1">
												<label name="from.ipaddress" id="from.ipaddress2" ondblclick="callShowDiv(this);" ><%=label.get("from.ipaddress") %></label>:
											</td>
											<td width="20%" colspan="1">
												<s:textfield name="branchFrom" maxlength="15" value="%{branchFrom}" onkeypress="return numbersWithDot();" />
											</td>
											<td width="15%" colspan="1">
												<label name="to.ipaddress" id="to.ipaddress2" ondblclick="callShowDiv(this);" ><%=label.get("to.ipaddress") %></label>:
											</td>
											<td width="100%" colspan="1">
												<s:textfield name="branchTo" maxlength="15" value="%{branchTo}" onkeypress="return numbersWithDot();" />
												<s:submit cssClass="add" value="Add Ranges" action="IPFilter_addBranchIpRange" onclick="return addBranchIpAdd();" />
											</td>		
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
								<div id="branchList" style="width: 100%; border: 1px; display: none">
									<table width="100%" cellpadding="1" cellspacing="1" class="sortable">
										<tr>
											<td valign="top" class="formth"><label name="sr.no" id="sr.no2" ondblclick="callShowDiv(this);" ><%=label.get("sr.no") %></label></td>
											<td valign="top" colspan="1" class="formth"><label name="branch" id="branch2" ondblclick="callShowDiv(this);" ><%=label.get("branch") %></label></td>
											<td colspan="1" valign="top" class="formth"><label name="from.ipaddress" id="from.ipaddress3" ondblclick="callShowDiv(this);" ><%=label.get("from.ipaddress") %></label></td>
											<td colspan="1" valign="top" class="formth"><label name="to.ipaddress" id="to.ipaddress3" ondblclick="callShowDiv(this);" ><%=label.get("to.ipaddress") %></label></td>
											<td valign="top" class="formth">
												<div align="center">
													<input type="checkbox" name="checkMe" id="checkMeBrn" onclick="return selectAllBrn();" />
													<input type="button" class="delete" value="Remove" onclick="return deleteBrnIp();" />
												</div>
											</td>
										</tr>
										<%!int kk = 0;%>
										<%
											int j = 0;
											int q = 0;
											HashMap afdataQual = (HashMap) request.getAttribute("dataBranch");
										%>
										<s:iterator value="branchList">
											<%
													if (afdataQual == null) {
													afdataQual = new HashMap();
													afdataQual.put("" + q, "Q");
												}
											%>
											<tr class="border2">
												<td width="10%" class="border2"><%=++j%><s:hidden name="srNoBr" /></td>
												<td width="30%" class="border2">
													<s:property value="brnName" /><s:hidden name="brnName" /><s:hidden name="brnCode" />
												</td>
												<td width="20%" class="border2">
													<s:property value="branchfromIpAdd" /><s:hidden name="branchfromIpAdd" />
												</td>
												<td width="20%" class="border2">
													<s:property value="branchtoIpAdd" /><s:hidden name="branchtoIpAdd" />
												</td>
												<td width="20%" class="border2">
													<div>
														<input type="checkbox" class="checkbox" id="<%="leaveChkBrn"+q%>" name="leaveChkBrn" value="<%=q%>"
														onclick="callChk1()" />
														<input type="hidden" name="pmChkFlagBrn" id="<%=q%>" value='Q' />
													</div>
												</td>
											</tr>
											<%
												q++;
											%>
										</s:iterator>
										<%
											kk = q;
										%>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr valign="top" height="300px">
			<td width="100%" colspan="4">
				<div id="emp" style="width: 100%; border: 1px">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4" class="txt">
								<strong class="formhead">
									<label name="exceptionalemp" id="exceptionalemp" ondblclick="callShowDiv(this);" ><%=label.get("exceptionalemp") %></label>
								</strong> :
							</td>		
						</tr>
						<tr>
							<td colspan="1" width="20%"><label name="employee" id="employee" ondblclick="callShowDiv(this);" ><%=label.get("employee") %></label>:</td>
							<td colspan="3" width="80%">
								<s:textfield name="empName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
								onclick="callsF9(500,325,'IPFilter_f9EmployeeName.action');">
								<s:hidden name="empId" /><s:hidden name="empToken" />
								<s:submit cssClass="add" value="Add" name="add" action="IPFilter_addException" onclick="return addEmp();" />
							</td>
						</tr>
						<tr>
							<td colspan="4" width="100%">
								<table width="100%" cellpadding="1" cellspacing="1" class="sortable">
									<tr>
										<td valign="top" class="formth"><label name="sr.no" id="sr.no4" ondblclick="callShowDiv(this);" ><%=label.get("sr.no") %></label></td>
										<td valign="top" colspan="1" class="formth"><label name="employee.id" id="employee.id" ondblclick="callShowDiv(this);" ><%=label.get("employee.id") %></label></td>
										<td valign="top" colspan="1" class="formth"><label name="employee" id="employee1" ondblclick="callShowDiv(this);" ><%=label.get("employee") %></label></td>
										<td valign="top" class="formth">
											<div>
												<input type="checkbox" name="checkMeEmp" id="checkMeEmp" onclick="return selectAllEmp();" />
												<input type="button" class="delete" value="Remove" onclick="return deleteEmp();" />
											</div>
										</td>
									</tr>
									<%!int cnt = 0;%>
									<%
										int a = 0;
										int b = 0;
										HashMap afdataEmp = (HashMap) request.getAttribute("dataEmp");
									%>
									<s:iterator value="empList" status="stat">
										<%
												if (afdataEmp == null) {
												afdataEmp = new HashMap();
												afdataEmp.put("" + b, "B");
											}
										%>
										<tr>
											<td class="border2"><%=++a%><s:hidden name="srNoEmp" />
											<td class="border2"><s:property value="eToken" /><s:hidden name="eToken" /></td>
											<td class="border2"><s:property value="eName" /><s:hidden name="eName" /><s:hidden name="eId" /></td>
											<td width="20%" class="border2">
												<div align="center">
													<input type="checkbox" class="checkbox" id="<%="leaveChkEmp"+b%>" name="leaveChkEmp" value="<%=b%>"
													onclick="callChk2()" />
													<input type="hidden" name="pmChkFlag" id="<%=b%>" value='B' />
												</div>
											</td>
											</td>			
										</tr>
										<%
											b++;
										%>
									</s:iterator>
									<%
										cnt = b;
									%>
								</table>
							</td>
						</tr>		
					</table>
				</div>
			</td>
		</tr>
	</table>
</s:form>

<script>
function callValidate()
{
 
try
{
		if(document.getElementById('ipFilterFlag').checked)
		{
			var brnFlag =document.getElementById('branchWiseCheck').checked;
			var orgFlag =document.getElementById('orgWiseCheck').checked;
			if(brnFlag==false && orgFlag==false)
			{
				alert("Please select Organisation wise or Branch wise filtering");
				return false;
				
			}
			if(document.getElementById('orgWiseCheck').checked)
			{
			  var cnt='<%=k%>';
			 if(cnt<1)
			 {
			 alert("Please add at least one IP address range");
			 return false;
			 }
			}
			if(document.getElementById('branchWiseCheck').checked)
			{
			  var cnt='<%=kk%>';
			if(cnt<1)
			 {
			 alert("Please add at least one IP address range");
			 return false;
			 }
			}
					
		}
		document.getElementById('paraFrm').action="IPFilter_save.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
		
		 
		}
	return true;
}

function isValidIPAddress(ipaddr) {

  var re = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
   if (re.test(ipaddr)) {
      var parts = ipaddr.split(".");
      if (parseInt(parseFloat(parts[0])) == 0) { return false; }
      for (var i=0; i<parts.length; i++) {
         if (parseInt(parseFloat(parts[i])) > 255) { return false; }
      }
      return true;
   } else {
      return false;
   }
}
function addRange()
{
   var fromIpAdd = document.getElementById('paraFrm_orgFrom').value;
    var toIpAdd = document.getElementById('paraFrm_orgTo').value;
    
     var from =fromIpAdd.split(".");
    var to =toIpAdd.split(".");
    for(i=0;i<from.length;i++)
    {
    		if(eval(from[i])>eval(to[i]))
    		{
    			alert(document.getElementById('to.ipaddress').innerHTML.toLowerCase()+" should be greater than "+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
    			return false;
    		}
    		else if(eval(from[i])<eval(to[i]))
    		{
    		break;
    		}
    }
     
     if(fromIpAdd=="")
        {
        alert('Please enter '+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
        return false;
        }
         if(toIpAdd=="")
        {
        alert('Please enter '+document.getElementById('to.ipaddress').innerHTML.toLowerCase());
        return false;
        }
        
        var validFrom =isValidIPAddress(fromIpAdd);
       if(!validFrom)
       {
        alert('Please enter valid '+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
        return false;
       }
          var validTo =isValidIPAddress(toIpAdd);
       if(!validTo)
       {
         alert('Please enter valid '+document.getElementById('to.ipaddress').innerHTML.toLowerCase());
        return false;
       }
		return true;

}

function addBranchIpAdd()
{
 
   var fromIpAdd =trim(document.getElementById('paraFrm_branchFrom').value);
      var toIpAdd =trim(document.getElementById('paraFrm_branchTo').value);
    
    var brnName =document.getElementById('paraFrm_branchName').value;
   var from =fromIpAdd.split(".");
    var to =toIpAdd.split(".");
    for(i=0;i<from.length;i++)
    {
    		if(eval(from[i])>eval(to[i]))
    		{
    			alert(document.getElementById('to.ipaddress').innerHTML.toLowerCase()+" should be greater than "+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
    			return false;
    		}
    		else if(eval(from[i])<eval(to[i]))
    		{
    		break;
    		}
    }
     
    if(brnName=="")
    {
     alert('Please select '+document.getElementById('branch2').innerHTML.toLowerCase());
        return false;
       }
    if(fromIpAdd=="")
        {
        alert('Please enter '+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
        return false;
        }
         if(toIpAdd=="")
        {
        alert('Please enter '+document.getElementById('to.ipaddress').innerHTML.toLowerCase());
        return false;
        }
          var validFrom =isValidIPAddress(fromIpAdd);
          
          
       if(!validFrom)
       {
        alert('Please enter valid '+document.getElementById('from.ipaddress').innerHTML.toLowerCase());
        
        document.getElementById('paraFrm_branchFrom').focus();
        return false;
       }
        var validTo =isValidIPAddress(toIpAdd);
       if(!validTo)
       {
         alert('Please enter valid '+document.getElementById('to.ipaddress').innerHTML.toLowerCase());
        return false;
       }
       
       
       
		return true;
}
	
	function addEmp()
	{
	var empName=document.getElementById('paraFrm_empName').value;
	if(empName=="")
	{
	alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
	return false;
	}
	return true;
	}
	
	
function callChk(){
	var maxCheck ='<%=k%>';
 for(var i =0; i<eval(maxCheck);i++){
 	if(document.getElementById('leaveChk'+i).checked == true){
 		return true;
 	}
	}return false ;
 }
	
function callChk1(){
	var maxCheck ='<%=kk%>';
 for(var i =0; i<eval(maxCheck);i++){
 	if(document.getElementById('leaveChkBrn'+i).checked == true){
 		return true;
 	}
	}return false ;
 }

function callChk2(){
	var maxCheck ='<%=cnt%>';
 for(var i =0; i<eval(maxCheck);i++){
 	if(document.getElementById('leaveChkEmp'+i).checked == true){
 		return true;
 	}
	}return false ;
 }


function deleteOrgIp()
{
	 var check= callChk();
  	  if(check){
  	  
  	   var con=confirm('<%=label.get("confirm.remove")%>');
  	   
  	   if(con)
  	   {
  	    	document.getElementById('paraFrm').action="IPFilter_deleteOrgIpAdd.action";
  			document.getElementById('paraFrm').submit();
  	   }
  	   else
  	   {
  	   
  	 				var maxCheck ='<%=k%>';
 			for(var i =0; i<eval(maxCheck);i++){
 				document.getElementById('leaveChk'+i).checked = false;
 					
 				}
  	  	return false;
  	   }
  
  }else{
  	alert('Please select atleast one record to remove');
  }  
  
  }
	
	 function deleteBrnIp()
  {
 	 
  	 var check= callChk1();
  	 	 
  	 if(check){
  	 
  	  var con=confirm('<%=label.get("confirm.remove")%>');
  	  
  	  if(con)
  	  {
  	   document.getElementById('paraFrm').action="IPFilter_deleteBrnIpAdd.action";
 		 document.getElementById('paraFrm').submit();
  	  }
  	  else
  	  {
  	  var maxCheck ='<%=kk%>';
 for(var i =0; i<eval(maxCheck);i++){
 	document.getElementById('leaveChkBrn'+i).checked =false;
 			
	}
  	  }
  
  }else{
  	alert('Please select atleast one record to remove');
  }  
  
  }
	function deleteEmp()
	{
	
  	 var check= callChk2();
  	
  if(check){
  
   var con=confirm('<%=label.get("confirm.remove")%>');
   
   if(con)
   {
   document.getElementById('paraFrm').action="IPFilter_deleteException.action";
  	document.getElementById('paraFrm').submit();
   }
   else
   
   {
   		var maxCheck ='<%=cnt%>';
   		
   		for(var i =0; i<eval(maxCheck);i++){
   		document.getElementById('leaveChkEmp'+i).checked =false;
   		}
   }
   
   
  }else{
  	alert('Please select atleast one record to remove');
  }  
	}


  
 




	function onload1()
	{
	try
	{
	
	 
		if(document.getElementById('branchWiseCheck').checked==true)
			{
			 
			   document.getElementById('org').style.display = 'none';
	       		document.getElementById('branch').style.display = '';
		       document.getElementById('selBrn').style.display = '';
		    	document.getElementById('selBrnText').style.display = '';
		 	    document.getElementById('branchList').style.display = '';
		 	 
		}
	}
	catch(e)
	{
	
	}
	
	}




function onload()
{
try
{
 
if(document.getElementById('orgWiseCheck').checked==true)
	{
	 
 	        document.getElementById('org').style.display = '';
 	        
			document.getElementById('orgText').style.display = '';
			 
		    document.getElementById('orgList').style.display  = '';
		     
		  
		    document.getElementById('branch').style.display = 'none';
		   
	}
	else
	{
	if(document.getElementById('branchWiseCheck').checked)
		{
       		document.getElementById('org').style.display = 'none';
			document.getElementById('branch').style.display = '';
	       document.getElementById('selBrn').style.display = '';
	    	document.getElementById('selBrnText').style.display = '';
	    	
	 	    document.getElementById('branchList').style.display = '';
			}
	}
	
}
catch(e)
{

}	
}

function orgWise()
{
 
try
{
if(document.getElementById('orgWiseCheck').checked==true)
	{
 	        document.getElementById('branch').style.display = 'none';
	        document.getElementById('org').style.display = '';
			document.getElementById('orgText').style.display = '';
		    document.getElementById('orgList').style.display = '';
	}
	else{
 
            document.getElementById('branch').style.display = '';
	         document.getElementById('org').style.display = '';
		document.getElementById('orgText').style.display = 'none';
		    document.getElementById('orgList').style.display = 'none';
	}
}	
	catch(e)
	{

	}
	
	
}

function brnWise()
{
		 
if(document.getElementById('branchWiseCheck').checked)
{
       		document.getElementById('org').style.display = 'none';
			document.getElementById('branch').style.display = '';
	       document.getElementById('selBrn').style.display = '';
	    	document.getElementById('selBrnText').style.display = '';
	 	    document.getElementById('branchList').style.display = '';
	}
	else
	{
    		 document.getElementById('org').style.display = '';
			    document.getElementById('branch').style.display = '';
			     document.getElementById('selBrn').style.display = 'none';
	    	document.getElementById('selBrnText').style.display = 'none';
	 	    document.getElementById('branchList').style.display = 'none';
	      
	 }
		
}



isApplicable();
 function isApplicable()
 {


	if(document.getElementById('ipFilterFlag').checked)
	{
 		    document.getElementById('org').style.display = '';
		    document.getElementById('orgText').style.display = 'none';
		    document.getElementById('orgList').style.display = 'none';
		    document.getElementById('branch').style.display = '';
		    document.getElementById('selBrn').style.display = 'none';
	    	document.getElementById('selBrnText').style.display = 'none';
	 	    document.getElementById('branchList').style.display = 'none';
		    document.getElementById('emp').style.display = '';
	 	}
	else
	{
     		document.getElementById('org').style.display = 'none';
			document.getElementById('orgText').style.display = 'none';
		    document.getElementById('orgList').style.display = 'none';
		    document.getElementById('branch').style.display = 'none';
	        document.getElementById('selBrn').style.display = 'none';
	    	document.getElementById('selBrnText').style.display = 'none';
	 	    document.getElementById('branchList').style.display = 'none';
	   	   document.getElementById('emp').style.display = 'none';
			document.getElementById('orgWiseCheck').checked="";
		   document.getElementById('branchWiseCheck').checked="";
	   
	}
	onload();
	
onload1();

 }
 
 function selectAllOrg()
	{
	var maxCheck ='<%=k%>';

		var check =document.getElementById('checkMe').checked;
		
			if(check == true)
			{
			 	for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChk' + i).checked = true;
		 		}
	 		}
			else
			{
		 		for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChk' + i).checked = false;
				
		 		}
		 	}
		}
	
	
	
	
	function selectAllEmp()
	{
	var maxCheck ='<%=cnt%>';
	
		var check =document.getElementById('checkMeEmp').checked;
			if(check == true)
			{
			 	for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChkEmp' + i).checked = true;
		 		}
	 		}
			else
			{
		 		for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChkEmp' + i).checked = false;
				
		 		}
		 	}
	}
	
	
	
	function selectAllBrn()
	{
	var maxCheck ='<%=kk%>';
	
		var check =document.getElementById('checkMeBrn').checked;
		
	
			if(check == true)
			{
			 	for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChkBrn' + i).checked = true;
		 		}
	 		}
			else
			{
		 		for(var i = 0; i < maxCheck; i++)
			 	{
				 document.getElementById('leaveChkBrn' + i).checked = false;
				
		 		}
		 	}
	}
</script>