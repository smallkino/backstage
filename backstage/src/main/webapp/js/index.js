jQuery(function(){
	jQuery("[name='subMenu']").click(function(){
		removeActiveMenu();
		jQuery(this).attr('class','active-menu');
		var url=jQuery(this).attr('target-url');
		jQuery('#iframeContent').attr('src',url);
		
	});
	
});

function removeActiveMenu(){
	jQuery("[name='subMenu']").each(function(index,item){
		var text = jQuery(item).attr('class');
		if(text == 'active-menu'){
			jQuery(item).attr('class','');
			return;
		}
	});
}
