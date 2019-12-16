
function s_open(title,width,height,url){
	art.dialog.open(url, 
		{
			title: title,
			width: width,
			height: height,
			resize:true,
			drag:true,
			fixed: true,
			
		}
	);
}

function s_confirm(title,yesCall,noCall){
	art.dialog.confirm(title, function(){
   		 yesCall();
	}, function(){
	   noCall();
	});
}
