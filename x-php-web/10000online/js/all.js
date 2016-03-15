
/* 导航控制 */
(function(){
	var $navList = $("#navList");
	var $navLine = $("#navLine");
	var style = null;
	var resetIndex = $navList.find("li.active").index();
	var placeArr = (function() {
		var tempArr = new Array();
		var $children = $navList.children();
		var childLen = $children.length;
		var childWidth = $children.first().width();
		var offsetMargin = $navList.width()/childLen - childWidth;
		for(var i = 0; i < childLen; i++) {
			var offset = (i + 1) * offsetMargin + i * childWidth;
			tempArr.push(offset);
		}
		return tempArr;
	})();

	var transform = function() {
		return 'transform' in document.documentElement.style || 'WebkitTransform' in document.documentElement.style || 'msTransform' in document.documentElement.style || 'MozTransform' in document.documentElement.style || 'OTransform' in document.documentElement.style;
	}();

	if(transform) {
		style = $navLine.get(0).style;
	}

	$navLine.show();
	goPosition(resetIndex);

	if(transform) {
		setTimeout(function() {
			style.transitionDuration = style.webkitTransitionDuration = style.MozTransitionDuration = style.OTransitionDuration = style.msTransitionDuration = 300 + "ms";
  		style.transitionTimingFunction = style.webkitTransitionTimingFunction = style.MozTransitionTimingFunction = style.OTransitionTimingFunction = style.msTransitionTimingFunction = "cubic-bezier(0.1, 0.57, 0.1, 1)";
		}, 100);
	}

	$navList.find("a").bind("mouseenter", function() {
		var index = $(this).parent().index();
		goPosition(index);
	});
	$navList.bind("mouseleave", function() {
		goPosition(resetIndex);
	});

	function goPosition(index) {
		if(transform) {
			style.left = 0;
			style.transform = style.webkitTransform = "translate(" + placeArr[index] + "px, 0)" + "translateZ(0)";
			style.MozTransform = style.OTransform = style.msTransform = "translateX(" + placeArr[index] + "px)";
		} else {
			$navLine.animate({"left": placeArr[index]});
		}
	}
})();

/* 二级导航 */
(function(){
	var $inNav = $("#tmLeftNav");
	if(!$inNav.length) return;
	var $inNavDot = $("#tmNavDot");
	if(!$inNavDot.length) return;
	var style = null;
	var placeArr = (function() {
		var tempArr = new Array();
		var $children = $inNav.children();
		var childLen = $children.length;
		var offsetMargin = $inNav.height()/childLen
		for(var i = 0; i < childLen; i++) {
			var offset = 25 + i * offsetMargin;
			tempArr.push(offset);
		}
		return tempArr;
	})();

	var transform = function() {
		return 'transform' in document.documentElement.style || 'WebkitTransform' in document.documentElement.style || 'msTransform' in document.documentElement.style || 'MozTransform' in document.documentElement.style || 'OTransform' in document.documentElement.style;
	}();

	if(transform) {
		style = $inNavDot.get(0).style;
		style.transitionDuration = style.webkitTransitionDuration = style.MozTransitionDuration = style.OTransitionDuration = style.msTransitionDuration = 300 + "ms";
  	style.transitionTimingFunction = style.webkitTransitionTimingFunction = style.MozTransitionTimingFunction = style.OTransitionTimingFunction = style.msTransitionTimingFunction = "cubic-bezier(0.1, 0.57, 0.1, 1)";
	}

	$inNav.find("li").bind("click", function() {
		var index = $(this).index();
		if(transform) {
			style.top = 0;
			style.transform = style.webkitTransform = "translate(0, " + placeArr[index] + "px)" + "translateZ(0)";
			style.MozTransform = style.OTransform = style.msTransform = "translateY(" + placeArr[index] + "px)";
		} else {
			$inNavDot.animate({"top": placeArr[index]});
		}

		// 做其他处理
		ctrlNav($(this));
	});

	// 解析URL,初始化焦点
	var page_href = window.location.search;
	page_href = page_href.replace("?", "");
	var urlArr = page_href.split("=");
	var urlIndex = urlArr[1];
	ctrlNav($inNav.find("li").eq(urlIndex));

	function ctrlNav(e) {
		e.addClass("active").siblings().removeClass("active");
		$("#statisticalType").html(e.html());
	}

})();

/* 首页城市选择 */
(function() {
	var $cityTip = $(".city-tip");
	$(".js-hover").bind("mouseenter", function() {
		$cityTip.removeClass("active");
		$(this).next().addClass("active");
	});
	$(".js-classify").bind("mouseenter", function() {
		$(this).addClass("hover").siblings().removeClass("hover");
	});
})();

/* 二级导航 展开与否 */
(function() {
	$(".js-detail").bind("click", function() {
		if(!$(this).hasClass("open")) {
			$(this).html("收起").addClass("open").parent().next().find(".briefing").hide(0, function() {
				$(this).next().slideDown(500);
			});
		} else {
			$(this).html("查看详情").removeClass("open").parent().next().find(".briefing").next().slideUp(500, function() {
				$(this).prev().show();
			});
		}
	});
})();

/* 职位 人物动画 */
(function() {
	$(".position-a").bind("mouseenter", function() {
		var $_parent = $(this).parent()
		$(".position-decoration").appendTo($_parent).addClass("active");
		$_parent.addClass("on").siblings().removeClass("on");
	});
})();

/* 装修加载弹框 */
function RModal() {
	this.init();
}
RModal.prototype.init = function() {
	var that = this;
	$(".renovation-modal-close, .renovation-modal-overlay").bind("click", function() {
		that.hide();
	});
}
RModal.prototype.show = function() {
	$(".renovation-modal-overlay, .renovation-modal-container").fadeIn(500);
}
RModal.prototype.hide = function() {
	$(".renovation-modal-overlay, .renovation-modal-container").fadeOut(500);
}

// about image
function AboutImage(e) {
	this.imgTimeId = null;
	this.init(e);
}

AboutImage.prototype.init = function(e) {
	this._e = e ? e : $("#aboutImage").find("ul.on");
	this.len = this._e.children().length;
	this.ele_w = this.len * 320;
	this.win_w = $(window).width();
	this._e.css({"width": this.ele_w});
	// 先解除上一次事件
	$("#imgLeftBtn").unbind("click");
	$("#imgRightBtn").unbind("click");

	var that = this;
	if(that.imgTimeId) window.clearInterval(that.imgTimeId);
	if(this.ele_w - this.win_w > 50) {
		$("#imgLeftBtn, #imgRightBtn").show();
		this.initEvents();
		this.autoMove();
	} else {
		$("#imgLeftBtn, #imgRightBtn").hide();
	}
}
AboutImage.prototype.initEvents = function() {
	var that = this;
	$("#imgLeftBtn").bind("click", function() {
		window.clearInterval(that.imgTimeId);
		var left = parseInt(that._e.attr("data-left")) + 320;
		if(left > 0) {
			left = 0;
		}
		that._e.animate({
			"left": left
		}, 300);
		that._e.attr("data-left", left);

		window.setTimeout(function() {
			that.imgTimeId = window.setInterval(function() {
				that.autoMoveFunc();
			}, 5000);
		}, 300);
	});

	$("#imgRightBtn").bind("click", function() {
		window.clearInterval(that.imgTimeId)
		var left = that._e.attr("data-left") - 320;
		var offetLen = that.win_w - that._e.width();
		if(left < offetLen) {
			left = offetLen;
		}
		that._e.animate({
			"left": left
		}, 300);
		that._e.attr("data-left", left);

		window.setTimeout(function() {
			that.imgTimeId = window.setInterval(function() {
				that.autoMoveFunc();
			}, 5000);
		}, 300);
	});
}
AboutImage.prototype.autoMove = function() {
	var that = this;
	that.imgTimeId = window.setInterval(function() {
		that.autoMoveFunc();
	}, 5000);
}

AboutImage.prototype.autoMoveFunc = function() {
	var left = this._e.attr("data-left") - 320;
	var offetLen = this.win_w - this.ele_w;
	if(left < offetLen) {
		left = offetLen;
	}
	if ((offetLen - left) == 0 ) {
		left = 0;
	}
	this._e.animate({
		"left": left
	}, 300);
	this._e.attr("data-left", left);
}




// function initAboutImage(e) {
// 	var $e = null;
// 	if(arguments.length > 0) {
// 		$e = e;
// 	} else {
// 		$e = $("#aboutImage").find("ul.on");
// 	}
// 	if(!$e.length) return;
// 	var len = $e.children().length;
// 	var ele_w = len * 320;
// 	var win_w = $(window).width();
// 	$e.css("width", ele_w);
// 	if(ele_w - win_w > 50) {
// 		$("#imgLeftBtn, #imgRightBtn").show();
// 	} else {
// 		$("#imgLeftBtn, #imgRightBtn").hide();
// 	}
	
// }
// 初始先执行一次
var aboutImage;
if($("#aboutImage").length) {
	aboutImage = new AboutImage();
}


$(window).resize(function() {
	if(aboutImage) {
		aboutImage.init();
	}
});