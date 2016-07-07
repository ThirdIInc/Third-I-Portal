<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ConferenceApproval" id="paraFrm" validate="true"
	theme="simple" target="main">
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
					<td width="93%" class="txt"><strong class="text_head">Conference
					Booking Approval </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>

					<td height="27" class="formtxt"><a
						href="ConferenceApproval_ckeckdata.action?status=P"> Pending
					List</a> | <a href="ConferenceApproval_ckeckdata.action?status=A">
					Approved List</a> | <a
						href="ConferenceApproval_ckeckdata.action?status=R"> Rejected
					List/Canceled List</a> <!--                        	 | <a href="TrainingApproval_ckeckdata.action?status=H"> On Hold List</a>-->
					<s:hidden name="confAppStatus" /></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength" />
				<s:hidden name="approveFlag"/>

			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><s:if test="apprflag"></s:if> <s:else>
						<br />
						<input type="button" class="save" theme="simple" value="    Save "
							onclick="return saveValid(this)" />
					</s:else> <input name="Submit3" theme="simple" type="button" class="reset"
						value="    Reset" onclick="resetStatus();" /></td>
					<!--<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>-->
					</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td height="27" class="formtxt"><strong> <%String status = (String)request.getAttribute("stat");
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Pending List");}%> </strong></td>
				</tr>

				<tr>
					<td><s:hidden name="hiddenStatus" />
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<!--                        	class="formth"-->
							<td width="5%" height="22" valign="top" align="center"
								class="formth">Sr.no</td>
							<td width="8%" valign="top" align="center" class="formth"><label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
							<td width="20%" valign="top" align="center" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td width="8%" valign="top" align="center" class="formth"><label  class = "set" name="conference.date" id="conference.date" ondblclick="callShowDiv(this);"><%=label.get("conference.date")%></label></td>
							<td width="10%" valign="top" align="center" class="formth"><label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
							<td width="15%" valign="top" align="center" class="formth"><label  class = "set" name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label></td>
							<td width="15%" valign="top" align="center" class="formth"><label  class = "set" name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
							<td width="20%" valign="top" align="center" class="formth"><label  class = "set" name="fromToTime " id="fromToTime " ondblclick="callShowDiv(this);"><%=label.get("fromToTime")%></label></td>							
							<td width="10%" valign="top" align="center" class="formth"><label  class = "set" name="cancel" id="cancel" ondblclick="callShowDiv(this);"><%=label.get("cancel")%></label></td>
						</tr>
						<s:if test="noData">
							<tr>
								<td width="100%" colspan="7" align="center"><font
									color="red">There is no data to display</font></td>
							</tr>
						</s:if>
						<%String statusIterator = (String)request.getAttribute("statusIterator");%>
						<%! int i =0; %>
						<%int k = 1; int c=0;%>
						<s:iterator value="list" status="stat">
							<tr class="border2">
								<!--	                        class="border2"-->

								<td class="sortableTD" width="5%"><%= k%></td>
								<td class="sortableTD" width="8%"><s:hidden
									name="conferenceID" /> <s:hidden name="empID" value="%{empID}"></s:hidden>
								<s:property value="empToken" /></td>
								<td class="sortableTD" width="20%" align="left"><s:property
									value="empName" /></td>
								<td class="sortableTD" width="10%"><s:property
									value="appliedDate" /><s:hidden name="level" /></td>
								<td class="sortableTD" width="8%"><s:if test="apprflag">
									<s:property value="statusIterator" />
								</s:if> <s:else>
									<s:select name="status"
										list="#{'P':'Pending','A':'Approved','R':'Rejected','B':'SentBack','C','Cancelled'}"
										id="<%="check"+k %>" cssStyle="width:100" theme="simple" onchange="return checkAvailabilty(this);" />
								</s:else></td>
								<td class="sortableTD" width="10%" align="center"><input
									type="button" name="view_Details" class="pagebutton"
									value=" View Details "
									onclick="viewDetails('<s:property value="conferenceID"/>')" />

								<s:hidden name="conferenceCode" /> <s:hidden name="empCode" /><s:hidden name='IsConfRoomAvailable' id="<%="IsConfRoomAvailable"+k %>"/></td>
								<td class="sortableTD" width="15%" align="left"><s:if test="apprflag">
									<s:property value="comment" />&nbsp;</s:if>
									<s:else>
										<s:textarea theme="simple" cols="34" rows="2" name="comment"
										id="<%="comm"+k %>" onkeyup="callLength(this);"/>
									<img src="../pages/images/zoomin.gif"
									height="12" align="absmiddle" id='ctrlHide' width="12"
									theme="simple"
									onclick="javascript:callWindow('<%="comm"+k %>','comments','','500','500');">
								</s:else></td>
								<td class="sortableTD" width="20%"><s:property
										value="confFromTime" />&nbsp;-&nbsp;<s:property value="confToTime" /></td>
								<td class="sortableTD" width="10%" align="center"><s:if test="cancelAppFlag">
									<input type="button" name="cancel_btn" value="Cancel"  onclick="cancelAppFun('<s:property value="conferenceID"/>');"></s:if></td>								
							</tr>
							<%k++; c++;%>
						</s:iterator>
						<%i=k; %>
					</table>
					<input type="hidden" name="count" id="count" value="<%=c%>" /></td>
				</tr>

			</table>
		</tr>
		<!--<tr>
			<td class="cellbg">
			<table width="130" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><strong>Page </strong></td>
					<td><img src="../pages/images/recruitment/first.gif"
						width="10" height="10" /></td>
					<td><img src="../pages/images/recruitment/previous.gif"
						width="10" height="10" /></td>
					<td>
					<div align="center">0-1</div>
					</td>
					<td><img src="../pages/images/recruitment/next.gif" width="10"
						height="10" /></td>
					<td><img src="../pages/images/recruitment/last.gif" width="10"
						height="10" /></td>
				</tr>
			</table>
			</td>
		</tr>-->
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td><s:if test="apprflag"></s:if> <s:else>
						<br />
						<input type="button" class="save" theme="simple" value="    Save "
							onclick="return saveValid(this)" />
					</s:else> <input name="Submit3" theme="simple" type="button" class="reset"
						value="    Reset" onclick="resetStatus();" /></td>

				</tr>
			</table>
			</td>
		</tr>


	</table>

	<script language="JavaScript" type="text/javascript"
		src="../pages/common/include/javascript/sorttable.js"></script>

</s:form>

<script><!--
	
	function resetStatus(){
		document.getElementById('paraFrm_confAppStatus').value = "P";
		document.getElementById('paraFrm').action = "ConferenceApproval_input.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function saveValid(obj){
		var count = eval(document.getElementById("count").value);
		var value1 = document.getElementById('paraFrm_confAppStatus').value;
		if(value1==""){
			alert('Plesse select list to display data');
			return false;
		}
		if(count==0){
			alert("There is no record to save");
			return false;
		}
		var status ="";
		for (var length = 1 ;length <= count;length++ ){
			if(document.getElementById("check"+length).value !="P"){
				status="changed";
			}
		}
		if(status != "changed"){
			alert("Please change the status of atleast one application .");
			return false;
		}
		
		var len_s="";
        for( var len = 1; len<=count; len++ ) {
                var val=document.getElementById('comm'+len).value
       if(eval(val.length)>500) {
       		alert(document.getElementById('comments').innerHTML.toLowerCase()+' accepts only 500 ' + 
		            ' characters. Please remove ' + (eval(val.length) - 500) + ' characters in record no: '+len +'.');
		   len_s="not";
		    //return false;
       }
		}
		if(len_s == "not")
			return false;
		obj.disabled = true;
		document.getElementById('paraFrm').action = "ConferenceApproval_saveApproval.action";
		document.getElementById('paraFrm').submit();
	}	
	
    function viewDetails(conferenceCode){
	try{
		document.getElementById('paraFrm_conferenceCode').value = conferenceCode;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "ConferenceBooking_retriveForApproval.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		}
		catch(e){
		alert(e);
		}
	}	
	
	function callLength(obj){ 
		 //if(type=='descCnt'){
					var cmt =document.getElementById(obj.id).value;
					var remain = 500 - eval(cmt.length);
					//document.getElementById('paraFrm_descCnt').value = remain;
						if(eval(remain)< 0){
					document.getElementById(obj.id).style.background = '#FFFF99';
					}else document.getElementById(obj.id).style.background = '#FFFFFF';
				//}
 }
 
   function checkAvailabilty(obj){
 	var rowId =obj.id.replace('check','');
 	var status = document.getElementById('check'+rowId).value;
 	var isAvailable = document.getElementById('IsConfRoomAvailable'+rowId).value;
	
 	if(status=='A' && isAvailable=='false'){
 		alert('Application cant be approved as Conference room is busy for specified time and date. ');
 		document.getElementById('check'+rowId).value="P";
 		return false;
 	}
 }
 
 function cancelAppFun(code){
	var conferenceID = code;
	var flag = "true";
	conf = confirm('Do you Really want to Cancel the Application');
	if(conf){
		document.getElementById('paraFrm').target="_self";	
		document.getElementById('paraFrm').action='ConferenceBooking_cancelApplication.action?confID='+conferenceID+'&approverFlag=true';
		document.getElementById('paraFrm').submit();
	}
 }  		
</script>
