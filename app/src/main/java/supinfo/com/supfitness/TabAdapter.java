package supinfo.com.supfitness;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabAdapter extends FragmentStatePagerAdapter {
    int nbTab;

    public TabAdapter(FragmentManager fm, int tab) {
        super(fm);
        this.nbTab = tab;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WeightFragment();
            case 1:
                return new WeightCurveFragment();
            case 2:
                return new FootRacesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nbTab;
    }
}
