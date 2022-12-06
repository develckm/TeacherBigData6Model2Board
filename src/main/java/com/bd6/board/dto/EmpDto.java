package com.bd6.board.dto;

import lombok.*;

import java.util.Date;

//@Getter  //java문서에는 없는데 class 컴파일하면서 Getter 메서들 생성
//@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto {
    private Integer empno;
    private String ename;
    private String job;
    private Date hiredate;
}
// chmod a+x /Users/choegyeongmin/intellij_study_workspace/apache-tomcat-9.0.69/bin/catalina.sh