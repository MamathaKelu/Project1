package lwtech.itad230.project1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Mamatha Kelu on 5/18/2017.
 */

public class CardStackAdapter extends FragmentStatePagerAdapter {

    private ArrayList quesArray, ansArray;
    private int noOfCards;
    public CardStackAdapter(FragmentManager fm, ArrayList question,ArrayList answer, int count)
    {

        super(fm);
        quesArray = question;
        noOfCards = count;
        ansArray = answer;
    }

    @Override
    public Fragment getItem(int position)
    {
       Bundle bundle = new Bundle();

        bundle.putString("index",  Integer.toString(position));
        bundle.putString("Question "+position+"", quesArray.get(position).toString());
        bundle.putString("Answer "+position+"",ansArray.get(position).toString());

        // set Fragmentclass Arguments
        CardStackFragment fragment = new CardStackFragment();
        fragment.setArguments(bundle);
        return /*new CardStackFragment()*/fragment;
    }

    @Override
    public int getCount()
    {
        return noOfCards;
    }
}
