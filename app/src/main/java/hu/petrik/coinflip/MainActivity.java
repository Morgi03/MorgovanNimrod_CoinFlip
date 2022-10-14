package hu.petrik.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView coinImg;
    private Button fej;
    private Button iras;
    private TextView dobasok;
    private TextView gyozelem;
    private TextView vereseg;
    private Random rnd;
    private int fejViras;
    private int dobasokSzama;
    private int gyozelmekSzama;
    private int veresegekSzama;
    private int jatekHossza;
    private AlertDialog.Builder vege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jatek(0);
            }
        });
        iras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jatek(1);
            }
        });

    }


    private void Jatek(int tipp) {
        if (jatekHossza < 4) {
            fejViras = rnd.nextInt(2);
            if (fejViras == 0) {
                coinImg.setImageResource(R.drawable.heads);
                Toast.makeText(this, "A dobás eredméyne fej", Toast.LENGTH_SHORT).show();
            } else {
                coinImg.setImageResource(R.drawable.tails);
                Toast.makeText(this, "A dobás eredméyne írás", Toast.LENGTH_SHORT).show();
            }
            if (tipp == fejViras) {
                gyozelmekSzama++;
            } else {
                veresegekSzama++;
            }
            jatekHossza++;
            String jh = "Dobások: "+jatekHossza;
            dobasok.setText(jh);
            String gy = "Győzelem: "+gyozelmekSzama;
            gyozelem.setText(gy);
            String v = "Vereség: "+veresegekSzama;
            vereseg.setText(v);
        } else {
            if (gyozelmekSzama > veresegekSzama) {
                vege.setTitle("Győzelem");
                vege.show();
            } else {
                vege.setTitle("Vereség");
                vege.show();
            }
        }
    }


    private void ujJatek() {
        dobasokSzama = 0;
        gyozelmekSzama = 0;
        veresegekSzama = 0;
        jatekHossza = 0;
        coinImg.setImageResource(R.drawable.heads);
        String jh = "Dobások: "+jatekHossza;
        dobasok.setText(jh);
        String gy = "Győzelem: "+gyozelmekSzama;
        gyozelem.setText(gy);
        String v = "Vereség: "+veresegekSzama;
        vereseg.setText(v);
    }


    private void init() {
        coinImg = findViewById(R.id.coinImg);
        fej = findViewById(R.id.fej);
        iras = findViewById(R.id.iras);
        dobasok = findViewById(R.id.dobasok);
        gyozelem = findViewById(R.id.gyozelem);
        vereseg = findViewById(R.id.vereseg);
        rnd = new Random();
        dobasokSzama = 0;
        gyozelmekSzama = 0;
        veresegekSzama = 0;
        jatekHossza = 0;
        vege = new AlertDialog.Builder(this);
        vege.setCancelable(false);
        vege.setMessage("Szeretne új játékot játszani?");
        vege.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        vege.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        });
    }
}