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
            HttpURLConnection connxn = (HttpURLConnection) url.openConnection();
            connxn.setDoOutput(true);
            connxn.connect();

            //Input File
            File archive = new File(baseLocal, "golconda.tar.gz");
            FileOutputStream archiveStream = new FileOutputStream(archive);

            //Output File
            InputStream input = connxn.getInputStream();
            bufferedWrite(input, archiveStream);

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
        File destination = new File(baseLocal, "extracted");

        try{
            archiver.extract(archive, destination);
        }
        catch(IOException e){
            Log.d("PackageReader:extract", e.toString());
        }
    }

    void bufferedWrite(InputStream in, FileOutputStream out){
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len - in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        }
        catch(IOException e){
            Log.d("PackageReader:DL", e.toString());
        }
    }
}