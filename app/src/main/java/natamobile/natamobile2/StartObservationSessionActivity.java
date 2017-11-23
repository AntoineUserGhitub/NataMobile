package natamobile.natamobile2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import natamobile.natamobile2.Entities.Observationsession;
import natamobile.natamobile2.Entities.Useraccount;

public class StartObservationSessionActivity extends AppCompatActivity {
    private Useraccount user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_observation_session);
        user=(Useraccount)getIntent().getSerializableExtra("User");
        Button button = (Button) findViewById(R.id.buttonStartObsSession);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSession();
            }
        });
    }
    private void startSession(){
        EditText duration = (EditText) findViewById(R.id.editTeextDuration);
        int i;
        if (duration.getText().length()<1)i=60;
        else i = Integer.parseInt(duration.getText().toString());
        if(i>120||i<30){
            duration.setError("la durée doit être de minimum 30 min et maximum 120 min");
        }else{
            goToObservationSessionActivity(i);
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter l'appli")
                .setMessage("Voulez-vous vraiment quitter l'application?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                        finish();
                    }

                }).create().show();
    }
    private void  goToObservationSessionActivity(int duration){
        Intent intent = new Intent(getApplicationContext(), ObservationSessionActivity.class);
        intent.putExtra("Duration", duration);
        Observationsession session=new Observationsession();
        session.setIdUsr(user);
        intent.putExtra("Session", session);
        startActivity(intent);
    }

}
