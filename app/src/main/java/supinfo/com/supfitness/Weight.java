package supinfo.com.supfitness;


public class Weight {
    private int _id;
    private int _weight;
    private String _date;
    private String _imc;

    public Weight(int weight, String date, String imc){
        this._weight = weight;
        this._date = date;
        this._imc = imc;
    }

    public Weight(){}

    public int get_id() {
        return _id;
    }

    public int get_weight() {
        return _weight;
    }

    public String get_date() {
        return _date;
    }

    public String get_imc() {
        return _imc;
    }

    public void set_imc(String _imc) {
        this._imc = _imc;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }
}
