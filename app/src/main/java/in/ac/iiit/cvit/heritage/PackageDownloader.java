package in.ac.iiit.cvit.heritage;

import android.app.ProgressDialog;
import android.content.Context;
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

public class PackageDownloader extends AsyncTask<String, String, String> {

    private URL url;
    private Context _context;
    private ProgressDialog progressDialog;
    private HttpURLConnection httpURLConnection;

    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final String LOGTAG = "Heritage";

    public PackageDownloader(Context context) {
        _context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(_context);
        progressDialog.setMessage("Downloading Package");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params){
        String archive_name = params[0] + ".tar.gz";
        String address = "http://preon.iiit.ac.in/~heritage/" + archive_name;
        File baseLocal = Environment.getExternalStorageDirectory();

        try {
            url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                File archive = new File(baseLocal, archive_name);
                FileOutputStream archiveStream = new FileOutputStream(archive);

                //Output File

                InputStream input = httpURLConnection.getInputStream();
                try {
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = input.read(buffer)) != -1) {
                        archiveStream.write(buffer, 0, len);
                    }
                    archiveStream.close();
                } catch (IOException e) {
                    Log.i(LOGTAG, e.toString());
                }

                Log.i(LOGTAG, "Download Finished");
                Extract();

                return "Package Download Completed";
            } else {
                return "Connection Unsuccessful: " + String.valueOf(responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MalformedURLException";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "FileNotFoundException";
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        } finally {
            httpURLConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        Log.i(LOGTAG, result);

        if (result.equals("Package Download Complete")) {
        }
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

