package in.ac.iiit.cvit.heritage;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jerin on 24/8/16.
 */
public class Downloader extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... f_url){
        //String address = "http://web.iiit.ac.in/~ashwin.sudhir/index.txt";
        String address = "http://preon.iiit.ac.in/~heritage/golconda.tar.gz";
        //String baseWeb = "http://web.iiit.ac.in/~ashwin.sudhir/parent";
        File baseLocal = Environment.getExternalStorageDirectory();

        URL url = null;
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();
            //Input File
            File archive = new File(baseLocal, "golconda.tar.gz");
            FileOutputStream archiveStream = new FileOutputStream(archive);

            //Output File

            InputStream input = connection.getInputStream();
            try {
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    archiveStream.write(buffer, 0, len);
                }
                archiveStream.close();
            }
            catch(IOException e){
                Log.d("PackageReader:DL", e.toString());
            }
            /*
            String s = "What the hell";
            archiveStream.write(s.getBytes());
            */
            //bufferedWrite(input, archiveStream);
            Log.d("PackageReader:DL", "Download Finished");
            Extract();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void Extract(){
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
        // Snippet from https://github.com/thrau/jarchivelib
        File baseLocal = Environment.getExternalStorageDirectory();
        File archive = new File(baseLocal, "golconda.tar.gz");
        File destination = new File(baseLocal, "heritage");


        try{
            archiver.extract(archive, destination);
        }
        catch(IOException e){
            Log.d("PackageReader:extract", e.toString());
        }
    }

    void bufferedWrite(InputStream in, FileOutputStream out){

    }
}

