package lwtech.itad230.project1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewCards extends AppCompatActivity {

    private ViewPager mPager;
    private CardStackAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cards);

        ArrayList<String> question = new ArrayList<>();
        ArrayList<String> answer = new ArrayList<>();

        int noOfCards;

        Intent intent = getIntent();

        question = intent.getStringArrayListExtra("Question");
        answer = intent.getStringArrayListExtra("Answer");

        mPager = (ViewPager)findViewById(R.id.viewPager);

        mAdapter = new CardStackAdapter(getSupportFragmentManager(), question,answer, question.size());

        mPager.setPageTransformer(true, new CardStackTransformer());

        mPager.setOffscreenPageLimit(5);

        //LinearLayout ll = (LinearLayout)findViewById(R.id.fragment_card);

        mPager.setAdapter(mAdapter);
    }

    private class CardStackTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {

            if(position>=0)
            {
                page.setScaleX(0.8f - 0.02f * position);
                page.setScaleY(0.8f);

                page.setTranslationX(- page.getWidth()*position);
                page.setTranslationY(30*position);
            }
        }
    }
}
