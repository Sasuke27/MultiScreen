package khentarjiel.cabural.com.example.khent.multiappscreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MultiAppScreen_MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FUNCTION = 100;

    Button send, cancel;
    EditText message;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_app_screen__main);

        send = (Button) findViewById(R.id.Sendbtn);
        cancel = (Button) findViewById(R.id.Cancelbtn);
        message = (EditText) findViewById(R.id.SendMessage);
        textView = (TextView) findViewById(R.id.textView);

    }

    public void click_btn(View view) {

        String button_text;
        button_text = ((Button) view).getText().toString();

        if (button_text.equals("Send")) {
            Intent intent = new Intent(MultiAppScreen_MainActivity.this, SecondActivity.class);
            intent.putExtra("com.example.khent.quiz.SecondActivity", message.getText().toString());
           // startActivity(intent);
            startActivityForResult(intent,REQUEST_CODE_FUNCTION);
            message.setText("");
        } else if (button_text.equals("Cancel")){
            if(!message.toString().isEmpty()) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert
                        .setTitle("Cancel Message")
                        .setMessage("Do you want to cancel your message?")
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        message.setText("");
                                        Toast.makeText(MultiAppScreen_MainActivity.this, "Cancel Message", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("No", null);
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }else {
                Toast.makeText(this, "No message at all", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_FUNCTION){
            if(resultCode == RESULT_OK){
                String Data = data.getStringExtra("SendAngMessageOy");
                    textView.setText(Data);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

