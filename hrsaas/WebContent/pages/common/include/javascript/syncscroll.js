// This is a function that returns a function that is used
// in the event listener
var x=0;
function getOnScrollFunction(oElement,toElement) {

	return function () {
	try{
	
	//alert("oElement.scrollTop "+x );
		if (oElement._scrollSyncDirection == "horizontal" || oElement._scrollSyncDirection == "both"){
		
			if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Safari")!=-1) {
					oElement.scrollLeft =toElement.scrollLeft+2;
					toElement.scrollTop =x;
		
				}
			if (navigator.userAgent.indexOf("MSIE")!=-1 && navigator.userAgent.indexOf("Opera")==-1) {
					oElement.scrollLeft =event.srcElement.scrollLeft;
					
				}
			
		
		}
		if (oElement._scrollSyncDirection == "vertical" || oElement._scrollSyncDirection == "both"){
				if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Safari")!=-1) {
				x=toElement.scrollTop;
				oElement.scrollTop = toElement.scrollTop+2;
				}
				if (navigator.userAgent.indexOf("MSIE")!=-1 && navigator.userAgent.indexOf("Opera")==-1) {
					oElement.scrollTop = event.srcElement.scrollTop;
					
				}
				
			}
		}
		catch(e){
		alert("getOnScrollFunction"+e);
		}
	};

}
// This function adds scroll syncronization for the fromElement to the toElement
// this means that the fromElement will be updated when the toElement is scrolled
function addScrollSynchronization(fromElement, toElement, direction) {
try{
	
	removeScrollSynchronization(fromElement);
		
		fromElement._syncScroll = getOnScrollFunction(fromElement,toElement);
		fromElement._scrollSyncDirection = direction;
		fromElement._syncTo = toElement;
	// Firefox
	if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Safari")!=-1) {
	
	
		toElement.addEventListener('scroll', fromElement._syncScroll, true);
	}
	// IE
	if (navigator.userAgent.indexOf("MSIE")!=-1 && navigator.userAgent.indexOf("Opera")==-1) {

		toElement.attachEvent("onscroll", fromElement._syncScroll);
	}
	
	}
	catch(e){
	alert("in syn.js addScrollSynchronization"+e);
	}
}

// removes the scroll synchronization for an element
function removeScrollSynchronization(fromElement) {
try{
	if (fromElement._syncTo != null)
		fromElement._syncTo.detachEvent("onscroll", fromElement._syncScroll);

	fromElement._syncTo = null;;
	fromElement._syncScroll = null;
	fromElement._scrollSyncDirection = null;
	}
	catch(e){
	alert("removeScrollSynchronization"+e);
	}
}


