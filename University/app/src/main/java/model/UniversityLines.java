package model;

public class UniversityLines {

    private String name;
    private String wenke;
    private String like;

    public UniversityLines(String name, String wenke, String like) {
        this.name = name;
        this.wenke = wenke;
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWenke() {
        return wenke;
    }

    public void setWenke(String wenke) {
        this.wenke = wenke;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
