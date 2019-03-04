package pl.localdeals.localdealsapp;

public class Deal {
    private int _id;
    private int _type;
    private int _day;
    private int _hourStart;
    private int _hourEnd;
    private int _points;
    private int _local;
    private String _title;
    private String _price;
    private String _description;
    private String _terms;
    private String _jpgUrl;

    public Deal(int _id, int _type, int _day, int _hourStart, int _hourEnd, int _points, int _local, String _title, String _price, String _description, String _terms, String _jpgUrl) {
        this._id = _id;
        this._type = _type;
        this._day = _day;
        this._hourStart = _hourStart;
        this._hourEnd = _hourEnd;
        this._points = _points;
        this._local = _local;
        this._title = _title;
        this._price = _price;
        this._description = _description;
        this._terms = _terms;
        this._jpgUrl = _jpgUrl;
    }

    public int get_id() {
        return _id;
    }

    public int get_type() {
        return _type;
    }

    public int get_day() {
        return _day;
    }

    public int get_hourStart() {
        return _hourStart;
    }

    public int get_hourEnd() {
        return _hourEnd;
    }

    public int get_points() {
        return _points;
    }

    public int get_local() {
        return _local;
    }

    public String get_title() {
        return _title;
    }

    public String get_price() {
        return _price;
    }

    public String get_description() {
        return _description;
    }

    public String get_terms() {
        return _terms;
    }

    public String get_jpgUrl() {
        return _jpgUrl;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_type(int _type) {
        this._type = _type;
    }

    public void set_day(int _day) {
        this._day = _day;
    }

    public void set_hourStart(int _hourStart) {
        this._hourStart = _hourStart;
    }

    public void set_hourEnd(int _hourEnd) {
        this._hourEnd = _hourEnd;
    }

    public void set_points(int _points) {
        this._points = _points;
    }

    public void set_local(int _local) {
        this._local = _local;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_terms(String _terms) {
        this._terms = _terms;
    }

    public void set_jpgUrl(String _jpgUrl) {
        this._jpgUrl = _jpgUrl;
    }
}