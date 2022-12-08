const registerForm=document.forms["registerForm"];
registerForm["userId"].addEventListener("change",userIdCheck);


function userIdCheck(){
    let check=false;
    let val=registerForm["userId"].value;
    //1.길이가 4이상
    //2.ajax로 userid가 저장되어 있는 지 확인
    if(val.length>=4){
        loadIdCheck(val)?.then((jsonObj)=>{
            if(jsonObj.idCheck===1){ //존재하는 아이디
                registerForm["userId"].classList.remove("is-valid");
                registerForm["userId"].classList.add("is-invalid");
                idInvalidMsg.innerText="이미 사용중인 아이디 입니다.";
            }else{
                check=true;
                registerForm["userId"].classList.remove("is-invalid");
                registerForm["userId"].classList.add("is-valid");
            }
        })
    }else{
        registerForm["userId"].classList.remove("is-valid");
        registerForm["userId"].classList.add("is-invalid");
        idInvalidMsg.innerText="4자리 이상 입력";
    }
    return check;
}
async function loadIdCheck(id){
    let url="idCheck.do?userId="+id;
    let response=await fetch(url);
    if(response.status===200){
        return await response.json();
    }else if(response.status===400){
        alert("잘못된 접근 입니다.");
    }else if(response.status===500){
        alert("서버 오류! 다시시도");
    }
}