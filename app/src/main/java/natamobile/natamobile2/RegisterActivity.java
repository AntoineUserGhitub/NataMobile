package natamobile.natamobile2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import natamobile.natamobile2.Entities.Useraccount;
import natamobile.natamobile2.RestCommunication.OnLoopjCompleted;
import natamobile.natamobile2.RestCommunication.UseraccountRest;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pd = new ProgressDialog(this);
        Button button = (Button) findViewById(R.id.buttonRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryRegister();
            }

        });
    }
    private void tryRegister(){
         if(checkFields()){
            pd = pd.show(this, "Création du compte", "Création du compte en cours...");
            putUser();
        }
    }
    private boolean checkFields(){
        boolean valid =true;
        String emailMatcher ="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        EditText email = (EditText) findViewById(R.id.emailRegister);
        if(email.getText().length()<1){
            email.setError("L'email est requis");
            valid =false;
        }
        else if (!email.getText().toString().matches(emailMatcher)){
            email.setError("L'email n'est pas valide");
            valid =false;
        }
        EditText firstName = (EditText) findViewById(R.id.fNameRegister);
        if(firstName.getText().length()<1){
            firstName.setError("Le prénom est requis");
            valid =false;
        }
        EditText lastName = (EditText) findViewById(R.id.lNameRegister);
        if(lastName.getText().length()<1){
            lastName.setError("Le Nom est requis");
            valid =false;
        }
        EditText password = (EditText) findViewById(R.id.passwordRegister);
        EditText password2 = (EditText) findViewById(R.id.password2Register);
        if(password.getText().length()<1){
            password.setError("Le mot de passe est requis");
            valid =false;
        }else if(password.getText().length()<8){
            password.setError("Le mot de passe doit faire au moins 8 charactères");
            valid =false;
        }
        else if(password2.getText().length()<1){
            password2.setError("Veuillez confirmer le mot de passe");
            valid =false;
        }
        else if(!password.getText().toString().equals(password2.getText().toString())){
            password.setError("Les mots de passe ne correspondent pas");
            valid =false;
        }
        return valid;

    }
    private void fillUser(Useraccount account){
        EditText email = (EditText) findViewById(R.id.emailRegister);
        EditText firstName = (EditText) findViewById(R.id.fNameRegister);
        EditText lastName = (EditText) findViewById(R.id.lNameRegister);
        EditText password = (EditText) findViewById(R.id.passwordRegister);
        account.setId(0);
        account.setUsrEmail(email.getText().toString());
        account.setUsrFirstName(firstName.getText().toString());
        account.setUsrLastName(lastName.getText().toString());
        account.setUsrPasswords(password.getText().toString());

    }
    private void putUser(){
        Useraccount useraccount = new Useraccount();
        fillUser(useraccount);
        UseraccountRest rest = new UseraccountRest();
        OnLoopjCompleted oljc = new OnLoopjCompleted() {
            @Override
            public void taskCompleted(String results) {
                if(results.equals("success")){
                    pd.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(), "Le compte a bien été créé.", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }else if(results.equals("406")){
                    pd.dismiss();
                    EditText email = (EditText) findViewById(R.id.emailRegister);
                    email.setError("L'email est déja utilisé par un autre compte");
                    Toast toast = Toast.makeText(getApplicationContext(), "L'email existe déja.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    pd.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur lors d'envoi des données. Veuillez réessayeer plus tard.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
        rest.putMember(useraccount,oljc);
    }
}
