
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="TravelGradeExpense" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">




		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Grade wise Travel Expense </strong></td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td>




			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><s:submit cssClass="add" action="TravelGradeExpense_save"
						theme="simple" value="   Add New " onclick="return callSave('s');" />

					<s:if test="%{updateFlag}">
						<s:submit cssClass="edit" action="TravelGradeExpense_update"
							theme="simple" value="   Update " onclick="return callSave('u');" />
					</s:if> <s:if test="%{viewFlag}">
						<input type="button" class="search"
							onclick="javascript:callsF9(500,325,'TravelGradeExpense_f9action.action'); "
							value="    Search " />
					</s:if> <s:submit cssClass="reset" action="TravelGradeExpense_reset"
						theme="simple" value="    Reset" /> <s:submit cssClass="delete"
						action="TravelGradeExpense_delete" theme="simple"
						value="   Delete"
						onclick="return callDelete('paraFrm_trGrade_trGradeId');" /> <s:if
						test="%{viewFlag}">
						<input type="button" class="token" onclick="callReport();"
							value="  Report " />
					</s:if></td>
					<td align="right"><font color="red">*</font>Indicates Required</td>
				</tr>
			</table>


			</td>
		</tr>

		<tr>
			<td>



			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="trGrade.trGradeId" />
					<td width="35%" colspan="1">Select Grade <font color="red">*</font>:</td>
					<td width="20%" colspan="1"><s:textfield size="20"
						name="trGrade.gradeName" readonly="true" /> <s:hidden
						name="trGrade.gradeCode" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TravelGradeExpense_f9Grade.action');">
					</td>
					<td width="27%" colspan="1">Travel Miscellaneous Expenses <font
						color="red">*</font> :</td>
					<td width="22%" colspan="1"><s:textfield size="15"
						name="trGrade.travelOtherCost"
						onkeypress="return numbersWithDot();" /></td>
				</tr>
				<tr>
					<td width="35%" colspan="1">Lodging Miscellaneous allowance
					per day <font color="red">*</font>:</td>
					<td width="22%" colspan="1"><s:textfield size="15"
						name="trGrade.hotelOtherCost"
						onkeypress="return numbersWithDot();" /></td>
					<td width="27%" colspan="1">Lodging allowance per Day<font
						color="red">*</font>:</td>
					<td width="20%" colspan="1"><s:textfield size="15"
						name="trGrade.hotelPerDayCost"
						onkeypress="return numbersWithDot();" /></td>
				</tr>
				<tr>
					<td width="35%" colspan="1">Out of pocket allowance per day :</td>
					<td width="22%" colspan="1"><s:textfield size="15"
						name="trGrade.pocketCost" onkeypress="return numbersWithDot();" />
					</td>
					<td width="27%" colspan="1">Food allowance per day:</td>
					<td width="20%" colspan="1"><s:textfield size="15"
						name="trGrade.foodCost" onkeypress="return numbersWithDot();" />
					</td>
				</tr>


				</td>
				</tr>
			</table>


			</td>
		</tr>


		<tr>
			<td>


			<table width="100%" class="formbg">
				<tr>
					<td width="20%" colspan="1" class="formth">Journery Mode</td>
					<td width="20%" colspan="1" class="formth">Journery Class</td>
					<td width="20%" colspan="1" class="formth">Status</td>
				</tr>
				<% int i =0;%>
				<s:iterator value="jourExp">
					<tr>
						<s:hidden name="journeyName" />
						<s:hidden name="journeyId" />
						<s:hidden name="className" />
						<s:hidden name="classId" />
						<td width="20%" colspan="1" class="border2"><s:property
							value="journeyName" /></td>
						<td width="20%" colspan="1" class="border2"><s:property
							value="className" /></td>
						<td width="20%" colspan="1" class="border2"><input
							type="checkbox" name="grCheck" id="grCheck<%=i%>"
							<s:property value="grCheck"/> value=''
							onclick="onCheckBox('<%=i%>');"> <input type="hidden"
							name="hidCheck" id="hidCheck<%=i%>"
							value="<s:property value="hidCheck"/>"></td>
					</tr>
					<%i++;%>
				</s:iterator>
			</table>

			</td>
		</tr>
	</table>

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
  function onCheckBox(id)
  { 
    var grCheck = document.getElementById('grCheck'+id).value;
    
    if(document.getElementById('grCheck'+id).checked)
    { 
     document.getElementById('hidCheck'+id).value='Y'
    }else
    {
      document.getElementById('hidCheck'+id).value='N'
    }
      
  }
  
  	 function callSave(flag)
   { 
       trGradeId =  document.getElementById("paraFrm_trGrade_trGradeId").value;  
       gradeCode =  document.getElementById("paraFrm_trGrade_gradeCode").value;
       travelOtherCost =  document.getElementById("paraFrm_trGrade_travelOtherCost").value;
       hotelOtherCost =  document.getElementById("paraFrm_trGrade_hotelOtherCost").value;
       hotelPerDayCost =  document.getElementById("paraFrm_trGrade_hotelPerDayCost").value; 
       if(flag=='s')
       {
		   if(trGradeId!=""){
			     alert("Please update record.");
				   return false;
			 }  
		 }
		 
		if(flag=='u')
       {
		   if(trGradeId==""){
			     alert("Please select record to update.");
				 return false;
			 }  
		 }
		 
	    if(gradeCode==""){
		  alert("Please select the grade.");
		  return false;
		}
		
		 if(travelOtherCost==""){
		  alert("Please enter Travel Miscellaneous Expenses.");
		   document.getElementById('paraFrm_trGrade_travelOtherCost').focus();
		  return false;
		}
		
		 if(hotelOtherCost==""){
		  alert("Please enter Lodging Miscellaneous allowance per day.");
		  document.getElementById('paraFrm_trGrade_hotelOtherCost').focus();
		  return false;
		}
		
		 if(hotelPerDayCost==""){
		  alert("Please enter Lodging allowance per Day.");
		   document.getElementById('paraFrm_trGrade_hotelPerDayCost').focus();
		  return false;
		}
		
	}
	
	
	 function callReport()
	 {  
		 var id = document.getElementById('paraFrm_trGrade_trGradeId').value;
		 if(id=="")
		 {
		   alert(" Please select the record.");
		   return false;
		 }
		 else
		 {  document.getElementById('paraFrm').target ='_blank';	 
			 document.getElementById('paraFrm').action = "TravelGradeExpense_report.action";
			 document.getElementById('paraFrm').submit();
			 document.getElementById('paraFrm').target ="";
		 }
	 }
</script>


