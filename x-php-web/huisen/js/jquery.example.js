$(function() {
    $("#tab_a li a").click(function() {        
        var curList = $("#tab_a li a.current").attr("rel");        
        var $newList = $(this);
        var curListHeight = $("#list-wrap").height();
        $("#list-wrap").height(curListHeight);
        $("#tab_a li a").removeClass("current");
        $newList.addClass("current");
        var listID = $newList.attr("rel");
        if (listID != curList) {
            $("#"+curList).fadeOut(300, function() {
                $("#"+listID).fadeIn();
                var newHeight = $("#"+listID).height();
                $("#list-wrap").animate({
                    height: newHeight
                });            
            });            
        }
        return false;
    });
});