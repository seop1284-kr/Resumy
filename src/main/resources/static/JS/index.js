
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

var api_key = "WNKTM9J2OCLG1EI81VOVZ2VR1HJ"; // 진섭님 키값
var req_url = "http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey="+ api_key +"&callTp=L&returnType=XML&startPage=1&display=10"

function removeTable(){
	document.getElementById('demoXML').removeChild(document.getElementById('demoXML').childNodes[0]);
}


