<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
 var adverList = new Array();
</script>
<s:form action="Advertisement" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	 <tr>
	  <td colspan="3" width="100%">
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Advertisement </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td  width="70%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td>
			<td  width="30%"><div align="right"><font color="red">*</font>Indicates Required</div> </td>
		</tr>
		<tr>
			<td colspan="3" width="100%"> <s:hidden name="editFlag"/>  <s:hidden name="searchFlag"/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"><strong>Advertisement </strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>	
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2"><!--Table 1-->	
								<tr> 
									<td width="20%"><label  class = "set" name="reqCodeLabel" id="reqCodeLabel" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqCodeLabel")%></label><font color="red">*</font> :</td>
									<td width="25%">
									<s:if test="editFlag">
									      <s:hidden name="reqName" /> <s:property value="reqName"/>
									</s:if>
									<s:else> 
									      <s:textfield name="reqName" size="25" readonly="true" />
									</s:else> 
									<s:hidden  name="reqCode"/> <s:hidden  name="advertiseCode"/>
									<s:if test="editFlag"></s:if>
									<s:else>
										<img src="../pages/images/search2.gif" 	class="iconImage" height="16" align="absmiddle" width="16" 	onclick="javascript:callsF9(500,325,'Advertisement_f9Requistion.action');">
									</s:else>	
										</td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr>
								<tr>
									 
									<td width="20%"><label  class = "set" name="position" id="position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="25%">
									 <s:if test="editFlag">
									     <s:hidden name="postionName" /> <s:property value="postionName"/> 
									</s:if>
									 <s:else>
									       <s:textfield name="postionName" size="25" readonly="true" />
									</s:else> 
								 	<s:hidden name="postionId"/>
									 </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr>
								<tr> 
									<td width="20%"><label  class = "set" name="noVacc" id="noVacc" 
			            		ondblclick="callShowDiv(this);"><%=label.get("noVacc")%></label> :</td>
									<td width="25%">
									 <s:if test="editFlag">
									 <s:hidden name="noOfVaccany" /> <s:property value="noOfVaccany"/> 
									</s:if>
									 <s:else>
									      <s:textfield name="noOfVaccany" size="25" readonly="true" />
									</s:else> 
								    </td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr> 
								
								<tr>
									<td  colspan="4">&nbsp;</td> 
								</tr> 
							</table><!--Table 1-->	
						</td>
					</tr>		
				</table><!--Table 2-->	
			</td><!--end of vacancy search-->	
		</tr>
		
		 <tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="80%"><strong>Advertisement Details </strong></td>
						<td width="20%">	
						<s:if test="editFlag"></s:if>
						<s:else>
						   <table width="100%" border="0">
						     <tr>
						       <td width="48%">
						          <input type="button" 	class="add" value="  Add Row  "  onclick="return addRowToTableFunc();" /> 
						       </td>
						       <td width="52%">
						          <input type="button" class="delete" value="  Remove  " 	onclick="return deleteAdvertise();" />
						       </td>
						      </tr>
						   </table>  
						 </s:else>
						</td> 
					</tr> 
					<tr>
						<td width="100%" colspan="2" >
							<table width="100%"    border="0" cellpadding="1" cellspacing="1" class="sortable" id="advetiseTable"  ><!--Table 6-->
			          		 <s:if test="editFlag">
			            		  <tr >
									<td width="5%" valign="top" class="formth" nowrap="nowrap"> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td width="15%" valign="top" class="formth">
										<label  class = "set" name="mode.Advertisement" id="mode.Advertisement" ondblclick="callShowDiv(this);"><%=label.get("mode.Advertisement")%></label>
									</td>
									<td width="15%" valign="top" class="formth">
										<label  class = "set" name="name" id="name" ondblclick="callShowDiv(this);"><%=label.get("name")%></label> 
									</td>
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
								 	</td>   
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="end.Date" id="end.Date" ondblclick="callShowDiv(this);"><%=label.get("end.Date")%></label>
							    	</td>  
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="cost" id="cost" ondblclick="callShowDiv(this);"><%=label.get("cost")%></label> 
									</td>
									 
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="responses" id="responses" ondblclick="callShowDiv(this);"><%=label.get("responses")%></label>
									</td> 
			            		</tr> 
			            		
			            		<% int cnt =1; %>
			            		<s:iterator value="advertiseList"> 
				            		<tr  >
										<td width="5%"  class="sortableTD" nowrap="nowrap"><%=cnt%> </td>
										<td width="15%" class="sortableTD"> 
										   <s:hidden name="advertiseModeText"  id="<%="advertiseModeText"+cnt%>"/>  <s:hidden  name="advertiseMode" id="<%="advertiseMode"+cnt%>"  />
										   <s:property value="advertiseModeText"/>  &nbsp;
									    </td>
										<td width="15%" class="sortableTD"> 
										 <s:hidden  name="advertiseModeName" id="<%="advertiseModeName"+cnt%>" /> 
											 <s:property value="advertiseModeName"/>  &nbsp;
										</td>
										<td width="10%" class="sortableTD">
										<s:hidden  name="advertiseStartDate" id="<%="advertiseStartDate"+cnt%>" /> 
												 <s:property value="advertiseStartDate"/>  &nbsp;
										</td> 
										<td width="10%" class="sortableTD">
										 <s:property value="advertiseEndDate"/>  &nbsp;
										 	<s:hidden name="advertiseEndDate" id="<%="advertiseEndDate"+cnt%>" />  
										</td> 
										<td width="10%" class="sortableTD">
										 <s:property value="advertiseDetails"/> &nbsp;
										 	<s:hidden name="advertiseDetails" id="<%="advertiseDetails"+cnt%>" />   
										</td>
										<td width="10%"class="sortableTD"> 
										  <s:property value="advertiseCost"/>   &nbsp;
										 	<s:hidden name="advertiseCost" id="<%="advertiseCost"+cnt%>" />    
										</td> 
										<td width="10%" class="sortableTD"> 
										 <s:property value="advertiseResponse"/>   &nbsp;
										 	<s:hidden name="advertiseResponse" id="<%="advertiseResponse"+cnt%>" />    
										 
										</td> 
								 
				            		</tr>  
				            		<%cnt++;%>
			            		</s:iterator>
			            		</s:if>
			            		<s:else>
			            		
			                    <tr >
									<td width="5%" valign="top" class="formth" nowrap="nowrap"> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>  </td>
									<td width="15%" valign="top" class="formth">
										<label  class = "set" name="mode.Advertisement" id="mode.Advertisement" ondblclick="callShowDiv(this);"><%=label.get("mode.Advertisement")%></label> <font color="red">*</font>
									</td>
									<td width="15%" valign="top" class="formth">
										<label  class = "set" name="name" id="name" ondblclick="callShowDiv(this);"><%=label.get("name")%></label> <font color="red">*</font>
									</td>
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
									<br><font style="size: 6" >(dd-mm-yyyy)</font>
									</td> 
									<td width="10%" valign="top" class="formth"> &nbsp;	</td> 
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="end.Date" id="end.Date" ondblclick="callShowDiv(this);"><%=label.get("end.Date")%></label>
									 <br> <font style="size: 6" >(dd-mm-yyyy)</font> 
									</td> 
									<td width="10%" valign="top" class="formth"> &nbsp;	</td>
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
										<td  valign="top" class="formth"> &nbsp;	</td> 
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="cost" id="cost" ondblclick="callShowDiv(this);"><%=label.get("cost")%></label> <font color="red">*</font>
									</td>
									 
									<td width="10%" valign="top" class="formth">
										<label  class = "set" name="responses" id="responses" ondblclick="callShowDiv(this);"><%=label.get("responses")%></label>
									</td> 
									<td width="10%" valign="top" class="formth"> &nbsp;	</td>
			            		</tr> 
			            		
			            		<% int cnt1 =1; %>
			            		<s:iterator value="advertiseList"> 
				            		<tr  >
										<td width="5%"  class="sortableTD" nowrap="nowrap"><%=cnt1%><s:hidden name="advertiseModeText" id="<%="advertiseModeText"+cnt1%>" />   </td>
										<td width="15%" class="sortableTD">  
										   <s:select headerKey="0" headerValue="Select" name="advertiseMode" id="<%="advertiseMode"+cnt1%>"  list="#{'N':'News Paper','T':'TV Media','W':'Website','O':'Other'}"></s:select>
									    </td>
										<td width="15%" class="sortableTD">
											<s:textfield name="advertiseModeName" id="<%="advertiseModeName"+cnt1%>" size="15" maxLength="50" />
										</td>
										<td width="10%" class="sortableTD">
										 <s:textfield name="advertiseStartDate" id="<%="advertiseStartDate"+cnt1%>" size="10" maxlength="10" onkeypress="return numbersWithHiphen();" /> 
										</td>
										<td width="2%"  class="sortableTD"> <img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('<%="advertiseStartDate"+cnt1%>','DDMMYYYY');"> </td>
										<td width="10%" class="sortableTD">
											<s:textfield name="advertiseEndDate" id="<%="advertiseEndDate"+cnt1%>" size="10" maxlength="10"  onkeypress="return numbersWithHiphen();" /> 
										</td>
										
										 <td width="2%"  class="sortableTD"> <img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('<%="advertiseEndDate"+cnt1%>','DDMMYYYY');"> </td>
												
										<td width="10%" class="sortableTD">
											 <s:textarea name="advertiseDetails"  id="<%="advertiseDetails"+cnt1%>"   cols="20" rows="1"   /> 
										</td>
										
										<td class="sortableTD">	<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
									 	onclick="javascript:callWindow('<%="advertiseDetails"+cnt1 %>','details','aa','500','500');" >
										</td>
										<td width="10%"class="sortableTD">
											 <s:textfield name="advertiseCost" id="<%="advertiseCost"+cnt1%>" size="10" maxlength="10" onkeypress="return numbersOnly();"  />
										</td> 
										<td width="10%" class="sortableTD"> 
											 <s:textfield name="advertiseResponse" id="<%="advertiseResponse"+cnt1%>" size="5" maxlength="25" onkeypress="return numbersOnly();" readonly="true"  />
										</td> 
										
										<td width="10%" class="sortableTD"> 
											 <input type="checkbox" name="adverChkBox" id="adverChkBox<%=cnt1%>" onclick="callChkBox('<%=cnt1%>');">
											 <input type="hidden" name="hidAdverChkBox" id="hidAdverChkBox<%=cnt1%>">
										</td>  
				            		</tr>  
				            		<%cnt1++;%>
			            		</s:iterator>
			            		
			            		</s:else>
			            		</table>
			            		
                      </td>
					</tr>
					   
				</table>
		  	</td>
		</tr>
		
			<tr>
			  <td  width="100%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td> 
		</tr>
	</table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
  
   function saveFun()
   { 
       var tbl = document.getElementById('advetiseTable');
	   var rowNum = tbl.rows.length;  
	   
	   var reqCode = document.getElementById('paraFrm_reqCode').value;
	   if(reqCode=="")
	   {
		  alert("Please select the "+document.getElementById('reqCodeLabel').innerHTML.toLowerCase());
		   document.getElementById('paraFrm_reqCode').focus();
		      return false;	    
	   } 
	   for(i=1 ; i<rowNum ; i++){
	
           var advertiseMode = document.getElementById('advertiseMode'+i).value;
           var advertiseModeName = trim(document.getElementById('advertiseModeName'+i).value);
           var advertiseStartDate = document.getElementById('advertiseStartDate'+i).value;
           var advertiseEndDate = document.getElementById('advertiseEndDate'+i).value; 
           var advertiseCost = document.getElementById('advertiseCost'+i).value; 
           var details  = document.getElementById('advertiseDetails'+i).value;    
           if(!(i>1 & (advertiseMode=="0" || advertiseMode=="") & advertiseModeName=="" &  (advertiseCost==""||advertiseCost=="0")))
           { 
        
		   if(advertiseMode=="0" || advertiseMode=="")
		   {
			    alert("Please select the "+document.getElementById('mode.Advertisement').innerHTML.toLowerCase());
			     document.getElementById('advertiseMode'+i).focus();
			    return false;	    
		   }
		        
	      if(advertiseModeName=="")
		   {
			    alert("Please enter the "+document.getElementById('name').innerHTML.toLowerCase());
			    document.getElementById('advertiseModeName'+i).value="";
			    document.getElementById('advertiseModeName'+i).focus();
			    return false 	    
		   } 

          /*    var advMode= document.getElementById('advertiseMode'+i).value; 
		   	   if(advMode=="W")
		   	   {
		   	    if(!validateWebSite('advertiseModeName'+i))
		   	    { 
		   	      document.getElementById('advertiseModeName'+i).focus();
		   	     return false;
		   	    }
		   	   }
		   */
		   
		   
	     if(!validateDate("advertiseStartDate"+i,'start.date')){
	         document.getElementById('advertiseStartDate'+i).focus();
				return false; 
			}   
			 
			if(!validateDate("advertiseEndDate"+i,'end.Date')){
	            document.getElementById('advertiseEndDate'+i).focus();
				return false; 
			}    
			if(!dateDifferenceEqual(advertiseStartDate, advertiseEndDate, "advertiseStartDate"+i, 'start.date','end.Date')){
	            document.getElementById('advertiseEndDate'+i).focus();
				return false; 
			}    
			
			  if(advertiseCost=="")
		     {
			    alert("Please enter the "+document.getElementById('cost').innerHTML.toLowerCase());
			    document.getElementById('advertiseCost'+i).focus();
			    return false; 	    
		    }  
		    
		    if(details.length >500){
		  		  alert("Maximum length of "+document.getElementById('details').innerHTML.toLowerCase()+" is 500 characters.");
		  		  document.getElementById('advertiseDetails'+i).focus();
		  		  document.getElementById('advertiseDetails'+i).style.background = '#FFFF99';
		  		  return false; 
		  		}
		} 
		}
		if(!checkDuplicateAdvertiseMode())
		 {
		   return false;
		 }
		 
		 
     document.getElementById('paraFrm').action="Advertisement_saveAdvertise.action"; 
     document.getElementById('paraFrm').submit(); 
   }
   
   
      function checkDuplicateAdvertiseMode()
      { 
       var tbl = document.getElementById('advetiseTable');
	   var rowNum = tbl.rows.length;  
	    var isDuplicate ="false"; 
		    for(i=1 ; i<rowNum ; i++){
		         var advertiseMode = document.getElementById('advertiseMode'+i).value ; 
		           for(j=(i+1) ; j<rowNum ; j++){ 
		             var adMode = document.getElementById('advertiseMode'+j).value;    
			           if(advertiseMode==adMode)
			             {  
							  var advertiseModeName = LTrim(RTrim(document.getElementById('advertiseModeName'+i).value.toUpperCase())); 
						      var adName = LTrim(RTrim(document.getElementById('advertiseModeName'+j).value.toUpperCase())); 
						      if(advertiseModeName==adName)
								 { 
							              isDuplicate ="true" ;
								 }      
							    if(isDuplicate =="true")
							     {
							       alert("Duplicate Row found.");
							      return false;
						       }
			            } 
		         }     
	    return true;
     } 
   }
   
 
   function editFun()
   { 
     document.getElementById('paraFrm').action="Advertisement_edit.action";
     document.getElementById('paraFrm').submit(); 
   }
   
   function cancelFun()
   { 
     document.getElementById('paraFrm').action="Advertisement_cancel.action";
     document.getElementById('paraFrm').submit(); 
   }
   
 callOnLoad() ;
 function callOnLoad()
 {     var tbl = document.getElementById('advetiseTable');
	   var lastRow = tbl.rows.length; 
	   if(lastRow==1)
	   {  addRowToTableFunc(); 
	      }
 }
 
function addRowToTableFunc()
	{
		  var tbl = document.getElementById('advetiseTable');
		  var lastRow = tbl.rows.length;		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow; 
		//  if(chkDuplicate(iteration)){
		  var row = tbl.insertRow(lastRow);
		  // left cell
		  var cellLeft = row.insertCell(0);
		  cellLeft.className='sortableTD';
		  var textNode = document.createTextNode(iteration);
		  cellLeft.align='left';
		  cellLeft.appendChild(textNode);
		  
	      var cellFuncName = row.insertCell(1);
		   cellFuncName.className='sortableTD';
		  var funcName = document.createElement('select');
		   funcName.name = 'advertiseMode' ;
		    funcName.id='advertiseMode'+iteration ;
		   funcName.cssClass='border2'; 
		    funcName.options[0] = new Option('Select','0');
            funcName.options[1] = new Option('News Paper', 'N');
            funcName.options[2] = new Option('TV Media', 'T'); 
            funcName.options[3] = new Option('Website', 'W');
            funcName.options[4] = new Option('Other', 'O'); 
		   cellFuncName.appendChild(funcName);
 
		    var cellModeName = row.insertCell(2);
		     cellModeName.className='sortableTD';
		  var modeName = document.createElement('input');
		  modeName.type = 'text';
		  modeName.size ='15';
		  modeName.name = 'advertiseModeName' ;
		  modeName.maxLength='50'; 
		  modeName.id = 'advertiseModeName' + iteration; 
		  cellModeName.appendChild(modeName);
 
		  var cellfuncModeStart = row.insertCell(3);
		  cellfuncModeStart.className='sortableTD';
		  var funStartDate = document.createElement('input');
		  funStartDate.type = 'text';
		  funStartDate.name = 'advertiseStartDate';
		  funStartDate.id = 'advertiseStartDate'+iteration;
		  funStartDate.size ='10';
		  funStartDate.maxLength='10'; 
		  funStartDate.onkeypress= function(){
		  		document.onkeypress = numbersWithHiphen;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }
		  cellfuncModeStart.appendChild(funStartDate);	
		  
		  
		  	  var cellRelDtImg= row.insertCell(4);
		  cellRelDtImg.className='sortableTD';
		  var RelDtImg=  document.createElement('img');
		  RelDtImg.type='image';
		  RelDtImg.src="../pages/images/Date.gif";
		  RelDtImg.height='16';
		  RelDtImg.align='absmiddle';
		  RelDtImg.width='16';
		  RelDtImg.id='img'+ iteration;
		  RelDtImg.theme='simple';
		  RelDtImg.align="center";
		  RelDtImg.onclick=function(){	 	
		 
				NewCal('advertiseStartDate'+iteration,'DDMMYYYY');
	 		
	     };
	     	  		 cellRelDtImg.appendChild(RelDtImg);
		  
		    var cellfuncModeEnd = row.insertCell(5);
		  cellfuncModeEnd.className='sortableTD';
		  var funEndDate = document.createElement('input');
		  funEndDate.type = 'text';
		  funEndDate.name = 'advertiseEndDate';
		  funEndDate.id = 'advertiseEndDate'+iteration;
		  funEndDate.size ='10';
		  funEndDate.maxLength='10'; 
		  funEndDate.onkeypress= function(){
		  		document.onkeypress = numbersWithHiphen;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
        } 
		  cellfuncModeEnd.appendChild(funEndDate);	
		 	
		  
		   var cellRelDtImg= row.insertCell(6);
		  cellRelDtImg.className='sortableTD';
		  var RelDtImg=  document.createElement('img');
		  RelDtImg.type='image';
		  RelDtImg.src="../pages/images/Date.gif";
		  RelDtImg.height='16';
		  RelDtImg.align='absmiddle';
		  RelDtImg.width='16';
		  RelDtImg.id='img'+ iteration;
		  RelDtImg.theme='simple';
		  RelDtImg.align="center";
		  RelDtImg.onclick=function(){	 	
		 
				NewCal('advertiseEndDate'+iteration,'DDMMYYYY');
	 		
	     };  
		   		 cellRelDtImg.appendChild(RelDtImg);
		  
		  var cellfuncDetails = row.insertCell(7);
		  cellfuncDetails.className='sortableTD';
		  var funcDetails = document.createElement('textArea'); 
		  funcDetails.rows ='1';
		  funcDetails.cols='20';
		  funcDetails.name = 'advertiseDetails';
		  funcDetails.id = 'advertiseDetails'+iteration;  
		  funcDetails.onkeyup= function()
		   {   
			   var fieldNameVal =document.getElementById('advertiseDetails'+iteration).value;
			   var textVal="";
			   var charStr;
					for(var i =0; i < eval(fieldNameVal.length);i++)
					 {
					   charStr =fieldNameVal.substring(i,eval(i+1));
					     if(charStr=='\n') { 
						    textVal=textVal+'  '; 
						 }else {
						     textVal=textVal+charStr;
						    }
					}  // end of for loop
				 			 
				var remain = 500 - eval(textVal.length);  
				if(eval(remain)< 0){ 
					document.getElementById('advertiseDetails'+iteration).style.background = '#FFFF99';
				}else 
				{
				 document.getElementById('advertiseDetails'+iteration).style.background = '#FFFFFF';
				 } 
          }
  
		  cellfuncDetails.appendChild(funcDetails);		
		  
		     		   var cellTextImg= row.insertCell(8);
		  cellTextImg.className='sortableTD';
		  var textImg=  document.createElement('img');
		  textImg.type='image';
		  textImg.src="../pages/images/zoomin.gif";
		  textImg.height='12';
		  textImg.align='absmiddle';
		  textImg.width='12';
		  textImg.id='img'+ iteration;
		  textImg.theme='simple';
		  textImg.align="center";
		  textImg.onclick=function(){	 
				callWindow('advertiseDetails'+iteration,'details','aa','500','500')
	 		
	     }; 
     	 cellTextImg.appendChild(textImg);   
		     
		  var cellfuncCost = row.insertCell(9);
		  cellfuncCost.className='sortableTD';
		  var funcCost = document.createElement('input');
		  funcCost.type = 'text';
		  funcCost.name = 'advertiseCost';
		  funcCost.id = 'advertiseCost'+iteration;
		  funcCost.size ='10';
		  funcCost.maxLength='10'; 
		  funcCost.onkeypress= function(){
		  		document.onkeypress = numbersOnly;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( key > 47 && key < 58 || key == 8 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }
		  cellfuncCost.appendChild(funcCost);		
		  
		 
		   var cellfuncResponse= row.insertCell(10);
		  cellfuncResponse.className='sortableTD';
		  var funcResponse = document.createElement('input');
		  funcResponse.type = 'text';
		  funcResponse.name = 'advertiseResponse';
		  funcResponse.id = 'advertiseResponse'+iteration;
		  funcResponse.readOnly='true';
		  funcResponse.size ='5';
		  funcResponse.maxLength='5'; 
		  funcResponse.onkeypress= function(){
		  		document.onkeypress = numbersOnly;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( key > 47 && key < 58 || key == 8 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }
		  cellfuncResponse.appendChild(funcResponse);	 
		   
		  
        var cellFuncChk = row.insertCell(11);
		  cellFuncChk .className='sortableTD';
		  var Chk = document.createElement('input');
		 
		  Chk.type = 'checkbox';
		  Chk.name = 'adverChkBox';
		  Chk.id = 'adverChkBox'+iteration;
		 Chk.onclick=function(){
		 		if(document.getElementById('adverChkBox'+iteration).checked == true)
			   {	
			    		document.getElementById('hidAdverChkBox'+iteration).value="Y";
			   }  else{
			  			 document.getElementById('hidAdverChkBox'+iteration).value="N";
		   		}
		};
		  cellFuncChk.align='center'; 	
		  cellFuncChk.appendChild(Chk); 
		  
		  var cellFuncDel = row.insertCell(12);
		  var funcDel = document.createElement('input');
		  funcDel.type = 'hidden';
		  funcDel.name = 'hidAdverChkBox';
		  funcDel.id = 'hidAdverChkBox'+iteration;
		  cellFuncDel.appendChild(funcDel);
		  
		  var cellFunText= row.insertCell(13);
		  var funcText = document.createElement('input');
		  funcText.type = 'hidden';
		  funcText.name = 'advertiseModeText';
		  funcText.id = 'advertiseModeText'+iteration;
		  cellFunText.appendChild(funcText);
   }   
   
   function deleteAdvertise()
   {
   
       var tbl = document.getElementById('advetiseTable');
	   var rowNum = tbl.rows.length;  
	   var  flag ="false";
	   for(i=1 ; i<rowNum ; i++){ 
               var advertiseMode = document.getElementById('hidAdverChkBox'+i).value; 
               if(advertiseMode=="Y")
               {
               flag ="true";
               }
           }
           if(flag=="true")
           {
		     	var conf=confirm("Do you really want to remove the records ?");
				if(conf){ 
						     document.getElementById('paraFrm').action="Advertisement_deleteDetailList.action";
						     document.getElementById('paraFrm').submit();
				 }  else
				 {
				  return false;
				 }
		 }else{
		    alert("Please select atleast one checkbox");
		   return false;		   
		 }
   }
 
 
  function deleteFun()
   {
   	   var conf=confirm("Do you really want to delete the records ?");
		if(conf){ 
				     document.getElementById('paraFrm').action="Advertisement_delete.action";
				     document.getElementById('paraFrm').submit();
		 }  else
		 {
		    return false;
		 }
   }
 function callChkBox(iteration){
		 		if(document.getElementById('adverChkBox'+iteration).checked == true)
			   {	
			    		document.getElementById('hidAdverChkBox'+iteration).value="Y";
			   }  else{
			  			 document.getElementById('hidAdverChkBox'+iteration).value="N";
		   		}
		  } 

</script>