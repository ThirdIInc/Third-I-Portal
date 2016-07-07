<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="LeavePolicy" id="paraFrm" validate="true" theme="simple"
	target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave Interval   Months</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr  class="td_bottom_border">
 
					<s:hidden name="frmId" id="frmId" />
					<s:hidden name="hiddenfrmId" id="hiddenfrmId" />
					<td width="40%" valign="top" class="formth">Month Name</td>
					<td width="20%" valign="top" class="formth">Select</td>
				</tr>
				
				<tr>
					<td align="left">January</td>
					<td width="10%" align="center">
						<input type="checkbox"	name="jan" id="check0" value="jan" />
					</td>
				</tr>
				
				<tr>
					<td align="left">February</td>
					<td width="10%" align="center">
						<input type="checkbox"	name="feb" id="check1" value="Feb" />
					</td>
				</tr>
				
				<tr>
					<td align="left">March</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="mar" id="check2" value="Mar"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">April</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="apr" id="check3" value="Apr"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">May</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="may" id="check4" value="May"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">June</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="jun" id="check5" value="Jun"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">July</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="jul" id="check6" value="Jul"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">August</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="aug" id="check7" value="Aug"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">September</td>
					<td width="10%" align="center">
						<input type="checkbox"	name="sept" id="check8" value="Sep"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">October</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="oct" id="check9" value="Oct"/>
					</td>
				</tr>
				
				<tr>
					<td align="left">November</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="nov" id="check10" value="Nov"/>
					</td>
				</tr>
				
				<tr>
					<td  align="left">December</td>
					<td  width="10%" align="center">
						<input type="checkbox"	name="dec" id="check11" value="Dec"/>
					</td>
				</tr>	
				
			</table>
			</td>
		</tr>

		<tr>
			<td align="left" width="25%" colspan="1"><input type="button"
				class="token" name="ok" value="OK" onclick="calculate();" />
				<input type="button"
				class="token" name="clear" value="Clear" onclick="callClear();" />
				</td>
		</tr>
	</table>

</s:form>
<script>
/**
*Modified by manish sakpal
*
*/
var intervalMonthID =document.getElementById('frmId').value;
var creditintervalID =document.getElementById('hiddenfrmId').value;
 


function callClear()
{
  opener.document.getElementById(intervalMonthID).value="";
  opener.document.getElementById(creditintervalID).value="";   			
  window.close();	
}



function calculate()
{
	/*
	alert("intervalMonthID =====>   "+intervalMonthID);
	alert("creditintervalID ====>  "+creditintervalID);
	 */
 	var id = '';
 	var id1='';
 	var chkBox;
  
 	
 	try
 	{
 		var count=0;
   		for(var i=0;i<=11;i++)
   			{   	 
   				chkBox = document.getElementById('check'+i).checked;
   				
   				if(chkBox == true)
   				{   		
   					id+=document.getElementById('check'+i).value+",";			 		
   			 		id1+=i+",";   				
   					count++;   		
   				}   			
   			}
   		id = id.substring(0,id.length-1);   
    
   		var selectedMonth = opener.document.getElementById(creditintervalID).value;

 	 	if(selectedMonth=='Qu')
  			{
   				
  				if(!(count==3))
  				{
  					alert("Please select only three months.");
  					
  					return false;
  				}
  			 
  			}
   		if(selectedMonth=='Hy')
  			{   				
  				if(!(count==6))
  				{
  					alert("Please select only six months.");
  					
  					return false;
  				}
  			 
  			}
  	 	if(selectedMonth=='Ye')
  			{   				
  				if(!(count==12))
  				{
  					alert("Please select only twelve months.");
  					
  					return false;
  				}
  			 
  			}
   		if(selectedMonth=='Mo')
   			{   				
  				if(!(count==1))
  				{
  					alert("Please select only one month.");  					
  					return false;
  				}
  			 
 		 	}
 		 
 		 if(selectedMonth=='')
  			{				 
  					alert("Please select Credit Interval.");  					
  					return false;  				 
 		 	}
  
   		opener.document.getElementById(intervalMonthID).value=id; 
 		window.close();
	}//End of try
	catch(e)
   			{
   				alert("Error"+e);
   			}

}

function setValue()
{
	 
      var oldval = opener.document.getElementById(intervalMonthID).value;
       var oldval_code = opener.document.getElementById(creditintervalID).value;
  		 fieldLis_codet=oldval_code.split(",");
  
  
      fieldList=oldval.split(",");
     
     
     
   for(k=0;k<=11;k++)
   { 
		     for(i=0; i < fieldList.length;i++) {
		    
		      if(document.getElementById('check'+k).value==fieldList[i] )
		      {
		      document.getElementById('check'+k).checked=true;
		      }  
		   }
   
   
    }

}
setValue();
</script>