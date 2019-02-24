package nl.psdcompany.duonavigationdrawer.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Gallery2 extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;


    JSONArray ja;
    //   private Integer[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    public Gallery2(Context context, JSONArray ja) {
        this.context = context;
        this.ja = ja;
    }

    @Override
    public int getCount() {
        return ja.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        try {
            JSONObject obj = ja.getJSONObject(position);
            String imgpath = obj.getString("imagepath");
            Log.d("image", imgpath);
            // imageView.setImageBitmap(BitmapFactory.decodeFile(imgpath));
           // ccc
            Picasso.with(context).load(imgpath).into(imageView);
           // Picasso.with(context).load("https://www.dropbox.com/s/uyri2ytom0pe294/_20170205_181821_1486298853756.jpg?dl=1").into(imageView);

        //    https://www.dropbox.com/s/p7regm4rwd5oy63/IMG_20161205_083159.jpg?dl=1

            //  imageView.setImageResource(images[position]);
            Log.d("yhbujn", "---" + imageView);
            ViewPager vp = (ViewPager) container;
            vp.addView(view);
        } catch (Exception e) {
            Log.e("72", e.getMessage());
        }
        return view;


    }

    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

//    public class ImageLoader{
//
//        public ImageLoader(ImageView imageView, final ProgressBar progressBar, String imagePath){
//
//            Glide.with(context).load(imagePath).listener(new RequestListener<String, GlideDrawable>() {
//
//                @Override
//                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                    //handle error
//                    return false;
//                }
//
//                @Override
//                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                    progressBar.setVisibility(View.GONE);
//                    return false;
//                }
//            }).into(imageView);
//        }
//    }
}

