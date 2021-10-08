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
*/

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
});

function parseJson(data) {
	
	var info = data.dhsOpenEmpInfo;
	var table = ""
	
	for (var i = 0; i < info.length; i++) {
		table += "<tr>\n";
		table += "<td><a href='" + info[i].empWantedHomepgDetail + "'><img src ='" + info[i].regLogImgNm + "' alt='" + info[i].empWantedTitle + "'></a></td>\n";
		table += "<td>" + info[i].empWantedTitle + "</td>\n";
		table += "<td>" + info[i].empBusiNm + "</td>\n";
		table += "<td>" + info[i].coClcdNm + "</td>\n";
		table += "<td>" + info[i].empWantedTypeNm + "</td>\n";
		table += "<td>" + info[i].empWantedEndt + "</td>\n";
		table += "</tr>\n";
	}
	
	$('#infoTable').html(table);
	
}


