package fr.esiea.inf4042.projet.inf4042_hayotte_cosson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView test1 = (TextView)findViewById(R.id.welcomeMsg);
        test1.setText("Yo!");

        Button but1 = (Button)findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast txt=Toast.makeText(getApplicationContext(),"J'ai cliqué",Toast.LENGTH_LONG);
                txt.show();
            }
        });

    }
}
