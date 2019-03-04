package pl.localdeals.localdealsapp;

import android.location.Location;

public class Local {
    private int _id;
    private String _name;
    private String _city;
    private String _address;
    private String webUrl;
    private String jpgUrl;
    private String _distance;
    private double _longitudinal;
    private double _lateral;


    public Local(int _id, String _name, String _city, String _address, String webUrl, String jpgUrl, double _longitudinal, double _lateral) {
        this._id = _id;
        this._name = _name;
        this._city = _city;
        this._address = _address;
        this.webUrl = webUrl;
        this.jpgUrl = jpgUrl;
        this._longitudinal = _longitudinal;
        this._lateral = _lateral;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getJpgUrl() {
        return jpgUrl;
    }

    public void setJpgUrl(String jpgUrl) {
        this.jpgUrl = jpgUrl;
    }

    public double get_longitudinal() {
        return _longitudinal;
    }

    public void set_longitudinal(double _longitudinal) {
        this._longitudinal = _longitudinal;
    }

    public double get_lateral() {
        return _lateral;
    }

    public void set_lateral(double _lateral) {
        this._lateral = _lateral;
    }

    public String get_distance() {
        return _distance;
    }

    public void set_distance(String _distance) {
        this._distance = _distance;
    }

    public void calculate_distance(Location device_location){
        double dz = 12756.274;
        double a = (device_location.getLongitude()-this.get_longitudinal())*Math.cos(this.get_lateral()*Math.PI/180);
        double b = device_location.getLatitude()-this.get_lateral();
        double result = Math.sqrt((a*a)+(b*b))*Math.PI*dz/360;
        if (result < 1){
            this._distance = String.valueOf(Math.round(result*100)*10) + "m";
        }else if (result > 100) {
            this._distance = String.valueOf(Math.round(result/10)*10) + "km";
        }else{
            this._distance = String.valueOf(Math.round(result*10)/10.0) + "km";
        }
    }
}
