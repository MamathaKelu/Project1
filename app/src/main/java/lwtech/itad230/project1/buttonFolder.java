package lwtech.itad230.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class buttonFolder extends AppCompatActivity {

    ImageButton cardCreateButton;
    public static final int EDIT_REQUEST = 1;
    public static final int VIEW_REQUEST = 2;
    public static final String EXTRA_MESSAGE = "message";


    ArrayList<String> questionArray = new ArrayList<String>();
    ArrayList<String> answerArray = new ArrayList<>();
    int count = 0;
    String cardFile = "";
    String folderName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_folder);

        Intent intent = getIntent();

        folderName = intent.getStringExtra(EXTRA_MESSAGE);

        readCardFromFile();

        cardCreateButton  = (ImageButton) findViewById(R.id.cardCreate);

        cardCreateButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {
               /*Toast.makeText(getApplicationContext(),"Card create button is clicked", Toast.LENGTH_SHORT).show();*/
               getTextOnCard("");
           }
       }
        );
    }

    private void readCardFromFile() {
        /*Read question from file*/
        try {
            cardFile = folderName+".txt";
            FileInputStream fileIn = openFileInput(cardFile);

            InputStreamReader inputStreamReader1 = new InputStreamReader(fileIn);
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
            String receiveString = "";
            while ( (receiveString = bufferedReader1.readLine()) != null ) {
                questionArray.add(receiveString);
                //Toast.makeText(getApplicationContext(),"READ "+receiveString, Toast.LENGTH_SHORT).show();
            }

            inputStreamReader1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        /*Read answer from file*/
        try {
            cardFile = folderName+"ans"+".txt";
            FileInputStream fileIn = openFileInput(cardFile);

            InputStreamReader inputStreamReader1 = new InputStreamReader(fileIn);
            BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
            String receiveString = "";
            while ( (receiveString = bufferedReader1.readLine()) != null ) {
                answerArray.add(receiveString);
                //Toast.makeText(getApplicationContext(),"READ "+receiveString, Toast.LENGTH_SHORT).show();
            }

            inputStreamReader1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTextOnCard(String s) {

        Intent intent = new Intent(this, TextOnCard.class);
        //intent.putExtra(ButtonCreate.EXTRA_MESSAGE, " hi ");
        //Log.d("BUTTON FOLDER","....CALL ACTIVITY....");

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
            // Make sure the request was successful
            //Log.d("TEXT ON CARD","....CODE....");
            if (resultCode == RESULT_OK) {
               // Log.d("TEXT ON CARD","....RESULT....");

               // String strResult = data.getStringExtra(TextOnCard.EDIT_RESULT);

                questionArray.add(data.getStringExtra(TextOnCard.QUESTION_CARD));
                count++;
                /*Write cards in  file*/
                try {
                    cardFile = folderName+".txt";
                    //Toast.makeText(getApplicationContext(), "WRITE1", Toast.LENGTH_SHORT).show();
                    FileOutputStream fileout2 = openFileOutput(cardFile, MODE_PRIVATE);
                    OutputStreamWriter outputWriter2 = new OutputStreamWriter(fileout2);
                    //Toast.makeText(getApplicationContext(), "C "+buttonFolderName.size()+" "+noOfButtonsCreated, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < questionArray.size(); i++) {
                        //Toast.makeText(getApplicationContext(), "W "+buttonFolderName.get(i), Toast.LENGTH_SHORT).show();
                        outputWriter2.write(questionArray.get(i));
                        outputWriter2.write("\n");
                    }
                    outputWriter2.close();
                    // Toast.makeText(getApplicationContext(), "WRITE2", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                answerArray.add(data.getStringExtra(TextOnCard.ANSWER_CARD));
                /*Write cards in  file*/
                try {
                    cardFile = folderName+"ans"+".txt";
                    //Toast.makeText(getApplicationContext(), "WRITE1", Toast.LENGTH_SHORT).show();
                    FileOutputStream fileout3 = openFileOutput(cardFile, MODE_PRIVATE);
                    OutputStreamWriter outputWriter3 = new OutputStreamWriter(fileout3);
                    //Toast.makeText(getApplicationContext(), "C "+buttonFolderName.size()+" "+noOfButtonsCreated, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < answerArray.size(); i++) {
                       // Toast.makeText(getApplicationContext(), "Ans "+answerArray.get(i), Toast.LENGTH_SHORT).show();
                        outputWriter3.write(answerArray.get(i));
                        outputWriter3.write("\n");
                    }
                    outputWriter3.close();
                    // Toast.makeText(getApplicationContext(), "WRITE2", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
    protected void viewCardsOnButtonClick(View view)
    {
        Intent intent = new Intent(this, ViewCards.class);

        //ArrayList<String> tempCardArray = new ArrayList<>();

       // Toast.makeText(getApplicationContext(), "View Button", Toast.LENGTH_SHORT).show();

        intent.putStringArrayListExtra("Question",questionArray);
        intent.putStringArrayListExtra("Answer",answerArray);

        startActivityForResult(intent, VIEW_REQUEST);
    }
}
