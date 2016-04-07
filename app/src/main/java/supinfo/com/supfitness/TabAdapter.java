package supinfo.com.supfitness;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;

public class TabAdapter extends FragmentPagerAdapter {
    int nbTab;

    public TabAdapter(ActionBar fm, int tab) {
        super(fm);
        this.nbTab = tab;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WeightFragment weightTab = new WeightFragment();
                return weightTab;
            case 1:
                WeightCurveFragment weightCurveTab = new WeightCurveFragment();
                return weightCurveTab;
            case 2:
                FootRacesFragment footRacesTab = new FootRacesFragment();
                return footRacesTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nbTab;
    }
}
