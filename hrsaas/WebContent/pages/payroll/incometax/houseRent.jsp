<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="HouseRent" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="status"/>
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">House
					Rent==</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"></td>
							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" class="formbg">
							<tr>
									<td><label class="set" id="employee" name="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									<font color="red">*</font>:</td>
									<td colspan="3"><s:hidden name="empInvestment.invCode" />
									<s:hidden name="empInvestment.empID" /> <s:textfield
										name="empInvestment.empTokenNo" size="20" readonly="true" />
									<s:textfield name="empInvestment.empName" size="72"
										readonly="true" /> <s:if test="%{empInvestment.generalFlag}">
									</s:if> <s:else>
										<img src="../pages/images/recruitment/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'EmployeeInvestment_f9empaction.action');">
									</s:else></td>
								</tr>


								<tr>
									<td><label class="set" id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td><s:textfield size="25" name="empInvestment.department"
										readonly="true" /></td>

									<td><label class="set" id="branch" name="branch"
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td><s:textfield size="25" name="empInvestment.center"
										readonly="true" /><s:hidden name="empInvestment.centerId"/>
										<s:hidden name="empInvestment.gender"/>
										<s:hidden name="empInvestment.joinedMonth"/>
										<s:hidden name="empInvestment.age"/></td>
								</tr>
								<tr>			
								<td><label class="set" id="panNum" name="panNum"
										ondblclick="callShowDiv(this);"><%=label.get("panNum")%></label>:</td>
								<td><s:textfield size="25" name="empInvestment.panNum"
										readonly="true" /></td>
								</tr>
								<tr>
									<td><label class="set" id="taxFinYrFrm" name="taxFinYrFrm"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrFrm")%></label><font
										color="red">*</font>:</td>
									<td><s:textfield size="25" name="empInvestment.fromYear"
										maxlength="4" onkeypress="return numbersOnly();"
										onblur="add()" /></td>

									<td><label class="set" id="taxFinYrTo" name="taxFinYrTo"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrTo")%></label>
									<font color="red">*</font>:</td>
									<td><s:textfield size="25" name="empInvestment.toYear"
										maxlength="4" readonly="true" /></td>
								</tr>
								<tr>
									<td colspan="3"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>
								<tr align="center">
									<td colspan="4"><s:if test="%{empInvestment.viewFlag}">
										<s:submit cssClass="token" action="EmployeeInvestment_viewHra"
											theme="simple" value="View" onclick="return callView()" />
										<input type="button" class="token" 
											theme="simple" value="View Calculator" onclick="callCalculator()" />	
									</s:if>
									</td>
								</tr>
						</table>
					</td>
				</tr>				
				<tr>
				<td>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<s:if test="rentFlag">
				<tr>
					<td width="100%">
						<table height="18" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							<ul>
								<li><a href="javascript:callDivLoad('ID');" id="ID_L">
								<div align="center"><span>Investment Declaration </span></div>
								</a></li>
								<li><a href="javascript:callDivLoad('HR');" id="HR_L">
								<div align="center"><span>House Rent Declaration</span></div>
								</a></li>
							</ul>
							</div>
							</td>
						</tr>
						</table>
					</td>
					<!--  Updated by Anantha Lakshmi -->
				</tr>	
				<tr>	
					<td>
						<table  width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
							   <tr class="sortableTD">
								   <td>
								      <table width="100%"  class="formbg">
										<tr>
											<td ><label  class = "set"  id="owner1Name" name="owner1Name" ondblclick="callShowDiv(this);"><%=label.get("owner1Name")%></label>.</td>
											<td><s:textfield size="25" name="empInvestment.owner1Name"	maxlength="10"/></td>
											<td ><label class = "set"  id="owner1Address" name=""owner1Address"" ondblclick="callShowDiv(this);"><%=label.get("owner1Address")%></label>.</td>
											<td><s:textarea cols="20" rows="2"  name="empInvestment.owner1Address" /></td>
								
										</tr>
										<tr>
											<td ><label  class = "set"  id="owner1Agreement" name="owner1Agreement" ondblclick="callShowDiv(this);"><%=label.get("owner1Agreement")%></label>.</td>
											<td>
												<s:textfield size="25" readonly="true" name="owner1Agreement"	maxlength="10"/>
												<input type="button"  value="Attach Agreement" class="token" onclick="uploadFile('owner1Agreement')" />
											</td>
											<td ><label class = "set"  id="owner1Period" name=""owner1Period"" ondblclick="callShowDiv(this);"><%=label.get("owner1Period")%></label>.</td>
											<td><s:textfield size="25" name="empInvestment.owner1Period" onkeypress="return numbersWithDot();"	maxlength="10"/></td>
										</tr>
									</table>
								 </td>
							 </tr>
							 <tr><td>&nbsp;</td></tr>
							 <tr class="sortableTD">
							 <td>
							   <table  width="100%"  class="formbg">
									<tr>
										<td ><label  class = "set"  id="owner2Name" name="owner2Name" ondblclick="callShowDiv(this);"><%=label.get("owner2Name")%></label>.</td>
										<td><s:textfield size="25" name="empInvestment.owner2Name"	maxlength="10"/></td>
										<td ><label class = "set"  id="owner2Address" name=""owner2Address"" ondblclick="callShowDiv(this);"><%=label.get("owner2Address")%></label>.</td>
										<td><s:textarea cols="20" rows="2" name="empInvestment.owner2Address" /></td>
							
									</tr>
									<tr>
										<td ><label  class = "set"  id="owner2Agreement" name="owner2Agreement"  ondblclick="callShowDiv(this);"><%=label.get("owner2Agreement")%></label>.</td>
										<td>
										     <s:textfield size="25" name="owner2Agreement" readonly="true"	maxlength="10" cssStyle="background-color: #F2F2F2;"/>
	 										 <input type="button"  readonly="true" value="Attach Agreement" class="token" onclick="uploadFile('owner2Agreement')"/>
										</td>
										<td ><label class = "set"  id="owner2Period" name=""owner2Period"" ondblclick="callShowDiv(this);"><%=label.get("owner2Period")%></label>.</td>
										<td><s:textfield size="25" name="empInvestment.owner2Period"	onkeypress="return numbersWithDot();" maxlength="10"/></td>
									</tr>
								</table>
							</td>
							</tr>
					  </table>
				   </td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>	
				<tr>
					<td colspan="6" width="100%">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td class="formtext" colspan="6">
								<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr>
										
										<td width="35%" class="formth"><label  class = "set"  id="taxMon" name="taxMon" ondblclick="callShowDiv(this);"><%=label.get("taxMon")%></label></td>
										<td width="35%" class="formth"><label  class = "set"  id="taxAmt" name="taxAmt" ondblclick="callShowDiv(this);"><%=label.get("taxAmt")%></label></td>
										<td width="30%" class="formth"><label  class = "set"  id="proofAttach" name="proofAttach" ondblclick="callShowDiv(this);"><%=label.get("proofAttach")%></label>.</td>

									</tr>

									<%
										Object[][] rows = (Object[][]) request.getAttribute("rows");
										int i = 0;
										int s = 1;
									%>
									<%!int p = 0, t = 0;%>
									<%
										java.util.HashMap hm = (java.util.HashMap) request.getAttribute("month");
										java.util.HashMap hmFinal = (java.util.HashMap) hm.get("0");
										int index = 0;
									%>
									<s:iterator value="rentList" status="stat">
										<%
											String flag = (String) hmFinal.get("" + index++);
										%>
										<%!int x = 0;%>
										<tr>
											<s:hidden name="monthNo"/>
											<td width="35%" class="border2" width="35%" align="left">
											<s:property	value="month" /></td>
											<td width="35%" class="border2" width="35%"><input
												type="text" style="text-align:right" size="15" name="amount" id="<%="amount"+i%>"
												onkeyup="sumRow();" value="<s:property value="amount" />"
												onkeypress="return numbersWithDot();"
												<%=flag.equals("true")?"readonly":""%> />
												
											</td>	
											
											<td width="20%" class="border2" width="30%" align="center" >
												<input type="text" style="text-align:right" size="15" readonly  name="proofAttach" id="<%="proofAttach"+i%>"
												       value="<s:property value="proofAttach" />" readonly/>
												<input type="button" value="Browser" class="token" onclick="uploadFile('<%="proofAttach"+i%>')"/>
											</td>
											<td><a href="#" onclick="viewUploadedFile('<%="proofAttach"+i%>');"><font color="blue"><u>view</u></font></a></td>
										</tr>
										<%
											i++; s++; p++;
										%>

									</s:iterator>
									<%
										t = p; p = 0;
									%>
									<%
										x++;
									%>
								</table>

								</td>
							</tr>

							<tr class="formbg">
								<td width="35%" align="right">Total Rs.</td>
								<td colspan="2"><s:textfield name="totAmt" readonly="true" size="15"
									theme="simple" cssStyle="text-align:right" />
								</td>
							</tr>
							<tr>
								<td colspan="3">
									
								</td>
						  </tr>
							<tr>
								<td colspan="3" align="center">
									<s:if test="%{empInvestment.insertFlag}">
											<s:submit cssClass="add" action="EmployeeInvestment_saveHra" theme="simple"
												value="Save" onclick="return save();" />
									</s:if>
								</td>
						  </tr>
						</table>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="rentCode" />
</s:form>
<script>
onLoad();
function onLoad(){
	document.getElementById('HR_L').className='on';
}
   
 function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}
	
	 
   
   function add()
   {
      var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
    
    
    function save(){
  var totalrow = <%=t%> ;
  var employee=document.getElementById('employee').innerHTML.toLowerCase();
  var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase();
  

    if(document.getElementById('paraFrm_empName').value==""){
    alert("Please select the "+employee+".");
	return false;    
        
    }
    
    if(document.getElementById('paraFrm_fromYear').value==""){
    alert("Please enter "+finYrFrm+".");
   return false;
    
    }
    
// if(!isNumber(document.getElementById("paraFrm_fromYear").value)|| document.getElementById("paraFrm_fromYear").value.length<4){
 // alert('Invalid year entered.');
 // document.getElementById("paraFrm_fromYear").focus();
 // return false;  
//}
 if(totalrow==0){
 		alert("Please enter House Rent for the selected year.");
 		return false;
 
 
 		}


 }
    
    
    
    
    function deleteR() {

 try{
    if(document.getElementById('paraFrm_rentCode').value==""){
    	alert("There is no record to delete. ");
   		return false;
    }
    
    var conf=confirm("Are you sure to delete this record?");
	if(conf){
		return true;
	
	} else {
	
	return false;
	
	}
    }catch(e){alert(e);}
 }
   
   
   
   
   function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
			
		if ((("0123456789").indexOf(keychar) > -1))
			{
			return true;	
			}
		else {
			myfield.focus();	
			return false;
		}
	}	
  
  
  
 function sumRow() 
 {
	var totalrow = <%=t%> ;
	var count=0;
	for(var row = 0;row < eval(totalrow) ;row++)
	{
		var values=document.getElementById('amount'+row).value;
		if(values ==""){
			values =0;
		}
		values =eval(values*100/100);
		count=eval(count)+eval(values);
	}
	document.getElementById('paraFrm_totAmt').value=count;
 }


	function callView(){
		var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase();
		if(document.getElementById("paraFrm_fromYear").value==""){
		  alert("Please enter "+finYrFrm+".");
		  document.getElementById("paraFrm_fromYear").focus();
		  return false;
		  }
		 if(!isNumber(document.getElementById("paraFrm_fromYear").value)|| document.getElementById("paraFrm_fromYear").value.length<4){
		  alert('Invalid year entered.');
		  document.getElementById("paraFrm_fromYear").focus();
		  return false;  
		}
	}
    function callDivLoad(id)
	{
		if(id == 'ID'){
			document.getElementById('paraFrm').action = "EmployeeInvestment_view.action";
			document.getElementById('paraFrm').target = "main";
			document.getElementById('paraFrm').submit();
		} 
		document.getElementById('HR_L').className='on';
	}
	function callCalculator(){
			try{
				     var empId = document.getElementById('paraFrm_empInvestment_empID').value;
				     if(empId == ""){
				     	alert("Please select employee");
				     	return false;
				     }
				    var gender = document.getElementById('paraFrm_empInvestment_gender').value;
				    var frmYear = document.getElementById('paraFrm_empInvestment_fromYear').value;
				    var toYear = document.getElementById('paraFrm_empInvestment_toYear').value;
				    var age = document.getElementById('paraFrm_empInvestment_age').value;
				    var token = document.getElementById('paraFrm_empInvestment_empTokenNo').value;
				    var path = '<%=request.getContextPath() %>';
				    var empName = document.getElementById('paraFrm_empInvestment_empName').value;
				    var center = document.getElementById('paraFrm_empInvestment_center').value;
				    var rank = document.getElementById('paraFrm_empInvestment_department').value;
				    var centerId = document.getElementById('paraFrm_empInvestment_centerId').value;
				    var joinedMonth = document.getElementById('paraFrm_empInvestment_joinedMonth').value;
				    window.open(path+'/incometax/TdsCalculator_viewCalculator.action?gender='+gender+'&bbb='+empId+'&frYr='+frmYear+'&toYr='+toYear+'&age='+age+'&token='+token+'&name='+empName+'&center='+center+'&rank='+rank+'&centerId='+centerId+'&joinedMonth='+joinedMonth,'','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');
			}catch(e){alert(e);}
	}
	function uploadFile(fieldName){
			var dataPath = document.getElementById('paraFrm_dataPath').value;
			window.open('<%=request.getContextPath()%>/pages/payroll/incometax/houseRentMigratedFile.jsp?path=' + dataPath + '&field=' + 
			fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
   function viewUploadedFile(fieldName) {
   		var fieldValue=	document.getElementById(fieldName).value;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'EmployeeInvestment_viewProof.action?prfAttach='+fieldValue;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
   </script>



