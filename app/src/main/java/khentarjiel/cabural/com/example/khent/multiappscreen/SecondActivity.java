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

import static android.R.id.message;

public class SecondActivity extends AppCompatActivity {


    TextView textView;

    Button reply, cancel;
    EditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.textView);
        reply = (Button)findViewById(R.id.Sendbtn);
        cancel = (Button)findViewById(R.id.Cancelbtn);
        message = (EditText)findViewById(R.id.MessageReplyEditText);

        Intent intent = getIntent();
        String result_score = intent.getExtras().getString("com.example.khent.quiz.SecondActivity");
        textView.setText(result_score);
    }


    public void Reply_Btn(View view){
        String reply_btn;
        reply_btn =  ((Button)view).getText().toString();

        if (reply_btn.equals("Send")) {
            Intent intent = new Intent();
            intent.putExtra("SendAngMessageOy",message.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
            message.setText("");
        }else if (reply_btn.equals("Cancel")){
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
                                        Toast.makeText(SecondActivity.this, "Cancel Message", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("No", null);
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
            else {
                Toast.makeText(this, "No message at all", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
