<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelMode_input" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="pageFlag" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />

	<!-- flags for paging and navigation panel -->
	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Mode </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<!-- The Following code Denotes  Include JSP Page For Button Panel -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="travel.mode" id="travel.mode"
						ondblclick="callShowDiv(this);"><%=label.get("travel.mode")%></label>
					<font color="red">*</font>:</td>
					<td width="34%"><s:hidden name="trvId" /> <s:textfield
						name="trvMode" theme="simple" size="30" maxlength="15"
						onkeypress="return allCharacters();" /></td>
				</tr>

				<tr>
					<td width="10%" colspan="1"><label class="set"
						name="travel.sts" id="status" ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label>
					<font color="red">*</font>:</td>
					<td width="34%" height="22"><s:select name="status" 
						list="#{'':'--Select--','A':'Active','D':'Deactive'}" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr>



	</table>

</s:form>
<script>
	var f9Fields= ["paraFrm_trvMode",,"paraFrm_tableLength"];
	var fieldName = ["paraFrm_trvMode",,"paraFrm_tableLength"];
	var lableName = ["travel.mode",,"class.list"];
	var type = ['enter','enter','add'];
	var f9Fields1= ["paraFrm_trvMode"];
	var fieldName1 = ["paraFrm_trvMode"];
	var lableName1 = ["travel.mode"];


	//For Addnew Button 
	function addnewFun()
	{
		document.getElementById('paraFrm').action="TravelMode_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	
	// For Save Button

function saveFun()
{	
	try
	{
		var trMode=document.getElementById('paraFrm_trvMode').value;
		var trvModId=document.getElementById('paraFrm_trvId').value;
		var status=document.getElementById('paraFrm_status').value;
		var chktrMode = LTrim(trMode);		
		
		if(trvModId=="" )
		{
			
			if(chktrMode=="")
			{
				alert("Please enter "+document.getElementById('travel.mode').innerHTML.toLowerCase() );
				return false;
			}
			else if(status==""){
				alert("Please select "+document.getElementById('status').innerHTML.toLowerCase() );
				return false;
			}
		}
		
		else
		{
			if(!validateBlank(fieldName1, lableName1, type))
		    	return false;
		    	if(status==""){
				alert("Please select "+document.getElementById('status').innerHTML.toLowerCase() );
				return false;
			}
		    if(!f9specialchars(f9Fields1))
				return false;
		} 
		document.getElementById('paraFrm').action="TravelMode_save.action";
		document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		//alert(e);
	}
}

//For Report Button
function reportFun()
{
	//document.getElementById('paraFrm').action="TravelMode_report.action";
	//document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="TravelMode_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="TravelMode_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"TravelMode_f9Action.action");
}

//For Cancel Button

function cancelFun(){
     	document.getElementById("paraFrm").action="TravelMode_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }


function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="TravelMode_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}



	
  	
  	function callUP(id)
 	{   	 
 	  var pageNo=document.getElementById('paraFrm_show').value;
 	  //alert(id);
 	  if(id!=1)
 	  { 
 	     if(id%20==1)
       {
         var pgNumber =document.getElementById('selectname').value;
         var lastRecord = document.getElementById('paraFrm_lastRecordOfPage').value;
         var lastRecordArr = lastRecord.split(","); 
         document.getElementById('paraFrm_previousLevel').value = lastRecordArr[pgNumber-2];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else
       {
	 	   var preId= eval(id)-1;
	 	   var previous = document.getElementById('itLevel'+preId).value;
	 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
	 	   var current = document.getElementById('itLevel'+id).value;
	 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
 	   }
 	//alert('----'+pageNo); 
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	//alert(id);
 	if(id=="1")
 	{
  	alert("This is first record,so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="TravelMode_upData.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id)
 {  
 
       var len=document.getElementById('paraFrm_modeLength').value;  
 	//alert("length"+len);
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	//id=(pageNo-1)*20+id;
 	 //alert("id"+id);	
  	if(id==eval(len))
  	{    
  	alert("This is last record,so you cannot down the record.");
 	 return false;
 	 }
       if(id%20==0)
       {
         var pgNumber =document.getElementById('selectname').value;
        var firstRecord = document.getElementById('paraFrm_firstRecordOfPage').value;
        var firstRecordArr = firstRecord.split(",");
         //alert("pgNumber======== "+pgNumber+" ===== firstRecordArr[pgNumber-1]"+firstRecordArr[pgNumber-1]);
        document.getElementById('paraFrm_previousLevel').value = firstRecordArr[pgNumber-1];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else{
		       var preId= eval(id)+1;
		 	   var previous = document.getElementById('itLevel'+preId).value;
		 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
		 	   var current = document.getElementById('itLevel'+id).value;
		 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
 	     
 	
  	
   	document.getElementById('paraFrm_statusDown').value="true";
   	document.getElementById('paraFrm_statusUp').value="false";
   	document.getElementById('paraFrm_upId').value=id; 
   	document.getElementById('paraFrm').action="TravelMode_upData.action";
   	document.getElementById('paraFrm').submit(); 
 }
  	
  	
</script>
