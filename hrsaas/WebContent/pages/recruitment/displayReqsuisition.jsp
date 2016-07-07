  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OpenVacReport" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right"  class="formbg">
	  <tr>
		  <td colspan="3" width="100%" align="left"> <input type="button" value=" OK " class="token" onclick="callOK();"> 
		    <input type="button" value=" Cancel " class="token" onclick="callCancel();">
		  </td>
		</tr>
		
	   <tr>
		  <td colspan="3" width="100%"> <s:hidden name="selectedReq" />  <s:hidden name="selectedReqName"/>  <s:hidden name="editVal" value="false"/>  <s:hidden name="dataLength"/> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0"><s:hidden name="noDataReq"/>  <s:hidden name="editReqFlag"/>
			 <tr>
			   <td width="31%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			   <td width="26%" class="formth" ><b> <label  class = "set"  id="position"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
			   <td width="13%" class="formth" ><b> <label  class = "set"  id="reqs.date"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b> </td>
			   <td width="13%" class="formth" ><b> <label  class = "set"  id="status"  name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> </b> </td>
			    <td width="5%"  class="formth" align="left"> <input type="checkbox" name="headerChk" id="headerChk" onclick="selectAllChk();"> </td>
			</tr> 
			</table>
			<div style="height: 300;overflow: auto;" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
				 <%int i=1; %>
				 <s:iterator value="dispList"> 
					  <tr> 
					   <td width="33%" class="sortableTD" ><s:hidden name="itReqName" id="<%="itReqName"+i%>" /> <s:hidden name="itPosition"/>  <s:hidden name="itReqCode" id="<%="itReqCode"+i%>"/>
					   <s:hidden name="itReqDate"/> <s:hidden name="itStatus"/> <s:hidden name="selectedReqFlag"/>  <s:property value="itReqName"/> </td>
					   <td width="28%" class="sortableTD" ><s:property value="itPosition"/> </td>
					   <td width="15%" class="sortableTD" ><s:property value="itReqDate"/> </td>
					   <td width="15%" class="sortableTD" > <s:property value="itStatus"/> </td>
					   <td width="5%" class="sortableTD" align="center"> <input type="checkbox" name="listChk" id="listChk<%=i%>" <s:property value="selectedReqFlag"/>  onclick="CallCheckBox('<%=i%>','<s:property value='itReqCode'/>','<s:property value='itReqName'/>');"> </td>
					</tr>  
					<%i++;%>
				</s:iterator>  
				 <s:if test="noDataReq">
									<tr>
										<td width="100%" align="center"><font
											color="red">There is no data to display.</font></td>
									</tr>
				 </s:if>  	 
	        </table> 
	        </div>
	     </td>
	  </tr>  
	    <tr>
		  <td colspan="1" align="left"> <input type="button" value=" OK " class="token" onclick="callOK();"> 
		    <input type="button" value=" Cancel " class="token" onclick="callCancel();">
		  </td>
		  <td colspan="2" align="right"><s:if test="noDataReq"> </s:if><s:else><b>Total Numbers of record : <s:property value="dataLength"/></b> </s:else>&nbsp; </td> 
		</tr>
 </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 

function callOK()
{
    var selVal=document.getElementById('paraFrm_selectedReq').value;
    var selValReqName= document.getElementById('paraFrm_selectedReqName').value ;
    
     var splitVal =selVal.split(","); 
     var splitValReqName =selValReqName.split(","); 
		    selVal=""; 
		    selValReqName="";
		    
		    for(var i=0;i<splitVal.length-1;i++)
		     {  
			     if(i<splitVal.length-2)
			     {
			       selVal+=splitVal[i]+",";
			       selValReqName+=splitValReqName[i]+",";
			     }else
			     {
			       selVal+=splitVal[i];
			       selValReqName+=splitValReqName[i];
			     }
		     } 
  opener.document.getElementById('paraFrm_selectedReq').value =selVal; 
  //opener.document.getElementById('textAreaId').style.display='';
  opener.document.getElementById('textAreaId1').style.display='';
  opener.document.getElementById('paraFrm_selectedReqName').value =selValReqName; 
  window.close();
}

function callCancel()
{
  window.close();
}

function selectAllChk()
{
   var dataLength = document.getElementById('paraFrm_dataLength').value ;  
   var selVal="";  
   var selValReqName= ""; 
      if(document.getElementById('headerChk').checked)
      {     var j =1;   
		    for(var i=0;i<dataLength;i++)
		     {    
		        document.getElementById('listChk'+j).checked=true; 
			    selVal+=document.getElementById('itReqCode'+j).value+","; 
			    selValReqName+=document.getElementById('itReqName'+j).value+","; 
			    j++;
		     }
	  } 
	  else { 
	        var k=1;  
		    for(var i=0;i<dataLength;i++)
		     {   
		        document.getElementById('listChk'+k).checked=false;
			    selVal="";
			    selValReqName= "";
			    k++;
		     } 
	    } 
   document.getElementById('paraFrm_selectedReq').value =selVal; 
   document.getElementById('paraFrm_selectedReqName').value = selValReqName;
}
 function CallCheckBox(id,req,reqName)
 { 
  var selVal= document.getElementById('paraFrm_selectedReq').value;
  var selValReqName= document.getElementById('paraFrm_selectedReqName').value;
  var editReqFlag= document.getElementById('paraFrm_editReqFlag').value;
  var editVal= document.getElementById('paraFrm_editVal').value;
  
  if(document.getElementById('listChk'+id).checked)
  {   
    if(editReqFlag=="true"){ 
        if(editVal=="false"){ 
         selVal+=",";
         selValReqName+=",";
        document.getElementById('paraFrm_editVal').value="true";
        }
       selVal+=req+","; //check
	   selValReqName+=reqName+",";
    }else{
	   selVal+=req+",";
	   selValReqName+=reqName+",";
   }
  } else
  {  
    if(selVal!="")
        { 
           var splitVal =selVal.split(","); 
            var splitValReqName =selValReqName.split(","); 
		    selVal=""; 
		    selValReqName="";
		    for(var i=0;i<splitVal.length-1;i++)
		     { 
		      if(splitVal[i]==req)
		       {   
		       }else
		       {
		        selVal+=splitVal[i]+",";
		        }
		        //=== for ReqName===
			       if(splitValReqName[i]==reqName)
			       {   
			       }else
			       {
			        selValReqName+=splitValReqName[i]+",";
			        } 
		     }
	   } //end of if
   }  
   document.getElementById('paraFrm_selectedReq').value=selVal;
   document.getElementById('paraFrm_selectedReqName').value=selValReqName;
 }
</script>