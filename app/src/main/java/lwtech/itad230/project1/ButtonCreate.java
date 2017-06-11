package lwtech.itad230.project1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonCreate extends AppCompatActivity {

    private EditText messageView;
    public static final String EDIT_RESULT = "EditResult";
    private static final String KEY_EDITTEXTSTRING = "editTextString";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_create);
       messageView = (EditText)findViewById(R.id.folderName);
        if (savedInstanceState != null) {
            String strEditText = savedInstanceState.getString(KEY_EDITTEXTSTRING, "<default string>");
            messageView.setText(strEditText);
        }
        TextView pageTitle = (TextView)findViewById(R.id.pageTitle);
        Typeface tf1;
        tf1 = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.ttf");
        pageTitle.setTypeface(tf1);
    }

    /**
     * onSaveInstanceState function saves the values for activity recreation
     * @param savedInstanceState values save din the bundle
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the string data from the EditText widget
        String strEditText = messageView.getText().toString();

        // store the string data in the savedInstanceState bundle
        savedInstanceState.putString(KEY_EDITTEXTSTRING, strEditText);

    }

    /**
     * onClickOk handler for OK button
     * @param view
     */
    protected void onClickOk(View view) {
        String message = messageView.getText().toString();
        if((message == "")||(message == null))
        {
            message = "noName";
        }
        //Toast.makeText(getApplicationContext(),"resultSend", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ButtonCreate.this, MainActivity.class);
        /*Send the result in extras*/
        intent.putExtra(EDIT_RESULT, message);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     *onClickCancel handler for cancel button
     * @param view
     */
    protected void onClickCancel(View view) {
       // Toast.makeText(getApplicationContext(),"resultSend", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }
}
