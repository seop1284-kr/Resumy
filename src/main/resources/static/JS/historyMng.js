var careerIdNo = 0;
var careerUserId = "";
var spec01 = 0;
var spec02 = 0;
var spec03 = 0;
var spec04 = 0;
$(document).ready(function(){
	
	loadPage();
	
	//학력사항 초등학교 버튼
	$("#btnSchool01").click(function(){
		$("#spec01").show();
		$("#spec02").hide();
		$("#spec03").hide();
		$("#spec04").hide();
		$("#btnSchool01").attr("class", "btn btn-mint active");
		$("#btnSchool02").attr("class", "btn btn-mint");
		$("#btnSchool03").attr("class", "btn btn-mint");
		$("#btnSchool04").attr("class", "btn btn-mint");
	});//초등학교 버튼 end
	
	//학력사항 중학교 버튼
	$("#btnSchool02").click(function(){
		$("#spec02").show();
		$("#spec01").hide();
		$("#spec03").hide();
		$("#spec04").hide();
		$("#btnSchool02").attr("class", "btn btn-mint active");
		$("#btnSchool01").attr("class", "btn btn-mint");
		$("#btnSchool03").attr("class", "btn btn-mint");
		$("#btnSchool04").attr("class", "btn btn-mint");
	});//중학교 버튼 end
	
	//학력사항 고등학교 버튼
	$("#btnSchool03").click(function(){
		$("#spec03").show();
		$("#spec01").hide();
		$("#spec02").hide();
		$("#spec04").hide();
		$("#btnSchool03").attr("class", "btn btn-mint active");
		$("#btnSchool02").attr("class", "btn btn-mint");
		$("#btnSchool01").attr("class", "btn btn-mint");
		$("#btnSchool04").attr("class", "btn btn-mint");
	});//고등학교 버튼 end
	
	//학력사항 대학/대학원 버튼
	$("#btnSchool04").click(function(){
		$("#spec04").show();
		$("#spec01").hide();
		$("#spec02").hide();
		$("#spec03").hide();
		$("#btnSchool04").attr("class", "btn btn-mint active");
		$("#btnSchool01").attr("class", "btn btn-mint");
		$("#btnSchool02").attr("class", "btn btn-mint");
		$("#btnSchool03").attr("class", "btn btn-mint");
		
	});//대학/대학원 버튼 end

	//경력 버튼 클릭시 경력폼 출력 및 백그라운 색상 변경
	$("#btnCareer").click(function(){
		$("#btnCareerPuls").show();
		$("#careerContent").show();
		$("#btnCareer").attr("class", "btn btn-mint active");
		$("#btnNewcomer").attr("class", "btn btn-mint");
		$("#careerPuls").empty();
		//makeCareerContentPuls(0);
	});//경력 버튼 end
	
	//신입 버튼 클릭시 백그라운 색상 변경
	$("#btnNewcomer").click(function(){
		$("#btnCareerPuls").hide();
		$("#careerContent").hide();
		$("#btnNewcomer").attr("class", "btn btn-mint active");
		$("#btnCareer").attr("class", "btn btn-mint");
		$("#careerPuls").empty();
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
	//$("#careerPuls").hide();
	$("#spec01").hide();
	$("#spec02").hide();
	$("#spec03").hide();
	$("#spec04").hide();
	$("#careerContent").empty();
	$("#careerPuls").empty();		
	$("#btnCareerPuls").hide();

	
	selectMemberList(); // 기본정보
	selectCareerList(); // 경력
	selectSpecList(); // 학력
	
}//loadPage end

//기본정보 조회
function selectMemberList(){
	$.ajax({
		url : "/memberAjax/list/" ,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.length > 0){

					for(var i = 0; i<data.length; i++){	
												
						$("input[name='name']").eq(i).val(data[i].name);
						$("input[name='email']").eq(i).val(data[i].email);
						$("select[name='gender']").eq(i).val(data[i].gender);
						$("input[name='phone']").eq(i).val(data[i].phone);
						$("input[name='birthday']").eq(i).val(data[i].birthday);
						$("input[name='address']").eq(i).val(data[i].address);
						$("input[name='userid']").eq(i).val(data[i].userid);
					}
				}else{
					
				}	
			}else{
				alert("기본정보를 읽어오지 못 했습니다");
			}
		}
	}); 		
	
} // end selectMemberList()

//기본정보 수정
function updateMember(formId){
	
	var serialData = $("#"+formId).serialize();
	
	$.ajax({
		url : "/memberAjax",  // url : /board
		type : "PUT",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("수정 성공");
				loadPage();
			}
		}
	});
}//updateMember end()



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
					//최종 이력 세팅
					$("#spec" + data[0].cat).show();
					$("#btnSchool" + data[0].cat).attr("class", "btn btn-mint active");
					
					//학력list 생성
					for(var i = 0; i<data.length; i++){	
						if(data[i].cat == 01){
							spec01++;
							$("#cat_01").val(data[i].cat);
							$("#school_01").val(data[i].name);
							$("#schoolArea_01").val(data[i].area);	
						}else if(data[i].cat == 02){
							spec02++;
							$("#cat_02").val(data[i].cat);
							$("#school_02").val(data[i].name);
							$("#schoolArea_02").val(data[i].area);
						}else if(data[i].cat == 03){
							spec03++;
							$("#cat_03").val(data[i].cat);
							$("#school_03").val(data[i].name);
							$("#schoolArea_03").val(data[i].area);
							$("#major_03").val(data[i].major);	
						}else if(data[i].cat == 04){
							spec04++;
							$("#cat_04").val(data[i].cat);
							$("#school_04").val(data[i].name);
							$("#schoolArea_04").val(data[i].area);
							$("#major_04").val(data[i].major);
							$("#university_04").val(data[i].university);
						}
													
					}//for문 end	
						if(spec01 == 0){
							$("#btnWriteSpec01").show();
							$("#btnUpdateSpec01").hide();
						}else{
							$("#btnUpdateSpec01").show();
							$("#btnWriteSpec01").hide();
						}
						if(spec02 == 0){
							$("#btnWriteSpec02").show();
							$("#btnUpdateSpec02").hide();
						}else{
							$("#btnUpdateSpec02").show();
							$("#btnWriteSpec02").hide();
						}
						if(spec03 == 0){
							$("#btnWriteSpec03").show();
							$("#btnUpdateSpec03").hide();
						}else{
							$("#btnUpdateSpec03").show();
							$("#btnWriteSpec03").hide();
						}
						if(spec04 == 0){
							$("#btnWriteSpec04").show();
							$("#btnUpdateSpec04").hide();
						}else{
							$("#btnUpdateSpec04").show();
							$("#btnWriteSpec04").hide();
						}
				} else {
					alert("조회 실패");
				}
			}// success if() end
		}
	});
} // end selectSpecList()

//학력사항 추가
function writeSpec(formId){	
	//from id 에서 초:01/중:02/고:03/대:04 중 value를 확인 하기 위함 
	var sFormCatCd = formId.substr(formId.length-2, 2 );
	$("#cat_"+sFormCatCd).val(sFormCatCd);
					
	var serialData = $("#"+formId).serialize();
	
	$.ajax({
		url : "/specAjax",  // url : /board
		type : "POST",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("등록 성공");
				loadPage();
				//등록 혹은 수정시 해당 입력 정보가 출력			
				$("#spec"+sFormCatCd).show();
			}
		}
	});	
	
}//writeSpec() end

//학력사항 수정
function updateSpec(formId){
	//from id 에서 초:01/중:02/고:03/대:04 중 value를 확인 하기 위함 
	var sFormCatCd = formId.substr(formId.length-2, 2 );
	$("#cat_"+sFormCatCd).val(sFormCatCd);
					
	var serialData = $("#"+formId).serialize();
	
	$.ajax({
		url : "/specAjax",  // url : /board
		type : "PUT",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				alert("수정 성공");
				loadPage();
				//등록 혹은 수정시 해당 입력 정보가 출력
				$("#spec"+sFormCatCd).show();
			}
		}
	});
}//updateSpec end()


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
					console.log("data.length : " + data.length);
					makeCareerContent(data);	
					$("#btnCareer").attr("class", "btn btn-mint active");
					$("#btnNewcomer").attr("class", "btn btn-mint");
					$("#btnCareerPuls").show();
					
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
				}else{
					
				}	
			}else{
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
		console.log("makeCareerContent 1 ");
	for(var i = 0; i < data.length; i++){				
		var careerContent ="<form name='career"+ i +"' id='career"+ i +"' action='' method='post'>";
		careerContent +='<table width="100%"><tr><td width="20%">회사명</td><td width="80%"><input type="text" class="inputBox" name="company" value="" placeholder="회사명을 입력하세요"></td>';
		careerContent +='<td width= 50px>';
		careerContent +="<button type='button' class='updateBtn btn btn-mint'onclick='updateCareer(\"career"+i+"\")'>수정</button></td>";
		careerContent +="<td width= 50px><button type='button' name='btnCareerDelete' class='deleteBtn btn btn-mint' onclick='deleteCareer(\"career"+i+"\")' >삭제</button></td></tr>";
		careerContent +='<tr><td width="15%">재직기간</td><td width="75%"><input type="text" class="inputBox" name="hiredate" value="">  ~  <input type="text" class="inputBox" name="leavedate" value=""></td></tr>';
		careerContent +='<tr><td width="15%">퇴사사유</td><td width="75%"><input type="text" class="inputBox2" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td></tr>';
		careerContent +='<tr><td width="15%">직급/직책</td><td width="75%"><input type="text" class="inputBox" name="post" value=""></td></tr>';
		careerContent +='<tr><td width="15%">근무부서</td><td width="75%"><input type="text" class="inputBox" name="dept" value=""></td></tr>';
		careerContent +='<tr><td width="15%">지역</td><td width="75%"><input type="text" class="inputBox" name="companyArea" value=""></td></tr>';
		careerContent +='<tr><td width="15%">연봉</td><td width="75%"><input type="text" class="inputBox" name="salary" value=""> <input type="text" class="inputBox" name="manwon" value="" placeholder = "만원"></td></tr>';
		careerContent +='<tr><td width="15%">담당업무</td><td width="75%"><input type="text" class="inputBox" name="work" value=""></td></tr><tr><td colspan="4"><hr></td></tr>';
		/* hidden setting */
		careerContent +='<input type="hidden" id="id" name="id" value="">';
		careerContent +='<input type="hidden" id="userid" name="userid" value="">';
		careerContent +='</table></form>';

//		careerContent +='<tr><td width="10%"></td><td width="60%"></td><td width="20%"><button type="button" class="">경력추가+</button></td></tr></table>';

		$('#careerContent').append(careerContent);	
	}
}

function makeCareerContentPuls(careerIdNo){
		console.log("makeCareerContentPuls 2 ");

	var careerContent ="<form name='conent_plus"+ careerIdNo +"' id='conent_plus"+ careerIdNo +"' action='' method='post'>";
	    careerContent +='<table width="100%"><tr><td width="20%">회사명</td><td width="80%"><input type="text" name="company" value="" placeholder="회사명을 입력하세요"></td>';
		careerContent +="<td ><button type='button' class='writeBtn btn btn-mint' onclick='writeCareer(\"conent_plus"+ careerIdNo +"\")'>등록</button></td>";
		careerContent +='<td ><button type="button" class="deleteBtn btn btn-mint" id="careerDelete" onclick="$(conent_plus'+ careerIdNo +').remove()">삭제</button></td></tr>';
		careerContent +='<tr><td width="15%">재직기간</td><td width="80%"><input type="text" name="hiredate" value="">  ~  <input type="text" name="leavedate" value=""></td></tr>';
		careerContent +='<tr><td width="15%">퇴사사유</td><td width="80%"><input type="text" name="lvreason" value="" placeholder="퇴사사유 입력하세요"></td></tr>';
		careerContent +='<tr><td width="15%">직급/직책</td><td width="80%"><input type="text" name="post" value=""></td></tr>';
		careerContent +='<tr><td width="15%">근무부서</td><td width="80%"><input type="text" name="dept" value=""></td></tr>';
		careerContent +='<tr><td width="15%">지역</td><td width="80%"><input type="text" name="companyArea" value=""></td></tr>';
		careerContent +='<tr><td width="15%">연봉</td><td width="80%"><input type="text" name="salary" value=""> <input type="text" name="manwon" value="" placeholder = "만원"></td></tr>';
		careerContent +='<tr><td width="15%">담당업무</td><td width="80%"><input type="text" name="work" value=""></td></tr><tr><td colspan="4"><hr></td></tr></tr>';
		/* hidden setting */
		//careerContent +='<input type="hidden" id="id" name="id" value="">';
		careerContent +='<input type="hidden"  name="userid" value= CareerDTO.getUserId>';
		careerContent +='</table></form>';
		
//		careerContent +='<tr><td width="10%"></td><td width="60%"></td><td width="20%"><button type="button" class="">경력추가+</button></td></tr>';
		
		$('#careerPuls').append(careerContent);
}