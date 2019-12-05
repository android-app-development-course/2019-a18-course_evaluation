package com.scottmangiapane.courseevaluation.ui.footprint;

public class Footprint {
    private  int user_number;
    private String course_name;
    private  String teachername;
    private  double rating;
    private String description;

    public Footprint(){
        this.rating=5;
        this.course_name="计算机网络";
        this.teachername="唐华";
        this.user_number=1;
        this.description="计算机网络基础";
    }

    public  Footprint(int user_number,String course_name,String teachername,double rating,String description){
        this.user_number=user_number;
        this.course_name=course_name;
        this.teachername=teachername;
        this.rating=rating;
        this.description=description;
    }

    public int getUser_number(){
        return this.user_number;
    }

    public  String getCourse_name()
    {
        return  this.course_name;
    }

    public double getRating()
    {
        return this.rating;
    }

    public  String getTeachername()
    {
        return this.teachername;
    }

    public  String getDescription(){return this.description;}

    public void setTeachername(String tname)
    {
        this.teachername=tname;
    }

    public  void setUser_number(int unumber){
        this.user_number=unumber;
    }

    public  void setCourse_name(String ncourse)
    {
        this.course_name=ncourse;
    }

    public  void setRating(int rating)
    {
        this.rating=rating;
    }

    public  void setDescription(String description){this.description=description;}
}
