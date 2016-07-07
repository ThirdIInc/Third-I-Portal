<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">
	
	
	  <table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg" >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Eligible Employees</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
              
           <tr><s:hidden name="importStartDate"></s:hidden><s:hidden name="importEndDate"></s:hidden>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0">
         <s:hidden name="saveEligibleFlag"/><s:hidden name="isStarted"/>
          <tr>
            <td width="78%">
            
            
  		<input type="button" class="save"  theme="simple"  value="   Save" onclick="return saveEmployee();"/>
  		<input type="button" class="token" theme="simple"  value="  Back  " onclick="return callBack();"/>
  		
	
  			
	</td><s:hidden name="appraisalId"/>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
            </table></td>
          </tr>
      </table></td>
    </tr>
    
    
    
        </table>
          </td>
    </tr>
   
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
              
              <tr height="25">
              
              
              <td width="20%" colspan="1" height="20" class="formtext"><label name="employee" class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red">*</font> :</td>
			  <td width="80%" colspan="3" height="20"><s:textfield name="empIdAdd" size="10" readonly="true" /><s:textfield name="empNameAdd" size="20" readonly="true" /><s:hidden name="empCodeAdd"></s:hidden><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalCalendar_f9actionEmployeeAdd.action'); "></td>
              
            </tr>
            <tr height="25">
              <td width="20%" colspan="1" height="20"></td>
              <td width="80%" colspan="4"   height="20" class="formtext"><input type="button" value="  Add Employee" class="add" onclick="return callAddEmp();"/></td>
              
            </tr>
            <s:hidden name="empJoinDateAdd"/><s:hidden name="empTypeAdd"/><s:hidden name="empGradeAdd"/><s:hidden name="empDeptAdd"/>
            </table></td>
          </tr>
      </table></td>
    </tr>
    <tr>
    <td colspan="3">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="sortable">
				<tr><td colspan="3"><strong>Employee List</strong>
				</td>
				</tr>
				<tr>
			<td colspan="8">

			<table width="100%" border="0" cellpadding="2" cellspacing="0">
<%
 	int total1 = (Integer) request.getAttribute("totalPage");
 	int PageNo1 = (Integer) request.getAttribute("pageNo");
 %>
				<tr>	<%if(total1 >0){ %>
					<td align="right"><b>Page:</b>  <%
 if (!(PageNo1 == 1)) {
 %><a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> <%
 	}
 	if (total1 < 5) {
 		for (int i = 1; i <= total1; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}

 	if (total1 >= 5) {
 		for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}
 	if (!(PageNo1 == total1)) {
 %>...<a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
						href="#" onclick="callPage('<%=total1%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="on()" id="selectname">
						<%
						for (int i = 1; i <= total1; i++) {
						%>

						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
					</select></td>
					<%} %>&nbsp;
				</tr>

<s:hidden name="show" value="%{show}" />
<s:hidden name="myPage" id="myPage" />

			</table>
			</td>
		</tr>

				<tr>
					<td colspan="1" class="formth" align="center" width="5%">Sr
					No.</td>
					<td colspan="1" class="formth" align="center" width="25%" ><label name="employee" class = "set"  id="employee1" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					</td>
					<td colspan="1" class="formth" align="center" width="15%" ><label name="doj" class = "set"  id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>
					</td>
					<td colspan="1" class="formth" align="center" width="20%" nowrap="nowrap"><label name="employee.type" class = "set"  id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
					<td colspan="1" class="formth" align="center" width="15%" nowrap="nowrap"> <label name="grade" class = "set"  id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label></td>
					<td colspan="1" class="formth" width="20%" ><label name="department" class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> </td>
					<td colspan="1" class="formth" width="5%"><input type="button" value="   Remove" class="delete" onclick="chkRemove();"/></td>

				</tr>

				<% int i=0;
				int cnt = PageNo1*20-20;
				%>
				
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center" class="sortableTD"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if><s:else>

				<s:iterator value="empList">


					
					<tr>
						<td width="10%" align="center" class="sortableTD"><%=++cnt%><%++i;%><s:hidden
							name="empCodeList" /> </td>

						<td  class="sortableTD" nowrap="nowrap"><s:hidden
							name="empNameList" /><s:property value="empNameList" /></td>

						<td align="center" class="sortableTD" nowrap="nowrap"><s:hidden
							name="empJoinDateList"  /><s:property value="empJoinDateList" />&nbsp;</td>

						<td  class="sortableTD"><s:hidden
							name="empTypeList"  /><s:property
							value="empTypeList" />&nbsp;</td>

						<td  class="sortableTD" nowrap="nowrap"><s:hidden
							name="empGradeList"  /><s:property
							value="empGradeList" />&nbsp;<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /><s:hidden name="criteriaType"/></td>
						
						<td  class="sortableTD" nowrap="nowrap"><s:hidden
							name="empDeptList"  /><s:property
							value="empDeptList" />&nbsp;</td>
						<td align="center" class="sortableTD"><s:if test="chk">
							<input type="checkbox" class="checkbox" name="chk" id="chk<%=i%>"
								checked="checked" disabled="disabled" onclick="callChk(<%=i%>)">
						</s:if> <s:else>
							<input type="checkbox" class="checkbox" name="removeChk" id="removeChk<%=i%>"
								onclick="callCheckBox('<s:property value="empCodeList"/>',<%=i%>)">

						</s:else> 
						
						
						</td>
					<td align="center" class="sortableTD"></td>
					</tr>

				</s:iterator>
				</s:else>
								
				
			</table>
			
			</td>

		</tr></table>
		</td>
    
    </tr>
      </table>
     
	<input type="hidden" name="count" id="count" value="<%=i%>"/>
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

	function saveEmployee(){
		
		document.getElementById('paraFrm').action="AppraisalCalendar_saveUpdatedEmployee.action";
	    document.getElementById('paraFrm').submit();
	}
	function callBack(){
	var apprId = document.getElementById('paraFrm_appraisalId').value;
	////alert("apprId=="+apprId);
	var saveFlag = document.getElementById("paraFrm_saveEligibleFlag").value;
	if(saveFlag=="false"){
		var confMsg =confirm("You have modified the list. \n Do you want to save this list?\n Click 'OK' to save the list or click 'Cancel' to go back");
		if(confMsg){
			document.getElementById('paraFrm').action="AppraisalCalendar_saveUpdatedEmployee.action";
	    	document.getElementById('paraFrm').submit();
		}else{
			document.getElementById('paraFrm').action="AppraisalCalendar_callBackToCalendar.action?apprId="+apprId;
	    	document.getElementById('paraFrm').submit();
	    	document.getElementById('myPage').value ='';
	    	document.getElementById('paraFrm_show').value="";
	    	document.getElementById('selectname').value="";
		 }
	}else{
			document.getElementById('paraFrm').action="AppraisalCalendar_callBackToCalendar.action?apprId="+apprId;
	    	document.getElementById('paraFrm').submit();
		}
	
	}
	function chkRemove()
	{
	var checkFlag="false";
	var flag=document.getElementById("count").value;
	
	for(x=1;x<=flag;x++){
		if(document.getElementById('removeChk'+x).checked==true){
			checkFlag="true";
			break;
		}
		
	}
	if(checkFlag =="false"){
		alert("Please select atleast one "+document.getElementById("employee").innerHTML+" to remove");
		return false;
	}else{
	
	 
	 var con=confirm('Do you really want to remove the '+document.getElementById("employee").innerHTML+' ?');
	 if(con){
	   document.getElementById('paraFrm').action="AppraisalCalendar_removeMultipleEmployee.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {     
	    
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('removeChk'+a).checked=false;
	    document.getElementById('hdeleteCode'+a).value="";
	 	}
	     return false;
	     }
	    
	 }
	 
	 
	}
	function callCheckBox(empId,i)
	   {
	   
	   if(document.getElementById('removeChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=empId;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
	function callPage(id){
	
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action="AppraisalCalendar_viewEligibleEmp.action";
		document.getElementById('paraFrm').submit();
   }
   
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="AppraisalCalendar_viewEligibleEmp.action";
	   document.getElementById('paraFrm').submit();
   }
	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="AppraisalCalendar_viewEligibleEmp.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="AppraisalCalendar_viewEligibleEmp.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
function callAddEmp(){
	if(document.getElementById("paraFrm_empIdAdd").value==""){
		alert("Please select the "+document.getElementById("employee").innerHTML);
		document.getElementById("paraFrm_empNameAdd").focus();
		return false;
		}	
	document.getElementById("paraFrm").action="AppraisalCalendar_addEligibleEmp.action";
	document.getElementById("paraFrm").submit();
}

</script>
