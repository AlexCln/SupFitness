package supinfo.com.supfitness;

import java.util.Date;

public class Weight {
    private int _id;
    private int _weight;
    private Date _date;

    public Weight(int weight, Date date){
        this._weight = weight;
        this._date = date;
    }

    public Weight(){}

    public int get_id() {
        return _id;
    }

    public int get_weight() {
        return _weight;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }
}
