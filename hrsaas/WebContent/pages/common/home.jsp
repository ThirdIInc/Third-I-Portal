
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

	
	<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>
	
<s:form action="HomePage" id="paraFrm"  name="HomeForm" theme="simple">

	<div id="floatingBoxParentContainer">
	</div>

</s:form>
<script type="text/javascript">
	var numberOfDash = 6;
	var numberOfColumns = 2;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;
function initDragableBoxes()
{
		dashletURLs= new Array('<%=request.getContextPath()%>/common/HomePage_getCorpComm.action',
		'<%=request.getContextPath()%>/common/HomePage_getPolls.action',
		'<%=request.getContextPath()%>/common/HomePage_getBulletin.action',
		'<%=request.getContextPath()%>/common/HomePage_getGenInfo.action',
		'<%=request.getContextPath()%>/common/HomePage_getCorpInfo.action',
		'<%=request.getContextPath()%>/common/HomePage_getKnowInfo.action'
		);
		
		dashletWidths= new Array("66","33");
		dashletHeights= new Array("200","200","200","200","200","200");
		columnNos= new Array("1","2","1","2","1","2");
		dashHeaders= new Array("Corporate Communication",
		"Opinion Poll","HrWork Communication","General Information"
		,"Corporate Information","Knowledge Information");
		dashletFloats= new Array("left","right","left","right","left","right");
		/*
		
		*/
		var numberOfColumns=2;
		
		
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.


		
	}
	
	
</script>

<script>

	

	


function setPollValue(id){
//alert('in homejsp----'+id.value);
	document.getElementById('optionValue').value =id.value;
	
}

var pollValue ;
  
  function callPollQuick(id){
  //alert(2);
  	pollValue= id.value;
  	 //alert(pollValue);
  }
  
   function callForDetails(){ 
   
   text=document.getElementById('quickSearch').value;
   
 		   
 		   if(text==""){
		   alert('Please Enter Text and Select any one of the option');
		   return false;    
		   }
		   
			   if(pollValue=='e'){
			   
					   if(!(text==""))
										{ 
										
										var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?0123456789";
							  			for (var i = 0; i < text.length; i++)
							  			 {		  	
								  		if (iChars.indexOf(text.charAt(i)) != -1)
								  	 	{
									  	alert ("Please Enter Valid Employee Name ");
									  	return false;				  	
					  					   }
					  					}
									}
						if(!(text==""))
									{ 
									 
									var count =0;
									var iChars =" ";
						  			for (var i = 0; i < text.length; i++)
						  			 {		
						  			 
							  		if (iChars.indexOf(text.charAt(i))!= -1)
							  	 	{
							  	 	count=count+1; 
								  	
				  					   }
				  					}
				  					if(count==text.length){
				  					alert ("Blank Spaces Not Allowed");
				  					return false;	
				  					}
								}
					  		
		   
		  	    document.getElementById('paraId').value=document.getElementById('quickSearch').value;
				document.getElementById('paraFrm').action='../common/MISReport_search.action';	
		        document.getElementById("paraFrm").submit();
		   }
		   
		   if(pollValue=='b' || pollValue=='j'){

		       if(text=='') return true;
			   var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
			
					if(!(text.match(dateFormat)) || text.length<10){
						alert(" Date should be in DD-MM-YYYY format");
						document.getElementById(quickSearch).focus();
						return false;
					}
					var dateArray = text.split("-");
					var day   = dateArray[0];
					var month = dateArray[1];
					var year  = dateArray[2];
					
					if(day<1 || day>31){
						alert("Day "+day+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if(month<1 || month>12){
						alert("Month "+month+" is not a valid month");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if(day>29 && month==2){
						window.alert("Day "+day+" of the month "+month+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					
					if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
						window.alert("29th of February is not a valid date in "+year);
						document.getElementById(quickSearch).focus();
						return false;
					}
					
					if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
						window.alert("Day "+day+" of the month "+month+" is not a valid day");
						document.getElementById(quickSearch).focus();
						return false;
					}
			document.getElementById('paraId').value=document.getElementById('quickSearch').value;
			document.getElementById('paraFrm').action='../common/MISReport_search.action';	
		    document.getElementById("paraFrm").submit();				     
		   }		  
	}
	
	
	
	function callForAdvSearch() {		
		document.getElementById('paraFrm').action='../common/MISReport_input.action';
		document.getElementById("paraFrm").submit();
	}
	
	function callSave(){
alert('in call save----------');
alert('in call save'+document.getElementById('pollCode').value);
document.getElementById('paraFrm').action='HomePage_pollSave.action';	
document.getElementById("paraFrm").submit();
//javascript:retrieveURL('HomePage_pollSave.action?','HomePage','optionValue','pollCode');
}

function callPoll(name) {
	
	window.open('','window','top=260,left=150,width=600,height=400,scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="window";
	document.getElementById("paraFrm").action=name;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}

function submitPoll(){
	var opt = document.getElementById('optionValue').value;
	if(opt==""){
		alert('Please select an option');
		return false;
	}else{
		retrievePoll('HomePage_pollSave.action?','HomeForm');
	}
}


</script>

