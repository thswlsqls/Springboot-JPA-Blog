let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
			this.save();
		});
		$("#btn-delete").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
			this.deleteById();
		});
		$("#btn-update").on("click", ()=>{ // function(){}이 아니라 화살표 함수 ()=>{} 를 사용하는 목적은 this를 바인딩하기 위해서 사용한다.
			this.update();
		});
	},
	
	save: function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
	
	$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지에 대한 것이다.(MIME)
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	
	deleteById: function(){
		let id = $("#id").text();
		
	$.ajax({	
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
	
	update: function(){
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
	
	$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지에 대한 것이다.(MIME)
			dataType: "json" //요청을 서버로보내고 응답이 왔을 때 기본적으로 모든 것이 문자열(그러나 형태가 json형식이라면) => javascript오브젝트로 변경해준다.
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
	},
}

index.init();

