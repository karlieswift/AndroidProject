package utils;

import android.content.Context;
import android.os.AsyncTask;

public class UniversityAsynTask   extends AsyncTask<Void, Void, Void> {

    private Context mContext = null;
    private UniversityDBManager dbManager = null;

    public UniversityAsynTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        dbManager = UniversityDBManager.getInstance(mContext);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        Toast.makeText(mContext.getApplicationContext(),"excel保存到数据库完成",Toast.LENGTH_SHORT).show();
    }
}
