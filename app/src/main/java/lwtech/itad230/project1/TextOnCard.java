package lwtech.itad230.project1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TextOnCard extends AppCompatActivity {
    private EditText textOnCard,ansOnCard;
    public static final String QUESTION_CARD= "QuestionResult";
    public static final String ANSWER_CARD = "AnswerCard";
    private static final String QUESTION = "Question";
    private static final String ANSWER = "Answer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_on_card);
        textOnCard = (EditText) findViewById(R.id.textOnCard);
        ansOnCard = (EditText) findViewById(R.id.ansOnCard);

        TextView pageTitle = (TextView)findViewById(R.id.pageTitle);
        Typeface tf = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.ttf");
        pageTitle.setTypeface(tf);


        if (savedInstanceState != null) {
            String strEditText = savedInstanceState.getString(QUESTION, "<default string>");
            textOnCard.setText(strEditText);
            strEditText = savedInstanceState.getString(ANSWER, "<default string>");
            ansOnCard.setText(strEditText);

        }
        //Log.d("TEXT ON FOLDER","....You are here....");
    }

    /**
     * onSaveInstanceState function saves the values for activity recreation
     * @param savedInstanceState values save din the bundle
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the string data from the EditText widget
        String strEditText = textOnCard.getText().toString();

        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(QUESTION, strEditText);

        // get the string data from the EditText widget
         strEditText = ansOnCard.getText().toString();

        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(ANSWER, strEditText);

    }

    /**
     * onClickOk handler for OK button
     * @param view
     */
    protected void onClickOkButton(View view) {
        String question = textOnCard.getText().toString();
        String answer =  ansOnCard.getText().toString();
        Intent intent = new Intent();
        /*Send the result in extras*/
        //Log.d("TEXT ON FOLDER","....Return result....");
       // Toast.makeText(getApplicationContext(),"Send result", Toast.LENGTH_SHORT).show();

        intent.putExtra(QUESTION_CARD, question);
        intent.putExtra(ANSWER_CARD,answer);

        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     *onClickCancel handler for cancel button
     * @param view
     */
    protected void onClickCancelButton(View view) {
        // Toast.makeText(getApplicationContext(),"resultSend", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}
