
$(document).ready(function(){
	
	$('#content1').html('<h4><b>기본정보</b></h4>');
	$('#content2').html('<h4><b>학력사항</b></h4>');
	$('#content3').html('<h4><b>경력사항</b></h4>');
	$("#career01").hide();
	
	selectCareerList();
	//경력 버튼 클릭시 경력폼 출력 및 백그라운 색상 변경
	$("#btnCareer").click(function(){
		$("#careerContent").show();
		$("#btnCareer").css("background-color", "skyblue");
		$("#btnNewcomer").css("background-color", "#EFEFEF");
		$('#careerContent').append(careerContent);
	});
	
	//신입 버튼 클릭시 백그라운 색상 변경
	$("#btnNewcomer").click(function(){
		$("#careerContent").hide();
		$("#btnNewcomer").css("background-color", "skyblue");
		$("#btnCareer").css("background-color", "#EFEFEF");
	});
	
//	$(".careerDelete").click(function() {
//		if(!confirm( + "글을 삭제하시겠습니까?")) return false;
	
//  });
});

// 경력사항 조회
function selectCareerList(){

	$.ajax({
		url : "/careerAjax/list/" ,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				//updateList(data)
				//addViewEvent();
				if(data.length > 0){
					$("#career01").show();
						makeCareerContent(data);
					for(var i = 0; i<data.length; i++){	
						$("input[name='company']").eq(i).val(data[i].company);
						$("input[name='hiredate']").eq(i).val(data[i].hiredate);
						$("input[name='leavedate']").eq(i).val(data[i].leavedate);
						$("input[name='lvreason']").eq(i).val(data[i].lvreason);
						$("input[name='post']").eq(i).val(data[i].post);
						$("input[name='dept']").eq(i).val(data[i].dept);
						$("input[name='companyArea']").eq(i).val(data[i].area);
						$("input[name='salary']").eq(i).val(data[i].salary);
						$("input[name='work']").eq(i).val(data[i].work);
					}
				}	
			} else {
				alert("444");
			}
		}
	}); 		
	
} // end selectCareerList()


//경력사항 보유시 해당 페이지 들어오면 출력
function makeCareerContent(data){
		
	for(var i = 0; i < data.length; i++){				
		var careerContent ='<table id="career01" width="100%"><tr><td width="10%">회사명</td><td width="50%"><input type="text" name="company" value="" placeholder="회사명을 입력하세요"></td>';
		careerContent +='<td width="30%"><span><button type="button" class="">저장</button> <button type="button" class="" id="careerDelete">삭제</button></span></td></tr>'
		careerContent +='<tr><td width="10%">재직기간</td><td width="70%"><input type="text" name="hiredate" value="">  ~  <input type="text" name="leavedate" value=""></td><td width="10%"></td></tr>';
		careerContent +='<tr><td width="10%">퇴사사유</td><td width="60%"><input type="text" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">직급/직책</td><td width="60%"><input type="text" name="post" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">근무부서</td><td width="60%"><input type="text" name="dept" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">지역</td><td width="60%"><input type="text" name="companyArea" value=""></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">연봉</td><td width="60%"><input type="text" name="salary" value=""> <input type="text" name="manwon" value="만원"></td><td width="20%"></td></tr>';
		careerContent +='<tr><td width="10%">담당업무</td><td width="60%"><input type="text" name="work" value=""></td><td width="20%"></td></tr><tr><td colspan="3"><hr></td></tr>';
		careerContent +='<tr><td width="10%"></td><td width="60%"></td><td width="20%"><button type="button" class="">경력추가+</button></td></tr></table>';
		$('#careerContent').append(careerContent);
					}
}