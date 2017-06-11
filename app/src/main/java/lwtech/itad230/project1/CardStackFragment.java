package lwtech.itad230.project1;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mamatha Kelu on 5/18/2017.
 */

public class CardStackFragment extends Fragment{

    private String strtext, strAns;
    private  String position;
    CardView cardFront;
    CardView cardBack;
    View view;
    @Override

    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getString("index");
            strtext = bundle.getString("Question "+Integer.parseInt(position)+"");
            strAns = bundle.getString("Answer "+Integer.parseInt(position)+"");
        }
        else
        {
            strtext = "";
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Typeface tf1;
        tf1 = Typeface.createFromAsset(getContext().getAssets(), "ElMessiri-Regular.ttf");

        view = inflater.inflate(R.layout.fragment_card, null);

        cardFront = (CardView)view.findViewById(R.id.Card_view);
        cardBack = (CardView)view.findViewById(R.id.Card_view2);

        String color;

        int color_index = Integer.parseInt(position);

        switch(color_index % 10)
        {
            case 0:
                color = /*"#E826EF"*/"#EE82EE";
                break;
            case 1:
                color = "#FFFF33";
                break;
            case 2:
                color = "#13F78D";
                break;
            case 3:
                color = "#FFFF00";
                break;
            case 4:
                color = "#5500FF";
                break;
            case 5:
                color = "#8B008B";
                break;
            case 6:
                color = "#FF0055";
                break;
            case 7:
                color = "#0055FF";
                break;
            case 8:
                color = "#FF4500";
                break;
            case 9:
                color = "#00BFFF";
                break;
            default:
                color = "#FF00FF";
                break;
        }
        cardFront.setCardBackgroundColor(Color.parseColor(color));
        cardBack.setCardBackgroundColor(Color.parseColor(color));

        TextView textView = new TextView(view.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(strtext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        textView.setTypeface(tf1);
        cardFront.addView(textView);

        TextView textView2 = new TextView(view.getContext());
        textView2.setGravity(Gravity.CENTER);
        textView2.setText(strAns);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        textView2.setTypeface(tf1);
        cardBack.addView(textView2);

        cardFront.setOnClickListener(new View.OnClickListener()
                                            {
                                                @Override
                                                public void onClick(View v)
                                                {

                    flipCard();
            }}
        );
        cardBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flipCard();
            }}
        );

        return view;
    }

    private void flipCard()
    {
        FlipAnimation flipAnimation = new FlipAnimation(cardFront, cardBack);

        if (cardFront.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        view.startAnimation(flipAnimation);
    }
}
