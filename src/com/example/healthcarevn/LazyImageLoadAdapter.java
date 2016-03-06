package com.example.healthcarevn;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//Adapter class extends with BaseAdapter and implements with OnClickListener 
public class LazyImageLoadAdapter extends BaseAdapter implements OnClickListener{
    
    private Activity activity;
    private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyImageLoadAdapter(Activity a, String[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
         
        public TextView textViewNameDoctor;
        public TextView textViewSomeInfo;
        public TextView textWide;
        public ImageView imageViewDoctor;
 
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi=convertView;
        ViewHolder holder;
         
        if(convertView==null){
             
            vi = inflater.inflate(R.layout.doctor_info, null);
             

            holder = new ViewHolder();
            holder.textViewNameDoctor = (TextView) vi.findViewById(R.id.textViewNameDoctor);
            holder.textViewSomeInfo   = (TextView)vi.findViewById(R.id.textViewSomeInfo);
            holder.imageViewDoctor    = (ImageView)vi.findViewById(R.id.imageViewDoctor);
             
            vi.setTag( holder );
        }
        else 
            holder=(ViewHolder)vi.getTag();
        
        
        holder.textViewNameDoctor.setText("Company "+position);
        holder.textViewSomeInfo.setText("company description "+position);
        ImageView image = holder.imageViewDoctor;
        
        //DisplayImage function from ImageLoader Class
        imageLoader.DisplayImage(data[position], image);
        
        vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
    
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
        
       OnItemClickListener(int position){
        	 mPosition = position;
        }
        
        @Override
        public void onClick(View arg0) {
        	ResultListViewDoctorActivity sct = (ResultListViewDoctorActivity)activity;
        	sct.onItemClick(mPosition);
        }               
    }   
}