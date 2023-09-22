package model;

public class UniversityInfo {

    //	名称,省,市,县,地址,介绍,985,211,软科排名,学校类型,学校属性,特色专业,2021分数线,2020分数线,2019分数线
//	, , , , , , ,  , , ,  , 2021 score line, 2020 score line, 2019 score line
    private int id;//id
    private String name;//名称
    private String province;//省
    private String city;//市
    private String county;//县
    private String address;//地址
    private String introduction;//介绍
    private String is_985;//是否是985
    private String is_211;//是否是211
    private String soft_science_ranking="1000";//软科排名
    private String school_type="综合类";//学校类型
    private String school_content="公办";//学校属性
    private String specialty;//特色专业
    private String score_line2021="：0";//2021分数线
    private String score_line2020;//2020分数线
    private String score_line2019;//2019分数线
    private Integer pic_id;//图片id

    public Integer getPic_id() {
        return pic_id;
    }

    public void setPic_id(Integer pic_id) {
        this.pic_id = pic_id;
    }

    public UniversityInfo(String name,String school_type, String province, String city, String score_line2021,String soft_science_ranking) {
        this.name = name;
        this.school_type=school_type;
        this.province = province;
        this.city = city;
        this.score_line2021 = score_line2021;
        this.soft_science_ranking=soft_science_ranking;

    }

    public UniversityInfo(String name, String introduction, Integer pic_id) {
        this.name = name;
        this.introduction = introduction;
        this.pic_id = pic_id;
    }

    public UniversityInfo(String name, String province, String city, String county, String address, String introduction, String is_985, String is_211, String soft_science_ranking, String school_type, String school_content, String specialty, String score_line2021, String score_line2020, String score_line2019) {
        super();
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = county;
        this.address = address;
        this.introduction = introduction;
        this.is_985 = is_985;
        this.is_211 = is_211;
        this.soft_science_ranking = soft_science_ranking;
        this.school_type = school_type;
        this.school_content = school_content;
        this.specialty = specialty;
        this.score_line2021 = score_line2021;
        this.score_line2020 = score_line2020;
        this.score_line2019 = score_line2019;
    }

    public UniversityInfo(int id, String name, String province, String city, String county, String address, String introduction, String is_985, String is_211, String soft_science_ranking, String school_type, String school_content, String specialty, String score_line2021, String score_line2020, String score_line2019) {
        super();
        this.id = id;
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = county;
        this.address = address;
        this.introduction = introduction;
        this.is_985 = is_985;
        this.is_211 = is_211;
        this.soft_science_ranking = soft_science_ranking;
        this.school_type = school_type;
        this.school_content = school_content;
        this.specialty = specialty;
        this.score_line2021 = score_line2021;
        this.score_line2020 = score_line2020;
        this.score_line2019 = score_line2019;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIs_985() {
        return is_985;
    }

    public void setIs_985(String is_985) {
        this.is_985 = is_985;
    }

    public String getIs_211() {
        return is_211;
    }

    public void setIs_211(String is_211) {
        this.is_211 = is_211;
    }

    public String getSoft_science_ranking() {
        return soft_science_ranking;
    }

    public void setSoft_science_ranking(String soft_science_ranking) {
        this.soft_science_ranking = soft_science_ranking;
    }

    public String getSchool_type() {
        return school_type;
    }

    public void setSchool_type(String school_type) {
        this.school_type = school_type;
    }

    public String getSchool_content() {
        return school_content;
    }

    public void setSchool_content(String school_content) {
        this.school_content = school_content;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getScore_line2021() {
        return score_line2021;
    }

    public void setScore_line2021(String score_line2021) {
        this.score_line2021 = score_line2021;
    }

    public String getScore_line2020() {
        return score_line2020;
    }

    public void setScore_line2020(String score_line2020) {
        this.score_line2020 = score_line2020;
    }

    public String getScore_line2019() {
        return score_line2019;
    }

    public void setScore_line2019(String score_line2019) {
        this.score_line2019 = score_line2019;
    }

    @Override
    public String toString() {
        return "ExtraVoiceInfo{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", address='" + address + '\'' +
                ", introduction='" + introduction + '\'' +
                ", is_985='" + is_985 + '\'' +
                ", is_211='" + is_211 + '\'' +
                ", soft_science_ranking='" + soft_science_ranking + '\'' +
                ", school_type='" + school_type + '\'' +
                ", school_content='" + school_content + '\'' +
                ", specialty='" + specialty + '\'' +
                ", score_line2021='" + score_line2021 + '\'' +
                ", score_line2020='" + score_line2020 + '\'' +
                ", score_line2019='" + score_line2019 + '\'' +
                '}';
    }
}


