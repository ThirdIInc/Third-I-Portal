<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>

<s:form action="HotelMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />



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
					<td width="93%" class="txt"><strong class="text_head">Hotel
					Master </strong></td>

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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>

					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"></span> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="20%"><label class="set" name="hotelname"
						id="hotelname" ondblclick="callShowDiv(this);"><%=label.get("hotelname")%></label>
					<font color="red">*</font>:</td>


					<td><s:textfield size="25" theme="simple" maxlength="60"
						name="hotelName" onkeypress="return allCharacters();" /></td>
				</tr>



				<tr>
					<td><label class="set" name="hoteltype" id="hoteltype"
						ondblclick="return roomAvailable;"><%=label.get("hoteltype")%></label><font
						color="red">*</font> :</td>

					<td><s:hidden theme="simple" name="typeId" /><s:textfield
						size="25" theme="simple" name="hotelType" readonly="true" /> <s:if
						test="hotelMaster.viewFlag">
						<img id='ctrlHide' align="middle"
							src="../pages/common/css/default/images/search2.gif"
							onclick="callSearch('f9type');">
					</s:if></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="contactperson"
						id="contactperson" ondblclick="callShowDiv(this);"><%=label.get("contactperson")%></label>
					<font color="red">*</font>:</td>


					<td><s:textfield size="25" theme="simple" maxlength="60"
						name="contactPerson" onkeypress="return allCharacters();" /></td>
				</tr>

				<tr>
					<td><label name="hoteladdress" class="set" id="hoteladdress"
						ondblclick="callShowDiv(this);"><%=label.get("hoteladdress")%>
					</label><font color="red">*</font>:</td>

					<td colspan="4"><s:textarea name="hotelMaster.address"
						cols="68" rows="3" onkeyup="callLength('addCnt');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_hotelMaster_address','hoteladdress','','2000','2000');">
					<td id='ctrlHide'>Remaining chars<s:textfield name="addCnt"
						readonly="true" size="5"></s:textfield></td>



				</tr>

				<tr>

					<td><label class="set" name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label> :<font
						color="red">*</font></td>

					<td><s:hidden theme="simple" name="cityId" /><s:textfield
						size="25" theme="simple" name="city" readonly="true" /> <s:if
						test="hotelMaster.viewFlag">
						<img id='ctrlHide' align="absmiddle"
							src="../pages/common/css/default/images/search2.gif"
							onclick="callSearch('f9city');">
					</s:if></td>
					<td></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" name="phone1" id="phone1"
						ondblclick="callShowDiv(this);"><%=label.get("phone1")%></label> :</td>
					<td><s:textfield size="25" theme="simple" maxlength="60"
						name="phone1" onkeypress="return validatePhoneNo();" /></td>
				</tr>

				<tr>

					<td width="20%"><label class="set" name="phone2" id="phone2"
						ondblclick="callShowDiv(this);"><%=label.get("phone2")%></label> :</td>
					<td><s:textfield size="25" theme="simple" maxlength="60"
						name="phone2" onkeypress="return validatePhoneNo();" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="emailid "
						id="emailid" ondblclick="callShowDiv(this);"><%=label.get("emailid")%></label><font
						color="red">*</font> :</td>

					<td><s:textfield size="25" theme="simple" maxlength="60"
						name="emailId" /></td>
				</tr>
				
				
				
				<tr>
					<td width="20%"><label class="set" name="zoneName "
						id="zoneName" ondblclick="callShowDiv(this);"><%=label.get("zoneName")%></label> :</td>

					<td><s:select name="zoneName" list="zonemap" size="1" /></td>
				</tr>
				
				
				<tr>
					<td width="20%"><label class="set" name="dinnerPackage "
						id="dinnerPackage" ondblclick="callShowDiv(this);"><%=label.get("dinnerPackage")%></label> :
					</td>
		<!-- <td><s:checkbox id="dinner"	name="dinnerPackage" value="%{dinnerPackage}" onclick="showTr();"/></td> -->
					<td><s:checkbox id="dinner"	name="dinnerPackage" onclick="showTr();" /></td>
				</tr>
				
				 
				<tr id="show">
					<td width="20%"><label class="set" name="dinnerPackageCost "
						id="dinnerPackageCost" ondblclick="callShowDiv(this);"><%=label.get("dinnerPackageCost")%></label> :</td>

					<td><s:textfield size="25" maxlength="10"  id="dinnerPkg"
						name="dinnerPackageCost" onkeypress="return numbersWithDot();" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label class="set" name="distanceFromAirport "
						id="distanceFromAirport" ondblclick="callShowDiv(this);"><%=label.get("distanceFromAirport")%></label><font
						color="red">*</font> :
					</td>
					
					<td ><s:textfield size="25" theme="simple" maxlength="6"
						name="distanceFromAirport" onkeypress="return numbersWithDot();"/></td>
				</tr>				
				
				<tr>
					<td width="20%"><label class="set" name="averageRating "
						id="averageRating" ondblclick="callShowDiv(this);"><%=label.get("averageRating")%></label> :
					</td>
					
					<td><s:property	value="averageRating" /></td>
				</tr>
				
				<tr>
					<td><label name="remark" class="set" id="remark"
						ondblclick="callShowDiv(this);"><%=label.get("remark")%> </label>:</td>

					<td colspan="4"><s:textarea name="hotelMasterRemark" cols="68"
						rows="3" onkeyup="callLengthRemarks('addCnt1');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_hotelMasterRemark','remark','','2000','2000');">
					<td id='ctrlHide'>Remaining chars<s:textfield name="addCnt1"
						readonly="true" size="5"></s:textfield></td>
				</tr>


			</table>
			</td>
		</tr>

		<s:if test="roomList">
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

								<td height="15" class="formhead" nowrap="nowrap"><strong
									class="forminnerhead"><label class="set" id="roomlist"
									name="roomlist" ondblclick="callShowDiv(this);"><%=label.get("roomlist")%></label></strong></td>
							</tr>
							<tr>

								<td class="formth" align="center" width="5%"><label
									class="set" id="sr.no" name="sr.no"
									ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
								<td class="formth" align="center" width="50%"><label
									class="set" id="roomtype" name="roomtype"
									ondblclick="callShowDiv(this);"><%=label.get("roomtype")%></label></td>

								<td class="formth" align="center" width="15%"><label
									class="set" id="actualrate" name="actualrate"
									ondblclick="callShowDiv(this);"><%=label.get("actualrate")%></label></td>
								<td class="formth" align="center" width="15%"><label
									class="set" id="corporaterate" name="corporaterate"
									ondblclick="callShowDiv(this);"><%=label.get("corporaterate")%></label></td>
								<td class="formth" align="center" width="15%"><label
									class="set" id="isbreakfast" name="isbreakfast"
									ondblclick="callShowDiv(this);"><%=label.get("isbreakfast")%></label></td>



								<%
								int srNo = 1;
								%>

								<s:iterator value="roomList">

									<tr>
										<td width="5%" align="left" class="sortableTD"><%=srNo++%></td>
										<td width="50%" align="left" class="sortableTD"><s:hidden
											name="roomtypeId" value="%{roomtypeId}"></s:hidden><s:property
											value="roomtypeName" /></td>

										<td width="15%" align="center" class="sortableTD"><s:textfield
											cssStyle="text-align: right;" maxlength="8" name="actualRate"
											onkeypress="return numbersWithDot();" /></td>

										<td width="15%" align="center" class="sortableTD"><s:textfield
											cssStyle="text-align: right;" maxlength="8"
											name="corporateRate" onkeypress="return numbersWithDot();" /></td>


										<td width="15%" align="center" class="sortableTD"><s:select name="isbreakFast" 
											list="#{'Y':'Yes','N':'No'}"
											cssStyle="width:151;z-index:5;" /></td>

									</tr>
									</td>
								</s:iterator>

							</tr>
						</table>

						<%
								} catch (Exception e) {
								e.printStackTrace();
							}
						%>
						</td>
					</tr>

					</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<s:hidden name="hiddencode" />

	</table>
</s:form>


<script>
 
 document.getElementById('dinnerPkg').readOnly=true;
 //showTr();
function showTr()
{
	if(document.getElementById('dinner').checked)
	{
 		document.getElementById('dinnerPkg').readOnly=false;
 		return false;
	}
	else
	{
		//document.getElementById('dinnerPkg').value='';
		document.getElementById('dinnerPkg').readOnly=true;
	}
}

function saveFun() {
 
 
		var hotelnamevalue = trim(document.getElementById('paraFrm_hotelName').value);
		 
     	 
     
     	 if(hotelnamevalue==""){
		  alert("Please enter "+document.getElementById('hotelname').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_hotelName').focus();
		 return false;
		
		}
		
		var hoteltype = trim(document.getElementById('paraFrm_hotelType').value); 
     	 
     	 if(hoteltype==""){
		  alert("Please select "+document.getElementById('hoteltype').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_hotelType').focus();
		 return false;
		
		}
		
		var contactpersonname = trim(document.getElementById('paraFrm_contactPerson').value); 
     	 
     	 if(contactpersonname ==""){
		  alert("Please enter "+document.getElementById('contactperson').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_contactPerson').focus();
		 return false;
		
		}
		
		var length_Desc=2000;
 	var addresdetails =document.getElementById('paraFrm_hotelMaster_address').value;
			if(addresdetails=="")
	{
	alert("Please Enter the Hotel Address");
	document.getElementById('paraFrm_address').focus();
		 return false;
	
	}
					
	var cityvalue = trim(document.getElementById('paraFrm_city').value); 
     	 
     	 if(cityvalue==""){
		  alert("Please select "+document.getElementById('city').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_city').focus();
		 return false;
		
		}
	
	
	var emaifield = trim(document.getElementById('paraFrm_emailId').value); 
     	 
     	 if(emaifield==""){
		  alert("Please enter "+document.getElementById('emailid').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_emailId').focus();
		 return false;
		
		}else{
		 if(!validateEmail('paraFrm_emailId')){
		 	return false;
		 }
		}
		
		var distanceFromAirportField = document.getElementById('paraFrm_distanceFromAirport').value;
		if(distanceFromAirportField=="")
		{	
			alert("Please enter "+document.getElementById('distanceFromAirport').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_distanceFromAirport').focus();
		  return false;
		}
	   document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'HotelMaster_save.action';
		document.getElementById('paraFrm').submit();
  	  	return true ;
  
	 }


function resetFun() {
		document.getElementById('paraFrm').target = "_self";
     	
  		document.getElementById('paraFrm').action = 'HotelMaster_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    return true;
  	}
function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HotelMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}


function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HotelMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}


function callSearch(action) {

try{
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'HotelMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	catch(e){
	alert(e);
	}
	
	}	
		
		
		function editFun() {
		return true;
		//document.getElementById('paraFrm').action = 'HotelMaster_edit.action';
		//document.getElementById('paraFrm').submit();
		
	}	



function callLength(type){ 
		
		 if(type=='addCnt'){
					
					/*alert("i am in calllength method>>>>>>");*/
					
					var add =document.getElementById('paraFrm_hotelMaster_address').value;
					var remain = 2000 - eval(add .length);
					document.getElementById('paraFrm_addCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_hotelMaster_address').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_hotelMaster_address').style.background = '#FFFFFF';
				
				}
				} 
				
	function callLengthRemarks(type){
		
		 if(type=='addCnt1'){
					
				//alert("i am in calllength method>>>>>>");
					
					var add =document.getElementById('paraFrm_hotelMasterRemark').value;
					var remain = 2000 - eval(add .length);
					document.getElementById('paraFrm_addCnt1').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_hotelMasterRemark').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_hotelMasterRemark').style.background = '#FFFFFF';
				
				}
				} 


function roomAvailable()

{
       document.getElementById('paraFrm').target = "_self";
       document.getElementById('paraFrm').action = 'HotelMaster_setRoom.action';
       document.getElementById('paraFrm').submit(); 
       return true;


}


</script>


