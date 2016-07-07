  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SchTestAnalysis" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr height="25">
		  <td colspan="3" width="100%" align="left"> <input type="button" value=" OK " class="token" onclick="callOK();"> 
		    <input type="button" value=" Cancel " class="token" onclick="callCancel();">
		  </td>
		</tr>
		
	   <tr>
		  <td colspan="3" width="100%"> <s:hidden name="selectedReq" />  <s:hidden name="noDataReq"/>  <s:hidden name="editReqFlag"/>  <s:hidden name="selectedReqName"/>  <s:hidden name="editVal" value="false"/>  <s:hidden name="dataLength"/> 
		    <s:if test="%{dataLength>15}">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
			    <td width="9%" class="formth" ><b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
			   <td width="28%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			   <td width="25%" class="formth" ><b> <label  class = "set"  id="labelPostion"  name="labelPostion" ondblclick="callShowDiv(this);"><%=label.get("labelPostion")%></label></b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="reqDateLabel"  name="reqDateLabel" ondblclick="callShowDiv(this);"><%=label.get("reqDateLabel")%></label></b> </td>
			   <td width="18%" class="formth" ><b> <label  class = "set"  id="Status"  name="Status" ondblclick="callShowDiv(this);"><%=label.get("Status")%></label> </b> </td>
			   <td width="5%"  class="formth" > <input type="checkbox" name="headerChk" id="headerChk" onclick="selectAllChk();"> </td>
			    <td width="6%"  class="formth" > &nbsp;&nbsp; </td>
			</tr> 
			</table>
			</s:if>
			<s:else>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0">
			 <tr>
			    <td width="9%" class="formth" ><b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
			   <td width="28%" class="formth" ><b> <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
			   <td width="25%" class="formth" ><b> <label  class = "set"  id="labelPostion"  name="labelPostion" ondblclick="callShowDiv(this);"><%=label.get("labelPostion")%></label></b></td>
			   <td width="15%" class="formth" ><b> <label  class = "set"  id="reqDateLabel"  name="reqDateLabel" ondblclick="callShowDiv(this);"><%=label.get("reqDateLabel")%></label></b> </td>
			   <td width="18%" class="formth" ><b> <label  class = "set"  id="Status"  name="Status" ondblclick="callShowDiv(this);"><%=label.get("Status")%></label> </b> </td>
			   <td width="5%"  class="formth" > <input type="checkbox" name="headerChk" id="headerChk" onclick="selectAllChk();"> </td>
			</tr> 
			</table> 
			</s:else>
			<div style="height: 300;overflow: auto;" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
				 <%int i=1; %>
				 <s:iterator value="dispList"> 
					  <tr> 
					   <td width="9%" class="sortableTD" align="center" > <%=i%> </td>
					   <td width="28%" class="sortableTD" > <s:hidden name="itReqName" id="<%="itReqName"+i%>" /> <s:hidden name="itPosition"/>  <s:hidden name="itReqCode" id="<%="itReqCode"+i%>"/>
					   <s:hidden name="itReqDate"/> <s:hidden name="itStatus"/> <s:hidden name="selectedReqFlag"/>  <s:hidden name="itRecriuterName"/>   <s:property value="itReqName"/> </td>
					   <td width="25%" class="sortableTD" ><s:property value="itPosition"/> </td>
					   <td width="15%" class="sortableTD" ><s:property value="itReqDate"/> </td>
					   <td width="18%" class="sortableTD" > <s:property value="itStatus"/> </td> 
					   <td width="5%" class="sortableTD" > <input type="checkbox" name="listChk" id="listChk<%=i%>" <s:property value="selectedReqFlag"/>  onclick="CallCheckBox('<%=i%>','<s:property value='itReqCode'/>','<s:property value='itReqName'/>');"> </td>
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
	    <tr height="35">
		  <td colspan="1" align="left"> <input type="button" value=" OK " class="token" onclick="callOK();"> 
		    <input type="button" value=" Cancel " class="token" onclick="callCancel();">
		  </td>
		  <td colspan="2" align="right"><s:if test="noDataReq"> </s:if><s:else><b>Total records : <s:property value="dataLength"/></b> </s:else>&nbsp; </td> 
		</tr>
 </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 

window.onload= callOnLoad;
function callOnLoad()
{
var editReqFlag = document.getElementById('paraFrm_editReqFlag').value;   
       if(editReqFlag=="true"){
       document.getElementById('paraFrm_selectedReqName').value=document.getElementById('paraFrm_selectedReqName').value+",";
       document.getElementById('paraFrm_selectedReq').value=document.getElementById('paraFrm_selectedReq').value+",";  
		}
}

function callOK()
{
    var selVal=document.getElementById('paraFrm_selectedReq').value;
    var selValReqName= document.getElementById('paraFrm_selectedReqName').value ;
    var checkCommaCode = selVal.substring((selVal.length-1),selVal.length); 
    var checkCommaName = selValReqName.substring((selValReqName.length-1),selValReqName.length); 
      if(checkCommaCode==","){
        selVal =selVal.substring(0,(selVal.length-1));
      }   
     if(checkCommaName==","){
       selValReqName =selValReqName.substring(0,(selValReqName.length-1));
     } 
      var checkFirstCommaName = selValReqName.substring(0,1);  
       var checkFirstCommaCode = selVal.substring(0,1); 
      if(checkFirstCommaName==","){
       selValReqName =selValReqName.substring(1,selValReqName.length);
      }
       if(checkFirstCommaCode==","){
         selVal =selVal.substring(1,selVal.length);
      }
      
  opener.document.getElementById('paraFrm_selectedReq').value =selVal; 
    opener.document.getElementById('paraFrm_hidSelectedReqName').value =selValReqName; 
  //opener.document.getElementById('textAreaId').style.display='';
  opener.document.getElementById('textAreaId1').style.display='';
  
     var arraySel = selValReqName.split(",");
	  selValReqName="";
	  for(i=0;i<arraySel.length;i++)
	  {
	    if(i<arraySel.length-1) {
	     selValReqName=selValReqName+arraySel[i]+"\n"; 
	    }else{
	     selValReqName=selValReqName+arraySel[i];
	    }
	  } 
	  
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
     selVal+=req+",";
     if(selValReqName.indexOf(reqName)==-1){
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