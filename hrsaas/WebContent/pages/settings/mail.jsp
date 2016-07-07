<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="MailSettings" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%"><strong class="text_head">Mail
					Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="add" value=" Add Server"
						action="MailSettings_addServerName" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td class="formth" width="15%" colspan="1"><label
						name="SrNo" id="SrNo" ondblclick="callShowDiv(this);"><%=label.get("SrNo")%></label></td>
					
					<td width="20%" class="formth" colspan="1"><label
						name="serverName" id="serverName"
						ondblclick="callShowDiv(this);"><%=label.get("serverName")%></label></td>
						
				
					<td width="20%" class="formth" colspan="1"><label
						name="inboundserver" id="inboundserver"
						ondblclick="callShowDiv(this);"><%=label.get("inboundserver")%></label></td>
						
					
						<td width="20%" class="formth" colspan="1"><label
						name="outboundserver" id="outboundserver"
						ondblclick="callShowDiv(this);"><%=label.get("outboundserver")%></label></td>
				 
				<td width="25%" class="formth" colspan="1">
							<input type="submit" value="Edit" class="edit" onclick="return callEdit('<s:property	value="ittHiddenCode" />');">
							<input type="submit" value="Delete" class="delete" onclick=" return callDelete('<s:property	value="ittHiddenCode" />');">
						
							</td>
				</tr>
				<%
						try {
						%>
				<%int i=1; %>
				<%!int tot=0; %>
				<s:iterator value="list">
					<tr>
						<td width="15%" class="sortableTD" colspan="1"><%=i %> <input
							type="hidden" name="ittServerCode" id="ittServerCode<%=i%>"
							value='<s:property value="ittServerCode" />' /></td>
						<td width="20%" class="sortableTD" colspan="1" align="center"><s:property
							value="ittServerName" /></td>
							<td width="20%" class="sortableTD" colspan="1" align="center"><s:property
							value="inboundServer" /></td>
							<td width="20%" class="sortableTD" colspan="1" align="center"><s:property
							value="outboundServer" /></td>
						
						<td width="25%" class="sortableTD" colspan="1" align="center">
						<input type="checkbox" name="editDelete" id="chk<%=i%>"></td>
					</tr>
					<%i++; %>
				</s:iterator>

				<%tot=i;
							} catch (Exception e) {
							}
						%>

			</table>
			</td>
		</tr>



	</table>
	
	<s:hidden name="hiddenCode" />
</s:form>



<script>


	function callEdit(){		
				
				try{
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittServerCode'+i).value;
					cnt=cnt+1;				
				}	
			}				
				
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	
					if(eval(cnt)>eval(1)){
					alert('Please select only one checkBox');
					return false;
					}					
						
			    document.getElementById('paraFrm_hiddenCode').value=id;
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="MailSettings_edit.action";
				document.getElementById('paraFrm').submit();	
				
				}catch(e){alert("e==============="+e);}	
	          }
	

		function callDelete(){

	var mess=confirm('Are you sure want to delete');
	if(mess){
			var hidCode="";
				var cnt=0;				
				var k='<%=tot%>';									
				for(var i=1;i<k;i++){							
				if(document.getElementById('chk'+i).checked==true){
				var id=document.getElementById('ittServerCode'+i).value;
					cnt=cnt+1;	
					hidCode +=id+",";			
				}	
			}				
			hidCode=hidCode.substring(0,eval(hidCode.length)-1);		
				if(cnt=='0'){
				alert('Please select atleast one checkBox');
				return false;
				}	

    document.getElementById('paraFrm_hiddenCode').value=hidCode;
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="MailSettings_delete.action";
	document.getElementById('paraFrm').submit();
	}
	else{
	return false;
	}
	}
	






</script>
