package natamobile.natamobile2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import natamobile.natamobile2.Entities.Observation;
import natamobile.natamobile2.Entities.Observationsession;
import natamobile.natamobile2.Entities.Useraccount;
import natamobile.natamobile2.RestCommunication.OnLoopjCompleted;
import natamobile.natamobile2.RestCommunication.UseraccountRest;


public class ObservationSessionActivity extends AppCompatActivity {
    private Observationsession session;
    public static final int NEW_OBSERVATION = 2;
    public static final int MODIFY_OBSERVATION = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_session);

        int duration = getIntent().getExtras().getInt("Duration");

        session = (Observationsession) getIntent().getSerializableExtra("Session");
        TextView txtUser = (TextView) findViewById(R.id.txtViewObservationSessionUser);
        txtUser.setText(String.format("observateur : %s %s",session.getIdUsr().getUsrFirstName(),session.getIdUsr().getUsrLastName()));
        txtUser.setText("nbObservations : "+session.getObservationList().size());
        startCountdown(duration);



        UseraccountRest.getImage(UseraccountRest.URL_BIRD,"pigeon.jpg",new OnLoopjCompleted() {

            @Override
            public void taskCompleted(String results) {
                loadImg(results);
            }
        });
    }
    private void startCountdown(int minutes){
        final TextView timer = (TextView) findViewById(R.id.textViewRemainingTime);
        new CountDownTimer(minutes*60*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.format("temps restant : %d:%d",millisUntilFinished/1000/60,(millisUntilFinished / 1000)%60));
            }
            public void onFinish() {
                timer.setText("done!");
            }
        }.start();
    }
    private void loadImg(String url){
        ImageView img= (ImageView)findViewById(R.id.ImageViewBird);
        img.setAdjustViewBounds(true);
        Picasso.with(this).load(url).into(img);
    }

    public void addObservation(View view) {
        Intent intent = new Intent(getApplicationContext(), ObservationActivity.class);
        intent.putExtra("Session",session);
        startActivityForResult(intent,this.NEW_OBSERVATION);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == this.NEW_OBSERVATION) {

            if (resultCode == RESULT_OK) {
                Observation observation = (Observation) data.getSerializableExtra("Observation");
                observation.setIdObsSession(session);
                session.getObservationList().add(observation);
                TextView txtUser = (TextView) findViewById(R.id.txtViewObservationSessionUser);
                txtUser.setText("nbObservations : "+session.getObservationList().size());

            } else if (resultCode == RESULT_CANCELED) {
                // some stuff that will happen if there's no result
            }
        }
    }
}
