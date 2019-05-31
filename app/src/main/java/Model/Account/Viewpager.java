package Model.Account;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Viewpager extends FragmentPagerAdapter {
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public Viewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }
    //tra ve item tai vi tri i

    @Override
    public int getCount() {
        return fragments.size();
    }
    // tra ve so luong item muon hien thi tren viewpager

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        titles.add(title);

    }
}
