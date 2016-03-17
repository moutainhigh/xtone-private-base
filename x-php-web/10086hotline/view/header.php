<div id="top" style="background:url(images/top_bg.jpg); ">
<div class="top-main">
<div class="logo">
<a href="#"> <img src="./images/logo.png"></a>
</div>

<div class="menu">
    <div class="menu-li  <?php if(isset($head)&&$head=='index'){ echo "cur"; } ?>">
        <a href="index.php"><span style="font-size: 24px;">首页</span>HOME</a>
    </div>
    <div class="menu-li <?php if(isset($head)&&$head=='news'){ echo "cur"; } ?>">
        <a href="index.php?controller=news"><span style="font-size: 24px;">垄金动态</span>NEWS</a>
    </div>
    <div class="menu-li  <?php if(isset($head)&&$head=='games'){ echo "cur"; } ?>">
        <a href="index.php?controller=games"><span style="font-size: 24px;">产品中心</span>PRODUCTS</a>
    </div>
    <div class="menu-li  <?php if(isset($head)&&$head=='about'){ echo "cur"; } ?>">
        <a href="index.php?controller=about"><span style="font-size: 24px;">关于垄金</span>ABOUT US</a>
    </div>
    <div class="menu-li  <?php if(isset($head)&&$head=='JoinUs'){ echo "cur"; } ?>">
        <a href="index.php?controller=about&action=JoinUs"><span style="font-size: 24px;">加入垄金</span>JOIN US</a>
    </div>
    <div class="menu-li  <?php if(isset($head)&&$head=='contact'){ echo "cur"; } ?>">
        <a href="index.php?controller=about&action=contact"><span style="font-size: 24px;">联系我们</span>CONTACT US</a>
    </div>
</div>
</div>
</div>