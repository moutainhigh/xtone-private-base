// JavaScript Document
$(function(){	
   var $cstemp = $("#online"), $stayBottomFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $cstemp.fadeIn(1000):$cstemp.fadeOut("slow");    
        if (!window.XMLHttpRequest)$cstemp.css("top", st + winh - 60);//IE6下的定位
    };
    $(window).bind("scroll", $stayBottomFun);
    $(function() { $stayBottomFun(); });

});