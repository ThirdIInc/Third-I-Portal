  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OfferLetterAnalysis" validate="true" id="paraFrm" theme="simple"> 
	<s:textfield name="editVal" value="false"/>
	<s:hidden name="editReqFlag"/>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	   <tr>
		  <td colspan="3" width="100%"> <s:textfield name="selectedReq" /> <s:textfield name="selectedReqName" /> <s:textfield name="dataLength"/> 
			<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
			 <tr>
			   <td width="35%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			   <td width="30%" class="formth" ><b> <label  class = "set"  id="position"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="reqs.date"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b> </td>
			   <td width="5%"  class="formth" > <input type="checkbox" name="headerChk" id="headerChk" onclick="selectAllChk();"> </td>
			</tr> 
			 <%int i=1; %>
			 <s:iterator value="dispList"> 
				  <tr> 
				   <td width="35%" class="sortableTD" > <s:hidden name="reqsName" id="<%="reqsName"+i%>" /> <s:hidden name="rankName"/>  <s:hidden name="requisitionCode" id="<%="requisitionCode"+i%>"/>
				   <s:hidden name="reqsDateCombo"/> <s:property value="reqsName"/> </td>
				   <td width="30%" class="sortableTD" ><s:property value="rankName"/> </td>
				   <td width="15%" class="sortableTD" ><s:property value="reqsDateCombo"/> </td>
				   <td width="15%" class="sortableTD" > <input type="checkbox" name="listChk" id="listChk<%=i%>" <s:property value="selectedReqFlag"/> onclick="CallCheckBox('<%=i%>','<s:property value='requisitionCode'/>','<s:property value='reqsName'/>');"> </td>
				</tr>  
				<%i++;%>
			</s:iterator>
	        </table> 
	     </td>
	  </tr> 
	    <tr>
		  <td colspan="3" width="100%"> <input type="button" value=" OK " class="token" onclick="callOK();">
		  </td>
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
  //opener.document.getElementById('textAreaId').style.display='';
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
			    selVal+=document.getElementById('requisitionCode'+j).value+","; 
			    selValReqName+=document.getElementById('reqsName'+j).value+","; 
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