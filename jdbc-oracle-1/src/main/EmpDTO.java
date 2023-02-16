package main;

public class EmpDTO {
    private Integer idx;
    private String name;
    private String email;
    private Integer salary;
    private Integer depIdx;

    public EmpDTO(Integer idx, String name, String email, Integer salary, Integer depIdx) {
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.depIdx = depIdx;
    }

    public Integer getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email + "@test.com";
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getDepIdx() {
        return depIdx;
    }

}
