package in.ac.iiit.cvit.heritage;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;


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
    /*
        The class is used to download a package.
        Extraction of package happens soon after it is downloaded.

     */
    public String baseWeb = "http://preon.iiit.ac.in/~heritage/packages/";
    public String compressedDir = "heritage/compressed/";
    public String extractDir = "heritage/extracted/";

    @Override
    protected String doInBackground(String... params){
        String packageName = params[0];
        String address = baseWeb+packageName;
        File baseLocal = Environment.getExternalStorageDirectory();


        URL url = null;
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            /*
                File to write out to.
                Writes the <package>.tar.gz file to heritage/compressed/<package>.tar.gz
                Assumes heritage/compressed is already created.
             */

            File archive = new File(baseLocal, compressedDir+packageName);
            FileOutputStream archiveStream = new FileOutputStream(archive);

            InputStream input = connection.getInputStream();

            /*
                Now read from the connection and write to the local file.
             */
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
            Log.d("PackageReader:DL", "Download Finished");

            /*
                Now extract the package.
             */
            ExtractPackage(packageName);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    void ExtractPackage(String packageName){

    }

    void bufferedWrite(InputStream in, FileOutputStream out){

    }
}

