/*
공채 속보 API

기업로고 <regLogImgNm>
채용업체명 <empBusiNm>
채용 분야 <empWantedTypeNm>
지원자격(경력) <empWantedCareerNm>
지원자격(학력) <empWantedEduNm>
근무지 <workRegionNm>
채용시작일 <empWantedStdt>
채용마감일 <empWantedEndt>

채용기업 홈페이지 <empWantedHomepg>
채용사이트URL <empWantedHomepgDetail>
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
		table += "<td>" + info[i].empWantedTitle + "</td>\n";
		table += "<td>" + info[i].empBusiNm + "</td>\n";
		table += "<td>" + info[i].coClcdNm + "</td>\n";
		table += "</tr>\n";
	}
	
	$('#infoTable').html(table);
	
}


