package com.kcd.monapp.fragment;



import com.kcd.monapp.MainActivity;
import com.kcd.monapp.R;
import com.kcd.monapp.db.TablaMonas;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class TableFragment extends Fragment implements OnClickListener, OnLongClickListener, OnTouchListener{

	private int startNumber=0;

	private boolean longClick;

	
	private Rect rect;

	private boolean outside;
	
	private TablaMonas monas;

	private int inTableNumber;
	
	
	 /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TableFragment newInstance(int sectionNumber,int startNumber,int inTable,TablaMonas nmonas) {
    	
    	TableFragment fragment = new TableFragment();
    	fragment.setDatabase(nmonas);
    	fragment.setStartNumber(startNumber);
    	fragment.setInTableNumber(inTable);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }	
    
    public TableFragment() {
    	 /*Bundle args = new Bundle();
         args.putInt(ARG_SECTION_NUMBER, sectionNumber);
         this.setArguments(args);*/
    	longClick=false;
    	outside=false;
    	    	
    }
    public void setDatabase(TablaMonas nmonas)
    {
    	monas=nmonas;
    }
    public void setStartNumber(int nStartNumber)
    {
    	startNumber=nStartNumber;
    }
    public void setInTableNumber(int nInTable)
    {
    	inTableNumber=nInTable;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
       
        TableLayout table1 = (TableLayout) rootView.findViewById(R.id.table1);
        
        
       // table1.setPadding(15, 15, 15, 15);  
  
       
        //table1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));  
        int numero=startNumber;
		for (int i = 0; i < 10; i++) {
			TableRow r1=new TableRow(getActivity());
			r1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			r1.setGravity(Gravity.CENTER_HORIZONTAL);
        	for (int j = 0; j < 5; j++) {
        		
        		if(numero>MainActivity.NUM_LAMINAS)
        		{
        			break;
        		}
				Button b=new Button(getActivity());
				int numFichas=monas.darContador(""+numero);
				b.setText(""+numero);
				b.setTextSize(20);
				//b.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				b.setOnClickListener(this);
				
				b.setOnLongClickListener(this);
				if(numFichas>0)
				{
				b.setBackgroundResource(R.drawable.button_py_sel);
				}
				else
				{
					b.setBackgroundResource(R.drawable.button_py_desel);
				}
				
				b.setOnTouchListener(this);
				
				  
				  
				r1.addView(b);
				 
				numero++;
			}
        	//r1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        	table1.addView(r1);
        	
        	if(numero>MainActivity.NUM_LAMINAS)
    		{
    			break;
    		}
        	
		}
        
        
        
        
        return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		/*Button bt=(Button)v;
		if(!longClick)
		{
		
		String text=(String) bt.getText();
		//bt.setText(text);
		System.out.println("Suma:"+text);
		bt.setBackgroundColor(Color.LTGRAY);
		
		bt.setBackgroundResource(R.drawable.button_py_sel);
		
		}
		else
		{
			
			longClick=false;
			bt.setBackgroundResource(R.drawable.button_py_desel);
			String text=(String) bt.getText();
			//bt.setText(text);
			System.out.println("Resta:"+text);
			
		}
		outside=true;*/
		
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		
		/*Button bt=(Button)v;
		//bt.setBackgroundResource(R.drawable.button_remove);
    	
		
		longClick=true;
		*/
		
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Button bt=(Button)v;
		String value=(String) bt.getText();
		
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
	        // Construct a rect of the view's bounds
	        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());

	    }
		else if(event.getAction() == MotionEvent.ACTION_UP){
	        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
	            // User moved outside bounds
	        	
	        	if(v.getTop() + (int) event.getY()>rect.top)
	        	{
	        		System.out.println("Abajo");
	        		
	        		int numFichas=monas.darContador(value);
	        		if(numFichas>0)
	        		{
	        			monas.disminuirContador(value);
	        			

	        		}
	        		
	        		int numFichas2=monas.darContador(value);
        			if(numFichas2>0)
        			{
        				bt.setBackgroundResource(R.drawable.button_py_sel);
        			}
        			else
        			{
        				bt.setBackgroundResource(R.drawable.button_py_desel);
        			}
        			Toast.makeText(getActivity(), ""+numFichas2, Toast.LENGTH_LONG).show();
					
					
	        	}
	        	else if(v.getTop() + (int) event.getY()<v.getBottom())
	        	{
	        		System.out.println("Arriba");
	        		bt.setBackgroundResource(R.drawable.button_py_sel);
	        		int numFichas=monas.darContador(value);
	        		
	        		monas.aumentarContador(value);
	        		Toast.makeText(getActivity(), ""+(numFichas+1), Toast.LENGTH_LONG).show();
	        		
	        		
	        		
	        	}else
	        	{
	        		int numFichas2=monas.darContador(value);
        			if(numFichas2>0)
        			{
        				bt.setBackgroundResource(R.drawable.button_py_sel);
        			}
        			else
        			{
        				bt.setBackgroundResource(R.drawable.button_py_desel);
        			}
        			Toast.makeText(getActivity(), ""+numFichas2, Toast.LENGTH_LONG).show();
	        	
	        	}
	        	
	        }
	        else
	        {
	        	int numFichas2=monas.darContador(value);
    			if(numFichas2>0)
    			{
    				bt.setBackgroundResource(R.drawable.button_py_sel);
    			}
    			else
    			{
    				bt.setBackgroundResource(R.drawable.button_py_desel);
    			}
    			Toast.makeText(getActivity(), ""+numFichas2, Toast.LENGTH_LONG).show();
	        }
	        
	    }
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
	        if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
	            // User moved outside bounds
	        	
	        	if(v.getTop() + (int) event.getY()>rect.top)
	        	{
	        		System.out.println("Caneca");
	        		bt.setBackgroundResource(R.drawable.button_remove);
					
	        	}
	        	else
	        	{
	        		bt.setBackgroundResource(R.drawable.button_py_desel);
	        	}
	        	
	        	
	        }
	        
	    }
		else
		{
			bt.setBackgroundResource(R.drawable.button_py_desel);
		}
		
		
		
		
		return false;
	}

	

	
}
