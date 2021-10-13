/*
공채 속보 API

★기업로고 <regLogImgNm>
★채용제목 <empWantedTitle>
★채용업체명 <empBusiNm>
★기업구분 <coClcdNm>
★고용형태 <empWantedTypeNm>
지원자격(경력) <empWantedCareerNm>
지원자격(학력) <empWantedEduNm>
근무지 <workRegionNm>
채용시작일 <empWantedStdt>
★채용마감일 <empWantedEndt> <- 이걸로 공고 종료일 d-day 구하기

★채용기업 홈페이지 <empWantedHomepg>
★채용사이트URL <empWantedHomepgDetail> - 현재 로고에 적용되어 있는 url


url: 
http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNKTM9J2OCLG1EI81VOVZ2VR1HJ&callTp=L&returnType=XML&&startPage=1&display=100
*/

document.cookie = "safeCookie1=foo; SameSite=Lax";
document.cookie = "safeCookie2=foo";
document.cookie = "crossCookie=bar; SameSite=None; Secure";

$(document).ready(function() {
	$.ajax({
		url: "/indexAjax/",
		type: "POST",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				parseJson(data);

			}
		}
	});
	
	
	$(window).scroll(function() {
        if ($(this).scrollTop() > 500) {
            $('#MOVE_TOP_BTN').fadeIn();
        } else {
            $('#MOVE_TOP_BTN').fadeOut();
        }
    });
    
    $("#MOVE_TOP_BTN").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
	
});

function parseJson(data) {

	var info = data.dhsOpenEmpInfo;
	var list = ""
	var now = new Date();

	for (var i = 0; i < info.length; i++) {
		list += "<a href='" + info[i].empWantedHomepgDetail + "'><li class='infoBox'>\n";
		list += "<div class='logo'><img src ='" + info[i].regLogImgNm + "' alt='" + info[i].empWantedTitle + "'></div>\n";
		list += "<span class='busiNm ellipsis'>" + info[i].empBusiNm + "</span><span class='clcdNm'>"+ info[i].coClcdNm+"</span>\n";
		list += "<span class='title ellipsis'>" + info[i].empWantedTitle + "</span>\n";
		list += "<span class='type ellipsis'>" + info[i].empWantedTypeNm.replaceAll("|", " / ") + "</span>\n";

		// yyyyMMdd 형식의 문자열을 Date 형식으로 바꾸는 함수
		function parse(str) {
			var y = str.substr(0, 4);
			var m = str.substr(4, 2);
			var d = str.substr(6, 2);
			return new Date(y, m - 1, d);
		}
		var date = parse(info[i].empWantedEndt);
		var now = new Date();
		var perDay = 1000 * 60 * 60 * 24;
		var dday = now.getTime() - date.getTime();
		var result = (Math.floor(dday / perDay)) * -1;

		if (result < 0) {
			result = "종 료";
			list += "<div class='endt endtEx'>" + result + "</div>\n";
		} else if (result == 0) {
			result = "D-day";
			list += "<div class='endt blinking'>" + result + "</div>\n";
		} else if (0 < result && result < 4) {
			result = "D - " + (Math.floor(dday / perDay)) * -1;
			list += "<div class='endt endtUnder3'>" + result + "</div>\n";
		} else {
			result = "D - " + (Math.floor(dday / perDay)) * -1;
			list += "<div class='endt'>" + result + "</div>\n";
		}
		
		list += "</li></a>";
	}

	$('#infoList').html(list);

}


const quotes = [
  {
    "quote": "당신은 움츠리기보다 활짝 피어나도록 만들어진 존재입니다.",
    "source": " 오프라 윈프리 "
  },
  {
    "quote": "연은 순풍이 아니라 역풍에 가장 높이 난다.",
    "source": " 윈스턴 처칠 "
  },
  {
    "quote": "늘 명심하라. 성공하겠다는 너 자신의 결심이 다른 어떤 것보다 중요하다는 것을.",
    "source": " 에이브러햄 링컨 "
  },
  {
    "quote": "기회는 그냥 오지 않는다. 그러므로 기회가 오면 바로 잡아라.",
    "source": " 오드리 햅번 "
  },
  {
    "quote": "스승은 문을 열어준다. 하지만 반드시 당신 스스로 들어가야만 한다.",
    "source": " 중국 속담 "
  },
  {
    "quote": "가장 큰 영광은 한번도 실패하지 않음이 아니라 실패할때마다 다시 일어서는 데에 있다.",
    "source": " 공자 "
  },
  {
    "quote": "과정에서 재미를 느끼지 못하는데, 성공하는 일은 거의 없다.",
    "source": " 데일 카네기 "
  },
  {
    "quote": "우리의 모든 꿈은 이루어질 것이다. 그것들을 믿고 나아갈 용기만 있다면",
    "source": " 월트 디즈니 "
  },
]

function randomQuote(){
  let random = quotes[Math.floor(Math.random() * quotes.length)];
  quotation.innerText = random.quote;
  source.innerText = random.source;
}

setInterval(randomQuote, 3000);





