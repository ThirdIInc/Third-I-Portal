
<script>

function callTimer(){
	try{
		parent.document.getElementById("timerDiv1").innerHTML=eval(parent.document.getElementById("timerDiv1").innerHTML)-1;
		if(parent.document.getElementById("timerDiv1").innerHTML==-1){
		parent.document.getElementById("timerDiv1").innerHTML=59;
		parent.document.getElementById("timerDiv2").innerHTML=eval(parent.document.getElementById("timerDiv2").innerHTML)-1;
		}
		if(eval(parent.document.getElementById("timerDiv1").innerHTML)<=0 && eval(parent.document.getElementById("timerDiv2").innerHTML)<=0){
		clearInterval(t1);
		parent.window.location='<%=request.getContextPath()%>/common/Login_logout.action?logout=forceLogout';
		}
		}catch(e){alert(e);}
}
/***** Time in seconds***/
var extra_time = 900;
var test = true ;
var loginName ;
var infoId;
function ShowTimeoutWarning() {

try{
	var pool ='<%=(String)session.getAttribute("poolId")%>';
	var user ='<%=(String)session.getAttribute("lname")%>';
	<%
	request.setAttribute("infoId",session.getAttribute("poolId"));
	request.setAttribute("loginName",session.getAttribute("lname"));
	
	%>
	parent.document.getElementById ('loginPassword').value='';
 	parent.document.getElementById ('MainDiv').style.display='';
    parent.document.getElementById ('MainDiv').style.visibility='visible';
    parent.document.getElementById ('MainDiv').style.position='absolute';

    parent.document.getElementById ('MainDiv').style.top='0px';
    parent.document.getElementById ('MainDiv').style.left='0px';
    parent.document.getElementById ('MainDiv').style.width= '100%';
    
	
 	var height=parent.document.body.scrollHeight;
    parent.document.getElementById ('MainDiv').style.height= height+'px';

    parent.document.getElementById('MainDiv').style.backgroundColor = "Gray";
    parent.document.getElementById('MainDiv').style.filter = "alpha(opacity=40)";
    parent.document.getElementById('MainDiv').style.opacity = "0.6";
    parent.document.getElementById("timerDiv1").innerHTML=10;
    parent.document.getElementById("timerDiv2").innerHTML=20;
    
	parent.document.getElementById('optionDiv').style.display='block';
	
	parent.window.focus();
	parent.document.getElementById("loginPassword").focus();
	JSFX_FloatTopDiv();
	t1=setInterval('callTimer();',1000);
	clearTimeout(t2);
	t2=setTimeout('ShowTimeoutWarning();', (extra_time));
	
	}catch(e){alert(e);}
	 
}

t2=setTimeout('ShowTimeoutWarning();',(extra_time));

var verticalpos="fromtop"

if (!document.layers)
document.write('</div>');

function JSFX_FloatTopDiv()
{
	var startX = 250,
	startY = 200;
	var ns = (navigator.appName.indexOf("Netscape") != -1);
	var d = parent.document;
	function ml(id)
	{
		var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
		if(d.layers)el.style=el;
		el.sP=function(x,y){this.style.left=x;this.style.top=y;};
		el.x = startX;
		if (verticalpos=="fromtop")
		el.y = startY;
		else{
		el.y = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		el.y -= startY;
		}
		return el;
	}
	window.stayTopLeft=function()
	{
		if (verticalpos=="fromtop"){
		var pY = ns ? pageYOffset : parent.document.body.scrollTop;
		ftlObj.y += (pY + startY - ftlObj.y)/8;
		}
		else{
		var pY = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		ftlObj.y += (pY - startY - ftlObj.y)/8;
		}
		ftlObj.sP(ftlObj.x, ftlObj.y);
		setTimeout("stayTopLeft()", 10);
	}
	ftlObj = ml("optionDiv");
	stayTopLeft();
}



</script>