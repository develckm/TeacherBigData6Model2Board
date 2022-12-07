<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">SPRING_BOARD 관리자 페이지</h1>
        <h2>깃 수업</h2>
        <h3>깃 시작하기</h3>
        <ol>
            <li>깃 저장소가 없는 위치**에 프로젝트 폴더 생성</li>
            <li>해당 프로젝트에서 콘솔(git bash) 실행</li>
            <li>git status 로 깃저장소가 아닌지 확인( Not a git repository )</li>
            <li>git init : 깃저장소 생성</li>
            <li>git config user.name "이름" : 깃 저장소의 주인 이름</li>
            <li>git config user.email sample@gmail.com : 깃 저장소의 주인의 이메일</li>
            <li>깃 원격 저장소의 계장과 동일하게 해야 인증이 편리하다</li>
            <li>프로젝트에 사용할 파일 생성</li>
            <li>리모트 저장소를 깃허브로 사용할거면 echo "# 프로젝트 리드미" >> README.md, git branch -m master main 을 할것을 권장)</li>
            <li>git add . : staging area(index)에 생성한 파일을 추가</li>
            <li>git commit -m "멘트" : staging area 에 등록된 파일의 스냅샷을 깃저장소의 저장</li>
            <li>git remote add "리모트 저장소 이름" "저장소 url"</li>
            <li>git push "리모트 저장소 이름" main</li>
        </ol>
        <h3>깃의 Staging Area(index)와 상태</h3>
        <ul>
            <li>staging area(index) : 작업 이력을 깃 저장소에 스냅샷으로 등록하기 전에 처리해주는 곳</li>
            <li>git status : 작업 이력의 상태를 보는 명령어
                <ul>
                    <li>Untracked files : 인덱스에 등록되지 않은 파일 (new file)</li>
                    <li>Changes not staged for commit: 인덱스에 등록되지 않은 파일 (modified,delete)</li>
                    <li>Changes to be committed : 인덱스에서 추적하는 파일 (작업이력을 add 한 상태 or 인덱스가 작업이력을 추적하는 상태)</li>
                    <li>nothing to commit, working tree clean : 작업이력과 저장소 이력이 동일 (최신,작업을 하지 않았다.)</li>
                    <li>Unmerged paths: 두 브랜치를 병합(merge)하던 중 충돌(CONFLICT)을 일으키면 수정하고 커밋하라고 강제하는 파일 </li>
                </ul>
            </li>
            <li>인덱스는 작업이력과 저장소의 이력을 수시로 검사하는 곳이다.</li>
            <li>이때 작업 이력의 상태를 확인하는 명령어가 git status 이다</li>
            <li>작업이력을 수정(new,modify,delete)을 하면 저장소에 등록하기 전 인덱스에 처리하는데 작업 이력을 인덱스에 올리는(추적하는) 명령어가 git add 파일</li>
            <li>git add 파일명 : 파일 하나를 인덱스에서 추적</li>
            <li>git add . : 해당 폴더 하위의 모든(숨김을 포함) 파일을 인덱스에서 추적</li>
            <li>git add * : 해당 폴더 하위의 모든 파일을 인덱스에서 추적(or 한번 저장소의 등록한적이 있는 모든 파일을 추적)</li>
            <li>인덱스에서 추적하는 파일의 수정된 이력을 스냅샷으로 저장소에 등록할 수 있는 데 이때 git commit -m ""을 사용한다.</li>
            <li>git commit -a -m "" : 한번 저장소에 등록되었던 파일은 git add *를 생략하고 저장소에 저장할 수 있다. </li>
            <li>git reset HEAD (.,*,파일명) : 추적을 취소(Unstage)</li>
        </ul>
        <h3>git diff 로 어떤 작업 이력이 수정되었는지 확인</h3>
        <ul>
            <li>git diff : 작업폴더의 작업 내역과 저장소의 이력을 비교해서 표시(Unstaged)</li>
            <li>git diff --staged(--cached): 인덱스의 작업 내역과 저장소의 이력을 비교해서 표시 (staged)</li>
            <li></li>
        </ul>
        <h3>git commit 으로 저장소 등록</h3>
        <ul>
            <li>인덱스에서 수정된 작업 이력을 저장소의 저장하는 명령어</li>
            <li>인덱스에서 저장하는 내역은 링크나 수정된 내역의 스냅샷으로 고유번호로 무결성을 지킨다.(앞의 7자리 만으로도 동작)</li>
            <li>파일은 인덱스에서 추적하지 않으면 절대 저장소에 저장할 수 없으며 한번 저장된 파일은 "-a" 옵션으로 인덱스 등록 및 저장 가능하다.</li>
        </ul>
        <h3>새로운 작업을 할 수 있는 브랜치</h3>
        <ul>
            <li>git branch "이름" : 기존의 브랜치를 복사한(깃을 복사와 비슷한 링크를 만든다) 새로운 브랜치가 생성된다.(작업하는 브랜치가 바뀌지 않는다.) </li>
            <li>HEAD : 작업하는 위치(브랜치 or 커밋)</li>
            <li>git checkout (브랜치명,커밋) : 작업하는 이력을 해당 브랜치나 커밋으로 이동</li>
            <li>새로만든 브랜치로 작업하려면 꼭 checkout 으로 브랜치를 선택해야한다.</li>
            <li>git checkout -- (.,*,파일명) : 작업이력을 마지막 저장소 이력과 동이하게 수정 (작업한 내역을 취소!!)</li>
        </ul>
        <h3>브랜치 통합 머지</h3>
        <ul>
            <li>git merge "브랜치명" : 통합의 기준이 되는 브랜치를 선택후(checkout) 해당 명령어를 하면 두 브랜치를 병합한 결과(commit)가 반환되고 기준이되는 브랜치가 병합한 결과로 이동 </li>
            <li>merge 통합 : 두개의 다른 이력(브랜치)을 병합하면 무조건 새로운 이력이 나온다.</li>
            <li>fast-forward merge : 이력이 자손관계면 기준이 되는 조상의 이력을 자손 이력으로 변경하기만 하는 상태</li>
            <li>git pull "저장소명" "브랜치명" : git fetch "저장소명" "브랜치명" 과 git merge "저장소명"/"브랜치명"이 모두 실행되는 명령어(저장소의 브랜치를 다운받고 병합)</li>
            <li>병합 중 같은 파일에 다른이력이 있는 경우 병합을 멈추고 충돌을 안내 Unmerged paths(CONFLICT) </li>
            <li>충돌을 해결하면(충돌이 일어난 부분 수정) 꼭 commit 을 해야 병합이 종료된다.</li>
        </ul>
        <h3>브랜치 관리</h3>
        <ul>
            <li>git branch</li>
            <li>git branch -v</li>
            <li>git branch -vv</li>
            <li>git branch --merge</li>
            <li>git branch --no -merge</li>
            <li>git branch -d</li>
            <li>git branch -m</li>
        </ul>
        <h3>브랜치 이름과 워크 플로워</h3>
        <ul>
            <li>master(main) : 통합 배포 브랜치(삭제되면 안된다!)</li>
            <li>develop</li>
            <li>hotfix</li>
            <li>topic</li>
            <li></li>
        </ul>
        <h3>리모트 저장소 github</h3>
        <ul>
            <li>작업폴더, 스테이지(index), 로컬저장소(.git), 리모트저장소</li>
            <li>리모트 저장소 : 로컬저장소를 원격으로 공유할 수 있는 저장소</li>
            <li>github : 깃의 리모트 저장소이며 프로젝트 공유 사이트이기도 하고 코드를 자동완성하는 인공지능을 제공</li>
            <li>git (add,remove,modify) remote "저장소명(origin)" "url" : 깃 저장소에 리모트 저장소 등록</li>
            <li>git push "저장소명" "브랜치명" : 로컬저장소의 브랜치 이력으로 리모트에 올리는 것</li>
            <li>git fetch "저장소명" "브랜치명" : 리모트의 브랜치를 로컬저장소에 다운받는 것</li>
            <li>git pull "저장소명" "브랜치명" : 리모트의 브랜치를 로컬저장소에 다운받고 현재 브랜치와 통합하는 것</li>

            <li>리모트저장소/브랜치이름 : 리모트 저장소에서 가져온 브랜치( git fetch "저장소이름" "브랜치이름" ) </li>
            <li>git checkout (--track) "저장소이름"/"브랜치명" : 리모트 저장소에서 가져온 브랜치를 선택</li>
            <li>git checkout "저장소의 브랜치명" : 리모트 저장소에서 가져온 브랜치를 새로운 브랜치로 만들어서 선택</li>
        </ul>
        <h3>rebase 통합</h3>
        <p>통합할 때 브랜치를 삭제하고 통합하는 브랜치로 커밋이력을 이동시키는 명령어(브랜치가 사라지면서 작업이력을 파악하기 힘들어서 기본적으로 사용하지 않는다)</p>

        <h3>메이븐 웹 프로젝트를 깃허브에서 받아서 사용하는 법 </h3>
        <ol>
            <li>리모트 저장소에서 다운 받기전 해당 폴더 내부에 동일한 이름의 폴더가 없어야 한다.</li>
            <li>git clone "리모트 url" : 리모트 저장소의 main 가지를 다운</li>
            <li>이클립스나 intelliJ는 프로젝트 임폴트를 해야 사용가능하다.</li>
            <li>이클립스 : import >maven >Existing Maven Project </li>
            <li>intelliJ : 1.pom.xml > +maven 모듈 추가 or 2.모듈 > + > 모듈가져오기 > 프로젝트 선택 > 외부모듈에서 maven 선택 </li>
            <li>(-)자바 프로젝트 보통 gitignore 설정으로 class(빌드)를 제외하고 저장소 올리기 때문에 처음 clone 할때 jdk 설정을 해야한다. </li>
            <li>(-)maven 종속성 추가  : pom.xml 에서 maven > 새로고침 (임폴트할때 안될 수 도 있어서 ...)</li>
            <li>톰캣의 구성편집에서 실행할 모듈을(war exploded : 매이븐 웹앱 배포하는 웹앱압축파일) 추가 </li>
        </ol>
    </main>
</body>
</html>