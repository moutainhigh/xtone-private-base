// JavaScript Document
var allBrandAnimate = function(t,d){
	if(t.find('.brand b').length != 0){
		return false;
	}
	t.find('.brand a').each(function(){
		$(this).append('<b>' + $(this).find('img').attr('alt') + '</b>')
	});
	t.find('.brand a').hover(function(){
		$(this).find('img').stop().animate({'height':0,'top':'56px'},d,function(){
			$(this).hide().next().show();
			$(this).next().animate({
				'height':'112px',
				'top':'0',
				'left':'0'
			},d);
		});
	},function(){
		$(this).find('b').animate({'height':0,'top':'56px'},d,function(){
			$(this).hide().prev().show();
			$(this).prev().animate({
				'height':'112px',
				'top':'0',
				'left':'0'
			},d);
		});
	});
}
allBrandAnimate($('#cases-index'),80);