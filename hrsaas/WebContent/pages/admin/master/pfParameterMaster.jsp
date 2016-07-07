<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">PF Parameter</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PFParameter_save" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_save" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9action.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PFParameter_reset"
					theme="simple" value="    Reset"  />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="    Delete" action="PFParameter_delete" onclick="return callDelete('paraFrm_pfParam_pfCode');"  />
	 </s:if>
	<s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_report.action')" />	
	</s:if>
	
	    <s:hidden name="pfParam.pfCode"></s:hidden>
	    </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
   
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
            <tr>
							<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">PF Parameter </strong>  </td>
						</tr>
					<tr>
			<td  width="25%"><label  class = "set" name="pfparam.edate" id="pfparam.edate" ondblclick="callShowDiv(this);"><%=label.get("pfparam.edate")%></label>
			<!--Effective Date-->
			<font color="red">*</font>:</td>
			<td width="25%">
			<s:textfield name="pfParam.effDate" theme="simple"  />
		 	<s:a href="javascript:NewCal('paraFrm_pfParam_effDate','DDMMYYYY');">
		 	<img src="../pages/images/Date.gif" class="iconImage" height="16" align="absmiddle" width="16" ></s:a></td>
	
		</tr>
		<tr>
			<td  width="25%"><label  class = "set" name="pfparam.percent" id="pfparam.percent" ondblclick="callShowDiv(this);"><%=label.get("pfparam.percent")%></label>
			<!--PF Percentage--><font color="red">*</font>:</td>
			<td width="25%">
			<s:textfield 
				theme="simple" name="pfParam.deduction"  maxlength="6" onkeypress="return numbersWithDot();" />	
			</td>
			<td width="25%"><label  class = "set" name="pfparam.type" id="pfparam.type" ondblclick="callShowDiv(this);"><%=label.get("pfparam.type")%></label>:
			<!--PF Type--></td>
			<td width="25%">
			<s:select name="pfParam.pfType"
				list="#{'','CT':'Company Trust','G':'Government'}"/>
		
			</td>	
		
		</tr> 
			
		<tr>
			<td  width="25%"><label  class = "set" name="pfparam.empPF" id="pfparam.empPF" ondblclick="callShowDiv(this);"><%=label.get("pfparam.empPF")%></label>%
			<!--Employee PF %--><font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfParam.empPF"  maxlength="6"     onkeyup="chkPercentage();" onkeypress="return numbersWithDot();"/></td>
			<td  width="25%"><label  class = "set" name="pfparam.empfamilyFund" id="pfparam.empfamilyFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.empfamilyFund")%></label>
			<!--Employee Family PF  --> %<font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfParam.empFamilyPF"  readonly="true" onkeypress=" return numbersWithDot();"/></td>
		</tr>

		<tr>
			<td  width="25%"><label  class = "set" name="pfparam.pensionFund" id="pfparam.pensionFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pensionFund")%></label>
			<!--Pension Fund--> %<font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfParam.pensionFund" maxlength="6"  onkeyup="chkPercentage();" onkeypress="return numbersWithDot();"/></td>
			<td  width="25%"><label  class = "set" name="pfparam.compFund" id="pfparam.compFund" ondblclick="callShowDiv(this);"><%=label.get("pfparam.compFund")%></label>
			<!--Company Fund-->  %<font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfParam.compFund" maxlength="6" readonly="true" onkeypress="return numbersWithDot();"/></td>
	
	
		</tr>
	
		
			<tr>
			<td  width="25%"><label  class = "set" name="pfparam.PFdebiCode" id="pfparam.PFdebiCode" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFdebiCode")%></label>
			<!--PF Debit Code--><font color="red">*</font>:</td>
			<td width="25%"><s:hidden name="pfParam.debitCode"></s:hidden>
			<s:textfield name="pfParam.debitName" readonly="true"></s:textfield>	
					<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle"
				width="18" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_debitaction.action'); ">
		
			</td>
			 <td  width="25%"><label  class = "set" name="pfparam.EDLIContribut" id="pfparam.EDLIContribut" ondblclick="callShowDiv(this);"><%=label.get("pfparam.EDLIContribut")%></label>
			 <!--EDLI Employee Contribution  --> %:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="edlicontribution" maxlength="6"  onkeypress="return numbersWithDot();"/></td>
			
		</tr>
		<tr>
		   <td  width="25%"><label  class = "set" name="pfparam.PFAdmin" id="pfparam.PFAdmin" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFAdmin")%></label>
		   <!--PF Admin Charge  --> %:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfadmincharge" maxlength="6"  onkeypress="return numbersWithDot();"/></td>
			<td  width="25%"><label  class = "set" name="pfparam.PFEDLICharge" id="pfparam.PFEDLICharge" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFEDLICharge")%></label>
			<!--PF EDLI Admin Charge  --> %:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="pfedlicharge" maxlength="6" onkeypress="return numbersWithDot();"/></td>
		</tr>
		<tr>
		   <td  width="25%"><label  class = "set" name="pfparam.minumumAmt" id="pfparam.minumumAmt" ondblclick="callShowDiv(this);"><%=label.get("pfparam.minumumAmt")%></label>
		   <!--PF Minimum AMt check  --> :</td>
			<td width="25%"><s:textfield theme="simple" name="pfMinAmt" maxlength="6"  onkeypress="return numbersWithDot();"/></td>
			<td  width="25%"></td>
			<td width="25%"></td>
		
		</tr>
		<tr>
		<td ><label  class = "set" name="pfparam.PFformulea" id="pfparam.PFformulea" ondblclick="callShowDiv(this);"><%=label.get("pfparam.PFformulea")%></label>
		<!--PF Formula  --><font color="red">*</font>:</td><td colspan="2">
			<s:textfield 
				theme="simple" name="pfParam.pfFormula"  maxlength="50"  />
			<input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
							onclick="callFormulaBuilder('paraFrm_pfParam_pfFormula');"/>	
				
				</td>
		
		
		</tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
   
   <tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td align="right"><b>Page:</b> <%
	int total1 = (Integer) request.getAttribute("abc");
	int PageNo1 = (Integer) request.getAttribute("xyz");
%> <%
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
 		if (!(PageNo1 ==1)) {
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
				</tr>



			</table>
			</td>
		</tr>



<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><!--Sr No.  -->
								<label  class = "set" name="pfparam.srno" id="pfparam.srno" ondblclick="callShowDiv(this);"><%=label.get("pfparam.srno")%></label>
							</td>
							<td class="formth" align="center"><!--Effective Date  -->
								<label  class = "set" name="pfparam.edate" id="pfparam.edate1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.edate")%></label>
							</td>
					        <td class="formth" align="center"><!--PF Type  -->
					        	<label  class = "set" name="pfparam.type" id="pfparam.type1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.type")%></label>
					        </td>
							<td class="formth" align="center"><!--Percentage  -->
							 	<label  class = "set" name="pfparam.percent" id="pfparam.percent1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.percent")%></label>
							</td>
							<td  align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete"  theme="simple"
								value="     Delete  "  onclick=" return chkDelete()"/></td>


							<%int count=0; %>
							<%! int d=0; %>
							<%
							int cnt= PageNo1*20-20;	
							int i = 0;
								%>
							<s:iterator value="iteratorlist">

								<tr 
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="pfCode" />');">


									<td width="10%" align="left"> <%=++cnt%><%++i;%></td>
									<s:hidden value="%{pfCode}"></s:hidden>

									<td width="40%" align="left">
                                                                    <s:property value="effDate" />
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td width="20%"><s:property value="pfType" /></td>
									<td width="20%"><s:property value="deduction" /></td>
									<td  align="center" nowrap="nowrap"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="pfCode" />','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%d=i; %>
						</tr>

						
					</table>

					<%
				} catch (Exception e) {
				}
			%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
   
   
    
   
    
  </table></td></tr></table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

function chkPercentage()
{

	var percen= document.getElementById('paraFrm_pfParam_deduction').value;
	var empPf= document.getElementById('paraFrm_pfParam_empPF').value;
	var pensionPf=document.getElementById('paraFrm_pfParam_pensionFund').value;
	
	if(percen==""){
	  alert("Please enter Percentage first");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_deduction').focus()=true;
	  
	  return false;
	}
	
	if(empPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_empPF').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Employee PF should be lessthan the PF Percentage");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_empFamilyPF').value='';
	  return false;
	
	}
	var empfamilyPf = (eval(percen * 100)/100 -eval(empPf *100)/100);
	
	document.getElementById('paraFrm_pfParam_empFamilyPF').value=empfamilyPf;

	}
	if(pensionPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_pensionFund').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Pension Fund Percentage should be lessthan PF Percentage");
	  document.getElementById('paraFrm_pfParam_pensionFund').value='';
	  document.getElementById('paraFrm_pfParam_compFund').value='';
	  return false;
	
	}
	var compFund = (eval(percen * 100)/100-eval(pensionPf * 100)/100);
	
	document.getElementById('paraFrm_pfParam_compFund').value=compFund;

	}
	
	return true;
	
}



var fieldName = ["paraFrm_pfParam_effDate","paraFrm_pfParam_deduction","paraFrm_pfParam_empPF","paraFrm_pfParam_pensionFund","paraFrm_pfParam_debitName","paraFrm_pfParam_pfFormula","paraFrm_pfParam_empFamilyPF","paraFrm_pfParam_compFund"];
var lableName = ["pfparam.edate","pfparam.percent","pfparam.empPF","pfparam.pensionFund","pfparam.PFdebiCode","pfparam.PFformulea","pfparam.empfamilyFund","pfparam.compFund"];
var badflag = ["enter","enter","enter","enter","select","enter","enter","enter"];


function callSave()
 {
		if(!document.getElementById('paraFrm_pfParam_pfCode').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }
       
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
       	
       if(!validateDate('paraFrm_pfParam_effDate','PFEffective Date'))
             return false;	   
            if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
   
		return true;
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_pfParam_pfCode').value==""){
 			alert("Please select the record to update!");
 			return false;
 		}else {
 			    if(!checkMandatory(fieldName,lableName,badflag))
       				 return false;
        
      			 if(!validateDate('paraFrm_pfParam_effDate','PFEffective Date'))
             return false;	   
             
             if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
              
		return true;   
 		}
 }









function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PFParameter_callPage.action";
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
	   document.getElementById('paraFrm').action="PFParameter_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PFParameter_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="PFParameter_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   


function callForDelete1(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
   
   	function newRowColor(cell)
   	 {
			cell.className='onOverCell';

	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to delete these records ?');
	 if(con){
	   document.getElementById('paraFrm').action="PFParameter_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	 document.getElementById('hdeleteCode'+a).value="";
		}
	     return false;
	 }
	   
	 }
	 else {
	 alert('Please Select Atleast one Record To Delete');
	 	 return false;
	 }
	 
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}


</script>		
		
