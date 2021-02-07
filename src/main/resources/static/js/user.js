let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
			this.save();
		});
		//$("#btn-login").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
		//	this.login();
		//});전통적인 로그인방식
		$("#btn-update").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
			this.update();
		});
	},
	
	save: function(){
		//alert('user의  save함수가 호출되었습니다.');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);

		//ajax호출 시에 default가 비동기로 호출된다.
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청한다.
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자도으로 자바 오브젝트를 변환해준다.
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지에 대한 것이다.(MIME)
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			alert(resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	
	/*전통적인 로그인방식
	login: function(){
		//alert('user의  save함수가 호출되었습니다.');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
 		$.ajax({
			// 로그인 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지에 대한 것이다.(MIME)
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			alert(resp);
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	}
	*/
	
	update: function(){
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지에 대한 것이다.(MIME)
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},

}

index.init();

