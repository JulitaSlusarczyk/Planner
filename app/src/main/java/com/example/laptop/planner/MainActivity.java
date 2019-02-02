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
    private ImageView imgPicture, imgPicture2,imgPicture3, imgPicture4,imgPicture5;
    private TextView textView;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    public int imd_id;
    List<String> lista, list, list2, list3, list4, list5;
    List<String> listpn, listwt, listsr, listczw, listpt;

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
        // Change the projection ap per need
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
