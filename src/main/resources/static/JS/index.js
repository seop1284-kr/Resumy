

// 공채 속보 API
/*
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

URL : https://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNKTM9J2OCLG1EI81VOVZ2VR1HJ&callTp=L&returnType=XML&&startPage=1&display=100	
*/

/*
var api_key = "WNKTM9J2OCLG1EI81VOVZ2VR1HJ"; // 진섭님 키값
var req_url = "http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=" + api_key + "&callTp=L&returnType=XML&startPage=1&display=10"

$(document).ready(function() {
	updateHireInfo();

});

function updateHireInfo() {

	$.ajax({
		url: req_url,
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				alert("목록 가져오기 성공");
				if (parseXML(data)){
				}
			}
		}
	});
}

 function parseXML(xmlDOM) {
	var table = "<tr><th>업체명</th><th>고용형태</th><th>마감일</th></tr>\n"
	$(xmlDOM).find('dhsOpenEmpInfo').each(function (){
		
		table += "<tr>\n";
		table += "<td>" + $(this).find('empBusiNm').text() + "</td>\n";
		table += "<td>" + $(this).find('empWantedTypeNm').text() + "</td>\n";
		table += "<td>" + $(this).find('empWantedEndt').text() + "</td>\n";
		table += "</tr>\n";
		
	});
	$('#tableXML').html(table);
		
}
*/

$(document).ready(function() {
	$.ajax({
		url: "/indexAjax/",
		type: "POST",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				parse(data);

			}
		}
	});
});

function parse(data) {
	
	var info = data.dhsOpenEmpInfo;
	var table = "";
	
	for (var i = 0; i < info.length; i++) {
		table += "<tr>\n";
		table += "<td>" + JSON.stringify(info[i].empWantedTitle) + "</td>\n";
		table += "<td>" + JSON.stringify(info[i].empBusiNm) + "</td>\n";
		table += "<td>" + JSON.stringify(info[i].coClcdNm) + "</td>\n";
		table += "<td>" + JSON.stringify(info[i].empWantedStdt) + "</td>\n";
		table += "<td>" + JSON.stringify(info[i].empWantedEndt) + "</td>\n";
		table += "<td>" + JSON.stringify(info[i].empWantedTypeNm) + "</td>\n";
		//table += "<td>" + JSON.stringify(info[i].regLogImgNm) + "</td>\n";
		//table += "<td>" + JSON.stringify(info[i].empWantedHomepgDetail) + "</td>\n";
		//table += "<td>" + JSON.stringify(info[i].empWantedMobileUrl) + "</td>\n";
		table += "</tr>\n";
	}
	
	$('#infoTable').html(table);
	
	/*var table = "<tr><th>업체명</th><th>고용형태</th><th>마감일</th></tr>\n"
	$(xmlDOM).find('dhsOpenEmpInfo').each(function (){
		
		table += "<tr>\n";
		table += "<td>" + $(this).find('empBusiNm').text() + "</td>\n";
		table += "<td>" + $(this).find('empWantedTypeNm').text() + "</td>\n";
		table += "<td>" + $(this).find('empWantedEndt').text() + "</td>\n";
		table += "</tr>\n";
		
	});
	$('#tableXML').html(table);*/
}


