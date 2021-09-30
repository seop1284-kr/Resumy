var careerIdNo = 0;
var careerUserId = "";
$(document).ready(function(){
	
	loadPage();
	
	//학력사항 초등학교 버튼
	$("#btnEleSchool").click(function(){
		$("#spec01").show();
		$("#spec02").hide();
		$("#spec03").hide();
		$("#spec04").hide();
		$("#btnEleSchool").css("background-color", "skyblue");
		$("#btnMidSchool").css("background-color", "#EFEFEF");
		$("#btnHigSchool").css("background-color", "#EFEFEF");
		$("#btnUniversity").css("background-color", "#EFEFEF");
	});//초등학교 버튼 end
	
	//학력사항 중학교 버튼
	$("#btnMidSchool").click(function(){
		$("#spec02").show();
		$("#spec01").hide();
		$("#spec03").hide();
		$("#spec04").hide();
		$("#btnMidSchool").css("background-color", "skyblue");
		$("#btnEleSchool").css("background-color", "#EFEFEF");
		$("#btnHigSchool").css("background-color", "#EFEFEF");
		$("#btnUniversity").css("background-color", "#EFEFEF");
	});//중학교 버튼 end
	
	//학력사항 고등학교 버튼
	$("#btnHigSchool").click(function(){
		$("#spec03").show();
		$("#spec01").hide();
		$("#spec02").hide();
		$("#spec04").hide();
		$("#btnHigSchool").css("background-color", "skyblue");
		$("#btnMidSchool").css("background-color", "#EFEFEF");
		$("#btnEleSchool").css("background-color", "#EFEFEF");
		$("#btnUniversity").css("background-color", "#EFEFEF");
	});//고등학교 버튼 end
	
	//학력사항 대학/대학원 버튼
	$("#btnUniversity").click(function(){
		$("#spec04").show();
		$("#spec01").hide();
		$("#spec02").hide();
		$("#spec03").hide();
		$("#btnUniversity").css("background-color", "skyblue");
		$("#btnEleSchool").css("background-color", "#EFEFEF");
		$("#btnMidSchool").css("background-color", "#EFEFEF");
		$("#btnHigSchool").css("background-color", "#EFEFEF");
		
	});//대학/대학원 버튼 end

	//경력 버튼 클릭시 경력폼 출력 및 백그라운 색상 변경
	$("#btnCareer").click(function(){
		$("#btnCareerPuls").show();
		$("#careerContent").show();
		$("#btnCareer").css("background-color", "skyblue");
		$("#btnNewcomer").css("background-color", "#EFEFEF");
		$("#careerPlus").remove();
		makeCareerContentPuls(0);
	});//경력 버튼 end
	
	//신입 버튼 클릭시 백그라운 색상 변경
	$("#btnNewcomer").click(function(){
		$("#btnCareerPuls").hide();
		$("#careerContent").hide();
		$("#btnNewcomer").css("background-color", "skyblue");
		$("#btnCareer").css("background-color", "#EFEFEF");
	});//신입 버튼 end
	
	//경력추가+ 버튼 
	$("#btnCareerPuls").click(function() {
		careerIdNo++;
		makeCareerContentPuls(careerIdNo);
	
  	});//경력추가+ 버튼 end

});
//초기 화면
function loadPage(){
	
	$('#content1').html('<h4><b>기본정보</b></h4>');
	$('#content2').html('<h4><b>학력사항</b></h4>');
	$('#content3').html('<h4><b>경력사항</b></h4>');
	$("#careerContent").hide();
	$("#spec01").hide();
	$("#spec02").hide();
	$("#spec03").hide();
	$("#spec04").hide();
	
	selectCareerList(); // 경력
	selectSpecList(); // 학력
	
}//loadPage end
//학력사항 조회
function selectSpecList(){
	$.ajax({
		url : "/specAjax/list/" ,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				//addViewEvent();
				if(data.length > 0){
					//$("#career01").show();
					for(var i = 0; i<data.length; i++){	
						if(data[i].cat == 01){
							$("input[name='id_01']").eq(i).val(data[i].id);
							$("input[name='cat_01']").eq(i).val(data[i].cat);
							$("input[name='School_01']").eq(i).val(data[i].name);
							$("input[name='schoolArea_01']").eq(i).val(data[i].area);
							$("input[name='userid_01']").eq(i).val(data[i].userid);
							console.log("1" + data[i].name);		
						}if(data[i].cat == 02){
							$("input[name='id_02']").eq(i).val(data[i].id);
							$("input[name='cat_02']").eq(i).val(data[i].cat);
							$("input[name='School_02']").eq(i).val(data[i].name);
							$("input[name='schoolArea_02']").eq(i).val(data[i].area);
							$("input[name='userid_02']").eq(i).val(data[i].userid);
							console.log("2" + data[i].name);	
						}
						if(data[i].cat == 03){
							$("input[name='id_03']").eq(i).val(data[i].id);
							$("input[name='cat_03']").eq(i).val(data[i].cat);
							$("input[name='School_03']").eq(i).val(data[i].name);
							$("input[name='schoolArea_03']").eq(i).val(data[i].area);
							$("input[name='major_03']").eq(i).val(data[i].major);
							$("input[name='userid_03']").eq(i).val(data[i].userid);
							console.log("3" + data[i].name);	
						}if(data[i].cat == 04){
							$("input[name='id_04']").eq(i).val(data[i].id);
							$("input[name='cat_04']").eq(i).val(data[i].cat);
							$("input[name='School_04']").eq(i).val(data[i].name);
							$("input[name='schoolArea_04']").eq(i).val(data[i].area);
							$("input[name='major_04']").eq(i).val(data[i].major);
							$("input[name='university_04']").eq(i).val(data[i].university);
							$("input[name='userid_04']").eq(i).val(data[i].userid);
								}
							}
						}	
						} else {
							alert("444");
			}
		}
	});
} // end selectSpecList()



// 경력사항 조회
function selectCareerList(){
	$.ajax({
		url : "/careerAjax/list/" ,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				//addViewEvent();
				if(data.length > 0){
					//$("#career01").show();
						makeCareerContent(data);
					for(var i = 0; i<data.length; i++){	
						
						$("input[name='company']").eq(i).val(data[i].company);
						$("input[name='hiredate']").eq(i).val(data[i].hiredate);
						$("input[name='leavedate']").eq(i).val(data[i].leavedate);
						$("input[name='lvreason']").eq(i).val(data[i].lvreason);
						$("input[name='post']").eq(i).val(data[i].post);
						$("input[name='dept']").eq(i).val(data[i].dept);
						$("input[name='companyArea']").eq(i).val(data[i].companyArea);
						$("input[name='salary']").eq(i).val(data[i].salary);
						$("input[name='work']").eq(i).val(data[i].work);
						$("input[name='userid']").eq(i).val(data[i].userid);
						$("input[name='id']").eq(i).val(data[i].id);
						
					}
				}	
			} else {
				alert("경력사항을 읽어오지 못 했습니다");
			}
		}
	}); 		
	
} // end selectCareerList()

//경력사항 추가
function writeCareer(formId){	
	
	var serialData = $("#"+formId).serialize();
	
	//console.log("!!! serialData : "+serialData);
	
	$.ajax({
		url : "/careerAjax",  // url : /board
		type : "POST",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("insert 성공 ");
				loadPage();
			}
		}
	});	
	
}//writeCareer() end
//경력사항 수정
function updateCareer(formId){
		var serialData = $("#"+formId).serialize();
			//alert(formId);
	$.ajax({
		url : "/careerAjax",  // url : /board
		type : "PUT",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("update 성공 ");
				loadPage();
			}
		}
	});
}//updateCareer end()

//경력사항 삭제
function deleteCareer(formId){	
	
	var serialData = $("#"+formId).serialize();
	
	//console.log("!!! serialData : "+serialData);
	
	$.ajax({
		url : "/careerAjax",  // url : /board
		type : "DELETE",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("delete 성공 ");
				loadPage();
			}
		}
	});	
	
}//deleteCareer end


//경력사항 보유시 해당 페이지 들어오면 출력
function makeCareerContent(data){
		
	for(var i = 0; i < data.length; i++){				
		var careerContent ="<form name='career"+ i +"' id='career"+ i +"' action='' method='post'>";
		careerContent +='<table width="100%"><tr><td width="10%">회사명</td><td width="50%"><input type="text" name="company" value="" placeholder="회사명을 입력하세요"></td>';
		careerContent +='<td width="30%">';
		careerContent +="<span><button type='button' class=''onclick='updateCareer(\"career"+i+"\")'>수정</button>";
		careerContent +="<button type='button' name='btnCareerDelete' onclick='deleteCareer(\"career"+i+"\")' >삭제</button></span></td></tr>";
		careerContent +='<tr><td width="10%">재직기간</td><td width="70%"><input type="text" name="hiredate" value="">  ~  <input type="text" name="leavedate" value=""></td><td width="10%"></td></tr>';
		careerContent +='<tr><td width="10%">퇴사사유</td><td width="60%"><input type="text" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">직급/직책</td><td width="60%"><input type="text" name="post" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">근무부서</td><td width="60%"><input type="text" name="dept" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">지역</td><td width="60%"><input type="text" name="companyArea" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">연봉</td><td width="60%"><input type="text" name="salary" value=""> <input type="text" name="manwon" value="만원"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">담당업무</td><td width="60%"><input type="text" name="work" value=""></td><td width="20%"></td></tr><tr><td colspan="3"><hr></td></tr>';
		/* hidden setting */
		careerContent +='<input type="hidden" id="id" name="id" value="">';
		careerContent +='<input type="hidden" id="userid" name="userid" value="">';
		careerContent +='</table></form>';

//		careerContent +='<tr><td width="10%"></td><td width="60%"></td><td width="20%"><button type="button" class="">경력추가+</button></td></tr></table>';

		$('#careerContent').append(careerContent);	
	}
}

function makeCareerContentPuls(careerIdNo){

	var careerContent ="<form name='conent_plus"+ careerIdNo +"' id='conent_plus"+ careerIdNo +"' action='' method='post'>";
	    careerContent +='<table width="100%"><tr><td width="10%">회사명</td><td width="50%"><input type="text" name="company" value="" placeholder="회사명을 입력하세요"></td>';
		careerContent +="<td width='30%'><button type='button' class='' onclick='writeCareer(\"conent_plus"+ careerIdNo +"\")'>등록</button>";
		careerContent +='<button type="button" class="" id="careerDelete" onclick="$(conent_plus'+ careerIdNo +').remove()">삭제</button></td></tr>';
		careerContent +='<tr><td width="10%">재직기간</td><td width="70%"><input type="text" name="hiredate" value="">  ~  <input type="text" name="leavedate" value=""></td><td width="10%"></td></tr>';
		careerContent +='<tr><td width="10%">퇴사사유</td><td width="60%"><input type="text" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">직급/직책</td><td width="60%"><input type="text" name="post" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">근무부서</td><td width="60%"><input type="text" name="dept" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">지역</td><td width="60%"><input type="text" name="companyArea" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">연봉</td><td width="60%"><input type="text" name="salary" value=""> <input type="text" name="manwon" value="만원"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">담당업무</td><td width="60%"><input type="text" name="work" value=""></td><td width="20%"></td></tr><tr><td colspan="3"><hr></td></tr></tr>';
		/* hidden setting */
		//careerContent +='<input type="hidden" id="id" name="id" value="">';
		careerContent +='<input type="hidden"  name="userid" value= CareerDTO.getUserId>';
		careerContent +='</table></form>';
		
//		careerContent +='<tr><td width="10%"></td><td width="60%"></td><td width="20%"><button type="button" class="">경력추가+</button></td></tr>';
		
		$('#careerContent').append(careerContent);
}