package com.actosoftcommunity.dialogs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends FragmentActivity implements LoginDialogFragment.LoginDialogListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button BtnLogin, BtnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    Log.d("TAG", "onAuthStateChanged:signed_in:" +user.getUid());
                    Intent intento = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intento);
                }
                else
                {
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
            }
        };

        BtnLogin = (Button) findViewById(R.id.BtnLogin);
        BtnSignIn = (Button) findViewById(R.id.BtnSignIn);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginDialogFragment dialog = new LoginDialogFragment();
                dialog.show(getFragmentManager(),"LoginDialogManager");
            }
        });

        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(getApplicationContext(), SignIn.class);
                startActivity(registro);
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if(mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void check(String correo, String pass)
    {
       /* if(correo.equals("nitram-210397@hotmail.com") && pass.equals("cbtis2014$"))
        {
            Intent intento = new Intent(getApplicationContext(), PrincipalActivity.class);
            startActivity(intento);
        }*/

       mAuth.signInWithEmailAndPassword(correo, pass)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());
                       if(!task.isSuccessful())
                       {
                           Log.w("TAG", "signInWithEmail:failed", task.getException());
                           Toast.makeText(MainActivity.this, "Valio verga",
                                   Toast.LENGTH_SHORT).show();;
                       }
                   }

               });
    }
}
