<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="MenuTabSetting" id="paraFrm" 
	theme="simple" target="main">

<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Menu/Tab Settings
			  </strong></td>
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
					<td colspan="4"><s:submit cssClass="save"
						action="MenuTabSetting_save" theme="simple" value=" Save"
						onclick="return callsave();" /></td>
						
<s:hidden name="deleteOp"  value=""></s:hidden>
						
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>


				

			</table>

			<label></label></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">






						<tr>

							<td width="15%" colspan="1" height="22"><label name="menu.name" id="menu.name" ondblclick="callShowDiv(this);"><%=label.get("menu.name") %></label><font
								color="red">*</font>:</td>
							<td width="85%" colspan="2" height="22"><s:textfield
								theme="simple" name="menuName" maxlength="50" readonly="true" />
							<img src="../pages/common/css/default/images/search2.gif"
								width="16" height="15"
								onclick="javascript:callsF9(500,325,'MenuTabSetting_f9menuAction.action');" />
							<s:hidden name="menuCode" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
		<tr>
			<td colspan="3"></td>
		</tr>



		<tr>
			<td colspan="3">
			<%
			try {
				int i=0;
				int total=(Integer)request.getAttribute("tot");
			%>
			<%!int cnt=0; %>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>

					<td class="formtext" colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" >
						<tr>

							
							<td class="formth" align="center"><label name="menu.name" id="menu.name1" ondblclick="callShowDiv(this);"><%=label.get("menu.name") %></label></td>
                             	<td class="formth" align="center">&nbsp;</td>
                             	<td  class="formth" align="center"><input type="button" value="Restore All" onclick="restoreAll();" class="token"> </td>



							<s:iterator value="menusetlist">

								<tr>
									<td width="40%" align="left"><s:hidden 
										value="menuName" />
									
						<input type="hidden" id="menuName<%=i %>"
												
												value='<s:property value="menuName"/>' />	
												
												
										<input type="hidden" id="menuCode<%=i %>" name="menuCode"
												
												value='<s:property value="menuCode"/>' />			
												
												<input type="hidden" id="orgmenuname<%=i %>" name="orgmenuname"
										value='<s:property value="orgmenuname"/>' />		
												
												<input type="text" id="edit<%=i%>" name="editmenu" theme="simple" style="border: none;" 
										size="30" maxlength="60"  disabled="true" value='<s:property value="menuName"/>'/>  
										<input type="hidden" id="menu<%=i%>" name="editmenucode" theme="simple" disabled="true"
										size="30" maxlength="30" value='<s:property value="editmenucode"/>'/>			
										 </td>
									<td>
									
									
									
									
									<input type="button"  class="rowEdit" onclick="return callSub('<%=i %>');">
									
										<input type="button" 
				                         class="reset" style="cursor: pointer;border: none;"   onclick="restoreMenu('<%=i %>')";/>&nbsp;
									
									
									</td>
									
									
									</tr>
<%i++; %>
							</s:iterator>
<%cnt=total; %>
						</tr>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<%
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			</td> 
		</tr>


	</table>
	
	
</s:form>
<script><!--
call();
function call()
{
var tot='<%=cnt%>';

for(var i=0;i<tot;i++)
{
document.getElementById('editDiv'+i).style.display='none';

}
}



function callSub(id)
	{
	
	//alert('x');
	//var tot='<%=cnt%>';
	//document.getElementById('paraFrm_deleteOp').value=
      
      document.getElementById('paraFrm_deleteOp').value="xx";
     // alert(1);
      try{
      //document.getElementById('editDiv'+id).style.display='';
       //document.getElementById('editDiv'+id).style.display='';
     // alert('value is'+document.getElementById('menuName'+id).value);
     //alert('hahaaa'+document.getElementById('menuName'+id).value);
      
      //use--document.getElementById('edit'+id).value=document.getElementById('menuName'+id).value;
      //alert('1');
      //alert(document.getElementById('edit'+id).disabled);
      document.getElementById('edit'+id).disabled="";
      document.getElementById('menu'+id).disabled="";
      document.getElementById('edit'+id).readOnly=false;
      document.getElementById('edit'+id).style.border="1px solid";
      //alert(document.getElementById('edit'+id).disabled);
       //alert('value  menu is'+document.getElementById('menuCode'+id).value);
      document.getElementById('menu'+id).value=document.getElementById('menuCode'+id).value;
      
  		//document.getElementById(id).style.display='';
		//document.getElementById('paraFrm_hiddensub').value='O';
		}catch(e){
		alert(e);
		}
	
	}
	 function callsave()
	 {
	 try{
	  var type=document.getElementById('paraFrm_deleteOp').value;
	 //alert('');
	  var tot='<%=cnt%>';
	 

	 var module=document.getElementById('paraFrm_menuName').value;
	
	
	 //alert('module'+module);
	// alert('type')
	 if(module=='')
	 {
	 alert('Please select '+document.getElementById('menu.name').innerHTML.toLowerCase());
	 return false;
	 }
	if(type=='')
	{
	 	alert('Record saved successfully');
		return false;
	 }
	   
	
	 
	else 
	{
		for(var i=0;i<tot;i++)
		{
		//alert('haha');
		
		//alert('ssss'+document.getElementById('edit'+i).value);
		if(trim(document.getElementById('edit'+i).value)==''){
		alert('no blank space is allowed ');
		return false;
		}
		}
		
	}
	return true;
	

	 
	}catch(e){
	alert(e);
	}   
	

	
	 
}

function restoreMenu(id)
{
//alert('id---'+id);
//alert('y');
var type=document.getElementById('paraFrm_deleteOp').value;
//alert('type'+type);
if(type=='')
	return false;
else
{
var changeId = document.getElementById('orgmenuname'+id).value;
//alert('changeId is___'+changeId);
		document.getElementById('edit'+id).value=changeId;
		document.getElementById('edit'+id).readOnly=true;
		
		
}
return true;
}


function restoreAll()
{
//alert('haha');
//alert('in'+document.getElementById('edit'+0).value);
try{
var type=document.getElementById('paraFrm_deleteOp').value;
//alert('type'+type);


var tot='<%=cnt%>';
//
	  for(var i=0;i<tot;i++){

		//if((document.getElementById('menu'+i).value!='')||(document.getElementById('menu'+i).value=''))
		//{
		//alert('in if');
		document.getElementById('edit'+i).value=document.getElementById('orgmenuname'+i).value;
		document.getElementById('menu'+i).value=document.getElementById('menuCode'+i).value;
		document.getElementById('edit'+i).style.border='none';
		document.getElementById('edit'+i).disabled=false;
		document.getElementById('menu'+i).disabled=false;
		document.getElementById('paraFrm_deleteOp').value="i"; 
		//}
		
		}
		


}catch(e)
{
alert(e);
}
}

--></script>











