package com.example.laptop.planner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private Button button, button2, button3, button4;
    private ImageView imgPicture, imgPicture2,imgPicture3, imgPicture4,imgPicture5;
    private TextView textView, textView7;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    public int imd_id, day=1;
    List<String> lista, list, list2, list3, list4, list5;
    List<String> listpn, listwt, listsr, listczw, listpt;
    private boolean isset = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }
        else {}
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

        lista = new ArrayList<>();

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();

        listpn = new ArrayList<>();
        listwt = new ArrayList<>();
        listsr = new ArrayList<>();
        listczw = new ArrayList<>();
        listpt = new ArrayList<>();

        zaladuj();
    }

    public void zaladuj()
    {
        try
        {
            File myFile = new File("/sdcard/mysdfile.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String Row = "";
            while ((Row = myReader.readLine()) != null)
            {
                lista.add(Row);
            }
            myReader.close();

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

    public void zapis2(View v)
    {
        lista.clear();
        wypelnij2();
        try {
            File myFile = new File("/sdcard/mysdfile.txt");
            if(!myFile.exists())
                myFile.createNewFile();
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
        }
        else
        {
            for (int pt = 0; pt < 5; pt++)
            {
                lista.add(listpt.get(pt));
            }
        }

    }

    public void odczyt(View v)
    {
        try
        {
            File myFile2 = new File("/sdcard/mysdfile.txt");
            FileInputStream fIn = new FileInputStream(myFile2);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null)
            {
                lista.add(aDataRow);
            }
            myReader.close();
            textView = (TextView) findViewById(R.id.textView);
            textView.setText(lista.get(0));
            Toast.makeText(v.getContext(),"Done reading SD 'mysdfile.txt'",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(v.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cur = getContentResolver().query(Uri.parse(lista.get(0)), projection, null, null, null);
        if (cur != null)
        {
            imgPicture.setImageURI(Uri.parse(lista.get(0)));
            Toast.makeText(this, list.get(0), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Istnijee", Toast.LENGTH_LONG).show();
        }
        else
        {
            //gdy nie znajdzie zdjęcia lub jest zapisane jako None
            Toast.makeText(this,"None",Toast.LENGTH_SHORT).show();
            imgPicture.setImageResource(R.drawable.ic_like);
        }

    }

    public void wypelnij()
    {
        if(list.size()>=1)
        {
            lista.add(list.get(list.size()-1));
        }
        else
        {
            lista.add("None");
        }
        //............................
        if(list2.size()>=1)
        {
            lista.add(list2.get(list2.size()-1));
        }
        else
        {
            lista.add("None");
        }
        //............................
        if(list3.size()>=1)
        {
            lista.add(list3.get(list3.size()-1));
        }
        else
        {
            lista.add("None");
        }
        //............................
        if(list4.size()>=1)
        {
            lista.add(list4.get(list4.size()-1));
        }
        else
        {
            lista.add("None");
        }
        //............................
        if(list5.size()>=1)
        {
            lista.add(list5.get(list5.size()-1));
        }
        else
        {
            lista.add("None");
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
        for(int a=0;a<5;a++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(a)), projection, null, null, null);
            if (cur != null) {
                switch(a)
                {
                    case 0: imgPicture.setImageURI(Uri.parse(lista.get(a)));break;
                    case 1: imgPicture2.setImageURI(Uri.parse(lista.get(a)));break;
                    case 2: imgPicture3.setImageURI(Uri.parse(lista.get(a)));break;
                    case 3: imgPicture4.setImageURI(Uri.parse(lista.get(a)));break;
                    case 4: imgPicture5.setImageURI(Uri.parse(lista.get(a)));break;
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
        for(int b=5;b<10;b++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(b)), projection, null, null, null);
            if (cur != null) {
                switch(b)
                {
                    case 5: imgPicture.setImageURI(Uri.parse(lista.get(b)));break;
                    case 6: imgPicture2.setImageURI(Uri.parse(lista.get(b)));break;
                    case 7: imgPicture3.setImageURI(Uri.parse(lista.get(b)));break;
                    case 8: imgPicture4.setImageURI(Uri.parse(lista.get(b)));break;
                    case 9: imgPicture5.setImageURI(Uri.parse(lista.get(b)));break;
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
        for(int c=10;c<15;c++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(c)), projection, null, null, null);
            if (cur != null) {
                switch(c)
                {
                    case 10: imgPicture.setImageURI(Uri.parse(lista.get(c)));break;
                    case 11: imgPicture2.setImageURI(Uri.parse(lista.get(c)));break;
                    case 12: imgPicture3.setImageURI(Uri.parse(lista.get(c)));break;
                    case 13: imgPicture4.setImageURI(Uri.parse(lista.get(c)));break;
                    case 14: imgPicture5.setImageURI(Uri.parse(lista.get(c)));break;
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
        for(int d=15;d<20;d++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(d)), projection, null, null, null);
            if (cur != null) {
                switch(d)
                {
                    case 15: imgPicture.setImageURI(Uri.parse(lista.get(d)));break;
                    case 16: imgPicture2.setImageURI(Uri.parse(lista.get(d)));break;
                    case 17: imgPicture3.setImageURI(Uri.parse(lista.get(d)));break;
                    case 18: imgPicture4.setImageURI(Uri.parse(lista.get(d)));break;
                    case 19: imgPicture5.setImageURI(Uri.parse(lista.get(d)));break;
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
        for(int e=20;e<25;e++)
        {
            Cursor cur = getContentResolver().query(Uri.parse(lista.get(e)), projection, null, null, null);
            if (cur != null) {
                switch(e)
                {
                    case 20: imgPicture.setImageURI(Uri.parse(lista.get(e)));break;
                    case 21: imgPicture2.setImageURI(Uri.parse(lista.get(e)));break;
                    case 22: imgPicture3.setImageURI(Uri.parse(lista.get(e)));break;
                    case 23: imgPicture4.setImageURI(Uri.parse(lista.get(e)));break;
                    case 24: imgPicture5.setImageURI(Uri.parse(lista.get(e)));break;
                }
            } else {
                //gdy nie znajdzie zdjęcia lub jest zapisane jako None
                switch(e)
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
        }
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
                        imgPicture.setImageURI(imageUri);

                        list.add(imageUri.toString());
                        Toast.makeText(this, imageUri.getPath().toString(), Toast.LENGTH_LONG).show();
                    break;
                    case R.id.imageView2:
                        Uri imageUri2 = data.getData();
                        imgPicture2.setImageURI(imageUri2);

                        list2.add(imageUri2.toString());
                        Toast.makeText(this, imageUri2.toString(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imageView3:
                        Uri imageUri3 = data.getData();
                        imgPicture3.setImageURI(imageUri3);

                        list3.add(imageUri3.getPath().toString());
                        break;
                    case R.id.imageView4:
                        Uri imageUri4 = data.getData();
                        imgPicture4.setImageURI(imageUri4);

                        list4.add(imageUri4.getPath().toString());
                        break;
                    case R.id.imageView5:
                        Uri imageUri5 = data.getData();
                        imgPicture5.setImageURI(imageUri5);

                        list5.add(imageUri5.getPath().toString());
                        break;
                    default:
                        throw new RuntimeException("Unknow button ID");
                }
            }
        }
    }
}
