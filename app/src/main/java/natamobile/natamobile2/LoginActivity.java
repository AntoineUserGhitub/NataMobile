package natamobile.natamobile2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import natamobile.natamobile2.Entities.Useraccount;
import natamobile.natamobile2.Models.UseraccountCredentials;
import natamobile.natamobile2.RestCommunication.OnLoopjCompleted;
import natamobile.natamobile2.RestCommunication.UseraccountRest;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog pd;
    private UseraccountCredentials credentials;
    private Useraccount user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        credentials = null;

        pd = new ProgressDialog(this);

        Button buttonConnection = (Button) findViewById(R.id.buttonConnection);
        buttonConnection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tryConnection();
            }
        });
        Button buttonGoToRegister = (Button) findViewById(R.id.buttonGoToRegister);
        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                goToRegisterActivity();
            }
        });
    }

    private void tryConnection() {
        if(checkFields()){
            pd = pd.show(this, "Connection", "Veuillez patienter pendant la connection...");
            credentials = new UseraccountCredentials();
            EditText pseudo = (EditText) findViewById(R.id.pseudo);
            EditText mdp = (EditText) findViewById(R.id.password);
            credentials.setEmail(pseudo.getText().toString());
            credentials.setEmail("rogirst@gmail.com");
            credentials.setPassword(mdp.getText().toString());


            OnLoopjCompleted oljc = new OnLoopjCompleted() {
                @Override
                public void taskCompleted(String results) {
                    if(results==null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Erreur de communication avec le serveur.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (results.equals("404")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connection, vérifiez votre mdp et votre pseudo.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if (results != null) {
                        checkIfMemberIsOk(results);
                    }

                    pd.dismiss();

                }
            };
            UseraccountRest.getMember(credentials, oljc);
        }

    }

    private void checkIfMemberIsOk(String results) {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<Useraccount>() {
        }.getType();
        user = gson.fromJson(results, listType);
        if (user.getUsrEmail() != null || user.getUsrPasswords() != null) {
            goToStartObservationSessionActivity();
            Toast toast = Toast.makeText(getApplicationContext(), "Connection réussie.", Toast.LENGTH_SHORT);
            toast.show();
            finish();

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Erreur de connection, vérifiez votre mdp et votre pseudo.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void  goToStartObservationSessionActivity(){
        Intent intent = new Intent(getApplicationContext(), StartObservationSessionActivity.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }
    private void goToRegisterActivity(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
    private boolean checkFields(){
        Boolean valid =true;
        String emailMatcher ="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        //EditText email = (EditText) findViewById(R.id.pseudo);
        //if(email.getText().length()<1){
        //    email.setError("L'email est requis");
        //    valid =false;
        //}else if (!email.getText().toString().matches(emailMatcher)){
        //    email.setError("L'email n'est pas valide");
        //    valid =false;
        //}

        EditText mdp = (EditText) findViewById(R.id.password);
         if (mdp.getText().toString().length()<8){
            mdp.setError("le mot de passe fait au moins 8 charactères");
            valid =false;
        }

        return valid;
    }
}
