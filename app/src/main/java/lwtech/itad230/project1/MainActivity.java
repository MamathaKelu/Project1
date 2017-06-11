package lwtech.itad230.project1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton floatButton;
    public static final int EDIT_REQUEST = 1;
    public static final int BUTTON_FOLDER = 2;
    ArrayList<String> buttonFolderName = new ArrayList<>();
    String buttonNameFile = "buttonNameFile.txt";
    String buttonCountFile = "buttonCountFile.txt";
    Typeface typeface, tf2;


    private int noOfButtonsCreated = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.title);
        typeface = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "ElMessiri-Regular.ttf");
        myTextView.setTypeface(typeface);

        /*Read number of buttons when app is launched */
        readFolderCount();

        /*Test read the button name from file*/
        readFolderName();

        /*Create buttons*/
        createFolder();

        floatButton  = (ImageButton) findViewById(R.id.imageButton);

        floatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*Toast.makeText(getApplicationContext(),"Button is clicked", Toast.LENGTH_SHORT).show();*/
                getButtonName("");
            }
        }
        );
    }

    private void createFolder() {

        for(int i=0;i<noOfButtonsCreated;i++)
        {
            //Toast.makeText(getApplicationContext(),"CF "+buttonFolderName.get(i), Toast.LENGTH_SHORT).show();
            createButton(buttonFolderName.get(i));
        }
    }

    private void readFolderName() {
        String s = "";
        try {
            FileInputStream fileIn = openFileInput(buttonNameFile);

            InputStreamReader inputStreamReader1 = new InputStreamReader(fileIn);
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
            String receiveString = "";
            while ( (receiveString = bufferedReader1.readLine()) != null ) {
                buttonFolderName.add(receiveString);
            }
            inputStreamReader1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readFolderCount() {
        try {

            FileInputStream fileIn = openFileInput(buttonCountFile);
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            noOfButtonsCreated = InputRead.read();
            /*File close*/
            InputRead.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(noOfButtonsCreated <=0)
        {
            noOfButtonsCreated = 0;
        }
    }

    protected void getButtonName(String msg)
    {
        Intent intent = new Intent(MainActivity.this, ButtonCreate.class);
        //Toast.makeText(getApplicationContext(),"button name ", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, EDIT_REQUEST);
    }

    /**
     * onActivityResult called when another activity sends result
     * @param requestCode - request code tom be respond to
     * @param resultCode - result code successul or cancel
     * @param data result
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == EDIT_REQUEST) {
            //Toast.makeText(getApplicationContext(),"result ", Toast.LENGTH_SHORT).show();
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
               // Toast.makeText(getApplicationContext(),"result OK", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(),"result ", Toast.LENGTH_SHORT).show();
                // The edit was successful, change the EditText widget's text
                String strResult = data.getStringExtra(ButtonCreate.EDIT_RESULT);
                if(strResult != "") {
                    noOfButtonsCreated++;
                    buttonFolderName.add(strResult);
                    createButton(strResult);
                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                //Toast.makeText(getApplicationContext(),"result NOT", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createButton(String buttonName) {

        TableLayout table = (TableLayout)findViewById(R.id.tableForButtons);
        //for(int i=0;i<3;i++);
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            //for (int j = 0; j < 3; j++) ;
            {
                final Button button = new Button(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                tableRow.addView(button);


                button.setText(buttonName);
                button.setTextColor(Color.parseColor("#ffffff"));
                button.setBackgroundColor(Color.parseColor("#808000"));
                button.setTextSize(20);
                button.setTypeface(tf2);

                /*Increase the count of the buttons created and write into file*/
                try {
                    FileOutputStream fileout=openFileOutput(buttonCountFile, MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(noOfButtonsCreated);
                    outputWriter.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                /*Write the button names into a file*/
                try {

                    FileOutputStream fileout1 = openFileOutput(buttonNameFile, MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout1);
                    for (int i = 0; i < buttonFolderName.size(); i++) {
                        outputWriter.write(buttonFolderName.get(i));
                        outputWriter.write("\n");
                    }
                    outputWriter.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      gridButtonClicked(button);
                  }
              });
            }
        }
    }

    private void gridButtonClicked(Button button) {

        String folderName = button.getText().toString();
        Intent intent = new Intent(this, buttonFolder.class);
        intent.putExtra(buttonFolder.EXTRA_MESSAGE, folderName);
        startActivityForResult(intent, BUTTON_FOLDER);
    }
}
