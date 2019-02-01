package com.example.laptop.planner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    private static final String FILE_NAME = "week.txt";
    private ImageView imgPicture, imgPicture2,imgPicture3, imgPicture4,imgPicture5;
    public int imd_id;
    List<String> lista, list, list2, list3, list4, list5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }
        else
        {

        }

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
    }

    public void onButtonClick(View v)
    {
        Toast.makeText(this, Integer.toString(lista.size()), Toast.LENGTH_LONG).show();
    }

    public void zapis(View v)
    {
        wypelnij();
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
            Toast.makeText(v.getContext(),"Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(v.getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
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
            lista.add("/storage/emulated/0/Pictures/Bacon Picture 8.jpg");
        }
        //............................
        if(list2.size()>=1)
        {
            lista.add(list2.get(list2.size()-1));
        }
        else
        {
            lista.add("/storage/emulated/0/Pictures/Bacon Picture 8.jpg");
        }
        //............................
        if(list3.size()>=1)
        {
            lista.add(list3.get(list3.size()-1));
        }
        else
        {
            lista.add("/raw//storage/emulated/0/Pictures/Bacon Picture 8.jpg");
        }
        //............................
        if(list4.size()>=1)
        {
            lista.add(list4.get(list4.size()-1));
        }
        else
        {
            lista.add("/raw//storage/emulated/0/Pictures/Bacon Picture 8.jpg");
        }
        //............................
        if(list5.size()>=1)
        {
            lista.add(list5.get(list5.size()-1));
        }
        else
        {
            lista.add("/raw//storage/emulated/0/Pictures/Bacon Picture 8.jpg");
        }
    }


    public void load(View v)
    {
        //FileInputStream fis = null;
        try
        {
//            fis = openFileInput(FILE_NAME);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            String text;
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fileReader);

            //while ((text = br.readLine()) != null)
            for(int i=0; i<5;i++)
            {
                lista.add(br.readLine());
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //przyklad
        File imgFile = new  File(lista.get(1));
        Toast.makeText(this, imgFile.getPath(), Toast.LENGTH_LONG).show();

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.imageView);

            myImage.setImageBitmap(myBitmap);

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

                        list.add(imageUri.getPath().toString());
                        Toast.makeText(this, imageUri.getPath().toString(), Toast.LENGTH_LONG).show();
                    break;
                    case R.id.imageView2:
                        Uri imageUri2 = data.getData();
                        imgPicture2.setImageURI(imageUri2);

                        list2.add(imageUri2.getPath().toString());
                        Toast.makeText(this, imageUri2.getPath().toString(), Toast.LENGTH_LONG).show();
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
