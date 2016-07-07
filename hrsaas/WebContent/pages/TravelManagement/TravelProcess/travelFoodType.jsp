<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
	<%
	
	String foodTypeList = request.getParameter("foodTypeList");
    String foodList[] = foodTypeList.split(",");
    
    String selectedFood[] = (String[] )request.getAttribute("selectedFood");
    
	
	%>
<s:form name="" action="" validate="" id="paraFrm" theme="simple">

<table width="100%" border="0"  cellpadding="1" cellspacing="1" class="formbg" style="overflow: scroll">
		
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Food Type</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<script>
		 
		 function checkAllFoodType(){
		   
		  for(i=1;i<document.getElementById('foodCount').value;i++){
		   if(document.getElementById('chkFood').checked){
		     document.getElementById('chkFood'+i).checked="true";
		     document.getElementById('foodFlag'+i).value="Y";
		   }else{
		     document.getElementById('chkFood'+i).checked="";
		     document.getElementById('foodFlag'+i).value="N";
		   }
		  }
		 } 
		 
		 function checkFoodType(chkId, FlagId){
		 	  
			  if(document.getElementById(chkId).checked){
			   	document.getElementById(FlagId).value="Y";
			  }else{
			   	document.getElementById(FlagId).value="N";
			  }
			  
		 }
		 
		 function setValue(){
		 
		 	  var idList="" ;
		 	  var valList="" ;
		 	  
		 	    for(i=1;i<document.getElementById('foodCount').value;i++){
		 	    
		   			if(document.getElementById('foodFlag'+i).value=="Y"){
			   			 
			   			 if(idList==""){
			   			 	idList=idList+document.getElementById('foodTypeId'+i).value; 
			   			 	valList=valList+document.getElementById('foodType'+i).value; 
			   			 }else{
			   			  	idList=idList+","+document.getElementById('foodTypeId'+i).value;
			   			  	valList=valList+","+document.getElementById('foodType'+i).value; 
			   			 }
		   			 
		   			}
		   		}

		 	  
			  window.opener.document.getElementById('paraFrm_foodTypeId').value=idList;
			  window.opener.document.getElementById('paraFrm_foodType').value=valList;
			  self.close();
		 }
		</script>
		<tr>
		 <td align="left">
		 	<input type="button"  value="   Add   " class="token" onclick="setValue();" />
		 	<input type="button"  value="   Close   " class="token" onclick="window.close();" />
		 </td>
		</tr>
		<tr>
		 <td>
		 
		 <div  style="width:370;height:280;overflow: scroll;">
		  <table width="100%">
		   <tr>
		    <td class="formth" width="5%"> 
		     	<b><label class="set" name="sno" id="sno"	ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>		     
		    </td>
		    <td class="formth" width="87%">
		    	<b><label class="set" name="fType" id="fType"	ondblclick="callShowDiv(this);"><%=label.get("fType")%></label></b>		    	
		    </td>
		    <td class="formth" width="8%">
		    	<input type="checkbox" id="chkFood" onclick="checkAllFoodType()">
		    </td>
		   </tr>
		   <%int i=1,index=0; %>
		   <s:iterator value="foodTypeList">
		     <tr>
		     <td class="sortableTD"><%=i %></td>
		     <td class="sortableTD"><s:property value="foodType"/></td>
		     <td class="sortableTD" align="center">
		        <input type="hidden"  id="foodType<%=i %>" value="<s:property value="foodType" />">
		        <input type="hidden" name="foodTypeId" id="foodTypeId<%=i %>" value="<s:property value="foodTypeId" />">
		     	<input type="checkbox" id="chkFood<%=i%>" onclick="checkFoodType('chkFood<%=i%>','foodFlag<%=i%>')" <%=selectedFood[index].equals("Y")?"checked":"" %>>
		     	<input type="hidden" name="" id="foodFlag<%=i%>" value="<%=selectedFood[index].equals("Y")?"Y":"N" %>"> 
		     </td>
		     <td></td>
		    </tr>
		    <%i++;index++; %>
		   </s:iterator>
		   <input type="hidden" id="foodCount" value="<%=i%>">
		  </table>
		  </div>
		   
		 </td>
		</tr>
				
		<tr>
		 <td align="left">
		 	<input type="button"  value="   Add   " class="token" onclick="setValue();" />
		 	<input type="button"  value="   Close   " class="token" onclick="window.close();" />
		 </td>
		</tr>
		
</table>


</s:form>