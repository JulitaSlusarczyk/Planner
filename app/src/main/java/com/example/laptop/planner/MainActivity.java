package com.example.laptop.planner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private Button button, button2, button3, button4, close, close2, close3, close4, close5;
    private ImageView imgPicture, imgPicture2,imgPicture3, imgPicture4,imgPicture5;
    private TextView textView, textView7;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    public int imd_id, day=1;
    List<String> lista = new ArrayList<>(), list = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>(), list4 = new ArrayList<>(), list5 = new ArrayList<>(), listpn = new ArrayList<>(), listwt = new ArrayList<>(), listsr = new ArrayList<>(), listczw = new ArrayList<>(), listpt = new ArrayList<>(), dodatkowa = new ArrayList<>();
    private boolean isset = false;
    public File myFile;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }
        else
        {
            zezwolono();
        }

    }

    public void puste()
    {
        imgPicture.setImageResource(R.drawable.ic_like);
        imgPicture2.setImageResource(R.drawable.ic_like);
        imgPicture3.setImageResource(R.drawable.ic_like);
        imgPicture4.setImageResource(R.drawable.ic_like);
        imgPicture5.setImageResource(R.drawable.ic_like);
    }

    public void zezwolono()
    {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView);
        textView7 = (TextView) findViewById(R.id.textView7);

        imgPicture = (ImageView) findViewById(R.id.imageView);
        imgPicture2 = (ImageView) findViewById(R.id.imageView2);
        imgPicture3 = (ImageView) findViewById(R.id.imageView3);
        imgPicture4 = (ImageView) findViewById(R.id.imageView4);
        imgPicture5 = (ImageView) findViewById(R.id.imageView5);

        close = (Button) findViewById(R.id.button5);
        close2 = (Button) findViewById(R.id.button6);
        close3 = (Button) findViewById(R.id.button7);
        close4 = (Button) findViewById(R.id.button8);
        close5 = (Button) findViewById(R.id.button9);

        myFile = new File("/sdcard/mysdfile.txt");

        zapis();
        zaladuj();
    }

    public void zaladuj()
    {
        try
        {
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String Row = "";
            while ((Row = myReader.readLine()) != null)
            {
                lista.add(Row.replace("\n", ""));
            }
            myReader.close();
            fIn.close();

            for(int pn=0;pn<5; pn++)
            {
                listpn.add(lista.get(pn));
            }
            for(int wt=5;wt<10; wt++)
            {
                listwt.add(lista.get(wt));
            }
            for(int sr=10;sr<15; sr++)
            {
                listsr.add(lista.get(sr));
            }
            for(int czw=15;czw<20; czw++)
            {
                listczw.add(lista.get(czw));
            }
            for(int pt=20;pt<25; pt++)
            {
                listpt.add(lista.get(pt));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void zapis()
    {
        try {
            if(!myFile.exists())
            {
                myFile.createNewFile();
                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                for (int i = 0; i < 25; i++) {
                    myOutWriter.append("None").append("\n");
                }
                myOutWriter.close();
                fOut.close();
                Toast.makeText(this, "Zapisano do plikuuuuu", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void zapis2(View v)
    {
        lista.clear();
        wypelnij2();
        try
        {
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            for(int i=0; i<lista.size();i++)
            {
                String li = lista.get(i);
                myOutWriter.append(li).append("\n");
            }
            myOutWriter.close();
            fOut.close();
            Toast.makeText(this,"Zapisano do pliku", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void wypelnij2()
    {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        if(radioButton.getId() == R.id.pon)
        {
            wypelnij();
            listpn.clear();
            for (int i = 0; i < 5; i++)
            {
                listpn.add(dodatkowa.get(i));
            }
        }
        else
        {
            for (int pn = 0; pn < 5; pn++)
            {
                lista.add(listpn.get(pn));
            }
        }
        //.....................................
        if(radioButton.getId() == R.id.wt)
        {
            wypelnij();
            listwt.clear();
            for (int i = 0; i < 5; i++)
            {
                listwt.add(dodatkowa.get(i));
            }
        }
        else
        {
            for (int wt = 0; wt < 5; wt++)
            {
                lista.add(listwt.get(wt));
            }
        }
        //.....................................
        if(radioButton.getId() == R.id.sr)
        {
            wypelnij();
            listsr.clear();
            for (int i = 0; i < 5; i++)
            {
                listsr.add(dodatkowa.get(i));
            }
        }
        else
        {
            for (int sr = 0; sr < 5; sr++)
            {
                lista.add(listsr.get(sr));
            }
        }
        //.....................................
        if(radioButton.getId() == R.id.czw)
        {
            wypelnij();
            listczw.clear();
            for (int i = 0; i < 5; i++)
            {
                listczw.add(dodatkowa.get(i));
            }
        }
        else
        {
            for (int czw = 0; czw < 5; czw++)
            {
                lista.add(listczw.get(czw));
            }
        }
        //.....................................
        if(radioButton.getId() == R.id.pt)
        {
            wypelnij();
            listpt.clear();
            for (int i = 0; i < 5; i++)
            {
                listpt.add(dodatkowa.get(i));
            }
        }
        else
        {
            for (int pt = 0; pt < 5; pt++)
            {
                lista.add(listpt.get(pt));
            }
        }

    }

    public void wypelnij()
    {
        dodatkowa.clear();
        if(list.size()>=1)
        {
            lista.add(list.get(list.size()-1));
            dodatkowa.add(list.get(list.size()-1));
        }
        else
        {
            lista.add("None");
            dodatkowa.add("None");
        }
        //............................
        if(list2.size()>=1)
        {
            lista.add(list2.get(list2.size()-1));
            dodatkowa.add(list2.get(list2.size()-1));
        }
        else
        {
            lista.add("None");
            dodatkowa.add("None");
        }
        //............................
        if(list3.size()>=1)
        {
            lista.add(list3.get(list3.size()-1));
            dodatkowa.add(list3.get(list3.size()-1));
        }
        else
        {
            lista.add("None");
            dodatkowa.add("None");
        }
        //............................
        if(list4.size()>=1)
        {
            lista.add(list4.get(list4.size()-1));
            dodatkowa.add(list4.get(list4.size()-1));
        }
        else
        {
            lista.add("None");
            dodatkowa.add("None");
        }
        //............................
        if(list5.size()>=1)
        {
            lista.add(list5.get(list5.size()-1));
            dodatkowa.add(list5.get(list5.size()-1));
        }
        else
        {
            lista.add("None");
            dodatkowa.add("None");
        }
    }

    public void next(View v)
    {
        day++;
        if(day==6)
            day=1;
        switch(day)
        {
            case 1:poniedzialek();break;
            case 2:wtorek();break;
            case 3:sroda();break;
            case 4:czwartek();break;
            case 5:piatek();break;
        }
    }

    public void previous(View v)
    {
        day--;
        if(day==0)
            day=5;
        switch(day)
        {
            case 1:poniedzialek();break;
            case 2:wtorek();break;
            case 3:sroda();break;
            case 4:czwartek();break;
            case 5:piatek();break;
        }
    }

    public void poniedzialek()
    {
        String[] projection = {MediaStore.MediaColumns.DATA};
        textView7.setText("Poniedziałek");
        for(int a=0;a<5;a++)
        {
            String aab = lista.get(a);
            Uri damn = Uri.parse(aab);
            Cursor cur = getContentResolver().query(damn, projection, null, null, null);
            if (cur != null) {
                switch(a)
                {
                    case 0: InputStream inputStream;

                        try
                        {
                            inputStream = getContentResolver().openInputStream(Uri.parse(lista.get(a)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imgPicture.setImageURI(Uri.parse(lista.get(a)));
                        break;
                    case 1: InputStream inputStream2;

                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(Uri.parse(lista.get(a)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2: InputStream inputStream3;

                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(Uri.parse(lista.get(a)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3: InputStream inputStream4;

                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(Uri.parse(lista.get(a)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4: InputStream inputStream5;

                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(Uri.parse(lista.get(a)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(a)
                {
                    case 0: imgPicture.setImageResource(R.drawable.ic_like);break;
                    case 1: imgPicture2.setImageResource(R.drawable.ic_like);break;
                    case 2: imgPicture3.setImageResource(R.drawable.ic_like);break;
                    case 3: imgPicture4.setImageResource(R.drawable.ic_like);break;
                    case 4: imgPicture5.setImageResource(R.drawable.ic_like);break;
                }
            }
        }
    }

    public void wtorek()
    {
        String[] projection = {MediaStore.MediaColumns.DATA};
        textView7.setText("Wtorek");
        for(int b=5;b<10;b++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(b)), projection, null, null, null);
            if (cur != null) {
                switch(b)
                {
                    case 5: InputStream inputStream;

                        try
                        {
                            inputStream = getContentResolver().openInputStream(Uri.parse(lista.get(b)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imgPicture.setImageURI(Uri.parse(lista.get(a)));
                        break;
                    case 6: InputStream inputStream2;

                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(Uri.parse(lista.get(b)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7: InputStream inputStream3;

                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(Uri.parse(lista.get(b)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 8: InputStream inputStream4;

                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(Uri.parse(lista.get(b)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 9: InputStream inputStream5;

                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(Uri.parse(lista.get(b)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(b)
                {
                    case 5: imgPicture.setImageResource(R.drawable.ic_like);break;
                    case 6: imgPicture2.setImageResource(R.drawable.ic_like);break;
                    case 7: imgPicture3.setImageResource(R.drawable.ic_like);break;
                    case 8: imgPicture4.setImageResource(R.drawable.ic_like);break;
                    case 9: imgPicture5.setImageResource(R.drawable.ic_like);break;
                }
            }
        }
    }

    public void sroda()
    {
        String[] projection = {MediaStore.MediaColumns.DATA};
        textView7.setText("Środa");
        for(int c=10;c<15;c++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(c)), projection, null, null, null);
            if (cur != null) {
                switch(c)
                {
                    case 10: InputStream inputStream;

                        try
                        {
                            inputStream = getContentResolver().openInputStream(Uri.parse(lista.get(c)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imgPicture.setImageURI(Uri.parse(lista.get(a)));
                        break;
                    case 11: InputStream inputStream2;

                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(Uri.parse(lista.get(c)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 12: InputStream inputStream3;

                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(Uri.parse(lista.get(c)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 13: InputStream inputStream4;

                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(Uri.parse(lista.get(c)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 14: InputStream inputStream5;

                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(Uri.parse(lista.get(c)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(c)
                {
                    case 10: imgPicture.setImageResource(R.drawable.ic_like);break;
                    case 11: imgPicture2.setImageResource(R.drawable.ic_like);break;
                    case 12: imgPicture3.setImageResource(R.drawable.ic_like);break;
                    case 13: imgPicture4.setImageResource(R.drawable.ic_like);break;
                    case 14: imgPicture5.setImageResource(R.drawable.ic_like);break;
                }
            }
        }
    }

    public void czwartek()
    {
        String[] projection = {MediaStore.MediaColumns.DATA};
        textView7.setText("Czwartek");
        for(int d=15;d<20;d++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(d)), projection, null, null, null);
            if (cur != null) {
                switch(d)
                {
                    case 15: InputStream inputStream;

                        try
                        {
                            inputStream = getContentResolver().openInputStream(Uri.parse(lista.get(d)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imgPicture.setImageURI(Uri.parse(lista.get(a)));
                        break;
                    case 16: InputStream inputStream2;

                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(Uri.parse(lista.get(d)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 17: InputStream inputStream3;

                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(Uri.parse(lista.get(d)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 18: InputStream inputStream4;

                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(Uri.parse(lista.get(d)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 19: InputStream inputStream5;

                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(Uri.parse(lista.get(d)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(d)
                {
                    case 15: imgPicture.setImageResource(R.drawable.ic_like);break;
                    case 16: imgPicture2.setImageResource(R.drawable.ic_like);break;
                    case 17: imgPicture3.setImageResource(R.drawable.ic_like);break;
                    case 18: imgPicture4.setImageResource(R.drawable.ic_like);break;
                    case 19: imgPicture5.setImageResource(R.drawable.ic_like);break;
                }
            }
        }
    }

    public void piatek()
    {
        String[] projection = {MediaStore.MediaColumns.DATA};
        textView7.setText("Piątek");
        for(int f=20;f<25;f++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(f)), projection, null, null, null);
            if (cur != null) {
                switch(f)
                {
                    case 20: InputStream inputStream;

                        try
                        {
                            inputStream = getContentResolver().openInputStream(Uri.parse(lista.get(f)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //imgPicture.setImageURI(Uri.parse(lista.get(a)));
                        break;
                    case 21: InputStream inputStream2;

                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(Uri.parse(lista.get(f)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 22: InputStream inputStream3;

                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(Uri.parse(lista.get(f)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 23: InputStream inputStream4;

                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(Uri.parse(lista.get(f)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 24: InputStream inputStream5;

                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(Uri.parse(lista.get(f)));
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(f)
                {
                    case 20: imgPicture.setImageResource(R.drawable.ic_like);break;
                    case 21: imgPicture2.setImageResource(R.drawable.ic_like);break;
                    case 22: imgPicture3.setImageResource(R.drawable.ic_like);break;
                    case 23: imgPicture4.setImageResource(R.drawable.ic_like);break;
                    case 24: imgPicture5.setImageResource(R.drawable.ic_like);break;
                }
            }
        }
    }

    public void plan(View v)
    {
        if(!isset)
        {
            button2.setText("Wróć");
            isset = true;
            button.setVisibility(View.GONE);
            radioGroup.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            textView7.setVisibility(View.VISIBLE);
            textView7.setText("Poniedziałek");
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            poniedzialek();

        }
        else
        {
            day=1;
            button2.setText("Plan");
            isset = false;
            button.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            puste();
        }
    }

    public void x1(View v)
    {
        list.add("None");
        imgPicture.setImageResource(R.drawable.ic_like);
        close.setVisibility(View.GONE);
    }

    public void x2(View v)
    {
        list2.add("None");
        imgPicture2.setImageResource(R.drawable.ic_like);
        close2.setVisibility(View.GONE);
    }

    public void x3(View v)
    {
        list3.add("None");
        imgPicture3.setImageResource(R.drawable.ic_like);
        close3.setVisibility(View.GONE);
    }

    public void x4(View v)
    {
        list4.add("None");
        imgPicture4.setImageResource(R.drawable.ic_like);
        close4.setVisibility(View.GONE);
    }

    public void x5(View v)
    {
        list5.add("None");
        imgPicture5.setImageResource(R.drawable.ic_like);
        close5.setVisibility(View.GONE);
    }

    public void onClickGallery(View v)
    {
        imd_id = v.getId();
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(resultCode == RESULT_OK)
        {
            if(requestCode == IMAGE_GALLERY_REQUEST)
            {
                switch (imd_id)
                {
                    case R.id.imageView:
                        Uri imageUri = data.getData();
                        InputStream inputStream;
                        try
                        {
                            inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap img = BitmapFactory.decodeStream(inputStream);
                            img = Bitmap.createScaledBitmap(img,imgPicture.getWidth(),imgPicture.getHeight(),true);
                            imgPicture.setImageBitmap(img);
                            close.setVisibility(View.VISIBLE);
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }

                        list.add(imageUri.toString());
                        Toast.makeText(this, imageUri.toString(), Toast.LENGTH_LONG).show();
                    break;
                    case R.id.imageView2:
                        Uri imageUri2 = data.getData();
                        InputStream inputStream2;
                        try
                        {
                            inputStream2 = getContentResolver().openInputStream(imageUri2);
                            Bitmap img = BitmapFactory.decodeStream(inputStream2);
                            img = Bitmap.createScaledBitmap(img,imgPicture2.getWidth(),imgPicture2.getHeight(),true);
                            imgPicture2.setImageBitmap(img);
                            close2.setVisibility(View.VISIBLE);
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }


                        list2.add(imageUri2.toString());
                        Toast.makeText(this, imageUri2.toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imageView3:
                        Uri imageUri3 = data.getData();
                        InputStream inputStream3;
                        try
                        {
                            inputStream3 = getContentResolver().openInputStream(imageUri3);
                            Bitmap img = BitmapFactory.decodeStream(inputStream3);
                            img = Bitmap.createScaledBitmap(img,imgPicture3.getWidth(),imgPicture3.getHeight(),true);
                            imgPicture3.setImageBitmap(img);
                            close3.setVisibility(View.VISIBLE);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        list3.add(imageUri3.toString());
                        break;
                    case R.id.imageView4:
                        Uri imageUri4 = data.getData();
                        InputStream inputStream4;
                        try
                        {
                            inputStream4 = getContentResolver().openInputStream(imageUri4);
                            Bitmap img = BitmapFactory.decodeStream(inputStream4);
                            img = Bitmap.createScaledBitmap(img,imgPicture4.getWidth(),imgPicture4.getHeight(),true);
                            imgPicture4.setImageBitmap(img);
                            close4.setVisibility(View.VISIBLE);
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }

                        list4.add(imageUri4.toString());
                        break;
                    case R.id.imageView5:
                        Uri imageUri5 = data.getData();
                        InputStream inputStream5;
                        try
                        {
                            inputStream5 = getContentResolver().openInputStream(imageUri5);
                            Bitmap img = BitmapFactory.decodeStream(inputStream5);
                            img = Bitmap.createScaledBitmap(img,imgPicture5.getWidth(),imgPicture5.getHeight(),true);
                            imgPicture5.setImageBitmap(img);
                            close5.setVisibility(View.VISIBLE);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        list5.add(imageUri5.toString());
                        break;
                    default:
                        throw new RuntimeException("Unknow button ID");
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1024:
                {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    zezwolono();
                }
                else
                {
                    Toast.makeText(this,"Zapis do pliku wymagany. Uruchom ponownie aplikację.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }
}
