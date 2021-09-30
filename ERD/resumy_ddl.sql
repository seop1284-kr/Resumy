DROP TABLE IF EXISTS `intr_feedback` CASCADE;
DROP TABLE IF EXISTS `hr_qna_a` CASCADE;
DROP TABLE IF EXISTS `hr_qna_q` CASCADE;
DROP TABLE IF EXISTS `hr_spec_info` CASCADE;
DROP TABLE IF EXISTS `hr_career` CASCADE;
DROP TABLE IF EXISTS `hr_file` CASCADE;
DROP TABLE IF EXISTS `hr_introduction_c` CASCADE;
DROP TABLE IF EXISTS `hr_introduction` CASCADE;
DROP TABLE IF EXISTS `hr_authority` CASCADE;
DROP TABLE IF EXISTS `hr_member` CASCADE;

-- 김민수
-- 회원정보 테이블
CREATE TABLE `hr_member` (
	`mem_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`mem_userid`	varchar(100)	NOT NULL UNIQUE	COMMENT '회원아이디',
	`mem_pw`	varchar(100)	NOT NULL	COMMENT '회원비밀번호',
	`mem_name`	varchar(10)	NOT NULL	COMMENT '회원이름',
	`mem_email`	varchar(50)	NOT NULL	COMMENT '회원이메일',
	`mem_gender`	int(1)	NULL	COMMENT '성별 1:남, 2:여',
	`mem_phone`	varchar(11)	NULL	COMMENT '연락처',
	`mem_birthday`	char(8)	NULL	COMMENT '생년월일',
	`mem_address`	varchar(255)	NULL	COMMENT '주소',
	`mem_career`	boolean	NULL	COMMENT '경력여부',
	`reg_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '등록일시',
	`mody_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '수정일시'/*,
	`mem_level`	boolean	NOT NULL	DEFAULT true	COMMENT '회원등급 (true:회원  false: 관리자)'*/
);

-- 회원권한 테이블(김진섭)
CREATE TABLE `hr_authority` (
	mem_userid varchar(50) REFERENCES hr_member(mem_userid),
	mem_auth varchar(50) NOT NULL,        -- 시큐리티의 authority
	PRIMARY KEY (mem_userid, mem_auth)
);
-- 김민수
-- 회원학력사항 테이블
CREATE TABLE `hr_spec_info` (
	`spec_id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '일련번호',
	`spec_cat_cd`	varchar(10)	NOT NULL	COMMENT '01: 초등학교 02: 중학교 03: 고등학교 04: 대학교.대학원',
	`spec_name`	varchar(10)	NOT NULL	COMMENT '학교명',
	`spec_area`	varchar(10)	NOT NULL	COMMENT '지역명',
	`spec_major`	varchar(10)	NULL	COMMENT '전공명',
	`spec_university`	varchar(10)	NULL	COMMENT '01: 2,3년제 02: 4년제  03: 대학원(석사) 04: 대학원(박사)',
	`mem_userid` varchar(100) NOT NULL
	FOREIGN KEY (mem_userid) REFERENCES hr_member(mem_userid) ON DELETE CASCADE
);

-- 경력사항 테이블
CREATE TABLE `hr_career` (
	`cr_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`cr_company`	varchar(20)	NOT NULL	COMMENT '회사명',
	`cr_hiredate`	varchar(8)	NOT NULL	COMMENT '재직일',
	`cr_leavedate`	varchar(8)	NOT NULL	COMMENT '퇴사일',
	`cr_lvreason`	varchar(20)	NULL	COMMENT '퇴사사유',
	`cr_post`	varchar(10)	NOT NULL	COMMENT '직급/직책',
	`cr_dept`	varchar(20)	NULL	COMMENT '근무부서',
	`cr_area`	varchar(20)	NULL	COMMENT '지역',
	`cr_salary`	int	NULL	COMMENT '연봉',
	`cr_work`	varchar(10)	NULL	COMMENT '담당업무',
	`mem_userid` varchar(100) NOT NULL,
	FOREIGN KEY (mem_userid) REFERENCES hr_member(mem_userid) ON DELETE CASCADE
);

-- 하병노
-- 파일 테이블
CREATE TABLE `hr_file` (
	`file_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`file_name`	varchar(255)	NOT NULL	COMMENT '첨부파일명(원본이름)',
	`file_cname`	varchar(255)	NOT NULL	COMMENT '바뀐 파일 이름',
	`file_volume`	int	NOT NULL	COMMENT '파일용량',
	`file_regdate`	datetime	NOT NULL	DEFAULT now()	COMMENT '등록일시',
	`file_memo`	varchar(8)	NULL  DEFAULT ''	COMMENT '메모',
	`mem_userid`	varchar(100)	NOT NULL,
	FOREIGN KEY (mem_userid) REFERENCES hr_member(mem_userid) #ON DELETE CASCADE #참조하는 부모(mem_id)가 삭제되면 같이 삭제
);

-- 김진섭
-- 자기소개서 테이블
CREATE TABLE `hr_introduction` (
	`intr_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`intr_title`	varchar(100)	NOT NULL	COMMENT '자소서제목',
	`intr_regdate`	datetime	NOT NULL	DEFAULT now()	COMMENT '등록일',
	`intr_public`	boolean	NOT NULL	DEFAULT false	COMMENT '공개여부 (공개: true, 비공개: false)',
	`intr_finish`	boolean	NOT NULL	DEFAULT false	COMMENT '완성 여부 (완성: true, 진행중 : false)',
	`mody_dtm`	datetime	NOT NULL	DEFAULT now()	COMMENT '수정 일시',
	`mem_userid`	varchar(100)	NOT NULL,
	FOREIGN KEY (mem_userid) REFERENCES hr_member(mem_userid) ON DELETE CASCADE
);

-- 자소서컨텐츠 테이블
CREATE TABLE `hr_introduction_c` (
	`intr_c_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`intr_question`	varchar(100)	NULL DEFAULT ""	COMMENT '자소서질문',
	`intr_content`	text	NULL	COMMENT '자소서내용',
	`intr_id`	int	NOT NULL	COMMENT '자소서 게시글 일련번호',
	FOREIGN KEY (intr_id) REFERENCES hr_introduction(intr_id) ON DELETE CASCADE
);

-- 자기소개서 피드백 게시판
CREATE TABLE `intr_feedback` (
	`fb_id`	int	NOT NULL AUTO_INCREMENT	PRIMARY KEY COMMENT '일련번호',
	`fb_userid`	varchar(20)	NOT NULL	COMMENT '유저아이디',
	`fb_content`	text	NOT NULL	COMMENT '피드백 내용',
	`fb_regdate`	datetime	NOT NULL DEFAULT now()	COMMENT '피드백 등록일시',
	`intr_id`	int	NOT NULL,
	FOREIGN KEY (intr_id) REFERENCES hr_introduction(intr_id) ON DELETE CASCADE
);


-- 노수빈
-- 고객센터 테이블
CREATE TABLE `hr_qna_q` (
	`q_id`			int			 NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '일련번호',
	`q_subject`		varchar(50)	 NOT NULL							 COMMENT '글 제목',
	`q_content`		text		 NOT NULL							 COMMENT '글 내용',
	`q_regdate`		datetime	 NOT NULL DEFAULT now()				 COMMENT '글 등록일시',
	`q_replyState` 	boolean  	 NOT NULL DEFAULT FALSE 			 COMMENT '답변 상태 : 0(답변없음), 1(답변있음)',
	`mem_userid`	varchar(100) NOT NULL							 COMMENT '회원아이디',
	FOREIGN KEY (mem_userid) REFERENCES hr_member(mem_userid)  ON DELETE CASCADE
);

-- 고객센터 답글 테이블
CREATE TABLE `hr_qna_a` (
	`q_id`		int		 NOT NULL PRIMARY KEY 	COMMENT '일련번호',
	`a_reply`	text	 NOT NULL				COMMENT '답글',
	`a_regdate`	datetime NOT NULL DEFAULT now()	COMMENT '답글 등록일시',
	FOREIGN KEY (q_id) REFERENCES hr_qna_q(q_id)  ON DELETE CASCADE
);