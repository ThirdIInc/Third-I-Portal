<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="Ajax.js"></script>


 <link href="hrms.css" rel="stylesheet" type="text/css">
 
<s:form action="HomePage" name="paraFrm" id="paraFrm" theme="simple">
<table width="100%">

<tr >
  			<td  colspan="7" class="pageHeader" align="center" >Compose Mail</td>
  	  </tr>
  	    <tr >
  			<td >&nbsp;</td>
  	  </tr>
<tr>
<td><s:submit action="HomePage_sendMail" cssClass="pagebutton"  value="Send" onclick="callSend();"/>&nbsp;
<input type="button" class="pagebutton"  value="Reset" onclick="callReset();"/>&nbsp;
<input type="button" class="pagebutton"  value="Cancel" onclick="callCancel()"/></td> </tr>
</table>
<table width="100%">
 
 <tr>
	 		<td  class="smallPadding"   >From :</td>
	  		<td  ><s:textfield size="50"  name="msgFrom" cssClass="box" readonly="true"/></td>  	
	  		
	  		</tr>
	 <tr>
	 		<td   class="smallPadding"  >To :</td>
	  		<td  ><s:textfield size="50"  name="msgTo" cssClass="box" /></td>  	
	  		
	  		</tr>
	  		<tr>
	 		<td  class="smallPadding" >CC :</td>
			<td > <s:textfield  name="msgcc" size="50" cssClass="box"/></td>
			
	  	</tr>
	  	
	  	<tr>
	 		<td class="smallPadding"  >BCC :</td>
			<td > <s:textfield name="msgBcc" size="50" cssClass="box"/></td>
			
	  				
		</tr>
		<tr>
	 		<td  class="smallPadding" >Subject :</td>
			<td > <s:textfield name="msgSubject" size="50" cssClass="box"/></td>
			
	  		<s:hidden name="msgText"/>		
		</tr>
		<tr>
	 		<td  class="smallPadding" >Add Attachment :</td>
			<td > <s:textfield name="msgAttach" size="50" cssClass="box"/><input type="button"
						class="pagebutton" name="Browse" value="Browse"
						onclick="uploadFile('msgAttach');" /></td>
			</tr>
		<tr><td>&nbsp;</td>
		</tr>
	
	
	</table>
	
	<table width="100" >
	<tr>
		<td colspan="5">
		
		
		<select id="FONT" unselectable="on"
			onchange='document.execCommand("FontName",0,this.options[this.selectedIndex].text);
						document.getElementById("EDITBOX").focus()'>
			<option>Arial</option>
			<option>Arial Narrow</option>
			<option>Comic Sans MS</option>
			<option>Courier</option>
			<option>Georgia</option>
			<option>Impact</option>
			<option>Tahoma</option>
			<option selected>Times New Roman</option>
			<option>Verdana</option>
		</select> <select id="SIZE" unselectable="on"
			onchange='document.execCommand("FontSize",0,this.options[this.selectedIndex].text);
			  		document.getElementById("EDITBOX").focus()'>
			<option>1</option>
			<option>2</option>
			<option selected>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
		</select>  &nbsp;

		<button title="Ordered List" unselectable="on"
			onclick='document.execCommand("InsertOrderedList");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Ordered.gif" /></button>
		<button title="Unordered List" unselectable="on"
			onclick='document.execCommand("InsertUnorderedList");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Unordered.gif" /></button>
		<button title="Outdent" unselectable="on"
			onclick='document.execCommand("Outdent");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Outdent.gif" /></button>
		<button title="Indent" unselectable="on"
			onclick='document.execCommand("Indent");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Indent.gif" /></button>

		<button title="Horizontal Rule" unselectable="on"
			onclick='document.execCommand("InsertHorizontalRule");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/HR.gif" /></button>
		<button title="Marquee" unselectable="on"
			onclick='document.execCommand("InsertMarquee");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Marquee.gif" /></button>

	

		
		<!--- End of FIRST ROW ------------------------------------------------------------------->

		

		<button title="Cut" unselectable="on"
			onclick='document.execCommand("Cut");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Cut.gif" /></button>

		<button title="Copy" unselectable="on"
			onclick='document.execCommand("Copy");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Copy.gif" /></button>

		<button title="Paste" unselectable="on"
			onclick='document.execCommand("Paste");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Paste.gif" /></button>
		</td>
</tr>
<tr>
<td>
		<button title="Remove Formatting" unselectable="on"
			onclick='document.execCommand("RemoveFormat");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/UndoFormat.gif" /></button>

		<button title="Undo" unselectable="on"
			onclick='document.execCommand("Undo");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Undo.gif" /></button>

		<button title="Redo" unselectable="on"
			onclick='document.execCommand("Redo");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Redo.gif" /></button>



		<button title="Bold" unselectable="on"
			onclick='document.execCommand("Bold");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Bold.gif" /></button>
		<button title="Italic" unselectable="on"
			onclick='document.execCommand("Italic");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Italic.gif" /></button>
		<button title="Underline" unselectable="on"
			onclick='document.execCommand("Underline");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Underline.gif" /></button>
		<button title="Strike Through" unselectable="on"
			onclick='document.execCommand("StrikeThrough");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Strike.gif" /></button>



		<button title="Superscript" unselectable="on"
			onclick='document.execCommand("SuperScript");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Super.gif" /></button>
		<button title="Subscript" unselectable="on"
			onclick='document.execCommand("SubScript");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Sub.gif" /></button>



		<button title="Foreground Color" unselectable="on"
			onclick="Color='ForeColor'; SetColor()"><img
			src="../pages/Travel/images/FGColor.gif" /></button>

		<button title="Background Color" unselectable="on"
			onclick="Color='BackColor'; SetColor()"><img
			src="../pages/Travel/images/BGColor.gif" /></button>



		<button title="Left Justify" unselectable="on"
			onclick='document.execCommand("JustifyLeft");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Left.gif" /></button>
		<button title="Center Justify" unselectable="on"
			onclick='document.execCommand("JustifyCenter");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Center.gif" /></button>
		<button title="Right Justify" unselectable="on"
			onclick='document.execCommand("JustifyRight");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Right.gif" /></button>
		<button title="Justify" unselectable="on"
			onclick='document.execCommand("JustifyFull");
					 document.getElementById("EDITBOX").focus()'>
		<img src="../pages/Travel/images/Justify.gif" /></button>
		</td></tr>
		
		<tr>
		<td width="599" valign="top" style="border:1px;border-bottom: 1px">
		
		<div id="EDITBOX" unselectable="off" contenteditable="true" onmouseover="callBox();" 
			style="height: 400px;width:499px; border:solid; border-color:black; margin-top: 3px; overflow: auto; background-color: #FFFFFF; font-family: times new roman; font-size: 12pt">
			Write Your Text Here...
		</div>
		</td>
	
	</tr>
</table>
<OBJECT id="FGCOLORDIALOG" CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0px" height="0px">
</OBJECT> 
<OBJECT id="BGCOLORDIALOG" CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0px" height="0px">
</OBJECT>	
	<table width="100%">

 	    <tr >
  			<td >&nbsp;</td>
  	  </tr>
<tr>
<td><s:submit action="HomePage_sendMail" cssClass="pagebutton"  value="Send" onclick="callSend();"/>&nbsp;
<input type="button" class="pagebutton"  value="Reset" onclick="callReset();"/>&nbsp;
<input type="button" class="pagebutton"  value="Cancel" onclick="callCancel();"/></td> </tr>
</table>
</s:form>
<script>
function callReset()
{
document.getElementById('msgTo').value="";
document.getElementById('msgcc').value="";
document.getElementById('msgBcc').value="";
document.getElementById('msgSubject').value="";
document.getElementById('msgText').value="";
}
function callCancel()
{
	window.close();
}

function uploadFile(fieldName) {
	var path="upload";
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		}
		
function callSend()
{
	var msgText = document.getElementById("EDITBOX").innerHTML;
	
	document.getElementById("msgText").value=msgText;
}

function callBox()
{
	var text =document.getElementById("EDITBOX").innerHTML;
	if(text=="Write Your Text Here... ") {
	document.getElementById("EDITBOX").innerHTML="";
	}
}

</script>

<script type="text/javascript">

var sPersistValue;


// Ensure that all document elements except the EDITOR are unselectable.
function InitEdit()
{
	for (var i=0; i < document.all.length; i++) {
		document.all[i].unselectable = "on";
	};
	document.getElementById("EDITOR").unselectable = "off";
	document.getElementById("HTMLBOX").unselectable = "off";
	document.getElementById("EDITBOX").unselectable = "off";
	document.getElementById("EDITBOX").focus();
}

function LoadDocument()
{
	// Setting CancelError to true and using try/catch allows the user to
	// click cancel on the save as dialog without causing a script error.
	cDialog.CancelError=true;
	try
		{
		var answer = checkForSave();
		// The user has clicked yes in the modal dialog box called in the
		// checkForSave function.
		if (answer) {
			var sCancel = SaveDocument();
				// The user has clicked cancel in the save as dialog box; exit function.
				if (sCancel) return; 
			cDialog.Filter = "HTM Files (*.htm)|*.htm|Text Files (*.txt)|*.txt";
			cDialog.ShowOpen();
			var ForReading = 1;
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var f = fso.OpenTextFile(cDialog.filename, ForReading);
			var r = f.ReadAll();
			f.close();
			document.getElementById("EDITBOX").innerHTML = r;
			// This variable is used in the checkForSave function to see
			// if there is new content in the div. 
			sPersistValue = document.getElementById("EDITBOX").innerHTML;
		};
		// The user has clicked no in the modal dialog box called in
		// the checkForSave function.
		if (answer == false) {
			cDialog.Filter = "HTM Files (*.htm)|*.htm|Text Files (*.txt)|*.txt";
			cDialog.ShowOpen();
			var ForReading = 1;
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var f = fso.OpenTextFile(cDialog.filename, ForReading);
			var r = f.ReadAll();
			f.close();
			document.getElementById("EDITBOX").innerHTML = r;
			sPersistValue = document.getElementById("EDITBOX").innerHTML;
		}
		document.getElementById("EDITBOX").focus();
		}
	catch(e)
		{
		var sCancel = "true";
		return sCancel;
		}
}

// This function checks to see if the div has new text, then displays a modal dialog box if appropriate
function checkForSave()
{ 
	if ((document.getElementById("EDITBOX").innerHTML != sPersistValue) && (document.getElementById("EDITBOX").innerHTML != "")) {
	 	var checkSave=showModalDialog('dcheckForSave.htm','','dialogHeight:125px;dialogWidth:250px;scroll:off;status:no');
	} else {
		var checkSave=false;
	};
  return checkSave;
}

// SaveDocument uses the common dialog box object to display the save as dialog, then writes 
// a textstream object from the value of the div's innerHTML property
function SaveDocument()
{
	// Setting CancelError to true and using try/catch allows the user to click cancel on the 
	// save as dialog without causing a script error
  	cDialog.CancelError=true
  	try
		{
  		cDialog.Filter="HTM Files (*.htm)|*.htm|Text Files (*.txt)|*.txt";
  		cDialog.ShowSave();
  		var fso = new ActiveXObject("Scripting.FileSystemObject");
  		var f = fso.CreateTextFile(cDialog.filename, true);
  		f.write(document.getElementById("EDITBOX").innerHTML);
  		f.Close();
  		sPersistValue = document.getElementById("EDITBOX").innerHTML;
		}
  	catch(e)
		{
  		var sCancel = "true";
  		return sCancel;
		}
		document.getElementById("EDITBOX").focus();
}

// NewDocument creates a new "document" by clearing the content of the div. If there is any 
// new content in the div, the user is asked whether or not to save
function NewDocument()
{
  	var answer = checkForSave();
   	if (answer) {
			var sCancel = SaveDocument();
	 		if (sCancel) return;
			EDITBOX.innerHTML = "";
		}
    if (answer == false) {
      document.getElementById("EDITBOX").innerHTML = "";
		};
	document.getElementById("EDITBOX").focus();
}

// Open Color Dialog to Set ForeColor or BackColor
var Color;              // ForeColor|BackColor
var FGInitColor = null; // Flag for dialog display
var BGInitColor = null; // Flag for dialog display
var FGChosenColor;      // Color chosen from dialog
var BGChosenColor;      // Color chosen from dialog
var TempString;         // 
var SelectRange;        // Text Range made from selection

function SetColor()
{
	if (Color == "ForeColor") 
	{
		// Display color dialog
		if (FGInitColor == null) {
			FGChosenColor = FGCOLORDIALOG.ChooseColorDlg();
		} else {
			FGChosenColor = FGCOLORDIALOG.ChooseColorDlg(FGInitColor);
		};
		
		// Change decimal to hex
		FGChosenColor = FGChosenColor.toString(16);
		
		// Add extra zeroes if hex number is less than 6 digits
		if (FGChosenColor.length < 6) {
  		TempString = "000000".substring(0,6-FGChosenColor.length);
  		FGChosenColor = TempString.concat(FGChosenColor);
		};
		
		// Make range from selection and apply color
		SelectRange = document.selection.createRange();
		SelectRange.execCommand(Color,false,FGChosenColor);
		
		// Set the initialization color to the chosen color
		FGInitColor = FGChosenColor;
		
		// Set color on button icon
		//FGColorBar.style.backgroundColor = FGChosenColor
	};
	
	if (Color == "BackColor") 
	{
		// Display color dialog
		if (BGInitColor == null) {
			BGChosenColor = BGCOLORDIALOG.ChooseColorDlg();
		} else {
			BGChosenColor = BGCOLORDIALOG.ChooseColorDlg(BGInitColor);
		};
		
		// Change decimal to hex
		BGChosenColor = BGChosenColor.toString(16);
		
		// Add extra zeroes if hex number is less than 6 digits
		if (BGChosenColor.length < 6) {
  			TempString = "000000".substring(0,6-BGChosenColor.length);
  			BGChosenColor = TempString.concat(BGChosenColor);
		};
		
		// Make range from selection and apply color
		SelectRange = document.selection.createRange();
		SelectRange.execCommand(Color,false,BGChosenColor);
		
		// Set the initialization color to the chosen color
		BGInitColor = BGChosenColor;
		
		// Set color on button icon
		//BGColorBar.style.backgroundColor = BGChosenColor
	};
}

function ViewPage()
{
	Win = open("", "", "");
	Win.document.open();
	Win.document.write("<html></body>");
	Win.document.write(EDITBOX.innerHTML);
	Win.document.write("</body></html>");
	Win.document.close();
}

function ShowHTML()
{
	if (document.getElementById("HTMLCheckbox").checked) {
	
		document.getElementById("HTMLBOX").innerText = document.getElementById("EDITBOX").innerHTML;
		document.getElementById("HTMLBOX").style.visibility = "visible";
		document.getElementById("HTMLBOX").focus();
		}
	else {
		document.getElementById("EDITBOX").innerHTML = document.getElementById("HTMLBOX").innerText;
		document.getElementById("HTMLBOX").style.visibility = "hidden";
		document.getElementById("EDITBOX").focus();
		};
}

// Show the help dialog
function ShowHelp()
{
	showModelessDialog("Help.htm", "", "dialogWidth:200px; dialogHeight:300px; status:no; help:yes");
}

</script>