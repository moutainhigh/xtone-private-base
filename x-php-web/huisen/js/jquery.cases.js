var class_array={};
function run_case(index)
{
	var id=0,x=0,y=0;
	$('.case_list .case_f').each(function(){
		x=parseInt(id/4);
		y=( id%4);
		$(this).css({'top':(215*x)+'px','left':(235*y)+'px'});
		id++;
	});
	id=0;
	$('.case_list .case_f').each(function(){
		var info=class_array['id'+$(this).attr('id').replace('case_id_','')];
		var res;
		res=index==-1 || index == info['class'];		
		if(res)
		{
			x=parseInt(id/4);
			y=( id%4);
			$(this).show().stop().animate({'top':(175*x)+'px','left':((235*y)+7)+'px'},400);
			id++;
		}
		else
		{
			$(this).fadeOut();
		}
				
	});
	$('.case_list').stop().animate({'height':(175*(x+1))+'px'});
}

$(function(){
	$(".case_list .case_body").hover(function(){
				$('.case_w',this).stop().animate({"bottom":"30px"},400);	
				
				$(".y",this).stop().css({'top':'20px'}).animate({'top':"63px"},400).show();
				$('.fire',this).show();
			},function(){
				$('.case_w',this).stop().animate({"bottom":"15px"},400);
				$('.fire, .y',this).hide();
			});	
	if( window.location.href.indexOf('#')>-1)
	{
		var xint=window.location.href.split('#');
		xint=xint[xint.length-1];
		$('.case_btn .btn'+xint).addClass('btn'+xint+'_fire').css({'height':'96px'});
		run_case(xint-1,-1);
	}
	else
	{
		run_case(-1,-1);
	}
});