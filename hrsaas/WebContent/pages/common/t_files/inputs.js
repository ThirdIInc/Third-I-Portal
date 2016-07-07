function hasSupport() {

	var ie55 = /msie 5\.[56789]/i.test( navigator.userAgent );
	
	return ( typeof document.implementation != "undefined" &&
			document.implementation.hasFeature( "html", "1.0" ) || ie55 );
			
}

var fade=null;
var unfade=null;
var iframes=10;
var ifade_step=4;

function start_fade()
{
 iframe=0;
 ihex=186;  // Initial color value.
 ihex1=186;
 ihex3=186;
//FFF5B1
 icolorfade();
}

function start_unfade()
{
 iframe2=0;
 ihex2=255-iframes*ifade_step;
 icolorunfade();
}


function icolorfade() 
{	         	
 if(iframe<iframes) 
 {	
	// alert(ihex+","+ihex1+","+ihex3);
  ihex+=ifade_step; // increase color value
  ihex1+=ifade_step; // increase color value
  ihex3+=ifade_step; // increase color value
  fade.style.backgroundColor="rgb("+ihex+","+ihex1+","+ihex3+")"; // Set color value.
  iframe++;
  setTimeout("icolorfade()",20);	
 }
}


function icolorunfade() 
{	         	
 if(iframe2<iframes) 
 {	
  ihex2+=ifade_step;
  unfade.style.backgroundColor="rgb("+ihex2+","+ihex2+","+ihex2+")"; // Set color value.
  iframe2++;
  setTimeout("icolorunfade()",20);	
 } 
}


function WebInput( el) {
	if ( !hasSupport() || el == null ) return;
	this.element = el;
	
	// hook up events, using DOM0
	var oThis = this;
	this.element.onfocus = function () { WebInput.Over( oThis ); };
	this.element.onblur = function () { WebInput.Out( oThis ); };
}

WebInput.Over = function ( tabpage ) 
{
        fade=tabpage.element;
	start_fade();  
};

WebInput.Out = function ( tabpage ) {
        unfade=tabpage.element;
	start_unfade();

};

function init_inputs()
{
        if(! hasSupport()){return;}
	var all = document.getElementsByTagName( "*" );
	var l = all.length;

	for ( var i = 0; i < l; i++ ) 
	{
	  var el = all[i];

	  if(el.type=="text" || el.type=="textarea" || el.type=="password")
	  {
		if(all[i].name!="shipcharge" && all[i].name!="gtot")
			{
			    new WebInput(el);
			}
		
	  } 
	}//end for
}//end sub

