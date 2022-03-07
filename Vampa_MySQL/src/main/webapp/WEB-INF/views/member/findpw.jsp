<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/findpw.css">
</head>
<body>

<div class="wrapper">
	
	<div class="wrap">
    <form id="findpw_form" method="post">
		<div class="logo_wrap">
			<span>Le'Go</span>
		</div>
		<div class="findpw_wrap"> 
			<div class="id_wrap">
					<div class="id_input_box">
					<input class="id_input" name="memberId">
				</div>
			</div>
			<div class="mail_wrap">
				<div class="mail_input_box">
				    <input class="mail_input" name="memberMail">
				</div>
			</div>

			<div class="findpw_button_wrap">
				<input type="button" class="findpw_button" value="임시비밀번호발급">
			</div>			
		</div>
    </form>
		
	</div>
</div>
<script>
 
		/* 임시 비밀번호 전송 */
		$(".findpw_button").click(function(){		    
		    var email = $(".mail_input").val();        // 입력한 이메일
		    var id = $(".id_input").val();
		    $.ajax({
		        type:"GET",
		        url:"mailCheck2?email=" + email + "&memberId="+id,
        		success:function(data){
        			const data1 = $.trim(data);
        			console.log(data1);
        	   		if(data1 == 'false'){
        				alert('아이디와 이메일을 확인해주세요');
        			}else{
        				alert('이메일로 임시 비밀번호를 발급해드렸습니다.\n메인페이지로 이동합니다.');
        				history.back();
        			}
        		}, fail:function(data){
        			const data2 = $.trim(data);
        			console.log(data2);
        		}

		    });
		});
 
</script>
</body>
</html>