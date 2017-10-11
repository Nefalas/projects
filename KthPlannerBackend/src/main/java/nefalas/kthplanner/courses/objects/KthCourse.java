package nefalas.kthplanner.courses.objects;

public class KthCourse {

    private String name;
    private String code;

    public KthCourse(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
