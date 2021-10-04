$(function() {
    navbarImg = '.navbar-nav > a.sidebar-brand';
    sidebarSize104 = '<img src="/img/logo_sm.png" style="width: 3em">';
    sidebarSize224 = '<img src="/img/logo_shadow.png" style="width: 10em;">';

    $(navbarImg).html(sidebarSize224);
});

function sidebarChangeImg() {
	var sidebarSize = $('#accordionSidebar').width(); // 104 OR 224
	
	if (sidebarSize == 104) {
        $(navbarImg).html(sidebarSize104);
    }
    if (sidebarSize == 224) {
        $(navbarImg).html(sidebarSize224);
    }
}

$(window).resize(function() { // 윈도우 크기 변경 시
    sidebarChangeImg();
});

$('#sidebarToggle').click(function() {
	sidebarChangeImg()
});
