package main;

public class App {
    public static void main(String[] args) throws Exception {
        // 서비스 싱글톤 객체 생성
        EmpService empService = EmpService.getInstance();

        // 서비스에서 DTO 객체를 받아서 출력
        EmpDTO empDto = empService.getEmpDto(100);
        if (empDto != null) {
            System.out.println(empDto.getName() + "의 월급은 " + empDto.getSalary());
        }

        // 리스트 출력 예제
        // List<EmpDto> empDtoList = empService.getEmpDtoList();
        // empDtoList.forEach((dto) -> System.out.println(dto.getEmail()));

    }
}
