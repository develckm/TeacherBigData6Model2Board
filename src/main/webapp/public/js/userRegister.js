const registerForm=document.forms["registerForm"];
registerForm.onsubmit=async function (e){
    let check=false;
    e.preventDefault();
    check=await userIdCheck();
    console.log(check);
    if(check){
        registerForm.submit();
    }
}

registerForm["userId"].addEventListener("change",userIdCheck);


async function userIdCheck(){
    let val=registerForm["userId"].value;
    //1.길이가 4이상
    //2.ajax로 userid가 저장되어 있는 지 확인
    if(val.length>=4){
        let url="idCheck.do?userId="+val;
        let resp=await fetch(url);
        if(resp.status===200){
            const jsonObj=await resp.json();
            console.log(jsonObj)
            if(jsonObj.idCheck===1){ //존재하는 아이디
                setInvalid("userId",idInvalidMsg,"이미 사용중인 아이디 입니다.");
                return false;
            }else{
                setValid("userId");
                return true;
            }
        }else if(resp.status===400){
            setInvalid("userId",idInvalidMsg,"잘못된 요청입니다.");
            return false;
        }else if(resp.status===500){
            setInvalid("userId",idInvalidMsg,"서버오류 다시시도");
            return false;
        }
    }else{
        setInvalid("userId",idInvalidMsg,"4자리 이상 입력");
        return false;
    }
}
function setValid(name,msgNode,msg){
    registerForm[name].classList.remove("is-invalid");
    registerForm[name].classList.add("is-valid");
    if(msgNode!=null){
        msgNode.innerText=msg;
    }
}
function setInvalid(name,msgNode,msg){
    registerForm[name].classList.remove("is-valid");
    registerForm[name].classList.add("is-invalid");
    msgNode.innerText=msg;
}